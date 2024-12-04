package com.p5k.bacao.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface BaseConstant {

	String KEY_EMPTY = "";
	String KEY_YES = "Y";
	String KEY_NO = "N";

	String DB_ORGAN = "1";
	String DB_INDIV = "2";

	String TRANS_STAT_NEW = "01";
	String TRANS_STAT_INPROCESS = "02";
	String TRANS_STAT_DONE = "03";
	String TRANS_STAT_CANCELLATION = "04";
	String PURCHASE = "PURCHASE";
	String PAYMENT = "PAYMENT";
	String REFUND = "REFUND";
	String STATUS_NEW = "1";
	String STATUS_IN_PROCESS = "2";
	String STATUS_DONE = "3";
	String STATUS_CANCELLATION = "4";

	String DOC_TYPE_REQ = "2";
	String DOC_TYPE_CERTIFICATE = "1";
	String DOC_TYPE_AVATAR = "5";
	String DOC_TYPE_COMMENT = "4";

	String USER_TYPE_ADMIN = "1";
	String USER_TYPE_NORMAL = "2";
	String RADIUS = "RADIUS";

	String APP_STORE = "app_store";
	String APP_STORE_ITEM = "APP_STORE_ITEM";
	String APPL_RECEIPT_SUCCESS = "PURCHASED";
	String CH_STORE = "ch_store";
	String TRANS_COMMISSION_RATE_TYPE = "2";

	String LOCK = "LOCK";
	String UNLOCK = "UNLOCK";

	String AUDITOR = "System";

	String CHG_PASS_NEW_PASS_EMPTY = "The new pass must be not null!";
	String CHG_PASS_NEW_PASS_CONFIRM_EMPTY = "The new pass confirm must be not null!";

	String CHG_PASS_LOGIN_ERROR = "Please login before change pass!";

	String CHG_PASS_NEW_PASS_CONFIRM_EQUAL = "The new pass confirm must be equal new pass!";

	String DATA_NOT_FOUND = "Data not found!";

	String DELIMITER_PATH="/";
	String DOWNLOAD_DOCUMENT_PATH = "/document/download";

	String ERROR="ERROR";

	@Getter
	@AllArgsConstructor
	enum Whether {
		YES(KEY_YES, "Yes"),
		NO(KEY_NO, "No");

		private final String code;
		private final String info;

		public static Whether getByCode(String code) {
			for (Whether item : Whether.values()) {
				if(item.code.equals(code)){
					return item;
				}
			}
			return null;
		}
	}
}
