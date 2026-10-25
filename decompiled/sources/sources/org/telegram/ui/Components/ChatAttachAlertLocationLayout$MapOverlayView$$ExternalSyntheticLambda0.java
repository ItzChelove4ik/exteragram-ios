package org.telegram.ui.Components;

import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ChatAttachAlertLocationLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertLocationLayout$MapOverlayView$$ExternalSyntheticLambda0 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatAttachAlertLocationLayout.MapOverlayView f$0;
    public final /* synthetic */ ChatAttachAlertLocationLayout.VenueLocation f$1;

    public /* synthetic */ ChatAttachAlertLocationLayout$MapOverlayView$$ExternalSyntheticLambda0(ChatAttachAlertLocationLayout.MapOverlayView r1, ChatAttachAlertLocationLayout.VenueLocation r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        ChatAttachAlertLocationLayout.MapOverlayView.m9681$r8$lambda$A6mSoeXDH8LyXuPa9k8WWUTOpU(this.f$0, this.f$1, r2, r3, r4);
    }
}
