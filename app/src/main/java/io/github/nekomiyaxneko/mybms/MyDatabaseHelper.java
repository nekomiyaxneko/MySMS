package io.github.nekomiyaxneko.mybms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_STUDENT = "create table Student ("
            + "stu_id integer primary key autoincrement, "
            + "stu_name text, "
            + "stu_score text)";

    private Context mContext;
    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("debugg", "onCreating");
        db.execSQL(CREATE_STUDENT);
        Log.d("debugg", "onCreated");
        Toast.makeText(mContext,"Creat succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Student");
        onCreate(db);
    }
}
