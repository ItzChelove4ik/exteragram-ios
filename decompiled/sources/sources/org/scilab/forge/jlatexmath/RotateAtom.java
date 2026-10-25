package org.scilab.forge.jlatexmath;

import java.util.Map;

/* loaded from: classes5.dex */
public class RotateAtom extends Atom {
    private double angle;
    private Atom base;
    private int option;
    private float x;
    private int xunit;
    private float y;
    private int yunit;

    public RotateAtom(Atom r4, double r5, String r7) {
        this.option = -1;
        this.type = r4.type;
        this.base = r4;
        this.angle = r5;
        Map<String, String> r42 = ParseOption.parseMap(r7);
        if (r42.containsKey("origin") == false) goto L7;
        this.option = RotateBox.getOrigin(r42.get("origin"));
        return;
    L7:
        if (r42.containsKey("x") == false) goto L9;
        float[] r52 = SpaceAtom.getLength(r42.get("x"));
        this.xunit = (int) r52[0];
        this.x = r52[1];
    L11:
        if (r42.containsKey("y") == false) goto L14;
        float[] r43 = SpaceAtom.getLength(r42.get("y"));
        this.yunit = (int) r43[0];
        this.y = r43[1];
        return;
    L14:
        this.yunit = 3;
        this.y = 0.0f;
        return;
    L9:
        this.xunit = 3;
        this.x = 0.0f;
        goto L11
    }

    public RotateAtom(Atom r2, String r3, String r4) {
        this.option = -1;
        this.type = r2.type;
        this.base = r2;
        this.angle = Double.parseDouble(r3);
        this.option = RotateBox.getOrigin(r4);
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r10) {
        if (this.option == (-1)) goto L7;
        return new RotateBox(this.base.createBox(r10), this.angle, this.option);
    L7:
        return new RotateBox(this.base.createBox(r10), this.angle, this.x * SpaceAtom.getFactor(this.xunit, r10), this.y * SpaceAtom.getFactor(this.yunit, r10));
    }
}
