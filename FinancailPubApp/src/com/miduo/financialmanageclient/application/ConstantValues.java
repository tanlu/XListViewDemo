package com.miduo.financialmanageclient.application;

public class ConstantValues {
	public static final Integer LOGIN_ERROR = 9523;
	// 根文件夹名字
	public static final String ROOTDIR = "financial_pub";
	// 录音文件
	public static final int RECROD_SOUND_TYPE = 1;
	public static final String RECROD_SOUND_DIR = "sound";
	// 图片文件
	public static final int IMAGE_TYPE = 2;
	public static final String IMAGE_DIR = "images";
	// 头像
	public static final int PHOTO_TYPE = 3;
	public static final String PHOTO_DIR = "photo";
	// 缓存
	public static final int CACHE_TYPE = 4;
	public static final String CACHE_DIR = "cache";
	// APK
	public static final int UPDATE_TYPE = 5;
	public static final String UPDATE_DIR = "update";

	// http://mdcf01.6655.la:10261/ （APP产品的接口、投资规划相关的产品接口）
	// mdcf01.6655.la:11294 （我的米仓）
	// mdcf01.6655.la:10338 （登录注册）
//
//	public static final String ROOT_IP_PRODUCT = "http://mdcf01.6655.la:10261";
//	public static final String ROOT_IP_BARN = "http://mdcf01.6655.la:11294";
//	public static final String ROOT_IP_LOGIN = "http://mdcf01.6655.la:10338";
//	public static final String ROOT_IP_MSG = "http://mdcf01.6655.la:11184";

	/**
	 * ip地址 产品模板 APP产品的接口、投资规划相关的产品接口
	 */
//	public static final String ROOT_IP_PRODUCT = "http://192.168.7.115";
	public static final String ROOT_IP_PRODUCT = "http://wwwtest.miduo.com";
	/**
	 * ip地址 我的米仓
	 */
//	public static final String ROOT_IP_BARN = "http://192.168.6.190";
//	public static final String ROOT_IP_BARN = "http://192.168.7.20";
	public static final String ROOT_IP_BARN = "http://wwwtest.miduo.com/ucenter";
	/**
	 * ip地址 登录注册
	 */
	
	public static final String ROOT_IP_LOGIN = "http://wwwtest.miduo.com/passport";
	/**
	 * ip地址 消息
	 */
	public static final String ROOT_IP_MSG = "http://wwwtest.miduo.com/message";
	/**
	 * 产品中心页面
	 */
	public static final String PRODUCT_CENTER = ROOT_IP_PRODUCT + "/app/product/list.htm";
	/**
	 * 获取转让专区
	 */
	public static final String TRANSFER_PRODUCT = ROOT_IP_PRODUCT + "/app/product/transferOrderlist.htm";
	/**
	 * 获取投保首页数据
	 */
	public static final String INSURE_HOMEPAGE = ROOT_IP_PRODUCT + "/tools/assess/insurance4App.htm";
	/**
	 * 获取投资结果页面数据（提交测试结果）
	 */
	public static final String INVESTEMENT_RESULT = ROOT_IP_PRODUCT + "/tools/assess/investResult4App.htm";
	/**
	 * 获取投资结果页面数据（提交风险系数）
	 */
	public static final String INVESTEMENT_RESULT_RISK = ROOT_IP_PRODUCT + "/tools/assess/reInvestResult4App.htm";
	/**
	 * 获取投资起始页
	 */
	public static final String INVEST_HOMEPAGE = ROOT_IP_PRODUCT + "/tools/assess/invest4App.htm";
	/**
	 * 投保结果页面
	 */
	public static final String INSURE_RESULT = ROOT_IP_PRODUCT + "/tools/assess/insuranceResult4App.htm";
	/**
	 * 投资历史记录列表
	 */
	public static final String INVEST_HISTORY_LST = ROOT_IP_PRODUCT + "/tools/assess/investTestHistoryList4App.htm";
	/**
	 * 投资历史记录删除
	 */
	public static final String INVEST_HISTORY_DEL = ROOT_IP_PRODUCT + "/tools/assess/investTestDelete4App.htm";
	/**
	 * 投资记录详情
	 */
	public static final String INVEST_HISTORY_DETAIL = ROOT_IP_PRODUCT
			+ "/tools/assess/investTestHistoryDetail4App.htm";
	/**
	 * 投保历史记录列表
	 */
	public static final String INSURE_HISTORY_LST = ROOT_IP_PRODUCT + "/tools/assess/insuranceTestHistoryList4App.htm";
	/**
	 * 投保历史记录删除
	 */
	public static final String INSURE_HISTORY_DEL = ROOT_IP_PRODUCT + "/tools/assess/insuranceTestDelete4App.htm";
	/**
	 * 投保记录详情
	 */
	public static final String INSURE_HISTORY_DETAIL = ROOT_IP_PRODUCT
			+ "/tools/assess/insuranceTestHistoryDetail4App.htm";
	/**
	 * 获取消息列表 roleId=1&pageIndex=1&pageSize=10&client=4&topTypeId=3 roleId
	 * 用户角色(0IFA、1大众) client 终端类型(0微信、1App、2短信、3邮件、4站内信) 4 topTypeId
	 * 顶级业务类型(1推广消息、 2CRM消息 、 3系统消息 、 4交易消息、 5消息通知 ) pageIndex pageSize
	 */
	public static final String MSG_LST = ROOT_IP_MSG + "/api/query/list.htm";
	/**
	 * 获取全部类型中未读消息数 client 终端类型(0微信、1App、2短信、3邮件、4站内信) 4 roleId 用户角色(0IFA、1大众)
	 */
	public static final String NOT_READ_COUNT = ROOT_IP_MSG + "/api/query/notReadCount.htm";
	/**
	 * 设置消息为已读 uuid
	 */
	public static final String SET_READ = ROOT_IP_MSG + "/api/query/read.htm";
	/**
	 * 设置全部消息为已读 roleId 用户角色(0IFA、1大众) client 终端类型(0微信、1App、2短信、3邮件、4站内信) 4
	 * topTypeId 顶级业务类型(1推广消息、 2CRM消息 、 3系统消息 、 4交易消息、 5消息通知 )
	 */
	public static final String SET_ALL_READ = ROOT_IP_MSG + "/api/query/readAll.htm";
	/**
	 * 判断是否有测试记录
	 */
	public static final String GET_TEST_COUNT = ROOT_IP_PRODUCT + "/tools/assess/resultCounts4App.htm";
	
	/**
	 * 是否升级
	 */
	public static final String APP_UAPDATE = ROOT_IP_LOGIN + "/accessDeviceInfo.do";
	/**
	 * 实名认证
	 */
	public static final String IDENTIFY = ROOT_IP_LOGIN + "/inRealNameAuthApp.do";
	/**
	 * 获取注册随机图形验证码
	 */
	public static final String GET_IMAGE = ROOT_IP_LOGIN + "/app/getImage.do";
	/**
	 * 发送手机验证码 传入参数：mobile 字符类型：String 传入参数：checkcode 字符类型：String
	 */
	public static final String GET_APP_CODE = ROOT_IP_LOGIN + "/app/getAppCode.do";
	/**
	 * 注册用户 1、mobile String类型 2、code String类型 3、password String类型 4、refPwd
	 * String类型 5、ifaCode String类型 6、source int类型 用户来源（0、pc 1、wechat 2、mobile
	 * 3、andorid 4、ios 5、后台）
	 */
	public static final String REGISTER = ROOT_IP_LOGIN + "/app/registerApp.do";
	/**
	 * 登录 传入参数：mobile 字符类型：String 传入参数：password 字符类型：String
	 */
	public static final String LOGIN = ROOT_IP_LOGIN + "/app/toAppSign.do";
	/**
	 * 修改登录密码 1、mobile String类型 2、code String类型 3、password String类型 4、refPwd
	 * String类型
	 */
	public static final String UPDATE_LOGIN_PSD = ROOT_IP_LOGIN + "/app/toUpdateMobilePwd.do";
	
	/**
	 * 退出登录
	 */
	public static final String LOGIN_OFF = ROOT_IP_LOGIN + "/toLoginDes.do";

	/**
	 * 米仓首页
	 */
	public static final String REPERTORY_INFO = ROOT_IP_BARN + "/app/mrepertory/queryMRepertoryInfo.do";
	/**
	 * 我的理财师
	 */
	public static final String MY_PLANNER = ROOT_IP_BARN + "/app/ifaInfo/queryIfaInfoByUserId.do";
	/**
	 * 资产列表 ?userId=1093
	 */
	public static final String ASSETS_LIST = ROOT_IP_BARN + "/app/mrepertory/queryUserAssetList.do";
	/**
	 * 累计佣金
	 */
	public static final String CUMULATIVE_COMMISSION = ROOT_IP_BARN + "/app/userIncomeDay/queryUserIncomeDay.do";
	/**
	 * 首页昨日收益
	 */
	public static final String USER_REVENUE = ROOT_IP_BARN + "/app/mrepertory/queryUserRevenue.do";
	/**
	 * 单日收益
	 * 首页昨日收益
	 */
	public static final String SINGLEDAY_GAIN = ROOT_IP_BARN + "/app/userIncomeDay/queryUserAssetIncomeDay.do";
	/**
	 * 查询理财师列表
	 */

	public static final String PLANNER_LIST = ROOT_IP_BARN + "/app/ifaInfo/queryIfaInfoList.do";
	/**
	 * 资产详情
	 */
	public static final String ASSET_DETAIL = ROOT_IP_BARN + "/app/mrepertory/queryUaerAssetInfo.do";
	/**
	 * 根据理财师id查询理财师
	 */
	public static final String PLANNER = ROOT_IP_BARN + "/app/ifaInfo/queryIfaByInvCode.do";
	/**
	 * 日历接口
	 */
	public static final String CALENDAR = ROOT_IP_BARN + "/app/mrepertory/queryMDCalendar.do";
	/**
	 * 判断未来三天（包含今天4天）是是否有提醒
	 */
	public static final String CHECK_CALENDAR_DATA = ROOT_IP_BARN + "/app/mrepertory/queryMDCalendarRead.do";
	/**
	 * 确认更换理财师
	 */
	public static final String CHANGE_PLANNER = ROOT_IP_BARN + "/app/ifaInfo/changeBindIfa.do";

	/**
	 * 获取银行卡数目
	 */
	public static final String BANKCARD_NUM = ROOT_IP_BARN + "/app/bankCard/queryUserBankCardCount.do";
	/**
	 * 查询银行卡列表
	 */
	public static final String BANK_CARD_LST = ROOT_IP_BARN + "/app/bankCard/queryUserBankCardInfoList.do";
	/**
	 * 查询银行列表
	 */
	public static final String BANK_LST = ROOT_IP_BARN + "/app/bankCard/queryBankListInfo.do";
	/**
	 * 保存银行卡
	 */
	public static final String SAVE_CARD = ROOT_IP_BARN + "/app/bankCard/saveUserBankCard.do";
	/**
	 * 购买生成订单
	 */
	public static final String ORDER_GENERATE = ROOT_IP_BARN + "/app/order/create.do";
	/**
	 * 赎回信息
	 */
	public static final String REDEEM_INFO = ROOT_IP_BARN + "/app/redeem/redeem.do";
	/**
	 * 赎回验证码
	 */
	public static final String GET_REDEEM_SMS = ROOT_IP_BARN + "/app/redeem/getSms.do";
	/**
	 * 提交单个赎回信息
	 */
	public static final String SINGLE_REDEEM_CONFIRM = ROOT_IP_BARN + "/app/redeem/confirm.do";
	/**
	 * 提交批量赎回信息
	 */
	public static final String BATCH_REDEEM_CONFIRM = ROOT_IP_BARN + "/app/redeem/batch.do";
	/**
	 * 确认支付
	 */
	public static final String LOAD_WEBVIEW = ROOT_IP_BARN + "/app/order/payResult.do";
	/**
	 * 取消转让单
	 */
	public static final String TRANSFER_CANCEL = ROOT_IP_BARN + "/app/transfer/cancel.do";
	/**
	 * 获取最新剩余额度
	 */
	public static final String RELATIVE_SURPLUS = ROOT_IP_PRODUCT + "/app/product/remainAmount.htm";
	/**
	 * 获取银行利率
	 */
	public static final String BANK_RATE = ROOT_IP_PRODUCT + "/app/product/calculator.htm";
	/**
	 * 购买获取验证码
	 */

	public static final String BUY_CODE = ROOT_IP_BARN + "/app/transfer/getSmsCode.do";


	/**
	 * 转让信息
	 */
	public static final String TRANSFER_INOFO = ROOT_IP_BARN + "/app/transfer/transfer.do";

	public static final String TRSTRING_INOFO_NEW = ROOT_IP_BARN + "/app/transfer/calcRate.do";
	/**
	 * 获取转让的验证码
	 */
	public static final String TRANSFER_CODE = ROOT_IP_BARN + "/app/transfer/dotransfer.do";
	/**
	 * 获取转让的验证码
	 */
	public static final String CONFIRM_TRANSFER = ROOT_IP_BARN + "/app/transfer/dotransfer.do";
	/**
	 * 绑定理财师的接口
	 */
	public static final String BIND_PLANNER = ROOT_IP_PRODUCT+"/passport/toBandIfa.do";
	/**
	 * 提交clientid
	 */
	public static final String SUBMIT_CLIENTID=ROOT_IP_LOGIN+"/AuthToCliendId.do";
	/**
	 * 购买转让单（判断是否是自己的）
	 */
	public static final String TRANSFER_MYSELF = ROOT_IP_BARN + "/app/order/myself.do";
	/**
	 * 购买产品计算收益
	 */
	public static final String PRODUCT_PROFIT=ROOT_IP_PRODUCT+"/app/product/count.htm";
	
	
	
	
}
