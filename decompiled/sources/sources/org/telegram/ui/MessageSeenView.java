package org.telegram.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.HideViewAfterAnimation;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MessageSeenCheckDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StatusBadgeComponent;

/* loaded from: classes6.dex */
public class MessageSeenView extends FrameLayout {
    AvatarsImageView avatarsImageView;
    int currentAccount;
    ArrayList<Integer> dates;
    FlickerLoadingView flickerLoadingView;
    ImageView iconView;
    boolean ignoreLayout;
    boolean isVoice;
    private RecyclerListView listView;
    ArrayList<Long> peerIds;
    SimpleTextView titleView;
    public ArrayList<TLObject> users;

    /* renamed from: org.telegram.ui.MessageSeenView$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ MessageSeenView this$0;

        public AnonymousClass1(MessageSeenView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r4, int r5) {
            int r52 = View.MeasureSpec.getSize(r5);
            int r0 = AndroidUtilities.dp(4.0f) + (AndroidUtilities.dp(50.0f) * getAdapter().getItemCount());
            if (r0 > r52) goto L6;
            r52 = r0;
        L6:
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.MessageSeenView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.ItemDecoration {
        final /* synthetic */ MessageSeenView this$0;

        public AnonymousClass2(MessageSeenView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            if (r3.getChildAdapterPosition(r2) != (this.this$0.users.size() - 1)) goto L6;
            r1.bottom = AndroidUtilities.dp(4.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.MessageSeenView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ MessageSeenView this$0;

        public AnonymousClass3(MessageSeenView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.users.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            ((UserCell) r2.itemView).setUser(this.this$0.users.get(r3), this.this$0.dates.get(r3).intValue());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            UserCell r1 = new UserCell(r2.getContext());
            r1.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(50.0f)));
            return new RecyclerListView.Holder(r1);
        }
    }

    public static class UserCell extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
        private static MessageSeenCheckDrawable seenDrawable;
        private static MessageSeenCheckDrawable votedDrawable;
        AvatarDrawable avatarDrawable;
        BackupImageView avatarImageView;
        private int currentAccount;
        SimpleTextView nameView;
        TLObject object;
        TextView readView;
        StatusBadgeComponent statusBadgeComponent;

        static {
            int r1 = R.drawable.msg_mini_checks;
            int r2 = Theme.key_windowBackgroundWhiteGrayText;
            seenDrawable = new MessageSeenCheckDrawable(r1, r2);
            votedDrawable = new MessageSeenCheckDrawable(R.drawable.mini_checklist_done_outline, r2);
        }

        public UserCell(Context r9) {
            super(r9);
            this.currentAccount = UserConfig.selectedAccount;
            this.avatarDrawable = new AvatarDrawable();
            BackupImageView r0 = new BackupImageView(r9);
            this.avatarImageView = r0;
            r0.setRoundRadius(ExteraConfig.getAvatarCorners(34.0f));
            SimpleTextView r02 = new SimpleTextView(r9);
            this.nameView = r02;
            r02.setTextSize(16);
            this.nameView.setEllipsizeByGradient(!LocaleController.isRTL);
            this.nameView.setImportantForAccessibility(2);
            this.nameView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
            SimpleTextView r03 = this.nameView;
            int r4 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r3 = 5;
        L6:
            r03.setGravity(r3);
            this.statusBadgeComponent = new StatusBadgeComponent(this);
            this.nameView.setDrawablePadding(AndroidUtilities.dp(3.0f));
            TextView r04 = new TextView(r9);
            this.readView = r04;
            r04.setTextSize(1, 13.0f);
            this.readView.setLines(1);
            this.readView.setEllipsize(TextUtils.TruncateAt.END);
            this.readView.setImportantForAccessibility(2);
            this.readView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            TextView r92 = this.readView;
            if (LocaleController.isRTL == false) goto L9;
            r4 = 5;
        L9:
            r92.setGravity(r4);
            boolean r93 = LocaleController.isRTL;
            BackupImageView r05 = this.avatarImageView;
            if (r93 == false) goto L13;
            addView(r05, LayoutHelper.createFrame(34, 34.0f, 21, 0.0f, 0.0f, 10.0f, 0.0f));
            addView(this.nameView, LayoutHelper.createFrame(-2, -2.0f, 53, 8.0f, 5.33f, 55.0f, 0.0f));
            addView(this.readView, LayoutHelper.createFrame(-2, -2.0f, 53, 13.0f, 19.0f, 55.0f, 0.0f));
            return;
        L13:
            addView(r05, LayoutHelper.createFrame(34, 34.0f, 19, 10.0f, 0.0f, 0.0f, 0.0f));
            addView(this.nameView, LayoutHelper.createFrame(-2, -2.0f, 51, 55.0f, 5.33f, 8.0f, 0.0f));
            addView(this.readView, LayoutHelper.createFrame(-2, -2.0f, 51, 55.0f, 19.0f, 13.0f, 0.0f));
            return;
        L5:
            r3 = 3;
            goto L6
        }

        private void updateStatus(boolean r4) {
            this.nameView.setRightDrawable(this.statusBadgeComponent.updateDrawable(this.object, Theme.getColor(Theme.key_chats_verifiedBackground), r4));
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            if (r3 != NotificationCenter.userEmojiStatusUpdated) goto L14;
            TLRPC.User r32 = (TLRPC.User) r5[0];
            TLObject r42 = this.object;
            if ((r42 instanceof TLRPC.User) == false) goto L7;
            TLRPC.User r43 = (TLRPC.User) r42;
        L8:
            if (r43 == null) goto L15;
            if (r32 != null) goto L11;
            return;
        L11:
            if (r43.id != r32.id) goto L17;
            this.object = r32;
            updateStatus(true);
            return;
        L17:
            return;
        L15:
            return;
        L7:
            r43 = null;
            goto L8
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.statusBadgeComponent.onAttachedToWindow();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.userEmojiStatusUpdated);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.statusBadgeComponent.onDetachedFromWindow();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.userEmojiStatusUpdated);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
            super.onInitializeAccessibilityNodeInfo(r4);
            String r0 = LocaleController.formatString("AccDescrPersonHasSeen", R.string.AccDescrPersonHasSeen, new Object[]{this.nameView.getText()});
            if (this.readView.getVisibility() != 0) goto L5;
            r0 = r0 + " " + this.readView.getText();
        L5:
            r4.setText(r0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void setUser(TLObject r2, int r3) {
            setUser(r2, r3, false);
        }

        public void setUser(TLObject r7, int r8, boolean r9) {
            this.object = r7;
            updateStatus(false);
            if (r7 == null) goto L5;
            this.avatarDrawable.setInfo(this.currentAccount, r7);
            this.avatarImageView.setImage(ImageLocation.getForUserOrChat(this.currentAccount, r7, 1), "50_50", this.avatarDrawable, r7);
            this.nameView.setText(ContactsController.formatName(r7));
        L5:
            TextView r72 = this.readView;
            if (r8 > 0) goto L9;
            r72.setVisibility(8);
            this.nameView.setTranslationY(AndroidUtilities.dp(9.0f));
            return;
        L9:
            if (r9 == false) goto L11;
            MessageSeenCheckDrawable r92 = votedDrawable;
        L12:
            r72.setText(TextUtils.concat(new CharSequence[]{r92.getSpanned(getContext(), null), LocaleController.formatSeenDate(r8)}));
            this.readView.setVisibility(0);
            this.nameView.setTranslationY(0.0f);
            return;
        L11:
            r92 = seenDrawable;
            goto L12
        }
    }

    /* renamed from: $r8$lambda$0X1wkrCpC8sXPF0Fa-4wTCqXrmU, reason: not valid java name */
    public static /* synthetic */ void m16109$r8$lambda$0X1wkrCpC8sXPF0Fa4wTCqXrmU(MessageSeenView r0, int r1, HashMap r2, ArrayList r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$new$1(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$b3Rbbzbuw3GSg4hyrcPTvrQpe00(MessageSeenView r0, TLRPC.TL_error r1, TLObject r2, long r3, int r5, TLRPC.Chat r6) {
        r0.lambda$new$4(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$d4SGR8LFlnqLnFqGIKZH4lsHLc4(MessageSeenView r0, int r1, HashMap r2, ArrayList r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$new$3(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$eJXkr4v-0wJMS8yAhyhIQ_Rfw6o, reason: not valid java name */
    public static /* synthetic */ void m16110$r8$lambda$eJXkr4v0wJMS8yAhyhIQ_Rfw6o(MessageSeenView r0, long r1, int r3, TLRPC.Chat r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$new$5(r1, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$gIRvuHywNiVI9o31HjESsxIPjhI(MessageSeenView r0, TLObject r1, int r2, HashMap r3, ArrayList r4) {
        r0.lambda$new$0(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$zcfCPgunUn9J10KOYFMrpX7hBeI(MessageSeenView r0, TLObject r1, int r2, HashMap r3, ArrayList r4) {
        r0.lambda$new$2(r1, r2, r3, r4);
    }

    public MessageSeenView(Context r12, int r13, MessageObject r14, TLRPC.Chat r15) {
        super(r12);
        this.peerIds = new ArrayList();
        this.dates = new ArrayList();
        this.users = new ArrayList();
        this.currentAccount = r13;
        if (r14.isRoundVideo() == false) goto L5;
    L8:
        boolean r0 = true;
    L9:
        this.isVoice = r0;
        FlickerLoadingView r02 = new FlickerLoadingView(r12);
        this.flickerLoadingView = r02;
        r02.setColors(Theme.key_actionBarDefaultSubmenuBackground, Theme.key_listSelector, -1);
        this.flickerLoadingView.setViewType(13);
        this.flickerLoadingView.setIsSingleCell(false);
        addView(this.flickerLoadingView, LayoutHelper.createFrame(-2, -1.0f));
        SimpleTextView r03 = new SimpleTextView(r12);
        this.titleView = r03;
        r03.setTextSize(16);
        this.titleView.setEllipsizeByGradient(true);
        this.titleView.setRightPadding(AndroidUtilities.dp(62.0f));
        addView(this.titleView, LayoutHelper.createFrame(0, -2.0f, 19, 40.0f, 0.0f, 0.0f, 0.0f));
        AvatarsImageView r04 = new AvatarsImageView(r12, false);
        this.avatarsImageView = r04;
        r04.setStyle(11);
        this.avatarsImageView.setAvatarsTextSize(AndroidUtilities.dp(22.0f));
        addView(this.avatarsImageView, LayoutHelper.createFrame(56, -1.0f, 21, 0.0f, 0.0f, 0.0f, 0.0f));
        this.titleView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        TLRPC.TL_messages_getMessageReadParticipants r05 = new TLRPC.TL_messages_getMessageReadParticipants();
        r05.msg_id = r14.getId();
        r05.peer = MessagesController.getInstance(r13).getInputPeer(r14.getDialogId());
        ImageView r1 = new ImageView(r12);
        this.iconView = r1;
        addView(r1, LayoutHelper.createFrame(24, 24.0f, 19, 11.0f, 0.0f, 0.0f, 0.0f));
        if (this.isVoice == false) goto L12;
        int r16 = R.drawable.msg_played;
    L13:
        Drawable r122 = ContextCompat.getDrawable(r12, r16).mutate();
        r122.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.MULTIPLY));
        this.iconView.setImageDrawable(r122);
        this.avatarsImageView.setAlpha(0.0f);
        this.titleView.setAlpha(0.0f);
        TLRPC.Peer r123 = r14.messageOwner.from_id;
        if (r123 == null) goto L17;
        long r3 = r123.user_id;
    L18:
        ConnectionsManager.getInstance(r13).sendRequest(r05, new MessageSeenView$$ExternalSyntheticLambda0(this, r3, r13, r15));
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector), 6, 0));
        setEnabled(false);
        return;
    L17:
        r3 = 0;
        goto L18
    L12:
        r16 = R.drawable.msg_seen;
        goto L13
    L5:
        if (r14.isVoice() == true) goto L8;
        r0 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$0(TLObject r6, int r7, HashMap r8, ArrayList r9) {
        if (r6 == null) goto L10;
        TLRPC.TL_channels_channelParticipants r62 = (TLRPC.TL_channels_channelParticipants) r6;
        int r0 = 0;
        int r1 = 0;
    L5:
        if (r1 >= r62.users.size()) goto L8;
        TLRPC.User r2 = r62.users.get(r1);
        MessagesController.getInstance(r7).putUser(r2, false);
        r8.put(Long.valueOf(r2.id), r2);
        r1 = r1 + 1;
    L8:
        if (r0 >= r9.size()) goto L10;
        Pair r63 = (Pair) r9.get(r0);
        this.peerIds.add((Long) r63.first);
        this.dates.add((Integer) r63.second);
        this.users.add((TLObject) r8.get(r63.first));
        r0 = r0 + 1;
    L10:
        updateView();
    }

    private /* synthetic */ void lambda$new$1(int r2, HashMap r3, ArrayList r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new MessageSeenView$$ExternalSyntheticLambda4(this, r5, r2, r3, r4));
    }

    private /* synthetic */ void lambda$new$2(TLObject r6, int r7, HashMap r8, ArrayList r9) {
        if (r6 == null) goto L10;
        TLRPC.TL_messages_chatFull r62 = (TLRPC.TL_messages_chatFull) r6;
        int r0 = 0;
        int r1 = 0;
    L5:
        if (r1 >= r62.users.size()) goto L8;
        TLRPC.User r2 = r62.users.get(r1);
        MessagesController.getInstance(r7).putUser(r2, false);
        r8.put(Long.valueOf(r2.id), r2);
        r1 = r1 + 1;
    L8:
        if (r0 >= r9.size()) goto L10;
        Pair r63 = (Pair) r9.get(r0);
        this.peerIds.add((Long) r63.first);
        this.dates.add((Integer) r63.second);
        this.users.add((TLObject) r8.get(r63.first));
        r0 = r0 + 1;
    L10:
        updateView();
    }

    private /* synthetic */ void lambda$new$3(int r2, HashMap r3, ArrayList r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new MessageSeenView$$ExternalSyntheticLambda5(this, r5, r2, r3, r4));
    }

    private /* synthetic */ void lambda$new$4(TLRPC.TL_error r18, TLObject r19, long r20, int r22, TLRPC.Chat r23) {
        int r4 = 0;
        if (r18 == null) goto L5;
    L37:
        updateView();
        return;
    L5:
        if ((r19 instanceof Vector) == false) goto L37;
        Vector r1 = (Vector) r19;
        ArrayList r6 = new ArrayList();
        ArrayList r7 = new ArrayList();
        HashMap r8 = new HashMap();
        ArrayList r9 = new ArrayList();
        int r10 = r1.objects.size();
        int r11 = 0;
    L7:
        if (r11 >= r10) goto L25;
        Object r12 = r1.objects.get(r11);
        if ((r12 instanceof TLRPC.TL_readParticipantDate) == false) goto L15;
        TLRPC.TL_readParticipantDate r122 = (TLRPC.TL_readParticipantDate) r12;
        int r13 = r122.date;
        long r14 = r122.user_id;
        Long r123 = Long.valueOf(r14);
        if (r20 == r14) goto L23;
        MessagesController.getInstance(r22).getUser(r123);
        r9.add(new Pair(r123, Integer.valueOf(r13)));
        r6.add(r123);
    L23:
        r11 = r11 + 1;
        goto L7
    L15:
        if ((r12 instanceof Long) == false) goto L23;
        Long r124 = (Long) r12;
        if (r20 == r124.longValue()) goto L23;
        if (r124.longValue() <= 0) goto L22;
        MessagesController.getInstance(r22).getUser(r124);
        r9.add(new Pair(r124, 0));
        r6.add(r124);
        goto L23
    L22:
        MessagesController.getInstance(r22).getChat(Long.valueOf(-r124.longValue()));
        r9.add(new Pair(r124, 0));
        r7.add(r124);
        goto L23
    L25:
        if (r6.isEmpty() == false) goto L32;
    L27:
        if (r4 >= r9.size()) goto L29;
        Pair r15 = (Pair) r9.get(r4);
        this.peerIds.add((Long) r15.first);
        this.dates.add((Integer) r15.second);
        this.users.add((TLObject) r8.get(r15.first));
        r4 = r4 + 1;
        goto L27
    L29:
        updateView();
        return;
    L32:
        if (ChatObject.isChannel(r23) == false) goto L35;
        TLRPC.TL_channels_getParticipants r16 = new TLRPC.TL_channels_getParticipants();
        r16.limit = MessagesController.getInstance(r22).chatReadMarkSizeThreshold;
        r16.offset = 0;
        r16.filter = new TLRPC.TL_channelParticipantsRecent();
        r16.channel = MessagesController.getInstance(r22).getInputChannel(r23.id);
        ConnectionsManager.getInstance(r22).sendRequest(r16, new MessageSeenView$$ExternalSyntheticLambda2(this, r22, r8, r9));
        return;
    L35:
        TLRPC.TL_messages_getFullChat r17 = new TLRPC.TL_messages_getFullChat();
        r17.chat_id = r23.id;
        ConnectionsManager.getInstance(r22).sendRequest(r17, new MessageSeenView$$ExternalSyntheticLambda3(this, r22, r8, r9));
    }

    private /* synthetic */ void lambda$new$5(long r9, int r11, TLRPC.Chat r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new MessageSeenView$$ExternalSyntheticLambda1(this, r14, r13, r9, r11, r12));
    }

    private void updateView() {
        if (this.users.size() <= 0) goto L5;
        boolean r0 = true;
    L6:
        setEnabled(r0);
        int r02 = 0;
    L7:
        ArrayList<TLObject> r3 = this.users;
        if (r02 >= 3) goto L15;
        int r32 = r3.size();
        AvatarsImageView r4 = this.avatarsImageView;
        if (r02 >= r32) goto L12;
        r4.setObject(r02, this.currentAccount, this.users.get(r02));
    L13:
        r02 = r02 + 1;
        goto L7
    L12:
        r4.setObject(r02, this.currentAccount, null);
        goto L13
    L15:
        if (r3.size() != 1) goto L17;
        this.avatarsImageView.setTranslationX(AndroidUtilities.dp(24.0f));
    L21:
        this.titleView.setRightPadding(AndroidUtilities.dp((Math.min(2, this.users.size() - 1) * 12) + 38));
        this.avatarsImageView.commitTransition(false);
        if (this.peerIds.size() == 1) goto L24;
    L26:
        int r03 = this.peerIds.size();
        SimpleTextView r1 = this.titleView;
        if (r03 != 0) goto L30;
        r1.setText(LocaleController.getString(R.string.NobodyViewed));
    L34:
        this.titleView.animate().alpha(1.0f).setDuration(220).start();
        this.avatarsImageView.animate().alpha(1.0f).setDuration(220).start();
        this.flickerLoadingView.animate().alpha(0.0f).setDuration(220).setListener(new HideViewAfterAnimation(this.flickerLoadingView)).start();
        RecyclerListView r6 = this.listView;
        if (r6 == null) goto L41;
        r6.getAdapter();
        return;
    L41:
        return;
    L30:
        if (this.isVoice == false) goto L32;
        String r04 = "MessagePlayed";
    L33:
        r1.setText(LocaleController.formatPluralString(r04, this.peerIds.size(), new Object[0]));
        goto L34
    L32:
        r04 = "MessageSeen";
        goto L33
    L24:
        if (this.users.get(0) == null) goto L26;
        this.titleView.setText(ContactsController.formatName(this.users.get(0)));
        goto L34
    L17:
        int r05 = this.users.size();
        AvatarsImageView r5 = this.avatarsImageView;
        if (r05 != 2) goto L20;
        r5.setTranslationX(AndroidUtilities.dp(12.0f));
        goto L21
    L20:
        r5.setTranslationX(0.0f);
        goto L21
    L5:
        r0 = false;
        goto L6
    }

    public RecyclerListView createListView() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L5;
        return r0;
    L5:
        AnonymousClass1 r02 = new AnonymousClass1(this, getContext());
        this.listView = r02;
        r02.setLayoutManager(new LinearLayoutManager(getContext()));
        this.listView.addItemDecoration(new AnonymousClass2(this));
        this.listView.setAdapter(new AnonymousClass3(this));
        return this.listView;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        View r0 = (View) getParent();
        if (r0 != null) goto L5;
    L7:
        boolean r02 = true;
        this.ignoreLayout = true;
        if (this.flickerLoadingView.getVisibility() == 0) goto L11;
        r02 = false;
    L11:
        this.titleView.setVisibility(8);
        if (r02 == false) goto L14;
        this.flickerLoadingView.setVisibility(8);
    L14:
        super.onMeasure(r5, r6);
        if (r02 == false) goto L17;
        this.flickerLoadingView.getLayoutParams().width = getMeasuredWidth();
        this.flickerLoadingView.setVisibility(0);
    L17:
        this.titleView.setVisibility(0);
        this.titleView.getLayoutParams().width = getMeasuredWidth() - AndroidUtilities.dp(40.0f);
        this.ignoreLayout = false;
        super.onMeasure(r5, r6);
        return;
    L5:
        if (r0.getWidth() <= 0) goto L7;
        r5 = View.MeasureSpec.makeMeasureSpec(r0.getWidth(), TLObject.FLAG_30);
        goto L7
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }
}
