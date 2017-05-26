package it.pknu.passwordmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;


public class NewDBActivity extends AppCompatActivity {

    private static Zxcvbn zx;
    private static Strength st;
    private String ranks;
    private int ranki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_db);

        zx = new Zxcvbn();
        final EditText etID = (EditText) findViewById(R.id.etID);
        final EditText etPwd = (EditText) findViewById(R.id.etPwd);
        final EditText etPwdV = (EditText) findViewById(R.id.etPwdV);
        final ImageView imgOKNO = (ImageView) findViewById(R.id.imgOKNO);
        final TextView tvStatus = (TextView) findViewById(R.id.tvStatus);
        ImageView imgSubmit = (ImageView) findViewById(R.id.imgSubmit);
        ImageView imgClose = (ImageView) findViewById(R.id.imgClose);

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etPwd.getText().toString().equals(etPwdV.getText().toString())){
                /*    st = zx.measure(etPwd.getText().toString());
                    ranki = st.getScore();
                    if (ranki == 0)
                        ranks = "Weak";
                    else if (ranki == 1)
                        ranks = "Fair";
                    else if (ranki == 2)
                        ranks = "Good";
                    else if (ranki == 3)
                        ranks = "Strong";
                    else
                        ranks = "Very strong";
                    if (ranki == 0) {
                        tvStatus.setText("Password is Matched\nBut strength is week");
                        imgOKNO.setImageResource(R.drawable.no);
                    } else {*/
                        tvStatus.setText("Password is matched");
                        imgOKNO.setImageResource(R.drawable.ok);
                    //}
                }
                else{
                    imgOKNO.setImageResource(R.drawable.no);
                    tvStatus.setText("Password miss matching\nMatching Please");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPwdV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etPwd.getText().toString().equals(etPwdV.getText().toString())){
                    /*st = zx.measure(etPwd.getText().toString());
                    ranki = st.getScore();
                    if (ranki == 0)
                        ranks = "Weak";
                    else if (ranki == 1)
                        ranks = "Fair";
                    else if (ranki == 2)
                        ranks = "Good";
                    else if (ranki == 3)
                        ranks = "Strong";
                    else
                        ranks = "Very strong";
                    if (ranki == 0) {
                        tvStatus.setText("Password is Matched\nBut strength is week");
                        imgOKNO.setImageResource(R.drawable.no);
                    } else {*/
                        tvStatus.setText("Password is matched");
                        imgOKNO.setImageResource(R.drawable.ok);
                //    }
                }
                else{
                    imgOKNO.setImageResource(R.drawable.no);
                    tvStatus.setText("Password miss matching\nMatching Please");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imgSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("ID",etID.getText().toString());
                outIntent.putExtra("Pwd",etPwd.getText().toString());
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
