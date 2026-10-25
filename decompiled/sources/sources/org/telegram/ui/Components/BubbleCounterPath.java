package org.telegram.ui.Components;

import android.graphics.Path;
import android.graphics.RectF;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes3.dex */
public abstract class BubbleCounterPath {
    private static RectF tmpRect;

    public static void addBubbleRect(Path r9, RectF r10, float r11) {
        if (r9 != null) goto L5;
        return;
    L5:
        if (tmpRect != null) goto L7;
        tmpRect = new RectF();
    L7:
        float r1 = 2.0f * r11;
        r9.rewind();
        tmpRect.set(0.0f, -r10.height(), r1, (-r10.height()) + r1);
        r9.arcTo(tmpRect, 180.0f, 90.0f);
        tmpRect.set(r10.width() - r1, -r10.height(), r10.width(), (-r10.height()) + r1);
        r9.arcTo(tmpRect, 270.0f, 90.0f);
        tmpRect.set(r10.width() - r1, -r1, r10.width(), 0.0f);
        r9.arcTo(tmpRect, 0.0f, 90.0f);
        r9.quadTo(r11, 0.0f, r11, 0.0f);
        r9.cubicTo(AndroidUtilities.dp(7.62f), AndroidUtilities.dp(-0.5f), AndroidUtilities.dp(5.807f), AndroidUtilities.dp(-1.502f), AndroidUtilities.dp(6.02f), AndroidUtilities.dp(-1.386f));
        r9.cubicTo(AndroidUtilities.dp(4.814f), AndroidUtilities.dp(-0.81f), AndroidUtilities.dp(2.706f), AndroidUtilities.dp(-0.133f), AndroidUtilities.dp(3.6f), AndroidUtilities.dp(-0.44f));
        r9.cubicTo(AndroidUtilities.dp(1.004f), AndroidUtilities.dp(-0.206f), AndroidUtilities.dp(-0.047f), AndroidUtilities.dp(-0.32f), AndroidUtilities.dp(0.247f), AndroidUtilities.dp(-0.29f));
        r9.cubicTo(AndroidUtilities.dp(-0.334f), AndroidUtilities.dp(-1.571f), 0.0f, AndroidUtilities.dp(-1.155f), AndroidUtilities.dp(-0.06f), AndroidUtilities.dp(-1.154f));
        r9.cubicTo(AndroidUtilities.dp(1.083f), AndroidUtilities.dp(-2.123f), AndroidUtilities.dp(1.667f), AndroidUtilities.dp(-3.667f), AndroidUtilities.dp(1.453f), AndroidUtilities.dp(-3.12f));
        r9.cubicTo(AndroidUtilities.dp(2.1f), AndroidUtilities.dp(-4.793f), AndroidUtilities.dp(1.24f), AndroidUtilities.dp(-6.267f), AndroidUtilities.dp(1.67f), AndroidUtilities.dp(-5.53f));
        float r12 = -r11;
        r9.quadTo(0.0f, AndroidUtilities.dp(2.187f) + r12, 0.0f, r12);
        r9.close();
        r9.offset(r10.left, r10.bottom);
    }
}
