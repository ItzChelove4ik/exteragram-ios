package com.android.dx.dex.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.io.OpcodeInfo;
import com.android.dx.io.Opcodes;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;

/* loaded from: classes.dex */
public final class Dop {
    private final int family;
    private final InsnFormat format;
    private final boolean hasResult;
    private final int nextOpcode;
    private final int opcode;

    public Dop(int r3, int r4, int r5, InsnFormat r6, boolean r7) {
        if (Opcodes.isValidShape(r3) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bogus opcode");
        throw null;
    L5:
        if (Opcodes.isValidShape(r4) == true) goto L7;
        f$$ExternalSyntheticBUOutline1.m("bogus family");
        throw null;
    L7:
        if (Opcodes.isValidShape(r5) == false) goto L13;
        if (r6 == null) goto L11;
        this.opcode = r3;
        this.family = r4;
        this.nextOpcode = r5;
        this.format = r6;
        this.hasResult = r7;
        return;
    L11:
        f$$ExternalSyntheticBUOutline2.m("format == null");
        throw null;
    L13:
        f$$ExternalSyntheticBUOutline1.m("bogus nextOpcode");
        throw null;
    }

    public int getFamily() {
        return this.family;
    }

    public InsnFormat getFormat() {
        return this.format;
    }

    public String getName() {
        return OpcodeInfo.getName(this.opcode);
    }

    public int getNextOpcode() {
        return this.nextOpcode;
    }

    public int getOpcode() {
        return this.opcode;
    }

    public Dop getOppositeTest() {
        switch(this.opcode) {
            case 50: goto L29;
            case 51: goto L27;
            case 52: goto L25;
            case 53: goto L23;
            case 54: goto L21;
            case 55: goto L19;
            case 56: goto L17;
            case 57: goto L15;
            case 58: goto L13;
            case 59: goto L11;
            case 60: goto L9;
            case 61: goto L7;
            default: goto L4;
        };
    L4:
        Native$$ExternalSyntheticBUOutline5.m("bogus opcode: ", this);
        return null;
    L7:
        return Dops.IF_GTZ;
    L9:
        return Dops.IF_LEZ;
    L11:
        return Dops.IF_LTZ;
    L13:
        return Dops.IF_GEZ;
    L15:
        return Dops.IF_EQZ;
    L17:
        return Dops.IF_NEZ;
    L19:
        return Dops.IF_GT;
    L21:
        return Dops.IF_LE;
    L23:
        return Dops.IF_LT;
    L25:
        return Dops.IF_GE;
    L27:
        return Dops.IF_EQ;
    L29:
        return Dops.IF_NE;
    }

    public boolean hasResult() {
        return this.hasResult;
    }

    public String toString() {
        return getName();
    }
}
