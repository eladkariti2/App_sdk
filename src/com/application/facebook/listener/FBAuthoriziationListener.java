package com.application.facebook.listener;

import java.io.Serializable;

/**
 * Created by elad on 9/14/2015.
 */
public interface FBAuthoriziationListener extends Serializable {

    public void onError(Exception error);
    public void onSuccess();
    public void onCancel();
}