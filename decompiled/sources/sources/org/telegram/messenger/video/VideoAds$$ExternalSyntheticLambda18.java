package org.telegram.messenger.video;

import android.content.Context;
import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.video.VideoAds;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes6.dex */
public final /* synthetic */ class VideoAds$$ExternalSyntheticLambda18 implements View.OnClickListener {
    public final /* synthetic */ VideoAds f$0;
    public final /* synthetic */ Bulletin f$1;
    public final /* synthetic */ TLRPC.TL_sponsoredMessage f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ Theme.ResourcesProvider f$4;
    public final /* synthetic */ VideoAds.AdLayout f$5;
    public final /* synthetic */ Utilities.Callback f$6;

    public /* synthetic */ VideoAds$$ExternalSyntheticLambda18(VideoAds r1, Bulletin r2, TLRPC.TL_sponsoredMessage r3, Context r4, Theme.ResourcesProvider r5, VideoAds.AdLayout r6, Utilities.Callback r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r9) {
        VideoAds.m5233$r8$lambda$yEh0OdQ5_bZK7ItmxhnVBWxPds(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r9);
    }
}
