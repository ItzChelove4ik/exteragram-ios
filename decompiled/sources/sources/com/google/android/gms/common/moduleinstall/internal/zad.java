package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;

/* loaded from: classes4.dex */
public abstract class zad extends com.google.android.gms.internal.base.zab implements zae {
    public zad() {
        super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks");
    }

    @Override // com.google.android.gms.internal.base.zab
    public final boolean zaa(int r1, Parcel r2, Parcel r3, int r4) {
        if (r1 != 1) goto L5;
        Status r12 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
        ModuleAvailabilityResponse r42 = (ModuleAvailabilityResponse) com.google.android.gms.internal.base.zac.zaa(r2, ModuleAvailabilityResponse.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        zab(r12, r42);
    L16:
        return true;
    L5:
        if (r1 != 2) goto L7;
        Status r13 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
        ModuleInstallResponse r43 = (ModuleInstallResponse) com.google.android.gms.internal.base.zac.zaa(r2, ModuleInstallResponse.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        zac(r13, r43);
        goto L16
    L7:
        if (r1 != 3) goto L9;
        Status r14 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
        ModuleInstallIntentResponse r44 = (ModuleInstallIntentResponse) com.google.android.gms.internal.base.zac.zaa(r2, ModuleInstallIntentResponse.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        zad(r14, r44);
        goto L16
    L9:
        if (r1 == 4) goto L12;
        return false;
    L12:
        Status r15 = (Status) com.google.android.gms.internal.base.zac.zaa(r2, Status.CREATOR);
        com.google.android.gms.internal.base.zac.zad(r2);
        zae(r15);
        goto L16
    }
}
