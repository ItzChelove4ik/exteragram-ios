package org.aspectj.runtime.reflect;

import org.aspectj.lang.reflect.MethodSignature;

/* loaded from: classes5.dex */
class MethodSignatureImpl extends CodeSignatureImpl implements MethodSignature {
    Class returnType;

    public MethodSignatureImpl(int r1, String r2, Class r3, Class[] r4, String[] r5, Class[] r6, Class r7) {
        super(r1, r2, r3, r4, r5, r6);
        this.returnType = r7;
    }

    @Override // org.aspectj.runtime.reflect.SignatureImpl
    public String createToString(StringMaker r4) {
        StringBuffer r0 = new StringBuffer();
        r0.append(r4.makeModifiersString(getModifiers()));
        if (r4.includeArgs == false) goto L6;
        r0.append(r4.makeTypeName(getReturnType()));
    L6:
        if (r4.includeArgs == false) goto L8;
        r0.append(" ");
    L8:
        r0.append(r4.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        r0.append(".");
        r0.append(getName());
        r4.addSignature(r0, getParameterTypes());
        r4.addThrows(r0, getExceptionTypes());
        return r0.toString();
    }

    public Class getReturnType() {
        if (this.returnType != null) goto L6;
        this.returnType = extractType(6);
    L6:
        return this.returnType;
    }
}
