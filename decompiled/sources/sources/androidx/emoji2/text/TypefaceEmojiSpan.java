package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* loaded from: classes3.dex */
public final class TypefaceEmojiSpan extends EmojiSpan {
    private static Paint sDebugPaint;
    private TextPaint mWorkingPaint;

    public TypefaceEmojiSpan(TypefaceEmojiRasterizer r1) {
        super(r1);
    }

    private TextPaint applyCharacterSpanStyles(CharSequence r3, int r4, int r5, Paint r6) {
        if ((r3 instanceof Spanned) == false) goto L25;
        CharacterStyle[] r32 = (CharacterStyle[]) ((Spanned) r3).getSpans(r4, r5, CharacterStyle.class);
        if (r32.length == 0) goto L20;
        int r52 = 0;
        if (r32.length == 1) goto L9;
    L11:
        TextPaint r42 = this.mWorkingPaint;
        if (r42 != null) goto L14;
        r42 = new TextPaint();
        this.mWorkingPaint = r42;
    L14:
        r42.set(r6);
    L16:
        if (r52 >= r32.length) goto L18;
        r32[r52].updateDrawState(r42);
        r52 = r52 + 1;
        goto L16
    L18:
        return r42;
    L9:
        if (r32[0] != this) goto L11;
    L20:
        if ((r6 instanceof TextPaint) == true) goto L22;
        return null;
    L22:
        return (TextPaint) r6;
    L25:
        if ((r6 instanceof TextPaint) == true) goto L27;
        return null;
    L27:
        return (TextPaint) r6;
    }

    private static Paint getDebugPaint() {
        if (sDebugPaint != null) goto L6;
        TextPaint r0 = new TextPaint();
        sDebugPaint = r0;
        r0.setColor(EmojiCompat.get().getEmojiSpanIndicatorColor());
        sDebugPaint.setStyle(Paint.Style.FILL);
    L6:
        return sDebugPaint;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas r11, @SuppressLint({"UnknownNullness"}) CharSequence r12, int r13, int r14, float r15, int r16, int r17, int r18, Paint r19) {
        TextPaint r5 = applyCharacterSpanStyles(r12, r13, r14, r19);
        if (r5 != null) goto L5;
    L7:
        Paint r122 = r5;
        if (EmojiCompat.get().isEmojiSpanIndicatorEnabled() == false) goto L10;
        r11.drawRect(r15, r16, r15 + getWidth(), r18, getDebugPaint());
    L10:
        TypefaceEmojiRasterizer r10 = getTypefaceRasterizer();
        float r132 = r17;
        if (r122 != null) goto L14;
        r122 = r19;
    L14:
        r10.draw(r11, r15, r132, r122);
        return;
    L5:
        if (r5.bgColor == 0) goto L7;
        drawBackground(r11, r5, r15, r15 + getWidth(), r16, r18);
        goto L7
    }

    public void drawBackground(Canvas r4, TextPaint r5, float r6, float r7, float r8, float r9) {
        int r3 = r5.getColor();
        Paint.Style r0 = r5.getStyle();
        r5.setColor(r5.bgColor);
        r5.setStyle(Paint.Style.FILL);
        r4.drawRect(r6, r8, r7, r9, r5);
        r5.setStyle(r0);
        r5.setColor(r3);
    }
}
