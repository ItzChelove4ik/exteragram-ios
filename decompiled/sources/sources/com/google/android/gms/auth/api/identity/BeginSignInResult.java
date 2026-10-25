package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
/* loaded from: classes4.dex */
public final class BeginSignInResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<BeginSignInResult> CREATOR = null;
    private final PendingIntent zba;

    static {
        CREATOR = new zbe();
    }

    public BeginSignInResult(PendingIntent r1) {
        this.zba = (PendingIntent) Preconditions.checkNotNull(r1);
    }

    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeParcelable(r4, 1, getPendingIntent(), r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
