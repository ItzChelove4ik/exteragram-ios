package org.telegram.ui.Components;

import android.view.MotionEvent;
import org.telegram.messenger.IMapsProvider;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertLocationLayout$$ExternalSyntheticLambda16 implements IMapsProvider.ITouchInterceptor {
    public final /* synthetic */ ChatAttachAlertLocationLayout f$0;

    public /* synthetic */ ChatAttachAlertLocationLayout$$ExternalSyntheticLambda16(ChatAttachAlertLocationLayout r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.IMapsProvider.ITouchInterceptor
    public final boolean onInterceptTouchEvent(MotionEvent r1, IMapsProvider.ICallableMethod r2) {
        return ChatAttachAlertLocationLayout.m9625$r8$lambda$0uKlMUgNylfi_BjWB5oRavDBQs(this.f$0, r1, r2);
    }
}
