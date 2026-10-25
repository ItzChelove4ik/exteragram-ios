package org.telegram.messenger.secretmedia;

import android.net.Uri;
import androidx.media3.datasource.BaseDataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.telegram.messenger.FileLoader;

/* loaded from: classes3.dex */
public final class EncryptedFileDataSource extends BaseDataSource {
    private int bytesRemaining;
    EncryptedFileInputStream fileInputStream;
    private boolean opened;
    private Uri uri;

    public static class EncryptedFileDataSourceException extends IOException {
        public EncryptedFileDataSourceException(Throwable r1) {
            super(r1);
        }
    }

    public EncryptedFileDataSource() {
        super(false);
    }

    @Deprecated
    public EncryptedFileDataSource(TransferListener r1) {
        this();
        if (r1 == null) goto L6;
        addTransferListener(r1);
        return;
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        this.fileInputStream.close();     // Catch: IOException -> L4
    L7:
        if (this.opened == false) goto L9;
        this.opened = false;
        transferEnded();
    L9:
        this.fileInputStream = null;
        this.uri = null;
        return;
    L4:
        e = move-exception;
        e.printStackTrace();
        goto L7
    }

    @Override // androidx.media3.datasource.DataSource
    public /* bridge */ /* synthetic */ Map getResponseHeaders() {
        return super.getResponseHeaders();
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r9) {
        this.uri = r9.uri;
        File r0 = new File(r9.uri.getPath());
        String r1 = r0.getName();
        EncryptedFileInputStream r3 = new EncryptedFileInputStream(r0, new File(FileLoader.getInternalCacheDir(), r1 + ".key"));     // Catch: Throwable -> L15
        this.fileInputStream = r3;     // Catch: Throwable -> L15
        r3.skip(r9.position);     // Catch: Throwable -> L15
        int r02 = (int) r0.length();     // Catch: Throwable -> L15
        transferInitializing(r9);     // Catch: Throwable -> L15
        long r2 = r9.position;     // Catch: Throwable -> L15
        long r4 = r02;
        if (r2 > r4) goto L14;
        int r03 = (int) (r4 - r2);     // Catch: Throwable -> L15
        this.bytesRemaining = r03;     // Catch: Throwable -> L15
        long r22 = r9.length;     // Catch: Throwable -> L15
        if (r22 == (-1)) goto L8;
        this.bytesRemaining = (int) Math.min(r03, r22);     // Catch: Throwable -> L15
    L8:
        this.opened = true;     // Catch: Throwable -> L15
        transferStarted(r9);     // Catch: Throwable -> L15
        long r23 = r9.length;     // Catch: Throwable -> L15
        if (r23 == (-1)) goto L12;
        return r23;
    L12:
        return this.bytesRemaining;
    L14:
        throw new DataSourceException(2008);     // Catch: Throwable -> L15
    L16:
        throw new DataSourceException(2008);
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r2, int r3, int r4) {
        if (r4 != 0) goto L5;
        return 0;
    L5:
        int r0 = this.bytesRemaining;
        if (r0 != 0) goto L9;
        return -1;
    L9:
        int r42 = Math.min(r4, r0);
        this.fileInputStream.read(r2, r3, r42);     // Catch: IOException -> L12
    L14:
        this.bytesRemaining -= r42;
        bytesTransferred(r42);
        return r42;
    L12:
        e = move-exception;
        e.printStackTrace();
        goto L14
    }
}
