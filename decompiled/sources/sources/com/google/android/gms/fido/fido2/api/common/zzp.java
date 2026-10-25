package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzp implements Parcelable.Creator {
    public zzp() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
        return COSEAlgorithmIdentifier.fromCoseValue(r1.readInt());
    L5:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new COSEAlgorithmIdentifier[r1];
    }
}
