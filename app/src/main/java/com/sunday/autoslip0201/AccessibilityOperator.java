package com.sunday.autoslip0201;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class AccessibilityOperator {
    private static final String TAG = "AccessibilityOperator";
    private static AccessibilityOperator mInstance;
    private AccessibilityService mAccessibilityService;
    private AccessibilityEvent mAccessibilityEvent;
    private AccessibilityOperator(){}
    public static AccessibilityOperator getInstance() {
        if (mInstance == null){
            synchronized (AccessibilityOperator.class){
                if (mInstance == null){
                    mInstance = new AccessibilityOperator();
                }
            }
        }
        return mInstance;
    }
    public void updateEvent(AccessibilityService service, AccessibilityEvent event) {
        if (mAccessibilityService == null && service != null){
            mAccessibilityService = service;
        }
        if (event != null){
            mAccessibilityEvent = event;
        }
    }

    private AccessibilityNodeInfo getRootNodeInfo() {
        Log.e(TAG, "getRootNodeInfo: ");
        AccessibilityEvent curEvent = mAccessibilityEvent;
        AccessibilityNodeInfo nodeInfo = null;
        if (Build.VERSION.SDK_INT >= 16){
            if (mAccessibilityService!=null){
                // 获得窗体根节点
                nodeInfo = mAccessibilityService.getRootInActiveWindow();
            }
        }else {
            nodeInfo = curEvent.getSource();
        }
        return nodeInfo;
    }
}