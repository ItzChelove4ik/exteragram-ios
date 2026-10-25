package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Storage.CacheModel;

/* loaded from: classes6.dex */
public final /* synthetic */ class CachedMediaLayout$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ CachedMediaLayout f$0;
    public final /* synthetic */ CacheModel.FileInfo f$1;
    public final /* synthetic */ TLRPC.TL_documentAttributeAudio f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ CachedMediaLayout$$ExternalSyntheticLambda3(CachedMediaLayout r1, CacheModel.FileInfo r2, TLRPC.TL_documentAttributeAudio r3, String r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CachedMediaLayout.$r8$lambda$xWlSN0lRTgJbuUKXGxt_wjfKHI0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
