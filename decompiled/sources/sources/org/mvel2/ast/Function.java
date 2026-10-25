package org.mvel2.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.compiler.ExpressionCompiler;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.DefaultLocalVariableResolverFactory;
import org.mvel2.integration.impl.FunctionVariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;
import org.mvel2.integration.impl.StackDemarcResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Function extends ASTNode implements Safe {
    protected ExecutableStatement compiledBlock;
    protected boolean compiledMode;
    protected String name;
    protected String[] parameters;
    protected int parmNum;
    protected boolean singleton;

    public Function(String r7, char[] r8, int r9, int r10, int r11, int r12, int r13, ParserContext r14) {
        super(r14);
        boolean r0 = false;
        this.compiledMode = false;
        this.name = r7;
        if (r7 != null) goto L5;
    L6:
        this.name = null;
    L7:
        this.expr = r8;
        String[] r92 = ParseTools.parseParameterDefList(r8, r9, r10);
        this.parameters = r92;
        this.parmNum = r92.length;
        ParserContext r93 = new ParserContext(r14.getParserConfiguration(), r14, true);
        if (r14.isFunctionContext() == true) goto L10;
        this.singleton = true;
        r14.declareFunction(this);
    L11:
        String[] r102 = this.parameters;
        int r2 = r102.length;
        int r3 = 0;
    L12:
        if (r3 >= r2) goto L14;
        String r4 = r102[r3];
        r93.addVariable(r4, Object.class);
        r93.addIndexedInput(r4);
        r3 = r3 + 1;
        goto L12
    L14:
        r93.setIndexAllocation(false);
        ExpressionCompiler r103 = new ExpressionCompiler(r8, r11, r12, r93);
        r103.setVerifyOnly(true);
        r103.compile();
        r93.setIndexAllocation(true);
        if (r14.getVariables() == null) goto L21;
        Iterator<Map.Entry<String, Class>> r104 = r14.getVariables().entrySet().iterator();
    L18:
        if (r104.hasNext() == false) goto L20;
        Map.Entry<String, Class> r22 = r104.next();
        r93.getVariables().remove(r22.getKey());
        r93.addInput(r22.getKey(), r22.getValue());
        goto L18
    L20:
        r93.processTables();
    L21:
        r93.addIndexedInputs(r93.getVariables().keySet());
        r93.getVariables().clear();
        this.compiledBlock = (ExecutableStatement) ParseTools.subCompileExpression(r8, r11, r12, r93);
        this.parameters = new String[r93.getIndexedInputs().size()];
        ArrayList<String> r82 = r93.getIndexedInputs();
        int r94 = r82.size();
        int r105 = 0;
        int r112 = 0;
    L22:
        if (r112 >= r94) goto L25;
        String r122 = r82.get(r112);
        r112 = r112 + 1;
        this.parameters[r105] = r122;
        r105 = r105 + 1;
        goto L22
    L25:
        if ((r13 & 16) == 0) goto L27;
        r0 = true;
    L27:
        this.compiledMode = r0;
        this.egressType = this.compiledBlock.getKnownEgressType();
        r14.addVariable(r7, Function.class);
        return;
    L10:
        r93.declareFunction(this);
        goto L11
    L5:
        if (r7.length() != 0) goto L7;
        goto L6
    }

    public Object call(Object r4, Object r5, VariableResolverFactory r6, Object[] r7) {
        if (r7 != null) goto L4;
    L20:
        boolean r42 = this.compiledMode;
        ExecutableStatement r72 = this.compiledBlock;
        if (r42 == false) goto L25;
        return r72.getValue(r5, new StackDemarcResolverFactory(new DefaultLocalVariableResolverFactory(r6, this.parameters)));
    L25:
        return r72.getValue(r5, new StackDemarcResolverFactory(new DefaultLocalVariableResolverFactory(r6, this.parameters)));
    L4:
        if (r7.length == 0) goto L20;
        if ((r6 instanceof FunctionVariableResolverFactory) == false) goto L19;
        FunctionVariableResolverFactory r0 = (FunctionVariableResolverFactory) r6;
        if (r0.getIndexedVariableResolvers().length != r7.length) goto L19;
        if (r0.getFunction().equals(this) == false) goto L19;
        VariableResolver[] r62 = r0.getIndexedVariableResolvers();
        r0.updateParameters(r7);
        Object r3 = this.compiledBlock.getValue(r4, r5, r0);     // Catch: Throwable -> L15
        r0.setIndexedVariableResolvers(r62);
        return r3;
    L15:
        th = move-exception;
        r0.setIndexedVariableResolvers(r62);
        throw th;
    L19:
        return this.compiledBlock.getValue(r5, new StackDemarcResolverFactory(new FunctionVariableResolverFactory(this, r6, this.parameters, r7)));
    }

    public void checkArgumentCount(int r4) {
        if (r4 == this.parmNum) goto L10;
        StringBuilder r1 = new StringBuilder("bad number of arguments in function call: ");
        r1.append(r4);
        r1.append(" (expected: ");
        int r42 = this.parmNum;
        if (r42 != 0) goto L7;
        Object r43 = "none";
    L8:
        r1.append(r43);
        r1.append(")");
        throw new CompileException(r1.toString(), this.expr, this.start);
    L7:
        r43 = Integer.valueOf(r42);
        goto L8
    }

    public ExecutableStatement getCompiledBlock() {
        return this.compiledBlock;
    }

    @Override // org.mvel2.ast.ASTNode
    public String getName() {
        return this.name;
    }

    public String[] getParameters() {
        return this.parameters;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        PrototypalFunctionInstance r12 = new PrototypalFunctionInstance(this, new MapVariableResolverFactory());
        if (this.name != null) goto L5;
    L12:
        return r12;
    L5:
        if (r3.isIndexedFactory() == false) goto L7;
    L11:
        r3.createVariable(this.name, r12);
        goto L12
    L7:
        if (r3.isResolveable(this.name) == false) goto L11;
        Sign$$ExternalSyntheticBUOutline0.m("duplicate function: " + this.name, this.expr, this.start);
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        PrototypalFunctionInstance r12 = new PrototypalFunctionInstance(this, new MapVariableResolverFactory());
        if (this.name != null) goto L5;
    L12:
        return r12;
    L5:
        if (r3.isIndexedFactory() == false) goto L7;
    L11:
        r3.createVariable(this.name, r12);
        goto L12
    L7:
        if (r3.isResolveable(this.name) == false) goto L11;
        Sign$$ExternalSyntheticBUOutline0.m("duplicate function: " + this.name, this.expr, this.start);
        return null;
    }

    public boolean hasParameters() {
        String[] r0 = this.parameters;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.length == 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void setName(String r1) {
        this.name = r1;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        StringBuilder r0 = new StringBuilder("FunctionDef:");
        String r2 = this.name;
        if (r2 != null) goto L5;
        r2 = "Anonymous";
    L5:
        r0.append(r2);
        return r0.toString();
    }
}
