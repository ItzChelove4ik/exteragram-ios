package org.telegram.messenger;

import android.content.Intent;
import android.widget.RemoteViewsService;

/* loaded from: classes5.dex */
public class FeedWidgetService extends RemoteViewsService {
    public FeedWidgetService() {
    }

    @Override // android.widget.RemoteViewsService
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent r2) {
        return new FeedRemoteViewsFactory(getApplicationContext(), r2);
    }
}
