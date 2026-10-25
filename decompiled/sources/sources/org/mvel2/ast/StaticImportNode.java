package org.mvel2.ast;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ArrayTools;

/* loaded from: classes5.dex */
public class StaticImportNode extends ASTNode {
    private Class declaringClass;
    private transient Method method;
    private String methodName;

    public StaticImportNode(char[] r5, int r6, int r7, ParserContext r8) {
        super(r8);
        this.expr = r5;     // Catch: Exception -> L8
        this.start = r6;     // Catch: Exception -> L8
        this.offset = r7;     // Catch: Exception -> L8
        ClassLoader r82 = getClassLoader();     // Catch: Exception -> L8
        this.expr = r5;     // Catch: Exception -> L8
        int r2 = ArrayTools.findLast('.', r6, r7, r5);     // Catch: Exception -> L8
        this.declaringClass = Class.forName(new String(r5, r6, r2 - r6), true, r82);     // Catch: Exception -> L8
        int r22 = r2 + 1;     // Catch: Exception -> L8
        this.methodName = new String(r5, r22, r7 - (r22 - r6));     // Catch: Exception -> L8
        if (resolveMethod() == null) goto L7;
        return;
    L7:
        throw new CompileException("can not find method for static import: " + this.declaringClass.getName() + "." + this.methodName, r5, r6);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("unable to import class", r5, r6, e);
        throw null;
    }

    private Method resolveMethod() {
        Method[] r0 = this.declaringClass.getMethods();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L11;
        Method r3 = r0[r2];
        if (Modifier.isStatic(r3.getModifiers()) == false) goto L10;
        if (this.methodName.equals(r3.getName()) == false) goto L10;
        this.method = r3;
        return r3;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        return null;
    }

    public Method getMethod() {
        return this.method;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return getReducedValueAccelerated(r1, r2, r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        String r12 = this.methodName;
        Method r22 = this.method;
        if (r22 != null) goto L5;
        r22 = resolveMethod();
        this.method = r22;
    L5:
        r3.createVariable(r12, r22);
        return null;
    }
}
