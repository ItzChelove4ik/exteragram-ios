package com.google.android.gms.identitycredentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class RegisterExportResponseCreator implements Parcelable.Creator<RegisterExportResponse> {
    public RegisterExportResponseCreator() {
    }

    public static void writeToParcel(RegisterExportResponse r0, Parcel r1, int r2) {
        SafeParcelWriter.finishObjectHeader(r1, SafeParcelWriter.beginObjectHeader(r1));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public RegisterExportResponse createFromParcel(Parcel r2) {
        int r1 = SafeParcelReader.validateObjectHeader(r2);
    L4:
        if (r2.dataPosition() >= r1) goto L6;
        int r0 = SafeParcelReader.readHeader(r2);
        SafeParcelReader.getFieldId(r0);
        SafeParcelReader.skipUnknownField(r2, r0);
        goto L4
    L6:
        SafeParcelReader.ensureAtEnd(r2, r1);
        return new RegisterExportResponse();
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ RegisterExportResponse createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public RegisterExportResponse[] newArray(int r1) {
        return new RegisterExportResponse[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ RegisterExportResponse[] newArray(int r1) {
        return newArray(r1);
    }
}
