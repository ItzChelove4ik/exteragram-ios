package org.telegram.messenger;

import org.telegram.messenger.TranslateController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda48 implements RequestDelegate {
    public final /* synthetic */ TranslateController f$0;
    public final /* synthetic */ TL_stories.StoryItem f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TranslateController.StoryKey f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ TLRPC.TL_textWithEntities f$5;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda48(TranslateController r1, TL_stories.StoryItem r2, String r3, TranslateController.StoryKey r4, Runnable r5, TLRPC.TL_textWithEntities r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        TranslateController.m5019$r8$lambda$1TqvnokLtbRgETYlacWVFPpZ8c(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
