package retrofit2;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* loaded from: classes7.dex */
abstract class DefaultMethodSupport {
    private static Constructor<MethodHandles.Lookup> lookupConstructor;

    @IgnoreJRERequirement
    public static Object invoke(Method r3, Class<?> r4, Object r5, Object[] r6) {
        Constructor<MethodHandles.Lookup> r0 = lookupConstructor;
        if (r0 != null) goto L6;
        r0 = DefaultMethodSupport$$ExternalSyntheticApiModelOutline0.m().getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
        r0.setAccessible(true);
        lookupConstructor = r0;
    L6:
        return DefaultMethodSupport$$ExternalSyntheticApiModelOutline1.m(r0.newInstance(new Object[]{r4, -1})).unreflectSpecial(r3, r4).bindTo(r5).invokeWithArguments(r6);
    }
}
