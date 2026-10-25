package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeList;
import com.android.dx.util.ToHuman;

/* loaded from: classes.dex */
public abstract class Insn implements ToHuman {
    private final Rop opcode;
    private final SourcePosition position;
    private final RegisterSpec result;
    private final RegisterSpecList sources;

    public static class BaseVisitor implements Visitor {
        public BaseVisitor() {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitFillArrayDataInsn(FillArrayDataInsn r1) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitInvokePolymorphicInsn(InvokePolymorphicInsn r1) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainCstInsn(PlainCstInsn r1) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitPlainInsn(PlainInsn r1) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitSwitchInsn(SwitchInsn r1) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingCstInsn(ThrowingCstInsn r1) {
        }

        @Override // com.android.dx.rop.code.Insn.Visitor
        public void visitThrowingInsn(ThrowingInsn r1) {
        }
    }

    public interface Visitor {
        void visitFillArrayDataInsn(FillArrayDataInsn r1);

        void visitInvokePolymorphicInsn(InvokePolymorphicInsn r1);

        void visitPlainCstInsn(PlainCstInsn r1);

        void visitPlainInsn(PlainInsn r1);

        void visitSwitchInsn(SwitchInsn r1);

        void visitThrowingCstInsn(ThrowingCstInsn r1);

        void visitThrowingInsn(ThrowingInsn r1);
    }

    public Insn(Rop r2, SourcePosition r3, RegisterSpec r4, RegisterSpecList r5) {
        if (r2 == null) goto L12;
        if (r3 == null) goto L10;
        if (r5 == null) goto L8;
        this.opcode = r2;
        this.position = r3;
        this.result = r4;
        this.sources = r5;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("sources == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("position == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("opcode == null");
        throw null;
    }

    private static boolean equalsHandleNulls(Object r0, Object r1) {
        if (r0 == r1) goto L9;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.equals(r1) == true) goto L12;
        return false;
    L12:
        return true;
    L9:
        return true;
    }

    public abstract void accept(Visitor r1);

    public final boolean canThrow() {
        return this.opcode.canThrow();
    }

    public boolean contentEquals(Insn r3) {
        if (this.opcode == r3.getOpcode()) goto L5;
        return false;
    L5:
        if (this.position.equals(r3.getPosition()) == true) goto L7;
        return false;
    L7:
        if (getClass() == r3.getClass()) goto L9;
        return false;
    L9:
        if (equalsHandleNulls(this.result, r3.getResult()) == true) goto L11;
        return false;
    L11:
        if (equalsHandleNulls(this.sources, r3.getSources()) == true) goto L13;
        return false;
    L13:
        if (StdTypeList.equalContents(getCatches(), r3.getCatches()) == false) goto L22;
        return true;
    L22:
        return false;
    }

    public Insn copy() {
        return withRegisterOffset(0);
    }

    public final boolean equals(Object r1) {
        if (this != r1) goto L5;
        return true;
    L5:
        return false;
    }

    public abstract TypeList getCatches();

    public String getInlineString() {
        return null;
    }

    public final RegisterSpec getLocalAssignment() {
        if (this.opcode.getOpcode() != 54) goto L5;
        RegisterSpec r2 = this.sources.get(0);
    L7:
        if (r2 != null) goto L10;
        return null;
    L10:
        if (r2.getLocalItem() != null) goto L12;
        return null;
    L12:
        return r2;
    L5:
        r2 = this.result;
        goto L7
    }

    public final Rop getOpcode() {
        return this.opcode;
    }

    public final SourcePosition getPosition() {
        return this.position;
    }

    public final RegisterSpec getResult() {
        return this.result;
    }

    public final RegisterSpecList getSources() {
        return this.sources;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return toHumanWithInline(getInlineString());
    }

    public final String toHumanWithInline(String r5) {
        StringBuilder r0 = new StringBuilder(80);
        r0.append(this.position);
        r0.append(": ");
        r0.append(this.opcode.getNickname());
        if (r5 == null) goto L6;
        r0.append("(");
        r0.append(r5);
        r0.append(")");
    L6:
        if (this.result != null) goto L8;
        r0.append(" .");
    L9:
        r0.append(" <-");
        int r52 = this.sources.size();
        if (r52 != 0) goto L12;
        r0.append(" .");
    L16:
        return r0.toString();
    L12:
        int r2 = 0;
    L13:
        if (r2 >= r52) goto L16;
        r0.append(" ");
        r0.append(this.sources.get(r2).toHuman());
        r2 = r2 + 1;
        goto L13
    L8:
        r0.append(" ");
        r0.append(this.result.toHuman());
        goto L9
    }

    public String toString() {
        return toStringWithInline(getInlineString());
    }

    public final String toStringWithInline(String r4) {
        StringBuilder r0 = new StringBuilder(80);
        r0.append("Insn{");
        r0.append(this.position);
        r0.append(' ');
        r0.append(this.opcode);
        if (r4 == null) goto L5;
        r0.append(' ');
        r0.append(r4);
    L5:
        r0.append(" :: ");
        RegisterSpec r42 = this.result;
        if (r42 == null) goto L8;
        r0.append(r42);
        r0.append(" <- ");
    L8:
        r0.append(this.sources);
        r0.append('}');
        return r0.toString();
    }

    public abstract Insn withAddedCatch(Type r1);

    public abstract Insn withNewRegisters(RegisterSpec r1, RegisterSpecList r2);

    public abstract Insn withRegisterOffset(int r1);

    public Insn withSourceLiteral() {
        return this;
    }
}
