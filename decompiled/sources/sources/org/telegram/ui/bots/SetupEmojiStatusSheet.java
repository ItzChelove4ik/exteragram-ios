package org.telegram.ui.bots;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AttachableDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Text;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public abstract class SetupEmojiStatusSheet {

    /* renamed from: org.telegram.ui.bots.SetupEmojiStatusSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends BaseFragment {
        public AnonymousClass1() {
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Context getContext() {
            return AndroidUtilities.findActivity(LaunchActivity.instance);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public int getCurrentAccount() {
            return this.currentAccount;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            Activity r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
            if (r0 == null) goto L5;
            return r0;
        L5:
            return LaunchActivity.instance;
        }
    }

    /* renamed from: org.telegram.ui.bots.SetupEmojiStatusSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends BaseFragment {
        public AnonymousClass2() {
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Context getContext() {
            return AndroidUtilities.findActivity(LaunchActivity.instance);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public int getCurrentAccount() {
            return this.currentAccount;
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public Activity getParentActivity() {
            Activity r0 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
            if (r0 == null) goto L5;
            return r0;
        L5:
            return LaunchActivity.instance;
        }
    }

    public static class UserEmojiStatusDrawable extends Drawable implements AttachableDrawable, NotificationCenter.NotificationCenterDelegate {
        private final AnimatedFloat animatedSwap;
        private boolean attached;
        private final Paint backgroundPaint;
        private final Paint backgroundPaint2;
        private int currentStatus;
        private final AnimatedEmojiDrawable[] emojis;
        private final boolean highlight;
        private final RectF rect;
        private final ImageReceiver statusImageReceiver;
        private final Text text;
        private final ImageReceiver userImageReceiver;
        private View view;
        private boolean waitingForStatuses;

        public static /* synthetic */ void $r8$lambda$Dh21MuXmhhDTl69s4oQVF3QiTV0(UserEmojiStatusDrawable r0) {
            r0.lambda$setRandomStatus$0();
        }

        public UserEmojiStatusDrawable(TLRPC.User r12) {
            Paint r0 = new Paint(1);
            this.backgroundPaint = r0;
            Paint r2 = new Paint(1);
            this.backgroundPaint2 = r2;
            ImageReceiver r3 = new ImageReceiver();
            this.userImageReceiver = r3;
            this.statusImageReceiver = new ImageReceiver();
            this.currentStatus = 1;
            this.emojis = new AnimatedEmojiDrawable[2];
            this.rect = new RectF();
            this.animatedSwap = new AnimatedFloat(new SetupEmojiStatusSheet$UserEmojiStatusDrawable$$ExternalSyntheticLambda1(this), 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.highlight = false;
            int r1 = Theme.key_windowBackgroundWhite;
            r0.setColor(Theme.getColor(r1));
            r2.setColor(Theme.getColor(r1));
            r2.setShadowLayer(AndroidUtilities.dp(2.33f), 0.0f, AndroidUtilities.dp(2.0f), Theme.multAlpha(-16777216, 0.18f));
            AvatarDrawable r02 = new AvatarDrawable();
            r02.setInfo(r12);
            r3.setForUserOrChat(r12, r02);
            r3.setRoundRadius(AndroidUtilities.dp(16.0f));
            setRandomStatus();
            this.text = new Text(UserObject.getUserName(r12), 14.0f);
        }

        public UserEmojiStatusDrawable(TLRPC.User r19, TLRPC.Document r20) {
            Paint r3 = new Paint(1);
            this.backgroundPaint = r3;
            Paint r5 = new Paint(1);
            this.backgroundPaint2 = r5;
            ImageReceiver r6 = new ImageReceiver();
            this.userImageReceiver = r6;
            ImageReceiver r7 = new ImageReceiver();
            this.statusImageReceiver = r7;
            this.currentStatus = 1;
            this.emojis = new AnimatedEmojiDrawable[2];
            this.rect = new RectF();
            this.animatedSwap = new AnimatedFloat(new SetupEmojiStatusSheet$UserEmojiStatusDrawable$$ExternalSyntheticLambda1(this), 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.highlight = true;
            int r4 = Theme.key_windowBackgroundWhite;
            r3.setColor(Theme.getColor(r4));
            r5.setColor(Theme.getColor(r4));
            r5.setShadowLayer(AndroidUtilities.dp(2.33f), 0.0f, AndroidUtilities.dp(2.0f), Theme.multAlpha(-16777216, 0.18f));
            AvatarDrawable r32 = new AvatarDrawable();
            r32.setInfo(r19);
            r6.setForUserOrChat(r19, r32);
            r6.setRoundRadius(AndroidUtilities.dp(16.0f));
            TLRPC.PhotoSize r33 = FileLoader.getClosestPhotoSizeWithSize(r20.thumbs, 120);
            r7.setImage(ImageLocation.getForDocument(r20), "120_120", ImageLocation.getForDocument(r33, r20), "120_120", DocumentObject.getSvgThumb(r20.thumbs, Theme.key_windowBackgroundGray, 0.35f), 0, null, null, 0);
            this.text = new Text(UserObject.getUserName(r19), 14.0f);
        }

        private /* synthetic */ void lambda$setRandomStatus$0() {
            if (this.attached == true) goto L5;
            return;
        L5:
            setRandomStatus();
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 == NotificationCenter.groupStickersDidLoad) goto L5;
            return;
        L5:
            if (this.waitingForStatuses == true) goto L7;
            return;
        L7:
            if (this.attached == false) goto L12;
            this.waitingForStatuses = false;
            setRandomStatus();
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r20) {
            Rect r1 = getBounds();
            if (this.highlight == false) goto L5;
            int r3 = 48;
        L6:
            float r32 = AndroidUtilities.dp((r3 + 38) + 6.66f) + this.text.getCurrentWidth();
            float r33 = r32 / 2.0f;
            float r4 = AndroidUtilities.dp(32.0f) / 2.0f;
            this.rect.set(r1.centerX() - r33, r1.centerY() - r4, r1.centerX() + r33, r1.centerY() + r4);
            r20.drawRoundRect(this.rect, r4, r4, this.backgroundPaint);
            ImageReceiver r12 = this.userImageReceiver;
            RectF r34 = this.rect;
            r12.setImageCoords(r34.left, r34.top, AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f));
            this.userImageReceiver.draw(r20);
            this.text.draw(r20, this.rect.left + AndroidUtilities.dp(36.0f), this.rect.centerY(), Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), 1.0f);
            if (this.highlight == false) goto L10;
            float r13 = this.rect.right - AndroidUtilities.dp(22.66f);
            r20.drawCircle(r13, this.rect.centerY(), AndroidUtilities.dp(24.0f), this.backgroundPaint2);
            this.statusImageReceiver.setImageCoords(r13 - AndroidUtilities.dp(16.0f), this.rect.centerY() - AndroidUtilities.dp(16.0f), AndroidUtilities.dp(32.0f), AndroidUtilities.dp(32.0f));
            this.statusImageReceiver.draw(r20);
            return;
        L10:
            float r14 = this.animatedSwap.set(this.currentStatus);
            r20.save();
            r20.translate((int) (this.rect.right - AndroidUtilities.dp(30.66f)), (int) (this.rect.centerY() - AndroidUtilities.dp(12.0f)));
            int r11 = -1;
            if (r14 >= 1.0f) goto L19;
            AnimatedEmojiDrawable r5 = this.emojis[0];
            if (r5 == null) goto L19;
            r20.save();
            if (this.currentStatus != 0) goto L17;
            int r15 = -1;
        L18:
            r20.translate(0.0f, (r15 * AndroidUtilities.dp(9.0f)) * r14);
            float r152 = 1.0f - r14;
            float r17 = 24.0f;
            float r35 = (r152 * 0.4f) + 0.6f;
            float r16 = 1.0f;
            float r18 = 12.0f;
            r20.scale(r35, r35, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
            r5.setBounds(0, 0, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
            r5.setAlpha((int) (r152 * 255.0f));
            r5.draw(r20);
            r20.restore();
        L21:
            if (r14 <= 0.0f) goto L29;
            AnimatedEmojiDrawable r36 = this.emojis[1];
            if (r36 == null) goto L29;
            r20.save();
            if (this.currentStatus == 1) goto L28;
            r11 = 1;
        L28:
            r20.translate(0.0f, (r11 * AndroidUtilities.dp(9.0f)) * (r16 - r14));
            float r8 = (0.4f * r14) + 0.6f;
            r20.scale(r8, r8, AndroidUtilities.dp(r18), AndroidUtilities.dp(r18));
            r36.setBounds(0, 0, AndroidUtilities.dp(r17), AndroidUtilities.dp(r17));
            r36.setAlpha((int) (r14 * 255.0f));
            r36.draw(r20);
            r20.restore();
        L29:
            r20.restore();
            return;
        L17:
            r15 = 1;
        L19:
            r17 = 24.0f;
            r16 = 1.0f;
            r18 = 12.0f;
            goto L21
        L5:
            r3 = 28;
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // org.telegram.ui.Components.AttachableDrawable
        public void onAttachedToWindow(ImageReceiver r3) {
            this.attached = true;
            this.userImageReceiver.onAttachedToWindow();
            this.statusImageReceiver.onAttachedToWindow();
            NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(this, NotificationCenter.recentEmojiStatusesUpdate);
            AnimatedEmojiDrawable r0 = this.emojis[0];
            if (r0 == null) goto L5;
            r0.addView(this.view);
        L5:
            AnimatedEmojiDrawable r32 = this.emojis[1];
            if (r32 == null) goto L9;
            r32.addView(this.view);
            return;
        }

        @Override // org.telegram.ui.Components.AttachableDrawable
        public void onDetachedFromWindow(ImageReceiver r3) {
            this.attached = false;
            this.userImageReceiver.onDetachedFromWindow();
            this.statusImageReceiver.onDetachedFromWindow();
            NotificationCenter.getInstance(UserConfig.selectedAccount).removeObserver(this, NotificationCenter.recentEmojiStatusesUpdate);
            AnimatedEmojiDrawable r32 = this.emojis[0];
            if (r32 == null) goto L5;
            r32.removeView(this.view);
        L5:
            AnimatedEmojiDrawable r33 = this.emojis[1];
            if (r33 == null) goto L9;
            r33.removeView(this.view);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        @Override // org.telegram.ui.Components.AttachableDrawable
        public void setParent(View r2) {
            this.view = r2;
            this.statusImageReceiver.setParentView(r2);
            this.userImageReceiver.setParentView(r2);
        }

        public void setRandomStatus() {
            TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSet(new TLRPC.TL_inputStickerSetEmojiDefaultStatuses(), false);
            if (r0 != null) goto L5;
        L17:
            this.waitingForStatuses = true;
            return;
        L5:
            if (r0.documents.isEmpty() == true) goto L17;
            TLRPC.Document r02 = r0.documents.get((int) Math.floor(Math.random() * r0.documents.size()));
            int r1 = 1 - this.currentStatus;
            this.currentStatus = r1;
            AnimatedEmojiDrawable r12 = this.emojis[r1];
            if (r12 == null) goto L10;
            r12.removeView(this.view);
        L10:
            this.emojis[this.currentStatus] = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, 9, r02);
            this.emojis[this.currentStatus].setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_featuredStickers_addButton), PorterDuff.Mode.SRC_IN));
            if (this.attached == false) goto L15;
            AnimatedEmojiDrawable r03 = this.emojis[this.currentStatus];
            if (r03 == null) goto L15;
            r03.addView(this.view);
        L15:
            AndroidUtilities.runOnUIThread(new SetupEmojiStatusSheet$UserEmojiStatusDrawable$$ExternalSyntheticLambda0(this), 2500);
        }
    }

    /* renamed from: $r8$lambda$-HIic6tNxbal5Rj9WJRo00n2y-Q, reason: not valid java name */
    public static /* synthetic */ void m21487$r8$lambda$HIic6tNxbal5Rj9WJRo00n2yQ(TLObject r2, boolean[] r3, Utilities.Callback2 r4, TLRPC.UserFull r5) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L5;
        r5.bot_can_manage_emoji_status = true;
        if (r3[0] == true) goto L13;
        r3[0] = true;
        r4.run(Boolean.TRUE, "allowed");
        return;
    L13:
        return;
    L5:
        if (r3[0] == true) goto L12;
        r3[0] = true;
        r4.run(Boolean.TRUE, "cancelled");
        return;
    }

    public static /* synthetic */ void $r8$lambda$84d1K1TcTg3Uc_f8QlS39OyHBJQ(TLRPC.UserFull r0, Utilities.Callback2 r1, int r2, TLRPC.User r3) {
        if (r0 != null) goto L5;
        r1.run(Boolean.FALSE, "cancelled");
        return;
    L5:
        askPermission(r2, r3, r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$8KJI1aMWJ2V4l1nA0nvHuA_Qvqc(int r6, TLRPC.User r7, int r8, Utilities.Callback2 r9, TLRPC.Document r10) {
        AndroidUtilities.runOnUIThread(new SetupEmojiStatusSheet$$ExternalSyntheticLambda8(r6, r7, r10, r8, r9));
    }

    /* renamed from: $r8$lambda$Bpe8fH5CnS0DMe-_Uy52JGcSAVw, reason: not valid java name */
    public static /* synthetic */ void m21488$r8$lambda$Bpe8fH5CnS0DMe_Uy52JGcSAVw(Utilities.Callback2 r0, TLRPC.Document r1, String r2) {
        r0.run(r2, r1);
    }

    public static /* synthetic */ void $r8$lambda$OQ0ytzBiGXpvsWY9v4dtAOtj2ws(boolean[] r0, Utilities.Callback r1, int r2, TL_account.updateEmojiStatus r3, TLObject r4, TLRPC.TL_error r5) {
        AndroidUtilities.runOnUIThread(new SetupEmojiStatusSheet$$ExternalSyntheticLambda13(r4, r0, r1, r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$V1U6ZnSV1fFU4tBdC9dbSPRxIho(boolean[] r0, boolean[] r1, Utilities.Callback r2, DialogInterface r3) {
        if (r0[0] == false) goto L5;
        return;
    L5:
        if (r1[0] == true) goto L9;
        r1[0] = true;
        r2.run("USER_DECLINED");
        return;
    }

    public static /* synthetic */ void $r8$lambda$VVMpj7sTa0tcE252yeQCFSf86BA(Utilities.Callback2 r1, int r2, TLRPC.User r3, TLRPC.UserFull r4) {
        AndroidUtilities.runOnUIThread(new SetupEmojiStatusSheet$$ExternalSyntheticLambda5(r4, r1, r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$ax83ennwCnpbPD0KwgVfUXmrEqI(boolean[] r0, Utilities.Callback2 r1, TLRPC.UserFull r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new SetupEmojiStatusSheet$$ExternalSyntheticLambda12(r3, r0, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$e9F1zgMeFykHyXu2bGe3uCAdOus(int r2, boolean[] r3, boolean[] r4, Utilities.Callback2 r5, Context r6, TLRPC.User r7, TLRPC.UserFull r8, AlertDialog r9, int r10) {
        if (UserConfig.getInstance(r2).isPremium() == true) goto L10;
        new PremiumFeatureBottomSheet(new AnonymousClass2(), 12, false).show();
        if (r3[0] == false) goto L7;
        return;
    L7:
        if (r4[0] == true) goto L13;
        r4[0] = true;
        r5.run(Boolean.TRUE, "cancelled");
        return;
    L13:
        return;
    L10:
        r3[0] = true;
        saveAccessRequested(r6, r2, r7.id);
        TL_bots.toggleUserEmojiStatusPermission r32 = new TL_bots.toggleUserEmojiStatusPermission();
        r32.bot = MessagesController.getInstance(r2).getInputUser(r7);
        r32.enabled = true;
        ConnectionsManager.getInstance(r2).sendRequest(r32, new SetupEmojiStatusSheet$$ExternalSyntheticLambda11(r4, r5, r8));
    }

    public static /* synthetic */ void $r8$lambda$jGmmYIy0VFIyzAMrrL6Tm3RNVBg(int r2, boolean[] r3, TLRPC.Document r4, int r5, boolean[] r6, Utilities.Callback r7, AlertDialog r8, int r9) {
        if (UserConfig.getInstance(r2).isPremium() == true) goto L6;
        new PremiumFeatureBottomSheet(new AnonymousClass1(), 12, false).show();
        return;
    L6:
        r3[0] = true;
        TL_account.updateEmojiStatus r32 = new TL_account.updateEmojiStatus();
        TLRPC.TL_emojiStatus r92 = new TLRPC.TL_emojiStatus();
        r92.document_id = r4.id;
        if (r5 <= 0) goto L9;
        r92.flags |= 1;
        r92.until = ConnectionsManager.getInstance(r2).getCurrentTime() + r5;
    L9:
        r32.emoji_status = r92;
        ConnectionsManager.getInstance(r2).sendRequest(r32, new SetupEmojiStatusSheet$$ExternalSyntheticLambda9(r6, r7, r2, r32));
    }

    public static /* synthetic */ void $r8$lambda$qDwyHc5fpxxeLe5vW_dVAWCeWXs(Utilities.Callback2 r0, TLRPC.Document r1, String r2) {
        r0.run(r2, r1);
    }

    public static /* synthetic */ void $r8$lambda$qjBVdEmBi2OOINVrp3sTDsRgkV4(TLObject r4, boolean[] r5, Utilities.Callback r6, int r7, TL_account.updateEmojiStatus r8) {
        if ((r4 instanceof TLRPC.TL_boolTrue) == false) goto L5;
        TLRPC.User r42 = UserConfig.getInstance(r7).getCurrentUser();
        if (r42 == null) goto L12;
        r42.emoji_status = r8.emoji_status;
        NotificationCenter.getInstance(r7).postNotificationName(NotificationCenter.userEmojiStatusUpdated, new Object[]{r42});
        MessagesController.getInstance(r7).updateEmojiStatusUntilUpdate(r42.id, r42.emoji_status);
    L12:
        if (r5[0] == true) goto L16;
        r5[0] = true;
        r6.run(null);
        return;
    L16:
        return;
    L5:
        if (r5[0] == true) goto L15;
        r5[0] = true;
        r6.run("SERVER_ERROR");
        return;
    }

    public static /* synthetic */ void $r8$lambda$ucmKbYbq6KPA0hOkeStQQwoEK5Q(int r1, TLRPC.User r2, TLRPC.Document r3, int r4, Utilities.Callback2 r5) {
        show(r1, r2, r3, r4, new SetupEmojiStatusSheet$$ExternalSyntheticLambda10(r5, r3));
    }

    /* renamed from: $r8$lambda$xBcQe-_QLK_Xp3dKHzB-ejZphvM, reason: not valid java name */
    public static /* synthetic */ void m21489$r8$lambda$xBcQe_QLK_Xp3dKHzBejZphvM(boolean[] r0, boolean[] r1, Context r2, int r3, TLRPC.User r4, Utilities.Callback2 r5, DialogInterface r6) {
        if (r0[0] == false) goto L5;
        return;
    L5:
        if (r1[0] == true) goto L9;
        r1[0] = true;
        saveAccessRequested(r2, r3, r4.id);
        r5.run(Boolean.TRUE, "cancelled");
        return;
    }

    public static void askPermission(int r2, long r3, Utilities.Callback2<Boolean, String> r5) {
        TLRPC.User r0 = MessagesController.getInstance(r2).getUser(Long.valueOf(r3));
        TLRPC.UserFull r32 = MessagesController.getInstance(r2).getUserFull(r3);
        if (r32 != null) goto L6;
        MessagesController.getInstance(r2).loadFullUser(r0, 0, true, new SetupEmojiStatusSheet$$ExternalSyntheticLambda0(r5, r2, r0));
        return;
    L6:
        askPermission(r2, r0, r32, r5);
    }

    public static void askPermission(int r11, TLRPC.User r12, TLRPC.UserFull r13, Utilities.Callback2<Boolean, String> r14) {
        if (r14 != null) goto L5;
        return;
    L5:
        if (r13.bot_can_manage_emoji_status == false) goto L8;
        r14.run(Boolean.FALSE, "allowed");
        return;
    L8:
        Context r0 = AndroidUtilities.findActivity(LaunchActivity.instance);
        if (r0 != null) goto L11;
        r0 = ApplicationLoader.applicationContext;
    L11:
        Context r4 = r0;
        TLRPC.User r02 = UserConfig.getInstance(r11).getCurrentUser();
        boolean[] r3 = new boolean[1];
        boolean[] r2 = new boolean[1];
        AlertDialog r122 = new AlertDialog.Builder(r4, null).setTopImage(new UserEmojiStatusDrawable(r02), Theme.getColor(Theme.key_dialogTopBackground)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotEmojiStatusPermissionRequest, new Object[]{UserObject.getUserName(r12), UserObject.getUserName(r12)}))).setPositiveButton(LocaleController.getString(R.string.BotEmojiStatusPermissionAllow), new SetupEmojiStatusSheet$$ExternalSyntheticLambda6(r11, r2, r3, r14, r4, r12, r13)).setNegativeButton(LocaleController.getString(R.string.BotEmojiStatusPermissionDecline), null).create();
        r122.show();
        r122.setOnDismissListener(new SetupEmojiStatusSheet$$ExternalSyntheticLambda7(r2, r3, r4, r11, r12, r14));
    }

    public static void clear() {
        Context r0 = ApplicationLoader.applicationContext;
        if (r0 == null) goto L9;
        int r2 = 0;
    L7:
        if (r2 >= 16) goto L11;
        r0.getSharedPreferences("botemojistatus_" + r2, 0).edit().clear().apply();
        r2 = r2 + 1;
        goto L7
    L11:
        return;
    }

    public static boolean getAccessRequested(Context r3, int r4, long r5) {
        if (r3 != null) goto L6;
        return false;
    L6:
        return r3.getSharedPreferences("botemojistatus_" + r4, 0).getBoolean("requested_" + r5, false);
    }

    public static void saveAccessRequested(Context r2, int r3, long r4) {
        if (r2 != null) goto L4;
        return;
    L4:
        r2.getSharedPreferences("botemojistatus_" + r3, 0).edit().putBoolean("requested_" + r4, true).apply();
    }

    public static void show(int r2, TLRPC.User r3, long r4, int r6, Utilities.Callback2<String, TLRPC.Document> r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        TLRPC.Document r0 = AnimatedEmojiDrawable.findDocument(r2, r4);
        if (r0 == null) goto L8;
        show(r2, r3, r0, r6, new SetupEmojiStatusSheet$$ExternalSyntheticLambda1(r7, r0));
        return;
    L8:
        AnimatedEmojiDrawable.getDocumentFetcher(r2).fetchDocument(r4, new SetupEmojiStatusSheet$$ExternalSyntheticLambda2(r2, r3, r6, r7));
    }

    public static void show(int r12, TLRPC.User r13, TLRPC.Document r14, int r15, Utilities.Callback<String> r16) {
        if (r16 != null) goto L4;
        return;
    L4:
        if (r14 == null) goto L33;
        if ((r14 instanceof TLRPC.TL_documentEmpty) == true) goto L33;
        Context r0 = AndroidUtilities.findActivity(LaunchActivity.instance);
        if (r0 != null) goto L11;
        r0 = ApplicationLoader.applicationContext;
    L11:
        ConnectionsManager.getInstance(r12).getCurrentTime();
        TLRPC.User r1 = UserConfig.getInstance(r12).getCurrentUser();
        boolean[] r5 = new boolean[1];
        boolean[] r2 = new boolean[1];
        if (r15 <= 0) goto L30;
        int r4 = r15 / 86400;
        int r6 = (r15 - (86400 * r4)) / 3600;
        int r3 = Math.round((r3 - (r6 * 3600)) / 60.0f);
        StringBuilder r7 = new StringBuilder();
        if (r4 > 0) goto L16;
    L19:
        if (r6 > 0) goto L21;
    L24:
        if (r3 > 0) goto L26;
    L29:
        SpannableStringBuilder r132 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotEmojiStatusSetRequestFor, new Object[]{UserObject.getUserName(r13), r7}));
    L31:
        AlertDialog r122 = new AlertDialog.Builder(r0, null).setTopImage(new UserEmojiStatusDrawable(r1, r14), Theme.getColor(Theme.key_dialogTopBackground)).setMessage(r132).setPositiveButton(LocaleController.getString(R.string.BotEmojiStatusConfirm), new SetupEmojiStatusSheet$$ExternalSyntheticLambda3(r12, r2, r14, r15, r5, r16)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        r122.show();
        r122.setOnDismissListener(new SetupEmojiStatusSheet$$ExternalSyntheticLambda4(r2, r5, r16));
        return;
    L26:
        if (r7.length() <= 0) goto L28;
        r7.append(" ");
    L28:
        r7.append(LocaleController.formatPluralString("BotEmojiStatusSetRequestForMinute", r3, new Object[0]));
        goto L29
    L21:
        if (r7.length() <= 0) goto L23;
        r7.append(" ");
    L23:
        r7.append(LocaleController.formatPluralString("BotEmojiStatusSetRequestForHour", r6, new Object[0]));
        goto L24
    L16:
        if (r7.length() <= 0) goto L18;
        r7.append(" ");
    L18:
        r7.append(LocaleController.formatPluralString("BotEmojiStatusSetRequestForDay", r4, new Object[0]));
        goto L19
    L30:
        r132 = AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotEmojiStatusSetRequest, new Object[]{UserObject.getUserName(r13)}));
    L33:
        r16.run("SUGGESTED_EMOJI_INVALID");
    }
}
