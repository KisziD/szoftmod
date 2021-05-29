package com;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
		Subscribers subs = new Loader().loadSubscribers();
		Controller c= new Controller();


		while(true){
		    c.checkHomes(subs);
            /*for (Subscriber asd: subs.getSubscribers()) {
                System.out.println(asd.getHomeId());
            }*/
            try {
                TimeUnit.MINUTES.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
