package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class FilledTabsView extends View {
    private final Paint backgroundPaint;
    private RectF[] bounds;
    private int lastPressedIndex;
    private Utilities.Callback<Integer> onTabClick;
    private final Paint selectedPaint;
    private final Path selectedPath;
    private float selectedTabIndex;
    private int selectedTextColor;
    private Text[] tabs;
    private int textColor;

    public FilledTabsView(Context r2) {
        super(r2);
        this.backgroundPaint = new Paint(1);
        this.selectedPaint = new Paint(1);
        this.selectedPath = new Path();
        this.textColor = -1;
        this.selectedTextColor = -1;
        this.lastPressedIndex = -1;
    }

    private void drawTabsText(Canvas r9, float r10, int r11, int r12) {
        Text[] r8 = this.tabs;
        int r0 = r8.length;
        int r1 = 0;
        float r4 = r10;
    L3:
        if (r1 >= r0) goto L5;
        Text r2 = r8[r1];
        Canvas r3 = r9;
        r2.draw(r3, r4, r11 / 2.0f, r12, 1.0f);
        r4 = r4 + (r2.getWidth() + AndroidUtilities.dp(24.0f));
        r1 = r1 + 1;
        r9 = r3;
        goto L3
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r18) {
        if (this.tabs != null) goto L5;
        return;
    L5:
        int r2 = getWidth();
        int r3 = getHeight();
        float r4 = 4.0f;
        int r5 = (AndroidUtilities.dp(4.0f) + (this.tabs.length * AndroidUtilities.dp(24.0f))) + AndroidUtilities.dp(4.0f);
        int r8 = 0;
    L6:
        Text[] r9 = this.tabs;
        if (r8 >= r9.length) goto L9;
        r5 = (int) (r5 + r9[r8].getWidth());
        r8 = r8 + 1;
        goto L6
    L9:
        float r92 = (r3 - AndroidUtilities.dp(36.0f)) / 2.0f;
        float r82 = (AndroidUtilities.dp(36.0f) + r3) / 2.0f;
        float r22 = (r2 - r5) / 2.0f;
        float r12 = AndroidUtilities.dp(16.0f) + r22;
        RectF r13 = AndroidUtilities.rectTmp;
        r13.set(r22, r92, r5 + r22, r82);
        r18.drawRoundRect(r13, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), this.backgroundPaint);
        float r23 = r22 + AndroidUtilities.dp(16.0f);
        int r52 = 0;
    L11:
        if (r52 >= this.tabs.length) goto L13;
        this.bounds[r52].set(r23 - AndroidUtilities.dp(16.0f), r92, (this.tabs[r52].getWidth() + r23) + AndroidUtilities.dp(16.0f), r82);
        r23 = r23 + (this.tabs[r52].getWidth() + AndroidUtilities.dp(24.0f));
        r52 = r52 + 1;
        r4 = r4;
        goto L11
    L13:
        AndroidUtilities.dp(r4);
        int r53 = Utilities.clamp((int) Math.floor(this.selectedTabIndex), this.tabs.length - 1, 0);
        int r6 = Utilities.clamp((int) Math.ceil(this.selectedTabIndex), this.tabs.length - 1, 0);
        float r7 = this.bounds[r53].left + AndroidUtilities.dp(r16);
        float r83 = this.bounds[r6].left + AndroidUtilities.dp(r16);
        float r93 = this.selectedTabIndex;
        float r72 = AndroidUtilities.lerp(r7, r83, (float) (r93 - Math.floor(r93)));
        float r54 = this.bounds[r53].right - AndroidUtilities.dp(r16);
        float r62 = this.bounds[r6].right - AndroidUtilities.dp(r16);
        float r84 = this.selectedTabIndex;
        float r55 = AndroidUtilities.lerp(r54, r62, (float) (r84 - Math.floor(r84)));
        RectF r63 = AndroidUtilities.rectTmp;
        r63.set(r72, (r3 - AndroidUtilities.dp(28.0f)) / 2.0f, r55, (AndroidUtilities.dp(28.0f) + r3) / 2.0f);
        this.selectedPath.rewind();
        this.selectedPath.addRoundRect(r63, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), Path.Direction.CW);
        r18.drawRoundRect(r63, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f), this.selectedPaint);
        drawTabsText(r18, r12, r3, this.textColor);
        r18.save();
        r18.clipPath(this.selectedPath);
        drawTabsText(r18, r12, r3, this.selectedTextColor);
        r18.restore();
    }

    public FilledTabsView onTabSelected(Utilities.Callback<Integer> r1) {
        this.onTabClick = r1;
        return this;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r6) {
        int r1 = 0;
        if (this.tabs != null) goto L5;
    L31:
        return false;
    L5:
        if (this.bounds == null) goto L31;
    L7:
        RectF[] r0 = this.bounds;
        if (r1 >= r0.length) goto L13;
        if (r0[r1].contains(r6.getX(), r6.getY()) == true) goto L14;
        r1 = r1 + 1;
    L14:
        if (r1 < 0) goto L21;
        if (r1 == this.lastPressedIndex) goto L21;
        this.lastPressedIndex = r1;
        Utilities.Callback<Integer> r02 = this.onTabClick;
        if (r02 == null) goto L21;
        r02.run(Integer.valueOf(r1));
    L21:
        if (r6.getAction() != 1) goto L23;
    L24:
        this.lastPressedIndex = -1;
    L26:
        if (r6.getAction() != 0) goto L30;
        if (r1 < 0) goto L30;
        return true;
    L30:
        return super.onTouchEvent(r6);
    L23:
        if (r6.getAction() != 3) goto L26;
    L13:
        r1 = -1;
        goto L14
    }

    @Override // android.view.View
    public void setBackgroundColor(int r2) {
        this.backgroundPaint.setColor(r2);
        invalidate();
    }

    public void setColors(int r2, int r3, int r4, int r5) {
        this.backgroundPaint.setColor(r2);
        this.selectedPaint.setColor(r3);
        this.textColor = r4;
        this.selectedTextColor = r5;
        invalidate();
    }

    public void setSelected(float r3) {
        if (Math.abs(r3 - this.selectedTabIndex) <= 0.001f) goto L5;
        invalidate();
    L5:
        this.selectedTabIndex = r3;
    }

    public void setSelectedColor(int r2) {
        this.selectedPaint.setColor(r2);
        invalidate();
    }

    public void setSelectedTextColor(int r1) {
        this.selectedTextColor = r1;
        invalidate();
    }

    public void setTabs(CharSequence... r7) {
        this.tabs = new Text[r7.length];
        this.bounds = new RectF[r7.length];
        int r0 = 0;
    L4:
        if (r0 >= r7.length) goto L6;
        this.tabs[r0] = new Text(r7[r0], 14.0f, AndroidUtilities.bold());
        this.bounds[r0] = new RectF();
        r0 = r0 + 1;
        goto L4
    L6:
        invalidate();
    }

    public void setTextColor(int r1) {
        this.textColor = r1;
        invalidate();
    }
}
