package com.google.android.gms.identitycredentials;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class PendingImportCredentialsHandleCreator implements Parcelable.Creator<PendingImportCredentialsHandle> {
    public PendingImportCredentialsHandleCreator() {
    }

    public static void writeToParcel(PendingImportCredentialsHandle r3, Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeParcelable(r4, 1, r3.getPendingIntent(), r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PendingImportCredentialsHandle createFromParcel(Parcel r5) {
        int r4 = SafeParcelReader.validateObjectHeader(r5);
        PendingIntent r0 = null;
    L4:
        if (r5.dataPosition() >= r4) goto L9;
        int r1 = SafeParcelReader.readHeader(r5);
        if (SafeParcelReader.getFieldId(r1) != 1) goto L7;
        r0 = (PendingIntent) SafeParcelReader.createParcelable(r5, r1, PendingIntent.CREATOR);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r5, r1);
        goto L4
    L9:
        SafeParcelReader.ensureAtEnd(r5, r4);
        return new PendingImportCredentialsHandle(r0);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ PendingImportCredentialsHandle createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public PendingImportCredentialsHandle[] newArray(int r1) {
        return new PendingImportCredentialsHandle[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ PendingImportCredentialsHandle[] newArray(int r1) {
        return newArray(r1);
    }
}
