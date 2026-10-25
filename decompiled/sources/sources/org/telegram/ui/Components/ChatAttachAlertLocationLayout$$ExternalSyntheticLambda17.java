package org.telegram.ui.Components;

import android.view.MotionEvent;
import org.telegram.messenger.IMapsProvider;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertLocationLayout$$ExternalSyntheticLambda17 implements IMapsProvider.ITouchInterceptor {
    public final /* synthetic */ ChatAttachAlertLocationLayout f$0;

    public /* synthetic */ ChatAttachAlertLocationLayout$$ExternalSyntheticLambda17(ChatAttachAlertLocationLayout r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.IMapsProvider.ITouchInterceptor
    public final boolean onInterceptTouchEvent(MotionEvent r1, IMapsProvider.ICallableMethod r2) {
        return ChatAttachAlertLocationLayout.m9641$r8$lambda$zLtFgSlCW52Ht8XOjF2FgI43kk(this.f$0, r1, r2);
    }
}
