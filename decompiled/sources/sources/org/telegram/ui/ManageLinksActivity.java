package org.telegram.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.timepicker.TimeModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.CreationTextCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.DotDividerSpan;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.InviteLinkBottomSheet;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkActionView;
import org.telegram.ui.Components.RecyclerItemsEnterAnimator;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.TimerParticles;
import org.telegram.ui.LinkEditActivity;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class ManageLinksActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private long adminId;
    private ArrayList<TLRPC.TL_chatAdminWithInvites> admins;
    private int adminsDividerRow;
    private int adminsEndRow;
    private int adminsHeaderRow;
    boolean adminsLoaded;
    private int adminsStartRow;
    private boolean canEdit;
    private int createLinkHelpRow;
    private int createNewLinkRow;
    private int creatorDividerRow;
    private int creatorRow;
    private TLRPC.Chat currentChat;
    private long currentChatId;
    boolean deletingRevokedLinks;
    private int dividerRow;
    boolean hasMore;
    private int helpRow;
    private TLRPC.ChatFull info;
    private TLRPC.TL_chatInviteExported invite;
    private InviteLinkBottomSheet inviteLinkBottomSheet;
    private ArrayList<TLRPC.TL_chatInviteExported> invites;
    private int invitesCount;
    private boolean isChannel;
    private boolean isOpened;
    private boolean isPublic;
    private int lastDivider;
    private final LinkEditActivity.Callback linkEditActivityCallback;
    Drawable linkIcon;
    Drawable linkIconRevenue;
    Drawable linkIconRevoked;
    private int linksEndRow;
    private int linksHeaderRow;
    private int linksInfoRow;
    boolean linksLoading;
    private int linksLoadingRow;
    private int linksStartRow;
    private RecyclerListView listView;
    private ListAdapter listViewAdapter;
    boolean loadAdmins;
    boolean loadRevoked;
    AnimationNotificationsLocker notificationsLocker;
    private int permanentLinkHeaderRow;
    private int permanentLinkRow;
    private RecyclerItemsEnterAnimator recyclerItemsEnterAnimator;
    private int revokeAllDivider;
    private int revokeAllRow;
    private int revokedDivider;
    private int revokedHeader;
    private ArrayList<TLRPC.TL_chatInviteExported> revokedInvites;
    private int revokedLinksEndRow;
    private int revokedLinksStartRow;
    private int rowCount;
    long timeDif;
    Runnable updateTimerRunnable;
    private HashMap<Long, TLRPC.User> users;

    /* renamed from: org.telegram.ui.ManageLinksActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ManageLinksActivity this$0;

        public AnonymousClass1(ManageLinksActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ManageLinksActivity.m16084$$Nest$fgetlistView(this.this$0) != null) goto L5;
            return;
        L5:
            int r0 = 0;
        L7:
            if (r0 >= ManageLinksActivity.m16084$$Nest$fgetlistView(this.this$0).getChildCount()) goto L14;
            View r1 = ManageLinksActivity.m16084$$Nest$fgetlistView(this.this$0).getChildAt(r0);
            if ((r1 instanceof LinkCell) == false) goto L13;
            LinkCell r12 = (LinkCell) r1;
            if (r12.timerRunning == false) goto L13;
            r12.setLink(r12.invite, r12.position);
        L13:
            r0 = r0 + 1;
            goto L7
        L14:
            AndroidUtilities.runOnUIThread(this, 500);
        }
    }

    /* renamed from: org.telegram.ui.ManageLinksActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ManageLinksActivity this$0;

        public AnonymousClass2(ManageLinksActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ManageLinksActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ ManageLinksActivity this$0;

        public AnonymousClass3(ManageLinksActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            AndroidUtilities.runOnUIThread(this.this$0.updateTimerRunnable, 500);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AndroidUtilities.cancelRunOnUIThread(this.this$0.updateTimerRunnable);
        }
    }

    /* renamed from: org.telegram.ui.ManageLinksActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends LinearLayoutManager {
        final /* synthetic */ ManageLinksActivity this$0;

        public AnonymousClass4(ManageLinksActivity r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.ManageLinksActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ManageLinksActivity this$0;
        final /* synthetic */ LinearLayoutManager val$layoutManager;

        public AnonymousClass5(ManageLinksActivity r1, LinearLayoutManager r2) {
            this.this$0 = r1;
            this.val$layoutManager = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            ManageLinksActivity r12 = this.this$0;
            if (r12.hasMore == true) goto L5;
            return;
        L5:
            if (r12.linksLoading == true) goto L11;
            int r13 = this.val$layoutManager.findLastVisibleItemPosition();
            if ((ManageLinksActivity.m16094$$Nest$fgetrowCount(this.this$0) - r13) >= 10) goto L12;
            ManageLinksActivity.m16097$$Nest$mloadLinks(this.this$0, true);
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ManageLinksActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements LinkEditActivity.Callback {
        final /* synthetic */ ManageLinksActivity this$0;

        public static /* synthetic */ void $r8$lambda$cIgK3t877AUU7f3f4_i6mkzWv84(AnonymousClass6 r0, TLObject r1) {
            r0.lambda$onLinkCreated$0(r1);
        }

        public AnonymousClass6(ManageLinksActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onLinkCreated$0(TLObject r5) {
            DiffCallback r0 = ManageLinksActivity.m16099$$Nest$msaveListState(this.this$0);
            ManageLinksActivity.m16074$$Nest$fgetinvites(this.this$0).add(0, (TLRPC.TL_chatInviteExported) r5);
            if (ManageLinksActivity.m16071$$Nest$fgetinfo(this.this$0) == null) goto L5;
            ManageLinksActivity.m16071$$Nest$fgetinfo(this.this$0).invitesCount++;
            this.this$0.getMessagesStorage().saveChatLinksCount(ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(this.this$0), ManageLinksActivity.m16071$$Nest$fgetinfo(this.this$0).invitesCount);
        L5:
            ManageLinksActivity.m16100$$Nest$mupdateRecyclerViewAnimated(this.this$0, r0);
        }

        @Override // org.telegram.ui.LinkEditActivity.Callback
        public void onLinkCreated(TLObject r2) {
            if ((r2 instanceof TLRPC.TL_chatInviteExported) == false) goto L6;
            AndroidUtilities.runOnUIThread(new ManageLinksActivity$6$$ExternalSyntheticLambda0(this, r2), 200);
            return;
        }

        @Override // org.telegram.ui.LinkEditActivity.Callback
        public void onLinkEdited(TLRPC.TL_chatInviteExported r5, TLObject r6) {
            if ((r6 instanceof TLRPC.TL_messages_exportedChatInvite) == false) goto L16;
            TLRPC.TL_chatInviteExported r62 = (TLRPC.TL_chatInviteExported) ((TLRPC.TL_messages_exportedChatInvite) r6).invite;
            this.this$0.fixDate(r62);
            int r1 = 0;
        L6:
            if (r1 >= ManageLinksActivity.m16074$$Nest$fgetinvites(this.this$0).size()) goto L19;
            if (((TLRPC.TL_chatInviteExported) ManageLinksActivity.m16074$$Nest$fgetinvites(this.this$0).get(r1)).link.equals(r5.link) == true) goto L9;
            r1 = r1 + 1;
            goto L6
        L9:
            boolean r52 = r62.revoked;
            ManageLinksActivity r2 = this.this$0;
            if (r52 == false) goto L13;
            DiffCallback r53 = ManageLinksActivity.m16099$$Nest$msaveListState(r2);
            ManageLinksActivity.m16074$$Nest$fgetinvites(this.this$0).remove(r1);
            ManageLinksActivity.m16091$$Nest$fgetrevokedInvites(this.this$0).add(0, r62);
            ManageLinksActivity.m16100$$Nest$mupdateRecyclerViewAnimated(this.this$0, r53);
            return;
        L13:
            ManageLinksActivity.m16074$$Nest$fgetinvites(r2).set(r1, r62);
            ManageLinksActivity.m16101$$Nest$mupdateRows(this.this$0, true);
            return;
        L19:
            return;
        }

        @Override // org.telegram.ui.LinkEditActivity.Callback
        public void onLinkRemoved(TLRPC.TL_chatInviteExported r4) {
            int r0 = 0;
        L4:
            if (r0 >= ManageLinksActivity.m16091$$Nest$fgetrevokedInvites(this.this$0).size()) goto L10;
            if (((TLRPC.TL_chatInviteExported) ManageLinksActivity.m16091$$Nest$fgetrevokedInvites(this.this$0).get(r0)).link.equals(r4.link) == true) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            DiffCallback r42 = ManageLinksActivity.m16099$$Nest$msaveListState(this.this$0);
            ManageLinksActivity.m16091$$Nest$fgetrevokedInvites(this.this$0).remove(r0);
            ManageLinksActivity.m16100$$Nest$mupdateRecyclerViewAnimated(this.this$0, r42);
            return;
        }

        @Override // org.telegram.ui.LinkEditActivity.Callback
        public void revokeLink(TLRPC.TL_chatInviteExported r1) {
            this.this$0.revokeLink(r1);
        }
    }

    public class DiffCallback extends DiffUtil.Callback {
        SparseIntArray newPositionToItem;
        int oldAdminsEndRow;
        int oldAdminsStartRow;
        ArrayList<TLRPC.TL_chatInviteExported> oldLinks;
        int oldLinksEndRow;
        int oldLinksStartRow;
        SparseIntArray oldPositionToItem;
        ArrayList<TLRPC.TL_chatInviteExported> oldRevokedLinks;
        int oldRevokedLinksEndRow;
        int oldRevokedLinksStartRow;
        int oldRowCount;
        final /* synthetic */ ManageLinksActivity this$0;

        private DiffCallback(ManageLinksActivity r1) {
            this.this$0 = r1;
            this.oldPositionToItem = new SparseIntArray();
            this.newPositionToItem = new SparseIntArray();
            this.oldLinks = new ArrayList();
            this.oldRevokedLinks = new ArrayList();
        }

        public /* synthetic */ DiffCallback(ManageLinksActivity r1, ManageLinksActivityIA r2) {
            this(r1);
        }

        private void put(int r1, int r2, SparseIntArray r3) {
            if (r2 < 0) goto L5;
            r3.put(r2, r1);
            return;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return areItemsTheSame(r1, r2);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r5, int r6) {
            if (r5 < this.oldLinksStartRow) goto L7;
            if (r5 >= this.oldLinksEndRow) goto L7;
        L11:
            if (r6 < ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0)) goto L15;
            if (r6 >= ManageLinksActivity.m16079$$Nest$fgetlinksEndRow(this.this$0)) goto L15;
        L19:
            if (r6 >= ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0)) goto L21;
        L23:
            TLRPC.TL_chatInviteExported r62 = (TLRPC.TL_chatInviteExported) ManageLinksActivity.m16091$$Nest$fgetrevokedInvites(this.this$0).get(r6 - ManageLinksActivity.m16093$$Nest$fgetrevokedLinksStartRow(this.this$0));
        L24:
            int r0 = this.oldLinksStartRow;
            if (r5 >= r0) goto L27;
        L29:
            TLRPC.TL_chatInviteExported r4 = this.oldRevokedLinks.get(r5 - this.oldRevokedLinksStartRow);
        L31:
            return r4.link.equals(r62.link);
        L27:
            if (r5 >= this.oldLinksEndRow) goto L29;
            r4 = this.oldLinks.get(r5 - r0);
            goto L31
        L21:
            if (r6 >= ManageLinksActivity.m16079$$Nest$fgetlinksEndRow(this.this$0)) goto L23;
            r62 = (TLRPC.TL_chatInviteExported) ManageLinksActivity.m16074$$Nest$fgetinvites(this.this$0).get(r6 - ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0));
        L15:
            if (r6 < ManageLinksActivity.m16093$$Nest$fgetrevokedLinksStartRow(this.this$0)) goto L33;
            if (r6 < ManageLinksActivity.m16092$$Nest$fgetrevokedLinksEndRow(this.this$0)) goto L19;
        L33:
            if (r5 >= this.oldAdminsStartRow) goto L35;
        L44:
            int r52 = this.oldPositionToItem.get(r5, -1);
            int r42 = this.newPositionToItem.get(r6, -1);
            if (r52 < 0) goto L48;
            if (r52 != r42) goto L48;
            return true;
        L48:
            return false;
        L35:
            if (r5 >= this.oldAdminsEndRow) goto L44;
            if (r6 < ManageLinksActivity.m16061$$Nest$fgetadminsStartRow(this.this$0)) goto L44;
            if (r6 >= ManageLinksActivity.m16059$$Nest$fgetadminsEndRow(this.this$0)) goto L44;
            if ((r5 - this.oldAdminsStartRow) != (r6 - ManageLinksActivity.m16061$$Nest$fgetadminsStartRow(this.this$0))) goto L43;
            return true;
        L43:
            return false;
        L7:
            if (r5 < this.oldRevokedLinksStartRow) goto L33;
            if (r5 >= this.oldRevokedLinksEndRow) goto L33;
            goto L11
        }

        public void fillPositions(SparseIntArray r3) {
            r3.clear();
            put(1, ManageLinksActivity.m16070$$Nest$fgethelpRow(this.this$0), r3);
            put(2, ManageLinksActivity.m16085$$Nest$fgetpermanentLinkHeaderRow(this.this$0), r3);
            put(3, ManageLinksActivity.m16086$$Nest$fgetpermanentLinkRow(this.this$0), r3);
            put(4, ManageLinksActivity.m16069$$Nest$fgetdividerRow(this.this$0), r3);
            put(5, ManageLinksActivity.m16064$$Nest$fgetcreateNewLinkRow(this.this$0), r3);
            put(6, ManageLinksActivity.m16090$$Nest$fgetrevokedHeader(this.this$0), r3);
            put(7, ManageLinksActivity.m16088$$Nest$fgetrevokeAllRow(this.this$0), r3);
            put(8, ManageLinksActivity.m16063$$Nest$fgetcreateLinkHelpRow(this.this$0), r3);
            put(9, ManageLinksActivity.m16066$$Nest$fgetcreatorRow(this.this$0), r3);
            put(10, ManageLinksActivity.m16065$$Nest$fgetcreatorDividerRow(this.this$0), r3);
            put(11, ManageLinksActivity.m16060$$Nest$fgetadminsHeaderRow(this.this$0), r3);
            put(12, ManageLinksActivity.m16080$$Nest$fgetlinksHeaderRow(this.this$0), r3);
            put(13, ManageLinksActivity.m16082$$Nest$fgetlinksLoadingRow(this.this$0), r3);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return ManageLinksActivity.m16094$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.oldRowCount;
        }
    }

    public static class EmptyView extends LinearLayout implements NotificationCenter.NotificationCenterDelegate {
        private final int currentAccount;
        private BackupImageView stickerView;

        public EmptyView(Context r9) {
            super(r9);
            this.currentAccount = UserConfig.selectedAccount;
            setPadding(0, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f));
            setOrientation(1);
            BackupImageView r0 = new BackupImageView(r9);
            this.stickerView = r0;
            addView(r0, LayoutHelper.createLinear(104, 104, 49, 0, 2, 0, 0));
        }

        private void setSticker() {
            TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
            if (r0 != null) goto L5;
            r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME);
        L5:
            TLRPC.TL_messages_stickerSet r7 = r0;
            if (r7 != null) goto L8;
        L11:
            MediaDataController r8 = MediaDataController.getInstance(this.currentAccount);
            if (r7 != null) goto L14;
            boolean r2 = true;
        L15:
            r8.loadStickersByEmojiOrName(AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME, false, r2);
            return;
        L14:
            r2 = false;
            goto L15
        L8:
            if (r7.documents.size() < 4) goto L11;
            TLRPC.Document r02 = r7.documents.get(3);
            this.stickerView.setImage(ImageLocation.getForDocument(r02), "104_104", "tgs", DocumentObject.getSvgThumb(r02, Theme.key_windowBackgroundGray, 1.0f), r7);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 == NotificationCenter.diceStickersDidLoad) goto L5;
            return;
        L5:
            if (AndroidUtilities.STICKERS_PLACEHOLDER_PACK_NAME.equals((String) r3[0]) == false) goto L9;
            setSticker();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            setSticker();
            NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.diceStickersDidLoad);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.diceStickersDidLoad);
        }
    }

    public class HintInnerCell extends FrameLayout {
        private EmptyView emptyView;
        private TextView messageTextView;
        final /* synthetic */ ManageLinksActivity this$0;

        public HintInnerCell(ManageLinksActivity r9, Context r10) {
            this.this$0 = r9;
            super(r10);
            EmptyView r0 = new EmptyView(r10);
            this.emptyView = r0;
            addView(r0, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 10.0f, 0.0f, 0.0f));
            TextView r02 = new TextView(r10);
            this.messageTextView = r02;
            r02.setTextColor(Theme.getColor(Theme.key_chats_message));
            this.messageTextView.setTextSize(1, 14.0f);
            this.messageTextView.setGravity(17);
            TextView r102 = this.messageTextView;
            if (ManageLinksActivity.m16076$$Nest$fgetisChannel(r9) == false) goto L5;
            int r92 = R.string.PrimaryLinkHelpChannel;
        L6:
            r102.setText(LocaleController.getString(r92));
            addView(this.messageTextView, LayoutHelper.createFrame(-1, -2.0f, 51, 52.0f, 143.0f, 52.0f, 18.0f));
            return;
        L5:
            r92 = R.string.PrimaryLinkHelp;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    public class LinkCell extends FrameLayout {
        int animateFromState;
        boolean animateHideExpiring;
        float animateToStateProgress;
        boolean drawDivider;
        TLRPC.TL_chatInviteExported invite;
        float lastDrawExpringProgress;
        int lastDrawingState;
        ImageView optionsView;
        Paint paint;
        Paint paint2;
        int position;
        private final LinearLayout priceLayout;
        private final TextView priceSubitleView;
        private final TextView priceTitleView;
        RectF rectF;
        private final TextView subtitleView;
        private final LinearLayout textLayout;
        final /* synthetic */ ManageLinksActivity this$0;
        private final TimerParticles timerParticles;
        boolean timerRunning;
        private final TextView titleView;

        /* renamed from: org.telegram.ui.ManageLinksActivity$LinkCell$1, reason: invalid class name */
        public class AnonymousClass1 extends ShareAlert {
            final /* synthetic */ LinkCell this$1;

            public AnonymousClass1(LinkCell r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7, Theme.ResourcesProvider r8) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6, r7, r8);
            }

            @Override // org.telegram.ui.Components.ShareAlert
            public void onSend(LongSparseArray<TLRPC.Dialog> r3, int r4, TLRPC.TL_forumTopic r5, boolean r6) {
                if (r6 == true) goto L5;
                return;
            L5:
                if (r3 != null) goto L7;
            L15:
                String r32 = LocaleController.formatString(R.string.InvLinkToChats, new Object[]{LocaleController.formatPluralString("Chats", r4, new Object[0])});
            L16:
                Bulletin r2 = BulletinFactory.of(this.this$1.this$0).createSimpleBulletin(R.raw.forward, AndroidUtilities.replaceTags(r32));
                r2.hideAfterBottomSheet = false;
                r2.show(true);
                return;
            L7:
                if (r3.size() != 1) goto L15;
                long r33 = r3.valueAt(0).id;
                if (r33 != 0) goto L11;
            L14:
                r32 = LocaleController.getString(R.string.InvLinkToSavedMessages);
                goto L16
            L11:
                if (r33 == this.this$1.this$0.getUserConfig().getClientUserId()) goto L14;
                r32 = LocaleController.formatString(R.string.InvLinkToUser, new Object[]{this.this$1.this$0.getMessagesController().getPeerName(r33, true)});
                goto L16
            }
        }

        /* renamed from: $r8$lambda$7lKOBuoyJJFCkFMmVzX-ibmiYls, reason: not valid java name */
        public static /* synthetic */ void m16102$r8$lambda$7lKOBuoyJJFCkFMmVzXibmiYls(LinkCell r0, View r1) {
            r0.lambda$new$7(r1);
        }

        public static /* synthetic */ void $r8$lambda$IM7BOlb67lH2JJIsGpSepLfbUvw(LinkCell r0) {
            r0.lambda$new$2();
        }

        public static /* synthetic */ void $r8$lambda$O87ebYvdbzDj9mWd99mVrKBNEqY(LinkCell r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$P86ucgJJZ4jNs7Xrf7pvzGNUKTU(LinkCell r0) {
            r0.lambda$new$4();
        }

        public static /* synthetic */ void $r8$lambda$RosOMqtBxSdbIZcaYSJ_QjrRLtg(LinkCell r0, TLRPC.TL_chatInviteExported r1, AlertDialog r2, int r3) {
            r0.lambda$new$5(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$e7PNv2d7bN9lvnrO-SuaRQoRAVE, reason: not valid java name */
        public static /* synthetic */ void m16103$r8$lambda$e7PNv2d7bN9lvnrOSuaRQoRAVE(LinkCell r0) {
            r0.lambda$new$3();
        }

        public static /* synthetic */ void $r8$lambda$j82Hfa4_OH69YwGYboy7TZfjZAI(LinkCell r0) {
            r0.lambda$new$6();
        }

        public static /* synthetic */ void $r8$lambda$q0JHAFqGMNNv53UZ8H0yTrFxykM(LinkCell r0, TLRPC.TL_chatInviteExported r1, AlertDialog r2, int r3) {
            r0.lambda$new$0(r1, r2, r3);
        }

        public LinkCell(ManageLinksActivity r20, Context r21) {
            this.this$0 = r20;
            super(r21);
            this.paint = new Paint(1);
            this.paint2 = new Paint(1);
            this.rectF = new RectF();
            this.animateToStateProgress = 1.0f;
            this.timerParticles = new TimerParticles();
            this.paint2.setStyle(Paint.Style.STROKE);
            this.paint2.setStrokeCap(Paint.Cap.ROUND);
            LinearLayout r1 = new LinearLayout(r21);
            this.textLayout = r1;
            r1.setOrientation(1);
            addView(r1, LayoutHelper.createFrame(-1, -2.0f, 16, 64.0f, 0.0f, 30.0f, 0.0f));
            TextView r4 = new TextView(r21);
            this.titleView = r4;
            r4.setTextSize(1, 16.0f);
            int r6 = Theme.key_windowBackgroundWhiteBlackText;
            r4.setTextColor(Theme.getColor(r6));
            r4.setLines(1);
            TextUtils.TruncateAt r7 = TextUtils.TruncateAt.END;
            r4.setEllipsize(r7);
            r1.addView(r4, LayoutHelper.createLinear(-1, -2));
            TextView r42 = new TextView(r21);
            this.subtitleView = r42;
            r42.setTextSize(1, 13.0f);
            int r11 = Theme.key_windowBackgroundWhiteGrayText;
            r42.setTextColor(Theme.getColor(r11));
            r1.addView(r42, LayoutHelper.createLinear(-1, -2, 0.0f, 4.33f, 0.0f, 0.0f));
            ImageView r12 = new ImageView(r21);
            this.optionsView = r12;
            r12.setImageDrawable(ContextCompat.getDrawable(r21, R.drawable.ic_ab_other));
            this.optionsView.setScaleType(ImageView.ScaleType.CENTER);
            this.optionsView.setColorFilter(Theme.getColor(Theme.key_stickers_menu));
            this.optionsView.setOnClickListener(new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda0(this));
            this.optionsView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 1));
            addView(this.optionsView, LayoutHelper.createFrame(48, 48.0f, 21, 0.0f, 0.0f, 8.0f, 0.0f));
            setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            setWillNotDraw(false);
            LinearLayout r13 = new LinearLayout(r21);
            this.priceLayout = r13;
            r13.setOrientation(1);
            TextView r43 = new TextView(r21);
            this.priceTitleView = r43;
            r43.setTextSize(1, 16.0f);
            r43.setTextColor(Theme.getColor(r6));
            r43.setLines(1);
            r43.setEllipsize(r7);
            r43.setTypeface(AndroidUtilities.bold());
            r43.setGravity(5);
            r13.addView(r43, LayoutHelper.createLinear(-1, -2, 5));
            TextView r44 = new TextView(r21);
            this.priceSubitleView = r44;
            r44.setTextSize(1, 13.0f);
            r44.setTextColor(Theme.getColor(r11));
            r44.setGravity(5);
            r13.addView(r44, LayoutHelper.createLinear(-1, -2, 5, 0, 1, 0, 0));
            addView(r13, LayoutHelper.createFrame(-2, -2.0f, 21, 0.0f, 0.0f, 18.0f, 0.0f));
            r13.setVisibility(8);
        }

        private int getColor(int r2, float r3) {
            TLRPC.TL_chatInviteExported r1 = this.invite;
            if (r1 == null) goto L9;
            if (r1.subscription_pricing == null) goto L9;
            return Theme.getColor(Theme.key_color_green);
        L9:
            if (r2 != 3) goto L13;
            return Theme.getColor(Theme.key_chat_attachAudioBackground);
        L13:
            if (r2 != 1) goto L21;
            if (r3 <= 0.5f) goto L19;
            return ColorUtils.blendARGB(Theme.getColor(Theme.key_chat_attachLocationBackground), Theme.getColor(Theme.key_chat_attachPollBackground), 1.0f - ((r3 - 0.5f) / 0.5f));
        L19:
            return ColorUtils.blendARGB(Theme.getColor(Theme.key_chat_attachPollBackground), Theme.getColor(Theme.key_chat_attachAudioBackground), 1.0f - (r3 / 0.5f));
        L21:
            if (r2 != 2) goto L25;
            return Theme.getColor(Theme.key_chat_attachPollBackground);
        L25:
            if (r2 != 4) goto L29;
            return Theme.getColor(Theme.key_chats_unreadCounterMuted);
        L29:
            return Theme.getColor(Theme.key_featuredStickers_addButton);
        }

        private boolean hasProgress(int r2) {
            if (r2 == 2) goto L8;
            if (r2 == 1) goto L8;
            return false;
        L8:
            return true;
        }

        private /* synthetic */ void lambda$new$0(TLRPC.TL_chatInviteExported r1, AlertDialog r2, int r3) {
            this.this$0.deleteLink(r1);
        }

        private /* synthetic */ void lambda$new$1() {
            TLRPC.TL_chatInviteExported r0 = this.invite;
            new AlertDialog.Builder(this.this$0.getParentActivity()).setTitle(LocaleController.getString(R.string.DeleteLink)).setMessage(LocaleController.getString(R.string.DeleteLinkHelp)).setPositiveButton(LocaleController.getString(R.string.Delete), new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda7(this, r0)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        }

        private /* synthetic */ void lambda$new$2() {
        L7:
            e = move-exception;
            FileLog.e(e);
            return;
        L3:
            if (this.invite.link != null) goto L5;
            return;
        L5:
            ((ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", this.invite.link));     // Catch: Exception -> L7
            BulletinFactory.createCopyLinkBulletin(this.this$0).show();     // Catch: Exception -> L7
        }

        private /* synthetic */ void lambda$new$3() {
        L7:
            e = move-exception;
            FileLog.e(e);
            return;
        L3:
            if (this.invite.link != null) goto L5;
            return;
        L5:
            ManageLinksActivity r0 = this.this$0;     // Catch: Exception -> L7
            Context r3 = getContext();     // Catch: Exception -> L7
            String r5 = this.invite.link;     // Catch: Exception -> L7
            r0.showDialog(new AnonymousClass1(this, r3, null, r5, false, r5, false, this.this$0.getResourceProvider()));     // Catch: Exception -> L7
        }

        private /* synthetic */ void lambda$new$4() {
            this.this$0.editLink(this.invite);
        }

        private /* synthetic */ void lambda$new$5(TLRPC.TL_chatInviteExported r1, AlertDialog r2, int r3) {
            this.this$0.revokeLink(r1);
        }

        private /* synthetic */ void lambda$new$6() {
            TLRPC.TL_chatInviteExported r0 = this.invite;
            new AlertDialog.Builder(this.this$0.getParentActivity()).setMessage(LocaleController.getString(R.string.RevokeAlert)).setTitle(LocaleController.getString(R.string.RevokeLink)).setPositiveButton(LocaleController.getString(R.string.RevokeButton), new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda6(this, r0)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).show();
        }

        private /* synthetic */ void lambda$new$7(View r7) {
            if (this.invite == null) goto L20;
            View r72 = this.this$0.fragmentView;
            if ((r72 instanceof ViewGroup) == true) goto L8;
            return;
        L8:
            ItemOptions r0 = ItemOptions.makeOptions((ViewGroup) r72, this);
            boolean r1 = true;
            if (this.invite.revoked == false) goto L11;
            r0.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda1(this));
        L18:
            r0.setScrimViewBackground(ManageLinksActivity.m16084$$Nest$fgetlistView(this.this$0).getClipBackground(this));
            r0.show();
            return;
        L11:
            r0.add(R.drawable.msg_copy, LocaleController.getString(R.string.CopyLink), new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda2(this));
            r0.add(R.drawable.msg_share, LocaleController.getString(R.string.ShareLink), new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda3(this));
            if (this.invite.permanent == false) goto L14;
        L16:
            r1 = false;
        L17:
            r0.addIf(r1, R.drawable.msg_edit, LocaleController.getString(R.string.EditLink), new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda4(this));
            r0.addIf(ManageLinksActivity.m16062$$Nest$fgetcanEdit(this.this$0), R.drawable.msg_delete, LocaleController.getString(R.string.RevokeLink), true, new ManageLinksActivity$LinkCell$$ExternalSyntheticLambda5(this));
            goto L18
        L14:
            if (ManageLinksActivity.m16062$$Nest$fgetcanEdit(this.this$0) == false) goto L16;
        }

        @Override // android.view.View
        public void onDraw(Canvas r21) {
            Canvas r1 = r21;
            if (this.invite == null) goto L90;
            int r7 = AndroidUtilities.dp(32.0f);
            int r8 = getMeasuredHeight() / 2;
            TLRPC.TL_chatInviteExported r3 = this.invite;
            int r5 = 3;
            if (r3.expired == false) goto L8;
        L9:
            float r16 = 32.0f;
            if (r3.revoked == true) goto L33;
        L16:
            float r32 = 0.0f;
            float r2 = 1.0f;
        L34:
            int r4 = this.lastDrawingState;
            if (r5 == r4) goto L43;
            if (r4 < 0) goto L43;
            this.animateFromState = r4;
            this.animateToStateProgress = 0.0f;
            if (hasProgress(r4) == true) goto L40;
        L42:
            this.animateHideExpiring = false;
            goto L43
        L40:
            if (hasProgress(r5) == true) goto L42;
            this.animateHideExpiring = true;
        L43:
            this.lastDrawingState = r5;
            float r42 = this.animateToStateProgress;
            if (r42 == 1.0f) goto L50;
            float r43 = r42 + 0.064f;
            this.animateToStateProgress = r43;
            if (r43 < 1.0f) goto L48;
            this.animateToStateProgress = 1.0f;
            this.animateHideExpiring = false;
            goto L50
        L48:
            invalidate();
        L50:
            if (this.animateToStateProgress == 1.0f) goto L52;
            int r33 = ColorUtils.blendARGB(getColor(this.animateFromState, r32), getColor(r5, r32), this.animateToStateProgress);
        L53:
            this.paint.setColor(r33);
            r1.drawCircle(r7, r8, AndroidUtilities.dp(r16) / 2.0f, this.paint);
            boolean r44 = this.animateHideExpiring;
            if (r44 == true) goto L61;
            TLRPC.TL_chatInviteExported r52 = this.invite;
            if (r52.expired == false) goto L58;
        L78:
            TLRPC.TL_chatInviteExported r22 = this.invite;
            if (r22.subscription_pricing == null) goto L81;
            this.this$0.linkIconRevenue.setBounds(r7 - AndroidUtilities.dp(12.0f), r8 - AndroidUtilities.dp(12.0f), r7 + AndroidUtilities.dp(12.0f), r8 + AndroidUtilities.dp(12.0f));
            this.this$0.linkIconRevenue.draw(r1);
        L86:
            if (this.drawDivider == false) goto L89;
            r1.drawLine(AndroidUtilities.dp(70.0f), getMeasuredHeight() - 1, getMeasuredWidth() + AndroidUtilities.dp(23.0f), getMeasuredHeight(), Theme.dividerPaint);
            return;
        L89:
            return;
        L81:
            boolean r23 = r22.revoked;
            ManageLinksActivity r34 = this.this$0;
            if (r23 == false) goto L84;
            r34.linkIconRevoked.setBounds(r7 - AndroidUtilities.dp(12.0f), r8 - AndroidUtilities.dp(12.0f), r7 + AndroidUtilities.dp(12.0f), r8 + AndroidUtilities.dp(12.0f));
            this.this$0.linkIconRevoked.draw(r1);
            goto L86
        L84:
            r34.linkIcon.setBounds(r7 - AndroidUtilities.dp(12.0f), r8 - AndroidUtilities.dp(12.0f), r7 + AndroidUtilities.dp(12.0f), r8 + AndroidUtilities.dp(12.0f));
            this.this$0.linkIcon.draw(r1);
            goto L86
        L58:
            if (r52.expire_date <= 0) goto L78;
            if (r52.revoked == true) goto L78;
        L61:
            if (r44 == false) goto L63;
            r2 = this.lastDrawExpringProgress;
        L63:
            float r9 = r2;
            this.paint2.setColor(r33);
            this.rectF.set(r7 - AndroidUtilities.dp(20.0f), r8 - AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f) + r7, AndroidUtilities.dp(20.0f) + r8);
            if (this.animateToStateProgress != 1.0f) goto L66;
        L73:
            float r45 = (-r9) * 360.0f;
            r21.drawArc(this.rectF, -90.0f, r45, false, this.paint2);
            this.timerParticles.draw(r21, this.paint2, this.rectF, r45, 1.0f);
            r1 = r21;
        L75:
            if (ManageLinksActivity.access$200(this.this$0) == true) goto L77;
            invalidate();
        L77:
            this.lastDrawExpringProgress = r9;
            goto L78
        L66:
            if (hasProgress(this.animateFromState) == true) goto L68;
        L69:
            r1.save();
            boolean r24 = this.animateHideExpiring;
            float r46 = this.animateToStateProgress;
            if (r24 == false) goto L72;
            r46 = 1.0f - r46;
        L72:
            float r11 = r46;
            float r25 = (float) ((0.3f * r11) + 0.7d);
            r1.scale(r25, r25, this.rectF.centerX(), this.rectF.centerY());
            float r47 = (-r9) * 360.0f;
            r1.drawArc(this.rectF, -90.0f, r47, false, this.paint2);
            this.timerParticles.draw(r21, this.paint2, this.rectF, r47, r11);
            r21.restore();
            r1 = r21;
            goto L75
        L68:
            if (this.animateHideExpiring == false) goto L73;
        L52:
            r33 = getColor(r5, r32);
            goto L53
        L33:
            r5 = 4;
            goto L16
        L8:
            if (r3.revoked == true) goto L9;
            int r48 = r3.expire_date;
            if (r48 <= 0) goto L13;
        L17:
            if (r48 <= 0) goto L22;
            long r35 = System.currentTimeMillis() + (this.this$0.timeDif * 1000);
            TLRPC.TL_chatInviteExported r12 = this.invite;
            r16 = 32.0f;
            long r26 = r12.expire_date * 1000;
            int r49 = r12.start_date;
            if (r49 > 0) goto L21;
            r49 = r12.date;
        L21:
            long r122 = r49 * 1000;
            r2 = 1.0f - ((r35 - r122) / (r26 - r122));
        L23:
            int r410 = this.invite.usage_limit;
            if (r410 <= 0) goto L26;
            float r36 = (r410 - r3.usage) / r410;
        L27:
            r32 = Math.min(r2, r36);
            if (r32 > 0.0f) goto L30;
            this.invite.expired = true;
            AndroidUtilities.updateVisibleRows(ManageLinksActivity.m16084$$Nest$fgetlistView(this.this$0));
            goto L34
        L30:
            r5 = 1;
            goto L34
        L26:
            r36 = 1.0f;
            goto L27
        L22:
            r16 = 32.0f;
            r2 = 1.0f;
            goto L23
        L13:
            if (r3.usage_limit > 0) goto L17;
            r16 = 32.0f;
            r5 = 0;
            goto L16
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
            this.paint2.setStrokeWidth(AndroidUtilities.dp(2.0f));
        }

        public void setLink(TLRPC.TL_chatInviteExported r11, int r12) {
            this.timerRunning = false;
            TLRPC.TL_chatInviteExported r1 = this.invite;
            if (r1 == null) goto L7;
            if (r11 == null) goto L7;
            if (r1.link.equals(r11.link) == false) goto L7;
        L8:
            this.invite = r11;
            this.position = r12;
            if (r11 != null) goto L11;
            return;
        L11:
            int r122 = AndroidUtilities.dp(30.0f);
            TL_stars.TL_starsSubscriptionPricing r13 = r11.subscription_pricing;
            LinearLayout r2 = this.priceLayout;
            if (r13 == null) goto L23;
            r2.setVisibility(0);
            this.optionsView.setVisibility(8);
            this.priceTitleView.setText(StarsIntroActivity.replaceStarsWithPlain("⭐️ " + LocaleController.formatNumber(r11.subscription_pricing.amount, ','), 0.75f));
            int r123 = r11.subscription_pricing.period;
            if (r123 != 2592000) goto L17;
            this.priceSubitleView.setText(LocaleController.getString(R.string.StarsParticipantSubscriptionPerMonth));
        L22:
            r122 = AndroidUtilities.dp(28.0f) + ((int) Math.max(HintView2.measureCorrectly(this.priceTitleView.getText(), this.priceTitleView.getPaint()), HintView2.measureCorrectly(this.priceSubitleView.getText(), this.priceSubitleView.getPaint())));
        L24:
            ((ViewGroup.MarginLayoutParams) this.textLayout.getLayoutParams()).rightMargin = r122;
            if (TextUtils.isEmpty(r11.title) == true) goto L28;
            SpannableStringBuilder r124 = new SpannableStringBuilder(r11.title);
            Emoji.replaceEmoji(r124, this.titleView.getPaint().getFontMetricsInt(), false);
            this.titleView.setText(r124);
        L37:
            int r125 = r11.usage;
            if (r125 == 0) goto L40;
        L48:
            int r14 = r11.usage_limit;
            if (r14 <= 0) goto L57;
            if (r125 != 0) goto L57;
            if (r11.expired == true) goto L57;
            if (r11.revoked == true) goto L57;
            String r126 = LocaleController.formatPluralString("CanJoin", r14, new Object[0]);
        L72:
            SpannableStringBuilder r15 = new SpannableStringBuilder(r126);
            if (r11.permanent == false) goto L78;
            if (r11.revoked == true) goto L78;
            DotDividerSpan r127 = new DotDividerSpan();
            r127.setTopPadding(AndroidUtilities.dp(1.5f));
            r15.append("  .  ").setSpan(r127, r15.length() - 3, r15.length() - 2, 0);
            r15.append(LocaleController.getString(R.string.Permanent));
        L112:
            if (r11.request_needed == false) goto L114;
            DotDividerSpan r112 = new DotDividerSpan();
            r112.setTopPadding(AndroidUtilities.dp(1.5f));
            r15.append("  .  ").setSpan(r112, r15.length() - 3, r15.length() - 2, 0);
            r15.append(LocaleController.getString(R.string.ApprovalRequired));
        L114:
            this.subtitleView.setText(r15);
            return;
        L78:
            if (r11.expired == true) goto L92;
            if (r11.revoked == true) goto L92;
            if (r11.expire_date <= 0) goto L112;
            DotDividerSpan r128 = new DotDividerSpan();
            r128.setTopPadding(AndroidUtilities.dp(1.5f));
            r15.append("  .  ").setSpan(r128, r15.length() - 3, r15.length() - 2, 0);
            long r6 = (r11.expire_date * 1000) - (System.currentTimeMillis() + (this.this$0.timeDif * 1000));
            if (r6 >= 0) goto L88;
            r6 = 0;
        L88:
            if (r6 <= DurationKt.MILLIS_IN_DAY) goto L90;
            r15.append(LocaleController.formatPluralString("DaysLeft", (int) (r6 / DurationKt.MILLIS_IN_DAY), new Object[0]));
            goto L112
        L90:
            long r62 = r6 / 1000;
            int r129 = (int) (r62 % 60);
            long r63 = r62 / 60;
            Locale r5 = Locale.ENGLISH;
            r15.append(String.format(r5, TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Integer.valueOf((int) (r63 / 60))})).append(String.format(r5, ":%02d", new Object[]{Integer.valueOf((int) (r63 % 60))})).append(String.format(r5, ":%02d", new Object[]{Integer.valueOf(r129)}));
            this.timerRunning = true;
        L92:
            if (r11.revoked == true) goto L94;
        L100:
            DotDividerSpan r1210 = new DotDividerSpan();
            r1210.setTopPadding(AndroidUtilities.dp(1.5f));
            r15.append("  .  ").setSpan(r1210, r15.length() - 3, r15.length() - 2, 0);
            boolean r1211 = r11.revoked;
            if (r1211 == true) goto L107;
            int r4 = r11.usage_limit;
            if (r4 <= 0) goto L107;
            if (r11.usage < r4) goto L107;
            r15.append(LocaleController.getString(R.string.LinkLimitReached));
        L107:
            if (r1211 == false) goto L109;
            int r1212 = R.string.Revoked;
        L110:
            r15.append(LocaleController.getString(r1212));
            goto L112
        L109:
            r1212 = R.string.Expired;
            goto L110
        L94:
            if (r11.usage != 0) goto L100;
            if (r11.subscription_pricing == null) goto L98;
            int r1213 = R.string.NoOneSubscribed;
        L99:
            String r1214 = LocaleController.getString(r1213);
            r15.clear();
            r15.append(r1214);
            goto L100
        L98:
            r1213 = R.string.NoOneJoined;
        L57:
            if (r14 > 0) goto L59;
        L63:
            if (r125 <= 0) goto L65;
            r126 = LocaleController.formatPluralString("PeopleJoined", r125, new Object[0]);
        L67:
            if (r11.requested <= 0) goto L72;
            if (r11.usage <= 0) goto L71;
            r126 = r126 + ", ";
        L71:
            r126 = r126 + LocaleController.formatPluralString("JoinRequests", r11.requested, new Object[0]);
            goto L72
        L65:
            r126 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L67
        L59:
            if (r11.expired == false) goto L63;
            if (r11.revoked == false) goto L63;
            r126 = LocaleController.formatPluralString("PeopleJoined", r11.usage, new Object[0]) + ", " + LocaleController.formatPluralString("PeopleJoinedRemaining", r11.usage_limit - r11.usage, new Object[0]);
            goto L72
        L40:
            if (r11.usage_limit != 0) goto L48;
            if (r11.requested != 0) goto L48;
            if (r11.subscription_pricing == null) goto L46;
            int r1215 = R.string.NoOneSubscribed;
        L47:
            r126 = LocaleController.getString(r1215);
            goto L72
        L46:
            r1215 = R.string.NoOneJoined;
            goto L47
        L28:
            if (r11.link.startsWith("https://t.me/+") == false) goto L31;
            this.titleView.setText(MessagesController.getInstance(ManageLinksActivity.access$300(this.this$0)).linkPrefix + "/" + r11.link.substring(14));
            goto L37
        L31:
            if (r11.link.startsWith("https://t.me/joinchat/") == false) goto L33;
            this.titleView.setText(r11.link.substring(22));
            goto L37
        L33:
            boolean r1216 = r11.link.startsWith("https://");
            TextView r16 = this.titleView;
            if (r1216 == false) goto L36;
            r16.setText(r11.link.substring(8));
            goto L37
        L36:
            r16.setText(r11.link);
            goto L37
        L17:
            if (r123 != 300) goto L20;
            this.priceSubitleView.setText("per 5 minutes");
            goto L22
        L20:
            if (r123 != 60) goto L22;
            this.priceSubitleView.setText("each minute");
            goto L22
        L23:
            r2.setVisibility(8);
            this.optionsView.setVisibility(8);
        L7:
            this.lastDrawingState = -1;
            this.animateToStateProgress = 1.0f;
            goto L8
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ManageLinksActivity this$0;

        /* renamed from: org.telegram.ui.ManageLinksActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements LinkActionView.Delegate {
            final /* synthetic */ ListAdapter this$1;
            final /* synthetic */ LinkActionView val$linkActionView;

            public AnonymousClass1(ListAdapter r1, LinkActionView r2) {
                this.this$1 = r1;
                this.val$linkActionView = r2;
            }

            @Override // org.telegram.ui.Components.LinkActionView.Delegate
            public void revokeLink() {
                ManageLinksActivity.m16098$$Nest$mrevokePermanent(this.this$1.this$0);
            }

            @Override // org.telegram.ui.Components.LinkActionView.Delegate
            public void showUsersForPermanentLink() {
                ManageLinksActivity r0 = this.this$1.this$0;
                Context r2 = this.val$linkActionView.getContext();
                TLRPC.TL_chatInviteExported r3 = ManageLinksActivity.m16072$$Nest$fgetinvite(this.this$1.this$0);
                TLRPC.ChatFull r4 = ManageLinksActivity.m16071$$Nest$fgetinfo(this.this$1.this$0);
                HashMap r5 = ManageLinksActivity.m16095$$Nest$fgetusers(this.this$1.this$0);
                ManageLinksActivity r6 = this.this$1.this$0;
                ManageLinksActivity.m16096$$Nest$fputinviteLinkBottomSheet(r0, new InviteLinkBottomSheet(r2, r3, r4, r5, r6, ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(r6), true, ManageLinksActivity.m16076$$Nest$fgetisChannel(this.this$1.this$0)));
                ManageLinksActivity.m16073$$Nest$fgetinviteLinkBottomSheet(this.this$1.this$0).show();
            }
        }

        public ListAdapter(ManageLinksActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ManageLinksActivity.m16094$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != ManageLinksActivity.m16070$$Nest$fgethelpRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r3 != ManageLinksActivity.m16085$$Nest$fgetpermanentLinkHeaderRow(this.this$0)) goto L9;
        L76:
            return 1;
        L9:
            if (r3 == ManageLinksActivity.m16090$$Nest$fgetrevokedHeader(this.this$0)) goto L76;
            if (r3 == ManageLinksActivity.m16060$$Nest$fgetadminsHeaderRow(this.this$0)) goto L76;
            if (r3 == ManageLinksActivity.m16080$$Nest$fgetlinksHeaderRow(this.this$0)) goto L76;
            if (r3 != ManageLinksActivity.m16086$$Nest$fgetpermanentLinkRow(this.this$0)) goto L20;
            return 2;
        L20:
            if (r3 != ManageLinksActivity.m16064$$Nest$fgetcreateNewLinkRow(this.this$0)) goto L24;
            return 3;
        L24:
            if (r3 != ManageLinksActivity.m16069$$Nest$fgetdividerRow(this.this$0)) goto L26;
            return 4;
        L26:
            if (r3 != ManageLinksActivity.m16089$$Nest$fgetrevokedDivider(this.this$0)) goto L28;
            return 4;
        L28:
            if (r3 != ManageLinksActivity.m16087$$Nest$fgetrevokeAllDivider(this.this$0)) goto L30;
            return 4;
        L30:
            if (r3 != ManageLinksActivity.m16065$$Nest$fgetcreatorDividerRow(this.this$0)) goto L32;
            return 4;
        L32:
            if (r3 != ManageLinksActivity.m16058$$Nest$fgetadminsDividerRow(this.this$0)) goto L35;
            return 4;
        L35:
            if (r3 < ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0)) goto L39;
            if (r3 >= ManageLinksActivity.m16079$$Nest$fgetlinksEndRow(this.this$0)) goto L39;
            return 5;
        L39:
            if (r3 < ManageLinksActivity.m16093$$Nest$fgetrevokedLinksStartRow(this.this$0)) goto L45;
            if (r3 >= ManageLinksActivity.m16092$$Nest$fgetrevokedLinksEndRow(this.this$0)) goto L45;
            return 5;
        L45:
            if (r3 != ManageLinksActivity.m16082$$Nest$fgetlinksLoadingRow(this.this$0)) goto L49;
            return 6;
        L49:
            if (r3 != ManageLinksActivity.m16078$$Nest$fgetlastDivider(this.this$0)) goto L53;
            return 7;
        L53:
            if (r3 != ManageLinksActivity.m16088$$Nest$fgetrevokeAllRow(this.this$0)) goto L57;
            return 8;
        L57:
            if (r3 != ManageLinksActivity.m16063$$Nest$fgetcreateLinkHelpRow(this.this$0)) goto L61;
            return 9;
        L61:
            if (r3 != ManageLinksActivity.m16066$$Nest$fgetcreatorRow(this.this$0)) goto L63;
            return 10;
        L63:
            if (r3 < ManageLinksActivity.m16061$$Nest$fgetadminsStartRow(this.this$0)) goto L68;
            if (r3 >= ManageLinksActivity.m16059$$Nest$fgetadminsEndRow(this.this$0)) goto L68;
            return 10;
        L68:
            if (r3 != ManageLinksActivity.m16081$$Nest$fgetlinksInfoRow(this.this$0)) goto L71;
            return 11;
        L71:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            int r32 = r3.getAdapterPosition();
            if (ManageLinksActivity.m16066$$Nest$fgetcreatorRow(this.this$0) != r32) goto L6;
            return true;
        L6:
            if (ManageLinksActivity.m16064$$Nest$fgetcreateNewLinkRow(this.this$0) != r32) goto L9;
            return true;
        L9:
            if (r32 < ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0)) goto L14;
            if (r32 >= ManageLinksActivity.m16079$$Nest$fgetlinksEndRow(this.this$0)) goto L14;
            return true;
        L14:
            if (r32 < ManageLinksActivity.m16093$$Nest$fgetrevokedLinksStartRow(this.this$0)) goto L19;
            if (r32 >= ManageLinksActivity.m16092$$Nest$fgetrevokedLinksEndRow(this.this$0)) goto L19;
            return true;
        L19:
            if (r32 != ManageLinksActivity.m16088$$Nest$fgetrevokeAllRow(this.this$0)) goto L22;
            return true;
        L22:
            if (r32 >= ManageLinksActivity.m16061$$Nest$fgetadminsStartRow(this.this$0)) goto L24;
            return false;
        L24:
            if (r32 >= ManageLinksActivity.m16059$$Nest$fgetadminsEndRow(this.this$0)) goto L28;
            return true;
        L28:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            boolean r1 = true;
            if (r0 != 1) goto L5;
            HeaderCell r92 = (HeaderCell) r9.itemView;
            int r02 = ManageLinksActivity.m16085$$Nest$fgetpermanentLinkHeaderRow(this.this$0);
            ManageLinksActivity r12 = this.this$0;
            if (r10 != r02) goto L92;
            if (ManageLinksActivity.m16077$$Nest$fgetisPublic(r12) == false) goto L86;
            if (ManageLinksActivity.m16056$$Nest$fgetadminId(this.this$0) != this.this$0.getAccountInstance().getUserConfig().clientUserId) goto L86;
            r92.setText(LocaleController.getString(R.string.PublicLink));
            return;
        L86:
            if (ManageLinksActivity.m16056$$Nest$fgetadminId(this.this$0) != this.this$0.getAccountInstance().getUserConfig().clientUserId) goto L89;
            r92.setText(LocaleController.getString(R.string.ChannelInviteLinkTitle));
            return;
        L89:
            r92.setText(LocaleController.getString(R.string.PermanentLinkForThisAdmin));
            return;
        L92:
            if (r10 != ManageLinksActivity.m16090$$Nest$fgetrevokedHeader(r12)) goto L96;
            r92.setText(LocaleController.getString(R.string.RevokedLinks));
            return;
        L96:
            if (r10 != ManageLinksActivity.m16080$$Nest$fgetlinksHeaderRow(this.this$0)) goto L100;
            r92.setText(LocaleController.getString(R.string.LinksCreatedByThisAdmin));
            return;
        L100:
            if (r10 != ManageLinksActivity.m16060$$Nest$fgetadminsHeaderRow(this.this$0)) goto L106;
            r92.setText(LocaleController.getString(R.string.LinksCreatedByOtherAdmins));
            return;
        L106:
            return;
        L5:
            if (r0 != 2) goto L7;
            LinkActionView r93 = (LinkActionView) r9.itemView;
            if (ManageLinksActivity.m16056$$Nest$fgetadminId(this.this$0) != this.this$0.getAccountInstance().getUserConfig().clientUserId) goto L62;
            boolean r102 = true;
        L63:
            r93.setCanEdit(r102);
            if (ManageLinksActivity.m16077$$Nest$fgetisPublic(this.this$0) == true) goto L66;
        L71:
            r93.hideRevokeOption(!ManageLinksActivity.m16062$$Nest$fgetcanEdit(this.this$0));
            if (ManageLinksActivity.m16072$$Nest$fgetinvite(this.this$0) == null) goto L75;
            TLRPC.TL_chatInviteExported r103 = ManageLinksActivity.m16072$$Nest$fgetinvite(this.this$0);
            r93.setLink(r103.link);
            r93.loadUsers(r103, ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(this.this$0));
            return;
        L75:
            r93.setLink(null);
            r93.loadUsers(null, ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(this.this$0));
            return;
        L66:
            if (ManageLinksActivity.m16056$$Nest$fgetadminId(this.this$0) != this.this$0.getAccountInstance().getUserConfig().clientUserId) goto L71;
            if (ManageLinksActivity.m16071$$Nest$fgetinfo(this.this$0) == null) goto L105;
            r93.setLink("https://t.me/" + ChatObject.getPublicUsername(ManageLinksActivity.m16067$$Nest$fgetcurrentChat(this.this$0)));
            r93.setUsers(0, null);
            r93.hideRevokeOption(true);
            return;
        L105:
            return;
        L62:
            r102 = false;
            goto L63
        L7:
            if (r0 != 3) goto L9;
            CreationTextCell r94 = (CreationTextCell) r9.itemView;
            Drawable r104 = this.mContext.getResources().getDrawable(R.drawable.poll_add_circle);
            Drawable r03 = this.mContext.getResources().getDrawable(R.drawable.poll_add_plus);
            int r3 = Theme.getColor(Theme.key_switchTrackChecked);
            PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
            r104.setColorFilter(new PorterDuffColorFilter(r3, r4));
            r03.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_checkboxCheck), r4));
            r94.setTextAndIcon(LocaleController.getString(R.string.CreateNewLink), new CombinedDrawable(r104, r03), !ManageLinksActivity.m16074$$Nest$fgetinvites(this.this$0).isEmpty());
            return;
        L9:
            if (r0 == 5) goto L46;
            switch(r0) {
                case 9: goto L35;
                case 10: goto L24;
                case 11: goto L12;
                default: goto L107;
            };
        L12:
            TextInfoPrivacyCell r95 = (TextInfoPrivacyCell) r9.itemView;
            if (r10 != ManageLinksActivity.m16081$$Nest$fgetlinksInfoRow(this.this$0)) goto L103;
            TLRPC.ChatFull r105 = this.this$0.getMessagesController().getChatFull(ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(this.this$0));
            TLRPC.Chat r8 = this.this$0.getMessagesController().getChat(Long.valueOf(ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(this.this$0)));
            if (r105 != null) goto L17;
        L22:
            r95.setText(LocaleController.getString(R.string.ChannelLinksInfo));
            return;
        L17:
            if (r105.paid_media_allowed == false) goto L22;
            if (ChatObject.isChannelAndNotMegaGroup(r8) == false) goto L22;
            r95.setText(LocaleController.getString(R.string.ChannelLinksInfoPaid));
            return;
        L103:
            return;
        L24:
            ManageChatUserCell r96 = (ManageChatUserCell) r9.itemView;
            int r04 = ManageLinksActivity.m16066$$Nest$fgetcreatorRow(this.this$0);
            ManageLinksActivity r2 = this.this$0;
            if (r10 != r04) goto L28;
            TLRPC.User r106 = r2.getMessagesController().getUser(Long.valueOf(ManageLinksActivity.m16056$$Nest$fgetadminId(this.this$0)));
            int r82 = ManageLinksActivity.m16075$$Nest$fgetinvitesCount(this.this$0);
        L27:
            r1 = false;
        L32:
            if (r106 == null) goto L104;
            r96.setData(r106, ContactsController.formatName(r106.first_name, r106.last_name), LocaleController.formatPluralString("InviteLinkCount", r82, new Object[0]), r1);
            return;
        L104:
            return;
        L28:
            TLRPC.TL_chatAdminWithInvites r05 = (TLRPC.TL_chatAdminWithInvites) ManageLinksActivity.m16057$$Nest$fgetadmins(this.this$0).get(r10 - ManageLinksActivity.m16061$$Nest$fgetadminsStartRow(r2));
            TLRPC.User r22 = (TLRPC.User) ManageLinksActivity.m16095$$Nest$fgetusers(this.this$0).get(Long.valueOf(r05.admin_id));
            int r06 = r05.invites_count;
            if (r10 != (ManageLinksActivity.m16059$$Nest$fgetadminsEndRow(this.this$0) - 1)) goto L31;
            r82 = r06;
            r106 = r22;
            goto L27
        L31:
            r82 = r06;
            r106 = r22;
            goto L32
        L35:
            TextInfoPrivacyCell r97 = (TextInfoPrivacyCell) r9.itemView;
            TLRPC.ChatFull r107 = this.this$0.getMessagesController().getChatFull(ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(this.this$0));
            TLRPC.Chat r83 = this.this$0.getMessagesController().getChat(Long.valueOf(ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(this.this$0)));
            if (r107 != null) goto L38;
        L43:
            r97.setText(LocaleController.getString(R.string.ChannelLinksInfo));
            return;
        L38:
            if (r107.paid_media_allowed == false) goto L43;
            if (ChatObject.isChannelAndNotMegaGroup(r83) == false) goto L43;
            r97.setText(LocaleController.getString(R.string.ChannelLinksInfoPaid));
            return;
        L107:
            return;
        L46:
            if (r10 >= ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0)) goto L48;
        L52:
            TLRPC.TL_chatInviteExported r07 = (TLRPC.TL_chatInviteExported) ManageLinksActivity.m16091$$Nest$fgetrevokedInvites(this.this$0).get(r10 - ManageLinksActivity.m16093$$Nest$fgetrevokedLinksStartRow(this.this$0));
            if (r10 == (ManageLinksActivity.m16092$$Nest$fgetrevokedLinksEndRow(this.this$0) - 1)) goto L51;
        L55:
            LinkCell r98 = (LinkCell) r9.itemView;
            r98.setLink(r07, r10 - ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0));
            r98.drawDivider = r1;
            return;
        L51:
            r1 = false;
            goto L55
        L48:
            if (r10 >= ManageLinksActivity.m16079$$Nest$fgetlinksEndRow(this.this$0)) goto L52;
            r07 = (TLRPC.TL_chatInviteExported) ManageLinksActivity.m16074$$Nest$fgetinvites(this.this$0).get(r10 - ManageLinksActivity.m16083$$Nest$fgetlinksStartRow(this.this$0));
            if (r10 != (ManageLinksActivity.m16079$$Nest$fgetlinksEndRow(this.this$0) - 1)) goto L55;
            goto L51
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r10, int r11) {
            switch(r11) {
                case 1: goto L15;
                case 2: goto L14;
                case 3: goto L13;
                case 4: goto L12;
                case 5: goto L11;
                case 6: goto L10;
                case 7: goto L9;
                case 8: goto L8;
                case 9: goto L7;
                case 10: goto L6;
                case 11: goto L5;
                default: goto L4;
            };
        L4:
            View r102 = new HintInnerCell(this.this$0, this.mContext);
            r102.setTag(-33024);
            View r103 = r102;
        L16:
            r103.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r103);
        L5:
            r103 = new TextInfoPrivacyCell(this.mContext, ManageLinksActivity.access$100(this.this$0));
            goto L16
        L6:
            r103 = new ManageChatUserCell(this.mContext, 8, 6, false);
            goto L16
        L7:
            r103 = new TextInfoPrivacyCell(this.mContext);
            goto L16
        L8:
            TextSettingsCell r104 = new TextSettingsCell(this.mContext);
            r104.setText(LocaleController.getString(R.string.DeleteAllRevokedLinks), false);
            r104.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
            r103 = r104;
            goto L16
        L9:
            r103 = new ShadowSectionCell(this.mContext);
            goto L16
        L10:
            FlickerLoadingView r112 = new FlickerLoadingView(this.mContext);
            r112.setIsSingleCell(true);
            r112.setViewType(9);
            r112.showDate(false);
            r103 = r112;
            goto L16
        L11:
            r103 = new LinkCell(this.this$0, this.mContext);
            goto L16
        L12:
            r103 = new ShadowSectionCell(this.mContext);
            goto L16
        L13:
            r103 = new CreationTextCell(this.mContext, 64, ManageLinksActivity.access$000(this.this$0));
            goto L16
        L14:
            Context r2 = this.mContext;
            ManageLinksActivity r3 = this.this$0;
            LinkActionView r1 = new LinkActionView(r2, r3, null, ManageLinksActivity.m16068$$Nest$fgetcurrentChatId(r3), true, ManageLinksActivity.m16076$$Nest$fgetisChannel(this.this$0));
            r1.setPermanent(true);
            r1.setDelegate(new AnonymousClass1(this, r1));
            r103 = r1;
            goto L16
        L15:
            r103 = new HeaderCell(this.mContext, 23);
            goto L16
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }
    }

    /* renamed from: $r8$lambda$-rK2mb228X2_YjAecI3tmDzwKTg, reason: not valid java name */
    public static /* synthetic */ boolean m16052$r8$lambda$rK2mb228X2_YjAecI3tmDzwKTg(ManageLinksActivity r0, View r1, int r2) {
        return r0.lambda$createView$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$0kpc_nyYVP3IpKZ3HAcsnm_Va3g(ManageLinksActivity r0) {
        r0.lambda$getThemeDescriptions$17();
    }

    /* renamed from: $r8$lambda$1tWgu7rmTL7-45ZnXaZuc7toc50, reason: not valid java name */
    public static /* synthetic */ void m16053$r8$lambda$1tWgu7rmTL745ZnXaZuc7toc50(ManageLinksActivity r0, TLRPC.TL_chatInviteExported r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$revokeLink$16(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$3Ba7LF-5x_lC6NVWhrFiWRG129c, reason: not valid java name */
    public static /* synthetic */ void m16054$r8$lambda$3Ba7LF5x_lC6NVWhrFiWRG129c(ManageLinksActivity r0, TLRPC.TL_chatInviteExported r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$deleteLink$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$CxBJigd_L71bsFwHXEIx4LNgMUA(ManageLinksActivity r0, TLRPC.TL_error r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$QXGlwHziqiWq62NDtsH1WU2MgUs(ManageLinksActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadLinks$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ti_JXowJ8HzVs0cXKJP_KYxTSMk(ManageLinksActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$UP2sBOv69KJbaS49dspQTfD6uy0(ManageLinksActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadLinks$0(r1, r2);
    }

    /* renamed from: $r8$lambda$VP-AyXq_nxAhxadJ8Akhh8u6zjg, reason: not valid java name */
    public static /* synthetic */ void m16055$r8$lambda$VPAyXq_nxAhxadJ8Akhh8u6zjg(ManageLinksActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadLinks$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_Zdog2vxecgHAYmnkyhOyCtawb8(ManageLinksActivity r0, TLRPC.TL_chatInviteExported r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$revokePermanent$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$b88xyGghKchBqAIC39PNLsZD_rs(ManageLinksActivity r0, TLRPC.TL_error r1, TLRPC.TL_chatInviteExported r2) {
        r0.lambda$deleteLink$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$d1QROUnHUcrgmEKgVQDBB0ZyDJQ(ManageLinksActivity r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_chatInviteExported r3) {
        r0.lambda$revokeLink$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$g8LOZXpU4tFNheHHMQrTesoKfl8(ManageLinksActivity r0, TLRPC.TL_chatInviteExported r1, boolean r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadLinks$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$kGkBnjCtWN4M25UpOqZ3Sz9fM1A(ManageLinksActivity r0, TLRPC.TL_chatInviteExported r1, TLRPC.TL_error r2, TLObject r3, boolean r4) {
        r0.lambda$loadLinks$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$pXlZBar6fhqNLvTj7cvHn3r2yeU(ManageLinksActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$createView$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tzrydcp3PTLajx_DdY2VD4svPEY(ManageLinksActivity r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_chatInviteExported r3) {
        r0.lambda$revokePermanent$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$uBxnQ7MKelnveuFqFA_x9k6_jN8(ManageLinksActivity r0, TLRPC.TL_chatInviteExported r1, TLRPC.TL_error r2, TLObject r3, boolean r4) {
        r0.lambda$loadLinks$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$u_TD3CHuUNIqYzW4BGlK6VNeZeE(ManageLinksActivity r0, AlertDialog r1, int r2) {
        r0.lambda$createView$8(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadminId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m16056$$Nest$fgetadminId(ManageLinksActivity r2) {
        return r2.adminId;
    }

    /* renamed from: -$$Nest$fgetadmins, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16057$$Nest$fgetadmins(ManageLinksActivity r0) {
        return r0.admins;
    }

    /* renamed from: -$$Nest$fgetadminsDividerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16058$$Nest$fgetadminsDividerRow(ManageLinksActivity r0) {
        return r0.adminsDividerRow;
    }

    /* renamed from: -$$Nest$fgetadminsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16059$$Nest$fgetadminsEndRow(ManageLinksActivity r0) {
        return r0.adminsEndRow;
    }

    /* renamed from: -$$Nest$fgetadminsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16060$$Nest$fgetadminsHeaderRow(ManageLinksActivity r0) {
        return r0.adminsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetadminsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16061$$Nest$fgetadminsStartRow(ManageLinksActivity r0) {
        return r0.adminsStartRow;
    }

    /* renamed from: -$$Nest$fgetcanEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16062$$Nest$fgetcanEdit(ManageLinksActivity r0) {
        return r0.canEdit;
    }

    /* renamed from: -$$Nest$fgetcreateLinkHelpRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16063$$Nest$fgetcreateLinkHelpRow(ManageLinksActivity r0) {
        return r0.createLinkHelpRow;
    }

    /* renamed from: -$$Nest$fgetcreateNewLinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16064$$Nest$fgetcreateNewLinkRow(ManageLinksActivity r0) {
        return r0.createNewLinkRow;
    }

    /* renamed from: -$$Nest$fgetcreatorDividerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16065$$Nest$fgetcreatorDividerRow(ManageLinksActivity r0) {
        return r0.creatorDividerRow;
    }

    /* renamed from: -$$Nest$fgetcreatorRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16066$$Nest$fgetcreatorRow(ManageLinksActivity r0) {
        return r0.creatorRow;
    }

    /* renamed from: -$$Nest$fgetcurrentChat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m16067$$Nest$fgetcurrentChat(ManageLinksActivity r0) {
        return r0.currentChat;
    }

    /* renamed from: -$$Nest$fgetcurrentChatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m16068$$Nest$fgetcurrentChatId(ManageLinksActivity r2) {
        return r2.currentChatId;
    }

    /* renamed from: -$$Nest$fgetdividerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16069$$Nest$fgetdividerRow(ManageLinksActivity r0) {
        return r0.dividerRow;
    }

    /* renamed from: -$$Nest$fgethelpRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16070$$Nest$fgethelpRow(ManageLinksActivity r0) {
        return r0.helpRow;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m16071$$Nest$fgetinfo(ManageLinksActivity r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetinvite, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_chatInviteExported m16072$$Nest$fgetinvite(ManageLinksActivity r0) {
        return r0.invite;
    }

    /* renamed from: -$$Nest$fgetinviteLinkBottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InviteLinkBottomSheet m16073$$Nest$fgetinviteLinkBottomSheet(ManageLinksActivity r0) {
        return r0.inviteLinkBottomSheet;
    }

    /* renamed from: -$$Nest$fgetinvites, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16074$$Nest$fgetinvites(ManageLinksActivity r0) {
        return r0.invites;
    }

    /* renamed from: -$$Nest$fgetinvitesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16075$$Nest$fgetinvitesCount(ManageLinksActivity r0) {
        return r0.invitesCount;
    }

    /* renamed from: -$$Nest$fgetisChannel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16076$$Nest$fgetisChannel(ManageLinksActivity r0) {
        return r0.isChannel;
    }

    /* renamed from: -$$Nest$fgetisPublic, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16077$$Nest$fgetisPublic(ManageLinksActivity r0) {
        return r0.isPublic;
    }

    /* renamed from: -$$Nest$fgetlastDivider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16078$$Nest$fgetlastDivider(ManageLinksActivity r0) {
        return r0.lastDivider;
    }

    /* renamed from: -$$Nest$fgetlinksEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16079$$Nest$fgetlinksEndRow(ManageLinksActivity r0) {
        return r0.linksEndRow;
    }

    /* renamed from: -$$Nest$fgetlinksHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16080$$Nest$fgetlinksHeaderRow(ManageLinksActivity r0) {
        return r0.linksHeaderRow;
    }

    /* renamed from: -$$Nest$fgetlinksInfoRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16081$$Nest$fgetlinksInfoRow(ManageLinksActivity r0) {
        return r0.linksInfoRow;
    }

    /* renamed from: -$$Nest$fgetlinksLoadingRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16082$$Nest$fgetlinksLoadingRow(ManageLinksActivity r0) {
        return r0.linksLoadingRow;
    }

    /* renamed from: -$$Nest$fgetlinksStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16083$$Nest$fgetlinksStartRow(ManageLinksActivity r0) {
        return r0.linksStartRow;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m16084$$Nest$fgetlistView(ManageLinksActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetpermanentLinkHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16085$$Nest$fgetpermanentLinkHeaderRow(ManageLinksActivity r0) {
        return r0.permanentLinkHeaderRow;
    }

    /* renamed from: -$$Nest$fgetpermanentLinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16086$$Nest$fgetpermanentLinkRow(ManageLinksActivity r0) {
        return r0.permanentLinkRow;
    }

    /* renamed from: -$$Nest$fgetrevokeAllDivider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16087$$Nest$fgetrevokeAllDivider(ManageLinksActivity r0) {
        return r0.revokeAllDivider;
    }

    /* renamed from: -$$Nest$fgetrevokeAllRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16088$$Nest$fgetrevokeAllRow(ManageLinksActivity r0) {
        return r0.revokeAllRow;
    }

    /* renamed from: -$$Nest$fgetrevokedDivider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16089$$Nest$fgetrevokedDivider(ManageLinksActivity r0) {
        return r0.revokedDivider;
    }

    /* renamed from: -$$Nest$fgetrevokedHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16090$$Nest$fgetrevokedHeader(ManageLinksActivity r0) {
        return r0.revokedHeader;
    }

    /* renamed from: -$$Nest$fgetrevokedInvites, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16091$$Nest$fgetrevokedInvites(ManageLinksActivity r0) {
        return r0.revokedInvites;
    }

    /* renamed from: -$$Nest$fgetrevokedLinksEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16092$$Nest$fgetrevokedLinksEndRow(ManageLinksActivity r0) {
        return r0.revokedLinksEndRow;
    }

    /* renamed from: -$$Nest$fgetrevokedLinksStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16093$$Nest$fgetrevokedLinksStartRow(ManageLinksActivity r0) {
        return r0.revokedLinksStartRow;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16094$$Nest$fgetrowCount(ManageLinksActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgetusers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m16095$$Nest$fgetusers(ManageLinksActivity r0) {
        return r0.users;
    }

    /* renamed from: -$$Nest$fputinviteLinkBottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16096$$Nest$fputinviteLinkBottomSheet(ManageLinksActivity r0, InviteLinkBottomSheet r1) {
        r0.inviteLinkBottomSheet = r1;
    }

    /* renamed from: -$$Nest$mloadLinks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16097$$Nest$mloadLinks(ManageLinksActivity r0, boolean r1) {
        r0.loadLinks(r1);
    }

    /* renamed from: -$$Nest$mrevokePermanent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16098$$Nest$mrevokePermanent(ManageLinksActivity r0) {
        r0.revokePermanent();
    }

    /* renamed from: -$$Nest$msaveListState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DiffCallback m16099$$Nest$msaveListState(ManageLinksActivity r0) {
        return r0.saveListState();
    }

    /* renamed from: -$$Nest$mupdateRecyclerViewAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16100$$Nest$mupdateRecyclerViewAnimated(ManageLinksActivity r0, DiffCallback r1) {
        r0.updateRecyclerViewAnimated(r1);
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16101$$Nest$mupdateRows(ManageLinksActivity r0, boolean r1) {
        r0.updateRows(r1);
    }

    public ManageLinksActivity(long r4, long r6, int r8) {
        this.invites = new ArrayList();
        this.revokedInvites = new ArrayList();
        this.users = new HashMap();
        this.admins = new ArrayList();
        this.updateTimerRunnable = new AnonymousClass1(this);
        boolean r0 = false;
        this.loadRevoked = false;
        this.linkEditActivityCallback = new AnonymousClass6(this);
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.currentChatId = r4;
        this.invitesCount = r8;
        TLRPC.Chat r42 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r4));
        this.currentChat = r42;
        if (ChatObject.isChannel(r42) == true) goto L5;
    L7:
        boolean r43 = false;
    L8:
        this.isChannel = r43;
        if (r6 != 0) goto L11;
        this.adminId = getAccountInstance().getUserConfig().clientUserId;
    L12:
        TLRPC.User r44 = getMessagesController().getUser(Long.valueOf(this.adminId));
        if (this.adminId == getAccountInstance().getUserConfig().clientUserId) goto L17;
        if (r44 != null) goto L16;
    L18:
        this.canEdit = r0;
        return;
    L16:
        if (r44.bot == true) goto L18;
    L17:
        r0 = true;
        goto L18
    L11:
        this.adminId = r6;
        goto L12
    L5:
        if (this.currentChat.megagroup == true) goto L7;
        r43 = true;
        goto L8
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(ManageLinksActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(ManageLinksActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ boolean access$200(ManageLinksActivity r0) {
        return r0.isPaused;
    }

    public static /* synthetic */ int access$300(ManageLinksActivity r0) {
        return r0.currentAccount;
    }

    private /* synthetic */ boolean lambda$createView$10(View r3, int r4) {
        if (r4 < this.linksStartRow) goto L7;
        if (r4 >= this.linksEndRow) goto L7;
    L10:
        ((LinkCell) r3).optionsView.callOnClick();
        r3.performHapticFeedback(0, 2);     // Catch: Exception -> L15
        return true;
    L18:
        return true;
    L7:
        if (r4 >= this.revokedLinksStartRow) goto L9;
    L14:
        return false;
    L9:
        if (r4 >= this.revokedLinksEndRow) goto L14;
        goto L10
    }

    private /* synthetic */ void lambda$createView$6(TLRPC.TL_error r2) {
        this.deletingRevokedLinks = false;
        if (r2 != null) goto L6;
        DiffCallback r22 = saveListState();
        this.revokedInvites.clear();
        updateRecyclerViewAnimated(r22);
        return;
    }

    private /* synthetic */ void lambda$createView$7(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ManageLinksActivity$$ExternalSyntheticLambda11(this, r2));
    }

    private /* synthetic */ void lambda$createView$8(AlertDialog r5, int r6) {
        TLRPC.TL_messages_deleteRevokedExportedChatInvites r52 = new TLRPC.TL_messages_deleteRevokedExportedChatInvites();
        r52.peer = getMessagesController().getInputPeer(-this.currentChatId);
        if (this.adminId != getUserConfig().getClientUserId()) goto L5;
        r52.admin_id = getMessagesController().getInputUser(getUserConfig().getCurrentUser());
    L6:
        this.deletingRevokedLinks = true;
        getConnectionsManager().sendRequest(r52, new ManageLinksActivity$$ExternalSyntheticLambda10(this));
        return;
    L5:
        r52.admin_id = getMessagesController().getInputUser(this.adminId);
        goto L6
    }

    private /* synthetic */ void lambda$createView$9(Context r13, View r14, int r15) {
        if (r15 != this.creatorRow) goto L9;
        TLRPC.User r0 = this.users.get(Long.valueOf(this.invite.admin_id));
        if (r0 == null) goto L40;
        Bundle r1 = new Bundle();
        r1.putLong("user_id", r0.id);
        MessagesController.getInstance(UserConfig.selectedAccount).putUser(r0, false);
        presentFragment(new ProfileActivity(r1));
        return;
    L40:
        return;
    L9:
        if (r15 != this.createNewLinkRow) goto L12;
        LinkEditActivity r02 = new LinkEditActivity(0, this.currentChatId);
        r02.setCallback(this.linkEditActivityCallback);
        presentFragment(r02);
        return;
    L12:
        int r12 = this.linksStartRow;
        if (r15 >= r12) goto L15;
    L18:
        int r16 = this.revokedLinksStartRow;
        if (r15 < r16) goto L25;
        if (r15 >= this.revokedLinksEndRow) goto L25;
        InviteLinkBottomSheet r03 = new InviteLinkBottomSheet(r13, this.revokedInvites.get(r15 - r16), this.info, this.users, this, this.currentChatId, false, this.isChannel);
        this.inviteLinkBottomSheet = r03;
        r03.show();
        return;
    L25:
        if (r15 == this.revokeAllRow) goto L27;
        int r17 = this.adminsStartRow;
        if (r15 >= r17) goto L34;
        return;
    L34:
        if (r15 >= this.adminsEndRow) goto L42;
        int r04 = r15 - r17;
        TLRPC.TL_chatAdminWithInvites r05 = this.admins.get(r04);
        if (this.users.containsKey(Long.valueOf(r05.admin_id)) == false) goto L38;
        getMessagesController().putUser(this.users.get(Long.valueOf(r05.admin_id)), false);
    L38:
        ManageLinksActivity r6 = new ManageLinksActivity(this.currentChatId, r05.admin_id, r05.invites_count);
        r6.setInfo(this.info, null);
        presentFragment(r6);
        return;
    L42:
        return;
    L27:
        if (this.deletingRevokedLinks == true) goto L43;
        AlertDialog.Builder r06 = new AlertDialog.Builder(getParentActivity());
        r06.setTitle(LocaleController.getString(R.string.DeleteAllRevokedLinks));
        r06.setMessage(LocaleController.getString(R.string.DeleteAllRevokedLinkHelp));
        r06.setPositiveButton(LocaleController.getString(R.string.Delete), new ManageLinksActivity$$ExternalSyntheticLambda6(this));
        r06.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r06.create());
        return;
    L43:
        return;
    L15:
        if (r15 >= this.linksEndRow) goto L18;
        InviteLinkBottomSheet r07 = new InviteLinkBottomSheet(r13, this.invites.get(r15 - r12), this.info, this.users, this, this.currentChatId, false, this.isChannel);
        this.inviteLinkBottomSheet = r07;
        r07.setCanEdit(this.canEdit);
        this.inviteLinkBottomSheet.show();
    }

    private /* synthetic */ void lambda$deleteLink$13(TLRPC.TL_error r1, TLRPC.TL_chatInviteExported r2) {
        if (r1 != null) goto L5;
        this.linkEditActivityCallback.onLinkRemoved(r2);
        return;
    }

    private /* synthetic */ void lambda$deleteLink$14(TLRPC.TL_chatInviteExported r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ManageLinksActivity$$ExternalSyntheticLambda17(this, r3, r1));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$17() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L13;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L13;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof ManageChatUserCell) == false) goto L10;
        ((ManageChatUserCell) r3).update(0);
    L10:
        if ((r3 instanceof LinkActionView) == false) goto L12;
        ((LinkActionView) r3).updateColors();
    L12:
        r2 = r2 + 1;
    L13:
        InviteLinkBottomSheet r5 = this.inviteLinkBottomSheet;
        if (r5 == null) goto L20;
        r5.updateColors();
        return;
    }

    private /* synthetic */ void lambda$loadLinks$0(TLRPC.TL_error r7, TLObject r8) {
        this.linksLoading = false;
        if (r7 != null) goto L15;
        TLRPC.TL_messages_chatAdminsWithInvites r82 = (TLRPC.TL_messages_chatAdminsWithInvites) r8;
        int r72 = 0;
    L6:
        if (r72 >= r82.admins.size()) goto L11;
        TLRPC.TL_chatAdminWithInvites r1 = r82.admins.get(r72);
        if (r1.admin_id == getAccountInstance().getUserConfig().clientUserId) goto L10;
        this.admins.add(r1);
    L10:
        r72 = r72 + 1;
        goto L6
    L11:
        int r73 = 0;
    L13:
        if (r73 >= r82.users.size()) goto L15;
        TLRPC.User r12 = r82.users.get(r73);
        this.users.put(Long.valueOf(r12.id), r12);
        r73 = r73 + 1;
    L15:
        int r74 = this.rowCount;
        this.adminsLoaded = true;
        this.hasMore = false;
        if (this.admins.size() <= 0) goto L25;
        RecyclerItemsEnterAnimator r13 = this.recyclerItemsEnterAnimator;
        if (r13 == null) goto L25;
        if (this.isPaused == true) goto L25;
        if (this.isOpened == false) goto L25;
        r13.showItemsAnimated(r74 + 1);
    L25:
        if (this.hasMore == true) goto L27;
    L28:
        resumeDelayedFragmentAnimation();
    L30:
        if (this.hasMore == false) goto L32;
    L34:
        updateRows(true);
        return;
    L32:
        if (this.loadRevoked == true) goto L34;
        this.hasMore = true;
        this.loadRevoked = true;
        loadLinks(false);
        goto L34
    L27:
        if (((this.invites.size() + this.revokedInvites.size()) + this.admins.size()) < 5) goto L30;
        goto L28
    }

    private /* synthetic */ void lambda$loadLinks$1(TLRPC.TL_error r3, TLObject r4) {
        getNotificationCenter().doOnIdle(new ManageLinksActivity$$ExternalSyntheticLambda9(this, r3, r4));
    }

    private /* synthetic */ void lambda$loadLinks$2(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ManageLinksActivity$$ExternalSyntheticLambda7(this, r3, r2));
    }

    private /* synthetic */ void lambda$loadLinks$3(TLRPC.TL_chatInviteExported r7, TLRPC.TL_error r8, TLObject r9, boolean r10) {
        DiffCallback r0 = saveListState();
        this.linksLoading = false;
        this.hasMore = false;
        if (r7 == null) goto L8;
        this.invite = r7;
        TLRPC.ChatFull r2 = this.info;
        if (r2 == null) goto L8;
        r2.exported_invite = r7;
    L8:
        if (r8 != null) goto L59;
        TLRPC.TL_messages_exportedChatInvites r92 = (TLRPC.TL_messages_exportedChatInvites) r9;
        if (r10 == false) goto L16;
        int r82 = 0;
    L13:
        if (r82 >= r92.invites.size()) goto L26;
        TLRPC.TL_chatInviteExported r22 = (TLRPC.TL_chatInviteExported) r92.invites.get(r82);
        fixDate(r22);
        this.revokedInvites.add(r22);
        r82 = r82 + 1;
    L26:
        int r83 = 0;
    L28:
        if (r83 >= r92.users.size()) goto L30;
        this.users.put(Long.valueOf(r92.users.get(r83).id), r92.users.get(r83));
        r83 = r83 + 1;
        goto L28
    L30:
        int r84 = this.rowCount;
        if (r92.invites.size() != 0) goto L33;
        this.hasMore = false;
    L45:
        if (r92.invites.size() > 0) goto L47;
    L54:
        boolean r85 = true;
    L55:
        TLRPC.ChatFull r23 = this.info;
        if (r23 == null) goto L61;
        if (r10 == true) goto L61;
        r23.invitesCount = r92.count;
        getMessagesStorage().saveChatLinksCount(this.currentChatId, this.info.invitesCount);
    L61:
        if (this.hasMore == true) goto L69;
        if (this.loadRevoked == true) goto L69;
        if (this.adminId != getAccountInstance().getUserConfig().clientUserId) goto L69;
        this.hasMore = true;
        this.loadAdmins = true;
    L67:
        boolean r93 = true;
    L75:
        if (this.hasMore == true) goto L77;
    L78:
        resumeDelayedFragmentAnimation();
    L79:
        if (r93 == false) goto L81;
        loadLinks(false);
    L81:
        if (r85 == true) goto L83;
    L88:
        updateRows(true);
        return;
    L83:
        if (this.listViewAdapter == null) goto L88;
        if (this.listView.getChildCount() <= 0) goto L88;
        updateRecyclerViewAnimated(r0);
        return;
    L77:
        if (((this.invites.size() + this.revokedInvites.size()) + this.admins.size()) < 5) goto L79;
    L69:
        if (this.hasMore == false) goto L71;
    L73:
        r93 = false;
        goto L75
    L71:
        if (this.loadRevoked == true) goto L73;
        this.hasMore = true;
        this.loadRevoked = true;
        goto L67
    L47:
        if (this.isOpened == false) goto L54;
        RecyclerItemsEnterAnimator r24 = this.recyclerItemsEnterAnimator;
        if (r24 != null) goto L51;
    L53:
        r85 = false;
        goto L55
    L51:
        if (this.isPaused == true) goto L53;
        r24.showItemsAnimated(r84 + 1);
        goto L53
    L33:
        if (r10 == false) goto L40;
        if ((this.revokedInvites.size() + 1) >= r92.count) goto L37;
        boolean r25 = true;
    L38:
        this.hasMore = r25;
        goto L45
    L37:
        r25 = false;
        goto L38
    L40:
        if ((this.invites.size() + 1) >= r92.count) goto L42;
        boolean r26 = true;
    L43:
        this.hasMore = r26;
        goto L45
    L42:
        r26 = false;
        goto L43
    L16:
        if (this.adminId != getAccountInstance().getUserConfig().clientUserId) goto L18;
    L22:
        int r86 = 0;
    L24:
        if (r86 >= r92.invites.size()) goto L26;
        TLRPC.TL_chatInviteExported r27 = (TLRPC.TL_chatInviteExported) r92.invites.get(r86);
        fixDate(r27);
        this.invites.add(r27);
        r86 = r86 + 1;
        goto L24
    L18:
        if (this.invites.size() != 0) goto L22;
        if (r92.invites.size() <= 0) goto L22;
        this.invite = (TLRPC.TL_chatInviteExported) r92.invites.get(0);
        r92.invites.remove(0);
        goto L22
    L59:
        this.hasMore = false;
        r85 = false;
        goto L61
    }

    private /* synthetic */ void lambda$loadLinks$4(TLRPC.TL_chatInviteExported r8, TLRPC.TL_error r9, TLObject r10, boolean r11) {
        getNotificationCenter().doOnIdle(new ManageLinksActivity$$ExternalSyntheticLambda8(this, r8, r9, r10, r11));
    }

    private /* synthetic */ void lambda$loadLinks$5(TLRPC.TL_chatInviteExported r7, boolean r8, TLObject r9, TLRPC.TL_error r10) {
        if (r10 != null) goto L14;
        TLRPC.TL_messages_exportedChatInvites r0 = (TLRPC.TL_messages_exportedChatInvites) r9;
        if (r0.invites.size() <= 0) goto L14;
        if (r7 == null) goto L14;
        int r1 = 0;
    L8:
        if (r1 >= r0.invites.size()) goto L14;
        if (((TLRPC.TL_chatInviteExported) r0.invites.get(r1)).link.equals(r7.link) == true) goto L11;
        r1 = r1 + 1;
        goto L8
    L11:
        TLRPC.TL_chatInviteExported r72 = (TLRPC.TL_chatInviteExported) r0.invites.remove(r1);
    L15:
        AndroidUtilities.runOnUIThread(new ManageLinksActivity$$ExternalSyntheticLambda5(this, r72, r10, r9, r8));
        return;
    L14:
        r72 = null;
        goto L15
    }

    private /* synthetic */ void lambda$revokeLink$15(TLRPC.TL_error r7, TLObject r8, TLRPC.TL_chatInviteExported r9) {
        if (r7 == null) goto L4;
        return;
    L4:
        if ((r8 instanceof TLRPC.TL_messages_exportedChatInviteReplaced) == false) goto L17;
        TLRPC.TL_messages_exportedChatInviteReplaced r82 = (TLRPC.TL_messages_exportedChatInviteReplaced) r8;
        if (this.isPublic == true) goto L8;
        this.invite = (TLRPC.TL_chatInviteExported) r82.new_invite;
    L8:
        r9.revoked = true;
        DiffCallback r72 = saveListState();
        if (this.isPublic == false) goto L14;
        if (this.adminId != getAccountInstance().getUserConfig().getClientUserId()) goto L14;
        this.invites.remove(r9);
        this.invites.add(0, (TLRPC.TL_chatInviteExported) r82.new_invite);
    L16:
        this.revokedInvites.add(0, r9);
        updateRecyclerViewAnimated(r72);
    L24:
        if (getParentActivity() == null) goto L28;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.InviteRevokedHint)).show();
        return;
    L28:
        return;
    L14:
        if (this.invite == null) goto L16;
        this.invite = (TLRPC.TL_chatInviteExported) r82.new_invite;
        goto L16
    L17:
        this.linkEditActivityCallback.onLinkEdited(r9, r8);
        TLRPC.ChatFull r73 = this.info;
        if (r73 == null) goto L24;
        int r83 = r73.invitesCount - 1;
        r73.invitesCount = r83;
        if (r83 >= 0) goto L22;
        r73.invitesCount = 0;
    L22:
        getMessagesStorage().saveChatLinksCount(this.currentChatId, this.info.invitesCount);
        goto L24
    }

    private /* synthetic */ void lambda$revokeLink$16(TLRPC.TL_chatInviteExported r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ManageLinksActivity$$ExternalSyntheticLambda16(this, r4, r3, r2));
    }

    private /* synthetic */ void lambda$revokePermanent$11(TLRPC.TL_error r2, TLObject r3, TLRPC.TL_chatInviteExported r4) {
        if (r2 != null) goto L11;
        TLRPC.TL_chatInviteExported r32 = (TLRPC.TL_chatInviteExported) r3;
        this.invite = r32;
        TLRPC.ChatFull r22 = this.info;
        if (r22 == null) goto L7;
        r22.exported_invite = r32;
    L7:
        if (getParentActivity() == null) goto L12;
        r4.revoked = true;
        DiffCallback r23 = saveListState();
        this.revokedInvites.add(0, r4);
        updateRecyclerViewAnimated(r23);
        BulletinFactory.of(this).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.InviteRevokedHint)).show();
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$revokePermanent$12(TLRPC.TL_chatInviteExported r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new ManageLinksActivity$$ExternalSyntheticLambda15(this, r4, r3, r2));
    }

    private void loadLinks(boolean r7) {
        if (this.loadAdmins == true) goto L5;
    L7:
        TLRPC.TL_messages_getExportedChatInvites r0 = new TLRPC.TL_messages_getExportedChatInvites();
        r0.peer = getMessagesController().getInputPeer(-this.currentChatId);
        if (this.adminId != getUserConfig().getClientUserId()) goto L10;
        r0.admin_id = getMessagesController().getInputUser(getUserConfig().getCurrentUser());
    L11:
        boolean r2 = this.loadRevoked;
        if (r2 == false) goto L17;
        r0.revoked = true;
        if (this.revokedInvites.isEmpty() == true) goto L19;
        r0.flags |= 4;
        ArrayList<TLRPC.TL_chatInviteExported> r3 = this.revokedInvites;
        r0.offset_link = r3.get(r3.size() - 1).link;
        ArrayList<TLRPC.TL_chatInviteExported> r32 = this.revokedInvites;
        r0.offset_date = r32.get(r32.size() - 1).date;
    L19:
        this.linksLoading = true;
        if (this.isPublic == false) goto L22;
        TLRPC.TL_chatInviteExported r33 = null;
    L23:
        getConnectionsManager().bindRequestToGuid(getConnectionsManager().sendRequest(r0, new ManageLinksActivity$$ExternalSyntheticLambda1(this, r33, r2)), getClassGuid());
    L24:
        if (r7 == false) goto L27;
        updateRows(true);
        return;
    L27:
        return;
    L22:
        r33 = this.invite;
        goto L23
    L17:
        if (this.invites.isEmpty() == true) goto L19;
        r0.flags |= 4;
        ArrayList<TLRPC.TL_chatInviteExported> r34 = this.invites;
        r0.offset_link = r34.get(r34.size() - 1).link;
        ArrayList<TLRPC.TL_chatInviteExported> r35 = this.invites;
        r0.offset_date = r35.get(r35.size() - 1).date;
        goto L19
    L10:
        r0.admin_id = getMessagesController().getInputUser(this.adminId);
        goto L11
    L5:
        if (this.adminsLoaded == true) goto L7;
        this.linksLoading = true;
        TLRPC.TL_messages_getAdminsWithInvites r02 = new TLRPC.TL_messages_getAdminsWithInvites();
        r02.peer = getMessagesController().getInputPeer(-this.currentChatId);
        getConnectionsManager().bindRequestToGuid(getConnectionsManager().sendRequest(r02, new ManageLinksActivity$$ExternalSyntheticLambda0(this)), getClassGuid());
        goto L24
    }

    private void revokePermanent() {
        if (this.adminId != getAccountInstance().getUserConfig().clientUserId) goto L6;
        TLRPC.TL_messages_exportChatInvite r0 = new TLRPC.TL_messages_exportChatInvite();
        r0.peer = getMessagesController().getInputPeer(-this.currentChatId);
        r0.legacy_revoke_permanent = true;
        TLRPC.TL_chatInviteExported r1 = this.invite;
        this.invite = null;
        this.info.exported_invite = null;
        int r02 = getConnectionsManager().sendRequest(r0, new ManageLinksActivity$$ExternalSyntheticLambda12(this, r1));
        AndroidUtilities.updateVisibleRows(this.listView);
        getConnectionsManager().bindRequestToGuid(r02, this.classGuid);
        return;
    L6:
        revokeLink(this.invite);
    }

    private DiffCallback saveListState() {
        DiffCallback r0 = new DiffCallback(this, null);
        r0.fillPositions(r0.oldPositionToItem);
        r0.oldLinksStartRow = this.linksStartRow;
        r0.oldLinksEndRow = this.linksEndRow;
        r0.oldRevokedLinksStartRow = this.revokedLinksStartRow;
        r0.oldRevokedLinksEndRow = this.revokedLinksEndRow;
        r0.oldAdminsStartRow = this.adminsStartRow;
        r0.oldAdminsEndRow = this.adminsEndRow;
        r0.oldRowCount = this.rowCount;
        r0.oldLinks.clear();
        r0.oldLinks.addAll(this.invites);
        r0.oldRevokedLinks.clear();
        r0.oldRevokedLinks.addAll(this.revokedInvites);
        return r0;
    }

    private void updateRecyclerViewAnimated(DiffCallback r2) {
        if (this.isPaused == false) goto L5;
    L11:
        updateRows(true);
        return;
    L5:
        if (this.listViewAdapter == null) goto L11;
        if (this.listView == null) goto L11;
        updateRows(false);
        r2.fillPositions(r2.newPositionToItem);
        DiffUtil.calculateDiff(r2).dispatchUpdatesTo(this.listViewAdapter);
        AndroidUtilities.updateVisibleRows(this.listView);
    }

    private void updateRows(boolean r7) {
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.currentChatId));
        this.currentChat = r0;
        if (r0 == null) goto L88;
        this.creatorRow = -1;
        this.creatorDividerRow = -1;
        this.linksStartRow = -1;
        this.linksEndRow = -1;
        this.linksLoadingRow = -1;
        this.revokedLinksStartRow = -1;
        this.revokedLinksEndRow = -1;
        this.revokedHeader = -1;
        this.revokedDivider = -1;
        this.lastDivider = -1;
        this.revokeAllRow = -1;
        this.revokeAllDivider = -1;
        this.createLinkHelpRow = -1;
        this.helpRow = -1;
        this.createNewLinkRow = -1;
        this.adminsEndRow = -1;
        this.adminsStartRow = -1;
        this.adminsDividerRow = -1;
        this.adminsHeaderRow = -1;
        this.linksHeaderRow = -1;
        this.dividerRow = -1;
        this.linksInfoRow = -1;
        boolean r1 = false;
        this.rowCount = 0;
        if (this.adminId == getAccountInstance().getUserConfig().clientUserId) goto L8;
        r1 = true;
    L8:
        int r2 = this.rowCount;
        if (r1 == false) goto L11;
        int r3 = r2 + 1;
        this.creatorRow = r2;
        this.rowCount = r2 + 2;
        this.creatorDividerRow = r3;
    L12:
        int r22 = this.rowCount;
        this.permanentLinkHeaderRow = r22;
        int r4 = r22 + 2;
        this.rowCount = r4;
        this.permanentLinkRow = r22 + 1;
        if (r1 == true) goto L16;
        int r32 = r22 + 3;
        this.dividerRow = r4;
        this.rowCount = r22 + 4;
        this.createNewLinkRow = r32;
    L19:
        if (this.invites.isEmpty() == true) goto L21;
        int r23 = this.rowCount;
        this.linksStartRow = r23;
        int r24 = r23 + this.invites.size();
        this.rowCount = r24;
        this.linksEndRow = r24;
    L21:
        if (r1 == false) goto L23;
    L33:
        if (r1 == true) goto L45;
        if (this.admins.size() <= 0) goto L45;
        if (this.invites.isEmpty() == false) goto L41;
        if (this.createNewLinkRow >= 0) goto L41;
    L43:
        int r25 = this.rowCount;
        int r33 = r25 + 1;
        this.rowCount = r33;
        this.adminsHeaderRow = r25;
        this.adminsStartRow = r33;
        int r34 = r33 + this.admins.size();
        this.rowCount = r34;
        this.adminsEndRow = r34;
    L41:
        if (this.createLinkHelpRow != (-1)) goto L43;
        int r26 = this.rowCount;
        this.rowCount = r26 + 1;
        this.adminsDividerRow = r26;
    L45:
        if (this.revokedInvites.isEmpty() == true) goto L62;
        if (this.adminsStartRow < 0) goto L50;
        int r02 = this.rowCount;
        this.rowCount = r02 + 1;
        this.revokedDivider = r02;
    L60:
        int r03 = this.rowCount;
        int r27 = r03 + 1;
        this.rowCount = r27;
        this.revokedHeader = r03;
        this.revokedLinksStartRow = r27;
        int r28 = r27 + this.revokedInvites.size();
        this.revokedLinksEndRow = r28;
        int r04 = r28 + 1;
        this.revokeAllDivider = r28;
        this.rowCount = r28 + 2;
        this.revokeAllRow = r04;
        goto L62
    L50:
        if (this.invites.isEmpty() == false) goto L54;
        if (this.createNewLinkRow >= 0) goto L54;
    L56:
        if (r1 == false) goto L60;
        if (this.linksStartRow != (-1)) goto L60;
        int r05 = this.rowCount;
        this.rowCount = r05 + 1;
        this.revokedDivider = r05;
    L54:
        if (this.createLinkHelpRow != (-1)) goto L56;
        int r06 = this.rowCount;
        this.rowCount = r06 + 1;
        this.revokedDivider = r06;
    L62:
        if (this.loadAdmins == true) goto L72;
        if (this.loadRevoked == true) goto L72;
        if (this.linksLoading == false) goto L68;
    L69:
        if (r1 == true) goto L72;
        int r07 = this.rowCount;
        this.rowCount = r07 + 1;
        this.linksLoadingRow = r07;
        goto L72
    L68:
        if (this.hasMore == true) goto L69;
    L72:
        if (this.invites.isEmpty() == true) goto L77;
        int r08 = this.linksEndRow;
        int r12 = this.rowCount;
        if (r08 != r12) goto L77;
        this.rowCount = r12 + 1;
        this.linksInfoRow = r12;
    L81:
        ListAdapter r6 = this.listViewAdapter;
        if (r6 == null) goto L86;
        if (r7 == false) goto L87;
        r6.notifyDataSetChanged();
        return;
    L87:
        return;
    L86:
        return;
    L77:
        if (this.invites.isEmpty() == true) goto L79;
    L80:
        int r09 = this.rowCount;
        this.rowCount = r09 + 1;
        this.lastDivider = r09;
        goto L81
    L79:
        if (this.revokedInvites.isEmpty() == true) goto L81;
    L23:
        if (this.invites.isEmpty() == false) goto L33;
        if (this.createNewLinkRow < 0) goto L33;
        if (this.linksLoading == true) goto L29;
    L32:
        int r29 = this.rowCount;
        this.rowCount = r29 + 1;
        this.createLinkHelpRow = r29;
        goto L33
    L29:
        if (this.loadAdmins == true) goto L32;
        if (this.loadRevoked == false) goto L33;
    L16:
        if (this.invites.isEmpty() == true) goto L19;
        int r210 = this.rowCount;
        int r35 = r210 + 1;
        this.dividerRow = r210;
        this.rowCount = r210 + 2;
        this.linksHeaderRow = r35;
        goto L19
    L11:
        this.rowCount = r2 + 1;
        this.helpRow = r2;
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r7) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.InviteLinks));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        AnonymousClass3 r0 = new AnonymousClass3(this, r7);
        this.fragmentView = r0;
        int r2 = Theme.key_windowBackgroundGray;
        r0.setBackgroundColor(Theme.getColor(r2));
        this.fragmentView.setTag(Integer.valueOf(r2));
        FrameLayout r02 = (FrameLayout) this.fragmentView;
        RecyclerListView r22 = new RecyclerListView(r7);
        this.listView = r22;
        r22.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        AnonymousClass4 r23 = new AnonymousClass4(this, r7, 1, false);
        this.listView.setLayoutManager(r23);
        RecyclerListView r4 = this.listView;
        ListAdapter r5 = new ListAdapter(this, r7);
        this.listViewAdapter = r5;
        r4.setAdapter(r5);
        this.listView.setOnScrollListener(new AnonymousClass5(this, r23));
        this.recyclerItemsEnterAnimator = new RecyclerItemsEnterAnimator(this.listView, false);
        DefaultItemAnimator r24 = new DefaultItemAnimator();
        r24.setDurations(420);
        r24.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r24.setDelayAnimations(false);
        r24.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r24);
        RecyclerListView r25 = this.listView;
        if (LocaleController.isRTL == false) goto L5;
        int r3 = 1;
    L6:
        r25.setVerticalScrollbarPosition(r3);
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setOnItemClickListener(new ManageLinksActivity$$ExternalSyntheticLambda3(this, r7));
        this.listView.setOnItemLongClickListener(new ManageLinksActivity$$ExternalSyntheticLambda4(this));
        this.linkIcon = ContextCompat.getDrawable(r7, R.drawable.msg_link_1);
        this.linkIconRevoked = ContextCompat.getDrawable(r7, R.drawable.msg_link_2);
        this.linkIconRevenue = ContextCompat.getDrawable(r7, R.drawable.large_income);
        this.linkIcon.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        updateRows(true);
        this.timeDif = getConnectionsManager().getCurrentTime() - (System.currentTimeMillis() / 1000);
        return this.fragmentView;
    L5:
        r3 = 2;
        goto L6
    }

    public void deleteLink(TLRPC.TL_chatInviteExported r5) {
        TLRPC.TL_messages_deleteExportedChatInvite r0 = new TLRPC.TL_messages_deleteExportedChatInvite();
        r0.link = r5.link;
        r0.peer = getMessagesController().getInputPeer(-this.currentChatId);
        getConnectionsManager().sendRequest(r0, new ManageLinksActivity$$ExternalSyntheticLambda13(this, r5));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.dialogDeleted) goto L5;
        return;
    L5:
        if (((Long) r5[0]).longValue() != (-this.currentChatId)) goto L15;
        INavigationLayout r32 = this.parentLayout;
        if (r32 != null) goto L9;
    L12:
        removeSelfFromStack();
        return;
    L9:
        if (r32.getLastFragment() != this) goto L12;
        finishFragment();
        return;
    }

    public void editLink(TLRPC.TL_chatInviteExported r5) {
        LinkEditActivity r0 = new LinkEditActivity(1, this.currentChatId);
        r0.setCallback(this.linkEditActivityCallback);
        r0.setInviteToEdit(r5);
        presentFragment(r0);
    }

    public void fixDate(TLRPC.TL_chatInviteExported r4) {
        boolean r1 = false;
        if (r4.expire_date > 0) goto L5;
        int r3 = r4.usage_limit;
        if (r3 > 0) goto L12;
        return;
    L12:
        if (r4.usage < r3) goto L14;
        r1 = true;
    L14:
        r4.expired = r1;
        return;
    L5:
        if (getConnectionsManager().getCurrentTime() < r4.expire_date) goto L7;
        r1 = true;
    L7:
        r4.expired = r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ManageLinksActivity$$ExternalSyntheticLambda2 r8 = new ManageLinksActivity$$ExternalSyntheticLambda2(this);
        int r16 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{HeaderCell.class, CreationTextCell.class, LinkActionView.class, LinkCell.class}, null, null, null, r16));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        int r27 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"nameTextView"}, null, null, null, r27));
        int r21 = Theme.key_windowBackgroundWhiteGrayText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusColor"}, null, null, r8, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ManageChatUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HintInnerCell.class}, new String[]{"messageTextView"}, null, null, null, Theme.key_chats_message));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, r27));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_chats_unreadCounterMuted));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueButton));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueIcon));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CreationTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText2));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{CreationTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_switchTrackChecked));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{CreationTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_checkboxCheck));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LinkCell.class}, new String[]{"titleView"}, null, null, null, r27));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LinkCell.class}, new String[]{"subtitleView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{LinkCell.class}, new String[]{"optionsView"}, null, null, null, Theme.key_stickers_menu));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean needDelayOpenAnimation() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listViewAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        super.onTransitionAnimationEnd(r1, r2);
        if (r1 == false) goto L11;
        this.isOpened = true;
        if (r2 == false) goto L11;
        InviteLinkBottomSheet r12 = this.inviteLinkBottomSheet;
        if (r12 == null) goto L11;
        if (r12.isNeedReopen == false) goto L11;
        r12.show();
    L11:
        this.notificationsLocker.unlock();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        super.onTransitionAnimationStart(r1, r2);
        this.notificationsLocker.lock();
    }

    public void revokeLink(TLRPC.TL_chatInviteExported r5) {
        TLRPC.TL_messages_editExportedChatInvite r0 = new TLRPC.TL_messages_editExportedChatInvite();
        r0.link = r5.link;
        r0.revoked = true;
        r0.peer = getMessagesController().getInputPeer(-this.currentChatId);
        getConnectionsManager().sendRequest(r0, new ManageLinksActivity$$ExternalSyntheticLambda14(this, r5));
    }

    public void setInfo(TLRPC.ChatFull r1, TLRPC.ExportedChatInvite r2) {
        this.info = r1;
        this.invite = (TLRPC.TL_chatInviteExported) r2;
        this.isPublic = ChatObject.isPublic(this.currentChat);
        loadLinks(true);
    }
}
