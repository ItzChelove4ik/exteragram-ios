package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;

/* loaded from: classes4.dex */
public final class ThreeRegisterDecodedInstruction extends DecodedInstruction {

    /* renamed from: a, reason: collision with root package name */
    private final int f26a;

    /* renamed from: b, reason: collision with root package name */
    private final int f27b;

    /* renamed from: c, reason: collision with root package name */
    private final int f28c;

    public ThreeRegisterDecodedInstruction(InstructionCodec r1, int r2, int r3, IndexType r4, int r5, long r6, int r8, int r9, int r10) {
        super(r1, r2, r3, r4, r5, r6);
        this.f26a = r8;
        this.f27b = r9;
        this.f28c = r10;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getA() {
        return this.f26a;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getB() {
        return this.f27b;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getC() {
        return this.f28c;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 3;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r12) {
        return new ThreeRegisterDecodedInstruction(getFormat(), getOpcode(), r12, getIndexType(), getTarget(), getLiteral(), this.f26a, this.f27b, this.f28c);
    }
}
