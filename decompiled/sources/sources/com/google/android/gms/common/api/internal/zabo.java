package com.google.android.gms.common.api.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final /* synthetic */ class zabo implements Executor {
    private final /* synthetic */ Handler zaa;

    public /* synthetic */ zabo(Handler r1) {
        this.zaa = r1;
    }

    @Override // java.util.concurrent.Executor
    public final /* synthetic */ void execute(Runnable r1) {
        this.zaa.post(r1);
    }
}
