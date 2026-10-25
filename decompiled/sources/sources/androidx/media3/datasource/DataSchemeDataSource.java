package androidx.media3.datasource;

import android.net.Uri;
import android.util.Base64;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import j$.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes3.dex */
public final class DataSchemeDataSource extends BaseDataSource {
    private int bytesRemaining;
    private byte[] data;
    private DataSpec dataSpec;
    private int readPosition;

    public DataSchemeDataSource() {
        super(false);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        if (this.data == null) goto L5;
        this.data = null;
        transferEnded();
    L5:
        this.dataSpec = null;
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        DataSpec r0 = this.dataSpec;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r8) {
        transferInitializing(r8);
        this.dataSpec = r8;
        Uri r0 = r8.uri.normalizeScheme();
        String r1 = r0.getScheme();
        Assertions.checkArgument("data".equals(r1), "Unsupported scheme: " + r1);
        String[] r12 = Util.split(r0.getSchemeSpecificPart(), ",");
        if (r12.length != 2) goto L25;
        String r02 = r12[1];
        if (r12[0].contains(";base64") == true) goto L26;
        this.data = Util.getUtf8Bytes(URLDecoder.decode(r02, StandardCharsets.US_ASCII.name()));
    L12:
        long r03 = r8.position;
        byte[] r2 = this.data;
        if (r03 > r2.length) goto L22;
        int r04 = (int) r03;
        this.readPosition = r04;
        int r13 = r2.length - r04;
        this.bytesRemaining = r13;
        long r22 = r8.length;
        if (r22 == (-1)) goto L17;
        this.bytesRemaining = (int) Math.min(r13, r22);
    L17:
        transferStarted(r8);
        long r05 = r8.length;
        if (r05 == (-1)) goto L21;
        return r05;
    L21:
        return this.bytesRemaining;
    L22:
        this.data = null;
        throw new DataSourceException(2008);
    L26:
        this.data = Base64.decode(r02, 0);     // Catch: IllegalArgumentException -> L8
    L8:
        e = move-exception;
        throw ParserException.createForMalformedDataOfUnknownType("Error while parsing Base64 encoded string: " + r02, e);
    L25:
        throw ParserException.createForMalformedDataOfUnknownType("Unexpected URI format: " + r0, null);
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r3, int r4, int r5) {
        if (r5 != 0) goto L5;
        return 0;
    L5:
        int r0 = this.bytesRemaining;
        if (r0 != 0) goto L9;
        return -1;
    L9:
        int r52 = Math.min(r5, r0);
        System.arraycopy(Util.castNonNull(this.data), this.readPosition, r3, r4, r52);
        this.readPosition += r52;
        this.bytesRemaining -= r52;
        bytesTransferred(r52);
        return r52;
    }
}
