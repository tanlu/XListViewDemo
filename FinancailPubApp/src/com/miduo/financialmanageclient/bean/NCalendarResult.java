package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

public class NCalendarResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * state : 1
     * msg : null
     * data : [{"yearNum":"2015","assetForCalendarList":[{"productName":"米多利-九鼎股权质押项目19期","assetAmount":3000,"assetNo":"MDL01447759191540","assetState":1,"redeemTimeCon":"2015-12-01","redeemRemainderTime":3,"transferRemainderTime":null,"payInterestRemainderTime":null,"redeemTimeSlot":"00:00-14:00","eventStartTime":"00:00","eventEndTime":"14:00","alarmClockTime":null,"bindBankcard":false}]}]
     */

    private int state;
    private String msg;
    private List<NCalendarBean> data;

    public void setState(int state) {
        this.state = state;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<NCalendarBean> data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public List<NCalendarBean> getData() {
        return data;
    }

   

}
