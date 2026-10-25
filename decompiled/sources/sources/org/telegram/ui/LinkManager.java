package org.telegram.ui;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_aicompose;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.AIEditorAlert;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CreateBotAlert;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.PaymentFormActivity;
import org.telegram.ui.Stars.BotStarsActivity;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.TON.TONIntroActivity;
import org.telegram.ui.bots.ChannelAffiliateProgramsFragment;
import org.telegram.ui.web.WebBrowserSettings;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class LinkManager {
    private final LaunchActivity activity;
    private final int currentAccount;
    private int currentRequestId;
    private boolean done;
    private boolean inited;
    private final boolean isExternalIntent;
    private final Browser.Progress progress;
    private AlertDialog progressDialog;

    /* renamed from: org.telegram.ui.LinkManager$1, reason: invalid class name */
    public class AnonymousClass1 extends GroupCreateActivity {
        final /* synthetic */ LinkManager this$0;

        /* renamed from: $r8$lambda$MshUPh8amEooRQYD5ffuu-ICO9Y, reason: not valid java name */
        public static /* synthetic */ void m15690$r8$lambda$MshUPh8amEooRQYD5ffuuICO9Y(AnonymousClass1 r0, TLObject r1, TLRPC.User r2, boolean r3) {
            r0.lambda$onCallUsersSelected$0(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$OD_x-5XwfvoUOkRtM9AnSeOQUYE, reason: not valid java name */
        public static /* synthetic */ void m15691$r8$lambda$OD_x5XwfvoUOkRtM9AnSeOQUYE(AnonymousClass1 r0, boolean r1, HashSet r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onCallUsersSelected$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$_ZUnJ9ezAJY0sSljJFHofmzHk4c(AnonymousClass1 r0, TLRPC.User r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onCallUsersSelected$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$scsJQTli3FoTbFF9KZEezGK3FXQ(AnonymousClass1 r0, TLObject r1, boolean r2, HashSet r3, TLRPC.TL_error r4) {
            r0.lambda$onCallUsersSelected$2(r1, r2, r3, r4);
        }

        public AnonymousClass1(LinkManager r1, Bundle r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onCallUsersSelected$0(TLObject r9, TLRPC.User r10, boolean r11) {
            boolean r1 = false;
            if ((r9 instanceof TLRPC.TL_users_userFull) == false) goto L6;
            TLRPC.TL_users_userFull r92 = (TLRPC.TL_users_userFull) r9;
            getMessagesController().putUsers(r92.users, false);
            getMessagesController().putChats(r92.chats, false);
            TLRPC.UserFull r93 = r92.full_user;
        L5:
            TLRPC.UserFull r6 = r93;
            if (r6 != null) goto L9;
        L11:
            VoIPHelper.startCall(r10, r11, r1, getParentActivity(), r6, getAccountInstance());
            return;
        L9:
            if (r6.video_calls_available == false) goto L11;
            r1 = true;
            goto L11
        L6:
            r93 = null;
            goto L5
        }

        private /* synthetic */ void lambda$onCallUsersSelected$1(TLRPC.User r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new LinkManager$1$$ExternalSyntheticLambda3(this, r3, r1, r2));
        }

        private /* synthetic */ void lambda$onCallUsersSelected$2(TLObject r17, boolean r18, HashSet r19, TLRPC.TL_error r20) {
            int r4 = 0;
            if ((r17 instanceof TLRPC.Updates) == false) goto L14;
            TLRPC.Updates r1 = (TLRPC.Updates) r17;
            MessagesController.getInstance(this.currentAccount).putUsers(r1.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r1.chats, false);
            ArrayList r12 = MessagesController.findUpdatesAndRemove(r1, TL_update.TL_updateGroupCall.class);
            int r2 = r12.size();
            TLRPC.GroupCall r9 = null;
        L5:
            if (r4 >= r2) goto L8;
            Object r3 = r12.get(r4);
            r4 = r4 + 1;
            r9 = ((TL_update.TL_updateGroupCall) r3).call;
            goto L5
        L8:
            if (LaunchActivity.instance == null) goto L26;
            if (r9 == null) goto L24;
            TLRPC.TL_inputGroupCall r7 = new TLRPC.TL_inputGroupCall();
            r7.id = r9.id;
            r7.access_hash = r9.access_hash;
            VoIPHelper.joinConference(LaunchActivity.instance, this.currentAccount, r7, r18, r9, r19);
            return;
        L24:
            return;
        L26:
            return;
        L14:
            if ((r17 instanceof TL_phone.groupCall) == false) goto L20;
            TL_phone.groupCall r13 = (TL_phone.groupCall) r17;
            MessagesController.getInstance(this.currentAccount).putUsers(r13.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r13.chats, false);
            if (LaunchActivity.instance == null) goto L27;
            TLRPC.TL_inputGroupCall r122 = new TLRPC.TL_inputGroupCall();
            TLRPC.GroupCall r14 = r13.call;
            r122.id = r14.id;
            r122.access_hash = r14.access_hash;
            VoIPHelper.joinConference(LaunchActivity.instance, this.currentAccount, r122, r18, r14, r19);
            return;
        L27:
            return;
        L20:
            if (r20 == null) goto L25;
            LinkManager.m15689$$Nest$mgetBulletinFactory(this.this$0).showForError(r20);
            return;
        }

        private /* synthetic */ void lambda$onCallUsersSelected$3(boolean r7, HashSet r8, TLObject r9, TLRPC.TL_error r10) {
            AndroidUtilities.runOnUIThread(new LinkManager$1$$ExternalSyntheticLambda2(this, r9, r7, r8, r10));
        }

        @Override // org.telegram.ui.GroupCreateActivity
        public void onCallUsersSelected(HashSet<Long> r8, boolean r9) {
            if (r8.size() != 1) goto L9;
            TLRPC.User r1 = getMessagesController().getUser(r8.iterator().next());
            TLRPC.UserFull r5 = getMessagesController().getUserFull(r1.id);
            if (r5 != null) goto L8;
            TLRPC.TL_users_getFullUser r82 = new TLRPC.TL_users_getFullUser();
            r82.id = getMessagesController().getInputUser(r1.id);
            getConnectionsManager().sendRequest(r82, new LinkManager$1$$ExternalSyntheticLambda0(this, r1, r9));
            return;
        L8:
            VoIPHelper.startCall(r1, r9, r5.video_calls_available, getParentActivity(), r5, getAccountInstance());
        L10:
            finishFragment();
            return;
        L9:
            TL_phone.createConferenceCall r92 = new TL_phone.createConferenceCall();
            r92.random_id = Utilities.random.nextInt();
            ConnectionsManager.getInstance(this.currentAccount).sendRequest(r92, new LinkManager$1$$ExternalSyntheticLambda1(this, r9, r8));
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.LinkManager$2, reason: invalid class name */
    public class AnonymousClass2 extends QrActivity {
        final /* synthetic */ LinkManager this$0;

        public AnonymousClass2(LinkManager r1, Bundle r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onBecomeFullyVisible() {
            super.onBecomeFullyVisible();
            AndroidUtilities.runOnUIThread(new LinkManager$2$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.LinkManager$3, reason: invalid class name */
    public class AnonymousClass3 extends ChatActivity {
        private boolean shownToast;
        final /* synthetic */ LinkManager this$0;
        final /* synthetic */ TLRPC.User[] val$manager;
        final /* synthetic */ long val$managerId;
        final /* synthetic */ TLRPC.User val$newBot;

        /* renamed from: $r8$lambda$geRhqoSkpWckDIxMazbxiy8yt-s, reason: not valid java name */
        public static /* synthetic */ void m15692$r8$lambda$geRhqoSkpWckDIxMazbxiy8yts(AnonymousClass3 r0, long r1) {
            r0.lambda$onBecomeFullyVisible$0(r1);
        }

        public AnonymousClass3(LinkManager r1, Bundle r2, TLRPC.User r3, TLRPC.User[] r4, long r5) {
            this.this$0 = r1;
            this.val$newBot = r3;
            this.val$manager = r4;
            this.val$managerId = r5;
            super(r2);
        }

        private /* synthetic */ void lambda$onBecomeFullyVisible$0(long r1) {
            presentFragment(ChatActivity.of(r1));
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onBecomeFullyVisible() {
            super.onBecomeFullyVisible();
            if (this.shownToast == true) goto L6;
            this.shownToast = true;
            BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.CreateManagedBotCreatedTitle, new Object[]{UserObject.getUserName(this.val$newBot)}), AndroidUtilities.replaceSingleTag(LocaleController.formatString(R.string.CreateManagedBotCreatedText, new Object[]{UserObject.getUserName(this.val$manager[0])}), new LinkManager$3$$ExternalSyntheticLambda0(this, this.val$managerId))).show();
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$27BjbDTWzQucmElTGG6AGUbtea4(LinkManager r0, String r1, TL_account.Passkeys r2, TLRPC.TL_error r3) {
        r0.lambda$handleSettings$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$5KgVTrduXjiAqfIwbxgOCFjBQl4(LinkManager r0, TL_aicompose.Tones r1, TLRPC.TL_error r2) {
        r0.lambda$handleAiStyle$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$5p5ZPZn3KDU8oha1jokY1ltg1Eo(Runnable r1, PaymentFormActivity.InvoiceStatus r2) {
        if (r2 != PaymentFormActivity.InvoiceStatus.PAID) goto L6;
        r1.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$DyQEWDw6lmo6YkcHKSkdiQbfHCE(LinkManager r0, String r1) {
        r0.lambda$handleSettings$9(r1);
    }

    /* renamed from: $r8$lambda$GQTYUjaCdhTg843sgqI0YzA-GWI, reason: not valid java name */
    public static /* synthetic */ void m15685$r8$lambda$GQTYUjaCdhTg843sgqI0YzAGWI(LinkManager r0, String r1) {
        r0.lambda$handleSettings$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$GmLMKlgceBVeDPNr0iT25HeL8LE(LinkManager r0, FiltersSetupActivity r1) {
        r0.lambda$handleSettings$0(r1);
    }

    /* renamed from: $r8$lambda$IlhH7s6PUU5t5jap-ngzdHWNvH8, reason: not valid java name */
    public static /* synthetic */ void m15686$r8$lambda$IlhH7s6PUU5t5japngzdHWNvH8(LinkManager r0) {
        r0.cancel();
    }

    public static /* synthetic */ void $r8$lambda$Ip5w2MszpA_BOiRYbERrSelV2gA(LinkManager r0, TLRPC.TL_inputInvoiceSlug r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$handleInvoiceSlug$17(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$LH95z6iiSbgUKZuFzNPo8_CwFi0(ProfileActivity r2) {
        SharedMediaLayout r0 = r2.sharedMediaLayout;
        if (r0 == null) goto L6;
        r0.scrollToPage(14);
        r2.scrollToSharedMedia();
        return;
    }

    public static /* synthetic */ void $r8$lambda$M83DG4eOPEKDIndItiLYBXjJulY(LinkManager r0, TLRPC.User[] r1, TLRPC.User r2) {
        r0.lambda$handleNewBot$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$P_2YKEX1Md8AKf0Uaq6mDBX3rys(LinkManager r0, TLRPC.TL_messages_requestUrlAuth r1, TLRPC.UrlAuthResult r2, TLRPC.TL_error r3) {
        r0.lambda$handleOAuth$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QmEBf2ufEqgCXj9Gbt6jZX2YSkI(LinkManager r0, DialogInterface r1) {
        r0.lambda$init$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$Rx0vQsu1qGItd7IWcoO776ixb9U(LinkManager r0, TLRPC.User[] r1, Runnable r2, Long r3) {
        r0.lambda$handleNewBot$21(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SmkEcCXj4_l5XMhTPvRUEK5ACMw(LinkManager r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_inputInvoiceSlug r3, String r4) {
        r0.lambda$handleInvoiceSlug$16(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$URxiwuOJG0fM2eKyA9oQo6oOclQ(Runnable r1, String r2) {
        if (r1 != null) goto L4;
        return;
    L4:
        if ("paid".equals(r2) == false) goto L8;
        r1.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$WAYt0XbusABBBakLekvaYtoVX2s(LinkManager r0, TLObject r1, String r2) {
        r0.lambda$handleSettings$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Z8BkDiiR6paAbLygKx7FD1qgTgk(LinkManager r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$handleSettings$2(r1, r2);
    }

    /* renamed from: $r8$lambda$_zCnesUZ34xqx4AN-LteFfF0uuw, reason: not valid java name */
    public static /* synthetic */ void m15687$r8$lambda$_zCnesUZ34xqx4ANLteFfF0uuw(ProfileActivity r3) {
        AndroidUtilities.runOnUIThread(new LinkManager$$ExternalSyntheticLambda13(r3), 200);
    }

    public static /* synthetic */ void $r8$lambda$aa09JsQVMYIsrTPPA6mfRC2bVhU(LinkManager r0) {
        r0.lambda$handleInvoiceSlug$13();
    }

    public static /* synthetic */ void $r8$lambda$iQJO9rx79FC74R7pRrB9ag6xEFQ(ProfileActivity r3) {
        AndroidUtilities.runOnUIThread(new LinkManager$$ExternalSyntheticLambda15(r3), 200);
    }

    public static /* synthetic */ void $r8$lambda$lUbPYZohusLcD1MGHe1LvMEk9kE(LinkManager r0, BaseFragment r1, TLRPC.User[] r2, TLRPC.TL_requestPeerTypeCreateBot r3) {
        r0.lambda$handleNewBot$20(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$mlwVGQ4BT5w8GSLhQdu07x8PgA8(LinkManager r0, String r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$handleSettings$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$p8VSfVMcfvVJsP-B5WJH_ZHFD7A, reason: not valid java name */
    public static /* synthetic */ void m15688$r8$lambda$p8VSfVMcfvVJsPB5WJH_ZHFD7A(LinkManager r0, NotificationsSettingsActivity r1, int r2, String r3) {
        r0.lambda$handleSettings$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$paHps_jeHUwNd7Rbor9CdgvlPiQ(ProfileActivity r2) {
        SharedMediaLayout r0 = r2.sharedMediaLayout;
        if (r0 == null) goto L6;
        r0.scrollToPage(14);
        r2.scrollToSharedMedia();
        return;
    }

    public static /* synthetic */ void $r8$lambda$r3zbEJWFHSJKLfNdTbwdQGAp8YU(LinkManager r0, TLObject r1) {
        r0.lambda$handleSettings$1(r1);
    }

    /* renamed from: -$$Nest$mgetBulletinFactory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BulletinFactory m15689$$Nest$mgetBulletinFactory(LinkManager r0) {
        return r0.getBulletinFactory();
    }

    public LinkManager(LaunchActivity r2, int r3, Browser.Progress r4, boolean r5) {
        this.currentRequestId = -1;
        this.activity = r2;
        this.currentAccount = r3;
        this.progress = r4;
        this.isExternalIntent = r5;
    }

    private void cancel() {
        if (this.currentRequestId < 0) goto L6;
        getConnectionsManager().cancelRequest(this.currentRequestId, true);
        this.currentRequestId = -1;
        return;
    }

    private void done() {
        if (this.done == false) goto L5;
        return;
    L5:
        AlertDialog r0 = this.progressDialog;
        if (r0 == null) goto L8;
        r0.dismiss();
    L8:
        Browser.Progress r02 = this.progress;
        if (r02 == null) goto L11;
        r02.end();
    L11:
        this.done = true;
    }

    private BulletinFactory getBulletinFactory() {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L7;
        return BulletinFactory.global();
    L7:
        return BulletinFactory.of(r0);
    }

    private BaseFragment getLastFragment() {
        return LaunchActivity.getSafeLastFragment();
    }

    private INavigationLayout getParentLayout() {
        return this.activity.getActionBarLayout();
    }

    private boolean handleAiStyle(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return false;
    L5:
        if (ExteraConfig.getTelegramAiEditor() == false) goto L11;
        TL_aicompose.getTone r0 = new TL_aicompose.getTone();
        TL_aicompose.inputAiComposeToneSlug r1 = new TL_aicompose.inputAiComposeToneSlug();
        r1.slug = r4;
        r0.tone = r1;
        init();
        ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new LinkManager$$ExternalSyntheticLambda1(this));
        return true;
    L11:
        return false;
    }

    private boolean handleHttp(Uri r9) {
        String r0 = r9.getHost();
        if (r0 != null) goto L5;
        return false;
    L5:
        Matcher r2 = LaunchActivity.PREFIX_T_ME_PATTERN.matcher(r0.toLowerCase());
        boolean r3 = r2.find();
        if ("telegram.me".equalsIgnoreCase(r0) == true) goto L14;
        if ("t.me".equalsIgnoreCase(r0) == true) goto L14;
        if ("telegram.dog".equalsIgnoreCase(r0) == true) goto L14;
        if (r3 == true) goto L14;
        return false;
    L14:
        if (r3 == false) goto L24;
        StringBuilder r32 = new StringBuilder("https://t.me/");
        r32.append(r2.group(1));
        boolean r22 = TextUtils.isEmpty(r9.getPath());
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r22 == false) goto L18;
        String r23 = _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        r32.append(r23);
        if (TextUtils.isEmpty(r9.getQuery()) == true) goto L23;
        r4 = "?" + r9.getQuery();
    L23:
        r32.append(r4);
        r9 = Uri.parse(r32.toString());
        goto L24
    L18:
        r23 = r9.getPath();
    L24:
        String r24 = r9.getPath();
        if (r24 != null) goto L27;
    L64:
        return false;
    L27:
        if (r24.length() <= 1) goto L64;
        String r25 = r24.substring(1);
        List<String> r33 = r9.getPathSegments();
        if (r33 == null) goto L64;
        if (r33.isEmpty() == true) goto L64;
        String r42 = r33.get(0);
        String r6 = null;
        if (r33.size() <= 1) goto L37;
        String r5 = r33.get(1);
    L39:
        if ("$".equalsIgnoreCase(r42) == false) goto L43;
        return handleInvoiceSlug(r25.substring(1));
    L43:
        if ("invoice".equalsIgnoreCase(r42) == false) goto L47;
        return handleInvoiceSlug(r5);
    L47:
        if ("addstyle".equalsIgnoreCase(r42) == false) goto L51;
        return handleAiStyle(r5);
    L51:
        if ("oauth".equalsIgnoreCase(r42) == false) goto L55;
        return handleOAuth(r9, r9.getQueryParameter("startapp"));
    L55:
        if ("newbot".equalsIgnoreCase(r42) == false) goto L64;
        if (r33.size() >= 2) goto L60;
        return true;
    L60:
        if (r33.size() < 3) goto L63;
        r6 = r33.get(2);
    L63:
        return handleNewBot(r5, r6, r9.getQueryParameter("name"));
    L37:
        r5 = null;
        goto L39
    }

    private boolean handleInvoiceSlug(String r5) {
        if (TextUtils.isEmpty(r5) == false) goto L6;
        return false;
    L6:
        init();
        TLRPC.TL_payments_getPaymentForm r0 = new TLRPC.TL_payments_getPaymentForm();
        TLRPC.TL_inputInvoiceSlug r1 = new TLRPC.TL_inputInvoiceSlug();
        r1.slug = r5;
        r0.invoice = r1;
        setRequestId(getConnectionsManager().sendRequest(r0, new LinkManager$$ExternalSyntheticLambda0(this, r1, r5)));
        return true;
    }

    private boolean handleNewBot(String r4, String r5, String r6) {
        TLRPC.TL_requestPeerTypeCreateBot r0 = new TLRPC.TL_requestPeerTypeCreateBot();
        r0.bot_managed = true;
        if (TextUtils.isEmpty(r6) == true) goto L6;
        r0.flags |= 2;
        r0.suggested_name = r6;
    L6:
        if (TextUtils.isEmpty(r5) == true) goto L8;
        r0.flags |= 4;
        r0.suggested_username = r5;
    L8:
        BaseFragment r52 = LaunchActivity.getSafeLastFragment();
        if (r52 != null) goto L11;
    L14:
        return true;
    L11:
        if (r52.getContext() == null) goto L14;
        init();
        TLRPC.User[] r62 = {null};
        MessagesController.getInstance(this.currentAccount).getUserNameResolver().resolve(r4, new LinkManager$$ExternalSyntheticLambda12(this, r62, new LinkManager$$ExternalSyntheticLambda11(this, r52, r62, r0)));
        goto L14
    }

    private boolean handleOAuth(Uri r4, String r5) {
        if (this.isExternalIntent == true) goto L6;
        return true;
    L6:
        if (isEmpty(r5) == false) goto L9;
        return false;
    L9:
        init();
        TLRPC.TL_messages_requestUrlAuth r52 = new TLRPC.TL_messages_requestUrlAuth();
        r52.flags |= 4;
        r52.url = r4.toString();
        getConnectionsManager().sendRequestTyped(r52, new AiTonesController$$ExternalSyntheticLambda0(), new LinkManager$$ExternalSyntheticLambda10(this, r52));
        return true;
    }

    private boolean handleSettings(List<String> r29) {
        if (r29 != null) goto L5;
        return false;
    L5:
        boolean r4 = true;
        if (r29.isEmpty() == false) goto L9;
        presentFragment(new SettingsActivity());
        return true;
    L9:
        String r3 = r29.get(0);
        if (r29.size() <= 1) goto L12;
        String r5 = r29.get(1);
    L14:
        if (r29.size() <= 2) goto L16;
        String r7 = r29.get(2);
    L18:
        if (r29.size() <= 3) goto L20;
        String r9 = r29.get(3);
    L22:
        if (r29.size() <= 4) goto L24;
        String r1 = r29.get(4);
    L26:
        if ("theme".equalsIgnoreCase(r3) == true) goto L915;
        if ("themes".equalsIgnoreCase(r3) == true) goto L915;
        if ("devices".equalsIgnoreCase(r3) == false) goto L43;
        SessionsActivity r12 = new SessionsActivity(0);
        if ("link-desktop".equalsIgnoreCase(r5) == false) goto L35;
        r12.setHighlightLinkDesktopDevice();
    L35:
        presentFragment(r12);
        if ("terminate-sessions".equalsIgnoreCase(r5) == false) goto L39;
        scrollTo("terminateAllSessionsRow");
    L39:
        if ("auto-terminate".equalsIgnoreCase(r5) == false) goto L41;
        scrollTo("ttlRow");
    L41:
        return true;
    L43:
        if ("folders".equalsIgnoreCase(r3) == false) goto L52;
        FiltersSetupActivity r13 = new FiltersSetupActivity();
        presentFragment(new FiltersSetupActivity());
        if ("create".equalsIgnoreCase(r5) == false) goto L48;
        AndroidUtilities.runOnUIThread(new LinkManager$$ExternalSyntheticLambda2(this, r13), 300);
    L48:
        if ("show-tags".equalsIgnoreCase(r5) == false) goto L50;
        scrollTo("showTagsRow");
    L50:
        return true;
    L52:
        if ("change_number".equalsIgnoreCase(r3) == false) goto L56;
        presentFragment(new ActionIntroActivity(3), true);
        return true;
    L56:
        if ("language".equalsIgnoreCase(r3) == false) goto L69;
        if ("do-not-translate".equalsIgnoreCase(r5) == false) goto L61;
        presentFragment(new RestrictedLanguagesSelectActivity());
        return true;
    L61:
        presentFragment(new LanguageSelectActivity());
        if ("show-button".equalsIgnoreCase(r5) == false) goto L65;
        scrollTo("manualTranslationPosition");
    L65:
        if ("translate-chats".equalsIgnoreCase(r5) == false) goto L67;
        scrollTo("autoTranslationPosition");
    L67:
        return true;
    L69:
        if ("auto_delete".equalsIgnoreCase(r3) == false) goto L73;
        presentFragment(new AutoDeleteMessagesActivity());
        return true;
    L73:
        if ("phone_privacy".equalsIgnoreCase(r3) == false) goto L77;
        presentFragment(new PrivacyControlActivity(6));
        return true;
    L77:
        if ("premium_sms".equalsIgnoreCase(r3) == false) goto L85;
        ApplicationLoader r14 = ApplicationLoader.applicationLoaderInstance;
        if (r14 == null) goto L85;
        BaseFragment r10 = r14.openSettings(13);
        if (r10 == null) goto L85;
        presentFragment(r10);
        return true;
    L85:
        if ("login_email".equalsIgnoreCase(r3) == false) goto L89;
        init();
        setRequestId(getConnectionsManager().sendRequest(new TL_account.getPassword(), new LinkManager$$ExternalSyntheticLambda3(this), 10));
        return true;
    L89:
        if ("chats".equalsIgnoreCase(r3) == false) goto L105;
        INavigationLayout r6 = getParentLayout();
        int r2 = r6.getFragmentStack().size() - 1;
    L91:
        boolean r17 = r4;
        if (r2 < 0) goto L99;
        if ((r6.getFragmentStack().get(r2) instanceof MainTabsActivity) == true) goto L95;
        if (r2 <= 0) goto L98;
        r6.removeFragmentFromStack(r2);
    L98:
        r2 = r2 + 1;
        r4 = r17;
        goto L91
    L95:
        MainTabsActivity r22 = (MainTabsActivity) r6.getFragmentStack().get(r2);
    L100:
        if (r22 == null) goto L107;
        if ("search".equalsIgnoreCase(r5) == false) goto L107;
        r22.viewPager.scrollToPosition(2);
        return r17;
    L107:
        if ("saved-messages".equalsIgnoreCase(r3) == false) goto L111;
        presentFragment(ChatActivity.of(getUserConfig().getClientUserId()));
        return r17;
    L111:
        if ("calls".equalsIgnoreCase(r3) == false) goto L119;
        if ("start-call".equalsIgnoreCase(r5) == false) goto L116;
        Bundle r15 = new Bundle();
        boolean r42 = r17;
        r15.putBoolean("isCall", r42);
        presentFragment(new AnonymousClass1(this, r15));
        return r42;
    L116:
        boolean r43 = r17;
        presentFragment(new CallLogActivity());
        return r43;
    L119:
        if ("qr-code".equalsIgnoreCase(r3) == false) goto L133;
        if ("scan".equalsIgnoreCase(r5) == false) goto L127;
        BaseFragment r16 = getLastFragment();
        if (r16 == null) goto L127;
        QrActivity.openCameraScanActivity(r16);
        return r17;
    L127:
        if ("share".equalsIgnoreCase(r5) == false) goto L130;
        Bundle r18 = new Bundle();
        r18.putLong("user_id", getUserConfig().getClientUserId());
        presentFragment(new AnonymousClass2(this, r18));
        return true;
    L130:
        Bundle r19 = new Bundle();
        r19.putLong("user_id", getUserConfig().getClientUserId());
        presentFragment(new QrActivity(r19));
        return true;
    L133:
        if ("chat".equalsIgnoreCase(r3) == true) goto L135;
    L166:
        String r182 = r1;
        if ("edit".equalsIgnoreCase(r3) == false) goto L198;
        presentFragment(new UserInfoActivity());
        if ("first-name".equalsIgnoreCase(r5) == false) goto L172;
        scrollTo("firstNameRow");
    L172:
        if ("last-name".equalsIgnoreCase(r5) == false) goto L175;
        scrollTo("lastNameRow");
    L175:
        if ("bio".equalsIgnoreCase(r5) == false) goto L178;
        scrollTo("bioRow");
    L178:
        if ("birthday".equalsIgnoreCase(r5) == false) goto L181;
        scrollTo("birthdayRow");
    L181:
        if ("change-number".equalsIgnoreCase(r5) == false) goto L184;
        scrollTo("numberRow");
    L184:
        if ("username".equalsIgnoreCase(r5) == false) goto L187;
        scrollTo("usernameRow");
    L187:
        if ("channel".equalsIgnoreCase(r5) == false) goto L190;
        scrollTo("channelRow");
    L190:
        if ("add-account".equalsIgnoreCase(r5) == false) goto L193;
        scrollTo("addAccountRow");
    L193:
        if ("log-out".equalsIgnoreCase(r5) == false) goto L922;
        scrollTo("logoutRow");
        return true;
    L922:
        return true;
    L198:
        if ("my-profile".equalsIgnoreCase(r3) == false) goto L215;
        if ("edit".equalsIgnoreCase(r5) == false) goto L203;
        presentFragment(new UserInfoActivity());
        return true;
    L203:
        Bundle r110 = new Bundle();
        r110.putLong("user_id", getUserConfig().getClientUserId());
        r110.putBoolean("my_profile", true);
        if ("gifts".equalsIgnoreCase(r5) == false) goto L206;
        r110.putBoolean("open_gifts", true);
    L206:
        ProfileActivity r23 = new ProfileActivity(r110);
        if ("gifts".equalsIgnoreCase(r5) == false) goto L210;
        r23.whenFullyVisible(new LinkManager$$ExternalSyntheticLambda4(r23));
    L210:
        if ("posts".equalsIgnoreCase(r5) == false) goto L212;
        r23.whenFullyVisible(new LinkManager$$ExternalSyntheticLambda5(r23));
    L212:
        presentFragment(r23);
        return true;
    L215:
        if ("notifications".equalsIgnoreCase(r3) == false) goto L299;
        if (TextUtils.isEmpty(r7) == false) goto L219;
    L245:
        presentFragment(new NotificationsSettingsActivity());
        if ("accounts".equalsIgnoreCase(r5) == false) goto L249;
        scrollTo("accountsAllRow");
    L249:
        if ("private-chats".equalsIgnoreCase(r5) == false) goto L252;
        scrollTo("privateRow");
    L252:
        if ("groups".equalsIgnoreCase(r5) == false) goto L255;
        scrollTo("groupRow");
    L255:
        if ("channels".equalsIgnoreCase(r5) == false) goto L258;
        scrollTo("channelsRow");
    L258:
        if ("stories".equalsIgnoreCase(r5) == false) goto L261;
        scrollTo("storiesRow");
    L261:
        if ("reactions".equalsIgnoreCase(r5) == false) goto L264;
        scrollTo("reactionsRow");
    L264:
        if ("in-app-sounds".equalsIgnoreCase(r5) == false) goto L267;
        scrollTo("inappSoundRow");
    L267:
        if ("in-app-vibrate".equalsIgnoreCase(r5) == false) goto L270;
        scrollTo("inappVibrateRow");
    L270:
        if ("in-app-preview".equalsIgnoreCase(r5) == false) goto L273;
        scrollTo("inappPreviewRow");
    L273:
        if ("in-chat-sounds".equalsIgnoreCase(r5) == false) goto L276;
        scrollTo("inchatSoundRow");
    L276:
        if ("in-app-popup".equalsIgnoreCase(r5) == false) goto L279;
        scrollTo("inappPriorityRow");
    L279:
        if ("show-badge-icon".equalsIgnoreCase(r5) == false) goto L282;
        scrollTo("badgeNumberShowRow");
    L282:
        if ("include-muted-chats".equalsIgnoreCase(r5) == false) goto L285;
        scrollTo("badgeNumberMutedRow");
    L285:
        if ("count-unread-messages".equalsIgnoreCase(r5) == false) goto L288;
        scrollTo("badgeNumberMessagesRow");
    L288:
        if ("new-contacts".equalsIgnoreCase(r5) == false) goto L291;
        scrollTo("contactJoinedRow");
    L291:
        if ("pinned-messages".equalsIgnoreCase(r5) == false) goto L294;
        scrollTo("pinnedMessageRow");
    L294:
        if ("reset".equalsIgnoreCase(r5) == false) goto L923;
        scrollTo("resetNotificationsRow");
        return true;
    L923:
        return true;
    L219:
        if ("private-chats".equalsIgnoreCase(r5) == true) goto L229;
        if ("groups".equalsIgnoreCase(r5) == true) goto L229;
        if ("channels".equalsIgnoreCase(r5) == true) goto L229;
        if ("stories".equalsIgnoreCase(r5) == true) goto L229;
        if ("reactions".equalsIgnoreCase(r5) == false) goto L245;
    L229:
        if ("private-chats".equalsIgnoreCase(r5) == false) goto L232;
        int r24 = 1;
    L243:
        NotificationsSettingsActivity r111 = new NotificationsSettingsActivity();
        init();
        r111.loadExceptions(new LinkManager$$ExternalSyntheticLambda6(this, r111, r24, r7));
        return true;
    L232:
        if ("groups".equalsIgnoreCase(r5) == false) goto L235;
    L233:
        r24 = 0;
        goto L243
    L235:
        if ("channels".equalsIgnoreCase(r5) == false) goto L238;
        r24 = 2;
        goto L243
    L238:
        if ("stories".equalsIgnoreCase(r5) == false) goto L241;
        r24 = 3;
        goto L243
    L241:
        if ("reactions".equalsIgnoreCase(r5) == false) goto L233;
        r24 = 4;
        goto L243
    L299:
        if ("privacy".equalsIgnoreCase(r3) == false) goto L573;
        if ("data-settings".equalsIgnoreCase(r5) == false) goto L307;
        if ("delete-cloud-drafts".equalsIgnoreCase(r7) == false) goto L307;
        presentFragment(new DataSettingsActivity());
        scrollTo("clearDraftsRow");
        return true;
    L307:
        if (TextUtils.isEmpty(r7) == true) goto L313;
        if ("blocked".equalsIgnoreCase(r5) == false) goto L313;
        presentFragment(new PrivacyUsersActivity());
        return true;
    L313:
        if (TextUtils.isEmpty(r7) == true) goto L321;
        if ("active-websites".equalsIgnoreCase(r5) == false) goto L321;
        presentFragment(new SessionsActivity(1));
        if ("disconnect-all".equalsIgnoreCase(r7) == false) goto L319;
        scrollTo("terminateAllSessionsRow");
    L319:
        return true;
    L321:
        if (TextUtils.isEmpty(r7) == true) goto L333;
        if ("passcode".equalsIgnoreCase(r5) == false) goto L333;
        LinkManager$$ExternalSyntheticLambda7 r112 = new LinkManager$$ExternalSyntheticLambda7(this, r7);
        BaseFragment r25 = PasscodeActivity.determineOpenFragment();
        presentFragment(r25);
        if ((r25 instanceof ActionIntroActivity) == false) goto L329;
        ((ActionIntroActivity) r25).setOnOpenedSettings(r112);
        return true;
    L329:
        if ((r25 instanceof PasscodeActivity) == false) goto L924;
        ((PasscodeActivity) r25).setOnOpenedSettings(r112);
        return true;
    L924:
        return true;
    L333:
        if (TextUtils.isEmpty(r7) == true) goto L339;
        if ("2sv".equalsIgnoreCase(r5) == false) goto L339;
        init();
        setRequestId(getConnectionsManager().sendRequest(new TL_account.getPassword(), new LinkManager$$ExternalSyntheticLambda8(this, r7), 10));
        return true;
    L339:
        if (TextUtils.isEmpty(r7) == true) goto L347;
        if ("passkey".equalsIgnoreCase(r5) == false) goto L347;
        if (Build.VERSION.SDK_INT < 28) goto L347;
        init();
        setRequestId(getConnectionsManager().sendRequestTyped(new TL_account.getPasskeys(), new AiTonesController$$ExternalSyntheticLambda0(), new LinkManager$$ExternalSyntheticLambda9(this, r7)));
        return true;
    L347:
        if (TextUtils.isEmpty(r7) == true) goto L355;
        if ("auto-delete".equalsIgnoreCase(r5) == false) goto L355;
        if (getUserConfig().getGlobalTTl() < 0) goto L355;
        presentFragment(new AutoDeleteMessagesActivity());
        return true;
    L355:
        if (TextUtils.isEmpty(r7) == true) goto L483;
        if ("phone-number".equalsIgnoreCase(r5) == true) goto L383;
        if ("last-seen".equalsIgnoreCase(r5) == true) goto L383;
        if ("profile-photos".equalsIgnoreCase(r5) == true) goto L383;
        if ("bio".equalsIgnoreCase(r5) == true) goto L383;
        if ("gifts".equalsIgnoreCase(r5) == true) goto L383;
        if ("birthday".equalsIgnoreCase(r5) == true) goto L383;
        if ("saved-music".equalsIgnoreCase(r5) == true) goto L383;
        if ("forwards".equalsIgnoreCase(r5) == true) goto L383;
        if ("calls".equalsIgnoreCase(r5) == true) goto L383;
        if ("voice".equalsIgnoreCase(r5) == true) goto L383;
        if ("messages".equalsIgnoreCase(r5) == true) goto L383;
        if ("invites".equalsIgnoreCase(r5) == false) goto L483;
    L383:
        if ("phone-number".equalsIgnoreCase(r5) == false) goto L386;
        int r26 = 6;
    L421:
        presentFragment(new PrivacyControlActivity(r26));
        if ("birthday".equalsIgnoreCase(r5) == false) goto L427;
        if ("add".equalsIgnoreCase(r7) == false) goto L427;
        scrollTo("setBirthdayRow");
    L427:
        if ("always-share".equalsIgnoreCase(r7) == false) goto L429;
    L434:
        scrollTo("everybodyRow");
    L436:
        if ("never-share".equalsIgnoreCase(r7) == false) goto L438;
    L443:
        scrollTo("nobodyRow");
    L445:
        if ("gifts".equalsIgnoreCase(r5) == false) goto L450;
        if ("show-icon".equalsIgnoreCase(r7) == false) goto L450;
        scrollTo("showGiftIconRow");
    L450:
        if ("gifts".equalsIgnoreCase(r5) == false) goto L455;
        if ("accepted-types".equalsIgnoreCase(r7) == false) goto L455;
        scrollTo("giftTypesHeaderRow");
    L455:
        if ("messages".equalsIgnoreCase(r5) == false) goto L460;
        if ("set-price".equalsIgnoreCase(r7) == false) goto L460;
        scrollTo("priceRow");
    L460:
        if ("messages".equalsIgnoreCase(r5) == false) goto L465;
        if ("remove-fee".equalsIgnoreCase(r7) == false) goto L465;
        scrollTo("alwaysShareRow");
    L465:
        if ("last-seen".equalsIgnoreCase(r5) == false) goto L470;
        if ("hide-read-time".equalsIgnoreCase(r7) == false) goto L470;
        scrollTo("readRow");
    L470:
        if ("profile-photos".equalsIgnoreCase(r5) == true) goto L472;
        return true;
    L472:
        if ("set-public".equalsIgnoreCase(r7) == false) goto L475;
        scrollTo("photoForRestRow");
    L475:
        if ("update-public".equalsIgnoreCase(r7) == false) goto L478;
        scrollTo("photoForRestRow");
    L478:
        if ("remove-public".equalsIgnoreCase(r7) == false) goto L927;
        scrollTo("currentPhotoForRestRow");
        return true;
    L927:
        return true;
    L438:
        if ("never-share".equalsIgnoreCase(r9) == true) goto L443;
        if ("never".equalsIgnoreCase(r7) == true) goto L443;
        if ("never".equalsIgnoreCase(r9) == false) goto L445;
    L429:
        if ("always-share".equalsIgnoreCase(r9) == true) goto L434;
        if ("always".equalsIgnoreCase(r7) == true) goto L434;
        if ("always".equalsIgnoreCase(r9) == false) goto L436;
    L386:
        if ("last-seen".equalsIgnoreCase(r5) == false) goto L389;
    L387:
        r26 = 0;
        goto L421
    L389:
        if ("profile-photos".equalsIgnoreCase(r5) == false) goto L392;
        r26 = 4;
        goto L421
    L392:
        if ("bio".equalsIgnoreCase(r5) == false) goto L395;
        r26 = 9;
        goto L421
    L395:
        if ("gifts".equalsIgnoreCase(r5) == false) goto L398;
        r26 = 12;
        goto L421
    L398:
        if ("birthday".equalsIgnoreCase(r5) == false) goto L401;
        r26 = 11;
        goto L421
    L401:
        if ("saved-music".equalsIgnoreCase(r5) == false) goto L404;
        r26 = 14;
        goto L421
    L404:
        if ("forwards".equalsIgnoreCase(r5) == false) goto L407;
        r26 = 5;
        goto L421
    L407:
        if ("calls".equalsIgnoreCase(r5) == false) goto L413;
        if ("p2p".equalsIgnoreCase(r7) == false) goto L411;
        r26 = 3;
        goto L421
    L411:
        r26 = 2;
        goto L421
    L413:
        if ("voice".equalsIgnoreCase(r5) == false) goto L416;
        r26 = 8;
        goto L421
    L416:
        if ("messages".equalsIgnoreCase(r5) == false) goto L419;
        r26 = 10;
        goto L421
    L419:
        if ("invites".equalsIgnoreCase(r5) == false) goto L387;
        r26 = 1;
    L483:
        if (MessagesController.getInstance(this.currentAccount).autoarchiveAvailable == false) goto L485;
    L487:
        presentFragment(new PrivacySettingsActivity());
        if ("blocked".equalsIgnoreCase(r5) == false) goto L491;
        scrollTo("blockedRow");
    L491:
        if ("active-websites".equalsIgnoreCase(r5) == false) goto L494;
        scrollTo("webSessionsRow");
    L494:
        if ("passcode".equalsIgnoreCase(r5) == false) goto L497;
        scrollTo("passcodeRow");
    L497:
        if ("2sv".equalsIgnoreCase(r5) == false) goto L500;
        scrollTo("passwordRow");
    L500:
        if ("passkey".equalsIgnoreCase(r5) == false) goto L503;
        scrollTo("passkeysRow");
    L503:
        if ("auto-delete".equalsIgnoreCase(r5) == false) goto L506;
        scrollTo("autoDeleteMesages");
    L506:
        if ("login-email".equalsIgnoreCase(r5) == false) goto L509;
        scrollTo("emailLoginRow");
    L509:
        if ("phone-number".equalsIgnoreCase(r5) == false) goto L512;
        scrollTo("phoneNumberRow");
    L512:
        if ("last-seen".equalsIgnoreCase(r5) == false) goto L515;
        scrollTo("lastSeenRow");
    L515:
        if ("profile-photos".equalsIgnoreCase(r5) == false) goto L518;
        scrollTo("profilePhotoRow");
    L518:
        if ("bio".equalsIgnoreCase(r5) == false) goto L521;
        scrollTo("bioRow");
    L521:
        if ("gifts".equalsIgnoreCase(r5) == false) goto L524;
        scrollTo("giftsRow");
    L524:
        if ("birthday".equalsIgnoreCase(r5) == false) goto L527;
        scrollTo("birthdayRow");
    L527:
        if ("saved-music".equalsIgnoreCase(r5) == false) goto L530;
        scrollTo("musicRow");
    L530:
        if ("forwards".equalsIgnoreCase(r5) == false) goto L533;
        scrollTo("forwardsRow");
    L533:
        if ("calls".equalsIgnoreCase(r5) == false) goto L536;
        scrollTo("callsRow");
    L536:
        if ("voice".equalsIgnoreCase(r5) == false) goto L539;
        scrollTo("voicesRow");
    L539:
        if ("messages".equalsIgnoreCase(r5) == false) goto L542;
        scrollTo("noncontactsRow");
    L542:
        if ("invites".equalsIgnoreCase(r5) == false) goto L545;
        scrollTo("groupsRow");
    L545:
        if ("self-destruct".equalsIgnoreCase(r5) == false) goto L548;
        scrollTo("deleteAccountRow");
    L548:
        if ("archive-and-mute".equalsIgnoreCase(r5) == false) goto L551;
        scrollTo("newChatsRow");
    L551:
        if ("data-settings".equalsIgnoreCase(r5) == true) goto L553;
        return true;
    L553:
        if ("sync-contacts".equalsIgnoreCase(r7) == false) goto L556;
        scrollTo("contactsSyncRow");
    L556:
        if ("delete-synced".equalsIgnoreCase(r7) == false) goto L559;
        scrollTo("contactsDeleteRow");
    L559:
        if ("suggest-contacts".equalsIgnoreCase(r7) == false) goto L562;
        scrollTo("contactsSuggestRow");
    L562:
        if ("clear-payment-info".equalsIgnoreCase(r7) == false) goto L565;
        scrollTo("paymentsClearRow");
    L565:
        if ("link-previews".equalsIgnoreCase(r7) == false) goto L568;
        scrollTo("secretWebpageRow");
    L568:
        if ("map-provider".equalsIgnoreCase(r7) == false) goto L929;
        scrollTo("secretMapRow");
        return true;
    L929:
        return true;
    L485:
        if ("archive-and-mute".equalsIgnoreCase(r5) == false) goto L487;
        return true;
    L573:
        if ("data".equalsIgnoreCase(r3) == false) goto L710;
        if ("storage".equalsIgnoreCase(r5) == false) goto L579;
        "clear-cache".equalsIgnoreCase(r7);
        presentFragment(new CacheControlActivity());
        return true;
    L579:
        if ("usage".equalsIgnoreCase(r5) == false) goto L595;
        DataUsage2Activity r113 = new DataUsage2Activity();
        presentFragment(r113);
        if ("mobile".equalsIgnoreCase(r7) == false) goto L584;
        r113.selectTab(1);
    L584:
        if ("wifi".equalsIgnoreCase(r7) == false) goto L587;
        r113.selectTab(2);
    L587:
        if ("roaming".equalsIgnoreCase(r7) == false) goto L590;
        r113.selectTab(3);
    L590:
        if ("reset".equalsIgnoreCase(r7) == false) goto L930;
        r113.scrollToReset();
        return true;
    L930:
        return true;
    L595:
        if ("auto-download".equalsIgnoreCase(r5) == false) goto L637;
        if ("mobile".equalsIgnoreCase(r7) == true) goto L608;
        if ("wifi".equalsIgnoreCase(r7) == true) goto L608;
        if ("roaming".equalsIgnoreCase(r7) == true) goto L608;
        if ("reset".equalsIgnoreCase(r7) == false) goto L637;
        presentFragment(new DataSettingsActivity());
        scrollTo("resetDownloadRow");
        return true;
    L608:
        if ("mobile".equalsIgnoreCase(r7) == false) goto L611;
    L609:
        int r27 = 0;
    L616:
        presentFragment(new DataAutoDownloadActivity(r27));
        if ("enable".equalsIgnoreCase(r9) == false) goto L620;
        scrollTo("autoDownloadRow");
    L620:
        if ("usage".equalsIgnoreCase(r9) == false) goto L623;
        scrollTo("usageProgressRow");
    L623:
        if ("photos".equalsIgnoreCase(r9) == false) goto L626;
        scrollTo("photosRow");
    L626:
        if ("stories".equalsIgnoreCase(r9) == false) goto L629;
        scrollTo("storiesRow");
    L629:
        if ("videos".equalsIgnoreCase(r9) == false) goto L632;
        scrollTo("videosRow");
    L632:
        if ("files".equalsIgnoreCase(r9) == false) goto L931;
        scrollTo("filesRow");
        return true;
    L931:
        return true;
    L611:
        if ("wifi".equalsIgnoreCase(r7) == false) goto L614;
        r27 = 1;
        goto L616
    L614:
        if ("roaming".equalsIgnoreCase(r7) == false) goto L609;
        r27 = 2;
    L637:
        if (TextUtils.isEmpty(r9) == true) goto L659;
        if ("save-to-photos".equalsIgnoreCase(r5) == false) goto L659;
        if ("groups".equalsIgnoreCase(r7) == false) goto L644;
        int r8 = 2;
    L647:
        Bundle r114 = new Bundle();
        r114.putInt("type", r8);
        presentFragment(new SaveToGallerySettingsActivity(r114));
        if ("max-video-size".equalsIgnoreCase(r9) == false) goto L651;
        scrollTo("maxVideoSizeRow");
    L651:
        if ("add-exception".equalsIgnoreCase(r9) == false) goto L654;
        scrollTo("addExceptionRow");
    L654:
        if ("delete-all".equalsIgnoreCase(r9) == false) goto L932;
        scrollTo("deleteAllExceptionsRow");
        return true;
    L932:
        return true;
    L644:
        if ("channels".equalsIgnoreCase(r7) == false) goto L646;
        r8 = 4;
        goto L647
    L646:
        r8 = 1;
    L659:
        if (TextUtils.isEmpty(r7) == true) goto L671;
        if ("proxy".equalsIgnoreCase(r5) == false) goto L671;
        presentFragment(new ProxyListActivity());
        if ("use-proxy".equalsIgnoreCase(r7) == false) goto L666;
        scrollTo("useProxyRow");
    L666:
        if ("add-proxy".equalsIgnoreCase(r7) == false) goto L933;
        scrollTo("proxyAddRow");
        return true;
    L933:
        return true;
    L671:
        if ("pause-music".equalsIgnoreCase(r5) == false) goto L675;
        presentFragment(new ThemeActivity(0));
        scrollTo("pauseOnMediaRow");
        return true;
    L675:
        if ("pause-music-on-record".equalsIgnoreCase(r5) == false) goto L679;
        presentFragment(new ThemeActivity(0));
        scrollTo("pauseOnRecordRow");
        return true;
    L679:
        if ("raise-to-listen".equalsIgnoreCase(r5) == false) goto L683;
        presentFragment(new ThemeActivity(0));
        scrollTo("raiseToListenRow");
        return true;
    L683:
        if ("raise-to-speak".equalsIgnoreCase(r5) == false) goto L687;
        presentFragment(new ThemeActivity(0));
        scrollTo("raiseToSpeakRow");
        return true;
    L687:
        if ("show-18-contnet".equalsIgnoreCase(r5) == false) goto L690;
        presentFragment(new ThemeActivity(0));
        scrollTo("sensitiveContentRow");
        return true;
    L690:
        presentFragment(new DataSettingsActivity());
        if ("save-to-photos".equalsIgnoreCase(r5) == false) goto L702;
        if ("chats".equalsIgnoreCase(r7) == false) goto L696;
        scrollTo("saveToGalleryPeerRow");
    L696:
        if ("groups".equalsIgnoreCase(r7) == false) goto L699;
        scrollTo("saveToGalleryGroupsRow");
    L699:
        if ("channels".equalsIgnoreCase(r7) == false) goto L702;
        scrollTo("saveToGalleryChannelsRow");
    L702:
        if ("use-less-data".equalsIgnoreCase(r5) == false) goto L705;
        scrollTo("useLessDataForCallsRow");
    L705:
        if ("proxy".equalsIgnoreCase(r5) == false) goto L934;
        scrollTo("proxyRow");
        return true;
    L934:
        return true;
    L710:
        if ("appearance".equalsIgnoreCase(r3) == false) goto L826;
        if ("themes".equalsIgnoreCase(r5) == true) goto L821;
        if ("theme".equalsIgnoreCase(r5) == true) goto L821;
        if (TextUtils.isEmpty(r7) == true) goto L731;
        if ("wallpaper".equalsIgnoreCase(r5) == false) goto L721;
    L722:
        presentFragment(new WallpapersListActivity(0));
        if ("set".equalsIgnoreCase(r7) == false) goto L725;
    L728:
        scrollTo("uploadImageRow");
        return true;
    L725:
        if ("choose-photo".equalsIgnoreCase(r7) == true) goto L728;
        return true;
    L721:
        if ("wallpapers".equalsIgnoreCase(r5) == true) goto L722;
    L731:
        if (TextUtils.isEmpty(r7) == true) goto L739;
        if ("your-color".equalsIgnoreCase(r5) == false) goto L735;
    L736:
        presentFragment(new PeerColorActivity(0));
        return true;
    L735:
        if ("color".equalsIgnoreCase(r5) == true) goto L736;
    L739:
        if (TextUtils.isEmpty(r7) == false) goto L741;
    L788:
        presentFragment(new ThemeActivity(0));
        if ("wallpaper".equalsIgnoreCase(r5) == false) goto L791;
    L792:
        scrollTo("backgroundRow");
    L794:
        if ("your-color".equalsIgnoreCase(r5) == false) goto L796;
    L797:
        scrollTo("changeUserColor");
    L799:
        if ("auto-night-mode".equalsIgnoreCase(r5) == false) goto L802;
        scrollTo("nightThemeRow");
    L802:
        if ("text-size".equalsIgnoreCase(r5) == false) goto L805;
        scrollTo("textSizeRow");
    L805:
        if ("message-corners".equalsIgnoreCase(r5) == false) goto L808;
        scrollTo("bubbleRadiusRow");
    L808:
        if ("animations".equalsIgnoreCase(r5) == false) goto L811;
        scrollTo("liteModeRow");
    L811:
        if ("stickers-and-emoji".equalsIgnoreCase(r5) == false) goto L814;
        scrollTo("stickersRow");
    L814:
        if ("app-icon".equalsIgnoreCase(r5) == false) goto L817;
        scrollTo("appIconSelectorRow");
    L817:
        if ("tap-for-next-media".equalsIgnoreCase(r5) == false) goto L940;
        scrollTo("nextMediaTapRow");
        return true;
    L940:
        return true;
    L796:
        if ("color".equalsIgnoreCase(r5) == false) goto L799;
    L791:
        if ("wallpapers".equalsIgnoreCase(r5) == false) goto L794;
    L741:
        if ("stickers-and-emoji".equalsIgnoreCase(r5) == false) goto L788;
        if (TextUtils.isEmpty(r9) == true) goto L749;
        if ("archived".equalsIgnoreCase(r7) == false) goto L749;
        presentFragment(new ArchivedStickersActivity(0));
        return true;
    L749:
        if ("emoji".equalsIgnoreCase(r7) == true) goto L751;
    L767:
        presentFragment(new StickersActivity(0, null));
        if ("trending".equalsIgnoreCase(r7) == false) goto L771;
        scrollTo("featuredRow");
    L771:
        if ("archived".equalsIgnoreCase(r7) == false) goto L774;
        scrollTo("archivedRow");
    L774:
        if ("emoji".equalsIgnoreCase(r7) == false) goto L780;
        if ("large".equalsIgnoreCase(r9) == false) goto L780;
        scrollTo("largeEmojiRow");
        return true;
    L780:
        if ("emoji".equalsIgnoreCase(r7) == false) goto L785;
        if ("dynamic-order".equalsIgnoreCase(r9) == false) goto L785;
        scrollTo("dynamicPackOrder");
        return true;
    L785:
        if ("emoji".equalsIgnoreCase(r7) == false) goto L938;
        scrollTo("emojiPacksRow");
        return true;
    L938:
        return true;
    L751:
        if (TextUtils.isEmpty(r9) == true) goto L767;
        if ("large".equalsIgnoreCase(r9) == true) goto L767;
        if ("dynamic-order".equalsIgnoreCase(r9) == true) goto L767;
        if (TextUtils.isEmpty(r182) == false) goto L759;
    L762:
        presentFragment(new StickersActivity(5, null));
        if ("suggest".equalsIgnoreCase(r9) == false) goto L936;
        scrollTo("suggestRow");
        return true;
    L936:
        return true;
    L759:
        if ("archived".equalsIgnoreCase(r9) == false) goto L762;
        presentFragment(new ArchivedStickersActivity(5));
        return true;
    L821:
        presentFragment(new ThemeActivity(3));
        if ("create".equalsIgnoreCase(r7) == false) goto L824;
        scrollTo("createNewThemeRow");
    L824:
        return true;
    L826:
        if ("power-saving".equalsIgnoreCase(r3) == false) goto L853;
        LiteModeSettingsActivity r115 = new LiteModeSettingsActivity();
        presentFragment(r115);
        if ("videos".equalsIgnoreCase(r5) == false) goto L831;
        r115.scrollToFlags(1024);
    L831:
        if ("gifs".equalsIgnoreCase(r5) == false) goto L834;
        r115.scrollToFlags(2048);
    L834:
        if ("stickers".equalsIgnoreCase(r5) == false) goto L837;
        r115.scrollToFlags(3);
    L837:
        if ("emoji".equalsIgnoreCase(r5) == false) goto L840;
        r115.scrollToFlags(LiteMode.FLAGS_ANIMATED_EMOJI);
    L840:
        if ("effects".equalsIgnoreCase(r5) == false) goto L843;
        r115.scrollToFlags(LiteMode.FLAGS_CHAT);
    L843:
        if ("call-animations".equalsIgnoreCase(r5) == false) goto L846;
        r115.scrollToFlags(512);
    L846:
        if ("particles".equalsIgnoreCase(r5) == false) goto L849;
        r115.scrollToFlags(131072);
    L849:
        if ("transitions".equalsIgnoreCase(r5) == false) goto L851;
        r115.scrollToType(1);
    L851:
        return true;
    L853:
        if ("stars".equalsIgnoreCase(r3) == false) goto L873;
        if ("top-up".equalsIgnoreCase(r5) == false) goto L859;
        new StarsIntroActivity.StarsOptionsSheet(this.activity, null).show();
        return true;
    L859:
        if ("stats".equalsIgnoreCase(r5) == false) goto L863;
        presentFragment(new BotStarsActivity(0, getUserConfig().getClientUserId()));
        return true;
    L863:
        if ("gift".equalsIgnoreCase(r5) == false) goto L867;
        StarsController.getInstance(this.currentAccount).getGiftOptions();
        UserSelectorBottomSheet.open(1, 0, BirthdayController.getInstance(this.currentAccount).getState());
        return true;
    L867:
        if ("earn".equalsIgnoreCase(r5) == false) goto L870;
        presentFragment(new ChannelAffiliateProgramsFragment(getUserConfig().getClientUserId()));
        return true;
    L870:
        presentFragment(new StarsIntroActivity());
        return true;
    L873:
        if ("premium".equalsIgnoreCase(r3) == false) goto L877;
        presentFragment(new PremiumPreviewFragment("link"));
        return true;
    L877:
        if ("business".equalsIgnoreCase(r3) == false) goto L883;
        presentFragment(new PremiumPreviewFragment(1, "link"));
        if ("do-not-hide-ads".equalsIgnoreCase(r5) == false) goto L881;
        scrollTo("showAdsRow");
    L881:
        return true;
    L883:
        if ("ton".equalsIgnoreCase(r3) == false) goto L887;
        presentFragment(new TONIntroActivity());
        return true;
    L887:
        if ("send-gift".equalsIgnoreCase(r3) == false) goto L895;
        if ("self".equalsIgnoreCase(r5) == false) goto L892;
        new GiftSheet(this.activity, this.currentAccount, getUserConfig().getClientUserId(), null, null).show();
        return true;
    L892:
        UserSelectorBottomSheet.open(0, BirthdayController.getInstance(this.currentAccount).getState());
        return true;
    L895:
        if ("ask-question".equalsIgnoreCase(r3) == true) goto L913;
        if ("ask-a-question".equalsIgnoreCase(r3) == true) goto L913;
        if ("faq".equalsIgnoreCase(r3) == false) goto L904;
        Browser.openUrl(this.activity, LocaleController.getString(R.string.TelegramFaqUrl));
        return true;
    L904:
        if ("features".equalsIgnoreCase(r3) == false) goto L908;
        Browser.openUrl(this.activity, LocaleController.getString(R.string.TelegramFeaturesUrl));
        return true;
    L908:
        if ("privacy-policy".equalsIgnoreCase(r3) == false) goto L911;
        Browser.openUrl(this.activity, LocaleController.getString(R.string.PrivacyPolicyUrl));
        return true;
    L911:
        presentFragment(new SettingsActivity());
        return true;
    L913:
        AlertsCreator.createSupportAlert(getLastFragment(), null).show();
        return true;
    L135:
        if ("browser".equalsIgnoreCase(r5) == false) goto L166;
        if (TextUtils.isEmpty(r7) == false) goto L140;
        presentFragment(new ThemeActivity(0));
        scrollTo("browserRow");
        return true;
    L140:
        presentFragment(new WebBrowserSettings(null));
        if ("enable-browser".equalsIgnoreCase(r7) == false) goto L144;
        scrollTo("enableRow");
    L144:
        if ("clear-cookies".equalsIgnoreCase(r7) == false) goto L147;
        scrollTo("clearCookiesRow");
    L147:
        if ("clear-cache".equalsIgnoreCase(r7) == false) goto L150;
        scrollTo("clearCacheRow");
    L150:
        if ("history".equalsIgnoreCase(r7) == false) goto L153;
        scrollTo("historyRow");
    L153:
        if ("clear-history".equalsIgnoreCase(r7) == false) goto L156;
        scrollTo("clearHistoryRow");
    L156:
        if ("never-open".equalsIgnoreCase(r7) == false) goto L159;
        scrollTo("neverOpenRow");
    L159:
        if ("clear-list".equalsIgnoreCase(r7) == false) goto L162;
        scrollTo("clearListRow");
    L162:
        if ("search".equalsIgnoreCase(r7) == false) goto L921;
        scrollTo("searchRow");
        return true;
    L921:
        return true;
    L99:
        r22 = null;
        goto L100
    L105:
        r17 = true;
    L915:
        presentFragment(new ThemeActivity(0));
        return true;
    L24:
        r1 = null;
        goto L26
    L20:
        r9 = null;
        goto L22
    L16:
        r7 = null;
        goto L18
    L12:
        r5 = null;
        goto L14
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v12, types: [int] */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Type inference failed for: r9v4, types: [org.telegram.ui.Stories.recorder.StoryRecorder] */
    private boolean handleTg(Uri r10) {
        Uri r102 = normalizeTgUri(r10);
        List<String> r0 = r102.getPathSegments();
        if (r0 != null) goto L5;
        return false;
    L5:
        ArrayList r2 = new ArrayList(r0);
        String r02 = r102.getAuthority();
        if (TextUtils.isEmpty(r02) == true) goto L9;
        r2.add(0, r02);
    L9:
        if (r2.isEmpty() == false) goto L11;
        return false;
    L11:
        String r03 = (String) r2.get(0);
        if (r2.size() <= 1) goto L14;
        String r3 = (String) r2.get(1);
    L16:
        if ("newbot".equalsIgnoreCase(r03) == false) goto L20;
        return handleNewBot(r102.getQueryParameter("manager"), r102.getQueryParameter("username"), r102.getQueryParameter("name"));
    L20:
        if ("resolve".equalsIgnoreCase(r03) == false) goto L24;
        return handleTgResolve(r102);
    L24:
        if ("invoice".equalsIgnoreCase(r03) == false) goto L28;
        return handleInvoiceSlug(r102.getQueryParameter("slug"));
    L28:
        if ("oauth".equalsIgnoreCase(r03) == false) goto L32;
        return handleOAuth(r102, r102.getQueryParameter("token"));
    L32:
        if ("settings".equalsIgnoreCase(r03) == false) goto L36;
        return handleSettings(r2.subList(1, r2.size()));
    L36:
        if ("chats".equalsIgnoreCase(r03) == false) goto L39;
        "search".equalsIgnoreCase(r3);
        "edit".equalsIgnoreCase(r3);
        "emoji-status".equalsIgnoreCase(r3);
    L39:
        if ("new".equalsIgnoreCase(r03) == false) goto L60;
        if ("group".equalsIgnoreCase(r3) == false) goto L45;
        presentFragment(new GroupCreateActivity(new Bundle()), false);
        return true;
    L45:
        if ("contact".equalsIgnoreCase(r3) == false) goto L49;
        new NewContactBottomSheet(getLastFragment(), this.activity).show();
        return true;
    L49:
        if ("channel".equalsIgnoreCase(r3) == false) goto L57;
        SharedPreferences r103 = MessagesController.getGlobalMainSettings();
        if (BuildVars.DEBUG_VERSION == false) goto L53;
    L55:
        presentFragment(new ActionIntroActivity(0));
        r103.edit().putBoolean("channel_intro", true).commit();
    L56:
        return true;
    L53:
        if (r103.getBoolean("channel_intro", false) == false) goto L55;
        Bundle r104 = new Bundle();
        r104.putInt("step", 0);
        presentFragment(new ChannelCreateActivity(r104));
        goto L56
    L57:
        Bundle r105 = new Bundle();
        r105.putBoolean("destroyAfterSelect", true);
        presentFragment(new ContactsActivity(r105));
        return true;
    L60:
        if ("post".equalsIgnoreCase(r03) == false) goto L67;
        ?? r106 = MediaStreamTrack.VIDEO_TRACK_KIND.equalsIgnoreCase(r3);
        if ("live".equalsIgnoreCase(r3) == false) goto L64;
        r106 = -1;
    L64:
        StoryRecorder.getInstance(this.activity, this.currentAccount).setMode(r106).open(null);
        return true;
    L67:
        if ("contacts".equalsIgnoreCase(r03) == false) goto L77;
        if ("new".equalsIgnoreCase(r3) == false) goto L72;
        new NewContactBottomSheet(getLastFragment(), this.activity).show();
        return true;
    L72:
        Bundle r107 = new Bundle();
        r107.putBoolean("needPhonebook", true);
        r107.putBoolean("needFinishFragment", true);
        presentFragment(new ContactsActivity(r107));
        "search".equalsIgnoreCase(r3);
        "sort".equalsIgnoreCase(r3);
        if ("invite".equalsIgnoreCase(r3) == false) goto L75;
        scrollTo("phonebookRow");
    L75:
        return true;
    L77:
        if ("addstyle".equalsIgnoreCase(r03) == true) goto L79;
        return false;
    L79:
        return handleAiStyle(r102.getQueryParameter("slug"));
    L14:
        r3 = null;
        goto L16
    }

    private boolean handleTgResolve(Uri r5) {
        List<String> r0 = r5.getPathSegments();
        if (r0 != null) goto L5;
        return false;
    L5:
        ArrayList r2 = new ArrayList(r0);
        String r02 = r5.getAuthority();
        if (TextUtils.isEmpty(r02) == true) goto L9;
        r2.add(0, r02);
    L9:
        if (r2.isEmpty() == false) goto L11;
        return false;
    L11:
        r2.remove(0);
        String r03 = r5.getQueryParameter("domain");
        String r22 = r5.getQueryParameter("startapp");
        if ("oauth".equalsIgnoreCase(r03) == true) goto L14;
    L17:
        return false;
    L14:
        if (isEmpty(r22) == true) goto L17;
        return handleOAuth(r5, r22);
    }

    private boolean handleTonsite(Uri r1) {
        Browser.openUrl(this.activity, r1);
        return true;
    }

    private void init() {
        if (this.inited == false) goto L5;
        return;
    L5:
        if (this.done == true) goto L17;
        Browser.Progress r0 = this.progress;
        if (r0 == null) goto L10;
        r0.onCancel(new LinkManager$$ExternalSyntheticLambda17(this));
        this.progress.init();
    L14:
        this.inited = true;
        return;
    L10:
        if (this.progressDialog != null) goto L12;
        this.progressDialog = new AlertDialog(this.activity, 3);
    L12:
        this.progressDialog.setOnCancelListener(new LinkManager$$ExternalSyntheticLambda16(this));
        this.progressDialog.showDelayed(300);
        goto L14
    }

    private static boolean isEmpty(String r0) {
        return TextUtils.isEmpty(r0);
    }

    public static boolean isWebAppLink(String r7) {
        if (r7 != null) goto L120;
        return false;
    L120:
        Uri r1 = Uri.parse(r7);     // Catch: Exception -> L21
        String r2 = r1.getScheme();     // Catch: Exception -> L21
        if (r2 != null) goto L8;
        return false;
    L8:
        String r3 = r1.getPath();     // Catch: Exception -> L21
        if (r3 != null) goto L11;
        return false;
    L11:
        int r4 = r2.hashCode();     // Catch: Exception -> L21
        if (r4 == 3699) goto L110;
        if (r4 == 3213448) goto L24;
        if (r4 == 99617003) goto L19;
    L119:
        return false;
    L19:
        if (r2.equals("https") == false) goto L119;
    L26:
        if (r3.isEmpty() == false) goto L28;
        return false;
    L28:
        String r72 = r1.getHost().toLowerCase();     // Catch: Exception -> L21
        boolean r22 = LaunchActivity.PREFIX_T_ME_PATTERN.matcher(r72).find();     // Catch: Exception -> L21
        if (r72.equals("telegram.me") == false) goto L31;
    L35:
        ArrayList r73 = new ArrayList(r1.getPathSegments());     // Catch: Exception -> L21
        if (r73.size() <= 0) goto L42;
        if (((String) r73.get(0)).equals("s") == false) goto L42;
        r73.remove(0);     // Catch: Exception -> L21
    L42:
        if (r73.size() <= 0) goto L119;
        if (r73.size() < 3) goto L49;
        if ("s".equals(r73.get(1)) == false) goto L49;
        return false;
    L49:
        if (r73.size() <= 1) goto L106;
        String r12 = (String) r73.get(0);     // Catch: Exception -> L21
        if (TextUtils.isEmpty(r12) == false) goto L54;
        return false;
    L54:
        switch(r12.hashCode()) {
            case -1401304190: goto L96;
            case -1268966290: goto L93;
            case -1230486459: goto L90;
            case -1216792120: goto L87;
            case -1147866945: goto L84;
            case 99: goto L81;
            case 108417: goto L78;
            case 93922211: goto L75;
            case 103149417: goto L72;
            case 109400031: goto L69;
            case 311086522: goto L66;
            case 492791415: goto L63;
            case 951526432: goto L60;
            case 2112655022: goto L57;
            default: goto L98;
        };     // Catch: Exception -> L21
    L98:
        String r74 = (String) r73.get(1);     // Catch: Exception -> L21
        if (TextUtils.isEmpty(r74) == false) goto L102;
        return false;
    L102:
        if (r74.matches("^\\d+$") == false) goto L104;
        return false;
    L104:
        return true;
    L57:
        if (r12.equals("confirmphone") == false) goto L98;
    L97:
        return false;
    L60:
        if (r12.equals("contact") == false) goto L98;
    L63:
        if (r12.equals("addstickers") == false) goto L98;
    L66:
        if (r12.equals("setlanguage") == false) goto L98;
    L69:
        if (r12.equals("share") == false) goto L98;
    L72:
        if (r12.equals("login") == false) goto L98;
    L75:
        if (r12.equals("boost") == false) goto L98;
    L78:
        if (r12.equals("msg") == false) goto L98;
    L81:
        if (r12.equals("c") == false) goto L98;
    L84:
        if (r12.equals("addlist") == false) goto L98;
    L87:
        if (r12.equals("addtheme") == false) goto L98;
    L90:
        if (r12.equals("addemoji") == false) goto L98;
    L93:
        if (r12.equals("folder") == false) goto L98;
    L96:
        if (r12.equals("joinchat") == false) goto L98;
    L106:
        if (r73.size() != 1) goto L119;
        return !TextUtils.isEmpty(r1.getQueryParameter("startapp"));
    L31:
        if (r72.equals("t.me") == true) goto L35;
        if (r72.equals("telegram.dog") == true) goto L35;
        if (r22 == false) goto L119;
    L24:
        if (r2.equals("http") == false) goto L119;
    L110:
        if (r2.equals("tg") == false) goto L119;
        if (r7.startsWith("tg:resolve") == true) goto L117;
        if (r7.startsWith("tg://resolve") == false) goto L119;
    L117:
        return !TextUtils.isEmpty(r1.getQueryParameter("appname"));
    L21:
        e = move-exception;
        FileLog.e(e);
        goto L119
    }

    private /* synthetic */ void lambda$handleAiStyle$22(TL_aicompose.Tones r2, TLRPC.TL_error r3) {
        done();
        if ((r2 instanceof TL_aicompose.TL_tones) == false) goto L12;
        TL_aicompose.TL_tones r22 = (TL_aicompose.TL_tones) r2;
        MessagesController.getInstance(this.currentAccount).putUsers(r22.users, false);
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 != null) goto L8;
        return;
    L8:
        if (r22.tones.isEmpty() == true) goto L21;
        TL_aicompose.AiComposeTone r23 = r22.tones.get(0);
        new AIEditorAlert.AiStyleAlert(r1.getContext(), r23, r1.getResourceProvider()).show();
        return;
    L21:
        return;
    L12:
        if (r3 != null) goto L14;
        return;
    L14:
        if ("AICOMPOSE_TONE_SLUG_INVALID".equalsIgnoreCase(r3.text) == false) goto L17;
        getBulletinFactory().createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.AIEditorStyleNotFound)).show();
        return;
    L17:
        getBulletinFactory().showForError(r3);
    }

    private /* synthetic */ void lambda$handleInvoiceSlug$13() {
        done();
    }

    private /* synthetic */ void lambda$handleInvoiceSlug$16(TLRPC.TL_error r7, TLObject r8, TLRPC.TL_inputInvoiceSlug r9, String r10) {
        if (r7 == null) goto L8;
        if ("SUBSCRIPTION_ALREADY_ACTIVE".equalsIgnoreCase(r7.text) == false) goto L6;
        getBulletinFactory().createErrorBulletin(LocaleController.getString(R.string.PaymentInvoiceSubscriptionLinkAlreadyPaid)).show();
    L25:
        done();
        return;
    L6:
        getBulletinFactory().createErrorBulletin(LocaleController.getString(R.string.PaymentInvoiceLinkInvalid)).show();
        goto L25
    L8:
        if (this.activity.isFinishing() == true) goto L25;
        if ((r8 instanceof TLRPC.TL_payments_paymentFormStars) == false) goto L14;
        LaunchActivity r72 = this.activity;
        Runnable r102 = r72.navigateToPremiumGiftCallback;
        r72.navigateToPremiumGiftCallback = null;
        StarsController.getInstance(this.currentAccount).openPaymentForm(null, r9, (TLRPC.TL_payments_paymentFormStars) r8, new LinkManager$$ExternalSyntheticLambda22(this), new LinkManager$$ExternalSyntheticLambda23(r102));
        return;
    L14:
        if ((r8 instanceof TLRPC.PaymentForm) == false) goto L17;
        TLRPC.PaymentForm r82 = (TLRPC.PaymentForm) r8;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        PaymentFormActivity r73 = new PaymentFormActivity(r82, r10, getLastFragment());
    L20:
        if (r73 == null) goto L25;
        LaunchActivity r83 = this.activity;
        Runnable r92 = r83.navigateToPremiumGiftCallback;
        if (r92 == null) goto L24;
        r83.navigateToPremiumGiftCallback = null;
        r73.setPaymentFormCallback(new LinkManager$$ExternalSyntheticLambda24(r92));
    L24:
        presentFragment(r73);
        goto L25
    L17:
        if ((r8 instanceof TLRPC.PaymentReceipt) == false) goto L19;
        r73 = new PaymentFormActivity((TLRPC.PaymentReceipt) r8);
        goto L20
    L19:
        r73 = null;
        goto L20
    }

    private /* synthetic */ void lambda$handleInvoiceSlug$17(TLRPC.TL_inputInvoiceSlug r7, String r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new LinkManager$$ExternalSyntheticLambda19(this, r10, r9, r7, r8));
    }

    private /* synthetic */ void lambda$handleNewBot$19(TLRPC.User[] r9, TLRPC.User r10) {
        done();
        if (r10 != null) goto L5;
        return;
    L5:
        long r6 = r9[0].id;
        Bundle r3 = new Bundle();
        r3.putLong("user_id", r10.id);
        presentFragment(new AnonymousClass3(this, r3, r10, r9, r6));
    }

    private /* synthetic */ void lambda$handleNewBot$20(BaseFragment r10, TLRPC.User[] r11, TLRPC.TL_requestPeerTypeCreateBot r12) {
        CreateBotAlert.show(r10.getContext(), this.currentAccount, r11[0], r12, true, new LinkManager$$ExternalSyntheticLambda18(this, r11), r10.getResourceProvider(), getBulletinFactory(), false);
    }

    private /* synthetic */ void lambda$handleNewBot$21(TLRPC.User[] r2, Runnable r3, Long r4) {
        if (r4 != null) goto L4;
        TLRPC.User r42 = null;
    L5:
        r2[0] = r42;
        if (r42 != null) goto L9;
        done();
        getBulletinFactory().createErrorBulletin(LocaleController.getString(R.string.NoUsernameFound)).show();
        return;
    L9:
        r3.run();
        return;
    L4:
        r42 = MessagesController.getInstance(this.currentAccount).getUser(r4);
        goto L5
    }

    private /* synthetic */ void lambda$handleOAuth$18(TLRPC.TL_messages_requestUrlAuth r1, TLRPC.UrlAuthResult r2, TLRPC.TL_error r3) {
        done();
        if (r3 != null) goto L5;
        OAuthSheet.handle(this.isExternalIntent, this.currentAccount, r1, r2);
        return;
    L5:
        if ("URL_EXPIRED".equalsIgnoreCase(r3.text) == false) goto L8;
        getBulletinFactory().createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.BotAuthLoggedInFailTitle), LocaleController.getString(R.string.BotAuthLoggedInFailNoDomain)).show();
        return;
    L8:
        getBulletinFactory().showForError(r3);
    }

    private /* synthetic */ void lambda$handleSettings$0(FiltersSetupActivity r1) {
        r1.createFolder(getParentLayout());
    }

    private /* synthetic */ void lambda$handleSettings$1(TLObject r1) {
        done();
        if (r1 == null) goto L6;
        LaunchActivity r0 = this.activity;
        r0.openEmailSettings((TL_account.Password) r1);
        return;
    }

    private /* synthetic */ void lambda$handleSettings$10(TLObject r4, String r5) {
        done();
        if (r4 != null) goto L5;
        return;
    L5:
        TL_account.Password r42 = (TL_account.Password) r4;
        if (TwoStepVerificationActivity.canHandleCurrentPassword(r42, false) == true) goto L8;
        AlertsCreator.showUpdateAppAlert(this.activity, LocaleController.getString(R.string.UpdateAppAlert), true);
    L8:
        LinkManager$$ExternalSyntheticLambda21 r0 = new LinkManager$$ExternalSyntheticLambda21(this, r5);
        if (r42.has_password == false) goto L13;
        TwoStepVerificationActivity r52 = new TwoStepVerificationActivity();
        r52.setPassword(r42);
        presentFragment(r52);
        r0.run();
        return;
    L13:
        if (TextUtils.isEmpty(r42.email_unconfirmed_pattern) == false) goto L15;
        int r53 = 6;
    L16:
        TwoStepVerificationSetupActivity r1 = new TwoStepVerificationSetupActivity(r53, r42);
        r1.setOnOpenedSettings(r0);
        presentFragment(r1);
        return;
    L15:
        r53 = 5;
        goto L16
    }

    private /* synthetic */ void lambda$handleSettings$11(String r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new LinkManager$$ExternalSyntheticLambda20(this, r2, r1));
    }

    private /* synthetic */ void lambda$handleSettings$12(String r1, TL_account.Passkeys r2, TLRPC.TL_error r3) {
        done();
        if (r2 == null) goto L10;
        presentFragment(new PasskeysActivity(r2.passkeys));
        if ("create".equalsIgnoreCase(r1) == false) goto L9;
        scrollTo("addPasskeyRow");
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$handleSettings$2(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new LinkManager$$ExternalSyntheticLambda14(this, r1));
    }

    private /* synthetic */ void lambda$handleSettings$7(NotificationsSettingsActivity r1, int r2, String r3) {
        done();
        NotificationsCustomSettingsActivity r12 = r1.makeNotificationsCustomSettingsActivity(r2);
        r12.expanded = true;
        r12.updateRows(false);
        presentFragment(r12);
        if ("show".equalsIgnoreCase(r3) == false) goto L6;
        scrollTo("showRow");
    L6:
        if ("new".equalsIgnoreCase(r3) == false) goto L9;
        scrollTo("newRow");
    L9:
        if ("important".equalsIgnoreCase(r3) == false) goto L12;
        scrollTo("importantRow");
    L12:
        if ("messages".equalsIgnoreCase(r3) == false) goto L15;
        scrollTo("messagesRow");
    L15:
        if ("stories".equalsIgnoreCase(r3) == false) goto L18;
        scrollTo("storiesRow");
    L18:
        if ("preview".equalsIgnoreCase(r3) == false) goto L21;
        scrollTo("previewRow");
    L21:
        if ("show-sender".equalsIgnoreCase(r3) == false) goto L24;
        scrollTo("showSenderRow");
    L24:
        if ("sound".equalsIgnoreCase(r3) == false) goto L27;
        scrollTo("soundRow");
    L27:
        if ("add-exception".equalsIgnoreCase(r3) == false) goto L30;
        scrollTo("addExceptionRow");
    L30:
        if ("delete-exceptions".equalsIgnoreCase(r3) == false) goto L33;
        scrollTo("deleteExceptionsRow");
    L33:
        if ("light-color".equalsIgnoreCase(r3) == false) goto L36;
        scrollTo("lightColorRow");
    L36:
        if ("vibrate".equalsIgnoreCase(r3) == false) goto L39;
        scrollTo("vibrateRow");
    L39:
        if ("popup".equalsIgnoreCase(r3) == false) goto L42;
        scrollTo("popupRow");
    L42:
        if (PluginsConstants.MenuItemProperties.PRIORITY.equalsIgnoreCase(r3) == false) goto L45;
        scrollTo("priorityRow");
        return;
    }

    private /* synthetic */ void lambda$handleSettings$8(String r2) {
        if ("disable".equalsIgnoreCase(r2) == false) goto L6;
        scrollTo("disablePasscodeRow");
    L6:
        if ("change".equalsIgnoreCase(r2) == false) goto L9;
        scrollTo("changePasscodeRow");
    L9:
        if ("auto-lock".equalsIgnoreCase(r2) == false) goto L12;
        scrollTo("autoLockRow");
    L12:
        if ("fingerprint".equalsIgnoreCase(r2) == false) goto L15;
        scrollTo("fingerprintRow");
        return;
    }

    private /* synthetic */ void lambda$handleSettings$9(String r2) {
        if ("disable".equalsIgnoreCase(r2) == false) goto L6;
        scrollTo("turnPasswordOffRow");
    L6:
        if ("change".equalsIgnoreCase(r2) == false) goto L9;
        scrollTo("changePasswordRow");
    L9:
        if ("change-email".equalsIgnoreCase(r2) == false) goto L12;
        scrollTo("emailRow");
        return;
    }

    private /* synthetic */ void lambda$init$23(DialogInterface r1) {
        cancel();
    }

    private Uri normalizeTgUri(Uri r2) {
        if (r2 != null) goto L4;
    L17:
        return r2;
    L4:
        if (r2.isOpaque() == false) goto L17;
        String r1 = r2.getScheme();
        if (r1 != null) goto L10;
    L14:
        return r2;
    L10:
        if (r2.getAuthority() != null) goto L14;
        String r0 = r2.getSchemeSpecificPart();
        if (r0 == null) goto L14;
        return Uri.parse(r1 + "://" + r0);
    }

    private void presentFragment(BaseFragment r2) {
        presentFragment(r2, false);
    }

    private void presentFragment(BaseFragment r3, boolean r4) {
        this.activity.presentFragment(r3, r4, false);
        if (AndroidUtilities.isTablet() == false) goto L6;
        this.activity.actionBarLayout.rebuildFragments(1);
        this.activity.rightActionBarLayout.rebuildFragments(1);
        return;
    }

    private void scrollTo(String r1) {
        AndroidUtilities.scrollToFragmentRow(getParentLayout(), r1);
    }

    private void setRequestId(int r1) {
        this.currentRequestId = r1;
    }

    public ConnectionsManager getConnectionsManager() {
        return ConnectionsManager.getInstance(this.currentAccount);
    }

    public UserConfig getUserConfig() {
        return UserConfig.getInstance(this.currentAccount);
    }

    public boolean handle(Uri r4) {
        if (r4 != null) goto L5;
        return false;
    L5:
        String r1 = r4.getScheme();
        if ("tonsite".equalsIgnoreCase(r1) == false) goto L10;
        return handleTonsite(r4);
    L10:
        if ("http".equalsIgnoreCase(r1) == true) goto L20;
        if ("https".equalsIgnoreCase(r1) == true) goto L20;
        if ("tg".equalsIgnoreCase(r1) == true) goto L17;
        return false;
    L17:
        return handleTg(r4);
    L20:
        return handleHttp(r4);
    }
}
