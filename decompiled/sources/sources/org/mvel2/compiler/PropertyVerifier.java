package org.mvel2.compiler;

import com.android.dx.Constants$$ExternalSyntheticBUOutline0;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.CompileException;
import org.mvel2.ErrorDetail;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.ast.Function;
import org.mvel2.ast.Negation$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.optimizers.AbstractOptimizer;
import org.mvel2.optimizers.impl.refl.nodes.WithAccessor;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.NullType;
import org.mvel2.util.ParseTools;
import org.mvel2.util.PropertyTools;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class PropertyVerifier extends AbstractOptimizer {
    private static final int COL = 2;
    private static final int DONE = -1;
    private static final int METH = 1;
    private static final int NORM = 0;
    private static final int WITH = 3;
    private boolean classLiteral;
    private Class ctx;
    private boolean deepProperty;
    private boolean first;
    private boolean fqcn;
    private List<String> inputs;
    private boolean methodCall;
    private Map<String, Type> paramTypes;
    private boolean resolvedExternally;

    public PropertyVerifier(String r2, ParserContext r3) {
        this.inputs = new LinkedList();
        this.first = false;
        this.classLiteral = false;
        this.methodCall = false;
        this.deepProperty = false;
        this.fqcn = false;
        this.ctx = null;
        char[] r22 = r2.toCharArray();
        this.expr = r22;
        int r23 = r22.length;
        this.end = r23;
        this.length = r23;
        this.pCtx = r3;
    }

    public PropertyVerifier(String r3, ParserContext r4, Class r5) {
        this.inputs = new LinkedList();
        this.first = false;
        this.classLiteral = false;
        this.methodCall = false;
        this.deepProperty = false;
        this.fqcn = false;
        this.ctx = null;
        char[] r1 = r3.toCharArray();
        this.expr = r1;
        int r12 = r1.length;
        this.length = r12;
        this.end = r12;
        if (r3.length() > 0) goto L5;
    L7:
        this.pCtx = r4;
        this.ctx = r5;
        return;
    L5:
        if (r3.charAt(0) != '.') goto L7;
        this.start = 1;
        this.st = 1;
        this.cursor = 1;
        goto L7
    }

    public PropertyVerifier(char[] r2, int r3, int r4, ParserContext r5) {
        this.inputs = new LinkedList();
        this.first = false;
        this.classLiteral = false;
        this.methodCall = false;
        this.deepProperty = false;
        this.fqcn = false;
        this.ctx = null;
        this.expr = r2;
        this.start = r3;
        this.length = r4;
        this.end = r3 + r4;
        this.pCtx = r5;
    }

    public PropertyVerifier(char[] r2, ParserContext r3) {
        this.inputs = new LinkedList();
        this.first = false;
        this.classLiteral = false;
        this.methodCall = false;
        this.deepProperty = false;
        this.fqcn = false;
        this.ctx = null;
        this.expr = r2;
        int r22 = r2.length;
        this.end = r22;
        this.length = r22;
        this.pCtx = r3;
    }

    private Class getBeanProperty(Class r7, String r8) {
        boolean r2 = false;
        int r22 = 0;
        char r23 = 0;
        if (this.first == false) goto L24;
        boolean r0 = this.pCtx.hasVarOrInput(r8);
        ParserContext r3 = this.pCtx;
        if (r0 == false) goto L12;
        if (r3.isStrictTypeEnforcement() == false) goto L10;
        recordTypeParmsForProperty(r8);
    L10:
        return this.pCtx.getVarOrInputType(r8);
    L12:
        if (r3.hasImport(r8) == false) goto L16;
        this.resolvedExternally = false;
        return this.pCtx.getImport(r8);
    L16:
        if (this.pCtx.isStrongTyping() == true) goto L19;
        return Object.class;
    L19:
        if (this.pCtx.hasVarOrInput("this") == false) goto L24;
        if (this.pCtx.isStrictTypeEnforcement() == false) goto L23;
        recordTypeParmsForProperty("this");
    L23:
        r7 = this.pCtx.getVarOrInputType("this");
        this.resolvedExternally = false;
    L24:
        this.st = this.cursor;
        if (r7 == null) goto L27;
        Member r32 = PropertyTools.getFieldOrAccessor(r7, r8);
    L29:
        if (MVEL.COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS == false) goto L34;
        if ("class".equals(r8) == false) goto L34;
        return Class.class;
    L34:
        if ((r32 instanceof Field) == true) goto L36;
        if (r32 != null) goto L63;
        ParserContext r33 = this.pCtx;
        if (r33 != null) goto L67;
    L74:
        ParserContext r34 = this.pCtx;
        if (r34 == null) goto L92;
        if (r34.getLastTypeParameters() == null) goto L92;
        if (this.pCtx.getLastTypeParameters().length == 0) goto L92;
        if (Collection.class.isAssignableFrom(r7) == false) goto L84;
    L86:
        Type r72 = this.pCtx.getLastTypeParameters()[r23];
        this.pCtx.setLastTypeParameters(null);
        if ((r72 instanceof ParameterizedType) == false) goto L90;
        return Object.class;
    L90:
        return (Class) r72;
    L84:
        if (Map.class.isAssignableFrom(r7) == false) goto L92;
        r23 = 1;
    L92:
        if (this.pCtx != null) goto L94;
    L99:
        Object r35 = tryStaticAccess();
        if (r35 == null) goto L125;
        this.fqcn = true;
        this.resolvedExternally = false;
        if ((r35 instanceof Class) == false) goto L114;
        if (MVEL.COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS == true) goto L106;
    L107:
        r2 = true;
    L108:
        this.classLiteral = r2;
        if (r2 == true) goto L111;
        return Class.class;
    L111:
        return (Class) r35;
    L106:
        if (new String(this.expr, this.end - 6, 6).equals(".class") == true) goto L108;
    L114:
        if ((r35 instanceof Field) == true) goto L150;
        return ((Method) r35).getReturnType();
    L122:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("in verifier: ", this.expr, this.start, e);
        return null;
    L150:
        return ((Field) r35).get(null).getClass();
    L117:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("in verifier: ", this.expr, this.start, e);
        return null;
    L125:
        if (r7 != null) goto L146;
    L128:
        ParserContext r24 = this.pCtx;
        if (r24 == null) goto L136;
        if (r24.getParserConfiguration() == null) goto L136;
        if (this.pCtx.getParserConfiguration().isAllowNakedMethCall() == false) goto L141;
    L137:
        Class r73 = this.getMethod(r7, r8);
        if (r73 == Object.class) goto L141;
        return r73;
    L141:
        if (this.pCtx.isStrictTypeEnforcement() == true) goto L143;
        return Object.class;
    L143:
        Sign$$ExternalSyntheticBUOutline0.m("unqualified type in strict mode for: " + r8, this.expr, this.tkStart);
        return null;
    L136:
        if (MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL == false) goto L141;
    L146:
        return ParseTools.findClass(this.variableFactory, r7.getName() + "$" + r8, this.pCtx);
    L94:
        if ("length".equals(r8) == false) goto L99;
        if (r7.isArray() == false) goto L99;
        return Integer.class;
    L67:
        if (this.first == false) goto L74;
        if (r33.hasImport(r8) == false) goto L74;
        if (this.pCtx.getImport(r8) == null) goto L74;
        return this.pCtx.getImport(r8);
    L63:
        return getReturnType(r7, (Method) r32);
    L36:
        if (this.pCtx.isStrictTypeEnforcement() == false) goto L60;
        Field r36 = (Field) r32;
        if (r36.getGenericType() == null) goto L58;
        if ((r36.getGenericType() instanceof ParameterizedType) == false) goto L50;
        ParameterizedType r74 = (ParameterizedType) r36.getGenericType();
        this.pCtx.setLastTypeParameters(r74.getActualTypeArguments());
        Type[] r82 = r74.getActualTypeArguments();
        TypeVariable<Class<?>>[] r75 = type2Class(r74.getRawType()).getTypeParameters();
        if (r82.length <= 0) goto L47;
        if (this.paramTypes != null) goto L47;
        this.paramTypes = new HashMap();
    L47:
        if (r22 >= r82.length) goto L58;
        this.paramTypes.put(r75[r22].toString(), r82[r22]);
        r22 = r22 + 1;
        goto L47
    L50:
        if ((r36.getGenericType() instanceof TypeVariable) == false) goto L58;
        Type r6 = this.paramTypes.remove(((TypeVariable) r36.getGenericType()).getName());
        if (r6 == null) goto L58;
        if ((r6 instanceof Class) == false) goto L58;
        return (Class) r6;
    L58:
        return r36.getType();
    L60:
        return ((Field) r32).getType();
    L27:
        r32 = null;
        goto L29
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r5v25, types: [java.lang.reflect.Type[]] */
    private Class getCollectionProperty(Class r5, String r6) {
        Class<Object> r2 = Object.class;
        if (this.first == false) goto L11;
        boolean r52 = this.pCtx.hasVarOrInput(r6);
        ParserContext r0 = this.pCtx;
        if (r52 == false) goto L8;
        r5 = ParseTools.getSubComponentType(r0.getVarOrInputType(r6));
        goto L11
    L8:
        if (r0.hasImport(r6) == false) goto L10;
        this.resolvedExternally = false;
        r5 = ParseTools.getSubComponentType(this.pCtx.getImport(r6));
        goto L11
    L10:
        r5 = r2;
    L11:
        Class r22 = r2;
        if (this.pCtx.isStrictTypeEnforcement() == true) goto L14;
    L40:
        this.cursor++;
        skipWhitespace();
        int r53 = this.cursor;
        if (scanTo(']') == false) goto L43;
        addFatalError("unterminated [ in token");
    L43:
        MVEL.analysisCompile(new String(this.expr, r53, this.cursor - r53), this.pCtx);
        this.cursor++;
        return r22;
    L14:
        if (r6.length() == 0) goto L17;
        r5 = getBeanProperty(r5, r6);
    L17:
        if (Map.class.isAssignableFrom(r5) == false) goto L25;
        Class<Object> r23 = r2;
        if (this.pCtx.getLastTypeParameters() == null) goto L23;
        r23 = r2;
        if (this.pCtx.getLastTypeParameters().length == 0) goto L23;
        r23 = this.pCtx.getLastTypeParameters()[1];
    L23:
        r22 = type2Class(r23);
        goto L40
    L25:
        if (Collection.class.isAssignableFrom(r5) == false) goto L33;
        r22 = r2;
        if (this.pCtx.getLastTypeParameters() == null) goto L40;
        r22 = r2;
        if (this.pCtx.getLastTypeParameters().length == 0) goto L40;
        r22 = type2Class(this.pCtx.getLastTypeParameters()[0]);
        goto L40
    L33:
        if (r5.isArray() == false) goto L36;
        r22 = r5.getComponentType();
        goto L40
    L36:
        if (this.pCtx.isStrongTyping() == true) goto L38;
        r22 = r5;
        goto L40
    L38:
        Sign$$ExternalSyntheticBUOutline0.m("unknown collection type: " + r5 + "; property=" + r6, this.expr, this.start);
        return null;
    }

    private Class getMethod(Class r21, String r22) {
        String r0 = r22;
        int r2 = this.cursor;
        int r7 = 0;
        if (this.first == false) goto L24;
        this.first = false;
        this.methodCall = true;
        boolean r3 = this.pCtx.hasImport(r0);
        ParserContext r8 = this.pCtx;
        if (r3 == false) goto L8;
        Method r02 = r8.getStaticImport(r0).getMethod();
        Class r32 = r02.getDeclaringClass();
        r0 = r02.getName();
    L7:
        String r82 = r0;
        int r03 = this.cursor;
        int r9 = this.end;
        if (r03 >= r9) goto L32;
        char[] r11 = this.expr;
        if (r11[r03] != '(') goto L32;
        int r04 = ParseTools.balancedCapture(r11, r03, r9, '(');
        this.cursor = r04;
        if ((r04 - r2) <= 1) goto L32;
        String r5 = new String(this.expr, r2 + 1, (r04 - r2) - 1);
    L33:
        this.cursor++;
        List<char[]> r23 = ParseTools.parseParameterList(r5.toCharArray(), 0, -1);
        if (r23.size() != 0) goto L37;
        Class[] r05 = new Class[0];
        r23 = Collections.EMPTY_LIST;
    L65:
        Method r4 = ParseTools.getBestCandidate(r05, r82, r32, r32.getMethods(), this.pCtx.isStrongTyping());
        Class<?> r52 = Object.class;
        if (r4 != null) goto L96;
        r4 = ParseTools.getBestCandidate(r05, r82, r32, r32.getDeclaredMethods(), this.pCtx.isStrongTyping());
        if (r4 != null) goto L96;
        StringAppender r24 = new StringAppender();
    L71:
        if (r7 >= r05.length) goto L81;
        Class r42 = r05[r7];
        if (r42 == null) goto L75;
        String r43 = r42.getName();
    L76:
        r24.append(r43);
        if (r7 >= (r05.length - 1)) goto L79;
        r24.append(", ");
    L79:
        r7 = r7 + 1;
        goto L71
    L75:
        r43 = null;
        goto L76
    L81:
        if ("size".equals(r82) == true) goto L85;
        if ("length".equals(r82) == true) goto L85;
    L91:
        if (this.pCtx.isStrictTypeEnforcement() == true) goto L93;
        return r52;
    L93:
        Sign$$ExternalSyntheticBUOutline0.m("unable to resolve method using strict-mode: " + r32.getName() + "." + r82 + "(" + r24.toString() + ")", this.expr, this.tkStart);
        return null;
    L85:
        if (r05.length != 0) goto L91;
        if (r32.isArray() == false) goto L91;
        return Integer.class;
    L96:
        if (this.pCtx.isStrictTypeEnforcement() == true) goto L98;
    L141:
        int r17 = 0;
    L143:
        if (Modifier.isPublic(r4.getModifiers()) == true) goto L162;
        if (this.pCtx.isStrictTypeEnforcement() == false) goto L162;
        StringAppender r25 = new StringAppender();
        int r72 = r17;
    L148:
        if (r72 >= r05.length) goto L157;
        Class r53 = r05[r72];
        if (r53 == null) goto L152;
        String r54 = r53.getName();
    L153:
        r25.append(r54);
        if (r72 >= (r05.length - 1)) goto L156;
        r25.append(", ");
    L156:
        r72 = r72 + 1;
        goto L148
    L152:
        r54 = null;
        goto L153
    L157:
        String r06 = Modifier.toString(r4.getModifiers());
        if (r06.trim().equals(_UrlKt.FRAGMENT_ENCODE_SET) == false) goto L160;
        r06 = "<package local>";
    L160:
        addFatalError("the referenced method is not accessible: " + r32.getName() + "." + r82 + "(" + r25.toString() + ") (scope: " + r06 + "; required: public", this.tkStart);
    L162:
        return getReturnType(r32, r4);
    L98:
        if (r4.getGenericReturnType() == null) goto L141;
        HashMap r13 = new HashMap();
        Type[] r14 = r4.getGenericParameterTypes();
        int r15 = 0;
    L101:
        if (r15 >= r14.length) goto L112;
        Type r6 = r14[r15];
        int r172 = r7;
        if ((r6 instanceof ParameterizedType) == false) goto L110;
        ParameterizedType r62 = (ParameterizedType) r6;
        Class<?> r222 = r52;
        List<char[]> r19 = r23;
        Class r26 = this.pCtx.getImport(new String(r23.get(r15)));
        if (r26 == null) goto L111;
        if (r62.getRawType().equals(Class.class) == false) goto L109;
        r13.put(r62.getActualTypeArguments()[r172].toString(), r26);
        goto L111
    L109:
        r13.put(r14[r15].toString(), r26);
    L111:
        r15 = r15 + 1;
        r52 = r222;
        r7 = r172;
        r23 = r19;
        goto L101
    L110:
        r19 = r23;
        r222 = r52;
        goto L111
    L112:
        Class<?> r223 = r52;
        r17 = r7;
        if (this.pCtx.isStrictTypeEnforcement() == true) goto L115;
    L128:
        Type r27 = r4.getGenericReturnType();
        String r55 = r27.toString();
        if ((r27 instanceof ParameterizedType) == false) goto L131;
        this.pCtx.setLastTypeParameters(((ParameterizedType) r27).getActualTypeArguments());
    L131:
        Map<String, Type> r28 = this.paramTypes;
        if (r28 == null) goto L138;
        if (r28.containsKey(r55) == false) goto L138;
        return type2Class(this.paramTypes.get(r55));
    L138:
        if (r13.containsKey(r55) == false) goto L143;
        return (Class) r13.get(r55);
    L115:
        if (r32.getTypeParameters().length == 0) goto L128;
        if (this.pCtx.getLastTypeParameters() == null) goto L128;
        if (this.pCtx.getLastTypeParameters().length != r32.getTypeParameters().length) goto L128;
        TypeVariable[] r29 = r32.getTypeParameters();
        int r56 = r17;
    L122:
        if (r56 >= r29.length) goto L128;
        Type r63 = this.pCtx.getLastTypeParameters()[r56];
        String r73 = r29[r56].getName();
        if ((r63 instanceof Class) == false) goto L126;
        Class<?> r64 = type2Class(this.pCtx.getLastTypeParameters()[r56]);
    L127:
        r13.put(r73, r64);
        r56 = r56 + 1;
        goto L122
    L126:
        r64 = r223;
        goto L127
    L37:
        Class[] r57 = new Class[r23.size()];
        if (this.pCtx.getErrorList().isEmpty() == false) goto L41;
        List<ErrorDetail> r07 = this.pCtx.getErrorList();
    L40:
        List<ErrorDetail> r92 = r07;
        int r112 = 0;
        CompileException r08 = null;
    L44:
        if (r112 >= r23.size()) goto L64;
        r57[r112] = MVEL.analyze(r23.get(r112), this.pCtx);     // Catch: CompileException -> L49
        if ("null".equals(String.valueOf(r23.get(r112))) == false) goto L52;
        r57[r112] = NullType.class;     // Catch: CompileException -> L49
    L52:
        if (r92.size() >= this.pCtx.getErrorList().size()) goto L61;
        Iterator<ErrorDetail> r12 = this.pCtx.getErrorList().iterator();
    L55:
        if (r12.hasNext() == false) goto L61;
        ErrorDetail r132 = r12.next();
        if (r92.contains(r132) == true) goto L55;
        r132.setExpr(this.expr);
        r132.setCursor(new String(this.expr).substring(this.st).indexOf(new String(r23.get(r112))) + this.st);
        r132.setColumn(0);
        r132.setLineNumber(0);
        r132.calcRowAndColumn();
    L61:
        if (r08 != null) goto L63;
        r112 = r112 + 1;
        goto L44
    L63:
        throw r08;
    L49:
        e = move-exception;
        r08 = ErrorUtil.rewriteIfNeeded(e, this.expr, this.st);
        goto L52
    L64:
        r05 = r57;
        goto L65
    L41:
        r07 = new ArrayList(this.pCtx.getErrorList());
    L32:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L33
    L8:
        Function r33 = r8.getFunction(r0);
        if (r33 == null) goto L19;
        if (r33.getEgressType() == null) goto L19;
        this.resolvedExternally = false;
        int r09 = ParseTools.balancedCapture(this.expr, this.cursor, this.end, '(');
        this.cursor = r09;
        if ((r09 - r2) <= 1) goto L15;
        char[] r010 = ParseTools.subset(this.expr, r2 + 1, (r09 - r2) - 1);
    L16:
        r33.checkArgumentCount(ParseTools.parseParameterList(r010, 0, -1).size());
        return r33.getEgressType();
    L15:
        r010 = new char[0];
    L19:
        if (this.pCtx.hasVarOrInput("this") == false) goto L24;
        if (this.pCtx.isStrictTypeEnforcement() == false) goto L23;
        recordTypeParmsForProperty("this");
    L23:
        r32 = this.pCtx.getVarOrInputType("this");
        this.resolvedExternally = false;
    L24:
        r32 = r21;
        goto L7
    }

    private Class getReturnType(Class r5, Method r6) {
        Class<?> r0 = r6.getDeclaringClass();
        if (r5 == r0) goto L5;
        Type r1 = r6.getGenericReturnType();
        if ((r1 instanceof TypeVariable) == false) goto L35;
        String r12 = ((TypeVariable) r1).getName();
        Type r2 = r5.getGenericSuperclass();
        Class<? super Object> r52 = r5.getSuperclass();
    L9:
        if (r52 == null) goto L12;
        if (r52 == r0) goto L12;
        r2 = r52.getGenericSuperclass();
        r52 = r52.getSuperclass();
    L12:
        if (r52 != null) goto L16;
        return returnGenericType(r6);
    L16:
        if ((r2 instanceof ParameterizedType) == false) goto L35;
        TypeVariable<Class<? super Object>>[] r53 = r52.getTypeParameters();
        int r02 = 0;
    L19:
        if (r02 >= r53.length) goto L24;
        if (r53[r02].getName().equals(r12) == true) goto L25;
        r02 = r02 + 1;
    L25:
        if (r02 < 0) goto L27;
        Type r54 = ((ParameterizedType) r2).getActualTypeArguments()[r02];
        if ((r54 instanceof Class) == false) goto L33;
        return (Class) r54;
    L33:
        return returnGenericType(r6);
    L27:
        return returnGenericType(r6);
    L24:
        r02 = -1;
    L35:
        return returnGenericType(r6);
    L5:
        return returnGenericType(r6);
    }

    private Class getWithProperty(Class r12) {
        String r6 = new String(this.expr, 0, this.cursor - 1).trim();
        int r0 = this.cursor;
        int r8 = r0 + 1;
        int r02 = ParseTools.balancedCaptureWithLineAccounting(this.expr, r0, this.end, '{', this.pCtx);
        ParserContext r5 = this.pCtx;
        char[] r7 = this.expr;
        this.cursor = r02 + 1;
        new WithAccessor(r5, r6, r7, r8, r02 - r8, r12);
        return r12;
    }

    private void recordParametricReturnedType(Type r6) {
        if ((r6 instanceof ParameterizedType) == false) goto L13;
        ParameterizedType r62 = (ParameterizedType) r6;
        this.pCtx.setLastTypeParameters(r62.getActualTypeArguments());
        Type[] r0 = r62.getActualTypeArguments();
        TypeVariable<Class<?>>[] r63 = type2Class(r62.getRawType()).getTypeParameters();
        if (r0.length > 0) goto L7;
    L9:
        int r1 = 0;
    L11:
        if (r1 >= r0.length) goto L15;
        this.paramTypes.put(r63[r1].toString(), r0[r1]);
        r1 = r1 + 1;
        goto L11
    L15:
        return;
    L7:
        if (this.paramTypes != null) goto L9;
        this.paramTypes = new HashMap();
        goto L9
    }

    private void recordTypeParmsForProperty(String r2) {
        if (this.pCtx.isStrictTypeEnforcement() == false) goto L6;
        ParserContext r1 = this.pCtx;
        r1.setLastTypeParameters(r1.getTypeParametersAsArray(r2));
        return;
    }

    private Class<?> returnGenericType(Method r4) {
        Type r0 = r4.getGenericReturnType();
        recordParametricReturnedType(r0);
        String r1 = r0.toString();
        if ((r0 instanceof ParameterizedType) == false) goto L5;
        this.pCtx.setLastTypeParameters(((ParameterizedType) r0).getActualTypeArguments());
    L5:
        Map<String, Type> r02 = this.paramTypes;
        if (r02 == null) goto L12;
        if (r02.containsKey(r1) == false) goto L12;
        return type2Class(this.paramTypes.get(r1));
    L12:
        return r4.getReturnType();
    }

    private static Class<?> type2Class(Type r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if ((r2 instanceof Class) == false) goto L10;
        return (Class) r2;
    L10:
        if ((r2 instanceof ParameterizedType) == false) goto L14;
        return type2Class(((ParameterizedType) r2).getRawType());
    L14:
        if ((r2 instanceof TypeVariable) == false) goto L21;
        GenericDeclaration r22 = ((TypeVariable) r2).getGenericDeclaration();
        if ((r22 instanceof Method) == true) goto L18;
        return Object.class;
    L18:
        return ((Method) r22).getReturnType();
    L21:
        Constants$$ExternalSyntheticBUOutline0.m("Unknown type ", r2);
        return null;
    }

    public Class analyze() {
        this.cursor = this.start;
        this.resolvedExternally = true;
        if (this.ctx != null) goto L6;
        this.ctx = Object.class;
        this.first = true;
    L6:
        if (this.cursor >= this.end) goto L26;
        this.classLiteral = false;
        int r2 = nextSubToken();
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L11;
        this.ctx = getMethod(this.ctx, capture());
    L20:
        if (this.cursor >= this.length) goto L24;
        if (this.first == true) goto L24;
        this.deepProperty = true;
    L24:
        this.first = false;
        goto L6
    L11:
        if (r2 != 2) goto L13;
        this.ctx = getCollectionProperty(this.ctx, capture());
        goto L20
    L13:
        if (r2 != 3) goto L20;
        this.ctx = getWithProperty(this.ctx);
        goto L20
    L18:
        this.ctx = getBeanProperty(this.ctx, capture());
        goto L20
    L26:
        return this.ctx;
    }

    public Class getCtx() {
        return this.ctx;
    }

    public List<String> getInputs() {
        return this.inputs;
    }

    public boolean isClassLiteral() {
        return this.classLiteral;
    }

    public boolean isDeepProperty() {
        return this.deepProperty;
    }

    public boolean isFqcn() {
        return this.fqcn;
    }

    public boolean isInput() {
        if (this.resolvedExternally == true) goto L5;
        return false;
    L5:
        if (this.methodCall == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isMethodCall() {
        return this.methodCall;
    }

    public boolean isResolvedExternally() {
        return this.resolvedExternally;
    }

    public void setCtx(Class r1) {
        this.ctx = r1;
    }

    public void setInputs(List<String> r1) {
        this.inputs = r1;
    }
}
