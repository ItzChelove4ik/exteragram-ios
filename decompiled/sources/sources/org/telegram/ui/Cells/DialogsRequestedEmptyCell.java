package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public abstract class DialogsRequestedEmptyCell extends LinearLayout implements NotificationCenter.NotificationCenterDelegate {
    TextView buttonView;
    int currentAccount;
    BackupImageView stickerView;
    TextView subtitleView;
    TextView titleView;

    /* renamed from: org.telegram.ui.Cells.DialogsRequestedEmptyCell$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        Paint paint;
        Path path;
        final /* synthetic */ DialogsRequestedEmptyCell this$0;

        public AnonymousClass1(DialogsRequestedEmptyCell r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.path = new Path();
            Paint r32 = new Paint(1);
            this.paint = r32;
            r32.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            this.paint.setShadowLayer(AndroidUtilities.dp(1.33f), 0.0f, AndroidUtilities.dp(0.33f), 503316480);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas r3) {
            r3.drawPath(this.path, this.paint);
            super.onDraw(r3);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            super.onMeasure(r5, r6);
            this.path.rewind();
            RectF r52 = AndroidUtilities.rectTmp;
            r52.set(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), getMeasuredWidth() - AndroidUtilities.dp(12.0f), getMeasuredHeight() - AndroidUtilities.dp(12.0f));
            this.path.addRoundRect(r52, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Path.Direction.CW);
        }
    }

    public static /* synthetic */ void $r8$lambda$iWfgjPRmQV2D2Yi_aQTqBWcYoCI(DialogsRequestedEmptyCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$udLp_rGZjXlg_lFG_2u-Oy_s7B4, reason: not valid java name */
    public static /* synthetic */ void m6921$r8$lambda$udLp_rGZjXlg_lFG_2uOy_s7B4(DialogsRequestedEmptyCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public DialogsRequestedEmptyCell(Context r14) {
        super(r14);
        this.currentAccount = UserConfig.selectedAccount;
        setOrientation(1);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass1 r1 = new AnonymousClass1(this, r14);
        r1.setWillNotDraw(false);
        r1.setOrientation(1);
        r1.setPadding(AndroidUtilities.dp(32.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f));
        BackupImageView r3 = new BackupImageView(r14);
        this.stickerView = r3;
        r3.setOnClickListener(new DialogsRequestedEmptyCell$$ExternalSyntheticLambda0(this));
        updateSticker();
        r1.addView(this.stickerView, LayoutHelper.createLinear(130, 130, 49));
        TextView r32 = new TextView(r14);
        this.titleView = r32;
        r32.setGravity(17);
        this.titleView.setTextSize(1, 18.0f);
        this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.titleView.setTypeface(AndroidUtilities.bold());
        r1.addView(this.titleView, LayoutHelper.createLinear(-1, -2, 49, 0, 6, 0, 0));
        TextView r33 = new TextView(r14);
        this.subtitleView = r33;
        r33.setGravity(17);
        this.subtitleView.setTextSize(1, 14.0f);
        this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r1.addView(this.subtitleView, LayoutHelper.createLinear(-1, -2, 49, 0, 7, 0, 0));
        TextView r34 = new TextView(r14);
        this.buttonView = r34;
        r34.setGravity(17);
        this.buttonView.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{8.0f}));
        this.buttonView.setTextSize(1, 14.0f);
        this.buttonView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.buttonView.setTypeface(AndroidUtilities.bold());
        this.buttonView.setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f));
        this.buttonView.setOnClickListener(new DialogsRequestedEmptyCell$$ExternalSyntheticLambda1(this));
        r1.addView(this.buttonView, LayoutHelper.createLinear(-1, -2, 49, 0, 18, 0, 0));
        addView(r1, LayoutHelper.createLinear(-1, -2));
        set(null);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        this.stickerView.getImageReceiver().startAnimation();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        onButtonClick();
    }

    private void updateSticker() {
        TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
        if (r0 != null) goto L5;
        r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
    L5:
        TLRPC.TL_messages_stickerSet r7 = r0;
        boolean r02 = true;
        if (r7 != null) goto L8;
    L10:
        TLRPC.Document r2 = null;
    L11:
        if (r2 == null) goto L17;
        SvgHelper.SvgDrawable r6 = DocumentObject.getSvgThumb(r2.thumbs, Theme.key_windowBackgroundGray, 0.2f);
        if (r6 == null) goto L15;
        r6.overrideWidthAndHeight(512, 512);
    L15:
        this.stickerView.setImage(ImageLocation.getForDocument(r2), "130_130", "tgs", r6, r7);
        this.stickerView.getImageReceiver().setAutoRepeat(2);
        return;
    L17:
        MediaDataController r22 = MediaDataController.getInstance(this.currentAccount);
        if (r7 == null) goto L21;
        r02 = false;
    L21:
        r22.loadStickersByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME, false, r02);
        this.stickerView.getImageReceiver().clearImage();
        return;
    L8:
        if (1 >= r7.documents.size()) goto L10;
        r2 = r7.documents.get(1);
        goto L11
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.diceStickersDidLoad) goto L5;
        return;
    L5:
        if (AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME.equals((String) r3[0]) == true) goto L7;
        return;
    L7:
        if (getVisibility() != 0) goto L12;
        updateSticker();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.diceStickersDidLoad);
    }

    public abstract void onButtonClick();

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.diceStickersDidLoad);
    }

    public void set(TLRPC.RequestPeerType r3) {
        if ((r3 instanceof TLRPC.TL_requestPeerTypeBroadcast) == false) goto L6;
        this.titleView.setText(LocaleController.getString(R.string.NoSuchChannels));
        this.subtitleView.setText(LocaleController.getString(R.string.NoSuchChannelsInfo));
        this.buttonView.setVisibility(0);
        this.buttonView.setText(LocaleController.getString(R.string.CreateChannelForThis));
        return;
    L6:
        boolean r32 = r3 instanceof TLRPC.TL_requestPeerTypeChat;
        TextView r0 = this.titleView;
        if (r32 == false) goto L10;
        r0.setText(LocaleController.getString(R.string.NoSuchGroups));
        this.subtitleView.setText(LocaleController.getString(R.string.NoSuchGroupsInfo));
        this.buttonView.setVisibility(0);
        this.buttonView.setText(LocaleController.getString(R.string.CreateGroupForThis));
        return;
    L10:
        r0.setText(LocaleController.getString(R.string.NoSuchUsers));
        this.subtitleView.setText(LocaleController.getString(R.string.NoSuchUsersInfo));
        this.buttonView.setVisibility(8);
    }
}
