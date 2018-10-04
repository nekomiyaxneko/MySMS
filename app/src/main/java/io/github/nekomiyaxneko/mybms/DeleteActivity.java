package io.github.nekomiyaxneko.mybms;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;
import java.util.regex.Pattern;

public class DeleteActivity extends BaseActivity implements View.OnClickListener{
    static  boolean flag=false;

    static String TAG="deletetesting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Button confirm_delete= findViewById(R.id.confirm_delete);
        confirm_delete.setOnClickListener(this);
        Log.d(TAG, "onCreate: start");
    }

    @Override
    public void onClick(View view){
        List<Student> students;
        switch (view.getId()){
            case R.id.confirm_delete:
                Log.d(TAG, "deleteing");
                EditText delete_stu_id=findViewById(R.id.delete_stu_id);
                if(TextUtils.isEmpty(delete_stu_id.getText())){
                    Toast.makeText(this,"学号不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(delete_stu_id.getText().toString().indexOf(" ")!=-1){
                    Toast.makeText(this,"学号不能有空格",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(!isInteger(delete_stu_id.getText().toString())){
                    Toast.makeText(this,"学号必须是整数",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    students= LitePal.where("student_id =?",delete_stu_id.getText().toString()).find(Student.class);
                    if(students.size()==0){
                        Toast.makeText(this,"学号不存在",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                final Student student=students.get(0);
                AlertDialog.Builder dialog=new AlertDialog.Builder(DeleteActivity.this);
                dialog.setTitle("警告");
                dialog.setMessage("是否删除 [学号:"+student.getStudent_id()+" 名字:"+student.getName()+" 成绩:"+student.getScore()+" ]?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LitePal.delete(Student.class,student.getId());
                        Toast.makeText(DeleteActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                        EditText delete_stu_id=findViewById(R.id.delete_stu_id);
                        delete_stu_id.setText("");
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialog.show();
                break;
            default:
                break;
        }
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");//判断是否是整数，是：返回true；
        return pattern.matcher(str).matches();
    }
}
