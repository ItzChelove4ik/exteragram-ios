package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;

@Deprecated
/* loaded from: classes4.dex */
public final class SignInCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInCredential> CREATOR = null;
    private final String zba;
    private final String zbb;
    private final String zbc;
    private final String zbd;
    private final Uri zbe;
    private final String zbf;
    private final String zbg;
    private final String zbh;
    private final PublicKeyCredential zbi;

    static {
        CREATOR = new zbu();
    }

    public SignInCredential(String r1, String r2, String r3, String r4, Uri r5, String r6, String r7, String r8, PublicKeyCredential r9) {
        this.zba = (String) Preconditions.checkNotNull(r1);
        this.zbb = r2;
        this.zbc = r3;
        this.zbd = r4;
        this.zbe = r5;
        this.zbf = r6;
        this.zbg = r7;
        this.zbh = r8;
        this.zbi = r9;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof SignInCredential) == true) goto L5;
        return false;
    L5:
        SignInCredential r42 = (SignInCredential) r4;
        if (Objects.equal(this.zba, r42.zba) == true) goto L8;
    L25:
        return false;
    L8:
        if (Objects.equal(this.zbb, r42.zbb) == false) goto L25;
        if (Objects.equal(this.zbc, r42.zbc) == false) goto L25;
        if (Objects.equal(this.zbd, r42.zbd) == false) goto L25;
        if (Objects.equal(this.zbe, r42.zbe) == false) goto L25;
        if (Objects.equal(this.zbf, r42.zbf) == false) goto L25;
        if (Objects.equal(this.zbg, r42.zbg) == false) goto L25;
        if (Objects.equal(this.zbh, r42.zbh) == false) goto L25;
        if (Objects.equal(this.zbi, r42.zbi) == false) goto L25;
        return true;
    }

    public String getDisplayName() {
        return this.zbb;
    }

    public String getFamilyName() {
        return this.zbd;
    }

    public String getGivenName() {
        return this.zbc;
    }

    public String getGoogleIdToken() {
        return this.zbg;
    }

    public String getId() {
        return this.zba;
    }

    public String getPassword() {
        return this.zbf;
    }

    @Deprecated
    public String getPhoneNumber() {
        return this.zbh;
    }

    public Uri getProfilePictureUri() {
        return this.zbe;
    }

    public PublicKeyCredential getPublicKeyCredential() {
        return this.zbi;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba, this.zbb, this.zbc, this.zbd, this.zbe, this.zbf, this.zbg, this.zbh, this.zbi});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, getId(), false);
        SafeParcelWriter.writeString(r5, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(r5, 3, getGivenName(), false);
        SafeParcelWriter.writeString(r5, 4, getFamilyName(), false);
        SafeParcelWriter.writeParcelable(r5, 5, getProfilePictureUri(), r6, false);
        SafeParcelWriter.writeString(r5, 6, getPassword(), false);
        SafeParcelWriter.writeString(r5, 7, getGoogleIdToken(), false);
        SafeParcelWriter.writeString(r5, 8, getPhoneNumber(), false);
        SafeParcelWriter.writeParcelable(r5, 9, getPublicKeyCredential(), r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
