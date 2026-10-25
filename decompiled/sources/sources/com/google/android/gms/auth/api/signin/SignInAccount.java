package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class SignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInAccount> CREATOR = null;

    @Deprecated
    final String zba;

    @Deprecated
    final String zbb;
    private final GoogleSignInAccount zbc;

    static {
        CREATOR = new zbb();
    }

    public SignInAccount(String r1, GoogleSignInAccount r2, String r3) {
        this.zbc = r2;
        this.zba = Preconditions.checkNotEmpty(r1, "8.3 and 8.4 SDKs require non-null email");
        this.zbb = Preconditions.checkNotEmpty(r3, "8.3 and 8.4 SDKs require non-null userId");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        String r0 = this.zba;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 4, r0, false);
        SafeParcelWriter.writeParcelable(r5, 7, this.zbc, r6, false);
        SafeParcelWriter.writeString(r5, 8, this.zbb, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final GoogleSignInAccount zba() {
        return this.zbc;
    }
}
