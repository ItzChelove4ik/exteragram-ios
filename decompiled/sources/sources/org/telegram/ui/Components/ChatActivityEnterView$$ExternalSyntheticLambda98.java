package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda98 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ TLRPC.Document f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Object f$3;
    public final /* synthetic */ MessageObject.SendAnimationData f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda98(ChatActivityEnterView r1, TLRPC.Document r2, String r3, Object r4, MessageObject.SendAnimationData r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r10, int r11, int r12) {
        ChatActivityEnterView.m9133$r8$lambda$Y9HMuUbCY7RqUrjvgMYDEdDKwY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10, r11, r12);
    }
}
