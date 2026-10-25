package androidx.media3.datasource;

import android.net.Uri;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes3.dex */
public final class FileDataSource extends BaseDataSource {
    private long bytesRemaining;
    private RandomAccessFile file;
    private boolean opened;
    private Uri uri;

    public static final class Factory implements DataSource.Factory {
        private TransferListener listener;

        public Factory() {
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public /* bridge */ /* synthetic */ DataSource createDataSource() {
            return createDataSource();
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public FileDataSource createDataSource() {
            FileDataSource r0 = new FileDataSource();
            TransferListener r1 = this.listener;
            if (r1 == null) goto L5;
            r0.addTransferListener(r1);
        L5:
            return r0;
        }
    }

    public static class FileDataSourceException extends DataSourceException {
        public FileDataSourceException(String r1, Throwable r2, int r3) {
            super(r1, r2, r3);
        }

        public FileDataSourceException(Throwable r1, int r2) {
            super(r1, r2);
        }
    }

    public FileDataSource() {
        super(false);
    }

    private static RandomAccessFile openLocalFile(Uri r4) {
        int r0 = CastStatusCodes.MESSAGE_TOO_LARGE;
        return new RandomAccessFile((String) Assertions.checkNotNull(r4.getPath()), "r");
    L7:
        e = move-exception;
        if (TextUtils.isEmpty(r4.getQuery()) == false) goto L27;
        if (TextUtils.isEmpty(r4.getFragment()) == false) goto L27;
        if ((e.getCause() instanceof ErrnoException) == true) goto L21;
    L23:
        r0 = CastStatusCodes.APPLICATION_NOT_RUNNING;
    L25:
        throw new FileDataSourceException(e, r0);
    L21:
        if (((ErrnoException) e.getCause()).errno != OsConstants.EACCES) goto L23;
    L27:
        throw new FileDataSourceException(String.format("uri has query and/or fragment, which are not supported. Did you call Uri.parse() on a string containing '?' or '#'? Use Uri.fromFile(new File(path)) to avoid this. path=%s,query=%s,fragment=%s", new Object[]{r4.getPath(), r4.getQuery(), r4.getFragment()}), e, 1004);
    L5:
        e = move-exception;
        throw new FileDataSourceException(e, CastStatusCodes.MESSAGE_TOO_LARGE);
    L9:
        e = move-exception;
        throw new FileDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        this.uri = null;
        RandomAccessFile r2 = this.file;     // Catch: Throwable -> L7 IOException -> L9
        if (r2 == null) goto L11;
        r2.close();     // Catch: Throwable -> L7 IOException -> L9
    L11:
        this.file = null;
        if (this.opened == false) goto L23;
        this.opened = false;
        transferEnded();
        return;
    L23:
        return;
    L7:
        th = move-exception;
        this.file = null;
        if (this.opened == false) goto L20;
        this.opened = false;
        transferEnded();
    L20:
        throw th;
    L9:
        e = move-exception;
        throw new FileDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L7
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r5) {
        Uri r0 = r5.uri;
        this.uri = r0;
        transferInitializing(r5);
        RandomAccessFile r02 = openLocalFile(r0);
        this.file = r02;
        r02.seek(r5.position);     // Catch: IOException -> L13
        long r03 = r5.length;     // Catch: IOException -> L13
        if (r03 != (-1)) goto L6;
        r03 = this.file.length() - r5.position;     // Catch: IOException -> L13
    L6:
        this.bytesRemaining = r03;     // Catch: IOException -> L13
        if (r03 < 0) goto L12;
        this.opened = true;
        transferStarted(r5);
        return this.bytesRemaining;
    L12:
        throw new FileDataSourceException(null, null, 2008);
    L13:
        e = move-exception;
        throw new FileDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r6, int r7, int r8) {
        if (r8 != 0) goto L6;
        return 0;
    L6:
        if (this.bytesRemaining != 0) goto L16;
        return -1;
    L16:
        int r62 = ((RandomAccessFile) Util.castNonNull(this.file)).read(r6, r7, (int) Math.min(this.bytesRemaining, r8));     // Catch: IOException -> L13
        if (r62 <= 0) goto L12;
        this.bytesRemaining -= r62;
        bytesTransferred(r62);
    L12:
        return r62;
    L13:
        e = move-exception;
        throw new FileDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);
    }
}
