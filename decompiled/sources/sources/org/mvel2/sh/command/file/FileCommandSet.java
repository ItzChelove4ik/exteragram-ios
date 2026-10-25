package org.mvel2.sh.command.file;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.sh.Command;
import org.mvel2.sh.CommandSet;

/* loaded from: classes5.dex */
public class FileCommandSet implements CommandSet {
    public FileCommandSet() {
    }

    @Override // org.mvel2.sh.CommandSet
    public Map<String, Command> load() {
        HashMap r2 = new HashMap();
        r2.put("ls", new DirList());
        r2.put("cd", new ChangeWorkingDir());
        r2.put("pwd", new PrintWorkingDirectory());
        return r2;
    }
}
