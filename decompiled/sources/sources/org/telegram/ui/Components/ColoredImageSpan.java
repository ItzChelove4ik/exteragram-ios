package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class ColoredImageSpan extends ReplacementSpan {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_DEFAULT = 0;
    private float alpha;
    private Runnable checkColorDelegate;
    int colorKey;
    public boolean draw;
    public Drawable drawable;
    int drawableColor;
    private Paint.FontMetricsInt fontMetrics;
    private boolean isRelativeSize;
    private int overrideColor;
    public boolean recolorDrawable;
    public float rotate;
    private float scaleX;
    private float scaleY;
    private int size;
    private int sizeWidth;
    public float spaceScaleX;
    private int topOffset;
    public float translateX;
    public float translateY;
    public boolean useLinkPaintColor;
    boolean usePaintColor;
    private final int verticalAlignment;

    public ColoredImageSpan(int r2) {
        this(r2, 0);
    }

    public ColoredImageSpan(int r2, int r3) {
        this(ContextCompat.getDrawable(ApplicationLoader.applicationContext, r2).mutate(), r3);
    }

    public ColoredImageSpan(Drawable r2) {
        this(r2, 0);
    }

    public ColoredImageSpan(Drawable r4, int r5) {
        this.draw = true;
        this.recolorDrawable = true;
        this.usePaintColor = true;
        this.useLinkPaintColor = false;
        this.topOffset = 0;
        this.alpha = 1.0f;
        this.spaceScaleX = 1.0f;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.drawable = r4;
        if (r4 == null) goto L5;
        r4.setBounds(0, 0, r4.getIntrinsicWidth(), r4.getIntrinsicHeight());
    L5:
        this.verticalAlignment = r5;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
        if (this.draw == true) goto L5;
        return;
    L5:
        Runnable r22 = this.checkColorDelegate;
        if (r22 == null) goto L9;
        r22.run();
    L9:
        if (this.recolorDrawable == false) goto L25;
        int r23 = this.overrideColor;
        if (r23 == 0) goto L14;
    L22:
        int r24 = ColorUtils.setAlphaComponent(r23, (int) (this.alpha * Color.alpha(r23)));
        if (this.drawableColor == r24) goto L25;
        this.drawableColor = r24;
        this.drawable.setColorFilter(new PorterDuffColorFilter(this.drawableColor, PorterDuff.Mode.SRC_IN));
        goto L25
    L14:
        if (this.useLinkPaintColor == false) goto L19;
        if ((r9 instanceof TextPaint) == false) goto L19;
        r23 = ((TextPaint) r9).linkColor;
    L19:
        if (this.usePaintColor == false) goto L21;
        r23 = r9.getColor();
        goto L22
    L21:
        r23 = Theme.getColor(this.colorKey);
    L25:
        r1.save();
        Drawable r25 = this.drawable;
        if (r25 == null) goto L28;
        int r26 = r25.getBounds().bottom;
    L29:
        int r27 = r8 - r26;
        int r32 = this.verticalAlignment;
        if (r32 != 1) goto L33;
    L45:
        r1.translate(r5 + this.translateX, r27 + this.translateY);
        if (this.drawable == null) goto L56;
        float r33 = this.scaleX;
        if (r33 == 1.0f) goto L50;
    L51:
        r1.scale(r33, this.scaleY, 0.0f, r2.getBounds().centerY());
    L52:
        float r28 = this.rotate;
        if (r28 == 1.0f) goto L55;
        r1.rotate(r28, this.drawable.getBounds().centerX(), this.drawable.getBounds().centerY());
    L55:
        this.drawable.draw(r1);
        goto L56
    L50:
        if (this.scaleY == 1.0f) goto L52;
    L56:
        r1.restore();
        return;
    L33:
        if (r32 != 2) goto L39;
        int r62 = r6 + ((r8 - r6) / 2);
        Drawable r29 = this.drawable;
        if (r29 == null) goto L37;
        int r210 = r29.getBounds().height() / 2;
    L38:
        r27 = r62 - r210;
        goto L45
    L37:
        r210 = 0;
        goto L38
    L39:
        if (r32 != 0) goto L45;
        int r82 = r8 - r6;
        int r211 = this.size;
        if (r211 != 0) goto L44;
        r211 = this.drawable.getIntrinsicHeight();
    L44:
        r27 = AndroidUtilities.dp(this.topOffset) + (r6 + ((r82 - r211) / 2));
        goto L45
    L28:
        r26 = r8;
        goto L29
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        if (this.isRelativeSize == true) goto L5;
    L12:
        int r12 = this.sizeWidth;
        float r22 = this.scaleX;
        if (r12 == 0) goto L15;
        float r13 = Math.abs(r22);
        int r0 = this.sizeWidth;
    L9:
        float r02 = r0;
    L11:
        return (int) (r13 * r02);
    L15:
        r13 = Math.abs(r22) * Math.abs(this.spaceScaleX);
        int r23 = this.size;
        if (r23 != 0) goto L19;
        r23 = this.drawable.getIntrinsicWidth();
    L19:
        r02 = r23;
        goto L11
    L5:
        if (this.fontMetrics == null) goto L12;
        if (r5 != null) goto L8;
        r5 = new Paint.FontMetricsInt();
    L8:
        Paint.FontMetricsInt r14 = this.fontMetrics;
        r5.ascent = r14.ascent;
        r5.descent = r14.descent;
        r5.top = r14.top;
        r5.bottom = r14.bottom;
        r13 = Math.abs(this.scaleX) * Math.abs(this.spaceScaleX);
        r0 = this.size;
        goto L9
    }

    public void rotate(float r1) {
        this.rotate = r1;
    }

    public void setAlpha(float r1) {
        this.alpha = r1;
    }

    public void setCheckColorDelegate(Runnable r1) {
        this.checkColorDelegate = r1;
    }

    public void setColorKey(int r1) {
        this.colorKey = r1;
        if (r1 >= 0) goto L5;
        boolean r12 = true;
    L6:
        this.usePaintColor = r12;
        return;
    L5:
        r12 = false;
        goto L6
    }

    public void setOverrideColor(int r1) {
        this.overrideColor = r1;
    }

    public void setRelativeSize(Paint.FontMetricsInt r2) {
        this.isRelativeSize = true;
        this.fontMetrics = r2;
        if (r2 == null) goto L8;
        setSize(Math.abs(r2.descent) + Math.abs(this.fontMetrics.ascent));
        if (this.size != 0) goto L9;
        setSize(AndroidUtilities.dp(20.0f));
        return;
    L9:
        return;
    }

    public void setScale(float r1) {
        this.scaleX = r1;
    }

    public void setScale(float r1, float r2) {
        this.scaleX = r1;
        this.scaleY = r2;
    }

    public void setSize(int r2) {
        this.size = r2;
        this.drawable.setBounds(0, 0, r2, r2);
    }

    public void setTopOffset(int r1) {
        this.topOffset = r1;
    }

    public void setTranslateX(float r1) {
        this.translateX = r1;
    }

    public void setTranslateY(float r1) {
        this.translateY = r1;
    }

    public void setWidth(int r1) {
        this.sizeWidth = r1;
    }

    public void translate(float r1, float r2) {
        this.translateX = r1;
        this.translateY = r2;
    }
}
