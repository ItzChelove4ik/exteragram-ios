package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class InvokePolymorphicRangeDecodedInstruction extends DecodedInstruction {

    /* renamed from: c, reason: collision with root package name */
    private final int f23c;
    private final int protoIndex;
    private final int registerCount;

    public InvokePolymorphicRangeDecodedInstruction(InstructionCodec r9, int r10, int r11, IndexType r12, int r13, int r14, int r15) {
        super(r9, r10, r11, r12, 0, 0);
        if (r15 != ((short) r15)) goto L6;
        this.f23c = r13;
        this.registerCount = r14;
        this.protoIndex = r15;
        return;
    L6:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("protoIndex doesn't fit in a short: ", r15);
        throw null;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getC() {
        return this.f23c;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public short getProtoIndex() {
        return (short) this.protoIndex;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return this.registerCount;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r1) {
        throw new UnsupportedOperationException("use withProtoIndex to update both the method and proto indices for invoke-polymorphic/range");
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withProtoIndex(int r9, int r10) {
        return new InvokePolymorphicRangeDecodedInstruction(getFormat(), getOpcode(), r9, getIndexType(), this.f23c, this.registerCount, r10);
    }
}
