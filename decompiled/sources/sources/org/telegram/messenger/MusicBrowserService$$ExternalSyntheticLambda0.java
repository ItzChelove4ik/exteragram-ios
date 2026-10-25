package org.telegram.messenger;

import android.service.media.MediaBrowserService;
import java.util.List;
import org.telegram.messenger.TelegramMediaSession;

/* loaded from: classes5.dex */
public final /* synthetic */ class MusicBrowserService$$ExternalSyntheticLambda0 implements TelegramMediaSession.BrowseChildrenCallback {
    public final /* synthetic */ MediaBrowserService.Result f$0;

    public /* synthetic */ MusicBrowserService$$ExternalSyntheticLambda0(MediaBrowserService.Result r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.TelegramMediaSession.BrowseChildrenCallback
    public final void onResult(List r1) {
        this.f$0.sendResult(r1);
    }
}
