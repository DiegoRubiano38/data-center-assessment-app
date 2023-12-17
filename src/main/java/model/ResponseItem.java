package model;

import lombok.Data;

@Data
public class ResponseItem{
	private Vendedor vendedor;
	private double valor;
	private int id;
	private Operador operador;
}