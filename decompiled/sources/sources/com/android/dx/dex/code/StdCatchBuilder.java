package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.dex.code.CatchTable;
import com.android.dx.rop.code.BasicBlock;
import com.android.dx.rop.code.BasicBlockList;
import com.android.dx.rop.code.RopMethod;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.IntList;
import java.util.ArrayList;
import java.util.HashSet;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class StdCatchBuilder implements CatchBuilder {
    private static final int MAX_CATCH_RANGE = 65535;
    private final BlockAddresses addresses;
    private final RopMethod method;
    private final int[] order;

    public StdCatchBuilder(RopMethod r2, int[] r3, BlockAddresses r4) {
        if (r2 == null) goto L12;
        if (r3 == null) goto L10;
        if (r4 == null) goto L8;
        this.method = r2;
        this.order = r3;
        this.addresses = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("addresses == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("order == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("method == null");
        throw null;
    }

    public static CatchTable build(RopMethod r10, int[] r11, BlockAddresses r12) {
        int r0 = r11.length;
        BasicBlockList r102 = r10.getBlocks();
        ArrayList r1 = new ArrayList(r0);
        CatchHandlerList r2 = CatchHandlerList.EMPTY;
        BasicBlock r3 = null;
        int r4 = 0;
        BasicBlock r5 = null;
        int r6 = 0;
    L3:
        if (r6 >= r0) goto L21;
        BasicBlock r7 = r102.labelToBlock(r11[r6]);
        if (r7.canThrow() == false) goto L19;
        CatchHandlerList r8 = handlersFor(r7, r12);
        if (r2.size() != 0) goto L11;
    L18:
        r3 = r7;
        r5 = r3;
        r2 = r8;
        goto L19
    L11:
        if (r2.equals(r8) == false) goto L16;
        if (rangeIsValid(r3, r7, r12) == false) goto L16;
        r5 = r7;
    L16:
        if (r2.size() == 0) goto L18;
        r1.add(makeEntry(r3, r5, r2, r12));
    L19:
        r6 = r6 + 1;
        goto L3
    L21:
        if (r2.size() == 0) goto L23;
        r1.add(makeEntry(r3, r5, r2, r12));
    L23:
        int r103 = r1.size();
        if (r103 == 0) goto L26;
        CatchTable r112 = new CatchTable(r103);
    L28:
        if (r4 >= r103) goto L30;
        r112.set(r4, (CatchTable.Entry) r1.get(r4));
        r4 = r4 + 1;
        goto L28
    L30:
        r112.setImmutable();
        return r112;
    L26:
        return CatchTable.EMPTY;
    }

    private static CatchHandlerList handlersFor(BasicBlock r6, BlockAddresses r7) {
        IntList r0 = r6.getSuccessors();
        int r1 = r0.size();
        int r2 = r6.getPrimarySuccessor();
        TypeList r62 = r6.getLastInsn().getCatches();
        int r3 = r62.size();
        if (r3 != 0) goto L7;
        return CatchHandlerList.EMPTY;
    L7:
        if (r2 != (-1)) goto L9;
        if (r1 == r3) goto L9;
    L15:
        GlShader$$ExternalSyntheticBUOutline1.m("shouldn't happen: weird successors list");
        return null;
    L9:
        if (r2 != (-1)) goto L11;
    L17:
        int r12 = 0;
        int r22 = 0;
    L18:
        if (r22 >= r3) goto L23;
        if (r62.getType(r22).equals(Type.OBJECT) == true) goto L21;
        r22 = r22 + 1;
        goto L18
    L21:
        r3 = r22 + 1;
    L23:
        CatchHandlerList r23 = new CatchHandlerList(r3);
    L24:
        if (r12 >= r3) goto L26;
        r23.set(r12, new CstType(r62.getType(r12)), r7.getStart(r0.get(r12)).getAddress());
        r12 = r12 + 1;
        goto L24
    L26:
        r23.setImmutable();
        return r23;
    L11:
        if (r1 != (r3 + 1)) goto L15;
        if (r2 != r0.get(r3)) goto L15;
        goto L15
    }

    private static CatchTable.Entry makeEntry(BasicBlock r0, BasicBlock r1, CatchHandlerList r2, BlockAddresses r3) {
        CodeAddress r02 = r3.getLast(r0);
        CodeAddress r12 = r3.getEnd(r1);
        return new CatchTable.Entry(r02.getAddress(), r12.getAddress(), r2);
    }

    private static boolean rangeIsValid(BasicBlock r1, BasicBlock r2, BlockAddresses r3) {
        if (r1 == null) goto L12;
        if (r2 == null) goto L10;
        int r12 = r3.getLast(r1).getAddress();
        if ((r3.getEnd(r2).getAddress() - r12) > 65535) goto L9;
        return true;
    L9:
        return false;
    L10:
        f$$ExternalSyntheticBUOutline2.m("end == null");
        return false;
    L12:
        f$$ExternalSyntheticBUOutline2.m("start == null");
        return false;
    }

    @Override // com.android.dx.dex.code.CatchBuilder
    public CatchTable build() {
        return build(this.method, this.order, this.addresses);
    }

    @Override // com.android.dx.dex.code.CatchBuilder
    public HashSet<Type> getCatchTypes() {
        HashSet<Type> r0 = new HashSet(20);
        BasicBlockList r8 = this.method.getBlocks();
        int r1 = r8.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        TypeList r4 = r8.get(r3).getLastInsn().getCatches();
        int r5 = r4.size();
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L7;
        r0.add(r4.getType(r6));
        r6 = r6 + 1;
        goto L5
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        return r0;
    }

    @Override // com.android.dx.dex.code.CatchBuilder
    public boolean hasAnyCatches() {
        BasicBlockList r4 = this.method.getBlocks();
        int r0 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4.get(r2).getLastInsn().getCatches().size() != 0) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }
}
