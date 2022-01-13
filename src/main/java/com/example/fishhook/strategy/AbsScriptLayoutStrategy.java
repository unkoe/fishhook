package com.example.fishhook.strategy;

/**
 * @author jojojo
 */
public abstract class AbsScriptLayoutStrategy implements ScriptLayoutStrategy {

    protected String location;


    protected AbsScriptLayoutStrategy() {
        location("default");
    }

    @Override
    public boolean location(String location) {
        this.location = location;
        return true;
    }

    public String getLocation() {
        return location;
    }
}
