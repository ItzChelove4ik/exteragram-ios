package org.mvel2.templates.util.io;

import java.io.IOException;
import java.io.OutputStream;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class StandardOutputStream implements TemplateOutputStream {
    private OutputStream outputStream;

    public StandardOutputStream(OutputStream r1) {
        this.outputStream = r1;
    }

    @Override // org.mvel2.templates.util.TemplateOutputStream
    public TemplateOutputStream append(CharSequence r4) {
        int r0 = 0;
    L11:
        if (r0 >= r4.length()) goto L7;
        this.outputStream.write(r4.charAt(r0));     // Catch: IOException -> L8
        r0 = r0 + 1;
        goto L11
    L7:
        return this;
    L8:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("failed to write to stream", e);
        return null;
    }

    @Override // org.mvel2.templates.util.TemplateOutputStream
    public TemplateOutputStream append(char[] r5) {
        int r0 = r5.length;     // Catch: IOException -> L7
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L6;
        char r2 = r5[r1];     // Catch: IOException -> L7
        this.outputStream.write(r2);     // Catch: IOException -> L7
        r1 = r1 + 1;
        goto L3
    L6:
        return this;
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("failed to write to stream", e);
        return null;
    }

    public String toString() {
        return null;
    }
}
