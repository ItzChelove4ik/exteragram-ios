package org.telegram.ui.iv;

import android.graphics.Bitmap;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class MediaUploadState {
    public TLRPC.Document audioDisplayDocument;
    public TLRPC.Document document;
    public int duration;
    public boolean hasSpoiler;
    public int height;
    public int invert;
    public boolean isAudio;
    public boolean isDocument;
    public boolean isVideo;
    public String localPath;
    public Bitmap localThumbBitmap;
    public int orientation;
    public TLRPC.Photo photo;
    public float progress;
    public int state;
    public int width;

    public MediaUploadState() {
        this.state = 0;
    }

    public boolean isPending() {
        if (this.state != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean isReady() {
        if (this.state == 2) goto L6;
        return false;
    L6:
        if (this.isVideo == true) goto L17;
        if (this.isAudio == true) goto L17;
        if (this.isDocument == true) goto L17;
        if (this.photo == null) goto L15;
        return true;
    L15:
        return false;
    L17:
        if (this.document == null) goto L19;
        return true;
    L19:
        return false;
    }
}
