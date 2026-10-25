package org.scilab.forge.jlatexmath;

import java.util.HashMap;
import java.util.regex.Matcher;

/* loaded from: classes5.dex */
public class NewCommandMacro {
    protected static HashMap<String, String> macrocode;
    protected static HashMap<String, String> macroreplacement;

    static {
        macrocode = new HashMap();
        macroreplacement = new HashMap();
    }

    public NewCommandMacro() {
    }

    public static void addNewCommand(String r3, String r4, int r5) {
        macrocode.put(r3, r4);
        MacroInfo.Commands.put(r3, new MacroInfo("org.scilab.forge.jlatexmath.NewCommandMacro", "executeMacro", r5));
    }

    public static void addNewCommand(String r3, String r4, int r5, String r6) {
        if (macrocode.get(r3) != null) goto L6;
        macrocode.put(r3, r4);
        macroreplacement.put(r3, r6);
        MacroInfo.Commands.put(r3, new MacroInfo("org.scilab.forge.jlatexmath.NewCommandMacro", "executeMacro", r5, 1.0f));
        return;
    L6:
        TeXParser$$ExternalSyntheticBUOutline0.m("Command ", r3, " already exists ! Use renewcommand instead ...");
    }

    public static void addReNewCommand(String r3, String r4, int r5) {
        if (macrocode.get(r3) == null) goto L6;
        macrocode.put(r3, r4);
        MacroInfo.Commands.put(r3, new MacroInfo("org.scilab.forge.jlatexmath.NewCommandMacro", "executeMacro", r5));
        return;
    L6:
        TeXParser$$ExternalSyntheticBUOutline0.m("Command ", r3, " is not defined ! Use newcommand instead ...");
    }

    public static boolean isMacro(String r1) {
        return macrocode.containsKey(r1);
    }

    public static void reset() {
        macrocode.clear();
        macroreplacement.clear();
    }

    public String executeMacro(TeXParser r6, String[] r7) {
        int r62 = 0;
        String r5 = macrocode.get(r7[0]);
        int r0 = r7.length;
        int r1 = r0 - 11;
        String r02 = r7[r0 - 10];
        int r3 = 1;
        if (r02 == null) goto L7;
        r5 = r5.replaceAll("#1", Matcher.quoteReplacement(r02));
    L5:
        r62 = 1;
    L9:
        if (r3 > r1) goto L11;
        r5 = r5.replaceAll("#" + (r3 + r62), Matcher.quoteReplacement(r7[r3]));
        r3 = r3 + 1;
        goto L9
    L11:
        return r5;
    L7:
        if (macroreplacement.get(r7[0]) == null) goto L9;
        r5 = r5.replaceAll("#1", Matcher.quoteReplacement(macroreplacement.get(r7[0])));
        goto L5
    }
}
