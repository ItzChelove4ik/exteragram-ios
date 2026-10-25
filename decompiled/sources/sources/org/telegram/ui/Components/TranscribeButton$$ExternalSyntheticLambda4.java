package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.RequestDelegate;

/* loaded from: classes7.dex */
public final /* synthetic */ class TranscribeButton$$ExternalSyntheticLambda4 implements MessagesStorage.IntCallback {
    public final /* synthetic */ MessageObject f$0;
    public final /* synthetic */ RequestDelegate f$1;

    public /* synthetic */ TranscribeButton$$ExternalSyntheticLambda4(MessageObject r1, RequestDelegate r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r2) {
        TranscribeButton.m13378$r8$lambda$kNGsRkppBULGalCwHXfcm26L9g(this.f$0, this.f$1, r2);
    }
}
