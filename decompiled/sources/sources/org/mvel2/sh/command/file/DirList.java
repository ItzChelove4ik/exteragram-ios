package org.mvel2.sh.command.file;

import java.io.File;
import org.mvel2.sh.Command;
import org.mvel2.sh.CommandException;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class DirList implements Command {
    public DirList() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r5, String[] r6) {
        File r4 = new File(r5.getEnv().get("$CWD"));
        if (r4.isDirectory() == false) goto L17;
        File[] r52 = r4.listFiles();
        if (r52.length != 0) goto L7;
        return null;
    L7:
        System.out.append("Total ").append(String.valueOf(r52.length)).append("\n");
        File[] r42 = r4.listFiles();
        int r53 = r42.length;
        int r1 = 0;
    L8:
        if (r1 >= r53) goto L14;
        File r2 = r42[r1];
        if (r2.isDirectory() == false) goto L12;
        System.out.append(r2.getName()).append("/");
    L13:
        System.out.append("\n");
        r1 = r1 + 1;
        goto L8
    L12:
        System.out.append(r2.getName());
        goto L13
    L14:
        System.out.flush();
        return null;
    L17:
        throw new CommandException("cannot list directory : " + r5.getEnv().get("$CWD") + " is not a directory");
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "performs a list of files and directories in the current working dir.";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "no help yet";
    }
}
