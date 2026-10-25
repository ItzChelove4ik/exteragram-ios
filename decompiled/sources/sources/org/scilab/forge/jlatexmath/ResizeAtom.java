package org.scilab.forge.jlatexmath;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class ResizeAtom extends Atom {
    private Atom base;
    private float h;
    private int hunit;
    private boolean keepaspectratio;
    private float w;
    private int wunit;

    public ResizeAtom(Atom r4, String r5, String r6, boolean r7) {
        this.type = r4.type;
        this.base = r4;
        this.keepaspectratio = r7;
        if (r5 != null) goto L5;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        float[] r52 = SpaceAtom.getLength(r5);
        if (r6 != null) goto L8;
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        float[] r42 = SpaceAtom.getLength(r6);
        if (r52.length == 2) goto L11;
        this.wunit = -1;
    L13:
        if (r42.length == 2) goto L16;
        this.hunit = -1;
        return;
    L16:
        this.hunit = (int) r42[0];
        this.h = r42[1];
        return;
    L11:
        this.wunit = (int) r52[0];
        this.w = r52[1];
        goto L13
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r8) {
        Box r2 = this.base.createBox(r8);
        int r0 = this.wunit;
        if (r0 == (-1)) goto L5;
    L7:
        if (r0 != (-1)) goto L9;
    L15:
        if (r0 != (-1)) goto L17;
    L19:
        double r02 = (this.h * SpaceAtom.getFactor(this.hunit, r8)) / r2.height;
    L13:
        double r3 = r02;
        double r5 = r3;
    L21:
        return new ScaleBox(r2, r3, r5);
    L17:
        if (this.hunit != (-1)) goto L19;
        r02 = (this.w * SpaceAtom.getFactor(r0, r8)) / r2.width;
        goto L13
    L9:
        if (this.hunit == (-1)) goto L15;
        double r03 = (this.w * SpaceAtom.getFactor(r0, r8)) / r2.width;
        double r32 = (this.h * SpaceAtom.getFactor(this.hunit, r8)) / r2.height;
        if (this.keepaspectratio == false) goto L14;
        r02 = Math.min(r03, r32);
        goto L13
    L14:
        r5 = r32;
        r3 = r03;
        goto L21
    L5:
        if (this.hunit != (-1)) goto L7;
        return r2;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getLeftType() {
        return this.base.getLeftType();
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public int getRightType() {
        return this.base.getRightType();
    }
}
