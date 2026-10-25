package androidx.media3.exoplayer.drm;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.os.Build;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceInputStream;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import com.google.common.io.ByteStreams;
import java.util.List;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class DrmUtil {

    public static final class Api23 {
        public static boolean isMediaDrmResetException(Throwable r0) {
            return r0 instanceof MediaDrmResetException;
        }
    }

    public static byte[] executePost(DataSource r9, String r10, byte[] r11, Map<String, String> r12) {
        StatsDataSource r1 = new StatsDataSource(r9);
        DataSpec r3 = new DataSpec.Builder().setUri(r10).setHttpRequestHeaders(r12).setHttpMethod(2).setHttpBody(r11).setFlags(1).build();
        int r92 = 0;
        DataSpec r102 = r3;
    L26:
        DataSourceInputStream r112 = new DataSourceInputStream(r1, r102);     // Catch: Exception -> L7
        byte[] r93 = ByteStreams.toByteArray(r112);     // Catch: Throwable -> L9 HttpDataSource$InvalidResponseCodeException -> L11
    L5:
        Util.closeQuietly(r112);     // Catch: Exception -> L7
        return r93;
    L11:
        e = move-exception;
        String r0 = getRedirectUrl(e, r92);     // Catch: Throwable -> L9
        if (r0 == null) goto L18;
        r92 = r92 + 1;     // Catch: Throwable -> L9
        r102 = r102.buildUpon().setUri(r0).build();     // Catch: Throwable -> L9
        Util.closeQuietly(r112);     // Catch: Exception -> L7
        goto L26
    L18:
        throw e;     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        Util.closeQuietly(r112);     // Catch: Exception -> L7
        throw th;     // Catch: Exception -> L7
    L7:
        e = move-exception;
        throw new MediaDrmCallbackException(r3, r1.getLastOpenedUri(), r1.getResponseHeaders(), r1.getBytesRead(), e);
    }

    public static int getErrorCodeForMediaDrmException(Throwable r3, int r4) {
        if ((r3 instanceof MediaDrm.MediaDrmStateException) == false) goto L7;
        return Util.getErrorCodeForMediaDrmErrorCode(Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) r3).getDiagnosticInfo()));
    L7:
        if (Api23.isMediaDrmResetException(r3) == false) goto L10;
        return 6006;
    L10:
        if ((r3 instanceof NotProvisionedException) == false) goto L12;
    L42:
        return 6002;
    L12:
        if (isFailureToConstructNotProvisionedException(r3) == true) goto L42;
        if ((r3 instanceof DeniedByServerException) == false) goto L19;
        return 6007;
    L19:
        if ((r3 instanceof UnsupportedDrmException) == false) goto L23;
        return 6001;
    L23:
        if ((r3 instanceof DefaultDrmSessionManager.MissingSchemeDataException) == false) goto L27;
        return 6003;
    L27:
        if ((r3 instanceof KeysExpiredException) == false) goto L31;
        return 6008;
    L31:
        if (r4 != 1) goto L34;
        return 6006;
    L34:
        if (r4 != 2) goto L38;
        return 6004;
    L38:
        if (r4 != 3) goto L40;
        return 6002;
    L40:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    private static String getRedirectUrl(HttpDataSource$InvalidResponseCodeException r3, int r4) {
        int r0 = r3.responseCode;
        if (r0 == 307) goto L7;
        if (r0 == 308) goto L7;
    L16:
        return null;
    L7:
        if (r4 >= 5) goto L16;
        Map<String, List<String>> r32 = r3.headerFields;
        if (r32 == null) goto L16;
        List<String> r33 = r32.get("Location");
        if (r33 == null) goto L16;
        if (r33.isEmpty() == true) goto L16;
        return r33.get(0);
    }

    public static boolean isFailureToConstructNotProvisionedException(Throwable r2) {
        if (Build.VERSION.SDK_INT == 34) goto L5;
        return false;
    L5:
        if ((r2 instanceof NoSuchMethodError) == true) goto L7;
        return false;
    L7:
        if (r2.getMessage() != null) goto L9;
        return false;
    L9:
        if (r2.getMessage().contains("Landroid/media/NotProvisionedException;.<init>(") == false) goto L16;
        return true;
    L16:
        return false;
    }

    public static boolean isFailureToConstructResourceBusyException(Throwable r2) {
        if (Build.VERSION.SDK_INT == 34) goto L5;
        return false;
    L5:
        if ((r2 instanceof NoSuchMethodError) == true) goto L7;
        return false;
    L7:
        if (r2.getMessage() != null) goto L9;
        return false;
    L9:
        if (r2.getMessage().contains("Landroid/media/ResourceBusyException;.<init>(") == false) goto L16;
        return true;
    L16:
        return false;
    }
}
