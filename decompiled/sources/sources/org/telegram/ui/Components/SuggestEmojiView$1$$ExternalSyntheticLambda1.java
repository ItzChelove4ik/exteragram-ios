package org.telegram.ui.Components;

import java.util.function.Consumer;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.SuggestEmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class SuggestEmojiView$1$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLRPC.Document f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ SuggestEmojiView$1$$ExternalSyntheticLambda1(BaseFragment r1, TLRPC.Document r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object r3) {
        SuggestEmojiView.AnonymousClass1.$r8$lambda$3J6a49kUBlx1tYi9mLCCBJ0cROM(this.f$0, this.f$1, this.f$2, (String) r3);
    }
}
