package com.android.dx.ssa;

import com.android.dx.cf.code.Merger;
import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.type.TypeBearer;
import java.util.BitSet;
import java.util.List;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class PhiTypeResolver {
    SsaMethod ssaMeth;
    private final BitSet worklist;

    private PhiTypeResolver(SsaMethod r2) {
        this.ssaMeth = r2;
        this.worklist = new BitSet(r2.getRegCount());
    }

    private static boolean equalsHandlesNulls(LocalItem r0, LocalItem r1) {
        if (r0 == r1) goto L9;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.equals(r1) == true) goto L12;
        return false;
    L12:
        return true;
    L9:
        return true;
    }

    public static void process(SsaMethod r1) {
        new PhiTypeResolver(r1).run();
    }

    private void run() {
        int r0 = this.ssaMeth.getRegCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        SsaInsn r3 = this.ssaMeth.getDefinitionForRegister(r2);
        if (r3 == null) goto L9;
        if (r3.getResult().getBasicType() != 0) goto L9;
        this.worklist.set(r2);
    L9:
        r2 = r2 + 1;
    L10:
        int r02 = this.worklist.nextSetBit(0);
        if (r02 < 0) goto L22;
        this.worklist.clear(r02);
        if (resolveResultType((PhiInsn) this.ssaMeth.getDefinitionForRegister(r02)) == false) goto L10;
        List<SsaInsn> r03 = this.ssaMeth.getUseListForRegister(r02);
        int r22 = r03.size();
        int r32 = 0;
    L15:
        if (r32 >= r22) goto L10;
        SsaInsn r4 = r03.get(r32);
        RegisterSpec r5 = r4.getResult();
        if (r5 == null) goto L21;
        if ((r4 instanceof PhiInsn) == false) goto L21;
        this.worklist.set(r5.getReg());
    L21:
        r32 = r32 + 1;
        goto L15
    }

    public boolean resolveResultType(PhiInsn r12) {
        r12.updateSourcesToDefinitions(this.ssaMeth);
        RegisterSpecList r11 = r12.getSources();
        int r0 = r11.size();
        LocalItem r1 = null;
        int r2 = -1;
        RegisterSpec r5 = null;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L8;
        RegisterSpec r6 = r11.get(r4);
        if (r6.getBasicType() == 0) goto L7;
        r2 = r4;
        r5 = r6;
    L7:
        r4 = r4 + 1;
        goto L3
    L8:
        if (r5 != null) goto L10;
        return false;
    L10:
        LocalItem r42 = r5.getLocalItem();
        TypeBearer r52 = r5.getType();
        int r7 = 0;
        boolean r8 = true;
    L11:
        if (r7 >= r0) goto L24;
        if (r7 == r2) goto L23;
        RegisterSpec r9 = r11.get(r7);
        if (r9.getBasicType() == 0) goto L23;
        if (r8 == true) goto L19;
    L21:
        r8 = false;
    L22:
        r52 = Merger.mergeType(r52, r9.getType());
        goto L23
    L19:
        if (equalsHandlesNulls(r42, r9.getLocalItem()) == false) goto L21;
        r8 = true;
    L23:
        r7 = r7 + 1;
        goto L11
    L24:
        if (r52 == null) goto L34;
        if (r8 == false) goto L27;
        r1 = r42;
    L27:
        RegisterSpec r112 = r12.getResult();
        if (r112.getTypeBearer() == r52) goto L30;
    L32:
        r12.changeResultType(r52, r1);
        return true;
    L30:
        if (equalsHandlesNulls(r1, r112.getLocalItem()) == false) goto L32;
        return false;
    L34:
        StringBuilder r122 = new StringBuilder();
        int r13 = 0;
    L35:
        if (r13 >= r0) goto L37;
        r122.append(r11.get(r13).toString());
        r122.append(' ');
        r13 = r13 + 1;
        goto L35
    L37:
        MVEL$$ExternalSyntheticBUOutline0.m("Couldn't map types in phi insn:", r122);
        return false;
    }
}
