package com.android.dx.ssa;

import com.android.dx.ssa.DomFront;
import com.android.dx.ssa.SsaBasicBlock;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;

/* loaded from: classes4.dex */
public final class Dominators {
    private final ArrayList<SsaBasicBlock> blocks;
    private final DomFront.DomInfo[] domInfos;
    private final DFSInfo[] info;
    private final SsaMethod meth;
    private final boolean postdom;
    private final ArrayList<SsaBasicBlock> vertex;

    /* renamed from: com.android.dx.ssa.Dominators$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class DFSInfo {
        public SsaBasicBlock ancestor;
        public ArrayList<SsaBasicBlock> bucket;
        public SsaBasicBlock parent;
        public SsaBasicBlock rep;
        public int semidom;

        public DFSInfo() {
            this.bucket = new ArrayList();
        }
    }

    public class DfsWalker implements SsaBasicBlock.Visitor {
        private int dfsNum;
        final /* synthetic */ Dominators this$0;

        private DfsWalker(Dominators r1) {
            this.this$0 = r1;
            this.dfsNum = 0;
        }

        public /* synthetic */ DfsWalker(Dominators r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // com.android.dx.ssa.SsaBasicBlock.Visitor
        public void visitBlock(SsaBasicBlock r3, SsaBasicBlock r4) {
            DFSInfo r0 = new DFSInfo();
            int r1 = this.dfsNum + 1;
            this.dfsNum = r1;
            r0.semidom = r1;
            r0.rep = r3;
            r0.parent = r4;
            Dominators.access$100(this.this$0).add(r3);
            Dominators.access$200(this.this$0)[r3.getIndex()] = r0;
        }
    }

    private Dominators(SsaMethod r1, DomFront.DomInfo[] r2, boolean r3) {
        this.meth = r1;
        this.domInfos = r2;
        this.postdom = r3;
        ArrayList<SsaBasicBlock> r12 = r1.getBlocks();
        this.blocks = r12;
        this.info = new DFSInfo[r12.size() + 2];
        this.vertex = new ArrayList();
    }

    public static /* synthetic */ ArrayList access$100(Dominators r0) {
        return r0.vertex;
    }

    public static /* synthetic */ DFSInfo[] access$200(Dominators r0) {
        return r0.info;
    }

    private void compress(SsaBasicBlock r8) {
        if (this.info[this.info[r8.getIndex()].ancestor.getIndex()].ancestor == null) goto L19;
        ArrayList r0 = new ArrayList();
        HashSet r1 = new HashSet();
        r0.add(r8);
    L6:
        if (r0.isEmpty() == true) goto L29;
        int r82 = r0.size() - 1;
        DFSInfo r2 = this.info[((SsaBasicBlock) r0.get(r82)).getIndex()];
        SsaBasicBlock r3 = r2.ancestor;
        DFSInfo r4 = this.info[r3.getIndex()];
        if (r1.add(r3) == false) goto L12;
        if (r4.ancestor == null) goto L12;
        r0.add(r3);
    L12:
        r0.remove(r82);
        if (r4.ancestor == null) goto L6;
        SsaBasicBlock r83 = r4.rep;
        if (this.info[r83.getIndex()].semidom >= this.info[r2.rep.getIndex()].semidom) goto L18;
        r2.rep = r83;
    L18:
        r2.ancestor = r4.ancestor;
        goto L6
    L29:
        return;
    }

    private SsaBasicBlock eval(SsaBasicBlock r3) {
        DFSInfo r0 = this.info[r3.getIndex()];
        if (r0.ancestor != null) goto L5;
        return r3;
    L5:
        compress(r3);
        return r0.rep;
    }

    private BitSet getPreds(SsaBasicBlock r1) {
        if (this.postdom == false) goto L7;
        return r1.getSuccessors();
    L7:
        return r1.getPredecessors();
    }

    private BitSet getSuccs(SsaBasicBlock r1) {
        if (this.postdom == false) goto L7;
        return r1.getPredecessors();
    L7:
        return r1.getSuccessors();
    }

    public static Dominators make(SsaMethod r1, DomFront.DomInfo[] r2, boolean r3) {
        Dominators r0 = new Dominators(r1, r2, r3);
        r0.run();
        return r0;
    }

    private void run() {
        boolean r0 = this.postdom;
        SsaMethod r1 = this.meth;
        if (r0 == false) goto L5;
        SsaBasicBlock r02 = r1.getExitBlock();
    L6:
        if (r02 == null) goto L8;
        this.vertex.add(r02);
        this.domInfos[r02.getIndex()].idom = r02.getIndex();
    L8:
        this.meth.forEachBlockDepthFirst(this.postdom, new DfsWalker(this, null));
        int r03 = this.vertex.size() - 1;
        int r12 = r03;
    L9:
        int r2 = 2;
        if (r12 < 2) goto L27;
        SsaBasicBlock r22 = this.vertex.get(r12);
        DFSInfo r3 = this.info[r22.getIndex()];
        BitSet r4 = getPreds(r22);
        int r5 = r4.nextSetBit(0);
    L12:
        if (r5 < 0) goto L19;
        SsaBasicBlock r6 = this.blocks.get(r5);
        if (this.info[r6.getIndex()] == null) goto L18;
        int r62 = this.info[eval(r6).getIndex()].semidom;
        if (r62 >= r3.semidom) goto L18;
        r3.semidom = r62;
    L18:
        r5 = r4.nextSetBit(r5 + 1);
        goto L12
    L19:
        this.info[this.vertex.get(r3.semidom).getIndex()].bucket.add(r22);
        SsaBasicBlock r23 = r3.parent;
        r3.ancestor = r23;
        ArrayList<SsaBasicBlock> r24 = this.info[r23.getIndex()].bucket;
    L21:
        if (r24.isEmpty() == true) goto L26;
        SsaBasicBlock r42 = r24.remove(r24.size() - 1);
        SsaBasicBlock r52 = eval(r42);
        int r63 = this.info[r52.getIndex()].semidom;
        int r7 = this.info[r42.getIndex()].semidom;
        DomFront.DomInfo[] r8 = this.domInfos;
        if (r63 < r7) goto L24;
        r8[r42.getIndex()].idom = r3.parent.getIndex();
        goto L21
    L24:
        r8[r42.getIndex()].idom = r52.getIndex();
        goto L21
    L26:
        r12 = r12 - 1;
    L27:
        if (r2 > r03) goto L32;
        SsaBasicBlock r13 = this.vertex.get(r2);
        if (this.domInfos[r13.getIndex()].idom == this.vertex.get(this.info[r13.getIndex()].semidom).getIndex()) goto L31;
        DomFront.DomInfo r32 = this.domInfos[r13.getIndex()];
        DomFront.DomInfo[] r43 = this.domInfos;
        r32.idom = r43[r43[r13.getIndex()].idom].idom;
    L31:
        r2 = r2 + 1;
        goto L27
    L32:
        return;
    L5:
        r02 = r1.getEntryBlock();
        goto L6
    }
}
