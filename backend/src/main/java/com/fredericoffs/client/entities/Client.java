package com.fredericoffs.client.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Client  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private Double income;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant birthDate;
	private Integer Children;

	public Client() {
	}

	public Client(Long id, String name, String cpf, Double income, Instant birthDate,
			Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		Children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return Children;
	}

	public void setChildren(Integer children) {
		Children = children;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Client client = (Client) o;
		return Objects.equals(id, client.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
