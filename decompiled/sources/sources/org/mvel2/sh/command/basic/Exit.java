package org.mvel2.sh.command.basic;

import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class Exit implements Command {
    public Exit() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r1, String[] r2) {
        System.exit(0);
        return null;
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "exits the command shell";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "No help yet.";
    }
}
