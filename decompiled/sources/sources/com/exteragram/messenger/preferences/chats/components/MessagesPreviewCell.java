package com.exteragram.messenger.preferences.chats.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import androidx.mediarouter.media.GlobalMediaRouter;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.BackgroundGradientDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class MessagesPreviewCell extends LinearLayout implements CustomPreferenceCell {
    private Drawable backgroundDrawable;
    private BackgroundGradientDrawable.Disposable backgroundGradientDisposable;
    private final Runnable cancelProgress;
    private ChatMessageCell[] cells;
    private final MessageObject[] messageObjects;
    private final Drawable monetBackgroundDrawable;
    private Drawable oldBackgroundDrawable;
    private BackgroundGradientDrawable.Disposable oldBackgroundGradientDisposable;
    private final INavigationLayout parentLayout;
    private int progress;
    private final Drawable shadowDrawable;
    private final int type;

    /* renamed from: com.exteragram.messenger.preferences.chats.components.MessagesPreviewCell$1, reason: invalid class name */
    public class AnonymousClass1 extends ChatMessageCell {
        final /* synthetic */ MessagesPreviewCell this$0;
        final /* synthetic */ int val$type;

        public AnonymousClass1(MessagesPreviewCell r1, Context r2, int r3, int r4) {
            this.this$0 = r1;
            this.val$type = r4;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public boolean checkNeedDrawShareButton(MessageObject r3) {
            if (r3 == null) goto L9;
            if (r3.isOutOwner() == true) goto L9;
            if (this.val$type != 1) goto L9;
            return true;
        L9:
            return super.checkNeedDrawShareButton(r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (getAvatarImage() != null) goto L5;
        L7:
            super.dispatchDraw(r6);
            return;
        L5:
            if (getAvatarImage().getImageHeight() == 0.0f) goto L7;
            getAvatarImage().setImageCoords(getAvatarImage().getImageX(), (getMeasuredHeight() - getAvatarImage().getImageHeight()) - AndroidUtilities.dp(4.0f), getAvatarImage().getImageWidth(), getAvatarImage().getImageHeight());
            getAvatarImage().setRoundRadius(ExteraConfig.getAvatarCorners(getAvatarImage().getImageHeight(), true));
            drawAvatarWithOnlineStatus(r6, getAvatarImage());
            goto L7
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public boolean isUserOnline(TLRPC.User r3) {
            if (this.val$type != 1) goto L6;
            return true;
        L6:
            return super.isUserOnline(r3);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public boolean shouldHideShareButton(MessageObject r3, boolean r4) {
            if (r3 == null) goto L10;
            if (r3.isOutOwner() == true) goto L10;
            if (this.val$type != 1) goto L10;
            return ExteraConfig.getHideShareButton();
        L10:
            return super.shouldHideShareButton(r3, r4);
        }
    }

    /* renamed from: com.exteragram.messenger.preferences.chats.components.MessagesPreviewCell$2, reason: invalid class name */
    public class AnonymousClass2 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ MessagesPreviewCell this$0;

        public AnonymousClass2(MessagesPreviewCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return true;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public void didPressReplyMessage(ChatMessageCell r1, int r2, float r3, float r4, boolean r5) {
            MessagesPreviewCell.m1700$$Nest$fputprogress(this.this$0, 0);
            r1.invalidate();
            AndroidUtilities.cancelRunOnUIThread(MessagesPreviewCell.m1698$$Nest$fgetcancelProgress(this.this$0));
            AndroidUtilities.runOnUIThread(MessagesPreviewCell.m1698$$Nest$fgetcancelProgress(this.this$0), 5000);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean isProgressLoading(ChatMessageCell r1, int r2) {
            if (r2 != MessagesPreviewCell.m1699$$Nest$fgetprogress(this.this$0)) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: $r8$lambda$tUlTl9SnOC-KIYflblUWSxe8xUs, reason: not valid java name */
    public static /* synthetic */ void m1697$r8$lambda$tUlTl9SnOCKIYflblUWSxe8xUs(MessagesPreviewCell r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetcancelProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m1698$$Nest$fgetcancelProgress(MessagesPreviewCell r0) {
        return r0.cancelProgress;
    }

    /* renamed from: -$$Nest$fgetprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1699$$Nest$fgetprogress(MessagesPreviewCell r0) {
        return r0.progress;
    }

    /* renamed from: -$$Nest$fputprogress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1700$$Nest$fputprogress(MessagesPreviewCell r0, int r1) {
        r0.progress = r1;
    }

    public MessagesPreviewCell(Context r2, INavigationLayout r3) {
        this(r2, r3, 0);
    }

    public MessagesPreviewCell(Context r18, INavigationLayout r19, int r20) {
        super(r18);
        this.progress = -1;
        this.cancelProgress = new MessagesPreviewCell$$ExternalSyntheticLambda0(this);
        this.parentLayout = r19;
        this.type = r20;
        if (r20 != 1) goto L5;
        int r6 = 1;
    L6:
        this.cells = new ChatMessageCell[r6];
        MessageObject[] r62 = new MessageObject[r6];
        this.messageObjects = r62;
        setWillNotDraw(false);
        setOrientation(1);
        setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(11.0f));
        this.monetBackgroundDrawable = new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray));
        this.shadowDrawable = Theme.getThemedDrawable(r18, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow);
        TLRPC.TL_message r8 = new TLRPC.TL_message();
        int r9 = (int) (System.currentTimeMillis() / 1000);
        int r10 = r9 - 3600;
        if (r20 != 0) goto L9;
        int r15 = r9 - 3590;
        r8.date = r15;
        r8.dialog_id = 1;
        r8.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_ADDED;
        TLRPC.TL_peerUser r3 = new TLRPC.TL_peerUser();
        r8.from_id = r3;
        r3.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        r8.id = 1;
        TLRPC.TL_messageMediaDocument r32 = new TLRPC.TL_messageMediaDocument();
        r8.media = r32;
        r32.flags = 1;
        r32.document = new TLRPC.TL_document();
        TLRPC.Document r33 = r8.media.document;
        r33.mime_type = "image/webp";
        r33.file_reference = new byte[0];
        r33.access_hash = 0;
        r33.date = r10;
        TLRPC.TL_documentAttributeSticker r34 = new TLRPC.TL_documentAttributeSticker();
        r34.alt = "🐈\u200d⬛";
        r8.media.document.attributes.add(r34);
        TLRPC.TL_documentAttributeImageSize r35 = new TLRPC.TL_documentAttributeImageSize();
        r35.h = 512;
        r35.w = 512;
        r8.media.document.attributes.add(r35);
        r8.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r8.out = true;
        TLRPC.TL_peerUser r36 = new TLRPC.TL_peerUser();
        r8.peer_id = r36;
        r36.user_id = 0;
        MessageObject r37 = new MessageObject(UserConfig.selectedAccount, r8, true, false);
        r62[0] = r37;
        r37.useCustomPhoto = true;
        TLRPC.TL_message r38 = new TLRPC.TL_message();
        r38.message = LocaleController.getString(R.string.StickerSizeDialogMessageReplyTo);
        r38.date = r15;
        r38.dialog_id = -1;
        r38.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CHANGED;
        r38.id = 2;
        r38.media = new TLRPC.TL_messageMediaEmpty();
        r38.out = false;
        TLRPC.TL_peerUser r82 = new TLRPC.TL_peerUser();
        r38.peer_id = r82;
        r82.user_id = 1;
        MessageObject r83 = r62[0];
        r83.customReplyName = "immat0x1";
        r83.replyMessageObject = new MessageObject(UserConfig.selectedAccount, r38, true, false);
        TLRPC.TL_message r39 = new TLRPC.TL_message();
        r39.message = LocaleController.getString(R.string.StickerSizeDialogMessage);
        r39.date = r9 - 3480;
        r39.dialog_id = -1;
        r39.flags = GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED;
        r39.id = 2;
        r39.media = new TLRPC.TL_messageMediaEmpty();
        r39.out = false;
        TLRPC.TL_peerUser r4 = new TLRPC.TL_peerUser();
        r39.peer_id = r4;
        r4.user_id = 1;
        r39.from_id = new TLRPC.TL_peerUser();
        TLRPC.TL_messageReplyHeader r42 = new TLRPC.TL_messageReplyHeader();
        r39.reply_to = r42;
        r42.flags |= 16;
        r42.reply_to_msg_id = 5;
        MessageObject r43 = new MessageObject(UserConfig.selectedAccount, r39, true, false);
        r62[1] = r43;
        r43.customReplyName = "8055";
        r43.replyMessageObject = r62[0];
    L14:
        int r310 = 0;
    L15:
        ChatMessageCell[] r44 = this.cells;
        if (r310 >= r44.length) goto L22;
        r44[r310] = new AnonymousClass1(this, r18, UserConfig.selectedAccount, r20);
        this.cells[r310].setDelegate(new AnonymousClass2(this));
        ChatMessageCell r45 = this.cells[r310];
        r45.isChat = false;
        if (r20 != 1) goto L20;
        boolean r63 = true;
    L21:
        r45.hideViews = r63;
        r45.setFullyDraw(true);
        this.cells[r310].setMessageObject(this.messageObjects[r310], null, false, false, false);
        addView(this.cells[r310], LayoutHelper.createLinear(-1, -2));
        r310 = r310 + 1;
        goto L15
    L20:
        r63 = false;
        goto L21
    L22:
        return;
    L9:
        if (r20 != 1) goto L14;
        TLRPC.TL_message r311 = new TLRPC.TL_message();
        String r84 = LocaleController.getString(R.string.MessagePreviewDialogMessage);
        r311.message = r84;
        int r85 = r84.indexOf("🤔");
        if (r85 < 0) goto L13;
        TLRPC.TL_messageEntityCustomEmoji r102 = new TLRPC.TL_messageEntityCustomEmoji();
        r102.offset = r85;
        r102.length = 2;
        r102.document_id = 5330328892811018097L;
        r311.entities.add(r102);
    L13:
        r311.date = r9 - 3540;
        r311.dialog_id = 1;
        r311.flags = 34051;
        r311.forwards = 67;
        r311.edit_date = r9 - 3480;
        TLRPC.TL_peerUser r46 = new TLRPC.TL_peerUser();
        r311.from_id = r46;
        r46.user_id = UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId();
        r311.id = 1;
        r311.out = false;
        TLRPC.TL_peerUser r47 = new TLRPC.TL_peerUser();
        r311.peer_id = r47;
        r47.user_id = 0;
        MessageObject r48 = new MessageObject(UserConfig.selectedAccount, r311, true, false);
        r62[0] = r48;
        r48.viewsReloaded = true;
        r48.forceAvatar = true;
        r48.resetLayout();
        r62[0].eventId = 1;
        goto L14
    L5:
        r6 = 2;
        goto L6
    }

    private /* synthetic */ void lambda$new$0() {
        this.progress = -1;
        ChatMessageCell[] r3 = this.cells;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        ChatMessageCell r2 = r3[r1];
        if (r2 == null) goto L7;
        r2.invalidate();
    L7:
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSetPressed(boolean r1) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (this.type != 1) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r3);
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof MessagesPreviewCell) == false) goto L12;
        MessagesPreviewCell r52 = (MessagesPreviewCell) r5;
        if (this.type != r52.type) goto L12;
        if (this.progress != r52.progress) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        ChatMessageCell[] r3 = this.cells;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        ChatMessageCell r2 = r3[r1];
        if (r2 == null) goto L7;
        r2.invalidate();
    L7:
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BackgroundGradientDrawable.Disposable r0 = this.backgroundGradientDisposable;
        if (r0 == null) goto L5;
        r0.dispose();
        this.backgroundGradientDisposable = null;
    L5:
        BackgroundGradientDrawable.Disposable r02 = this.oldBackgroundGradientDisposable;
        if (r02 == null) goto L9;
        r02.dispose();
        this.oldBackgroundGradientDisposable = null;
        return;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r12) {
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        Drawable r0 = this.monetBackgroundDrawable;
    L7:
        if (r0 == this.backgroundDrawable) goto L16;
        if (r0 == null) goto L16;
        if (Theme.isAnimatingColor() == false) goto L12;
        this.oldBackgroundDrawable = this.backgroundDrawable;
        this.oldBackgroundGradientDisposable = this.backgroundGradientDisposable;
    L15:
        this.backgroundDrawable = r0;
        goto L16
    L12:
        BackgroundGradientDrawable.Disposable r1 = this.backgroundGradientDisposable;
        if (r1 == null) goto L15;
        r1.dispose();
        this.backgroundGradientDisposable = null;
    L16:
        float r02 = this.parentLayout.getThemeAnimationValue();
        int r3 = 0;
    L18:
        if (r3 >= 2) goto L62;
        if (r3 != 0) goto L21;
        Drawable r5 = this.oldBackgroundDrawable;
    L23:
        if (r5 != this.monetBackgroundDrawable) goto L25;
        int r6 = 150;
    L26:
        if (r5 == null) goto L61;
        if (r3 == 1) goto L31;
    L33:
        r5.setAlpha(r6);
    L35:
        if ((r5 instanceof ColorDrawable) == false) goto L37;
    L48:
        r5.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        if ((r5 instanceof BackgroundGradientDrawable) == false) goto L51;
        this.backgroundGradientDisposable = ((BackgroundGradientDrawable) r5).drawExactBoundsSize(r12, this);
    L52:
        if (r3 != 0) goto L61;
        if (this.oldBackgroundDrawable == null) goto L61;
        if (r02 < 1.0f) goto L61;
        BackgroundGradientDrawable.Disposable r4 = this.oldBackgroundGradientDisposable;
        if (r4 == null) goto L60;
        r4.dispose();
        this.oldBackgroundGradientDisposable = null;
    L60:
        this.oldBackgroundDrawable = null;
        invalidate();
        goto L61
    L51:
        r5.draw(r12);
        goto L52
    L37:
        if ((r5 instanceof GradientDrawable) == true) goto L48;
        if ((r5 instanceof MotionBackgroundDrawable) == true) goto L48;
        if ((r5 instanceof BitmapDrawable) == false) goto L52;
        if (((BitmapDrawable) r5).getTileModeX() != Shader.TileMode.REPEAT) goto L46;
        r12.save();
        float r42 = 2.0f / AndroidUtilities.density;
        r12.scale(r42, r42);
        r5.setBounds(0, 0, (int) Math.ceil(getMeasuredWidth() / r42), (int) Math.ceil(getMeasuredHeight() / r42));
    L47:
        r5.draw(r12);
        r12.restore();
        goto L52
    L46:
        int r62 = getMeasuredHeight();
        float r7 = Math.max(getMeasuredWidth() / r5.getIntrinsicWidth(), r62 / r5.getIntrinsicHeight());
        int r8 = (int) Math.ceil(r5.getIntrinsicWidth() * r7);
        int r72 = (int) Math.ceil(r5.getIntrinsicHeight() * r7);
        int r9 = (getMeasuredWidth() - r8) / 2;
        int r63 = (r62 - r72) / 2;
        r12.save();
        r12.clipRect(0, 0, r8, getMeasuredHeight());
        r5.setBounds(r9, r63, r8 + r9, r72 + r63);
        goto L47
    L31:
        if (this.oldBackgroundDrawable == null) goto L33;
        r5.setAlpha((int) (r6 * r02));
    L61:
        r3 = r3 + 1;
        goto L18
    L25:
        r6 = 255;
        goto L26
    L21:
        r5 = this.backgroundDrawable;
        goto L23
    L62:
        this.shadowDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        this.shadowDrawable.draw(r12);
        return;
    L5:
        r0 = Theme.getCachedWallpaperNonBlocking();
        goto L7
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (this.type != 1) goto L7;
        return false;
    L7:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r3) {
        if (this.type != 1) goto L7;
        return false;
    L7:
        return super.onTouchEvent(r3);
    }

    public void refreshMessages() {
        int r0 = 0;
    L3:
        ChatMessageCell[] r1 = this.cells;
        if (r0 >= r1.length) goto L9;
        MessageObject r4 = this.messageObjects[r0];
        if (r4 == null) goto L8;
        r4.forceUpdate = true;
    L8:
        r1[r0].setMessageObject(r4, null, false, false, false);
        this.cells[r0].invalidate();
        r0 = r0 + 1;
        goto L3
    }
}
