package com.study.web.auth.jwt.constants;

/**
 * service error code
 * check_xxxx: 状态检查异常
 * login_xxxx: 登录异常
 * user_xxxx: 用户异常
 */
public enum ErrorCode {

    /********************************************** common ***************************************************************/
    /**
     * 用户未登录
     */
    CHECK_NO_USER_LOGIN("check_0001", "用户未登录"),

    /**
     * 用户无权限
     */
    CHECK_NO_AUTHORITY("check_0002", "无权限"),

    /**
     * 参数缺失
     */
    FORM_PARAM_MISSING("form_param_missing", "参数缺失"),

    /**
     * 不合法的排序条件
     * */
    FORM_INVALID_ORDER_BY_CLAUSE_INVALID("form_invalid_order_by_clause_invalid", "排序条件不合法"),

    /**
     * 主键缺失
     * */
    FORM_ID_MISSING("form_id_missing", "主键不存在"),

    /**
     * 唯一约束冲突
     * */
    FORM_DUPLICATE_UNIQUE_KEY_EXIST("form_duplicate_unique_key_exist", "唯一约束冲突"),
    /********************************************** user ********************************************************************/

    /**
     * 用户不存在
     */
    CHECK_USER_NOT_FOUND("check_user_not_found", "用户不存在"),

    /**
     * 用户已存在
     */
    CHECK_USER_ALREADY_EXISTS("check_user_already_exists", "用户已存在"),

    /**
     * 用户名缺失
     */
    FORM_USERNAME_MISSING("form_username_missing", "用户名参数缺失"),

    /**
     * 原密码错误
     */
    FORM_ORIGINAL_PASSWORD_WRONG("form_original_password_missing", "原密码参数错误"),

    /**
     * 原密码缺失
     */
    FORM_ORIGINAL_PASSWORD_MISSING("form_original_password_missing", "原密码参数缺失"),

    /**
     * 新密码缺失
     */
    FORM_NEW_PASSWORD_MISSING("form_new_password_missing", "新密码参数缺失"),

    /**
     * 密码缺失
     */
    FORM_PASSWORD_MISSING("form_password_missing", "密码参数缺失"),

    /**
     * 角色缺失
     */
    FORM_ROLE_MISSING("form_role_missing", "角色参数缺失"),

    /**
     * 昵称缺失
     */
    FORM_NICKNAME_MISSING("form_nickname_missing", "昵称参数缺失"),

    /********************************************** role ********************************************************************/

    /**
     * 角色ID缺失
     */
    FORM_ROLE_ID_MISSING("form_role_id_missing", "角色ID缺失"),

    /**
     * 角色名称缺失
     */
    FORM_ROLE_NAME_MISSING("form_role_name_missing", "角色名称缺失"),

    /**
     * 角色不存在
     */
    CHECK_ROLE_NOT_FOUND("check_role_not_found", "角色不存在"),

    /**
     * 角色已存在
     */
    CHECK_ROLE_ALREADY_EXISTS("check_role_already_exists", "角色已存在"),


    /********************************************** contract ***************************************************************/
    /**
     * 合同不存在
     */
    CHECK_CONTRACT_NOT_FOUND("check_contract_not_found", "合同不存在"),

    /**
     * 合同已完成
     */
    CHECK_CONTRACT_ALREADY_FINISHED("check_contract_already_finished", "合同已完成,不允许编辑"),

    /**
     * 区域缺失
     * */
    FORM_CONTRACT_AREA_MISSING("form_contract_area_missing", "区域参数缺失"),

    /**
     * 甲方缺失
     * */
    FORM_CONTRACT_FIRST_PARTY_NAME_MISSING("form_contract_first_party_name_missing", "甲方参数缺失"),

    /**
     * 乙方缺失
     * */
    FORM_CONTRACT_SECOND_PARTY_NAME_MISSING("form_contract_second_party_name_missing", "乙方参数缺失"),

    /**
     * 中标通知书缺失
     * */
    FORM_CONTRACT_LETTER_OF_ACCEPTANCE_MISSING("form_contract_letter_of_acceptance_missing", "中标通知书参数缺失"),

    /**
     * 合同编号缺失
     * */
    FORM_CONTRACT_NO_MISSING("form_contract_no_missing", "合同编号参数缺失"),

    /**
     * 合同名称缺失
     * */
    FORM_CONTRACT_NAME_MISSING("form_contract_name_missing", "合同名称参数缺失"),

    /**
     * 合同金额缺失
     * */
    FORM_CONTRACT_AMOUNT_MISSING("form_contract_amount_missing", "合同金额参数缺失"),

    /********************************************** contract order ***************************************************************/

    /**
     * 合同订单已完成
     */
    CHECK_CONTRACT_ORDER_ALREADY_FINISHED("check_contract_order_already_finished", "订单已完成,不允许修改"),

    /**
     * 合同ID缺失
     * */
    FORM_CONTRACT_ID_MISSING("form_contract_id_missing", "合同ID为空"),

    /**
     * 订单不存在
     */
    CHECK_CONTRACT_ORDER_NOT_FOUND("check_contract_order_not_found", "订单不存在"),

    /**
     * 存在未结束的订单
     */
    CHECK_CONTRACT_ORDER_WITHOUT_END_STATUS("check_contract_order_without_end_status", "有订单尚未结束"),

    /**
     * 订单编号缺失
     * */
    FORM_CONTRACT_ORDER_NO_MISSING("form_contract_order_no_missing", "订单编号为空"),

    /**
     * 订单名称缺失
     * */
    FORM_CONTRACT_ORDER_NAME_MISSING("form_contract_order_name_missing", "订单名称为空"),

    /**
     * 订单大项编号缺失
     * */
    FORM_CONTRACT_ORDER_BIG_ITEM_NO_MISSING("form_contract_order_big_item_no_missing", "大项编号为空"),

    /**
     * 订单金额缺失
     * */
    FORM_CONTRACT_ORDER_AMOUNT_MISSING("form_contract_order_amount_missing", "订单金额为空"),

    /**
     * 订单ID缺失
     * */
    FORM_CONTRACT_ORDER_ID_MISSING("form_contract_order_id_missing", "订单ID为空"),

    /**
     * 订单开始时间缺失
     * */
    FORM_CONTRACT_ORDER_START_TIME_MISSING("form_contract_order_start_time_missing", "开始时间为空"),

    /**
     * 订单miss号缺失
     * */
    FORM_CONTRACT_ORDER_MISS_NO_MISSING("form_contract_order_start_time_missing", "miss号为空"),

    /**
     * 订单税率缺失
     * */
    FORM_CONTRACT_ORDER_TAX_RATE_MISSING("form_contract_order_start_time_missing", "税率为空"),

    /**
     * 订单图片过多
     * */
    FORM_CONTRACT_ORDER_TOO_MANY_ORDER_IMAGE_ATTACHMENT("form_contract_order_too_many_order_image_attachment", "订单图片过多(不能超过5张)"),

    /**
     * 订单发票过多
     * */
    FORM_CONTRACT_ORDER_TOO_MANY_INVOICE_ATTACHMENT("form_contract_order_too_many_invoice_attachment", "订单发票文件过多(不能超过20张)"),

    /********************************************** contract order payment record ***************************************************************/

    /**
     * 订单回款总金额缺失
     * */
    FORM_CONTRACT_ORDER_PAYMENT_RECORD_AMOUNT_MISSING("form_contract_order_payment_record_amount_missing","总金额未填写"),

    /**
     * 订单回款日期缺失
     * */
    FORM_CONTRACT_ORDER_PAYMENT_RECORD_PAID_TIME_MISSING("form_contract_order_payment_record_paid_time_missing","日期未填写"),

    /********************************************** small item ***************************************************************/
    /**
     * 小项不存在
     */
    CHECK_SMALL_ITEM_NOT_FOUND("check_small_item_not_found", "小项不存在"),

    /**
     * 存在未结束的小项
     */
    CHECK_SMALL_ITEM_WITHOUT_END_STATUS("check_contract_order_without_end_status", "有小项尚未结束"),

    /**
     * 小项已完成
     */
    CHECK_SMALL_ITEM_ALREADY_FINISHED("check_small_item_already_finished", "小项已完成,不允许修改"),

    /**
     * 小项名称缺失
     * */
    FORM_SMALL_ITEM_NAME_MISSING("form_small_item_name_missing", "小项名称为空"),

    /**
     * 小项合作单位缺失
     * */
    FORM_SMALL_ITEM_COOPERATOR_MISSING("form_small_item_cooperator_missing", "合作单位为空"),

    /**
     * 小项开工日期缺失
     * */
    FORM_SMALL_ITEM_START_DATE_MISSING("form_small_item_start_date_missing", "开工日期为空"),

    /**
     * 小项完全支付状态期缺失
     * */
    FORM_SMALL_ITEM_TOTAL_PAID_MISSING("form_small_item_start_date_missing", "支付状态为空"),

    /**
     * 验收证书文件过多
     * */
    FORM_SMALL_ITEM_TOO_MANY_CHECK_AND_ACCEPT_ATTACHMENT("form_small_item_too_many_check_and_accept_attachment", "验收证书图片过多(不能超过5张)"),

    /**
     * 审核报告文件过多
     * */
    FORM_SMALL_ITEM_TOO_MANY_AUDIT_REPORT_ATTACHMENT("form_small_item_too_many_audit_report_attachment", "审核报告图片过多(不能超过5张)"),

    /**
     * 施工对扣款文件过多
     * */
    FORM_SMALL_ITEM_TOO_MANY_SECOND_PARTY_DEDUCTION_ATTACHMENT("form_small_item_too_many_second_party_deduction_attachment", "施工对扣款图片过多(不能超过5张)"),

    /**
     * 实际付款文件过多
     * */
    FORM_SMALL_ITEM_TOO_MANY_ACTUAL_PAYMENT_ATTACHMENT("form_small_item_too_many_actual_payment_attachment", "实际付款图片过多(不能超过20张)"),

    /**
     * 应付款文件过多
     * */
    FORM_SMALL_ITEM_TOO_MANY_BALANCE_ATTACHMENT("form_small_item_too_many_balance_attachment", "应付款款图片过多(不能超过5张)"),

    /********************************************** instrument ***************************************************************/
    /**
     * 工具不存在
     */
    CHECK_INSTRUMENT_NOT_FOUND("CHECK_INSTRUMENT_NOT_FOUND", "工具不存在"),

    /**
     * 工具名称缺失
     * */
    FORM_INSTRUMENT_NAME_MISSING("form_instrument_name_missing", "工具名称为空"),

    /**
     * 工具类型缺失
     * */
    FORM_INSTRUMENT_TYPE_MISSING("form_instrument_type_missing", "工具型号为空"),

    /********************************************** material ***************************************************************/

    /**
     * 材料不存在
     */
    CHECK_MATERIAL_NOT_FOUND("check_material_not_found", "材料不存在"),

    /**
     * 材料库存不足
     */
    CHECK_MATERIAL_REMAIN_NOT_ENOUGH("check_material_remain_not_enough", "材料库存不足"),

    /**
     * 材料名称缺失
     * */
    FORM_MATERIAL_NAME_MISSING("form_material_name_missing", "材料名称为空"),

    /**
     * 材料计量单位缺失
     * */
    FORM_MATERIAL_UNIT_MISSING("form_material_name_missing", "材料单位为空"),

    /**
     * 材料类型缺失
     * */
    FORM_MATERIAL_TYPE_MISSING("form_material_type_missing", "材料型号为空"),

    /********************************************** material log ***************************************************************/

    /**
     * 材料日志不存在
     */
    CHECK_MATERIAL_LOG_NOT_FOUND("check_material_log_not_found", "材料记录不存在"),

    /**
     * 材料日志材料ID缺失
     * */
    FORM_MATERIAL_LOG_MATERIAL_ID_MISSING("form_material_log_material_id_missing", "材料ID为空"),

    /**
     * 材料日志类型缺失
     * */
    FORM_MATERIAL_LOG_TYPE_MISSING("form_material_log_type_missing", "类型为空"),

    /**
     * 材料日志数量缺失
     * */
    FORM_MATERIAL_LOG_COUNT_MISSING("form_material_log_count_missing", "数量为空"),

    /**
     * 材料日志审核人缺失
     * */
    FORM_MATERIAL_LOG_AUDITOR_MISSING("form_material_log_auditor_missing", "材库管为空"),

    /**
     * 材料日志类型错误
     * */
    FORM_MATERIAL_LOG_TYPE_INVALID("form_material_log_type_invalid", "材料记录类型不合法"),

    /********************************************** login ********************************************************************/

    /**
     * 用户名不正确
     */
    LOGIN_USERNAME_ERROR("login_0001", "用户名不正确"),

    /**
     * 密码不正确
     */
    LOGIN_PASSWORD_ERROR("login_0002", "密码不正确"),

    /**
     * 用户名不能为空
     */
    LOGIN_USERNAME_EMPTY_ERROR("login_0003", "用户名为空"),

    /**
     * 密码不能为空
     */
    LOGIN_PASSWORD_EMPTY_ERROR("login_0004", "密码为空"),

    /**
     * 用户名或密码不正确
     */
    LOGIN_USERNAME_OR_PASSWORD_ERROR("login_0003", "用户名或密码不正确"),

    /**
     * 验证码不正确
     */
    LOGIN_CODE_ERROR("login_0003", "验证码不正确"),

    /********************************************** file ********************************************************************/

    /**
     * 文件不存在
     * */
    CHECK_FILE_NOT_FOUND("check_file_not_found", "文件不存在"),

    /**
     * 文件缺失
     * */
    FORM_FILE_MISSING("form_file_missing", "文件为空"),

    /**
     * 文件使用类型不正确
     * */
    FORM_FILE_USAGE_INVALID("form_file_usage_invalid", "文件为空"),


    /********************************************** server ********************************************************************/

    /**
     * 400
     */
    BAD_PARAM_EXCEPTION("400", "参数不合法"),

    /**
     * 404
     */
    SOURCE_NOT_FOUND_EXCEPTION("404", "资源不存在"),

    /**
     * 请求方法不支持
     * */
    REQUEST_METHOD_NOT_SUPPORT("405", "请求方法不支持"),

    /**
     * 服务器内部异常
     */
    SERVER_INTERNAL_EXCEPTION("500", "服务器内部异常");


    public final String code;

    public final String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCode getErrorCodeDescription(String code) {
        for (ErrorCode errorCode : values()) {
            if (errorCode.code.equals(code)) {
                return errorCode;
            }
        }
        return SERVER_INTERNAL_EXCEPTION;
    }

}
