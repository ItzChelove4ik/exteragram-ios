package org.telegram.messenger.video;

import android.content.Context;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ItemOptions;

/* loaded from: classes6.dex */
public final /* synthetic */ class VideoAds$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ VideoAds f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ TLRPC.TL_sponsoredMessage f$2;
    public final /* synthetic */ ItemOptions f$3;

    public /* synthetic */ VideoAds$$ExternalSyntheticLambda10(VideoAds r1, Context r2, TLRPC.TL_sponsoredMessage r3, ItemOptions r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VideoAds.m5232$r8$lambda$kgwfkmGMY1P8mPtgHmFW64sxeA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
