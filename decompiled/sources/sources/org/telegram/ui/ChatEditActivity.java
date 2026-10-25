package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.material.timepicker.TimeModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.RadioButtonCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextDetailCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity;
import org.telegram.ui.Components.Reactions.ReactionsUtils;
import org.telegram.ui.Components.SectionsScrollView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.Switch;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.FilterCreateActivity;
import org.telegram.ui.PeerColorActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.Stars.BotStarsActivity;
import org.telegram.ui.Stars.BotStarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.bots.AffiliateProgramFragment;
import org.telegram.ui.bots.BotVerifySheet;
import org.telegram.ui.bots.ChannelAffiliateProgramsFragment;
import org.telegram.ui.community.CommunityCreateActivity;
import org.telegram.ui.community.CommunityEditActivity;
import org.telegram.ui.community.CommunitySheet;
import org.telegram.ui.community.cells.CommunityLinkView2;

/* loaded from: classes6.dex */
public class ChatEditActivity extends BaseFragment implements ImageUpdater.ImageUpdaterDelegate, NotificationCenter.NotificationCenterDelegate {
    private TextCell adminCell;
    private TextCell autoTranslationCell;
    private TLRPC.ChatReactions availableReactions;
    private TLRPC.FileLocation avatar;
    private AnimatorSet avatarAnimation;
    private LinearLayout avatarContainer;
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImage;
    private View avatarOverlay;
    private RadialProgressView avatarProgressView;
    private LinearLayout balanceContainer;
    private TextCell blockCell;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    private TextCell botAffiliateProgramCell;
    private TextInfoPrivacyCell botInfoCell;
    RLottieDrawable cameraDrawable;
    private boolean canForum;
    private TextCell changeBotSettingsCell;
    private TextCell channelAffiliateProgramsCell;
    private TLRPC.TL_chatAdminRights chatAdminRights;
    private TLRPC.TL_chatBannedRights chatBannedRights;
    private TLRPC.TL_chatBannedRights chatDefaultBannedRights;
    private long chatId;
    private PeerColorActivity.ChangeNameColorCell colorCell;
    private TextCell communityCell;
    private TextInfoPrivacyCell communityGapView;
    private TextInfoPrivacyCell communityInfoCell;
    private CommunityLinkView2 communityLinkView;
    private TextCell communityUnlinkCell;
    private boolean createAfterUpload;
    private TLRPC.Chat currentChat;
    private TLRPC.User currentUser;
    private TextSettingsCell deleteCell;
    private FrameLayout deleteContainer;
    private ShadowSectionCell deleteInfoCell;
    private EditTextBoldCursor descriptionTextView;
    private View doneButton;
    private boolean donePressed;
    private TextCell editCommandsCell;
    private TextCell editIntroCell;
    private boolean forum;
    private boolean forumTabs;
    private TextCell forumsCell;
    private boolean hasUploadedPhoto;
    private TextCell historyCell;
    private boolean historyHidden;
    private ImageUpdater imageUpdater;
    private TLRPC.ChatFull info;
    private LinearLayout infoContainer;
    private ShadowSectionCell infoSectionCell;
    private TextCell inviteLinksCell;
    private boolean isChannel;
    private LinearLayout linearLayout;
    private TextCell linkedCell;
    private TextCell locationCell;
    private TextCell logCell;
    private TextCell memberRequestsCell;
    private TextCell membersCell;
    private EditTextEmoji nameTextView;
    private final List<AnimatedEmojiDrawable> preloadedReactions;
    private AlertDialog progressDialog;
    private PhotoViewer.PhotoViewerProvider provider;
    private TextCell publicLinkCell;
    private TextCell reactionsCell;
    private int realAdminCount;
    private SectionsScrollView scrollView;
    private TextCell setAvatarCell;
    private LinearLayout settingsContainer;
    private TextInfoPrivacyCell settingsSectionCell;
    private ShadowSectionCell settingsTopSectionCell;
    private TextCell starsBalanceCell;
    private TextCell statsAndBoosts;
    private TextCell stickersCell;
    private FrameLayout stickersContainer;
    private TextInfoPrivacyCell stickersInfoCell;
    private TextCell suggestedCell;
    private TextCell tonBalanceCell;
    private TextCell typeCell;
    private LinearLayout typeEditContainer;
    private UndoView undoView;
    private ValueAnimator updateHistoryShowAnimator;
    private long userId;
    private TLRPC.UserFull userInfo;
    private TextCell verifyCell;
    private TextInfoPrivacyCell verifyInfoCell;
    private TextCell welcomeMessagesCell;

    /* renamed from: org.telegram.ui.ChatEditActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ ChatEditActivity this$0;

        /* renamed from: $r8$lambda$uwwtglORI-853ZBmkCiNPzsIRFw, reason: not valid java name */
        public static /* synthetic */ void m8290$r8$lambda$uwwtglORI853ZBmkCiNPzsIRFw(AnonymousClass1 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$onDeletePhoto$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$zOulFUwcDMo2qHSkvzSXgkIs7vw(AnonymousClass1 r0) {
            r0.lambda$onDeletePhoto$0();
        }

        public AnonymousClass1(ChatEditActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onDeletePhoto$0() {
            ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).setImageDrawable(ChatEditActivity.m8275$$Nest$fgetavatarDrawable(this.this$0));
            ChatEditActivity.m8284$$Nest$fgetsetAvatarCell(this.this$0).setTextAndIcon(LocaleController.getString("ChatSetPhotoOrVideo", R.string.ChatSetPhotoOrVideo), R.drawable.msg_addphoto, true);
            if (ChatEditActivity.m8280$$Nest$fgetcurrentUser(this.this$0) == null) goto L5;
            ChatEditActivity.m8280$$Nest$fgetcurrentUser(this.this$0).photo = null;
            this.this$0.getMessagesController().putUser(ChatEditActivity.m8280$$Nest$fgetcurrentUser(this.this$0), true);
        L5:
            ChatEditActivity.m8287$$Nest$fputhasUploadedPhoto(this.this$0, true);
            ChatEditActivity r0 = this.this$0;
            if (r0.cameraDrawable != null) goto L8;
            r0.cameraDrawable = new RLottieDrawable(R.raw.camera_outline, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), false, null);
        L8:
            ChatEditActivity.m8284$$Nest$fgetsetAvatarCell(this.this$0).imageView.setTranslationX(-AndroidUtilities.dp(8.0f));
            ChatEditActivity.m8284$$Nest$fgetsetAvatarCell(this.this$0).imageView.setAnimation(this.this$0.cameraDrawable);
        }

        private /* synthetic */ void lambda$onDeletePhoto$1(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new ChatEditActivity$1$$ExternalSyntheticLambda1(this));
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean canLoadMoreAvatars() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r5, TLRPC.FileLocation r6, int r7, boolean r8, boolean r9) {
            if (r6 != null) goto L5;
            return null;
        L5:
            TLRPC.User r72 = ChatEditActivity.m8280$$Nest$fgetcurrentUser(this.this$0);
            ChatEditActivity r82 = this.this$0;
            if (r72 != null) goto L8;
            TLRPC.Chat r73 = r82.getMessagesController().getChat(Long.valueOf(ChatEditActivity.m8279$$Nest$fgetchatId(this.this$0)));
            if (r73 == null) goto L24;
            TLRPC.ChatPhoto r74 = r73.photo;
            if (r74 == null) goto L24;
            TLRPC.FileLocation r75 = r74.photo_big;
            if (r75 == null) goto L24;
        L25:
            if (r75 != null) goto L27;
        L38:
            return null;
        L27:
            if (r75.local_id != r6.local_id) goto L38;
            if (r75.volume_id != r6.volume_id) goto L38;
            if (r75.dc_id != r6.dc_id) goto L38;
            int[] r52 = new int[2];
            ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).getLocationInWindow(r52);
            PhotoViewer.PlaceProviderObject r62 = new PhotoViewer.PlaceProviderObject();
            r62.viewX = r52[0];
            r62.viewY = r52[1];
            r62.parentView = ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0);
            r62.imageReceiver = ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).getImageReceiver();
            long r83 = ChatEditActivity.m8285$$Nest$fgetuserId(this.this$0);
            ChatEditActivity r84 = this.this$0;
            if (r83 == 0) goto L35;
            long r85 = ChatEditActivity.m8285$$Nest$fgetuserId(r84);
        L36:
            r62.dialogId = r85;
            r62.thumb = r62.imageReceiver.getBitmapSafe();
            r62.size = -1;
            r62.radius = ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).getImageReceiver().getRoundRadius(true);
            r62.scale = ChatEditActivity.m8274$$Nest$fgetavatarContainer(this.this$0).getScaleX();
            r62.canEdit = true;
            return r62;
        L35:
            r85 = -ChatEditActivity.m8279$$Nest$fgetchatId(r84);
        L24:
            r75 = null;
            goto L25
        L8:
            if (ChatEditActivity.m8285$$Nest$fgetuserId(r82) != 0) goto L10;
            TLRPC.User r76 = null;
        L11:
            if (r76 == null) goto L24;
            TLRPC.UserProfilePhoto r77 = r76.photo;
            if (r77 == null) goto L24;
            r75 = r77.photo_big;
            if (r75 == null) goto L24;
        L10:
            r76 = this.this$0.getMessagesController().getUser(Long.valueOf(ChatEditActivity.m8285$$Nest$fgetuserId(this.this$0)));
            goto L11
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int getTotalImageCount() {
            return 1;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean onDeletePhoto(int r5) {
            if (ChatEditActivity.m8285$$Nest$fgetuserId(this.this$0) != 0) goto L6;
            return true;
        L6:
            TLRPC.TL_photos_updateProfilePhoto r52 = new TLRPC.TL_photos_updateProfilePhoto();
            r52.bot = this.this$0.getMessagesController().getInputUser(ChatEditActivity.m8285$$Nest$fgetuserId(this.this$0));
            r52.flags |= 2;
            r52.id = new TLRPC.TL_inputPhotoEmpty();
            this.this$0.getConnectionsManager().sendRequest(r52, new ChatEditActivity$1$$ExternalSyntheticLambda0(this));
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void openPhotoForEdit(String r2, String r3, boolean r4) {
            ChatEditActivity.m8282$$Nest$fgetimageUpdater(this.this$0).openPhotoForEdit(r2, r3, 0, r4);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void willHidePhotoViewer() {
            ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).getImageReceiver().setVisible(true, true);
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatEditActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass10(ChatEditActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ChatEditActivity.m8286$$Nest$fputavatarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ChatEditActivity.m8273$$Nest$fgetavatarAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ChatEditActivity.m8278$$Nest$fgetavatarProgressView(this.this$0) != null) goto L8;
            return;
        L8:
            if (this.val$show == true) goto L10;
            ChatEditActivity.m8278$$Nest$fgetavatarProgressView(this.this$0).setVisibility(4);
            ChatEditActivity.m8277$$Nest$fgetavatarOverlay(this.this$0).setVisibility(4);
        L10:
            ChatEditActivity.m8286$$Nest$fputavatarAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatEditActivity this$0;
        final /* synthetic */ boolean val$finalShow;
        final /* synthetic */ ArrayList val$nextViews;

        public AnonymousClass11(ChatEditActivity r1, boolean r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$finalShow = r2;
            this.val$nextViews = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            TextCell r32 = ChatEditActivity.m8281$$Nest$fgethistoryCell(this.this$0);
            int r1 = 0;
            if (this.val$finalShow == false) goto L5;
            int r0 = 0;
        L6:
            r32.setVisibility(r0);
        L8:
            if (r1 >= this.val$nextViews.size()) goto L10;
            ((View) this.val$nextViews.get(r1)).setTranslationY(0.0f);
            r1 = r1 + 1;
            goto L8
        L10:
            return;
        L5:
            r0 = 8;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatEditActivity this$0;

        public AnonymousClass2(ChatEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L12;
            ChatEditActivity.m8289$$Nest$mprocessDone(this.this$0);
            return;
        L12:
            return;
        L5:
            if (ChatEditActivity.m8288$$Nest$mcheckDiscard(this.this$0, true) == false) goto L11;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        final /* synthetic */ ChatEditActivity this$0;

        public AnonymousClass3(ChatEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
            int r112 = getChildCount();
            int r0 = measureKeyboardHeight();
            int r2 = 0;
            if (r0 <= AndroidUtilities.dp(20.0f)) goto L5;
        L9:
            int r1 = 0;
        L10:
            setBottomClip(r1);
        L11:
            if (r2 >= r112) goto L48;
            View r3 = getChildAt(r2);
            if (r3.getVisibility() == 8) goto L47;
            FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r3.getLayoutParams();
            int r5 = r3.getMeasuredWidth();
            int r6 = r3.getMeasuredHeight();
            int r7 = r4.gravity;
            if (r7 != (-1)) goto L18;
            r7 = 51;
        L18:
            int r8 = r7 & 112;
            int r72 = r7 & 7;
            if (r72 != 1) goto L21;
            int r73 = (((r14 - r12) - r5) / 2) + r4.leftMargin;
            int r9 = r4.rightMargin;
        L24:
            int r74 = r73 - r9;
        L27:
            if (r8 != 16) goto L29;
            int r82 = ((((r15 - r1) - r13) - r6) / 2) + r4.topMargin;
            int r42 = r4.bottomMargin;
        L34:
            int r43 = r82 - r42;
        L38:
            if (ChatEditActivity.m8283$$Nest$fgetnameTextView(this.this$0) != null) goto L40;
        L46:
            r3.layout(r74, r43, r5 + r74, r6 + r43);
            goto L47
        L40:
            if (ChatEditActivity.m8283$$Nest$fgetnameTextView(this.this$0).isPopupView(r3) == false) goto L46;
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r44 = getMeasuredHeight();
            int r83 = r3.getMeasuredHeight();
        L44:
            r43 = r44 - r83;
            goto L46
        L45:
            r44 = getMeasuredHeight() + r0;
            r83 = r3.getMeasuredHeight();
            goto L44
        L29:
            if (r8 != 48) goto L31;
            r43 = r4.topMargin + getPaddingTop();
            goto L38
        L31:
            if (r8 == 80) goto L33;
            r43 = r4.topMargin;
            goto L38
        L33:
            r82 = ((r15 - r1) - r13) - r6;
            r42 = r4.bottomMargin;
            goto L34
        L21:
            if (r72 == 5) goto L23;
            r74 = r4.leftMargin;
            goto L27
        L23:
            r73 = r14 - r5;
            r9 = r4.rightMargin;
        L47:
            r2 = r2 + 1;
            goto L11
        L48:
            notifyHeightChanged();
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L9;
            if (AndroidUtilities.isTablet() == true) goto L9;
            r1 = ChatEditActivity.m8283$$Nest$fgetnameTextView(this.this$0).getEmojiPadding();
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r12, int r13) {
            int r6 = View.MeasureSpec.getSize(r12);
            int r1 = View.MeasureSpec.getSize(r13);
            setMeasuredDimension(r6, r1);
            int r7 = r1 - getPaddingTop();
            measureChildWithMargins(ChatEditActivity.access$000(this.this$0), r12, 0, r13, 0);
            if (measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L5;
            this.ignoreLayout = true;
            ChatEditActivity.m8283$$Nest$fgetnameTextView(this.this$0).hideEmojiView();
            this.ignoreLayout = false;
        L5:
            int r8 = getChildCount();
            int r9 = 0;
        L6:
            if (r9 >= r8) goto L34;
            View r14 = getChildAt(r9);
            if (r14 == null) goto L33;
            if (r14.getVisibility() == 8) goto L33;
            if (r14 == ChatEditActivity.access$100(this.this$0)) goto L33;
            if (ChatEditActivity.m8283$$Nest$fgetnameTextView(this.this$0) != null) goto L17;
        L32:
            measureChildWithMargins(r14, r12, 0, r13, 0);
            goto L33
        L17:
            if (ChatEditActivity.m8283$$Nest$fgetnameTextView(this.this$0).isPopupView(r14) == false) goto L32;
            if (AndroidUtilities.isInMultiwindow == true) goto L25;
            if (AndroidUtilities.isTablet() == true) goto L25;
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r14.getLayoutParams().height, TLObject.FLAG_30));
        L25:
            if (AndroidUtilities.isTablet() == false) goto L31;
            int r2 = View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L29;
            float r4 = 200.0f;
        L30:
            r14.measure(r2, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r4), (r7 - AndroidUtilities.statusBarHeight) + getPaddingTop()), TLObject.FLAG_30));
            goto L33
        L29:
            r4 = 320.0f;
            goto L30
        L31:
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r7 - AndroidUtilities.statusBarHeight) + getPaddingTop(), TLObject.FLAG_30));
        L33:
            r9 = r9 + 1;
            goto L6
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends BackupImageView {
        final /* synthetic */ ChatEditActivity this$0;

        public AnonymousClass4(ChatEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            if (ChatEditActivity.m8277$$Nest$fgetavatarOverlay(this.this$0) == null) goto L5;
            ChatEditActivity.m8277$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        L5:
            super.invalidate();
        }

        @Override // android.view.View
        public void invalidate(int r2, int r3, int r4, int r5) {
            if (ChatEditActivity.m8277$$Nest$fgetavatarOverlay(this.this$0) == null) goto L5;
            ChatEditActivity.m8277$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        L5:
            super.invalidate(r2, r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final /* synthetic */ ChatEditActivity this$0;
        final /* synthetic */ Paint val$paint;

        public AnonymousClass5(ChatEditActivity r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$paint = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0) != null) goto L5;
            return;
        L5:
            if (ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).getImageReceiver().hasNotThumb() == false) goto L9;
            this.val$paint.setAlpha((int) (ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).getImageReceiver().getCurrentAlpha() * 85.0f));
            r10.drawRoundRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), this.val$paint);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements TextWatcher {
        final /* synthetic */ ChatEditActivity this$0;

        public AnonymousClass6(ChatEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            ChatEditActivity.m8275$$Nest$fgetavatarDrawable(this.this$0).setInfo(5, ChatEditActivity.m8283$$Nest$fgetnameTextView(this.this$0).getText().toString(), null);
            if (ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0) == null) goto L6;
            ChatEditActivity.m8276$$Nest$fgetavatarImage(this.this$0).invalidate();
            return;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends TextCell {
        final /* synthetic */ ChatEditActivity this$0;

        public AnonymousClass7(ChatEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Cells.TextCell, android.view.View
        public void onDraw(Canvas r9) {
            if (LocaleController.isRTL == false) goto L6;
            float r0 = 0.0f;
        L5:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L10;
            int r1 = AndroidUtilities.dp(20.0f);
        L11:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L10:
            r1 = 0;
            goto L11
        L6:
            r0 = AndroidUtilities.dp(20.0f);
            goto L5
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements TextWatcher {
        final /* synthetic */ ChatEditActivity this$0;

        public AnonymousClass8(ChatEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ChatEditActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends ClickableSpan {
        final /* synthetic */ ChatEditActivity this$0;

        public AnonymousClass9(ChatEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Browser.openUrl(r1.getContext(), "https://t.me/BotFather");
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: $r8$lambda$-InxIEeQvroKvwGLborWF_hxfGM, reason: not valid java name */
    public static /* synthetic */ void m8245$r8$lambda$InxIEeQvroKvwGLborWF_hxfGM(ChatEditActivity r0, TLRPC.PhotoSize r1, TLRPC.InputFile r2, TLRPC.InputFile r3, TLRPC.VideoSize r4, TLRPC.PhotoSize r5, double r6, String r8) {
        r0.lambda$didUploadPhoto$59(r1, r2, r3, r4, r5, r6, r8);
    }

    /* renamed from: $r8$lambda$-ZjQE2cc4kT1bp21CH9kdiN3gY4, reason: not valid java name */
    public static /* synthetic */ void m8246$r8$lambda$ZjQE2cc4kT1bp21CH9kdiN3gY4(ChatEditActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadLinksCount$0(r1, r2);
    }

    /* renamed from: $r8$lambda$-Zv4VBOodw1WpPTh2ueSf1hPblU, reason: not valid java name */
    public static /* synthetic */ void m8247$r8$lambda$Zv4VBOodw1WpPTh2ueSf1hPblU(ChatEditActivity r0, View r1) {
        r0.lambda$createView$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$0EqAYhPDKb31ENEbmmCnFTykgVQ(ChatEditActivity r0, View r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$0LZ2GoqVwQFWJDk_OyfqsoGypAE(ChatEditActivity r0, View r1) {
        r0.lambda$createView$37(r1);
    }

    public static /* synthetic */ void $r8$lambda$1SNDT_M01qytqk7BYi7y05fNJ6k(ChatEditActivity r0, View r1) {
        r0.lambda$createView$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$1lAouOJzvWSWLDTzWlrXNVQWerI(ChatEditActivity r0, AlertDialog r1, TL_stories.TL_premium_boostsStatus r2, long r3, ChannelBoostsController.CanApplyBoost r5) {
        r0.lambda$createView$15(r1, r2, r3, r5);
    }

    /* renamed from: $r8$lambda$1vSqldAl-SpkYqRiEtB47kmuY7s, reason: not valid java name */
    public static /* synthetic */ void m8248$r8$lambda$1vSqldAlSpkYqRiEtB47kmuY7s(ChatEditActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadLinksCount$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$29autbw2xMWmekVCPGlpdGkMcEk(ChatEditActivity r0) {
        r0.lambda$getThemeDescriptions$72();
    }

    /* renamed from: $r8$lambda$5G5aONPi-0otNLusfklBy7-rmi0, reason: not valid java name */
    public static /* synthetic */ void m8249$r8$lambda$5G5aONPi0otNLusfklBy7rmi0(ChatEditActivity r0, View r1) {
        r0.lambda$createView$38(r1);
    }

    /* renamed from: $r8$lambda$7WU-CCyTGTY9C_6mXrSCrWItKLQ, reason: not valid java name */
    public static /* synthetic */ void m8250$r8$lambda$7WUCCyTGTY9C_6mXrSCrWItKLQ(ChatEditActivity r0, boolean[] r1, long r2, View r4) {
        r0.lambda$createView$20(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$8kGsMz4w3ymdidwnI_FKUrH0aFQ(ChatEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$63(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$93z50xOEPm6WYBQHgZf7OORd_uc(ChatEditActivity r0, long r1) {
        r0.lambda$processDone$67(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$9SoW__aIfuyOLwzbZ2dWQDairW8(ChatEditActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$9XLZgTsyI0jewD1pljqE0HF5dBA(ChatEditActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$didUploadPhoto$58(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$9bl7R8K4MHeDLyWDlKjEcGawwNs(ChatEditActivity r0) {
        r0.lambda$openSetPhotoAlert$53();
    }

    public static /* synthetic */ void $r8$lambda$9citJdfa66RZrrFOtfrlfgGsia4(ChatEditActivity r0, View r1) {
        r0.lambda$createView$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$AFuwCXmSuR81eAE0ZadKpS8BY40(ChatEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$61(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AqqrUb2J1p1SKr8fw_i22O9aG00(ChatEditActivity r0, View r1) {
        r0.lambda$createView$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$Cvd_na8bIp1PtiYXdWApAk1kmo0(ChatEditActivity r0, View r1) {
        r0.lambda$createView$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$DJmquqqezLbgqsyut6lgBPk_Gm0(ChatEditActivity r0, View r1) {
        r0.lambda$createView$42(r1);
    }

    public static /* synthetic */ void $r8$lambda$Fo0_gZAFkYnN6_2JYZQ3uAkjDNE(ChatEditActivity r0, View r1) {
        r0.lambda$createView$34(r1);
    }

    /* renamed from: $r8$lambda$H2yKBXTWtiD6x-aErW2XH0sJrUI, reason: not valid java name */
    public static /* synthetic */ void m8251$r8$lambda$H2yKBXTWtiD6xaErW2XH0sJrUI(ChatEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$60(r1, r2);
    }

    /* renamed from: $r8$lambda$Hwfup-OISpa1cSrTFROY9pnttHQ, reason: not valid java name */
    public static /* synthetic */ void m8252$r8$lambda$HwfupOISpa1cSrTFROY9pnttHQ(ChatEditActivity r0, TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        r0.lambda$createView$6(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$IDiSN_Zit9Iukzsh7TenKOEEiu0(ChatEditActivity r0, View r1) {
        r0.lambda$createView$41(r1);
    }

    public static /* synthetic */ void $r8$lambda$IHde6oClHoYUgMLFKdZtLKkcBAQ(ChatEditActivity r0, View r1) {
        r0.lambda$createView$33(r1);
    }

    /* renamed from: $r8$lambda$IKzY3GND0-1UWzD6mzF9trR0K2M, reason: not valid java name */
    public static /* synthetic */ void m8253$r8$lambda$IKzY3GND01UWzD6mzF9trR0K2M(ChatEditActivity r0, View r1) {
        r0.lambda$createView$24(r1);
    }

    /* renamed from: $r8$lambda$JrjyYXajQXt5t--jIlOYeq_sTjk, reason: not valid java name */
    public static /* synthetic */ void m8254$r8$lambda$JrjyYXajQXt5tjIlOYeq_sTjk(ChatEditActivity r0, TLRPC.Chat r1) {
        r0.lambda$createView$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$KjuzzKyVwVkEfDVl4q5MfnGQZcA(ChatEditActivity r0, BotStarsController r1, View r2) {
        r0.lambda$createView$44(r1, r2);
    }

    /* renamed from: $r8$lambda$MVg5H3HP-KlCgIVX8vk8tGI8G3E, reason: not valid java name */
    public static /* synthetic */ void m8255$r8$lambda$MVg5H3HPKlCgIVX8vk8tGI8G3E(ChatEditActivity r0, long r1, long r3) {
        r0.lambda$createView$48(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$NZ8xMWkXd1d1G8tJKpwCb4sNYLU(ChatEditActivity r0, View r1) {
        r0.lambda$createView$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$QBdoa_xStAYxgpkOZ2vgwCLIxZg(ChatEditActivity r0, boolean r1) {
        r0.lambda$createView$16(r1);
    }

    /* renamed from: $r8$lambda$R-FK4xJRelJIo9gNX99532o3yaU, reason: not valid java name */
    public static /* synthetic */ void m8256$r8$lambda$RFK4xJRelJIo9gNX99532o3yaU(ChatEditActivity r0, BotStarsController r1, View r2) {
        r0.lambda$createView$45(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$R1viMDz9iYz2aJqMGaaNOuGYYjU(ChatEditActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$getRealChannelAdminCount$70(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RVdQJfNY96V1moJMO4fCSZj5Cwg(ChatEditActivity r0) {
        r0.lambda$processDone$64();
    }

    public static /* synthetic */ void $r8$lambda$SONsfscfVWTfk85E_6O1DLiC54w(ChatEditActivity r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$createView$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$TZFiSAXHKSUyjkOwxFjyWmRpzNI(ChatEditActivity r0, View r1) {
        r0.lambda$createView$39(r1);
    }

    /* renamed from: $r8$lambda$UuWGxr68GLZS930f-W9IiGTxAr0, reason: not valid java name */
    public static /* synthetic */ void m8257$r8$lambda$UuWGxr68GLZS930fW9IiGTxAr0(ChatEditActivity r0) {
        r0.lambda$createView$17();
    }

    public static /* synthetic */ void $r8$lambda$V0fDVeg4PoTdz2QfN8NsNMyxoFU(ChatEditActivity r0, int r1, DialogInterface r2) {
        r0.lambda$processDone$66(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$V2nGDfCBmcI1wuP73pC9dv3QChk(ChatEditActivity r0, TLObject r1) {
        r0.lambda$getRealChannelAdminCount$69(r1);
    }

    public static /* synthetic */ void $r8$lambda$V3R294Az3ZhTPfuqqP6XYkgrX2s(ChatEditActivity r0) {
        r0.lambda$didUploadPhoto$57();
    }

    /* renamed from: $r8$lambda$WF1qRYyw7F_G4Q-9gof2GsReM80, reason: not valid java name */
    public static /* synthetic */ void m8258$r8$lambda$WF1qRYyw7F_G4Q9gof2GsReM80(ChatEditActivity r0, boolean r1, long r2, long r4, View r6) {
        r0.lambda$createView$49(r1, r2, r4, r6);
    }

    /* renamed from: $r8$lambda$WOwVI-woighXdxAXJFmhND-BUgA, reason: not valid java name */
    public static /* synthetic */ void m8259$r8$lambda$WOwVIwoighXdxAXJFmhNDBUgA(ChatEditActivity r0, View r1) {
        r0.lambda$createView$35(r1);
    }

    /* renamed from: $r8$lambda$W_0OLyML0-m1G39iq-hrTX1jE_8, reason: not valid java name */
    public static /* synthetic */ void m8260$r8$lambda$W_0OLyML0m1G39iqhrTX1jE_8(ChatEditActivity r0, View r1) {
        r0.lambda$createView$40(r1);
    }

    public static /* synthetic */ void $r8$lambda$WaH5LBWguCfdqLnryfNjZf6K3hE(ChatEditActivity r0, TLRPC.Bool r1, TLRPC.TL_error r2) {
        r0.lambda$createView$47(r1, r2);
    }

    /* renamed from: $r8$lambda$Y6_tfIv4_M_s-HgWYKzHtdaE2dM, reason: not valid java name */
    public static /* synthetic */ void m8261$r8$lambda$Y6_tfIv4_M_sHgWYKzHtdaE2dM(ChatEditActivity r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$createView$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$Yd6sJvwrmVZ4fV7zWH6FyMqa7Pg(ChatEditActivity r0, Context r1, View r2) {
        r0.lambda$createView$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZfCwguiaDSMYSzSkzeq2xTcgzNQ(ChatEditActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$openSetPhotoAlert$54(r1, r2);
    }

    /* renamed from: $r8$lambda$_SV-tglyn3li1Rr3QTIqdDEvyWU, reason: not valid java name */
    public static /* synthetic */ void m8262$r8$lambda$_SVtglyn3li1Rr3QTIqdDEvyWU(ChatEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$62(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_t8dujQQenMEDgquYvsVBZQ852M(ChatEditActivity r0, boolean r1) {
        r0.lambda$createView$51(r1);
    }

    /* renamed from: $r8$lambda$aJxGv2PoDtkTH-FJnvCj4KAurMk, reason: not valid java name */
    public static /* synthetic */ void m8263$r8$lambda$aJxGv2PoDtkTHFJnvCj4KAurMk(ChatEditActivity r0, DialogInterface r1) {
        r0.lambda$processDone$68(r1);
    }

    /* renamed from: $r8$lambda$bXAng0qen7XhsWkVmy-F69bECNc, reason: not valid java name */
    public static /* synthetic */ void m8264$r8$lambda$bXAng0qen7XhsWkVmyF69bECNc(ChatEditActivity r0, ArrayList r1, ValueAnimator r2) {
        r0.lambda$updateHistoryShow$71(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cPX_ANAztwVccnd2gJea3HMuz6c(ChatEditActivity r0, long r1, View r3) {
        r0.lambda$createView$50(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$d1WOiEGUVEJtXYrmg47IMdthfDc(ChatEditActivity r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createView$18(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$dSTKF0SkZcv-Ufr7F0oPuFT3zRo, reason: not valid java name */
    public static /* synthetic */ void m8265$r8$lambda$dSTKF0SkZcvUfr7F0oPuFT3zRo(ChatEditActivity r0, AlertDialog r1, long r2) {
        r0.lambda$createView$25(r1, r2);
    }

    /* renamed from: $r8$lambda$eVPTVd-YMUsHjKwQ0sCv8DACSEI, reason: not valid java name */
    public static /* synthetic */ void m8266$r8$lambda$eVPTVdYMUsHjKwQ0sCv8DACSEI(ChatEditActivity r0, DialogInterface r1) {
        r0.lambda$openSetPhotoAlert$56(r1);
    }

    /* renamed from: $r8$lambda$f9LQQuVKlrq-AaA6hM-oCKVv-sE, reason: not valid java name */
    public static /* synthetic */ void m8267$r8$lambda$f9LQQuVKlrqAaA6hMoCKVvsE(ChatEditActivity r0, TL_bots.setBotInfo r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$processDone$65(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$i9UfzCA4pKjSCypQtOttkrYIymk(ChatEditActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$kOW24g2VXNfnoZCtpqAOQqKfhMw(ChatEditActivity r0, View r1) {
        r0.lambda$createView$28(r1);
    }

    /* renamed from: $r8$lambda$lN-VAxFzR35SWLT_HrGcD1rqb8k, reason: not valid java name */
    public static /* synthetic */ void m8268$r8$lambda$lNVAxFzR35SWLT_HrGcD1rqb8k(ChatEditActivity r0, RadioButtonCell[] r1, BottomSheet.Builder r2, View r3) {
        r0.lambda$createView$21(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$nRgA-tTVkWmssORKNoHUqGiLG8s, reason: not valid java name */
    public static /* synthetic */ void m8269$r8$lambda$nRgAtTVkWmssORKNoHUqGiLG8s(ChatEditActivity r0, View r1) {
        r0.lambda$createView$52(r1);
    }

    public static /* synthetic */ void $r8$lambda$r2rVEPy6lQ4ruIN2ggdkXIQ08ds(ChatEditActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$rLbSg_yKZkFVipZzgBTSJOofnV8(ChatEditActivity r0, long r1) {
        r0.lambda$createView$10(r1);
    }

    /* renamed from: $r8$lambda$rqlfRo5pJekueHxZugCw-1myhvs, reason: not valid java name */
    public static /* synthetic */ void m8270$r8$lambda$rqlfRo5pJekueHxZugCw1myhvs(ChatEditActivity r0) {
        r0.lambda$openSetPhotoAlert$55();
    }

    public static /* synthetic */ void $r8$lambda$sBDAb8t1kRxgTCxQYa0DCv5cUrY(ChatEditActivity r0, View r1, Boolean r2, Boolean r3) {
        r0.lambda$createView$26(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$sZ3VDA2Upoq-vnlILdeYRWh7Quw, reason: not valid java name */
    public static /* synthetic */ boolean m8271$r8$lambda$sZ3VDA2UpoqvnlILdeYRWh7Quw(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$suxbuBtcAfKZExJ6KmPpoMEG2pA(ChatEditActivity r0, View r1) {
        r0.lambda$createView$30(r1);
    }

    /* renamed from: $r8$lambda$vHV-8FGjsyKydfanRYiBv3Q47PU, reason: not valid java name */
    public static /* synthetic */ void m8272$r8$lambda$vHV8FGjsyKydfanRYiBv3Q47PU(ChatEditActivity r0, long r1, View r3) {
        r0.lambda$createView$46(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$wW2CR3PVQHIygxvKLuvcugrwb7I(ChatEditActivity r0, boolean r1, boolean[] r2, long r3, AlertDialog r5, TL_stories.TL_premium_boostsStatus r6) {
        r0.lambda$createView$19(r1, r2, r3, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$x0jkR4Jfoqv4x7WCOykwAYdQnpQ(ChatEditActivity r0, View r1) {
        r0.lambda$createView$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$xgQJHXYiH7QctIKU55J8VQay46I(ChatEditActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$ybiddBgy5B6uACit1vwnd_5cNEw(ChatEditActivity r0, View r1) {
        r0.lambda$createView$36(r1);
    }

    public static /* synthetic */ void $r8$lambda$zY9oYJmqo7CKzLwpeTsDU91Sy4Y(ChatEditActivity r0, FrameLayout r1, View r2) {
        r0.lambda$createView$27(r1, r2);
    }

    /* renamed from: -$$Nest$fgetavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m8273$$Nest$fgetavatarAnimation(ChatEditActivity r0) {
        return r0.avatarAnimation;
    }

    /* renamed from: -$$Nest$fgetavatarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m8274$$Nest$fgetavatarContainer(ChatEditActivity r0) {
        return r0.avatarContainer;
    }

    /* renamed from: -$$Nest$fgetavatarDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AvatarDrawable m8275$$Nest$fgetavatarDrawable(ChatEditActivity r0) {
        return r0.avatarDrawable;
    }

    /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m8276$$Nest$fgetavatarImage(ChatEditActivity r0) {
        return r0.avatarImage;
    }

    /* renamed from: -$$Nest$fgetavatarOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m8277$$Nest$fgetavatarOverlay(ChatEditActivity r0) {
        return r0.avatarOverlay;
    }

    /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m8278$$Nest$fgetavatarProgressView(ChatEditActivity r0) {
        return r0.avatarProgressView;
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m8279$$Nest$fgetchatId(ChatEditActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetcurrentUser, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.User m8280$$Nest$fgetcurrentUser(ChatEditActivity r0) {
        return r0.currentUser;
    }

    /* renamed from: -$$Nest$fgethistoryCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextCell m8281$$Nest$fgethistoryCell(ChatEditActivity r0) {
        return r0.historyCell;
    }

    /* renamed from: -$$Nest$fgetimageUpdater, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageUpdater m8282$$Nest$fgetimageUpdater(ChatEditActivity r0) {
        return r0.imageUpdater;
    }

    /* renamed from: -$$Nest$fgetnameTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextEmoji m8283$$Nest$fgetnameTextView(ChatEditActivity r0) {
        return r0.nameTextView;
    }

    /* renamed from: -$$Nest$fgetsetAvatarCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextCell m8284$$Nest$fgetsetAvatarCell(ChatEditActivity r0) {
        return r0.setAvatarCell;
    }

    /* renamed from: -$$Nest$fgetuserId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m8285$$Nest$fgetuserId(ChatEditActivity r2) {
        return r2.userId;
    }

    /* renamed from: -$$Nest$fputavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8286$$Nest$fputavatarAnimation(ChatEditActivity r0, AnimatorSet r1) {
        r0.avatarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputhasUploadedPhoto, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8287$$Nest$fputhasUploadedPhoto(ChatEditActivity r0, boolean r1) {
        r0.hasUploadedPhoto = r1;
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m8288$$Nest$mcheckDiscard(ChatEditActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mprocessDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m8289$$Nest$mprocessDone(ChatEditActivity r0) {
        r0.processDone();
    }

    public ChatEditActivity(Bundle r7) {
        super(r7);
        this.realAdminCount = 0;
        this.preloadedReactions = new ArrayList();
        this.provider = new AnonymousClass1(this);
        this.avatarDrawable = new AvatarDrawable();
        this.chatId = r7.getLong("chat_id", 0);
        this.userId = r7.getLong("user_id", 0);
        if (this.chatId == 0) goto L12;
        TLRPC.Chat r72 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (r72 != null) goto L7;
    L9:
        int r73 = 2;
    L10:
        this.imageUpdater = new ImageUpdater(true, r73, true);
        return;
    L7:
        if (ChatObject.isChannelAndNotMegaGroup(r72) == false) goto L9;
        r73 = 1;
        goto L10
    L12:
        this.imageUpdater = new ImageUpdater(false, 0, false);
    }

    public static /* synthetic */ ActionBar access$000(ChatEditActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(ChatEditActivity r0) {
        return r0.actionBar;
    }

    public static CharSequence applyNewSpan(String r4) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(r4);
        r0.append("  d");
        FilterCreateActivity.NewSpan r42 = new FilterCreateActivity.NewSpan(false, 10);
        r42.setTypeface(AndroidUtilities.getTypeface("fonts/num.otf"));
        r42.setColor(Theme.getColor(Theme.key_premiumGradient1));
        r0.setSpan(r42, r0.length() - 1, r0.length(), 0);
        return r0;
    }

    private boolean checkDiscard(boolean r6) {
        long r0 = this.userId;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == 0) goto L22;
        TLRPC.UserFull r02 = this.userInfo;
        if (r02 == null) goto L9;
        String r03 = r02.about;
        if (r03 == null) goto L9;
        r2 = r03;
    L9:
        EditTextEmoji r04 = this.nameTextView;
        if (r04 != null) goto L12;
    L13:
        EditTextBoldCursor r05 = this.descriptionTextView;
        if (r05 != null) goto L16;
    L20:
        return true;
    L16:
        if (r2.equals(r05.getText().toString()) == true) goto L20;
    L17:
        if (r6 == false) goto L19;
        showDialog(new AlertDialog.Builder(getParentActivity()).setTitle(LocaleController.getString(R.string.UserRestrictionsApplyChanges)).setMessage(LocaleController.getString(R.string.BotSettingsChangedAlert)).setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new ChatEditActivity$$ExternalSyntheticLambda42(this)).setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new ChatEditActivity$$ExternalSyntheticLambda43(this)).create());
    L19:
        return false;
    L12:
        if (this.currentUser.first_name.equals(r04.getText().toString()) == false) goto L17;
    L22:
        if (ChatObject.hasAdminRights(this.currentChat) == true) goto L24;
        return true;
    L24:
        TLRPC.ChatFull r06 = this.info;
        if (r06 == null) goto L29;
        String r4 = r06.about;
        if (r4 == null) goto L29;
        r2 = r4;
    L29:
        if (r06 != null) goto L31;
    L34:
        EditTextEmoji r07 = this.nameTextView;
        if (r07 != null) goto L37;
    L38:
        EditTextBoldCursor r08 = this.descriptionTextView;
        if (r08 == null) goto L43;
        if (r2.equals(r08.getText().toString()) == true) goto L43;
    L44:
        if (r6 == false) goto L50;
        AlertDialog.Builder r62 = new AlertDialog.Builder(getParentActivity());
        r62.setTitle(LocaleController.getString("UserRestrictionsApplyChanges", R.string.UserRestrictionsApplyChanges));
        if (this.isChannel == false) goto L48;
        r62.setMessage(LocaleController.getString("ChannelSettingsChangedAlert", R.string.ChannelSettingsChangedAlert));
    L49:
        r62.setPositiveButton(LocaleController.getString("ApplyTheme", R.string.ApplyTheme), new ChatEditActivity$$ExternalSyntheticLambda44(this));
        r62.setNegativeButton(LocaleController.getString("PassportDiscard", R.string.PassportDiscard), new ChatEditActivity$$ExternalSyntheticLambda45(this));
        showDialog(r62.create());
        goto L50
    L48:
        r62.setMessage(LocaleController.getString("GroupSettingsChangedAlert", R.string.GroupSettingsChangedAlert));
    L50:
        return false;
    L43:
        if (this.forum != this.currentChat.forum) goto L44;
        return true;
    L37:
        if (this.currentChat.title.equals(r07.getText().toString()) == false) goto L44;
    L31:
        if (ChatObject.isChannel(this.currentChat) == false) goto L34;
        if (this.info.hidden_prehistory != this.historyHidden) goto L44;
        goto L34
    }

    private void checkWelcomeMessagesValue() {
        if (this.welcomeMessagesCell != null) goto L5;
        return;
    L5:
        String r0 = getMessagesController().getFirstWelcomeMessageText(-this.chatId);
        TLRPC.ChatFull r1 = this.info;
        if (r1 != null) goto L8;
    L11:
        this.welcomeMessagesCell.setTextAndValueAndIcon(LocaleController.getString(R.string.WelcomeMessage), r0, R.drawable.menu_welcome_messages, true);
        return;
    L8:
        if (r1.has_welcome_messages == true) goto L11;
        if (r0 != null) goto L11;
        r0 = LocaleController.getString(R.string.WelcomeMessageOff);
        goto L11
    }

    private String getActiveUsername(TLRPC.User r4) {
        String r3 = r4.username;
        if (r3 == null) goto L5;
        return r3;
    L5:
        ArrayList<TLRPC.TL_username> r32 = r4.usernames;
        int r42 = r32.size();
        int r0 = 0;
    L6:
        if (r0 >= r42) goto L11;
        TLRPC.TL_username r1 = r32.get(r0);
        r0 = r0 + 1;
        TLRPC.TL_username r12 = r1;
        if (r12.active == false) goto L6;
        return r12.username;
    L11:
        return null;
    }

    private int getAdminCount() {
        TLRPC.ChatFull r0 = this.info;
        if (r0 != null) goto L6;
        return 1;
    L6:
        int r02 = r0.participants.participants.size();
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= r02) goto L14;
        TLRPC.ChatParticipant r3 = this.info.participants.participants.get(r1);
        if ((r3 instanceof TLRPC.TL_chatParticipantAdmin) == false) goto L11;
    L12:
        r2 = r2 + 1;
    L13:
        r1 = r1 + 1;
        goto L7
    L11:
        if ((r3 instanceof TLRPC.TL_chatParticipantCreator) == false) goto L13;
    L14:
        return r2;
    }

    private int getChannelAdminCount() {
        TLRPC.ChatFull r0 = this.info;
        if (r0 == null) goto L18;
        TLRPC.ChatParticipants r02 = r0.participants;
        if (r02 == null) goto L23;
        ArrayList<TLRPC.ChatParticipant> r03 = r02.participants;
        if (r03 == null) goto L24;
        int r04 = r03.size();
        int r1 = 0;
        int r2 = 0;
    L10:
        if (r1 >= r04) goto L17;
        TLRPC.ChannelParticipant r3 = ((TLRPC.TL_chatChannelParticipant) this.info.participants.participants.get(r1)).channelParticipant;
        if ((r3 instanceof TLRPC.TL_channelParticipantAdmin) == false) goto L14;
    L15:
        r2 = r2 + 1;
    L16:
        r1 = r1 + 1;
        goto L10
    L14:
        if ((r3 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L16;
    L17:
        return r2;
    L24:
        return 1;
    L23:
        return 1;
    L18:
        return 1;
    }

    private void getRealChannelAdminCount() {
        TLRPC.TL_channels_getParticipants r0 = new TLRPC.TL_channels_getParticipants();
        r0.channel = getMessagesController().getInputChannel(this.chatId);
        r0.filter = new TLRPC.TL_channelParticipantsAdmins();
        int r02 = getConnectionsManager().sendRequest(r0, new ChatEditActivity$$ExternalSyntheticLambda0(this));
        getConnectionsManager().bindRequestToGuid(r02, this.classGuid);
    }

    private /* synthetic */ void lambda$checkDiscard$60(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$checkDiscard$61(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$checkDiscard$62(AlertDialog r1, int r2) {
        processDone();
    }

    private /* synthetic */ void lambda$checkDiscard$63(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$10(long r1) {
        updateSuggestedCell(Long.valueOf(r1), false);
    }

    private /* synthetic */ void lambda$createView$11(View r3) {
        PostSuggestionsEditActivity r32 = new PostSuggestionsEditActivity(this.chatId);
        r32.setOnApplied(new ChatEditActivity$$ExternalSyntheticLambda49(this));
        presentFragment(r32);
    }

    private /* synthetic */ void lambda$createView$12(View r3) {
        presentFragment(new ChannelColorActivity(-this.currentChat.id).setOnApplied(this));
        MessagesController.getInstance(this.currentAccount).getMainSettings().edit().putInt("boostingappearance", MessagesController.getInstance(this.currentAccount).getMainSettings().getInt("boostingappearance", 0) + 1).apply();
    }

    private /* synthetic */ void lambda$createView$13(TL_stories.TL_premium_boostsStatus r2) {
        if (r2 == null) goto L9;
        Switch r0 = this.autoTranslationCell.getCheckBox();
        if (r2.level >= getMessagesController().channelAutotranslationLevelMin) goto L6;
        int r1 = R.drawable.permission_locked;
    L7:
        r0.setIcon(r1);
        return;
    L6:
        r1 = 0;
        goto L7
    }

    private /* synthetic */ void lambda$createView$14(TLRPC.Chat r1) {
        presentFragment(StatisticActivity.create(r1));
    }

    private /* synthetic */ void lambda$createView$15(AlertDialog r7, TL_stories.TL_premium_boostsStatus r8, long r9, ChannelBoostsController.CanApplyBoost r11) {
        r7.dismiss();
        if (getContext() != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(this, getContext(), 35, this.currentAccount, getResourceProvider());
        r0.setCanApplyBoost(r11);
        r0.setBoostsStats(r8, true);
        r0.setDialogId(r9);
        TLRPC.Chat r6 = getMessagesController().getChat(Long.valueOf(-r9));
        if (r6 == null) goto L8;
        r0.showStatisticButtonInLink(new ChatEditActivity$$ExternalSyntheticLambda72(this, r6));
    L8:
        showDialog(r0);
    }

    private /* synthetic */ void lambda$createView$16(boolean r2) {
        this.currentChat.autotranslation = r2;
        getMessagesController().putChat(this.currentChat, false);
    }

    private /* synthetic */ void lambda$createView$17() {
        this.autoTranslationCell.setChecked(this.currentChat.autotranslation);
    }

    private /* synthetic */ void lambda$createView$18(boolean r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.Updates) == false) goto L6;
        getMessagesController().processUpdates((TLRPC.Updates) r3, false);
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda69(this, r2));
        return;
    L6:
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda70(this));
    }

    private /* synthetic */ void lambda$createView$19(boolean r7, boolean[] r8, long r9, AlertDialog r11, TL_stories.TL_premium_boostsStatus r12) {
        TLRPC.Chat r0 = this.currentChat;
        int r1 = r0.level;
        int r2 = r12.level;
        if (r1 == r2) goto L5;
        r0.level = r2;
        getMessagesController().putChat(this.currentChat, false);
    L5:
        Switch r02 = this.autoTranslationCell.getCheckBox();
        if (r12.level >= getMessagesController().channelAutotranslationLevelMin) goto L8;
        int r13 = R.drawable.permission_locked;
    L9:
        r02.setIcon(r13);
        if (r7 == true) goto L12;
    L15:
        TLRPC.TL_channels_toggleAutotranslation r6 = new TLRPC.TL_channels_toggleAutotranslation();
        getMessagesController();
        r6.channel = MessagesController.getInputChannel(this.currentChat);
        r6.enabled = r7;
        this.autoTranslationCell.setChecked(r7);
        r8[0] = false;
        r11.dismiss();
        getConnectionsManager().sendRequest(r6, new ChatEditActivity$$ExternalSyntheticLambda68(this, r7), 64);
        return;
    L12:
        if (r12.level >= getMessagesController().channelAutotranslationLevelMin) goto L15;
        this.autoTranslationCell.setChecked(false);
        r8[0] = false;
        getMessagesController().getBoostsController().userCanBoostChannel(r9, r12, new ChatEditActivity$$ExternalSyntheticLambda67(this, r11, r12, r9));
        return;
    L8:
        r13 = 0;
        goto L9
    }

    private /* synthetic */ void lambda$createView$20(boolean[] r9, long r10, View r12) {
        if (r9[0] == false) goto L5;
        return;
    L5:
        AlertDialog r7 = new AlertDialog(getParentActivity(), 3);
        r7.showDelayed(400);
        r9[0] = true;
        boolean r3 = !this.autoTranslationCell.isChecked();
        if (this.autoTranslationCell.getCheckBox().hasIcon() == true) goto L8;
        this.autoTranslationCell.setChecked(r3);
    L8:
        getMessagesController().getBoostsController().getBoostsStats(r10, new ChatEditActivity$$ExternalSyntheticLambda52(this, r3, r9, r10, r7));
    }

    private /* synthetic */ void lambda$createView$21(RadioButtonCell[] r5, BottomSheet.Builder r6, View r7) {
        Integer r72 = (Integer) r7.getTag();
        boolean r0 = false;
        RadioButtonCell r1 = r5[0];
        if (r72.intValue() != 0) goto L5;
        boolean r2 = true;
    L6:
        r1.setChecked(r2, true);
        RadioButtonCell r52 = r5[1];
        if (r72.intValue() != 1) goto L9;
        boolean r12 = true;
    L10:
        r52.setChecked(r12, true);
        if (r72.intValue() != 1) goto L13;
        r0 = true;
    L13:
        this.historyHidden = r0;
        r6.getDismissRunnable().run();
        updateFields(true, true);
        return;
    L9:
        r12 = false;
        goto L10
    L5:
        r2 = false;
        goto L6
    }

    private /* synthetic */ void lambda$createView$22(Context r14, View r15) {
        BottomSheet.Builder r152 = new BottomSheet.Builder(r14);
        r152.setApplyTopPadding(false);
        LinearLayout r1 = new LinearLayout(r14);
        r1.setOrientation(1);
        HeaderCell r3 = new HeaderCell(r14, Theme.key_dialogTextBlue2, 23, 15, false);
        r3.setHeight(47);
        r3.setText(LocaleController.getString("ChatHistory", R.string.ChatHistory));
        r1.addView(r3);
        LinearLayout r142 = new LinearLayout(r14);
        r142.setOrientation(1);
        r1.addView(r142, LayoutHelper.createLinear(-1, -2));
        RadioButtonCell[] r7 = new RadioButtonCell[2];
        int r8 = 0;
    L3:
        if (r8 >= 2) goto L12;
        RadioButtonCell r9 = new RadioButtonCell(r14, true);
        r7[r8] = r9;
        r9.setTag(Integer.valueOf(r8));
        r7[r8].setBackgroundDrawable(Theme.getSelectorDrawable(false));
        if (r8 != 0) goto L8;
        r7[r8].setTextAndValue(LocaleController.getString("ChatHistoryVisible", R.string.ChatHistoryVisible), LocaleController.getString("ChatHistoryVisibleInfo", R.string.ChatHistoryVisibleInfo), true, !this.historyHidden);
    L11:
        r142.addView(r7[r8], LayoutHelper.createLinear(-1, -2));
        r7[r8].setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda46(this, r7, r152));
        r8 = r8 + 1;
        goto L3
    L8:
        if (ChatObject.isChannel(this.currentChat) == false) goto L10;
        r7[r8].setTextAndValue(LocaleController.getString("ChatHistoryHidden", R.string.ChatHistoryHidden), LocaleController.getString("ChatHistoryHiddenInfo", R.string.ChatHistoryHiddenInfo), false, this.historyHidden);
        goto L11
    L10:
        r7[r8].setTextAndValue(LocaleController.getString("ChatHistoryHidden", R.string.ChatHistoryHidden), LocaleController.getString("ChatHistoryHiddenInfo2", R.string.ChatHistoryHiddenInfo2), false, this.historyHidden);
        goto L11
    L12:
        r152.setCustomView(r1);
        showDialog(r152.create());
    }

    private /* synthetic */ void lambda$createView$23(TL_stories.TL_premium_boostsStatus r1) {
        this.boostsStatus = r1;
    }

    private /* synthetic */ void lambda$createView$24(View r3) {
        GroupColorActivity r32 = new GroupColorActivity(-this.currentChat.id);
        r32.boostsStatus = this.boostsStatus;
        r32.setOnApplied(this);
        presentFragment(r32);
    }

    private /* synthetic */ void lambda$createView$25(AlertDialog r4, long r5) {
        r4.dismiss();
        boolean r42 = false;
        this.donePressed = false;
        if (r5 == 0) goto L16;
        this.chatId = r5;
        TLRPC.Chat r52 = getMessagesController().getChat(Long.valueOf(r5));
        this.currentChat = r52;
        TLRPC.ChatFull r6 = this.info;
        if (r6 == null) goto L9;
        r6.hidden_prehistory = true;
    L9:
        if (r52.forum_tabs == this.forumTabs) goto L11;
        r42 = true;
    L11:
        getMessagesController().toggleChannelForum(this.chatId, this.forum, this.forumTabs);
        TLRPC.Chat r53 = this.currentChat;
        r53.forum = this.forum;
        r53.forum_tabs = this.forumTabs;
        if (r42 == false) goto L15;
        updatePastFragmentsOnTabs();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$createView$26(View r7, Boolean r8, Boolean r9) {
        this.forum = r8.booleanValue();
        this.forumTabs = r9.booleanValue();
        BackupImageView r82 = this.avatarImage;
        if (this.forum == false) goto L6;
        float r92 = 16.0f;
    L7:
        r82.animateToRoundRadius(AndroidUtilities.dp(r92));
        ((TextCell) r7).setChecked(this.forum);
        boolean r72 = false;
        updateFields(false, true);
        if (this.donePressed == true) goto L33;
        TLRPC.Chat r93 = this.currentChat;
        if (r93.forum != this.forum) goto L14;
        if (r93.forum_tabs != this.forumTabs) goto L14;
        return;
    L14:
        if (ChatObject.isChannel(r93) == true) goto L27;
        if (this.forum == false) goto L27;
        Context r73 = getContext();
        if (r73 != null) goto L20;
        r73 = LaunchActivity.instance;
    L20:
        if (r73 != null) goto L22;
        r73 = ApplicationLoader.applicationContext;
    L22:
        if (r73 == null) goto L36;
        AlertDialog r94 = new AlertDialog(r73, 3);
        this.donePressed = true;
        r94.showDelayed(250);
        getMessagesController().convertToMegaGroup(getParentActivity(), this.chatId, this, new ChatEditActivity$$ExternalSyntheticLambda64(this, r94));
        return;
    L36:
        return;
    L27:
        if (this.currentChat.forum_tabs == this.forumTabs) goto L29;
        r72 = true;
    L29:
        getMessagesController().toggleChannelForum(this.chatId, this.forum, this.forumTabs);
        TLRPC.Chat r6 = this.currentChat;
        r6.forum = this.forum;
        r6.forum_tabs = this.forumTabs;
        if (r72 == false) goto L35;
        updatePastFragmentsOnTabs();
        return;
    L35:
        return;
    L33:
        return;
    L6:
        r92 = 32.0f;
        goto L7
    }

    private /* synthetic */ void lambda$createView$27(FrameLayout r5, View r6) {
        if (this.canForum == true) goto L13;
        TLRPC.ChatFull r62 = this.info;
        if (r62 != null) goto L7;
    L9:
        SpannableStringBuilder r63 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("ChannelTopicsForbidden", getMessagesController().forumUpgradeParticipantsMin, new Object[0]));
    L10:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.topics, r63).show();
        r5.performHapticFeedback(3);     // Catch: Exception -> L15
        return;
    L18:
        return;
    L7:
        if (r62.linked_chat_id == 0) goto L9;
        r63 = AndroidUtilities.replaceTags(LocaleController.getString("ChannelTopicsDiscussionForbidden", R.string.ChannelTopicsDiscussionForbidden));
        goto L10
    L13:
        EnableTopicsActivity r52 = new EnableTopicsActivity(-this.chatId);
        r52.setResourceProvider(this.resourceProvider);
        r52.setOnForumChanged(this.forum, this.forumTabs, new ChatEditActivity$$ExternalSyntheticLambda57(this, r6));
        presentFragment(r52);
    }

    private /* synthetic */ void lambda$createView$28(View r4) {
        Bundle r42 = new Bundle();
        r42.putLong("chat_id", this.chatId);
        if (this.isChannel == true) goto L7;
        if (this.currentChat.gigagroup == true) goto L7;
    L8:
        int r0 = 3;
    L10:
        r42.putInt("type", r0);
        ChatUsersActivity r02 = new ChatUsersActivity(r42);
        r02.setInfo(this.info);
        presentFragment(r02);
        return;
    L7:
        if (ChatObject.isCommunity(this.currentChat) == true) goto L8;
        r0 = 0;
        goto L10
    }

    private /* synthetic */ void lambda$createView$29(View r7) {
        ManageLinksActivity r0 = new ManageLinksActivity(this.chatId, 0, 0);
        TLRPC.ChatFull r72 = this.info;
        r0.setInfo(r72, r72.exported_invite);
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createView$3(View r5) {
        if (this.imageUpdater.isUploadingImage() == true) goto L39;
        ImageLocation r0 = null;
        if (this.userId != 0) goto L8;
        TLRPC.User r52 = null;
    L9:
        if (r52 == null) goto L19;
        TLRPC.UserProfilePhoto r02 = r52.photo;
        if (r02 != null) goto L13;
        return;
    L13:
        if (r02.photo_big == null) goto L36;
        PhotoViewer.getInstance().setParentActivity(this);
        TLRPC.UserProfilePhoto r03 = r52.photo;
        int r1 = r03.dc_id;
        if (r1 == 0) goto L17;
        r03.photo_big.dc_id = r1;
    L17:
        PhotoViewer.getInstance().openPhoto(r52.photo.photo_big, this.provider);
        return;
    L36:
        return;
    L19:
        TLRPC.Chat r53 = getMessagesController().getChat(Long.valueOf(this.chatId));
        TLRPC.ChatPhoto r12 = r53.photo;
        if (r12 != null) goto L22;
        return;
    L22:
        if (r12.photo_big == null) goto L38;
        PhotoViewer.getInstance().setParentActivity(this);
        TLRPC.ChatPhoto r13 = r53.photo;
        int r2 = r13.dc_id;
        if (r2 == 0) goto L26;
        r13.photo_big.dc_id = r2;
    L26:
        TLRPC.ChatFull r14 = this.info;
        if (r14 == null) goto L33;
        TLRPC.Photo r15 = r14.chat_photo;
        if ((r15 instanceof TLRPC.TL_photo) == false) goto L33;
        if (r15.video_sizes.isEmpty() == true) goto L33;
        r0 = ImageLocation.getForPhoto(this.info.chat_photo.video_sizes.get(0), this.info.chat_photo);
    L33:
        PhotoViewer.getInstance().openPhotoWithVideo(r53.photo.photo_big, r0, this.provider);
        return;
    L38:
        return;
    L8:
        r52 = getMessagesController().getUser(Long.valueOf(this.userId));
        goto L9
    }

    private /* synthetic */ void lambda$createView$30(View r4) {
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == false) goto L6;
        presentFragment(new ChatCustomReactionsEditActivity(this.chatId, this.info));
        return;
    L6:
        Bundle r42 = new Bundle();
        r42.putLong("chat_id", this.chatId);
        ChatReactionsEditActivity r0 = new ChatReactionsEditActivity(r42);
        r0.setInfo(this.info);
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createView$31(View r4) {
        Bundle r42 = new Bundle();
        r42.putInt("chatMode", 9);
        r42.putLong("chat_id", this.chatId);
        r42.putLong("welcome_messages_chat_id", this.chatId);
        presentFragment(new ChatActivity(r42));
    }

    private /* synthetic */ void lambda$createView$32(View r4) {
        Bundle r42 = new Bundle();
        r42.putLong("chat_id", this.chatId);
        r42.putInt("type", 1);
        ChatUsersActivity r0 = new ChatUsersActivity(r42);
        r0.setInfo(this.info);
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createView$33(View r4) {
        Bundle r42 = new Bundle();
        r42.putLong("chat_id", this.chatId);
        r42.putInt("type", 2);
        ChatUsersActivity r0 = new ChatUsersActivity(r42);
        r0.setInfo(this.info);
        presentFragment(r0);
    }

    private /* synthetic */ void lambda$createView$34(View r3) {
        presentFragment(new MemberRequestsActivity(this.chatId));
    }

    private /* synthetic */ void lambda$createView$35(View r3) {
        presentFragment(new ChannelAffiliateProgramsFragment(-this.chatId));
    }

    private /* synthetic */ void lambda$createView$36(View r2) {
        presentFragment(new ChannelAdminLogActivity(this.currentChat));
    }

    private /* synthetic */ void lambda$createView$37(View r2) {
        presentFragment(StatisticActivity.create(this.currentChat, false));
    }

    private /* synthetic */ void lambda$createView$38(View r4) {
        Bundle r42 = new Bundle();
        r42.putLong("bot_id", this.userId);
        presentFragment(new ChangeUsernameActivity(r42));
    }

    private /* synthetic */ void lambda$createView$39(View r3) {
        presentFragment(new AffiliateProgramFragment(this.userId));
    }

    private /* synthetic */ void lambda$createView$4(View r1) {
        openSetPhotoAlert();
    }

    private /* synthetic */ void lambda$createView$40(View r3) {
        Browser.openUrl(r3.getContext(), "https://t.me/BotFather?start=" + getActiveUsername(this.currentUser) + "-intro");
    }

    private /* synthetic */ void lambda$createView$41(View r3) {
        Browser.openUrl(r3.getContext(), "https://t.me/BotFather?start=" + getActiveUsername(this.currentUser) + "-commands");
    }

    private /* synthetic */ void lambda$createView$42(View r3) {
        Browser.openUrl(r3.getContext(), "https://t.me/BotFather?start=" + getActiveUsername(this.currentUser));
    }

    private /* synthetic */ void lambda$createView$43(View r3) {
        BotVerifySheet.openVerify(this.currentAccount, this.userId, this.userInfo.bot_info.verifier_settings);
    }

    private /* synthetic */ void lambda$createView$44(BotStarsController r3, View r4) {
        if (r3.isStarsBalanceAvailable(this.userId) == true) goto L5;
        return;
    L5:
        presentFragment(new BotStarsActivity(1, this.userId));
    }

    private /* synthetic */ void lambda$createView$45(BotStarsController r3, View r4) {
        if (r3.isStarsBalanceAvailable(this.userId) == true) goto L5;
        return;
    L5:
        presentFragment(new BotStarsActivity(0, this.userId));
    }

    private /* synthetic */ void lambda$createView$46(long r2, View r4) {
        if (ChatObject.hasAdminRights(getMessagesController().getChat(Long.valueOf(r2))) == false) goto L6;
        Bundle r42 = new Bundle();
        r42.putLong("community_id", r2);
        presentFragment(new CommunityEditActivity(r42));
        return;
    L6:
        showDialog(new CommunitySheet(this, r2));
    }

    private /* synthetic */ void lambda$createView$47(TLRPC.Bool r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L4;
        BulletinFactory.of(this).showForError(r2);
    L4:
        AndroidUtilities.removeFromParent(this.communityGapView);
        AndroidUtilities.removeFromParent(this.communityLinkView);
        AndroidUtilities.removeFromParent(this.communityUnlinkCell);
    }

    private /* synthetic */ void lambda$createView$48(long r8, long r10) {
        MessagesController.getInstance(this.currentAccount).unlinkCommunity(r8, r10, new ChatEditActivity$$ExternalSyntheticLambda65(this));
    }

    private /* synthetic */ void lambda$createView$49(boolean r8, long r9, long r11, View r13) {
        String r132 = LocaleController.getString(R.string.CommunityMenuRemoveFromCommunity);
        if (r8 == false) goto L6;
        int r82 = R.string.CommunityMenuRemoveBotFromCommunityConfirm;
    L9:
        AlertsCreator.showSimpleConfirmAlert(this, r132, LocaleController.getString(r82), LocaleController.getString(R.string.Remove), true, new ChatEditActivity$$ExternalSyntheticLambda59(this, r9, r11));
        return;
    L6:
        if (this.isChannel == false) goto L8;
        r82 = R.string.CommunityMenuRemoveChannelFromCommunityConfirm;
        goto L9
    L8:
        r82 = R.string.CommunityMenuRemoveGroupFromCommunityConfirm;
        goto L9
    }

    private /* synthetic */ boolean lambda$createView$5(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L8;
        View r0 = this.doneButton;
        if (r0 == null) goto L10;
        r0.performClick();
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    private /* synthetic */ void lambda$createView$50(long r2, View r4) {
        Bundle r42 = new Bundle();
        r42.putLong("dialog_id", r2);
        presentFragment(new CommunityCreateActivity(r42));
    }

    private /* synthetic */ void lambda$createView$51(boolean r5) {
        if (AndroidUtilities.isTablet() == false) goto L5;
        getNotificationCenter().postNotificationName(NotificationCenter.closeChats, new Object[]{Long.valueOf(-this.chatId)});
    L6:
        finishFragment();
        getNotificationCenter().postNotificationName(NotificationCenter.needDeleteDialog, new Object[]{Long.valueOf(-this.currentChat.id), null, this.currentChat, Boolean.valueOf(r5)});
        return;
    L5:
        getNotificationCenter().postNotificationName(NotificationCenter.closeChats, new Object[0]);
        goto L6
    }

    private /* synthetic */ void lambda$createView$52(View r10) {
        AlertsCreator.createClearOrDeleteDialogAlert(this, false, this.currentChat, null, false, true, true, false, new ChatEditActivity$$ExternalSyntheticLambda61(this));
    }

    private /* synthetic */ void lambda$createView$6(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        TLRPC.TL_channelLocation r22 = new TLRPC.TL_channelLocation();
        r22.address = r1.address;
        r22.geo_point = r1.geo;
        TLRPC.ChatFull r12 = this.info;
        r12.location = r22;
        r12.flags |= 32768;
        updateFields(false, true);
        getMessagesController().loadFullChat(this.chatId, 0, true);
    }

    private /* synthetic */ void lambda$createView$7(View r3) {
        if (AndroidUtilities.isMapsInstalled(this) == true) goto L5;
        return;
    L5:
        LocationActivity r32 = new LocationActivity(4);
        r32.setDialogId(-this.chatId);
        TLRPC.ChatFull r0 = this.info;
        if (r0 == null) goto L10;
        TLRPC.ChannelLocation r02 = r0.location;
        if ((r02 instanceof TLRPC.TL_channelLocation) == false) goto L10;
        r32.setInitialLocation((TLRPC.TL_channelLocation) r02);
    L10:
        r32.setDelegate(new ChatEditActivity$$ExternalSyntheticLambda60(this));
        presentFragment(r32);
    }

    private /* synthetic */ void lambda$createView$8(View r4) {
        long r0 = this.chatId;
        TextCell r2 = this.locationCell;
        if (r2 != null) goto L5;
    L7:
        boolean r22 = false;
    L8:
        ChatEditTypeActivity r42 = new ChatEditTypeActivity(r0, r22);
        r42.setInfo(this.info);
        presentFragment(r42);
        return;
    L5:
        if (r2.getVisibility() != 0) goto L7;
        r22 = true;
        goto L8
    }

    private /* synthetic */ void lambda$createView$9(View r3) {
        ChatLinkActivity r32 = new ChatLinkActivity(this.chatId);
        r32.setInfo(this.info);
        presentFragment(r32);
    }

    private /* synthetic */ void lambda$didUploadPhoto$57() {
        this.hasUploadedPhoto = true;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_AVATAR)});
    }

    private /* synthetic */ void lambda$didUploadPhoto$58(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda62(this));
    }

    private /* synthetic */ void lambda$didUploadPhoto$59(TLRPC.PhotoSize r17, TLRPC.InputFile r18, TLRPC.InputFile r19, TLRPC.VideoSize r20, TLRPC.PhotoSize r21, double r22, String r24) {
        TLRPC.FileLocation r3 = r17.location;
        this.avatar = r3;
        if (r18 != null) goto L16;
        if (r19 != null) goto L16;
        if (r20 != null) goto L16;
        BackupImageView r0 = this.avatarImage;
        ImageLocation r2 = ImageLocation.getForLocal(r3);
        AvatarDrawable r32 = this.avatarDrawable;
        Object r5 = this.currentUser;
        if (r5 != null) goto L11;
        r5 = this.currentChat;
    L11:
        r0.setImage(r2, "50_50", r32, r5);
        this.setAvatarCell.setTextAndIcon(LocaleController.getString("ChatSetNewPhoto", R.string.ChatSetNewPhoto), R.drawable.msg_addphoto, true);
        if (this.cameraDrawable != null) goto L14;
        this.cameraDrawable = new RLottieDrawable(R.raw.camera_outline, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), false, null);
    L14:
        this.setAvatarCell.imageView.setTranslationX(-AndroidUtilities.dp(8.0f));
        this.setAvatarCell.imageView.setAnimation(this.cameraDrawable);
        showAvatarProgress(true, false);
        return;
    L16:
        long r11 = 0;
        if (this.userId == 0) goto L34;
        TLRPC.User r33 = this.currentUser;
        if (r33 == null) goto L26;
        r33.photo = new TLRPC.TL_userProfilePhoto();
        TLRPC.UserProfilePhoto r34 = this.currentUser.photo;
        if (r18 == null) goto L23;
        r11 = r18.id;
    L25:
        r34.photo_id = r11;
        r34.photo_big = r21.location;
        r34.photo_small = r17.location;
        getMessagesController().putUser(this.currentUser, true);
        goto L26
    L23:
        if (r19 == null) goto L25;
        r11 = r19.id;
    L26:
        TLRPC.TL_photos_uploadProfilePhoto r02 = new TLRPC.TL_photos_uploadProfilePhoto();
        if (r18 == null) goto L29;
        r02.file = r18;
        r02.flags |= 1;
    L29:
        if (r19 == null) goto L31;
        r02.video = r19;
        int r23 = r02.flags;
        r02.video_start_ts = r22;
        r02.flags = r23 | 6;
    L31:
        if (r20 == null) goto L33;
        r02.video_emoji_markup = r20;
        r02.flags |= 16;
    L33:
        r02.bot = getMessagesController().getInputUser(this.currentUser);
        r02.flags |= 32;
        getConnectionsManager().sendRequest(r02, new ChatEditActivity$$ExternalSyntheticLambda48(this));
    L36:
        if (this.createAfterUpload == true) goto L48;
    L46:
        showAvatarProgress(false, true);
        return;
    L48:
        AlertDialog r03 = this.progressDialog;     // Catch: Exception -> L43
        if (r03 != null) goto L40;
    L45:
        this.donePressed = false;
        this.doneButton.performClick();
        goto L46
    L40:
        if (r03.isShowing() == false) goto L45;
        this.progressDialog.dismiss();     // Catch: Exception -> L43
        this.progressDialog = null;     // Catch: Exception -> L43
    L43:
        e = move-exception;
        FileLog.e(e);
        goto L45
    L34:
        getMessagesController().changeChatAvatar(this.chatId, null, r18, r19, r20, r22, r24, r17.location, r21.location, null);
        goto L36
    }

    private /* synthetic */ void lambda$getRealChannelAdminCount$69(TLObject r4) {
        TextCell r0 = this.adminCell;
        if (r0 == null) goto L8;
        if (r4 == null) goto L9;
        TLRPC.TL_channels_channelParticipants r42 = (TLRPC.TL_channels_channelParticipants) r4;
        this.realAdminCount = r42.count;
        r0.setTextAndValueAndIcon(LocaleController.getString(R.string.ChannelAdministrators), String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r42.count)}), R.drawable.msg_admins, true);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$getRealChannelAdminCount$70(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda58(this, r1));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$72() {
        BackupImageView r0 = this.avatarImage;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    private /* synthetic */ void lambda$loadLinksCount$0(TLRPC.TL_error r3, TLObject r4) {
        if (r3 != null) goto L5;
        TLRPC.ChatFull r32 = this.info;
        r32.invitesCount = ((TLRPC.TL_messages_exportedChatInvites) r4).count;
        getMessagesStorage().saveChatLinksCount(this.chatId, this.info.invitesCount);
        updateFields(false, false);
        return;
    }

    private /* synthetic */ void lambda$loadLinksCount$1(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda47(this, r3, r2));
    }

    private /* synthetic */ void lambda$openSetPhotoAlert$53() {
        this.avatarImage.setImageDrawable(this.avatarDrawable);
        this.setAvatarCell.setTextAndIcon(LocaleController.getString("ChatSetPhotoOrVideo", R.string.ChatSetPhotoOrVideo), R.drawable.msg_addphoto, true);
        TLRPC.User r0 = this.currentUser;
        if (r0 == null) goto L5;
        r0.photo = null;
        getMessagesController().putUser(this.currentUser, true);
    L5:
        this.hasUploadedPhoto = true;
        if (this.cameraDrawable != null) goto L8;
        this.cameraDrawable = new RLottieDrawable(R.raw.camera_outline, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), false, null);
    L8:
        this.setAvatarCell.imageView.setTranslationX(-AndroidUtilities.dp(8.0f));
        this.setAvatarCell.imageView.setAnimation(this.cameraDrawable);
    }

    private /* synthetic */ void lambda$openSetPhotoAlert$54(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda71(this));
    }

    private /* synthetic */ void lambda$openSetPhotoAlert$55() {
        this.avatar = null;
        if (this.userId != 0) goto L5;
        MessagesController.getInstance(this.currentAccount).changeChatAvatar(this.chatId, null, null, null, null, 0.0d, null, null, null, null);
    L6:
        showAvatarProgress(false, true);
        BackupImageView r1 = this.avatarImage;
        AvatarDrawable r3 = this.avatarDrawable;
        Object r4 = this.currentUser;
        if (r4 != null) goto L10;
        r4 = this.currentChat;
    L10:
        r1.setImage(null, null, r3, r4);
        this.cameraDrawable.setCurrentFrame(0);
        this.setAvatarCell.imageView.playAnimation();
        return;
    L5:
        TLRPC.TL_photos_updateProfilePhoto r12 = new TLRPC.TL_photos_updateProfilePhoto();
        r12.bot = getMessagesController().getInputUser(this.userId);
        r12.flags |= 2;
        r12.id = new TLRPC.TL_inputPhotoEmpty();
        getConnectionsManager().sendRequest(r12, new ChatEditActivity$$ExternalSyntheticLambda63(this));
        goto L6
    }

    private /* synthetic */ void lambda$openSetPhotoAlert$56(DialogInterface r2) {
        boolean r22 = this.imageUpdater.isUploadingImage();
        RLottieDrawable r0 = this.cameraDrawable;
        if (r22 == true) goto L6;
        r0.setCustomEndFrame(86);
        this.setAvatarCell.imageView.playAnimation();
        return;
    L6:
        r0.setCurrentFrame(0, false);
    }

    private /* synthetic */ void lambda$processDone$64() {
        this.progressDialog.dismiss();
        finishFragment();
    }

    private /* synthetic */ void lambda$processDone$65(TL_bots.setBotInfo r1, TLObject r2, TLRPC.TL_error r3) {
        TLRPC.UserFull r22 = this.userInfo;
        if (r22 == null) goto L5;
        r22.about = r1.about;
        getMessagesStorage().updateUserInfo(this.userInfo, false);
    L5:
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda66(this));
    }

    private /* synthetic */ void lambda$processDone$66(int r1, DialogInterface r2) {
        this.donePressed = false;
        this.progressDialog = null;
        getConnectionsManager().cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$processDone$67(long r3) {
        if (r3 != 0) goto L6;
        this.donePressed = false;
        return;
    L6:
        this.chatId = r3;
        this.currentChat = getMessagesController().getChat(Long.valueOf(r3));
        this.donePressed = false;
        TLRPC.ChatFull r32 = this.info;
        if (r32 == null) goto L9;
        r32.hidden_prehistory = true;
    L9:
        processDone();
    }

    private /* synthetic */ void lambda$processDone$68(DialogInterface r2) {
        this.createAfterUpload = false;
        this.progressDialog = null;
        this.donePressed = false;
    }

    private /* synthetic */ void lambda$updateHistoryShow$71(ArrayList r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.historyCell.setAlpha(r52);
        float r2 = 1.0f - r52;
        this.historyCell.setTranslationY(((-r0.getHeight()) / 2.0f) * r2);
        this.historyCell.setScaleY((r52 * 0.8f) + 0.2f);
        int r53 = 0;
    L4:
        if (r53 >= r4.size()) goto L6;
        ((View) r4.get(r53)).setTranslationY((-this.historyCell.getHeight()) * r2);
        r53 = r53 + 1;
        goto L4
    }

    private void loadLinksCount() {
        TLRPC.TL_messages_getExportedChatInvites r0 = new TLRPC.TL_messages_getExportedChatInvites();
        r0.peer = getMessagesController().getInputPeer(-this.chatId);
        r0.admin_id = getMessagesController().getInputUser(getUserConfig().getCurrentUser());
        r0.limit = 0;
        getConnectionsManager().sendRequest(r0, new ChatEditActivity$$ExternalSyntheticLambda41(this));
    }

    private void processDone() {
        if (this.donePressed == true) goto L91;
        EditTextEmoji r0 = this.nameTextView;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.length() != 0) goto L11;
        this.nameTextView.performHapticFeedback(3, 2);
        AndroidUtilities.shakeView(this.nameTextView);
        return;
    L11:
        boolean r02 = true;
        this.donePressed = true;
        TLRPC.User r2 = this.currentUser;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r2 == null) goto L29;
        TL_bots.setBotInfo r22 = new TL_bots.setBotInfo();
        r22.bot = getMessagesController().getInputUser(this.currentUser);
        r22.flags |= 4;
        r22.lang_code = _UrlKt.FRAGMENT_ENCODE_SET;
        if (this.currentUser.first_name.equals(this.nameTextView.getText().toString()) == true) goto L16;
        r22.name = this.nameTextView.getText().toString();
        r22.flags |= 8;
    L16:
        TLRPC.UserFull r4 = this.userInfo;
        if (r4 == null) goto L21;
        String r42 = r4.about;
        if (r42 == null) goto L21;
        r3 = r42;
    L21:
        EditTextBoldCursor r43 = this.descriptionTextView;
        if (r43 != null) goto L24;
    L26:
        this.progressDialog = new AlertDialog(getParentActivity(), 3);
        this.progressDialog.setOnCancelListener(new ChatEditActivity$$ExternalSyntheticLambda54(this, getConnectionsManager().sendRequest(r22, new ChatEditActivity$$ExternalSyntheticLambda53(this, r22))));
        this.progressDialog.show();
        return;
    L24:
        if (r3.equals(r43.getText().toString()) == true) goto L26;
        r22.about = this.descriptionTextView.getText().toString();
        r22.flags = 1 | r22.flags;
        goto L26
    L29:
        if (ChatObject.isChannel(this.currentChat) == true) goto L39;
        if (this.historyHidden == true) goto L33;
    L36:
        getMessagesController().convertToMegaGroup(getParentActivity(), this.chatId, this, new ChatEditActivity$$ExternalSyntheticLambda55(this));
        return;
    L33:
        if (this.forum == true) goto L36;
    L39:
        if (this.info == null) goto L46;
        if (ChatObject.isChannel(this.currentChat) == false) goto L46;
        TLRPC.ChatFull r10 = this.info;
        boolean r23 = r10.hidden_prehistory;
        boolean r44 = this.historyHidden;
        if (r23 == r44) goto L46;
        r10.hidden_prehistory = r44;
        getMessagesController().toggleChannelInvitesHistory(this.chatId, this.historyHidden);
    L46:
        if (this.imageUpdater.isUploadingImage() == false) goto L50;
        this.createAfterUpload = true;
        AlertDialog r102 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r102;
        r102.setOnCancelListener(new ChatEditActivity$$ExternalSyntheticLambda56(this));
        this.progressDialog.show();
        return;
    L50:
        if (this.currentChat.title.equals(this.nameTextView.getText().toString()) == true) goto L52;
        getMessagesController().changeChatTitle(this.chatId, this.nameTextView.getText().toString());
    L52:
        TLRPC.ChatFull r103 = this.info;
        if (r103 == null) goto L57;
        String r104 = r103.about;
        if (r104 == null) goto L57;
        r3 = r104;
    L57:
        EditTextBoldCursor r105 = this.descriptionTextView;
        if (r105 != null) goto L60;
    L62:
        boolean r106 = this.forum;
        TLRPC.Chat r1 = this.currentChat;
        if (r106 == r1.forum) goto L65;
    L66:
        int r24 = 0;
        if (this.forumTabs != r1.forum_tabs) goto L70;
        r02 = false;
    L70:
        getMessagesController().toggleChannelForum(this.chatId, this.forum, this.forumTabs);
        if (this.forum == true) goto L73;
    L83:
        if (r02 == false) goto L85;
        updatePastFragmentsOnTabs();
    L85:
        finishFragment();
        return;
    L73:
        if (this.forumTabs == true) goto L83;
        List<BaseFragment> r107 = getParentLayout().getFragmentStack();
    L76:
        if (r24 >= r107.size()) goto L83;
        if ((r107.get(r24) instanceof ChatActivity) == false) goto L82;
        if (((ChatActivity) r107.get(r24)).getArguments().getLong("chat_id") != this.chatId) goto L82;
        getParentLayout().removeFragmentFromStack(r24);
        Bundle r12 = new Bundle();
        r12.putLong("chat_id", this.chatId);
        getParentLayout().addFragmentToStack(TopicsFragment.getTopicsOrChat(this, r12), r24);
    L82:
        r24 = r24 + 1;
        goto L76
    L65:
        if (this.forumTabs == r1.forum_tabs) goto L85;
    L60:
        if (r3.equals(r105.getText().toString()) == true) goto L62;
        getMessagesController().updateChatAbout(this.chatId, this.descriptionTextView.getText().toString(), this.info);
        goto L62
    }

    private void setAvatar() {
        if (this.avatarImage != null) goto L5;
        return;
    L5:
        if (this.hasUploadedPhoto == true) goto L61;
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (this.userId != 0) goto L10;
        TLRPC.User r1 = null;
    L11:
        if (r0 != null) goto L14;
        if (r1 != null) goto L14;
        return;
    L14:
        this.currentUser = r1;
        this.currentChat = r0;
        if (r1 == null) goto L20;
        if (r1.photo != null) goto L21;
    L30:
        this.avatarImage.setImageDrawable(this.avatarDrawable);
    L31:
        boolean r02 = false;
    L33:
        if (this.setAvatarCell == null) goto L45;
        if (r02 == false) goto L36;
    L39:
        this.setAvatarCell.setTextAndIcon(LocaleController.getString("ChatSetNewPhoto", R.string.ChatSetNewPhoto), R.drawable.msg_addphoto, true);
    L41:
        if (this.cameraDrawable != null) goto L43;
        this.cameraDrawable = new RLottieDrawable(R.raw.camera_outline, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), false, null);
    L43:
        this.setAvatarCell.imageView.setTranslationX(-AndroidUtilities.dp(8.0f));
        this.setAvatarCell.imageView.setAnimation(this.cameraDrawable);
        goto L45
    L36:
        if (this.imageUpdater.isUploadingImage() == true) goto L39;
        this.setAvatarCell.setTextAndIcon(LocaleController.getString("ChatSetPhotoOrVideo", R.string.ChatSetPhotoOrVideo), R.drawable.msg_addphoto, true);
    L45:
        if (PhotoViewer.hasInstance() == false) goto L50;
        if (PhotoViewer.getInstance().isVisible() == false) goto L50;
        PhotoViewer.getInstance().checkCurrentImageVisibility();
    L50:
        if (this.channelAffiliateProgramsCell != null) goto L52;
        return;
    L52:
        if (getMessagesController().starrefConnectAllowed == true) goto L54;
        return;
    L54:
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == false) goto L60;
        this.channelAffiliateProgramsCell.setVisibility(0);
        return;
    L60:
        return;
    L21:
        if (r1 == null) goto L23;
        TLRPC.Chat r4 = r1;
    L24:
        if (r1 == null) goto L26;
        TLRPC.FileLocation r03 = r1.photo.photo_small;
    L27:
        this.avatar = r03;
        ImageLocation r04 = ImageLocation.getForUserOrChat(r4, 1);
        this.avatarImage.setForUserOrChat(r4, this.avatarDrawable);
        if (r04 == null) goto L31;
        r02 = true;
        goto L33
    L26:
        r03 = r0.photo.photo_small;
        goto L27
    L23:
        r4 = r0;
        goto L24
    L20:
        if (r0.photo == null) goto L30;
    L10:
        r1 = getMessagesController().getUser(Long.valueOf(this.userId));
        goto L11
    }

    private void showAvatarProgress(boolean r9, boolean r10) {
        if (this.avatarProgressView != null) goto L5;
        return;
    L5:
        AnimatorSet r0 = this.avatarAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
        this.avatarAnimation = null;
    L9:
        if (r10 == false) goto L16;
        AnimatorSet r102 = new AnimatorSet();
        this.avatarAnimation = r102;
        RadialProgressView r3 = this.avatarProgressView;
        if (r9 == false) goto L13;
        r3.setVisibility(0);
        this.avatarOverlay.setVisibility(0);
        AnimatorSet r103 = this.avatarAnimation;
        RadialProgressView r02 = this.avatarProgressView;
        Property r32 = View.ALPHA;
        r103.playTogether(new Animator[]{ObjectAnimator.ofFloat(r02, r32, new float[]{1.0f}), ObjectAnimator.ofFloat(this.avatarOverlay, r32, new float[]{1.0f})});
    L14:
        this.avatarAnimation.setDuration(180);
        this.avatarAnimation.addListener(new AnonymousClass10(this, r9));
        this.avatarAnimation.start();
        return;
    L13:
        Property r1 = View.ALPHA;
        r102.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r1, new float[]{0.0f}), ObjectAnimator.ofFloat(this.avatarOverlay, r1, new float[]{0.0f})});
        goto L14
    L16:
        RadialProgressView r104 = this.avatarProgressView;
        if (r9 == false) goto L20;
        r104.setAlpha(1.0f);
        this.avatarProgressView.setVisibility(0);
        this.avatarOverlay.setAlpha(1.0f);
        this.avatarOverlay.setVisibility(0);
        return;
    L20:
        r104.setAlpha(0.0f);
        this.avatarProgressView.setVisibility(4);
        this.avatarOverlay.setAlpha(0.0f);
        this.avatarOverlay.setVisibility(4);
    }

    private void updateCanForum() {
        int r1 = 0;
        if (this.userId == 0) goto L7;
        this.canForum = false;
        return;
    L7:
        if (this.forum == true) goto L14;
        TLRPC.ChatFull r0 = this.info;
        if (r0 != null) goto L11;
        int r02 = 0;
    L13:
        if (Math.max(r02, this.currentChat.participants_count) >= getMessagesController().forumUpgradeParticipantsMin) goto L14;
    L19:
        boolean r03 = false;
    L21:
        this.canForum = r03;
        TextCell r04 = this.forumsCell;
        if (r04 == null) goto L29;
        Switch r05 = r04.getCheckBox();
        if (this.canForum == true) goto L27;
        r1 = R.drawable.permission_locked;
    L27:
        r05.setIcon(r1);
        return;
    L29:
        return;
    L11:
        r02 = r0.participants_count;
    L14:
        TLRPC.ChatFull r06 = this.info;
        if (r06 != null) goto L17;
    L20:
        r03 = true;
        goto L21
    L17:
        if (r06.linked_chat_id != 0) goto L19;
        goto L19
    }

    private void updateFields(boolean r14, boolean r15) {
        if (r14 == false) goto L7;
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (r0 == null) goto L7;
        this.currentChat = r0;
    L7:
        boolean r6 = ChatObject.isPublic(this.currentChat);
        TextInfoPrivacyCell r02 = this.settingsSectionCell;
        if (r02 != null) goto L10;
    L26:
        TextCell r03 = this.logCell;
        if (r03 == null) goto L38;
        TLRPC.Chat r1 = this.currentChat;
        if (r1.megagroup == true) goto L31;
    L36:
        int r12 = 0;
    L37:
        r03.setVisibility(r12);
        goto L38
    L31:
        if (r1.gigagroup == true) goto L36;
        if (this.info != null) goto L36;
        r12 = 8;
    L38:
        TextCell r04 = this.linkedCell;
        if (r04 == null) goto L64;
        TLRPC.ChatFull r13 = this.info;
        if (r13 != null) goto L43;
    L63:
        r04.setVisibility(8);
        goto L64
    L43:
        if (this.isChannel == false) goto L45;
    L47:
        r04.setVisibility(0);
        if (this.info.linked_chat_id != 0) goto L50;
        this.linkedCell.setTextAndValueAndIcon(LocaleController.getString("Discussion", R.string.Discussion), LocaleController.getString("DiscussionInfoShort", R.string.DiscussionInfoShort), R.drawable.msg_discuss, true);
        goto L64
    L50:
        TLRPC.Chat r05 = getMessagesController().getChat(Long.valueOf(this.info.linked_chat_id));
        if (r05 != null) goto L54;
        this.linkedCell.setVisibility(8);
        goto L64
    L54:
        if (this.isChannel == false) goto L59;
        String r2 = ChatObject.getPublicUsername(r05);
        boolean r5 = TextUtils.isEmpty(r2);
        TextCell r122 = this.linkedCell;
        if (r5 == false) goto L58;
        r122.setTextAndValueAndIcon(LocaleController.getString("Discussion", R.string.Discussion), r05.title, R.drawable.msg_discuss, true);
        goto L64
    L58:
        r122.setTextAndValueAndIcon(LocaleController.getString("Discussion", R.string.Discussion), "@" + r2, R.drawable.msg_discuss, true);
        goto L64
    L59:
        String r16 = ChatObject.getPublicUsername(r05);
        boolean r22 = TextUtils.isEmpty(r16);
        TextCell r52 = this.linkedCell;
        if (r22 == false) goto L62;
        r52.setTextAndValueAndIcon(LocaleController.getString("LinkedChannel", R.string.LinkedChannel), r05.title, R.drawable.msg_channel, true);
        goto L64
    L62:
        r52.setTextAndValueAndIcon(LocaleController.getString("LinkedChannel", R.string.LinkedChannel), "@" + r16, R.drawable.msg_channel, true);
        goto L64
    L45:
        if (r13.linked_chat_id != 0) goto L47;
    L64:
        TextCell r06 = this.locationCell;
        if (r06 == null) goto L76;
        TLRPC.ChatFull r17 = this.info;
        if (r17 != null) goto L69;
    L74:
        r06.setVisibility(8);
        goto L76
    L69:
        if (r17.can_set_location == false) goto L74;
        r06.setVisibility(0);
        TLRPC.ChannelLocation r07 = this.info.location;
        if ((r07 instanceof TLRPC.TL_channelLocation) == false) goto L73;
        this.locationCell.setTextAndValue(LocaleController.getString("AttachLocation", R.string.AttachLocation), ((TLRPC.TL_channelLocation) r07).address, r15, true);
        goto L76
    L73:
        this.locationCell.setTextAndValue(LocaleController.getString("AttachLocation", R.string.AttachLocation), "Unknown address", r15, true);
    L76:
        if (this.typeCell == null) goto L146;
        TLRPC.ChatFull r08 = this.info;
        if (r08 != null) goto L80;
    L99:
        boolean r09 = this.currentChat.noforwards;
        if (this.isChannel == false) goto L107;
        if (r6 == true) goto L106;
        if (r09 == false) goto L105;
        String r010 = "TypePrivateRestrictedForwards";
        int r18 = R.string.TypePrivateRestrictedForwards;
    L104:
        String r011 = LocaleController.getString(r010, r18);
    L113:
        boolean r19 = this.isChannel;
        TextCell r23 = this.typeCell;
        if (r19 == false) goto L130;
        String r110 = LocaleController.getString("ChannelType", R.string.ChannelType);
        int r4 = R.drawable.msg_channel;
        TextCell r53 = this.historyCell;
        if (r53 != null) goto L118;
    L119:
        TextCell r54 = this.linkedCell;
        if (r54 != null) goto L122;
    L123:
        TextCell r55 = this.forumsCell;
        if (r55 != null) goto L126;
    L128:
        boolean r56 = false;
    L129:
        r23.setTextAndValueAndIcon(r110, r011, r4, r56);
        goto L146
    L126:
        if (r55.getVisibility() != 0) goto L128;
    L127:
        r56 = true;
        goto L129
    L122:
        if (r54.getVisibility() == 0) goto L127;
    L118:
        if (r53.getVisibility() == 0) goto L127;
    L130:
        String r111 = LocaleController.getString("GroupType", R.string.GroupType);
        int r42 = R.drawable.msg_groups;
        TextCell r57 = this.historyCell;
        if (r57 != null) goto L133;
    L134:
        TextCell r58 = this.linkedCell;
        if (r58 != null) goto L137;
    L138:
        TextCell r59 = this.forumsCell;
        if (r59 != null) goto L141;
    L143:
        boolean r510 = false;
    L144:
        r23.setTextAndValueAndIcon(r111, r011, r42, r510);
        goto L146
    L141:
        if (r59.getVisibility() != 0) goto L143;
    L142:
        r510 = true;
        goto L144
    L137:
        if (r58.getVisibility() == 0) goto L142;
    L133:
        if (r57.getVisibility() == 0) goto L142;
    L105:
        r010 = "TypePrivate";
        r18 = R.string.TypePrivate;
        goto L104
    L106:
        r010 = "TypePublic";
        r18 = R.string.TypePublic;
        goto L104
    L107:
        if (r6 == true) goto L112;
        if (r09 == false) goto L111;
        String r012 = "TypePrivateGroupRestrictedForwards";
        int r112 = R.string.TypePrivateGroupRestrictedForwards;
    L110:
        r011 = LocaleController.getString(r012, r112);
        goto L113
    L111:
        r012 = "TypePrivateGroup";
        r112 = R.string.TypePrivateGroup;
        goto L110
    L112:
        r012 = "TypePublicGroup";
        r112 = R.string.TypePublicGroup;
        goto L110
    L80:
        if ((r08.location instanceof TLRPC.TL_channelLocation) == false) goto L99;
        if (r6 == true) goto L83;
        String r013 = LocaleController.getString("TypeLocationGroupEdit", R.string.TypeLocationGroupEdit);
    L84:
        TextCell r113 = this.typeCell;
        String r24 = LocaleController.getString("TypeLocationGroup", R.string.TypeLocationGroup);
        int r43 = R.drawable.msg_channel;
        TextCell r511 = this.historyCell;
        if (r511 != null) goto L87;
    L88:
        TextCell r512 = this.linkedCell;
        if (r512 != null) goto L91;
    L92:
        TextCell r513 = this.forumsCell;
        if (r513 != null) goto L95;
    L97:
        boolean r514 = false;
    L98:
        r113.setTextAndValueAndIcon(r24, r013, r43, r514);
        goto L146
    L95:
        if (r513.getVisibility() != 0) goto L97;
    L96:
        r514 = true;
        goto L98
    L91:
        if (r512.getVisibility() == 0) goto L96;
    L87:
        if (r511.getVisibility() == 0) goto L96;
    L83:
        r013 = String.format("https://" + getMessagesController().linkPrefix + "/%s", new Object[]{ChatObject.getPublicUsername(this.currentChat)});
    L146:
        if (this.historyCell == null) goto L172;
        if (this.historyHidden == true) goto L150;
    L153:
        String r014 = "ChatHistoryVisible";
        int r114 = R.string.ChatHistoryVisible;
    L152:
        String r015 = LocaleController.getString(r014, r114);
        TextCell r016 = this.historyCell;
        String r115 = LocaleController.getString("ChatHistoryShort", R.string.ChatHistoryShort);
        int r44 = R.drawable.msg_discuss;
        if (this.forumsCell == null) goto L157;
        boolean r515 = true;
    L158:
        r016.setTextAndValueAndIcon(r115, r015, r15, r44, r515);
        this.historyCell.setEnabled(!this.forum);
        if (this.forum == true) goto L169;
        if (r6 == true) goto L169;
        TLRPC.ChatFull r017 = this.info;
        if (r017 != null) goto L164;
    L165:
        if (r017 != null) goto L167;
    L168:
        boolean r018 = true;
    L170:
        updateHistoryShow(r018, r15);
        goto L172
    L167:
        if ((r017.location instanceof TLRPC.TL_channelLocation) == true) goto L169;
    L164:
        if (r017.linked_chat_id == 0) goto L165;
    L169:
        r018 = false;
        goto L170
    L157:
        r515 = false;
        goto L158
    L150:
        if (this.forum == true) goto L153;
        r014 = "ChatHistoryHidden";
        r114 = R.string.ChatHistoryHidden;
    L172:
        if (this.membersCell != null) goto L174;
    L322:
        TextCell r019 = this.stickersCell;
        if (r019 == null) goto L332;
        if (this.info == null) goto L332;
        String r116 = LocaleController.getString(R.string.GroupStickers);
        TLRPC.StickerSet r25 = this.info.stickerset;
        if (r25 == null) goto L329;
        String r26 = r25.title;
    L330:
        r019.setTextAndValueAndIcon(r116, r26, R.drawable.msg_sticker, false);
        goto L332
    L329:
        r26 = LocaleController.getString(R.string.Add);
    L332:
        if (this.suggestedCell == null) goto L335;
        updateSuggestedCell(r15);
        return;
    L335:
        return;
    L174:
        if (this.info == null) goto L278;
        TextCell r020 = this.memberRequestsCell;
        if (r020 == null) goto L186;
        if (r020.getParent() != null) goto L180;
        this.infoContainer.addView(this.memberRequestsCell, this.infoContainer.indexOfChild(this.membersCell) + 1, LayoutHelper.createLinear(-1, -2));
    L180:
        TextCell r021 = this.memberRequestsCell;
        if (this.info.requests_pending <= 0) goto L183;
        int r45 = 0;
    L184:
        r021.setVisibility(r45);
        goto L186
    L183:
        r45 = 8;
    L186:
        if (this.isChannel == true) goto L188;
    L196:
        boolean r022 = ChatObject.isChannel(this.currentChat);
        TextCell r117 = this.membersCell;
        if (r022 == false) goto L199;
        r117.setTextAndValueAndIcon(LocaleController.getString("ChannelMembers", R.string.ChannelMembers), String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.info.participants_count)}), R.drawable.msg_groups, true);
    L206:
        TLRPC.Chat r023 = this.currentChat;
        if (r023.gigagroup == false) goto L218;
        if (ChatObject.isCommunity(r023) == true) goto L218;
        TextCell r024 = this.blockCell;
        String r118 = LocaleController.getString(R.string.ChannelBlacklist);
        TLRPC.ChatFull r27 = this.info;
        String r28 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(Math.max(r27.banned_count, r27.kicked_count))});
        int r46 = R.drawable.msg_user_remove;
        TextCell r516 = this.logCell;
        if (r516 != null) goto L213;
    L215:
        boolean r517 = false;
    L216:
        r024.setTextAndValueAndIcon(r118, r28, r46, r517);
    L243:
        TextCell r025 = this.memberRequestsCell;
        if (r025 == null) goto L253;
        String r119 = LocaleController.getString("MemberRequests", R.string.MemberRequests);
        String r29 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.info.requests_pending)});
        int r47 = R.drawable.msg_requests;
        TextCell r518 = this.logCell;
        if (r518 != null) goto L248;
    L250:
        boolean r519 = false;
    L251:
        r025.setTextAndValueAndIcon(r119, r29, r47, r519);
        goto L253
    L248:
        if (r518.getVisibility() != 0) goto L250;
        r519 = true;
    L253:
        if (ChatObject.hasAdminRights(this.currentChat) == false) goto L260;
        TextCell r026 = this.adminCell;
        String r120 = LocaleController.getString("ChannelAdministrators", R.string.ChannelAdministrators);
        if (ChatObject.isChannel(this.currentChat) == false) goto L257;
        int r210 = this.info.admins_count;
    L258:
        r026.setTextAndValueAndIcon(r120, String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r210)}), R.drawable.msg_admins, true);
    L305:
        TextCell r027 = this.reactionsCell;
        if (ChatObject.canChangeChatInfo(this.currentChat) == false) goto L308;
        int r121 = 0;
    L309:
        r027.setVisibility(r121);
        updateReactionsCell(r15);
        if (this.info != null) goto L312;
    L321:
        this.inviteLinksCell.setVisibility(8);
        goto L322
    L312:
        if (ChatObject.canUserDoAdminAction(this.currentChat, 3) == false) goto L321;
        if (r6 == true) goto L315;
    L317:
        int r028 = this.info.invitesCount;
        TextCell r123 = this.inviteLinksCell;
        if (r028 <= 0) goto L320;
        r123.setTextAndValueAndIcon(LocaleController.getString("InviteLinks", R.string.InviteLinks), Integer.toString(this.info.invitesCount), R.drawable.msg_link2, true);
        goto L322
    L320:
        r123.setTextAndValueAndIcon(LocaleController.getString("InviteLinks", R.string.InviteLinks), "1", R.drawable.msg_link2, true);
        goto L322
    L315:
        if (this.currentChat.creator == false) goto L317;
    L308:
        r121 = 8;
        goto L309
    L257:
        r210 = getAdminCount();
        goto L258
    L260:
        if (ChatObject.isChannel(this.currentChat) == false) goto L270;
        TLRPC.ChatParticipants r029 = this.info.participants;
        if (r029 == null) goto L270;
        ArrayList<TLRPC.ChatParticipant> r030 = r029.participants;
        if (r030 == null) goto L270;
        if (r030.size() == this.info.participants_count) goto L270;
        if (this.realAdminCount != 0) goto L270;
        this.adminCell.setTextAndIcon(LocaleController.getString("ChannelAdministrators", R.string.ChannelAdministrators), R.drawable.msg_admins, true);
        getRealChannelAdminCount();
    L270:
        TextCell r031 = this.adminCell;
        String r124 = LocaleController.getString("ChannelAdministrators", R.string.ChannelAdministrators);
        if (ChatObject.isChannel(this.currentChat) == false) goto L275;
        int r211 = this.realAdminCount;
        if (r211 != 0) goto L276;
        r211 = getChannelAdminCount();
    L276:
        r031.setTextAndValueAndIcon(r124, String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(r211)}), R.drawable.msg_admins, true);
        goto L305
    L275:
        r211 = getAdminCount();
        goto L276
    L213:
        if (r516.getVisibility() != 0) goto L215;
        r517 = true;
    L218:
        if (this.forum == false) goto L220;
        int r032 = 15;
    L221:
        TLRPC.TL_chatBannedRights r125 = this.currentChat.default_banned_rights;
        if (r125 == null) goto L241;
        int r212 = !r125.send_plain ? 1 : 0;
        if (r125.edit_rank == true) goto L226;
        r212 = r212 + 1;
    L226:
        int r213 = r212 + ChatUsersActivity.getSendMediaSelectedCount(r125);
        TLRPC.TL_chatBannedRights r126 = this.currentChat.default_banned_rights;
        if (r126.pin_messages == true) goto L230;
        r213 = r213 + 1;
    L230:
        if (r126.invite_users == true) goto L233;
        r213 = r213 + 1;
    L233:
        if (this.forum == false) goto L238;
        if (r126.manage_topics == true) goto L238;
        r213 = r213 + 1;
    L238:
        if (r126.change_info == true) goto L240;
        r213 = r213 + 1;
    L240:
        int r127 = r032;
    L242:
        this.blockCell.setTextAndValueAndIcon(LocaleController.getString(R.string.ChannelPermissions), String.format("%d/%d", new Object[]{Integer.valueOf(r213), Integer.valueOf(r127)}), r15, R.drawable.msg_permissions, true);
        goto L243
    L241:
        r127 = r032;
        r213 = r127;
        goto L242
    L220:
        r032 = 14;
        goto L221
    L199:
        String r033 = LocaleController.getString("ChannelMembers", R.string.ChannelMembers);
        String r214 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.info.participants.participants.size())});
        int r48 = R.drawable.msg_groups;
        TextCell r520 = this.memberRequestsCell;
        if (r520 != null) goto L202;
    L204:
        boolean r521 = false;
    L205:
        r117.setTextAndValueAndIcon(r033, r214, r48, r521);
        goto L206
    L202:
        if (r520.getVisibility() != 0) goto L204;
        r521 = true;
        goto L205
    L188:
        if (ChatObject.isCommunity(this.currentChat) == true) goto L196;
        this.membersCell.setTextAndValueAndIcon(LocaleController.getString("ChannelSubscribers", R.string.ChannelSubscribers), String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(this.info.participants_count)}), R.drawable.msg_groups, true);
        TextCell r034 = this.blockCell;
        String r128 = LocaleController.getString(R.string.ChannelBlacklist);
        TLRPC.ChatFull r215 = this.info;
        String r216 = String.format(TimeModel.NUMBER_FORMAT, new Object[]{Integer.valueOf(Math.max(r215.banned_count, r215.kicked_count))});
        int r49 = R.drawable.msg_user_remove;
        TextCell r522 = this.logCell;
        if (r522 != null) goto L192;
    L194:
        boolean r523 = false;
    L195:
        r034.setTextAndValueAndIcon(r128, r216, r49, r523);
        goto L253
    L192:
        if (r522.getVisibility() != 0) goto L194;
        r523 = true;
        goto L195
    L278:
        if (this.isChannel == true) goto L280;
    L288:
        TextCell r035 = this.membersCell;
        String r129 = LocaleController.getString("ChannelMembers", R.string.ChannelMembers);
        int r217 = R.drawable.msg_groups;
        TextCell r410 = this.logCell;
        if (r410 != null) goto L291;
    L293:
        boolean r411 = false;
    L294:
        r035.setTextAndIcon(r129, r217, r411);
        boolean r036 = this.currentChat.gigagroup;
        TextCell r130 = this.blockCell;
        if (r036 == false) goto L303;
        String r037 = LocaleController.getString(R.string.ChannelBlacklist);
        int r218 = R.drawable.msg_chats_remove;
        TextCell r412 = this.logCell;
        if (r412 != null) goto L299;
    L301:
        boolean r413 = false;
    L302:
        r130.setTextAndIcon(r037, r218, r413);
    L304:
        this.adminCell.setTextAndIcon(LocaleController.getString("ChannelAdministrators", R.string.ChannelAdministrators), R.drawable.msg_admins, true);
        goto L305
    L299:
        if (r412.getVisibility() != 0) goto L301;
        r413 = true;
        goto L302
    L303:
        r130.setTextAndIcon(LocaleController.getString(R.string.ChannelPermissions), R.drawable.msg_permissions, true);
        goto L304
    L291:
        if (r410.getVisibility() != 0) goto L293;
        r411 = true;
        goto L294
    L280:
        if (ChatObject.isCommunity(this.currentChat) == true) goto L288;
        this.membersCell.setTextAndIcon(LocaleController.getString("ChannelSubscribers", R.string.ChannelSubscribers), R.drawable.msg_groups, true);
        TextCell r038 = this.blockCell;
        String r131 = LocaleController.getString(R.string.ChannelBlacklist);
        int r219 = R.drawable.msg_chats_remove;
        TextCell r414 = this.logCell;
        if (r414 != null) goto L284;
    L286:
        boolean r415 = false;
    L287:
        r038.setTextAndIcon(r131, r219, r415);
        goto L304
    L284:
        if (r414.getVisibility() != 0) goto L286;
        r415 = true;
        goto L287
    L10:
        if (this.typeCell != null) goto L24;
        TextCell r132 = this.linkedCell;
        if (r132 != null) goto L14;
    L15:
        TextCell r133 = this.historyCell;
        if (r133 != null) goto L18;
    L19:
        TextCell r134 = this.locationCell;
        if (r134 != null) goto L22;
    L23:
        int r135 = 8;
    L25:
        r02.setVisibility(r135);
        goto L26
    L22:
        if (r134.getVisibility() == 0) goto L24;
    L18:
        if (r133.getVisibility() == 0) goto L24;
    L14:
        if (r132.getVisibility() != 0) goto L15;
    L24:
        r135 = 0;
        goto L25
    }

    private void updateHistoryShow(boolean r12, boolean r13) {
        ValueAnimator r0 = this.updateHistoryShowAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        float r1 = 0.0f;
        int r2 = 8;
        if (this.historyCell.getAlpha() > 0.0f) goto L10;
        if (r12 == true) goto L10;
        this.historyCell.setVisibility(8);
        updateColorCell();
        return;
    L10:
        float r3 = 1.0f;
        if (this.historyCell.getVisibility() == 0) goto L13;
    L16:
        ArrayList r02 = new ArrayList();
        int r4 = 0;
        int r5 = 0;
        boolean r6 = false;
    L18:
        if (r5 >= this.typeEditContainer.getChildCount()) goto L26;
        if (r6 == false) goto L21;
    L23:
        if (r6 == false) goto L25;
        r02.add(this.typeEditContainer.getChildAt(r5));
    L25:
        r5 = r5 + 1;
        goto L18
    L21:
        if (this.typeEditContainer.getChildAt(r5) != this.historyCell) goto L23;
        r6 = true;
        goto L25
    L26:
        int r52 = 0;
        boolean r62 = false;
    L28:
        if (r52 >= this.linearLayout.getChildCount()) goto L37;
        if (r62 == false) goto L31;
    L33:
        if (r62 == false) goto L35;
        r02.add(this.linearLayout.getChildAt(r52));
    L35:
        r52 = r52 + 1;
        goto L28
    L31:
        if (this.linearLayout.getChildAt(r52) != this.typeEditContainer) goto L33;
        r62 = true;
        goto L35
    L37:
        if (this.historyCell.getVisibility() == 0) goto L39;
        this.historyCell.setAlpha(0.0f);
        this.historyCell.setTranslationY((-r5.getHeight()) / 2.0f);
    L39:
        this.historyCell.setVisibility(0);
        int r53 = 0;
    L41:
        if (r53 >= r02.size()) goto L43;
        ((View) r02.get(r53)).setTranslationY((-this.historyCell.getHeight()) * (1.0f - this.historyCell.getAlpha()));
        r53 = r53 + 1;
        goto L41
    L43:
        TextCell r54 = this.historyCell;
        if (r13 == false) goto L50;
        float r132 = r54.getAlpha();
        if (r12 == false) goto L48;
        r1 = 1.0f;
    L48:
        ValueAnimator r133 = ValueAnimator.ofFloat(new float[]{r132, r1});
        this.updateHistoryShowAnimator = r133;
        r133.addUpdateListener(new ChatEditActivity$$ExternalSyntheticLambda38(this, r02));
        this.updateHistoryShowAnimator.addListener(new AnonymousClass11(this, r12, r02));
        this.updateHistoryShowAnimator.setDuration(320);
        this.updateHistoryShowAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.updateHistoryShowAnimator.start();
        return;
    L50:
        if (r12 == false) goto L52;
        float r134 = 1.0f;
    L53:
        r54.setAlpha(r134);
        TextCell r135 = this.historyCell;
        float r55 = (-r135.getHeight()) / 2.0f;
        if (r12 == false) goto L56;
        float r63 = 0.0f;
    L57:
        r135.setTranslationY(r55 * r63);
        TextCell r136 = this.historyCell;
        if (r12 == true) goto L61;
        r3 = 0.0f;
    L61:
        r136.setScaleY((r3 * 0.8f) + 0.2f);
        TextCell r137 = this.historyCell;
        if (r12 == false) goto L64;
        r2 = 0;
    L64:
        r137.setVisibility(r2);
    L66:
        if (r4 >= r02.size()) goto L68;
        ((View) r02.get(r4)).setTranslationY(0.0f);
        r4 = r4 + 1;
        goto L66
    L68:
        this.updateHistoryShowAnimator = null;
        return;
    L56:
        r63 = 1.0f;
        goto L57
    L52:
        r134 = 0.0f;
        goto L53
    L13:
        if (this.historyCell.getAlpha() < 1.0f) goto L16;
        if (r12 == false) goto L16;
    }

    private void updatePastFragmentsOnTabs() {
        if (getParentLayout() == null) goto L36;
        List<BaseFragment> r0 = getParentLayout().getFragmentStack();
        int r1 = 0;
    L7:
        if (r1 >= r0.size()) goto L50;
        if ((r0.get(r1) instanceof ChatActivity) == false) goto L16;
        ChatActivity r2 = (ChatActivity) r0.get(r1);
        if (r2.getArguments().getLong("chat_id") != this.chatId) goto L35;
        getParentLayout().removeFragmentFromStack(r2);
        r2.clearViews();
        getParentLayout().addFragmentToStack(r2, r1);
        if (this.forumTabs == true) goto L35;
        Bundle r22 = new Bundle();
        r22.putLong("chat_id", this.chatId);
        getParentLayout().addFragmentToStack(new TopicsFragment(r22), r1);
        r1 = r1 + 1;
    L35:
        r1 = r1 + 1;
        goto L7
    L16:
        if (this.forumTabs == false) goto L25;
        if ((r0.get(r1) instanceof TopicsFragment) == false) goto L25;
        TopicsFragment r23 = (TopicsFragment) r0.get(r1);
        if (r23.getCurrentChat() == null) goto L35;
        if (r23.getCurrentChat().id != this.chatId) goto L35;
        getParentLayout().removeFragmentFromStack(r23);
        r1 = r1 - 1;
    L25:
        if (this.forumTabs == false) goto L35;
        if ((r0.get(r1) instanceof DialogsActivity) == false) goto L35;
        DialogsActivity r24 = (DialogsActivity) r0.get(r1);
        if (r24 == null) goto L35;
        RightSlidingDialogContainer r3 = r24.rightSlidingDialogContainer;
        if (r3 == null) goto L35;
        if (r3.hasFragment() == false) goto L35;
        r24.rightSlidingDialogContainer.finishPreview();
        goto L35
    L50:
        return;
    }

    private void updatePublicLinksCount() {
        if (this.publicLinkCell != null) goto L6;
        return;
    L6:
        if (this.currentUser.usernames.size() <= 1) goto L14;
        ArrayList<TLRPC.TL_username> r0 = this.currentUser.usernames;
        int r2 = r0.size();
        int r3 = 0;
        int r4 = 0;
    L8:
        if (r4 >= r2) goto L12;
        TLRPC.TL_username r5 = r0.get(r4);
        r4 = r4 + 1;
        if (r5.active == false) goto L8;
        r3 = r3 + 1;
        goto L8
    L12:
        this.publicLinkCell.setTextAndValueAndIcon(LocaleController.getString(R.string.BotPublicLinks), LocaleController.formatString(R.string.BotPublicLinksCount, new Object[]{Integer.valueOf(r3), Integer.valueOf(this.currentUser.usernames.size())}), R.drawable.msg_link2, true);
        return;
    L14:
        this.publicLinkCell.setTextAndValueAndIcon(LocaleController.getString(R.string.BotPublicLink), "t.me/" + this.currentUser.username, R.drawable.msg_link2, true);
    }

    private void updateReactionsCell(boolean r8) {
        TLRPC.ChatFull r0 = getMessagesController().getChatFull(this.chatId);
        boolean r1 = ChatObject.isChannelAndNotMegaGroup(this.currentChat);
        TLRPC.ChatReactions r2 = this.availableReactions;
        if (r2 != null) goto L5;
    L37:
        String r12 = LocaleController.getString(R.string.ReactionsOff);
        if (r0 != null) goto L40;
    L42:
        String r3 = r12;
    L43:
        this.reactionsCell.setTextAndValueAndIcon(LocaleController.getString(R.string.Reactions), r3, r8, R.drawable.msg_reactions2, true);
        return;
    L40:
        if (r0.paid_reactions_available == false) goto L42;
        String r02 = "1";
    L35:
        r3 = r02;
        goto L43
    L5:
        if ((r2 instanceof TLRPC.TL_chatReactionsNone) == true) goto L37;
        if ((r2 instanceof TLRPC.TL_chatReactionsSome) == false) goto L36;
        TLRPC.TL_chatReactionsSome r22 = (TLRPC.TL_chatReactionsSome) r2;
        int r32 = 0;
        int r4 = 0;
    L11:
        if (r32 >= r22.reactions.size()) goto L23;
        TLRPC.Reaction r5 = r22.reactions.get(r32);
        if ((r5 instanceof TLRPC.TL_reactionEmoji) == false) goto L20;
        TLRPC.TL_availableReaction r52 = getMediaDataController().getReactionsMap().get(((TLRPC.TL_reactionEmoji) r5).emoticon);
        if (r52 == null) goto L22;
        if (r52.inactive == true) goto L22;
    L21:
        r4 = r4 + 1;
    L22:
        r32 = r32 + 1;
        goto L11
    L20:
        if ((r5 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L22;
    L23:
        if (r1 == false) goto L31;
        if (r0 != null) goto L26;
    L28:
        if (r4 != 0) goto L30;
        r02 = LocaleController.getString(R.string.ReactionsOff);
        goto L35
    L30:
        r02 = String.valueOf(r4);
        goto L35
    L26:
        if (r0.paid_reactions_available == false) goto L28;
        r4 = r4 + 1;
        goto L28
    L31:
        int r03 = Math.min(getMediaDataController().getEnabledReactionsList().size(), r4);
        if (r03 != 0) goto L34;
        r02 = LocaleController.getString(R.string.ReactionsOff);
        goto L35
    L34:
        r02 = LocaleController.formatString(R.string.ReactionsCount, new Object[]{Integer.valueOf(r03), Integer.valueOf(getMediaDataController().getEnabledReactionsList().size())});
        goto L35
    L36:
        r02 = LocaleController.getString(R.string.ReactionsAll);
        goto L35
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r33) {
        EditTextEmoji r0 = this.nameTextView;
        if (r0 == null) goto L5;
        r0.onDestroy();
    L5:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        AnonymousClass3 r2 = new AnonymousClass3(this, r33);
        r2.setOnTouchListener(new ChatEditActivity$$ExternalSyntheticLambda1());
        this.fragmentView = r2;
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        SectionsScrollView.SectionsLinearLayout r9 = new SectionsScrollView.SectionsLinearLayout(r33);
        this.linearLayout = r9;
        SectionsScrollView r02 = new SectionsScrollView(r33, this.linearLayout, this.resourceProvider, false);
        this.scrollView = r02;
        r02.setFillViewport(true);
        r2.addView(this.scrollView, LayoutHelper.createFrame(-1, -1.0f));
        this.actionBar.setAdaptiveBackground(this.scrollView);
        this.scrollView.addView(r9, new FrameLayout.LayoutParams(-1, -2));
        r9.setOrientation(1);
        boolean r03 = ChatObject.hasAdminRights(this.currentChat);
        ActionBar r3 = this.actionBar;
        if (r03 == false) goto L8;
        r3.setTitle(LocaleController.getString(R.string.ChannelEdit));
    L9:
        LinearLayout r04 = new LinearLayout(r33);
        this.avatarContainer = r04;
        r04.setOrientation(1);
        this.avatarContainer.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r9.addView(this.avatarContainer, LayoutHelper.createLinear(-1, -2));
        FrameLayout r6 = new FrameLayout(r33);
        this.avatarContainer.addView(r6, LayoutHelper.createLinear(-1, -2));
        AnonymousClass4 r05 = new AnonymousClass4(this, r33);
        this.avatarImage = r05;
        r05.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f, false, this.forum));
        int r15 = 5;
        if (this.currentUser == null) goto L12;
    L26:
        BackupImageView r06 = this.avatarImage;
        boolean r17 = LocaleController.isRTL;
        if (r17 == false) goto L29;
        int r18 = 5;
    L30:
        int r21 = r18 | 48;
        if (r17 == false) goto L33;
        float r22 = 0.0f;
    L34:
        if (r17 == false) goto L36;
        float r24 = 24.0f;
    L37:
        r6.addView(r06, LayoutHelper.createFrame(56, 56.0f, r21, r22, 20.0f, r24, 12.0f));
        Paint r07 = new Paint(1);
        r07.setColor(1426063360);
        AnonymousClass5 r32 = new AnonymousClass5(this, r33, r07);
        this.avatarOverlay = r32;
        boolean r08 = LocaleController.isRTL;
        if (r08 == false) goto L40;
        int r182 = 5;
    L41:
        int r212 = r182 | 48;
        if (r08 == false) goto L44;
        float r222 = 0.0f;
    L45:
        if (r08 == false) goto L47;
        float r242 = 24.0f;
    L48:
        r6.addView(r32, LayoutHelper.createFrame(56, 56.0f, r212, r222, 20.0f, r242, 12.0f));
        RadialProgressView r09 = new RadialProgressView(r33);
        this.avatarProgressView = r09;
        r09.setSize(AndroidUtilities.dp(30.0f));
        this.avatarProgressView.setProgressColor(-1);
        this.avatarProgressView.setNoProgress(false);
        RadialProgressView r010 = this.avatarProgressView;
        boolean r34 = LocaleController.isRTL;
        if (r34 == false) goto L51;
        int r183 = 5;
    L52:
        int r213 = r183 | 48;
        if (r34 == false) goto L55;
        float r223 = 0.0f;
    L56:
        if (r34 == false) goto L58;
        float r243 = 24.0f;
    L59:
        r6.addView(r010, LayoutHelper.createFrame(56, 56.0f, r213, r223, 20.0f, r243, 12.0f));
        showAvatarProgress(false, false);
        this.avatarContainer.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda12(this));
    L60:
        EditTextEmoji r011 = new EditTextEmoji(r33, r2, this, 0, false);
        this.nameTextView = r011;
        if (this.userId == 0) goto L64;
        r011.setHint(LocaleController.getString(R.string.BotName));
    L67:
        this.nameTextView.getEditText().setSingleLine(true);
        this.nameTextView.setEnabled(ChatObject.canChangeChatInfo(this.currentChat));
        EditTextEmoji r35 = this.nameTextView;
        r35.setFocusable(r35.isEnabled());
        this.nameTextView.getEditText().addTextChangedListener(new AnonymousClass6(this));
        this.nameTextView.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(128)});
        EditTextEmoji r36 = this.nameTextView;
        boolean r4 = LocaleController.isRTL;
        if (r4 == false) goto L70;
        float r244 = 24.0f;
    L71:
        if (r4 == false) goto L73;
        float r26 = 96.0f;
    L74:
        r6.addView(r36, LayoutHelper.createFrame(-1, -2.0f, 16, r244, 12.0f, r26, 12.0f));
        LinearLayout r37 = new LinearLayout(r33);
        this.settingsContainer = r37;
        r37.setOrientation(1);
        r9.addView(this.settingsContainer, LayoutHelper.createLinear(-1, -2));
        if (this.currentUser == null) goto L77;
    L78:
        AnonymousClass7 r38 = new AnonymousClass7(this, r33);
        this.setAvatarCell = r38;
        r38.setBackground(Theme.getSelectorDrawable(false));
        this.setAvatarCell.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
        this.setAvatarCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda23(this));
        this.settingsContainer.addView(this.setAvatarCell, LayoutHelper.createLinear(-1, -2));
    L79:
        EditTextBoldCursor r39 = new EditTextBoldCursor(r33);
        this.descriptionTextView = r39;
        r39.setTextSize(1, 16.0f);
        this.descriptionTextView.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextBoldCursor r310 = this.descriptionTextView;
        int r42 = Theme.key_windowBackgroundWhiteBlackText;
        r310.setTextColor(Theme.getColor(r42));
        this.descriptionTextView.setPadding(0, 0, 0, AndroidUtilities.dp(6.0f));
        this.descriptionTextView.setBackground(null);
        EditTextBoldCursor r311 = this.descriptionTextView;
        if (LocaleController.isRTL == true) goto L83;
        r15 = 3;
    L83:
        r311.setGravity(r15);
        this.descriptionTextView.setInputType(180225);
        this.descriptionTextView.setImeOptions(6);
        EditTextBoldCursor r312 = this.descriptionTextView;
        if (this.currentUser == null) goto L86;
    L89:
        boolean r5 = true;
    L90:
        r312.setEnabled(r5);
        EditTextBoldCursor r313 = this.descriptionTextView;
        r313.setFocusable(r313.isEnabled());
        this.descriptionTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(255)});
        this.descriptionTextView.setHint(LocaleController.getString("DescriptionOptionalPlaceholder", R.string.DescriptionOptionalPlaceholder));
        this.descriptionTextView.setCursorColor(Theme.getColor(r42));
        this.descriptionTextView.setCursorSize(AndroidUtilities.dp(20.0f));
        this.descriptionTextView.setCursorWidth(1.5f);
        this.settingsContainer.addView(this.descriptionTextView, LayoutHelper.createLinear(-1, -2, 23.0f, 15.0f, 23.0f, 15.0f));
        this.descriptionTextView.setOnEditorActionListener(new ChatEditActivity$$ExternalSyntheticLambda31(this));
        this.descriptionTextView.addTextChangedListener(new AnonymousClass8(this));
        ShadowSectionCell r314 = new ShadowSectionCell(r33);
        this.settingsTopSectionCell = r314;
        r9.addView(r314, LayoutHelper.createLinear(-1, -2));
        LinearLayout r315 = new LinearLayout(r33);
        this.typeEditContainer = r315;
        r315.setOrientation(1);
        r9.addView(this.typeEditContainer, LayoutHelper.createLinear(-1, -2));
        TLRPC.Chat r316 = this.currentChat;
        if (r316 != null) goto L93;
        Context r7 = r33;
        AnonymousClass3 r28 = r2;
        CharSequence r13 = _UrlKt.FRAGMENT_ENCODE_SET;
        Integer r245 = 1;
        float r16 = 30.0f;
        ChatEditActivity r14 = this;
    L166:
        ActionBarMenu r012 = r14.actionBar.createMenu();
        if (r14.currentUser == null) goto L169;
    L172:
        ActionBarMenuItem r013 = r012.addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f));
        r14.doneButton = r013;
        r013.setContentDescription(LocaleController.getString("Done", R.string.Done));
    L174:
        if (r14.locationCell == null) goto L176;
    L183:
        TextInfoPrivacyCell r014 = new TextInfoPrivacyCell(r7, 12, r14.resourceProvider);
        r14.settingsSectionCell = r014;
        if (r14.forumsCell == null) goto L186;
        r014.setText(LocaleController.getString(R.string.ForumToggleDescription));
    L187:
        r9.addView(r14.settingsSectionCell, LayoutHelper.createLinear(-1, -2));
    L188:
        LinearLayout r015 = new LinearLayout(r7);
        r14.infoContainer = r015;
        r015.setOrientation(1);
        r9.addView(r14.infoContainer, LayoutHelper.createLinear(-1, -2));
        if (r14.currentChat == null) goto L284;
        TextCell r016 = new TextCell(r7);
        r14.blockCell = r016;
        r016.setBackground(Theme.getSelectorDrawable(false));
        TextCell r017 = r14.blockCell;
        if (ChatObject.isChannel(r14.currentChat) == true) goto L200;
        TLRPC.Chat r23 = r14.currentChat;
        if (r23.creator == true) goto L200;
        if (ChatObject.hasAdminRights(r23) == true) goto L197;
    L199:
        int r25 = 8;
    L201:
        r017.setVisibility(r25);
        r14.blockCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda7(r14));
        TextCell r018 = new TextCell(r7);
        r14.inviteLinksCell = r018;
        r018.setBackground(Theme.getSelectorDrawable(false));
        r14.inviteLinksCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda8(r14));
        TextCell r019 = new TextCell(r7);
        r14.reactionsCell = r019;
        r019.setBackground(Theme.getSelectorDrawable(false));
        r14.reactionsCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda9(r14));
        if (ChatObject.canUserDoAdminAction(r14.currentChat, 28) == false) goto L204;
        TextCell r020 = new TextCell(r7);
        r14.welcomeMessagesCell = r020;
        r020.setBackground(Theme.getSelectorDrawable(false));
        r14.welcomeMessagesCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda10(r14));
        r14.checkWelcomeMessagesValue();
    L204:
        TextCell r021 = new TextCell(r7);
        r14.adminCell = r021;
        r021.setBackground(Theme.getSelectorDrawable(false));
        r14.adminCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda11(r14));
        TextCell r022 = new TextCell(r7);
        r14.membersCell = r022;
        r022.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        r14.membersCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda13(r14));
        if (ChatObject.isChannelAndNotMegaGroup(r14.currentChat) == true) goto L210;
        if (ChatObject.hasAdminRights(r14.currentChat) == false) goto L210;
        TextCell r023 = new TextCell(r7);
        r14.memberRequestsCell = r023;
        r023.setBackground(Theme.getSelectorDrawable(false));
        r14.memberRequestsCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda14(r14));
    L210:
        if (ChatObject.hasAdminRights(r14.currentChat) == false) goto L213;
        TextCell r024 = new TextCell(r7);
        r14.channelAffiliateProgramsCell = r024;
        r024.setTextAndIcon(applyNewSpan(LocaleController.getString(R.string.ChannelAffiliatePrograms)), R.drawable.menu_feature_premium, false);
        r14.channelAffiliateProgramsCell.setBackground(Theme.getSelectorDrawable(false));
        r14.channelAffiliateProgramsCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda15(r14));
        r14.channelAffiliateProgramsCell.setVisibility(8);
    L213:
        if (ChatObject.isChannel(r14.currentChat) == true) goto L217;
        if (r14.currentChat.gigagroup == true) goto L217;
    L230:
        if (ChatObject.isBoostSupported(r14.currentChat) == false) goto L232;
        TextCell r025 = new TextCell(r7);
        r14.statsAndBoosts = r025;
        r025.setTextAndIcon(LocaleController.getString(R.string.StatisticsAndBoosts), R.drawable.msg_stats, true);
        r14.statsAndBoosts.setBackground(Theme.getSelectorDrawable(false));
        r14.statsAndBoosts.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda17(r14));
    L232:
        r14.infoContainer.addView(r14.reactionsCell, LayoutHelper.createLinear(-1, -2));
        TextCell r026 = r14.welcomeMessagesCell;
        if (r026 == null) goto L236;
        r14.infoContainer.addView(r026, LayoutHelper.createLinear(-1, -2));
    L236:
        if (r14.isChannel == true) goto L241;
        if (r14.currentChat.gigagroup == true) goto L241;
        r14.infoContainer.addView(r14.blockCell, LayoutHelper.createLinear(-1, -2));
    L241:
        if (r14.isChannel == true) goto L243;
        r14.infoContainer.addView(r14.inviteLinksCell, LayoutHelper.createLinear(-1, -2));
    L243:
        r14.infoContainer.addView(r14.adminCell, LayoutHelper.createLinear(-1, -2));
        r14.infoContainer.addView(r14.membersCell, LayoutHelper.createLinear(-1, -2));
        TextCell r027 = r14.memberRequestsCell;
        if (r027 == null) goto L251;
        TLRPC.ChatFull r27 = r14.info;
        if (r27 == null) goto L251;
        if (r27.requests_pending <= 0) goto L251;
        r14.infoContainer.addView(r027, LayoutHelper.createLinear(-1, -2));
    L251:
        if (r14.isChannel == false) goto L254;
        r14.infoContainer.addView(r14.inviteLinksCell, LayoutHelper.createLinear(-1, -2));
    L254:
        if (r14.isChannel == false) goto L256;
    L257:
        r14.infoContainer.addView(r14.blockCell, LayoutHelper.createLinear(-1, -2));
    L258:
        TextCell r028 = r14.statsAndBoosts;
        if (r028 == null) goto L261;
        r14.infoContainer.addView(r028, LayoutHelper.createLinear(-1, -2));
    L261:
        TextCell r029 = r14.logCell;
        if (r029 == null) goto L264;
        r14.infoContainer.addView(r029, LayoutHelper.createLinear(-1, -2));
    L264:
        TextCell r030 = r14.channelAffiliateProgramsCell;
        if (r030 == null) goto L268;
        r14.infoContainer.addView(r030, LayoutHelper.createLinear(-1, -2));
    L268:
        if (r14.channelAffiliateProgramsCell != null) goto L270;
    L274:
        TextCell r031 = r14.logCell;
        if (r031 == null) goto L284;
        TextCell r29 = r14.channelAffiliateProgramsCell;
        if (r29 != null) goto L279;
    L281:
        boolean r210 = false;
    L282:
        r031.setNeedDivider(r210);
        goto L284
    L279:
        if (r29.getVisibility() != 0) goto L281;
        r210 = true;
        goto L282
    L270:
        if (r14.getMessagesController().starrefConnectAllowed == false) goto L274;
        if (ChatObject.isChannelAndNotMegaGroup(r14.currentChat) == false) goto L274;
        r14.channelAffiliateProgramsCell.setVisibility(0);
        goto L274
    L256:
        if (r14.currentChat.gigagroup == false) goto L258;
    L217:
        if (ChatObject.hasAdminRights(r14.currentChat) == false) goto L230;
        TextCell r032 = new TextCell(r7);
        r14.logCell = r032;
        String r211 = LocaleController.getString(R.string.EventLog);
        int r317 = R.drawable.msg_log;
        if (r14.isChannel == true) goto L225;
        TLRPC.ChatFull r43 = r14.info;
        if (r43 == null) goto L225;
        if (r43.can_set_stickers == false) goto L225;
    L226:
        boolean r44 = true;
    L228:
        r032.setTextAndIcon(r211, r317, r44);
        r14.logCell.setBackground(Theme.getSelectorDrawable(false));
        r14.logCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda16(r14));
    L225:
        if (ChatObject.isChannelAndNotMegaGroup(r14.currentChat) == true) goto L226;
        r44 = false;
        goto L228
    L197:
        if (ChatObject.canChangeChatInfo(r14.currentChat) == false) goto L199;
    L200:
        r25 = 0;
    L284:
        if (r14.currentUser == null) goto L301;
        TextCell r033 = new TextCell(r7);
        r14.publicLinkCell = r033;
        r033.setBackground(Theme.getSelectorDrawable(false));
        r14.publicLinkCell.setPrioritizeTitleOverValue(true);
        r14.infoContainer.addView(r14.publicLinkCell, LayoutHelper.createLinear(-1, -2));
        r14.publicLinkCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda18(r14));
        r14.updatePublicLinksCount();
        TextCell r034 = new TextCell(r7);
        r14.botAffiliateProgramCell = r034;
        r034.setBackground(Theme.getSelectorDrawable(false));
        r14.botAffiliateProgramCell.setTextAndValueAndIcon(applyNewSpan(LocaleController.getString(R.string.AffiliateProgramBot)), r13, R.drawable.msg_shareout, true);
        r14.infoContainer.addView(r14.botAffiliateProgramCell, LayoutHelper.createLinear(-1, -2));
        r14.botAffiliateProgramCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda19(r14));
        TextCell r035 = r14.botAffiliateProgramCell;
        if (r14.userInfo != null) goto L288;
        boolean r214 = true;
    L289:
        r035.setDrawLoading(r214, 45, false);
        TLRPC.UserFull r036 = r14.userInfo;
        if (r036 == null) goto L297;
        TextCell r215 = r14.botAffiliateProgramCell;
        if (r036.starref_program != null) goto L294;
        String r037 = LocaleController.getString(R.string.AffiliateProgramBotOff);
    L295:
        r215.setValue(r037, false);
        goto L297
    L294:
        r037 = String.format(Locale.US, "%.1f%%", new Object[]{Float.valueOf(r0.commission_permille / 10.0f)});
    L297:
        if (r14.getMessagesController().starrefProgramAllowed == true) goto L299;
        r14.botAffiliateProgramCell.setVisibility(8);
    L299:
        TextCell r038 = new TextCell(r7);
        r14.editIntroCell = r038;
        r038.setBackground(Theme.getSelectorDrawable(false));
        r14.editIntroCell.setTextAndIcon(LocaleController.getString(R.string.BotEditIntro), R.drawable.msg_log, true);
        r14.infoContainer.addView(r14.editIntroCell, LayoutHelper.createLinear(-1, -2));
        r14.editIntroCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda20(r14));
        TextCell r039 = new TextCell(r7);
        r14.editCommandsCell = r039;
        r039.setBackground(Theme.getSelectorDrawable(false));
        r14.editCommandsCell.setTextAndIcon(LocaleController.getString(R.string.BotEditCommands), R.drawable.msg_media, true);
        r14.infoContainer.addView(r14.editCommandsCell, LayoutHelper.createLinear(-1, -2));
        r14.editCommandsCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda21(r14));
        TextCell r040 = new TextCell(r7);
        r14.changeBotSettingsCell = r040;
        r040.setBackground(Theme.getSelectorDrawable(false));
        r14.changeBotSettingsCell.setTextAndIcon(LocaleController.getString(R.string.BotChangeSettings), R.drawable.msg_bot, true);
        r14.infoContainer.addView(r14.changeBotSettingsCell, LayoutHelper.createLinear(-1, -2));
        r14.changeBotSettingsCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda22(r14));
        goto L301
    L288:
        r214 = false;
    L301:
        if (r14.currentChat == null) goto L307;
        if (r14.stickersCell != null) goto L305;
        ShadowSectionCell r041 = new ShadowSectionCell(r7);
        r14.infoSectionCell = r041;
        r9.addView(r041, LayoutHelper.createLinear(-1, -2));
    L305:
        Integer r8 = r245;
    L365:
        TLRPC.Chat r042 = r14.currentChat;
        if (r042 != null) goto L368;
    L369:
        TLRPC.User r1 = r14.currentUser;
        if (r1 != null) goto L372;
    L412:
        ChatEditActivity r12 = r14;
    L413:
        TLRPC.Chat r043 = r12.currentChat;
        if (r043 != null) goto L416;
    L425:
        UndoView r044 = new UndoView(r7);
        r12.undoView = r044;
        r28.addView(r044, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        EditTextEmoji r045 = r12.nameTextView;
        TLRPC.User r216 = r12.currentUser;
        if (r216 == null) goto L428;
        String r217 = ContactsController.formatName(r216);
    L429:
        r045.setText(Emoji.replaceEmoji(r217, r12.nameTextView.getEditText().getPaint().getFontMetricsInt(), true));
        EditTextEmoji r046 = r12.nameTextView;
        r046.setSelection(r046.length());
        TLRPC.ChatFull r047 = r12.info;
        if (r047 == null) goto L432;
        r12.descriptionTextView.setText(r047.about);
    L435:
        r12.setAvatar();
        r12.updateFields(true, false);
        return r12.fragmentView;
    L432:
        TLRPC.UserFull r048 = r12.userInfo;
        if (r048 == null) goto L435;
        r12.descriptionTextView.setText(r048.about);
        goto L435
    L428:
        r217 = r12.currentChat.title;
        goto L429
    L416:
        if (r043.creator == false) goto L425;
        FrameLayout r049 = new FrameLayout(r7);
        r12.deleteContainer = r049;
        r9.addView(r049, LayoutHelper.createLinear(-1, -2));
        TextSettingsCell r050 = new TextSettingsCell(r7);
        r12.deleteCell = r050;
        r050.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        r12.deleteCell.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        if (r12.currentUser == null) goto L420;
        r12.deleteCell.setText(LocaleController.getString(R.string.DeleteBot), false);
    L424:
        r12.deleteContainer.addView(r12.deleteCell, LayoutHelper.createFrame(-1, -2.0f));
        r12.deleteCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda30(r12));
        ShadowSectionCell r051 = new ShadowSectionCell(r7);
        r12.deleteInfoCell = r051;
        r9.addView(r051, LayoutHelper.createLinear(-1, -2));
        goto L425
    L420:
        boolean r052 = r12.isChannel;
        TextSettingsCell r218 = r12.deleteCell;
        if (r052 == false) goto L423;
        r218.setText(LocaleController.getString(R.string.ChannelDelete), false);
        goto L424
    L423:
        r218.setText(LocaleController.getString(R.string.DeleteAndExitButton), false);
        goto L424
    L372:
        if (r1.bot == false) goto L412;
        if (r1.bot_can_edit == false) goto L412;
    L375:
        if (r042 == null) goto L378;
        long r19 = r042.linked_community_id;
    L377:
        long r52 = r19;
        if (r042 == null) goto L382;
        long r053 = -r042.id;
    L381:
        long r318 = r053;
        if (r14.currentUser == null) goto L386;
        boolean r219 = true;
    L388:
        if (r52 == 0) goto L397;
        CommunityLinkView2 r054 = new CommunityLinkView2(r7, r14.resourceProvider);
        r14.communityLinkView = r054;
        r054.setChat(r14.currentAccount, r14.getMessagesController().getChat(Long.valueOf(r52)));
        r14.communityLinkView.setBackground(Theme.getSelectorDrawable(false));
        r14.communityLinkView.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda27(r14, r52));
        r9.addView(r14.communityLinkView, LayoutHelper.createLinear(-1, -2));
        TextCell r055 = new TextCell(r7);
        r14.communityUnlinkCell = r055;
        if (r219 == false) goto L393;
        int r110 = R.string.CommunityRemoveBotFromCommunity;
    L396:
        r055.setTextAndIcon(LocaleController.getString(r110), R.drawable.outline_community_remove_24, false);
        TextCell r056 = r14.communityUnlinkCell;
        int r111 = Theme.key_text_RedRegular;
        r056.setColors(r111, r111);
        r14.communityUnlinkCell.setBackground(Theme.getSelectorDrawable(false));
        r12 = r14;
        r14.communityUnlinkCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda28(r12, r219, r318, r52));
        r9.addView(r12.communityUnlinkCell, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r057 = new TextInfoPrivacyCell(r7, 12, r12.getResourceProvider());
        r12.communityGapView = r057;
        r057.setFixedSize(14);
        r12.communityGapView.setTag(R.id.fit_width_tag, r8);
        r9.addView(r12.communityGapView, LayoutHelper.createLinear(-1, 14));
        goto L413
    L393:
        if (r14.isChannel == false) goto L395;
        r110 = R.string.CommunityRemoveChannelFromCommunity;
        goto L396
    L395:
        r110 = R.string.CommunityRemoveGroupFromCommunity;
        goto L396
    L397:
        r12 = r14;
        TextCell r058 = new TextCell(r7);
        r12.communityCell = r058;
        r058.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
        r12.communityCell.setTextColor(Theme.getColor(Theme.key_telegram_color_text));
        r12.communityCell.setBackground(Theme.getSelectorDrawable(false));
        TextCell r059 = r12.communityCell;
        if (r219 == false) goto L401;
        int r53 = R.string.CommunityAddBotToCommunity;
    L404:
        r059.setTextAndIcon(LocaleController.getString(r53), R.drawable.msg_groups, false);
        r12.communityCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda29(r12, r318));
        TextInfoPrivacyCell r060 = new TextInfoPrivacyCell(r7, 12, r12.resourceProvider);
        r12.communityInfoCell = r060;
        if (r219 == false) goto L408;
        int r220 = R.string.CommunityAddBotToCommunityInfo;
    L411:
        r060.setText(LocaleController.getString(r220));
        r9.addView(r12.communityCell, LayoutHelper.createLinear(-1, -2));
        r9.addView(r12.communityInfoCell, LayoutHelper.createLinear(-1, -2));
        goto L413
    L408:
        if (r12.isChannel == false) goto L410;
        r220 = R.string.CommunityAddChannelToCommunityInfo;
        goto L411
    L410:
        r220 = R.string.CommunityAddGroupToCommunityInfo;
        goto L411
    L401:
        if (r12.isChannel == false) goto L403;
        r53 = R.string.CommunityAddChannelToCommunity;
        goto L404
    L403:
        r53 = R.string.CommunityAddGroupToCommunity;
        goto L404
    L386:
        r219 = false;
        goto L388
    L382:
        r053 = r14.currentUser.id;
        goto L381
    L378:
        r19 = r14.currentUser.linked_community_id;
        goto L377
    L368:
        if (r042.creator == true) goto L375;
    L307:
        if (r14.currentUser == null) goto L305;
        r14.botInfoCell = new TextInfoPrivacyCell(r7, 12, r14.resourceProvider);
        String r061 = LocaleController.getString(R.string.BotManageInfo);
        SpannableString r221 = SpannableString.valueOf(r061);
        int r062 = r061.indexOf("@BotFather");
        if (r062 == (-1)) goto L311;
        r221.setSpan(new AnonymousClass9(r14), r062, r062 + 10, 33);
    L311:
        r14.botInfoCell.setText(r221);
        r9.addView(r14.botInfoCell, LayoutHelper.createLinear(-1, -2));
        TextCell r063 = new TextCell(r7);
        r14.verifyCell = r063;
        r063.setBackground(Theme.getSelectorDrawable(true));
        r14.verifyCell.setTextAndIcon(LocaleController.getString(R.string.BotVerifyAccounts), R.drawable.menu_factcheck, false);
        TextCell r064 = r14.verifyCell;
        int r224 = Theme.key_windowBackgroundWhiteBlueText4;
        r064.setColors(r224, r224);
        r9.addView(r14.verifyCell, LayoutHelper.createLinear(-1, -2));
        r14.verifyCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda24(r14));
        TextInfoPrivacyCell r065 = new TextInfoPrivacyCell(r7, 12, r14.resourceProvider);
        r14.verifyInfoCell = r065;
        r065.setFixedSize(12);
        r9.addView(r14.verifyInfoCell, LayoutHelper.createLinear(-1, -2));
        TextCell r066 = r14.verifyCell;
        TLRPC.UserFull r225 = r14.userInfo;
        if (r225 == null) goto L318;
        TL_bots.BotInfo r226 = r225.bot_info;
        if (r226 == null) goto L318;
        if (r226.verifier_settings == null) goto L318;
        int r227 = 0;
    L319:
        r066.setVisibility(r227);
        TextInfoPrivacyCell r067 = r14.verifyInfoCell;
        TLRPC.UserFull r228 = r14.userInfo;
        if (r228 == null) goto L326;
        TL_bots.BotInfo r229 = r228.bot_info;
        if (r229 == null) goto L326;
        if (r229.verifier_settings == null) goto L326;
        int r230 = 0;
    L327:
        r067.setVisibility(r230);
        TLRPC.User r068 = r14.currentUser;
        if (r068.bot == false) goto L305;
        if (r068.bot_can_edit == false) goto L305;
        LinearLayout r069 = new LinearLayout(r7);
        r14.balanceContainer = r069;
        r069.setOrientation(1);
        r9.addView(r14.balanceContainer, LayoutHelper.createLinear(-1, -2));
        HeaderCell r070 = new HeaderCell(r7);
        r070.setText(LocaleController.getString(R.string.BotBalance));
        r14.balanceContainer.addView(r070, LayoutHelper.createLinear(-1, -2));
        TextCell r071 = new TextCell(r7);
        r14.tonBalanceCell = r071;
        r071.setBackground(Theme.getSelectorDrawable(false));
        r14.tonBalanceCell.setPrioritizeTitleOverValue(true);
        r14.balanceContainer.addView(r14.tonBalanceCell, LayoutHelper.createLinear(-1, -2));
        BotStarsController r072 = BotStarsController.getInstance(r14.currentAccount);
        r14.tonBalanceCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda25(r14, r072));
        if (r072.isTONBalanceAvailable(r14.userId) == true) goto L334;
        SpannableStringBuilder r231 = new SpannableStringBuilder("x");
        r231.setSpan(new LoadingSpan(r14.tonBalanceCell.valueTextView, AndroidUtilities.dp(r16)), 0, r231.length(), 33);
        r14.tonBalanceCell.setTextAndValueAndIcon(LocaleController.getString(R.string.BotBalanceTON), r231, R.drawable.outline_gram_24, false);
    L341:
        TextCell r112 = r14.tonBalanceCell;
        if (r072.botHasTON(r14.userId) == false) goto L344;
        int r232 = 0;
    L345:
        r112.setVisibility(r232);
        TextCell r113 = new TextCell(r7);
        r14.starsBalanceCell = r113;
        r113.setBackground(Theme.getSelectorDrawable(false));
        r14.starsBalanceCell.setPrioritizeTitleOverValue(true);
        r14.balanceContainer.addView(r14.starsBalanceCell, LayoutHelper.createLinear(-1, -2));
        r14.starsBalanceCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda26(r14, r072));
        if (r072.isStarsBalanceAvailable(r14.userId) == true) goto L348;
        SpannableStringBuilder r114 = new SpannableStringBuilder("x");
        r114.setSpan(new LoadingSpan(r14.starsBalanceCell.valueTextView, AndroidUtilities.dp(r16)), 0, r114.length(), 33);
        r14.starsBalanceCell.setTextAndValueAndIcon(LocaleController.getString(R.string.BotBalanceStars), r114, R.drawable.menu_premium_main, false);
    L353:
        TextCell r115 = r14.starsBalanceCell;
        if (r072.botHasStars(r14.userId) == false) goto L356;
        int r073 = 0;
    L357:
        r115.setVisibility(r073);
        TextInfoPrivacyCell r074 = new TextInfoPrivacyCell(r7, 12, r14.getResourceProvider());
        r074.setFixedSize(12);
        r8 = r245;
        r074.setTag(R.id.fit_width_tag, r8);
        r9.addView(r074, LayoutHelper.createLinear(-1, 12));
        LinearLayout r075 = r14.balanceContainer;
        if (r14.starsBalanceCell.getVisibility() != 0) goto L360;
    L363:
        int r116 = 0;
    L364:
        r075.setVisibility(r116);
        goto L365
    L360:
        if (r14.tonBalanceCell.getVisibility() == 0) goto L363;
        r116 = 8;
        goto L364
    L356:
        r073 = 8;
        goto L357
    L348:
        TextCell r117 = r14.starsBalanceCell;
        String r233 = LocaleController.getString(R.string.BotBalanceStars);
        if (r072.getBotStarsBalance(r14.userId).amount > 0) goto L351;
        CharSequence r45 = r13;
    L352:
        r117.setTextAndValueAndIcon(r233, r45, R.drawable.menu_premium_main, false);
        goto L353
    L351:
        r45 = StarsIntroActivity.replaceStarsWithPlain(TextUtils.concat(new CharSequence[]{"XTR", StarsIntroActivity.formatStarsAmountShort(r072.getBotStarsBalance(r14.userId), 0.85f, ' ')}), 0.85f);
        goto L352
    L344:
        r232 = 8;
        goto L345
    L334:
        long r118 = r072.getTONBalance(r14.userId);
        SpannableStringBuilder r62 = new SpannableStringBuilder();
        if (r118 <= 0) goto L340;
        double r119 = r118 / 1.0E9d;
        if (r119 <= 1000.0d) goto L339;
        r62.append("TON ").append(AndroidUtilities.formatWholeNumber((int) r119, 0));
        goto L340
    L339:
        DecimalFormatSymbols r152 = new DecimalFormatSymbols(Locale.US);
        r152.setDecimalSeparator('.');
        DecimalFormat r319 = new DecimalFormat("#.##", r152);
        r319.setMinimumFractionDigits(2);
        r319.setMaximumFractionDigits(3);
        r319.setGroupingUsed(false);
        r62.append("TON ").append(r319.format(r119));
    L340:
        r14.tonBalanceCell.setTextAndValueAndIcon(LocaleController.getString(R.string.BotBalanceTON), r62, R.drawable.outline_gram_24, true);
    L326:
        r230 = 8;
    L318:
        r227 = 8;
        goto L319
    L186:
        r014.setFixedSize(12);
        goto L187
    L176:
        if (r14.historyCell != null) goto L183;
        if (r14.typeCell != null) goto L183;
        if (r14.linkedCell != null) goto L183;
        if (r14.forumsCell == null) goto L188;
    L169:
        if (ChatObject.canChangeChatInfo(r14.currentChat) == true) goto L172;
        if (r14.historyCell == null) goto L174;
    L93:
        if (r316.megagroup == false) goto L99;
        TLRPC.ChatFull r320 = this.info;
        if (r320 != null) goto L97;
    L100:
        TextCell r321 = new TextCell(r33);
        this.locationCell = r321;
        r321.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        r16 = 30.0f;
        this.typeEditContainer.addView(this.locationCell, LayoutHelper.createLinear(-1, -2));
        this.locationCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda32(this));
    L102:
        if (this.currentChat.creator == false) goto L109;
        TLRPC.ChatFull r322 = this.info;
        if (r322 != null) goto L106;
    L107:
        TextCell r323 = new TextCell(r33);
        this.typeCell = r323;
        r323.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.typeEditContainer.addView(this.typeCell, LayoutHelper.createLinear(-1, -2));
        this.typeCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda33(this));
        goto L109
    L106:
        if (r322.can_set_username == true) goto L107;
    L109:
        if (ChatObject.isChannel(this.currentChat) == false) goto L120;
        if (this.isChannel == false) goto L115;
        if (ChatObject.canUserDoAdminAction(this.currentChat, 1) == false) goto L115;
    L118:
        TextCell r324 = new TextCell(r33);
        this.linkedCell = r324;
        r324.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        this.typeEditContainer.addView(this.linkedCell, LayoutHelper.createLinear(-1, -2));
        this.linkedCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda34(this));
    L115:
        if (this.isChannel == true) goto L120;
        if (ChatObject.canUserDoAdminAction(this.currentChat, 0) == true) goto L118;
    L120:
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == false) goto L127;
        if (this.isChannel == false) goto L127;
        if (ChatObject.canUserDoAdminAction(this.currentChat, 1) == false) goto L127;
        TextCell r325 = new TextCell(r33);
        this.suggestedCell = r325;
        r325.setBackground(Theme.getSelectorDrawable(true));
        this.suggestedCell.setTextAndValueAndIcon(LocaleController.getString(R.string.PostSuggestions), _UrlKt.FRAGMENT_ENCODE_SET, R.drawable.msg_markunread, true);
        this.typeEditContainer.addView(this.suggestedCell, LayoutHelper.createLinear(-1, -2));
        this.suggestedCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda35(this));
    L127:
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == true) goto L129;
    L131:
        r14 = this;
        r28 = r2;
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
    L133:
        if (ChatObject.isChannelAndNotMegaGroup(r14.currentChat) == true) goto L135;
    L137:
        Context r120 = r33;
        r245 = 1;
    L139:
        if (r14.isChannel == true) goto L148;
        if (ChatObject.canBlockUsers(r14.currentChat) == false) goto L148;
        if (ChatObject.isChannel(r14.currentChat) == false) goto L145;
    L146:
        TextCell r076 = new TextCell(r120);
        r14.historyCell = r076;
        r076.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        r14.typeEditContainer.addView(r14.historyCell, LayoutHelper.createLinear(-1, -2));
        r14.historyCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda3(r14, r120));
        goto L148
    L145:
        if (r14.currentChat.creator == true) goto L146;
    L148:
        if (ChatObject.isMegagroup(r14.currentChat) == false) goto L155;
        if (ChatObject.hasAdminRights(r14.currentChat) == false) goto L155;
        if (ChatObject.isCommunity(r14.currentChat) == true) goto L155;
        MessagesController.getInstance(r14.currentAccount).getBoostsController().getBoostsStats(-r14.currentChat.id, new ChatEditActivity$$ExternalSyntheticLambda4(r14));
        PeerColorActivity.ChangeNameColorCell r077 = new PeerColorActivity.ChangeNameColorCell(r14.currentAccount, -r14.currentChat.id, r33, r14.getResourceProvider());
        r14.colorCell = r077;
        r077.setBackground(Theme.getSelectorDrawable(true));
        r14.typeEditContainer.addView(r14.colorCell, LayoutHelper.createLinear(-1, -2));
        r14.colorCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda5(r14));
    L155:
        if (r14.isChannel == false) goto L158;
    L156:
        r7 = r33;
    L164:
        r14.updateColorCell();
        goto L166
    L158:
        if (r14.currentChat.creator == false) goto L156;
        TextCell r078 = new TextCell(r33, 23, false, true, null);
        r7 = r33;
        r14.forumsCell = r078;
        r078.setBackground(Theme.getSelectorDrawable(true));
        r14.forumsCell.setTextAndCheckAndIcon(LocaleController.getString(R.string.ChannelTopics), r14.forum, R.drawable.msg_topics, false);
        Switch r079 = r14.forumsCell.getCheckBox();
        if (r14.canForum == false) goto L162;
        int r121 = 0;
    L163:
        r079.setIcon(r121);
        r14.typeEditContainer.addView(r14.forumsCell, LayoutHelper.createFrame(-1, -2.0f));
        r14.forumsCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda6(r14, r6));
        goto L164
    L162:
        r121 = R.drawable.permission_locked;
        goto L163
    L135:
        if (ChatObject.isCommunity(r14.currentChat) == true) goto L137;
        long r080 = -r14.currentChat.id;
        r120 = r33;
        r245 = 1;
        TextCell r081 = new TextCell(r120, 23, false, true, r14.resourceProvider);
        r14.autoTranslationCell = r081;
        r081.setBackground(Theme.getSelectorDrawable(true));
        r14.autoTranslationCell.setTextAndCheckAndIcon(LocaleController.getString(R.string.ChannelAutotranslation), r14.currentChat.autotranslation, R.drawable.msg_translate, false);
        r14.getMessagesController().getBoostsController().getBoostsStats(r080, new ChatEditActivity$$ExternalSyntheticLambda37(r14));
        r14.typeEditContainer.addView(r14.autoTranslationCell, LayoutHelper.createLinear(-1, -2));
        r14.autoTranslationCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda2(r14, new boolean[]{false}, r080));
        goto L139
    L129:
        if (ChatObject.canChangeChatInfo(this.currentChat) == false) goto L131;
        int r122 = this.currentAccount;
        long r132 = -this.currentChat.id;
        Theme.ResourcesProvider r54 = getResourceProvider();
        r28 = r2;
        r14 = this;
        r13 = _UrlKt.FRAGMENT_ENCODE_SET;
        PeerColorActivity.ChangeNameColorCell r326 = new PeerColorActivity.ChangeNameColorCell(r122, r132, r33, r54);
        r14.colorCell = r326;
        r326.setBackground(Theme.getSelectorDrawable(true));
        r14.typeEditContainer.addView(r14.colorCell, LayoutHelper.createLinear(-1, -2));
        r14.colorCell.setOnClickListener(new ChatEditActivity$$ExternalSyntheticLambda36(r14));
        goto L133
    L97:
        if (r320.can_set_location == true) goto L100;
    L99:
        r16 = 30.0f;
        goto L102
    L86:
        if (ChatObject.canChangeChatInfo(this.currentChat) == true) goto L89;
        r5 = false;
        goto L90
    L77:
        if (ChatObject.canChangeChatInfo(this.currentChat) == false) goto L79;
    L73:
        r26 = 24.0f;
        goto L74
    L70:
        r244 = 96.0f;
        goto L71
    L64:
        if (this.isChannel == false) goto L66;
        r011.setHint(LocaleController.getString("EnterChannelName", R.string.EnterChannelName));
        goto L67
    L66:
        r011.setHint(LocaleController.getString("GroupName", R.string.GroupName));
        goto L67
    L58:
        r243 = 0.0f;
        goto L59
    L55:
        r223 = 24.0f;
        goto L56
    L51:
        r183 = 3;
        goto L52
    L47:
        r242 = 0.0f;
        goto L48
    L44:
        r222 = 24.0f;
        goto L45
    L40:
        r182 = 3;
        goto L41
    L36:
        r24 = 0.0f;
        goto L37
    L33:
        r22 = 24.0f;
        goto L34
    L29:
        r18 = 3;
        goto L30
    L12:
        if (ChatObject.canChangeChatInfo(this.currentChat) == true) goto L26;
        BackupImageView r082 = this.avatarImage;
        boolean r172 = LocaleController.isRTL;
        if (r172 == false) goto L17;
        int r184 = 5;
    L18:
        int r2110 = r184 | 48;
        if (r172 == false) goto L21;
        float r2210 = 0.0f;
    L22:
        if (r172 == false) goto L24;
        float r246 = 24.0f;
    L25:
        r6.addView(r082, LayoutHelper.createFrame(56, 56.0f, r2110, r2210, 20.0f, r246, 12.0f));
        goto L60
    L24:
        r246 = 0.0f;
        goto L25
    L21:
        r2210 = 24.0f;
        goto L22
    L17:
        r184 = 3;
        goto L18
    L8:
        r3.setTitle(LocaleController.getString("Info", R.string.Info));
        goto L9
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r10, int r11, Object... r12) {
        boolean r0 = true;
        int r1 = 0;
        if (r10 != NotificationCenter.chatInfoDidLoad) goto L26;
        TLRPC.ChatFull r102 = (TLRPC.ChatFull) r12[0];
        if (r102.id == this.chatId) goto L7;
        return;
    L7:
        if (this.info != null) goto L12;
        EditTextBoldCursor r112 = this.descriptionTextView;
        if (r112 == null) goto L12;
        r112.setText(r102.about);
    L12:
        if (this.info != null) goto L14;
        boolean r113 = true;
    L15:
        this.info = r102;
        checkWelcomeMessagesValue();
        updateCanForum();
        if (ChatObject.isChannel(this.currentChat) == true) goto L18;
    L21:
        this.historyHidden = r0;
        updateFields(false, false);
        if (r113 == false) goto L158;
        loadLinksCount();
        return;
    L158:
        return;
    L18:
        if (this.info.hidden_prehistory == true) goto L21;
        r0 = false;
        goto L21
    L14:
        r113 = false;
        goto L15
    L26:
        if (r10 != NotificationCenter.chatSwitchedForum) goto L35;
        long r103 = ((Long) r12[0]).longValue();
        boolean r02 = ((Boolean) r12[1]).booleanValue();
        boolean r122 = ((Boolean) r12[2]).booleanValue();
        if (this.chatId != r103) goto L171;
        this.forum = r02;
        this.forumTabs = r122;
        TextCell r9 = this.forumsCell;
        if (r9 == null) goto L159;
        r9.setChecked(r02);
        return;
    L159:
        return;
    L171:
        return;
    L35:
        if (r10 != NotificationCenter.updateInterfaces) goto L44;
        int r104 = ((Integer) r12[0]).intValue();
        if ((MessagesController.UPDATE_MASK_AVATAR & r104) == 0) goto L40;
        setAvatar();
    L40:
        if ((r104 & MessagesController.UPDATE_MASK_NAME) == 0) goto L160;
        updatePublicLinksCount();
        return;
    L160:
        return;
    L44:
        if (r10 != NotificationCenter.channelRightsUpdated) goto L70;
        TLRPC.Chat r105 = (TLRPC.Chat) r12[0];
        if (r105 != null) goto L48;
        return;
    L48:
        if (r105.id != this.chatId) goto L162;
        TLRPC.TL_chatAdminRights r114 = this.chatAdminRights;
        if (r114 != null) goto L52;
    L53:
        TLRPC.TL_chatBannedRights r115 = this.chatBannedRights;
        if (r115 != null) goto L56;
    L57:
        TLRPC.TL_chatBannedRights r116 = this.chatDefaultBannedRights;
        if (r116 != null) goto L60;
        return;
    L60:
        if (r116.equals(r105.default_banned_rights) == true) goto L164;
    L61:
        INavigationLayout r106 = this.parentLayout;
        if (r106 != null) goto L64;
    L67:
        removeSelfFromStack();
        return;
    L64:
        if (r106.getLastFragment() != this) goto L67;
        finishFragment();
        return;
    L164:
        return;
    L56:
        if (r115.equals(r105.banned_rights) == false) goto L61;
    L52:
        if (r114.equals(r105.admin_rights) == false) goto L61;
    L162:
        return;
    L70:
        if (r10 != NotificationCenter.chatAvailableReactionsUpdated) goto L79;
        long r107 = ((Long) r12[0]).longValue();
        if (r107 != this.chatId) goto L165;
        TLRPC.ChatFull r108 = getMessagesController().getChatFull(r107);
        this.info = r108;
        if (r108 == null) goto L76;
        this.availableReactions = r108.available_reactions;
    L76:
        updateReactionsCell(true);
        return;
    L165:
        return;
    L79:
        if (r10 != NotificationCenter.botStarsUpdated) goto L136;
        if (((Long) r12[0]).longValue() == this.userId) goto L83;
        return;
    L83:
        if (this.starsBalanceCell == null) goto L107;
        BotStarsController r109 = BotStarsController.getInstance(this.currentAccount);
        TextCell r123 = this.starsBalanceCell;
        if (r109.botHasStars(this.userId) == false) goto L87;
        int r3 = 0;
    L88:
        r123.setVisibility(r3);
        this.starsBalanceCell.setValue(StarsIntroActivity.replaceStarsWithPlain(TextUtils.concat(new CharSequence[]{"XTR", StarsIntroActivity.formatStarsAmount(r109.getBotStarsBalance(this.userId), 0.8f, ' ')}), 0.85f), true);
        TextCell r124 = this.publicLinkCell;
        if (r124 != null) goto L91;
    L98:
        LinearLayout r1010 = this.balanceContainer;
        if (this.starsBalanceCell.getVisibility() != 0) goto L101;
    L104:
        int r125 = 0;
    L105:
        r1010.setVisibility(r125);
        goto L107
    L101:
        if (this.tonBalanceCell.getVisibility() == 0) goto L104;
        r125 = 8;
        goto L105
    L91:
        if (r109.botHasStars(this.userId) == false) goto L93;
    L96:
        boolean r1011 = true;
    L97:
        r124.setNeedDivider(r1011);
        goto L98
    L93:
        if (r109.botHasTON(this.userId) == true) goto L96;
        r1011 = false;
        goto L97
    L87:
        r3 = 8;
    L107:
        if (this.tonBalanceCell == null) goto L167;
        BotStarsController r1012 = BotStarsController.getInstance(this.currentAccount);
        TextCell r126 = this.tonBalanceCell;
        if (r1012.botHasTON(this.userId) == false) goto L111;
        int r32 = 0;
    L112:
        r126.setVisibility(r32);
        long r33 = r1012.getTONBalance(this.userId);
        SpannableStringBuilder r127 = new SpannableStringBuilder();
        if (r33 <= 0) goto L118;
        double r34 = r33 / 1.0E9d;
        if (r34 <= 1000.0d) goto L117;
        r127.append("TON ").append(AndroidUtilities.formatWholeNumber((int) r34, 0));
        goto L118
    L117:
        DecimalFormatSymbols r5 = new DecimalFormatSymbols(Locale.US);
        r5.setDecimalSeparator('.');
        DecimalFormat r7 = new DecimalFormat("#.##", r5);
        r7.setMinimumFractionDigits(2);
        r7.setMaximumFractionDigits(3);
        r7.setGroupingUsed(false);
        r127.append("TON ").append(r7.format(r34));
    L118:
        this.tonBalanceCell.setValue(r127, true);
        TextCell r128 = this.publicLinkCell;
        if (r128 != null) goto L121;
    L127:
        LinearLayout r1013 = this.balanceContainer;
        if (this.starsBalanceCell.getVisibility() != 0) goto L130;
    L133:
        r1013.setVisibility(r1);
        return;
    L130:
        if (this.tonBalanceCell.getVisibility() == 0) goto L133;
        r1 = 8;
        goto L133
    L121:
        if (r1012.botHasStars(this.userId) == false) goto L123;
    L126:
        r128.setNeedDivider(r0);
        goto L127
    L123:
        if (r1012.botHasTON(this.userId) == true) goto L126;
        r0 = false;
        goto L126
    L111:
        r32 = 8;
        goto L112
    L167:
        return;
    L136:
        if (r10 != NotificationCenter.userInfoDidLoad) goto L142;
        if (((Long) r12[0]).longValue() != this.userId) goto L168;
        setInfo(getMessagesController().getUserFull(this.userId));
        return;
    L168:
        return;
    L142:
        if (r10 != NotificationCenter.channelConnectedBotsUpdate) goto L146;
        ((Long) r12[0]).longValue();
        return;
    L146:
        if (r10 == NotificationCenter.dialogDeleted) goto L148;
        return;
    L148:
        if ((-this.chatId) != ((Long) r12[0]).longValue()) goto L170;
        INavigationLayout r1014 = this.parentLayout;
        if (r1014 != null) goto L152;
    L155:
        removeSelfFromStack();
        return;
    L152:
        if (r1014.getLastFragment() != this) goto L155;
        finishFragment();
        return;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didStartUpload(boolean r1, boolean r2) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(0.0f);
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadPhoto(TLRPC.InputFile r3, TLRPC.InputFile r4, double r5, String r7, TLRPC.PhotoSize r8, TLRPC.PhotoSize r9, boolean r10, TLRPC.VideoSize r11) {
        AndroidUtilities.runOnUIThread(new ChatEditActivity$$ExternalSyntheticLambda39(this, r9, r3, r4, r11, r8, r5, r7));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void dismissCurrentDialog() {
        if (this.imageUpdater.dismissCurrentDialog(this.visibleDialog) == false) goto L5;
        return;
    L5:
        super.dismissCurrentDialog();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean dismissDialogOnPause(Dialog r2) {
        if (this.imageUpdater.dismissDialogOnPause(r2) == true) goto L5;
        return false;
    L5:
        if (super.dismissDialogOnPause(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public String getInitialSearchString() {
        return this.nameTextView.getText().toString();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ChatEditActivity$$ExternalSyntheticLambda40 r8 = new ChatEditActivity$$ExternalSyntheticLambda40(this);
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        TextCell r14 = this.setAvatarCell;
        int r15 = ThemeDescription.FLAG_SELECTOR;
        int r23 = Theme.key_listSelector;
        r1.add(new ThemeDescription(r14, r15, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.setAvatarCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueButton));
        r1.add(new ThemeDescription(this.setAvatarCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueIcon));
        r1.add(new ThemeDescription(this.membersCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        int r32 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.membersCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        int r41 = Theme.key_windowBackgroundWhiteGrayIcon;
        r1.add(new ThemeDescription(this.membersCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
        r1.add(new ThemeDescription(this.adminCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.adminCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.adminCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
        r1.add(new ThemeDescription(this.inviteLinksCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.inviteLinksCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.inviteLinksCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
        if (this.memberRequestsCell == null) goto L5;
        r1.add(new ThemeDescription(this.memberRequestsCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.memberRequestsCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.memberRequestsCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
    L5:
        r1.add(new ThemeDescription(this.blockCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.blockCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.blockCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
        r1.add(new ThemeDescription(this.logCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.logCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.logCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
        r1.add(new ThemeDescription(this.typeCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.typeCell, 0, new Class[]{TextDetailCell.class}, new String[]{"textView"}, null, null, null, r32));
        int r50 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.typeCell, 0, new Class[]{TextDetailCell.class}, new String[]{"valueTextView"}, null, null, null, r50));
        r1.add(new ThemeDescription(this.historyCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.historyCell, 0, new Class[]{TextDetailCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.historyCell, 0, new Class[]{TextDetailCell.class}, new String[]{"valueTextView"}, null, null, null, r50));
        r1.add(new ThemeDescription(this.locationCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.locationCell, 0, new Class[]{TextDetailCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.locationCell, 0, new Class[]{TextDetailCell.class}, new String[]{"valueTextView"}, null, null, null, r50));
        r1.add(new ThemeDescription(this.nameTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r32));
        EditTextEmoji r142 = this.nameTextView;
        int r152 = ThemeDescription.FLAG_HINTTEXTCOLOR;
        int r31 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(r142, r152, null, null, null, null, r31));
        r1.add(new ThemeDescription(this.nameTextView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_windowBackgroundWhiteInputField));
        r1.add(new ThemeDescription(this.nameTextView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_windowBackgroundWhiteInputFieldActivated));
        r1.add(new ThemeDescription(this.descriptionTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r32));
        r1.add(new ThemeDescription(this.descriptionTextView, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r31));
        LinearLayout r143 = this.avatarContainer;
        int r153 = ThemeDescription.FLAG_BACKGROUND;
        int r312 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r143, r153, null, null, null, null, r312));
        r1.add(new ThemeDescription(this.settingsContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r312));
        r1.add(new ThemeDescription(this.typeEditContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r312));
        r1.add(new ThemeDescription(this.deleteContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r312));
        r1.add(new ThemeDescription(this.stickersContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r312));
        r1.add(new ThemeDescription(this.infoContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r312));
        int r313 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(this.settingsTopSectionCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r313));
        r1.add(new ThemeDescription(this.settingsSectionCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r313));
        r1.add(new ThemeDescription(this.deleteInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, r313));
        r1.add(new ThemeDescription(this.deleteCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.deleteCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_text_RedRegular));
        r1.add(new ThemeDescription(this.stickersCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.stickersCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.stickersInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r313));
        r1.add(new ThemeDescription(this.stickersInfoCell, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(null, 0, null, null, Theme.avatarDrawables, r8, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_undo_background));
        int r502 = Theme.key_undo_cancelColor;
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoImageView"}, null, null, null, r502));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoTextView"}, null, null, null, r502));
        int r503 = Theme.key_undo_infoColor;
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"infoTextView"}, null, null, null, r503));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"textPaint"}, null, null, null, r503));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"progressPaint"}, null, null, null, r503));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{UndoView.class}, new String[]{"leftImageView"}, null, null, null, r503));
        r1.add(new ThemeDescription(this.reactionsCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.reactionsCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.reactionsCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
        if (this.suggestedCell == null) goto L9;
        r1.add(new ThemeDescription(this.suggestedCell, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.suggestedCell, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.suggestedCell, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
    L9:
        if (this.statsAndBoosts == null) goto L11;
        r1.add(new ThemeDescription(this.statsAndBoosts, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r23));
        r1.add(new ThemeDescription(this.statsAndBoosts, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.statsAndBoosts, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, r41));
    L11:
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r1, int r2, Intent r3) {
        super.onActivityResultFragment(r1, r2, r3);
        this.imageUpdater.onActivityResult(r1, r2, r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        EditTextEmoji r0 = this.nameTextView;
        if (r0 == null) goto L11;
        if (r0.isPopupShowing() == false) goto L11;
        if (r2 == false) goto L12;
        this.nameTextView.hidePopup(true);
        return false;
    L12:
        return false;
    L11:
        return checkDiscard(r2);
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
        boolean r4 = true;
        if (this.chatId == 0) goto L14;
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        this.currentChat = r0;
        if (r0 != null) goto L27;
        TLRPC.Chat r02 = MessagesStorage.getInstance(this.currentAccount).getChatSync(this.chatId);
        this.currentChat = r02;
        if (r02 == null) goto L12;
        getMessagesController().putChat(this.currentChat, true);
        if (this.info != null) goto L27;
        TLRPC.ChatFull r03 = MessagesStorage.getInstance(this.currentAccount).loadChatInfo(this.chatId, ChatObject.isChannel(this.currentChat), new CountDownLatch(1), false, false);
        this.info = r03;
        if (r03 != null) goto L27;
    L12:
        return false;
    L27:
        TLRPC.Chat r04 = this.currentChat;
        if (r04 == null) goto L51;
        this.chatAdminRights = TLRPC.TL_chatAdminRights.clone(r04.admin_rights);
        this.chatBannedRights = TLRPC.TL_chatBannedRights.clone(this.currentChat.banned_rights);
        this.chatDefaultBannedRights = TLRPC.TL_chatBannedRights.clone(this.currentChat.default_banned_rights);
        this.avatarDrawable.setInfo(5, this.currentChat.title, null);
        if (ChatObject.isChannel(this.currentChat) == true) goto L32;
    L34:
        boolean r05 = false;
    L35:
        this.isChannel = r05;
        TLRPC.Chat r06 = this.currentChat;
        boolean r1 = r06.forum;
        this.forum = r1;
        this.forumTabs = r06.forum_tabs;
        if (this.userId != 0) goto L49;
        if (r1 == true) goto L44;
        TLRPC.ChatFull r12 = this.info;
        if (r12 != null) goto L41;
        int r13 = 0;
    L43:
        if (Math.max(r13, r06.participants_count) < getMessagesController().forumUpgradeParticipantsMin) goto L49;
    L41:
        r13 = r12.participants_count;
    L44:
        TLRPC.ChatFull r07 = this.info;
        if (r07 != null) goto L47;
    L50:
        this.canForum = r4;
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.chatSwitchedForum);
        getNotificationCenter().addObserver(this, NotificationCenter.chatAvailableReactionsUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.channelConnectedBotsUpdate);
    L54:
        ImageUpdater r08 = this.imageUpdater;
        r08.parentFragment = this;
        r08.setDelegate(this);
        getNotificationCenter().addObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        getNotificationCenter().addObserver(this, NotificationCenter.channelRightsUpdated);
        if (this.info == null) goto L58;
        loadLinksCount();
    L58:
        return super.onFragmentCreate();
    L47:
        if (r07.linked_chat_id == 0) goto L50;
    L49:
        r4 = false;
        goto L50
    L32:
        if (this.currentChat.megagroup == true) goto L34;
        r05 = true;
        goto L35
    L51:
        this.avatarDrawable.setInfo(5, this.currentUser.first_name, null);
        this.isChannel = false;
        this.forum = false;
        this.forumTabs = false;
        this.canForum = false;
        getNotificationCenter().addObserver(this, NotificationCenter.userInfoDidLoad);
        if (this.currentUser.bot == false) goto L54;
        getNotificationCenter().addObserver(this, NotificationCenter.botStarsUpdated);
        goto L54
    L14:
        if (this.userId != 0) goto L16;
        TLRPC.User r09 = null;
    L17:
        this.currentUser = r09;
        if (r09 != null) goto L27;
        TLRPC.User r010 = MessagesStorage.getInstance(this.currentAccount).getUserSync(this.userId);
        this.currentUser = r010;
        if (r010 == null) goto L26;
        getMessagesController().putUser(this.currentUser, true);
        if (this.userInfo != null) goto L27;
        HashSet<Long> r011 = new HashSet();
        r011.add(Long.valueOf(this.userId));
        ArrayList<TLRPC.UserFull> r012 = MessagesStorage.getInstance(this.currentAccount).loadUserInfos(r011);
        if (r012.isEmpty() == true) goto L26;
        this.userInfo = r012.get(0);
    L26:
        return false;
    L16:
        r09 = getMessagesController().getUser(Long.valueOf(this.userId));
        goto L17
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L6;
        r0.clear();
    L6:
        if (this.currentChat == null) goto L8;
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatSwitchedForum);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatAvailableReactionsUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.channelConnectedBotsUpdate);
    L11:
        getNotificationCenter().removeObserver(this, NotificationCenter.updateInterfaces);
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
        getNotificationCenter().removeObserver(this, NotificationCenter.channelRightsUpdated);
        EditTextEmoji r2 = this.nameTextView;
        if (r2 == null) goto L15;
        r2.onDestroy();
        return;
    L15:
        return;
    L8:
        getNotificationCenter().removeObserver(this, NotificationCenter.userInfoDidLoad);
        if (this.currentUser.bot == false) goto L11;
        getNotificationCenter().removeObserver(this, NotificationCenter.botStarsUpdated);
        goto L11
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r3, int r4, int r5, int r6) {
        LinearLayout r32 = this.linearLayout;
        if (r32 == null) goto L5;
        r32.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f) + r6);
    L5:
        UndoView r2 = this.undoView;
        if (r2 == null) goto L9;
        r2.setTranslationY(-r6);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        ReactionsUtils.stopPreloadReactions(this.preloadedReactions);
        EditTextEmoji r0 = this.nameTextView;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        UndoView r02 = this.undoView;
        if (r02 == null) goto L8;
        r02.hide(true, 0);
    L8:
        this.imageUpdater.onPause();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        this.imageUpdater.onRequestPermissionsResultFragment(r1, r2, r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        EditTextEmoji r0 = this.nameTextView;
        if (r0 == null) goto L5;
        r0.onResume();
        this.nameTextView.getEditText().requestFocus();
    L5:
        updateColorCell();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        updateFields(true, true);
        this.imageUpdater.onResume();
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void onUploadProgressChanged(float r1) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(r1);
    }

    public void openSetPhotoAlert() {
        ImageUpdater r0 = this.imageUpdater;
        if (this.avatar == null) goto L5;
        boolean r1 = true;
    L6:
        r0.openMenu(r1, new ChatEditActivity$$ExternalSyntheticLambda50(this), new ChatEditActivity$$ExternalSyntheticLambda51(this), 0);
        this.cameraDrawable.setCurrentFrame(0);
        this.cameraDrawable.setCustomEndFrame(43);
        this.setAvatarCell.imageView.playAnimation();
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void restoreSelfArgs(Bundle r2) {
        ImageUpdater r1 = this.imageUpdater;
        if (r1 == null) goto L6;
        r1.currentPicturePath = r2.getString("path");
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void saveSelfArgs(Bundle r3) {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L7;
        String r02 = r0.currentPicturePath;
        if (r02 == null) goto L7;
        r3.putString("path", r02);
    L7:
        EditTextEmoji r2 = this.nameTextView;
        if (r2 == null) goto L13;
        String r22 = r2.getText().toString();
        if (r22.length() == 0) goto L14;
        r3.putString("nameTextView", r22);
        return;
    L14:
        return;
    }

    public void setInfo(TLRPC.ChatFull r4) {
        this.info = r4;
        if (r4 != null) goto L5;
    L21:
        checkWelcomeMessagesValue();
        return;
    L5:
        if (this.currentChat != null) goto L8;
        this.currentChat = getMessagesController().getChat(Long.valueOf(this.chatId));
    L8:
        if (ChatObject.isChannel(this.currentChat) == true) goto L10;
    L13:
        boolean r42 = true;
    L14:
        this.historyHidden = r42;
        this.availableReactions = this.info.available_reactions;
        this.preloadedReactions.clear();
        this.preloadedReactions.addAll(ReactionsUtils.startPreloadReactions(this.currentChat, this.info));
        if (this.channelAffiliateProgramsCell == null) goto L21;
        if (getMessagesController().starrefConnectAllowed == false) goto L21;
        if (ChatObject.isChannelAndNotMegaGroup(this.currentChat) == false) goto L21;
        this.channelAffiliateProgramsCell.setVisibility(0);
        goto L21
    L10:
        if (this.info.hidden_prehistory == true) goto L13;
        r42 = false;
        goto L14
    }

    public void setInfo(TLRPC.UserFull r5) {
        this.userInfo = r5;
        if (r5 != null) goto L5;
        return;
    L5:
        if (this.currentUser == null) goto L7;
    L11:
        TextCell r52 = this.botAffiliateProgramCell;
        int r0 = 0;
        if (r52 != null) goto L14;
    L24:
        TextCell r53 = this.verifyCell;
        if (r53 == null) goto L35;
        TLRPC.UserFull r2 = this.userInfo;
        if (r2 == null) goto L33;
        TL_bots.BotInfo r22 = r2.bot_info;
        if (r22 == null) goto L33;
        if (r22.verifier_settings == null) goto L33;
        int r23 = 0;
    L34:
        r53.setVisibility(r23);
    L33:
        r23 = 8;
    L35:
        TextInfoPrivacyCell r54 = this.verifyInfoCell;
        if (r54 == null) goto L48;
        TLRPC.UserFull r4 = this.userInfo;
        if (r4 == null) goto L44;
        TL_bots.BotInfo r42 = r4.bot_info;
        if (r42 == null) goto L44;
        if (r42.verifier_settings == null) goto L44;
    L45:
        r54.setVisibility(r0);
        return;
    L44:
        r0 = 8;
        goto L45
    L48:
        return;
    L14:
        if (this.userInfo != null) goto L16;
        boolean r1 = true;
    L17:
        r52.setDrawLoading(r1, 45, true);
        TLRPC.UserFull r55 = this.userInfo;
        if (r55 == null) goto L24;
        TextCell r12 = this.botAffiliateProgramCell;
        if (r55.starref_program != null) goto L22;
        String r56 = LocaleController.getString(R.string.AffiliateProgramBotOff);
    L23:
        r12.setValue(r56, false);
        goto L24
    L22:
        r56 = String.format(Locale.US, "%.1f%%", new Object[]{Float.valueOf(r5.commission_permille / 10.0f)});
        goto L23
    L16:
        r1 = false;
        goto L17
    L7:
        if (this.userId != 0) goto L9;
        TLRPC.User r57 = null;
    L10:
        this.currentUser = r57;
        goto L11
    L9:
        r57 = getMessagesController().getUser(Long.valueOf(this.userId));
        goto L10
    }

    public void showConvertTooltip() {
        this.undoView.showWithAction(0, 76, null);
    }

    public void updateColorCell() {
        PeerColorActivity.ChangeNameColorCell r0 = this.colorCell;
        if (r0 == null) goto L24;
        TLRPC.Chat r1 = this.currentChat;
        TextCell r2 = this.historyCell;
        if (r2 != null) goto L7;
    L8:
        TextCell r22 = this.forumsCell;
        if (r22 == null) goto L13;
        if (r22.getVisibility() != 0) goto L13;
    L20:
        boolean r3 = true;
    L22:
        r0.set(r1, r3);
        return;
    L13:
        if (ChatObject.isMegagroup(this.currentChat) == true) goto L15;
    L16:
        TextCell r32 = this.autoTranslationCell;
        if (r32 != null) goto L19;
    L21:
        r3 = false;
        goto L22
    L19:
        if (r32.getVisibility() != 0) goto L21;
    L15:
        if (ChatObject.hasAdminRights(this.currentChat) == true) goto L20;
    L7:
        if (r2.getVisibility() == 0) goto L20;
    }

    public void updateSuggestedCell(Long r6, boolean r7) {
        TLRPC.Chat r72 = this.currentChat;
        if (r72 != null) goto L5;
        return;
    L5:
        if (this.suggestedCell == null) goto L25;
        long r0 = 0;
        if (r6 != null) goto L10;
        boolean r73 = r72.broadcast_messages_allowed;
    L14:
        if (r73 == false) goto L22;
        TLRPC.Chat r74 = getMessagesController().getChat(Long.valueOf(this.currentChat.linked_monoforum_id));
        if (r6 == null) goto L18;
        r0 = r6.longValue();
    L20:
        this.suggestedCell.setTextAndValueAndIcon(LocaleController.getString(R.string.PostSuggestions), StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatString(R.string.PostSuggestionsStars, new Object[]{Long.valueOf(r0)}), 0.66f), R.drawable.msg_markunread, true);
        return;
    L18:
        if (r74 == null) goto L20;
        r0 = r74.send_paid_messages_stars;
        goto L20
    L22:
        this.suggestedCell.setTextAndValueAndIcon(LocaleController.getString(R.string.PostSuggestions), LocaleController.getString(R.string.PostSuggestionsOff), R.drawable.msg_markunread, true);
        return;
    L10:
        if (r6.longValue() < 0) goto L12;
        r73 = true;
        goto L14
    L12:
        r73 = false;
        goto L14
    }

    public void updateSuggestedCell(boolean r2) {
        updateSuggestedCell(null, r2);
    }
}
