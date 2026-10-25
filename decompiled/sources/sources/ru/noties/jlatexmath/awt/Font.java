package ru.noties.jlatexmath.awt;

import android.graphics.Typeface;
import java.io.InputStream;
import java.util.Locale;

/* loaded from: classes7.dex */
public class Font {
    public static final int BOLD = 1;
    public static final int ITALIC = 2;
    public static final int PLAIN = 0;
    private float size;
    private int style;
    private final Typeface typeface;

    private Font(Typeface r1, int r2, float r3) {
        this.typeface = applyStyle(r1, r2);
        this.style = r2;
        this.size = r3;
    }

    public Font(String r1, int r2, int r3) {
        this(createTypeface(r1, r2), r2, r3);
    }

    private static Typeface applyStyle(Typeface r4, int r5) {
        boolean r0 = r4.isBold();
        int r2 = 0;
        if (r4.isItalic() == false) goto L5;
        boolean r1 = 2;
    L7:
        if ((r0 | r1) != r5) goto L9;
        return r4;
    L9:
        if ((r5 & 1) == 0) goto L11;
        int r02 = 1;
    L13:
        if ((r5 & 2) == 0) goto L16;
        r2 = 2;
    L16:
        return Typeface.create(r4, r02 | r2);
    L11:
        r02 = 0;
        goto L13
    L5:
        r1 = false;
        goto L7
    }

    @Deprecated
    public static Font createFont(int r0, InputStream r1) {
        return null;
    }

    public static Font createFont(Typeface r2, float r3) {
        return new Font(r2, 0, r3);
    }

    private static Typeface createTypeface(String r1, int r2) {
        Typeface r12 = Typeface.create(r1.toLowerCase(Locale.US), toAndroidStyle(r2));
        if (r12 == null) goto L5;
        return r12;
    L5:
        return Typeface.DEFAULT;
    }

    private static int toAndroidStyle(int r3) {
        int r0 = 0;
        if (r3 != 0) goto L6;
        return 0;
    L6:
        if ((r3 & 1) == 0) goto L8;
        int r1 = 1;
    L10:
        if ((r3 & 2) == 0) goto L13;
        r0 = 2;
    L13:
        return r1 | r0;
    L8:
        r1 = 0;
        goto L10
    }

    public Font deriveFont(int r3) {
        return new Font(this.typeface, r3, this.size);
    }

    public boolean isBold() {
        if ((this.style & 1) == 0) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isItalic() {
        if ((this.style & 2) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public float size() {
        return this.size;
    }

    public int style() {
        return this.style;
    }

    public Typeface typeface() {
        return this.typeface;
    }
}
