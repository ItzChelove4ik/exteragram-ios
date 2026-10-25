package org.aspectj.runtime.reflect;

import org.aspectj.lang.Signature;

/* loaded from: classes5.dex */
abstract class CodeSignatureImpl extends MemberSignatureImpl implements Signature {
    Class[] exceptionTypes;
    String[] parameterNames;
    Class[] parameterTypes;

    public CodeSignatureImpl(int r1, String r2, Class r3, Class[] r4, String[] r5, Class[] r6) {
        super(r1, r2, r3);
        this.parameterTypes = r4;
        this.parameterNames = r5;
        this.exceptionTypes = r6;
    }

    public Class[] getExceptionTypes() {
        if (this.exceptionTypes != null) goto L6;
        this.exceptionTypes = extractTypes(5);
    L6:
        return this.exceptionTypes;
    }

    public Class[] getParameterTypes() {
        if (this.parameterTypes != null) goto L6;
        this.parameterTypes = extractTypes(3);
    L6:
        return this.parameterTypes;
    }
}
