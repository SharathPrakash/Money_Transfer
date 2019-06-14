package com.src.main.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public enum MoneyUtil {

	INSTANCE;

	public static final BigDecimal zeroAmount = new BigDecimal(0).setScale(4, RoundingMode.HALF_EVEN);

	/**
	 * To validate the currency 
	 */
	public boolean validateCcyCode(String inputCcyCode) {
		try {
			Currency instance = Currency.getInstance(inputCcyCode);
			System.out.println("Validate Currency Code: " + instance.getSymbol());
			return instance.getCurrencyCode().equals(inputCcyCode);
		} catch (Exception e) {
			System.out.println("Cannot parse the input Currency Code, Validation Failed: " + e);
		}
		return false;
	}

}
