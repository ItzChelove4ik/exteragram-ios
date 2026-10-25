package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ReactedHeaderView extends FrameLayout {
    private AvatarsImageView avatarsImageView;
    private int currentAccount;
    private long dialogId;
    private int fixedWidth;
    private FlickerLoadingView flickerLoadingView;
    private ImageView iconView;
    private boolean ignoreLayout;
    private boolean isLoaded;
    private MessageObject message;
    private BackupImageView reactView;
    private Consumer<List<UserSeen>> seenCallback;
    private List<UserSeen> seenUsers;
    private TextView titleView;
    private List<UserSeen> users;

    public static class UserSeen {
        public int date;
        long dialogId;
        public TLObject user;

        public UserSeen(TLObject r1, int r2) {
            this.user = r1;
            this.date = r2;
            if ((r1 instanceof TLRPC.User) == false) goto L7;
            this.dialogId = ((TLRPC.User) r1).id;
            return;
        L7:
            if ((r1 instanceof TLRPC.Chat) == false) goto L10;
            this.dialogId = -((TLRPC.Chat) r1).id;
            return;
        }
    }

    /* renamed from: $r8$lambda$8_BPe1iacfs5GPH3RBd-eyHnAY4, reason: not valid java name */
    public static /* synthetic */ void m12290$r8$lambda$8_BPe1iacfs5GPH3RBdeyHnAY4(ReactedHeaderView r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadReactions$7(r1, r2);
    }

    /* renamed from: $r8$lambda$947Ea0vg5lPz-V7Z11-UwxLMiDw, reason: not valid java name */
    public static /* synthetic */ void m12291$r8$lambda$947Ea0vg5lPzV7Z11UwxLMiDw(ReactedHeaderView r0, TLObject r1, List r2, List r3, List r4, Runnable r5) {
        r0.lambda$onAttachedToWindow$1(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$BS-ZSHO70oq4bTYJR3rHB5PT-T0, reason: not valid java name */
    public static /* synthetic */ void m12292$r8$lambda$BSZSHO70oq4bTYJR3rHB5PTT0(ReactedHeaderView r0, List r1, List r2, List r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$onAttachedToWindow$2(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$DSbHacLPp0RJl_2x7kUvY0nNLa8(ReactedHeaderView r0, long r1, TLRPC.Chat r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$onAttachedToWindow$5(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$fy5isu5f9VnGwPMpn_rrbJrjty0(ReactedHeaderView r0, List r1, List r2, List r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$onAttachedToWindow$4(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$isakaZGBQJKDgyW1sxcHy-Be3VY, reason: not valid java name */
    public static /* synthetic */ void m12293$r8$lambda$isakaZGBQJKDgyW1sxcHyBe3VY(ReactedHeaderView r0, List r1) {
        r0.lambda$onAttachedToWindow$0(r1);
    }

    /* renamed from: $r8$lambda$tyySU3G8WWwG05Odf-njZMrLkWs, reason: not valid java name */
    public static /* synthetic */ void m12294$r8$lambda$tyySU3G8WWwG05OdfnjZMrLkWs(ReactedHeaderView r0, int r1, TLRPC.TL_messages_messageReactionsList r2) {
        r0.lambda$loadReactions$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zOfqwfFUIpp59o1iJ3fl29kUI58(ReactedHeaderView r0, TLObject r1, List r2, List r3, List r4, Runnable r5) {
        r0.lambda$onAttachedToWindow$3(r1, r2, r3, r4, r5);
    }

    public ReactedHeaderView(Context r8, int r9, MessageObject r10, long r11) {
        super(r8);
        this.seenUsers = new ArrayList();
        this.users = new ArrayList();
        this.currentAccount = r9;
        this.message = r10;
        this.dialogId = r11;
        FlickerLoadingView r92 = new FlickerLoadingView(r8);
        this.flickerLoadingView = r92;
        r92.setColors(Theme.key_actionBarDefaultSubmenuBackground, Theme.key_listSelector, -1);
        this.flickerLoadingView.setViewType(13);
        this.flickerLoadingView.setIsSingleCell(false);
        addView(this.flickerLoadingView, LayoutHelper.createFrame(-2, -1.0f));
        TextView r93 = new TextView(r8);
        this.titleView = r93;
        r93.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        this.titleView.setTextSize(1, 16.0f);
        this.titleView.setLines(1);
        this.titleView.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.titleView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 40.0f, 0.0f, 62.0f, 0.0f));
        AvatarsImageView r94 = new AvatarsImageView(r8, false);
        this.avatarsImageView = r94;
        r94.setStyle(11);
        this.avatarsImageView.setAvatarsTextSize(AndroidUtilities.dp(22.0f));
        addView(this.avatarsImageView, LayoutHelper.createFrameRelatively(56.0f, -1.0f, 8388629, 0.0f, 0.0f, 0.0f, 0.0f));
        ImageView r95 = new ImageView(r8);
        this.iconView = r95;
        addView(r95, LayoutHelper.createFrameRelatively(24.0f, 24.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 11.0f, 0.0f, 0.0f, 0.0f));
        Drawable r96 = ContextCompat.getDrawable(r8, R.drawable.msg_reactions).mutate();
        r96.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon), PorterDuff.Mode.MULTIPLY));
        this.iconView.setImageDrawable(r96);
        this.iconView.setVisibility(8);
        BackupImageView r97 = new BackupImageView(r8);
        this.reactView = r97;
        addView(r97, LayoutHelper.createFrameRelatively(24.0f, 24.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 11.0f, 0.0f, 0.0f, 0.0f));
        this.titleView.setAlpha(0.0f);
        this.avatarsImageView.setAlpha(0.0f);
        setBackground(Theme.getSelectorDrawable(false));
    }

    private /* synthetic */ void lambda$loadReactions$6(int r11, TLRPC.TL_messages_messageReactionsList r12) {
        if (this.seenUsers.isEmpty() == false) goto L5;
    L12:
        String r112 = LocaleController.formatPluralString("ReactionsCount", r11, new Object[0]);
    L14:
        if (getMeasuredWidth() <= 0) goto L16;
        this.fixedWidth = getMeasuredWidth();
    L16:
        this.titleView.setText(r112);
        TLRPC.TL_messageReactions r113 = this.message.messageOwner.reactions;
        if (r113 != null) goto L19;
    L28:
        this.iconView.setVisibility(0);
        this.iconView.setAlpha(0.0f);
        this.iconView.animate().alpha(1.0f).start();
    L29:
        ArrayList<TLRPC.User> r114 = r12.users;
        int r0 = r114.size();
        int r2 = 0;
    L30:
        if (r2 >= r0) goto L43;
        TLRPC.User r3 = r114.get(r2);
        r2 = r2 + 1;
        TLRPC.User r32 = r3;
        TLRPC.Peer r4 = this.message.messageOwner.from_id;
        if (r4 == null) goto L30;
        if (r32.id == r4.user_id) goto L30;
        int r42 = 0;
    L36:
        int r5 = this.users.size();
        List<UserSeen> r6 = this.users;
        if (r42 >= r5) goto L42;
        if (r6.get(r42).dialogId == r32.id) goto L30;
        r42 = r42 + 1;
        goto L36
    L42:
        r6.add(new UserSeen(r32, 0));
        goto L30
    L43:
        ArrayList<TLRPC.Chat> r115 = r12.chats;
        int r122 = r115.size();
        int r02 = 0;
    L44:
        if (r02 >= r122) goto L57;
        TLRPC.Chat r22 = r115.get(r02);
        r02 = r02 + 1;
        TLRPC.Chat r23 = r22;
        TLRPC.Peer r33 = this.message.messageOwner.from_id;
        if (r33 == null) goto L44;
        if (r23.id == r33.user_id) goto L44;
        int r34 = 0;
    L50:
        int r43 = this.users.size();
        List<UserSeen> r52 = this.users;
        if (r34 >= r43) goto L56;
        if (r52.get(r34).dialogId == (-r23.id)) goto L44;
        r34 = r34 + 1;
        goto L50
    L56:
        r52.add(new UserSeen(r23, 0));
        goto L44
    L57:
        updateView();
        return;
    L19:
        if (r113.results.size() != 1) goto L28;
        if (r12.reactions.isEmpty() == true) goto L28;
        Iterator<TLRPC.TL_availableReaction> r116 = MediaDataController.getInstance(this.currentAccount).getReactionsList().iterator();
    L24:
        if (r116.hasNext() == false) goto L28;
        TLRPC.TL_availableReaction r9 = r116.next();
        if (r9.reaction.equals(r12.reactions.get(0).reaction) == false) goto L24;
        this.reactView.setImage(ImageLocation.getForDocument(r9.center_icon), "40_40_lastreactframe", "webp", null, r9);
        this.reactView.setVisibility(0);
        this.reactView.setAlpha(0.0f);
        this.reactView.animate().alpha(1.0f).start();
        this.iconView.setVisibility(8);
        goto L29
    L5:
        if (this.seenUsers.size() < r11) goto L12;
        if (r11 != this.seenUsers.size()) goto L10;
        String r03 = String.valueOf(r11);
    L11:
        r112 = String.format(LocaleController.getPluralString("Reacted", r11), new Object[]{r03});
        goto L14
    L10:
        r03 = r11 + "/" + this.seenUsers.size();
        goto L11
    }

    private /* synthetic */ void lambda$loadReactions$7(TLObject r2, TLRPC.TL_error r3) {
        if ((r2 instanceof TLRPC.TL_messages_messageReactionsList) == false) goto L6;
        TLRPC.TL_messages_messageReactionsList r22 = (TLRPC.TL_messages_messageReactionsList) r2;
        int r32 = r22.count;
        r22.users.size();
        post(new ReactedHeaderView$$ExternalSyntheticLambda6(this, r32, r22));
        return;
    }

    private /* synthetic */ void lambda$onAttachedToWindow$0(List r8) {
        this.seenUsers.addAll(r8);
        Iterator r0 = r8.iterator();
    L4:
        if (r0.hasNext() == false) goto L15;
        UserSeen r1 = (UserSeen) r0.next();
        int r2 = 0;
    L6:
        int r3 = this.users.size();
        List<UserSeen> r4 = this.users;
        if (r2 >= r3) goto L14;
        if (MessageObject.getObjectPeerId(r4.get(r2).user) == MessageObject.getObjectPeerId(r1.user)) goto L11;
        r2 = r2 + 1;
        goto L6
    L11:
        if (r1.date <= 0) goto L4;
        UserSeen r22 = this.users.get(r2);
        r22.date = r1.date;
        goto L4
    L14:
        r4.add(r1);
        goto L4
    L15:
        Consumer<List<UserSeen>> r02 = this.seenCallback;
        if (r02 == null) goto L18;
        r02.accept(r8);
    L18:
        loadReactions();
    }

    private /* synthetic */ void lambda$onAttachedToWindow$1(TLObject r6, List r7, List r8, List r9, Runnable r10) {
        if (r6 == null) goto L11;
        TLRPC.TL_channels_channelParticipants r62 = (TLRPC.TL_channels_channelParticipants) r6;
        int r1 = 0;
    L5:
        if (r1 >= r62.users.size()) goto L11;
        TLRPC.User r2 = r62.users.get(r1);
        MessagesController.getInstance(this.currentAccount).putUser(r2, false);
        int r3 = r7.indexOf(Long.valueOf(r2.id));
        if (r2.self == true) goto L10;
        if (r3 < 0) goto L10;
        r8.add(new UserSeen(r2, ((Integer) r9.get(r3)).intValue()));
    L10:
        r1 = r1 + 1;
    L11:
        r10.run();
    }

    private /* synthetic */ void lambda$onAttachedToWindow$2(List r1, List r2, List r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new ReactedHeaderView$$ExternalSyntheticLambda5(this, r5, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$onAttachedToWindow$3(TLObject r6, List r7, List r8, List r9, Runnable r10) {
        if (r6 == null) goto L11;
        TLRPC.TL_messages_chatFull r62 = (TLRPC.TL_messages_chatFull) r6;
        int r1 = 0;
    L5:
        if (r1 >= r62.users.size()) goto L11;
        TLRPC.User r2 = r62.users.get(r1);
        MessagesController.getInstance(this.currentAccount).putUser(r2, false);
        int r3 = r7.indexOf(Long.valueOf(r2.id));
        if (r2.self == true) goto L10;
        if (r3 < 0) goto L10;
        r8.add(new UserSeen(r2, ((Integer) r9.get(r3)).intValue()));
    L10:
        r1 = r1 + 1;
    L11:
        r10.run();
    }

    private /* synthetic */ void lambda$onAttachedToWindow$4(List r1, List r2, List r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new ReactedHeaderView$$ExternalSyntheticLambda7(this, r5, r1, r2, r3, r4));
    }

    private /* synthetic */ void lambda$onAttachedToWindow$5(long r10, TLRPC.Chat r12, TLObject r13, TLRPC.TL_error r14) {
        if ((r13 instanceof Vector) == false) goto L34;
        ArrayList r4 = new ArrayList();
        ArrayList r6 = new ArrayList();
        ArrayList<T> r132 = ((Vector) r13).objects;
        int r1 = r132.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L16;
        Object r3 = r132.get(r2);
        r2 = r2 + 1;
        if ((r3 instanceof Long) == true) goto L8;
        if ((r3 instanceof TLRPC.TL_readParticipantDate) == false) goto L5;
        TLRPC.TL_readParticipantDate r32 = (TLRPC.TL_readParticipantDate) r3;
        long r7 = r32.user_id;
        int r33 = r32.date;
        if (r10 == r7) goto L5;
        r4.add(Long.valueOf(r7));
        r6.add(Integer.valueOf(r33));
        goto L5
    L8:
        Long r34 = (Long) r3;
        if (r10 == r34.longValue()) goto L5;
        r4.add(r34);
        r6.add(0);
        goto L5
    L16:
        r4.add(Long.valueOf(r10));
        r6.add(0);
        ArrayList r5 = new ArrayList();
        ReactedHeaderView$$ExternalSyntheticLambda2 r72 = new ReactedHeaderView$$ExternalSyntheticLambda2(this, r5);
        if (ChatObject.isChannel(r12) == false) goto L20;
        TLRPC.TL_channels_getParticipants r102 = new TLRPC.TL_channels_getParticipants();
        r102.limit = MessagesController.getInstance(this.currentAccount).chatReadMarkSizeThreshold;
        r102.offset = 0;
        r102.filter = new TLRPC.TL_channelParticipantsRecent();
        r102.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(r12.id);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r102, new ReactedHeaderView$$ExternalSyntheticLambda3(this, r4, r5, r6, r72));
        return;
    L20:
        TLRPC.TL_messages_getFullChat r9 = new TLRPC.TL_messages_getFullChat();
        r9.chat_id = r12.id;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new ReactedHeaderView$$ExternalSyntheticLambda4(this, r4, r5, r6, r72));
        return;
    }

    private void loadReactions() {
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        TLRPC.TL_messages_getMessageReactionsList r1 = new TLRPC.TL_messages_getMessageReactionsList();
        r1.peer = r0.getInputPeer(this.message.getDialogId());
        r1.id = this.message.getId();
        r1.limit = 3;
        r1.reaction = null;
        r1.offset = null;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new ReactedHeaderView$$ExternalSyntheticLambda1(this), 64);
    }

    private void updateView() {
        if (this.users.size() <= 0) goto L5;
        boolean r0 = true;
    L6:
        setEnabled(r0);
        int r02 = 0;
    L7:
        List<UserSeen> r3 = this.users;
        if (r02 >= 3) goto L14;
        int r32 = r3.size();
        AvatarsImageView r4 = this.avatarsImageView;
        if (r02 >= r32) goto L12;
        r4.setObject(r02, this.currentAccount, this.users.get(r02).user);
    L13:
        r02 = r02 + 1;
        goto L7
    L12:
        r4.setObject(r02, this.currentAccount, null);
        goto L13
    L14:
        int r03 = r3.size();
        if (r03 != 1) goto L17;
        int r04 = AndroidUtilities.dp(24.0f);
    L20:
        float r05 = r04;
    L22:
        AvatarsImageView r2 = this.avatarsImageView;
        if (LocaleController.isRTL == false) goto L25;
        r05 = AndroidUtilities.dp(12.0f);
    L25:
        r2.setTranslationX(r05);
        this.avatarsImageView.commitTransition(false);
        this.titleView.animate().alpha(1.0f).setDuration(220).start();
        this.avatarsImageView.animate().alpha(1.0f).setDuration(220).start();
        this.flickerLoadingView.animate().alpha(0.0f).setDuration(220).setListener(new HideViewAfterAnimation(this.flickerLoadingView)).start();
        return;
    L17:
        if (r03 == 2) goto L19;
        r05 = 0.0f;
        goto L22
    L19:
        r04 = AndroidUtilities.dp(12.0f);
        goto L20
    L5:
        r0 = false;
        goto L6
    }

    public List<UserSeen> getSeenUsers() {
        return this.seenUsers;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isLoaded == true) goto L39;
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        TLRPC.Chat r1 = r0.getChat(Long.valueOf(this.message.getChatId()));
        TLRPC.ChatFull r02 = r0.getChatFull(this.message.getChatId());
        if (r1 != null) goto L7;
    L37:
        loadReactions();
        return;
    L7:
        if (this.message.isOutOwner() == false) goto L37;
        if (this.message.isSent() == false) goto L37;
        if (this.message.isEditing() == true) goto L37;
        if (this.message.isSending() == true) goto L37;
        if (this.message.isSendError() == true) goto L37;
        if (this.message.isContentUnread() == true) goto L37;
        if (this.message.isUnread() == true) goto L37;
        if ((ConnectionsManager.getInstance(this.currentAccount).getCurrentTime() - this.message.messageOwner.date) >= 604800) goto L37;
        if (ChatObject.isMegagroup(r1) == false) goto L25;
    L26:
        if (r02 == null) goto L37;
        if (r02.participants_count > MessagesController.getInstance(this.currentAccount).chatReadMarkSizeThreshold) goto L37;
        if ((this.message.messageOwner.action instanceof TLRPC.TL_messageActionChatJoinedByRequest) == true) goto L37;
        TLRPC.TL_messages_getMessageReadParticipants r03 = new TLRPC.TL_messages_getMessageReadParticipants();
        r03.msg_id = this.message.getId();
        r03.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.message.getDialogId());
        TLRPC.Peer r2 = this.message.messageOwner.from_id;
        if (r2 == null) goto L34;
        long r22 = r2.user_id;
    L35:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new ReactedHeaderView$$ExternalSyntheticLambda0(this, r22, r1), 64);
        return;
    L34:
        r22 = 0;
        goto L35
    L25:
        if (ChatObject.isChannel(r1) == true) goto L37;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        int r0 = this.fixedWidth;
        if (r0 <= 0) goto L6;
        r3 = View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30);
    L6:
        if (this.flickerLoadingView.getVisibility() != 0) goto L9;
        this.ignoreLayout = true;
        this.flickerLoadingView.setVisibility(8);
        super.onMeasure(r3, r4);
        ViewGroup.LayoutParams r02 = this.flickerLoadingView.getLayoutParams();
        r02.width = getMeasuredWidth();
        this.flickerLoadingView.setVisibility(0);
        this.ignoreLayout = false;
        super.onMeasure(r3, r4);
        return;
    L9:
        super.onMeasure(r3, r4);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void setSeenCallback(Consumer<List<UserSeen>> r1) {
        this.seenCallback = r1;
    }
}
