package org.mvel2.optimizers.impl.asm;

import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import org.mvel2.CompileException;
import org.mvel2.DataConversion;
import org.mvel2.MVEL;
import org.mvel2.OptimizationFailure;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessException;
import org.mvel2.asm.ClassWriter;
import org.mvel2.asm.Label;
import org.mvel2.asm.MethodVisitor;
import org.mvel2.asm.Type;
import org.mvel2.ast.FunctionInstance;
import org.mvel2.ast.Negation$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.Sign$$ExternalSyntheticBUOutline0;
import org.mvel2.ast.TypeDescriptor;
import org.mvel2.ast.WithNode;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.ExecutableAccessor;
import org.mvel2.compiler.ExecutableLiteral;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.compiler.PropertyVerifier;
import org.mvel2.integration.GlobalListenerFactory;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.PropertyHandlerFactory;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AbstractOptimizer;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizationNotSupported;
import org.mvel2.optimizers.impl.refl.nodes.Union;
import org.mvel2.util.ArrayTools;
import org.mvel2.util.JITClassLoader;
import org.mvel2.util.MVELClassLoader;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.mvel2.util.MethodStub;
import org.mvel2.util.NullType;
import org.mvel2.util.ParseTools;
import org.mvel2.util.PropertyTools;
import org.mvel2.util.ReflectionUtil;
import org.mvel2.util.StringAppender;
import org.mvel2.util.Varargs;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class ASMAccessorOptimizer extends AbstractOptimizer implements AccessorOptimizer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ARRAY = 0;
    private static final Object[] EMPTYARG = null;
    private static final Class[] EMPTYCLS = null;
    private static final int LIST = 1;
    private static String LIST_IMPL = null;
    private static final int MAP = 2;
    private static final String MAP_IMPL = "java/util/HashMap";
    private static String NAMESPACE = null;
    private static final int OPCODES_VERSION = 0;
    private static final int VAL = 3;
    private static MVELClassLoader classLoader;
    private StringAppender buildLog;
    private String className;
    private int compileDepth;
    private ArrayList<ExecutableStatement> compiledInputs;
    private Object ctx;
    private ClassWriter cw;
    private boolean deferFinish;
    private boolean first;
    private Class ingressType;
    private boolean literal;
    private int maxlocals;
    private boolean methNull;
    private MethodVisitor mv;
    private boolean noinit;
    private boolean propNull;
    private Class returnType;
    private int stacksize;
    private Object thisRef;
    private long time;
    private Object val;
    private VariableResolverFactory variableFactory;

    public static class ContextClassLoader extends ClassLoader {
        public ContextClassLoader(ClassLoader r1) {
            super(r1);
        }

        public Class<?> defineClass(String r3, byte[] r4) {
            return defineClass(r3, r4, 0, r4.length);
        }
    }

    static {
        String r0 = PropertyTools.getJavaVersion();
        if (r0.startsWith("1.4") == false) goto L6;
        OPCODES_VERSION = 48;
    L9:
        String r02 = System.getProperty("mvel2.namespace");
        if (r02 != null) goto L12;
        NAMESPACE = "org/mvel2/";
    L13:
        String r03 = System.getProperty("mvel2.jit.list_impl");
        if (r03 != null) goto L16;
        LIST_IMPL = NAMESPACE + "util/FastList";
    L17:
        EMPTYARG = new Object[0];
        EMPTYCLS = new Class[0];
        return;
    L16:
        LIST_IMPL = r03;
        goto L17
    L12:
        NAMESPACE = r02;
        goto L13
    L6:
        if (r0.startsWith("1.5") == false) goto L8;
        OPCODES_VERSION = 49;
        goto L9
    L8:
        OPCODES_VERSION = 50;
        goto L9
    }

    public ASMAccessorOptimizer() {
        this.first = true;
        this.noinit = false;
        this.deferFinish = false;
        this.literal = false;
        this.propNull = false;
        this.methNull = false;
        this.stacksize = 1;
        this.maxlocals = 1;
        this.compileDepth = 0;
        new ClassWriter(1);
    }

    private ASMAccessorOptimizer(ClassWriter r3, MethodVisitor r4, ArrayList<ExecutableStatement> r5, String r6, StringAppender r7, int r8) {
        this.first = true;
        this.literal = false;
        this.propNull = false;
        this.methNull = false;
        this.stacksize = 1;
        this.maxlocals = 1;
        this.cw = r3;
        this.mv = r4;
        this.compiledInputs = r5;
        this.className = r6;
        this.buildLog = r7;
        this.compileDepth = r8 + 1;
        this.noinit = true;
        this.deferFinish = true;
    }

    private void _finishJIT() {
        if (this.deferFinish == false) goto L5;
        return;
    L5:
        Class r0 = this.returnType;
        if (r0 == null) goto L11;
        if (r0.isPrimitive() == false) goto L11;
        wrapPrimitive(this.returnType);
    L11:
        if (this.returnType != Void.TYPE) goto L13;
        this.mv.visitInsn(1);
    L13:
        this.mv.visitInsn(176);
        dumpAdvancedDebugging();
        this.mv.visitMaxs(this.stacksize, this.maxlocals);
        this.mv.visitEnd();
        MethodVisitor r02 = this.cw.visitMethod(1, "getKnownEgressType", "()Ljava/lang/Class;", null, null);
        this.mv = r02;
        r02.visitCode();
        visitConstantClass(this.returnType);
        this.mv.visitInsn(176);
        this.mv.visitMaxs(1, 1);
        this.mv.visitEnd();
        if (this.propNull == false) goto L17;
        this.cw.visitField(1, "nullPropertyHandler", "L" + NAMESPACE + "integration/PropertyHandler;", null, null).visitEnd();
    L17:
        if (this.methNull == false) goto L19;
        this.cw.visitField(1, "nullMethodHandler", "L" + NAMESPACE + "integration/PropertyHandler;", null, null).visitEnd();
    L19:
        buildInputs();
        StringAppender r03 = this.buildLog;
        if (r03 != null) goto L22;
    L26:
        this.cw.visitEnd();
        return;
    L22:
        if (r03.length() == 0) goto L26;
        if (this.expr == null) goto L26;
        MethodVisitor r04 = this.cw.visitMethod(1, "toString", "()Ljava/lang/String;", null, null);
        this.mv = r04;
        r04.visitCode();
        this.mv.visitLabel(new Label());
        this.mv.visitLdcInsn(this.buildLog.toString() + "\n\n## { " + new String(this.expr) + " }");
        this.mv.visitInsn(176);
        this.mv.visitLabel(new Label());
        this.mv.visitMaxs(1, 1);
        this.mv.visitEnd();
        goto L26
    }

    private int _getAccessor(Object r12, Class r13) {
        if ((r12 instanceof List) == false) goto L14;
        this.mv.visitTypeInsn(187, LIST_IMPL);
        this.mv.visitInsn(89);
        this.mv.visitInsn(89);
        List r122 = (List) r12;
        intPush(r122.size());
        this.mv.visitMethodInsn(183, LIST_IMPL, "<init>", "(I)V");
        Iterator r123 = r122.iterator();
    L6:
        if (r123.hasNext() == false) goto L11;
        if (_getAccessor(r123.next(), r13) == 3) goto L10;
        this.mv.visitInsn(87);
    L10:
        this.mv.visitMethodInsn(185, "java/util/List", "add", "(Ljava/lang/Object;)Z");
        this.mv.visitInsn(87);
        this.mv.visitInsn(89);
        goto L6
    L11:
        this.returnType = List.class;
        return 1;
    L14:
        if ((r12 instanceof Map) == false) goto L28;
        this.mv.visitTypeInsn(187, MAP_IMPL);
        this.mv.visitInsn(89);
        this.mv.visitInsn(89);
        Map r124 = (Map) r12;
        intPush(r124.size());
        this.mv.visitMethodInsn(183, MAP_IMPL, "<init>", "(I)V");
        Iterator r0 = r124.keySet().iterator();
    L17:
        if (r0.hasNext() == false) goto L25;
        Object r2 = r0.next();
        this.mv.visitTypeInsn(192, "java/util/Map");
        if (_getAccessor(r2, r13) == 3) goto L22;
        this.mv.visitInsn(87);
    L22:
        if (_getAccessor(r124.get(r2), r13) == 3) goto L24;
        this.mv.visitInsn(87);
    L24:
        this.mv.visitMethodInsn(185, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        this.mv.visitInsn(87);
        this.mv.visitInsn(89);
        goto L17
    L25:
        this.returnType = Map.class;
        return 2;
    L28:
        if ((r12 instanceof Object[]) == false) goto L56;
        Accessor[] r02 = new Accessor[((Object[]) r12).length];
        if (r13 == null) goto L35;
        int r3 = 0;
    L33:
        if (r13.getName().charAt(r3) != '[') goto L60;
        r3 = r3 + 1;
    L60:
        Class r22 = ParseTools.getSubComponentType(r13);     // Catch: ClassNotFoundException -> L39
        createArray(r22, ((Object[]) r12).length);     // Catch: ClassNotFoundException -> L39
        if (r3 <= 1) goto L41;
        Class<?> r03 = ParseTools.findClass(null, ParseTools.repeatChar('[', r3 - 1) + "L" + ParseTools.getBaseComponentType(r13).getName() + ";", this.pCtx);     // Catch: ClassNotFoundException -> L39
    L42:
        this.mv.visitInsn(89);     // Catch: ClassNotFoundException -> L39
        Object[] r125 = (Object[]) r12;     // Catch: ClassNotFoundException -> L39
        int r32 = r125.length;     // Catch: ClassNotFoundException -> L39
        int r4 = 0;
        int r5 = 0;
    L43:
        if (r4 >= r32) goto L52;
        Object r8 = r125[r4];     // Catch: ClassNotFoundException -> L39
        intPush(r5);     // Catch: ClassNotFoundException -> L39
        if (_getAccessor(r8, r03) == 3) goto L48;
        this.mv.visitInsn(87);     // Catch: ClassNotFoundException -> L39
    L48:
        if (r22.isPrimitive() == false) goto L50;
        unwrapPrimitive(r22);     // Catch: ClassNotFoundException -> L39
    L50:
        arrayStore(r22);     // Catch: ClassNotFoundException -> L39
        this.mv.visitInsn(89);     // Catch: ClassNotFoundException -> L39
        r5 = r5 + 1;
        r4 = r4 + 1;
        goto L43
    L52:
        return 0;
    L41:
        r03 = ReflectionUtil.toNonPrimitiveArray(r13);     // Catch: ClassNotFoundException -> L39
    L39:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("this error should never throw:".concat(ParseTools.getBaseComponentType(r13).getName()), e);
        return 0;
    L35:
        r13 = Object[].class;
        r3 = 1;
        goto L60
    L56:
        if (r13.isArray() == false) goto L58;
        writeLiteralOrSubexpression(ParseTools.subCompileExpression(((String) r12).toCharArray(), this.pCtx), ParseTools.getSubComponentType(r13));
    L59:
        return 3;
    L58:
        writeLiteralOrSubexpression(ParseTools.subCompileExpression(((String) r12).toCharArray(), this.pCtx));
        goto L59
    }

    private void _initJIT() {
        if (MVEL.isAdvancedDebugging() == false) goto L5;
        this.buildLog = new StringAppender();
    L5:
        this.cw = new ClassWriter(3);
        Runtime r1 = Runtime.getRuntime();
        monitor-enter(r1);
        ClassWriter r2 = this.cw;     // Catch: Throwable -> L11
        int r3 = OPCODES_VERSION;     // Catch: Throwable -> L11
        String r5 = "ASMAccessorImpl_" + String.valueOf(this.cw.hashCode()).replaceAll("\\-", "_") + (System.currentTimeMillis() / 10) + ((int) (Math.random() * 100.0d));     // Catch: Throwable -> L11
        this.className = r5;     // Catch: Throwable -> L11
        r2.visit(r3, 33, r5, null, "java/lang/Object", new String[]{NAMESPACE + "compiler/Accessor"});     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        MethodVisitor r0 = this.cw.visitMethod(1, "<init>", "()V", null, null);
        r0.visitCode();
        r0.visitVarInsn(25, 0);
        r0.visitMethodInsn(183, "java/lang/Object", "<init>", "()V");
        r0.visitInsn(177);
        r0.visitMaxs(1, 1);
        r0.visitEnd();
        MethodVisitor r02 = this.cw.visitMethod(1, "getValue", "(Ljava/lang/Object;Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;)Ljava/lang/Object;", null, null);
        this.mv = r02;
        r02.visitCode();
        return;
    L11:
        th = move-exception;
        throw th;
    }

    private void _initJIT2() {
        if (MVEL.isAdvancedDebugging() == false) goto L5;
        this.buildLog = new StringAppender();
    L5:
        this.cw = new ClassWriter(3);
        Runtime r1 = Runtime.getRuntime();
        monitor-enter(r1);
        ClassWriter r2 = this.cw;     // Catch: Throwable -> L11
        int r3 = OPCODES_VERSION;     // Catch: Throwable -> L11
        String r5 = "ASMAccessorImpl_" + String.valueOf(this.cw.hashCode()).replaceAll("\\-", "_") + (System.currentTimeMillis() / 10) + ((int) (Math.random() * 100.0d));     // Catch: Throwable -> L11
        this.className = r5;     // Catch: Throwable -> L11
        r2.visit(r3, 33, r5, null, "java/lang/Object", new String[]{NAMESPACE + "compiler/Accessor"});     // Catch: Throwable -> L11
        monitor-exit(r1);     // Catch: Throwable -> L11
        MethodVisitor r0 = this.cw.visitMethod(1, "<init>", "()V", null, null);
        r0.visitCode();
        r0.visitVarInsn(25, 0);
        r0.visitMethodInsn(183, "java/lang/Object", "<init>", "()V");
        r0.visitInsn(177);
        r0.visitMaxs(1, 1);
        r0.visitEnd();
        MethodVisitor r02 = this.cw.visitMethod(1, "setValue", "(Ljava/lang/Object;Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;Ljava/lang/Object;)Ljava/lang/Object;", null, null);
        this.mv = r02;
        r02.visitCode();
        return;
    L11:
        th = move-exception;
        throw th;
    }

    private Accessor _initializeAccessor() {
        String r1 = null;
        if (this.deferFinish == false) goto L5;
        return null;
    L5:
        Class r0 = loadClass(this.className, this.cw.toByteArray());
    L9:
        e = move-exception;
        System.out.println("**** COMPILER BUG! REPORT THIS IMMEDIATELY AT http://jira.codehaus.org/browse/MVEL");
        PrintStream r2 = System.out;
        StringBuilder r3 = new StringBuilder("Expression: ");
        char[] r5 = this.expr;
        if (r5 == null) goto L28;
        r1 = new String(r5);
    L28:
        r3.append(r1);
        r2.println(r3.toString());
        throw e;
    L7:
        if (this.compiledInputs.size() != 0) goto L11;
        Object r22 = r0.newInstance();     // Catch: VerifyError -> L9
    L17:
        if (this.propNull == false) goto L20;
        r0.getField("nullPropertyHandler").set(r22, PropertyHandlerFactory.getNullPropertyHandler());     // Catch: VerifyError -> L9
    L20:
        if (this.methNull == false) goto L23;
        r0.getField("nullMethodHandler").set(r22, PropertyHandlerFactory.getNullMethodHandler());     // Catch: VerifyError -> L9
    L23:
        return (Accessor) r22;
    L11:
        Class<?>[] r23 = new Class[this.compiledInputs.size()];     // Catch: VerifyError -> L9
        int r32 = 0;
    L13:
        if (r32 >= this.compiledInputs.size()) goto L15;
        r23[r32] = ExecutableStatement.class;     // Catch: VerifyError -> L9
        r32 = r32 + 1;     // Catch: VerifyError -> L9
        goto L13
    L15:
        Constructor r24 = r0.getConstructor(r23);     // Catch: VerifyError -> L9
        ArrayList<ExecutableStatement> r33 = this.compiledInputs;     // Catch: VerifyError -> L9
        r22 = r24.newInstance(r33.toArray(new ExecutableStatement[r33.size()]));     // Catch: VerifyError -> L9
        goto L17
    }

    private void addPrintOut(String r6) {
        this.mv.visitFieldInsn(178, "java/lang/System", "out", "Ljava/io/PrintStream;");
        this.mv.visitLdcInsn(r6);
        this.mv.visitMethodInsn(182, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    }

    private Object addSubstatement(ExecutableStatement r6) {
        this.compiledInputs.add(r6);
        this.mv.visitVarInsn(25, 0);
        MethodVisitor r62 = this.mv;
        String r0 = this.className;
        StringBuilder r2 = new StringBuilder("p");
        r2.append(this.compiledInputs.size() - 1);
        r62.visitFieldInsn(180, r0, r2.toString(), "L" + NAMESPACE + "compiler/ExecutableStatement;");
        this.mv.visitVarInsn(25, 2);
        this.mv.visitVarInsn(25, 3);
        this.mv.visitMethodInsn(185, Type.getInternalName(ExecutableStatement.class), "getValue", "(Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;)Ljava/lang/Object;");
        return null;
    }

    private void anyArrayCheck(Class r3) {
        if (r3 != boolean[].class) goto L7;
        this.mv.visitTypeInsn(192, "[Z");
        return;
    L7:
        if (r3 != int[].class) goto L11;
        this.mv.visitTypeInsn(192, "[I");
        return;
    L11:
        if (r3 != float[].class) goto L15;
        this.mv.visitTypeInsn(192, "[F");
        return;
    L15:
        if (r3 != double[].class) goto L19;
        this.mv.visitTypeInsn(192, "[D");
        return;
    L19:
        if (r3 != short[].class) goto L23;
        this.mv.visitTypeInsn(192, "[S");
        return;
    L23:
        if (r3 != long[].class) goto L27;
        this.mv.visitTypeInsn(192, "[J");
        return;
    L27:
        if (r3 != byte[].class) goto L30;
        this.mv.visitTypeInsn(192, "[B");
        return;
    L30:
        MethodVisitor r2 = this.mv;
        if (r3 != char[].class) goto L34;
        r2.visitTypeInsn(192, "[C");
        return;
    L34:
        r2.visitTypeInsn(192, "[Ljava/lang/Object;");
    }

    private void buildInputs() {
        if (this.compiledInputs.size() != 0) goto L5;
        return;
    L5:
        StringAppender r0 = new StringAppender("(");
        int r1 = this.compiledInputs.size();
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L9;
        this.cw.visitField(2, "p" + r3, "L" + NAMESPACE + "compiler/ExecutableStatement;", null, null).visitEnd();
        r0.append("L" + NAMESPACE + "compiler/ExecutableStatement;");
        r3 = r3 + 1;
        goto L7
    L9:
        r0.append(")V");
        MethodVisitor r02 = this.cw.visitMethod(1, "<init>", r0.toString(), null, null);
        r02.visitCode();
        r02.visitVarInsn(25, 0);
        r02.visitMethodInsn(183, "java/lang/Object", "<init>", "()V");
        int r7 = 0;
    L10:
        if (r7 >= r1) goto L12;
        r02.visitVarInsn(25, 0);
        int r8 = r7 + 1;
        r02.visitVarInsn(25, r8);
        r02.visitFieldInsn(181, this.className, "p" + r7, "L" + NAMESPACE + "compiler/ExecutableStatement;");
        r7 = r8;
        goto L10
    L12:
        r02.visitInsn(177);
        r02.visitMaxs(0, 0);
        r02.visitEnd();
    }

    private void checkcast(Class r2) {
        this.mv.visitTypeInsn(192, Type.getInternalName(r2));
    }

    private Accessor compileAccessor() {
        Object r0 = this.ctx;
    L22:
        e = move-exception;
        throw new PropertyAccessException(new String(this.expr), this.expr, this.st, e, this.pCtx);
    L20:
        e = move-exception;
        throw new PropertyAccessException(new String(this.expr), this.expr, this.st, e, this.pCtx);
    L16:
        e = move-exception;
        throw new PropertyAccessException(new String(this.expr), this.expr, this.st, e, this.pCtx);
    L24:
        e = move-exception;
        throw new PropertyAccessException(new String(this.expr), this.expr, this.st, e, this.pCtx);
    L18:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m(e.getMessage(), this.expr, this.st, e);
        return null;
    L79:
        e = move-exception;
        throw e;
    L74:
        e = move-exception;
        throw e;
    L14:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m(e.getMessage(), this.expr, this.st, e);
        return null;
    L4:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L44;
    L6:
        if (this.cursor >= this.end) goto L69;
        int r1 = nextSubToken();     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        if (r1 == 0) goto L28;
        if (r1 == 1) goto L27;
        if (r1 == 2) goto L26;
        if (r1 != 3) goto L30;
        r0 = getWithProperty(r0);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
    L30:
        if (this.fields != (-1)) goto L38;
        if (r0 == null) goto L33;
        this.fields = 0;     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L38
    L33:
        if (this.nullSafe == false) goto L69;
        throw new OptimizationNotSupported();     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
    L38:
        this.first = false;     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        if (this.nullSafe == false) goto L6;
        if (this.cursor >= this.end) goto L6;
        this.mv.visitInsn(89);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        Label r12 = new Label();     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        this.mv.visitJumpInsn(199, r12);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        this.mv.visitInsn(176);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        this.mv.visitLabel(r12);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L6
    L26:
        r0 = getCollectionProperty(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L30
    L27:
        r0 = getMethod(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L30
    L28:
        r0 = getBeanProperty(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
    L69:
        this.val = r0;     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        _finishJIT();     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        return _initializeAccessor();
    L44:
        if (this.cursor >= this.end) goto L69;
        int r13 = nextSubToken();     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        if (r13 == 0) goto L54;
        if (r13 == 1) goto L53;
        if (r13 == 2) goto L52;
        if (r13 != 3) goto L56;
        r0 = getWithProperty(r0);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
    L56:
        if (this.fields != (-1)) goto L64;
        if (r0 == null) goto L59;
        this.fields = 0;     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L64
    L59:
        if (this.nullSafe == false) goto L69;
        throw new OptimizationNotSupported();     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
    L64:
        this.first = false;     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        if (this.nullSafe == false) goto L44;
        if (this.cursor >= this.end) goto L44;
        this.mv.visitInsn(89);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        Label r14 = new Label();     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        this.mv.visitJumpInsn(199, r14);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        this.mv.visitInsn(176);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        this.mv.visitLabel(r14);     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L44
    L52:
        r0 = getCollectionPropertyAO(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L56
    L53:
        r0 = getMethod(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L56
    L54:
        r0 = getBeanPropertyAO(r0, capture());     // Catch: Exception -> L14 NullPointerException -> L16 PropertyAccessException -> L18 IndexOutOfBoundsException -> L20 IllegalAccessException -> L22 InvocationTargetException -> L24 OptimizationNotSupported -> L74 CompileException -> L79
        goto L56
    }

    private void createArray(Class r1, int r2) {
        intPush(r2);
        boolean r22 = r1.isPrimitive();
        MethodVisitor r0 = this.mv;
        if (r22 == false) goto L6;
        r0.visitIntInsn(188, toPrimitiveTypeOperand(r1));
        return;
    L6:
        r0.visitTypeInsn(189, Type.getInternalName(r1));
    }

    private void dataConversion(Class r4) {
        if (r4.equals(Object.class) == false) goto L5;
        return;
    L5:
        ldcClassConstant(r4);
        this.mv.visitMethodInsn(184, NAMESPACE + "DataConversion", "convert", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
    }

    private boolean debug(String r1) {
        StringAppender r0 = this.buildLog;
        if (r0 == null) goto L7;
        r0.append(r1).append("\n");
        return true;
    L7:
        return true;
    }

    private void dumpAdvancedDebugging() {
        if (this.buildLog == null) goto L17;
        PrintStream r0 = System.out;
        StringBuilder r1 = new StringBuilder("JIT Compiler Dump for: <<");
        char[] r2 = this.expr;
        if (r2 != null) goto L8;
        String r22 = null;
    L9:
        r1.append(r22);
        r1.append(">>\n-------------------------------\n");
        r0.println(r1.toString());
        System.out.println(this.buildLog.toString());
        System.out.println("\n<END OF DUMP>\n");
        if (MVEL.isFileDebugging() == false) goto L16;
        FileWriter r02 = ParseTools.getDebugFileWriter();     // Catch: IOException -> L13
        r02.write(this.buildLog.toString());     // Catch: IOException -> L13
        r02.flush();     // Catch: IOException -> L13
        r02.close();     // Catch: IOException -> L13
        return;
    L18:
        return;
    L16:
        return;
    L8:
        r22 = new String(r2);
        goto L9
    }

    private Object getBeanProperty(Object r23, String r24) {
        ParserContext r0 = this.pCtx;
        if (r0 != null) goto L5;
        Class r02 = this.currType;
    L7:
        if (r02 == Object.class) goto L9;
    L11:
        Class r03 = this.returnType;
        if (r03 != null) goto L14;
    L16:
        boolean r04 = r23 instanceof Class;
        int r5 = 0;
        if (r04 == true) goto L19;
        if (r23 == null) goto L27;
        Class<?> r8 = r23.getClass();
        boolean r9 = false;
    L29:
        if (PropertyHandlerFactory.hasPropertyHandler(r8) == false) goto L36;
        PropertyHandler r05 = PropertyHandlerFactory.getPropertyHandler(r8);
        if ((r05 instanceof ProducesBytecode) == false) goto L34;
        ((ProducesBytecode) r05).produceBytecodeGet(this.mv, r24, this.variableFactory);
        return r05.getProperty(r24, r23, this.variableFactory);
    L34:
        GlShader$$ExternalSyntheticBUOutline1.m("unable to compileShared: custom accessor does not support producing bytecode: ".concat(r05.getClass().getName()));
        return null;
    L36:
        if (r8 == null) goto L38;
        Member r10 = PropertyTools.getFieldOrAccessor(r8, r24);
    L39:
        if (r10 == null) goto L45;
        if (r9 == false) goto L45;
        if ((r10.getModifiers() & 8) != 0) goto L45;
        r10 = null;
    L45:
        if (r10 == null) goto L50;
        if (GlobalListenerFactory.hasGetListeners() == false) goto L50;
        this.mv.visitVarInsn(25, 1);
        this.mv.visitLdcInsn(r10.getName());
        this.mv.visitVarInsn(25, 3);
        this.mv.visitMethodInsn(184, NAMESPACE + "integration/GlobalListenerFactory", "notifyGetListeners", "(Ljava/lang/Object;Ljava/lang/String;L" + NAMESPACE + "integration/VariableResolverFactory;)V");
        GlobalListenerFactory.notifyGetListeners(r23, r10.getName(), this.variableFactory);
    L50:
        if (this.first == false) goto L76;
        if ("this".equals(r24) == false) goto L55;
        this.mv.visitVarInsn(25, 2);
        return this.thisRef;
    L55:
        VariableResolverFactory r12 = this.variableFactory;
        if (r12 != null) goto L58;
    L74:
        this.mv.visitVarInsn(25, 1);
        goto L76
    L58:
        if (r12.isResolveable(r24) == false) goto L74;
        if (this.variableFactory.isIndexedFactory() == true) goto L62;
    L190:
        loadVariableByName(r24);     // Catch: Exception -> L71
        return this.variableFactory.getVariableResolver(r24).getValue();
    L71:
        e = move-exception;
        throw new OptimizationFailure("critical error in JIT", e);
    L62:
        if (this.variableFactory.isTarget(r24) == false) goto L190;
        int r06 = this.variableFactory.variableIndexOf(r24);     // Catch: Exception -> L66
        loadVariableByIndex(r06);     // Catch: Exception -> L66
        return this.variableFactory.getIndexedVariableResolver(r06).getValue();
    L67:
        throw new OptimizationFailure(r24);
    L76:
        if ((r10 instanceof Field) == false) goto L80;
        return optimizeFieldMethodProperty(r23, r24, r8, r10);
    L80:
        if (r10 == null) goto L114;
        if (this.first == false) goto L198;
        this.mv.visitVarInsn(25, 1);
    L198:
        Object r07 = ((Method) r10).invoke(r23, EMPTYARG);     // Catch: IllegalArgumentException -> L87 IllegalAccessException -> L89
        if (this.returnType == r10.getDeclaringClass()) goto L91;
        this.mv.visitTypeInsn(192, Type.getInternalName(r10.getDeclaringClass()));     // Catch: IllegalArgumentException -> L87 IllegalAccessException -> L89
    L91:
        this.returnType = ((Method) r10).getReturnType();     // Catch: IllegalArgumentException -> L87 IllegalAccessException -> L89
        if (r10.getDeclaringClass().isInterface() == false) goto L94;
        this.mv.visitMethodInsn(185, Type.getInternalName(r10.getDeclaringClass()), r10.getName(), Type.getMethodDescriptor((Method) r10));     // Catch: IllegalArgumentException -> L87 IllegalAccessException -> L89
    L105:
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L109;
        if (r07 != null) goto L108;
        r07 = PropertyHandlerFactory.getNullPropertyHandler().getProperty(r10.getName(), r23, this.variableFactory);
    L108:
        writeOutNullHandler(r10, 0);
    L109:
        this.currType = ReflectionUtil.toNonPrimitiveType(this.returnType);
        return r07;
    L94:
        this.mv.visitMethodInsn(182, Type.getInternalName(r10.getDeclaringClass()), r10.getName(), Type.getMethodDescriptor((Method) r10));     // Catch: IllegalArgumentException -> L87 IllegalAccessException -> L89
    L89:
        e = move-exception;
        Method r08 = (Method) r10;
        Method r4 = ParseTools.determineActualTargetMethod(r08);
        if (r4 == null) goto L112;
        this.mv.visitTypeInsn(192, Type.getInternalName(r4.getDeclaringClass()));
        this.returnType = r4.getReturnType();
        this.mv.visitMethodInsn(185, Type.getInternalName(r4.getDeclaringClass()), r10.getName(), Type.getMethodDescriptor(r08));
        r07 = r4.invoke(r23, EMPTYARG);
        goto L105
    L112:
        throw new PropertyAccessException("could not access field: " + r8.getName() + "." + r24, this.expr, this.st, e, this.pCtx);
    L87:
        e = move-exception;
        if (r10.getDeclaringClass().equals(r23) == true) goto L192;
    L100:
        throw e;
    L192:
        throw new CompileException("name collision between innerclass: " + Class.forName(r10.getDeclaringClass().getName() + "$" + r24).getCanonicalName() + "; and bean accessor: " + r24 + " (" + r10.toString() + ")", this.expr, this.tkStart);     // Catch: ClassNotFoundException -> L188
    L114:
        if ((r23 instanceof Map) == false) goto L122;
        Map r42 = (Map) r23;
        if (r42.containsKey(r24) == false) goto L118;
    L119:
        this.mv.visitTypeInsn(192, "java/util/Map");
        this.mv.visitLdcInsn(r24);
        this.mv.visitMethodInsn(185, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
        return r42.get(r24);
    L118:
        if (this.nullSafe == true) goto L119;
    L122:
        if (this.first == false) goto L128;
        if ("this".equals(r24) == false) goto L128;
        this.mv.visitVarInsn(25, 2);
        return this.thisRef;
    L128:
        if ("length".equals(r24) == false) goto L134;
        if (r23.getClass().isArray() == false) goto L134;
        anyArrayCheck(r23.getClass());
        this.mv.visitInsn(190);
        wrapPrimitive(Integer.TYPE);
        return Integer.valueOf(Array.getLength(r23));
    L134:
        if (AbstractParser.LITERALS.containsKey(r24) == false) goto L139;
        Object r09 = AbstractParser.LITERALS.get(r24);
        if ((r09 instanceof Class) == false) goto L138;
        ldcClassConstant((Class) r09);
    L138:
        return r09;
    L139:
        Object r43 = tryStaticAccess();
        if (r43 != null) goto L142;
        if (r04 == false) goto L172;
        Class r010 = (Class) r23;
        Method[] r44 = r010.getMethods();
        int r6 = r44.length;
    L153:
        if (r5 >= r6) goto L196;
        Method r7 = r44[r5];
        if (r24.equals(r7.getName()) == true) goto L156;
        r5 = r5 + 1;
        goto L153
    L156:
        ParserContext r2 = this.pCtx;
        if (r2 == null) goto L164;
        if (r2.getParserConfiguration() == null) goto L164;
        if (this.pCtx.getParserConfiguration().isAllowNakedMethCall() == true) goto L165;
    L167:
        writeFunctionPointerStub(r010, r7);
        return r7;
    L165:
        this.mv.visitInsn(87);
        this.mv.visitMethodInsn(184, Type.getInternalName(r7.getDeclaringClass()), r7.getName(), Type.getMethodDescriptor(r7));
        this.returnType = r7.getReturnType();
        return r7.invoke(null, ParseTools.EMPTY_OBJ_ARR);
    L164:
        if (MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL == false) goto L167;
    L196:
        Class r011 = ParseTools.findClass(this.variableFactory, r010.getName() + "$" + r24, this.pCtx);     // Catch: ClassNotFoundException -> L189
        ldcClassConstant(r011);     // Catch: ClassNotFoundException -> L189
        return r011;
    L183:
        if (r23 != null) goto L187;
        throw new PropertyAccessException("unresolvable property or identifier: " + r24, this.expr, this.st, this.pCtx);
    L187:
        throw new PropertyAccessException("could not access: " + r24 + "; in class: " + r23.getClass().getName(), this.expr, this.st, this.pCtx);
    L172:
        ParserContext r012 = this.pCtx;
        if (r012 == null) goto L180;
        if (r012.getParserConfiguration() == null) goto L180;
        if (this.pCtx.getParserConfiguration().isAllowNakedMethCall() == false) goto L183;
    L182:
        return getMethod(r23, r24);
    L180:
        if (MVEL.COMPILER_OPT_ALLOW_NAKED_METH_CALL == false) goto L183;
    L142:
        if ((r43 instanceof Class) == false) goto L146;
        ldcClassConstant((Class) r43);
        return r43;
    L146:
        if ((r43 instanceof Method) == false) goto L150;
        Method r013 = (Method) r43;
        writeFunctionPointerStub(r013.getDeclaringClass(), r013);
        return r43;
    L150:
        return optimizeFieldMethodProperty(r23, r24, r8, (Field) r43);
    L38:
        r10 = null;
        goto L39
    L27:
        r9 = false;
        r8 = null;
        goto L29
    L19:
        if (MVEL.COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS == true) goto L21;
    L24:
        r8 = (Class) r23;
        r9 = true;
        goto L29
    L21:
        if ("class".equals(r24) == false) goto L24;
        ldcClassConstant((Class) r23);
        return r23;
    L14:
        if (r03.isPrimitive() == false) goto L16;
        wrapPrimitive(this.returnType);
        goto L16
    L9:
        if (this.pCtx.isStrongTyping() == true) goto L11;
        this.currType = null;
        goto L11
    L5:
        r02 = r0.getVarOrInputTypeOrNull(r24);
        goto L7
    }

    private Object getBeanPropertyAO(Object r2, String r3) {
        if (r2 == null) goto L8;
        if (PropertyHandlerFactory.hasPropertyHandler(r2.getClass()) == false) goto L8;
        return propHandlerByteCode(r3, r2, r2.getClass());
    L8:
        return getBeanProperty(r2, r3);
    }

    private Object getCollectionProperty(Object r10, String r11) {
        if (r11.trim().length() <= 0) goto L5;
        r10 = getBeanProperty(r10, r11);
        this.first = false;
    L5:
        this.currType = null;
        int r0 = this.cursor + 1;
        this.cursor = r0;
        skipWhitespace();
        if (this.cursor == this.end) goto L74;
        boolean r3 = scanTo(']');
        char[] r4 = this.expr;
        if (r3 == true) goto L72;
        String r32 = new String(r4, r0, this.cursor - r0);
        if (r10 != null) goto L13;
        return null;
    L13:
        if (this.first == false) goto L15;
        this.mv.visitVarInsn(25, 1);
    L15:
        ExecutableStatement r02 = (ExecutableStatement) ParseTools.subCompileExpression(r32.toCharArray(), this.pCtx);
        Object r33 = r02.getValue(this.ctx, this.variableFactory);
        this.cursor++;
        if ((r10 instanceof Map) == false) goto L24;
        this.mv.visitTypeInsn(192, "java/util/Map");
        Class r112 = writeLiteralOrSubexpression(r02);
        if (r112 != null) goto L20;
    L22:
        this.mv.visitMethodInsn(185, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
        return ((Map) r10).get(r33);
    L20:
        if (r112.isPrimitive() == false) goto L22;
        wrapPrimitive(r112);
        goto L22
    L24:
        boolean r2 = r10 instanceof List;
        Class r7 = Integer.TYPE;
        if (r2 == false) goto L28;
        this.mv.visitTypeInsn(192, "java/util/List");
        writeLiteralOrSubexpression(r02, r7, Object.class);
        this.mv.visitMethodInsn(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
        return ((List) r10).get(((Integer) DataConversion.convert(r33, Integer.class)).intValue());
    L28:
        boolean r22 = r10.getClass().isArray();
        Class r42 = Character.TYPE;
        if (r22 == false) goto L59;
        this.mv.visitTypeInsn(192, Type.getDescriptor(r10.getClass()));
        writeLiteralOrSubexpression(r02, r7, r33.getClass());
        Class r113 = ParseTools.getBaseComponentType(r10.getClass());
        if (r113.isPrimitive() == false) goto L55;
        if (r113 != r7) goto L34;
        this.mv.visitInsn(46);
    L54:
        wrapPrimitive(r113);
    L57:
        return Array.get(r10, ((Integer) DataConversion.convert(r33, Integer.class)).intValue());
    L34:
        if (r113 != r42) goto L37;
        this.mv.visitInsn(52);
        goto L54
    L37:
        if (r113 != Boolean.TYPE) goto L40;
        this.mv.visitInsn(51);
        goto L54
    L40:
        if (r113 != Double.TYPE) goto L43;
        this.mv.visitInsn(49);
        goto L54
    L43:
        if (r113 != Float.TYPE) goto L46;
        this.mv.visitInsn(48);
        goto L54
    L46:
        if (r113 != Short.TYPE) goto L49;
        this.mv.visitInsn(53);
        goto L54
    L49:
        if (r113 != Long.TYPE) goto L52;
        this.mv.visitInsn(47);
        goto L54
    L52:
        if (r113 != Byte.TYPE) goto L54;
        this.mv.visitInsn(51);
        goto L54
    L55:
        this.mv.visitInsn(50);
        goto L57
    L59:
        if ((r10 instanceof CharSequence) == false) goto L66;
        this.mv.visitTypeInsn(192, "java/lang/CharSequence");
        if ((r33 instanceof Integer) == false) goto L64;
        Integer r34 = (Integer) r33;
        intPush(r34.intValue());
        this.mv.visitMethodInsn(185, "java/lang/CharSequence", "charAt", "(I)C");
        wrapPrimitive(r42);
        return Character.valueOf(((CharSequence) r10).charAt(r34.intValue()));
    L64:
        writeLiteralOrSubexpression(r02, Integer.class);
        unwrapPrimitive(r7);
        this.mv.visitMethodInsn(185, "java/lang/CharSequence", "charAt", "(I)C");
        wrapPrimitive(r42);
        return Character.valueOf(((CharSequence) r10).charAt(((Integer) DataConversion.convert(r33, Integer.class)).intValue()));
    L66:
        TypeDescriptor r03 = new TypeDescriptor(this.expr, this.start, this.length, 0);
        if (r03.isArray() == true) goto L77;
    L70:
        Sign$$ExternalSyntheticBUOutline0.m("illegal use of []: unknown type: " + r10.getClass().getName(), this.expr, this.st);
        return null;
    L77:
        Class r04 = TypeDescriptor.getClassReference((Class) r10, r03, this.variableFactory, this.pCtx);     // Catch: Exception -> L76
        ldcClassConstant(r04);     // Catch: Exception -> L76
        return r04;
    L72:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", r4, this.st);
        return null;
    L74:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", this.expr, this.st);
        return null;
    }

    private Object getCollectionPropertyAO(Object r13, String r14) {
        if (r14.length() <= 0) goto L5;
        r13 = getBeanProperty(r13, r14);
        this.first = false;
    L5:
        this.currType = null;
        int r0 = this.cursor + 1;
        this.cursor = r0;
        skipWhitespace();
        if (this.cursor == this.end) goto L99;
        boolean r3 = scanTo(']');
        char[] r4 = this.expr;
        if (r3 == true) goto L97;
        String r32 = new String(r4, r0, this.cursor - r0);
        if (r13 != null) goto L12;
        return null;
    L12:
        ExecutableStatement r02 = (ExecutableStatement) ParseTools.subCompileExpression(r32.toCharArray());
        Object r42 = r02.getValue(this.ctx, this.variableFactory);
        this.cursor++;
        if ((r13 instanceof Map) == true) goto L15;
        boolean r5 = r13 instanceof List;
        Class r10 = Integer.TYPE;
        if (r5 == true) goto L31;
        boolean r52 = r13.getClass().isArray();
        Class r6 = Character.TYPE;
        if (r52 == false) goto L77;
        if (PropertyHandlerFactory.hasPropertyHandler(Array.class) == false) goto L46;
        return propHandlerByteCode(r32, r13, Array.class);
    L46:
        if (this.first == false) goto L48;
        this.mv.visitVarInsn(25, 1);
    L48:
        this.mv.visitTypeInsn(192, Type.getDescriptor(r13.getClass()));
        writeLiteralOrSubexpression(r02, r10, r42.getClass());
        Class r142 = ParseTools.getBaseComponentType(r13.getClass());
        if (r142.isPrimitive() == false) goto L73;
        if (r142 != r10) goto L52;
        this.mv.visitInsn(46);
    L72:
        wrapPrimitive(r142);
    L75:
        return Array.get(r13, ((Integer) DataConversion.convert(r42, Integer.class)).intValue());
    L52:
        if (r142 != r6) goto L55;
        this.mv.visitInsn(52);
        goto L72
    L55:
        if (r142 != Boolean.TYPE) goto L58;
        this.mv.visitInsn(51);
        goto L72
    L58:
        if (r142 != Double.TYPE) goto L61;
        this.mv.visitInsn(49);
        goto L72
    L61:
        if (r142 != Float.TYPE) goto L64;
        this.mv.visitInsn(48);
        goto L72
    L64:
        if (r142 != Short.TYPE) goto L67;
        this.mv.visitInsn(53);
        goto L72
    L67:
        if (r142 != Long.TYPE) goto L70;
        this.mv.visitInsn(47);
        goto L72
    L70:
        if (r142 != Byte.TYPE) goto L72;
        this.mv.visitInsn(51);
        goto L72
    L73:
        this.mv.visitInsn(50);
        goto L75
    L77:
        if ((r13 instanceof CharSequence) == true) goto L79;
        char[] r2 = this.expr;
        int r33 = this.start;
        TypeDescriptor r03 = new TypeDescriptor(r2, r33, this.end - r33, 0);
        if (r03.isArray() == true) goto L102;
    L95:
        Sign$$ExternalSyntheticBUOutline0.m("illegal use of []: unknown type: " + r13.getClass().getName(), this.expr, this.st);
        return null;
    L102:
        Class r04 = TypeDescriptor.getClassReference((Class) r13, r03, this.variableFactory, this.pCtx);     // Catch: Exception -> L101
        ldcClassConstant(r04);     // Catch: Exception -> L101
        return r04;
    L79:
        if (PropertyHandlerFactory.hasPropertyHandler(CharSequence.class) == false) goto L83;
        return propHandlerByteCode(r32, r13, CharSequence.class);
    L83:
        if (this.first == false) goto L85;
        this.mv.visitVarInsn(25, 1);
    L85:
        this.mv.visitTypeInsn(192, "java/lang/CharSequence");
        if ((r42 instanceof Integer) == false) goto L89;
        Integer r43 = (Integer) r42;
        intPush(r43.intValue());
        this.mv.visitMethodInsn(185, "java/lang/CharSequence", "charAt", "(I)C");
        wrapPrimitive(r6);
        return Character.valueOf(((CharSequence) r13).charAt(r43.intValue()));
    L89:
        writeLiteralOrSubexpression(r02, Integer.class);
        unwrapPrimitive(r10);
        this.mv.visitMethodInsn(185, "java/lang/CharSequence", "charAt", "(I)C");
        wrapPrimitive(r6);
        return Character.valueOf(((CharSequence) r13).charAt(((Integer) DataConversion.convert(r42, Integer.class)).intValue()));
    L31:
        if (PropertyHandlerFactory.hasPropertyHandler(List.class) == false) goto L35;
        return propHandlerByteCode(r32, r13, List.class);
    L35:
        if (this.first == false) goto L37;
        this.mv.visitVarInsn(25, 1);
    L37:
        this.mv.visitTypeInsn(192, "java/util/List");
        writeLiteralOrSubexpression(r02, r10);
        this.mv.visitMethodInsn(185, "java/util/List", "get", "(I)Ljava/lang/Object;");
        return ((List) r13).get(((Integer) DataConversion.convert(r42, Integer.class)).intValue());
    L15:
        if (PropertyHandlerFactory.hasPropertyHandler(Map.class) == false) goto L19;
        return propHandlerByteCode(r32, r13, Map.class);
    L19:
        if (this.first == false) goto L21;
        this.mv.visitVarInsn(25, 1);
    L21:
        this.mv.visitTypeInsn(192, "java/util/Map");
        Class r143 = writeLiteralOrSubexpression(r02);
        if (r143 != null) goto L24;
    L26:
        this.mv.visitMethodInsn(185, "java/util/Map", "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
        return ((Map) r13).get(r42);
    L24:
        if (r143.isPrimitive() == false) goto L26;
        wrapPrimitive(r143);
        goto L26
    L97:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", r4, this.st);
        return null;
    L99:
        Sign$$ExternalSyntheticBUOutline0.m("unterminated '['", this.expr, this.st);
        return null;
    }

    private ContextClassLoader getContextClassLoader() {
        if (this.pCtx != null) goto L7;
        return null;
    L7:
        return new ContextClassLoader(this.pCtx.getClassLoader());
    }

    public static MVELClassLoader getMVELClassLoader() {
        return classLoader;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [org.mvel2.compiler.ExecutableStatement[]] */
    /* JADX WARN: Type inference failed for: r25v0, types: [org.mvel2.compiler.AbstractParser, org.mvel2.optimizers.AbstractOptimizer, org.mvel2.optimizers.impl.asm.ASMAccessorOptimizer] */
    /* JADX WARN: Type inference failed for: r2v98, types: [org.mvel2.compiler.ExecutableStatement[]] */
    /* JADX WARN: Type inference failed for: r5v13, types: [org.mvel2.compiler.ExecutableStatement] */
    /* JADX WARN: Type inference failed for: r5v16, types: [org.mvel2.compiler.ExecutableStatement] */
    /* JADX WARN: Type inference failed for: r8v11, types: [org.mvel2.compiler.ExecutableStatement] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v2, types: [org.mvel2.compiler.ExecutableStatement] */
    /* JADX WARN: Type inference failed for: r8v4, types: [org.mvel2.compiler.ExecutableStatement] */
    private Object getMethod(Object r26, String r27) {
        Object r1 = r26;
        String r2 = r27;
        int r3 = this.cursor;
        if (r3 == this.end) goto L9;
        char[] r4 = this.expr;
        if (r4[r3] != '(') goto L9;
        int r42 = ParseTools.balancedCapture(r4, r3, '(');
        this.cursor = r42;
        if ((r42 - r3) <= 1) goto L9;
        String r6 = new String(this.expr, r3 + 1, (r42 - r3) - 1);
    L10:
        this.cursor++;
        Class<?> r7 = Object.class;
        if (r6.length() != 0) goto L13;
        Object[] r43 = ParseTools.EMPTY_OBJ_ARR;
        Object[] r13 = r43;
        Class[] r18 = ParseTools.EMPTY_CLS_ARR;
        List<char[]> r8 = null;
        ExecutableLiteral[] r11 = null;
    L46:
        if (this.first == false) goto L84;
        VariableResolverFactory r5 = this.variableFactory;
        if (r5 == null) goto L84;
        if (r5.isResolveable(r2) == false) goto L84;
        Object r52 = this.variableFactory.getVariableResolver(r2).getValue();
        if ((r52 instanceof Method) == false) goto L56;
        Method r53 = (Method) r52;
        Class<?> r12 = r53.getDeclaringClass();
        r2 = r53.getName();
    L58:
        this.first = false;
        Class<?> r14 = r12;
    L59:
        String r19 = r2;
        Class<?> r22 = this.currType;
        if (r22 == null) goto L92;
        Class<?> r20 = r22;
        Class r23 = null;
        boolean r232 = false;
    L97:
        this.currType = r23;
        Method r24 = ParseTools.getBestCandidate(r18, r19, r20, r20.getMethods(), false, r232);
        Class[] r9 = r18;
        Class<?> r54 = r20;
        if (r24 == null) goto L100;
        Class<?>[] r182 = r24.getParameterTypes();
    L101:
        if (r24 != null) goto L107;
        if (r232 == false) goto L107;
        String r242 = "getValue";
        r24 = ParseTools.getBestCandidate(r9, r19, r54, Class.class.getMethods(), false);
        if (r24 == null) goto L106;
        r182 = r24.getParameterTypes();
    L106:
        Class<?>[] r142 = r182;
        Method r262 = r24;
        if (r24 == null) goto L111;
    L118:
        Method r25 = r262;
    L119:
        if (r11 == null) goto L151;
        if (r25 == null) goto L151;
        if (r25.isVarArgs() == false) goto L151;
        Method r263 = r25;
        if (r11.length != r142.length) goto L129;
        boolean r28 = r11[r11.length - 1] instanceof ExecutableAccessor;
        ExecutableLiteral[] r112 = r11;
        if (r28 == false) goto L129;
    L152:
        int r29 = this.compiledInputs.size();
        if (r112 == null) goto L161;
        int r62 = r112.length;
        int r82 = 0;
    L155:
        if (r82 >= r62) goto L161;
        ExecutableLiteral r92 = r112[r82];
        int r272 = r29;
        if ((r92 instanceof ExecutableLiteral) == true) goto L160;
        this.compiledInputs.add(r92);
    L160:
        r82 = r82 + 1;
        r29 = r272;
    L161:
        int r273 = r29;
        if (this.first == false) goto L164;
        this.mv.visitVarInsn(25, 1);
    L164:
        Class<?> r210 = Integer.TYPE;
        if (r263 != null) goto L189;
        StringAppender r44 = new StringAppender();
        if (r142 == null) goto L180;
        int r93 = 0;
    L170:
        if (r93 >= r13.length) goto L180;
        Class<?> r63 = r142[r93];
        if (r63 == null) goto L174;
        String r64 = r63.getClass().getName();
    L175:
        r44.append(r64);
        if (r93 >= (r13.length - 1)) goto L178;
        r44.append(", ");
    L178:
        r93 = r93 + 1;
        goto L170
    L174:
        r64 = null;
    L180:
        if ("size".equals(r19) == true) goto L182;
    L187:
        Sign$$ExternalSyntheticBUOutline0.m("unable to resolve method: " + r54.getName() + "." + r19 + "(" + r44.toString() + ") [arglength=" + r13.length + "]", this.expr, r3);
        return null;
    L182:
        if (r13.length != 0) goto L187;
        if (r54.isArray() == false) goto L187;
        anyArrayCheck(r54);
        this.mv.visitInsn(190);
        wrapPrimitive(r210);
        return Integer.valueOf(Array.getLength(r14));
    L189:
        Method r32 = ParseTools.getWidenedTarget(r263);
        if (r112 == null) goto L203;
        int r55 = 0;
    L193:
        if (r55 >= r112.length) goto L207;
        ExecutableLiteral r65 = r112[r55];
        if (r65.getKnownIngressType() != null) goto L198;
        r65.setKnownIngressType(r142[r55]);
        r65.computeTypeConversionRule();
    L198:
        if (r65.isConvertableIngressEgress() == true) goto L202;
        if (r55 >= r13.length) goto L202;
        r13[r55] = DataConversion.convert(r13[r55], Varargs.paramTypeVarArgsSafe(r142, r55, r32.isVarArgs()));
    L202:
        r55 = r55 + 1;
    L207:
        Class<?> r56 = r32.getDeclaringClass();
        if (r32.getParameterTypes().length != 0) goto L218;
        int r211 = r32.getModifiers() & 8;
        MethodVisitor r45 = this.mv;
        if (r211 == 0) goto L212;
        r45.visitMethodInsn(184, Type.getInternalName(r56), r32.getName(), Type.getMethodDescriptor(r32));
    L216:
        this.returnType = r32.getReturnType();
        this.stacksize++;
        int r46 = 1;
    L313:
        Object r212 = r32.invoke(r14, Varargs.normalizeArgsForVarArgs(r142, r13, r32.isVarArgs()));
        if (PropertyHandlerFactory.hasNullMethodHandler() == false) goto L318;
        writeOutNullHandler(r32, r46);
        if (r212 != null) goto L318;
        r212 = PropertyHandlerFactory.getNullMethodHandler().getProperty(r32.getName(), r14, this.variableFactory);
    L318:
        this.currType = ReflectionUtil.toNonPrimitiveType(r32.getReturnType());
        return r212;
    L212:
        r45.visitTypeInsn(192, Type.getInternalName(r56));
        boolean r213 = r56.isInterface();
        MethodVisitor r47 = this.mv;
        if (r213 == false) goto L215;
        r47.visitMethodInsn(185, Type.getInternalName(r56), r32.getName(), Type.getMethodDescriptor(r32));
        goto L216
    L215:
        r47.visitMethodInsn(182, Type.getInternalName(r56), r32.getName(), Type.getMethodDescriptor(r32));
        goto L216
    L218:
        if ((r32.getModifiers() & 8) != 0) goto L220;
        this.mv.visitTypeInsn(192, Type.getInternalName(r56));
    L220:
        int r66 = r273;
        int r94 = 0;
    L221:
        if (r112 == null) goto L297;
        if (r94 >= r112.length) goto L297;
        ExecutableLiteral r15 = r112[r94];
        if ((r15 instanceof ExecutableLiteral) == false) goto L272;
        ExecutableLiteral r152 = r15;
        if (r152.getLiteral() != null) goto L231;
        this.mv.visitInsn(1);
    L229:
        Class<?> r274 = r210;
        Object[] r16 = r43;
    L268:
        Class<?> r183 = r56;
        int r202 = r66;
        Class<?> r192 = r7;
        String r153 = r242;
    L296:
        r94 = r94 + 1;
        r210 = r274;
        r242 = r153;
        r43 = r16;
        r56 = r183;
        r7 = r192;
        r66 = r202;
        goto L221
    L231:
        if (r142[r94] == r210) goto L233;
    L235:
        Class<?> r83 = r142[r94];
        r274 = r210;
        if (r83 != r210) goto L240;
        Object r214 = r43[r94];
        r16 = r43;
        if ((r214 instanceof Integer) == false) goto L242;
        intPush(((Integer) r214).intValue());
    L242:
        if (r83 != Boolean.TYPE) goto L248;
        boolean r215 = ((Boolean) DataConversion.convert(r152.getLiteral(), Boolean.class)).booleanValue();
        MethodVisitor r48 = this.mv;
        if (r215 == false) goto L246;
        int r216 = 4;
    L247:
        r48.visitInsn(r216);
        goto L268
    L246:
        r216 = 3;
        goto L247
    L248:
        Object r217 = r152.getLiteral();
        Class<?> r49 = r142[r94];
        if (r49 != r7) goto L261;
        if (ParseTools.isPrimitiveWrapper(r217.getClass()) == false) goto L258;
        if (r217.getClass() != Integer.class) goto L255;
        intPush(((Integer) r217).intValue());
    L256:
        wrapPrimitive(r217.getClass());
        goto L268
    L255:
        this.mv.visitLdcInsn(r217);
        goto L256
    L258:
        if ((r217 instanceof String) == false) goto L268;
        this.mv.visitLdcInsn(r217);
        checkcast(r7);
        goto L268
    L261:
        if (DataConversion.canConvert(r49, r217.getClass()) == false) goto L271;
        Object r410 = DataConversion.convert(r217, r142[r94]);
        if ((r410 instanceof Class) == false) goto L265;
        ldcClassConstant((Class) r410);
        goto L268
    L265:
        this.mv.visitLdcInsn(r410);
        if (ParseTools.isPrimitiveWrapper(r142[r94]) == false) goto L268;
        wrapPrimitive(r217.getClass());
        goto L268
    L271:
        throw new OptimizationNotSupported();
    L240:
        r16 = r43;
        goto L242
    L233:
        if (r152.intOptimized() == false) goto L235;
        intPush(r152.getInteger32());
        goto L229
    L272:
        r274 = r210;
        r16 = r43;
        this.mv.visitVarInsn(25, 0);
        r183 = r56;
        this.mv.visitFieldInsn(180, this.className, "p" + r66, "L" + NAMESPACE + "compiler/ExecutableStatement;");
        int r67 = r66 + 1;
        this.mv.visitVarInsn(25, 2);
        this.mv.visitVarInsn(25, 3);
        r153 = r242;
        this.mv.visitMethodInsn(185, Type.getInternalName(ExecutableStatement.class), r153, "(Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;)Ljava/lang/Object;");
        if (r142[r94].isPrimitive() == false) goto L284;
        Object r193 = r16[r94];
        r202 = r67;
        if (r193 == null) goto L282;
        Class<?> r68 = r142[r94];
        if (r68 != String.class) goto L279;
    L281:
        r192 = r7;
    L283:
        unwrapPrimitive(r142[r94]);
        goto L296
    L279:
        if (r68.isAssignableFrom(r193.getClass()) == true) goto L281;
    L282:
        ldcClassConstant(getWrapperClass(r142[r94]));
        MethodVisitor r218 = this.mv;
        StringBuilder r69 = new StringBuilder();
        r192 = r7;
        r69.append(NAMESPACE);
        r69.append("DataConversion");
        r218.visitMethodInsn(184, r69.toString(), "convert", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
        goto L283
    L284:
        r202 = r67;
        r192 = r7;
        Object r610 = r16[r94];
        if (r610 == null) goto L295;
        Class<?> r72 = r142[r94];
        if (r72 != String.class) goto L289;
    L291:
        Class<?> r411 = r142[r94];
        MethodVisitor r57 = this.mv;
        if (r411 != String.class) goto L294;
        r57.visitVarInsn(58, 4);
        Label r412 = new Label();
        this.mv.visitVarInsn(25, 4);
        this.mv.visitJumpInsn(199, r412);
        this.mv.visitInsn(1);
        Label r58 = new Label();
        this.mv.visitJumpInsn(167, r58);
        this.mv.visitLabel(r412);
        this.mv.visitVarInsn(25, 4);
        this.mv.visitMethodInsn(184, "java/lang/String", "valueOf", "(Ljava/lang/Object;)Ljava/lang/String;");
        this.mv.visitLabel(r58);
        goto L296
    L294:
        r57.visitTypeInsn(192, Type.getInternalName(r411));
        goto L296
    L289:
        if (r72.isAssignableFrom(r610.getClass()) == true) goto L291;
    L295:
        ldcClassConstant(r142[r94]);
        this.mv.visitMethodInsn(184, NAMESPACE + "DataConversion", "convert", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
        this.mv.visitTypeInsn(192, Type.getInternalName(r142[r94]));
    L297:
        Class<?> r184 = r56;
        if (r32.isVarArgs() == false) goto L306;
        if (r112 == null) goto L304;
        if (r112.length != (r142.length - 1)) goto L306;
    L304:
        createArray(ParseTools.getBaseComponentType(r142[r142.length - 1]), 0);
    L306:
        if ((r32.getModifiers() & 8) == 0) goto L308;
        this.mv.visitMethodInsn(184, Type.getInternalName(r184), r32.getName(), Type.getMethodDescriptor(r32));
    L312:
        this.returnType = r32.getReturnType();
        r46 = 1;
        this.stacksize++;
        goto L313
    L308:
        boolean r219 = r184.isInterface();
        MethodVisitor r413 = this.mv;
        if (r219 == false) goto L311;
        r413.visitMethodInsn(185, Type.getInternalName(r184), r32.getName(), Type.getMethodDescriptor(r32));
        goto L312
    L311:
        r413.visitMethodInsn(182, Type.getInternalName(r184), r32.getName(), Type.getMethodDescriptor(r32));
        goto L312
    L203:
        int r59 = 0;
    L205:
        if (r59 >= r13.length) goto L207;
        r13[r59] = DataConversion.convert(r13[r59], Varargs.paramTypeVarArgsSafe(r142, r59, r32.isVarArgs()));
        r59 = r59 + 1;
    L129:
        ?? r220 = new ExecutableStatement[r142.length];
        int r95 = r142.length - 1;
        int r221 = 0;
    L130:
        if (r221 >= r95) goto L132;
        r220[r221] = r11[r221];
        r221 = r221 + 1;
        goto L130
    L132:
        String r222 = r142[r142.length - 1].getComponentType().getName();
        if ("null".equals(r6) == true) goto L143;
        StringBuilder r611 = new StringBuilder("new ");
        r611.append(r222);
        r611.append("[] {");
        int r223 = r95;
    L137:
        if (r223 >= r8.size()) goto L142;
        r611.append(r8.get(r223));
        if (r223 >= (r8.size() - 1)) goto L141;
        r611.append(",");
    L141:
        r223 = r223 + 1;
        goto L137
    L142:
        r611.append("}");
        r6 = r611.toString();
    L143:
        r220[r95] = (ExecutableStatement) ParseTools.subCompileExpression(r6.toCharArray(), this.pCtx);
        if (r43.length != (r142.length - 1)) goto L150;
        Object[] r224 = new Object[r142.length];
        int r612 = 0;
    L147:
        if (r612 >= r43.length) goto L149;
        r224[r612] = r43[r612];
        r612 = r612 + 1;
        goto L147
    L149:
        r224[r142.length - 1] = Array.newInstance(r142[r142.length - 1].getComponentType(), 0);
        r43 = r224;
    L150:
        r112 = r220;
    L151:
        r263 = r25;
        r112 = r11;
        goto L152
    L111:
        if (r54 == r14.getClass()) goto L118;
        if ((r14 instanceof Class) == true) goto L118;
        Class<?> r203 = r14.getClass();
        r25 = ParseTools.getBestCandidate(r9, r19, r203, r203.getMethods(), false, r232);
        if (r25 == null) goto L117;
        r142 = r25.getParameterTypes();
    L117:
        r54 = r203;
    L107:
        r242 = "getValue";
        goto L106
    L100:
        r182 = null;
        goto L101
    L92:
        boolean r225 = r14 instanceof Class;
        if (r225 == false) goto L96;
        Class<?> r510 = r14;
    L95:
        r232 = r225;
        r20 = r510;
        r23 = null;
        goto L97
    L96:
        r510 = r14.getClass();
        goto L95
    L56:
        if ((r52 instanceof MethodStub) == false) goto L61;
        MethodStub r511 = (MethodStub) r52;
        r12 = r511.getClassReference();
        r2 = r511.getMethodName();
        goto L58
    L61:
        if ((r52 instanceof FunctionInstance) == false) goto L78;
        if (r11 != null) goto L64;
    L69:
        this.mv.visitInsn(1);
        this.mv.visitTypeInsn(192, "[Ljava/lang/Object;");
        this.mv.visitVarInsn(58, 4);
    L71:
        if (this.variableFactory.isIndexedFactory() == true) goto L73;
    L75:
        loadVariableByName(r2);
    L76:
        checkcast(FunctionInstance.class);
        this.mv.visitVarInsn(25, 1);
        this.mv.visitVarInsn(25, 2);
        this.mv.visitVarInsn(25, 3);
        this.mv.visitVarInsn(25, 4);
        this.mv.visitMethodInsn(182, Type.getInternalName(FunctionInstance.class), "call", "(Ljava/lang/Object;Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;[Ljava/lang/Object;)Ljava/lang/Object;");
        return ((FunctionInstance) r52).call(r1, this.thisRef, this.variableFactory, r13);
    L73:
        if (this.variableFactory.isTarget(r2) == false) goto L75;
        loadVariableByIndex(this.variableFactory.variableIndexOf(r2));
        goto L76
    L64:
        if (r11.length == 0) goto L69;
        this.compiledInputs.addAll(Arrays.asList(r11));
        intPush(r11.length);
        this.mv.visitTypeInsn(189, "java/lang/Object");
        int r414 = 4;
        this.mv.visitVarInsn(58, 4);
        int r96 = 0;
    L67:
        if (r96 >= r11.length) goto L71;
        this.mv.visitVarInsn(25, r414);
        intPush(r96);
        loadField(r96);
        this.mv.visitVarInsn(25, 1);
        this.mv.visitIntInsn(25, 3);
        this.mv.visitMethodInsn(185, NAMESPACE + "compiler/ExecutableStatement", "getValue", "(Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;)Ljava/lang/Object;");
        this.mv.visitInsn(83);
        r96 = r96 + 1;
        r414 = 4;
        goto L67
    L78:
        StringBuilder r33 = new StringBuilder("attempt to optimize a method call for a reference that does not point to a method: ");
        r33.append(r2);
        r33.append(" (reference is type: ");
        if (r1 == null) goto L81;
        String r84 = r1.getClass().getName();
    L82:
        r33.append(r84);
        r33.append(")");
        throw new OptimizationFailure(r33.toString());
    L81:
        r84 = null;
    L84:
        Class r512 = this.returnType;
        r14 = r1;
        if (r512 == null) goto L59;
        r14 = r1;
        if (r512.isPrimitive() == false) goto L59;
        wrapPrimitive(this.returnType);
        r14 = r1;
        goto L59
    L13:
        List<char[]> r415 = ParseTools.parseParameterList(r6.toCharArray(), 0, -1);
        int r10 = r415.size();
        ?? r113 = new ExecutableStatement[r10];
        int r122 = r415.size();
        r13 = new Object[r122];
        Class[] r143 = new Class[r415.size()];
        Object[] r102 = new Object[r10];
        int r154 = 0;
    L15:
        if (r154 >= r415.size()) goto L21;
        ExecutableStatement r513 = (ExecutableStatement) ParseTools.subCompileExpression(r415.get(r154), this.pCtx);
        r113[r154] = r513;
        Object r85 = this.thisRef;
        Object r514 = r513.getValue(r85, r85, this.variableFactory);
        r13[r154] = r514;
        r102[r154] = r514;
        if (r113[r154].isExplicitCast() == false) goto L19;
        r143[r154] = r113[r154].getKnownEgressType();
    L19:
        r154 = r154 + 1;
        goto L15
    L21:
        if (this.pCtx.isStrictTypeEnforcement() == false) goto L30;
        int r515 = 0;
    L23:
        if (r515 >= r122) goto L44;
        r143[r515] = r113[r515].getKnownEgressType();
        ?? r86 = r113[r515];
        if ((r86 instanceof ExecutableLiteral) == false) goto L29;
        if (((ExecutableLiteral) r86).getLiteral() != null) goto L29;
        r143[r515] = NullType.class;
    L29:
        r515 = r515 + 1;
    L44:
        r8 = r415;
        r43 = r102;
        r18 = r143;
        r11 = r113;
        goto L46
    L30:
        int r516 = 0;
    L31:
        if (r516 >= r122) goto L44;
        if (r143[r516] != null) goto L43;
        if (r113[r516].getKnownEgressType() != r7) goto L42;
        Object r87 = r13[r516];
        if (r87 != null) goto L40;
        Class<?> r88 = null;
    L41:
        r143[r516] = r88;
        goto L43
    L40:
        r88 = r87.getClass();
        goto L41
    L42:
        r143[r516] = r113[r516].getKnownEgressType();
    L43:
        r516 = r516 + 1;
    L9:
        r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L10
    }

    private Object getWithProperty(Object r13) {
        if (this.first == false) goto L5;
        this.mv.visitVarInsn(25, 1);
        this.first = false;
    L5:
        String r9 = new String(this.expr, 0, this.cursor - 1).trim();
        int r0 = this.cursor;
        int r7 = r0 + 1;
        this.cursor = ParseTools.balancedCaptureWithLineAccounting(this.expr, r0, this.end, '{', this.pCtx);
        if (r13 == null) goto L8;
        Class<?> r02 = r13.getClass();
    L9:
        this.returnType = r02;
        char[] r6 = this.expr;
        int r03 = this.cursor;
        this.cursor = r03 + 1;
        WithNode.ParmValuePair[] r04 = WithNode.compileWithExpressions(r6, r7, r03 - r7, r9, this.ingressType, this.pCtx);
        int r4 = r04.length;
        int r5 = 0;
    L10:
        if (r5 >= r4) goto L16;
        WithNode.ParmValuePair r62 = r04[r5];
        this.mv.visitInsn(89);
        this.mv.visitVarInsn(58, this.compileDepth + 5);
        r62.eval(r13, this.variableFactory);
        if (r62.getSetExpression() != null) goto L14;
        addSubstatement(r62.getStatement());
    L15:
        r5 = r5 + 1;
        goto L10
    L14:
        this.compiledInputs.add((ExecutableStatement) r62.getSetExpression());
        this.mv.visitVarInsn(25, 0);
        this.mv.visitFieldInsn(180, this.className, "p" + (this.compiledInputs.size() - 1), "L" + NAMESPACE + "compiler/ExecutableStatement;");
        this.mv.visitVarInsn(25, this.compileDepth + 5);
        this.mv.visitVarInsn(25, 2);
        this.mv.visitVarInsn(25, 3);
        addSubstatement(r62.getStatement());
        this.mv.visitMethodInsn(185, NAMESPACE + "compiler/ExecutableStatement", "setValue", "(Ljava/lang/Object;Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;Ljava/lang/Object;)Ljava/lang/Object;");
        this.mv.visitInsn(87);
        goto L15
    L16:
        return r13;
    L8:
        r02 = null;
        goto L9
    }

    private Class getWrapperClass(Class r1) {
        if (r1 != Boolean.TYPE) goto L7;
        return Boolean.class;
    L7:
        if (r1 != Integer.TYPE) goto L11;
        return Integer.class;
    L11:
        if (r1 != Float.TYPE) goto L15;
        return Float.class;
    L15:
        if (r1 != Double.TYPE) goto L19;
        return Double.class;
    L19:
        if (r1 != Short.TYPE) goto L23;
        return Short.class;
    L23:
        if (r1 != Long.TYPE) goto L27;
        return Long.class;
    L27:
        if (r1 != Byte.TYPE) goto L31;
        return Byte.class;
    L31:
        if (r1 != Character.TYPE) goto L34;
        return Character.class;
    L34:
        return r1;
    }

    private void intPush(int r6) {
        if (r6 < 0) goto L28;
        if (r6 >= 6) goto L28;
        if (r6 != 0) goto L8;
        this.mv.visitInsn(3);
        return;
    L8:
        if (r6 != 1) goto L10;
        this.mv.visitInsn(4);
        return;
    L10:
        if (r6 == 2) goto L21;
        if (r6 == 3) goto L19;
        if (r6 == 4) goto L17;
        if (r6 == 5) goto L15;
        return;
    L15:
        this.mv.visitInsn(8);
        return;
    L17:
        this.mv.visitInsn(7);
        return;
    L19:
        this.mv.visitInsn(6);
        return;
    L21:
        this.mv.visitInsn(5);
        return;
    L28:
        if (r6 > (-127)) goto L30;
    L33:
        MethodVisitor r5 = this.mv;
        if (r6 <= 32767) goto L37;
        r5.visitLdcInsn(Integer.valueOf(r6));
        return;
    L37:
        r5.visitIntInsn(17, r6);
        return;
    L30:
        if (r6 >= 128) goto L33;
        this.mv.visitIntInsn(16, r6);
    }

    private void ldcClassConstant(Class r7) {
        int r0 = OPCODES_VERSION;
        MethodVisitor r1 = this.mv;
        if (r0 != 48) goto L6;
        r1.visitLdcInsn(r7.getName());
        this.mv.visitMethodInsn(184, "java/lang/Class", "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
        Label r72 = new Label();
        this.mv.visitJumpInsn(167, r72);
        this.mv.visitTypeInsn(187, "java/lang/NoClassDefFoundError");
        this.mv.visitInsn(90);
        this.mv.visitInsn(95);
        this.mv.visitMethodInsn(182, "java/lang/Throwable", "getMessage", "()Ljava/lang/String;");
        this.mv.visitMethodInsn(183, "java/lang/NoClassDefFoundError", "<init>", "(Ljava/lang/String;)V");
        this.mv.visitInsn(191);
        this.mv.visitLabel(r72);
        return;
    L6:
        r1.visitLdcInsn(Type.getType(r7));
    }

    private Class loadClass(String r3, byte[] r4) {
        ContextClassLoader r2 = getContextClassLoader();
        if (r2 != null) goto L7;
        return classLoader.defineClassX(r3, r4, 0, r4.length);
    L7:
        return r2.defineClass(r3, r4);
    }

    private void loadField(int r4) {
        this.mv.visitVarInsn(25, 0);
        this.mv.visitFieldInsn(180, this.className, "p" + r4, "L" + NAMESPACE + "compiler/ExecutableStatement;");
    }

    private void loadVariableByIndex(int r6) {
        this.mv.visitVarInsn(25, 3);
        intPush(r6);
        this.mv.visitMethodInsn(185, _UrlKt.FRAGMENT_ENCODE_SET + NAMESPACE + "integration/VariableResolverFactory", "getIndexedVariableResolver", "(I)L" + NAMESPACE + "integration/VariableResolver;");
        this.mv.visitMethodInsn(185, _UrlKt.FRAGMENT_ENCODE_SET + NAMESPACE + "integration/VariableResolver", "getValue", "()Ljava/lang/Object;");
        this.returnType = Object.class;
    }

    private void loadVariableByName(String r6) {
        this.mv.visitVarInsn(25, 3);
        this.mv.visitLdcInsn(r6);
        this.mv.visitMethodInsn(185, _UrlKt.FRAGMENT_ENCODE_SET + NAMESPACE + "integration/VariableResolverFactory", "getVariableResolver", "(Ljava/lang/String;)L" + NAMESPACE + "integration/VariableResolver;");
        this.mv.visitMethodInsn(185, _UrlKt.FRAGMENT_ENCODE_SET + NAMESPACE + "integration/VariableResolver", "getValue", "()Ljava/lang/Object;");
        this.returnType = Object.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object optimizeFieldMethodProperty(Object r6, String r7, Class<?> r8, Member r9) {
        Object r0 = ((Field) r9).get(r6);
        if ((r9.getModifiers() & 8) != 0) goto L5;
        this.mv.visitTypeInsn(192, Type.getInternalName(r8));
        MethodVisitor r1 = this.mv;
        String r82 = Type.getInternalName(r8);
        Class<?> r3 = ((Field) r9).getType();
        this.returnType = r3;
        r1.visitFieldInsn(180, r82, r7, Type.getDescriptor(r3));
    L16:
        this.returnType = ((Field) r9).getType();
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L21;
        if (r0 != null) goto L20;
        r0 = PropertyHandlerFactory.getNullPropertyHandler().getProperty(r9.getName(), r6, this.variableFactory);
    L20:
        writeOutNullHandler(r9, 0);
    L21:
        this.currType = ReflectionUtil.toNonPrimitiveType(this.returnType);
        return r0;
    L5:
        if ((r9.getModifiers() & 16) != 0) goto L7;
    L14:
        MethodVisitor r72 = this.mv;
        String r83 = Type.getInternalName(r9.getDeclaringClass());
        String r12 = r9.getName();
        Class<?> r32 = ((Field) r9).getType();
        this.returnType = r32;
        r72.visitFieldInsn(178, r83, r12, Type.getDescriptor(r32));
        goto L16
    L7:
        if ((r0 instanceof String) == false) goto L9;
    L10:
        Object r62 = ((Field) r9).get(null);
        this.mv.visitLdcInsn(r62);
        wrapPrimitive(r62.getClass());
        if (PropertyHandlerFactory.hasNullPropertyHandler() == false) goto L13;
        writeOutNullHandler(r9, 0);
    L13:
        return r62;
    L9:
        if (((Field) r9).getType().isPrimitive() == false) goto L14;
        goto L10
    }

    private Object propHandlerByteCode(String r4, Object r5, Class r6) {
        PropertyHandler r62 = PropertyHandlerFactory.getPropertyHandler(r6);
        if ((r62 instanceof ProducesBytecode) == false) goto L6;
        ((ProducesBytecode) r62).produceBytecodeGet(this.mv, r4, this.variableFactory);
        return r62.getProperty(r4, r5, this.variableFactory);
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("unable to compileShared: custom accessor does not support producing bytecode: ".concat(r62.getClass().getName()));
        return null;
    }

    private void propHandlerByteCodePut(String r4, Object r5, Class r6, Object r7) {
        PropertyHandler r62 = PropertyHandlerFactory.getPropertyHandler(r6);
        if ((r62 instanceof ProducesBytecode) == false) goto L6;
        ((ProducesBytecode) r62).produceBytecodePut(this.mv, r4, this.variableFactory);
        r62.setProperty(r4, r5, this.variableFactory, r7);
        return;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("unable to compileShared: custom accessor does not support producing bytecode: ".concat(r62.getClass().getName()));
    }

    public static void setMVELClassLoader(MVELClassLoader r0) {
        classLoader = r0;
    }

    public static int toPrimitiveTypeOperand(Class<?> r1) {
        if (r1 != Integer.TYPE) goto L7;
        return 10;
    L7:
        if (r1 != Long.TYPE) goto L11;
        return 11;
    L11:
        if (r1 != Double.TYPE) goto L15;
        return 7;
    L15:
        if (r1 != Float.TYPE) goto L19;
        return 6;
    L19:
        if (r1 != Short.TYPE) goto L23;
        return 9;
    L23:
        if (r1 != Byte.TYPE) goto L27;
        return 8;
    L27:
        if (r1 != Character.TYPE) goto L31;
        return 5;
    L31:
        if (r1 != Boolean.TYPE) goto L34;
        return 4;
    L34:
        DexMaker$$ExternalSyntheticBUOutline0.m("Non-primitive type passed to toPrimitiveTypeOperand: ", r1);
        return 0;
    }

    private void unwrapPrimitive(Class r4) {
        if (r4 != Boolean.TYPE) goto L7;
        this.mv.visitTypeInsn(192, "java/lang/Boolean");
        this.mv.visitMethodInsn(182, "java/lang/Boolean", "booleanValue", "()Z");
        return;
    L7:
        if (r4 != Integer.TYPE) goto L11;
        this.mv.visitTypeInsn(192, "java/lang/Integer");
        this.mv.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I");
        return;
    L11:
        if (r4 != Float.TYPE) goto L15;
        this.mv.visitTypeInsn(192, "java/lang/Float");
        this.mv.visitMethodInsn(182, "java/lang/Float", "floatValue", "()F");
        return;
    L15:
        if (r4 != Double.TYPE) goto L19;
        this.mv.visitTypeInsn(192, "java/lang/Double");
        this.mv.visitMethodInsn(182, "java/lang/Double", "doubleValue", "()D");
        return;
    L19:
        if (r4 != Short.TYPE) goto L23;
        this.mv.visitTypeInsn(192, "java/lang/Short");
        this.mv.visitMethodInsn(182, "java/lang/Short", "shortValue", "()S");
        return;
    L23:
        if (r4 != Long.TYPE) goto L27;
        this.mv.visitTypeInsn(192, "java/lang/Long");
        this.mv.visitMethodInsn(182, "java/lang/Long", "longValue", "()J");
        return;
    L27:
        if (r4 != Byte.TYPE) goto L31;
        this.mv.visitTypeInsn(192, "java/lang/Byte");
        this.mv.visitMethodInsn(182, "java/lang/Byte", "byteValue", "()B");
        return;
    L31:
        if (r4 != Character.TYPE) goto L34;
        this.mv.visitTypeInsn(192, "java/lang/Character");
        this.mv.visitMethodInsn(182, "java/lang/Character", "charValue", "()C");
        return;
    }

    private void visitConstantClass(Class<?> r4) {
        if (r4 != null) goto L4;
        r4 = Object.class;
    L4:
        boolean r0 = r4.isPrimitive();
        MethodVisitor r3 = this.mv;
        if (r0 == false) goto L8;
        r3.visitFieldInsn(178, ReflectionUtil.toNonPrimitiveType(r4).getName().replace(".", "/"), "TYPE", "Ljava/lang/Class;");
        return;
    L8:
        r3.visitLdcInsn(Type.getType(r4));
    }

    private void wrapPrimitive(Class<? extends Object> r20) {
        int r2 = OPCODES_VERSION;
        Class<? extends Object> r5 = Float.TYPE;
        Class<? extends Object> r7 = Integer.TYPE;
        Class<? extends Object> r9 = Boolean.TYPE;
        if (r2 != 48) goto L55;
        debug("** Using 1.4 Bytecode **");
        if (r20 == r9) goto L52;
        if (r20 == Boolean.class) goto L52;
        if (r20 == r7) goto L50;
        if (r20 == Integer.class) goto L50;
        if (r20 == r5) goto L48;
        if (r20 == Float.class) goto L48;
        if (r20 != Double.TYPE) goto L17;
    L46:
        debug("NEW java/lang/Double");
        this.mv.visitTypeInsn(187, "java/lang/Double");
        debug("DUP X2");
        this.mv.visitInsn(91);
        debug("DUP X2");
        this.mv.visitInsn(91);
        debug("POP");
        this.mv.visitInsn(87);
        debug("INVOKESPECIAL java/lang/Double.<init>::(D)V");
        this.mv.visitMethodInsn(183, "java/lang/Double", "<init>", "(D)V");
        return;
    L17:
        if (r20 == Double.class) goto L46;
        if (r20 != Short.TYPE) goto L22;
    L44:
        debug("NEW java/lang/Short");
        this.mv.visitTypeInsn(187, "java/lang/Short");
        debug("DUP X1");
        this.mv.visitInsn(90);
        debug("SWAP");
        this.mv.visitInsn(95);
        debug("INVOKESPECIAL java/lang/Short.<init>::(S)V");
        this.mv.visitMethodInsn(183, "java/lang/Short", "<init>", "(S)V");
        return;
    L22:
        if (r20 == Short.class) goto L44;
        if (r20 != Long.TYPE) goto L27;
    L42:
        debug("NEW java/lang/Long");
        this.mv.visitTypeInsn(187, "java/lang/Long");
        debug("DUP X1");
        this.mv.visitInsn(90);
        debug("SWAP");
        this.mv.visitInsn(95);
        debug("INVOKESPECIAL java/lang/Long.<init>::(L)V");
        this.mv.visitMethodInsn(183, "java/lang/Float", "<init>", "(L)V");
        return;
    L27:
        if (r20 == Long.class) goto L42;
        if (r20 != Byte.TYPE) goto L32;
    L40:
        debug("NEW java/lang/Byte");
        this.mv.visitTypeInsn(187, "java/lang/Byte");
        debug("DUP X1");
        this.mv.visitInsn(90);
        debug("SWAP");
        this.mv.visitInsn(95);
        debug("INVOKESPECIAL java/lang/Byte.<init>::(B)V");
        this.mv.visitMethodInsn(183, "java/lang/Byte", "<init>", "(B)V");
        return;
    L32:
        if (r20 == Byte.class) goto L40;
        if (r20 != Character.TYPE) goto L37;
    L38:
        debug("NEW java/lang/Character");
        this.mv.visitTypeInsn(187, "java/lang/Character");
        debug("DUP X1");
        this.mv.visitInsn(90);
        debug("SWAP");
        this.mv.visitInsn(95);
        debug("INVOKESPECIAL java/lang/Character.<init>::(C)V");
        this.mv.visitMethodInsn(183, "java/lang/Character", "<init>", "(C)V");
        return;
    L37:
        if (r20 == Character.class) goto L38;
        return;
    L48:
        debug("NEW java/lang/Float");
        this.mv.visitTypeInsn(187, "java/lang/Float");
        debug("DUP X1");
        this.mv.visitInsn(90);
        debug("SWAP");
        this.mv.visitInsn(95);
        debug("INVOKESPECIAL java/lang/Float.<init>::(F)V");
        this.mv.visitMethodInsn(183, "java/lang/Float", "<init>", "(F)V");
        return;
    L50:
        debug("NEW java/lang/Integer");
        this.mv.visitTypeInsn(187, "java/lang/Integer");
        debug("DUP X1");
        this.mv.visitInsn(90);
        debug("SWAP");
        this.mv.visitInsn(95);
        debug("INVOKESPECIAL java/lang/Integer.<init>::(I)V");
        this.mv.visitMethodInsn(183, "java/lang/Integer", "<init>", "(I)V");
        return;
    L52:
        debug("NEW java/lang/Boolean");
        this.mv.visitTypeInsn(187, "java/lang/Boolean");
        debug("DUP X1");
        this.mv.visitInsn(90);
        debug("SWAP");
        this.mv.visitInsn(95);
        debug("INVOKESPECIAL java/lang/Boolean.<init>::(Z)V");
        this.mv.visitMethodInsn(183, "java/lang/Boolean", "<init>", "(Z)V");
        return;
    L55:
        if (r20 == r9) goto L104;
        if (r20 == Boolean.class) goto L104;
        if (r20 == r7) goto L102;
        if (r20 == Integer.class) goto L102;
        if (r20 == r5) goto L100;
        if (r20 == Float.class) goto L100;
        if (r20 != Double.TYPE) goto L67;
    L98:
        debug("INVOKESTATIC java/lang/Double.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;");
        return;
    L67:
        if (r20 == Double.class) goto L98;
        if (r20 != Short.TYPE) goto L72;
    L96:
        debug("INVOKESTATIC java/lang/Short.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;");
        return;
    L72:
        if (r20 == Short.class) goto L96;
        if (r20 != Long.TYPE) goto L77;
    L94:
        debug("INVOKESTATIC java/lang/Long.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;");
        return;
    L77:
        if (r20 == Long.class) goto L94;
        if (r20 != Byte.TYPE) goto L82;
    L92:
        debug("INVOKESTATIC java/lang/Byte.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        return;
    L82:
        if (r20 == Byte.class) goto L92;
        if (r20 != Character.TYPE) goto L87;
    L90:
        debug("INVOKESTATIC java/lang/Character.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        return;
    L87:
        if (r20 == Character.class) goto L90;
        return;
    L100:
        debug("INVOKESTATIC java/lang/Float.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;");
        return;
    L102:
        debug("INVOKESTATIC java/lang/Integer.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        return;
    L104:
        debug("INVOKESTATIC java/lang/Boolean.valueOf");
        this.mv.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
    }

    private void writeFunctionPointerStub(Class r11, Method r12) {
        ldcClassConstant(r11);
        this.mv.visitMethodInsn(182, "java/lang/Class", "getMethods", "()[Ljava/lang/reflect/Method;");
        this.mv.visitVarInsn(58, 7);
        this.mv.visitInsn(3);
        this.mv.visitVarInsn(54, 5);
        this.mv.visitVarInsn(25, 7);
        this.mv.visitInsn(190);
        this.mv.visitVarInsn(54, 6);
        Label r112 = new Label();
        this.mv.visitJumpInsn(167, r112);
        Label r3 = new Label();
        this.mv.visitLabel(r3);
        this.mv.visitVarInsn(25, 7);
        this.mv.visitVarInsn(21, 5);
        this.mv.visitInsn(50);
        this.mv.visitVarInsn(58, 4);
        Label r0 = new Label();
        this.mv.visitLabel(r0);
        this.mv.visitLdcInsn(r12.getName());
        this.mv.visitVarInsn(25, 4);
        this.mv.visitMethodInsn(182, "java/lang/reflect/Method", "getName", "()Ljava/lang/String;");
        this.mv.visitMethodInsn(182, "java/lang/String", "equals", "(Ljava/lang/Object;)Z");
        Label r122 = new Label();
        this.mv.visitJumpInsn(153, r122);
        Label r02 = new Label();
        this.mv.visitLabel(r02);
        this.mv.visitVarInsn(25, 4);
        this.mv.visitInsn(176);
        this.mv.visitLabel(r122);
        this.mv.visitIincInsn(5, 1);
        this.mv.visitLabel(r112);
        this.mv.visitVarInsn(21, 5);
        this.mv.visitVarInsn(21, 6);
        this.mv.visitJumpInsn(161, r3);
        Label r113 = new Label();
        this.mv.visitLabel(r113);
        this.mv.visitInsn(1);
        this.mv.visitInsn(176);
    }

    private Class writeLiteralOrSubexpression(Object r2) {
        return writeLiteralOrSubexpression(r2, null, null);
    }

    private Class writeLiteralOrSubexpression(Object r2, Class r3) {
        return writeLiteralOrSubexpression(r2, r3, null);
    }

    private Class writeLiteralOrSubexpression(Object r4, Class r5, Class r6) {
        if ((r4 instanceof ExecutableLiteral) == false) goto L24;
        ExecutableLiteral r42 = (ExecutableLiteral) r4;
        Object r62 = r42.getLiteral();
        if (r62 != null) goto L8;
        this.mv.visitInsn(1);
        return null;
    L8:
        Class<?> r1 = r62.getClass();
        if (r1 != Integer.class) goto L14;
        Class r2 = Integer.TYPE;
        if (r5 != r2) goto L14;
        intPush(r42.getInteger32());
        return r2;
    L14:
        if (r5 == null) goto L22;
        if (r5 == r1) goto L22;
        if (DataConversion.canConvert(r1, r5) == false) goto L20;
        writeOutLiteralWrapped(DataConversion.convert(r62, r5));
        return r1;
    L20:
        Sign$$ExternalSyntheticBUOutline0.m("was expecting type: " + r5.getName() + "; but found type: " + r1.getName(), this.expr, this.st);
        return null;
    L22:
        writeOutLiteralWrapped(r62);
        return r1;
    L24:
        this.literal = false;
        ExecutableStatement r43 = (ExecutableStatement) r4;
        addSubstatement(r43);
        if (r6 != null) goto L27;
        r6 = r43.getKnownEgressType();
    L27:
        if (r5 == null) goto L36;
        if (r6 == r5) goto L36;
        if (r5.isPrimitive() == false) goto L36;
        if (r6 == null) goto L35;
        checkcast(r6);
        unwrapPrimitive(r5);
        return r6;
    L35:
        throw new OptimizationFailure("cannot optimize expression: " + new String(this.expr) + ": cannot determine ingress type for primitive output");
    L36:
        return r6;
    }

    private void writeOutLiteralWrapped(Object r2) {
        if ((r2 instanceof Integer) == false) goto L7;
        intPush(((Integer) r2).intValue());
        wrapPrimitive(Integer.TYPE);
        return;
    L7:
        if ((r2 instanceof String) == false) goto L11;
        this.mv.visitLdcInsn(r2);
        return;
    L11:
        if ((r2 instanceof Long) == false) goto L15;
        this.mv.visitLdcInsn(r2);
        wrapPrimitive(Long.TYPE);
        return;
    L15:
        if ((r2 instanceof Float) == false) goto L19;
        this.mv.visitLdcInsn(r2);
        wrapPrimitive(Float.TYPE);
        return;
    L19:
        if ((r2 instanceof Double) == false) goto L23;
        this.mv.visitLdcInsn(r2);
        wrapPrimitive(Double.TYPE);
        return;
    L23:
        if ((r2 instanceof Short) == false) goto L27;
        this.mv.visitLdcInsn(r2);
        wrapPrimitive(Short.TYPE);
        return;
    L27:
        if ((r2 instanceof Character) == false) goto L31;
        this.mv.visitLdcInsn(r2);
        wrapPrimitive(Character.TYPE);
        return;
    L31:
        if ((r2 instanceof Boolean) == false) goto L35;
        this.mv.visitLdcInsn(r2);
        wrapPrimitive(Boolean.TYPE);
        return;
    L35:
        if ((r2 instanceof Byte) == false) goto L38;
        this.mv.visitLdcInsn(r2);
        wrapPrimitive(Byte.TYPE);
        return;
    }

    private void writeOutNullHandler(Member r9, int r10) {
        this.mv.visitInsn(89);
        Label r0 = new Label();
        this.mv.visitJumpInsn(199, r0);
        this.mv.visitInsn(87);
        this.mv.visitVarInsn(25, 0);
        if (r10 != 0) goto L5;
        this.propNull = true;
        this.mv.visitFieldInsn(180, this.className, "nullPropertyHandler", "L" + NAMESPACE + "integration/PropertyHandler;");
    L6:
        this.mv.visitLdcInsn(r9.getName());
        this.mv.visitVarInsn(25, 1);
        this.mv.visitVarInsn(25, 3);
        this.mv.visitMethodInsn(185, NAMESPACE + "integration/PropertyHandler", "getProperty", "(Ljava/lang/String;Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;)Ljava/lang/Object;");
        this.mv.visitLabel(r0);
        return;
    L5:
        this.methNull = true;
        this.mv.visitFieldInsn(180, this.className, "nullMethodHandler", "L" + NAMESPACE + "integration/PropertyHandler;");
        goto L6
    }

    public void arrayStore(Class r3) {
        if (r3.isPrimitive() == true) goto L5;
        this.mv.visitInsn(83);
        return;
    L5:
        if (r3 != Integer.TYPE) goto L9;
        this.mv.visitInsn(79);
        return;
    L9:
        if (r3 != Character.TYPE) goto L13;
        this.mv.visitInsn(85);
        return;
    L13:
        if (r3 != Boolean.TYPE) goto L17;
        this.mv.visitInsn(84);
        return;
    L17:
        if (r3 != Double.TYPE) goto L21;
        this.mv.visitInsn(82);
        return;
    L21:
        if (r3 != Float.TYPE) goto L25;
        this.mv.visitInsn(81);
        return;
    L25:
        if (r3 != Short.TYPE) goto L29;
        this.mv.visitInsn(86);
        return;
    L29:
        if (r3 != Long.TYPE) goto L33;
        this.mv.visitInsn(80);
        return;
    L33:
        if (r3 != Byte.TYPE) goto L38;
        this.mv.visitInsn(84);
        return;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Class getEgressType() {
        return this.returnType;
    }

    public String getName() {
        return "ASM";
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Object getResultOptPass() {
        return this.val;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public void init() {
        classLoader = new JITClassLoader(Thread.currentThread().getContextClassLoader());     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public boolean isLiteralOnly() {
        return this.literal;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeAccessor(ParserContext r3, char[] r4, int r5, int r6, Object r7, Object r8, VariableResolverFactory r9, boolean r10, Class r11) {
        this.time = System.currentTimeMillis();
        if (this.compiledInputs != null) goto L5;
        this.compiledInputs = new ArrayList();
    L5:
        this.cursor = r5;
        this.start = r5;
        int r62 = r6 + r5;
        this.end = r62;
        this.length = r62 - r5;
        this.first = true;
        this.val = null;
        this.pCtx = r3;
        this.expr = r4;
        this.ctx = r7;
        this.thisRef = r8;
        this.variableFactory = r9;
        this.ingressType = r11;
        if (this.noinit == true) goto L9;
        _initJIT();
    L9:
        return compileAccessor();
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeCollection(ParserContext r2, Object r3, Class r4, char[] r5, int r6, int r7, Object r8, Object r9, VariableResolverFactory r10) {
        this.expr = r5;
        this.start = r6;
        this.cursor = r6;
        this.end = r6 + r7;
        this.length = r7;
        this.returnType = r4;
        this.compiledInputs = new ArrayList();
        this.ctx = r8;
        this.thisRef = r9;
        this.variableFactory = r10;
        this.pCtx = r2;
        _initJIT();
        this.literal = true;
        _getAccessor(r3, r4);
        _finishJIT();
        Accessor r32 = _initializeAccessor();     // Catch: Exception -> L10
        if (r5 == null) goto L9;
        int r1 = this.length;     // Catch: Exception -> L10
        if (r1 <= r6) goto L9;
        return new Union(r2, r32, r5, r6, r1);
    L9:
        return r32;
    L10:
        e = move-exception;
        throw new OptimizationFailure("could not optimize collection", e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r24v0, types: [int] */
    /* JADX WARN: Type inference failed for: r24v1, types: [org.mvel2.compiler.Accessor] */
    /* JADX WARN: Type inference failed for: r24v2 */
    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeObjectCreation(ParserContext r21, char[] r22, int r23, int r24, Object r25, Object r26, VariableResolverFactory r27) {
        _initJIT();
        this.compiledInputs = new ArrayList();
        this.cursor = r23;
        this.start = r23;
        int r5 = r23 + r24;
        this.end = r5;
        this.length = r5 - r23;
        this.ctx = r25;
        this.thisRef = r26;
        this.variableFactory = r27;
        this.pCtx = r21;
        String[] r52 = ParseTools.captureContructorAndResidual(r22, r23, r24);
        int r6 = 0;
        List<char[]> r8 = ParseTools.parseMethodOrConstructor(r52[0].toCharArray());
        if (r8 != null) goto L85;
        Class r1 = ParseTools.findClass(r27, new String(r22), r21);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitTypeInsn(187, Type.getInternalName(r1));     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitInsn(89);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitMethodInsn(183, Type.getInternalName(r1), "<init>", Type.getConstructorDescriptor(r1.getConstructor(EMPTYCLS)));     // Catch: Exception -> L10 ClassNotFoundException -> L84
        _finishJIT();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        Accessor r3 = _initializeAccessor();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r52.length <= 1) goto L79;
        String r12 = r52[1];     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r12 == null) goto L79;
        if (r12.trim().equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L79;
        return new Union(r21, r3, r52[1].toCharArray(), 0, r52[1].length());
    L79:
        return r3;
    L85:
        Iterator<char[]> r16 = r8.iterator();     // Catch: Exception -> L10 ClassNotFoundException -> L12
    L5:
        if (r16.hasNext() == false) goto L13;
        this.compiledInputs.add((ExecutableStatement) ParseTools.subCompileExpression(r16.next(), r21));     // Catch: Exception -> L10 ClassNotFoundException -> L84
        goto L5
    L13:
        Class r13 = ParseTools.findClass(r27, new String(ParseTools.subset(r22, 0, ArrayTools.findFirst('(', r23, this.length, r22))), r21);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitTypeInsn(187, Type.getInternalName(r13));     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitInsn(89);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        int r9 = r8.size();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        Object[] r11 = new Object[r9];     // Catch: Exception -> L10 ClassNotFoundException -> L84
        ArrayList<ExecutableStatement> r122 = this.compiledInputs;     // Catch: Exception -> L10 ClassNotFoundException -> L84
        int r15 = r122.size();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        int r17 = 0;
    L14:
        if (r6 >= r15) goto L16;
        ExecutableStatement r18 = r122.get(r6);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r6 = r6 + 1;     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r11[r17] = r18.getValue(r25, r27);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r17 = r17 + 1;     // Catch: Exception -> L10 ClassNotFoundException -> L84
        goto L14
    L16:
        Constructor r32 = ParseTools.getBestConstructorCandidate(r11, r13, r21.isStrongTyping());     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r32 != null) goto L25;
        StringBuilder r2 = new StringBuilder();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        int r62 = 0;
    L19:
        if (r62 >= r9) goto L24;
        r2.append(r11[r62].getClass().getName());     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r62 = r62 + 1;     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r62 >= r9) goto L19;
        r2.append(", ");     // Catch: Exception -> L10 ClassNotFoundException -> L84
        goto L19
    L24:
        throw new CompileException("unable to find constructor: " + r13.getName() + "(" + r2.toString() + ")", this.expr, this.st);     // Catch: Exception -> L10 ClassNotFoundException -> L84
    L25:
        this.returnType = r32.getDeclaringClass();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        Class<?>[] r4 = r32.getParameterTypes();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        int r63 = -1;
        Class<?> r123 = null;
        int r92 = 0;
    L27:
        if (r92 >= r8.size()) goto L57;
        if (r92 >= r4.length) goto L35;
        r123 = r4[r92];     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r32.isVarArgs() == true) goto L33;
    L37:
        if (r63 < 0) goto L39;
        this.mv.visitInsn(89);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        intPush(r92 - r63);     // Catch: Exception -> L10 ClassNotFoundException -> L84
    L39:
        Class r232 = r13;
        this.mv.visitVarInsn(25, 0);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        StringBuilder r152 = new StringBuilder();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        Constructor r272 = r32;
        r152.append("L");     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r152.append(NAMESPACE);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r152.append("compiler/ExecutableStatement;");     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitFieldInsn(180, this.className, "p" + r92, r152.toString());     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitVarInsn(25, 2);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitVarInsn(25, 3);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        this.mv.visitMethodInsn(185, _UrlKt.FRAGMENT_ENCODE_SET + NAMESPACE + "compiler/ExecutableStatement", "getValue", "(Ljava/lang/Object;L" + NAMESPACE + "integration/VariableResolverFactory;)Ljava/lang/Object;");     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r123.isPrimitive() == false) goto L42;
        Class<?> r14 = getWrapperClass(r123);     // Catch: Exception -> L10 ClassNotFoundException -> L84
    L43:
        Object r28 = r11[r92];     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r28 != null) goto L46;
    L51:
        this.mv.visitTypeInsn(192, Type.getInternalName(r123));     // Catch: Exception -> L10 ClassNotFoundException -> L84
    L52:
        if (r63 < 0) goto L54;
        arrayStore(r123);     // Catch: Exception -> L10 ClassNotFoundException -> L84
    L54:
        r92 = r92 + 1;     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r13 = r232;
        r32 = r272;
        goto L27
    L46:
        if (r28.getClass().isAssignableFrom(r123) == true) goto L51;
        ldcClassConstant(r14);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        Class<?> r172 = r14;
        this.mv.visitMethodInsn(184, _UrlKt.FRAGMENT_ENCODE_SET + NAMESPACE + "DataConversion", "convert", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r123.isPrimitive() == false) goto L50;
        unwrapPrimitive(r123);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        goto L52
    L50:
        this.mv.visitTypeInsn(192, Type.getInternalName(r172));     // Catch: Exception -> L10 ClassNotFoundException -> L84
        goto L52
    L42:
        r14 = r123;
        goto L43
    L33:
        if (r92 != (r4.length - 1)) goto L37;
        r123 = ParseTools.getBaseComponentType(r123);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        createArray(r123, r8.size() - r92);     // Catch: Exception -> L10 ClassNotFoundException -> L84
        r63 = r92;
        goto L37
    L35:
        if (r63 < 0) goto L56;
        if (r123 != null) goto L37;
    L56:
        throw new IllegalStateException("Incorrect argument count " + r92);     // Catch: Exception -> L10 ClassNotFoundException -> L84
    L57:
        Class r233 = r13;
        Constructor r273 = r32;
        if (r92 < r4.length) goto L60;
    L62:
        this.mv.visitMethodInsn(183, Type.getInternalName(r233), "<init>", Type.getConstructorDescriptor(r273));     // Catch: Exception -> L10 ClassNotFoundException -> L84
        _finishJIT();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        Accessor r33 = _initializeAccessor();     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r52.length <= 1) goto L70;
        String r19 = r52[1];     // Catch: Exception -> L10 ClassNotFoundException -> L84
        if (r19 == null) goto L70;
        if (r19.trim().equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L70;
        return new Union(r21, r33, r52[1].toCharArray(), 0, r52[1].length());
    L70:
        return r33;
    L60:
        if (r273.isVarArgs() == false) goto L62;
        createArray(ParseTools.getBaseComponentType(r4[r92]), 0);     // Catch: Exception -> L10 ClassNotFoundException -> L84
    L12:
        r24 = 0;
    L10:
        e = move-exception;
        throw new OptimizationFailure("could not optimize construtor: ".concat(new String(r22)), e);
    L82:
        Sign$$ExternalSyntheticBUOutline0.m("class or class reference not found: ".concat(new String(r22)), r22, this.st);
        return r24;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeSetAccessor(ParserContext r19, char[] r20, int r21, int r22, Object r23, Object r24, VariableResolverFactory r25, boolean r26, Object r27, Class r28) {
        Object r4 = r27;
        this.expr = r20;
        this.cursor = r21;
        this.start = r21;
        int r11 = r21 + r22;
        this.end = r11;
        this.length = r11;
        this.first = true;
        this.ingressType = r28;
        this.compiledInputs = new ArrayList();
        this.ctx = r23;
        this.thisRef = r24;
        this.variableFactory = r25;
        this.pCtx = r19;
        PropertyVerifier r14 = new PropertyVerifier(r20, r19);
        int r15 = findLastUnion();
        if (r15 == (-1)) goto L5;
        char[] r0 = ParseTools.subset(r20, 0, r15);
    L6:
        _initJIT2();
        if (r0 == null) goto L9;
        int r13 = this.length;
        char[] r152 = this.expr;
        this.expr = r0;
        int r12 = r0.length;
        this.end = r12;
        this.length = r12;
        this.deferFinish = true;
        this.noinit = true;
        compileAccessor();
        Object r112 = this.val;
        this.expr = r152;
        int r153 = (r0.length + r21) + 1;
        this.cursor = r153;
        int r132 = (r13 - r0.length) - 1;
        this.length = r132;
        this.end = r153 + r132;
    L155:
        skipWhitespace();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (this.collection == false) goto L85;
        int r02 = this.cursor;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        whiteSpaceSkip();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r02 == this.end) goto L84;
        if (scanTo(']') == true) goto L82;
        String r03 = new String(this.expr, r02, this.cursor - r02).trim();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitTypeInsn(192, Type.getInternalName(r112.getClass()));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if ((r112 instanceof Map) == true) goto L22;
        boolean r7 = r112 instanceof List;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Class r8 = Integer.TYPE;
        if (r7 == false) goto L52;
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L43;
    L45:
        int r6 = ((Integer) MVEL.eval(r03, r112, this.variableFactory, Integer.class)).intValue();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Class r72 = r14.analyze();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.returnType = r72;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        ((List) r112).set(r6, DataConversion.convert(r4, r72));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        writeLiteralOrSubexpression(ParseTools.subCompileExpression(r03.toCharArray(), r19));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        unwrapPrimitive(r8);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r4 != null) goto L48;
    L50:
        this.mv.visitMethodInsn(185, "java/util/List", "set", "(ILjava/lang/Object;)Ljava/lang/Object;");     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L73:
        this.deferFinish = false;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.noinit = false;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        _finishJIT();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.deferFinish = false;     // Catch: Exception -> L76
        return _initializeAccessor();
    L76:
        e = move-exception;
        throw new CompileException("could not generate accessor", this.expr, r21, e);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L48:
        if (r4.getClass().isAssignableFrom(this.returnType) == true) goto L50;
        dataConversion(this.returnType);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        checkcast(this.returnType);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L50
    L43:
        if (PropertyHandlerFactory.hasPropertyHandler(List.class) == false) goto L45;
        propHandlerByteCodePut(r03, r112, List.class, r4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L73
    L52:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == false) goto L57;
        if (PropertyHandlerFactory.hasPropertyHandler(r112.getClass()) == false) goto L57;
        propHandlerByteCodePut(r03, r112, r112.getClass(), r4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L57:
        if (r112.getClass().isArray() == false) goto L80;
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L61;
    L63:
        Class r5 = ParseTools.getBaseComponentType(r112.getClass());     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Object r62 = MVEL.eval(r03, r112, this.variableFactory);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        writeLiteralOrSubexpression(ParseTools.subCompileExpression(r03.toCharArray(), r19), r8);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if ((r62 instanceof Integer) == true) goto L66;
        dataConversion(Integer.class);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        r62 = DataConversion.convert(r62, Integer.class);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        unwrapPrimitive(r8);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L66:
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r5.isPrimitive() == false) goto L70;
        unwrapPrimitive(r5);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L72:
        arrayStore(r5);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Array.set(r112, ((Integer) r62).intValue(), DataConversion.convert(r4, r5));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L73
    L70:
        if (r5.equals(r4.getClass()) == true) goto L72;
        dataConversion(r5);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L72
    L61:
        if (PropertyHandlerFactory.hasPropertyHandler(Array.class) == false) goto L63;
        propHandlerByteCodePut(r03, r112, Array.class, r4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L73
    L80:
        throw new PropertyAccessException("cannot bind to collection property: " + new String(this.expr) + ": not a recognized collection type: " + r112.getClass(), this.expr, r21, r19);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L22:
        if (MVEL.COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING == true) goto L24;
    L31:
        Object r63 = MVEL.eval(r03, r112, this.variableFactory);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Class r73 = r14.analyze();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.returnType = r73;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        ((Map) r112).put(r63, DataConversion.convert(r4, r73));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        writeLiteralOrSubexpression(ParseTools.subCompileExpression(r03.toCharArray(), r19));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r4 != null) goto L34;
    L36:
        this.mv.visitMethodInsn(185, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitInsn(87);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L73
    L34:
        if (this.returnType == r4.getClass()) goto L36;
        dataConversion(this.returnType);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        checkcast(this.returnType);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L36
    L24:
        if (PropertyHandlerFactory.hasPropertyHandler(Map.class) == false) goto L31;
        propHandlerByteCodePut(r03, r112, Map.class, r4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L73
    L82:
        throw new PropertyAccessException("unterminated '['", this.expr, r21, r19);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L84:
        throw new PropertyAccessException("unterminated '['", this.expr, r21, r19);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L85:
        char[] r52 = this.expr;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        int r64 = this.cursor;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        String r04 = new String(r52, r64, this.end - r64);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Class<?> r53 = r112.getClass();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r4 != null) goto L88;
        Class r65 = null;
    L89:
        Member r54 = PropertyTools.getFieldOrWriteAccessor(r53, r04, r65);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (GlobalListenerFactory.hasSetListeners() == false) goto L94;
        this.mv.visitVarInsn(25, 1);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitLdcInsn(r04);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 3);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitMethodInsn(184, NAMESPACE + "integration/GlobalListenerFactory", "notifySetListeners", "(Ljava/lang/Object;Ljava/lang/String;L" + NAMESPACE + "integration/VariableResolverFactory;Ljava/lang/Object;)V");     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        GlobalListenerFactory.notifySetListeners(r112, r04, this.variableFactory, r4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L94:
        if ((r54 instanceof Field) == false) goto L114;
        checkcast(r112.getClass());     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Field r55 = (Field) r54;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Label r66 = new Label();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r55.getType().isPrimitive() == false) goto L101;
        this.mv.visitVarInsn(58, 5);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r4 != null) goto L100;
        r4 = PropertyTools.getPrimitiveInitialValue(r55.getType());     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L100:
        Label r133 = new Label();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitJumpInsn(199, r133);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 5);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitInsn(3);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitFieldInsn(181, Type.getInternalName(r55.getDeclaringClass()), r04, Type.getDescriptor(r55.getType()));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitJumpInsn(167, r66);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitLabel(r133);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 5);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        unwrapPrimitive(r55.getType());     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L102:
        if (r133 != null) goto L111;
        if (r4 == null) goto L111;
        if (r55.getType().isAssignableFrom(r4.getClass()) == true) goto L111;
        if (DataConversion.canConvert(r55.getType(), r4.getClass()) == false) goto L110;
        dataConversion(r55.getType());     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        r55.set(r112, DataConversion.convert(r4, r55.getType()));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L112:
        this.mv.visitFieldInsn(181, Type.getInternalName(r55.getDeclaringClass()), r04, Type.getDescriptor(r55.getType()));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitLabel(r66);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L153:
        this.deferFinish = false;     // Catch: Exception -> L142
        this.noinit = false;     // Catch: Exception -> L142
        _finishJIT();     // Catch: Exception -> L142
        return _initializeAccessor();
    L142:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("could not generate accessor", this.expr, r21, e);
        return null;
    L110:
        throw new CompileException("cannot convert type: " + r4.getClass() + ": to " + r55.getType(), this.expr, r21);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L111:
        r55.set(r112, r4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L112
    L101:
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        checkcast(r55.getType());     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        r133 = null;
        goto L102
    L114:
        if (r54 == null) goto L137;
        this.mv.visitTypeInsn(192, Type.getInternalName(r112.getClass()));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Method r56 = (Method) r54;     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Class<?> r05 = r56.getParameterTypes()[0];     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        Label r67 = new Label();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r4 == null) goto L129;
        if (r05.isAssignableFrom(r4.getClass()) == true) goto L129;
        if (DataConversion.canConvert(r05, r4.getClass()) == false) goto L127;
        dataConversion(getWrapperClass(r05));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        if (r05.isPrimitive() == false) goto L124;
        unwrapPrimitive(r05);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L125:
        r56.invoke(r112, new Object[]{DataConversion.convert(r4, r56.getParameterTypes()[0])});     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L135:
        this.mv.visitMethodInsn(182, Type.getInternalName(r56.getDeclaringClass()), r56.getName(), Type.getMethodDescriptor(r56));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitLabel(r67);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L153
    L124:
        checkcast(r05);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L125
    L127:
        throw new CompileException("cannot convert type: " + r4.getClass() + ": to " + r56.getParameterTypes()[0], this.expr, r21);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L129:
        if (r05.isPrimitive() == false) goto L133;
        if (r4 != null) goto L132;
        r4 = PropertyTools.getPrimitiveInitialValue(r05);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L132:
        Label r74 = new Label();     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitJumpInsn(199, r74);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitInsn(3);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitMethodInsn(182, Type.getInternalName(r56.getDeclaringClass()), r56.getName(), Type.getMethodDescriptor(r56));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitJumpInsn(167, r67);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitLabel(r74);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        unwrapPrimitive(r05);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L134:
        r56.invoke(r112, new Object[]{r4});     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L135
    L133:
        checkcast(r05);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L134
    L137:
        if ((r112 instanceof Map) == false) goto L146;
        this.mv.visitTypeInsn(192, Type.getInternalName(r112.getClass()));     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitLdcInsn(r04);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitMethodInsn(185, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        this.mv.visitVarInsn(25, 4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        ((Map) r112).put(r04, r4);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
        goto L153
    L146:
        throw new PropertyAccessException("could not access property (" + r04 + ") in: " + r28.getName(), this.expr, r21, r19);     // Catch: IllegalAccessException -> L27 InvocationTargetException -> L29
    L88:
        r65 = r28;
    L27:
        e = move-exception;
        throw new PropertyAccessException("could not access property", this.expr, r21, e, r19);
    L29:
        e = move-exception;
        throw new PropertyAccessException("could not access property", this.expr, r21, e, r19);
    L9:
        this.mv.visitVarInsn(25, 1);
        r112 = r23;
        goto L155
    L5:
        r0 = null;
        goto L6
    }

    public void wrapRuntimeConverstion(Class r4) {
        ldcClassConstant(getWrapperClass(r4));
        this.mv.visitMethodInsn(184, _UrlKt.FRAGMENT_ENCODE_SET + NAMESPACE + "DataConversion", "convert", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
    }
}
