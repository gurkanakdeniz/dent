package com.dent.crawler.core.job.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.job.model.CrawlingJobModel;
import com.dent.crawler.core.job.model.JobStatus;
import com.dent.crawler.service.job.IJobService;
import com.dent.crawler.service.job.model.RequestList;
import com.dent.crawler.service.job.model.RequestUpdate;
import com.dent.crawler.service.job.model.ResponseList;

@Component
public class CrawlingJobFactory {
    private static final Logger logger = LoggerFactory.getLogger(CrawlingJobFactory.class);

    @Value("${crawler.job.max-running}")
    public int MAX_RUNNING_JOB;

    @Value("${crawler.job.check-time}")
    public long JOBS_CHECK_TIME;

    private static final Map<String, Pair<CrawlingJob, Future<?>>> JOB_TASKS = new HashMap<String, Pair<CrawlingJob, Future<?>>>();
    private static ExecutorService worker;

    @Autowired
    IJobService jobService;

    public void init() {
        logger.info("--- crawling job factory start ---");

        runningToWaiting();

        worker = Executors.newFixedThreadPool(MAX_RUNNING_JOB);

        // INFO:GA: override
//        ThreadPoolExecutor asd = null;
//        asd.setCorePoolSize(corePoolSize);
//        asd.setMaximumPoolSize(maximumPoolSize);

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(() -> {
            try {
                prepareTasks();
            } catch (Throwable e) {
                logger.info("--- prepare jobs end ---");
                service.shutdown();
                try {
                    service.awaitTermination(1, TimeUnit.SECONDS);
                } catch (Throwable ex) {
                    // ignore
                }
            }
        }, 0, JOBS_CHECK_TIME, TimeUnit.MILLISECONDS);

        logger.info("--- crawling job factory end ---\n");
    }

    public void prepareTasks() {
        logger.info("--- prepare jobs start ---");

        List<CrawlingJobModel> crawlingJobs = getJobs();
        if (crawlingJobs != null && crawlingJobs.size() > 0) {
            synchronized (crawlingJobs) {
                for (CrawlingJobModel item : crawlingJobs) {
                    try {
                        Pair<CrawlingJob, Future<?>> pair = JOB_TASKS.get(item.getId());
                        if (pair != null) {
                            Future<?> task = pair.getSecond();
                            if (task != null) {
                                checkTask(item, task);
                                continue;
                            }
                        }

                        addTask(item);
                    } catch (Throwable e) {
                        logger.error("--- jobs ---", e);
                    }
                }
            }
        }

        logger.info("---  prepare jobs end  ---\n");
    }

    private void checkTask(CrawlingJobModel item, Future<?> task) {
        if (JobStatus.RUNNING.equals(item.getStatus()) || JobStatus.FINISHED.equals(item.getStatus())) {
            String jobId = item.getId();
            if (JobStatus.RUNNING.equals(item.getStatus())) {
                if (task.isCancelled() || task.isDone()) {
                    jobService.update(new RequestUpdate(jobId, JobStatus.FINISHED));
                    JOB_TASKS.remove(jobId);
                    logger.info("--- job db running status and tasks sync ---");
                }
            } else if (JobStatus.FINISHED.equals(item.getStatus())) {
                if (!task.isCancelled() || !task.isDone()) {
                    task.cancel(true);
                    JOB_TASKS.remove(jobId);
                    logger.info("--- job db finished status and tasks sync ---");
                }
            }
        }
    }

    private void addTask(CrawlingJobModel item) {
        if (JobStatus.WAITING.equals(item.getStatus()) || JobStatus.RUNNING.equals(item.getStatus())) {
            CrawlingModel crawlingModel = item.getCrawlingModel();
            String jobId = item.getId();
            CrawlingJob job = new CrawlingJob(new ThreadGroup(jobId), new CrawlingJobRunner(jobId, crawlingModel),
                    jobId, crawlingModel);

            try {
                job.setDaemon(true);
            } catch (Throwable e) {
                // ignore
            }

            Future<?> nextTask = worker.submit(job);
            Pair<CrawlingJob, Future<?>> pair = Pair.of(job, nextTask);
            JOB_TASKS.put(jobId, pair);
        }
    }

    private List<CrawlingJobModel> getJobs() {
        ResponseList list = jobService.list(new RequestList());
        return list.getCrawlingJobs();
    }

    private void runningToWaiting() {
        List<CrawlingJobModel> runningJobs = jobService.list(new RequestList(JobStatus.RUNNING)).getCrawlingJobs();
        for (CrawlingJobModel item : runningJobs) {
            try {
                jobService.update(new RequestUpdate(item.getId(), JobStatus.WAITING));
                logger.info("--- job finished : {}-{}-{} ---", item.getId(), item.getCrawlingModel().getProfileId(),
                        item.getCrawlingModel().getMode());
            } catch (Throwable e) {
                logger.error("--- running to waiting error --- ", e);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void stop(String jobId) {
        try {
            Pair<CrawlingJob, Future<?>> pair = JOB_TASKS.get(jobId);
            if (pair != null) {
                CrawlingJob job = pair.getFirst();
                try {
                    job.stop();
                } catch (Throwable e) {
                    logger.error("--- stop " + jobId + "---");
                }

                try {
                    job.interrupt();
                } catch (Throwable e) {
                    logger.error("--- stop " + jobId + "---");
                }

                try {
                    job.getThreadGroup().interrupt();
                } catch (Throwable e) {
                    logger.error("--- stop " + jobId + "---");
                }

                try {
                    job.getThreadGroup().destroy();
                } catch (Throwable e) {
                    logger.error("--- stop " + jobId + "---");
                }
            }
        } catch (Throwable e) {
            logger.error("--- stop " + jobId + "---");
        }

    }

}
