package com.google.android.gms.identitycredentials;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class CredentialOptionCreator implements Parcelable.Creator<CredentialOption> {
    public CredentialOptionCreator() {
    }

    public static void writeToParcel(CredentialOption r3, Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r3.getType(), false);
        SafeParcelWriter.writeBundle(r4, 2, r3.getCredentialRetrievalData(), false);
        SafeParcelWriter.writeBundle(r4, 3, r3.getCandidateQueryData(), false);
        SafeParcelWriter.writeString(r4, 4, r3.getRequestMatcher(), false);
        SafeParcelWriter.writeString(r4, 5, r3.getRequestType(), false);
        SafeParcelWriter.writeString(r4, 6, r3.getProtocolType(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CredentialOption createFromParcel(Parcel r10) {
        int r9 = SafeParcelReader.validateObjectHeader(r10);
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r7 = r3;
        String r8 = r7;
        Bundle r4 = null;
        Bundle r5 = null;
        String r6 = null;
    L4:
        if (r10.dataPosition() >= r9) goto L14;
        int r0 = SafeParcelReader.readHeader(r10);
        switch(SafeParcelReader.getFieldId(r0)) {
            case 1: goto L13;
            case 2: goto L12;
            case 3: goto L11;
            case 4: goto L10;
            case 5: goto L9;
            case 6: goto L8;
            default: goto L7;
        };
    L8:
        r8 = SafeParcelReader.createString(r10, r0);
        goto L4
    L9:
        r7 = SafeParcelReader.createString(r10, r0);
        goto L4
    L10:
        r6 = SafeParcelReader.createString(r10, r0);
        goto L4
    L11:
        r5 = SafeParcelReader.createBundle(r10, r0);
        goto L4
    L12:
        r4 = SafeParcelReader.createBundle(r10, r0);
        goto L4
    L13:
        r3 = SafeParcelReader.createString(r10, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r10, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r10, r9);
        return new CredentialOption(r3, r4, r5, r6, r7, r8);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CredentialOption createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CredentialOption[] newArray(int r1) {
        return new CredentialOption[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CredentialOption[] newArray(int r1) {
        return newArray(r1);
    }
}
