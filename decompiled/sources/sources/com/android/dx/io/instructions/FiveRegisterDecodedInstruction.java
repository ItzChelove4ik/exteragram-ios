package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;

/* loaded from: classes4.dex */
public final class FiveRegisterDecodedInstruction extends DecodedInstruction {

    /* renamed from: a, reason: collision with root package name */
    private final int f17a;

    /* renamed from: b, reason: collision with root package name */
    private final int f18b;

    /* renamed from: c, reason: collision with root package name */
    private final int f19c;
    private final int d;
    private final int e;

    public FiveRegisterDecodedInstruction(InstructionCodec r1, int r2, int r3, IndexType r4, int r5, long r6, int r8, int r9, int r10, int r11, int r12) {
        super(r1, r2, r3, r4, r5, r6);
        this.f17a = r8;
        this.f18b = r9;
        this.f19c = r10;
        this.d = r11;
        this.e = r12;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getA() {
        return this.f17a;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getB() {
        return this.f18b;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getC() {
        return this.f19c;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getD() {
        return this.d;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getE() {
        return this.e;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 5;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r14) {
        return new FiveRegisterDecodedInstruction(getFormat(), getOpcode(), r14, getIndexType(), getTarget(), getLiteral(), this.f17a, this.f18b, this.f19c, this.d, this.e);
    }
}
