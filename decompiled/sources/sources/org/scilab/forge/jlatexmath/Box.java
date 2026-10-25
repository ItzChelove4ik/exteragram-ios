package org.scilab.forge.jlatexmath;

import java.util.LinkedList;
import ru.noties.jlatexmath.awt.BasicStroke;
import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.Stroke;
import ru.noties.jlatexmath.awt.geom.Rectangle2D;

/* loaded from: classes5.dex */
public abstract class Box {
    public static boolean DEBUG = false;
    private static final int MAX_BOX_BUDGET = 100000;
    private static int boxBudgetUsed;
    protected Color background;
    protected LinkedList<Box> children;
    protected float depth;
    protected Box elderParent;
    protected Color foreground;
    protected float height;
    protected Color markForDEBUG;
    protected Box parent;
    private Color prevColor;
    protected float shift;
    protected int type;
    protected float width;

    static {
    }

    public Box() {
        this(null, null);
    }

    public Box(Color r2, Color r3) {
        this.width = 0.0f;
        this.height = 0.0f;
        this.depth = 0.0f;
        this.shift = 0.0f;
        this.type = -1;
        this.children = new LinkedList();
        countBoxAllocation();
        this.foreground = r2;
        this.background = r3;
    }

    private static void countBoxAllocation() {
        int r0 = boxBudgetUsed + 1;
        boxBudgetUsed = r0;
        if (r0 > 100000) goto L5;
        return;
    L5:
        Box$$ExternalSyntheticBUOutline0.m("Formula is too large to lay out!");
    }

    public static void resetBoxBudget() {
        boxBudgetUsed = 0;
    }

    public void add(int r2, Box r3) {
        countBoxAllocation();
        this.children.add(r2, r3);
        r3.parent = this;
        r3.elderParent = this.elderParent;
    }

    public void add(Box r2) {
        countBoxAllocation();
        this.children.add(r2);
        r2.parent = this;
        r2.elderParent = this.elderParent;
    }

    public abstract void draw(Graphics2D r1, float r2, float r3);

    public void drawDebug(Graphics2D r2, float r3, float r4) {
        if (DEBUG == false) goto L6;
        drawDebug(r2, r3, r4, true);
        return;
    }

    public void drawDebug(Graphics2D r8, float r9, float r10, boolean r11) {
        if (DEBUG == false) goto L21;
        Stroke r0 = r8.getStroke();
        if (this.markForDEBUG == null) goto L7;
        Color r1 = r8.getColor();
        r8.setColor(this.markForDEBUG);
        float r3 = this.height;
        r8.fill(new Rectangle2D.Float(r9, r10 - r3, this.width, r3 + this.depth));
        r8.setColor(r1);
    L7:
        r8.setStroke(new BasicStroke((float) Math.abs(1.0d / r8.getTransform().getScaleX()), 0, 0));
        float r12 = this.width;
        if (r12 >= 0.0f) goto L10;
        r9 = r9 + r12;
        this.width = -r12;
    L10:
        float r32 = this.height;
        r8.draw(new Rectangle2D.Float(r9, r10 - r32, this.width, r32 + this.depth));
        if (r11 == false) goto L19;
        Color r112 = r8.getColor();
        r8.setColor(Color.RED);
        float r13 = this.depth;
        if (r13 <= 0.0f) goto L16;
        r8.fill(new Rectangle2D.Float(r9, r10, this.width, r13));
        r8.setColor(r112);
        r8.draw(new Rectangle2D.Float(r9, r10, this.width, this.depth));
        goto L19
    L16:
        if (r13 >= 0.0f) goto L18;
        r8.fill(new Rectangle2D.Float(r9, r10 + r13, this.width, -r13));
        r8.setColor(r112);
        float r14 = this.depth;
        r8.draw(new Rectangle2D.Float(r9, r10 + r14, this.width, -r14));
        goto L19
    L18:
        r8.setColor(r112);
    L19:
        r8.setStroke(r0);
        return;
    }

    public void endDraw(Graphics2D r1) {
        r1.setColor(this.prevColor);
    }

    public float getDepth() {
        return this.depth;
    }

    public Box getElderParent() {
        return this.elderParent;
    }

    public float getHeight() {
        return this.height;
    }

    public abstract int getLastFontId();

    public Box getParent() {
        return this.parent;
    }

    public float getShift() {
        return this.shift;
    }

    public float getWidth() {
        return this.width;
    }

    public void negWidth() {
        this.width = -this.width;
    }

    public void setDepth(float r1) {
        this.depth = r1;
    }

    public void setElderParent(Box r1) {
        this.elderParent = r1;
    }

    public void setHeight(float r1) {
        this.height = r1;
    }

    public void setParent(Box r1) {
        this.parent = r1;
    }

    public void setShift(float r1) {
        this.shift = r1;
    }

    public void setWidth(float r1) {
        this.width = r1;
    }

    public void startDraw(Graphics2D r6, float r7, float r8) {
        this.prevColor = r6.getColor();
        Color r0 = this.background;
        if (r0 == null) goto L5;
        r6.setColor(r0);
        float r1 = this.height;
        r6.fill(new Rectangle2D.Float(r7, r8 - r1, this.width, r1 + this.depth));
    L5:
        Color r02 = this.foreground;
        if (r02 != null) goto L8;
        r6.setColor(this.prevColor);
    L9:
        drawDebug(r6, r7, r8);
        return;
    L8:
        r6.setColor(r02);
        goto L9
    }
}
