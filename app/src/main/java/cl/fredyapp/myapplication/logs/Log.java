package cl.fredyapp.myapplication.logs;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;

import cl.fredyapp.myapplication.BuildConfig;


public class Log {


    private long timestamp = System.currentTimeMillis();
    private int versionCode = BuildConfig.VERSION_CODE;
    private String versionName = BuildConfig.VERSION_NAME;
    private String message;
    private String transactionId;
    private String imei;
    private String response;
    private String request;
    private String type;
    private String path;
    private String stacktrace;


    public Log() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }


    @NonNull
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Log.class);
    }
}
