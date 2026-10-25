package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Font;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.geom.AffineTransform;

/* loaded from: classes5.dex */
public class CharBox extends Box {
    private final char[] arr;
    private final CharFont cf;
    private float italic;
    private final float size;

    public CharBox(Char r2) {
        this.arr = new char[1];
        this.cf = r2.getCharFont();
        this.size = r2.getMetrics().getSize();
        this.width = r2.getWidth();
        this.height = r2.getHeight();
        this.depth = r2.getDepth();
        this.italic = r2.getItalic();
    }

    public void addItalicCorrectionToWidth() {
        this.width += this.italic;
        this.italic = 0.0f;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r8, float r9, float r10) {
        drawDebug(r8, r9, r10);
        AffineTransform r0 = r8.getTransform();
        r8.translate(r9, r10);
        Font r92 = FontInfo.getFont(this.cf.fontId);
        if (Math.abs(this.size - TeXFormula.FONT_SCALE_FACTOR) <= 1.0E-7f) goto L6;
        float r102 = this.size;
        float r1 = TeXFormula.FONT_SCALE_FACTOR;
        r8.scale(r102 / r1, r102 / r1);
    L6:
        if (r8.getFont() == r92) goto L8;
        r8.setFont(r92);
    L8:
        char[] r2 = this.arr;
        r2[0] = this.cf.f503c;
        r8.drawChars(r2, 0, 1, 0, 0);
        r8.setTransform(r0);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.cf.fontId;
    }

    public String toString() {
        return super.toString() + "=" + this.cf.f503c;
    }
}
