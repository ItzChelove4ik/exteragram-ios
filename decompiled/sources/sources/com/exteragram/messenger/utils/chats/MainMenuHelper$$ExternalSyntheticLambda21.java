package com.exteragram.messenger.utils.chats;

import com.exteragram.messenger.utils.chats.MainMenuHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class MainMenuHelper$$ExternalSyntheticLambda21 implements Utilities.Callback {
    public final /* synthetic */ MainMenuHelper.MenuContext f$0;
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$1;
    public final /* synthetic */ LaunchActivity f$2;

    public /* synthetic */ MainMenuHelper$$ExternalSyntheticLambda21(MainMenuHelper.MenuContext r1, TLRPC.TL_attachMenuBot r2, LaunchActivity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        MainMenuHelper.m1780$r8$lambda$DjPIIdSukBvUFDTjUNeTJfx0YY(this.f$0, this.f$1, this.f$2, (Boolean) r3);
    }
}
