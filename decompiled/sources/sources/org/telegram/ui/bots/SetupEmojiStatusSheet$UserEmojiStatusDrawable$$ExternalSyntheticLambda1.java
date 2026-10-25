package org.telegram.ui.bots;

import org.telegram.ui.bots.SetupEmojiStatusSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class SetupEmojiStatusSheet$UserEmojiStatusDrawable$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SetupEmojiStatusSheet.UserEmojiStatusDrawable f$0;

    public /* synthetic */ SetupEmojiStatusSheet$UserEmojiStatusDrawable$$ExternalSyntheticLambda1(SetupEmojiStatusSheet.UserEmojiStatusDrawable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateSelf();
    }
}
