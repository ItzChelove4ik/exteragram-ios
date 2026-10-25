package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.content.ContextCompat;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.GenericProvider;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBoxBase;

/* loaded from: classes3.dex */
public class CheckBox2 extends View {
    private CheckBoxBase checkBoxBase;
    int currentIcon;
    Drawable iconDrawable;
    private final Paint paint;

    public CheckBox2(Context r2, int r3) {
        this(r2, r3, null);
    }

    public CheckBox2(Context r1, int r2, Theme.ResourcesProvider r3) {
        super(r1);
        this.paint = new Paint();
        this.checkBoxBase = new CheckBoxBase(this, r2, r3);
    }

    public CheckBoxBase getCheckBoxBase() {
        return this.checkBoxBase;
    }

    public boolean getDrawUnchecked() {
        return this.checkBoxBase.getDrawUnchecked();
    }

    public float getProgress() {
        return this.checkBoxBase.getProgress();
    }

    public boolean hasIcon() {
        if (this.iconDrawable == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isChecked() {
        return this.checkBoxBase.isChecked();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.checkBoxBase.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.checkBoxBase.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r8) {
        if (this.iconDrawable == null) goto L6;
        int r0 = getMeasuredWidth() >> 1;
        int r1 = getMeasuredHeight() >> 1;
        Drawable r2 = this.iconDrawable;
        r2.setBounds(r0 - (r2.getIntrinsicWidth() / 2), r1 - (this.iconDrawable.getIntrinsicHeight() / 2), (this.iconDrawable.getIntrinsicWidth() / 2) + r0, (this.iconDrawable.getIntrinsicHeight() / 2) + r1);
        this.iconDrawable.draw(r8);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(AndroidUtilities.dp(1.2f));
        this.paint.setColor(Theme.getColor(Theme.key_switch2Track));
        r8.drawCircle(r0, r1, r0 - AndroidUtilities.dp(1.5f), this.paint);
        return;
    L6:
        this.checkBoxBase.draw(r8);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        r2.setClassName("android.widget.Switch");
        r2.setCheckable(true);
        r2.setChecked(isChecked());
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        this.checkBoxBase.setBounds(0, 0, r4 - r2, r5 - r3);
    }

    public void setChecked(int r1, boolean r2, boolean r3) {
        this.checkBoxBase.setChecked(r1, r2, r3);
    }

    public void setChecked(boolean r1, boolean r2) {
        this.checkBoxBase.setChecked(r1, r2);
    }

    public void setCirclePaintProvider(GenericProvider<Void, Paint> r1) {
        this.checkBoxBase.setCirclePaintProvider(r1);
    }

    public void setColor(int r1, int r2, int r3) {
        this.checkBoxBase.setColor(r1, r2, r3);
    }

    public void setDrawBackgroundAsArc(int r1) {
        this.checkBoxBase.setBackgroundType(r1);
    }

    public void setDrawUnchecked(boolean r1) {
        this.checkBoxBase.setDrawUnchecked(r1);
    }

    public void setDuration(long r1) {
        this.checkBoxBase.animationDuration = r1;
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        this.checkBoxBase.setEnabled(r2);
        super.setEnabled(r2);
    }

    public void setForbidden(boolean r1) {
        this.checkBoxBase.setForbidden(r1);
    }

    public void setIcon(int r2) {
        if (r2 == this.currentIcon) goto L10;
        this.currentIcon = r2;
        if (r2 != 0) goto L8;
        this.iconDrawable = null;
        return;
    L8:
        Drawable r22 = ContextCompat.getDrawable(getContext(), r2).mutate();
        this.iconDrawable = r22;
        r22.setColorFilter(Theme.getColor(Theme.key_switch2Track), PorterDuff.Mode.MULTIPLY);
        return;
    }

    public void setNum(int r1) {
        this.checkBoxBase.setNum(r1);
    }

    public void setProgressDelegate(CheckBoxBase.ProgressDelegate r1) {
        this.checkBoxBase.setProgressDelegate(r1);
    }
}
