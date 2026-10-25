package org.telegram.ui.Components;

import java.util.ArrayList;
import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.Components.DialogsBotsAdapter;

/* loaded from: classes7.dex */
public final /* synthetic */ class DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ DialogsBotsAdapter.PopularBots f$0;
    public final /* synthetic */ MessagesStorage f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ DialogsBotsAdapter$PopularBots$$ExternalSyntheticLambda4(DialogsBotsAdapter.PopularBots r1, MessagesStorage r2, ArrayList r3, long r4, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DialogsBotsAdapter.PopularBots.$r8$lambda$MXbOMFvfPy5pwYQVLaREmjMkMwE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
