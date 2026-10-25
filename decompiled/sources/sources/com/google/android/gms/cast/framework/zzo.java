package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class zzo implements Parcelable.Creator {
    public zzo() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r28) {
        int r1 = SafeParcelReader.validateObjectHeader(r28);
        boolean r9 = false;
        boolean r11 = false;
        boolean r13 = false;
        boolean r16 = false;
        boolean r17 = false;
        boolean r18 = false;
        boolean r20 = false;
        int r21 = 0;
        boolean r22 = false;
        boolean r25 = false;
        boolean r26 = false;
        String r7 = null;
        ArrayList<String> r8 = null;
        LaunchOptions r10 = null;
        CastMediaOptions r12 = null;
        ArrayList<String> r19 = null;
        zzk r23 = null;
        zzm r24 = null;
        double r14 = 0.0d;
    L4:
        if (r28.dataPosition() >= r1) goto L27;
        int r2 = SafeParcelReader.readHeader(r28);
        switch(SafeParcelReader.getFieldId(r2)) {
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
        r26 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L9:
        r25 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L10:
        r24 = (zzm) SafeParcelReader.createParcelable(r28, r2, zzm.CREATOR);
        goto L4
    L11:
        r23 = (zzk) SafeParcelReader.createParcelable(r28, r2, zzk.CREATOR);
        goto L4
    L12:
        r22 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L13:
        r21 = SafeParcelReader.readInt(r28, r2);
        goto L4
    L14:
        r20 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L15:
        r19 = SafeParcelReader.createStringList(r28, r2);
        goto L4
    L16:
        r18 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L17:
        r17 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L18:
        r16 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L19:
        r14 = SafeParcelReader.readDouble(r28, r2);
        goto L4
    L20:
        r13 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L21:
        r12 = (CastMediaOptions) SafeParcelReader.createParcelable(r28, r2, CastMediaOptions.CREATOR);
        goto L4
    L22:
        r11 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L23:
        r10 = (LaunchOptions) SafeParcelReader.createParcelable(r28, r2, LaunchOptions.CREATOR);
        goto L4
    L24:
        r9 = SafeParcelReader.readBoolean(r28, r2);
        goto L4
    L25:
        r8 = SafeParcelReader.createStringList(r28, r2);
        goto L4
    L26:
        r7 = SafeParcelReader.createString(r28, r2);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r28, r2);
        goto L4
    L27:
        SafeParcelReader.ensureAtEnd(r28, r1);
        return new CastOptions(r7, r8, r9, r10, r11, r12, r13, r14, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new CastOptions[r1];
    }
}
