package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class ModuleAvailabilityResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ModuleAvailabilityResponse> CREATOR = null;
    private final boolean zaa;
    private final int zab;

    static {
        CREATOR = new zaa();
    }

    public ModuleAvailabilityResponse(boolean r1, int r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    public boolean areModulesAvailable() {
        return this.zaa;
    }

    public int getAvailabilityStatus() {
        return this.zab;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeBoolean(r3, 1, areModulesAvailable());
        SafeParcelWriter.writeInt(r3, 2, getAvailabilityStatus());
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
