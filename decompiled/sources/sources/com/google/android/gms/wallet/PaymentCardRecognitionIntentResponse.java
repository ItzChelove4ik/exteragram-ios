package com.google.android.gms.wallet;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public class PaymentCardRecognitionIntentResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentCardRecognitionIntentResponse> CREATOR = null;
    PendingIntent zza;

    static {
        CREATOR = new zzz();
    }

    public PaymentCardRecognitionIntentResponse(PendingIntent r1) {
        this.zza = r1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeParcelable(r4, 1, this.zza, r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
