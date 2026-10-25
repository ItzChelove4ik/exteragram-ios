package org.telegram.ui;

import android.content.Intent;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda122 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda122(ChatActivity r1, Intent r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        ChatActivity.$r8$lambda$NELIvYSf5YavrBhFKPXuTZWjR_g(this.f$0, this.f$1, r2, r3, r4);
    }
}
