package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.UUID;

/* loaded from: classes4.dex */
public final class Cast {
    public static final int ACTIVE_INPUT_STATE_NO = 0;
    public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
    public static final int ACTIVE_INPUT_STATE_YES = 1;
    public static final Api<CastOptions> API = null;
    public static final CastApi CastApi = null;
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    public static final int STANDBY_STATE_NO = 0;
    public static final int STANDBY_STATE_UNKNOWN = -1;
    public static final int STANDBY_STATE_YES = 1;
    static final Api.AbstractClientBuilder zza = null;

    public interface ApplicationConnectionResult extends Result {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        @Override // com.google.android.gms.common.api.Result
        /* synthetic */ Status getStatus();

        boolean getWasLaunched();
    }

    @Deprecated
    public interface CastApi {
        int getActiveInputState(GoogleApiClient r1);

        ApplicationMetadata getApplicationMetadata(GoogleApiClient r1);

        String getApplicationStatus(GoogleApiClient r1);

        int getStandbyState(GoogleApiClient r1);

        double getVolume(GoogleApiClient r1);

        boolean isMute(GoogleApiClient r1);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient r1);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient r1, String r2);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient r1, String r2, String r3);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient r1, String r2);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient r1, String r2, LaunchOptions r3);

        @Deprecated
        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient r1, String r2, boolean r3);

        PendingResult<Status> leaveApplication(GoogleApiClient r1);

        void removeMessageReceivedCallbacks(GoogleApiClient r1, String r2);

        void requestStatus(GoogleApiClient r1);

        PendingResult<Status> sendMessage(GoogleApiClient r1, String r2, String r3);

        void setMessageReceivedCallbacks(GoogleApiClient r1, String r2, MessageReceivedCallback r3);

        void setMute(GoogleApiClient r1, boolean r2);

        void setVolume(GoogleApiClient r1, double r2);

        PendingResult<Status> stopApplication(GoogleApiClient r1);

        PendingResult<Status> stopApplication(GoogleApiClient r1, String r2);
    }

    public static final class CastOptions implements Api.ApiOptions {
        final CastDevice zza;
        final Listener zzb;
        final Bundle zzc;
        final int zzd;
        final String zze;

        public static final class Builder {
            final CastDevice zza;
            final Listener zzb;
            private int zzc;
            private Bundle zzd;

            public Builder(CastDevice r2, Listener r3) {
                Preconditions.checkNotNull(r2, "CastDevice parameter cannot be null");
                Preconditions.checkNotNull(r3, "CastListener parameter cannot be null");
                this.zza = r2;
                this.zzb = r3;
                this.zzc = 0;
            }

            public CastOptions build() {
                return new CastOptions(this, null);
            }

            public Builder setVerboseLoggingEnabled(boolean r1) {
                this.zzc = r1 ? 1 : 0;
                return this;
            }

            public final Builder zza(Bundle r1) {
                this.zzd = r1;
                return this;
            }

            public final /* synthetic */ int zzb() {
                return this.zzc;
            }

            public final /* synthetic */ Bundle zzc() {
                return this.zzd;
            }
        }

        public /* synthetic */ CastOptions(Builder r1, byte[] r2) {
            this.zza = r1.zza;
            this.zzb = r1.zzb;
            this.zzd = r1.zzb();
            this.zzc = r1.zzc();
            this.zze = UUID.randomUUID().toString();
        }

        @Deprecated
        public static Builder builder(CastDevice r1, Listener r2) {
            return new Builder(r1, r2);
        }

        public boolean equals(Object r5) {
            if (r5 != this) goto L6;
            return true;
        L6:
            if ((r5 instanceof CastOptions) == true) goto L8;
            return false;
        L8:
            CastOptions r52 = (CastOptions) r5;
            if (Objects.equal(this.zza, r52.zza) == true) goto L11;
        L17:
            return false;
        L11:
            if (Objects.checkBundlesEquality(this.zzc, r52.zzc) == false) goto L17;
            if (this.zzd != r52.zzd) goto L17;
            if (Objects.equal(this.zze, r52.zze) == false) goto L17;
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.zza, this.zzc, Integer.valueOf(this.zzd), this.zze});
        }
    }

    public static class Listener {
        public Listener() {
        }

        public void onActiveInputStateChanged(int r1) {
        }

        public void onApplicationDisconnected(int r1) {
        }

        public void onApplicationMetadataChanged(ApplicationMetadata r1) {
        }

        public void onApplicationStatusChanged() {
        }

        public void onDeviceNameChanged() {
        }

        public void onStandbyStateChanged(int r1) {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice r1, String r2, String r3);
    }

    static {
        zze r0 = new zze();
        zza = r0;
        API = new Api("Cast.API", r0, com.google.android.gms.cast.internal.zzal.zza);
        CastApi = new zzm();
    }

    private Cast() {
    }

    public static zzq zza(Context r1, CastOptions r2) {
        return new zzbm(r1, r2);
    }
}
