package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class zzw implements Parcelable.Creator {
    public zzw() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
        return ErrorCode.toErrorCode(r1.readInt());
    L5:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ErrorCode[r1];
    }
}
