package org.telegram.ui;

import org.telegram.messenger.CacheByChatsController;
import org.telegram.ui.KeepMediaPopupView;

/* loaded from: classes6.dex */
public final /* synthetic */ class CacheChatsExceptionsFragment$$ExternalSyntheticLambda5 implements KeepMediaPopupView.Callback {
    public final /* synthetic */ CacheChatsExceptionsFragment f$0;
    public final /* synthetic */ CacheByChatsController.KeepMediaException f$1;

    public /* synthetic */ CacheChatsExceptionsFragment$$ExternalSyntheticLambda5(CacheChatsExceptionsFragment r1, CacheByChatsController.KeepMediaException r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.KeepMediaPopupView.Callback
    public final void onKeepMediaChange(int r2, int r3) {
        CacheChatsExceptionsFragment.$r8$lambda$Lg9sMdhr8_NNICx4ECdSUuUFKU0(this.f$0, this.f$1, r2, r3);
    }
}
