package org.mvel2.optimizers.dynamic;

import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class DynamicSetAccessor implements DynamicAccessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Accessor _accessor;
    private final Accessor _safeAccessor;
    private ParserContext context;
    private String description;
    private int offset;
    private boolean opt;
    private char[] property;
    private int runcount;
    private long stamp;
    private int start;

    static {
    }

    public DynamicSetAccessor(ParserContext r2, char[] r3, int r4, int r5, Accessor r6) {
        this.opt = false;
        this.runcount = 0;
        this._accessor = r6;
        this._safeAccessor = r6;
        this.context = r2;
        this.property = r3;
        this.start = r4;
        this.offset = r5;
        this.stamp = System.currentTimeMillis();
    }

    private Object optimize(Object r13, Object r14, VariableResolverFactory r15, Object r16) {
        if (DynamicOptimizer.isOverloaded() == false) goto L5;
        DynamicOptimizer.enforceTenureLimit();
    L5:
        AccessorOptimizer r1 = OptimizerFactory.getAccessorCompiler("ASM");
        ParserContext r2 = this.context;
        char[] r3 = this.property;
        int r4 = this.start;
        int r5 = this.offset;
        if (r16 == null) goto L9;
        Class<?> r0 = r16.getClass();
    L10:
        this._accessor = r1.optimizeSetAccessor(r2, r3, r4, r5, r13, r14, r15, false, r16, r0);
        return r16;
    L9:
        r0 = Object.class;
        goto L10
    }

    @Override // org.mvel2.optimizers.dynamic.DynamicAccessor
    public void deoptimize() {
        this._accessor = this._safeAccessor;
        this.opt = false;
        this.runcount = 0;
        this.stamp = System.currentTimeMillis();
    }

    public String getDescription() {
        return this.description;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this._safeAccessor.getKnownEgressType();
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        throw new RuntimeException("value cannot be read with this accessor");
    }

    public void setDescription(String r1) {
        this.description = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r7, Object r8, VariableResolverFactory r9, Object r10) {
        if (this.opt == true) goto L11;
        int r0 = this.runcount + 1;
        this.runcount = r0;
        if (r0 <= DynamicOptimizer.tenuringThreshold) goto L11;
        if ((System.currentTimeMillis() - this.stamp) >= DynamicOptimizer.timeSpan) goto L10;
        this.opt = true;
        return optimize(r7, r8, r9, r10);
    L10:
        this.runcount = 0;
        this.stamp = System.currentTimeMillis();
    L11:
        this._accessor.setValue(r7, r8, r9, r10);
        return r10;
    }
}
