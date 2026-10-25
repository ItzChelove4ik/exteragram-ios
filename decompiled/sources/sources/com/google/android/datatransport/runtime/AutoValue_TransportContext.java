package com.google.android.datatransport.runtime;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
final class AutoValue_TransportContext extends TransportContext {
    private final String backendName;
    private final byte[] extras;
    private final Priority priority;

    /* renamed from: com.google.android.datatransport.runtime.AutoValue_TransportContext$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder extends TransportContext.Builder {
        private String backendName;
        private byte[] extras;
        private Priority priority;

        public Builder() {
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext build() {
            if (this.backendName != null) goto L5;
            String r0 = " backendName";
        L7:
            if (this.priority != null) goto L10;
            r0 = r0.concat(" priority");
        L10:
            if (r0.isEmpty() == true) goto L12;
            Segment$$ExternalSyntheticBUOutline1.m("Missing required properties:".concat(r0));
            return null;
        L12:
            return new AutoValue_TransportContext(this.backendName, this.extras, this.priority, null);
        L5:
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setBackendName(String r1) {
            if (r1 == null) goto L5;
            this.backendName = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null backendName");
            return null;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setExtras(byte[] r1) {
            this.extras = r1;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.TransportContext.Builder
        public TransportContext.Builder setPriority(Priority r1) {
            if (r1 == null) goto L5;
            this.priority = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline2.m("Null priority");
            return null;
        }
    }

    private AutoValue_TransportContext(String r1, byte[] r2, Priority r3) {
        this.backendName = r1;
        this.extras = r2;
        this.priority = r3;
    }

    public /* synthetic */ AutoValue_TransportContext(String r1, byte[] r2, Priority r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof TransportContext) == false) goto L18;
        TransportContext r52 = (TransportContext) r5;
        if (this.backendName.equals(r52.getBackendName()) == false) goto L18;
        byte[] r1 = this.extras;
        if ((r52 instanceof AutoValue_TransportContext) == false) goto L12;
        byte[] r3 = ((AutoValue_TransportContext) r52).extras;
    L14:
        if (Arrays.equals(r1, r3) == false) goto L18;
        if (this.priority.equals(r52.getPriority()) == false) goto L18;
        return true;
    L12:
        r3 = r52.getExtras();
    L18:
        return false;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public String getBackendName() {
        return this.backendName;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public byte[] getExtras() {
        return this.extras;
    }

    @Override // com.google.android.datatransport.runtime.TransportContext
    public Priority getPriority() {
        return this.priority;
    }

    public int hashCode() {
        int r0 = (((this.backendName.hashCode() ^ 1000003) * 1000003) ^ Arrays.hashCode(this.extras)) * 1000003;
        return this.priority.hashCode() ^ r0;
    }
}
