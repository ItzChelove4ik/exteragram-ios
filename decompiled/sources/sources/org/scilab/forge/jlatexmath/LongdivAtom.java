package org.scilab.forge.jlatexmath;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class LongdivAtom extends VRowAtom {
    public LongdivAtom(long r18, long r20) {
        setHalign(1);
        setVtop(true);
        String[] r2 = makeResults(r18, r20);
        RuleAtom r3 = new RuleAtom(1, 0.0f, 1, 2.6f, 1, 0.5f);
        int r5 = 0;
    L4:
        if (r5 >= r2.length) goto L15;
        Atom r6 = new TeXFormula(r2[r5]).root;
        if ((r5 % 2) != 0) goto L11;
        RowAtom r7 = new RowAtom(r6);
        r7.add(r3);
        if (r5 != 0) goto L10;
        append(r7);
    L14:
        r5 = r5 + 1;
        goto L4
    L10:
        append(new UnderlinedAtom(r7));
        goto L14
    L11:
        if (r5 != 1) goto L13;
        String r72 = Long.toString(r18);
        BigDelimiterAtom r10 = new BigDelimiterAtom(SymbolAtom.get(TeXFormula.symbolMappings[41]), 1);
        RowAtom r9 = new RowAtom(new PhantomAtom(r10, false, true, true));
        r9.add(new SmashedAtom(new RaiseAtom(r10, 13, 3.5f, 13, 0.0f, 13, 0.0f)));
        r9.add(r6);
        OverlinedAtom r62 = new OverlinedAtom(r9);
        RowAtom r8 = new RowAtom(new TeXFormula(r72).root);
        r8.add(new SpaceAtom(1));
        r8.add(r62);
        append(r8);
        goto L14
    L13:
        RowAtom r73 = new RowAtom(r6);
        r73.add(r3);
        append(r73);
        goto L14
    }

    private String[] makeResults(long r10, long r12) {
        ArrayList r9 = new ArrayList();
        long r0 = r12 / r10;
        r9.add(Long.toString(r0));
        r9.add(Long.toString(r12));
        int r1 = Long.toString(r0).length();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        long r3 = ((r0.charAt(r2) - '0') * ((long) Math.pow(10.0d, (r1 - r2) - 1))) * r10;
        r12 = r12 - r3;
        r9.add(Long.toString(r3));
        r9.add(Long.toString(r12));
        r2 = r2 + 1;
        goto L3
    L6:
        return (String[]) r9.toArray(new String[r9.size()]);
    }
}
