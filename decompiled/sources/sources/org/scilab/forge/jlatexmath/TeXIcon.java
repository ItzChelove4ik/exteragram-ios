package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Color;
import ru.noties.jlatexmath.awt.Component;
import ru.noties.jlatexmath.awt.Graphics;
import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.Insets;
import ru.noties.jlatexmath.awt.RenderingHints;
import ru.noties.jlatexmath.awt.geom.AffineTransform;
import ru.noties.jlatexmath.swing.Icon;

/* loaded from: classes5.dex */
public class TeXIcon implements Icon {
    private static final int MAX_PIXELS = 4096;
    private static final Color defaultColor = null;
    public static float defaultSize;
    public static float magFactor;
    private Box box;
    private Color fg;
    private Insets insets;
    public boolean isColored;
    private final float size;

    static {
        defaultColor = new Color(0, 0, 0);
        defaultSize = -1.0f;
        magFactor = 0.0f;
    }

    public TeXIcon(Box r2, float r3) {
        this(r2, r3, false);
    }

    public TeXIcon(Box r3, float r4, boolean r5) {
        this.insets = new Insets(0, 0, 0, 0);
        this.fg = null;
        this.isColored = false;
        this.box = r3;
        float r32 = defaultSize;
        if (r32 == (-1.0f)) goto L5;
        r4 = r32;
    L5:
        float r33 = magFactor;
        if (r33 == 0.0f) goto L8;
        this.size = Math.abs(r33) * r4;
    L9:
        if (r5 == true) goto L12;
        Insets r2 = this.insets;
        int r42 = (int) (r4 * 0.18f);
        r2.top += r42;
        r2.bottom += r42;
        r2.left += r42;
        r2.right += r42;
        return;
    L12:
        return;
    L8:
        this.size = r4;
        goto L9
    }

    private static int sanitizePx(int r1) {
        if (r1 < 0) goto L7;
        if (r1 > 4096) goto L7;
        return r1;
    L7:
        return 4096;
    }

    public float getBaseLine() {
        double r0 = ((this.box.getHeight() * this.size) + 0.99d) + this.insets.top;
        double r4 = ((this.box.getHeight() + this.box.getDepth()) * this.size) + 0.99d;
        Insets r6 = this.insets;
        return (float) (r0 / ((r4 + r6.top) + r6.bottom));
    }

    public Box getBox() {
        return this.box;
    }

    public int getIconDepth() {
        return sanitizePx((int) (((this.box.getDepth() * this.size) + 0.99d) + this.insets.bottom));
    }

    @Override // ru.noties.jlatexmath.swing.Icon
    public int getIconHeight() {
        return sanitizePx(((int) (((this.box.getHeight() * this.size) + 0.99d) + this.insets.top)) + ((int) (((this.box.getDepth() * this.size) + 0.99d) + this.insets.bottom)));
    }

    @Override // ru.noties.jlatexmath.swing.Icon
    public int getIconWidth() {
        double r0 = (this.box.getWidth() * this.size) + 0.99d;
        Insets r4 = this.insets;
        return sanitizePx((int) ((r0 + r4.left) + r4.right));
    }

    public Insets getInsets() {
        return this.insets;
    }

    public float getTrueIconDepth() {
        return this.box.getDepth() * this.size;
    }

    public float getTrueIconHeight() {
        return (this.box.getHeight() + this.box.getDepth()) * this.size;
    }

    public float getTrueIconWidth() {
        return this.box.getWidth() * this.size;
    }

    @Override // ru.noties.jlatexmath.swing.Icon
    public void paintIcon(Component r9, Graphics r10, int r11, int r12) {
        Graphics2D r102 = (Graphics2D) r10;
        RenderingHints r0 = r102.getRenderingHints();
        AffineTransform r1 = r102.getTransform();
        Color r2 = r102.getColor();
        r102.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        r102.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        r102.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        float r3 = this.size;
        r102.scale(r3, r3);
        Color r32 = this.fg;
        if (r32 == null) goto L5;
        r102.setColor(r32);
    L8:
        Box r92 = this.box;
        float r112 = r11 + this.insets.left;
        float r8 = this.size;
        r92.draw(r102, r112 / r8, ((r12 + r3.top) / r8) + r92.getHeight());
        r102.setRenderingHints(r0);
        r102.setTransform(r1);
        r102.setColor(r2);
        return;
    L5:
        if (r9 == null) goto L7;
        r102.setColor(r9.getForeground());
        goto L8
    L7:
        r102.setColor(defaultColor);
        goto L8
    }

    public void setForeground(Color r1) {
        this.fg = r1;
    }

    public void setIconHeight(int r3, int r4) {
        float r32 = r3 - getIconHeight();
        if (r32 <= 0.0f) goto L6;
        this.box = new VerticalBox(this.box, r32, r4);
        return;
    }

    public void setIconWidth(int r4, int r5) {
        float r42 = r4 - getIconWidth();
        if (r42 <= 0.0f) goto L6;
        Box r1 = this.box;
        this.box = new HorizontalBox(r1, r1.getWidth() + r42, r5);
        return;
    }

    public void setInsets(Insets r2) {
        setInsets(r2, false);
    }

    public void setInsets(Insets r3, boolean r4) {
        this.insets = r3;
        if (r4 == true) goto L6;
        int r42 = r3.top;
        float r2 = this.size;
        r3.top = r42 + ((int) (r2 * 0.18f));
        r3.bottom += (int) (r2 * 0.18f);
        r3.left += (int) (r2 * 0.18f);
        r3.right += (int) (r2 * 0.18f);
        return;
    }
}
