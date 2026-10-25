package org.mvel2.integration.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolver;
import org.mvel2.util.MethodStub;

/* loaded from: classes5.dex */
public class StaticMethodImportResolverFactory extends BaseVariableResolverFactory {
    public StaticMethodImportResolverFactory() {
        this.variableResolvers = new HashMap();
    }

    public StaticMethodImportResolverFactory(ParserContext r3) {
        this.variableResolvers = new HashMap();
        Iterator<Map.Entry<String, Object>> r32 = r3.getImports().entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        Map.Entry<String, Object> r0 = r32.next();
        if ((r0.getValue() instanceof Method) == false) goto L4;
        createVariable(r0.getKey(), r0.getValue());
        goto L4
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r2, Object r3) {
        if ((r3 instanceof Method) == false) goto L5;
        r3 = new MethodStub((Method) r3);
    L5:
        StaticMethodImportResolver r0 = new StaticMethodImportResolver(r2, (MethodStub) r3);
        this.variableResolvers.put(r2, r0);
        return r0;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2, Class<?> r3) {
        return null;
    }

    public Map<String, Method> getImportedMethods() {
        HashMap r0 = new HashMap();
        Iterator<Map.Entry<String, VariableResolver>> r3 = this.variableResolvers.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<String, VariableResolver> r1 = r3.next();
        r0.put(r1.getKey(), (Method) r1.getValue().getValue());
        goto L4
    L6:
        return r0;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r2) {
        if (isTarget(r2) == false) goto L5;
        return true;
    L5:
        if (isNextResolveable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r1) {
        return this.variableResolvers.containsKey(r1);
    }
}
