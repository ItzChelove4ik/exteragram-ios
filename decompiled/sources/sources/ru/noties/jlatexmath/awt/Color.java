package ru.noties.jlatexmath.awt;

import org.mvel2.asm.Opcodes;

/* loaded from: classes7.dex */
public class Color {
    public static final Color BLACK = null;
    public static final Color RED = null;
    public static final Color black = null;
    public static final Color blue = null;
    public static final Color cyan = null;
    public static final Color green = null;
    public static final Color magenta = null;
    public static final Color red = null;
    public static final Color white = null;
    public static final Color yellow = null;
    private final int color;

    static {
        Color r0 = new Color(-16777216);
        black = r0;
        white = new Color(-1);
        Color r1 = new Color(Opcodes.V_PREVIEW);
        red = r1;
        green = new Color(-16711936);
        blue = new Color(-16776961);
        cyan = new Color(android.graphics.Color.parseColor("cyan"));
        magenta = new Color(android.graphics.Color.parseColor("magenta"));
        yellow = new Color(android.graphics.Color.parseColor("yellow"));
        BLACK = r0;
        RED = r1;
    }

    public Color(float r3, float r4, float r5) {
        this((int) ((r3 * 255.0f) + 0.5f), (int) ((r4 * 255.0f) + 0.5f), (int) ((r5 * 255.0f) + 0.5f));
    }

    public Color(int r1) {
        this.color = r1;
    }

    public Color(int r1, int r2, int r3) {
        this(android.graphics.Color.rgb(r1, r2, r3));
    }

    public static Color decode(String r1) {
        return new Color(android.graphics.Color.parseColor(r1));
    }

    public int getAlpha() {
        return 255;
    }

    public int getBlue() {
        return android.graphics.Color.blue(this.color);
    }

    public int getColorInt() {
        return this.color;
    }

    public int getGreen() {
        return android.graphics.Color.green(this.color);
    }

    public int getRed() {
        return android.graphics.Color.red(this.color);
    }
}
