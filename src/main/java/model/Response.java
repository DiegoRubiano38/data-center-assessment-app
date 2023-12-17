package model;

import java.util.List;
import lombok.Data;

@Data
public class Response{
	private List<ResponseItem> response;
}