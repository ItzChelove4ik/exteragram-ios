package com.google.android.gms.identitycredentials;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class CreateCredentialResponseCreator implements Parcelable.Creator<CreateCredentialResponse> {
    public CreateCredentialResponseCreator() {
    }

    public static void writeToParcel(CreateCredentialResponse r3, Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 1, r3.getType(), false);
        SafeParcelWriter.writeBundle(r4, 2, r3.getData(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CreateCredentialResponse createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        String r0 = null;
        Bundle r1 = null;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 1) goto L8;
        r0 = SafeParcelReader.createString(r6, r2);
        goto L4
    L8:
        if (r3 != 2) goto L9;
        r1 = SafeParcelReader.createBundle(r6, r2);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new CreateCredentialResponse(r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CreateCredentialResponse createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CreateCredentialResponse[] newArray(int r1) {
        return new CreateCredentialResponse[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CreateCredentialResponse[] newArray(int r1) {
        return newArray(r1);
    }
}
