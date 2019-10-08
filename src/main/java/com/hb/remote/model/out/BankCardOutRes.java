package com.hb.remote.model.out;

import java.io.Serializable;

/**
 * ========== 银行卡实名认证第三方返回结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.model.out.BankCardOutRes.java, v1.0
 * @date 2019年08月12日 21时57分
 */
public class BankCardOutRes implements Serializable {

    private static final long serialVersionUID = -6969233965703399742L;

    /*状态码:01 验证通过；02 验证不通过；详见状态码说明*/
    private String code;
    /*信息*/
    private String message;

    private BankCardOutData data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BankCardOutData getData() {
        return data;
    }

    public void setData(BankCardOutData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BankCardOutRes{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + String.valueOf(data) +
                '}';
    }
}
