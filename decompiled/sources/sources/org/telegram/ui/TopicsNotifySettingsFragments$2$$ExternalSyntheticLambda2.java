package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.NotificationsSettingsActivity;
import org.telegram.ui.ProfileNotificationsActivity;
import org.telegram.ui.TopicsNotifySettingsFragments;

/* loaded from: classes6.dex */
public final /* synthetic */ class TopicsNotifySettingsFragments$2$$ExternalSyntheticLambda2 implements ProfileNotificationsActivity.ProfileNotificationsActivityDelegate {
    public final /* synthetic */ TopicsNotifySettingsFragments.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.TL_forumTopic f$1;

    public /* synthetic */ TopicsNotifySettingsFragments$2$$ExternalSyntheticLambda2(TopicsNotifySettingsFragments.AnonymousClass2 r1, TLRPC.TL_forumTopic r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ProfileNotificationsActivity.ProfileNotificationsActivityDelegate
    public final void didCreateNewException(NotificationsSettingsActivity.NotificationException r2) {
        TopicsNotifySettingsFragments.AnonymousClass2.m21011$r8$lambda$YRzvGIyjDhwky2f6aPOst0R838(this.f$0, this.f$1, r2);
    }
}
