package org.mvel2;

import c.a$$ExternalSyntheticBUOutline0;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.mvel2.ast.Proto;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.integration.Interceptor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ClassImportResolverFactory;
import org.mvel2.integration.impl.StackResetResolverFactory;
import org.mvel2.util.MethodStub;
import org.mvel2.util.ParseTools;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ParserConfiguration implements Serializable {
    private boolean allowBootstrapBypass;
    private boolean allowNakedMethCall;
    protected transient ClassLoader classLoader;
    protected final Map<String, Object> imports;
    protected Map<String, Interceptor> interceptors;
    private final transient Set<String> nonValidImports;
    protected HashSet<String> packageImports;
    private VariableResolverFactory threadUnsafeVariableResolverFactory;

    public ParserConfiguration() {
        this.imports = new ConcurrentHashMap();
        this.nonValidImports = Collections.newSetFromMap(new ConcurrentHashMap());
        this.allowNakedMethCall = MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL;
        this.allowBootstrapBypass = true;
    }

    public ParserConfiguration(Map<String, Object> r2, HashSet<String> r3, Map<String, Interceptor> r4) {
        this.imports = new ConcurrentHashMap();
        this.nonValidImports = Collections.newSetFromMap(new ConcurrentHashMap());
        this.allowNakedMethCall = MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL;
        this.allowBootstrapBypass = true;
        addAllImports(r2);
        this.packageImports = r3;
        this.interceptors = r4;
    }

    public ParserConfiguration(Map<String, Object> r2, Map<String, Interceptor> r3) {
        this.imports = new ConcurrentHashMap();
        this.nonValidImports = Collections.newSetFromMap(new ConcurrentHashMap());
        this.allowNakedMethCall = MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL;
        this.allowBootstrapBypass = true;
        addAllImports(r2);
        this.interceptors = r3;
    }

    private boolean addClassMemberStaticImports(String r9) {
        Class<?> r1 = Class.forName(r9);     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
        if (r1.isEnum() == false) goto L13;
        Iterator r12 = EnumSet.allOf(r1).iterator();     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
    L7:
        if (r12.hasNext() == false) goto L11;
        Enum r2 = (Enum) r12.next();     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
        this.imports.put(r2.name(), r2);     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
        goto L7
    L11:
        return true;
    L13:
        Field[] r13 = r1.getDeclaredFields();     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
        int r22 = r13.length;     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
        int r3 = 0;
    L14:
        if (r3 >= r22) goto L20;
        Field r4 = r13[r3];     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
        if ((r4.getModifiers() & 9) != 9) goto L18;
        this.imports.put(r4.getName(), r4.get(null));     // Catch: IllegalAccessException -> L9 ClassNotFoundException -> L21
    L18:
        r3 = r3 + 1;
    L9:
        e = move-exception;
        a$$ExternalSyntheticBUOutline0.m("error adding static imports for: ", r9, e);
    L20:
        return false;
    }

    private void cacheNegativeHitForDynamicImport(String r1) {
        this.nonValidImports.add(r1);
    }

    private boolean checkForDynamicImport(String r7) {
        if (this.packageImports != null) goto L6;
        return false;
    L6:
        if (Character.isJavaIdentifierStart(r7.charAt(0)) == true) goto L9;
        return false;
    L9:
        if (this.nonValidImports.contains(r7) == false) goto L11;
        return false;
    L11:
        Iterator<String> r0 = this.packageImports.iterator();
        Class r2 = null;
        int r3 = 0;
    L13:
        if (r0.hasNext() == false) goto L18;
        r2 = ParseTools.forNameWithInner(r0.next() + "." + r7, getClassLoader());     // Catch: Throwable -> L26
        r3 = r3 + 1;
        goto L13
    L18:
        if (r3 > 1) goto L24;
        if (r3 != 1) goto L22;
        addImport(r7, r2);
        return true;
    L22:
        cacheNegativeHitForDynamicImport(r7);
        return false;
    L24:
        GlShader$$ExternalSyntheticBUOutline1.m("ambiguous class name: ".concat(r7));
        return false;
    }

    private VariableResolverFactory createVariableResolverFactory(VariableResolverFactory r3) {
        if (hasImports() == false) goto L7;
        return new ClassImportResolverFactory(this, r3, true);
    L7:
        return new StackResetResolverFactory(r3);
    }

    public void addAllImports(Map<String, Object> r5) {
        if (r5 == null) goto L11;
        Iterator<Map.Entry<String, Object>> r52 = r5.entrySet().iterator();
    L6:
        if (r52.hasNext() == false) goto L18;
        Map.Entry<String, Object> r0 = r52.next();
        Object r1 = r0.getValue();
        boolean r2 = r1 instanceof Method;
        Map<String, Object> r3 = this.imports;
        if (r2 == true) goto L9;
        r3.put(r0.getKey(), r1);
        goto L6
    L9:
        r3.put(r0.getKey(), new MethodStub((Method) r1));
        goto L6
    L18:
        return;
    }

    public void addImport(Class r2) {
        addImport(r2.getSimpleName(), r2);
    }

    public void addImport(String r1, Class r2) {
        this.imports.put(r1, r2);
    }

    public void addImport(String r2, Method r3) {
        addImport(r2, new MethodStub(r3));
    }

    public void addImport(String r1, Proto r2) {
        this.imports.put(r1, r2);
    }

    public void addImport(String r1, MethodStub r2) {
        this.imports.put(r1, r2);
    }

    public void addPackageImport(String r2) {
        if (this.packageImports != null) goto L5;
        this.packageImports = new LinkedHashSet();
    L5:
        this.packageImports.add(r2);
        if (addClassMemberStaticImports(r2) == true) goto L9;
        this.packageImports.add(r2);
        return;
    }

    public void flushCaches() {
        this.nonValidImports.clear();
    }

    public ClassLoader getClassLoader() {
        ClassLoader r0 = this.classLoader;
        if (r0 != null) goto L6;
        ClassLoader r02 = Thread.currentThread().getContextClassLoader();
        this.classLoader = r02;
        return r02;
    L6:
        return r0;
    }

    public Class getImport(String r2) {
        if (this.imports.containsKey(r2) == false) goto L9;
        if ((this.imports.get(r2) instanceof Class) == false) goto L9;
        return (Class) this.imports.get(r2);
    L9:
        if ((AbstractParser.LITERALS.get(r2) instanceof Class) == false) goto L11;
        Object r1 = AbstractParser.LITERALS.get(r2);
    L13:
        return (Class) r1;
    L11:
        r1 = null;
        goto L13
    }

    public Map<String, Object> getImports() {
        return this.imports;
    }

    public Map<String, Interceptor> getInterceptors() {
        return this.interceptors;
    }

    public HashSet<String> getPackageImports() {
        return this.packageImports;
    }

    public MethodStub getStaticImport(String r1) {
        return (MethodStub) this.imports.get(r1);
    }

    public Object getStaticOrClassImport(String r2) {
        if (this.imports.containsKey(r2) == false) goto L7;
        return this.imports.get(r2);
    L7:
        return AbstractParser.LITERALS.get(r2);
    }

    public VariableResolverFactory getVariableFactory(VariableResolverFactory r3) {
        if (MVEL.RUNTIME_OPT_THREAD_UNSAFE == false) goto L14;
        VariableResolverFactory r0 = this.threadUnsafeVariableResolverFactory;
        if (r0 != null) goto L8;
        this.threadUnsafeVariableResolverFactory = createVariableResolverFactory(r3);
    L12:
        return this.threadUnsafeVariableResolverFactory;
    L8:
        if ((r0 instanceof StackResetResolverFactory) == false) goto L10;
        ((StackResetResolverFactory) r0).setDelegate(r3);
        goto L12
    L10:
        r0.setNextFactory(r3);
        goto L12
    L14:
        return createVariableResolverFactory(r3);
    }

    public boolean hasImport(String r2) {
        if (this.imports.containsKey(r2) == false) goto L5;
        return true;
    L5:
        if (AbstractParser.CLASS_LITERALS.containsKey(r2) == false) goto L7;
        return true;
    L7:
        if (checkForDynamicImport(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean hasImports() {
        if (this.imports.isEmpty() == false) goto L11;
        HashSet<String> r1 = this.packageImports;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.size() != 0) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    public boolean isAllowBootstrapBypass() {
        return this.allowBootstrapBypass;
    }

    public boolean isAllowNakedMethCall() {
        return this.allowNakedMethCall;
    }

    public void setAllImports(Map<String, Object> r2) {
        this.imports.clear();
        if (r2 == null) goto L6;
        this.imports.putAll(r2);
        return;
    }

    public void setAllowBootstrapBypass(boolean r1) {
        this.allowBootstrapBypass = r1;
    }

    public void setAllowNakedMethCall(boolean r1) {
        this.allowNakedMethCall = r1;
    }

    public void setClassLoader(ClassLoader r1) {
        this.classLoader = r1;
    }

    public void setImports(HashMap<String, Object> r1) {
        setAllImports(r1);
    }

    public void setImports(Map<String, Object> r4) {
        if (r4 == null) goto L21;
        Iterator<Map.Entry<String, Object>> r42 = r4.entrySet().iterator();
    L6:
        if (r42.hasNext() == false) goto L35;
        Map.Entry<String, Object> r0 = r42.next();
        Object r1 = r0.getValue();
        if ((r1 instanceof Class) == true) goto L9;
        if ((r1 instanceof Method) == true) goto L12;
        if ((r1 instanceof MethodStub) == true) goto L15;
        if ((r1 instanceof Proto) == false) goto L20;
        addImport(r0.getKey(), (Proto) r0.getValue());
        goto L6
    L20:
        throw new RuntimeException("invalid element in imports map: " + r0.getKey() + " (" + r1 + ")");
    L15:
        addImport(r0.getKey(), (MethodStub) r1);
        goto L6
    L12:
        addImport(r0.getKey(), (Method) r1);
        goto L6
    L9:
        addImport(r0.getKey(), (Class) r1);
        goto L6
    L35:
        return;
    }

    public void setInterceptors(Map<String, Interceptor> r1) {
        this.interceptors = r1;
    }

    public void setPackageImports(HashSet<String> r1) {
        this.packageImports = r1;
    }
}
