package org.mvel2.sh.command.basic;

import com.exteragram.messenger.backup.InvisibleEncryptor$$ExternalSyntheticBackport0;
import org.mvel2.MVEL;
import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class PushContext implements Command {
    public PushContext() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r3, String[] r4) {
        if (r4.length != 0) goto L5;
        boolean r2 = r3.pushCtxObject(null);
    L12:
        Object r32 = r3.getCtxObject();
        if (r2 == false) goto L15;
        System.out.println("Pushed context to " + r32);
    L15:
        return r32;
    L5:
        r2 = false;
        Object r42 = MVEL.eval(InvisibleEncryptor$$ExternalSyntheticBackport0.m(" ", r4), r3.getCtxObject(), r3.getVariables());     // Catch: Exception -> L10
        if (r42 == null) goto L12;
        r2 = r3.pushCtxObject(r42);     // Catch: Exception -> L10
    L10:
        e = move-exception;
        System.err.println(e.getMessage());
        goto L12
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "pushes the given variable to the current context";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return null;
    }
}
