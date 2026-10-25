package com.android.dx.io.instructions;

import com.android.dx.io.IndexType;

/* loaded from: classes4.dex */
public final class ZeroRegisterDecodedInstruction extends DecodedInstruction {
    public ZeroRegisterDecodedInstruction(InstructionCodec r1, int r2, int r3, IndexType r4, int r5, long r6) {
        super(r1, r2, r3, r4, r5, r6);
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 0;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r9) {
        return new ZeroRegisterDecodedInstruction(getFormat(), getOpcode(), r9, getIndexType(), getTarget(), getLiteral());
    }
}
