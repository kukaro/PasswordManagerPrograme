package it.pknu.passwordmanagerandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.util.AES256Util;
import org.util.SHA256;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class PasswordVerifyActivity extends AppCompatActivity {
    private static AES256Util aes;

    {
        aes=null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_verify);
        final EditText etMasterPwdVerify = (EditText) findViewById(R.id.etMasterPwd);
        ImageView imgCheck = (ImageView) findViewById(R.id.imgCheck);
        ImageView imgClose = (ImageView) findViewById(R.id.imgClose);

        Intent intent = getIntent();
        final String MasterPwd = getIntent().getStringExtra("dPwd");

        imgCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    aes = new AES256Util(SHA256.encrypt(etMasterPwdVerify.getText().toString()));
                    Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                    try {
                        if(aes.aesDecode(MasterPwd).equals(SHA256.encrypt(etMasterPwdVerify.getText().toString()))) {
                            outIntent.putExtra("Match",true);
                            outIntent.putExtra("MPwd",etMasterPwdVerify.getText().toString());
                        }
                        else {
                            outIntent.putExtra("Match", false);
                        }
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (NoSuchPaddingException e) {
                        e.printStackTrace();
                    } catch (InvalidKeyException e) {
                        e.printStackTrace();
                    } catch (InvalidAlgorithmParameterException e) {
                        e.printStackTrace();
                    } catch (IllegalBlockSizeException e) {
                        e.printStackTrace();
                    } catch (BadPaddingException e) {
                        e.printStackTrace();
                    }
                    setResult(5,outIntent);
                    finish();
                } catch (UnsupportedEncodingException e) { e.printStackTrace();}
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("Match",false);
                setResult(5,outIntent);
                finish();
            }
        });
    }
}
