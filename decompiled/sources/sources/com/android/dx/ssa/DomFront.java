package com.android.dx.ssa;

import com.android.dx.util.IntSet;
import java.util.ArrayList;
import java.util.BitSet;

/* loaded from: classes4.dex */
public class DomFront {
    private static final boolean DEBUG = false;
    private final DomInfo[] domInfos;
    private final SsaMethod meth;
    private final ArrayList<SsaBasicBlock> nodes;

    public static class DomInfo {
        public IntSet dominanceFrontiers;
        public int idom;

        public DomInfo() {
            this.idom = -1;
        }
    }

    public DomFront(SsaMethod r4) {
        this.meth = r4;
        ArrayList<SsaBasicBlock> r42 = r4.getBlocks();
        this.nodes = r42;
        int r43 = r42.size();
        this.domInfos = new DomInfo[r43];
        int r0 = 0;
    L3:
        if (r0 >= r43) goto L5;
        this.domInfos[r0] = new DomInfo();
        r0 = r0 + 1;
        goto L3
    }

    private void buildDomTree() {
        int r0 = this.nodes.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        int r2 = this.domInfos[r1].idom;
        if (r2 == (-1)) goto L8;
        this.nodes.get(r2).addDomChild(this.nodes.get(r1));
    L8:
        r1 = r1 + 1;
        goto L3
    }

    private void calcDomFronts() {
        int r0 = this.nodes.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L20;
        SsaBasicBlock r3 = this.nodes.get(r2);
        DomInfo r4 = this.domInfos[r2];
        BitSet r32 = r3.getPredecessors();
        if (r32.cardinality() <= 1) goto L19;
        int r5 = r32.nextSetBit(0);
    L7:
        if (r5 < 0) goto L19;
        int r6 = r5;
    L10:
        if (r6 == r4.idom) goto L18;
        if (r6 == (-1)) goto L18;
        DomInfo r62 = this.domInfos[r6];
        if (r62.dominanceFrontiers.has(r2) == true) goto L18;
        r62.dominanceFrontiers.add(r2);
        r6 = r62.idom;
    L18:
        r5 = r32.nextSetBit(r5 + 1);
    L19:
        r2 = r2 + 1;
        goto L3
    }

    private void debugPrintDomChildren() {
        int r0 = this.nodes.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        SsaBasicBlock r3 = this.nodes.get(r2);
        StringBuffer r4 = new StringBuffer("{");
        ArrayList<SsaBasicBlock> r5 = r3.getDomChildren();
        int r6 = r5.size();
        boolean r7 = false;
        int r8 = 0;
    L5:
        if (r8 >= r6) goto L10;
        SsaBasicBlock r9 = r5.get(r8);
        r8 = r8 + 1;
        SsaBasicBlock r92 = r9;
        if (r7 == false) goto L9;
        r4.append(',');
    L9:
        r4.append(r92);
        r7 = true;
        goto L5
    L10:
        r4.append('}');
        System.out.println("domChildren[" + r3 + "]: " + r4);
        r2 = r2 + 1;
        goto L3
    }

    public DomInfo[] run() {
        int r0 = this.nodes.size();
        int r3 = 0;
        Dominators.make(this.meth, this.domInfos, false);
        buildDomTree();
    L3:
        if (r3 >= r0) goto L5;
        DomInfo r1 = this.domInfos[r3];
        r1.dominanceFrontiers = SetFactory.makeDomFrontSet(r0);
        r3 = r3 + 1;
        goto L3
    L5:
        calcDomFronts();
        return this.domInfos;
    }
}
