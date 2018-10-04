package io.github.nekomiyaxneko.mybms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private long lastBack = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        String userName=intent.getStringExtra("userName");
        TextView textView=(TextView) findViewById(R.id.text_view1);
        textView.setText("欢迎回来! "+userName);
        //set_rate();
        Button browseAll=(Button) findViewById(R.id.browse_all);//浏览
        browseAll.setOnClickListener(this);
        Button more=(Button) findViewById(R.id.more);//更多
        more.setOnClickListener(this);
        Button change=(Button) findViewById(R.id.change);//修改
        change.setOnClickListener(this);
        Button addData=(Button) findViewById(R.id.add_student);//添加
        addData.setOnClickListener(this);
        Button DeleteData=(Button) findViewById(R.id.delete_student);//删除
        DeleteData.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {//双击退出
        if (lastBack == 0 || System.currentTimeMillis() - lastBack > 2000) {
            Toast.makeText(MainActivity.this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
            lastBack = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.browse_all:
                Intent intent=new Intent(MainActivity.this,BrowseAllActivity.class);
                startActivity(intent);
                break;
            case R.id.more:
                intent=new Intent(MainActivity.this,MoreActivity.class);
                startActivity(intent);
                //set_rate();
                break;

            case R.id.change:
                intent=new Intent(MainActivity.this,ChangeActivity.class);
                startActivity(intent);
                //set_rate();
                break;
            case R.id.add_student:
                intent= new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(intent);
                //set_rate();
                break;

            case R.id.delete_student:
                intent= new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(intent);
                //set_rate();
                break;
            default:
                break;
        }
    }
    public void set_rate(){
        int cotpass=0,cotall=0;
        List<Student> stus= LitePal.findAll(Student.class);
        for(Student i:stus){
            Log.d("findfind", "find_pass: "+i.getName()+" "+i.getScore());
            if(Integer.parseInt(i.getScore())>=60){
                cotpass++;
            }
            cotall++;
        }
        double rate=(double) cotpass/(double) cotall;
        rate=rate*100.0;
        TextView pass_cot=findViewById(R.id.all_cot);
        pass_cot.setText("及格数: "+cotpass);
        TextView all_cot=findViewById(R.id.all_cot);
        all_cot.setText("总人数: "+cotall);
        TextView pass_rate=findViewById(R.id.pass_rate);
        pass_rate.setText("及格率: "+(int)rate+"%");
        ProgressBar progressBar=findViewById(R.id.progressBar);
        int progress=(int)rate;
        progressBar.setProgress(progress);
    }

    @Override
    protected void onStart() {
        set_rate();
        super.onStart();
    }
}
