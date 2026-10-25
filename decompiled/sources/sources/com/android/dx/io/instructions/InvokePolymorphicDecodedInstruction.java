package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class InvokePolymorphicDecodedInstruction extends DecodedInstruction {
    private final int protoIndex;
    private final int[] registers;

    public InvokePolymorphicDecodedInstruction(InstructionCodec r9, int r10, int r11, IndexType r12, int r13, int[] r14) {
        super(r9, r10, r11, r12, 0, 0);
        if (r13 != ((short) r13)) goto L6;
        this.protoIndex = r13;
        this.registers = r14;
        return;
    L6:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("protoIndex doesn't fit in a short: ", r13);
        throw null;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getC() {
        int[] r2 = this.registers;
        if (r2.length > 0) goto L5;
        return 0;
    L5:
        return r2[0];
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getD() {
        int[] r2 = this.registers;
        if (r2.length > 1) goto L5;
        return 0;
    L5:
        return r2[1];
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getE() {
        int[] r2 = this.registers;
        if (r2.length > 2) goto L5;
        return 0;
    L5:
        return r2[2];
    }

    public int getF() {
        int[] r2 = this.registers;
        if (r2.length > 3) goto L5;
        return 0;
    L5:
        return r2[3];
    }

    public int getG() {
        int[] r2 = this.registers;
        if (r2.length > 4) goto L5;
        return 0;
    L5:
        return r2[4];
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public short getProtoIndex() {
        return (short) this.protoIndex;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return this.registers.length;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r1) {
        throw new UnsupportedOperationException("use withProtoIndex to update both the method and proto indices for invoke-polymorphic");
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withProtoIndex(int r8, int r9) {
        return new InvokePolymorphicDecodedInstruction(getFormat(), getOpcode(), r8, getIndexType(), r9, this.registers);
    }
}
