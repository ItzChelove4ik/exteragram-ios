package org.mvel2.templates.res;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateError;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.templates.util.TemplateTools;

/* loaded from: classes5.dex */
public class IncludeNode extends Node {
    int includeOffset;
    int includeStart;
    int preOffset;
    int preStart;

    public IncludeNode(int r1, String r2, char[] r3, int r4, int r5) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
        int r12 = TemplateTools.captureToEOS(r3, 0);
        int r22 = this.cStart;
        this.includeStart = r22;
        this.includeOffset = r12 - r22;
        int r13 = r12 + 1;
        this.preStart = r13;
        this.preOffset = this.cEnd - r13;
    }

    public static String readInFile(TemplateRuntime r7, String r8) {
        File r0 = new File(String.valueOf(r7.getRelPath().peek()) + "/" + r8);
        FileInputStream r1 = new FileInputStream(r0);     // Catch: IOException -> L9 FileNotFoundException -> L15
        BufferedInputStream r2 = new BufferedInputStream(r1);     // Catch: IOException -> L9 FileNotFoundException -> L15
        r7.getRelPath().push(r0.getParent());     // Catch: IOException -> L9 FileNotFoundException -> L15
        byte[] r02 = new byte[10];     // Catch: IOException -> L9 FileNotFoundException -> L15
        StringBuilder r3 = new StringBuilder();     // Catch: IOException -> L9 FileNotFoundException -> L15
    L4:
        int r4 = r2.read(r02);     // Catch: IOException -> L9 FileNotFoundException -> L15
        if (r4 == (-1)) goto L11;
        int r5 = 0;
    L7:
        if (r5 >= r4) goto L4;
        r3.append((char) r02[r5]);     // Catch: IOException -> L9 FileNotFoundException -> L15
        r5 = r5 + 1;     // Catch: IOException -> L9 FileNotFoundException -> L15
        goto L7
    L11:
        r2.close();     // Catch: IOException -> L9 FileNotFoundException -> L15
        r1.close();     // Catch: IOException -> L9 FileNotFoundException -> L15
        r7.getRelPath().pop();     // Catch: IOException -> L9 FileNotFoundException -> L15
        return r3.toString();
    L9:
        e = move-exception;
        throw new TemplateError("unknown I/O exception while including '" + r8 + "' (stacktrace nested)", e);
    L16:
        throw new TemplateError("cannot include template '" + r8 + "': file not found.");
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r7, TemplateOutputStream r8, Object r9, VariableResolverFactory r10) {
        String r92 = (String) MVEL.eval(this.contents, this.includeStart, this.includeOffset, r9, r10, String.class);
        int r102 = this.preOffset;
        if (r102 == 0) goto L5;
        MVEL.eval(this.contents, this.preStart, r102, r9, r10);
    L5:
        Node r6 = this.next;
        if (r6 == null) goto L10;
        return r6.eval(r7, r8.append(String.valueOf(TemplateRuntime.eval(readInFile(r7, r92), r9, r10))), r9, r10);
    L10:
        return r8.append(String.valueOf(MVEL.eval(readInFile(r7, r92), r9, r10)));
    }
}
