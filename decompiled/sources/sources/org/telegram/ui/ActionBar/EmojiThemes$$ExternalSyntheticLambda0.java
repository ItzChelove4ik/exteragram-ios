package org.telegram.ui.ActionBar;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.tgnet.ResultCallback;

/* loaded from: classes6.dex */
public final /* synthetic */ class EmojiThemes$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ ResultCallback f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ EmojiThemes$$ExternalSyntheticLambda0(ResultCallback r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        EmojiThemes.$r8$lambda$nI0EPMEf3fJW0kk3pTsEpmqvnGU(this.f$0, this.f$1, (WallpaperBitmapHolder) r4);
    }
}
