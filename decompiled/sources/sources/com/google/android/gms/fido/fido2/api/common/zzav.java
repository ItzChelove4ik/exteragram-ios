package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzav implements Parcelable.Creator {
    public zzav() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel r1) {
        String r0 = r1.readString();
        if (r0 != null) goto L10;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        return ResidentKeyRequirement.fromString(r0);
    L7:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int r1) {
        return new ResidentKeyRequirement[r1];
    }
}
