package io.github.nekomiyaxneko.mybms;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;
import java.util.regex.Pattern;

public class ChangeActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Button confirm_change= findViewById(R.id.confirm_change);
        confirm_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        List<Student> students;
        String TAG="changetesting";
        Log.d(TAG, "onClick: ");
        switch (view.getId()){
            case R.id.confirm_change:
                Log.d(TAG, "changing");
                EditText change_stu_id=findViewById(R.id.delete_stu_id);
                EditText change_stu_score=findViewById(R.id.change_stu_score);


                if(TextUtils.isEmpty(change_stu_id.getText())){
                    Toast.makeText(this,"学号不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(change_stu_id.getText().toString().indexOf(" ")!=-1){
                    Toast.makeText(this,"学号不能有空格",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(!isInteger(change_stu_id.getText().toString())){
                    Toast.makeText(this,"学号必须是整数",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    students= LitePal.where("student_id =?",change_stu_id.getText().toString()).find(Student.class);
                    if(students.size()==0){
                        Toast.makeText(this,"学号不存在",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if(TextUtils.isEmpty(change_stu_score.getText())){
                    Toast.makeText(this,"成绩不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(change_stu_score.getText().toString().indexOf(" ")!=-1){
                    Toast.makeText(this,"成绩不能有空格",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(!isInteger(change_stu_score.getText().toString())){
                    Toast.makeText(this,"成绩必须是整数",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(Integer.parseInt(change_stu_score.getText().toString())<0||Integer.parseInt(change_stu_score.getText().toString())>100){
                    Toast.makeText(this,"成绩必须大于等于0小于等于100",Toast.LENGTH_SHORT).show();
                    break;
                }
                final Student student=students.get(0);
                final String prescore=student.getScore();
                student.setScore(change_stu_score.getText().toString());
                AlertDialog.Builder dialog=new AlertDialog.Builder(ChangeActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("确认将 [ 学号: "+student.getStudent_id()+" 名字: "+student.getName()+" ] 的原成绩: "+prescore+" 修改为 "+change_stu_score.getText().toString()+" ?");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        student.save();
                        Toast.makeText(ChangeActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        finish();
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
