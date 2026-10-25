package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class SquigglyLinesSpan extends CharacterStyle {
    private final Paint paint;
    private final Path path;

    public SquigglyLinesSpan() {
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.path = new Path();
        r0.setStyle(Paint.Style.STROKE);
        r0.setStrokeCap(Paint.Cap.ROUND);
        r0.setStrokeJoin(Paint.Join.ROUND);
    }

    public static void drawOnText(Canvas r12, Layout r13) {
        if (r13 == null) goto L34;
        CharSequence r0 = r13.getText();
        if (r0 != null) goto L8;
        return;
    L8:
        if ((r0 instanceof Spanned) == false) goto L36;
        Spanned r02 = (Spanned) r0;
        int r3 = 0;
        SquigglyLinesSpan[] r1 = (SquigglyLinesSpan[]) r02.getSpans(0, r02.length(), SquigglyLinesSpan.class);
        if (r1 != null) goto L13;
        return;
    L13:
        if (r1.length != 0) goto L16;
        return;
    L16:
        if (r3 >= r1.length) goto L38;
        SquigglyLinesSpan r2 = r1[r3];
        int r4 = r02.getSpanStart(r2);
        int r5 = r02.getSpanEnd(r2);
        int r6 = r13.getLineForOffset(r4);
        int r7 = r13.getLineForOffset(r5);
        int r8 = r6;
    L18:
        if (r8 > r7) goto L28;
        float r9 = r13.getLineBottom(r8) - AndroidUtilities.dp(1.0f);
        if (r8 != r6) goto L22;
        int r10 = r4;
    L23:
        float r102 = r13.getPrimaryHorizontal(r10);
        if (r8 != r7) goto L26;
        int r11 = r5;
    L27:
        r2.draw(r12, r9, r102, r13.getPrimaryHorizontal(r11));
        r8 = r8 + 1;
        goto L18
    L26:
        r11 = r13.getLineEnd(r8) - 1;
        goto L27
    L22:
        r10 = r13.getLineStart(r8);
        goto L23
    L28:
        r3 = r3 + 1;
        goto L16
    L38:
        return;
    L36:
        return;
    }

    public void draw(Canvas r11, float r12, float r13, float r14) {
        float r0 = AndroidUtilities.dp(1.33f);
        float r1 = AndroidUtilities.dp(10.0f);
        float r3 = AndroidUtilities.dp(2.0f);
        this.paint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
        this.paint.setStrokeWidth(r0);
        this.path.rewind();
        this.path.moveTo(r13, r12);
        float r4 = r13;
    L4:
        if (r4 >= r14) goto L7;
        this.path.quadTo((r1 / 4.0f) + r4, r12 - r3, (r1 / 2.0f) + r4, r12);
        r4 = r4 + r1;
        this.path.quadTo(((3.0f * r1) / 4.0f) + r4, r12 + r3, r4, r12);
        goto L4
    L7:
        if (r4 <= r14) goto L10;
        r11.save();
        float r02 = r0 / 2.0f;
        r11.clipRect(r13 - r02, (r12 - r3) - r02, r14 + r02, (r12 + r3) + r02);
        r11.drawPath(this.path, this.paint);
        r11.restore();
        return;
    L10:
        r11.drawPath(this.path, this.paint);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r1) {
    }
}
