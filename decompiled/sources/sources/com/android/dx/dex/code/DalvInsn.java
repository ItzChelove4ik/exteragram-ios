package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.ssa.RegisterMapper;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import com.android.dx.util.TwoColumnOutput;
import java.util.BitSet;
import okhttp3.internal.url._UrlKt;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class DalvInsn {
    private int address;
    private final Dop opcode;
    private final SourcePosition position;
    private final RegisterSpecList registers;

    public DalvInsn(Dop r2, SourcePosition r3, RegisterSpecList r4) {
        if (r2 == null) goto L12;
        if (r3 == null) goto L10;
        if (r4 == null) goto L8;
        this.address = -1;
        this.opcode = r2;
        this.position = r3;
        this.registers = r4;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("registers == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("position == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("opcode == null");
        throw null;
    }

    public static SimpleInsn makeMove(SourcePosition r5, RegisterSpec r6, RegisterSpec r7) {
        boolean r1 = true;
        if (r6.getCategory() == 1) goto L6;
        r1 = false;
    L6:
        boolean r0 = r6.getType().isReference();
        int r2 = r6.getReg();
        if ((r7.getReg() | r2) >= 16) goto L14;
        if (r0 == false) goto L10;
        Dop r02 = Dops.MOVE_OBJECT;
    L26:
        return new SimpleInsn(r02, r5, RegisterSpecList.make(r6, r7));
    L10:
        if (r1 == false) goto L12;
        r02 = Dops.MOVE;
        goto L26
    L12:
        r02 = Dops.MOVE_WIDE;
        goto L26
    L14:
        if (r2 >= 256) goto L20;
        if (r0 == false) goto L17;
        r02 = Dops.MOVE_OBJECT_FROM16;
        goto L26
    L17:
        if (r1 == false) goto L19;
        r02 = Dops.MOVE_FROM16;
        goto L26
    L19:
        r02 = Dops.MOVE_WIDE_FROM16;
        goto L26
    L20:
        if (r0 == false) goto L22;
        r02 = Dops.MOVE_OBJECT_16;
        goto L26
    L22:
        if (r1 == false) goto L24;
        r02 = Dops.MOVE_16;
        goto L26
    L24:
        r02 = Dops.MOVE_WIDE_16;
        goto L26
    }

    public abstract String argString();

    public abstract int codeSize();

    public String cstComment() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public String cstString() {
        throw new UnsupportedOperationException("Not supported.");
    }

    public DalvInsn expandedPrefix(BitSet r5) {
        RegisterSpecList r0 = this.registers;
        boolean r2 = r5.get(0);
        if (hasResult() == false) goto L5;
        r5.set(0);
    L5:
        RegisterSpecList r02 = r0.subset(r5);
        if (hasResult() == false) goto L9;
        r5.set(0, r2);
    L9:
        if (r02.size() != 0) goto L13;
        return null;
    L13:
        return new HighRegisterPrefix(this.position, r02);
    }

    public DalvInsn expandedSuffix(BitSet r2) {
        if (hasResult() == true) goto L5;
        return null;
    L5:
        if (r2.get(0) == true) goto L10;
        RegisterSpec r22 = this.registers.get(0);
        return makeMove(this.position, r22, r22.withReg(0));
    L10:
        return null;
    }

    public DalvInsn expandedVersion(BitSet r4) {
        return withRegisters(this.registers.withExpandedRegisters(0, hasResult(), r4));
    }

    public final int getAddress() {
        int r0 = this.address;
        if (r0 < 0) goto L5;
        return r0;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("address not yet known");
        return 0;
    }

    public DalvInsn getLowRegVersion() {
        return withRegisters(this.registers.withExpandedRegisters(0, hasResult(), null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.android.dx.rop.code.RegisterSpecList] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.BitSet] */
    public final int getMinimumRegisterRequirement(BitSet r6) {
        ?? r0 = hasResult();
        int r1 = this.registers.size();
        int r2 = 0;
        if (r0 != 0) goto L5;
    L7:
        int r3 = 0;
    L8:
        if (r0 >= r1) goto L14;
        if (r6.get(r0) == true) goto L12;
        r2 = r2 + this.registers.get(r0).getCategory();
    L12:
        r0 = r0 + 1;
        goto L8
    L14:
        return Math.max(r2, r3);
    L5:
        if (r6.get(0) == true) goto L7;
        r3 = this.registers.get(0).getCategory();
        goto L8
    }

    public final int getNextAddress() {
        return getAddress() + codeSize();
    }

    public final Dop getOpcode() {
        return this.opcode;
    }

    public final SourcePosition getPosition() {
        return this.position;
    }

    public final RegisterSpecList getRegisters() {
        return this.registers;
    }

    public final boolean hasAddress() {
        if (this.address < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean hasResult() {
        return this.opcode.hasResult();
    }

    public final String identifierString() {
        int r0 = this.address;
        if (r0 == (-1)) goto L7;
        return String.format("%04x", new Object[]{Integer.valueOf(r0)});
    L7:
        return Hex.u4(System.identityHashCode(this));
    }

    public final String listingString(String r2, int r3, boolean r4) {
        String r42 = listingString0(r4);
        if (r42 != null) goto L6;
        return null;
    L6:
        String r1 = r2 + identifierString() + ": ";
        int r22 = r1.length();
        if (r3 != 0) goto L9;
        int r32 = r42.length();
    L11:
        return TwoColumnOutput.toString(r1, r22, _UrlKt.FRAGMENT_ENCODE_SET, r42, r32);
    L9:
        r32 = r3 - r22;
        goto L11
    }

    public abstract String listingString0(boolean r1);

    public final void setAddress(int r1) {
        if (r1 < 0) goto L5;
        this.address = r1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("address < 0");
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(100);
        r0.append(identifierString());
        r0.append(' ');
        r0.append(this.position);
        r0.append(": ");
        r0.append(this.opcode.getName());
        if (this.registers.size() == 0) goto L5;
        r0.append(this.registers.toHuman(" ", ", ", null));
        boolean r2 = true;
    L6:
        String r6 = argString();
        if (r6 == null) goto L12;
        if (r2 == false) goto L10;
        r0.append(',');
    L10:
        r0.append(' ');
        r0.append(r6);
    L12:
        return r0.toString();
    L5:
        r2 = false;
        goto L6
    }

    public DalvInsn withMapper(RegisterMapper r2) {
        return withRegisters(r2.map(getRegisters()));
    }

    public abstract DalvInsn withOpcode(Dop r1);

    public abstract DalvInsn withRegisterOffset(int r1);

    public abstract DalvInsn withRegisters(RegisterSpecList r1);

    public abstract void writeTo(AnnotatedOutput r1);
}
