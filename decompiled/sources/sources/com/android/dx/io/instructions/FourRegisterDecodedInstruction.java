package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;

/* loaded from: classes4.dex */
public final class FourRegisterDecodedInstruction extends DecodedInstruction {

    /* renamed from: a, reason: collision with root package name */
    private final int f20a;

    /* renamed from: b, reason: collision with root package name */
    private final int f21b;

    /* renamed from: c, reason: collision with root package name */
    private final int f22c;
    private final int d;

    public FourRegisterDecodedInstruction(InstructionCodec r1, int r2, int r3, IndexType r4, int r5, long r6, int r8, int r9, int r10, int r11) {
        super(r1, r2, r3, r4, r5, r6);
        this.f20a = r8;
        this.f21b = r9;
        this.f22c = r10;
        this.d = r11;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getA() {
        return this.f20a;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getB() {
        return this.f21b;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getC() {
        return this.f22c;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getD() {
        return this.d;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 4;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r13) {
        return new FourRegisterDecodedInstruction(getFormat(), getOpcode(), r13, getIndexType(), getTarget(), getLiteral(), this.f20a, this.f21b, this.f22c, this.d);
    }
}
