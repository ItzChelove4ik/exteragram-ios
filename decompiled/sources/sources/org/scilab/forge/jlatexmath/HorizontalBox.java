package org.scilab.forge.jlatexmath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Graphics2D;

/* loaded from: classes5.dex */
public class HorizontalBox extends Box {
    protected List<Integer> breakPositions;
    private float curPos;

    public HorizontalBox() {
        this.curPos = 0.0f;
    }

    public HorizontalBox(Box r2) {
        this.curPos = 0.0f;
        add(r2);
    }

    public HorizontalBox(Box r3, float r4, int r5) {
        this.curPos = 0.0f;
        if (r4 == Float.POSITIVE_INFINITY) goto L24;
        float r42 = r4 - r3.getWidth();
        if (r42 > 0.0f) goto L7;
        add(r3);
        return;
    L7:
        if (r5 != 2) goto L9;
    L20:
        StrutBox r52 = new StrutBox(r42 / 2.0f, 0.0f, 0.0f, 0.0f);
        add(r52);
        add(r3);
        add(r52);
        return;
    L9:
        if (r5 == 5) goto L20;
        if (r5 != 0) goto L15;
        add(r3);
        add(new StrutBox(r42, 0.0f, 0.0f, 0.0f));
        return;
    L15:
        if (r5 != 1) goto L18;
        add(new StrutBox(r42, 0.0f, 0.0f, 0.0f));
        add(r3);
        return;
    L18:
        add(r3);
        return;
    L24:
        add(r3);
    }

    public HorizontalBox(Color r1, Color r2) {
        super(r1, r2);
        this.curPos = 0.0f;
    }

    private void recalculate(Box r5) {
        this.width += r5.getWidth();
        float r1 = Float.NEGATIVE_INFINITY;
        if (this.children.size() != 0) goto L5;
        float r0 = Float.NEGATIVE_INFINITY;
    L6:
        this.height = Math.max(r0, r5.height - r5.shift);
        if (this.children.size() == 0) goto L10;
        r1 = this.depth;
    L10:
        this.depth = Math.max(r1, r5.depth + r5.shift);
        return;
    L5:
        r0 = this.height;
        goto L6
    }

    private HorizontalBox[] split(int r6, int r7) {
        HorizontalBox r0 = cloneBox();
        HorizontalBox r1 = cloneBox();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 > r6) goto L5;
        r0.add(this.children.get(r3));
        r3 = r3 + 1;
        goto L3
    L5:
        int r72 = r7 + r6;
    L7:
        if (r72 >= this.children.size()) goto L10;
        r1.add(this.children.get(r72));
        r72 = r72 + 1;
        goto L7
    L10:
        if (this.breakPositions == null) goto L18;
    L12:
        if (r2 >= this.breakPositions.size()) goto L18;
        if (this.breakPositions.get(r2).intValue() <= (r6 + 1)) goto L16;
        r1.addBreakPosition((this.breakPositions.get(r2).intValue() - r6) - 1);
    L16:
        r2 = r2 + 1;
    L18:
        return new HorizontalBox[]{r0, r1};
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public final void add(int r1, Box r2) {
        recalculate(r2);
        super.add(r1, r2);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public final void add(Box r1) {
        recalculate(r1);
        super.add(r1);
    }

    public void addBreakPosition(int r2) {
        if (this.breakPositions != null) goto L5;
        this.breakPositions = new ArrayList();
    L5:
        this.breakPositions.add(Integer.valueOf(r2));
    }

    public HorizontalBox cloneBox() {
        HorizontalBox r0 = new HorizontalBox(this.foreground, this.background);
        r0.shift = this.shift;
        return r0;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r4, float r5, float r6) {
        startDraw(r4, r5, r6);
        Iterator<Box> r0 = this.children.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        Box r1 = r0.next();
        r1.draw(r4, r5, r1.shift + r6);
        r5 = r5 + r1.getWidth();
        goto L4
    L6:
        endDraw(r4);
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

    public HorizontalBox[] split(int r2) {
        return split(r2, 1);
    }

    public HorizontalBox[] splitRemove(int r2) {
        return split(r2, 2);
    }
}
