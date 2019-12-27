package com.study.mongodb.runner;

import com.study.mongodb.dao.LbsRecordRepository;
import com.study.mongodb.entity.LbsRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Component;

@Component
public class LbsRecordRunner implements CommandLineRunner {

    private LbsRecordRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new LbsRecord("point1", new double[]{1,1}));
        repository.save(new LbsRecord("point2", new double[]{10, 10}));

        // fetch all customers
        System.out.println("lbs-record found with findAll():");
        System.out.println("-------------------------------");
        for (LbsRecord lbsRecord : repository.findAll()) {
            System.out.println(lbsRecord);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("查询附近");
        System.out.println("--------------------------------");

        System.out.println(repository.findByLocNear(new GeoJsonPoint(0.8, 0.8), new Distance(1000000, Metrics.NEUTRAL)));

    }

    public LbsRecordRunner(LbsRecordRepository lbsRecordRepository) {
        this.repository = lbsRecordRepository;
    }
}
