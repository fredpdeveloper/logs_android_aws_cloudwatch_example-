package cl.fredyapp.myapplication.logs;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.logs.AmazonCloudWatchLogsClient;
import com.amazonaws.services.logs.model.CreateLogStreamRequest;

public class CreateLogStream extends AsyncTask<Void, Void, Void> {
    private String groupName;
    private String streamName;
    private AmazonCloudWatchLogsClient client;

    CreateLogStream(AmazonCloudWatchLogsClient client, String groupName, String streamName) {
        this.groupName = groupName;
        this.streamName = streamName;
        this.client = client;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        CreateLogStreamRequest request = new CreateLogStreamRequest();
        request.setLogGroupName(groupName);
        request.setLogStreamName(streamName);
        try {
            client.createLogStream(request);
        } catch (AmazonClientException e) {
            Log.e("AWSClient", "Create Stream failed: " + e);
        }
        return null;
    }
}
