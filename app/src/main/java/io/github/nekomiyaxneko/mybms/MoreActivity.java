package io.github.nekomiyaxneko.mybms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MoreActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Button signout=(Button) findViewById(R.id.signout);
        signout.setOnClickListener(this);
        TextView tomail=findViewById(R.id.toemail);
        TextView togithub=findViewById(R.id.togithub);
        SpannableString ss=new SpannableString("bill_lll@qq.com");
        ss.setSpan(new URLSpan("mailto:bill_li@qq.com"), 0,15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tomail.setText(ss);
        tomail.setMovementMethod(LinkMovementMethod.getInstance());
        ss=new SpannableString("github项目主页");
        ss.setSpan(new URLSpan("https://github.com/nekomiyaxneko/MySMS"), 0,10,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        togithub.setText(ss);
        togithub.setMovementMethod(LinkMovementMethod.getInstance());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signout:
                Intent intent=new Intent(MoreActivity.this,loginActivity.class);
                Toast.makeText(MoreActivity.this,"退出成功",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                ActivityCollector.finishAll();
                break;
            default:
                break;
        }
    }
}
