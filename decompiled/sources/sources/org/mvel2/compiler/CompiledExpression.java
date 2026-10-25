package org.mvel2.compiler;

import java.io.Serializable;
import org.mvel2.MVELRuntime;
import org.mvel2.ParserConfiguration;
import org.mvel2.ast.ASTNode;
import org.mvel2.ast.TypeCast;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.util.ASTLinkedList;

/* loaded from: classes5.dex */
public class CompiledExpression implements Serializable, ExecutableStatement {
    private boolean convertableIngressEgress;
    private final ASTNode firstNode;
    private Class knownEgressType;
    private Class knownIngressType;
    private final boolean literalOnly;
    private boolean optimized;
    private final ParserConfiguration parserConfiguration;
    private final String sourceName;

    public CompiledExpression(ASTLinkedList r2, String r3, Class r4, ParserConfiguration r5, boolean r6) {
        this.optimized = false;
        this.firstNode = r2.firstNode();
        this.sourceName = r3;
        if (r2.isSingleNode() == false) goto L5;
        r4 = r2.firstNonSymbol().getEgressType();
    L5:
        this.knownEgressType = r4;
        this.literalOnly = r6;
        this.parserConfiguration = r5;
    }

    private void setupOptimizers() {
        this.optimized = true;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void computeTypeConversionRule() {
        Class r0 = this.knownIngressType;
        if (r0 == null) goto L8;
        Class<?> r1 = this.knownEgressType;
        if (r1 == null) goto L9;
        this.convertableIngressEgress = r0.isAssignableFrom(r1);
        return;
    L9:
        return;
    }

    public Object getDirectValue(Object r2, VariableResolverFactory r3) {
        return MVELRuntime.execute(false, this, r2, this.parserConfiguration.getVariableFactory(r3));
    }

    public ASTNode getFirstNode() {
        return this.firstNode;
    }

    @Override // org.mvel2.compiler.ExecutableStatement, org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.knownEgressType;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Class getKnownIngressType() {
        return this.knownIngressType;
    }

    public ParserConfiguration getParserConfiguration() {
        return this.parserConfiguration;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        if (this.optimized == true) goto L12;
        setupOptimizers();
        Object r0 = getValue(r1, r3);     // Catch: Throwable -> L8
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r0;
    L8:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L12:
        return getValue(r1, r3);
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public Object getValue(Object r2, VariableResolverFactory r3) {
        if (this.optimized == true) goto L12;
        setupOptimizers();
        Object r1 = getValue(r2, r3);     // Catch: Throwable -> L8
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r1;
    L8:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L12:
        return getDirectValue(r2, r3);
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean intOptimized() {
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isConvertableIngressEgress() {
        return this.convertableIngressEgress;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isEmptyStatement() {
        if (this.firstNode != null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isExplicitCast() {
        ASTNode r0 = this.firstNode;
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0 instanceof TypeCast) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isImportInjectionRequired() {
        return this.parserConfiguration.hasImports();
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public boolean isLiteralOnly() {
        return this.literalOnly;
    }

    public boolean isSingleNode() {
        ASTNode r0 = this.firstNode;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.nextASTNode != null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownEgressType(Class r1) {
        this.knownEgressType = r1;
    }

    @Override // org.mvel2.compiler.ExecutableStatement
    public void setKnownIngressType(Class r1) {
        this.knownIngressType = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        ASTNode r2 = this.firstNode;
    L3:
        if (r2 == null) goto L6;
        r0.append(r2);
        r0.append(";\n");
        r2 = r2.nextASTNode;
        goto L3
    L6:
        return r0.toString();
    }
}
