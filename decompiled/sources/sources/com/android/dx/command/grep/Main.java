package com.android.dx.command.grep;

import com.android.dex.Dex;
import java.io.File;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public final class Main {
    public Main() {
    }

    public static void main(String[] r6) {
        int r0 = 0;
        if (new Grep(new Dex(new File(r6[0])), Pattern.compile(r6[1]), new PrintWriter(System.out)).grep() > 0) goto L6;
        r0 = 1;
    L6:
        System.exit(r0);
    }
}
