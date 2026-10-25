package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes.dex */
public class MutablePropertyReference0Impl extends MutablePropertyReference0 {
    @SinceKotlin(version = "1.4")
    public MutablePropertyReference0Impl(Class r7, String r8, String r9, int r10) {
        super(CallableReference.NO_RECEIVER, r7, r8, r9, r10);
    }

    @SinceKotlin(version = "1.4")
    public MutablePropertyReference0Impl(Object r1, Class r2, String r3, String r4, int r5) {
        super(r1, r2, r3, r4, r5);
    }

    public MutablePropertyReference0Impl(KDeclarationContainer r7, String r8, String r9) {
        super(CallableReference.NO_RECEIVER, ((ClassBasedDeclarationContainer) r7).getJClass(), r8, r9, !(r7 instanceof KClass) ? 1 : 0);
    }

    public Object get() {
        return getGetter().call(new Object[0]);
    }

    public void set(Object r1) {
        getSetter().call(new Object[]{r1});
    }
}
