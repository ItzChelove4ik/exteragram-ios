package androidx.emoji2.text;

import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import java.util.stream.IntStream;

/* loaded from: classes3.dex */
class UnprecomputeTextOnModificationSpannable implements Spannable {
    private Spannable mDelegate;
    private boolean mSafeToWrite;

    public static class CharSequenceHelper_API24 {
        public static IntStream chars(CharSequence r0) {
            return r0.chars();
        }

        public static IntStream codePoints(CharSequence r0) {
            return r0.codePoints();
        }
    }

    public static class PrecomputedTextDetector {
        public PrecomputedTextDetector() {
        }

        public boolean isPrecomputedText(CharSequence r1) {
            return false;
        }
    }

    public static class PrecomputedTextDetector_28 extends PrecomputedTextDetector {
        public PrecomputedTextDetector_28() {
        }

        @Override // androidx.emoji2.text.UnprecomputeTextOnModificationSpannable.PrecomputedTextDetector
        public boolean isPrecomputedText(CharSequence r1) {
            if (UnprecomputeTextOnModificationSpannable$PrecomputedTextDetector_28$$ExternalSyntheticApiModelOutline0.m(r1) == true) goto L6;
            return false;
        L6:
            return true;
        }
    }

    public UnprecomputeTextOnModificationSpannable(Spannable r2) {
        this.mSafeToWrite = false;
        this.mDelegate = r2;
    }

    public UnprecomputeTextOnModificationSpannable(CharSequence r2) {
        this.mSafeToWrite = false;
        this.mDelegate = new SpannableString(r2);
    }

    private void ensureSafeWrites() {
        Spannable r0 = this.mDelegate;
        if (this.mSafeToWrite == false) goto L5;
    L7:
        this.mSafeToWrite = true;
        return;
    L5:
        if (precomputedTextDetector().isPrecomputedText(r0) == false) goto L7;
        this.mDelegate = new SpannableString(r0);
        goto L7
    }

    public static PrecomputedTextDetector precomputedTextDetector() {
        if (Build.VERSION.SDK_INT >= 28) goto L7;
        return new PrecomputedTextDetector();
    L7:
        return new PrecomputedTextDetector_28();
    }

    @Override // java.lang.CharSequence
    public char charAt(int r1) {
        return this.mDelegate.charAt(r1);
    }

    @Override // java.lang.CharSequence
    public IntStream chars() {
        return CharSequenceHelper_API24.chars(this.mDelegate);
    }

    @Override // java.lang.CharSequence
    public IntStream codePoints() {
        return CharSequenceHelper_API24.codePoints(this.mDelegate);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object r1) {
        return this.mDelegate.getSpanEnd(r1);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object r1) {
        return this.mDelegate.getSpanFlags(r1);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object r1) {
        return this.mDelegate.getSpanStart(r1);
    }

    @Override // android.text.Spanned
    public <T> T[] getSpans(int r1, int r2, Class<T> r3) {
        return (T[]) this.mDelegate.getSpans(r1, r2, r3);
    }

    public Spannable getUnwrappedSpannable() {
        return this.mDelegate;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.mDelegate.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int r1, int r2, Class r3) {
        return this.mDelegate.nextSpanTransition(r1, r2, r3);
    }

    @Override // android.text.Spannable
    public void removeSpan(Object r1) {
        ensureSafeWrites();
        this.mDelegate.removeSpan(r1);
    }

    @Override // android.text.Spannable
    public void setSpan(Object r1, int r2, int r3, int r4) {
        ensureSafeWrites();
        this.mDelegate.setSpan(r1, r2, r3, r4);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int r1, int r2) {
        return this.mDelegate.subSequence(r1, r2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.mDelegate.toString();
    }
}
