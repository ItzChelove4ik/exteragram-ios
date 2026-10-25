package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.Hex;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class Rop {
    public static final int BRANCH_GOTO = 3;
    public static final int BRANCH_IF = 4;
    public static final int BRANCH_MAX = 6;
    public static final int BRANCH_MIN = 1;
    public static final int BRANCH_NONE = 1;
    public static final int BRANCH_RETURN = 2;
    public static final int BRANCH_SWITCH = 5;
    public static final int BRANCH_THROW = 6;
    private final int branchingness;
    private final TypeList exceptions;
    private final boolean isCallLike;
    private final String nickname;
    private final int opcode;
    private final Type result;
    private final TypeList sources;

    public Rop(int r9, Type r10, TypeList r11, int r12, String r13) {
        this(r9, r10, r11, StdTypeList.EMPTY, r12, false, r13);
    }

    public Rop(int r9, Type r10, TypeList r11, TypeList r12, int r13, String r14) {
        this(r9, r10, r11, r12, r13, false, r14);
    }

    public Rop(int r4, Type r5, TypeList r6, TypeList r7, int r8, boolean r9, String r10) {
        if (r5 == null) goto L24;
        if (r6 == null) goto L22;
        if (r7 != null) goto L7;
        f$$ExternalSyntheticBUOutline2.m("exceptions == null");
        throw null;
    L7:
        if (r8 >= 1) goto L9;
    L18:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("invalid branchingness: ", r8);
        throw null;
    L9:
        if (r8 > 6) goto L18;
        if (r7.size() == 0) goto L16;
        if (r8 == 6) goto L16;
        f$$ExternalSyntheticBUOutline1.m("exceptions / branchingness mismatch");
        throw null;
    L16:
        this.opcode = r4;
        this.result = r5;
        this.sources = r6;
        this.exceptions = r7;
        this.branchingness = r8;
        this.isCallLike = r9;
        this.nickname = r10;
        return;
    L22:
        f$$ExternalSyntheticBUOutline2.m("sources == null");
        throw null;
    L24:
        f$$ExternalSyntheticBUOutline2.m("result == null");
        throw null;
    }

    public Rop(int r9, Type r10, TypeList r11, TypeList r12, String r13) {
        this(r9, r10, r11, r12, 6, false, r13);
    }

    public Rop(int r9, Type r10, TypeList r11, String r12) {
        this(r9, r10, r11, StdTypeList.EMPTY, 1, false, r12);
    }

    public Rop(int r9, TypeList r10, TypeList r11) {
        this(r9, Type.VOID, r10, r11, 6, true, null);
    }

    public final boolean canThrow() {
        if (this.exceptions.size() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Rop) == true) goto L8;
        return false;
    L8:
        Rop r52 = (Rop) r5;
        if (this.opcode == r52.opcode) goto L11;
    L19:
        return false;
    L11:
        if (this.branchingness != r52.branchingness) goto L19;
        if (this.result != r52.result) goto L19;
        if (this.sources.equals(r52.sources) == false) goto L19;
        if (this.exceptions.equals(r52.exceptions) == false) goto L19;
        return true;
    }

    public int getBranchingness() {
        return this.branchingness;
    }

    public TypeList getExceptions() {
        return this.exceptions;
    }

    public String getNickname() {
        String r0 = this.nickname;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return toString();
    }

    public int getOpcode() {
        return this.opcode;
    }

    public Type getResult() {
        return this.result;
    }

    public TypeList getSources() {
        return this.sources;
    }

    public int hashCode() {
        return (((((((this.opcode * 31) + this.branchingness) * 31) + this.result.hashCode()) * 31) + this.sources.hashCode()) * 31) + this.exceptions.hashCode();
    }

    public boolean isCallLike() {
        return this.isCallLike;
    }

    public boolean isCommutative() {
        int r1 = this.opcode;
        if (r1 != 14) goto L5;
        return true;
    L5:
        if (r1 == 16) goto L11;
        switch(r1) {
            case 20: goto L12;
            case 21: goto L12;
            case 22: goto L12;
            default: goto L7;
        };
    L7:
        return false;
    L12:
        return true;
    L11:
        return true;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(40);
        r0.append("Rop{");
        r0.append(RegOps.opName(this.opcode));
        if (this.result == Type.VOID) goto L5;
        r0.append(" ");
        r0.append(this.result);
    L6:
        r0.append(" <-");
        int r1 = this.sources.size();
        int r5 = 0;
        if (r1 != 0) goto L9;
        r0.append(" .");
    L13:
        if (this.isCallLike == false) goto L15;
        r0.append(" call");
    L15:
        int r12 = this.exceptions.size();
        if (r12 == 0) goto L24;
        r0.append(" throws");
    L18:
        if (r5 >= r12) goto L40;
        r0.append(' ');
        if (this.exceptions.getType(r5) != Type.THROWABLE) goto L22;
        r0.append("<any>");
    L23:
        r5 = r5 + 1;
        goto L18
    L22:
        r0.append(this.exceptions.getType(r5));
    L40:
        r0.append('}');
        return r0.toString();
    L24:
        int r13 = this.branchingness;
        if (r13 != 1) goto L27;
        r0.append(" flows");
        goto L40
    L27:
        if (r13 != 2) goto L29;
        r0.append(" returns");
        goto L40
    L29:
        if (r13 != 3) goto L31;
        r0.append(" gotos");
        goto L40
    L31:
        if (r13 != 4) goto L33;
        r0.append(" ifs");
        goto L40
    L33:
        if (r13 == 5) goto L35;
        r0.append(" " + Hex.u1(this.branchingness));
        goto L40
    L35:
        r0.append(" switches");
        goto L40
    L9:
        int r3 = 0;
    L10:
        if (r3 >= r1) goto L13;
        r0.append(' ');
        r0.append(this.sources.getType(r3));
        r3 = r3 + 1;
        goto L10
    L5:
        r0.append(" .");
        goto L6
    }
}
