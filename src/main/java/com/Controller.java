package com;

//import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Objects;

public class Controller {

    public void checkHomes(Subscribers subs){
        Session sess;
        Monitor m= new Monitor();
        int tempChange;
        int ac,boiler;
        boolean acState = false, boilerState = false;
        int hour = LocalDateTime.now().getHour();

        for (Subscriber sub: subs.getSubscribers()) {
            for (Temperature temp: sub.getTemperatures()) {
                if(hour>=temp.getBeginTime() && hour<temp.getEndTime()) {
                        sess = m.getSession(sub.getHomeId());
                    if (!Objects.isNull(sess)) {
                        tempChange = checkTemp(temp.getTemperature(), sess.getTemperature(), sub.getHomeId());
                        ac = setAc(tempChange, sess.getAirConditionerState());
                        boiler = setBoiler(tempChange, sess.getBoilerState());

                        if (ac != 0 || boiler != 0) {
                            switch (ac) {
                                case 1:
                                    acState = true;
                                    break;
                                case 0:
                                    acState = sess.getAirConditionerState();
                                    break;
                                case -1:
                                    acState = false;
                                    break;
                            }
                            switch (boiler) {
                                case 1:
                                    boilerState = true;
                                    break;
                                case 0:
                                    boilerState = sess.getBoilerState();
                                    break;
                                case -1:
                                    boilerState = false;
                                    break;
                            }
                            System.out.println(new Driver().sendCommand(sub, boilerState, acState));
                        }
                    }
                }
            }
        }
    }

    private int checkTemp(Double desired, Double current, String homeId){

        try {
            if (desired + (desired * 0.2) < current || desired - (desired * 0.2) > current) {
                PrintWriter pw = new PrintWriter("resources/log.txt");
                pw.write(LocalDateTime.now().toString()+"   Error: homeId: "+homeId+"    Temps: Desired: "+desired+" Measured: "+current);
                pw.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(current+0.2<desired){
            return 1;
        }else if(current-0.2>desired){
            return -1;
        }else{
            return 0;
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private int setBoiler(int tempChange, boolean boilerState){
        if(0 ==tempChange&& boilerState){
            return -1;
        }else if(tempChange==-1 && boilerState){
            return -1;
        }else if(tempChange==1 && !boilerState){
            return 1;
        }else{
            return 0;
        }
    }
    @SuppressWarnings("DuplicatedCode")
    private int setAc(int tempChange, boolean acState){
        if(tempChange==0 && acState){
            return -1;
        }else if(tempChange==1 && acState){
            return -1;
        }else if(tempChange==-1 && !acState){
            return 1;
        }else{
            return 0;
        }
    }


}
