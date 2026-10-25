package org.telegram.ui;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.telegram.messenger.MediaDataController;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$$ExternalSyntheticLambda33 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ LinkedHashSet f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ SelectAnimatedEmojiDialog$$ExternalSyntheticLambda33(LinkedHashSet r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r2, String r3) {
        SelectAnimatedEmojiDialog.$r8$lambda$oE0xTFpVIBp_a965tQFMs2jVRFo(this.f$0, this.f$1, r2, r3);
    }
}
