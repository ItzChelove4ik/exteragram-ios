package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.poll.attached.PollAttachedMediaLocation;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertPollLayout$$ExternalSyntheticLambda20 implements Utilities.CallbackReturn {
    public final /* synthetic */ PollAttachedMediaLocation f$0;

    public /* synthetic */ ChatAttachAlertPollLayout$$ExternalSyntheticLambda20(PollAttachedMediaLocation r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r1) {
        return this.f$0.createMessagePreviewDrawable((View) r1);
    }
}
