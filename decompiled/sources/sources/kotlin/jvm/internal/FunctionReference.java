package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

/* loaded from: classes.dex */
public class FunctionReference extends CallableReference implements FunctionBase, KFunction {
    private final int arity;

    public FunctionReference(int r8) {
        this(r8, CallableReference.NO_RECEIVER, null, null, null, 0);
    }

    @SinceKotlin(version = "1.1")
    public FunctionReference(int r8, Object r9) {
        this(r8, r9, null, null, null, 0);
    }

    @SinceKotlin(version = "1.4")
    public FunctionReference(int r8, Object r9, Class r10, String r11, String r12, int r13) {
        boolean r0 = true;
        if ((r13 & 1) == 1) goto L6;
        r0 = false;
    L6:
        super(r9, r10, r11, r12, r0);
        this.arity = r8;
    }

    @Override // kotlin.jvm.internal.CallableReference
    @SinceKotlin(version = "1.1")
    public KCallable computeReflected() {
        return Reflection.function(this);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof FunctionReference) == false) goto L18;
        FunctionReference r52 = (FunctionReference) r5;
        if (getName().equals(r52.getName()) == true) goto L10;
    L16:
        return false;
    L10:
        if (getSignature().equals(r52.getSignature()) == false) goto L16;
        if (Intrinsics.areEqual(getBoundReceiver(), r52.getBoundReceiver()) == false) goto L16;
        if (Intrinsics.areEqual(getOwner(), r52.getOwner()) == false) goto L16;
        return true;
    L18:
        if ((r5 instanceof KFunction) == true) goto L20;
        return false;
    L20:
        return r5.equals(compute());
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    @Override // kotlin.jvm.internal.CallableReference
    @SinceKotlin(version = "1.1")
    public /* bridge */ /* synthetic */ KCallable getReflected() {
        return getReflected();
    }

    @Override // kotlin.jvm.internal.CallableReference
    @SinceKotlin(version = "1.1")
    public KFunction getReflected() {
        return (KFunction) super.getReflected();
    }

    public int hashCode() {
        if (getOwner() != null) goto L5;
        int r0 = 0;
    L7:
        return ((r0 + getName().hashCode()) * 31) + getSignature().hashCode();
    L5:
        r0 = getOwner().hashCode() * 31;
        goto L7
    }

    @Override // kotlin.reflect.KFunction
    @SinceKotlin(version = "1.1")
    public boolean isExternal() {
        return getReflected().isExternal();
    }

    @Override // kotlin.reflect.KFunction
    @SinceKotlin(version = "1.1")
    public boolean isInfix() {
        return getReflected().isInfix();
    }

    @Override // kotlin.reflect.KFunction
    @SinceKotlin(version = "1.1")
    public boolean isInline() {
        return getReflected().isInline();
    }

    @Override // kotlin.reflect.KFunction
    @SinceKotlin(version = "1.1")
    public boolean isOperator() {
        return getReflected().isOperator();
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    @SinceKotlin(version = "1.1")
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }

    public String toString() {
        KCallable r0 = compute();
        if (r0 == this) goto L7;
        return r0.toString();
    L7:
        if ("<init>".equals(getName()) == false) goto L11;
        return "constructor (Kotlin reflection is not available)";
    L11:
        return "function " + getName() + " (Kotlin reflection is not available)";
    }
}
