package org.telegram.messenger;

import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda46 implements LanguageDetector.StringCallback {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ TranslateController.StoryKey f$2;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda46(TranslateController r1, TL_stories.StoryItem r2, TranslateController.StoryKey r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.LanguageDetector.StringCallback
    public final void run(String r3) {
        TranslateController.m5020$r8$lambda$1zcgcFiS5FyukDzea4SqrmSofE(this.f$0, this.f$1, this.f$2, r3);
    }
}
