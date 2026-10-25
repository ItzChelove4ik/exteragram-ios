package org.mvel2.util;

import java.lang.reflect.Method;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class MethodStub implements StaticStub {
    private Class classReference;
    private transient Method method;
    private String name;

    public MethodStub(Class r1, String r2) {
        this.classReference = r1;
        this.name = r2;
    }

    public MethodStub(Method r2) {
        this.classReference = r2.getDeclaringClass();
        this.name = r2.getName();
    }

    @Override // org.mvel2.util.StaticStub
    public Object call(Object r1, Object r2, VariableResolverFactory r3, Object[] r4) {
        return this.method.invoke(r1, r4);
    }

    public Class getClassReference() {
        return this.classReference;
    }

    public Method getMethod() {
        if (this.method != null) goto L12;
        Method[] r0 = this.classReference.getMethods();
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L12;
        Method r3 = r0[r2];
        if (this.name.equals(r3.getName()) == true) goto L8;
        r2 = r2 + 1;
        goto L5
    L8:
        this.method = r3;
        return r3;
    L12:
        return this.method;
    }

    public String getMethodName() {
        return this.name;
    }

    public void setClassReference(Class r1) {
        this.classReference = r1;
    }

    public void setMethodName(String r1) {
        this.name = r1;
    }
}
