package bank;

public class Bank {
	private String account;
	private String name;
	private int value;
	
	public Bank(String account, String name, int value) {
		this.account = account;
		this.name = name;
		this.value = value;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return account + "\t" + name + "\t" + value;
	}
	
	
}
