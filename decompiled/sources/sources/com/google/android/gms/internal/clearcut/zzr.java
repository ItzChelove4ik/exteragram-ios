package com.google.android.gms.internal.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzr> CREATOR = null;
    private final String packageName;
    private final boolean zzay;
    private final int zzaz;
    private final int zzi;
    public final String zzj;
    public final int zzk;
    private final String zzl;
    private final String zzm;
    private final boolean zzn;

    static {
        CREATOR = new zzs();
    }

    public zzr(String r1, int r2, int r3, String r4, String r5, String r6, boolean r7, zzge$zzv$zzb r8) {
        this.packageName = (String) Preconditions.checkNotNull(r1);
        this.zzi = r2;
        this.zzk = r3;
        this.zzj = r4;
        this.zzl = r5;
        this.zzm = r6;
        this.zzay = !r7;
        this.zzn = r7;
        this.zzaz = r8.zzc();
    }

    public zzr(String r1, int r2, int r3, String r4, String r5, boolean r6, String r7, boolean r8, int r9) {
        this.packageName = r1;
        this.zzi = r2;
        this.zzk = r3;
        this.zzl = r4;
        this.zzm = r5;
        this.zzay = r6;
        this.zzj = r7;
        this.zzn = r8;
        this.zzaz = r9;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzr) == false) goto L26;
        zzr r52 = (zzr) r5;
        if (Objects.equal(this.packageName, r52.packageName) == false) goto L26;
        if (this.zzi != r52.zzi) goto L26;
        if (this.zzk != r52.zzk) goto L26;
        if (Objects.equal(this.zzj, r52.zzj) == false) goto L26;
        if (Objects.equal(this.zzl, r52.zzl) == false) goto L26;
        if (Objects.equal(this.zzm, r52.zzm) == false) goto L26;
        if (this.zzay != r52.zzay) goto L26;
        if (this.zzn != r52.zzn) goto L26;
        if (this.zzaz != r52.zzaz) goto L26;
        return true;
    L26:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.packageName, Integer.valueOf(this.zzi), Integer.valueOf(this.zzk), this.zzj, this.zzl, this.zzm, Boolean.valueOf(this.zzay), Boolean.valueOf(this.zzn), Integer.valueOf(this.zzaz)});
    }

    public final String toString() {
        return "PlayLoggerContext[package=" + this.packageName + ",packageVersionCode=" + this.zzi + ",logSource=" + this.zzk + ",logSourceName=" + this.zzj + ",uploadAccount=" + this.zzl + ",loggingId=" + this.zzm + ",logAndroidId=" + this.zzay + ",isAnonymous=" + this.zzn + ",qosTier=" + this.zzaz + "]";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, this.packageName, false);
        SafeParcelWriter.writeInt(r4, 3, this.zzi);
        SafeParcelWriter.writeInt(r4, 4, this.zzk);
        SafeParcelWriter.writeString(r4, 5, this.zzl, false);
        SafeParcelWriter.writeString(r4, 6, this.zzm, false);
        SafeParcelWriter.writeBoolean(r4, 7, this.zzay);
        SafeParcelWriter.writeString(r4, 8, this.zzj, false);
        SafeParcelWriter.writeBoolean(r4, 9, this.zzn);
        SafeParcelWriter.writeInt(r4, 10, this.zzaz);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
