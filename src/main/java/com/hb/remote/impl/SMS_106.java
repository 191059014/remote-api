package com.hb.remote.impl;

import com.alibaba.fastjson.JSON;
import com.hb.remote.constant.enumutil.SMSResEnum;
import com.hb.remote.model.SMSSendResult;
import com.hb.remote.model.out.SMS106OutRes;
import com.hb.remote.service.ISMS;
import com.hb.remote.tool.AlarmTools;
import com.hb.remote.util.HttpUtils;
import com.hb.unic.base.BaseContext;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.LogHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ========== 106短信 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.impl.SMS_106.java, v1.0
 * @date 2019年08月12日 19时59分
 */
@Service("sms_106")
public class SMS_106 implements ISMS {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SMS_106.class);

    @Autowired
    public AlarmTools alarmTools;

    @Override
    public SMSSendResult send(String mobile, Integer verifyCode, String smsTemplate, Long expireTime, Object... param) {
        LOGGER.info("SMS_106#send 入参:{}={}={}={}={}", mobile, verifyCode, smsTemplate, expireTime, param);
        String host = BaseContext.getValue("sms.host");
        String path = BaseContext.getValue("sms.path");
        String appcode = BaseContext.getValue("sms.appcode");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        /**
         * 短信内容
         */
        String content = smsTemplate;
        content = content.replace("{verifyCode}", verifyCode + "");
        content = content.replace("{expireTime}", expireTime + "");
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                String symbol = "{" + i + "}";
                if (!smsTemplate.contains(symbol)) {
                    return new SMSSendResult(mobile, verifyCode, SMSResEnum.err_template);
                }
                content = content.replace(symbol, String.valueOf(param[i]));
            }
        }
        LOGGER.info("SMS_106 content:{}", content);
        Map<String, String> querys = new HashMap<>();
        querys.put("content", content);
        querys.put("mobile", mobile);
        Map<String, String> bodys = new HashMap<>();
        try {
            /**
             * http调用短信服务
             */
            HttpResponse response = HttpUtils.doPost(host, path, "POST", headers, querys, bodys);
            String bodyString = EntityUtils.toString(response.getEntity());
            LOGGER.info("SMS_106 result:{}", bodyString);
            SMS106OutRes res = JSON.parseObject(bodyString, SMS106OutRes.class);
            if (!StringUtils.equals(SMSResEnum.success.getCode(), res.getReturnStatus())) {
                return new SMSSendResult(mobile, verifyCode, SMSResEnum.error);
            }
            return new SMSSendResult(mobile, verifyCode, SMSResEnum.success);
        } catch (Exception e) {
            LOGGER.error("SMS_106 Exception:{}", LogHelper.getStackTrace(e));
            alarmTools.alert("SMS_106", "阿里云服务", "短信发送", "短信发送异常：" + e.getMessage());
            return new SMSSendResult(mobile, verifyCode, SMSResEnum.error);
        }
    }

}
