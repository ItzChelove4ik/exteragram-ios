package com.google.android.gms.identitycredentials;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class CreateCredentialRequestCreator implements Parcelable.Creator<CreateCredentialRequest> {
    public CreateCredentialRequestCreator() {
    }

    public static void writeToParcel(CreateCredentialRequest r4, Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 1, r4.getType(), false);
        SafeParcelWriter.writeBundle(r5, 2, r4.getCredentialData(), false);
        SafeParcelWriter.writeBundle(r5, 3, r4.getCandidateQueryData(), false);
        SafeParcelWriter.writeString(r5, 4, r4.getOrigin(), false);
        SafeParcelWriter.writeString(r5, 5, r4.getRequestJson(), false);
        SafeParcelWriter.writeParcelable(r5, 6, r4.getResultReceiver(), r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CreateCredentialRequest createFromParcel(Parcel r9) {
        int r8 = SafeParcelReader.validateObjectHeader(r9);
        String r2 = null;
        Bundle r3 = null;
        Bundle r4 = null;
        String r5 = null;
        String r6 = null;
        ResultReceiver r7 = null;
    L4:
        if (r9.dataPosition() >= r8) goto L14;
        int r0 = SafeParcelReader.readHeader(r9);
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
        r7 = (ResultReceiver) SafeParcelReader.createParcelable(r9, r0, ResultReceiver.CREATOR);
        goto L4
    L9:
        r6 = SafeParcelReader.createString(r9, r0);
        goto L4
    L10:
        r5 = SafeParcelReader.createString(r9, r0);
        goto L4
    L11:
        r4 = SafeParcelReader.createBundle(r9, r0);
        goto L4
    L12:
        r3 = SafeParcelReader.createBundle(r9, r0);
        goto L4
    L13:
        r2 = SafeParcelReader.createString(r9, r0);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r9, r0);
        goto L4
    L14:
        SafeParcelReader.ensureAtEnd(r9, r8);
        return new CreateCredentialRequest(r2, r3, r4, r5, r6, r7);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CreateCredentialRequest createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CreateCredentialRequest[] newArray(int r1) {
        return new CreateCredentialRequest[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CreateCredentialRequest[] newArray(int r1) {
        return newArray(r1);
    }
}
