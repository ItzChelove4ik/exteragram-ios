package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class StickerImageView extends BackupImageView implements NotificationCenter.NotificationCenterDelegate {
    int currentAccount;
    int stickerNum;
    String stickerPackName;

    public StickerImageView(Context r1, int r2) {
        super(r1);
        this.stickerPackName = AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME;
        this.currentAccount = r2;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.diceStickersDidLoad) goto L8;
        String r12 = (String) r3[0];
        if (this.stickerPackName.equals(r12) == false) goto L9;
        setSticker();
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.BackupImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setSticker();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.diceStickersDidLoad);
    }

    @Override // org.telegram.ui.Components.BackupImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.diceStickersDidLoad);
    }

    public void setSticker() {
        TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(this.stickerPackName);
        if (r0 != null) goto L5;
        r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(this.stickerPackName);
    L5:
        TLRPC.TL_messages_stickerSet r6 = r0;
        SvgHelper.SvgDrawable r02 = null;
        if (r6 == null) goto L10;
        int r1 = r6.documents.size();
        int r2 = this.stickerNum;
        if (r1 <= r2) goto L10;
        TLRPC.Document r12 = r6.documents.get(r2);
    L11:
        if (r12 == null) goto L13;
        r02 = DocumentObject.getSvgThumb(r12.thumbs, Theme.key_emptyListPlaceholder, 0.2f);
    L13:
        SvgHelper.SvgDrawable r5 = r02;
        if (r5 == null) goto L16;
        r5.overrideWidthAndHeight(512, 512);
    L16:
        if (r12 == null) goto L19;
        setImage(ImageLocation.getForDocument(r12), "130_130", "tgs", r5, r6);
        return;
    L19:
        this.imageReceiver.clearImage();
        MediaDataController r7 = MediaDataController.getInstance(this.currentAccount);
        String r03 = this.stickerPackName;
        if (r6 != null) goto L22;
        boolean r22 = true;
    L23:
        r7.loadStickersByEmojiOrName(r03, false, r22);
        return;
    L22:
        r22 = false;
    L10:
        r12 = null;
        goto L11
    }

    public void setStickerNum(int r2) {
        if (this.stickerNum == r2) goto L6;
        this.stickerNum = r2;
        setSticker();
        return;
    }

    public void setStickerPackName(String r1) {
        this.stickerPackName = r1;
    }
}
