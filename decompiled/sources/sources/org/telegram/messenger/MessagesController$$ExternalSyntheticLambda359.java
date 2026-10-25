package org.telegram.messenger;

import java.io.File;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda359 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.WallPaper f$1;
    public final /* synthetic */ TLRPC.TL_wallPaperSettings f$2;
    public final /* synthetic */ Theme.OverrideWallpaperInfo f$3;
    public final /* synthetic */ File f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda359(MessagesController r1, TLRPC.WallPaper r2, TLRPC.TL_wallPaperSettings r3, Theme.OverrideWallpaperInfo r4, File r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$5N93B8Hy518wEik0qiEDYheomco(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
