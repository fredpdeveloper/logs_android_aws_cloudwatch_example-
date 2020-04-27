package cl.fredyapp.myapplication.logs;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.logs.AmazonCloudWatchLogsClient;
import com.amazonaws.services.logs.model.CreateLogGroupRequest;

public class CreateLogGroup extends AsyncTask<Void, Void, Void> {

    private String groupName;
    private AmazonCloudWatchLogsClient client;

    CreateLogGroup(AmazonCloudWatchLogsClient client, String groupName) {
        this.groupName = groupName;
        this.client = client;
    }

    @Override
    protected Void doInBackground(Void... params) {

        CreateLogGroupRequest request = new CreateLogGroupRequest();
        request.setLogGroupName(groupName);
        try {
            client.createLogGroup(request);

        } catch (AmazonClientException e) {

            Log.e("AWSClient", "Create Group failed: " + e);
        }
        return null;
    }
}
