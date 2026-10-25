package org.telegram.messenger.audioinfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import java.io.File;
import org.mvel2.MVEL;
import org.telegram.messenger.FileLog;

/* loaded from: classes5.dex */
public class OtherAudioInfo extends AudioInfo {
    public boolean failed;
    private final MediaMetadataRetriever r;

    public OtherAudioInfo(File r5) {
        MediaMetadataRetriever r0 = new MediaMetadataRetriever();
        this.r = r0;
        r0.setDataSource(r5.getAbsolutePath());     // Catch: Exception -> L6
        this.brand = "OTHER";     // Catch: Exception -> L6
        this.version = MVEL.VERSION_SUB;     // Catch: Exception -> L6
        this.duration = getLong(9);     // Catch: Exception -> L6
        this.title = getString(7);     // Catch: Exception -> L6
        this.artist = getString(2);     // Catch: Exception -> L6
        this.albumArtist = getString(13);     // Catch: Exception -> L6
        this.album = getString(1);     // Catch: Exception -> L6
        this.year = getShort(8);     // Catch: Exception -> L6
        this.genre = getString(6);     // Catch: Exception -> L6
        this.track = getShort(0);     // Catch: Exception -> L6
        this.tracks = getShort(10);     // Catch: Exception -> L6
        this.disc = getShort(14);     // Catch: Exception -> L6
        this.composer = getString(4);     // Catch: Exception -> L6
        byte[] r02 = r0.getEmbeddedPicture();     // Catch: Exception -> L6
        if (r02 == null) goto L9;
        this.cover = BitmapFactory.decodeByteArray(r02, 0, r02.length);     // Catch: Exception -> L6
    L9:
        if (this.cover != null) goto L11;
    L24:
        MediaMetadataRetriever r4 = this.r;     // Catch: Exception -> L21
        if (r4 == null) goto L27;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r4);     // Catch: Exception -> L21
        return;
    L27:
        return;
    L21:
        e = move-exception;
        FileLog.e(e);
        return;
    L11:
        float r52 = Math.max(r5.getWidth(), this.cover.getHeight()) / 120.0f;
        Bitmap r2 = this.cover;
        if (r52 <= 0.0f) goto L14;
        this.smallCover = Bitmap.createScaledBitmap(r2, (int) (r2.getWidth() / r52), (int) (this.cover.getHeight() / r52), true);     // Catch: Exception -> L6
        goto L24
    L14:
        this.smallCover = r2;     // Catch: Exception -> L6
    L6:
        e = move-exception;
        this.failed = true;
        FileLog.e(e);
        goto L24
    }

    private long getLong(int r1) {
        return Long.parseLong(this.r.extractMetadata(r1));
    L4:
        return 0;
    }

    private short getShort(int r1) {
        return Short.parseShort(this.r.extractMetadata(r1));
    L4:
        return 0;
    }

    private String getString(int r1) {
        return this.r.extractMetadata(r1);
    L4:
        return null;
    }
}
