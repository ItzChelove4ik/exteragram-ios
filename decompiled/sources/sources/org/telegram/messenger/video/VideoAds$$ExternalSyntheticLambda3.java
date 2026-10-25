package org.telegram.messenger.video;

import android.content.Context;
import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ItemOptions;

/* loaded from: classes6.dex */
public final /* synthetic */ class VideoAds$$ExternalSyntheticLambda3 implements View.OnClickListener {
    public final /* synthetic */ VideoAds f$0;
    public final /* synthetic */ ItemOptions f$1;
    public final /* synthetic */ TLRPC.TL_sponsoredMessage f$2;
    public final /* synthetic */ Context f$3;

    public /* synthetic */ VideoAds$$ExternalSyntheticLambda3(VideoAds r1, ItemOptions r2, TLRPC.TL_sponsoredMessage r3, Context r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r4) {
        VideoAds.$r8$lambda$1QGZewha_uIKm1JzxxXGiFZKI14(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
