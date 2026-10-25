package androidx.media3.datasource;

import com.google.android.gms.cast.CastStatusCodes;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;

/* loaded from: classes3.dex */
public class HttpDataSource$HttpDataSourceException extends DataSourceException {
    public final DataSpec dataSpec;
    public final int type;

    public HttpDataSource$HttpDataSourceException(DataSpec r1, int r2, int r3) {
        super(assignErrorCode(r2, r3));
        this.dataSpec = r1;
        this.type = r3;
    }

    public HttpDataSource$HttpDataSourceException(IOException r1, DataSpec r2, int r3, int r4) {
        super(r1, assignErrorCode(r3, r4));
        this.dataSpec = r2;
        this.type = r4;
    }

    public HttpDataSource$HttpDataSourceException(String r1, DataSpec r2, int r3, int r4) {
        super(r1, assignErrorCode(r3, r4));
        this.dataSpec = r2;
        this.type = r4;
    }

    public HttpDataSource$HttpDataSourceException(String r1, IOException r2, DataSpec r3, int r4, int r5) {
        super(r1, r2, assignErrorCode(r4, r5));
        this.dataSpec = r3;
        this.type = r5;
    }

    private static int assignErrorCode(int r1, int r2) {
        if (r1 == 2000) goto L5;
        return r1;
    L5:
        if (r2 != 1) goto L9;
        return CastStatusCodes.INVALID_REQUEST;
    L9:
        return r1;
    }

    public static HttpDataSource$HttpDataSourceException createForIOException(IOException r3, DataSpec r4, int r5) {
        String r0 = r3.getMessage();
        if ((r3 instanceof SocketTimeoutException) == false) goto L6;
        int r02 = CastStatusCodes.CANCELED;
    L13:
        if (r02 != 2007) goto L17;
        return new HttpDataSource$CleartextNotPermittedException(r3, r4);
    L17:
        return new HttpDataSource$HttpDataSourceException(r3, r4, r02, r5);
    L6:
        if ((r3 instanceof InterruptedIOException) == false) goto L8;
        r02 = 1004;
        goto L13
    L8:
        if (r0 != null) goto L10;
    L12:
        r02 = CastStatusCodes.INVALID_REQUEST;
        goto L13
    L10:
        if (Ascii.toLowerCase(r0).matches("cleartext.*not permitted.*") == false) goto L12;
        r02 = 2007;
        goto L13
    }
}
