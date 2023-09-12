 <img src="/gif/dragflowlayout.gif" width="360px" height="640px"/>

### 特点
 - 1, 增加、删除、拖动。
 - 2, 流式布局，根据内容长度换行。
 - 3, 可拖拽

### 使用

```xml
  <com.varmin.flowlayout.DragFlowLayout
        android:id="@+id/drag_flowLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
  </com.varmin.flowlayout.DragFlowLayout>
```

```java 
mFlowLayout.setAdapter(new ItemAdapter<Object>() {
    @Override
    public int getItemLayout() {
        return R.layout.item_flow;//标签布局
    }
    @Override
    public void onBindData(View itemView, int dragState, TestBean data) {
        //数据绑定
        itemView.setTag(data);
        TextView tv = (TextView) itemView.findViewById(R.id.tv_text);
        tv.setText(data.text);
    }
});

//添加      
mFlowLayout.getDragItemManager().addItem(index, obj);
mFlowLayout.getDragItemManager().addItem(obj);
//移除  
mFlowLayout.getDragItemManager().removeItem(index);
mFlowLayout.getDragItemManager().removeItem(obj);

```
