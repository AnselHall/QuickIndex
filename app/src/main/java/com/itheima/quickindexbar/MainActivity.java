package com.itheima.quickindexbar;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.quickindexbar.adapter.HaoHanAdapter;
import com.itheima.quickindexbar.domain.HaoHan;
import com.itheima.quickindexbar.ui.QuickIndexBar;
import com.itheima.quickindexbar.ui.QuickIndexBar.OnLetterUpdateListener;
import com.itheima.quickindexbar.util.Cheeses;

public class MainActivity extends Activity {

	private ListView lv;
	private ArrayList<HaoHan> persons;
	private TextView tv_index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		tv_index = (TextView) findViewById(R.id.tv_index);
		
		lv = (ListView) findViewById(R.id.lv);
		
		QuickIndexBar qib = (QuickIndexBar) findViewById(R.id.qib);
		qib.setOnLetterUpdateListener(new OnLetterUpdateListener() {
			
			@Override
			public void onLetterUpdate(String letter) {
				// 显示用户选中的字母
//				Utils.showToast(getApplicationContext(), letter);
				showLetter(letter);
				
				for (int i = 0; i < persons.size(); i++) {
					String indexStr = persons.get(i).getPinyin().charAt(0) + "";
					if(TextUtils.equals(indexStr, letter)){
						// 匹配成功
						lv.setSelection(i);
						break;
					}
				}
				
			}
		});
		
		persons = new ArrayList<HaoHan>();
		
		fillAndSortData(persons);
		
		lv.setAdapter(new HaoHanAdapter(persons));
	}
	
	private Handler mHandler = new Handler();

	protected void showLetter(String letter) {
		tv_index.setVisibility(View.VISIBLE);
		tv_index.setText(letter);
		
		mHandler.removeCallbacksAndMessages(null);
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				tv_index.setVisibility(View.GONE);
			}
			
		}, 2000);
		
	}

	private void fillAndSortData(ArrayList<HaoHan> persons) {
		// 填充数据
		for (int i = 0; i < Cheeses.NAMES.length; i++) {
			String str = Cheeses.NAMES[i];
			HaoHan haoHan = new HaoHan(str);
			persons.add(haoHan);
		}
		
		// 排序
		Collections.sort(persons);
	}

}
