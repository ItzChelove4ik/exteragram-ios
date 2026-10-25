package androidx.core.graphics;

import android.graphics.Typeface;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl {
    public TypefaceCompatApi28Impl() {
    }

    @Override // androidx.core.graphics.TypefaceCompatApi26Impl
    public Typeface createFromFamiliesWithDefault(Object r5) {
        Object r2 = Array.newInstance(this.mFontFamily, 1);     // Catch: InvocationTargetException -> L5 Throwable -> L7
        Array.set(r2, 0, r5);     // Catch: InvocationTargetException -> L5 Throwable -> L7
        return (Typeface) this.mCreateFromFamiliesWithDefault.invoke(null, new Object[]{r2, "sans-serif", -1, -1});
    L7:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @Override // androidx.core.graphics.TypefaceCompatApi26Impl
    public Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> r3) {
        Class<?> r32 = Array.newInstance(r3, 1).getClass();
        Class r1 = Integer.TYPE;
        Method r33 = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[]{r32, String.class, r1, r1});
        r33.setAccessible(true);
        return r33;
    }
}
