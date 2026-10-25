package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.MediaDataController;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda9 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ EmojiView.EmojiSearchAdapter.AnonymousClass5 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda9(EmojiView.EmojiSearchAdapter.AnonymousClass5 r1, String r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r3, String r4) {
        EmojiView.EmojiSearchAdapter.AnonymousClass5.$r8$lambda$S50ekS_VYIzDxDZu7ERVvnTyD_o(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
