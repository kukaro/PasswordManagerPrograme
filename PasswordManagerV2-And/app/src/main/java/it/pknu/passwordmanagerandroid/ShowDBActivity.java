package it.pknu.passwordmanagerandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.database.Database;
import org.database.Record;
import org.util.AES256Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ShowDBActivity extends AppCompatActivity {
    private Database db;
    private TableLayout tblLayout;
    private ArrayList<TableRow> tr;
    private ArrayList<EditText> etIDArr;
    private ArrayList<EditText> etPwdArr;
    private ArrayList<EditText> etURLArr;
    private ArrayList<Boolean> etIDstat;
    private ArrayList<Boolean> etPwdstat;
    private ArrayList<Boolean> etURLstat;
    private AES256Util aes;
    /*
     * Initialize Block
     */

    {
        etIDstat = new ArrayList<>();
        etPwdstat = new ArrayList<>();
        etURLstat = new ArrayList<>();
        db = null;
        aes = null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_db);
        db = (Database) getIntent().getSerializableExtra("db");

        ImageView imgSubmit = (ImageView) findViewById(R.id.imgSubmit);
        ImageView imgClose = (ImageView) findViewById(R.id.imgClose);
        ImageView imgExport = (ImageView) findViewById(R.id.imgExport);
        ImageView imgEdit = (ImageView) findViewById(R.id.imgEdit);
        TextView showDBTitle = (TextView) findViewById(R.id.ShowDBTitle);
        showDBTitle.setText("DB Name : " + db.getName());

        tblLayout = (TableLayout) findViewById(R.id.ShowDBBody);
        //Toast.makeText(getApplicationContext(),db.Size()+"",Toast.LENGTH_SHORT).show();
        tr = new ArrayList<>();
        etIDArr = new ArrayList<>();
        etPwdArr = new ArrayList<>();
        etURLArr = new ArrayList<>();
        for(int i=0;i<db.Size();i++) {
            tr.add(new TableRow(this));
            EditText etID = new EditText(this);
            etID.setFocusable(false);
            etID.setClickable(false);
            etID.setGravity(Gravity.CENTER);
            etID.setText(db.getRecord(i).getID());
            etID.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
            etIDArr.add(etID);
            etIDstat.add(false);
            EditText etPwd = new EditText(this);
            etPwd.setFocusable(false);
            etPwd.setClickable(false);
            etPwd.setGravity(Gravity.CENTER);
            etPwd.setText(db.getRecord(i).getPassword());
            etPwd.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
            etPwdArr.add(etPwd);
            etPwdstat.add(false);
            EditText etURL = new EditText(this);
            etURL.setFocusable(false);
            etURL.setClickable(false);
            etURL.setGravity(Gravity.CENTER);
            etURL.setText(db.getRecord(i).getURL());
            etURL.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,2.0f));
            etURLArr.add(etURL);
            etURLstat.add(false);
            tr.get(i).addView(etID);
            tr.get(i).addView(etPwd);
            tr.get(i).addView(etURL);
            tblLayout.addView(tr.get(i));
        }
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<db.Size();i++){
                    etIDstat.set(i,!etIDstat.get(i));
                    etIDArr.get(i).setClickable(etIDstat.get(i));
                    etIDArr.get(i).setFocusable(etIDstat.get(i));
                    etPwdstat.set(i,!etPwdstat.get(i));
                    etPwdArr.get(i).setClickable(etPwdstat.get(i));
                    etPwdArr.get(i).setFocusable(etPwdstat.get(i));
                    etURLstat.set(i,!etURLstat.get(i));
                    etURLArr.get(i).setClickable(etURLstat.get(i));
                    etURLArr.get(i).setFocusable(etURLstat.get(i));
                }
            }
        });
        imgExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database temp = db;
                try {
                    aes = new AES256Util(temp.getPassword());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                try {
                    FileOutputStream outFs = openFileOutput(temp.getName() + ".txt", Context.MODE_WORLD_WRITEABLE);
                    outFs.write(aes.aesEncode(temp.getName()).getBytes());
                    outFs.write(",".getBytes());
                    outFs.write(aes.aesEncode(temp.getPassword()).getBytes());
                    outFs.write("\n".getBytes());
                    for(int i=0;i<db.Size();i++){
                        outFs.write(aes.aesEncode(temp.getRecord(i).getID()).getBytes());
                        outFs.write(",".getBytes());
                        outFs.write(aes.aesEncode(temp.getRecord(i).getPassword()).getBytes());
                        outFs.write(",".getBytes());
                        outFs.write(aes.aesEncode(temp.getRecord(i).getURL()).getBytes());
                        outFs.write("\n".getBytes());
                    }
                    outFs.close();
                    File f = new File("/data/data/it.pknu.passwordmanagerandroid/files/"+temp.getName()+".txt");
                    try {
                        changePermissons(f,0777);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
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
                }
            }
        });

        imgSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddRecordActivity.class);
                startActivityForResult(intent,0);
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),SelDBActivity.class);
                outIntent.putExtra("db",db);
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(db.Size()>tr.size()){
            tr.add(new TableRow(this));
            EditText etID = new EditText(this);
            etID.setFocusable(false);
            etID.setClickable(false);
            etID.setGravity(Gravity.CENTER);
            etID.setText(db.getRecord(db.Size()-1).getID());
            etID.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
            EditText etPwd = new EditText(this);
            etPwd.setFocusable(false);
            etPwd.setClickable(false);
            etPwd.setGravity(Gravity.CENTER);
            etPwd.setText(db.getRecord(db.Size()-1).getPassword());
            etPwd.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
            EditText etURL = new EditText(this);
            etURL.setFocusable(false);
            etURL.setClickable(false);
            etURL.setGravity(Gravity.CENTER);
            etURL.setText(db.getRecord(db.Size()-1).getURL());
            etURL.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,2.0f));
            tr.get(db.Size()-1).addView(etID);
            tr.get(db.Size()-1).addView(etPwd);
            tr.get(db.Size()-1).addView(etURL);
            tblLayout.addView(tr.get(db.Size()-1));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            Record rc = new Record(data.getStringExtra("ID"),data.getStringExtra("Pwd"),data.getStringExtra("URL"));
            db.AddRecord(rc);
            Toast.makeText(getApplicationContext(),"Make " + rc.getID(),Toast.LENGTH_SHORT).show();
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
