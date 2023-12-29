package model;

import lombok.Data;

@Data
public class ResponseItem{
	private Seller seller;
	private double value;
	private int id;
	private Operator operator;
	private String cellphoneNumber;
}