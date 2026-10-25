package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes.dex */
public class PropertyReference2Impl extends PropertyReference2 {
    @SinceKotlin(version = "1.4")
    public PropertyReference2Impl(Class r1, String r2, String r3, int r4) {
        super(r1, r2, r3, r4);
    }

    public PropertyReference2Impl(KDeclarationContainer r2, String r3, String r4) {
        super(((ClassBasedDeclarationContainer) r2).getJClass(), r3, r4, !(r2 instanceof KClass) ? 1 : 0);
    }

    @Override // kotlin.reflect.KProperty2
    public Object get(Object r1, Object r2) {
        return getGetter().call(new Object[]{r1, r2});
    }
}
