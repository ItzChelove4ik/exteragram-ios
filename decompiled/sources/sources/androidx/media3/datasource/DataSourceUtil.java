package androidx.media3.datasource;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes3.dex */
public abstract class DataSourceUtil {
    public static void closeQuietly(DataSource r0) {
        if (r0 == null) goto L8;
        r0.close();     // Catch: IOException -> L5
        return;
    L9:
        return;
    }

    public static byte[] readToEnd(DataSource r4) {
        byte[] r0 = new byte[1024];
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 == (-1)) goto L12;
        if (r2 != r0.length) goto L8;
        r0 = Arrays.copyOf(r0, r0.length * 2);
    L8:
        r1 = r4.read(r0, r2, r0.length - r2);
        if (r1 == (-1)) goto L4;
        r2 = r2 + r1;
        goto L4
    L12:
        return Arrays.copyOf(r0, r2);
    }
}
