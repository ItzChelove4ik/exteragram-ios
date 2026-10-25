package org.mvel2.compiler;

import java.io.Serializable;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class CompiledAccExpression implements ExecutableStatement, Serializable {
    private transient Accessor accessor;
    private ParserContext context;
    private char[] expression;
    private Class ingressType;
    private int offset;
    private int start;

    public CompiledAccExpression(char[] r1, int r2, int r3, Class r4, ParserContext r5) {
        this.expression = r1;
        this.start = r2;
        this.offset = r3;
        this.context = r5;
        if (r4 != null) goto L6;
        r4 = Object.class;
    L6:
        this.ingressType = r4;
    }

    public CompiledAccExpression(char[] r7, Class r8, ParserContext r9) {
        this(r7, 0, r7.length, r8, r9);
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void computeTypeConversionRule() {
    }

    public Accessor getAccessor() {
        return this.accessor;
    }

    @Override // org.mvel2.compiler.ExecutableStatement, org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return null;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Class getKnownIngressType() {
        return this.ingressType;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r12, Object r13, VariableResolverFactory r14) {
        Accessor r0 = this.accessor;
        if (r0 != null) goto L11;
        this.accessor = OptimizerFactory.getThreadAccessorOptimizer().optimizeAccessor(this.context, this.expression, this.start, this.offset, r12, r13, r14, false, this.ingressType);     // Catch: Throwable -> L7
        Object r11 = getValue(r12, r13, r14);     // Catch: Throwable -> L7
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r11;
    L7:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L11:
        return r0.getValue(r12, r13, r14);
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Object getValue(Object r12, VariableResolverFactory r13) {
        Accessor r0 = this.accessor;
        if (r0 != null) goto L11;
        AccessorOptimizer r1 = OptimizerFactory.getThreadAccessorOptimizer();     // Catch: Throwable -> L7
        ParserContext r2 = this.context;     // Catch: Throwable -> L7
        char[] r3 = this.expression;     // Catch: Throwable -> L7
        this.accessor = r1.optimizeAccessor(r2, r3, 0, r3.length, r12, r12, r13, false, this.ingressType);     // Catch: Throwable -> L7
        Object r11 = getValue(r12, r13);     // Catch: Throwable -> L7
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r11;
    L7:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L11:
        return r0.getValue(r12, r12, r13);
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean intOptimized() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isConvertableIngressEgress() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isEmptyStatement() {
        if (this.accessor != null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isExplicitCast() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isLiteralOnly() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownEgressType(Class r1) {
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownIngressType(Class r1) {
        this.ingressType = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r12, Object r13, VariableResolverFactory r14, Object r15) {
        Accessor r0 = this.accessor;
        if (r0 == null) goto L5;
        r0.setValue(r12, r13, r14, r15);
        return r15;
    L5:
        if (this.ingressType != Object.class) goto L8;
        if (r15 == null) goto L8;
        this.ingressType = r15.getClass();
    L8:
        AccessorOptimizer r02 = OptimizerFactory.getThreadAccessorOptimizer();
        ParserContext r1 = this.context;
        char[] r2 = this.expression;
        this.accessor = r02.optimizeSetAccessor(r1, r2, 0, r2.length, r12, r12, r14, false, r15, this.ingressType);
        return r15;
    }
}
