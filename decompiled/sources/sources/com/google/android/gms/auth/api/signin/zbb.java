package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zbb implements Parcelable.Creator {
    public zbb() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        String r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        GoogleSignInAccount r2 = null;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 4) goto L8;
        r0 = SafeParcelReader.createString(r7, r3);
        goto L4
    L8:
        if (r4 != 7) goto L10;
        r2 = (GoogleSignInAccount) SafeParcelReader.createParcelable(r7, r3, GoogleSignInAccount.CREATOR);
        goto L4
    L10:
        if (r4 != 8) goto L11;
        r1 = SafeParcelReader.createString(r7, r3);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new SignInAccount(r0, r2, r1);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new SignInAccount[r1];
    }
}
