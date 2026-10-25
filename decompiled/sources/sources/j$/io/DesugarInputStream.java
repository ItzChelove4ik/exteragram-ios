package j$.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class DesugarInputStream {
    public static long transferTo(InputStream r6, OutputStream r7) {
        Objects.requireNonNull(r7, "out");
        byte[] r1 = new byte[8192];
        long r2 = 0;
    L3:
        int r5 = r6.read(r1, 0, 8192);
        if (r5 < 0) goto L6;
        r7.write(r1, 0, r5);
        r2 = r2 + r5;
        goto L3
    L6:
        return r2;
    }
}
