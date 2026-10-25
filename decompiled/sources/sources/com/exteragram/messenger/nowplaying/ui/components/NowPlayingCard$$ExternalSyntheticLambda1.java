package com.exteragram.messenger.nowplaying.ui.components;

import android.view.View;
import android.widget.FrameLayout;
import com.exteragram.messenger.api.dto.NowPlayingDTO;

/* loaded from: classes4.dex */
public final /* synthetic */ class NowPlayingCard$$ExternalSyntheticLambda1 implements View.OnLongClickListener {
    public final /* synthetic */ NowPlayingDTO f$0;
    public final /* synthetic */ NowPlayingCard f$1;
    public final /* synthetic */ FrameLayout f$2;
    public final /* synthetic */ NowPlayingCardData f$3;

    public /* synthetic */ NowPlayingCard$$ExternalSyntheticLambda1(NowPlayingDTO r1, NowPlayingCard r2, FrameLayout r3, NowPlayingCardData r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View r4) {
        return NowPlayingCard.$r8$lambda$1BAf14SAIk8s4HkHdGaX6glplHA(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
