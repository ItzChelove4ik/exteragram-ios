package org.telegram.messenger;

import android.content.Intent;
import android.widget.RemoteViewsService;

/* loaded from: classes5.dex */
public class ContactsWidgetService extends RemoteViewsService {
    public ContactsWidgetService() {
    }

    @Override // android.widget.RemoteViewsService
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent r2) {
        return new ContactsRemoteViewsFactory(getApplicationContext(), r2);
    }
}
