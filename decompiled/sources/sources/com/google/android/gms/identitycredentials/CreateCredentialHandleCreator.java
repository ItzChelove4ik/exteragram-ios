package com.google.android.gms.identitycredentials;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class CreateCredentialHandleCreator implements Parcelable.Creator<CreateCredentialHandle> {
    public CreateCredentialHandleCreator() {
    }

    public static void writeToParcel(CreateCredentialHandle r4, Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 1, r4.getPendingIntent(), r6, false);
        SafeParcelWriter.writeParcelable(r5, 2, r4.getCreateCredentialResponse(), r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CreateCredentialHandle createFromParcel(Parcel r6) {
        int r5 = SafeParcelReader.validateObjectHeader(r6);
        PendingIntent r0 = null;
        CreateCredentialResponse r1 = null;
    L4:
        if (r6.dataPosition() >= r5) goto L12;
        int r2 = SafeParcelReader.readHeader(r6);
        int r3 = SafeParcelReader.getFieldId(r2);
        if (r3 != 1) goto L8;
        r0 = (PendingIntent) SafeParcelReader.createParcelable(r6, r2, PendingIntent.CREATOR);
        goto L4
    L8:
        if (r3 != 2) goto L9;
        r1 = (CreateCredentialResponse) SafeParcelReader.createParcelable(r6, r2, CreateCredentialResponse.CREATOR);
        goto L4
    L9:
        SafeParcelReader.skipUnknownField(r6, r2);
        goto L4
    L12:
        SafeParcelReader.ensureAtEnd(r6, r5);
        return new CreateCredentialHandle(r0, r1);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CreateCredentialHandle createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CreateCredentialHandle[] newArray(int r1) {
        return new CreateCredentialHandle[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ CreateCredentialHandle[] newArray(int r1) {
        return newArray(r1);
    }
}
