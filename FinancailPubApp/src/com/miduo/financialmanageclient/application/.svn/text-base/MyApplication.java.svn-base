package com.miduo.financialmanageclient.application;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

import com.miduo.financialmanageclient.bean.CalendarAssetForApp;
import com.miduo.financialmanageclient.bean.InsureBean;
import com.miduo.financialmanageclient.bean.InvestBean;
import com.miduo.financialmanageclient.bean.MyMessage;
import com.miduo.financialmanageclient.bean.NInsuranceHomeBean;
import com.miduo.financialmanageclient.bean.NInvestmentResult;
import com.miduo.financialmanageclient.bean.NPlannerTempBean;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult.NProductCenterInsureBean;
import com.miduo.financialmanageclient.bean.NProductResult;
import com.miduo.financialmanageclient.bean.ProfitBean;
import com.miduo.financialmanageclient.ui.HomeActivity;

public class MyApplication extends Application {
	public static MyApplication instance;
	public static int fragment_id;
	public static List<Integer> fromIdLst = new ArrayList<Integer>();
	public static List<Activity> activityLists = new ArrayList<Activity>();// 购买相关的Activity使用
	public static int home_index = 0;
	public static boolean home_refresh = true;
	public static int index = 0;// 投资测试结果往产品中心跳转使用
	// 投资答题信息
	public static InvestBean investBean = null;
	// 投保答题信息
	public static InsureBean insureBean = null;
	// true 购买普通产品 false 购买转让单
	public static boolean isBuy = true;
	// 保险详情
	public static NProductCenterInsureBean ninsurebean;
	// 首页 昨日收益数据
	public static ProfitBean profitBean = null;
	// 首页 日历
	public static CalendarAssetForApp calendarAssetForApp = null;
	// 首页热销产品
	public static NProductResult productResult = null;
	// 首页投资信息
	public static NInvestmentResult investmentResult = null;
	// 首页投保信息
	public static NInsuranceHomeBean insureResult = null;
	public static String cookie;
	public static String cus_sessionid;
	public static NPlannerTempBean new_planner;
	public static NPlannerTempBean old_planner;
	public static HomeActivity homeActivity;
	public static boolean test_value = false;
	/**
	 * 推送通知点击我知道了使用
	 */
	public static MyMessage message;

	@Override
	public void onCreate() {
		instance = this;
		super.onCreate();
	}

	public static MyApplication getInstance() {
		return instance;
	}

	public static void setInstance(MyApplication instance) {
		MyApplication.instance = instance;
	}

	public static void finishPurchaseActivity() {
		for (int i = 0; i < activityLists.size(); i++) {
			activityLists.get(i).finish();
		}
		activityLists.clear();// 防止内存泄露
	}
}
