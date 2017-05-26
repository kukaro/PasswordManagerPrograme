package it.pknu.passwordmanager;

import org.database.Database;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jiharu on 2017. 5. 26..
 */

public class DatabaseArray implements Serializable {
    private ArrayList<Database> dbArr;

    {
        dbArr=new ArrayList<>();
    }

    public void add(Database db){
        dbArr.add(db);
    }

    public Database get(int index){
        return dbArr.get(index);
    }

    public int size(){
        return dbArr.size();
    }

    public void remove(int index){
        dbArr.remove(index);
    }
}
