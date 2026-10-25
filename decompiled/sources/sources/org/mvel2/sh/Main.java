package org.mvel2.sh;

import java.io.File;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public class Main {
    public Main() {
    }

    public static void main(String[] r2) {
        if (r2.length == 0) goto L6;
        MVEL.evalFile(new File(r2[0]));
        return;
    L6:
        showSplash();
        new ShellSession().run();
    }

    private static void showSplash() {
        System.out.println("\nMVEL-Shell (MVELSH)");
        System.out.println("Copyright (C) 2010, Christopher Brock, The Codehaus");
        System.out.println("Version 2.3.0\n");
    }
}
