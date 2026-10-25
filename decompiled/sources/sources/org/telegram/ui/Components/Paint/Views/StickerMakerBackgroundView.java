package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes7.dex */
public abstract class StickerMakerBackgroundView extends View {
    private final Paint backgroundPaint;
    private final Path path;

    public StickerMakerBackgroundView(Context r2) {
        super(r2);
        Paint r22 = new Paint();
        this.backgroundPaint = r22;
        this.path = new Path();
        r22.setColor(-1);
        r22.setAlpha(40);
        setLayerType(2, null);
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        float r1 = AndroidUtilities.dp(10.0f);
        float r4 = r1 * 2.0f;
        float r2 = getMeasuredWidth() - r4;
        float r5 = getMeasuredHeight() - r4;
        r13.save();
        RectF r42 = AndroidUtilities.rectTmp;
        float r6 = r1 + r2;
        r42.set(r1, r1, r6, r6);
        r42.offset(0.0f, (r5 - r42.height()) / 2.0f);
        float r22 = r2 / 7.0f;
        this.path.rewind();
        this.path.addRoundRect(r42, r22, r22, Path.Direction.CW);
        r13.clipPath(this.path);
        int r0 = AndroidUtilities.dp(10.0f);
        r13.save();
        r13.translate(r42.left, r42.top);
        float r8 = r0;
        int r02 = ((int) (r42.width() / r8)) + 1;
        int r23 = ((int) (r42.height() / r8)) + 1;
        int r43 = 0;
    L3:
        if (r43 >= r23) goto L17;
        r13.save();
        int r11 = 0;
    L5:
        if (r11 >= r02) goto L16;
        int r52 = r11 % 2;
        if (r52 == 0) goto L9;
    L10:
        if (r52 != 0) goto L12;
    L14:
        Canvas r53 = r13;
    L15:
        r53.translate(r8, 0.0f);
        r11 = r11 + 1;
        r13 = r53;
        goto L5
    L12:
        if ((r43 % 2) == 0) goto L14;
    L13:
        r53 = r13;
        r53.drawRect(0.0f, 0.0f, r8, r8, this.backgroundPaint);
        goto L15
    L9:
        if ((r43 % 2) == 0) goto L13;
    L16:
        Canvas r54 = r13;
        r54.restore();
        r54.translate(0.0f, r8);
        r43 = r43 + 1;
        goto L3
    L17:
        Canvas r55 = r13;
        r55.restore();
        r55.restore();
    }
}
