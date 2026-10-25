package org.aspectj.runtime.reflect;

import java.lang.ref.SoftReference;
import java.util.StringTokenizer;
import org.aspectj.lang.Signature;

/* loaded from: classes5.dex */
abstract class SignatureImpl implements Signature {
    static Class[] EMPTY_CLASS_ARRAY = null;
    static String[] EMPTY_STRING_ARRAY = null;
    private static boolean useCache = true;
    Class declaringType;
    String declaringTypeName;
    ClassLoader lookupClassLoader;
    int modifiers;
    String name;
    Cache stringCache;
    private String stringRep;

    public interface Cache {
        String get(int r1);

        void set(int r1, String r2);
    }

    public static final class CacheImpl implements Cache {
        private SoftReference toStringCacheRef;

        public CacheImpl() {
            makeCache();
        }

        private String[] array() {
            return (String[]) this.toStringCacheRef.get();
        }

        private String[] makeCache() {
            String[] r0 = new String[3];
            this.toStringCacheRef = new SoftReference(r0);
            return r0;
        }

        @Override // org.aspectj.runtime.reflect.SignatureImpl.Cache
        public String get(int r1) {
            String[] r0 = array();
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0[r1];
        }

        @Override // org.aspectj.runtime.reflect.SignatureImpl.Cache
        public void set(int r2, String r3) {
            String[] r0 = array();
            if (r0 != null) goto L5;
            r0 = makeCache();
        L5:
            r0[r2] = r3;
        }
    }

    static {
        EMPTY_STRING_ARRAY = new String[0];
        EMPTY_CLASS_ARRAY = new Class[0];
    }

    public SignatureImpl(int r2, String r3, Class r4) {
        this.lookupClassLoader = null;
        this.modifiers = r2;
        this.name = r3;
        this.declaringType = r4;
    }

    private ClassLoader getLookupClassLoader() {
        if (this.lookupClassLoader != null) goto L6;
        this.lookupClassLoader = getClass().getClassLoader();
    L6:
        return this.lookupClassLoader;
    }

    public abstract String createToString(StringMaker r1);

    public int extractInt(int r1) {
        return Integer.parseInt(extractString(r1), 16);
    }

    public String extractString(int r5) {
        int r0 = this.stringRep.indexOf(45);
        int r2 = 0;
    L3:
        int r3 = r5 - 1;
        if (r5 <= 0) goto L7;
        r2 = r0 + 1;
        r0 = this.stringRep.indexOf(45, r2);
        r5 = r3;
        goto L3
    L7:
        if (r0 != (-1)) goto L10;
        r0 = this.stringRep.length();
    L10:
        return this.stringRep.substring(r2, r0);
    }

    public Class extractType(int r1) {
        return Factory.makeClass(extractString(r1), getLookupClassLoader());
    }

    public Class[] extractTypes(int r6) {
        StringTokenizer r0 = new StringTokenizer(extractString(r6), ":");
        int r62 = r0.countTokens();
        Class[] r1 = new Class[r62];
        int r2 = 0;
    L3:
        if (r2 >= r62) goto L5;
        r1[r2] = Factory.makeClass(r0.nextToken(), getLookupClassLoader());
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public Class getDeclaringType() {
        if (this.declaringType != null) goto L6;
        this.declaringType = extractType(2);
    L6:
        return this.declaringType;
    }

    public String getDeclaringTypeName() {
        if (this.declaringTypeName != null) goto L6;
        this.declaringTypeName = getDeclaringType().getName();
    L6:
        return this.declaringTypeName;
    }

    public int getModifiers() {
        if (this.modifiers != (-1)) goto L6;
        this.modifiers = extractInt(0);
    L6:
        return this.modifiers;
    }

    public String getName() {
        if (this.name != null) goto L6;
        this.name = extractString(1);
    L6:
        return this.name;
    }

    public final String toString() {
        return toString(StringMaker.middleStringMaker);
    }

    public String toString(StringMaker r3) {
        if (useCache == false) goto L10;
        Cache r0 = this.stringCache;
        if (r0 == null) goto L17;
        String r02 = r0.get(r3.cacheOffset);
    L11:
        if (r02 != null) goto L14;
        r02 = createToString(r3);
    L14:
        if (useCache == false) goto L16;
        this.stringCache.set(r3.cacheOffset, r02);
    L16:
        return r02;
    L17:
        this.stringCache = new CacheImpl();     // Catch: Throwable -> L8
    L8:
        useCache = false;
    L10:
        r02 = null;
        goto L11
    }
}
