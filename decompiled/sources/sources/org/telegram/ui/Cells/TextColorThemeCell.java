package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextColorThemeCell extends FrameLayout {
    private static Paint colorPaint;
    private float alpha;
    private int currentColor;
    private boolean needDivider;
    private TextView textView;

    public TextColorThemeCell(Context r10) {
        super(r10);
        this.alpha = 1.0f;
        if (colorPaint != null) goto L5;
        colorPaint = new Paint(1);
    L5:
        TextView r0 = new TextView(r10);
        this.textView = r0;
        r0.setTextColor(-14606047);
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r102 = this.textView;
        int r1 = 3;
        if (LocaleController.isRTL == false) goto L8;
        int r02 = 5;
    L9:
        r102.setGravity(r02 | 16);
        this.textView.setPadding(0, 0, 0, AndroidUtilities.dp(3.0f));
        TextView r103 = this.textView;
        boolean r03 = LocaleController.isRTL;
        if (r03 == false) goto L12;
        r1 = 5;
    L12:
        int r4 = r1 | 48;
        int r12 = 57;
        if (r03 == false) goto L15;
        int r3 = 21;
    L16:
        float r5 = r3;
        if (r03 == true) goto L20;
        r12 = 21;
    L20:
        addView(r103, LayoutHelper.createFrame(-1, -1.0f, r4, r5, 0.0f, r12, 0.0f));
        return;
    L15:
        r3 = 57;
        goto L16
    L8:
        r02 = 3;
        goto L9
    }

    @Override // android.view.View
    public float getAlpha() {
        return this.alpha;
    }

    @Override // android.view.View
    public void onDraw(Canvas r4) {
        int r0 = this.currentColor;
        if (r0 == 0) goto L11;
        colorPaint.setColor(r0);
        colorPaint.setAlpha((int) (this.alpha * 255.0f));
        if (LocaleController.isRTL == true) goto L8;
        int r02 = AndroidUtilities.dp(28.0f);
    L9:
        r4.drawCircle(r02, getMeasuredHeight() / 2, AndroidUtilities.dp(10.0f), colorPaint);
        return;
    L8:
        r02 = getMeasuredWidth() - AndroidUtilities.dp(28.0f);
        goto L9
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    @Override // android.view.View
    public void setAlpha(float r1) {
        this.alpha = r1;
        invalidate();
    }

    public void setTextAndColor(CharSequence r2, int r3) {
        this.textView.setText(r2);
        this.currentColor = r3;
        if (this.needDivider == true) goto L6;
        if (r3 != 0) goto L6;
        boolean r22 = true;
    L7:
        setWillNotDraw(r22);
        invalidate();
        return;
    L6:
        r22 = false;
        goto L7
    }
}
