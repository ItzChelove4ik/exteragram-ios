package org.mvel2.sh.command.basic;

import java.util.Iterator;
import java.util.Map;
import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;

/* loaded from: classes5.dex */
public class ShowEnv implements Command {
    public ShowEnv() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r4, String[] r5) {
        Map<String, String> r3 = r4.getEnv();
        System.out.println("Printing Environment ...");
        Iterator<String> r42 = r3.keySet().iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        String r52 = r42.next();
        System.out.println(r52 + " => " + r3.get(r52));
        goto L4
    L6:
        System.out.println(" ** " + r3.size() + " environment variables total.");
        return null;
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "shows current environment";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return "no help yet";
    }
}
