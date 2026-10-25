package org.mvel2.sh.command.basic;

import java.util.Iterator;
import java.util.Map;
import org.mvel2.sh.Command;
import org.mvel2.sh.ShellSession;
import org.mvel2.util.StringAppender;

/* loaded from: classes5.dex */
public class Set implements Command {
    public Set() {
    }

    @Override // org.mvel2.sh.Command
    public Object execute(ShellSession r5, String[] r6) {
        Map<String, String> r4 = r5.getEnv();
        int r52 = r6.length;
        if (r52 == 0) goto L17;
        int r2 = 1;
        if (r52 == 1) goto L14;
        StringAppender r53 = new StringAppender();
    L8:
        if (r2 >= r6.length) goto L13;
        r53.append(r6[r2]);
        if (r2 >= r6.length) goto L12;
        r53.append(" ");
    L12:
        r2 = r2 + 1;
        goto L8
    L13:
        r4.put(r6[0], r53.toString().trim());
        return null;
    L14:
        String r42 = r4.remove(r6[0]);
        if (r42 == null) goto L27;
        System.out.println("Removed " + r6[0] + " = " + r42);
        return null;
    L27:
        return null;
    L17:
        Iterator<String> r54 = r4.keySet().iterator();
    L19:
        if (r54.hasNext() == false) goto L29;
        String r62 = r54.next();
        System.out.println(r62 + " = " + r4.get(r62));
        goto L19
    L29:
        return null;
    }

    @Override // org.mvel2.sh.Command
    public String getDescription() {
        return "sets an environment variable";
    }

    @Override // org.mvel2.sh.Command
    public String getHelp() {
        return null;
    }
}
