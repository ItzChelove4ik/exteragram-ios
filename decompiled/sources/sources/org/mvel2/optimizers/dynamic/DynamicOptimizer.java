package org.mvel2.optimizers.dynamic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AbstractOptimizer;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.optimizers.impl.asm.ASMAccessorOptimizer;

/* loaded from: classes5.dex */
public class DynamicOptimizer extends AbstractOptimizer implements AccessorOptimizer {
    public static final int COLLECTION = 2;
    public static final int OBJ_CREATION = 3;
    public static final int REGULAR_ACCESSOR = 0;
    public static final int SET_ACCESSOR = 1;
    private static volatile DynamicClassLoader classLoader = null;
    private static ReadWriteLock lock = null;
    public static int maximumTenure = 1500;
    private static final Object oLock = null;
    private static Lock readLock = null;
    public static int tenuringThreshold = 50;
    public static long timeSpan = 100;
    public static int totalRecycled = 0;
    private static volatile boolean useSafeClassloading = false;
    private static Lock writeLock;
    private AccessorOptimizer firstStage;

    static {
        oLock = new Object();
        ReentrantReadWriteLock r0 = new ReentrantReadWriteLock();
        lock = r0;
        readLock = r0.readLock();
        writeLock = lock.writeLock();
    }

    public DynamicOptimizer() {
        this.firstStage = OptimizerFactory.getAccessorCompiler(OptimizerFactory.SAFE_REFLECTIVE);
    }

    private static void _init() {
        DynamicClassLoader r0 = new DynamicClassLoader(Thread.currentThread().getContextClassLoader(), maximumTenure);
        classLoader = r0;
        ASMAccessorOptimizer.setMVELClassLoader(r0);
    }

    public static void enforceTenureLimit() {
        writeLock.lock();
    L8:
        th = move-exception;
        writeLock.unlock();
        throw th;
    L4:
        if (classLoader.isOverloaded() == false) goto L6;
        classLoader.deoptimizeAll();     // Catch: Throwable -> L8
        totalRecycled = classLoader.getTotalClasses();     // Catch: Throwable -> L8
        _init();     // Catch: Throwable -> L8
    L6:
        writeLock.unlock();
    }

    public static boolean isOverloaded() {
        return classLoader.isOverloaded();
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Class getEgressType() {
        return this.firstStage.getEgressType();
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Object getResultOptPass() {
        return this.firstStage.getResultOptPass();
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public void init() {
        _init();
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public boolean isLiteralOnly() {
        return this.firstStage.isLiteralOnly();
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeAccessor(ParserContext r13, char[] r14, int r15, int r16, Object r17, Object r18, VariableResolverFactory r19, boolean r20, Class r21) {
        readLock.lock();
        r13.optimizationNotify();     // Catch: Throwable -> L6
        DynamicAccessor r12 = classLoader.registerDynamicAccessor(new DynamicGetAccessor(r13, r14, r15, r16, 0, this.firstStage.optimizeAccessor(r13, r14, r15, r16, r17, r18, r19, r20, r21)));     // Catch: Throwable -> L6
        readLock.unlock();
        return r12;
    L6:
        th = move-exception;
        readLock.unlock();
        throw th;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeCollection(ParserContext r13, Object r14, Class r15, char[] r16, int r17, int r18, Object r19, Object r20, VariableResolverFactory r21) {
        readLock.lock();
        DynamicAccessor r12 = classLoader.registerDynamicAccessor(new DynamicCollectionAccessor(r13, r14, r15, r16, r17, r18, 2, this.firstStage.optimizeCollection(r13, r14, r15, r16, r17, r18, r19, r20, r21)));     // Catch: Throwable -> L6
        readLock.unlock();
        return r12;
    L6:
        th = move-exception;
        readLock.unlock();
        throw th;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeObjectCreation(ParserContext r11, char[] r12, int r13, int r14, Object r15, Object r16, VariableResolverFactory r17) {
        readLock.lock();
        DynamicAccessor r10 = classLoader.registerDynamicAccessor(new DynamicGetAccessor(r11, r12, r13, r14, 3, this.firstStage.optimizeObjectCreation(r11, r12, r13, r14, r15, r16, r17)));     // Catch: Throwable -> L6
        readLock.unlock();
        return r10;
    L6:
        th = move-exception;
        readLock.unlock();
        throw th;
    }

    @Override // org.mvel2.optimizers.AccessorOptimizer
    public Accessor optimizeSetAccessor(ParserContext r14, char[] r15, int r16, int r17, Object r18, Object r19, VariableResolverFactory r20, boolean r21, Object r22, Class r23) {
        readLock.lock();
        DynamicAccessor r13 = classLoader.registerDynamicAccessor(new DynamicSetAccessor(r14, r15, r16, r17, this.firstStage.optimizeSetAccessor(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)));     // Catch: Throwable -> L6
        readLock.unlock();
        return r13;
    L6:
        th = move-exception;
        readLock.unlock();
        throw th;
    }
}
