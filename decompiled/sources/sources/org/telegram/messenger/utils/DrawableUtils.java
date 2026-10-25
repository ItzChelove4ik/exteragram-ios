package org.telegram.messenger.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes.dex */
public abstract class DrawableUtils {
    private static final Rect tmpRect = null;
    private static final Rect tmpRect2 = null;
    private static final RectF tmpRectF = null;

    static {
        tmpRect = new Rect();
        tmpRect2 = new Rect();
        tmpRectF = new RectF();
    }

    public static void drawCommunityCardDrawable(Canvas r3, Drawable r4, float r5, float r6, float r7) {
        float r1 = r5 - AndroidUtilities.dpf2(36.0f);
        float r0 = r6 - AndroidUtilities.dpf2(36.0f);
        float r72 = r7 / AndroidUtilities.dpf2(72.0f);
        setBounds(r4, r1 + AndroidUtilities.dpf2(9.66f), r0 + AndroidUtilities.dpf2(4.66f), 53);
        r3.save();
        r3.scale(r72, r72, r5, r6);
        r4.draw(r3);
        r3.restore();
    }

    public static void drawWithScale(Canvas r2, Drawable r3, float r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r4 != 0.0f) goto L7;
        return;
    L7:
        if (r4 != 1.0f) goto L10;
        r3.draw(r2);
        return;
    L10:
        r2.save();
        r2.scale(r4, r4, r3.getBounds().exactCenterX(), r3.getBounds().exactCenterY());
        r3.draw(r2);
        r2.restore();
    }

    public static void setBounds(Rect r3, float r4, float r5, int r6, int r7, int r8) {
        int r0 = r8 & 7;
        if (r0 != 3) goto L5;
        int r42 = Math.round(r4);
    L9:
        int r82 = r8 & 112;
        if (r82 != 48) goto L12;
        int r52 = Math.round(r5);
    L16:
        r3.set(r42, r52, r6 + r42, r7 + r52);
        return;
    L12:
        if (r82 == 80) goto L14;
        r52 = Math.round(r5 - (r7 / 2.0f));
        goto L16
    L14:
        r52 = Math.round(r5 - r7);
        goto L16
    L5:
        if (r0 == 5) goto L7;
        r42 = Math.round(r4 - (r6 / 2.0f));
        goto L9
    L7:
        r42 = Math.round(r4 - r6);
        goto L9
    }

    public static void setBounds(Drawable r6, float r7, float r8, int r9) {
        if (r6 != null) goto L4;
        return;
    L4:
        setBounds(r6, r7, r8, r6.getIntrinsicWidth(), r6.getIntrinsicHeight(), r9);
    }

    public static void setBounds(Drawable r6, float r7, float r8, int r9, int r10, int r11) {
        if (r6 == null) goto L5;
        Rect r0 = tmpRect;
        setBounds(r0, r7, r8, r9, r10, r11);
        r6.setBounds(r0);
        return;
    }

    public static void setBounds(ImageReceiver r6, float r7, float r8, int r9, int r10, int r11) {
        if (r6 == null) goto L5;
        Rect r0 = tmpRect;
        setBounds(r0, r7, r8, r9, r10, r11);
        r6.setImageCoords(r0);
        return;
    }

    public static void setBoundsIncreasePadding(Drawable r2, int r3, int r4, int r5, int r6) {
        Rect r0 = tmpRect;
        if (r2.getPadding(r0) == false) goto L6;
        r2.setBounds(r3 - r0.left, r4 - r0.top, r5 + r0.right, r6 + r0.bottom);
        return;
    L6:
        r2.setBounds(r3, r4, r5, r6);
    }

    public static void setBoundsIncreasePadding(Drawable r5, Rect r6) {
        Rect r0 = tmpRect;
        if (r5.getPadding(r0) == false) goto L6;
        r5.setBounds(r6.left - r0.left, r6.top - r0.top, r6.right + r0.right, r6.bottom + r0.bottom);
        return;
    L6:
        r5.setBounds(r6);
    }
}
