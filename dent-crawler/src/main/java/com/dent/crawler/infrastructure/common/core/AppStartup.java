package com.dent.crawler.infrastructure.common.core;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.dent.crawler.core.job.core.CrawlingJobFactory;
import com.dent.crawler.core.linkedin.core.WebUnitBuilder;
import com.dent.crawler.core.proxy.core.ProxyClientBuilder;

@Component
public class AppStartup implements ApplicationRunner {
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        AppContext.getContext().getBean(CrawlingJobFactory.class).init();
        AppContext.getContext().getBean(WebUnitBuilder.class).init();
        AppContext.getContext().getBean(ProxyClientBuilder.class).init();

        
    }
}