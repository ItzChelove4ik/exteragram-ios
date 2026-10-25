package androidx.media3.extractor.text.ttml;

import android.text.TextUtils;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
final class TextEmphasis {
    private static final ImmutableSet<String> MARK_FILL_VALUES = null;
    private static final ImmutableSet<String> MARK_SHAPE_VALUES = null;
    private static final ImmutableSet<String> POSITION_VALUES = null;
    private static final ImmutableSet<String> SINGLE_STYLE_VALUES = null;
    private static final Pattern WHITESPACE_PATTERN = null;
    public final int markFill;
    public final int markShape;
    public final int position;

    static {
        WHITESPACE_PATTERN = Pattern.compile("\\s+");
        SINGLE_STYLE_VALUES = ImmutableSet.of("auto", "none");
        MARK_SHAPE_VALUES = ImmutableSet.of("dot", "sesame", "circle");
        MARK_FILL_VALUES = ImmutableSet.of("filled", "open");
        POSITION_VALUES = ImmutableSet.of("after", "before", "outside");
    }

    private TextEmphasis(int r1, int r2, int r3) {
        this.markShape = r1;
        this.markFill = r2;
        this.position = r3;
    }

    public static TextEmphasis parse(String r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        String r22 = Ascii.toLowerCase(r2.trim());
        if (r22.isEmpty() == false) goto L9;
        return null;
    L9:
        return parseWords(ImmutableSet.copyOf(TextUtils.split(r22, WHITESPACE_PATTERN)));
    }

    private static TextEmphasis parseWords(ImmutableSet<String> r7) {
        String r0 = (String) Iterables.getFirst(Sets.intersection(POSITION_VALUES, r7), "outside");
        int r2 = r0.hashCode();
        int r4 = 1;
        if (r2 != (-1392885889)) goto L5;
        r0.equals("before");
    L16:
        int r02 = 1;
    L17:
        Sets.SetView r1 = Sets.intersection(SINGLE_STYLE_VALUES, r7);
        int r3 = -1;
        if (r1.isEmpty() == true) goto L30;
        String r72 = (String) r1.iterator().next();
        int r12 = r72.hashCode();
        if (r12 != 3005871) goto L22;
        r72.equals("auto");
    L29:
        return new TextEmphasis(r3, 0, r02);
    L22:
        if (r12 != 3387192) goto L29;
        if (r72.equals("none") == false) goto L29;
        r3 = 0;
        goto L29
    L30:
        Sets.SetView r13 = Sets.intersection(MARK_FILL_VALUES, r7);
        Sets.SetView r73 = Sets.intersection(MARK_SHAPE_VALUES, r7);
        if (r13.isEmpty() == true) goto L33;
    L36:
        String r14 = (String) Iterables.getFirst(r13, "filled");
        int r32 = r14.hashCode();
        if (r32 != (-1274499742)) goto L39;
        r14.equals("filled");
    L45:
        int r15 = 1;
    L46:
        String r74 = (String) Iterables.getFirst(r73, "circle");
        int r33 = r74.hashCode();
        if (r33 != (-1360216880)) goto L49;
        r74.equals("circle");
    L61:
        return new TextEmphasis(r4, r15, r02);
    L49:
        if (r33 == (-905816648)) goto L57;
        if (r33 != 99657) goto L61;
        if (r74.equals("dot") == false) goto L61;
        r4 = 2;
        goto L61
    L57:
        if (r74.equals("sesame") == false) goto L61;
        r4 = 3;
        goto L61
    L39:
        if (r32 != 3417674) goto L45;
        if (r14.equals("open") == false) goto L45;
        r15 = 2;
        goto L46
    L33:
        if (r73.isEmpty() == false) goto L36;
        return new TextEmphasis(-1, 0, r02);
    L5:
        if (r2 == (-1106037339)) goto L13;
        if (r2 != 92734940) goto L16;
        if (r0.equals("after") == false) goto L16;
        r02 = 2;
        goto L17
    L13:
        if (r0.equals("outside") == false) goto L16;
        r02 = -2;
        goto L17
    }
}
