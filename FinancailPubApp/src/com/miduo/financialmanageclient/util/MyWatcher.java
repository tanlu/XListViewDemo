package com.miduo.financialmanageclient.util;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MyWatcher implements TextWatcher {
	private EditText edittext;

	private String inputBeforeText; // 输入表情前EditText中的文本
	private String inputAfterText; // 输入表情后的文本
	private boolean containtEnojiFlag = false;
	private int beforeStart;

	public MyWatcher(EditText edittext) {
		this.edittext = edittext;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int before, int count) {
		inputBeforeText = s.toString();
		if(!containtEnojiFlag){
			beforeStart = edittext.getSelectionStart();
		}
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		inputAfterText = s.toString();
		if(containsEmoji(inputAfterText)){
			containtEnojiFlag = true;
			this.edittext.setText(inputBeforeText);
			CharSequence text = this.edittext.getText();
			if (text instanceof Spannable) {
				Spannable spanText = (Spannable) text;
				Selection.setSelection(spanText, beforeStart);
			}
		}else{
			containtEnojiFlag = false;
		}
	}

	@Override
	public void afterTextChanged(Editable editable) {

	}

	/**
	 * 检测是否有emoji表情
	 * 
	 * @param source
	 * @return
	 */
	private boolean containsEmoji(String source) {
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否是Emoji
	 * 
	 * @param codePoint
	 *            比较的单个字符
	 * @return
	 */
	private boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}
}

