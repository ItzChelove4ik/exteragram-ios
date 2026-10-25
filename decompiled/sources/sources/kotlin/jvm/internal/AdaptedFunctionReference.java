package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.SinceKotlin;
import kotlin.reflect.KDeclarationContainer;

@SinceKotlin(version = "1.4")
/* loaded from: classes5.dex */
public class AdaptedFunctionReference implements FunctionBase, Serializable {
    private final int arity;
    private final int flags;
    private final boolean isTopLevel;
    private final String name;
    private final Class owner;
    protected final Object receiver;
    private final String signature;

    public AdaptedFunctionReference(int r8, Class r9, String r10, String r11, int r12) {
        this(r8, CallableReference.NO_RECEIVER, r9, r10, r11, r12);
    }

    public AdaptedFunctionReference(int r1, Object r2, Class r3, String r4, String r5, int r6) {
        this.receiver = r2;
        this.owner = r3;
        this.name = r4;
        this.signature = r5;
        if ((r6 & 1) != 1) goto L5;
        boolean r22 = true;
    L6:
        this.isTopLevel = r22;
        this.arity = r1;
        this.flags = r6 >> 1;
        return;
    L5:
        r22 = false;
        goto L6
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof AdaptedFunctionReference) == true) goto L8;
        return false;
    L8:
        AdaptedFunctionReference r52 = (AdaptedFunctionReference) r5;
        if (this.isTopLevel == r52.isTopLevel) goto L11;
    L23:
        return false;
    L11:
        if (this.arity != r52.arity) goto L23;
        if (this.flags != r52.flags) goto L23;
        if (Intrinsics.areEqual(this.receiver, r52.receiver) == false) goto L23;
        if (Intrinsics.areEqual(this.owner, r52.owner) == false) goto L23;
        if (this.name.equals(r52.name) == false) goto L23;
        if (this.signature.equals(r52.signature) == false) goto L23;
        return true;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    public KDeclarationContainer getOwner() {
        Class r0 = this.owner;
        if (r0 != null) goto L7;
        return null;
    L7:
        if (this.isTopLevel == false) goto L11;
        return Reflection.getOrCreateKotlinPackage(r0);
    L11:
        return Reflection.getOrCreateKotlinClass(r0);
    }

    public int hashCode() {
        Object r0 = this.receiver;
        int r1 = 0;
        if (r0 == null) goto L5;
        int r02 = r0.hashCode();
    L6:
        int r03 = r02 * 31;
        Class r2 = this.owner;
        if (r2 == null) goto L9;
        r1 = r2.hashCode();
    L9:
        int r04 = (((((r03 + r1) * 31) + this.name.hashCode()) * 31) + this.signature.hashCode()) * 31;
        if (this.isTopLevel == false) goto L12;
        int r12 = 1231;
    L14:
        return ((((r04 + r12) * 31) + this.arity) * 31) + this.flags;
    L12:
        r12 = 1237;
        goto L14
    L5:
        r02 = 0;
        goto L6
    }

    public String toString() {
        return Reflection.renderLambdaToString(this);
    }
}
