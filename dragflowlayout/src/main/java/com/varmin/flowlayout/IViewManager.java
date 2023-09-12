package com.varmin.flowlayout;

import android.view.View;

/*public*/ interface IViewManager {
    /**
     * 删除视图而不通知观察者
     */
    void removeViewWithoutNotify(View child);

}
