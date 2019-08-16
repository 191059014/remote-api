package com.hb.remote.common;

/**
 * ========== 告警请求参数实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.common.AlarmContent.java, v1.0
 * @date 2019年07月15日 20时28分
 */
public class AlarmContent {
    /**
     * 文本内容
     */
    private String content;

    public AlarmContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
