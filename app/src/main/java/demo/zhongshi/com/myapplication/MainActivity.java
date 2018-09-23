package demo.zhongshi.com.myapplication;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private Button button0;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn);
        button0 = findViewById(R.id.btn0);
        button0.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final View ContentView = getLayoutInflater().inflate(R.layout.pop_item, null);
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(ContentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x8f000000));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.getContentView().measure(onMeasure(popupWindow.getWidth()), onMeasure(popupWindow.getHeight()));
        /**
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
         //initWindow(0.5f);
         Log.e("----------->",String.valueOf(ContentView.getMeasuredHeight())
         +"---"+button.getMeasuredHeight());
         int xoff = ContentView.getMeasuredWidth();
         popupWindow.showAsDropDown(button, xoff, 0, Gravity.BOTTOM);
         }
         */
        /**
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
         //initWindow(0.5f);
         int yoff = -Math.abs(popupWindow.getContentView().getMeasuredHeight() + button0.getMeasuredHeight());
         Log.e("----------->",yoff
         +"---"+button0.getMeasuredHeight());
         PopupWindowCompat.showAsDropDown(popupWindow, button0, 0, yoff, Gravity.TOP);
         }
         */
    }

    private int onMeasure(int width) {
        int mode;
        if(width == ViewGroup.LayoutParams.WRAP_CONTENT){
            mode = View.MeasureSpec.UNSPECIFIED;
        }else {
            mode = View.MeasureSpec.EXACTLY;
        }
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(width), mode);
    }

    private void initWindow(float alpha) {
       Window window = getWindow();
       WindowManager.LayoutParams layoutParams = window.getAttributes();
       layoutParams.alpha = alpha;
       window.setAttributes(layoutParams);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn0:
                startPersonalData();
                break;
            case R.id.btn:
                Log.e("--->", "登陆账号");
                Constants.setLogin(true);
                break;
        }
    }
    @CheckLogin("startPersonalData")
    private void startPersonalData() {
        Log.e("--->","进入个人资料页面");
    }
}
