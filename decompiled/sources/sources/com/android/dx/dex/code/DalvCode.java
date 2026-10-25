package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.type.Type;
import java.util.HashSet;

/* loaded from: classes.dex */
public final class DalvCode {
    private CatchTable catches;
    private DalvInsnList insns;
    private LocalList locals;
    private final int positionInfo;
    private PositionList positions;
    private CatchBuilder unprocessedCatches;
    private OutputFinisher unprocessedInsns;

    public interface AssignIndicesCallback {
        int getIndex(Constant r1);
    }

    public DalvCode(int r2, OutputFinisher r3, CatchBuilder r4) {
        if (r3 == null) goto L9;
        if (r4 == null) goto L7;
        this.positionInfo = r2;
        this.unprocessedInsns = r3;
        this.unprocessedCatches = r4;
        this.catches = null;
        this.positions = null;
        this.locals = null;
        this.insns = null;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("unprocessedCatches == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("unprocessedInsns == null");
        throw null;
    }

    private void finishProcessingIfNecessary() {
        if (this.insns == null) goto L5;
        return;
    L5:
        DalvInsnList r0 = this.unprocessedInsns.finishProcessingAndGetList();
        this.insns = r0;
        this.positions = PositionList.make(r0, this.positionInfo);
        this.locals = LocalList.make(this.insns);
        this.catches = this.unprocessedCatches.build();
        this.unprocessedInsns = null;
        this.unprocessedCatches = null;
    }

    public void assignIndices(AssignIndicesCallback r1) {
        this.unprocessedInsns.assignIndices(r1);
    }

    public HashSet<Type> getCatchTypes() {
        return this.unprocessedCatches.getCatchTypes();
    }

    public CatchTable getCatches() {
        finishProcessingIfNecessary();
        return this.catches;
    }

    public HashSet<Constant> getInsnConstants() {
        return this.unprocessedInsns.getAllConstants();
    }

    public DalvInsnList getInsns() {
        finishProcessingIfNecessary();
        return this.insns;
    }

    public LocalList getLocals() {
        finishProcessingIfNecessary();
        return this.locals;
    }

    public PositionList getPositions() {
        finishProcessingIfNecessary();
        return this.positions;
    }

    public boolean hasAnyCatches() {
        return this.unprocessedCatches.hasAnyCatches();
    }

    public boolean hasLocals() {
        return this.unprocessedInsns.hasAnyLocalInfo();
    }

    public boolean hasPositions() {
        if (this.positionInfo != 1) goto L5;
        return false;
    L5:
        if (this.unprocessedInsns.hasAnyPositionInfo() == false) goto L9;
        return true;
    L9:
        return false;
    }
}
