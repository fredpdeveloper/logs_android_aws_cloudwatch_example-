package cl.fredyapp.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txt_send)
    EditText txtSend;
    @BindView(R.id.btn_send)
    Button btnSend;
    SingletoneGlobal singletoneGlobal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        singletoneGlobal = SingletoneGlobal.getInstance(this);


    }


    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        String stackTrace = UUID.randomUUID().toString();
        singletoneGlobal.logRequest("request",stackTrace,"www.holi.com");
        singletoneGlobal.logResponse("response",stackTrace,"www.holi.com");
    }
}
