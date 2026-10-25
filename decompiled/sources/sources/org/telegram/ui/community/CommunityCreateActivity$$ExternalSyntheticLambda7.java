package org.telegram.ui.community;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityCreateActivity$$ExternalSyntheticLambda7 implements MessagesStorage.LongCallback {
    public final /* synthetic */ CommunityCreateActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ CommunityCreateActivity$$ExternalSyntheticLambda7(CommunityCreateActivity r1, AlertDialog r2, long r3, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r8) {
        CommunityCreateActivity.$r8$lambda$U5948WejS7b8vQyctof8ET5rXf8(this.f$0, this.f$1, this.f$2, this.f$3, r8);
    }
}
