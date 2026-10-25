package com.android.dx.io.instructions;

import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class SparseSwitchPayloadDecodedInstruction extends DecodedInstruction {
    private final int[] keys;
    private final int[] targets;

    public SparseSwitchPayloadDecodedInstruction(InstructionCodec r9, int r10, int[] r11, int[] r12) {
        super(r9, r10, 0, null, 0, 0);
        if (r11.length != r12.length) goto L6;
        this.keys = r11;
        this.targets = r12;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("keys/targets length mismatch");
        throw null;
    }

    public int[] getKeys() {
        return this.keys;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 0;
    }

    public int[] getTargets() {
        return this.targets;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r1) {
        throw new UnsupportedOperationException("no index in instruction");
    }
}
