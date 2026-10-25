package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.SlideIntChooseView;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.ChatRightsEditActivity;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.GigagroupConvertAlert;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SlideChooseView;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.GroupCreateActivity;
import org.telegram.ui.bots.AffiliateProgramFragment;

/* loaded from: classes6.dex */
public class ChatUsersActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private int addNew2Row;
    private int addNewRow;
    private int addNewSectionRow;
    private int addUsersRow;
    private int antiSpamInfoRow;
    private int antiSpamRow;
    private boolean antiSpamToggleLoading;
    private int blockedEmptyRow;
    private int botEndRow;
    private int botHeaderRow;
    private int botStartRow;
    private ArrayList<TLObject> bots;
    private boolean botsEndReached;
    private LongSparseArray<TLObject> botsMap;
    private int changeInfoRow;
    private long chatId;
    private ArrayList<TLObject> contacts;
    private boolean contactsEndReached;
    private int contactsEndRow;
    private int contactsHeaderRow;
    private LongSparseArray<TLObject> contactsMap;
    private int contactsStartRow;
    private TLRPC.Chat currentChat;
    private TLRPC.TL_chatBannedRights defaultBannedRights;
    private int delayResults;
    private ChatUsersActivityDelegate delegate;
    private ActionBarMenuItem doneItem;
    private int dontRestrictBoostersInfoRow;
    private int dontRestrictBoostersRow;
    private int dontRestrictBoostersSliderRow;
    private int editTagRow;
    private int embedLinksRow;
    private StickerEmptyView emptyView;
    private boolean enablePrice;
    private boolean firstLoaded;
    private FlickerLoadingView flickerLoadingView;
    private int gigaConvertRow;
    private int gigaHeaderRow;
    private int gigaInfoRow;
    private int hideMembersInfoRow;
    private int hideMembersRow;
    private boolean hideMembersToggleLoading;
    private LongSparseArray<TLRPC.GroupCallParticipant> ignoredUsers;
    private TLRPC.ChatFull info;
    private String initialBannedRights;
    private boolean initialEnablePrice;
    private boolean initialProfiles;
    private boolean initialSignatures;
    private int initialSlowmode;
    private long initialStarsPrice;
    private boolean isChannel;
    private boolean isCommunity;
    private boolean isEnabledNotRestrictBoosters;
    private boolean isForum;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private ListAdapter listViewAdapter;
    private int loadingHeaderRow;
    private int loadingProgressRow;
    private int loadingUserCellRow;
    private boolean loadingUsers;
    private int manageLinkedPeersRow;
    private int manageTopicsRow;
    private int membersHeaderRow;
    private boolean needOpenSearch;
    private int notRestrictBoosters;
    private boolean openTransitionStarted;
    private ArrayList<TLObject> participants;
    private int participantsDivider2Row;
    private int participantsDividerRow;
    private int participantsEndRow;
    private int participantsInfoRow;
    private LongSparseArray<TLObject> participantsMap;
    private int participantsStartRow;
    private int payInfoRow;
    private int payRow;
    private int permissionsSectionRow;
    private int pinMessagesRow;
    private int priceHeaderRow;
    private int priceInfoRow;
    private int priceRow;
    private boolean profiles;
    private View progressBar;
    private int recentActionsRow;
    private int removedUsersRow;
    private int restricted1SectionRow;
    private int rowCount;
    private ActionBarMenuItem searchItem;
    private SearchAdapter searchListViewAdapter;
    private boolean searching;
    private int selectType;
    private int selectedSlowmode;
    private int sendMediaEmbededLinksRow;
    private boolean sendMediaExpanded;
    private int sendMediaFilesRow;
    private int sendMediaMusicRow;
    private int sendMediaPhotosRow;
    private int sendMediaRow;
    private int sendMediaStickerGifsRow;
    private int sendMediaVideoMessagesRow;
    private int sendMediaVideosRow;
    private int sendMediaVoiceMessagesRow;
    private int sendMessagesRow;
    private int sendPollsRow;
    private int sendReactionsRow;
    private int sendStickersRow;
    private int signMessagesInfoRow;
    private int signMessagesProfilesRow;
    private int signMessagesRow;
    private boolean signatures;
    private int slowmodeInfoRow;
    private int slowmodeRow;
    private int slowmodeSelectRow;
    private long starsPrice;
    private int tagsInfoRow;
    private int tagsRow;
    private boolean transfer;
    private int type;
    private UndoView undoView;

    /* renamed from: org.telegram.ui.ChatUsersActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatUsersActivity this$0;

        public AnonymousClass1(ChatUsersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            ChatUsersActivity.m8633$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (ChatUsersActivity.m8622$$Nest$mcheckDiscard(this.this$0, true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements ChatRightsEditActivity.ChatRightsEditActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ TLObject val$participant;

        public AnonymousClass10(ChatUsersActivity r1, TLObject r2) {
            this.this$0 = r1;
            this.val$participant = r2;
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didChangeOwner(TLRPC.User r1) {
            ChatUsersActivity.m8631$$Nest$monOwnerChaged(this.this$0, r1);
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didSetRights(int r9, TLRPC.TL_chatAdminRights r10, TLRPC.TL_chatBannedRights r11, String r12) {
            TLObject r92 = this.val$participant;
            if ((r92 instanceof TLRPC.ChannelParticipant) == false) goto L6;
            TLRPC.ChannelParticipant r2 = (TLRPC.ChannelParticipant) r92;
            r2.admin_rights = r10;
            r2.banned_rights = r11;
            r2.rank = r12;
            ChatUsersActivity.m8638$$Nest$mupdateParticipantWithRights(this.this$0, r2, r10, r11, 0, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatUsersActivity this$0;

        public AnonymousClass11(ChatUsersActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$12, reason: invalid class name */
    public class AnonymousClass12 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ int val$finalFrom;
        final /* synthetic */ View val$finalProgressView;

        /* renamed from: org.telegram.ui.ChatUsersActivity$12$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass12 this$1;
            final /* synthetic */ RecyclerView.LayoutManager val$layoutManager;

            public AnonymousClass1(AnonymousClass12 r1, RecyclerView.LayoutManager r2) {
                this.this$1 = r1;
                this.val$layoutManager = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.val$finalProgressView.setAlpha(1.0f);
                this.val$layoutManager.stopIgnoringView(this.this$1.val$finalProgressView);
                ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$1.this$0).removeView(this.this$1.val$finalProgressView);
            }
        }

        public AnonymousClass12(ChatUsersActivity r1, View r2, int r3) {
            this.this$0 = r1;
            this.val$finalProgressView = r2;
            this.val$finalFrom = r3;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getViewTreeObserver().removeOnPreDrawListener(this);
            int r0 = ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getChildCount();
            AnimatorSet r1 = new AnimatorSet();
            int r3 = 0;
        L4:
            if (r3 >= r0) goto L12;
            View r7 = ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getChildAt(r3);
            if (r7 == this.val$finalProgressView) goto L11;
            if (ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getChildAdapterPosition(r7) < this.val$finalFrom) goto L11;
            r7.setAlpha(0.0f);
            ObjectAnimator r4 = ObjectAnimator.ofFloat(r7, View.ALPHA, new float[]{0.0f, 1.0f});
            r4.setStartDelay((int) ((Math.min(ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getMeasuredHeight(), Math.max(0, r7.getTop())) / ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getMeasuredHeight()) * 100.0f));
            r4.setDuration(200);
            r1.playTogether(new Animator[]{r4});
        L11:
            r3 = r3 + 1;
            goto L4
        L12:
            View r02 = this.val$finalProgressView;
            if (r02 != null) goto L15;
        L19:
            r1.start();
            return true;
        L15:
            if (r02.getParent() != null) goto L19;
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).addView(this.val$finalProgressView);
            RecyclerView.LayoutManager r03 = ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getLayoutManager();
            if (r03 == null) goto L19;
            r03.ignoreView(this.val$finalProgressView);
            View r32 = this.val$finalProgressView;
            ObjectAnimator r2 = ObjectAnimator.ofFloat(r32, View.ALPHA, new float[]{r32.getAlpha(), 0.0f});
            r2.addListener(new AnonymousClass1(this, r03));
            r2.start();
            goto L19
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$13, reason: invalid class name */
    public class AnonymousClass13 extends ChatRightsEditActivity {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ boolean[] val$needShowBulletin;
        final /* synthetic */ long val$peerId;

        public AnonymousClass13(ChatUsersActivity r14, long r15, long r17, TLRPC.TL_chatAdminRights r19, TLRPC.TL_chatBannedRights r20, TLRPC.TL_chatBannedRights r21, String r22, int r23, boolean r24, boolean r25, String r26, boolean[] r27, long r28) {
            this.this$0 = r14;
            this.val$needShowBulletin = r27;
            this.val$peerId = r28;
            super(r15, r17, r19, r20, r21, r22, r23, r24, r25, r26);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onTransitionAnimationEnd(boolean r3, boolean r4) {
            if (r3 == true) goto L18;
            if (r4 == true) goto L5;
            return;
        L5:
            if (this.val$needShowBulletin[0] == true) goto L7;
            return;
        L7:
            if (BulletinFactory.canShowBulletin(this.this$0) == true) goto L9;
            return;
        L9:
            if (this.val$peerId <= 0) goto L14;
            TLRPC.User r32 = getMessagesController().getUser(Long.valueOf(this.val$peerId));
            if (r32 == null) goto L22;
            BulletinFactory.createPromoteToAdminBulletin(this.this$0, r32.first_name).show();
            return;
        L22:
            return;
        L14:
            TLRPC.Chat r33 = getMessagesController().getChat(Long.valueOf(-this.val$peerId));
            if (r33 == null) goto L23;
            BulletinFactory.createPromoteToAdminBulletin(this.this$0, r33.title).show();
            return;
        L23:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$14, reason: invalid class name */
    public class AnonymousClass14 implements ChatRightsEditActivity.ChatRightsEditActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ int val$date;
        final /* synthetic */ boolean val$isAdmin;
        final /* synthetic */ boolean[] val$needShowBulletin;
        final /* synthetic */ long val$peerId;
        final /* synthetic */ int val$type;

        public AnonymousClass14(ChatUsersActivity r1, int r2, long r3, int r5, boolean r6, boolean[] r7) {
            this.this$0 = r1;
            this.val$type = r2;
            this.val$peerId = r3;
            this.val$date = r5;
            this.val$isAdmin = r6;
            this.val$needShowBulletin = r7;
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didChangeOwner(TLRPC.User r1) {
            ChatUsersActivity.m8631$$Nest$monOwnerChaged(this.this$0, r1);
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didSetRights(int r8, TLRPC.TL_chatAdminRights r9, TLRPC.TL_chatBannedRights r10, String r11) {
            int r0 = this.val$type;
            if (r0 != 0) goto L35;
            int r2 = 0;
        L6:
            if (r2 >= ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).size()) goto L30;
            TLObject r3 = (TLObject) ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).get(r2);
            if ((r3 instanceof TLRPC.ChannelParticipant) == false) goto L20;
            if (MessageObject.getPeerId(((TLRPC.ChannelParticipant) r3).peer) != this.val$peerId) goto L29;
            if (r8 != 1) goto L13;
            TLRPC.ChannelParticipant r32 = new TLRPC.TL_channelParticipantAdmin();
        L14:
            r32.admin_rights = r9;
            r32.banned_rights = r10;
            r32.inviter_id = this.this$0.getUserConfig().getClientUserId();
            if (this.val$peerId <= 0) goto L17;
            TLRPC.TL_peerUser r92 = new TLRPC.TL_peerUser();
            r32.peer = r92;
            r92.user_id = this.val$peerId;
        L18:
            r32.date = this.val$date;
            r32.flags |= 4;
            r32.rank = r11;
            ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).set(r2, r32);
            goto L30
        L17:
            TLRPC.TL_peerChannel r93 = new TLRPC.TL_peerChannel();
            r32.peer = r93;
            r93.channel_id = -this.val$peerId;
            goto L18
        L13:
            r32 = new TLRPC.TL_channelParticipant();
        L29:
            r2 = r2 + 1;
            goto L6
        L20:
            if ((r3 instanceof TLRPC.ChatParticipant) == false) goto L29;
            TLRPC.ChatParticipant r33 = (TLRPC.ChatParticipant) r3;
            if (r8 != 1) goto L24;
            TLRPC.ChatParticipant r4 = new TLRPC.TL_chatParticipantAdmin();
        L25:
            r4.user_id = r33.user_id;
            r4.date = r33.date;
            r4.inviter_id = r33.inviter_id;
            int r34 = ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).participants.participants.indexOf(r33);
            if (r34 < 0) goto L28;
            ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).participants.participants.set(r34, r4);
        L28:
            ChatUsersActivity.m8630$$Nest$mloadChatParticipants(this.this$0, 0, 200);
            goto L29
        L24:
            r4 = new TLRPC.TL_chatParticipant();
        L30:
            if (r8 == 1) goto L32;
            return;
        L32:
            if (this.val$isAdmin == true) goto L45;
            this.val$needShowBulletin[0] = true;
            return;
        L45:
            return;
        L35:
            if (r0 != 1) goto L46;
            if (r8 != 0) goto L47;
            ChatUsersActivity.m8634$$Nest$mremoveParticipants(this.this$0, this.val$peerId);
            return;
        L47:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$15, reason: invalid class name */
    public class AnonymousClass15 implements ChatRightsEditActivity.ChatRightsEditActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ TLObject val$participant;
        final /* synthetic */ boolean val$removeFragment;
        final /* synthetic */ long val$user_id;

        public AnonymousClass15(ChatUsersActivity r1, TLObject r2, long r3, boolean r5) {
            this.this$0 = r1;
            this.val$participant = r2;
            this.val$user_id = r3;
            this.val$removeFragment = r5;
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didChangeOwner(TLRPC.User r1) {
            ChatUsersActivity.m8631$$Nest$monOwnerChaged(this.this$0, r1);
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didSetRights(int r3, TLRPC.TL_chatAdminRights r4, TLRPC.TL_chatBannedRights r5, String r6) {
            TLObject r0 = this.val$participant;
            if ((r0 instanceof TLRPC.ChannelParticipant) == false) goto L6;
            TLRPC.ChannelParticipant r02 = (TLRPC.ChannelParticipant) r0;
            r02.admin_rights = r4;
            r02.banned_rights = r5;
            r02.rank = r6;
        L6:
            if (ChatUsersActivity.m8538$$Nest$fgetdelegate(this.this$0) == null) goto L11;
            if (r3 != 1) goto L11;
            ChatUsersActivity.m8538$$Nest$fgetdelegate(this.this$0).didSelectUser(this.val$user_id);
        L14:
            if (this.val$removeFragment == false) goto L17;
            this.this$0.removeSelfFromStack();
            return;
        L17:
            return;
        L11:
            if (ChatUsersActivity.m8538$$Nest$fgetdelegate(this.this$0) == null) goto L14;
            ChatUsersActivity.m8538$$Nest$fgetdelegate(this.this$0).didAddParticipantToList(this.val$user_id, this.val$participant);
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$16, reason: invalid class name */
    public class AnonymousClass16 implements ChatRightsEditActivity.ChatRightsEditActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ TLObject val$participant;

        public AnonymousClass16(ChatUsersActivity r1, TLObject r2) {
            this.this$0 = r1;
            this.val$participant = r2;
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didChangeOwner(TLRPC.User r1) {
            ChatUsersActivity.m8631$$Nest$monOwnerChaged(this.this$0, r1);
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didSetRights(int r9, TLRPC.TL_chatAdminRights r10, TLRPC.TL_chatBannedRights r11, String r12) {
            TLObject r92 = this.val$participant;
            if ((r92 instanceof TLRPC.ChannelParticipant) == false) goto L6;
            TLRPC.ChannelParticipant r2 = (TLRPC.ChannelParticipant) r92;
            r2.admin_rights = r10;
            r2.banned_rights = r11;
            r2.rank = r12;
            ChatUsersActivity.m8638$$Nest$mupdateParticipantWithRights(this.this$0, r2, r10, r11, 0, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$17, reason: invalid class name */
    public class AnonymousClass17 implements ChatRightsEditActivity.ChatRightsEditActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ TLObject val$participant;

        public AnonymousClass17(ChatUsersActivity r1, TLObject r2) {
            this.this$0 = r1;
            this.val$participant = r2;
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didChangeOwner(TLRPC.User r1) {
            ChatUsersActivity.m8631$$Nest$monOwnerChaged(this.this$0, r1);
        }

        @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
        public void didSetRights(int r9, TLRPC.TL_chatAdminRights r10, TLRPC.TL_chatBannedRights r11, String r12) {
            TLObject r92 = this.val$participant;
            if ((r92 instanceof TLRPC.ChannelParticipant) == false) goto L6;
            TLRPC.ChannelParticipant r2 = (TLRPC.ChannelParticipant) r92;
            r2.admin_rights = r10;
            r2.banned_rights = r11;
            r2.rank = r12;
            ChatUsersActivity.m8638$$Nest$mupdateParticipantWithRights(this.this$0, r2, r10, r11, 0, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ ChatUsersActivity this$0;

        public AnonymousClass2(ChatUsersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            ChatUsersActivity.m8589$$Nest$fgetsearchListViewAdapter(this.this$0).searchUsers(null);
            ChatUsersActivity.m8619$$Nest$fputsearching(this.this$0, false);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setAnimateEmptyView(false, 0);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setAdapter(ChatUsersActivity.m8560$$Nest$fgetlistViewAdapter(this.this$0));
            ChatUsersActivity.m8560$$Nest$fgetlistViewAdapter(this.this$0).notifyDataSetChanged();
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setFastScrollVisible(true);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(false);
            if (ChatUsersActivity.m8539$$Nest$fgetdoneItem(this.this$0) == null) goto L6;
            ChatUsersActivity.m8539$$Nest$fgetdoneItem(this.this$0).setVisibility(0);
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            ChatUsersActivity.m8619$$Nest$fputsearching(this.this$0, true);
            if (ChatUsersActivity.m8539$$Nest$fgetdoneItem(this.this$0) == null) goto L6;
            ChatUsersActivity.m8539$$Nest$fgetdoneItem(this.this$0).setVisibility(8);
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r4) {
            if (ChatUsersActivity.m8589$$Nest$fgetsearchListViewAdapter(this.this$0) != null) goto L5;
            return;
        L5:
            String r42 = r4.getText().toString();
            if (ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getAdapter() != null) goto L8;
            int r0 = 0;
        L9:
            ChatUsersActivity.m8589$$Nest$fgetsearchListViewAdapter(this.this$0).searchUsers(r42);
            if (TextUtils.isEmpty(r42) == true) goto L12;
        L18:
            ChatUsersActivity.m8584$$Nest$fgetprogressBar(this.this$0).setVisibility(8);
            ChatUsersActivity.m8548$$Nest$fgetflickerLoadingView(this.this$0).setVisibility(0);
            return;
        L12:
            if (ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0) == null) goto L18;
            if (ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getAdapter() == ChatUsersActivity.m8560$$Nest$fgetlistViewAdapter(this.this$0)) goto L18;
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setAnimateEmptyView(false, 0);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setAdapter(ChatUsersActivity.m8560$$Nest$fgetlistViewAdapter(this.this$0));
            if (r0 != 0) goto L18;
            ChatUsersActivity.m8636$$Nest$mshowItemsAnimated(this.this$0, 0);
            goto L18
        L8:
            r0 = ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getAdapter().getItemCount();
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ ChatUsersActivity this$0;

        public AnonymousClass3(ChatUsersActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            View r0 = this.this$0.fragmentView;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends LinearLayoutManager {
        final /* synthetic */ ChatUsersActivity this$0;

        public AnonymousClass4(ChatUsersActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int r2, RecyclerView.Recycler r3, RecyclerView.State r4) {
            if (ChatUsersActivity.m8547$$Nest$fgetfirstLoaded(this.this$0) == true) goto L11;
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 0) goto L11;
            if (ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).size() != 0) goto L11;
            return 0;
        L11:
            return super.scrollVerticallyBy(r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends DefaultItemAnimator {
        AnimationNotificationsLocker notificationsLocker;
        final /* synthetic */ ChatUsersActivity this$0;

        public AnonymousClass5(ChatUsersActivity r1) {
            this.this$0 = r1;
            this.notificationsLocker = new AnimationNotificationsLocker();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onAllAnimationsDone() {
            super.onAllAnimationsDone();
            this.notificationsLocker.unlock();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onChangeAnimationUpdate(r1);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).invalidate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).invalidate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void runPendingAnimations() {
            boolean r0 = this.mPendingRemovals.isEmpty();
            boolean r1 = this.mPendingMoves.isEmpty();
            boolean r2 = this.mPendingChanges.isEmpty();
            boolean r3 = this.mPendingAdditions.isEmpty();
            if (r0 == false) goto L7;
            if (r1 == false) goto L7;
            if (r3 == false) goto L7;
            if (r2 == false) goto L7;
        L8:
            super.runPendingAnimations();
            return;
        L7:
            this.notificationsLocker.lock();
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements ChatUsersActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;

        public AnonymousClass6(ChatUsersActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ChatUsersActivity.ChatUsersActivityDelegate
        public void didAddParticipantToList(long r3, TLObject r5) {
            if (ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).get(r3) != null) goto L6;
            DiffCallback r0 = this.this$0.saveState();
            ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).add(r5);
            ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).put(r3, r5);
            ChatUsersActivity r32 = this.this$0;
            ChatUsersActivity.m8637$$Nest$msortUsers(r32, ChatUsersActivity.m8569$$Nest$fgetparticipants(r32));
            this.this$0.updateListAnimated(r0);
            return;
        }

        @Override // org.telegram.ui.ChatUsersActivity.ChatUsersActivityDelegate
        public void didKickParticipant(long r6) {
            if (ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).get(r6) != null) goto L10;
            DiffCallback r0 = this.this$0.saveState();
            TLRPC.TL_channelParticipantBanned r1 = new TLRPC.TL_channelParticipantBanned();
            if (r6 <= 0) goto L7;
            TLRPC.TL_peerUser r2 = new TLRPC.TL_peerUser();
            r1.peer = r2;
            r2.user_id = r6;
        L8:
            r1.date = this.this$0.getConnectionsManager().getCurrentTime();
            r1.kicked_by = this.this$0.getAccountInstance().getUserConfig().clientUserId;
            ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).kicked_count++;
            ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).add(r1);
            ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).put(r6, r1);
            ChatUsersActivity r62 = this.this$0;
            ChatUsersActivity.m8637$$Nest$msortUsers(r62, ChatUsersActivity.m8569$$Nest$fgetparticipants(r62));
            this.this$0.updateListAnimated(r0);
            return;
        L7:
            TLRPC.TL_peerChannel r22 = new TLRPC.TL_peerChannel();
            r1.peer = r22;
            r22.channel_id = -r6;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements ChatUsersActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;

        /* renamed from: $r8$lambda$DamzrUyI4iW5mraOAvY8nFi-WiE, reason: not valid java name */
        public static /* synthetic */ void m8640$r8$lambda$DamzrUyI4iW5mraOAvY8nFiWiE(AnonymousClass7 r0, TLRPC.User r1) {
            r0.lambda$didSelectUser$0(r1);
        }

        public AnonymousClass7(ChatUsersActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$didSelectUser$0(TLRPC.User r2) {
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L6;
            BulletinFactory.createPromoteToAdminBulletin(this.this$0, r2.first_name).show();
            return;
        }

        @Override // org.telegram.ui.ChatUsersActivity.ChatUsersActivityDelegate
        public void didAddParticipantToList(long r3, TLObject r5) {
            if (r5 != null) goto L4;
            return;
        L4:
            if (ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).get(r3) != null) goto L8;
            DiffCallback r0 = this.this$0.saveState();
            ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).add(r5);
            ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).put(r3, r5);
            ChatUsersActivity.sortAdmins(ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0));
            this.this$0.updateListAnimated(r0);
            return;
        }

        @Override // org.telegram.ui.ChatUsersActivity.ChatUsersActivityDelegate
        public void didChangeOwner(TLRPC.User r1) {
            ChatUsersActivity.m8631$$Nest$monOwnerChaged(this.this$0, r1);
        }

        @Override // org.telegram.ui.ChatUsersActivity.ChatUsersActivityDelegate
        public void didSelectUser(long r5) {
            TLRPC.User r0 = this.this$0.getMessagesController().getUser(Long.valueOf(r5));
            if (r0 == null) goto L6;
            AndroidUtilities.runOnUIThread(new ChatUsersActivity$7$$ExternalSyntheticLambda0(this, r0), 200);
        L6:
            if (ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).get(r5) != null) goto L9;
            DiffCallback r52 = this.this$0.saveState();
            TLRPC.TL_channelParticipantAdmin r6 = new TLRPC.TL_channelParticipantAdmin();
            TLRPC.TL_peerUser r1 = new TLRPC.TL_peerUser();
            r6.peer = r1;
            r1.user_id = r0.id;
            r6.date = this.this$0.getConnectionsManager().getCurrentTime();
            r6.promoted_by = this.this$0.getAccountInstance().getUserConfig().clientUserId;
            ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).add(r6);
            ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0).put(r0.id, r6);
            ChatUsersActivity.sortAdmins(ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0));
            this.this$0.updateListAnimated(r52);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements GroupCreateActivity.ContactsAddActivityDelegate {
        final /* synthetic */ ChatUsersActivity this$0;
        final /* synthetic */ GroupCreateActivity val$fragment;

        /* renamed from: $r8$lambda$DsrossrdWfcoCC_pML1Mtk5N1-E, reason: not valid java name */
        public static /* synthetic */ void m8641$r8$lambda$DsrossrdWfcoCC_pML1Mtk5N1E(AnonymousClass8 r0, TLRPC.User r1) {
            r0.lambda$didSelectUsers$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$PA6hqLhR41X8pAYxwG7PbJlMIiA(TLRPC.User r0) {
        }

        public AnonymousClass8(ChatUsersActivity r1, GroupCreateActivity r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        private /* synthetic */ void lambda$didSelectUsers$0(TLRPC.User r9) {
            DiffCallback r0 = this.this$0.saveState();
            if (ChatUsersActivity.m8534$$Nest$fgetcontactsMap(this.this$0) != null) goto L5;
        L7:
            ArrayList r1 = ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0);
        L9:
            if (ChatUsersActivity.m8534$$Nest$fgetcontactsMap(this.this$0) != null) goto L11;
        L13:
            LongSparseArray r2 = ChatUsersActivity.m8574$$Nest$fgetparticipantsMap(this.this$0);
        L15:
            if (r2.get(r9.id) != null) goto L21;
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == false) goto L19;
            TLRPC.TL_channelParticipant r3 = new TLRPC.TL_channelParticipant();
            r3.inviter_id = this.this$0.getUserConfig().getClientUserId();
            TLRPC.TL_peerUser r5 = new TLRPC.TL_peerUser();
            r3.peer = r5;
            r5.user_id = r9.id;
            r3.date = this.this$0.getConnectionsManager().getCurrentTime();
            r1.add(0, r3);
            r2.put(r9.id, r3);
            goto L21
        L19:
            TLRPC.TL_chatParticipant r32 = new TLRPC.TL_chatParticipant();
            r32.user_id = r9.id;
            r32.inviter_id = this.this$0.getUserConfig().getClientUserId();
            r1.add(0, r32);
            r2.put(r9.id, r32);
        L21:
            if (r1 != ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0)) goto L23;
            ChatUsersActivity.sortAdmins(ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0));
        L23:
            this.this$0.updateListAnimated(r0);
            return;
        L11:
            if (ChatUsersActivity.m8534$$Nest$fgetcontactsMap(this.this$0).size() == 0) goto L13;
            r2 = ChatUsersActivity.m8534$$Nest$fgetcontactsMap(this.this$0);
            goto L15
        L5:
            if (ChatUsersActivity.m8534$$Nest$fgetcontactsMap(this.this$0).size() == 0) goto L7;
            r1 = ChatUsersActivity.m8531$$Nest$fgetcontacts(this.this$0);
            goto L9
        }

        @Override // org.telegram.ui.GroupCreateActivity.ContactsAddActivityDelegate
        public void didSelectUsers(ArrayList<TLRPC.User> r10, int r11) {
            if (this.val$fragment.getParentActivity() != null) goto L5;
            return;
        L5:
            this.this$0.getMessagesController().addUsersToChat(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0), this.this$0, r10, r11, new ChatUsersActivity$8$$ExternalSyntheticLambda0(this), new ChatUsersActivity$8$$ExternalSyntheticLambda1(), null);
        }

        @Override // org.telegram.ui.GroupCreateActivity.ContactsAddActivityDelegate
        public void needAddBot(TLRPC.User r11) {
            ChatUsersActivity.m8632$$Nest$mopenRightsEdit(this.this$0, r11.id, null, null, null, _UrlKt.FRAGMENT_ENCODE_SET, true, 0, false);
        }
    }

    /* renamed from: org.telegram.ui.ChatUsersActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends GigagroupConvertAlert {
        final /* synthetic */ ChatUsersActivity this$0;

        public static /* synthetic */ void $r8$lambda$600FLFruLZjrYPQxVOeTDaTXJ2Y(AnonymousClass9 r0, boolean r1) {
            r0.lambda$onCovert$0(r1);
        }

        public AnonymousClass9(ChatUsersActivity r1, Context r2, BaseFragment r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        private /* synthetic */ void lambda$onCovert$0(boolean r4) {
            if (r4 == true) goto L4;
            return;
        L4:
            if (ChatUsersActivity.access$400(this.this$0) == null) goto L12;
            BaseFragment r42 = ChatUsersActivity.access$600(this.this$0).getFragmentStack().get(ChatUsersActivity.access$500(this.this$0).getFragmentStack().size() - 2);
            if ((r42 instanceof ChatEditActivity) == false) goto L9;
            r42.removeSelfFromStack();
            Bundle r43 = new Bundle();
            r43.putLong("chat_id", ChatUsersActivity.m8530$$Nest$fgetchatId(this.this$0));
            ChatEditActivity r0 = new ChatEditActivity(r43);
            r0.setInfo(ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0));
            ChatUsersActivity.access$800(this.this$0).addFragmentToStack(r0, ChatUsersActivity.access$700(this.this$0).getFragmentStack().size() - 1);
            this.this$0.finishFragment();
            r0.showConvertTooltip();
            return;
        L9:
            this.this$0.finishFragment();
            return;
        }

        @Override // org.telegram.ui.Components.GigagroupConvertAlert
        public void onCancel() {
        }

        @Override // org.telegram.ui.Components.GigagroupConvertAlert
        public void onCovert() {
            this.this$0.getMessagesController().convertToGigaGroup(this.this$0.getParentActivity(), ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0), this.this$0, new ChatUsersActivity$9$$ExternalSyntheticLambda0(this));
        }
    }

    public interface ChatUsersActivityDelegate {
        void didAddParticipantToList(long r1, TLObject r3);

        default void didChangeOwner(TLRPC.User r1) {
        }

        default void didKickParticipant(long r1) {
        }

        default void didSelectUser(long r1) {
        }
    }

    public class DiffCallback extends DiffUtil.Callback {
        SparseIntArray newPositionToItem;
        int oldBotEndRow;
        int oldBotStartRow;
        private ArrayList<TLObject> oldBots;
        private ArrayList<TLObject> oldContacts;
        int oldContactsEndRow;
        int oldContactsStartRow;
        private ArrayList<TLObject> oldParticipants;
        int oldParticipantsEndRow;
        int oldParticipantsStartRow;
        SparseIntArray oldPositionToItem;
        int oldRowCount;
        final /* synthetic */ ChatUsersActivity this$0;

        /* renamed from: -$$Nest$fgetoldBots, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m8642$$Nest$fgetoldBots(DiffCallback r0) {
            return r0.oldBots;
        }

        /* renamed from: -$$Nest$fgetoldContacts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m8643$$Nest$fgetoldContacts(DiffCallback r0) {
            return r0.oldContacts;
        }

        /* renamed from: -$$Nest$fgetoldParticipants, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m8644$$Nest$fgetoldParticipants(DiffCallback r0) {
            return r0.oldParticipants;
        }

        private DiffCallback(ChatUsersActivity r1) {
            this.this$0 = r1;
            this.oldPositionToItem = new SparseIntArray();
            this.newPositionToItem = new SparseIntArray();
            this.oldParticipants = new ArrayList();
            this.oldBots = new ArrayList();
            this.oldContacts = new ArrayList();
        }

        public /* synthetic */ DiffCallback(ChatUsersActivity r1, ChatUsersActivityIA r2) {
            this(r1);
        }

        private void put(int r1, int r2, SparseIntArray r3) {
            if (r2 < 0) goto L5;
            r3.put(r2, r1);
            return;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r2, int r3) {
            if (areItemsTheSame(r2, r3) == true) goto L5;
            return false;
        L5:
            if (ChatUsersActivity.m8587$$Nest$fgetrestricted1SectionRow(this.this$0) != r3) goto L7;
            return false;
        L7:
            return true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r3, int r4) {
            if (r3 < this.oldBotStartRow) goto L13;
            if (r3 >= this.oldBotEndRow) goto L13;
            if (r4 < ChatUsersActivity.m8527$$Nest$fgetbotStartRow(this.this$0)) goto L13;
            if (r4 >= ChatUsersActivity.m8525$$Nest$fgetbotEndRow(this.this$0)) goto L13;
            return this.oldBots.get(r3 - this.oldBotStartRow).equals(ChatUsersActivity.m8528$$Nest$fgetbots(this.this$0).get(r4 - ChatUsersActivity.m8527$$Nest$fgetbotStartRow(this.this$0)));
        L13:
            if (r3 < this.oldContactsStartRow) goto L23;
            if (r3 >= this.oldContactsEndRow) goto L23;
            if (r4 < ChatUsersActivity.m8535$$Nest$fgetcontactsStartRow(this.this$0)) goto L23;
            if (r4 >= ChatUsersActivity.m8532$$Nest$fgetcontactsEndRow(this.this$0)) goto L23;
            return this.oldContacts.get(r3 - this.oldContactsStartRow).equals(ChatUsersActivity.m8531$$Nest$fgetcontacts(this.this$0).get(r4 - ChatUsersActivity.m8535$$Nest$fgetcontactsStartRow(this.this$0)));
        L23:
            if (r3 < this.oldParticipantsStartRow) goto L33;
            if (r3 >= this.oldParticipantsEndRow) goto L33;
            if (r4 < ChatUsersActivity.m8575$$Nest$fgetparticipantsStartRow(this.this$0)) goto L33;
            if (r4 >= ChatUsersActivity.m8572$$Nest$fgetparticipantsEndRow(this.this$0)) goto L33;
            return this.oldParticipants.get(r3 - this.oldParticipantsStartRow).equals(ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).get(r4 - ChatUsersActivity.m8575$$Nest$fgetparticipantsStartRow(this.this$0)));
        L33:
            if (this.oldPositionToItem.get(r3) != this.newPositionToItem.get(r4)) goto L36;
            return true;
        L36:
            return false;
        }

        public void fillPositions(SparseIntArray r5) {
            r5.clear();
            put(1, ChatUsersActivity.m8585$$Nest$fgetrecentActionsRow(this.this$0), r5);
            put(2, ChatUsersActivity.m8519$$Nest$fgetaddNewRow(this.this$0), r5);
            put(3, ChatUsersActivity.m8518$$Nest$fgetaddNew2Row(this.this$0), r5);
            put(4, ChatUsersActivity.m8520$$Nest$fgetaddNewSectionRow(this.this$0), r5);
            put(5, ChatUsersActivity.m8587$$Nest$fgetrestricted1SectionRow(this.this$0), r5);
            put(6, ChatUsersActivity.m8571$$Nest$fgetparticipantsDividerRow(this.this$0), r5);
            put(7, ChatUsersActivity.m8570$$Nest$fgetparticipantsDivider2Row(this.this$0), r5);
            put(8, ChatUsersActivity.m8550$$Nest$fgetgigaHeaderRow(this.this$0), r5);
            put(9, ChatUsersActivity.m8549$$Nest$fgetgigaConvertRow(this.this$0), r5);
            put(10, ChatUsersActivity.m8551$$Nest$fgetgigaInfoRow(this.this$0), r5);
            put(11, ChatUsersActivity.m8573$$Nest$fgetparticipantsInfoRow(this.this$0), r5);
            put(12, ChatUsersActivity.m8524$$Nest$fgetblockedEmptyRow(this.this$0), r5);
            put(13, ChatUsersActivity.m8578$$Nest$fgetpermissionsSectionRow(this.this$0), r5);
            put(14, ChatUsersActivity.m8603$$Nest$fgetsendMessagesRow(this.this$0), r5);
            put(15, ChatUsersActivity.m8598$$Nest$fgetsendMediaRow(this.this$0), r5);
            put(16, ChatUsersActivity.m8606$$Nest$fgetsendStickersRow(this.this$0), r5);
            put(17, ChatUsersActivity.m8604$$Nest$fgetsendPollsRow(this.this$0), r5);
            put(18, ChatUsersActivity.m8544$$Nest$fgetembedLinksRow(this.this$0), r5);
            put(19, ChatUsersActivity.m8521$$Nest$fgetaddUsersRow(this.this$0), r5);
            put(20, ChatUsersActivity.m8565$$Nest$fgetmanageLinkedPeersRow(this.this$0), r5);
            put(21, ChatUsersActivity.m8579$$Nest$fgetpinMessagesRow(this.this$0), r5);
            put(22, ChatUsersActivity.m8543$$Nest$fgeteditTagRow(this.this$0), r5);
            int r2 = 23;
            put(23, ChatUsersActivity.m8605$$Nest$fgetsendReactionsRow(this.this$0), r5);
            if (ChatUsersActivity.m8558$$Nest$fgetisForum(this.this$0) == false) goto L5;
            r2 = 24;
            put(24, ChatUsersActivity.m8566$$Nest$fgetmanageTopicsRow(this.this$0), r5);
        L5:
            put(r2 + 1, ChatUsersActivity.m8529$$Nest$fgetchangeInfoRow(this.this$0), r5);
            put(r2 + 2, ChatUsersActivity.m8586$$Nest$fgetremovedUsersRow(this.this$0), r5);
            put(r2 + 3, ChatUsersActivity.m8533$$Nest$fgetcontactsHeaderRow(this.this$0), r5);
            put(r2 + 4, ChatUsersActivity.m8526$$Nest$fgetbotHeaderRow(this.this$0), r5);
            put(r2 + 5, ChatUsersActivity.m8567$$Nest$fgetmembersHeaderRow(this.this$0), r5);
            put(r2 + 6, ChatUsersActivity.m8612$$Nest$fgetslowmodeRow(this.this$0), r5);
            put(r2 + 7, ChatUsersActivity.m8613$$Nest$fgetslowmodeSelectRow(this.this$0), r5);
            put(r2 + 8, ChatUsersActivity.m8611$$Nest$fgetslowmodeInfoRow(this.this$0), r5);
            put(r2 + 9, ChatUsersActivity.m8541$$Nest$fgetdontRestrictBoostersRow(this.this$0), r5);
            put(r2 + 10, ChatUsersActivity.m8542$$Nest$fgetdontRestrictBoostersSliderRow(this.this$0), r5);
            put(r2 + 11, ChatUsersActivity.m8540$$Nest$fgetdontRestrictBoostersInfoRow(this.this$0), r5);
            put(r2 + 12, ChatUsersActivity.m8562$$Nest$fgetloadingProgressRow(this.this$0), r5);
            put(r2 + 13, ChatUsersActivity.m8563$$Nest$fgetloadingUserCellRow(this.this$0), r5);
            put(r2 + 14, ChatUsersActivity.m8561$$Nest$fgetloadingHeaderRow(this.this$0), r5);
            put(r2 + 15, ChatUsersActivity.m8609$$Nest$fgetsignMessagesRow(this.this$0), r5);
            put(r2 + 16, ChatUsersActivity.m8608$$Nest$fgetsignMessagesProfilesRow(this.this$0), r5);
            put(r2 + 17, ChatUsersActivity.m8607$$Nest$fgetsignMessagesInfoRow(this.this$0), r5);
            put(r2 + 18, ChatUsersActivity.m8577$$Nest$fgetpayRow(this.this$0), r5);
            put(r2 + 19, ChatUsersActivity.m8576$$Nest$fgetpayInfoRow(this.this$0), r5);
            put(r2 + 20, ChatUsersActivity.m8580$$Nest$fgetpriceHeaderRow(this.this$0), r5);
            put(r2 + 21, ChatUsersActivity.m8582$$Nest$fgetpriceRow(this.this$0), r5);
            put(r2 + 22, ChatUsersActivity.m8581$$Nest$fgetpriceInfoRow(this.this$0), r5);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return ChatUsersActivity.m8588$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.oldRowCount;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ChatUsersActivity this$0;

        /* renamed from: org.telegram.ui.ChatUsersActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ TextCheckCell2 val$checkCell;

            public AnonymousClass1(ListAdapter r1, TextCheckCell2 r2) {
                this.this$1 = r1;
                this.val$checkCell = r2;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (ChatObject.canBlockUsers(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$1.this$0)) == true) goto L5;
                return;
            L5:
                boolean r0 = !this.val$checkCell.isChecked();
                this.val$checkCell.setChecked(r0);
                ChatUsersActivity.m8635$$Nest$msetSendMediaEnabled(this.this$1.this$0, r0);
            }
        }

        /* renamed from: $r8$lambda$Q-2jK49SO0jTDGESgO_XVPqP7y8, reason: not valid java name */
        public static /* synthetic */ void m8645$r8$lambda$Q2jK49SO0jTDGESgO_XVPqP7y8(ListAdapter r0, Integer r1) {
            r0.lambda$onBindViewHolder$4(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$UZhAK3bNkJg2guxDwZpwScoPy0c(ListAdapter r0, ManageChatUserCell r1, boolean r2) {
            return r0.lambda$onCreateViewHolder$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$iMTOx0pDMVgklXoTOfx3hoIG0jE(ListAdapter r0, int r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        public static /* synthetic */ CharSequence $r8$lambda$irSbMzWtKnWL3PuWL5_LR9R9gR0(Integer r1, Integer r2) {
            if (r1.intValue() != 0) goto L7;
            return LocaleController.formatPluralStringComma("Stars", r2.intValue());
        L7:
            return _UrlKt.FRAGMENT_ENCODE_SET + r2;
        }

        public static /* synthetic */ void $r8$lambda$rnHBRfGr2nsVsN3g2Z9uGxEU8VY(ListAdapter r0, int r1) {
            r0.lambda$onCreateViewHolder$2(r1);
        }

        public ListAdapter(ChatUsersActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$4(Integer r4) {
            ChatUsersActivity.m8621$$Nest$fputstarsPrice(this.this$0, r4.intValue());
            AndroidUtilities.updateVisibleRow(ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0), ChatUsersActivity.m8581$$Nest$fgetpriceInfoRow(this.this$0));
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$0(ManageChatUserCell r3, boolean r4) {
            return ChatUsersActivity.m8623$$Nest$mcreateMenuForParticipant(this.this$0, ChatUsersActivity.m8560$$Nest$fgetlistViewAdapter(this.this$0).getItem(((Integer) r3.getTag()).intValue()), !r4, r3);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(int r3) {
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) != null) goto L6;
            return;
        L6:
            if (ChatUsersActivity.m8592$$Nest$fgetselectedSlowmode(this.this$0) <= 0) goto L9;
            if (r3 != 0) goto L9;
        L11:
            boolean r0 = true;
        L13:
            ChatUsersActivity.m8620$$Nest$fputselectedSlowmode(this.this$0, r3);
            if (r0 == false) goto L16;
            DiffCallback r32 = this.this$0.saveState();
            ChatUsersActivity.m8639$$Nest$mupdateRows(this.this$0);
            this.this$0.updateListAnimated(r32);
        L16:
            ChatUsersActivity.m8560$$Nest$fgetlistViewAdapter(this.this$0).notifyItemChanged(ChatUsersActivity.m8611$$Nest$fgetslowmodeInfoRow(this.this$0));
            return;
        L9:
            if (ChatUsersActivity.m8592$$Nest$fgetselectedSlowmode(this.this$0) != 0) goto L12;
            if (r3 > 0) goto L11;
        L12:
            r0 = false;
            goto L13
        }

        private /* synthetic */ void lambda$onCreateViewHolder$2(int r1) {
            ChatUsersActivity.m8618$$Nest$fputnotRestrictBoosters(this.this$0, r1 + 1);
        }

        public TLObject getItem(int r2) {
            if (r2 < ChatUsersActivity.m8575$$Nest$fgetparticipantsStartRow(this.this$0)) goto L9;
            if (r2 >= ChatUsersActivity.m8572$$Nest$fgetparticipantsEndRow(this.this$0)) goto L9;
            return (TLObject) ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).get(r2 - ChatUsersActivity.m8575$$Nest$fgetparticipantsStartRow(this.this$0));
        L9:
            if (r2 < ChatUsersActivity.m8535$$Nest$fgetcontactsStartRow(this.this$0)) goto L15;
            if (r2 >= ChatUsersActivity.m8532$$Nest$fgetcontactsEndRow(this.this$0)) goto L15;
            return (TLObject) ChatUsersActivity.m8531$$Nest$fgetcontacts(this.this$0).get(r2 - ChatUsersActivity.m8535$$Nest$fgetcontactsStartRow(this.this$0));
        L15:
            if (r2 >= ChatUsersActivity.m8527$$Nest$fgetbotStartRow(this.this$0)) goto L17;
            return null;
        L17:
            if (r2 < ChatUsersActivity.m8525$$Nest$fgetbotEndRow(this.this$0)) goto L19;
            return null;
        L19:
            return (TLObject) ChatUsersActivity.m8528$$Nest$fgetbots(this.this$0).get(r2 - ChatUsersActivity.m8527$$Nest$fgetbotStartRow(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ChatUsersActivity.m8588$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != ChatUsersActivity.m8519$$Nest$fgetaddNewRow(this.this$0)) goto L5;
            return 2;
        L5:
            if (r3 != ChatUsersActivity.m8518$$Nest$fgetaddNew2Row(this.this$0)) goto L7;
            return 2;
        L7:
            if (r3 != ChatUsersActivity.m8585$$Nest$fgetrecentActionsRow(this.this$0)) goto L9;
            return 2;
        L9:
            if (r3 != ChatUsersActivity.m8549$$Nest$fgetgigaConvertRow(this.this$0)) goto L12;
            return 2;
        L12:
            if (r3 < ChatUsersActivity.m8575$$Nest$fgetparticipantsStartRow(this.this$0)) goto L16;
            if (r3 >= ChatUsersActivity.m8572$$Nest$fgetparticipantsEndRow(this.this$0)) goto L16;
        L23:
            return 0;
        L16:
            if (r3 < ChatUsersActivity.m8527$$Nest$fgetbotStartRow(this.this$0)) goto L20;
            if (r3 < ChatUsersActivity.m8525$$Nest$fgetbotEndRow(this.this$0)) goto L23;
        L20:
            if (r3 < ChatUsersActivity.m8535$$Nest$fgetcontactsStartRow(this.this$0)) goto L25;
            if (r3 < ChatUsersActivity.m8532$$Nest$fgetcontactsEndRow(this.this$0)) goto L23;
        L25:
            if (r3 != ChatUsersActivity.m8520$$Nest$fgetaddNewSectionRow(this.this$0)) goto L27;
            return 3;
        L27:
            if (r3 != ChatUsersActivity.m8571$$Nest$fgetparticipantsDividerRow(this.this$0)) goto L29;
            return 3;
        L29:
            if (r3 != ChatUsersActivity.m8570$$Nest$fgetparticipantsDivider2Row(this.this$0)) goto L32;
            return 3;
        L32:
            if (r3 != ChatUsersActivity.m8587$$Nest$fgetrestricted1SectionRow(this.this$0)) goto L34;
            return 5;
        L34:
            if (r3 != ChatUsersActivity.m8578$$Nest$fgetpermissionsSectionRow(this.this$0)) goto L36;
            return 5;
        L36:
            if (r3 != ChatUsersActivity.m8612$$Nest$fgetslowmodeRow(this.this$0)) goto L38;
            return 5;
        L38:
            if (r3 != ChatUsersActivity.m8550$$Nest$fgetgigaHeaderRow(this.this$0)) goto L40;
            return 5;
        L40:
            if (r3 != ChatUsersActivity.m8580$$Nest$fgetpriceHeaderRow(this.this$0)) goto L43;
            return 5;
        L43:
            if (r3 != ChatUsersActivity.m8573$$Nest$fgetparticipantsInfoRow(this.this$0)) goto L45;
            return 1;
        L45:
            if (r3 != ChatUsersActivity.m8611$$Nest$fgetslowmodeInfoRow(this.this$0)) goto L47;
            return 1;
        L47:
            if (r3 != ChatUsersActivity.m8540$$Nest$fgetdontRestrictBoostersInfoRow(this.this$0)) goto L49;
            return 1;
        L49:
            if (r3 != ChatUsersActivity.m8551$$Nest$fgetgigaInfoRow(this.this$0)) goto L51;
            return 1;
        L51:
            if (r3 != ChatUsersActivity.m8522$$Nest$fgetantiSpamInfoRow(this.this$0)) goto L53;
            return 1;
        L53:
            if (r3 != ChatUsersActivity.m8552$$Nest$fgethideMembersInfoRow(this.this$0)) goto L55;
            return 1;
        L55:
            if (r3 != ChatUsersActivity.m8615$$Nest$fgettagsInfoRow(this.this$0)) goto L57;
            return 1;
        L57:
            if (r3 != ChatUsersActivity.m8607$$Nest$fgetsignMessagesInfoRow(this.this$0)) goto L59;
            return 1;
        L59:
            if (r3 != ChatUsersActivity.m8576$$Nest$fgetpayInfoRow(this.this$0)) goto L61;
            return 1;
        L61:
            if (r3 != ChatUsersActivity.m8581$$Nest$fgetpriceInfoRow(this.this$0)) goto L64;
            return 1;
        L64:
            if (r3 != ChatUsersActivity.m8524$$Nest$fgetblockedEmptyRow(this.this$0)) goto L68;
            return 4;
        L68:
            if (r3 != ChatUsersActivity.m8586$$Nest$fgetremovedUsersRow(this.this$0)) goto L72;
            return 6;
        L72:
            if (r3 != ChatUsersActivity.m8529$$Nest$fgetchangeInfoRow(this.this$0)) goto L74;
            return 7;
        L74:
            if (r3 != ChatUsersActivity.m8521$$Nest$fgetaddUsersRow(this.this$0)) goto L76;
            return 7;
        L76:
            if (r3 != ChatUsersActivity.m8565$$Nest$fgetmanageLinkedPeersRow(this.this$0)) goto L78;
            return 7;
        L78:
            if (r3 != ChatUsersActivity.m8579$$Nest$fgetpinMessagesRow(this.this$0)) goto L80;
            return 7;
        L80:
            if (r3 != ChatUsersActivity.m8543$$Nest$fgeteditTagRow(this.this$0)) goto L82;
            return 7;
        L82:
            if (r3 != ChatUsersActivity.m8603$$Nest$fgetsendMessagesRow(this.this$0)) goto L84;
            return 7;
        L84:
            if (r3 != ChatUsersActivity.m8606$$Nest$fgetsendStickersRow(this.this$0)) goto L86;
            return 7;
        L86:
            if (r3 != ChatUsersActivity.m8544$$Nest$fgetembedLinksRow(this.this$0)) goto L88;
            return 7;
        L88:
            if (r3 != ChatUsersActivity.m8566$$Nest$fgetmanageTopicsRow(this.this$0)) goto L90;
            return 7;
        L90:
            if (r3 != ChatUsersActivity.m8541$$Nest$fgetdontRestrictBoostersRow(this.this$0)) goto L93;
            return 7;
        L93:
            if (r3 != ChatUsersActivity.m8567$$Nest$fgetmembersHeaderRow(this.this$0)) goto L95;
            return 8;
        L95:
            if (r3 != ChatUsersActivity.m8533$$Nest$fgetcontactsHeaderRow(this.this$0)) goto L97;
            return 8;
        L97:
            if (r3 != ChatUsersActivity.m8526$$Nest$fgetbotHeaderRow(this.this$0)) goto L99;
            return 8;
        L99:
            if (r3 != ChatUsersActivity.m8561$$Nest$fgetloadingHeaderRow(this.this$0)) goto L102;
            return 8;
        L102:
            if (r3 != ChatUsersActivity.m8613$$Nest$fgetslowmodeSelectRow(this.this$0)) goto L106;
            return 9;
        L106:
            if (r3 != ChatUsersActivity.m8562$$Nest$fgetloadingProgressRow(this.this$0)) goto L110;
            return 10;
        L110:
            if (r3 != ChatUsersActivity.m8563$$Nest$fgetloadingUserCellRow(this.this$0)) goto L114;
            return 11;
        L114:
            if (r3 != ChatUsersActivity.m8523$$Nest$fgetantiSpamRow(this.this$0)) goto L116;
            return 12;
        L116:
            if (r3 != ChatUsersActivity.m8553$$Nest$fgethideMembersRow(this.this$0)) goto L118;
            return 12;
        L118:
            if (r3 != ChatUsersActivity.m8616$$Nest$fgettagsRow(this.this$0)) goto L121;
            return 12;
        L121:
            if (ChatUsersActivity.m8629$$Nest$misExpandableSendMediaRow(this.this$0, r3) == false) goto L125;
            return 13;
        L125:
            if (r3 != ChatUsersActivity.m8598$$Nest$fgetsendMediaRow(this.this$0)) goto L129;
            return 14;
        L129:
            if (r3 != ChatUsersActivity.m8542$$Nest$fgetdontRestrictBoostersSliderRow(this.this$0)) goto L133;
            return 15;
        L133:
            if (r3 != ChatUsersActivity.m8609$$Nest$fgetsignMessagesRow(this.this$0)) goto L135;
            return 16;
        L135:
            if (r3 != ChatUsersActivity.m8608$$Nest$fgetsignMessagesProfilesRow(this.this$0)) goto L137;
            return 16;
        L137:
            if (r3 != ChatUsersActivity.m8577$$Nest$fgetpayRow(this.this$0)) goto L140;
            return 16;
        L140:
            if (r3 != ChatUsersActivity.m8582$$Nest$fgetpriceRow(this.this$0)) goto L143;
            return 17;
        L143:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r8) {
            int r0 = r8.getItemViewType();
            if (r0 != 16) goto L6;
            return true;
        L6:
            if (r0 == 7) goto L47;
            if (r0 == 14) goto L47;
            if (r0 == 13) goto L47;
            if (r0 != 0) goto L22;
            Object r82 = ((ManageChatUserCell) r8.itemView).getCurrentObject();
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 1) goto L17;
        L21:
            return true;
        L17:
            if ((r82 instanceof TLRPC.User) == false) goto L21;
            if (((TLRPC.User) r82).self == false) goto L21;
            return false;
        L22:
            int r83 = r8.getAdapterPosition();
            if (r0 != 0) goto L25;
        L45:
            return true;
        L25:
            if (r0 == 2) goto L45;
            if (r0 == 6) goto L45;
            if (r0 != 12) goto L42;
            int r5 = ChatUsersActivity.m8523$$Nest$fgetantiSpamRow(this.this$0);
            ChatUsersActivity r6 = this.this$0;
            if (r83 == r5) goto L34;
            int r52 = ChatUsersActivity.m8553$$Nest$fgethideMembersRow(r6);
            ChatUsersActivity r7 = this.this$0;
            if (r83 != r52) goto L40;
            return ChatObject.canUserDoAdminAction(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(r7), 2);
        L40:
            if (r83 != ChatUsersActivity.m8616$$Nest$fgettagsRow(r7)) goto L42;
            return true;
        L34:
            return ChatObject.canUserDoAdminAction(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(r6), 13);
        L42:
            if (r0 != 13) goto L44;
            return true;
        L44:
            return false;
        L47:
            return ChatObject.canBlockUsers(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r18v0 */
        /* JADX WARN: Type inference failed for: r18v1 */
        /* JADX WARN: Type inference failed for: r18v2 */
        /* JADX WARN: Type inference failed for: r18v5 */
        /* JADX WARN: Type inference failed for: r1v2, types: [android.view.View, org.telegram.ui.Cells.ManageChatUserCell] */
        /* JADX WARN: Type inference failed for: r23v1 */
        /* JADX WARN: Type inference failed for: r23v2 */
        /* JADX WARN: Type inference failed for: r23v3 */
        /* JADX WARN: Type inference failed for: r23v4 */
        /* JADX WARN: Type inference failed for: r7v7 */
        /* JADX WARN: Type inference failed for: r7v8 */
        /* JADX WARN: Type inference failed for: r7v9 */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r23, int r24) {
            boolean r9 = false;
            int r10 = 1;
            boolean r102 = true;
            boolean r103 = true;
            boolean r104 = true;
            r104 = true;
            r104 = true;
            switch(r23.getItemViewType()) {
                case 0: goto L465;
                case 1: goto L375;
                case 2: goto L310;
                case 3: goto L568;
                case 4: goto L568;
                case 5: goto L275;
                case 6: goto L269;
                case 7: goto L165;
                case 8: goto L137;
                case 9: goto L568;
                case 10: goto L568;
                case 11: goto L127;
                case 12: goto L75;
                case 13: goto L21;
                case 14: goto L165;
                case 15: goto L568;
                case 16: goto L9;
                case 17: goto L5;
                default: goto L568;
            };
        L5:
            SlideIntChooseView r1 = (SlideIntChooseView) r23.itemView;
            if (r24 != ChatUsersActivity.m8582$$Nest$fgetpriceRow(this.this$0)) goto L556;
            r1.set((int) Utilities.clamp(ChatUsersActivity.m8614$$Nest$fgetstarsPrice(this.this$0), this.this$0.getMessagesController().starsPaidMessageAmountMax, 1), SlideIntChooseView.Options.make(1, SlideIntChooseView.cut(new int[]{1, 10, 50, 100, 200, 250, MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000, 2500, 5000, 7500, 9000, 10000}, (int) this.this$0.getMessagesController().starsPaidMessageAmountMax), 20, new ChatUsersActivity$ListAdapter$$ExternalSyntheticLambda0()), new ChatUsersActivity$ListAdapter$$ExternalSyntheticLambda1(this));
            return;
        L556:
            return;
        L9:
            TextCheckCell r12 = (TextCheckCell) r23.itemView;
            if (r24 != ChatUsersActivity.m8609$$Nest$fgetsignMessagesRow(this.this$0)) goto L14;
            r12.setTextAndCheck(LocaleController.getString(R.string.ChannelSignMessages), ChatUsersActivity.m8610$$Nest$fgetsignatures(this.this$0), ChatUsersActivity.m8610$$Nest$fgetsignatures(this.this$0));
            return;
        L14:
            if (r24 != ChatUsersActivity.m8608$$Nest$fgetsignMessagesProfilesRow(this.this$0)) goto L18;
            r12.setTextAndCheck(LocaleController.getString(R.string.ChannelSignMessagesWithProfile), ChatUsersActivity.m8583$$Nest$fgetprofiles(this.this$0), false);
            return;
        L18:
            if (r24 != ChatUsersActivity.m8577$$Nest$fgetpayRow(this.this$0)) goto L557;
            r12.setTextAndCheck(LocaleController.getString(R.string.GroupMessagesChargePrice), ChatUsersActivity.m8546$$Nest$fgetenablePrice(this.this$0), false);
            return;
        L557:
            return;
        L21:
            CheckBoxCell r13 = (CheckBoxCell) r23.itemView;
            if (r13.getTag() != null) goto L24;
        L26:
            boolean r7 = false;
        L27:
            r13.setTag(Integer.valueOf(r24));
            if (r24 != ChatUsersActivity.m8597$$Nest$fgetsendMediaPhotosRow(this.this$0)) goto L32;
            r13.setText(LocaleController.getString("SendMediaPermissionPhotos", R.string.SendMediaPermissionPhotos), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_photos, true, r7);
            return;
        L32:
            if (r24 != ChatUsersActivity.m8601$$Nest$fgetsendMediaVideosRow(this.this$0)) goto L36;
            r13.setText(LocaleController.getString("SendMediaPermissionVideos", R.string.SendMediaPermissionVideos), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_videos, true, r7);
            return;
        L36:
            if (r24 != ChatUsersActivity.m8599$$Nest$fgetsendMediaStickerGifsRow(this.this$0)) goto L40;
            r13.setText(LocaleController.getString("SendMediaPermissionStickersGifs", R.string.SendMediaPermissionStickersGifs), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_stickers, true, r7);
            return;
        L40:
            if (r24 != ChatUsersActivity.m8596$$Nest$fgetsendMediaMusicRow(this.this$0)) goto L44;
            r13.setText(LocaleController.getString("SendMediaPermissionMusic", R.string.SendMediaPermissionMusic), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_audios, true, r7);
            return;
        L44:
            if (r24 != ChatUsersActivity.m8595$$Nest$fgetsendMediaFilesRow(this.this$0)) goto L48;
            r13.setText(LocaleController.getString("SendMediaPermissionFiles", R.string.SendMediaPermissionFiles), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_docs, true, r7);
            return;
        L48:
            if (r24 != ChatUsersActivity.m8602$$Nest$fgetsendMediaVoiceMessagesRow(this.this$0)) goto L52;
            r13.setText(LocaleController.getString("SendMediaPermissionVoice", R.string.SendMediaPermissionVoice), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_voices, true, r7);
            return;
        L52:
            if (r24 != ChatUsersActivity.m8600$$Nest$fgetsendMediaVideoMessagesRow(this.this$0)) goto L56;
            r13.setText(LocaleController.getString("SendMediaPermissionRound", R.string.SendMediaPermissionRound), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_roundvideos, true, r7);
            return;
        L56:
            if (r24 != ChatUsersActivity.m8593$$Nest$fgetsendMediaEmbededLinksRow(this.this$0)) goto L66;
            String r3 = LocaleController.getString("SendMediaEmbededLinks", R.string.SendMediaEmbededLinks);
            if (ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).embed_links == false) goto L60;
        L62:
            boolean r5 = false;
        L63:
            r13.setText(r3, _UrlKt.FRAGMENT_ENCODE_SET, r5, true, r7);
            return;
        L60:
            if (ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_plain == true) goto L62;
            r5 = true;
            goto L63
        L66:
            if (r24 != ChatUsersActivity.m8605$$Nest$fgetsendReactionsRow(this.this$0)) goto L70;
            r13.setText(LocaleController.getString(R.string.UserRestrictionsSendReactions), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_reactions, false, r7);
            return;
        L70:
            if (r24 != ChatUsersActivity.m8604$$Nest$fgetsendPollsRow(this.this$0)) goto L73;
            r13.setText(LocaleController.getString("SendMediaPolls", R.string.SendMediaPolls), _UrlKt.FRAGMENT_ENCODE_SET, !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_polls, true, r7);
            return;
        L73:
            r13.setPad(1);
            return;
        L24:
            if (((Integer) r13.getTag()).intValue() != r24) goto L26;
            r7 = true;
            goto L27
        L75:
            TextCell r14 = (TextCell) r23.itemView;
            if (r24 != ChatUsersActivity.m8523$$Nest$fgetantiSpamRow(this.this$0)) goto L96;
            Switch r2 = r14.getCheckBox();
            if (ChatObject.canUserDoAdminAction(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0), 13) == true) goto L80;
        L86:
            int r32 = R.drawable.permission_locked;
        L87:
            r2.setIcon(r32);
            String r22 = LocaleController.getString("ChannelAntiSpam", R.string.ChannelAntiSpam);
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) != null) goto L90;
        L92:
            r102 = false;
        L93:
            r14.setTextAndCheckAndIcon(r22, r102, R.drawable.msg_policy, false);
            return;
        L90:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).antispam == false) goto L92;
        L80:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) != null) goto L82;
        L85:
            r32 = 0;
            goto L87
        L82:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).antispam == true) goto L85;
            if (ChatUsersActivity.m8626$$Nest$mgetParticipantsCount(this.this$0) < this.this$0.getMessagesController().telegramAntispamGroupSizeMin) goto L86;
        L96:
            if (r24 != ChatUsersActivity.m8553$$Nest$fgethideMembersRow(this.this$0)) goto L116;
            Switch r25 = r14.getCheckBox();
            if (ChatObject.canUserDoAdminAction(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0), 2) == true) goto L100;
        L106:
            int r33 = R.drawable.permission_locked;
        L107:
            r25.setIcon(r33);
            String r26 = LocaleController.getString(R.string.ChannelHideMembers);
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) != null) goto L110;
        L112:
            r103 = false;
        L113:
            r14.setTextAndCheck(r26, r103, false);
            return;
        L110:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).participants_hidden == false) goto L112;
        L100:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) != null) goto L102;
        L105:
            r33 = 0;
            goto L107
        L102:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).participants_hidden == true) goto L105;
            if (ChatUsersActivity.m8626$$Nest$mgetParticipantsCount(this.this$0) < this.this$0.getMessagesController().hiddenMembersGroupSizeMin) goto L106;
        L116:
            if (r24 != ChatUsersActivity.m8616$$Nest$fgettagsRow(this.this$0)) goto L558;
            r14.getCheckBox().setIcon(0);
            String r27 = LocaleController.getString(R.string.ChannelMemberTags);
            if (ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0) != null) goto L120;
        L125:
            r14.setTextAndCheck(r27, r104, false);
            return;
        L120:
            if (ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0).default_banned_rights == null) goto L125;
            if (ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0).default_banned_rights.edit_rank == false) goto L125;
            r104 = false;
            goto L125
        L558:
            return;
        L127:
            FlickerLoadingView r15 = (FlickerLoadingView) r23.itemView;
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) == 0) goto L130;
            r15.setItemsCount(1);
            return;
        L130:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) == null) goto L133;
            r10 = ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).kicked_count;
        L133:
            r15.setItemsCount(r10);
            return;
        L137:
            GraySectionCell r16 = (GraySectionCell) r23.itemView;
            int r34 = ChatUsersActivity.m8567$$Nest$fgetmembersHeaderRow(this.this$0);
            ChatUsersActivity r52 = this.this$0;
            if (r24 != r34) goto L148;
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(r52)) == true) goto L142;
        L145:
            r16.setText(LocaleController.getString("ChannelOtherMembers", R.string.ChannelOtherMembers));
            return;
        L142:
            if (ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0).megagroup == true) goto L145;
            r16.setText(LocaleController.getString("ChannelOtherSubscribers", R.string.ChannelOtherSubscribers));
            return;
        L148:
            if (r24 != ChatUsersActivity.m8526$$Nest$fgetbotHeaderRow(r52)) goto L151;
            r16.setText(LocaleController.getString("ChannelBots", R.string.ChannelBots));
            return;
        L151:
            int r35 = ChatUsersActivity.m8533$$Nest$fgetcontactsHeaderRow(this.this$0);
            ChatUsersActivity r53 = this.this$0;
            if (r24 != r35) goto L162;
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(r53)) == true) goto L156;
        L159:
            r16.setText(LocaleController.getString("GroupContacts", R.string.GroupContacts));
            return;
        L156:
            if (ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0).megagroup == true) goto L159;
            r16.setText(LocaleController.getString("ChannelContacts", R.string.ChannelContacts));
            return;
        L162:
            if (r24 != ChatUsersActivity.m8561$$Nest$fgetloadingHeaderRow(r53)) goto L559;
            r16.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L559:
            return;
        L165:
            TextCheckCell2 r17 = (TextCheckCell2) r23.itemView;
            r17.getCheckBox().setDrawIconType(1);
            Switch r36 = r17.getCheckBox();
            int r4 = Theme.key_fill_RedNormal;
            int r6 = Theme.key_switch2TrackChecked;
            int r8 = Theme.key_windowBackgroundWhite;
            r36.setColors(r4, r6, r8, r8);
            if (r17.getTag() != null) goto L168;
        L170:
            boolean r37 = false;
        L171:
            r17.setTag(Integer.valueOf(r24));
            int r42 = ChatUsersActivity.m8529$$Nest$fgetchangeInfoRow(this.this$0);
            ChatUsersActivity r62 = this.this$0;
            if (r24 != r42) goto L189;
            if (ChatUsersActivity.m8556$$Nest$fgetisCommunity(r62) == false) goto L176;
            int r43 = R.string.CommunityAdminRightEditCommunityName;
        L177:
            String r44 = LocaleController.getString(r43);
            if (ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).change_info == false) goto L180;
        L182:
            boolean r63 = false;
        L184:
            if (ChatUsersActivity.m8566$$Nest$fgetmanageTopicsRow(this.this$0) == (-1)) goto L186;
            boolean r72 = true;
        L187:
            r17.setTextAndCheck(r44, r63, r72, r37);
        L232:
            if (r24 == ChatUsersActivity.m8579$$Nest$fgetpinMessagesRow(this.this$0)) goto L236;
            if (r24 == ChatUsersActivity.m8529$$Nest$fgetchangeInfoRow(this.this$0)) goto L236;
        L240:
            if (ChatObject.canBlockUsers(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == true) goto L242;
            r17.setIcon(0);
            return;
        L242:
            if (r24 != ChatUsersActivity.m8521$$Nest$fgetaddUsersRow(this.this$0)) goto L246;
            if (ChatObject.canUserDoAdminAction(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0), 3) == true) goto L246;
        L263:
            r17.setIcon(R.drawable.permission_locked);
            return;
        L246:
            if (r24 != ChatUsersActivity.m8579$$Nest$fgetpinMessagesRow(this.this$0)) goto L250;
            if (ChatObject.canUserDoAdminAction(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0), 0) == false) goto L263;
        L250:
            if (r24 != ChatUsersActivity.m8529$$Nest$fgetchangeInfoRow(this.this$0)) goto L254;
            if (ChatObject.canUserDoAdminAction(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0), 1) == false) goto L263;
        L254:
            if (r24 != ChatUsersActivity.m8566$$Nest$fgetmanageTopicsRow(this.this$0)) goto L258;
            if (ChatObject.canManageTopics(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == false) goto L263;
        L258:
            if (ChatObject.isPublic(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == true) goto L260;
        L265:
            r17.setIcon(0);
            return;
        L260:
            if (r24 == ChatUsersActivity.m8579$$Nest$fgetpinMessagesRow(this.this$0)) goto L263;
            if (r24 != ChatUsersActivity.m8529$$Nest$fgetchangeInfoRow(this.this$0)) goto L265;
        L236:
            if (ChatObject.isDiscussionGroup(ChatUsersActivity.access$300(this.this$0), ChatUsersActivity.m8530$$Nest$fgetchatId(this.this$0)) == false) goto L240;
            r17.setIcon(R.drawable.permission_locked);
            return;
        L186:
            r72 = false;
            goto L187
        L180:
            if (ChatObject.isPublic(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == true) goto L182;
            r63 = true;
            goto L184
        L176:
            r43 = R.string.UserRestrictionsChangeInfo;
            goto L177
        L189:
            if (r24 != ChatUsersActivity.m8565$$Nest$fgetmanageLinkedPeersRow(r62)) goto L192;
            r17.setTextAndCheck(LocaleController.getString(R.string.CommunityAdminRightEditGroupList), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).manage_linked_peers, false, r37);
            goto L232
        L192:
            if (r24 != ChatUsersActivity.m8521$$Nest$fgetaddUsersRow(this.this$0)) goto L195;
            r17.setTextAndCheck(LocaleController.getString("UserRestrictionsInviteUsers", R.string.UserRestrictionsInviteUsers), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).invite_users, true, r37);
            goto L232
        L195:
            if (r24 != ChatUsersActivity.m8579$$Nest$fgetpinMessagesRow(this.this$0)) goto L204;
            String r45 = LocaleController.getString(R.string.UserRestrictionsPinMessages);
            if (ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).pin_messages == false) goto L199;
        L201:
            boolean r64 = false;
        L202:
            r17.setTextAndCheck(r45, r64, true, r37);
            goto L232
        L199:
            if (ChatObject.isPublic(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == true) goto L201;
            r64 = true;
            goto L202
        L204:
            if (r24 != ChatUsersActivity.m8543$$Nest$fgeteditTagRow(this.this$0)) goto L207;
            r17.setTextAndCheck(LocaleController.getString(R.string.UserRestrictionsEditTags), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).edit_rank, true, r37);
            goto L232
        L207:
            if (r24 != ChatUsersActivity.m8603$$Nest$fgetsendMessagesRow(this.this$0)) goto L210;
            r17.setTextAndCheck(LocaleController.getString("UserRestrictionsSendText", R.string.UserRestrictionsSendText), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_plain, true, r37);
            goto L232
        L210:
            if (r24 != ChatUsersActivity.m8541$$Nest$fgetdontRestrictBoostersRow(this.this$0)) goto L212;
            r17.setTextAndCheck(LocaleController.getString(R.string.GroupNotRestrictBoosters), ChatUsersActivity.m8557$$Nest$fgetisEnabledNotRestrictBoosters(this.this$0), false, r37);
            r17.getCheckBox().setDrawIconType(0);
            r17.getCheckBox().setColors(Theme.key_switchTrack, Theme.key_switchTrackChecked, r8, r8);
            goto L232
        L212:
            int r46 = ChatUsersActivity.m8598$$Nest$fgetsendMediaRow(this.this$0);
            ChatUsersActivity r65 = this.this$0;
            if (r24 != r46) goto L220;
            int r47 = ChatUsersActivity.m8628$$Nest$mgetSendMediaSelectedCount(r65);
            String r66 = LocaleController.getString("UserRestrictionsSendMedia", R.string.UserRestrictionsSendMedia);
            if (r47 <= 0) goto L217;
            boolean r73 = true;
        L218:
            r17.setTextAndCheck(r66, r73, true, r37);
            r17.setCollapseArrow(String.format(Locale.US, "%d/10", new Object[]{Integer.valueOf(r47)}), !ChatUsersActivity.m8594$$Nest$fgetsendMediaExpanded(this.this$0), new AnonymousClass1(this, r17));
            goto L232
        L217:
            r73 = false;
            goto L218
        L220:
            if (r24 != ChatUsersActivity.m8606$$Nest$fgetsendStickersRow(r65)) goto L223;
            r17.setTextAndCheck(LocaleController.getString("UserRestrictionsSendStickers", R.string.UserRestrictionsSendStickers), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_stickers, true, r37);
            goto L232
        L223:
            if (r24 != ChatUsersActivity.m8544$$Nest$fgetembedLinksRow(this.this$0)) goto L226;
            r17.setTextAndCheck(LocaleController.getString("UserRestrictionsEmbedLinks", R.string.UserRestrictionsEmbedLinks), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).embed_links, true, r37);
            goto L232
        L226:
            if (r24 != ChatUsersActivity.m8604$$Nest$fgetsendPollsRow(this.this$0)) goto L229;
            r17.setTextAndCheck(LocaleController.getString("UserRestrictionsSendPollsShort", R.string.UserRestrictionsSendPollsShort), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).send_polls, true);
            goto L232
        L229:
            if (r24 != ChatUsersActivity.m8566$$Nest$fgetmanageTopicsRow(this.this$0)) goto L232;
            r17.setTextAndCheck(LocaleController.getString("CreateTopicsPermission", R.string.CreateTopicsPermission), !ChatUsersActivity.m8537$$Nest$fgetdefaultBannedRights(this.this$0).manage_topics, false, r37);
            goto L232
        L168:
            if (((Integer) r17.getTag()).intValue() != r24) goto L170;
            r37 = true;
            goto L171
        L269:
            TextSettingsCell r18 = (TextSettingsCell) r23.itemView;
            String r28 = LocaleController.getString("ChannelBlacklist", R.string.ChannelBlacklist);
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) == null) goto L272;
            int r0 = ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).kicked_count;
        L273:
            r18.setTextAndValue(r28, String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r0)}), false);
            return;
        L272:
            r0 = 0;
            goto L273
        L275:
            HeaderCell r19 = (HeaderCell) r23.itemView;
            int r38 = ChatUsersActivity.m8587$$Nest$fgetrestricted1SectionRow(this.this$0);
            ChatUsersActivity r48 = this.this$0;
            if (r24 == r38) goto L278;
            int r39 = ChatUsersActivity.m8578$$Nest$fgetpermissionsSectionRow(r48);
            ChatUsersActivity r49 = this.this$0;
            if (r24 != r39) goto L299;
            if (ChatUsersActivity.m8556$$Nest$fgetisCommunity(r49) == false) goto L295;
            int r02 = R.string.CommunityPermissionsHeader;
        L296:
            r19.setText(LocaleController.getString(r02));
            return;
        L295:
            r02 = R.string.ChannelPermissionsHeader;
            goto L296
        L299:
            if (r24 != ChatUsersActivity.m8612$$Nest$fgetslowmodeRow(r49)) goto L303;
            r19.setText(LocaleController.getString(R.string.Slowmode));
            return;
        L303:
            if (r24 != ChatUsersActivity.m8550$$Nest$fgetgigaHeaderRow(this.this$0)) goto L307;
            r19.setText(LocaleController.getString(R.string.BroadcastGroup));
            return;
        L307:
            if (r24 != ChatUsersActivity.m8580$$Nest$fgetpriceHeaderRow(this.this$0)) goto L560;
            r19.setText(LocaleController.getString(R.string.GroupMessagesPriceHeader));
            return;
        L560:
            return;
        L278:
            if (ChatUsersActivity.m8617$$Nest$fgettype(r48) != 0) goto L288;
            TLRPC.ChatFull r29 = ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0);
            ChatUsersActivity r03 = this.this$0;
            if (r29 == null) goto L282;
            int r04 = ChatUsersActivity.m8554$$Nest$fgetinfo(r03).kicked_count;
        L283:
            if (r04 == 0) goto L286;
            r19.setText(LocaleController.formatPluralString("RemovedUser", r04, new Object[0]));
            return;
        L286:
            r19.setText(LocaleController.getString(R.string.ChannelBlockedUsers));
            return;
        L282:
            r04 = ChatUsersActivity.m8569$$Nest$fgetparticipants(r03).size();
            goto L283
        L288:
            r19.setText(LocaleController.getString(R.string.ChannelRestrictedUsers));
            return;
        L310:
            ManageChatTextCell r110 = (ManageChatTextCell) r23.itemView;
            r110.setColors(Theme.key_windowBackgroundWhiteGrayIcon, Theme.key_windowBackgroundWhiteBlackText);
            int r310 = ChatUsersActivity.m8519$$Nest$fgetaddNewRow(this.this$0);
            ChatUsersActivity r410 = this.this$0;
            if (r24 != r310) goto L352;
            if (ChatUsersActivity.m8617$$Nest$fgettype(r410) != 3) goto L320;
            r110.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            String r210 = LocaleController.getString("ChannelAddException", R.string.ChannelAddException);
            int r311 = R.drawable.msg_contact_add;
            if (ChatUsersActivity.m8575$$Nest$fgetparticipantsStartRow(this.this$0) == (-1)) goto L317;
            r9 = true;
        L317:
            r110.setText(r210, null, r311, r9);
            return;
        L320:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 0) goto L324;
            r110.setText(LocaleController.getString("ChannelBlockUser", R.string.ChannelBlockUser), null, R.drawable.msg_user_remove, false);
            return;
        L324:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 1) goto L333;
            r110.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            if (ChatUsersActivity.m8564$$Nest$fgetloadingUsers(this.this$0) == true) goto L328;
        L329:
            r9 = true;
        L330:
            r110.setText(LocaleController.getString("ChannelAddAdmin", R.string.ChannelAddAdmin), null, R.drawable.msg_admin_add, r9);
            return;
        L328:
            if (ChatUsersActivity.m8547$$Nest$fgetfirstLoaded(this.this$0) == false) goto L330;
        L333:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 2) goto L561;
            r110.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            if (ChatUsersActivity.m8518$$Nest$fgetaddNew2Row(this.this$0) == (-1)) goto L337;
        L344:
            r9 = true;
        L346:
            if (ChatUsersActivity.m8555$$Nest$fgetisChannel(this.this$0) == false) goto L349;
            r110.setText(LocaleController.getString(R.string.AddSubscriber), null, R.drawable.msg_contact_add, r9);
            return;
        L349:
            r110.setText(LocaleController.getString(R.string.AddMember), null, R.drawable.msg_contact_add, r9);
            return;
        L337:
            if (ChatUsersActivity.m8564$$Nest$fgetloadingUsers(this.this$0) == false) goto L341;
            if (ChatUsersActivity.m8547$$Nest$fgetfirstLoaded(this.this$0) == false) goto L346;
        L341:
            if (ChatUsersActivity.m8567$$Nest$fgetmembersHeaderRow(this.this$0) != (-1)) goto L346;
            if (ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).isEmpty() == true) goto L346;
        L561:
            return;
        L352:
            if (r24 != ChatUsersActivity.m8585$$Nest$fgetrecentActionsRow(r410)) goto L359;
            String r211 = LocaleController.getString(R.string.EventLog);
            int r312 = R.drawable.msg_log;
            if (ChatUsersActivity.m8523$$Nest$fgetantiSpamRow(this.this$0) <= ChatUsersActivity.m8585$$Nest$fgetrecentActionsRow(this.this$0)) goto L356;
            r9 = true;
        L356:
            r110.setText(r211, null, r312, r9);
            return;
        L359:
            if (r24 != ChatUsersActivity.m8518$$Nest$fgetaddNew2Row(this.this$0)) goto L372;
            r110.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            if (ChatUsersActivity.m8564$$Nest$fgetloadingUsers(this.this$0) == false) goto L365;
            if (ChatUsersActivity.m8547$$Nest$fgetfirstLoaded(this.this$0) == true) goto L365;
        L369:
            r110.setText(LocaleController.getString("ChannelInviteViaLink", R.string.ChannelInviteViaLink), null, R.drawable.msg_link2, r9);
            return;
        L365:
            if (ChatUsersActivity.m8567$$Nest$fgetmembersHeaderRow(this.this$0) != (-1)) goto L369;
            if (ChatUsersActivity.m8569$$Nest$fgetparticipants(this.this$0).isEmpty() == true) goto L369;
            r9 = true;
            goto L369
        L372:
            if (r24 != ChatUsersActivity.m8549$$Nest$fgetgigaConvertRow(this.this$0)) goto L562;
            r110.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r110.setText(LocaleController.getString("BroadcastGroupConvert", R.string.BroadcastGroupConvert), null, R.drawable.msg_channel, false);
            return;
        L562:
            return;
        L375:
            TextInfoPrivacyCell r111 = (TextInfoPrivacyCell) r23.itemView;
            if (r24 != ChatUsersActivity.m8522$$Nest$fgetantiSpamInfoRow(this.this$0)) goto L379;
            r111.setText(LocaleController.getString("ChannelAntiSpamInfo", R.string.ChannelAntiSpamInfo));
            return;
        L379:
            int r313 = ChatUsersActivity.m8573$$Nest$fgetparticipantsInfoRow(this.this$0);
            ChatUsersActivity r82 = this.this$0;
            if (r24 == r313) goto L382;
            int r314 = ChatUsersActivity.m8611$$Nest$fgetslowmodeInfoRow(r82);
            ChatUsersActivity r411 = this.this$0;
            if (r24 != r314) goto L430;
            int r212 = ChatUsersActivity.m8627$$Nest$mgetSecondsForIndex(r411, ChatUsersActivity.m8592$$Nest$fgetselectedSlowmode(r411));
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) == null) goto L427;
            if (r212 == 0) goto L427;
            r111.setText(LocaleController.formatString(R.string.SlowmodeInfoSelected, new Object[]{ChatUsersActivity.m8624$$Nest$mformatSeconds(this.this$0, r212)}));
            return;
        L427:
            r111.setText(LocaleController.getString(R.string.SlowmodeInfoOff));
            return;
        L430:
            if (r24 != ChatUsersActivity.m8576$$Nest$fgetpayInfoRow(r411)) goto L433;
            r111.setText(LocaleController.getString(R.string.GroupMessagesChargePriceInfo));
            return;
        L433:
            int r315 = ChatUsersActivity.m8581$$Nest$fgetpriceInfoRow(this.this$0);
            ChatUsersActivity r412 = this.this$0;
            if (r24 != r315) goto L438;
            float r213 = r412.getMessagesController().starsPaidMessageCommissionPermille / 1000.0f;
            r111.setText(LocaleController.formatString(R.string.GroupMessagesPriceInfo, new Object[]{AffiliateProgramFragment.percents(this.this$0.getMessagesController().starsPaidMessageCommissionPermille), String.valueOf(((int) (((ChatUsersActivity.m8614$$Nest$fgetstarsPrice(this.this$0) * r213) / 1000.0d) * this.this$0.getMessagesController().starsUsdWithdrawRate1000)) / 100.0d)}));
            return;
        L438:
            if (r24 != ChatUsersActivity.m8552$$Nest$fgethideMembersInfoRow(r412)) goto L442;
            r111.setText(LocaleController.getString(R.string.ChannelHideMembersInfo));
            return;
        L442:
            if (r24 != ChatUsersActivity.m8615$$Nest$fgettagsInfoRow(this.this$0)) goto L446;
            r111.setText(LocaleController.getString(R.string.ChannelMemberTagsInfo));
            return;
        L446:
            if (r24 != ChatUsersActivity.m8551$$Nest$fgetgigaInfoRow(this.this$0)) goto L449;
            r111.setText(LocaleController.getString(R.string.BroadcastGroupConvertInfo));
            return;
        L449:
            int r316 = ChatUsersActivity.m8540$$Nest$fgetdontRestrictBoostersInfoRow(this.this$0);
            ChatUsersActivity r413 = this.this$0;
            if (r24 != r316) goto L458;
            if (ChatUsersActivity.m8557$$Nest$fgetisEnabledNotRestrictBoosters(r413) == false) goto L455;
            r111.setText(LocaleController.getString(R.string.GroupNotRestrictBoostersInfo2));
            return;
        L455:
            r111.setText(LocaleController.getString(R.string.GroupNotRestrictBoostersInfo));
            return;
        L458:
            if (r24 == ChatUsersActivity.m8607$$Nest$fgetsignMessagesInfoRow(r413)) goto L460;
            return;
        L460:
            if (ChatUsersActivity.m8610$$Nest$fgetsignatures(this.this$0) == false) goto L462;
            int r05 = R.string.ChannelSignProfilesInfo;
        L463:
            r111.setText(LocaleController.getString(r05));
            return;
        L462:
            r05 = R.string.ChannelSignInfo;
            goto L463
        L382:
            if (ChatUsersActivity.m8617$$Nest$fgettype(r82) == 0) goto L410;
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) == 3) goto L410;
            int r214 = ChatUsersActivity.m8617$$Nest$fgettype(this.this$0);
            ChatUsersActivity r317 = this.this$0;
            if (r214 != 1) goto L399;
            if (ChatUsersActivity.m8519$$Nest$fgetaddNewRow(r317) != (-1)) goto L391;
            r111.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L391:
            if (ChatUsersActivity.m8555$$Nest$fgetisChannel(this.this$0) == false) goto L394;
            r111.setText(LocaleController.getString("ChannelAdminsInfo", R.string.ChannelAdminsInfo));
            return;
        L394:
            r111.setText(LocaleController.getString("MegaAdminsInfo", R.string.MegaAdminsInfo));
            return;
        L399:
            if (ChatUsersActivity.m8617$$Nest$fgettype(r317) == 2) goto L401;
            return;
        L401:
            if (ChatUsersActivity.m8555$$Nest$fgetisChannel(this.this$0) == true) goto L403;
        L407:
            r111.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L403:
            if (ChatUsersActivity.m8591$$Nest$fgetselectType(this.this$0) != 0) goto L407;
            r111.setText(LocaleController.getString("ChannelMembersInfo", R.string.ChannelMembersInfo));
            return;
        L410:
            if (ChatUsersActivity.m8555$$Nest$fgetisChannel(this.this$0) == false) goto L414;
            r111.setText(LocaleController.getString(R.string.NoBlockedChannel2));
            return;
        L414:
            if (ChatUsersActivity.m8556$$Nest$fgetisCommunity(this.this$0) == false) goto L417;
            r111.setText(LocaleController.getString(R.string.NoBlockedCommunity2));
            return;
        L417:
            r111.setText(LocaleController.getString(R.string.NoBlockedGroup2));
            return;
        L465:
            ?? r112 = (ManageChatUserCell) r23.itemView;
            r112.setTag(Integer.valueOf(r24));
            TLObject r318 = getItem(r24);
            if (r24 < ChatUsersActivity.m8575$$Nest$fgetparticipantsStartRow(this.this$0)) goto L475;
            if (r24 >= ChatUsersActivity.m8572$$Nest$fgetparticipantsEndRow(this.this$0)) goto L475;
            int r414 = ChatUsersActivity.m8572$$Nest$fgetparticipantsEndRow(this.this$0);
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == true) goto L472;
        L484:
            ?? r74 = false;
        L486:
            if ((r318 instanceof TLRPC.User) == false) goto L489;
            long r142 = ((TLRPC.User) r318).id;
            ?? r232 = r74;
            boolean r319 = false;
            int r11 = 0;
            int r182 = true;
            long r67 = 0;
            long r122 = 0;
            long r162 = 0;
            TLRPC.TL_chatBannedRights r92 = null;
            boolean r83 = false;
            boolean r105 = false;
        L494:
            ChatUsersActivity r54 = this.this$0;
            if (r142 <= r162) goto L497;
            boolean r163 = r319;
            Object r320 = r54.getMessagesController().getUser(Long.valueOf(r142));
            int r192 = r414;
        L498:
            if (r320 == null) goto L566;
            int r415 = ChatUsersActivity.m8617$$Nest$fgettype(this.this$0);
            ChatUsersActivity r55 = this.this$0;
            long r20 = r67;
            if (r415 != 3) goto L509;
            String r06 = ChatUsersActivity.m8625$$Nest$mformatUserPermissions(r55, r92);
            if (r24 == (r192 - 1)) goto L505;
            boolean r93 = r182;
        L506:
            r112.setData(r320, null, r06, r93);
            return;
        L505:
            r93 = false;
            goto L506
        L509:
            if (ChatUsersActivity.m8617$$Nest$fgettype(r55) != 0) goto L523;
            if (r105 == false) goto L514;
            TLRPC.User r07 = this.this$0.getMessagesController().getUser(Long.valueOf(r122));
            if (r07 == null) goto L514;
            String r08 = LocaleController.formatString(R.string.UserRemovedBy, new Object[]{UserObject.getUserName(r07)});
        L516:
            if (r24 == (r192 - 1)) goto L519;
            boolean r94 = r182;
        L520:
            r112.setData(r320, null, r08, r94);
            return;
        L519:
            r94 = false;
        L514:
            r08 = null;
            goto L516
        L523:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != r182) goto L543;
            if (r83 == false) goto L527;
            String r09 = LocaleController.getString(R.string.ChannelCreator);
        L536:
            if (r24 == (r192 - 1)) goto L539;
            boolean r95 = true;
        L540:
            r112.setData(r320, null, r09, r95);
            return;
        L539:
            r95 = false;
            goto L540
        L527:
            if (r163 == false) goto L534;
            TLRPC.User r010 = this.this$0.getMessagesController().getUser(Long.valueOf(r20));
            if (r010 == null) goto L534;
            if (r010.id != r142) goto L533;
            r09 = LocaleController.getString(R.string.ChannelAdministrator);
            goto L536
        L533:
            r09 = LocaleController.formatString(R.string.EditAdminPromotedBy, new Object[]{UserObject.getUserName(r010)});
        L534:
            r09 = null;
            goto L536
        L543:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 2) goto L567;
            if (r232 == false) goto L548;
            if (r11 == 0) goto L548;
            String r011 = LocaleController.formatJoined(r11);
        L550:
            if (r24 == (r192 - 1)) goto L553;
            boolean r96 = true;
        L554:
            r112.setData(r320, null, r011, r96);
            return;
        L553:
            r96 = false;
        L548:
            r011 = null;
            goto L550
        L567:
            return;
        L566:
            return;
        L497:
            r163 = r319;
            r192 = r414;
            r320 = r54.getMessagesController().getChat(Long.valueOf(-r142));
            goto L498
        L489:
            if ((r318 instanceof TLRPC.ChannelParticipant) == false) goto L491;
            TLRPC.ChannelParticipant r321 = (TLRPC.ChannelParticipant) r318;
            r142 = MessageObject.getPeerId(r321.peer);
            r162 = 0;
            r122 = r321.kicked_by;
            r232 = r74;
            r67 = r321.promoted_by;
            r92 = r321.banned_rights;
            r11 = r321.date;
            r182 = true;
            r105 = r321 instanceof TLRPC.TL_channelParticipantBanned;
            r83 = r321 instanceof TLRPC.TL_channelParticipantCreator;
            r319 = r321 instanceof TLRPC.TL_channelParticipantAdmin;
            goto L494
        L491:
            r232 = r74;
            r182 = true;
            r162 = 0;
            if ((r318 instanceof TLRPC.ChatParticipant) == false) goto L565;
            TLRPC.ChatParticipant r322 = (TLRPC.ChatParticipant) r318;
            r142 = r322.user_id;
            r11 = r322.date;
            r83 = r322 instanceof TLRPC.TL_chatParticipantCreator;
            r319 = r322 instanceof TLRPC.TL_chatParticipantAdmin;
            r67 = 0;
            r122 = 0;
            r92 = null;
            r105 = false;
            goto L494
        L565:
            return;
        L472:
            if (ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0).megagroup == true) goto L484;
        L473:
            r74 = true;
        L475:
            if (r24 >= ChatUsersActivity.m8535$$Nest$fgetcontactsStartRow(this.this$0)) goto L477;
        L483:
            r414 = ChatUsersActivity.m8525$$Nest$fgetbotEndRow(this.this$0);
            goto L484
        L477:
            if (r24 >= ChatUsersActivity.m8532$$Nest$fgetcontactsEndRow(this.this$0)) goto L483;
            r414 = ChatUsersActivity.m8532$$Nest$fgetcontactsEndRow(this.this$0);
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == false) goto L484;
            if (ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0).megagroup == true) goto L484;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r13, int r14) {
            boolean r0 = true;
            int r2 = 6;
            int r3 = 0;
            switch(r14) {
                case 0: goto L29;
                case 1: goto L28;
                case 2: goto L27;
                case 3: goto L26;
                case 4: goto L19;
                case 5: goto L18;
                case 6: goto L17;
                case 7: goto L16;
                case 8: goto L15;
                case 9: goto L4;
                case 10: goto L14;
                case 11: goto L13;
                case 12: goto L12;
                case 13: goto L11;
                case 14: goto L16;
                case 15: goto L7;
                case 16: goto L6;
                case 17: goto L5;
                default: goto L4;
            };
        L4:
            SlideChooseView r142 = new SlideChooseView(this.mContext);
            r142.setAllowSlide(ChatObject.hasAdminRights(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)));
            r142.setOptions(ChatUsersActivity.m8592$$Nest$fgetselectedSlowmode(this.this$0), new String[]{LocaleController.getString("SlowmodeOff", R.string.SlowmodeOff), LocaleController.formatString(R.string.SlowmodeSeconds, new Object[]{5}), LocaleController.formatString(R.string.SlowmodeSeconds, new Object[]{10}), LocaleController.formatString(R.string.SlowmodeSeconds, new Object[]{30}), LocaleController.formatString(R.string.SlowmodeMinutes, new Object[]{1}), LocaleController.formatString(R.string.SlowmodeMinutes, new Object[]{5}), LocaleController.formatString(R.string.SlowmodeMinutes, new Object[]{15}), LocaleController.formatString(R.string.SlowmodeHours, new Object[]{1})});
            r142.setCallback(new ChatUsersActivity$ListAdapter$$ExternalSyntheticLambda3(this));
            View r143 = r142;
        L48:
            return new RecyclerListView.Holder(r143);
        L5:
            r143 = new SlideIntChooseView(this.mContext, this.this$0.getResourceProvider());
            goto L48
        L6:
            r143 = new TextCheckCell(this.mContext, this.this$0.getResourceProvider());
            goto L48
        L7:
            SlideChooseView r144 = new SlideChooseView(this.mContext);
            Drawable[] r132 = {ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.mini_boost_profile_badge), ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.mini_boost_profile_badge2), ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.mini_boost_profile_badge2), ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.mini_boost_profile_badge2), ContextCompat.getDrawable(this.this$0.getContext(), R.drawable.mini_boost_profile_badge2)};
            if (ChatUsersActivity.m8568$$Nest$fgetnotRestrictBoosters(this.this$0) <= 0) goto L10;
            r3 = ChatUsersActivity.m8568$$Nest$fgetnotRestrictBoosters(this.this$0) - 1;
        L10:
            r144.setOptions(r3, r132, new String[]{"1", "2", "3", "4", "5"});
            r144.setCallback(new ChatUsersActivity$ListAdapter$$ExternalSyntheticLambda4(this));
            r143 = r144;
            goto L48
        L11:
            CheckBoxCell r145 = new CheckBoxCell(this.mContext, 4, 21, this.this$0.getResourceProvider());
            r145.getCheckBoxRound().setDrawBackgroundAsArc(14);
            r145.getCheckBoxRound().setColor(Theme.key_switch2TrackChecked, Theme.key_radioBackground, Theme.key_checkboxCheck);
            r145.setEnabled(true);
            r143 = r145;
            goto L48
        L12:
            TextCell r32 = new TextCell(this.mContext, 23, false, true, this.this$0.getResourceProvider());
            r32.heightDp = 50;
            r143 = r32;
            goto L48
        L13:
            FlickerLoadingView r146 = new FlickerLoadingView(this.mContext);
            r146.setIsSingleCell(true);
            r146.setViewType(6);
            r146.showDate(false);
            r146.setUseHeaderOffset(false);
            r146.setPaddingLeft(AndroidUtilities.dp(5.0f));
            RecyclerView.LayoutParams r12 = new RecyclerView.LayoutParams(-1, -1);
            int r133 = AndroidUtilities.dp(12.0f);
            ((ViewGroup.MarginLayoutParams) r12).rightMargin = r133;
            ((ViewGroup.MarginLayoutParams) r12).leftMargin = r133;
            ((ViewGroup.MarginLayoutParams) r12).topMargin = AndroidUtilities.dp(30.0f);
            r146.setLayoutParams(r12);
            r143 = r146;
            goto L48
        L14:
            r143 = new LoadingCell(this.mContext, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(120.0f));
            goto L48
        L15:
            View r147 = new GraySectionCell(this.mContext, 26, ChatUsersActivity.access$200(this.this$0));
            r147.setBackground(null);
            r143 = r147;
            goto L48
        L16:
            r143 = new TextCheckCell2(this.mContext);
            goto L48
        L17:
            r143 = new TextSettingsCell(this.mContext);
            goto L48
        L18:
            HeaderCell r02 = new HeaderCell(this.mContext, Theme.key_windowBackgroundWhiteBlueHeader, 21, 11, false);
            r02.setHeight(43);
            r143 = r02;
            goto L48
        L19:
            TextInfoPrivacyCell r148 = new TextInfoPrivacyCell(this.mContext);
            if (ChatUsersActivity.m8555$$Nest$fgetisChannel(this.this$0) == false) goto L23;
            r148.setText(LocaleController.getString(R.string.NoBlockedChannel2));
            r143 = r148;
            goto L48
        L23:
            if (ChatUsersActivity.m8556$$Nest$fgetisCommunity(this.this$0) == false) goto L25;
            r148.setText(LocaleController.getString(R.string.NoBlockedCommunity2));
            r143 = r148;
            goto L48
        L25:
            r148.setText(LocaleController.getString(R.string.NoBlockedGroup2));
            r143 = r148;
            goto L48
        L26:
            r143 = new ShadowSectionCell(this.mContext);
            goto L48
        L27:
            r143 = new ManageChatTextCell(this.mContext);
            goto L48
        L28:
            r143 = new TextInfoPrivacyCell(this.mContext);
            goto L48
        L29:
            Context r134 = this.mContext;
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 0) goto L32;
        L35:
            int r1 = 7;
        L37:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) == 0) goto L43;
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) == 3) goto L43;
            r2 = 2;
        L43:
            if (ChatUsersActivity.m8591$$Nest$fgetselectType(this.this$0) == 0) goto L46;
            r0 = false;
        L46:
            ManageChatUserCell r149 = new ManageChatUserCell(r134, r1, r2, r0);
            r149.setDelegate(new ChatUsersActivity$ListAdapter$$ExternalSyntheticLambda2(this));
            r143 = r149;
            goto L48
        L32:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) == 3) goto L35;
            r1 = 6;
            goto L37
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private int contactsStartRow;
        private int globalStartRow;
        private int groupStartRow;
        private Context mContext;
        private SearchAdapterHelper searchAdapterHelper;
        private boolean searchInProgress;
        private ArrayList<Object> searchResult;
        private LongSparseArray<TLObject> searchResultMap;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        final /* synthetic */ ChatUsersActivity this$0;
        private int totalCount;

        /* renamed from: $r8$lambda$euMukv-kHz9qYTe2kmRRRwA9NSo, reason: not valid java name */
        public static /* synthetic */ void m8646$r8$lambda$euMukvkHz9qYTe2kmRRRwA9NSo(SearchAdapter r0, ArrayList r1, LongSparseArray r2, ArrayList r3, ArrayList r4) {
            r0.lambda$updateSearchResults$4(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$gtJls5e-j_uov0BzJI_77Ou0yhQ, reason: not valid java name */
        public static /* synthetic */ void m8647$r8$lambda$gtJls5ej_uov0BzJI_77Ou0yhQ(SearchAdapter r0, String r1, ArrayList r2, ArrayList r3) {
            r0.lambda$processSearch$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$hXuFp5I56qwqm3rJD0y9slLlGDM(SearchAdapter r0, String r1) {
            r0.lambda$processSearch$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$lCqxkv8W8KqcDKGHQiIuN1gsIsc(SearchAdapter r0, int r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$tx4t7uAWUVHHjLHCt3Jqg6UYjEI(SearchAdapter r0, String r1) {
            r0.lambda$searchUsers$1(r1);
        }

        /* renamed from: $r8$lambda$wktNpBAxQ77u-DzQ3SxTyXZno88, reason: not valid java name */
        public static /* synthetic */ boolean m8648$r8$lambda$wktNpBAxQ77uDzQ3SxTyXZno88(SearchAdapter r0, ManageChatUserCell r1, boolean r2) {
            return r0.lambda$onCreateViewHolder$5(r1, r2);
        }

        public SearchAdapter(ChatUsersActivity r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.searchResultMap = new LongSparseArray();
            this.searchResultNames = new ArrayList();
            this.totalCount = 0;
            this.mContext = r2;
            SearchAdapterHelper r12 = new SearchAdapterHelper(true);
            this.searchAdapterHelper = r12;
            r12.setDelegate(new ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda1(this));
        }

        private /* synthetic */ void lambda$new$0(int r3) {
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L14;
            int r0 = getItemCount();
            notifyDataSetChanged();
            if (getItemCount() <= r0) goto L8;
            ChatUsersActivity.m8636$$Nest$mshowItemsAnimated(this.this$0, r0);
        L8:
            if (this.searchInProgress == false) goto L10;
            return;
        L10:
            if (getItemCount() != 0) goto L16;
            if (r3 == 0) goto L17;
            ChatUsersActivity.m8545$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
            return;
        L17:
            return;
        L16:
            return;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$5(ManageChatUserCell r3, boolean r4) {
            TLObject r0 = getItem(((Integer) r3.getTag()).intValue());
            if ((r0 instanceof TLRPC.ChannelParticipant) == true) goto L5;
            return false;
        L5:
            return ChatUsersActivity.m8623$$Nest$mcreateMenuForParticipant(this.this$0, (TLRPC.ChannelParticipant) r0, !r4, r3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r23v0, types: [org.telegram.ui.ChatUsersActivity$SearchAdapter] */
        /* JADX WARN: Type inference failed for: r4v15 */
        /* JADX WARN: Type inference failed for: r4v16 */
        /* JADX WARN: Type inference failed for: r4v23 */
        /* JADX WARN: Type inference failed for: r4v25 */
        private /* synthetic */ void lambda$processSearch$2(String r24, ArrayList r25, ArrayList r26) {
            ArrayList r1 = r25;
            String r3 = r24.trim().toLowerCase();
            if (r3.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), new LongSparseArray(), new ArrayList(), new ArrayList());
            return;
        L6:
            String r4 = LocaleController.getInstance().getTranslitString(r3);
            if (r3.equals(r4) == false) goto L9;
        L10:
            r4 = null;
        L11:
            int r5 = 0;
            if (r4 == null) goto L14;
            int r8 = 1;
        L15:
            int r82 = r8 + 1;
            String[] r9 = new String[r82];
            r9[0] = r3;
            if (r4 == null) goto L18;
            r9[1] = r4;
        L18:
            ArrayList r32 = new ArrayList();
            LongSparseArray r42 = new LongSparseArray();
            ArrayList r10 = new ArrayList();
            ArrayList r11 = new ArrayList();
            if (r1 == null) goto L67;
            int r15 = r1.size();
        L21:
            if (r5 >= r15) goto L67;
            TLObject r12 = (TLObject) r1.get(r5);
            if ((r12 instanceof TLRPC.ChatParticipant) == false) goto L25;
            int r17 = r82;
            long r7 = ((TLRPC.ChatParticipant) r12).user_id;
        L28:
            ChatUsersActivity r13 = this.this$0;
            if (r7 <= 0) goto L35;
            TLRPC.User r14 = r13.getMessagesController().getUser(Long.valueOf(r7));
            if (r14.id != this.this$0.getUserConfig().getClientUserId()) goto L33;
        L32:
            LongSparseArray r22 = r42;
            int r18 = r5;
            String[] r19 = r9;
            int r20 = r15;
            int r92 = r17;
        L66:
            r5 = r18 + 1;
            r1 = r25;
            r82 = r92;
            r9 = r19;
            r15 = r20;
            r42 = r22;
            goto L21
        L33:
            String r6 = UserObject.getUserName(r14).toLowerCase();
            String r72 = UserObject.getPublicUsername(r14);
            String r83 = r14.first_name;
            String r16 = r14.last_name;
        L34:
            r18 = r5;
            String r52 = LocaleController.getInstance().getTranslitString(r6);
            if (r6.equals(r52) == false) goto L39;
            r52 = null;
        L39:
            r19 = r9;
            r20 = r15;
            r92 = r17;
            int r152 = 0;
            boolean r172 = false;
        L40:
            if (r152 >= r92) goto L65;
            int r21 = r152;
            String r153 = r19[r21];
            if (r6.startsWith(r153) == true) goto L56;
            r22 = r42;
            if (r6.contains(" " + r153) == true) goto L57;
            if (r52 != null) goto L47;
        L51:
            if (r72 != null) goto L53;
        L55:
            ?? r43 = r172;
        L58:
            if (r43 != 0) goto L60;
            r152 = r21 + 1;
            r172 = r43;
            r42 = r22;
            goto L40
        L60:
            if (r43 != 1) goto L62;
            r10.add(AndroidUtilities.generateSearchName(r83, r16, r153));
        L63:
            r11.add(r12);
            goto L66
        L62:
            r10.add(AndroidUtilities.generateSearchName("@" + r72, null, "@" + r153));
            goto L63
        L53:
            if (r72.startsWith(r153) == false) goto L55;
            r43 = 2;
            goto L58
        L47:
            if (r52.startsWith(r153) == true) goto L57;
            if (r52.contains(" " + r153) == false) goto L51;
        L57:
            r43 = 1;
            goto L58
        L56:
            r22 = r42;
            goto L57
        L65:
            r22 = r42;
            goto L66
        L35:
            TLRPC.Chat r110 = r13.getMessagesController().getChat(Long.valueOf(-r7));
            r6 = r110.title.toLowerCase();
            r72 = ChatObject.getPublicUsername(r110);
            r83 = r110.title;
            r16 = null;
            goto L34
        L25:
            r17 = r82;
            if ((r12 instanceof TLRPC.ChannelParticipant) == false) goto L32;
            r7 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) r12).peer);
        L67:
            LongSparseArray r222 = r42;
            String[] r192 = r9;
            int r93 = r82;
            if (r26 == null) goto L104;
            int r111 = 0;
        L71:
            if (r111 >= r26.size()) goto L104;
            TLRPC.User r44 = this.this$0.getMessagesController().getUser(Long.valueOf(((TLRPC.TL_contact) r26.get(r111)).user_id));
            if (r44.id != this.this$0.getUserConfig().getClientUserId()) goto L75;
        L74:
            LongSparseArray r122 = r222;
        L103:
            r111 = r111 + 1;
            r222 = r122;
            goto L71
        L75:
            String r53 = UserObject.getUserName(r44).toLowerCase();
            String r62 = LocaleController.getInstance().getTranslitString(r53);
            if (r53.equals(r62) == false) goto L78;
            r62 = null;
        L78:
            char r73 = 0;
            int r84 = 0;
        L79:
            if (r84 >= r93) goto L74;
            String r123 = r192[r84];
            if (r53.startsWith(r123) == false) goto L83;
        L95:
            r73 = 1;
        L96:
            if (r73 != 0) goto L98;
            r84 = r84 + 1;
            goto L79
        L98:
            if (r73 != 1) goto L100;
            r10.add(AndroidUtilities.generateSearchName(r44.first_name, r44.last_name, r123));
        L101:
            r32.add(r44);
            LongSparseArray r74 = r222;
            r74.put(r44.id, r44);
            r122 = r74;
            goto L103
        L100:
            r10.add(AndroidUtilities.generateSearchName("@" + UserObject.getPublicUsername(r44), null, "@" + r123));
            goto L101
        L83:
            if (r53.contains(" " + r123) == true) goto L95;
            if (r62 != null) goto L86;
        L90:
            String r154 = UserObject.getPublicUsername(r44);
            if (r154 == null) goto L96;
            if (r154.startsWith(r123) == false) goto L96;
            r73 = 2;
            goto L96
        L86:
            if (r62.startsWith(r123) == true) goto L95;
            if (r62.contains(" " + r123) == false) goto L90;
        L104:
            updateSearchResults(r32, r222, r10, r11);
            return;
        L14:
            r8 = 0;
            goto L15
        L9:
            if (r4.length() != 0) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$processSearch$3(String r22) {
            ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda4 r1 = null;
            this.searchRunnable = null;
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == false) goto L5;
        L7:
            ArrayList r2 = null;
        L9:
            if (ChatUsersActivity.m8591$$Nest$fgetselectType(this.this$0) != 1) goto L11;
            ArrayList r3 = new ArrayList(ChatUsersActivity.access$100(this.this$0).contacts);
        L13:
            if (r2 != null) goto L18;
            if (r3 != null) goto L18;
            this.searchInProgress = false;
            String r7 = r22;
        L17:
            ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda4 r20 = r1;
            SearchAdapterHelper r6 = this.searchAdapterHelper;
            if (ChatUsersActivity.m8591$$Nest$fgetselectType(this.this$0) == 0) goto L22;
            boolean r8 = true;
        L24:
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == false) goto L27;
            long r12 = ChatUsersActivity.m8530$$Nest$fgetchatId(this.this$0);
        L28:
            r6.queryServerSearch(r7, r8, false, true, false, false, r12, false, ChatUsersActivity.m8617$$Nest$fgettype(this.this$0), 1, 0, r20);
            return;
        L27:
            r12 = 0;
            goto L28
        L22:
            r8 = false;
        L18:
            r7 = r22;
            r1 = new ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda4(this, r7, r2, r3);
            goto L17
        L11:
            r3 = null;
            goto L13
        L5:
            if (ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0) == null) goto L7;
            r2 = new ArrayList(ChatUsersActivity.m8554$$Nest$fgetinfo(this.this$0).participants.participants);
            goto L9
        }

        private /* synthetic */ void lambda$searchUsers$1(String r1) {
            processSearch(r1);
        }

        private /* synthetic */ void lambda$updateSearchResults$4(ArrayList r2, LongSparseArray r3, ArrayList r4, ArrayList r5) {
            if (ChatUsersActivity.m8590$$Nest$fgetsearching(this.this$0) == false) goto L19;
            this.searchInProgress = false;
            this.searchResult = r2;
            this.searchResultMap = r3;
            this.searchResultNames = r4;
            this.searchAdapterHelper.mergeResults(r2);
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == true) goto L8;
            ArrayList<TLObject> r22 = this.searchAdapterHelper.getGroupSearch();
            r22.clear();
            r22.addAll(r5);
        L8:
            int r23 = getItemCount();
            notifyDataSetChanged();
            if (getItemCount() <= r23) goto L12;
            ChatUsersActivity.m8636$$Nest$mshowItemsAnimated(this.this$0, r23);
        L12:
            if (this.searchAdapterHelper.isSearchInProgress() == false) goto L14;
            return;
        L14:
            if (getItemCount() != 0) goto L18;
            ChatUsersActivity.m8545$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
            return;
        L18:
            return;
        }

        private void processSearch(String r2) {
            AndroidUtilities.runOnUIThread(new ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda3(this, r2));
        }

        private void updateSearchResults(ArrayList<Object> r7, LongSparseArray<TLObject> r8, ArrayList<CharSequence> r9, ArrayList<TLObject> r10) {
            AndroidUtilities.runOnUIThread(new ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda5(this, r7, r8, r9, r10));
        }

        public TLObject getItem(int r3) {
            int r0 = this.searchAdapterHelper.getGroupSearch().size();
            if (r0 == 0) goto L11;
            int r02 = r0 + 1;
            if (r02 <= r3) goto L10;
            if (r3 != 0) goto L9;
            return null;
        L9:
            return this.searchAdapterHelper.getGroupSearch().get(r3 - 1);
        L10:
            r3 = r3 - r02;
        L11:
            int r03 = this.searchResult.size();
            if (r03 == 0) goto L20;
            int r04 = r03 + 1;
            if (r04 <= r3) goto L19;
            if (r3 != 0) goto L18;
            return null;
        L18:
            return (TLObject) this.searchResult.get(r3 - 1);
        L19:
            r3 = r3 - r04;
        L20:
            int r05 = this.searchAdapterHelper.getGlobalSearch().size();
            if (r05 != 0) goto L23;
        L28:
            return null;
        L23:
            if ((r05 + 1) <= r3) goto L28;
            if (r3 != 0) goto L27;
            return null;
        L27:
            return this.searchAdapterHelper.getGlobalSearch().get(r3 - 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.totalCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != this.globalStartRow) goto L5;
            return 1;
        L5:
            if (r2 != this.groupStartRow) goto L7;
            return 1;
        L7:
            if (r2 == this.contactsStartRow) goto L14;
            return 0;
        L14:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() == 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            this.totalCount = 0;
            int r1 = this.searchAdapterHelper.getGroupSearch().size();
            if (r1 == 0) goto L5;
            this.groupStartRow = 0;
            this.totalCount += r1 + 1;
        L6:
            int r12 = this.searchResult.size();
            if (r12 == 0) goto L9;
            int r4 = this.totalCount;
            this.contactsStartRow = r4;
            this.totalCount = r4 + (r12 + 1);
        L10:
            int r13 = this.searchAdapterHelper.getGlobalSearch().size();
            if (r13 == 0) goto L13;
            int r2 = this.totalCount;
            this.globalStartRow = r2;
            this.totalCount = r2 + (r13 + 1);
        L15:
            if (ChatUsersActivity.m8590$$Nest$fgetsearching(this.this$0) == true) goto L17;
        L21:
            super.notifyDataSetChanged();
            return;
        L17:
            if (ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0) == null) goto L21;
            if (ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).getAdapter() == ChatUsersActivity.m8589$$Nest$fgetsearchListViewAdapter(this.this$0)) goto L21;
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setAnimateEmptyView(true, 0);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setAdapter(ChatUsersActivity.m8589$$Nest$fgetsearchListViewAdapter(this.this$0));
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setFastScrollVisible(false);
            ChatUsersActivity.m8559$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(true);
            goto L21
        L13:
            this.globalStartRow = -1;
            goto L15
        L9:
            this.contactsStartRow = -1;
            goto L10
        L5:
            this.groupStartRow = -1;
            goto L6
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
            int r0 = r12.getItemViewType();
            if (r0 == 0) goto L30;
            if (r0 != 1) goto L100;
            GraySectionCell r122 = (GraySectionCell) r12.itemView;
            if (r13 != this.groupStartRow) goto L23;
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 0) goto L13;
            r122.setText(LocaleController.getString(R.string.ChannelBlockedUsers));
            return;
        L13:
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) != 3) goto L17;
            r122.setText(LocaleController.getString(R.string.ChannelRestrictedUsers));
            return;
        L17:
            if (ChatUsersActivity.m8555$$Nest$fgetisChannel(this.this$0) == false) goto L20;
            r122.setText(LocaleController.getString(R.string.ChannelSubscribers));
            return;
        L20:
            r122.setText(LocaleController.getString(R.string.ChannelMembers));
            return;
        L23:
            if (r13 != this.globalStartRow) goto L27;
            r122.setText(LocaleController.getString(R.string.GlobalSearch));
            return;
        L27:
            if (r13 != this.contactsStartRow) goto L98;
            r122.setText(LocaleController.getString(R.string.Contacts));
            return;
        L98:
            return;
        L100:
            return;
        L30:
            TLObject r02 = getItem(r13);
            boolean r2 = r02 instanceof TLRPC.User;
            String r3 = null;
            String r32 = null;
            r32 = null;
            r32 = null;
            TLObject r03 = r02;
            if (r2 == false) goto L34;
        L32:
            String r22 = null;
            TLObject r04 = r03;
        L46:
            int r4 = this.searchAdapterHelper.getGroupSearch().size();
            if (r4 == 0) goto L52;
            int r42 = r4 + 1;
            if (r42 <= r13) goto L51;
            String r43 = this.searchAdapterHelper.getLastFoundChannel();
            boolean r6 = true;
        L54:
            if (r6 == true) goto L68;
            int r8 = this.searchResult.size();
            if (r8 == 0) goto L68;
            int r82 = r8 + 1;
            if (r82 <= r13) goto L67;
            CharSequence r62 = this.searchResultNames.get(r13 - 1);
            SpannableStringBuilder r63 = r62;
            if (r62 == 0) goto L66;
            r63 = r62;
            if (TextUtils.isEmpty(r22) == true) goto L66;
            String r83 = r62.toString();
            r63 = r62;
            if (r83.startsWith("@" + r22) == false) goto L66;
            r63 = null;
            r32 = r62;
        L66:
            boolean r84 = true;
            r3 = r32;
            SpannableStringBuilder r64 = r63;
        L69:
            String r33 = r3;
            r33 = r3;
            if (r84 == true) goto L89;
            if (r22 == null) goto L89;
            int r85 = this.searchAdapterHelper.getGlobalSearch().size();
            r33 = r3;
            if (r85 == 0) goto L89;
            r33 = r3;
            if ((r85 + 1) <= r13) goto L89;
            String r11 = this.searchAdapterHelper.getLastFoundUsername();
            if (r11.startsWith("@") == false) goto L96;
            r11 = r11.substring(1);
        L96:
            SpannableStringBuilder r34 = new SpannableStringBuilder();     // Catch: Exception -> L87
            r34.append("@");     // Catch: Exception -> L87
            r34.append(r22);     // Catch: Exception -> L87
            int r1 = AndroidUtilities.indexOfIgnoreCase(r22, r11);     // Catch: Exception -> L87
            r33 = r34;
            if (r1 == (-1)) goto L89;
            int r112 = r11.length();     // Catch: Exception -> L87
            if (r1 != 0) goto L84;
            r112 = r112 + 1;     // Catch: Exception -> L87
        L85:
            r34.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)), r1, r112 + r1, 33);     // Catch: Exception -> L87
            r33 = r34;
            goto L89
        L84:
            r1 = r1 + 1;     // Catch: Exception -> L87
        L87:
            e = move-exception;
            FileLog.e(e);
            r33 = r22;
        L89:
            if (r43 == null) goto L94;
            if (r22 == null) goto L94;
            r64 = new SpannableStringBuilder(r22);
            int r113 = AndroidUtilities.indexOfIgnoreCase(r22, r43);
            if (r113 == (-1)) goto L94;
            r64.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)), r113, r43.length() + r113, 33);
        L94:
            ManageChatUserCell r114 = (ManageChatUserCell) r12.itemView;
            r114.setTag(Integer.valueOf(r13));
            r114.setData(r04, r64, r33, false);
            return;
        L67:
            r13 = r13 - r82;
        L68:
            r84 = r6;
            r64 = null;
            goto L69
        L51:
            r13 = r13 - r42;
        L52:
            r43 = null;
            r6 = false;
            goto L54
        L34:
            if ((r02 instanceof TLRPC.ChannelParticipant) == false) goto L44;
            long r44 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) r02).peer);
            ChatUsersActivity r23 = this.this$0;
            if (r44 < 0) goto L40;
            TLRPC.User r05 = r23.getMessagesController().getUser(Long.valueOf(r44));
            r03 = r05;
            if (r05 == null) goto L32;
            r22 = UserObject.getPublicUsername(r05);
            r04 = r05;
            goto L46
        L40:
            TLRPC.Chat r06 = r23.getMessagesController().getChat(Long.valueOf(-r44));
            r03 = r06;
            if (r06 == null) goto L32;
            r22 = ChatObject.getPublicUsername(r06);
            r04 = r06;
            goto L46
        L44:
            if ((r02 instanceof TLRPC.ChatParticipant) == false) goto L99;
            r03 = this.this$0.getMessagesController().getUser(Long.valueOf(((TLRPC.ChatParticipant) r02).user_id));
            goto L32
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 == 0) goto L4;
            FrameLayout r32 = new GraySectionCell(this.mContext, 26, ChatUsersActivity.access$000(this.this$0));
            r32.setBackground(null);
            FrameLayout r33 = r32;
        L10:
            return new RecyclerListView.Holder(r33);
        L4:
            Context r42 = this.mContext;
            if (ChatUsersActivity.m8591$$Nest$fgetselectType(this.this$0) != 0) goto L7;
            boolean r0 = true;
        L8:
            ManageChatUserCell r34 = new ManageChatUserCell(r42, 2, 2, r0);
            r34.setUsernameSubtitle();
            r34.setDelegate(new ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda0(this));
            r33 = r34;
            goto L10
        L7:
            r0 = false;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }

        public void removeUserId(long r2) {
            this.searchAdapterHelper.removeUserId(r2);
            TLObject r22 = this.searchResultMap.get(r2);
            if (r22 == null) goto L5;
            this.searchResult.remove(r22);
        L5:
            notifyDataSetChanged();
        }

        public void searchUsers(String r15) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            this.searchResult.clear();
            this.searchResultMap.clear();
            this.searchResultNames.clear();
            this.searchAdapterHelper.mergeResults(null);
            SearchAdapterHelper r2 = this.searchAdapterHelper;
            if (ChatUsersActivity.m8617$$Nest$fgettype(this.this$0) == 0) goto L8;
            boolean r4 = true;
        L10:
            if (ChatObject.isChannel(ChatUsersActivity.m8536$$Nest$fgetcurrentChat(this.this$0)) == false) goto L13;
            long r5 = ChatUsersActivity.m8530$$Nest$fgetchatId(this.this$0);
        L14:
            r2.queryServerSearch(null, r4, false, true, false, false, r5, false, ChatUsersActivity.m8617$$Nest$fgettype(this.this$0), 0);
            notifyDataSetChanged();
            if (TextUtils.isEmpty(r15) == true) goto L18;
            this.searchInProgress = true;
            ChatUsersActivity.m8545$$Nest$fgetemptyView(this.this$0).showProgress(true, true);
            DispatchQueue r0 = Utilities.searchQueue;
            ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda2 r1 = new ChatUsersActivity$SearchAdapter$$ExternalSyntheticLambda2(this, r15);
            this.searchRunnable = r1;
            r0.postRunnable(r1, 300);
            return;
        L18:
            return;
        L13:
            r5 = 0;
            goto L14
        L8:
            r4 = false;
            goto L10
        }
    }

    /* renamed from: $r8$lambda$-bOjjYPWZvO8pVI8Ap3Q5ynlwRI, reason: not valid java name */
    public static /* synthetic */ void m8500$r8$lambda$bOjjYPWZvO8pVI8Ap3Q5ynlwRI(ChatUsersActivity r0, TLRPC.User r1, long r2) {
        r0.lambda$createMenuForParticipant$14(r1, r2);
    }

    /* renamed from: $r8$lambda$55ORjWHfEEiMA-1j-Wmi42a1piM, reason: not valid java name */
    public static /* synthetic */ void m8501$r8$lambda$55ORjWHfEEiMA1jWmi42a1piM(TLRPC.TL_error r0, TLObject r1, ArrayList r2, int r3, AtomicInteger r4, ArrayList r5, Runnable r6) {
        if (r0 == null) goto L4;
    L6:
        r4.getAndIncrement();
        if (r4.get() != r5.size()) goto L10;
        r6.run();
        return;
    L10:
        return;
    L4:
        if ((r1 instanceof TLRPC.TL_channels_channelParticipants) == false) goto L6;
        r2.set(r3, (TLRPC.TL_channels_channelParticipants) r1);
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$6HcY23bEO3LZ3I10Fjd7piaeElk(ChatUsersActivity r0) {
        r0.lambda$getThemeDescriptions$35();
    }

    public static /* synthetic */ void $r8$lambda$93qFDFpdAEAynuLaBl4lcFLo7xg(ChatUsersActivity r0, long r1) {
        r0.lambda$createMenuForParticipant$18(r1);
    }

    /* renamed from: $r8$lambda$Ay141H6-CAdz2gE3DjV-H2TsgAg, reason: not valid java name */
    public static /* synthetic */ void m8502$r8$lambda$Ay141H6CAdz2gE3DjVH2TsgAg(Utilities.Callback r0, AlertDialog r1, int r2) {
        r0.run(1);
    }

    public static /* synthetic */ void $r8$lambda$B33nLi6eRDTu7T0LwqJUVdY8tRs(ChatUsersActivity r0) {
        r0.lambda$didReceivedNotification$25();
    }

    /* renamed from: $r8$lambda$FqRf28EsUJ5ecOfwAOzZl2-8nRg, reason: not valid java name */
    public static /* synthetic */ void m8503$r8$lambda$FqRf28EsUJ5ecOfwAOzZl28nRg(TLObject r0, TLRPC.TL_error r1) {
        AndroidUtilities.runOnUIThread(new ChatUsersActivity$$ExternalSyntheticLambda28());
    }

    public static /* synthetic */ void $r8$lambda$Hw_VOGDA8bwg_DFODV8InaxGpoA(ChatUsersActivity r0, long r1) {
        r0.lambda$createMenuForParticipant$16(r1);
    }

    /* renamed from: $r8$lambda$IJTbhR-nRE6RyeeB2Oavu5OEX9g, reason: not valid java name */
    public static /* synthetic */ void m8504$r8$lambda$IJTbhRnRE6RyeeB2Oavu5OEX9g(ChatUsersActivity r0, TextCell r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$createView$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$KmXXscvvIwyPVmly4oDmq8yM_FM(ChatUsersActivity r0, long r1, TLRPC.TL_chatAdminRights r3, String r4, TLObject r5) {
        r0.lambda$createMenuForParticipant$19(r1, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$L32-o19w8OD_eZrZU0EV-OyMTc0, reason: not valid java name */
    public static /* synthetic */ void m8505$r8$lambda$L32o19w8OD_eZrZU0EVOyMTc0(ChatUsersActivity r0, long r1, int r3, TLObject r4, TLRPC.TL_chatAdminRights r5, TLRPC.TL_chatBannedRights r6, String r7, boolean r8, Integer r9) {
        r0.lambda$createMenuForParticipant$10(r1, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$N16Gx7YQXDGgiAF37HORjRRoces(ChatUsersActivity r0, TextCell r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$createView$1(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$Rd5RvGnddwUCZG7sjy7Vi-5izyk, reason: not valid java name */
    public static /* synthetic */ void m8506$r8$lambda$Rd5RvGnddwUCZG7sjy7Vi5izyk(ChatUsersActivity r0, TextCell r1, boolean r2) {
        r0.lambda$createView$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Rh2xkDbhR5G2EkptHomreHqoJN8(ChatUsersActivity r0, long r1) {
        r0.lambda$createMenuForParticipant$20(r1);
    }

    public static /* synthetic */ int $r8$lambda$S0tzKXU2pZwBcNzLcToR7TeKYyk(TLObject r0, TLObject r1) {
        int r02 = getChannelAdminParticipantType(r0);
        int r12 = getChannelAdminParticipantType(r1);
        if (r02 <= r12) goto L6;
        return 1;
    L6:
        if (r02 >= r12) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$Sim9cjQKPVLNSboSpkBd2LFJGCk(ChatUsersActivity r0, TLRPC.Bool r1, TLRPC.TL_error r2) {
        r0.lambda$deletePeer$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$SztNNm0_MHqNJ9iTurYjguj0Q3U(ChatUsersActivity r0, TLRPC.User r1, TLObject r2, TLRPC.TL_chatAdminRights r3, TLRPC.TL_chatBannedRights r4, String r5, boolean r6, AlertDialog r7, int r8) {
        r0.lambda$createView$5(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$TOUhcWpMreRjPKK7PFZdD2Sj554(ChatUsersActivity r0, ArrayList r1, ArrayList r2) {
        r0.lambda$loadChatParticipants$31(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$VZGBEZq8jVt9yLH8_xL4SPlOMj4(TLObject r2, TLObject r3) {
        int r0 = getChannelAdminParticipantType(r2);
        int r1 = getChannelAdminParticipantType(r3);
        if (r0 <= r1) goto L6;
        return 1;
    L6:
        if (r0 >= r1) goto L10;
        return -1;
    L10:
        if ((r2 instanceof TLRPC.ChannelParticipant) == true) goto L12;
        return 0;
    L12:
        if ((r3 instanceof TLRPC.ChannelParticipant) == true) goto L14;
        return 0;
    L14:
        return (int) (MessageObject.getPeerId(((TLRPC.ChannelParticipant) r2).peer) - MessageObject.getPeerId(((TLRPC.ChannelParticipant) r3).peer));
    }

    public static /* synthetic */ void $r8$lambda$XKODWyoZFCQpuCQTCWfhLg7eYWQ(ChatUsersActivity r0, long r1) {
        r0.lambda$createMenuForParticipant$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$YMon1SkEXQS98W4osJRO6zZbJK8(ChatUsersActivity r0, TLObject r1, TLRPC.User r2, Utilities.Callback r3) {
        r0.lambda$createMenuForParticipant$13(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$_xzyfzSd5CfU-8fhcatACVuCFuo, reason: not valid java name */
    public static /* synthetic */ void m8507$r8$lambda$_xzyfzSd5CfU8fhcatACVuCFuo(ChatUsersActivity r0, TextCell r1, boolean r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: $r8$lambda$bt-QIs1J2K-IYdsS8SsxMaG7h-A, reason: not valid java name */
    public static /* synthetic */ void m8508$r8$lambda$btQIs1J2KIYdsS8SsxMaG7hA(ChatUsersActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$27(r1, r2);
    }

    /* renamed from: $r8$lambda$d6Q-RhzhUGAu0LSoKxsUnaTXpU8, reason: not valid java name */
    public static /* synthetic */ void m8509$r8$lambda$d6QRhzhUGAu0LSoKxsUnaTXpU8(ChatUsersActivity r0, TLRPC.Updates r1) {
        r0.lambda$deletePeer$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$h4Q84OchG9TaYHtXXBuTjXobgdo(ChatUsersActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$6(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$hHV4EA8yN6ZOz-6jLWbg7aDZO0w, reason: not valid java name */
    public static /* synthetic */ void m8510$r8$lambda$hHV4EA8yN6ZOz6jLWbg7aDZO0w(ChatUsersActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$deletePeer$24(r1, r2);
    }

    /* renamed from: $r8$lambda$jHFDBC9E-EBnH_rOiipqwFaK2xA, reason: not valid java name */
    public static /* synthetic */ int m8511$r8$lambda$jHFDBC9EEBnH_rOiipqwFaK2xA(ChatUsersActivity r0, int r1, TLObject r2, TLObject r3) {
        return r0.lambda$sortUsers$34(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kejzDe4gxrMtpEuK1vGlmHGcSd4(ArrayList r8, int r9, AtomicInteger r10, ArrayList r11, Runnable r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new ChatUsersActivity$$ExternalSyntheticLambda22(r14, r13, r8, r9, r10, r11, r12));
    }

    /* renamed from: $r8$lambda$knzkHAdEEcgdsiFrZIXE-7PW0Xs, reason: not valid java name */
    public static /* synthetic */ void m8512$r8$lambda$knzkHAdEEcgdsiFrZIXE7PW0Xs(ChatUsersActivity r0, long r1) {
        r0.lambda$processDone$28(r1);
    }

    /* renamed from: $r8$lambda$m5Rty2cn-stNJ-jdRA758SlCzpE, reason: not valid java name */
    public static /* synthetic */ void m8513$r8$lambda$m5Rty2cnstNJjdRA758SlCzpE(ChatUsersActivity r0) {
        r0.lambda$deletePeer$21();
    }

    /* renamed from: $r8$lambda$oeh6iY0vmOlL6KBPnQc0ji-JCtA, reason: not valid java name */
    public static /* synthetic */ void m8514$r8$lambda$oeh6iY0vmOlL6KBPnQc0jiJCtA() {
    }

    public static /* synthetic */ void $r8$lambda$qrvboOD76h006F4b2KomC2i9fZ4(Utilities.Callback r1) {
        r1.run(0);
    }

    /* renamed from: $r8$lambda$rldLa_8u3yKlIY-9liExR1FtMno, reason: not valid java name */
    public static /* synthetic */ void m8515$r8$lambda$rldLa_8u3yKlIY9liExR1FtMno(ChatUsersActivity r0, long r1, TLRPC.TL_chatBannedRights r3, String r4, TLObject r5) {
        r0.lambda$createMenuForParticipant$15(r1, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$yiU1Mj8LqULOBzCzhnriBKUdz0I(ChatUsersActivity r0, View r1, int r2) {
        return r0.lambda$createView$7(r1, r2);
    }

    /* renamed from: $r8$lambda$zG-1RyCRuF4_HEASRD9yxuHNNQo, reason: not valid java name */
    public static /* synthetic */ void m8516$r8$lambda$zG1RyCRuF4_HEASRD9yxuHNNQo(ChatUsersActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$26(r1, r2);
    }

    /* renamed from: $r8$lambda$zaYC3Cjux-3O4VmQ86L454dQuAo, reason: not valid java name */
    public static /* synthetic */ void m8517$r8$lambda$zaYC3Cjux3O4VmQ86L454dQuAo(ChatUsersActivity r0, TextCell r1, boolean r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        r0.lambda$createView$4(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$fgetaddNew2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8518$$Nest$fgetaddNew2Row(ChatUsersActivity r0) {
        return r0.addNew2Row;
    }

    /* renamed from: -$$Nest$fgetaddNewRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8519$$Nest$fgetaddNewRow(ChatUsersActivity r0) {
        return r0.addNewRow;
    }

    /* renamed from: -$$Nest$fgetaddNewSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8520$$Nest$fgetaddNewSectionRow(ChatUsersActivity r0) {
        return r0.addNewSectionRow;
    }

    /* renamed from: -$$Nest$fgetaddUsersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8521$$Nest$fgetaddUsersRow(ChatUsersActivity r0) {
        return r0.addUsersRow;
    }

    /* renamed from: -$$Nest$fgetantiSpamInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8522$$Nest$fgetantiSpamInfoRow(ChatUsersActivity r0) {
        return r0.antiSpamInfoRow;
    }

    /* renamed from: -$$Nest$fgetantiSpamRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8523$$Nest$fgetantiSpamRow(ChatUsersActivity r0) {
        return r0.antiSpamRow;
    }

    /* renamed from: -$$Nest$fgetblockedEmptyRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8524$$Nest$fgetblockedEmptyRow(ChatUsersActivity r0) {
        return r0.blockedEmptyRow;
    }

    /* renamed from: -$$Nest$fgetbotEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8525$$Nest$fgetbotEndRow(ChatUsersActivity r0) {
        return r0.botEndRow;
    }

    /* renamed from: -$$Nest$fgetbotHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8526$$Nest$fgetbotHeaderRow(ChatUsersActivity r0) {
        return r0.botHeaderRow;
    }

    /* renamed from: -$$Nest$fgetbotStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8527$$Nest$fgetbotStartRow(ChatUsersActivity r0) {
        return r0.botStartRow;
    }

    /* renamed from: -$$Nest$fgetbots, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8528$$Nest$fgetbots(ChatUsersActivity r0) {
        return r0.bots;
    }

    /* renamed from: -$$Nest$fgetchangeInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8529$$Nest$fgetchangeInfoRow(ChatUsersActivity r0) {
        return r0.changeInfoRow;
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m8530$$Nest$fgetchatId(ChatUsersActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetcontacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8531$$Nest$fgetcontacts(ChatUsersActivity r0) {
        return r0.contacts;
    }

    /* renamed from: -$$Nest$fgetcontactsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8532$$Nest$fgetcontactsEndRow(ChatUsersActivity r0) {
        return r0.contactsEndRow;
    }

    /* renamed from: -$$Nest$fgetcontactsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8533$$Nest$fgetcontactsHeaderRow(ChatUsersActivity r0) {
        return r0.contactsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetcontactsMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m8534$$Nest$fgetcontactsMap(ChatUsersActivity r0) {
        return r0.contactsMap;
    }

    /* renamed from: -$$Nest$fgetcontactsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8535$$Nest$fgetcontactsStartRow(ChatUsersActivity r0) {
        return r0.contactsStartRow;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m8536$$Nest$fgetcurrentChat(ChatUsersActivity r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetdefaultBannedRights, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatBannedRights m8537$$Nest$fgetdefaultBannedRights(ChatUsersActivity r0) {
        return r0.defaultBannedRights;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatUsersActivityDelegate m8538$$Nest$fgetdelegate(ChatUsersActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdoneItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m8539$$Nest$fgetdoneItem(ChatUsersActivity r0) {
        return r0.doneItem;
    }

    /* renamed from: -$$Nest$fgetdontRestrictBoostersInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8540$$Nest$fgetdontRestrictBoostersInfoRow(ChatUsersActivity r0) {
        return r0.dontRestrictBoostersInfoRow;
    }

    /* renamed from: -$$Nest$fgetdontRestrictBoostersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8541$$Nest$fgetdontRestrictBoostersRow(ChatUsersActivity r0) {
        return r0.dontRestrictBoostersRow;
    }

    /* renamed from: -$$Nest$fgetdontRestrictBoostersSliderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8542$$Nest$fgetdontRestrictBoostersSliderRow(ChatUsersActivity r0) {
        return r0.dontRestrictBoostersSliderRow;
    }

    /* renamed from: -$$Nest$fgeteditTagRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8543$$Nest$fgeteditTagRow(ChatUsersActivity r0) {
        return r0.editTagRow;
    }

    /* renamed from: -$$Nest$fgetembedLinksRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8544$$Nest$fgetembedLinksRow(ChatUsersActivity r0) {
        return r0.embedLinksRow;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m8545$$Nest$fgetemptyView(ChatUsersActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetenablePrice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8546$$Nest$fgetenablePrice(ChatUsersActivity r0) {
        return r0.enablePrice;
    }

    /* renamed from: -$$Nest$fgetfirstLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8547$$Nest$fgetfirstLoaded(ChatUsersActivity r0) {
        return r0.firstLoaded;
    }

    /* renamed from: -$$Nest$fgetflickerLoadingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FlickerLoadingView m8548$$Nest$fgetflickerLoadingView(ChatUsersActivity r0) {
        return r0.flickerLoadingView;
    }

    /* renamed from: -$$Nest$fgetgigaConvertRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8549$$Nest$fgetgigaConvertRow(ChatUsersActivity r0) {
        return r0.gigaConvertRow;
    }

    /* renamed from: -$$Nest$fgetgigaHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8550$$Nest$fgetgigaHeaderRow(ChatUsersActivity r0) {
        return r0.gigaHeaderRow;
    }

    /* renamed from: -$$Nest$fgetgigaInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8551$$Nest$fgetgigaInfoRow(ChatUsersActivity r0) {
        return r0.gigaInfoRow;
    }

    /* renamed from: -$$Nest$fgethideMembersInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8552$$Nest$fgethideMembersInfoRow(ChatUsersActivity r0) {
        return r0.hideMembersInfoRow;
    }

    /* renamed from: -$$Nest$fgethideMembersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8553$$Nest$fgethideMembersRow(ChatUsersActivity r0) {
        return r0.hideMembersRow;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m8554$$Nest$fgetinfo(ChatUsersActivity r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetisChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8555$$Nest$fgetisChannel(ChatUsersActivity r0) {
        return r0.isChannel;
    }

    /* renamed from: -$$Nest$fgetisCommunity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8556$$Nest$fgetisCommunity(ChatUsersActivity r0) {
        return r0.isCommunity;
    }

    /* renamed from: -$$Nest$fgetisEnabledNotRestrictBoosters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8557$$Nest$fgetisEnabledNotRestrictBoosters(ChatUsersActivity r0) {
        return r0.isEnabledNotRestrictBoosters;
    }

    /* renamed from: -$$Nest$fgetisForum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8558$$Nest$fgetisForum(ChatUsersActivity r0) {
        return r0.isForum;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m8559$$Nest$fgetlistView(ChatUsersActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlistViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m8560$$Nest$fgetlistViewAdapter(ChatUsersActivity r0) {
        return r0.listViewAdapter;
    }

    /* renamed from: -$$Nest$fgetloadingHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8561$$Nest$fgetloadingHeaderRow(ChatUsersActivity r0) {
        return r0.loadingHeaderRow;
    }

    /* renamed from: -$$Nest$fgetloadingProgressRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8562$$Nest$fgetloadingProgressRow(ChatUsersActivity r0) {
        return r0.loadingProgressRow;
    }

    /* renamed from: -$$Nest$fgetloadingUserCellRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8563$$Nest$fgetloadingUserCellRow(ChatUsersActivity r0) {
        return r0.loadingUserCellRow;
    }

    /* renamed from: -$$Nest$fgetloadingUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8564$$Nest$fgetloadingUsers(ChatUsersActivity r0) {
        return r0.loadingUsers;
    }

    /* renamed from: -$$Nest$fgetmanageLinkedPeersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8565$$Nest$fgetmanageLinkedPeersRow(ChatUsersActivity r0) {
        return r0.manageLinkedPeersRow;
    }

    /* renamed from: -$$Nest$fgetmanageTopicsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8566$$Nest$fgetmanageTopicsRow(ChatUsersActivity r0) {
        return r0.manageTopicsRow;
    }

    /* renamed from: -$$Nest$fgetmembersHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8567$$Nest$fgetmembersHeaderRow(ChatUsersActivity r0) {
        return r0.membersHeaderRow;
    }

    /* renamed from: -$$Nest$fgetnotRestrictBoosters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8568$$Nest$fgetnotRestrictBoosters(ChatUsersActivity r0) {
        return r0.notRestrictBoosters;
    }

    /* renamed from: -$$Nest$fgetparticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m8569$$Nest$fgetparticipants(ChatUsersActivity r0) {
        return r0.participants;
    }

    /* renamed from: -$$Nest$fgetparticipantsDivider2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8570$$Nest$fgetparticipantsDivider2Row(ChatUsersActivity r0) {
        return r0.participantsDivider2Row;
    }

    /* renamed from: -$$Nest$fgetparticipantsDividerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8571$$Nest$fgetparticipantsDividerRow(ChatUsersActivity r0) {
        return r0.participantsDividerRow;
    }

    /* renamed from: -$$Nest$fgetparticipantsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8572$$Nest$fgetparticipantsEndRow(ChatUsersActivity r0) {
        return r0.participantsEndRow;
    }

    /* renamed from: -$$Nest$fgetparticipantsInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8573$$Nest$fgetparticipantsInfoRow(ChatUsersActivity r0) {
        return r0.participantsInfoRow;
    }

    /* renamed from: -$$Nest$fgetparticipantsMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m8574$$Nest$fgetparticipantsMap(ChatUsersActivity r0) {
        return r0.participantsMap;
    }

    /* renamed from: -$$Nest$fgetparticipantsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8575$$Nest$fgetparticipantsStartRow(ChatUsersActivity r0) {
        return r0.participantsStartRow;
    }

    /* renamed from: -$$Nest$fgetpayInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8576$$Nest$fgetpayInfoRow(ChatUsersActivity r0) {
        return r0.payInfoRow;
    }

    /* renamed from: -$$Nest$fgetpayRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8577$$Nest$fgetpayRow(ChatUsersActivity r0) {
        return r0.payRow;
    }

    /* renamed from: -$$Nest$fgetpermissionsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8578$$Nest$fgetpermissionsSectionRow(ChatUsersActivity r0) {
        return r0.permissionsSectionRow;
    }

    /* renamed from: -$$Nest$fgetpinMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8579$$Nest$fgetpinMessagesRow(ChatUsersActivity r0) {
        return r0.pinMessagesRow;
    }

    /* renamed from: -$$Nest$fgetpriceHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8580$$Nest$fgetpriceHeaderRow(ChatUsersActivity r0) {
        return r0.priceHeaderRow;
    }

    /* renamed from: -$$Nest$fgetpriceInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8581$$Nest$fgetpriceInfoRow(ChatUsersActivity r0) {
        return r0.priceInfoRow;
    }

    /* renamed from: -$$Nest$fgetpriceRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8582$$Nest$fgetpriceRow(ChatUsersActivity r0) {
        return r0.priceRow;
    }

    /* renamed from: -$$Nest$fgetprofiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8583$$Nest$fgetprofiles(ChatUsersActivity r0) {
        return r0.profiles;
    }

    /* renamed from: -$$Nest$fgetprogressBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m8584$$Nest$fgetprogressBar(ChatUsersActivity r0) {
        return r0.progressBar;
    }

    /* renamed from: -$$Nest$fgetrecentActionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8585$$Nest$fgetrecentActionsRow(ChatUsersActivity r0) {
        return r0.recentActionsRow;
    }

    /* renamed from: -$$Nest$fgetremovedUsersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8586$$Nest$fgetremovedUsersRow(ChatUsersActivity r0) {
        return r0.removedUsersRow;
    }

    /* renamed from: -$$Nest$fgetrestricted1SectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8587$$Nest$fgetrestricted1SectionRow(ChatUsersActivity r0) {
        return r0.restricted1SectionRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8588$$Nest$fgetrowCount(ChatUsersActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetsearchListViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m8589$$Nest$fgetsearchListViewAdapter(ChatUsersActivity r0) {
        return r0.searchListViewAdapter;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8590$$Nest$fgetsearching(ChatUsersActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetselectType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8591$$Nest$fgetselectType(ChatUsersActivity r0) {
        return r0.selectType;
    }

    /* renamed from: -$$Nest$fgetselectedSlowmode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8592$$Nest$fgetselectedSlowmode(ChatUsersActivity r0) {
        return r0.selectedSlowmode;
    }

    /* renamed from: -$$Nest$fgetsendMediaEmbededLinksRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8593$$Nest$fgetsendMediaEmbededLinksRow(ChatUsersActivity r0) {
        return r0.sendMediaEmbededLinksRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaExpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8594$$Nest$fgetsendMediaExpanded(ChatUsersActivity r0) {
        return r0.sendMediaExpanded;
    }

    /* renamed from: -$$Nest$fgetsendMediaFilesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8595$$Nest$fgetsendMediaFilesRow(ChatUsersActivity r0) {
        return r0.sendMediaFilesRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaMusicRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8596$$Nest$fgetsendMediaMusicRow(ChatUsersActivity r0) {
        return r0.sendMediaMusicRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaPhotosRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8597$$Nest$fgetsendMediaPhotosRow(ChatUsersActivity r0) {
        return r0.sendMediaPhotosRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8598$$Nest$fgetsendMediaRow(ChatUsersActivity r0) {
        return r0.sendMediaRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaStickerGifsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8599$$Nest$fgetsendMediaStickerGifsRow(ChatUsersActivity r0) {
        return r0.sendMediaStickerGifsRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaVideoMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8600$$Nest$fgetsendMediaVideoMessagesRow(ChatUsersActivity r0) {
        return r0.sendMediaVideoMessagesRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaVideosRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8601$$Nest$fgetsendMediaVideosRow(ChatUsersActivity r0) {
        return r0.sendMediaVideosRow;
    }

    /* renamed from: -$$Nest$fgetsendMediaVoiceMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8602$$Nest$fgetsendMediaVoiceMessagesRow(ChatUsersActivity r0) {
        return r0.sendMediaVoiceMessagesRow;
    }

    /* renamed from: -$$Nest$fgetsendMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8603$$Nest$fgetsendMessagesRow(ChatUsersActivity r0) {
        return r0.sendMessagesRow;
    }

    /* renamed from: -$$Nest$fgetsendPollsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8604$$Nest$fgetsendPollsRow(ChatUsersActivity r0) {
        return r0.sendPollsRow;
    }

    /* renamed from: -$$Nest$fgetsendReactionsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8605$$Nest$fgetsendReactionsRow(ChatUsersActivity r0) {
        return r0.sendReactionsRow;
    }

    /* renamed from: -$$Nest$fgetsendStickersRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8606$$Nest$fgetsendStickersRow(ChatUsersActivity r0) {
        return r0.sendStickersRow;
    }

    /* renamed from: -$$Nest$fgetsignMessagesInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8607$$Nest$fgetsignMessagesInfoRow(ChatUsersActivity r0) {
        return r0.signMessagesInfoRow;
    }

    /* renamed from: -$$Nest$fgetsignMessagesProfilesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8608$$Nest$fgetsignMessagesProfilesRow(ChatUsersActivity r0) {
        return r0.signMessagesProfilesRow;
    }

    /* renamed from: -$$Nest$fgetsignMessagesRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8609$$Nest$fgetsignMessagesRow(ChatUsersActivity r0) {
        return r0.signMessagesRow;
    }

    /* renamed from: -$$Nest$fgetsignatures, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8610$$Nest$fgetsignatures(ChatUsersActivity r0) {
        return r0.signatures;
    }

    /* renamed from: -$$Nest$fgetslowmodeInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8611$$Nest$fgetslowmodeInfoRow(ChatUsersActivity r0) {
        return r0.slowmodeInfoRow;
    }

    /* renamed from: -$$Nest$fgetslowmodeRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8612$$Nest$fgetslowmodeRow(ChatUsersActivity r0) {
        return r0.slowmodeRow;
    }

    /* renamed from: -$$Nest$fgetslowmodeSelectRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8613$$Nest$fgetslowmodeSelectRow(ChatUsersActivity r0) {
        return r0.slowmodeSelectRow;
    }

    /* renamed from: -$$Nest$fgetstarsPrice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m8614$$Nest$fgetstarsPrice(ChatUsersActivity r2) {
        return r2.starsPrice;
    }

    /* renamed from: -$$Nest$fgettagsInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8615$$Nest$fgettagsInfoRow(ChatUsersActivity r0) {
        return r0.tagsInfoRow;
    }

    /* renamed from: -$$Nest$fgettagsRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8616$$Nest$fgettagsRow(ChatUsersActivity r0) {
        return r0.tagsRow;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8617$$Nest$fgettype(ChatUsersActivity r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fputnotRestrictBoosters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8618$$Nest$fputnotRestrictBoosters(ChatUsersActivity r0, int r1) {
        r0.notRestrictBoosters = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8619$$Nest$fputsearching(ChatUsersActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputselectedSlowmode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8620$$Nest$fputselectedSlowmode(ChatUsersActivity r0, int r1) {
        r0.selectedSlowmode = r1;
    }

    /* renamed from: -$$Nest$fputstarsPrice, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8621$$Nest$fputstarsPrice(ChatUsersActivity r0, long r1) {
        r0.starsPrice = r1;
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8622$$Nest$mcheckDiscard(ChatUsersActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mcreateMenuForParticipant, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8623$$Nest$mcreateMenuForParticipant(ChatUsersActivity r0, TLObject r1, boolean r2, View r3) {
        return r0.createMenuForParticipant(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mformatSeconds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m8624$$Nest$mformatSeconds(ChatUsersActivity r0, int r1) {
        return r0.formatSeconds(r1);
    }

    /* renamed from: -$$Nest$mformatUserPermissions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m8625$$Nest$mformatUserPermissions(ChatUsersActivity r0, TLRPC.TL_chatBannedRights r1) {
        return r0.formatUserPermissions(r1);
    }

    /* renamed from: -$$Nest$mgetParticipantsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8626$$Nest$mgetParticipantsCount(ChatUsersActivity r0) {
        return r0.getParticipantsCount();
    }

    /* renamed from: -$$Nest$mgetSecondsForIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8627$$Nest$mgetSecondsForIndex(ChatUsersActivity r0, int r1) {
        return r0.getSecondsForIndex(r1);
    }

    /* renamed from: -$$Nest$mgetSendMediaSelectedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m8628$$Nest$mgetSendMediaSelectedCount(ChatUsersActivity r0) {
        return r0.getSendMediaSelectedCount();
    }

    /* renamed from: -$$Nest$misExpandableSendMediaRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8629$$Nest$misExpandableSendMediaRow(ChatUsersActivity r0, int r1) {
        return r0.isExpandableSendMediaRow(r1);
    }

    /* renamed from: -$$Nest$mloadChatParticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8630$$Nest$mloadChatParticipants(ChatUsersActivity r0, int r1, int r2) {
        r0.loadChatParticipants(r1, r2);
    }

    /* renamed from: -$$Nest$monOwnerChaged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8631$$Nest$monOwnerChaged(ChatUsersActivity r0, TLRPC.User r1) {
        r0.onOwnerChaged(r1);
    }

    /* renamed from: -$$Nest$mopenRightsEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8632$$Nest$mopenRightsEdit(ChatUsersActivity r0, long r1, TLObject r3, TLRPC.TL_chatAdminRights r4, TLRPC.TL_chatBannedRights r5, String r6, boolean r7, int r8, boolean r9) {
        r0.openRightsEdit(r1, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8633$$Nest$mprocessDone(ChatUsersActivity r0) {
        r0.processDone();
    }

    /* renamed from: -$$Nest$mremoveParticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8634$$Nest$mremoveParticipants(ChatUsersActivity r0, long r1) {
        r0.removeParticipants(r1);
    }

    /* renamed from: -$$Nest$msetSendMediaEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8635$$Nest$msetSendMediaEnabled(ChatUsersActivity r0, boolean r1) {
        r0.setSendMediaEnabled(r1);
    }

    /* renamed from: -$$Nest$mshowItemsAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8636$$Nest$mshowItemsAnimated(ChatUsersActivity r0, int r1) {
        r0.showItemsAnimated(r1);
    }

    /* renamed from: -$$Nest$msortUsers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8637$$Nest$msortUsers(ChatUsersActivity r0, ArrayList r1) {
        r0.sortUsers(r1);
    }

    /* renamed from: -$$Nest$mupdateParticipantWithRights, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8638$$Nest$mupdateParticipantWithRights(ChatUsersActivity r0, TLRPC.ChannelParticipant r1, TLRPC.TL_chatAdminRights r2, TLRPC.TL_chatBannedRights r3, long r4, boolean r6) {
        r0.updateParticipantWithRights(r1, r2, r3, r4, r6);
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8639$$Nest$mupdateRows(ChatUsersActivity r0) {
        r0.updateRows();
    }

    public ChatUsersActivity(Bundle r10) {
        super(r10);
        this.defaultBannedRights = new TLRPC.TL_chatBannedRights();
        this.participants = new ArrayList();
        this.bots = new ArrayList();
        this.contacts = new ArrayList();
        this.participantsMap = new LongSparseArray();
        this.botsMap = new LongSparseArray();
        this.contactsMap = new LongSparseArray();
        this.initialStarsPrice = 10;
        this.starsPrice = 10;
        this.chatId = this.arguments.getLong("chat_id");
        this.type = this.arguments.getInt("type");
        this.transfer = this.arguments.getBoolean("transfer");
        this.needOpenSearch = this.arguments.getBoolean("open_search");
        this.selectType = this.arguments.getInt("selectType");
        TLRPC.Chat r102 = getMessagesController().getChat(Long.valueOf(this.chatId));
        this.currentChat = r102;
        boolean r0 = false;
        if (r102 == null) goto L15;
        TLRPC.TL_chatBannedRights r103 = r102.default_banned_rights;
        if (r103 == null) goto L15;
        TLRPC.TL_chatBannedRights r1 = this.defaultBannedRights;
        r1.view_messages = r103.view_messages;
        r1.send_stickers = r103.send_stickers;
        boolean r2 = r103.send_media;
        r1.send_media = r2;
        r1.embed_links = r103.embed_links;
        r1.send_messages = r103.send_messages;
        r1.send_games = r103.send_games;
        r1.send_inline = r103.send_inline;
        r1.send_gifs = r103.send_gifs;
        r1.pin_messages = r103.pin_messages;
        r1.edit_rank = r103.edit_rank;
        r1.send_reactions = r103.send_reactions;
        r1.send_polls = r103.send_polls;
        r1.invite_users = r103.invite_users;
        r1.manage_topics = r103.manage_topics;
        r1.change_info = r103.change_info;
        r1.manage_linked_peers = r103.manage_linked_peers;
        boolean r3 = r103.send_photos;
        r1.send_photos = r3;
        boolean r4 = r103.send_videos;
        r1.send_videos = r4;
        boolean r5 = r103.send_roundvideos;
        r1.send_roundvideos = r5;
        boolean r6 = r103.send_audios;
        r1.send_audios = r6;
        boolean r7 = r103.send_voices;
        r1.send_voices = r7;
        boolean r8 = r103.send_docs;
        r1.send_docs = r8;
        r1.send_plain = r103.send_plain;
        if (r2 == true) goto L15;
        if (r8 == false) goto L15;
        if (r7 == false) goto L15;
        if (r6 == false) goto L15;
        if (r5 == false) goto L15;
        if (r4 == false) goto L15;
        if (r3 == false) goto L15;
        r1.send_photos = false;
        r1.send_videos = false;
        r1.send_roundvideos = false;
        r1.send_audios = false;
        r1.send_voices = false;
        r1.send_docs = false;
    L15:
        this.initialBannedRights = ChatObject.getBannedRightsString(this.defaultBannedRights);
        this.isCommunity = ChatObject.isCommunity(this.currentChat);
        if (ChatObject.isChannel(this.currentChat) == true) goto L18;
    L22:
        this.isChannel = r0;
        this.isForum = ChatObject.isForum(this.currentChat);
        TLRPC.Chat r104 = this.currentChat;
        if (r104 == null) goto L26;
        boolean r02 = r104.signatures;
        this.signatures = r02;
        this.initialSignatures = r02;
        boolean r105 = r104.signature_profiles;
        this.profiles = r105;
        this.initialProfiles = r105;
        return;
    L26:
        return;
    L18:
        if (this.currentChat.megagroup == true) goto L22;
        if (this.isCommunity == true) goto L22;
        r0 = true;
        goto L22
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(ChatUsersActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ ContactsController access$100(ChatUsersActivity r0) {
        return r0.getContactsController();
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(ChatUsersActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$300(ChatUsersActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ INavigationLayout access$400(ChatUsersActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$500(ChatUsersActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$600(ChatUsersActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$700(ChatUsersActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$800(ChatUsersActivity r0) {
        return r0.parentLayout;
    }

    private boolean checkDiscard(boolean r5) {
        if (this.transfer == false) goto L6;
        return true;
    L6:
        if (ChatObject.getBannedRightsString(this.defaultBannedRights).equals(this.initialBannedRights) == true) goto L8;
    L22:
        if (r5 == false) goto L28;
        AlertDialog.Builder r52 = new AlertDialog.Builder(getParentActivity());
        r52.setTitle(LocaleController.getString("UserRestrictionsApplyChanges", R.string.UserRestrictionsApplyChanges));
        if (this.isChannel == false) goto L26;
        r52.setMessage(LocaleController.getString("ChannelSettingsChangedAlert", R.string.ChannelSettingsChangedAlert));
    L27:
        r52.setPositiveButton(LocaleController.getString("ApplyTheme", R.string.ApplyTheme), new ChatUsersActivity$$ExternalSyntheticLambda4(this));
        r52.setNegativeButton(LocaleController.getString("PassportDiscard", R.string.PassportDiscard), new ChatUsersActivity$$ExternalSyntheticLambda5(this));
        showDialog(r52.create());
        goto L28
    L26:
        r52.setMessage(LocaleController.getString("GroupSettingsChangedAlert", R.string.GroupSettingsChangedAlert));
    L28:
        return false;
    L8:
        if (this.initialSlowmode != this.selectedSlowmode) goto L22;
        if (hasNotRestrictBoostersChanges() == true) goto L22;
        boolean r0 = this.signatures;
        if (r0 != this.initialSignatures) goto L22;
        if (r0 == true) goto L15;
    L17:
        boolean r02 = false;
    L19:
        if (r02 != this.initialProfiles) goto L22;
        return true;
    L15:
        if (this.profiles == false) goto L17;
        r02 = true;
        goto L19
    }

    private boolean createMenuForParticipant(TLObject r27, boolean r28, View r29) {
        if (r27 != null) goto L5;
    L6:
        return false;
    L5:
        if (this.selectType != 0) goto L6;
        long r2 = 0;
        if ((r27 instanceof TLRPC.ChannelParticipant) == false) goto L11;
        TLRPC.ChannelParticipant r0 = (TLRPC.ChannelParticipant) r27;
        long r4 = MessageObject.getPeerId(r0.peer);
        boolean r7 = r0.can_edit;
        TLRPC.TL_chatBannedRights r8 = r0.banned_rights;
        TLRPC.TL_chatAdminRights r9 = r0.admin_rights;
        int r12 = r0.date;
        long r13 = 0;
        r2 = r4;
        TLRPC.TL_chatBannedRights r42 = r8;
        String r5 = r0.rank;
        int r02 = r12;
    L15:
        if (r2 != r13) goto L17;
    L18:
        return false;
    L17:
        if (r2 == getUserConfig().getClientUserId()) goto L18;
        boolean r16 = false;
        if (this.type != 2) goto L102;
        TLRPC.User r132 = getMessagesController().getUser(Long.valueOf(r2));
        if (ChatObject.canAddAdmins(this.currentChat) == true) goto L24;
    L31:
        boolean r82 = false;
    L32:
        boolean r14 = r27 instanceof TLRPC.TL_channelParticipantAdmin;
        if (r14 == false) goto L35;
    L40:
        if (r7 == false) goto L42;
    L41:
        TLRPC.TL_chatAdminRights r72 = r9;
        boolean r92 = true;
    L43:
        if (r14 == false) goto L45;
    L48:
        boolean r11 = true;
    L50:
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L59;
        if (r92 == false) goto L59;
        if (this.isChannel == true) goto L59;
        if (ChatObject.isChannel(this.currentChat) == false) goto L59;
        if (this.currentChat.gigagroup == true) goto L59;
        boolean r142 = true;
    L61:
        if (this.selectType != 0) goto L63;
        r82 = r82 & (!UserObject.isDeleted(r132));
    L63:
        boolean r15 = r82;
        if (r12 != 0) goto L66;
    L68:
        boolean r18 = false;
    L69:
        if (r15 == false) goto L71;
    L75:
        boolean r19 = true;
    L76:
        if (r28 == true) goto L101;
        if (r19 == true) goto L80;
        if (r18 == false) goto L101;
    L80:
        ChatUsersActivity$$ExternalSyntheticLambda9 r03 = new ChatUsersActivity$$ExternalSyntheticLambda9(this, r2, r02, r27, r72, r42, r5, r92);
        ItemOptions r43 = ItemOptions.makeOptions(this, r29).setScrimViewBackground(this.listView.getClipBackground(r29));
        int r52 = R.drawable.msg_admins;
        if (r11 == false) goto L84;
        int r73 = R.string.EditAdminRights;
    L85:
        ItemOptions r20 = r43.addIf(r15, r52, LocaleController.getString(r73), new ChatUsersActivity$$ExternalSyntheticLambda10(r03)).addIf(r142, R.drawable.msg_permissions, LocaleController.getString("ChangePermissions", R.string.ChangePermissions), new ChatUsersActivity$$ExternalSyntheticLambda11(this, r27, r132, r03));
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L89;
        if (r92 == false) goto L89;
        boolean r21 = true;
    L90:
        int r22 = R.drawable.msg_remove;
        if (this.isChannel == false) goto L94;
        String r04 = "ChannelRemoveUser";
        int r44 = R.string.ChannelRemoveUser;
    L95:
        ItemOptions r05 = r20.addIf(r21, r22, LocaleController.getString(r04, r44), true, new ChatUsersActivity$$ExternalSyntheticLambda12(this, r132, r2)).setMinWidth(190);
        if (r18 == false) goto L100;
        if (r19 == false) goto L99;
        r05.addGap();
    L99:
        r05.addText(LocaleController.formatGroupMemberJoined(r12), 13);
    L100:
        r05.show();
        return true;
    L94:
        r04 = "KickFromGroup";
        r44 = R.string.KickFromGroup;
    L89:
        r21 = false;
        goto L90
    L84:
        r73 = R.string.SetAsAdmin;
    L101:
        return r19;
    L71:
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L74;
        if (r92 == true) goto L75;
    L74:
        r19 = false;
        goto L76
    L66:
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == true) goto L68;
        r18 = true;
    L59:
        r142 = false;
        goto L61
    L45:
        if ((r27 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L48;
        r11 = false;
        goto L50
    L42:
        r72 = r9;
        r92 = false;
        goto L43
    L35:
        if ((r27 instanceof TLRPC.TL_channelParticipantCreator) == true) goto L40;
        if ((r27 instanceof TLRPC.TL_chatParticipantCreator) == true) goto L40;
        if ((r27 instanceof TLRPC.TL_chatParticipantAdmin) == false) goto L41;
    L24:
        if ((r27 instanceof TLRPC.TL_channelParticipant) == false) goto L26;
    L30:
        r82 = true;
        goto L32
    L26:
        if ((r27 instanceof TLRPC.TL_channelParticipantBanned) == true) goto L30;
        if ((r27 instanceof TLRPC.TL_chatParticipant) == true) goto L30;
        if (r7 == false) goto L31;
    L102:
        ItemOptions r112 = ItemOptions.makeOptions(this, r29);
        if (this.type != 3) goto L108;
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L108;
        r112.add(R.drawable.msg_permissions, LocaleController.getString(R.string.ChannelEditPermissions), new ChatUsersActivity$$ExternalSyntheticLambda13(this, r2, r42, r5, r27));
        r112.add(R.drawable.msg_delete, LocaleController.getString("ChannelDeleteFromList", R.string.ChannelDeleteFromList), true, new ChatUsersActivity$$ExternalSyntheticLambda14(this, r2));
    L134:
        r112.setScrimViewBackground(this.listView.getClipBackground(r29));
        r112.setMinWidth(190);
        if (r112.getItemsCount() <= 0) goto L137;
        r16 = true;
    L137:
        if (r28 == true) goto L145;
        if (r16 == false) goto L146;
        r112.show();
        return true;
    L146:
        return r16;
    L145:
        return r16;
    L108:
        if (this.type != 0) goto L124;
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L124;
        if (ChatObject.canAddUsers(this.currentChat) == false) goto L122;
        if (r2 <= r13) goto L122;
        if (ChatObject.isCommunity(this.currentChat) == true) goto L122;
        int r06 = R.drawable.msg_contact_add;
        if (this.isChannel == false) goto L120;
        int r45 = R.string.ChannelAddToChannel;
    L121:
        r112.add(r06, LocaleController.getString(r45), new ChatUsersActivity$$ExternalSyntheticLambda15(this, r2));
        goto L122
    L120:
        r45 = R.string.ChannelAddToGroup;
    L122:
        r112.add(R.drawable.msg_delete, LocaleController.getString("ChannelDeleteFromList", R.string.ChannelDeleteFromList), true, new ChatUsersActivity$$ExternalSyntheticLambda16(this, r2));
    L124:
        if (this.type != 1) goto L134;
        if (ChatObject.canAddAdmins(this.currentChat) == false) goto L134;
        if (r7 == false) goto L134;
        if (this.currentChat.creator == false) goto L131;
    L132:
        r112.add(R.drawable.msg_admins, LocaleController.getString("EditAdminRights", R.string.EditAdminRights), new ChatUsersActivity$$ExternalSyntheticLambda17(this, r2, r9, r5, r27));
    L133:
        r112.add(R.drawable.msg_remove, LocaleController.getString("ChannelRemoveUserAdmin", R.string.ChannelRemoveUserAdmin), true, new ChatUsersActivity$$ExternalSyntheticLambda18(this, r2));
        goto L134
    L131:
        if ((r27 instanceof TLRPC.TL_channelParticipantCreator) == true) goto L133;
    L11:
        if ((r27 instanceof TLRPC.ChatParticipant) == false) goto L13;
        TLRPC.ChatParticipant r07 = (TLRPC.ChatParticipant) r27;
        long r46 = r07.user_id;
        int r122 = r07.date;
        r7 = ChatObject.canAddAdmins(this.currentChat);
        r12 = r07.date;
        r02 = r122;
        r13 = 0;
        r2 = r46;
        r42 = null;
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        r9 = null;
        goto L15
    L13:
        r13 = 0;
        r42 = null;
        r5 = null;
        r9 = null;
        r02 = 0;
        r7 = false;
        r12 = 0;
        goto L15
    }

    private void deletePeer(long r4) {
        if (this.isCommunity == false) goto L6;
        TL_communities.TL_communities_toggleParticipantBanned r0 = new TL_communities.TL_communities_toggleParticipantBanned();
        r0.participant = getMessagesController().getInputPeer(r4);
        r0.community = getMessagesController().getInputChannel(this.chatId);
        r0.unban = true;
        getConnectionsManager().sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new ChatUsersActivity$$ExternalSyntheticLambda31(this));
        return;
    L6:
        TLRPC.TL_channels_editBanned r02 = new TLRPC.TL_channels_editBanned();
        r02.participant = getMessagesController().getInputPeer(r4);
        r02.channel = getMessagesController().getInputChannel(this.chatId);
        r02.banned_rights = new TLRPC.TL_chatBannedRights();
        getConnectionsManager().sendRequest(r02, new ChatUsersActivity$$ExternalSyntheticLambda32(this));
    }

    private String formatSeconds(int r3) {
        if (r3 >= 60) goto L7;
        return LocaleController.formatPluralString("Seconds", r3, new Object[0]);
    L7:
        if (r3 >= 3600) goto L11;
        return LocaleController.formatPluralString("Minutes", r3 / 60, new Object[0]);
    L11:
        return LocaleController.formatPluralString("Hours", (r3 / 60) / 60, new Object[0]);
    }

    private String formatUserPermissions(TLRPC.TL_chatBannedRights r5) {
        if (r5 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        StringBuilder r0 = new StringBuilder();
        boolean r1 = r5.view_messages;
        if (r1 == false) goto L11;
        if (this.defaultBannedRights.view_messages == r1) goto L11;
        r0.append(LocaleController.getString("UserRestrictionsNoRead", R.string.UserRestrictionsNoRead));
    L11:
        if (r5.send_messages == true) goto L13;
    L18:
        boolean r12 = r5.send_media;
        if (r12 == true) goto L21;
    L26:
        boolean r13 = r5.send_photos;
        if (r13 == true) goto L29;
    L34:
        boolean r14 = r5.send_videos;
        if (r14 == true) goto L37;
    L42:
        boolean r15 = r5.send_audios;
        if (r15 == true) goto L45;
    L50:
        boolean r16 = r5.send_docs;
        if (r16 == true) goto L53;
    L58:
        boolean r17 = r5.send_voices;
        if (r17 == true) goto L61;
    L66:
        boolean r18 = r5.send_roundvideos;
        if (r18 == true) goto L69;
    L74:
        boolean r19 = r5.send_stickers;
        if (r19 == true) goto L77;
    L82:
        boolean r110 = r5.send_polls;
        if (r110 == true) goto L85;
    L90:
        boolean r111 = r5.embed_links;
        if (r111 == true) goto L93;
    L100:
        boolean r112 = r5.invite_users;
        if (r112 == true) goto L103;
    L108:
        boolean r113 = r5.pin_messages;
        if (r113 == true) goto L111;
    L116:
        boolean r114 = r5.edit_rank;
        if (r114 == true) goto L119;
    L124:
        boolean r115 = r5.send_reactions;
        if (r115 == true) goto L127;
    L132:
        boolean r52 = r5.change_info;
        if (r52 == false) goto L141;
        if (this.defaultBannedRights.change_info == r52) goto L141;
        if (r0.length() == 0) goto L139;
        r0.append(", ");
    L139:
        r0.append(LocaleController.getString(R.string.UserRestrictionsNoChangeInfo));
    L141:
        if (r0.length() == 0) goto L144;
        r0.replace(0, 1, r0.substring(0, 1).toUpperCase());
        r0.append('.');
    L144:
        return r0.toString();
    L127:
        if (this.defaultBannedRights.send_reactions == r115) goto L132;
        if (r0.length() == 0) goto L131;
        r0.append(", ");
    L131:
        r0.append(LocaleController.getString(R.string.UserRestrictionsNoSendReactions));
        goto L132
    L119:
        if (this.defaultBannedRights.edit_rank == r114) goto L124;
        if (r0.length() == 0) goto L123;
        r0.append(", ");
    L123:
        r0.append(LocaleController.getString(R.string.UserRestrictionsNoEditTags));
        goto L124
    L111:
        if (this.defaultBannedRights.pin_messages == r113) goto L116;
        if (r0.length() == 0) goto L115;
        r0.append(", ");
    L115:
        r0.append(LocaleController.getString(R.string.UserRestrictionsNoPinMessages));
        goto L116
    L103:
        if (this.defaultBannedRights.invite_users == r112) goto L108;
        if (r0.length() == 0) goto L107;
        r0.append(", ");
    L107:
        r0.append(LocaleController.getString("UserRestrictionsNoInviteUsers", R.string.UserRestrictionsNoInviteUsers));
        goto L108
    L93:
        if (r5.send_plain == true) goto L100;
        if (this.defaultBannedRights.embed_links == r111) goto L100;
        if (r0.length() == 0) goto L99;
        r0.append(", ");
    L99:
        r0.append(LocaleController.getString("UserRestrictionsNoEmbedLinks", R.string.UserRestrictionsNoEmbedLinks));
        goto L100
    L85:
        if (this.defaultBannedRights.send_polls == r110) goto L90;
        if (r0.length() == 0) goto L89;
        r0.append(", ");
    L89:
        r0.append(LocaleController.getString("UserRestrictionsNoSendPolls", R.string.UserRestrictionsNoSendPolls));
        goto L90
    L77:
        if (this.defaultBannedRights.send_stickers == r19) goto L82;
        if (r0.length() == 0) goto L81;
        r0.append(", ");
    L81:
        r0.append(LocaleController.getString("UserRestrictionsNoSendStickers", R.string.UserRestrictionsNoSendStickers));
        goto L82
    L69:
        if (this.defaultBannedRights.send_roundvideos == r18) goto L74;
        if (r0.length() == 0) goto L73;
        r0.append(", ");
    L73:
        r0.append(LocaleController.getString("UserRestrictionsNoSendRound", R.string.UserRestrictionsNoSendRound));
        goto L74
    L61:
        if (this.defaultBannedRights.send_voices == r17) goto L66;
        if (r0.length() == 0) goto L65;
        r0.append(", ");
    L65:
        r0.append(LocaleController.getString("UserRestrictionsNoSendVoice", R.string.UserRestrictionsNoSendVoice));
        goto L66
    L53:
        if (this.defaultBannedRights.send_docs == r16) goto L58;
        if (r0.length() == 0) goto L57;
        r0.append(", ");
    L57:
        r0.append(LocaleController.getString("UserRestrictionsNoSendDocs", R.string.UserRestrictionsNoSendDocs));
        goto L58
    L45:
        if (this.defaultBannedRights.send_audios == r15) goto L50;
        if (r0.length() == 0) goto L49;
        r0.append(", ");
    L49:
        r0.append(LocaleController.getString("UserRestrictionsNoSendMusic", R.string.UserRestrictionsNoSendMusic));
        goto L50
    L37:
        if (this.defaultBannedRights.send_videos == r14) goto L42;
        if (r0.length() == 0) goto L41;
        r0.append(", ");
    L41:
        r0.append(LocaleController.getString("UserRestrictionsNoSendVideos", R.string.UserRestrictionsNoSendVideos));
        goto L42
    L29:
        if (this.defaultBannedRights.send_photos == r13) goto L34;
        if (r0.length() == 0) goto L33;
        r0.append(", ");
    L33:
        r0.append(LocaleController.getString("UserRestrictionsNoSendPhotos", R.string.UserRestrictionsNoSendPhotos));
        goto L34
    L21:
        if (this.defaultBannedRights.send_media == r12) goto L26;
        if (r0.length() == 0) goto L25;
        r0.append(", ");
    L25:
        r0.append(LocaleController.getString("UserRestrictionsNoSendMedia", R.string.UserRestrictionsNoSendMedia));
        goto L74
    L13:
        if (this.defaultBannedRights.send_plain == r5.send_plain) goto L18;
        if (r0.length() == 0) goto L17;
        r0.append(", ");
    L17:
        r0.append(LocaleController.getString("UserRestrictionsNoSendText", R.string.UserRestrictionsNoSendText));
        goto L18
    }

    private TLObject getAnyParticipant(long r3) {
        int r0 = 0;
    L4:
        if (r0 >= 3) goto L15;
        if (r0 != 0) goto L8;
        LongSparseArray<TLObject> r1 = this.contactsMap;
    L11:
        TLObject r12 = r1.get(r3);
        if (r12 != null) goto L13;
        r0 = r0 + 1;
        goto L4
    L13:
        return r12;
    L8:
        if (r0 != 1) goto L10;
        r1 = this.botsMap;
        goto L11
    L10:
        r1 = this.participantsMap;
        goto L11
    L15:
        return null;
    }

    public static int getChannelAdminParticipantType(TLObject r1) {
        if ((r1 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L5;
        return 0;
    L5:
        if ((r1 instanceof TLRPC.TL_channelParticipantSelf) == false) goto L8;
        return 0;
    L8:
        if ((r1 instanceof TLRPC.TL_channelParticipantAdmin) == false) goto L10;
        return 1;
    L10:
        if ((r1 instanceof TLRPC.TL_channelParticipant) == true) goto L18;
        return 2;
    L18:
        return 1;
    }

    private int getCurrentSlowmode() {
        TLRPC.ChatFull r2 = this.info;
        if (r2 == null) goto L31;
        int r22 = r2.slowmode_seconds;
        if (r22 != 5) goto L9;
        return 1;
    L9:
        if (r22 != 10) goto L13;
        return 2;
    L13:
        if (r22 != 30) goto L17;
        return 3;
    L17:
        if (r22 != 60) goto L21;
        return 4;
    L21:
        if (r22 != 300) goto L24;
        return 5;
    L24:
        if (r22 != 900) goto L28;
        return 6;
    L28:
        if (r22 != 3600) goto L33;
        return 7;
    L33:
        return 0;
    L31:
        return 0;
    }

    private int getParticipantsCount() {
        TLRPC.ChatFull r1 = this.info;
        if (r1 != null) goto L6;
        return 0;
    L6:
        int r0 = r1.participants_count;
        TLRPC.ChatParticipants r12 = r1.participants;
        if (r12 == null) goto L12;
        ArrayList<TLRPC.ChatParticipant> r13 = r12.participants;
        if (r13 == null) goto L12;
        return Math.max(r0, r13.size());
    L12:
        return r0;
    }

    private int getSecondsForIndex(int r2) {
        if (r2 != 1) goto L6;
        return 5;
    L6:
        if (r2 != 2) goto L10;
        return 10;
    L10:
        if (r2 != 3) goto L14;
        return 30;
    L14:
        if (r2 != 4) goto L17;
        return 60;
    L17:
        if (r2 != 5) goto L21;
        return 300;
    L21:
        if (r2 != 6) goto L25;
        return 900;
    L25:
        if (r2 != 7) goto L28;
        return 3600;
    L28:
        return 0;
    }

    private int getSendMediaSelectedCount() {
        return getSendMediaSelectedCount(this.defaultBannedRights);
    }

    public static int getSendMediaSelectedCount(TLRPC.TL_chatBannedRights r2) {
        int r0 = !r2.send_photos ? 1 : 0;
        if (r2.send_videos == true) goto L6;
        r0 = r0 + 1;
    L6:
        if (r2.send_stickers == true) goto L9;
        r0 = r0 + 1;
    L9:
        if (r2.send_audios == true) goto L12;
        r0 = r0 + 1;
    L12:
        if (r2.send_docs == true) goto L15;
        r0 = r0 + 1;
    L15:
        if (r2.send_voices == true) goto L18;
        r0 = r0 + 1;
    L18:
        if (r2.send_roundvideos == true) goto L21;
        r0 = r0 + 1;
    L21:
        if (r2.embed_links == true) goto L26;
        if (r2.send_plain == true) goto L26;
        r0 = r0 + 1;
    L26:
        if (r2.send_polls == true) goto L29;
        r0 = r0 + 1;
    L29:
        if (r2.send_reactions == false) goto L31;
        return r0;
    L31:
        return r0 + 1;
    }

    private boolean hasNotRestrictBoostersChanges() {
        if (this.isEnabledNotRestrictBoosters == true) goto L5;
    L7:
        boolean r0 = false;
    L8:
        TLRPC.ChatFull r3 = this.info;
        if (r3 == null) goto L17;
        int r32 = r3.boosts_unrestrict;
        int r4 = this.notRestrictBoosters;
        if (r32 != r4) goto L16;
        if (r0 == false) goto L14;
        if (r4 == 0) goto L16;
    L14:
        if (r0 == true) goto L17;
        if (r4 == 0) goto L17;
    L16:
        return true;
    L17:
        return false;
    L5:
        if (isNotRestrictBoostersVisible() == false) goto L7;
        r0 = true;
        goto L8
    }

    private boolean isExpandableSendMediaRow(int r2) {
        if (r2 != this.sendMediaPhotosRow) goto L5;
        return true;
    L5:
        if (r2 != this.sendMediaVideosRow) goto L7;
        return true;
    L7:
        if (r2 != this.sendMediaStickerGifsRow) goto L9;
        return true;
    L9:
        if (r2 != this.sendMediaMusicRow) goto L11;
        return true;
    L11:
        if (r2 != this.sendMediaFilesRow) goto L13;
        return true;
    L13:
        if (r2 != this.sendMediaVoiceMessagesRow) goto L15;
        return true;
    L15:
        if (r2 != this.sendReactionsRow) goto L17;
        return true;
    L17:
        if (r2 != this.sendMediaVideoMessagesRow) goto L19;
        return true;
    L19:
        if (r2 != this.sendMediaEmbededLinksRow) goto L21;
        return true;
    L21:
        if (r2 == this.sendPollsRow) goto L35;
        return false;
    L35:
        return true;
    }

    private boolean isNotRestrictBoostersVisible() {
        TLRPC.Chat r0 = this.currentChat;
        if (r0.megagroup == true) goto L5;
        return false;
    L5:
        if (r0.gigagroup == false) goto L7;
        return false;
    L7:
        if (ChatObject.canUserDoAdminAction(r0, 13) == true) goto L9;
        return false;
    L9:
        if (this.selectedSlowmode > 0) goto L34;
        TLRPC.TL_chatBannedRights r2 = this.defaultBannedRights;
        if (r2.send_plain == false) goto L13;
        return true;
    L13:
        if (r2.send_media == false) goto L15;
        return true;
    L15:
        if (r2.send_photos == false) goto L17;
        return true;
    L17:
        if (r2.send_videos == false) goto L19;
        return true;
    L19:
        if (r2.send_stickers == false) goto L21;
        return true;
    L21:
        if (r2.send_audios == false) goto L23;
        return true;
    L23:
        if (r2.send_docs == false) goto L25;
        return true;
    L25:
        if (r2.send_voices == false) goto L27;
        return true;
    L27:
        if (r2.send_roundvideos == false) goto L29;
        return true;
    L29:
        if (r2.embed_links == false) goto L31;
        return true;
    L31:
        if (r2.send_polls == false) goto L33;
        return true;
    L33:
        if (r2.send_reactions == false) goto L52;
        return true;
    L52:
        return false;
    L34:
        return true;
    }

    private /* synthetic */ void lambda$checkDiscard$26(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$checkDiscard$27(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$createMenuForParticipant$10(long r12, int r14, TLObject r15, TLRPC.TL_chatAdminRights r16, TLRPC.TL_chatBannedRights r17, String r18, boolean r19, Integer r20) {
        openRightsEdit2(r12, r14, r15, r16, r17, r18, r19, r20.intValue(), false);
    }

    private /* synthetic */ void lambda$createMenuForParticipant$13(TLObject r3, TLRPC.User r4, Utilities.Callback r5) {
        if ((r3 instanceof TLRPC.TL_channelParticipantAdmin) == false) goto L5;
    L9:
        showDialog(new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString("AppName", R.string.AppName)).setMessage(LocaleController.formatString(R.string.AdminWillBeRemoved, new Object[]{UserObject.getUserName(r4)})).setPositiveButton(LocaleController.getString("OK", R.string.OK), new ChatUsersActivity$$ExternalSyntheticLambda30(r5)).setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null).create());
        return;
    L5:
        if ((r3 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L9;
        r5.run(1);
    }

    private /* synthetic */ void lambda$createMenuForParticipant$14(TLRPC.User r4, long r5) {
        getMessagesController().deleteParticipantFromChat(this.chatId, r4);
        removeParticipants(r5);
        if (this.currentChat == null) goto L9;
        if (r4 != null) goto L6;
        return;
    L6:
        if (BulletinFactory.canShowBulletin(this) == false) goto L11;
        BulletinFactory.createRemoveFromChatBulletin(this, r4, this.currentChat.title).show();
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$createMenuForParticipant$15(long r14, TLRPC.TL_chatBannedRights r16, String r17, TLObject r18) {
        ChatRightsEditActivity r0 = new ChatRightsEditActivity(r14, this.chatId, null, this.defaultBannedRights, r16, r17, 1, true, false, null);
        r0.setDelegate(new AnonymousClass16(this, r18));
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createMenuForParticipant$16(long r1) {
        deletePeer(r1);
    }

    private /* synthetic */ void lambda$createMenuForParticipant$17(long r10) {
        deletePeer(r10);
        TLRPC.User r4 = getMessagesController().getUser(Long.valueOf(r10));
        getMessagesController().addUserToChat(this.chatId, r4, 0, null, this, null);
    }

    private /* synthetic */ void lambda$createMenuForParticipant$18(long r1) {
        deletePeer(r1);
    }

    private /* synthetic */ void lambda$createMenuForParticipant$19(long r14, TLRPC.TL_chatAdminRights r16, String r17, TLObject r18) {
        ChatRightsEditActivity r0 = new ChatRightsEditActivity(r14, this.chatId, r16, null, null, r17, 0, true, false, null);
        r0.setDelegate(new AnonymousClass17(this, r18));
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createMenuForParticipant$20(long r13) {
        getMessagesController().setUserAdminRole(this.chatId, getMessagesController().getUser(Long.valueOf(r13)), new TLRPC.TL_chatAdminRights(), _UrlKt.FRAGMENT_ENCODE_SET, !this.isChannel, this, false, false, null, null);
        removeParticipants(r13);
    }

    private /* synthetic */ void lambda$createView$0(TextCell r2, boolean r3) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        this.info.antispam = r3;
        r2.setChecked(r3);
        Switch r22 = r2.getCheckBox();
        if (ChatObject.canUserDoAdminAction(this.currentChat, 13) == false) goto L14;
        TLRPC.ChatFull r32 = this.info;
        if (r32 != null) goto L10;
    L13:
        int r33 = 0;
    L15:
        r22.setIcon(r33);
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString("UnknownError", R.string.UnknownError)).show();
        return;
    L10:
        if (r32.antispam == false) goto L13;
        if (getParticipantsCount() >= getMessagesController().telegramAntispamGroupSizeMin) goto L13;
    L14:
        r33 = R.drawable.permission_locked;
        goto L15
    }

    private /* synthetic */ void lambda$createView$1(TextCell r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r5, false);
        getMessagesController().putChatFull(this.info);
    L5:
        if (r6 != null) goto L7;
    L9:
        this.antiSpamToggleLoading = false;
        return;
    L7:
        if ("CHAT_NOT_MODIFIED".equals(r6.text) == true) goto L9;
        AndroidUtilities.runOnUIThread(new ChatUsersActivity$$ExternalSyntheticLambda27(this, r3, r4));
        goto L9
    }

    private /* synthetic */ void lambda$createView$2(TextCell r2, boolean r3) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        this.info.participants_hidden = r3;
        r2.setChecked(r3);
        Switch r22 = r2.getCheckBox();
        if (ChatObject.canUserDoAdminAction(this.currentChat, 2) == false) goto L14;
        TLRPC.ChatFull r32 = this.info;
        if (r32 != null) goto L10;
    L13:
        int r33 = 0;
    L15:
        r22.setIcon(r33);
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString("UnknownError", R.string.UnknownError)).show();
        return;
    L10:
        if (r32.participants_hidden == false) goto L13;
        if (getParticipantsCount() >= getMessagesController().hiddenMembersGroupSizeMin) goto L13;
    L14:
        r33 = R.drawable.permission_locked;
        goto L15
    }

    private /* synthetic */ void lambda$createView$3(TextCell r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        if (r5 == null) goto L5;
        getMessagesController().processUpdates((TLRPC.Updates) r5, false);
        getMessagesController().putChatFull(this.info);
    L5:
        if (r6 != null) goto L7;
    L9:
        this.hideMembersToggleLoading = false;
        return;
    L7:
        if ("CHAT_NOT_MODIFIED".equals(r6.text) == true) goto L9;
        AndroidUtilities.runOnUIThread(new ChatUsersActivity$$ExternalSyntheticLambda29(this, r3, r4));
        goto L9
    }

    private /* synthetic */ void lambda$createView$4(TextCell r1, boolean r2, TLRPC.Updates r3, TLRPC.TL_error r4) {
        if (r3 == null) goto L5;
        getMessagesController().processUpdates(r3, false);
        return;
    L5:
        if (r4 == null) goto L8;
        r1.setChecked(r2);
        BulletinFactory.of(this).showForError(r4);
        return;
    }

    private /* synthetic */ void lambda$createView$5(TLRPC.User r11, TLObject r12, TLRPC.TL_chatAdminRights r13, TLRPC.TL_chatBannedRights r14, String r15, boolean r16, AlertDialog r17, int r18) {
        long r1 = r11.id;
        int r0 = 1;
        if (this.selectType != 1) goto L5;
        r0 = 0;
    L5:
        openRightsEdit(r1, r12, r13, r14, r15, r16, r0, false);
    }

    private /* synthetic */ void lambda$createView$6(View r27, int r28, float r29, float r30) {
        int r4 = 0;
        if (this.listView.getAdapter() != this.listViewAdapter) goto L5;
        boolean r2 = true;
    L6:
        int r7 = 3;
        if (r28 != this.signMessagesRow) goto L10;
        boolean r3 = !this.signatures;
        this.signatures = r3;
        ((TextCheckCell) r27).setChecked(r3);
        AndroidUtilities.updateVisibleRows(this.listView);
        DiffCallback r32 = saveState();
        updateRows();
        updateListAnimated(r32);
        this.listViewAdapter.notifyItemChanged(this.signMessagesInfoRow);
    L252:
        if (r2 == false) goto L288;
        TLObject r1 = this.listViewAdapter.getItem(r28);
        if ((r1 instanceof TLRPC.User) == false) goto L258;
        long r12 = ((TLRPC.User) r1).id;
        String r20 = _UrlKt.FRAGMENT_ENCODE_SET;
        boolean r22 = true;
        TLRPC.TL_chatBannedRights r9 = null;
        TLRPC.TL_chatAdminRights r17 = null;
        long r13 = r12;
        long r292 = 0;
    L256:
        TLObject r33 = r1;
    L318:
        if (r13 == r292) goto L401;
        int r10 = this.selectType;
        if (r10 == 0) goto L347;
        if (r10 == 3) goto L326;
        if (r10 == 1) goto L326;
        removeParticipant(r13);
        return;
    L326:
        if (r10 == 1) goto L333;
        if (r22 == false) goto L333;
        if ((r33 instanceof TLRPC.TL_channelParticipantAdmin) == false) goto L331;
    L334:
        TLRPC.User r14 = getMessagesController().getUser(Long.valueOf(r13));
        AlertDialog.Builder r8 = new AlertDialog.Builder(getParentActivity());
        r8.setTitle(LocaleController.getString("AppName", R.string.AppName));
        r8.setMessage(LocaleController.formatString("AdminWillBeRemoved", R.string.AdminWillBeRemoved, new Object[]{UserObject.getUserName(r14)}));
        TLRPC.TL_chatAdminRights r42 = r17;
        r8.setPositiveButton(LocaleController.getString("OK", R.string.OK), new ChatUsersActivity$$ExternalSyntheticLambda26(this, r14, r33, r42, r9, r20, r22));
        r8.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), null);
        showDialog(r8.create());
        return;
    L331:
        if ((r33 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L334;
    L333:
        TLRPC.TL_chatBannedRights r11 = r9;
        String r6 = r20;
        if (r10 != 1) goto L338;
        int r82 = 0;
    L339:
        if (r10 == 1) goto L344;
        if (r10 == 3) goto L344;
        boolean r92 = false;
    L345:
        openRightsEdit(r13, r33, r17, r11, r6, r22, r82, r92);
        return;
    L344:
        r92 = true;
        goto L345
    L338:
        r82 = 1;
        goto L339
    L347:
        TLRPC.TL_chatBannedRights r112 = r9;
        int r93 = this.type;
        if (r93 == 1) goto L350;
        if (r93 == 0) goto L361;
        if (r93 == 3) goto L361;
        boolean r222 = false;
    L362:
        int r23 = this.type;
        if (r23 == 0) goto L384;
        if (r23 != 1) goto L366;
    L367:
        if (r23 == 2) goto L369;
    L371:
        if (r112 != null) goto L373;
        TLRPC.TL_chatBannedRights r94 = new TLRPC.TL_chatBannedRights();
        r94.view_messages = true;
        r94.send_stickers = true;
        r94.send_media = true;
        r94.send_photos = true;
        r94.send_videos = true;
        r94.send_roundvideos = true;
        r94.send_audios = true;
        r94.send_voices = true;
        r94.send_docs = true;
        r94.embed_links = true;
        r94.send_plain = true;
        r94.send_messages = true;
        r94.send_games = true;
        r94.send_inline = true;
        r94.send_gifs = true;
        r94.pin_messages = true;
        r94.edit_rank = true;
        r94.send_reactions = true;
        r94.send_polls = true;
        r94.invite_users = true;
        r94.manage_topics = true;
        r94.change_info = true;
        TLRPC.TL_chatBannedRights r19 = r94;
    L374:
        long r15 = this.chatId;
        TLRPC.TL_chatBannedRights r62 = this.defaultBannedRights;
        if (this.type != 1) goto L377;
        int r21 = 0;
    L378:
        if (r33 != null) goto L380;
        boolean r232 = true;
    L381:
        ChatRightsEditActivity r122 = new ChatRightsEditActivity(r13, r15, r17, r62, r19, r20, r21, r222, r232, null);
        r122.setDelegate(new AnonymousClass10(this, r33));
        presentFragment(r122);
        return;
    L380:
        r232 = false;
        goto L381
    L377:
        r21 = 1;
        goto L378
    L373:
        r19 = r112;
        goto L374
    L369:
        if (this.selectType != 0) goto L371;
    L366:
        if (this.isChannel == false) goto L367;
    L384:
        if (r13 == getUserConfig().getClientUserId()) goto L405;
        Bundle r24 = new Bundle();
        if (r13 <= r292) goto L389;
        r24.putLong("user_id", r13);
    L390:
        presentFragment(new ProfileActivity(r24));
        return;
    L389:
        r24.putLong("chat_id", -r13);
        goto L390
    L405:
        return;
    L361:
        boolean r25 = ChatObject.canBlockUsers(this.currentChat);
    L356:
        r222 = r25;
        goto L362
    L350:
        if (r13 != getUserConfig().getClientUserId()) goto L352;
    L355:
        r25 = false;
        goto L356
    L352:
        if (this.currentChat.creator == true) goto L354;
        if (r22 == false) goto L355;
    L354:
        r25 = true;
        goto L356
    L401:
        return;
    L258:
        if ((r1 instanceof TLRPC.ChannelParticipant) == false) goto L276;
        TLRPC.ChannelParticipant r26 = (TLRPC.ChannelParticipant) r1;
        long r123 = MessageObject.getPeerId(r26.peer);
        TLRPC.TL_chatBannedRights r34 = r26.banned_rights;
        TLRPC.TL_chatAdminRights r142 = r26.admin_rights;
        String r152 = r26.rank;
        r292 = 0;
        if ((r26 instanceof TLRPC.TL_channelParticipantAdmin) == true) goto L264;
        if ((r26 instanceof TLRPC.TL_channelParticipantCreator) == true) goto L264;
    L265:
        r22 = true;
    L268:
        if ((r1 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L274;
        r142 = ((TLRPC.TL_channelParticipantCreator) r1).admin_rights;
        if (r142 != null) goto L274;
        r142 = new TLRPC.TL_chatAdminRights();
        r142.manage_ranks = true;
        r142.add_admins = true;
        r142.pin_messages = true;
        r142.manage_topics = true;
        r142.invite_users = true;
        r142.ban_users = true;
        r142.delete_messages = true;
        r142.edit_messages = true;
        r142.post_messages = true;
        r142.change_info = true;
        if (this.isChannel == true) goto L274;
        r142.manage_call = true;
    L274:
        r9 = r34;
        r17 = r142;
        r20 = r152;
    L275:
        r33 = r1;
        r13 = r123;
    L264:
        if (r26.can_edit == true) goto L265;
        r22 = false;
        goto L268
    L276:
        r292 = 0;
        if ((r1 instanceof TLRPC.ChatParticipant) == false) goto L287;
        r123 = ((TLRPC.ChatParticipant) r1).user_id;
        r22 = this.currentChat.creator;
        if ((r1 instanceof TLRPC.TL_chatParticipantCreator) == false) goto L284;
        TLRPC.TL_chatAdminRights r95 = new TLRPC.TL_chatAdminRights();
        r95.manage_ranks = true;
        r95.add_admins = true;
        r95.pin_messages = true;
        r95.manage_topics = true;
        r95.invite_users = true;
        r95.ban_users = true;
        r95.delete_messages = true;
        r95.edit_messages = true;
        r95.post_messages = true;
        r95.change_info = true;
        if (this.isChannel == true) goto L283;
        r95.manage_call = true;
    L283:
        TLRPC.TL_chatAdminRights r143 = r95;
    L285:
        r20 = _UrlKt.FRAGMENT_ENCODE_SET;
        r9 = null;
    L286:
        r17 = r143;
        goto L275
    L284:
        r143 = null;
        goto L285
    L287:
        r13 = 0;
        r20 = _UrlKt.FRAGMENT_ENCODE_SET;
        r22 = false;
        r9 = null;
        r17 = null;
        goto L256
    L288:
        r292 = 0;
        r1 = this.searchListViewAdapter.getItem(r28);
        if ((r1 instanceof TLRPC.User) == false) goto L292;
        TLRPC.User r16 = (TLRPC.User) r1;
        getMessagesController().putUser(r16, false);
        long r18 = r16.id;
        long r124 = r18;
        r1 = getAnyParticipant(r18);
    L299:
        if ((r1 instanceof TLRPC.ChannelParticipant) == false) goto L310;
        TLRPC.ChannelParticipant r210 = (TLRPC.ChannelParticipant) r1;
        r123 = MessageObject.getPeerId(r210.peer);
        if ((r210 instanceof TLRPC.TL_channelParticipantAdmin) == true) goto L305;
        if ((r210 instanceof TLRPC.TL_channelParticipantCreator) == true) goto L305;
    L306:
        boolean r35 = true;
    L308:
        r9 = r210.banned_rights;
        r143 = r210.admin_rights;
        r20 = r210.rank;
        r22 = r35;
    L305:
        if (r210.can_edit == true) goto L306;
        r35 = false;
        goto L308
    L310:
        if ((r1 instanceof TLRPC.ChatParticipant) == false) goto L313;
        r124 = ((TLRPC.ChatParticipant) r1).user_id;
        r22 = this.currentChat.creator;
        r20 = _UrlKt.FRAGMENT_ENCODE_SET;
    L312:
        r9 = null;
        r17 = null;
        r13 = r124;
        goto L256
    L313:
        r20 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 != null) goto L316;
        r22 = true;
        goto L312
    L316:
        r22 = false;
        goto L312
    L292:
        if ((r1 instanceof TLRPC.ChannelParticipant) == false) goto L294;
    L297:
        r124 = 0;
        goto L299
    L294:
        if ((r1 instanceof TLRPC.ChatParticipant) == true) goto L297;
        r124 = 0;
        r1 = null;
        goto L299
    L10:
        if (r28 != this.signMessagesProfilesRow) goto L13;
        boolean r36 = !this.profiles;
        this.profiles = r36;
        ((TextCheckCell) r27).setChecked(r36);
        AndroidUtilities.updateVisibleRows(this.listView);
        DiffCallback r37 = saveState();
        updateRows();
        updateListAnimated(r37);
        this.listViewAdapter.notifyItemChanged(this.signMessagesInfoRow);
        goto L252
    L13:
        if (r28 != this.payRow) goto L15;
        boolean r38 = !this.enablePrice;
        this.enablePrice = r38;
        ((TextCheckCell) r27).setChecked(r38);
        AndroidUtilities.updateVisibleRows(this.listView);
        DiffCallback r39 = saveState();
        updateRows();
        updateListAnimated(r39);
        this.listViewAdapter.notifyItemChanged(this.payRow);
        goto L252
    L15:
        if (r2 == false) goto L252;
        if (isExpandableSendMediaRow(r28) == false) goto L59;
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L402;
        CheckBoxCell r310 = (CheckBoxCell) r27;
        if (r28 != this.sendMediaPhotosRow) goto L25;
        this.defaultBannedRights.send_photos = !r9.send_photos;
    L57:
        r310.setChecked(!r310.isChecked(), true);
        AndroidUtilities.updateVisibleRows(this.listView);
        DiffCallback r311 = saveState();
        updateRows();
        updateListAnimated(r311);
        goto L252
    L25:
        if (r28 != this.sendMediaVideosRow) goto L28;
        this.defaultBannedRights.send_videos = !r9.send_videos;
        goto L57
    L28:
        if (r28 != this.sendMediaStickerGifsRow) goto L31;
        TLRPC.TL_chatBannedRights r96 = this.defaultBannedRights;
        boolean r102 = !r96.send_stickers;
        r96.send_inline = r102;
        r96.send_gifs = r102;
        r96.send_games = r102;
        r96.send_stickers = r102;
        goto L57
    L31:
        if (r28 != this.sendMediaMusicRow) goto L34;
        this.defaultBannedRights.send_audios = !r9.send_audios;
        goto L57
    L34:
        if (r28 != this.sendMediaFilesRow) goto L37;
        this.defaultBannedRights.send_docs = !r9.send_docs;
        goto L57
    L37:
        if (r28 != this.sendMediaVoiceMessagesRow) goto L40;
        this.defaultBannedRights.send_voices = !r9.send_voices;
        goto L57
    L40:
        if (r28 != this.sendMediaVideoMessagesRow) goto L43;
        this.defaultBannedRights.send_roundvideos = !r9.send_roundvideos;
        goto L57
    L43:
        if (r28 != this.sendMediaEmbededLinksRow) goto L52;
        if (this.defaultBannedRights.send_plain == false) goto L50;
        View r97 = this.layoutManager.findViewByPosition(this.sendMessagesRow);
        if (r97 == null) goto L50;
        AndroidUtilities.shakeViewSpring(r97);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L50:
        this.defaultBannedRights.embed_links = !r9.embed_links;
        goto L57
    L52:
        if (r28 != this.sendPollsRow) goto L55;
        this.defaultBannedRights.send_polls = !r9.send_polls;
        goto L57
    L55:
        if (r28 != this.sendReactionsRow) goto L57;
        this.defaultBannedRights.send_reactions = !r9.send_reactions;
        goto L57
    L402:
        return;
    L59:
        if (r28 != this.dontRestrictBoostersRow) goto L62;
        TextCheckCell2 r312 = (TextCheckCell2) r27;
        boolean r98 = !r312.isChecked();
        this.isEnabledNotRestrictBoosters = r98;
        r312.setChecked(r98);
        AndroidUtilities.updateVisibleRows(this.listView);
        DiffCallback r313 = saveState();
        updateRows();
        updateListAnimated(r313);
        goto L252
    L62:
        if (r28 != this.addNewRow) goto L89;
        int r110 = this.type;
        if (r110 == 0) goto L83;
        if (r110 == 3) goto L83;
        if (r110 != 1) goto L70;
        Bundle r111 = new Bundle();
        r111.putLong("chat_id", this.chatId);
        r111.putInt("type", 2);
        r111.putInt("selectType", 1);
        ChatUsersActivity r211 = new ChatUsersActivity(r111);
        r211.setDelegate(new AnonymousClass7(this));
        r211.setInfo(this.info);
        presentFragment(r211);
        return;
    L70:
        if (r110 != 2) goto L392;
        Bundle r113 = new Bundle();
        r113.putBoolean("addToGroup", true);
        if (this.isChannel == false) goto L74;
        String r212 = "channelId";
    L75:
        r113.putLong(r212, this.currentChat.id);
        GroupCreateActivity r213 = new GroupCreateActivity(r113);
        r213.setInfo(this.info);
        LongSparseArray<TLObject> r114 = this.contactsMap;
        if (r114 != null) goto L78;
    L80:
        LongSparseArray<TLObject> r115 = this.participantsMap;
    L81:
        r213.setIgnoreUsers(r115);
        r213.setDelegate2(new AnonymousClass8(this, r213));
        presentFragment(r213);
        return;
    L78:
        if (r114.size() == 0) goto L80;
        r115 = this.contactsMap;
        goto L81
    L74:
        r212 = "chatId";
        goto L75
    L392:
        return;
    L83:
        Bundle r116 = new Bundle();
        r116.putLong("chat_id", this.chatId);
        r116.putInt("type", 2);
        if (this.type != 0) goto L86;
        r7 = 2;
    L86:
        r116.putInt("selectType", r7);
        ChatUsersActivity r214 = new ChatUsersActivity(r116);
        r214.setInfo(this.info);
        r214.setBannedRights(this.defaultBannedRights);
        r214.setDelegate(new AnonymousClass6(this));
        presentFragment(r214);
        return;
    L89:
        if (r28 != this.recentActionsRow) goto L93;
        presentFragment(new ChannelAdminLogActivity(this.currentChat));
        return;
    L93:
        if (r28 != this.antiSpamRow) goto L121;
        TextCell r117 = (TextCell) r27;
        TLRPC.ChatFull r215 = this.info;
        if (r215 == null) goto L103;
        if (r215.antispam == true) goto L103;
        if (getParticipantsCount() >= getMessagesController().telegramAntispamGroupSizeMin) goto L103;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.msg_antispam, AndroidUtilities.replaceTags(LocaleController.formatPluralString("ChannelAntiSpamForbidden", getMessagesController().telegramAntispamGroupSizeMin, new Object[0]))).show();
        return;
    L103:
        if (this.info != null) goto L105;
        return;
    L105:
        if (ChatObject.canUserDoAdminAction(this.currentChat, 13) == true) goto L107;
        return;
    L107:
        if (this.antiSpamToggleLoading == true) goto L395;
        this.antiSpamToggleLoading = true;
        boolean r216 = this.info.antispam;
        TLRPC.TL_channels_toggleAntiSpam r63 = new TLRPC.TL_channels_toggleAntiSpam();
        r63.channel = getMessagesController().getInputChannel(this.chatId);
        TLRPC.ChatFull r72 = this.info;
        boolean r5 = true ^ r72.antispam;
        r72.antispam = r5;
        r63.enabled = r5;
        r117.setChecked(r5);
        Switch r52 = r117.getCheckBox();
        if (ChatObject.canUserDoAdminAction(this.currentChat, 13) == false) goto L117;
        TLRPC.ChatFull r314 = this.info;
        if (r314 != null) goto L113;
    L118:
        r52.setIcon(r4);
        getConnectionsManager().sendRequest(r63, new ChatUsersActivity$$ExternalSyntheticLambda23(this, r117, r216));
        return;
    L113:
        if (r314.antispam == true) goto L118;
        if (getParticipantsCount() >= getMessagesController().telegramAntispamGroupSizeMin) goto L118;
    L117:
        r4 = R.drawable.permission_locked;
        goto L118
    L395:
        return;
    L121:
        if (r28 != this.hideMembersRow) goto L145;
        TextCell r118 = (TextCell) r27;
        if (getParticipantsCount() >= getMessagesController().hiddenMembersGroupSizeMin) goto L127;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contacts_sync_off, AndroidUtilities.replaceTags(LocaleController.formatPluralString("ChannelHiddenMembersForbidden", getMessagesController().hiddenMembersGroupSizeMin, new Object[0]))).show();
        return;
    L127:
        if (this.info != null) goto L129;
        return;
    L129:
        if (ChatObject.canUserDoAdminAction(this.currentChat, 2) == true) goto L131;
        return;
    L131:
        if (this.hideMembersToggleLoading == true) goto L398;
        this.hideMembersToggleLoading = true;
        boolean r217 = this.info.participants_hidden;
        TLRPC.TL_channels_toggleParticipantsHidden r315 = new TLRPC.TL_channels_toggleParticipantsHidden();
        r315.channel = getMessagesController().getInputChannel(this.chatId);
        TLRPC.ChatFull r64 = this.info;
        boolean r53 = true ^ r64.participants_hidden;
        r64.participants_hidden = r53;
        r315.enabled = r53;
        r118.setChecked(r53);
        Switch r54 = r118.getCheckBox();
        if (ChatObject.canUserDoAdminAction(this.currentChat, 2) == false) goto L141;
        TLRPC.ChatFull r65 = this.info;
        if (r65 != null) goto L137;
    L142:
        r54.setIcon(r4);
        getConnectionsManager().sendRequest(r315, new ChatUsersActivity$$ExternalSyntheticLambda24(this, r118, r217));
        return;
    L137:
        if (r65.participants_hidden == true) goto L142;
        if (getParticipantsCount() >= getMessagesController().hiddenMembersGroupSizeMin) goto L142;
    L141:
        r4 = R.drawable.permission_locked;
        goto L142
    L398:
        return;
    L145:
        if (r28 != this.tagsRow) goto L154;
        if (this.currentChat == null) goto L403;
        TextCell r316 = (TextCell) r27;
        boolean r99 = r316.isChecked();
        r316.setChecked(!r316.isChecked());
        TLRPC.TL_messages_editChatDefaultBannedRights r103 = new TLRPC.TL_messages_editChatDefaultBannedRights();
        r103.peer = MessagesController.getInputPeer(this.currentChat);
        TLRPC.Chat r119 = this.currentChat;
        if (r119.default_banned_rights != null) goto L152;
        r119.default_banned_rights = new TLRPC.TL_chatBannedRights();
    L152:
        TLRPC.TL_chatBannedRights r1110 = this.currentChat.default_banned_rights;
        r103.banned_rights = r1110;
        r1110.edit_rank = !r316.isChecked();
        getConnectionsManager().sendRequestTyped(r103, new AiTonesController$$ExternalSyntheticLambda0(), new ChatUsersActivity$$ExternalSyntheticLambda25(this, r316, r99));
        goto L252
    L403:
        return;
    L154:
        if (r28 != this.removedUsersRow) goto L158;
        Bundle r120 = new Bundle();
        r120.putLong("chat_id", this.chatId);
        r120.putInt("type", 0);
        ChatUsersActivity r218 = new ChatUsersActivity(r120);
        r218.setInfo(this.info);
        presentFragment(r218);
        return;
    L158:
        if (r28 != this.gigaConvertRow) goto L161;
        showDialog(new AnonymousClass9(this, getParentActivity(), this));
        goto L252
    L161:
        if (r28 != this.addNew2Row) goto L167;
        if (this.info == null) goto L399;
        ManageLinksActivity r219 = new ManageLinksActivity(this.chatId, 0, 0);
        TLRPC.ChatFull r121 = this.info;
        r219.setInfo(r121, r121.exported_invite);
        presentFragment(r219);
        return;
    L399:
        return;
    L167:
        if (r28 <= this.permissionsSectionRow) goto L171;
        if (r28 > Math.max(this.manageTopicsRow, this.changeInfoRow)) goto L171;
    L172:
        TextCheckCell2 r220 = (TextCheckCell2) r27;
        if (r220.isEnabled() == true) goto L178;
        if (r28 == this.sendMediaRow) goto L178;
        return;
    L178:
        if (r220.hasIcon() == false) goto L198;
        if (ChatObject.isPublic(this.currentChat) == false) goto L188;
        if (r28 != this.pinMessagesRow) goto L184;
    L185:
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.EditCantEditPermissionsPublic)).show();
        return;
    L184:
        if (r28 == this.changeInfoRow) goto L185;
    L188:
        if (ChatObject.isDiscussionGroup(this.currentAccount, this.chatId) == true) goto L190;
    L195:
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString("EditCantEditPermissions", R.string.EditCantEditPermissions)).show();
        return;
    L190:
        if (r28 != this.pinMessagesRow) goto L192;
    L193:
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.EditCantEditPermissionsDiscussion)).show();
        return;
    L192:
        if (r28 != this.changeInfoRow) goto L195;
    L198:
        if (r28 != this.sendMediaRow) goto L201;
        DiffCallback r125 = saveState();
        this.sendMediaExpanded = !this.sendMediaExpanded;
        AndroidUtilities.updateVisibleRows(this.listView);
        updateListAnimated(r125);
        return;
    L201:
        r220.setChecked(!r220.isChecked());
        if (r28 != this.changeInfoRow) goto L206;
        this.defaultBannedRights.change_info = !r0.change_info;
        return;
    L206:
        if (r28 != this.manageLinkedPeersRow) goto L210;
        this.defaultBannedRights.manage_linked_peers = !r0.manage_linked_peers;
        return;
    L210:
        if (r28 != this.addUsersRow) goto L214;
        this.defaultBannedRights.invite_users = !r0.invite_users;
        return;
    L214:
        if (r28 != this.manageTopicsRow) goto L218;
        this.defaultBannedRights.manage_topics = !r0.manage_topics;
        return;
    L218:
        if (r28 != this.pinMessagesRow) goto L222;
        this.defaultBannedRights.pin_messages = !r0.pin_messages;
        return;
    L222:
        if (r28 != this.editTagRow) goto L226;
        this.defaultBannedRights.edit_rank = !r0.edit_rank;
        return;
    L226:
        if (r28 != this.sendMessagesRow) goto L236;
        this.defaultBannedRights.send_plain = !r1.send_plain;
        int r126 = this.sendMediaEmbededLinksRow;
        if (r126 < 0) goto L230;
        this.listViewAdapter.notifyItemChanged(r126);
    L230:
        int r127 = this.sendMediaRow;
        if (r127 < 0) goto L233;
        this.listViewAdapter.notifyItemChanged(r127);
    L233:
        DiffCallback r128 = saveState();
        updateRows();
        updateListAnimated(r128);
        return;
    L236:
        if (r28 != this.sendMediaRow) goto L240;
        DiffCallback r129 = saveState();
        this.sendMediaExpanded = !this.sendMediaExpanded;
        AndroidUtilities.updateVisibleRows(this.listView);
        updateListAnimated(r129);
        return;
    L240:
        if (r28 != this.sendStickersRow) goto L244;
        TLRPC.TL_chatBannedRights r0 = this.defaultBannedRights;
        boolean r130 = !r0.send_stickers;
        r0.send_inline = r130;
        r0.send_gifs = r130;
        r0.send_games = r130;
        r0.send_stickers = r130;
        return;
    L244:
        if (r28 != this.embedLinksRow) goto L248;
        this.defaultBannedRights.embed_links = !r0.embed_links;
        return;
    L248:
        if (r28 != this.sendPollsRow) goto L400;
        this.defaultBannedRights.send_polls = !r0.send_polls;
        return;
    L400:
        return;
    L171:
        if (r28 != this.manageLinkedPeersRow) goto L252;
    L5:
        r2 = false;
        goto L6
    }

    private /* synthetic */ boolean lambda$createView$7(View r4, int r5) {
        if (getParentActivity() == null) goto L8;
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        ListAdapter r2 = this.listViewAdapter;
        if (r0 != r2) goto L8;
        return createMenuForParticipant(r2.getItem(r5), false, r4);
    L8:
        return false;
    }

    private /* synthetic */ void lambda$deletePeer$21() {
        getMessagesController().loadFullChat(this.chatId, 0, true);
    }

    private /* synthetic */ void lambda$deletePeer$22(TLRPC.Bool r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ChatUsersActivity$$ExternalSyntheticLambda33(this), 1000);
    }

    private /* synthetic */ void lambda$deletePeer$23(TLRPC.Updates r4) {
        TLRPC.Chat r42 = r4.chats.get(0);
        getMessagesController().loadFullChat(r42.id, 0, true);
    }

    private /* synthetic */ void lambda$deletePeer$24(TLObject r2, TLRPC.TL_error r3) {
        if (r2 == null) goto L7;
        TLRPC.Updates r22 = (TLRPC.Updates) r2;
        getMessagesController().processUpdates(r22, false);
        if (r22.chats.isEmpty() == true) goto L8;
        AndroidUtilities.runOnUIThread(new ChatUsersActivity$$ExternalSyntheticLambda34(this, r22), 1000);
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$didReceivedNotification$25() {
        loadChatParticipants(0, 200);
    }

    private /* synthetic */ void lambda$getThemeDescriptions$35() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof ManageChatUserCell) == false) goto L9;
        ((ManageChatUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12, types: [int] */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [int] */
    /* JADX WARN: Type inference failed for: r14v3 */
    private /* synthetic */ void lambda$loadChatParticipants$31(ArrayList r18, ArrayList r19) {
        boolean r2 = false;
        int r0 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r18.size()) goto L87;
        TLRPC.TL_channels_getParticipants r7 = (TLRPC.TL_channels_getParticipants) r18.get(r3);
        TLRPC.TL_channels_channelParticipants r9 = (TLRPC.TL_channels_channelParticipants) r19.get(r3);
        if (r7 == null) goto L8;
        if (r9 == null) goto L8;
        if (this.type != 1) goto L12;
        getMessagesController().processLoadedAdminsResponse(this.chatId, r9);
    L12:
        getMessagesController().putUsers(r9.users, r2);
        getMessagesController().putChats(r9.chats, r2);
        long r10 = getUserConfig().getClientUserId();
        if (this.selectType == 0) goto L22;
        ?? r12 = r2;
    L16:
        if (r12 >= r9.participants.size()) goto L22;
        if (MessageObject.getPeerId(r9.participants.get(r12).peer) == r10) goto L19;
        r12 = r12 + 1;
        goto L16
    L19:
        r9.participants.remove(r12);
    L22:
        if (this.type != 2) goto L30;
        this.delayResults--;
        TLRPC.ChannelParticipantsFilter r72 = r7.filter;
        if ((r72 instanceof TLRPC.TL_channelParticipantsContacts) == false) goto L27;
        ArrayList<TLObject> r73 = this.contacts;
        LongSparseArray<TLObject> r122 = this.contactsMap;
    L31:
        r73.clear();
        r73.addAll(r9.participants);
        int r13 = r9.participants.size();
        ?? r14 = r2;
    L32:
        if (r14 >= r13) goto L38;
        TLRPC.ChannelParticipant r15 = r9.participants.get(r14);
        int r16 = r3;
        if (r15.user_id != r10) goto L36;
        r73.remove(r15);
    L37:
        r3 = r16;
        r14 = r14 + 1;
        goto L32
    L36:
        r122.put(MessageObject.getPeerId(r15.peer), r15);
        goto L37
    L38:
        int r162 = r3;
        int r22 = r73.size() + r0;
        if (this.type != 2) goto L115;
        int r02 = this.participants.size();
        int r32 = 0;
    L41:
        if (r32 >= r02) goto L115;
        TLObject r92 = this.participants.get(r32);
        if ((r92 instanceof TLRPC.ChannelParticipant) == true) goto L46;
        this.participants.remove(r32);
    L45:
        r32 = r32 - 1;
        r02 = r02 - 1;
    L63:
        r32 = r32 + 1;
        goto L41
    L46:
        long r93 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) r92).peer);
        if (this.contactsMap.get(r93) == null) goto L49;
    L62:
        this.participants.remove(r32);
        this.participantsMap.remove(r93);
        goto L45
    L49:
        if (this.botsMap.get(r93) != null) goto L62;
        if (this.selectType == 1) goto L54;
    L58:
        LongSparseArray<TLRPC.GroupCallParticipant> r11 = this.ignoredUsers;
        if (r11 == null) goto L63;
        if (r11.indexOfKey(r93) < 0) goto L63;
    L54:
        if (r93 <= 0) goto L58;
        if (UserObject.isDeleted(getMessagesController().getUser(Long.valueOf(r93))) == false) goto L58;
    L115:
        int r03 = this.type;     // Catch: Exception -> L78
        if (r03 != 0) goto L67;
    L69:
        TLRPC.Chat r33 = this.currentChat;     // Catch: Exception -> L78
        if (r33 != null) goto L72;
    L80:
        if (r03 != 1) goto L84;
        sortAdmins(this.participants);     // Catch: Exception -> L78
    L84:
        r0 = r22;
    L85:
        r3 = r162 + 1;
        r2 = false;
        goto L4
    L72:
        if (r33.megagroup == false) goto L80;
        TLRPC.ChatFull r34 = this.info;     // Catch: Exception -> L78
        if ((r34 instanceof TLRPC.TL_channelFull) == false) goto L80;
        if (r34.participants_count > 200) goto L80;
        sortUsers(r73);     // Catch: Exception -> L78
        goto L84
    L67:
        if (r03 == 3) goto L69;
        if (r03 != 2) goto L80;
    L78:
        e = move-exception;
        FileLog.e(e);
        goto L84
    L27:
        if ((r72 instanceof TLRPC.TL_channelParticipantsBots) == false) goto L29;
        r73 = this.bots;
        r122 = this.botsMap;
        goto L31
    L29:
        r73 = this.participants;
        r122 = this.participantsMap;
        goto L31
    L30:
        r73 = this.participants;
        r122 = this.participantsMap;
        r122.clear();
    L8:
        r162 = r3;
        goto L85
    L87:
        if (this.type == 2) goto L89;
    L90:
        ListAdapter r23 = this.listViewAdapter;
        if (r23 == null) goto L93;
        int r24 = r23.getItemCount();
    L94:
        showItemsAnimated(r24);
        this.loadingUsers = false;
        this.firstLoaded = true;
        ActionBarMenuItem r25 = this.searchItem;
        if (r25 != null) goto L97;
    L104:
        updateRows();
        if (this.listViewAdapter == null) goto L113;
        this.listView.setAnimateEmptyView(this.openTransitionStarted, 0);
        this.listViewAdapter.notifyDataSetChanged();
        if (this.emptyView == null) goto L113;
        if (this.listViewAdapter.getItemCount() != 0) goto L113;
        if (this.firstLoaded == false) goto L113;
        this.emptyView.showProgress(false, true);
    L113:
        resumeDelayedFragmentAnimation();
        return;
    L97:
        if (this.type == 0) goto L99;
    L102:
        int r04 = 0;
    L103:
        r25.setVisibility(r04);
        goto L104
    L99:
        if (r0 > 5) goto L102;
        r04 = 8;
        goto L103
    L93:
        r24 = 0;
        goto L94
    L89:
        if (this.delayResults > 0) goto L104;
        goto L90
    }

    private /* synthetic */ void lambda$processDone$28(long r3) {
        if (r3 == 0) goto L6;
        this.chatId = r3;
        this.currentChat = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r3));
        processDone();
        return;
    }

    private /* synthetic */ int lambda$sortUsers$34(int r11, TLObject r12, TLObject r13) {
        TLRPC.ChannelParticipant r122 = (TLRPC.ChannelParticipant) r12;
        TLRPC.ChannelParticipant r132 = (TLRPC.ChannelParticipant) r13;
        long r0 = MessageObject.getPeerId(r122.peer);
        long r2 = MessageObject.getPeerId(r132.peer);
        int r1 = -100;
        if (r0 <= 0) goto L13;
        TLRPC.User r123 = getMessagesController().getUser(Long.valueOf(MessageObject.getPeerId(r122.peer)));
        if (r123 == null) goto L12;
        TLRPC.UserStatus r02 = r123.status;
        if (r02 == null) goto L12;
        if (r123.self == false) goto L11;
        int r124 = r11 + 50000;
    L15:
        if (r2 <= 0) goto L26;
        TLRPC.User r10 = getMessagesController().getUser(Long.valueOf(MessageObject.getPeerId(r132.peer)));
        if (r10 == null) goto L24;
        TLRPC.UserStatus r133 = r10.status;
        if (r133 == null) goto L24;
        if (r10.self == false) goto L23;
        r1 = r11 + 50000;
        goto L26
    L23:
        r1 = r133.expires;
    L24:
        r1 = 0;
    L26:
        if (r124 <= 0) goto L33;
        if (r1 <= 0) goto L33;
        if (r124 <= r1) goto L30;
        return 1;
    L30:
        if (r124 >= r1) goto L32;
        return -1;
    L32:
        return 0;
    L33:
        if (r124 >= 0) goto L40;
        if (r1 >= 0) goto L40;
        if (r124 <= r1) goto L37;
        return 1;
    L37:
        if (r124 >= r1) goto L39;
        return -1;
    L39:
        return 0;
    L40:
        if (r124 >= 0) goto L42;
        if (r1 <= 0) goto L42;
    L44:
        return -1;
    L42:
        if (r124 != 0) goto L45;
        if (r1 != 0) goto L44;
    L45:
        if (r1 >= 0) goto L47;
        if (r124 <= 0) goto L47;
    L49:
        return 1;
    L47:
        if (r1 != 0) goto L50;
        if (r124 != 0) goto L49;
    L50:
        return 0;
    L11:
        r124 = r02.expires;
    L12:
        r124 = 0;
        goto L15
    L13:
        r124 = -100;
        goto L15
    }

    private void loadChatParticipants(int r2, int r3) {
        if (this.loadingUsers == false) goto L5;
        return;
    L5:
        this.contactsEndReached = false;
        this.botsEndReached = false;
        loadChatParticipants(r2, r3, true);
    }

    private void loadChatParticipants(int r10, int r11, boolean r12) {
        int r2 = 0;
        if (this.isCommunity == false) goto L16;
        if (this.type != 2) goto L16;
        this.loadingUsers = false;
        this.participants.clear();
        this.bots.clear();
        this.contacts.clear();
        this.participantsMap.clear();
        this.contactsMap.clear();
        this.botsMap.clear();
        HashMap<String, ArrayList<TLRPC.TL_contact>> r102 = ContactsController.getInstance(this.currentAccount).usersSectionsDict;
        ArrayList<String> r112 = ContactsController.getInstance(this.currentAccount).sortedUsersSectionsArray;
        int r122 = r112.size();
        int r0 = 0;
    L7:
        if (r0 >= r122) goto L11;
        String r1 = r112.get(r0);
        r0 = r0 + 1;
        ArrayList<TLRPC.TL_contact> r13 = r102.get(r1);
        int r3 = r13.size();
        int r4 = 0;
    L9:
        if (r4 >= r3) goto L7;
        TLRPC.TL_contact r5 = r13.get(r4);
        r4 = r4 + 1;
        this.participants.add(getMessagesController().getUser(Long.valueOf(r5.user_id)));
        goto L9
    L11:
        updateRows();
        ListAdapter r9 = this.listViewAdapter;
        if (r9 == null) goto L83;
        r9.notifyDataSetChanged();
        return;
    L83:
        return;
    L16:
        if (ChatObject.isChannel(this.currentChat) == true) goto L73;
        this.loadingUsers = false;
        this.participants.clear();
        this.bots.clear();
        this.contacts.clear();
        this.participantsMap.clear();
        this.contactsMap.clear();
        this.botsMap.clear();
        int r103 = this.type;
        if (r103 != 1) goto L31;
        TLRPC.ChatFull r104 = this.info;
        if (r104 == null) goto L66;
        TLRPC.ChatParticipants r105 = r104.participants;
        if (r105 == null) goto L66;
        int r106 = r105.participants.size();
    L24:
        if (r2 >= r106) goto L66;
        TLRPC.ChatParticipant r113 = this.info.participants.participants.get(r2);
        if ((r113 instanceof TLRPC.TL_chatParticipantCreator) == false) goto L28;
    L29:
        this.participants.add(r113);
    L30:
        this.participantsMap.put(r113.user_id, r113);
        r2 = r2 + 1;
        goto L24
    L28:
        if ((r113 instanceof TLRPC.TL_chatParticipantAdmin) == false) goto L30;
    L66:
        ListAdapter r107 = this.listViewAdapter;
        if (r107 == null) goto L69;
        r107.notifyDataSetChanged();
    L69:
        updateRows();
        ListAdapter r92 = this.listViewAdapter;
        if (r92 == null) goto L101;
        r92.notifyDataSetChanged();
        return;
    L101:
        return;
    L31:
        if (r103 != 2) goto L66;
        TLRPC.ChatFull r108 = this.info;
        if (r108 == null) goto L66;
        if (r108.participants == null) goto L66;
        long r109 = getUserConfig().clientUserId;
        int r123 = this.info.participants.participants.size();
    L37:
        if (r2 >= r123) goto L66;
        TLRPC.ChatParticipant r02 = this.info.participants.participants.get(r2);
        if (this.selectType != 0) goto L41;
    L43:
        LongSparseArray<TLRPC.GroupCallParticipant> r14 = this.ignoredUsers;
        if (r14 == null) goto L49;
        if (r14.indexOfKey(r02.user_id) < 0) goto L49;
    L65:
        r2 = r2 + 1;
    L49:
        if (this.selectType != 1) goto L57;
        if (getContactsController().isContact(r02.user_id) == false) goto L54;
        this.contacts.add(r02);
        this.contactsMap.put(r02.user_id, r02);
        goto L65
    L54:
        if (UserObject.isDeleted(getMessagesController().getUser(Long.valueOf(r02.user_id))) == true) goto L65;
        this.participants.add(r02);
        this.participantsMap.put(r02.user_id, r02);
        goto L65
    L57:
        if (getContactsController().isContact(r02.user_id) == false) goto L59;
        this.contacts.add(r02);
        this.contactsMap.put(r02.user_id, r02);
        goto L65
    L59:
        TLRPC.User r15 = getMessagesController().getUser(Long.valueOf(r02.user_id));
        if (r15 != null) goto L62;
    L64:
        this.participants.add(r02);
        this.participantsMap.put(r02.user_id, r02);
        goto L65
    L62:
        if (r15.bot == false) goto L64;
        this.bots.add(r02);
        this.botsMap.put(r02.user_id, r02);
        goto L65
    L41:
        if (r02.user_id != r109) goto L43;
    L73:
        this.loadingUsers = true;
        StickerEmptyView r03 = this.emptyView;
        if (r03 == null) goto L76;
        r03.showProgress(true, false);
    L76:
        ListAdapter r04 = this.listViewAdapter;
        if (r04 == null) goto L79;
        r04.notifyDataSetChanged();
    L79:
        ArrayList<TLRPC.TL_channels_getParticipants> r7 = loadChatParticipantsRequests(r10, r11, r12);
        ArrayList r42 = new ArrayList();
        ChatUsersActivity$$ExternalSyntheticLambda2 r8 = new ChatUsersActivity$$ExternalSyntheticLambda2(this, r7, r42);
        AtomicInteger r6 = new AtomicInteger(0);
        int r52 = 0;
    L81:
        if (r52 >= r7.size()) goto L102;
        r42.add(null);
        getConnectionsManager().bindRequestToGuid(getConnectionsManager().sendRequest(r7.get(r52), new ChatUsersActivity$$ExternalSyntheticLambda3(r42, r52, r6, r7, r8)), this.classGuid);
        r52 = r52 + 1;
        goto L81
    }

    private ArrayList<TLRPC.TL_channels_getParticipants> loadChatParticipantsRequests(int r9, int r10, boolean r11) {
        TLRPC.TL_channels_getParticipants r112 = new TLRPC.TL_channels_getParticipants();
        ArrayList<TLRPC.TL_channels_getParticipants> r0 = new ArrayList();
        r0.add(r112);
        r112.channel = getMessagesController().getInputChannel(this.chatId);
        int r1 = this.type;
        if (r1 != 0) goto L6;
        r112.filter = new TLRPC.TL_channelParticipantsKicked();
    L32:
        r112.filter.q = _UrlKt.FRAGMENT_ENCODE_SET;
        r112.offset = r9;
        r112.limit = r10;
        return r0;
    L6:
        if (r1 != 1) goto L9;
        r112.filter = new TLRPC.TL_channelParticipantsAdmins();
        goto L32
    L9:
        if (r1 != 2) goto L30;
        TLRPC.ChatFull r12 = this.info;
        if (r12 != null) goto L13;
    L19:
        int r13 = this.selectType;
        boolean r6 = this.contactsEndReached;
        if (r13 != 1) goto L24;
        if (r6 == true) goto L23;
        this.delayResults = 2;
        r112.filter = new TLRPC.TL_channelParticipantsContacts();
        this.contactsEndReached = true;
        r0.addAll(loadChatParticipantsRequests(0, 200, false));
        goto L32
    L23:
        r112.filter = new TLRPC.TL_channelParticipantsRecent();
        goto L32
    L24:
        if (r6 == true) goto L27;
        this.delayResults = 3;
        r112.filter = new TLRPC.TL_channelParticipantsContacts();
        this.contactsEndReached = true;
        r0.addAll(loadChatParticipantsRequests(0, 200, false));
        goto L32
    L27:
        if (this.botsEndReached == true) goto L29;
        r112.filter = new TLRPC.TL_channelParticipantsBots();
        this.botsEndReached = true;
        r0.addAll(loadChatParticipantsRequests(0, 200, false));
        goto L32
    L29:
        r112.filter = new TLRPC.TL_channelParticipantsRecent();
        goto L32
    L13:
        if (r12.participants_count > 200) goto L19;
        TLRPC.Chat r14 = this.currentChat;
        if (r14 == null) goto L19;
        if (r14.megagroup == false) goto L19;
        r112.filter = new TLRPC.TL_channelParticipantsRecent();
        goto L32
    L30:
        if (r1 != 3) goto L32;
        r112.filter = new TLRPC.TL_channelParticipantsBanned();
        goto L32
    }

    private void onOwnerChaged(TLRPC.User r15) {
        UndoView r0 = this.undoView;
        long r1 = -this.chatId;
        if (this.isChannel == false) goto L5;
        int r3 = 9;
    L6:
        r0.showWithAction(r1, r3, r15);
        this.currentChat.creator = false;
        int r02 = 0;
        boolean r2 = false;
    L8:
        if (r02 >= 3) goto L34;
        boolean r32 = true;
        if (r02 != 0) goto L12;
        LongSparseArray<TLObject> r4 = this.contactsMap;
        ArrayList<TLObject> r5 = this.contacts;
    L15:
        TLObject r6 = r4.get(r15.id);
        if ((r6 instanceof TLRPC.ChannelParticipant) == false) goto L21;
        TLRPC.TL_channelParticipantCreator r22 = new TLRPC.TL_channelParticipantCreator();
        TLRPC.TL_peerUser r7 = new TLRPC.TL_peerUser();
        r22.peer = r7;
        long r8 = r15.id;
        r7.user_id = r8;
        r4.put(r8, r22);
        int r62 = r5.indexOf(r6);
        if (r62 < 0) goto L20;
        r5.set(r62, r22);
    L20:
        r2 = true;
        boolean r63 = true;
    L22:
        long r72 = getUserConfig().getClientUserId();
        TLObject r9 = r4.get(r72);
        if ((r9 instanceof TLRPC.ChannelParticipant) == false) goto L30;
        TLRPC.TL_channelParticipantAdmin r64 = new TLRPC.TL_channelParticipantAdmin();
        TLRPC.TL_peerUser r10 = new TLRPC.TL_peerUser();
        r64.peer = r10;
        r10.user_id = r72;
        r64.self = true;
        r64.inviter_id = r72;
        r64.promoted_by = r72;
        r64.date = (int) (System.currentTimeMillis() / 1000);
        TLRPC.TL_chatAdminRights r102 = new TLRPC.TL_chatAdminRights();
        r64.admin_rights = r102;
        r102.add_admins = true;
        r102.manage_ranks = true;
        r102.pin_messages = true;
        r102.manage_topics = true;
        r102.invite_users = true;
        r102.ban_users = true;
        r102.delete_messages = true;
        r102.edit_messages = true;
        r102.post_messages = true;
        r102.change_info = true;
        if (this.isChannel == true) goto L27;
        r102.manage_call = true;
    L27:
        r4.put(r72, r64);
        int r42 = r5.indexOf(r9);
        if (r42 < 0) goto L31;
        r5.set(r42, r64);
    L31:
        if (r32 == false) goto L33;
        Collections.sort(r5, new ChatUsersActivity$$ExternalSyntheticLambda35());
    L33:
        r02 = r02 + 1;
        goto L8
    L30:
        r32 = r63;
        goto L31
    L21:
        r63 = false;
        goto L22
    L12:
        if (r02 != 1) goto L14;
        r4 = this.botsMap;
        r5 = this.bots;
        goto L15
    L14:
        r4 = this.participantsMap;
        r5 = this.participants;
        goto L15
    L34:
        if (r2 == true) goto L36;
        TLRPC.TL_channelParticipantCreator r03 = new TLRPC.TL_channelParticipantCreator();
        TLRPC.TL_peerUser r12 = new TLRPC.TL_peerUser();
        r03.peer = r12;
        long r23 = r15.id;
        r12.user_id = r23;
        this.participantsMap.put(r23, r03);
        this.participants.add(r03);
        sortAdmins(this.participants);
        updateRows();
    L36:
        this.listViewAdapter.notifyDataSetChanged();
        ChatUsersActivityDelegate r14 = this.delegate;
        if (r14 == null) goto L43;
        r14.didChangeOwner(r15);
        return;
    L43:
        return;
    L5:
        r3 = 10;
        goto L6
    }

    private void openRightsEdit(long r16, TLObject r18, TLRPC.TL_chatAdminRights r19, TLRPC.TL_chatBannedRights r20, String r21, boolean r22, int r23, boolean r24) {
        long r5 = this.chatId;
        TLRPC.TL_chatBannedRights r8 = this.defaultBannedRights;
        if (r18 != null) goto L6;
        boolean r0 = true;
    L7:
        ChatRightsEditActivity r2 = new ChatRightsEditActivity(r16, r5, r19, r8, r20, r21, r23, r22, r0, null);
        r2.setDelegate(new AnonymousClass15(this, r18, r16, r24));
        presentFragment(r2, r24);
        return;
    L6:
        r0 = false;
        goto L7
    }

    private void openRightsEdit2(long r19, int r21, TLObject r22, TLRPC.TL_chatAdminRights r23, TLRPC.TL_chatBannedRights r24, String r25, boolean r26, int r27, boolean r28) {
        boolean r2 = true;
        boolean[] r7 = new boolean[1];
        if ((r22 instanceof TLRPC.TL_channelParticipantAdmin) == false) goto L5;
    L8:
        AnonymousClass13 r0 = new AnonymousClass13(this, r19, this.chatId, r23, this.defaultBannedRights, r24, r25, r27, true, false, null, r7, r19);
        r0.setDelegate(new AnonymousClass14(this, r27, r19, r21, r2, r7));
        presentFragment(r0);
        return;
    L5:
        if ((r22 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L8;
        r2 = false;
        goto L8
    }

    private void processDone() {
        int r0 = this.type;
        boolean r2 = false;
        if (r0 != 3) goto L61;
        TLRPC.Chat r02 = this.currentChat;
        if (r02.creator == true) goto L7;
    L13:
        ChatUsersActivity r5 = this;
        if (ChatObject.getBannedRightsString(r5.defaultBannedRights).equals(r5.initialBannedRights) == true) goto L23;
        r5.getMessagesController().setDefaultBannedRole(r5.chatId, r5.defaultBannedRights, ChatObject.isChannel(r5.currentChat), r5);
        TLRPC.Chat r13 = r5.getMessagesController().getChat(Long.valueOf(r5.chatId));
        if (r13 == null) goto L23;
        r13.default_banned_rights = r5.defaultBannedRights;
    L23:
        int r132 = r5.selectedSlowmode;
        if (r132 == r5.initialSlowmode) goto L28;
        TLRPC.ChatFull r03 = r5.info;
        if (r03 == null) goto L28;
        r03.slowmode_seconds = r5.getSecondsForIndex(r132);
        r5.info.flags |= 131072;
        r5.getMessagesController().setChannelSlowMode(r5.chatId, r5.info.slowmode_seconds);
    L28:
        boolean r133 = r5.enablePrice;
        if (r133 != r5.initialEnablePrice) goto L33;
        if (r133 == false) goto L45;
        if (r5.initialStarsPrice != r5.starsPrice) goto L33;
    L45:
        if (r5.hasNotRestrictBoostersChanges() == true) goto L47;
    L78:
        r5.finishFragment();
        return;
    L47:
        if (r5.isEnabledNotRestrictBoosters == true) goto L49;
    L51:
        boolean r134 = false;
    L52:
        if (r134 == true) goto L54;
    L56:
        if (r134 == false) goto L58;
    L60:
        r5.getMessagesController().setBoostsToUnblockRestrictions(r5.chatId, r5.notRestrictBoosters);
        goto L78
    L58:
        if (r5.notRestrictBoosters == 0) goto L60;
        r5.getMessagesController().setBoostsToUnblockRestrictions(r5.chatId, 0);
        goto L78
    L54:
        if (r5.notRestrictBoosters != 0) goto L56;
        r5.getMessagesController().setBoostsToUnblockRestrictions(r5.chatId, 1);
        goto L78
    L49:
        if (r5.isNotRestrictBoostersVisible() == false) goto L51;
        r134 = true;
    L33:
        TL_stars.updatePaidMessagesPrice r135 = new TL_stars.updatePaidMessagesPrice();
        r135.channel = r5.getMessagesController().getInputChannel(r5.chatId);
        if (r5.enablePrice == false) goto L36;
        long r04 = r5.starsPrice;
    L37:
        r135.send_paid_messages_stars = r04;
        r5.getConnectionsManager().sendRequest(r135, new ChatUsersActivity$$ExternalSyntheticLambda21());
        TLRPC.Chat r136 = r5.getMessagesController().getChat(Long.valueOf(r5.chatId));
        if (r136 == null) goto L45;
        boolean r05 = r5.enablePrice;
        int r1 = r136.flags2;
        if (r05 == false) goto L42;
        r136.flags2 = r1 | 16384;
        r136.send_paid_messages_stars = r5.starsPrice;
    L43:
        r5.getMessagesController().putChat(r136, true);
        goto L45
    L42:
        r136.flags2 = r1 & (-16385);
        r136.send_paid_messages_stars = 0;
        goto L43
    L36:
        r04 = 0;
        goto L37
    L7:
        if (ChatObject.isChannel(r02) == true) goto L13;
        if (this.selectedSlowmode != this.initialSlowmode) goto L15;
        if (this.enablePrice == false) goto L13;
    L15:
        if (this.info == null) goto L13;
        MessagesController.getInstance(this.currentAccount).convertToMegaGroup(getParentActivity(), this.chatId, this, new ChatUsersActivity$$ExternalSyntheticLambda20(this));
        return;
    L61:
        r5 = this;
        if (r0 != 1) goto L78;
        boolean r137 = r5.signatures;
        if (r137 != r5.initialSignatures) goto L72;
        if (r137 == true) goto L67;
    L69:
        boolean r138 = false;
    L71:
        if (r138 == r5.initialProfiles) goto L78;
    L67:
        if (r5.profiles == false) goto L69;
        r138 = true;
    L72:
        MessagesController r139 = r5.getMessagesController();
        long r06 = r5.chatId;
        boolean r4 = r5.signatures;
        if (r4 == true) goto L75;
    L77:
        r139.toggleChannelSignatures(r06, r4, r2);
        goto L78
    L75:
        if (r5.profiles == false) goto L77;
        r2 = true;
        goto L77
    }

    private void removeParticipant(long r5) {
        if (ChatObject.isChannel(this.currentChat) == true) goto L5;
        return;
    L5:
        TLRPC.User r0 = getMessagesController().getUser(Long.valueOf(r5));
        getMessagesController().deleteParticipantFromChat(this.chatId, r0);
        ChatUsersActivityDelegate r02 = this.delegate;
        if (r02 == null) goto L8;
        r02.didKickParticipant(r5);
    L8:
        finishFragment();
    }

    private void removeParticipants(long r8) {
        DiffCallback r0 = saveState();
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= 3) goto L20;
        if (r1 != 0) goto L8;
        LongSparseArray<TLObject> r4 = this.contactsMap;
        ArrayList<TLObject> r5 = this.contacts;
    L11:
        TLObject r6 = r4.get(r8);
        if (r6 == null) goto L19;
        r4.remove(r8);
        r5.remove(r6);
        if (this.type != 0) goto L18;
        TLRPC.ChatFull r22 = this.info;
        if (r22 == null) goto L18;
        r22.kicked_count--;
    L18:
        r2 = true;
    L19:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r1 != 1) goto L10;
        r4 = this.botsMap;
        r5 = this.bots;
        goto L11
    L10:
        r4 = this.participantsMap;
        r5 = this.participants;
        goto L11
    L20:
        if (r2 == false) goto L22;
        updateListAnimated(r0);
    L22:
        RecyclerView.Adapter r02 = this.listView.getAdapter();
        SearchAdapter r7 = this.searchListViewAdapter;
        if (r02 != r7) goto L29;
        r7.removeUserId(r8);
        return;
    }

    private void setBannedRights(TLRPC.TL_chatBannedRights r1) {
        if (r1 == null) goto L5;
        this.defaultBannedRights = r1;
        return;
    }

    private void setSendMediaEnabled(boolean r3) {
        TLRPC.TL_chatBannedRights r0 = this.defaultBannedRights;
        r0.send_media = !r3;
        r0.send_gifs = !r3;
        r0.send_inline = !r3;
        r0.send_games = !r3;
        r0.send_photos = !r3;
        r0.send_videos = !r3;
        r0.send_stickers = !r3;
        r0.send_audios = !r3;
        r0.send_docs = !r3;
        r0.send_voices = !r3;
        r0.send_roundvideos = !r3;
        r0.embed_links = !r3;
        r0.send_polls = !r3;
        r0.send_reactions = !r3;
        AndroidUtilities.updateVisibleRows(this.listView);
        DiffCallback r32 = saveState();
        updateRows();
        updateListAnimated(r32);
    }

    private void showItemsAnimated(int r5) {
        if (this.isPaused == false) goto L5;
        return;
    L5:
        if (this.openTransitionStarted == true) goto L7;
        return;
    L7:
        if (this.listView.getAdapter() == this.listViewAdapter) goto L9;
    L11:
        View r0 = null;
        int r1 = 0;
    L13:
        if (r1 >= this.listView.getChildCount()) goto L18;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof FlickerLoadingView) == false) goto L17;
        r0 = r2;
    L17:
        r1 = r1 + 1;
        goto L13
    L18:
        if (r0 == null) goto L20;
        this.listView.removeView(r0);
        r5 = r5 - 1;
    L20:
        this.listView.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass12(this, r0, r5));
        return;
    L9:
        if (this.firstLoaded == false) goto L11;
    }

    public static void sortAdmins(ArrayList<TLObject> r1) {
        Collections.sort(r1, new ChatUsersActivity$$ExternalSyntheticLambda19());
    }

    private void sortUsers(ArrayList<TLObject> r3) {
        Collections.sort(r3, new ChatUsersActivity$$ExternalSyntheticLambda8(this, getConnectionsManager().getCurrentTime()));
    }

    private void updateParticipantWithRights(TLRPC.ChannelParticipant r7, TLRPC.TL_chatAdminRights r8, TLRPC.TL_chatBannedRights r9, long r10, boolean r12) {
        int r0 = 0;
        boolean r1 = false;
    L4:
        if (r0 >= 3) goto L23;
        if (r0 != 0) goto L8;
        LongSparseArray<TLObject> r3 = this.contactsMap;
    L11:
        TLObject r32 = r3.get(MessageObject.getPeerId(r7.peer));
        if ((r32 instanceof TLRPC.ChannelParticipant) == false) goto L16;
        r7 = (TLRPC.ChannelParticipant) r32;
        r7.admin_rights = r8;
        r7.banned_rights = r9;
        if (r12 == false) goto L16;
        r7.promoted_by = getUserConfig().getClientUserId();
    L16:
        if (r12 == false) goto L22;
        if (r32 == null) goto L22;
        if (r1 == true) goto L22;
        ChatUsersActivityDelegate r4 = this.delegate;
        if (r4 == null) goto L22;
        r4.didAddParticipantToList(r10, r32);
        r1 = true;
    L22:
        r0 = r0 + 1;
        goto L4
    L8:
        if (r0 != 1) goto L10;
        r3 = this.botsMap;
        goto L11
    L10:
        r3 = this.participantsMap;
        goto L11
    }

    private void updateRows() {
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        this.currentChat = r0;
        if (r0 == null) goto L275;
        this.recentActionsRow = -1;
        this.antiSpamRow = -1;
        this.antiSpamInfoRow = -1;
        this.addNewRow = -1;
        this.addNew2Row = -1;
        this.hideMembersRow = -1;
        this.hideMembersInfoRow = -1;
        this.tagsRow = -1;
        this.tagsInfoRow = -1;
        this.addNewSectionRow = -1;
        this.restricted1SectionRow = -1;
        this.participantsStartRow = -1;
        this.participantsDividerRow = -1;
        this.participantsDivider2Row = -1;
        this.gigaInfoRow = -1;
        this.gigaConvertRow = -1;
        this.gigaHeaderRow = -1;
        this.participantsEndRow = -1;
        this.participantsInfoRow = -1;
        this.signMessagesRow = -1;
        this.signMessagesProfilesRow = -1;
        this.signMessagesInfoRow = -1;
        this.blockedEmptyRow = -1;
        this.permissionsSectionRow = -1;
        this.sendMessagesRow = -1;
        this.sendMediaRow = -1;
        this.sendStickersRow = -1;
        this.sendPollsRow = -1;
        this.embedLinksRow = -1;
        this.addUsersRow = -1;
        this.manageLinkedPeersRow = -1;
        this.manageTopicsRow = -1;
        this.pinMessagesRow = -1;
        this.editTagRow = -1;
        this.sendReactionsRow = -1;
        this.changeInfoRow = -1;
        this.removedUsersRow = -1;
        this.contactsHeaderRow = -1;
        this.contactsStartRow = -1;
        this.contactsEndRow = -1;
        this.botHeaderRow = -1;
        this.botStartRow = -1;
        this.botEndRow = -1;
        this.membersHeaderRow = -1;
        this.slowmodeRow = -1;
        this.slowmodeSelectRow = -1;
        this.slowmodeInfoRow = -1;
        this.dontRestrictBoostersRow = -1;
        this.dontRestrictBoostersInfoRow = -1;
        this.dontRestrictBoostersSliderRow = -1;
        this.loadingProgressRow = -1;
        this.loadingUserCellRow = -1;
        this.loadingHeaderRow = -1;
        this.sendMediaPhotosRow = -1;
        this.sendMediaVideosRow = -1;
        this.sendMediaStickerGifsRow = -1;
        this.sendMediaMusicRow = -1;
        this.sendMediaFilesRow = -1;
        this.sendMediaVoiceMessagesRow = -1;
        this.sendMediaVideoMessagesRow = -1;
        this.sendMediaEmbededLinksRow = -1;
        this.payRow = -1;
        this.payInfoRow = -1;
        this.priceHeaderRow = -1;
        this.priceRow = -1;
        this.priceInfoRow = -1;
        int r1 = 0;
        this.rowCount = 0;
        int r2 = this.type;
        int r5 = 1;
        if (r2 != 3) goto L131;
        int r22 = this.rowCount;
        int r6 = r22 + 1;
        this.rowCount = r6;
        this.permissionsSectionRow = r22;
        boolean r7 = this.isCommunity;
        if (r7 == true) goto L13;
        this.sendMessagesRow = r6;
        int r62 = r22 + 3;
        this.rowCount = r62;
        this.sendMediaRow = r22 + 2;
        if (this.sendMediaExpanded == false) goto L12;
        this.sendMediaPhotosRow = r62;
        this.sendMediaVideosRow = r22 + 4;
        this.sendMediaStickerGifsRow = r22 + 5;
        this.sendMediaMusicRow = r22 + 6;
        this.sendMediaFilesRow = r22 + 7;
        this.sendMediaVoiceMessagesRow = r22 + 8;
        this.sendMediaVideoMessagesRow = r22 + 9;
        this.sendMediaEmbededLinksRow = r22 + 10;
        this.sendPollsRow = r22 + 11;
        this.rowCount = r22 + 13;
        this.sendReactionsRow = r22 + 12;
    L12:
        int r23 = this.rowCount;
        this.addUsersRow = r23;
        this.pinMessagesRow = r23 + 1;
        this.rowCount = r23 + 3;
        this.editTagRow = r23 + 2;
    L13:
        int r24 = this.rowCount;
        int r63 = r24 + 1;
        this.rowCount = r63;
        this.changeInfoRow = r24;
        if (r7 == false) goto L17;
        this.rowCount = r24 + 2;
        this.manageLinkedPeersRow = r63;
    L20:
        if (ChatObject.isChannel(this.currentChat) == false) goto L35;
        TLRPC.Chat r25 = this.currentChat;
        if (r25.creator == false) goto L35;
        if (r25.megagroup == false) goto L35;
        if (r25.gigagroup == true) goto L35;
        if (this.isCommunity == true) goto L35;
        int r26 = r25.participants_count;
        TLRPC.ChatFull r64 = this.info;
        if (r64 == null) goto L33;
        r1 = r64.participants_count;
    L33:
        if (Math.max(r26, r1) < (getMessagesController().maxMegagroupCount - 1000)) goto L35;
        int r12 = this.rowCount;
        this.participantsDivider2Row = r12;
        this.gigaHeaderRow = r12 + 1;
        this.gigaConvertRow = r12 + 2;
        this.rowCount = r12 + 4;
        this.gigaInfoRow = r12 + 3;
    L35:
        TLRPC.ChatFull r13 = this.info;
        if (r13 == null) goto L56;
        if (r13.paid_messages_available == false) goto L56;
        if (this.isCommunity == true) goto L56;
        if (ChatObject.canUserDoAction(this.currentChat, 2) == false) goto L56;
        if (ChatObject.isChannel(this.currentChat) == true) goto L50;
        TLRPC.Chat r14 = this.currentChat;
        if (r14 == null) goto L56;
        if (r14.creator == false) goto L56;
    L50:
        if (this.participantsDivider2Row != (-1)) goto L52;
        int r15 = this.rowCount;
        this.rowCount = r15 + 1;
        this.participantsDivider2Row = r15;
    L52:
        int r16 = this.rowCount;
        this.payRow = r16;
        int r65 = r16 + 2;
        this.rowCount = r65;
        this.payInfoRow = r16 + 1;
        if (this.enablePrice == false) goto L56;
        this.priceHeaderRow = r65;
        this.priceRow = r16 + 3;
        this.rowCount = r16 + 5;
        this.priceInfoRow = r16 + 4;
    L56:
        if (this.isCommunity == true) goto L74;
        if (ChatObject.isChannel(this.currentChat) == false) goto L60;
    L61:
        TLRPC.Chat r17 = this.currentChat;
        if (r17.megagroup == false) goto L68;
        if (r17.gigagroup == true) goto L68;
        if (ChatObject.canBlockUsers(r17) == false) goto L68;
    L70:
        if (this.participantsDivider2Row != (-1)) goto L72;
        int r18 = this.rowCount;
        this.rowCount = r18 + 1;
        this.participantsDivider2Row = r18;
    L72:
        int r19 = this.rowCount;
        this.slowmodeRow = r19;
        this.slowmodeSelectRow = r19 + 1;
        this.rowCount = r19 + 3;
        this.slowmodeInfoRow = r19 + 2;
    L68:
        if (ChatObject.hasAdminRights(this.currentChat) == true) goto L74;
    L60:
        if (this.currentChat.creator == true) goto L70;
    L74:
        if (isNotRestrictBoostersVisible() == false) goto L85;
        if (this.isCommunity == true) goto L85;
        if (this.participantsDivider2Row != (-1)) goto L80;
        int r110 = this.rowCount;
        this.rowCount = r110 + 1;
        this.participantsDivider2Row = r110;
    L80:
        int r111 = this.rowCount;
        int r27 = r111 + 1;
        this.rowCount = r27;
        this.dontRestrictBoostersRow = r111;
        if (this.isEnabledNotRestrictBoosters == false) goto L83;
        this.rowCount = r111 + 2;
        this.dontRestrictBoostersSliderRow = r27;
    L83:
        int r112 = this.rowCount;
        this.rowCount = r112 + 1;
        this.dontRestrictBoostersInfoRow = r112;
    L85:
        if (ChatObject.isChannel(this.currentChat) == false) goto L95;
        if (ChatObject.hasAdminRights(this.currentChat) == false) goto L95;
        if (this.isCommunity == true) goto L95;
        if (this.participantsDivider2Row != (-1)) goto L93;
        int r113 = this.rowCount;
        this.rowCount = r113 + 1;
        this.participantsDivider2Row = r113;
    L93:
        int r114 = this.rowCount;
        this.rowCount = r114 + 1;
        this.removedUsersRow = r114;
    L95:
        if (this.slowmodeInfoRow != (-1)) goto L99;
        if (this.gigaHeaderRow != (-1)) goto L99;
    L100:
        int r115 = this.rowCount;
        this.rowCount = r115 + 1;
        this.participantsDividerRow = r115;
    L102:
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L113;
        if (this.isCommunity == true) goto L113;
        if (getParticipantsCount() <= 1) goto L113;
        if (ChatObject.isChannel(this.currentChat) == false) goto L110;
    L111:
        int r116 = this.rowCount;
        this.rowCount = r116 + 1;
        this.addNewRow = r116;
        goto L113
    L110:
        if (this.currentChat.creator == true) goto L111;
    L113:
        if (this.loadingUsers == false) goto L123;
        if (this.firstLoaded == true) goto L123;
        TLRPC.ChatFull r02 = this.info;
        if (r02 != null) goto L119;
        return;
    L119:
        if (r02.banned_count <= 0) goto L265;
        int r03 = this.rowCount;
        this.rowCount = r03 + 1;
        this.loadingUserCellRow = r03;
        return;
    L265:
        return;
    L123:
        if (this.participants.isEmpty() == true) goto L126;
        int r117 = this.rowCount;
        this.participantsStartRow = r117;
        int r118 = r117 + this.participants.size();
        this.rowCount = r118;
        this.participantsEndRow = r118;
    L126:
        if (this.addNewRow == (-1)) goto L128;
    L129:
        int r04 = this.rowCount;
        this.rowCount = r04 + 1;
        this.addNewSectionRow = r04;
        return;
    L128:
        if (this.participantsStartRow != (-1)) goto L129;
        return;
    L99:
        if (this.removedUsersRow == (-1)) goto L102;
    L17:
        if (this.isForum == false) goto L20;
        this.rowCount = r24 + 2;
        this.manageTopicsRow = r63;
        goto L20
    L131:
        if (r2 == 0) goto L133;
        if (r2 == 1) goto L168;
        if (r2 == 2) goto L219;
        return;
    L219:
        if (ChatObject.isChannel(this.currentChat) == false) goto L228;
        if (ChatObject.hasAdminRights(this.currentChat) == false) goto L228;
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == true) goto L228;
        if (this.needOpenSearch == true) goto L228;
        int r05 = this.rowCount;
        this.hideMembersRow = r05;
        this.rowCount = r05 + 2;
        this.hideMembersInfoRow = r05 + 1;
    L228:
        if (this.selectType != 0) goto L233;
        if (ChatObject.canAddUsers(this.currentChat) == false) goto L233;
        int r06 = this.rowCount;
        this.rowCount = r06 + 1;
        this.addNewRow = r06;
    L233:
        if (this.selectType != 0) goto L238;
        if (ChatObject.canUserDoAdminAction(this.currentChat, 3) == false) goto L238;
        int r07 = this.rowCount;
        this.rowCount = r07 + 1;
        this.addNew2Row = r07;
    L238:
        if (this.loadingUsers == false) goto L249;
        boolean r08 = this.firstLoaded;
        if (r08 == true) goto L249;
        if (r08 == false) goto L244;
        return;
    L244:
        if (this.selectType != 0) goto L246;
        int r09 = this.rowCount;
        this.rowCount = r09 + 1;
        this.loadingHeaderRow = r09;
    L246:
        int r010 = this.rowCount;
        this.rowCount = r010 + 1;
        this.loadingUserCellRow = r010;
        return;
    L249:
        if (this.contacts.isEmpty() == true) goto L252;
        int r011 = this.rowCount;
        int r119 = r011 + 1;
        this.rowCount = r119;
        this.contactsHeaderRow = r011;
        this.contactsStartRow = r119;
        int r120 = r119 + this.contacts.size();
        this.rowCount = r120;
        this.contactsEndRow = r120;
        r1 = 1;
    L252:
        if (this.bots.isEmpty() == true) goto L254;
        int r012 = this.rowCount;
        int r121 = r012 + 1;
        this.rowCount = r121;
        this.botHeaderRow = r012;
        this.botStartRow = r121;
        int r122 = r121 + this.bots.size();
        this.rowCount = r122;
        this.botEndRow = r122;
    L256:
        if (this.participants.isEmpty() == true) goto L260;
        if (r5 == 0) goto L259;
        int r013 = this.rowCount;
        this.rowCount = r013 + 1;
        this.membersHeaderRow = r013;
    L259:
        int r014 = this.rowCount;
        this.participantsStartRow = r014;
        int r015 = r014 + this.participants.size();
        this.rowCount = r015;
        this.participantsEndRow = r015;
    L260:
        int r016 = this.rowCount;
        if (r016 == 0) goto L274;
        this.rowCount = r016 + 1;
        this.participantsInfoRow = r016;
        return;
    L274:
        return;
    L254:
        r5 = r1;
        goto L256
    L168:
        if (this.transfer == true) goto L188;
        if (ChatObject.isChannel(this.currentChat) == false) goto L188;
        TLRPC.Chat r017 = this.currentChat;
        if (r017.megagroup == false) goto L188;
        if (r017.gigagroup == true) goto L188;
        TLRPC.ChatFull r123 = this.info;
        if (r123 != null) goto L178;
    L183:
        boolean r018 = ChatObject.hasAdminRights(r017);
        int r124 = this.rowCount;
        if (r018 == false) goto L186;
        this.antiSpamRow = r124;
        this.rowCount = r124 + 2;
        this.antiSpamInfoRow = r124 + 1;
        goto L188
    L186:
        this.rowCount = r124 + 1;
        this.addNewSectionRow = r124;
        goto L188
    L178:
        if (r123.participants_count <= 200) goto L183;
        if (this.isChannel == true) goto L188;
        if (r123.can_set_stickers == true) goto L183;
    L188:
        if (ChatObject.canAddAdmins(this.currentChat) == false) goto L191;
        int r019 = this.rowCount;
        this.rowCount = r019 + 1;
        this.addNewRow = r019;
    L191:
        if (this.loadingUsers == false) goto L198;
        boolean r020 = this.firstLoaded;
        if (r020 == true) goto L198;
        if (r020 == true) goto L204;
        int r021 = this.rowCount;
        this.rowCount = r021 + 1;
        this.loadingUserCellRow = r021;
    L204:
        if (this.transfer == false) goto L206;
        return;
    L206:
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == true) goto L208;
        return;
    L208:
        if (ChatObject.hasAdminRights(this.currentChat) == true) goto L210;
        return;
    L210:
        if (this.isCommunity == true) goto L271;
        int r022 = this.rowCount;
        int r125 = r022 + 1;
        this.rowCount = r125;
        this.signMessagesRow = r022;
        if (this.signatures == false) goto L215;
        this.signMessagesProfilesRow = r125;
        this.rowCount = r022 + 3;
        this.signMessagesInfoRow = r022 + 2;
        return;
    L215:
        this.rowCount = r022 + 2;
        this.signMessagesInfoRow = r125;
        return;
    L271:
        return;
    L198:
        if (this.participants.isEmpty() == true) goto L201;
        int r023 = this.rowCount;
        this.participantsStartRow = r023;
        int r024 = r023 + this.participants.size();
        this.rowCount = r024;
        this.participantsEndRow = r024;
    L201:
        if (this.isCommunity == true) goto L204;
        int r025 = this.rowCount;
        this.rowCount = r025 + 1;
        this.participantsInfoRow = r025;
        goto L204
    L133:
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L146;
        int r126 = this.rowCount;
        this.rowCount = r126 + 1;
        this.addNewRow = r126;
        if (this.participants.isEmpty() == true) goto L137;
    L144:
        int r127 = this.rowCount;
        this.rowCount = r127 + 1;
        this.participantsInfoRow = r127;
        goto L146
    L137:
        if (this.loadingUsers == false) goto L146;
        if (this.firstLoaded == true) goto L146;
        TLRPC.ChatFull r128 = this.info;
        if (r128 == null) goto L146;
        if (r128.kicked_count > 0) goto L144;
    L146:
        if (this.loadingUsers == false) goto L154;
        boolean r129 = this.firstLoaded;
        if (r129 == true) goto L154;
        if (r129 == true) goto L267;
        int r026 = this.rowCount;
        this.restricted1SectionRow = r026;
        this.rowCount = r026 + 2;
        this.loadingUserCellRow = r026 + 1;
        return;
    L267:
        return;
    L154:
        if (this.participants.isEmpty() == true) goto L157;
        int r130 = this.rowCount;
        int r28 = r130 + 1;
        this.rowCount = r28;
        this.restricted1SectionRow = r130;
        this.participantsStartRow = r28;
        int r29 = r28 + this.participants.size();
        this.rowCount = r29;
        this.participantsEndRow = r29;
    L157:
        if (this.participantsStartRow == (-1)) goto L164;
        int r131 = this.participantsInfoRow;
        int r210 = this.rowCount;
        if (r131 != (-1)) goto L162;
        this.rowCount = r210 + 1;
        this.participantsInfoRow = r210;
        return;
    L162:
        this.rowCount = r210 + 1;
        this.addNewSectionRow = r210;
        return;
    L164:
        int r027 = this.rowCount;
        this.rowCount = r027 + 1;
        this.blockedEmptyRow = r027;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        return checkDiscard(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r18) {
        this.searching = false;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        int r4 = 1;
        this.actionBar.setAllowOverlayTitle(true);
        int r3 = this.type;
        if (r3 != 3) goto L5;
        this.actionBar.setTitle(LocaleController.getString("ChannelPermissions", R.string.ChannelPermissions));
    L22:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        if (this.selectType == 0) goto L25;
    L39:
        this.searchListViewAdapter = new SearchAdapter(this, r18);
        ActionBarMenu r32 = this.actionBar.createMenu();
        ActionBarMenuItem r10 = r32.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r10;
        if (this.type == 0) goto L42;
    L44:
        int r102 = this.type;
        ActionBarMenuItem r11 = this.searchItem;
        if (r102 != 3) goto L47;
        r11.setSearchFieldHint(LocaleController.getString("ChannelSearchException", R.string.ChannelSearchException));
    L49:
        if (ChatObject.isChannel(this.currentChat) == true) goto L54;
        if (this.currentChat.creator == true) goto L54;
        this.searchItem.setVisibility(8);
    L54:
        if (this.type != 3) goto L56;
        this.doneItem = r32.addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f), LocaleController.getString("Done", R.string.Done));
    L56:
        FrameLayout r33 = new FrameLayout(r18);
        this.fragmentView = r33;
        r33.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        FrameLayout r34 = (FrameLayout) this.fragmentView;
        FrameLayout r6 = new FrameLayout(r18);
        FlickerLoadingView r7 = new FlickerLoadingView(r18);
        this.flickerLoadingView = r7;
        r7.setViewType(6);
        this.flickerLoadingView.showDate(false);
        this.flickerLoadingView.setUseHeaderOffset(false);
        FlickerLoadingView r72 = this.flickerLoadingView;
        int r8 = Theme.key_actionBarDefaultSubmenuBackground;
        int r103 = Theme.key_listSelector;
        r72.setColors(r8, r103, r103);
        r6.addView(this.flickerLoadingView, LayoutHelper.createFrame(-1, -1.0f, 0, 12.0f, 30.0f, 12.0f, 0.0f));
        RadialProgressView r73 = new RadialProgressView(r18);
        this.progressBar = r73;
        r6.addView(r73, LayoutHelper.createFrame(-2, -2, 17));
        this.flickerLoadingView.setVisibility(8);
        this.progressBar.setVisibility(8);
        StickerEmptyView r74 = new StickerEmptyView(r18, r6, 1);
        this.emptyView = r74;
        r74.title.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        this.emptyView.setVisibility(8);
        this.emptyView.setAnimateLayoutChange(true);
        this.emptyView.showProgress(true, false);
        r34.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.emptyView.addView(r6, 0);
        AnonymousClass3 r62 = new AnonymousClass3(this, r18);
        this.listView = r62;
        r62.setSections();
        RecyclerListView r63 = this.listView;
        AnonymousClass4 r75 = new AnonymousClass4(this, r18, 1, false);
        this.layoutManager = r75;
        r63.setLayoutManager(r75);
        AnonymousClass5 r64 = new AnonymousClass5(this);
        r64.setDurations(420);
        r64.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r64.setDelayAnimations(false);
        r64.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r64);
        this.listView.setAnimateEmptyView(true, 0);
        RecyclerListView r65 = this.listView;
        ListAdapter r76 = new ListAdapter(this, r18);
        this.listViewAdapter = r76;
        r65.setAdapter(r76);
        RecyclerListView r66 = this.listView;
        if (LocaleController.isRTL == true) goto L60;
        r4 = 2;
    L60:
        r66.setVerticalScrollbarPosition(r4);
        r34.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setOnItemClickListener(new ChatUsersActivity$$ExternalSyntheticLambda6(this));
        this.listView.setOnItemLongClickListener(new ChatUsersActivity$$ExternalSyntheticLambda7(this));
        if (this.searchItem == null) goto L63;
        this.listView.setOnScrollListener(new AnonymousClass11(this));
    L63:
        UndoView r42 = new UndoView(r18);
        this.undoView = r42;
        r34.addView(r42, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        updateRows();
        this.listView.setEmptyView(this.emptyView);
        this.listView.setAnimateEmptyView(false, 0);
        if (this.needOpenSearch == false) goto L67;
        this.searchItem.openSearch(false);
    L67:
        return this.fragmentView;
    L47:
        r11.setSearchFieldHint(LocaleController.getString("Search", R.string.Search));
        goto L49
    L42:
        if (this.firstLoaded == true) goto L44;
        r10.setVisibility(8);
        goto L44
    L25:
        if (this.type == 2) goto L39;
        if (ChatObject.hasAdminRights(this.currentChat) == false) goto L33;
        int r35 = this.type;
        if (r35 == 0) goto L39;
        if (r35 == 3) goto L39;
    L33:
        if (this.type != 1) goto L56;
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == false) goto L56;
        if (ChatObject.hasAdminRights(this.currentChat) == false) goto L56;
        this.doneItem = this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f), LocaleController.getString("Done", R.string.Done));
        goto L56
    L5:
        if (r3 != 0) goto L7;
        this.actionBar.setTitle(LocaleController.getString("ChannelBlacklist", R.string.ChannelBlacklist));
        goto L22
    L7:
        if (r3 != 1) goto L9;
        this.actionBar.setTitle(LocaleController.getString(R.string.ChannelAdministrators));
        goto L22
    L9:
        if (r3 != 2) goto L22;
        int r36 = this.selectType;
        if (r36 != 0) goto L16;
        boolean r37 = this.isChannel;
        ActionBar r77 = this.actionBar;
        if (r37 == false) goto L15;
        r77.setTitle(LocaleController.getString(R.string.ChannelSubscribers));
        goto L22
    L15:
        r77.setTitle(LocaleController.getString("ChannelMembers", R.string.ChannelMembers));
        goto L22
    L16:
        if (r36 != 1) goto L18;
        this.actionBar.setTitle(LocaleController.getString("ChannelAddAdmin", R.string.ChannelAddAdmin));
        goto L22
    L18:
        if (r36 != 2) goto L20;
        this.actionBar.setTitle(LocaleController.getString("ChannelBlockUser", R.string.ChannelBlockUser));
        goto L22
    L20:
        if (r36 != 3) goto L22;
        this.actionBar.setTitle(LocaleController.getString("ChannelAddException", R.string.ChannelAddException));
        goto L22
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        boolean r0 = false;
        if (r7 != NotificationCenter.chatInfoDidLoad) goto L34;
        TLRPC.ChatFull r72 = (TLRPC.ChatFull) r9[0];
        boolean r82 = ((Boolean) r9[2]).booleanValue();
        if (r72.id != this.chatId) goto L45;
        if (r82 == false) goto L10;
        if (ChatObject.isChannel(this.currentChat) == false) goto L10;
        return;
    L10:
        if (this.info == null) goto L12;
        boolean r83 = true;
    L13:
        this.info = r72;
        if (r83 == true) goto L31;
        int r73 = getCurrentSlowmode();
        this.initialSlowmode = r73;
        this.selectedSlowmode = r73;
        int r74 = this.info.boosts_unrestrict;
        if (r74 <= 0) goto L18;
        boolean r84 = true;
    L19:
        this.isEnabledNotRestrictBoosters = r84;
        this.notRestrictBoosters = r74;
        TLRPC.Chat r75 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (r75 != null) goto L22;
        long r76 = 0;
    L24:
        if (r76 <= 0) goto L26;
        r0 = true;
    L26:
        this.enablePrice = r0;
        this.initialEnablePrice = r0;
        if (r76 > 0) goto L30;
        r76 = 10;
    L30:
        long r77 = Utilities.clamp(r76, getMessagesController().starsPaidMessageAmountMax, 1);
        this.starsPrice = r77;
        this.initialStarsPrice = r77;
        goto L31
    L22:
        r76 = r75.send_paid_messages_stars;
        goto L24
    L18:
        r84 = false;
    L31:
        AndroidUtilities.runOnUIThread(new ChatUsersActivity$$ExternalSyntheticLambda0(this));
        return;
    L12:
        r83 = false;
        goto L13
    L45:
        return;
    L34:
        if (r7 == NotificationCenter.dialogDeleted) goto L36;
        return;
    L36:
        if (((Long) r9[0]).longValue() != (-this.chatId)) goto L48;
        INavigationLayout r78 = this.parentLayout;
        if (r78 != null) goto L40;
    L43:
        removeSelfFromStack();
        return;
    L40:
        if (r78.getLastFragment() != this) goto L43;
        finishFragment();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ChatUsersActivity$$ExternalSyntheticLambda1 r8 = new ChatUsersActivity$$ExternalSyntheticLambda1(this);
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, ManageChatUserCell.class, ManageChatTextCell.class, TextCheckCell2.class, TextSettingsCell.class, SlideChooseView.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        int r16 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        int r29 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r29));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"textView"}, null, null, null, r29));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switch2Track));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCheckCell2.class}, new String[]{"checkBox"}, null, null, null, Theme.key_switch2TrackChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"nameTextView"}, null, null, null, r29));
        int r20 = Theme.key_windowBackgroundWhiteGrayText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusColor"}, null, null, r8, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_undo_background));
        int r38 = Theme.key_undo_cancelColor;
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoImageView"}, null, null, null, r38));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoTextView"}, null, null, null, r38));
        int r43 = Theme.key_undo_infoColor;
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"infoTextView"}, null, null, null, r43));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"textPaint"}, null, null, null, r43));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"progressPaint"}, null, null, null, r43));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{UndoView.class}, new String[]{"leftImageView"}, null, null, null, r43));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, r29));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueButton));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerEmptyView.class}, new String[]{"title"}, null, null, null, r29));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{StickerEmptyView.class}, new String[]{MediaTrack.ROLE_SUBTITLE}, null, null, null, r29));
        r1.add(new ThemeDescription(this.emptyView.title, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r29));
        r1.add(new ThemeDescription(this.emptyView.subtitle, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        return r1;
    }

    public boolean hasSelectType() {
        if (this.selectType == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean needDelayOpenAnimation() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r1) {
        return checkDiscard(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        UndoView r2 = this.undoView;
        if (r2 == null) goto L6;
        r2.hide(true, 0);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        loadChatParticipants(0, 200);
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, 0, 0, r4);
        this.listView.setClipToPadding(false);
        this.undoView.setTranslationY(-r4);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        UndoView r2 = this.undoView;
        if (r2 == null) goto L6;
        r2.hide(true, 0);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        ListAdapter r0 = this.listViewAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        StickerEmptyView r2 = this.emptyView;
        if (r2 == null) goto L9;
        r2.requestLayout();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r2, boolean r3) {
        if (r2 == false) goto L4;
        this.openTransitionStarted = true;
    L4:
        if (r2 == false) goto L10;
        if (r3 == false) goto L7;
        return;
    L7:
        if (this.needOpenSearch == false) goto L12;
        this.searchItem.getSearchField().requestFocus();
        AndroidUtilities.showKeyboard(this.searchItem.getSearchField());
        this.searchItem.setVisibility(8);
        return;
    L12:
        return;
    }

    public DiffCallback saveState() {
        DiffCallback r0 = new DiffCallback(this, null);
        r0.oldRowCount = this.rowCount;
        r0.oldBotStartRow = this.botStartRow;
        r0.oldBotEndRow = this.botEndRow;
        DiffCallback.m8642$$Nest$fgetoldBots(r0).clear();
        DiffCallback.m8642$$Nest$fgetoldBots(r0).addAll(this.bots);
        r0.oldContactsEndRow = this.contactsEndRow;
        r0.oldContactsStartRow = this.contactsStartRow;
        DiffCallback.m8643$$Nest$fgetoldContacts(r0).clear();
        DiffCallback.m8643$$Nest$fgetoldContacts(r0).addAll(this.contacts);
        r0.oldParticipantsStartRow = this.participantsStartRow;
        r0.oldParticipantsEndRow = this.participantsEndRow;
        DiffCallback.m8644$$Nest$fgetoldParticipants(r0).clear();
        DiffCallback.m8644$$Nest$fgetoldParticipants(r0).addAll(this.participants);
        r0.fillPositions(r0.oldPositionToItem);
        return r0;
    }

    public void setDelegate(ChatUsersActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setInfo(TLRPC.ChatFull r13) {
        this.info = r13;
        if (r13 == null) goto L21;
        int r132 = getCurrentSlowmode();
        this.initialSlowmode = r132;
        this.selectedSlowmode = r132;
        int r133 = this.info.boosts_unrestrict;
        boolean r0 = false;
        if (r133 <= 0) goto L7;
        boolean r2 = true;
    L8:
        this.isEnabledNotRestrictBoosters = r2;
        this.notRestrictBoosters = r133;
        TLRPC.Chat r134 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (r134 != null) goto L11;
        long r4 = 0;
    L13:
        if (r4 <= 0) goto L15;
        r0 = true;
    L15:
        this.enablePrice = r0;
        this.initialEnablePrice = r0;
        if (r4 > 0) goto L19;
        r4 = 10;
    L19:
        long r02 = Utilities.clamp(r4, getMessagesController().starsPaidMessageAmountMax, 1);
        this.starsPrice = r02;
        this.initialStarsPrice = r02;
        return;
    L11:
        r4 = r134.send_paid_messages_stars;
        goto L13
    L7:
        r2 = false;
        goto L8
    }

    public void updateListAnimated(DiffCallback r4) {
        if (this.listViewAdapter != null) goto L6;
        updateRows();
        return;
    L6:
        updateRows();
        r4.fillPositions(r4.newPositionToItem);
        DiffUtil.calculateDiff(r4).dispatchUpdatesTo(this.listViewAdapter);
        RecyclerListView r42 = this.listView;
        if (r42 != null) goto L9;
        return;
    L9:
        if (this.layoutManager != null) goto L11;
        return;
    L11:
        if (r42.getChildCount() <= 0) goto L27;
        int r0 = 0;
        int r1 = -1;
    L14:
        if (r0 >= this.listView.getChildCount()) goto L19;
        RecyclerListView r12 = this.listView;
        r1 = r12.getChildAdapterPosition(r12.getChildAt(r0));
        if (r1 != (-1)) goto L17;
        r0 = r0 + 1;
        goto L14
    L17:
        View r43 = this.listView.getChildAt(r0);
    L20:
        if (r43 == null) goto L28;
        this.layoutManager.scrollToPositionWithOffset(r1, r43.getTop() - this.listView.getPaddingTop());
        return;
    L28:
        return;
    L19:
        r43 = null;
        goto L20
    }
}
