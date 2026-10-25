package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

/* loaded from: classes4.dex */
final class zzd extends PathClassLoader {
    public zzd(String r1, ClassLoader r2) {
        super(r1, r2);
    }

    @Override // java.lang.ClassLoader
    public final Class loadClass(String r2, boolean r3) {
        if (r2.startsWith("java.") == true) goto L9;
        if (r2.startsWith("android.") == true) goto L9;
        return findClass(r2);
    L9:
        return super.loadClass(r2, r3);
    }
}
