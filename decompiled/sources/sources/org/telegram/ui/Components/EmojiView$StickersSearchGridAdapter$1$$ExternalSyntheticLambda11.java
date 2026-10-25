package org.telegram.ui.Components;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.MediaDataController;
import org.telegram.ui.Components.EmojiView;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda11 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ EmojiView.StickersSearchGridAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ HashMap f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda11(EmojiView.StickersSearchGridAdapter.AnonymousClass1 r1, HashMap r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r3, String r4) {
        EmojiView.StickersSearchGridAdapter.AnonymousClass1.$r8$lambda$2Pt2ytVABj67HTy51djyvKFDeDE(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
