package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;

@Deprecated(level = DeprecationLevel.ERROR, message = "This class is no longer supported, do not use it.")
@Deprecated
/* loaded from: classes5.dex */
public abstract class FunctionImpl implements Function, Serializable, Function0, Function1, Function2, Function3, Function4, Function5, Function6, Function7, Function8, Function9, Function10, Function11, Function12, Function13, Function14, Function15, Function16, Function17, Function18, Function19, Function20, Function21, Function22 {
    public FunctionImpl() {
    }

    private void checkArity(int r2) {
        if (getArity() == r2) goto L6;
        throwWrongArity(r2);
        return;
    }

    private void throwWrongArity(int r4) {
        throw new IllegalStateException("Wrong function arity, expected: " + r4 + ", actual: " + getArity());
    }

    public abstract int getArity();

    @Override // kotlin.jvm.functions.Function0
    public Object invoke() {
        checkArity(0);
        return invokeVararg(new Object[0]);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object r2) {
        checkArity(1);
        return invokeVararg(new Object[]{r2});
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object r2, Object r3) {
        checkArity(2);
        return invokeVararg(new Object[]{r2, r3});
    }

    @Override // kotlin.jvm.functions.Function3
    public Object invoke(Object r2, Object r3, Object r4) {
        checkArity(3);
        return invokeVararg(new Object[]{r2, r3, r4});
    }

    @Override // kotlin.jvm.functions.Function4
    public Object invoke(Object r2, Object r3, Object r4, Object r5) {
        checkArity(4);
        return invokeVararg(new Object[]{r2, r3, r4, r5});
    }

    @Override // kotlin.jvm.functions.Function5
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6) {
        checkArity(5);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6});
    }

    @Override // kotlin.jvm.functions.Function6
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7) {
        checkArity(6);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7});
    }

    @Override // kotlin.jvm.functions.Function7
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8) {
        checkArity(7);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8});
    }

    @Override // kotlin.jvm.functions.Function8
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9) {
        checkArity(8);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9});
    }

    @Override // kotlin.jvm.functions.Function9
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10) {
        checkArity(9);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10});
    }

    @Override // kotlin.jvm.functions.Function10
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11) {
        checkArity(10);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11});
    }

    @Override // kotlin.jvm.functions.Function11
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12) {
        checkArity(11);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12});
    }

    @Override // kotlin.jvm.functions.Function12
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13) {
        checkArity(12);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13});
    }

    @Override // kotlin.jvm.functions.Function13
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14) {
        checkArity(13);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14});
    }

    @Override // kotlin.jvm.functions.Function14
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15) {
        checkArity(14);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15});
    }

    @Override // kotlin.jvm.functions.Function15
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16) {
        checkArity(15);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16});
    }

    @Override // kotlin.jvm.functions.Function16
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16, Object r17) {
        checkArity(16);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17});
    }

    @Override // kotlin.jvm.functions.Function17
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16, Object r17, Object r18) {
        checkArity(17);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18});
    }

    @Override // kotlin.jvm.functions.Function18
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16, Object r17, Object r18, Object r19) {
        checkArity(18);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19});
    }

    @Override // kotlin.jvm.functions.Function19
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16, Object r17, Object r18, Object r19, Object r20) {
        checkArity(19);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20});
    }

    @Override // kotlin.jvm.functions.Function20
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16, Object r17, Object r18, Object r19, Object r20, Object r21) {
        checkArity(20);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21});
    }

    @Override // kotlin.jvm.functions.Function21
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16, Object r17, Object r18, Object r19, Object r20, Object r21, Object r22) {
        checkArity(21);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22});
    }

    @Override // kotlin.jvm.functions.Function22
    public Object invoke(Object r2, Object r3, Object r4, Object r5, Object r6, Object r7, Object r8, Object r9, Object r10, Object r11, Object r12, Object r13, Object r14, Object r15, Object r16, Object r17, Object r18, Object r19, Object r20, Object r21, Object r22, Object r23) {
        checkArity(22);
        return invokeVararg(new Object[]{r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23});
    }

    public Object invokeVararg(Object... r1) {
        throw new UnsupportedOperationException();
    }
}
