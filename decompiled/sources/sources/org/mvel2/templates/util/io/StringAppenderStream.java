package org.mvel2.templates.util.io;

import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class StringAppenderStream implements TemplateOutputStream {
    private StringAppender appender;

    public StringAppenderStream(StringAppender r1) {
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
