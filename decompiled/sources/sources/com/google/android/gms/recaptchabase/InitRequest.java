package com.google.android.gms.recaptchabase;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public final class InitRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<InitRequest> CREATOR = null;

    public static final class Builder {
        private final InitRequest zza;

        public Builder() {
            this.zza = new InitRequest();
        }

        public InitRequest build() {
            return this.zza;
        }
    }

    static {
        CREATOR = new zzf();
    }

    public InitRequest() {
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof InitRequest) == false) goto L8;
        return true;
    L8:
        return false;
    }

    public int hashCode() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        SafeParcelWriter.finishObjectHeader(r1, SafeParcelWriter.beginObjectHeader(r1));
    }
}
