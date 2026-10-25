package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;

/* loaded from: classes4.dex */
public class LaunchOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LaunchOptions> CREATOR = null;
    private boolean zza;
    private String zzb;
    private boolean zzc;
    private CredentialsData zzd;

    public static final class Builder {
        private final LaunchOptions zza;

        public Builder() {
            this.zza = new LaunchOptions();
        }

        public Builder(LaunchOptions r5) {
            this.zza = new LaunchOptions(r5.getRelaunchIfRunning(), r5.getLanguage(), r5.getAndroidReceiverCompatible(), r5.getCredentialsData());
        }

        public LaunchOptions build() {
            return this.zza;
        }

        public Builder setAndroidReceiverCompatible(boolean r2) {
            this.zza.zza(r2);
            return this;
        }

        public Builder setCredentialsData(CredentialsData r2) {
            this.zza.zzb(r2);
            return this;
        }

        public Builder setLocale(Locale r2) {
            this.zza.setLanguage(CastUtils.zzd(r2));
            return this;
        }

        public Builder setRelaunchIfRunning(boolean r2) {
            this.zza.setRelaunchIfRunning(r2);
            return this;
        }
    }

    static {
        CREATOR = new zzbp();
    }

    public LaunchOptions() {
        this(false, CastUtils.zzd(Locale.getDefault()), false, null);
    }

    public LaunchOptions(boolean r1, String r2, boolean r3, CredentialsData r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof LaunchOptions) == true) goto L8;
        return false;
    L8:
        LaunchOptions r52 = (LaunchOptions) r5;
        if (this.zza == r52.zza) goto L11;
    L17:
        return false;
    L11:
        if (CastUtils.zza(this.zzb, r52.zzb) == false) goto L17;
        if (this.zzc != r52.zzc) goto L17;
        if (CastUtils.zza(this.zzd, r52.zzd) == false) goto L17;
        return true;
    }

    public boolean getAndroidReceiverCompatible() {
        return this.zzc;
    }

    public CredentialsData getCredentialsData() {
        return this.zzd;
    }

    public String getLanguage() {
        return this.zzb;
    }

    public boolean getRelaunchIfRunning() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza), this.zzb, Boolean.valueOf(this.zzc), this.zzd});
    }

    public void setLanguage(String r1) {
        this.zzb = r1;
    }

    public void setRelaunchIfRunning(boolean r1) {
        this.zza = r1;
    }

    public String toString() {
        return String.format("LaunchOptions(relaunchIfRunning=%b, language=%s, androidReceiverCompatible: %b)", new Object[]{Boolean.valueOf(this.zza), this.zzb, Boolean.valueOf(this.zzc)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeBoolean(r5, 2, getRelaunchIfRunning());
        SafeParcelWriter.writeString(r5, 3, getLanguage(), false);
        SafeParcelWriter.writeBoolean(r5, 4, getAndroidReceiverCompatible());
        SafeParcelWriter.writeParcelable(r5, 5, getCredentialsData(), r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final void zza(boolean r1) {
        this.zzc = r1;
    }

    public final /* synthetic */ void zzb(CredentialsData r1) {
        this.zzd = r1;
    }
}
