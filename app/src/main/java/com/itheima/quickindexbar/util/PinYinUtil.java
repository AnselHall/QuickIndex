package com.itheima.quickindexbar.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {

	/**
	 * 获取指定字符串的拼音
	 * @param strs
	 * @return
	 */
	public static String getPinyin(String strs) {
		// 黑马 -> HEIMA
		//    黑  马 -> HEIMA
		// J黑K%#F12马 -> HEIMA
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		
		StringBuilder sb = new StringBuilder();
		
		char[] charArray = strs.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			
			// 跳过空格
			if(Character.isWhitespace(c)){
				continue;
			}
			
			// 直接添加特殊字符\字母\数字
			if(c >= -128 && c < 127){
				sb.append(c);
			}else {
				// 可能是汉字
				try {
					// 将一个汉字转换成拼音 黑 -> HEI, 单 -> DAN，SHAN
					String str = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
					sb.append(str);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}

}
