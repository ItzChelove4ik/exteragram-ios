package com.google.android.gms.identitycredentials;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class ClearRegistryResponseCreator implements Parcelable.Creator<ClearRegistryResponse> {
    public ClearRegistryResponseCreator() {
    }

    public static void writeToParcel(ClearRegistryResponse r1, Parcel r2, int r3) {
        int r32 = SafeParcelWriter.beginObjectHeader(r2);
        SafeParcelWriter.writeBoolean(r2, 1, r1.isDeleted());
        SafeParcelWriter.finishObjectHeader(r2, r32);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ClearRegistryResponse createFromParcel(Parcel r5) {
        int r4 = SafeParcelReader.validateObjectHeader(r5);
        boolean r0 = false;
    L4:
        if (r5.dataPosition() >= r4) goto L9;
        int r1 = SafeParcelReader.readHeader(r5);
        if (SafeParcelReader.getFieldId(r1) != 1) goto L7;
        r0 = SafeParcelReader.readBoolean(r5, r1);
        goto L4
    L7:
        SafeParcelReader.skipUnknownField(r5, r1);
        goto L4
    L9:
        SafeParcelReader.ensureAtEnd(r5, r4);
        return new ClearRegistryResponse(r0);
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ ClearRegistryResponse createFromParcel(Parcel r1) {
        return createFromParcel(r1);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ClearRegistryResponse[] newArray(int r1) {
        return new ClearRegistryResponse[r1];
    }

    @Override // android.os.Parcelable.Creator
    public /* bridge */ /* synthetic */ ClearRegistryResponse[] newArray(int r1) {
        return newArray(r1);
    }
}
