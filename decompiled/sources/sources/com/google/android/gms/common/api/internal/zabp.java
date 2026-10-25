package com.google.android.gms.common.api.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final /* synthetic */ class zabp implements Executor {
    private final /* synthetic */ Handler zaa;

    public /* synthetic */ zabp(Handler r1) {
        this.zaa = r1;
    }

    @Override // java.util.concurrent.Executor
    public final /* synthetic */ void execute(Runnable r1) {
        this.zaa.post(r1);
    }
}
