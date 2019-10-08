package com.hb.remote.constant.enumutil;

import org.apache.commons.lang3.StringUtils;

/**
 * ========== 身份证实名认证响应枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.constant.enumutil.SMSResEnum.java, v1.0
 * @date 2019年08月12日 19时37分
 */
public enum IdCardAuthResEnum {

    success("0", "验证通过", "验证通过", "验证通过"),
    failed("400", "参数错误", "参数错误", "参数错误"),
    not_exist("20010", "身份证号为空或非法", "身份证号为空或非法", "身份证号为空或非法"),
    error("20310", "姓名为空或非法", "姓名为空或非法", "姓名为空或非法");

    private String code;
    private String message;
    private String desc;
    private String hint;

    IdCardAuthResEnum(String code, String message, String desc, String hint) {
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
     * @return IdCardAuthResEnum
     */
    public static IdCardAuthResEnum match(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (IdCardAuthResEnum idCardAuthResEnum : IdCardAuthResEnum.values()) {
            if (StringUtils.equals(idCardAuthResEnum.code, code)) {
                return idCardAuthResEnum;
            }
        }
        return null;
    }

}
