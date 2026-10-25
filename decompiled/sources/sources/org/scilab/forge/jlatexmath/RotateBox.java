package org.scilab.forge.jlatexmath;

import ru.noties.jlatexmath.awt.Graphics2D;
import ru.noties.jlatexmath.awt.geom.Point2D;

/* loaded from: classes5.dex */
public class RotateBox extends Box {
    public static final int BBC = 8;
    public static final int BBL = 6;
    public static final int BBR = 7;
    public static final int BC = 1;
    public static final int BL = 0;
    public static final int BR = 2;
    public static final int CC = 10;
    public static final int CL = 9;
    public static final int CR = 11;
    public static final int TC = 4;
    public static final int TL = 3;
    public static final int TR = 5;
    protected double angle;
    private Box box;
    private float shiftX;
    private float shiftY;
    private float xmax;
    private float xmin;
    private float ymax;
    private float ymin;

    public RotateBox(Box r17, double r18, float r20, float r21) {
        this.box = r17;
        double r2 = (3.141592653589793d * r18) / 180.0d;
        this.angle = r2;
        this.height = r17.height;
        this.depth = r17.depth;
        this.width = r17.width;
        double r1 = Math.sin(r2);
        double r3 = Math.cos(this.angle);
        double r5 = r20;
        double r7 = 1.0d - r3;
        double r11 = r21;
        this.shiftX = (float) ((r5 * r7) + (r11 * r1));
        this.shiftY = (float) ((r11 * r7) - (r5 * r1));
        float r52 = this.height;
        float r8 = this.depth;
        float r112 = this.width;
        this.xmax = ((float) Math.max((-r52) * r1, Math.max(r8 * r1, Math.max((r112 * r3) + (r8 * r1), (r112 * r3) - (r52 * r1))))) + this.shiftX;
        float r12 = this.height;
        float r22 = this.depth;
        float r9 = this.width;
        this.xmin = ((float) Math.min((-r12) * r1, Math.min(r22 * r1, Math.min((r9 * r3) + (r22 * r1), (r9 * r3) - (r12 * r1))))) + this.shiftX;
        float r13 = this.height;
        float r23 = this.depth;
        float r92 = this.width;
        this.ymax = (float) Math.max(r13 * r3, Math.max((-r23) * r3, Math.max((r92 * r1) - (r23 * r3), (r92 * r1) + (r13 * r3))));
        float r14 = this.height;
        float r24 = this.depth;
        float r93 = this.width;
        float r15 = (float) Math.min(r14 * r3, Math.min((-r24) * r3, Math.min((r93 * r1) - (r24 * r3), (r93 * r1) + (r14 * r3))));
        this.ymin = r15;
        this.width = this.xmax - this.xmin;
        float r25 = this.ymax;
        float r32 = this.shiftY;
        this.height = r25 + r32;
        this.depth = (-r15) - r32;
    }

    public RotateBox(Box r1, double r2, int r4) {
        this(r1, r2, calculateShift(r1, r4));
    }

    public RotateBox(Box r7, double r8, Point2D.Float r10) {
        this(r7, r8, r10.x, r10.y);
    }

    private static Point2D.Float calculateShift(Box r3, int r4) {
        Point2D.Float r0 = new Point2D.Float(0.0f, -r3.depth);
        switch(r4) {
            case 0: goto L27;
            case 1: goto L25;
            case 2: goto L23;
            case 3: goto L21;
            case 4: goto L19;
            case 5: goto L17;
            case 6: goto L15;
            case 7: goto L13;
            case 8: goto L11;
            case 9: goto L9;
            case 10: goto L7;
            case 11: goto L5;
            default: goto L4;
        };
    L4:
        return r0;
    L5:
        r0.x = r3.width;
        r0.y = (r3.height - r3.depth) / 2.0f;
        return r0;
    L7:
        r0.x = r3.width / 2.0f;
        r0.y = (r3.height - r3.depth) / 2.0f;
        return r0;
    L9:
        r0.x = 0.0f;
        r0.y = (r3.height - r3.depth) / 2.0f;
        return r0;
    L11:
        r0.x = r3.width / 2.0f;
        r0.y = 0.0f;
        return r0;
    L13:
        r0.x = r3.width;
        r0.y = 0.0f;
        return r0;
    L15:
        r0.x = 0.0f;
        r0.y = 0.0f;
        return r0;
    L17:
        r0.x = r3.width;
        r0.y = r3.height;
        return r0;
    L19:
        r0.x = r3.width / 2.0f;
        r0.y = r3.height;
        return r0;
    L21:
        r0.x = 0.0f;
        r0.y = r3.height;
        return r0;
    L23:
        r0.x = r3.width;
        r0.y = -r3.depth;
        return r0;
    L25:
        r0.x = r3.width / 2.0f;
        r0.y = -r3.depth;
        return r0;
    L27:
        r0.x = 0.0f;
        r0.y = -r3.depth;
        return r0;
    }

    public static int getOrigin(String r3) {
        if (r3 != null) goto L5;
    L90:
        return 6;
    L5:
        if (r3.length() == 0) goto L90;
        if (r3.length() != 1) goto L11;
        r3 = r3.concat("c");
    L11:
        if (r3.equals("bl") == false) goto L13;
        return 0;
    L13:
        if (r3.equals("lb") == false) goto L16;
        return 0;
    L16:
        if (r3.equals("bc") == false) goto L18;
    L87:
        return 1;
    L18:
        if (r3.equals("cb") == true) goto L87;
        if (r3.equals("br") == false) goto L23;
        return 2;
    L23:
        if (r3.equals("rb") == false) goto L26;
        return 2;
    L26:
        if (r3.equals("cl") == false) goto L28;
        return 9;
    L28:
        if (r3.equals("lc") == false) goto L31;
        return 9;
    L31:
        if (r3.equals("cc") == false) goto L35;
        return 10;
    L35:
        if (r3.equals("cr") == false) goto L37;
        return 11;
    L37:
        if (r3.equals("cr") == false) goto L40;
        return 11;
    L40:
        if (r3.equals("tl") == false) goto L42;
        return 3;
    L42:
        if (r3.equals("lt") == false) goto L45;
        return 3;
    L45:
        if (r3.equals("tc") == false) goto L47;
        return 4;
    L47:
        if (r3.equals("ct") == false) goto L50;
        return 4;
    L50:
        if (r3.equals("tr") == false) goto L52;
        return 5;
    L52:
        if (r3.equals("rt") == false) goto L55;
        return 5;
    L55:
        if (r3.equals("Bl") == false) goto L57;
    L74:
        return 6;
    L57:
        if (r3.equals("lB") == true) goto L74;
        if (r3.equals("Bc") == false) goto L62;
        return 8;
    L62:
        if (r3.equals("cB") == false) goto L65;
        return 8;
    L65:
        if (r3.equals("Br") == false) goto L67;
        return 7;
    L67:
        if (r3.equals("rB") == true) goto L91;
        return 6;
    L91:
        return 7;
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public void draw(Graphics2D r12, float r13, float r14) {
        drawDebug(r12, r13, r14);
        this.box.drawDebug(r12, r13, r14, true);
        float r9 = r14 - this.shiftY;
        float r10 = r13 + (this.shiftX - this.xmin);
        double r4 = r10;
        double r6 = r9;
        r12.rotate(-this.angle, r4, r6);
        this.box.draw(r12, r10, r9);
        this.box.drawDebug(r12, r10, r9, true);
        r12.rotate(this.angle, r4, r6);
    }

    @Override // org.scilab.forge.jlatexmath.Box
    public int getLastFontId() {
        return this.box.getLastFontId();
    }
}
