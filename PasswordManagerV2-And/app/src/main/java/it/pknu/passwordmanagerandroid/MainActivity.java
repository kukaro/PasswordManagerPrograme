package it.pknu.passwordmanagerandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.OpenFileDialog.OnFileSelectedListener;
import org.OpenFileDialog.OnNotifyEventListener;
import org.OpenFileDialog.OpenDialog;
import org.database.Database;
import org.database.Record;
import org.util.AES256Util;
import org.util.SHA256;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {
    DatabaseArray dbArr;
    MainActivity self;

    private FileInputStream inFs;
    private String dID;
    private String dPwd;
    private AES256Util aes;
    /*
     * Initialize block
     */
    {
        dID = "";
        dPwd = "";
        inFs = null;
        dbArr = new DatabaseArray();
        self = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layoutNewDB = (LinearLayout) findViewById(R.id.NewDB);
        LinearLayout layoutSelDB = (LinearLayout) findViewById(R.id.SelectDB);
        LinearLayout layoutOpenDB = (LinearLayout) findViewById(R.id.OpenDB);

        layoutNewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewDBActivity.class);
                startActivityForResult(intent,0);
            }
        });

        layoutOpenDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog _Dialog;
                _Dialog = new OpenDialog(self);

                OnFileSelectedListener _OnFileSelected = new OnFileSelectedListener() {
                    @Override
                    public void onSelected(String path, String fileName) {
                        if (fileName.length() > 0) {
                            try {

                                inFs = openFileInput(fileName);
                                char ch;
                                while((ch = (char)inFs.read())!=(int)','){
                                    dID+=ch;
                                }
                                while((ch = (char)inFs.read())!=(int)'\n'){
                                    dPwd+=ch;
                                }
                                Intent intent = new Intent(getApplicationContext(),PasswordVerifyActivity.class);
                                intent.putExtra("dPwd",dPwd);
                                startActivityForResult(intent,0);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

                OnNotifyEventListener _OnCanceled = new OnNotifyEventListener() {
                    @Override
                    public void onNotify(Object sender) {
                        Toast.makeText(getApplicationContext(), "선택이 취소 되었습니다.", Toast.LENGTH_LONG).show();
                    }
                };
                _Dialog.setOnFileSelected(_OnFileSelected);
                _Dialog.setOnCanceled(_OnCanceled);
                _Dialog.Show();
            }
        });

        layoutSelDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelDBActivity.class);
                intent.putExtra("dbArr",dbArr);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Database db = new Database(data.getStringExtra("ID"),data.getStringExtra("Pwd"));
            dbArr.add(db);
            Toast.makeText(getApplicationContext(),"Make " + db.getName()+"|"+dbArr.size(),Toast.LENGTH_SHORT).show();
        }
        else if(resultCode == 10){
            dbArr = (DatabaseArray)data.getSerializableExtra("dbArr");
        }
        else if(resultCode == 5){
            Toast.makeText(getApplicationContext(),data.getBooleanExtra("Match",false)+"",Toast.LENGTH_SHORT).show();
            if(data.getBooleanExtra("Match",false)==true){

                try {
                    aes = new AES256Util(SHA256.encrypt(data.getStringExtra("MPwd")));
                    //Toast.makeText(getApplicationContext(),data.getStringExtra("MPwd"),Toast.LENGTH_SHORT).show();
                    Database tDB = new Database(aes.aesDecode(dID),aes.aesDecode(dPwd),true);

                    int ch=0;
                    String rID="";
                    String rPwd="";
                    String rURL="";

                    while(true){
                        //Toast.makeText(getApplicationContext(),(int)ch+"",Toast.LENGTH_SHORT).show();
                        while((ch = inFs.read())!=(int)','){
                            if(ch==-1) break;
                            rID+=(char)ch;
                        }
                        if(ch==-1) break;
                        rID = aes.aesDecode(rID);
                        while((ch = inFs.read())!=(int)','){
                            if(ch==-1) break;
                            rPwd+=(char)ch;
                        }
                        if(ch==-1) break;
                        rPwd = aes.aesDecode(rPwd);
                        while((ch = inFs.read())!=(int)'\n'){
                            if(ch==-1) break;
                            rURL+=(char)ch;
                        }
                        if(ch==-1) break;
                        rURL = aes.aesDecode(rURL);
                        tDB.AddRecord(new Record(rID,rPwd,rURL));
                        rID="";
                        rPwd="";
                        rURL="";
                    }
                    while(inFs.read()!=-1);
                    //Toast.makeText(getApplicationContext(),(int)ch+"",Toast.LENGTH_SHORT).show();
                    dbArr.add(tDB);
                    inFs.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
    public int changePermissons(File path, int mode) throws Exception {
        Class<?> fileUtils = Class.forName("android.os.FileUtils");
        Method setPermissions = fileUtils.getMethod("setPermissions",
                String.class, int.class, int.class, int.class);

        return (Integer) setPermissions.invoke(null, path.getAbsolutePath(),
                mode, -1, -1);
    }
}

