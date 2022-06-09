package com.tsj.service;

import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.tsj.service.common.MyService;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


public class PrintService extends MyService {

    public static final Log logger = Log.getLog(PrintService.class);

    public Page<Record> getPrintPage(int pageNumber, int pageSize, String caseNbr, String insNo) {
        String condition="";
        if(StringUtils.isNotEmpty(caseNbr)){
            condition+=" and a.caseNbr='"+caseNbr+"'";
        }
        if(StringUtils.isNotEmpty(insNo)){
            condition+=" and a.insNo='"+insNo+"'";
        }
        String select = "select a.insNo,a.caseNbr,a.epc,a.userId,a.comGoodsId,a.lotNo,substring(a.expireDate,1,10) expireDate,a.printFlag,a.shelfCode,a.hvFlag,b.name,b.spec,b.unit,c.`name` manufacturerName ";
        String sqlExceptSelect = "  from print a LEFT JOIN base_goods b on a.comGoodsId=b.id left JOIN base_manufacturer c on b.manufacturerId=c.id where 1=1 "+condition+" order by a.printFlag,a.caseNbr ";
        return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }

    public List<Record> findByInsNo(String insNo) {
        return Db.find("select a.caseNbr,a.epc,a.comGoodsId,a.lotNo,substring(a.expireDate,1,10) expireDate,a.shelfCode,a.hvFlag,b.name,b.spec,b.unit,c.`name` manufacturerName from print a LEFT JOIN base_goods b on a.comGoodsId=b.id left JOIN base_manufacturer c on b.manufacturerId=c.id where a.insNo=?", insNo);
    }

    public Page<Record> getPrintPage2(int pageNumber, int pageSize) {
        String select = "select a.* ";
        String sqlExceptSelect = " from (select insNo,count(insNo) num,sum(hvFlag='是') highNum,sum(hvFlag='否') lowNum,sum(printFlag=1) printNum,sum(printFlag=0) unPrintNum from print group by insNo ORDER BY insNo desc) as a";
        return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
    }
}