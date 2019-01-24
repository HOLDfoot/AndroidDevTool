package me.wangyi.fuckvivo;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private Button button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_open);
        
        if (!isStartAccessibilityService(this, "me.wangyi.fuckvivo.InstallHelperService")) {
            openSettings(null);
        } else {
            Button button = findViewById(R.id.btn_open);
            button.setText("小米应用安装服务已打开");
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (!isStartAccessibilityService(this, "me.wangyi.fuckvivo.InstallHelperService")) {
            button.setText("去打开小米安装服务");
        } else {
            button.setText("小米应用安装服务已打开");
        }
    }
    
    public static boolean isStartAccessibilityService(Context context, String name) {
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> serviceInfos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : serviceInfos) {
            String id = info.getId();
            if (id.contains(name)) {
                return true;
            }
        }
        return false;
    }
    
    public void openSettings(View view) {
        startActivity(new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS));
    }
}
