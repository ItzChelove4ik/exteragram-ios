package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ SelectAnimatedEmojiDialog f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ AnimatedEmojiSpan f$2;
    public final /* synthetic */ TLRPC.Document f$3;
    public final /* synthetic */ SelectAnimatedEmojiDialog.ImageViewEmoji f$4;

    public /* synthetic */ SelectAnimatedEmojiDialog$$ExternalSyntheticLambda8(SelectAnimatedEmojiDialog r1, View r2, AnimatedEmojiSpan r3, TLRPC.Document r4, SelectAnimatedEmojiDialog.ImageViewEmoji r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SelectAnimatedEmojiDialog.m18618$r8$lambda$XuMii2UOgsLdJZe_8ub1wpHk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
