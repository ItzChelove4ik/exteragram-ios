package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public abstract class PropertyReference extends CallableReference implements KProperty {
    private final boolean syntheticJavaProperty;

    public PropertyReference() {
        this.syntheticJavaProperty = false;
    }

    @SinceKotlin(version = "1.1")
    public PropertyReference(Object r1) {
        super(r1);
        this.syntheticJavaProperty = false;
    }

    @SinceKotlin(version = "1.4")
    public PropertyReference(Object r10, Class r11, String r12, String r13, int r14) {
        boolean r1 = false;
        if ((r14 & 1) != 1) goto L6;
        boolean r8 = true;
    L7:
        super(r10, r11, r12, r13, r8);
        if ((r14 & 2) != 2) goto L10;
        r1 = true;
    L10:
        this.syntheticJavaProperty = r1;
        return;
    L6:
        r8 = false;
        goto L7
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KCallable compute() {
        if (this.syntheticJavaProperty == false) goto L6;
        return this;
    L6:
        return super.compute();
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof PropertyReference) == false) goto L18;
        PropertyReference r52 = (PropertyReference) r5;
        if (getOwner().equals(r52.getOwner()) == true) goto L10;
    L16:
        return false;
    L10:
        if (getName().equals(r52.getName()) == false) goto L16;
        if (getSignature().equals(r52.getSignature()) == false) goto L16;
        if (Intrinsics.areEqual(getBoundReceiver(), r52.getBoundReceiver()) == false) goto L16;
        return true;
    L18:
        if ((r5 instanceof KProperty) == true) goto L20;
        return false;
    L20:
        return r5.equals(compute());
    }

    @Override // kotlin.jvm.internal.CallableReference
    @SinceKotlin(version = "1.1")
    public /* bridge */ /* synthetic */ KCallable getReflected() {
        return getReflected();
    }

    @Override // kotlin.jvm.internal.CallableReference
    @SinceKotlin(version = "1.1")
    public KProperty getReflected() {
        if (this.syntheticJavaProperty == false) goto L5;
        ByteString$$ExternalSyntheticBUOutline0.m("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        return null;
    L5:
        return (KProperty) super.getReflected();
    }

    public int hashCode() {
        return (((getOwner().hashCode() * 31) + getName().hashCode()) * 31) + getSignature().hashCode();
    }

    @Override // kotlin.reflect.KProperty
    @SinceKotlin(version = "1.1")
    public boolean isConst() {
        return getReflected().isConst();
    }

    @Override // kotlin.reflect.KProperty
    @SinceKotlin(version = "1.1")
    public boolean isLateinit() {
        return getReflected().isLateinit();
    }

    public String toString() {
        KCallable r0 = compute();
        if (r0 == this) goto L7;
        return r0.toString();
    L7:
        return "property " + getName() + " (Kotlin reflection is not available)";
    }
}
