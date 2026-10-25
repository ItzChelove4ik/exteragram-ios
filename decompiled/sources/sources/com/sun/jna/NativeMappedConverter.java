package com.sun.jna;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes5.dex */
public class NativeMappedConverter implements TypeConverter {
    private static final Map<Class<?>, Reference<NativeMappedConverter>> converters = null;
    private final NativeMapped instance;
    private final Class<?> nativeType;
    private final Class<?> type;

    static {
        converters = new WeakHashMap();
    }

    public NativeMappedConverter(Class<?> r3) {
        if (NativeMapped.class.isAssignableFrom(r3) == false) goto L6;
        this.type = r3;
        NativeMapped r32 = defaultValue();
        this.instance = r32;
        this.nativeType = r32.nativeType();
        return;
    L6:
        Native$$ExternalSyntheticBUOutline5.m("Type must derive from ", NativeMapped.class);
        throw null;
    }

    public static NativeMappedConverter getInstance(Class<?> r3) {
        Map<Class<?>, Reference<NativeMappedConverter>> r0 = converters;
        monitor-enter(r0);
        Reference<NativeMappedConverter> r1 = r0.get(r3);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        NativeMappedConverter r12 = r1.get();     // Catch: Throwable -> L7
    L10:
        if (r12 != null) goto L12;
        r12 = new NativeMappedConverter(r3);     // Catch: Throwable -> L7
        r0.put(r3, new SoftReference(r12));     // Catch: Throwable -> L7
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r12;
    L9:
        r12 = null;
    L7:
        th = move-exception;
        throw th;
    }

    public NativeMapped defaultValue() {
        boolean r0 = this.type.isEnum();
        Class<?> r1 = this.type;
        if (r0 == false) goto L7;
        return (NativeMapped) r1.getEnumConstants()[0];
    L7:
        return (NativeMapped) Klass.newInstance(r1);
    }

    @Override // com.sun.jna.FromNativeConverter
    public Object fromNative(Object r1, FromNativeContext r2) {
        return this.instance.fromNative(r1, r2);
    }

    @Override // com.sun.jna.FromNativeConverter, com.sun.jna.ToNativeConverter
    public Class<?> nativeType() {
        return this.nativeType;
    }

    @Override // com.sun.jna.ToNativeConverter
    public Object toNative(Object r1, ToNativeContext r2) {
        if (r1 != null) goto L9;
        if (Pointer.class.isAssignableFrom(this.nativeType) == false) goto L7;
        return null;
    L7:
        r1 = defaultValue();
    L9:
        return ((NativeMapped) r1).toNative();
    }
}
