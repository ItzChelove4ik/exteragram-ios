package com.google.android.gms.wallet;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class PaymentDataRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PaymentDataRequest> CREATOR = null;
    boolean zza;
    boolean zzb;
    CardRequirements zzc;
    boolean zzd;
    ShippingAddressRequirements zze;
    ArrayList zzf;
    PaymentMethodTokenizationParameters zzg;
    TransactionInfo zzh;
    boolean zzi;
    String zzj;
    byte[] zzk;
    Bundle zzl;

    @Deprecated
    public final class Builder {
        final /* synthetic */ PaymentDataRequest zza;

        public /* synthetic */ Builder(PaymentDataRequest r1, zzab r2) {
            this.zza = r1;
        }

        public PaymentDataRequest build() {
            PaymentDataRequest r0 = this.zza;
            if (r0.zzj != null) goto L10;
            if (r0.zzk != null) goto L10;
            Preconditions.checkNotNull(r0.zzf, "Allowed payment methods must be set! You can set it through addAllowedPaymentMethod() or addAllowedPaymentMethods() in the PaymentDataRequest Builder.");
            Preconditions.checkNotNull(this.zza.zzc, "Card requirements must be set!");
            PaymentDataRequest r02 = this.zza;
            if (r02.zzg == null) goto L10;
            Preconditions.checkNotNull(r02.zzh, "Transaction info must be set if paymentMethodTokenizationParameters is set!");
        L10:
            return this.zza;
        }
    }

    static {
        CREATOR = new zzac();
    }

    private PaymentDataRequest() {
        this.zzi = true;
    }

    public PaymentDataRequest(boolean r1, boolean r2, CardRequirements r3, boolean r4, ShippingAddressRequirements r5, ArrayList r6, PaymentMethodTokenizationParameters r7, TransactionInfo r8, boolean r9, String r10, byte[] r11, Bundle r12) {
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

    public static PaymentDataRequest fromJson(String r2) {
        Builder r0 = newBuilder();
        r0.zza.zzj = (String) Preconditions.checkNotNull(r2, "paymentDataRequestJson cannot be null!");
        return r0.build();
    }

    @Deprecated
    public static Builder newBuilder() {
        return new Builder(new PaymentDataRequest(), null);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeBoolean(r5, 1, this.zza);
        SafeParcelWriter.writeBoolean(r5, 2, this.zzb);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeBoolean(r5, 4, this.zzd);
        SafeParcelWriter.writeParcelable(r5, 5, this.zze, r6, false);
        SafeParcelWriter.writeIntegerList(r5, 6, this.zzf, false);
        SafeParcelWriter.writeParcelable(r5, 7, this.zzg, r6, false);
        SafeParcelWriter.writeParcelable(r5, 8, this.zzh, r6, false);
        SafeParcelWriter.writeBoolean(r5, 9, this.zzi);
        SafeParcelWriter.writeString(r5, 10, this.zzj, false);
        SafeParcelWriter.writeBundle(r5, 11, this.zzl, false);
        SafeParcelWriter.writeByteArray(r5, 12, this.zzk, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
