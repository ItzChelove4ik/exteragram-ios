package org.mvel2.optimizers;

import org.mvel2.compiler.Accessor;

/* loaded from: classes5.dex */
public interface OptimizerHook {
    Accessor generateAccessor(AccessorOptimizer r1);

    boolean isOptimizerSupported(Class<? extends AccessorOptimizer> r1);
}
