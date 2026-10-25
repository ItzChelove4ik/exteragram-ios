package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ThemePreviewActivity;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class WallpapersListActivity$1$$ExternalSyntheticLambda0 implements ThemePreviewActivity.WallpaperActivityDelegate {
    public final /* synthetic */ WallpapersListActivity.AnonymousClass1 f$0;

    public /* synthetic */ WallpapersListActivity$1$$ExternalSyntheticLambda0(WallpapersListActivity.AnonymousClass1 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ThemePreviewActivity.WallpaperActivityDelegate
    public final void didSetNewBackground(TLRPC.WallPaper r1) {
        WallpapersListActivity.AnonymousClass1.$r8$lambda$AsS4yYT_tiugaH8ccTih_PQMTLg(this.f$0, r1);
    }
}
