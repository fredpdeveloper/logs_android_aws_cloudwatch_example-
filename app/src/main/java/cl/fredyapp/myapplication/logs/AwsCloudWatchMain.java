package cl.fredyapp.myapplication.logs;

import android.content.Context;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.logs.AmazonCloudWatchLogsClient;

import cl.fredyapp.myapplication.BuildConfig;
import cl.fredyapp.myapplication.Utils;


public class AwsCloudWatchMain {
    private AmazonCloudWatchLogsClient client;
    private Context ctx;
    private boolean sendLogs;

    public AwsCloudWatchMain(Context ctx,boolean sendLogs) {
        this.client = iniClientAWS();
        this.ctx    = ctx;
        this.sendLogs    = sendLogs;

    }

    private AmazonCloudWatchLogsClient iniClientAWS() {

        BasicAWSCredentials credentials = new BasicAWSCredentials(BuildConfig.AWS_ACCESS_KEY, BuildConfig.AWS_SECRET_KEY);
        AmazonCloudWatchLogsClient client = new AmazonCloudWatchLogsClient(credentials);
        Regions region = Regions.US_EAST_1;
        client.setRegion(Region.getRegion(region));

        return client;

    }

    /**
     * create log to cloudwatch
     * @param log
     */

    public void createLog(Log log) {
        if(sendLogs){
            String imei = Utils.getIMEI(ctx);
            log.setImei(imei);
            CreateLog createLog = new CreateLog(client, BuildConfig.AWS_GROUP_NAME, imei, log);
            createLog.execute();
        }


    }


    /**
     * Create stream to cloudwatch
     *
     * @param groupName
     * @param streamName
     */
    public void createLogStream(String groupName, String streamName) {
        if(sendLogs){
            CreateLogStream createLogStream = new CreateLogStream(client, groupName, streamName);
            createLogStream.execute();
        }


    }

    /**
     * Create groupname to cloudwatch
     *
     * @param groupName
     */

    public void createGroupStream(String groupName) {
        if(sendLogs){
            CreateLogGroup createLogGroup = new CreateLogGroup(client, groupName);
            createLogGroup.execute();
        }

    }

}