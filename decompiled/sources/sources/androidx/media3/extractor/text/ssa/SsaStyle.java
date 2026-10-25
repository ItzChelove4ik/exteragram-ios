package androidx.media3.extractor.text.ssa;

import android.graphics.Color;
import android.graphics.PointF;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.google.common.base.Ascii;
import com.google.common.primitives.Ints;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class SsaStyle {
    public final int alignment;
    public final boolean bold;
    public final int borderStyle;
    public final float fontSize;
    public final boolean italic;
    public final String name;
    public final Integer outlineColor;
    public final Integer primaryColor;
    public final boolean strikeout;
    public final boolean underline;

    public static final class Format {
        public final int alignmentIndex;
        public final int boldIndex;
        public final int borderStyleIndex;
        public final int fontSizeIndex;
        public final int italicIndex;
        public final int length;
        public final int nameIndex;
        public final int outlineColorIndex;
        public final int primaryColorIndex;
        public final int strikeoutIndex;
        public final int underlineIndex;

        private Format(int r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11) {
            this.nameIndex = r1;
            this.alignmentIndex = r2;
            this.primaryColorIndex = r3;
            this.outlineColorIndex = r4;
            this.fontSizeIndex = r5;
            this.boldIndex = r6;
            this.italicIndex = r7;
            this.underlineIndex = r8;
            this.strikeoutIndex = r9;
            this.borderStyleIndex = r10;
            this.length = r11;
        }

        public static Format fromFormatLine(String r17) {
            String[] r1 = TextUtils.split(r17.substring(7), ",");
            int r6 = -1;
            int r7 = -1;
            int r8 = -1;
            int r9 = -1;
            int r10 = -1;
            int r11 = -1;
            int r12 = -1;
            int r13 = -1;
            int r14 = -1;
            int r15 = -1;
            int r4 = 0;
        L4:
            if (r4 >= r1.length) goto L61;
            String r5 = Ascii.toLowerCase(r1[r4].trim());
            r5.getClass();
            switch(r5.hashCode()) {
                case -1178781136: goto L45;
                case -1026963764: goto L41;
                case -192095652: goto L37;
                case -70925746: goto L33;
                case 3029637: goto L29;
                case 3373707: goto L25;
                case 366554320: goto L21;
                case 767321349: goto L17;
                case 1767875043: goto L13;
                case 1988365454: goto L9;
                default: goto L7;
            };
        L7:
            char r0 = 65535;
        L48:
            switch(r0) {
                case 0: goto L59;
                case 1: goto L58;
                case 2: goto L57;
                case 3: goto L56;
                case 4: goto L55;
                case 5: goto L54;
                case 6: goto L53;
                case 7: goto L52;
                case 8: goto L51;
                case 9: goto L50;
                default: goto L60;
            };
        L50:
            r9 = r4;
            goto L60
        L51:
            r7 = r4;
            goto L60
        L52:
            r15 = r4;
            goto L60
        L53:
            r10 = r4;
            goto L60
        L54:
            r6 = r4;
            goto L60
        L55:
            r11 = r4;
            goto L60
        L56:
            r8 = r4;
            goto L60
        L57:
            r14 = r4;
            goto L60
        L58:
            r13 = r4;
            goto L60
        L59:
            r12 = r4;
        L60:
            r4 = r4 + 1;
            goto L4
        L9:
            if (r5.equals("outlinecolour") == false) goto L7;
            r0 = '\t';
            goto L48
        L13:
            if (r5.equals("alignment") == false) goto L7;
            r0 = '\b';
            goto L48
        L17:
            if (r5.equals("borderstyle") == false) goto L7;
            r0 = 7;
            goto L48
        L21:
            if (r5.equals("fontsize") == false) goto L7;
            r0 = 6;
            goto L48
        L25:
            if (r5.equals("name") == false) goto L7;
            r0 = 5;
            goto L48
        L29:
            if (r5.equals("bold") == false) goto L7;
            r0 = 4;
            goto L48
        L33:
            if (r5.equals("primarycolour") == false) goto L7;
            r0 = 3;
            goto L48
        L37:
            if (r5.equals("strikeout") == false) goto L7;
            r0 = 2;
            goto L48
        L41:
            if (r5.equals("underline") == false) goto L7;
            r0 = 1;
            goto L48
        L45:
            if (r5.equals("italic") == false) goto L7;
            r0 = 0;
            goto L48
        L61:
            if (r6 != (-1)) goto L63;
            return null;
        L63:
            return new Format(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r1.length);
        }
    }

    public static final class Overrides {
        private static final Pattern ALIGNMENT_OVERRIDE_PATTERN = null;
        private static final Pattern BRACES_PATTERN = null;
        private static final Pattern MOVE_PATTERN = null;
        private static final Pattern POSITION_PATTERN = null;
        public final int alignment;
        public final PointF position;

        static {
            BRACES_PATTERN = Pattern.compile("\\{([^}]*)\\}");
            POSITION_PATTERN = Pattern.compile(Util.formatInvariant("\\\\pos\\((%1$s),(%1$s)\\)", new Object[]{"\\s*\\d+(?:\\.\\d+)?\\s*"}));
            MOVE_PATTERN = Pattern.compile(Util.formatInvariant("\\\\move\\(%1$s,%1$s,(%1$s),(%1$s)(?:,%1$s,%1$s)?\\)", new Object[]{"\\s*\\d+(?:\\.\\d+)?\\s*"}));
            ALIGNMENT_OVERRIDE_PATTERN = Pattern.compile("\\\\an(\\d+)");
        }

        private Overrides(int r1, PointF r2) {
            this.alignment = r1;
            this.position = r2;
        }

        private static int parseAlignmentOverride(String r1) {
            Matcher r12 = ALIGNMENT_OVERRIDE_PATTERN.matcher(r1);
            if (r12.find() == true) goto L5;
            return -1;
        L5:
            return SsaStyle.access$000((String) Assertions.checkNotNull(r12.group(1)));
        }

        public static Overrides parseFromDialogue(String r5) {
            Matcher r52 = BRACES_PATTERN.matcher(r5);
            PointF r1 = null;
            int r2 = -1;
        L4:
            if (r52.find() == false) goto L13;
            String r3 = (String) Assertions.checkNotNull(r52.group(1));
            PointF r4 = parsePosition(r3);     // Catch: RuntimeException -> L15
            if (r4 == null) goto L18;
            r1 = r4;
        L18:
            int r32 = parseAlignmentOverride(r3);     // Catch: RuntimeException -> L14
            if (r32 == (-1)) goto L4;
            r2 = r32;
            goto L4
        L13:
            return new Overrides(r2, r1);
        }

        private static PointF parsePosition(String r6) {
            Matcher r0 = POSITION_PATTERN.matcher(r6);
            Matcher r1 = MOVE_PATTERN.matcher(r6);
            boolean r2 = r0.find();
            boolean r3 = r1.find();
            if (r2 == false) goto L7;
            if (r3 == false) goto L6;
            Log.i("SsaStyle.Overrides", "Override has both \\pos(x,y) and \\move(x1,y1,x2,y2); using \\pos values. override='" + r6 + "'");
        L6:
            String r62 = r0.group(1);
            String r02 = r0.group(2);
        L10:
            return new PointF(Float.parseFloat(((String) Assertions.checkNotNull(r62)).trim()), Float.parseFloat(((String) Assertions.checkNotNull(r02)).trim()));
        L7:
            if (r3 == false) goto L11;
            r62 = r1.group(1);
            r02 = r1.group(2);
            goto L10
        L11:
            return null;
        }

        public static String stripStyleOverrides(String r1) {
            return BRACES_PATTERN.matcher(r1).replaceAll(_UrlKt.FRAGMENT_ENCODE_SET);
        }
    }

    private SsaStyle(String r1, int r2, Integer r3, Integer r4, float r5, boolean r6, boolean r7, boolean r8, boolean r9, int r10) {
        this.name = r1;
        this.alignment = r2;
        this.primaryColor = r3;
        this.outlineColor = r4;
        this.fontSize = r5;
        this.bold = r6;
        this.italic = r7;
        this.underline = r8;
        this.strikeout = r9;
        this.borderStyle = r10;
    }

    public static /* synthetic */ int access$000(String r0) {
        return parseAlignment(r0);
    }

    public static SsaStyle fromStyleLine(String r18, Format r19) {
        Assertions.checkArgument(r18.startsWith("Style:"));
        String[] r2 = TextUtils.split(r18.substring(6), ",");
        int r3 = r2.length;
        int r4 = r19.length;
        if (r3 == r4) goto L56;
        Log.w("SsaStyle", Util.formatInvariant("Skipping malformed 'Style:' line (expected %s values, found %s): '%s'", new Object[]{Integer.valueOf(r4), Integer.valueOf(r2.length), r18}));
        return null;
    L56:
        String r8 = r2[r19.nameIndex].trim();     // Catch: RuntimeException -> L9
        int r32 = r19.alignmentIndex;     // Catch: RuntimeException -> L9
        int r42 = -1;
        if (r32 == (-1)) goto L11;
        int r9 = parseAlignment(r2[r32].trim());     // Catch: RuntimeException -> L9
    L12:
        int r33 = r19.primaryColorIndex;     // Catch: RuntimeException -> L9
        if (r33 == (-1)) goto L15;
        Integer r10 = parseColor(r2[r33].trim());     // Catch: RuntimeException -> L9
    L16:
        int r34 = r19.outlineColorIndex;     // Catch: RuntimeException -> L9
        if (r34 == (-1)) goto L19;
        Integer r11 = parseColor(r2[r34].trim());     // Catch: RuntimeException -> L9
    L20:
        int r35 = r19.fontSizeIndex;     // Catch: RuntimeException -> L9
        if (r35 == (-1)) goto L24;
        float r36 = parseFontSize(r2[r35].trim());     // Catch: RuntimeException -> L9
    L23:
        float r12 = r36;
        int r37 = r19.boldIndex;     // Catch: RuntimeException -> L9
        boolean r13 = false;
        boolean r14 = true;
        if (r37 != (-1)) goto L28;
    L30:
        boolean r38 = false;
    L31:
        int r15 = r19.italicIndex;     // Catch: RuntimeException -> L9
        if (r15 != (-1)) goto L34;
    L36:
        boolean r152 = true;
        r14 = r38;
    L37:
        int r39 = r19.underlineIndex;     // Catch: RuntimeException -> L9
        if (r39 != (-1)) goto L40;
    L42:
        r152 = false;
    L43:
        int r310 = r19.strikeoutIndex;     // Catch: RuntimeException -> L9
        if (r310 != (-1)) goto L46;
    L48:
        boolean r16 = false;
    L49:
        int r0 = r19.borderStyleIndex;     // Catch: RuntimeException -> L9
        if (r0 == (-1)) goto L52;
        r42 = parseBorderStyle(r2[r0].trim());     // Catch: RuntimeException -> L9
    L52:
        return new SsaStyle(r8, r9, r10, r11, r12, r13, r14, r152, r16, r42);
    L46:
        if (parseBooleanValue(r2[r310].trim()) == false) goto L48;
        r16 = true;
        goto L49
    L40:
        if (parseBooleanValue(r2[r39].trim()) == false) goto L42;
    L34:
        if (parseBooleanValue(r2[r15].trim()) == false) goto L36;
        r152 = true;
        goto L37
    L28:
        if (parseBooleanValue(r2[r37].trim()) == false) goto L30;
        r38 = false;
        r13 = true;
        goto L31
    L24:
        r36 = -3.4028235E38f;
        goto L23
    L19:
        r11 = null;
        goto L20
    L15:
        r10 = null;
        goto L16
    L11:
        r9 = -1;
    L9:
        e = move-exception;
        Log.w("SsaStyle", "Skipping malformed 'Style:' line: '" + r18 + "'", e);
        return null;
    }

    private static boolean isValidAlignment(int r0) {
        switch(r0) {
            case 1: goto L5;
            case 2: goto L5;
            case 3: goto L5;
            case 4: goto L5;
            case 5: goto L5;
            case 6: goto L5;
            case 7: goto L5;
            case 8: goto L5;
            case 9: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        return true;
    }

    private static boolean isValidBorderStyle(int r2) {
        if (r2 != 1) goto L5;
    L8:
        return true;
    L5:
        if (r2 == 3) goto L8;
        return false;
    }

    private static int parseAlignment(String r2) {
        int r0 = Integer.parseInt(r2.trim());     // Catch: NumberFormatException -> L7
        if (isValidAlignment(r0) == false) goto L5;
        return r0;
    L5:
        Log.w("SsaStyle", "Ignoring unknown alignment: " + r2);
        return -1;
    }

    private static boolean parseBooleanValue(String r4) {
        int r42 = Integer.parseInt(r4);     // Catch: NumberFormatException -> L11
        if (r42 != 1) goto L7;
    L10:
        return true;
    L7:
        if (r42 == (-1)) goto L10;
        return false;
    L11:
        e = move-exception;
        Log.w("SsaStyle", "Failed to parse boolean value: '" + r4 + "'", e);
        return false;
    }

    private static int parseBorderStyle(String r2) {
        int r0 = Integer.parseInt(r2.trim());     // Catch: NumberFormatException -> L7
        if (isValidBorderStyle(r0) == false) goto L5;
        return r0;
    L5:
        Log.w("SsaStyle", "Ignoring unknown BorderStyle: " + r2);
        return -1;
    }

    public static Integer parseColor(String r8) {
    L5:
        e = move-exception;
        Log.w("SsaStyle", "Failed to parse color expression: '" + r8 + "'", e);
        return null;
    L3:
        if (r8.startsWith("&H") == false) goto L7;
        long r2 = Long.parseLong(r8.substring(2), 16);     // Catch: IllegalArgumentException -> L5
    L9:
        if (r2 > 4294967295L) goto L11;
        boolean r0 = true;
    L12:
        Assertions.checkArgument(r0);     // Catch: IllegalArgumentException -> L5
        int r82 = Ints.checkedCast(((r2 >> 24) & 255) ^ 255);
        int r02 = Ints.checkedCast((r2 >> 16) & 255);
        return Integer.valueOf(Color.argb(r82, Ints.checkedCast(r2 & 255), Ints.checkedCast((r2 >> 8) & 255), r02));
    L11:
        r0 = false;
        goto L12
    L7:
        r2 = Long.parseLong(r8);     // Catch: IllegalArgumentException -> L5
        goto L9
    }

    private static float parseFontSize(String r3) {
        return Float.parseFloat(r3);
    L4:
        e = move-exception;
        Log.w("SsaStyle", "Failed to parse font size: '" + r3 + "'", e);
        return -3.4028235E38f;
    }
}
