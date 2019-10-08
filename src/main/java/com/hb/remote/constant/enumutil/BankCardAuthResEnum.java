package com.hb.remote.constant.enumutil;

import org.apache.commons.lang3.StringUtils;

/**
 * ========== 银行卡实名认证响应枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.constant.enumutil.SMSResEnum.java, v1.0
 * @date 2019年08月12日 19时37分
 */
public enum BankCardAuthResEnum {

    success("0", "验证通过", "验证通过", "验证通过"),
    error("-1", "验证失败", "验证失败", "验证失败"),
    fail_1("20502", "银行卡为空或者卡号错误", "银行卡为空或者卡号错误", "银行卡为空或者卡号错误"),
    fail_2("10016", "姓名为空或者包含特殊字符", "姓名为空或者包含特殊字符", "姓名为空或者包含特殊字符"),
    fail_3("20010", "身份证号为空或者不符合校验规范", "身份证号为空或者不符合校验规范", "身份证号为空或者不符合校验规范");

    private String code;
    private String message;
    private String desc;
    private String hint;

    BankCardAuthResEnum(String code, String message, String desc, String hint) {
        this.code = code;
        this.message = message;
        this.desc = desc;
        this.hint = hint;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDesc() {
        return desc;
    }

    public String getHint() {
        return hint;
    }

    /**
     * ########## 根据code匹配 ##########
     *
     * @param code code
     * @return BankCardAuthResEnum
     */
    public static BankCardAuthResEnum match(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (BankCardAuthResEnum bankCardAuthResEnum : BankCardAuthResEnum.values()) {
            if (StringUtils.equals(bankCardAuthResEnum.code, code)) {
                return bankCardAuthResEnum;
            }
        }
        return null;
    }

}
