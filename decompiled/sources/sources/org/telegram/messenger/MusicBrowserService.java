package org.telegram.messenger;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.Process;
import android.service.media.MediaBrowserService;
import android.widget.Toast;
import java.util.List;

/* loaded from: classes5.dex */
public class MusicBrowserService extends MediaBrowserService {
    private static final String MEDIA_ID_ROOT = "__ROOT__";

    public MusicBrowserService() {
    }

    @Override // android.service.media.MediaBrowserService, android.app.Service
    public void onCreate() {
        super.onCreate();
        ApplicationLoader.postInitApplication();
        setSessionToken(TelegramMediaSession.getInstance(this).getFrameworkSessionToken());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.service.media.MediaBrowserService
    public MediaBrowserService.BrowserRoot onGetRoot(String r2, int r3, Bundle r4) {
        if (r2 != null) goto L6;
        return null;
    L6:
        if (1000 == r3) goto L14;
        if (Process.myUid() == r3) goto L14;
        if (PackageValidator.isKnownCaller(this, r2, r3) == true) goto L14;
        return null;
    L14:
        if (TelegramMediaSession.getInstance(this).isPasscodeLocked() == false) goto L17;
        return null;
    L17:
        if ("com.android.systemui".equals(r2) == true) goto L20;
        TelegramMediaSession.getInstance(this).enablePlaybackMirroring();
    L20:
        return new MediaBrowserService.BrowserRoot(MEDIA_ID_ROOT, TelegramMediaSession.getInstance(this).buildRootHints());
    }

    @Override // android.service.media.MediaBrowserService
    public void onLoadChildren(String r3, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> r4) {
        TelegramMediaSession r0 = TelegramMediaSession.getInstance(this);
        if (r0.isPasscodeLocked() == false) goto L6;
        Toast.makeText(getApplicationContext(), LocaleController.getString(R.string.EnterYourTelegramPasscode), 1).show();
        stopSelf();
        r4.detach();
        return;
    L6:
        r4.detach();
        r0.loadBrowseChildren(r3, new MusicBrowserService$$ExternalSyntheticLambda0(r4));
    }
}
