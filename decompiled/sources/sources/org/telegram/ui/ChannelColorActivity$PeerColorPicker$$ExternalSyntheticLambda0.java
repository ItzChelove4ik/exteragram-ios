package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ChannelColorActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelColorActivity$PeerColorPicker$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ ChannelColorActivity.PeerColorPicker f$0;
    public final /* synthetic */ MessagesController.PeerColors f$1;

    public /* synthetic */ ChannelColorActivity$PeerColorPicker$$ExternalSyntheticLambda0(ChannelColorActivity.PeerColorPicker r1, MessagesController.PeerColors r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChannelColorActivity.PeerColorPicker.$r8$lambda$Wy4Z2VO8Q7bVlGLbZPl2_5cIkNs(this.f$0, this.f$1, (View) r2);
    }
}
