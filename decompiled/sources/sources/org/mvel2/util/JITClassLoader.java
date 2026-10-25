package org.mvel2.util;

/* loaded from: classes5.dex */
public class JITClassLoader extends ClassLoader implements MVELClassLoader {
    public JITClassLoader(ClassLoader r1) {
        super(r1);
    }

    @Override // org.mvel2.util.MVELClassLoader
    public Class<?> defineClassX(String r1, byte[] r2, int r3, int r4) {
        return super.defineClass(r1, r2, r3, r4);
    }
}
