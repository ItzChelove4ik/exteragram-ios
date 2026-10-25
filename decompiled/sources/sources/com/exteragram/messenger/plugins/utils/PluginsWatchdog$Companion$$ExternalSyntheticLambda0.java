package com.exteragram.messenger.plugins.utils;

import android.app.Activity;
import android.content.DialogInterface;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.utils.PluginsWatchdog;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginsWatchdog$Companion$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ Plugin f$0;
    public final /* synthetic */ Activity f$1;

    public /* synthetic */ PluginsWatchdog$Companion$$ExternalSyntheticLambda0(Plugin r1, Activity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r2, int r3) {
        PluginsWatchdog.Companion.$r8$lambda$YVUu_yybweI20Wq2RsPMfENiUXc(this.f$0, this.f$1, r2, r3);
    }
}
