package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.TopicsController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_forum;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.Forum.ForumBubbleDrawable;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LetterDrawable;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.ReplaceableIconDrawable;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes6.dex */
public class TopicCreateFragment extends BaseFragment {
    BackupImageView[] backupImageView;
    TextCheckCell2 checkBoxCell;
    boolean created;
    Drawable defaultIconDrawable;
    long dialogId;
    EditTextBoldCursor editTextBoldCursor;
    String firstSymbol;
    ForumBubbleDrawable forumBubbleDrawable;
    int iconColor;
    AnimationNotificationsLocker notificationsLocker;
    private ChatActivity openInChatActivity;
    ReplaceableIconDrawable replaceableIconDrawable;
    SelectAnimatedEmojiDialog selectAnimatedEmojiDialog;
    long selectedEmojiDocumentId;
    TLRPC.TL_forumTopic topicForEdit;
    long topicId;

    /* renamed from: org.telegram.ui.TopicCreateFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ TopicCreateFragment this$0;

        public static /* synthetic */ void $r8$lambda$A4rw8_fIKrbUpoQ2vSRisKnBqRU(AnonymousClass1 r0, TLObject r1, String r2, AlertDialog r3) {
            r0.lambda$onItemClick$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$amldw3qyO9IcHiCsIMINuL4IC4E(TLObject r0, TLRPC.TL_error r1) {
        }

        public static /* synthetic */ void $r8$lambda$iNFPvgVNVpBR8EQajc7nBAHNcns(AnonymousClass1 r0, String r1, AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onItemClick$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$otLZCyN9xeFR8YST98V_B6y2Ojk(TLObject r0, TLRPC.TL_error r1) {
        }

        public AnonymousClass1(TopicCreateFragment r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(TLObject r18, String r19, AlertDialog r20) {
            if (r18 == null) goto L17;
            TLRPC.Updates r2 = (TLRPC.Updates) r18;
            int r4 = 0;
        L6:
            if (r4 >= r2.updates.size()) goto L17;
            if ((r2.updates.get(r4) instanceof TL_update.TL_updateMessageID) == false) goto L16;
            TL_update.TL_updateMessageID r5 = (TL_update.TL_updateMessageID) r2.updates.get(r4);
            TLRPC.TL_messageActionTopicCreate r6 = new TLRPC.TL_messageActionTopicCreate();
            r6.title = r19;
            TLRPC.TL_messageService r7 = new TLRPC.TL_messageService();
            r7.action = r6;
            r7.peer_id = this.this$0.getMessagesController().getPeer(this.this$0.dialogId);
            r7.dialog_id = this.this$0.dialogId;
            r7.id = r5.id;
            r7.date = (int) (System.currentTimeMillis() / 1000);
            ArrayList<MessageObject> r9 = new ArrayList();
            r9.add(new MessageObject(TopicCreateFragment.access$300(this.this$0), r7, false, false));
            TLRPC.Chat r10 = this.this$0.getMessagesController().getChat(Long.valueOf(-this.this$0.dialogId));
            TLRPC.TL_forumTopic r14 = new TLRPC.TL_forumTopic();
            r14.id = r5.id;
            TopicCreateFragment r52 = this.this$0;
            long r11 = r52.selectedEmojiDocumentId;
            if (r11 == 0) goto L12;
            r14.icon_emoji_id = r11;
            r14.flags |= 1;
        L12:
            r14.my = true;
            r14.flags |= 2;
            r14.topicStartMessage = r7;
            r14.title = r19;
            r14.top_message = r7.id;
            r14.topMessage = r7;
            r14.from_id = r52.getMessagesController().getPeer(this.this$0.getUserConfig().clientUserId);
            r14.notify_settings = new TLRPC.TL_peerNotifySettings();
            TopicCreateFragment r53 = this.this$0;
            r14.icon_color = r53.iconColor;
            if (TopicCreateFragment.m20913$$Nest$fgetopenInChatActivity(r53) == null) goto L15;
            ChatActivity r8 = TopicCreateFragment.m20913$$Nest$fgetopenInChatActivity(this.this$0);
            r8.resetForReload();
            r8.saveDraft();
            r8.setThreadMessages(r9, r10, r7.id, 1, 1, r14);
            r8.justCreatedTopic = true;
            r8.firstLoadMessages();
            r8.updateTitle(true);
            r8.avatarContainer.updateSubtitle(true);
            r8.updateTopicTitleIcon();
            r8.topicsTabs.setCurrentTopic(r8.getTopicId());
            r8.updateTopPanel(true);
            r8.updateBottomOverlay(true);
            r8.hideFieldPanel(true);
            r8.applyDraftMaybe(true, true);
            r8.reloadPinnedMessages();
            this.this$0.getMessagesController().getTopicsController().onTopicCreated(this.this$0.dialogId, r14, true);
            this.this$0.finishFragment();
            goto L16
        L15:
            Bundle r54 = new Bundle();
            r54.putLong("chat_id", -this.this$0.dialogId);
            r54.putInt("message_id", 1);
            r54.putInt("unread_count", 0);
            r54.putBoolean("historyPreloaded", false);
            ChatActivity r82 = new ChatActivity(r54);
            r82.setThreadMessages(r9, r10, r7.id, 1, 1, r14);
            r82.justCreatedTopic = true;
            this.this$0.getMessagesController().getTopicsController().onTopicCreated(this.this$0.dialogId, r14, true);
            this.this$0.presentFragment(r82);
        L16:
            r4 = r4 + 1;
        L17:
            r20.dismiss();
        }

        private /* synthetic */ void lambda$onItemClick$1(String r1, AlertDialog r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new TopicCreateFragment$1$$ExternalSyntheticLambda3(this, r3, r1, r2));
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r11) {
            if (r11 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        L6:
            String r2 = null;
            if (r11 != 1) goto L25;
            if (this.this$0.editTextBoldCursor.getText() == null) goto L13;
            r2 = this.this$0.editTextBoldCursor.getText().toString();
        L13:
            if (TextUtils.isEmpty(r2) == false) goto L17;
            VibratorUtils.vibrate();
            AndroidUtilities.shakeView(this.this$0.editTextBoldCursor);
            return;
        L17:
            if (this.this$0.created == true) goto L62;
            AlertDialog r112 = new AlertDialog(this.this$0.getParentActivity(), 3);
            r112.showDelayed(500);
            this.this$0.created = true;
            TL_forum.TL_messages_createForumTopic r4 = new TL_forum.TL_messages_createForumTopic();
            r4.peer = this.this$0.getMessagesController().getInputPeer(this.this$0.dialogId);
            r4.title = r2;
            long r5 = this.this$0.selectedEmojiDocumentId;
            if (r5 == 0) goto L22;
            r4.icon_emoji_id = r5;
            r4.flags |= 8;
        L22:
            r4.random_id = Utilities.random.nextLong();
            TopicCreateFragment r0 = this.this$0;
            r4.icon_color = r0.iconColor;
            r4.flags |= 1;
            ConnectionsManager.getInstance(TopicCreateFragment.access$000(r0)).sendRequest(r4, new TopicCreateFragment$1$$ExternalSyntheticLambda0(this, r2, r112));
            return;
        L62:
            return;
        L25:
            if (r11 == 2) goto L27;
            return;
        L27:
            if (this.this$0.editTextBoldCursor.getText() == null) goto L31;
            r2 = this.this$0.editTextBoldCursor.getText().toString();
        L31:
            if (TextUtils.isEmpty(r2) == false) goto L35;
            VibratorUtils.vibrate();
            AndroidUtilities.shakeView(this.this$0.editTextBoldCursor);
            return;
        L35:
            if (this.this$0.topicForEdit.title.equals(r2) == false) goto L38;
            TopicCreateFragment r113 = this.this$0;
            if (r113.topicForEdit.icon_emoji_id != r113.selectedEmojiDocumentId) goto L38;
        L45:
            TopicCreateFragment r114 = this.this$0;
            if (r114.checkBoxCell != null) goto L48;
        L52:
            TopicCreateFragment r115 = this.this$0;
            TLRPC.TL_forumTopic r42 = r115.topicForEdit;
            long r52 = r115.selectedEmojiDocumentId;
            r42.icon_emoji_id = r52;
            if (r52 == 0) goto L55;
            r42.flags |= 1;
        L56:
            r42.title = r2;
            if (r115.checkBoxCell == null) goto L59;
            r42.hidden = !r11.isChecked();
        L59:
            TopicsController r116 = this.this$0.getMessagesController().getTopicsController();
            TopicCreateFragment r02 = this.this$0;
            r116.onTopicEdited(r02.dialogId, r02.topicForEdit);
            this.this$0.finishFragment();
            return;
        L55:
            r42.flags &= -2;
            goto L56
        L48:
            if (r114.topicForEdit.id != 1) goto L52;
            if ((!r4.isChecked()) == this.this$0.topicForEdit.hidden) goto L52;
            TL_forum.TL_messages_editForumTopic r117 = new TL_forum.TL_messages_editForumTopic();
            r117.peer = this.this$0.getMessagesController().getInputPeer(this.this$0.dialogId);
            r117.topic_id = this.this$0.topicForEdit.id;
            r117.hidden = !r4.checkBoxCell.isChecked();
            r117.flags |= 8;
            ConnectionsManager.getInstance(TopicCreateFragment.access$200(this.this$0)).sendRequest(r117, new TopicCreateFragment$1$$ExternalSyntheticLambda2());
        L38:
            TL_forum.TL_messages_editForumTopic r118 = new TL_forum.TL_messages_editForumTopic();
            r118.peer = this.this$0.getMessagesController().getInputPeer(this.this$0.dialogId);
            TLRPC.TL_forumTopic r53 = this.this$0.topicForEdit;
            r118.topic_id = r53.id;
            if (r53.title.equals(r2) == true) goto L41;
            r118.title = r2;
            r118.flags |= 1;
        L41:
            TopicCreateFragment r54 = this.this$0;
            long r6 = r54.topicForEdit.icon_emoji_id;
            long r8 = r54.selectedEmojiDocumentId;
            if (r6 == r8) goto L44;
            r118.icon_emoji_id = r8;
            r118.flags = 2 | r118.flags;
        L44:
            ConnectionsManager.getInstance(TopicCreateFragment.access$100(r54)).sendRequest(r118, new TopicCreateFragment$1$$ExternalSyntheticLambda1());
            goto L45
        }
    }

    /* renamed from: org.telegram.ui.TopicCreateFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        boolean keyboardWasShown;
        final /* synthetic */ TopicCreateFragment this$0;

        public AnonymousClass2(TopicCreateFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            measureKeyboardHeight();
            if (getKeyboardHeight() == 0) goto L5;
        L7:
            this.keyboardWasShown = true;
            setPadding(0, 0, 0, 0);
        L8:
            super.onMeasure(r5, r6);
            return;
        L5:
            if (this.keyboardWasShown == true) goto L7;
            int r0 = MessagesController.getGlobalEmojiSettings().getInt("kbd_height", AndroidUtilities.dp(200.0f));
            this.keyboardHeight = r0;
            setPadding(0, 0, 0, r0);
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.TopicCreateFragment$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ TopicCreateFragment this$0;

        public AnonymousClass3(TopicCreateFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            String r52 = r5.toString().trim();
            String r0 = this.this$0.firstSymbol;
            int r1 = r52.length();
            TopicCreateFragment r2 = this.this$0;
            if (r1 <= 0) goto L5;
            r2.firstSymbol = r52.substring(0, 1).toUpperCase();
        L7:
            if (r0.equals(this.this$0.firstSymbol) == true) goto L12;
            LetterDrawable r53 = new LetterDrawable(null, 1);
            r53.setTitle(this.this$0.firstSymbol);
            ReplaceableIconDrawable r4 = this.this$0.replaceableIconDrawable;
            if (r4 == null) goto L13;
            r4.setIcon(r53, true);
            return;
        L13:
            return;
        L12:
            return;
        L5:
            r2.firstSymbol = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L7
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.TopicCreateFragment$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        ValueAnimator backAnimator;
        boolean pressed;
        float pressedProgress;
        final /* synthetic */ TopicCreateFragment this$0;

        /* renamed from: org.telegram.ui.TopicCreateFragment$4$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$1.backAnimator = null;
            }
        }

        /* renamed from: $r8$lambda$1AWJwu23wgo2-nymx7j3H-XOS3I, reason: not valid java name */
        public static /* synthetic */ void m20915$r8$lambda$1AWJwu23wgo2nymx7j3HXOS3I(AnonymousClass4 r0, ValueAnimator r1) {
            r0.lambda$setPressed$0(r1);
        }

        public AnonymousClass4(TopicCreateFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$setPressed$0(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            float r0 = ((1.0f - this.pressedProgress) * 0.2f) + 0.8f;
            r5.save();
            r5.scale(r0, r0, getMeasuredHeight() / 2.0f, getMeasuredWidth() / 2.0f);
            super.dispatchDraw(r5);
            r5.restore();
            updatePressedProgress();
        }

        @Override // android.view.View
        public void setPressed(boolean r4) {
            super.setPressed(r4);
            if (this.pressed == r4) goto L14;
            this.pressed = r4;
            invalidate();
            if (r4 == false) goto L9;
            ValueAnimator r0 = this.backAnimator;
            if (r0 == null) goto L9;
            r0.removeAllListeners();
            this.backAnimator.cancel();
        L9:
            if (r4 == true) goto L15;
            float r42 = this.pressedProgress;
            if (r42 == 0.0f) goto L16;
            ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, 0.0f});
            this.backAnimator = r43;
            r43.addUpdateListener(new TopicCreateFragment$4$$ExternalSyntheticLambda0(this));
            this.backAnimator.addListener(new AnonymousClass1(this));
            this.backAnimator.setInterpolator(new OvershootInterpolator(5.0f));
            this.backAnimator.setDuration(350);
            this.backAnimator.start();
            return;
        L16:
            return;
        L15:
            return;
        }

        public void updatePressedProgress() {
            if (isPressed() == false) goto L8;
            float r0 = this.pressedProgress;
            if (r0 == 1.0f) goto L9;
            this.pressedProgress = Utilities.clamp(r0 + 0.16f, 1.0f, 0.0f);
            invalidate();
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.TopicCreateFragment$5, reason: invalid class name */
    public class AnonymousClass5 extends SelectAnimatedEmojiDialog {
        private boolean firstLayout;
        final /* synthetic */ TopicCreateFragment this$0;

        public AnonymousClass5(TopicCreateFragment r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
            this.firstLayout = true;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r5, Long r6, TLRPC.Document r7, TL_stars.TL_starGiftUnique r8, Integer r9) {
            boolean r82 = false;
            if (TextUtils.isEmpty(UserConfig.getInstance(TopicCreateFragment.access$400(this.this$0)).defaultTopicIcons) == true) goto L11;
            TLRPC.TL_messages_stickerSet r52 = this.this$0.getMediaDataController().getStickerSetByEmojiOrName(UserConfig.getInstance(TopicCreateFragment.access$500(this.this$0)).defaultTopicIcons);
            if (r52 != null) goto L7;
            long r0 = 0;
        L9:
            if (r0 != MediaDataController.getStickerSetId(r7)) goto L11;
            r82 = true;
            goto L11
        L7:
            r0 = r52.set.id;
        L11:
            TopicCreateFragment.m20914$$Nest$mselectEmoji(this.this$0, r6, r82);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.firstLayout == false) goto L6;
            this.firstLayout = false;
            this.this$0.selectAnimatedEmojiDialog.onShow(null);
            return;
        }
    }

    /* renamed from: $r8$lambda$DeQ7hFf-Dj-aVXNnNJspJWgEadQ, reason: not valid java name */
    public static /* synthetic */ void m20912$r8$lambda$DeQ7hFfDjaVXNnNJspJWgEadQ(TopicCreateFragment r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$IcgdsjAljmTA7OUK8gBy40mO17E(TopicCreateFragment r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$iNzxIkwwoCGJOQoN5eFLZS8xULU(TopicCreateFragment r0) {
        r0.lambda$selectEmoji$2();
    }

    /* renamed from: -$$Nest$fgetopenInChatActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m20913$$Nest$fgetopenInChatActivity(TopicCreateFragment r0) {
        return r0.openInChatActivity;
    }

    /* renamed from: -$$Nest$mselectEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20914$$Nest$mselectEmoji(TopicCreateFragment r0, Long r1, boolean r2) {
        r0.selectEmoji(r1, r2);
    }

    private TopicCreateFragment(Bundle r1) {
        super(r1);
        this.backupImageView = new BackupImageView[2];
        this.firstSymbol = _UrlKt.FRAGMENT_ENCODE_SET;
        this.notificationsLocker = new AnimationNotificationsLocker();
    }

    public static /* synthetic */ int access$000(TopicCreateFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(TopicCreateFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(TopicCreateFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$300(TopicCreateFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(TopicCreateFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(TopicCreateFragment r0) {
        return r0.currentAccount;
    }

    public static TopicCreateFragment create(long r2, long r4) {
        Bundle r0 = new Bundle();
        r0.putLong("chat_id", r2);
        r0.putLong("topic_id", r4);
        return new TopicCreateFragment(r0);
    }

    private /* synthetic */ void lambda$createView$0(View r5) {
        if (this.selectedEmojiDocumentId == 0) goto L5;
        return;
    L5:
        if (this.topicForEdit != null) goto L9;
        this.iconColor = this.forumBubbleDrawable.moveNexColor();
        return;
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        this.checkBoxCell.setChecked(!r0.isChecked());
    }

    private /* synthetic */ void lambda$selectEmoji$2() {
        new PremiumFeatureBottomSheet(this, 11, false).show();
    }

    private void selectEmoji(Long r7, boolean r8) {
        if (this.selectAnimatedEmojiDialog != null) goto L5;
        return;
    L5:
        if (this.replaceableIconDrawable != null) goto L8;
        return;
    L8:
        if (r7 != null) goto L10;
        long r2 = 0;
    L11:
        this.selectAnimatedEmojiDialog.setSelected(Long.valueOf(r2));
        if (this.selectedEmojiDocumentId == r2) goto L32;
        if (r8 == false) goto L16;
    L23:
        this.selectedEmojiDocumentId = r2;
        if (r2 == 0) goto L26;
        AnimatedEmojiDrawable r72 = new AnimatedEmojiDrawable(10, this.currentAccount, r2);
        r72.setColorFilter(Theme.chat_animatedEmojiTextColorFilter);
        this.backupImageView[1].setAnimatedEmojiDrawable(r72);
        this.backupImageView[1].setImageDrawable(null);
    L27:
        BackupImageView[] r73 = this.backupImageView;
        BackupImageView r82 = r73[0];
        BackupImageView r22 = r73[1];
        r73[0] = r22;
        r73[1] = r82;
        AndroidUtilities.updateViewVisibilityAnimated(r22, true, 0.5f, true);
        AndroidUtilities.updateViewVisibilityAnimated(this.backupImageView[1], false, 0.5f, true);
        return;
    L26:
        LetterDrawable r74 = new LetterDrawable(null, 1);
        r74.setTitle(this.firstSymbol);
        this.replaceableIconDrawable.setIcon(r74, false);
        this.backupImageView[1].setImageDrawable(this.defaultIconDrawable);
        this.backupImageView[1].setAnimatedEmojiDrawable(null);
        goto L27
    L16:
        if (r2 == 0) goto L23;
        if (getUserConfig().isPremium() == true) goto L23;
        TLRPC.Document r75 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r7.longValue());
        if (r75 == null) goto L30;
        BulletinFactory.of(this).createEmojiBulletin(r75, AndroidUtilities.replaceTags(LocaleController.getString(R.string.UnlockPremiumEmojiHint)), LocaleController.getString(R.string.PremiumMore), new TopicCreateFragment$$ExternalSyntheticLambda2(this)).show();
        return;
    L30:
        return;
    L32:
        return;
    L10:
        r2 = r7.longValue();
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r22) {
        TLRPC.TL_forumTopic r0 = this.topicForEdit;
        ActionBar r2 = this.actionBar;
        if (r0 == null) goto L5;
        r2.setTitle(LocaleController.getString(R.string.EditTopic));
    L6:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        TLRPC.TL_forumTopic r02 = this.topicForEdit;
        ActionBar r23 = this.actionBar;
        if (r02 != null) goto L9;
        r23.createMenu().addItem(1, LocaleController.getString(R.string.Create));
    L10:
        ActionBar r03 = this.actionBar;
        int r24 = Theme.key_windowBackgroundGray;
        r03.setBackgroundColor(getThemedColor(r24));
        this.actionBar.setCastShadows(false);
        AnonymousClass2 r04 = new AnonymousClass2(this, r22);
        this.fragmentView = r04;
        r04.setBackgroundColor(getThemedColor(r24));
        LinearLayout r11 = new LinearLayout(r22);
        r11.setOrientation(1);
        r04.addView(r11);
        HeaderCell r05 = new HeaderCell(r22);
        TLRPC.TL_forumTopic r25 = this.topicForEdit;
        if (r25 != null) goto L13;
    L15:
        r05.setText(LocaleController.getString(R.string.CreateTopicTitle));
    L16:
        FrameLayout r26 = new FrameLayout(r22);
        EditTextBoldCursor r4 = new EditTextBoldCursor(r22);
        this.editTextBoldCursor = r4;
        r4.setHintText(LocaleController.getString(R.string.EnterTopicName));
        this.editTextBoldCursor.setHintColor(getThemedColor(Theme.key_chat_messagePanelHint));
        this.editTextBoldCursor.setTextColor(getThemedColor(Theme.key_chat_messagePanelText));
        this.editTextBoldCursor.setPadding(AndroidUtilities.dp(0.0f), this.editTextBoldCursor.getPaddingTop(), AndroidUtilities.dp(0.0f), this.editTextBoldCursor.getPaddingBottom());
        this.editTextBoldCursor.setBackground(null);
        this.editTextBoldCursor.setSingleLine(true);
        EditTextBoldCursor r42 = this.editTextBoldCursor;
        r42.setInputType(r42.getInputType() | 16384);
        r26.addView(this.editTextBoldCursor, LayoutHelper.createFrame(-1, -1.0f, 0, 51.0f, 4.0f, 21.0f, 4.0f));
        this.editTextBoldCursor.addTextChangedListener(new AnonymousClass3(this));
        AnonymousClass4 r43 = new AnonymousClass4(this, r22);
        r43.setOnClickListener(new TopicCreateFragment$$ExternalSyntheticLambda0(this));
        int r5 = 0;
    L18:
        if (r5 >= 2) goto L20;
        this.backupImageView[r5] = new BackupImageView(r22);
        r43.addView(this.backupImageView[r5], LayoutHelper.createFrame(28, 28, 17));
        r5 = r5 + 1;
        goto L18
    L20:
        r26.addView(r43, LayoutHelper.createFrame(40, 40.0f, 16, 10.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r3 = new LinearLayout(r22);
        r3.setOrientation(1);
        r3.addView(r05);
        r3.addView(r26);
        int r27 = AndroidUtilities.dp(16.0f);
        int r52 = Theme.key_windowBackgroundWhite;
        r3.setBackground(Theme.createRoundRectDrawableShadowed(r27, getThemedColor(r52)));
        r11.addView(r3, LayoutHelper.createLinear(-1, -2, 48, 9, 1, 9, 0));
        FrameLayout r12 = new FrameLayout(r22);
        r12.setClipChildren(false);
        TLRPC.TL_forumTopic r28 = this.topicForEdit;
        if (r28 != null) goto L23;
    L26:
        AnonymousClass5 r06 = new AnonymousClass5(this, this, getContext(), false, null, 3, null);
        this.selectAnimatedEmojiDialog = r06;
        r06.setAnimationsEnabled(this.fragmentBeginToShow);
        this.selectAnimatedEmojiDialog.setClipChildren(false);
        r12.addView(this.selectAnimatedEmojiDialog, LayoutHelper.createFrame(-1, -1.0f, 0, 12.0f, 12.0f, 12.0f, 12.0f));
        Drawable r07 = ForumUtilities.createTopicDrawable(_UrlKt.FRAGMENT_ENCODE_SET, this.iconColor, false);
        this.forumBubbleDrawable = (ForumBubbleDrawable) ((CombinedDrawable) r07).getBackgroundDrawable();
        this.replaceableIconDrawable = new ReplaceableIconDrawable(r22);
        CombinedDrawable r29 = new CombinedDrawable(r07, this.replaceableIconDrawable, 0, 0);
        r29.setFullsize(true);
        this.selectAnimatedEmojiDialog.setForumIconDrawable(r29);
        this.defaultIconDrawable = r29;
        this.replaceableIconDrawable.addView(this.backupImageView[0]);
        this.replaceableIconDrawable.addView(this.backupImageView[1]);
        this.backupImageView[0].setImageDrawable(this.defaultIconDrawable);
        AndroidUtilities.updateViewVisibilityAnimated(this.backupImageView[0], true, 1.0f, false);
        AndroidUtilities.updateViewVisibilityAnimated(this.backupImageView[1], false, 1.0f, false);
        this.forumBubbleDrawable.addParent(this.backupImageView[0]);
        this.forumBubbleDrawable.addParent(this.backupImageView[1]);
    L27:
        r11.addView(r12, LayoutHelper.createFrame(-1, -1.0f));
        TLRPC.TL_forumTopic r08 = this.topicForEdit;
        if (r08 == null) goto L30;
        this.editTextBoldCursor.setText(r08.title);
        selectEmoji(Long.valueOf(this.topicForEdit.icon_emoji_id), true);
    L32:
        return this.fragmentView;
    L30:
        selectEmoji(0L, true);
        goto L32
    L23:
        if (r28.id != 1) goto L26;
        ImageView r210 = new ImageView(r22);
        r210.setImageResource(R.drawable.msg_filled_general);
        r210.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_inMenu), PorterDuff.Mode.MULTIPLY));
        r43.addView(r210, LayoutHelper.createFrame(22, 22, 17));
        r12.addView(new View(r22), LayoutHelper.createFrame(-1, 8.0f));
        FrameLayout r211 = new FrameLayout(r22);
        r211.setBackground(Theme.createRoundRectDrawableShadowed(AndroidUtilities.dp(16.0f), getThemedColor(r52)));
        TextCheckCell2 r09 = new TextCheckCell2(r22);
        this.checkBoxCell = r09;
        r09.getCheckBox().setDrawIconType(0);
        this.checkBoxCell.setTextAndCheck(LocaleController.getString(R.string.EditTopicHide), !this.topicForEdit.hidden, false);
        this.checkBoxCell.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r52), getThemedColor(Theme.key_listSelector), 16, 16));
        this.checkBoxCell.setOnClickListener(new TopicCreateFragment$$ExternalSyntheticLambda1(this));
        r211.addView(this.checkBoxCell, LayoutHelper.createFrame(-1, 50, 119));
        r12.addView(r211, LayoutHelper.createFrame(-1, 56.0f, 48, 9.0f, 8.0f, 9.0f, 0.0f));
        TextInfoPrivacyCell r010 = new TextInfoPrivacyCell(r22);
        r010.setText(LocaleController.getString(R.string.EditTopicHideInfo));
        r12.addView(r010, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, 58.0f, 0.0f, 0.0f));
        goto L27
    L13:
        if (r25.id != 1) goto L15;
        r05.setText(LocaleController.getString(R.string.CreateGeneralTopicTitle));
        goto L16
    L9:
        r23.createMenu().addItem(2, R.drawable.ic_ab_done);
        goto L10
    L5:
        r2.setTitle(LocaleController.getString(R.string.NewTopic));
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.dialogId = -this.arguments.getLong("chat_id");
        long r0 = this.arguments.getLong("topic_id", 0);
        this.topicId = r0;
        if (r0 == 0) goto L9;
        TLRPC.TL_forumTopic r02 = getMessagesController().getTopicsController().findTopic(-this.dialogId, this.topicId);
        this.topicForEdit = r02;
        if (r02 != null) goto L8;
        return false;
    L8:
        this.iconColor = r02.icon_color;
    L11:
        return super.onFragmentCreate();
    L9:
        int[] r03 = ForumBubbleDrawable.serverSupportedColor;
        this.iconColor = r03[Math.abs(Utilities.random.nextInt() % r03.length)];
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        showKeyboard();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        super.onTransitionAnimationEnd(r1, r2);
        if (r1 == false) goto L5;
    L7:
        this.notificationsLocker.unlock();
        SelectAnimatedEmojiDialog r12 = this.selectAnimatedEmojiDialog;
        if (r12 == null) goto L11;
        r12.setAnimationsEnabled(this.fragmentBeginToShow);
        return;
    L11:
        return;
    L5:
        if (this.created == false) goto L7;
        removeSelfFromStack();
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        super.onTransitionAnimationStart(r1, r2);
        if (r1 == false) goto L6;
        this.notificationsLocker.lock();
        return;
    }

    public TopicCreateFragment setOpenInChatActivity(ChatActivity r1) {
        this.openInChatActivity = r1;
        return this;
    }

    public void showKeyboard() {
        this.editTextBoldCursor.requestFocus();
        AndroidUtilities.showKeyboard(this.editTextBoldCursor);
    }
}
