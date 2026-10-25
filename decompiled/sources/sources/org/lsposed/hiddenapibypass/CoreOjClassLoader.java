package org.lsposed.hiddenapibypass;

import dalvik.system.PathClassLoader;
import okhttp3.internal.url._UrlKt;
import org.lsposed.hiddenapibypass.Helper;

/* loaded from: classes.dex */
final class CoreOjClassLoader extends PathClassLoader {
    static {
    }

    public CoreOjClassLoader() {
        super(getCoreOjPath(), null);
    }

    private static String getCoreOjPath() {
        return System.getProperty("java.boot.class.path", _UrlKt.FRAGMENT_ENCODE_SET).split(":", 2)[0];
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String r3) {
        if (Object.class.getName().equals(r3) == false) goto L21;
        return Object.class;
    L21:
        return findClass(r3);
    L8:
        if (CoreOjClassLoader$$ExternalSyntheticApiModelOutline0.m().getName().equals(r3) == false) goto L12;
        return Helper.Executable.class;
    L12:
        if (CoreOjClassLoader$$ExternalSyntheticApiModelOutline1.m().getName().equals(r3) == false) goto L16;
        return Helper.MethodHandle.class;
    L16:
        if (Class.class.getName().equals(r3) == false) goto L20;
        return Helper.Class.class;
    L20:
        return super.loadClass(r3);
    }
}
