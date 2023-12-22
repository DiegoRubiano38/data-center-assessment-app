package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Recarga implements Serializable {

    private String operador;
    private String valor;
    private String vendedor;
    private String numeroCelular;

}
