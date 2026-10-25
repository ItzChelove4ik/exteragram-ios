package androidx.media3.datasource;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes3.dex */
public final class AssetDataSource extends BaseDataSource {
    private final AssetManager assetManager;
    private long bytesRemaining;
    private InputStream inputStream;
    private boolean opened;
    private Uri uri;

    public static final class AssetDataSourceException extends DataSourceException {
        public AssetDataSourceException(Throwable r1, int r2) {
            super(r1, r2);
        }
    }

    public AssetDataSource(Context r2) {
        super(false);
        this.assetManager = r2.getAssets();
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        this.uri = null;
        InputStream r2 = this.inputStream;     // Catch: Throwable -> L7 IOException -> L9
        if (r2 == null) goto L11;
        r2.close();     // Catch: Throwable -> L7 IOException -> L9
    L11:
        this.inputStream = null;
        if (this.opened == false) goto L23;
        this.opened = false;
        transferEnded();
        return;
    L23:
        return;
    L7:
        th = move-exception;
        this.inputStream = null;
        if (this.opened == false) goto L20;
        this.opened = false;
        transferEnded();
    L20:
        throw th;
    L9:
        e = move-exception;
        throw new AssetDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L7
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r8) {
        Uri r0 = r8.uri;     // Catch: IOException -> L20 AssetDataSourceException -> L27
        this.uri = r0;     // Catch: IOException -> L20 AssetDataSourceException -> L27
        String r02 = (String) Assertions.checkNotNull(r0.getPath());     // Catch: IOException -> L20 AssetDataSourceException -> L27
        if (r02.startsWith("/android_asset/") == false) goto L6;
        r02 = r02.substring(15);     // Catch: IOException -> L20 AssetDataSourceException -> L27
    L8:
        transferInitializing(r8);     // Catch: IOException -> L20 AssetDataSourceException -> L27
        InputStream r03 = this.assetManager.open(r02, 1);     // Catch: IOException -> L20 AssetDataSourceException -> L27
        this.inputStream = r03;     // Catch: IOException -> L20 AssetDataSourceException -> L27
        if (r03.skip(r8.position) < r8.position) goto L19;
        long r04 = r8.length;     // Catch: IOException -> L20 AssetDataSourceException -> L27
        if (r04 == (-1)) goto L13;
        this.bytesRemaining = r04;     // Catch: IOException -> L20 AssetDataSourceException -> L27
    L16:
        this.opened = true;
        transferStarted(r8);
        return this.bytesRemaining;
    L13:
        long r05 = this.inputStream.available();     // Catch: IOException -> L20 AssetDataSourceException -> L27
        this.bytesRemaining = r05;     // Catch: IOException -> L20 AssetDataSourceException -> L27
        if (r05 != 2147483647L) goto L16;
        this.bytesRemaining = -1;     // Catch: IOException -> L20 AssetDataSourceException -> L27
        goto L16
    L19:
        throw new AssetDataSourceException(null, 2008);     // Catch: IOException -> L20 AssetDataSourceException -> L27
    L6:
        if (r02.startsWith("/") == false) goto L8;
        r02 = r02.substring(1);     // Catch: IOException -> L20 AssetDataSourceException -> L27
    L27:
        e = move-exception;
        throw e;
    L20:
        e = move-exception;
        if ((e instanceof FileNotFoundException) == false) goto L24;
        int r06 = CastStatusCodes.APPLICATION_NOT_RUNNING;
    L26:
        throw new AssetDataSourceException(e, r06);
    L24:
        r06 = CastStatusCodes.AUTHENTICATION_FAILED;
        goto L26
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r9, int r10, int r11) {
        if (r11 != 0) goto L5;
        return 0;
    L5:
        long r0 = this.bytesRemaining;
        if (r0 != 0) goto L9;
        return -1;
    L9:
        if (r0 != (-1)) goto L24;
    L13:
        int r92 = ((InputStream) Util.castNonNull(this.inputStream)).read(r9, r10, r11);     // Catch: IOException -> L21
        if (r92 != (-1)) goto L16;
        return -1;
    L16:
        long r102 = this.bytesRemaining;
        if (r102 == (-1)) goto L19;
        this.bytesRemaining = r102 - r92;
    L19:
        bytesTransferred(r92);
        return r92;
    L21:
        e = move-exception;
        throw new AssetDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);
    L24:
        r11 = (int) Math.min(r0, r11);     // Catch: IOException -> L21
        goto L13
    }
}
