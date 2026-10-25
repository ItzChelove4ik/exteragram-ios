package androidx.media3.datasource;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.android.gms.cast.CastStatusCodes;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.List;

/* loaded from: classes3.dex */
public final class RawResourceDataSource extends BaseDataSource {
    private final Context applicationContext;
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private DataSpec dataSpec;
    private InputStream inputStream;
    private boolean opened;

    public static class RawResourceDataSourceException extends DataSourceException {
        @Deprecated
        public RawResourceDataSourceException(String r3) {
            super(r3, null, CastStatusCodes.AUTHENTICATION_FAILED);
        }

        public RawResourceDataSourceException(String r1, Throwable r2, int r3) {
            super(r1, r2, r3);
        }
    }

    public RawResourceDataSource(Context r2) {
        super(false);
        this.applicationContext = r2.getApplicationContext();
    }

    @Deprecated
    public static Uri buildRawResourceUri(int r2) {
        return Uri.parse("rawresource:///" + r2);
    }

    private static AssetFileDescriptor openAssetFileDescriptor(Context r5, DataSpec r6) {
        Uri r62 = r6.uri.normalizeScheme();
        if (TextUtils.equals("rawresource", r62.getScheme()) == false) goto L10;
        Resources r52 = r5.getResources();
        List<String> r0 = r62.getPathSegments();
        if (r0.size() != 1) goto L8;
        int r02 = parseResourceId(r0.get(0));
    L42:
        AssetFileDescriptor r53 = r52.openRawResourceFd(r02);     // Catch: Resources.NotFoundException -> L32
        if (r53 == null) goto L31;
        return r53;
    L31:
        throw new RawResourceDataSourceException("Resource is compressed: " + r62, null, CastStatusCodes.AUTHENTICATION_FAILED);
    L32:
        e = move-exception;
        throw new RawResourceDataSourceException(null, e, CastStatusCodes.APPLICATION_NOT_RUNNING);
    L8:
        throw new RawResourceDataSourceException("rawresource:// URI must have exactly one path element, found " + r0.size());
    L10:
        if (TextUtils.equals("android.resource", r62.getScheme()) == false) goto L41;
        String r03 = (String) Assertions.checkNotNull(r62.getPath());
        if (r03.startsWith("/") == false) goto L15;
        r03 = r03.substring(1);
    L15:
        if (TextUtils.isEmpty(r62.getHost()) == false) goto L17;
        String r1 = r5.getPackageName();
    L19:
        if (r1.equals(r5.getPackageName()) == false) goto L44;
        r52 = r5.getResources();
    L23:
        if (r03.matches("\\d+") == false) goto L25;
        r02 = parseResourceId(r03);
        goto L42
    L25:
        r02 = r52.getIdentifier(r1 + ":" + r03, "raw", null);
        if (r02 != 0) goto L42;
        throw new RawResourceDataSourceException("Resource not found.", null, CastStatusCodes.APPLICATION_NOT_RUNNING);
    L44:
        r52 = r5.getPackageManager().getResourcesForApplication(r1);     // Catch: PackageManager.NameNotFoundException -> L37
    L37:
        e = move-exception;
        throw new RawResourceDataSourceException("Package in android.resource:// URI not found. Check http://g.co/dev/packagevisibility.", e, CastStatusCodes.APPLICATION_NOT_RUNNING);
    L17:
        r1 = r62.getHost();
        goto L19
    L41:
        throw new RawResourceDataSourceException("Unsupported URI scheme (" + r62.getScheme() + "). Only android.resource is supported.", null, 1004);
    }

    private static int parseResourceId(String r3) {
        return Integer.parseInt(r3);
    L5:
        throw new RawResourceDataSourceException("Resource identifier must be an integer.", null, 1004);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        this.dataSpec = null;
        InputStream r3 = this.inputStream;     // Catch: Throwable -> L7 IOException -> L9
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
        throw new RawResourceDataSourceException(null, e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L16
    L9:
        e = move-exception;
        throw new RawResourceDataSourceException(null, e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L7
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
        throw new RawResourceDataSourceException(null, e, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L37
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
        DataSpec r0 = this.dataSpec;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.uri;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec r13) {
        this.dataSpec = r13;
        transferInitializing(r13);
        AssetFileDescriptor r0 = openAssetFileDescriptor(this.applicationContext, r13);
        this.assetFileDescriptor = r0;
        long r02 = r0.getLength();
        FileInputStream r2 = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
        this.inputStream = r2;
        if (r02 != (-1)) goto L46;
    L11:
        long r8 = this.assetFileDescriptor.getStartOffset();     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
        long r10 = r2.skip(r13.position + r8) - r8;     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
        if (r10 != r13.position) goto L41;
        if (r02 != (-1)) goto L23;
        FileChannel r03 = r2.getChannel();     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
        if (r03.size() != 0) goto L18;
        this.bytesRemaining = -1;     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
    L26:
        long r04 = r13.length;
        if (r04 == (-1)) goto L33;
        long r5 = this.bytesRemaining;
        if (r5 == (-1)) goto L32;
        r04 = Math.min(r5, r04);
    L32:
        this.bytesRemaining = r04;
    L33:
        this.opened = true;
        transferStarted(r13);
        long r05 = r13.length;
        if (r05 == (-1)) goto L37;
        return r05;
    L37:
        return this.bytesRemaining;
    L18:
        long r1 = r03.size() - r03.position();     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
        this.bytesRemaining = r1;     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
        if (r1 >= 0) goto L26;
        throw new RawResourceDataSourceException(null, null, 2008);     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
    L23:
        long r06 = r02 - r10;     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
        this.bytesRemaining = r06;     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
        if (r06 >= 0) goto L26;
        throw new DataSourceException(2008);     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
    L41:
        throw new RawResourceDataSourceException(null, null, 2008);     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
    L46:
    L44:
        e = move-exception;
        throw e;
    L9:
        e = move-exception;
        throw new RawResourceDataSourceException(null, e, CastStatusCodes.AUTHENTICATION_FAILED);
    L5:
        if (r13.position <= r02) goto L11;
        throw new RawResourceDataSourceException(null, null, 2008);     // Catch: IOException -> L9 RawResourceDataSourceException -> L44
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] r10, int r11, int r12) {
        if (r12 != 0) goto L5;
        return 0;
    L5:
        long r0 = this.bytesRemaining;
        if (r0 != 0) goto L9;
        return -1;
    L9:
        if (r0 != (-1)) goto L29;
    L13:
        int r102 = ((InputStream) Util.castNonNull(this.inputStream)).read(r10, r11, r12);     // Catch: IOException -> L26
        long r112 = this.bytesRemaining;
        if (r102 != (-1)) goto L22;
        if (r112 != (-1)) goto L20;
        return -1;
    L20:
        throw new RawResourceDataSourceException("End of stream reached having not read sufficient data.", new EOFException(), CastStatusCodes.AUTHENTICATION_FAILED);
    L22:
        if (r112 == (-1)) goto L24;
        this.bytesRemaining = r112 - r102;
    L24:
        bytesTransferred(r102);
        return r102;
    L26:
        e = move-exception;
        throw new RawResourceDataSourceException(null, e, CastStatusCodes.AUTHENTICATION_FAILED);
    L29:
        r12 = (int) Math.min(r0, r12);     // Catch: IOException -> L26
        goto L13
    }
}
