package com.miduo.financialmanageclient.util;

import android.app.Activity;
import android.content.Intent;

import com.miduo.financialmanageclient.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

public class SocialShareUtil {
	public static final String DESCRIPTOR = "com.umeng.share";
	private final static UMSocialService mController = UMServiceFactory
			.getUMSocialService(DESCRIPTOR);
	private static Activity activity;

	/**
	 * 
	 * @param _activity
	 * @param index
	 *            1 分享产品 2分享理财师
	 * @param url 分享的url
	 */
	public static void share(Activity _activity, String url,String title,String content) {
		activity = _activity;
		// 添加微信平台
		addWXPlatform();
		mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN,
				SHARE_MEDIA.WEIXIN_CIRCLE);
		mController.openShare(activity, false);
		// 设置分享的内容
		setShare(url,title,content);
	}

	/**
	 * 添加微信平台
	 */
	private static void addWXPlatform() {
		// TODO Auto-generated method stub
		// 注意：在微信授权的时候，必须传递appSecret
		// 在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
		String appId = "wx591cf2967e4083d2";
		String appSecret = "d4624c36b6795d1d99dcf0547af5443d";
		// 添加微信平台
		UMWXHandler wxHandler = new UMWXHandler(activity, appId, appSecret);
		wxHandler.addToSocialSDK();

		// 支持微信朋友圈
		UMWXHandler wxCircleHandler = new UMWXHandler(activity, appId,
				appSecret);
		wxCircleHandler.setToCircle(true);
		wxCircleHandler.addToSocialSDK();
	}
	
	/**
	 * 分享
	 */
	private static void setShare(String url,String title,String content) {
		// TODO Auto-generated method stub
		UMImage localImage = new UMImage(activity, R.drawable.fen_xiang_logo_client);

		// 设置微信分享内容
		WeiXinShareContent weixinContent = new WeiXinShareContent();
		weixinContent.setShareImage(localImage);
		weixinContent.setShareContent(content);	
		weixinContent.setTitle(title);	
		weixinContent.setTargetUrl(url);
		mController.setShareMedia(weixinContent);

		// 设置朋友圈分享的内容
		CircleShareContent circleMedia = new CircleShareContent();
		circleMedia.setShareImage(localImage);
		circleMedia.setTitle(content);
		circleMedia.setShareContent(content);
		circleMedia.setTargetUrl(url);
		mController.setShareMedia(circleMedia);
	}

	public static void closeShare(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(
				requestCode);
		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
}
