package com.varmin.flowlayout;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.heaven7.adapter.BaseSelector;
import butterknife.BindView;
import butterknife.OnClick;

/** 拖拽流布局 示例程序
 */
public class DragFlowLayoutMain extends BaseActivity {

    private static final String TAG = "DragFlowLayoutTest";
    private static final String[] tags = {"内", "内容", "内容内", "内容内容", "内容内容容"};

    @BindView(R.id.drag_flowLayout)
    DragFlowLayout mDragflowLayout;

    private int mIndex;
    @Override
    protected int getlayoutId() {
        return R.layout.activity_drag_flow_main;
    }

    @Override
    protected void onPreSetContentView() {
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
    }

    @Override
    protected void initView() {
        mDragflowLayout.setOnItemClickListener(new ClickToDeleteItemListenerImpl(R.id.iv_close){
            @Override
            protected void onDeleteSuccess(DragFlowLayout dfl, View child, Object data) {
                //删除成功后的处理。
            }
        });
        mDragflowLayout.setDragAdapter(new ItemAdapter<ItemBean>() {
            @Override
            public int getItemLayoutId() {
                return R.layout.item_drag_flow;
            }
            @Override
            public void onBindData(View itemView, int dragState, ItemBean data) {
                itemView.setTag(data);
                TextView tv = (TextView) itemView.findViewById(R.id.tv_text);
                tv.setText(data.text);
                itemView.findViewById(R.id.iv_close).setVisibility(
                        dragState!= DragFlowLayout.DRAG_STATE_IDLE
                        && data.draggable ? View.VISIBLE : View.INVISIBLE);
            }
            @NonNull
            @Override
            public ItemBean getData(View itemView) {
                return (ItemBean) itemView.getTag();
            }
        });

        //预存指定个数的Item. 这些Item view会反复使用，避免重复创建, 默认10个
        mDragflowLayout.prepareItemsByCount(10);
        //设置拖拽状态监听器
        mDragflowLayout.setOnDragStateChangeListener(new DragFlowLayout.OnDragStateChangeListener() {
            @Override
            public void onDragStateChange(DragFlowLayout dfl, int dragState) {
               // System.out.println("on drag state change : dragState = " + dragState);
            }
        });
        //添加view观察者
        mDragflowLayout.addViewObserver(new IViewObserver() {
            @Override
            public void onAddView(View child, int index) {
               // Logger.i(TAG, "onAddView", "index = " + index);
            }
            @Override
            public void onRemoveView(View child, int index) {
              // Logger.i(TAG, "onRemoveView", "index = " + index);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {}

    @OnClick(R.id.bt_begin_drag)
    public void onClickBeginDrag(View v){
        mDragflowLayout.beginDrag();
    }
    @OnClick(R.id.bt_finish_drag)
    public void onClickFinishDrag(View v){
        mDragflowLayout.finishDrag();
    }
    @OnClick(R.id.bt_done)
    public void onClickDone(View v){
        mDragflowLayout.finishDrag();
    }

    @OnClick(R.id.bt_add)
    public void onClickAdd(View v){
        ItemBean bean = new ItemBean( mIndex + ": " +  tags[mIndex % 5] );
        bean.draggable = true;
        mDragflowLayout.getDragItemManager().addItem( bean);
        mIndex ++;
    }

    @OnClick(R.id.bt_remove)
    public void onClickRemove(View v){
        final DragFlowLayout.DragItemManager itemManager = mDragflowLayout.getDragItemManager();
        final int count = itemManager.getItemCount();
        if(count == 0){
            return;
        }
        itemManager.removeItem(count - 1);
    }

    private static class ItemBean extends BaseSelector implements IDraggable{
        String text;
        boolean draggable = true;
        public ItemBean(String text) {
            this.text = text;
        }
        @Override
        public boolean isDraggable() {
            return draggable;
        }

    }
}
