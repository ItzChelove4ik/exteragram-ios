package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/* loaded from: classes4.dex */
final class zae implements View.OnClickListener {
    final /* synthetic */ Context zaa;
    final /* synthetic */ Intent zab;

    public zae(Context r1, Intent r2) {
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r2) {
        this.zaa.startActivity(this.zab);     // Catch: ActivityNotFoundException -> L4
        return;
    L4:
        e = move-exception;
        Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
    }
}
