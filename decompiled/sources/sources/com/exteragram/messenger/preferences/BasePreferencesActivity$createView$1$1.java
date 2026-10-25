package com.exteragram.messenger.preferences;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.ActionBar.ActionBar;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/preferences/BasePreferencesActivity$createView$1$1", "Lorg/telegram/ui/ActionBar/ActionBar$ActionBarMenuOnItemClick;", "onItemClick", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BasePreferencesActivity$createView$1$1 extends ActionBar.ActionBarMenuOnItemClick {
    final /* synthetic */ BasePreferencesActivity this$0;

    public BasePreferencesActivity$createView$1$1(BasePreferencesActivity r1) {
        this.this$0 = r1;
    }

    @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
    public void onItemClick(int r2) {
        if (r2 != (-1)) goto L6;
        this.this$0.finishFragment();
        return;
    }
}
