package org.mvel2.optimizers;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mvel2.optimizers.dynamic.DynamicOptimizer;
import org.mvel2.optimizers.impl.asm.ASMAccessorOptimizer;
import org.mvel2.optimizers.impl.refl.ReflectiveAccessorOptimizer;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public class OptimizerFactory {
    public static String DYNAMIC = "dynamic";
    private static final Logger LOG = null;
    public static String SAFE_REFLECTIVE = "reflective";
    private static final Map<String, AccessorOptimizer> accessorCompilers = null;
    private static String defaultOptimizer;
    private static ThreadLocal<Class<? extends AccessorOptimizer>> threadOptimizer;

    static {
        LOG = Logger.getLogger(OptimizerFactory.class.getName());
        HashMap r1 = new HashMap();
        accessorCompilers = r1;
        threadOptimizer = new ThreadLocal();
        r1.put(SAFE_REFLECTIVE, new ReflectiveAccessorOptimizer());
        r1.put(DYNAMIC, new DynamicOptimizer());
    L12:
        defaultOptimizer = SAFE_REFLECTIVE;
    L14:
        if (Boolean.getBoolean("mvel2.disable.jit") == false) goto L16;
        setDefaultOptimizer(SAFE_REFLECTIVE);
        return;
    L16:
        setDefaultOptimizer(DYNAMIC);
        return;
    L10:
        th = move-exception;
        LOG.log(Level.WARNING, "[MVEL] Notice: Possible incorrect version of ASM present (3.0 required).  Disabling JIT compiler.  Reflective Optimizer will be used.", th);
        defaultOptimizer = SAFE_REFLECTIVE;
        goto L14
    L5:
        if (OptimizerFactory.class.getClassLoader() == null) goto L7;
        OptimizerFactory.class.getClassLoader().loadClass("org.mvel2.asm.ClassWriter");     // Catch: Throwable -> L10 ClassNotFoundException -> L12
    L8:
        r1.put("ASM", new ASMAccessorOptimizer());     // Catch: Throwable -> L10 ClassNotFoundException -> L12
    L7:
        ClassLoader.getSystemClassLoader().loadClass("org.mvel2.asm.ClassWriter");     // Catch: Throwable -> L10 ClassNotFoundException -> L12
        goto L8
    }

    public OptimizerFactory() {
    }

    public static void clearThreadAccessorOptimizer() {
        threadOptimizer.set(null);
        threadOptimizer.remove();
    }

    public static AccessorOptimizer getAccessorCompiler(String r1) {
        return (AccessorOptimizer) accessorCompilers.get(r1).getClass().newInstance();
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to instantiate accessor compiler", e);
        return null;
    }

    public static AccessorOptimizer getDefaultAccessorCompiler() {
        return (AccessorOptimizer) accessorCompilers.get(defaultOptimizer).getClass().newInstance();
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to instantiate accessor compiler", e);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AccessorOptimizer getThreadAccessorOptimizer() {
        if (threadOptimizer.get() != null) goto L10;
        threadOptimizer.set(getDefaultAccessorCompiler().getClass());
    L10:
        return threadOptimizer.get().newInstance();
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to instantiate accessor compiler", e);
        return null;
    }

    public static boolean isThreadAccessorOptimizerInitialized() {
        if (threadOptimizer.get() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public static void setDefaultOptimizer(String r1) {
        Map<String, AccessorOptimizer> r0 = accessorCompilers;     // Catch: Exception -> L4
        defaultOptimizer = r1;     // Catch: Exception -> L4
        r0.get(r1).init();     // Catch: Exception -> L4
        threadOptimizer.set(null);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to instantiate accessor compiler", e);
    }

    public static void setThreadAccessorOptimizer(Class<? extends AccessorOptimizer> r1) {
        if (r1 == null) goto L5;
        threadOptimizer.set(r1);
        return;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("null optimizer");
    }
}
