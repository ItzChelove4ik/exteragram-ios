package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;

/* loaded from: classes4.dex */
public final class OneRegisterDecodedInstruction extends DecodedInstruction {

    /* renamed from: a, reason: collision with root package name */
    private final int f24a;

    public OneRegisterDecodedInstruction(InstructionCodec r1, int r2, int r3, IndexType r4, int r5, long r6, int r8) {
        super(r1, r2, r3, r4, r5, r6);
        this.f24a = r8;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getA() {
        return this.f24a;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 1;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r10) {
        return new OneRegisterDecodedInstruction(getFormat(), getOpcode(), r10, getIndexType(), getTarget(), getLiteral(), this.f24a);
    }
}
