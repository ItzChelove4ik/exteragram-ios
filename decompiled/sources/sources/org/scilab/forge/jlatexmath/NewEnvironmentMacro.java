package org.scilab.forge.jlatexmath;

/* loaded from: classes5.dex */
public class NewEnvironmentMacro extends NewCommandMacro {
    public NewEnvironmentMacro() {
    }

    public static void addNewEnvironment(String r1, String r2, String r3, int r4) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r2);
        r0.append(" #");
        int r42 = r4 + 1;
        r0.append(r42);
        r0.append(" ");
        r0.append(r3);
        NewCommandMacro.addNewCommand(r1 + "@env", r0.toString(), r42);
    }

    public static void addReNewEnvironment(String r3, String r4, String r5, int r6) {
        if (NewCommandMacro.macrocode.get(r3 + "@env") == null) goto L6;
        StringBuilder r0 = new StringBuilder();
        r0.append(r4);
        r0.append(" #");
        int r62 = r6 + 1;
        r0.append(r62);
        r0.append(" ");
        r0.append(r5);
        NewCommandMacro.addReNewCommand(r3 + "@env", r0.toString(), r62);
        return;
    L6:
        TeXParser$$ExternalSyntheticBUOutline0.m("Environment ", r3, "is not defined ! Use newenvironment instead ...");
    }
}
