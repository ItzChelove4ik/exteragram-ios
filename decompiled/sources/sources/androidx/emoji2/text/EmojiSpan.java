package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
public abstract class EmojiSpan extends ReplacementSpan {
    private short mHeight;
    private final TypefaceEmojiRasterizer mRasterizer;
    private float mRatio;
    private final Paint.FontMetricsInt mTmpFontMetrics;
    private short mWidth;

    public EmojiSpan(TypefaceEmojiRasterizer r2) {
        this.mTmpFontMetrics = new Paint.FontMetricsInt();
        this.mWidth = -1;
        this.mHeight = -1;
        this.mRatio = 1.0f;
        Preconditions.checkNotNull(r2, "rasterizer cannot be null");
        this.mRasterizer = r2;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, @SuppressLint({"UnknownNullness"}) CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        r1.getFontMetricsInt(this.mTmpFontMetrics);
        Paint.FontMetricsInt r12 = this.mTmpFontMetrics;
        this.mRatio = (Math.abs(r12.descent - r12.ascent) * 1.0f) / this.mRasterizer.getHeight();
        this.mHeight = (short) (this.mRasterizer.getHeight() * this.mRatio);
        short r13 = (short) (this.mRasterizer.getWidth() * this.mRatio);
        this.mWidth = r13;
        if (r5 == null) goto L5;
        Paint.FontMetricsInt r0 = this.mTmpFontMetrics;
        r5.ascent = r0.ascent;
        r5.descent = r0.descent;
        r5.top = r0.top;
        r5.bottom = r0.bottom;
    L5:
        return r13;
    }

    public final TypefaceEmojiRasterizer getTypefaceRasterizer() {
        return this.mRasterizer;
    }

    public final int getWidth() {
        return this.mWidth;
    }
}
