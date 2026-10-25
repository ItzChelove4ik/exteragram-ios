package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ThemeDelegate$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ChatActivity.ThemeDelegate f$0;
    public final /* synthetic */ EmojiThemes f$1;
    public final /* synthetic */ TLRPC.WallPaper f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ ChatActivity f$4;

    public /* synthetic */ ChatActivity$ThemeDelegate$$ExternalSyntheticLambda1(ChatActivity.ThemeDelegate r1, EmojiThemes r2, TLRPC.WallPaper r3, boolean r4, ChatActivity r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.ThemeDelegate.$r8$lambda$xlzBSLYhGqpYJ3dlHDMfPJM3BYA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
