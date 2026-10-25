package com.google.android.gms.cast;

import android.content.Context;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzet;

@Deprecated
/* loaded from: classes4.dex */
public final class CastRemoteDisplay {

    @Deprecated
    public static final Api<CastRemoteDisplayOptions> API = null;
    public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
    public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
    public static final int CONFIGURATION_NONINTERACTIVE = 3;

    @Deprecated
    public static final CastRemoteDisplayApi CastRemoteDisplayApi = null;
    public static final String EXTRA_INT_SESSION_ENDED_STATUS_CODE = "extra_int_session_ended_status_code";
    private static final Api.AbstractClientBuilder zza = null;

    @Deprecated
    public static final class CastRemoteDisplayOptions implements Api.ApiOptions {
        final CastDevice zza;
        final CastRemoteDisplaySessionCallbacks zzb;
        final int zzc;

        @Deprecated
        public static final class Builder {
            final CastDevice zza;
            final CastRemoteDisplaySessionCallbacks zzb;
            int zzc;

            public Builder(CastDevice r2, CastRemoteDisplaySessionCallbacks r3) {
                Preconditions.checkNotNull(r2, "CastDevice parameter cannot be null");
                this.zza = r2;
                this.zzb = r3;
                this.zzc = 2;
            }

            public CastRemoteDisplayOptions build() {
                return new CastRemoteDisplayOptions(this, null);
            }

            public Builder setConfigPreset(@Configuration int r1) {
                this.zzc = r1;
                return this;
            }
        }

        public /* synthetic */ CastRemoteDisplayOptions(Builder r1, byte[] r2) {
            this.zza = r1.zza;
            this.zzb = r1.zzb;
            this.zzc = r1.zzc;
        }
    }

    @Deprecated
    public interface CastRemoteDisplaySessionCallbacks {
        void onRemoteDisplayEnded(Status r1);
    }

    @Deprecated
    public interface CastRemoteDisplaySessionResult extends Result {
        Display getPresentationDisplay();

        @Override // com.google.android.gms.common.api.Result
        /* synthetic */ Status getStatus();
    }

    public @interface Configuration {
    }

    static {
        zzu r0 = new zzu();
        zza = r0;
        Api<CastRemoteDisplayOptions> r1 = new Api("CastRemoteDisplay.API", r0, com.google.android.gms.cast.internal.zzal.zzc);
        API = r1;
        CastRemoteDisplayApi = new zzet(r1);
    }

    private CastRemoteDisplay() {
    }

    public static CastRemoteDisplayClient getClient(Context r1) {
        return new CastRemoteDisplayClient(r1);
    }

    @Deprecated
    public static final boolean isRemoteDisplaySdkSupported(Context r0) {
        return false;
    }
}
