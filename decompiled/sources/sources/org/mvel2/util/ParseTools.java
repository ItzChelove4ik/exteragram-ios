package org.mvel2.util;

import c.a$$ExternalSyntheticBUOutline0;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.MVEL;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.mvel2.OptimizationFailure;
import org.mvel2.ParserContext;
import org.mvel2.asm.signature.SignatureVisitor;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.BlankLiteral;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExecutableAccessor;
import org.mvel2.compiler.ExecutableAccessorSafe;
import org.mvel2.compiler.ExecutableLiteral;
import org.mvel2.compiler.ExpressionCompiler;
import org.mvel2.integration.ResolverTools;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.ClassImportResolverFactory;
import org.mvel2.math.MathProcessor;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ParseTools {
    private static final Map<Class, WeakReference<Constructor[]>> CLASS_CONSTRUCTOR_CACHE = null;
    private static final Map<ClassLoader, Map<String, WeakReference<Class>>> CLASS_RESOLVER_CACHE = null;
    private static final Map<Constructor, WeakReference<Class[]>> CONSTRUCTOR_PARMS_CACHE = null;
    public static final Class[] EMPTY_CLS_ARR = null;
    public static final Object[] EMPTY_OBJ_ARR = null;
    private static final Map<Class, Integer> typeCodes = null;
    private static final HashMap<Class, Integer> typeResolveMap = null;

    static {
        EMPTY_OBJ_ARR = new Object[0];
        EMPTY_CLS_ARR = new Class[0];
        CONSTRUCTOR_PARMS_CACHE = Collections.synchronizedMap(new WeakHashMap(10));
        CLASS_RESOLVER_CACHE = Collections.synchronizedMap(new WeakHashMap(1, 1.0f));
        CLASS_CONSTRUCTOR_CACHE = Collections.synchronizedMap(new WeakHashMap(10));
        HashMap<Class, Integer> r0 = new HashMap();
        typeResolveMap = r0;
        r0.put(BigDecimal.class, 110);
        r0.put(BigInteger.class, 111);
        r0.put(String.class, 1);
        Class r8 = Integer.TYPE;
        r0.put(r8, 101);
        r0.put(Integer.class, 106);
        Class r12 = Short.TYPE;
        r0.put(r12, 100);
        r0.put(Short.class, 105);
        Class r11 = Float.TYPE;
        r0.put(r11, 104);
        r0.put(Float.class, 108);
        Class r7 = Double.TYPE;
        r0.put(r7, 103);
        r0.put(Double.class, 109);
        Class r4 = Long.TYPE;
        r0.put(r4, 102);
        r0.put(Long.class, 107);
        Class r5 = Boolean.TYPE;
        r0.put(r5, 7);
        r0.put(Boolean.class, 15);
        Class r1 = Byte.TYPE;
        r0.put(r1, 9);
        r0.put(Byte.class, 113);
        Class r2 = Character.TYPE;
        r0.put(r2, 8);
        r0.put(Character.class, 112);
        r0.put(BlankLiteral.class, 200);
        HashMap r02 = new HashMap(30, 0.5f);
        typeCodes = r02;
        r02.put(Integer.class, 106);
        r02.put(Double.class, 109);
        r02.put(Boolean.class, 15);
        r02.put(String.class, 1);
        r02.put(Long.class, 107);
        r02.put(Short.class, 105);
        r02.put(Float.class, 108);
        r02.put(Byte.class, 113);
        r02.put(Character.class, 112);
        r02.put(BigDecimal.class, 110);
        r02.put(BigInteger.class, 111);
        r02.put(r8, 101);
        r02.put(r7, 103);
        r02.put(r5, 7);
        r02.put(r4, 102);
        r02.put(r12, 100);
        r02.put(r11, 104);
        r02.put(r1, 9);
        r02.put(r2, 8);
        r02.put(BlankLiteral.class, 200);
    }

    public ParseTools() {
    }

    public static int __resolveType(Class r1) {
        Integer r0 = typeCodes.get(r1);
        if (r0 != null) goto L12;
        if (r1 != null) goto L6;
        return 0;
    L6:
        if (Collection.class.isAssignableFrom(r1) == false) goto L13;
        return 50;
    L13:
        return 0;
    L12:
        return r0.intValue();
    }

    private static Serializable _optimizeTree(CompiledExpression r2) {
        if (r2.isSingleNode() == false) goto L16;
        ASTNode r0 = r2.getFirstNode();
        if (r0.isLiteral() == false) goto L11;
        if (r0.isThisVal() == true) goto L11;
        return new ExecutableLiteral(r0.getLiteralValue());
    L11:
        if (r0.canSerializeAccessor() == false) goto L15;
        return new ExecutableAccessorSafe(r0, r2.getKnownEgressType());
    L15:
        return new ExecutableAccessor(r0, r2.getKnownEgressType());
    L16:
        return r2;
    }

    public static int balancedCapture(char[] r1, int r2, char r3) {
        return balancedCapture(r1, r2, r1.length, r3);
    }

    public static int balancedCapture(char[] r9, int r10, int r11, char r12) {
        if (r12 == '(') goto L9;
        if (r12 == '[') goto L8;
        if (r12 == '{') goto L7;
        char r3 = r12;
    L11:
        if (r12 != r3) goto L21;
        int r102 = r10 + 1;
    L13:
        if (r102 >= r11) goto L65;
        if (r9[r102] != '\\') goto L18;
        r102 = skipStringEscape(r102);
    L18:
        if (r9[r102] == r12) goto L19;
        r102 = r102 + 1;
        goto L13
    L19:
        return r102;
    L65:
        if (r12 == '(') goto L74;
        if (r12 == '[') goto L72;
        if (r12 == '{') goto L70;
        Sign$$ExternalSyntheticBUOutline0.m("unterminated string literal", r9, r102);
        return 0;
    L70:
        Sign$$ExternalSyntheticBUOutline0.m("unbalanced braces { ... }", r9, r102);
        return 0;
    L72:
        Sign$$ExternalSyntheticBUOutline0.m("unbalanced braces [ ... ]", r9, r102);
        return 0;
    L74:
        Sign$$ExternalSyntheticBUOutline0.m("unbalanced braces ( ... )", r9, r102);
        return 0;
    L21:
        r102 = r10 + 1;
        int r5 = 1;
    L22:
        if (r102 >= r11) goto L65;
        if (r102 < r11) goto L25;
    L49:
        if (r102 == r11) goto L61;
        char r6 = r9[r102];
        if (r6 != '\'') goto L54;
    L62:
        r102 = captureStringLiteral(r6, r9, r102, r11);
    L63:
        r102 = r102 + 1;
        goto L22
    L54:
        if (r6 == '\"') goto L62;
        if (r6 != r12) goto L58;
        r5 = r5 + 1;
        goto L63
    L58:
        if (r6 != r3) goto L63;
        r5 = r5 - 1;
        if (r5 != 0) goto L63;
    L61:
        return r102;
    L25:
        if (r9[r102] != '/') goto L49;
        int r62 = r102 + 1;
        if (r62 == r11) goto L28;
        char r8 = r9[r62];
        if (r8 != '/') goto L37;
        r102 = r62;
    L32:
        if (r102 >= r11) goto L49;
        if (r9[r102] == '\n') goto L49;
        r102 = r102 + 1;
        goto L32
    L37:
        if (r8 != '*') goto L49;
        r102 = r102 + 2;
    L39:
        if (r102 >= r11) goto L49;
        if (r9[r102] != '*') goto L48;
        int r82 = r102 + 1;
        if (r82 >= r11) goto L48;
        if (r9[r82] == '/') goto L49;
    L48:
        r102 = r102 + 1;
        goto L39
    L28:
        return r102;
    L7:
        r3 = '}';
        goto L11
    L8:
        r3 = ']';
        goto L11
    L9:
        r3 = ')';
        goto L11
    }

    public static int balancedCaptureWithLineAccounting(char[] r18, int r19, int r20, char r21, ParserContext r22) {
        if (r21 == '(') goto L9;
        if (r21 == '[') goto L8;
        if (r21 == '{') goto L7;
        char r8 = r21;
    L10:
        if (r21 != r8) goto L18;
        int r4 = r19 + 1;
    L12:
        if (r4 == r20) goto L79;
        if (r18[r4] == r21) goto L15;
        r4 = r4 + 1;
        goto L12
    L15:
        return r4;
    L79:
        if (r21 == '(') goto L89;
        if (r21 != '[') goto L82;
        Sign$$ExternalSyntheticBUOutline0.m("unbalanced braces [ ... ]", r18, r19);
        return 0;
    L82:
        if (r21 == '{') goto L85;
        Sign$$ExternalSyntheticBUOutline0.m("unterminated string literal", r18, r19);
        return 0;
    L85:
        Sign$$ExternalSyntheticBUOutline0.m("unbalanced braces { ... }", r18, r19);
        return 0;
    L89:
        Sign$$ExternalSyntheticBUOutline0.m("unbalanced braces ( ... )", r18, r19);
        return 0;
    L18:
        int r10 = r19 + 1;
        int r12 = 0;
        int r13 = 1;
    L19:
        if (r10 >= r20) goto L79;
        if (isWhitespace(r18[r10]) == false) goto L31;
        char r14 = r18[r10];
        if (r14 == '\n') goto L27;
        if (r14 != '\r') goto L62;
    L78:
        r10 = r10 + 1;
    L62:
        if (r10 == r20) goto L76;
        char r5 = r18[r10];
        if (r5 != '\'') goto L67;
    L77:
        r10 = captureStringLiteral(r5, r18, r10, r20);
        goto L78
    L67:
        if (r5 == '\"') goto L77;
        if (r5 != r21) goto L71;
        r13 = r13 + 1;
        goto L78
    L71:
        if (r5 != r8) goto L78;
        r13 = r13 - 1;
        if (r13 != 0) goto L78;
        if (r22 == null) goto L76;
        r22.incrementLineCount(r12);
    L76:
        return r10;
    L27:
        if (r22 == null) goto L29;
        r22.setLineOffset((short) r10);
    L29:
        r12 = r12 + 1;
        goto L62
    L31:
        if (r10 >= r20) goto L62;
        if (r18[r10] != '/') goto L62;
        int r142 = r10 + 1;
        if (r142 == r20) goto L36;
        char r52 = r18[r142];
        if (r52 != '/') goto L45;
        r10 = r142;
    L40:
        if (r10 >= r20) goto L62;
        if (r18[r10] == '\n') goto L62;
        r10 = r10 + 1;
        goto L40
    L45:
        if (r52 != '*') goto L62;
        r10 = r10 + 2;
    L47:
        if (r10 == r20) goto L62;
        char r53 = r18[r10];
        if (r53 == '\n') goto L58;
        if (r53 == '\r') goto L58;
        if (r53 != '*') goto L61;
        int r54 = r10 + 1;
        if (r54 >= r20) goto L58;
        if (r18[r54] != '/') goto L58;
    L61:
        r10 = r10 + 1;
    L58:
        if (r22 == null) goto L60;
        r22.setLineOffset((short) r10);
    L60:
        r12 = r12 + 1;
        goto L61
    L36:
        return r10;
    L7:
        r8 = '}';
        goto L10
    L8:
        r8 = ']';
        goto L10
    L9:
        r8 = ')';
        goto L10
    }

    public static Class<?> boxPrimitive(Class r2) {
        Class r1 = Integer.class;
        if (r2 == Integer.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Integer[].class;
        if (r2 == int[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Character.class;
        if (r2 == Character.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Character[].class;
        if (r2 == char[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Long.class;
        if (r2 == Long.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Long[].class;
        if (r2 == long[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Short.class;
        if (r2 == Short.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Short[].class;
        if (r2 == short[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Double.class;
        if (r2 == Double.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Double[].class;
        if (r2 == double[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Float.class;
        if (r2 == Float.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Float[].class;
        if (r2 == float[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Boolean.class;
        if (r2 == Boolean.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Boolean[].class;
        if (r2 == boolean[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Byte.class;
        if (r2 == Byte.TYPE) goto L67;
        if (r2 == r1) goto L67;
        r1 = Byte[].class;
        if (r2 == byte[].class) goto L67;
        if (r2 == r1) goto L67;
        return r2;
    L67:
        return r1;
    }

    public static String[] captureContructorAndResidual(char[] r6, int r7, int r8) {
        int r0 = r7 + r8;
        boolean r1 = false;
        int r3 = r7;
        int r2 = 0;
    L3:
        if (r3 >= r0) goto L21;
        char r4 = r6[r3];
        if (r4 != '\"') goto L7;
        r1 = !r1;
    L19:
        r3 = r3 + 1;
        goto L3
    L7:
        if (r4 != '(') goto L9;
        r2 = r2 + 1;
        goto L19
    L9:
        if (r4 != ')') goto L19;
        if (r1 == true) goto L19;
        int r42 = r2 - 1;
        if (1 == r2) goto L14;
        r2 = r42;
        goto L19
    L14:
        int r32 = r3 + 1;
        return new String[]{createStringTrimmed(r6, r7, r32 - r7), createStringTrimmed(r6, r32, r0 - r32)};
    L21:
        return new String[]{new String(r6, r7, r8)};
    }

    public static int captureStringLiteral(char r3, char[] r4, int r5, int r6) {
    L2:
        int r0 = r5 + 1;
        if (r0 >= r6) goto L10;
        char r1 = r4[r0];
        if (r1 == r3) goto L10;
        if (r1 == '\\') goto L8;
        r5 = r0;
        goto L2
    L8:
        r5 = r5 + 2;
    L10:
        if (r0 < r6) goto L12;
    L14:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated string literal", r4, r0);
        return 0;
    L12:
        if (r4[r0] != r3) goto L14;
        return r0;
    }

    public static int captureToEOS(char[] r2, int r3, int r4, ParserContext r5) {
    L3:
        if (r3 == r2.length) goto L26;
        char r0 = r2[r3];
        if (r0 != '\"') goto L7;
    L24:
        r3 = captureStringLiteral(r0, r2, r3, r2.length);
    L25:
        r3 = r3 + 1;
        goto L3
    L7:
        if (r0 == ',') goto L26;
        if (r0 == ';') goto L26;
        if (r0 != '[') goto L13;
    L21:
        r3 = balancedCaptureWithLineAccounting(r2, r3, r4, r0, r5);
        if (r3 < r2.length) goto L25;
        return r3;
    L13:
        if (r0 == '{') goto L21;
        if (r0 == '}') goto L26;
        if (r0 == '\'') goto L24;
        if (r0 == '(') goto L21;
    L26:
        return r3;
    }

    public static int captureToNextTokenJunction(char[] r2, int r3, int r4, ParserContext r5) {
    L3:
        if (r3 == r2.length) goto L15;
        char r0 = r2[r3];
        if (r0 == '(') goto L15;
        if (r0 != '[') goto L9;
        r3 = balancedCaptureWithLineAccounting(r2, r3, r4, '[', r5);
    L13:
        r3 = r3 + 1;
        goto L3
    L9:
        if (r0 == '{') goto L15;
        if (isWhitespace(r0) == false) goto L13;
    L15:
        return r3;
    }

    public static void checkNameSafety(String r1) {
        if (isReservedWord(r1) == false) goto L5;
        MVEL$$ExternalSyntheticBUOutline0.m("illegal use of reserved word: ", r1);
        return;
    L5:
        if (isDigit(r1.charAt(0)) == true) goto L7;
        return;
    L7:
        GlShader$$ExternalSyntheticBUOutline1.m("not an identifier: ".concat(r1));
    }

    private static int comparePrecision(Class<?> r7, Class<?> r8) {
        if (r7 != r8) goto L6;
        return 0;
    L6:
        if (r7 != BigDecimal.class) goto L8;
        return 1;
    L8:
        Class<?> r0 = Double.TYPE;
        Class<?> r2 = Float.TYPE;
        Class<?> r4 = Long.TYPE;
        Class<?> r5 = Short.TYPE;
        Class<?> r6 = Integer.TYPE;
        if (r7 != r0) goto L16;
        if (r8 == r2) goto L15;
        if (r8 == r4) goto L15;
        if (r8 == r6) goto L15;
        if (r8 == r5) goto L15;
        if (r8 != BigInteger.class) goto L16;
    L15:
        return 1;
    L16:
        if (r7 != r2) goto L22;
        if (r8 == r4) goto L21;
        if (r8 == r6) goto L21;
        if (r8 == r5) goto L21;
        if (r8 != BigInteger.class) goto L22;
    L21:
        return 1;
    L22:
        if (r7 != BigInteger.class) goto L27;
        if (r8 == r4) goto L26;
        if (r8 == r6) goto L26;
        if (r8 != r5) goto L27;
    L26:
        return 1;
    L27:
        if (r7 != r4) goto L31;
        if (r8 == r6) goto L30;
        if (r8 != r5) goto L31;
    L30:
        return 1;
    L31:
        if (r7 != r6) goto L34;
        if (r8 != r5) goto L36;
        return 1;
    L36:
        return -1;
    L34:
        return -1;
    }

    public static boolean containsCheck(Object r6, Object r7) {
        if (r6 != null) goto L6;
        return false;
    L6:
        if ((r6 instanceof String) == false) goto L10;
        return ((String) r6).contains(String.valueOf(r7));
    L10:
        if ((r6 instanceof Collection) == false) goto L14;
        return ((Collection) r6).contains(r7);
    L14:
        if ((r6 instanceof Map) == false) goto L18;
        return ((Map) r6).containsKey(r7);
    L18:
        if (r6.getClass().isArray() == true) goto L20;
    L33:
        return false;
    L20:
        if (r6.getClass().getComponentType().isPrimitive() == true) goto L22;
        Object[] r62 = (Object[]) r6;
        int r1 = r62.length;
        int r2 = 0;
    L24:
        if (r2 >= r1) goto L33;
        Object r3 = r62[r2];
        if (r7 != null) goto L30;
        if (r3 != null) goto L30;
        return true;
    L30:
        if (((Boolean) MathProcessor.doOperations(r3, 18, r7)).booleanValue() == true) goto L31;
        r2 = r2 + 1;
        goto L24
    L31:
        return true;
    L22:
        return containsCheckOnPrimitveArray(r6, r7);
    }

    private static boolean containsCheckOnBooleanArray(boolean[] r4, Boolean r5) {
        boolean r52 = r5.booleanValue();
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r52) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static boolean containsCheckOnByteArray(byte[] r4, Byte r5) {
        byte r52 = r5.byteValue();
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r52) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static boolean containsCheckOnCharArray(char[] r4, Character r5) {
        char r52 = r5.charValue();
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r52) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static boolean containsCheckOnDoubleArray(double[] r6, Double r7) {
        double r0 = r7.doubleValue();
        int r72 = r6.length;
        int r3 = 0;
    L3:
        if (r3 >= r72) goto L9;
        if (r6[r3] == r0) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static boolean containsCheckOnFloatArray(float[] r4, Float r5) {
        float r52 = r5.floatValue();
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r52) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static boolean containsCheckOnIntArray(int[] r4, Integer r5) {
        int r52 = r5.intValue();
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r52) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static boolean containsCheckOnLongArray(long[] r6, Long r7) {
        long r0 = r7.longValue();
        int r72 = r6.length;
        int r3 = 0;
    L3:
        if (r3 >= r72) goto L9;
        if (r6[r3] == r0) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    private static boolean containsCheckOnPrimitveArray(Object r4, Object r5) {
        Class<?> r0 = r4.getClass().getComponentType();
        if (r0 != Boolean.TYPE) goto L11;
        if ((r5 instanceof Boolean) == true) goto L7;
    L9:
        return false;
    L7:
        if (containsCheckOnBooleanArray((boolean[]) r4, (Boolean) r5) == false) goto L9;
        return true;
    L11:
        if (r0 != Integer.TYPE) goto L19;
        if ((r5 instanceof Integer) == true) goto L15;
    L17:
        return false;
    L15:
        if (containsCheckOnIntArray((int[]) r4, (Integer) r5) == false) goto L17;
        return true;
    L19:
        if (r0 != Long.TYPE) goto L27;
        if ((r5 instanceof Long) == true) goto L23;
    L25:
        return false;
    L23:
        if (containsCheckOnLongArray((long[]) r4, (Long) r5) == false) goto L25;
        return true;
    L27:
        if (r0 != Double.TYPE) goto L35;
        if ((r5 instanceof Double) == true) goto L31;
    L33:
        return false;
    L31:
        if (containsCheckOnDoubleArray((double[]) r4, (Double) r5) == false) goto L33;
        return true;
    L35:
        if (r0 != Float.TYPE) goto L43;
        if ((r5 instanceof Float) == true) goto L39;
    L41:
        return false;
    L39:
        if (containsCheckOnFloatArray((float[]) r4, (Float) r5) == false) goto L41;
        return true;
    L43:
        if (r0 != Character.TYPE) goto L51;
        if ((r5 instanceof Character) == true) goto L47;
    L49:
        return false;
    L47:
        if (containsCheckOnCharArray((char[]) r4, (Character) r5) == false) goto L49;
        return true;
    L51:
        if (r0 != Short.TYPE) goto L59;
        if ((r5 instanceof Short) == true) goto L55;
    L57:
        return false;
    L55:
        if (containsCheckOnShortArray((short[]) r4, (Short) r5) == false) goto L57;
        return true;
    L59:
        if (r0 == Byte.TYPE) goto L61;
    L65:
        return false;
    L61:
        if ((r5 instanceof Byte) == false) goto L65;
        if (containsCheckOnByteArray((byte[]) r4, (Byte) r5) == false) goto L65;
        return true;
    }

    private static boolean containsCheckOnShortArray(short[] r4, Short r5) {
        short r52 = r5.shortValue();
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2] == r52) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    public static Class createClass(String r4, ParserContext r5) {
        if (r5 == null) goto L4;
        ClassLoader r52 = r5.getClassLoader();
    L5:
        Map<ClassLoader, Map<String, WeakReference<Class>>> r0 = CLASS_RESOLVER_CACHE;
        Map<String, WeakReference<Class>> r1 = r0.get(r52);
        if (r1 != null) goto L8;
        r1 = Collections.synchronizedMap(new WeakHashMap(10));
        r0.put(r52, r1);
    L8:
        WeakReference<Class> r02 = r1.get(r4);
        if (r02 == null) goto L23;
        Class r03 = r02.get();
        if (r03 == null) goto L23;
        return r03;
    L23:
        Class<?> r53 = Class.forName(r4, true, r52);     // Catch: ClassNotFoundException -> L16
    L20:
        r1.put(r4, new WeakReference(r53));
        return r53;
    L16:
        e = move-exception;
        if (r52 == Thread.currentThread().getContextClassLoader()) goto L22;
        r53 = Class.forName(r4, true, Thread.currentThread().getContextClassLoader());
        goto L20
    L22:
        throw e;
    L4:
        r52 = Thread.currentThread().getContextClassLoader();
        goto L5
    }

    public static char[] createShortFormOperativeAssignment(String r5, char[] r6, int r7, int r8, int r9) {
        if (r9 != (-1)) goto L6;
        return r6;
    L6:
        if (r9 == 0) goto L32;
        if (r9 != 1) goto L9;
        char r92 = SignatureVisitor.SUPER;
    L33:
        char[] r2 = r5.toCharArray();
        char[] r3 = new char[(r5.length() + r8) + 1];
        System.arraycopy(r2, 0, r3, 0, r5.length());
        r3[r5.length()] = r92;
        System.arraycopy(r6, r7, r3, r5.length() + 1, r8);
        return r3;
    L9:
        if (r9 != 2) goto L11;
        r92 = '*';
        goto L33
    L11:
        if (r9 != 3) goto L13;
        r92 = '/';
        goto L33
    L13:
        if (r9 != 4) goto L15;
        r92 = '%';
        goto L33
    L15:
        if (r9 != 6) goto L17;
        r92 = Typography.amp;
        goto L33
    L17:
        if (r9 != 7) goto L19;
        r92 = '|';
        goto L33
    L19:
        if (r9 == 20) goto L25;
        switch(r9) {
            case 9: goto L24;
            case 10: goto L23;
            case 11: goto L22;
            default: goto L21;
        };
    L21:
        r92 = 0;
        goto L33
    L22:
        r92 = 172;
        goto L33
    L23:
        r92 = 171;
        goto L33
    L24:
        r92 = 187;
        goto L33
    L25:
        r92 = '#';
        goto L33
    L32:
        r92 = SignatureVisitor.EXTENDS;
        goto L33
    }

    public static String createStringTrimmed(char[] r4) {
        int r0 = r4.length;
        int r1 = 0;
    L4:
        if (r1 == r0) goto L8;
        if (r4[r1] >= '!') goto L8;
        r1 = r1 + 1;
    L8:
        if (r0 == r1) goto L13;
        if (r4[r0 - 1] >= '!') goto L13;
        r0 = r0 - 1;
    L13:
        return new String(r4, r1, r0 - r1);
    }

    public static String createStringTrimmed(char[] r2, int r3, int r4) {
        int r42 = r4 + r3;
        if (r42 <= r2.length) goto L7;
        return new String(r2);
    L7:
        if (r3 == r42) goto L11;
        if (r2[r3] >= '!') goto L11;
        r3 = r3 + 1;
    L11:
        if (r42 == r3) goto L16;
        if (r2[r42 - 1] >= '!') goto L16;
        r42 = r42 - 1;
    L16:
        return new String(r2, r3, r42 - r3);
    }

    private static Method determineActualTargetMethod(Class r10, Method r11) {
        String r0 = r11.getName();
        Class<?>[] r1 = r10.getInterfaces();
        int r2 = r1.length;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L14;
        Method[] r5 = r1[r4].getMethods();
        int r6 = r5.length;
        int r7 = 0;
    L5:
        if (r7 >= r6) goto L12;
        Method r8 = r5[r7];
        if (r8.getParameterTypes().length != 0) goto L11;
        if (r0.equals(r8.getName()) == false) goto L11;
        return r8;
    L11:
        r7 = r7 + 1;
        goto L5
    L12:
        r4 = r4 + 1;
        goto L3
    L14:
        if (r10.getSuperclass() != null) goto L16;
        return null;
    L16:
        return determineActualTargetMethod(r10.getSuperclass(), r11);
    }

    public static Method determineActualTargetMethod(Method r1) {
        return determineActualTargetMethod(r1.getDeclaringClass(), r1);
    }

    public static boolean endsWith(char[] r4, int r5, int r6, char[] r7) {
        if (r7.length <= r4.length) goto L5;
        return false;
    L5:
        int r0 = r7.length - 1;
        int r52 = (r5 + r6) - 1;
    L6:
        if (r0 < 0) goto L11;
        int r62 = r52 - 1;
        int r3 = r0 - 1;
        if (r4[r52] != r7[r0]) goto L9;
        r52 = r62;
        r0 = r3;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    public static int find(char[] r1, int r2, int r3, char r4) {
        int r32 = r3 + r2;
    L3:
        if (r2 >= r32) goto L8;
        if (r1[r2] == r4) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return -1;
    }

    public static int findAbsoluteLast(char[] r5) {
        int r0 = r5.length - 1;
        int r1 = 0;
    L3:
        if (r0 < 0) goto L16;
        char r2 = r5[r0];
        if (r2 != ']') goto L8;
        r1 = r1 + 1;
    L8:
        if (r2 != '[') goto L10;
        r1 = r1 - 1;
    L10:
        if (r1 == 0) goto L12;
    L13:
        if (r2 == '[') goto L14;
        r0 = r0 - 1;
    L14:
        return r0;
    L12:
        if (r2 == '.') goto L14;
    L16:
        return -1;
    }

    public static Class findClass(VariableResolverFactory r1, String r2, ParserContext r3) {
    L22:
        e = move-exception;
        throw e;
    L6:
        e = move-exception;
        a$$ExternalSyntheticBUOutline0.m("class not found: ", r2, e);
        return null;
    L3:
        if (AbstractParser.LITERALS.containsKey(r2) == true) goto L5;
        if (r1 != null) goto L10;
    L13:
        if (r3 == null) goto L19;
        if (r3.hasImport(r2) == false) goto L19;
        return r3.getImport(r2);
    L19:
        return createClass(r2, r3);
    L10:
        if (r1.isResolveable(r2) == false) goto L13;
        return (Class) r1.getVariableResolver(r2).getValue();
    L5:
        return (Class) AbstractParser.LITERALS.get(r2);
    }

    public static ClassImportResolverFactory findClassImportResolverFactory(VariableResolverFactory r2, ParserContext r3) {
        if (r2 == null) goto L13;
        VariableResolverFactory r32 = r2;
    L4:
        if (r32 == null) goto L11;
        if ((r32 instanceof ClassImportResolverFactory) == true) goto L8;
        r32 = r32.getNextFactory();
        goto L4
    L8:
        return (ClassImportResolverFactory) r32;
    L11:
        return (ClassImportResolverFactory) ResolverTools.appendFactory(r2, new ClassImportResolverFactory(null, null, false));
    L13:
        throw new OptimizationFailure("unable to import classes.  no variable resolver factory available.");
    }

    public static Class findInnerClass(String r4, ClassLoader r5, ClassNotFoundException r6) {
    L3:
        int r1 = r4.lastIndexOf(46);
        if (r1 <= 0) goto L8;
        r4 = r4.substring(0, r1) + "$" + r4.substring(r1 + 1);
        return r5.loadClass(r4);
    L8:
        throw r6;
    }

    public static int findLast(char[] r1, int r2, int r3, char r4) {
        int r32 = r3 + r2;
    L3:
        if (r32 < r2) goto L8;
        if (r1[r32] == r4) goto L6;
        r32 = r32 - 1;
        goto L3
    L6:
        return r32;
    L8:
        return -1;
    }

    public static Class forNameWithInner(String r1, ClassLoader r2) {
        return r2.loadClass(r1);
    L4:
        e = move-exception;
        return findInnerClass(r1, r2, e);
    }

    public static Class getBaseComponentType(Class r1) {
    L3:
        if (r1.isArray() == false) goto L5;
        r1 = r1.getComponentType();
        goto L3
    L5:
        return r1;
    }

    public static Method getBestCandidate(Class[] r6, String r7, Class r8, Method[] r9, boolean r10) {
        return getBestCandidate(r6, r7, r8, r9, r10, false);
    }

    public static Method getBestCandidate(Class[] r10, String r11, Class r12, Method[] r13, boolean r14, boolean r15) {
        Method r1 = null;
        if (r13.length != 0) goto L5;
        return null;
    L5:
        int r2 = -1;
        boolean r3 = false;
    L6:
        int r4 = r13.length;
        int r5 = 0;
    L7:
        if (r5 >= r4) goto L38;
        Method r6 = r13[r5];
        if (r15 == false) goto L14;
        if (Modifier.isStatic(r6.getModifiers()) == true) goto L14;
    L37:
        r5 = r5 + 1;
    L14:
        if (r11.equals(r6.getName()) == false) goto L37;
        Class<?>[] r7 = r6.getParameterTypes();
        if (r7.length == 0) goto L18;
    L23:
        boolean r8 = r6.isVarArgs();
        if (isArgsNumberNotCompatible(r10, r7, r8) == true) goto L37;
        int r72 = getMethodScore(r10, r14, r7, r8);
        if (r72 == 0) goto L37;
        if (r72 <= r2) goto L30;
        r1 = r6;
        r2 = r72;
        goto L37
    L30:
        if (r72 != r2) goto L37;
        if (isMoreSpecialized(r6, r1) == false) goto L34;
    L35:
        if (r8 == true) goto L37;
    L36:
        r1 = r6;
        goto L37
    L34:
        if (isMorePreciseForBigDecimal(r6, r1, r10) == false) goto L37;
    L18:
        if (r10.length != 0) goto L23;
        if (r1 == null) goto L36;
        if (isMoreSpecialized(r6, r1) == false) goto L37;
    L38:
        if (r1 != null) goto L52;
        if (r3 == true) goto L52;
        if (r12.isInterface() == false) goto L52;
        Method[] r32 = Object.class.getMethods();
        Method[] r42 = new Method[r13.length + r32.length];
        int r52 = 0;
    L45:
        if (r52 >= r13.length) goto L47;
        r42[r52] = r13[r52];
        r52 = r52 + 1;
        goto L45
    L47:
        int r53 = 0;
    L49:
        if (r53 >= r32.length) goto L51;
        r42[r13.length + r53] = r32[r53];
        r53 = r53 + 1;
        goto L49
    L51:
        r3 = true;
        r13 = r42;
    L52:
        return r1;
    }

    public static Method getBestCandidate(Object[] r3, String r4, Class r5, Method[] r6, boolean r7) {
        Class[] r0 = new Class[r3.length];
        int r1 = 0;
    L4:
        if (r1 == r3.length) goto L11;
        Object r2 = r3[r1];
        if (r2 == null) goto L8;
        Class<?> r22 = r2.getClass();
    L9:
        r0[r1] = r22;
        r1 = r1 + 1;
        goto L4
    L8:
        r22 = null;
        goto L9
    L11:
        return getBestCandidate(r0, r4, r5, r6, r7);
    }

    public static Constructor getBestConstructorCandidate(Class[] r9, Class r10, boolean r11) {
        Constructor[] r102 = getConstructors(r10);
        int r0 = r102.length;
        Constructor r1 = null;
        int r2 = 0;
        int r3 = 0;
        boolean r4 = false;
    L3:
        if (r2 >= r0) goto L23;
        Constructor r5 = r102[r2];
        boolean r6 = r5.isVarArgs();
        Class[] r7 = getConstructors(r5);
        if (isArgsNumberNotCompatible(r9, r7, r6) == true) goto L22;
        if (r9.length == 0) goto L10;
    L12:
        int r72 = getMethodScore(r9, r11, r7, r6);
        if (r72 == 0) goto L22;
        if (r72 <= r3) goto L16;
        r1 = r5;
        r4 = r6;
        r3 = r72;
        goto L22
    L16:
        if (r72 != r3) goto L22;
        if (isMorePreciseForBigDecimal(r5, r1, r9) == true) goto L21;
        if (r4 == false) goto L22;
        if (r6 == true) goto L22;
    L21:
        r1 = r5;
        r4 = r6;
        goto L22
    L10:
        if (r7.length != 0) goto L12;
        return r5;
    L22:
        r2 = r2 + 1;
        goto L3
    L23:
        return r1;
    }

    public static Constructor getBestConstructorCandidate(Object[] r3, Class r4, boolean r5) {
        Class[] r0 = new Class[r3.length];
        int r1 = 0;
    L4:
        if (r1 == r3.length) goto L10;
        Object r2 = r3[r1];
        if (r2 == null) goto L8;
        r0[r1] = r2.getClass();
    L8:
        r1 = r1 + 1;
        goto L4
    L10:
        return getBestConstructorCandidate(r0, r4, r5);
    }

    private static Class[] getConstructors(Constructor r3) {
        Map<Constructor, WeakReference<Class[]>> r0 = CONSTRUCTOR_PARMS_CACHE;
        WeakReference<Class[]> r1 = r0.get(r3);
        if (r1 == null) goto L7;
        Class[] r12 = r1.get();
        if (r12 == null) goto L7;
        return r12;
    L7:
        Class<?>[] r2 = r3.getParameterTypes();
        r0.put(r3, new WeakReference(r2));
        return r2;
    }

    public static Constructor[] getConstructors(Class r3) {
        Map<Class, WeakReference<Constructor[]>> r0 = CLASS_CONSTRUCTOR_CACHE;
        WeakReference<Constructor[]> r1 = r0.get(r3);
        if (r1 == null) goto L7;
        Constructor[] r12 = r1.get();
        if (r12 == null) goto L7;
        return r12;
    L7:
        Constructor<?>[] r2 = r3.getConstructors();
        r0.put(r3, new WeakReference(r2));
        return r2;
    }

    public static FileWriter getDebugFileWriter() {
        return new FileWriter(new File(MVEL.getDebuggingOutputFileName()), true);
    }

    public static Method getExactMatch(String r8, Class[] r9, Class r10, Class r11) {
        Method[] r112 = r11.getMethods();
        int r0 = r112.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L20;
        Method r3 = r112[r2];
        if (r8.equals(r3.getName()) == false) goto L19;
        if (r10 != r3.getReturnType()) goto L19;
        Class<?>[] r4 = r3.getParameterTypes();
        if (r4.length != r9.length) goto L19;
        int r5 = 0;
    L13:
        if (r5 >= r4.length) goto L18;
        if (r4[r5] != r9[r5]) goto L19;
        r5 = r5 + 1;
        goto L13
    L18:
        return r3;
    L19:
        r2 = r2 + 1;
        goto L3
    L20:
        return null;
    }

    private static int getMethodScore(Class[] r6, boolean r7, Class<?>[] r8, boolean r9) {
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 == r6.length) goto L60;
        if (r9 == true) goto L7;
    L9:
        Class r3 = r8[r1];
    L10:
        Class r4 = r6[r1];
        if (r4 == null) goto L13;
        if (r3 != r4) goto L18;
        r2 = r2 + 8;
    L59:
        r1 = r1 + 1;
        goto L4
    L18:
        if (r3.isPrimitive() == false) goto L23;
        if (boxPrimitive(r3) != r6[r1]) goto L23;
    L14:
        r2 = r2 + 7;
    L23:
        if (r6[r1].isPrimitive() == false) goto L28;
        if (unboxPrimitive(r6[r1]) == r3) goto L14;
    L28:
        if (r3.isAssignableFrom(r6[r1]) == false) goto L31;
        r2 = r2 + 6;
        goto L59
    L31:
        if (isPrimitiveSubtype(r6[r1], r3) == false) goto L34;
        r2 = r2 + 5;
        goto L59
    L34:
        if (isNumericallyCoercible(r6[r1], r3) == false) goto L37;
        r2 = r2 + 4;
        goto L59
    L37:
        if (boxPrimitive(r3).isAssignableFrom(boxPrimitive(r6[r1])) == false) goto L41;
        Class r42 = r6[r1];
        if (Object.class == r42) goto L41;
        r2 = r2 + (scoreInterface(r3, r42) + 3);
    L41:
        if (r7 == false) goto L43;
    L55:
        if (r3 != Object.class) goto L57;
    L54:
        r2 = r2 + 1;
        goto L59
    L57:
        if (r6[r1] == NullType.class) goto L54;
    L61:
        if (r0 != 0) goto L77;
        if (r9 == true) goto L64;
        return r0;
    L64:
        if ((r8.length - 1) == r6.length) goto L66;
        return r0;
    L66:
        return r0 + 3;
    L77:
        return r0;
    L43:
        if (DataConversion.canConvert(r3, r6[r1]) == false) goto L55;
        if (r3.isArray() == false) goto L50;
        if (r6[r1].isArray() == false) goto L50;
    L48:
        r2 = r2 + 1;
    L50:
        if (r3 != Character.TYPE) goto L54;
        if (r6[r1] != String.class) goto L54;
    L13:
        if (r3.isPrimitive() == true) goto L61;
    L7:
        if (r1 < (r8.length - 1)) goto L9;
        r3 = r8[r8.length - 1].getComponentType();
        goto L10
    L60:
        r0 = r2;
        goto L61
    }

    public static Class getSubComponentType(Class r1) {
        if (r1.isArray() == true) goto L5;
        return r1;
    L5:
        return r1.getComponentType();
    }

    public static Method getWidenedTarget(Class r9, Method r10) {
        if (Modifier.isStatic(r10.getModifiers()) == false) goto L5;
        return r10;
    L5:
        Class<?>[] r0 = r10.getParameterTypes();
        String r1 = r10.getName();
        Class<?> r2 = r10.getReturnType();
        Class r3 = r9;
        Method r4 = r10;
    L6:
        if (r3 == null) goto L14;
        Class<?>[] r5 = r3.getInterfaces();
        int r6 = r5.length;
        int r7 = 0;
    L8:
        if (r7 >= r6) goto L13;
        Method r8 = getExactMatch(r1, r0, r2, r5[r7]);
        if (r8 == null) goto L12;
        r4 = r8;
    L12:
        r7 = r7 + 1;
        goto L8
    L13:
        r3 = r3.getSuperclass();
        goto L6
    L14:
        if (r4 == r10) goto L16;
        return r4;
    L16:
        if (r9 == null) goto L21;
        Method r102 = getExactMatch(r1, r0, r2, r9);
        if (r102 == null) goto L20;
        r4 = r102;
    L20:
        r9 = r9.getSuperclass();
        goto L16
    L21:
        return r4;
    }

    public static Method getWidenedTarget(Method r1) {
        return getWidenedTarget(r1.getDeclaringClass(), r1);
    }

    public static int handleEscapeSequence(char[] r11, int r12) {
        int r0 = r12 - 1;
        r11[r0] = 0;
        char r2 = r11[r12];
        if (r2 != '\"') goto L5;
        r11[r12] = Typography.quote;
        return 1;
    L5:
        if (r2 != '\'') goto L7;
        r11[r12] = '\'';
        return 1;
    L7:
        if (r2 != '\\') goto L9;
        r11[r12] = '\\';
        return 1;
    L9:
        if (r2 != 'b') goto L11;
        r11[r12] = '\b';
        return 1;
    L11:
        if (r2 != 'f') goto L13;
        r11[r12] = '\f';
        return 1;
    L13:
        if (r2 != 'n') goto L15;
        r11[r12] = '\n';
        return 1;
    L15:
        if (r2 != 'r') goto L17;
        r11[r12] = '\r';
        return 1;
    L17:
        if (r2 != 't') goto L19;
        r11[r12] = '\t';
        return 1;
    L19:
        if (r2 == 'u') goto L44;
        int r22 = r12;
    L21:
        char r4 = r11[r22];
        if (r4 < '0') goto L43;
        if (r4 >= '8') goto L43;
        if (r22 != r12) goto L28;
    L31:
        int r9 = r22 - r12;
        if (r9 == 2) goto L33;
        r22 = r22 + 1;
        if (r22 == r11.length) goto L40;
        if (r4 < '0') goto L40;
        if (r4 <= '7') goto L21;
    L40:
        r11[r0] = (char) Integer.decode(MVEL.VERSION_SUB.concat(new String(r11, r12, r9 + 1))).intValue();
        r11[r12] = 0;
        return 1;
    L33:
        r11[r0] = (char) Integer.decode(MVEL.VERSION_SUB.concat(new String(r11, r12, r9 + 1))).intValue();
        r11[r12] = 0;
        r11[r12 + 1] = 0;
        r11[r12 + 2] = 0;
        return 3;
    L28:
        if (r11[r12] <= '3') goto L31;
        r11[r0] = (char) Integer.decode(MVEL.VERSION_SUB.concat(new String(r11, r12, (r22 - r12) + 1))).intValue();
        r11[r12] = 0;
        r11[r12 + 1] = 0;
        return 2;
    L43:
        throw new CompileException("illegal escape sequence: " + r11[r22], r11, r22);
    L44:
        int r23 = r12 + 4;
        if (r23 > r11.length) goto L62;
        int r42 = r12;
    L47:
        r42 = r42 + 1;
        if ((r42 - r12) == 5) goto L60;
        char r7 = r11[r42];
        if (r7 <= '/') goto L54;
        if (r7 < ':') goto L47;
    L54:
        if (r7 <= '@') goto L58;
        if (r7 < 'G') goto L47;
    L58:
        Sign$$ExternalSyntheticBUOutline0.m("illegal unicode escape sequence", r11, r42);
        return 0;
    L60:
        int r43 = r12 + 1;
        r11[r0] = (char) Integer.decode("0x".concat(new String(r11, r43, 4))).intValue();
        r11[r12] = 0;
        r11[r43] = 0;
        r11[r12 + 2] = 0;
        r11[r12 + 3] = 0;
        r11[r23] = 0;
        return 5;
    L62:
        Sign$$ExternalSyntheticBUOutline0.m("illegal unicode escape sequence", r11, r12);
        return 0;
    }

    public static Object handleNumericConversion(char[] r8, int r9, int r10) {
        if (r10 != 1) goto L5;
    L24:
        int r6 = (r9 + r10) - 1;
        if (isDigit(r8[r6]) == true) goto L53;
        char r62 = r8[r6];
        if (r62 == '.') goto L52;
        if (r62 == 'B') goto L50;
        if (r62 == 'D') goto L52;
        if (r62 == 'F') goto L48;
        if (r62 == 'I') goto L46;
        if (r62 == 'L') goto L44;
        if (r62 == 'd') goto L52;
        if (r62 == 'f') goto L48;
        if (r62 == 'l') goto L44;
        Sign$$ExternalSyntheticBUOutline0.m("unrecognized numeric literal", r8, r9);
        return null;
    L44:
        return Long.valueOf(Long.parseLong(new String(r8, r9, r10 - 1)));
    L46:
        return new BigInteger(new String(r8, r9, r10 - 1));
    L48:
        return Float.valueOf(Float.parseFloat(new String(r8, r9, r10 - 1)));
    L50:
        return new BigDecimal(new String(r8, r9, r10 - 1));
    L52:
        return Double.valueOf(Double.parseDouble(new String(r8, r9, r10 - 1)));
    L53:
        int r0 = numericTest(r8, r9, r10);
        if (r0 == 110) goto L67;
        switch(r0) {
            case 101: goto L65;
            case 102: goto L63;
            case 103: goto L61;
            case 104: goto L59;
            default: goto L57;
        };
    L57:
        return new String(r8, r9, r10);
    L59:
        return Float.valueOf(Float.parseFloat(new String(r8, r9, r10)));
    L61:
        return Double.valueOf(Double.parseDouble(new String(r8, r9, r10)));
    L63:
        return Long.valueOf(Long.parseLong(new String(r8, r9, r10)));
    L65:
        return Integer.valueOf(Integer.parseInt(new String(r8, r9, r10)));
    L67:
        return new BigDecimal(r8, MathContext.DECIMAL128);
    L5:
        if (r8[r9] != '0') goto L24;
        if (r8[r9 + 1] == '.') goto L24;
        int r4 = (r9 + r10) - 1;
        if (isDigit(r8[r4]) == true) goto L23;
        char r42 = r8[r4];
        if (r42 == 'B') goto L21;
        if (r42 == 'I') goto L19;
        if (r42 == 'L') goto L17;
        if (r42 != 'l') goto L23;
    L17:
        return Long.decode(new String(r8, r9, r10 - 1));
    L19:
        return new BigInteger(new String(r8, r9, r10 - 1));
    L21:
        return new BigDecimal(new String(r8, r9, r10 - 1));
    L23:
        return Integer.decode(new String(r8, r9, r10));
    }

    public static String handleStringEscapes(char[] r6) {
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r6.length) goto L9;
        if (r6[r1] != '\\') goto L8;
        r1 = r1 + 1;
        r2 = r2 + handleEscapeSequence(r6, r1);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        if (r2 == 0) goto L11;
        char[] r12 = new char[r6.length - r2];
        int r22 = r6.length;
        int r3 = 0;
    L13:
        if (r0 >= r22) goto L19;
        char r4 = r6[r0];
        if (r4 == 0) goto L17;
        r12[r3] = r4;
        r3 = r3 + 1;
    L17:
        r0 = r0 + 1;
        goto L13
    L19:
        return new String(r12);
    L11:
        return new String(r6);
    }

    private static boolean isArgsNumberNotCompatible(Class[] r3, Class<?>[] r4, boolean r5) {
        if (r5 == true) goto L5;
    L6:
        if (r5 == false) goto L8;
        return false;
    L8:
        if (r4.length == r3.length) goto L12;
    L9:
        return true;
    L12:
        return false;
    L5:
        if ((r4.length - 1) > r3.length) goto L9;
        goto L6
    }

    public static boolean isArrayType(char[] r1, int r2, int r3) {
        if (r3 <= (r2 + 2)) goto L11;
        int r0 = r3 - 2;
        if (isPropertyOnly(r1, r2, r0) == true) goto L7;
        return false;
    L7:
        if (r1[r0] == '[') goto L9;
        return false;
    L9:
        if (r1[r3 - 1] != ']') goto L15;
        return true;
    L15:
        return false;
    L11:
        return false;
    }

    public static boolean isDigit(int r1) {
        if (r1 > 47) goto L5;
        return false;
    L5:
        if (r1 >= 58) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isIdentifierPart(int r1) {
        if (r1 <= 96) goto L7;
        if (r1 >= 123) goto L7;
        return true;
    L7:
        if (r1 <= 64) goto L11;
        if (r1 >= 91) goto L11;
        return true;
    L11:
        if (r1 <= 47) goto L15;
        if (r1 >= 58) goto L15;
        return true;
    L15:
        if (r1 != 95) goto L17;
        return true;
    L17:
        if (r1 != 36) goto L19;
        return true;
    L19:
        if (Character.isJavaIdentifierPart(r1) == true) goto L29;
        return false;
    L29:
        return true;
    }

    public static boolean isJunct(char r1) {
        if (r1 != '(') goto L5;
        return true;
    L5:
        if (r1 != '[') goto L7;
        return true;
    L7:
        return isWhitespace(r1);
    }

    private static boolean isMorePreciseForBigDecimal(Executable r7, Executable r8, Class[] r9) {
        Class<?>[] r72 = r7.getParameterTypes();
        Class<?>[] r82 = r8.getParameterTypes();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 == r9.length) goto L14;
        Class<?> r3 = r72[r1];
        Class<?> r4 = r82[r1];
        if (r9[r1] != BigDecimal.class) goto L13;
        if (isNumeric(r4) == false) goto L13;
        if (isNumeric(r3) == false) goto L13;
        r2 = r2 + comparePrecision(unboxPrimitive(r3), unboxPrimitive(r4));
    L13:
        r1 = r1 + 1;
        goto L4
    L14:
        if (r2 <= 0) goto L17;
        return true;
    L17:
        return false;
    }

    private static boolean isMoreSpecialized(Method r2, Method r3) {
        if (r3.getReturnType().isAssignableFrom(r2.getReturnType()) == true) goto L5;
        return false;
    L5:
        if (r3.getDeclaringClass().isAssignableFrom(r2.getDeclaringClass()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isNotValidNameorLabel(String r6) {
        char[] r62 = r6.toCharArray();
        int r0 = r62.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L11;
        char r3 = r62[r2];
        if (r3 == '.') goto L6;
        if (isIdentifierPart(r3) == false) goto L9;
        r2 = r2 + 1;
        goto L3
    L9:
        return true;
    L6:
        return true;
    L11:
        return false;
    }

    public static boolean isNumber(Object r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if ((r2 instanceof String) == false) goto L10;
        return isNumber((String) r2);
    L10:
        if ((r2 instanceof char[]) == false) goto L14;
        return isNumber(new String((char[]) r2));
    L14:
        if ((r2 instanceof Integer) == false) goto L16;
        return true;
    L16:
        if ((r2 instanceof BigDecimal) == false) goto L18;
        return true;
    L18:
        if ((r2 instanceof BigInteger) == false) goto L20;
        return true;
    L20:
        if ((r2 instanceof Float) == false) goto L22;
        return true;
    L22:
        if ((r2 instanceof Double) == false) goto L24;
        return true;
    L24:
        if ((r2 instanceof Long) == false) goto L26;
        return true;
    L26:
        if ((r2 instanceof Short) == false) goto L28;
        return true;
    L28:
        if ((r2 instanceof Character) == true) goto L39;
        return false;
    L39:
        return true;
    }

    public static boolean isNumber(String r7) {
        int r0 = r7.length();
        if (r0 > 1) goto L5;
    L12:
        boolean r4 = true;
        int r3 = 0;
    L13:
        if (r3 >= r0) goto L22;
        char r5 = r7.charAt(r3);
        if (isDigit(r5) == true) goto L21;
        if (r5 != '.') goto L20;
        if (r4 == false) goto L20;
        r4 = false;
    L20:
        return false;
    L21:
        r3 = r3 + 1;
        goto L13
    L22:
        if (r0 <= 0) goto L24;
        return true;
    L24:
        return false;
    L5:
        if (r7.charAt(0) != '-') goto L8;
    L6:
        r3 = 1;
        r4 = true;
        goto L13
    L8:
        if (r7.charAt(0) != '~') goto L12;
        if (r7.charAt(1) != '-') goto L6;
        r3 = 2;
        r4 = true;
        goto L13
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r17v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v7 */
    public static boolean isNumber(char[] r18, int r19, int r20) {
        int r3 = r19 + r20;
        char r4 = SignatureVisitor.SUPER;
        int r5 = 1;
        if (r20 <= 1) goto L15;
        char r6 = r18[r19];
        if (r6 != '-') goto L7;
        int r62 = r19 + 1;
        if (r18[r62] == '-') goto L13;
    L12:
        r62 = r19;
    L13:
        int r63 = r62 + 1;
    L14:
        boolean r7 = true;
    L16:
        if (r63 >= r3) goto L93;
        char r9 = r18[r63];
        if (isDigit(r9) == false) goto L20;
        char r8 = r4;
        ?? r17 = r5;
    L92:
        r63 = r63 + 1;
        r4 = r8;
        r5 = r17;
        goto L16
    L20:
        if (r7 == false) goto L24;
        if (r9 != '.') goto L24;
        r8 = r4;
        r17 = r5;
        r7 = false;
    L24:
        if (r20 == r5) goto L42;
        ?? r172 = r5;
        r17 = r172;
        if (r63 == (r3 - 1)) goto L27;
    L44:
        if (r63 == (r19 + 1)) goto L46;
    L73:
        if (r63 == r19) goto L87;
        int r42 = r63 + 1;
        if (r42 >= r3) goto L87;
        if (r9 != 'E') goto L79;
    L80:
        char r52 = r18[r42];
        r8 = SignatureVisitor.SUPER;
        if (r52 != '-') goto L83;
    L86:
        r63 = r63 + 2;
        goto L92
    L83:
        if (r52 == '+') goto L86;
        r63 = r42;
        goto L92
    L79:
        if (r9 == 'e') goto L80;
    L87:
        if (r63 != r19) goto L89;
        return false;
    L89:
        Sign$$ExternalSyntheticBUOutline0.m("invalid number literal: ".concat(new String(r18, r19, r20)), r18, r19);
        return false;
    L46:
        if (r9 != 'x') goto L73;
        if (r18[r19] != '0') goto L73;
        int r64 = r63 + 1;
    L50:
        if (r64 >= r3) goto L70;
        char r1 = r18[r64];
        if (isDigit(r1) == true) goto L68;
        if (r1 < 'A') goto L57;
        if (r1 <= 'F') goto L68;
    L57:
        if (r1 < 'a') goto L60;
        if (r1 <= 'f') goto L68;
    L60:
        if (r64 != (r20 - 1)) goto L67;
        if (r1 == 'B') goto L66;
        if (r1 == 'I') goto L66;
        if (r1 == 'L') goto L66;
        if (r1 != 'l') goto L67;
    L66:
        return r17;
    L67:
        return false;
    L68:
        r64 = r64 + 1;
        goto L50
    L70:
        if ((r20 - 2) <= 0) goto L72;
        return r17;
    L72:
        return false;
    L27:
        if (r9 == '.') goto L40;
        if (r9 != 'B') goto L30;
    L39:
        return r172;
    L30:
        if (r9 == 'D') goto L39;
        if (r9 == 'F') goto L39;
        if (r9 == 'I') goto L39;
        if (r9 == 'L') goto L39;
        if (r9 == 'd') goto L39;
        if (r9 == 'f') goto L39;
        if (r9 == 'l') goto L39;
        return false;
    L40:
        Sign$$ExternalSyntheticBUOutline0.m("invalid number literal: ".concat(new String(r18)), r18, r19);
        return false;
    L42:
        r17 = r5;
        goto L44
    L93:
        ?? r173 = r5;
        if (r3 <= r19) goto L96;
        return r173;
    L96:
        return false;
    L7:
        if (r6 == '~') goto L12;
    L15:
        r63 = r19;
        goto L14
    }

    public static boolean isNumeric(Object r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if ((r2 instanceof Class) == false) goto L8;
        Class<?> r22 = (Class) r2;
    L10:
        if (r22 != Integer.TYPE) goto L12;
        return true;
    L12:
        if (r22 != Long.TYPE) goto L14;
        return true;
    L14:
        if (r22 != Short.TYPE) goto L16;
        return true;
    L16:
        if (r22 != Double.TYPE) goto L18;
        return true;
    L18:
        if (r22 != Float.TYPE) goto L20;
        return true;
    L20:
        if (Number.class.isAssignableFrom(r22) == true) goto L29;
        return false;
    L29:
        return true;
    L8:
        r22 = r2.getClass();
        goto L10
    }

    public static boolean isNumericallyCoercible(Class r1, Class r2) {
        if (r1.isPrimitive() == false) goto L5;
        Class r0 = boxPrimitive(r1);
    L6:
        if (r0 != null) goto L8;
        return false;
    L8:
        if (Number.class.isAssignableFrom(r1) == true) goto L10;
        return false;
    L10:
        if (r2.isPrimitive() == false) goto L12;
        r2 = boxPrimitive(r2);
    L12:
        if (r2 != null) goto L14;
        return false;
    L14:
        return Number.class.isAssignableFrom(r2);
    L5:
        r0 = r1;
        goto L6
    }

    private static boolean isPrimitiveSubtype(Class r3, Class<?> r4) {
        if (r4.isPrimitive() == true) goto L5;
        return false;
    L5:
        Class<?> r32 = unboxPrimitive(r3);
        if (r32.isPrimitive() == true) goto L8;
        return false;
    L8:
        Class<?> r0 = Double.TYPE;
        Class<?> r2 = Float.TYPE;
        if (r4 != r0) goto L11;
        if (r32 != r2) goto L11;
        return true;
    L11:
        Class<?> r02 = Long.TYPE;
        if (r4 != r2) goto L14;
        if (r32 != r02) goto L14;
        return true;
    L14:
        Class<?> r22 = Integer.TYPE;
        if (r4 != r02) goto L17;
        if (r32 != r22) goto L17;
        return true;
    L17:
        if (r4 == r22) goto L19;
    L20:
        Class<?> r03 = Short.TYPE;
        if (r4 != r22) goto L23;
        if (r32 != r03) goto L23;
        return true;
    L23:
        if (r4 == r03) goto L25;
    L28:
        return false;
    L25:
        if (r32 != Byte.TYPE) goto L28;
        return true;
    L19:
        if (r32 != Character.TYPE) goto L20;
        return true;
    }

    public static boolean isPrimitiveWrapper(Class r1) {
        if (r1 != Integer.class) goto L5;
        return true;
    L5:
        if (r1 != Boolean.class) goto L7;
        return true;
    L7:
        if (r1 != Long.class) goto L9;
        return true;
    L9:
        if (r1 != Double.class) goto L11;
        return true;
    L11:
        if (r1 != Float.class) goto L13;
        return true;
    L13:
        if (r1 != Character.class) goto L15;
        return true;
    L15:
        if (r1 != Short.class) goto L17;
        return true;
    L17:
        if (r1 == Byte.class) goto L29;
        return false;
    L29:
        return true;
    }

    public static boolean isPropertyOnly(char[] r1, int r2, int r3) {
    L2:
        if (r2 >= r3) goto L8;
        if (isIdentifierPart(r1[r2]) == false) goto L5;
        r2 = r2 + 1;
        goto L2
    L5:
        return false;
    L8:
        return true;
    }

    public static boolean isReservedWord(String r1) {
        if (AbstractParser.LITERALS.containsKey(r1) == false) goto L5;
        return true;
    L5:
        if (AbstractParser.OPERATORS.containsKey(r1) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isStatementNotManuallyTerminated(char[] r2, int r3) {
        if (r3 < r2.length) goto L6;
        return false;
    L6:
        if (r3 == r2.length) goto L11;
        if (isWhitespace(r2[r3]) == false) goto L11;
        r3 = r3 + 1;
    L11:
        if (r3 != r2.length) goto L13;
        return true;
    L13:
        if (r2[r3] != ';') goto L20;
        return false;
    L20:
        return true;
    }

    public static boolean isWhitespace(char r1) {
        if (r1 >= '!') goto L6;
        return true;
    L6:
        return false;
    }

    public static char[] loadFromFile(File r1) {
        return loadFromFile(r1, null);
    }

    public static char[] loadFromFile(File r7, String r8) {
        FileInputStream r1 = null;
        if (r7.exists() == true) goto L38;
        Instance$$ExternalSyntheticBUOutline0.m("cannot find file: ", r7.getName());
        return null;
    L38:
        FileInputStream r0 = new FileInputStream(r7);     // Catch: Throwable -> L22 FileNotFoundException -> L24
    L42:
        FileChannel r2 = r0.getChannel();     // Catch: Throwable -> L19 FileNotFoundException -> L21
    L40:
        ByteBuffer r3 = ByteBuffer.allocateDirect(10);     // Catch: Throwable -> L12 FileNotFoundException -> L37
        StringAppender r4 = new StringAppender((int) r7.length(), r8);     // Catch: Throwable -> L12 FileNotFoundException -> L37
        int r72 = 0;
    L8:
        if (r72 < 0) goto L14;
        ByteBuffer r73 = (ByteBuffer) r3.rewind();     // Catch: Throwable -> L12 FileNotFoundException -> L37
        r72 = r2.read(r3);     // Catch: Throwable -> L12 FileNotFoundException -> L37
        ByteBuffer r82 = (ByteBuffer) r3.rewind();     // Catch: Throwable -> L12 FileNotFoundException -> L37
    L10:
        if (r72 <= 0) goto L8;
        r4.append(r3.get());     // Catch: Throwable -> L12 FileNotFoundException -> L37
        r72 = r72 - 1;
        goto L10
    L14:
        char[] r74 = r4.toChars();     // Catch: Throwable -> L12 FileNotFoundException -> L37
        r0.close();
        if (r2 == null) goto L18;
        r2.close();
    L18:
        return r74;
    L12:
        th = th;
    L13:
        r1 = r0;
    L25:
        if (r1 == null) goto L27;
        r1.close();
    L27:
        if (r2 == null) goto L29;
        r2.close();
    L29:
        throw th;
    L30:
        if (r0 == null) goto L32;
        r0.close();
    L32:
        if (r2 == null) goto L34;
        r2.close();
    L34:
        return null;
    L21:
        r2 = null;
    L19:
        th = th;
        r2 = null;
    L24:
        r0 = null;
        r2 = null;
    L22:
        th = th;
        r2 = null;
        goto L25
    }

    public static Object narrowType(BigDecimal r4, int r5) {
        if (r5 == 109) goto L17;
        if (r4.scale() > 0) goto L17;
        if (r5 == 107) goto L15;
        if (r4.longValue() > 2147483647L) goto L15;
        return Integer.valueOf(r4.intValue());
    L15:
        return Long.valueOf(r4.longValue());
    L17:
        return Double.valueOf(r4.doubleValue());
    }

    public static int nextNonBlank(char[] r2, int r3) {
        if ((r3 + 1) < r2.length) goto L5;
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r2, r3);
        return 0;
    L5:
        if (r3 == r2.length) goto L9;
        if (isWhitespace(r2[r3]) == false) goto L9;
        r3 = r3 + 1;
    L9:
        return r3;
    }

    public static int numericTest(char[] r8, int r9, int r10) {
        if (r10 <= 1) goto L12;
        char r2 = r8[r9];
        if (r2 != '-') goto L8;
        int r22 = r9 + 1;
    L13:
        int r92 = r9 + r10;
        boolean r3 = false;
    L15:
        if (r22 >= r92) goto L32;
        char r6 = r8[r22];
        if (isDigit(r6) == true) goto L31;
        if (r6 != '.') goto L21;
    L29:
        r3 = true;
        goto L31
    L21:
        if (r6 == 'E') goto L24;
        if (r6 == 'e') goto L24;
        return -1;
    L24:
        int r32 = r22 + 1;
        if (r22 < r92) goto L27;
    L30:
        r22 = r32;
        goto L29
    L27:
        if (r8[r32] != '-') goto L30;
        r22 = r22 + 2;
    L31:
        r22 = r22 + 1;
        goto L15
    L32:
        if (r10 == 0) goto L41;
        if (r3 == false) goto L37;
        return 103;
    L37:
        if (r10 <= 9) goto L40;
        return 102;
    L40:
        return 101;
    L41:
        return -1;
    L8:
        if (r2 != '~') goto L12;
        r22 = r9 + 1;
        if (r8[r22] != '-') goto L13;
        r22 = r9 + 2;
    L12:
        r22 = r9;
        goto L13
    }

    public static int opLookup(char r1) {
        if (r1 != '%') goto L5;
        return 4;
    L5:
        if (r1 != '&') goto L7;
        return 6;
    L7:
        if (r1 != '*') goto L9;
        return 2;
    L9:
        if (r1 != '+') goto L11;
        return 0;
    L11:
        if (r1 != '/') goto L13;
        return 3;
    L13:
        if (r1 != '^') goto L15;
        return 8;
    L15:
        if (r1 != '|') goto L17;
        return 7;
    L17:
        if (r1 != 187) goto L19;
        return 9;
    L19:
        if (r1 != 171) goto L21;
        return 10;
    L21:
        if (r1 == 172) goto L24;
        return -1;
    L24:
        return 11;
    }

    public static Serializable optimizeTree(CompiledExpression r1) {
        if (r1.isImportInjectionRequired() == false) goto L5;
        return r1;
    L5:
        if (r1.getParserConfiguration().isAllowBootstrapBypass() == true) goto L7;
        return r1;
    L7:
        if (r1.isSingleNode() == true) goto L9;
        return r1;
    L9:
        return _optimizeTree(r1);
    }

    public static List<char[]> parseMethodOrConstructor(char[] r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L9;
        if (r4[r0] == '(') goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        int r02 = r0 + 1;
    L10:
        if (r02 == (-1)) goto L14;
        int r1 = r02 - 1;
        return parseParameterList(r4, r02, (balancedCapture(r4, r1, '(') - r1) - 1);
    L14:
        return Collections.EMPTY_LIST;
    L9:
        r02 = -1;
        goto L10
    }

    public static String[] parseParameterDefList(char[] r5, int r6, int r7) {
        LinkedList r0 = new LinkedList();
        if (r7 != (-1)) goto L5;
        r7 = r5.length;
    L5:
        int r1 = r6 + r7;
        int r2 = r6;
    L6:
        if (r6 >= r1) goto L39;
        char r3 = r5[r6];
        if (r3 != '\"') goto L10;
        r6 = captureStringLiteral(Typography.quote, r5, r6, r5.length);
    L38:
        r6 = r6 + 1;
        goto L6
    L10:
        if (r3 != ',') goto L12;
        if (r6 <= r2) goto L34;
    L30:
        if (isWhitespace(r5[r2]) == false) goto L32;
        r2 = r2 + 1;
        goto L30
    L32:
        String r32 = new String(r5, r2, r6 - r2);
        checkNameSafety(r32);
        r0.add(r32);
    L34:
        if (isWhitespace(r5[r6]) == false) goto L36;
        r6 = r6 + 1;
        goto L34
    L36:
        r2 = r6 + 1;
        goto L38
    L12:
        if (r3 != '[') goto L14;
    L27:
        r6 = balancedCapture(r5, r6, r3);
        goto L38
    L14:
        if (r3 == '{') goto L27;
        if (r3 != '\'') goto L18;
        r6 = captureStringLiteral('\'', r5, r6, r5.length);
        goto L38
    L18:
        if (r3 == '(') goto L27;
        if (isWhitespace(r3) == true) goto L38;
        if (isIdentifierPart(r5[r6]) == true) goto L38;
        Sign$$ExternalSyntheticBUOutline0.m("expected parameter", r5, r2);
        return null;
    L39:
        if (r2 >= r1) goto L45;
        if (r6 <= r2) goto L45;
        String r52 = createStringTrimmed(r5, r2, r6 - r2);
        if (r52.length() <= 0) goto L50;
        checkNameSafety(r52);
        r0.add(r52);
    L50:
        return (String[]) r0.toArray(new String[r0.size()]);
    L45:
        if (r0.size() != 0) goto L50;
        String r53 = createStringTrimmed(r5, r2, r7);
        if (r53.length() <= 0) goto L50;
        checkNameSafety(r53);
        r0.add(r53);
        goto L50
    }

    public static List<char[]> parseParameterList(char[] r5, int r6, int r7) {
        ArrayList r0 = new ArrayList();
        if (r7 != (-1)) goto L5;
        r7 = r5.length;
    L5:
        int r1 = r6 + r7;
        int r2 = r6;
    L6:
        if (r6 >= r1) goto L33;
        char r3 = r5[r6];
        if (r3 != '\"') goto L10;
        r6 = captureStringLiteral(Typography.quote, r5, r6, r5.length);
    L32:
        r6 = r6 + 1;
        goto L6
    L10:
        if (r3 != ',') goto L12;
        if (r6 <= r2) goto L28;
    L24:
        if (isWhitespace(r5[r2]) == false) goto L26;
        r2 = r2 + 1;
        goto L24
    L26:
        r0.add(subsetTrimmed(r5, r2, r6 - r2));
    L28:
        if (isWhitespace(r5[r6]) == false) goto L30;
        r6 = r6 + 1;
        goto L28
    L30:
        r2 = r6 + 1;
        goto L32
    L12:
        if (r3 != '[') goto L14;
    L21:
        r6 = balancedCapture(r5, r6, r3);
        goto L32
    L14:
        if (r3 == '{') goto L21;
        if (r3 != '\'') goto L18;
        r6 = captureStringLiteral('\'', r5, r6, r5.length);
        goto L32
    L18:
        if (r3 == '(') goto L21;
    L33:
        if (r2 >= r1) goto L40;
        if (r6 <= r2) goto L40;
        char[] r52 = subsetTrimmed(r5, r2, r6 - r2);
        if (r52.length <= 0) goto L44;
        r0.add(r52);
        return r0;
    L44:
        return r0;
    L40:
        if (r0.size() != 0) goto L44;
        char[] r53 = subsetTrimmed(r5, r2, r7);
        if (r53.length <= 0) goto L44;
        r0.add(r53);
        goto L44
    }

    public static void parseWithExpressions(String r17, char[] r18, int r19, int r20, Object r21, VariableResolverFactory r22) {
        int r5 = r19 + r20;
        int r8 = r19;
        int r9 = r8;
        String r10 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r11 = -1;
    L4:
        if (r8 >= r5) goto L83;
        char r15 = r18[r8];
        if (r15 != '\"') goto L8;
    L81:
        r8 = balancedCapture(r18, r8, r5, r15);
    L82:
        r8 = r8 + 1;
        goto L4
    L8:
        if (r15 != '%') goto L10;
    L76:
        int r6 = r8 + 1;
        if (r6 >= r5) goto L82;
        if (r18[r6] != '=') goto L82;
        r11 = opLookup(r15);
        goto L82
    L10:
        if (r15 == '/') goto L50;
        if (r15 != '=') goto L13;
        int r102 = r8 - r9;
        if (r11 == (-1)) goto L47;
        int r12 = 1;
    L48:
        r10 = new String(r18, r9, r102 - r12).trim();
        r9 = r8 + 1;
        goto L82
    L47:
        r12 = 0;
        goto L48
    L13:
        if (r15 == '[') goto L81;
        if (r15 == '{') goto L81;
        if (r15 == '\'') goto L81;
        if (r15 == '(') goto L81;
        switch(r15) {
            case 42: goto L76;
            case 43: goto L76;
            case 44: goto L22;
            case 45: goto L76;
            default: goto L82;
        };
    L22:
        if (r10 != null) goto L32;
        if (r17 == null) goto L107;
        StringBuilder r62 = new StringBuilder(r17);     // Catch: CompileException -> L25
        r62.append('.');     // Catch: CompileException -> L25
        r62.append(r18, r9, r8 - r9);     // Catch: CompileException -> L25
        MVEL.eval(r62.toString(), r21, r22);     // Catch: CompileException -> L25
    L28:
        r8 = r8 + 1;
    L41:
        r9 = r8;
        r11 = -1;
        goto L82
    L107:
        MVEL.eval(new String(r18, r9, r8 - r9), r21, r22);     // Catch: CompileException -> L25
    L25:
        e = move-exception;
        e.setCursor(r9 + (e.getCursor() - (e.getExpr().length - r20)));
        e.setExpr(r18);
        throw e;
    L32:
        if (r11 == (-1)) goto L39;
        if (r17 == null) goto L38;
        MVEL.setProperty(r21, r10, MVEL.eval(new String(createShortFormOperativeAssignment(r17 + "." + r10, r18, r9, r8 - r9, r11)), r21, r22));     // Catch: CompileException -> L35
    L40:
        r8 = r8 + 1;
        r10 = null;
    L35:
        e = move-exception;
        e.setCursor(r9 + (e.getCursor() - (e.getExpr().length - r20)));
        e.setExpr(r18);
        throw e;
    L38:
        throw new CompileException("operative assignment not possible here", r18, r19);     // Catch: CompileException -> L35
    L39:
        MVEL.setProperty(r21, r10, MVEL.eval(r18, r9, r8 - r9, r21, r22));     // Catch: CompileException -> L35
        goto L40
    L50:
        if (r8 < r5) goto L52;
    L59:
        if (r8 < r5) goto L61;
    L72:
        if (r8 >= r5) goto L82;
        if (r18[r8 + 1] != '=') goto L82;
        r11 = 3;
        goto L82
    L61:
        if (r18[r8 + 1] != '*') goto L72;
        int r13 = r5 - 1;
    L63:
        if (r8 >= r13) goto L69;
        if (r18[r8] != '*') goto L68;
        if (r18[r8 + 1] == '/') goto L69;
    L68:
        r18[r8] = ' ';
        r8 = r8 + 1;
    L69:
        int r63 = r8 + 1;
        r18[r8] = ' ';
        r8 = r8 + 2;
        r18[r63] = ' ';
        if (r10 != null) goto L82;
    L58:
        r9 = r8;
        goto L82
    L52:
        if (r18[r8 + 1] != '/') goto L59;
    L53:
        if (r8 >= r5) goto L57;
        if (r18[r8] == '\n') goto L57;
        r18[r8] = ' ';
        r8 = r8 + 1;
    L57:
        if (r10 != null) goto L82;
    L83:
        if (r9 == r5) goto L106;
        if (r10 != null) goto L109;
    L99:
        if (r17 != null) goto L102;
        MVEL.eval(new String(r18, r9, r5 - r9), r21, r22);     // Catch: CompileException -> L93
        return;
    L102:
        MVEL.eval(new StringAppender(r17).append('.').append(r18, r9, r5 - r9).toString(), r21, r22);     // Catch: CompileException -> L93
        return;
    L109:
    L93:
        e = move-exception;
        e.setCursor(r9 + (e.getCursor() - (e.getExpr().length - r20)));
        e.setExpr(r18);
        throw e;
    L86:
        if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r10) == true) goto L99;
        if (r11 == (-1)) goto L97;
        if (r17 == null) goto L96;
        MVEL.setProperty(r21, r10, MVEL.eval(new String(createShortFormOperativeAssignment(r17 + "." + r10, r18, r9, r5 - r9, r11)), r21, r22));     // Catch: CompileException -> L93
        return;
    L96:
        throw new CompileException("operative assignment not possible here", r18, r19);     // Catch: CompileException -> L93
    L97:
        MVEL.setProperty(r21, r10, MVEL.eval(r18, r9, r5 - r9, r21, r22));     // Catch: CompileException -> L93
        return;
    }

    public static char[] readIn(InputStream r4, String r5) {
        byte[] r1 = new byte[10];     // Catch: Throwable -> L9
        StringAppender r2 = new StringAppender(10, r5);     // Catch: Throwable -> L9
    L4:
        int r52 = r4.read(r1);     // Catch: Throwable -> L9
        if (r52 <= 0) goto L11;
        int r0 = 0;
    L7:
        if (r0 >= r52) goto L4;
        r2.append(r1[r0]);     // Catch: Throwable -> L9
        r0 = r0 + 1;     // Catch: Throwable -> L9
        goto L7
    L11:
        char[] r53 = r2.toChars();     // Catch: Throwable -> L9
        r4.close();
        return r53;
    L9:
        th = move-exception;
        if (r4 == null) goto L16;
        r4.close();
    L16:
        throw th;
    }

    public static String repeatChar(char r2, int r3) {
        char[] r0 = new char[r3];
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L6;
        r0[r1] = r2;
        r1 = r1 + 1;
        goto L3
    L6:
        return new String(r0);
    }

    public static int resolveType(Object r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return __resolveType(r0.getClass());
    }

    public static int scoreInterface(Class<?> r5, Class<?> r6) {
        if (r5.isInterface() == false) goto L17;
        Class<?>[] r0 = r6.getInterfaces();
        if (r0 == null) goto L17;
        int r2 = r0.length;
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L17;
        Class<?> r4 = r0[r3];
        if (r4 == r5) goto L10;
        if (r5.isAssignableFrom(r4) == true) goto L15;
        r3 = r3 + 1;
        goto L7
    L15:
        return scoreInterface(r5, r6.getSuperclass());
    L10:
        return 1;
    L17:
        return 0;
    }

    public static float similarity(String r7, String r8) {
        float r1 = 0.0f;
        if (r7 == null) goto L20;
        if (r8 == null) goto L20;
        char[] r72 = r7.toCharArray();
        char[] r82 = r8.toCharArray();
        int r4 = 0;
        if (r72.length <= r82.length) goto L9;
        float r2 = r72.length;
    L11:
        if (r4 >= r72.length) goto L19;
        if (r4 >= r82.length) goto L19;
        if (r72[r4] != r82[r4]) goto L17;
        r1 = r1 + 1.0f;
    L17:
        r4 = r4 + 1;
    L19:
        return r1 / r2;
    L9:
        r2 = r82.length;
        r82 = r72;
        r72 = r82;
    L20:
        if (r7 != null) goto L23;
        if (r8 != null) goto L23;
        return 1.0f;
    L23:
        return 0.0f;
    }

    private static int skipStringEscape(int r0) {
        return r0 + 2;
    }

    public static int skipWhitespace(char[] r6, int r7) {
    L3:
        if (r7 == r6.length) goto L39;
        char r0 = r6[r7];
        if (r0 == '\n') goto L38;
        if (r0 == '\r') goto L38;
        if (r0 != '/') goto L36;
        int r3 = r7 + 1;
        if (r3 == r6.length) goto L36;
        char r02 = r6[r3];
        if (r02 != '*') goto L15;
        int r03 = r6.length - 1;
        r6[r7] = ' ';
        r7 = r3;
    L27:
        if (r7 == r03) goto L33;
        if (r6[r7] != '*') goto L32;
        if (r6[r7 + 1] == '/') goto L33;
    L32:
        r6[r7] = ' ';
        r7 = r7 + 1;
    L33:
        if (r7 == r03) goto L3;
        r6[r7 + 1] = ' ';
        r6[r7] = ' ';
        r7 = r7 + 2;
        goto L3
    L15:
        if (r02 != '/') goto L39;
        r6[r7] = ' ';
        r7 = r3;
    L19:
        if (r7 == r6.length) goto L24;
        if (r6[r7] == '\n') goto L24;
        r6[r7] = ' ';
        r7 = r7 + 1;
    L24:
        if (r7 == r6.length) goto L3;
        r6[r7] = ' ';
        r7 = r7 + 1;
    L36:
        if (isWhitespace(r0) == false) goto L39;
    L38:
        r7 = r7 + 1;
    L39:
        return r7;
    }

    public static char[] subArray(char[] r3, int r4, int r5) {
        int r0 = 0;
        if (r4 >= r5) goto L5;
        int r52 = r5 - r4;
        char[] r1 = new char[r52];
    L7:
        if (r0 == r52) goto L9;
        r1[r0] = r3[r0 + r4];
        r0 = r0 + 1;
        goto L7
    L9:
        return r1;
    L5:
        return new char[0];
    }

    public static Serializable subCompileExpression(String r1, ParserContext r2) {
        return _optimizeTree(new ExpressionCompiler(r1, r2)._compile());
    }

    public static Serializable subCompileExpression(char[] r1) {
        return _optimizeTree(new ExpressionCompiler(r1)._compile());
    }

    public static Serializable subCompileExpression(char[] r1, int r2, int r3, ParserContext r4) {
        return _optimizeTree(new ExpressionCompiler(r1, r2, r3, r4)._compile());
    }

    public static Serializable subCompileExpression(char[] r1, ParserContext r2) {
        return _optimizeTree(new ExpressionCompiler(r1, r2)._compile());
    }

    public static char[] subset(char[] r4, int r5) {
        int r0 = r4.length - r5;
        char[] r1 = new char[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = r4[r2 + r5];
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public static char[] subset(char[] r3, int r4, int r5) {
        char[] r0 = new char[r5];
        int r1 = 0;
    L3:
        if (r1 >= r5) goto L5;
        r0[r1] = r3[r1 + r4];
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    public static char[] subsetTrimmed(char[] r2, int r3, int r4) {
        if (r4 <= 0) goto L5;
        int r42 = r4 + r3;
    L7:
        if (r42 <= 0) goto L12;
        if (isWhitespace(r2[r42 - 1]) == false) goto L12;
        r42 = r42 - 1;
    L12:
        if (isWhitespace(r2[r3]) == false) goto L15;
        if (r3 >= r42) goto L15;
        r3 = r3 + 1;
    L15:
        int r43 = r42 - r3;
        if (r43 != 0) goto L20;
        return new char[0];
    L20:
        return subset(r2, r3, r43);
    L5:
        return new char[0];
    }

    public static int trimLeft(char[] r1, int r2, int r3) {
        if (r3 <= r1.length) goto L5;
        r3 = r1.length;
    L5:
        if (r3 == 0) goto L10;
        if (r3 < r2) goto L10;
        if (isWhitespace(r1[r3 - 1]) == false) goto L10;
        r3 = r3 - 1;
    L10:
        return r3;
    }

    public static int trimRight(char[] r1, int r2) {
    L3:
        if (r2 == r1.length) goto L7;
        if (isWhitespace(r1[r2]) == false) goto L7;
        r2 = r2 + 1;
    L7:
        return r2;
    }

    public static Class unboxPrimitive(Class r2) {
        Class r1 = Integer.TYPE;
        if (r2 == Integer.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = int[].class;
        if (r2 == Integer[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Long.TYPE;
        if (r2 == Long.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = long[].class;
        if (r2 == Long[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Character.TYPE;
        if (r2 == Character.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = char[].class;
        if (r2 == Character[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Short.TYPE;
        if (r2 == Short.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = short[].class;
        if (r2 == Short[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Double.TYPE;
        if (r2 == Double.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = double[].class;
        if (r2 == Double[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Float.TYPE;
        if (r2 == Float.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = float[].class;
        if (r2 == Float[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Boolean.TYPE;
        if (r2 == Boolean.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = boolean[].class;
        if (r2 == Boolean[].class) goto L67;
        if (r2 == r1) goto L67;
        r1 = Byte.TYPE;
        if (r2 == Byte.class) goto L67;
        if (r2 == r1) goto L67;
        r1 = byte[].class;
        if (r2 == Byte[].class) goto L67;
        if (r2 == r1) goto L67;
        return r2;
    L67:
        return r1;
    }
}
