package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
import java.util.List;

public class NSingleGainResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 /**
     * state : 1
     * msg : 数据加载成功
     * data : [{"icDate":"2015-11-24","icDateFloat":54.795,"amount":5.4795,"appAssetUserIncomeJsonVos":[{"productTitle":"米多利-九鼎股权质押项目19期","amount":2.1918},{"productTitle":"米多利-股票配资20号","amount":3.2877}]}]
     */

    private int state;
    private String msg;
    private List<ScrollPerBarBean> data;
    private int total;
    public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

    public void setState(int state) {
        this.state = state;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<ScrollPerBarBean> data) {
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public List<ScrollPerBarBean> getData() {
        return data;
    }

    public static class ScrollPerBarBean {
        /**
         * icDate : 2015-11-24
         * icDateFloat : 54.795
         * amount : 5.4795
         * appAssetUserIncomeJsonVos : [{"productTitle":"米多利-九鼎股权质押项目19期","amount":2.1918},{"productTitle":"米多利-股票配资20号","amount":3.2877}]
         */

        private String icDate;
        private double icDateFloat;
        private double amount;
        private List<SingleGainBean> appAssetUserIncomeJsonVos;
    	private int actualHeight;// 实际高度
    	private boolean flag;// 记录是不是点击状态
    	private int leftX, rightX, topY;// 记录左右上边距


        public int getActualHeight() {
			return actualHeight;
		}

		public void setActualHeight(int actualHeight) {
			this.actualHeight = actualHeight;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public int getLeftX() {
			return leftX;
		}

		public void setLeftX(int leftX) {
			this.leftX = leftX;
		}

		public int getRightX() {
			return rightX;
		}

		public void setRightX(int rightX) {
			this.rightX = rightX;
		}

		public int getTopY() {
			return topY;
		}

		public void setTopY(int topY) {
			this.topY = topY;
		}

		public void setIcDate(String icDate) {
            this.icDate = icDate;
        }

        public void setIcDateFloat(double icDateFloat) {
            this.icDateFloat = icDateFloat;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setAppAssetUserIncomeJsonVos(List<SingleGainBean> appAssetUserIncomeJsonVos) {
            this.appAssetUserIncomeJsonVos = appAssetUserIncomeJsonVos;
        }

        public String getIcDate() {
            return icDate;
        }

        public double getIcDateFloat() {
            return icDateFloat;
        }

        public double getAmount() {
            return amount;
        }

        public List<SingleGainBean> getAppAssetUserIncomeJsonVos() {
            return appAssetUserIncomeJsonVos;
        }

        public static class SingleGainBean {
            /**
             * productTitle : 米多利-九鼎股权质押项目19期
             * amount : 2.1918
             */

            private String productTitle;
            private double amount;

            public void setProductTitle(String productTitle) {
                this.productTitle = productTitle;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getProductTitle() {
                return productTitle;
            }

            public double getAmount() {
                return amount;
            }
        }
    }

}
