package com.android.dx.command;

/* loaded from: classes4.dex */
public class Main {
    private static final String USAGE_MESSAGE = "usage:\n  dx --dex [--debug] [--verbose] [--positions=<style>] [--no-locals]\n  [--no-optimize] [--statistics] [--[no-]optimize-list=<file>] [--no-strict]\n  [--keep-classes] [--output=<file>] [--dump-to=<file>] [--dump-width=<n>]\n  [--dump-method=<name>[*]] [--verbose-dump] [--no-files] [--core-library]\n  [--num-threads=<n>] [--incremental] [--force-jumbo] [--no-warning]\n  [--multi-dex [--main-dex-list=<file> [--minimal-main-dex]]\n  [--input-list=<file>] [--min-sdk-version=<n>]\n  [--allow-all-interface-method-invokes]\n  [<file>.class | <file>.{zip,jar,apk} | <directory>] ...\n    Convert a set of classfiles into a dex file, optionally embedded in a\n    jar/zip. Output name must end with one of: .dex .jar .zip .apk or be a\n    directory.\n    Positions options: none, important, lines.\n    --multi-dex: allows to generate several dex files if needed. This option is\n    exclusive with --incremental, causes --num-threads to be ignored and only\n    supports folder or archive output.\n    --main-dex-list=<file>: <file> is a list of class file names, classes\n    defined by those class files are put in classes.dex.\n    --minimal-main-dex: only classes selected by --main-dex-list are to be put\n    in the main dex.\n    --input-list: <file> is a list of inputs.\n    Each line in <file> must end with one of: .class .jar .zip .apk or be a\n    directory.\n    --min-sdk-version=<n>: Enable dex file features that require at least sdk\n    version <n>.\n  dx --annotool --annotation=<class> [--element=<element types>]\n  [--print=<print types>]\n  dx --dump [--debug] [--strict] [--bytes] [--optimize]\n  [--basic-blocks | --rop-blocks | --ssa-blocks | --dot] [--ssa-step=<step>]\n  [--width=<n>] [<file>.class | <file>.txt] ...\n    Dump classfiles, or transformations thereof, in a human-oriented format.\n  dx --find-usages <file.dex> <declaring type> <member>\n    Find references and declarations to a field or method.\n    <declaring type> is a class name in internal form, like Ljava/lang/Object;\n    <member> is a field or method name, like hashCode.\n  dx -J<option> ... <arguments, in one of the above forms>\n    Pass VM-specific options to the virtual machine that runs dx.\n  dx --version\n    Print the version of this tool (1.16).\n  dx --help\n    Print this message.";

    private Main() {
    }

    public static void main(String[] r6) {
        boolean r1 = false;
        int r2 = 0;
    L57:
        if (r2 >= r6.length) goto L40;
        String r4 = r6[r2];     // Catch: Throwable -> L36 RuntimeException -> L38 UsageException -> L55
        if (r4.equals("--") == true) goto L49;
        if (r4.startsWith("--") == false) goto L49;
    L18:
        r1 = true;
    L16:
        e = e;
        boolean r0 = true;
    L48:
        System.err.println("\nUNEXPECTED TOP-LEVEL EXCEPTION:");
        e.printStackTrace();
        System.exit(2);
    L47:
        boolean r62 = false;
        r1 = r0;
    L50:
        if (r1 == true) goto L52;
        System.err.println("error: no command specified");
        r62 = true;
    L52:
        if (r62 == false) goto L68;
        usage();
        System.exit(1);
        return;
    L68:
        return;
    L14:
        th = th;
        r0 = true;
    L41:
        System.err.println("\nUNEXPECTED TOP-LEVEL ERROR:");
        th.printStackTrace();
        if ((th instanceof NoClassDefFoundError) == false) goto L44;
    L45:
        System.err.println("Note: You may be using an incompatible virtual machine or class library.\n(This program is known to be incompatible with recent releases of GCJ.)");
    L46:
        System.exit(3);
        goto L47
    L44:
        if ((th instanceof NoSuchMethodError) == false) goto L46;
    L12:
        if (r4.equals("--dex") == true) goto L13;
        if (r4.equals("--dump") == true) goto L21;
        if (r4.equals("--annotool") == true) goto L24;
        if (r4.equals("--find-usages") == true) goto L27;
        if (r4.equals("--version") == true) goto L30;
        if (r4.equals("--help") == true) goto L34;
        r2 = r2 + 1;
        goto L57
    L34:
        r62 = true;
        r1 = true;
        goto L50
    L30:
        version();     // Catch: Throwable -> L14 RuntimeException -> L16 UsageException -> L18
    L31:
        r62 = false;
        r1 = true;
        goto L50
    L27:
        com.android.dx.command.findusages.Main.main(without(r6, r2));     // Catch: Throwable -> L14 RuntimeException -> L16 UsageException -> L18
        goto L31
    L24:
        com.android.dx.command.annotool.Main.main(without(r6, r2));     // Catch: Throwable -> L14 RuntimeException -> L16 UsageException -> L18
        goto L31
    L21:
        com.android.dx.command.dump.Main.main(without(r6, r2));     // Catch: Throwable -> L14 RuntimeException -> L16 UsageException -> L18
        goto L31
    L13:
        com.android.dx.command.dexer.Main.main(without(r6, r2));     // Catch: Throwable -> L14 RuntimeException -> L16 UsageException -> L18
        goto L31
    L40:
        r62 = false;
        goto L50
    L38:
        e = e;
        r0 = false;
    L36:
        th = th;
        r0 = false;
    L49:
        r62 = true;
        goto L50
    }

    private static void usage() {
        System.err.println(USAGE_MESSAGE);
    }

    private static void version() {
        System.err.println("dx version 1.16");
        System.exit(0);
    }

    private static String[] without(String[] r3, int r4) {
        int r0 = r3.length - 1;
        String[] r1 = new String[r0];
        System.arraycopy(r3, 0, r1, 0, r4);
        System.arraycopy(r3, r4 + 1, r1, r4, r0 - r4);
        return r1;
    }
}
