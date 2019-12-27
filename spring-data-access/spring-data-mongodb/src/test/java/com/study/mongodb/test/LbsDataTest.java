//package com.study.mongodb.test;
//
//import com.study.mongodb.MongodbApplication;
//import com.study.mongodb.dao.LbsRecordRepository;
//import com.study.mongodb.dao.LbsRepository;
//import com.wxsk.geo.constant.CoordinateType;
//import com.wxsk.geo.constant.LbsExtensionType;
//import com.wxsk.geo.model.Lbs;
//import com.wxsk.geo.model.base.LbsHistoryBase;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Date;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MongodbApplication.class})
//public class LbsDataTest {
//
//    @Autowired
//    private LbsRepository lbsRepository;
//
//    @Test
//    public void lbsTest() {
//        Lbs lbs = new Lbs();
//        lbs.setAddress("天安门广场西侧");
//        lbs.setCrTime(new Date());
//        lbs.setObjId(3L);
//        lbs.setTitle("张亚龙");
//        lbs.setType(CoordinateType.PERSON.getValue());
//        LbsHistoryBase.Coordinate loc = new LbsHistoryBase.Coordinate();
//        loc.setCoordinates(new double[] { 116.402778,39.909401 });
//        lbs.setLoc(loc);
//        lbs.setDepartmentId(1L);
//        lbs.setCrTime(new Date());
//        lbs.setExtensionValue(LbsExtensionType.HUMAN_ROLE, "消防员");
//        lbsRepository.insert(lbs);
//
////        Lbs lbs = new Lbs();
////        lbs.setAddress("天安门广场北侧");
////        lbs.setCrTime(new Date());
////        lbs.setObjId(8L);
////        lbs.setTitle("杨建");
////        lbs.setType(CoordinateType.PERSON.getValue());
////        LbsHistoryBase.Coordinate loc = new LbsHistoryBase.Coordinate();
////        loc.setCoordinates(new double[] { 116.40558,39.911435 });
////        lbs.setLoc(loc);
////        lbs.setDepartmentId(1L);
////        lbs.setCrTime(new Date());
////        lbsRepository.insert(lbs);
//    }
//
//}
