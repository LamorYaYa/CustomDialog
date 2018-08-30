package com.lamor.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

/**
 * @author Master
 * @create 2018/8/24 16:48
 */

public class CustomDialog extends Dialog implements View.OnClickListener {

    /**
     * 上下文
     */
    private Context iContext;
    /**
     * 布局资源Id
     */
    private int iLayoutResId;
    /**
     * 要监听的空间Id
     */
    private int[] iListenedItems = new int[]{};
    /**
     * dialog 动画
     */
    private int iAnimationResId;

    /**
     * Dialog 相对页面显示的位置
     */
    private int iPosition = 0;
    /**
     * 默认点击空白区域消失
     */
    private boolean iCanceledOnTouchOutside = true;
    /**
     * 默认点击返回键消失
     */
    private boolean iCancelable = true;
    /**
     * 点击按钮后是否隐藏Dialog，默认隐藏，否则需要自己处理
     */
    private boolean iIsDismiss = true;

    private OnDialogItemClickListener iOnDialogItemClickListener;


    /**
     *
     * @param iContext
     * @param iLayoutResId
     * @param iListenedItems
     * @param iIsDismiss
     */
    public CustomDialog(@NonNull Context iContext, int iLayoutResId, int[] iListenedItems, boolean iIsDismiss) {
        this(iContext, iLayoutResId, iListenedItems, 0, 0, true, true, iIsDismiss);
    }

    /**
     * @param context                 上下文
     * @param layoutResId             布局资源Id
     * @param iListenedItems          要监听的空间Id
     * @param iPosition               Dialog 相对页面显示的位置
     * @param iCanceledOnTouchOutside 点击空白区域是否消失
     * @param iCancelable             点击返回键是否消失
     * @param iIsDismiss              点击按钮后是否隐藏Dialog，默认隐藏，否则需要自己处理
     */
    public CustomDialog(@NonNull Context context, int layoutResId,
                        int[] iListenedItems, int iAnimationResId, int iPosition,
                        boolean iCanceledOnTouchOutside, boolean iCancelable,
                        boolean iIsDismiss) {
        super(context, R.style.Custom_Dialog_Style);
        this.iContext = context;
        this.iLayoutResId = layoutResId;
        this.iListenedItems = iListenedItems;
        this.iAnimationResId = iAnimationResId;
        this.iPosition = iPosition;
        this.iCanceledOnTouchOutside = iCanceledOnTouchOutside;
        this.iCancelable = iCancelable;
        this.iIsDismiss = iIsDismiss;
    }

    public void setOnDialogItemClickListener(OnDialogItemClickListener onDialogItemClickListener) {
        this.iOnDialogItemClickListener = onDialogItemClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (0 == iPosition) {
            // 默认居中
            window.setGravity(Gravity.CENTER);
        } else {
            // 设置要显示的位置
            window.setGravity(iPosition);
        }

        if (iAnimationResId == 0) {
            // 添加默认动画
            window.setWindowAnimations(R.style.bottom_animation);
        } else {
            // 添加自定义动画
            window.setWindowAnimations(iAnimationResId);
        }
        setContentView(iLayoutResId);

        setCanceledOnTouchOutside(iCanceledOnTouchOutside);
        setCancelable(iCancelable);

        for (int id : iListenedItems) {
            findViewById(id).setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        if (iIsDismiss) {
            dismiss();
        }
        if (iOnDialogItemClickListener != null) {
            iOnDialogItemClickListener.onDialogItemClick(this, v);
        }
    }

    public interface OnDialogItemClickListener {
        void onDialogItemClick(CustomDialog dialog, View view);
    }

}
