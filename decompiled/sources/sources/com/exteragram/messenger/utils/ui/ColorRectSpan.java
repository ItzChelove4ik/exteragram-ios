package com.exteragram.messenger.utils.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ReplacementSpan;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J4\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016JR\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/exteragram/messenger/utils/ui/ColorRectSpan;", "Landroid/text/style/ReplacementSpan;", "color", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "getSize", "paint", "Landroid/graphics/Paint;", "text", _UrlKt.FRAGMENT_ENCODE_SET, "start", "end", "fm", "Landroid/graphics/Paint$FontMetricsInt;", "draw", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "x", _UrlKt.FRAGMENT_ENCODE_SET, "top", "y", "bottom", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ColorRectSpan extends ReplacementSpan {
    public static final Companion Companion = null;
    private static final Paint colorPaint = null;
    private static final int offset = 0;
    private final int color;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/utils/ui/ColorRectSpan$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "colorPaint", "Landroid/graphics/Paint;", "offset", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        colorPaint = new Paint(1);
        offset = AndroidUtilities.dp(2.0f);
    }

    public ColorRectSpan(int r1) {
        this.color = r1;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r8, CharSequence r9, int r10, int r11, float r12, int r13, int r14, int r15, Paint r16) {
        android.text.TextPaint r6 = (android.text.TextPaint) r16;
        if ((r9 instanceof Spanned) == false) goto L10;
        Iterator r0 = ArrayIteratorKt.iterator((CharacterStyle[]) ((Spanned) r9).getSpans(r10, r11, CharacterStyle.class));
    L6:
        if (r0.hasNext() == false) goto L10;
        CharacterStyle r1 = (CharacterStyle) r0.next();
        if (r1 == this) goto L6;
        r1.updateDrawState(r6);
    L10:
        r8.drawText(r9, r10, r11, r12, r14, r6);
        float r142 = r6.getTextSize() * 0.9f;
        float r92 = r6.measureText(r9, r10, r11);
        float r102 = (r15 + r13) / 2.0f;
        float r112 = r142 / 2.0f;
        float r122 = (r12 + r92) + offset;
        float r93 = r102 - r112;
        float r132 = r122 + r142;
        float r103 = r102 + r112;
        float r143 = r142 * 0.285f;
        Paint r113 = colorPaint;
        r113.setColor(this.color);
        r8.drawRoundRect(r122, r93, r132, r103, r143, r143, r113);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
        return MathKt.roundToInt((r1.measureText(r2, r3, r4) + offset) + ((int) r1.getTextSize()));
    }
}
