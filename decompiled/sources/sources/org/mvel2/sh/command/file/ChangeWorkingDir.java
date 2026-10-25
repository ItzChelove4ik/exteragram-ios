package org.mvel2.sh.command.file;

import java.io.File;
import org.mvel2.sh.Command;
import org.mvel2.sh.CommandException;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class ChangeWorkingDir implements Command {
    public ChangeWorkingDir() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r7, String[] r8) {
        File r6 = new File(r7.getEnv().get("$CWD"));
        if (r8.length != 0) goto L5;
    L28:
        return null;
    L5:
        if (".".equals(r8[0]) == true) goto L28;
        if ("..".equals(r8[0]) == false) goto L15;
        if (r6.getParentFile() == null) goto L13;
        File r62 = r6.getParentFile();
    L24:
        r7.getEnv().put("$CWD", r62.getAbsolutePath());
        return null;
    L13:
        throw new CommandException("already at top-level directory");
    L15:
        if (r8[0].charAt(0) != '/') goto L21;
        r62 = new File(r8[0]);
        if (r62.exists() == true) goto L24;
        throw new CommandException("no such directory: " + r8[0]);
    L21:
        File r0 = new File(r6.getAbsolutePath() + "/" + r8[0]);
        if (r0.exists() == false) goto L27;
        r62 = r0;
        goto L24
    L27:
        throw new CommandException("no such directory: " + r8[0]);
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "changes the working directory";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "no help yet";
    }
}
