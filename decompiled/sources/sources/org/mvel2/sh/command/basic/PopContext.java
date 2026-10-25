package org.mvel2.sh.command.basic;

import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class PopContext implements Command {
    public PopContext() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r2, String[] r3) {
        if (r2.popCtxObject() == false) goto L6;
        System.out.println("Popped context to " + r2.getCtxObject());
    L6:
        return r2.getCtxObject();
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "pops the previous context from the context queue";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return null;
    }
}
