package com.varmin.flowlayout;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.heaven7.core.util.Logger;

/**
 * Created by varmin on 2020/8/23.
 */
public class SimpleService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.i("SimpleService", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }
}
