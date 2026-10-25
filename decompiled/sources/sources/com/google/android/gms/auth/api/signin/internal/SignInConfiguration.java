package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = null;
    private final String zba;
    private final GoogleSignInOptions zbb;

    static {
        CREATOR = new zbu();
    }

    public SignInConfiguration(String r1, GoogleSignInOptions r2) {
        this.zba = Preconditions.checkNotEmpty(r1);
        this.zbb = r2;
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof SignInConfiguration) == true) goto L5;
        return false;
    L5:
        SignInConfiguration r42 = (SignInConfiguration) r4;
        if (this.zba.equals(r42.zba) == false) goto L16;
        GoogleSignInOptions r3 = this.zbb;
        GoogleSignInOptions r43 = r42.zbb;
        if (r3 != null) goto L12;
        if (r43 != null) goto L16;
        return true;
    L12:
        if (r3.equals(r43) == false) goto L16;
        return true;
    L16:
        return false;
    }

    public final int hashCode() {
        return new HashAccumulator().addObject(this.zba).addObject(this.zbb).hash();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        String r0 = this.zba;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, r0, false);
        SafeParcelWriter.writeParcelable(r5, 5, this.zbb, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final GoogleSignInOptions zba() {
        return this.zbb;
    }
}
