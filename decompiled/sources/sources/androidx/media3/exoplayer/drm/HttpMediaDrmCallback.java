package androidx.media3.exoplayer.drm;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes4.dex */
public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private final DataSource.Factory dataSourceFactory;
    private final String defaultLicenseUrl;
    private final boolean forceDefaultLicenseUrl;
    private final Map<String, String> keyRequestProperties;

    public HttpMediaDrmCallback(String r2, boolean r3, DataSource.Factory r4) {
        if (r3 == true) goto L5;
    L8:
        boolean r0 = true;
    L9:
        Assertions.checkArgument(r0);
        this.dataSourceFactory = r4;
        this.defaultLicenseUrl = r2;
        this.forceDefaultLicenseUrl = r3;
        this.keyRequestProperties = new HashMap();
        return;
    L5:
        if (TextUtils.isEmpty(r2) == false) goto L8;
        r0 = false;
        goto L9
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID r8, ExoMediaDrm.KeyRequest r9) {
        String r0 = r9.getLicenseServerUrl();
        if (this.forceDefaultLicenseUrl == false) goto L5;
    L6:
        r0 = this.defaultLicenseUrl;
    L8:
        if (TextUtils.isEmpty(r0) == true) goto L29;
        HashMap r1 = new HashMap();
        UUID r2 = C.PLAYREADY_UUID;
        if (r2.equals(r8) == false) goto L13;
        String r3 = "text/xml";
    L16:
        r1.put("Content-Type", r3);
        if (r2.equals(r8) == false) goto L19;
        r1.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
    L19:
        Map<String, String> r82 = this.keyRequestProperties;
        monitor-enter(r82);
        r1.putAll(this.keyRequestProperties);     // Catch: Throwable -> L25
        monitor-exit(r82);     // Catch: Throwable -> L25
        return DrmUtil.executePost(this.dataSourceFactory.createDataSource(), r0, r9.getData(), r1);
    L25:
        th = move-exception;
        throw th;
    L13:
        if (C.CLEARKEY_UUID.equals(r8) == false) goto L15;
        r3 = "application/json";
        goto L16
    L15:
        r3 = "application/octet-stream";
        goto L16
    L29:
        DataSpec.Builder r7 = new DataSpec.Builder();
        Uri r22 = Uri.EMPTY;
        throw new MediaDrmCallbackException(r7.setUri(r22).build(), r22, ImmutableMap.of(), 0, new IllegalStateException("No license URL"));
    L5:
        if (TextUtils.isEmpty(r0) == false) goto L8;
        goto L6
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID r2, ExoMediaDrm.ProvisionRequest r3) {
        return DrmUtil.executePost(this.dataSourceFactory.createDataSource(), r3.getDefaultUrl() + "&signedRequest=" + Util.fromUtf8Bytes(r3.getData()), null, Collections.EMPTY_MAP);
    }

    public void setKeyRequestProperty(String r2, String r3) {
        Assertions.checkNotNull(r2);
        Assertions.checkNotNull(r3);
        Map<String, String> r0 = this.keyRequestProperties;
        monitor-enter(r0);
        this.keyRequestProperties.put(r2, r3);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
