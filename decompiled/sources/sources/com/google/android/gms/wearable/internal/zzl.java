package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.zzb;

/* loaded from: classes5.dex */
public final class zzl extends AbstractSafeParcelable implements zzb {
    public static final Parcelable.Creator<zzl> CREATOR = null;
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final byte zzh;
    private final byte zzi;
    private final byte zzj;
    private final byte zzk;
    private final String zzl;

    static {
        CREATOR = new zzm();
    }

    public zzl(int r1, String r2, String r3, String r4, String r5, String r6, String r7, byte r8, byte r9, byte r10, byte r11, String r12) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
        this.zzg = r7;
        this.zzh = r8;
        this.zzi = r9;
        this.zzj = r10;
        this.zzk = r11;
        this.zzl = r12;
    }

    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L58:
        return false;
    L8:
        if (zzl.class != r5.getClass()) goto L58;
        zzl r52 = (zzl) r5;
        if (this.zza == r52.zza) goto L14;
        return false;
    L14:
        if (this.zzh == r52.zzh) goto L17;
        return false;
    L17:
        if (this.zzi == r52.zzi) goto L20;
        return false;
    L20:
        if (this.zzj == r52.zzj) goto L23;
        return false;
    L23:
        if (this.zzk == r52.zzk) goto L26;
        return false;
    L26:
        if (this.zzb.equals(r52.zzb) == true) goto L28;
        return false;
    L28:
        String r2 = this.zzc;
        String r3 = r52.zzc;
        if (r2 != null) goto L31;
        if (r3 == null) goto L36;
    L34:
        return false;
    L36:
        if (this.zzd.equals(r52.zzd) == true) goto L39;
        return false;
    L39:
        if (this.zze.equals(r52.zze) == true) goto L42;
        return false;
    L42:
        if (this.zzf.equals(r52.zzf) == true) goto L44;
        return false;
    L44:
        String r22 = this.zzg;
        String r32 = r52.zzg;
        if (r22 != null) goto L47;
        if (r32 == null) goto L51;
    L50:
        return false;
    L51:
        String r4 = this.zzl;
        String r53 = r52.zzl;
        if (r4 != null) goto L54;
        if (r53 == null) goto L57;
        return false;
    L57:
        return true;
    L54:
        return r4.equals(r53);
    L47:
        if (r22.equals(r32) == false) goto L50;
    L31:
        if (r2.equals(r3) == false) goto L34;
        goto L34
    }

    public final int hashCode() {
        int r0 = (((this.zza + 31) * 31) + this.zzb.hashCode()) * 31;
        String r1 = this.zzc;
        int r2 = 0;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L6:
        int r02 = (((((((r0 + r12) * 31) + this.zzd.hashCode()) * 31) + this.zze.hashCode()) * 31) + this.zzf.hashCode()) * 31;
        String r13 = this.zzg;
        if (r13 == null) goto L9;
        int r14 = r13.hashCode();
    L10:
        int r03 = (((((((((r02 + r14) * 31) + this.zzh) * 31) + this.zzi) * 31) + this.zzj) * 31) + this.zzk) * 31;
        String r3 = this.zzl;
        if (r3 == null) goto L14;
        r2 = r3.hashCode();
    L14:
        return r03 + r2;
    L9:
        r14 = 0;
        goto L10
    L5:
        r12 = 0;
        goto L6
    }

    public final String toString() {
        return "AncsNotificationParcelable{, id=" + this.zza + ", appId='" + this.zzb + "', dateTime='" + this.zzc + "', eventId=" + this.zzh + ", eventFlags=" + this.zzi + ", categoryId=" + this.zzj + ", categoryCount=" + this.zzk + ", packageName='" + this.zzl + "'}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeInt(r4, 2, this.zza);
        SafeParcelWriter.writeString(r4, 3, this.zzb, false);
        SafeParcelWriter.writeString(r4, 4, this.zzc, false);
        SafeParcelWriter.writeString(r4, 5, this.zzd, false);
        SafeParcelWriter.writeString(r4, 6, this.zze, false);
        SafeParcelWriter.writeString(r4, 7, this.zzf, false);
        String r0 = this.zzg;
        if (r0 != null) goto L5;
        r0 = this.zzb;
    L5:
        SafeParcelWriter.writeString(r4, 8, r0, false);
        SafeParcelWriter.writeByte(r4, 9, this.zzh);
        SafeParcelWriter.writeByte(r4, 10, this.zzi);
        SafeParcelWriter.writeByte(r4, 11, this.zzj);
        SafeParcelWriter.writeByte(r4, 12, this.zzk);
        SafeParcelWriter.writeString(r4, 13, this.zzl, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
