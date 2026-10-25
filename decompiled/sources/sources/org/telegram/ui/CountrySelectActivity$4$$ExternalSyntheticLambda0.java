package org.telegram.ui;

import org.telegram.messenger.NotificationCenter;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.CountrySelectActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class CountrySelectActivity$4$$ExternalSyntheticLambda0 implements NotificationCenter.NotificationCenterDelegate {
    public final /* synthetic */ TextSettingsCell f$0;

    public /* synthetic */ CountrySelectActivity$4$$ExternalSyntheticLambda0(TextSettingsCell r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public final void didReceivedNotification(int r1, int r2, Object[] r3) {
        CountrySelectActivity.AnonymousClass4.$r8$lambda$0DoFj1EMwzi9gkncDjaoCqlCJDY(this.f$0, r1, r2, r3);
    }
}
