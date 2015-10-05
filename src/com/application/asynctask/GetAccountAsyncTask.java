package com.application.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.application.listener.AsyncTaskListener;
import com.application.models.AccountModel;
import com.application.utils.JsonUtil;
import com.application.utils.ServerUtil;

/**
 * Created by elad on 10/5/2015.
 */
public class GetAccountAsyncTask extends AsyncTask<String, Void, AccountModel> {

    private static final String TAG = "GetAccountAsyncTask";
    Class<AccountModel> mLoadedClass;
    Exception mException = null;
    AsyncTaskListener mListener;


    public GetAccountAsyncTask(AsyncTaskListener listener,Class loadedClass)
    {
        mListener = listener;
        mLoadedClass = loadedClass;
    }

    @Override
    protected AccountModel doInBackground(String... params) {
        String url = params[0];
        String json = null;
        AccountModel account = null;

        try {
            Log.d(TAG, "url");
            json = ServerUtil.doGet(url);
        } catch (Exception e) {
            Log.d(TAG, "Exeption: can't load account and create user, message: " + e.getMessage() );
            mException = e;
        }

        account = (AccountModel) JsonUtil.serialize(json, mLoadedClass);

        return account;
    }

    protected void onPostExecute(AccountModel model)
    {
        if(mException != null){
            mListener.handleException(mException);
        }
        else{
            mListener.onTaskComplete(model);
        }
    }

}
