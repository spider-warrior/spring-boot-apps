package com.study.test.web.controller;

import com.study.test.web.param.CreateUserParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 参数校验控制器
 *
 * @author <a href="mailto:jian.yang@liby.ltd">野生程序员-杨建</a>
 * @version V1.0
 * @since 2020-03-07 21:28
 **/
@RequestMapping("param")
@RestController
@Validated
public class ParamValidateController {

    @RequestMapping("singleParamTest")
    public String singleParamTest(@NotNull(message = "id不能为空") @Min(value = 1, message = "id不能小于1") @Max(value = 9, message = "id不能大于9") @RequestParam(required = false) Long id) {
        return "success";
    }

    @PostMapping("objectTest")
    public String objectTest(@Valid @RequestBody CreateUserParam createUserParam) {
        return "success";
    }



}
