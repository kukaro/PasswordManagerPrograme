package it.pknu.passwordmanagerandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.database.Database;

public class MainActivity extends AppCompatActivity {
    DatabaseArray dbArr;

    /*
     * Initialize block
     */
    {
        dbArr = new DatabaseArray();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layoutNewDB = (LinearLayout) findViewById(R.id.NewDB);
        LinearLayout layoutSelDB = (LinearLayout) findViewById(R.id.SelectDB);

        layoutNewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewDBActivity.class);
                startActivityForResult(intent,0);
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
    }
}
