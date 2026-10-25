package com.android.dx.ssa.back;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.code.PlainInsn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.ssa.NormalSsaInsn;
import com.android.dx.ssa.RegisterMapper;
import com.android.dx.ssa.SsaBasicBlock;
import com.android.dx.ssa.SsaInsn;
import com.android.dx.ssa.SsaMethod;
import com.android.dx.util.IntIterator;
import java.util.ArrayList;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes4.dex */
public abstract class RegisterAllocator {
    protected final InterferenceGraph interference;
    protected final SsaMethod ssaMeth;

    public RegisterAllocator(SsaMethod r1, InterferenceGraph r2) {
        this.ssaMeth = r1;
        this.interference = r2;
    }

    public abstract RegisterMapper allocateRegisters();

    public final int getCategoryForSsaReg(int r1) {
        SsaInsn r0 = this.ssaMeth.getDefinitionForRegister(r1);
        if (r0 != null) goto L7;
        return 1;
    L7:
        return r0.getResult().getCategory();
    }

    public final RegisterSpec getDefinitionSpecForSsaReg(int r1) {
        SsaInsn r0 = this.ssaMeth.getDefinitionForRegister(r1);
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getResult();
    }

    public final RegisterSpec insertMoveBefore(SsaInsn r8, RegisterSpec r9) {
        SsaBasicBlock r0 = r8.getBlock();
        ArrayList<SsaInsn> r1 = r0.getInsns();
        int r2 = r1.indexOf(r8);
        if (r2 >= 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("specified insn is not in this block");
        return null;
    L5:
        if (r2 != (r1.size() - 1)) goto L15;
        RegisterSpec r3 = RegisterSpec.make(this.ssaMeth.makeNewSsaReg(), r9.getTypeBearer());
        r1.add(r2, SsaInsn.makeFromRop(new PlainInsn(Rops.opMove(r3.getType()), SourcePosition.NO_INFO, r3, RegisterSpecList.make(r9)), r0));
        int r92 = r3.getReg();
        IntIterator r02 = r0.getLiveOutRegs().iterator();
    L8:
        if (r02.hasNext() == false) goto L10;
        this.interference.add(r92, r02.next());
        goto L8
    L10:
        RegisterSpecList r82 = r8.getSources();
        int r03 = r82.size();
        int r12 = 0;
    L11:
        if (r12 >= r03) goto L13;
        this.interference.add(r92, r82.get(r12).getReg());
        r12 = r12 + 1;
        goto L11
    L13:
        this.ssaMeth.onInsnsChanged();
        return r3;
    L15:
        Buffer$$ExternalSyntheticBUOutline4.m("Adding move here not supported:", r8.toHuman());
        return null;
    }

    public boolean isDefinitionMoveParam(int r2) {
        SsaInsn r1 = this.ssaMeth.getDefinitionForRegister(r2);
        if ((r1 instanceof NormalSsaInsn) == true) goto L5;
    L8:
        return false;
    L5:
        if (((NormalSsaInsn) r1).getOpcode().getOpcode() != 3) goto L8;
        return true;
    }

    public abstract boolean wantsParamsMovedHigh();
}
