package com.itheima.quickindexbar.adapter;

import java.util.ArrayList;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.quickindexbar.R;
import com.itheima.quickindexbar.domain.HaoHan;

public class HaoHanAdapter extends BaseAdapter {

	private final ArrayList<HaoHan> persons;

	public HaoHanAdapter(ArrayList<HaoHan> persons) {
		this.persons = persons;
	}

	@Override
	public int getCount() {
		return persons.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if(convertView == null){
			view = View.inflate(parent.getContext(), R.layout.item_list_haohan, null);
		}else {
			view = convertView;
		}
		
		TextView tv_index = (TextView) view.findViewById(R.id.tv_index);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);

		HaoHan haoHan = persons.get(position);
		String currentIndexStr = haoHan.getPinyin().charAt(0) + "";
		
		String indexStr = null;
		
		// 经过判断进行indexStr赋值
		
		if(position == 0){
			// 1. 是首位
			indexStr = currentIndexStr;
		}else {
			// 2. 当前首字母和上一个不一致
			String lastIndexStr = persons.get(position - 1).getPinyin().charAt(0) + "";
			if(!TextUtils.equals(lastIndexStr, currentIndexStr)){
				// 不一致， 把当前字母赋给indexStr
				indexStr = currentIndexStr;
			}
		}
		
		tv_index.setVisibility(indexStr != null ? View.VISIBLE : View.GONE);
		tv_index.setText(currentIndexStr);
		tv_name.setText(haoHan.getName());
		
		return view;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}



}
