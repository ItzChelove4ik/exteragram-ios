package org.telegram.messenger;

import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda42 implements Runnable {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$3;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$4;
    public final /* synthetic */ TranslateController.StoryKey f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda42(TranslateController r1, TL_stories.StoryItem r2, String r3, TLRPC.TL_textWithEntities r4, TLRPC.TL_textWithEntities r5, TranslateController.StoryKey r6, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TranslateController.m5033$r8$lambda$oUqzsxXadNZdVKeFo0HCv1pOf8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
