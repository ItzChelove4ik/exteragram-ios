package androidx.emoji2.text;

import android.text.TextPaint;
import androidx.core.graphics.PaintCompat;
import androidx.emoji2.text.EmojiCompat;

/* loaded from: classes.dex */
class DefaultGlyphChecker implements EmojiCompat.GlyphChecker {
    private static final ThreadLocal<StringBuilder> sStringBuilder = null;
    private final TextPaint mTextPaint;

    static {
        sStringBuilder = new ThreadLocal();
    }

    public DefaultGlyphChecker() {
        TextPaint r0 = new TextPaint();
        this.mTextPaint = r0;
        r0.setTextSize(10.0f);
    }

    private static StringBuilder getStringBuilder() {
        ThreadLocal<StringBuilder> r0 = sStringBuilder;
        if (r0.get() != null) goto L6;
        r0.set(new StringBuilder());
    L6:
        return r0.get();
    }

    @Override // androidx.emoji2.text.EmojiCompat.GlyphChecker
    public boolean hasGlyph(CharSequence r2, int r3, int r4, int r5) {
        StringBuilder r52 = getStringBuilder();
        r52.setLength(0);
    L3:
        if (r3 >= r4) goto L6;
        r52.append(r2.charAt(r3));
        r3 = r3 + 1;
        goto L3
    L6:
        return PaintCompat.hasGlyph(this.mTextPaint, r52.toString());
    }
}
