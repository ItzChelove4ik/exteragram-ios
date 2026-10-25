package org.scilab.forge.jlatexmath;

import org.scilab.forge.jlatexmath.TeXFormula;
import ru.noties.jlatexmath.awt.Font;

/* loaded from: classes5.dex */
public class JavaFontRenderingAtom extends Atom {
    private TeXFormula.FontInfos fontInfos;
    private String str;
    private int type;

    public JavaFontRenderingAtom(String r1, int r2) {
        this.str = r1;
        this.type = r2;
    }

    public JavaFontRenderingAtom(String r2, TeXFormula.FontInfos r3) {
        this(r2, 0);
        this.fontInfos = r3;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r11) {
        if (this.fontInfos == null) goto L5;
        DefaultTeXFont r0 = (DefaultTeXFont) r11.getTeXFont();
        if (r0.isIt == false) goto L9;
        int r1 = 2;
    L10:
        int r6 = r1 | (r0.isBold ? 1 : 0);
        boolean r9 = r0.isRoman;
        boolean r02 = r0.isSs;
        TeXFormula.FontInfos r12 = this.fontInfos;
        if (r02 == false) goto L18;
        String r03 = r12.sansserif;
        if (r03 != null) goto L16;
        Font r04 = new Font(r12.serif, 0, 10);
    L15:
        Font r8 = r04;
    L23:
        return new JavaFontRenderingBox(this.str, r6, DefaultTeXFont.getSizeFactor(r11.getStyle()), r8, r9);
    L16:
        Font r13 = new Font(r03, 0, 10);
    L17:
        r8 = r13;
        goto L23
    L18:
        String r05 = r12.serif;
        if (r05 != null) goto L21;
        r04 = new Font(r12.sansserif, 0, 10);
        goto L15
    L21:
        r13 = new Font(r05, 0, 10);
        goto L17
    L9:
        r1 = 0;
        goto L10
    L5:
        return new JavaFontRenderingBox(this.str, this.type, DefaultTeXFont.getSizeFactor(r11.getStyle()));
    }
}
