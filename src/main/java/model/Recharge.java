package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Recharge implements Serializable {

    private String operator;
    private String value;
    private String seller;
    private String cellphoneNumber;

}
