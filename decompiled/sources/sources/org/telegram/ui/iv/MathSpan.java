package org.telegram.ui.iv;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.style.ReplacementSpan;

/* loaded from: classes7.dex */
public class MathSpan extends ReplacementSpan {
    private final Bitmap bitmap;
    private final int depth;
    private final int height;
    private final Paint paint;
    public final String source;
    private final int width;

    private MathSpan(String r3, Bitmap r4, int r5, int r6, int r7, int r8) {
        Paint r0 = new Paint(3);
        this.paint = r0;
        this.source = r3;
        this.bitmap = r4;
        this.width = r5;
        this.height = r6;
        this.depth = r8;
        r0.setColor(r7);
    }

    public static MathSpan create(String r8, int r9, float r10) {
        if (r8 != null) goto L5;
    L12:
        return null;
    L5:
        if (r8.isEmpty() == true) goto L12;
        Latex r102 = Latex.render(r8, r10, true);
        if (r102 != null) goto L11;
        return null;
    L11:
        return new MathSpan(r8, r102.bitmap, r102.width, r102.height, r9, r102.depth);
    }

    public static String sourceAt(CharSequence r2, int r3, int r4) {
        if ((r2 instanceof Spanned) == true) goto L5;
        return null;
    L5:
        MathSpan[] r22 = (MathSpan[]) ((Spanned) r2).getSpans(r3, r4, MathSpan.class);
        if (r22.length > 0) goto L8;
        return null;
    L8:
        return r22[0].source;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        if (this.bitmap != null) goto L5;
        return;
    L5:
        this.paint.setColor(r9.getColor());
        r1.drawBitmap(this.bitmap, r5, r7 - (this.height - this.depth), this.paint);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        if (r5 == null) goto L5;
        int r12 = this.height;
        int r22 = this.depth;
        int r13 = -(r12 - r22);
        r5.ascent = r13;
        r5.top = r13;
        r5.descent = r22;
        r5.bottom = r22;
    L5:
        return this.width;
    }
}
