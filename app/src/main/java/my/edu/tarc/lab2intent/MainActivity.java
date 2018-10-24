package my.edu.tarc.lab2intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_MESSAGE = "my.edu.tarc.lab2intent.MESSAGE";
    private static final int REQUEST_REPLY_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Display UI
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view){
        EditText editTextMessage;
        //link variable to UI
        editTextMessage = findViewById(R.id.editTextMessage);
        //Input validation here
        if(TextUtils.isEmpty(editTextMessage.getText())){
            editTextMessage.setError(getString(R.string.error_message));
            return;
        }
        //Explicit Intent - know exactly which activity to handle
        Intent intent = new Intent(this, SecondActivity.class);
        String stringMessage;
        stringMessage = editTextMessage.getText().toString();
        //Put extra things into the intent
        intent.putExtra(TAG_MESSAGE, stringMessage);
        //I expect this activity to return something
        startActivityForResult(intent, REQUEST_REPLY_CODE);
    }

    //Handle the return value
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //resultCode = result is OK or CANCELLED
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_REPLY_CODE){
            if(resultCode == RESULT_OK){
                TextView textViewReply;
                textViewReply = findViewById(R.id.textViewReply);
                String stringReply = data.getStringExtra(SecondActivity.TAG_REPLY);
                textViewReply.setText(stringReply);
            }
        }
    }
}
