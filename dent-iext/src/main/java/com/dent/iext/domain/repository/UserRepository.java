package com.dent.iext.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dent.iext.domain.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

}
