package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzcq implements Parcelable.Creator {
    public zzcq() {
    }

    public static void zza(zzcp r4, Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r4.zza());
        SafeParcelWriter.writeString(r5, 2, r4.zzb(), false);
        SafeParcelWriter.writeString(r5, 3, r4.zzc(), false);
        SafeParcelWriter.writeBooleanObject(r5, 4, r4.zzd(), false);
        SafeParcelWriter.writeStringList(r5, 5, r4.zze(), false);
        SafeParcelWriter.writeParcelable(r5, 6, r4.zzf(), r6, false);
        SafeParcelWriter.writeLongObject(r5, 7, r4.zzg(), false);
        SafeParcelWriter.writeLongObject(r5, 8, r4.zzh(), false);
        SafeParcelWriter.writeStringList(r5, 9, r4.zzi(), false);
        SafeParcelWriter.writeLongObject(r5, 10, r4.zzj(), false);
        SafeParcelWriter.writeLongObject(r5, 11, r4.zzk(), false);
        SafeParcelWriter.writeDoubleObject(r5, 12, r4.zzl(), false);
        SafeParcelWriter.writeDoubleObject(r5, 13, r4.zzm(), false);
        SafeParcelWriter.writeLongObject(r5, 14, r4.zzn(), false);
        SafeParcelWriter.writeLongObject(r5, 15, r4.zzo(), false);
        SafeParcelWriter.writeString(r5, 16, r4.zzp(), false);
        SafeParcelWriter.writeTypedList(r5, 17, r4.zzq(), false);
        SafeParcelWriter.writeStringList(r5, 18, r4.zzr(), false);
        SafeParcelWriter.writeString(r5, 19, r4.zzs(), false);
        SafeParcelWriter.writeBundle(r5, 20, r4.zzt(), false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r26) {
        int r1 = SafeParcelReader.validateObjectHeader(r26);
        String r6 = null;
        String r7 = null;
        Boolean r8 = null;
        ArrayList<String> r9 = null;
        zzcp r10 = null;
        Long r11 = null;
        Long r12 = null;
        ArrayList<String> r13 = null;
        Long r14 = null;
        Long r15 = null;
        Double r16 = null;
        Double r17 = null;
        Long r18 = null;
        Long r19 = null;
        String r20 = null;
        ArrayList r21 = null;
        ArrayList<String> r22 = null;
        String r23 = null;
        Bundle r24 = null;
        int r5 = 0;
    L4:
        if (r26.dataPosition() >= r1) goto L28;
        int r2 = SafeParcelReader.readHeader(r26);
        switch(SafeParcelReader.getFieldId(r2)) {
            case 1: goto L27;
            case 2: goto L26;
            case 3: goto L25;
            case 4: goto L24;
            case 5: goto L23;
            case 6: goto L22;
            case 7: goto L21;
            case 8: goto L20;
            case 9: goto L19;
            case 10: goto L18;
            case 11: goto L17;
            case 12: goto L16;
            case 13: goto L15;
            case 14: goto L14;
            case 15: goto L13;
            case 16: goto L12;
            case 17: goto L11;
            case 18: goto L10;
            case 19: goto L9;
            case 20: goto L8;
            default: goto L7;
        };
    L8:
        r24 = SafeParcelReader.createBundle(r26, r2);
        goto L4
    L9:
        r23 = SafeParcelReader.createString(r26, r2);
        goto L4
    L10:
        r22 = SafeParcelReader.createStringList(r26, r2);
        goto L4
    L11:
        r21 = SafeParcelReader.createTypedList(r26, r2, zzcp.CREATOR);
        goto L4
    L12:
        r20 = SafeParcelReader.createString(r26, r2);
        goto L4
    L13:
        r19 = SafeParcelReader.readLongObject(r26, r2);
        goto L4
    L14:
        r18 = SafeParcelReader.readLongObject(r26, r2);
        goto L4
    L15:
        r17 = SafeParcelReader.readDoubleObject(r26, r2);
        goto L4
    L16:
        r16 = SafeParcelReader.readDoubleObject(r26, r2);
        goto L4
    L17:
        r15 = SafeParcelReader.readLongObject(r26, r2);
        goto L4
    L18:
        r14 = SafeParcelReader.readLongObject(r26, r2);
        goto L4
    L19:
        r13 = SafeParcelReader.createStringList(r26, r2);
        goto L4
    L20:
        r12 = SafeParcelReader.readLongObject(r26, r2);
        goto L4
    L21:
        r11 = SafeParcelReader.readLongObject(r26, r2);
        goto L4
    L22:
        r10 = (zzcp) SafeParcelReader.createParcelable(r26, r2, zzcp.CREATOR);
        goto L4
    L23:
        r9 = SafeParcelReader.createStringList(r26, r2);
        goto L4
    L24:
        r8 = SafeParcelReader.readBooleanObject(r26, r2);
        goto L4
    L25:
        r7 = SafeParcelReader.createString(r26, r2);
        goto L4
    L26:
        r6 = SafeParcelReader.createString(r26, r2);
        goto L4
    L27:
        r5 = SafeParcelReader.readInt(r26, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r26, r2);
        goto L4
    L28:
        SafeParcelReader.ensureAtEnd(r26, r1);
        return new zzcp(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new zzcp[r1];
    }
}
