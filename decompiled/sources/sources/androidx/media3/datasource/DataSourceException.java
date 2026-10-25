package androidx.media3.datasource;

import java.io.IOException;

/* loaded from: classes3.dex */
public class DataSourceException extends IOException {
    public final int reason;

    public DataSourceException(int r1) {
        this.reason = r1;
    }

    public DataSourceException(String r1, int r2) {
        super(r1);
        this.reason = r2;
    }

    public DataSourceException(String r1, Throwable r2, int r3) {
        super(r1, r2);
        this.reason = r3;
    }

    public DataSourceException(Throwable r1, int r2) {
        super(r1);
        this.reason = r2;
    }

    public static boolean isCausedByPositionOutOfRange(IOException r2) {
    L2:
        if (r2 == null) goto L10;
        if ((r2 instanceof DataSourceException) == false) goto L9;
        if (((DataSourceException) r2).reason != 2008) goto L9;
        return true;
    L9:
        IOException r22 = r22.getCause();
        goto L2
    L10:
        return false;
    }
}
