package org.aspectj.runtime.reflect;

import java.util.Hashtable;
import java.util.StringTokenizer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.aspectj.runtime.reflect.JoinPointImpl;

/* loaded from: classes5.dex */
public final class Factory {
    private static Object[] NO_ARGS;
    static /* synthetic */ Class class$java$lang$ClassNotFoundException;
    static Hashtable prims;
    int count;
    String filename;
    Class lexicalClass;
    ClassLoader lookupClassLoader;

    static {
        Hashtable r0 = new Hashtable();
        prims = r0;
        r0.put("void", Void.TYPE);
        prims.put("boolean", Boolean.TYPE);
        prims.put("byte", Byte.TYPE);
        prims.put("char", Character.TYPE);
        prims.put("short", Short.TYPE);
        prims.put("int", Integer.TYPE);
        prims.put("long", Long.TYPE);
        prims.put("float", Float.TYPE);
        prims.put("double", Double.TYPE);
        NO_ARGS = new Object[0];
    }

    public Factory(String r1, Class r2) {
        this.filename = r1;
        this.lexicalClass = r2;
        this.count = 0;
        this.lookupClassLoader = r2.getClassLoader();
    }

    public static /* synthetic */ Class class$(String r1) {
        return Class.forName(r1);
    L4:
        e = move-exception;
        throw new NoClassDefFoundError(e.getMessage());
    }

    public static Class makeClass(String r1, ClassLoader r2) {
        if (r1.equals("*") == false) goto L6;
        return null;
    L6:
        Class r0 = (Class) prims.get(r1);
        if (r0 == null) goto L18;
        return r0;
    L18:
        if (r2 != null) goto L13;
        return Class.forName(r1);
    L13:
        return Class.forName(r1, false, r2);
    L14:
        Class r12 = class$java$lang$ClassNotFoundException;
        if (r12 != null) goto L19;
        Class r13 = class$("java.lang.ClassNotFoundException");
        class$java$lang$ClassNotFoundException = r13;
        return r13;
    L19:
        return r12;
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart r2, Object r3, Object r4) {
        return new JoinPointImpl(r2, r3, r4, NO_ARGS);
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart r1, Object r2, Object r3, Object r4) {
        return new JoinPointImpl(r1, r2, r3, new Object[]{r4});
    }

    public MethodSignature makeMethodSig(String r8, String r9, String r10, String r11, String r12, String r13, String r14) {
        int r82 = Integer.parseInt(r8, 16);
        Class r102 = makeClass(r10, this.lookupClassLoader);
        StringTokenizer r0 = new StringTokenizer(r11, ":");
        int r112 = r0.countTokens();
        Class[] r113 = new Class[r112];
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r112) goto L5;
        r113[r4] = makeClass(r0.nextToken(), this.lookupClassLoader);
        r4 = r4 + 1;
        goto L3
    L5:
        StringTokenizer r02 = new StringTokenizer(r12, ":");
        int r122 = r02.countTokens();
        String[] r123 = new String[r122];
        int r42 = 0;
    L6:
        if (r42 >= r122) goto L8;
        r123[r42] = r02.nextToken();
        r42 = r42 + 1;
        goto L6
    L8:
        StringTokenizer r03 = new StringTokenizer(r13, ":");
        int r132 = r03.countTokens();
        Class[] r133 = new Class[r132];
    L9:
        if (r3 >= r132) goto L12;
        r133[r3] = makeClass(r03.nextToken(), this.lookupClassLoader);
        r3 = r3 + 1;
        goto L9
    L12:
        return new MethodSignatureImpl(r82, r9, r102, r113, r123, r133, makeClass(r14, this.lookupClassLoader));
    }

    public JoinPoint.StaticPart makeSJP(String r4, Signature r5, int r6) {
        int r1 = this.count;
        this.count = r1 + 1;
        return new JoinPointImpl.StaticPartImpl(r1, r4, r5, makeSourceLoc(r6, -1));
    }

    public SourceLocation makeSourceLoc(int r2, int r3) {
        return new SourceLocationImpl(this.lexicalClass, this.filename, r2);
    }
}
