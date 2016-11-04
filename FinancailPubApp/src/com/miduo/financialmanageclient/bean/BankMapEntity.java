package com.miduo.financialmanageclient.bean;

import java.io.Serializable;
 public  class BankMapEntity implements Serializable{
            /**
             * branchBankAddress :
             * payee : Jack
             * bankName : �й�����
             * shortBankNo : 1111
             * bankIco : /res/images/bank/S_BOC.png
             * bankCardId : 99
             */

            private String branchBankAddress;
            private String payee;
            private String bankName;
            private String shortBankNo;
            private String bankIco;
            private int bankCardId;

            public void setBranchBankAddress(String branchBankAddress) {
                this.branchBankAddress = branchBankAddress;
            }

            public void setPayee(String payee) {
                this.payee = payee;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public void setShortBankNo(String shortBankNo) {
                this.shortBankNo = shortBankNo;
            }

            public void setBankIco(String bankIco) {
                this.bankIco = bankIco;
            }

            public void setBankCardId(int bankCardId) {
                this.bankCardId = bankCardId;
            }

            public String getBranchBankAddress() {
                return branchBankAddress;
            }

            public String getPayee() {
                return payee;
            }

            public String getBankName() {
                return bankName;
            }

            public String getShortBankNo() {
                return shortBankNo;
            }

            public String getBankIco() {
                return bankIco;
            }

            public int getBankCardId() {
                return bankCardId;
            }
        }
    