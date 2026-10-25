package retrofit2;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class Invocation {
    private final List<?> arguments;
    private final Object instance;
    private final Method method;
    private final Class<?> service;

    public Invocation(Class<?> r1, Object r2, Method r3, List<?> r4) {
        this.service = r1;
        this.instance = r2;
        this.method = r3;
        this.arguments = Collections.unmodifiableList(r4);
    }

    public Method method() {
        return this.method;
    }

    public Class<?> service() {
        return this.service;
    }

    public String toString() {
        return String.format("%s.%s() %s", new Object[]{this.service.getName(), this.method.getName(), this.arguments});
    }
}
