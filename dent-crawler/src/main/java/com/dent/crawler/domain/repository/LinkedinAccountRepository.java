package com.dent.crawler.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dent.crawler.domain.entity.LinkedinAccountEntity;

@Repository
public interface LinkedinAccountRepository extends MongoRepository<LinkedinAccountEntity, String> {

    @Query(value = "{ 'status' : ?0 }", sort = "{'lastAccessDate': 1}")
    List<LinkedinAccountEntity> findByStatus(Boolean status);

}
