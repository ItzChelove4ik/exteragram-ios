package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes5.dex */
public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    @SinceKotlin(version = "1.4")
    public MutablePropertyReference1Impl(Class r7, String r8, String r9, int r10) {
        super(CallableReference.NO_RECEIVER, r7, r8, r9, r10);
    }

    @SinceKotlin(version = "1.4")
    public MutablePropertyReference1Impl(Object r1, Class r2, String r3, String r4, int r5) {
        super(r1, r2, r3, r4, r5);
    }

    public MutablePropertyReference1Impl(KDeclarationContainer r7, String r8, String r9) {
        super(CallableReference.NO_RECEIVER, ((ClassBasedDeclarationContainer) r7).getJClass(), r8, r9, !(r7 instanceof KClass) ? 1 : 0);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object r1) {
        return getGetter().call(new Object[]{r1});
    }

    @Override // kotlin.reflect.KMutableProperty1
    public void set(Object r1, Object r2) {
        getSetter().call(new Object[]{r1, r2});
    }
}
