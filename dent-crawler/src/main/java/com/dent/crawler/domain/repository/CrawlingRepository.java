package com.dent.crawler.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dent.crawler.domain.entity.CrawlingEntity;

@Repository
public interface CrawlingRepository extends MongoRepository<CrawlingEntity, String> {

}
