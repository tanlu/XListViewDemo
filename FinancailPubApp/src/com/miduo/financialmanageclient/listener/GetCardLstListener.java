package com.miduo.financialmanageclient.listener;

import java.util.List;

import com.miduo.financialmanageclient.bean.BankCardInfo;

public interface GetCardLstListener {
	void refresh(List<BankCardInfo> cardLst);
}
