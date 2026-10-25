package org.mvel2.templates.res;

import com.sun.jna.Native$$ExternalSyntheticBUOutline1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateError;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.templates.util.TemplateTools;
import org.mvel2.templates.util.TemplateTools$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class CompiledIncludeNode extends Node {
    private CompiledTemplate cFileCache;
    private Serializable cIncludeExpression;
    private Serializable cPreExpression;
    private ParserContext context;
    private long fileDateStamp;

    public CompiledIncludeNode(int r1, String r2, char[] r3, int r4, int r5, ParserContext r6) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        this.cEnd = r5 - 1;
        this.end = r5;
        this.context = r6;
        int r12 = TemplateTools.captureToEOS(r3, r4);
        char[] r22 = this.contents;
        int r32 = this.cStart;
        this.cIncludeExpression = MVEL.compileExpression(r22, r32, r12 - r32, r6);
        char[] r23 = this.contents;
        if (r12 == r23.length) goto L6;
        int r13 = r12 + 1;
        this.cPreExpression = MVEL.compileExpression(r23, r13, this.cEnd - r13, r6);
        return;
    }

    private static FileInputStream openInputStream(File r3) {
        if (r3 == null) goto L18;
        if (r3.exists() == false) goto L16;
        if (r3.isDirectory() == false) goto L8;
        Native$$ExternalSyntheticBUOutline1.m("File '", r3, "' exists but is a directory");
        return null;
    L8:
        if (r3.canRead() == true) goto L10;
        Native$$ExternalSyntheticBUOutline1.m("File '", r3, "' cannot be read");
        return null;
    L10:
        return new FileInputStream(r3);
    L16:
        throw new FileNotFoundException("File '" + r3 + "' does not exist");
    L18:
        throw new FileNotFoundException("file parameter is null");
    }

    private String readFile(TemplateRuntime r6, String r7, Object r8, VariableResolverFactory r9) {
        File r0 = new File(String.valueOf(r6.getRelPath().peek()) + "/" + r7);
        long r1 = this.fileDateStamp;
        if (r1 != 0) goto L5;
    L6:
        this.fileDateStamp = r0.lastModified();
        this.cFileCache = TemplateCompiler.compileTemplate(readInFile(r6, r0), this.context);
    L8:
        return String.valueOf(TemplateRuntime.execute(this.cFileCache, r8, r9));
    L5:
        if (r1 == r0.lastModified()) goto L8;
        goto L6
    }

    public static String readInFile(TemplateRuntime r10, File r11) {
        StringBuilder r3 = new StringBuilder();
        FileInputStream r5 = openInputStream(r11);     // Catch: Throwable -> L31 IOException -> L33 FileNotFoundException -> L37
        goto L39
    L64:
        r10.getRelPath().push(r11.getParent());     // Catch: Throwable -> L26 IOException -> L28 FileNotFoundException -> L30
        BufferedReader r6 = new BufferedReader(new InputStreamReader(r5, "UTF-8"));     // Catch: Throwable -> L26 IOException -> L28 FileNotFoundException -> L30
        boolean r7 = true;
    L61:
        String r8 = r6.readLine();     // Catch: Throwable -> L12 IOException -> L14 FileNotFoundException -> L51
        if (r8 == null) goto L16;
        if (r7 == false) goto L10;
        r7 = false;
    L11:
        r3.append(r8);     // Catch: Throwable -> L12 IOException -> L14 FileNotFoundException -> L51
        goto L61
    L10:
        r3.append('\n');     // Catch: Throwable -> L12 IOException -> L14 FileNotFoundException -> L51
        goto L11
    L16:
        r10.getRelPath().pop();     // Catch: Throwable -> L12 IOException -> L14 FileNotFoundException -> L51
        String r102 = r3.toString();     // Catch: Throwable -> L12 IOException -> L14 FileNotFoundException -> L51
        r6.close();     // Catch: IOException -> L24
        if (r5 != null) goto L52;
        return r102;
    L52:
        r5.close();     // Catch: IOException -> L21
        return r102;
    L21:
        TemplateTools$$ExternalSyntheticBUOutline0.m("cannot close the stream on template file '", r11.getPath(), "'.");
        return null;
    L24:
        TemplateTools$$ExternalSyntheticBUOutline0.m("cannot close the reader on template file '", r11.getPath(), "'.");
        return null;
    L14:
        e = e;
    L36:
        throw new TemplateError("unknown I/O exception while including '" + r11.getPath() + "' (stacktrace nested)", e);     // Catch: Throwable -> L12
    L28:
        e = e;
    L26:
        th = th;
        BufferedReader r62 = null;
    L40:
        if (r62 != null) goto L57;
    L45:
        if (r5 != null) goto L54;
    L50:
        throw th;
    L54:
        r5.close();     // Catch: IOException -> L48
    L48:
        TemplateTools$$ExternalSyntheticBUOutline0.m("cannot close the stream on template file '", r11.getPath(), "'.");
        return null;
    L57:
        r62.close();     // Catch: IOException -> L43
    L43:
        TemplateTools$$ExternalSyntheticBUOutline0.m("cannot close the reader on template file '", r11.getPath(), "'.");
        return null;
    L12:
        th = th;
    L33:
        e = e;
    L31:
        th = th;
        r5 = null;
        r62 = null;
    L39:
        throw new TemplateError("cannot include template '" + r11.getPath() + "': file not found.");     // Catch: Throwable -> L12
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r3, TemplateOutputStream r4, Object r5, VariableResolverFactory r6) {
        String r0 = (String) MVEL.executeExpression(this.cIncludeExpression, r5, r6, String.class);
        Serializable r1 = this.cPreExpression;
        if (r1 == null) goto L5;
        MVEL.executeExpression(r1, r5, r6);
    L5:
        Node r12 = this.next;
        if (r12 == null) goto L10;
        return r12.eval(r3, r4.append(String.valueOf(TemplateRuntime.eval(readFile(r3, r0, r5, r6), r5, r6))), r5, r6);
    L10:
        return r4.append(String.valueOf(MVEL.eval(readFile(r3, r0, r5, r6), r5, r6)));
    }
}
