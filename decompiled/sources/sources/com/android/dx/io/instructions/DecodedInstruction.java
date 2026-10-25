package com.android.dx.io.instructions;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.DexException;
import com.android.dx.io.IndexType;
import com.android.dx.io.OpcodeInfo;
import com.android.dx.io.Opcodes;
import com.android.dx.merge.DexMerger$$ExternalSyntheticBUOutline0;
import com.android.dx.util.Hex;
import java.io.EOFException;

/* loaded from: classes4.dex */
public abstract class DecodedInstruction {
    private final InstructionCodec format;
    private final int index;
    private final IndexType indexType;
    private final long literal;
    private final int opcode;
    private final int target;

    public DecodedInstruction(InstructionCodec r3, int r4, int r5, IndexType r6, int r7, long r8) {
        if (r3 != null) goto L5;
        f$$ExternalSyntheticBUOutline2.m("format == null");
        throw null;
    L5:
        if (Opcodes.isValidShape(r4) == false) goto L8;
        this.format = r3;
        this.opcode = r4;
        this.index = r5;
        this.indexType = r6;
        this.target = r7;
        this.literal = r8;
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("invalid opcode");
        throw null;
    }

    public static DecodedInstruction decode(CodeInput r2) {
        int r0 = r2.read();
        return OpcodeInfo.getFormat(Opcodes.extractOpcodeFromUnit(r0)).decode(r0, r2);
    }

    public static DecodedInstruction[] decodeAll(short[] r3) {
        DecodedInstruction[] r0 = new DecodedInstruction[r3.length];
        ShortArrayCodeInput r1 = new ShortArrayCodeInput(r3);
    L11:
        if (r1.hasMore() == false) goto L7;
        r0[r1.cursor()] = decode(r1);     // Catch: EOFException -> L8
        goto L11
    L7:
        return r0;
    L8:
        e = move-exception;
        throw new DexException(e);
    }

    public final void encode(CodeOutput r2) {
        this.format.encode(this, r2);
    }

    public int getA() {
        return 0;
    }

    public final short getAByte() {
        int r2 = getA();
        if ((r2 & (-256)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register A out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getANibble() {
        int r2 = getA();
        if ((r2 & (-16)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register A out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getAUnit() {
        int r2 = getA();
        if (((-65536) & r2) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register A out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public int getB() {
        return 0;
    }

    public final short getBByte() {
        int r2 = getB();
        if ((r2 & (-256)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register B out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getBNibble() {
        int r2 = getB();
        if ((r2 & (-16)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register B out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getBUnit() {
        int r2 = getB();
        if (((-65536) & r2) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register B out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public int getC() {
        return 0;
    }

    public final short getCByte() {
        int r2 = getC();
        if ((r2 & (-256)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register C out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getCNibble() {
        int r2 = getC();
        if ((r2 & (-16)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register C out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getCUnit() {
        int r2 = getC();
        if (((-65536) & r2) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register C out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public int getD() {
        return 0;
    }

    public final short getDByte() {
        int r2 = getD();
        if ((r2 & (-256)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register D out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getDNibble() {
        int r2 = getD();
        if ((r2 & (-16)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register D out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final short getDUnit() {
        int r2 = getD();
        if (((-65536) & r2) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register D out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public int getE() {
        return 0;
    }

    public final short getENibble() {
        int r2 = getE();
        if ((r2 & (-16)) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register E out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final InstructionCodec getFormat() {
        return this.format;
    }

    public final int getIndex() {
        return this.index;
    }

    public final IndexType getIndexType() {
        return this.indexType;
    }

    public final short getIndexUnit() {
        return (short) this.index;
    }

    public final long getLiteral() {
        return this.literal;
    }

    public final int getLiteralByte() {
        long r0 = this.literal;
        if (r0 == ((byte) r0)) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Literal out of range: ", Hex.u8(this.literal));
        return 0;
    L5:
        return ((int) r0) & 255;
    }

    public final int getLiteralInt() {
        long r0 = this.literal;
        if (r0 == ((int) r0)) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Literal out of range: ", Hex.u8(this.literal));
        return 0;
    L5:
        return (int) r0;
    }

    public final int getLiteralNibble() {
        long r0 = this.literal;
        if (r0 >= (-8)) goto L5;
    L8:
        DexMerger$$ExternalSyntheticBUOutline0.m("Literal out of range: ", Hex.u8(this.literal));
        return 0;
    L5:
        if (r0 > 7) goto L8;
        return ((int) r0) & 15;
    }

    public final short getLiteralUnit() {
        long r0 = this.literal;
        if (r0 == ((short) r0)) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Literal out of range: ", Hex.u8(this.literal));
        return 0;
    L5:
        return (short) r0;
    }

    public final int getOpcode() {
        return this.opcode;
    }

    public final short getOpcodeUnit() {
        return (short) this.opcode;
    }

    public short getProtoIndex() {
        throw new IllegalStateException(getClass().toString());
    }

    public abstract int getRegisterCount();

    public final short getRegisterCountUnit() {
        int r2 = getRegisterCount();
        if (((-65536) & r2) == 0) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Register count out of range: ", Hex.u8(r2));
        return 0;
    L5:
        return (short) r2;
    }

    public final int getTarget() {
        return this.target;
    }

    public final int getTarget(int r1) {
        return this.target - r1;
    }

    public final int getTargetByte(int r1) {
        int r0 = getTarget(r1);
        if (r0 == ((byte) r0)) goto L5;
        DexMerger$$ExternalSyntheticBUOutline0.m("Target out of range: ", Hex.s4(r0));
        return 0;
    L5:
        return r0 & 255;
    }

    public final short getTargetUnit(int r1) {
        int r0 = getTarget(r1);
        short r12 = (short) r0;
        if (r0 != r12) goto L5;
        return r12;
    L5:
        DexMerger$$ExternalSyntheticBUOutline0.m("Target out of range: ", Hex.s4(r0));
        return 0;
    }

    public abstract DecodedInstruction withIndex(int r1);

    public DecodedInstruction withProtoIndex(int r1, int r2) {
        throw new IllegalStateException(getClass().toString());
    }
}
