package com.study.mongodb.dao;

import com.study.mongodb.entity.LbsRecord;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LbsRecordRepository extends MongoRepository<LbsRecord, String> {

    List<LbsRecord> findByLocNear(Point location, Distance distance);
}
