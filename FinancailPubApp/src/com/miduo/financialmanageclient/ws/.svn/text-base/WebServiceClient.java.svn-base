package com.miduo.financialmanageclient.ws;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.reflect.TypeToken;
import com.miduo.financialmanageclient.application.ConstantValues;
import com.miduo.financialmanageclient.application.MyApplication;
import com.miduo.financialmanageclient.bean.MyTranserResult;
import com.miduo.financialmanageclient.bean.NAssetsResult;
import com.miduo.financialmanageclient.bean.NBankCardNum;
import com.miduo.financialmanageclient.bean.NBankRateResult;
import com.miduo.financialmanageclient.bean.NCalendarResult;
import com.miduo.financialmanageclient.bean.NInsuranceHomeBean;
import com.miduo.financialmanageclient.bean.NInsureanceResult;
import com.miduo.financialmanageclient.bean.NInvestmentResult;
import com.miduo.financialmanageclient.bean.NOrderResult;
import com.miduo.financialmanageclient.bean.NPlannerInfo;
import com.miduo.financialmanageclient.bean.NPlannerResult;
import com.miduo.financialmanageclient.bean.NProductCenterInsureResult;
import com.miduo.financialmanageclient.bean.NProductResult;
import com.miduo.financialmanageclient.bean.NSingelPlannerResult;
import com.miduo.financialmanageclient.bean.NSingleGainResult;
import com.miduo.financialmanageclient.bean.NSurplusResult;
import com.miduo.financialmanageclient.bean.NTransferResult;
import com.miduo.financialmanageclient.bean.ReplacePlannerResult;
import com.miduo.financialmanageclient.bean.ReturnMsg;
import com.miduo.financialmanageclient.bean.ServerVersionInfo;
import com.miduo.financialmanageclient.util.JsonUtils;
import com.miduo.financialmanageclient.util.LOG;
import com.miduo.financialmanageclient.util.SharePrefUtil;
import com.miduo.financialmanageclient.util.StringUtil;

public class WebServiceClient {
	static HttpClient httpclient = null;
	private static HttpPost post;
	static boolean isSessionTimeOut = false;
	static String originalUrl = null;
	static Map<String, String> originalMap = null;

	public static HttpClient getHttpClient() {
		return httpclient;
	}

	public static String map_post(String url, Map<String, String> params) throws Exception {
		if (httpclient == null)
			httpclient = new DefaultHttpClient();
		post = new HttpPost(url);
		post.setHeader("connection", "close");
		if (params != null && params.size() > 0) {

			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> item : params.entrySet()) {
				BasicNameValuePair pair = new BasicNameValuePair(item.getKey(), item.getValue());
				parameters.add(pair);
			}
			UrlEncodedFormEntity entity;
			try {
				entity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
				post.setEntity(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		HttpResponse response = httpclient.execute(post);
		System.out.println(response.getStatusLine().getStatusCode() + "==code");
		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			List<Cookie> cookies = ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
			Cookie cookie = null;
			String sessionId = null;
			for (int i = 0; i < cookies.size(); i++) {
				cookie = cookies.get(i);
				if ("jsessionid".equalsIgnoreCase(cookie.getName())) {
					sessionId = cookie.getValue();
					MyApplication.cookie = sessionId;
				}
				if ("_cus_sessionid".equalsIgnoreCase(cookie.getName())) {
					MyApplication.cus_sessionid = cookie.getValue();
				}
			}
			System.out.println("url:" + url);
			if (StringUtil.isEmpty(result)) {
				return result;
			} else {
				JSONObject jo = new JSONObject(result);
				int state = jo.getInt("state");
				if (state == -1) {
					// 走登录接口
					isSessionTimeOut = true;
					originalUrl = url;
					originalMap = params;
					return getLogin();
				} else {
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * 登录
	 * 
	 * @param score
	 * @return
	 * @throws AppException
	 */
	public static String getLogin() throws AppException {
		try {
			Map<String, String> map = new HashMap<String, String>();
			String mobile = SharePrefUtil.getString(MyApplication.instance, SharePrefUtil.ACCOUNT_MOBILE, null);
			String psd = SharePrefUtil.getString(MyApplication.instance, SharePrefUtil.ACCOUNT_PSD, null);
			map.put("mobile", mobile);
			map.put("password", psd);
			return login_post(ConstantValues.LOGIN, map);
		} catch (Exception e) {
			if (e instanceof AppException) {
				throw (AppException) e;
			} else {
				throw AppException.network(e);
			}
		}
	}

	public static String login_post(String url, Map<String, String> params) throws Exception {
		if (httpclient == null)
			httpclient = new DefaultHttpClient();
		post = new HttpPost(url);
		if (params != null && params.size() > 0) {

			List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
			for (Map.Entry<String, String> item : params.entrySet()) {
				BasicNameValuePair pair = new BasicNameValuePair(item.getKey(), item.getValue());
				parameters.add(pair);
			}
			UrlEncodedFormEntity entity;
			try {
				entity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
				post.setEntity(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		HttpResponse response = httpclient.execute(post);
		System.out.println(response.getStatusLine().getStatusCode() + "==code");
		if (response.getStatusLine().getStatusCode() == 200) {
			List<Cookie> cookies = ((AbstractHttpClient) httpclient).getCookieStore().getCookies();
			Cookie cookie = null;
			String sessionId = null;
			for (int i = 0; i < cookies.size(); i++) {
				cookie = cookies.get(i);
				if ("jsessionid".equalsIgnoreCase(cookie.getName())) {
					sessionId = cookie.getValue();
					MyApplication.cookie = sessionId;
				}
				if ("_cus_sessionid".equalsIgnoreCase(cookie.getName())) {
					MyApplication.cus_sessionid = cookie.getValue();
				}
			}
			String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			if (StringUtil.isEmpty(result)) {
				isSessionTimeOut = false;
				return result;
			} else {
				JSONObject jo = new JSONObject(result);
				int state = jo.getInt("state");
				if (state == 1) {
					if (isSessionTimeOut) {
						isSessionTimeOut = false;
						return map_post(originalUrl, originalMap);
					}
				} else {
					isSessionTimeOut = false;
					state = ConstantValues.LOGIN_ERROR;
					result = "{\"state\":" + state + ",\"msg\":\"" + jo.getString("msg") + "\",\"data\":null}";
					return result;
				}
			}
		}
		return null;
	}

	/**
	 * 获取注册随机图形验证码
	 * 
	 * @throws AppException
	 */
	public static Bitmap getUrlImage() throws Exception {
		Bitmap img = null;
		if (httpclient == null)
			httpclient = new DefaultHttpClient();
		post = new HttpPost(ConstantValues.GET_IMAGE);
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
		HttpResponse response = httpclient.execute(post);
		System.out.println(response.getStatusLine().getStatusCode() + "==code");
		if (response.getStatusLine().getStatusCode() == 200) {
			InputStream is = response.getEntity().getContent();// 获得图片的数据流
			img = BitmapFactory.decodeStream(is);
			is.close();
		}
		return img;
	}

	/**
	 * 获取投保首页数据
	 * 
	 * @return
	 */
	public static NInsuranceHomeBean getInsuranceHomeData() throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.INSURE_HOMEPAGE, null),
					new TypeToken<NInsuranceHomeBean>() {
					}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取投资结果页面数据(提交测试结果)
	 * 
	 * @throws AppException
	 */
	public static NInvestmentResult getInvestmentResultData(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.INVESTEMENT_RESULT, map),
					new TypeToken<NInvestmentResult>() {
					}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取投资结果页面数据（提交风险系数）
	 * 
	 * @throws AppException
	 */
	public static NInvestmentResult getInvestmentRiskesultData(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.INVESTEMENT_RESULT_RISK, map),
					new TypeToken<NInvestmentResult>() {
					}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取投资起始页信息
	 * 
	 * @throws AppException
	 */
	public static NInvestmentResult getInvestHomeData() throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.INVEST_HOMEPAGE, null), new TypeToken<NInvestmentResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 发送手机验证码
	 * 
	 * @throws AppException
	 */
	public static ReturnMsg getAppCode(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.GET_APP_CODE, map), new TypeToken<ReturnMsg>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 注册用户
	 * 
	 * @throws AppException
	 */
	public static String register(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.REGISTER, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 登录
	 * 
	 * @throws AppException
	 */
	public static String login(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.LOGIN, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @throws AppException
	 */
	public static ReturnMsg updateLoginPsd(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.UPDATE_LOGIN_PSD, map), new TypeToken<ReturnMsg>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取投保结果页面数据
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static NInsureanceResult getInsuranceResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.INSURE_RESULT, map), new TypeToken<NInsureanceResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取产品中心数据
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static NProductResult getProductResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.PRODUCT_CENTER, map), new TypeToken<NProductResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取投资投保历史数据 0投资 1投保
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static String getRecordLst(Map<String, String> map, int type) throws AppException {
		try {
			String url = "";
			if (type == 0) {
				url = ConstantValues.INVEST_HISTORY_LST;
			} else {
				url = ConstantValues.INSURE_HISTORY_LST;
			}
			return map_post(url, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 删除投资投保历史数据 0投资 1投保
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static ReturnMsg delRecordLst(String param, int type) throws AppException {
		try {
			String url = "";
			if (type == 0) {
				url = ConstantValues.INVEST_HISTORY_DEL;
			} else {
				url = ConstantValues.INSURE_HISTORY_DEL;
			}
			return JsonUtils.toBean(map_post(url + param, null), new TypeToken<ReturnMsg>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取投资投保记录详情 0投资 1投保
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static String getRecordDetail(Map<String, String> map, int type) throws AppException {
		try {
			String url = "";
			if (type == 0) {
				url = ConstantValues.INVEST_HISTORY_DETAIL;
			} else {
				url = ConstantValues.INSURE_HISTORY_DETAIL;
			}
			return map_post(url, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取产品中心数据
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static NProductCenterInsureResult getInsureProductResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.PRODUCT_CENTER, map),
					new TypeToken<NProductCenterInsureResult>() {
					}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 米仓首页
	 * 
	 * @return
	 * @throws AppException
	 */
	public static String repertoryInfo(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.REPERTORY_INFO, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取消息列表 roleId=1&pageIndex=1&pageSize=10&client=4&topTypeId=3 roleId
	 * 用户角色(0IFA、1大众) client 终端类型(0微信、1App、2短信、3邮件、4站内信) 4 topTypeId
	 * 顶级业务类型(1推广消息、 2CRM消息 、 3系统消息 、 4交易消息、 5消息通知 ) pageIndex pageSize
	 */
	public static String getMsgLst(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.MSG_LST, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取全部类型中未读消息数 client 终端类型(0微信、1App、2短信、3邮件、4站内信) 4 roleId 用户角色(0IFA、1大众)
	 */
	public static String notReadCount() throws AppException {
		try {
			return map_post(ConstantValues.NOT_READ_COUNT + "?client=4&roleId=1", null);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 设置消息为已读 uuid
	 */
	public static String setMsgRead(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.SET_READ, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 设置全部消息为已读 roleId 用户角色(0IFA、1大众) client 终端类型(0微信、1App、2短信、3邮件、4站内信) 4
	 * topTypeId 顶级业务类型(1推广消息、 2CRM消息 、 3系统消息 、 4交易消息、 5消息通知 )
	 */
	public static String setAllMsgRead(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.SET_ALL_READ, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 我的理财师
	 * 
	 * @return
	 * @throws AppException
	 */
	public static NPlannerInfo getPlannerInfo() throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.MY_PLANNER, null), new TypeToken<NPlannerInfo>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 转让产品
	 * 
	 * @return
	 */
	public static NTransferResult getTransferProduct(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.TRANSFER_PRODUCT, map), new TypeToken<NTransferResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 资产列表
	 * 
	 * @return
	 */
	public static NAssetsResult getAssetsResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.ASSETS_LIST, map), new TypeToken<NAssetsResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 得到银行卡数目
	 * 
	 * @return
	 * @throws AppException
	 */
	public static NBankCardNum getBankCardNum() throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.BANKCARD_NUM, null), new TypeToken<NBankCardNum>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 设置全部消息为已读 roleId 用户角色(0IFA、1大众) client 终端类型(0微信、1App、2短信、3邮件、4站内信) 4
	 * topTypeId 顶级业务类型(1推广消息、 2CRM消息 、 3系统消息 、 4交易消息、 5消息通知 )
	 */
	public static String getCumulativeCommission() throws AppException {
		try {
			return map_post(ConstantValues.CUMULATIVE_COMMISSION, null);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 首页昨日收益
	 */
	public static String getUserRevenue() throws AppException {
		try {
			return map_post(ConstantValues.USER_REVENUE, null);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 单日收益
	 */
	public static NSingleGainResult getSingleGainResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.SINGLEDAY_GAIN, map), new TypeToken<NSingleGainResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 查询银行卡列表
	 */
	public static String getCardLst() throws AppException {
		try {
			return map_post(ConstantValues.BANK_CARD_LST, null);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取转让验证码
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static ReturnMsg getTransferCode(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.BUY_CODE, map), new TypeToken<ReturnMsg>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 查询理财师列表
	 */
	public static NPlannerResult getPlannerResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.PLANNER_LIST, map), new TypeToken<NPlannerResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 查询银行列表
	 */
	public static String getBankLst() throws AppException {
		try {
			return map_post(ConstantValues.BANK_LST, null);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 保存银行卡
	 */
	public static String saveBankCard(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.SAVE_CARD, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取资产详情信息
	 */
	public static String GetAssetDetail(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.ASSET_DETAIL, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取赎回信息
	 */
	public static String GetRedeemInfo(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.REDEEM_INFO, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取日历数据
	 */
	public static NCalendarResult getCalendarResult() throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.CALENDAR, null), new TypeToken<NCalendarResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 提交生成订单请求
	 */
	public static NOrderResult getOrderResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.ORDER_GENERATE, map), new TypeToken<NOrderResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 赎回验证码
	 */
	public static ReturnMsg GetRedeemSms() throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.GET_REDEEM_SMS, null), new TypeToken<ReturnMsg>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 提交单个赎回信息
	 */
	public static String ConfirmSingleRedeem(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.SINGLE_REDEEM_CONFIRM, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 提交批量赎回信息
	 */
	public static String ConfirmBatchRedeem(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.BATCH_REDEEM_CONFIRM, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 获取最新剩余额度
	 */
	public static NSurplusResult getNSurplusResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.RELATIVE_SURPLUS, map), new TypeToken<NSurplusResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 普通产品详情中获取银行利率
	 */
	public static NBankRateResult getNBankRateResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.BANK_RATE, map), new TypeToken<NBankRateResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 取消转让
	 * 
	 * @throws AppException
	 */
	public static ReturnMsg transferCancel(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.TRANSFER_CANCEL, map), new TypeToken<ReturnMsg>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 购买验证码
	 */
	public static ReturnMsg GetRedeemSms1(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.BUY_CODE, map), new TypeToken<ReturnMsg>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 提交转让单
	 */
	public static String ConfirmTransfer(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.CONFIRM_TRANSFER, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	// 获取
	public static String getTransfer(Map<String, String> map) throws AppException {
		String tRANSFER_INOFO_URL = null;
		try {
			int size = map.size();
			if (size == 1) {
				tRANSFER_INOFO_URL = ConstantValues.TRANSFER_INOFO;
			}
			if (size == 2) {
				tRANSFER_INOFO_URL = ConstantValues.TRSTRING_INOFO_NEW;
			}

			System.out.println(map.toString());

			return map_post(tRANSFER_INOFO_URL, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 判断未来三天（包含今天4天）是是否有提醒
	 */
	public static String checkCalendarData() throws AppException {
		try {
			return map_post(ConstantValues.CHECK_CALENDAR_DATA, null);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 判断是否有测试记录
	 */
	public static String getTestCount(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.GET_TEST_COUNT, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 根据理财师id查询理财师
	 */

	public static NSingelPlannerResult getSinglePlannerResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.PLANNER, map), new TypeToken<NSingelPlannerResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 确认更换理财师
	 */
	public static ReplacePlannerResult getReplacePlannerResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.CHANGE_PLANNER, map),
					new TypeToken<ReplacePlannerResult>() {
					}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 刷新转让页面的信息
	 */
	public static String getTurnInfo(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.TRANSFER_INOFO, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 刷新转让页面的信息
	 */
	public static String refreshTurnInfo(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.TRSTRING_INOFO_NEW, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 退出账户登录
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static String loginoff() throws AppException {
		try {
			return map_post(ConstantValues.LOGIN_OFF, null);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 确认支付
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static String confirmPay(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.LOAD_WEBVIEW, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 绑定理财师
	 */
	public static ReplacePlannerResult getBindPlannerResult(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.BIND_PLANNER, map), new TypeToken<ReplacePlannerResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 提交设备id，ifaid
	 * 
	 * @param map
	 * @return
	 * @throws AppException
	 */
	public static String submitClientID(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.SUBMIT_CLIENTID, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 购买转让单，判断是否是自己的转让单
	 * 
	 * @throws AppException
	 */
	public static MyTranserResult getIsSelfTransferOrder(Map<String, String> map) throws AppException {
		try {
			return JsonUtils.toBean(map_post(ConstantValues.TRANSFER_MYSELF, map), new TypeToken<MyTranserResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 实名认证
	 */
	public static String identify(Map<String, String> map) throws AppException {
		try {
			return map_post(ConstantValues.IDENTIFY, map);
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}
	
	/**
	 * 强制升级
	 * 
	 * @param insurancetest
	 * @return
	 * @throws AppException
	 */

	public static ServerVersionInfo mobileUpdate(Map<String, String> map) throws AppException{
		try {
			return JsonUtils.toBean(map_post(ConstantValues.APP_UAPDATE, map), new TypeToken<ServerVersionInfo>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

	/**
	 * 判断是否在升级
	 * 
	 * @return
	 * @throws AppException
	 */
	public static String debug() throws AppException {
		try {
			return debug_post("http://www.jinkaoedu.com/mobiles/miduo/debug_public.json");
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}
	
	public static String debug_post(String url)
			throws Exception {
		if (httpclient == null)
			httpclient = new DefaultHttpClient();
		post = new HttpPost(url);
		post.setHeader("connection", "close");
		System.out.println(url);
		
		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				30000);
		HttpResponse response = httpclient.execute(post);
		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString(response.getEntity(),
					HTTP.UTF_8);
			System.out.println("result="+result);
			System.out.println("StringUtil.isEmpty(result)="+ StringUtil.isEmpty(result));
			if (!StringUtil.isEmpty(result)) {
				return result;
			}
		}
		return null;
	}
	
	/**
	 * 获取计算收益的参数
	 * 
	 * @param insurancetest
	 * @return
	 * @throws AppException
	 */

	public static NBankRateResult getProductProfit(Map<String, String> map) throws AppException{
		try {
			return JsonUtils.toBean(map_post(ConstantValues.PRODUCT_PROFIT, map), new TypeToken<NBankRateResult>() {
			}.getType());
		} catch (Exception e) {
			if (e instanceof AppException)
				throw (AppException) e;
			throw AppException.network(e);
		}
	}

}
