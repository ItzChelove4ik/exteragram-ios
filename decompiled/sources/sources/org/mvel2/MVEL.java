package org.mvel2;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mvel2.compiler.CompiledAccExpression;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.compiler.ExpressionCompiler;
import org.mvel2.integration.Interceptor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.CachedMapVariableResolverFactory;
import org.mvel2.integration.impl.CachingMapVariableResolverFactory;
import org.mvel2.integration.impl.ClassImportResolverFactory;
import org.mvel2.integration.impl.ImmutableDefaultFactory;
import org.mvel2.optimizers.impl.refl.nodes.GetterAccessor;
import org.mvel2.util.ParseTools;

/* loaded from: classes.dex */
public class MVEL {
    static boolean ADVANCED_DEBUG = false;
    static String ADVANCED_DEBUGGING_FILE = null;
    public static final String CODENAME = "liberty";
    public static boolean COMPILER_OPT_ALLOCATE_TYPE_LITERALS_TO_SHARED_SYMBOL_TABLE = false;
    public static boolean COMPILER_OPT_ALLOW_NAKED_METH_CALL = false;
    public static boolean COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING = false;
    public static boolean COMPILER_OPT_ALLOW_RESOLVE_INNERCLASSES_WITH_DOTNOTATION = false;
    public static boolean COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS = false;
    static boolean DEBUG_FILE = false;
    public static boolean INVOKED_METHOD_EXCEPTIONS_BUBBLE = false;
    public static final String NAME = "MVEL (MVFLEX Expression Language)";
    static boolean NO_JIT = false;
    static boolean OPTIMIZER = false;
    public static boolean RUNTIME_OPT_THREAD_UNSAFE = false;
    public static final String VERSION = "2.3";
    public static final String VERSION_SUB = "0";
    static boolean WEAK_CACHE;

    static {
        DEBUG_FILE = Boolean.getBoolean("mvel2.debug.fileoutput");
        if (System.getProperty("mvel2.debugging.file") != null) goto L5;
        String r0 = "mvel_debug.txt";
    L6:
        ADVANCED_DEBUGGING_FILE = r0;
        ADVANCED_DEBUG = Boolean.getBoolean("mvel2.advanced_debugging");
        WEAK_CACHE = Boolean.getBoolean("mvel2.weak_caching");
        NO_JIT = Boolean.getBoolean("mvel2.disable.jit");
        INVOKED_METHOD_EXCEPTIONS_BUBBLE = Boolean.getBoolean("mvel2.invoked_meth_exceptions_bubble");
        COMPILER_OPT_ALLOW_NAKED_METH_CALL = Boolean.getBoolean("mvel2.compiler.allow_naked_meth_calls");
        COMPILER_OPT_ALLOW_OVERRIDE_ALL_PROPHANDLING = Boolean.getBoolean("mvel2.compiler.allow_override_all_prophandling");
        COMPILER_OPT_ALLOW_RESOLVE_INNERCLASSES_WITH_DOTNOTATION = Boolean.getBoolean("mvel2.compiler.allow_resolve_inner_classes_with_dotnotation");
        COMPILER_OPT_SUPPORT_JAVA_STYLE_CLASS_LITERALS = Boolean.getBoolean("mvel2.compiler.support_java_style_class_literals");
        COMPILER_OPT_ALLOCATE_TYPE_LITERALS_TO_SHARED_SYMBOL_TABLE = Boolean.getBoolean("mvel2.compiler.allocate_type_literals_to_shared_symbol_table");
        RUNTIME_OPT_THREAD_UNSAFE = Boolean.getBoolean("mvel2.runtime.thread_unsafe");
        OPTIMIZER = true;
        if (System.getProperty("mvel2.optimizer") == null) goto L10;
        OPTIMIZER = Boolean.getBoolean("mvel2.optimizer");
        return;
    L10:
        return;
    L5:
        r0 = System.getProperty("mvel2.debugging.file");
        goto L6
    }

    private MVEL() {
    }

    private static Object _evalFile(File r1, Object r2, VariableResolverFactory r3) {
        return _evalFile(r1, null, r2, r3);
    }

    private static Object _evalFile(File r0, String r1, Object r2, VariableResolverFactory r3) {
        return eval(ParseTools.loadFromFile(r0, r1), r2, r3);
    }

    public static void analysisCompile(String r0, ParserContext r1) {
        analysisCompile(r0.toCharArray(), r1);
    }

    public static void analysisCompile(char[] r1, ParserContext r2) {
        ExpressionCompiler r0 = new ExpressionCompiler(r1, r2);
        r0.setVerifyOnly(true);
        r0.compile();
    }

    public static Class analyze(String r0, ParserContext r1) {
        return analyze(r0.toCharArray(), r1);
    }

    public static Class analyze(char[] r1, ParserContext r2) {
        ExpressionCompiler r0 = new ExpressionCompiler(r1, r2);
        r0.setVerifyOnly(true);
        r0.compile();
        return r0.getReturnType();
    }

    public static Serializable compileExpression(String r1) {
        return compileExpression(r1, null, null, null);
    }

    public static Serializable compileExpression(String r1, Map<String, Object> r2) {
        return compileExpression(r1, r2, null, null);
    }

    public static Serializable compileExpression(String r1, Map<String, Object> r2, Map<String, Interceptor> r3) {
        return compileExpression(r1, r2, r3, null);
    }

    public static Serializable compileExpression(String r1, Map<String, Object> r2, Map<String, Interceptor> r3, String r4) {
        return compileExpression(r1, new ParserContext(r2, r3, r4));
    }

    public static Serializable compileExpression(String r1, ParserContext r2) {
        return ParseTools.optimizeTree(new ExpressionCompiler(r1, r2).compile());
    }

    public static Serializable compileExpression(char[] r1) {
        return compileExpression(r1, null, null, null);
    }

    public static Serializable compileExpression(char[] r1, int r2, int r3, ParserContext r4) {
        return ParseTools.optimizeTree(new ExpressionCompiler(r1, r2, r3, r4)._compile());
    }

    public static Serializable compileExpression(char[] r1, Map<String, Object> r2) {
        return compileExpression(r1, r2, null, null);
    }

    public static Serializable compileExpression(char[] r1, Map<String, Object> r2, Map<String, Interceptor> r3) {
        return compileExpression(r1, r2, r3, null);
    }

    public static Serializable compileExpression(char[] r1, Map<String, Object> r2, Map<String, Interceptor> r3, String r4) {
        return compileExpression(r1, new ParserContext(r2, r3, r4));
    }

    public static Serializable compileExpression(char[] r1, ParserContext r2) {
        return ParseTools.optimizeTree(new ExpressionCompiler(r1, r2).compile());
    }

    public static Serializable compileGetExpression(String r3) {
        return new CompiledAccExpression(r3.toCharArray(), Object.class, new ParserContext());
    }

    public static Serializable compileGetExpression(String r2, ParserContext r3) {
        return new CompiledAccExpression(r2.toCharArray(), Object.class, r3);
    }

    public static Serializable compileGetExpression(char[] r3) {
        return new CompiledAccExpression(r3, Object.class, new ParserContext());
    }

    public static Serializable compileGetExpression(char[] r2, ParserContext r3) {
        return new CompiledAccExpression(r2, Object.class, r3);
    }

    public static Serializable compileSetExpression(String r3) {
        return new CompiledAccExpression(r3.toCharArray(), Object.class, new ParserContext());
    }

    public static Serializable compileSetExpression(String r1, Class r2, ParserContext r3) {
        return new CompiledAccExpression(r1.toCharArray(), r2, r3);
    }

    public static Serializable compileSetExpression(String r2, ParserContext r3) {
        return new CompiledAccExpression(r2.toCharArray(), Object.class, r3);
    }

    public static Serializable compileSetExpression(char[] r3) {
        return new CompiledAccExpression(r3, Object.class, new ParserContext());
    }

    public static Serializable compileSetExpression(char[] r6, int r7, int r8, ParserContext r9) {
        return new CompiledAccExpression(r6, r7, r8, Object.class, r9);
    }

    public static Serializable compileSetExpression(char[] r1, Class r2, ParserContext r3) {
        return new CompiledAccExpression(r1, r2, r3);
    }

    public static Serializable compileSetExpression(char[] r2, ParserContext r3) {
        return new CompiledAccExpression(r2, Object.class, r3);
    }

    public static Object eval(String r2) {
        return new MVELInterpretedRuntime(r2, new ImmutableDefaultFactory()).parse();
    }

    public static <T> T eval(String r1, Class<T> r2) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r1).parse(), r2);
    }

    public static Object eval(String r2, Object r3) {
        return new MVELInterpretedRuntime(r2, r3, new ImmutableDefaultFactory()).parse();
    }

    public static <T> T eval(String r1, Object r2, Class<T> r3) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r1, r2).parse(), r3);
    }

    public static Object eval(String r1, Object r2, Map<String, Object> r3) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r3);
        Object r12 = new MVELInterpretedRuntime(r1, r2, r0).parse();     // Catch: Throwable -> L6
        r0.externalize();
        return r12;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static <T> T eval(String r1, Object r2, Map<String, Object> r3, Class<T> r4) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r3);
        T r12 = (T) DataConversion.convert(new MVELInterpretedRuntime(r1, r2, r0).parse(), r4);
        r0.externalize();
        return r12;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static Object eval(String r1, Object r2, VariableResolverFactory r3) {
        return new MVELInterpretedRuntime(r1, r2, r3).parse();
    }

    public static <T> T eval(String r1, Object r2, VariableResolverFactory r3, Class<T> r4) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r1, r2, r3).parse(), r4);
    }

    public static Object eval(String r2, Map<String, Object> r3) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r3);
        Object r22 = new MVELInterpretedRuntime(r2, null, r0).parse();     // Catch: Throwable -> L6
        r0.externalize();
        return r22;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static <T> T eval(String r2, Map<String, Object> r3, Class<T> r4) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r3);
        T r22 = (T) DataConversion.convert(new MVELInterpretedRuntime(r2, null, r0).parse(), r4);
        r0.externalize();
        return r22;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static Object eval(String r1, VariableResolverFactory r2) {
        return new MVELInterpretedRuntime(r1, r2).parse();
    }

    public static <T> T eval(String r2, VariableResolverFactory r3, Class<T> r4) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r2, null, r3).parse(), r4);
    }

    public static Object eval(char[] r2) {
        return new MVELInterpretedRuntime(r2, new ImmutableDefaultFactory()).parse();
    }

    public static Object eval(char[] r6, int r7, int r8, Object r9, VariableResolverFactory r10) {
        return new MVELInterpretedRuntime(r6, r7, r8, r9, r10).parse();
    }

    public static <T> T eval(char[] r6, int r7, int r8, Object r9, VariableResolverFactory r10, Class<T> r11) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r6, r7, r8, r9, r10).parse(), r11);
    }

    public static <T> T eval(char[] r1, Class<T> r2) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r1).parse(), r2);
    }

    public static Object eval(char[] r1, Object r2) {
        return new MVELInterpretedRuntime(r1, r2).parse();
    }

    public static <T> T eval(char[] r1, Object r2, Class<T> r3) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r1, r2).parse(), r3);
    }

    public static Object eval(char[] r1, Object r2, Map r3) {
        return new MVELInterpretedRuntime(r1, r2, r3).parse();
    }

    public static <T> T eval(char[] r1, Object r2, Map<String, Object> r3, Class<T> r4) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r1, r2, r3).parse(), r4);
    }

    public static Object eval(char[] r1, Object r2, VariableResolverFactory r3) {
        return new MVELInterpretedRuntime(r1, r2, r3).parse();
    }

    public static <T> T eval(char[] r1, Object r2, VariableResolverFactory r3, Class<T> r4) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r1, r2, r3).parse(), r4);
    }

    public static <T> T eval(char[] r2, Map<String, Object> r3, Class<T> r4) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r2, null, r3).parse(), r4);
    }

    public static <T> T eval(char[] r2, VariableResolverFactory r3, Class<T> r4) {
        return (T) DataConversion.convert(new MVELInterpretedRuntime(r2, null, r3).parse(), r4);
    }

    public static Object evalFile(File r2) {
        return _evalFile(r2, null, new CachedMapVariableResolverFactory(new HashMap()));
    }

    public static Object evalFile(File r2, Object r3) {
        return _evalFile(r2, r3, new CachedMapVariableResolverFactory(new HashMap()));
    }

    public static Object evalFile(File r1, Object r2, Map<String, Object> r3) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r3);
        Object r12 = _evalFile(r1, r2, r0);     // Catch: Throwable -> L6
        r0.externalize();
        return r12;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static Object evalFile(File r0, Object r1, VariableResolverFactory r2) {
        return _evalFile(r0, r1, r2);
    }

    public static Object evalFile(File r2, String r3) {
        return _evalFile(r2, r3, null, new CachedMapVariableResolverFactory(new HashMap()));
    }

    public static Object evalFile(File r2, String r3, Object r4) {
        return _evalFile(r2, r3, r4, new CachedMapVariableResolverFactory(new HashMap()));
    }

    public static Object evalFile(File r1, String r2, Object r3, Map<String, Object> r4) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r4);
        Object r12 = _evalFile(r1, r2, r3, r0);     // Catch: Throwable -> L6
        r0.externalize();
        return r12;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static Object evalFile(File r0, String r1, Object r2, VariableResolverFactory r3) {
        return _evalFile(r0, r1, r2, r3);
    }

    public static Object evalFile(File r1, Map<String, Object> r2) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r2);
        Object r12 = _evalFile(r1, null, r0);     // Catch: Throwable -> L6
        r0.externalize();
        return r12;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static Boolean evalToBoolean(String r2, Object r3) {
        return (Boolean) eval(r2, r3, new ImmutableDefaultFactory(), Boolean.class);
    }

    public static Boolean evalToBoolean(String r1, Object r2, Map<String, Object> r3) {
        return (Boolean) eval(r1, r2, r3, Boolean.class);
    }

    public static Boolean evalToBoolean(String r1, Object r2, VariableResolverFactory r3) {
        return (Boolean) eval(r1, r2, r3, Boolean.class);
    }

    public static Boolean evalToBoolean(String r1, Map<String, Object> r2) {
        return evalToBoolean(r1, null, r2);
    }

    public static Boolean evalToBoolean(String r1, VariableResolverFactory r2) {
        return (Boolean) eval(r1, r2, Boolean.class);
    }

    public static String evalToString(String r0) {
        return String.valueOf(eval(r0));
    }

    public static String evalToString(String r0, Object r1) {
        return String.valueOf(eval(r0, r1));
    }

    public static String evalToString(String r0, Object r1, Map r2) {
        return String.valueOf(eval(r0, r1, r2));
    }

    public static String evalToString(String r0, Object r1, VariableResolverFactory r2) {
        return String.valueOf(eval(r0, r1, r2));
    }

    public static String evalToString(String r0, Map r1) {
        return String.valueOf(eval(r0, r1));
    }

    public static String evalToString(String r0, VariableResolverFactory r1) {
        return String.valueOf(eval(r0, r1));
    }

    public static Object[] executeAllExpression(Serializable[] r3, Object r4, VariableResolverFactory r5) {
        if (r3 == null) goto L4;
        Object[] r0 = new Object[r3.length];
        int r1 = 0;
    L7:
        if (r1 >= r3.length) goto L9;
        r0[r1] = executeExpression(r3[r1], r4, r5);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    L4:
        return GetterAccessor.EMPTY;
    }

    public static Object executeDebugger(CompiledExpression r4, Object r5, VariableResolverFactory r6) {
        if (r4.isImportInjectionRequired() == false) goto L7;
        return MVELRuntime.execute(true, r4, r5, new ClassImportResolverFactory(r4.getParserConfiguration(), r6, false));
    L7:
        return MVELRuntime.execute(true, r4, r5, r6);
    }

    public static Object executeExpression(Object r2) {
        return ((ExecutableStatement) r2).getValue(null, new ImmutableDefaultFactory());
    }

    public static Object executeExpression(Object r1, Object r2) {
        return ((ExecutableStatement) r1).getValue(r2, new ImmutableDefaultFactory());
    }

    public static <T> T executeExpression(Object r0, Object r1, Class<T> r2) {
        return (T) DataConversion.convert(executeExpression(r0, r1), r2);
    }

    public static Object executeExpression(Object r1, Object r2, Map r3) {
        if (r3 == null) goto L4;
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r3);
    L13:
        Object r12 = ((ExecutableStatement) r1).getValue(r2, r0);     // Catch: Throwable -> L9
        if (r0 == null) goto L8;
        r0.externalize();
    L8:
        return r12;
    L9:
        th = move-exception;
        if (r0 == null) goto L12;
        r0.externalize();
    L12:
        throw th;
    L4:
        r0 = null;
        goto L13
    }

    public static <T> T executeExpression(Object r0, Object r1, Map r2, Class<T> r3) {
        return (T) DataConversion.convert(executeExpression(r0, r1, r2), r3);
    }

    public static Object executeExpression(Object r0, Object r1, VariableResolverFactory r2) {
        return ((ExecutableStatement) r0).getValue(r1, r2);
    }

    public static <T> T executeExpression(Object r0, Object r1, VariableResolverFactory r2, Class<T> r3) {
        return (T) DataConversion.convert(executeExpression(r0, r1, r2), r3);
    }

    public static Object executeExpression(Object r1, Map r2) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r2);
        Object r12 = ((ExecutableStatement) r1).getValue(null, r0);     // Catch: Throwable -> L6
        r0.externalize();
        return r12;
    L6:
        th = move-exception;
        r0.externalize();
        throw th;
    }

    public static <T> T executeExpression(Object r0, Map r1, Class<T> r2) {
        return (T) DataConversion.convert(executeExpression(r0, r1), r2);
    }

    public static Object executeExpression(Object r1, VariableResolverFactory r2) {
        return ((ExecutableStatement) r1).getValue(null, r2);
    }

    public static void executeExpression(Iterable<CompiledExpression> r2) {
        Iterator<CompiledExpression> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().getValue(null, null);
        goto L4
    }

    public static void executeExpression(Iterable<CompiledExpression> r2, Object r3) {
        Iterator<CompiledExpression> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().getValue(r3, null);
        goto L4
    }

    public static void executeExpression(Iterable<CompiledExpression> r1, Object r2, Map r3) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r3);
        executeExpression(r1, r2, r0);
        r0.externalize();
    }

    public static void executeExpression(Iterable<CompiledExpression> r1, Object r2, VariableResolverFactory r3) {
        Iterator<CompiledExpression> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        r12.next().getValue(r2, r3);
        goto L4
    }

    public static void executeExpression(Iterable<CompiledExpression> r1, Map r2) {
        CachingMapVariableResolverFactory r0 = new CachingMapVariableResolverFactory(r2);
        executeExpression(r1, null, r0);
        r0.externalize();
    }

    public static void executeSetExpression(Serializable r1, Object r2, Object r3) {
        ((CompiledAccExpression) r1).setValue(r2, r2, new ImmutableDefaultFactory(), r3);
    }

    public static void executeSetExpression(Serializable r0, Object r1, VariableResolverFactory r2, Object r3) {
        ((CompiledAccExpression) r0).setValue(r1, r1, r2, r3);
    }

    public static String getDebuggingOutputFileName() {
        return ADVANCED_DEBUGGING_FILE;
    }

    public static Object getProperty(String r0, Object r1) {
        return PropertyAccessor.get(r0, r1);
    }

    public static Method getStaticMethod(Class r1, String r2, Class[] r3) {
        Method r12 = r1.getMethod(r2, r3);     // Catch: NoSuchMethodException -> L8
        if ((r12.getModifiers() & 8) == 0) goto L7;
        return r12;
    L7:
        throw new RuntimeException("method not a static method: " + r2);     // Catch: NoSuchMethodException -> L8
    L8:
        MVEL$$ExternalSyntheticBUOutline0.m("no such method: ", r2);
        return null;
    }

    public static boolean isAdvancedDebugging() {
        return ADVANCED_DEBUG;
    }

    public static boolean isFileDebugging() {
        return DEBUG_FILE;
    }

    public static String parseMacros(String r1, Map<String, Macro> r2) {
        return new MacroProcessor(r2).parse(r1);
    }

    public static String preprocess(String r0, PreProcessor[] r1) {
        return preprocess(r0.toCharArray(), r1);
    }

    public static String preprocess(char[] r3, PreProcessor[] r4) {
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L6;
        r3 = r4[r1].parse(r3);
        r1 = r1 + 1;
        goto L3
    L6:
        return new String(r3);
    }

    public static void setProperty(Object r0, String r1, Object r2) {
        PropertyAccessor.set(r0, r1, r2);
    }
}
