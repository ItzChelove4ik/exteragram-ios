package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Storage.CacheModel;

/* loaded from: classes6.dex */
public final /* synthetic */ class CachedMediaLayout$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CachedMediaLayout f$0;
    public final /* synthetic */ CacheModel.FileInfo f$1;
    public final /* synthetic */ TLRPC.TL_documentAttributeAudio f$2;

    public /* synthetic */ CachedMediaLayout$$ExternalSyntheticLambda2(CachedMediaLayout r1, CacheModel.FileInfo r2, TLRPC.TL_documentAttributeAudio r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CachedMediaLayout.m6472$r8$lambda$Cuw4lcuu0FOrEfEArGnJySj_mU(this.f$0, this.f$1, this.f$2);
    }
}
