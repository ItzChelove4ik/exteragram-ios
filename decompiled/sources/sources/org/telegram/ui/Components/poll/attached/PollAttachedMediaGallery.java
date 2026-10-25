package org.telegram.ui.Components.poll.attached;

import android.graphics.Canvas;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.ui.Components.poll.PollAttachedMedia;

/* loaded from: classes7.dex */
public class PollAttachedMediaGallery extends PollAttachedMedia {
    public final MediaController.PhotoEntry photoEntry;
    public final SendMessagesHelper.SendingMediaInfo sendingMediaInfo;

    public PollAttachedMediaGallery(SendMessagesHelper.SendingMediaInfo r2) {
        this.sendingMediaInfo = r2;
        this.photoEntry = r2.originalPhotoEntry;
        this.imageReceiver.setRoundRadius(AndroidUtilities.dp(7.0f));
        setupImageReceiver(this.imageReceiver);
    }

    private void setupImageReceiver(ImageReceiver r8) {
        r8.setOrientation(0, true);
        MediaController.PhotoEntry r0 = this.photoEntry;
        String r2 = r0.coverPath;
        if (r2 == null) goto L6;
        ImageLocation r7 = ImageLocation.getForPath(r2);
    L5:
        ImageLocation r1 = r7;
    L18:
        if (r1 == null) goto L21;
        r8.setImage(r1, null, null, null, null, 0);
        return;
    L21:
        r8.clearImage();
        return;
    L6:
        String r22 = r0.thumbPath;
        if (r22 == null) goto L10;
        r7 = ImageLocation.getForPath(r22);
        goto L5
    L10:
        if (r0.path != null) goto L12;
        r8.clearImage();
        r7 = null;
        goto L5
    L12:
        if (r0.isVideo == true) goto L14;
    L16:
        ImageLocation r02 = ImageLocation.getForPath("thumb://" + this.photoEntry.imageId + ":" + this.photoEntry.path);
        MediaController.PhotoEntry r72 = this.photoEntry;
        r8.setOrientation(r72.orientation, r72.invert, true);
        r1 = r02;
        goto L18
    L14:
        if (r0.isLivePhoto() == true) goto L16;
        r7 = ImageLocation.getForPath("vthumb://" + this.photoEntry.imageId + ":" + this.photoEntry.path);
        goto L5
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void draw(Canvas r3, int r4, int r5) {
        this.imageReceiver.setImageCoords(0.0f, 0.0f, r4, r5);
        this.imageReceiver.draw(r3);
    }
}
