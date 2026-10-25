package com.android.dex;

/* loaded from: classes4.dex */
public final class Code {
    private final CatchHandler[] catchHandlers;
    private final int debugInfoOffset;
    private final int insSize;
    private final short[] instructions;
    private final int outsSize;
    private final int registersSize;
    private final Try[] tries;

    public static class CatchHandler {
        final int[] addresses;
        final int catchAllAddress;
        final int offset;
        final int[] typeIndexes;

        public CatchHandler(int[] r1, int[] r2, int r3, int r4) {
            this.typeIndexes = r1;
            this.addresses = r2;
            this.catchAllAddress = r3;
            this.offset = r4;
        }

        public int[] getAddresses() {
            return this.addresses;
        }

        public int getCatchAllAddress() {
            return this.catchAllAddress;
        }

        public int getOffset() {
            return this.offset;
        }

        public int[] getTypeIndexes() {
            return this.typeIndexes;
        }
    }

    public static class Try {
        final int catchHandlerIndex;
        final int instructionCount;
        final int startAddress;

        public Try(int r1, int r2, int r3) {
            this.startAddress = r1;
            this.instructionCount = r2;
            this.catchHandlerIndex = r3;
        }

        public int getCatchHandlerIndex() {
            return this.catchHandlerIndex;
        }

        public int getInstructionCount() {
            return this.instructionCount;
        }

        public int getStartAddress() {
            return this.startAddress;
        }
    }

    public Code(int r1, int r2, int r3, int r4, short[] r5, Try[] r6, CatchHandler[] r7) {
        this.registersSize = r1;
        this.insSize = r2;
        this.outsSize = r3;
        this.debugInfoOffset = r4;
        this.instructions = r5;
        this.tries = r6;
        this.catchHandlers = r7;
    }

    public CatchHandler[] getCatchHandlers() {
        return this.catchHandlers;
    }

    public int getDebugInfoOffset() {
        return this.debugInfoOffset;
    }

    public int getInsSize() {
        return this.insSize;
    }

    public short[] getInstructions() {
        return this.instructions;
    }

    public int getOutsSize() {
        return this.outsSize;
    }

    public int getRegistersSize() {
        return this.registersSize;
    }

    public Try[] getTries() {
        return this.tries;
    }
}
