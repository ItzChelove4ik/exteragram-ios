package org.mvel2.optimizers.dynamic;

import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class DynamicGetAccessor implements DynamicAccessor {
    private Accessor _accessor;
    private Accessor _safeAccessor;
    private char[] expr;
    private int offset;
    private boolean opt;
    private ParserContext pCtx;
    private int runcount;
    private long stamp;
    private int start;
    private int type;

    public DynamicGetAccessor(ParserContext r2, char[] r3, int r4, int r5, int r6, Accessor r7) {
        this.opt = false;
        this._accessor = r7;
        this._safeAccessor = r7;
        this.type = r6;
        this.expr = r3;
        this.start = r4;
        this.offset = r5;
        this.pCtx = r2;
        this.stamp = System.currentTimeMillis();
    }

    private Object optimize(Object r12, Object r13, VariableResolverFactory r14) {
        if (DynamicOptimizer.isOverloaded() == false) goto L5;
        DynamicOptimizer.enforceTenureLimit();
    L5:
        AccessorOptimizer r1 = OptimizerFactory.getAccessorCompiler("ASM");
        int r0 = this.type;
        if (r0 != 0) goto L8;
        this._accessor = r1.optimizeAccessor(this.pCtx, this.expr, this.start, this.offset, r12, r13, r14, false, null);
        return r1.getResultOptPass();
    L8:
        if (r0 != 2) goto L10;
        Accessor r122 = r1.optimizeCollection(this.pCtx, r12, null, this.expr, this.start, this.offset, r12, r13, r14);
        this._accessor = r122;
        return r122.getValue(r12, r13, r14);
    L10:
        if (r0 == 3) goto L13;
        return null;
    L13:
        Accessor r123 = r1.optimizeObjectCreation(this.pCtx, this.expr, this.start, this.offset, r12, r13, r14);
        this._accessor = r123;
        return r123.getValue(r12, r13, r14);
    }

    @Override // org.mvel2.optimizers.dynamic.DynamicAccessor
    public void deoptimize() {
        this._accessor = this._safeAccessor;
        this.opt = false;
        this.runcount = 0;
        this.stamp = System.currentTimeMillis();
    }

    public Accessor getAccessor() {
        return this._accessor;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this._safeAccessor.getKnownEgressType();
    }

    public int getRuncount() {
        return this.runcount;
    }

    public Accessor getSafeAccessor() {
        return this._safeAccessor;
    }

    public long getStamp() {
        return this.stamp;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r7, Object r8, VariableResolverFactory r9) {
        if (this.opt == true) goto L13;
        int r0 = this.runcount + 1;
        this.runcount = r0;
        if (r0 <= DynamicOptimizer.tenuringThreshold) goto L13;
        if ((System.currentTimeMillis() - this.stamp) >= DynamicOptimizer.timeSpan) goto L11;
        this.opt = true;
        return optimize(r7, r8, r9);
    L11:
        this.runcount = 0;
        this.stamp = System.currentTimeMillis();
    L13:
        return this._accessor.getValue(r7, r8, r9);
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        this.runcount++;
        return this._accessor.setValue(r2, r3, r4, r5);
    }
}
