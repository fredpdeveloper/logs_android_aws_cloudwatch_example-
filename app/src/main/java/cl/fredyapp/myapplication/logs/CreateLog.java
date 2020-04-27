package cl.fredyapp.myapplication.logs;


import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.logs.AmazonCloudWatchLogsClient;
import com.amazonaws.services.logs.model.DescribeLogStreamsRequest;
import com.amazonaws.services.logs.model.DescribeLogStreamsResult;
import com.amazonaws.services.logs.model.InputLogEvent;
import com.amazonaws.services.logs.model.PutLogEventsRequest;

import java.util.Collections;

public class CreateLog extends AsyncTask<Void, Void, Void> {
    private String groupName;
    private String streamName;
    private AmazonCloudWatchLogsClient client;
    private String log;
    private long timestamp;

    CreateLog(AmazonCloudWatchLogsClient client, String groupName,
              String streamName, cl.fredyapp.myapplication.logs.Log log) {
        this.groupName = groupName;
        this.streamName = streamName;
        this.client = client;
        this.log = log.toString();
        this.timestamp = log.getTimestamp();
    }

    @Override
    protected Void doInBackground(Void... voids) {

        // A sequence token is required to put a log event in an existing stream.
        // Look up the stream to find its sequence token.
        String sequenceToken = getNextSequenceToken(groupName, streamName);
        // Build a JSON log using the EmbeddedMetricFormat.
        InputLogEvent inputLogEvent = new InputLogEvent();
        inputLogEvent.setMessage(log);
        inputLogEvent.setTimestamp(timestamp);


        // Specify the request parameters.

        PutLogEventsRequest putLogEventsRequest = new PutLogEventsRequest();
        putLogEventsRequest.setLogEvents(Collections.singletonList(inputLogEvent));
        putLogEventsRequest.setLogGroupName(groupName);
        putLogEventsRequest.setLogStreamName(streamName);
        putLogEventsRequest.setSequenceToken(sequenceToken);


        try {
            client.putLogEvents(putLogEventsRequest);
        } catch (AmazonClientException e) {
            Log.e("AWSClient", "Create log failed: " + e);

        }

        return null;
    }

    /**
     * Search for the token associated with a specific streamName
     *
     * @param groupName
     * @param streamName
     * @return
     */
    private String getNextSequenceToken(String groupName, String streamName) {

        DescribeLogStreamsRequest request = new DescribeLogStreamsRequest();
        request.setLogGroupName(groupName);
        request.setLogStreamNamePrefix(streamName);
        DescribeLogStreamsResult response = client.describeLogStreams(request);

        // Assume that a single stream is returned since a specific stream name was
        // specified in the previous request.
        return response.getLogStreams().get(0).getUploadSequenceToken();
    }
}
