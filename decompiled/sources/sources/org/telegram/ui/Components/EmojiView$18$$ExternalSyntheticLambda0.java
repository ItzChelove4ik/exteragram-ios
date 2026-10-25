package org.telegram.ui.Components;

import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$18$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ EmojiView.SearchRunnable f$0;

    public /* synthetic */ EmojiView$18$$ExternalSyntheticLambda0(EmojiView.SearchRunnable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.loadNext();
    }
}
