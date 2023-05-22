package com.salon.utils;


public class APIRoutes {

	public static final String BASE_API_URL = "/api/v1";

	public static final String MASTER_BASE_URL = BASE_API_URL+ "/master";

	public static final String SAVE_TAX_URL="tax/savetax";

	public static final String GET_ALL_TAX_URL="tax/getalltax";

	public static final String SAVE_ITEM_URL="item/saveitem";

	public static final String SAVE_INVOICE_URL="invoice/saveinvoice/{companyId}/{customerId}";

	public static final String GET_ALL_ITEM_URL="item/getallitem";
	
	public static final String CUSTOMER_BASE_URL = BASE_API_URL+ "/customer";
	
	public static final String SAVE_CUSTOMER_URL="/save";
	
	public static final String UPDATE_CUSTOMER_URL="/update/{id}";
	
	public static final String GET_ALL_CUSTOMER_URL="/get";
	
	public static final String SAVE_COMPANY_URL="company/savecompany";

	public static final String SAVE_CURRENCY_URL="currency/savecurrency";

	public static final String GET_ALL_CURRENCY_URL="currency/getallcurrency";

	public static final String GET_ALL_INVOICE_URL="invoice/getallinvoice";

	public static final String GET_INVOICE_NUMBER_URL="invoice/getinvoicenumber";

	public static final String GENERATE_BILL="invoice/generatebill/{companyId}/{customerId}";

	public static final String CALCULATE_FINAL_AMOUNT="tax/calculation";

	public static final String GET_CUSTOMER_BY_ID="getcustomer/{id}";

	private APIRoutes() {

	}

}
