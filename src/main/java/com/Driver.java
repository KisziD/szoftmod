package com;

import sun.tools.jconsole.JConsole;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Driver implements IDriver{
    private LinkedHashMap<String,String> boiler = new LinkedHashMap<String,String>(){{
        put("Boiler 1200W Start", "bX3434");
        put("Boiler 1200W Stop", "bX1232");
        put("Boiler p5600 Start", "cX7898");
        put("Boiler p5600 Stop", "cX3452");
        put("Boiler tw560 Start", "dX3422");
        put("Boiler tw560 Stop", "dX111");
        put("Boiler 1400L Start", "kx8417");
        put("Boiler 1400L Stop", "kx4823");
    }};
    private LinkedHashMap<String,String> ac = new LinkedHashMap<String,String>(){{
        put("Air p5600 Start", "bX5676");
        put("Air p5600 Stop", "bX3421");
        put("Air c3200 Start", "cX3452");
        put("Air c3200 Stop", "cX5423");
        put("Air rk110 Start", "eX1111");
        put("Air rk110 Stop", "eX2222");
    }};

    @Override
    public int sendCommand(Subscriber subs, Boolean boilerState, Boolean acState) {

        String homeId= subs.getHomeId();
        String boilerCommand;
        String acCommand;
        String post;

        if(boilerState){
                boilerCommand= boiler.get(subs.getBoilerType()+" Start");
            }else{
                boilerCommand= boiler.get(subs.getBoilerType()+" Stop");
            }


            if (acState){
                acCommand= ac.get(subs.getAirConditionerType()+" Start");
            }else{
                acCommand= ac.get(subs.getAirConditionerType()+" Stop");
            }
            if(Objects.isNull(boilerCommand)){
                post = "{ \"homeId\":\""+homeId+"\", \"airConditionerCommand\":\""+acCommand+"\" }";
            }else if(Objects.isNull(acCommand)){
                post = "{ \"homeId\":\""+homeId+"\", \"boilerCommand\":\""+boilerCommand+"\"}";
            }else{
                post = "{ \"homeId\":\""+homeId+"\", \"boilerCommand\":\""+boilerCommand+"\", \"airConditionerCommand\":\""+acCommand+"\" }";
            }


        //System.out.println("\t\t\t"+post);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://193.6.19.58:8182/smarthome/"+homeId))
                    .POST(HttpRequest.BodyPublishers.ofString(post))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return Integer.parseInt(response.body());

        }catch (Exception e){
            e.printStackTrace();
        }
        return 101;
    }
}
