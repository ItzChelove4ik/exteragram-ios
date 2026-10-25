package com.google.android.gms.auth.blockstore.restorecredential;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class GetRestoreCredentialResponseCreator implements Parcelable.Creator<GetRestoreCredentialResponse> {
    public GetRestoreCredentialResponseCreator() {
    }

    public static void writeToParcel(GetRestoreCredentialResponse r2, Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBundle(r3, 1, r2.getResponseBundle(), false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GetRestoreCredentialResponse createFromParcel(Parcel r5) {
        int r4 = SafeParcelReader.validateObjectHeader(r5);
        Bundle r0 = null;
    L4:
        if (r5.dataPosition() >= r4) goto L9;
        int r1 = SafeParcelReader.readHeader(r5);
        if (SafeParcelReader.getFieldId(r1) != 1) goto L7;
        r0 = SafeParcelReader.createBundle(r5, r1);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r5, r1);
        goto L4
    L9:
        SafeParcelReader.ensureAtEnd(r5, r4);
        return new GetRestoreCredentialResponse(r0);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ GetRestoreCredentialResponse createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public GetRestoreCredentialResponse[] newArray(int r1) {
        return new GetRestoreCredentialResponse[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ GetRestoreCredentialResponse[] newArray(int r1) {
        return newArray(r1);
    }
}
