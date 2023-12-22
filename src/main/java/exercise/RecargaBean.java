package exercise;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import model.ResponseItem;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
@ManagedBean
public class RecargaBean implements Serializable {

    private Double valor;
    private Long vendedor;
    private Long operador;
    private String operadorFiltro;
    private String numeroCelular;
    private Long numeroRecargas;
    private Double valorTotalRecargas;
    private List<String> listaOperadores;

    private List<Recarga> recargas = new ArrayList<>();

    @PostConstruct
    public void init() {
        obtenerRecargas();
    }

    public void realizarRecarga() {

        try {
            URL url = new URL("http://localhost:8081/api/v1/recargas");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"valor\":" + valor +
                    ",\"vendedor\":{\"id\":" + vendedor +
                    "},\"operador\":{\"id\":" + operador +
                    "},\"numeroCelular\":\"" + numeroCelular +
                    "\"}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        operador = null;
        valor = null;
        vendedor = null;
        numeroCelular = null;

        obtenerRecargas();

    }

    public List<ResponseItem> obtenerData() {
        return obtenerData(null);
    }

    public List<ResponseItem> obtenerData(String filtroOperador) {

        StringBuilder response = new StringBuilder();
        String apiUrl;

        recargas.clear();

        if (filtroOperador == null) apiUrl = "http://localhost:8081/api/v1/recargas";
        else apiUrl = "http://localhost:8081/api/v1/recargas?operador=" + filtroOperador;

        try {


            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }

                    System.out.println(response);
                }
            } else {
                System.out.println("Error al realizar la solicitud. Código de respuesta: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseJsonArray(response.toString());
    }

    public void obtenerRecargas() {

        List<ResponseItem> responseItem = obtenerData();

        responseItem.forEach(responseItem1 -> {
                    Recarga recargaTemp = new Recarga(responseItem1.getOperador().getNombre(),
                            String.valueOf(responseItem1.getValor()),
                            responseItem1.getVendedor().getNombre(),
                            responseItem1.getNumeroCelular()
                    );
                    recargas.add(recargaTemp);
                }
        );

        operador = null;
        valor = null;
        vendedor = null;
        numeroCelular = null;

    }

    private List<ResponseItem> parseJsonArray(String jsonArray) {
        Gson gson = new Gson();
        return gson.fromJson(jsonArray, new TypeToken<List<ResponseItem>>() {
        }.getType());
    }

    public List<String> getListaOperadores() {
        return Arrays.asList("Tigo", "Movistar", "Comcel", "Uff");
    }

    public void filtroOperadorSeleccionado() {

        List<ResponseItem> responseItem = obtenerData(operadorFiltro);
        numeroRecargas = (long) responseItem.size();
        valorTotalRecargas = 0.0;
        responseItem.forEach(responseItemTemp -> valorTotalRecargas += responseItemTemp.getValor());

    }
}
