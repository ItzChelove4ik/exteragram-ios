package androidx.core.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class BidiFormatter {
    static final BidiFormatter DEFAULT_LTR_INSTANCE = null;
    static final BidiFormatter DEFAULT_RTL_INSTANCE = null;
    static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC = null;
    private static final String LRM_STRING = null;
    private static final String RLM_STRING = null;
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    public static final class Builder {
        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
        }

        private static BidiFormatter getDefaultInstanceFromContext(boolean r0) {
            if (r0 == false) goto L6;
            return BidiFormatter.DEFAULT_RTL_INSTANCE;
        L6:
            return BidiFormatter.DEFAULT_LTR_INSTANCE;
        }

        private void initialize(boolean r1) {
            this.mIsRtlContext = r1;
            this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public BidiFormatter build() {
            if (this.mFlags != 2) goto L9;
            if (this.mTextDirectionHeuristicCompat != BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC) goto L9;
            return getDefaultInstanceFromContext(this.mIsRtlContext);
        L9:
            return new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat);
        }
    }

    public static class DirectionalityEstimator {
        private static final byte[] DIR_TYPE_CACHE = null;
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final CharSequence text;

        static {
            DIR_TYPE_CACHE = new byte[1792];
            int r1 = 0;
        L3:
            if (r1 >= 1792) goto L5;
            DIR_TYPE_CACHE[r1] = Character.getDirectionality(r1);
            r1 = r1 + 1;
            goto L3
        }

        public DirectionalityEstimator(CharSequence r1, boolean r2) {
            this.text = r1;
            this.isHtml = r2;
            this.length = r1.length();
        }

        private static byte getCachedDirectionality(char r1) {
            if (r1 >= 1792) goto L7;
            return DIR_TYPE_CACHE[r1];
        L7:
            return Character.getDirectionality(r1);
        }

        private byte skipEntityBackward() {
            int r0 = this.charIndex;
        L3:
            int r1 = this.charIndex;
            if (r1 <= 0) goto L10;
            CharSequence r3 = this.text;
            int r12 = r1 - 1;
            this.charIndex = r12;
            char r13 = r3.charAt(r12);
            this.lastChar = r13;
            if (r13 == '&') goto L7;
            if (r13 != ';') goto L3;
        L7:
            return 12;
        L10:
            this.charIndex = r0;
            this.lastChar = ';';
            return 13;
        }

        private byte skipEntityForward() {
        L2:
            int r0 = this.charIndex;
            if (r0 >= this.length) goto L7;
            CharSequence r1 = this.text;
            this.charIndex = r0 + 1;
            char r02 = r1.charAt(r0);
            this.lastChar = r02;
            if (r02 != ';') goto L2;
            return 12;
        L7:
            return 12;
        }

        private byte skipTagBackward() {
            int r0 = this.charIndex;
        L3:
            int r1 = this.charIndex;
            if (r1 <= 0) goto L20;
            CharSequence r3 = this.text;
            int r12 = r1 - 1;
            this.charIndex = r12;
            char r13 = r3.charAt(r12);
            this.lastChar = r13;
            if (r13 == '<') goto L7;
            if (r13 == '>') goto L20;
            if (r13 == '\"') goto L15;
            if (r13 != '\'') goto L3;
        L15:
            int r2 = this.charIndex;
            if (r2 <= 0) goto L3;
            CharSequence r32 = this.text;
            int r22 = r2 - 1;
            this.charIndex = r22;
            char r23 = r32.charAt(r22);
            this.lastChar = r23;
            if (r23 == r13) goto L3;
        L7:
            return 12;
        L20:
            this.charIndex = r0;
            this.lastChar = Typography.greater;
            return 13;
        }

        private byte skipTagForward() {
            int r0 = this.charIndex;
        L3:
            int r1 = this.charIndex;
            if (r1 >= this.length) goto L18;
            CharSequence r2 = this.text;
            this.charIndex = r1 + 1;
            char r12 = r2.charAt(r1);
            this.lastChar = r12;
            if (r12 == '>') goto L7;
            if (r12 == '\"') goto L13;
            if (r12 != '\'') goto L3;
        L13:
            int r22 = this.charIndex;
            if (r22 >= this.length) goto L3;
            CharSequence r3 = this.text;
            this.charIndex = r22 + 1;
            char r23 = r3.charAt(r22);
            this.lastChar = r23;
            if (r23 == r12) goto L3;
        L7:
            return 12;
        L18:
            this.charIndex = r0;
            this.lastChar = Typography.less;
            return 13;
        }

        public byte dirTypeBackward() {
            char r0 = this.text.charAt(this.charIndex - 1);
            this.lastChar = r0;
            if (Character.isLowSurrogate(r0) == false) goto L6;
            int r02 = Character.codePointBefore(this.text, this.charIndex);
            this.charIndex -= Character.charCount(r02);
            return Character.getDirectionality(r02);
        L6:
            this.charIndex--;
            byte r03 = getCachedDirectionality(this.lastChar);
            if (this.isHtml == false) goto L16;
            char r1 = this.lastChar;
            if (r1 != '>') goto L13;
            return skipTagBackward();
        L13:
            if (r1 != ';') goto L16;
            return skipEntityBackward();
        L16:
            return r03;
        }

        public byte dirTypeForward() {
            char r0 = this.text.charAt(this.charIndex);
            this.lastChar = r0;
            if (Character.isHighSurrogate(r0) == false) goto L6;
            int r02 = Character.codePointAt(this.text, this.charIndex);
            this.charIndex += Character.charCount(r02);
            return Character.getDirectionality(r02);
        L6:
            this.charIndex++;
            byte r03 = getCachedDirectionality(this.lastChar);
            if (this.isHtml == false) goto L16;
            char r1 = this.lastChar;
            if (r1 != '<') goto L13;
            return skipTagForward();
        L13:
            if (r1 != '&') goto L16;
            return skipEntityForward();
        L16:
            return r03;
        }

        public int getEntryDir() {
            this.charIndex = 0;
            int r1 = 0;
            int r2 = 0;
            int r3 = 0;
        L4:
            if (this.charIndex >= this.length) goto L23;
            if (r1 != 0) goto L23;
            byte r4 = dirTypeForward();
            if (r4 == 0) goto L20;
            if (r4 == 1) goto L18;
            if (r4 == 2) goto L18;
            if (r4 == 9) goto L4;
            switch(r4) {
                case 14: goto L17;
                case 15: goto L17;
                case 16: goto L16;
                case 17: goto L16;
                case 18: goto L15;
                default: goto L22;
            };
        L16:
            r3 = r3 + 1;
            r2 = 1;
            goto L4
        L17:
            r3 = r3 + 1;
            r2 = -1;
        L22:
            r1 = r3;
            goto L4
        L15:
            r3 = r3 - 1;
            r2 = 0;
        L18:
            if (r3 != 0) goto L22;
            return 1;
        L20:
            if (r3 != 0) goto L22;
            return -1;
        L23:
            if (r1 != 0) goto L25;
            return 0;
        L25:
            if (r2 == 0) goto L28;
            return r2;
        L28:
            if (this.charIndex <= 0) goto L38;
            switch(dirTypeBackward()) {
                case 14: goto L36;
                case 15: goto L36;
                case 16: goto L33;
                case 17: goto L33;
                case 18: goto L32;
                default: goto L28;
            };
        L32:
            r3 = r3 + 1;
            goto L28
        L33:
            if (r1 == r3) goto L34;
        L35:
            r3 = r3 - 1;
            goto L28
        L34:
            return 1;
        L36:
            if (r1 != r3) goto L35;
            return -1;
        L38:
            return 0;
        }

        public int getExitDir() {
            this.charIndex = this.length;
            int r1 = 0;
        L3:
            int r2 = r1;
        L5:
            if (this.charIndex <= 0) goto L31;
            byte r3 = dirTypeBackward();
            if (r3 != 0) goto L9;
            if (r1 == 0) goto L28;
            if (r2 != 0) goto L5;
        L28:
            return -1;
        L9:
            if (r3 == 1) goto L23;
            if (r3 == 2) goto L23;
            if (r3 == 9) goto L5;
            switch(r3) {
                case 14: goto L21;
                case 15: goto L21;
                case 16: goto L18;
                case 17: goto L18;
                case 18: goto L17;
                default: goto L15;
            };
        L17:
            r1 = r1 + 1;
            goto L5
        L18:
            if (r2 == r1) goto L19;
        L20:
            r1 = r1 - 1;
            goto L5
        L19:
            return 1;
        L21:
            if (r2 != r1) goto L20;
            return -1;
        L15:
            if (r2 != 0) goto L5;
        L23:
            if (r1 == 0) goto L24;
            if (r2 != 0) goto L5;
        L24:
            return 1;
        L31:
            return 0;
        }
    }

    static {
        TextDirectionHeuristicCompat r0 = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        DEFAULT_TEXT_DIRECTION_HEURISTIC = r0;
        LRM_STRING = Character.toString(8206);
        RLM_STRING = Character.toString(8207);
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, r0);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, r0);
    }

    public BidiFormatter(boolean r1, int r2, TextDirectionHeuristicCompat r3) {
        this.mIsRtlContext = r1;
        this.mFlags = r2;
        this.mDefaultTextDirectionHeuristicCompat = r3;
    }

    private static int getEntryDir(CharSequence r2) {
        return new DirectionalityEstimator(r2, false).getEntryDir();
    }

    private static int getExitDir(CharSequence r2) {
        return new DirectionalityEstimator(r2, false).getExitDir();
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static boolean isRtlLocale(Locale r1) {
        if (TextUtilsCompat.getLayoutDirectionFromLocale(r1) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    private String markAfter(CharSequence r3, TextDirectionHeuristicCompat r4) {
        boolean r42 = r4.isRtl(r3, 0, r3.length());
        if (this.mIsRtlContext == true) goto L10;
        if (r42 == true) goto L8;
        if (getExitDir(r3) != 1) goto L10;
    L8:
        return LRM_STRING;
    L10:
        if (this.mIsRtlContext == false) goto L16;
        if (r42 == false) goto L15;
        if (getExitDir(r3) == (-1)) goto L15;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        return RLM_STRING;
    L16:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private String markBefore(CharSequence r3, TextDirectionHeuristicCompat r4) {
        boolean r42 = r4.isRtl(r3, 0, r3.length());
        if (this.mIsRtlContext == true) goto L10;
        if (r42 == true) goto L8;
        if (getEntryDir(r3) != 1) goto L10;
    L8:
        return LRM_STRING;
    L10:
        if (this.mIsRtlContext == false) goto L16;
        if (r42 == false) goto L15;
        if (getEntryDir(r3) == (-1)) goto L15;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L15:
        return RLM_STRING;
    L16:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public boolean getStereoReset() {
        if ((this.mFlags & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public CharSequence unicodeWrap(CharSequence r3) {
        return unicodeWrap(r3, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence r3, TextDirectionHeuristicCompat r4, boolean r5) {
        if (r3 != null) goto L5;
        return null;
    L5:
        boolean r42 = r4.isRtl(r3, 0, r3.length());
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        if (getStereoReset() == false) goto L13;
        if (r5 == false) goto L13;
        if (r42 == false) goto L10;
        TextDirectionHeuristicCompat r1 = TextDirectionHeuristicsCompat.RTL;
    L11:
        r0.append(markBefore(r3, r1));
        goto L13
    L10:
        r1 = TextDirectionHeuristicsCompat.LTR;
    L13:
        if (r42 == this.mIsRtlContext) goto L18;
        if (r42 == false) goto L16;
        char r12 = 8235;
    L17:
        r0.append(r12);
        r0.append(r3);
        r0.append(8236);
    L19:
        if (r5 == false) goto L24;
        if (r42 == false) goto L22;
        TextDirectionHeuristicCompat r43 = TextDirectionHeuristicsCompat.RTL;
    L23:
        r0.append(markAfter(r3, r43));
        goto L24
    L22:
        r43 = TextDirectionHeuristicsCompat.LTR;
    L24:
        return r0;
    L16:
        r12 = 8234;
        goto L17
    L18:
        r0.append(r3);
        goto L19
    }

    public String unicodeWrap(String r3) {
        return unicodeWrap(r3, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String r1, TextDirectionHeuristicCompat r2, boolean r3) {
        if (r1 != null) goto L6;
        return null;
    L6:
        return unicodeWrap(r1, r2, r3).toString();
    }
}
