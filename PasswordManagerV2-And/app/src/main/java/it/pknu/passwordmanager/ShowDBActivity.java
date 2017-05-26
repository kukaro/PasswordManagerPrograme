package it.pknu.passwordmanager;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.database.Database;
import org.database.Record;

import java.util.ArrayList;

public class ShowDBActivity extends AppCompatActivity {
    private Database db;
    private TableLayout tblLayout;
    private ArrayList<TableRow> tr;
    /*
     * Initialize Block
     */

    {
        db=null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_db);
        db = (Database) getIntent().getSerializableExtra("db");

        ImageView imgSubmit = (ImageView) findViewById(R.id.imgSubmit);
        ImageView imgClose = (ImageView) findViewById(R.id.imgClose);
        TextView showDBTitle = (TextView) findViewById(R.id.ShowDBTitle);
        showDBTitle.setText("DB Name : " + db.getName());

        tblLayout = (TableLayout) findViewById(R.id.ShowDBBody);
        //Toast.makeText(getApplicationContext(),db.Size()+"",Toast.LENGTH_SHORT).show();
        tr = new ArrayList<>();
        for(int i=0;i<db.Size();i++) {
            tr.add(new TableRow(this));
            EditText etID = new EditText(this);
            etID.setFocusable(false);
            etID.setClickable(false);
            etID.setGravity(Gravity.CENTER);
            etID.setText(db.getRecord(i).getID());
            etID.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
            EditText etPwd = new EditText(this);
            etPwd.setFocusable(false);
            etPwd.setClickable(false);
            etPwd.setGravity(Gravity.CENTER);
            etPwd.setText(db.getRecord(i).getPassword());
            etPwd.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
            EditText etURL = new EditText(this);
            etURL.setFocusable(false);
            etURL.setClickable(false);
            etURL.setGravity(Gravity.CENTER);
            etURL.setText(db.getRecord(i).getURL());
            etURL.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,2.0f));
            tr.get(i).addView(etID);
            tr.get(i).addView(etPwd);
            tr.get(i).addView(etURL);
            tblLayout.addView(tr.get(i));
        }


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
}
