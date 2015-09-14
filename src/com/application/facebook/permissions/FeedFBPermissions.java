package com.application.facebook.permissions;

import java.util.List;

/**
 * Created by elad on 9/14/2015.
 */
public class FeedFBPermissions extends APFBPermissions {

    private static FeedFBPermissions instance;

    private FeedFBPermissions() {}

    public static FeedFBPermissions getInstance() {
        if (instance == null) {
            instance = new FeedFBPermissions();
        }
        return instance;
    }

    @Override
    public List<String> getReadPermissions() {
        return null;
    }

    @Override
    public List<String> getPublishPermissions() {
        return PUBLISH_PERMISSIONS;
    }

}
