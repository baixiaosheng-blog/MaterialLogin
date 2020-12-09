package cn.fanrunqi.materiallogin;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityOptionsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    //@InjectView(R.id.bt_go)       //加上也没关系，会被findViewById覆盖
    Button btGo;                    //不用findViewById有点击动画而没界面跳转
    @InjectView(R.id.cv)
    CardView cv;
    //@InjectView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        btGo = findViewById(R.id.bt_go);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(onClickListener);
        btGo.setOnClickListener(onClickListener);

    }

   View.OnClickListener onClickListener = new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           switch (v.getId()) {
               case R.id.fab:
                   Log.i("TAG", "onClick: ++++");
                   getWindow().setExitTransition(null);
                   getWindow().setEnterTransition(null);

                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                       ActivityOptions options =
                               ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, fab, "loginFab");
                       startActivity(new Intent(MainActivity.this, RegisterActivity.class), options.toBundle());
                   } else {
                       startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                   }
                   break;
               case R.id.bt_go:
                   Explode explode = new Explode();
                   explode.setDuration(500);

                   getWindow().setExitTransition(explode);
                   getWindow().setEnterTransition(explode);
                   ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
                   Intent i2 = new Intent(MainActivity.this,LoginSuccessActivity.class);
                   startActivity(i2, oc2.toBundle());
                   break;
           }
       }
   };


//登录有点击动画而没界面跳转，注册界面跳转的FloatingActionButton fab没焦点，点击没反应。
// @OnClick({R.id.bt_go, R.id.fab})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.fab:
//                Log.i("TAG", "onClick: ++++");
//                getWindow().setExitTransition(null);
//                getWindow().setEnterTransition(null);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    ActivityOptions options =
//                            ActivityOptions.makeSceneTransitionAnimation(this, fab, "loginFab");
//                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
//                } else {
//                    startActivity(new Intent(this, RegisterActivity.class));
//                }
//                break;
//            case R.id.bt_go:
//                Explode explode = new Explode();
//                explode.setDuration(500);
//
//                getWindow().setExitTransition(explode);
//                getWindow().setEnterTransition(explode);
//                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//                Intent i2 = new Intent(this,LoginSuccessActivity.class);
//                startActivity(i2, oc2.toBundle());
//                break;
//        }
//    }
}
