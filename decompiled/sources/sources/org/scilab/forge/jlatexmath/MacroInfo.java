package org.scilab.forge.jlatexmath;

import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class MacroInfo {
    public static HashMap<String, MacroInfo> Commands;
    public static HashMap<String, Object> Packages;
    public boolean hasOptions;
    public Method macro;
    public int nbArgs;
    public Object pack;
    public int posOpts;

    static {
        Commands = new HashMap(300);
        Packages = new HashMap();
    }

    public MacroInfo(int r2) {
        this(null, null, r2);
    }

    public MacroInfo(int r2, int r3) {
        this(null, null, r2);
        this.hasOptions = true;
        this.posOpts = r3;
    }

    public MacroInfo(Object r2, Method r3, int r4) {
        this.hasOptions = false;
        this.pack = r2;
        this.macro = r3;
        this.nbArgs = r4;
    }

    public MacroInfo(Object r1, Method r2, int r3, int r4) {
        this(r1, r2, r3);
        this.hasOptions = true;
        this.posOpts = r4;
    }

    public MacroInfo(String r4, String r5, float r6) {
        this.hasOptions = false;
        int r62 = (int) r6;
        Class<?>[] r0 = {TeXParser.class, String[].class};
        Object r1 = Packages.get(r4);     // Catch: Exception -> L6
        if (r1 != null) goto L8;
        r1 = Class.forName(r4).getConstructor(null).newInstance(null);     // Catch: Exception -> L6
        Packages.put(r4, r1);     // Catch: Exception -> L6
    L8:
        this.pack = r1;     // Catch: Exception -> L6
        this.macro = r1.getClass().getDeclaredMethod(r5, r0);     // Catch: Exception -> L6
        this.nbArgs = r62;     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        System.err.println("Cannot load package " + r4 + ":");
        System.err.println(e.toString());
    }

    public MacroInfo(String r4, String r5, float r6, float r7) {
        this.hasOptions = false;
        int r62 = (int) r6;
        Class<?>[] r0 = {TeXParser.class, String[].class};
        Object r1 = Packages.get(r4);     // Catch: Exception -> L6
        if (r1 != null) goto L8;
        r1 = Class.forName(r4).getConstructor(null).newInstance(null);     // Catch: Exception -> L6
        Packages.put(r4, r1);     // Catch: Exception -> L6
    L8:
        this.pack = r1;     // Catch: Exception -> L6
        this.macro = r1.getClass().getDeclaredMethod(r5, r0);     // Catch: Exception -> L6
        this.nbArgs = r62;     // Catch: Exception -> L6
        this.hasOptions = true;     // Catch: Exception -> L6
        this.posOpts = (int) r7;     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        System.err.println("Cannot load package " + r4 + ":");
        System.err.println(e.toString());
    }

    public Object invoke(TeXParser r8, String[] r9) {
        return this.macro.invoke(this.pack, new Object[]{r8, r9});
    L9:
        e = move-exception;
        throw new ParseException("Problem with command " + r9[0] + " at position " + r8.getLine() + ":" + r8.getCol() + "\n", e);
    L7:
        e = move-exception;
        throw new ParseException("Problem with command " + r9[0] + " at position " + r8.getLine() + ":" + r8.getCol() + "\n", e);
    L5:
        e = move-exception;
        Throwable r7 = e.getCause();
        throw new ParseException("Problem with command " + r9[0] + " at position " + r8.getLine() + ":" + r8.getCol() + "\n" + r7.getMessage());
    }
}
