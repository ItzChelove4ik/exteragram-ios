package org.telegram.ui.Components;

import org.telegram.ui.Components.AnimatedEmojiDrawable;

/* loaded from: classes3.dex */
public final /* synthetic */ class AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable f$0;

    public /* synthetic */ AnimatedEmojiDrawable$SwapAnimatedEmojiDrawable$$ExternalSyntheticLambda0(AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
