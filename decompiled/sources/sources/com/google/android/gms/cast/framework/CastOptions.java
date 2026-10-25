package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzhc;
import com.google.android.gms.internal.cast.zzhd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class CastOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CastOptions> CREATOR = null;
    static final zzk zza = null;
    static final zzm zzb = null;
    static final CastMediaOptions zzc = null;
    private String zzd;
    private final List zze;
    private final boolean zzf;
    private LaunchOptions zzg;
    private final boolean zzh;
    private final CastMediaOptions zzi;
    private final boolean zzj;
    private final double zzk;
    private final boolean zzl;
    private boolean zzm;
    private boolean zzn;
    private final List zzo;
    private final boolean zzp;
    private final boolean zzq;
    private final zzk zzr;
    private zzm zzs;
    private final boolean zzt;
    private final boolean zzu;

    public static final class Builder {
        private String zza;
        private List zzb;
        private boolean zzc;
        private LaunchOptions zzd;
        private boolean zze;
        private zzhc zzf;
        private boolean zzg;
        private double zzh;
        private boolean zzi;
        private boolean zzj;
        private final List zzk;
        private boolean zzl;
        private boolean zzm;

        public Builder() {
            this.zzb = new ArrayList();
            this.zzd = new LaunchOptions();
            this.zze = true;
            this.zzf = zzhc.zzb();
            this.zzg = true;
            this.zzh = 0.05000000074505806d;
            this.zzi = false;
            this.zzj = false;
            this.zzk = new ArrayList();
            this.zzl = true;
            this.zzm = false;
        }

        public CastOptions build() {
            CastMediaOptions r8 = (CastMediaOptions) this.zzf.zza(CastOptions.zzc);
            zzk r1 = CastOptions.zza;
            zzhd.zza(r1, "use Optional.orNull() instead of Optional.or(null)");
            zzm r3 = CastOptions.zzb;
            zzhd.zza(r3, "use Optional.orNull() instead of Optional.or(null)");
            return new CastOptions(this.zza, this.zzb, this.zzc, this.zzd, this.zze, r8, this.zzg, this.zzh, false, this.zzi, this.zzj, this.zzk, this.zzl, 0, false, r1, r3, false, this.zzm);
        }

        public Builder setCastMediaOptions(CastMediaOptions r1) {
            this.zzf = zzhc.zzc(r1);
            return this;
        }

        public Builder setEnableReconnectionService(boolean r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setLaunchOptions(LaunchOptions r1) {
            this.zzd = r1;
            return this;
        }

        public Builder setMediaTransferRestrictedToSelfProviders(boolean r1) {
            this.zzm = r1;
            return this;
        }

        public Builder setReceiverApplicationId(String r1) {
            this.zza = r1;
            return this;
        }

        public Builder setRemoteToLocalEnabled(boolean r1) {
            this.zzj = r1;
            return this;
        }

        public Builder setResumeSavedSession(boolean r1) {
            this.zze = r1;
            return this;
        }

        public Builder setSessionTransferEnabled(boolean r1) {
            this.zzl = r1;
            return this;
        }

        public Builder setShowSystemOutputSwitcherOnCastIconClick(boolean r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setStopReceiverApplicationWhenEndingSession(boolean r1) {
            this.zzc = r1;
            return this;
        }

        public Builder setSupportedNamespaces(List<String> r1) {
            this.zzb = r1;
            return this;
        }

        @Deprecated
        public Builder setVolumeDeltaBeforeIceCreamSandwich(double r3) {
            if (r3 > 0.0d) goto L5;
        L8:
            f$$ExternalSyntheticBUOutline1.m("volumeDelta must be greater than 0 and less or equal to 0.5");
            return null;
        L5:
            if (r3 > 0.5d) goto L8;
            this.zzh = r3;
            return this;
        }
    }

    public static final class Modifier {
        public static final Modifier EMPTY = null;
        final String zza;
        final Boolean zzb;
        final Boolean zzc;

        public static final class Builder {
            private String zza;
            private Boolean zzb;
            private Boolean zzc;

            public Builder() {
            }

            public Modifier build() {
                return new Modifier(this, null);
            }

            public Builder setReceiverApplicationId(String r1) {
                this.zza = r1;
                return this;
            }

            public Builder setRemoteToLocalEnabled(Boolean r1) {
                this.zzb = r1;
                return this;
            }

            public Builder setShowSystemOutputSwitcherOnCastIconClick(Boolean r1) {
                this.zzc = r1;
                return this;
            }

            public final /* synthetic */ String zza() {
                return this.zza;
            }

            public final /* synthetic */ Boolean zzb() {
                return this.zzb;
            }

            public final /* synthetic */ Boolean zzc() {
                return this.zzc;
            }
        }

        static {
            EMPTY = new Builder().build();
        }

        public /* synthetic */ Modifier(Builder r1, byte[] r2) {
            this.zza = r1.zza();
            this.zzb = r1.zzb();
            this.zzc = r1.zzc();
        }

        public String getReceiverApplicationId() {
            return this.zza;
        }

        public Boolean getRemoteToLocalEnabled() {
            return this.zzb;
        }

        public Boolean getShowSystemOutputSwitcherOnCastIconClick() {
            return this.zzc;
        }
    }

    static {
        zza = new zzk(false);
        zzb = new zzm(0);
        CastMediaOptions.Builder r0 = new CastMediaOptions.Builder();
        r0.setMediaSessionEnabled(false);
        r0.setNotificationOptions(null);
        zzc = r0.build();
        CREATOR = new zzo();
    }

    public CastOptions(String r3, List r4, boolean r5, LaunchOptions r6, boolean r7, CastMediaOptions r8, boolean r9, double r10, boolean r12, boolean r13, boolean r14, List r15, boolean r16, int r17, boolean r18, zzk r19, zzm r20, boolean r21, boolean r22) {
        if (true != TextUtils.isEmpty(r3)) goto L5;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        this.zzd = r3;
        if (r4 != null) goto L8;
        int r32 = 0;
    L9:
        ArrayList r0 = new ArrayList(r32);
        this.zze = r0;
        if (r32 <= 0) goto L12;
        r0.addAll(r4);
    L12:
        this.zzf = r5;
        if (r6 != null) goto L15;
        r6 = new LaunchOptions();
    L15:
        this.zzg = r6;
        this.zzh = r7;
        this.zzi = r8;
        this.zzj = r9;
        this.zzk = r10;
        this.zzl = r12;
        this.zzm = r13;
        this.zzn = r14;
        this.zzo = r15;
        this.zzp = r16;
        this.zzq = r18;
        this.zzr = r19;
        this.zzs = r20;
        this.zzt = r21;
        this.zzu = r22;
        return;
    L8:
        r32 = r4.size();
        goto L9
    }

    public CastMediaOptions getCastMediaOptions() {
        return this.zzi;
    }

    public boolean getEnableReconnectionService() {
        return this.zzj;
    }

    public LaunchOptions getLaunchOptions() {
        return this.zzg;
    }

    public String getReceiverApplicationId() {
        return this.zzd;
    }

    public boolean getResumeSavedSession() {
        return this.zzh;
    }

    public boolean getShowSystemOutputSwitcherOnCastIconClick() {
        return this.zzm;
    }

    public boolean getStopReceiverApplicationWhenEndingSession() {
        return this.zzf;
    }

    public List<String> getSupportedNamespaces() {
        return Collections.unmodifiableList(this.zze);
    }

    @Deprecated
    public double getVolumeDeltaBeforeIceCreamSandwich() {
        return this.zzk;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r7, int r8) {
        int r0 = SafeParcelWriter.beginObjectHeader(r7);
        SafeParcelWriter.writeString(r7, 2, getReceiverApplicationId(), false);
        SafeParcelWriter.writeStringList(r7, 3, getSupportedNamespaces(), false);
        SafeParcelWriter.writeBoolean(r7, 4, getStopReceiverApplicationWhenEndingSession());
        SafeParcelWriter.writeParcelable(r7, 5, getLaunchOptions(), r8, false);
        SafeParcelWriter.writeBoolean(r7, 6, getResumeSavedSession());
        SafeParcelWriter.writeParcelable(r7, 7, getCastMediaOptions(), r8, false);
        SafeParcelWriter.writeBoolean(r7, 8, getEnableReconnectionService());
        SafeParcelWriter.writeDouble(r7, 9, getVolumeDeltaBeforeIceCreamSandwich());
        SafeParcelWriter.writeBoolean(r7, 10, this.zzl);
        SafeParcelWriter.writeBoolean(r7, 11, getShowSystemOutputSwitcherOnCastIconClick());
        SafeParcelWriter.writeBoolean(r7, 12, this.zzn);
        SafeParcelWriter.writeStringList(r7, 13, Collections.unmodifiableList(this.zzo), false);
        SafeParcelWriter.writeBoolean(r7, 14, this.zzp);
        SafeParcelWriter.writeInt(r7, 15, 0);
        SafeParcelWriter.writeBoolean(r7, 16, this.zzq);
        SafeParcelWriter.writeParcelable(r7, 17, this.zzr, r8, false);
        SafeParcelWriter.writeParcelable(r7, 18, this.zzs, r8, false);
        SafeParcelWriter.writeBoolean(r7, 19, this.zzt);
        SafeParcelWriter.writeBoolean(r7, 20, this.zzu);
        SafeParcelWriter.finishObjectHeader(r7, r0);
    }

    public final void zza(Modifier r2) {
        String r0 = r2.getReceiverApplicationId();
        if (r0 == null) goto L5;
        this.zzd = r0;
    L5:
        Boolean r02 = r2.getRemoteToLocalEnabled();
        if (r02 == null) goto L8;
        this.zzn = r02.booleanValue();
    L8:
        Boolean r22 = r2.getShowSystemOutputSwitcherOnCastIconClick();
        if (r22 == null) goto L12;
        this.zzm = r22.booleanValue();
        return;
    }

    public final void zzb(String r1) {
        this.zzd = r1;
    }

    public final void zzc(boolean r1) {
        this.zzn = r1;
    }

    public final void zzd(boolean r1) {
        this.zzm = r1;
    }

    public final void zze(LaunchOptions r1) {
        this.zzg = r1;
    }

    public final boolean zzf() {
        return this.zzn;
    }

    public final List zzg() {
        return Collections.unmodifiableList(this.zzo);
    }

    public final boolean zzh() {
        return this.zzp;
    }

    public final void zzi(zzm r1) {
        this.zzs = r1;
    }

    public final boolean zzj() {
        return this.zzt;
    }

    public final boolean zzk() {
        return this.zzu;
    }
}
