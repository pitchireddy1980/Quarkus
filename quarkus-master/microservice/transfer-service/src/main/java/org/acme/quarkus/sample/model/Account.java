package org.acme.quarkus.sample.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "ACCOUNTS")
public class Account extends PanacheEntityBase {

	@Id
	private String num;
	private double balance;

	public Account() {
	}

	public Account(String num, double balance) {
		super();
		this.num = num;
		this.balance = balance;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
