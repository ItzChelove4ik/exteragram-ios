package com.google.android.gms.identitycredentials;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final class GetCredentialRequestCreator implements Parcelable.Creator<GetCredentialRequest> {
    public GetCredentialRequestCreator() {
    }

    public static void writeToParcel(GetCredentialRequest r4, Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeTypedList(r5, 1, r4.getCredentialOptions(), false);
        SafeParcelWriter.writeBundle(r5, 2, r4.getData(), false);
        SafeParcelWriter.writeString(r5, 3, r4.getOrigin(), false);
        SafeParcelWriter.writeParcelable(r5, 4, r4.getResultReceiver(), r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GetCredentialRequest createFromParcel(Parcel r8) {
        int r7 = SafeParcelReader.validateObjectHeader(r8);
        ArrayList r0 = null;
        Bundle r1 = null;
        String r2 = null;
        ResultReceiver r3 = null;
    L4:
        if (r8.dataPosition() >= r7) goto L18;
        int r4 = SafeParcelReader.readHeader(r8);
        int r5 = SafeParcelReader.getFieldId(r4);
        if (r5 != 1) goto L8;
        r0 = SafeParcelReader.createTypedList(r8, r4, CredentialOption.CREATOR);
        goto L4
    L8:
        if (r5 != 2) goto L10;
        r1 = SafeParcelReader.createBundle(r8, r4);
        goto L4
    L10:
        if (r5 != 3) goto L12;
        r2 = SafeParcelReader.createString(r8, r4);
        goto L4
    L12:
        if (r5 != 4) goto L13;
        r3 = (ResultReceiver) SafeParcelReader.createParcelable(r8, r4, ResultReceiver.CREATOR);
        goto L4
    L13:
        SafeParcelReader.skipUnknownField(r8, r4);
        goto L4
    L18:
        SafeParcelReader.ensureAtEnd(r8, r7);
        return new GetCredentialRequest(r0, r1, r2, r3);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ GetCredentialRequest createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GetCredentialRequest[] newArray(int r1) {
        return new GetCredentialRequest[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ GetCredentialRequest[] newArray(int r1) {
        return newArray(r1);
    }
}
