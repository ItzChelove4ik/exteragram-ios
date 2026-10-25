package c;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes.dex */
public abstract class f {
    public static void a(Member r1) {
        if (r1 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("method must not be null");
        return;
    L4:
        if ((r1 instanceof Method) == true) goto L11;
        if ((r1 instanceof Constructor) == true) goto L11;
        f$$ExternalSyntheticBUOutline1.m("method must be a Method or Constructor");
        return;
    L11:
        if (Modifier.isAbstract(f$$ExternalSyntheticApiModelOutline0.m(r1).getModifiers()) == true) goto L13;
        return;
    L13:
        f$$ExternalSyntheticBUOutline1.m("method must not be abstract");
    }
}
