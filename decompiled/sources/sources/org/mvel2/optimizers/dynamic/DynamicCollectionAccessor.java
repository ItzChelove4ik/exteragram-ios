package org.mvel2.optimizers.dynamic;

import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class DynamicCollectionAccessor implements DynamicAccessor {
    private Accessor _accessor;
    private Accessor _safeAccessor;
    private Class colType;
    private int offset;
    private boolean opt;
    private ParserContext pCtx;
    private char[] property;
    private Object rootObject;
    private int runcount;
    private long stamp;
    private int start;
    private int type;

    public DynamicCollectionAccessor(ParserContext r2, Object r3, Class r4, char[] r5, int r6, int r7, int r8, Accessor r9) {
        this.opt = false;
        this.pCtx = r2;
        this.rootObject = r3;
        this.colType = r4;
        this._accessor = r9;
        this._safeAccessor = r9;
        this.type = r8;
        this.property = r5;
        this.start = r6;
        this.offset = r7;
        this.stamp = System.currentTimeMillis();
    }

    private Object optimize(ParserContext r12, Object r13, Object r14, VariableResolverFactory r15) {
        if (DynamicOptimizer.isOverloaded() == false) goto L5;
        DynamicOptimizer.enforceTenureLimit();
    L5:
        Accessor r122 = OptimizerFactory.getAccessorCompiler("ASM").optimizeCollection(r12, this.rootObject, this.colType, this.property, this.start, this.offset, r13, r14, r15);
        this._accessor = r122;
        return r122.getValue(r13, r14, r15);
    }

    @Override // org.mvel2.optimizers.dynamic.DynamicAccessor
    public void deoptimize() {
        this._accessor = this._safeAccessor;
        this.opt = false;
        this.runcount = 0;
        this.stamp = System.currentTimeMillis();
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.colType;
    }

    public int getRuncount() {
        return this.runcount;
    }

    public long getStamp() {
        return this.stamp;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r7, Object r8, VariableResolverFactory r9) {
        if (this.opt == true) goto L12;
        int r0 = this.runcount + 1;
        this.runcount = r0;
        if (r0 <= DynamicOptimizer.tenuringThreshold) goto L12;
        if ((System.currentTimeMillis() - this.stamp) >= DynamicOptimizer.timeSpan) goto L10;
        this.opt = true;
        return optimize(this.pCtx, r7, r8, r9);
    L10:
        this.runcount = 0;
        this.stamp = System.currentTimeMillis();
    L12:
        return this._accessor.getValue(r7, r8, r9);
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        this.runcount++;
        return this._accessor.setValue(r2, r3, r4, r5);
    }
}
