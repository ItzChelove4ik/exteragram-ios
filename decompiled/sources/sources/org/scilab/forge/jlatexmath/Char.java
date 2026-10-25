package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Font;

/* loaded from: classes5.dex */
public class Char {

    /* renamed from: c, reason: collision with root package name */
    private final char f501c;
    private final Font font;
    private final int fontCode;
    private final Metrics m;

    public Char(char r1, Font r2, int r3, Metrics r4) {
        this.font = r2;
        this.fontCode = r3;
        this.f501c = r1;
        this.m = r4;
    }

    public char getChar() {
        return this.f501c;
    }

    public CharFont getCharFont() {
        return new CharFont(this.f501c, this.fontCode);
    }

    public float getDepth() {
        return this.m.getDepth();
    }

    public Font getFont() {
        return this.font;
    }

    public int getFontCode() {
        return this.fontCode;
    }

    public float getHeight() {
        return this.m.getHeight();
    }

    public float getItalic() {
        return this.m.getItalic();
    }

    public Metrics getMetrics() {
        return this.m;
    }

    public float getWidth() {
        return this.m.getWidth();
    }
}
