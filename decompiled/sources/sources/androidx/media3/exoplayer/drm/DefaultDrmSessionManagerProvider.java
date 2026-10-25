package androidx.media3.exoplayer.drm;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.primitives.Ints;
import java.util.Map;

/* loaded from: classes4.dex */
public final class DefaultDrmSessionManagerProvider implements DrmSessionManagerProvider {
    private MediaItem.DrmConfiguration drmConfiguration;
    private DataSource.Factory drmHttpDataSourceFactory;
    private LoadErrorHandlingPolicy drmLoadErrorHandlingPolicy;
    private final Object lock;
    private DrmSessionManager manager;
    private String userAgent;

    public DefaultDrmSessionManagerProvider() {
        this.lock = new Object();
    }

    private DrmSessionManager createManager(MediaItem.DrmConfiguration r5) {
        DataSource.Factory r0 = this.drmHttpDataSourceFactory;
        if (r0 != null) goto L6;
        r0 = new DefaultHttpDataSource.Factory().setUserAgent(this.userAgent);
    L6:
        Uri r2 = r5.licenseUri;
        if (r2 != null) goto L9;
        String r22 = null;
    L10:
        HttpMediaDrmCallback r1 = new HttpMediaDrmCallback(r22, r5.forceDefaultLicenseUri, r0);
        UnmodifiableIterator<Map.Entry<String, String>> r02 = r5.licenseRequestHeaders.entrySet().iterator();
    L12:
        if (r02.hasNext() == false) goto L14;
        Map.Entry<String, String> r23 = r02.next();
        r1.setKeyRequestProperty(r23.getKey(), r23.getValue());
        goto L12
    L14:
        DefaultDrmSessionManager.Builder r03 = new DefaultDrmSessionManager.Builder().setUuidAndExoMediaDrmProvider(r5.scheme, FrameworkMediaDrm.DEFAULT_PROVIDER).setMultiSession(r5.multiSession).setPlayClearSamplesWithoutKeys(r5.playClearContentWithoutKey).setUseDrmSessionsForClearContent(Ints.toArray(r5.forcedSessionTrackTypes));
        LoadErrorHandlingPolicy r4 = this.drmLoadErrorHandlingPolicy;
        if (r4 == null) goto L17;
        r03.setLoadErrorHandlingPolicy(r4);
    L17:
        DefaultDrmSessionManager r42 = r03.build(r1);
        r42.setMode(0, r5.getKeySetId());
        return r42;
    L9:
        r22 = r2.toString();
        goto L10
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
    public DrmSessionManager get(MediaItem r3) {
        Assertions.checkNotNull(r3.localConfiguration);
        MediaItem.DrmConfiguration r32 = r3.localConfiguration.drmConfiguration;
        if (r32 == null) goto L5;
        Object r0 = this.lock;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L9:
        if (r32.equals(this.drmConfiguration) == true) goto L13;
        this.drmConfiguration = r32;     // Catch: Throwable -> L11
        this.manager = createManager(r32);     // Catch: Throwable -> L11
    L13:
        DrmSessionManager r2 = (DrmSessionManager) Assertions.checkNotNull(this.manager);     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r2;
    L5:
        return DrmSessionManager.DRM_UNSUPPORTED;
    }
}
