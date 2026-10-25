package org.scilab.forge.jlatexmath;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import ru.noties.jlatexmath.awt.Graphics2D;

/* loaded from: classes5.dex */
class VerticalBox extends Box {
    private float leftMostPos;
    private float rightMostPos;

    public VerticalBox() {
        this.leftMostPos = Float.MAX_VALUE;
        this.rightMostPos = -3.4028235E38f;
    }

    public VerticalBox(Box r3, float r4, int r5) {
        this();
        add(r3);
        if (r5 != 2) goto L7;
        float r42 = r4 / 2.0f;
        StrutBox r32 = new StrutBox(0.0f, r42, 0.0f, 0.0f);
        super.add(0, r32);
        this.height += r42;
        this.depth += r42;
        super.add(r32);
        return;
    L7:
        if (r5 != 3) goto L11;
        this.depth += r4;
        super.add(new StrutBox(0.0f, r4, 0.0f, 0.0f));
        return;
    L11:
        if (r5 != 4) goto L14;
        this.height += r4;
        super.add(0, new StrutBox(0.0f, r4, 0.0f, 0.0f));
        return;
    }

    private void recalculateWidth(Box r5) {
        this.leftMostPos = Math.min(this.leftMostPos, r5.shift);
        float r0 = this.rightMostPos;
        float r1 = r5.shift;
        float r52 = r5.width;
        if (r52 > 0.0f) goto L6;
        r52 = 0.0f;
    L6:
        float r53 = Math.max(r0, r1 + r52);
        this.rightMostPos = r53;
        this.width = r53 - this.leftMostPos;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void add(int r3, Box r4) {
        super.add(r3, r4);
        float r0 = this.depth;
        if (r3 != 0) goto L5;
        this.depth = r0 + (r4.depth + this.height);
        this.height = r4.height;
    L6:
        recalculateWidth(r4);
        return;
    L5:
        this.depth = r0 + (r4.height + r4.depth);
        goto L6
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public final void add(Box r4) {
        super.add(r4);
        if (this.children.size() != 1) goto L5;
        this.height = r4.height;
        this.depth = r4.depth;
    L6:
        recalculateWidth(r4);
        return;
    L5:
        this.depth += r4.height + r4.depth;
        goto L6
    }

    public final void add(Box r3, float r4) {
        if (this.children.size() < 1) goto L5;
        add(new StrutBox(0.0f, r4, 0.0f, 0.0f));
    L5:
        add(r3);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r5, float r6, float r7) {
        float r72 = r7 - this.height;
        Iterator<Box> r0 = this.children.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        Box r1 = r0.next();
        float r73 = r72 + r1.getHeight();
        r1.draw(r5, (r1.getShift() + r6) - this.leftMostPos, r73);
        r72 = r73 + r1.getDepth();
        goto L4
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        LinkedList<Box> r3 = this.children;
        ListIterator<Box> r32 = r3.listIterator(r3.size());
        int r1 = -1;
    L3:
        if (r1 != (-1)) goto L7;
        if (r32.hasPrevious() == false) goto L7;
        r1 = r32.previous().getLastFontId();
    L7:
        return r1;
    }

    public int getSize() {
        return this.children.size();
    }
}
