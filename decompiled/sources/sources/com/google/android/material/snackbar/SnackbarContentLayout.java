package com.google.android.material.snackbar;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.motion.MotionUtils;

/* loaded from: classes5.dex */
public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
    private Button actionView;
    private Button closeView;
    private final TimeInterpolator contentInterpolator;
    private int maxInlineActionWidth;
    private TextView messageView;

    public SnackbarContentLayout(Context r2) {
        this(r2, null);
    }

    public SnackbarContentLayout(Context r2, AttributeSet r3) {
        super(r2, r3);
        this.contentInterpolator = MotionUtils.resolveThemeInterpolator(r2, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    }

    private static void updateTopBottomPadding(View r2, int r3, int r4) {
        if (r2.isPaddingRelative() == false) goto L6;
        r2.setPaddingRelative(r2.getPaddingStart(), r3, r2.getPaddingEnd(), r4);
        return;
    L6:
        r2.setPadding(r2.getPaddingLeft(), r3, r2.getPaddingRight(), r4);
    }

    private boolean updateViewsWithinLayout(int r3, int r4, int r5) {
        if (r3 == getOrientation()) goto L5;
        setOrientation(r3);
        boolean r32 = true;
    L7:
        if (this.messageView.getPaddingTop() == r4) goto L9;
    L12:
        updateTopBottomPadding(this.messageView, r4, r5);
        return true;
    L9:
        if (this.messageView.getPaddingBottom() != r5) goto L12;
        return r32;
    L5:
        r32 = false;
        goto L7
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentIn(int r8, int r9) {
        this.messageView.setAlpha(0.0f);
        long r3 = r9;
        long r5 = r8;
        this.messageView.animate().alpha(1.0f).setDuration(r3).setInterpolator(this.contentInterpolator).setStartDelay(r5).start();
        if (this.actionView.getVisibility() != 0) goto L6;
        this.actionView.setAlpha(0.0f);
        this.actionView.animate().alpha(1.0f).setDuration(r3).setInterpolator(this.contentInterpolator).setStartDelay(r5).start();
        return;
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentOut(int r8, int r9) {
        this.messageView.setAlpha(1.0f);
        long r3 = r9;
        long r5 = r8;
        this.messageView.animate().alpha(0.0f).setDuration(r3).setInterpolator(this.contentInterpolator).setStartDelay(r5).start();
        if (this.actionView.getVisibility() != 0) goto L6;
        this.actionView.setAlpha(1.0f);
        this.actionView.animate().alpha(0.0f).setDuration(r3).setInterpolator(this.contentInterpolator).setStartDelay(r5).start();
        return;
    }

    public Button getActionView() {
        return this.actionView;
    }

    public Button getCloseView() {
        return this.closeView;
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(R.id.snackbar_text);
        this.actionView = (Button) findViewById(R.id.snackbar_action);
        this.closeView = (Button) findViewById(R.id.mtrl_snackbar_close);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        super.onMeasure(r8, r9);
        if (getOrientation() == 1) goto L28;
        int r0 = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
        int r2 = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
        Layout r3 = this.messageView.getLayout();
        if (r3 != null) goto L8;
    L10:
        boolean r32 = false;
    L11:
        if (r32 == true) goto L13;
    L19:
        if (r32 == true) goto L23;
        r0 = r2;
    L23:
        if (updateViewsWithinLayout(0, r0, r0) == false) goto L27;
    L24:
        super.onMeasure(r8, r9);
        return;
    L27:
        return;
    L13:
        if (this.maxInlineActionWidth <= 0) goto L19;
        if (this.actionView.getMeasuredWidth() <= this.maxInlineActionWidth) goto L19;
        if (updateViewsWithinLayout(1, r0, r0 - r2) == true) goto L24;
        return;
    L8:
        if (r3.getLineCount() <= 1) goto L10;
        r32 = true;
        goto L11
    }

    public void setMaxInlineActionWidth(int r1) {
        this.maxInlineActionWidth = r1;
    }

    public void updateActionTextColorAlphaIfNeeded(float r3) {
        if (r3 == 1.0f) goto L6;
        int r0 = this.actionView.getCurrentTextColor();
        int r32 = MaterialColors.layer(MaterialColors.getColor(this, R.attr.colorSurface), r0, r3);
        this.actionView.setTextColor(r32);
        return;
    }
}
