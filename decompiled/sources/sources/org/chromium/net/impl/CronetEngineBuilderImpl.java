package org.chromium.net.impl;

import android.content.Context;
import android.os.Process;
import android.os.SystemClock;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.chromium.net.ICronetEngineBuilder;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public abstract class CronetEngineBuilderImpl extends ICronetEngineBuilder {
    private static final Pattern INVALID_PKP_HOST_NAME = null;
    static int sApiLevel;
    private final Context mApplicationContext;
    private boolean mBrotiEnabled;
    private String mExperimentalOptions;
    private boolean mHttp2Enabled;
    private long mHttpCacheMaxSize;
    private HttpCacheMode mHttpCacheMode;
    protected final CronetLogger mLogger;
    private boolean mNetworkQualityEstimatorEnabled;
    private final List<Object> mPkps;
    private boolean mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
    private boolean mQuicEnabled;
    private final List<Object> mQuicHints;
    private String mStoragePath;
    private String mUserAgent;

    public enum HttpCacheMode extends Enum<HttpCacheMode> {
        private static final /* synthetic */ HttpCacheMode[] $VALUES = null;
        public static final HttpCacheMode DISABLED = null;
        public static final HttpCacheMode DISK = null;
        public static final HttpCacheMode DISK_NO_HTTP = null;
        public static final HttpCacheMode MEMORY = null;
        private final boolean mContentCacheEnabled;
        private final int mType;

        private static /* synthetic */ HttpCacheMode[] $values() {
            return new HttpCacheMode[]{DISABLED, DISK, DISK_NO_HTTP, MEMORY};
        }

        static {
            DISABLED = new HttpCacheMode("DISABLED", 0, 0, false);
            DISK = new HttpCacheMode("DISK", 1, 1, true);
            DISK_NO_HTTP = new HttpCacheMode("DISK_NO_HTTP", 2, 1, false);
            MEMORY = new HttpCacheMode("MEMORY", 3, 2, true);
            $VALUES = $values();
        }

        HttpCacheMode(String r1, int r2, int r3, boolean r4) {
            this.mContentCacheEnabled = r4;
            this.mType = r3;
        }

        public static HttpCacheMode fromPublicBuilderCacheMode(int r1) {
            if (r1 == 0) goto L18;
            if (r1 == 1) goto L16;
            if (r1 == 2) goto L14;
            if (r1 == 3) goto L10;
            f$$ExternalSyntheticBUOutline1.m("Unknown public builder cache mode");
            return null;
        L10:
            return DISK;
        L14:
            return DISK_NO_HTTP;
        L16:
            return MEMORY;
        L18:
            return DISABLED;
        }

        public static HttpCacheMode valueOf(String r1) {
            return (HttpCacheMode) Enum.valueOf(HttpCacheMode.class, r1);
        }

        public static HttpCacheMode[] values() {
            return (HttpCacheMode[]) $VALUES.clone();
        }

        public int getType() {
            return this.mType;
        }

        public int toPublicBuilderCacheMode() {
            int r3 = ordinal();
            if (r3 != 0) goto L5;
            return 0;
        L5:
            if (r3 != 1) goto L7;
            return 3;
        L7:
            if (r3 == 2) goto L12;
            if (r3 != 3) goto L10;
            return 1;
        L10:
            f$$ExternalSyntheticBUOutline1.m("Unknown internal builder cache mode");
            return 0;
        L12:
            return 2;
        }
    }

    static {
        INVALID_PKP_HOST_NAME = Pattern.compile("^[0-9\\.]*$");
        sApiLevel = VersionSafeCallbacks$ApiVersion.getMaximumAvailableApiLevel();
    }

    public CronetEngineBuilderImpl(Context r6, CronetLogger.CronetSource r7) {
        this.mQuicHints = new ArrayList();
        this.mPkps = new ArrayList();
        long r0 = SystemClock.uptimeMillis();
        Context r62 = r6.getApplicationContext();
        this.mApplicationContext = r62;
        this.mLogger = CronetLoggerFactory.createLogger(r62, r7);
        enableQuic(true);     // Catch: Throwable -> L6
        enableHttp2(true);     // Catch: Throwable -> L6
        enableBrotli(false);     // Catch: Throwable -> L6
        enableHttpCache(0, 0);     // Catch: Throwable -> L6
        enableNetworkQualityEstimator(false);     // Catch: Throwable -> L6
        enablePublicKeyPinningBypassForLocalTrustAnchors(true);     // Catch: Throwable -> L6
        maybeLogCronetEngineBuilderInitializedInfo(r0, true, r7);
        return;
    L6:
        th = move-exception;
        maybeLogCronetEngineBuilderInitializedInfo(r0, false, r7);
        throw th;
    }

    private void maybeLogCronetEngineBuilderInitializedInfo(long r4, boolean r6, CronetLogger.CronetSource r7) {
        if (sApiLevel < 30) goto L5;
        return;
    L5:
        CronetLogger.CronetEngineBuilderInitializedInfo r0 = new CronetLogger.CronetEngineBuilderInitializedInfo();
        r0.creationSuccessful = Boolean.FALSE;
        r0.author = CronetLogger.CronetEngineBuilderInitializedInfo.Author.IMPL;     // Catch: Throwable -> L9
        r0.uid = Process.myUid();     // Catch: Throwable -> L9
        r0.implVersion = new CronetLogger.CronetVersion(ImplVersion.getCronetVersion());     // Catch: Throwable -> L9
        r0.source = r7;     // Catch: Throwable -> L9
        r0.apiVersion = new CronetLogger.CronetVersion(VersionSafeCallbacks$ApiVersion.getCronetVersion());     // Catch: Throwable -> L9
        r0.cronetInitializationRef = getLogCronetInitializationRef();     // Catch: Throwable -> L9
        r0.creationSuccessful = Boolean.valueOf(r6);     // Catch: Throwable -> L9
        r0.engineBuilderCreatedLatencyMillis = (int) (SystemClock.uptimeMillis() - r4);
        this.mLogger.logCronetEngineBuilderInitializedInfo(r0);
        return;
    L9:
        th = move-exception;
        r0.engineBuilderCreatedLatencyMillis = (int) (SystemClock.uptimeMillis() - r4);
        this.mLogger.logCronetEngineBuilderInitializedInfo(r0);
        throw th;
    }

    public boolean brotliEnabled() {
        return this.mBrotiEnabled;
    }

    public CronetEngineBuilderImpl enableBrotli(boolean r1) {
        this.mBrotiEnabled = r1;
        return this;
    }

    public CronetEngineBuilderImpl enableHttp2(boolean r1) {
        this.mHttp2Enabled = r1;
        return this;
    }

    public CronetEngineBuilderImpl enableHttpCache(int r3, long r4) {
        HttpCacheMode r32 = HttpCacheMode.fromPublicBuilderCacheMode(r3);
        if (r32.getType() == 1) goto L5;
    L9:
        this.mHttpCacheMode = r32;
        this.mHttpCacheMaxSize = r4;
        return this;
    L5:
        if (storagePath() != null) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Storage path must be set");
        return null;
    }

    public CronetEngineBuilderImpl enableNetworkQualityEstimator(boolean r1) {
        this.mNetworkQualityEstimatorEnabled = r1;
        return this;
    }

    public CronetEngineBuilderImpl enablePublicKeyPinningBypassForLocalTrustAnchors(boolean r1) {
        this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled = r1;
        return this;
    }

    public CronetEngineBuilderImpl enableQuic(boolean r1) {
        this.mQuicEnabled = r1;
        return this;
    }

    public String experimentalOptions() {
        return this.mExperimentalOptions;
    }

    public Context getContext() {
        return this.mApplicationContext;
    }

    public String getDefaultUserAgent() {
        return UserAgent.from(this.mApplicationContext);
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public abstract long getLogCronetInitializationRef();

    public String getUserAgent() {
        return this.mUserAgent;
    }

    public boolean http2Enabled() {
        return this.mHttp2Enabled;
    }

    public boolean networkQualityEstimatorEnabled() {
        return this.mNetworkQualityEstimatorEnabled;
    }

    public int publicBuilderHttpCacheMode() {
        return this.mHttpCacheMode.toPublicBuilderCacheMode();
    }

    public boolean publicKeyPinningBypassForLocalTrustAnchorsEnabled() {
        return this.mPublicKeyPinningBypassForLocalTrustAnchorsEnabled;
    }

    public boolean quicEnabled() {
        return this.mQuicEnabled;
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public CronetEngineBuilderImpl setExperimentalOptions(String r1) {
        this.mExperimentalOptions = r1;
        return this;
    }

    public CronetEngineBuilderImpl setUserAgent(String r1) {
        this.mUserAgent = r1;
        return this;
    }

    public String storagePath() {
        return this.mStoragePath;
    }

    public CronetLogger.CronetEngineBuilderInfo toLoggerInfo() {
        return new CronetLogger.CronetEngineBuilderInfo(publicKeyPinningBypassForLocalTrustAnchorsEnabled(), getUserAgent(), storagePath(), quicEnabled(), http2Enabled(), brotliEnabled(), publicBuilderHttpCacheMode(), experimentalOptions(), networkQualityEstimatorEnabled(), 0, getLogCronetInitializationRef());
    }
}
