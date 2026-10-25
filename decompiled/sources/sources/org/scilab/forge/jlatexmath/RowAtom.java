package org.scilab.forge.jlatexmath;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.ListIterator;
import org.scilab.forge.jlatexmath.dynamic.DynamicAtom;

/* loaded from: classes5.dex */
public class RowAtom extends Atom implements Row {
    private static BitSet binSet;
    private static BitSet ligKernSet;
    protected LinkedList<Atom> elements;
    public boolean lookAtLastAtom;
    private Dummy previousAtom;

    static {
        BitSet r0 = new BitSet(16);
        binSet = r0;
        r0.set(2);
        binSet.set(1);
        binSet.set(3);
        binSet.set(4);
        binSet.set(6);
        BitSet r02 = new BitSet(16);
        ligKernSet = r02;
        r02.set(0);
        ligKernSet.set(1);
        ligKernSet.set(2);
        ligKernSet.set(3);
        ligKernSet.set(4);
        ligKernSet.set(5);
        ligKernSet.set(6);
    }

    public RowAtom() {
        this.elements = new LinkedList();
        this.lookAtLastAtom = false;
        this.previousAtom = null;
    }

    public RowAtom(Atom r3) {
        LinkedList<Atom> r0 = new LinkedList();
        this.elements = r0;
        this.lookAtLastAtom = false;
        this.previousAtom = null;
        if (r3 != null) goto L5;
        return;
    L5:
        if ((r3 instanceof RowAtom) == false) goto L8;
        r0.addAll(((RowAtom) r3).elements);
        return;
    L8:
        r0.add(r3);
    }

    private void changeToOrd(Dummy r3, Dummy r4, Atom r5) {
        if (r3.getLeftType() != 2) goto L10;
        if (r4 != null) goto L6;
    L8:
        r3.setType(0);
        return;
    L6:
        if (binSet.get(r4.getRightType()) == true) goto L8;
        if (r5 == null) goto L8;
    L10:
        if (r5 != null) goto L12;
        return;
    L12:
        if (r3.getRightType() != 2) goto L22;
        int r2 = r5.getLeftType();
        if (r2 != 3) goto L16;
    L19:
        r3.setType(0);
        return;
    L16:
        if (r2 == 5) goto L19;
        if (r2 == 6) goto L19;
        return;
    }

    public final void add(Atom r1) {
        if (r1 == null) goto L5;
        this.elements.add(r1);
        return;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r14) {
        TeXFont r0 = r14.getTeXFont();
        HorizontalBox r1 = new HorizontalBox(r14.getColor(), r14.getBackground());
        r14.reset();
        ListIterator<Atom> r2 = this.elements.listIterator();
        int r4 = 0;
    L3:
        Atom r6 = null;
        if (r2.hasNext() == false) goto L66;
        Atom r5 = r2.next();
        r4 = r4 + 1;
        boolean r8 = false;
    L7:
        if ((r5 instanceof BreakMarkAtom) == false) goto L14;
        if (r8 == true) goto L11;
        r8 = true;
    L11:
        if (r2.hasNext() == false) goto L14;
        r5 = r2.next();
        r4 = r4 + 1;
    L14:
        if ((r5 instanceof DynamicAtom) == false) goto L20;
        DynamicAtom r7 = (DynamicAtom) r5;
        if (r7.getInsertMode() == false) goto L20;
        r5 = r7.getAtom();
        if ((r5 instanceof RowAtom) == false) goto L20;
        int r72 = r4 - 1;
        this.elements.remove(r72);
        this.elements.addAll(r72, ((RowAtom) r5).elements);
        r2 = this.elements.listIterator(r72);
        r5 = r2.next();
    L20:
        Dummy r73 = new Dummy(r5);
        if (r2.hasNext() == false) goto L23;
        r6 = r2.next();
        r2.previous();
    L23:
        changeToOrd(r73, this.previousAtom, r6);
    L25:
        if (r2.hasNext() == false) goto L39;
        if (r73.getRightType() != 0) goto L39;
        if (r73.isCharSymbol() == false) goto L39;
        Atom r62 = r2.next();
        int r10 = r4 + 1;
        if ((r62 instanceof CharSymbol) == false) goto L38;
        if (ligKernSet.get(r62.getLeftType()) == false) goto L38;
        r73.markAsTextSymbol();
        CharFont r11 = r73.getCharFont(r0);
        CharFont r63 = ((CharSymbol) r62).getCharFont(r0);
        CharFont r12 = r0.getLigature(r11, r63);
        if (r12 == null) goto L36;
        r73.changeAtom(new FixedCharAtom(r12));
        r4 = r10;
        goto L25
    L36:
        float r64 = r0.getKern(r11, r63, r14.getStyle());
        r2.previous();
    L41:
        if (r2.previousIndex() == 0) goto L49;
        Dummy r102 = this.previousAtom;
        if (r102 == null) goto L49;
        if (r102.isKern() == true) goto L49;
        if (r73.isKern() == true) goto L49;
        r1.add(Glue.get(this.previousAtom.getRightType(), r73.getLeftType(), r14));
    L49:
        r73.setPreviousAtom(this.previousAtom);
        Box r103 = r73.createBox(r14);
        if (r73.isCharInMathMode() == true) goto L52;
    L54:
        if (r8 == false) goto L56;
    L59:
        r1.addBreakPosition(r1.children.size());
    L60:
        r1.add(r103);
        r14.setLastFontId(r103.getLastFontId());
        if (Math.abs(r64) <= 1.0E-7f) goto L64;
        r1.add(new StrutBox(r64, 0.0f, 0.0f, 0.0f));
    L64:
        if (r73.isKern() == true) goto L3;
        this.previousAtom = r73;
        goto L3
    L56:
        if ((r5 instanceof CharAtom) == false) goto L60;
        if (Character.isDigit(((CharAtom) r5).getCharacter()) == false) goto L60;
    L52:
        if ((r103 instanceof CharBox) == false) goto L54;
        ((CharBox) r103).addItalicCorrectionToWidth();
    L38:
        r2.previous();
    L39:
        r64 = 0.0f;
        goto L41
    L66:
        this.previousAtom = null;
        return r1;
    }

    public Atom getLastAtom() {
        if (this.elements.size() == 0) goto L7;
        return this.elements.removeLast();
    L7:
        return new SpaceAtom(3, 0.0f, 0.0f, 0.0f);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        if (this.elements.size() != 0) goto L6;
        return 0;
    L6:
        return this.elements.get(0).getLeftType();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        if (this.elements.size() != 0) goto L7;
        return 0;
    L7:
        return this.elements.get(r1.size() - 1).getRightType();
    }

    @Override // org.scilab.forge.jlatexmath.Row
    public void setPreviousAtom(Dummy r1) {
        this.previousAtom = r1;
    }
}
