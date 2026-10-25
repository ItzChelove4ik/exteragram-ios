package com.exteragram.messenger.ai.ui.activities;

import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/exteragram/messenger/ai/ui/activities/ServicesActivity$statusPoll$1", "Ljava/lang/Runnable;", "run", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ServicesActivity$statusPoll$1 implements Runnable {
    final /* synthetic */ ServicesActivity this$0;

    public ServicesActivity$statusPoll$1(ServicesActivity r1) {
        this.this$0 = r1;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.this$0.getContext() == null) goto L10;
        OnDeviceAvailability.refresh(new ServicesActivity$statusPoll$1$$ExternalSyntheticLambda0(this.this$0));
        if (OnDeviceAvailability.isDownloadInProgress() == false) goto L9;
        AndroidUtilities.runOnUIThread(this, 5000);
        return;
    L9:
        return;
    }
}
