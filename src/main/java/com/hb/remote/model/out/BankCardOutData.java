package com.hb.remote.model.out;

import java.io.Serializable;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.model.out.BankCardOutData.java, v1.0
 * @date 2019年10月08日 18时39分
 */
public class BankCardOutData implements Serializable {
    private static final long serialVersionUID = -3175841222552389123L;
    private String bankcard;
    private String name;
    private String idcard;
    private String result;
    private String description;

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BankCardOutData{" +
                "bankcard='" + bankcard + '\'' +
                ", name='" + name + '\'' +
                ", idcard='" + idcard + '\'' +
                ", result='" + result + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
