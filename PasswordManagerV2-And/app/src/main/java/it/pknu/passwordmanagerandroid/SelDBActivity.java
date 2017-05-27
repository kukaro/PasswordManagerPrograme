package it.pknu.passwordmanagerandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.database.Database;

import java.util.ArrayList;

public class SelDBActivity extends AppCompatActivity {
    private DatabaseArray dbArr;
    private ArrayList<LinearLayout> IvArr;
    private ArrayList<LinearLayout.LayoutParams> params;
    private ArrayList<TextView> TvArr;

    /*
     * Initialize Block
     */
    {
        dbArr = null;
        TvArr = null;
    }

    /*
     * Activity onCreate Handle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_db);
        LinearLayout selDBBody = (LinearLayout) findViewById(R.id.SelDBBody);
        Intent inIntent = getIntent();

        ImageView imgSubmit = (ImageView) findViewById(R.id.imgSubmit);
        ImageView imgClose = (ImageView) findViewById(R.id.imgClose);

        dbArr = (DatabaseArray) getIntent().getSerializableExtra("dbArr");
        IvArr = new ArrayList<>();
        params = new ArrayList<>();
        TvArr = new ArrayList<>();
        Toast.makeText(getApplicationContext(),dbArr.size()+"",Toast.LENGTH_SHORT).show();
        for(int i=0;i<5;i++){
            IvArr.add(new LinearLayout(this));
            IvArr.get(i).setOrientation(LinearLayout.HORIZONTAL);
            params.add(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,0.2f));
            selDBBody.addView(IvArr.get(i),params.get(i));
        }
        for(int i=0;i<dbArr.size();i++){
            IvArr.get(i).setBackgroundResource(R.drawable.round_background);
            ImageView tempiv = new ImageView(this);
            TvArr.add(new TextView(this));
            TvArr.get(i).setText("DB Name:"+dbArr.get(i).getName()+"\nRecord:"+dbArr.get(i).Size());
            TvArr.get(i).setTextColor(Color.WHITE);
            TvArr.get(i).setTextSize(28);
            TvArr.get(i).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,0.2f));
            tempiv.setImageResource(R.drawable.file_open);
            tempiv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,0.8f));
            IvArr.get(i).addView(tempiv,tempiv.getLayoutParams());
            IvArr.get(i).addView(TvArr.get(i),TvArr.get(i).getLayoutParams());
            final int temp_int = i;
            IvArr.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),ShowDBActivity.class);
                    intent.putExtra("db",dbArr.get(temp_int));
                    startActivityForResult(intent,0);
                }
            });
        }
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent =  new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("dbArr",dbArr);
                setResult(10,outIntent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(int i=0;i<dbArr.size();i++){
            TvArr.get(i).setText("DB Name:"+dbArr.get(i).getName()+"\nRecord:"+dbArr.get(i).Size());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            for(int i=0;i< dbArr.size();i++){
                if(dbArr.get(i).getName().equals(((Database)data.getSerializableExtra("db")).getName())){
                    //Toast.makeText(getApplicationContext(),dbArr.get(i).getName(),Toast.LENGTH_SHORT).show();
                    dbArr.remove(i);
                }
            }
            dbArr.add((Database)data.getSerializableExtra("db"));
        }
    }
}
