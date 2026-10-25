package org.scilab.forge.jlatexmath;

import java.util.LinkedList;
import java.util.ListIterator;

/* loaded from: classes5.dex */
public class VRowAtom extends Atom {
    protected boolean addInterline;
    protected LinkedList<Atom> elements;
    protected int halign;
    private SpaceAtom raise;
    protected boolean vtop;

    public VRowAtom() {
        this.elements = new LinkedList();
        this.raise = new SpaceAtom(1, 0.0f, 0.0f, 0.0f);
        this.addInterline = false;
        this.vtop = false;
        this.halign = 5;
    }

    public VRowAtom(Atom r4) {
        this.elements = new LinkedList();
        this.raise = new SpaceAtom(1, 0.0f, 0.0f, 0.0f);
        this.addInterline = false;
        this.vtop = false;
        this.halign = 5;
        if (r4 == null) goto L10;
        boolean r0 = r4 instanceof VRowAtom;
        LinkedList<Atom> r3 = this.elements;
        if (r0 == false) goto L8;
        r3.addAll(((VRowAtom) r4).elements);
        return;
    L8:
        r3.add(r4);
        return;
    }

    public final void add(Atom r2) {
        if (r2 == null) goto L5;
        this.elements.add(0, r2);
        return;
    }

    public final void append(Atom r1) {
        if (r1 == null) goto L5;
        this.elements.add(r1);
        return;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r9) {
        VerticalBox r0 = new VerticalBox();
        float r3 = 0.0f;
        if (this.halign == 5) goto L18;
        LinkedList r1 = new LinkedList();
        ListIterator<Atom> r2 = this.elements.listIterator();
        float r4 = Float.NEGATIVE_INFINITY;
    L6:
        if (r2.hasNext() == false) goto L10;
        Box r5 = r2.next().createBox(r9);
        r1.add(r5);
        if (r4 >= r5.getWidth()) goto L6;
        r4 = r5.getWidth();
        goto L6
    L10:
        StrutBox r22 = new StrutBox(0.0f, r9.getInterline(), 0.0f, 0.0f);
        ListIterator r12 = r1.listIterator();
    L12:
        if (r12.hasNext() == false) goto L26;
        r0.add(new HorizontalBox((Box) r12.next(), r4, this.halign));
        if (this.addInterline == false) goto L12;
        if (r12.hasNext() == false) goto L12;
        r0.add(r22);
    L26:
        r0.setShift(-this.raise.createBox(r9).getWidth());
        if (this.vtop == false) goto L35;
        if (r0.getSize() == 0) goto L32;
        r3 = r0.children.getFirst().getHeight();
    L32:
        r0.setHeight(r3);
        r0.setDepth((r0.getDepth() + r0.getHeight()) - r3);
        return r0;
    L35:
        if (r0.getSize() == 0) goto L38;
        r3 = r0.children.getLast().getDepth();
    L38:
        r0.setHeight((r0.getDepth() + r0.getHeight()) - r3);
        r0.setDepth(r3);
        return r0;
    L18:
        StrutBox r13 = new StrutBox(0.0f, r9.getInterline(), 0.0f, 0.0f);
        ListIterator<Atom> r23 = this.elements.listIterator();
    L20:
        if (r23.hasNext() == false) goto L26;
        r0.add(r23.next().createBox(r9));
        if (this.addInterline == false) goto L20;
        if (r23.hasNext() == false) goto L20;
        r0.add(r13);
        goto L20
    }

    public boolean getAddInterline() {
        return this.addInterline;
    }

    public int getHalign() {
        return this.halign;
    }

    public Atom getLastAtom() {
        return this.elements.removeLast();
    }

    public boolean getVtop() {
        return this.vtop;
    }

    public void setAddInterline(boolean r1) {
        this.addInterline = r1;
    }

    public void setHalign(int r1) {
        this.halign = r1;
    }

    public void setRaise(int r3, float r4) {
        this.raise = new SpaceAtom(r3, r4, 0.0f, 0.0f);
    }

    public void setVtop(boolean r1) {
        this.vtop = r1;
    }
}
