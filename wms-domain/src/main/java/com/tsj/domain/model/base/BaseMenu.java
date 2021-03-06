package com.tsj.domain.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseMenu<M extends BaseMenu<M>> extends Model<M> implements IBean {

	public M setId(Integer id) {
		set("id", id);
		return (M)this;
	}

	public Integer getId() {
		return getInt("id");
	}

	public M setPid(Integer pid) {
		set("pid", pid);
		return (M)this;
	}

	public Integer getPid() {
		return getInt("pid");
	}

	public M setTitle(String title) {
		set("title", title);
		return (M)this;
	}

	public String getTitle() {
		return getStr("title");
	}

	public M setCode(String code) {
		set("code", code);
		return (M)this;
	}

	public String getCode() {
		return getStr("code");
	}

	public M setIcon(String icon) {
		set("icon", icon);
		return (M)this;
	}

	public String getIcon() {
		return getStr("icon");
	}

	public M setHref(String href) {
		set("href", href);
		return (M)this;
	}

	public String getHref() {
		return getStr("href");
	}

	public M setSpread(Integer spread) {
		set("spread", spread);
		return (M)this;
	}

	public Integer getSpread() {
		return getInt("spread");
	}

	public M setMenuType(String menuType) {
		set("menuType", menuType);
		return (M)this;
	}

	public String getMenuType() {
		return getStr("menuType");
	}

	public M setType(String type) {
		set("type", type);
		return (M)this;
	}

	public String getType() {
		return getStr("type");
	}

	public M setState(Integer state) {
		set("state", state);
		return (M)this;
	}

	public Integer getState() {
		return getInt("state");
	}

	public M setCaption(String caption) {
		set("caption", caption);
		return (M)this;
	}

	public String getCaption() {
		return getStr("caption");
	}

	public M setSort(Integer sort) {
		set("sort", sort);
		return (M)this;
	}

	public Integer getSort() {
		return getInt("sort");
	}

}
