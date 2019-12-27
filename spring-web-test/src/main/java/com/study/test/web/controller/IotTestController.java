package com.study.test.web.controller;

import cn.t.util.common.digital.ShortUtil;
import org.apache.tomcat.util.buf.HexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("test")
@RestController
public class IotTestController {

    private static final Logger logger =  LoggerFactory.getLogger(IotTestController.class);

    @RequestMapping("device_data")
    public void receiveDeviceData(@RequestBody(required = false) String body) {
        logger.info("data: {}", body);
    }

    @RequestMapping("deviceDataChanged")
    public void deviceDataChanged(@RequestBody(required = false) IotMsg deviceDataChanged) {
        IotService service = deviceDataChanged.getService();
        Map<String, Object> data = service.getData();
        String dtaValue = (String)data.get("dataVal1");
        logger.info("\r\nreceive a [{}] msg, deviceId: [{}]"
            + "\r\nmsg details:"
            + "\r\n{"
            + "\r\nservice type: {}"
            + "\r\nservice id: {}"
            + "\r\nevent time: {}"
            + "\r\nIMEI: {}"
            + "\r\ndata transport type: [{}({})]"
            + "\r\ndata value: [{}]"
            + "\r\n}",
            deviceDataChanged.getNotifyType(), deviceDataChanged.getDeviceId(),
            service.getServiceType(), service.getServiceId(), service.getEventTime(),
            HexUtils.toHexString(Base64Utils.decode(((String)data.get("IMEI")).getBytes())), data.get("dataType1"), DataTransportType.getDataTransportType((Integer)data.get("dataType1")), analyseValue((dtaValue)));
    }

    private String analyseValue(String source) {
        byte[] bytes = HexUtils.fromHexString(source);
        StringBuilder builder = new StringBuilder();
        builder.append("\r\n状态: ").append(analyseStatus(bytes[0], bytes[1]));
        builder.append("\r\n事件: [").append(analyseEvent(bytes[2])).append("]");
        builder.append("\r\n故障代码: ").append(analyseErrorNum(bytes[3]));
        builder.append("\r\n电量1: ").append(analyseBattery(bytes[4], bytes[5]));
        builder.append("\r\n电量2: ").append(analyseBattery(bytes[6], bytes[7]));
        return builder.toString();
    }

    private String analyseStatus(byte byte1, byte byte2) {
        StringBuilder builder = new StringBuilder();
        builder.append("\r\nbyte1{");
        builder.append("报警: ").append((byte1&1)==1);
        builder.append(",报警音: ").append(((byte1>>1)&1)==1);
        builder.append(",防拆状态: ").append(((byte1>>2)&1)==1);
        builder.append(",电池低压: ").append(((byte1>>3)&1)==1);
        builder.append(",主故障: ").append(((byte1>>4)&1)==1);
        builder.append(",其他故障: ").append(((byte1>>5)&1)==1);
        builder.append(",reserved: ").append(((byte1>>6)&1)==1);
        builder.append(",reserved: ").append(((byte1>>7)&1)==1);
        builder.append("}");

        builder.append("\r\nbyte2{");
        builder.append("报警2: ").append((byte1&1)==1);
        builder.append(",reserved: ").append(((byte1>>1)&1)==1);
        builder.append(",防拆2: ").append(((byte1>>2)&1)==1);
        builder.append(",电池2低压: ").append(((byte1>>3)&1)==1);
        builder.append(",reserved: ").append(((byte1>>4)&1)==1);
        builder.append(",reserved: ").append(((byte1>>5)&1)==1);
        builder.append(",reserved: ").append(((byte1>>6)&1)==1);
        builder.append("内部通讯故障: ").append(((byte1>>7)&1)==1);
        builder.append("}");
        return builder.toString();
    }

    private Event analyseEvent(byte value) {
        return Event.getEvent(value);
    }

    private String analyseErrorNum(byte value) {
        return String.valueOf(value);
    }

    private double analyseBattery(byte byte1, byte byte2) {
        return ShortUtil.bytesToShort(new byte[]{byte1, byte2})/100.0D;
    }

}

class IotMsg {
    private String deviceId;
    private String gatewayId;
    private String notifyType;
    private String requestId;
    private IotService service;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public IotService getService() {
        return service;
    }

    public void setService(IotService service) {
        this.service = service;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IotMsg{");
        sb.append("deviceId='").append(deviceId).append('\'');
        sb.append(", gatewayId='").append(gatewayId).append('\'');
        sb.append(", notifyType='").append(notifyType).append('\'');
        sb.append(", requestId='").append(requestId).append('\'');
        sb.append(", service=").append(service);
        sb.append('}');
        return sb.toString();
    }
}

class IotService {
    private String serviceId;
    private String serviceType;
    private String eventTime;
    private Map<String, Object> data;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IotService{");
        sb.append("serviceId='").append(serviceId).append('\'');
        sb.append(", serviceType='").append(serviceType).append('\'');
        sb.append(", eventTime='").append(eventTime).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}

enum DataTransportType {

    上报数据(1),
    透传数据(2);
    public final int value;

    DataTransportType(int value) {
        this.value = value;
    }

    public static final DataTransportType getDataTransportType(int value) {
        for(DataTransportType transportType: values()) {
            if(transportType.value == value) {
                return transportType;
            }
        }
        return null;
    }
}

enum Event {
    无事件(0),
    心跳(1),
    报警(2),
    报警恢复(3),
    防拆报警(4),
    防拆报警恢复(5),
    电池低压(6),
    电池电压恢复(7),
    电池2低压(8),
    电池2电压恢复(9),
    烟感自检(10),
    通讯板自检(11),
    内部通讯异常(12),
    内部通讯恢复(13),
    探测器故障(14),
    探测器故障恢复(15);
    public final int value;
    Event(int value) {
        this.value = value;
    }

    public static final Event getEvent(int value) {
        for(Event event: values()) {
            if(event.value == value) {
                return event;
            }
        }
        return null;
    }
}
