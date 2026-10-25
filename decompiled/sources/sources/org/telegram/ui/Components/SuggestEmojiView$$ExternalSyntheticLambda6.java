package org.telegram.ui.Components;

import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.messenger.MediaDataController;

/* loaded from: classes7.dex */
public final /* synthetic */ class SuggestEmojiView$$ExternalSyntheticLambda6 implements MediaDataController.KeywordResultCallback {
    public final /* synthetic */ SuggestEmojiView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ HashSet f$3;
    public final /* synthetic */ ArrayList f$4;

    public /* synthetic */ SuggestEmojiView$$ExternalSyntheticLambda6(SuggestEmojiView r1, int r2, String r3, HashSet r4, ArrayList r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.MediaDataController.KeywordResultCallback
    public final void run(ArrayList r8, String r9) {
        SuggestEmojiView.m13146$r8$lambda$yKgcBwiyW5I4gUl2bhvDB2AsLE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
