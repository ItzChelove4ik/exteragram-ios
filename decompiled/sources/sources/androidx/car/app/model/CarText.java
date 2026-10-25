package androidx.car.app.model;

import android.text.SpannableString;
import android.text.Spanned;
import androidx.car.app.utils.CollectionUtils;
import androidx.car.app.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class CarText {
    private final List<SpanWrapper> mSpans;
    private final List<List<SpanWrapper>> mSpansForVariants;
    private final String mText;
    private final List<String> mTextVariants;

    public static final class Builder {
        CharSequence mText;
        List<CharSequence> mTextVariants;

        public Builder(CharSequence r2) {
            this.mTextVariants = new ArrayList();
            Objects.requireNonNull(r2);
            this.mText = r2;
        }

        public Builder addVariant(CharSequence r2) {
            List<CharSequence> r0 = this.mTextVariants;
            Objects.requireNonNull(r2);
            r0.add(r2);
            return this;
        }

        public CarText build() {
            return new CarText(this);
        }
    }

    public static class SpanWrapper {
        private final CarSpan mCarSpan;
        private final int mEnd;
        private final int mFlags;
        private final int mStart;

        public SpanWrapper() {
            this.mStart = 0;
            this.mEnd = 0;
            this.mFlags = 0;
            this.mCarSpan = new CarSpan();
        }

        public SpanWrapper(Spanned r2, CarSpan r3) {
            this.mStart = r2.getSpanStart(r3);
            this.mEnd = r2.getSpanEnd(r3);
            this.mFlags = r2.getSpanFlags(r3);
            this.mCarSpan = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof SpanWrapper) == true) goto L8;
            return false;
        L8:
            SpanWrapper r52 = (SpanWrapper) r5;
            if (this.mStart == r52.mStart) goto L11;
        L17:
            return false;
        L11:
            if (this.mEnd != r52.mEnd) goto L17;
            if (this.mFlags != r52.mFlags) goto L17;
            if (Objects.equals(this.mCarSpan, r52.mCarSpan) == false) goto L17;
            return true;
        }

        public CarSpan getCarSpan() {
            return this.mCarSpan;
        }

        public int getEnd() {
            return this.mEnd;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public int getStart() {
            return this.mStart;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Integer.valueOf(this.mStart), Integer.valueOf(this.mEnd), Integer.valueOf(this.mFlags), this.mCarSpan});
        }

        public String toString() {
            return "[" + this.mCarSpan + ": " + this.mStart + ", " + this.mEnd + ", flags: " + this.mFlags + "]";
        }
    }

    private CarText() {
        this.mText = _UrlKt.FRAGMENT_ENCODE_SET;
        List r0 = Collections.EMPTY_LIST;
        this.mSpans = r0;
        this.mTextVariants = r0;
        this.mSpansForVariants = r0;
    }

    public CarText(Builder r6) {
        this.mText = r6.mText.toString();
        this.mSpans = getSpans(r6.mText);
        List<CharSequence> r62 = r6.mTextVariants;
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= r62.size()) goto L6;
        CharSequence r3 = r62.get(r2);
        r0.add(r3.toString());
        r1.add(getSpans(r3));
        r2 = r2 + 1;
        goto L4
    L6:
        this.mTextVariants = CollectionUtils.unmodifiableCopy(r0);
        this.mSpansForVariants = CollectionUtils.unmodifiableCopy(r1);
    }

    public CarText(CharSequence r2) {
        this.mText = r2.toString();
        this.mSpans = getSpans(r2);
        List r22 = Collections.EMPTY_LIST;
        this.mTextVariants = r22;
        this.mSpansForVariants = r22;
    }

    public static CarText create(CharSequence r1) {
        Objects.requireNonNull(r1);
        return new CarText(r1);
    }

    private static CharSequence getCharSequence(String r4, List<SpanWrapper> r5) {
        SpannableString r0 = new SpannableString(r4);
        Iterator r42 = CollectionUtils.emptyIfNull(r5).iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        SpanWrapper r52 = (SpanWrapper) r42.next();
        r0.setSpan(r52.getCarSpan(), r52.getStart(), r52.getEnd(), r52.getFlags());
        goto L4
    L6:
        return r0;
    }

    private static List<SpanWrapper> getSpans(CharSequence r6) {
        ArrayList r0 = new ArrayList();
        if ((r6 instanceof Spanned) == false) goto L11;
        Spanned r1 = (Spanned) r6;
        int r3 = 0;
        Object[] r62 = r1.getSpans(0, r6.length(), Object.class);
        int r2 = r62.length;
    L5:
        if (r3 >= r2) goto L11;
        Object r4 = r62[r3];
        if ((r4 instanceof CarSpan) == false) goto L9;
        r0.add(new SpanWrapper(r1, (CarSpan) r4));
    L9:
        r3 = r3 + 1;
    L11:
        return CollectionUtils.unmodifiableCopy(r0);
    }

    public static boolean isNullOrEmpty(CarText r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }

    public static String toShortString(CarText r0) {
        if (r0 != null) goto L6;
        return null;
    L6:
        return StringUtils.shortenString(r0.toString());
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof CarText) == true) goto L8;
        return false;
    L8:
        CarText r52 = (CarText) r5;
        if (Objects.equals(this.mText, r52.mText) == true) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equals(this.mSpans, r52.mSpans) == false) goto L17;
        if (Objects.equals(this.mTextVariants, r52.mTextVariants) == false) goto L17;
        if (Objects.equals(this.mSpansForVariants, r52.mSpansForVariants) == false) goto L17;
        return true;
    }

    public List<SpanWrapper> getSpans() {
        return this.mSpans;
    }

    public List<List<SpanWrapper>> getSpansForVariants() {
        return this.mSpansForVariants;
    }

    public List<CharSequence> getVariants() {
        if (this.mTextVariants.isEmpty() == true) goto L5;
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L8:
        if (r1 >= this.mTextVariants.size()) goto L11;
        r0.add(getCharSequence(this.mTextVariants.get(r1), this.mSpansForVariants.get(r1)));
        r1 = r1 + 1;
        goto L8
    L11:
        return Collections.unmodifiableList(r0);
    L5:
        return Collections.EMPTY_LIST;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mText, this.mSpans, this.mTextVariants, this.mSpansForVariants});
    }

    public boolean isEmpty() {
        return this.mText.isEmpty();
    }

    public CharSequence toCharSequence() {
        return getCharSequence(this.mText, this.mSpans);
    }

    public String toString() {
        return this.mText;
    }
}
