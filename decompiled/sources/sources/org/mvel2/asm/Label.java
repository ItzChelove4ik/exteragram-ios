package org.mvel2.asm;

import kotlin.UByte;
import kotlin.UShort;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class Label {
    static final Label EMPTY_LIST = null;
    static final int FLAG_DEBUG_ONLY = 1;
    static final int FLAG_JUMP_TARGET = 2;
    static final int FLAG_LINE_NUMBER = 128;
    static final int FLAG_REACHABLE = 8;
    static final int FLAG_RESOLVED = 4;
    static final int FLAG_SUBROUTINE_CALLER = 16;
    static final int FLAG_SUBROUTINE_END = 64;
    static final int FLAG_SUBROUTINE_START = 32;
    static final int FORWARD_REFERENCES_CAPACITY_INCREMENT = 6;
    static final int FORWARD_REFERENCE_HANDLE_MASK = 268435455;
    static final int FORWARD_REFERENCE_TYPE_MASK = -268435456;
    static final int FORWARD_REFERENCE_TYPE_SHORT = 268435456;
    static final int FORWARD_REFERENCE_TYPE_WIDE = 536870912;
    static final int LINE_NUMBERS_CAPACITY_INCREMENT = 4;
    int bytecodeOffset;
    short flags;
    private int[] forwardReferences;
    Frame frame;
    public Object info;
    short inputStackSize;
    private short lineNumber;
    Label nextBasicBlock;
    Label nextListElement;
    private int[] otherLineNumbers;
    Edge outgoingEdges;
    short outputStackMax;
    short outputStackSize;
    short subroutineId;

    static {
        EMPTY_LIST = new Label();
    }

    public Label() {
    }

    private void addForwardReference(int r7, int r8, int r9) {
        if (this.forwardReferences != null) goto L5;
        this.forwardReferences = new int[6];
    L5:
        int[] r0 = this.forwardReferences;
        int r3 = r0[0];
        if ((r3 + 2) < r0.length) goto L8;
        int[] r1 = new int[r0.length + 6];
        System.arraycopy(r0, 0, r1, 0, r0.length);
        this.forwardReferences = r1;
    L8:
        int[] r6 = this.forwardReferences;
        r6[r3 + 1] = r7;
        int r32 = r3 + 2;
        r6[r32] = r8 | r9;
        r6[0] = r32;
    }

    private Label pushSuccessors(Label r4) {
        Edge r0 = this.outgoingEdges;
    L3:
        if (r0 == null) goto L13;
        if ((this.flags & 16) != 0) goto L7;
    L9:
        Label r1 = r0.successor;
        if (r1.nextListElement != null) goto L12;
        r1.nextListElement = r4;
        r4 = r1;
    L12:
        r0 = r0.nextEdge;
        goto L3
    L7:
        if (r0 != this.outgoingEdges.nextEdge) goto L9;
    L13:
        return r4;
    }

    public final void accept(MethodVisitor r3, boolean r4) {
        r3.visitLabel(this);
        if (r4 == true) goto L5;
        return;
    L5:
        if ((this.flags & 128) == 0) goto L14;
        r3.visitLineNumber(this.lineNumber & UShort.MAX_VALUE, this);
        if (this.otherLineNumbers == null) goto L15;
        int r42 = 1;
    L9:
        int[] r0 = this.otherLineNumbers;
        if (r42 > r0[0]) goto L16;
        r3.visitLineNumber(r0[r42], this);
        r42 = r42 + 1;
        goto L9
    L16:
        return;
    L15:
        return;
    }

    public final void addLineNumber(int r6) {
        short r0 = this.flags;
        if ((r0 & 128) != 0) goto L7;
        this.flags = (short) (r0 | 128);
        this.lineNumber = (short) r6;
        return;
    L7:
        if (this.otherLineNumbers != null) goto L9;
        this.otherLineNumbers = new int[4];
    L9:
        int[] r02 = this.otherLineNumbers;
        int r3 = r02[0] + 1;
        r02[0] = r3;
        if (r3 < r02.length) goto L12;
        int[] r1 = new int[r02.length + 4];
        System.arraycopy(r02, 0, r1, 0, r02.length);
        this.otherLineNumbers = r1;
    L12:
        this.otherLineNumbers[r3] = r6;
    }

    public final void addSubroutineRetSuccessors(Label r7) {
        Label r0 = EMPTY_LIST;
        this.nextListElement = r0;
    L4:
        if (this == EMPTY_LIST) goto L12;
        Label r1 = this.nextListElement;
        this.nextListElement = r0;
        if ((this.flags & 64) == 0) goto L10;
        if (this.subroutineId == r7.subroutineId) goto L10;
        this.outgoingEdges = new Edge(this.outputStackSize, r7.outgoingEdges.successor, this.outgoingEdges);
    L10:
        r0 = this;
        Label this = this.pushSuccessors(r1);
    L12:
        if (r0 == EMPTY_LIST) goto L14;
        Label r6 = r0.nextListElement;
        r0.nextListElement = null;
        r0 = r6;
        goto L12
    }

    public final Label getCanonicalInstance() {
        Frame r0 = this.frame;
        if (r0 != null) goto L6;
        return this;
    L6:
        return r0.owner;
    }

    public int getOffset() {
        if ((this.flags & 4) != 0) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Label offset position has not been resolved yet");
        return 0;
    L5:
        return this.bytecodeOffset;
    }

    public final void markSubroutine(short r3) {
        this.nextListElement = EMPTY_LIST;
    L4:
        if (this == EMPTY_LIST) goto L9;
        Label r0 = this.nextListElement;
        this.nextListElement = null;
        if (this.subroutineId == 0) goto L7;
        Label this = r0;
        goto L4
    L7:
        this.subroutineId = r3;
        this = this.pushSuccessors(r0);
        goto L4
    }

    public final void put(ByteVector r3, int r4, boolean r5) {
        if ((this.flags & 4) == 0) goto L5;
        int r2 = this.bytecodeOffset;
        if (r5 == false) goto L14;
        r3.putInt(r2 - r4);
        return;
    L14:
        r3.putShort(r2 - r4);
        return;
    L5:
        if (r5 == false) goto L8;
        addForwardReference(r4, 536870912, r3.length);
        r3.putInt(-1);
        return;
    L8:
        addForwardReference(r4, 268435456, r3.length);
        r3.putShort(-1);
    }

    public final boolean resolve(byte[] r8, int r9) {
        this.flags = (short) (this.flags | 4);
        this.bytecodeOffset = r9;
        int[] r0 = this.forwardReferences;
        boolean r1 = false;
        if (r0 != null) goto L5;
        return false;
    L5:
        int r02 = r0[0];
    L6:
        if (r02 <= 0) goto L21;
        int[] r2 = this.forwardReferences;
        int r3 = r2[r02 - 1];
        int r22 = r2[r02];
        int r4 = r9 - r3;
        int r5 = FORWARD_REFERENCE_HANDLE_MASK & r22;
        if ((r22 & FORWARD_REFERENCE_TYPE_MASK) == 268435456) goto L10;
        r8[r5] = (byte) (r4 >>> 24);
        r8[r5 + 1] = (byte) (r4 >>> 16);
        r8[r5 + 2] = (byte) (r4 >>> 8);
        r8[r5 + 3] = (byte) r4;
    L20:
        r02 = r02 - 2;
        goto L6
    L10:
        if (r4 >= (-32768)) goto L12;
    L13:
        int r12 = r8[r3] & UByte.MAX_VALUE;
        if (r12 >= 198) goto L16;
        r8[r3] = (byte) (r12 + 49);
    L17:
        r1 = true;
    L18:
        r8[r5] = (byte) (r4 >>> 8);
        r8[r5 + 1] = (byte) r4;
        goto L20
    L16:
        r8[r3] = (byte) (r12 + 20);
        goto L17
    L12:
        if (r4 <= 32767) goto L18;
    L21:
        return r1;
    }

    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}
