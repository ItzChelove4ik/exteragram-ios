package org.chromium.net.impl;

import android.net.http.ConnectionMigrationOptions;
import android.net.http.DnsOptions;
import android.net.http.HttpEngine;
import android.net.http.QuicOptions;
import j$.time.Duration;
import okio.Buffer$$ExternalSyntheticBUOutline2;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.ICronetEngineBuilder;
import org.chromium.net.telemetry.ExperimentalOptions;
import org.chromium.net.telemetry.OptionalBoolean;

/* loaded from: classes5.dex */
class AndroidHttpEngineBuilderWrapper extends ICronetEngineBuilder {
    private final HttpEngine.Builder mBackend;

    /* renamed from: org.chromium.net.impl.AndroidHttpEngineBuilderWrapper$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$chromium$net$telemetry$OptionalBoolean = null;

        static {
            int[] r0 = new int[OptionalBoolean.values().length];
            $SwitchMap$org$chromium$net$telemetry$OptionalBoolean = r0;
            r0[OptionalBoolean.TRUE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$org$chromium$net$telemetry$OptionalBoolean[OptionalBoolean.FALSE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$chromium$net$telemetry$OptionalBoolean[OptionalBoolean.UNSET.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public AndroidHttpEngineBuilderWrapper(HttpEngine.Builder r1) {
        this.mBackend = r1;
    }

    private static int optionalBooleanToMigrationOptionState(OptionalBoolean r3) {
        int r0 = AnonymousClass1.$SwitchMap$org$chromium$net$telemetry$OptionalBoolean[r3.ordinal()];
        int r1 = 1;
        if (r0 == 1) goto L11;
        r1 = 2;
        if (r0 == 2) goto L11;
        if (r0 != 3) goto L9;
        return 0;
    L9:
        Buffer$$ExternalSyntheticBUOutline2.m("Invalid OptionalBoolean value: ".concat(String.valueOf(r3)));
        return 0;
    L11:
        return r1;
    }

    public static ConnectionMigrationOptions parseConnectionMigrationOptions(ExperimentalOptions r2) {
        ConnectionMigrationOptions.Builder r0 = AndroidHttpEngineBuilderWrapper$$ExternalSyntheticApiModelOutline7.m();
        r0.setDefaultNetworkMigration(optionalBooleanToMigrationOptionState(r2.getMigrateSessionsOnNetworkChangeV2Option()));
        r0.setPathDegradationMigration(optionalBooleanToMigrationOptionState(r2.getAllowPortMigration()));
        OptionalBoolean r22 = r2.getMigrateSessionsEarlyV2();
        r0.setAllowNonDefaultNetworkUsage(optionalBooleanToMigrationOptionState(r22));
        OptionalBoolean r1 = OptionalBoolean.TRUE;
        if (r22 != r1) goto L6;
        r0.setPathDegradationMigration(optionalBooleanToMigrationOptionState(r1));
    L6:
        return r0.build();
    }

    public static DnsOptions parseDnsOptions(ExperimentalOptions r5) {
        DnsOptions.StaleDnsOptions.Builder r0 = AndroidHttpEngineBuilderWrapper$$ExternalSyntheticApiModelOutline4.m();
        int r1 = r5.getStaleDnsDelayMillisOption();
        if (r1 == (-1)) goto L5;
        AndroidHttpEngineBuilderWrapper$$ExternalSyntheticAPIConversion0.m(r0, Duration.ofMillis(r1));
    L5:
        int r12 = r5.getStaleDnsMaxExpiredTimeMillisOption();
        if (r12 == (-1)) goto L8;
        AndroidHttpEngineBuilderWrapper$$ExternalSyntheticAPIConversion1.m(r0, Duration.ofMillis(r12));
    L8:
        r0.setAllowCrossNetworkUsage(optionalBooleanToMigrationOptionState(r5.getStaleDnsAllowOtherNetworkOption())).setUseStaleOnNameNotResolved(optionalBooleanToMigrationOptionState(r5.getStaleDnsUseStaleOnNameNotResolvedOption()));
        DnsOptions.Builder r13 = AndroidHttpEngineBuilderWrapper$$ExternalSyntheticApiModelOutline5.m();
        r13.setUseHttpStackDnsResolver(optionalBooleanToMigrationOptionState(r5.getAsyncDnsEnableOption())).setStaleDns(optionalBooleanToMigrationOptionState(r5.getStaleDnsEnableOption())).setStaleDnsOptions(r0.build()).setPreestablishConnectionsToStaleDnsResults(optionalBooleanToMigrationOptionState(r5.getRaceStaleDnsOnConnection())).setPersistHostCache(optionalBooleanToMigrationOptionState(r5.getStaleDnsPersistToDiskOption()));
        int r52 = r5.getStaleDnsPersistDelayMillisOption();
        if (r52 == (-1)) goto L12;
        AndroidHttpEngineBuilderWrapper$$ExternalSyntheticAPIConversion2.m(r13, Duration.ofMillis(r52));
    L12:
        return r13.build();
    }

    public static QuicOptions parseQuicOptions(ExperimentalOptions r5) {
        QuicOptions.Builder r0 = AndroidHttpEngineBuilderWrapper$$ExternalSyntheticApiModelOutline6.m();
        if (r5.getHostWhitelist() == null) goto L7;
        String[] r1 = r5.getHostWhitelist().split(",");
        int r2 = r1.length;
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L7;
        r0.addAllowedQuicHost(r1[r3]);
        r3 = r3 + 1;
    L7:
        int r12 = r5.getMaxServerConfigsStoredInPropertiesOption();
        if (r12 == (-1)) goto L10;
        r0.setInMemoryServerConfigsCacheSize(r12);
    L10:
        String r13 = r5.getUserAgentId();
        if (r13 == null) goto L13;
        r0.setHandshakeUserAgent(r13);
    L13:
        int r52 = r5.getIdleConnectionTimeoutSecondsOption();
        if (r52 == (-1)) goto L17;
        AndroidHttpEngineBuilderWrapper$$ExternalSyntheticAPIConversion3.m(r0, Duration.ofSeconds(r52));
    L17:
        return r0.build();
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public ExperimentalCronetEngine build() {
        return new AndroidHttpEngineWrapper(this.mBackend.build());
    }

    @Override // org.chromium.net.ICronetEngineBuilder
    public ICronetEngineBuilder setExperimentalOptions(String r3) {
        ExperimentalOptions r0 = new ExperimentalOptions(r3);
        this.mBackend.setConnectionMigrationOptions(parseConnectionMigrationOptions(r0));
        this.mBackend.setDnsOptions(parseDnsOptions(r0));
        this.mBackend.setQuicOptions(parseQuicOptions(r0));
        return this;
    }
}
