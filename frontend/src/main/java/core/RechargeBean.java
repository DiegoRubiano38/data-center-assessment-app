package core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import model.Recharge;
import model.ResponseItem;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Named
@ViewScoped
@ManagedBean
public class RechargeBean implements Serializable {

    private Double value;
    private Long seller;
    private Long operator;
    private String operatorFilter;
    private String cellphoneNumber;
    private Long rechargeCount;
    private Double totalRecharges;
    private List<String> operatorList;
    private List<Recharge> recharges = new ArrayList<>();

    @PostConstruct
    public void init() {
        getAllRecharges();
    }

    public void doRecharge() {

        try {
            URL url = new URL("http://localhost:8081/api/v1/recharge");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"value\":" + value +
                    ",\"seller\":{\"id\":" + seller +
                    "},\"operator\":{\"id\":" + operator +
                    "},\"cellphoneNumber\":\"" + cellphoneNumber +
                    "\"}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        operator = null;
        value = null;
        seller = null;
        cellphoneNumber = null;

        getAllRecharges();

    }

    public List<ResponseItem> getData() {
        return getData(null);
    }

    public List<ResponseItem> getData(String operatorFilter) {

        StringBuilder response = new StringBuilder();
        String apiUrl;

        recharges.clear();

        if (operatorFilter == null) apiUrl = "http://localhost:8081/api/v1/recharge";
        else apiUrl = "http://localhost:8081/api/v1/recharge?operator=" + operatorFilter;

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
                System.out.println("Something wrong happened. Status code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseJsonArray(response.toString());
    }

    public void getAllRecharges() {

        List<ResponseItem> responseItem = getData();

        responseItem.forEach(responseItem1 -> {
                    Recharge tempRecharge = new Recharge(responseItem1.getOperator().getName(),
                            String.valueOf(responseItem1.getValue()),
                            responseItem1.getSeller().getName(),
                            responseItem1.getCellphoneNumber()
                    );
                    recharges.add(tempRecharge);
                }
        );

        operator = null;
        value = null;
        seller = null;
        cellphoneNumber = null;

    }

    private List<ResponseItem> parseJsonArray(String jsonArray) {

        Gson gson = new Gson();
        return gson.fromJson(jsonArray, new TypeToken<List<ResponseItem>>() {
        }.getType());

    }

    public List<String> getOperatorList() {
        return Arrays.asList("Tigo", "Movistar", "Comcel", "Uff");
    }

    public void selectedOperatorFilter() {

        List<ResponseItem> responseItem = getData(operatorFilter);
        rechargeCount = (long) responseItem.size();
        totalRecharges = 0.0;
        responseItem.forEach(responseItemTemp -> totalRecharges += responseItemTemp.getValue());

    }
}
