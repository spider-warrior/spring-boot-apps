package com.study.mongodb.dao;

import com.study.mongodb.entity.Lbs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LbsRepository extends MongoRepository<Lbs, String> {
}
