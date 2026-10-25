package com.android.dx.io.instructions;

/* loaded from: classes4.dex */
public final class FillArrayDataPayloadDecodedInstruction extends DecodedInstruction {
    private final Object data;
    private final int elementWidth;
    private final int size;

    private FillArrayDataPayloadDecodedInstruction(InstructionCodec r9, int r10, Object r11, int r12, int r13) {
        super(r9, r10, 0, null, 0, 0);
        this.data = r11;
        this.size = r12;
        this.elementWidth = r13;
    }

    public FillArrayDataPayloadDecodedInstruction(InstructionCodec r7, int r8, byte[] r9) {
        this(r7, r8, r9, r9.length, 1);
    }

    public FillArrayDataPayloadDecodedInstruction(InstructionCodec r7, int r8, int[] r9) {
        this(r7, r8, r9, r9.length, 4);
    }

    public FillArrayDataPayloadDecodedInstruction(InstructionCodec r7, int r8, long[] r9) {
        this(r7, r8, r9, r9.length, 8);
    }

    public FillArrayDataPayloadDecodedInstruction(InstructionCodec r7, int r8, short[] r9) {
        this(r7, r8, r9, r9.length, 2);
    }

    public Object getData() {
        return this.data;
    }

    public short getElementWidthUnit() {
        return (short) this.elementWidth;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public int getRegisterCount() {
        return 0;
    }

    public int getSize() {
        return this.size;
    }

    @Override // com.android.dx.io.instructions.DecodedInstruction
    public DecodedInstruction withIndex(int r1) {
        throw new UnsupportedOperationException("no index in instruction");
    }
}
