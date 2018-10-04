package io.github.nekomiyaxneko.mybms;

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

public class AddStudentActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Button confirm_add= findViewById(R.id.confirm_add);
        confirm_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        String TAG="addtesting";
        Log.d(TAG, "onClick: ");
        switch (view.getId()){
            case R.id.confirm_add:
                Log.d(TAG, "adding");
                EditText new_stu_name=findViewById(R.id.new_stu_name);
                EditText new_stu_id=findViewById(R.id.delete_stu_id);
                EditText new_stu_score=findViewById(R.id.change_stu_score);
                if(TextUtils.isEmpty(new_stu_name.getText())){
                    Toast.makeText(this,"名字不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(new_stu_name.getText().toString().indexOf(" ")!=-1){
                    Toast.makeText(this,"名字不能有空格",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(getlength(new_stu_name.getText().toString())>10){
                    Toast.makeText(this,"名字长度不能超过10个英文字母或者5个中文字",Toast.LENGTH_SHORT).show();
                    break;

                }
                else if(isInteger(new_stu_name.getText().toString())){
                    Toast.makeText(this,"名字不能为纯数字",Toast.LENGTH_SHORT).show();
                    break;
                }

                if(TextUtils.isEmpty(new_stu_id.getText())){
                    Toast.makeText(this,"学号不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(new_stu_id.getText().toString().indexOf(" ")!=-1){
                    Toast.makeText(this,"学号不能有空格",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(!isInteger(new_stu_id.getText().toString())){
                    Toast.makeText(this,"学号必须是整数",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(new_stu_id.getText().toString().length()<=4||new_stu_id.getText().toString().length()>10){
                    Toast.makeText(this,"学号长度不能大于10或小于4",Toast.LENGTH_SHORT).show();
                    break;
                }
                else {
                    List<Student> students= LitePal.where("student_id =?",new_stu_id.getText().toString()).find(Student.class);
                    if(students.size()!=0){
                        Toast.makeText(this,"学号已存在",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if(TextUtils.isEmpty(new_stu_score.getText())){
                    Toast.makeText(this,"成绩不能为空",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(new_stu_score.getText().toString().indexOf(" ")!=-1){
                    Toast.makeText(this,"成绩不能有空格",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(!isInteger(new_stu_score.getText().toString())){
                    Toast.makeText(this,"成绩必须是整数",Toast.LENGTH_SHORT).show();
                    break;
                }
                else if(Integer.parseInt(new_stu_score.getText().toString())<0||Integer.parseInt(new_stu_score.getText().toString())>100){
                    Toast.makeText(this,"成绩必须大于等于0小于等于100",Toast.LENGTH_SHORT).show();
                    break;
                }
                Student student=new Student();
                student.setStudent_Id(new_stu_id.getText().toString());
                student.setName(new_stu_name.getText().toString());
                student.setScore(new_stu_score.getText().toString());
                student.save();
                Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
                super.onBackPressed();
                break;
            default:
                break;
        }
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");//判断是否是整数，是：返回true；
        return pattern.matcher(str).matches();
    }
    public static int getlength(String s)
    {
        int length = 0;
        for(int i = 0; i < s.length(); i++)
        {
            int ascii = Character.codePointAt(s, i);
            if(ascii >= 0 && ascii <=255)
                length++;
            else
                length += 2;

        }
        return length;

    }
}
