package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;

/* loaded from: classes4.dex */
public final class AutoBatchedLogRequestEncoder implements Configurator {
    public static final Configurator CONFIG = null;

    public static final class AndroidClientInfoEncoder implements ObjectEncoder<AndroidClientInfo> {
        private static final FieldDescriptor APPLICATIONBUILD_DESCRIPTOR = null;
        private static final FieldDescriptor COUNTRY_DESCRIPTOR = null;
        private static final FieldDescriptor DEVICE_DESCRIPTOR = null;
        private static final FieldDescriptor FINGERPRINT_DESCRIPTOR = null;
        private static final FieldDescriptor HARDWARE_DESCRIPTOR = null;
        static final AndroidClientInfoEncoder INSTANCE = null;
        private static final FieldDescriptor LOCALE_DESCRIPTOR = null;
        private static final FieldDescriptor MANUFACTURER_DESCRIPTOR = null;
        private static final FieldDescriptor MCCMNC_DESCRIPTOR = null;
        private static final FieldDescriptor MODEL_DESCRIPTOR = null;
        private static final FieldDescriptor OSBUILD_DESCRIPTOR = null;
        private static final FieldDescriptor PRODUCT_DESCRIPTOR = null;
        private static final FieldDescriptor SDKVERSION_DESCRIPTOR = null;

        static {
            INSTANCE = new AndroidClientInfoEncoder();
            SDKVERSION_DESCRIPTOR = FieldDescriptor.of("sdkVersion");
            MODEL_DESCRIPTOR = FieldDescriptor.of("model");
            HARDWARE_DESCRIPTOR = FieldDescriptor.of("hardware");
            DEVICE_DESCRIPTOR = FieldDescriptor.of("device");
            PRODUCT_DESCRIPTOR = FieldDescriptor.of("product");
            OSBUILD_DESCRIPTOR = FieldDescriptor.of("osBuild");
            MANUFACTURER_DESCRIPTOR = FieldDescriptor.of("manufacturer");
            FINGERPRINT_DESCRIPTOR = FieldDescriptor.of("fingerprint");
            LOCALE_DESCRIPTOR = FieldDescriptor.of("locale");
            COUNTRY_DESCRIPTOR = FieldDescriptor.of("country");
            MCCMNC_DESCRIPTOR = FieldDescriptor.of("mccMnc");
            APPLICATIONBUILD_DESCRIPTOR = FieldDescriptor.of("applicationBuild");
        }

        private AndroidClientInfoEncoder() {
        }

        public void encode(AndroidClientInfo r2, ObjectEncoderContext r3) {
            r3.add(SDKVERSION_DESCRIPTOR, r2.getSdkVersion());
            r3.add(MODEL_DESCRIPTOR, r2.getModel());
            r3.add(HARDWARE_DESCRIPTOR, r2.getHardware());
            r3.add(DEVICE_DESCRIPTOR, r2.getDevice());
            r3.add(PRODUCT_DESCRIPTOR, r2.getProduct());
            r3.add(OSBUILD_DESCRIPTOR, r2.getOsBuild());
            r3.add(MANUFACTURER_DESCRIPTOR, r2.getManufacturer());
            r3.add(FINGERPRINT_DESCRIPTOR, r2.getFingerprint());
            r3.add(LOCALE_DESCRIPTOR, r2.getLocale());
            r3.add(COUNTRY_DESCRIPTOR, r2.getCountry());
            r3.add(MCCMNC_DESCRIPTOR, r2.getMccMnc());
            r3.add(APPLICATIONBUILD_DESCRIPTOR, r2.getApplicationBuild());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((AndroidClientInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class BatchedLogRequestEncoder implements ObjectEncoder<BatchedLogRequest> {
        static final BatchedLogRequestEncoder INSTANCE = null;
        private static final FieldDescriptor LOGREQUEST_DESCRIPTOR = null;

        static {
            INSTANCE = new BatchedLogRequestEncoder();
            LOGREQUEST_DESCRIPTOR = FieldDescriptor.of("logRequest");
        }

        private BatchedLogRequestEncoder() {
        }

        public void encode(BatchedLogRequest r1, ObjectEncoderContext r2) {
            r2.add(LOGREQUEST_DESCRIPTOR, r1.getLogRequests());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((BatchedLogRequest) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ClientInfoEncoder implements ObjectEncoder<ClientInfo> {
        private static final FieldDescriptor ANDROIDCLIENTINFO_DESCRIPTOR = null;
        private static final FieldDescriptor CLIENTTYPE_DESCRIPTOR = null;
        static final ClientInfoEncoder INSTANCE = null;

        static {
            INSTANCE = new ClientInfoEncoder();
            CLIENTTYPE_DESCRIPTOR = FieldDescriptor.of("clientType");
            ANDROIDCLIENTINFO_DESCRIPTOR = FieldDescriptor.of("androidClientInfo");
        }

        private ClientInfoEncoder() {
        }

        public void encode(ClientInfo r2, ObjectEncoderContext r3) {
            r3.add(CLIENTTYPE_DESCRIPTOR, r2.getClientType());
            r3.add(ANDROIDCLIENTINFO_DESCRIPTOR, r2.getAndroidClientInfo());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ClientInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ComplianceDataEncoder implements ObjectEncoder<ComplianceData> {
        static final ComplianceDataEncoder INSTANCE = null;
        private static final FieldDescriptor PRIVACYCONTEXT_DESCRIPTOR = null;
        private static final FieldDescriptor PRODUCTIDORIGIN_DESCRIPTOR = null;

        static {
            INSTANCE = new ComplianceDataEncoder();
            PRIVACYCONTEXT_DESCRIPTOR = FieldDescriptor.of("privacyContext");
            PRODUCTIDORIGIN_DESCRIPTOR = FieldDescriptor.of("productIdOrigin");
        }

        private ComplianceDataEncoder() {
        }

        public void encode(ComplianceData r2, ObjectEncoderContext r3) {
            r3.add(PRIVACYCONTEXT_DESCRIPTOR, r2.getPrivacyContext());
            r3.add(PRODUCTIDORIGIN_DESCRIPTOR, r2.getProductIdOrigin());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ComplianceData) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ExperimentIdsEncoder implements ObjectEncoder<ExperimentIds> {
        private static final FieldDescriptor CLEARBLOB_DESCRIPTOR = null;
        private static final FieldDescriptor ENCRYPTEDBLOB_DESCRIPTOR = null;
        static final ExperimentIdsEncoder INSTANCE = null;

        static {
            INSTANCE = new ExperimentIdsEncoder();
            CLEARBLOB_DESCRIPTOR = FieldDescriptor.of("clearBlob");
            ENCRYPTEDBLOB_DESCRIPTOR = FieldDescriptor.of("encryptedBlob");
        }

        private ExperimentIdsEncoder() {
        }

        public void encode(ExperimentIds r2, ObjectEncoderContext r3) {
            r3.add(CLEARBLOB_DESCRIPTOR, r2.getClearBlob());
            r3.add(ENCRYPTEDBLOB_DESCRIPTOR, r2.getEncryptedBlob());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ExperimentIds) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ExternalPRequestContextEncoder implements ObjectEncoder<ExternalPRequestContext> {
        static final ExternalPRequestContextEncoder INSTANCE = null;
        private static final FieldDescriptor ORIGINASSOCIATEDPRODUCTID_DESCRIPTOR = null;

        static {
            INSTANCE = new ExternalPRequestContextEncoder();
            ORIGINASSOCIATEDPRODUCTID_DESCRIPTOR = FieldDescriptor.of("originAssociatedProductId");
        }

        private ExternalPRequestContextEncoder() {
        }

        public void encode(ExternalPRequestContext r1, ObjectEncoderContext r2) {
            r2.add(ORIGINASSOCIATEDPRODUCTID_DESCRIPTOR, r1.getOriginAssociatedProductId());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ExternalPRequestContext) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class ExternalPrivacyContextEncoder implements ObjectEncoder<ExternalPrivacyContext> {
        static final ExternalPrivacyContextEncoder INSTANCE = null;
        private static final FieldDescriptor PREQUEST_DESCRIPTOR = null;

        static {
            INSTANCE = new ExternalPrivacyContextEncoder();
            PREQUEST_DESCRIPTOR = FieldDescriptor.of("prequest");
        }

        private ExternalPrivacyContextEncoder() {
        }

        public void encode(ExternalPrivacyContext r1, ObjectEncoderContext r2) {
            r2.add(PREQUEST_DESCRIPTOR, r1.getPrequest());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((ExternalPrivacyContext) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class LogEventEncoder implements ObjectEncoder<LogEvent> {
        private static final FieldDescriptor COMPLIANCEDATA_DESCRIPTOR = null;
        private static final FieldDescriptor EVENTCODE_DESCRIPTOR = null;
        private static final FieldDescriptor EVENTTIMEMS_DESCRIPTOR = null;
        private static final FieldDescriptor EVENTUPTIMEMS_DESCRIPTOR = null;
        private static final FieldDescriptor EXPERIMENTIDS_DESCRIPTOR = null;
        static final LogEventEncoder INSTANCE = null;
        private static final FieldDescriptor NETWORKCONNECTIONINFO_DESCRIPTOR = null;
        private static final FieldDescriptor SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR = null;
        private static final FieldDescriptor SOURCEEXTENSION_DESCRIPTOR = null;
        private static final FieldDescriptor TIMEZONEOFFSETSECONDS_DESCRIPTOR = null;

        static {
            INSTANCE = new LogEventEncoder();
            EVENTTIMEMS_DESCRIPTOR = FieldDescriptor.of("eventTimeMs");
            EVENTCODE_DESCRIPTOR = FieldDescriptor.of("eventCode");
            COMPLIANCEDATA_DESCRIPTOR = FieldDescriptor.of("complianceData");
            EVENTUPTIMEMS_DESCRIPTOR = FieldDescriptor.of("eventUptimeMs");
            SOURCEEXTENSION_DESCRIPTOR = FieldDescriptor.of("sourceExtension");
            SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR = FieldDescriptor.of("sourceExtensionJsonProto3");
            TIMEZONEOFFSETSECONDS_DESCRIPTOR = FieldDescriptor.of("timezoneOffsetSeconds");
            NETWORKCONNECTIONINFO_DESCRIPTOR = FieldDescriptor.of("networkConnectionInfo");
            EXPERIMENTIDS_DESCRIPTOR = FieldDescriptor.of("experimentIds");
        }

        private LogEventEncoder() {
        }

        public void encode(LogEvent r3, ObjectEncoderContext r4) {
            r4.add(EVENTTIMEMS_DESCRIPTOR, r3.getEventTimeMs());
            r4.add(EVENTCODE_DESCRIPTOR, r3.getEventCode());
            r4.add(COMPLIANCEDATA_DESCRIPTOR, r3.getComplianceData());
            r4.add(EVENTUPTIMEMS_DESCRIPTOR, r3.getEventUptimeMs());
            r4.add(SOURCEEXTENSION_DESCRIPTOR, r3.getSourceExtension());
            r4.add(SOURCEEXTENSIONJSONPROTO3_DESCRIPTOR, r3.getSourceExtensionJsonProto3());
            r4.add(TIMEZONEOFFSETSECONDS_DESCRIPTOR, r3.getTimezoneOffsetSeconds());
            r4.add(NETWORKCONNECTIONINFO_DESCRIPTOR, r3.getNetworkConnectionInfo());
            r4.add(EXPERIMENTIDS_DESCRIPTOR, r3.getExperimentIds());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((LogEvent) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class LogRequestEncoder implements ObjectEncoder<LogRequest> {
        private static final FieldDescriptor CLIENTINFO_DESCRIPTOR = null;
        static final LogRequestEncoder INSTANCE = null;
        private static final FieldDescriptor LOGEVENT_DESCRIPTOR = null;
        private static final FieldDescriptor LOGSOURCENAME_DESCRIPTOR = null;
        private static final FieldDescriptor LOGSOURCE_DESCRIPTOR = null;
        private static final FieldDescriptor QOSTIER_DESCRIPTOR = null;
        private static final FieldDescriptor REQUESTTIMEMS_DESCRIPTOR = null;
        private static final FieldDescriptor REQUESTUPTIMEMS_DESCRIPTOR = null;

        static {
            INSTANCE = new LogRequestEncoder();
            REQUESTTIMEMS_DESCRIPTOR = FieldDescriptor.of("requestTimeMs");
            REQUESTUPTIMEMS_DESCRIPTOR = FieldDescriptor.of("requestUptimeMs");
            CLIENTINFO_DESCRIPTOR = FieldDescriptor.of("clientInfo");
            LOGSOURCE_DESCRIPTOR = FieldDescriptor.of("logSource");
            LOGSOURCENAME_DESCRIPTOR = FieldDescriptor.of("logSourceName");
            LOGEVENT_DESCRIPTOR = FieldDescriptor.of("logEvent");
            QOSTIER_DESCRIPTOR = FieldDescriptor.of("qosTier");
        }

        private LogRequestEncoder() {
        }

        public void encode(LogRequest r3, ObjectEncoderContext r4) {
            r4.add(REQUESTTIMEMS_DESCRIPTOR, r3.getRequestTimeMs());
            r4.add(REQUESTUPTIMEMS_DESCRIPTOR, r3.getRequestUptimeMs());
            r4.add(CLIENTINFO_DESCRIPTOR, r3.getClientInfo());
            r4.add(LOGSOURCE_DESCRIPTOR, r3.getLogSource());
            r4.add(LOGSOURCENAME_DESCRIPTOR, r3.getLogSourceName());
            r4.add(LOGEVENT_DESCRIPTOR, r3.getLogEvents());
            r4.add(QOSTIER_DESCRIPTOR, r3.getQosTier());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((LogRequest) r1, (ObjectEncoderContext) r2);
        }
    }

    public static final class NetworkConnectionInfoEncoder implements ObjectEncoder<NetworkConnectionInfo> {
        static final NetworkConnectionInfoEncoder INSTANCE = null;
        private static final FieldDescriptor MOBILESUBTYPE_DESCRIPTOR = null;
        private static final FieldDescriptor NETWORKTYPE_DESCRIPTOR = null;

        static {
            INSTANCE = new NetworkConnectionInfoEncoder();
            NETWORKTYPE_DESCRIPTOR = FieldDescriptor.of("networkType");
            MOBILESUBTYPE_DESCRIPTOR = FieldDescriptor.of("mobileSubtype");
        }

        private NetworkConnectionInfoEncoder() {
        }

        public void encode(NetworkConnectionInfo r2, ObjectEncoderContext r3) {
            r3.add(NETWORKTYPE_DESCRIPTOR, r2.getNetworkType());
            r3.add(MOBILESUBTYPE_DESCRIPTOR, r2.getMobileSubtype());
        }

        @Override // com.google.firebase.encoders.ObjectEncoder
        public /* bridge */ /* synthetic */ void encode(Object r1, Object r2) {
            encode((NetworkConnectionInfo) r1, (ObjectEncoderContext) r2);
        }
    }

    static {
        CONFIG = new AutoBatchedLogRequestEncoder();
    }

    private AutoBatchedLogRequestEncoder() {
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> r2) {
        BatchedLogRequestEncoder r1 = BatchedLogRequestEncoder.INSTANCE;
        r2.registerEncoder(BatchedLogRequest.class, r1);
        r2.registerEncoder(AutoValue_BatchedLogRequest.class, r1);
        LogRequestEncoder r12 = LogRequestEncoder.INSTANCE;
        r2.registerEncoder(LogRequest.class, r12);
        r2.registerEncoder(AutoValue_LogRequest.class, r12);
        ClientInfoEncoder r13 = ClientInfoEncoder.INSTANCE;
        r2.registerEncoder(ClientInfo.class, r13);
        r2.registerEncoder(AutoValue_ClientInfo.class, r13);
        AndroidClientInfoEncoder r14 = AndroidClientInfoEncoder.INSTANCE;
        r2.registerEncoder(AndroidClientInfo.class, r14);
        r2.registerEncoder(AutoValue_AndroidClientInfo.class, r14);
        LogEventEncoder r15 = LogEventEncoder.INSTANCE;
        r2.registerEncoder(LogEvent.class, r15);
        r2.registerEncoder(AutoValue_LogEvent.class, r15);
        ComplianceDataEncoder r16 = ComplianceDataEncoder.INSTANCE;
        r2.registerEncoder(ComplianceData.class, r16);
        r2.registerEncoder(AutoValue_ComplianceData.class, r16);
        ExternalPrivacyContextEncoder r17 = ExternalPrivacyContextEncoder.INSTANCE;
        r2.registerEncoder(ExternalPrivacyContext.class, r17);
        r2.registerEncoder(AutoValue_ExternalPrivacyContext.class, r17);
        ExternalPRequestContextEncoder r18 = ExternalPRequestContextEncoder.INSTANCE;
        r2.registerEncoder(ExternalPRequestContext.class, r18);
        r2.registerEncoder(AutoValue_ExternalPRequestContext.class, r18);
        NetworkConnectionInfoEncoder r19 = NetworkConnectionInfoEncoder.INSTANCE;
        r2.registerEncoder(NetworkConnectionInfo.class, r19);
        r2.registerEncoder(AutoValue_NetworkConnectionInfo.class, r19);
        ExperimentIdsEncoder r110 = ExperimentIdsEncoder.INSTANCE;
        r2.registerEncoder(ExperimentIds.class, r110);
        r2.registerEncoder(AutoValue_ExperimentIds.class, r110);
    }
}
