package com.ecom.domain;

import java.io.Serializable;

public class PaymentInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bankName;
	private String bankCode;
	private String iban;
	private String bic;
	private String blz;
	private String accountNumber;
	private String referenceName;
	private String referenceText;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getReferenceText() {
		return referenceText;
	}
	public void setReferenceText(String referenceText) {
		this.referenceText = referenceText;
	}
	public String getReferenceName() {
		return referenceName;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	public String getBlz() {
		return blz;
	}
	public void setBlz(String blz) {
		this.blz = blz;
	}
	
}
