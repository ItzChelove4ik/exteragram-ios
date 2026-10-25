package com.google.android.datatransport.runtime;

import android.util.Base64;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.AutoValue_TransportContext;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public abstract class TransportContext {

    public static abstract class Builder {
        public Builder() {
        }

        public abstract TransportContext build();

        public abstract Builder setBackendName(String r1);

        public abstract Builder setExtras(byte[] r1);

        public abstract Builder setPriority(Priority r1);
    }

    public TransportContext() {
    }

    public static Builder builder() {
        return new AutoValue_TransportContext.Builder().setPriority(Priority.DEFAULT);
    }

    public abstract String getBackendName();

    public abstract byte[] getExtras();

    public abstract Priority getPriority();

    public boolean shouldUploadClientHealthMetrics() {
        if (getExtras() == null) goto L6;
        return true;
    L6:
        return false;
    }

    public final String toString() {
        String r0 = getBackendName();
        Priority r1 = getPriority();
        if (getExtras() != null) goto L5;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return String.format("TransportContext(%s, %s, %s)", new Object[]{r0, r1, r3});
    L5:
        r3 = Base64.encodeToString(getExtras(), 2);
        goto L7
    }

    public TransportContext withPriority(Priority r3) {
        return builder().setBackendName(getBackendName()).setPriority(r3).setExtras(getExtras()).build();
    }
}
