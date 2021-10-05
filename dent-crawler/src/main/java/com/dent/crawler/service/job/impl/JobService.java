package com.dent.crawler.service.job.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.crawling.model.FilterType;
import com.dent.crawler.core.crawling.model.Mode;
import com.dent.crawler.core.job.model.CrawlingJobModel;
import com.dent.crawler.core.job.model.JobStatus;
import com.dent.crawler.domain.entity.CrawlingJobEntity;
import com.dent.crawler.domain.model.CrawlingModelDTO;
import com.dent.crawler.domain.repository.CrawlingJobRepository;
import com.dent.crawler.service.job.IJobService;
import com.dent.crawler.service.job.model.RequestAdd;
import com.dent.crawler.service.job.model.RequestDelete;
import com.dent.crawler.service.job.model.RequestList;
import com.dent.crawler.service.job.model.RequestUpdate;
import com.dent.crawler.service.job.model.ResponseAdd;
import com.dent.crawler.service.job.model.ResponseDelete;
import com.dent.crawler.service.job.model.ResponseList;
import com.dent.crawler.service.job.model.ResponseUpdate;

@Service
public class JobService implements IJobService {

    @Autowired
    CrawlingJobRepository crawlingJobRepository;

    @Override
    public ResponseAdd add(RequestAdd request) {
        CrawlingJobEntity entity = new CrawlingJobEntity();
        entity.setCreateDate(new Date());
        entity.setStatus(JobStatus.WAITING.getCode());
        CrawlingModel crawlingModel = request.getCrawlingModel();

        String filterType = crawlingModel.getFilterType() != null ? crawlingModel.getFilterType().getCode() : null;
        entity.setCrawlingModel(new CrawlingModelDTO(crawlingModel.getMode().getCode(), crawlingModel.getProfileId(),
                crawlingModel.getSummary(), filterType, crawlingModel.getFilter()));

        crawlingJobRepository.save(entity);

        return new ResponseAdd(entity.getId());
    }

    @Override
    public ResponseList list(RequestList request) {
        List<CrawlingJobModel> crawlingJobs = new ArrayList<CrawlingJobModel>();

        if (request.getJobId() != null) {
            Optional<CrawlingJobEntity> entity = crawlingJobRepository.findById(request.getJobId());
            if (entity != null && entity.isPresent()) {
                CrawlingJobEntity crawlingJobEntity = entity.get();

                CrawlingModelDTO crawlingModel = crawlingJobEntity.getCrawlingModel();
                if (crawlingModel != null) {
                    crawlingJobs.add(new CrawlingJobModel(crawlingJobEntity.getId(),
                            JobStatus.fromCode(crawlingJobEntity.getStatus()), crawlingJobEntity.getCreateDate(),
                            crawlingJobEntity.getStartDate(), crawlingJobEntity.getEndDate(),
                            new CrawlingModel(Mode.fromCode(crawlingModel.getMode()), crawlingModel.getProfileId(),
                                    crawlingModel.getSummary(), FilterType.fromCode(crawlingModel.getFilterType()),
                                    crawlingModel.getFilter())));
                }
            }

        } else {
            List<CrawlingJobEntity> entities = request.getJobStatus() != null
                    ? crawlingJobRepository.findByJobStatus(request.getJobStatus().getCode())
                    : crawlingJobRepository.findAll(Sort.by(Direction.DESC, "createDate"));
            if (entities != null && entities.size() > 0) {
                for (CrawlingJobEntity item : entities) {
                    CrawlingModelDTO crawlingModel = item.getCrawlingModel();
                    if (crawlingModel != null) {
                        crawlingJobs.add(new CrawlingJobModel(item.getId(), JobStatus.fromCode(item.getStatus()),
                                item.getCreateDate(), item.getStartDate(), item.getEndDate(),
                                new CrawlingModel(Mode.fromCode(crawlingModel.getMode()), crawlingModel.getProfileId(),
                                        crawlingModel.getSummary(), FilterType.fromCode(crawlingModel.getFilterType()),
                                        crawlingModel.getFilter())));
                    }
                }
            }
        }

        return new ResponseList(crawlingJobs);
    }

    @Override
    public ResponseDelete delete(RequestDelete request) {
        crawlingJobRepository.deleteById(request.getId());
        return new ResponseDelete(request.getId());
    }

    @Override
    public ResponseUpdate update(RequestUpdate request) {
        Optional<CrawlingJobEntity> entity = crawlingJobRepository.findById(request.getJobId());

        if (entity.isPresent()) {
            CrawlingJobEntity crawlingJobEntity = entity.get();
            JobStatus jobStatus = request.getJobStatus();
            switch (jobStatus) {
                case FINISHED:
                    crawlingJobEntity.setEndDate(new Date());
                    break;
                case RUNNING:
                    crawlingJobEntity.setStartDate(new Date());
                    break;
                default:
                    break;
            }

            crawlingJobEntity.setStatus(jobStatus.getCode());
            crawlingJobRepository.save(crawlingJobEntity);

            return new ResponseUpdate(request.getJobId());
        }

        return new ResponseUpdate("");
    }

}
