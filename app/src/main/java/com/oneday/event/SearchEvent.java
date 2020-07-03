package com.oneday.event;

/**
 * Created by nikhil.v on 7/25/2017.
 */

public class SearchEvent extends BaseEvent {

    public SearchEvent(String text) {
        super(text);
    }

    @Override
    public String getCastedObject() {
        return (String) getObject();
    }

}
