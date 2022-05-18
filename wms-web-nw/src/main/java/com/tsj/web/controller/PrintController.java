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

public class PrintController extends MyController {

    @Inject
    private PrintService printService;

    @Before(GET.class)
    @NotNull({"page", "limit"})
    @OperateLog("查询打印分页")
    public void getPrintList(int page, int limit) {
        Page<Record> pageData = printService.getPrintPage(page, limit);
        renderJson(R.ok().putData(pageData.getList()).put("count", pageData.getTotalRow()));
    }

    @Before(GET.class)
    @OperateLog("查询在线打印机")
    public void getPrinter() {
        renderJson(R.ok().putData(MyWebSocket.getPrinter()));
    }

    @Before(POST.class)
    @OperateLog("执行打印操作")
    public void doPrint() {
        String rawData = getRawData();
        JSONObject requestObject = JSON.parseObject(rawData);
        //处理高值
        JSONObject high = requestObject.getJSONObject("high");
        handle(high);
        //处理低值
        JSONObject low = requestObject.getJSONObject("low");
        handle(low);
        renderJson(R.ok().putData("success"));
    }

    private void handle(JSONObject high) {
        String id = high.getString("id");
        JSONArray printRequests = new JSONArray();
        high.getJSONArray("data").stream().map(o -> (JSONObject) o).forEach(jsonObject -> {
            JSONObject printRequest = new JSONObject();
            printRequest.put("name", jsonObject.getString("name"));
            printRequest.put("spec", "规格：" + jsonObject.getString("spec"));
            printRequest.put("manufacturerName", "厂家：" + jsonObject.getString("manufacturerName"));
            printRequest.put("lotNo", "批号：" + jsonObject.getString("lotNo"));
            printRequest.put("expireDate", "有效期：" + jsonObject.getString("expireDate").substring(0, 10));
            printRequest.put("shelfName", jsonObject.getString("shelfName"));
            printRequest.put("comGoodsId", jsonObject.getString("comGoodsId"));
            printRequest.put("caseNbr", jsonObject.getString("caseNbr"));
            printRequest.put("rfid", jsonObject.getString("epc"));
            printRequest.put("unit", "1[" + jsonObject.getString("unit") + "]");
            printRequests.add(printRequest);
            Db.update("update print set printFlag=1 , userId=? where caseNbr=?", getLoginUserId(), jsonObject.getString("caseNbr"));
        });
        System.out.println(printRequests.toJSONString());
        MyWebSocket.send(id, printRequests.toJSONString());
    }
}
