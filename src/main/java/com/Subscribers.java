package com;

import java.util.ArrayList;

public class Subscribers {
    ArrayList<Subscriber> subscribers = new ArrayList<>();

    public ArrayList<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(ArrayList<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public void addSubscriber(Subscriber sub){subscribers.add(sub);};

    public Subscriber getSubscriber(int id){
        return subscribers.get(id);
    }
}
