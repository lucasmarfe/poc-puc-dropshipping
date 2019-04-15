package com.store.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application
{
    @Override
    public Set<Object> getSingletons()
    {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new Shipping());
        singletons.add(new Shopping());
        singletons.add(new AuthFilter());
        singletons.add(new Authentication());
        return singletons;
    }
}
