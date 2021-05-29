package com;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Monitor implements IMonitor{
    @Override
    public Session getSession(String homeId) {
        Session session=null;
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://193.6.19.58:8182/smarthome/"+homeId))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(response.body());
            if (json.get("sessionId")==null){
                return null;
            }
            session = new Session(
                    json.get("sessionId").toString(),
                    Double.parseDouble(json.get("temperature").toString()),
                    Boolean.parseBoolean( json.get("boilerState").toString()),
                    Boolean.parseBoolean(json.get("airConditionerState").toString())
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }
}
