package org.scilab.forge.jlatexmath;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class XLeftRightArrowFactory {
    private static final Atom LEFT = null;
    private static final Atom MINUS = null;
    private static final Atom RIGHT = null;

    static {
        MINUS = SymbolAtom.get("minus");
        LEFT = SymbolAtom.get("leftarrow");
        RIGHT = SymbolAtom.get("rightarrow");
    }

    public XLeftRightArrowFactory() {
    }

    public static Box create(TeXEnvironment r13, float r14) {
        float r1 = 0.0f;
        if (Float.isFinite(r14) == true) goto L5;
    L6:
        r14 = 0.0f;
    L7:
        float r142 = Math.min(r14, 4096.0f);
        Box r0 = LEFT.createBox(r13);
        Box r2 = RIGHT.createBox(r13);
        float r3 = r0.getWidth() + r2.getWidth();
        if (r142 >= r3) goto L11;
        HorizontalBox r132 = new HorizontalBox(r0);
        r132.add(new StrutBox(-Math.min(r3 - r142, r0.getWidth()), 0.0f, 0.0f, 0.0f));
        r132.add(r2);
        return r132;
    L11:
        Box r8 = new SmashedAtom(MINUS, _UrlKt.FRAGMENT_ENCODE_SET).createBox(r13);
        Box r133 = new SpaceAtom(5, -3.4f, 0.0f, 0.0f).createBox(r13);
        float r4 = r8.getWidth() + r133.getWidth();
        float r32 = r3 + (r133.getWidth() * 2.0f);
        HorizontalBox r5 = new HorizontalBox();
    L13:
        if (r1 >= ((r142 - r32) - r4)) goto L15;
        r5.add(r8);
        r5.add(r133);
        r1 = r1 + r4;
        goto L13
    L15:
        r5.add(new ScaleBox(r8, (r6 - r1) / r8.getWidth(), 1.0d));
        r5.add(0, r133);
        r5.add(0, r0);
        r5.add(r133);
        r5.add(r2);
        return r5;
    L5:
        if (r14 >= 0.0f) goto L7;
        goto L6
    }

    public static Box create(boolean r21, TeXEnvironment r22, float r23) {
        if (Float.isFinite(r23) == true) goto L5;
    L8:
        float r1 = 0.0f;
    L9:
        float r12 = Math.min(r1, 4096.0f);
        if (r21 == false) goto L13;
        Atom r3 = LEFT;
    L12:
        Box r32 = r3.createBox(r22);
        float r4 = r32.getHeight();
        float r5 = r32.getDepth();
        float r6 = r32.getWidth();
        if (r12 > r6) goto L18;
        r32.setDepth(r5 / 2.0f);
        return r32;
    L18:
        Box r7 = new SmashedAtom(MINUS, _UrlKt.FRAGMENT_ENCODE_SET).createBox(r22);
        Box r9 = new SpaceAtom(5, -4.0f, 0.0f, 0.0f).createBox(r22);
        float r10 = r7.getWidth() + r9.getWidth();
        float r62 = r6 + r9.getWidth();
        HorizontalBox r122 = new HorizontalBox();
        float r13 = 0.0f;
    L19:
        float r14 = r12 - r62;
        if (r13 >= (r14 - r10)) goto L22;
        r122.add(r7);
        r122.add(r9);
        r13 = r13 + r10;
        goto L19
    L22:
        float r142 = (r14 - r13) / r7.getWidth();
        float r63 = (-2.0f) * r142;
        r122.add(new SpaceAtom(5, r63, 0.0f, 0.0f).createBox(r22));
        r122.add(new ScaleAtom(MINUS, r142, 1.0d).createBox(r22));
        if (r21 == false) goto L25;
        r122.add(0, new SpaceAtom(5, -3.5f, 0.0f, 0.0f).createBox(r22));
        r122.add(0, r32);
    L26:
        r122.setDepth(r5 / 2.0f);
        r122.setHeight(r4);
        return r122;
    L25:
        r122.add(new SpaceAtom(5, r63 - 2.0f, 0.0f, 0.0f).createBox(r22));
        r122.add(r32);
        goto L26
    L13:
        r3 = RIGHT;
        goto L12
    L5:
        if (r23 < 0.0f) goto L8;
        r1 = r23;
        goto L9
    }
}
