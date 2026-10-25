package org.telegram.ui;

import androidx.core.util.Consumer;
import java.util.List;
import org.telegram.ui.Components.ReactedUsersListView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda422 implements Consumer {
    public final /* synthetic */ ReactedUsersListView f$0;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda422(ReactedUsersListView r1) {
        this.f$0 = r1;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r1) {
        this.f$0.setSeenUsers((List) r1);
    }
}
