package model;

import lombok.Data;

import java.util.List;

@Data
public class Response{
	private List<ResponseItem> response;
}