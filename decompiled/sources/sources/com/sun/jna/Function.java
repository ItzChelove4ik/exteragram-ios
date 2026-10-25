package com.sun.jna;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.sun.jna.Structure;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class Function extends Pointer {
    public static final int ALT_CONVENTION = 63;
    public static final int C_CONVENTION = 0;
    static final Integer INTEGER_FALSE = null;
    static final Integer INTEGER_TRUE = null;
    private static final VarArgsChecker IS_VARARGS = null;
    private static final int MASK_CC = 63;
    public static final int MAX_NARGS = 256;
    static final String OPTION_INVOKING_METHOD = "invoking-method";
    public static final int THROW_LAST_ERROR = 64;
    public static final int USE_VARARGS = 255;
    private static final int USE_VARARGS_SHIFT = 7;
    final int callFlags;
    final String encoding;
    private final String functionName;
    private NativeLibrary library;
    final Map<String, ?> options;

    public static class NativeMappedArray extends Memory implements PostCallRead {
        private final NativeMapped[] original;

        public NativeMappedArray(NativeMapped[] r4) {
            super(Native.getNativeSize(r4.getClass(), r4));
            this.original = r4;
            setValue(0, r4, r4.getClass());
        }

        @Override // com.sun.jna.Function.PostCallRead
        public void read() {
            getValue(0, this.original.getClass(), this.original);
        }
    }

    public static class PointerArray extends Memory implements PostCallRead {
        private final Pointer[] original;

        public PointerArray(Pointer[] r5) {
            super(Native.POINTER_SIZE * (r5.length + 1));
            this.original = r5;
            int r0 = 0;
        L4:
            if (r0 >= r5.length) goto L6;
            setPointer(Native.POINTER_SIZE * r0, r5[r0]);
            r0 = r0 + 1;
            goto L4
        L6:
            setPointer(Native.POINTER_SIZE * r5.length, null);
        }

        @Override // com.sun.jna.Function.PostCallRead
        public void read() {
            Pointer[] r3 = this.original;
            read(0, r3, 0, r3.length);
        }
    }

    public interface PostCallRead {
        void read();
    }

    static {
        INTEGER_TRUE = -1;
        INTEGER_FALSE = 0;
        IS_VARARGS = VarArgsChecker.create();
    }

    public Function(NativeLibrary r2, String r3, int r4, String r5) {
        checkCallingConvention(r4 & 63);
        if (r3 == null) goto L14;
        this.library = r2;
        this.functionName = r3;
        this.callFlags = r4;
        this.options = r2.getOptions();
        if (r5 != null) goto L8;
        r5 = Native.getDefaultStringEncoding();
    L8:
        this.encoding = r5;
        this.peer = r2.getSymbolAddress(r3);     // Catch: UnsatisfiedLinkError -> L11
        return;
    L11:
        e = move-exception;
        throw new UnsatisfiedLinkError("Error looking up function '" + r3 + "': " + e.getMessage());
    L14:
        f$$ExternalSyntheticBUOutline2.m("Function name must not be null");
        throw null;
    }

    public Function(Pointer r5, int r6, String r7) {
        checkCallingConvention(r6 & 63);
        if (r5 != null) goto L5;
    L12:
        f$$ExternalSyntheticBUOutline2.m("Function address may not be null");
        throw null;
    L5:
        if (r5.peer == 0) goto L12;
        this.functionName = r5.toString();
        this.callFlags = r6;
        this.peer = r5.peer;
        this.options = Collections.EMPTY_MAP;
        if (r7 != null) goto L10;
        r7 = Native.getDefaultStringEncoding();
    L10:
        this.encoding = r7;
    }

    private void checkCallingConvention(int r1) {
        if ((r1 & 63) != r1) goto L5;
        return;
    L5:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unrecognized calling convention: ", r1);
    }

    public static Object[] concatenateVarArgs(Object[] r6) {
        if (r6 != null) goto L4;
    L21:
        return r6;
    L4:
        if (r6.length <= 0) goto L21;
        Object r0 = r6[r6.length - 1];
        if (r0 == null) goto L8;
        Class<?> r2 = r0.getClass();
    L9:
        if (r2 == null) goto L21;
        if (r2.isArray() == false) goto L21;
        Object[] r02 = (Object[]) r0;
        int r3 = 0;
    L14:
        if (r3 >= r02.length) goto L19;
        if ((r02[r3] instanceof Float) == false) goto L18;
        r02[r3] = Double.valueOf(((Float) r4).floatValue());
    L18:
        r3 = r3 + 1;
        goto L14
    L19:
        int r32 = r6.length + r02.length;
        Object[] r4 = new Object[r32];
        System.arraycopy(r6, 0, r4, 0, r6.length - 1);
        System.arraycopy(r02, 0, r4, r6.length - 1, r02.length);
        r4[r32 - 1] = null;
        return r4;
    L8:
        r2 = null;
        goto L9
    }

    private Object convertArgument(Object[] r6, int r7, Method r8, TypeMapper r9, boolean r10, Class<?> r11) {
        Object r0 = r6[r7];
        if (r0 == null) goto L15;
        Class<?> r2 = r0.getClass();
        if (NativeMapped.class.isAssignableFrom(r2) == false) goto L7;
        ToNativeConverter r92 = NativeMappedConverter.getInstance(r2);
    L10:
        if (r92 == null) goto L15;
        if (r8 == null) goto L13;
        ToNativeContext r22 = new MethodParameterContext(this, r6, r7, r8);
    L14:
        r0 = r92.toNative(r0, r22);
        goto L15
    L13:
        r22 = new FunctionParameterContext(this, r6, r7);
        goto L14
    L7:
        if (r9 == null) goto L9;
        r92 = r9.getToNativeConverter(r2);
        goto L10
    L9:
        r92 = null;
    L15:
        if (r0 != null) goto L17;
    L125:
        return r0;
    L17:
        if (isPrimitiveArray(r0.getClass()) == true) goto L125;
        Class<?> r62 = r0.getClass();
        if ((r0 instanceof Structure) == false) goto L40;
        Structure r02 = (Structure) r0;
        r02.autoWrite();
        if ((r02 instanceof Structure.ByValue) == false) goto L38;
        Class<?> r5 = r02.getClass();
        if (r8 == null) goto L35;
        Class<?>[] r63 = r8.getParameterTypes();
        if (IS_VARARGS.isVarArgs(r8) == true) goto L28;
        r5 = r63[r7];
        goto L35
    L28:
        if (r7 >= (r63.length - 1)) goto L30;
        r5 = r63[r7];
        goto L35
    L30:
        Class<?> r64 = r63[r63.length - 1].getComponentType();
        if (r64 == Object.class) goto L35;
        r5 = r64;
    L35:
        if (Structure.ByValue.class.isAssignableFrom(r5) == false) goto L38;
        return r02;
    L38:
        return r02.getPointer();
    L40:
        if ((r0 instanceof Callback) == false) goto L44;
        return CallbackReference.getFunctionPointer((Callback) r0);
    L44:
        if ((r0 instanceof String) == false) goto L48;
        return new NativeString((String) r0, this.encoding).getPointer();
    L48:
        if ((r0 instanceof WString) == false) goto L52;
        return new NativeString(r0.toString(), true).getPointer();
    L52:
        if ((r0 instanceof Boolean) == false) goto L60;
        if (Boolean.TRUE.equals(r0) == false) goto L58;
        return INTEGER_TRUE;
    L58:
        return INTEGER_FALSE;
    L60:
        if (String[].class != r62) goto L64;
        return new StringArray((String[]) r0, this.encoding);
    L64:
        if (WString[].class != r62) goto L68;
        return new StringArray((WString[]) r0);
    L68:
        if (Pointer[].class != r62) goto L72;
        return new PointerArray((Pointer[]) r0);
    L72:
        if (NativeMapped[].class.isAssignableFrom(r62) == false) goto L76;
        return new NativeMappedArray((NativeMapped[]) r0);
    L76:
        if (Structure[].class.isAssignableFrom(r62) == false) goto L115;
        Structure[] r03 = (Structure[]) r0;
        Class<?> r65 = r62.getComponentType();
        boolean r82 = Structure.ByReference.class.isAssignableFrom(r65);
        int r93 = 0;
        if (r11 != null) goto L80;
    L93:
        if (r82 == false) goto L105;
        Structure.autoWrite(r03);
        Pointer[] r52 = new Pointer[r03.length + 1];
    L96:
        if (r93 >= r03.length) goto L103;
        Structure r66 = r03[r93];
        if (r66 == null) goto L100;
        Pointer r67 = r66.getPointer();
    L101:
        r52[r93] = r67;
        r93 = r93 + 1;
        goto L96
    L100:
        r67 = null;
        goto L101
    L103:
        return new PointerArray(r52);
    L105:
        if (r03.length != 0) goto L107;
        f$$ExternalSyntheticBUOutline1.m("Structure array must have non-zero length");
        return null;
    L107:
        if (r03[0] != null) goto L110;
        Structure.newInstance(r65).toArray(r03);
        return r03[0].getPointer();
    L110:
        Structure.autoWrite(r03);
        return r03[0].getPointer();
    L80:
        if (Structure.ByReference[].class.isAssignableFrom(r11) == true) goto L93;
        if (r82 == true) goto L92;
        int r3 = 0;
    L85:
        if (r3 >= r03.length) goto L93;
        if ((r03[r3] instanceof Structure.ByReference) == true) goto L90;
        r3 = r3 + 1;
        goto L85
    L90:
        throw new IllegalArgumentException("Function " + getName() + " declared Structure[] at parameter " + r7 + " but element " + r3 + " is of Structure.ByReference type");
    L92:
        throw new IllegalArgumentException("Function " + getName() + " declared Structure[] at parameter " + r7 + " but array of " + r65 + " was passed");
    L115:
        if (r62.isArray() == true) goto L123;
        if (r10 == false) goto L119;
    L120:
        return r0;
    L119:
        if (Native.isSupportedNativeType(r0.getClass()) == true) goto L120;
        throw new IllegalArgumentException("Unsupported argument type " + r0.getClass().getName() + " at parameter " + r7 + " of function " + getName());
    L123:
        Buffer$$ExternalSyntheticBUOutline4.m("Unsupported array argument type: ", r62.getComponentType());
        return null;
    }

    public static int fixedArgs(Method r1) {
        return IS_VARARGS.fixedArgs(r1);
    }

    public static Function getFunction(Pointer r2) {
        return getFunction(r2, 0, null);
    }

    public static Function getFunction(Pointer r1, int r2) {
        return getFunction(r1, r2, null);
    }

    public static Function getFunction(Pointer r1, int r2, String r3) {
        return new Function(r1, r2, r3);
    }

    public static Function getFunction(String r0, String r1) {
        return NativeLibrary.getInstance(r0).getFunction(r1);
    }

    public static Function getFunction(String r1, String r2, int r3) {
        return NativeLibrary.getInstance(r1).getFunction(r2, r3, null);
    }

    public static Function getFunction(String r0, String r1, int r2, String r3) {
        return NativeLibrary.getInstance(r0).getFunction(r1, r2, r3);
    }

    private Pointer invokePointer(int r3, Object[] r4) {
        long r2 = Native.invokePointer(this, this.peer, r3, r4);
        if (r2 != 0) goto L7;
        return null;
    L7:
        return new Pointer(r2);
    }

    private String invokeString(int r3, Object[] r4, boolean r5) {
        Pointer r32 = invokePointer(r3, r4);
        if (r32 != null) goto L5;
        return null;
    L5:
        if (r5 == false) goto L9;
        return r32.getWideString(0);
    L9:
        return r32.getString(0, this.encoding);
    }

    private boolean isPrimitiveArray(Class<?> r1) {
        if (r1.isArray() == true) goto L5;
        return false;
    L5:
        if (r1.getComponentType().isPrimitive() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isVarArgs(Method r1) {
        return IS_VARARGS.isVarArgs(r1);
    }

    public static Boolean valueOf(boolean r0) {
        if (r0 == false) goto L6;
        return Boolean.TRUE;
    L6:
        return Boolean.FALSE;
    }

    @Override // com.sun.jna.Pointer
    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if (r5 != null) goto L9;
        return false;
    L9:
        if (r5.getClass() != getClass()) goto L17;
        Function r52 = (Function) r5;
        if (r52.callFlags != this.callFlags) goto L17;
        if (r52.options.equals(this.options) == false) goto L17;
        if (r52.peer != this.peer) goto L17;
        return true;
    L17:
        return false;
    }

    public int getCallingConvention() {
        return this.callFlags & 63;
    }

    public String getName() {
        return this.functionName;
    }

    @Override // com.sun.jna.Pointer
    public int hashCode() {
        return (this.callFlags + this.options.hashCode()) + super.hashCode();
    }

    public Object invoke(Class<?> r2, Object[] r3) {
        return invoke(r2, r3, this.options);
    }

    public Object invoke(Class<?> r8, Object[] r9, Map<String, ?> r10) {
        Method r2 = (Method) r10.get(OPTION_INVOKING_METHOD);
        if (r2 == null) goto L6;
        Class<?>[] r0 = r2.getParameterTypes();
    L8:
        return invoke(r2, r0, r8, r9, r10);
    L6:
        r0 = null;
        goto L8
    }

    public Object invoke(Method r11, Class<?>[] r12, Class<?> r13, Object[] r14, Map<String, ?> r15) {
        Object[] r1 = new Object[0];
        FromNativeConverter r2 = null;
        if (r14 != null) goto L5;
        Object[] r4 = r1;
    L10:
        TypeMapper r7 = (TypeMapper) r15.get(Library.OPTION_TYPE_MAPPER);
        boolean r8 = Boolean.TRUE.equals(r15.get(Library.OPTION_ALLOW_OBJECTS));
        if (r4.length <= 0) goto L14;
        if (r11 == null) goto L14;
        boolean r152 = isVarArgs(r11);
    L16:
        if (r4.length <= 0) goto L19;
        if (r11 == null) goto L19;
        int r16 = fixedArgs(r11);
    L20:
        int r5 = 0;
    L22:
        if (r5 >= r4.length) goto L32;
        if (r11 == null) goto L30;
        if (r152 == true) goto L26;
    L29:
        Class<?> r3 = r12[r5];
    L28:
        Method r6 = r11;
        Class<?> r9 = r3;
        Function r32 = this;
    L31:
        r4[r5] = r32.convertArgument(r4, r5, r6, r7, r8, r9);
        r5 = r5 + 1;
        Function this = r32;
        r11 = r6;
        goto L22
    L26:
        if (r5 < (r12.length - 1)) goto L29;
        r3 = r12[r12.length - 1].getComponentType();
        goto L28
    L30:
        r32 = this;
        r6 = r11;
        r9 = null;
        goto L31
    L32:
        Function r33 = this;
        Method r62 = r11;
        if (NativeMapped.class.isAssignableFrom(r13) == false) goto L35;
        NativeMappedConverter r22 = NativeMappedConverter.getInstance(r13);
        Class<?> r10 = r22.nativeType();
        FromNativeConverter r23 = r22;
    L40:
        Object r102 = r33.invoke(r4, r10, r8, r16);
        if (r23 == null) goto L46;
        if (r62 == null) goto L44;
        FromNativeContext r112 = new MethodResultContext(r13, r33, r14, r62);
    L45:
        r102 = r23.fromNative(r102, r112);
        goto L46
    L44:
        r112 = new FunctionResultContext(r13, r33, r14);
    L46:
        if (r14 == null) goto L72;
        int r113 = 0;
    L49:
        if (r113 >= r14.length) goto L72;
        Object r122 = r14[r113];
        if (r122 == null) goto L71;
        if ((r122 instanceof Structure) == true) goto L56;
        Object r132 = r4[r113];
        if ((r132 instanceof PostCallRead) == false) goto L69;
        ((PostCallRead) r132).read();
        Object r133 = r4[r113];
        if ((r133 instanceof PointerArray) == false) goto L71;
        PointerArray r134 = (PointerArray) r133;
        if (Structure.ByReference[].class.isAssignableFrom(r122.getClass()) == false) goto L71;
        Class<?> r153 = r122.getClass().getComponentType();
        Structure[] r123 = (Structure[]) r122;
        int r17 = 0;
    L66:
        if (r17 >= r123.length) goto L71;
        r123[r17] = Structure.updateStructureByReference(r153, r123[r17], r134.getPointer(Native.POINTER_SIZE * r17));
        r17 = r17 + 1;
        goto L66
    L69:
        if (Structure[].class.isAssignableFrom(r122.getClass()) == false) goto L71;
        Structure.autoRead((Structure[]) r122);
        goto L71
    L56:
        if ((r122 instanceof Structure.ByValue) == true) goto L71;
        ((Structure) r122).autoRead();
    L71:
        r113 = r113 + 1;
    L72:
        return r102;
    L35:
        if (r7 == null) goto L39;
        FromNativeConverter r24 = r7.getFromNativeConverter(r13);
        r2 = r24;
        if (r24 == null) goto L39;
        r10 = r24.nativeType();
        r23 = r24;
    L39:
        r10 = r13;
        r23 = r2;
    L19:
        r16 = 0;
    L14:
        r152 = false;
        goto L16
    L5:
        if (r14.length > 256) goto L7;
        int r18 = r14.length;
        Object[] r34 = new Object[r18];
        System.arraycopy(r14, 0, r34, 0, r18);
        r4 = r34;
        goto L10
    L7:
        ByteString$$ExternalSyntheticBUOutline0.m("Maximum argument count is 256");
        return null;
    }

    public Object invoke(Object[] r2, Class<?> r3, boolean r4) {
        return invoke(r2, r3, r4, 0);
    }

    public Object invoke(Object[] r8, Class<?> r9, boolean r10, int r11) {
        int r4 = this.callFlags | ((r11 & 255) << 7);
        if (r9 == null) goto L132;
        if (r9 == Void.TYPE) goto L132;
        if (r9 == Void.class) goto L132;
        boolean r1 = true;
        int r2 = 0;
        if (r9 == Boolean.TYPE) goto L127;
        if (r9 == Boolean.class) goto L127;
        if (r9 == Byte.TYPE) goto L125;
        if (r9 == Byte.class) goto L125;
        if (r9 == Short.TYPE) goto L123;
        if (r9 == Short.class) goto L123;
        if (r9 == Character.TYPE) goto L121;
        if (r9 == Character.class) goto L121;
        if (r9 == Integer.TYPE) goto L119;
        if (r9 == Integer.class) goto L119;
        if (r9 == Long.TYPE) goto L117;
        if (r9 == Long.class) goto L117;
        if (r9 == Float.TYPE) goto L115;
        if (r9 == Float.class) goto L115;
        if (r9 == Double.TYPE) goto L113;
        if (r9 == Double.class) goto L113;
        if (r9 != String.class) goto L54;
        return invokeString(r4, r8, false);
    L54:
        if (r9 != WString.class) goto L60;
        String r7 = invokeString(r4, r8, true);
        if (r7 != null) goto L58;
    L100:
        return null;
    L58:
        return new WString(r7);
    L60:
        if (Pointer.class.isAssignableFrom(r9) == false) goto L64;
        return invokePointer(r4, r8);
    L64:
        if (Structure.class.isAssignableFrom(r9) == false) goto L74;
        if (Structure.ByValue.class.isAssignableFrom(r9) == false) goto L69;
        Structure r72 = Native.invokeStructure(this, this.peer, r4, r8, Structure.newInstance(r9));
        r72.autoRead();
        return r72;
    L69:
        Pointer r73 = invokePointer(r4, r8);
        if (r73 == null) goto L135;
        Structure r74 = Structure.newInstance(r9, r73);
        r74.conditionalAutoRead();
        return r74;
    L135:
        return r73;
    L74:
        if (Callback.class.isAssignableFrom(r9) == false) goto L80;
        Pointer r75 = invokePointer(r4, r8);
        if (r75 != null) goto L78;
        return r75;
    L78:
        return CallbackReference.getCallback(r9, r75);
    L80:
        if (r9 != String[].class) goto L86;
        Pointer r82 = invokePointer(r4, r8);
        if (r82 == null) goto L100;
        return r82.getStringArray(0, this.encoding);
    L86:
        if (r9 != WString[].class) goto L95;
        Pointer r76 = invokePointer(r4, r8);
        if (r76 == null) goto L100;
        String[] r77 = r76.getWideStringArray(0);
        WString[] r83 = new WString[r77.length];
    L91:
        if (r2 >= r77.length) goto L93;
        r83[r2] = new WString(r77[r2]);
        r2 = r2 + 1;
        goto L91
    L93:
        return r83;
    L95:
        if (r9 != Pointer[].class) goto L101;
        Pointer r78 = invokePointer(r4, r8);
        if (r78 == null) goto L100;
        return r78.getPointerArray(0);
    L101:
        if (r10 == false) goto L110;
        Object r79 = Native.invokeObject(this, this.peer, r4, r8);
        if (r79 != null) goto L105;
    L109:
        return r79;
    L105:
        if (r9.isAssignableFrom(r79.getClass()) == true) goto L109;
        StringBuilder r102 = new StringBuilder("Return type ");
        r102.append(r9);
        Class<?> r710 = r79.getClass();
        r102.append(" does not match result ");
        r102.append(r710);
        throw new ClassCastException(r102.toString());
    L110:
        StringBuilder r84 = new StringBuilder("Unsupported return type ");
        r84.append(r9);
        Utils$$ExternalSyntheticBUOutline1.m(r84, " in function ", getName());
        return null;
    L113:
        return Double.valueOf(Native.invokeDouble(this, this.peer, r4, r8));
    L115:
        return Float.valueOf(Native.invokeFloat(this, this.peer, r4, r8));
    L117:
        return Long.valueOf(Native.invokeLong(this, this.peer, r4, r8));
    L119:
        return Integer.valueOf(Native.invokeInt(this, this.peer, r4, r8));
    L121:
        return Character.valueOf((char) Native.invokeInt(this, this.peer, r4, r8));
    L123:
        return Short.valueOf((short) Native.invokeInt(this, this.peer, r4, r8));
    L125:
        return Byte.valueOf((byte) Native.invokeInt(this, this.peer, r4, r8));
    L127:
        if (Native.invokeInt(this, this.peer, r4, r8) != 0) goto L131;
        r1 = false;
    L131:
        return valueOf(r1);
    L132:
        Native.invokeVoid(this, this.peer, r4, r8);
        return null;
    }

    public void invoke(Object[] r2) {
        invoke(Void.class, r2);
    }

    public double invokeDouble(Object[] r2) {
        return ((Double) invoke(Double.class, r2)).doubleValue();
    }

    public float invokeFloat(Object[] r2) {
        return ((Float) invoke(Float.class, r2)).floatValue();
    }

    public int invokeInt(Object[] r2) {
        return ((Integer) invoke(Integer.class, r2)).intValue();
    }

    public long invokeLong(Object[] r2) {
        return ((Long) invoke(Long.class, r2)).longValue();
    }

    public Object invokeObject(Object[] r2) {
        return invoke(Object.class, r2);
    }

    public Pointer invokePointer(Object[] r2) {
        return (Pointer) invoke(Pointer.class, r2);
    }

    public String invokeString(Object[] r1, boolean r2) {
        if (r2 == false) goto L4;
        Class<?> r22 = WString.class;
    L5:
        Object r0 = invoke(r22, r1);
        if (r0 != null) goto L8;
        return null;
    L8:
        return r0.toString();
    L4:
        r22 = String.class;
        goto L5
    }

    public void invokeVoid(Object[] r2) {
        invoke(Void.class, r2);
    }

    @Override // com.sun.jna.Pointer
    public String toString() {
        if (this.library == null) goto L7;
        return "native function " + this.functionName + "(" + this.library.getName() + ")@0x" + Long.toHexString(this.peer);
    L7:
        return "native function@0x" + Long.toHexString(this.peer);
    }
}
