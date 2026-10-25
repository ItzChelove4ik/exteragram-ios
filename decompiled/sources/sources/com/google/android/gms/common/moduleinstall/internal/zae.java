package com.google.android.gms.common.moduleinstall.internal;

import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;

/* loaded from: classes4.dex */
public interface zae extends IInterface {
    void zab(Status r1, ModuleAvailabilityResponse r2);

    void zac(Status r1, ModuleInstallResponse r2);

    void zad(Status r1, ModuleInstallIntentResponse r2);

    void zae(Status r1);
}
