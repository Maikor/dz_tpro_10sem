package com.springapp.adapterPattern;

/**
 * Created by mkorshun on 4/23/2015.
 */
public class MikeLoggingAdapter implements logging {

    private MikeLogging m;

    public MikeLoggingAdapter (MikeLogging m) { this.m = m;}

    public void store_info() {
        m.showAction();
    }
}
