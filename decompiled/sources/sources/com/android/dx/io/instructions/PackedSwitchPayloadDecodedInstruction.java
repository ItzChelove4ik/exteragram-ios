package com.android.dx.io.instructions;

/* loaded from: classes4.dex */
public final class PackedSwitchPayloadDecodedInstruction extends DecodedInstruction {
    private final int firstKey;
    private final int[] targets;

    public PackedSwitchPayloadDecodedInstruction(InstructionCodec r9, int r10, int r11, int[] r12) {
        super(r9, r10, 0, null, 0, 0);
        this.firstKey = r11;
        this.targets = r12;
    }

    public int getFirstKey() {
        return this.firstKey;
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
