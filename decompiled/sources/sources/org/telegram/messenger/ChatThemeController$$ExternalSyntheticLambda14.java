package org.telegram.messenger;

import android.graphics.Bitmap;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ResultCallback;

/* loaded from: classes5.dex */
public final /* synthetic */ class ChatThemeController$$ExternalSyntheticLambda14 implements ResultCallback {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ ChatThemeController$$ExternalSyntheticLambda14(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.ResultCallback
    public final void onComplete(Object r1) {
        ChatThemeController.$r8$lambda$8Na7zulZalXIe__rhLaQ2WiSe8w(this.f$0, (Bitmap) r1);
    }
}
