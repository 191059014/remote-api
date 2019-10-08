package com.hb.remote.impl;

import com.alibaba.fastjson.JSON;
import com.hb.remote.constant.enumutil.BankCardAuthResEnum;
import com.hb.remote.constant.enumutil.IdCardAuthResEnum;
import com.hb.remote.model.BankCardAuthResult;
import com.hb.remote.model.IdCardAuthResult;
import com.hb.remote.model.out.BankCardOutRes;
import com.hb.remote.model.out.IdCardOutRes;
import com.hb.remote.service.IRealNameAuth;
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
 * ========== 实名认证实现类 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.impl.RealNameAuthImpl.java, v1.0
 * @date 2019年08月12日 22时32分
 */
@Service("realNameAuth")
public class RealNameAuthImpl implements IRealNameAuth {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RealNameAuthImpl.class);

    @Autowired
    public AlarmTools alarmTools;

    @Override
    public BankCardAuthResult bankCardAuth(String accountNo, String idCard, String name) {
        LOGGER.info("RealNameAuthImpl#bankCardAuth 入参:{}={}={}", accountNo, idCard, name);
        String bankcard_host = BaseContext.getValue("bankcard.host");
        String bankcard_path = BaseContext.getValue("bankcard.path");
        String bankcard_appcode = BaseContext.getValue("bankcard.appcode");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + bankcard_appcode);

        Map<String, String> querys = new HashMap<>();
        querys.put("bankcard", accountNo);
        querys.put("idcard", idCard);
        querys.put("name", name);
        try {
            /**
             * http调用银行卡实名认证
             */
            HttpResponse response = HttpUtils.doGet(bankcard_host, bankcard_path, "GET", headers, querys);
            String bodyString = EntityUtils.toString(response.getEntity());
            LOGGER.info("RealNameAuthImpl#bankCardAuth result:{}", bodyString);
            BankCardOutRes res = JSON.parseObject(bodyString, BankCardOutRes.class);
            if (!StringUtils.equals(BankCardAuthResEnum.success.getCode(), res.getCode())) {
                return new BankCardAuthResult(BankCardAuthResEnum.match(res.getCode()), res);
            }
            if (res.getData() == null || !"1".equals(res.getData().getResult())) {
                return new BankCardAuthResult(BankCardAuthResEnum.error, res);
            }
            return new BankCardAuthResult(BankCardAuthResEnum.success, res);
        } catch (Exception e) {
            LOGGER.error("RealNameAuthImpl#bankCardAuth Exception:{}", LogHelper.getStackTrace(e));
            alarmTools.alert("RealNameAuthImpl", "阿里云服务", "银行卡实名认证", "异常：" + e.getMessage());
            return new BankCardAuthResult(BankCardAuthResEnum.error, null);
        }
    }

    @Override
    public IdCardAuthResult idCardAuth(String cardNo, String realName) {
        LOGGER.info("RealNameAuthImpl#idCardAuth 入参:{}={}", cardNo, realName);
        String idcard_host = BaseContext.getValue("idcard.host");
        String idcard_path = BaseContext.getValue("idcard.path");
        String idcard_appcode = BaseContext.getValue("idcard.appcode");
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + idcard_appcode);

        Map<String, String> querys = new HashMap<>();
        querys.put("idcard", cardNo);
        querys.put("name", realName);
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            /**
             * http调用身份证实名认证
             */
            HttpResponse response = HttpUtils.doPost(idcard_host, idcard_path, "POST", headers, querys, bodys);
            String bodyString = EntityUtils.toString(response.getEntity());
            LOGGER.info("RealNameAuthImpl#idCardAuth result:{}", bodyString);
            IdCardOutRes res = JSON.parseObject(bodyString, IdCardOutRes.class);
            if (!StringUtils.equals(IdCardAuthResEnum.success.getCode(), res.getCode())) {
                return new IdCardAuthResult(IdCardAuthResEnum.match(res.getCode()), res);
            }
            if (res.getResult() == null || !"1".equals(res.getResult().getRes())) {
                return new IdCardAuthResult(IdCardAuthResEnum.failed, res);
            }
            return new IdCardAuthResult(IdCardAuthResEnum.success, res);
        } catch (Exception e) {
            LOGGER.error("RealNameAuthImpl#idCardAuth Exception:{}", LogHelper.getStackTrace(e));
            alarmTools.alert("RealNameAuthImpl", "阿里云服务", "身份证实名认证", "异常：" + e.getMessage());
            return new IdCardAuthResult(IdCardAuthResEnum.error, null);
        }
    }

}
