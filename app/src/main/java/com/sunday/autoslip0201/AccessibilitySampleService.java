package com.sunday.autoslip0201;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Handler;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class AccessibilitySampleService extends AccessibilityService {

    /**当无障碍服务连接之后回调*/
    @Override
    public void  onServiceConnected() {
        super.onServiceConnected();
    }

    /**当触发了需要监听的无障碍事件后回调*/
//    @Override
//    public void onAccessibilityEvent(AccessibilityEvent event){
//        if (!OpenAccessibilitySettingHelper.isAccessibilitySettingsOn(this,
//                AccessibilitySampleService.class.getName())){// 判断服务是否开启
//            OpenAccessibilitySettingHelper.jumpToSettingPage(this);// 跳转到开启页面
//        }else {
//            Toast.makeText(this, "服务已开启", Toast.LENGTH_SHORT).show();
//        }
//        // 获取包名
//        String pkgName = event.getPackageName().toString();
//        int eventType = event.getEventType();
//
//        AccessibilityOperator.getInstance().updateEvent(this, event);
//        //过滤出目标包，如果要检测所有包，可以去掉此判断
//       // if (pkgName.equals(pageName)) {
//        while(true){
//            //slipWindow
//            Path path = new Path();
//            path.moveTo(300,1000);
//            path.lineTo(300,200);
//            new GestureDescription.StrokeDescription(path,20,300);
//        }
//
//       // }
//    }
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 执行向下滑屏操作

            Log.d("TAG","slip start");
            Path path = new Path();
            path.moveTo(300, 1000);
            path.lineTo(300, 200);
            GestureDescription gestureDescription = new GestureDescription.Builder()
                    .addStroke(new GestureDescription.StrokeDescription(path, 0, 300))
                    .build();
            dispatchGesture(gestureDescription, null, null);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Log.d("TAG","slip end");
            // 定时触发下一次滑屏操作
            handler.postDelayed(this, 1000); // 每隔1秒触发一次滑屏操作
        }
    };

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event){
        // 其他处理逻辑
        Log.d("TAG","onAccessibilityEvent");

        // 启动定时滑屏操作
        handler.postDelayed(runnable, 1000); // 延迟1秒后开始滑屏操作
    }

    /**无障碍服务断开后回调*/
    @Override
    public void onInterrupt(){
        // TODO Auto-generated method stub
    }
}