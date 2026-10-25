package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class HashtagSearchCell extends TextView {
    private boolean needDivider;

    public HashtagSearchCell(Context r3) {
        super(r3);
        setGravity(16);
        setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        setTextSize(1, 17.0f);
        setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas r8) {
        super.onDraw(r8);
        if (this.needDivider == false) goto L6;
        r8.drawLine(0.0f, getHeight() - 1, getWidth(), getHeight() - 1, Theme.dividerPaint);
        return;
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r1, int r2) {
        setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(48.0f) + 1);
    }

    public void setNeedDivider(boolean r1) {
        this.needDivider = r1;
    }
}
