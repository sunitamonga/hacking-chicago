package com.hackingchicago.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Donor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	private String lastName;
	private String title;
	private String organization;
	private long donatedAmount;
	private Date joinDate;
	private String portfolioType;
	private String involvement;
	private String history;
	private String expertise;

	public Donor() {}
	
	public Donor(String firstName, String lastName, String title, String organization, long donatedAmount,
			Date joinDate, String portfolioType, String involvement, String history, String expertise) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.organization = organization;
		this.donatedAmount = donatedAmount;
		this.joinDate = joinDate;
		this.portfolioType = portfolioType;
		this.involvement = involvement;
		this.history = history;
		this.expertise = expertise;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public long getDonatedAmount() {
		return donatedAmount;
	}

	public void setDonatedAmount(long donatedAmount) {
		this.donatedAmount = donatedAmount;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getPortfolioType() {
		return portfolioType;
	}

	public void setPortfolioType(String portfolioType) {
		this.portfolioType = portfolioType;
	}

	public String getInvolvement() {
		return involvement;
	}

	public void setInvolvement(String involvement) {
		this.involvement = involvement;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}