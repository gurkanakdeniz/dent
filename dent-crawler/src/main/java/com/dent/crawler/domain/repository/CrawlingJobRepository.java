package com.dent.crawler.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dent.crawler.domain.entity.CrawlingJobEntity;

@Repository
public interface CrawlingJobRepository extends MongoRepository<CrawlingJobEntity, String> {

    @Query("{ 'status' : ?0 }")
    List<CrawlingJobEntity> findByJobStatus(String jobStatus);

}