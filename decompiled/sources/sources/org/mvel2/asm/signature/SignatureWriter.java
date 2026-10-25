package org.mvel2.asm.signature;

import kotlin.text.Typography;
import org.mvel2.asm.Opcodes;

/* loaded from: classes5.dex */
public class SignatureWriter extends SignatureVisitor {
    private int argumentStack;
    private boolean hasFormals;
    private boolean hasParameters;
    private final StringBuilder stringBuilder;

    public SignatureWriter() {
        this(new StringBuilder());
    }

    private SignatureWriter(StringBuilder r2) {
        super(Opcodes.ASM9);
        this.argumentStack = 1;
        this.stringBuilder = r2;
    }

    private void endArguments() {
        if ((this.argumentStack & 1) != 1) goto L5;
        this.stringBuilder.append(Typography.greater);
    L5:
        this.argumentStack >>>= 1;
    }

    private void endFormals() {
        if (this.hasFormals == false) goto L6;
        this.hasFormals = false;
        this.stringBuilder.append(Typography.greater);
        return;
    }

    public String toString() {
        return this.stringBuilder.toString();
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitArrayType() {
        this.stringBuilder.append('[');
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public void visitBaseType(char r1) {
        this.stringBuilder.append(r1);
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitClassBound() {
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public void visitClassType(String r3) {
        this.stringBuilder.append('L');
        this.stringBuilder.append(r3);
        this.argumentStack <<= 1;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public void visitEnd() {
        endArguments();
        this.stringBuilder.append(';');
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitExceptionType() {
        this.stringBuilder.append('^');
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public void visitFormalTypeParameter(String r3) {
        if (this.hasFormals == true) goto L5;
        this.hasFormals = true;
        this.stringBuilder.append(Typography.less);
    L5:
        this.stringBuilder.append(r3);
        this.stringBuilder.append(':');
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public void visitInnerClassType(String r3) {
        endArguments();
        this.stringBuilder.append('.');
        this.stringBuilder.append(r3);
        this.argumentStack <<= 1;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitInterface() {
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitInterfaceBound() {
        this.stringBuilder.append(':');
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitParameterType() {
        endFormals();
        if (this.hasParameters == true) goto L5;
        this.hasParameters = true;
        this.stringBuilder.append('(');
    L5:
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitReturnType() {
        endFormals();
        if (this.hasParameters == true) goto L5;
        this.stringBuilder.append('(');
    L5:
        this.stringBuilder.append(')');
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitSuperclass() {
        endFormals();
        return this;
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public SignatureVisitor visitTypeArgument(char r3) {
        int r0 = this.argumentStack;
        if ((r0 & 1) != 0) goto L6;
        this.argumentStack = r0 | 1;
        this.stringBuilder.append(Typography.less);
    L6:
        if (r3 == '=') goto L9;
        this.stringBuilder.append(r3);
    L9:
        if ((this.argumentStack & Integer.MIN_VALUE) != 0) goto L12;
        return this;
    L12:
        return new SignatureWriter(this.stringBuilder);
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public void visitTypeArgument() {
        int r0 = this.argumentStack;
        if ((r0 & 1) != 0) goto L5;
        this.argumentStack = r0 | 1;
        this.stringBuilder.append(Typography.less);
    L5:
        this.stringBuilder.append('*');
    }

    @Override // org.mvel2.asm.signature.SignatureVisitor
    public void visitTypeVariable(String r3) {
        this.stringBuilder.append('T');
        this.stringBuilder.append(r3);
        this.stringBuilder.append(';');
    }
}
