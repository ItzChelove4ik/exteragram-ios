package com.google.android.datatransport.runtime.backends;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class AutoValue_BackendRequest extends BackendRequest {
    private final Iterable<EventInternal> events;
    private final byte[] extras;

    /* renamed from: com.google.android.datatransport.runtime.backends.AutoValue_BackendRequest$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends BackendRequest.Builder {
        private Iterable<EventInternal> events;
        private byte[] extras;

        public Builder() {
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest build() {
            if (this.events != null) goto L5;
            String r0 = " events";
        L7:
            if (r0.isEmpty() == true) goto L9;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L9:
            return new AutoValue_BackendRequest(this.events, this.extras, null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest.Builder setEvents(Iterable<EventInternal> r1) {
            if (r1 == null) goto L5;
            this.events = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null events");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.backends.BackendRequest.Builder
        public BackendRequest.Builder setExtras(byte[] r1) {
            this.extras = r1;
            return this;
        }
    }

    private AutoValue_BackendRequest(Iterable<EventInternal> r1, byte[] r2) {
        this.events = r1;
        this.extras = r2;
    }

    public /* synthetic */ AutoValue_BackendRequest(Iterable r1, byte[] r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof BackendRequest) == false) goto L16;
        BackendRequest r52 = (BackendRequest) r5;
        if (this.events.equals(r52.getEvents()) == false) goto L16;
        byte[] r4 = this.extras;
        if ((r52 instanceof AutoValue_BackendRequest) == false) goto L12;
        byte[] r53 = ((AutoValue_BackendRequest) r52).extras;
    L14:
        if (Arrays.equals(r4, r53) == false) goto L16;
        return true;
    L12:
        r53 = r52.getExtras();
    L16:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRequest
    public Iterable<EventInternal> getEvents() {
        return this.events;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRequest
    public byte[] getExtras() {
        return this.extras;
    }

    public int hashCode() {
        int r0 = (this.events.hashCode() ^ 1000003) * 1000003;
        return Arrays.hashCode(this.extras) ^ r0;
    }

    public String toString() {
        return "BackendRequest{events=" + this.events + ", extras=" + Arrays.toString(this.extras) + "}";
    }
}
