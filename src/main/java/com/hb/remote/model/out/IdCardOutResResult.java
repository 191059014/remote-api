package com.hb.remote.model.out;

import java.io.Serializable;

/**
 * ========== 身份证实名认证-验证结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.model.out.IdCardOutResResult.java, v1.0
 * @date 2019年08月12日 23时17分
 */
public class IdCardOutResResult implements Serializable {

    private static final long serialVersionUID = -1673679606799256580L;

    /*真实姓名*/
    private String name;
    /*身份证号码*/
    private String idcard;
    private String res;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "IdCardOutResResult{" +
                "name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", res='" + res + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
