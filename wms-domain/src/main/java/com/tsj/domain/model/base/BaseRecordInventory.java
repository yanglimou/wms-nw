package com.tsj.domain.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseRecordInventory<M extends BaseRecordInventory<M>> extends Model<M> implements IBean {

	public M setId(String id) {
		set("id", id);
		return (M)this;
	}
	
	public String getId() {
		return getStr("id");
	}

	public M setCabinetId(String cabinetId) {
		set("cabinetId", cabinetId);
		return (M)this;
	}
	
	public String getCabinetId() {
		return getStr("cabinetId");
	}

	public M setGoodsId(String goodsId) {
		set("goodsId", goodsId);
		return (M)this;
	}
	
	public String getGoodsId() {
		return getStr("goodsId");
	}

	public M setSpdCode(String spdCode) {
		set("spdCode", spdCode);
		return (M)this;
	}
	
	public String getSpdCode() {
		return getStr("spdCode");
	}

	public M setState(String state) {
		set("state", state);
		return (M)this;
	}
	
	public String getState() {
		return getStr("state");
	}

	public M setCreateUserId(String createUserId) {
		set("createUserId", createUserId);
		return (M)this;
	}
	
	public String getCreateUserId() {
		return getStr("createUserId");
	}

	public M setCreateDate(String createDate) {
		set("createDate", createDate);
		return (M)this;
	}
	
	public String getCreateDate() {
		return getStr("createDate");
	}

	public M setUpload(String upload) {
		set("upload", upload);
		return (M)this;
	}
	
	public String getUpload() {
		return getStr("upload");
	}

	public M setInventoryDifferenceId(String inventoryDifferenceId) {
		set("inventoryDifferenceId", inventoryDifferenceId);
		return (M)this;
	}
	
	public String getInventoryDifferenceId() {
		return getStr("inventoryDifferenceId");
	}

}
