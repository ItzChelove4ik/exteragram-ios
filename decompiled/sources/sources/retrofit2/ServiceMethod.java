package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* loaded from: classes3.dex */
abstract class ServiceMethod<T> {
    public ServiceMethod() {
    }

    public static <T> ServiceMethod<T> parseAnnotations(Retrofit r2, Class<?> r3, Method r4) {
        RequestFactory r32 = RequestFactory.parseAnnotations(r2, r3, r4);
        Type r0 = r4.getGenericReturnType();
        if (Utils.hasUnresolvableType(r0) == true) goto L11;
        if (r0 == Void.TYPE) goto L9;
        return HttpServiceMethod.parseAnnotations(r2, r4, r32);
    L9:
        throw Utils.methodError(r4, "Service methods cannot return void.", new Object[0]);
    L11:
        throw Utils.methodError(r4, "Method return type must not include a type variable or wildcard: %s", new Object[]{r0});
    }

    public abstract T invoke(Object r1, Object[] r2);
}
