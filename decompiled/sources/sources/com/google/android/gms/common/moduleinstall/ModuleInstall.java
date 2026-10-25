package com.google.android.gms.common.moduleinstall;

import android.content.Context;
import com.google.android.gms.common.moduleinstall.internal.zay;

/* loaded from: classes4.dex */
public abstract class ModuleInstall {
    public static ModuleInstallClient getClient(Context r1) {
        return new zay(r1);
    }
}
