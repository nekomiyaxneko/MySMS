package io.github.nekomiyaxneko.mybms;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.tablemanager.Connector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class loginActivity extends BaseActivity implements View.OnClickListener{
    private long lastBack = 0;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private boolean isRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass=(CheckBox) findViewById(R.id.remember_pass);
        isRemember=pref.getBoolean("remember_password",false);
        String user= pref.getString("user","");
        EditText userNameText=(EditText)findViewById(R.id.user_name);
        userNameText.setText(user);
        if(user.equals("")){
            preduce();
        }
        if(isRemember){
            //将密码设置到文本框
            rememberPass.setChecked(true);
            String password= pref.getString("passwordmd5","");
            EditText userPassext=(EditText) findViewById(R.id.user_pass);
            userPassext.setText(password);
        }
        Button login_button=(Button) findViewById(R.id.login);
        login_button.setOnClickListener(this);
        Button registe_button=(Button) findViewById(R.id.registe);
        registe_button.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {//双击退出
        if (lastBack == 0 || System.currentTimeMillis() - lastBack > 2000) {
            Toast.makeText(loginActivity.this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
            lastBack = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }
    @Override
    public void onClick(View v){
        String TAG="onclick";
        switch (v.getId()){
            case R.id.login:
                EditText userNameText=(EditText) findViewById(R.id.user_name);
                EditText userPassText=(EditText) findViewById(R.id.user_pass);
                if(TextUtils.isEmpty(userNameText.getText())){
                    Toast.makeText(loginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: usernameblank!");
                    return;
                }
                if(TextUtils.isEmpty(userPassText.getText())){
                    Toast.makeText(loginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: userpassblank!");
                    return;
                }
                String userName=userNameText.getText().toString();
                String userPass=userPassText.getText().toString();
                //Log.d(TAG, "name"+userName+" endl pass"+userPass);
                if(userPass.length()<=16){
                    userPass=MD5Util.crypt(userPass);
                }
                if(judge(userName,userPass)){
                    //密码验证通过
                    editor=pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("user",userName);
                        editor.putString("passwordmd5",userPass);
                    }
                    else {
                        editor.clear();
                        editor.putString("user",userName);

                    }
                    editor.apply();
                    Intent intent=new Intent(loginActivity.this,MainActivity.class);
                    intent.putExtra("userName",userName);
                    Toast.makeText(loginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(loginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.registe:
                Toast.makeText(loginActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
    public boolean judge(String tuserName,String tuserPass){
        String a=new String("source:"+tuserName+" source:"+tuserPass+" user:"+"nekomiya"+"usermd5:e10adc3949ba59abbe56e057f20f883e");
        Log.d("find", "judge: "+a);
        String userName=new String("nekomiya");
        String userPass=new String("e10adc3949ba59abbe56e057f20f883e");
        if(tuserName.equals(userName)&&tuserPass.equals(userPass)){
            return true;
        }
        return false;
    }
    public void preduce(){
        Connector.getDatabase();
        Student stu1=new Student();
        stu1.setName("狗蛋");
        stu1.setScore("90");
        stu1.setStudent_Id("170501");
        stu1.save();
        Log.d("preducedebug", "makeList: id:"+stu1.getId()+" name:"+stu1.getName()+" score:"+stu1.getScore());
        stu1=new Student();
        stu1.setStudent_Id("170502");
        stu1.setName("neko酱");
        stu1.setScore("99");
        stu1.save();stu1=new Student();
        stu1.setStudent_Id("170503");
        stu1.setName("德邦总管");
        stu1.setScore("59");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("171314");
        stu1.setName("是谁在唱歌");
        stu1.setScore("43");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("170504");
        stu1.setName("亚斯娜");
        stu1.setScore("66");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("00001");
        stu1.setName("tourist");
        stu1.setScore("100");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("182720");
        stu1.setName("卡卡西");
        stu1.setScore("77");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("170132");
        stu1.setName("菜鸡");
        stu1.setScore("13");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("151067");
        stu1.setName("周杰伦");
        stu1.setScore("67");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("170431");
        stu1.setName("薛之谦");
        stu1.setScore("61");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("121007");
        stu1.setName("毕业不存在的");
        stu1.setScore("59");
        stu1.save();
        stu1=new Student();
        stu1.setStudent_Id("162222");
        stu1.setName("wazawaza");
        stu1.setScore("39");
        stu1.save();
    }

}
