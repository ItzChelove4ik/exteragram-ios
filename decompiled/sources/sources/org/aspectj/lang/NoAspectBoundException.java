package org.aspectj.lang;

/* loaded from: classes5.dex */
public class NoAspectBoundException extends RuntimeException {
    Throwable cause;

    public NoAspectBoundException(String r3, Throwable r4) {
        if (r4 == null) goto L5;
        StringBuffer r0 = new StringBuffer("Exception while initializing ");
        r0.append(r3);
        r0.append(": ");
        r0.append(r4);
        r3 = r0.toString();
    L5:
        super(r3);
        this.cause = r4;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }
}
