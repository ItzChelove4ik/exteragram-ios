package com.google.android.datatransport.runtime;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;

/* loaded from: classes4.dex */
public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    public static final Configurator CONFIG = null;

    public static final class ClientMetricsEncoder implements ObjectEncoder<ClientMetrics> {
        private static final FieldDescriptor APPNAMESPACE_DESCRIPTOR = null;
        private static final FieldDescriptor GLOBALMETRICS_DESCRIPTOR = null;
        static final ClientMetricsEncoder INSTANCE = null;
        private static final FieldDescriptor LOGSOURCEMETRICS_DESCRIPTOR = null;
        private static final FieldDescriptor WINDOW_DESCRIPTOR = null;

        static {
            INSTANCE = new ClientMetricsEncoder();
            WINDOW_DESCRIPTOR = FieldDescriptor.builder("window").withProperty(AtProtobuf.builder().tag(1).build()).build();
            LOGSOURCEMETRICS_DESCRIPTOR = FieldDescriptor.builder("logSourceMetrics").withProperty(AtProtobuf.builder().tag(2).build()).build();
            GLOBALMETRICS_DESCRIPTOR = FieldDescriptor.builder("globalMetrics").withProperty(AtProtobuf.builder().tag(3).build()).build();
            APPNAMESPACE_DESCRIPTOR = FieldDescriptor.builder("appNamespace").withProperty(AtProtobuf.builder().tag(4).build()).build();
        }

        private ClientMetricsEncoder() {
        }

        public void encode(ClientMetrics r2, ObjectEncoderContext r3) {
            r3.add(WINDOW_DESCRIPTOR, r2.getWindowInternal());
            r3.add(LOGSOURCEMETRICS_DESCRIPTOR, r2.getLogSourceMetricsList());
            r3.add(GLOBALMETRICS_DESCRIPTOR, r2.getGlobalMetricsInternal());
            r3.add(APPNAMESPACE_DESCRIPTOR, r2.getAppNamespace());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ClientMetrics) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class GlobalMetricsEncoder implements ObjectEncoder<GlobalMetrics> {
        static final GlobalMetricsEncoder INSTANCE = null;
        private static final FieldDescriptor STORAGEMETRICS_DESCRIPTOR = null;

        static {
            INSTANCE = new GlobalMetricsEncoder();
            STORAGEMETRICS_DESCRIPTOR = FieldDescriptor.builder("storageMetrics").withProperty(AtProtobuf.builder().tag(1).build()).build();
        }

        private GlobalMetricsEncoder() {
        }

        public void encode(GlobalMetrics r1, ObjectEncoderContext r2) {
            r2.add(STORAGEMETRICS_DESCRIPTOR, r1.getStorageMetricsInternal());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((GlobalMetrics) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class LogEventDroppedEncoder implements ObjectEncoder<LogEventDropped> {
        private static final FieldDescriptor EVENTSDROPPEDCOUNT_DESCRIPTOR = null;
        static final LogEventDroppedEncoder INSTANCE = null;
        private static final FieldDescriptor REASON_DESCRIPTOR = null;

        static {
            INSTANCE = new LogEventDroppedEncoder();
            EVENTSDROPPEDCOUNT_DESCRIPTOR = FieldDescriptor.builder("eventsDroppedCount").withProperty(AtProtobuf.builder().tag(1).build()).build();
            REASON_DESCRIPTOR = FieldDescriptor.builder("reason").withProperty(AtProtobuf.builder().tag(3).build()).build();
        }

        private LogEventDroppedEncoder() {
        }

        public void encode(LogEventDropped r3, ObjectEncoderContext r4) {
            r4.add(EVENTSDROPPEDCOUNT_DESCRIPTOR, r3.getEventsDroppedCount());
            r4.add(REASON_DESCRIPTOR, r3.getReason());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((LogEventDropped) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class LogSourceMetricsEncoder implements ObjectEncoder<LogSourceMetrics> {
        static final LogSourceMetricsEncoder INSTANCE = null;
        private static final FieldDescriptor LOGEVENTDROPPED_DESCRIPTOR = null;
        private static final FieldDescriptor LOGSOURCE_DESCRIPTOR = null;

        static {
            INSTANCE = new LogSourceMetricsEncoder();
            LOGSOURCE_DESCRIPTOR = FieldDescriptor.builder("logSource").withProperty(AtProtobuf.builder().tag(1).build()).build();
            LOGEVENTDROPPED_DESCRIPTOR = FieldDescriptor.builder("logEventDropped").withProperty(AtProtobuf.builder().tag(2).build()).build();
        }

        private LogSourceMetricsEncoder() {
        }

        public void encode(LogSourceMetrics r2, ObjectEncoderContext r3) {
            r3.add(LOGSOURCE_DESCRIPTOR, r2.getLogSource());
            r3.add(LOGEVENTDROPPED_DESCRIPTOR, r2.getLogEventDroppedList());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((LogSourceMetrics) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder<ProtoEncoderDoNotUse> {
        private static final FieldDescriptor CLIENTMETRICS_DESCRIPTOR = null;
        static final ProtoEncoderDoNotUseEncoder INSTANCE = null;

        static {
            INSTANCE = new ProtoEncoderDoNotUseEncoder();
            CLIENTMETRICS_DESCRIPTOR = FieldDescriptor.of("clientMetrics");
        }

        private ProtoEncoderDoNotUseEncoder() {
        }

        public void encode(ProtoEncoderDoNotUse r1, ObjectEncoderContext r2) {
            throw null;
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
            encode(null, (ObjectEncoderContext) r2);
        }
    }

    public static final class StorageMetricsEncoder implements ObjectEncoder<StorageMetrics> {
        private static final FieldDescriptor CURRENTCACHESIZEBYTES_DESCRIPTOR = null;
        static final StorageMetricsEncoder INSTANCE = null;
        private static final FieldDescriptor MAXCACHESIZEBYTES_DESCRIPTOR = null;

        static {
            INSTANCE = new StorageMetricsEncoder();
            CURRENTCACHESIZEBYTES_DESCRIPTOR = FieldDescriptor.builder("currentCacheSizeBytes").withProperty(AtProtobuf.builder().tag(1).build()).build();
            MAXCACHESIZEBYTES_DESCRIPTOR = FieldDescriptor.builder("maxCacheSizeBytes").withProperty(AtProtobuf.builder().tag(2).build()).build();
        }

        private StorageMetricsEncoder() {
        }

        public void encode(StorageMetrics r3, ObjectEncoderContext r4) {
            r4.add(CURRENTCACHESIZEBYTES_DESCRIPTOR, r3.getCurrentCacheSizeBytes());
            r4.add(MAXCACHESIZEBYTES_DESCRIPTOR, r3.getMaxCacheSizeBytes());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((StorageMetrics) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class TimeWindowEncoder implements ObjectEncoder<TimeWindow> {
        private static final FieldDescriptor ENDMS_DESCRIPTOR = null;
        static final TimeWindowEncoder INSTANCE = null;
        private static final FieldDescriptor STARTMS_DESCRIPTOR = null;

        static {
            INSTANCE = new TimeWindowEncoder();
            STARTMS_DESCRIPTOR = FieldDescriptor.builder("startMs").withProperty(AtProtobuf.builder().tag(1).build()).build();
            ENDMS_DESCRIPTOR = FieldDescriptor.builder("endMs").withProperty(AtProtobuf.builder().tag(2).build()).build();
        }

        private TimeWindowEncoder() {
        }

        public void encode(TimeWindow r3, ObjectEncoderContext r4) {
            r4.add(STARTMS_DESCRIPTOR, r3.getStartMs());
            r4.add(ENDMS_DESCRIPTOR, r3.getEndMs());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((TimeWindow) r1, (ObjectEncoderContext) r2);
        }
    }

    static {
        CONFIG = new AutoProtoEncoderDoNotUseEncoder();
    }

    private AutoProtoEncoderDoNotUseEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> r2) {
        r2.registerEncoder(ProtoEncoderDoNotUse.class, ProtoEncoderDoNotUseEncoder.INSTANCE);
        r2.registerEncoder(ClientMetrics.class, ClientMetricsEncoder.INSTANCE);
        r2.registerEncoder(TimeWindow.class, TimeWindowEncoder.INSTANCE);
        r2.registerEncoder(LogSourceMetrics.class, LogSourceMetricsEncoder.INSTANCE);
        r2.registerEncoder(LogEventDropped.class, LogEventDroppedEncoder.INSTANCE);
        r2.registerEncoder(GlobalMetrics.class, GlobalMetricsEncoder.INSTANCE);
        r2.registerEncoder(StorageMetrics.class, StorageMetricsEncoder.INSTANCE);
    }
}
