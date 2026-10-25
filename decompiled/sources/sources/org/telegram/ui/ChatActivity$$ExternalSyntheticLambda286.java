package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ScrimOptions;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda286 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ ScrimOptions f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda286(ChatActivity r1, ArrayList r2, long r3, ScrimOptions r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r9, int r10, int r11) {
        ChatActivity.$r8$lambda$5QWx4MgxjBVURo11YdpOpKPXiPo(this.f$0, this.f$1, this.f$2, this.f$3, r9, r10, r11);
    }
}
