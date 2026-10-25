package com.google.android.gms.identitycredentials;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class SignalCredentialStateRequestCreator implements Parcelable.Creator<SignalCredentialStateRequest> {
    public SignalCredentialStateRequestCreator() {
    }

    public static void writeToParcel(SignalCredentialStateRequest r3, Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r3.getType(), false);
        SafeParcelWriter.writeString(r4, 2, r3.getOrigin(), false);
        SafeParcelWriter.writeBundle(r4, 3, r3.getRequestData(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public SignalCredentialStateRequest createFromParcel(Parcel r7) {
        int r6 = SafeParcelReader.validateObjectHeader(r7);
        String r0 = null;
        String r1 = null;
        Bundle r2 = null;
    L4:
        if (r7.dataPosition() >= r6) goto L15;
        int r3 = SafeParcelReader.readHeader(r7);
        int r4 = SafeParcelReader.getFieldId(r3);
        if (r4 != 1) goto L8;
        r0 = SafeParcelReader.createString(r7, r3);
        goto L4
    L8:
        if (r4 != 2) goto L10;
        r1 = SafeParcelReader.createString(r7, r3);
        goto L4
    L10:
        if (r4 != 3) goto L11;
        r2 = SafeParcelReader.createBundle(r7, r3);
        goto L4
    L11:
        SafeParcelReader.skipUnknownField(r7, r3);
        goto L4
    L15:
        SafeParcelReader.ensureAtEnd(r7, r6);
        return new SignalCredentialStateRequest(r0, r1, r2);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ SignalCredentialStateRequest createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public SignalCredentialStateRequest[] newArray(int r1) {
        return new SignalCredentialStateRequest[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ SignalCredentialStateRequest[] newArray(int r1) {
        return newArray(r1);
    }
}
