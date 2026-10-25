package com.exteragram.messenger.nowplaying.ui.components;

import com.exteragram.messenger.api.dto.NowPlayingDTO;
import com.exteragram.messenger.nowplaying.ui.components.NowPlayingCardData;

/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingCardData$Companion$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ NowPlayingCardData.Callback f$0;
    public final /* synthetic */ NowPlayingDTO f$1;

    public /* synthetic */ NowPlayingCardData$Companion$$ExternalSyntheticLambda0(NowPlayingCardData.Callback r1, NowPlayingDTO r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NowPlayingCardData.Companion.m1510$r8$lambda$CkJytQzmKuKqumf6IFQhPZcbKY(this.f$0, this.f$1);
    }
}
