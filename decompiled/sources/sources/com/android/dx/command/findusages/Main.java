package com.android.dx.command.findusages;

import com.android.dex.Dex;
import java.io.File;
import java.io.PrintWriter;

/* loaded from: classes4.dex */
public final class Main {
    public Main() {
    }

    public static void main(String[] r4) {
        String r0 = r4[0];
        String r1 = r4[1];
        String r42 = r4[2];
        Dex r2 = new Dex(new File(r0));
        PrintWriter r02 = new PrintWriter(System.out);
        new FindUsages(r2, r1, r42, r02).findUsages();
        r02.flush();
    }
}
