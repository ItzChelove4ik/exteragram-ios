package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Api;

/* loaded from: classes4.dex */
public class TelemetryLoggingOptions implements Api.ApiOptions {
    public static final TelemetryLoggingOptions zaa = null;
    private final String zab;

    public static class Builder {
        private String zaa;

        public /* synthetic */ Builder(byte[] r1) {
        }

        public TelemetryLoggingOptions build() {
            return new TelemetryLoggingOptions(this.zaa, null);
        }

        public Builder setApi(String r1) {
            this.zaa = r1;
            return this;
        }
    }

    static {
        zaa = builder().build();
    }

    public /* synthetic */ TelemetryLoggingOptions(String r1, byte[] r2) {
        this.zab = r1;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    public final boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof TelemetryLoggingOptions) == true) goto L10;
        return false;
    L10:
        return Objects.equal(this.zab, ((TelemetryLoggingOptions) r2).zab);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zab});
    }

    public final Bundle zaa() {
        Bundle r0 = new Bundle();
        String r2 = this.zab;
        if (r2 == null) goto L5;
        r0.putString("api", r2);
    L5:
        return r0;
    }
}
