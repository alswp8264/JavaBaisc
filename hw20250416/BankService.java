package app;

import java.util.LinkedList;


public class BankService {

	private LinkedList<Bank> bankList = new LinkedList<>();
	
	public boolean isIdValid(String account) {
		for(Bank bank : bankList) {
			if(bank.getAccount().equals(account)) {
				return false;
			}
		}
		return true;
	}
	
	public LinkedList<Bank> getBackList() {

		return bankList;
	}
	
	public boolean registBank(String account, String name, int value) {
		bankList.add(new Bank(account, name, value));
		return true;
	}
	
	public boolean DepositBank(String account, int value) {
		for(Bank bank : bankList) {
			if(bank.getAccount().equals(account)) {
				int setvalue = bank.getValue() + value;
				bank.setValue(setvalue);
				return false;
			}
		}
		return true;
	}
	
	public boolean WithdrawalBank(String account, int value) {
		for(Bank bank : bankList) {
			if(bank.getAccount().equals(account)) {
				if(bank.getValue() > value) {
					int setvalue = bank.getValue() - value;
					bank.setValue(setvalue);
					return false;
				}
			}
		}
		return true;
	}
}
