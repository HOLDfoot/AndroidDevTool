package app.xiaoming.fuckxiaomi;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.ArrayList;
import java.util.List;

public class InstallHelperService extends AccessibilityService {
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.D("InstallHelperService onCreate");
    }
    
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.D("event.getPackageName()= " + event.getPackageName());
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode == null) return;
        if (event.getPackageName().equals("com.miui.securitycenter")) {
            installConfirm(rootNode);
        }
    }

    private void installConfirm(AccessibilityNodeInfo rootNode) {
        Log.D("installConfirm");
        List<AccessibilityNodeInfo> nodeInfoList = new ArrayList<>();
        nodeInfoList.addAll(rootNode.findAccessibilityNodeInfosByText("继续安装"));

        for (AccessibilityNodeInfo nodeInfo : nodeInfoList) {
            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
    }

    @Override
    public void onInterrupt() {
    }
}
