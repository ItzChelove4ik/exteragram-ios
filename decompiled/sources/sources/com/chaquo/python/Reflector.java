package com.chaquo.python;

import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class Reflector {
    private static final DeniedMethod[] DENIED_METHODS = null;
    private static final Map<Class<?>, WeakReference<Reflector>> instances = null;
    private Map<String, Class<?>> classes;
    private Map<String, Class<?>> classesAll;
    private Map<String, Field> fields;
    private Map<String, Field> fieldsAll;
    private final Class<?> klass;
    private Map<String, Member> methods;
    private Map<String, Member> methodsAll;
    private Map<String, List<Member>> multipleMethods;
    private Map<String, List<Member>> multipleMethodsAll;
    private Map<String, List<Member>> multiplePropertyGetters;
    private Map<String, List<Member>> multiplePropertyGettersAll;
    private Map<String, List<Member>> multiplePropertySetters;
    private Map<String, List<Member>> multiplePropertySettersAll;
    private Map<String, List<Member>> multipleStaticPropertyGetters;
    private Map<String, List<Member>> multipleStaticPropertyGettersAll;
    private Map<String, List<Member>> multipleStaticPropertySetters;
    private Map<String, List<Member>> multipleStaticPropertySettersAll;
    private Map<String, Member> propertyGetters;
    private Map<String, Member> propertyGettersAll;
    private Map<String, Member> propertySetters;
    private Map<String, Member> propertySettersAll;
    private Map<String, Member> staticPropertyGetters;
    private Map<String, Member> staticPropertyGettersAll;
    private Map<String, Member> staticPropertySetters;
    private Map<String, Member> staticPropertySettersAll;

    public static class DeniedMethod {
        private final String className;
        private final String methodName;
        private final Class<?>[] parameterTypes;
        private final Class<?> returnType;

        public DeniedMethod(String r1, String r2, Class<?> r3, Class<?>... r4) {
            this.className = r1;
            this.methodName = r2;
            this.returnType = r3;
            this.parameterTypes = r4;
        }

        public boolean matches(Method r3) {
            if (this.className.equals(r3.getDeclaringClass().getName()) == true) goto L5;
            return false;
        L5:
            if (this.methodName.equals(r3.getName()) == true) goto L7;
            return false;
        L7:
            if (this.returnType == r3.getReturnType()) goto L9;
            return false;
        L9:
            if (Arrays.equals(this.parameterTypes, r3.getParameterTypes()) == false) goto L16;
            return true;
        L16:
            return false;
        }
    }

    static {
        Class[] r1 = {Long.TYPE};
        DENIED_METHODS = new DeniedMethod[]{new DeniedMethod("android.widget.TextView", "setTextColor", Void.TYPE, r1)};
        instances = new WeakHashMap();
    }

    private Reflector(Class<?> r1) {
        this.klass = r1;
    }

    private void addPropertyAlias(List<String> r1, String r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r1.contains(r2) == true) goto L8;
        r1.add(r2);
        return;
    }

    private String booleanPropertyAliasName(String r3) {
        if (r3.isEmpty() == false) goto L7;
        return null;
    L7:
        if (r3.startsWith("Is") == false) goto L15;
        if (r3.length() <= 2) goto L15;
        if (Character.isUpperCase(r3.charAt(2)) == false) goto L15;
        return decapitalizePropertyName(r3);
    L15:
        return "is".concat(r3);
    }

    private String decapitalizePropertyName(String r2) {
        if (r2.isEmpty() == false) goto L7;
        return null;
    L7:
        return Character.toLowerCase(r2.charAt(0)) + r2.substring(1);
    }

    private Collection<Field> getDeclaredFields() {
        return Arrays.asList(this.klass.getDeclaredFields());
    L4:
        HashSet r0 = new HashSet();
        Field[] r1 = this.klass.getFields();     // Catch: NoClassDefFoundError -> L13
        int r2 = r1.length;     // Catch: NoClassDefFoundError -> L13
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L11;
        Field r4 = r1[r3];     // Catch: NoClassDefFoundError -> L13
        if (r4.getDeclaringClass() != this.klass) goto L10;
        r4.getType();     // Catch: NoClassDefFoundError -> L12
        r0.add(r4);     // Catch: NoClassDefFoundError -> L12
    L10:
        r3 = r3 + 1;
    L11:
        return r0;
    }

    private Collection<Method> getDeclaredMethods() {
        return Arrays.asList(this.klass.getDeclaredMethods());
    L4:
        HashSet r0 = new HashSet();
        Method[] r1 = this.klass.getMethods();     // Catch: NoClassDefFoundError -> L22
        int r2 = r1.length;     // Catch: NoClassDefFoundError -> L22
        int r3 = 0;
    L6:
        if (r3 >= r2) goto L11;
        Method r4 = r1[r3];     // Catch: NoClassDefFoundError -> L22
        if (r4.getDeclaringClass() != this.klass) goto L10;
        r4.getReturnType();     // Catch: NoClassDefFoundError -> L21
        r4.getParameterTypes();     // Catch: NoClassDefFoundError -> L21
        r0.add(r4);     // Catch: NoClassDefFoundError -> L21
    L10:
        r3 = r3 + 1;
    L11:
        Class<?> r12 = this.klass;
    L12:
        r12 = r12.getSuperclass();
        if (r12 == null) goto L20;
        Iterator<Method> r22 = getInstance(r12).getDeclaredMethods().iterator();
    L16:
        if (r22.hasNext() == false) goto L12;
        Method r32 = r22.next();
        r0.add(this.klass.getDeclaredMethod(r32.getName(), r32.getParameterTypes()));     // Catch: NoSuchMethodException -> L23
        goto L16
    L20:
        return r0;
    }

    public static synchronized Reflector getInstance(Class<?> r4) {
        monitor-enter(Reflector.class);
        Map<Class<?>, WeakReference<Reflector>> r1 = instances;     // Catch: Throwable -> L14
        WeakReference<Reflector> r2 = r1.get(r4);     // Catch: Throwable -> L14
        if (r2 != null) goto L7;
        Reflector r22 = null;
    L8:
        if (r22 == null) goto L11;
        monitor-exit(Reflector.class);
        return r22;
    L11:
        Reflector r23 = new Reflector(r4);     // Catch: Throwable -> L14
        r1.put(r4, new WeakReference(r23));     // Catch: Throwable -> L14
        monitor-exit(Reflector.class);
        return r23;
    L7:
        r22 = r2.get();     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    private Member[] getPropertyMethods(String r1, boolean r2, int r3, Map<String, Member> r4, Map<String, List<Member>> r5, Map<String, Member> r6, Map<String, List<Member>> r7, Map<String, Member> r8, Map<String, List<Member>> r9, Map<String, Member> r10, Map<String, List<Member>> r11) {
        if (r2 == false) goto L8;
        if (r3 != 1) goto L6;
        r8 = r10;
    L6:
        if (r3 != 1) goto L13;
        r9 = r11;
    L13:
        List<Member> r22 = r9.get(r1);
        if (r22 != null) goto L16;
        Member r12 = r8.get(r1);
        if (r12 != null) goto L20;
        return null;
    L20:
        return new Member[]{r12};
    L16:
        return (Member[]) r22.toArray(new Member[0]);
    L8:
        if (r3 != 1) goto L10;
        r4 = r6;
    L10:
        if (r3 != 1) goto L12;
        r5 = r7;
    L12:
        r8 = r4;
        r9 = r5;
        goto L13
    }

    private boolean includeMember(Member r1, boolean r2) {
        if (r2 == false) goto L10;
        if (r1.isSynthetic() == true) goto L7;
        return true;
    L7:
        return false;
    L10:
        return isAccessible(r1);
    }

    private boolean isAccessible(int r1) {
        if ((r1 & 5) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isAccessible(Member r2) {
        if (isAccessible(r2.getModifiers()) == true) goto L6;
        return false;
    L6:
        if (r2.isSynthetic() == false) goto L8;
        return false;
    L8:
        return true;
    }

    private boolean isBooleanType(Class<?> r1) {
        if (r1 != Boolean.TYPE) goto L5;
        return true;
    L5:
        if (r1 == Boolean.class) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isDeniedMethod(Method r5) {
        DeniedMethod[] r4 = DENIED_METHODS;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2].matches(r5) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private void loadClasses(boolean r8) {
        HashMap r0 = new HashMap();
        Class<?>[] r1 = this.klass.getDeclaredClasses();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L13;
        Class<?> r4 = r1[r3];
        if (r8 == false) goto L7;
    L8:
        String r5 = r4.getSimpleName();
        if (r5.isEmpty() == true) goto L12;
        r0.put(r5, r4);
    L12:
        r3 = r3 + 1;
        goto L3
    L7:
        if (isAccessible(r4.getModifiers()) == false) goto L12;
    L13:
        if (r8 == false) goto L16;
        this.classesAll = r0;
        return;
    L16:
        this.classes = r0;
    }

    private void loadFields(boolean r5) {
        HashMap r0 = new HashMap();
        Iterator<Field> r1 = getDeclaredFields().iterator();
    L4:
        if (r1.hasNext() == false) goto L10;
        Field r2 = r1.next();
        if (r5 == true) goto L9;
        if (isAccessible(r2) == false) goto L4;
    L9:
        prepareAccessible(r2, r5);
        r0.put(r2.getName(), r2);
        goto L4
    L10:
        if (r5 == false) goto L13;
        this.fieldsAll = r0;
        return;
    L13:
        this.fields = r0;
    }

    private void loadMethod(Map<String, Member> r1, Map<String, List<Member>> r2, Member r3, String r4) {
        Member r0 = r1.remove(r4);
        if (r0 == null) goto L6;
        ArrayList r12 = new ArrayList();
        r12.add(r0);
        r12.add(r3);
        r2.put(r4, r12);
        return;
    L6:
        List<Member> r02 = r2.get(r4);
        if (r02 == null) goto L10;
        r02.add(r3);
        return;
    L10:
        r1.put(r4, r3);
    }

    private void loadMethods(boolean r18) {
        Map<String, Member> r11 = new HashMap();
        Map<String, List<Member>> r12 = new HashMap();
        Map<String, Member> r2 = new HashMap();
        Map<String, List<Member>> r3 = new HashMap();
        Map<String, Member> r4 = new HashMap();
        Map<String, List<Member>> r5 = new HashMap();
        Map<String, Member> r6 = new HashMap();
        Map<String, List<Member>> r7 = new HashMap();
        Map<String, Member> r8 = new HashMap();
        Map<String, List<Member>> r9 = new HashMap();
        Constructor<?>[] r1 = this.klass.getDeclaredConstructors();
        int r13 = r1.length;
        int r14 = 0;
    L3:
        if (r14 >= r13) goto L9;
        Constructor<?> r15 = r1[r14];
        if (includeMember(r15, r18) == false) goto L7;
        prepareAccessible(r15, r18);
        Constructor<?>[] r16 = r1;
        loadMethod(r11, r12, r15, "<init>");
    L8:
        r14 = r14 + 1;
        r1 = r16;
        goto L3
    L7:
        r16 = r1;
        goto L8
    L9:
        Iterator<Method> r132 = getDeclaredMethods().iterator();
    L11:
        if (r132.hasNext() == false) goto L18;
        Method r17 = r132.next();
        if (isDeniedMethod(r17) == true) goto L11;
        if (includeMember(r17, r18) == false) goto L11;
        prepareAccessible(r17, r18);
        loadMethod(r11, r12, r17, r17.getName());
        loadPropertyMethods(r17, r2, r3, r4, r5, r6, r7, r8, r9);
        goto L11
    L18:
        if (r18 == false) goto L21;
        this.methodsAll = r11;
        this.multipleMethodsAll = r12;
        this.propertyGettersAll = r2;
        this.multiplePropertyGettersAll = r3;
        this.staticPropertyGettersAll = r4;
        this.multipleStaticPropertyGettersAll = r5;
        this.propertySettersAll = r6;
        this.multiplePropertySettersAll = r7;
        this.staticPropertySettersAll = r8;
        this.multipleStaticPropertySettersAll = r9;
        return;
    L21:
        this.methods = r11;
        this.multipleMethods = r12;
        this.propertyGetters = r2;
        this.multiplePropertyGetters = r3;
        this.staticPropertyGetters = r4;
        this.multipleStaticPropertyGetters = r5;
        this.propertySetters = r6;
        this.multiplePropertySetters = r7;
        this.staticPropertySetters = r8;
        this.multipleStaticPropertySetters = r9;
    }

    private void loadPropertyMethods(Method r4, Map<String, Member> r5, Map<String, List<Member>> r6, Map<String, Member> r7, Map<String, List<Member>> r8, Map<String, Member> r9, Map<String, List<Member>> r10, Map<String, Member> r11, Map<String, List<Member>> r12) {
        boolean r0 = Modifier.isStatic(r4.getModifiers());
        Iterator<String> r1 = propertyGetterNames(r4).iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        String r2 = r1.next();
        loadMethod(r5, r6, r4, r2);
        if (r0 == false) goto L4;
        loadMethod(r7, r8, r4, r2);
        goto L4
    L8:
        Iterator<String> r52 = propertySetterNames(r4).iterator();
    L10:
        if (r52.hasNext() == false) goto L14;
        String r62 = r52.next();
        loadMethod(r9, r10, r4, r62);
        if (r0 == false) goto L10;
        loadMethod(r11, r12, r4, r62);
        goto L10
    }

    private void prepareAccessible(AccessibleObject r1, boolean r2) {
        if (r2 == true) goto L8;
        return;
    L8:
        r1.setAccessible(true);     // Catch: SecurityException -> L7
        return;
    }

    private List<String> propertyGetterNames(Method r6) {
        String r0 = r6.getName();
        ArrayList r1 = new ArrayList();
        if (r0.startsWith("get") == false) goto L14;
        if (r0.length() <= 3) goto L14;
        if (r6.getParameterTypes().length != 0) goto L14;
        if (r6.getReturnType() == Void.TYPE) goto L14;
        String r2 = r0.substring(3);
        addPropertyAlias(r1, decapitalizePropertyName(r2));
        if (isBooleanType(r6.getReturnType()) == false) goto L14;
        addPropertyAlias(r1, booleanPropertyAliasName(r2));
    L14:
        if (r0.startsWith("is") == true) goto L16;
    L22:
        return r1;
    L16:
        if (r0.length() <= 2) goto L22;
        if (r6.getParameterTypes().length != 0) goto L22;
        if (isBooleanType(r6.getReturnType()) == false) goto L22;
        addPropertyAlias(r1, decapitalizePropertyName(r0.substring(2)));
        addPropertyAlias(r1, r0);
        goto L22
    }

    private List<String> propertySetterNames(Method r5) {
        String r0 = r5.getName();
        ArrayList r1 = new ArrayList();
        if (r0.startsWith("set") == true) goto L5;
    L11:
        return r1;
    L5:
        if (r0.length() <= 3) goto L11;
        if (r5.getParameterTypes().length == 0) goto L11;
        String r02 = r0.substring(3);
        addPropertyAlias(r1, decapitalizePropertyName(r02));
        if (isBooleanType(r5.getParameterTypes()[0]) == false) goto L11;
        addPropertyAlias(r1, booleanPropertyAliasName(r02));
        goto L11
    }

    public synchronized String[] dir() {
        monitor-enter(this);
        String[] r0 = dir(false);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized String[] dir(boolean r4) {
        monitor-enter(this);
        if (r4 == false) goto L17;
        if (this.methodsAll != null) goto L11;
        loadMethods(true);     // Catch: Throwable -> L8
    L11:
        if (this.fieldsAll != null) goto L14;
        loadFields(true);     // Catch: Throwable -> L8
    L14:
        if (this.classesAll != null) goto L25;
        loadClasses(true);     // Catch: Throwable -> L8
    L25:
        HashSet r1 = new HashSet();     // Catch: Throwable -> L8
        if (r4 == false) goto L28;
        Map<String, Member> r2 = this.methodsAll;     // Catch: Throwable -> L8
    L29:
        r1.addAll(r2.keySet());     // Catch: Throwable -> L8
        if (r4 == false) goto L32;
        Map<String, List<Member>> r22 = this.multipleMethodsAll;     // Catch: Throwable -> L8
    L33:
        r1.addAll(r22.keySet());     // Catch: Throwable -> L8
        if (r4 == false) goto L36;
        Map<String, Field> r23 = this.fieldsAll;     // Catch: Throwable -> L8
    L37:
        r1.addAll(r23.keySet());     // Catch: Throwable -> L8
        if (r4 == false) goto L40;
        Map<String, Class<?>> r42 = this.classesAll;     // Catch: Throwable -> L8
    L41:
        r1.addAll(r42.keySet());     // Catch: Throwable -> L8
        String[] r43 = (String[]) r1.toArray(new String[0]);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r43;
    L40:
        r42 = this.classes;     // Catch: Throwable -> L8
        goto L41
    L36:
        r23 = this.fields;     // Catch: Throwable -> L8
        goto L37
    L32:
        r22 = this.multipleMethods;     // Catch: Throwable -> L8
        goto L33
    L28:
        r2 = this.methods;     // Catch: Throwable -> L8
        goto L29
    L17:
        if (this.methods != null) goto L20;
        loadMethods(false);     // Catch: Throwable -> L8
    L20:
        if (this.fields != null) goto L23;
        loadFields(false);     // Catch: Throwable -> L8
    L23:
        if (this.classes != null) goto L25;
        loadClasses(false);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized String[] dirAll() {
        monitor-enter(this);
        String[] r0 = dir(true);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Field getField(String r2) {
        monitor-enter(this);
        Field r22 = getField(r2, false);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Field getField(String r1, boolean r2) {
        monitor-enter(this);
        if (r2 == false) goto L13;
        if (this.fieldsAll != null) goto L9;
        loadFields(true);     // Catch: Throwable -> L7
    L9:
        Field r12 = this.fieldsAll.get(r1);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r12;
    L13:
        if (this.fields != null) goto L15;
        loadFields(false);     // Catch: Throwable -> L7
    L15:
        Field r13 = this.fields.get(r1);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r13;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Field getFieldAll(String r2) {
        monitor-enter(this);
        Field r22 = getField(r2, true);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Member[] getMethods(String r2) {
        monitor-enter(this);
        Member[] r22 = getMethods(r2, false);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Member[] getMethods(String r4, boolean r5) {
        monitor-enter(this);
        if (r5 == false) goto L11;
        if (this.methodsAll != null) goto L13;
        loadMethods(true);     // Catch: Throwable -> L8
    L13:
        if (r5 == false) goto L15;
        Map<String, Member> r2 = this.methodsAll;     // Catch: Throwable -> L8
    L16:
        if (r5 == false) goto L18;
        Map<String, List<Member>> r52 = this.multipleMethodsAll;     // Catch: Throwable -> L8
    L19:
        List<Member> r53 = r52.get(r4);     // Catch: Throwable -> L8
        if (r53 == null) goto L24;
        Member[] r42 = (Member[]) r53.toArray(new Member[0]);     // Catch: Throwable -> L8
        monitor-exit(this);
        return r42;
    L24:
        Member r43 = r2.get(r4);     // Catch: Throwable -> L8
        if (r43 == null) goto L29;
        Member[] r54 = {r43};     // Catch: Throwable -> L8
        monitor-exit(this);
        return r54;
    L29:
        monitor-exit(this);
        return null;
    L18:
        r52 = this.multipleMethods;     // Catch: Throwable -> L8
        goto L19
    L15:
        r2 = this.methods;     // Catch: Throwable -> L8
        goto L16
    L11:
        if (this.methods != null) goto L13;
        loadMethods(false);     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public synchronized Member[] getMethodsAll(String r2) {
        monitor-enter(this);
        Member[] r22 = getMethods(r2, true);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Class<?> getNestedClass(String r2) {
        monitor-enter(this);
        Class<?> r22 = getNestedClass(r2, false);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Class<?> getNestedClass(String r1, boolean r2) {
        monitor-enter(this);
        if (r2 == false) goto L13;
        if (this.classesAll != null) goto L9;
        loadClasses(true);     // Catch: Throwable -> L7
    L9:
        Class<?> r12 = this.classesAll.get(r1);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r12;
    L13:
        if (this.classes != null) goto L15;
        loadClasses(false);     // Catch: Throwable -> L7
    L15:
        Class<?> r13 = this.classes.get(r1);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r13;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Class<?> getNestedClassAll(String r2) {
        monitor-enter(this);
        Class<?> r22 = getNestedClass(r2, true);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Member[] getPropertyGetters(String r14, boolean r15, int r16) {
        monitor-enter(this);
        if (r15 == false) goto L10;
        if (this.propertyGettersAll != null) goto L12;
        loadMethods(true);     // Catch: Throwable -> L7
    L12:
        Member[] r142 = getPropertyMethods(r14, r15, r16, this.propertyGetters, this.multiplePropertyGetters, this.staticPropertyGetters, this.multipleStaticPropertyGetters, this.propertyGettersAll, this.multiplePropertyGettersAll, this.staticPropertyGettersAll, this.multipleStaticPropertyGettersAll);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r142;
    L10:
        if (this.propertyGetters != null) goto L12;
        loadMethods(false);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized Member[] getPropertySetters(String r14, boolean r15, int r16) {
        monitor-enter(this);
        if (r15 == false) goto L10;
        if (this.propertySettersAll != null) goto L12;
        loadMethods(true);     // Catch: Throwable -> L7
    L12:
        Member[] r142 = getPropertyMethods(r14, r15, r16, this.propertySetters, this.multiplePropertySetters, this.staticPropertySetters, this.multipleStaticPropertySetters, this.propertySettersAll, this.multiplePropertySettersAll, this.staticPropertySettersAll, this.multipleStaticPropertySettersAll);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r142;
    L10:
        if (this.propertySetters != null) goto L12;
        loadMethods(false);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }
}
