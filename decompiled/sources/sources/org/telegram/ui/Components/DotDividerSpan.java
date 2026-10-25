package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public class DotDividerSpan extends ReplacementSpan {
    int color;
    Paint p;
    private float size;
    int topPadding;

    public DotDividerSpan() {
        this.p = new Paint(1);
        this.size = 3.0f;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        if (this.color == r9.getColor()) goto L5;
        this.p.setColor(r9.getColor());
    L5:
        float r22 = AndroidUtilities.dpf2(this.size) / 2.0f;
        r1.drawCircle(r5 + r22, ((r8 - r6) / 2) + this.topPadding, AndroidUtilities.dpf2(3.0f) / 2.0f, this.p);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return AndroidUtilities.dp(this.size);
    }

    public void setSize(float r1) {
        this.size = r1;
    }

    public void setTopPadding(int r1) {
        this.topPadding = r1;
    }
}
