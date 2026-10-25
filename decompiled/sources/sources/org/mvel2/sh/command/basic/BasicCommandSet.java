package org.mvel2.sh.command.basic;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.sh.Command;
import org.mvel2.sh.CommandSet;

/* loaded from: classes5.dex */
public class BasicCommandSet implements CommandSet {
    public BasicCommandSet() {
    }

    @Override // org.mvel2.sh.CommandSet
    public Map<String, Command> load() {
        HashMap r2 = new HashMap();
        r2.put("set", new Set());
        r2.put("push", new PushContext());
        r2.put("pop", new PopContext());
        r2.put("help", new Help());
        r2.put("showvars", new ShowVars());
        r2.put("showenv", new ShowEnv());
        r2.put("inspect", new ObjectInspector());
        r2.put("exit", new Exit());
        return r2;
    }
}
