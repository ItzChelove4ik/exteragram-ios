package org.telegram.ui.Components;

import android.net.Uri;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda102 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Uri f$2;
    public final /* synthetic */ InputContentInfoCompat f$3;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda102(ChatActivityEnterView r1, String r2, Uri r3, InputContentInfoCompat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r8, int r9, int r10) {
        ChatActivityEnterView.m9153$r8$lambda$uszdWpSZLpfDLSNTp3UsqBnpZs(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9, r10);
    }
}
