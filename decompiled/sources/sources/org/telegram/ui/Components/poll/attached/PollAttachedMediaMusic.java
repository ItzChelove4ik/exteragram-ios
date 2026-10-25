package org.telegram.ui.Components.poll.attached;

import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.poll.PollAttachedMedia;

/* loaded from: classes7.dex */
public class PollAttachedMediaMusic extends PollAttachedMedia {
    public final MessageObject messageObject;
    private final RadialProgress2 radialProgress;

    public PollAttachedMediaMusic(MessageObject r7) {
        this.messageObject = r7;
        RadialProgress2 r0 = new RadialProgress2(null);
        this.radialProgress = r0;
        TLRPC.Document r6 = r7.getDocument();
        if (MessageObject.isDocumentHasThumb(r6) == false) goto L5;
        TLRPC.PhotoSize r1 = FileLoader.getClosestPhotoSizeWithSize(r6.thumbs, AndroidUtilities.dp(22.0f), true, null, false);
        r0.setImageOverlay(FileLoader.getClosestPhotoSizeWithSize(r6.thumbs, AndroidUtilities.dp(44.0f), true, r1, true), r1, r6, r7);
    L9:
        r0.setColorKeys(Theme.key_chat_inLoader, Theme.key_chat_inLoaderSelected, Theme.key_chat_inMediaIcon, Theme.key_chat_inMediaIconSelected);
        return;
    L5:
        String r62 = MessageObject.getArtworkUrl(r6, true);
        if (TextUtils.isEmpty(r62) == true) goto L8;
        r0.setImageOverlay(r62);
        goto L9
    L8:
        r0.setImageOverlay(null, null, null);
        goto L9
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void attach(View r2) {
        super.attach(r2);
        this.radialProgress.setParent(r2);
        this.radialProgress.onAttachedToWindow();
        this.radialProgress.setIcon(0, false, false);
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void detach() {
        super.detach();
        this.radialProgress.onDetachedFromWindow();
    }

    @Override // org.telegram.ui.Components.poll.PollAttachedMedia
    public void draw(Canvas r3, int r4, int r5) {
        this.radialProgress.setCircleRadius(r4 / 2);
        this.radialProgress.setProgressRect(0, 0, r4, r5);
        this.radialProgress.draw(r3);
    }
}
