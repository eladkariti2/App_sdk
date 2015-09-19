package com.application.facebook.permissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by elad on 9/14/2015.
 */
public abstract class APFBPermissions {


    public static final List<String> BASIC_PERMISSIONS = Arrays.asList(new String[]{"user_friends","email","user_birthday"});
    public static final List<String> PUBLISH_PERMISSIONS = Arrays.asList(new String[] {"publish_actions"});
    public static final List<String> EXTENDED_READ_PERMISSIONS = Arrays.asList(new String[] {});

    public List<String> getAllPermissions() {
        ArrayList<String> result = new ArrayList<String>();
        List<String> basic = getBasicPermissions();
        List<String> publish = getPublishPermissions();
        List<String> read = getReadPermissions();

        if( basic != null){
            result.addAll(basic);
        }
        if (publish != null) {
            result.addAll(publish);
        }
        if (read != null) {
            result.addAll(read);
        }


        return result;
    }

    public  List<String> getBasicPermissions(){
        return BASIC_PERMISSIONS;
    }

    public abstract List<String> getReadPermissions();

    public abstract List<String> getPublishPermissions();

}
