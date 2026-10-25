package com.google.android.datatransport.runtime;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.runtime.EventInternal;
import java.util.Arrays;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class AutoValue_EventInternal extends EventInternal {
    private final Map<String, String> autoMetadata;
    private final Integer code;
    private final EncodedPayload encodedPayload;
    private final long eventMillis;
    private final byte[] experimentIdsClear;
    private final byte[] experimentIdsEncrypted;
    private final Integer productId;
    private final String pseudonymousId;
    private final String transportName;
    private final long uptimeMillis;

    /* renamed from: com.google.android.datatransport.runtime.AutoValue_EventInternal$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends EventInternal.Builder {
        private Map<String, String> autoMetadata;
        private Integer code;
        private EncodedPayload encodedPayload;
        private Long eventMillis;
        private byte[] experimentIdsClear;
        private byte[] experimentIdsEncrypted;
        private Integer productId;
        private String pseudonymousId;
        private String transportName;
        private Long uptimeMillis;

        public Builder() {
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal build() {
            if (this.transportName != null) goto L5;
            String r1 = " transportName";
        L7:
            if (this.encodedPayload != null) goto L10;
            r1 = r1.concat(" encodedPayload");
        L10:
            if (this.eventMillis != null) goto L13;
            r1 = r1.concat(" eventMillis");
        L13:
            if (this.uptimeMillis != null) goto L16;
            r1 = r1.concat(" uptimeMillis");
        L16:
            if (this.autoMetadata != null) goto L19;
            r1 = r1.concat(" autoMetadata");
        L19:
            if (r1.isEmpty() == true) goto L21;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r1));
            return null;
        L21:
            return new AutoValue_EventInternal(this.transportName, this.code, this.encodedPayload, this.eventMillis.longValue(), this.uptimeMillis.longValue(), this.autoMetadata, this.productId, this.pseudonymousId, this.experimentIdsClear, this.experimentIdsEncrypted, null);
        L5:
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public Map<String, String> getAutoMetadata() {
            Map<String, String> r0 = this.autoMetadata;
            if (r0 == null) goto L5;
            return r0;
        L5:
            Segment$$ExternalSyntheticBUOutline1.m("Property \"autoMetadata\" has not been set");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setAutoMetadata(Map<String, String> r1) {
            if (r1 == null) goto L5;
            this.autoMetadata = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null autoMetadata");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setCode(Integer r1) {
            this.code = r1;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setEncodedPayload(EncodedPayload r1) {
            if (r1 == null) goto L5;
            this.encodedPayload = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null encodedPayload");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setEventMillis(long r1) {
            this.eventMillis = Long.valueOf(r1);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setExperimentIdsClear(byte[] r1) {
            this.experimentIdsClear = r1;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setExperimentIdsEncrypted(byte[] r1) {
            this.experimentIdsEncrypted = r1;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setProductId(Integer r1) {
            this.productId = r1;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setPseudonymousId(String r1) {
            this.pseudonymousId = r1;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setTransportName(String r1) {
            if (r1 == null) goto L5;
            this.transportName = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null transportName");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.EventInternal.Builder
        public EventInternal.Builder setUptimeMillis(long r1) {
            this.uptimeMillis = Long.valueOf(r1);
            return this;
        }
    }

    private AutoValue_EventInternal(String r1, Integer r2, EncodedPayload r3, long r4, long r6, Map<String, String> r8, Integer r9, String r10, byte[] r11, byte[] r12) {
        this.transportName = r1;
        this.code = r2;
        this.encodedPayload = r3;
        this.eventMillis = r4;
        this.uptimeMillis = r6;
        this.autoMetadata = r8;
        this.productId = r9;
        this.pseudonymousId = r10;
        this.experimentIdsClear = r11;
        this.experimentIdsEncrypted = r12;
    }

    public /* synthetic */ AutoValue_EventInternal(String r1, Integer r2, EncodedPayload r3, long r4, long r6, Map r8, Integer r9, String r10, byte[] r11, byte[] r12, AnonymousClass1 r13) {
        this(r1, r2, r3, r4, r6, r8, r9, r10, r11, r12);
    }

    public boolean equals(Object r8) {
        if (r8 != this) goto L6;
        return true;
    L6:
        if ((r8 instanceof EventInternal) == false) goto L51;
        EventInternal r82 = (EventInternal) r8;
        if (this.transportName.equals(r82.getTransportName()) == false) goto L51;
        Integer r1 = this.code;
        if (r1 != null) goto L15;
        if (r82.getCode() != null) goto L51;
    L17:
        if (this.encodedPayload.equals(r82.getEncodedPayload()) == false) goto L51;
        if (this.eventMillis != r82.getEventMillis()) goto L51;
        if (this.uptimeMillis != r82.getUptimeMillis()) goto L51;
        if (this.autoMetadata.equals(r82.getAutoMetadata()) == false) goto L51;
        Integer r12 = this.productId;
        if (r12 != null) goto L30;
        if (r82.getProductId() != null) goto L51;
    L31:
        String r13 = this.pseudonymousId;
        if (r13 != null) goto L37;
        if (r82.getPseudonymousId() != null) goto L51;
    L38:
        byte[] r14 = this.experimentIdsClear;
        boolean r3 = r82 instanceof AutoValue_EventInternal;
        if (r3 == false) goto L41;
        byte[] r4 = ((AutoValue_EventInternal) r82).experimentIdsClear;
    L43:
        if (Arrays.equals(r14, r4) == false) goto L51;
        byte[] r7 = this.experimentIdsEncrypted;
        if (r3 == false) goto L47;
        byte[] r83 = ((AutoValue_EventInternal) r82).experimentIdsEncrypted;
    L49:
        if (Arrays.equals(r7, r83) == false) goto L51;
        return true;
    L47:
        r83 = r82.getExperimentIdsEncrypted();
        goto L49
    L41:
        r4 = r82.getExperimentIdsClear();
        goto L43
    L37:
        if (r13.equals(r82.getPseudonymousId()) == false) goto L51;
    L30:
        if (r12.equals(r82.getProductId()) == false) goto L51;
    L15:
        if (r1.equals(r82.getCode()) == true) goto L17;
    L51:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public Map<String, String> getAutoMetadata() {
        return this.autoMetadata;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public Integer getCode() {
        return this.code;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public EncodedPayload getEncodedPayload() {
        return this.encodedPayload;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long getEventMillis() {
        return this.eventMillis;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public byte[] getExperimentIdsClear() {
        return this.experimentIdsClear;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public byte[] getExperimentIdsEncrypted() {
        return this.experimentIdsEncrypted;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public Integer getProductId() {
        return this.productId;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public String getPseudonymousId() {
        return this.pseudonymousId;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public String getTransportName() {
        return this.transportName;
    }

    @Override // com.google.android.datatransport.runtime.EventInternal
    public long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public int hashCode() {
        int r0 = (this.transportName.hashCode() ^ 1000003) * 1000003;
        Integer r2 = this.code;
        int r3 = 0;
        if (r2 != null) goto L5;
        int r22 = 0;
    L6:
        int r02 = (((r0 ^ r22) * 1000003) ^ this.encodedPayload.hashCode()) * 1000003;
        long r4 = this.eventMillis;
        int r03 = (r02 ^ ((int) (r4 ^ (r4 >>> 32)))) * 1000003;
        long r42 = this.uptimeMillis;
        int r04 = (((r03 ^ ((int) (r42 ^ (r42 >>> 32)))) * 1000003) ^ this.autoMetadata.hashCode()) * 1000003;
        Integer r23 = this.productId;
        if (r23 != null) goto L9;
        int r24 = 0;
    L10:
        int r05 = (r04 ^ r24) * 1000003;
        String r25 = this.pseudonymousId;
        if (r25 == null) goto L15;
        r3 = r25.hashCode();
    L15:
        return Arrays.hashCode(this.experimentIdsEncrypted) ^ ((((r05 ^ r3) * 1000003) ^ Arrays.hashCode(this.experimentIdsClear)) * 1000003);
    L9:
        r24 = r23.hashCode();
        goto L10
    L5:
        r22 = r2.hashCode();
        goto L6
    }

    public String toString() {
        return "EventInternal{transportName=" + this.transportName + ", code=" + this.code + ", encodedPayload=" + this.encodedPayload + ", eventMillis=" + this.eventMillis + ", uptimeMillis=" + this.uptimeMillis + ", autoMetadata=" + this.autoMetadata + ", productId=" + this.productId + ", pseudonymousId=" + this.pseudonymousId + ", experimentIdsClear=" + Arrays.toString(this.experimentIdsClear) + ", experimentIdsEncrypted=" + Arrays.toString(this.experimentIdsEncrypted) + "}";
    }
}
