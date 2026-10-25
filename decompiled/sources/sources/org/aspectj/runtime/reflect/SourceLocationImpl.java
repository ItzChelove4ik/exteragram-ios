package org.aspectj.runtime.reflect;

import org.aspectj.lang.reflect.SourceLocation;

/* loaded from: classes5.dex */
class SourceLocationImpl implements SourceLocation {
    String fileName;
    int line;
    Class withinType;

    public SourceLocationImpl(Class r1, String r2, int r3) {
        this.withinType = r1;
        this.fileName = r2;
        this.line = r3;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getLine() {
        return this.line;
    }

    public String toString() {
        StringBuffer r0 = new StringBuffer();
        r0.append(getFileName());
        r0.append(":");
        r0.append(getLine());
        return r0.toString();
    }
}
