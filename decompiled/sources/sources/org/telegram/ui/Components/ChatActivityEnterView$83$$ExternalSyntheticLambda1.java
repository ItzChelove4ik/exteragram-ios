package org.telegram.ui.Components;

import android.view.View;
import org.telegram.messenger.MediaController;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ChatActivityEnterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$83$$ExternalSyntheticLambda1 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivityEnterView.AnonymousClass83 f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ Object f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Object f$4;
    public final /* synthetic */ MediaController.PhotoEntry f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ ChatActivityEnterView$83$$ExternalSyntheticLambda1(ChatActivityEnterView.AnonymousClass83 r1, View r2, Object r3, String r4, Object r5, MediaController.PhotoEntry r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r11, int r12, int r13) {
        ChatActivityEnterView.AnonymousClass83.$r8$lambda$lfOgfdmK0lnwOx9y5E4snys7NmA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12, r13);
    }
}
