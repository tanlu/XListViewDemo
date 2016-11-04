package com.miduo.financialmanageclient.bean;

import java.io.Serializable;

import com.miduo.financialmanageclient.listener.DialogEventListener;

public class DialogBean implements Serializable {
	private String title;
	private String title2;
	private String content;
	private String submit;
	private String cancel;
	private DialogEventListener dialogEvent;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public DialogEventListener getDialogEvent() {
		return dialogEvent;
	}

	public void setDialogEvent(DialogEventListener dialogEvent) {
		this.dialogEvent = dialogEvent;
	}

}
