package com.application.facebook.permissions;

import java.util.List;

/**
 * Created by elad on 9/14/2015.
 */

public class BasicFBPermissions extends APFBPermissions {

    private static BasicFBPermissions instance;

    private BasicFBPermissions() {}

    public static BasicFBPermissions getInstance() {
        if (instance == null) {
            instance = new BasicFBPermissions();
        }
        return instance;
    }

    @Override
    public List<String> getReadPermissions() {
        return null;
    }

    @Override
    public List<String> getPublishPermissions() {
        return null;
    }

}