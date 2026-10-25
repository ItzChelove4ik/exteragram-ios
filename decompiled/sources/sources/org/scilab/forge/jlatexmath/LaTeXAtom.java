package org.scilab.forge.jlatexmath;

import java.lang.Character;
import java.util.Map;
import org.scilab.forge.jlatexmath.TeXFormula;

/* loaded from: classes5.dex */
public class LaTeXAtom extends Atom {
    public LaTeXAtom() {
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r12) {
        TeXEnvironment r11 = r12.copy(r12.getTeXFont().copy());
        r11.getTeXFont().setRoman(true);
        float r122 = r11.getTeXFont().getScaleFactor();
        Map<Character.UnicodeBlock, TeXFormula.FontInfos> r1 = TeXFormula.externalFontMap;
        Character.UnicodeBlock r2 = Character.UnicodeBlock.BASIC_LATIN;
        TeXFormula.FontInfos r13 = r1.get(r2);
        if (r13 == null) goto L5;
        TeXFormula.externalFontMap.put(r2, null);
    L5:
        RowAtom r3 = (RowAtom) ((RomanAtom) new TeXFormula("\\mathrm{XETL}").root).base;
        if (r13 == null) goto L8;
        TeXFormula.externalFontMap.put(r2, r13);
    L8:
        HorizontalBox r14 = new HorizontalBox(r3.getLastAtom().createBox(r11));
        r14.add(new SpaceAtom(0, (-0.35f) * r122, 0.0f, 0.0f).createBox(r11));
        float r22 = new SpaceAtom(1, 0.45f * r122, 0.0f, 0.0f).createBox(r11).getWidth();
        float r0 = new SpaceAtom(1, 0.5f * r122, 0.0f, 0.0f).createBox(r11).getWidth();
        CharBox r4 = new CharBox(r11.getTeXFont().getChar('A', "mathnormal", r11.supStyle().getStyle()));
        r4.setShift(-r22);
        r14.add(r4);
        float r123 = r122 * (-0.15f);
        r14.add(new SpaceAtom(0, r123, 0.0f, 0.0f).createBox(r11));
        r14.add(r3.getLastAtom().createBox(r11));
        r14.add(new SpaceAtom(0, r123, 0.0f, 0.0f).createBox(r11));
        Box r23 = r3.getLastAtom().createBox(r11);
        r23.setShift(r0);
        r14.add(r23);
        r14.add(new SpaceAtom(0, r123, 0.0f, 0.0f).createBox(r11));
        r14.add(r3.getLastAtom().createBox(r11));
        return r14;
    }
}
