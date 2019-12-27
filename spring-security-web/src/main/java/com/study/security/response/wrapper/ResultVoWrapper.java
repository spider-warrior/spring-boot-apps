package com.study.security.response.wrapper;

import com.study.security.constants.ErrorCode;
import com.study.security.response.vo.ResultVo;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResultVoWrapper {

    private static final String SUCCESS = "200";
    private static final String INTERNAL_ERROR = "500";


    /**
     * 成功
     */
    public ResultVo buildSuccess() {
        return buildSuccess(null, null);
    }

    public ResultVo buildSuccess(Map<String, Object> data) {
        return buildSuccess(null, data);
    }

    public ResultVo buildSuccess(String message) {
        return buildSuccess(message, null);
    }

    public ResultVo buildSuccess(String message, Map<String, Object> data) {
        ResultVo vo = new ResultVo();
        vo.setCode(SUCCESS);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

    /**
     * 失败
     */
    public ResultVo buildFail() {
        return buildFail(INTERNAL_ERROR, (String) null);
    }

    public ResultVo buildFail(String errorCode) {
        return buildFail(errorCode, (String) null);
    }

    public ResultVo buildFail(String errorCode, String msg) {
        return buildFail(errorCode, msg, null);
    }

    public ResultVo buildFail(ErrorCode errorCode) {
        return buildFail(errorCode.value, errorCode.description, null);
    }

    public ResultVo buildFail(String errorCode, Map<String, Object> data) {
        return buildFail(errorCode, null, data);
    }

    public ResultVo buildFail(String errorCode, String message, Map<String, Object> data) {
        ResultVo vo = new ResultVo();
        vo.setCode(errorCode);
        vo.setMessage(message);
        vo.setData(data);
        return vo;
    }

}
