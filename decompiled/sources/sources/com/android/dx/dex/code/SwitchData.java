package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.android.dx.util.IntList;

/* loaded from: classes4.dex */
public final class SwitchData extends VariableSizeInsn {
    private final IntList cases;
    private final boolean packed;
    private final CodeAddress[] targets;
    private final CodeAddress user;

    public SwitchData(SourcePosition r3, CodeAddress r4, IntList r5, CodeAddress[] r6) {
        super(r3, RegisterSpecList.EMPTY);
        if (r4 == null) goto L20;
        if (r5 == null) goto L18;
        if (r6 == null) goto L16;
        int r0 = r5.size();
        if (r0 == r6.length) goto L9;
        f$$ExternalSyntheticBUOutline1.m("cases / targets mismatch");
        throw null;
    L9:
        if (r0 > 65535) goto L12;
        this.user = r4;
        this.cases = r5;
        this.targets = r6;
        this.packed = shouldPack(r5);
        return;
    L12:
        f$$ExternalSyntheticBUOutline1.m("too many cases");
        throw null;
    L16:
        f$$ExternalSyntheticBUOutline2.m("targets == null");
        throw null;
    L18:
        f$$ExternalSyntheticBUOutline2.m("cases == null");
        throw null;
    L20:
        f$$ExternalSyntheticBUOutline2.m("user == null");
        throw null;
    }

    private static long packedCodeSize(IntList r5) {
        int r0 = r5.size();
        long r3 = (((r5.get(r0 - 1) - r5.get(0)) + 1) * 2) + 4;
        if (r3 > 2147483647L) goto L5;
        return r3;
    L5:
        return -1;
    }

    private static boolean shouldPack(IntList r7) {
        if (r7.size() >= 2) goto L5;
        return true;
    L5:
        long r0 = packedCodeSize(r7);
        long r3 = sparseCodeSize(r7);
        if (r0 >= 0) goto L8;
        return false;
    L8:
        if (r0 > ((r3 * 5) / 4)) goto L12;
        return true;
    L12:
        return false;
    }

    private static long sparseCodeSize(IntList r4) {
        return (r4.size() * 4) + 2;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String argString() {
        StringBuilder r0 = new StringBuilder(100);
        int r1 = this.targets.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.append("\n    ");
        r0.append(this.cases.get(r2));
        r0.append(": ");
        r0.append(this.targets[r2]);
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public int codeSize() {
        boolean r0 = this.packed;
        IntList r2 = this.cases;
        if (r0 == false) goto L7;
        long r02 = packedCodeSize(r2);
    L6:
        return (int) r02;
    L7:
        r02 = sparseCodeSize(r2);
        goto L6
    }

    public boolean isPacked() {
        return this.packed;
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public String listingString0(boolean r7) {
        int r72 = this.user.getAddress();
        StringBuilder r0 = new StringBuilder(100);
        int r1 = this.targets.length;
        if (this.packed == false) goto L5;
        String r2 = "packed";
    L6:
        r0.append(r2);
        r0.append("-switch-payload // for switch @ ");
        r0.append(Hex.u2(r72));
        int r22 = 0;
    L7:
        if (r22 >= r1) goto L10;
        int r3 = this.targets[r22].getAddress();
        int r4 = r3 - r72;
        r0.append("\n  ");
        r0.append(this.cases.get(r22));
        r0.append(": ");
        r0.append(Hex.u4(r3));
        r0.append(" // ");
        r0.append(Hex.s4(r4));
        r22 = r22 + 1;
        goto L7
    L10:
        return r0.toString();
    L5:
        r2 = "sparse";
        goto L6
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public DalvInsn withRegisters(RegisterSpecList r4) {
        return new SwitchData(getPosition(), this.user, this.cases, this.targets);
    }

    @Override // com.android.dx.dex.code.DalvInsn
    public void writeTo(AnnotatedOutput r9) {
        int r0 = this.user.getAddress();
        int r1 = Dops.PACKED_SWITCH.getFormat().codeSize();
        int r2 = this.targets.length;
        int r4 = 0;
        if (this.packed == false) goto L17;
        if (r2 != 0) goto L6;
        int r3 = 0;
    L7:
        if (r2 != 0) goto L9;
        int r22 = 0;
    L10:
        int r23 = (r22 - r3) + 1;
        r9.writeShort(256);
        r9.writeShort(r23);
        r9.writeInt(r3);
        int r5 = 0;
    L11:
        if (r4 >= r23) goto L22;
        if (this.cases.get(r5) <= (r3 + r4)) goto L15;
        int r6 = r1;
    L16:
        r9.writeInt(r6);
        r4 = r4 + 1;
        goto L11
    L15:
        r6 = this.targets[r5].getAddress() - r0;
        r5 = r5 + 1;
        goto L16
    L22:
        return;
    L9:
        r22 = this.cases.get(r2 - 1);
        goto L10
    L6:
        r3 = this.cases.get(0);
        goto L7
    L17:
        r9.writeShort(512);
        r9.writeShort(r2);
        int r12 = 0;
    L18:
        if (r12 >= r2) goto L20;
        r9.writeInt(this.cases.get(r12));
        r12 = r12 + 1;
    L20:
        if (r4 >= r2) goto L28;
        r9.writeInt(this.targets[r4].getAddress() - r0);
        r4 = r4 + 1;
        goto L20
    }
}
