package com.exteragram.messenger.plugins.utils;

import androidx.annotation.Keep;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.Code;
import com.android.dx.Comparison;
import com.android.dx.DexMaker;
import com.android.dx.FieldId;
import com.android.dx.Label;
import com.android.dx.Local;
import com.android.dx.MethodId;
import com.android.dx.TypeId;
import com.android.tools.r8.RecordTag;
import com.chaquo.python.PyObject;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.utils.AppUtils;
import dalvik.system.InMemoryDexClassLoader;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Utilities;

/* loaded from: classes.dex */
public class ClassProxy {
    private static final Object[] EMPTY_ARGS = null;
    private static final String PYTHON_PEER_FIELD_NAME = "__extera_internal_python_peer__";
    private static final Object generatedClassLoaderLock = null;
    private static final ConcurrentHashMap<String, Serializable> mvelExpressionCache = null;
    private static volatile ClassLoader sharedGeneratedClassLoader;

    public interface DexMakerHook {
        void apply(DexMaker r1, TypeId<?> r2, TypeId<?> r3, List<TypeId<?>> r4);
    }

    public static final class FieldMethodSpec extends RecordTag {
        private final boolean getter;
        private final int modifiers;
        private final String name;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof FieldMethodSpec) == false) goto L12;
            FieldMethodSpec r32 = (FieldMethodSpec) r3;
            if (this.getter == r32.getter) goto L7;
            return false;
        L7:
            if (this.modifiers == r32.modifiers) goto L9;
            return false;
        L9:
            if (Objects.equals(this.name, r32.name) == false) goto L16;
            return true;
        L16:
            return false;
        L12:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.name, Integer.valueOf(this.modifiers), Boolean.valueOf(this.getter)};
        }

        /* renamed from: -$$Nest$fgetgetter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m1622$$Nest$fgetgetter(FieldMethodSpec r0) {
            return r0.getter;
        }

        /* renamed from: -$$Nest$fgetmodifiers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1623$$Nest$fgetmodifiers(FieldMethodSpec r0) {
            return r0.modifiers;
        }

        /* renamed from: -$$Nest$fgetname, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1624$$Nest$fgetname(FieldMethodSpec r0) {
            return r0.name;
        }

        public FieldMethodSpec(String r1, int r2, boolean r3) {
            this.name = r1;
            this.modifiers = r2;
            this.getter = r3;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public boolean getter() {
            return this.getter;
        }

        public final int hashCode() {
            return ClassProxy$FieldMethodSpec$$ExternalSyntheticRecord0.m(this.getter, this.modifiers, this.name);
        }

        public int modifiers() {
            return this.modifiers;
        }

        public String name() {
            return this.name;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), FieldMethodSpec.class, "name;modifiers;getter");
        }
    }

    public static final class FieldSpec extends RecordTag {
        private final List<FieldMethodSpec> methods;
        private final int modifiers;
        private final String name;
        private final Class<?> type;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof FieldSpec) == false) goto L14;
            FieldSpec r32 = (FieldSpec) r3;
            if (this.modifiers == r32.modifiers) goto L7;
            return false;
        L7:
            if (Objects.equals(this.name, r32.name) == true) goto L9;
            return false;
        L9:
            if (Objects.equals(this.type, r32.type) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.methods, r32.methods) == false) goto L19;
            return true;
        L19:
            return false;
        L14:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.name, this.type, Integer.valueOf(this.modifiers), this.methods};
        }

        /* renamed from: -$$Nest$fgetmethods, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m1625$$Nest$fgetmethods(FieldSpec r0) {
            return r0.methods;
        }

        /* renamed from: -$$Nest$fgetmodifiers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1626$$Nest$fgetmodifiers(FieldSpec r0) {
            return r0.modifiers;
        }

        /* renamed from: -$$Nest$fgetname, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1627$$Nest$fgetname(FieldSpec r0) {
            return r0.name;
        }

        /* renamed from: -$$Nest$fgettype, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Class m1628$$Nest$fgettype(FieldSpec r0) {
            return r0.type;
        }

        public FieldSpec(String r2, Class<?> r3, int r4) {
            this(r2, r3, r4, null);
        }

        public FieldSpec(String r1, Class<?> r2, int r3, List<FieldMethodSpec> r4) {
            this.name = r1;
            this.type = r2;
            this.modifiers = r3;
            this.methods = r4;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return ClassProxy$FieldSpec$$ExternalSyntheticRecord0.m(this.modifiers, this.name, this.type, this.methods);
        }

        public List<FieldMethodSpec> methods() {
            return this.methods;
        }

        public int modifiers() {
            return this.modifiers;
        }

        public String name() {
            return this.name;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), FieldSpec.class, "name;type;modifiers;methods");
        }

        public Class<?> type() {
            return this.type;
        }
    }

    public static final class ProxyMethodSpec extends RecordTag {
        private final List<String> argumentNames;
        private final String implementation;
        private final int modifiers;
        private final String mvelCode;
        private final String name;
        private final boolean overrideExisting;
        private final Class<?>[] parameterTypes;
        private final Class<?> returnType;

        private /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof ProxyMethodSpec) == false) goto L22;
            ProxyMethodSpec r32 = (ProxyMethodSpec) r3;
            if (this.overrideExisting == r32.overrideExisting) goto L7;
            return false;
        L7:
            if (this.modifiers == r32.modifiers) goto L9;
            return false;
        L9:
            if (Objects.equals(this.name, r32.name) == true) goto L11;
            return false;
        L11:
            if (Objects.equals(this.returnType, r32.returnType) == true) goto L13;
            return false;
        L13:
            if (Objects.equals(this.parameterTypes, r32.parameterTypes) == true) goto L15;
            return false;
        L15:
            if (Objects.equals(this.implementation, r32.implementation) == true) goto L17;
            return false;
        L17:
            if (Objects.equals(this.mvelCode, r32.mvelCode) == true) goto L19;
            return false;
        L19:
            if (Objects.equals(this.argumentNames, r32.argumentNames) == false) goto L31;
            return true;
        L31:
            return false;
        L22:
            return false;
        }

        private /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.name, this.returnType, this.parameterTypes, Integer.valueOf(this.modifiers), Boolean.valueOf(this.overrideExisting), this.implementation, this.mvelCode, this.argumentNames};
        }

        /* renamed from: -$$Nest$fgetargumentNames, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m1629$$Nest$fgetargumentNames(ProxyMethodSpec r0) {
            return r0.argumentNames;
        }

        /* renamed from: -$$Nest$fgetmodifiers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m1630$$Nest$fgetmodifiers(ProxyMethodSpec r0) {
            return r0.modifiers;
        }

        /* renamed from: -$$Nest$fgetmvelCode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1631$$Nest$fgetmvelCode(ProxyMethodSpec r0) {
            return r0.mvelCode;
        }

        /* renamed from: -$$Nest$fgetname, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m1632$$Nest$fgetname(ProxyMethodSpec r0) {
            return r0.name;
        }

        /* renamed from: -$$Nest$fgetoverrideExisting, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m1633$$Nest$fgetoverrideExisting(ProxyMethodSpec r0) {
            return r0.overrideExisting;
        }

        /* renamed from: -$$Nest$fgetparameterTypes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Class[] m1634$$Nest$fgetparameterTypes(ProxyMethodSpec r0) {
            return r0.parameterTypes;
        }

        /* renamed from: -$$Nest$fgetreturnType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Class m1635$$Nest$fgetreturnType(ProxyMethodSpec r0) {
            return r0.returnType;
        }

        public ProxyMethodSpec(String r10, Class<?> r11, Class<?>[] r12, int r13, boolean r14) {
            this(r10, r11, r12, r13, r14, PluginsConstants.PYTHON, null, null);
        }

        public ProxyMethodSpec(String r1, Class<?> r2, Class<?>[] r3, int r4, boolean r5, String r6, String r7, List<String> r8) {
            this.name = r1;
            this.returnType = r2;
            this.parameterTypes = r3;
            this.modifiers = r4;
            this.overrideExisting = r5;
            this.implementation = r6;
            this.mvelCode = r7;
            this.argumentNames = r8;
        }

        public List<String> argumentNames() {
            return this.argumentNames;
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        public final int hashCode() {
            return ClassProxy$ProxyMethodSpec$$ExternalSyntheticRecord0.m(this.overrideExisting, this.modifiers, this.name, this.returnType, this.parameterTypes, this.implementation, this.mvelCode, this.argumentNames);
        }

        public String implementation() {
            return this.implementation;
        }

        public boolean isMvel() {
            if ("mvel".equals(this.implementation) == true) goto L5;
            return false;
        L5:
            if (this.mvelCode == null) goto L10;
            return true;
        L10:
            return false;
        }

        public int modifiers() {
            return this.modifiers;
        }

        public String mvelCode() {
            return this.mvelCode;
        }

        public String name() {
            return this.name;
        }

        public boolean overrideExisting() {
            return this.overrideExisting;
        }

        public Class<?>[] parameterTypes() {
            return this.parameterTypes;
        }

        public Class<?> returnType() {
            return this.returnType;
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), ProxyMethodSpec.class, "name;returnType;parameterTypes;modifiers;overrideExisting;implementation;mvelCode;argumentNames");
        }
    }

    public class TypeHelper {
        final /* synthetic */ ClassProxy this$0;

        public TypeHelper(ClassProxy r1) {
            this.this$0 = r1;
        }

        public static Object box(byte r0) {
            return Byte.valueOf(r0);
        }

        public static Object box(char r0) {
            return Character.valueOf(r0);
        }

        public static Object box(double r0) {
            return Double.valueOf(r0);
        }

        public static Object box(float r0) {
            return Float.valueOf(r0);
        }

        public static Object box(int r0) {
            return Integer.valueOf(r0);
        }

        public static Object box(long r0) {
            return Long.valueOf(r0);
        }

        public static Object box(Object r0) {
            return r0;
        }

        public static Object box(short r0) {
            return Short.valueOf(r0);
        }

        public static Object box(boolean r0) {
            return Boolean.valueOf(r0);
        }

        public static boolean unboxBool(Object r1) {
            if ((r1 instanceof Boolean) == true) goto L5;
            return false;
        L5:
            return ((Boolean) r1).booleanValue();
        }

        public static byte unboxByte(Object r1) {
            if ((r1 instanceof Number) == true) goto L5;
            return 0;
        L5:
            return ((Number) r1).byteValue();
        }

        public static char unboxChar(Object r1) {
            if ((r1 instanceof Character) == true) goto L5;
            return 0;
        L5:
            return ((Character) r1).charValue();
        }

        public static double unboxDouble(Object r2) {
            if ((r2 instanceof Number) == true) goto L5;
            return 0.0d;
        L5:
            return ((Number) r2).doubleValue();
        }

        public static float unboxFloat(Object r1) {
            if ((r1 instanceof Number) == true) goto L5;
            return 0.0f;
        L5:
            return ((Number) r1).floatValue();
        }

        public static int unboxInt(Object r1) {
            if ((r1 instanceof Number) == true) goto L5;
            return 0;
        L5:
            return ((Number) r1).intValue();
        }

        public static long unboxLong(Object r2) {
            if ((r2 instanceof Number) == true) goto L5;
            return 0;
        L5:
            return ((Number) r2).longValue();
        }

        public static short unboxShort(Object r1) {
            if ((r1 instanceof Number) == true) goto L5;
            return 0;
        L5:
            return ((Number) r1).shortValue();
        }
    }

    static {
        EMPTY_ARGS = new Object[0];
        mvelExpressionCache = new ConcurrentHashMap();
        generatedClassLoaderLock = new Object();
    }

    public ClassProxy() {
    }

    private static String buildMethodSignature(String r4, Class<?>[] r5) {
        StringBuilder r0 = new StringBuilder(r4);
        if (r5 == null) goto L8;
        int r42 = r5.length;
        int r1 = 0;
    L5:
        if (r1 >= r42) goto L8;
        Class<?> r2 = r5[r1];
        r0.append(':');
        r0.append(r2.getName());
        r1 = r1 + 1;
    L8:
        return r0.toString();
    }

    private static String capitalize(String r3) {
        if (r3.equals("int") == false) goto L7;
        return "Int";
    L7:
        if (r3.equals("boolean") == false) goto L11;
        return "Bool";
    L11:
        if (r3.equals("short") == false) goto L15;
        return "Short";
    L15:
        if (r3.equals("byte") == false) goto L19;
        return "Byte";
    L19:
        if (r3.equals("char") == false) goto L23;
        return "Char";
    L23:
        return r3.substring(0, 1).toUpperCase() + r3.substring(1);
    }

    private static void collectOverridableMethods(Class<?> r7, Map<String, Method> r8) {
        if (r7 != null) goto L4;
        return;
    L4:
        if (r7 == Object.class) goto L33;
        Method[] r0 = r7.getDeclaredMethods();
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L20;
        Method r4 = r0[r3];
        int r5 = r4.getModifiers();
        if (Modifier.isStatic(r5) == true) goto L19;
        if (Modifier.isFinal(r5) == true) goto L19;
        if (Modifier.isPublic(r5) == false) goto L15;
    L16:
        String r52 = buildMethodSignature(r4.getName(), r4.getParameterTypes());
        if (r8.containsKey(r52) == true) goto L19;
        r8.put(r52, r4);
        goto L19
    L15:
        if (Modifier.isProtected(r5) == true) goto L16;
    L19:
        r3 = r3 + 1;
        goto L7
    L20:
        Class<?>[] r02 = r7.getInterfaces();
        int r12 = r02.length;
    L21:
        if (r2 >= r12) goto L23;
        collectOverridableMethods(r02[r2], r8);
        r2 = r2 + 1;
        goto L21
    L23:
        collectOverridableMethods(r7.getSuperclass(), r8);
        return;
    }

    public static Class<?> createProxyClass(Class<?> r10, Utilities.Callback3Return<Object, String, Object[], Object> r11, DexMakerHook r12, List<Class<?>> r13, List<Method> r14, List<Constructor<?>> r15, List<ProxyMethodSpec> r16, List<FieldSpec> r17) {
        return createProxyClassInternal(r10, r11, r12, r13, r14, r15, r16, r17, null, false);
    }

    public static Class<?> createProxyClass(Class<?> r10, Utilities.Callback3Return<Object, String, Object[], Object> r11, DexMakerHook r12, List<Class<?>> r13, List<Method> r14, List<Constructor<?>> r15, List<ProxyMethodSpec> r16, List<FieldSpec> r17, String r18) {
        return createProxyClassInternal(r10, r11, r12, r13, r14, r15, r16, r17, r18, false);
    }

    public static Class<?> createProxyClass(Class<?> r10, Utilities.Callback3Return<Object, String, Object[], Object> r11, List<Method> r12, List<Constructor<?>> r13) {
        return createProxyClassInternal(r10, r11, null, null, r12, r13, null, null, null, true);
    }

    public static Class<?> createProxyClass(Class<?> r10, Utilities.Callback3Return<Object, String, Object[], Object> r11, List<Method> r12, List<Constructor<?>> r13, String r14) {
        return createProxyClassInternal(r10, r11, null, null, r12, r13, null, null, r14, true);
    }

    public static Class<?> createProxyClass(Class<?> r10, Utilities.Callback3Return<Object, String, Object[], Object> r11, List<Method> r12, List<Constructor<?>> r13, List<ProxyMethodSpec> r14, List<FieldSpec> r15) {
        return createProxyClassInternal(r10, r11, null, null, r12, r13, r14, r15, null, false);
    }

    public static Class<?> createProxyClass(Class<?> r10, Utilities.Callback3Return<Object, String, Object[], Object> r11, List<Method> r12, List<Constructor<?>> r13, List<ProxyMethodSpec> r14, List<FieldSpec> r15, String r16) {
        return createProxyClassInternal(r10, r11, null, null, r12, r13, r14, r15, r16, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Class<?> createProxyClassInternal(Class<?> r19, Utilities.Callback3Return<Object, String, Object[], Object> r20, DexMakerHook r21, List<Class<?>> r22, List<Method> r23, List<Constructor<?>> r24, List<ProxyMethodSpec> r25, List<FieldSpec> r26, String r27, boolean r28) {
        DexMaker r2 = new DexMaker();
        String r3 = r19.getSimpleName();
        if (r3.isEmpty() == false) goto L5;
        r3 = "Anonymous";
    L5:
        String r4 = sanitizeProxyClassSegment(r27);
        StringBuilder r5 = new StringBuilder("com.exteragram.messenger.plugins.Proxy_");
        r5.append(r3);
        if (r4 == null) goto L8;
        String r32 = "_".concat(r4);
    L9:
        r5.append(r32);
        r5.append("_");
        r5.append(Math.abs(r19.getName().hashCode()));
        r5.append("_");
        r5.append(Math.abs(new Random().nextInt()));
        String r9 = r5.toString();
        TypeId<?> r33 = TypeId.get("L" + r9.replace('.', '/') + ";");
        TypeId<?> r6 = TypeId.get(r19);
        FieldId r10 = r33.getField(TypeId.get(Utilities.Callback3Return.class), "staticHandler");
        TypeId<?>[] r7 = toInterfaceTypeIds(r22);
        ArrayList r11 = new ArrayList();
        Collections.addAll(r11, r7);
        r2.declare(r33, "GeneratedByExteraProxy.java", 1, r6, r7);
        r2.declare(r10, 9, null);
        if (r26 == null) goto L37;
        Iterator<FieldSpec> r42 = r26.iterator();
    L13:
        if (r42.hasNext() == false) goto L37;
        FieldSpec r52 = r42.next();
        if (r52 == null) goto L13;
        if (FieldSpec.m1627$$Nest$fgetname(r52) == null) goto L13;
        if (FieldSpec.m1628$$Nest$fgettype(r52) == null) goto L13;
        FieldId<?, V> r62 = r33.getField(TypeId.get(FieldSpec.m1628$$Nest$fgettype(r52)), FieldSpec.m1627$$Nest$fgetname(r52));
        if (Modifier.isStatic(FieldSpec.m1626$$Nest$fgetmodifiers(r52)) == false) goto L24;
        Object r72 = defaultValueForType(FieldSpec.m1628$$Nest$fgettype(r52));
    L25:
        r2.declare(r62, FieldSpec.m1626$$Nest$fgetmodifiers(r52), r72);
        if (FieldSpec.m1625$$Nest$fgetmethods(r52) == null) goto L13;
        Iterator r63 = FieldSpec.m1625$$Nest$fgetmethods(r52).iterator();
    L29:
        if (r63.hasNext() == false) goto L13;
        FieldMethodSpec r73 = (FieldMethodSpec) r63.next();
        if (r73 == null) goto L29;
        if (FieldMethodSpec.m1624$$Nest$fgetname(r73) == null) goto L29;
        generateFieldAccessorMethod(r2, r33, r52, r73);
        goto L29
    L24:
        r72 = null;
    L37:
        if (r24 == null) goto L39;
        Constructor<?>[] r1 = (Constructor[]) r24.toArray(new Constructor[0]);
    L40:
        int r43 = r1.length;
        int r53 = 0;
    L41:
        if (r53 >= r43) goto L47;
        Constructor<?> r64 = r1[r53];
        if (Modifier.isPrivate(r64.getModifiers()) == true) goto L46;
        generateConstructor(r2, r33, r6, r64, r10);
    L46:
        r53 = r53 + 1;
        goto L41
    L47:
        Map<String, Method> r12 = getAllOverridableMethods(r19, r22);
        LinkedHashMap r44 = new LinkedHashMap();
        if (r28 == false) goto L54;
        if (r23 != null) goto L54;
        if (r25 != null) goto L52;
    L53:
        r44.putAll(r12);
        goto L54
    L52:
        if (r25.isEmpty() == true) goto L53;
    L54:
        if (r23 == null) goto L62;
        Iterator<Method> r54 = r23.iterator();
    L57:
        if (r54.hasNext() == false) goto L62;
        Method r65 = r54.next();
        if (r65 == null) goto L57;
        r44.put(buildMethodSignature(r65.getName(), r65.getParameterTypes()), r65);
    L62:
        ArrayList r15 = new ArrayList();
        LinkedHashMap r55 = new LinkedHashMap();
        if (r25 == null) goto L95;
        Iterator<ProxyMethodSpec> r66 = r25.iterator();
    L66:
        if (r66.hasNext() == false) goto L95;
        ProxyMethodSpec r74 = r66.next();
        if (r74 == null) goto L66;
        if (ProxyMethodSpec.m1632$$Nest$fgetname(r74) == null) goto L66;
        if (ProxyMethodSpec.m1633$$Nest$fgetoverrideExisting(r74) == true) goto L75;
        Iterator<ProxyMethodSpec> r192 = r66;
        r15.add(r74);
    L94:
        r66 = r192;
        goto L66
    L75:
        if (ProxyMethodSpec.m1634$$Nest$fgetparameterTypes(r74) != null) goto L86;
        Iterator<Method> r8 = r12.values().iterator();
    L78:
        if (r8.hasNext() == false) goto L85;
        Method r13 = r8.next();
        Iterator<ProxyMethodSpec> r193 = r66;
        if (ProxyMethodSpec.m1632$$Nest$fgetname(r74).equals(r13.getName()) == false) goto L84;
        String r67 = buildMethodSignature(r13.getName(), r13.getParameterTypes());
        r44.put(r67, r13);
        if (r74.isMvel() == false) goto L84;
        r55.put(r67, r74);
    L84:
        r66 = r193;
        goto L78
    L85:
        r192 = r66;
        goto L94
    L86:
        r192 = r66;
        Method r68 = r12.get(buildMethodSignature(ProxyMethodSpec.m1632$$Nest$fgetname(r74), ProxyMethodSpec.m1634$$Nest$fgetparameterTypes(r74)));
        if (r68 == null) goto L92;
        String r82 = buildMethodSignature(r68.getName(), r68.getParameterTypes());
        r44.put(r82, r68);
        if (r74.isMvel() == false) goto L94;
        r55.put(r82, r74);
        goto L94
    L92:
        throw new NoSuchMethodException("Unable to resolve overridable method " + ProxyMethodSpec.m1632$$Nest$fgetname(r74));
    L95:
        Iterator r132 = r44.entrySet().iterator();
        DexMaker r29 = r2;
    L97:
        if (r132.hasNext() == false) goto L105;
        Map.Entry r14 = (Map.Entry) r132.next();
        Method r45 = (Method) r14.getValue();
        generateProxyMethod(r29, r33, r45, r10, true);
        ProxyMethodSpec r16 = (ProxyMethodSpec) r55.get(r14.getKey());
        if (r16 != null) goto L101;
    L103:
        DexMaker r17 = r29;
        TypeId<?> r210 = r33;
        LinkedHashMap r172 = r55;
        generateProxyMethod(r17, r210, r45, r10, false);
    L104:
        r33 = r210;
        r55 = r172;
        r29 = r17;
        goto L97
    L101:
        if (r16.isMvel() == false) goto L103;
        r17 = r29;
        r210 = r33;
        r172 = r55;
        generateMvelMethod(r17, r210, r45.getName(), r45.getReturnType(), r45.getParameterTypes(), 1, ProxyMethodSpec.m1631$$Nest$fgetmvelCode(r16), ProxyMethodSpec.m1629$$Nest$fgetargumentNames(r16));
        goto L104
    L105:
        DexMaker r18 = r29;
        TypeId<?> r211 = r33;
        int r133 = r15.size();
        int r34 = 0;
    L106:
        if (r34 >= r133) goto L137;
        int r173 = r34 + 1;
        ProxyMethodSpec r46 = (ProxyMethodSpec) r15.get(r34);
        if (r46.isMvel() == false) goto L122;
        String r35 = ProxyMethodSpec.m1632$$Nest$fgetname(r46);
        if (ProxyMethodSpec.m1635$$Nest$fgetreturnType(r46) == null) goto L112;
        Class r56 = ProxyMethodSpec.m1635$$Nest$fgetreturnType(r46);
    L114:
        if (ProxyMethodSpec.m1634$$Nest$fgetparameterTypes(r46) == null) goto L116;
        Class[] r69 = ProxyMethodSpec.m1634$$Nest$fgetparameterTypes(r46);
    L118:
        if (ProxyMethodSpec.m1630$$Nest$fgetmodifiers(r46) == 0) goto L120;
        int r75 = ProxyMethodSpec.m1630$$Nest$fgetmodifiers(r46);
    L121:
        generateMvelMethod(r18, r211, r35, r56, r69, r75, ProxyMethodSpec.m1631$$Nest$fgetmvelCode(r46), ProxyMethodSpec.m1629$$Nest$fgetargumentNames(r46));
        FieldId r36 = r10;
    L136:
        r10 = r36;
        r34 = r173;
        goto L106
    L120:
        r75 = 1;
        goto L121
    L116:
        r69 = new Class[0];
        goto L118
    L112:
        r56 = Void.TYPE;
        goto L114
    L122:
        String r37 = ProxyMethodSpec.m1632$$Nest$fgetname(r46);
        if (ProxyMethodSpec.m1635$$Nest$fgetreturnType(r46) == null) goto L125;
        Class r47 = ProxyMethodSpec.m1635$$Nest$fgetreturnType(r46);
    L127:
        if (ProxyMethodSpec.m1634$$Nest$fgetparameterTypes(r46) == null) goto L129;
        Class[] r57 = ProxyMethodSpec.m1634$$Nest$fgetparameterTypes(r46);
    L131:
        if (ProxyMethodSpec.m1630$$Nest$fgetmodifiers(r46) == 0) goto L134;
        int r272 = ProxyMethodSpec.m1630$$Nest$fgetmodifiers(r46);
    L133:
        DexMaker r222 = r18;
        TypeId<?> r232 = r211;
        FieldId r282 = r10;
        generateHandlerMethod(r222, r232, r37, r47, r57, r272, r282);
        r18 = r222;
        r211 = r232;
        r36 = r282;
        goto L136
    L134:
        r272 = 1;
        goto L133
    L129:
        r57 = new Class[0];
        goto L131
    L125:
        r47 = Void.TYPE;
        goto L127
    L137:
        if (r21 == null) goto L139;
        r21.apply(r18, r211, r6, r11);
    L139:
        byte[] r0 = r18.generate();
        Object r110 = generatedClassLoaderLock;
        monitor-enter(r110);
        ClassLoader r212 = sharedGeneratedClassLoader;     // Catch: Throwable -> L144
        if (r212 != null) goto L146;
        r212 = ApplicationLoader.applicationContext.getClassLoader();     // Catch: Throwable -> L144
    L146:
        ClassProxy$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L144
        InMemoryDexClassLoader r02 = ClassProxy$$ExternalSyntheticApiModelOutline0.m(ByteBuffer.wrap(r0), r212);     // Catch: Throwable -> L144
        Class<?> r213 = r02.loadClass(r9);     // Catch: Throwable -> L144
        sharedGeneratedClassLoader = r02;     // Catch: Throwable -> L144
        monitor-exit(r110);     // Catch: Throwable -> L144
        Field r03 = r213.getDeclaredField("staticHandler");
        r03.setAccessible(true);
        r03.set(null, r20);
        return r213;
    L144:
        th = move-exception;
        throw th;
    L39:
        r1 = r19.getDeclaredConstructors();
        goto L40
    L8:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    private static Object defaultValueForType(Class<?> r2) {
        if (r2.isPrimitive() == true) goto L7;
        return null;
    L7:
        if (r2 != Boolean.TYPE) goto L11;
        return Boolean.FALSE;
    L11:
        if (r2 != Long.TYPE) goto L15;
        return 0L;
    L15:
        if (r2 != Double.TYPE) goto L19;
        return Double.valueOf(0.0d);
    L19:
        if (r2 != Float.TYPE) goto L23;
        return Float.valueOf(0.0f);
    L23:
        if (r2 != Short.TYPE) goto L27;
        return (short) 0;
    L27:
        if (r2 != Byte.TYPE) goto L31;
        return (byte) 0;
    L31:
        if (r2 != Character.TYPE) goto L35;
        return (char) 0;
    L35:
        return 0;
    }

    public static Object executeMvelMethod(Object r4, Object r5, String r6, String[] r7, Object[] r8) {
        HashMap r0 = new HashMap();
        int r1 = 0;
        if (r8 != null) goto L6;
        r8 = new Object[0];
    L6:
        r0.put("java", r4);
        r0.put(PluginsConstants.PYTHON, r5);
        r0.put("py", r5);
        r0.put("self", r5);
        r0.put("args", r8);
        r0.put("argc", Integer.valueOf(r8.length));
    L8:
        if (r1 >= r8.length) goto L20;
        Object r52 = r8[r1];
        r0.put("arg" + r1, r52);
        if (r7 == null) goto L18;
        if (r1 >= r7.length) goto L18;
        String r2 = r7[r1];
        if (r2 == null) goto L18;
        if (r2.isEmpty() == true) goto L18;
        r0.put(r2, r52);
    L18:
        r1 = r1 + 1;
        goto L8
    L20:
        return MVEL.executeExpression(mvelExpressionCache.computeIfAbsent(r6, new ClassProxy$$ExternalSyntheticLambda2()), r4, r0);
    }

    private static void generateConstructor(DexMaker r18, TypeId<?> r19, TypeId<?> r20, Constructor<?> r21, FieldId r22) {
        Class<?>[] r1 = r21.getParameterTypes();
        TypeId<?>[] r2 = new TypeId[r1.length];
        int r5 = 0;
    L4:
        if (r5 >= r1.length) goto L6;
        r2[r5] = TypeId.get(r1[r5]);
        r5 = r5 + 1;
        goto L4
    L6:
        Code r52 = r18.declare(r19.getConstructor(r2), 1);
        TypeId r6 = TypeId.get(Utilities.Callback3Return.class);
        TypeId<Object> r7 = TypeId.OBJECT;
        MethodId r8 = r6.getMethod(r7, "run", new TypeId[]{r7, r7, r7});
        Local r0 = r52.getThis(r19);
        Local r62 = r52.newLocal(r6);
        Local r9 = r52.newLocal(TypeId.STRING);
        Local<?> r11 = r52.newLocal(TypeId.get(Object[].class));
        Local r12 = r52.newLocal(r7);
        Local<?> r13 = r52.newLocal(r7);
        Local<?> r10 = r52.newLocal(TypeId.get(Object[].class));
        TypeId<Integer> r14 = TypeId.INT;
        Local<Integer> r15 = r52.newLocal(r14);
        Local<Integer> r142 = r52.newLocal(r14);
        Local<?>[] r3 = new Local[r1.length];
        Local<?> r72 = r52.newLocal(r7);
        int r32 = 0;
    L8:
        if (r32 >= r1.length) goto L10;
        r3[r32] = r52.newLocal(TypeId.OBJECT);
        r32 = r32 + 1;
        goto L8
    L10:
        Local<Integer> r02 = r52.newLocal(TypeId.INT);
        Local<?>[] r33 = new Local[r1.length];
        int r34 = 0;
    L12:
        if (r34 >= r1.length) goto L14;
        r33[r34] = r52.newLocal(r2[r34]);
        r34 = r34 + 1;
        goto L12
    L14:
        r52.loadConstant(r02, 0);
        r52.newArray(r11, r02);
        r52.move(r10, r11);
        r52.sget(r22, r62);
        r52.loadConstant(r9, buildMethodSignature("<constructor>", r1));
        r52.loadConstant(r12, null);
        r52.loadConstant(r142, 0);
        r52.loadConstant(r72, null);
        int r23 = 0;
    L16:
        if (r23 >= r1.length) goto L18;
        r52.move(r33[r23], r52.getParameter(r23, r2[r23]));
        r23 = r23 + 1;
        goto L16
    L18:
        r52.loadConstant(r15, Integer.valueOf(r1.length));
        r52.newArray(r11, r15);
        int r24 = 0;
    L20:
        if (r24 >= r1.length) goto L26;
        TypeId r35 = TypeId.get(TypeHelper.class);
        TypeId<?> r4 = TypeId.OBJECT;
        if (r1[r24].isPrimitive() == false) goto L24;
        TypeId<?> r152 = r2[r24];
    L25:
        r52.invokeStatic(r35.getMethod(r4, "box", new TypeId[]{r152}), r3[r24], new Local[]{r33[r24]});
        r52.loadConstant(r142, Integer.valueOf(r24));
        r52.aput(r11, r142, r3[r24]);
        r24 = r24 + 1;
        goto L20
    L24:
        r152 = r4;
        goto L25
    L26:
        r52.invokeInterface(r8, r13, r62, new Local[]{r12, r9, r11});
        Label r03 = new Label();
        r52.compare(Comparison.EQ, r03, r13, r12);
        r52.cast(r10, r13);
        int r36 = 0;
    L28:
        if (r36 >= r1.length) goto L34;
        r52.loadConstant(r142, Integer.valueOf(r36));
        r52.aget(r72, r10, r142);
        if (r1[r36].isPrimitive() == false) goto L32;
        r52.invokeStatic(TypeId.get(TypeHelper.class).getMethod(r2[r36], "unbox" + capitalize(r1[r36].getName()), new TypeId[]{TypeId.OBJECT}), r33[r36], new Local[]{r72});
    L33:
        r36 = r36 + 1;
        goto L28
    L32:
        r52.cast(r33[r36], r72);
        goto L33
    L34:
        r52.mark(r03);
        r52.invokeDirect(r20.getConstructor(r2), null, r0, r33);
        r52.invokeInterface(r8, null, r62, new Local[]{r0, r9, r11});
        r52.returnVoid();
    }

    private static void generateFieldAccessorMethod(DexMaker r6, TypeId<?> r7, FieldSpec r8, FieldMethodSpec r9) {
        TypeId r0 = TypeId.get(FieldSpec.m1628$$Nest$fgettype(r8));
        Object r1 = r7.getField(r0, FieldSpec.m1627$$Nest$fgetname(r8));
        boolean r82 = Modifier.isStatic(FieldSpec.m1626$$Nest$fgetmodifiers(r8));
        if (FieldMethodSpec.m1623$$Nest$fgetmodifiers(r9) == 0) goto L5;
        int r2 = FieldMethodSpec.m1623$$Nest$fgetmodifiers(r9);
    L7:
        if (FieldMethodSpec.m1622$$Nest$fgetgetter(r9) == false) goto L14;
        Code r62 = r6.declare(r7.getMethod(r0, FieldMethodSpec.m1624$$Nest$fgetname(r9), new TypeId[0]), r2);
        Local<?> r92 = r62.newLocal(r0);
        if (r82 == false) goto L11;
        r62.sget(r1, r92);
    L12:
        r62.returnValue(r92);
        return;
    L11:
        r62.iget(r1, r92, r62.getThis(r7));
        goto L12
    L14:
        Code r63 = r6.declare(r7.getMethod(TypeId.VOID, FieldMethodSpec.m1624$$Nest$fgetname(r9), new TypeId[]{r0}), r2);
        Local r93 = r63.getParameter(0, r0);
        if (r82 == false) goto L17;
        r63.sput(r1, r93);
    L18:
        r63.returnVoid();
        return;
    L17:
        r63.iput(r1, r63.getThis(r7), r93);
        goto L18
    L5:
        r2 = 1;
        goto L7
    }

    private static void generateHandlerMethod(DexMaker r18, TypeId<?> r19, String r20, Class<?> r21, Class<?>[] r22, int r23, FieldId r24) {
        Class<?>[] r2 = r22;
        TypeId<?>[] r3 = new TypeId[r2.length];
        int r5 = 0;
    L4:
        if (r5 >= r2.length) goto L6;
        r3[r5] = TypeId.get(r2[r5]);
        r5 = r5 + 1;
        goto L4
    L6:
        TypeId r6 = TypeId.get(Utilities.Callback3Return.class);
        TypeId<Object> r7 = TypeId.OBJECT;
        MethodId r62 = r6.getMethod(r7, "run", new TypeId[]{r7, r7, r7});
        TypeId<R> r8 = TypeId.get(r21);
        Code r9 = r18.declare(r19.getMethod(r8, r20, r3), r23);
        Local r0 = r9.getThis(r19);
        if (r8.equals(TypeId.VOID) == true) goto L9;
        Local<?> r10 = r9.newLocal(r8);
    L10:
        Local r52 = r9.newLocal(TypeId.get(Utilities.Callback3Return.class));
        Local r11 = r9.newLocal(TypeId.STRING);
        Local<?> r12 = r9.newLocal(TypeId.get(Object[].class));
        TypeId<Integer> r13 = TypeId.INT;
        Local<Integer> r14 = r9.newLocal(r13);
        Local<?> r72 = r9.newLocal(r7);
        Local<Integer> r132 = r9.newLocal(r13);
        Local<?>[] r15 = new Local[r2.length];
        int r4 = 0;
    L12:
        if (r4 >= r2.length) goto L14;
        r15[r4] = r9.newLocal(TypeId.OBJECT);
        r4 = r4 + 1;
        goto L12
    L14:
        r9.sget(r24, r52);
        r9.loadConstant(r11, buildMethodSignature(r20, r2));
        r9.loadConstant(r14, Integer.valueOf(r2.length));
        r9.newArray(r12, r14);
        int r42 = 0;
    L16:
        if (r42 >= r2.length) goto L23;
        Local r1 = r9.getParameter(r42, r3[r42]);
        TypeId r32 = TypeId.get(TypeHelper.class);
        TypeId<?> r142 = TypeId.OBJECT;
        if (r2[r42].isPrimitive() == false) goto L21;
        TypeId<?> r16 = r3[r42];
    L22:
        r9.invokeStatic(r32.getMethod(r142, "box", new TypeId[]{r16}), r15[r42], new Local[]{r1});
        r9.loadConstant(r132, Integer.valueOf(r42));
        r9.aput(r12, r132, r15[r42]);
        r42 = r42 + 1;
        r2 = r22;
        goto L16
    L21:
        r16 = r142;
        goto L22
    L23:
        r9.invokeInterface(r62, r72, r52, new Local[]{r0, r11, r12});
        if (r8.equals(TypeId.VOID) == false) goto L28;
        r9.returnVoid();
        return;
    L28:
        if (r21.isPrimitive() == false) goto L30;
        r9.invokeStatic(TypeId.get(TypeHelper.class).getMethod(r8, "unbox" + capitalize(r21.getName()), new TypeId[]{TypeId.OBJECT}), r10, new Local[]{r72});
    L31:
        r9.returnValue(r10);
        return;
    L30:
        r9.cast(r10, r72);
        goto L31
    L9:
        r10 = null;
        goto L10
    }

    private static void generateMvelMethod(DexMaker r23, TypeId<?> r24, String r25, Class<?> r26, Class<?>[] r27, int r28, String r29, List<String> r30) {
        TypeId<?>[] r3 = new TypeId[r27.length];
        int r5 = 0;
    L4:
        if (r5 >= r27.length) goto L6;
        r3[r5] = TypeId.get(r27[r5]);
        r5 = r5 + 1;
        goto L4
    L6:
        TypeId<R> r52 = TypeId.get(r26);
        Code r6 = r23.declare(r24.getMethod(r52, r25, r3), r28);
        if (Modifier.isStatic(r28) == false) goto L9;
        Local<?> r7 = null;
    L10:
        TypeId<Object> r10 = TypeId.OBJECT;
        Local<?> r11 = r6.newLocal(r10);
        Local r12 = r6.newLocal(r10);
        Local<?> r13 = r6.newLocal(r10);
        TypeId<String> r14 = TypeId.STRING;
        Local r15 = r6.newLocal(r14);
        Local<?> r4 = r6.newLocal(TypeId.get(Object[].class));
        Local<?> r9 = r6.newLocal(TypeId.get(String[].class));
        TypeId<Integer> r32 = TypeId.INT;
        Local<Integer> r8 = r6.newLocal(r32);
        Local<Integer> r33 = r6.newLocal(r32);
        Local r102 = r6.newLocal(r10);
        Local r132 = r6.newLocal(TypeId.get(String[].class));
        Local<?> r142 = r6.newLocal(r14);
        if (r52.equals(TypeId.VOID) == false) goto L13;
        Local<?> r20 = null;
    L14:
        Local<?>[] r133 = new Local[r27.length];
        int r134 = 0;
    L16:
        if (r134 >= r27.length) goto L18;
        r133[r134] = r6.newLocal(TypeId.OBJECT);
        r134 = r134 + 1;
        goto L16
    L18:
        r6.loadConstant(r102, null);
        r6.move(r11, r102);
        r6.move(r12, r102);
        if (Modifier.isStatic(r28) == true) goto L21;
        Object r0 = r24.getField(TypeId.OBJECT, PYTHON_PEER_FIELD_NAME);
        r6.cast(r11, r7);
        r6.iget(r0, r12, r7);
    L21:
        r6.loadConstant(r15, r29);
        r6.loadConstant(r8, Integer.valueOf(r27.length));
        r6.newArray(r4, r8);
        int r02 = 0;
    L23:
        if (r02 >= r27.length) goto L29;
        Local r53 = r6.getParameter(r02, r3[r02]);
        TypeId r72 = TypeId.get(TypeHelper.class);
        TypeId<?> r103 = TypeId.OBJECT;
        if (r27[r02].isPrimitive() == false) goto L27;
        TypeId<?> r135 = r3[r02];
    L28:
        int r242 = r02;
        r6.invokeStatic(r72.getMethod(r103, "box", new TypeId[]{r135}), r133[r242], new Local[]{r53});
        r6.loadConstant(r33, Integer.valueOf(r242));
        r6.aput(r4, r33, r133[r242]);
        r02 = r242 + 1;
        goto L23
    L27:
        r135 = r103;
        goto L28
    L29:
        if (r30 != null) goto L31;
    L36:
        r6.loadConstant(r132, null);
        r6.move(r9, r132);
    L37:
        TypeId r03 = TypeId.get(ClassProxy.class);
        TypeId<Object> r1 = TypeId.OBJECT;
        r6.invokeStatic(r03.getMethod(r1, "executeMvelMethod", new TypeId[]{r1, r1, TypeId.STRING, TypeId.get(String[].class), TypeId.get(Object[].class)}), r13, new Local[]{r11, r12, r15, r9, r4});
        if (r52.equals(TypeId.VOID) == false) goto L42;
        r6.returnVoid();
        return;
    L42:
        if (r26.isPrimitive() == false) goto L44;
        MethodId r04 = TypeId.get(TypeHelper.class).getMethod(r52, "unbox" + capitalize(r26.getName()), new TypeId[]{r1});
        Local<?>[] r16 = {r13};
        Local<?> r136 = r20;
        r6.invokeStatic(r04, r136, r16);
    L45:
        r6.returnValue(r136);
        return;
    L44:
        r136 = r20;
        r6.cast(r136, r13);
        goto L45
    L31:
        if (r30.isEmpty() == true) goto L36;
        r6.loadConstant(r8, Integer.valueOf(r30.size()));
        r6.newArray(r9, r8);
        int r05 = 0;
    L34:
        if (r05 >= r30.size()) goto L37;
        r6.loadConstant(r142, r30.get(r05));
        r6.loadConstant(r33, Integer.valueOf(r05));
        r6.aput(r9, r33, r142);
        r05 = r05 + 1;
        goto L34
    L13:
        r20 = r6.newLocal(r52);
        goto L14
    L9:
        r7 = r6.getThis(r24);
        goto L10
    }

    private static void generateProxyMethod(DexMaker r7, TypeId<?> r8, Method r9, FieldId r10, boolean r11) {
        Class<?>[] r4 = r9.getParameterTypes();
        TypeId<?>[] r0 = new TypeId[r4.length];
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r4.length) goto L6;
        r0[r2] = TypeId.get(r4[r2]);
        r2 = r2 + 1;
        goto L4
    L6:
        TypeId<R> r22 = TypeId.get(r9.getReturnType());
        if (r11 == false) goto L9;
        String r3 = "SUPER_" + r9.getName();
    L10:
        if (r11 == false) goto L25;
        Code r72 = r7.declare(r8.getMethod(r22, r3, r0), 1);
        Local r82 = r72.getThis(r8);
        if (r22.equals(TypeId.VOID) == false) goto L14;
        Local<?> r102 = null;
    L15:
        Local<?>[] r112 = new Local[r4.length];
    L17:
        if (r1 >= r4.length) goto L19;
        r112[r1] = r72.getParameter(r1, r0[r1]);
        r1 = r1 + 1;
        goto L17
    L19:
        r72.invokeSuper(TypeId.get(r9.getDeclaringClass()).getMethod(r22, r9.getName(), r0), r102, r82, r112);
        if (r22.equals(TypeId.VOID) == false) goto L23;
        r72.returnVoid();
        return;
    L23:
        r72.returnValue(r102);
        return;
    L14:
        r102 = r72.newLocal(r22);
        goto L15
    L25:
        generateHandlerMethod(r7, r8, r3, r9.getReturnType(), r4, 1, r10);
        return;
    L9:
        r3 = r9.getName();
        goto L10
    }

    private static Map<String, Method> getAllOverridableMethods(Class<?> r1, List<Class<?>> r2) {
        LinkedHashMap r0 = new LinkedHashMap();
        collectOverridableMethods(r1, r0);
        if (r2 == null) goto L8;
        Iterator<Class<?>> r12 = r2.iterator();
    L6:
        if (r12.hasNext() == false) goto L8;
        collectOverridableMethods(r12.next(), r0);
    L8:
        return r0;
    }

    @Keep
    public static void runPythonRunnable(PyObject r1) {
        if (r1 != null) goto L10;
        AppUtils.log("Runnable callback is null");
        return;
    L10:
        r1.call(EMPTY_ARGS);     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        AppUtils.log("Error in runnable", e);
    }

    private static String sanitizeProxyClassSegment(String r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        String r42 = r4.trim();
        if (r42.isEmpty() == false) goto L8;
        return null;
    L8:
        StringBuilder r0 = new StringBuilder(r42.length());
        int r1 = 0;
    L10:
        if (r1 >= r42.length()) goto L20;
        char r2 = r42.charAt(r1);
        if (Character.isLetterOrDigit(r2) == false) goto L14;
    L17:
        r0.append(r2);
    L18:
        r1 = r1 + 1;
        goto L10
    L14:
        if (r2 == '_') goto L17;
        r0.append('_');
        goto L18
    L20:
        return r0.toString();
    }

    private static TypeId<?>[] toInterfaceTypeIds(List<Class<?>> r4) {
        if (r4 == null) goto L21;
        if (r4.isEmpty() == true) goto L21;
        ArrayList r1 = new ArrayList();
        Iterator<Class<?>> r42 = r4.iterator();
    L9:
        if (r42.hasNext() == false) goto L19;
        Class<?> r2 = r42.next();
        if (r2 == null) goto L9;
        if (r2.isInterface() == false) goto L16;
        r1.add(TypeId.get(r2));
        goto L9
    L16:
        f$$ExternalSyntheticBUOutline1.m("Expected interface, got ".concat(r2.getName()));
        return null;
    L19:
        return (TypeId[]) r1.toArray(new TypeId[0]);
    L21:
        return new TypeId[0];
    }
}
