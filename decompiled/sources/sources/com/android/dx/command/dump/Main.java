package com.android.dx.command.dump;

import com.android.dex.util.FileUtils;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.util.HexParser;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class Main {
    private final Args parsedArgs;

    private Main() {
        this.parsedArgs = new Args();
    }

    public static void main(String[] r1) {
        new Main().run(r1);
    }

    private void processOne(String r3, byte[] r4) {
        Args r2 = this.parsedArgs;
        if (r2.dotDump == false) goto L7;
        DotDumper.dump(r4, r3, r2);
        return;
    L7:
        if (r2.basicBlocks == false) goto L11;
        BlockDumper.dump(r4, System.out, r3, false, r2);
        return;
    L11:
        if (r2.ropBlocks == false) goto L15;
        BlockDumper.dump(r4, System.out, r3, true, r2);
        return;
    L15:
        if (r2.ssaBlocks == false) goto L18;
        r2.optimize = false;
        SsaDumper.dump(r4, System.out, r3, r2);
        return;
    L18:
        ClassDumper.dump(r4, System.out, r3, r2);
    }

    private void run(String[] r7) {
        int r0 = 0;
    L4:
        if (r0 >= r7.length) goto L47;
        String r1 = r7[r0];
        if (r1.equals("--") == true) goto L47;
        if (r1.startsWith("--") == false) goto L47;
        if (r1.equals("--bytes") == false) goto L14;
        this.parsedArgs.rawBytes = true;
    L43:
        r0 = r0 + 1;
        goto L4
    L14:
        if (r1.equals("--basic-blocks") == false) goto L17;
        this.parsedArgs.basicBlocks = true;
        goto L43
    L17:
        if (r1.equals("--rop-blocks") == false) goto L20;
        this.parsedArgs.ropBlocks = true;
        goto L43
    L20:
        if (r1.equals("--optimize") == false) goto L23;
        this.parsedArgs.optimize = true;
        goto L43
    L23:
        if (r1.equals("--ssa-blocks") == false) goto L26;
        this.parsedArgs.ssaBlocks = true;
        goto L43
    L26:
        if (r1.startsWith("--ssa-step=") == false) goto L29;
        this.parsedArgs.ssaStep = r1.substring(r1.indexOf(61) + 1);
        goto L43
    L29:
        if (r1.equals("--debug") == false) goto L32;
        this.parsedArgs.debug = true;
        goto L43
    L32:
        if (r1.equals("--dot") == false) goto L35;
        this.parsedArgs.dotDump = true;
        goto L43
    L35:
        if (r1.equals("--strict") == false) goto L38;
        this.parsedArgs.strictParse = true;
        goto L43
    L38:
        if (r1.startsWith("--width=") == false) goto L41;
        String r12 = r1.substring(r1.indexOf(61) + 1);
        this.parsedArgs.width = Integer.parseInt(r12);
        goto L43
    L41:
        if (r1.startsWith("--method=") == false) goto L44;
        this.parsedArgs.method = r1.substring(r1.indexOf(61) + 1);
        goto L43
    L44:
        System.err.println("unknown option: ".concat(r1));
        GlShader$$ExternalSyntheticBUOutline1.m("usage");
        return;
    L47:
        if (r0 != r7.length) goto L49;
        System.err.println("no input files specified");
        GlShader$$ExternalSyntheticBUOutline1.m("usage");
        return;
    L49:
        if (r0 >= r7.length) goto L66;
        String r13 = r7[r0];     // Catch: ParseException -> L54
        System.out.println("reading " + r13 + "...");     // Catch: ParseException -> L54
        byte[] r2 = FileUtils.readFile(r13);     // Catch: ParseException -> L54
        if (r13.endsWith(".class") == false) goto L71;
    L59:
        processOne(r13, r2);     // Catch: ParseException -> L54
    L65:
        r0 = r0 + 1;
        goto L49
    L71:
        r2 = HexParser.parse(new String(r2, "utf-8"));     // Catch: ParseException -> L54
        goto L59
    L56:
        e = move-exception;
        throw new RuntimeException("shouldn't happen", e);     // Catch: ParseException -> L54
    L54:
        e = move-exception;
        System.err.println("\ntrouble parsing:");
        if (this.parsedArgs.debug == false) goto L64;
        e.printStackTrace();
        goto L65
    L64:
        e.printContext(System.err);
        goto L65
    }
}
