package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda429 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Theme.OverrideWallpaperInfo f$1;
    public final /* synthetic */ TLRPC.TL_wallPaperSettings f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda429(MessagesController r1, Theme.OverrideWallpaperInfo r2, TLRPC.TL_wallPaperSettings r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        MessagesController.$r8$lambda$OYxx0Qsl7niVBz7FY7hK1zHFmx8(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
