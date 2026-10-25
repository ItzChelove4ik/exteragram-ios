package org.mvel2.sh.command.basic;

import java.util.Iterator;
import java.util.Map;
import org.mvel2.sh.Command;
import org.mvel2.sh.CommandException;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class ShowVars implements Command {
    public ShowVars() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r4, String[] r5) {
        Map<String, Object> r3 = r4.getVariables();
        int r42 = 0;
        boolean r0 = false;
    L4:
        if (r42 >= r5.length) goto L10;
        if ("-values".equals(r5[r42]) == false) goto L9;
        r42 = r42 + 1;
        r0 = true;
        goto L4
    L9:
        throw new CommandException("unknown argument: " + r5[r42]);
    L10:
        System.out.println("Printing Variables ...");
        if (r0 == false) goto L16;
        Iterator<String> r43 = r3.keySet().iterator();
    L14:
        if (r43.hasNext() == false) goto L20;
        String r52 = r43.next();
        System.out.println(r52 + " => " + String.valueOf(r3.get(r52)));
    L20:
        System.out.println(" ** " + r3.size() + " variables total.");
        return null;
    L16:
        Iterator<String> r44 = r3.keySet().iterator();
    L18:
        if (r44.hasNext() == false) goto L20;
        System.out.println(r44.next());
        goto L18
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "shows current variables";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "no help yet";
    }
}
