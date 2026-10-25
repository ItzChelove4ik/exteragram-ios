package org.telegram.ui.Components.poll.attached;

import android.graphics.Canvas;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.poll.PollAttachedMedia;

/* loaded from: classes7.dex */
public class PollAttachedMediaSticker extends PollAttachedMedia {
    public final boolean isEmoji;
    public final Object parent;
    public final TLRPC.Document sticker;

    public PollAttachedMediaSticker(TLRPC.Document r1, Object r2) {
        this.sticker = r1;
        this.parent = r2;
        this.isEmoji = MessageObject.isAnimatedEmoji(r1);
        setupImageReceiver(this.imageReceiver);
    }

    private void setupImageReceiver(ImageReceiver r14) {
        if (MessageObject.isStickerDocument(this.sticker) == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        MessageObject.isAnimatedStickerDocument(this.sticker, true);
        SvgHelper.SvgDrawable r7 = DocumentObject.getSvgThumb(this.sticker, Theme.key_chat_serviceBackground, 1.0f);
        ImageLocation r5 = ImageLocation.getForDocument(this.sticker);
        long r8 = this.sticker.size;
        if (r0 == false) goto L13;
        String r02 = "webp";
    L14:
        r14.setImage(r5, "38_38", r7, r8, r02, this.parent, 0);
        return;
    L13:
        r02 = null;
        goto L14
    L5:
        if (MessageObject.isVideoSticker(this.sticker) == true) goto L8;
        r0 = false;
        goto L9
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void draw(Canvas r3, int r4, int r5) {
        this.imageReceiver.setImageCoords(0.0f, 0.0f, r4, r5);
        this.imageReceiver.draw(r3);
    }
}
