package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class DelimiterFactory {
    private static final float MAX_LENGTH = 4096.0f;

    public DelimiterFactory() {
    }

    public static Box create(String r4, TeXEnvironment r5, float r6) {
        if (Float.isFinite(r6) == true) goto L5;
    L6:
        r6 = 0.0f;
    L7:
        float r62 = Math.min(r6, MAX_LENGTH);
        TeXFont r0 = r5.getTeXFont();
        int r52 = r5.getStyle();
        Char r42 = r0.getChar(r4, r52);
        Metrics r1 = r42.getMetrics();
        float r2 = r1.getHeight();
        float r12 = r1.getDepth();
    L8:
        float r22 = r2 + r12;
        if (r22 >= r62) goto L14;
        if (r0.hasNextLarger(r42) == false) goto L14;
        r42 = r0.getNextLarger(r42, r52);
        Metrics r13 = r42.getMetrics();
        r2 = r13.getHeight();
        r12 = r13.getDepth();
    L14:
        if (r22 < r62) goto L18;
        return new CharBox(r42);
    L18:
        if (r0.isExtensionChar(r42) == false) goto L44;
        VerticalBox r14 = new VerticalBox();
        Extension r43 = r0.getExtension(r42, r52);
        if (r43.hasTop() == false) goto L22;
        r14.add(new CharBox(r43.getTop()));
    L22:
        boolean r53 = r43.hasMiddle();
        if (r53 == false) goto L26;
        r14.add(new CharBox(r43.getMiddle()));
    L26:
        if (r43.hasBottom() == false) goto L28;
        r14.add(new CharBox(r43.getBottom()));
    L28:
        CharBox r23 = new CharBox(r43.getRepeat());
    L30:
        if ((r14.getHeight() + r14.getDepth()) > r62) goto L42;
        if (r43.hasTop() == false) goto L39;
        if (r43.hasBottom() == false) goto L39;
        r14.add(1, r23);
        if (r53 == false) goto L30;
        r14.add(r14.getSize() - 1, r23);
    L39:
        if (r43.hasBottom() == true) goto L40;
        r14.add(r23);
        goto L30
    L40:
        r14.add(0, r23);
        goto L30
    L42:
        return r14;
    L44:
        return new CharBox(r42);
    L5:
        if (r6 >= 0.0f) goto L7;
        goto L6
    }

    public static Box create(SymbolAtom r5, TeXEnvironment r6, int r7) {
        if (r7 > 4) goto L5;
        TeXFont r0 = r6.getTeXFont();
        int r1 = r6.getStyle();
        Char r2 = r0.getChar(r5.getName(), r1);
        int r3 = 1;
    L7:
        if (r3 > r7) goto L11;
        if (r0.hasNextLarger(r2) == false) goto L11;
        r2 = r0.getNextLarger(r2, r1);
        r3 = r3 + 1;
    L11:
        if (r3 > r7) goto L17;
        if (r0.hasNextLarger(r2) == true) goto L17;
        CharBox r22 = new CharBox(r0.getChar('A', "mathnormal", r1));
        return create(r5.getName(), r6, r7 * (r22.getHeight() + r22.getDepth()));
    L17:
        return new CharBox(r2);
    L5:
        return r5.createBox(r6);
    }
}
