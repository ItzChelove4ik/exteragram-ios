package org.mvel2.optimizers.impl.refl;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.MVEL;
import org.mvel2.OptimizationFailure;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessException;
import org.mvel2.ast.FunctionInstance;
import org.mvel2.ast.Negation$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.TypeDescriptor;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableLiteral;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.compiler.PropertyVerifier;
import org.mvel2.integration.GlobalListenerFactory;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.PropertyHandlerFactory;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AbstractOptimizer;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.impl.refl.collection.ArrayCreator;
import org.mvel2.optimizers.impl.refl.collection.ExprValueAccessor;
import org.mvel2.optimizers.impl.refl.collection.ListCreator;
import org.mvel2.optimizers.impl.refl.collection.MapCreator;
import org.mvel2.optimizers.impl.refl.nodes.ArrayAccessor;
import org.mvel2.optimizers.impl.refl.nodes.ArrayAccessorNest;
import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.mvel2.optimizers.impl.refl.nodes.ConstructorAccessor;
import org.mvel2.optimizers.impl.refl.nodes.DynamicFieldAccessor;
import org.mvel2.optimizers.impl.refl.nodes.DynamicFunctionAccessor;
import org.mvel2.optimizers.impl.refl.nodes.FieldAccessor;
import org.mvel2.optimizers.impl.refl.nodes.FieldAccessorNH;
import org.mvel2.optimizers.impl.refl.nodes.FunctionAccessor;
import org.mvel2.optimizers.impl.refl.nodes.GetterAccessor;
import org.mvel2.optimizers.impl.refl.nodes.GetterAccessorNH;
import org.mvel2.optimizers.impl.refl.nodes.IndexedCharSeqAccessor;
import org.mvel2.optimizers.impl.refl.nodes.IndexedCharSeqAccessorNest;
import org.mvel2.optimizers.impl.refl.nodes.IndexedVariableAccessor;
import org.mvel2.optimizers.impl.refl.nodes.ListAccessor;
import org.mvel2.optimizers.impl.refl.nodes.ListAccessorNest;
import org.mvel2.optimizers.impl.refl.nodes.MapAccessor;
import org.mvel2.optimizers.impl.refl.nodes.MapAccessorNest;
import org.mvel2.optimizers.impl.refl.nodes.MethodAccessor;
import org.mvel2.optimizers.impl.refl.nodes.MethodAccessorNH;
import org.mvel2.optimizers.impl.refl.nodes.Notify;
import org.mvel2.optimizers.impl.refl.nodes.NullSafe;
import org.mvel2.optimizers.impl.refl.nodes.PropertyHandlerAccessor;
import org.mvel2.optimizers.impl.refl.nodes.SetterAccessor;
import org.mvel2.optimizers.impl.refl.nodes.StaticReferenceAccessor;
import org.mvel2.optimizers.impl.refl.nodes.StaticVarAccessor;
import org.mvel2.optimizers.impl.refl.nodes.StaticVarAccessorNH;
import org.mvel2.optimizers.impl.refl.nodes.ThisValueAccessor;
import org.mvel2.optimizers.impl.refl.nodes.Union;
import org.mvel2.optimizers.impl.refl.nodes.VariableAccessor;
import org.mvel2.optimizers.impl.refl.nodes.WithAccessor;
import org.mvel2.util.ArrayTools;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ErrorUtil;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.MethodStub;
import org.mvel2.util.NullType;
import org.mvel2.util.ParseTools;
import org.mvel2.util.PropertyTools;
import org.mvel2.util.ReflectionUtil;
import org.mvel2.util.StringAppender;
import org.mvel2.util.Varargs;

/* loaded from: classes5.dex */
public class ReflectiveAccessorOptimizer extends AbstractOptimizer implements AccessorOptimizer {
    private static final int DONE = -1;
    private static final Object[] EMPTYARG = null;
    private static final Class[] EMPTYCLS = null;
    private static final Logger LOG = null;
    private Object ctx;
    private AccessorNode currNode;
    private boolean first;
    private Class ingressType;
    private Class returnType;
    private AccessorNode rootNode;
    private Object thisRef;
    private Object val;
    private VariableResolverFactory variableFactory;

    static {
        LOG = Logger.getLogger(ReflectiveAccessorOptimizer.class.getName());
        EMPTYARG = new Object[0];
        EMPTYCLS = new Class[0];
    }

    public ReflectiveAccessorOptimizer() {
        this.first = true;
    }

    private ReflectiveAccessorOptimizer(ParserContext r1, char[] r2, int r3, int r4, Object r5, Object r6, VariableResolverFactory r7) {
        super(r1);
        this.first = true;
        this.expr = r2;
        this.start = r3;
        if (r2 != null) goto L6;
        r4 = r3;
    L6:
        this.length = r4;
        this.end = r3 + r4;
        this.ctx = r5;
        this.variableFactory = r7;
        this.thisRef = r6;
    }

    private Accessor _getAccessor(Object r12, Class r13) {
        int r1 = 0;
        if ((r12 instanceof List) == false) goto L11;
        List r122 = (List) r12;
        Accessor[] r0 = new Accessor[r122.size()];
        Iterator r123 = r122.iterator();
    L6:
        if (r123.hasNext() == false) goto L8;
        r0[r1] = _getAccessor(r123.next(), r13);
        r1 = r1 + 1;
        goto L6
    L8:
        this.returnType = List.class;
        return new ListCreator(r0);
    L11:
        if ((r12 instanceof Map) == false) goto L19;
        Map r124 = (Map) r12;
        int r02 = r124.size();
        Accessor[] r2 = new Accessor[r02];
        Accessor[] r03 = new Accessor[r02];
        Iterator r3 = r124.keySet().iterator();
    L14:
        if (r3.hasNext() == false) goto L16;
        Object r4 = r3.next();
        r2[r1] = _getAccessor(r4, r13);
        r03[r1] = _getAccessor(r124.get(r4), r13);
        r1 = r1 + 1;
        goto L14
    L16:
        this.returnType = Map.class;
        return new MapCreator(r2, r03);
    L19:
        if ((r12 instanceof Object[]) == false) goto L42;
        Accessor[] r04 = new Accessor[((Object[]) r12).length];
        if (r13 == null) goto L26;
        int r5 = 0;
    L24:
        if (r13.getName().charAt(r5) != '[') goto L50;
        r5 = r5 + 1;
    L50:
        Class r6 = ParseTools.getBaseComponentType(r13);     // Catch: ClassNotFoundException -> L31
        if (r5 <= 1) goto L33;
        Class r22 = ParseTools.findClass(null, ParseTools.repeatChar('[', r5 - 1) + "L" + r6.getName() + ";", this.pCtx);     // Catch: ClassNotFoundException -> L31
    L34:
        Object[] r125 = (Object[]) r12;     // Catch: ClassNotFoundException -> L31
        int r52 = r125.length;     // Catch: ClassNotFoundException -> L31
        int r7 = 0;
    L35:
        if (r1 >= r52) goto L37;
        Object r8 = r125[r1];     // Catch: ClassNotFoundException -> L31
        ParserContext r9 = this.pCtx;     // Catch: ClassNotFoundException -> L31
        Accessor r82 = _getAccessor(r8, r22);     // Catch: ClassNotFoundException -> L31
        r04[r7] = r82;     // Catch: ClassNotFoundException -> L31
        CompilerTools.expectType(r9, r82, r6, true);     // Catch: ClassNotFoundException -> L31
        r1 = r1 + 1;     // Catch: ClassNotFoundException -> L31
        r7 = r7 + 1;     // Catch: ClassNotFoundException -> L31
        goto L35
    L37:
        return new ArrayCreator(r04, ParseTools.getSubComponentType(r13));
    L33:
        r22 = r13;
    L31:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("this error should never throw:".concat(ParseTools.getBaseComponentType(r13).getName()), e);
        return null;
    L26:
        r13 = Object[].class;
        r5 = 1;
        goto L50
    L42:
        if (this.returnType != null) goto L45;
        this.returnType = Object.class;
    L45:
        if (r13.isArray() == false) goto L49;
        return new ExprValueAccessor((String) r12, r13, this.ctx, this.variableFactory, this.pCtx);
    L49:
        return new ExprValueAccessor((String) r12, Object.class, this.ctx, this.variableFactory, this.pCtx);
    }

    private void addAccessorNode(AccessorNode r2) {
        if (this.rootNode != null) goto L6;
        this.currNode = r2;
        this.rootNode = r2;
        return;
    L6:
        this.currNode = this.currNode.setNextNode(r2);
    }

    private AccessorNode compileConstructor(char[] r12, Object r13, VariableResolverFactory r14) {
        String[] r0 = ParseTools.captureContructorAndResidual(r12, this.start, this.length);
        int r1 = 0;
        List<char[]> r2 = ParseTools.parseMethodOrConstructor(r0[0].toCharArray());
        if (r2 == null) goto L28;
        Class r122 = ParseTools.findClass(r14, new String(ParseTools.subset(r12, 0, ArrayTools.findFirst('(', this.start, this.length, r12))), this.pCtx);
        ExecutableStatement[] r5 = new ExecutableStatement[r2.size()];
        int r6 = 0;
    L6:
        if (r6 >= r2.size()) goto L8;
        r5[r6] = (ExecutableStatement) ParseTools.subCompileExpression(r2.get(r6), this.pCtx);
        r6 = r6 + 1;
        goto L6
    L8:
        int r62 = r2.size();
        Object[] r8 = new Object[r62];
        int r9 = 0;
    L10:
        if (r9 >= r2.size()) goto L12;
        r8[r9] = r5[r9].getValue(r13, r14);
        r9 = r9 + 1;
        goto L10
    L12:
        Constructor r92 = ParseTools.getBestConstructorCandidate(r8, r122, this.pCtx.isStrongTyping());
        if (r92 != null) goto L21;
        StringBuilder r02 = new StringBuilder();
    L15:
        if (r1 >= r62) goto L19;
        r02.append(r8[r1].getClass().getName());
        r1 = r1 + 1;
        if (r1 >= r62) goto L15;
        r02.append(", ");
        goto L15
    L19:
        Sign$$ExternalSyntheticBUOutline0.m("unable to find constructor: " + r122.getName() + "(" + r02.toString() + ")", this.expr, this.start);
        return null;
    L21:
        if (r1 >= r62) goto L23;
        r8[r1] = DataConversion.convert(r8[r1], Varargs.paramTypeVarArgsSafe(r92.getParameterTypes(), r1, r92.isVarArgs()));
        r1 = r1 + 1;
        goto L21
    L23:
        Object[] r123 = Varargs.normalizeArgsForVarArgs(r92.getParameterTypes(), r8, r92.isVarArgs());
        ConstructorAccessor r82 = new ConstructorAccessor(r92, r5);
        if (r0.length <= 1) goto L27;
        ReflectiveAccessorOptimizer r03 = new ReflectiveAccessorOptimizer(this.pCtx, r0[1].toCharArray(), 0, r0[1].length(), r92.newInstance(r123), r13, r14);
        r03.ingressType = r92.getDeclaringClass();
        r03.setRootNode(r82);
        r03.compileGetChain();
        AccessorNode r124 = r03.getRootNode();
        this.val = r03.getResultOptPass();
        return r124;
    L27:
        return r82;
    L28:
        ParserContext r04 = this.pCtx;
        if (r04 == null) goto L31;
        ClassLoader r05 = r04.getClassLoader();
    L32:
        Constructor<?> r125 = Class.forName(new String(r12), true, r05).getConstructor(EMPTYCLS);
        ConstructorAccessor r83 = new ConstructorAccessor(r125, null);
        if (r0.length <= 1) goto L36;
        ReflectiveAccessorOptimizer r06 = new ReflectiveAccessorOptimizer(this.pCtx, r0[1].toCharArray(), 0, r0[1].length(), r125.newInstance(null), r13, r14);
        r06.setRootNode(r83);
        r06.compileGetChain();
        AccessorNode r126 = r06.getRootNode();
        this.val = r06.getResultOptPass();
        return r126;
    L36:
        return r83;
    L31:
        r05 = Thread.currentThread().getContextClassLoader();
        goto L32
    }

    private Accessor compileGetChain() {
        Object r0 = this.ctx;
        this.cursor = this.start;
    L20:
        e = move-exception;
        throw new PropertyAccessException(new String(this.expr, this.start, this.length) + ": " + e.getMessage(), this.expr, this.st, e, this.pCtx);
    L18:
        e = move-exception;
        throw new PropertyAccessException(new String(this.expr, this.start, this.length).concat(": array index out of bounds."), this.expr, this.st, e, this.pCtx);
    L16:
        e = move-exception;
        throw new PropertyAccessException("null pointer: ".concat(new String(this.expr, this.start, this.length)), this.expr, this.st, e, this.pCtx);
    L22:
        e = move-exception;
        if (MVEL.INVOKED_METHOD_EXCEPTIONS_BUBBLE == false) goto L96;
        if ((e.getTargetException() instanceof RuntimeException) == true) goto L94;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L94:
        throw ((RuntimeException) e.getTargetException());
    L96:
        throw new PropertyAccessException(new String(this.expr, this.start, this.length) + ": " + e.getTargetException().getMessage(), this.expr, this.st, e, this.pCtx);
    L81:
        e = move-exception;
        throw e;
    L14:
        e = move-exception;
        LOG.log(Level.WARNING, _UrlKt.FRAGMENT_ENCODE_SET, e);
        Negation$$ExternalSyntheticBUOutline0.m(e.getMessage(), this.expr, this.st, e);
        return null;
    L4:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L46;
    L6:
        if (this.cursor >= this.end) goto L75;
        int r4 = nextSubToken();     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r4 == 0) goto L26;
        if (r4 == 1) goto L25;
        if (r4 == 2) goto L24;
        if (r4 != 3) goto L27;
        r0 = getWithProperty(r0);     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
    L27:
        if (r0 == null) goto L29;
        this.returnType = r0.getClass();     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
    L29:
        int r42 = this.cursor;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        int r10 = this.end;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r42 >= r10) goto L44;
        if (this.nullSafe == false) goto L40;
        char[] r13 = this.expr;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r13[r42] != '.') goto L36;
        int r11 = 1;
    L37:
        addAccessorNode(new NullSafe(r13, r42 + r11, (r10 - r42) - r11, this.pCtx, this.first));     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r0 != null) goto L40;
    L36:
        r11 = 0;
    L40:
        if (r0 != null) goto L44;
        throw new NullPointerException();     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
    L44:
        this.first = false;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        this.staticAccess = false;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        goto L6
    L24:
        r0 = getCollectionProperty(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        goto L27
    L25:
        r0 = getMethod(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        goto L27
    L26:
        r0 = getBeanProperty(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
    L75:
        this.val = r0;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        return this.rootNode;
    L46:
        if (this.cursor >= this.end) goto L75;
        int r43 = nextSubToken();     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r43 == 0) goto L56;
        if (r43 == 1) goto L55;
        if (r43 == 2) goto L54;
        if (r43 != 3) goto L57;
        r0 = getWithProperty(r0);     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
    L57:
        if (r0 == null) goto L59;
        this.returnType = r0.getClass();     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
    L59:
        int r44 = this.cursor;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        int r102 = this.end;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r44 >= r102) goto L74;
        if (this.nullSafe == false) goto L70;
        char[] r132 = this.expr;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r132[r44] != '.') goto L66;
        int r112 = 1;
    L67:
        addAccessorNode(new NullSafe(r132, r44 + r112, (r102 - r44) - r112, this.pCtx, this.first));     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        if (r0 != null) goto L70;
    L66:
        r112 = 0;
    L70:
        if (r0 != null) goto L74;
        throw new NullPointerException();     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
    L74:
        this.first = false;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        this.staticAccess = false;     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        goto L46
    L54:
        r0 = getCollectionPropertyAO(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        goto L57
    L55:
        r0 = getMethod(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        goto L57
    L56:
        r0 = getBeanPropertyAO(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 IndexOutOfBoundsException -> L18 IllegalAccessException -> L20 InvocationTargetException -> L22 CompileException -> L81
        goto L57
    }

    private Object getBeanProperty(Object r10, String r11) {
        ParserContext r1 = this.pCtx;
        if (r1 != null) goto L5;
        Class r12 = this.currType;
    L6:
        Object r3 = null;
        if (r12 != Object.class) goto L12;
        if (this.pCtx.isStrongTyping() == true) goto L12;
        this.currType = null;
    L12:
        if (this.first == true) goto L14;
    L32:
        boolean r13 = r10 instanceof Class;
        if (r13 == true) goto L35;
        if (r10 == null) goto L43;
        Class<?> r5 = r10.getClass();
    L42:
        boolean r6 = false;
    L45:
        if (PropertyHandlerFactory.hasPropertyHandler(r5) == false) goto L48;
        PropertyHandlerAccessor r0 = new PropertyHandlerAccessor(r11, r5, PropertyHandlerFactory.getPropertyHandler(r5));
        addAccessorNode(r0);
        return r0.getValue(r10, this.thisRef, this.variableFactory);
    L48:
        if (r5 == null) goto L50;
        Member r7 = PropertyTools.getFieldOrAccessor(r5, r11);
    L51:
        if (r7 == null) goto L57;
        if (r6 == false) goto L57;
        if ((r7.getModifiers() & 8) != 0) goto L57;
        r7 = null;
    L57:
        if ((r7 instanceof Method) == false) goto L87;
        if (r10 == null) goto L63;
        r3 = ((Method) r7).invoke(r10, EMPTYARG);     // Catch: IllegalArgumentException -> L60 IllegalAccessException -> L74
    L74:
        Method r02 = (Method) r7;
        Method r14 = ParseTools.determineActualTargetMethod(r02);
        if (r14 == null) goto L86;
        Object r112 = r14.invoke(r10, EMPTYARG);
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L81;
        addAccessorNode(new GetterAccessorNH(r02, PropertyHandlerFactory.getNullMethodHandler()));
        if (r112 != null) goto L82;
        r3 = PropertyHandlerFactory.getNullMethodHandler().getProperty(r7.getName(), r10, this.variableFactory);
    L83:
        this.currType = ReflectionUtil.toNonPrimitiveType(((Method) r7).getReturnType());
        return r3;
    L82:
        r3 = r112;
        goto L83
    L81:
        addAccessorNode(new GetterAccessor(r14));
        goto L82
    L86:
        throw new PropertyAccessException("could not access field: " + r5.getName() + "." + r11, this.expr, this.start, this.pCtx);
    L60:
        e = move-exception;
        if (r7.getDeclaringClass().equals(r10) == true) goto L186;
    L73:
        throw e;
    L186:
        throw new CompileException("name collision between innerclass: " + Class.forName(r7.getDeclaringClass().getName() + "$" + r11).getCanonicalName() + "; and bean accessor: " + r11 + " (" + r7.toString() + ")", this.expr, this.tkStart);     // Catch: ClassNotFoundException -> L182
    L63:
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L67;
        addAccessorNode(new GetterAccessorNH((Method) r7, PropertyHandlerFactory.getNullPropertyHandler()));     // Catch: IllegalArgumentException -> L60 IllegalAccessException -> L74
        if (r3 != null) goto L83;
        r3 = PropertyHandlerFactory.getNullPropertyHandler().getProperty(r7.getName(), r10, this.variableFactory);     // Catch: IllegalArgumentException -> L60 IllegalAccessException -> L74
        goto L83
    L67:
        addAccessorNode(new GetterAccessor((Method) r7));     // Catch: IllegalArgumentException -> L60 IllegalAccessException -> L74
        goto L83
    L87:
        if (r7 == null) goto L108;
        Field r113 = (Field) r7;
        if ((r113.getModifiers() & 8) == 0) goto L96;
        Object r03 = r113.get(null);
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L95;
        addAccessorNode(new StaticVarAccessorNH(r113, PropertyHandlerFactory.getNullMethodHandler()));
        if (r03 != null) goto L105;
        r03 = PropertyHandlerFactory.getNullMethodHandler().getProperty(r7.getName(), r10, this.variableFactory);
    L105:
        this.currType = ReflectionUtil.toNonPrimitiveType(r113.getType());
        return r03;
    L95:
        addAccessorNode(new StaticVarAccessor(r113));
        goto L105
    L96:
        if (r10 == null) goto L99;
        r3 = r113.get(r10);
    L99:
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L103;
        addAccessorNode(new FieldAccessorNH(r113, PropertyHandlerFactory.getNullMethodHandler()));
        if (r3 != null) goto L104;
        r03 = PropertyHandlerFactory.getNullMethodHandler().getProperty(r7.getName(), r10, this.variableFactory);
    L104:
        r03 = r3;
        goto L105
    L103:
        addAccessorNode(new FieldAccessor(r113));
        goto L104
    L108:
        if ((r10 instanceof Map) == false) goto L115;
        Map r04 = (Map) r10;
        if (r04.containsKey(r11) == false) goto L112;
    L113:
        addAccessorNode(new MapAccessor(r11));
        return r04.get(r11);
    L112:
        if (this.nullSafe == true) goto L113;
    L115:
        if (r10 == null) goto L123;
        if ("length".equals(r11) == false) goto L123;
        if (r10.getClass().isArray() == false) goto L123;
        addAccessorNode(new ArrayLength());
        return Integer.valueOf(Array.getLength(r10));
    L123:
        if (AbstractParser.LITERALS.containsKey(r11) == false) goto L126;
        Object r114 = AbstractParser.LITERALS.get(r11);
        addAccessorNode(new StaticReferenceAccessor(r114));
        return r114;
    L126:
        Object r05 = tryStaticAccess();
        this.staticAccess = true;
        if (r05 != null) goto L129;
        if (r13 == false) goto L166;
        Class r06 = (Class) r10;
        Method[] r15 = r06.getMethods();
        int r2 = r15.length;
        int r52 = 0;
    L140:
        if (r52 >= r2) goto L184;
        Method r62 = r15[r52];
        if (r11.equals(r62.getName()) == true) goto L143;
        r52 = r52 + 1;
        goto L140
    L143:
        ParserContext r115 = this.pCtx;
        if (r115 == null) goto L151;
        if (r115.getParserConfiguration() == null) goto L151;
        if (this.pCtx.getParserConfiguration().isAllowNakedMethCall() == true) goto L152;
    L161:
        addAccessorNode(new StaticReferenceAccessor(r62));
        return r62;
    L152:
        Object r116 = r62.invoke(null, ParseTools.EMPTY_OBJ_ARR);
        if (PropertyHandlerFactory.hasNullMethodHandler() == false) goto L159;
        addAccessorNode(new MethodAccessorNH(r62, new ExecutableStatement[0], PropertyHandlerFactory.getNullMethodHandler()));
        if (r116 == null) goto L157;
        return r116;
    L157:
        return PropertyHandlerFactory.getNullMethodHandler().getProperty(r62.getName(), r10, this.variableFactory);
    L159:
        addAccessorNode(new MethodAccessor(r62, new ExecutableStatement[0]));
        return r116;
    L151:
        if (MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL == false) goto L161;
    L184:
        Class r07 = ParseTools.findClass(this.variableFactory, r06.getName() + "$" + r11, this.pCtx);     // Catch: ClassNotFoundException -> L183
        addAccessorNode(new StaticReferenceAccessor(r07));     // Catch: ClassNotFoundException -> L183
        return r07;
    L177:
        if (r10 != null) goto L181;
        throw new PropertyAccessException("unresolvable property or identifier: " + r11, this.expr, this.start, this.pCtx);
    L181:
        throw new PropertyAccessException("could not access: " + r11 + "; in class: " + r10.getClass().getName(), this.expr, this.start, this.pCtx);
    L166:
        ParserContext r08 = this.pCtx;
        if (r08 == null) goto L174;
        if (r08.getParserConfiguration() == null) goto L174;
        if (this.pCtx.getParserConfiguration().isAllowNakedMethCall() == false) goto L177;
    L176:
        return getMethod(r10, r11);
    L174:
        if (MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL == false) goto L177;
    L129:
        if ((r05 instanceof Class) == false) goto L133;
        addAccessorNode(new StaticReferenceAccessor(r05));
        return r05;
    L133:
        if ((r05 instanceof Field) == false) goto L136;
        Field r09 = (Field) r05;
        addAccessorNode(new StaticVarAccessor(r09));
        return r09.get(null);
    L136:
        addAccessorNode(new StaticReferenceAccessor(r05));
        return r05;
    L50:
        r7 = null;
        goto L51
    L43:
        r5 = this.currType;
        goto L42
    L35:
        if (MVEL.COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS == true) goto L37;
    L39:
        r5 = (Class) r10;
        r6 = true;
        goto L45
    L37:
        if ("class".equals(r11) == false) goto L39;
        return r10;
    L14:
        if ("this".equals(r11) == false) goto L17;
        addAccessorNode(new ThisValueAccessor());
        return this.thisRef;
    L17:
        VariableResolverFactory r16 = this.variableFactory;
        if (r16 == null) goto L32;
        if (r16.isResolveable(r11) == false) goto L32;
        if (this.variableFactory.isIndexedFactory() == true) goto L24;
    L30:
        addAccessorNode(new VariableAccessor(r11));
        return this.variableFactory.getVariableResolver(r11).getValue();
    L24:
        if (this.variableFactory.isTarget(r11) == false) goto L30;
        int r010 = this.variableFactory.variableIndexOf(r11);
        addAccessorNode(new IndexedVariableAccessor(r010));
        if (this.variableFactory.getIndexedVariableResolver(r010) != null) goto L29;
        VariableResolverFactory r102 = this.variableFactory;
        r102.setIndexedVariableResolver(r010, r102.getVariableResolver(r11));
    L29:
        return this.variableFactory.getIndexedVariableResolver(r010).getValue();
    L5:
        r12 = r1.getVarOrInputTypeOrNull(r11);
        goto L6
    }

    private Object getBeanPropertyAO(Object r2, String r3) {
        if (GlobalListenerFactory.hasGetListeners() == false) goto L5;
        GlobalListenerFactory.notifyGetListeners(r2, r3, this.variableFactory);
        addAccessorNode(new Notify(r3));
    L5:
        if (r2 == null) goto L11;
        if (PropertyHandlerFactory.hasPropertyHandler(r2.getClass()) == false) goto L11;
        return propHandler(r3, r2, r2.getClass());
    L11:
        return getBeanProperty(r2, r3);
    }

    private Object getCollectionProperty(Object r9, String r10) {
        if (r10.length() <= 0) goto L5;
        r9 = getBeanProperty(r9, r10);
    L5:
        this.currType = null;
        if (r9 != null) goto L8;
        return null;
    L8:
        int r0 = this.cursor + 1;
        this.cursor = r0;
        skipWhitespace();
        if (this.cursor == this.end) goto L61;
        boolean r2 = scanTo(']');
        char[] r3 = this.expr;
        if (r2 == true) goto L59;
        String r22 = new String(r3, r0, this.cursor - r0);
        Integer r4 = Integer.valueOf(Integer.parseInt(r22));     // Catch: Exception -> L15
        boolean r5 = false;
    L16:
        if (r5 == true) goto L65;
        Object r02 = r4;
        ExecutableStatement r42 = null;
    L23:
        this.cursor++;
        if ((r9 instanceof Map) == false) goto L31;
        if (r5 == false) goto L27;
        addAccessorNode(new MapAccessorNest(r42, null));
    L29:
        return ((Map) r9).get(r02);
    L27:
        addAccessorNode(new MapAccessor(Integer.valueOf(Integer.parseInt(r22))));
        goto L29
    L31:
        if ((r9 instanceof List) == false) goto L38;
        if (r5 == false) goto L34;
        addAccessorNode(new ListAccessorNest(r42, null));
    L36:
        return ((List) r9).get(((Integer) r02).intValue());
    L34:
        addAccessorNode(new ListAccessor(Integer.parseInt(r22)));
        goto L36
    L38:
        if (r9.getClass().isArray() == false) goto L45;
        if (r5 == false) goto L41;
        addAccessorNode(new ArrayAccessorNest(r42));
    L43:
        return Array.get(r9, ((Integer) r02).intValue());
    L41:
        addAccessorNode(new ArrayAccessor(Integer.parseInt(r22)));
        goto L43
    L45:
        if ((r9 instanceof CharSequence) == false) goto L52;
        if (r5 == false) goto L48;
        addAccessorNode(new IndexedCharSeqAccessorNest(r42));
    L50:
        return Character.valueOf(((CharSequence) r9).charAt(((Integer) r02).intValue()));
    L48:
        addAccessorNode(new IndexedCharSeqAccessor(Integer.parseInt(r22)));
        goto L50
    L52:
        if ((r9 instanceof Class) == false) goto L57;
        TypeDescriptor r03 = new TypeDescriptor(this.expr, this.start, this.length, 0);
        if (r03.isArray() == false) goto L57;
        Class r92 = TypeDescriptor.getClassReference((Class) r9, r03, this.variableFactory, this.pCtx);
        this.rootNode = new StaticReferenceAccessor(r92);
        return r92;
    L57:
        Sign$$ExternalSyntheticBUOutline0.m("illegal use of []: unknown type: ".concat(r9.getClass().getName()), this.expr, this.start);
        return null;
    L65:
        r42 = (ExecutableStatement) ParseTools.subCompileExpression(r22.toCharArray(), this.pCtx);     // Catch: CompileException -> L19
        Object r6 = this.thisRef;     // Catch: CompileException -> L19
        r02 = r42.getValue(r6, r6, this.variableFactory);     // Catch: CompileException -> L19
    L19:
        e = move-exception;
        e.setExpr(this.expr);
        e.setCursor(r0);
        throw e;
    L15:
        r4 = null;
        r5 = true;
        goto L16
    L59:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", r3, this.start);
        return null;
    L61:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", this.expr, this.start);
        return null;
    }

    private Object getCollectionPropertyAO(Object r8, String r9) {
        if (r9.length() <= 0) goto L5;
        r8 = getBeanPropertyAO(r8, r9);
    L5:
        this.currType = null;
        if (r8 != null) goto L8;
        return null;
    L8:
        int r0 = this.cursor + 1;
        this.cursor = r0;
        skipWhitespace();
        if (this.cursor == this.end) goto L71;
        boolean r2 = scanTo(']');
        char[] r3 = this.expr;
        if (r2 == true) goto L69;
        String r22 = new String(r3, r0, this.cursor - r0);
        Integer r32 = Integer.valueOf(Integer.parseInt(r22));     // Catch: Exception -> L15
        boolean r4 = false;
    L16:
        if (r4 == false) goto L18;
        ExecutableStatement r33 = (ExecutableStatement) ParseTools.subCompileExpression(r22.toCharArray(), this.pCtx);
        Object r5 = this.thisRef;
        Object r52 = r33.getValue(r5, r5, this.variableFactory);
    L19:
        this.cursor++;
        if ((r8 instanceof Map) == false) goto L31;
        if (PropertyHandlerFactory.hasPropertyHandler(Map.class) == true) goto L24;
        if (r4 == false) goto L27;
        addAccessorNode(new MapAccessorNest(r33, null));
    L29:
        return ((Map) r8).get(r52);
    L27:
        addAccessorNode(new MapAccessor(Integer.valueOf(Integer.parseInt(r22))));
        goto L29
    L24:
        return propHandler(r22, r8, Map.class);
    L31:
        if ((r8 instanceof List) == false) goto L42;
        if (PropertyHandlerFactory.hasPropertyHandler(List.class) == true) goto L35;
        if (r4 == false) goto L38;
        addAccessorNode(new ListAccessorNest(r33, null));
    L40:
        return ((List) r8).get(((Integer) r52).intValue());
    L38:
        addAccessorNode(new ListAccessor(Integer.parseInt(r22)));
        goto L40
    L35:
        return propHandler(r22, r8, List.class);
    L42:
        if (r8.getClass().isArray() == false) goto L53;
        if (PropertyHandlerFactory.hasPropertyHandler(Array.class) == true) goto L46;
        if (r4 == false) goto L49;
        addAccessorNode(new ArrayAccessorNest(r33));
    L51:
        return Array.get(r8, ((Integer) r52).intValue());
    L49:
        addAccessorNode(new ArrayAccessor(Integer.parseInt(r22)));
        goto L51
    L46:
        return propHandler(r22, r8, Array.class);
    L53:
        if ((r8 instanceof CharSequence) == true) goto L55;
        char[] r23 = this.expr;
        int r34 = this.start;
        TypeDescriptor r1 = new TypeDescriptor(r23, r34, this.end - r34, 0);
        if (r1.isArray() == false) goto L67;
        Class r82 = TypeDescriptor.getClassReference((Class) r8, r1, this.variableFactory, this.pCtx);
        this.rootNode = new StaticReferenceAccessor(r82);
        return r82;
    L67:
        Sign$$ExternalSyntheticBUOutline0.m("illegal use of []: unknown type: ".concat(r8.getClass().getName()), this.expr, this.st);
        return null;
    L55:
        if (PropertyHandlerFactory.hasPropertyHandler(CharSequence.class) == true) goto L57;
        if (r4 == false) goto L60;
        addAccessorNode(new IndexedCharSeqAccessorNest(r33));
    L62:
        return Character.valueOf(((CharSequence) r8).charAt(((Integer) r52).intValue()));
    L60:
        addAccessorNode(new IndexedCharSeqAccessor(Integer.parseInt(r22)));
        goto L62
    L57:
        return propHandler(r22, r8, CharSequence.class);
    L18:
        r52 = r32;
        r33 = null;
    L15:
        r32 = null;
        r4 = true;
        goto L16
    L69:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", r3, this.start);
        return null;
    L71:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", this.expr, this.start);
        return null;
    }

    private Object getMethod(Object r14, String r15) {
        int r0 = this.cursor;
        if (r0 == this.end) goto L9;
        char[] r1 = this.expr;
        if (r1[r0] != '(') goto L9;
        int r12 = ParseTools.balancedCapture(r1, r0, '(');
        this.cursor = r12;
        if ((r12 - r0) <= 1) goto L9;
        String r3 = new String(this.expr, r0 + 1, (r12 - r0) - 1);
    L10:
        this.cursor++;
        if (r3.length() != 0) goto L14;
        Object[] r10 = ParseTools.EMPTY_OBJ_ARR;
        ExecutableStatement[] r122 = null;
        Class[] r11 = ParseTools.EMPTY_CLS_ARR;
    L49:
        return getMethod(r14, r15, r10, r11, r122);
    L14:
        char[] r02 = r3.toCharArray();
        int r32 = 0;
        List<char[]> r03 = ParseTools.parseParameterList(r02, 0, -1);
        ExecutableStatement[] r2 = new ExecutableStatement[r03.size()];
        int r4 = r03.size();
        Object[] r5 = new Object[r4];
        Class[] r6 = new Class[r03.size()];
        int r7 = 0;
    L16:
        if (r7 >= r03.size()) goto L26;
        ExecutableStatement r8 = (ExecutableStatement) ParseTools.subCompileExpression(r03.get(r7), this.pCtx);     // Catch: CompileException -> L22
        r2[r7] = r8;     // Catch: CompileException -> L22
        Object r9 = this.thisRef;     // Catch: CompileException -> L22
        r5[r7] = r8.getValue(r9, r9, this.variableFactory);     // Catch: CompileException -> L22
        if (r2[r7].isExplicitCast() == false) goto L21;
        r6[r7] = r2[r7].getKnownEgressType();
    L21:
        r7 = r7 + 1;
    L22:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, this.expr, this.start);
    L26:
        if (this.pCtx.isStrictTypeEnforcement() == false) goto L34;
    L27:
        if (r32 >= r4) goto L47;
        r6[r32] = r2[r32].getKnownEgressType();
        ExecutableStatement r04 = r2[r32];
        if ((r04 instanceof ExecutableLiteral) == false) goto L33;
        if (((ExecutableLiteral) r04).getLiteral() != null) goto L33;
        r6[r32] = NullType.class;
    L33:
        r32 = r32 + 1;
    L47:
        r122 = r2;
        r10 = r5;
        r11 = r6;
    L34:
        if (r32 >= r4) goto L47;
        if (r6[r32] != null) goto L46;
        if (r2[r32].getKnownEgressType() != Object.class) goto L45;
        Object r05 = r5[r32];
        if (r05 != null) goto L43;
        Class<?> r06 = null;
    L44:
        r6[r32] = r06;
        goto L46
    L43:
        r06 = r05.getClass();
        goto L44
    L45:
        r6[r32] = r2[r32].getKnownEgressType();
    L46:
        r32 = r32 + 1;
    L9:
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    private Object getMethod(Class<?> r10, String r11, Object[] r12, Class[] r13, ExecutableStatement[] r14) {
        Object r1 = null;
        String r15 = null;
        int r2 = 0;
        if (this.first == false) goto L15;
        VariableResolverFactory r0 = this.variableFactory;
        if (r0 == null) goto L15;
        if (r0.isResolveable(r11) == false) goto L15;
        Object r02 = this.variableFactory.getVariableResolver(r11).getValue();
        if ((r02 instanceof Method) == false) goto L12;
        Method r03 = (Method) r02;
        r10 = r03.getDeclaringClass();
        r11 = r03.getName();
    L14:
        this.first = false;
        goto L15
    L12:
        if ((r02 instanceof MethodStub) == false) goto L17;
        MethodStub r04 = (MethodStub) r02;
        r10 = r04.getClassReference();
        r11 = r04.getMethodName();
        goto L14
    L17:
        if ((r02 instanceof FunctionInstance) == false) goto L24;
        FunctionInstance r05 = (FunctionInstance) r02;
        if (r11.equals(r05.getFunction().getName()) == true) goto L21;
        getBeanProperty(r10, r11);
        addAccessorNode(new DynamicFunctionAccessor(r14));
    L23:
        return r05.call(r10, this.thisRef, this.variableFactory, r12);
    L21:
        addAccessorNode(new FunctionAccessor(r05, r14));
        goto L23
    L24:
        StringBuilder r122 = new StringBuilder("attempt to optimize a method call for a reference that does not point to a method: ");
        r122.append(r11);
        r122.append(" (reference is type: ");
        if (r10 == null) goto L27;
        r15 = r10.getClass().getName();
    L27:
        r122.append(r15);
        r122.append(")");
        throw new OptimizationFailure(r122.toString());
    L15:
        String r4 = r11;
        if (r10 == null) goto L31;
    L35:
        Class<?> r112 = this.currType;
        if (r112 == null) goto L38;
        Class<?> r5 = r112;
        boolean r8 = false;
    L43:
        this.currType = null;
        Method r113 = ParseTools.getBestCandidate(r13, r4, r5, r5.getMethods(), false, r8);
        if (r113 == null) goto L46;
        Class<?>[] r132 = r113.getParameterTypes();
    L47:
        if (r113 != null) goto L52;
        if (r8 == false) goto L52;
        r113 = ParseTools.getBestCandidate(r13, r4, r5, Class.class.getMethods(), false);
        if (r113 == null) goto L52;
        r132 = r113.getParameterTypes();
    L52:
        if (r113 != null) goto L61;
        if (r10 == null) goto L61;
        if (r5 == r10.getClass()) goto L61;
        if ((r10 instanceof Class) == true) goto L61;
        r5 = r10.getClass();
        r113 = ParseTools.getBestCandidate(r13, r4, r5, r5.getMethods(), false, r8);
        if (r113 == null) goto L61;
        r132 = r113.getParameterTypes();
    L61:
        if (r113 != null) goto L82;
        StringAppender r114 = new StringAppender();
        if ("size".equals(r4) == false) goto L71;
        if (r12.length != 0) goto L71;
        if (r5.isArray() == false) goto L71;
        addAccessorNode(new ArrayLength());
        return Integer.valueOf(Array.getLength(r10));
    L71:
        if (r2 >= r12.length) goto L81;
        Object r102 = r12[r2];
        if (r102 == null) goto L75;
        String r103 = r102.getClass().getName();
    L76:
        r114.append(r103);
        if (r2 >= (r12.length - 1)) goto L79;
        r114.append(", ");
    L79:
        r2 = r2 + 1;
        goto L71
    L75:
        r103 = null;
        goto L76
    L81:
        throw new PropertyAccessException("unable to resolve method: " + r5.getName() + "." + r4 + "(" + r114.toString() + ") [arglength=" + r12.length + "]", this.expr, this.st, this.pCtx);
    L82:
        if (r14 == null) goto L93;
    L84:
        if (r2 >= r14.length) goto L95;
        ExecutableStatement r06 = r14[r2];
        if (r06.getKnownIngressType() != null) goto L89;
        r06.setKnownIngressType(Varargs.paramTypeVarArgsSafe(r132, r2, r113.isVarArgs()));
        r06.computeTypeConversionRule();
    L89:
        if (r06.isConvertableIngressEgress() == true) goto L91;
        r12[r2] = DataConversion.convert(r12[r2], Varargs.paramTypeVarArgsSafe(r132, r2, r113.isVarArgs()));
    L91:
        r2 = r2 + 1;
    L95:
        Method r07 = ParseTools.getWidenedTarget(r5, r113);
        if (r10 == null) goto L99;
        r1 = r07.invoke(r10, Varargs.normalizeArgsForVarArgs(r132, r12, r113.isVarArgs()));
    L99:
        if (PropertyHandlerFactory.hasNullMethodHandler() == false) goto L103;
        addAccessorNode(new MethodAccessorNH(r07, r14, PropertyHandlerFactory.getNullMethodHandler()));
        if (r1 != null) goto L104;
        r1 = PropertyHandlerFactory.getNullMethodHandler().getProperty(r113.getName(), r10, this.variableFactory);
    L104:
        this.currType = ReflectionUtil.toNonPrimitiveType(r07.getReturnType());
        return r1;
    L103:
        addAccessorNode(new MethodAccessor(r07, r14));
    L93:
        if (r2 >= r12.length) goto L95;
        r12[r2] = DataConversion.convert(r12[r2], Varargs.paramTypeVarArgsSafe(r132, r2, r113.isVarArgs()));
        r2 = r2 + 1;
        goto L93
    L46:
        r132 = null;
        goto L47
    L38:
        boolean r115 = r10 instanceof Class;
        if (r115 == false) goto L42;
        Class<?> r08 = r10;
    L41:
        r8 = r115;
        r5 = r08;
        goto L43
    L42:
        r08 = r10.getClass();
        goto L41
    L31:
        if (this.currType != null) goto L35;
        throw new PropertyAccessException("null pointer or function not found: " + r4, this.expr, this.start, this.pCtx);
    }

    private AccessorNode getRootNode() {
        return this.rootNode;
    }

    private Object getWithProperty(Object r9) {
        String r0 = null;
        this.currType = null;
        int r1 = this.start;
        int r2 = this.cursor;
        if (r1 != r2) goto L5;
    L4:
        String r3 = r0;
        int r02 = this.cursor;
        int r5 = r02 + 1;
        int r03 = ParseTools.balancedCaptureWithLineAccounting(this.expr, r02, this.end, '{', this.pCtx);
        ParserContext r22 = this.pCtx;
        char[] r4 = this.expr;
        this.cursor = r03 + 1;
        WithAccessor r12 = new WithAccessor(r22, r3, r4, r5, r03 - r5, this.ingressType);
        addAccessorNode(r12);
        return r12.getValue(r9, this.thisRef, this.variableFactory);
    L5:
        r0 = new String(this.expr, r1, r2 - 1).trim();
        goto L4
    }

    private Object propHandler(String r3, Object r4, Class r5) {
        PropertyHandler r0 = PropertyHandlerFactory.getPropertyHandler(r5);
        addAccessorNode(new PropertyHandlerAccessor(r3, r5, r0));
        return r0.getProperty(r3, r4, this.variableFactory);
    }

    private void setRootNode(AccessorNode r1) {
        this.currNode = r1;
        this.rootNode = r1;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Class getEgressType() {
        return this.returnType;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Object getResultOptPass() {
        return this.val;
    }

    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        return this.rootNode.getValue(r1, r2, r3);
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public void init() {
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public boolean isLiteralOnly() {
        return false;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeAccessor(ParserContext r12, char[] r13, int r14, int r15, Object r16, Object r17, VariableResolverFactory r18, boolean r19, Class r20) {
        return optimizeAccessor(r12, r13, r14, r15, r16, r17, r18, r19, r20, true);
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeAccessor(ParserContext r1, char[] r2, int r3, int r4, Object r5, Object r6, VariableResolverFactory r7, boolean r8, Class r9, boolean r10) {
        this.currNode = null;
        this.rootNode = null;
        this.expr = r2;
        this.start = r3;
        int r42 = r4 + r3;
        this.end = r42;
        this.length = r42 - r3;
        this.first = r10;
        this.ctx = r5;
        this.thisRef = r6;
        this.variableFactory = r7;
        this.ingressType = r9;
        this.pCtx = r1;
        return compileGetChain();
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeCollection(ParserContext r1, Object r2, Class r3, char[] r4, int r5, int r6, Object r7, Object r8, VariableResolverFactory r9) {
        this.cursor = r5;
        this.start = r5;
        this.length = r5 + r6;
        this.returnType = r3;
        this.ctx = r7;
        this.variableFactory = r9;
        this.pCtx = r1;
        Accessor r22 = _getAccessor(r2, r3);
        if (r4 != null) goto L5;
    L8:
        return r22;
    L5:
        if (this.length <= r5) goto L8;
        return new Union(r1, r22, r4, this.cursor, r6);
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeObjectCreation(ParserContext r1, char[] r2, int r3, int r4, Object r5, Object r6, VariableResolverFactory r7) {
        this.length = r4 + r3;
        this.start = r3;
        this.cursor = r3;
        this.pCtx = r1;
        return compileConstructor(r2, r5, r7);
    L9:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("could not resolve class: " + e.getMessage(), r2, this.start, e);
        return null;
    L11:
        e = move-exception;
        throw ErrorUtil.rewriteIfNeeded(e, r2, this.start);
    L5:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("could not create constructor: " + e.getMessage(), r2, this.start, e);
        return null;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeSetAccessor(ParserContext r17, char[] r18, int r19, int r20, Object r21, Object r22, VariableResolverFactory r23, boolean r24, Object r25, Class r26) {
        char[] r0 = r18;
        this.currNode = null;
        this.rootNode = null;
        this.expr = r0;
        this.start = r19;
        this.first = true;
        this.length = r19 + r20;
        this.ctx = r21;
        this.thisRef = r22;
        this.variableFactory = r23;
        this.ingressType = r26;
        int r12 = findLastUnion();
        this.pCtx = r17;
        PropertyVerifier r13 = new PropertyVerifier(r0, r17);
        if (r12 == (-1)) goto L6;
        int r14 = r12 + 1;
        char[] r122 = ParseTools.subset(r0, 0, r12);
        r0 = ParseTools.subset(r0, r14, r0.length - r14);
    L5:
        char[] r10 = r0;
        if (r122 == null) goto L9;
        this.expr = r122;
        int r02 = r122.length;
        this.end = r02;
        this.length = r02;
        compileGetChain();
        Object r03 = this.val;
    L11:
        if (r03 == null) goto L133;
        this.expr = r10;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        int r123 = r10.length;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        this.end = r123;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        this.length = r123;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        this.cursor = 0;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        skipWhitespace();     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if (this.collection == false) goto L70;
        int r8 = this.cursor;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if (r8 == this.end) goto L68;
        if (scanTo(']') == true) goto L66;
        String r11 = new String(r10, r8, this.cursor - r8);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if ((r03 instanceof Map) == false) goto L38;
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L25;
    L33:
        Map r5 = (Map) r03;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        Object r04 = MVEL.eval(r11, r03, this.variableFactory);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        Class r6 = r13.analyze();     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        this.returnType = r6;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        r5.put(r04, DataConversion.convert(r25, r6));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new MapAccessorNest(r11, this.returnType));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L35:
        return this.rootNode;
    L25:
        if (PropertyHandlerFactory.hasPropertyHandler(Map.class) == false) goto L33;
        propHandlerSet(r11, r03, Map.class, r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L35
    L38:
        if ((r03 instanceof List) == false) goto L48;
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L42;
    L44:
        List r52 = (List) r03;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        int r05 = ((Integer) MVEL.eval(r11, r03, this.variableFactory, Integer.class)).intValue();     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        Class r62 = r13.analyze();     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        this.returnType = r62;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        r52.set(r05, DataConversion.convert(r25, r62));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new ListAccessorNest(r11, this.returnType));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L46:
        return this.rootNode;
    L42:
        if (PropertyHandlerFactory.hasPropertyHandler(List.class) == false) goto L44;
        propHandlerSet(r11, r03, List.class, r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L46
    L48:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == false) goto L54;
        if (PropertyHandlerFactory.hasPropertyHandler(r03.getClass()) == false) goto L54;
        propHandlerSet(r11, r03, r03.getClass(), r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        return this.rootNode;
    L54:
        if (r03.getClass().isArray() == false) goto L64;
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L58;
    L60:
        Array.set(r03, ((Integer) MVEL.eval(r11, r03, this.variableFactory, Integer.class)).intValue(), DataConversion.convert(r25, ParseTools.getBaseComponentType(r03.getClass())));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new ArrayAccessorNest(r11));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L62:
        return this.rootNode;
    L58:
        if (PropertyHandlerFactory.hasPropertyHandler(Array.class) == false) goto L60;
        propHandlerSet(r11, r03, Array.class, r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L62
    L64:
        throw new PropertyAccessException("cannot bind to collection property: " + new String(r10) + ": not a recognized collection type: " + r03.getClass(), this.expr, this.st, r17);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L66:
        throw new PropertyAccessException("unterminated '['", this.expr, this.start, r17);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L68:
        throw new PropertyAccessException("unterminated '['", this.expr, this.start, r17);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L70:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L72;
    L75:
        String r53 = new String(r10, 0, this.length).trim();     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if (GlobalListenerFactory.hasSetListeners() == false) goto L78;
        GlobalListenerFactory.notifySetListeners(r03, r53, this.variableFactory, r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new Notify(r53));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L78:
        Class<?> r63 = r03.getClass();     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if (r25 != null) goto L81;
        Class r7 = null;
    L82:
        Member r64 = PropertyTools.getFieldOrWriteAccessor(r63, r53, r7);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if ((r64 instanceof Field) == false) goto L99;
        Field r65 = (Field) r64;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if (r25 != null) goto L88;
    L94:
        if (r25 == null) goto L96;
    L98:
        r65.set(r03, r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new FieldAccessor(r65));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L119:
        return this.rootNode;
    L96:
        if (r65.getType().isPrimitive() == false) goto L98;
        r65.set(r03, PropertyTools.getPrimitiveInitialValue(r65.getType()));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new FieldAccessor(r65));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L119
    L88:
        if (r65.getType().isAssignableFrom(r25.getClass()) == true) goto L94;
        if (DataConversion.canConvert(r65.getType(), r25.getClass()) == false) goto L93;
        r65.set(r03, DataConversion.convert(r25, r65.getType()));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new DynamicFieldAccessor(r65));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L119
    L93:
        throw new CompileException("cannot convert type: " + r25.getClass() + ": to " + r65.getType(), this.expr, this.start);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L99:
        if (r64 == null) goto L116;
        Method r66 = (Method) r64;     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        if (r25 != null) goto L103;
    L109:
        if (r25 == null) goto L111;
    L113:
        r66.invoke(r03, new Object[]{r25});     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L114:
        addAccessorNode(new SetterAccessor(r66));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L119
    L111:
        if (r66.getParameterTypes()[0].isPrimitive() == false) goto L113;
        r66.invoke(r03, new Object[]{PropertyTools.getPrimitiveInitialValue(r66.getParameterTypes()[0])});     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L114
    L103:
        if (r66.getParameterTypes()[0].isAssignableFrom(r25.getClass()) == true) goto L109;
        if (DataConversion.canConvert(r66.getParameterTypes()[0], r25.getClass()) == false) goto L108;
        r66.invoke(r03, new Object[]{DataConversion.convert(r25, r66.getParameterTypes()[0])});     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L114
    L108:
        throw new CompileException("cannot convert type: " + r25.getClass() + ": to " + r66.getParameterTypes()[0], this.expr, this.start);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L116:
        if ((r03 instanceof Map) == false) goto L121;
        ((Map) r03).put(r53, r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        addAccessorNode(new MapAccessor(r53));     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        goto L119
    L121:
        throw new PropertyAccessException("could not access property (" + r53 + ") in: " + r26.getName(), this.expr, this.start, r17);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
    L81:
        r7 = r26;
        goto L82
    L72:
        if (PropertyHandlerFactory.hasPropertyHandler(r03.getClass()) == false) goto L75;
        propHandlerSet(new String(r10), r03, r03.getClass(), r25);     // Catch: IllegalArgumentException -> L27 IllegalAccessException -> L29 InvocationTargetException -> L31
        return this.rootNode;
    L29:
        e = move-exception;
        throw new PropertyAccessException("could not access property: ".concat(new String(r10)), this.expr, this.st, e, r17);
    L27:
        e = move-exception;
        StringBuilder r54 = new StringBuilder("error binding property: ");
        r54.append(new String(r10));
        r54.append(" (value <<");
        r54.append(r25);
        r54.append(">>::");
        if (r25 != null) goto L125;
        String r4 = "null";
    L126:
        r54.append(r4);
        r54.append(")");
        throw new PropertyAccessException(r54.toString(), this.expr, this.st, e, r17);
    L125:
        r4 = r25.getClass().getCanonicalName();
    L31:
        e = move-exception;
        throw new PropertyAccessException("could not access property: ".concat(new String(r10)), this.expr, this.st, e, r17);
    L133:
        throw new PropertyAccessException("could not access property: " + new String(r10, this.start, Math.min(this.length, r10.length)) + "; parent is null: " + new String(this.expr), this.expr, this.start, r17);
    L9:
        r03 = r21;
        goto L11
    L6:
        r122 = null;
        goto L5
    }

    public void propHandlerSet(String r3, Object r4, Class r5, Object r6) {
        PropertyHandler r0 = PropertyHandlerFactory.getPropertyHandler(r5);
        addAccessorNode(new PropertyHandlerAccessor(r3, r5, r0));
        r0.setProperty(r3, r4, this.variableFactory, r6);
    }
}
