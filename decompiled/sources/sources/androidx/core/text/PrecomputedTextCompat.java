package androidx.core.text;

import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;

/* loaded from: classes3.dex */
public abstract class PrecomputedTextCompat implements Spannable {

    public static final class Params {
        private final int mBreakStrategy;
        private final int mHyphenationFrequency;
        private final TextPaint mPaint;
        private final TextDirectionHeuristic mTextDir;
        final PrecomputedText.Params mWrapped;

        public static class Builder {
            private int mBreakStrategy;
            private int mHyphenationFrequency;
            private final TextPaint mPaint;
            private TextDirectionHeuristic mTextDir;

            public Builder(TextPaint r1) {
                this.mPaint = r1;
                this.mBreakStrategy = 1;
                this.mHyphenationFrequency = 1;
                this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
            }

            public Params build() {
                return new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
            }

            public Builder setBreakStrategy(int r1) {
                this.mBreakStrategy = r1;
                return this;
            }

            public Builder setHyphenationFrequency(int r1) {
                this.mHyphenationFrequency = r1;
                return this;
            }

            public Builder setTextDirection(TextDirectionHeuristic r1) {
                this.mTextDir = r1;
                return this;
            }
        }

        public Params(PrecomputedText.Params r3) {
            this.mPaint = r3.getTextPaint();
            this.mTextDir = r3.getTextDirection();
            this.mBreakStrategy = r3.getBreakStrategy();
            this.mHyphenationFrequency = r3.getHyphenationFrequency();
            if (Build.VERSION.SDK_INT >= 29) goto L6;
            r3 = null;
        L6:
            this.mWrapped = r3;
        }

        public Params(TextPaint r3, TextDirectionHeuristic r4, int r5, int r6) {
            if (Build.VERSION.SDK_INT < 29) goto L5;
            this.mWrapped = PrecomputedTextCompat$Params$$ExternalSyntheticApiModelOutline0.m(r3).setBreakStrategy(r5).setHyphenationFrequency(r6).setTextDirection(r4).build();
        L6:
            this.mPaint = r3;
            this.mTextDir = r4;
            this.mBreakStrategy = r5;
            this.mHyphenationFrequency = r6;
            return;
        L5:
            this.mWrapped = null;
            goto L6
        }

        public boolean equals(Object r4) {
            if (r4 != this) goto L6;
            return true;
        L6:
            if ((r4 instanceof Params) == true) goto L8;
            return false;
        L8:
            Params r42 = (Params) r4;
            if (equalsWithoutTextDirection(r42) == true) goto L12;
            return false;
        L12:
            if (this.mTextDir != r42.getTextDirection()) goto L14;
            return true;
        L14:
            return false;
        }

        public boolean equalsWithoutTextDirection(Params r4) {
            if (this.mBreakStrategy == r4.getBreakStrategy()) goto L6;
            return false;
        L6:
            if (this.mHyphenationFrequency == r4.getHyphenationFrequency()) goto L9;
            return false;
        L9:
            if (this.mPaint.getTextSize() == r4.getTextPaint().getTextSize()) goto L12;
            return false;
        L12:
            if (this.mPaint.getTextScaleX() == r4.getTextPaint().getTextScaleX()) goto L15;
            return false;
        L15:
            if (this.mPaint.getTextSkewX() == r4.getTextPaint().getTextSkewX()) goto L18;
            return false;
        L18:
            if (this.mPaint.getLetterSpacing() == r4.getTextPaint().getLetterSpacing()) goto L21;
            return false;
        L21:
            if (TextUtils.equals(this.mPaint.getFontFeatureSettings(), r4.getTextPaint().getFontFeatureSettings()) == true) goto L24;
            return false;
        L24:
            if (this.mPaint.getFlags() == r4.getTextPaint().getFlags()) goto L27;
            return false;
        L27:
            if (this.mPaint.getTextLocales().equals(r4.getTextPaint().getTextLocales()) == true) goto L30;
            return false;
        L30:
            if (this.mPaint.getTypeface() != null) goto L35;
            if (r4.getTextPaint().getTypeface() == null) goto L37;
            return false;
        L37:
            return true;
        L35:
            if (this.mPaint.getTypeface().equals(r4.getTextPaint().getTypeface()) == true) goto L39;
            return false;
        L39:
            return true;
        }

        public int getBreakStrategy() {
            return this.mBreakStrategy;
        }

        public int getHyphenationFrequency() {
            return this.mHyphenationFrequency;
        }

        public TextDirectionHeuristic getTextDirection() {
            return this.mTextDir;
        }

        public TextPaint getTextPaint() {
            return this.mPaint;
        }

        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{Float.valueOf(this.mPaint.getTextSize()), Float.valueOf(this.mPaint.getTextScaleX()), Float.valueOf(this.mPaint.getTextSkewX()), Float.valueOf(this.mPaint.getLetterSpacing()), Integer.valueOf(this.mPaint.getFlags()), this.mPaint.getTextLocales(), this.mPaint.getTypeface(), Boolean.valueOf(this.mPaint.isElegantTextHeight()), this.mTextDir, Integer.valueOf(this.mBreakStrategy), Integer.valueOf(this.mHyphenationFrequency)});
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("{");
            r0.append("textSize=" + this.mPaint.getTextSize());
            r0.append(", textScaleX=" + this.mPaint.getTextScaleX());
            r0.append(", textSkewX=" + this.mPaint.getTextSkewX());
            r0.append(", letterSpacing=" + this.mPaint.getLetterSpacing());
            r0.append(", elegantTextHeight=" + this.mPaint.isElegantTextHeight());
            r0.append(", textLocale=" + this.mPaint.getTextLocales());
            r0.append(", typeface=" + this.mPaint.getTypeface());
            if (Build.VERSION.SDK_INT < 26) goto L5;
            r0.append(", variationSettings=" + this.mPaint.getFontVariationSettings());
        L5:
            r0.append(", textDir=" + this.mTextDir);
            r0.append(", breakStrategy=" + this.mBreakStrategy);
            r0.append(", hyphenationFrequency=" + this.mHyphenationFrequency);
            r0.append("}");
            return r0.toString();
        }
    }
}
