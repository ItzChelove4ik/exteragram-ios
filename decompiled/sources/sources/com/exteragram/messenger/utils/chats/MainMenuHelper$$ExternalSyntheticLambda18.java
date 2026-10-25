package com.exteragram.messenger.utils.chats;

import com.exteragram.messenger.utils.chats.MainMenuHelper;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class MainMenuHelper$$ExternalSyntheticLambda18 implements Runnable {
    public final /* synthetic */ TLRPC.TL_attachMenuBot f$0;
    public final /* synthetic */ MainMenuHelper.MenuContext f$1;
    public final /* synthetic */ LaunchActivity f$2;

    public /* synthetic */ MainMenuHelper$$ExternalSyntheticLambda18(TLRPC.TL_attachMenuBot r1, MainMenuHelper.MenuContext r2, LaunchActivity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MainMenuHelper.$r8$lambda$aYhptRjLYU6aWVNMAzGjtgDV_Ec(this.f$0, this.f$1, this.f$2);
    }
}
