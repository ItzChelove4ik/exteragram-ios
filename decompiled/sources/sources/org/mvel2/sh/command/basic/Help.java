package org.mvel2.sh.command.basic;

import java.util.Iterator;
import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;
import org.mvel2.sh.text.TextUtil;

/* loaded from: classes5.dex */
public class Help implements Command {
    public Help() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r5, String[] r6) {
        Iterator<String> r4 = r5.getCommands().keySet().iterator();
    L4:
        if (r4.hasNext() == false) goto L6;
        String r62 = r4.next();
        System.out.println(r62 + TextUtil.pad(r62.length(), 25) + "- " + r5.getCommands().get(r62).getDescription());
        goto L4
    L6:
        return null;
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "displays help for available shell commands";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "No help yet";
    }
}
