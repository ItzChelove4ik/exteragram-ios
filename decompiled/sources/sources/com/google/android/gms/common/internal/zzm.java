package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zzm implements Parcelable.Creator {
    public zzm() {
    }

    public static void zza(GetServiceRequest r4, Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r4.zzc);
        SafeParcelWriter.writeInt(r5, 2, r4.zzd);
        SafeParcelWriter.writeInt(r5, 3, r4.zze);
        SafeParcelWriter.writeString(r5, 4, r4.zzf, false);
        SafeParcelWriter.writeIBinder(r5, 5, r4.zzg, false);
        SafeParcelWriter.writeTypedArray(r5, 6, r4.zzh, r6, false);
        SafeParcelWriter.writeBundle(r5, 7, r4.zzi, false);
        SafeParcelWriter.writeParcelable(r5, 8, r4.zzj, r6, false);
        SafeParcelWriter.writeTypedArray(r5, 10, r4.zzk, r6, false);
        SafeParcelWriter.writeTypedArray(r5, 11, r4.zzl, r6, false);
        SafeParcelWriter.writeBoolean(r5, 12, r4.zzm);
        SafeParcelWriter.writeInt(r5, 13, r4.zzn);
        SafeParcelWriter.writeBoolean(r5, 14, r4.zzo);
        SafeParcelWriter.writeString(r5, 15, r4.getAttributionTag(), false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r23) {
        int r1 = SafeParcelReader.validateObjectHeader(r23);
        Scope[] r2 = GetServiceRequest.zza;
        Scope[] r13 = r2;
        Bundle r14 = new Bundle();
        Feature[] r16 = GetServiceRequest.zzb;
        Feature[] r17 = r16;
        String r11 = null;
        IBinder r12 = null;
        Account r15 = null;
        String r21 = null;
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
        boolean r18 = false;
        int r19 = 0;
        boolean r20 = false;
    L4:
        if (r23.dataPosition() >= r1) goto L22;
        int r22 = SafeParcelReader.readHeader(r23);
        switch(SafeParcelReader.getFieldId(r22)) {
            case 1: goto L21;
            case 2: goto L20;
            case 3: goto L19;
            case 4: goto L18;
            case 5: goto L17;
            case 6: goto L16;
            case 7: goto L15;
            case 8: goto L14;
            case 9: goto L7;
            case 10: goto L13;
            case 11: goto L12;
            case 12: goto L11;
            case 13: goto L10;
            case 14: goto L9;
            case 15: goto L8;
            default: goto L7;
        };
    L8:
        r21 = SafeParcelReader.createString(r23, r22);
        goto L4
    L9:
        r20 = SafeParcelReader.readBoolean(r23, r22);
        goto L4
    L10:
        r19 = SafeParcelReader.readInt(r23, r22);
        goto L4
    L11:
        r18 = SafeParcelReader.readBoolean(r23, r22);
        goto L4
    L12:
        r17 = (Feature[]) SafeParcelReader.createTypedArray(r23, r22, Feature.CREATOR);
        goto L4
    L13:
        r16 = (Feature[]) SafeParcelReader.createTypedArray(r23, r22, Feature.CREATOR);
        goto L4
    L14:
        r15 = (Account) SafeParcelReader.createParcelable(r23, r22, Account.CREATOR);
        goto L4
    L15:
        r14 = SafeParcelReader.createBundle(r23, r22);
        goto L4
    L16:
        r13 = (Scope[]) SafeParcelReader.createTypedArray(r23, r22, Scope.CREATOR);
        goto L4
    L17:
        r12 = SafeParcelReader.readIBinder(r23, r22);
        goto L4
    L18:
        r11 = SafeParcelReader.createString(r23, r22);
        goto L4
    L19:
        r10 = SafeParcelReader.readInt(r23, r22);
        goto L4
    L20:
        r9 = SafeParcelReader.readInt(r23, r22);
        goto L4
    L21:
        r8 = SafeParcelReader.readInt(r23, r22);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r23, r22);
        goto L4
    L22:
        SafeParcelReader.ensureAtEnd(r23, r1);
        return new GetServiceRequest(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new GetServiceRequest[r1];
    }
}
