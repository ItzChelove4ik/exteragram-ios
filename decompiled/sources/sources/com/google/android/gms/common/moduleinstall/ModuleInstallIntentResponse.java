package com.google.android.gms.common.moduleinstall;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class ModuleInstallIntentResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ModuleInstallIntentResponse> CREATOR = null;
    private final PendingIntent zaa;

    static {
        CREATOR = new zab();
    }

    public ModuleInstallIntentResponse(PendingIntent r1) {
        this.zaa = r1;
    }

    public PendingIntent getPendingIntent() {
        return this.zaa;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r0 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeParcelable(r4, 1, getPendingIntent(), r5, false);
        SafeParcelWriter.finishObjectHeader(r4, r0);
    }
}
