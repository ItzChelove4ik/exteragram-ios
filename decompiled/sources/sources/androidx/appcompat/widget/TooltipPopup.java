package androidx.appcompat.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$style;

/* loaded from: classes3.dex */
class TooltipPopup {
    private final View mContentView;
    private final Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private final TextView mMessageView;
    private final int[] mTmpAnchorPos;
    private final int[] mTmpAppPos;
    private final Rect mTmpDisplayFrame;

    public TooltipPopup(Context r5) {
        WindowManager.LayoutParams r0 = new WindowManager.LayoutParams();
        this.mLayoutParams = r0;
        this.mTmpDisplayFrame = new Rect();
        this.mTmpAnchorPos = new int[2];
        this.mTmpAppPos = new int[2];
        this.mContext = r5;
        View r1 = LayoutInflater.from(r5).inflate(R$layout.abc_tooltip, null);
        this.mContentView = r1;
        this.mMessageView = (TextView) r1.findViewById(R$id.message);
        r0.setTitle(getClass().getSimpleName());
        r0.packageName = r5.getPackageName();
        r0.type = 1002;
        r0.width = -2;
        r0.height = -2;
        r0.format = -3;
        r0.windowAnimations = R$style.Animation_AppCompat_Tooltip;
        r0.flags = 24;
    }

    private void computePosition(View r9, int r10, int r11, boolean r12, WindowManager.LayoutParams r13) {
        r13.token = r9.getApplicationWindowToken();
        int r0 = this.mContext.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_threshold);
        if (r9.getWidth() >= r0) goto L7;
        r10 = r9.getWidth() / 2;
    L7:
        if (r9.getHeight() < r0) goto L9;
        int r02 = this.mContext.getResources().getDimensionPixelOffset(R$dimen.tooltip_precise_anchor_extra_offset);
        int r1 = r11 + r02;
        int r112 = r11 - r02;
    L10:
        r13.gravity = 49;
        Resources r03 = this.mContext.getResources();
        if (r12 == false) goto L13;
        int r3 = R$dimen.tooltip_y_offset_touch;
    L14:
        int r04 = r03.getDimensionPixelOffset(r3);
        View r32 = getAppRootView(r9);
        if (r32 != null) goto L18;
        Log.e("TooltipPopup", "Cannot find app view");
        return;
    L18:
        r32.getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
        Rect r4 = this.mTmpDisplayFrame;
        if (r4.left < 0) goto L21;
    L27:
        r32.getLocationOnScreen(this.mTmpAppPos);
        r9.getLocationOnScreen(this.mTmpAnchorPos);
        int[] r92 = this.mTmpAnchorPos;
        int r42 = r92[0];
        int[] r5 = this.mTmpAppPos;
        int r43 = r42 - r5[0];
        r92[0] = r43;
        r92[1] = r92[1] - r5[1];
        r13.x = (r43 + r10) - (r32.getWidth() / 2);
        int r93 = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mContentView.measure(r93, r93);
        int r94 = this.mContentView.getMeasuredHeight();
        int r102 = this.mTmpAnchorPos[1];
        int r113 = ((r112 + r102) - r04) - r94;
        int r103 = (r102 + r1) + r04;
        if (r12 == false) goto L35;
        if (r113 < 0) goto L32;
        r13.y = r113;
        return;
    L32:
        r13.y = r103;
        return;
    L35:
        if ((r94 + r103) > this.mTmpDisplayFrame.height()) goto L38;
        r13.y = r103;
        return;
    L38:
        r13.y = r113;
        return;
    L21:
        if (r4.top >= 0) goto L27;
        Resources r44 = this.mContext.getResources();
        int r52 = r44.getIdentifier("status_bar_height", "dimen", "android");
        if (r52 == 0) goto L25;
        int r53 = r44.getDimensionPixelSize(r52);
    L26:
        DisplayMetrics r45 = r44.getDisplayMetrics();
        this.mTmpDisplayFrame.set(0, r53, r45.widthPixels, r45.heightPixels);
        goto L27
    L25:
        r53 = 0;
        goto L26
    L13:
        r3 = R$dimen.tooltip_y_offset_non_touch;
        goto L14
    L9:
        r1 = r9.getHeight();
        r112 = 0;
        goto L10
    }

    private static View getAppRootView(View r3) {
        View r0 = r3.getRootView();
        ViewGroup.LayoutParams r1 = r0.getLayoutParams();
        if ((r1 instanceof WindowManager.LayoutParams) == true) goto L5;
    L7:
        Context r32 = r3.getContext();
    L9:
        if ((r32 instanceof ContextWrapper) == false) goto L15;
        if ((r32 instanceof Activity) == true) goto L13;
        r32 = ((ContextWrapper) r32).getBaseContext();
        goto L9
    L13:
        return ((Activity) r32).getWindow().getDecorView();
    L15:
        return r0;
    L5:
        if (((WindowManager.LayoutParams) r1).type != 2) goto L7;
        goto L7
    }

    public void hide() {
        if (isShowing() == true) goto L5;
        return;
    L5:
        ((WindowManager) this.mContext.getSystemService("window")).removeView(this.mContentView);
    }

    public boolean isShowing() {
        if (this.mContentView.getParent() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void show(View r2, int r3, int r4, boolean r5, CharSequence r6) {
        if (isShowing() == false) goto L5;
        hide();
    L5:
        this.mMessageView.setText(r6);
        computePosition(r2, r3, r4, r5, this.mLayoutParams);
        ((WindowManager) this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
    }
}
