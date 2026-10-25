package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public class ModuleInstallResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ModuleInstallResponse> CREATOR = null;
    private final int zaa;
    private final boolean zab;

    static {
        CREATOR = new zac();
    }

    public ModuleInstallResponse(int r2) {
        this(r2, false);
    }

    public ModuleInstallResponse(int r1, boolean r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    public int getSessionId() {
        return this.zaa;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeInt(r3, 1, getSessionId());
        SafeParcelWriter.writeBoolean(r3, 2, this.zab);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
