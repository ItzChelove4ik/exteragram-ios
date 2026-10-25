package org.mvel2.asm.signature;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class SignatureVisitor {
    public static final char EXTENDS = '+';
    public static final char INSTANCEOF = '=';
    public static final char SUPER = '-';
    protected final int api;

    public SignatureVisitor(int r2) {
        if (r2 != 589824) goto L5;
    L19:
        this.api = r2;
        return;
    L5:
        if (r2 == 524288) goto L19;
        if (r2 == 458752) goto L19;
        if (r2 == 393216) goto L19;
        if (r2 == 327680) goto L19;
        if (r2 == 262144) goto L19;
        if (r2 == 17432576) goto L19;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unsupported api ", r2);
        throw null;
    }

    public SignatureVisitor visitArrayType() {
        return this;
    }

    public void visitBaseType(char r1) {
    }

    public SignatureVisitor visitClassBound() {
        return this;
    }

    public void visitClassType(String r1) {
    }

    public void visitEnd() {
    }

    public SignatureVisitor visitExceptionType() {
        return this;
    }

    public void visitFormalTypeParameter(String r1) {
    }

    public void visitInnerClassType(String r1) {
    }

    public SignatureVisitor visitInterface() {
        return this;
    }

    public SignatureVisitor visitInterfaceBound() {
        return this;
    }

    public SignatureVisitor visitParameterType() {
        return this;
    }

    public SignatureVisitor visitReturnType() {
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        return this;
    }

    public SignatureVisitor visitTypeArgument(char r1) {
        return this;
    }

    public void visitTypeArgument() {
    }

    public void visitTypeVariable(String r1) {
    }
}
