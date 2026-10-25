package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.wearable.internal.zzah;
import com.google.android.gms.wearable.internal.zzau;
import com.google.android.gms.wearable.internal.zzce;
import com.google.android.gms.wearable.internal.zzct;
import com.google.android.gms.wearable.internal.zzfl;
import com.google.android.gms.wearable.internal.zzfw;
import com.google.android.gms.wearable.internal.zzgd;
import com.google.android.gms.wearable.internal.zzhk;
import com.google.android.gms.wearable.internal.zziw;

/* loaded from: classes5.dex */
public abstract class Wearable {

    @Deprecated
    public static final Api<WearableOptions> API = null;

    @Deprecated
    public static final CapabilityApi CapabilityApi = null;

    @Deprecated
    public static final ChannelApi ChannelApi = null;

    @Deprecated
    public static final DataApi DataApi = null;

    @Deprecated
    public static final MessageApi MessageApi = null;

    @Deprecated
    public static final NodeApi NodeApi = null;

    @Deprecated
    public static final com.google.android.gms.wearable.internal.zzk zza = null;

    @Deprecated
    public static final com.google.android.gms.wearable.internal.zzh zzb = null;

    @Deprecated
    public static final zzce zzc = null;

    @Deprecated
    public static final zzhk zzd = null;

    @Deprecated
    public static final zziw zze = null;
    private static final Api.ClientKey zzf = null;
    private static final Api.AbstractClientBuilder zzg = null;

    public static final class WearableOptions implements Api.ApiOptions {
        static final WearableOptions zza = null;
        private final Looper zzb;

        public static class Builder {
            private Looper zza;

            public Builder() {
            }

            public static /* bridge */ /* synthetic */ Looper zza(Builder r0) {
                return r0.zza;
            }
        }

        static {
            zza = new WearableOptions(new Builder());
        }

        private WearableOptions(Builder r1) {
            this.zzb = Builder.zza(r1);
        }

        public boolean equals(Object r1) {
            return r1 instanceof WearableOptions;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{WearableOptions.class});
        }
    }

    static {
        DataApi = new zzct();
        CapabilityApi = new zzah();
        MessageApi = new zzfl();
        NodeApi = new zzgd();
        ChannelApi = new zzau();
        zza = new com.google.android.gms.wearable.internal.zzk();
        zzb = new com.google.android.gms.wearable.internal.zzh();
        zzc = new zzce();
        zzd = new zzhk();
        zze = new zziw();
        Api.ClientKey r0 = new Api.ClientKey();
        zzf = r0;
        zzh r1 = new zzh();
        zzg = r1;
        API = new Api("Wearable.API", r1, r0);
    }

    public static MessageClient getMessageClient(Context r2) {
        return new zzfw(r2, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
