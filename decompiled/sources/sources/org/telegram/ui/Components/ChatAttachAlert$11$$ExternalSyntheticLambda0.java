package org.telegram.ui.Components;

import org.telegram.messenger.MediaController;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.ChatAttachAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$11$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ ChatAttachAlert.AnonymousClass11 f$0;
    public final /* synthetic */ MediaController.PhotoEntry f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ ChatAttachAlert$11$$ExternalSyntheticLambda0(ChatAttachAlert.AnonymousClass11 r1, MediaController.PhotoEntry r2, boolean r3, int r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        ChatAttachAlert.AnonymousClass11.$r8$lambda$8AKsV6FBSbBgxsyA4HqxMYYZQAc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Long) r7);
    }
}
