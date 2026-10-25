package com.android.dex.util;

import c.f$$ExternalSyntheticBUOutline2;
import java.io.PrintStream;
import java.io.PrintWriter;

/* loaded from: classes4.dex */
public class ExceptionWithContext extends RuntimeException {
    private StringBuffer context;

    public ExceptionWithContext(String r2) {
        this(r2, null);
    }

    public ExceptionWithContext(String r3, Throwable r4) {
        if (r3 != null) goto L7;
        if (r4 == null) goto L6;
        r3 = r4.getMessage();
        goto L7
    L6:
        r3 = null;
    L7:
        super(r3, r4);
        if ((r4 instanceof ExceptionWithContext) == false) goto L11;
        String r32 = ((ExceptionWithContext) r4).context.toString();
        StringBuffer r42 = new StringBuffer(r32.length() + 200);
        this.context = r42;
        r42.append(r32);
        return;
    L11:
        this.context = new StringBuffer(200);
    }

    public ExceptionWithContext(Throwable r2) {
        this(null, r2);
    }

    public static ExceptionWithContext withContext(Throwable r1, String r2) {
        if ((r1 instanceof ExceptionWithContext) == false) goto L5;
        ExceptionWithContext r12 = (ExceptionWithContext) r1;
    L6:
        r12.addContext(r2);
        return r12;
    L5:
        r12 = new ExceptionWithContext(r1);
        goto L6
    }

    public void addContext(String r2) {
        if (r2 == null) goto L7;
        this.context.append(r2);
        if (r2.endsWith("\n") == true) goto L9;
        this.context.append('\n');
        return;
    L9:
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("str == null");
    }

    public String getContext() {
        return this.context.toString();
    }

    public void printContext(PrintStream r2) {
        r2.println(getMessage());
        r2.print(this.context);
    }

    public void printContext(PrintWriter r2) {
        r2.println(getMessage());
        r2.print(this.context);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream r1) {
        super.printStackTrace(r1);
        r1.println(this.context);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter r1) {
        super.printStackTrace(r1);
        r1.println(this.context);
    }
}
