package com.hb.remote.service;

import com.hb.remote.model.StockIndexModel;
import com.hb.remote.model.StockModel;

import java.util.List;
import java.util.Set;

/**
 * ========== 股票相关service接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.service.IStockService.java, v1.0
 * @date 2019年05月31日 11时05分
 */
public interface IStockService {

    /**
     * ###### 根据股票代码查询股票信息 ######
     *
     * @param stockCode 股票代码
     * @return 股票信息
     */
    StockModel queryStock(String stockCode);

    /**
     * ###### 根据股票代码集合查询股票信息 ######
     *
     * @param stockCodeSet 股票代码集合
     * @return 股票信息
     */
    List<StockModel> queryStockList(Set<String> stockCodeSet);

    /**
     * ###### 根据指数代码查询指数信息 ######
     *
     * @param indexCodeSet 指数代码集合
     * @return 指数信息
     */
    List<StockIndexModel> queryStockIndexList(Set<String> indexCodeSet);

}
