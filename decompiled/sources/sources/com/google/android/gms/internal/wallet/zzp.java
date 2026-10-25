package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.PaymentCardRecognitionIntentResponse;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.zzau;

/* loaded from: classes5.dex */
public abstract class zzp extends zzb implements zzq {
    public zzp() {
        super("com.google.android.gms.wallet.internal.IWalletServiceCallbacks");
    }

    @Override // com.google.android.gms.internal.wallet.zzb
    public final boolean zza(int r1, Parcel r2, Parcel r3, int r4) {
        switch(r1) {
            case 1: goto L23;
            case 2: goto L22;
            case 3: goto L21;
            case 4: goto L20;
            case 5: goto L3;
            case 6: goto L19;
            case 7: goto L18;
            case 8: goto L17;
            case 9: goto L16;
            case 10: goto L15;
            case 11: goto L14;
            case 12: goto L13;
            case 13: goto L12;
            case 14: goto L11;
            case 15: goto L10;
            case 16: goto L9;
            case 17: goto L8;
            case 18: goto L7;
            case 19: goto L6;
            case 20: goto L5;
            default: goto L3;
        };
    L3:
        return false;
    L5:
        Status r0 = (Status) zzc.zza(r2, Status.CREATOR);
        com.google.android.gms.wallet.button.zzc r02 = (com.google.android.gms.wallet.button.zzc) zzc.zza(r2, com.google.android.gms.wallet.button.zzc.CREATOR);
        Bundle r03 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L6:
        Status r12 = (Status) zzc.zza(r2, Status.CREATOR);
        PaymentCardRecognitionIntentResponse r32 = (PaymentCardRecognitionIntentResponse) zzc.zza(r2, PaymentCardRecognitionIntentResponse.CREATOR);
        Bundle r42 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        zze(r12, r32, r42);
        return true;
    L7:
        r2.readInt();
        Bundle r04 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L8:
        Status r05 = (Status) zzc.zza(r2, Status.CREATOR);
        zzk r06 = (zzk) zzc.zza(r2, zzk.CREATOR);
        Bundle r07 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L9:
        Status r08 = (Status) zzc.zza(r2, Status.CREATOR);
        zzi r09 = (zzi) zzc.zza(r2, zzi.CREATOR);
        Bundle r010 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L10:
        Status r011 = (Status) zzc.zza(r2, Status.CREATOR);
        zzm r012 = (zzm) zzc.zza(r2, zzm.CREATOR);
        Bundle r013 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L11:
        Status r13 = (Status) zzc.zza(r2, Status.CREATOR);
        PaymentData r33 = (PaymentData) zzc.zza(r2, PaymentData.CREATOR);
        Bundle r43 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        zzf(r13, r33, r43);
        return true;
    L12:
        Status r014 = (Status) zzc.zza(r2, Status.CREATOR);
        Bundle r015 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L13:
        Status r016 = (Status) zzc.zza(r2, Status.CREATOR);
        zzau r017 = (zzau) zzc.zza(r2, zzau.CREATOR);
        Bundle r018 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L14:
        Status r019 = (Status) zzc.zza(r2, Status.CREATOR);
        Bundle r020 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L15:
        Status r021 = (Status) zzc.zza(r2, Status.CREATOR);
        zzg r022 = (zzg) zzc.zza(r2, zzg.CREATOR);
        Bundle r023 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L16:
        Status r14 = (Status) zzc.zza(r2, Status.CREATOR);
        boolean r34 = zzc.zze(r2);
        Bundle r44 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        zzc(r14, r34, r44);
        return true;
    L17:
        Status r024 = (Status) zzc.zza(r2, Status.CREATOR);
        Bundle r025 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L18:
        Status r026 = (Status) zzc.zza(r2, Status.CREATOR);
        zze r027 = (zze) zzc.zza(r2, zze.CREATOR);
        Bundle r028 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L19:
        r2.readInt();
        zzc.zze(r2);
        Bundle r029 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        return true;
    L20:
        int r15 = r2.readInt();
        Bundle r35 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        zzh(r15, r35);
        return true;
    L21:
        int r16 = r2.readInt();
        boolean r36 = zzc.zze(r2);
        Bundle r45 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        zzg(r16, r36, r45);
        return true;
    L22:
        int r17 = r2.readInt();
        FullWallet r37 = (FullWallet) zzc.zza(r2, FullWallet.CREATOR);
        Bundle r46 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        zzb(r17, r37, r46);
        return true;
    L23:
        int r18 = r2.readInt();
        MaskedWallet r38 = (MaskedWallet) zzc.zza(r2, MaskedWallet.CREATOR);
        Bundle r47 = (Bundle) zzc.zza(r2, Bundle.CREATOR);
        zzc.zzb(r2);
        zzd(r18, r38, r47);
        return true;
    }
}
