package cl.fredyapp.myapplication;

import android.content.Context;

import cl.fredyapp.myapplication.logs.AwsCloudWatchMain;
import cl.fredyapp.myapplication.logs.Log;


public class SingletoneGlobal {


    private static SingletoneGlobal instance;
    private AwsCloudWatchMain awsCloudWatchMain;
    private Context context;

    private SingletoneGlobal(Context context) {
        this.context = context;
        this.awsCloudWatchMain = new AwsCloudWatchMain(context,true);

    }

    synchronized static SingletoneGlobal getInstance(Context context) {
        if (instance == null) {
            instance = new SingletoneGlobal(context);
        }
        return instance;
    }

    AwsCloudWatchMain getAwsCloudWatchMain() {
        return awsCloudWatchMain;
    }


    public void logRequest(String request, String stackTrace, String path){
        Log log = new Log();
        log.setRequest(request);
        log.setStacktrace(stackTrace);
        log.setPath(path);
        getAwsCloudWatchMain().createLog(log);
    }

    public void logResponse(String response, String stackTrace, String path){
        Log log = new Log();
        log.setResponse(response);
        log.setStacktrace(stackTrace);
        log.setPath(path);
        getAwsCloudWatchMain().createLog(log);
    }


}
