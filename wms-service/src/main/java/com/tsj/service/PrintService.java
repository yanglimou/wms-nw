package com.tsj.service;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.tsj.service.common.MyService;

import java.util.List;


public class PrintService extends MyService {

    public static final Log logger = Log.getLog(PrintService.class);

    public Page<Record> getPrintPage(int pageNumber, int pageSize) {
        String select = "select a.caseNbr,a.epc,a.userId,a.comGoodsId,a.lotNo,a.expireDate,a.printFlag,a.shelfCode,a.hvFlag,b.name,b.spec,b.unit,c.`name` manufacturerName ";
        String sqlExceptSelect = "  from print a LEFT JOIN base_goods b on a.comGoodsId=b.id left JOIN base_manufacturer c on b.manufacturerId=c.id order by a.printFlag,a.caseNbr ";
        return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

}