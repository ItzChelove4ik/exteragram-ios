package org.chromium.net.telemetry;

import android.os.Process;
import android.util.Log;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.impl.CronetLogger;

/* loaded from: classes5.dex */
public class CronetLoggerImpl extends CronetLogger {
    private static final String TAG = "CronetLoggerImpl";
    private final RateLimiter mRateLimiter;
    private final AtomicInteger mSamplesRateLimited;

    /* renamed from: org.chromium.net.telemetry.CronetLoggerImpl$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$chromium$net$impl$CronetLogger$CronetEngineBuilderInitializedInfo$Author = null;
        static final /* synthetic */ int[] $SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource = null;
        static final /* synthetic */ int[] $SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestFailureReason = null;
        static final /* synthetic */ int[] $SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestTerminalState = null;

        static {
            int[] r0 = new int[CronetLogger.CronetSource.values().length];
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource = r0;
            r0[CronetLogger.CronetSource.CRONET_SOURCE_STATICALLY_LINKED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L21
        L33:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource[CronetLogger.CronetSource.CRONET_SOURCE_PLAY_SERVICES.ordinal()] = 2;     // Catch: NoSuchFieldError -> L22
        L43:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource[CronetLogger.CronetSource.CRONET_SOURCE_FALLBACK.ordinal()] = 3;     // Catch: NoSuchFieldError -> L23
        L55:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource[CronetLogger.CronetSource.CRONET_SOURCE_PLATFORM.ordinal()] = 4;     // Catch: NoSuchFieldError -> L24
        L37:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource[CronetLogger.CronetSource.CRONET_SOURCE_UNSPECIFIED.ordinal()] = 5;     // Catch: NoSuchFieldError -> L25
        L10:
            int[] r3 = new int[CronetLogger.CronetTrafficInfo.RequestTerminalState.values().length];
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestTerminalState = r3;
            r3[CronetLogger.CronetTrafficInfo.RequestTerminalState.SUCCEEDED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L26
        L47:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestTerminalState[CronetLogger.CronetTrafficInfo.RequestTerminalState.ERROR.ordinal()] = 2;     // Catch: NoSuchFieldError -> L27
        L53:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestTerminalState[CronetLogger.CronetTrafficInfo.RequestTerminalState.CANCELLED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L28
        L14:
            int[] r2 = new int[CronetLogger.CronetEngineBuilderInitializedInfo.Author.values().length];
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetEngineBuilderInitializedInfo$Author = r2;
            r2[CronetLogger.CronetEngineBuilderInitializedInfo.Author.API.ordinal()] = 1;     // Catch: NoSuchFieldError -> L29
        L35:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetEngineBuilderInitializedInfo$Author[CronetLogger.CronetEngineBuilderInitializedInfo.Author.IMPL.ordinal()] = 2;     // Catch: NoSuchFieldError -> L30
        L17:
            int[] r22 = new int[CronetLogger.CronetTrafficInfo.RequestFailureReason.values().length];
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestFailureReason = r22;
            r22[CronetLogger.CronetTrafficInfo.RequestFailureReason.NETWORK.ordinal()] = 1;     // Catch: NoSuchFieldError -> L31
        L45:
            $SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestFailureReason[CronetLogger.CronetTrafficInfo.RequestFailureReason.OTHER.ordinal()] = 2;     // Catch: NoSuchFieldError -> L32
            return;
        }
    }

    static {
    }

    public CronetLoggerImpl(int r2) {
        this(new RateLimiter(r2));
    }

    public CronetLoggerImpl(RateLimiter r2) {
        this.mSamplesRateLimited = new AtomicInteger();
        this.mRateLimiter = r2;
    }

    private static int convertToProtoConnectionCloseSource(int r2) {
        if (r2 == 1) goto L8;
        if (r2 == 2) goto L7;
        return 0;
    L7:
        return 1;
    L8:
        return 2;
    }

    private static int convertToProtoCronetEngineBuilderInitializedAuthor(CronetLogger.CronetEngineBuilderInitializedInfo.Author r1) {
        int r12 = AnonymousClass1.$SwitchMap$org$chromium$net$impl$CronetLogger$CronetEngineBuilderInitializedInfo$Author[r1.ordinal()];
        int r0 = 1;
        if (r12 == 1) goto L8;
        r0 = 2;
        if (r12 == 2) goto L8;
        return 0;
    L8:
        return r0;
    }

    private static int convertToProtoCronetEngineBuilderInitializedSource(CronetLogger.CronetSource r1) {
        int r12 = AnonymousClass1.$SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource[r1.ordinal()];
        int r0 = 1;
        if (r12 == 1) goto L12;
        r0 = 2;
        if (r12 == 2) goto L12;
        r0 = 3;
        if (r12 == 3) goto L12;
        r0 = 4;
        if (r12 == 4) goto L12;
        return 0;
    L12:
        return r0;
    }

    private static int convertToProtoCronetEngineCreatedSource(CronetLogger.CronetSource r1) {
        int r12 = AnonymousClass1.$SwitchMap$org$chromium$net$impl$CronetLogger$CronetSource[r1.ordinal()];
        int r0 = 1;
        if (r12 == 1) goto L12;
        r0 = 2;
        if (r12 == 2) goto L12;
        r0 = 3;
        if (r12 == 3) goto L12;
        r0 = 4;
        if (r12 == 4) goto L12;
        return 0;
    L12:
        return r0;
    }

    private static int convertToProtoCronetRequestTerminalState(CronetLogger.CronetTrafficInfo.RequestTerminalState r1) {
        int r12 = AnonymousClass1.$SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestTerminalState[r1.ordinal()];
        int r0 = 1;
        if (r12 == 1) goto L10;
        r0 = 2;
        if (r12 == 2) goto L10;
        r0 = 3;
        if (r12 == 3) goto L10;
        return 0;
    L10:
        return r0;
    }

    private static int convertToProtoFailureReason(CronetLogger.CronetTrafficInfo.RequestFailureReason r1) {
        int r12 = AnonymousClass1.$SwitchMap$org$chromium$net$impl$CronetLogger$CronetTrafficInfo$RequestFailureReason[r1.ordinal()];
        if (r12 != 1) goto L5;
        return 1;
    L5:
        if (r12 == 2) goto L8;
        return 0;
    L8:
        return 100;
    }

    private static int convertToProtoHttpCacheMode(int r2) {
        if (r2 != 0) goto L5;
        return 1;
    L5:
        if (r2 != 1) goto L7;
        return 2;
    L7:
        if (r2 == 2) goto L13;
        if (r2 != 3) goto L11;
        return 4;
    L11:
        f$$ExternalSyntheticBUOutline1.m("Expected httpCacheMode to range from 0 to 3");
        return 0;
    L13:
        return 3;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public long generateId() {
        long r0 = ThreadLocalRandom.current().nextLong(-9223372036854775807L, 9223372036854775805L);
        if (r0 >= (-1)) goto L5;
        return r0;
    L5:
        return r0 + 2;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public void logCronetEngineBuilderInitializedInfo(CronetLogger.CronetEngineBuilderInitializedInfo r19) {
        ScopedSysTraceEvent r1 = ScopedSysTraceEvent.scoped("CronetLoggerImpl#logCronetEngineBuilderInitializedInfo");
        long r3 = r19.cronetInitializationRef;     // Catch: Throwable -> L23
        int r5 = convertToProtoCronetEngineBuilderInitializedAuthor(r19.author);     // Catch: Throwable -> L23
        int r6 = r19.engineBuilderCreatedLatencyMillis;     // Catch: Throwable -> L23
        int r7 = convertToProtoCronetEngineBuilderInitializedSource(r19.source);     // Catch: Throwable -> L23
        int r8 = OptionalBoolean.fromBoolean(r19.creationSuccessful).getValue();     // Catch: Throwable -> L23
        int r9 = r19.apiVersion.getMajorVersion();     // Catch: Throwable -> L23
        int r10 = r19.apiVersion.getMinorVersion();     // Catch: Throwable -> L23
        int r11 = r19.apiVersion.getBuildVersion();     // Catch: Throwable -> L23
        int r12 = r19.apiVersion.getPatchVersion();     // Catch: Throwable -> L23
        CronetLogger.CronetVersion r2 = r19.implVersion;     // Catch: Throwable -> L23
        if (r2 != null) goto L6;
        int r22 = -1;
    L7:
        CronetLogger.CronetVersion r14 = r19.implVersion;     // Catch: Throwable -> L23
        if (r14 != null) goto L10;
        int r142 = -1;
    L11:
        CronetLogger.CronetVersion r15 = r19.implVersion;     // Catch: Throwable -> L23
        if (r15 != null) goto L14;
        int r152 = -1;
    L15:
        CronetLogger.CronetVersion r13 = r19.implVersion;     // Catch: Throwable -> L23
        if (r13 != null) goto L18;
        int r16 = -1;
    L19:
        int r132 = r22;
        CronetStatsLog.write(762, r3, r5, r6, r7, r8, r9, r10, r11, r12, r132, r142, r152, r16, r19.uid);     // Catch: Throwable -> L23
        if (r1 == null) goto L35;
        r1.close();
        return;
    L35:
        return;
    L18:
        r16 = r13.getPatchVersion();     // Catch: Throwable -> L23
        goto L19
    L14:
        r152 = r15.getBuildVersion();     // Catch: Throwable -> L23
        goto L15
    L10:
        r142 = r14.getMinorVersion();     // Catch: Throwable -> L23
        goto L11
    L6:
        r22 = r2.getMajorVersion();     // Catch: Throwable -> L23
    L23:
        th = move-exception;
        if (r1 == null) goto L36;
        r1.close();     // Catch: Throwable -> L28
        throw th;
    L28:
        th = move-exception;
        th.addSuppressed(th);
        throw th;
    L36:
        throw th;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public void logCronetEngineCreation(long r1, CronetLogger.CronetEngineBuilderInfo r3, CronetLogger.CronetVersion r4, CronetLogger.CronetSource r5) {
        if (r3 == null) goto L8;
        if (r4 == null) goto L9;
        if (r5 == null) goto L10;
        writeCronetEngineCreation(r1, r3, r4, r5);
        return;
    L10:
        return;
    L9:
        return;
    }

    @Override // org.chromium.net.impl.CronetLogger
    public void logCronetTrafficInfo(long r3, CronetLogger.CronetTrafficInfo r5) {
        if (r5 != null) goto L4;
        return;
    L4:
        boolean r0 = this.mRateLimiter.tryAcquire();
        AtomicInteger r1 = this.mSamplesRateLimited;
        if (r0 == true) goto L8;
        r1.incrementAndGet();
        return;
    L8:
        writeCronetTrafficReported(r3, r5, r1.getAndSet(0));
    }

    public void writeCronetEngineCreation(long r44, CronetLogger.CronetEngineBuilderInfo r46, CronetLogger.CronetVersion r47, CronetLogger.CronetSource r48) {
        ScopedSysTraceEvent r1 = ScopedSysTraceEvent.scoped("CronetLoggerImpl#writeCronetEngineCreation");     // Catch: Exception -> L7
        ExperimentalOptions r0 = new ExperimentalOptions(r46.getExperimentalOptions());     // Catch: Throwable -> L9
        CronetStatsLog.write(703, r44, r47.getMajorVersion(), r47.getMinorVersion(), r47.getBuildVersion(), r47.getPatchVersion(), convertToProtoCronetEngineCreatedSource(r48), r46.isBrotliEnabled(), r46.isHttp2Enabled(), convertToProtoHttpCacheMode(r46.getHttpCacheMode()), r46.isPublicKeyPinningBypassForLocalTrustAnchorsEnabled(), r46.isQuicEnabled(), r46.isNetworkQualityEstimatorEnabled(), r46.getThreadPriority(), r0.getConnectionOptionsOption(), r0.getStoreServerConfigsInPropertiesOption().getValue(), r0.getMaxServerConfigsStoredInPropertiesOption(), r0.getIdleConnectionTimeoutSecondsOption(), r0.getGoawaySessionsOnIpChangeOption().getValue(), r0.getCloseSessionsOnIpChangeOption().getValue(), r0.getMigrateSessionsOnNetworkChangeV2Option().getValue(), r0.getMigrateSessionsEarlyV2().getValue(), r0.getDisableBidirectionalStreamsOption().getValue(), r0.getMaxTimeBeforeCryptoHandshakeSecondsOption(), r0.getMaxIdleTimeBeforeCryptoHandshakeSecondsOption(), r0.getEnableSocketRecvOptimizationOption().getValue(), r0.getAsyncDnsEnableOption().getValue(), r0.getStaleDnsEnableOption().getValue(), r0.getStaleDnsDelayMillisOption(), r0.getStaleDnsMaxExpiredTimeMillisOption(), r0.getStaleDnsMaxStaleUsesOption(), r0.getStaleDnsAllowOtherNetworkOption().getValue(), r0.getStaleDnsPersistToDiskOption().getValue(), r0.getStaleDnsPersistDelayMillisOption(), r0.getStaleDnsUseStaleOnNameNotResolvedOption().getValue(), r0.getDisableIpv6OnWifiOption().getValue(), r46.getCronetInitializationRef(), Process.myUid());     // Catch: Throwable -> L9
        if (r1 == null) goto L28;
        r1.close();     // Catch: Exception -> L7
        return;
    L28:
        return;
    L9:
        th = move-exception;
        if (r1 == null) goto L26;
        r1.close();     // Catch: Throwable -> L14
    L27:
        throw th;     // Catch: Exception -> L7
    L14:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L7
        throw th;     // Catch: Exception -> L7
    L26:
        throw th;     // Catch: Exception -> L7
    L7:
        e = move-exception;
        String r12 = TAG;
        if (Log.isLoggable(r12, 3) == false) goto L29;
        Log.d(r12, String.format("Failed to log CronetEngine:%s creation: %s", new Object[]{Long.valueOf(r44), e.getMessage()}));
        return;
    }

    public void writeCronetTrafficReported(long r46, CronetLogger.CronetTrafficInfo r48, int r49) {
        ScopedSysTraceEvent r1 = ScopedSysTraceEvent.scoped("CronetLoggerImpl#writeCronetTrafficReported");     // Catch: Exception -> L7
        CronetStatsLog.write(704, r46, SizeBuckets.calcRequestHeadersSizeBucket(r48.getRequestHeaderSizeInBytes()), SizeBuckets.calcRequestBodySizeBucket(r48.getRequestBodySizeInBytes()), SizeBuckets.calcResponseHeadersSizeBucket(r48.getResponseHeaderSizeInBytes()), SizeBuckets.calcResponseBodySizeBucket(r48.getResponseBodySizeInBytes()), r48.getResponseStatusCode(), Hash.hash(r48.getNegotiatedProtocol()), (int) r48.getHeadersLatency().toMillis(), (int) r48.getTotalLatency().toMillis(), r48.wasConnectionMigrationAttempted(), r48.didConnectionMigrationSucceed(), r49, convertToProtoCronetRequestTerminalState(r48.getTerminalState()), r48.getNonfinalUserCallbackExceptionCount(), -1, -1, r48.getReadCount(), r48.getOnUploadReadCount(), OptionalBoolean.fromBoolean(Boolean.valueOf(r48.getIsBidiStream())).getValue(), OptionalBoolean.fromBoolean(Boolean.valueOf(r48.getFinalUserCallbackThrew())).getValue(), r48.getUid(), r48.getNetworkInternalErrorCode(), r48.getQuicErrorCode(), convertToProtoConnectionCloseSource(r48.getConnectionCloseSource()), convertToProtoFailureReason(r48.getFailureReason()), OptionalBoolean.fromBoolean(Boolean.valueOf(r48.getIsSocketReused())).getValue(), r48.getCronetVersion(), convertToProtoCronetEngineBuilderInitializedSource(r48.getCronetSource()), r48.getTimeToEstablishDNSMillis(), r48.getTimeToEstablishSSLMillis(), r48.getTimeToConnectMillis(), r48.getTimeToSendFirstByteMillis());     // Catch: Throwable -> L9
        if (r1 == null) goto L28;
        r1.close();     // Catch: Exception -> L7
        return;
    L28:
        return;
    L9:
        th = move-exception;
        if (r1 == null) goto L26;
        r1.close();     // Catch: Throwable -> L14
    L27:
        throw th;     // Catch: Exception -> L7
    L14:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L7
        throw th;     // Catch: Exception -> L7
    L26:
        throw th;     // Catch: Exception -> L7
    L7:
        e = move-exception;
        this.mSamplesRateLimited.addAndGet(r49);
        String r12 = TAG;
        if (Log.isLoggable(r12, 3) == false) goto L29;
        Log.d(r12, String.format("Failed to log cronet traffic sample for CronetEngine %s: %s", new Object[]{Long.valueOf(r46), e.getMessage()}));
        return;
    }
}
