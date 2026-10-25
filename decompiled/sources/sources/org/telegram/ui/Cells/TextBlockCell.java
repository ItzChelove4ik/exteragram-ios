package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class TextBlockCell extends FrameLayout {
    private boolean needDivider;
    private TextView textView;

    public TextBlockCell(Context r10) {
        super(r10);
        TextView r0 = new TextView(r10);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.textView.setTextSize(1, 16.0f);
        TextView r102 = this.textView;
        int r1 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r02 = 5;
    L6:
        r102.setGravity(r02 | 16);
        TextView r103 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        r1 = 5;
    L9:
        addView(r103, LayoutHelper.createFrame(-1, -2.0f, r1 | 48, 23.0f, 10.0f, 23.0f, 10.0f));
        return;
    L5:
        r02 = 3;
        goto L6
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(19.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(19.0f);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public void setText(String r2, boolean r3) {
        this.textView.setText(r2);
        this.needDivider = r3;
        setWillNotDraw(!r3);
    }

    public void setTextColor(int r1) {
        this.textView.setTextColor(r1);
    }
}
