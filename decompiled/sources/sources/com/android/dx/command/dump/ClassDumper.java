package com.android.dx.command.dump;

import com.android.dx.cf.direct.DirectClassFile;
import com.android.dx.cf.direct.StdAttributeFactory;
import com.android.dx.util.ByteArray;
import java.io.PrintStream;

/* loaded from: classes4.dex */
public final class ClassDumper extends BaseDumper {
    private ClassDumper(byte[] r1, PrintStream r2, String r3, Args r4) {
        super(r1, r2, r3, r4);
    }

    public static void dump(byte[] r1, PrintStream r2, String r3, Args r4) {
        new ClassDumper(r1, r2, r3, r4).dump();
    }

    public void dump() {
        byte[] r0 = getBytes();
        ByteArray r1 = new ByteArray(r0);
        DirectClassFile r2 = new DirectClassFile(r1, getFilePath(), getStrictParse());
        r2.setAttributeFactory(StdAttributeFactory.THE_ONE);
        r2.setObserver(this);
        r2.getMagic();
        int r22 = getReadBytes();
        if (r22 == r0.length) goto L6;
        parsed(r1, r22, r0.length - r22, "<extra data at end of file>");
        return;
    }
}
