package it.pknu.passwordmanagerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Intent intent = getIntent();
        final EditText etID = (EditText) findViewById(R.id.etID);
        final EditText etPwd = (EditText) findViewById(R.id.etPwd);
        final EditText etURL = (EditText) findViewById(R.id.etURL);

        ImageView imgSubmit = (ImageView) findViewById(R.id.imgSubmit);
        ImageView imgClose = (ImageView) findViewById(R.id.imgClose);

        imgSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),ShowDBActivity.class);
                outIntent.putExtra("ID",etID.getText().toString());
                outIntent.putExtra("Pwd",etPwd.getText().toString());
                outIntent.putExtra("URL",etURL.getText().toString());
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
