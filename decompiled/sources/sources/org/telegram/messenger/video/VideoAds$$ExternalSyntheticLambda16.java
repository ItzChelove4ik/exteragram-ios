package org.telegram.messenger.video;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.video.VideoAds;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes6.dex */
public final /* synthetic */ class VideoAds$$ExternalSyntheticLambda16 implements Utilities.Callback {
    public final /* synthetic */ VideoAds f$0;
    public final /* synthetic */ Bulletin f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ VideoAds.CloseDrawable f$3;
    public final /* synthetic */ long[] f$4;
    public final /* synthetic */ Runnable f$5;
    public final /* synthetic */ long[] f$6;
    public final /* synthetic */ long f$7;
    public final /* synthetic */ TLRPC.TL_sponsoredMessage f$8;

    public /* synthetic */ VideoAds$$ExternalSyntheticLambda16(VideoAds r1, Bulletin r2, boolean[] r3, VideoAds.CloseDrawable r4, long[] r5, Runnable r6, long[] r7, long r8, TLRPC.TL_sponsoredMessage r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r10;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r12) {
        VideoAds.$r8$lambda$iJNzr4cxQtKwCkMVBcwYn2ocNhw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, (Boolean) r12);
    }
}
