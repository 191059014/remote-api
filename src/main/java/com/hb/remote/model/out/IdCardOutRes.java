package com.hb.remote.model.out;

import java.io.Serializable;

/**
 * ========== 身份证实名认证第三方响应结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.model.out.IdCardOutRes.java, v1.0
 * @date 2019年08月12日 22时18分
 */
public class IdCardOutRes implements Serializable {

    private static final long serialVersionUID = -7762315339894257201L;

    private String code;
    private String message;
    private IdCardOutResResult result;

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

    public IdCardOutResResult getResult() {
        return result;
    }

    public void setResult(IdCardOutResResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IdCardOutRes{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + String.valueOf(result) +
                '}';
    }
}
