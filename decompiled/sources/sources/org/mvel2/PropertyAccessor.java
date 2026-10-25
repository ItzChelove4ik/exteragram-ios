package org.mvel2;

import c.f$$ExternalSyntheticBUOutline2;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.internal.url._UrlKt;
import org.mvel2.ast.FunctionInstance;
import org.mvel2.ast.InvokationContextFactory;
import org.mvel2.ast.Proto;
import org.mvel2.ast.PrototypalFunctionInstance;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.TypeDescriptor;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.integration.GlobalListenerFactory;
import org.mvel2.integration.PropertyHandlerFactory;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.MethodStub;
import org.mvel2.util.ParseTools;
import org.mvel2.util.PropertyTools;
import org.mvel2.util.ReflectionUtil;
import org.mvel2.util.StringAppender;
import org.mvel2.util.Varargs;

/* loaded from: classes5.dex */
public class PropertyAccessor {
    private static final int COL = 2;
    private static final Object[] EMPTYARG = null;
    private static final int METH = 1;
    private static final Map<Member, WeakReference<Class[]>> METHOD_PARMTYPES_CACHE = null;
    private static final Map<Class, WeakHashMap<Integer, WeakReference<Object[]>>> METHOD_RESOLVER_CACHE = null;
    private static final int NORM = 0;
    private static final Map<Class, WeakHashMap<Integer, WeakReference<Member>>> READ_PROPERTY_RESOLVER_CACHE = null;
    private static final int WITH = 3;
    private static final Map<Class, WeakHashMap<Integer, WeakReference<Member>>> WRITE_PROPERTY_RESOLVER_CACHE = null;
    private Object ctx;
    private Object curr;
    private Class currType;
    private int cursor;
    private int end;
    private boolean first;
    private int length;
    private boolean nullHandle;
    private ParserContext pCtx;
    private char[] property;
    private int st;
    private int start;
    private Object thisReference;
    private VariableResolverFactory variableFactory;

    static {
        EMPTYARG = new Object[0];
        READ_PROPERTY_RESOLVER_CACHE = Collections.synchronizedMap(new WeakHashMap(10));
        WRITE_PROPERTY_RESOLVER_CACHE = Collections.synchronizedMap(new WeakHashMap(10));
        METHOD_RESOLVER_CACHE = Collections.synchronizedMap(new WeakHashMap(10));
        METHOD_PARMTYPES_CACHE = Collections.synchronizedMap(new WeakHashMap(10));
    }

    public PropertyAccessor(String r3, Object r4) {
        this.start = 0;
        this.cursor = 0;
        this.currType = null;
        this.first = true;
        this.nullHandle = false;
        char[] r32 = r3.toCharArray();
        this.property = r32;
        int r33 = r32.length;
        this.end = r33;
        this.length = r33;
        this.ctx = r4;
        this.variableFactory = new ImmutableDefaultFactory();
    }

    public PropertyAccessor(char[] r2, int r3, int r4, Object r5, VariableResolverFactory r6, Object r7, ParserContext r8) {
        this.currType = null;
        this.first = true;
        this.nullHandle = false;
        this.property = r2;
        this.start = r3;
        this.st = r3;
        this.cursor = r3;
        this.length = r4;
        this.end = r3 + r4;
        this.ctx = r5;
        this.variableFactory = r6;
        this.thisReference = r7;
        this.pCtx = r8;
    }

    public PropertyAccessor(char[] r3, Object r4, VariableResolverFactory r5, Object r6, ParserContext r7) {
        this.start = 0;
        this.cursor = 0;
        this.currType = null;
        this.first = true;
        this.nullHandle = false;
        this.property = r3;
        int r32 = r3.length;
        this.end = r32;
        this.length = r32;
        this.ctx = r4;
        this.variableFactory = r5;
        this.thisReference = r6;
        this.pCtx = r7;
    }

    private static void addMethodCache(Class r3, Integer r4, Method r5) {
        Map<Class, WeakHashMap<Integer, WeakReference<Object[]>>> r0 = METHOD_RESOLVER_CACHE;
        monitor-enter(r0);
        WeakHashMap<Integer, WeakReference<Object[]>> r1 = r0.get(r3);     // Catch: Throwable -> L7
        if (r1 != null) goto L9;
        r1 = new WeakHashMap();     // Catch: Throwable -> L7
        r0.put(r3, r1);     // Catch: Throwable -> L7
    L9:
        r1.put(r4, new WeakReference(new Object[]{r5, r5.getParameterTypes()}));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static void addReadCache(Class r2, Integer r3, Member r4) {
        Map<Class, WeakHashMap<Integer, WeakReference<Member>>> r0 = READ_PROPERTY_RESOLVER_CACHE;
        monitor-enter(r0);
        WeakHashMap<Integer, WeakReference<Member>> r1 = r0.get(r2);     // Catch: Throwable -> L7
        if (r1 != null) goto L9;
        r1 = new WeakHashMap();     // Catch: Throwable -> L7
        r0.put(r2, r1);     // Catch: Throwable -> L7
    L9:
        r1.put(r3, new WeakReference(r4));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private static void addWriteCache(Class r2, Integer r3, Member r4) {
        Map<Class, WeakHashMap<Integer, WeakReference<Member>>> r0 = WRITE_PROPERTY_RESOLVER_CACHE;
        monitor-enter(r0);
        WeakHashMap<Integer, WeakReference<Member>> r1 = r0.get(r2);     // Catch: Throwable -> L7
        if (r1 != null) goto L9;
        r1 = new WeakHashMap();     // Catch: Throwable -> L7
        r0.put(r2, r1);     // Catch: Throwable -> L7
    L9:
        r1.put(r3, new WeakReference(r4));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private String capture() {
        return new String(this.property, this.st, trimLeft(this.cursor) - this.st);
    }

    private static Object[] checkMethodCache(Class r1, Integer r2) {
        WeakHashMap<Integer, WeakReference<Object[]>> r12 = METHOD_RESOLVER_CACHE.get(r1);
        if (r12 == null) goto L8;
        WeakReference<Object[]> r13 = r12.get(r2);
        if (r13 != null) goto L7;
        return null;
    L7:
        return r13.get();
    L8:
        return null;
    }

    public static Class[] checkParmTypesCache(Method r3) {
        Map<Member, WeakReference<Class[]>> r0 = METHOD_PARMTYPES_CACHE;
        WeakReference<Class[]> r1 = r0.get(r3);
        if (r1 == null) goto L8;
        Class[] r12 = r1.get();
        if (r12 == null) goto L8;
        return r12;
    L8:
        Class<?>[] r2 = r3.getParameterTypes();
        r0.put(r3, new WeakReference(r2));
        return r2;
    }

    private static Member checkReadCache(Class r1, Integer r2) {
        WeakHashMap<Integer, WeakReference<Member>> r12 = READ_PROPERTY_RESOLVER_CACHE.get(r1);
        if (r12 == null) goto L8;
        WeakReference<Member> r13 = r12.get(r2);
        if (r13 != null) goto L7;
        return null;
    L7:
        return r13.get();
    L8:
        return null;
    }

    private static Member checkWriteCache(Class r1, Integer r2) {
        WeakHashMap<Integer, WeakReference<Member>> r12 = WRITE_PROPERTY_RESOLVER_CACHE.get(r1);
        if (r12 == null) goto L8;
        WeakReference<Member> r13 = r12.get(r2);
        if (r13 != null) goto L7;
        return null;
    L7:
        return r13.get();
    L8:
        return null;
    }

    public static void clearPropertyResolverCache() {
        READ_PROPERTY_RESOLVER_CACHE.clear();
        WRITE_PROPERTY_RESOLVER_CACHE.clear();
        METHOD_RESOLVER_CACHE.clear();
    }

    private static int createSignature(String r0, String r1) {
        return r0.hashCode() + r1.hashCode();
    }

    private Object get() {
        this.curr = this.ctx;
    L15:
        e = move-exception;
        throw new PropertyAccessException("could not access property", this.property, this.cursor, e, this.pCtx);
    L13:
        e = move-exception;
        int r0 = this.cursor;
        int r1 = this.length;
        if (r0 < r1) goto L31;
        this.cursor = r1 - 1;
    L31:
        throw new PropertyAccessException("array or collections index out of bounds in property: ".concat(new String(this.property, this.cursor, r1)), this.property, this.cursor, e, this.pCtx);
    L9:
        e = move-exception;
        throw new PropertyAccessException("null pointer exception in property: ".concat(new String(this.property)), this.property, this.cursor, e, this.pCtx);
    L17:
        e = move-exception;
        throw new PropertyAccessException("could not access property", this.property, this.cursor, e, this.pCtx);
    L11:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, this.property, this.st);
    L7:
        e = move-exception;
        throw new PropertyAccessException("unknown exception in expression: ".concat(new String(this.property)), this.property, this.cursor, e, this.pCtx);
    L4:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L20;
        return getNormal();
    L20:
        return getAllowOverride();
    }

    public static Object get(String r1, Object r2) {
        return new PropertyAccessor(r1, r2).get();
    }

    public static Object get(String r6, Object r7, VariableResolverFactory r8, Object r9, ParserContext r10) {
        return new PropertyAccessor(r6.toCharArray(), r7, r8, r9, r10).get();
    }

    public static Object get(char[] r8, int r9, int r10, Object r11, VariableResolverFactory r12, Object r13, ParserContext r14) {
        return new PropertyAccessor(r8, r9, r10, r11, r12, r13, r14).get();
    }

    private Object getAllowOverride() {
    L3:
        if (this.cursor >= this.end) goto L37;
        int r0 = nextToken();
        if (r0 != 0) goto L7;
        Object r02 = getBeanPropertyAO(this.curr, capture());
        this.curr = r02;
        if (r02 == null) goto L23;
    L25:
        boolean r03 = this.nullHandle;
        Object r1 = this.curr;
        if (r03 == false) goto L30;
        if (r1 == null) goto L28;
        this.nullHandle = false;
    L35:
        this.first = false;
        goto L3
    L28:
        return null;
    L30:
        if (r1 != null) goto L35;
        if (this.cursor >= this.end) goto L35;
        throw null;
    L23:
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L25;
        this.curr = PropertyHandlerFactory.getNullPropertyHandler().getProperty(capture(), this.ctx, this.variableFactory);
        goto L25
    L7:
        if (r0 != 1) goto L9;
        Object r04 = getMethod(this.curr, capture());
        this.curr = r04;
        if (r04 != null) goto L25;
        if (PropertyHandlerFactory.hasNullMethodHandler() == false) goto L25;
        this.curr = PropertyHandlerFactory.getNullMethodHandler().getProperty(capture(), this.ctx, this.variableFactory);
        goto L25
    L9:
        if (r0 != 2) goto L11;
        this.curr = getCollectionPropertyAO(this.curr, capture());
        goto L25
    L11:
        if (r0 != 3) goto L25;
        this.curr = getWithProperty(this.curr);
        goto L25
    L37:
        return this.curr;
    }

    private Object getBeanProperty(Object r8, String r9) {
        if (this.first == false) goto L19;
        if ("this".equals(r9) == false) goto L9;
        return this.ctx;
    L9:
        if (AbstractParser.LITERALS.containsKey(r9) == true) goto L11;
        VariableResolverFactory r1 = this.variableFactory;
        if (r1 == null) goto L19;
        if (r1.isResolveable(r9) == false) goto L19;
        return this.variableFactory.getVariableResolver(r9).getValue();
    L11:
        return AbstractParser.LITERALS.get(r9);
    L19:
        if (r8 == null) goto L109;
        boolean r2 = r8 instanceof Class;
        if (r2 == true) goto L23;
        Class<?> r3 = r8.getClass();
    L29:
        Member r4 = checkReadCache(r3, Integer.valueOf(r9.hashCode()));
        if (r4 != null) goto L32;
        Integer r42 = Integer.valueOf(r9.hashCode());
        Member r5 = PropertyTools.getFieldOrAccessor(r3, r9);
        addReadCache(r3, r42, r5);
        r4 = r5;
    L32:
        int r6 = 0;
        if ((r4 instanceof Method) == false) goto L60;
        Method r12 = (Method) r4;
        return r12.invoke(r8, EMPTYARG);
    L43:
        Method r7 = ParseTools.determineActualTargetMethod(r12);
        if (r7 != null) goto L46;
        monitor-enter(r4);
        r12.setAccessible(true);     // Catch: Throwable -> L55
        Object r72 = r12.invoke(r8, EMPTYARG);     // Catch: Throwable -> L55
        r12.setAccessible(false);     // Catch: Throwable -> L53
        return r72;
    L53:
        th = move-exception;
        throw th;
    L55:
        th = move-exception;
        r12.setAccessible(false);     // Catch: Throwable -> L53
        throw th;     // Catch: Throwable -> L53
    L46:
        return r7.invoke(r8, EMPTYARG);
    L37:
        e = move-exception;
        if (r4.getDeclaringClass().equals(r8) == true) goto L137;
    L42:
        throw e;
    L137:
        throw new CompileException("name collision between innerclass: " + Class.forName(r4.getDeclaringClass().getName() + "$" + r9).getCanonicalName() + "; and bean accessor: " + r9 + " (" + r4.toString() + ")", this.property, this.st);     // Catch: ClassNotFoundException -> L135
    L60:
        if (r4 == null) goto L64;
        Field r43 = (Field) r4;
        this.currType = ReflectionUtil.toNonPrimitiveType(r43.getType());
        return r43.get(r8);
    L64:
        if ((r8 instanceof Map) == false) goto L76;
        Map r0 = (Map) r8;
        if (r0.containsKey(r9) == true) goto L70;
        if (this.nullHandle == false) goto L76;
    L70:
        if ((r8 instanceof Proto.ProtoInstance) == false) goto L74;
        return ((Proto.ProtoInstance) r8).get2(r9).call(null, this.thisReference, this.variableFactory, ParseTools.EMPTY_OBJ_ARR);
    L74:
        return r0.get(r9);
    L76:
        if ("length".equals(r9) == true) goto L78;
    L81:
        if (r2 == false) goto L102;
        Class r02 = (Class) r8;
        Method[] r22 = r02.getMethods();
        int r32 = r22.length;
    L83:
        if (r6 >= r32) goto L139;
        Method r44 = r22[r6];
        if (r9.equals(r44.getName()) == true) goto L86;
        r6 = r6 + 1;
        goto L83
    L86:
        ParserContext r92 = this.pCtx;
        if (r92 == null) goto L94;
        if (r92.getParserConfiguration() == null) goto L94;
        if (this.pCtx.getParserConfiguration().isAllowNakedMethCall() == true) goto L96;
    L97:
        return r44;
    L96:
        return r44.invoke(r8, ParseTools.EMPTY_OBJ_ARR);
    L94:
        if (MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL == false) goto L97;
    L139:
        return ParseTools.findClass(this.variableFactory, r02.getName() + "$" + r9, this.pCtx);
    L102:
        if (PropertyHandlerFactory.hasPropertyHandler(r3) == false) goto L106;
        return PropertyHandlerFactory.getPropertyHandler(r3).getProperty(r9, r8, this.variableFactory);
    L106:
        if ((r8 instanceof FunctionInstance) == false) goto L109;
        return ((PrototypalFunctionInstance) r8).getResolverFactory().getVariableResolver(r9).getValue();
    L78:
        if (r8.getClass().isArray() == false) goto L81;
        return Integer.valueOf(Array.getLength(r8));
    L23:
        if (MVEL.COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS == true) goto L25;
    L27:
        r3 = (Class) r8;
        goto L29
    L25:
        if ("class".equals(r9) == false) goto L27;
        return r8;
    L109:
        Object r03 = this.tryStaticAccess();
        if (r03 != null) goto L112;
        ParserContext r04 = this.pCtx;
        if (r04 == null) goto L127;
        if (r04.getParserConfiguration() == null) goto L127;
        if (this.pCtx.getParserConfiguration().isAllowNakedMethCall() == true) goto L129;
    L130:
        if (r8 != null) goto L134;
        throw new PropertyAccessException("unresolvable property or identifier: " + r9, this.property, this.st, this.pCtx);
    L134:
        throw new PropertyAccessException("could not access: " + r9 + "; in class: " + r8.getClass().getName(), this.property, this.st, this.pCtx);
    L129:
        return this.getMethod(r8, r9);
    L127:
        if (MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL == false) goto L130;
    L112:
        if ((r03 instanceof Class) == false) goto L114;
    L118:
        return r03;
    L114:
        if ((r03 instanceof Method) == true) goto L118;
        return ((Field) r03).get(null);
    }

    private Object getBeanPropertyAO(Object r2, String r3) {
        if (r2 != null) goto L4;
    L7:
        GlobalListenerFactory.notifyGetListeners(r2, r3, this.variableFactory);
        return getBeanProperty(r2, r3);
    L4:
        if (PropertyHandlerFactory.hasPropertyHandler(r2.getClass()) == false) goto L7;
        return PropertyHandlerFactory.getPropertyHandler(r2.getClass()).getProperty(r3, r2, this.variableFactory);
    }

    private ClassLoader getClassLoader() {
        ParserContext r0 = this.pCtx;
        if (r0 == null) goto L7;
        return r0.getClassLoader();
    L7:
        return Thread.currentThread().getContextClassLoader();
    }

    private Object getCollectionProperty(Object r7, String r8) {
        if (r8.length() == 0) goto L9;
        r7 = getBeanProperty(r7, r8);
        if (r7 != null) goto L9;
        f$$ExternalSyntheticBUOutline2.m("null pointer on indexed access for: ".concat(r8));
        return null;
    L9:
        this.currType = null;
        int r82 = this.cursor + 1;
        this.cursor = r82;
        whiteSpaceSkip();
        if (this.cursor == this.end) goto L46;
        if (scanTo(']') == true) goto L46;
        char[] r1 = this.property;
        int r2 = this.cursor;
        this.cursor = r2 + 1;
        String r0 = new String(r1, r82, r2 - r82);
        if ((r7 instanceof Map) == false) goto L18;
        return ((Map) r7).get(MVEL.eval(r0, r7, this.variableFactory));
    L18:
        if ((r7 instanceof List) == true) goto L20;
        int r12 = 0;
        if ((r7 instanceof Collection) == false) goto L33;
        int r83 = ((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue();
        Collection r72 = (Collection) r7;
        if (r83 > r72.size()) goto L31;
        Iterator r6 = r72.iterator();
    L26:
        if (r12 >= r83) goto L29;
        r6.next();
        r12 = r12 + 1;
        goto L26
    L29:
        return r6.next();
    L31:
        throw new PropertyAccessException("index [" + r83 + "] out of bounds on collections", this.property, this.cursor, this.pCtx);
    L33:
        if (r7.getClass().isArray() == false) goto L37;
        return Array.get(r7, ((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue());
    L37:
        if ((r7 instanceof CharSequence) == true) goto L39;
        return TypeDescriptor.getClassReference(this.pCtx, (Class) r7, new TypeDescriptor(this.property, this.start, this.length, 0));
    L42:
        e = move-exception;
        throw new PropertyAccessException("illegal use of []: unknown type: ".concat(r7.getClass().getName()), this.property, this.st, e, this.pCtx);
    L39:
        return Character.valueOf(((CharSequence) r7).charAt(((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue()));
    L20:
        return ((List) r7).get(((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue());
    L46:
        throw new PropertyAccessException("unterminated '['", this.property, this.cursor, this.pCtx);
    }

    private Object getCollectionPropertyAO(Object r7, String r8) {
        if (r8.length() == 0) goto L5;
        r7 = getBeanProperty(r7, r8);
    L5:
        this.currType = null;
        if (r7 != null) goto L8;
        return null;
    L8:
        int r82 = this.cursor + 1;
        this.cursor = r82;
        whiteSpaceSkip();
        if (this.cursor == this.end) goto L64;
        if (scanTo(']') == true) goto L64;
        char[] r1 = this.property;
        int r2 = this.cursor;
        this.cursor = r2 + 1;
        String r0 = new String(r1, r82, r2 - r82);
        if ((r7 instanceof Map) == false) goto L21;
        if (PropertyHandlerFactory.hasPropertyHandler(Map.class) == false) goto L19;
        return PropertyHandlerFactory.getPropertyHandler(Map.class).getProperty(r0, r7, this.variableFactory);
    L19:
        return ((Map) r7).get(MVEL.eval(r0, r7, this.variableFactory));
    L21:
        if ((r7 instanceof List) == true) goto L23;
        int r12 = 0;
        if ((r7 instanceof Collection) == false) goto L44;
        if (PropertyHandlerFactory.hasPropertyHandler(Collection.class) == true) goto L33;
        int r83 = ((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue();
        Collection r72 = (Collection) r7;
        if (r83 > r72.size()) goto L42;
        Iterator r6 = r72.iterator();
    L37:
        if (r12 >= r83) goto L40;
        r6.next();
        r12 = r12 + 1;
        goto L37
    L40:
        return r6.next();
    L42:
        throw new PropertyAccessException("index [" + r83 + "] out of bounds on collections", this.property, this.cursor, this.pCtx);
    L33:
        return PropertyHandlerFactory.getPropertyHandler(Collection.class).getProperty(r0, r7, this.variableFactory);
    L44:
        if (r7.getClass().isArray() == false) goto L52;
        if (PropertyHandlerFactory.hasPropertyHandler(Array.class) == false) goto L50;
        return PropertyHandlerFactory.getPropertyHandler(Array.class).getProperty(r0, r7, this.variableFactory);
    L50:
        return Array.get(r7, ((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue());
    L52:
        if ((r7 instanceof CharSequence) == true) goto L54;
        char[] r3 = this.property;     // Catch: Exception -> L61
        int r4 = this.start;     // Catch: Exception -> L61
        return TypeDescriptor.getClassReference(this.pCtx, (Class) r7, new TypeDescriptor(r3, r4, this.end - r4, 0));
    L62:
        throw new PropertyAccessException("illegal use of []: unknown type: ".concat(r7.getClass().getName()), this.property, this.st, this.pCtx);
    L54:
        if (PropertyHandlerFactory.hasPropertyHandler(CharSequence.class) == false) goto L58;
        return PropertyHandlerFactory.getPropertyHandler(CharSequence.class).getProperty(r0, r7, this.variableFactory);
    L58:
        return Character.valueOf(((CharSequence) r7).charAt(((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue()));
    L23:
        if (PropertyHandlerFactory.hasPropertyHandler(List.class) == false) goto L27;
        return PropertyHandlerFactory.getPropertyHandler(List.class).getProperty(r0, r7, this.variableFactory);
    L27:
        return ((List) r7).get(((Integer) MVEL.eval(r0, r7, this.variableFactory)).intValue());
    L64:
        throw new PropertyAccessException("unterminated '['", this.property, this.cursor, this.pCtx);
    }

    private Object getMethod(Object r13, String r14) {
        int r0 = this.cursor;
        if (r0 == this.end) goto L9;
        char[] r2 = this.property;
        if (r2[r0] != '(') goto L9;
        int r22 = ParseTools.balancedCapture(r2, r0, '(');
        this.cursor = r22;
        if ((r22 - r0) <= 1) goto L9;
        String r4 = new String(this.property, r0 + 1, (r22 - r0) - 1);
    L10:
        this.cursor++;
        int r23 = 0;
        if (r4.length() != 0) goto L13;
        Object[] r02 = ParseTools.EMPTY_OBJ_ARR;
    L18:
        String r6 = null;
        if (this.first == false) goto L40;
        VariableResolverFactory r5 = this.variableFactory;
        if (r5 == null) goto L40;
        if (r5.isResolveable(r14) == false) goto L40;
        Object r52 = this.variableFactory.getVariableResolver(r14).getValue();
        if ((r52 instanceof Method) == false) goto L28;
        Method r53 = (Method) r52;
        r13 = r53.getDeclaringClass();
        r14 = r53.getName();
    L30:
        this.first = false;
        goto L40
    L28:
        if ((r52 instanceof MethodStub) == false) goto L32;
        MethodStub r54 = (MethodStub) r52;
        r13 = r54.getClassReference();
        r14 = r54.getMethodName();
        goto L30
    L32:
        if ((r52 instanceof FunctionInstance) == false) goto L35;
        FunctionInstance r55 = (FunctionInstance) r52;
        r55.getFunction().checkArgumentCount(r02.length);
        return r55.call(null, this.thisReference, this.variableFactory, r02);
    L35:
        StringBuilder r03 = new StringBuilder("attempt to optimize a method call for a reference that does not point to a method: ");
        r03.append(r14);
        r03.append(" (reference is type: ");
        if (r13 == null) goto L38;
        r6 = r13.getClass().getName();
    L38:
        r03.append(r6);
        r03.append(")");
        throw new OptimizationFailure(r03.toString());
    L40:
        if (r13 == null) goto L116;
        boolean r56 = r13 instanceof Class;
        Class<?> r7 = this.currType;
        if (r7 != null) goto L47;
        if (r56 == false) goto L46;
        r7 = (Class) r13;
        goto L47
    L46:
        r7 = r13.getClass();
    L47:
        this.currType = null;
        if (r7 == Proto.ProtoInstance.class) goto L50;
        Object[] r8 = checkMethodCache(r7, Integer.valueOf(createSignature(r14, r4)));
        if (r8 == null) goto L54;
        Method r9 = (Method) r8[0];
        Class<?>[] r82 = (Class[]) r8[1];
    L55:
        if (r9 != null) goto L64;
        r9 = ParseTools.getBestCandidate(r02, r14, r7, r7.getMethods(), false);
        if (r9 == null) goto L59;
        addMethodCache(r7, Integer.valueOf(createSignature(r14, r4)), r9);
        r82 = r9.getParameterTypes();
    L59:
        if (r9 != null) goto L64;
        if (r56 == false) goto L64;
        r9 = ParseTools.getBestCandidate(r02, r14, r7, r7.getDeclaredMethods(), false);
        if (r9 == null) goto L64;
        addMethodCache(r7, Integer.valueOf(createSignature(r14, r4)), r9);
        r82 = r9.getParameterTypes();
    L64:
        if (r9 != null) goto L72;
        if (r7 == r13.getClass()) goto L72;
        if (r56 == true) goto L72;
        r7 = r13.getClass();
        r9 = ParseTools.getBestCandidate(r02, r14, r7, r7.getDeclaredMethods(), false);
        if (r9 == null) goto L72;
        addMethodCache(r7, Integer.valueOf(createSignature(r14, r4)), r9);
        r82 = r9.getParameterTypes();
    L72:
        if ((r13 instanceof PrototypalFunctionInstance) == false) goto L77;
        VariableResolverFactory r57 = ((PrototypalFunctionInstance) r13).getResolverFactory();
        Object r10 = r57.getVariableResolver(r14).getValue();
        if ((r10 instanceof PrototypalFunctionInstance) == false) goto L77;
        return ((PrototypalFunctionInstance) r10).call(r13, this.thisReference, new InvokationContextFactory(this.variableFactory, r57), r02);
    L77:
        if (r9 != null) goto L100;
        StringAppender r1 = new StringAppender();
    L80:
        if (r23 >= r02.length) goto L90;
        Object r42 = r02[r23];
        if (r42 == null) goto L84;
        String r43 = r42.getClass().getName();
    L85:
        r1.append(r43);
        if (r23 >= (r02.length - 1)) goto L88;
        r1.append(", ");
    L88:
        r23 = r23 + 1;
        goto L80
    L84:
        r43 = null;
        goto L85
    L90:
        if ("size".equals(r14) == false) goto L98;
        if (r02.length != 0) goto L98;
        if (r7.isArray() == false) goto L98;
        return Integer.valueOf(Array.getLength(r13));
    L98:
        throw new PropertyAccessException("unable to resolve method: " + r7.getName() + "." + r14 + "(" + r1.toString() + ") [arglength=" + r02.length + "]", this.property, this.st, this.pCtx);
    L100:
        if (r23 >= r02.length) goto L102;
        r02[r23] = DataConversion.convert(r02[r23], Varargs.paramTypeVarArgsSafe(r82, r23, r9.isVarArgs()));
        r23 = r23 + 1;
        goto L100
    L102:
        this.currType = ReflectionUtil.toNonPrimitiveType(r9.getReturnType());
        return r9.invoke(r13, Varargs.normalizeArgsForVarArgs(r82, r02, r9.isVarArgs()));
    L111:
        Integer r24 = Integer.valueOf(createSignature(r14, r4));     // Catch: Exception -> L113
        Method r3 = ParseTools.getWidenedTarget(r9);     // Catch: Exception -> L113
        addMethodCache(r7, r24, r3);     // Catch: Exception -> L113
        return r3.invoke(r13, r02);
    L108:
        e = move-exception;
        throw e;
    L105:
        e = move-exception;
        throw new PropertyAccessException("unable to invoke method: " + r14, this.property, this.cursor, e, this.pCtx);
    L113:
        e = move-exception;
        throw new PropertyAccessException("unable to invoke method: " + r14, this.property, this.cursor, e, this.pCtx);
    L54:
        r82 = null;
        r9 = null;
        goto L55
    L50:
        return ((Proto.ProtoInstance) r13).get2(r14).call(null, this.thisReference, this.variableFactory, r02);
    L116:
        Sign$$ExternalSyntheticBUOutline0.m("no such method or function: " + r14, this.property, this.cursor);
        return null;
    L13:
        List<char[]> r04 = ParseTools.parseParameterList(r4.toCharArray(), 0, -1);
        Object[] r58 = new Object[r04.size()];
        int r62 = 0;
    L15:
        if (r62 >= r04.size()) goto L17;
        r58[r62] = MVEL.eval(r04.get(r62), this.thisReference, this.variableFactory);
        r62 = r62 + 1;
        goto L15
    L17:
        r02 = r58;
    L9:
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    private Object getNormal() {
    L3:
        if (this.cursor >= this.end) goto L26;
        int r0 = nextToken();
        if (r0 != 0) goto L7;
        this.curr = getBeanProperty(this.curr, capture());
    L18:
        if (this.nullHandle == false) goto L24;
        if (this.curr == null) goto L21;
        this.nullHandle = false;
        goto L24
    L21:
        return null;
    L24:
        this.first = false;
        goto L3
    L7:
        if (r0 != 1) goto L9;
        this.curr = getMethod(this.curr, capture());
        goto L18
    L9:
        if (r0 != 2) goto L11;
        this.curr = getCollectionProperty(this.curr, capture());
        goto L18
    L11:
        if (r0 != 3) goto L18;
        this.curr = getWithProperty(this.curr);
        goto L18
    L26:
        return this.curr;
    }

    private Object getWithProperty(Object r8) {
        int r0 = this.start;
        if (r0 != this.cursor) goto L6;
        String r02 = null;
    L5:
        String r1 = r02;
        char[] r2 = this.property;
        int r03 = this.cursor;
        int r3 = r03 + 1;
        int r04 = ParseTools.balancedCaptureWithLineAccounting(r2, r03, this.end, '{', this.pCtx);
        this.cursor = r04;
        ParseTools.parseWithExpressions(r1, r2, r3, r04 - r3, r8, this.variableFactory);
        this.cursor++;
        return r8;
    L6:
        r02 = new String(this.property, r0, (r1 - r0) - 1).trim();
        goto L5
    }

    private int nextToken() {
        char[] r0 = this.property;
        int r1 = this.cursor;
        this.st = r1;
        char r2 = r0[r1];
        if (r2 == '.') goto L12;
        if (r2 == '?') goto L26;
        if (r2 == '[') goto L11;
        if (r2 != '{') goto L28;
        if (r0[r1 - 1] != '.') goto L28;
        return 3;
    L28:
        int r02 = this.cursor;
        if (r02 >= this.end) goto L33;
        if (ParseTools.isWhitespace(this.property[r02]) == false) goto L33;
        this.cursor++;
    L33:
        int r03 = this.cursor;
        if (r03 >= this.end) goto L38;
        if (this.property[r03] != '.') goto L38;
        this.cursor = r03 + 1;
    L38:
        this.st = r03;
    L39:
        int r04 = this.cursor + 1;
        this.cursor = r04;
        if (r04 >= this.end) goto L45;
        if (Character.isJavaIdentifierPart(this.property[r04]) == true) goto L39;
    L45:
        if (this.cursor < this.end) goto L47;
        return 0;
    L47:
        if (ParseTools.isWhitespace(this.property[this.cursor]) == false) goto L49;
        this.cursor++;
        goto L47
    L49:
        char r10 = this.property[this.cursor];
        if (r10 == '(') goto L54;
        if (r10 == '[') goto L53;
        return 0;
    L53:
        return 2;
    L54:
        return 1;
    L11:
        return 2;
    L26:
        if (this.cursor != this.start) goto L28;
        int r05 = this.st + 1;
        this.st = r05;
        this.cursor = r05;
        this.nullHandle = true;
    L12:
        int r06 = this.cursor;
        if (r06 >= this.end) goto L17;
        if (ParseTools.isWhitespace(this.property[r06]) == false) goto L17;
        this.cursor++;
    L17:
        int r07 = this.st;
        if ((r07 + 1) == this.end) goto L26;
        char[] r12 = this.property;
        int r22 = r07 + 1;
        this.st = r22;
        this.cursor = r22;
        char r13 = r12[r22];
        if (r13 == '?') goto L24;
        if (r13 != '{') goto L26;
        return 3;
    L24:
        int r08 = r07 + 2;
        this.st = r08;
        this.cursor = r08;
        this.nullHandle = true;
        goto L26
    }

    public static void reportCacheSizes() {
        PrintStream r0 = System.out;
        StringBuilder r1 = new StringBuilder("read property cache: ");
        Map<Class, WeakHashMap<Integer, WeakReference<Member>>> r2 = READ_PROPERTY_RESOLVER_CACHE;
        r1.append(r2.size());
        r0.println(r1.toString());
        Iterator<Class> r02 = r2.keySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        Class r12 = r02.next();
        System.out.println(" [" + r12.getName() + "]: " + READ_PROPERTY_RESOLVER_CACHE.get(r12).size() + " entries.");
        goto L4
    L6:
        PrintStream r03 = System.out;
        StringBuilder r13 = new StringBuilder("write property cache: ");
        Map<Class, WeakHashMap<Integer, WeakReference<Member>>> r5 = WRITE_PROPERTY_RESOLVER_CACHE;
        r13.append(r5.size());
        r03.println(r13.toString());
        Iterator<Class> r04 = r5.keySet().iterator();
    L8:
        if (r04.hasNext() == false) goto L10;
        Class r14 = r04.next();
        System.out.println(" [" + r14.getName() + "]: " + WRITE_PROPERTY_RESOLVER_CACHE.get(r14).size() + " entries.");
        goto L8
    L10:
        PrintStream r05 = System.out;
        StringBuilder r15 = new StringBuilder("method cache: ");
        Map<Class, WeakHashMap<Integer, WeakReference<Object[]>>> r52 = METHOD_RESOLVER_CACHE;
        r15.append(r52.size());
        r05.println(r15.toString());
        Iterator<Class> r06 = r52.keySet().iterator();
    L12:
        if (r06.hasNext() == false) goto L14;
        Class r16 = r06.next();
        System.out.println(" [" + r16.getName() + "]: " + METHOD_RESOLVER_CACHE.get(r16).size() + " entries.");
        goto L12
    }

    private boolean scanTo(char r6) {
    L2:
        int r0 = this.cursor;
        int r1 = this.end;
        if (r0 >= r1) goto L15;
        char[] r2 = this.property;
        char r3 = r2[r0];
        if (r3 != '\"') goto L7;
    L9:
        this.cursor = ParseTools.captureStringLiteral(r3, r2, r0, r1);
    L10:
        char[] r02 = this.property;
        int r12 = this.cursor;
        if (r02[r12] == r6) goto L12;
        this.cursor = r12 + 1;
        goto L2
    L12:
        return false;
    L7:
        if (r3 == '\'') goto L9;
    L15:
        return true;
    }

    private void set(Object r10) {
        this.curr = this.ctx;
        int r5 = this.end;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        this.end = ParseTools.findAbsoluteLast(this.property);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        Object r6 = get();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        this.curr = r6;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (r6 == null) goto L134;
        this.end = r5;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (nextToken() != 2) goto L70;
        int r3 = this.cursor + 1;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        this.cursor = r3;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        whiteSpaceSkip();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (this.cursor == this.length) goto L68;
        if (scanTo(']') == true) goto L68;
        String r4 = new String(this.property, r3, this.cursor - r3);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L37;
        Object r0 = this.curr;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if ((r0 instanceof Map) == false) goto L23;
        ((Map) r0).put(MVEL.eval(r4, this.ctx, this.variableFactory), r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L23:
        if ((r0 instanceof List) == false) goto L26;
        ((List) r0).set(((Integer) MVEL.eval(r4, this.ctx, this.variableFactory, Integer.class)).intValue(), r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L26:
        boolean r02 = PropertyHandlerFactory.hasPropertyHandler(r0.getClass());     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        Object r1 = this.curr;
        if (r02 == false) goto L32;
        PropertyHandlerFactory.getPropertyHandler(r1.getClass()).setProperty(r4, this.ctx, this.variableFactory, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L32:
        if (r1.getClass().isArray() == false) goto L36;
        Array.set(this.curr, ((Integer) MVEL.eval(r4, this.ctx, this.variableFactory, Integer.class)).intValue(), DataConversion.convert(r10, ParseTools.getBaseComponentType(this.curr.getClass())));     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L36:
        throw new PropertyAccessException("cannot bind to collection property: " + new String(this.property) + ": not a recognized collection type: " + this.ctx.getClass(), this.property, this.cursor, this.pCtx);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L37:
        GlobalListenerFactory.notifySetListeners(this.ctx, r4, this.variableFactory, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        Object r32 = this.curr;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if ((r32 instanceof Map) == false) goto L46;
        if (PropertyHandlerFactory.hasPropertyHandler(Map.class) == false) goto L43;
        PropertyHandlerFactory.getPropertyHandler(Map.class).setProperty(r4, this.curr, this.variableFactory, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L43:
        ((Map) this.curr).put(MVEL.eval(r4, this.ctx, this.variableFactory), r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L46:
        if ((r32 instanceof List) == false) goto L54;
        if (PropertyHandlerFactory.hasPropertyHandler(List.class) == false) goto L51;
        PropertyHandlerFactory.getPropertyHandler(List.class).setProperty(r4, this.curr, this.variableFactory, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L51:
        ((List) this.curr).set(((Integer) MVEL.eval(r4, this.ctx, this.variableFactory, Integer.class)).intValue(), r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L54:
        if (r32.getClass().isArray() == false) goto L62;
        if (PropertyHandlerFactory.hasPropertyHandler(Array.class) == false) goto L59;
        PropertyHandlerFactory.getPropertyHandler(Array.class).setProperty(r4, this.curr, this.variableFactory, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L59:
        Array.set(this.curr, ((Integer) MVEL.eval(r4, this.ctx, this.variableFactory, Integer.class)).intValue(), DataConversion.convert(r10, ParseTools.getBaseComponentType(this.curr.getClass())));     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L62:
        if (PropertyHandlerFactory.hasPropertyHandler(this.curr.getClass()) == false) goto L66;
        PropertyHandlerFactory.getPropertyHandler(this.curr.getClass()).setProperty(r4, this.curr, this.variableFactory, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L66:
        throw new PropertyAccessException("cannot bind to collection property: " + new String(this.property) + ": not a recognized collection type: " + this.ctx.getClass(), this.property, this.cursor, this.pCtx);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L68:
        throw new PropertyAccessException("unterminated '['", this.property, this.cursor, this.pCtx);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L70:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L72;
    L75:
        String r03 = capture();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        Class<?> r12 = this.curr.getClass();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (r03 != null) goto L78;
        int r42 = 0;
    L79:
        Member r13 = checkWriteCache(r12, Integer.valueOf(r42));     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (r13 != null) goto L93;
        Class<?> r14 = this.curr.getClass();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (r03 == null) goto L84;
        int r43 = r03.hashCode();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L85:
        Integer r44 = Integer.valueOf(r43);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        Object r52 = this.curr;
        if (r10 == null) goto L89;
        Member r53 = PropertyTools.getFieldOrWriteAccessor(r52.getClass(), r03, r10.getClass());     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L90:
        addWriteCache(r14, r44, r53);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        r13 = r53;
        goto L93
    L89:
        r53 = PropertyTools.getFieldOrWriteAccessor(r52.getClass(), r03);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        goto L90
    L84:
        r43 = -1;
    L93:
        if ((r13 instanceof Method) == false) goto L106;
        Method r15 = (Method) r13;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        Class[] r04 = checkParmTypesCache(r15);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (r10 != null) goto L97;
    L104:
        r15.invoke(this.curr, new Object[]{r10});     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L97:
        if (r04[0].isAssignableFrom(r10.getClass()) == true) goto L104;
        if (DataConversion.canConvert(r04[0], r10.getClass()) == false) goto L103;
        r15.invoke(this.curr, new Object[]{DataConversion.convert(r10, r04[0])});     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L103:
        throw new CompileException("cannot convert type: " + r10.getClass() + ": to " + r15.getParameterTypes()[0], this.property, this.cursor);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L106:
        if (r13 == null) goto L119;
        Field r16 = (Field) r13;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (r10 != null) goto L110;
    L117:
        r16.set(this.curr, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L110:
        if (r16.getType().isAssignableFrom(r10.getClass()) == true) goto L117;
        if (DataConversion.canConvert(r16.getType(), r10.getClass()) == false) goto L116;
        r16.set(this.curr, DataConversion.convert(r10, r16.getType()));     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L116:
        throw new CompileException("cannot convert type: " + r10.getClass() + ": to " + r16.getType(), this.property, this.cursor);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L119:
        Object r17 = this.curr;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if ((r17 instanceof Map) == false) goto L124;
        ((Map) r17).put(MVEL.eval(r03, this.ctx, this.variableFactory), r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L124:
        if ((r17 instanceof FunctionInstance) == false) goto L127;
        ((PrototypalFunctionInstance) r17).getResolverFactory().getVariableResolver(r03).setValue(r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L127:
        StringBuilder r18 = new StringBuilder("could not access/write property (");     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        r18.append(r03);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        r18.append(") in: ");     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        Object r05 = this.curr;     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        if (r05 != null) goto L130;
        String r06 = "Unknown";
    L131:
        r18.append(r06);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        throw new PropertyAccessException(r18.toString(), this.property, this.cursor, this.pCtx);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L130:
        r06 = r05.getClass().getName();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        goto L131
    L78:
        r42 = r03.hashCode();     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        goto L79
    L72:
        if (PropertyHandlerFactory.hasPropertyHandler(this.curr.getClass()) == false) goto L75;
        PropertyHandlerFactory.getPropertyHandler(this.curr.getClass()).setProperty(capture(), this.curr, this.variableFactory, r10);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
        return;
    L134:
        throw new PropertyAccessException("cannot bind to null context: ".concat(new String(this.property, this.cursor, this.length)), this.property, this.cursor, this.pCtx);     // Catch: IllegalAccessException -> L18 InvocationTargetException -> L20
    L18:
        e = move-exception;
        throw new PropertyAccessException("could not access property", this.property, this.st, e, this.pCtx);
    L20:
        e = move-exception;
        throw new PropertyAccessException("could not access property", this.property, this.st, e, this.pCtx);
    }

    public static void set(Object r1, String r2, Object r3) {
        new PropertyAccessor(r2, r1).set(r3);
    }

    public static void set(Object r6, VariableResolverFactory r7, String r8, Object r9, ParserContext r10) {
        new PropertyAccessor(r8.toCharArray(), r6, r7, null, r10).set(r9);
    }

    private void whiteSpaceSkip() {
        if (this.cursor < this.end) goto L5;
        return;
    L5:
        if (ParseTools.isWhitespace(this.property[this.cursor]) == false) goto L13;
        int r0 = this.cursor + 1;
        this.cursor = r0;
        if (r0 < this.end) goto L5;
        return;
    }

    public int trimLeft(int r3) {
    L2:
        if (r3 <= 0) goto L6;
        if (ParseTools.isWhitespace(this.property[r3 - 1]) == false) goto L6;
        r3 = r3 - 1;
    L6:
        return r3;
    }

    public Object tryStaticAccess() {
        int r0 = this.cursor;
        int r2 = this.end;     // Catch: Exception -> L89
        int r3 = r2 - 1;
        int r4 = 0;
        boolean r5 = false;
    L4:
        int r6 = this.start;     // Catch: Exception -> L89
        if (r3 <= r6) goto L90;
        char[] r7 = this.property;     // Catch: Exception -> L89
        char r8 = r7[r3];     // Catch: Exception -> L89
        if (r8 != '\"') goto L9;
    L80:
        int r62 = r3 - 1;
        if (r62 <= 0) goto L79;
        char[] r72 = this.property;     // Catch: Exception -> L89
        if (r72[r62] != '\"') goto L87;
        if (r72[r3 - 2] != '\\') goto L79;
    L87:
        r3 = r62;
    L79:
        r3 = r62;
    L88:
        r3 = r3 - 1;
        goto L4
    L9:
        if (r8 == '\'') goto L71;
        int r13 = 1;
        if (r8 != ')') goto L13;
        int r32 = r3 - 1;
        int r22 = 1;
    L55:
        if (r32 <= 0) goto L70;
        if (r22 == 0) goto L70;
        char r52 = this.property[r32];     // Catch: Exception -> L89
        if (r52 == '\"') goto L63;
        switch(r52) {
            case 39: goto L63;
            case 40: goto L62;
            case 41: goto L61;
            default: goto L69;
        };     // Catch: Exception -> L89
    L61:
        r22 = r22 + 1;     // Catch: Exception -> L89
        goto L69
    L62:
        r22 = r22 - 1;
    L69:
        r32 = r32 - 1;
    L63:
        if (r32 <= 0) goto L69;
        char[] r63 = this.property;     // Catch: Exception -> L89
        if (r63[r32] == r52) goto L69;
        if (r63[r32 - 1] == '\\') goto L69;
        r32 = r32 - 1;
    L70:
        int r53 = r32;
        r3 = r32 + 1;     // Catch: Exception -> L89
        r2 = r53;
        r5 = true;
        goto L88
    L13:
        if (r8 != '.') goto L15;
        if (r5 == false) goto L92;
        r2 = r3;
        r5 = false;
        goto L88
    L92:
        this.cursor = r2;     // Catch: ClassNotFoundException -> L43 Exception -> L89
        String r54 = new String(r7, r6, r2 - r6);     // Catch: ClassNotFoundException -> L43 Exception -> L89
        if (MVEL.COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS == true) goto L39;
    L41:
        return getClassLoader().loadClass(r54);
    L39:
        if (r54.endsWith(".class") == false) goto L41;
        r54 = r54.substring(0, r54.length() - 6);     // Catch: ClassNotFoundException -> L43 Exception -> L89
    L43:
        ClassLoader r23 = this.getClassLoader();     // Catch: Exception -> L89
        char[] r64 = this.property;     // Catch: Exception -> L89
        int r73 = this.start;     // Catch: Exception -> L89
        Class<?> r24 = r23.loadClass(new String(r64, r73, r3 - r73));     // Catch: Exception -> L89
        String r55 = new String(this.property, r3 + 1, (this.end - r3) - 1);     // Catch: Exception -> L89
        return r24.getField(r55);
    L46:
        Method[] r25 = r24.getMethods();     // Catch: Exception -> L89
        int r33 = r25.length;     // Catch: Exception -> L89
    L47:
        if (r4 >= r33) goto L52;
        Method r65 = r25[r4];     // Catch: Exception -> L89
        if (r55.equals(r65.getName()) == true) goto L50;
        r4 = r4 + 1;     // Catch: Exception -> L89
        goto L47
    L50:
        return r65;
    L52:
        return null;
    L15:
        if (r8 != '}') goto L88;
    L17:
        r3 = r3 - 1;
        if (r3 <= 0) goto L88;
        if (r13 == 0) goto L88;
        char r74 = this.property[r3];     // Catch: Exception -> L89
        if (r74 == '\"') goto L29;
        if (r74 == '\'') goto L29;
        if (r74 != '{') goto L25;
        r13 = r13 - 1;
        goto L17
    L25:
        if (r74 != '}') goto L17;
        r13 = r13 + 1;     // Catch: Exception -> L89
    L29:
        if (r3 <= 0) goto L17;
        char[] r82 = this.property;     // Catch: Exception -> L89
        if (r82[r3] == r74) goto L17;
        if (r82[r3 - 1] == '\\') goto L17;
        r3 = r3 - 1;
    L71:
        r62 = r3 - 1;
        if (r62 <= 0) goto L79;
        char[] r75 = this.property;     // Catch: Exception -> L89
        if (r75[r62] != '\'') goto L78;
        if (r75[r3 - 2] != '\\') goto L79;
    L78:
        r3 = r62;
    L90:
        return null;
    L89:
        this.cursor = r0;
        goto L90
    }
}
