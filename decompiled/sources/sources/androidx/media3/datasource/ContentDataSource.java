package androidx.media3.datasource;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import androidx.media3.common.util.Util;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class ContentDataSource extends BaseDataSource {
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private FileInputStream inputStream;
    private boolean opened;
    private final ContentResolver resolver;
    private Uri uri;

    public static class ContentDataSourceException extends DataSourceException {
        public ContentDataSourceException(IOException r1, int r2) {
            super(r1, r2);
        }
    }

    public ContentDataSource(Context r2) {
        super(false);
        this.resolver = r2.getContentResolver();
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        this.uri = null;
        FileInputStream r3 = this.inputStream;     // Catch: Throwable -> L7 IOException -> L9
        if (r3 == null) goto L11;
        r3.close();     // Catch: Throwable -> L7 IOException -> L9
    L11:
        this.inputStream = null;
        AssetFileDescriptor r32 = this.assetFileDescriptor;     // Catch: Throwable -> L16 IOException -> L18
        if (r32 == null) goto L20;
        r32.close();     // Catch: Throwable -> L16 IOException -> L18
    L20:
        this.assetFileDescriptor = null;
        if (this.opened == false) goto L59;
        this.opened = false;
        transferEnded();
        return;
    L59:
        return;
    L16:
        th = move-exception;
        this.assetFileDescriptor = null;
        if (this.opened == false) goto L29;
        this.opened = false;
        transferEnded();
    L29:
        throw th;
    L18:
        e = move-exception;
        throw new ContentDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L16
    L9:
        e = move-exception;
        throw new ContentDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        this.inputStream = null;
        AssetFileDescriptor r4 = this.assetFileDescriptor;     // Catch: Throwable -> L37 IOException -> L39
        if (r4 == null) goto L41;
        r4.close();     // Catch: Throwable -> L37 IOException -> L39
    L41:
        this.assetFileDescriptor = null;
        if (this.opened == false) goto L44;
        this.opened = false;
        transferEnded();
    L44:
        throw th;
    L39:
        e = move-exception;
        throw new ContentDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L37
    L37:
        th = move-exception;
        this.assetFileDescriptor = null;
        if (this.opened == false) goto L50;
        this.opened = false;
        transferEnded();
    L50:
        throw th;
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        return this.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r17) {
        Uri r4 = r17.uri.normalizeScheme();     // Catch: IOException -> L6 ContentDataSourceException -> L60
        this.uri = r4;     // Catch: IOException -> L6 ContentDataSourceException -> L60
        transferInitializing(r17);     // Catch: IOException -> L6 ContentDataSourceException -> L60
        if (Objects.equals(r4.getScheme(), "content") == false) goto L8;
        Bundle r5 = new Bundle();     // Catch: IOException -> L6 ContentDataSourceException -> L60
        r5.putBoolean("android.provider.extra.ACCEPT_ORIGINAL_MEDIA_FORMAT", true);     // Catch: IOException -> L6 ContentDataSourceException -> L60
        AssetFileDescriptor r52 = this.resolver.openTypedAssetFileDescriptor(r4, "*/*", r5);     // Catch: IOException -> L6 ContentDataSourceException -> L60
    L9:
        this.assetFileDescriptor = r52;     // Catch: IOException -> L6 ContentDataSourceException -> L60
        if (r52 == null) goto L49;
        long r7 = r52.getLength();     // Catch: IOException -> L6 ContentDataSourceException -> L60
        FileInputStream r2 = new FileInputStream(r52.getFileDescriptor());     // Catch: IOException -> L6 ContentDataSourceException -> L60
        this.inputStream = r2;     // Catch: IOException -> L6 ContentDataSourceException -> L60
        if (r7 != (-1)) goto L14;
    L18:
        long r13 = r52.getStartOffset();     // Catch: IOException -> L6 ContentDataSourceException -> L60
        long r3 = r2.skip(r17.position + r13) - r13;     // Catch: IOException -> L6 ContentDataSourceException -> L60
        if (r3 != r17.position) goto L48;
        if (r7 != (-1)) goto L30;
        FileChannel r22 = r2.getChannel();     // Catch: IOException -> L6 ContentDataSourceException -> L60
        long r32 = r22.size();     // Catch: IOException -> L6 ContentDataSourceException -> L60
        if (r32 != 0) goto L25;
        this.bytesRemaining = -1;     // Catch: IOException -> L6 ContentDataSourceException -> L60
    L33:
        long r23 = r17.length;
        if (r23 == (-1)) goto L40;
        long r42 = this.bytesRemaining;
        if (r42 == (-1)) goto L39;
        r23 = Math.min(r42, r23);
    L39:
        this.bytesRemaining = r23;
    L40:
        this.opened = true;
        transferStarted(r17);
        long r1 = r17.length;
        if (r1 == (-1)) goto L44;
        return r1;
    L44:
        return this.bytesRemaining;
    L25:
        long r33 = r32 - r22.position();     // Catch: IOException -> L6 ContentDataSourceException -> L60
        this.bytesRemaining = r33;     // Catch: IOException -> L6 ContentDataSourceException -> L60
        if (r33 >= 0) goto L33;
        throw new ContentDataSourceException(null, 2008);     // Catch: IOException -> L6 ContentDataSourceException -> L60
    L30:
        long r72 = r7 - r3;     // Catch: IOException -> L6 ContentDataSourceException -> L60
        this.bytesRemaining = r72;     // Catch: IOException -> L6 ContentDataSourceException -> L60
        if (r72 >= 0) goto L33;
        throw new ContentDataSourceException(null, 2008);     // Catch: IOException -> L6 ContentDataSourceException -> L60
    L48:
        throw new ContentDataSourceException(null, 2008);     // Catch: IOException -> L6 ContentDataSourceException -> L60
    L14:
        if (r17.position <= r7) goto L18;
        throw new ContentDataSourceException(null, 2008);     // Catch: IOException -> L6 ContentDataSourceException -> L60
    L49:
        IOException r12 = new IOException("Could not open file descriptor for: " + r4);     // Catch: IOException -> L6 ContentDataSourceException -> L60
        int r15 = CastStatusCodes.AUTHENTICATION_FAILED;
    L53:
        e = e;
    L55:
        if ((e instanceof FileNotFoundException) == false) goto L57;
        int r34 = CastStatusCodes.APPLICATION_NOT_RUNNING;
    L59:
        throw new ContentDataSourceException(e, r34);
    L57:
        r34 = r15;
        goto L59
    L52:
        throw new ContentDataSourceException(r12, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: IOException -> L53 ContentDataSourceException -> L60
    L8:
        r52 = this.resolver.openAssetFileDescriptor(r4, "r");     // Catch: IOException -> L6 ContentDataSourceException -> L60
    L60:
        e = move-exception;
        throw e;
    L6:
        e = e;
        r15 = CastStatusCodes.AUTHENTICATION_FAILED;
        goto L55
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
        int r92 = ((FileInputStream) Util.castNonNull(this.inputStream)).read(r9, r10, r11);     // Catch: IOException -> L21
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
        throw new ContentDataSourceException(e, CastStatusCodes.AUTHENTICATION_FAILED);
    L24:
        r11 = (int) Math.min(r0, r11);     // Catch: IOException -> L21
        goto L13
    }
}
