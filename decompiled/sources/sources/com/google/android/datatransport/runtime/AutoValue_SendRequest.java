package com.google.android.datatransport.runtime;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.runtime.SendRequest;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class AutoValue_SendRequest extends SendRequest {
    private final Encoding encoding;
    private final Event<?> event;
    private final Transformer<?, byte[]> transformer;
    private final TransportContext transportContext;
    private final String transportName;

    /* renamed from: com.google.android.datatransport.runtime.AutoValue_SendRequest$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends SendRequest.Builder {
        private Encoding encoding;
        private Event<?> event;
        private Transformer<?, byte[]> transformer;
        private TransportContext transportContext;
        private String transportName;

        public Builder() {
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest build() {
            if (this.transportContext != null) goto L5;
            String r0 = " transportContext";
        L7:
            if (this.transportName != null) goto L10;
            r0 = r0.concat(" transportName");
        L10:
            if (this.event != null) goto L13;
            r0 = r0.concat(" event");
        L13:
            if (this.transformer != null) goto L16;
            r0 = r0.concat(" transformer");
        L16:
            if (this.encoding != null) goto L19;
            r0 = r0.concat(" encoding");
        L19:
            if (r0.isEmpty() == true) goto L21;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L21:
            return new AutoValue_SendRequest(this.transportContext, this.transportName, this.event, this.transformer, this.encoding, null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setEncoding(Encoding r1) {
            if (r1 == null) goto L5;
            this.encoding = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null encoding");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setEvent(Event<?> r1) {
            if (r1 == null) goto L5;
            this.event = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null event");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransformer(Transformer<?, byte[]> r1) {
            if (r1 == null) goto L5;
            this.transformer = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null transformer");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportContext(TransportContext r1) {
            if (r1 == null) goto L5;
            this.transportContext = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null transportContext");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.SendRequest.Builder
        public SendRequest.Builder setTransportName(String r1) {
            if (r1 == null) goto L5;
            this.transportName = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null transportName");
            return null;
        }
    }

    private AutoValue_SendRequest(TransportContext r1, String r2, Event<?> r3, Transformer<?, byte[]> r4, Encoding r5) {
        this.transportContext = r1;
        this.transportName = r2;
        this.event = r3;
        this.transformer = r4;
        this.encoding = r5;
    }

    public /* synthetic */ AutoValue_SendRequest(TransportContext r1, String r2, Event r3, Transformer r4, Encoding r5, AnonymousClass1 r6) {
        this(r1, r2, r3, r4, r5);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof SendRequest) == false) goto L18;
        SendRequest r52 = (SendRequest) r5;
        if (this.transportContext.equals(r52.getTransportContext()) == false) goto L18;
        if (this.transportName.equals(r52.getTransportName()) == false) goto L18;
        if (this.event.equals(r52.getEvent()) == false) goto L18;
        if (this.transformer.equals(r52.getTransformer()) == false) goto L18;
        if (this.encoding.equals(r52.getEncoding()) == false) goto L18;
        return true;
    L18:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Encoding getEncoding() {
        return this.encoding;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Event<?> getEvent() {
        return this.event;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public Transformer<?, byte[]> getTransformer() {
        return this.transformer;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public TransportContext getTransportContext() {
        return this.transportContext;
    }

    @Override // com.google.android.datatransport.runtime.SendRequest
    public String getTransportName() {
        return this.transportName;
    }

    public int hashCode() {
        int r0 = (((((((this.transportContext.hashCode() ^ 1000003) * 1000003) ^ this.transportName.hashCode()) * 1000003) ^ this.event.hashCode()) * 1000003) ^ this.transformer.hashCode()) * 1000003;
        return this.encoding.hashCode() ^ r0;
    }

    public String toString() {
        return "SendRequest{transportContext=" + this.transportContext + ", transportName=" + this.transportName + ", event=" + this.event + ", transformer=" + this.transformer + ", encoding=" + this.encoding + "}";
    }
}
