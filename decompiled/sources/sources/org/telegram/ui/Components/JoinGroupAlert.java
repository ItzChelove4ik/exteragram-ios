package org.telegram.ui.Components;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotGuardHelper;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes7.dex */
public class JoinGroupAlert extends BottomSheet {
    private BulletinFactory bulletinFactory;
    private TLRPC.ChatInvite chatInvite;
    private TLRPC.Chat currentChat;
    private final BaseFragment fragment;
    private final String hash;
    private RadialProgressView requestProgressView;
    private TextView requestTextView;

    /* renamed from: org.telegram.ui.Components.JoinGroupAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends ChatActivity {
        private boolean shownToast;
        final /* synthetic */ JoinGroupAlert this$0;
        final /* synthetic */ long val$chatId;
        final /* synthetic */ boolean val$showJoined;

        public static /* synthetic */ void $r8$lambda$5TOeQmOYh7QQCaPfFpeVdtT6WFM(AnonymousClass1 r0, long r1, TLRPC.Chat r3) {
            r0.lambda$onBecomeFullyVisible$0(r1, r3);
        }

        public AnonymousClass1(JoinGroupAlert r1, Bundle r2, boolean r3, long r4) {
            this.this$0 = r1;
            this.val$showJoined = r3;
            this.val$chatId = r4;
            super(r2);
            this.shownToast = false;
        }

        private /* synthetic */ void lambda$onBecomeFullyVisible$0(long r11, TLRPC.Chat r13) {
            if (AndroidUtilities.isContextSafe(getContext()) == true) goto L5;
            return;
        L5:
            Context r1 = getContext();
            int r2 = this.currentAccount;
            long r3 = -r11;
            TLRPC.User r5 = getUserConfig().getCurrentUser();
            if (r13.admin_rights == null) goto L9;
            boolean r112 = true;
        L8:
            boolean r7 = r112;
            TagEditCell.showSheet(r1, r2, r3, r5, null, r7, r13.creator, JoinGroupAlert.access$000(this.this$0));
            return;
        L9:
            r112 = false;
            goto L8
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onBecomeFullyVisible() {
            super.onBecomeFullyVisible();
            if (this.shownToast == false) goto L5;
            return;
        L5:
            if (this.val$showJoined == false) goto L13;
            this.shownToast = true;
            TLRPC.Chat r1 = getMessagesController().getChat(Long.valueOf(this.val$chatId));
            if (ChatObject.canManageMyTag(r1) == false) goto L10;
            BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.JoinedGroup), LocaleController.getString(R.string.JoinedGroupAddTag), new JoinGroupAlert$1$$ExternalSyntheticLambda0(this, this.val$chatId, r1)).hideAfterBottomSheet(false).show(true);
            return;
        L10:
            BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.JoinedGroup)).hideAfterBottomSheet(false).show(true);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$4wMXaiw5W9k8YjZSy1ZU0duA2lc(JoinGroupAlert r0, TLRPC.TL_error r1, TLRPC.Updates r2, int r3, TLRPC.TL_messages_importChatInvite r4) {
        r0.lambda$new$10(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Bu_bjDryU4sMRJTu4W2Reb1G_cM(JoinGroupAlert r0, TLRPC.TL_error r1, boolean r2, TLRPC.TL_messages_importChatInvite r3) {
        r0.lambda$new$6(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$E2dLngcMEDz-AnVjelhBYswlESU, reason: not valid java name */
    public static /* synthetic */ void m11180$r8$lambda$E2dLngcMEDzAnVjelhBYswlESU(JoinGroupAlert r0, long r1, boolean r3, TLRPC.TL_messages_importChatInvite r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$new$7(r1, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$EyrUumNonpYjmCv0KKGPB49oRTU(JoinGroupAlert r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$Frw2fBC0zhgRuUxx23g1lZzXw4g(JoinGroupAlert r0, long r1, int r3, View r4) {
        r0.lambda$new$12(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ZAo9gBvIBYHr4d33XX2gbNPZ1ds(JoinGroupAlert r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$jUQirQs5fVsTf3A5osRHMvcgeEw(JoinGroupAlert r0, boolean r1, DialogInterface r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$lpo4j0RhrARlKJQmuKHwR2XGaWo(JoinGroupAlert r0, boolean r1, TLRPC.TL_error r2) {
        return r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ouYdOE7W01BDssnKhMd75h9j1GY(JoinGroupAlert r0, TLRPC.TL_chatInviteJoinResultWebView r1, long r2) {
        r0.lambda$new$4(r1, r2);
    }

    /* renamed from: $r8$lambda$p4bQ3C-c1A-8UyArzNdtswITNDU, reason: not valid java name */
    public static /* synthetic */ void m11181$r8$lambda$p4bQ3Cc1A8UyArzNdtswITNDU(JoinGroupAlert r0, boolean r1, DialogInterface r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$pn8asG299hdV9xdbWHNrmxPG-o4, reason: not valid java name */
    public static /* synthetic */ void m11182$r8$lambda$pn8asG299hdV9xdbWHNrmxPGo4(JoinGroupAlert r0, boolean r1, long r2, View r4) {
        r0.lambda$new$8(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$qxOBjBWZgCLrKnSSBW8cCBP6IKw(JoinGroupAlert r0, TLRPC.TL_chatInviteJoinResultWebView r1, long r2) {
        r0.lambda$new$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rXlV93SCXGe8SJFjGHWIZL4vum0(JoinGroupAlert r0, long r1, int r3, TLRPC.TL_messages_importChatInvite r4, TLRPC.ChatInviteJoinResult r5, TLRPC.TL_error r6) {
        r0.lambda$new$11(r1, r3, r4, r5, r6);
    }

    public JoinGroupAlert(Context r8, TLObject r9, String r10, BaseFragment r11, Theme.ResourcesProvider r12) {
        this(r8, r9, r10, r11, r12, -1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JoinGroupAlert(Context r28, TLObject r29, String r30, BaseFragment r31, Theme.ResourcesProvider r32, int r33) {
        super(r28, false, r32);
        setApplyBottomPadding(false);
        setApplyTopPadding(false);
        fixNavigationBar();
        this.fragment = r31;
        if ((r29 instanceof TLRPC.ChatInvite) == false) goto L10;
        TLRPC.ChatInvite r2 = (TLRPC.ChatInvite) r29;
        this.chatInvite = r2;
        TLRPC.Chat r22 = r2.chat;
        if (r22 == null) goto L7;
        long r8 = -r22.id;
    L8:
        String r23 = r30;
    L13:
        this.hash = r23;
        LinearLayout r24 = new LinearLayout(r28);
        r24.setOrientation(1);
        r24.setClickable(true);
        FrameLayout r10 = new FrameLayout(r28);
        r10.addView(r24);
        NestedScrollView r11 = new NestedScrollView(r28);
        r11.addView(r10);
        setCustomView(r11);
        ImageView r112 = new ImageView(r28);
        r112.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector)));
        r112.setColorFilter(getThemedColor(Theme.key_sheet_other));
        r112.setImageResource(R.drawable.ic_layer_close);
        r112.setOnClickListener(new JoinGroupAlert$$ExternalSyntheticLambda0(this));
        int r12 = AndroidUtilities.dp(8.0f);
        r112.setPadding(r12, r12, r12, r12);
        r10.addView(r112, LayoutHelper.createFrame(36, 36.0f, 8388661, 6.0f, 8.0f, 8.0f, 0.0f));
        BackupImageView r102 = new BackupImageView(r28);
        r102.setRoundRadius(ExteraConfig.getAvatarCorners(90.0f));
        r24.addView(r102, LayoutHelper.createLinear(90, 90, 49, 0, 27, 0, 0));
        TLRPC.ChatInvite r113 = this.chatInvite;
        String r122 = null;
        if (r113 == null) goto L21;
        if (r113.chat == null) goto L18;
        AvatarDrawable r6 = new AvatarDrawable(this.chatInvite.chat);
        TLRPC.ChatInvite r7 = this.chatInvite;
        TLRPC.Chat r114 = r7.chat;
        r122 = r114.title;
        int r13 = r114.participants_count;
        r102.setForUserOrChat(r114, r6, r7);
    L19:
        TLRPC.ChatInvite r62 = this.chatInvite;
        String r72 = r62.about;
        boolean r103 = r62.verified;
        boolean r115 = r62.fake;
        boolean r63 = r62.scam;
    L31:
        SimpleTextView r14 = new SimpleTextView(r28);
        r14.setTypeface(AndroidUtilities.bold());
        r14.setTextSize(20);
        int r15 = Theme.key_dialogTextBlack;
        r14.setTextColor(getThemedColor(r15));
        r14.setText(r122);
        r14.setGravity(17);
        if (r13 <= 0) goto L34;
        int r232 = 0;
    L35:
        r24.addView(r14, LayoutHelper.createLinear(-2, -2, 49, 10, 10, 10, r232));
        if (r63 != 0) goto L41;
        if (r115 == true) goto L41;
        if (r103 == false) goto L42;
        r14.setRightDrawable(getVerifiedCrossfadeDrawable());
    L42:
        TLRPC.ChatInvite r64 = this.chatInvite;
        if (r64 == null) goto L51;
        if (r64.channel == false) goto L49;
        if (r64.megagroup == true) goto L49;
    L54:
        boolean r65 = true;
    L56:
        boolean r104 = TextUtils.isEmpty(r72);
        TextView r116 = new TextView(r28);
        r116.setTextSize(1, 13.0f);
        int r142 = Theme.key_dialogTextGray3;
        r116.setTextColor(getThemedColor(r142));
        r116.setSingleLine(true);
        TextUtils.TruncateAt r123 = TextUtils.TruncateAt.END;
        r116.setEllipsize(r123);
        if (r65 == false) goto L60;
        String r17 = "Subscribers";
    L61:
        r116.setText(LocaleController.formatPluralString(r17, r13, new Object[0]));
        if (r104 == true) goto L64;
        int r25 = 0;
    L65:
        r24.addView(r116, LayoutHelper.createLinear(-2, -2, 49, 10, 0, 10, r25));
        if (r104 == true) goto L68;
        TextView r4 = new TextView(r28);
        r4.setGravity(17);
        r4.setText(r72);
        r4.setTextColor(getThemedColor(r15));
        r4.setTextSize(1, 15.0f);
        r24.addView(r4, LayoutHelper.createLinear(-1, -2, 48, 24, 10, 24, 20));
    L68:
        TLRPC.ChatInvite r42 = this.chatInvite;
        if (r42 != null) goto L71;
    L108:
        FrameLayout r1 = new FrameLayout(getContext());
        r24.addView(r1, LayoutHelper.createLinear(-1, -2));
        RadialProgressView r43 = new RadialProgressView(getContext(), r32);
        this.requestProgressView = r43;
        int r3 = Theme.key_featuredStickers_addButton;
        r43.setProgressColor(getThemedColor(r3));
        this.requestProgressView.setSize(AndroidUtilities.dp(32.0f));
        this.requestProgressView.setVisibility(4);
        r1.addView(this.requestProgressView, LayoutHelper.createFrame(48, 48, 17));
        TextView r44 = new TextView(getContext());
        this.requestTextView = r44;
        r44.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(24.0f), getThemedColor(r3), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
        this.requestTextView.setEllipsize(r123);
        this.requestTextView.setGravity(17);
        this.requestTextView.setSingleLine(true);
        TextView r34 = this.requestTextView;
        if (r65 == false) goto L111;
        int r45 = R.string.RequestToJoinChannel;
    L112:
        r34.setText(LocaleController.getString(r45));
        this.requestTextView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.requestTextView.setTextSize(1, 14.0f);
        this.requestTextView.setTypeface(AndroidUtilities.bold());
        this.requestTextView.setOnClickListener(new JoinGroupAlert$$ExternalSyntheticLambda1(this, r65, r8));
        r1.addView(this.requestTextView, LayoutHelper.createLinear(-1, 48, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 14, 0, 14, 0));
        TextView r16 = new TextView(getContext());
        r16.setGravity(17);
        r16.setTextSize(1, 14.0f);
        if (r65 == false) goto L115;
        int r35 = R.string.RequestToJoinChannelDescription;
    L116:
        r16.setText(LocaleController.getString(r35));
        r16.setTextColor(getThemedColor(r142));
        r24.addView(r16, LayoutHelper.createLinear(-1, -2, 48, 24, 17, 24, 15));
        return;
    L115:
        r35 = R.string.RequestToJoinGroupDescription;
        goto L116
    L111:
        r45 = R.string.RequestToJoinGroup;
        goto L112
    L71:
        if (r42.request_needed == true) goto L108;
        if (r42.participants.isEmpty() == true) goto L91;
        if (r65 == false) goto L77;
        String r36 = LocaleController.getString(R.string.ChannelPrivate).toLowerCase();
    L78:
        r116.setText(r36);
        int r37 = Math.min(this.chatInvite.participants.size(), 3);
        AvatarsImageView r66 = new AvatarsImageView(r28, false);
        r66.setAvatarsTextSize(AndroidUtilities.dp(20.0f));
        r66.setSize(AndroidUtilities.dp(38.0f));
        r66.setCount(r37);
        r66.setStepFactor(0.65f);
        int r117 = 0;
    L79:
        if (r117 >= r37) goto L81;
        r66.setObject(r117, UserConfig.selectedAccount, this.chatInvite.participants.get(r117));
        r117 = r117 + 1;
        goto L79
    L81:
        r66.commitTransition(false);
        r24.addView(r66, LayoutHelper.createLinear((int) (38.0f + ((r37 - 1) * 25.699999f)), 44, 17, 0, 2, 0, 4));
        TextView r67 = new TextView(r28);
        r67.setTextSize(1, 13.0f);
        r67.setTextColor(getThemedColor(Theme.key_dialogTextGray3));
        r67.setGravity(17);
        if (r37 != 1) goto L84;
        boolean r118 = false;
        CharSequence r18 = ellipsize(r67, this.chatInvite, 0).toString();
    L90:
        r67.setText(r18);
        r24.addView(r67, LayoutHelper.createLinear(-2, -2, 49, 10, 0, 10, 24));
    L92:
        TLRPC.ChatInvite r19 = this.chatInvite;
        if (r19.channel == false) goto L97;
        if (r19.megagroup == true) goto L97;
    L100:
        boolean r46 = true;
    L102:
        TextView r110 = new TextView(getContext());
        r110.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(24.0f), getThemedColor(Theme.key_featuredStickers_addButton), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
        ScaleStateListAnimator.apply(r110, 0.02f, 1.2f);
        r110.setEllipsize(TextUtils.TruncateAt.END);
        r110.setGravity(17);
        r110.setSingleLine(true);
        if (r46 == false) goto L105;
        int r38 = R.string.ProfileJoinChannel;
    L106:
        r110.setText(LocaleController.getString(r38));
        r110.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        r110.setTextSize(1, 14.0f);
        r110.setTypeface(AndroidUtilities.bold());
        r24.addView(r110, LayoutHelper.createLinear(-1, 48, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 14, 0, 14, 14));
        r110.setOnClickListener(new JoinGroupAlert$$ExternalSyntheticLambda2(this, r8, r33));
        return;
    L105:
        r38 = R.string.ProfileJoinGroup;
    L97:
        if (ChatObject.isChannel(r19.chat) == true) goto L99;
    L101:
        r46 = r118;
        goto L102
    L99:
        if (this.chatInvite.chat.megagroup == true) goto L101;
    L84:
        r118 = false;
        if (r37 != 2) goto L87;
        r18 = LocaleController.formatString("RequestToJoinMembersTwo", R.string.RequestToJoinMembersTwo, new Object[]{ellipsize(r67, this.chatInvite, 0), ellipsize(r67, this.chatInvite, 1)});
        goto L90
    L87:
        if (r13 != 3) goto L89;
        r18 = LocaleController.formatString("RequestToJoinMembersThree", R.string.RequestToJoinMembersThree, new Object[]{ellipsize(r67, this.chatInvite, 0), ellipsize(r67, this.chatInvite, 1), ellipsize(r67, this.chatInvite, 2)});
        r118 = false;
        goto L90
    L89:
        int r111 = Math.max(r13 - r37, 2);
        r118 = false;
        r18 = String.format(LocaleController.getPluralString("RequestToJoinMembersAll", r111), new Object[]{ellipsize(r67, this.chatInvite, 0), ellipsize(r67, this.chatInvite, 1), Integer.valueOf(r111)});
        goto L90
    L77:
        r36 = LocaleController.getString(R.string.MegaPrivate).toLowerCase();
        goto L78
    L91:
        r118 = false;
        goto L92
    L64:
        r25 = 20;
        goto L65
    L60:
        r17 = "Members";
    L49:
        if (ChatObject.isChannelAndNotMegaGroup(r64.chat) == true) goto L54;
    L51:
        if (ChatObject.isChannel(this.currentChat) == true) goto L53;
    L55:
        r65 = false;
        goto L56
    L53:
        if (this.currentChat.megagroup == true) goto L55;
    L41:
        r14.setRightDrawable(getScamDrawable(!r63));
        goto L42
    L34:
        r232 = 20;
        goto L35
    L18:
        AvatarDrawable r119 = new AvatarDrawable();
        r119.setInfo(0, this.chatInvite.title, null);
        TLRPC.ChatInvite r68 = this.chatInvite;
        String r73 = r68.title;
        r13 = r68.participants_count;
        r102.setImage(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r68.photo.sizes, 50), this.chatInvite.photo), "50_50", r119, this.chatInvite);
        r122 = r73;
        goto L19
    L21:
        if (this.currentChat == null) goto L30;
        AvatarDrawable r69 = new AvatarDrawable(this.currentChat);
        String r74 = this.currentChat.title;
        TLRPC.ChatFull r1110 = MessagesController.getInstance(this.currentAccount).getChatFull(this.currentChat.id);
        if (r1110 == null) goto L25;
        r122 = r1110.about;
    L25:
        int r132 = this.currentChat.participants_count;
        if (r1110 == null) goto L28;
        int r1111 = r1110.participants_count;
    L29:
        r13 = Math.max(r132, r1111);
        TLRPC.Chat r1112 = this.currentChat;
        r102.setForUserOrChat(r1112, r69, r1112);
        TLRPC.Chat r610 = this.currentChat;
        r103 = r610.verified;
        r115 = r610.fake;
        String r26 = r122;
        r122 = r74;
        r72 = r26;
        r63 = r610.scam;
        goto L31
    L28:
        r1111 = 0;
        goto L29
    L30:
        r63 = 0;
        r103 = false;
        r115 = false;
        r13 = 0;
        r72 = null;
        goto L31
    L7:
        r8 = 0;
        goto L8
    L10:
        if ((r29 instanceof TLRPC.Chat) == false) goto L12;
        TLRPC.Chat r27 = (TLRPC.Chat) r29;
        this.currentChat = r27;
        r8 = r27.id;
        goto L8
    L12:
        r23 = r30;
        r8 = 0;
        goto L13
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(JoinGroupAlert r0) {
        return r0.resourcesProvider;
    }

    private CharSequence ellipsize(TextView r1, TLRPC.ChatInvite r2, int r3) {
        String r0 = r2.participants.get(r3).first_name;
        if (r0 != null) goto L6;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        return TextUtils.ellipsize(r0.trim(), r1.getPaint(), AndroidUtilities.dp(120.0f), TextUtils.TruncateAt.END);
    }

    private Drawable getScamDrawable(int r1) {
        if (r1 != 0) goto L6;
        return Theme.dialogs_scamDrawable;
    L6:
        return Theme.dialogs_fakeDrawable;
    }

    private Drawable getVerifiedCrossfadeDrawable() {
        return new CombinedDrawable(Theme.dialogs_verifiedDrawable, Theme.dialogs_verifiedCheckDrawable);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$1() {
        if (isDismissed() == true) goto L6;
        this.requestTextView.setVisibility(4);
        this.requestProgressView.setVisibility(0);
        return;
    }

    private /* synthetic */ void lambda$new$10(TLRPC.TL_error r3, TLRPC.Updates r4, int r5, TLRPC.TL_messages_importChatInvite r6) {
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() != null) goto L8;
        return;
    L8:
        if (r3 != null) goto L15;
        if (r4 != null) goto L11;
        return;
    L11:
        if (r4.chats.isEmpty() == true) goto L27;
        TLRPC.Chat r32 = r4.chats.get(0);
        r32.left = false;
        r32.kicked = false;
        MessagesController.getInstance(this.currentAccount).putUsers(r4.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r4.chats, false);
        openChat(r32.id, !ChatObject.isChannelAndNotMegaGroup(r32));
        return;
    L27:
        return;
    L15:
        if ("USER_ALREADY_PARTICIPANT".equals(r3.text) == false) goto L23;
        if (r5 != 0) goto L23;
        TLRPC.ChatInvite r42 = this.chatInvite;
        if (r42 == null) goto L23;
        TLRPC.Chat r43 = r42.chat;
        if (r43 == null) goto L23;
        openChat(r43.id, false);
        return;
    L23:
        AlertsCreator.processError(this.currentAccount, r3, this.fragment, r6, new Object[0]);
    }

    private /* synthetic */ void lambda$new$11(long r9, int r11, TLRPC.TL_messages_importChatInvite r12, TLRPC.ChatInviteJoinResult r13, TLRPC.TL_error r14) {
        if ((r13 instanceof TLRPC.TL_chatInviteJoinResultOk) == false) goto L6;
        TLRPC.Updates r92 = ((TLRPC.TL_chatInviteJoinResultOk) r13).updates;
        MessagesController.getInstance(this.currentAccount).processUpdates(r92, false);
        TLRPC.Updates r5 = r92;
    L9:
        AndroidUtilities.runOnUIThread(new JoinGroupAlert$$ExternalSyntheticLambda9(this, r14, r5, r11, r12));
        return;
    L6:
        if ((r13 instanceof TLRPC.TL_chatInviteJoinResultWebView) == false) goto L8;
        AndroidUtilities.runOnUIThread(new JoinGroupAlert$$ExternalSyntheticLambda8(this, (TLRPC.TL_chatInviteJoinResultWebView) r13, r9));
    L8:
        r5 = null;
        goto L9
    }

    private /* synthetic */ void lambda$new$12(long r7, int r9, View r10) {
        dismiss();
        TLRPC.TL_messages_importChatInvite r5 = new TLRPC.TL_messages_importChatInvite();
        r5.hash = this.hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r5, null, new JoinGroupAlert$$ExternalSyntheticLambda3(this, r7, r9, r5), 2);
    }

    private /* synthetic */ void lambda$new$2(boolean r2, DialogInterface r3) {
        showBulletin(getContext(), this.fragment, this.bulletinFactory, r2);
    }

    private /* synthetic */ boolean lambda$new$3(boolean r2, TLRPC.TL_error r3) {
        if (r3 != null) goto L4;
    L6:
        dismiss();
        return false;
    L4:
        if ("INVITE_REQUEST_SENT".equals(r3.text) == false) goto L6;
        setOnDismissListener(new JoinGroupAlert$$ExternalSyntheticLambda12(this, r2));
        goto L6
    }

    private /* synthetic */ void lambda$new$4(TLRPC.TL_chatInviteJoinResultWebView r8, long r9) {
        MessagesController.getInstance(this.currentAccount).putUsers(r8.users, false);
        BotGuardHelper.getInstance(this.currentAccount).openGuardBotWebApp(r9, r8.bot_id, r8.query_id);
    }

    private /* synthetic */ void lambda$new$5(boolean r2, DialogInterface r3) {
        showBulletin(getContext(), this.fragment, this.bulletinFactory, r2);
    }

    private /* synthetic */ void lambda$new$6(TLRPC.TL_error r3, boolean r4, TLRPC.TL_messages_importChatInvite r5) {
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L15;
        if (r3 != null) goto L9;
    L12:
        dismiss();
        return;
    L9:
        if ("INVITE_REQUEST_SENT".equals(r3.text) == false) goto L11;
        setOnDismissListener(new JoinGroupAlert$$ExternalSyntheticLambda13(this, r4));
        goto L12
    L11:
        AlertsCreator.processError(this.currentAccount, r3, this.fragment, r5, new Object[0]);
        goto L12
    }

    private /* synthetic */ void lambda$new$7(long r2, boolean r4, TLRPC.TL_messages_importChatInvite r5, TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof TLRPC.TL_chatInviteJoinResultOk) == false) goto L6;
        TLRPC.Updates r22 = ((TLRPC.TL_chatInviteJoinResultOk) r6).updates;
        MessagesController.getInstance(this.currentAccount).processUpdates(r22, false);
    L8:
        AndroidUtilities.runOnUIThread(new JoinGroupAlert$$ExternalSyntheticLambda11(this, r7, r4, r5));
        return;
    L6:
        if ((r6 instanceof TLRPC.TL_chatInviteJoinResultWebView) == false) goto L8;
        AndroidUtilities.runOnUIThread(new JoinGroupAlert$$ExternalSyntheticLambda10(this, (TLRPC.TL_chatInviteJoinResultWebView) r6, r2));
        goto L8
    }

    private /* synthetic */ void lambda$new$8(boolean r12, long r13, View r15) {
        AndroidUtilities.runOnUIThread(new JoinGroupAlert$$ExternalSyntheticLambda4(this), 400);
        if (this.chatInvite == null) goto L5;
    L8:
        TLRPC.TL_messages_importChatInvite r5 = new TLRPC.TL_messages_importChatInvite();
        r5.hash = this.hash;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r5, new JoinGroupAlert$$ExternalSyntheticLambda7(this, r13, r12, r5), 2);
        return;
    L5:
        if (this.currentChat == null) goto L8;
        MessagesController.getInstance(this.currentAccount).addUserToChat(this.currentChat.id, UserConfig.getInstance(this.currentAccount).getCurrentUser(), 0, null, null, true, new JoinGroupAlert$$ExternalSyntheticLambda5(this), new JoinGroupAlert$$ExternalSyntheticLambda6(this, r12));
    }

    private /* synthetic */ void lambda$new$9(TLRPC.TL_chatInviteJoinResultWebView r8, long r9) {
        MessagesController.getInstance(this.currentAccount).putUsers(r8.users, false);
        BotGuardHelper.getInstance(this.currentAccount).openGuardBotWebApp(r9, r8.bot_id, r8.query_id);
    }

    private void openChat(long r7, boolean r9) {
        Bundle r2 = new Bundle();
        r2.putLong("chat_id", r7);
        if (MessagesController.getInstance(this.currentAccount).checkCanOpenChat(r2, this.fragment) == false) goto L6;
        AnonymousClass1 r0 = new AnonymousClass1(this, r2, r9, r7);
        BaseFragment r6 = this.fragment;
        r6.presentFragment(r0, r6 instanceof ChatActivity);
        return;
    }

    public static void showBulletin(Context r2, BaseFragment r3, BulletinFactory r4, boolean r5) {
        if (r2 != null) goto L6;
        if (r3 == null) goto L14;
        r3.getContext();
        return;
    L14:
        return;
    L6:
        if (r4 != null) goto L8;
        r4 = BulletinFactory.of(r3);
    L8:
        Bulletin.TwoLineLottieLayout r0 = new Bulletin.TwoLineLottieLayout(r2, r3.getResourceProvider());
        r0.imageView.setAnimation(R.raw.timer_3, 28, 28);
        r0.titleTextView.setText(LocaleController.getString(R.string.RequestToJoinSent));
        if (r5 == false) goto L11;
        String r22 = LocaleController.getString(R.string.RequestToJoinChannelSentDescription);
    L12:
        r0.subtitleTextView.setText(r22);
        r4.create(r0, 2750).show();
        return;
    L11:
        r22 = LocaleController.getString(R.string.RequestToJoinGroupSentDescription);
        goto L12
    }

    public static void showBulletin(Context r1, BaseFragment r2, boolean r3) {
        showBulletin(r1, r2, BulletinFactory.of(r2), r3);
    }

    public JoinGroupAlert setBulletinFactory(BulletinFactory r1) {
        this.bulletinFactory = r1;
        return this;
    }
}
