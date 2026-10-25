package org.telegram.ui.Components;

import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ShareAlert$$ExternalSyntheticLambda21 implements Runnable {
    public final /* synthetic */ ShareAlert f$0;
    public final /* synthetic */ AtomicReference f$1;
    public final /* synthetic */ NotificationCenter.NotificationCenterDelegate f$2;
    public final /* synthetic */ TLRPC.Dialog f$3;

    public /* synthetic */ ShareAlert$$ExternalSyntheticLambda21(ShareAlert r1, AtomicReference r2, NotificationCenter.NotificationCenterDelegate r3, TLRPC.Dialog r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ShareAlert.$r8$lambda$cYrHFUawrwPZAiMrLTCHATfBRWk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
