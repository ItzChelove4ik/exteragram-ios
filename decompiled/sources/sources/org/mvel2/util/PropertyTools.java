package org.mvel2.util;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.DataConversion;
import org.mvel2.ParserContext;
import org.mvel2.compiler.PropertyVerifier;

/* loaded from: classes5.dex */
public class PropertyTools {
    public PropertyTools() {
    }

    public static boolean contains(Object r5, Object r6) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if ((r5 instanceof String) == false) goto L10;
        return ((String) r5).contains(String.valueOf(r6));
    L10:
        if ((r5 instanceof Collection) == false) goto L14;
        return ((Collection) r5).contains(r6);
    L14:
        if ((r5 instanceof Map) == false) goto L18;
        return ((Map) r5).containsKey(r6);
    L18:
        if (r5.getClass().isArray() == false) goto L30;
        Object[] r52 = (Object[]) r5;
        int r1 = r52.length;
        int r2 = 0;
    L20:
        if (r2 >= r1) goto L30;
        Object r3 = r52[r2];
        if (r6 != null) goto L25;
        if (r3 != null) goto L25;
        return true;
    L25:
        if (r3 == null) goto L29;
        if (r3.equals(r6) == false) goto L29;
        return true;
    L29:
        r2 = r2 + 1;
    L30:
        return false;
    }

    public static Member getFieldOrAccessor(Class r5, String r6) {
        Field[] r0 = r5.getFields();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L11;
        Field r3 = r0[r2];
        if (r6.equals(r3.getName()) == true) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        if ((r3.getModifiers() & 1) == 0) goto L11;
        return r3;
    L11:
        return getGetter(r5, r6);
    }

    public static Member getFieldOrWriteAccessor(Class r2, String r3) {
        Field r0 = r2.getField(r3);     // Catch: NoSuchFieldException -> L11 NullPointerException -> L9
        if (r0 == null) goto L8;
        if (Modifier.isPublic(r0.getModifiers()) == false) goto L8;
        return r0;
    L9:
        return null;
    L8:
        return getSetter(r2, r3);
    }

    public static Member getFieldOrWriteAccessor(Class r5, String r6, Class r7) {
        Field[] r0 = r5.getFields();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L12;
        Field r3 = r0[r2];
        if (r6.equals(r3.getName()) == false) goto L10;
        if (r7 == null) goto L9;
        if (DataConversion.canConvert(r3.getType(), r7) == false) goto L10;
    L9:
        return r3;
    L10:
        r2 = r2 + 1;
        goto L3
    L12:
        return getSetter(r5, r6, r7);
    }

    public static Method getGetter(Class r12, String r13) {
        String r0 = "get" + r13;
        String r1 = "is" + r13;
        String r2 = ReflectionUtil.getIsGetter(r13);
        String r3 = ReflectionUtil.getGetter(r13);
        HashMap r4 = new HashMap();
        r4.put(r2, 4);
        r4.put(r1, 3);
        r4.put(r3, 2);
        r4.put(r0, 1);
        int r6 = 0;
        r4.put(r13, 0);
        Method r9 = null;
        if (Collection.class.isAssignableFrom(r12) == true) goto L5;
    L8:
        Method[] r122 = r12.getMethods();
        int r7 = r122.length;
    L9:
        if (r6 >= r7) goto L33;
        Method r8 = r122[r6];
        if ((r8.getModifiers() & 1) == 0) goto L32;
        if ((r8.getModifiers() & 8) != 0) goto L32;
        if (r8.getParameterTypes().length != 0) goto L32;
        if (r3.equals(r8.getName()) == false) goto L19;
    L28:
        if (r9 != null) goto L30;
    L31:
        r9 = r8;
        goto L32
    L30:
        if (isPreferredGetter(r9, r8, r4) == false) goto L32;
    L19:
        if (r13.equals(r8.getName()) == true) goto L28;
        if (r2.equals(r8.getName()) == true) goto L25;
        if (r1.equals(r8.getName()) == true) goto L25;
    L27:
        if (r0.equals(r8.getName()) == false) goto L32;
    L25:
        if (r8.getReturnType() == Boolean.TYPE) goto L28;
    L32:
        r6 = r6 + 1;
        goto L9
    L33:
        return r9;
    L5:
        if ("isEmpty".equals(r2) == false) goto L8;
        return Collection.class.getMethod("isEmpty", null);
    }

    public static String getJavaVersion() {
        if (System.getProperty("mvel.java.version") == null) goto L7;
        return System.getProperty("mvel.java.version");
    L7:
        return System.getProperty("java.version");
    }

    public static Object getPrimitiveInitialValue(Class r3) {
        if (r3 != Integer.TYPE) goto L6;
        return 0;
    L6:
        if (r3 != Boolean.TYPE) goto L10;
        return Boolean.FALSE;
    L10:
        if (r3 != Character.TYPE) goto L14;
        return (char) 0;
    L14:
        if (r3 != Double.TYPE) goto L18;
        return Double.valueOf(0.0d);
    L18:
        if (r3 != Long.TYPE) goto L22;
        return 0L;
    L22:
        if (r3 != Float.TYPE) goto L26;
        return Float.valueOf(0.0f);
    L26:
        if (r3 != Short.TYPE) goto L30;
        return (short) 0;
    L30:
        if (r3 == Byte.TYPE) goto L32;
        return 0;
    L32:
        return (byte) 0;
    }

    public static Class getReturnType(Class r1, String r2, ParserContext r3) {
        return new PropertyVerifier(r2, r3, r1).analyze();
    }

    public static Method getSetter(Class r5, String r6) {
        String r62 = ReflectionUtil.getSetter(r6);
        Method[] r52 = r5.getMethods();
        int r0 = r52.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        Method r2 = r52[r1];
        if ((r2.getModifiers() & 1) == 0) goto L11;
        if (r2.getParameterTypes().length != 1) goto L11;
        if (r62.equals(r2.getName()) == false) goto L11;
        return r2;
    L11:
        r1 = r1 + 1;
        goto L3
    L12:
        return null;
    }

    public static Method getSetter(Class r7, String r8, Class r9) {
        String r0 = "set" + r8;
        String r82 = ReflectionUtil.getSetter(r8);
        Method[] r72 = r7.getMethods();
        int r1 = r72.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L17;
        Method r4 = r72[r3];
        if ((r4.getModifiers() & 1) == 0) goto L16;
        if (r4.getParameterTypes().length != 1) goto L16;
        if (r82.equals(r4.getName()) == false) goto L11;
    L12:
        if (r9 == null) goto L15;
        if (DataConversion.canConvert(r4.getParameterTypes()[0], r9) == false) goto L16;
    L15:
        return r4;
    L11:
        if (r0.equals(r4.getName()) == true) goto L12;
    L16:
        r3 = r3 + 1;
        goto L3
    L17:
        return null;
    }

    public static boolean hasGetter(Field r2) {
        Method r0 = getGetter(r2.getDeclaringClass(), r2.getName());
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r2.getType().isAssignableFrom(r0.getReturnType()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean hasSetter(Field r4) {
        Method r0 = getSetter(r4.getDeclaringClass(), r4.getName());
        if (r0 != null) goto L5;
    L9:
        return false;
    L5:
        if (r0.getParameterTypes().length != 1) goto L9;
        if (r4.getType().isAssignableFrom(r0.getParameterTypes()[0]) == false) goto L9;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean isAssignable(Class r1, Class r2) {
        boolean r0 = r1.isPrimitive();
        Class r12 = r1;
        if (r0 == false) goto L6;
        r12 = ParseTools.boxPrimitive(r1);
    L6:
        if (r2.isPrimitive() == false) goto L9;
        r2 = ParseTools.boxPrimitive(r2);
    L9:
        return r12.isAssignableFrom(r2);
    }

    public static boolean isEmpty(Object r4) {
        if (r4 != null) goto L5;
    L29:
        return true;
    L5:
        if ((r4 instanceof Object[]) == false) goto L16;
        Object[] r42 = (Object[]) r4;
        if (r42.length != 0) goto L9;
    L14:
        return true;
    L9:
        if (r42.length == 1) goto L11;
    L13:
        return false;
    L11:
        if (isEmpty(r42[0]) == false) goto L13;
    L16:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(String.valueOf(r4)) == true) goto L29;
        if ("null".equals(String.valueOf(r4)) == true) goto L29;
        if ((r4 instanceof Collection) == false) goto L24;
        if (((Collection) r4).size() == 0) goto L29;
    L24:
        if ((r4 instanceof Map) == true) goto L26;
    L28:
        return false;
    L26:
        if (((Map) r4).size() != 0) goto L28;
        goto L28
    }

    private static boolean isPreferredGetter(Method r5, Method r6, Map<String, Integer> r7) {
        Class<?> r0 = r5.getReturnType();
        Class<?> r1 = r6.getReturnType();
        if (r0.equals(r1) == false) goto L9;
        if (r7.get(r6.getName()).intValue() <= r7.get(r5.getName()).intValue()) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (r0.isAssignableFrom(r1) == false) goto L11;
        return true;
    L11:
        return false;
    }
}
