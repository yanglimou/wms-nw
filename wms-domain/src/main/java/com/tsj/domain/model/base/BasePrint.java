package com.tsj.domain.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BasePrint<M extends BasePrint<M>> extends Model<M> implements IBean {

	public M setCaseNbr(String caseNbr) {
		set("caseNbr", caseNbr);
		return (M)this;
	}
	
	public String getCaseNbr() {
		return getStr("caseNbr");
	}
	
	public M setOrderCode(String orderCode) {
		set("orderCode", orderCode);
		return (M)this;
	}
	
	public String getOrderCode() {
		return getStr("orderCode");
	}
	
	public M setComGoodsId(String comGoodsId) {
		set("comGoodsId", comGoodsId);
		return (M)this;
	}
	
	public String getComGoodsId() {
		return getStr("comGoodsId");
	}
	
	public M setLotNo(String lotNo) {
		set("lotNo", lotNo);
		return (M)this;
	}
	
	public String getLotNo() {
		return getStr("lotNo");
	}
	
	public M setExpireDate(String expireDate) {
		set("expireDate", expireDate);
		return (M)this;
	}
	
	public String getExpireDate() {
		return getStr("expireDate");
	}
	
	public M setDepotId(String depotId) {
		set("depotId", depotId);
		return (M)this;
	}
	
	public String getDepotId() {
		return getStr("depotId");
	}
	
	public M setCreateDate(String createDate) {
		set("createDate", createDate);
		return (M)this;
	}
	
	public String getCreateDate() {
		return getStr("createDate");
	}
	
	/**
	 * 0未打印1已打印
	 */
	public M setPrintFlag(String printFlag) {
		set("printFlag", printFlag);
		return (M)this;
	}
	
	/**
	 * 0未打印1已打印
	 */
	public String getPrintFlag() {
		return getStr("printFlag");
	}
	
	public M setShelfName(String shelfName) {
		set("shelfName", shelfName);
		return (M)this;
	}
	
	public String getShelfName() {
		return getStr("shelfName");
	}
	
	public M setHvFlag(String hvFlag) {
		set("hvFlag", hvFlag);
		return (M)this;
	}
	
	public String getHvFlag() {
		return getStr("hvFlag");
	}
	
	public M setShelfCode(String shelfCode) {
		set("shelfCode", shelfCode);
		return (M)this;
	}
	
	public String getShelfCode() {
		return getStr("shelfCode");
	}
	
	public M setEpc(String epc) {
		set("epc", epc);
		return (M)this;
	}
	
	public String getEpc() {
		return getStr("epc");
	}
	
	public M setUserId(String userId) {
		set("userId", userId);
		return (M)this;
	}
	
	public String getUserId() {
		return getStr("userId");
	}

	public M setInsNo(String insNo) {
		set("insNo", insNo);
		return (M)this;
	}

	public String getInsNo() {
		return getStr("insNo");
	}
	
}

