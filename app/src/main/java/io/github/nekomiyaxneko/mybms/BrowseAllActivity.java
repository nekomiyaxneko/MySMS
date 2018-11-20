package io.github.nekomiyaxneko.mybms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;
import java.util.ArrayList;
import java.util.List;

public class BrowseAllActivity extends BaseActivity implements View.OnClickListener{
    private List<Student> studentList=new ArrayList<>();
    private String TAG="browsedebug";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_all);
        Log.d(TAG, "onCreate: start");
        Connector.getDatabase();
        makeList();//刷新list
        show_data();//显示
    }
    private void makeList(){
        studentList=LitePal.findAll(Student.class);
    }
    protected void show_data(){
        //Log.d(TAG, "show_data: showing");
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StudentAdapter adapter=new StudentAdapter(studentList);
        recyclerView.setAdapter(adapter);
        //Log.d(TAG, "show_data: showed");
    }
    @Override
    public void onClick(View v) {

    }

}
