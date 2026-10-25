package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Property;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextColorCell extends FrameLayout {
    private static Paint colorPaint;
    public static final int[] colors = null;
    public static final int[] colorsToSave = null;
    private int currentColor;
    private boolean needDivider;
    private Theme.ResourcesProvider resourcesProvider;
    private TextView textView;

    static {
        colors = new int[]{-1031100, -29183, -12769, -8792480, -12521994, -12140801, -2984711, -45162, -4473925};
        colorsToSave = new int[]{Opcodes.V_PREVIEW, -29183, -256, -16711936, -16711681, -16776961, -2984711, -65281, -1};
    }

    public TextColorCell(Context r2) {
        this(r2, null);
    }

    public TextColorCell(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.resourcesProvider = r10;
        if (colorPaint != null) goto L5;
        colorPaint = new Paint(1);
    L5:
        TextView r0 = new TextView(r9);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r10));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r92 = this.textView;
        int r02 = 3;
        if (LocaleController.isRTL == false) goto L8;
        int r102 = 5;
    L9:
        r92.setGravity(r102 | 16);
        TextView r93 = this.textView;
        if (LocaleController.isRTL == false) goto L12;
        r02 = 5;
    L12:
        addView(r93, LayoutHelper.createFrame(-1, -1.0f, r02 | 48, 21.0f, 0.0f, 21.0f, 0.0f));
        return;
    L8:
        r102 = 3;
        goto L9
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        Canvas r2 = r9;
    L15:
        int r92 = this.currentColor;
        if (r92 == 0) goto L24;
        colorPaint.setColor(r92);
        if (LocaleController.isRTL == false) goto L21;
        int r93 = AndroidUtilities.dp(33.0f);
    L22:
        r2.drawCircle(r93, getMeasuredHeight() / 2, AndroidUtilities.dp(10.0f), colorPaint);
        return;
    L21:
        r93 = getMeasuredWidth() - AndroidUtilities.dp(33.0f);
        goto L22
    L24:
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(20.0f);
    L13:
        r2 = r9;
        r2.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        goto L15
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(20.0f);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
    }

    public void setEnabled(boolean r8, ArrayList<Animator> r9) {
        super.setEnabled(r8);
        TextView r0 = this.textView;
        float r1 = 0.5f;
        if (r9 == null) goto L12;
        if (r8 == false) goto L6;
        float r3 = 1.0f;
    L7:
        float[] r5 = {r3};
        Property r32 = View.ALPHA;
        r9.add(ObjectAnimator.ofFloat(r0, r32, r5));
        if (r8 == false) goto L10;
        r1 = 1.0f;
    L10:
        r9.add(ObjectAnimator.ofFloat(this, r32, new float[]{r1}));
        return;
    L6:
        r3 = 0.5f;
        goto L7
    L12:
        if (r8 == false) goto L14;
        float r92 = 1.0f;
    L15:
        r0.setAlpha(r92);
        if (r8 == false) goto L18;
        r1 = 1.0f;
    L18:
        setAlpha(r1);
        return;
    L14:
        r92 = 0.5f;
        goto L15
    }

    public void setTextAndColor(String r2, int r3, boolean r4) {
        this.textView.setText(r2);
        this.needDivider = r4;
        this.currentColor = r3;
        if (r4 == true) goto L6;
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
