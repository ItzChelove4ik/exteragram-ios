package org.telegram.messenger;

import android.content.Intent;
import android.widget.RemoteViewsService;

/* loaded from: classes5.dex */
public class ChatsWidgetService extends RemoteViewsService {
    public ChatsWidgetService() {
    }

    @Override // android.widget.RemoteViewsService
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent r2) {
        return new ChatsRemoteViewsFactory(getApplicationContext(), r2);
    }
}
