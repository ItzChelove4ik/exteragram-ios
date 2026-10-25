package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.PaymentCardRecognitionIntentResponse;
import com.google.android.gms.wallet.PaymentData;

/* loaded from: classes5.dex */
public interface zzq extends IInterface {
    void zzb(int r1, FullWallet r2, Bundle r3);

    void zzc(Status r1, boolean r2, Bundle r3);

    void zzd(int r1, MaskedWallet r2, Bundle r3);

    void zze(Status r1, PaymentCardRecognitionIntentResponse r2, Bundle r3);

    void zzf(Status r1, PaymentData r2, Bundle r3);

    void zzg(int r1, boolean r2, Bundle r3);

    void zzh(int r1, Bundle r2);
}
