package com.tsj.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.ext.interceptor.GET;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.tsj.common.annotation.NotNull;
import com.tsj.common.annotation.OperateLog;
import com.tsj.common.utils.R;
import com.tsj.service.PrintService;
import com.tsj.web.common.MyController;
import com.tsj.web.websocket.MyWebSocket;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PrintController extends MyController {

    @Inject
    private PrintService printService;

    @Before(GET.class)
    @NotNull({"page", "limit"})
    @OperateLog("查询打印分页")
    public void getPrintList(int page, int limit,String caseNbr,String insNo) {
        Page<Record> pageData = printService.getPrintPage(page, limit,caseNbr,insNo);
        renderJson(R.ok().putData(pageData.getList()).put("count", pageData.getTotalRow()));
    }

    @Before(GET.class)
    @NotNull({"page", "limit"})
    @OperateLog("查询打印分页2")
    public void getPrintList2(int page, int limit) {
        Page<Record> pageData = printService.getPrintPage2(page, limit);
        renderJson(R.ok().putData(pageData.getList()).put("count", pageData.getTotalRow()));
    }

    @Before(GET.class)
    @OperateLog("查询在线打印机")
    public void getPrinter() {
        renderJson(R.ok().putData(MyWebSocket.getPrinter()));
    }

    @Before(POST.class)
    @OperateLog("执行打印操作")
    public void doPrint2(String insNo, String high, String low) {
        logger.info("insNo:{},high:{},low:{}", insNo, high, low);
        List<Record> recordList = printService.findByInsNo(insNo);
        JSONArray highRequests = new JSONArray();
        JSONArray lowRequests = new JSONArray();
        recordList.forEach(record -> {
            JSONObject printRequest = new JSONObject();
            printRequest.put("name", record.getStr("name"));
            printRequest.put("spec", record.getStr("spec"));
            printRequest.put("manufacturerName", record.getStr("manufacturerName"));
            printRequest.put("lotNo", record.getStr("lotNo"));
            printRequest.put("expireDate", record.getStr("expireDate").substring(0, 10));
            printRequest.put("shelfCode", record.getStr("shelfCode"));
            printRequest.put("comGoodsId", record.getStr("comGoodsId"));
            printRequest.put("caseNbr", record.getStr("caseNbr"));
            printRequest.put("rfid", record.getStr("epc"));
            printRequest.put("unit", record.getStr("unit"));
            printRequest.put("highFlag", record.getStr("hvFlag").equals("是") ? "H" : "L");
            if (record.getStr("hvFlag").equals("是")) {
                highRequests.add(printRequest);
            } else {
                lowRequests.add(printRequest);
            }
        });
        Db.update("update print set printFlag=1 , userId=? where insNo=?", getLoginUserId(), insNo);
        if (StringUtils.isNotEmpty(low)) {
            MyWebSocket.send(low, lowRequests.toJSONString());
        }
        if (StringUtils.isNotEmpty(high)) {
            MyWebSocket.send(high, highRequests.toJSONString());
        }
        renderJson(R.ok().putData("success"));
    }

    @Before(POST.class)
    @OperateLog("执行打印操作")
    public void doPrint() {
        String rawData = getRawData();
        JSONObject requestObject = JSON.parseObject(rawData);
        //处理高值
        JSONObject high = requestObject.getJSONObject("high");
        handle(high, true);
        //处理低值
        JSONObject low = requestObject.getJSONObject("low");
        handle(low, false);
        renderJson(R.ok().putData("success"));
    }

    private void handle(JSONObject high, boolean isHighFlag) {
        String id = high.getString("id");
        JSONArray printRequests = new JSONArray();
        high.getJSONArray("data").stream().map(o -> (JSONObject) o).forEach(jsonObject -> {
            JSONObject printRequest = new JSONObject();
            printRequest.put("name", jsonObject.getString("name"));
            printRequest.put("spec", jsonObject.getString("spec"));
            printRequest.put("manufacturerName", jsonObject.getString("manufacturerName"));
            printRequest.put("lotNo", jsonObject.getString("lotNo"));
            printRequest.put("expireDate", jsonObject.getString("expireDate").substring(0, 10));
            printRequest.put("shelfCode", jsonObject.getString("shelfCode"));
            printRequest.put("comGoodsId", jsonObject.getString("comGoodsId"));
            printRequest.put("caseNbr", jsonObject.getString("caseNbr"));
            printRequest.put("rfid", jsonObject.getString("epc"));
            printRequest.put("unit", jsonObject.getString("unit"));
            printRequest.put("highFlag", isHighFlag ? "H" : "L");
            printRequests.add(printRequest);
            Db.update("update print set printFlag=1 , userId=? where caseNbr=?", getLoginUserId(), jsonObject.getString("caseNbr"));
        });
        System.out.println(printRequests.toJSONString());
        MyWebSocket.send(id, printRequests.toJSONString());
    }
}
