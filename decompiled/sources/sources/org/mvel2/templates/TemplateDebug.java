package org.mvel2.templates;

import java.io.PrintStream;
import org.mvel2.templates.res.Node;

/* loaded from: classes5.dex */
public class TemplateDebug {
    public TemplateDebug() {
    }

    public static void decompile(CompiledTemplate r7, char[] r8) {
        Node r72 = r7.getRoot();
        int r0 = 1;
    L3:
        if (r72 == null) goto L5;
        PrintStream r1 = System.out;
        StringBuilder r2 = new StringBuilder();
        int r3 = r0 + 1;
        r2.append(r0);
        r2.append("> ");
        r2.append(r72.toString());
        r2.append("['");
        r2.append(new String(r8, r72.getBegin(), r72.getEnd() - r72.getBegin()));
        r2.append("']");
        r1.println(r2.toString());
        r72 = r72.getNext();
        r0 = r3;
        goto L3
    }
}
