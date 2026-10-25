package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.messenger.wallpaper.WallpaperBitmapHolder;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatBackgroundDrawable$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ ChatBackgroundDrawable f$0;
    public final /* synthetic */ TLRPC.WallPaper f$1;

    public /* synthetic */ ChatBackgroundDrawable$$ExternalSyntheticLambda0(ChatBackgroundDrawable r1, TLRPC.WallPaper r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatBackgroundDrawable.m8244$r8$lambda$73PYOrGzQTEuHkfepAmdbypBb8(this.f$0, this.f$1, (WallpaperBitmapHolder) r2);
    }
}
