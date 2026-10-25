package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.LongSparseArray;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.utils.chats.WidePosts;
import com.exteragram.messenger.utils.ui.ChatHeaderUiHelper;
import com.google.android.gms.cast.MediaError;
import de.robv.android.xposed.callbacks.XCallback;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.AvatarPreviewer;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatActionCell$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.Cells.ChatActionCell$$ExternalSyntheticApiModelOutline1;
import org.telegram.ui.Cells.ChatLoadingCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.ChatMessageUnsupportedCell;
import org.telegram.ui.Cells.ChatUnreadCell;
import org.telegram.ui.Components.AdminLogFilterAlert2;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.ChatScrimPopupContainerLayout;
import org.telegram.ui.Components.ClearHistoryAlert;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.InviteLinkBottomSheet;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.PhonebookShareAlert;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Components.URLSpanMono;
import org.telegram.ui.Components.URLSpanNoUnderline;
import org.telegram.ui.Components.URLSpanReplacement;
import org.telegram.ui.Components.URLSpanUserMention;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceWrapped;
import org.telegram.ui.Components.chat.WallpaperBitmapProvider;
import org.telegram.ui.Components.chat.layouts.ChatActivityChannelButtonsLayout;
import org.telegram.ui.Components.chat.layouts.ChatActivityFadeView;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.recyclerview.ChatListItemAnimator;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;
import org.telegram.utils.glass.positions.GlassPositionsArray;
import org.telegram.utils.glass.positions.GlassPositionsMerger;

/* loaded from: classes6.dex */
public class ChannelAdminLogActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static final int[] allowedNotificationsDuringChatListAnimations = null;
    public static int lastStableId = 10;
    private long activityResumeTime;
    private ArrayList<TLRPC.ChannelParticipant> admins;
    private AspectRatioFrameLayout aspectRatioFrameLayout;
    private ChatAvatarContainer avatarContainer;
    private ChatActivityChannelButtonsLayout bottomOverlayChat2;
    private TextView bottomOverlayChatText;
    private ChatActivityFadeView chatActivityFadeView;
    private ChatActivityAdapter chatAdapter;
    private LinearLayoutManager chatLayoutManager;
    private ChatListItemAnimator chatListItemAnimator;
    private ChatListRecyclerView chatListView;
    private ArrayList<ChatMessageCell> chatMessageCellsCache;
    private RecyclerAnimationScrollHelper chatScrollHelper;
    private final ChatScrollCallback chatScrollHelperCallback;
    private boolean checkTextureViewPosition;
    private ChatActivityFragmentView contentView;
    protected TLRPC.Chat currentChat;
    private TLRPC.TL_channelAdminLogEventsFilter currentFilter;
    private boolean currentFloatingDateOnScreen;
    private boolean currentFloatingTopIsNotMessage;
    private ChatMessageCell dummyMessageCell;
    private ImageView emptyImageView;
    private LinearLayout emptyLayoutView;
    private TextView emptyView;
    private FrameLayout emptyViewContainer;
    private boolean endReached;
    private final HashSet<Long> expandedEvents;
    private final ArrayList<MessageObject> filteredMessages;
    private final ArrayList<Integer> filteredMessagesUpdatedPosition;
    private AnimatorSet floatingDateAnimation;
    private ChatActionCell floatingDateView;
    private final BlurredBackgroundDrawableViewFactory glassBackgroundDrawableFactory;
    private final BlurredBackgroundSourceRenderNode glassBackgroundSourceFrostedRenderNode;
    private final BlurredBackgroundSourceRenderNode glassBackgroundSourceRenderNode;
    public int highlightMessageId;
    public String highlightMessageQuote;
    public boolean highlightMessageQuoteFirst;
    public int highlightMessageQuoteOffset;
    private HashMap<String, Object> invitesCache;
    private boolean linviteLoading;
    private boolean loading;
    private int loadsCount;
    protected ArrayList<MessageObject> messages;
    private final HashMap<String, ArrayList<MessageObject>> messagesByDays;
    private final LongSparseArray<MessageObject> messagesDict;
    private final int[] mid;
    private long minEventId;
    private final BlurredBackgroundDrawableViewFactory navbarContentDrawableFactory;
    private final BlurredBackgroundSourceWrapped navbarContentSourceWallpaper;
    private AnimationNotificationsLocker notificationsLocker;
    private boolean openAnimationEnded;
    private boolean paused;
    private RadialProgressView progressBar;
    private FrameLayout progressView;
    private View progressView2;
    private PhotoViewer.PhotoViewerProvider provider;
    private final LongSparseArray<MessageObject> realMessagesDict;
    private final int recommendedAdditionalSizeY;
    private boolean reloadingLastMessages;
    private FrameLayout roundVideoContainer;
    private long savedScrollEventId;
    private int savedScrollOffset;
    private int savedScrollPosition;
    private ActionBarPopupWindow scrimPopupWindow;
    private int scrimPopupX;
    private int scrimPopupY;
    private boolean scrollByTouch;
    private int scrollCallbackAnimationIndex;
    private int scrollToMessagePosition;
    private int scrollToOffsetOnRecreate;
    private int scrollToPositionOnRecreate;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private boolean scrollingFloatingDate;
    private ImageView searchCalendarButton;
    private FrameLayout searchContainer;
    private SimpleTextView searchCountText;
    private ActionBarMenuItem searchItem;
    private String searchQuery;
    private boolean searchWas;
    private LongSparseArray<TLRPC.User> selectedAdmins;
    private MessageObject selectedObject;
    private TLRPC.ChannelParticipant selectedParticipant;
    public boolean showNoQuoteAlert;
    private final LongSparseArray<Integer> stableIdByEventExpand;
    private final RectF tmpViewRectF;
    private UndoView undoView;
    private Runnable unselectRunnable;
    private HashMap<Long, TLRPC.User> usersMap;
    private TextureView videoTextureView;
    private boolean wasManualScroll;
    private boolean wasPaused;

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass1(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r11, TLRPC.FileLocation r12, int r13, boolean r14, boolean r15) {
            int r132 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getChildCount();
            int r152 = 0;
        L3:
            ImageReceiver r0 = null;
            if (r152 >= r132) goto L37;
            View r1 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getChildAt(r152);
            if ((r1 instanceof ChatMessageCell) == false) goto L14;
            if (r11 == null) goto L33;
            ChatMessageCell r2 = (ChatMessageCell) r1;
            MessageObject r3 = r2.getMessageObject();
            if (r3 == null) goto L33;
            if (r3.getId() != r11.getId()) goto L33;
            r0 = r2.getPhotoImage();
        L33:
            if (r0 != null) goto L34;
            r152 = r152 + 1;
            goto L3
        L34:
            int[] r112 = new int[2];
            r1.getLocationInWindow(r112);
            PhotoViewer.PlaceProviderObject r122 = new PhotoViewer.PlaceProviderObject();
            r122.viewX = r112[0];
            r122.viewY = r112[1];
            r122.parentView = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0);
            r122.imageReceiver = r0;
            r122.thumb = r0.getBitmapSafe();
            r122.radius = r0.getRoundRadius(true);
            r122.isEvent = true;
            return r122;
        L14:
            if ((r1 instanceof ChatActionCell) == false) goto L33;
            ChatActionCell r22 = (ChatActionCell) r1;
            MessageObject r32 = r22.getMessageObject();
            if (r32 == null) goto L33;
            if (r11 != null) goto L19;
            if (r12 == null) goto L33;
            if (r32.photoThumbs == null) goto L33;
            int r4 = 0;
        L26:
            if (r4 >= r32.photoThumbs.size()) goto L33;
            TLRPC.FileLocation r5 = r32.photoThumbs.get(r4).location;
            if (r5.volume_id != r12.volume_id) goto L32;
            if (r5.local_id != r12.local_id) goto L32;
            r0 = r22.getPhotoImage();
        L32:
            r4 = r4 + 1;
            goto L26
        L19:
            if (r32.getId() != r11.getId()) goto L33;
            r0 = r22.getPhotoImage();
            goto L33
        L37:
            return null;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends LinearLayoutManager {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass10(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void scrollToPositionWithOffset(int r1, int r2) {
            super.scrollToPositionWithOffset(r1, r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r2, RecyclerView.State r3, int r4) {
            ChannelAdminLogActivity.m7221$$Nest$fputscrollByTouch(this.this$0, false);
            LinearSmoothScrollerCustom r32 = new LinearSmoothScrollerCustom(r2.getContext(), 0);
            r32.setTargetPosition(r4);
            startSmoothScroll(r32);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends RecyclerView.OnScrollListener {
        private final int scrollValue;
        final /* synthetic */ ChannelAdminLogActivity this$0;
        private float totalDy;

        /* renamed from: org.telegram.ui.ChannelAdminLogActivity$11$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass11 this$1;

            public AnonymousClass1(AnonymousClass11 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (r2.equals(ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$1.this$0)) == false) goto L6;
                ChannelAdminLogActivity.m7218$$Nest$fputfloatingDateAnimation(this.this$1.this$0, null);
                return;
            }
        }

        public AnonymousClass11(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
            this.totalDy = 0.0f;
            this.scrollValue = AndroidUtilities.dp(100.0f);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r3 != 1) goto L6;
            ChannelAdminLogActivity.m7225$$Nest$fputscrollingFloatingDate(this.this$0, true);
            ChannelAdminLogActivity.m7217$$Nest$fputcheckTextureViewPosition(this.this$0, true);
            return;
        L6:
            if (r3 != 0) goto L9;
            ChannelAdminLogActivity.m7225$$Nest$fputscrollingFloatingDate(this.this$0, false);
            ChannelAdminLogActivity.m7217$$Nest$fputcheckTextureViewPosition(this.this$0, false);
            ChannelAdminLogActivity.m7237$$Nest$mhideFloatingDateView(this.this$0, true);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r4, int r5, int r6) {
            ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).invalidate();
            if (r6 != 0) goto L5;
        L14:
            ChannelAdminLogActivity.m7231$$Nest$mcheckScrollForLoad(this.this$0, true);
            ChannelAdminLogActivity.m7243$$Nest$mupdateMessagesVisiblePart(this.this$0);
            return;
        L5:
            if (ChannelAdminLogActivity.m7212$$Nest$fgetscrollingFloatingDate(this.this$0) == false) goto L14;
            if (ChannelAdminLogActivity.m7193$$Nest$fgetcurrentFloatingTopIsNotMessage(this.this$0) == true) goto L14;
            if (ChannelAdminLogActivity.m7199$$Nest$fgetfloatingDateView(this.this$0).getTag() != null) goto L14;
            if (ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$0) == null) goto L13;
            ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$0).cancel();
        L13:
            ChannelAdminLogActivity.m7199$$Nest$fgetfloatingDateView(this.this$0).setTag(1);
            ChannelAdminLogActivity.m7218$$Nest$fputfloatingDateAnimation(this.this$0, new AnimatorSet());
            ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$0).setDuration(150);
            ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$0).playTogether(new Animator[]{ObjectAnimator.ofFloat(ChannelAdminLogActivity.m7199$$Nest$fgetfloatingDateView(this.this$0), "alpha", new float[]{1.0f})});
            ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$0).addListener(new AnonymousClass1(this));
            ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$0).start();
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$12, reason: invalid class name */
    public class AnonymousClass12 extends ClickableSpan {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass12(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            this.this$0.finishFragment();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            super.updateDrawState(r1);
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$13, reason: invalid class name */
    public class AnonymousClass13 implements ClearHistoryAlert.ClearHistoryAlertDelegate {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass13(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ClearHistoryAlert.ClearHistoryAlertDelegate
        public void onAutoDeleteHistory(int r10, int r11) {
            this.this$0.getMessagesController().setDialogHistoryTTL(-this.this$0.currentChat.id, r10);
            TLRPC.ChatFull r102 = this.this$0.getMessagesController().getChatFull(this.this$0.currentChat.id);
            if (r102 == null) goto L6;
            ChannelAdminLogActivity.m7215$$Nest$fgetundoView(this.this$0).showWithAction(-this.this$0.currentChat.id, r11, null, Integer.valueOf(r102.ttl_period), null, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$14, reason: invalid class name */
    public class AnonymousClass14 extends ChatScrimPopupContainerLayout {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass14(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r3) {
            if (r3.getKeyCode() != 4) goto L8;
            if (r3.getRepeatCount() != 0) goto L8;
            ChannelAdminLogActivity.m7232$$Nest$mcloseMenu(this.this$0);
        L8:
            return super.dispatchKeyEvent(r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            boolean r0 = super.dispatchTouchEvent(r2);
            if (r2.getAction() != 0) goto L6;
            if (r0 == true) goto L6;
            ChannelAdminLogActivity.m7232$$Nest$mcloseMenu(this.this$0);
        L6:
            return r0;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$15, reason: invalid class name */
    public class AnonymousClass15 extends ActionBarPopupWindow {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass15(ChannelAdminLogActivity r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            if (ChannelAdminLogActivity.m7209$$Nest$fgetscrimPopupWindow(this.this$0) == this) goto L5;
            return;
        L5:
            Bulletin.hideVisible();
            ChannelAdminLogActivity.m7220$$Nest$fputscrimPopupWindow(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$16, reason: invalid class name */
    public class AnonymousClass16 extends FrameLayout {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass16(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$17, reason: invalid class name */
    public class AnonymousClass17 extends ViewOutlineProvider {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass17(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r1, Outline r2) {
            int r12 = AndroidUtilities.roundMessageSize;
            r2.setOval(0, 0, r12, r12);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$18, reason: invalid class name */
    public class AnonymousClass18 extends TLRPC.ChannelParticipant {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass18(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$19, reason: invalid class name */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass19(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (r2.equals(ChannelAdminLogActivity.m7198$$Nest$fgetfloatingDateAnimation(this.this$0)) == false) goto L6;
            ChannelAdminLogActivity.m7218$$Nest$fputfloatingDateAnimation(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$2, reason: invalid class name */
    public class AnonymousClass2 implements Bulletin.Delegate {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass2(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.dp(51.0f);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$20, reason: invalid class name */
    public class AnonymousClass20 implements InviteLinkBottomSheet.InviteDelegate {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass20(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.InviteLinkBottomSheet.InviteDelegate
        public void linkRevoked(TLRPC.TL_chatInviteExported r11) {
            TLRPC.TL_channelAdminLogEvent r2 = new TLRPC.TL_channelAdminLogEvent();
            int r8 = ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size();
            r11.revoked = true;
            TLRPC.TL_channelAdminLogEventActionExportedInviteRevoke r0 = new TLRPC.TL_channelAdminLogEventActionExportedInviteRevoke();
            r0.invite = r11;
            r2.action = r0;
            r2.date = (int) (System.currentTimeMillis() / 1000);
            r2.user_id = this.this$0.getAccountInstance().getUserConfig().clientUserId;
            int r1 = ChannelAdminLogActivity.access$3300(this.this$0);
            ChannelAdminLogActivity r3 = this.this$0;
            ArrayList<MessageObject> r32 = r3.messages;
            HashMap r4 = ChannelAdminLogActivity.m7202$$Nest$fgetmessagesByDays(r3);
            ChannelAdminLogActivity r5 = this.this$0;
            if (new MessageObject(r1, r2, r32, r4, r5.currentChat, ChannelAdminLogActivity.m7203$$Nest$fgetmid(r5), true).contentType >= 0) goto L5;
            return;
        L5:
            ChannelAdminLogActivity.m7236$$Nest$mfilterDeletedMessages(this.this$0);
            int r02 = ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - r8;
            if (r02 <= 0) goto L8;
            ChannelAdminLogActivity.m7189$$Nest$fgetchatListItemAnimator(this.this$0).setShouldAnimateEnterFromBottom(true);
            ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$0).notifyItemRangeInserted(ChatActivityAdapter.m7247$$Nest$fgetmessagesEndRow(ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$0)), r02);
            ChannelAdminLogActivity.m7239$$Nest$mmoveScrollToLastMessage(this.this$0);
        L8:
            ChannelAdminLogActivity.m7200$$Nest$fgetinvitesCache(this.this$0).remove(r11.link);
        }

        @Override // org.telegram.ui.Components.InviteLinkBottomSheet.InviteDelegate
        public void onLinkDeleted(TLRPC.TL_chatInviteExported r11) {
            int r0 = ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size();
            ChatActivityAdapter.m7247$$Nest$fgetmessagesEndRow(ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$0));
            TLRPC.TL_channelAdminLogEvent r4 = new TLRPC.TL_channelAdminLogEvent();
            TLRPC.TL_channelAdminLogEventActionExportedInviteDelete r1 = new TLRPC.TL_channelAdminLogEventActionExportedInviteDelete();
            r1.invite = r11;
            r4.action = r1;
            r4.date = (int) (System.currentTimeMillis() / 1000);
            r4.user_id = this.this$0.getAccountInstance().getUserConfig().clientUserId;
            int r3 = ChannelAdminLogActivity.access$3400(this.this$0);
            ChannelAdminLogActivity r12 = this.this$0;
            ArrayList<MessageObject> r5 = r12.messages;
            HashMap r6 = ChannelAdminLogActivity.m7202$$Nest$fgetmessagesByDays(r12);
            ChannelAdminLogActivity r13 = this.this$0;
            if (new MessageObject(r3, r4, r5, r6, r13.currentChat, ChannelAdminLogActivity.m7203$$Nest$fgetmid(r13), true).contentType >= 0) goto L5;
            return;
        L5:
            ChannelAdminLogActivity.m7236$$Nest$mfilterDeletedMessages(this.this$0);
            int r14 = ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - r0;
            if (r14 <= 0) goto L8;
            ChannelAdminLogActivity.m7189$$Nest$fgetchatListItemAnimator(this.this$0).setShouldAnimateEnterFromBottom(true);
            ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$0).notifyItemRangeInserted(ChatActivityAdapter.m7247$$Nest$fgetmessagesEndRow(ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$0)), r14);
            ChannelAdminLogActivity.m7239$$Nest$mmoveScrollToLastMessage(this.this$0);
        L8:
            ChannelAdminLogActivity.m7200$$Nest$fgetinvitesCache(this.this$0).remove(r11.link);
        }

        @Override // org.telegram.ui.Components.InviteLinkBottomSheet.InviteDelegate
        public void onLinkEdited(TLRPC.TL_chatInviteExported r9) {
            TLRPC.TL_channelAdminLogEvent r2 = new TLRPC.TL_channelAdminLogEvent();
            TLRPC.TL_channelAdminLogEventActionExportedInviteEdit r0 = new TLRPC.TL_channelAdminLogEventActionExportedInviteEdit();
            r0.new_invite = r9;
            r0.prev_invite = r9;
            r2.action = r0;
            r2.date = (int) (System.currentTimeMillis() / 1000);
            r2.user_id = this.this$0.getAccountInstance().getUserConfig().clientUserId;
            int r1 = ChannelAdminLogActivity.access$3500(this.this$0);
            ChannelAdminLogActivity r92 = this.this$0;
            ArrayList<MessageObject> r3 = r92.messages;
            HashMap r4 = ChannelAdminLogActivity.m7202$$Nest$fgetmessagesByDays(r92);
            ChannelAdminLogActivity r93 = this.this$0;
            if (new MessageObject(r1, r2, r3, r4, r93.currentChat, ChannelAdminLogActivity.m7203$$Nest$fgetmid(r93), true).contentType >= 0) goto L5;
            return;
        L5:
            ChannelAdminLogActivity.m7236$$Nest$mfilterDeletedMessages(this.this$0);
            ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$0).notifyDataSetChanged();
            ChannelAdminLogActivity.m7239$$Nest$mmoveScrollToLastMessage(this.this$0);
        }

        @Override // org.telegram.ui.Components.InviteLinkBottomSheet.InviteDelegate
        public void permanentLinkReplaced(TLRPC.TL_chatInviteExported r1, TLRPC.TL_chatInviteExported r2) {
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$21, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass21 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem = null;

        static {
            int[] r0 = new int[AvatarPreviewer.MenuItem.values().length];
            $SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem = r0;
            r0[AvatarPreviewer.MenuItem.SEND_MESSAGE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L6
        L8:
            $SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem[AvatarPreviewer.MenuItem.OPEN_PROFILE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L7
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass3(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass4(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            ChannelAdminLogActivity.m7226$$Nest$fputsearchQuery(this.this$0, _UrlKt.FRAGMENT_ENCODE_SET);
            ChannelAdminLogActivity.m7185$$Nest$fgetavatarContainer(this.this$0).setVisibility(0);
            if (ChannelAdminLogActivity.m7214$$Nest$fgetsearchWas(this.this$0) == false) goto L5;
            ChannelAdminLogActivity.m7227$$Nest$fputsearchWas(this.this$0, false);
            ChannelAdminLogActivity.m7238$$Nest$mloadMessages(this.this$0, true);
        L5:
            ChannelAdminLogActivity.m7242$$Nest$mupdateBottomOverlay(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            ChannelAdminLogActivity.m7185$$Nest$fgetavatarContainer(this.this$0).setVisibility(8);
            ChannelAdminLogActivity.m7242$$Nest$mupdateBottomOverlay(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchPressed(EditText r3) {
            ChannelAdminLogActivity.m7227$$Nest$fputsearchWas(this.this$0, true);
            ChannelAdminLogActivity.m7226$$Nest$fputsearchQuery(this.this$0, r3.getText().toString());
            ChannelAdminLogActivity.m7238$$Nest$mloadMessages(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends ChatActivityFragmentView {
        final /* synthetic */ ChannelAdminLogActivity this$0;
        private final WallpaperBitmapProvider wallpaperBitmapProvider;

        public AnonymousClass5(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            this.wallpaperBitmapProvider = new WallpaperBitmapProvider();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (AvatarPreviewer.hasVisibleInstance() == false) goto L7;
            AvatarPreviewer.getInstance().onTouchEvent(r2);
            return true;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isStatusBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            MessageObject r0 = MediaController.getInstance().getPlayingMessageObject();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isRoundVideo() == true) goto L7;
            return;
        L7:
            if (r0.eventId != 0) goto L9;
            return;
        L9:
            if (r0.getDialogId() != (-this.this$0.currentChat.id)) goto L15;
            MediaController.getInstance().setTextureView(ChannelAdminLogActivity.m7235$$Nest$mcreateTextureView(this.this$0, false), ChannelAdminLogActivity.m7184$$Nest$fgetaspectRatioFrameLayout(this.this$0), ChannelAdminLogActivity.m7208$$Nest$fgetroundVideoContainer(this.this$0), true);
            return;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
            int r102 = getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r102) goto L55;
            View r2 = getChildAt(r1);
            if (r2.getVisibility() == 8) goto L54;
            FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) r2.getLayoutParams();
            int r4 = r2.getMeasuredWidth();
            int r5 = r2.getMeasuredHeight();
            int r6 = r3.gravity;
            if (r6 != (-1)) goto L10;
            r6 = 51;
        L10:
            int r7 = r6 & 112;
            int r62 = r6 & 7;
            if (r62 != 1) goto L13;
            int r63 = (((r13 - r11) - r4) / 2) + r3.leftMargin;
            int r8 = r3.rightMargin;
        L16:
            int r64 = r63 - r8;
        L19:
            if (r7 != 16) goto L21;
            int r72 = (((r14 - r12) - r5) / 2) + r3.topMargin;
            int r32 = r3.bottomMargin;
        L26:
            int r33 = r72 - r32;
        L34:
            if (r2 != ChannelAdminLogActivity.m7194$$Nest$fgetemptyViewContainer(this.this$0)) goto L42;
            int r73 = AndroidUtilities.dp(24.0f);
            if (ChannelAdminLogActivity.access$700(this.this$0).getVisibility() != 0) goto L38;
            int r82 = ChannelAdminLogActivity.access$800(this.this$0).getMeasuredHeight() / 2;
        L39:
            int r74 = r73 - r82;
        L40:
            r33 = r33 - r74;
        L53:
            r2.layout(r64, r33, r4 + r64, r5 + r33);
            goto L54
        L38:
            r82 = 0;
            goto L39
        L42:
            if (r2 != ChannelAdminLogActivity.access$900(this.this$0)) goto L45;
            r74 = getPaddingTop();
            goto L40
        L45:
            if (r2 != this.backgroundView) goto L47;
        L52:
            r33 = 0;
            goto L53
        L47:
            if (r2 == ChannelAdminLogActivity.m7186$$Nest$fgetchatActivityFadeView(this.this$0)) goto L52;
            if (r2 != ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0)) goto L53;
            r33 = -ChannelAdminLogActivity.m7207$$Nest$fgetrecommendedAdditionalSizeY(this.this$0);
            goto L53
        L21:
            if (r7 != 48) goto L23;
            r33 = r3.topMargin + getPaddingTop();
            if (r2 == ChannelAdminLogActivity.access$400(this.this$0)) goto L34;
            if (ChannelAdminLogActivity.access$500(this.this$0).getVisibility() != 0) goto L34;
            r33 = r33 + ChannelAdminLogActivity.access$600(this.this$0).getMeasuredHeight();
            goto L34
        L23:
            if (r7 == 80) goto L25;
            r33 = r3.topMargin;
            goto L34
        L25:
            r72 = (r14 - r12) - r5;
            r32 = r3.bottomMargin;
            goto L26
        L13:
            if (r62 == 5) goto L15;
            r64 = r3.leftMargin;
            goto L19
        L15:
            r63 = r13 - r4;
            r8 = r3.rightMargin;
        L54:
            r1 = r1 + 1;
            goto L3
        L55:
            ChannelAdminLogActivity.m7243$$Nest$mupdateMessagesVisiblePart(this.this$0);
            notifyHeightChanged();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r12, int r13) {
            int r6 = View.MeasureSpec.getSize(r12);
            int r1 = View.MeasureSpec.getSize(r13);
            int r7 = 0;
            if ((ChannelAdminLogActivity.m7204$$Nest$fgetnavbarContentSourceWallpaper(this.this$0).getSource() instanceof BlurredBackgroundSourceBitmap) == false) goto L5;
            ((BlurredBackgroundSourceBitmap) ChannelAdminLogActivity.m7204$$Nest$fgetnavbarContentSourceWallpaper(this.this$0).getSource()).setParentSize(r6, r1, 0);
        L5:
            setMeasuredDimension(r6, r1);
            int r8 = r1 - getPaddingTop();
            measureChildWithMargins(ChannelAdminLogActivity.access$000(this.this$0), r12, 0, r13, 0);
            int r14 = ChannelAdminLogActivity.access$100(this.this$0).getMeasuredHeight();
            if (ChannelAdminLogActivity.access$200(this.this$0).getVisibility() != 0) goto L8;
            r8 = r8 - r14;
        L8:
            int r9 = getChildCount();
        L9:
            if (r7 >= r9) goto L28;
            View r15 = getChildAt(r7);
            if (r15 == null) goto L27;
            if (r15.getVisibility() == 8) goto L27;
            if (r15 == ChannelAdminLogActivity.access$300(this.this$0)) goto L27;
            if (r15 != ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0)) goto L20;
        L26:
            r15.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((ChannelAdminLogActivity.m7207$$Nest$fgetrecommendedAdditionalSizeY(this.this$0) * 2) + Math.max(AndroidUtilities.dp(10.0f), View.MeasureSpec.getSize(r13)), TLObject.FLAG_30));
            goto L27
        L20:
            if (r15 == ChannelAdminLogActivity.m7205$$Nest$fgetprogressView(this.this$0)) goto L26;
            if (r15 != ChannelAdminLogActivity.m7194$$Nest$fgetemptyViewContainer(this.this$0)) goto L25;
            r15.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r8, TLObject.FLAG_30));
            goto L27
        L25:
            measureChildWithMargins(r15, r12, 0, r13, 0);
        L27:
            r7 = r7 + 1;
            goto L9
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void onUpdateBackgroundDrawable(Drawable r5) {
            super.onUpdateBackgroundDrawable(r5);
            if ((r5 instanceof MotionBackgroundDrawable) == false) goto L5;
            ((MotionBackgroundDrawable) r5).setFastRenderAllowed();
        L5:
            BlurredBackgroundSource r52 = this.wallpaperBitmapProvider.updateSourceFromBackgroundViewDrawable(r5);
            AndroidUtilities.computePerceivedBrightness(this.wallpaperBitmapProvider.getNavigationBarColor(r52));
            if ((r52 instanceof BlurredBackgroundSourceBitmap) == true) goto L8;
        L10:
            ChannelAdminLogActivity.m7204$$Nest$fgetnavbarContentSourceWallpaper(this.this$0).setSource(r52);
            if (ChannelAdminLogActivity.m7186$$Nest$fgetchatActivityFadeView(this.this$0) == null) goto L14;
            ChannelAdminLogActivity.m7186$$Nest$fgetchatActivityFadeView(this.this$0).invalidate();
            return;
        L14:
            return;
        L8:
            if (getMeasuredWidth() <= 0) goto L10;
            ((BlurredBackgroundSourceBitmap) r52).setParentSize(getMeasuredWidth(), getMeasuredHeight(), 0);
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends TextView {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass6(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(r3), AndroidUtilities.dp(220.0f)), View.MeasureSpec.getMode(r3)), r4);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends ChatListRecyclerView {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass7(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r11, View r12, long r13) {
            if ((r12 instanceof ChatMessageUnsupportedCell) == false) goto L5;
            r11.save();
            r11.translate(r12.getX(), r12.getY());
            ((ChatMessageUnsupportedCell) r12).drawBackground(r11);
            r11.restore();
        L5:
            boolean r132 = super.drawChild(r11, r12, r13);
            if ((r12 instanceof ChatMessageCell) == false) goto L69;
            ChatMessageCell r14 = (ChatMessageCell) r12;
            ImageReceiver r0 = r14.getAvatarImage();
            if (r0 == null) goto L69;
            if (r14.getMessageObject().deleted == false) goto L12;
        L14:
            boolean r1 = false;
        L16:
            if (r14.getMessageObject().deleted == false) goto L19;
            r0.setVisible(false, false);
            return r132;
        L19:
            int r4 = (int) r12.getY();
            if (r14.drawPinnedBottom() == false) goto L27;
            int r5 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getChildViewHolder(r12).getAdapterPosition();
            if (r5 < 0) goto L27;
            if (ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).findViewHolderForAdapterPosition(r5 + 1) == null) goto L27;
            r0.setVisible(false, false);
            return r132;
        L27:
            float r52 = r14.getSlidingOffsetX() + r14.getCheckBoxTranslation();
            int r6 = ((int) r12.getY()) + r14.getLayoutHeight();
            int r7 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getMeasuredHeight() - ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getPaddingBottom();
            if (r6 <= r7) goto L31;
            r6 = r7;
        L31:
            if (r14.drawPinnedTop() == false) goto L45;
            int r122 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getChildViewHolder(r12).getAdapterPosition();
            if (r122 < 0) goto L45;
            int r72 = 0;
        L36:
            if (r72 >= 20) goto L45;
            r72 = r72 + 1;
            r122 = r122 - 1;
            RecyclerView.ViewHolder r8 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).findViewHolderForAdapterPosition(r122);
            if (r8 == null) goto L45;
            r4 = r8.itemView.getTop();
            View r82 = r8.itemView;
            if ((r82 instanceof ChatMessageCell) == false) goto L45;
            r14 = (ChatMessageCell) r82;
            if (r14.drawPinnedTop() == true) goto L36;
        L45:
            if ((r6 - AndroidUtilities.dp(48.0f)) >= r4) goto L48;
            r6 = r4 + AndroidUtilities.dp(48.0f);
        L48:
            if (r14.drawPinnedBottom() == true) goto L52;
            int r10 = (int) (r14.getY() + r14.getMeasuredHeight());
            if (r6 <= r10) goto L52;
            r6 = r10;
        L52:
            r11.save();
            if (r52 == 0.0f) goto L56;
            r11.translate(r52, 0.0f);
        L56:
            if (r14.getCurrentMessagesGroup() != null) goto L58;
        L60:
            if (r1 == false) goto L63;
            r0.setImageY(r6 - AndroidUtilities.dp(44.0f));
        L63:
            if (r14.shouldDrawAlphaLayer() == false) goto L65;
            r0.setAlpha(r14.getAlpha());
            r11.scale(r14.getScaleX(), r14.getScaleY(), r14.getX() + r14.getPivotX(), r14.getY() + (r14.getHeight() >> 1));
        L66:
            if (r1 == false) goto L68;
            r0.setVisible(true, false);
        L68:
            r0.draw(r11);
            r11.restore();
            goto L69
        L65:
            r0.setAlpha(1.0f);
            goto L66
        L58:
            if (r14.getCurrentMessagesGroup().transitionParams.backgroundChangeBounds == false) goto L60;
            r6 = (int) (r6 - r14.getTranslationY());
            goto L60
        L12:
            if (ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getChildAdapterPosition(r14) == (-1)) goto L14;
            r1 = true;
        L69:
            return r132;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            this.this$0.applyScrolledPosition();
            super.onLayout(r2, r3, r4, r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements RecyclerListView.OnItemClickListenerExtended {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public AnonymousClass8(ChannelAdminLogActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListenerExtended
        public void onItemClick(View r5, int r6, float r7, float r8) {
            if ((r5 instanceof ChatActionCell) == false) goto L14;
            MessageObject r62 = ((ChatActionCell) r5).getMessageObject();
            if (r62 == null) goto L14;
            if (r62.actionDeleteGroupEventId == (-1)) goto L14;
            boolean r52 = ChannelAdminLogActivity.m7196$$Nest$fgetexpandedEvents(this.this$0).contains(Long.valueOf(r62.actionDeleteGroupEventId));
            ChannelAdminLogActivity r72 = this.this$0;
            if (r52 == false) goto L11;
            ChannelAdminLogActivity.m7196$$Nest$fgetexpandedEvents(r72).remove(Long.valueOf(r62.actionDeleteGroupEventId));
        L12:
            this.this$0.saveScrollPosition(true);
            ChannelAdminLogActivity.m7236$$Nest$mfilterDeletedMessages(this.this$0);
            ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$0).notifyDataSetChanged();
            return;
        L11:
            ChannelAdminLogActivity.m7196$$Nest$fgetexpandedEvents(r72).add(Long.valueOf(r62.actionDeleteGroupEventId));
        L14:
            ChannelAdminLogActivity.m7234$$Nest$mcreateMenu(this.this$0, r5, r7, r8);
        }
    }

    /* renamed from: org.telegram.ui.ChannelAdminLogActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends ChatListItemAnimator {
        Runnable finishRunnable;
        int scrollAnimationIndex;
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public static /* synthetic */ void $r8$lambda$I6AR2HSjhX7IFqYXreHtlPdB8SY(AnonymousClass9 r0) {
            r0.lambda$onAllAnimationsDone$0();
        }

        public AnonymousClass9(ChannelAdminLogActivity r1, ChatActivity r2, RecyclerListView r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.scrollAnimationIndex = -1;
        }

        private /* synthetic */ void lambda$onAllAnimationsDone$0() {
            if (this.scrollAnimationIndex == (-1)) goto L6;
            this.this$0.getNotificationCenter().onAnimationFinish(this.scrollAnimationIndex);
            this.scrollAnimationIndex = -1;
        L6:
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("admin logs chatItemAnimator enable notifications");
            return;
        }

        @Override // org.telegram.ui.recyclerview.ChatListItemAnimator, androidx.recyclerview.widget.DefaultItemAnimator
        public void onAllAnimationsDone() {
            super.onAllAnimationsDone();
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L5:
            ChannelAdminLogActivity$9$$ExternalSyntheticLambda0 r02 = new ChannelAdminLogActivity$9$$ExternalSyntheticLambda0(this);
            this.finishRunnable = r02;
            AndroidUtilities.runOnUIThread(r02);
        }

        @Override // org.telegram.ui.recyclerview.ChatListItemAnimator
        public void onAnimationStart() {
            if (this.scrollAnimationIndex != (-1)) goto L5;
            this.scrollAnimationIndex = this.this$0.getNotificationCenter().setAnimationInProgress(this.scrollAnimationIndex, ChannelAdminLogActivity.m7245$$Nest$sfgetallowedNotificationsDuringChatListAnimations(), false);
        L5:
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.finishRunnable = null;
        L9:
            if (BuildVars.LOGS_ENABLED == false) goto L12;
            FileLog.d("admin logs chatItemAnimator disable notifications");
            return;
        }
    }

    public class ChatActivityAdapter extends RecyclerView.Adapter {
        private int loadingUpRow;
        private Context mContext;
        private int messagesEndRow;
        private int messagesStartRow;
        private final ArrayList<Long> oldStableIds;
        private int rowCount;
        private final ArrayList<Long> stableIds;
        final /* synthetic */ ChannelAdminLogActivity this$0;

        /* renamed from: org.telegram.ui.ChannelAdminLogActivity$ChatActivityAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements ChatMessageCell.ChatMessageCellDelegate {
            final /* synthetic */ ChatActivityAdapter this$1;

            public static /* synthetic */ void $r8$lambda$PZYvPZY4gyx1ESPZavwi4ki_qRE(AnonymousClass1 r0, String r1, DialogInterface r2, int r3) {
                r0.lambda$didPressUrl$1(r1, r2, r3);
            }

            /* renamed from: $r8$lambda$gPv5BnIYihm-o5uh74rTygcoy_Y, reason: not valid java name */
            public static /* synthetic */ void m7249$r8$lambda$gPv5BnIYihmo5uh74rTygcoy_Y(AnonymousClass1 r0, ChatMessageCell r1, TLRPC.User r2, AvatarPreviewer.MenuItem r3) {
                r0.lambda$didLongPressUserAvatar$0(r1, r2, r3);
            }

            public AnonymousClass1(ChatActivityAdapter r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$didLongPressUserAvatar$0(ChatMessageCell r2, TLRPC.User r3, AvatarPreviewer.MenuItem r4) {
                int r42 = AnonymousClass21.$SwitchMap$org$telegram$ui$AvatarPreviewer$MenuItem[r4.ordinal()];
                if (r42 != 1) goto L5;
                openDialog(r2, r3);
                return;
            L5:
                if (r42 == 2) goto L7;
                return;
            L7:
                openProfile(r3);
            }

            private /* synthetic */ void lambda$didPressUrl$1(String r1, DialogInterface r2, int r3) {
                if (r3 != 0) goto L6;
                Browser.openUrl(this.this$1.this$0.getParentActivity(), r1, true);
                return;
            L6:
                if (r3 == 1) goto L8;
                return;
            L8:
                if (r1.startsWith("mailto:") == false) goto L11;
                r1 = r1.substring(7);
            L13:
                AndroidUtilities.addToClipboard(r1);
                return;
            L11:
                if (r1.startsWith("tel:") == false) goto L13;
                r1 = r1.substring(4);
                goto L13
            }

            private void openDialog(ChatMessageCell r4, TLRPC.User r5) {
                if (r5 == null) goto L7;
                Bundle r42 = new Bundle();
                r42.putLong("user_id", r5.id);
                if (this.this$1.this$0.getMessagesController().checkCanOpenChat(r42, this.this$1.this$0) == false) goto L8;
                this.this$1.this$0.presentFragment(new ChatActivity(r42));
                return;
            L8:
                return;
            }

            private void openProfile(TLRPC.User r5) {
                Bundle r0 = new Bundle();
                r0.putLong("user_id", r5.id);
                ChannelAdminLogActivity.m7229$$Nest$maddCanBanUser(this.this$1.this$0, r0, r5.id);
                ProfileActivity r52 = new ProfileActivity(r0);
                r52.setPlayProfileAnimation(0);
                this.this$1.this$0.presentFragment(r52);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean canDrawOutboundsContent() {
                return true;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean canPerformActions() {
                return true;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didLongPress(ChatMessageCell r1, float r2, float r3) {
                ChannelAdminLogActivity.m7233$$Nest$mcreateMenu(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean didLongPressUserAvatar(ChatMessageCell r4, TLRPC.User r5, float r6, float r7) {
                if (r5 != null) goto L4;
                return false;
            L4:
                if (r5.id == UserConfig.getInstance(ChannelAdminLogActivity.access$1400(this.this$1.this$0)).getClientUserId()) goto L18;
                TLRPC.User r62 = this.this$1.this$0.getMessagesController().getUser(Long.valueOf(r5.id));
                if (r62 == null) goto L8;
                r5 = r62;
            L8:
                AvatarPreviewer.MenuItem[] r63 = {AvatarPreviewer.MenuItem.OPEN_PROFILE, AvatarPreviewer.MenuItem.SEND_MESSAGE};
                TLRPC.UserFull r72 = this.this$1.this$0.getMessagesController().getUserFull(r5.id);
                if (r72 == null) goto L11;
                AvatarPreviewer.Data r64 = AvatarPreviewer.Data.of(r5, r72, r63);
            L13:
                if (AvatarPreviewer.canPreview(r64) == false) goto L19;
                AvatarPreviewer r73 = AvatarPreviewer.getInstance();
                ChannelAdminLogActivity r0 = this.this$1.this$0;
                r73.show((ViewGroup) r0.fragmentView, r0.getResourceProvider(), r64, new ChannelAdminLogActivity$ChatActivityAdapter$1$$ExternalSyntheticLambda1(this, r4, r5));
                return true;
            L19:
                return false;
            L11:
                r64 = AvatarPreviewer.Data.of(r5, ChannelAdminLogActivity.access$1500(this.this$1.this$0), r63);
                goto L13
            L18:
                return false;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressAppUpdateButton() {
                LaunchActivity r2 = LaunchActivity.instance;
                if (r2 == null) goto L6;
                r2.checkAppUpdate(true, null);
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressBotButton(ChatMessageCell r3, TL_keyboard.KeyboardButtonProto r4) {
                MessageObject r32 = r3.getMessageObject();
                boolean r42 = ChannelAdminLogActivity.m7196$$Nest$fgetexpandedEvents(this.this$1.this$0).contains(Long.valueOf(r32.eventId));
                ChatActivityAdapter r0 = this.this$1;
                if (r42 == false) goto L5;
                ChannelAdminLogActivity.m7196$$Nest$fgetexpandedEvents(r0.this$0).remove(Long.valueOf(r32.eventId));
            L6:
                this.this$1.this$0.saveScrollPosition(true);
                ChannelAdminLogActivity.m7236$$Nest$mfilterDeletedMessages(this.this$1.this$0);
                ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(this.this$1.this$0).notifyDataSetChanged();
                return;
            L5:
                ChannelAdminLogActivity.m7196$$Nest$fgetexpandedEvents(r0.this$0).add(Long.valueOf(r32.eventId));
                goto L6
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressCancelSendButton(ChatMessageCell r1) {
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressChannelAvatar(ChatMessageCell r1, TLRPC.Chat r2, int r3, float r4, float r5, boolean r6) {
                if (r2 != null) goto L4;
                return;
            L4:
                if (r2 == this.this$1.this$0.currentChat) goto L13;
                Bundle r12 = new Bundle();
                r12.putLong("chat_id", r2.id);
                if (r3 == 0) goto L9;
                r12.putInt("message_id", r3);
            L9:
                if (MessagesController.getInstance(ChannelAdminLogActivity.access$1200(this.this$1.this$0)).checkCanOpenChat(r12, this.this$1.this$0) == false) goto L14;
                this.this$1.this$0.presentFragment(new ChatActivity(r12), true);
                return;
            L14:
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressImage(ChatMessageCell r11, float r12, float r13, boolean r14) {
                MessageObject r1 = r11.getMessageObject();
                if (r1.getInputStickerSet() == null) goto L7;
                this.this$1.this$0.showDialog(new StickersAlert(this.this$1.this$0.getParentActivity(), this.this$1.this$0, r1.getInputStickerSet(), null, null, false));
                return;
            L7:
                if (r1.isVideo() == true) goto L72;
                int r112 = r1.type;
                if (r112 == 1) goto L72;
                if (r112 != 0) goto L14;
                if (r1.isWebpageDocument() == false) goto L72;
            L14:
                if (r1.isGif() == true) goto L72;
                int r113 = r1.type;
                File r142 = null;
                if (r113 != 3) goto L31;
                String r114 = r1.messageOwner.attachPath;     // Catch: Exception -> L29
                if (r114 != null) goto L21;
            L23:
                if (r142 != null) goto L25;
            L26:
                r142 = this.this$1.this$0.getFileLoader().getPathToMessage(r1.messageOwner);     // Catch: Exception -> L29
            L27:
                Intent r115 = new Intent("android.intent.action.VIEW");     // Catch: Exception -> L29
                r115.setFlags(1);     // Catch: Exception -> L29
                r115.setDataAndType(FileProvider.getUriForFile(this.this$1.this$0.getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", r142), "video/mp4");     // Catch: Exception -> L29
                this.this$1.this$0.getParentActivity().startActivityForResult(r115, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L29
                return;
            L25:
                if (r142.exists() == true) goto L27;
            L21:
                if (r114.length() == 0) goto L23;
                r142 = new File(r1.messageOwner.attachPath);     // Catch: Exception -> L29
            L29:
                ChannelAdminLogActivity.m7230$$Nest$malertUserOpenError(this.this$1.this$0, r1);
                return;
            L31:
                if (r113 != 4) goto L38;
                if (AndroidUtilities.isMapsInstalled(this.this$1.this$0) == false) goto L80;
                LocationActivity r116 = new LocationActivity(0);
                r116.setMessageObject(r1);
                this.this$1.this$0.presentFragment(r116);
                return;
            L80:
                return;
            L38:
                if (r113 == 9) goto L41;
                if (r113 == 0) goto L41;
                return;
            L41:
                if (r1.getDocumentName().toLowerCase().endsWith("attheme") == false) goto L74;
                String r117 = r1.messageOwner.attachPath;
                if (r117 != null) goto L45;
            L49:
                File r118 = null;
            L50:
                if (r118 != null) goto L55;
                File r132 = this.this$1.this$0.getFileLoader().getPathToMessage(r1.messageOwner);
                if (r132.exists() == false) goto L55;
                r118 = r132;
            L55:
                if (ChannelAdminLogActivity.m7188$$Nest$fgetchatLayoutManager(this.this$1.this$0) == null) goto L63;
                int r133 = ChannelAdminLogActivity.m7188$$Nest$fgetchatLayoutManager(this.this$1.this$0).findLastVisibleItemPosition();
                int r3 = ChannelAdminLogActivity.m7188$$Nest$fgetchatLayoutManager(this.this$1.this$0).getItemCount() - 1;
                ChatActivityAdapter r4 = this.this$1;
                if (r133 >= r3) goto L62;
                ChannelAdminLogActivity r134 = r4.this$0;
                ChannelAdminLogActivity.m7224$$Nest$fputscrollToPositionOnRecreate(r134, ChannelAdminLogActivity.m7188$$Nest$fgetchatLayoutManager(r134).findFirstVisibleItemPosition());
                RecyclerListView.Holder r135 = (RecyclerListView.Holder) ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$1.this$0).findViewHolderForAdapterPosition(ChannelAdminLogActivity.m7211$$Nest$fgetscrollToPositionOnRecreate(this.this$1.this$0));
                ChatActivityAdapter r32 = this.this$1;
                if (r135 == null) goto L61;
                ChannelAdminLogActivity.m7223$$Nest$fputscrollToOffsetOnRecreate(r32.this$0, r135.itemView.getTop());
                goto L63
            L61:
                ChannelAdminLogActivity.m7224$$Nest$fputscrollToPositionOnRecreate(r32.this$0, -1);
                goto L63
            L62:
                ChannelAdminLogActivity.m7224$$Nest$fputscrollToPositionOnRecreate(r4.this$0, -1);
            L63:
                Theme.ThemeInfo r119 = Theme.applyThemeFile(r118, r1.getDocumentName(), null, true);
                ChatActivityAdapter r122 = this.this$1;
                if (r119 == null) goto L67;
                r122.this$0.presentFragment(new ThemePreviewActivity(r119));
                return;
            L67:
                ChannelAdminLogActivity.m7224$$Nest$fputscrollToPositionOnRecreate(r122.this$0, -1);
                goto L74
            L45:
                if (r117.length() == 0) goto L49;
                r118 = new File(r1.messageOwner.attachPath);
                if (r118.exists() == false) goto L49;
            L74:
                AndroidUtilities.openForView(r1, this.this$1.this$0.getParentActivity(), null, false);     // Catch: Exception -> L70
                return;
            L70:
                ChannelAdminLogActivity.m7230$$Nest$malertUserOpenError(this.this$1.this$0, r1);
                return;
            L72:
                PhotoViewer.getInstance().setParentActivity(this.this$1.this$0);
                PhotoViewer.getInstance().openPhoto(r1, null, 0, 0, 0, ChannelAdminLogActivity.m7206$$Nest$fgetprovider(this.this$1.this$0));
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressInstantButton(ChatMessageCell r5, int r6) {
                MessageObject r52 = r5.getMessageObject();
                TLRPC.TL_channelAdminLogEvent r0 = r52.currentEvent;
                if (r0 != null) goto L5;
            L11:
                if (r6 != 0) goto L28;
                TLRPC.MessageMedia r62 = r52.messageOwner.media;
                if (r62 == null) goto L37;
                TLRPC.WebPage r63 = r62.webpage;
                if (r63 != null) goto L17;
                return;
            L17:
                if (r63.cached_page == null) goto L39;
                LaunchActivity r64 = LaunchActivity.instance;
                if (r64 != null) goto L21;
            L25:
                this.this$1.this$0.createArticleViewer(false).open(r52);
                return;
            L21:
                if (r64.getBottomSheetTabs() == null) goto L25;
                if (LaunchActivity.instance.getBottomSheetTabs().tryReopenTab(r52) == null) goto L25;
                return;
            L39:
                return;
            L37:
                return;
            L28:
                if (r6 != 5) goto L31;
                ChannelAdminLogActivity r4 = this.this$1.this$0;
                TLRPC.User r65 = r4.getMessagesController().getUser(Long.valueOf(r52.messageOwner.media.user_id));
                TLRPC.MessageMedia r53 = r52.messageOwner.media;
                r4.openVCard(r65, r53.vcard, r53.first_name, r53.last_name);
                return;
            L31:
                TLRPC.MessageMedia r66 = r52.messageOwner.media;
                if (r66 != null) goto L34;
                return;
            L34:
                if (r66.webpage == null) goto L41;
                Browser.openUrl(this.this$1.this$0.getParentActivity(), r52.messageOwner.media.webpage.url);
                return;
            L41:
                return;
            L5:
                if ((r0.action instanceof TLRPC.TL_channelAdminLogEventActionEditMessage) == false) goto L11;
                Bundle r67 = new Bundle();
                r67.putLong("chat_id", -r52.getDialogId());
                r67.putInt("message_id", r52.getRealId());
                ChatActivity r02 = new ChatActivity(r67);
                if (ChatObject.isForum(this.this$1.this$0.currentChat) == false) goto L9;
                ForumUtilities.applyTopic(r02, MessagesStorage.TopicKey.of(r52.getDialogId(), MessageObject.getTopicId(ChannelAdminLogActivity.access$2100(this.this$1.this$0), r52.messageOwner, true)));
            L9:
                this.this$1.this$0.presentFragment(r02);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressOther(ChatMessageCell r1, float r2, float r3) {
                ChannelAdminLogActivity.m7233$$Nest$mcreateMenu(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressReplyMessage(ChatMessageCell r2, int r3, float r4, float r5, boolean r6) {
                MessageObject r22 = r2.getMessageObject().replyMessageObject;
                if (r22.getDialogId() != (-this.this$1.this$0.currentChat.id)) goto L16;
                int r32 = 0;
            L6:
                if (r32 >= ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$1.this$0).size()) goto L16;
                MessageObject r42 = (MessageObject) ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$1.this$0).get(r32);
                if (r42 == null) goto L15;
                if (r42.contentType == 1) goto L15;
                if (r42.getRealId() != r22.getRealId()) goto L15;
                this.this$1.this$0.scrollToMessage(r42, true);
                return;
            L15:
                r32 = r32 + 1;
            L16:
                Bundle r33 = new Bundle();
                r33.putLong("chat_id", this.this$1.this$0.currentChat.id);
                r33.putInt("message_id", r22.getRealId());
                this.this$1.this$0.presentFragment(new ChatActivity(r33));
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressSideButton(ChatMessageCell r9) {
                if (this.this$1.this$0.getParentActivity() != null) goto L5;
                return;
            L5:
                ChatActivityAdapter r0 = this.this$1;
                ChannelAdminLogActivity r1 = r0.this$0;
                Context r2 = ChatActivityAdapter.m7246$$Nest$fgetmContext(r0);
                MessageObject r3 = r9.getMessageObject();
                if (ChatObject.isChannel(this.this$1.this$0.currentChat) == true) goto L8;
            L11:
                boolean r8 = false;
            L12:
                r1.showDialog(ShareAlert.createShareAlert(r2, r3, null, r8, null, false));
                return;
            L8:
                if (this.this$1.this$0.currentChat.megagroup == true) goto L11;
                r8 = true;
                goto L12
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressUrl(ChatMessageCell r7, CharacterStyle r8, boolean r9) {
                if (r8 == null) goto L69;
                MessageObject r72 = r7.getMessageObject();
                if ((r8 instanceof URLSpanMono) == false) goto L11;
                ((URLSpanMono) r8).copyToClipboard();
                if (AndroidUtilities.shouldShowClipboardToast() == false) goto L60;
                Toast.makeText(this.this$1.this$0.getParentActivity(), LocaleController.getString(R.string.TextCopied), 0).show();
                return;
            L60:
                return;
            L11:
                if ((r8 instanceof URLSpanUserMention) == false) goto L23;
                Long r73 = Utilities.parseLong(((URLSpanUserMention) r8).getURL());
                long r82 = r73.longValue();
                ChatActivityAdapter r1 = this.this$1;
                if (r82 <= 0) goto L18;
                TLRPC.User r12 = MessagesController.getInstance(ChannelAdminLogActivity.access$1600(r1.this$0)).getUser(r73);
                if (r12 == null) goto L65;
                MessagesController.getInstance(ChannelAdminLogActivity.access$1700(this.this$1.this$0)).openChatOrProfileWith(r12, null, this.this$1.this$0, 0, false);
                return;
            L65:
                return;
            L18:
                TLRPC.Chat r2 = MessagesController.getInstance(ChannelAdminLogActivity.access$1800(r1.this$0)).getChat(Long.valueOf(-r82));
                if (r2 == null) goto L66;
                MessagesController.getInstance(ChannelAdminLogActivity.access$1900(this.this$1.this$0)).openChatOrProfileWith(null, r2, this.this$1.this$0, 0, false);
                return;
            L66:
                return;
            L23:
                if ((r8 instanceof URLSpanNoUnderline) == false) goto L32;
                String r74 = ((URLSpanNoUnderline) r8).getURL();
                if (r74.startsWith("@") == false) goto L29;
                MessagesController.getInstance(ChannelAdminLogActivity.access$2000(this.this$1.this$0)).openByUserName(r74.substring(1), this.this$1.this$0, 0);
                return;
            L29:
                if (r74.startsWith("#") == false) goto L67;
                DialogsActivity r83 = new DialogsActivity(null);
                r83.setSearchString(r74);
                this.this$1.this$0.presentFragment(r83);
                return;
            L67:
                return;
            L32:
                String r0 = ((URLSpan) r8).getURL();
                if (r9 == false) goto L37;
                BottomSheet.Builder r75 = new BottomSheet.Builder(this.this$1.this$0.getParentActivity());
                r75.setTitle(r0);
                r75.setItems(new CharSequence[]{LocaleController.getString(R.string.Open), LocaleController.getString(R.string.Copy)}, new ChannelAdminLogActivity$ChatActivityAdapter$1$$ExternalSyntheticLambda0(this, r0));
                this.this$1.this$0.showDialog(r75.create());
                return;
            L37:
                if ((r8 instanceof URLSpanReplacement) == false) goto L40;
                this.this$1.this$0.showOpenUrlAlert(((URLSpanReplacement) r8).getURL(), true);
                return;
            L40:
                TLRPC.MessageMedia r84 = r72.messageOwner.media;
                if ((r84 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L63;
                TLRPC.WebPage r85 = r84.webpage;
                if (r85 == null) goto L63;
                if (r85.cached_page == null) goto L63;
                String r86 = r0.toLowerCase();
                String r92 = r72.messageOwner.media.webpage.url.toLowerCase();
                if (Browser.isTelegraphUrl(r86, false) == true) goto L51;
                if (r86.contains("t.me/iv") == false) goto L63;
            L51:
                if (r86.contains(r92) == false) goto L53;
            L54:
                LaunchActivity r87 = LaunchActivity.instance;
                if (r87 != null) goto L57;
            L61:
                this.this$1.this$0.createArticleViewer(false).open(r72);
                return;
            L57:
                if (r87.getBottomSheetTabs() == null) goto L61;
                if (LaunchActivity.instance.getBottomSheetTabs().tryReopenTab(r72) == null) goto L61;
                return;
            L53:
                if (r92.contains(r86) == true) goto L54;
            L63:
                Browser.openUrl(this.this$1.this$0.getParentActivity(), r0, true);
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressUserAvatar(ChatMessageCell r3, TLRPC.User r4, float r5, float r6, boolean r7) {
                if (r4 != null) goto L4;
                return;
            L4:
                if (r4.id == UserConfig.getInstance(ChannelAdminLogActivity.access$1300(this.this$1.this$0)).getClientUserId()) goto L8;
                openProfile(r4);
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressViaBot(ChatMessageCell r1, String r2) {
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void needOpenWebView(MessageObject r11, String r12, String r13, String r14, String r15, int r16, int r17) {
                ChannelAdminLogActivity r0 = this.this$1.this$0;
                EmbedBottomSheet.show(r0, r11, ChannelAdminLogActivity.m7206$$Nest$fgetprovider(r0), r13, r14, r15, r12, r16, r17, false);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean needPlayMessage(ChatMessageCell r3, MessageObject r4, boolean r5) {
                if (r4.isVoice() == false) goto L5;
            L12:
                boolean r2 = MediaController.getInstance().playMessage(r4, r5);
                MediaController.getInstance().setVoiceMessagesPlaylist(null, false);
                return r2;
            L5:
                if (r4.isRoundVideo() == true) goto L12;
                if (r4.isMusic() == true) goto L10;
                return false;
            L10:
                return MediaController.getInstance().setPlaylist(ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$1.this$0), r4, 0);
            }
        }

        /* renamed from: org.telegram.ui.ChannelAdminLogActivity$ChatActivityAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends ChatActionCell {
            final /* synthetic */ ChatActivityAdapter this$1;

            public AnonymousClass2(ChatActivityAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Cells.ChatActionCell, android.view.View
            public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
                super.onInitializeAccessibilityNodeInfo(r1);
                r1.setVisibleToUser(true);
            }
        }

        /* renamed from: org.telegram.ui.ChannelAdminLogActivity$ChatActivityAdapter$3, reason: invalid class name */
        public class AnonymousClass3 implements ChatActionCell.ChatActionCellDelegate {
            final /* synthetic */ ChatActivityAdapter this$1;

            public static /* synthetic */ void $r8$lambda$KFUidg5wJMEORoeKSNIEoqr9UEE(AnonymousClass3 r0, TLRPC.TL_chatInviteExported r1, TLRPC.TL_messages_exportedChatInvite r2, boolean[] r3, AlertDialog r4) {
                r0.lambda$needOpenInviteLink$1(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$iAWY9uUdcb63Tl7wSWObbtHydwM(AnonymousClass3 r0, TLRPC.TL_chatInviteExported r1, boolean[] r2, AlertDialog r3, TLObject r4, TLRPC.TL_error r5) {
                r0.lambda$needOpenInviteLink$2(r1, r2, r3, r4, r5);
            }

            /* renamed from: $r8$lambda$mAoA0w-n3p64I9j01S9HRaeUV38, reason: not valid java name */
            public static /* synthetic */ void m7250$r8$lambda$mAoA0wn3p64I9j01S9HRaeUV38(AnonymousClass3 r0, boolean[] r1, DialogInterface r2) {
                r0.lambda$needOpenInviteLink$0(r1, r2);
            }

            public AnonymousClass3(ChatActivityAdapter r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$needOpenInviteLink$0(boolean[] r1, DialogInterface r2) {
                ChannelAdminLogActivity.m7219$$Nest$fputlinviteLoading(this.this$1.this$0, false);
                r1[0] = true;
            }

            private /* synthetic */ void lambda$needOpenInviteLink$1(TLRPC.TL_chatInviteExported r4, TLRPC.TL_messages_exportedChatInvite r5, boolean[] r6, AlertDialog r7) {
                ChannelAdminLogActivity.m7219$$Nest$fputlinviteLoading(this.this$1.this$0, false);
                HashMap r0 = ChannelAdminLogActivity.m7200$$Nest$fgetinvitesCache(this.this$1.this$0);
                String r42 = r4.link;
                if (r5 != null) goto L5;
                Object r2 = 0;
            L6:
                r0.put(r42, r2);
                if (r6[0] == false) goto L9;
                return;
            L9:
                r7.dismiss();
                ChatActivityAdapter r3 = this.this$1;
                if (r5 == null) goto L13;
                ChannelAdminLogActivity r32 = r3.this$0;
                ChannelAdminLogActivity.m7241$$Nest$mshowInviteLinkBottomSheet(r32, r5, ChannelAdminLogActivity.m7216$$Nest$fgetusersMap(r32));
                return;
            L13:
                BulletinFactory.of(r3.this$0).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.LinkHashExpired)).show();
                return;
            L5:
                r2 = r5;
                goto L6
            }

            private /* synthetic */ void lambda$needOpenInviteLink$2(TLRPC.TL_chatInviteExported r7, boolean[] r8, AlertDialog r9, TLObject r10, TLRPC.TL_error r11) {
                if (r11 != null) goto L11;
                TLRPC.TL_messages_exportedChatInvite r102 = (TLRPC.TL_messages_exportedChatInvite) r10;
                int r112 = 0;
            L5:
                if (r112 >= r102.users.size()) goto L12;
                TLRPC.User r0 = r102.users.get(r112);
                if (ChannelAdminLogActivity.m7216$$Nest$fgetusersMap(this.this$1.this$0) != null) goto L9;
                ChannelAdminLogActivity.m7228$$Nest$fputusersMap(this.this$1.this$0, new HashMap());
            L9:
                ChannelAdminLogActivity.m7216$$Nest$fgetusersMap(this.this$1.this$0).put(Long.valueOf(r0.id), r0);
                r112 = r112 + 1;
            L12:
                AndroidUtilities.runOnUIThread(new ChannelAdminLogActivity$ChatActivityAdapter$3$$ExternalSyntheticLambda2(this, r7, r102, r8, r9));
                return;
            L11:
                r102 = null;
                goto L12
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public void didClickImage(ChatActionCell r11) {
                MessageObject r1 = r11.getMessageObject();
                if (r1.type != 22) goto L6;
                this.this$1.this$0.presentFragment(new ChannelColorActivity(getDialogId()).setOnApplied(this.this$1.this$0));
                return;
            L6:
                PhotoViewer.getInstance().setParentActivity(this.this$1.this$0);
                TLRPC.PhotoSize r112 = FileLoader.getClosestPhotoSizeWithSize(r1.photoThumbs, 640);
                if (r112 == null) goto L10;
                ImageLocation r0 = ImageLocation.getForPhoto(r112, r1.messageOwner.action.photo);
                PhotoViewer.getInstance().openPhoto(r112.location, r0, ChannelAdminLogActivity.m7206$$Nest$fgetprovider(this.this$1.this$0));
                return;
            L10:
                PhotoViewer.getInstance().openPhoto(r1, null, 0, 0, 0, ChannelAdminLogActivity.m7206$$Nest$fgetprovider(this.this$1.this$0));
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public boolean didLongPress(ChatActionCell r1, float r2, float r3) {
                return ChannelAdminLogActivity.m7233$$Nest$mcreateMenu(this.this$1.this$0, r1);
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public void didPressReplyMessage(ChatActionCell r1, int r2) {
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public BaseFragment getBaseFragment() {
                return this.this$1.this$0;
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public long getDialogId() {
                return -this.this$1.this$0.currentChat.id;
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public void needOpenInviteLink(TLRPC.TL_chatInviteExported r6) {
                if (ChannelAdminLogActivity.m7201$$Nest$fgetlinviteLoading(this.this$1.this$0) == false) goto L6;
                return;
            L6:
                if (ChannelAdminLogActivity.m7200$$Nest$fgetinvitesCache(this.this$1.this$0).containsKey(r6.link) == false) goto L8;
                Object r0 = ChannelAdminLogActivity.m7200$$Nest$fgetinvitesCache(this.this$1.this$0).get(r6.link);
            L9:
                if (r0 != null) goto L12;
                TLRPC.TL_messages_getExportedChatInvite r02 = new TLRPC.TL_messages_getExportedChatInvite();
                r02.peer = this.this$1.this$0.getMessagesController().getInputPeer(-this.this$1.this$0.currentChat.id);
                r02.link = r6.link;
                ChannelAdminLogActivity.m7219$$Nest$fputlinviteLoading(this.this$1.this$0, true);
                boolean[] r1 = new boolean[1];
                AlertDialog r2 = new AlertDialog(this.this$1.this$0.getParentActivity(), 3);
                r2.setOnCancelListener(new ChannelAdminLogActivity$ChatActivityAdapter$3$$ExternalSyntheticLambda0(this, r1));
                r2.showDelayed(300);
                int r62 = this.this$1.this$0.getConnectionsManager().sendRequest(r02, new ChannelAdminLogActivity$ChatActivityAdapter$3$$ExternalSyntheticLambda1(this, r6, r1, r2));
                this.this$1.this$0.getConnectionsManager().bindRequestToGuid(r62, ChannelAdminLogActivity.access$2400(this.this$1.this$0));
                return;
            L12:
                boolean r63 = r0 instanceof TLRPC.TL_messages_exportedChatInvite;
                ChatActivityAdapter r5 = this.this$1;
                if (r63 == false) goto L16;
                ChannelAdminLogActivity r52 = r5.this$0;
                ChannelAdminLogActivity.m7241$$Nest$mshowInviteLinkBottomSheet(r52, (TLRPC.TL_messages_exportedChatInvite) r0, ChannelAdminLogActivity.m7216$$Nest$fgetusersMap(r52));
                return;
            L16:
                BulletinFactory.of(r5.this$0).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.LinkHashExpired)).show();
                return;
            L8:
                r0 = null;
                goto L9
            }

            @Override // org.telegram.ui.Cells.ChatActionCell.ChatActionCellDelegate
            public void needOpenUserProfile(long r3) {
                if (r3 >= 0) goto L9;
                Bundle r0 = new Bundle();
                r0.putLong("chat_id", -r3);
                if (MessagesController.getInstance(ChannelAdminLogActivity.access$2200(this.this$1.this$0)).checkCanOpenChat(r0, this.this$1.this$0) == false) goto L12;
                this.this$1.this$0.presentFragment(new ChatActivity(r0), true);
                return;
            L12:
                return;
            L9:
                if (r3 == UserConfig.getInstance(ChannelAdminLogActivity.access$2300(this.this$1.this$0)).getClientUserId()) goto L13;
                Bundle r02 = new Bundle();
                r02.putLong("user_id", r3);
                ChannelAdminLogActivity.m7229$$Nest$maddCanBanUser(this.this$1.this$0, r02, r3);
                ProfileActivity r32 = new ProfileActivity(r02);
                r32.setPlayProfileAnimation(0);
                this.this$1.this$0.presentFragment(r32);
                return;
            }
        }

        /* renamed from: org.telegram.ui.ChannelAdminLogActivity$ChatActivityAdapter$4, reason: invalid class name */
        public class AnonymousClass4 implements ChatMessageCell.ChatMessageCellDelegate {
            final /* synthetic */ ChatActivityAdapter this$1;

            public AnonymousClass4(ChatActivityAdapter r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressAppUpdateButton() {
                LaunchActivity r2 = LaunchActivity.instance;
                if (r2 == null) goto L6;
                r2.checkAppUpdate(true, null);
                return;
            }
        }

        /* renamed from: org.telegram.ui.ChannelAdminLogActivity$ChatActivityAdapter$5, reason: invalid class name */
        public class AnonymousClass5 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ ChatActivityAdapter this$1;
            final /* synthetic */ RecyclerView.ViewHolder val$holder;
            final /* synthetic */ View val$view;

            public AnonymousClass5(ChatActivityAdapter r1, View r2, RecyclerView.ViewHolder r3) {
                this.this$1 = r1;
                this.val$view = r2;
                this.val$holder = r3;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$view.getViewTreeObserver().removeOnPreDrawListener(this);
                int r0 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$1.this$0).getMeasuredHeight();
                int r1 = this.val$view.getTop();
                this.val$view.getBottom();
                if (r1 < 0) goto L6;
                int r12 = 0;
            L5:
                int r3 = r12;
                int r13 = this.val$view.getMeasuredHeight();
                if (r13 <= r0) goto L10;
                r13 = r3 + r0;
            L10:
                View r02 = this.val$holder.itemView;
                if ((r02 instanceof ChatMessageCell) == false) goto L14;
                ((ChatMessageCell) this.val$view).setVisiblePart(r3, r13 - r3, (ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$1.this$0).getHeightWithKeyboard() - AndroidUtilities.dp(48.0f)) - ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$1.this$0).getTop(), 0.0f, (this.val$view.getY() + ChannelAdminLogActivity.access$3000(this.this$1.this$0).getMeasuredHeight()) - ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundTranslationY(), ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$1.this$0).getMeasuredWidth(), ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundSizeY(), 0, 0, 0);
                return true;
            L14:
                if ((r02 instanceof ChatActionCell) == true) goto L16;
                return true;
            L16:
                if (ChannelAdminLogActivity.access$3100(this.this$1.this$0) != null) goto L18;
                return true;
            L18:
                if (ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$1.this$0) == null) goto L25;
                View r03 = this.val$view;
                ((ChatActionCell) r03).setVisiblePart((r03.getY() + ChannelAdminLogActivity.access$3200(this.this$1.this$0).getMeasuredHeight()) - ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundTranslationY(), ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$1.this$0).getBackgroundSizeY());
                return true;
            L25:
                return true;
            L6:
                r12 = -r1;
                goto L5
            }
        }

        /* renamed from: -$$Nest$fgetmContext, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Context m7246$$Nest$fgetmContext(ChatActivityAdapter r0) {
            return r0.mContext;
        }

        /* renamed from: -$$Nest$fgetmessagesEndRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m7247$$Nest$fgetmessagesEndRow(ChatActivityAdapter r0) {
            return r0.messagesEndRow;
        }

        /* renamed from: -$$Nest$fgetmessagesStartRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m7248$$Nest$fgetmessagesStartRow(ChatActivityAdapter r0) {
            return r0.messagesStartRow;
        }

        public ChatActivityAdapter(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            this.oldStableIds = new ArrayList();
            this.stableIds = new ArrayList();
            this.mContext = r2;
            setHasStableIds(true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r3) {
            if (r3 < this.messagesStartRow) goto L9;
            if (r3 >= this.messagesEndRow) goto L9;
            return ((MessageObject) ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).get((ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - (r3 - this.messagesStartRow)) - 1)).stableId;
        L9:
            if (r3 != this.loadingUpRow) goto L12;
            return 2;
        L12:
            return 5;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 >= this.messagesStartRow) goto L5;
            return 4;
        L5:
            if (r3 < this.messagesEndRow) goto L7;
            return 4;
        L7:
            return ((MessageObject) ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).get((ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - (r3 - this.messagesStartRow)) - 1)).contentType;
        }

        public MessageObject getMessageObject(int r3) {
            if (r3 >= this.messagesStartRow) goto L5;
            return null;
        L5:
            if (r3 < this.messagesEndRow) goto L7;
            return null;
        L7:
            return (MessageObject) ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).get((ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - (r3 - this.messagesStartRow)) - 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            updateRows();
            super.notifyDataSetChanged();     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemChanged(int r2) {
            updateRows(false);
            super.notifyItemChanged(r2);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemInserted(int r2) {
            updateRows(false);
            super.notifyItemInserted(r2);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemMoved(int r2, int r3) {
            updateRows(false);
            super.notifyItemMoved(r2, r3);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeChanged(int r2, int r3) {
            updateRows(false);
            super.notifyItemRangeChanged(r2, r3);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeInserted(int r2, int r3) {
            updateRows(false);
            super.notifyItemRangeInserted(r2, r3);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeRemoved(int r2, int r3) {
            updateRows(false);
            super.notifyItemRangeRemoved(r2, r3);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRemoved(int r2) {
            updateRows(false);
            super.notifyItemRemoved(r2);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r14, int r15) {
            if (r15 != this.loadingUpRow) goto L7;
            ((ChatLoadingCell) r14.itemView).setProgressVisible(true);
            return;
        L7:
            if (r15 >= this.messagesStartRow) goto L9;
            return;
        L9:
            if (r15 >= this.messagesEndRow) goto L78;
            MessageObject r3 = (MessageObject) ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).get((ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - (r15 - this.messagesStartRow)) - 1);
            View r0 = r14.itemView;
            if ((r0 instanceof ChatMessageCell) == false) goto L74;
            ChatMessageCell r2 = (ChatMessageCell) r0;
            r2.isChat = true;
            int r02 = r15 + 1;
            int r4 = getItemViewType(r02);
            int r5 = getItemViewType(r15 - 1);
            if ((r3.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) == false) goto L15;
        L39:
            boolean r42 = false;
        L41:
            if (r5 != r14.getItemViewType()) goto L70;
            MessageObject r142 = (MessageObject) ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).get(ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - (r15 - this.messagesStartRow));
            if ((r142.messageOwner.reply_markup instanceof TLRPC.TL_replyInlineMarkup) == false) goto L45;
        L51:
            boolean r152 = false;
        L52:
            if (r152 == false) goto L68;
            TLRPC.TL_forumTopic r03 = r3.replyToForumTopic;
            if (r03 != null) goto L56;
            long r52 = MessageObject.getTopicId(ChannelAdminLogActivity.access$2800(this.this$0), r3.messageOwner, true);
        L57:
            TLRPC.TL_forumTopic r04 = r142.replyToForumTopic;
            if (r04 != null) goto L60;
            long r05 = MessageObject.getTopicId(ChannelAdminLogActivity.access$2900(this.this$0), r142.messageOwner, true);
        L62:
            if (r52 == r05) goto L64;
            r152 = false;
        L64:
            if (r152 == false) goto L68;
            if (WidePosts.shouldEmbedAuthorProfileAvatar(r142) == WidePosts.shouldEmbedAuthorProfileAvatar(r3)) goto L68;
            r152 = false;
            goto L68
        L60:
            r05 = r04.id;
            goto L62
        L56:
            r52 = r03.id;
        L68:
            boolean r6 = r152;
        L71:
            r2.setMessageObject(r3, null, r42, r6, false);
            r2.setHighlighted(false);
            r2.setHighlightedText(ChannelAdminLogActivity.m7213$$Nest$fgetsearchQuery(this.this$0));
            return;
        L45:
            if (r142.isOutOwner() != r3.isOutOwner()) goto L51;
            if (r142.getFromChatId() != r3.getFromChatId()) goto L51;
            if (Math.abs(r142.messageOwner.date - r3.messageOwner.date) > 300) goto L51;
            r152 = true;
            goto L52
        L70:
            r6 = false;
            goto L71
        L15:
            if (r4 != r14.getItemViewType()) goto L39;
            MessageObject r06 = (MessageObject) ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).get((ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size() - (r02 - this.messagesStartRow)) - 1);
            if (r06.isOutOwner() == r3.isOutOwner()) goto L19;
        L23:
            r42 = false;
        L24:
            if (r42 == false) goto L41;
            TLRPC.TL_forumTopic r62 = r3.replyToForumTopic;
            if (r62 != null) goto L28;
            long r9 = MessageObject.getTopicId(ChannelAdminLogActivity.access$2600(this.this$0), r3.messageOwner, true);
        L29:
            TLRPC.TL_forumTopic r63 = r06.replyToForumTopic;
            if (r63 != null) goto L32;
            long r11 = MessageObject.getTopicId(ChannelAdminLogActivity.access$2700(this.this$0), r06.messageOwner, true);
        L34:
            if (r9 == r11) goto L36;
            r42 = false;
        L36:
            if (r42 == false) goto L41;
            if (WidePosts.shouldEmbedAuthorProfileAvatar(r06) == WidePosts.shouldEmbedAuthorProfileAvatar(r3)) goto L41;
        L32:
            r11 = r63.id;
            goto L34
        L28:
            r9 = r62.id;
            goto L29
        L19:
            if (r06.getFromChatId() != r3.getFromChatId()) goto L23;
            if (Math.abs(r06.messageOwner.date - r3.messageOwner.date) > 300) goto L23;
            r42 = true;
            goto L24
        L74:
            if ((r0 instanceof ChatActionCell) == false) goto L79;
            ChatActionCell r07 = (ChatActionCell) r0;
            r07.setMessageObject(r3);
            r07.setAlpha(1.0f);
            return;
        L79:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v14, types: [android.view.View] */
        /* JADX WARN: Type inference failed for: r4v2, types: [org.telegram.ui.Cells.ChatMessageUnsupportedCell] */
        /* JADX WARN: Type inference failed for: r4v4, types: [org.telegram.ui.Cells.ChatActionCell, org.telegram.ui.ChannelAdminLogActivity$ChatActivityAdapter$2] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 == 0) goto L5;
            if (r4 != 1) goto L12;
            ?? r42 = new AnonymousClass2(this, this.mContext);
            r42.setDelegate(new AnonymousClass3(this));
            ViewGroup r43 = r42;
        L18:
            r43.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r43);
        L12:
            if (r4 != 2) goto L15;
            r43 = new ChatUnreadCell(this.mContext, null);
            goto L18
        L15:
            if (r4 != 10) goto L17;
            ?? r44 = new ChatMessageUnsupportedCell(this.mContext, ChannelAdminLogActivity.access$2500(this.this$0));
            r44.setDelegate(new AnonymousClass4(this));
            r43 = r44;
            goto L18
        L17:
            r43 = new ChatLoadingCell(this.mContext, ChannelAdminLogActivity.m7192$$Nest$fgetcontentView(this.this$0), null);
            goto L18
        L5:
            if (ChannelAdminLogActivity.m7191$$Nest$fgetchatMessageCellsCache(this.this$0).isEmpty() == true) goto L7;
            ?? r45 = (View) ChannelAdminLogActivity.m7191$$Nest$fgetchatMessageCellsCache(this.this$0).get(0);
            ChannelAdminLogActivity.m7191$$Nest$fgetchatMessageCellsCache(this.this$0).remove(0);
            ViewGroup r46 = r45;
        L8:
            ChatMessageCell r0 = (ChatMessageCell) r46;
            r0.setDelegate(new AnonymousClass1(this));
            r0.setAllowAssistant(true);
            r43 = r46;
            goto L18
        L7:
            r46 = new ChatMessageCell(this.mContext, ChannelAdminLogActivity.access$1000(this.this$0));
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
            View r0 = r4.itemView;
            if ((r0 instanceof ChatMessageCell) == false) goto L5;
        L6:
            r0.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass5(this, r0, r4));
        L7:
            View r3 = r4.itemView;
            if ((r3 instanceof ChatMessageCell) == false) goto L11;
            ChatMessageCell r32 = (ChatMessageCell) r3;
            r32.getMessageObject();
            r32.setBackgroundDrawable(null);
            r32.setCheckPressed(true, false);
            r32.setHighlighted(false);
            return;
        L11:
            return;
        L5:
            if ((r0 instanceof ChatActionCell) == false) goto L7;
            goto L6
        }

        public void updateRows() {
            updateRows(true);
        }

        public void updateRows(boolean r2) {
            this.rowCount = 0;
            if (ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).isEmpty() == false) goto L5;
            this.loadingUpRow = -1;
            this.messagesStartRow = -1;
            this.messagesEndRow = -1;
            return;
        L5:
            if (ChannelAdminLogActivity.m7195$$Nest$fgetendReached(this.this$0) == true) goto L7;
            int r22 = this.rowCount;
            this.rowCount = r22 + 1;
            this.loadingUpRow = r22;
        L8:
            int r23 = this.rowCount;
            this.messagesStartRow = r23;
            int r24 = r23 + ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).size();
            this.rowCount = r24;
            this.messagesEndRow = r24;
            return;
        L7:
            this.loadingUpRow = -1;
            goto L8
        }
    }

    public class ChatActivityFragmentView extends SizeNotifierFrameLayout {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public ChatActivityFragmentView(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        public void drawList(Canvas r8, RectF r9) {
            long r0 = SystemClock.uptimeMillis();
            if (ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).hasActiveEdgeEffects() == false) goto L6;
            r8.save();
            r8.clipRect(r9);
            drawChild(r8, ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0), r0);
            r8.restore();
            return;
        L6:
            r8.save();
            r8.clipRect(r9);
            r8.translate(0.0f, ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getY());
            ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).drawChatBackgroundElements(r8, r9);
            int r2 = 0;
        L7:
            int r4 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).getChildCount();
            ChannelAdminLogActivity r5 = this.this$0;
            if (r2 >= r4) goto L25;
            View r42 = ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(r5).getChildAt(r2);
            if (ChannelAdminLogActivity.m7240$$Nest$mquickRejectChild(this.this$0, r42, r9) == true) goto L24;
            if ((r42 instanceof ChatMessageCell) == false) goto L20;
            r8.save();
            r8.translate(r42.getX(), r42.getY());
            ChatMessageCell r52 = (ChatMessageCell) r42;
            if (r52.drawBackgroundInParent() == false) goto L17;
            r8.save();
            r8.translate(0.0f, r52.starsPriceTopPadding);
            r52.drawBackgroundInternal(r8, true);
            r8.restore();
        L17:
            r8.restore();
            ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(this.this$0).drawChild(r8, r42, r0);
            if (r52.hasOutboundsContent() == false) goto L24;
            r8.save();
            r8.translate(r52.getX(), r52.getY());
            r52.drawOutboundsContent(r8);
            r8.restore();
            goto L24
        L20:
            boolean r53 = r42 instanceof ChatActionCell;
            ChannelAdminLogActivity r6 = this.this$0;
            if (r53 == false) goto L23;
            ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(r6).drawChild(r8, r42, r0);
            r8.save();
            r8.translate(r42.getX(), r42.getY());
            ((ChatActionCell) r42).drawOutboundsContent(r8);
            r8.restore();
            goto L24
        L23:
            ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(r6).drawChild(r8, r42, r0);
        L24:
            r2 = r2 + 1;
            goto L7
        L25:
            ChannelAdminLogActivity.m7190$$Nest$fgetchatListView(r5).drawChatForegroundElements(r8, r9);
            r8.restore();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }
    }

    public abstract class ChatListRecyclerView extends RecyclerListView {
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public ChatListRecyclerView(ChannelAdminLogActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        public void drawChatBackgroundElements(Canvas r1, RectF r2) {
        }

        public void drawChatForegroundElements(Canvas r1, RectF r2) {
        }
    }

    public class ChatScrollCallback extends RecyclerAnimationScrollHelper.AnimationCallback {
        private boolean bottom;
        private boolean lastBottom;
        private int lastItemOffset;
        private int lastPadding;
        private int offset;
        private int position;
        private MessageObject scrollTo;
        final /* synthetic */ ChannelAdminLogActivity this$0;

        public static /* synthetic */ void $r8$lambda$ntd3D5M6c6TSucXagEufNe1lsgg(ChatScrollCallback r0) {
            r0.lambda$onEndAnimation$0();
        }

        /* renamed from: -$$Nest$fputbottom, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7251$$Nest$fputbottom(ChatScrollCallback r0, boolean r1) {
            r0.bottom = r1;
        }

        /* renamed from: -$$Nest$fputlastBottom, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7252$$Nest$fputlastBottom(ChatScrollCallback r0, boolean r1) {
            r0.lastBottom = r1;
        }

        /* renamed from: -$$Nest$fputlastItemOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7253$$Nest$fputlastItemOffset(ChatScrollCallback r0, int r1) {
            r0.lastItemOffset = r1;
        }

        /* renamed from: -$$Nest$fputoffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7254$$Nest$fputoffset(ChatScrollCallback r0, int r1) {
            r0.offset = r1;
        }

        /* renamed from: -$$Nest$fputposition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7255$$Nest$fputposition(ChatScrollCallback r0, int r1) {
            r0.position = r1;
        }

        /* renamed from: -$$Nest$fputscrollTo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7256$$Nest$fputscrollTo(ChatScrollCallback r0, MessageObject r1) {
            r0.scrollTo = r1;
        }

        public ChatScrollCallback(ChannelAdminLogActivity r2) {
            this.this$0 = r2;
            this.position = 0;
            this.bottom = true;
            this.offset = 0;
        }

        private /* synthetic */ void lambda$onEndAnimation$0() {
            this.this$0.getNotificationCenter().onAnimationFinish(ChannelAdminLogActivity.m7210$$Nest$fgetscrollCallbackAnimationIndex(this.this$0));
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void onEndAnimation() {
            MessageObject r0 = this.scrollTo;
            ChannelAdminLogActivity r1 = this.this$0;
            if (r0 == null) goto L7;
            int r02 = ChatActivityAdapter.m7248$$Nest$fgetmessagesStartRow(ChannelAdminLogActivity.m7187$$Nest$fgetchatAdapter(r1)) + ChannelAdminLogActivity.m7197$$Nest$fgetfilteredMessages(this.this$0).indexOf(this.scrollTo);
            if (r02 < 0) goto L8;
            ChannelAdminLogActivity.m7188$$Nest$fgetchatLayoutManager(this.this$0).scrollToPositionWithOffset(r02, this.lastItemOffset + this.lastPadding, this.lastBottom);
        L8:
            this.scrollTo = null;
            ChannelAdminLogActivity.m7217$$Nest$fputcheckTextureViewPosition(this.this$0, true);
            ChannelAdminLogActivity.m7244$$Nest$mupdateVisibleRows(this.this$0);
            AndroidUtilities.runOnUIThread(new ChannelAdminLogActivity$ChatScrollCallback$$ExternalSyntheticLambda0(this));
            return;
        L7:
            ChannelAdminLogActivity.m7188$$Nest$fgetchatLayoutManager(r1).scrollToPositionWithOffset(this.position, this.offset, this.bottom);
            goto L8
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void onStartAnimation() {
            super.onStartAnimation();
            ChannelAdminLogActivity r0 = this.this$0;
            ChannelAdminLogActivity.m7222$$Nest$fputscrollCallbackAnimationIndex(r0, r0.getNotificationCenter().setAnimationInProgress(ChannelAdminLogActivity.m7210$$Nest$fgetscrollCallbackAnimationIndex(this.this$0), ChannelAdminLogActivity.m7245$$Nest$sfgetallowedNotificationsDuringChatListAnimations()));
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void recycleView(View r2) {
            if ((r2 instanceof ChatMessageCell) == false) goto L6;
            ChannelAdminLogActivity.m7191$$Nest$fgetchatMessageCellsCache(this.this$0).add((ChatMessageCell) r2);
            return;
        }
    }

    /* renamed from: $r8$lambda$-gLri6UVl3_D41WyG3snW5PS9tc, reason: not valid java name */
    public static /* synthetic */ void m7173$r8$lambda$gLri6UVl3_D41WyG3snW5PS9tc(ChannelAdminLogActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAntispamUser$24(r1, r2);
    }

    /* renamed from: $r8$lambda$4n0i8htdT-uUIk812KH5gDBqqn0, reason: not valid java name */
    public static /* synthetic */ void m7174$r8$lambda$4n0i8htdTuUIk812KH5gDBqqn0(ChannelAdminLogActivity r0, TLObject r1) {
        r0.lambda$processSelectedOption$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$5XTsZ1UbGihszAZ1yyOpwDvpGyU(ChannelAdminLogActivity r0, TLRPC.TL_channels_adminLogResults r1) {
        r0.lambda$loadMessages$4(r1);
    }

    /* renamed from: $r8$lambda$6wtz-IkoR8C8voBm_tBQcdHhci4, reason: not valid java name */
    public static /* synthetic */ void m7175$r8$lambda$6wtzIkoR8C8voBm_tBQcdHhci4(ChannelAdminLogActivity r0, View r1) {
        r0.lambda$createView$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$AYL_V5dZQIQG_29QuEGR4cpqL8Y(ChannelAdminLogActivity r0, ArrayList r1, ArrayList r2, ArrayList r3, Runnable r4, TLRPC.ChannelParticipant r5) {
        r0.lambda$createMenu$17(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$Bl_RVb-FyAGsfdNUI293ArJCJZY, reason: not valid java name */
    public static /* synthetic */ void m7176$r8$lambda$Bl_RVbFyAGsfdNUI293ArJCJZY(ChannelAdminLogActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$processSelectedOption$19(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$BxVQEFChUpqo8sSjxom9nBxUnS8(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$CeMY3u89ST4c2ucbrBzzpjDNjMM(ChannelAdminLogActivity r0, String r1, AlertDialog r2, int r3) {
        r0.lambda$showOpenUrlAlert$25(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$EN3fJ6-Tub144-tejhTksBeM5bo, reason: not valid java name */
    public static /* synthetic */ void m7177$r8$lambda$EN3fJ6Tub144tejhTksBeM5bo(ChannelAdminLogActivity r0) {
        r0.lambda$loadMessages$2();
    }

    public static /* synthetic */ String $r8$lambda$END5mD_23eJ_Jjrpu9m5B6JaBHY(ChannelAdminLogActivity r0, Long r1) {
        return r0.lambda$actionMessagesDeletedBy$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ekj5VKhZd_W8wsboyGk0ECh0CDk(ChannelAdminLogActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdmins$23(r1, r2);
    }

    /* renamed from: $r8$lambda$ITM5EFOQh90TSfC46_v-F_vTejs, reason: not valid java name */
    public static /* synthetic */ void m7178$r8$lambda$ITM5EFOQh90TSfC46_vF_vTejs(ChannelAdminLogActivity r0, View r1) {
        r0.lambda$createView$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$O6ln_e3_ChQqbuRVWGd78pGhf5Y(ChannelAdminLogActivity r0, TLRPC.TL_channels_adminLogResults r1) {
        r0.lambda$reloadLastMessages$0(r1);
    }

    /* renamed from: $r8$lambda$Q5JM-BSQmnvnFldcwL8Lv0QazzA, reason: not valid java name */
    public static /* synthetic */ void m7179$r8$lambda$Q5JMBSQmnvnFldcwL8Lv0QazzA(ChannelAdminLogActivity r0) {
        r0.lambda$processSelectedOption$21();
    }

    public static /* synthetic */ void $r8$lambda$S2ifHci8AuuXYAXBpJqV858vJK0(ChannelAdminLogActivity r0, ArrayList r1, ArrayList r2, ArrayList r3, View r4, float r5, float r6) {
        r0.lambda$createMenu$15(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$SqmNJZejryf92l2qHUSMvSH-NE4, reason: not valid java name */
    public static /* synthetic */ void m7180$r8$lambda$SqmNJZejryf92l2qHUSMvSHNE4(ChannelAdminLogActivity r0) {
        r0.updateMessagesVisiblePart();
    }

    public static /* synthetic */ boolean $r8$lambda$X0j8pefB9yax9lZM_iAz9eorgJo(String r0) {
        if (r0 == null) goto L5;
        return true;
    L5:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$XZq7H0Xrxqkl39YO7m0pZDqm6NI(ChannelAdminLogActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$loadAdmins$22(r1, r2);
    }

    /* renamed from: $r8$lambda$YcDYNHbk1Fv7-O-BE8LYMNc-5XQ, reason: not valid java name */
    public static /* synthetic */ void m7181$r8$lambda$YcDYNHbk1Fv7OBE8LYMNc5XQ(ChannelAdminLogActivity r0, View r1) {
        r0.lambda$createView$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZVMrFmAS9Ctd5Ut3AiiIjJkbNw4(ChannelAdminLogActivity r0) {
        r0.lambda$startMessageUnselect$26();
    }

    /* renamed from: $r8$lambda$cmMmy9wvmE15KlWwWX-gnxOTs6g, reason: not valid java name */
    public static /* synthetic */ void m7182$r8$lambda$cmMmy9wvmE15KlWwWXgnxOTs6g(ChannelAdminLogActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$reloadLastMessages$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dWL3USl5xG7GLWpyeS4As5pdOJU(ChannelAdminLogActivity r0, TLRPC.User r1) {
        r0.lambda$processSelectedOption$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$hH5bdmkCsiKkGPeu7KPIJmDasPY(ChannelAdminLogActivity r0, TLRPC.ChannelParticipant r1, ArrayList r2, ArrayList r3, ArrayList r4, Runnable r5) {
        r0.lambda$createMenu$16(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$hJpF4V6wHw2EwCPWOEoSvpLRcS0(ChannelAdminLogActivity r0, int r1) {
        r0.lambda$createView$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$iM6_bTDlrqyFJlot9SHhKEQodm0(ChannelAdminLogActivity r0, int r1) {
        r0.invalidateMergedVisibleBlurredPositionsAndSourcesImpl(r1);
    }

    public static /* synthetic */ void $r8$lambda$kcFjdU_TGIEfVtEyatfADQXWL3Q(ChannelAdminLogActivity r0, int r1, ArrayList r2, Integer r3, View r4) {
        r0.lambda$createMenu$14(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$q28jY-RD7osOF8J6OV96e_sqj20, reason: not valid java name */
    public static /* synthetic */ void m7183$r8$lambda$q28jYRD7osOF8J6OV96e_sqj20(ChannelAdminLogActivity r0, TLRPC.TL_channelAdminLogEventsFilter r1, LongSparseArray r2) {
        r0.lambda$createView$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$szESFAccohZn_mjyn7yWo6g9Ghw(ChannelAdminLogActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadMessages$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$u9r_BuO4F69lG4jYPogHFBjEPCU(ChannelAdminLogActivity r0) {
        r0.lambda$loadMessages$3();
    }

    /* renamed from: -$$Nest$fgetaspectRatioFrameLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AspectRatioFrameLayout m7184$$Nest$fgetaspectRatioFrameLayout(ChannelAdminLogActivity r0) {
        return r0.aspectRatioFrameLayout;
    }

    /* renamed from: -$$Nest$fgetavatarContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatAvatarContainer m7185$$Nest$fgetavatarContainer(ChannelAdminLogActivity r0) {
        return r0.avatarContainer;
    }

    /* renamed from: -$$Nest$fgetchatActivityFadeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityFadeView m7186$$Nest$fgetchatActivityFadeView(ChannelAdminLogActivity r0) {
        return r0.chatActivityFadeView;
    }

    /* renamed from: -$$Nest$fgetchatAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityAdapter m7187$$Nest$fgetchatAdapter(ChannelAdminLogActivity r0) {
        return r0.chatAdapter;
    }

    /* renamed from: -$$Nest$fgetchatLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m7188$$Nest$fgetchatLayoutManager(ChannelAdminLogActivity r0) {
        return r0.chatLayoutManager;
    }

    /* renamed from: -$$Nest$fgetchatListItemAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatListItemAnimator m7189$$Nest$fgetchatListItemAnimator(ChannelAdminLogActivity r0) {
        return r0.chatListItemAnimator;
    }

    /* renamed from: -$$Nest$fgetchatListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatListRecyclerView m7190$$Nest$fgetchatListView(ChannelAdminLogActivity r0) {
        return r0.chatListView;
    }

    /* renamed from: -$$Nest$fgetchatMessageCellsCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m7191$$Nest$fgetchatMessageCellsCache(ChannelAdminLogActivity r0) {
        return r0.chatMessageCellsCache;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityFragmentView m7192$$Nest$fgetcontentView(ChannelAdminLogActivity r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetcurrentFloatingTopIsNotMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7193$$Nest$fgetcurrentFloatingTopIsNotMessage(ChannelAdminLogActivity r0) {
        return r0.currentFloatingTopIsNotMessage;
    }

    /* renamed from: -$$Nest$fgetemptyViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m7194$$Nest$fgetemptyViewContainer(ChannelAdminLogActivity r0) {
        return r0.emptyViewContainer;
    }

    /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7195$$Nest$fgetendReached(ChannelAdminLogActivity r0) {
        return r0.endReached;
    }

    /* renamed from: -$$Nest$fgetexpandedEvents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashSet m7196$$Nest$fgetexpandedEvents(ChannelAdminLogActivity r0) {
        return r0.expandedEvents;
    }

    /* renamed from: -$$Nest$fgetfilteredMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m7197$$Nest$fgetfilteredMessages(ChannelAdminLogActivity r0) {
        return r0.filteredMessages;
    }

    /* renamed from: -$$Nest$fgetfloatingDateAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m7198$$Nest$fgetfloatingDateAnimation(ChannelAdminLogActivity r0) {
        return r0.floatingDateAnimation;
    }

    /* renamed from: -$$Nest$fgetfloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActionCell m7199$$Nest$fgetfloatingDateView(ChannelAdminLogActivity r0) {
        return r0.floatingDateView;
    }

    /* renamed from: -$$Nest$fgetinvitesCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m7200$$Nest$fgetinvitesCache(ChannelAdminLogActivity r0) {
        return r0.invitesCache;
    }

    /* renamed from: -$$Nest$fgetlinviteLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7201$$Nest$fgetlinviteLoading(ChannelAdminLogActivity r0) {
        return r0.linviteLoading;
    }

    /* renamed from: -$$Nest$fgetmessagesByDays, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m7202$$Nest$fgetmessagesByDays(ChannelAdminLogActivity r0) {
        return r0.messagesByDays;
    }

    /* renamed from: -$$Nest$fgetmid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m7203$$Nest$fgetmid(ChannelAdminLogActivity r0) {
        return r0.mid;
    }

    /* renamed from: -$$Nest$fgetnavbarContentSourceWallpaper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceWrapped m7204$$Nest$fgetnavbarContentSourceWallpaper(ChannelAdminLogActivity r0) {
        return r0.navbarContentSourceWallpaper;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m7205$$Nest$fgetprogressView(ChannelAdminLogActivity r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetprovider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoViewer.PhotoViewerProvider m7206$$Nest$fgetprovider(ChannelAdminLogActivity r0) {
        return r0.provider;
    }

    /* renamed from: -$$Nest$fgetrecommendedAdditionalSizeY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7207$$Nest$fgetrecommendedAdditionalSizeY(ChannelAdminLogActivity r0) {
        return r0.recommendedAdditionalSizeY;
    }

    /* renamed from: -$$Nest$fgetroundVideoContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m7208$$Nest$fgetroundVideoContainer(ChannelAdminLogActivity r0) {
        return r0.roundVideoContainer;
    }

    /* renamed from: -$$Nest$fgetscrimPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m7209$$Nest$fgetscrimPopupWindow(ChannelAdminLogActivity r0) {
        return r0.scrimPopupWindow;
    }

    /* renamed from: -$$Nest$fgetscrollCallbackAnimationIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7210$$Nest$fgetscrollCallbackAnimationIndex(ChannelAdminLogActivity r0) {
        return r0.scrollCallbackAnimationIndex;
    }

    /* renamed from: -$$Nest$fgetscrollToPositionOnRecreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7211$$Nest$fgetscrollToPositionOnRecreate(ChannelAdminLogActivity r0) {
        return r0.scrollToPositionOnRecreate;
    }

    /* renamed from: -$$Nest$fgetscrollingFloatingDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7212$$Nest$fgetscrollingFloatingDate(ChannelAdminLogActivity r0) {
        return r0.scrollingFloatingDate;
    }

    /* renamed from: -$$Nest$fgetsearchQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m7213$$Nest$fgetsearchQuery(ChannelAdminLogActivity r0) {
        return r0.searchQuery;
    }

    /* renamed from: -$$Nest$fgetsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7214$$Nest$fgetsearchWas(ChannelAdminLogActivity r0) {
        return r0.searchWas;
    }

    /* renamed from: -$$Nest$fgetundoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoView m7215$$Nest$fgetundoView(ChannelAdminLogActivity r0) {
        return r0.undoView;
    }

    /* renamed from: -$$Nest$fgetusersMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m7216$$Nest$fgetusersMap(ChannelAdminLogActivity r0) {
        return r0.usersMap;
    }

    /* renamed from: -$$Nest$fputcheckTextureViewPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7217$$Nest$fputcheckTextureViewPosition(ChannelAdminLogActivity r0, boolean r1) {
        r0.checkTextureViewPosition = r1;
    }

    /* renamed from: -$$Nest$fputfloatingDateAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7218$$Nest$fputfloatingDateAnimation(ChannelAdminLogActivity r0, AnimatorSet r1) {
        r0.floatingDateAnimation = r1;
    }

    /* renamed from: -$$Nest$fputlinviteLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7219$$Nest$fputlinviteLoading(ChannelAdminLogActivity r0, boolean r1) {
        r0.linviteLoading = r1;
    }

    /* renamed from: -$$Nest$fputscrimPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7220$$Nest$fputscrimPopupWindow(ChannelAdminLogActivity r0, ActionBarPopupWindow r1) {
        r0.scrimPopupWindow = r1;
    }

    /* renamed from: -$$Nest$fputscrollByTouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7221$$Nest$fputscrollByTouch(ChannelAdminLogActivity r0, boolean r1) {
        r0.scrollByTouch = r1;
    }

    /* renamed from: -$$Nest$fputscrollCallbackAnimationIndex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7222$$Nest$fputscrollCallbackAnimationIndex(ChannelAdminLogActivity r0, int r1) {
        r0.scrollCallbackAnimationIndex = r1;
    }

    /* renamed from: -$$Nest$fputscrollToOffsetOnRecreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7223$$Nest$fputscrollToOffsetOnRecreate(ChannelAdminLogActivity r0, int r1) {
        r0.scrollToOffsetOnRecreate = r1;
    }

    /* renamed from: -$$Nest$fputscrollToPositionOnRecreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7224$$Nest$fputscrollToPositionOnRecreate(ChannelAdminLogActivity r0, int r1) {
        r0.scrollToPositionOnRecreate = r1;
    }

    /* renamed from: -$$Nest$fputscrollingFloatingDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7225$$Nest$fputscrollingFloatingDate(ChannelAdminLogActivity r0, boolean r1) {
        r0.scrollingFloatingDate = r1;
    }

    /* renamed from: -$$Nest$fputsearchQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7226$$Nest$fputsearchQuery(ChannelAdminLogActivity r0, String r1) {
        r0.searchQuery = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7227$$Nest$fputsearchWas(ChannelAdminLogActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputusersMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7228$$Nest$fputusersMap(ChannelAdminLogActivity r0, HashMap r1) {
        r0.usersMap = r1;
    }

    /* renamed from: -$$Nest$maddCanBanUser, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7229$$Nest$maddCanBanUser(ChannelAdminLogActivity r0, Bundle r1, long r2) {
        r0.addCanBanUser(r1, r2);
    }

    /* renamed from: -$$Nest$malertUserOpenError, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7230$$Nest$malertUserOpenError(ChannelAdminLogActivity r0, MessageObject r1) {
        r0.alertUserOpenError(r1);
    }

    /* renamed from: -$$Nest$mcheckScrollForLoad, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7231$$Nest$mcheckScrollForLoad(ChannelAdminLogActivity r0, boolean r1) {
        r0.checkScrollForLoad(r1);
    }

    /* renamed from: -$$Nest$mcloseMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7232$$Nest$mcloseMenu(ChannelAdminLogActivity r0) {
        r0.closeMenu();
    }

    /* renamed from: -$$Nest$mcreateMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7233$$Nest$mcreateMenu(ChannelAdminLogActivity r0, View r1) {
        return r0.createMenu(r1);
    }

    /* renamed from: -$$Nest$mcreateMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7234$$Nest$mcreateMenu(ChannelAdminLogActivity r0, View r1, float r2, float r3) {
        return r0.createMenu(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mcreateTextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m7235$$Nest$mcreateTextureView(ChannelAdminLogActivity r0, boolean r1) {
        return r0.createTextureView(r1);
    }

    /* renamed from: -$$Nest$mfilterDeletedMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7236$$Nest$mfilterDeletedMessages(ChannelAdminLogActivity r0) {
        r0.filterDeletedMessages();
    }

    /* renamed from: -$$Nest$mhideFloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7237$$Nest$mhideFloatingDateView(ChannelAdminLogActivity r0, boolean r1) {
        r0.hideFloatingDateView(r1);
    }

    /* renamed from: -$$Nest$mloadMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7238$$Nest$mloadMessages(ChannelAdminLogActivity r0, boolean r1) {
        r0.loadMessages(r1);
    }

    /* renamed from: -$$Nest$mmoveScrollToLastMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7239$$Nest$mmoveScrollToLastMessage(ChannelAdminLogActivity r0) {
        r0.moveScrollToLastMessage();
    }

    /* renamed from: -$$Nest$mquickRejectChild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7240$$Nest$mquickRejectChild(ChannelAdminLogActivity r0, View r1, RectF r2) {
        return r0.quickRejectChild(r1, r2);
    }

    /* renamed from: -$$Nest$mshowInviteLinkBottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7241$$Nest$mshowInviteLinkBottomSheet(ChannelAdminLogActivity r0, TLRPC.TL_messages_exportedChatInvite r1, HashMap r2) {
        r0.showInviteLinkBottomSheet(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateBottomOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7242$$Nest$mupdateBottomOverlay(ChannelAdminLogActivity r0) {
        r0.updateBottomOverlay();
    }

    /* renamed from: -$$Nest$mupdateMessagesVisiblePart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7243$$Nest$mupdateMessagesVisiblePart(ChannelAdminLogActivity r0) {
        r0.updateMessagesVisiblePart();
    }

    /* renamed from: -$$Nest$mupdateVisibleRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7244$$Nest$mupdateVisibleRows(ChannelAdminLogActivity r0) {
        r0.updateVisibleRows();
    }

    /* renamed from: -$$Nest$sfgetallowedNotificationsDuringChatListAnimations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m7245$$Nest$sfgetallowedNotificationsDuringChatListAnimations() {
        return allowedNotificationsDuringChatListAnimations;
    }

    static {
        allowedNotificationsDuringChatListAnimations = new int[]{NotificationCenter.chatInfoDidLoad, NotificationCenter.dialogsNeedReload, NotificationCenter.closeChats, NotificationCenter.messagesDidLoad, NotificationCenter.botKeyboardDidLoad};
    }

    public ChannelAdminLogActivity(TLRPC.Chat r10) {
        this.chatMessageCellsCache = new ArrayList();
        this.mid = new int[]{2};
        this.scrollToPositionOnRecreate = -1;
        this.scrollToOffsetOnRecreate = 0;
        this.paused = true;
        this.wasPaused = false;
        this.messagesDict = new LongSparseArray();
        this.realMessagesDict = new LongSparseArray();
        this.messagesByDays = new HashMap();
        this.messages = new ArrayList();
        this.filteredMessages = new ArrayList();
        this.expandedEvents = new HashSet();
        this.currentFilter = null;
        this.searchQuery = _UrlKt.FRAGMENT_ENCODE_SET;
        this.notificationsLocker = new AnimationNotificationsLocker(allowedNotificationsDuringChatListAnimations);
        this.invitesCache = new HashMap();
        this.provider = new AnonymousClass1(this);
        this.filteredMessagesUpdatedPosition = new ArrayList();
        this.stableIdByEventExpand = new LongSparseArray();
        this.highlightMessageId = Integer.MAX_VALUE;
        this.highlightMessageQuoteOffset = -1;
        this.scrollToMessagePosition = XCallback.PRIORITY_LOWEST;
        this.chatScrollHelperCallback = new ChatScrollCallback(this);
        this.savedScrollPosition = -1;
        this.tmpViewRectF = new RectF();
        this.glassEngine.setGlassInvalidationListener(new ChannelAdminLogActivity$$ExternalSyntheticLambda6(this));
        this.glassEngine.setPositionsMerger(new GlassPositionsMerger(Float.POSITIVE_INFINITY, AndroidUtilities.dp(48.0f)));
        BlurredBackgroundSourceWrapped r0 = new BlurredBackgroundSourceWrapped();
        this.navbarContentSourceWallpaper = r0;
        BlurredBackgroundDrawableViewFactory r3 = new BlurredBackgroundDrawableViewFactory(r0);
        this.navbarContentDrawableFactory = r3;
        r3.setGlassEngine(this.glassEngine);
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L18:
        this.scrollableViewNoiseSuppressor = null;
        this.recommendedAdditionalSizeY = 0;
        this.glassBackgroundSourceRenderNode = null;
        this.glassBackgroundSourceFrostedRenderNode = null;
        BlurredBackgroundDrawableViewFactory r1 = new BlurredBackgroundDrawableViewFactory(r0);
        this.glassBackgroundDrawableFactory = r1;
        r1.setGlassEngine(this.glassEngine);
    L19:
        this.currentChat = r10;
        return;
    L5:
        if (SharedConfig.chatBlurEnabled() == false) goto L18;
        DownscaleScrollableNoiseSuppressor r32 = new DownscaleScrollableNoiseSuppressor();
        this.scrollableViewNoiseSuppressor = r32;
        BlurredBackgroundSourceRenderNode r5 = new BlurredBackgroundSourceRenderNode(r0);
        this.glassBackgroundSourceFrostedRenderNode = r5;
        r5.setScrollableNoiseSuppressor(r32, -3);
        r5.setUnderSource(r0);
        if (LiteMode.isEnabled(262144) == false) goto L13;
        BlurredBackgroundSourceRenderNode r2 = new BlurredBackgroundSourceRenderNode(r0);
        this.glassBackgroundSourceRenderNode = r2;
        r2.setScrollableNoiseSuppressor(r32, -2);
        r2.setUnderSource(r0);
        BlurredBackgroundDrawableViewFactory r02 = new BlurredBackgroundDrawableViewFactory(r2);
        this.glassBackgroundDrawableFactory = r02;
        r02.setGlassEngine(this.glassEngine);
        if (LiteMode.isEnabled(262144) == false) goto L11;
        int r22 = AndroidUtilities.dp(8.0f);
    L12:
        r02.setOutset(r22);
        r02.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        this.recommendedAdditionalSizeY = 0;
        goto L19
    L11:
        r22 = AndroidUtilities.dp(48.0f);
        goto L12
    L13:
        this.glassBackgroundSourceRenderNode = null;
        BlurredBackgroundDrawableViewFactory r03 = new BlurredBackgroundDrawableViewFactory(r5);
        this.glassBackgroundDrawableFactory = r03;
        r03.setGlassEngine(this.glassEngine);
        if (LiteMode.isEnabled(262144) == false) goto L16;
        int r12 = AndroidUtilities.dp(8.0f);
    L17:
        r03.setOutset(r12);
        this.recommendedAdditionalSizeY = AndroidUtilities.dp(48.0f);
        goto L19
    L16:
        r12 = AndroidUtilities.dp(48.0f);
        goto L17
    }

    public static /* synthetic */ ActionBar access$000(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$1000(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1200(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1300(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1400(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1500(ChannelAdminLogActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$1600(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1700(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$200(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$2000(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2100(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2200(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2300(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2400(ChannelAdminLogActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2500(ChannelAdminLogActivity r0) {
        return r0.resourceProvider;
    }

    public static /* synthetic */ int access$2600(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2800(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2900(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$300(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$3000(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$3100(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$3200(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$3300(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3400(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3500(ChannelAdminLogActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$400(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$600(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$700(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$800(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$900(ChannelAdminLogActivity r0) {
        return r0.actionBar;
    }

    private MessageObject actionMessagesDeletedBy(long r8, long r10, ArrayList<MessageObject> r12, boolean r13, boolean r14) {
        int r1 = 0;
    L3:
        MessageObject r4 = null;
        if (r1 >= this.filteredMessages.size()) goto L13;
        MessageObject r2 = this.filteredMessages.get(r1);
        if (r2 == null) goto L12;
        if (r2.contentType != 1) goto L12;
        if (r2.actionDeleteGroupEventId != r8) goto L12;
    L15:
        if (r2 != null) goto L22;
        TLRPC.TL_message r22 = new TLRPC.TL_message();
        r22.dialog_id = -this.currentChat.id;
        r22.id = -1;
        r22.date = r12.get(0).messageOwner.date;     // Catch: Exception -> L19
    L21:
        r2 = new MessageObject(this.currentAccount, r22, false, false);
    L19:
        e = move-exception;
        FileLog.e(e);
    L22:
        TLRPC.User r102 = getMessagesController().getUser(Long.valueOf(r10));
        r2.contentType = 1;
        if (r14 == true) goto L25;
    L27:
        r2.actionDeleteGroupEventId = -1;
    L28:
        String r82 = TextUtils.join(", ", r12.stream().map(new ChannelAdminLogActivity$$ExternalSyntheticLambda13()).distinct().map(new ChannelAdminLogActivity$$ExternalSyntheticLambda14(this)).filter(new ChannelAdminLogActivity$$ExternalSyntheticLambda15()).limit(4).toArray());
        if (r14 == false) goto L31;
        String r11 = "EventLogDeletedMultipleMessagesToExpand";
    L32:
        SpannableStringBuilder r9 = new SpannableStringBuilder(MessageObject.replaceWithLink(LocaleController.formatPluralString(r11, r12.size(), new Object[]{r82}), "un1", r102));
        if (r14 == true) goto L35;
    L49:
        r2.messageText = r9;
        if (r12.size() <= 0) goto L53;
        r4 = r12.get(r12.size() - 1);
    L53:
        if (r4 != null) goto L55;
    L58:
        return r2;
    L55:
        if (this.stableIdByEventExpand.containsKey(r4.eventId) == true) goto L57;
        LongSparseArray<Integer> r83 = this.stableIdByEventExpand;
        long r92 = r4.eventId;
        int r112 = lastStableId;
        lastStableId = r112 + 1;
        r83.put(r92, Integer.valueOf(r112));
    L57:
        r2.stableId = this.stableIdByEventExpand.get(r4.eventId).intValue();
        goto L58
    L35:
        if (r12.size() <= 1) goto L49;
        ProfileActivity.ShowDrawable r84 = findDrawable(r2.messageText);
        if (r84 == null) goto L39;
        AnimatedTextView.AnimatedTextDrawable r103 = r84.textDrawable;
        if (r13 == false) goto L46;
        int r113 = R.string.EventLogDeletedMultipleMessagesHide;
    L47:
        r103.setText(LocaleController.getString(r113), false);
    L48:
        r84.setBounds(0, 0, r84.getIntrinsicWidth(), r84.getIntrinsicHeight());
        r9.append(" S");
        r9.setSpan(new ColoredImageSpan(r84), r9.length() - 1, r9.length(), 33);
        goto L49
    L46:
        r113 = R.string.EventLogDeletedMultipleMessagesShow;
        goto L47
    L39:
        if (r13 == false) goto L41;
        int r104 = R.string.EventLogDeletedMultipleMessagesHide;
    L42:
        r84 = new ProfileActivity.ShowDrawable(LocaleController.getString(r104));
        r84.textDrawable.setTypeface(AndroidUtilities.bold());
        r84.textDrawable.setTextSize(AndroidUtilities.dp(10.0f));
        r84.setTextColor(-1);
        r84.setBackgroundColor(503316480);
        goto L48
    L41:
        r104 = R.string.EventLogDeletedMultipleMessagesShow;
        goto L42
    L31:
        r11 = "EventLogDeletedMultipleMessages";
        goto L32
    L25:
        if (r12.size() <= 1) goto L27;
        r2.actionDeleteGroupEventId = r8;
    L12:
        r1 = r1 + 1;
        goto L3
    L13:
        r2 = null;
        goto L15
    }

    private void addCanBanUser(Bundle r5, long r6) {
        TLRPC.Chat r0 = this.currentChat;
        if (r0.megagroup == true) goto L5;
        return;
    L5:
        if (this.admins != null) goto L7;
        return;
    L7:
        if (ChatObject.canBlockUsers(r0) == false) goto L24;
        int r02 = 0;
    L11:
        if (r02 >= this.admins.size()) goto L18;
        TLRPC.ChannelParticipant r1 = this.admins.get(r02);
        if (MessageObject.getPeerId(r1.peer) == r6) goto L15;
        r02 = r02 + 1;
        goto L11
    L15:
        if (r1.can_edit == true) goto L18;
        return;
    L18:
        r5.putLong("ban_chat_id", this.currentChat.id);
        return;
    }

    private void alertUserOpenError(MessageObject r4) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.AppName));
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        if (r4.type != 3) goto L8;
        r0.setMessage(LocaleController.getString(R.string.NoPlayerInstalled));
    L9:
        showDialog(r0.create());
        return;
    L8:
        r0.setMessage(LocaleController.formatString(R.string.NoHandleAppInstalled, new Object[]{r4.getDocument().mime_type}));
        goto L9
    }

    private void checkScrollForLoad(boolean r5) {
        LinearLayoutManager r0 = this.chatLayoutManager;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.paused == true) goto L27;
        int r02 = r0.findFirstVisibleItemPosition();
        int r2 = 1;
        if (r02 != (-1)) goto L10;
        int r1 = 0;
    L11:
        if (r1 <= 0) goto L23;
        this.chatAdapter.getItemCount();
        if (r5 == false) goto L15;
        r2 = 4;
    L15:
        if (r02 <= r2) goto L17;
        return;
    L17:
        if (this.loading == false) goto L19;
        return;
    L19:
        if (this.endReached == true) goto L26;
        loadMessages(false);
        return;
    L26:
        return;
    L23:
        return;
    L10:
        r1 = Math.abs(this.chatLayoutManager.findLastVisibleItemPosition() - r02) + 1;
        goto L11
    }

    private void closeMenu() {
        ActionBarPopupWindow r0 = this.scrimPopupWindow;
        if (r0 == null) goto L6;
        r0.dismiss();
        return;
    }

    private boolean createMenu(View r2) {
        return createMenu(r2, 0.0f, 0.0f);
    }

    private boolean createMenu(View r25, float r26, float r27) {
        View r5 = r25;
        if ((r5 instanceof ChatMessageCell) == false) goto L6;
        MessageObject r11 = ((ChatMessageCell) r5).getMessageObject();
    L10:
        if (r11 != null) goto L12;
        return false;
    L12:
        int r14 = getMessageType(r11);
        this.selectedObject = r11;
        if (getParentActivity() != null) goto L15;
        return false;
    L15:
        ArrayList r15 = new ArrayList();
        ArrayList r9 = new ArrayList();
        ArrayList r7 = new ArrayList();
        if (this.currentChat == null) goto L25;
        TLRPC.TL_channelAdminLogEvent r2 = r11.currentEvent;
        if (r2 == null) goto L25;
        TLRPC.ChannelAdminLogEventAction r22 = r2.action;
        if ((r22 instanceof TLRPC.TL_channelAdminLogEventActionParticipantJoinByInvite) == false) goto L25;
        TLRPC.TL_channelAdminLogEventActionParticipantJoinByInvite r23 = (TLRPC.TL_channelAdminLogEventActionParticipantJoinByInvite) r22;
        if (r23.invite == null) goto L25;
        TLRPC.ChatFull r3 = getMessagesController().getChatFull(this.currentChat.id);
        Context r4 = getContext();
        TLRPC.TL_chatInviteExported r24 = r23.invite;
        TLRPC.Chat r52 = this.currentChat;
        InviteLinkBottomSheet r0 = new InviteLinkBottomSheet(r4, r24, r3, null, this, r52.id, false, ChatObject.isChannelAndNotMegaGroup(r52));
        r0.setCanEdit(false);
        r0.show();
        return true;
    L25:
        TLRPC.TL_channelAdminLogEvent r28 = r11.currentEvent;
        if (r28 == null) goto L43;
        int r16 = 1;
        if ((r28.action instanceof TLRPC.TL_channelAdminLogEventActionDeleteMessage) == false) goto L34;
        if (r28.user_id != getMessagesController().telegramAntispamUserId) goto L34;
    L32:
        r5 = r25;
        if ((r5 instanceof ChatActionCell) == false) goto L40;
        SpannableString r02 = new SpannableString(">");
        Drawable r29 = getContext().getResources().getDrawable(R.drawable.attach_arrow_right).mutate();
        r29.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_undo_cancelColor), PorterDuff.Mode.MULTIPLY));
        r29.setBounds(0, 0, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        r02.setSpan(new ImageSpan(r29, 2), 0, r02.length(), 33);
        SpannableStringBuilder r210 = new SpannableStringBuilder();
        r210.append(LocaleController.getString(R.string.EventLogFilterGroupInfo)).append("\u2009").append(r02).append("\u2009").append(LocaleController.getString(R.string.ChannelAdministrators));
        r210.setSpan(new AnonymousClass12(this), 0, r210.length(), 33);
        Bulletin r03 = BulletinFactory.of(this).createSimpleBulletin(R.raw.msg_antispam, LocaleController.getString(R.string.ChannelAntiSpamUser), AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.ChannelAntiSpamInfo2), r210));
        r03.setDuration(5000);
        r03.show();
        return true;
    L40:
        r15.add(LocaleController.getString(R.string.ReportFalsePositive));
        r7.add(Integer.valueOf(R.drawable.msg_notspam));
        r9.add(34);
        TLRPC.User r211 = null;
        r15.add(null);
        r7.add(null);
        r9.add(null);
    L44:
        MessageObject r42 = this.selectedObject;
        if (r42.type != 0) goto L47;
    L50:
        r15.add(LocaleController.getString(R.string.Copy));
        r7.add(Integer.valueOf(R.drawable.msg_copy));
        r9.add(3);
    L51:
        if (r14 != r16) goto L79;
        TLRPC.TL_channelAdminLogEvent r04 = this.selectedObject.currentEvent;
        if (r04 == null) goto L67;
        TLRPC.ChannelAdminLogEventAction r212 = r04.action;
        if ((r212 instanceof TLRPC.TL_channelAdminLogEventActionChangeStickerSet) == false) goto L67;
        TLRPC.TL_channelAdminLogEventActionChangeStickerSet r213 = (TLRPC.TL_channelAdminLogEventActionChangeStickerSet) r212;
        TLRPC.InputStickerSet r05 = r213.new_stickerset;
        if (r05 != null) goto L59;
    L62:
        r05 = r213.prev_stickerset;
    L61:
        TLRPC.InputStickerSet r32 = r05;
        if (r32 == null) goto L130;
        showDialog(new StickersAlert(getParentActivity(), this, r32, null, null, false));
        return true;
    L130:
        ChannelAdminLogActivity$$ExternalSyntheticLambda17 r06 = new ChannelAdminLogActivity$$ExternalSyntheticLambda17(this, r9, r15, r7, r5, r26, r27);
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L154;
        TLRPC.TL_channelAdminLogEvent r53 = r11.currentEvent;
        if (r53 == null) goto L154;
        TLRPC.ChannelAdminLogEventAction r54 = r53.action;
        if ((r54 instanceof TLRPC.TL_channelAdminLogEventActionDeleteMessage) == false) goto L137;
    L144:
        TLRPC.Message r55 = r11.messageOwner;
        if (r55 == null) goto L154;
        if (r55.from_id == null) goto L154;
        TLRPC.User r6 = getMessagesController().getUser(Long.valueOf(this.selectedObject.messageOwner.from_id.user_id));
        if (r6 == null) goto L154;
        if (UserObject.isUserSelf(r6) == true) goto L154;
        getMessagesController().getChannelParticipant(this.currentChat, r6, new ChannelAdminLogActivity$$ExternalSyntheticLambda18(this, r15, r7, r9, r06));
        return true;
    L137:
        if ((r54 instanceof TLRPC.TL_channelAdminLogEventActionEditMessage) == true) goto L144;
        if ((r54 instanceof TLRPC.TL_channelAdminLogEventActionParticipantJoin) == true) goto L144;
        if ((r54 instanceof TLRPC.TL_channelAdminLogEventActionParticipantJoinByInvite) == true) goto L144;
        if ((r54 instanceof TLRPC.TL_channelAdminLogEventActionParticipantJoinByRequest) == true) goto L144;
    L154:
        r06.run();
        return true;
    L59:
        if ((r05 instanceof TLRPC.TL_inputStickerSetEmpty) == false) goto L61;
    L67:
        if (r04 != null) goto L69;
    L73:
        if (r04 == null) goto L130;
        if ((r04.action instanceof TLRPC.TL_channelAdminLogEventActionChangeHistoryTTL) == false) goto L130;
        if (ChatObject.canUserDoAdminAction(this.currentChat, 13) == false) goto L130;
        ClearHistoryAlert r17 = new ClearHistoryAlert(getParentActivity(), null, this.currentChat, false, null);
        r17.setDelegate(new AnonymousClass13(this));
        showDialog(r17);
        goto L130
    L69:
        if ((r04.action instanceof TLRPC.TL_channelAdminLogEventActionChangeEmojiStickerSet) == false) goto L73;
        GroupStickersActivity r07 = new GroupStickersActivity(this.currentChat.id, true);
        TLRPC.ChatFull r214 = getMessagesController().getChatFull(this.currentChat.id);
        if (r214 == null) goto L130;
        r07.setInfo(r214);
        presentFragment(r07);
        goto L130
    L79:
        if (r14 != 3) goto L86;
        TLRPC.MessageMedia r215 = this.selectedObject.messageOwner.media;
        if ((r215 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L130;
        if (MessageObject.isNewGifDocument(r215.webpage.document) == false) goto L130;
        r15.add(LocaleController.getString(R.string.SaveToGIFs));
        r7.add(Integer.valueOf(R.drawable.msg_gif));
        r9.add(11);
        goto L130
    L86:
        if (r14 != 4) goto L101;
        if (this.selectedObject.isVideo() == false) goto L91;
        r15.add(LocaleController.getString(R.string.SaveToGallery));
        r7.add(Integer.valueOf(R.drawable.msg_gallery));
        r9.add(4);
        r15.add(LocaleController.getString(R.string.ShareFile));
        r7.add(Integer.valueOf(R.drawable.msg_share));
        r9.add(6);
        goto L130
    L91:
        if (this.selectedObject.isMusic() == false) goto L94;
        r15.add(LocaleController.getString(R.string.SaveToMusic));
        r7.add(Integer.valueOf(R.drawable.msg_download));
        r9.add(10);
        r15.add(LocaleController.getString(R.string.ShareFile));
        r7.add(Integer.valueOf(R.drawable.msg_share));
        r9.add(6);
        goto L130
    L94:
        if (this.selectedObject.getDocument() != null) goto L96;
        r15.add(LocaleController.getString(R.string.SaveToGallery));
        r7.add(Integer.valueOf(R.drawable.msg_gallery));
        r9.add(4);
        goto L130
    L96:
        if (MessageObject.isNewGifDocument(this.selectedObject.getDocument()) == false) goto L98;
        r15.add(LocaleController.getString(R.string.SaveToGIFs));
        r7.add(Integer.valueOf(R.drawable.msg_gif));
        r9.add(11);
    L98:
        r15.add(LocaleController.getString(R.string.SaveToDownloads));
        r7.add(Integer.valueOf(R.drawable.msg_download));
        r9.add(10);
        r15.add(LocaleController.getString(R.string.ShareFile));
        r7.add(Integer.valueOf(R.drawable.msg_share));
        r9.add(6);
        goto L130
    L101:
        if (r14 != 5) goto L104;
        r15.add(LocaleController.getString(R.string.ApplyLocalizationFile));
        r7.add(Integer.valueOf(R.drawable.msg_language));
        r9.add(5);
        r15.add(LocaleController.getString(R.string.SaveToDownloads));
        r7.add(Integer.valueOf(R.drawable.msg_download));
        r9.add(10);
        r15.add(LocaleController.getString(R.string.ShareFile));
        r7.add(Integer.valueOf(R.drawable.msg_share));
        r9.add(6);
        goto L130
    L104:
        if (r14 != 10) goto L107;
        r15.add(LocaleController.getString(R.string.ApplyThemeFile));
        r7.add(Integer.valueOf(R.drawable.msg_theme));
        r9.add(5);
        r15.add(LocaleController.getString(R.string.SaveToDownloads));
        r7.add(Integer.valueOf(R.drawable.msg_download));
        r9.add(10);
        r15.add(LocaleController.getString(R.string.ShareFile));
        r7.add(Integer.valueOf(R.drawable.msg_share));
        r9.add(6);
        goto L130
    L107:
        if (r14 != 6) goto L109;
        r15.add(LocaleController.getString(R.string.SaveToGallery));
        r7.add(Integer.valueOf(R.drawable.msg_gallery));
        r9.add(7);
        r15.add(LocaleController.getString(R.string.SaveToDownloads));
        r7.add(Integer.valueOf(R.drawable.msg_download));
        r9.add(10);
        r15.add(LocaleController.getString(R.string.ShareFile));
        r7.add(Integer.valueOf(R.drawable.msg_share));
        r9.add(6);
        goto L130
    L109:
        if (r14 != 7) goto L116;
        if (this.selectedObject.isMask() == false) goto L113;
        r15.add(LocaleController.getString(R.string.AddToMasks));
    L114:
        r7.add(Integer.valueOf(R.drawable.msg_sticker));
        r9.add(9);
        goto L130
    L113:
        r15.add(LocaleController.getString(R.string.AddToStickers));
        goto L114
    L116:
        if (r14 != 8) goto L130;
        long r33 = this.selectedObject.messageOwner.media.user_id;
        if (r33 == 0) goto L120;
        TLRPC.User r12 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r33));
    L121:
        if (r12 == null) goto L128;
        if (r12.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L128;
        if (ContactsController.getInstance(this.currentAccount).contactsDict.get(Long.valueOf(r12.id)) != null) goto L128;
        r15.add(LocaleController.getString(R.string.AddContactTitle));
        r7.add(Integer.valueOf(R.drawable.msg_addcontact));
        r9.add(15);
    L128:
        if (TextUtils.isEmpty(this.selectedObject.messageOwner.media.phone_number) == true) goto L130;
        r15.add(LocaleController.getString(R.string.Copy));
        r7.add(Integer.valueOf(R.drawable.msg_copy));
        r9.add(16);
        r15.add(LocaleController.getString(R.string.Call));
        r7.add(Integer.valueOf(R.drawable.msg_calls));
        r9.add(17);
        goto L130
    L120:
        r12 = r211;
        goto L121
    L47:
        if (r42.caption == null) goto L51;
    L34:
        if ((r11.currentEvent.action instanceof TLRPC.TL_channelAdminLogEventActionToggleAntiSpam) == true) goto L32;
        r5 = r25;
    L42:
        r211 = null;
        goto L44
    L43:
        r16 = 1;
        goto L42
    L6:
        if ((r5 instanceof ChatActionCell) == false) goto L8;
        r11 = ((ChatActionCell) r5).getMessageObject();
        goto L10
    L8:
        r11 = null;
        goto L10
    }

    private TextureView createTextureView(boolean r8) {
        if (this.parentLayout != null) goto L7;
        return null;
    L7:
        if (this.roundVideoContainer != null) goto L13;
        AnonymousClass16 r0 = new AnonymousClass16(this, getParentActivity());
        this.roundVideoContainer = r0;
        r0.setOutlineProvider(new AnonymousClass17(this));
        this.roundVideoContainer.setClipToOutline(true);
        this.roundVideoContainer.setWillNotDraw(false);
        this.roundVideoContainer.setVisibility(4);
        AspectRatioFrameLayout r02 = new AspectRatioFrameLayout(getParentActivity());
        this.aspectRatioFrameLayout = r02;
        r02.setBackgroundColor(0);
        if (r8 == false) goto L11;
        this.roundVideoContainer.addView(this.aspectRatioFrameLayout, LayoutHelper.createFrame(-1, -1.0f));
    L11:
        TextureView r82 = new TextureView(getParentActivity());
        this.videoTextureView = r82;
        r82.setOpaque(false);
        this.aspectRatioFrameLayout.addView(this.videoTextureView, LayoutHelper.createFrame(-1, -1.0f));
    L13:
        if (this.roundVideoContainer.getParent() != null) goto L15;
        ChatActivityFragmentView r83 = this.contentView;
        FrameLayout r03 = this.roundVideoContainer;
        int r5 = AndroidUtilities.roundMessageSize;
        r83.addView(r03, 1, new FrameLayout.LayoutParams(r5, r5));
    L15:
        this.roundVideoContainer.setVisibility(4);
        this.aspectRatioFrameLayout.setDrawingReady(false);
        return this.videoTextureView;
    }

    private void filterDeletedMessages() {
        ChannelAdminLogActivity r0 = this;
        ArrayList r8 = new ArrayList();
        ArrayList<MessageObject> r5 = new ArrayList();
        r0.filteredMessagesUpdatedPosition.clear();
        int r1 = 0;
    L4:
        if (r1 >= r0.messages.size()) goto L74;
        MessageObject r2 = r0.messages.get(r1);
        long r3 = r0.messageDeletedBy(r2);
        if (r2.stableId > 0) goto L8;
        int r6 = lastStableId;
        lastStableId = r6 + 1;
        r2.stableId = r6;
    L8:
        int r10 = r1 + 1;
        if (r10 >= r0.messages.size()) goto L11;
        MessageObject r12 = r0.messages.get(r10);
    L12:
        long r62 = r0.messageDeletedBy(r12);
        if (r3 == 0) goto L15;
        r5.add(r2);
    L17:
        if (r3 != r62) goto L19;
    L72:
        ArrayList<MessageObject> r13 = r5;
    L73:
        r1 = r10;
        r5 = r13;
        goto L4
    L19:
        if (r5.isEmpty() == true) goto L72;
        TLRPC.ReplyMarkup r14 = r2.messageOwner.reply_markup;
        if ((r14 instanceof TLRPC.TL_replyInlineMarkup) == true) goto L23;
    L25:
        boolean r15 = false;
    L26:
        int r32 = r8.size();
        ArrayList<MessageObject> r122 = new ArrayList();
        int r4 = r5.size() - 1;
    L27:
        if (r4 < 0) goto L32;
        if (r5.get(r4).contentType != 1) goto L32;
        r122.add(r5.remove(r4));
        r4 = r4 - 1;
    L32:
        if (r5.isEmpty() == true) goto L66;
        MessageObject r42 = r5.get(r5.size() - 1);
        if (TextUtils.isEmpty(r0.searchQuery) == true) goto L36;
    L38:
        boolean r7 = false;
    L40:
        if (r0.expandedEvents.contains(Long.valueOf(r42.eventId)) == true) goto L44;
        if (r7 == false) goto L44;
        r0.setupExpandButton(r42, r5.size() - 1);
        r8.add(r42);
    L49:
        TLRPC.ReplyMarkup r63 = r42.messageOwner.reply_markup;
        if ((r63 instanceof TLRPC.TL_replyInlineMarkup) == true) goto L52;
    L54:
        boolean r64 = false;
    L55:
        if (r15 == r64) goto L65;
        r42.forceUpdate = true;
        ChatActivityAdapter r43 = r0.chatAdapter;
        if (r15 == false) goto L59;
        int r65 = r5.size() - 1;
    L60:
        r43.notifyItemChanged(r65 + r32);
        ChatActivityAdapter r44 = r0.chatAdapter;
        if (r15 == false) goto L63;
        int r16 = r5.size() - 1;
    L64:
        r44.notifyItemChanged((r32 + r16) + 1);
        goto L65
    L63:
        r16 = 0;
        goto L64
    L59:
        r65 = 0;
    L65:
        long r33 = r2.eventId;
        r13 = r5;
        r8.add(r0.actionMessagesDeletedBy(r33, r2.currentEvent.user_id, r5, r0.expandedEvents.contains(Long.valueOf(r33)), r7));
    L68:
        if (r122.isEmpty() == true) goto L70;
        MessageObject r02 = r122.get(r122.size() - 1);
        r8.addAll(r122);
        long r17 = r02.eventId;
        long r34 = r02.currentEvent.user_id;
        r0 = this;
        r8.add(r0.actionMessagesDeletedBy(r17, r34, r122, true, false));
    L71:
        r13.clear();
        goto L73
    L70:
        r0 = this;
        goto L71
    L52:
        if (((TLRPC.TL_replyInlineMarkup) r63).rows.isEmpty() == true) goto L54;
        r64 = true;
    L44:
        int r66 = 0;
    L46:
        if (r66 >= r5.size()) goto L48;
        r0.setupExpandButton(r5.get(r66), 0);
        r66 = r66 + 1;
        goto L46
    L48:
        r8.addAll(r5);
        goto L49
    L36:
        if (r5.size() <= 3) goto L38;
        r7 = true;
        goto L40
    L66:
        r13 = r5;
        goto L68
    L23:
        if (((TLRPC.TL_replyInlineMarkup) r14).rows.isEmpty() == true) goto L25;
        r15 = true;
        goto L26
    L15:
        r8.add(r2);
        goto L17
    L11:
        r12 = null;
        goto L12
    L74:
        r0.filteredMessages.clear();
        r0.filteredMessages.addAll(r8);
    }

    public static ProfileActivity.ShowDrawable findDrawable(CharSequence r3) {
        if ((r3 instanceof Spannable) == false) goto L14;
        int r2 = 0;
        ColoredImageSpan[] r32 = (ColoredImageSpan[]) ((Spannable) r3).getSpans(0, r3.length(), ColoredImageSpan.class);
    L6:
        if (r2 >= r32.length) goto L20;
        ColoredImageSpan r0 = r32[r2];
        if (r0 == null) goto L13;
        Drawable r02 = r0.drawable;
        if ((r02 instanceof ProfileActivity.ShowDrawable) == false) goto L13;
        return (ProfileActivity.ShowDrawable) r02;
    L13:
        r2 = r2 + 1;
        goto L6
    L20:
        return null;
    L14:
        return null;
    }

    private int getHeightForMessage(MessageObject r6, boolean r7) {
        boolean r1 = false;
        if (getParentActivity() != null) goto L6;
        return 0;
    L6:
        if (this.dummyMessageCell != null) goto L8;
        this.dummyMessageCell = new ChatMessageCell(getParentActivity(), this.currentAccount);
    L8:
        ChatMessageCell r0 = this.dummyMessageCell;
        TLRPC.Chat r2 = this.currentChat;
        if (r2 == null) goto L11;
        boolean r4 = true;
    L12:
        r0.isChat = r4;
        if (ChatObject.isChannel(r2) == true) goto L15;
    L17:
        r0.isMegagroup = r1;
        return this.dummyMessageCell.computeHeight(r6, null, r7);
    L15:
        if (this.currentChat.megagroup == false) goto L17;
        r1 = true;
        goto L17
    L11:
        r4 = false;
        goto L12
    }

    private CharSequence getMessageContent(MessageObject r4, int r5, boolean r6) {
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        if (r6 == false) goto L16;
        long r1 = r4.getFromChatId();
        if (r5 == r1) goto L16;
        if (r1 <= 0) goto L11;
        TLRPC.User r3 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r1));
        if (r3 == null) goto L16;
        r0.append(ContactsController.formatName(r3.first_name, r3.last_name)).append(":\n");
        goto L16
    L11:
        if (r1 >= 0) goto L16;
        TLRPC.Chat r32 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r1));
        if (r32 == null) goto L16;
        r0.append(r32.title).append(":\n");
    L16:
        if (TextUtils.isEmpty(r4.messageText) == false) goto L19;
        r0.append(r4.messageOwner.message);
        return r0;
    L19:
        r0.append(r4.messageText);
        return r0;
    }

    private int getMessageType(MessageObject r7) {
        if (r7 != null) goto L5;
        return -1;
    L5:
        int r1 = r7.type;
        if (r1 != 6) goto L8;
        return -1;
    L8:
        boolean r4 = true;
        if (r1 == 10) goto L92;
        if (r1 == 11) goto L92;
        if (r1 == 16) goto L92;
        if (r7.isVoice() == false) goto L19;
        return 2;
    L19:
        if (r7.isSticker() == false) goto L21;
    L80:
        TLRPC.InputStickerSet r72 = r7.getInputStickerSet();
        if ((r72 instanceof TLRPC.TL_inputStickerSetID) == false) goto L86;
        if (MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r72.id) == true) goto L90;
        return 7;
    L90:
        return 2;
    L86:
        if ((r72 instanceof TLRPC.TL_inputStickerSetShortName) == false) goto L90;
        if (MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r72.short_name) == true) goto L90;
        return 7;
    L21:
        if (r7.isAnimatedSticker() == true) goto L80;
        if (r7.isRoundVideo() == false) goto L30;
        if (r7.isRoundVideo() == false) goto L39;
        if (BuildVars.DEBUG_VERSION == true) goto L30;
    L39:
        if (r7.type != 12) goto L43;
        return 8;
    L43:
        if (r7.isMediaEmpty() == false) goto L90;
        return 3;
    L30:
        if ((r7.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) == false) goto L32;
    L46:
        String r0 = r7.messageOwner.attachPath;
        if (r0 != null) goto L49;
    L53:
        boolean r02 = false;
    L54:
        if (r02 == false) goto L56;
    L58:
        r4 = r02;
    L59:
        if (r4 == false) goto L90;
        if (r7.getDocument() == null) goto L78;
        String r6 = r7.getDocument().mime_type;
        if (r6 != null) goto L65;
        return 4;
    L65:
        if (r7.getDocumentName().toLowerCase().endsWith("attheme") == false) goto L68;
        return 10;
    L68:
        if (r6.endsWith("/xml") == false) goto L72;
        return 5;
    L72:
        if (r6.endsWith("/png") == false) goto L74;
    L77:
        return 6;
    L74:
        if (r6.endsWith("/jpg") == true) goto L77;
        if (r6.endsWith("/jpeg") == true) goto L77;
        return 4;
    L78:
        return 4;
    L56:
        if (getFileLoader().getPathToMessage(r7.messageOwner).exists() == false) goto L58;
    L49:
        if (r0.length() == 0) goto L53;
        if (new File(r7.messageOwner.attachPath).exists() == false) goto L53;
        r02 = true;
        goto L54
    L32:
        if (r7.getDocument() != null) goto L46;
        if (r7.isMusic() == true) goto L46;
        if (r7.isVideo() == false) goto L39;
    L92:
        if (r7.getId() != 0) goto L94;
        return -1;
    L94:
        return 1;
    }

    private int getScrollOffsetForMessage(int r2) {
        return Math.max(-AndroidUtilities.dp(2.0f), (this.chatListView.getMeasuredHeight() - r2) / 2);
    }

    private int getScrollOffsetForMessage(MessageObject r2) {
        return getScrollOffsetForMessage(getHeightForMessage(r2, !TextUtils.isEmpty(this.highlightMessageQuote))) - scrollOffsetForQuote(r2);
    }

    private int getScrollingOffsetForView(View r2) {
        return (this.chatListView.getMeasuredHeight() - r2.getBottom()) - this.chatListView.getPaddingBottom();
    }

    private void hideFloatingDateView(boolean r6) {
        if (this.floatingDateView.getTag() != null) goto L5;
        return;
    L5:
        if (this.currentFloatingDateOnScreen == false) goto L7;
        return;
    L7:
        if (this.scrollingFloatingDate == true) goto L9;
    L10:
        this.floatingDateView.setTag(null);
        if (r6 == false) goto L14;
        AnimatorSet r62 = new AnimatorSet();
        this.floatingDateAnimation = r62;
        r62.setDuration(150);
        this.floatingDateAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.floatingDateView, "alpha", new float[]{0.0f})});
        this.floatingDateAnimation.addListener(new AnonymousClass19(this));
        this.floatingDateAnimation.setStartDelay(500);
        this.floatingDateAnimation.start();
        return;
    L14:
        AnimatorSet r63 = this.floatingDateAnimation;
        if (r63 == null) goto L17;
        r63.cancel();
        this.floatingDateAnimation = null;
    L17:
        this.floatingDateView.setAlpha(0.0f);
        return;
    L9:
        if (this.currentFloatingTopIsNotMessage == true) goto L10;
    }

    private void invalidateMergedVisibleBlurredPositionsAndSourcesImpl(int r5) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor != null) goto L8;
        return;
    L8:
        if (BitwiseUtils.hasFlag(r5, 4) == false) goto L10;
        GlassPositionsArray r52 = this.glassEngine.getAllVisibleDrawablesWithDisplayListPositionsMerged();
        r52.clip(0.0f, this.chatListView.getY(), this.contentView.getWidth(), this.chatListView.getY() + this.chatListView.getHeight());
        this.scrollableViewNoiseSuppressor.setupRenderNodes(r52);
    L10:
        DownscaleScrollableNoiseSuppressor r53 = this.scrollableViewNoiseSuppressor;
        ChatActivityFragmentView r0 = this.contentView;
        Objects.requireNonNull(r0);
        r53.invalidateResultRenderNodes(new ChannelAdminLogActivity$$ExternalSyntheticLambda11(r0), this.contentView.getWidth(), this.contentView.getHeight());
    }

    private /* synthetic */ String lambda$actionMessagesDeletedBy$6(Long r5) {
        if (r5.longValue() >= 0) goto L11;
        TLRPC.Chat r4 = getMessagesController().getChat(Long.valueOf(-r5.longValue()));
        if (r4 != null) goto L9;
        return null;
    L9:
        return r4.title;
    L11:
        return UserObject.getForcedFirstName(getMessagesController().getUser(r5));
    }

    private /* synthetic */ void lambda$createMenu$14(int r1, ArrayList r2, Integer r3, View r4) {
        if (this.selectedObject != null) goto L5;
        return;
    L5:
        if (r1 >= r2.size()) goto L10;
        processSelectedOption(r3.intValue());
        return;
    }

    private /* synthetic */ void lambda$createMenu$15(ArrayList r17, ArrayList r18, ArrayList r19, View r20, float r21, float r22) {
        if (r17.isEmpty() == false) goto L5;
        return;
    L5:
        if (getParentActivity() == null) goto L58;
        int r6 = 0;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r2 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getParentActivity(), R.drawable.popup_fixed_alert, getResourceProvider(), 0);
        r2.setMinimumWidth(AndroidUtilities.dp(200.0f));
        Rect r4 = new Rect();
        getParentActivity().getResources().getDrawable(R.drawable.popup_fixed_alert).mutate().getPadding(r4);
        r2.setBackgroundColor(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        int r5 = r18.size();
        int r7 = 0;
    L8:
        boolean r8 = true;
        if (r7 >= r5) goto L26;
        if (r17.get(r7) != null) goto L13;
        r2.addView(new ActionBarPopupWindow.GapView(getContext(), getResourceProvider()), LayoutHelper.createLinear(-1, 8));
    L25:
        r7 = r7 + 1;
        goto L8
    L13:
        Activity r10 = getParentActivity();
        if (r7 != 0) goto L16;
        boolean r11 = true;
    L18:
        if (r7 == (r5 - 1)) goto L21;
        r8 = false;
    L21:
        ActionBarMenuSubItem r9 = new ActionBarMenuSubItem(r10, r11, r8, getResourceProvider());
        r9.setMinimumWidth(AndroidUtilities.dp(200.0f));
        r9.setTextAndIcon((CharSequence) r18.get(r7), ((Integer) r19.get(r7)).intValue());
        if (((Integer) r17.get(r7)).intValue() != 35) goto L24;
        r9.setColors(getThemedColor(Theme.key_text_RedBold), getThemedColor(Theme.key_text_RedRegular));
    L24:
        Integer r82 = (Integer) r17.get(r7);
        r2.addView(r9);
        r9.setOnClickListener(new ChannelAdminLogActivity$$ExternalSyntheticLambda23(this, r7, r17, r82));
        goto L25
    L16:
        r11 = false;
        goto L18
    L26:
        AnonymousClass14 r1 = new AnonymousClass14(this, this.contentView.getContext());
        r1.addView(r2, LayoutHelper.createLinearRelatively(-2.0f, -2.0f, 3, 0.0f, 0.0f, 0.0f, 0.0f));
        r1.setPopupWindowLayout(r2);
        AnonymousClass15 r3 = new AnonymousClass15(this, r1, -2, -2);
        this.scrimPopupWindow = r3;
        r3.setPauseNotifications(true);
        this.scrimPopupWindow.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        this.scrimPopupWindow.setOutsideTouchable(true);
        this.scrimPopupWindow.setClippingEnabled(true);
        this.scrimPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
        this.scrimPopupWindow.setFocusable(true);
        r1.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.scrimPopupWindow.setInputMethodMode(2);
        this.scrimPopupWindow.setSoftInputMode(48);
        this.scrimPopupWindow.getContentView().setFocusableInTouchMode(true);
        r2.setFitItems(true);
        int r23 = (((r20.getLeft() + ((int) r21)) - r1.getMeasuredWidth()) + r4.left) - AndroidUtilities.dp(28.0f);
        if (r23 >= AndroidUtilities.dp(6.0f)) goto L30;
        r23 = AndroidUtilities.dp(6.0f);
    L33:
        if (AndroidUtilities.isTablet() == false) goto L35;
        int[] r32 = new int[2];
        this.fragmentView.getLocationInWindow(r32);
        r23 = r23 + r32[0];
    L35:
        int r33 = this.contentView.getHeight();
        int r52 = r1.getMeasuredHeight() + AndroidUtilities.dp(48.0f);
        int r72 = this.contentView.measureKeyboardHeight();
        if (r72 <= AndroidUtilities.dp(20.0f)) goto L38;
        r33 = r33 + r72;
    L38:
        if (r52 >= r33) goto L49;
        r6 = (int) ((this.chatListView.getY() + r20.getTop()) + r22);
        if (((r52 - r4.top) - r4.bottom) <= AndroidUtilities.dp(240.0f)) goto L43;
        r6 = r6 + (AndroidUtilities.dp(240.0f) - r52);
    L43:
        if (r6 >= (this.chatListView.getY() + AndroidUtilities.dp(24.0f))) goto L45;
        r6 = (int) (this.chatListView.getY() + AndroidUtilities.dp(24.0f));
    L52:
        this.scrimPopupX = r23;
        this.scrimPopupY = r6;
        r1.setMaxHeight(r33 - r6);
        this.scrimPopupWindow.showAtLocation(this.chatListView, 51, r23, r6);
        this.scrimPopupWindow.dimBehind();
        return;
    L45:
        int r42 = r33 - r52;
        if (r6 <= (r42 - AndroidUtilities.dp(8.0f))) goto L52;
        r6 = r42 - AndroidUtilities.dp(8.0f);
        goto L52
    L49:
        if (this.inBubbleMode == true) goto L52;
        r6 = AndroidUtilities.statusBarHeight;
        goto L52
    L30:
        if (r23 <= ((this.chatListView.getMeasuredWidth() - AndroidUtilities.dp(6.0f)) - r1.getMeasuredWidth())) goto L33;
        r23 = (this.chatListView.getMeasuredWidth() - AndroidUtilities.dp(6.0f)) - r1.getMeasuredWidth();
        goto L33
    }

    private /* synthetic */ void lambda$createMenu$16(TLRPC.ChannelParticipant r9, ArrayList r10, ArrayList r11, ArrayList r12, Runnable r13) {
        this.selectedParticipant = r9;
        if (r9 != null) goto L5;
    L37:
        r13.run();
        return;
    L5:
        if ((r9.peer instanceof TLRPC.TL_peerUser) == false) goto L32;
        if (ChatObject.isChannel(this.currentChat) == false) goto L15;
        TLRPC.ChannelParticipant r0 = getMessagesController().getAdminInChannel(r9.peer.user_id, this.currentChat.id);
        if (r0 == null) goto L32;
        if ((r0 instanceof TLRPC.TL_channelParticipantCreator) == true) goto L28;
        if (r0.admin_rights.manage_call == false) goto L32;
    L28:
        if (this.currentChat.creator == false) goto L37;
    L15:
        TLRPC.ChatFull r02 = getMessagesController().getChatFull(this.currentChat.id);
        if (r02 == null) goto L32;
        TLRPC.ChatParticipants r1 = r02.participants;
        if (r1 == null) goto L32;
        int r14 = r1.participants.size();
        int r2 = 0;
    L20:
        if (r2 >= r14) goto L32;
        TLRPC.ChatParticipant r3 = r02.participants.participants.get(r2);
        if (r3.user_id == r9.peer.user_id) goto L24;
        r2 = r2 + 1;
        goto L20
    L24:
        if ((r3 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L28;
        if ((r3 instanceof TLRPC.TL_chatParticipantCreator) == true) goto L28;
    L32:
        if (ChatObject.canUserDoAction(this.currentChat, r9, 6) == false) goto L34;
    L35:
        r10.add(LocaleController.getString(R.string.Restrict));
        r11.add(Integer.valueOf(R.drawable.msg_block2));
        r12.add(33);
    L36:
        r10.add(LocaleController.getString(R.string.Ban));
        r11.add(Integer.valueOf(R.drawable.msg_block));
        r12.add(35);
        goto L37
    L34:
        if (ChatObject.canUserDoAction(this.currentChat, r9, 7) == false) goto L36;
        goto L35
    }

    private /* synthetic */ void lambda$createMenu$17(ArrayList r8, ArrayList r9, ArrayList r10, Runnable r11, TLRPC.ChannelParticipant r12) {
        AndroidUtilities.runOnUIThread(new ChannelAdminLogActivity$$ExternalSyntheticLambda22(this, r12, r8, r9, r10, r11));
    }

    private /* synthetic */ void lambda$createView$10(View r4) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AdminLogFilterAlert2 r42 = new AdminLogFilterAlert2(this, this.currentFilter, this.selectedAdmins, this.currentChat.megagroup);
        r42.setCurrentAdmins(this.admins);
        r42.setAdminLogFilterAlertDelegate(new ChannelAdminLogActivity$$ExternalSyntheticLambda8(this));
        showDialog(r42);
    }

    private /* synthetic */ void lambda$createView$11(View r3) {
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        if (this.currentChat.megagroup == false) goto L5;
        r32.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EventLogInfoDetail)));
    L6:
        r32.setPositiveButton(LocaleController.getString(R.string.OK), null);
        r32.setTitle(LocaleController.getString(R.string.EventLogInfoTitle));
        showDialog(r32.create());
        return;
    L5:
        r32.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EventLogInfoDetailChannel)));
        goto L6
    }

    private /* synthetic */ void lambda$createView$12(int r1) {
        loadMessages(true);
    }

    private /* synthetic */ void lambda$createView$13(View r5) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AndroidUtilities.hideKeyboard(this.searchItem.getSearchField());
        showDialog(AlertsCreator.createCalendarPickerDialog(getParentActivity(), 1375315200000L, new ChannelAdminLogActivity$$ExternalSyntheticLambda12(this), null).create());
    }

    private /* synthetic */ void lambda$createView$9(TLRPC.TL_channelAdminLogEventsFilter r1, LongSparseArray r2) {
        this.currentFilter = r1;
        this.selectedAdmins = r2;
        if (r1 != null) goto L7;
        if (r2 != null) goto L7;
        this.avatarContainer.setSubtitle(LocaleController.getString(R.string.EventLogAllEvents));
    L8:
        loadMessages(true);
        return;
    L7:
        this.avatarContainer.setSubtitle(LocaleController.getString(R.string.EventLogSelectedEvents));
        goto L8
    }

    private /* synthetic */ void lambda$loadAdmins$22(TLRPC.TL_error r5, TLObject r6) {
        if (r5 != null) goto L14;
        TLRPC.TL_channels_channelParticipants r62 = (TLRPC.TL_channels_channelParticipants) r6;
        getMessagesController().putUsers(r62.users, false);
        getMessagesController().putChats(r62.chats, false);
        this.admins = r62.participants;
        if (this.currentChat == null) goto L10;
        TLRPC.ChatFull r52 = getMessagesController().getChatFull(this.currentChat.id);
        if (r52 == null) goto L10;
        if (r52.antispam == false) goto L10;
        AnonymousClass18 r53 = new AnonymousClass18(this);
        r53.user_id = getMessagesController().telegramAntispamUserId;
        r53.peer = getMessagesController().getPeer(r53.user_id);
        loadAntispamUser(getMessagesController().telegramAntispamUserId);
        this.admins.add(0, r53);
    L10:
        Dialog r54 = this.visibleDialog;
        if ((r54 instanceof AdminLogFilterAlert2) == false) goto L15;
        ((AdminLogFilterAlert2) r54).setCurrentAdmins(this.admins);
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$loadAdmins$23(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChannelAdminLogActivity$$ExternalSyntheticLambda9(this, r3, r2));
    }

    private /* synthetic */ void lambda$loadAntispamUser$24(TLObject r4, TLRPC.TL_error r5) {
        if ((r4 instanceof Vector) == false) goto L16;
        ArrayList<T> r42 = ((Vector) r4).objects;
        ArrayList<TLRPC.User> r52 = new ArrayList();
        int r1 = 0;
    L6:
        if (r1 >= r42.size()) goto L11;
        if ((r42.get(r1) instanceof TLRPC.User) == false) goto L10;
        r52.add((TLRPC.User) r42.get(r1));
    L10:
        r1 = r1 + 1;
        goto L6
    L11:
        getMessagesController().putUsers(r52, false);
        return;
    }

    private /* synthetic */ void lambda$loadMessages$2() {
        saveScrollPosition(false);
        this.chatAdapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$loadMessages$3() {
        checkScrollForLoad(true);
    }

    private /* synthetic */ void lambda$loadMessages$4(TLRPC.TL_channels_adminLogResults r18) {
        this.loadsCount--;
        int r4 = 0;
        this.chatListItemAnimator.setShouldAnimateEnterFromBottom(false);
        saveScrollPosition(false);
        MessagesController.getInstance(this.currentAccount).putUsers(r18.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r18.chats, false);
        int r2 = this.messages.size();
        int r5 = 0;
        boolean r6 = false;
    L4:
        if (r5 >= r18.events.size()) goto L21;
        TLRPC.TL_channelAdminLogEvent r10 = r18.events.get(r5);
        if (this.messagesDict.indexOfKey(r10.id) >= 0) goto L20;
        TLRPC.ChannelAdminLogEventAction r7 = r10.action;
        if ((r7 instanceof TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin) == false) goto L15;
        TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin r72 = (TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin) r7;
        if ((r72.prev_participant instanceof TLRPC.TL_channelParticipantCreator) == false) goto L15;
        if ((r72.new_participant instanceof TLRPC.TL_channelParticipantCreator) == false) goto L20;
    L15:
        this.minEventId = Math.min(this.minEventId, r10.id);
        MessageObject r8 = new MessageObject(this.currentAccount, r10, this.messages, this.messagesByDays, this.currentChat, this.mid, false);
        if (r8.contentType < 0) goto L19;
        this.messagesDict.put(r10.id, r8);
    L19:
        r6 = true;
    L20:
        r5 = r5 + 1;
        goto L4
    L21:
        this.messages.size();
        ArrayList<MessageObject> r82 = new ArrayList();
    L23:
        if (r2 >= this.messages.size()) goto L56;
        MessageObject r1 = this.messages.get(r2);
        if (r1 != null) goto L27;
    L31:
        if (r1 == null) goto L54;
        TLRPC.Message r52 = r1.messageOwner;
        if (r52 == null) goto L54;
        TLRPC.MessageReplyHeader r53 = r52.reply_to;
        if (r53 == null) goto L54;
        if (r53.reply_to_peer_id != null) goto L53;
        int r73 = 0;
    L41:
        if (r73 >= this.messages.size()) goto L50;
        if (r2 == r73) goto L49;
        MessageObject r9 = this.messages.get(r73);
        if (r9.contentType == 1) goto L49;
        if (r9.getRealId() != r53.reply_to_msg_id) goto L49;
    L51:
        if (r9 == null) goto L53;
        r1.replyMessageObject = r9;
    L49:
        r73 = r73 + 1;
        goto L41
    L50:
        r9 = null;
    L53:
        r82.add(r1);
    L54:
        r2 = r2 + 1;
        goto L23
    L27:
        if (r1.contentType == 0) goto L31;
        if (r1.getRealId() < 0) goto L31;
        this.realMessagesDict.put(r1.getRealId(), r1);
        goto L31
    L56:
        if (r82.isEmpty() == true) goto L58;
        MediaDataController.getInstance(this.currentAccount).loadReplyMessagesForMessages(r82, -this.currentChat.id, 0, 0, new ChannelAdminLogActivity$$ExternalSyntheticLambda19(this), getClassGuid(), null);
    L58:
        filterDeletedMessages();
        this.loading = false;
        if (r6 == true) goto L61;
        this.endReached = true;
    L61:
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, false, 0.3f, true);
        this.chatListView.setEmptyView(this.emptyViewContainer);
        ChatActivityAdapter r12 = this.chatAdapter;
        if (r12 == null) goto L66;
        r12.notifyDataSetChanged();
        ChatListRecyclerView r13 = this.chatListView;
        if (r13 == null) goto L66;
        r13.post(new ChannelAdminLogActivity$$ExternalSyntheticLambda20(this));
    L66:
        ActionBarMenuItem r14 = this.searchItem;
        if (r14 != null) goto L69;
        return;
    L69:
        if (this.filteredMessages.isEmpty() == true) goto L71;
    L73:
        r14.setVisibility(r4);
        return;
    L71:
        if (TextUtils.isEmpty(this.searchQuery) == false) goto L73;
        r4 = 8;
        goto L73
    }

    private /* synthetic */ void lambda$loadMessages$5(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        AndroidUtilities.runOnUIThread(new ChannelAdminLogActivity$$ExternalSyntheticLambda10(this, (TLRPC.TL_channels_adminLogResults) r1));
        return;
    }

    private /* synthetic */ void lambda$processSelectedOption$18(TLObject r2) {
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L7;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.msg_antispam, LocaleController.getString(R.string.ChannelAntiSpamFalsePositiveReported)).show();
        return;
    L7:
        if ((r2 instanceof TLRPC.TL_boolFalse) == false) goto L10;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
        return;
    L10:
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.UnknownError)).show();
    }

    private /* synthetic */ void lambda$processSelectedOption$19(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChannelAdminLogActivity$$ExternalSyntheticLambda30(this, r1));
    }

    private /* synthetic */ void lambda$processSelectedOption$20(TLRPC.User r4) {
        BulletinFactory.of(this).createSimpleBulletin(R.raw.ic_ban, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.RestrictedParticipantSending, new Object[]{UserObject.getFirstName(r4)}))).show(false);
        reloadLastMessages();
    }

    private /* synthetic */ void lambda$processSelectedOption$21() {
        reloadLastMessages();
    }

    private /* synthetic */ void lambda$reloadLastMessages$0(TLRPC.TL_channels_adminLogResults r12) {
        this.reloadingLastMessages = false;
        this.chatListItemAnimator.setShouldAnimateEnterFromBottom(false);
        saveScrollPosition(false);
        MessagesController.getInstance(this.currentAccount).putUsers(r12.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r12.chats, false);
        ArrayList r6 = new ArrayList();
        HashMap r7 = new HashMap();
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= r12.events.size()) goto L27;
        TLRPC.TL_channelAdminLogEvent r5 = r12.events.get(r1);
        if (this.messagesDict.indexOfKey(r5.id) >= 0) goto L25;
        TLRPC.ChannelAdminLogEventAction r3 = r5.action;
        if ((r3 instanceof TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin) == false) goto L15;
        TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin r32 = (TLRPC.TL_channelAdminLogEventActionParticipantToggleAdmin) r3;
        if ((r32.prev_participant instanceof TLRPC.TL_channelParticipantCreator) == false) goto L15;
        if ((r32.new_participant instanceof TLRPC.TL_channelParticipantCreator) == false) goto L25;
    L15:
        this.minEventId = Math.min(this.minEventId, r5.id);
        MessageObject r33 = new MessageObject(this.currentAccount, r5, r6, r7, this.currentChat, this.mid, false);
        if (r33.contentType < 0) goto L25;
        TLRPC.TL_channelAdminLogEvent r4 = r33.currentEvent;
        if (r4 == null) goto L23;
        if ((r4.action instanceof TLRPC.TL_channelAdminLogEventActionDeleteMessage) == true) goto L25;
    L23:
        if (this.messagesDict.containsKey(r5.id) == true) goto L25;
        this.messages.add(0, r33);
        this.messagesDict.put(r5.id, r33);
        r2 = true;
    L25:
        r1 = r1 + 1;
        goto L4
    L27:
        if (this.chatAdapter == null) goto L38;
        if (r2 == false) goto L39;
        filterDeletedMessages();
        this.chatAdapter.notifyDataSetChanged();
        return;
    L39:
        return;
    }

    private /* synthetic */ void lambda$reloadLastMessages$1(TLObject r1, TLRPC.TL_error r2) {
        if (r1 == null) goto L5;
        AndroidUtilities.runOnUIThread(new ChannelAdminLogActivity$$ExternalSyntheticLambda25(this, (TLRPC.TL_channels_adminLogResults) r1));
        return;
    }

    private /* synthetic */ void lambda$showOpenUrlAlert$25(String r1, AlertDialog r2, int r3) {
        Browser.openUrl(getParentActivity(), r1, true);
    }

    private /* synthetic */ void lambda$startMessageUnselect$26() {
        this.highlightMessageId = Integer.MAX_VALUE;
        this.highlightMessageQuoteFirst = false;
        this.highlightMessageQuote = null;
        this.highlightMessageQuoteOffset = -1;
        this.showNoQuoteAlert = false;
        updateVisibleRows();
        this.unselectRunnable = null;
    }

    private void loadAdmins() {
        TLRPC.TL_channels_getParticipants r0 = new TLRPC.TL_channels_getParticipants();
        r0.channel = MessagesController.getInputChannel(this.currentChat);
        r0.filter = new TLRPC.TL_channelParticipantsAdmins();
        r0.offset = 0;
        r0.limit = 200;
        int r02 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChannelAdminLogActivity$$ExternalSyntheticLambda7(this));
        ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(r02, this.classGuid);
    }

    private void loadAntispamUser(long r3) {
        if (getMessagesController().getUser(Long.valueOf(r3)) == null) goto L5;
        return;
    L5:
        TLRPC.TL_users_getUsers r0 = new TLRPC.TL_users_getUsers();
        TLRPC.TL_inputUser r1 = new TLRPC.TL_inputUser();
        r1.user_id = r3;
        r0.id.add(r1);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChannelAdminLogActivity$$ExternalSyntheticLambda16(this));
    }

    private void loadMessages(boolean r8) {
        if (this.loading == true) goto L35;
        int r0 = 0;
        if (r8 == false) goto L11;
        this.minEventId = LongCompanionObject.MAX_VALUE;
        FrameLayout r2 = this.progressView;
        if (r2 == null) goto L10;
        AndroidUtilities.updateViewVisibilityAnimated(r2, true, 0.3f, true);
        this.emptyViewContainer.setVisibility(4);
        this.chatListView.setEmptyView(null);
    L10:
        this.messagesDict.clear();
        this.messages.clear();
        this.messagesByDays.clear();
        filterDeletedMessages();
        this.loadsCount = 0;
    L11:
        this.loading = true;
        TLRPC.TL_channels_getAdminLog r22 = new TLRPC.TL_channels_getAdminLog();
        r22.channel = MessagesController.getInputChannel(this.currentChat);
        r22.q = this.searchQuery;
        r22.limit = 50;
        if (r8 == false) goto L14;
    L16:
        r22.max_id = 0;
    L17:
        r22.min_id = 0;
        TLRPC.TL_channelAdminLogEventsFilter r3 = this.currentFilter;
        if (r3 == null) goto L21;
        r22.flags |= 1;
        r22.events_filter = r3;
    L21:
        if (this.selectedAdmins == null) goto L26;
        r22.flags |= 2;
    L24:
        if (r0 >= this.selectedAdmins.size()) goto L26;
        r22.admins.add(MessagesController.getInstance(this.currentAccount).getInputUser(this.selectedAdmins.valueAt(r0)));
        r0 = r0 + 1;
    L26:
        this.loadsCount++;
        updateEmptyPlaceholder();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new ChannelAdminLogActivity$$ExternalSyntheticLambda5(this));
        if (r8 == false) goto L33;
        ChatActivityAdapter r7 = this.chatAdapter;
        if (r7 == null) goto L34;
        r7.notifyDataSetChanged();
        return;
    L34:
        return;
    L33:
        return;
    L14:
        if (this.messages.isEmpty() == true) goto L16;
        r22.max_id = this.minEventId;
        goto L17
    }

    private long messageDeletedBy(MessageObject r1) {
        if (r1 == null) goto L10;
        TLRPC.TL_channelAdminLogEvent r0 = r1.currentEvent;
        if (r0 != null) goto L6;
        return 0;
    L6:
        if ((r0.action instanceof TLRPC.TL_channelAdminLogEventActionDeleteMessage) == true) goto L9;
        return 0;
    L9:
        return r0.user_id;
    L10:
        return 0;
    }

    private void moveScrollToLastMessage() {
        if (this.chatListView != null) goto L5;
        return;
    L5:
        if (this.messages.isEmpty() == true) goto L9;
        this.chatLayoutManager.scrollToPositionWithOffset(this.filteredMessages.size() - 1, (-100000) - this.chatListView.getPaddingTop());
        return;
    }

    private void processSelectedOption(int r22) {
        closeMenu();
        MessageObject r4 = this.selectedObject;
        if (r4 != null) goto L5;
        return;
    L5:
        int r5 = 0;
        int r6 = 3;
        if (r22 != 3) goto L8;
        AndroidUtilities.addToClipboard(getMessageContent(r4, 0, true));
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(R.string.MessageCopied)).show();
    L178:
        this.selectedObject = null;
        this.selectedParticipant = null;
        return;
    L8:
        if (r22 != 4) goto L10;
        String r0 = r4.messageOwner.attachPath;
        if (r0 != null) goto L154;
    L158:
        if (r0 != null) goto L160;
    L161:
        r0 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L162:
        int r1 = this.selectedObject.type;
        if (r1 == 3) goto L166;
        if (r1 != 1) goto L178;
    L166:
        if (Build.VERSION.SDK_INT <= 28) goto L170;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L170;
    L173:
        Activity r12 = getParentActivity();
        if (this.selectedObject.type != 3) goto L176;
        r5 = 1;
    L176:
        MediaController.saveFile(r0, r12, r5, null, null);
    L170:
        if (getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L173;
        getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
        this.selectedObject = null;
        this.selectedParticipant = null;
        return;
    L160:
        if (r0.length() != 0) goto L162;
    L154:
        if (r0.length() <= 0) goto L158;
        if (new File(r0).exists() == true) goto L158;
        r0 = null;
        goto L158
    L10:
        if (r22 != 5) goto L12;
        String r02 = r4.messageOwner.attachPath;
        if (r02 != null) goto L114;
    L118:
        File r03 = null;
    L119:
        if (r03 != null) goto L123;
        File r13 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner);
        if (r13.exists() == false) goto L123;
        r03 = r13;
    L123:
        if (r03 == null) goto L178;
        if (r03.getName().toLowerCase().endsWith("attheme") == false) goto L144;
        LinearLayoutManager r14 = this.chatLayoutManager;
        if (r14 != null) goto L129;
    L135:
        Theme.ThemeInfo r04 = Theme.applyThemeFile(r03, this.selectedObject.getDocumentName(), null, true);
        if (r04 == null) goto L138;
        presentFragment(new ThemePreviewActivity(r04));
        goto L178
    L138:
        this.scrollToPositionOnRecreate = -1;
        if (getParentActivity() != null) goto L142;
        this.selectedObject = null;
        this.selectedParticipant = null;
        return;
    L142:
        AlertDialog.Builder r05 = new AlertDialog.Builder(getParentActivity());
        r05.setTitle(LocaleController.getString(R.string.AppName));
        r05.setMessage(LocaleController.getString(R.string.IncorrectTheme));
        r05.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r05.create());
        goto L178
    L129:
        if (r14.findLastVisibleItemPosition() >= (this.chatLayoutManager.getItemCount() - 1)) goto L134;
        int r15 = this.chatLayoutManager.findFirstVisibleItemPosition();
        this.scrollToPositionOnRecreate = r15;
        RecyclerListView.Holder r16 = (RecyclerListView.Holder) this.chatListView.findViewHolderForAdapterPosition(r15);
        if (r16 == null) goto L133;
        this.scrollToOffsetOnRecreate = r16.itemView.getTop();
        goto L135
    L133:
        this.scrollToPositionOnRecreate = -1;
        goto L135
    L134:
        this.scrollToPositionOnRecreate = -1;
        goto L135
    L144:
        if (LocaleController.getInstance().applyLanguageFile(r03, this.currentAccount) == false) goto L147;
        presentFragment(new LanguageSelectActivity());
        goto L178
    L147:
        if (getParentActivity() != null) goto L150;
        this.selectedObject = null;
        this.selectedParticipant = null;
        return;
    L150:
        AlertDialog.Builder r06 = new AlertDialog.Builder(getParentActivity());
        r06.setTitle(LocaleController.getString(R.string.AppName));
        r06.setMessage(LocaleController.getString(R.string.IncorrectLocalization));
        r06.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r06.create());
        goto L178
    L114:
        if (r02.length() == 0) goto L118;
        r03 = new File(this.selectedObject.messageOwner.attachPath);
        if (r03.exists() == false) goto L118;
    L12:
        if (r22 != 6) goto L14;
        String r07 = r4.messageOwner.attachPath;
        if (r07 != null) goto L97;
    L101:
        if (r07 != null) goto L103;
    L104:
        r07 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L105:
        Intent r3 = new Intent("android.intent.action.SEND");
        r3.setType(this.selectedObject.getDocument().mime_type);
        r3.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(getParentActivity(), ApplicationLoader.getApplicationId() + ".provider", new File(r07)));     // Catch: Exception -> L108
        r3.setFlags(1);     // Catch: Exception -> L108
    L181:
        getParentActivity().startActivityForResult(Intent.createChooser(r3, LocaleController.getString(R.string.ShareFile)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L180
    L108:
        r3.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(r07)));
        goto L181
    L103:
        if (r07.length() != 0) goto L105;
    L97:
        if (r07.length() <= 0) goto L101;
        if (new File(r07).exists() == true) goto L101;
        r07 = null;
        goto L101
    L14:
        if (r22 == 7) goto L74;
        switch(r22) {
            case 9: goto L73;
            case 10: goto L43;
            case 11: goto L41;
            default: goto L16;
        };
    L16:
        switch(r22) {
            case 15: goto L40;
            case 16: goto L39;
            case 17: goto L183;
            default: goto L17;
        };
    L17:
        switch(r22) {
            case 33: goto L30;
            case 34: goto L28;
            case 35: goto L19;
            default: goto L178;
        };
    L19:
        getMessagesController().deleteParticipantFromChat(this.currentChat.id, getMessagesController().getInputPeer(this.selectedObject.messageOwner.from_id), false, false, new ChannelAdminLogActivity$$ExternalSyntheticLambda29(this));
        if (this.currentChat == null) goto L178;
        if ((this.selectedObject.messageOwner.from_id instanceof TLRPC.TL_peerUser) == false) goto L178;
        if (BulletinFactory.canShowBulletin(this) == false) goto L178;
        TLRPC.User r08 = getMessagesController().getUser(Long.valueOf(this.selectedObject.messageOwner.from_id.user_id));
        if (r08 == null) goto L178;
        BulletinFactory.createRemoveFromChatBulletin(this, r08, this.currentChat.title).show();
        goto L178
    L28:
        TLRPC.TL_channels_reportAntiSpamFalsePositive r09 = new TLRPC.TL_channels_reportAntiSpamFalsePositive();
        r09.channel = getMessagesController().getInputChannel(this.currentChat.id);
        r09.msg_id = this.selectedObject.getRealId();
        getConnectionsManager().sendRequest(r09, new ChannelAdminLogActivity$$ExternalSyntheticLambda27(this));
        goto L178
    L30:
        if (this.selectedParticipant == null) goto L178;
        TLRPC.User r122 = getMessagesController().getUser(Long.valueOf(DialogObject.getPeerDialogId(this.selectedParticipant.peer)));
        TLRPC.ChannelParticipant r010 = this.selectedParticipant;
        if (r010.banned_rights != null) goto L34;
        r010.banned_rights = new TLRPC.TL_chatBannedRights();
    L34:
        TLRPC.TL_chatBannedRights r011 = this.selectedParticipant.banned_rights;
        r011.send_plain = true;
        r011.send_messages = true;
        r011.send_media = true;
        r011.send_stickers = true;
        r011.send_gifs = true;
        r011.send_games = true;
        r011.send_inline = true;
        r011.send_polls = true;
        r011.send_photos = true;
        r011.send_videos = true;
        r011.send_roundvideos = true;
        r011.send_audios = true;
        r011.send_voices = true;
        r011.send_docs = true;
        r011.send_reactions = true;
        getMessagesController().setParticipantBannedRole(this.currentChat.id, r122, null, this.selectedParticipant.banned_rights, true, getFragmentForAlert(1), new ChannelAdminLogActivity$$ExternalSyntheticLambda28(this, r122));
        goto L178
    L39:
        AndroidUtilities.addToClipboard(r4.messageOwner.media.phone_number);
        BulletinFactory.of(this).createCopyBulletin(LocaleController.getString(R.string.PhoneCopied)).show();
        goto L178
    L40:
        Bundle r012 = new Bundle();
        r012.putLong("user_id", this.selectedObject.messageOwner.media.user_id);
        r012.putString("phone", this.selectedObject.messageOwner.media.phone_number);
        r012.putBoolean("addContact", true);
        presentFragment(new ContactAddActivity(r012));
        goto L178
    L183:
        Intent r013 = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + this.selectedObject.messageOwner.media.phone_number));     // Catch: Exception -> L37
        r013.addFlags(268435456);     // Catch: Exception -> L37
        getParentActivity().startActivityForResult(r013, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L37
    L37:
        e = move-exception;
        FileLog.e(e);
        goto L178
    L41:
        MessagesController.getInstance(this.currentAccount).saveGif(this.selectedObject, r4.getDocument());
        goto L178
    L73:
        showDialog(new StickersAlert(getParentActivity(), this, this.selectedObject.getInputStickerSet(), null, null, false));
        goto L178
    L43:
        if (Build.VERSION.SDK_INT <= 28) goto L47;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L47;
    L50:
        String r014 = FileLoader.getDocumentFileName(this.selectedObject.getDocument());
        if (TextUtils.isEmpty(r014) == false) goto L53;
        r014 = this.selectedObject.getFileName();
    L53:
        String r17 = this.selectedObject.messageOwner.attachPath;
        if (r17 != null) goto L56;
    L60:
        if (r17 != null) goto L62;
    L63:
        r17 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L64:
        Activity r32 = getParentActivity();
        if (this.selectedObject.isMusic() == true) goto L69;
        r6 = 2;
    L69:
        if (this.selectedObject.getDocument() == null) goto L71;
        String r42 = this.selectedObject.getDocument().mime_type;
    L72:
        MediaController.saveFile(r17, r32, r6, r014, r42);
        goto L178
    L71:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L72
    L62:
        if (r17.length() != 0) goto L64;
    L56:
        if (r17.length() <= 0) goto L60;
        if (new File(r17).exists() == true) goto L60;
        r17 = null;
    L47:
        if (getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L50;
        getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
        this.selectedObject = null;
        this.selectedParticipant = null;
        return;
    L74:
        String r015 = r4.messageOwner.attachPath;
        if (r015 != null) goto L77;
    L81:
        if (r015 != null) goto L83;
    L84:
        r015 = getFileLoader().getPathToMessage(this.selectedObject.messageOwner).toString();
    L86:
        if (Build.VERSION.SDK_INT <= 28) goto L90;
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L90;
    L93:
        MediaController.saveFile(r015, getParentActivity(), 0, null, null);
    L90:
        if (getParentActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) goto L93;
        getParentActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 4);
        this.selectedObject = null;
        this.selectedParticipant = null;
        return;
    L83:
        if (r015.length() != 0) goto L86;
    L77:
        if (r015.length() <= 0) goto L81;
        if (new File(r015).exists() == true) goto L81;
        r015 = null;
        goto L81
    }

    private boolean quickRejectChild(View r7, RectF r8) {
        if (r8 != null) goto L4;
        return false;
    L4:
        if (this.chatListView == null) goto L11;
        if (r7 == null) goto L12;
        this.tmpViewRectF.set(r7.getX(), r7.getY() + this.chatListView.getY(), r7.getX() + r7.getWidth(), (r7.getY() + this.chatListView.getY()) + r7.getHeight());
        return !this.tmpViewRectF.intersect(r8);
    L12:
        return false;
    L11:
        return false;
    }

    private void removeSelectedMessageHighlight() {
        if (this.highlightMessageQuote == null) goto L5;
        return;
    L5:
        Runnable r0 = this.unselectRunnable;
        if (r0 == null) goto L8;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.unselectRunnable = null;
    L8:
        this.highlightMessageId = Integer.MAX_VALUE;
        this.highlightMessageQuoteFirst = false;
        this.highlightMessageQuote = null;
    }

    private int scrollOffsetForQuote(MessageObject r10) {
        if (TextUtils.isEmpty(this.highlightMessageQuote) == true) goto L51;
        if (r10 == null) goto L51;
        if (TextUtils.isEmpty(r10.caption) == true) goto L13;
        ChatMessageCell r0 = this.dummyMessageCell;
        if (r0 == null) goto L13;
        MessageObject.TextLayoutBlocks r3 = r0.captionLayout;
        if (r3 == null) goto L13;
        int r02 = (int) r0.captionY;
        CharSequence r102 = r10.caption;
        ArrayList<MessageObject.TextLayoutBlock> r32 = r3.textLayoutBlocks;
    L19:
        ChatMessageCell r4 = this.dummyMessageCell;
        if (r4 == null) goto L22;
        r4.computedGroupCaptionY = 0;
        r4.computedCaptionLayout = null;
    L22:
        if (r32 == null) goto L50;
        if (r102 == null) goto L50;
        int r103 = MessageObject.findQuoteStart(r102.toString(), this.highlightMessageQuote, this.highlightMessageQuoteOffset);
        if (r103 >= 0) goto L28;
        return 0;
    L28:
        int r1 = 0;
    L30:
        if (r1 >= r32.size()) goto L50;
        MessageObject.TextLayoutBlock r42 = r32.get(r1);
        String r6 = r42.textLayout.getText().toString();
        int r7 = r42.charactersOffset;
        if (r103 > r7) goto L34;
        r1 = r1 + 1;
        goto L30
    L34:
        if ((r103 - r7) <= (r6.length() - 1)) goto L36;
        float r104 = r02 + ((int) ((r42.textYOffset(r32) + r42.padTop) + r42.height));
    L37:
        float r03 = AndroidUtilities.displaySize.y;
        float r33 = 0.5f;
        if (isKeyboardVisible() == false) goto L40;
        float r12 = 0.7f;
    L42:
        if (r104 <= (r03 * r12)) goto L48;
        float r04 = AndroidUtilities.displaySize.y;
        if (isKeyboardVisible() == false) goto L47;
        r33 = 0.7f;
    L47:
        return (int) (r104 - (r04 * r33));
    L48:
        return 0;
    L40:
        r12 = 0.5f;
        goto L42
    L36:
        r104 = r5.getLineTop(r5.getLineForOffset(r103 - r42.charactersOffset)) + ((r02 + r42.textYOffset(r32)) + r42.padTop);
    L50:
        return 0;
    L13:
        CharSequence r05 = r10.messageText;
        r32 = r10.textLayoutBlocks;
        ChatMessageCell r105 = this.dummyMessageCell;
        if (r105 != null) goto L16;
    L18:
        r102 = r05;
        r02 = 0;
        goto L19
    L16:
        if (r105.linkPreviewAbove == false) goto L18;
        r02 = r105.linkPreviewHeight + AndroidUtilities.dp(10.0f);
        r102 = r05;
    L51:
        ChatMessageCell r9 = this.dummyMessageCell;
        if (r9 == null) goto L54;
        r9.computedGroupCaptionY = 0;
        r9.computedCaptionLayout = null;
    L54:
        return 0;
    }

    private void setupExpandButton(MessageObject r4, int r5) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r5 > 0) goto L11;
        TLRPC.ReplyMarkup r3 = r4.messageOwner.reply_markup;
        if ((r3 instanceof TLRPC.TL_replyInlineMarkup) == false) goto L8;
        ((TLRPC.TL_replyInlineMarkup) r3).rows.clear();
    L8:
        TLRPC.ReplyMarkup r32 = r4.messageOwner.reply_markup;
        if ((r32 instanceof TLRPC.TL_replyKeyboardMarkup) == false) goto L12;
        ((TLRPC.TL_replyKeyboardMarkup) r32).rows.clear();
    L12:
        r4.measureInlineBotButtons();
        return;
    L11:
        TLRPC.TL_replyInlineMarkup r33 = new TLRPC.TL_replyInlineMarkup();
        r4.messageOwner.reply_markup = r33;
        TL_keyboard.TL_keyboardInlineButtonRow r0 = new TL_keyboard.TL_keyboardInlineButtonRow();
        r33.rows.add(r0);
        TL_keyboard.TL_keyboardInlineButton r34 = new TL_keyboard.TL_keyboardInlineButton();
        r34.text = LocaleController.formatPluralString("EventLogExpandMore", r5, new Object[0]);
        r0.buttons.add(r34);
        goto L12
    }

    private void showInviteLinkBottomSheet(TLRPC.TL_messages_exportedChatInvite r14, HashMap<Long, TLRPC.User> r15) {
        TLRPC.ChatFull r6 = getMessagesController().getChatFull(this.currentChat.id);
        InviteLinkBottomSheet r3 = new InviteLinkBottomSheet(this.contentView.getContext(), (TLRPC.TL_chatInviteExported) r14.invite, r6, r15, this, r6.id, false, ChatObject.isChannel(this.currentChat));
        r3.setInviteDelegate(new AnonymousClass20(this));
        r3.show();
    }

    private CharSequence smallerNewNewLine(CharSequence r5) {
        int r4 = AndroidUtilities.charSequenceIndexOf(r5, "\n\n");
        if (r4 >= 0) goto L5;
    L10:
        return r5;
    L5:
        if (Build.VERSION.SDK_INT < 29) goto L10;
        if ((r5 instanceof Spannable) == true) goto L9;
        r5 = new SpannableStringBuilder(r5);
    L9:
        ChatActionCell$$ExternalSyntheticApiModelOutline1.m();
        ((SpannableStringBuilder) r5).setSpan(ChatActionCell$$ExternalSyntheticApiModelOutline0.m(AndroidUtilities.dp(8.0f)), r4 + 1, r4 + 2, 33);
        goto L10
    }

    private void startMessageUnselect() {
        Runnable r0 = this.unselectRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        ChannelAdminLogActivity$$ExternalSyntheticLambda26 r02 = new ChannelAdminLogActivity$$ExternalSyntheticLambda26(this);
        this.unselectRunnable = r02;
        if (this.highlightMessageQuote == null) goto L8;
        long r1 = 2500;
    L9:
        AndroidUtilities.runOnUIThread(r02, r1);
        return;
    L8:
        r1 = 1000;
        goto L9
    }

    private void updateBottomOverlay() {
    }

    private void updateEmptyPlaceholder() {
        if (this.emptyView != null) goto L6;
        return;
    L6:
        if (TextUtils.isEmpty(this.searchQuery) == true) goto L10;
        this.emptyImageView.setVisibility(8);
        this.emptyView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(3.0f));
        this.emptyView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.NoLogFound)));
        return;
    L10:
        if (this.selectedAdmins == null) goto L12;
    L20:
        this.emptyImageView.setVisibility(8);
        this.emptyView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(3.0f));
        this.emptyView.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.NoLogFoundFiltered)));
        return;
    L12:
        if (this.currentFilter != null) goto L20;
        this.emptyImageView.setVisibility(0);
        this.emptyView.setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        boolean r0 = this.currentChat.megagroup;
        TextView r1 = this.emptyView;
        if (r0 == false) goto L18;
        r1.setText(smallerNewNewLine(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EventLogEmpty2))));
        return;
    L18:
        r1.setText(smallerNewNewLine(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EventLogEmptyChannel2))));
    }

    private void updateMessagesVisiblePart() {
        ChatListRecyclerView r1 = this.chatListView;
        if (r1 != null) goto L5;
        return;
    L5:
        int r12 = r1.getChildCount();
        int r2 = this.chatListView.getMeasuredHeight();
        int r3 = Integer.MAX_VALUE;
        boolean r5 = false;
        int r6 = Integer.MAX_VALUE;
        int r7 = 0;
        boolean r8 = false;
        View r9 = null;
        View r10 = null;
        View r11 = null;
    L7:
        if (r7 >= r12) goto L59;
        View r14 = this.chatListView.getChildAt(r7);
        if ((r14 instanceof ChatMessageUnsupportedCell) == false) goto L12;
        ((ChatMessageUnsupportedCell) r14).setVisiblePart((r14.getY() + this.actionBar.getMeasuredHeight()) - this.contentView.getBackgroundTranslationY(), this.contentView.getBackgroundSizeY());
    L33:
        if (r14.getBottom() <= this.chatListView.getPaddingTop()) goto L58;
        int r4 = r14.getBottom();
        if (r4 < r3) goto L38;
    L43:
        ChatListItemAnimator r13 = this.chatListItemAnimator;
        if (r13 == null) goto L50;
        if (r13.willRemoved(r14) == true) goto L58;
        if (this.chatListItemAnimator.willAddedFromAlpha(r14) == true) goto L58;
    L50:
        if ((r14 instanceof ChatActionCell) == false) goto L58;
        if (((ChatActionCell) r14).getMessageObject().isDateObject == false) goto L58;
        if (r14.getAlpha() == 1.0f) goto L56;
        r14.setAlpha(1.0f);
    L56:
        if (r4 >= r6) goto L58;
        r6 = r4;
        r10 = r14;
        goto L58
    L38:
        if ((r14 instanceof ChatMessageCell) == false) goto L40;
    L41:
        r9 = r14;
    L42:
        r3 = r4;
        r11 = r14;
        goto L43
    L40:
        if ((r14 instanceof ChatActionCell) == false) goto L42;
    L58:
        r7 = r7 + 1;
        goto L7
    L12:
        if ((r14 instanceof ChatMessageCell) == false) goto L28;
        ChatMessageCell r16 = (ChatMessageCell) r14;
        int r42 = r16.getTop();
        r16.getBottom();
        if (r42 < 0) goto L16;
        int r17 = 0;
    L17:
        int r15 = r16.getMeasuredHeight();
        if (r15 <= r2) goto L20;
        r15 = r17 + r2;
    L20:
        r16.setVisiblePart(r17, r15 - r17, (this.contentView.getHeightWithKeyboard() - AndroidUtilities.dp(48.0f)) - this.chatListView.getTop(), 0.0f, (r14.getY() + this.actionBar.getMeasuredHeight()) - this.contentView.getBackgroundTranslationY(), this.contentView.getMeasuredWidth(), this.contentView.getBackgroundSizeY(), 0, 0, 0);
        MessageObject r132 = r16.getMessageObject();
        if (this.roundVideoContainer == null) goto L33;
        if (r132.isRoundVideo() == false) goto L33;
        if (MediaController.getInstance().isPlayingMessage(r132) == false) goto L33;
        ImageReceiver r82 = r16.getPhotoImage();
        this.roundVideoContainer.setTranslationX(r82.getImageX());
        this.roundVideoContainer.setTranslationY((this.fragmentView.getPaddingTop() + r42) + r82.getImageY());
        this.fragmentView.invalidate();
        this.roundVideoContainer.invalidate();
        r8 = true;
        goto L33
    L16:
        r17 = -r42;
        goto L17
    L28:
        if ((r14 instanceof ChatActionCell) == false) goto L33;
        ChatActionCell r43 = (ChatActionCell) r14;
        r43.setVisiblePart((r14.getY() + this.actionBar.getMeasuredHeight()) - this.contentView.getBackgroundTranslationY(), this.contentView.getBackgroundSizeY());
        if (r43.hasGradientService() == false) goto L33;
        r43.invalidate();
        goto L33
    L59:
        FrameLayout r18 = this.roundVideoContainer;
        if (r18 == null) goto L70;
        if (r8 == true) goto L69;
        r18.setTranslationY((-AndroidUtilities.roundMessageSize) - 100);
        this.fragmentView.invalidate();
        MessageObject r19 = MediaController.getInstance().getPlayingMessageObject();
        if (r19 == null) goto L70;
        if (r19.isRoundVideo() == false) goto L70;
        if (this.checkTextureViewPosition == false) goto L70;
        MediaController.getInstance().setCurrentVideoVisible(false);
        goto L70
    L69:
        MediaController.getInstance().setCurrentVideoVisible(true);
    L70:
        if (r9 != null) goto L72;
    L76:
        this.currentFloatingDateOnScreen = false;
        if ((r11 instanceof ChatMessageCell) == false) goto L79;
    L81:
        this.currentFloatingTopIsNotMessage = r5;
        if (r10 != null) goto L84;
        hideFloatingDateView(true);
        this.floatingDateView.setTranslationY(0.0f);
        return;
    L84:
        if (r10.getTop() > this.chatListView.getPaddingTop()) goto L102;
        if (this.currentFloatingTopIsNotMessage == true) goto L102;
        if (r10.getAlpha() == 0.0f) goto L91;
        r10.setAlpha(0.0f);
    L91:
        AnimatorSet r22 = this.floatingDateAnimation;
        if (r22 == null) goto L95;
        r22.cancel();
        this.floatingDateAnimation = null;
    L95:
        if (this.floatingDateView.getTag() != null) goto L98;
        this.floatingDateView.setTag(1);
    L98:
        if (this.floatingDateView.getAlpha() == 1.0f) goto L100;
        this.floatingDateView.setAlpha(1.0f);
    L100:
        this.currentFloatingDateOnScreen = true;
    L105:
        int r23 = r10.getBottom() - this.chatListView.getPaddingTop();
        if (r23 > this.floatingDateView.getMeasuredHeight()) goto L108;
    L111:
        this.floatingDateView.setTranslationY(0.0f);
        return;
    L108:
        if (r23 >= (this.floatingDateView.getMeasuredHeight() * 2)) goto L111;
        this.floatingDateView.setTranslationY(((-r0.getMeasuredHeight()) * 2) + r23);
        return;
    L102:
        if (r10.getAlpha() == 1.0f) goto L104;
        r10.setAlpha(1.0f);
    L104:
        hideFloatingDateView(true ^ this.currentFloatingTopIsNotMessage);
        goto L105
    L79:
        if ((r11 instanceof ChatActionCell) == true) goto L81;
        r5 = true;
        goto L81
    L72:
        if ((r9 instanceof ChatMessageCell) == false) goto L74;
        MessageObject r110 = ((ChatMessageCell) r9).getMessageObject();
    L75:
        this.floatingDateView.setCustomDate(r110.messageOwner.date, false, true);
        goto L76
    L74:
        r110 = ((ChatActionCell) r9).getMessageObject();
        goto L75
    }

    private void updateTextureViewPosition() {
        int r0 = this.chatListView.getChildCount();
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L15;
        View r4 = this.chatListView.getChildAt(r2);
        if ((r4 instanceof ChatMessageCell) == false) goto L14;
        ChatMessageCell r42 = (ChatMessageCell) r4;
        MessageObject r5 = r42.getMessageObject();
        if (this.roundVideoContainer == null) goto L14;
        if (r5.isRoundVideo() == false) goto L14;
        if (MediaController.getInstance().isPlayingMessage(r5) == false) goto L14;
        ImageReceiver r02 = r42.getPhotoImage();
        this.roundVideoContainer.setTranslationX(r02.getImageX());
        this.roundVideoContainer.setTranslationY((this.fragmentView.getPaddingTop() + r42.getTop()) + r02.getImageY());
        this.fragmentView.invalidate();
        this.roundVideoContainer.invalidate();
        boolean r03 = true;
    L17:
        if (this.roundVideoContainer == null) goto L38;
        MessageObject r22 = MediaController.getInstance().getPlayingMessageObject();
        if (r03 == true) goto L30;
        this.roundVideoContainer.setTranslationY((-AndroidUtilities.roundMessageSize) - 100);
        this.fragmentView.invalidate();
        if (r22 != null) goto L23;
        return;
    L23:
        if (r22.isRoundVideo() == true) goto L25;
        return;
    L25:
        if (this.checkTextureViewPosition == false) goto L27;
    L28:
        MediaController.getInstance().setCurrentVideoVisible(false);
        return;
    L27:
        if (PipRoundVideoView.getInstance() != null) goto L28;
        return;
    L30:
        MediaController.getInstance().setCurrentVideoVisible(true);
        return;
    L38:
        return;
    L14:
        r2 = r2 + 1;
        goto L4
    L15:
        r03 = false;
        goto L17
    }

    private void updateVisibleRows() {
        updateVisibleRows(false);
    }

    private void updateVisibleRows(boolean r10) {
        ChatListRecyclerView r0 = this.chatListView;
        if (r0 != null) goto L5;
        return;
    L5:
        int r02 = r0.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L55;
        View r3 = this.chatListView.getChildAt(r2);
        boolean r5 = true;
        if ((r3 instanceof ChatMessageCell) == false) goto L45;
        ChatMessageCell r32 = (ChatMessageCell) r3;
        MessageObject r4 = r32.getMessageObject();
        if (r4 == null) goto L54;
        if (this.actionBar.isActionModeShowed() == false) goto L15;
        this.highlightMessageQuoteFirst = false;
        this.highlightMessageQuote = null;
    L17:
        if (this.highlightMessageId != Integer.MAX_VALUE) goto L19;
    L21:
        boolean r42 = false;
    L22:
        r32.setHighlighted(r42);
        if (this.highlightMessageId == Integer.MAX_VALUE) goto L26;
        startMessageUnselect();
    L26:
        if (r32.isHighlighted() == false) goto L36;
        String r43 = this.highlightMessageQuote;
        if (r43 == null) goto L36;
        if (r32.setHighlightedText(r43, true, this.highlightMessageQuoteOffset, this.highlightMessageQuoteFirst) == false) goto L32;
    L34:
        this.highlightMessageQuoteFirst = false;
        this.showNoQuoteAlert = false;
    L40:
        if (this.chatListView.getScrollState() != 0) goto L43;
        r5 = false;
    L43:
        r32.setSpoilersSuppressed(r5);
        goto L54
    L32:
        if (this.showNoQuoteAlert == false) goto L34;
        showNoQuoteFound();
    L36:
        if (TextUtils.isEmpty(this.searchQuery) == true) goto L38;
        r32.setHighlightedText(this.searchQuery);
        goto L40
    L38:
        r32.setHighlightedText(null);
        goto L40
    L19:
        if (r4.getRealId() != this.highlightMessageId) goto L21;
        r42 = true;
        goto L22
    L15:
        r32.setDrawSelectionBackground(false);
        r32.setCheckBoxVisible(false, true);
        r32.setChecked(false, false, true);
    L54:
        r2 = r2 + 1;
        goto L6
    L45:
        if ((r3 instanceof ChatActionCell) == false) goto L54;
        ChatActionCell r33 = (ChatActionCell) r3;
        if (r10 == true) goto L50;
        r33.setMessageObject(r33.getMessageObject());
    L50:
        if (this.chatListView.getScrollState() != 0) goto L53;
        r5 = false;
    L53:
        r33.setSpoilersSuppressed(r5);
        goto L54
    }

    public void applyScrolledPosition() {
        if (this.chatListView != null) goto L5;
        return;
    L5:
        if (this.chatLayoutManager == null) goto L26;
        int r0 = this.savedScrollPosition;
        if (r0 >= 0) goto L9;
        return;
    L9:
        if (this.savedScrollEventId == 0) goto L19;
        int r1 = 0;
    L12:
        if (r1 >= this.chatAdapter.getItemCount()) goto L19;
        MessageObject r2 = this.chatAdapter.getMessageObject(r1);
        if (r2 == null) goto L18;
        if (r2.eventId != this.savedScrollEventId) goto L18;
        r0 = r1;
    L18:
        r1 = r1 + 1;
    L19:
        this.chatLayoutManager.scrollToPositionWithOffset(r0, this.savedScrollOffset, true);
        this.savedScrollPosition = -1;
        this.savedScrollEventId = 0;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r22) {
        if (this.chatMessageCellsCache.isEmpty() == false) goto L7;
        int r2 = 0;
    L5:
        if (r2 >= 8) goto L7;
        this.chatMessageCellsCache.add(new ChatMessageCell(r22, this.currentAccount));
        r2 = r2 + 1;
    L7:
        this.searchWas = false;
        this.hasOwnBackground = true;
        Theme.createChatResources(r22, false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setCastShadows(false);
        this.actionBar.setBackground(null);
        this.actionBar.setOccupyStatusBar(!AndroidUtilities.isTablet());
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass3(this));
        ChatAvatarContainer r5 = new ChatAvatarContainer(r22, null, false);
        this.avatarContainer = r5;
        ChatHeaderUiHelper.setupGlassAvatarContainer(r5);
        this.avatarContainer.setOccupyStatusBar(!AndroidUtilities.isTablet());
        this.actionBar.addView(this.avatarContainer, 0, LayoutHelper.createFrame(-2, -1.0f, 51, ChatHeaderUiHelper.getAvatarContainerLeftMargin(false), 0.0f, 52.0f, 0.0f));
        ActionBarMenuItem r52 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass4(this));
        this.searchItem = r52;
        r52.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.searchItem.setSearchPaddingStart(7);
        this.avatarContainer.setEnabled(false);
        this.avatarContainer.setTitle(this.currentChat.title);
        this.avatarContainer.setSubtitle(LocaleController.getString(R.string.EventLogAllEvents));
        this.avatarContainer.setChatAvatar(this.currentChat);
        AnonymousClass5 r53 = new AnonymousClass5(this, r22);
        this.fragmentView = r53;
        AnonymousClass5 r54 = r53;
        this.contentView = r54;
        r54.setOccupyStatusBar(!AndroidUtilities.isTablet());
        this.contentView.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        this.actionBar.setupGlass(this.glassBackgroundDrawableFactory, BlurredBackgroundProviderImpl.topPanelChatActivity(this.resourceProvider), ChatObject.isForum(this.currentChat));
        ChatHeaderUiHelper.applyChatHeaderGlassStyle(this.actionBar);
        FrameLayout r55 = new FrameLayout(r22);
        this.emptyViewContainer = r55;
        r55.setVisibility(4);
        this.contentView.addView(this.emptyViewContainer, LayoutHelper.createFrame(-1, -2, 17));
        this.emptyViewContainer.setOnTouchListener(new ChannelAdminLogActivity$$ExternalSyntheticLambda0());
        LinearLayout r56 = new LinearLayout(r22);
        this.emptyLayoutView = r56;
        r56.setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(12.0f), this.emptyView, this.contentView));
        this.emptyLayoutView.setOrientation(1);
        ImageView r57 = new ImageView(r22);
        this.emptyImageView = r57;
        ImageView.ScaleType r8 = ImageView.ScaleType.CENTER;
        r57.setScaleType(r8);
        this.emptyImageView.setImageResource(R.drawable.large_log_actions);
        ImageView r58 = this.emptyImageView;
        if (Theme.isCurrentThemeMonet() == false) goto L10;
        int r13 = Theme.getColor(Theme.key_chat_serviceText);
    L11:
        r58.setColorFilter(new PorterDuffColorFilter(r13, PorterDuff.Mode.SRC_IN));
        this.emptyImageView.setVisibility(8);
        this.emptyLayoutView.addView(this.emptyImageView, LayoutHelper.createLinear(54, 54, 17, 16, 20, 16, -4));
        AnonymousClass6 r3 = new AnonymousClass6(this, r22);
        this.emptyView = r3;
        r3.setTextSize(1, 14.0f);
        this.emptyView.setGravity(17);
        TextView r32 = this.emptyView;
        int r59 = Theme.key_chat_serviceText;
        r32.setTextColor(Theme.getColor(r59));
        this.emptyView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(5.0f));
        this.emptyLayoutView.addView(this.emptyView, LayoutHelper.createLinear(-2, -2, 17, 0, 0, 0, 0));
        this.emptyViewContainer.addView(this.emptyLayoutView, LayoutHelper.createFrame(-2, -2.0f, 17, 20.0f, 0.0f, 20.0f, 0.0f));
        AnonymousClass7 r33 = new AnonymousClass7(this, r22);
        this.chatListView = r33;
        r33.setOnItemClickListener(new AnonymousClass8(this));
        this.chatListView.setTag(1);
        this.chatListView.setVerticalScrollBarEnabled(true);
        ChatListRecyclerView r34 = this.chatListView;
        ChatActivityAdapter r12 = new ChatActivityAdapter(this, r22);
        this.chatAdapter = r12;
        r34.setAdapter(r12);
        this.chatListView.setClipToPadding(false);
        this.chatListView.setPadding(0, ((this.recommendedAdditionalSizeY + AndroidUtilities.statusBarHeight) + ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(4.0f), 0, (this.recommendedAdditionalSizeY + AndroidUtilities.dp(60.0f)) + AndroidUtilities.navigationBarHeight);
        ChatListRecyclerView r35 = this.chatListView;
        AnonymousClass9 r122 = new AnonymousClass9(this, null, this.chatListView, this.resourceProvider);
        this.chatListItemAnimator = r122;
        r35.setItemAnimator(r122);
        this.chatListItemAnimator.setReversePositions(true);
        this.chatListView.setLayoutAnimation(null);
        AnonymousClass10 r36 = new AnonymousClass10(this, r22);
        this.chatLayoutManager = r36;
        r36.setOrientation(1);
        this.chatLayoutManager.setStackFromEnd(true);
        this.chatListView.setLayoutManager(this.chatLayoutManager);
        RecyclerAnimationScrollHelper r37 = new RecyclerAnimationScrollHelper(this.chatListView, this.chatLayoutManager);
        this.chatScrollHelper = r37;
        r37.setScrollListener(new ChannelAdminLogActivity$$ExternalSyntheticLambda1(this));
        this.chatScrollHelper.setAnimationCallback(this.chatScrollHelperCallback);
        this.contentView.addView(this.chatListView, LayoutHelper.createFrame(-1, -1.0f));
        this.chatListView.setOnScrollListener(new AnonymousClass11(this));
        this.glassEngine.addScrolledView(this.chatListView);
        int r38 = this.scrollToPositionOnRecreate;
        if (r38 == (-1)) goto L14;
        this.chatLayoutManager.scrollToPositionWithOffset(r38, this.scrollToOffsetOnRecreate);
        this.scrollToPositionOnRecreate = -1;
    L14:
        ChatActivityFadeView r39 = new ChatActivityFadeView(r22);
        this.chatActivityFadeView = r39;
        r39.setup(this.navbarContentDrawableFactory);
        ChatHeaderUiHelper.setupChatTopFade(this.chatActivityFadeView, this.actionBar, getThemedColor(Theme.key_windowBackgroundGray), (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(2.0f));
        this.chatActivityFadeView.setFadeZoneBottom(((AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(9.0f)) + AndroidUtilities.dp(44.0f)) + AndroidUtilities.dp(7.0f));
        this.chatActivityFadeView.setFadeHeightBottom(AndroidUtilities.dp(60.0f));
        this.contentView.addView(this.chatActivityFadeView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r310 = new FrameLayout(r22);
        this.progressView = r310;
        r310.setVisibility(4);
        this.contentView.addView(this.progressView, LayoutHelper.createFrame(-1, -1, 51));
        View r311 = new View(r22);
        this.progressView2 = r311;
        r311.setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(18.0f), this.progressView2, this.contentView));
        this.progressView.addView(this.progressView2, LayoutHelper.createFrame(36, 36, 17));
        RadialProgressView r312 = new RadialProgressView(r22);
        this.progressBar = r312;
        r312.setStyle(2);
        this.progressBar.setSize(AndroidUtilities.dp(28.0f));
        this.progressBar.setProgressColor(Theme.getColor(r59));
        RadialProgressView r313 = this.progressBar;
        int r14 = Theme.getColor(Theme.key_chat_serviceBackground);
        int r510 = Theme.getColor(r59);
        if (Theme.isCurrentThemeDark() == false) goto L17;
        float r15 = 0.65f;
    L18:
        r313.setTrackColor(Theme.blendOver(r14, Theme.multAlpha(r510, r15)));
        this.progressView.addView(this.progressBar, LayoutHelper.createFrame(32, 32, 17));
        ChatActionCell r314 = new ChatActionCell(r22);
        this.floatingDateView = r314;
        r314.setAlpha(0.0f);
        this.floatingDateView.setImportantForAccessibility(2);
        this.contentView.addView(this.floatingDateView, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 4.0f, 0.0f, 0.0f));
        this.contentView.addView(this.actionBar);
        Theme.ResourcesProvider r511 = this.resourceProvider;
        ChatActivityChannelButtonsLayout r315 = new ChatActivityChannelButtonsLayout(r22, r511, BlurredBackgroundProviderImpl.bottomPanelChatActivity(r511), this.glassBackgroundDrawableFactory);
        this.bottomOverlayChat2 = r315;
        r315.setTotalVisibilityFactor(1.0f);
        this.bottomOverlayChat2.setTranslationY(-AndroidUtilities.navigationBarHeight);
        this.bottomOverlayChat2.showButton(4, true, false);
        this.bottomOverlayChat2.setupDrawableForContainer();
        this.contentView.addView(this.bottomOverlayChat2, LayoutHelper.createFrame(-1, 56.0f, 80, 54.0f, 0.0f, 0.0f, 3.0f));
        TextView r316 = new TextView(r22);
        this.bottomOverlayChatText = r316;
        r316.setOnClickListener(new ChannelAdminLogActivity$$ExternalSyntheticLambda2(this));
        this.bottomOverlayChatText.setTextSize(1, 15.0f);
        this.bottomOverlayChatText.setTypeface(AndroidUtilities.bold());
        this.bottomOverlayChatText.setTextColor(Theme.getColor(Theme.key_chat_fieldOverlayText));
        this.bottomOverlayChatText.setText(LocaleController.getString(R.string.SETTINGS));
        this.bottomOverlayChatText.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
        this.bottomOverlayChat2.getContainer().addView(this.bottomOverlayChatText, LayoutHelper.createFrame(-2, -2, 17));
        this.bottomOverlayChat2.makeViewWrapContent(this.bottomOverlayChatText);
        this.bottomOverlayChat2.updateWrappingVisible(false);
        this.bottomOverlayChat2.setButtonOnClickListener(4, new ChannelAdminLogActivity$$ExternalSyntheticLambda3(this));
        FrameLayout r317 = new FrameLayout(r22);
        this.searchContainer = r317;
        r317.setWillNotDraw(false);
        this.searchContainer.setVisibility(4);
        this.searchContainer.setFocusable(true);
        this.searchContainer.setFocusableInTouchMode(true);
        this.searchContainer.setClickable(true);
        this.searchContainer.setPadding(0, AndroidUtilities.dp(3.0f), 0, 0);
        this.contentView.addView(this.searchContainer, LayoutHelper.createFrame(-1, 51, 80));
        ImageView r318 = new ImageView(r22);
        this.searchCalendarButton = r318;
        r318.setScaleType(r8);
        this.searchCalendarButton.setImageResource(R.drawable.msg_calendar);
        this.searchCalendarButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_searchPanelIcons), PorterDuff.Mode.MULTIPLY));
        this.searchContainer.addView(this.searchCalendarButton, LayoutHelper.createFrame(48, 48, 53));
        this.searchCalendarButton.setOnClickListener(new ChannelAdminLogActivity$$ExternalSyntheticLambda4(this));
        SimpleTextView r319 = new SimpleTextView(r22);
        this.searchCountText = r319;
        r319.setTextColor(Theme.getColor(Theme.key_chat_searchPanelText));
        this.searchCountText.setTextSize(15);
        this.searchCountText.setTypeface(AndroidUtilities.bold());
        this.searchContainer.addView(this.searchCountText, LayoutHelper.createFrame(-1, -2.0f, 19, 108.0f, 0.0f, 0.0f, 0.0f));
        this.chatAdapter.updateRows();
        if (this.loading == true) goto L21;
    L23:
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, false, 0.3f, true);
        this.chatListView.setEmptyView(this.emptyViewContainer);
    L24:
        this.chatListView.setAnimateEmptyView(true, 1);
        UndoView r23 = new UndoView(r22);
        this.undoView = r23;
        r23.setAdditionalTranslationY(AndroidUtilities.dp(51.0f));
        this.contentView.addView(this.undoView, LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        updateEmptyPlaceholder();
        return this.fragmentView;
    L21:
        if (this.messages.isEmpty() == false) goto L23;
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, true, 0.3f, true);
        this.chatListView.setEmptyView(null);
        goto L24
    L17:
        r15 = 0.8f;
        goto L18
    L10:
        r13 = -1;
        goto L11
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        if (r7 != NotificationCenter.emojiLoaded) goto L8;
        ChatListRecyclerView r6 = this.chatListView;
        if (r6 == null) goto L86;
        r6.invalidateViews();
        return;
    L86:
        return;
    L8:
        int r2 = 0;
        if (r7 != NotificationCenter.messagePlayingDidStart) goto L36;
        if (((MessageObject) r9[0]).isRoundVideo() == false) goto L13;
        MediaController.getInstance().setTextureView(createTextureView(true), this.aspectRatioFrameLayout, this.roundVideoContainer, true);
        updateTextureViewPosition();
    L13:
        ChatListRecyclerView r72 = this.chatListView;
        if (r72 == null) goto L107;
        int r73 = r72.getChildCount();
        int r82 = 0;
    L16:
        if (r82 >= r73) goto L113;
        View r92 = this.chatListView.getChildAt(r82);
        if ((r92 instanceof ChatMessageCell) == false) goto L34;
        ChatMessageCell r93 = (ChatMessageCell) r92;
        MessageObject r3 = r93.getMessageObject();
        if (r3 == null) goto L34;
        if (r3.isVoice() == false) goto L24;
    L33:
        r93.updateButtonState(false, true, false);
        goto L34
    L24:
        if (r3.isMusic() == true) goto L33;
        if (r3.isRoundVideo() == false) goto L34;
        r93.checkVideoPlayback(false, null);
        if (MediaController.getInstance().isPlayingMessage(r3) == true) goto L34;
        if (r3.audioProgress == 0.0f) goto L34;
        r3.resetPlayingProgress();
        r93.invalidate();
    L34:
        r82 = r82 + 1;
        goto L16
    L113:
        return;
    L107:
        return;
    L36:
        if (r7 != NotificationCenter.messagePlayingDidReset) goto L38;
    L66:
        ChatListRecyclerView r74 = this.chatListView;
        if (r74 == null) goto L112;
        int r75 = r74.getChildCount();
        int r83 = 0;
    L69:
        if (r83 >= r75) goto L115;
        View r94 = this.chatListView.getChildAt(r83);
        if ((r94 instanceof ChatMessageCell) == false) goto L85;
        ChatMessageCell r95 = (ChatMessageCell) r94;
        MessageObject r32 = r95.getMessageObject();
        if (r32 == null) goto L85;
        if (r32.isVoice() == false) goto L77;
    L84:
        r95.updateButtonState(false, true, false);
        goto L85
    L77:
        if (r32.isMusic() == true) goto L84;
        if (r32.isRoundVideo() == false) goto L85;
        if (MediaController.getInstance().isPlayingMessage(r32) == true) goto L85;
        r95.checkVideoPlayback(true, null);
    L85:
        r83 = r83 + 1;
        goto L69
    L115:
        return;
    L112:
        return;
    L38:
        if (r7 == NotificationCenter.messagePlayingPlayStateChanged) goto L66;
        if (r7 != NotificationCenter.messagePlayingProgressDidChanged) goto L58;
        Integer r76 = (Integer) r9[0];
        ChatListRecyclerView r84 = this.chatListView;
        if (r84 == null) goto L108;
        int r85 = r84.getChildCount();
    L45:
        if (r2 >= r85) goto L114;
        View r96 = this.chatListView.getChildAt(r2);
        if ((r96 instanceof ChatMessageCell) == false) goto L56;
        ChatMessageCell r97 = (ChatMessageCell) r96;
        MessageObject r0 = r97.getMessageObject();
        if (r0 == null) goto L56;
        if (r0.getId() != r76.intValue()) goto L56;
        MessageObject r62 = MediaController.getInstance().getPlayingMessageObject();
        if (r62 == null) goto L109;
        r0.audioProgress = r62.audioProgress;
        r0.audioProgressSec = r62.audioProgressSec;
        r0.audioPlayerDuration = r62.audioPlayerDuration;
        r97.updatePlayingMessageProgress();
        return;
    L109:
        return;
    L56:
        r2 = r2 + 1;
        goto L45
    L114:
        return;
    L108:
        return;
    L58:
        if (r7 == NotificationCenter.didSetNewWallpapper) goto L60;
        return;
    L60:
        if (this.fragmentView == null) goto L111;
        this.contentView.setBackgroundImage(Theme.getCachedWallpaper(), Theme.isWallpaperMotion());
        this.progressView2.invalidate();
        TextView r77 = this.emptyView;
        if (r77 == null) goto L64;
        r77.invalidate();
    L64:
        this.chatListView.invalidateViews();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_chat_messagePanelBackground);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.fragmentView, 0, null, null, null, null, Theme.key_chat_wallpaper));
        ActionBar r4 = this.actionBar;
        int r5 = ThemeDescription.FLAG_BACKGROUND;
        int r13 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r13));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r13));
        ActionBar r15 = this.actionBar;
        int r16 = ThemeDescription.FLAG_AB_ITEMSCOLOR;
        int r21 = Theme.key_actionBarDefaultIcon;
        r1.add(new ThemeDescription(r15, r16, null, null, null, null, r21));
        ActionBar r3 = this.actionBar;
        int r42 = ThemeDescription.FLAG_AB_SELECTORCOLOR;
        int r9 = Theme.key_actionBarDefaultSelector;
        r1.add(new ThemeDescription(r3, r42, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, null, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, null, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r13));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r13));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, r21));
        r1.add(new ThemeDescription(this.avatarContainer.getTitleTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.avatarContainer.getSubtitleTextView(), ThemeDescription.FLAG_TEXTCOLOR, null, new Paint[]{Theme.chat_statusPaint, Theme.chat_statusRecordPaint}, null, null, Theme.key_actionBarDefaultSubtitle, null));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, r9));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageRed));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageOrange));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageViolet));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageGreen));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageCyan));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessageBlue));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_avatar_nameInMessagePink));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInDrawable, Theme.chat_msgInMediaDrawable}, null, Theme.key_chat_inBubble));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInSelectedDrawable, Theme.chat_msgInMediaSelectedDrawable}, null, Theme.key_chat_inBubbleSelected));
        Drawable[] r8 = Theme.chat_msgInDrawable.getShadowDrawables();
        int r212 = Theme.key_chat_inBubbleShadow;
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, r8, null, r212));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgInMediaDrawable.getShadowDrawables(), null, r212));
        Drawable[] r82 = Theme.chat_msgOutDrawable.getShadowDrawables();
        int r213 = Theme.key_chat_outBubbleShadow;
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, r82, null, r213));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgOutMediaDrawable.getShadowDrawables(), null, r213));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubble));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient1));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient2));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutDrawable, Theme.chat_msgOutMediaDrawable}, null, Theme.key_chat_outBubbleGradient3));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutSelectedDrawable, Theme.chat_msgOutMediaSelectedDrawable}, null, Theme.key_chat_outBubbleSelected));
        com.exteragram.messenger.utils.ui.TextPaint r83 = Theme.chat_actionTextPaint;
        int r214 = Theme.key_chat_serviceText;
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatActionCell.class}, r83, null, null, r214));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{ChatActionCell.class}, Theme.chat_actionTextPaint, null, null, Theme.key_chat_serviceLink));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_botCardDrawable, Theme.chat_shareIconDrawable, Theme.chat_botInlineDrawable, Theme.chat_botLinkDrawable, Theme.chat_goIconDrawable, Theme.chat_commentStickerDrawable}, null, Theme.key_chat_serviceIcon));
        int r33 = Theme.key_chat_serviceBackground;
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class, ChatActionCell.class}, null, null, null, r33));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class, ChatActionCell.class}, null, null, null, Theme.key_chat_serviceBackgroundSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageTextIn));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageTextOut));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageLinkIn, null));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_LINKCOLOR, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_messageLinkOut, null));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckDrawable}, null, Theme.key_chat_outSentCheck));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckReadDrawable, Theme.chat_msgOutHalfCheckDrawable}, null, Theme.key_chat_outSentCheckRead));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutCheckReadSelectedDrawable, Theme.chat_msgOutHalfCheckSelectedDrawable}, null, Theme.key_chat_outSentCheckReadSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaCheckDrawable, Theme.chat_msgMediaHalfCheckDrawable}, null, Theme.key_chat_mediaSentCheck));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutViewsDrawable, Theme.chat_msgOutRepliesDrawable, Theme.chat_msgOutPinnedDrawable}, null, Theme.key_chat_outViews));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutViewsSelectedDrawable, Theme.chat_msgOutRepliesSelectedDrawable, Theme.chat_msgOutPinnedSelectedDrawable}, null, Theme.key_chat_outViewsSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInViewsDrawable, Theme.chat_msgInRepliesDrawable, Theme.chat_msgInPinnedDrawable}, null, Theme.key_chat_inViews));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInViewsSelectedDrawable, Theme.chat_msgInRepliesSelectedDrawable, Theme.chat_msgInPinnedSelectedDrawable}, null, Theme.key_chat_inViewsSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaViewsDrawable, Theme.chat_msgMediaRepliesDrawable, Theme.chat_msgMediaPinnedDrawable}, null, Theme.key_chat_mediaViews));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutMenuDrawable}, null, Theme.key_chat_outMenu));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgOutMenuSelectedDrawable}, null, Theme.key_chat_outMenuSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInMenuDrawable}, null, Theme.key_chat_inMenu));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgInMenuSelectedDrawable}, null, Theme.key_chat_inMenuSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgMediaMenuDrawable}, null, Theme.key_chat_mediaMenu));
        Drawable[] r27 = {Theme.chat_msgOutInstantDrawable};
        int r11 = Theme.key_chat_outInstant;
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, r27, null, r11));
        Drawable[] r272 = {Theme.chat_msgInInstantDrawable, Theme.chat_commentDrawable, Theme.chat_commentArrowDrawable};
        int r41 = Theme.key_chat_inInstant;
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, r272, null, r41));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgOutCallDrawable, null, r11));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgOutCallSelectedDrawable, null, Theme.key_chat_outInstantSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgInCallDrawable, null, r41));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, Theme.chat_msgInCallSelectedDrawable, null, Theme.key_chat_inInstantSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgCallUpGreenDrawable}, null, Theme.key_chat_outGreenCall));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgCallDownRedDrawable}, null, Theme.key_fill_RedNormal));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgCallDownGreenDrawable}, null, Theme.key_chat_inGreenCall));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_msgErrorPaint, null, null, Theme.key_chat_sentError));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_msgErrorDrawable}, null, Theme.key_chat_sentErrorIcon));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_durationPaint, null, null, Theme.key_chat_previewDurationText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_gamePaint, null, null, Theme.key_chat_previewGameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inPreviewInstantText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outPreviewInstantText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_deleteProgressPaint, null, null, Theme.key_chat_secretTimeText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_botButtonPaint, null, null, Theme.key_chat_botButtonText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inForwardedNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outForwardedNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inViaBotNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outViaBotNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerViaBotNameText));
        int r112 = Theme.key_chat_inReplyLine;
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, r112));
        int r412 = Theme.key_chat_outReplyLine;
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, r412));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyLine2));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerReplyLine));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerReplyNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMessageText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMessageText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMediaMessageText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMediaMessageText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outReplyMediaMessageSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_stickerReplyMessageText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inPreviewLine));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outPreviewLine));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inSiteNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outSiteNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inContactNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outContactNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inContactPhoneText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outContactPhoneText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_mediaProgress));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioProgress));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioProgress));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSelectedProgress));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSelectedProgress));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_mediaTimeText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inTimeText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outTimeText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inTimeSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outTimeSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioPerformerText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioPerformerText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioTitleText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioTitleText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioDurationText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioDurationText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioDurationSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioDurationSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSeekbar));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSeekbar));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSeekbarSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSeekbarSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioSeekbarFill));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inAudioCacheSeekbar));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioSeekbarFill));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outAudioCacheSeekbar));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVoiceSeekbar));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVoiceSeekbar));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVoiceSeekbarSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVoiceSeekbarSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVoiceSeekbarFill));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVoiceSeekbarFill));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileProgress));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileProgress));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileProgressSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileProgressSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileNameText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileInfoText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileInfoText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileInfoSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileInfoSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileBackground));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileBackground));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inFileBackgroundSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outFileBackgroundSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVenueInfoText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVenueInfoText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inVenueInfoSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outVenueInfoSelectedText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_mediaInfoText));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_urlPaint, null, null, r112));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, Theme.chat_textSearchSelectionPaint, null, null, r412));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outLoader));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outMediaIcon));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outLoaderSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_outMediaIconSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inLoader));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inMediaIcon));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inLoaderSelected));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inMediaIconSelected));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[0]}, null, Theme.key_chat_inContactBackground));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[0]}, null, Theme.key_chat_inContactIcon));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[1]}, null, Theme.key_chat_outContactBackground));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_contactDrawable[1]}, null, Theme.key_chat_outContactIcon));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, null, null, Theme.key_chat_inLocationBackground));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_locationDrawable[0]}, null, Theme.key_chat_inLocationIcon));
        r1.add(new ThemeDescription(this.chatListView, 0, new Class[]{ChatMessageCell.class}, null, new Drawable[]{Theme.chat_locationDrawable[1]}, null, Theme.key_chat_outLocationIcon));
        r1.add(new ThemeDescription(this.bottomOverlayChatText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_chat_fieldOverlayText));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r214));
        r1.add(new ThemeDescription(this.progressBar, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, r214));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, new Class[]{ChatUnreadCell.class}, new String[]{"backgroundLayout"}, null, null, null, Theme.key_chat_unreadMessagesStartBackground));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{ChatUnreadCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_chat_unreadMessagesStartArrowIcon));
        r1.add(new ThemeDescription(this.chatListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{ChatUnreadCell.class}, new String[]{"textView"}, null, null, null, Theme.key_chat_unreadMessagesStartText));
        r1.add(new ThemeDescription(this.progressView2, ThemeDescription.FLAG_SERVICEBACKGROUND, null, null, null, null, r33));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_SERVICEBACKGROUND, null, null, null, null, r33));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_undo_background));
        int r17 = Theme.key_undo_cancelColor;
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoImageView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"undoTextView"}, null, null, null, r17));
        int r23 = Theme.key_undo_infoColor;
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"infoTextView"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"textPaint"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.undoView, 0, new Class[]{UndoView.class}, new String[]{"progressPaint"}, null, null, null, r23));
        r1.add(new ThemeDescription(this.undoView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{UndoView.class}, new String[]{"leftImageView"}, null, null, null, r23));
        return r1;
    }

    public boolean isKeyboardVisible() {
        if (this.contentView.getKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        UndoView r2 = this.undoView;
        if (r2 == null) goto L6;
        r2.hide(true, 0);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r1) {
        Dialog r0 = this.visibleDialog;
        if ((r0 instanceof DatePickerDialog) == false) goto L6;
        r0.dismiss();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewWallpapper);
        loadMessages(true);
        loadAdmins();
        Bulletin.addDelegate(this, new AnonymousClass2(this));
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.messagePlayingProgressDidChanged);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewWallpapper);
        this.notificationsLocker.unlock();
        Bulletin.removeDelegate(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        ChatActivityFragmentView r0 = this.contentView;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        UndoView r02 = this.undoView;
        if (r02 == null) goto L8;
        r02.hide(true, 0);
    L8:
        this.paused = true;
        this.wasPaused = true;
        if (AvatarPreviewer.hasVisibleInstance() == false) goto L12;
        AvatarPreviewer.getInstance().close();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRemoveFromParent() {
        MediaController.getInstance().setTextureView(this.videoTextureView, null, null, false);
        super.onRemoveFromParent();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.activityResumeTime = System.currentTimeMillis();
        ChatActivityFragmentView r0 = this.contentView;
        if (r0 == null) goto L5;
        r0.onResume();
    L5:
        this.paused = false;
        checkScrollForLoad(false);
        if (this.wasPaused == false) goto L11;
        this.wasPaused = false;
        ChatActivityAdapter r2 = this.chatAdapter;
        if (r2 == null) goto L12;
        r2.notifyDataSetChanged();
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == false) goto L5;
        this.notificationsLocker.unlock();
        this.openAnimationEnded = true;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        if (r1 == false) goto L5;
        this.notificationsLocker.lock();
        this.openAnimationEnded = false;
        return;
    }

    public void openVCard(TLRPC.User r10, String r11, String r12, String r13) {
        File r0 = AndroidUtilities.getSharingDirectory();     // Catch: Exception -> L4
        r0.mkdirs();     // Catch: Exception -> L4
        File r6 = new File(r0, "vcard.vcf");     // Catch: Exception -> L4
        BufferedWriter r02 = new BufferedWriter(new FileWriter(r6));     // Catch: Exception -> L4
        r02.write(r11);     // Catch: Exception -> L4
        r02.close();     // Catch: Exception -> L4
        showDialog(new PhonebookShareAlert(this, null, r10, null, r6, r12, r13));     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public void reloadLastMessages() {
        if (this.reloadingLastMessages == false) goto L5;
        return;
    L5:
        this.reloadingLastMessages = true;
        TLRPC.TL_channels_getAdminLog r1 = new TLRPC.TL_channels_getAdminLog();
        r1.channel = MessagesController.getInputChannel(this.currentChat);
        r1.q = this.searchQuery;
        r1.limit = 10;
        r1.max_id = 0;
        r1.min_id = 0;
        TLRPC.TL_channelAdminLogEventsFilter r2 = this.currentFilter;
        if (r2 == null) goto L9;
        r1.flags = 1 | r1.flags;
        r1.events_filter = r2;
    L9:
        if (this.selectedAdmins == null) goto L14;
        r1.flags |= 2;
        int r0 = 0;
    L12:
        if (r0 >= this.selectedAdmins.size()) goto L14;
        r1.admins.add(MessagesController.getInstance(this.currentAccount).getInputUser(this.selectedAdmins.valueAt(r0)));
        r0 = r0 + 1;
    L14:
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new ChannelAdminLogActivity$$ExternalSyntheticLambda24(this));
    }

    public void saveScrollPosition(boolean r8) {
        ChatListRecyclerView r0 = this.chatListView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.chatLayoutManager != null) goto L7;
        return;
    L7:
        if (r0.getChildCount() <= 0) goto L40;
        if (r8 == false) goto L10;
        int r02 = Integer.MAX_VALUE;
    L11:
        View r1 = null;
        int r2 = -1;
        int r3 = 0;
    L13:
        if (r3 >= this.chatListView.getChildCount()) goto L23;
        View r4 = this.chatListView.getChildAt(r3);
        int r5 = this.chatListView.getChildAdapterPosition(r4);
        if (r5 < 0) goto L22;
        int r6 = r4.getTop();
        if (r8 == false) goto L20;
        if (r6 >= r02) goto L22;
    L21:
        r02 = r4.getTop();
        r1 = r4;
        r2 = r5;
        goto L22
    L20:
        if (r6 > r02) goto L21;
    L22:
        r3 = r3 + 1;
        goto L13
    L23:
        if (r1 != null) goto L25;
        return;
    L25:
        if ((r1 instanceof ChatMessageCell) == false) goto L28;
        long r32 = ((ChatMessageCell) r1).getMessageObject().eventId;
    L31:
        this.savedScrollEventId = r32;
        this.savedScrollPosition = r2;
        this.savedScrollOffset = getScrollingOffsetForView(r1);
        return;
    L28:
        if ((r1 instanceof ChatActionCell) == false) goto L30;
        r32 = ((ChatActionCell) r1).getMessageObject().eventId;
        goto L31
    L30:
        r32 = 0;
        goto L31
    L10:
        r02 = Integer.MIN_VALUE;
        goto L11
    }

    public void scrollToMessage(MessageObject r13, boolean r14) {
        this.wasManualScroll = true;
        if (this.filteredMessages.size() <= 0) goto L23;
        int r1 = this.chatLayoutManager.findLastVisibleItemPosition();
        int r4 = this.chatLayoutManager.findFirstVisibleItemPosition();
    L5:
        if (r4 > r1) goto L23;
        if (r4 < ChatActivityAdapter.m7248$$Nest$fgetmessagesStartRow(this.chatAdapter)) goto L22;
        if (r4 >= ChatActivityAdapter.m7247$$Nest$fgetmessagesEndRow(this.chatAdapter)) goto L22;
        MessageObject r5 = this.filteredMessages.get(r4 - ChatActivityAdapter.m7248$$Nest$fgetmessagesStartRow(this.chatAdapter));
        if (r5.contentType == 1) goto L22;
        if (r5.getRealId() == 0) goto L22;
        if (r5.isSponsored() == true) goto L22;
        int r42 = r4 - ChatActivityAdapter.m7248$$Nest$fgetmessagesStartRow(this.chatAdapter);
        if (r5.getRealId() >= r13.getRealId()) goto L20;
        int r12 = 1;
    L21:
        int r15 = r12 ^ 1;
    L24:
        this.chatScrollHelper.setScrollDirection(r15);
        if (r13 == null) goto L74;
        int r52 = this.filteredMessages.indexOf(r13);
        if (r52 == (-1)) goto L75;
        if (r42 <= 0) goto L33;
        if (r42 <= r52) goto L31;
        r15 = 0;
    L32:
        this.chatScrollHelper.setScrollDirection(r15);
        goto L33
    L31:
        r15 = 1;
    L33:
        removeSelectedMessageHighlight();
        if (r14 == false) goto L36;
        this.highlightMessageId = r13.getRealId();
    L36:
        int r142 = ChatActivityAdapter.m7248$$Nest$fgetmessagesStartRow(this.chatAdapter) + this.filteredMessages.indexOf(r13);
        updateVisibleRows();
        int r2 = this.chatListView.getChildCount();
        int r43 = 0;
        boolean r53 = false;
        int r6 = 0;
    L37:
        if (r43 >= r2) goto L62;
        View r7 = this.chatListView.getChildAt(r43);
        if ((r7 instanceof ChatMessageCell) == false) goto L46;
        MessageObject r8 = ((ChatMessageCell) r7).getMessageObject();
        if (r8 != null) goto L43;
    L53:
        if (r53 == true) goto L54;
        r43 = r43 + 1;
        goto L37
    L54:
        int r44 = r7.getTop() - (getScrollOffsetForMessage(r7.getHeight()) - r6);
        int r22 = (this.chatListView.computeVerticalScrollRange() - this.chatListView.computeVerticalScrollOffset()) - this.chatListView.computeVerticalScrollExtent();
        if (r22 >= 0) goto L57;
        r22 = 0;
    L57:
        if (r44 <= r22) goto L59;
        r44 = r22;
    L59:
        if (r44 == 0) goto L62;
        this.chatListView.smoothScrollBy(0, r44);
        this.chatListView.setOverScrollMode(2);
        goto L62
    L43:
        if (r8.getRealId() != r13.getRealId()) goto L53;
        r7.sendAccessibilityEvent(8);
        r6 = scrollOffsetForQuote(r8);
    L52:
        r53 = true;
        goto L53
    L46:
        if ((r7 instanceof ChatActionCell) == false) goto L53;
        MessageObject r82 = ((ChatActionCell) r7).getMessageObject();
        if (r82 == null) goto L53;
        if (r82.getRealId() != r13.getRealId()) goto L53;
        r7.sendAccessibilityEvent(8);
    L62:
        if (r53 == true) goto L76;
        int r23 = getScrollOffsetForMessage(r13);
        ChatScrollCallback.m7256$$Nest$fputscrollTo(this.chatScrollHelperCallback, r13);
        ChatScrollCallback.m7252$$Nest$fputlastBottom(this.chatScrollHelperCallback, false);
        ChatScrollCallback.m7253$$Nest$fputlastItemOffset(this.chatScrollHelperCallback, r23);
        this.chatScrollHelper.setScrollDirection(r15);
        RecyclerAnimationScrollHelper r132 = this.chatScrollHelper;
        ChatScrollCallback.m7255$$Nest$fputposition(this.chatScrollHelperCallback, r142);
        ChatScrollCallback.m7254$$Nest$fputoffset(this.chatScrollHelperCallback, r23);
        ChatScrollCallback.m7251$$Nest$fputbottom(this.chatScrollHelperCallback, false);
        r132.scrollToPosition(r142, r23, false, true);
        return;
    L76:
        return;
    L75:
        return;
    L74:
        return;
    L20:
        r12 = 0;
    L22:
        r4 = r4 + 1;
    L23:
        r15 = -1;
        r42 = 0;
        goto L24
    }

    public void showNoQuoteFound() {
        BulletinFactory.of(this).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.QuoteNotFound)).show(true);
    }

    public void showOpenUrlAlert(String r4, boolean r5) {
        if (Browser.isInternalUrl(r4, null) == true) goto L8;
        if (r5 == false) goto L8;
        AlertDialog.Builder r52 = new AlertDialog.Builder(getParentActivity());
        r52.setTitle(LocaleController.getString(R.string.OpenUrlTitle));
        r52.setMessage(LocaleController.formatString(R.string.OpenUrlAlert2, new Object[]{r4}));
        r52.setPositiveButton(LocaleController.getString(R.string.Open), new ChannelAdminLogActivity$$ExternalSyntheticLambda21(this, r4));
        r52.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r52.create());
        return;
    L8:
        Browser.openUrl(getParentActivity(), r4, true);
    }
}
