package com.sun.jna;

import java.lang.reflect.Method;

/* loaded from: classes5.dex */
abstract class VarArgsChecker {

    /* renamed from: com.sun.jna.VarArgsChecker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class NoVarArgsChecker extends VarArgsChecker {
        private NoVarArgsChecker() {
            super(null);
        }

        public /* synthetic */ NoVarArgsChecker(AnonymousClass1 r1) {
            this();
        }

        @Override // com.sun.jna.VarArgsChecker
        public int fixedArgs(Method r1) {
            return 0;
        }

        @Override // com.sun.jna.VarArgsChecker
        public boolean isVarArgs(Method r1) {
            return false;
        }
    }

    public static final class RealVarArgsChecker extends VarArgsChecker {
        private RealVarArgsChecker() {
            super(null);
        }

        public /* synthetic */ RealVarArgsChecker(AnonymousClass1 r1) {
            this();
        }

        @Override // com.sun.jna.VarArgsChecker
        public int fixedArgs(Method r1) {
            if (r1.isVarArgs() == true) goto L5;
            return 0;
        L5:
            return r1.getParameterTypes().length - 1;
        }

        @Override // com.sun.jna.VarArgsChecker
        public boolean isVarArgs(Method r1) {
            return r1.isVarArgs();
        }
    }

    private VarArgsChecker() {
    }

    public /* synthetic */ VarArgsChecker(AnonymousClass1 r1) {
        this();
    }

    public static VarArgsChecker create() {
        if (Method.class.getMethod("isVarArgs", null) == null) goto L8;
        return new RealVarArgsChecker(null);
    L8:
        return new NoVarArgsChecker(null);
    L10:
        return new NoVarArgsChecker(null);
    }

    public abstract int fixedArgs(Method r1);

    public abstract boolean isVarArgs(Method r1);
}
