package com.google.android.gms.common.internal.safeparcel;

/* loaded from: classes.dex */
public abstract class AbstractSafeParcelable implements SafeParcelable {
    public AbstractSafeParcelable() {
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }
}
