package com.coremedia.iso;

import com.googlecode.mp4parser.BasicContainer;
import com.googlecode.mp4parser.util.Logger;
import java.io.Closeable;

/* loaded from: classes4.dex */
public abstract class IsoFile extends BasicContainer implements Closeable {
    private static Logger LOG;

    static {
        LOG = Logger.getLogger(IsoFile.class);
    }

    public static byte[] fourCCtoBytes(String r4) {
        byte[] r1 = new byte[4];
        if (r4 == null) goto L9;
        int r2 = 0;
    L6:
        if (r2 >= Math.min(4, r4.length())) goto L9;
        r1[r2] = (byte) r4.charAt(r2);
        r2 = r2 + 1;
    L9:
        return r1;
    }
}
