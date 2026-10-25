package org.scilab.forge.jlatexmath;

import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class MultlineAtom extends Atom {
    public static final int GATHER = 1;
    public static final int GATHERED = 2;
    public static final int MULTLINE = 0;
    public static SpaceAtom vsep_in;
    private ArrayOfAtoms column;
    private boolean isPartial;
    private int type;

    static {
        vsep_in = new SpaceAtom(1, 0.0f, 1.0f, 0.0f);
    }

    public MultlineAtom(ArrayOfAtoms r2, int r3) {
        this(false, r2, r3);
    }

    public MultlineAtom(boolean r1, ArrayOfAtoms r2, int r3) {
        this.isPartial = r1;
        this.column = r2;
        this.type = r3;
    }

    @Override // org.scilab.forge.jlatexmath.Atom
    public Box createBox(TeXEnvironment r12) {
        float r0 = r12.getTextwidth();
        if (r0 == Float.POSITIVE_INFINITY) goto L34;
        int r2 = 2;
        if (this.type == 2) goto L34;
        VerticalBox r1 = new VerticalBox();
        Atom r3 = this.column.array.get(0).get(0);
        if (this.type != 1) goto L10;
        int r5 = 2;
    L11:
        int r7 = r3.alignment;
        if (r7 == (-1)) goto L14;
        r5 = r7;
    L14:
        r1.add(new HorizontalBox(r3.createBox(r12), r0, r5));
        Box r32 = vsep_in.createBox(r12);
        int r52 = 1;
    L15:
        ArrayOfAtoms r72 = this.column;
        int r9 = r72.row;
        if (r52 >= (r9 - 1)) goto L22;
        Atom r73 = r72.array.get(r52).get(0);
        int r92 = r73.alignment;
        if (r92 != (-1)) goto L21;
        r92 = 2;
    L21:
        r1.add(r32);
        r1.add(new HorizontalBox(r73.createBox(r12), r0, r92));
        r52 = r52 + 1;
        goto L15
    L22:
        if (r9 <= 1) goto L31;
        Atom r4 = r72.array.get(r9 - 1).get(0);
        if (this.type == 1) goto L27;
        r2 = 1;
    L27:
        int r11 = r4.alignment;
        if (r11 == (-1)) goto L30;
        r2 = r11;
    L30:
        r1.add(r32);
        r1.add(new HorizontalBox(r4.createBox(r12), r0, r2));
    L31:
        float r112 = (r1.getHeight() + r1.getDepth()) / 2.0f;
        r1.setHeight(r112);
        r1.setDepth(r112);
        return r1;
    L10:
        r5 = 0;
    L34:
        return new MatrixAtom(this.isPartial, this.column, _UrlKt.FRAGMENT_ENCODE_SET).createBox(r12);
    }
}
