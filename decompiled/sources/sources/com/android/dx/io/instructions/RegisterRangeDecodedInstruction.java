package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;

/* loaded from: classes4.dex */
public final class RegisterRangeDecodedInstruction extends DecodedInstruction {

    /* renamed from: a, reason: collision with root package name */
    private final int f25a;
    private final int registerCount;

    public RegisterRangeDecodedInstruction(InstructionCodec r1, int r2, int r3, IndexType r4, int r5, long r6, int r8, int r9) {
        super(r1, r2, r3, r4, r5, r6);
        this.f25a = r8;
        this.registerCount = r9;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getA() {
        return this.f25a;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return this.registerCount;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r11) {
        return new RegisterRangeDecodedInstruction(getFormat(), getOpcode(), r11, getIndexType(), getTarget(), getLiteral(), this.f25a, this.registerCount);
    }
}
