package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class zbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zbf> CREATOR = null;
    private final String zba;
    private final boolean zbb;

    static {
        CREATOR = new zbg();
    }

    public zbf(String r1, boolean r2) {
        this.zba = r1;
        this.zbb = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zbf) == false) goto L10;
        zbf r42 = (zbf) r4;
        if (this.zba.equals(r42.zba) == false) goto L10;
        if (this.zbb != r42.zbb) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, Boolean.valueOf(this.zbb)});
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r4, int r5) {
        String r52 = this.zba;
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r52, false);
        SafeParcelWriter.writeBoolean(r4, 2, this.zbb);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
