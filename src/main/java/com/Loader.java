package com;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Loader implements ILoader{
    @Override
    public Subscribers loadSubscribers() {
        Subscribers subscribers = new Subscribers();
        try {
            FileReader reader = new FileReader("resources/subscribers.json");
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject =(JSONObject) jsonParser.parse(reader);

            List<JSONObject> subs = (List<JSONObject>)jsonObject.get("subscribers");
            for (JSONObject sub : subs) {
                ArrayList<Temperature> temps = new ArrayList<>();
                JSONArray tempsObj =(JSONArray) sub.get("temperatures");
                for (Object temp:tempsObj.toArray()) {
                    JSONObject obj= (JSONObject)temp;
                    Temperature temperature=new Temperature((String)obj.get("period"),Double.parseDouble(obj.get("temperature").toString()));
                    temps.add(temperature);
                }
                subscribers.addSubscriber(new Subscriber(
                        sub.get("subscriber").toString(),
                        sub.get("homeId").toString(),
                        sub.get("boilerType").toString(),
                        sub.get("airConditionerType").toString(),
                        temps
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return subscribers;
    }
}
