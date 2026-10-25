package org.telegram.ui.ActionBar;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class EmojiThemes$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.WallPaper f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ EmojiThemes$$ExternalSyntheticLambda1(Utilities.Callback r1, TLRPC.WallPaper r2, int r3, int r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        EmojiThemes.$r8$lambda$VCkI0d8L2PeP2148z7K7_BYAE3Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (WallpaperBitmapHolder) r8);
    }
}
