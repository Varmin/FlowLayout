package com.varmin.flowlayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.heaven7.core.util.Logger;

/**
 * Created by varmin on 2020/8/23.
 */
public class NetstateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent ==null) return;
        /**
         *
         1， 只要app的没有一个进程存活（正在运行的进程和缓存进程都不存在）,都将启动不了service(通过系统receiver)
         2，强制停止后也 启动不了service(通过系统receiver)
         3，只要该app有进程存活，就可以.
         */
        Logger.i("NetstateChangeReceiver", "action = " + intent.getAction());
        context.startService(new Intent(context, SimpleService.class));
    }
}
