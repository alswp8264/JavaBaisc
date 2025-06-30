package app;

import java.util.LinkedList;
import java.util.Scanner;

import bank.Bank;
import bank.BankService;

public class BankApplication {
	Scanner sc = new Scanner(System.in);
	BankService bs = new BankService();
	
	public static void main(String[] args) {
		BankApplication bk = new BankApplication();
		bk.menu();
	}
	
	public void menu() {
		int menu = 1;
		do {
			menu = menuSet();
			switch(menu) {
			case 1 : menuBankCreate(); break;
			case 2 : menuBackList(); break;
			case 3 : menuDeposit(); break;
			case 4 : menuWithdrawal(); break;
			case 5 : System.out.println("프로그램 종료"); break;
			default : System.out.println("잘못된 입력입니다.");
			
			}
		}while(menu != 5);
	}
	
	public void menuWithdrawal() {
		System.out.println("-----------------");
		System.out.println("출금");
		System.out.println("-----------------");
		System.out.print("계좌번호 : ");
		String account = sc.next();
		System.out.print("출금액 : ");
		int value = sc.nextInt();
		if(!bs.WithdrawalBank(account, value)) {
			System.out.println("예금에 성공하였습니다");
		}else {
			System.out.println("예금에 실패했습니다");
		}
	}
	
	public void menuDeposit() {
		System.out.println("-----------------");
		System.out.println("예금");
		System.out.println("-----------------");
		System.out.print("계좌번호 : ");
		String account = sc.next();
		int value;
		do {
			System.out.print(">> 초기입금액 : ");
			value = sc.nextInt();
		}while(value < 0);
		
		if(!bs.DepositBank(account, value)) {
			System.out.println("예금에 성공하였습니다");
		}else {
			System.out.println("예금에 실패했습니다");
		}
	}
	
	public void menuBankCreate() {
		System.out.println("-----------------");
		System.out.println("계좌 생성");
		System.out.println("-----------------");
		String account;
		do {
			System.out.print(">> 계좌번호 : ");
			account = sc.next();
		}while(!bs.isIdValid(account));
		
		System.out.print(">> 계좌주 : ");
		String name = sc.next();
		
		int value;
		do {
			System.out.print(">> 초기입금액 : ");
			value = sc.nextInt();
		}while(value < 0);
		
		
		if(bs.registBank(account, name, value)) {
			System.out.println("회원가입이 되었습니다");
		}else {
			System.out.println("회원가입이 실패하였습니다.");
		}
	}
	
	public void menuBackList() {
		System.out.println("-----------------");
		System.out.println("계좌 목록");
		System.out.println("-----------------");
		LinkedList<Bank> bankList = bs.getBackList();
		if(bankList.isEmpty()) {
			System.out.println("가입한 회원이 없습니다");
		}else {
			for(Bank bank : bankList) {
				System.out.println(bank.toString());
			}
		}
		System.out.println("------------------------------------------");
	}
	
	public int menuSet() {
		String[] menu = {"계좌생성","계좌목록","예금","출금","종룍"};
		
		System.out.println("-------------------------------------------------");
		for(int i=0; i<menu.length; i++) {
			if(i == menu.length-1) {
				System.out.print((i+1) + "." + menu[i]);
			}else {
				System.out.print((i+1) + "." + menu[i] + " | ");
			}
		}
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.print("선택 > ");
		int menunum = sc.nextInt();
		return menunum;
	}
}
