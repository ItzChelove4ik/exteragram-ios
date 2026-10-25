package com.google.android.material.internal;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import java.lang.reflect.Constructor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class StaticLayoutBuilderCompat {
    static final int DEFAULT_HYPHENATION_FREQUENCY = 1;
    static final float DEFAULT_LINE_SPACING_ADD = 0.0f;
    static final float DEFAULT_LINE_SPACING_MULTIPLIER = 1.0f;
    private static Constructor<StaticLayout> constructor;
    private static boolean initialized;
    private static Object textDirection;
    private Layout.Alignment alignment;
    private TextUtils.TruncateAt ellipsize;
    private int end;
    private int hyphenationFrequency;
    private boolean includePad;
    private boolean isRtl;
    private float lineSpacingAdd;
    private float lineSpacingMultiplier;
    private int maxLines;
    private final TextPaint paint;
    private CharSequence source;
    private int start;
    private StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer;
    private final int width;

    public static class StaticLayoutBuilderCompatException extends Exception {
        public StaticLayoutBuilderCompatException(Throwable r3) {
            super("Error thrown initializing StaticLayout " + r3.getMessage(), r3);
        }
    }

    static {
    }

    private StaticLayoutBuilderCompat(CharSequence r1, TextPaint r2, int r3) {
        this.source = r1;
        this.paint = r2;
        this.width = r3;
        this.start = 0;
        this.end = r1.length();
        this.alignment = Layout.Alignment.ALIGN_NORMAL;
        this.maxLines = Integer.MAX_VALUE;
        this.lineSpacingAdd = DEFAULT_LINE_SPACING_ADD;
        this.lineSpacingMultiplier = 1.0f;
        this.hyphenationFrequency = DEFAULT_HYPHENATION_FREQUENCY;
        this.includePad = true;
        this.ellipsize = null;
    }

    private void createConstructorWithReflection() {
        if (initialized == false) goto L14;
        return;
    L14:
    L11:
        e = move-exception;
        throw new StaticLayoutBuilderCompatException(e);
    L6:
        if (this.isRtl == false) goto L8;
        TextDirectionHeuristic r13 = TextDirectionHeuristics.RTL;     // Catch: Exception -> L11
    L9:
        textDirection = r13;     // Catch: Exception -> L11
        Class r1 = Integer.TYPE;     // Catch: Exception -> L11
        Class r7 = Float.TYPE;     // Catch: Exception -> L11
        Constructor<StaticLayout> r132 = StaticLayout.class.getDeclaredConstructor(new Class[]{CharSequence.class, r1, r1, TextPaint.class, r1, Layout.Alignment.class, TextDirectionHeuristic.class, r7, r7, Boolean.TYPE, TextUtils.TruncateAt.class, r1, r1});     // Catch: Exception -> L11
        constructor = r132;     // Catch: Exception -> L11
        r132.setAccessible(true);     // Catch: Exception -> L11
        initialized = true;     // Catch: Exception -> L11
        return;
    L8:
        r13 = TextDirectionHeuristics.LTR;     // Catch: Exception -> L11
        goto L9
    }

    public static StaticLayoutBuilderCompat obtain(CharSequence r1, TextPaint r2, int r3) {
        return new StaticLayoutBuilderCompat(r1, r2, r3);
    }

    public StaticLayout build() {
        if (this.source != null) goto L5;
        this.source = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        int r0 = Math.max(0, this.width);
        CharSequence r1 = this.source;
        if (this.maxLines != 1) goto L8;
        r1 = TextUtils.ellipsize(r1, this.paint, r0, this.ellipsize);
    L8:
        int r2 = Math.min(r1.length(), this.end);
        this.end = r2;
        if (this.isRtl == true) goto L11;
    L13:
        StaticLayout.Builder r02 = StaticLayout.Builder.obtain(r1, this.start, r2, this.paint, r0);
        r02.setAlignment(this.alignment);
        r02.setIncludePad(this.includePad);
        if (this.isRtl == false) goto L16;
        TextDirectionHeuristic r12 = TextDirectionHeuristics.RTL;
    L17:
        r02.setTextDirection(r12);
        TextUtils.TruncateAt r13 = this.ellipsize;
        if (r13 == null) goto L20;
        r02.setEllipsize(r13);
    L20:
        r02.setMaxLines(this.maxLines);
        float r14 = this.lineSpacingAdd;
        if (r14 == DEFAULT_LINE_SPACING_ADD) goto L23;
    L24:
        r02.setLineSpacing(r14, this.lineSpacingMultiplier);
    L26:
        if (this.maxLines <= 1) goto L28;
        r02.setHyphenationFrequency(this.hyphenationFrequency);
    L28:
        StaticLayoutBuilderConfigurer r6 = this.staticLayoutBuilderConfigurer;
        if (r6 == null) goto L32;
        r6.configure(r02);
    L32:
        return r02.build();
    L23:
        if (this.lineSpacingMultiplier == 1.0f) goto L26;
    L16:
        r12 = TextDirectionHeuristics.LTR;
        goto L17
    L11:
        if (this.maxLines != 1) goto L13;
        this.alignment = Layout.Alignment.ALIGN_OPPOSITE;
        goto L13
    }

    public StaticLayoutBuilderCompat setAlignment(Layout.Alignment r1) {
        this.alignment = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setEllipsize(TextUtils.TruncateAt r1) {
        this.ellipsize = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setEnd(int r1) {
        this.end = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setHyphenationFrequency(int r1) {
        this.hyphenationFrequency = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setIncludePad(boolean r1) {
        this.includePad = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setIsRtl(boolean r1) {
        this.isRtl = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setLineSpacing(float r1, float r2) {
        this.lineSpacingAdd = r1;
        this.lineSpacingMultiplier = r2;
        return this;
    }

    public StaticLayoutBuilderCompat setMaxLines(int r1) {
        this.maxLines = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setStart(int r1) {
        this.start = r1;
        return this;
    }

    public StaticLayoutBuilderCompat setStaticLayoutBuilderConfigurer(StaticLayoutBuilderConfigurer r1) {
        this.staticLayoutBuilderConfigurer = r1;
        return this;
    }
}
