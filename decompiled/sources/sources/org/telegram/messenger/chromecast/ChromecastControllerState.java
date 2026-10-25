package org.telegram.messenger.chromecast;

import android.text.TextUtils;
import java.io.File;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.chromecast.ChromecastController;

/* loaded from: classes5.dex */
class ChromecastControllerState {
    private ChromecastController.RemoteMediaClientHandler client;
    private ChromecastMediaVariations media;
    private ChromecastFileServer server;

    public ChromecastControllerState() {
    }

    private void addToFileServer(ChromecastMediaVariations r4) {
        if (this.server != null) goto L5;
        this.server = new ChromecastFileServer();
    L5:
        int r0 = 0;
    L7:
        if (r0 >= r4.getVariationsCount()) goto L9;
        this.server.addFileToCast(r4.getVariation(r0));
        r0 = r0 + 1;
        goto L7
    }

    private void removeFromFileServer(ChromecastMediaVariations r4) {
        if (this.server == null) goto L9;
        int r0 = 0;
    L7:
        if (r0 >= r4.getVariationsCount()) goto L11;
        this.server.removeFileFromCast(r4.getVariation(r0));
        r0 = r0 + 1;
        goto L7
    L11:
        return;
    }

    public ChromecastController.RemoteMediaClientHandler getClient() {
        return this.client;
    }

    public ChromecastMediaVariations getMedia() {
        return this.media;
    }

    public void setClient(ChromecastController.RemoteMediaClientHandler r3) {
        ChromecastMediaVariations r0 = this.media;
        if (r0 == null) goto L9;
        if (this.client != null) goto L9;
        if (r3 == null) goto L9;
        addToFileServer(r0);
    L9:
        if (this.client == null) goto L14;
        ChromecastMediaVariations r02 = this.media;
        if (r02 == null) goto L14;
        if (r3 != null) goto L14;
        removeFromFileServer(r02);
    L14:
        ChromecastController.RemoteMediaClientHandler r03 = this.client;
        if (r03 == null) goto L17;
        r03.unregister();
    L17:
        if (r3 == null) goto L21;
        r3.register();
        ChromecastMediaVariations r04 = this.media;
        if (r04 == null) goto L21;
        r3.load(r04);
    L21:
        this.client = r3;
    }

    public String setCoverFile(File r4) {
        if (r4 == null) goto L11;
        ChromecastFileServer r0 = this.server;
        if (r0 == null) goto L11;
        if (r0.getCoverFile() == null) goto L11;
        if (TextUtils.equals(this.server.getCoverFile().getAbsolutePath(), r4.getAbsolutePath()) == false) goto L11;
        return this.server.getCoverPath();
    L11:
        String r02 = "/file" + Utilities.fastRandom.nextLong();
        if (this.server != null) goto L14;
        this.server = new ChromecastFileServer();
    L14:
        this.server.setCoverFile(r02, r4);
        return r02;
    }

    public void setMedia(ChromecastMediaVariations r3) {
        if (this.client == null) goto L7;
        if (r3 == null) goto L7;
        addToFileServer(r3);
    L7:
        if (this.client == null) goto L11;
        ChromecastMediaVariations r0 = this.media;
        if (r0 == null) goto L11;
        removeFromFileServer(r0);
    L11:
        if (r3 != null) goto L13;
    L19:
        ChromecastController.RemoteMediaClientHandler r02 = this.client;
        if (r02 == null) goto L23;
        if (r3 == null) goto L23;
        r02.load(r3);
    L23:
        this.media = r3;
        return;
    L13:
        if (r3.getVariationsCount() <= 0) goto L19;
        if (r3.getVariation(0).mimeType.startsWith("audio/") == true) goto L19;
        ChromecastFileServer r03 = this.server;
        if (r03 == null) goto L19;
        r03.setCoverFile(null, null);
        goto L19
    }
}
