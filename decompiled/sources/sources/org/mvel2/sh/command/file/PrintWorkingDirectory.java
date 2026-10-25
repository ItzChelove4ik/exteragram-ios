package org.mvel2.sh.command.file;

import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class PrintWorkingDirectory implements Command {
    public PrintWorkingDirectory() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r1, String[] r2) {
        System.out.println(r1.getEnv().get("$CWD"));
        return null;
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "prints the current working directory";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "no help yet.";
    }
}
