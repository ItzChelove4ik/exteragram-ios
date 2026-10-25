package org.mvel2.sh;

/* loaded from: classes5.dex */
public interface Command {
    Object execute(ShellSession r1, String[] r2);

    String getDescription();

    String getHelp();
}
