package org.mvel2.templates.util.io;

import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class StringBuilderStream implements TemplateOutputStream {
    private StringBuilder appender;

    public StringBuilderStream(StringBuilder r1) {
        this.appender = r1;
    }

    @Override // org.mvel2.templates.util.TemplateOutputStream
    public TemplateOutputStream append(CharSequence r2) {
        this.appender.append(r2);
        return this;
    }

    @Override // org.mvel2.templates.util.TemplateOutputStream
    public TemplateOutputStream append(char[] r2) {
        this.appender.append(r2);
        return this;
    }

    public String toString() {
        return this.appender.toString();
    }
}
