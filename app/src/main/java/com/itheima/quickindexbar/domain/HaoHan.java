package com.itheima.quickindexbar.domain;

import com.itheima.quickindexbar.util.PinYinUtil;

public class HaoHan implements Comparable<HaoHan>{

	private String name;
	private String pinyin;

	public HaoHan(String name) {
		this.name = name;
		this.pinyin = PinYinUtil.getPinyin(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public int compareTo(HaoHan another) {
		return pinyin.compareTo(another.pinyin);
	}
	
}
