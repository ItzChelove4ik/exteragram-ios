package org.telegram.ui.Components;

import java.util.function.Consumer;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.EmojiPacksAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiPacksAlert$1$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ EmojiPacksAlert.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.Document f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ EmojiPacksAlert$1$$ExternalSyntheticLambda1(EmojiPacksAlert.AnonymousClass1 r1, TLRPC.Document r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object r3) {
        EmojiPacksAlert.AnonymousClass1.$r8$lambda$qfE2vkyV810tTZEbAxCPYMghhko(this.f$0, this.f$1, this.f$2, (String) r3);
    }
}
