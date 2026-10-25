package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Color;

/* loaded from: classes5.dex */
public class TeXEnvironment {
    private static final int MAX_DEPTH = 64;
    private Color background;
    private Color color;
    private int depth;
    private float interline;
    private int interlineUnit;
    public boolean isColored;
    private int lastFontId;
    private float scaleFactor;
    private boolean smallCap;
    private int style;
    private String textStyle;
    private float textwidth;
    private TeXFont tf;

    private TeXEnvironment(int r10, float r11, TeXFont r12, Color r13, Color r14, String r15, boolean r16) {
        this(r10, r11, r12, r13, r14, r15, r16, 0);
    }

    private TeXEnvironment(int r2, float r3, TeXFont r4, Color r5, Color r6, String r7, boolean r8, int r9) {
        this.lastFontId = -1;
        this.textwidth = Float.POSITIVE_INFINITY;
        this.isColored = false;
        this.style = r2;
        this.scaleFactor = r3;
        this.tf = r4;
        this.textStyle = r7;
        this.smallCap = r8;
        this.depth = r9;
        this.background = r5;
        this.color = r6;
        setInterline(1, 1.0f);
    }

    public TeXEnvironment(int r2, TeXFont r3) {
        this(r2, r3, null, null);
    }

    public TeXEnvironment(int r2, TeXFont r3, int r4, float r5) {
        this(r2, r3, null, null);
        this.textwidth = r5 * SpaceAtom.getFactor(r4, this);
    }

    private TeXEnvironment(int r3, TeXFont r4, Color r5, Color r6) {
        this.lastFontId = -1;
        this.textwidth = Float.POSITIVE_INFINITY;
        this.scaleFactor = 1.0f;
        this.isColored = false;
        this.style = r3;
        this.tf = r4;
        this.background = r5;
        this.color = r6;
        setInterline(1, 1.0f);
    }

    public TeXEnvironment copy() {
        int r0 = this.depth;
        if (r0 > 64) goto L7;
        return new TeXEnvironment(this.style, this.scaleFactor, this.tf, this.background, this.color, this.textStyle, this.smallCap, r0 + 1);
    L7:
        throw new DepthLimitExceededException();
    }

    public TeXEnvironment copy(TeXFont r12) {
        int r0 = this.depth;
        if (r0 > 64) goto L7;
        TeXEnvironment r2 = new TeXEnvironment(this.style, this.scaleFactor, r12, this.background, this.color, this.textStyle, this.smallCap, r0 + 1);
        r2.textwidth = this.textwidth;
        r2.interline = this.interline;
        r2.interlineUnit = this.interlineUnit;
        return r2;
    L7:
        throw new DepthLimitExceededException();
    }

    public TeXEnvironment crampStyle() {
        TeXEnvironment r0 = copy();
        int r3 = this.style;
        if ((r3 % 2) == 1) goto L6;
        r3 = r3 + 1;
    L6:
        r0.style = r3;
        return r0;
    }

    public TeXEnvironment denomStyle() {
        TeXEnvironment r0 = copy();
        int r2 = this.style;
        r0.style = (((r2 / 2) * 2) + 3) - ((r2 / 6) * 2);
        return r0;
    }

    public Color getBackground() {
        return this.background;
    }

    public Color getColor() {
        return this.color;
    }

    public float getInterline() {
        return this.interline * SpaceAtom.getFactor(this.interlineUnit, this);
    }

    public int getLastFontId() {
        int r0 = this.lastFontId;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        return this.tf.getMuFontId();
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public float getSize() {
        return this.tf.getSize();
    }

    public boolean getSmallCap() {
        return this.smallCap;
    }

    public float getSpace() {
        return this.tf.getSpace(this.style) * this.tf.getScaleFactor();
    }

    public int getStyle() {
        return this.style;
    }

    public TeXFont getTeXFont() {
        return this.tf;
    }

    public String getTextStyle() {
        return this.textStyle;
    }

    public float getTextwidth() {
        return this.textwidth;
    }

    public TeXEnvironment numStyle() {
        TeXEnvironment r0 = copy();
        int r2 = this.style;
        r0.style = (r2 + 2) - ((r2 / 6) * 2);
        return r0;
    }

    public void reset() {
        this.color = null;
        this.background = null;
    }

    public TeXEnvironment rootStyle() {
        TeXEnvironment r1 = copy();
        r1.style = 6;
        return r1;
    }

    public void setBackground(Color r1) {
        this.background = r1;
    }

    public void setColor(Color r1) {
        this.color = r1;
    }

    public void setInterline(int r1, float r2) {
        this.interline = r2;
        this.interlineUnit = r1;
    }

    public void setLastFontId(int r1) {
        this.lastFontId = r1;
    }

    public void setScaleFactor(float r1) {
        this.scaleFactor = r1;
    }

    public void setSmallCap(boolean r1) {
        this.smallCap = r1;
    }

    public void setStyle(int r1) {
        this.style = r1;
    }

    public void setTextStyle(String r1) {
        this.textStyle = r1;
    }

    public void setTextwidth(int r1, float r2) {
        this.textwidth = r2 * SpaceAtom.getFactor(r1, this);
    }

    public TeXEnvironment subStyle() {
        TeXEnvironment r0 = copy();
        r0.style = ((this.style / 4) * 2) + 5;
        return r0;
    }

    public TeXEnvironment supStyle() {
        TeXEnvironment r0 = copy();
        int r2 = this.style;
        r0.style = (((r2 / 4) * 2) + 4) + (r2 % 2);
        return r0;
    }
}
