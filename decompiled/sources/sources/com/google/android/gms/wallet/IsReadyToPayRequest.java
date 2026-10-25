package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class IsReadyToPayRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<IsReadyToPayRequest> CREATOR = null;
    ArrayList zza;
    String zzb;
    String zzc;
    ArrayList zzd;
    boolean zze;
    String zzf;

    @Deprecated
    public final class Builder {
        final /* synthetic */ IsReadyToPayRequest zza;

        public /* synthetic */ Builder(IsReadyToPayRequest r1, zzp r2) {
            this.zza = r1;
        }

        public IsReadyToPayRequest build() {
            return this.zza;
        }
    }

    static {
        CREATOR = new zzq();
    }

    public IsReadyToPayRequest() {
    }

    public IsReadyToPayRequest(ArrayList r1, String r2, String r3, ArrayList r4, boolean r5, String r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    public static IsReadyToPayRequest fromJson(String r2) {
        Builder r0 = newBuilder();
        r0.zza.zzf = (String) Preconditions.checkNotNull(r2, "isReadyToPayRequestJson cannot be null!");
        return r0.build();
    }

    @Deprecated
    public static Builder newBuilder() {
        return new Builder(new IsReadyToPayRequest(), null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeIntegerList(r4, 2, this.zza, false);
        SafeParcelWriter.writeString(r4, 4, this.zzb, false);
        SafeParcelWriter.writeString(r4, 5, this.zzc, false);
        SafeParcelWriter.writeIntegerList(r4, 6, this.zzd, false);
        SafeParcelWriter.writeBoolean(r4, 7, this.zze);
        SafeParcelWriter.writeString(r4, 8, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
