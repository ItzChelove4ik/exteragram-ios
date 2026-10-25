package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.utils.ui.FabUiHelper;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.TopicsController;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.DialogsAdapter;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.TopicSearchCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.BlurredRecyclerView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatActivityInterface;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.ChatNotificationsPopupWrapper;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.DialogsActivityTopPanelLayout;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.Forum.ForumBubbleDrawable;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.FragmentContextView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.InviteMembersBottomSheet;
import org.telegram.ui.Components.JoinGroupAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.PullForegroundDrawable;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerItemsEnterAnimator;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SearchDownloadsContainer;
import org.telegram.ui.Components.SearchViewPager;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.UnreadCounterTextView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.voip.VoIPHelper;
import org.telegram.ui.Delegates.ChatActivityMemberRequestsDelegate;
import org.telegram.ui.FilteredSearchView;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.RightSlidingDialogContainer;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class TopicsFragment extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, ChatActivityInterface, RightSlidingDialogContainer.BaseFragmentWithFullscreen, MainTabsActivity.TabFragmentDelegate {
    private static HashSet<Long> settingsPreloaded;
    Adapter adapter;
    private ActionBarMenuSubItem addMemberSubMenu;
    private int additionFloatingButtonOffset;
    private int additionNavigationBarHeight;
    private boolean allowSwipeDuringCurrentTouch;
    boolean animateSearchWithScale;
    boolean animatedUpdateEnabled;
    ChatAvatarContainer avatarContainer;
    private View blurredView;
    private ActionBarMenuSubItem boostGroupSubmenu;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    private int bottomButtonType;
    private UnreadCounterTextView bottomOverlayChatText;
    private FrameLayout bottomOverlayContainer;
    private RadialProgressView bottomOverlayProgress;
    private boolean bottomPannelVisible;
    boolean canShowCreateTopic;
    private boolean canShowHiddenArchive;
    private boolean canShowProgress;
    TLRPC.ChatFull chatFull;
    final long chatId;
    private ImageView closeReportSpam;
    private ActionBarMenuSubItem closeTopic;
    SizeNotifierFrameLayout contentView;
    private boolean createGroupCall;
    private ActionBarMenuSubItem createTopicSubmenu;
    private ActionBarMenuSubItem deleteChatSubmenu;
    private ActionBarMenuItem deleteItem;
    private int dialogChangeFinished;
    private int dialogInsertFinished;
    private int dialogRemoveFinished;
    DialogsActivity dialogsActivity;
    private boolean disableActionBarScrolling;
    private View emptyView;
    private EmptyViewContainer emptyViewContainer;
    HashSet<Integer> excludeTopics;
    private boolean finishDialogRightSlidingPreviewOnTransitionEnd;
    FragmentFloatingButton floatingButton;
    ArrayList<Item> forumTopics;
    private boolean forumTopicsListFrozen;
    FragmentContextView fragmentContextView;
    private FrameLayout fragmentContextViewWrapper;
    private ArrayList<Item> frozenForumTopicsList;
    FrameLayout fullscreenView;
    private View generalTopicViewMoving;
    private ChatObject.Call groupCall;
    private int hiddenCount;
    private boolean hiddenShown;
    private ActionBarMenuItem hideItem;
    private IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryLiquidGlass;
    private boolean iBlur3Invalidated;
    private final RectF iBlur3PositionActionBar;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private boolean ignoreDiffUtil;
    boolean isSlideBackTransition;
    private DefaultItemAnimator itemAnimator;
    private ItemTouchHelper itemTouchHelper;
    private TouchHelperCallback itemTouchHelperCallback;
    RecyclerItemsEnterAnimator itemsEnterAnimator;
    private boolean joinRequested;
    private boolean lastCallCheckFromServer;
    private int lastItemsCount;
    LinearLayoutManager layoutManager;
    private boolean loadingTopics;
    private boolean mainTabsHiddenByScroll;
    private ArrayList<MessagesController.DialogFilter> movingDialogFilters;
    private boolean mute;
    private ActionBarMenuItem muteItem;
    private int navigationBarHeight;
    private final AnimationNotificationsLocker notificationsLocker;
    OnTopicSelectedListener onTopicSelectedListener;
    private boolean openAnimationEnded;
    private boolean openVideoChat;
    private final boolean openedForBotShare;
    private final boolean openedForForward;
    private final boolean openedForQuote;
    private final boolean openedForReply;
    private final boolean openedForSelect;
    private ActionBarMenuItem other;
    ActionBarMenuItem otherItem;
    public DialogsActivity parentDialogsActivity;
    private ChatActivityMemberRequestsDelegate pendingRequestsDelegate;
    private ActionBarMenuItem pinItem;
    private PullForegroundDrawable pullForegroundDrawable;
    private int pullViewState;
    private ActionBarMenuSubItem readItem;
    private TopicsRecyclerView recyclerListView;
    private boolean removeFragmentOnTransitionEnd;
    private boolean reordering;
    private ActionBarMenuSubItem reportSubmenu;
    private ActionBarMenuSubItem restartTopic;
    private RecyclerAnimationScrollHelper scrollHelper;
    private boolean scrollToTop;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private float searchAnimationProgress;
    ValueAnimator searchAnimator;
    private MessagesSearchContainer searchContainer;
    private ActionBarMenuItem searchItem;
    private ViewPagerFixed.TabsView searchTabsView;
    public boolean searching;
    private NumberTextView selectedDialogsCountTextView;
    private long selectedTopicForTablet;
    HashSet<Integer> selectedTopics;
    private ActionBarMenuItem showItem;
    ValueAnimator slideBackTransitionAnimator;
    float slideFragmentProgress;
    private long startArchivePullingTime;
    ChatActivity.ThemeDelegate themeDelegate;
    private DialogsActivityTopPanelLayout topPanelLayout;
    private final TopicsController topicsController;
    StickerEmptyView topicsEmptyView;
    float transitionPadding;
    private ActionBarMenuItem unpinItem;
    private boolean updateAnimated;
    private String voiceChatHash;
    private boolean waitingForScrollFinished;

    /* renamed from: org.telegram.ui.TopicsFragment$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends SizeNotifierFrameLayout {
        private Paint actionBarPaint;
        private boolean ignoreLayout;
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass1(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
            this.actionBarPaint = new Paint();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r14) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L33:
            super.dispatchDraw(r14);
            if (this.this$0.isInPreviewMode() == false) goto L36;
            this.actionBarPaint.setColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            this.actionBarPaint.setAlpha((int) (TopicsFragment.m20956$$Nest$fgetsearchAnimationProgress(this.this$0) * 255.0f));
            Canvas r1 = r14;
            r1.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.statusBarHeight, this.actionBarPaint);
            r1.drawLine(0.0f, 0.0f, 0.0f, getHeight(), Theme.forcedDividerPaint);
        L37:
            TopicsFragment r142 = this.this$0;
            if (r142.parentDialogsActivity != null) goto L41;
            AndroidUtilities.drawNavigationBarProtection(r1, this, r142.getThemedColor(Theme.key_windowBackgroundWhite), TopicsFragment.m20948$$Nest$fgetnavigationBarHeight(this.this$0));
            return;
        L41:
            return;
        L36:
            r1 = r14;
            goto L37
        L5:
            if (TopicsFragment.m20955$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L33;
            TopicsFragment.m20980$$Nest$mblur3_InvalidateBlur(this.this$0);
            DialogsActivity r0 = this.this$0.parentDialogsActivity;
            if (r0 == null) goto L9;
            int r02 = r0.fragmentView.getMeasuredWidth();
        L10:
            DialogsActivity r12 = this.this$0.parentDialogsActivity;
            if (r12 == null) goto L13;
            int r13 = r12.fragmentView.getMeasuredHeight();
        L15:
            if (TopicsFragment.m20942$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L24;
            if (TopicsFragment.m20942$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).inRecording() == true) goto L24;
            if (TopicsFragment.m20942$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).needUpdateDisplayList(r02, r13) == false) goto L21;
        L22:
            TopicsFragment.m20955$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(TopicsFragment.m20942$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).beginRecording(r02, r13), -3);
            TopicsFragment.m20942$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).endRecording();
            goto L24
        L21:
            if (TopicsFragment.m20940$$Nest$fgetiBlur3Invalidated(this.this$0) == true) goto L22;
        L24:
            if (TopicsFragment.m20941$$Nest$fgetiBlur3SourceGlass(this.this$0) != null) goto L26;
        L32:
            TopicsFragment.m20972$$Nest$fputiBlur3Invalidated(this.this$0, false);
            goto L33
        L26:
            if (TopicsFragment.m20941$$Nest$fgetiBlur3SourceGlass(this.this$0).inRecording() == true) goto L32;
            if (TopicsFragment.m20941$$Nest$fgetiBlur3SourceGlass(this.this$0).needUpdateDisplayList(r02, r13) == false) goto L30;
        L31:
            TopicsFragment.m20955$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(TopicsFragment.m20941$$Nest$fgetiBlur3SourceGlass(this.this$0).beginRecording(r02, r13), -2);
            TopicsFragment.m20941$$Nest$fgetiBlur3SourceGlass(this.this$0).endRecording();
            goto L32
        L30:
            if (TopicsFragment.m20940$$Nest$fgetiBlur3Invalidated(this.this$0) == false) goto L32;
        L13:
            r13 = getMeasuredHeight();
            goto L15
        L9:
            r02 = getMeasuredWidth();
            goto L10
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawBlurRect(Canvas r7, float r8, Rect r9, Paint r10, boolean r11) {
            if (Build.VERSION.SDK_INT < 29) goto L11;
            if (SharedConfig.chatBlurEnabled() == false) goto L11;
            if (TopicsFragment.m20942$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L11;
            r7.save();
            r7.translate(0.0f, -r8);
            TopicsFragment.m20942$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).draw(r7, r9.left, r9.top + r8, r9.right, r9.bottom + r8);
            r7.restore();
            int r6 = r10.getAlpha();
            r10.setAlpha(178);
            r7.drawRect(r9, r10);
            r10.setAlpha(r6);
            return;
        L11:
            r7.drawRect(r9, r10);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r12, View r13, long r14) {
            if (r13 != TopicsFragment.access$100(this.this$0)) goto L13;
            if (this.this$0.isInPreviewMode() == true) goto L13;
            int r1 = (int) (TopicsFragment.access$200(this.this$0).getY() + getActionBarFullHeight());
            this.this$0.getParentLayout().drawHeaderShadow(r12, (int) ((1.0f - TopicsFragment.m20956$$Nest$fgetsearchAnimationProgress(this.this$0)) * 255.0f), r1);
            if (TopicsFragment.m20956$$Nest$fgetsearchAnimationProgress(this.this$0) <= 0.0f) goto L13;
            if (TopicsFragment.m20956$$Nest$fgetsearchAnimationProgress(this.this$0) >= 1.0f) goto L11;
            int r2 = Theme.forcedDividerPaint.getAlpha();
            Theme.forcedDividerPaint.setAlpha((int) (r2 * TopicsFragment.m20956$$Nest$fgetsearchAnimationProgress(this.this$0)));
            float r7 = r1;
            r12.drawLine(0.0f, r7, getMeasuredWidth(), r7, Theme.forcedDividerPaint);
            Theme.forcedDividerPaint.setAlpha(r2);
            goto L13
        L11:
            float r72 = r1;
            r12.drawLine(0.0f, r72, getMeasuredWidth(), r72, Theme.forcedDividerPaint);
        L13:
            return super.drawChild(r12, r13, r14);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawList(Canvas r6, boolean r7, ArrayList<SizeNotifierFrameLayout.IViewWithInvalidateCallback> r8) {
            int r72 = 0;
        L4:
            if (r72 >= TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getChildCount()) goto L16;
            View r0 = TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getChildAt(r72);
            if (r0.getY() >= AndroidUtilities.dp(100.0f)) goto L15;
            if (r0.getVisibility() != 0) goto L15;
            int r1 = r6.save();
            r6.translate(TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getX() + r0.getX(), (getY() + TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getY()) + r0.getY());
            if (r8 != null) goto L12;
        L14:
            r0.draw(r6);
            r6.restoreToCount(r1);
            goto L15
        L12:
            if ((r0 instanceof SizeNotifierFrameLayout.IViewWithInvalidateCallback) == false) goto L14;
            r8.add((SizeNotifierFrameLayout.IViewWithInvalidateCallback) r0);
        L15:
            r72 = r72 + 1;
            goto L4
        }

        public int getActionBarFullHeight() {
            float r0 = TopicsFragment.access$000(this.this$0).getHeight();
            if (TopicsFragment.m20959$$Nest$fgetsearchTabsView(this.this$0) != null) goto L5;
        L7:
            float r1 = 0.0f;
        L9:
            return (int) (r0 + (r1 * TopicsFragment.m20956$$Nest$fgetsearchAnimationProgress(this.this$0)));
        L5:
            if (TopicsFragment.m20959$$Nest$fgetsearchTabsView(this.this$0).getVisibility() == 8) goto L7;
            r1 = TopicsFragment.m20959$$Nest$fgetsearchTabsView(this.this$0).getMeasuredHeight();
            goto L9
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r10, int r11, int r12, int r13, int r14) {
            int r102 = getChildCount();
            int r0 = getPaddingLeft();
            int r132 = (r13 - r11) - getPaddingRight();
            int r112 = getPaddingTop();
            int r142 = (r14 - r12) - getPaddingBottom();
            int r1 = 0;
        L3:
            if (r1 >= r102) goto L31;
            View r2 = getChildAt(r1);
            if (r2.getVisibility() == 8) goto L30;
            FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) r2.getLayoutParams();
            int r4 = r2.getMeasuredWidth();
            int r5 = r2.getMeasuredHeight();
            int r6 = r3.gravity;
            if (r6 != (-1)) goto L9;
            r6 = 0;
        L9:
            int r7 = Gravity.getAbsoluteGravity(r6, getLayoutDirection());
            int r62 = r6 & 112;
            int r72 = r7 & 7;
            if (r72 != 1) goto L12;
            int r73 = ((((r132 - r0) - r4) / 2) + r0) + r3.leftMargin;
            int r8 = r3.rightMargin;
        L15:
            int r74 = r73 - r8;
        L18:
            if (r62 != 16) goto L20;
            int r63 = ((((r142 - r112) - r5) / 2) + r112) + r3.topMargin;
            int r32 = r3.bottomMargin;
        L27:
            int r33 = r63 - r32;
        L29:
            r2.layout(r74, r33, r4 + r74, r5 + r33);
            goto L30
        L20:
            if (r62 == 80) goto L26;
            r33 = r3.topMargin + r112;
            if ((r2 instanceof ActionBar) == true) goto L29;
            if (this.this$0.isInPreviewMode() == true) goto L29;
            r33 = r33 + (TopicsFragment.access$300(this.this$0).getTop() + TopicsFragment.access$400(this.this$0).getMeasuredHeight());
            goto L29
        L26:
            r63 = r142 - r5;
            r32 = r3.bottomMargin;
            goto L27
        L12:
            if (r72 == 5) goto L14;
            r74 = r3.leftMargin + r0;
            goto L18
        L14:
            r73 = r132 - r4;
            r8 = r3.rightMargin;
        L30:
            r1 = r1 + 1;
            goto L3
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r11, int r12) {
            int r6 = View.MeasureSpec.getSize(r11);
            int r7 = View.MeasureSpec.getSize(r12);
            if (TopicsFragment.m20927$$Nest$fgetbottomOverlayContainer(this.this$0) == null) goto L5;
            this.ignoreLayout = true;
            TopicsFragment.m20927$$Nest$fgetbottomOverlayContainer(this.this$0).getLayoutParams().height = AndroidUtilities.dp(51.0f) + TopicsFragment.m20948$$Nest$fgetnavigationBarHeight(this.this$0);
            TopicsFragment.m20927$$Nest$fgetbottomOverlayContainer(this.this$0).setPadding(0, 0, 0, TopicsFragment.m20948$$Nest$fgetnavigationBarHeight(this.this$0));
            this.ignoreLayout = false;
        L5:
            int r1 = 0;
            int r8 = 0;
        L7:
            if (r1 >= getChildCount()) goto L12;
            View r3 = getChildAt(r1);
            if ((r3 instanceof ActionBar) == false) goto L11;
            r3.measure(r11, View.MeasureSpec.makeMeasureSpec(0, 0));
            r8 = r3.getMeasuredHeight();
        L11:
            r1 = r1 + 1;
            goto L7
        L12:
            int r9 = 0;
        L14:
            if (r9 >= getChildCount()) goto L23;
            View r13 = getChildAt(r9);
            if ((r13 instanceof ActionBar) == false) goto L18;
        L20:
            int r5 = r8;
        L22:
            r9 = r9 + 1;
            r8 = r5;
            goto L14
        L18:
            if (r13.getFitsSystemWindows() == false) goto L21;
            measureChildWithMargins(r13, r11, 0, r12, 0);
            goto L20
        L21:
            r5 = r8;
            measureChildWithMargins(r13, r11, 0, r12, r5);
            goto L22
        L23:
            setMeasuredDimension(r6, r7);
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends LinearLayoutManager {
        private boolean fixOffset;
        final /* synthetic */ TopicsFragment this$0;

        public static /* synthetic */ void $r8$lambda$w7qPqCzIC53IQyassnS_qoA1Gh4(AnonymousClass10 r0) {
            r0.lambda$onLayoutChildren$0();
        }

        public AnonymousClass10(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onLayoutChildren$0() {
            this.this$0.adapter.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler r2, RecyclerView.State r3) {
            boolean r0 = BuildVars.DEBUG_VERSION;
            super.onLayoutChildren(r2, r3);     // Catch: IndexOutOfBoundsException -> L5
            return;
        L5:
            e = move-exception;
            FileLog.e(e);
            AndroidUtilities.runOnUIThread(new TopicsFragment$10$$ExternalSyntheticLambda0(this));
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.ItemTouchHelper.ViewDropHandler
        public void prepareForDrop(View r2, View r3, int r4, int r5) {
            this.fixOffset = true;
            super.prepareForDrop(r2, r3, r4, r5);
            this.fixOffset = false;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void scrollToPositionWithOffset(int r2, int r3) {
            if (this.fixOffset == false) goto L5;
            r3 = r3 - TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getPaddingTop();
        L5:
            super.scrollToPositionWithOffset(r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int r18, RecyclerView.Recycler r19, RecyclerView.State r20) {
            boolean r5 = false;
            if (TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).fastScrollAnimationRunning == false) goto L6;
            return 0;
        L6:
            if (TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getScrollState() != 1) goto L8;
            boolean r4 = true;
        L9:
            int r7 = TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getPaddingTop();
            if (r18 < 0) goto L12;
        L41:
            int r11 = r18;
        L43:
            if (TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getViewOffset() == 0.0f) goto L52;
            if (r18 <= 0) goto L52;
            if (r4 == false) goto L52;
            float r112 = ((int) TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getViewOffset()) - r18;
            if (r112 >= 0.0f) goto L49;
            int r12 = (int) r112;
            r112 = 0.0f;
        L50:
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).setViewsOffset(r112);
            r11 = r12;
            goto L52
        L49:
            r12 = 0;
        L52:
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) == 0) goto L110;
            if (TopicsFragment.m20938$$Nest$fgethiddenCount(this.this$0) <= 0) goto L110;
            int r2 = super.scrollVerticallyBy(r11, r19, r20);
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) == null) goto L58;
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).scrollDy = r2;
        L58:
            int r3 = this.this$0.layoutManager.findFirstVisibleItemPosition();
            if (r3 != 0) goto L61;
            View r122 = this.this$0.layoutManager.findViewByPosition(r3);
        L62:
            if (r122 == null) goto L65;
            r122.setTranslationX(0.0f);
        L65:
            if (r3 != 0) goto L103;
            if (r122 == null) goto L103;
            if ((r122.getBottom() - r7) < AndroidUtilities.dp(4.0f)) goto L103;
            if (TopicsFragment.m20961$$Nest$fgetstartArchivePullingTime(this.this$0) != 0) goto L73;
            TopicsFragment.m20977$$Nest$fputstartArchivePullingTime(this.this$0, System.currentTimeMillis());
        L73:
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) == 2) goto L75;
        L77:
            float r32 = ((r122.getTop() - r7) / r122.getMeasuredHeight()) + 1.0f;
            if (r32 <= 1.0f) goto L80;
            r32 = 1.0f;
        L80:
            long r72 = System.currentTimeMillis() - TopicsFragment.m20961$$Nest$fgetstartArchivePullingTime(this.this$0);
            if (r32 <= 0.85f) goto L86;
            if (r72 <= 220) goto L86;
            r5 = true;
        L86:
            if (TopicsFragment.m20928$$Nest$fgetcanShowHiddenArchive(this.this$0) == r5) goto L94;
            TopicsFragment.m20966$$Nest$fputcanShowHiddenArchive(this.this$0, r5);
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) != 2) goto L94;
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).performHapticFeedback(3, 2);     // Catch: Exception -> L111
        L91:
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) == null) goto L94;
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).colorize(r5);
        L94:
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) != 2) goto L101;
            if ((r11 - r2) == 0) goto L101;
            if (r18 >= 0) goto L101;
            if (r4 == false) goto L101;
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).setViewsOffset(TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getViewOffset() - ((r18 * 0.2f) * (1.0f - (TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getViewOffset() / PullForegroundDrawable.getMaxOverscroll()))));
        L101:
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) == null) goto L106;
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).setPullProgress(r32);
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).setListView(TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0));
        L106:
            if (r122 == null) goto L108;
            r122.invalidate();
        L108:
            return r2;
        L75:
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) == null) goto L77;
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).showHidden();
        L103:
            TopicsFragment.m20977$$Nest$fputstartArchivePullingTime(this.this$0, 0);
            TopicsFragment.m20966$$Nest$fputcanShowHiddenArchive(this.this$0, false);
            TopicsFragment.m20975$$Nest$fputpullViewState(this.this$0, 2);
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) == null) goto L106;
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).resetText();
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).setPullProgress(0.0f);
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).setListView(TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0));
            goto L106
        L61:
            r122 = null;
        L110:
            return super.scrollVerticallyBy(r11, r19, r20);
        L12:
            if (TopicsFragment.m20938$$Nest$fgethiddenCount(this.this$0) <= 0) goto L41;
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) != 2) goto L41;
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).setOverScrollMode(0);
            int r113 = this.this$0.layoutManager.findFirstVisibleItemPosition();
            if (r113 != 0) goto L24;
            View r123 = this.this$0.layoutManager.findViewByPosition(r113);
            if (r123 == null) goto L20;
            r123.setTranslationX(0.0f);
        L20:
            if (r123 == null) goto L24;
            if ((r123.getBottom() - r7) > AndroidUtilities.dp(1.0f)) goto L24;
            r113 = 1;
        L24:
            if (r4 == true) goto L34;
            View r124 = this.this$0.layoutManager.findViewByPosition(r113);
            if (r124 == null) goto L41;
            if (SharedConfig.useThreeLinesLayout == false) goto L30;
            float r13 = 78.0f;
        L31:
            int r125 = (-(r124.getTop() - r7)) + ((r113 - 1) * (AndroidUtilities.dp(r13) + 1));
            if (r125 >= Math.abs(r18)) goto L41;
            r11 = -r125;
            goto L43
        L30:
            r13 = 72.0f;
            goto L31
        L34:
            if (r113 != 0) goto L41;
            View r114 = this.this$0.layoutManager.findViewByPosition(r113);
            float r126 = ((r114.getTop() - r7) / r114.getMeasuredHeight()) + 1.0f;
            if (r126 <= 1.0f) goto L38;
            r126 = 1.0f;
        L38:
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).setOverScrollMode(2);
            r11 = (int) (r18 * (0.45f - (r126 * 0.25f)));
            if (r11 <= (-1)) goto L43;
            r11 = -1;
            goto L43
        L8:
            r4 = false;
            goto L9
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r2, RecyclerView.State r3, int r4) {
            if (TopicsFragment.m20938$$Nest$fgethiddenCount(this.this$0) > 0) goto L5;
        L8:
            LinearSmoothScrollerCustom r32 = new LinearSmoothScrollerCustom(r2.getContext(), 0);
            r32.setTargetPosition(r4);
            startSmoothScroll(r32);
            return;
        L5:
            if (r4 != 1) goto L8;
            super.smoothScrollToPosition(r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 extends RecyclerView.OnScrollListener {
        int prevPosition;
        int prevTop;
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass11(TopicsFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r7, int r8, int r9) {
            int r82 = this.this$0.layoutManager.findFirstVisibleItemPosition();
            if (r82 == (-1)) goto L39;
            RecyclerView.ViewHolder r92 = r7.findViewHolderForAdapterPosition(r82);
            boolean r0 = false;
            if (r92 == null) goto L7;
            int r93 = r92.itemView.getTop();
        L8:
            int r1 = this.prevPosition;
            if (r1 != r82) goto L18;
            int r12 = this.prevTop;
            int r3 = r12 - r93;
            if (r93 >= r12) goto L13;
            boolean r13 = true;
        L15:
            if (Math.abs(r3) > 1) goto L21;
            boolean r32 = false;
        L22:
            if (r32 == false) goto L30;
            boolean r33 = r7.canScrollVertically(1);
            TopicsFragment r4 = this.this$0;
            if (BottomNavigationBar.floating() == false) goto L28;
            if (r13 == false) goto L28;
            if (r33 == false) goto L28;
            boolean r34 = true;
        L29:
            TopicsFragment.m20974$$Nest$fputmainTabsHiddenByScroll(r4, r34);
            TopicsFragment.m20982$$Nest$mcheckUi_mainTabsVisible(this.this$0);
        L28:
            r34 = false;
        L30:
            TopicsFragment r35 = this.this$0;
            if (r13 == false) goto L35;
            if (r7.canScrollVertically(1) == false) goto L35;
        L36:
            r0 = true;
        L37:
            TopicsFragment.m20986$$Nest$mhideFloatingButton(r35, r0, true);
            this.prevPosition = r82;
            this.prevTop = r93;
            return;
        L35:
            if (this.this$0.canShowCreateTopic == true) goto L37;
        L21:
            r32 = true;
            goto L22
        L13:
            r13 = false;
            goto L15
        L18:
            if (r82 <= r1) goto L20;
            r13 = true;
            goto L21
        L20:
            r13 = false;
            goto L21
        L7:
            r93 = 0;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 extends ItemTouchHelper {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass12(TopicsFragment r1, ItemTouchHelper.Callback r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper
        public boolean shouldSwipeBack() {
            if (TopicsFragment.m20938$$Nest$fgethiddenCount(this.this$0) <= 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 extends StickerEmptyView {
        boolean showProgressInternal;
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass13(TopicsFragment r1, Context r2, View r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.StickerEmptyView
        public void showProgress(boolean r4, boolean r5) {
            super.showProgress(r4, r5);
            this.showProgressInternal = r4;
            TopicsFragment r0 = this.this$0;
            float r1 = 1.0f;
            if (r5 == false) goto L9;
            ViewPropertyAnimator r3 = TopicsFragment.m20933$$Nest$fgetemptyViewContainer(r0).textView.animate();
            if (r4 == false) goto L7;
            r1 = 0.0f;
        L7:
            r3.alpha(r1).start();
            return;
        L9:
            TopicsFragment.m20933$$Nest$fgetemptyViewContainer(r0).textView.animate().cancel();
            TextView r32 = TopicsFragment.m20933$$Nest$fgetemptyViewContainer(this.this$0).textView;
            if (r4 == false) goto L12;
            r1 = 0.0f;
        L12:
            r32.setAlpha(r1);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 extends FrameLayout {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass14(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            float r3 = Theme.chat_composeShadowDrawable.getIntrinsicHeight();
            r8.drawLine(0.0f, r3, getWidth(), r3, Theme.forcedDividerPaint);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 implements View.OnClickListener {
        final /* synthetic */ TopicsFragment this$0;

        public static /* synthetic */ void $r8$lambda$r6NAYkTsxq9WO_MTIwaTKuQGYMk(AnonymousClass15 r0, int r1) {
            r0.lambda$onClick$0(r1);
        }

        public AnonymousClass15(TopicsFragment r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onClick$0(int r1) {
            TopicsFragment r0 = this.this$0;
            if (r1 != 0) goto L6;
            TopicsFragment.m20992$$Nest$mupdateChatInfo(r0);
            return;
        L6:
            r0.finishFragment();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r11) {
            int r112 = TopicsFragment.m20926$$Nest$fgetbottomButtonType(this.this$0);
            TopicsFragment r0 = this.this$0;
            if (r112 != 1) goto L6;
            AlertsCreator.showBlockReportSpamAlert(r0, -r0.chatId, null, r0.getCurrentChat(), null, false, this.this$0.chatFull, new TopicsFragment$15$$ExternalSyntheticLambda0(this), this.this$0.getResourceProvider());
            return;
        L6:
            TopicsFragment.m20987$$Nest$mjoinToGroup(r0);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$16, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass16 extends FrameLayout {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass16(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (r6 != TopicsFragment.m20959$$Nest$fgetsearchTabsView(this.this$0)) goto L8;
            if (this.this$0.isInPreviewMode() == false) goto L8;
            this.this$0.getParentLayout().drawHeaderShadow(r5, (int) (TopicsFragment.m20956$$Nest$fgetsearchAnimationProgress(this.this$0) * 255.0f), (int) (TopicsFragment.m20959$$Nest$fgetsearchTabsView(this.this$0).getY() + TopicsFragment.m20959$$Nest$fgetsearchTabsView(this.this$0).getMeasuredHeight()));
        L8:
            return super.drawChild(r5, r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$17, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass17 extends FragmentContextView {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass17(TopicsFragment r1, Context r2, BaseFragment r3, boolean r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.FragmentContextView, android.view.View
        public void setVisibility(int r3) {
            DialogsActivityTopPanelLayout r0 = TopicsFragment.m20962$$Nest$fgettopPanelLayout(this.this$0);
            FrameLayout r2 = TopicsFragment.m20935$$Nest$fgetfragmentContextViewWrapper(this.this$0);
            if (r3 != 0) goto L5;
            boolean r32 = true;
        L6:
            r0.setViewVisible(r2, r32, true);
            return;
        L5:
            r32 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$18, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass18 extends View {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass18(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            View r0 = this.this$0.fragmentView;
            if (r0 == null) goto L6;
            r0.invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$19, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass19 implements ChatNotificationsPopupWrapper.Callback {
        final /* synthetic */ TopicsFragment this$0;
        final /* synthetic */ TLRPC.TL_forumTopic val$topic;

        public static /* synthetic */ void $r8$lambda$sltOT9VVPHW6SnIbzYe0oVf15Pg(AnonymousClass19 r0, TLRPC.TL_forumTopic r1) {
            r0.lambda$showCustomize$0(r1);
        }

        public AnonymousClass19(TopicsFragment r1, TLRPC.TL_forumTopic r2) {
            this.this$0 = r1;
            this.val$topic = r2;
        }

        private /* synthetic */ void lambda$showCustomize$0(TLRPC.TL_forumTopic r5) {
            Bundle r0 = new Bundle();
            r0.putLong("dialog_id", -this.this$0.chatId);
            r0.putLong("topic_id", r5.id);
            TopicsFragment r4 = this.this$0;
            r4.presentFragment(new ProfileNotificationsActivity(r0, r4.themeDelegate));
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void dismiss() {
            this.this$0.finishPreviewFragment();
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void muteFor(int r8) {
            this.this$0.finishPreviewFragment();
            TopicsFragment r0 = this.this$0;
            if (r8 == 0) goto L5;
            TopicsFragment.access$1200(r0).muteUntil(-this.this$0.chatId, this.val$topic.id, r8);
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L16;
            TopicsFragment r7 = this.this$0;
            BulletinFactory.createMuteBulletin(r7, 5, r8, r7.getResourceProvider()).show();
            return;
        L16:
            return;
        L5:
            if (r0.getMessagesController().isDialogMuted(-this.this$0.chatId, this.val$topic.id) == false) goto L8;
            TopicsFragment.access$1100(this.this$0).muteDialog(-this.this$0.chatId, this.val$topic.id, false);
        L8:
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L15;
            TopicsFragment r72 = this.this$0;
            BulletinFactory.createMuteBulletin(r72, 4, r8, r72.getResourceProvider()).show();
            return;
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void showCustomize() {
            this.this$0.finishPreviewFragment();
            AndroidUtilities.runOnUIThread(new TopicsFragment$19$$ExternalSyntheticLambda0(this, this.val$topic), 500);
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void toggleMute() {
            this.this$0.finishPreviewFragment();
            boolean r0 = this.this$0.getMessagesController().isDialogMuted(-this.this$0.chatId, this.val$topic.id);
            TopicsFragment.access$1300(this.this$0).muteDialog(-this.this$0.chatId, this.val$topic.id, !r0);
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L13;
            TopicsFragment r7 = this.this$0;
            if (r0 == true) goto L7;
            int r1 = 3;
        L8:
            if (r0 == true) goto L10;
            int r02 = Integer.MAX_VALUE;
        L11:
            BulletinFactory.createMuteBulletin(r7, r1, r02, r7.getResourceProvider()).show();
            return;
        L10:
            r02 = 0;
            goto L11
        L7:
            r1 = 4;
            goto L8
        }

        @Override // org.telegram.ui.Components.ChatNotificationsPopupWrapper.Callback
        public void toggleSound() {
            SharedPreferences r0 = MessagesController.getNotificationsSettings(TopicsFragment.access$1000(this.this$0));
            boolean r1 = r0.getBoolean("sound_enabled_" + NotificationsController.getSharedPrefKey(-this.this$0.chatId, this.val$topic.id), true);
            boolean r3 = !r1;
            r0.edit().putBoolean("sound_enabled_" + NotificationsController.getSharedPrefKey(-this.this$0.chatId, this.val$topic.id), r3).apply();
            this.this$0.finishPreviewFragment();
            if (BulletinFactory.canShowBulletin(this.this$0) == false) goto L6;
            TopicsFragment r9 = this.this$0;
            BulletinFactory.createSoundEnabledBulletin(r9, r1 ? 1 : 0, r9.getResourceProvider()).show();
            return;
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ TopicsFragment this$0;
        final /* synthetic */ Context val$context;

        /* renamed from: org.telegram.ui.TopicsFragment$2$1, reason: invalid class name */
        public class AnonymousClass1 extends InviteMembersBottomSheet {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ long val$chatId;

            public AnonymousClass1(AnonymousClass2 r1, Context r2, int r3, LongSparseArray r4, long r5, BaseFragment r7, Theme.ResourcesProvider r8, long r9) {
                this.this$1 = r1;
                this.val$chatId = r9;
                super(r2, r3, r4, r5, r7, r8);
            }

            @Override // org.telegram.ui.Components.InviteMembersBottomSheet
            public boolean canGenerateLink() {
                TLRPC.Chat r3 = this.this$1.this$0.getMessagesController().getChat(Long.valueOf(this.val$chatId));
                if (r3 != null) goto L5;
                return false;
            L5:
                if (ChatObject.canUserDoAdminAction(r3, 3) == false) goto L10;
                return true;
            L10:
                return false;
            }
        }

        public static /* synthetic */ void $r8$lambda$1OkCq9MtFhXJWecrxhBTGxZpKV8() {
        }

        /* renamed from: $r8$lambda$9GgGXZ13Gn88oVaK9gZJ-ot8XjQ, reason: not valid java name */
        public static /* synthetic */ void m20996$r8$lambda$9GgGXZ13Gn88oVaK9gZJot8XjQ(TopicCreateFragment r0) {
            r0.showKeyboard();
        }

        public static /* synthetic */ void $r8$lambda$MvWTsA_daWBQWSNgDbBJgx2Yw_E(AnonymousClass2 r0, long r1, ArrayList r3, int r4) {
            r0.lambda$onItemClick$2(r1, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$YGWmPQd9l6dGwXzjn3nJGyacVfg(AnonymousClass2 r0) {
            r0.lambda$onItemClick$5();
        }

        public static /* synthetic */ void $r8$lambda$a5_ejfyVFlLHt_jetnweJw9aNXk(AnonymousClass2 r0, TLRPC.TL_messages_invitedUsers r1, int[] r2, int r3, ArrayList r4, long r5, TLRPC.TL_messages_invitedUsers r7) {
            r0.lambda$onItemClick$1(r1, r2, r3, r4, r5, r7);
        }

        public static /* synthetic */ void $r8$lambda$pYosptSIHGeb1N5SAhAi9CVhPGQ(AnonymousClass2 r0, TLRPC.Chat r1, boolean r2) {
            r0.lambda$onItemClick$4(r1, r2);
        }

        public AnonymousClass2(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        private /* synthetic */ void lambda$onItemClick$1(TLRPC.TL_messages_invitedUsers r2, int[] r3, int r4, ArrayList r5, long r6, TLRPC.TL_messages_invitedUsers r8) {
            if (r8 == null) goto L4;
            r2.missing_invitees.addAll(r8.missing_invitees);
        L4:
            int r0 = r3[0] + 1;
            r3[0] = r0;
            if (r0 != r4) goto L12;
            boolean r32 = r2.missing_invitees.isEmpty();
            TopicsFragment r42 = this.this$0;
            if (r32 == false) goto L10;
            BulletinFactory.of(r42).createUsersAddedBulletin(r5, this.this$0.getMessagesController().getChat(Long.valueOf(r6))).show();
            return;
        L10:
            TLRPC.Chat r33 = r42.getMessagesController().getChat(Long.valueOf(r6));
            AlertsCreator.checkRestrictedInviteUsers(TopicsFragment.access$700(this.this$0), r33, r2);
            return;
        }

        private /* synthetic */ void lambda$onItemClick$2(long r21, ArrayList r23, int r24) {
            AnonymousClass2 r1 = this;
            int r4 = r23.size();
            int[] r3 = new int[1];
            TLRPC.TL_messages_invitedUsers r2 = new TLRPC.TL_messages_invitedUsers();
            r2.updates = new TLRPC.TL_updates();
            int r8 = 0;
        L3:
            if (r8 >= r4) goto L5;
            r1.this$0.getMessagesController().addUserToChat(r21, (TLRPC.User) r23.get(r8), r24, null, r1.this$0, false, new TopicsFragment$2$$ExternalSyntheticLambda4(), null, new TopicsFragment$2$$ExternalSyntheticLambda5(r1, r2, r3, r4, r23, r21));
            r8 = r8 + 1;
            r1 = this;
            goto L3
        }

        private /* synthetic */ void lambda$onItemClick$4(TLRPC.Chat r4, boolean r5) {
            NotificationCenter r0 = this.this$0.getNotificationCenter();
            TopicsFragment r1 = this.this$0;
            int r2 = NotificationCenter.closeChats;
            r0.removeObserver(r1, r2);
            this.this$0.getNotificationCenter().postNotificationName(r2, new Object[0]);
            this.this$0.finishFragment();
            this.this$0.getNotificationCenter().postNotificationName(NotificationCenter.needDeleteDialog, new Object[]{Long.valueOf(-r4.id), null, r4, Boolean.valueOf(r5)});
        }

        private /* synthetic */ void lambda$onItemClick$5() {
            TopicsFragment.m20983$$Nest$mclearSelectedTopics(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r25) {
            if (r25 != (-1)) goto L10;
            int r0 = this.this$0.selectedTopics.size();
            TopicsFragment r1 = this.this$0;
            if (r0 <= 0) goto L8;
            TopicsFragment.m20983$$Nest$mclearSelectedTopics(r1);
            return;
        L8:
            r1.finishFragment();
            return;
        L10:
            TLRPC.TL_forumTopic r02 = null;
            int r2 = 0;
            switch(r25) {
                case 1: goto L101;
                case 2: goto L89;
                case 3: goto L88;
                case 4: goto L81;
                case 5: goto L81;
                case 6: goto L75;
                case 7: goto L74;
                case 8: goto L62;
                case 9: goto L53;
                case 10: goto L53;
                case 11: goto L52;
                case 12: goto L18;
                case 13: goto L18;
                case 14: goto L14;
                case 15: goto L13;
                default: goto L102;
            };
        L13:
            TopicsFragment r03 = this.this$0;
            ReportBottomSheet.openChat(r03, -r03.chatId);
            goto L102
        L14:
            boolean r04 = ChatObject.hasAdminRights(this.this$0.getMessagesController().getChat(Long.valueOf(this.this$0.chatId)));
            TopicsFragment r22 = this.this$0;
            if (r04 == false) goto L17;
            BoostsActivity r05 = new BoostsActivity(-r22.chatId);
            r05.setBoostsStatus(TopicsFragment.m20925$$Nest$fgetboostsStatus(this.this$0));
            this.this$0.presentFragment(r05);
            goto L102
        L17:
            r22.getNotificationCenter().postNotificationName(NotificationCenter.openBoostForUsersDialog, new Object[]{Long.valueOf(-this.this$0.chatId)});
            goto L102
        L18:
            int r4 = 0;
        L20:
            if (r4 >= TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getChildCount()) goto L29;
            View r5 = TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getChildAt(r4);
            if ((r5 instanceof TopicDialogCell) == false) goto L28;
            TopicDialogCell r52 = (TopicDialogCell) r5;
            TLRPC.TL_forumTopic r6 = r52.forumTopic;
            if (r6 == null) goto L28;
            if (r6.id != 1) goto L28;
            r02 = r6;
        L30:
            if (r02 == null) goto L32;
        L41:
            if (r02 != null) goto L43;
        L51:
            TopicsFragment.m20983$$Nest$mclearSelectedTopics(this.this$0);
            goto L102
        L43:
            if (TopicsFragment.m20938$$Nest$fgethiddenCount(this.this$0) > 0) goto L45;
            TopicsFragment.m20971$$Nest$fputhiddenShown(this.this$0, true);
            TopicsFragment.m20975$$Nest$fputpullViewState(this.this$0, 2);
        L45:
            this.this$0.getMessagesController().getTopicsController().toggleShowTopic(this.this$0.chatId, 1, r02.hidden);
            if (r52 == null) goto L48;
            TopicsFragment.m20970$$Nest$fputgeneralTopicViewMoving(this.this$0, r52);
        L48:
            TopicsRecyclerView.m21004$$Nest$msetArchiveHidden(TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0), !r02.hidden, r52);
            TopicsFragment.m20995$$Nest$mupdateTopicsList(this.this$0, true, true);
            if (r52 == null) goto L51;
            r52.setTopicIcon(TopicDialogCell.m21002$$Nest$fgetcurrentTopic(r52));
        L32:
            if (r2 >= this.this$0.forumTopics.size()) goto L41;
            if (this.this$0.forumTopics.get(r2) == null) goto L40;
            if (this.this$0.forumTopics.get(r2).topic == null) goto L40;
            if (this.this$0.forumTopics.get(r2).topic.id != 1) goto L40;
            r02 = this.this$0.forumTopics.get(r2).topic;
        L40:
            r2 = r2 + 1;
        L28:
            r4 = r4 + 1;
            goto L20
        L29:
            r52 = null;
            goto L30
        L52:
            TLRPC.Chat r14 = this.this$0.getMessagesController().getChat(Long.valueOf(this.this$0.chatId));
            AlertsCreator.createClearOrDeleteDialogAlert(this.this$0, false, r14, null, false, true, false, false, new TopicsFragment$2$$ExternalSyntheticLambda2(this, r14));
            goto L102
        L53:
            TopicsFragment.m20978$$Nest$fputupdateAnimated(this.this$0, true);
            ArrayList r06 = new ArrayList(this.this$0.selectedTopics);
            int r42 = 0;
        L54:
            int r53 = r06.size();
            TopicsFragment r62 = this.this$0;
            if (r42 >= r53) goto L61;
            TopicsController r54 = TopicsFragment.m20963$$Nest$fgettopicsController(r62);
            long r63 = this.this$0.chatId;
            int r8 = ((Integer) r06.get(r42)).intValue();
            if (r25 != 9) goto L59;
            boolean r9 = true;
        L60:
            r54.toggleCloseTopic(r63, r8, r9);
            r42 = r42 + 1;
            goto L54
        L59:
            r9 = false;
            goto L60
        L61:
            TopicsFragment.m20983$$Nest$mclearSelectedTopics(r62);
            goto L102
        L62:
            ArrayList r07 = new ArrayList(this.this$0.selectedTopics);
            int r3 = 0;
        L63:
            int r43 = r07.size();
            TopicsFragment r55 = this.this$0;
            if (r3 >= r43) goto L73;
            TLRPC.TL_forumTopic r44 = TopicsFragment.m20963$$Nest$fgettopicsController(r55).findTopic(this.this$0.chatId, ((Integer) r07.get(r3)).intValue());
            if (r44 == null) goto L72;
            this.this$0.getMessagesController().markMentionsAsRead(-this.this$0.chatId, r44.id);
            MessagesController r12 = this.this$0.getMessagesController();
            long r13 = -this.this$0.chatId;
            int r15 = r44.top_message;
            TLRPC.Message r56 = r44.topMessage;
            if (r56 == null) goto L70;
            int r17 = r56.date;
        L71:
            r12.markDialogAsRead(r13, r15, 0, r17, false, r44.id, 0, true, 0);
            this.this$0.getMessagesStorage().updateRepliesMaxReadId(this.this$0.chatId, r44.id, r44.top_message, 0, true);
            goto L72
        L70:
            r17 = 0;
        L72:
            r3 = r3 + 1;
            goto L63
        L73:
            TopicsFragment.m20983$$Nest$mclearSelectedTopics(r55);
            goto L102
        L74:
            TopicsFragment r08 = this.this$0;
            TopicsFragment.m20984$$Nest$mdeleteTopics(r08, r08.selectedTopics, new TopicsFragment$2$$ExternalSyntheticLambda3(this));
            goto L102
        L75:
            Iterator<Integer> r09 = this.this$0.selectedTopics.iterator();
        L77:
            if (r09.hasNext() == false) goto L79;
            int r23 = r09.next().intValue();
            NotificationsController r45 = TopicsFragment.access$600(this.this$0);
            TopicsFragment r32 = this.this$0;
            r45.muteDialog(-r32.chatId, r23, TopicsFragment.m20947$$Nest$fgetmute(r32));
            goto L77
        L79:
            TopicsFragment.m20983$$Nest$mclearSelectedTopics(this.this$0);
            goto L102
        L88:
            TopicCreateFragment r010 = TopicCreateFragment.create(this.this$0.chatId, 0);
            this.this$0.presentFragment(r010);
            AndroidUtilities.runOnUIThread(new TopicsFragment$2$$ExternalSyntheticLambda1(r010), 200);
            goto L102
        L89:
            TLRPC.ChatFull r33 = this.this$0.getMessagesController().getChatFull(this.this$0.chatId);
            TLRPC.ChatFull r46 = this.this$0.chatFull;
            if (r46 == null) goto L94;
            TLRPC.ChatParticipants r47 = r46.participants;
            if (r47 == null) goto L94;
            r33.participants = r47;
        L94:
            if (r33 == null) goto L102;
            LongSparseArray r48 = new LongSparseArray();
            if (r33.participants != null) goto L98;
        L100:
            long r92 = r33.id;
            Context r24 = this.val$context;
            int r57 = TopicsFragment.access$500(this.this$0);
            long r64 = r33.id;
            TopicsFragment r7 = this.this$0;
            AnonymousClass1 r011 = new AnonymousClass1(this, r24, r57, r48, r64, r7, r7.themeDelegate, r92);
            r011.setDelegate(new TopicsFragment$2$$ExternalSyntheticLambda0(this, r92));
            r011.show();
        L98:
            if (r2 >= r33.participants.participants.size()) goto L100;
            r48.put(r33.participants.participants.get(r2).user_id, null);
            r2 = r2 + 1;
            goto L98
        L101:
            this.this$0.getMessagesController().getTopicsController().toggleViewForumAsMessages(this.this$0.chatId, true);
            TopicsFragment.m20969$$Nest$fputfinishDialogRightSlidingPreviewOnTransitionEnd(this.this$0, true);
            Bundle r012 = new Bundle();
            r012.putLong("chat_id", this.this$0.chatId);
            ChatActivity r26 = new ChatActivity(r012);
            r26.setSwitchFromTopics(true);
            this.this$0.presentFragment(r26);
        L102:
            super.onItemClick(r25);
            return;
        L81:
            if (this.this$0.selectedTopics.size() <= 0) goto L87;
            TopicsFragment.m20976$$Nest$fputscrollToTop(this.this$0, true);
            TopicsFragment.m20978$$Nest$fputupdateAnimated(this.this$0, true);
            TopicsController r49 = TopicsFragment.m20963$$Nest$fgettopicsController(this.this$0);
            TopicsFragment r013 = this.this$0;
            long r58 = r013.chatId;
            int r72 = r013.selectedTopics.iterator().next().intValue();
            if (r25 != 4) goto L85;
            boolean r82 = true;
        L86:
            r49.pinTopic(r58, r72, r82, this.this$0);
            goto L87
        L85:
            r82 = false;
        L87:
            TopicsFragment.m20983$$Nest$mclearSelectedTopics(this.this$0);
            goto L102
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$20, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass20 extends AnimatorListenerAdapter {
        final /* synthetic */ TopicsFragment this$0;
        final /* synthetic */ boolean val$showSearch;

        public AnonymousClass20(TopicsFragment r1, boolean r2) {
            this.this$0 = r1;
            this.val$showSearch = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            TopicsFragment r32 = this.this$0;
            if (this.val$showSearch == false) goto L5;
            float r0 = 1.0f;
        L6:
            TopicsFragment.m20994$$Nest$mupdateSearchProgress(r32, r0);
            boolean r33 = this.val$showSearch;
            TopicsFragment r02 = this.this$0;
            if (r33 == false) goto L10;
            TopicsFragment.m20950$$Nest$fgetother(r02).setVisibility(8);
            return;
        L10:
            AndroidUtilities.setAdjustResizeToNothing(r02.getParentActivity(), TopicsFragment.access$1400(this.this$0));
            TopicsFragment.m20957$$Nest$fgetsearchContainer(this.this$0).setVisibility(8);
            TopicsFragment.m20993$$Nest$mupdateCreateTopicButton(this.this$0, true);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$21, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass21 implements RecyclerListView.onMultiSelectionChanged {
        final /* synthetic */ TopicsFragment this$0;
        final /* synthetic */ HashSet val$alreadySelectedTopics;
        final /* synthetic */ boolean val$unselect;

        public AnonymousClass21(TopicsFragment r1, boolean r2, HashSet r3) {
            this.this$0 = r1;
            this.val$unselect = r2;
            this.val$alreadySelectedTopics = r3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.onMultiSelectionChanged
        public boolean canSelect(int r2) {
            TLRPC.TL_forumTopic r22 = TopicsFragment.m20985$$Nest$mgetTopicForMultiselect(this.this$0, r2);
            if (r22 != null) goto L6;
            return false;
        L6:
            boolean r0 = this.val$unselect;
            HashSet r1 = this.val$alreadySelectedTopics;
            if (r0 == false) goto L11;
            return r1.contains(Integer.valueOf(r22.id));
        L11:
            return !r1.contains(Integer.valueOf(r22.id));
        }

        @Override // org.telegram.ui.Components.RecyclerListView.onMultiSelectionChanged
        public int checkPosition(int r1, boolean r2) {
            return r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.onMultiSelectionChanged
        public void getPaddings(int[] r3) {
            r3[0] = TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getPaddingTop();
            r3[1] = TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getPaddingBottom();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.onMultiSelectionChanged
        public int getStartDragDistance() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.onMultiSelectionChanged
        public boolean limitReached() {
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.onMultiSelectionChanged
        public void onSelectionChanged(int r2, boolean r3, float r4, float r5) {
            TLRPC.TL_forumTopic r42 = TopicsFragment.m20985$$Nest$mgetTopicForMultiselect(this.this$0, r2);
            if (r42 != null) goto L6;
            return;
        L6:
            if (this.val$unselect == false) goto L9;
            r3 = !r3;
        L9:
            if (r3 != this.this$0.selectedTopics.contains(Integer.valueOf(r42.id))) goto L11;
            return;
        L11:
            RecyclerView.ViewHolder r22 = TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).findViewHolderForAdapterPosition(r2);
            TopicsFragment r1 = this.this$0;
            if (r22 == null) goto L14;
            View r23 = r22.itemView;
        L15:
            TopicsFragment.m20991$$Nest$mtoggleSelection(r1, r42, r23);
            return;
        L14:
            r23 = null;
            goto L15
        }

        @Override // org.telegram.ui.Components.RecyclerListView.onMultiSelectionChanged
        public void scrollBy(int r2) {
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).scrollBy(0, r2);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$22, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass22 extends AnimatorListenerAdapter {
        final /* synthetic */ TopicsFragment this$0;
        final /* synthetic */ boolean val$bottomPannelVisibleLocal;

        public AnonymousClass22(TopicsFragment r1, boolean r2) {
            this.this$0 = r1;
            this.val$bottomPannelVisibleLocal = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$bottomPannelVisibleLocal == true) goto L6;
            TopicsFragment.m20927$$Nest$fgetbottomOverlayContainer(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$23, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass23 implements Bulletin.Delegate {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass23(TopicsFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            if (TopicsFragment.m20927$$Nest$fgetbottomOverlayContainer(this.this$0) != null) goto L5;
            return 0;
        L5:
            if (TopicsFragment.m20927$$Nest$fgetbottomOverlayContainer(this.this$0).getVisibility() == 0) goto L7;
            return 0;
        L7:
            return TopicsFragment.m20927$$Nest$fgetbottomOverlayContainer(this.this$0).getMeasuredHeight();
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass3(TopicsFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            TopicsFragment.m20979$$Nest$manimateToSearchView(this.this$0, false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            TopicsFragment.m20979$$Nest$manimateToSearchView(this.this$0, true);
            TopicsFragment.m20957$$Nest$fgetsearchContainer(this.this$0).setSearchString(_UrlKt.FRAGMENT_ENCODE_SET);
            TopicsFragment.m20957$$Nest$fgetsearchContainer(this.this$0).setAlpha(0.0f);
            TopicsFragment.m20957$$Nest$fgetsearchContainer(this.this$0).emptyView.showProgress(true, false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchFilterCleared(FiltersView.MediaFilterData r1) {
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r1) {
            String r12 = r1.getText().toString();
            TopicsFragment.m20957$$Nest$fgetsearchContainer(this.this$0).setSearchString(r12);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 implements View.OnClickListener {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass4(TopicsFragment r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            TopicsFragment.m20989$$Nest$mopenProfile(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends TopicsRecyclerView {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass5(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean emptyViewIsVisible() {
            if (getAdapter() != null) goto L5;
        L24:
            return false;
        L5:
            if (isFastScrollAnimationRunning() == true) goto L24;
            ArrayList<Item> r0 = this.this$0.forumTopics;
            if (r0 == null) goto L22;
            if (r0.size() != 1) goto L22;
            if (this.this$0.forumTopics.get(0) == null) goto L22;
            if (this.this$0.forumTopics.get(0).topic == null) goto L22;
            if (this.this$0.forumTopics.get(0).topic.id != 1) goto L22;
            if (getAdapter().getItemCount() > 2) goto L20;
            return true;
        L20:
            return false;
        L22:
            if (getAdapter().getItemCount() > 1) goto L24;
            return true;
        }

        @Override // org.telegram.ui.TopicsFragment.TopicsRecyclerView, org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            TopicsFragment.m20981$$Nest$mcheckForLoadMore(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends PullForegroundDrawable {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass6(TopicsFragment r1, CharSequence r2, CharSequence r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.PullForegroundDrawable
        public float getViewOffset() {
            return TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getViewOffset();
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends DefaultItemAnimator {
        Runnable finishRunnable;
        int scrollAnimationIndex;
        final /* synthetic */ TopicsFragment this$0;

        public static /* synthetic */ void $r8$lambda$kgHYrQ_tQIa3XWr13aJX0Se6nt8(AnonymousClass7 r0) {
            r0.lambda$endAnimations$1();
        }

        /* renamed from: $r8$lambda$vCqJ_0Q-LKos-GuLDs6HIyp7oGQ, reason: not valid java name */
        public static /* synthetic */ void m20997$r8$lambda$vCqJ_0QLKosGuLDs6HIyp7oGQ(AnonymousClass7 r0) {
            r0.lambda$onAllAnimationsDone$0();
        }

        public AnonymousClass7(TopicsFragment r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$endAnimations$1() {
            this.finishRunnable = null;
            if (this.scrollAnimationIndex == (-1)) goto L6;
            this.this$0.getNotificationCenter().onAnimationFinish(this.scrollAnimationIndex);
            this.scrollAnimationIndex = -1;
            return;
        }

        private /* synthetic */ void lambda$onAllAnimationsDone$0() {
            this.finishRunnable = null;
            if (this.scrollAnimationIndex == (-1)) goto L6;
            this.this$0.getNotificationCenter().onAnimationFinish(this.scrollAnimationIndex);
            this.scrollAnimationIndex = -1;
            return;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void afterAnimateMoveImpl(RecyclerView.ViewHolder r2) {
            if (TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0) != r2.itemView) goto L12;
            TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0).setTranslationX(0.0f);
            if (TopicsFragment.m20944$$Nest$fgetitemTouchHelper(this.this$0) == null) goto L8;
            TopicsFragment.m20944$$Nest$fgetitemTouchHelper(this.this$0).clearRecoverAnimations();
        L8:
            if ((TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0) instanceof TopicDialogCell) == false) goto L10;
            ((TopicDialogCell) TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0)).setTopicIcon(TopicDialogCell.m21002$$Nest$fgetcurrentTopic((TopicDialogCell) TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0)));
        L10:
            TopicsFragment.m20970$$Nest$fputgeneralTopicViewMoving(this.this$0, null);
            return;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void checkIsRunning() {
            if (this.scrollAnimationIndex != (-1)) goto L8;
            this.scrollAnimationIndex = this.this$0.getNotificationCenter().setAnimationInProgress(this.scrollAnimationIndex, null, false);
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.finishRunnable = null;
            return;
        L9:
            return;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void endAnimations() {
            super.endAnimations();
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L5:
            TopicsFragment$7$$ExternalSyntheticLambda1 r02 = new TopicsFragment$7$$ExternalSyntheticLambda1(this);
            this.finishRunnable = r02;
            AndroidUtilities.runOnUIThread(r02);
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onAllAnimationsDone() {
            super.onAllAnimationsDone();
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.finishRunnable = null;
        L5:
            TopicsFragment$7$$ExternalSyntheticLambda0 r02 = new TopicsFragment$7$$ExternalSyntheticLambda0(this);
            this.finishRunnable = r02;
            AndroidUtilities.runOnUIThread(r02);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass8(TopicsFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            TopicsFragment.m20981$$Nest$mcheckForLoadMore(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.TopicsFragment$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TopicsFragment this$0;

        public AnonymousClass9(TopicsFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            super.onScrolled(r2, r3, r4);
            if (Build.VERSION.SDK_INT >= 31) goto L5;
            return;
        L5:
            if (TopicsFragment.m20955$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L9;
            TopicsFragment.m20955$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            TopicsFragment.m20980$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    /* loaded from: classes6.dex */
    public class Adapter extends AdapterWithDiffUtils {
        final /* synthetic */ TopicsFragment this$0;

        /* renamed from: org.telegram.ui.TopicsFragment$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            HashMap<String, Boolean> precalcEllipsized;
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
                this.precalcEllipsized = new HashMap();
            }

            @Override // android.view.View
            public void onMeasure(int r12, int r13) {
                int r132 = View.MeasureSpec.getSize(r12);
                int r0 = AndroidUtilities.dp(64.0f);
                int r2 = 0;
                int r3 = 0;
                int r4 = 0;
            L4:
                if (r2 >= this.this$1.getArray().size()) goto L45;
                if (this.this$1.getArray().get(r2) == null) goto L44;
                if (this.this$1.getArray().get(r2).topic == null) goto L44;
                String r5 = this.this$1.getArray().get(r2).topic.title;
                Boolean r6 = this.precalcEllipsized.get(r5);
                if (r6 != null) goto L34;
                int r8 = 50;
                if (LocaleController.isRTL == false) goto L15;
                float r62 = 18.0f;
            L20:
                int r63 = AndroidUtilities.dp(r62);
                if (LocaleController.isRTL == true) goto L24;
                int r64 = r132 - r63;
                int r82 = AndroidUtilities.dp(22.0f);
            L29:
                if (Theme.dialogs_namePaint[0].measureText(r5) > ((r64 - r82) - ((int) Math.ceil(Theme.dialogs_timePaint.measureText("00:00"))))) goto L31;
                boolean r65 = true;
            L32:
                r6 = Boolean.valueOf(r65);
                this.precalcEllipsized.put(r5, r6);
                goto L34
            L31:
                r65 = false;
                goto L32
            L24:
                r64 = r132 - r63;
                if (this.this$1.this$0.isInPreviewMode() == false) goto L27;
                r8 = 11;
            L27:
                r82 = AndroidUtilities.dp(r8 + 13);
                goto L29
            L15:
                if (this.this$1.this$0.isInPreviewMode() == false) goto L17;
                int r66 = 11;
            L18:
                r62 = r66 + 4;
                goto L20
            L17:
                r66 = 50;
            L34:
                if (r6.booleanValue() == true) goto L36;
                int r52 = 20;
            L37:
                int r53 = AndroidUtilities.dp(r52 + 64);
                if (this.this$1.getArray().get(r2).topic.id != 1) goto L41;
                r0 = r53;
            L41:
                if (this.this$1.getArray().get(r2).topic.hidden == false) goto L43;
                r3 = r3 + 1;
            L43:
                r4 = r4 + r53;
                goto L44
            L36:
                r52 = 0;
            L44:
                r2 = r2 + 1;
                goto L4
            L45:
                if (r3 <= 0) goto L47;
                int r133 = (((TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$1.this$0).getMeasuredHeight() - TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$1.this$0).getPaddingTop()) - TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$1.this$0).getPaddingBottom()) - r4) + r0;
            L48:
                super.onMeasure(r12, View.MeasureSpec.makeMeasureSpec(Math.max(0, r133), TLObject.FLAG_30));
                return;
            L47:
                r133 = 0;
                goto L48
            }
        }

        private Adapter(TopicsFragment r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(TopicsFragment r1, TopicsFragmentIA r2) {
            this(r1);
        }

        public ArrayList<Item> getArray() {
            boolean r0 = TopicsFragment.m20934$$Nest$fgetforumTopicsListFrozen(this.this$0);
            TopicsFragment r1 = this.this$0;
            if (r0 == false) goto L7;
            return TopicsFragment.m20936$$Nest$fgetfrozenForumTopicsList(r1);
        L7:
            return r1.forumTopics;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return getArray().size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != (getItemCount() - 1)) goto L7;
            return 2;
        L7:
            return this.this$0.forumTopics.get(r2).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L5;
            return true;
        L5:
            if (r1.getItemViewType() == 3) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            TopicsFragment.m20973$$Nest$fputlastItemsCount(this.this$0, getItemCount());
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r23, int r24) {
            if (r23.getItemViewType() != 0) goto L61;
            TLRPC.TL_forumTopic r7 = getArray().get(r24).topic;
            int r3 = r24 + 1;
            if (r3 >= getArray().size()) goto L8;
            TLRPC.TL_forumTopic r6 = getArray().get(r3).topic;
        L7:
            TLRPC.TL_forumTopic r13 = r6;
            TopicDialogCell r14 = (TopicDialogCell) r23.itemView;
            TLRPC.Message r1 = r7.topMessage;
            TLRPC.TL_forumTopic r62 = r14.forumTopic;
            if (r62 != null) goto L12;
            int r63 = 0;
        L13:
            int r8 = r7.id;
            if (r63 == r8) goto L16;
        L20:
            boolean r12 = false;
        L21:
            if (r1 == null) goto L29;
            MessageObject r10 = new MessageObject(TopicsFragment.access$1700(this.this$0), r1, false, false);
            if (this.this$0.getMessagesController().isMonoForum(-this.this$0.chatId) == false) goto L30;
            r14.isMonoForumTopicDialog = true;
            r14.drawAvatar = true;
            r14.forumTopic = r7;
            r14.messagePaddingStart = 72;
            r14.chekBoxPaddingTop = 42.0f;
            r14.heightDefault = 72;
            r14.heightThreeLines = 78;
            r14.setDialog(DialogObject.getPeerDialogId(r7.from_id), r10, r1.date, false, false);
            r14.isSavedDialogCell = true;
            if (r3 >= getItemCount()) goto L27;
            boolean r15 = true;
        L28:
            r14.useSeparator = r15;
            goto L29
        L27:
            r15 = false;
            goto L28
        L30:
            TopicsFragment r16 = this.this$0;
            long r32 = -r16.chatId;
            boolean r11 = r16.isInPreviewMode();
            int r17 = r8;
            r14.setForumTopic(r7, r32, r10, r11, r12);
            if (r24 == (this.this$0.forumTopics.size() - 1)) goto L33;
        L36:
            boolean r33 = true;
        L37:
            r14.drawDivider = r33;
            boolean r34 = r7.pinned;
            if (r34 == false) goto L43;
            if (r13 != null) goto L41;
        L42:
            boolean r4 = true;
        L44:
            r14.fullSeparator = r4;
            if (r34 == true) goto L47;
        L49:
            boolean r35 = false;
        L50:
            r14.setPinForced(r35);
            r14.position = r24;
        L52:
            if (this.this$0.getMessagesController().isMonoForum(-this.this$0.chatId) == true) goto L54;
            r14.setTopicIcon(r7);
        L54:
            r14.setChecked(this.this$0.selectedTopics.contains(Integer.valueOf(r17)), r12);
            if (TopicsFragment.m20960$$Nest$fgetselectedTopicForTablet(this.this$0) != r17) goto L57;
            boolean r42 = true;
        L58:
            r14.setDialogSelected(r42);
            r14.onReorderStateChanged(TopicsFragment.m20954$$Nest$fgetreordering(this.this$0), true);
            return;
        L57:
            r42 = false;
            goto L58
        L47:
            if (r7.hidden == true) goto L49;
            r35 = true;
            goto L50
        L41:
            if (r13.pinned == false) goto L42;
        L43:
            r4 = false;
            goto L44
        L33:
            if (TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).emptyViewIsVisible() == true) goto L36;
            r33 = false;
        L29:
            r17 = r8;
            goto L52
        L16:
            if (r14.position != r24) goto L20;
            if (this.this$0.animatedUpdateEnabled == false) goto L20;
            r12 = true;
            goto L21
        L12:
            r63 = r62.id;
            goto L13
        L8:
            r6 = null;
            goto L7
        L61:
            if (r23.getItemViewType() != 3) goto L71;
            TopicDialogCell r18 = (TopicDialogCell) r23.itemView;
            r18.setCurrentDialogId(-this.this$0.chatId);
            if (r24 == (this.this$0.forumTopics.size() - 1)) goto L65;
        L68:
            boolean r43 = true;
        L69:
            r18.drawDivider = r43;
            r18.position = r24;
            return;
        L65:
            if (TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).emptyViewIsVisible() == true) goto L68;
            r43 = false;
            goto L69
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
            if (r9 == 0) goto L12;
            if (r9 == 3) goto L12;
            if (r9 != 2) goto L10;
            TopicsFragment r92 = this.this$0;
            AnonymousClass1 r0 = new AnonymousClass1(this, this.this$0.getContext());
            TopicsFragment.m20968$$Nest$fputemptyView(r92, r0);
            return new RecyclerListView.Holder(r0);
        L10:
            FlickerLoadingView r7 = new FlickerLoadingView(r8.getContext());
            r7.setViewType(24);
            r7.setIsSingleCell(true);
            r7.showDate(true);
            return new RecyclerListView.Holder(r7);
        L12:
            TopicDialogCell r1 = new TopicDialogCell(this.this$0, null, r8.getContext(), true, false);
            if (r9 != 3) goto L23;
            boolean r82 = UserObject.isBotForumWithEditableTopics(TopicsFragment.access$1500(this.this$0), -this.this$0.chatId);
            r1.setForumIcon(ForumUtilities.createTopicDrawable(_UrlKt.FRAGMENT_ENCODE_SET, ForumBubbleDrawable.serverSupportedColor[0], false));
            if (r82 == true) goto L17;
            int r93 = R.string.BotForumAskForStartOffNewChatTitle;
        L18:
            r1.setTitleOverride(LocaleController.getString(r93));
            if (r82 == true) goto L21;
            int r83 = R.string.BotForumAskForStartOffNewChatForward;
        L22:
            r1.setCustomMessage(LocaleController.getString(r83));
            goto L23
        L21:
            r83 = R.string.BotForumAskForStartNewChatForward;
            goto L22
        L17:
            r93 = R.string.BotForumAskForStartNewChatTitle;
        L23:
            r1.inPreviewMode = TopicsFragment.access$1600(this.this$0);
            r1.setArchivedPullAnimation(TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0));
            return new RecyclerListView.Holder(r1);
        }

        public void swapElements(int r3, int r4) {
            if (TopicsFragment.m20934$$Nest$fgetforumTopicsListFrozen(this.this$0) == false) goto L5;
            return;
        L5:
            ArrayList<Item> r0 = this.this$0.forumTopics;
            r0.add(r4, r0.remove(r3));
            if (TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).getItemAnimator() == TopicsFragment.m20943$$Nest$fgetitemAnimator(this.this$0)) goto L8;
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0).setItemAnimator(TopicsFragment.m20943$$Nest$fgetitemAnimator(this.this$0));
        L8:
            notifyItemMoved(r3, r4);
        }
    }

    /* loaded from: classes6.dex */
    public class EmptyViewContainer extends FrameLayout {
        boolean increment;
        float progress;
        TextView textView;
        final /* synthetic */ TopicsFragment this$0;

        public EmptyViewContainer(TopicsFragment r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            this.textView = new TextView(r11);
            if (LocaleController.isRTL == false) goto L5;
            SpannableStringBuilder r112 = new SpannableStringBuilder("  ");
            r112.setSpan(new ColoredImageSpan(R.drawable.attach_arrow_left), 0, 1, 0);
            r112.append(LocaleController.getString(R.string.TapToCreateTopicHint));
        L6:
            this.textView.setText(r112);
            this.textView.setTextSize(1, 14.0f);
            this.textView.setLayerType(2, null);
            this.textView.setTextColor(r10.getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
            TextView r102 = this.textView;
            boolean r113 = LocaleController.isRTL;
            if (r113 == false) goto L9;
            float r5 = 72.0f;
        L10:
            if (r113 == false) goto L12;
            float r7 = 32.0f;
        L13:
            addView(r102, LayoutHelper.createFrame(-2, -2.0f, 81, r5, 0.0f, r7, 32.0f));
            return;
        L12:
            r7 = 72.0f;
            goto L13
        L9:
            r5 = 32.0f;
            goto L10
        L5:
            r112 = new SpannableStringBuilder(LocaleController.getString(R.string.TapToCreateTopicHint));
            r112.append("  ");
            r112.setSpan(new ColoredImageSpan(R.drawable.arrow_newchat), r112.length() - 1, r112.length(), 0);
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            super.dispatchDraw(r4);
            boolean r42 = this.increment;
            float r0 = this.progress;
            int r1 = 1;
            if (r42 == false) goto L7;
            float r02 = r0 + 0.013333334f;
            this.progress = r02;
            if (r02 <= 1.0f) goto L10;
            this.increment = false;
            this.progress = 1.0f;
        L10:
            TextView r43 = this.textView;
            float r03 = CubicBezierInterpolator.DEFAULT.getInterpolation(this.progress) * AndroidUtilities.dp(8.0f);
            if (LocaleController.isRTL == false) goto L13;
            r1 = -1;
        L13:
            r43.setTranslationX(r03 * r1);
            invalidate();
            return;
        L7:
            float r04 = r0 - 0.013333334f;
            this.progress = r04;
            if (r04 >= 0.0f) goto L10;
            this.increment = true;
            this.progress = 0.0f;
            goto L10
        }
    }

    /* loaded from: classes6.dex */
    public class Item extends AdapterWithDiffUtils.Item {
        final /* synthetic */ TopicsFragment this$0;
        TLRPC.TL_forumTopic topic;

        public Item(TopicsFragment r1, int r2, TLRPC.TL_forumTopic r3) {
            this.this$0 = r1;
            super(r2, true);
            this.topic = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L16:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L16;
            Item r52 = (Item) r5;
            int r2 = this.viewType;
            if (r2 != r52.viewType) goto L16;
            if (r2 != 0) goto L16;
            if (this.topic.id != r52.topic.id) goto L16;
            return true;
        }
    }

    /* loaded from: classes6.dex */
    public class MessagesSearchContainer extends ViewPagerFixed implements FilteredSearchView.UiCallback {
        boolean canLoadMore;
        SearchViewPager.ChatPreviewDelegate chatPreviewDelegate;
        StickerEmptyView emptyView;
        FlickerLoadingView flickerLoadingView;
        boolean isLoading;
        RecyclerItemsEnterAnimator itemsEnterAnimator;
        private int keyboardSize;
        LinearLayoutManager layoutManager;
        int messagesEndRow;
        int messagesHeaderRow;
        boolean messagesIsLoading;
        int messagesStartRow;
        RecyclerListView recyclerView;
        int rowCount;
        SearchAdapter searchAdapter;
        FrameLayout searchContainer;
        ArrayList<MessageObject> searchResultMessages;
        ArrayList<TLRPC.TL_forumTopic> searchResultTopics;
        Runnable searchRunnable;
        String searchString;
        private ArrayList<MessageObject> selectedItems;
        final /* synthetic */ TopicsFragment this$0;
        int topicsEndRow;
        int topicsHeaderRow;
        int topicsStartRow;
        private ViewPagerAdapter viewPagerAdapter;

        /* renamed from: org.telegram.ui.TopicsFragment$MessagesSearchContainer$1, reason: invalid class name */
        public class AnonymousClass1 implements SearchViewPager.ChatPreviewDelegate {
            final /* synthetic */ MessagesSearchContainer this$1;
            final /* synthetic */ TopicsFragment val$this$0;

            public AnonymousClass1(MessagesSearchContainer r1, TopicsFragment r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.SearchViewPager.ChatPreviewDelegate
            public void finish() {
                Point r0 = AndroidUtilities.displaySize;
                if (r0.x <= r0.y) goto L6;
                this.this$1.this$0.finishPreviewFragment();
                return;
            }

            @Override // org.telegram.ui.Components.SearchViewPager.ChatPreviewDelegate
            public void move(float r3) {
                Point r0 = AndroidUtilities.displaySize;
                if (r0.x <= r0.y) goto L6;
                this.this$1.this$0.movePreviewFragment(r3);
                return;
            }

            @Override // org.telegram.ui.Components.SearchViewPager.ChatPreviewDelegate
            public void startChatPreview(RecyclerListView r1, DialogCell r2) {
                TopicsFragment.m20990$$Nest$mshowChatPreview(this.this$1.this$0, r2);
            }
        }

        /* renamed from: org.telegram.ui.TopicsFragment$MessagesSearchContainer$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.OnScrollListener {
            final /* synthetic */ MessagesSearchContainer this$1;
            final /* synthetic */ TopicsFragment val$this$0;

            public AnonymousClass2(MessagesSearchContainer r1, TopicsFragment r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r3, int r4, int r5) {
                super.onScrolled(r3, r4, r5);
                MessagesSearchContainer r32 = this.this$1;
                if (r32.canLoadMore == false) goto L7;
                int r33 = r32.layoutManager.findLastVisibleItemPosition() + 5;
                MessagesSearchContainer r0 = this.this$1;
                if (r33 < r0.rowCount) goto L7;
                MessagesSearchContainer.m20999$$Nest$mloadMessages(r0, r0.searchString);
            L7:
                TopicsFragment r2 = this.this$1.this$0;
                if (r2.searching == false) goto L13;
                if (r4 != 0) goto L11;
                if (r5 != 0) goto L11;
                return;
            L11:
                AndroidUtilities.hideKeyboard(TopicsFragment.m20958$$Nest$fgetsearchItem(r2).getSearchField());
                return;
            }
        }

        public class Item {
            int filterIndex;
            final /* synthetic */ MessagesSearchContainer this$1;
            private final int type;

            /* renamed from: -$$Nest$fgettype, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m21001$$Nest$fgettype(Item r0) {
                return r0.type;
            }

            private Item(MessagesSearchContainer r1, int r2) {
                this.this$1 = r1;
                this.type = r2;
            }

            public /* synthetic */ Item(MessagesSearchContainer r1, int r2, TopicsFragmentIA r3) {
                this(r1, r2);
            }
        }

        public class SearchAdapter extends RecyclerListView.SelectionAdapter {
            final /* synthetic */ MessagesSearchContainer this$1;

            private SearchAdapter(MessagesSearchContainer r1) {
                this.this$1 = r1;
            }

            public /* synthetic */ SearchAdapter(MessagesSearchContainer r1, TopicsFragmentIA r2) {
                this(r1);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                MessagesSearchContainer r1 = this.this$1;
                if (r1.isLoading == false) goto L7;
                return 0;
            L7:
                return r1.rowCount;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r2) {
                MessagesSearchContainer r1 = this.this$1;
                if (r2 != r1.messagesHeaderRow) goto L5;
                return 1;
            L5:
                if (r2 != r1.topicsHeaderRow) goto L8;
                return 1;
            L8:
                if (r2 < r1.topicsStartRow) goto L14;
                if (r2 >= r1.topicsEndRow) goto L14;
                return 2;
            L14:
                if (r2 >= r1.messagesStartRow) goto L16;
                return 0;
            L16:
                if (r2 >= r1.messagesEndRow) goto L23;
                return 3;
            L23:
                return 0;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r2) {
                if (r2.getItemViewType() != 3) goto L5;
                return true;
            L5:
                if (r2.getItemViewType() == 2) goto L11;
                return false;
            L11:
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r12, int r13) {
                if (getItemViewType(r13) != 1) goto L10;
                GraySectionCell r0 = (GraySectionCell) r12.itemView;
                if (r13 != this.this$1.topicsHeaderRow) goto L8;
                r0.setText(LocaleController.getString(R.string.Topics));
            L8:
                if (r13 != this.this$1.messagesHeaderRow) goto L10;
                r0.setText(LocaleController.getString(R.string.SearchMessages));
            L10:
                boolean r3 = false;
                if (getItemViewType(r13) != 2) goto L18;
                MessagesSearchContainer r02 = this.this$1;
                TLRPC.TL_forumTopic r03 = r02.searchResultTopics.get(r13 - r02.topicsStartRow);
                TopicSearchCell r2 = (TopicSearchCell) r12.itemView;
                r2.setTopic(r03);
                if (r13 == (this.this$1.topicsEndRow - 1)) goto L15;
                boolean r04 = true;
            L16:
                r2.drawDivider = r04;
                goto L18
            L15:
                r04 = false;
            L18:
                if (getItemViewType(r13) != 3) goto L31;
                MessagesSearchContainer r05 = this.this$1;
                MessageObject r8 = r05.searchResultMessages.get(r13 - r05.messagesStartRow);
                TopicDialogCell r4 = (TopicDialogCell) r12.itemView;
                MessagesSearchContainer r122 = this.this$1;
                if (r13 == (r122.messagesEndRow - 1)) goto L22;
                r3 = true;
            L22:
                r4.drawDivider = r3;
                long r123 = MessageObject.getTopicId(TopicsFragment.access$2600(r122.this$0), r8.messageOwner, true);
                if (r123 != 0) goto L25;
                r123 = 1;
            L25:
                TLRPC.TL_forumTopic r5 = TopicsFragment.m20963$$Nest$fgettopicsController(this.this$1.this$0).findTopic(this.this$1.this$0.chatId, r123);
                if (r5 != null) goto L29;
                FileLog.d("cant find topic " + r123);
                return;
            L29:
                r4.setForumTopic(r5, r8.getDialogId(), r8, false, false);
                r4.setTopicIcon(r5);
                return;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v3, types: [org.telegram.ui.Cells.DialogCell, org.telegram.ui.TopicsFragment$TopicDialogCell] */
            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
                if (r9 != 1) goto L5;
                FrameLayout r1 = new GraySectionCell(r8.getContext());
            L13:
                r1.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
                return new RecyclerListView.Holder(r1);
            L5:
                if (r9 != 2) goto L7;
                r1 = new TopicSearchCell(r8.getContext());
                goto L13
            L7:
                if (r9 != 3) goto L9;
                ?? r12 = new TopicDialogCell(this.this$1.this$0, null, r8.getContext(), false, true);
                r12.inPreviewMode = TopicsFragment.access$2500(this.this$1.this$0);
                r1 = r12;
                goto L13
            L9:
                GlShader$$ExternalSyntheticBUOutline1.m("unsupported view type");
                return null;
            }
        }

        public class ViewPagerAdapter extends ViewPagerFixed.Adapter {
            ArrayList<Item> items;
            final /* synthetic */ MessagesSearchContainer this$1;

            /* renamed from: org.telegram.ui.TopicsFragment$MessagesSearchContainer$ViewPagerAdapter$1, reason: invalid class name */
            public class AnonymousClass1 extends RecyclerView.OnScrollListener {
                final /* synthetic */ ViewPagerAdapter this$2;

                public AnonymousClass1(ViewPagerAdapter r1) {
                    this.this$2 = r1;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView r1, int r2, int r3) {
                    super.onScrolled(r1, r2, r3);
                }
            }

            /* renamed from: org.telegram.ui.TopicsFragment$MessagesSearchContainer$ViewPagerAdapter$2, reason: invalid class name */
            public class AnonymousClass2 extends RecyclerView.OnScrollListener {
                final /* synthetic */ ViewPagerAdapter this$2;

                public AnonymousClass2(ViewPagerAdapter r1) {
                    this.this$2 = r1;
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView r1, int r2, int r3) {
                    super.onScrolled(r1, r2, r3);
                }
            }

            public ViewPagerAdapter(MessagesSearchContainer r5) {
                this.this$1 = r5;
                ArrayList<Item> r0 = new ArrayList();
                this.items = r0;
                r0.add(new Item(r5, 0, null));
                Item r02 = new Item(r5, 2, null);
                r02.filterIndex = 0;
                this.items.add(r02);
                Item r03 = new Item(r5, 2, null);
                r03.filterIndex = 1;
                this.items.add(r03);
                Item r04 = new Item(r5, 2, null);
                r04.filterIndex = 2;
                this.items.add(r04);
                Item r05 = new Item(r5, 2, null);
                r05.filterIndex = 3;
                this.items.add(r05);
                Item r06 = new Item(r5, 2, null);
                r06.filterIndex = 4;
                this.items.add(r06);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public void bindView(View r2, int r3, int r4) {
                MessagesSearchContainer r1 = this.this$1;
                MessagesSearchContainer.m21000$$Nest$msearch(r1, r2, r3, r1.searchString, true);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public View createView(int r3) {
                if (r3 != 1) goto L7;
                return this.this$1.searchContainer;
            L7:
                if (r3 != 2) goto L10;
                TopicsFragment r0 = this.this$1.this$0;
                SearchDownloadsContainer r32 = new SearchDownloadsContainer(r0, TopicsFragment.access$1900(r0));
                r32.recyclerListView.addOnScrollListener(new AnonymousClass1(this));
                r32.setUiCallback(this.this$1);
                return r32;
            L10:
                FilteredSearchView r33 = new FilteredSearchView(this.this$1.this$0);
                r33.setChatPreviewDelegate(this.this$1.chatPreviewDelegate);
                r33.setUiCallback(this.this$1);
                r33.recyclerListView.addOnScrollListener(new AnonymousClass2(this));
                return r33;
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemCount() {
                return this.items.size();
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public /* bridge */ /* synthetic */ CharSequence getItemTitle(int r1) {
                return getItemTitle(r1);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public String getItemTitle(int r3) {
                if (Item.m21001$$Nest$fgettype(this.items.get(r3)) != 0) goto L7;
                return LocaleController.getString(R.string.SearchMessages);
            L7:
                if (Item.m21001$$Nest$fgettype(this.items.get(r3)) != 1) goto L11;
                return LocaleController.getString(R.string.DownloadsTabs);
            L11:
                return FiltersView.filters[this.items.get(r3).filterIndex].getTitle();
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemViewType(int r3) {
                if (Item.m21001$$Nest$fgettype(this.items.get(r3)) != 0) goto L6;
                return 1;
            L6:
                if (Item.m21001$$Nest$fgettype(this.items.get(r3)) != 1) goto L10;
                return 2;
            L10:
                return Item.m21001$$Nest$fgettype(this.items.get(r3)) + r3;
            }
        }

        public static /* synthetic */ void $r8$lambda$Q47Uc8jFAbkSODfGSLArtjxZVwM(MessagesSearchContainer r0, String r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadMessages$3(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$SJ3ef0Na0OSaafU-Gmp-Rj6fDwo, reason: not valid java name */
        public static /* synthetic */ void m20998$r8$lambda$SJ3ef0Na0OSaafUGmpRj6fDwo(MessagesSearchContainer r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$U0w8HjoskpC7iZUeSX9rvp1zlFA(MessagesSearchContainer r0, String r1) {
            r0.lambda$searchMessages$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$qBqM9sEoUUobxAnh5bgrRF1_qW8(MessagesSearchContainer r0, String r1, TLObject r2) {
            r0.lambda$loadMessages$2(r1, r2);
        }

        /* renamed from: -$$Nest$mloadMessages, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m20999$$Nest$mloadMessages(MessagesSearchContainer r0, String r1) {
            r0.loadMessages(r1);
        }

        /* renamed from: -$$Nest$msearch, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21000$$Nest$msearch(MessagesSearchContainer r0, View r1, int r2, String r3, boolean r4) {
            r0.search(r1, r2, r3, r4);
        }

        public MessagesSearchContainer(TopicsFragment r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            this.searchString = "empty";
            this.searchResultTopics = new ArrayList();
            this.searchResultMessages = new ArrayList();
            this.selectedItems = new ArrayList();
            this.searchContainer = new FrameLayout(r5);
            this.chatPreviewDelegate = new AnonymousClass1(this, r4);
            RecyclerListView r0 = new RecyclerListView(r5);
            this.recyclerView = r0;
            SearchAdapter r1 = new SearchAdapter(this, null);
            this.searchAdapter = r1;
            r0.setAdapter(r1);
            RecyclerListView r02 = this.recyclerView;
            LinearLayoutManager r12 = new LinearLayoutManager(r5);
            this.layoutManager = r12;
            r02.setLayoutManager(r12);
            this.recyclerView.setOnItemClickListener(new TopicsFragment$MessagesSearchContainer$$ExternalSyntheticLambda0(this));
            this.recyclerView.setOnScrollListener(new AnonymousClass2(this, r4));
            FlickerLoadingView r42 = new FlickerLoadingView(r5);
            this.flickerLoadingView = r42;
            r42.setViewType(7);
            this.flickerLoadingView.showDate(false);
            this.flickerLoadingView.setUseHeaderOffset(true);
            StickerEmptyView r43 = new StickerEmptyView(r5, this.flickerLoadingView, 1);
            this.emptyView = r43;
            r43.title.setText(LocaleController.getString(R.string.NoResult));
            this.emptyView.subtitle.setVisibility(8);
            this.emptyView.setVisibility(8);
            this.emptyView.addView(this.flickerLoadingView, 0);
            this.emptyView.setAnimateLayoutChange(true);
            this.recyclerView.setEmptyView(this.emptyView);
            this.recyclerView.setAnimateEmptyView(true, 0);
            this.searchContainer.addView(this.emptyView);
            this.searchContainer.addView(this.recyclerView);
            updateRows();
            RecyclerItemsEnterAnimator r44 = new RecyclerItemsEnterAnimator(this.recyclerView, true);
            this.itemsEnterAnimator = r44;
            this.recyclerView.setItemsEnterAnimator(r44);
            ViewPagerAdapter r45 = new ViewPagerAdapter(this);
            this.viewPagerAdapter = r45;
            setAdapter(r45);
        }

        private /* synthetic */ void lambda$loadMessages$2(String r8, TLObject r9) {
            if (r8.equals(this.searchString) == false) goto L23;
            int r0 = this.rowCount;
            boolean r1 = false;
            this.messagesIsLoading = false;
            this.isLoading = false;
            if ((r9 instanceof TLRPC.messages_Messages) == false) goto L16;
            TLRPC.messages_Messages r92 = (TLRPC.messages_Messages) r9;
            int r2 = 0;
        L8:
            if (r2 >= r92.messages.size()) goto L10;
            MessageObject r5 = new MessageObject(TopicsFragment.access$2700(this.this$0), r92.messages.get(r2), false, false);
            r5.setQuery(r8);
            this.searchResultMessages.add(r5);
            r2 = r2 + 1;
            goto L8
        L10:
            updateRows();
            if (this.searchResultMessages.size() < r92.count) goto L13;
        L15:
            this.canLoadMore = r1;
        L18:
            if (this.rowCount != 0) goto L20;
            this.emptyView.showProgress(this.isLoading, true);
        L20:
            this.itemsEnterAnimator.showItemsAnimated(r0);
            return;
        L13:
            if (r92.messages.isEmpty() == true) goto L15;
            r1 = true;
            goto L15
        L16:
            this.canLoadMore = false;
            goto L18
        }

        private /* synthetic */ void lambda$loadMessages$3(String r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new TopicsFragment$MessagesSearchContainer$$ExternalSyntheticLambda3(this, r1, r2));
        }

        private /* synthetic */ void lambda$new$0(View r3, int r4) {
            if ((r3 instanceof TopicSearchCell) == false) goto L7;
            TopicsFragment r2 = this.this$0;
            ForumUtilities.openTopic(r2, r2.chatId, ((TopicSearchCell) r3).getTopic(), 0);
            return;
        L7:
            if ((r3 instanceof TopicDialogCell) == false) goto L10;
            TopicDialogCell r32 = (TopicDialogCell) r3;
            TopicsFragment r22 = this.this$0;
            ForumUtilities.openTopic(r22, r22.chatId, r32.forumTopic, r32.getMessageId());
            return;
        }

        private /* synthetic */ void lambda$searchMessages$1(String r6) {
            String r0 = r6.trim().toLowerCase();
            ArrayList r1 = new ArrayList();
            int r3 = 0;
        L4:
            if (r3 >= this.this$0.forumTopics.size()) goto L11;
            if (this.this$0.forumTopics.get(r3).topic == null) goto L10;
            if (this.this$0.forumTopics.get(r3).topic.title.toLowerCase().contains(r0) == false) goto L10;
            r1.add(this.this$0.forumTopics.get(r3).topic);
            this.this$0.forumTopics.get(r3).topic.searchQuery = r0;
        L10:
            r3 = r3 + 1;
            goto L4
        L11:
            this.searchResultTopics.clear();
            this.searchResultTopics.addAll(r1);
            updateRows();
            if (this.searchResultTopics.isEmpty() == true) goto L14;
            this.isLoading = false;
            this.itemsEnterAnimator.showItemsAnimated(0);
        L14:
            loadMessages(r6);
        }

        private void loadMessages(String r5) {
            if (this.messagesIsLoading == false) goto L5;
            return;
        L5:
            TLRPC.TL_messages_search r0 = new TLRPC.TL_messages_search();
            r0.peer = this.this$0.getMessagesController().getInputPeer(-this.this$0.chatId);
            r0.filter = new TLRPC.TL_inputMessagesFilterEmpty();
            r0.limit = 20;
            r0.q = r5;
            if (this.searchResultMessages.isEmpty() == true) goto L8;
            ArrayList<MessageObject> r1 = this.searchResultMessages;
            r0.offset_id = r1.get(r1.size() - 1).getId();
        L8:
            this.messagesIsLoading = true;
            ConnectionsManager.getInstance(TopicsFragment.access$2400(this.this$0)).sendRequest(r0, new TopicsFragment$MessagesSearchContainer$$ExternalSyntheticLambda2(this, r5));
        }

        private void search(View r14, int r15, String r16, boolean r17) {
            this.searchString = r16;
            if (r14 != this.searchContainer) goto L7;
            searchMessages(r16);
            return;
        L7:
            if ((r14 instanceof FilteredSearchView) == false) goto L11;
            FilteredSearchView r0 = (FilteredSearchView) r14;
            r0.setKeyboardHeight(this.keyboardSize, false);
            Item r142 = this.viewPagerAdapter.items.get(r15);
            r0.search(-this.this$0.chatId, 0, 0, 0, FiltersView.filters[r142.filterIndex], false, r16, r17);
            return;
        L11:
            if ((r14 instanceof SearchDownloadsContainer) == false) goto L14;
            SearchDownloadsContainer r143 = (SearchDownloadsContainer) r14;
            r143.setKeyboardHeight(this.keyboardSize, false);
            r143.search(r16);
            return;
        }

        private void searchMessages(String r4) {
            Runnable r0 = this.searchRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.searchRunnable = null;
        L5:
            int r02 = 0;
            this.messagesIsLoading = false;
            this.canLoadMore = false;
            this.searchResultTopics.clear();
            this.searchResultMessages.clear();
            updateRows();
            if (TextUtils.isEmpty(r4) == false) goto L16;
            this.isLoading = false;
            this.searchResultTopics.clear();
        L9:
            if (r02 >= this.this$0.forumTopics.size()) goto L14;
            if (this.this$0.forumTopics.get(r02).topic == null) goto L13;
            this.searchResultTopics.add(this.this$0.forumTopics.get(r02).topic);
            this.this$0.forumTopics.get(r02).topic.searchQuery = null;
        L13:
            r02 = r02 + 1;
            goto L9
        L14:
            updateRows();
            return;
        L16:
            updateRows();
            this.isLoading = true;
            this.emptyView.showProgress(true, true);
            TopicsFragment$MessagesSearchContainer$$ExternalSyntheticLambda1 r03 = new TopicsFragment$MessagesSearchContainer$$ExternalSyntheticLambda1(this, r4);
            this.searchRunnable = r03;
            AndroidUtilities.runOnUIThread(r03, 200);
        }

        private void updateRows() {
            this.topicsHeaderRow = -1;
            this.topicsStartRow = -1;
            this.topicsEndRow = -1;
            this.messagesHeaderRow = -1;
            this.messagesStartRow = -1;
            this.messagesEndRow = -1;
            this.rowCount = 0;
            if (this.searchResultTopics.isEmpty() == true) goto L6;
            int r0 = this.rowCount;
            int r1 = r0 + 1;
            this.rowCount = r1;
            this.topicsHeaderRow = r0;
            this.topicsStartRow = r1;
            int r12 = r1 + this.searchResultTopics.size();
            this.rowCount = r12;
            this.topicsEndRow = r12;
        L6:
            if (this.searchResultMessages.isEmpty() == true) goto L8;
            int r02 = this.rowCount;
            int r13 = r02 + 1;
            this.rowCount = r13;
            this.messagesHeaderRow = r02;
            this.messagesStartRow = r13;
            int r14 = r13 + this.searchResultMessages.size();
            this.rowCount = r14;
            this.messagesEndRow = r14;
        L8:
            this.searchAdapter.notifyDataSetChanged();
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public boolean actionModeShowing() {
            return TopicsFragment.access$2100(this.this$0).isActionModeShowed();
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public void goToMessage(MessageObject r7) {
            Bundle r0 = new Bundle();
            long r1 = r7.getDialogId();
            if (DialogObject.isEncryptedDialog(r1) == false) goto L6;
            r0.putInt("enc_id", DialogObject.getEncryptedChatId(r1));
        L14:
            r0.putInt("message_id", r7.getId());
            this.this$0.presentFragment(new ChatActivity(r0));
            return;
        L6:
            if (DialogObject.isUserDialog(r1) == false) goto L8;
            r0.putLong("user_id", r1);
            goto L14
        L8:
            TLRPC.Chat r3 = AccountInstance.getInstance(TopicsFragment.access$2000(this.this$0)).getMessagesController().getChat(Long.valueOf(-r1));
            if (r3 != null) goto L11;
        L13:
            r0.putLong("chat_id", -r1);
            goto L14
        L11:
            if (r3.migrated_to == null) goto L13;
            r0.putLong("migrated_to", r1);
            r1 = -r3.migrated_to.channel_id;
            goto L13
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public boolean isSelected(FilteredSearchView.MessageHashId r7) {
            if (r7 != null) goto L5;
            return false;
        L5:
            int r1 = 0;
        L7:
            if (r1 >= this.selectedItems.size()) goto L17;
            MessageObject r2 = this.selectedItems.get(r1);
            if (r2 == null) goto L16;
            if (r2.getId() != r7.messageId) goto L16;
            if (r2.getDialogId() != r7.dialogId) goto L16;
            return true;
        L16:
            r1 = r1 + 1;
            goto L7
        L17:
            return false;
        }

        public void setSearchString(String r4) {
            if (this.searchString.equals(r4) == false) goto L5;
            return;
        L5:
            search(this.viewPages[0], getCurrentPosition(), r4, false);
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public void showActionMode() {
            TopicsFragment.access$2300(this.this$0).showActionMode();
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public void toggleItemSelection(MessageObject r1, View r2, int r3) {
            if (this.selectedItems.remove(r1) == true) goto L6;
            this.selectedItems.add(r1);
        L6:
            if (this.selectedItems.isEmpty() == false) goto L9;
            TopicsFragment.access$2200(this.this$0).hideActionMode();
            return;
        }
    }

    /* loaded from: classes6.dex */
    public interface OnTopicSelectedListener {
        void onTopicSelected(TLRPC.TL_forumTopic r1);
    }

    /* loaded from: classes6.dex */
    public class TopicDialogCell extends DialogCell {
        private AnimatedEmojiDrawable animatedEmojiDrawable;
        boolean attached;
        private boolean closed;
        private TLRPC.TL_forumTopic currentTopic;
        public boolean drawDivider;
        private Drawable forumIcon;
        private Boolean hidden;
        private ValueAnimator hiddenAnimator;
        private float hiddenT;
        private boolean isGeneral;
        public int position;
        final /* synthetic */ TopicsFragment this$0;

        public static /* synthetic */ void $r8$lambda$AixhsEp_MYdvdwtZcCk_SJl0WSg(TopicDialogCell r0, ValueAnimator r1) {
            r0.lambda$updateHidden$0(r1);
        }

        /* renamed from: -$$Nest$fgetcurrentTopic, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_forumTopic m21002$$Nest$fgetcurrentTopic(TopicDialogCell r0) {
            return r0.currentTopic;
        }

        public TopicDialogCell(TopicsFragment r1, DialogsActivity r2, Context r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
            this.position = -1;
            this.drawAvatar = false;
            if (r1.isInPreviewMode() == false) goto L5;
            int r12 = 11;
        L6:
            this.messagePaddingStart = r12;
            this.chekBoxPaddingTop = 24.0f;
            this.heightDefault = 64;
            this.heightThreeLines = 76;
            this.forbidVerified = true;
            return;
        L5:
            r12 = 50;
            goto L6
        }

        private /* synthetic */ void lambda$updateHidden$0(ValueAnimator r1) {
            this.hiddenT = ((Float) r1.getAnimatedValue()).floatValue();
            setHiddenT();
        }

        private void setHiddenT() {
            Drawable r0 = this.forumIcon;
            if ((r0 instanceof ForumUtilities.GeneralTopicDrawable) == false) goto L5;
            ((ForumUtilities.GeneralTopicDrawable) r0).setColor(ColorUtils.blendARGB(this.this$0.getThemedColor(Theme.key_chats_archivePullDownBackground), this.this$0.getThemedColor(Theme.key_avatar_background2Saved), this.hiddenT));
        L5:
            Drawable[] r02 = this.topicIconInName;
            if (r02 == null) goto L10;
            Drawable r03 = r02[0];
            if ((r03 instanceof ForumUtilities.GeneralTopicDrawable) == false) goto L10;
            ((ForumUtilities.GeneralTopicDrawable) r03).setColor(ColorUtils.blendARGB(this.this$0.getThemedColor(Theme.key_chats_archivePullDownBackground), this.this$0.getThemedColor(Theme.key_avatar_background2Saved), this.hiddenT));
        L10:
            invalidate();
        }

        private void updateHidden(boolean r4, boolean r5) {
            if (this.hidden != null) goto L5;
            r5 = false;
        L5:
            ValueAnimator r0 = this.hiddenAnimator;
            if (r0 == null) goto L8;
            r0.cancel();
            this.hiddenAnimator = null;
        L8:
            this.hidden = Boolean.valueOf(r4);
            float r02 = 0.0f;
            if (r5 == false) goto L15;
            float r52 = this.hiddenT;
            if (r4 == false) goto L13;
            r02 = 1.0f;
        L13:
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r52, r02});
            this.hiddenAnimator = r42;
            r42.addUpdateListener(new TopicsFragment$TopicDialogCell$$ExternalSyntheticLambda0(this));
            this.hiddenAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            this.hiddenAnimator.start();
            return;
        L15:
            if (r4 == false) goto L17;
            r02 = 1.0f;
        L17:
            this.hiddenT = r02;
            setHiddenT();
        }

        @Override // org.telegram.ui.Cells.DialogCell
        public void buildLayout() {
            super.buildLayout();
            setHiddenT();
        }

        @Override // org.telegram.ui.Cells.DialogCell
        public boolean drawLock2() {
            return this.closed;
        }

        @Override // org.telegram.ui.Cells.DialogCell, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // org.telegram.ui.Cells.DialogCell, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.attached = true;
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 == null) goto L6;
            r0.addView(this);
            return;
        }

        @Override // org.telegram.ui.Cells.DialogCell, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.attached = false;
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 == null) goto L6;
            r0.removeView(this);
            return;
        }

        @Override // org.telegram.ui.Cells.DialogCell, android.view.View
        public void onDraw(Canvas r9) {
            if (this.this$0.getMessagesController().isMonoForum(-this.this$0.chatId) == false) goto L7;
            super.onDraw(r9);
            return;
        L7:
            if (this.inPreviewMode == false) goto L11;
            CheckBox2 r2 = this.checkBox;
            if (r2 == null) goto L11;
            float r22 = r2.getProgress() * AndroidUtilities.dp(30.0f);
        L12:
            this.xOffset = r22;
            r9.save();
            float r23 = this.xOffset;
            int r3 = -AndroidUtilities.dp(4.0f);
            this.translateY = r3;
            r9.translate(r23, r3);
            r9.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            super.onDraw(r9);
            r9.restore();
            r9.save();
            r9.translate(this.translationX, 0.0f);
            if (this.drawDivider == false) goto L23;
            if (this.fullSeparator == false) goto L17;
            int r24 = 0;
        L18:
            boolean r32 = LocaleController.isRTL;
            float r4 = this.translationX;
            if (r32 == false) goto L21;
            int r33 = r24;
            r9.drawLine(0.0f - r4, getMeasuredHeight() - 1, getMeasuredWidth() - r33, getMeasuredHeight() - 1, Theme.dividerPaint);
            goto L23
        L21:
            r9.drawLine(r24 - r4, getMeasuredHeight() - 1, getMeasuredWidth(), getMeasuredHeight() - 1, Theme.dividerPaint);
            goto L23
        L17:
            r24 = AndroidUtilities.dp(this.messagePaddingStart);
        L23:
            if (this.isGeneral == false) goto L29;
            PullForegroundDrawable r25 = this.archivedChatsDrawable;
            if (r25 == null) goto L29;
            if (r25.outProgress != 0.0f) goto L29;
        L44:
            r9.restore();
            return;
        L29:
            if (this.animatedEmojiDrawable == null) goto L31;
        L32:
            int r34 = AndroidUtilities.dp(10.0f);
            int r26 = AndroidUtilities.dp(10.0f);
            int r42 = AndroidUtilities.dp(28.0f);
            AnimatedEmojiDrawable r5 = this.animatedEmojiDrawable;
            if (r5 != null) goto L35;
            boolean r52 = LocaleController.isRTL;
            Drawable r6 = this.forumIcon;
            if (r52 == false) goto L42;
            r6.setBounds((getWidth() - r34) - r42, r26, getWidth() - r34, r42 + r26);
        L43:
            this.forumIcon.draw(r9);
            goto L44
        L42:
            r6.setBounds(r34, r26, r34 + r42, r42 + r26);
            goto L43
        L35:
            if (LocaleController.isRTL == false) goto L37;
            r5.setBounds((getWidth() - r34) - r42, r26, getWidth() - r34, r42 + r26);
        L38:
            this.animatedEmojiDrawable.draw(r9);
            goto L44
        L37:
            r5.setBounds(r34, r26, r34 + r42, r42 + r26);
            goto L38
        L31:
            if (this.forumIcon == null) goto L44;
        L11:
            r22 = 0.0f;
            goto L12
        }

        public void setAnimatedEmojiDrawable(AnimatedEmojiDrawable r3) {
            AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
            if (r0 == r3) goto L19;
            if (r0 != null) goto L7;
        L9:
            if (r3 == null) goto L11;
            r3.setColorFilter(Theme.chat_animatedEmojiTextColorFilter);
        L11:
            this.animatedEmojiDrawable = r3;
            if (r3 != null) goto L14;
            return;
        L14:
            if (this.attached == false) goto L18;
            r3.addView(this);
            return;
        L18:
            return;
        L7:
            if (this.attached == false) goto L9;
            r0.removeView(this);
            goto L9
        }

        public void setForumIcon(Drawable r1) {
            this.forumIcon = r1;
        }

        public void setTopicIcon(TLRPC.TL_forumTopic r8) {
            this.currentTopic = r8;
            boolean r0 = false;
            if (r8 != null) goto L5;
        L7:
            boolean r2 = false;
        L8:
            this.closed = r2;
            if (this.inPreviewMode == false) goto L16;
            if (r8 != null) goto L12;
        L14:
            boolean r22 = false;
        L15:
            updateHidden(r22, true);
            goto L16
        L12:
            if (r8.hidden == false) goto L14;
            r22 = true;
        L16:
            if (r8 != null) goto L18;
        L20:
            boolean r23 = false;
        L21:
            this.isGeneral = r23;
            if (r8 == null) goto L31;
            if (this == TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0)) goto L31;
            if (r8.hidden == false) goto L28;
            this.overrideSwipeAction = true;
            this.overrideSwipeActionBackgroundColorKey = Theme.key_chats_archivePinBackground;
            this.overrideSwipeActionRevealBackgroundColorKey = Theme.key_chats_archiveBackground;
            this.overrideSwipeActionStringKey = "Unhide";
            this.overrideSwipeActionStringId = R.string.Unhide;
            this.overrideSwipeActionDrawable = Theme.dialogs_unpinArchiveDrawable;
        L29:
            invalidate();
            goto L31
        L28:
            this.overrideSwipeAction = true;
            this.overrideSwipeActionBackgroundColorKey = Theme.key_chats_archiveBackground;
            this.overrideSwipeActionRevealBackgroundColorKey = Theme.key_chats_archivePinBackground;
            this.overrideSwipeActionStringKey = "Hide";
            this.overrideSwipeActionStringId = R.string.Hide;
            this.overrideSwipeActionDrawable = Theme.dialogs_pinArchiveDrawable;
        L31:
            if (this.inPreviewMode == false) goto L34;
            return;
        L34:
            if (r8 != null) goto L36;
        L38:
            if (r8 != null) goto L40;
        L50:
            setAnimatedEmojiDrawable(null);
            setForumIcon(ForumUtilities.createTopicDrawable(r8, false));
        L51:
            if (r8 != null) goto L53;
        L55:
            updateHidden(r0, true);
            buildLayout();
            return;
        L53:
            if (r8.hidden == false) goto L55;
            r0 = true;
            goto L55
        L40:
            if (r8.icon_emoji_id == 0) goto L50;
            setForumIcon(null);
            AnimatedEmojiDrawable r24 = this.animatedEmojiDrawable;
            if (r24 == null) goto L46;
            if (r24.getDocumentId() == r8.icon_emoji_id) goto L51;
        L46:
            if (TopicsFragment.m20949$$Nest$fgetopenedForForward(this.this$0) == false) goto L48;
            int r3 = 13;
        L49:
            setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(r3, TopicsFragment.access$1800(this.this$0), r8.icon_emoji_id));
            goto L51
        L48:
            r3 = 10;
            goto L49
        L36:
            if (r8.id != 1) goto L38;
            setAnimatedEmojiDrawable(null);
            setForumIcon(ForumUtilities.createGeneralTopicDrawable(getContext(), 1.0f, this.this$0.getThemedColor(Theme.key_chat_inMenu), false));
            goto L51
        L18:
            if (r8.id != 1) goto L20;
            r23 = true;
            goto L21
        L5:
            if (r8.closed == false) goto L7;
            r2 = true;
            goto L8
        }
    }

    /* loaded from: classes6.dex */
    public class TopicsRecyclerView extends BlurredRecyclerView {
        private boolean firstLayout;
        private boolean ignoreLayout;
        Paint paint;
        RectF rectF;
        final /* synthetic */ TopicsFragment this$0;
        private float viewOffset;

        /* renamed from: org.telegram.ui.TopicsFragment$TopicsRecyclerView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ TopicsRecyclerView this$1;

            public AnonymousClass1(TopicsRecyclerView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$1.setScrollEnabled(true);
            }
        }

        /* renamed from: $r8$lambda$LdM_j6czKR9lO3-jBRVFsCayyUc, reason: not valid java name */
        public static /* synthetic */ void m21003$r8$lambda$LdM_j6czKR9lO3jBRVFsCayyUc(TopicsRecyclerView r0, ValueAnimator r1) {
            r0.lambda$onTouchEvent$0(r1);
        }

        /* renamed from: -$$Nest$msetArchiveHidden, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21004$$Nest$msetArchiveHidden(TopicsRecyclerView r0, boolean r1, DialogCell r2) {
            r0.setArchiveHidden(r1, r2);
        }

        public TopicsRecyclerView(TopicsFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.firstLayout = true;
            this.paint = new Paint();
            this.rectF = new RectF();
            this.useLayoutPositionOnClick = true;
            this.additionalClipBottom = AndroidUtilities.dp(200.0f);
        }

        private void checkIfAdapterValid() {
            RecyclerView.Adapter r0 = getAdapter();
            if (TopicsFragment.m20946$$Nest$fgetlastItemsCount(this.this$0) != r0.getItemCount()) goto L5;
            return;
        L5:
            if (TopicsFragment.m20934$$Nest$fgetforumTopicsListFrozen(this.this$0) == true) goto L9;
            this.ignoreLayout = true;
            r0.notifyDataSetChanged();
            this.ignoreLayout = false;
            return;
        }

        private boolean drawMovingViewsOverlayed() {
            if (getItemAnimator() != null) goto L5;
            return false;
        L5:
            if (getItemAnimator().isRunning() == true) goto L7;
            return false;
        L7:
            if (TopicsFragment.m20931$$Nest$fgetdialogRemoveFinished(this.this$0) == 0) goto L9;
            return true;
        L9:
            if (TopicsFragment.m20930$$Nest$fgetdialogInsertFinished(this.this$0) == 0) goto L11;
            return true;
        L11:
            if (TopicsFragment.m20929$$Nest$fgetdialogChangeFinished(this.this$0) == 0) goto L19;
            return true;
        L19:
            return false;
        }

        private /* synthetic */ void lambda$onTouchEvent$0(ValueAnimator r1) {
            setViewsOffset(((Float) r1.getAnimatedValue()).floatValue());
        }

        private void setArchiveHidden(boolean r2, DialogCell r3) {
            TopicsFragment.m20971$$Nest$fputhiddenShown(this.this$0, r2);
            if (TopicsFragment.m20939$$Nest$fgethiddenShown(this.this$0) == true) goto L6;
            if (r3 == null) goto L10;
            TopicsFragment.m20967$$Nest$fputdisableActionBarScrolling(this.this$0, true);
            this.this$0.layoutManager.scrollToPositionWithOffset(1, 0);
            updatePullState();
        L10:
            if (TopicsFragment.m20932$$Nest$fgetemptyView(this.this$0) == null) goto L13;
            TopicsFragment.m20932$$Nest$fgetemptyView(this.this$0).forceLayout();
            return;
        L13:
            return;
        L6:
            this.this$0.layoutManager.scrollToPositionWithOffset(0, 0);
            updatePullState();
            if (r3 == null) goto L10;
            r3.resetPinnedArchiveState();
            r3.invalidate();
            goto L10
        }

        private void updatePullState() {
            TopicsFragment r0 = this.this$0;
            boolean r2 = false;
            if (TopicsFragment.m20939$$Nest$fgethiddenShown(r0) == true) goto L5;
            int r1 = 2;
        L6:
            TopicsFragment.m20975$$Nest$fputpullViewState(r0, r1);
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) == null) goto L13;
            PullForegroundDrawable r02 = TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0);
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) == 0) goto L11;
            r2 = true;
        L11:
            r02.setWillDraw(r2);
            return;
        L13:
            return;
        L5:
            r1 = 0;
            goto L6
        }

        @Override // android.view.ViewGroup
        public void addView(View r1, int r2, ViewGroup.LayoutParams r3) {
            super.addView(r1, r2, r3);
            r1.setTranslationY(this.viewOffset);
            r1.setTranslationX(0.0f);
            r1.setAlpha(1.0f);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(View r1) {
            if ((r1 instanceof HeaderCell) == true) goto L5;
            return true;
        L5:
            if (r1.isClickable() == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            if (TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0) == null) goto L5;
            r10.save();
            r10.translate(TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0).getLeft(), TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0).getY());
            TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0).draw(r10);
            r10.restore();
        L5:
            super.dispatchDraw(r10);
            if (drawMovingViewsOverlayed() == false) goto L30;
            this.paint.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
            int r0 = 0;
        L9:
            if (r0 >= getChildCount()) goto L25;
            View r1 = getChildAt(r0);
            if ((r1 instanceof DialogCell) == false) goto L15;
            if (((DialogCell) r1).isMoving() == false) goto L15;
        L19:
            if (r1.getAlpha() == 1.0f) goto L21;
            this.rectF.set(r1.getX(), r1.getY(), r1.getX() + r1.getMeasuredWidth(), r1.getY() + r1.getMeasuredHeight());
            r10.saveLayerAlpha(this.rectF, (int) (r1.getAlpha() * 255.0f), 31);
        L22:
            r10.translate(r1.getX(), r1.getY());
            Canvas r3 = r10;
            r3.drawRect(0.0f, 0.0f, r1.getMeasuredWidth(), r1.getMeasuredHeight(), this.paint);
            r1.draw(r3);
            r3.restore();
        L24:
            r0 = r0 + 1;
            r10 = r3;
            goto L9
        L21:
            r10.save();
        L15:
            if ((r1 instanceof DialogsAdapter.LastEmptyView) == true) goto L17;
        L23:
            r3 = r10;
            goto L24
        L17:
            if (((DialogsAdapter.LastEmptyView) r1).moving == false) goto L23;
        L25:
            invalidate();
            return;
        }

        @Override // org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (drawMovingViewsOverlayed() == false) goto L9;
            if ((r3 instanceof DialogCell) == false) goto L9;
            if (((DialogCell) r3).isMoving() == false) goto L9;
            return true;
        L9:
            if (TopicsFragment.m20937$$Nest$fgetgeneralTopicViewMoving(this.this$0) != r3) goto L13;
            return true;
        L13:
            return super.drawChild(r2, r3, r4);
        }

        public float getViewOffset() {
            return this.viewOffset;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onDraw(Canvas r4) {
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) != null) goto L5;
        L12:
            super.onDraw(r4);
            return;
        L5:
            if (this.viewOffset == 0.0f) goto L12;
            int r0 = getPaddingTop();
            if (r0 == 0) goto L9;
            r4.save();
            r4.translate(0.0f, r0);
        L9:
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).drawOverScroll(r4);
            if (r0 == 0) goto L12;
            r4.restore();
            goto L12
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (this.fastScrollAnimationRunning == false) goto L5;
            return false;
        L5:
            if (TopicsFragment.m20964$$Nest$fgetwaitingForScrollFinished(this.this$0) == false) goto L7;
            return false;
        L7:
            if (TopicsFragment.m20931$$Nest$fgetdialogRemoveFinished(this.this$0) == 0) goto L9;
            return false;
        L9:
            if (TopicsFragment.m20930$$Nest$fgetdialogInsertFinished(this.this$0) == 0) goto L11;
            return false;
        L11:
            if (TopicsFragment.m20929$$Nest$fgetdialogChangeFinished(this.this$0) == 0) goto L13;
            return false;
        L13:
            if (this.this$0.getParentLayout() == null) goto L18;
            if (this.this$0.getParentLayout().isInPreviewMode() == false) goto L18;
            return false;
        L18:
            if (r3.getAction() != 0) goto L21;
            TopicsFragment.m20965$$Nest$fputallowSwipeDuringCurrentTouch(this.this$0, !TopicsFragment.access$900(r0).isActionModeShowed());
            checkIfAdapterValid();
        L21:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (TopicsFragment.m20931$$Nest$fgetdialogRemoveFinished(this.this$0) != 0) goto L9;
            if (TopicsFragment.m20930$$Nest$fgetdialogInsertFinished(this.this$0) != 0) goto L9;
            if (TopicsFragment.m20929$$Nest$fgetdialogChangeFinished(this.this$0) != 0) goto L9;
            return;
        L9:
            if (TopicsFragment.m20943$$Nest$fgetitemAnimator(this.this$0).isRunning() == true) goto L13;
            TopicsFragment.m20988$$Nest$monDialogAnimationFinished(this.this$0);
            return;
        }

        @Override // org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r5, int r6) {
            if (this.firstLayout == true) goto L5;
        L10:
            super.onMeasure(r5, r6);
            return;
        L5:
            if (this.this$0.getMessagesController().dialogsLoaded == false) goto L10;
            if (TopicsFragment.m20938$$Nest$fgethiddenCount(this.this$0) <= 0) goto L9;
            this.ignoreLayout = true;
            ((LinearLayoutManager) getLayoutManager()).scrollToPositionWithOffset(1, (int) TopicsFragment.access$800(this.this$0).getTranslationY());
            this.ignoreLayout = false;
        L9:
            this.firstLayout = false;
            goto L10
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r14) {
            if (this.fastScrollAnimationRunning == false) goto L5;
        L70:
            return false;
        L5:
            if (TopicsFragment.m20964$$Nest$fgetwaitingForScrollFinished(this.this$0) == true) goto L70;
            if (TopicsFragment.m20931$$Nest$fgetdialogRemoveFinished(this.this$0) != 0) goto L70;
            if (TopicsFragment.m20930$$Nest$fgetdialogInsertFinished(this.this$0) != 0) goto L70;
            if (TopicsFragment.m20929$$Nest$fgetdialogChangeFinished(this.this$0) != 0) goto L70;
            if (this.this$0.getParentLayout() != null) goto L15;
        L17:
            int r0 = r14.getAction();
            if (r0 != 0) goto L21;
            setOverScrollMode(0);
        L21:
            if (r0 == 1) goto L24;
            if (r0 == 3) goto L24;
        L34:
            boolean r142 = super.onTouchEvent(r14);
            if (r0 == 1) goto L38;
            if (r0 == 3) goto L38;
        L69:
            return r142;
        L38:
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) != 2) goto L69;
            if (TopicsFragment.m20938$$Nest$fgethiddenCount(this.this$0) <= 0) goto L69;
            LinearLayoutManager r02 = (LinearLayoutManager) getLayoutManager();
            int r5 = r02.findFirstVisibleItemPosition();
            if (r5 != 0) goto L69;
            int r6 = getPaddingTop();
            View r03 = r02.findViewByPosition(r5);
            if (SharedConfig.useThreeLinesLayout == false) goto L46;
            float r52 = 78.0f;
        L47:
            int r53 = (int) (AndroidUtilities.dp(r52) * 0.85f);
            int r7 = (r03.getTop() - r6) + r03.getMeasuredHeight();
            long r8 = System.currentTimeMillis() - TopicsFragment.m20961$$Nest$fgetstartArchivePullingTime(this.this$0);
            if (r7 >= r53) goto L50;
        L65:
            TopicsFragment.m20967$$Nest$fputdisableActionBarScrolling(this.this$0, true);
            smoothScrollBy(0, r7, CubicBezierInterpolator.EASE_OUT_QUINT);
            TopicsFragment.m20975$$Nest$fputpullViewState(this.this$0, 2);
        L67:
            if (getViewOffset() == 0.0f) goto L69;
            ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{getViewOffset(), 0.0f});
            r04.addUpdateListener(new TopicsFragment$TopicsRecyclerView$$ExternalSyntheticLambda0(this));
            r04.setDuration(Math.max(100, (long) (350.0f - ((getViewOffset() / PullForegroundDrawable.getMaxOverscroll()) * 120.0f))));
            r04.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            setScrollEnabled(false);
            r04.addListener(new AnonymousClass1(this));
            r04.start();
            goto L69
        L50:
            if (r8 < 200) goto L65;
            if (TopicsFragment.m20952$$Nest$fgetpullViewState(this.this$0) == 1) goto L67;
            if (getViewOffset() != 0.0f) goto L58;
            TopicsFragment.m20967$$Nest$fputdisableActionBarScrolling(this.this$0, true);
            smoothScrollBy(0, r03.getTop() - r6, CubicBezierInterpolator.EASE_OUT_QUINT);
        L58:
            if (TopicsFragment.m20928$$Nest$fgetcanShowHiddenArchive(this.this$0) == true) goto L64;
            TopicsFragment.m20966$$Nest$fputcanShowHiddenArchive(this.this$0, true);
            performHapticFeedback(3, 2);     // Catch: Exception -> L71
        L62:
            if (TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0) == null) goto L64;
            TopicsFragment.m20951$$Nest$fgetpullForegroundDrawable(this.this$0).colorize(true);
        L64:
            ((DialogCell) r03).startOutAnimation();
            TopicsFragment.m20975$$Nest$fputpullViewState(this.this$0, 1);
            goto L67
        L46:
            r52 = 72.0f;
        L24:
            if (TopicsFragment.m20944$$Nest$fgetitemTouchHelper(this.this$0).isIdle() == true) goto L34;
            if (TouchHelperCallback.m21006$$Nest$fgetswipingFolder(TopicsFragment.m20945$$Nest$fgetitemTouchHelperCallback(this.this$0)) == false) goto L34;
            TouchHelperCallback.m21007$$Nest$fputswipeFolderBack(TopicsFragment.m20945$$Nest$fgetitemTouchHelperCallback(this.this$0), true);
            if (TopicsFragment.m20944$$Nest$fgetitemTouchHelper(this.this$0).checkHorizontalSwipe(null, 4) == 0) goto L34;
            if (TouchHelperCallback.m21005$$Nest$fgetcurrentItemViewHolder(TopicsFragment.m20945$$Nest$fgetitemTouchHelperCallback(this.this$0)) == null) goto L34;
            RecyclerView.ViewHolder r4 = TouchHelperCallback.m21005$$Nest$fgetcurrentItemViewHolder(TopicsFragment.m20945$$Nest$fgetitemTouchHelperCallback(this.this$0));
            if ((r4.itemView instanceof DialogCell) == false) goto L34;
            setArchiveHidden(!TopicsFragment.m20939$$Nest$fgethiddenShown(this.this$0), (DialogCell) r4.itemView);
            goto L34
        L15:
            if (this.this$0.getParentLayout().isInPreviewMode() == false) goto L17;
            goto L17
        }

        @Override // android.view.ViewGroup, android.view.ViewManager
        public void removeView(View r1) {
            super.removeView(r1);
            r1.setTranslationY(0.0f);
            r1.setTranslationX(0.0f);
            r1.setAlpha(1.0f);
        }

        @Override // org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView
        public void setAdapter(RecyclerView.Adapter r1) {
            super.setAdapter(r1);
            this.firstLayout = true;
        }

        public void setViewsOffset(float r6) {
            this.viewOffset = r6;
            int r0 = getChildCount();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L6;
            getChildAt(r1).setTranslationY(r6);
            r1 = r1 + 1;
            goto L3
        L6:
            if (this.selectorPosition == (-1)) goto L10;
            View r02 = getLayoutManager().findViewByPosition(this.selectorPosition);
            if (r02 == null) goto L10;
            this.selectorRect.set(r02.getLeft(), (int) (r02.getTop() + r6), r02.getRight(), (int) (r02.getBottom() + r6));
            this.selectorDrawable.setBounds(this.selectorRect);
        L10:
            invalidate();
        }
    }

    /* loaded from: classes6.dex */
    public class TouchHelperCallback extends ItemTouchHelper.Callback {
        private RecyclerView.ViewHolder currentItemViewHolder;
        private boolean swipeFolderBack;
        private boolean swipingFolder;
        final /* synthetic */ TopicsFragment this$0;

        /* renamed from: -$$Nest$fgetcurrentItemViewHolder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerView.ViewHolder m21005$$Nest$fgetcurrentItemViewHolder(TouchHelperCallback r0) {
            return r0.currentItemViewHolder;
        }

        /* renamed from: -$$Nest$fgetswipingFolder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21006$$Nest$fgetswipingFolder(TouchHelperCallback r0) {
            return r0.swipingFolder;
        }

        /* renamed from: -$$Nest$fputswipeFolderBack, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21007$$Nest$fputswipeFolderBack(TouchHelperCallback r0, boolean r1) {
            r0.swipeFolderBack = r1;
        }

        public TouchHelperCallback(TopicsFragment r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r4, RecyclerView.ViewHolder r5) {
            int r42 = r5.getAdapterPosition();
            if (r42 < 0) goto L26;
            if (r42 >= this.this$0.forumTopics.size()) goto L26;
            if (this.this$0.forumTopics.get(r42).topic == null) goto L26;
            if (ChatObject.canManageTopics(this.this$0.getCurrentChat()) == false) goto L26;
            TLRPC.TL_forumTopic r43 = this.this$0.forumTopics.get(r42).topic;
            if (this.this$0.selectedTopics.isEmpty() == false) goto L20;
            View r52 = r5.itemView;
            if ((r52 instanceof TopicDialogCell) == false) goto L20;
            if (r43.id != 1) goto L20;
            this.swipeFolderBack = false;
            this.swipingFolder = true;
            ((TopicDialogCell) r52).setSliding(true);
            return ItemTouchHelper.Callback.makeMovementFlags(0, 4);
        L20:
            if (r43.pinned == true) goto L24;
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        L24:
            return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
        L26:
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            return !this.this$0.selectedTopics.isEmpty();
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onChildDraw(Canvas r1, RecyclerView r2, RecyclerView.ViewHolder r3, float r4, float r5, int r6, boolean r7) {
            super.onChildDraw(r1, r2, r3, r4, r5, r6, r7);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r3, RecyclerView.ViewHolder r4, RecyclerView.ViewHolder r5) {
            if (r4.getItemViewType() == r5.getItemViewType()) goto L5;
            return false;
        L5:
            int r32 = r5.getAdapterPosition();
            if (r32 >= 0) goto L8;
        L16:
            return false;
        L8:
            if (r32 >= this.this$0.forumTopics.size()) goto L16;
            if (this.this$0.forumTopics.get(r32).topic == null) goto L16;
            if (this.this$0.forumTopics.get(r32).topic.pinned == false) goto L16;
            this.this$0.adapter.swapElements(r4.getAdapterPosition(), r5.getAdapterPosition());
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
            TopicsFragment r0 = this.this$0;
            if (r4 != 0) goto L5;
            r0.sendReorder();
        L6:
            super.onSelectedChanged(r3, r4);
            return;
        L5:
            TopicsFragment.m20953$$Nest$fgetrecyclerListView(r0).cancelClickRunnables(false);
            r3.itemView.setPressed(true);
            goto L6
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r5, int r6) {
            if (r5 == null) goto L10;
            TopicDialogCell r52 = (TopicDialogCell) r5.itemView;
            if (r52.forumTopic == null) goto L6;
            TopicsController r62 = this.this$0.getMessagesController().getTopicsController();
            long r0 = this.this$0.chatId;
            TLRPC.TL_forumTopic r2 = r52.forumTopic;
            r62.toggleShowTopic(r0, r2.id, r2.hidden);
        L6:
            TopicsFragment.m20970$$Nest$fputgeneralTopicViewMoving(this.this$0, r52);
            TopicsRecyclerView.m21004$$Nest$msetArchiveHidden(TopicsFragment.m20953$$Nest$fgetrecyclerListView(this.this$0), !r52.forumTopic.hidden, r52);
            TopicsFragment.m20995$$Nest$mupdateTopicsList(this.this$0, true, true);
            if (TopicDialogCell.m21002$$Nest$fgetcurrentTopic(r52) == null) goto L11;
            r52.setTopicIcon(TopicDialogCell.m21002$$Nest$fgetcurrentTopic(r52));
            return;
        L11:
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$272q3SXwKNRQtA9NRMe7LAZG53s(TopicsFragment r0) {
        r0.lambda$getThemeDescriptions$24();
    }

    public static /* synthetic */ void $r8$lambda$3HEjVLklPS63BqVBRPsXyDw9pBU(TopicsFragment r0, boolean r1, boolean r2) {
        r0.lambda$createView$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3hADYURKmGsaj1VgxjVnztwHuKI(TopicsFragment r0) {
        r0.lambda$createView$7();
    }

    public static /* synthetic */ void $r8$lambda$B7KHxTjmevBMrH8dW9uVkjGwo30(TopicsFragment r0, View r1) {
        r0.lambda$createView$9(r1);
    }

    /* renamed from: $r8$lambda$B9FQdM7bbI9iAujI-rU2_EznkL8, reason: not valid java name */
    public static /* synthetic */ boolean m20916$r8$lambda$B9FQdM7bbI9iAujIrU2_EznkL8(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$BrHvHnq7BmEIGbldD8hoo1lmIxA(TopicsFragment r0) {
        r0.blur3_InvalidateBlur();
    }

    public static /* synthetic */ void $r8$lambda$FLNt20XBT5wm3vti4JCLvSVO00E() {
    }

    public static /* synthetic */ void $r8$lambda$H0n3ZO88VfZ_zJIQr8WGc59V6u0(TopicsFragment r0) {
        r0.lambda$deleteTopics$12();
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$Ij0rM5h6LJld1F_5Pav_4VVr2as(TopicsFragment r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JGPpkxN1txBS8ETaW0lh_41qWpM(TopicsFragment r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    /* renamed from: $r8$lambda$Kxvj0xEzTLM4YyrRuYIwo6-K4sY, reason: not valid java name */
    public static /* synthetic */ void m20917$r8$lambda$Kxvj0xEzTLM4YyrRuYIwo6K4sY(TopicsFragment r0, HashSet r1, ArrayList r2, Runnable r3, AlertDialog r4, int r5) {
        r0.lambda$deleteTopics$14(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$LnFKTZNApkRdGWhkO4XTEU9QW1s(TopicsFragment r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$MXwTqoWw3S0usvcahEBucZHlex8(TopicsFragment r0, ValueAnimator r1) {
        r0.lambda$animateToSearchView$20(r1);
    }

    /* renamed from: $r8$lambda$RD9DbEjnZAEdAn5HV1-ruKtXBqY, reason: not valid java name */
    public static /* synthetic */ boolean m20918$r8$lambda$RD9DbEjnZAEdAn5HV1ruKtXBqY(TopicsFragment r0, View r1, int r2, float r3, float r4) {
        return r0.lambda$createView$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$SnJqCnCfoHcYPoWcvYVZ0UzL7wc(TopicsFragment r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: $r8$lambda$b8HyV1v-FvHhMQENqRWLPI5KZsY, reason: not valid java name */
    public static /* synthetic */ void m20919$r8$lambda$b8HyV1vFvHhMQENqRWLPI5KZsY(TopicsFragment r0, View r1, int r2) {
        r0.lambda$createView$3(r1, r2);
    }

    /* renamed from: $r8$lambda$c-VOxkfMjviC8YN-LjP66tDoW3w, reason: not valid java name */
    public static /* synthetic */ void m20920$r8$lambda$cVOxkfMjviC8YNLjP66tDoW3w(TopicsFragment r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$cRB3lEIGoRu0KUkdbbs_MFgUupw(TopicsFragment r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$dYgE47UDgPxCErZaxP7fU_TZOpU(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$lyCk8bO38E_M6DPJizaxUUF9Dm0(TopicsFragment r0, TLRPC.TL_forumTopic r1, ActionBarPopupWindow.ActionBarPopupWindowLayout[] r2, int r3, View r4) {
        r0.lambda$showChatPreview$17(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$me2VhM0A2DglnAjvM-XDG6bFYnA, reason: not valid java name */
    public static /* synthetic */ void m20921$r8$lambda$me2VhM0A2DglnAjvMXDG6bFYnA(TopicsFragment r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$createView$10(r1);
    }

    /* renamed from: $r8$lambda$rUDzJ3-52vmAJIA2e4vfPt_Q8Jk, reason: not valid java name */
    public static /* synthetic */ void m20922$r8$lambda$rUDzJ352vmAJIA2e4vfPt_Q8Jk(TopicsFragment r0, TLRPC.TL_forumTopic r1, View r2) {
        r0.lambda$showChatPreview$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$taKOJ91sKqT59Q3CAcec2U3bH80(TopicsFragment r0) {
        r0.lambda$joinToGroup$21();
    }

    /* renamed from: $r8$lambda$v-MJbkrrb773L3MXlYjlZy6BuV0, reason: not valid java name */
    public static /* synthetic */ void m20923$r8$lambda$vMJbkrrb773L3MXlYjlZy6BuV0(TopicsFragment r0, TLRPC.TL_forumTopic r1, View r2) {
        r0.lambda$showChatPreview$16(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$w9MVWORz_4TzUrChZDlKAc2Tqug(TopicsFragment r0, TLRPC.TL_error r1) {
        return r0.lambda$joinToGroup$22(r1);
    }

    /* renamed from: $r8$lambda$wYoE1G7LsGQsL2XfXUkwYsnI-hk, reason: not valid java name */
    public static /* synthetic */ void m20924$r8$lambda$wYoE1G7LsGQsL2XfXUkwYsnIhk(TopicsFragment r0, ArrayList r1, Runnable r2) {
        r0.lambda$deleteTopics$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xpRIVSDmh3Ej464yKLIN3Aswi8k(TopicsFragment r0, TLRPC.TL_forumTopic r1, View r2) {
        r0.lambda$showChatPreview$19(r1, r2);
    }

    /* renamed from: -$$Nest$fgetboostsStatus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stories.TL_premium_boostsStatus m20925$$Nest$fgetboostsStatus(TopicsFragment r0) {
        return r0.boostsStatus;
    }

    /* renamed from: -$$Nest$fgetbottomButtonType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20926$$Nest$fgetbottomButtonType(TopicsFragment r0) {
        return r0.bottomButtonType;
    }

    /* renamed from: -$$Nest$fgetbottomOverlayContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20927$$Nest$fgetbottomOverlayContainer(TopicsFragment r0) {
        return r0.bottomOverlayContainer;
    }

    /* renamed from: -$$Nest$fgetcanShowHiddenArchive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20928$$Nest$fgetcanShowHiddenArchive(TopicsFragment r0) {
        return r0.canShowHiddenArchive;
    }

    /* renamed from: -$$Nest$fgetdialogChangeFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20929$$Nest$fgetdialogChangeFinished(TopicsFragment r0) {
        return r0.dialogChangeFinished;
    }

    /* renamed from: -$$Nest$fgetdialogInsertFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20930$$Nest$fgetdialogInsertFinished(TopicsFragment r0) {
        return r0.dialogInsertFinished;
    }

    /* renamed from: -$$Nest$fgetdialogRemoveFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20931$$Nest$fgetdialogRemoveFinished(TopicsFragment r0) {
        return r0.dialogRemoveFinished;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20932$$Nest$fgetemptyView(TopicsFragment r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetemptyViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyViewContainer m20933$$Nest$fgetemptyViewContainer(TopicsFragment r0) {
        return r0.emptyViewContainer;
    }

    /* renamed from: -$$Nest$fgetforumTopicsListFrozen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20934$$Nest$fgetforumTopicsListFrozen(TopicsFragment r0) {
        return r0.forumTopicsListFrozen;
    }

    /* renamed from: -$$Nest$fgetfragmentContextViewWrapper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20935$$Nest$fgetfragmentContextViewWrapper(TopicsFragment r0) {
        return r0.fragmentContextViewWrapper;
    }

    /* renamed from: -$$Nest$fgetfrozenForumTopicsList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20936$$Nest$fgetfrozenForumTopicsList(TopicsFragment r0) {
        return r0.frozenForumTopicsList;
    }

    /* renamed from: -$$Nest$fgetgeneralTopicViewMoving, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20937$$Nest$fgetgeneralTopicViewMoving(TopicsFragment r0) {
        return r0.generalTopicViewMoving;
    }

    /* renamed from: -$$Nest$fgethiddenCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20938$$Nest$fgethiddenCount(TopicsFragment r0) {
        return r0.hiddenCount;
    }

    /* renamed from: -$$Nest$fgethiddenShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20939$$Nest$fgethiddenShown(TopicsFragment r0) {
        return r0.hiddenShown;
    }

    /* renamed from: -$$Nest$fgetiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20940$$Nest$fgetiBlur3Invalidated(TopicsFragment r0) {
        return r0.iBlur3Invalidated;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m20941$$Nest$fgetiBlur3SourceGlass(TopicsFragment r0) {
        return r0.iBlur3SourceGlass;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m20942$$Nest$fgetiBlur3SourceGlassFrosted(TopicsFragment r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetitemAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DefaultItemAnimator m20943$$Nest$fgetitemAnimator(TopicsFragment r0) {
        return r0.itemAnimator;
    }

    /* renamed from: -$$Nest$fgetitemTouchHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemTouchHelper m20944$$Nest$fgetitemTouchHelper(TopicsFragment r0) {
        return r0.itemTouchHelper;
    }

    /* renamed from: -$$Nest$fgetitemTouchHelperCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TouchHelperCallback m20945$$Nest$fgetitemTouchHelperCallback(TopicsFragment r0) {
        return r0.itemTouchHelperCallback;
    }

    /* renamed from: -$$Nest$fgetlastItemsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20946$$Nest$fgetlastItemsCount(TopicsFragment r0) {
        return r0.lastItemsCount;
    }

    /* renamed from: -$$Nest$fgetmute, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20947$$Nest$fgetmute(TopicsFragment r0) {
        return r0.mute;
    }

    /* renamed from: -$$Nest$fgetnavigationBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20948$$Nest$fgetnavigationBarHeight(TopicsFragment r0) {
        return r0.navigationBarHeight;
    }

    /* renamed from: -$$Nest$fgetopenedForForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20949$$Nest$fgetopenedForForward(TopicsFragment r0) {
        return r0.openedForForward;
    }

    /* renamed from: -$$Nest$fgetother, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m20950$$Nest$fgetother(TopicsFragment r0) {
        return r0.other;
    }

    /* renamed from: -$$Nest$fgetpullForegroundDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PullForegroundDrawable m20951$$Nest$fgetpullForegroundDrawable(TopicsFragment r0) {
        return r0.pullForegroundDrawable;
    }

    /* renamed from: -$$Nest$fgetpullViewState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20952$$Nest$fgetpullViewState(TopicsFragment r0) {
        return r0.pullViewState;
    }

    /* renamed from: -$$Nest$fgetrecyclerListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TopicsRecyclerView m20953$$Nest$fgetrecyclerListView(TopicsFragment r0) {
        return r0.recyclerListView;
    }

    /* renamed from: -$$Nest$fgetreordering, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20954$$Nest$fgetreordering(TopicsFragment r0) {
        return r0.reordering;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m20955$$Nest$fgetscrollableViewNoiseSuppressor(TopicsFragment r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsearchAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20956$$Nest$fgetsearchAnimationProgress(TopicsFragment r0) {
        return r0.searchAnimationProgress;
    }

    /* renamed from: -$$Nest$fgetsearchContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessagesSearchContainer m20957$$Nest$fgetsearchContainer(TopicsFragment r0) {
        return r0.searchContainer;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m20958$$Nest$fgetsearchItem(TopicsFragment r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearchTabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed.TabsView m20959$$Nest$fgetsearchTabsView(TopicsFragment r0) {
        return r0.searchTabsView;
    }

    /* renamed from: -$$Nest$fgetselectedTopicForTablet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20960$$Nest$fgetselectedTopicForTablet(TopicsFragment r2) {
        return r2.selectedTopicForTablet;
    }

    /* renamed from: -$$Nest$fgetstartArchivePullingTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m20961$$Nest$fgetstartArchivePullingTime(TopicsFragment r2) {
        return r2.startArchivePullingTime;
    }

    /* renamed from: -$$Nest$fgettopPanelLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsActivityTopPanelLayout m20962$$Nest$fgettopPanelLayout(TopicsFragment r0) {
        return r0.topPanelLayout;
    }

    /* renamed from: -$$Nest$fgettopicsController, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TopicsController m20963$$Nest$fgettopicsController(TopicsFragment r0) {
        return r0.topicsController;
    }

    /* renamed from: -$$Nest$fgetwaitingForScrollFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20964$$Nest$fgetwaitingForScrollFinished(TopicsFragment r0) {
        return r0.waitingForScrollFinished;
    }

    /* renamed from: -$$Nest$fputallowSwipeDuringCurrentTouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20965$$Nest$fputallowSwipeDuringCurrentTouch(TopicsFragment r0, boolean r1) {
        r0.allowSwipeDuringCurrentTouch = r1;
    }

    /* renamed from: -$$Nest$fputcanShowHiddenArchive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20966$$Nest$fputcanShowHiddenArchive(TopicsFragment r0, boolean r1) {
        r0.canShowHiddenArchive = r1;
    }

    /* renamed from: -$$Nest$fputdisableActionBarScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20967$$Nest$fputdisableActionBarScrolling(TopicsFragment r0, boolean r1) {
        r0.disableActionBarScrolling = r1;
    }

    /* renamed from: -$$Nest$fputemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20968$$Nest$fputemptyView(TopicsFragment r0, View r1) {
        r0.emptyView = r1;
    }

    /* renamed from: -$$Nest$fputfinishDialogRightSlidingPreviewOnTransitionEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20969$$Nest$fputfinishDialogRightSlidingPreviewOnTransitionEnd(TopicsFragment r0, boolean r1) {
        r0.finishDialogRightSlidingPreviewOnTransitionEnd = r1;
    }

    /* renamed from: -$$Nest$fputgeneralTopicViewMoving, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20970$$Nest$fputgeneralTopicViewMoving(TopicsFragment r0, View r1) {
        r0.generalTopicViewMoving = r1;
    }

    /* renamed from: -$$Nest$fputhiddenShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20971$$Nest$fputhiddenShown(TopicsFragment r0, boolean r1) {
        r0.hiddenShown = r1;
    }

    /* renamed from: -$$Nest$fputiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20972$$Nest$fputiBlur3Invalidated(TopicsFragment r0, boolean r1) {
        r0.iBlur3Invalidated = r1;
    }

    /* renamed from: -$$Nest$fputlastItemsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20973$$Nest$fputlastItemsCount(TopicsFragment r0, int r1) {
        r0.lastItemsCount = r1;
    }

    /* renamed from: -$$Nest$fputmainTabsHiddenByScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20974$$Nest$fputmainTabsHiddenByScroll(TopicsFragment r0, boolean r1) {
        r0.mainTabsHiddenByScroll = r1;
    }

    /* renamed from: -$$Nest$fputpullViewState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20975$$Nest$fputpullViewState(TopicsFragment r0, int r1) {
        r0.pullViewState = r1;
    }

    /* renamed from: -$$Nest$fputscrollToTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20976$$Nest$fputscrollToTop(TopicsFragment r0, boolean r1) {
        r0.scrollToTop = r1;
    }

    /* renamed from: -$$Nest$fputstartArchivePullingTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20977$$Nest$fputstartArchivePullingTime(TopicsFragment r0, long r1) {
        r0.startArchivePullingTime = r1;
    }

    /* renamed from: -$$Nest$fputupdateAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20978$$Nest$fputupdateAnimated(TopicsFragment r0, boolean r1) {
        r0.updateAnimated = r1;
    }

    /* renamed from: -$$Nest$manimateToSearchView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20979$$Nest$manimateToSearchView(TopicsFragment r0, boolean r1) {
        r0.animateToSearchView(r1);
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20980$$Nest$mblur3_InvalidateBlur(TopicsFragment r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mcheckForLoadMore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20981$$Nest$mcheckForLoadMore(TopicsFragment r0) {
        r0.checkForLoadMore();
    }

    /* renamed from: -$$Nest$mcheckUi_mainTabsVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20982$$Nest$mcheckUi_mainTabsVisible(TopicsFragment r0) {
        r0.checkUi_mainTabsVisible();
    }

    /* renamed from: -$$Nest$mclearSelectedTopics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20983$$Nest$mclearSelectedTopics(TopicsFragment r0) {
        r0.clearSelectedTopics();
    }

    /* renamed from: -$$Nest$mdeleteTopics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20984$$Nest$mdeleteTopics(TopicsFragment r0, HashSet r1, Runnable r2) {
        r0.deleteTopics(r1, r2);
    }

    /* renamed from: -$$Nest$mgetTopicForMultiselect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_forumTopic m20985$$Nest$mgetTopicForMultiselect(TopicsFragment r0, int r1) {
        return r0.getTopicForMultiselect(r1);
    }

    /* renamed from: -$$Nest$mhideFloatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20986$$Nest$mhideFloatingButton(TopicsFragment r0, boolean r1, boolean r2) {
        r0.hideFloatingButton(r1, r2);
    }

    /* renamed from: -$$Nest$mjoinToGroup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20987$$Nest$mjoinToGroup(TopicsFragment r0) {
        r0.joinToGroup();
    }

    /* renamed from: -$$Nest$monDialogAnimationFinished, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20988$$Nest$monDialogAnimationFinished(TopicsFragment r0) {
        r0.onDialogAnimationFinished();
    }

    /* renamed from: -$$Nest$mopenProfile, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20989$$Nest$mopenProfile(TopicsFragment r0, boolean r1) {
        r0.openProfile(r1);
    }

    /* renamed from: -$$Nest$mshowChatPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20990$$Nest$mshowChatPreview(TopicsFragment r0, DialogCell r1) {
        return r0.showChatPreview(r1);
    }

    /* renamed from: -$$Nest$mtoggleSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20991$$Nest$mtoggleSelection(TopicsFragment r0, TLRPC.TL_forumTopic r1, View r2) {
        r0.toggleSelection(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateChatInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20992$$Nest$mupdateChatInfo(TopicsFragment r0) {
        r0.updateChatInfo();
    }

    /* renamed from: -$$Nest$mupdateCreateTopicButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20993$$Nest$mupdateCreateTopicButton(TopicsFragment r0, boolean r1) {
        r0.updateCreateTopicButton(r1);
    }

    /* renamed from: -$$Nest$mupdateSearchProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20994$$Nest$mupdateSearchProgress(TopicsFragment r0, float r1) {
        r0.updateSearchProgress(r1);
    }

    /* renamed from: -$$Nest$mupdateTopicsList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20995$$Nest$mupdateTopicsList(TopicsFragment r0, boolean r1, boolean r2) {
        r0.updateTopicsList(r1, r2);
    }

    static {
        settingsPreloaded = new HashSet();
    }

    public TopicsFragment(Bundle r8) {
        super(r8);
        this.forumTopics = new ArrayList();
        this.frozenForumTopicsList = new ArrayList();
        this.adapter = new Adapter(this, null);
        this.hiddenCount = 0;
        this.hiddenShown = true;
        this.animatedUpdateEnabled = true;
        this.bottomPannelVisible = true;
        this.searchAnimationProgress = 0.0f;
        this.selectedTopics = new HashSet();
        this.mute = false;
        this.notificationsLocker = new AnimationNotificationsLocker(new int[]{NotificationCenter.topicsDidLoaded});
        this.slideFragmentProgress = 1.0f;
        this.movingDialogFilters = new ArrayList();
        ArrayList<RectF> r2 = new ArrayList();
        this.iBlur3Positions = r2;
        RectF r3 = new RectF();
        this.iBlur3PositionActionBar = r3;
        RectF r4 = new RectF();
        this.iBlur3PositionMainTabs = r4;
        r2.add(r3);
        r2.add(r4);
        this.chatId = this.arguments.getLong("chat_id", 0);
        this.openedForSelect = this.arguments.getBoolean("for_select", false);
        this.openedForForward = this.arguments.getBoolean("forward_to", false);
        this.openedForBotShare = this.arguments.getBoolean("bot_share_to", false);
        this.openedForQuote = this.arguments.getBoolean("quote", false);
        this.openedForReply = this.arguments.getBoolean("reply_to", false);
        this.voiceChatHash = this.arguments.getString("voicechat", null);
        this.openVideoChat = this.arguments.getBoolean("videochat", false);
        this.topicsController = getMessagesController().getTopicsController();
        SharedPreferences r42 = getUserConfig().getPreferences();
        this.canShowProgress = !r42.getBoolean("topics_end_reached_" + r2, false);
        BlurredBackgroundSourceColor r82 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r82;
        r82.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        if (Build.VERSION.SDK_INT < 31) goto L6;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        this.iBlur3SourceGlassFrosted = new BlurredBackgroundSourceRenderNode(null);
        BlurredBackgroundSourceRenderNode r83 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = r83;
        BlurredBackgroundDrawableViewFactory r0 = new BlurredBackgroundDrawableViewFactory(r83);
        this.iBlur3FactoryLiquidGlass = r0;
        r0.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        return;
    L6:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
        this.iBlur3FactoryLiquidGlass = new BlurredBackgroundDrawableViewFactory(r82);
    }

    public static /* synthetic */ ActionBar access$000(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$1000(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ NotificationsController access$1100(TopicsFragment r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ NotificationsController access$1200(TopicsFragment r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ NotificationsController access$1300(TopicsFragment r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ int access$1400(TopicsFragment r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$1500(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ boolean access$1600(TopicsFragment r0) {
        return r0.inPreviewMode;
    }

    public static /* synthetic */ int access$1700(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1800(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$1900(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$200(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$2000(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$2100(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$2200(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$2300(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$2400(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ boolean access$2500(TopicsFragment r0) {
        return r0.inPreviewMode;
    }

    public static /* synthetic */ int access$2600(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2700(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$300(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$500(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ NotificationsController access$600(TopicsFragment r0) {
        return r0.getNotificationsController();
    }

    public static /* synthetic */ int access$700(TopicsFragment r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$800(TopicsFragment r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$900(TopicsFragment r0) {
        return r0.actionBar;
    }

    private void animateToSearchView(boolean r6) {
        this.searching = r6;
        ValueAnimator r0 = this.searchAnimator;
        if (r0 == null) goto L6;
        r0.removeAllListeners();
        this.searchAnimator.cancel();
    L6:
        if (this.searchTabsView != null) goto L11;
        ViewPagerFixed.TabsView r02 = this.searchContainer.createTabsView(false, 8);
        this.searchTabsView = r02;
        if (this.parentDialogsActivity == null) goto L10;
        r02.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
    L10:
        this.fullscreenView.addView(this.searchTabsView, LayoutHelper.createFrame(-1, 44.0f));
    L11:
        float r03 = this.searchAnimationProgress;
        if (r6 == false) goto L14;
        float r3 = 1.0f;
    L15:
        boolean r04 = true;
        this.searchAnimator = ValueAnimator.ofFloat(new float[]{r03, r3});
        AndroidUtilities.updateViewVisibilityAnimated(this.searchContainer, false, 1.0f, true);
        DialogsActivity r32 = this.parentDialogsActivity;
        if (r32 == null) goto L20;
        RightSlidingDialogContainer r33 = r32.rightSlidingDialogContainer;
        if (r33 == null) goto L20;
        r33.enabled = !r6;
    L20:
        if (r6 == false) goto L22;
    L26:
        r04 = false;
    L27:
        this.animateSearchWithScale = r04;
        this.searchAnimator.addUpdateListener(new TopicsFragment$$ExternalSyntheticLambda24(this));
        this.searchContainer.setVisibility(0);
        if (r6 == true) goto L30;
        this.other.setVisibility(0);
    L31:
        this.searchAnimator.addListener(new AnonymousClass20(this, r6));
        this.searchAnimator.setDuration(200);
        this.searchAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.searchAnimator.start();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[]{Boolean.TRUE});
        return;
    L30:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        updateCreateTopicButton(false);
        goto L31
    L22:
        if (this.searchContainer.getVisibility() != 0) goto L26;
        if (this.searchContainer.getAlpha() != 1.0f) goto L26;
    L14:
        r3 = 0.0f;
        goto L15
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L25;
        int r1 = AndroidUtilities.dp(48.0f);
        int r2 = AndroidUtilities.dp(48.0f) + ((int) this.topPanelLayout.getAnimatedHeightWithPadding(AndroidUtilities.dp(14.0f)));
        DialogsActivity r3 = this.parentDialogsActivity;
        if (r3 == null) goto L10;
        View r4 = r3.fragmentView;
    L11:
        if (r3 == null) goto L13;
        ActionBar r32 = r3.getActionBar();
    L14:
        this.iBlur3PositionActionBar.set(0.0f, -r1, r4.getMeasuredWidth(), (r32.getMeasuredHeight() + r1) + r2);
        MainTabsUiHelper.setBlurBounds(this.iBlur3PositionMainTabs, r4, this.navigationBarHeight);
        RectF r12 = this.iBlur3PositionMainTabs;
        if (LiteMode.isEnabled(262144) == false) goto L17;
        float r0 = 0.0f;
    L18:
        r12.inset(0.0f, r0);
        DownscaleScrollableNoiseSuppressor r02 = this.scrollableViewNoiseSuppressor;
        ArrayList<RectF> r13 = this.iBlur3Positions;
        if (this.parentDialogsActivity == null) goto L21;
        int r22 = 2;
    L22:
        r02.setupRenderNodes(r13, r22);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, r4.getMeasuredWidth(), r4.getMeasuredHeight());
        return;
    L21:
        r22 = 1;
        goto L22
    L17:
        r0 = -AndroidUtilities.dp(48.0f);
        goto L18
    L13:
        r32 = this.actionBar;
        goto L14
    L10:
        r4 = this.fragmentView;
        goto L11
    }

    private void checkForLoadMore() {
        if (this.topicsController.endIsReached(this.chatId) == true) goto L14;
        LinearLayoutManager r0 = this.layoutManager;
        if (r0 == null) goto L15;
        int r02 = r0.findLastVisibleItemPosition();
        if (this.forumTopics.isEmpty() == false) goto L10;
    L11:
        this.topicsController.loadTopics(this.chatId);
    L12:
        checkLoading();
        return;
    L10:
        if (r02 < (this.adapter.getItemCount() - 5)) goto L12;
    L15:
        return;
    }

    private void checkGroupCallJoin(boolean r13) {
        TLRPC.Chat r2 = getMessagesController().getChat(Long.valueOf(this.chatId));
        TLRPC.ChatFull r0 = getMessagesController().getChatFull(this.chatId);
        if (this.groupCall == null) goto L13;
        String r4 = this.voiceChatHash;
        if (r4 == null) goto L7;
    L10:
        VoIPHelper.startCall(r2, null, r4, this.createGroupCall, Boolean.valueOf(!r1.call.rtmp_stream), getParentActivity(), this, getAccountInstance());
        this.voiceChatHash = null;
        this.openVideoChat = false;
        return;
    L7:
        if (this.openVideoChat == true) goto L10;
    L13:
        if (this.voiceChatHash == null) goto L23;
        if (r13 == false) goto L23;
        if (r0 == null) goto L23;
        if (r0.call != null) goto L23;
        if (this.fragmentView == null) goto L23;
        if (getParentActivity() == null) goto L23;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.linkbroken, LocaleController.getString(R.string.LinkHashExpired)).show();
        this.voiceChatHash = null;
    L23:
        this.lastCallCheckFromServer = false;
    }

    private void checkLoading() {
        this.loadingTopics = this.topicsController.isLoading(this.chatId);
        if (this.topicsEmptyView != null) goto L5;
    L13:
        TopicsRecyclerView r0 = this.recyclerListView;
        if (r0 == null) goto L16;
        r0.checkIfEmpty();
    L16:
        updateCreateTopicButton(true);
        return;
    L5:
        if (this.forumTopics.size() != 0) goto L7;
    L12:
        this.topicsEmptyView.showProgress(this.loadingTopics, this.fragmentBeginToShow);
        goto L13
    L7:
        if (this.forumTopics.size() != 1) goto L13;
        if (this.forumTopics.get(0).topic == null) goto L13;
        if (this.forumTopics.get(0).topic.id != 1) goto L13;
        goto L12
    }

    private void checkUi_mainTabsVisible() {
        DialogsActivity r0 = this.parentDialogsActivity;
        if (r0 != null) goto L5;
        return;
    L5:
        if (BottomNavigationBar.visible() == true) goto L7;
    L9:
        boolean r2 = false;
    L10:
        r0.setChildMainTabsVisible(r2);
        return;
    L7:
        if (this.mainTabsHiddenByScroll == true) goto L9;
        r2 = true;
        goto L10
    }

    private void chekActionMode() {
        if (this.actionBar.actionModeIsExist(null) == false) goto L5;
        return;
    L5:
        ActionBarMenu r0 = this.actionBar.createActionMode(false, null);
        if (this.inPreviewMode == false) goto L8;
        r0.setBackgroundColor(0);
        r0.drawBlur = false;
    L8:
        NumberTextView r1 = new NumberTextView(r0.getContext());
        this.selectedDialogsCountTextView = r1;
        r1.setTextSize(18);
        this.selectedDialogsCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedDialogsCountTextView.setTextColor(getThemedColor(Theme.key_actionBarActionModeDefaultIcon));
        r0.addView(this.selectedDialogsCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 72, 0, 0, 0));
        this.selectedDialogsCountTextView.setOnTouchListener(new TopicsFragment$$ExternalSyntheticLambda15());
        this.pinItem = r0.addItemWithWidth(4, R.drawable.msg_pin, AndroidUtilities.dp(54.0f));
        this.unpinItem = r0.addItemWithWidth(5, R.drawable.msg_unpin, AndroidUtilities.dp(54.0f));
        this.muteItem = r0.addItemWithWidth(6, R.drawable.msg_mute, AndroidUtilities.dp(54.0f));
        this.deleteItem = r0.addItemWithWidth(7, R.drawable.msg_delete, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Delete));
        ActionBarMenuItem r12 = r0.addItemWithWidth(12, R.drawable.msg_archive_hide, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Hide));
        this.hideItem = r12;
        r12.setVisibility(8);
        ActionBarMenuItem r13 = r0.addItemWithWidth(13, R.drawable.msg_archive_show, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Show));
        this.showItem = r13;
        r13.setVisibility(8);
        ActionBarMenuItem r02 = r0.addItemWithWidth(0, R.drawable.ic_ab_other, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.AccDescrMoreOptions));
        this.otherItem = r02;
        this.readItem = r02.addSubItem(8, R.drawable.msg_markread, LocaleController.getString(R.string.MarkAsRead));
        this.closeTopic = this.otherItem.addSubItem(9, R.drawable.msg_topic_close, LocaleController.getString(R.string.CloseTopic));
        this.restartTopic = this.otherItem.addSubItem(10, R.drawable.msg_topic_restart, LocaleController.getString(R.string.RestartTopic));
    }

    private void clearSelectedTopics() {
        this.selectedTopics.clear();
        this.actionBar.hideActionMode();
        AndroidUtilities.updateVisibleRows(this.recyclerListView);
        updateReordering();
    }

    private void deleteTopics(HashSet<Integer> r8, Runnable r9) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext());
        r0.setTitle(LocaleController.getPluralString("DeleteTopics", r8.size()));
        ArrayList r1 = new ArrayList(r8);
        if (r8.size() != 1) goto L5;
        r0.setMessage(LocaleController.formatString(R.string.DeleteSelectedTopic, new Object[]{this.topicsController.findTopic(this.chatId, ((Integer) r1.get(0)).intValue()).title}));
    L6:
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new TopicsFragment$$ExternalSyntheticLambda18(this, r8, r1, r9));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new TopicsFragment$$ExternalSyntheticLambda19());
        AlertDialog r82 = r0.create();
        r82.show();
        TextView r83 = (TextView) r82.getButton(-1);
        if (r83 == null) goto L10;
        r83.setTextColor(getThemedColor(Theme.key_text_RedBold));
        return;
    L10:
        return;
    L5:
        r0.setMessage(LocaleController.getString(R.string.DeleteSelectedTopics));
        goto L6
    }

    private TLRPC.TL_forumTopic getTopicForMultiselect(int r3) {
        ArrayList<Item> r2 = this.adapter.getArray();
        if (r3 >= 0) goto L5;
    L14:
        return null;
    L5:
        if (r3 >= r2.size()) goto L14;
        Item r22 = r2.get(r3);
        if (r22 == null) goto L14;
        if (r22.viewType != 0) goto L14;
        return r22.topic;
    }

    private static BaseFragment getTopicsOrChat(MessagesController r8, MessagesStorage r9, Bundle r10) {
        long r2 = r10.getLong("chat_id");
        if (r2 == 0) goto L19;
        TLRPC.Dialog r0 = r8.getDialog(-r2);
        if (r0 != null) goto L7;
    L10:
        TLRPC.ChatFull r82 = r8.getChatFull(r2);
        if (r82 != null) goto L13;
        r82 = r9.loadChatInfo(r2, true, new CountDownLatch(1), false, false);
    L13:
        if (r82 == null) goto L19;
        if (r82.view_forum_as_messages == false) goto L19;
        return new ChatActivity(r10);
    L7:
        if (r0.view_forum_as_messages == false) goto L10;
        return new ChatActivity(r10);
    L19:
        return new TopicsFragment(r10);
    }

    public static BaseFragment getTopicsOrChat(BaseFragment r1, Bundle r2) {
        return getTopicsOrChat(r1.getMessagesController(), r1.getMessagesStorage(), r2);
    }

    public static BaseFragment getTopicsOrChat(LaunchActivity r1, Bundle r2) {
        return getTopicsOrChat(MessagesController.getInstance(r1.currentAccount), MessagesStorage.getInstance(r1.currentAccount), r2);
    }

    private void hideFloatingButton(boolean r4, boolean r5) {
        FragmentFloatingButton r0 = this.floatingButton;
        boolean r1 = true;
        boolean r42 = !r4;
        if (this.fragmentBeginToShow == false) goto L6;
        if (r5 == false) goto L6;
    L7:
        r0.setButtonVisible(r42, r1);
        if (BottomNavigationBar.floating() == true) goto L11;
        checkUi_mainTabsVisible();
        return;
    L11:
        return;
    L6:
        r1 = false;
        goto L7
    }

    private void joinToGroup() {
        getMessagesController().addUserToChat(this.chatId, getUserConfig().getCurrentUser(), 0, null, this, false, new TopicsFragment$$ExternalSyntheticLambda16(this), new TopicsFragment$$ExternalSyntheticLambda17(this));
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.closeSearchByActiveAction, new Object[0]);
        updateChatInfo();
    }

    private /* synthetic */ void lambda$animateToSearchView$20(ValueAnimator r1) {
        updateSearchProgress(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        if (this.searching == true) goto L6;
        openProfile(false);
        return;
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        openParentSearch();
    }

    private /* synthetic */ void lambda$createView$10(TL_stories.TL_premium_boostsStatus r1) {
        this.boostsStatus = r1;
    }

    private /* synthetic */ void lambda$createView$2() {
        this.recyclerListView.postOnAnimation(new TopicsFragment$$ExternalSyntheticLambda14(this));
    }

    private /* synthetic */ void lambda$createView$3(View r11, int r12) {
        if (getParentLayout() != null) goto L5;
        return;
    L5:
        if (getParentLayout().isInPreviewMode() == false) goto L8;
        return;
    L8:
        if ((r11 instanceof TopicDialogCell) == false) goto L60;
        TLRPC.TL_forumTopic r122 = ((TopicDialogCell) r11).forumTopic;
        if (r122 != null) goto L13;
        return;
    L13:
        if (getMessagesController().isMonoForum(-this.chatId) == false) goto L16;
        long r0 = DialogObject.getPeerDialogId(r122.from_id);
    L15:
        long r5 = r0;
        if (this.openedForSelect == false) goto L27;
        OnTopicSelectedListener r112 = this.onTopicSelectedListener;
        if (r112 == null) goto L22;
        r112.onTopicSelected(r122);
    L22:
        DialogsActivity r2 = this.dialogsActivity;
        if (r2 == null) goto L61;
        r2.didSelectResult(-this.chatId, r5, true, false, this);
        return;
    L61:
        return;
    L27:
        if (this.selectedTopics.size() <= 0) goto L31;
        toggleSelection(r11);
        return;
    L31:
        if (this.inPreviewMode == true) goto L33;
    L47:
        ForumUtilities.openTopic(this, this.chatId, r122, 0);
        return;
    L33:
        if (AndroidUtilities.isTablet() == false) goto L47;
        Iterator<BaseFragment> r10 = getParentLayout().getFragmentStack().iterator();
    L36:
        if (r10.hasNext() == false) goto L46;
        BaseFragment r02 = r10.next();
        if ((r02 instanceof DialogsActivity) == false) goto L36;
        DialogsActivity r03 = (DialogsActivity) r02;
        if (r03.isMainDialogList() == false) goto L36;
        MessagesStorage.TopicKey r04 = r03.getOpenedDialogId();
        if (r04.dialogId != (-this.chatId)) goto L36;
        if (r04.topicId != r5) goto L36;
        return;
    L46:
        this.selectedTopicForTablet = r5;
        updateTopicsList(false, false);
        goto L47
    L16:
        r0 = r122.id;
        goto L15
    }

    private /* synthetic */ boolean lambda$createView$4(View r11, int r12, float r13, float r14) {
        if (this.openedForSelect == false) goto L5;
    L27:
        return false;
    L5:
        if (getParentLayout() == null) goto L27;
        if (getParentLayout().isInPreviewMode() == true) goto L27;
        if (this.actionBar.isActionModeShowed() == false) goto L12;
    L19:
        toggleSelection(r11);
        r11.performHapticFeedback(0);     // Catch: Exception -> L28
    L22:
        if (this.actionBar.isActionModeShowed() == true) goto L24;
    L26:
        return true;
    L24:
        if ((r11 instanceof TopicDialogCell) == false) goto L26;
        startMultiselect(r12);
        goto L26
    L12:
        if (AndroidUtilities.isTablet() == true) goto L19;
        if ((r11 instanceof TopicDialogCell) == false) goto L19;
        TopicDialogCell r0 = (TopicDialogCell) r11;
        if (r0.isPointInsideAvatar(r13, r14) == false) goto L19;
        showChatPreview(r0);
        this.recyclerListView.cancelClickRunnables(true);
        this.recyclerListView.dispatchTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
        return false;
    }

    private /* synthetic */ void lambda$createView$5(View r5) {
        presentFragment(TopicCreateFragment.create(this.chatId, 0));
    }

    private /* synthetic */ void lambda$createView$6(View r5) {
        getMessagesController().hidePeerSettingsBar(-this.chatId, null, getCurrentChat());
        updateChatInfo();
    }

    private /* synthetic */ void lambda$createView$7() {
        blur3_InvalidateBlur();
        checkUi_listViewPadding();
    }

    private /* synthetic */ void lambda$createView$8(boolean r2, boolean r3) {
        this.topPanelLayout.setViewVisible(this.pendingRequestsDelegate.getView(), r2, r3);
    }

    private /* synthetic */ void lambda$createView$9(View r1) {
        finishPreviewFragment();
    }

    private /* synthetic */ void lambda$deleteTopics$12() {
        this.excludeTopics = null;
        updateTopicsList(true, false);
    }

    private /* synthetic */ void lambda$deleteTopics$13(ArrayList r4, Runnable r5) {
        this.topicsController.deleteTopics(this.chatId, r4);
        r5.run();
    }

    private /* synthetic */ void lambda$deleteTopics$14(HashSet r3, ArrayList r4, Runnable r5, AlertDialog r6, int r7) {
        HashSet<Integer> r72 = new HashSet();
        this.excludeTopics = r72;
        r72.addAll(r3);
        updateTopicsList(true, false);
        BulletinFactory.of(this).createUndoBulletin(LocaleController.getPluralString("TopicsDeleted", r3.size()), new TopicsFragment$$ExternalSyntheticLambda25(this), new TopicsFragment$$ExternalSyntheticLambda26(this, r4, r5)).show();
        clearSelectedTopics();
        r6.dismiss();
    }

    private /* synthetic */ void lambda$getThemeDescriptions$24() {
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L26;
        if (r1 != 0) goto L7;
        ViewGroup r2 = this.recyclerListView;
    L11:
        if (r2 == null) goto L25;
        int r3 = r2.getChildCount();
        int r4 = 0;
    L14:
        if (r4 >= r3) goto L25;
        View r5 = r2.getChildAt(r4);
        if ((r5 instanceof ProfileSearchCell) == false) goto L19;
        ((ProfileSearchCell) r5).update(0);
    L24:
        r4 = r4 + 1;
        goto L14
    L19:
        if ((r5 instanceof DialogCell) == false) goto L22;
        ((DialogCell) r5).update(0);
        goto L24
    L22:
        if ((r5 instanceof UserCell) == false) goto L24;
        ((UserCell) r5).update(0);
    L25:
        r1 = r1 + 1;
        goto L4
    L7:
        MessagesSearchContainer r22 = this.searchContainer;
        if (r22 == null) goto L10;
        r2 = r22.recyclerView;
        goto L11
    L10:
        r2 = null;
        goto L11
    L26:
        ActionBar r12 = this.actionBar;
        if (r12 == null) goto L29;
        r12.setPopupBackgroundColor(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground), true);
        this.actionBar.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem), false, true);
        this.actionBar.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), true, true);
        this.actionBar.setPopupItemsSelectorColor(getThemedColor(Theme.key_dialogButtonSelector), true);
    L29:
        View r0 = this.blurredView;
        if (r0 == null) goto L32;
        r0.setForeground(new ColorDrawable(ColorUtils.setAlphaComponent(getThemedColor(Theme.key_windowBackgroundWhite), 100)));
    L32:
        updateColors();
    }

    private /* synthetic */ void lambda$joinToGroup$21() {
        this.joinRequested = false;
        updateChatInfo(true);
    }

    private /* synthetic */ boolean lambda$joinToGroup$22(TLRPC.TL_error r5) {
        if (r5 != null) goto L5;
    L8:
        return true;
    L5:
        if ("INVITE_REQUEST_SENT".equals(r5.text) == false) goto L8;
        MessagesController.getNotificationsSettings(this.currentAccount).edit().putLong("dialog_join_requested_time_" + (-this.chatId), System.currentTimeMillis()).apply();
        JoinGroupAlert.showBulletin(getContext(), this, ChatObject.isChannelAndNotMegaGroup(getCurrentChat()));
        updateChatInfo(true);
        return false;
    }

    private /* synthetic */ void lambda$showChatPreview$16(TLRPC.TL_forumTopic r7, View r8) {
        this.scrollToTop = true;
        this.updateAnimated = true;
        this.topicsController.pinTopic(this.chatId, r7.id, !r7.pinned, this);
        finishPreviewFragment();
    }

    private /* synthetic */ void lambda$showChatPreview$17(TLRPC.TL_forumTopic r8, ActionBarPopupWindow.ActionBarPopupWindowLayout[] r9, int r10, View r11) {
        if (getMessagesController().isDialogMuted(-this.chatId, r8.id) == false) goto L8;
        getNotificationsController().muteDialog(-this.chatId, r8.id, false);
        finishPreviewFragment();
        if (BulletinFactory.canShowBulletin(this) == false) goto L10;
        BulletinFactory.createMuteBulletin(this, 4, 0, getResourceProvider()).show();
        return;
    L10:
        return;
    L8:
        r9[0].getSwipeBack().openForeground(r10);
    }

    private /* synthetic */ void lambda$showChatPreview$18(TLRPC.TL_forumTopic r5, View r6) {
        this.updateAnimated = true;
        this.topicsController.toggleCloseTopic(this.chatId, r5.id, !r5.closed);
        finishPreviewFragment();
    }

    private /* synthetic */ void lambda$showChatPreview$19(TLRPC.TL_forumTopic r1, View r2) {
        HashSet<Integer> r22 = new HashSet();
        r22.add(Integer.valueOf(r1.id));
        deleteTopics(r22, new TopicsFragment$$ExternalSyntheticLambda28(this));
    }

    private WindowInsetsCompat onApplyWindowInsets(View r2, WindowInsetsCompat r3) {
        int r22 = r3.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
        this.navigationBarHeight = r22;
        MessagesSearchContainer r32 = this.searchContainer;
        if (r32 == null) goto L5;
        r32.setPadding(0, 0, 0, r22);
    L5:
        EmptyViewContainer r23 = this.emptyViewContainer;
        if (r23 == null) goto L8;
        r23.textView.setTranslationY((-this.navigationBarHeight) - this.additionFloatingButtonOffset);
    L8:
        updateFloatingButtonOffset();
        checkUi_listViewPadding();
        return WindowInsetsCompat.CONSUMED;
    }

    private void onDialogAnimationFinished() {
        this.dialogRemoveFinished = 0;
        this.dialogInsertFinished = 0;
        this.dialogChangeFinished = 0;
        AndroidUtilities.runOnUIThread(new TopicsFragment$$ExternalSyntheticLambda27());
    }

    private void openParentSearch() {
        DialogsActivity r0 = this.parentDialogsActivity;
        if (r0 == null) goto L8;
        ActionBarMenuItem r02 = r0.searchItem;
        if (r02 == null) goto L9;
        r02.performClick();
        return;
    L9:
        return;
    }

    private void openProfile(boolean r5) {
        if (r5 == false) goto L10;
        TLRPC.Chat r0 = getCurrentChat();
        if (r0 == null) goto L10;
        TLRPC.ChatPhoto r02 = r0.photo;
        if (r02 != null) goto L8;
    L9:
        r5 = false;
        goto L10
    L8:
        if ((r02 instanceof TLRPC.TL_chatPhotoEmpty) == true) goto L9;
    L10:
        Bundle r03 = new Bundle();
        r03.putLong("chat_id", this.chatId);
        ProfileActivity r1 = new ProfileActivity(r03, this.avatarContainer.getSharedMediaPreloader());
        r1.setChatInfo(this.chatFull);
        if (this.fragmentView.getMeasuredHeight() > this.fragmentView.getMeasuredWidth()) goto L13;
    L16:
        int r52 = 1;
    L17:
        r1.setPlayProfileAnimation(r52);
        presentFragment(r1);
        return;
    L13:
        if (this.avatarContainer.getAvatarImageView().getImageReceiver().hasImageLoaded() == false) goto L16;
        if (r5 == false) goto L16;
        r52 = 2;
        goto L17
    }

    private void prepareBlurBitmap() {
        if (this.blurredView != null) goto L5;
        return;
    L5:
        if (this.parentLayout == null) goto L13;
        int r0 = (int) (this.fragmentView.getMeasuredWidth() / 6.0f);
        int r1 = (int) (this.fragmentView.getMeasuredHeight() / 6.0f);
        Bitmap r2 = Bitmap.createBitmap(r0, r1, Bitmap.Config.ARGB_8888);
        Canvas r3 = new Canvas(r2);
        r3.scale(0.16666667f, 0.16666667f);
        this.parentLayout.getView().draw(r3);
        Utilities.stackBlurBitmap(r2, Math.max(7, Math.max(r0, r1) / 180));
        this.blurredView.setBackground(new BitmapDrawable(r2));
        this.blurredView.setAlpha(0.0f);
        if (this.blurredView.getParent() == null) goto L10;
        ((ViewGroup) this.blurredView.getParent()).removeView(this.blurredView);
    L10:
        this.parentLayout.getOverlayContainerView().addView(this.blurredView, LayoutHelper.createFrame(-1, -1.0f));
        return;
    }

    public static void prepareToSwitchAnimation(ChatActivity r6) {
        if (r6.getParentLayout() != null) goto L6;
        return;
    L6:
        if (r6.getParentLayout().getFragmentStack().size() <= 1) goto L12;
        BaseFragment r0 = r6.getParentLayout().getFragmentStack().get(r6.getParentLayout().getFragmentStack().size() - 2);
        if ((r0 instanceof TopicsFragment) == false) goto L12;
        if (((TopicsFragment) r0).chatId != (-r6.getDialogId())) goto L12;
    L13:
        r6.setSwitchFromTopics(true);
        r6.finishFragment();
        return;
    L12:
        Bundle r02 = new Bundle();
        r02.putLong("chat_id", -r6.getDialogId());
        r6.getParentLayout().addFragmentToStack(new TopicsFragment(r02), r6.getParentLayout().getFragmentStack().size() - 1);
        goto L13
    }

    private void setButtonType(int r3) {
        if (this.bottomButtonType == r3) goto L14;
        this.bottomButtonType = r3;
        UnreadCounterTextView r0 = this.bottomOverlayChatText;
        if (r3 != 0) goto L7;
        int r1 = Theme.key_chat_fieldOverlayText;
    L8:
        r0.setTextColorKey(r1);
        ImageView r02 = this.closeReportSpam;
        if (r3 != 1) goto L11;
        int r32 = 0;
    L12:
        r02.setVisibility(r32);
        updateChatInfo();
        return;
    L11:
        r32 = 8;
        goto L12
    L7:
        r1 = Theme.key_text_RedBold;
        goto L8
    }

    private void setFragmentIsSliding(boolean r4) {
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L5;
        return;
    L5:
        SizeNotifierFrameLayout r0 = this.contentView;
        if (r0 != null) goto L8;
    L11:
        SizeNotifierFrameLayout r42 = this.contentView;
        if (r42 == null) goto L14;
        r42.requestLayout();
    L14:
        this.actionBar.requestLayout();
        return;
    L8:
        if (r4 == false) goto L10;
        r0.setLayerType(2, null);
        r0.setClipChildren(false);
        r0.setClipToPadding(false);
        goto L11
    L10:
        r0.setLayerType(0, null);
        r0.setClipChildren(true);
        r0.setClipToPadding(true);
        goto L11
    }

    private void setSlideTransitionProgress(float r4) {
        if (SharedConfig.getDevicePerformanceClass() == 0) goto L13;
        this.slideFragmentProgress = r4;
        View r42 = this.fragmentView;
        if (r42 == null) goto L8;
        r42.invalidate();
    L8:
        TopicsRecyclerView r43 = this.recyclerListView;
        if (r43 == null) goto L12;
        float r1 = 1.0f - ((1.0f - this.slideFragmentProgress) * 0.05f);
        r43.setPivotX(0.0f);
        r43.setPivotY(0.0f);
        r43.setScaleX(r1);
        r43.setScaleY(r1);
        this.actionBar.setPivotX(0.0f);
        this.actionBar.setPivotY(0.0f);
        this.actionBar.setScaleX(r1);
        this.actionBar.setScaleY(r1);
        return;
    L12:
        return;
    }

    private boolean showChatPreview(DialogCell r15) {
        r15.performHapticFeedback(0);     // Catch: Exception -> L31
    L4:
        ActionBarPopupWindow.ActionBarPopupWindowLayout[] r1 = {new ActionBarPopupWindow.ActionBarPopupWindowLayout(getParentActivity(), R.drawable.popup_fixed_alert, getResourceProvider(), 1)};
        TLRPC.TL_forumTopic r2 = r15.forumTopic;
        ChatNotificationsPopupWrapper r6 = new ChatNotificationsPopupWrapper(getContext(), this.currentAccount, r1[0].getSwipeBack(), false, false, new AnonymousClass19(this, r2), getResourceProvider());
        int r3 = r1[0].addViewToSwipeBack(r6.windowLayout);
        r6.type = 1;
        r6.update(-this.chatId, r2.id, null);
        if (ChatObject.canManageTopics(getCurrentChat()) == false) goto L11;
        ActionBarMenuSubItem r4 = new ActionBarMenuSubItem(getParentActivity(), true, false);
        if (r2.pinned == false) goto L9;
        r4.setTextAndIcon(LocaleController.getString(R.string.DialogUnpin), R.drawable.msg_unpin);
    L10:
        r4.setMinimumWidth(160);
        r4.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda20(this, r2));
        r1[0].addView(r4);
        goto L11
    L9:
        r4.setTextAndIcon(LocaleController.getString(R.string.DialogPin), R.drawable.msg_pin);
    L11:
        ActionBarMenuSubItem r42 = new ActionBarMenuSubItem(getParentActivity(), false, false);
        if (getMessagesController().isDialogMuted(-this.chatId, r2.id) == false) goto L14;
        r42.setTextAndIcon(LocaleController.getString(R.string.Unmute), R.drawable.msg_mute);
    L15:
        r42.setMinimumWidth(160);
        r42.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda21(this, r2, r1, r3));
        r1[0].addView(r42);
        if (ChatObject.canManageTopic(this.currentAccount, getCurrentChat(), r2) == false) goto L23;
        ActionBarMenuSubItem r32 = new ActionBarMenuSubItem(getParentActivity(), false, false);
        if (r2.closed == false) goto L20;
        r32.setTextAndIcon(LocaleController.getString(R.string.RestartTopic), R.drawable.msg_topic_restart);
    L21:
        r32.setMinimumWidth(160);
        r32.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda22(this, r2));
        r1[0].addView(r32);
        goto L23
    L20:
        r32.setTextAndIcon(LocaleController.getString(R.string.CloseTopic), R.drawable.msg_topic_close);
    L23:
        if (ChatObject.canDeleteTopic(this.currentAccount, getCurrentChat(), r2) == false) goto L25;
        ActionBarMenuSubItem r33 = new ActionBarMenuSubItem(getParentActivity(), false, true);
        r33.setTextAndIcon(LocaleController.getPluralString("DeleteTopics", 1), R.drawable.msg_delete);
        r33.setIconColor(getThemedColor(Theme.key_text_RedRegular));
        r33.setTextColor(getThemedColor(Theme.key_text_RedBold));
        r33.setMinimumWidth(160);
        r33.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda23(this, r2));
        r1[0].addView(r33);
    L25:
        boolean r22 = getMessagesController().isMonoForum(-this.chatId);
        prepareBlurBitmap();
        Bundle r34 = new Bundle();
        r34.putLong("chat_id", this.chatId);
        ChatActivity r43 = new ChatActivity(r34);
        long r5 = -this.chatId;
        TLRPC.TL_forumTopic r152 = r15.forumTopic;
        if (r22 == false) goto L28;
        long r23 = DialogObject.getPeerDialogId(r152.from_id);
    L29:
        ForumUtilities.applyTopic(r43, MessagesStorage.TopicKey.of(r5, r23));
        presentFragmentAsPreviewWithMenu(r43, r1[0]);
        return false;
    L28:
        r23 = r152.id;
        goto L29
    L14:
        r42.setTextAndIcon(LocaleController.getString(R.string.Mute), R.drawable.msg_unmute);
        goto L15
    }

    private void startMultiselect(int r5) {
        TLRPC.TL_forumTopic r0 = getTopicForMultiselect(r5);
        if (r0 != null) goto L5;
        return;
    L5:
        this.recyclerListView.startMultiselect(r5, false, new AnonymousClass21(this, !this.selectedTopics.contains(Integer.valueOf(r0.id)), new HashSet(this.selectedTopics)));
    }

    private void toggleSelection(View r2) {
        if ((r2 instanceof TopicDialogCell) == false) goto L5;
        TLRPC.TL_forumTopic r0 = ((TopicDialogCell) r2).forumTopic;
    L6:
        toggleSelection(r0, r2);
        return;
    L5:
        r0 = null;
        goto L6
    }

    private void toggleSelection(TLRPC.TL_forumTopic r16, View r17) {
        if (r16 != null) goto L5;
        return;
    L5:
        int r1 = r16.id;
        if (this.selectedTopics.remove(Integer.valueOf(r1)) == true) goto L9;
        this.selectedTopics.add(Integer.valueOf(r1));
    L9:
        if ((r17 instanceof TopicDialogCell) == false) goto L11;
        ((TopicDialogCell) r17).setChecked(this.selectedTopics.contains(Integer.valueOf(r1)), true);
    L11:
        TLRPC.Chat r12 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (this.selectedTopics.isEmpty() == true) goto L115;
        chekActionMode();
        if (this.inPreviewMode == false) goto L16;
        ((View) this.fragmentView.getParent()).invalidate();
    L16:
        this.actionBar.showActionMode(true);
        Iterator<Integer> r2 = this.selectedTopics.iterator();
        int r3 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
    L18:
        if (r2.hasNext() == false) goto L35;
        long r13 = r2.next().intValue();
        TLRPC.TL_forumTopic r9 = this.topicsController.findTopic(this.chatId, r13);
        if (r9 == null) goto L33;
        if (r9.unread_count == 0) goto L25;
        r5 = r5 + 1;
    L25:
        if (ChatObject.canManageTopics(r12) == false) goto L33;
        if (r9.hidden == true) goto L33;
        if (r9.pinned == false) goto L31;
        r8 = r8 + 1;
        goto L33
    L31:
        r7 = r7 + 1;
    L33:
        if (getMessagesController().isDialogMuted(-this.chatId, r13) == false) goto L18;
        r6 = r6 + 1;
        goto L18
    L35:
        ActionBarMenuSubItem r22 = this.readItem;
        if (r5 <= 0) goto L38;
        r22.setVisibility(0);
        this.readItem.setTextAndIcon(LocaleController.getString(R.string.MarkAsRead), R.drawable.msg_markread);
    L39:
        if (r6 == 0) goto L41;
        this.mute = false;
        this.muteItem.setIcon(R.drawable.msg_unmute);
        this.muteItem.setContentDescription(LocaleController.getString(R.string.ChatsUnmute));
    L42:
        ActionBarMenuItem r23 = this.pinItem;
        if (r7 != 1) goto L46;
        if (r8 != 0) goto L46;
        int r52 = 0;
    L47:
        r23.setVisibility(r52);
        ActionBarMenuItem r24 = this.unpinItem;
        if (r8 != 1) goto L51;
        if (r7 != 0) goto L51;
        int r53 = 0;
    L52:
        r24.setVisibility(r53);
        this.selectedDialogsCountTextView.setNumber(this.selectedTopics.size(), true);
        Iterator<Integer> r25 = this.selectedTopics.iterator();
        int r54 = 0;
        int r62 = 0;
        int r72 = 0;
        int r82 = 0;
        int r10 = 0;
    L54:
        if (r25.hasNext() == false) goto L77;
        int r172 = r10;
        TLRPC.TL_forumTopic r92 = this.topicsController.findTopic(this.chatId, r25.next().intValue());
        if (r92 != null) goto L58;
    L75:
        r10 = r172;
        goto L54
    L58:
        if (ChatObject.canDeleteTopic(this.currentAccount, r12, r92) == false) goto L61;
        r72 = r72 + 1;
    L61:
        if (ChatObject.canManageTopic(this.currentAccount, r12, r92) == false) goto L75;
        if (r92.id == 1) goto L65;
    L68:
        r10 = r172;
    L70:
        if (r92.hidden == true) goto L54;
        if (r92.closed == false) goto L74;
        r54 = r54 + 1;
        goto L54
    L74:
        r62 = r62 + 1;
        goto L54
    L65:
        if (r92.hidden == false) goto L67;
        r10 = r172 + 1;
        goto L70
    L67:
        r82 = r82 + 1;
        goto L68
    L77:
        int r173 = r10;
        ActionBarMenuSubItem r14 = this.closeTopic;
        if (r54 != 0) goto L81;
        if (r62 <= 0) goto L81;
        int r26 = 0;
    L82:
        r14.setVisibility(r26);
        ActionBarMenuSubItem r15 = this.closeTopic;
        if (r62 <= 1) goto L86;
        int r27 = R.string.CloseTopics;
    L87:
        r15.setText(LocaleController.getString(r27));
        ActionBarMenuSubItem r18 = this.restartTopic;
        if (r62 != 0) goto L91;
        if (r54 <= 0) goto L91;
        int r28 = 0;
    L92:
        r18.setVisibility(r28);
        ActionBarMenuSubItem r19 = this.restartTopic;
        if (r54 <= 1) goto L96;
        int r29 = R.string.RestartTopics;
    L97:
        r19.setText(LocaleController.getString(r29));
        ActionBarMenuItem r110 = this.deleteItem;
        if (r72 != this.selectedTopics.size()) goto L100;
        int r210 = 0;
    L101:
        r110.setVisibility(r210);
        ActionBarMenuItem r111 = this.hideItem;
        if (r82 == 1) goto L104;
    L106:
        int r211 = 8;
    L107:
        r111.setVisibility(r211);
        ActionBarMenuItem r112 = this.showItem;
        if (r173 == 1) goto L110;
    L112:
        r3 = 8;
    L113:
        r112.setVisibility(r3);
        this.otherItem.checkHideMenuItem();
        updateReordering();
        return;
    L110:
        if (this.selectedTopics.size() != 1) goto L112;
    L104:
        if (this.selectedTopics.size() != 1) goto L106;
        r211 = 0;
        goto L107
    L100:
        r210 = 8;
        goto L101
    L96:
        r29 = R.string.RestartTopic;
    L91:
        r28 = 8;
        goto L92
    L86:
        r27 = R.string.CloseTopic;
    L81:
        r26 = 8;
    L51:
        r53 = 8;
    L46:
        r52 = 8;
        goto L47
    L41:
        this.mute = true;
        this.muteItem.setIcon(R.drawable.msg_mute);
        this.muteItem.setContentDescription(LocaleController.getString(R.string.ChatsMute));
        goto L42
    L38:
        r22.setVisibility(8);
        goto L39
    L115:
        this.actionBar.hideActionMode();
    }

    private void updateChatInfo() {
        updateChatInfo(false);
    }

    private void updateChatInfo(boolean r15) {
        if (this.fragmentView != null) goto L5;
        return;
    L5:
        if (this.avatarContainer == null) goto L145;
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        TLRPC.User r1 = getMessagesController().getUser(Long.valueOf(-this.chatId));
        if (UserObject.isBotForum(r1) == false) goto L11;
        this.avatarContainer.setUserAvatar(r1);
    L16:
        long r12 = -this.chatId;
        SharedPreferences r3 = MessagesController.getNotificationsSettings(this.currentAccount);
        if (r3.getInt("dialog_bar_vis3" + r12, 0) != 2) goto L19;
        boolean r13 = true;
    L20:
        boolean r4 = r3.getBoolean("dialog_bar_report" + (-this.chatId), false);
        boolean r32 = r3.getBoolean("dialog_bar_block" + (-this.chatId), false);
        int r10 = 8;
        if (this.openedForSelect == true) goto L30;
        if (r0 == null) goto L28;
        this.avatarContainer.setTitle(r0.title);
        if (getMessagesController().isDialogMuted(-this.chatId, 0) == false) goto L26;
        Drawable r6 = getThemedDrawable("drawableMuteIcon");
    L27:
        this.avatarContainer.setTitleIcons(null, r6);
        goto L28
    L26:
        r6 = null;
    L28:
        updateSubtitle();
    L56:
        if (this.fragmentBeginToShow == true) goto L60;
        if (r15 == true) goto L60;
        boolean r152 = false;
    L61:
        long r11 = MessagesController.getNotificationsSettings(this.currentAccount).getLong("dialog_join_requested_time_" + (-this.chatId), -1);
        if (r0 != null) goto L64;
    L71:
        if (r0 != null) goto L73;
    L87:
        if (r13 == false) goto L91;
        if (r32 == true) goto L90;
        if (r4 == false) goto L91;
    L90:
        this.bottomOverlayChatText.setText(LocaleController.getString(R.string.ReportSpamAndLeaveNoCaps));
        this.bottomOverlayChatText.setClickable(true);
        this.bottomOverlayChatText.setEnabled(true);
        AndroidUtilities.updateViewVisibilityAnimated(this.bottomOverlayProgress, false, 0.5f, false);
        AndroidUtilities.updateViewVisibilityAnimated(this.bottomOverlayChatText, true, 0.5f, false);
        setButtonType(1);
    L70:
        boolean r14 = true;
    L93:
        if (this.bottomPannelVisible == r14) goto L109;
        this.bottomPannelVisible = r14;
        this.bottomOverlayContainer.animate().setListener(null).cancel();
        FrameLayout r33 = this.bottomOverlayContainer;
        float r62 = 0.0f;
        if (r152 == true) goto L104;
        if (r14 == false) goto L98;
        int r153 = 0;
    L99:
        r33.setVisibility(r153);
        FrameLayout r154 = this.bottomOverlayContainer;
        if (r14 == true) goto L103;
        r62 = AndroidUtilities.dp(53.0f);
    L103:
        r154.setTranslationY(r62);
        goto L109
    L98:
        r153 = 8;
        goto L99
    L104:
        ViewPropertyAnimator r155 = r33.animate();
        if (r14 == true) goto L108;
        r62 = AndroidUtilities.dp(53.0f);
    L108:
        r155.translationY(r62).setListener(new AnonymousClass22(this, r14));
    L109:
        checkUi_listViewPadding();
        ActionBarMenuItem r156 = this.other;
        if (this.openedForSelect == false) goto L112;
        int r16 = 8;
    L113:
        r156.setVisibility(r16);
        ActionBarMenuSubItem r157 = this.addMemberSubMenu;
        if (ChatObject.canAddUsers(r0) == false) goto L116;
        int r17 = 0;
    L117:
        r157.setVisibility(r17);
        ActionBarMenuSubItem r158 = this.boostGroupSubmenu;
        if (ChatObject.isBoostSupported(r0) == true) goto L120;
    L126:
        int r18 = 8;
    L127:
        r158.setVisibility(r18);
        ActionBarMenuSubItem r159 = this.deleteChatSubmenu;
        if (r0 != null) goto L130;
    L134:
        int r19 = 8;
    L135:
        r159.setVisibility(r19);
        ActionBarMenuSubItem r1510 = this.reportSubmenu;
        if (r0 != null) goto L138;
    L142:
        r1510.setVisibility(r10);
        updateCreateTopicButton(true);
        this.groupCall = getMessagesController().getGroupCall(this.chatId, true);
        checkGroupCallJoin(false);
        return;
    L138:
        if (r0.creator == true) goto L142;
        if (ChatObject.hasAdminRights(r0) == true) goto L142;
        r10 = 0;
        goto L142
    L130:
        if (r0.creator == true) goto L134;
        if (ChatObject.isNotInChat(r0) == true) goto L134;
        r19 = 0;
        goto L135
    L120:
        if (getUserConfig().isPremium() == false) goto L122;
    L125:
        r18 = 0;
        goto L127
    L122:
        if (ChatObject.isBoosted(this.chatFull) == true) goto L125;
        if (ChatObject.hasAdminRights(r0) == false) goto L126;
    L116:
        r17 = 8;
        goto L117
    L112:
        r16 = 0;
    L91:
        r14 = false;
        goto L93
    L73:
        if (this.openedForSelect == true) goto L87;
        if (ChatObject.isNotInChat(r0) == true) goto L79;
        if (getMessagesController().isJoiningChannel(r0.id) == false) goto L87;
    L79:
        if (getMessagesController().isJoiningChannel(r0.id) == false) goto L81;
        boolean r110 = true;
    L86:
        AndroidUtilities.updateViewVisibilityAnimated(this.bottomOverlayProgress, r110, 0.5f, r152);
        AndroidUtilities.updateViewVisibilityAnimated(this.bottomOverlayChatText, !r110, 0.5f, r152);
        setButtonType(0);
        goto L70
    L81:
        boolean r111 = r0.join_request;
        UnreadCounterTextView r34 = this.bottomOverlayChatText;
        if (r111 == false) goto L84;
        r34.setText(LocaleController.getString(R.string.ChannelJoinRequest));
    L85:
        this.bottomOverlayChatText.setClickable(true);
        this.bottomOverlayChatText.setEnabled(true);
        r110 = false;
        goto L86
    L84:
        r34.setText(LocaleController.getString(R.string.ChannelJoin));
        goto L85
    L64:
        if (ChatObject.isNotInChat(r0) == false) goto L71;
        if (r11 <= 0) goto L71;
        if ((System.currentTimeMillis() - r11) >= 120000) goto L71;
        this.bottomOverlayChatText.setText(LocaleController.getString(R.string.ChannelJoinRequestSent), r152);
        this.bottomOverlayChatText.setEnabled(false);
        AndroidUtilities.updateViewVisibilityAnimated(this.bottomOverlayProgress, false, 0.5f, r152);
        AndroidUtilities.updateViewVisibilityAnimated(this.bottomOverlayChatText, true, 0.5f, r152);
        setButtonType(0);
    L60:
        r152 = true;
        goto L61
    L30:
        if (this.openedForReply == false) goto L33;
        this.avatarContainer.setTitle(LocaleController.getString(R.string.ReplyToDialog));
    L42:
        this.searchItem.setVisibility(8);
        ChatAvatarContainer r63 = this.avatarContainer;
        if (r63 != null) goto L45;
    L51:
        ChatAvatarContainer r64 = this.avatarContainer;
        if (r64 == null) goto L54;
        r64.updateSubtitle();
    L54:
        this.avatarContainer.getSubtitleTextView().setVisibility(8);
        goto L56
    L45:
        if (r63.getLayoutParams() == null) goto L51;
        ViewGroup.MarginLayoutParams r65 = (ViewGroup.MarginLayoutParams) this.avatarContainer.getLayoutParams();
        if (this.searchItem.getVisibility() != 0) goto L49;
        float r112 = 86.0f;
    L50:
        r65.rightMargin = AndroidUtilities.dp(r112);
        goto L51
    L49:
        r112 = 40.0f;
        goto L50
    L33:
        if (this.openedForQuote == false) goto L36;
        this.avatarContainer.setTitle(LocaleController.getString(R.string.QuoteTo));
        goto L42
    L36:
        if (this.openedForBotShare == false) goto L38;
        this.avatarContainer.setTitle(LocaleController.getString(R.string.BotShareToTopic));
        goto L42
    L38:
        boolean r66 = this.openedForForward;
        ChatAvatarContainer r113 = this.avatarContainer;
        if (r66 == false) goto L41;
        r113.setTitle(LocaleController.getString(R.string.ForwardTo));
        goto L42
    L41:
        r113.setTitle(LocaleController.getString(R.string.SelectTopic));
        goto L42
    L19:
        r13 = false;
        goto L20
    L11:
        if (ChatObject.isMonoForum(r0) == false) goto L15;
        TLRPC.Chat r114 = getMessagesController().getChat(Long.valueOf(r0.linked_monoforum_id));
        if (r114 == null) goto L16;
        this.avatarContainer.setChatAvatar(r114);
        goto L16
    L15:
        this.avatarContainer.setChatAvatar(r0);
        goto L16
    }

    private void updateColors() {
        RadialProgressView r0 = this.bottomOverlayProgress;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgressColor(getThemedColor(Theme.key_chat_fieldOverlayText));
        this.floatingButton.updateColors();
        FrameLayout r02 = this.bottomOverlayContainer;
        int r1 = Theme.key_windowBackgroundWhite;
        r02.setBackgroundColor(getThemedColor(r1));
        this.actionBar.setActionModeColor(getThemedColor(r1));
        if (this.inPreviewMode == true) goto L8;
        this.actionBar.setBackgroundColor(getThemedColor(Theme.key_actionBarDefault));
    L8:
        this.searchContainer.setBackgroundColor(getThemedColor(r1));
    }

    private void updateCreateTopicButton(boolean r5) {
        if (this.createTopicSubmenu != null) goto L5;
        return;
    L5:
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        int r2 = 0;
        if (ChatObject.isNotInChat(getMessagesController().getChat(Long.valueOf(this.chatId))) == false) goto L8;
    L16:
        boolean r02 = false;
    L17:
        this.canShowCreateTopic = r02;
        ActionBarMenuSubItem r1 = this.createTopicSubmenu;
        if (r02 == true) goto L21;
        r2 = 8;
    L21:
        r1.setVisibility(r2);
        hideFloatingButton(!this.canShowCreateTopic, r5);
        return;
    L8:
        if (ChatObject.canCreateTopic(r0) == false) goto L16;
        if (this.searching == true) goto L16;
        if (this.openedForSelect == true) goto L16;
        if (this.loadingTopics == true) goto L16;
        r02 = true;
        goto L17
    }

    private void updateFloatingButtonOffset() {
        this.floatingButton.setTranslationY(((-this.transitionPadding) - this.navigationBarHeight) - this.additionFloatingButtonOffset);
    }

    private void updateSearchProgress(float r6) {
        this.searchAnimationProgress = r6;
        int r0 = getThemedColor(Theme.key_actionBarDefaultIcon);
        ActionBar r1 = this.actionBar;
        int r2 = Theme.key_actionBarActionModeDefaultIcon;
        r1.setItemsColor(ColorUtils.blendARGB(r0, getThemedColor(r2), this.searchAnimationProgress), false);
        this.actionBar.setItemsColor(ColorUtils.blendARGB(getThemedColor(r2), getThemedColor(r2), this.searchAnimationProgress), true);
        this.actionBar.setItemsBackgroundColor(ColorUtils.blendARGB(getThemedColor(Theme.key_actionBarDefaultSelector), getThemedColor(Theme.key_actionBarActionModeDefaultSelector), this.searchAnimationProgress), false);
        if (this.inPreviewMode == true) goto L5;
        this.actionBar.setBackgroundColor(ColorUtils.blendARGB(getThemedColor(Theme.key_actionBarDefault), getThemedColor(Theme.key_windowBackgroundWhite), this.searchAnimationProgress));
    L5:
        float r22 = 1.0f - r6;
        this.avatarContainer.getTitleTextView().setAlpha(r22);
        this.avatarContainer.getSubtitleTextView().setAlpha(r22);
        ViewPagerFixed.TabsView r02 = this.searchTabsView;
        if (r02 == null) goto L8;
        r02.setTranslationY((-AndroidUtilities.dp(16.0f)) * r22);
        this.searchTabsView.setAlpha(r6);
    L8:
        this.searchContainer.setTranslationY((-AndroidUtilities.dp(16.0f)) * r22);
        this.searchContainer.setAlpha(r6);
        if (isInPreviewMode() == false) goto L11;
        this.fullscreenView.invalidate();
    L11:
        this.contentView.invalidate();
        this.recyclerListView.setAlpha(r22);
        if (this.animateSearchWithScale == false) goto L15;
        float r12 = ((1.0f - this.searchAnimationProgress) * 0.02f) + 0.98f;
        this.recyclerListView.setScaleX(r12);
        this.recyclerListView.setScaleY(r12);
        return;
    }

    private void updateSubtitle() {
        TLRPC.ChatFull r0 = getMessagesController().getChatFull(this.chatId);
        if (r0 == null) goto L9;
        TLRPC.ChatFull r1 = this.chatFull;
        if (r1 == null) goto L9;
        TLRPC.ChatParticipants r12 = r1.participants;
        if (r12 == null) goto L9;
        r0.participants = r12;
    L9:
        this.chatFull = r0;
        if (r0 == null) goto L21;
        int r02 = r0.participants_count;
        if (r02 > 0) goto L20;
        TLRPC.Chat r03 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (r03 != null) goto L17;
        String r04 = LocaleController.getString(R.string.Loading);
    L22:
        this.avatarContainer.setSubtitle(r04);
        return;
    L17:
        if (ChatObject.isPublic(r03) == false) goto L19;
        r04 = LocaleController.getString(R.string.MegaPublic).toLowerCase();
        goto L22
    L19:
        r04 = LocaleController.getString(R.string.MegaPrivate).toLowerCase();
        goto L22
    L20:
        r04 = LocaleController.formatPluralString("Members", r02, new Object[0]);
        goto L22
    L21:
        r04 = LocaleController.getString(R.string.Loading);
        goto L22
    }

    private void updateTopicsEmptyViewText() {
        StickerEmptyView r0 = this.topicsEmptyView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.subtitle == null) goto L17;
        SpannableStringBuilder r02 = new SpannableStringBuilder("d");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.ic_ab_other);
        r1.setSize(AndroidUtilities.dp(16.0f));
        r02.setSpan(r1, 0, 1, 0);
        if (ChatObject.canUserDoAdminAction(getCurrentChat(), 15) == false) goto L11;
        this.topicsEmptyView.subtitle.setText(AndroidUtilities.replaceCharSequence("%s", AndroidUtilities.replaceTags(LocaleController.getString(R.string.NoTopicsDescription)), r02));
        return;
    L11:
        String r03 = LocaleController.getString(R.string.General);
        TLRPC.TL_forumTopic r12 = getMessagesController().getTopicsController().findTopic(this.chatId, 1);
        if (r12 == null) goto L14;
        r03 = r12.title;
    L14:
        this.topicsEmptyView.subtitle.setText(AndroidUtilities.replaceTags(LocaleController.formatString("NoTopicsDescriptionUser", R.string.NoTopicsDescriptionUser, new Object[]{r03})));
        return;
    }

    private void updateTopicsList(boolean r11, boolean r12) {
        if (r11 == false) goto L5;
    L7:
        this.updateAnimated = false;
        ArrayList<TLRPC.TL_forumTopic> r2 = this.topicsController.getTopics(this.chatId);
        if (r2 == null) goto L67;
        int r3 = this.forumTopics.size();
        ArrayList<? extends AdapterWithDiffUtils.Item> r4 = new ArrayList(this.forumTopics);
        this.forumTopics.clear();
        DefaultItemAnimator r6 = null;
        if (UserObject.isBotForum(this.currentAccount, -this.chatId) == true) goto L12;
    L14:
        int r5 = 0;
    L16:
        if (r5 >= r2.size()) goto L25;
        HashSet<Integer> r7 = this.excludeTopics;
        if (r7 != null) goto L20;
    L22:
        this.forumTopics.add(new Item(this, 0, r2.get(r5)));
    L23:
        r5 = r5 + 1;
        goto L16
    L20:
        if (r7.contains(Integer.valueOf(r2.get(r5).id)) == false) goto L22;
    L25:
        if (this.forumTopics.isEmpty() == false) goto L27;
    L31:
        int r22 = this.forumTopics.size();
        if (this.fragmentBeginToShow == false) goto L36;
        if (r12 == false) goto L36;
        if (r22 <= r3) goto L36;
        this.itemsEnterAnimator.showItemsAnimated(r3 + 4);
        r11 = false;
    L36:
        this.hiddenCount = 0;
        int r122 = 0;
    L38:
        if (r122 >= this.forumTopics.size()) goto L47;
        Item r23 = this.forumTopics.get(r122);
        if (r23 == null) goto L46;
        TLRPC.TL_forumTopic r24 = r23.topic;
        if (r24 == null) goto L46;
        if (r24.hidden == false) goto L46;
        this.hiddenCount++;
    L46:
        r122 = r122 + 1;
        goto L38
    L47:
        TopicsRecyclerView r123 = this.recyclerListView;
        if (r123 == null) goto L58;
        RecyclerView.ItemAnimator r124 = r123.getItemAnimator();
        if (r11 == false) goto L52;
        DefaultItemAnimator r0 = this.itemAnimator;
    L53:
        if (r124 == r0) goto L58;
        TopicsRecyclerView r125 = this.recyclerListView;
        if (r11 == false) goto L57;
        r6 = this.itemAnimator;
    L57:
        r125.setItemAnimator(r6);
        goto L58
    L52:
        r0 = null;
    L58:
        Adapter r112 = this.adapter;
        if (r112 == null) goto L62;
        r112.setItems(r4, this.forumTopics);
    L62:
        if (this.scrollToTop == true) goto L64;
        if (r3 != 0) goto L67;
    L64:
        LinearLayoutManager r113 = this.layoutManager;
        if (r113 == null) goto L67;
        r113.scrollToPositionWithOffset(0, 0);
        this.scrollToTop = false;
        goto L67
    L27:
        if (this.topicsController.endIsReached(this.chatId) == true) goto L31;
        if (this.canShowProgress == false) goto L31;
        this.forumTopics.add(new Item(this, 1, null));
        goto L31
    L12:
        if (this.openedForForward == false) goto L14;
        this.forumTopics.add(new Item(this, 3, null));
    L67:
        checkLoading();
        updateTopicsEmptyViewText();
        return;
    L5:
        if (this.updateAnimated == false) goto L7;
        r11 = true;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean allowFinishFragmentInsteadOfRemoveFromStack() {
        return false;
    }

    public void checkUi_listViewPadding() {
        DialogsActivity r0 = this.parentDialogsActivity;
        float r2 = 0.0f;
        if (r0 == null) goto L7;
        r2 = 0.0f + r0.getTopPanelAnimatedHeight();
        DialogsActivityTopPanelLayout r02 = this.topPanelLayout;
        if (r02 == null) goto L10;
        r02.setTranslationY(r2 - (AndroidUtilities.dp(7.0f) * this.parentDialogsActivity.getTopPanelVisibility()));
        r2 = r2 + this.topPanelLayout.getAnimatedHeightWithPadding(AndroidUtilities.lerp(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(7.0f), this.parentDialogsActivity.getTopPanelVisibility()));
    L10:
        DialogsActivity r03 = this.parentDialogsActivity;
        if (r03 != null) goto L13;
    L15:
        boolean r04 = false;
    L16:
        int r3 = (this.navigationBarHeight + this.additionNavigationBarHeight) + MainTabsUiHelper.getFloatingTabsPadding(r04);
        if (this.bottomPannelVisible == false) goto L19;
        int r05 = AndroidUtilities.dp(51.0f);
    L20:
        this.recyclerListView.setPadding(0, (int) r2, 0, r3 + r05);
        return;
    L19:
        r05 = 0;
        goto L20
    L13:
        if (r03.hasMainTabs == false) goto L15;
        r04 = true;
        goto L16
    L7:
        DialogsActivityTopPanelLayout r06 = this.topPanelLayout;
        if (r06 == null) goto L10;
        r2 = 0.0f + r06.getAnimatedHeightWithPadding(AndroidUtilities.dp(14.0f));
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r21) {
        DialogsActivity r0 = this.parentDialogsActivity;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        this.additionNavigationBarHeight = MainTabsUiHelper.getAdditionalNavigationBarHeight(r02);
        DialogsActivity r03 = this.parentDialogsActivity;
        if (r03 != null) goto L11;
    L13:
        boolean r04 = false;
    L14:
        this.additionFloatingButtonOffset = MainTabsUiHelper.getTabsFabOffset(r04);
        AnonymousClass1 r05 = new AnonymousClass1(this, r21);
        this.contentView = r05;
        this.fragmentView = r05;
        r05.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        this.actionBar.setAddToContainer(false);
        this.actionBar.setCastShadows(false);
        this.actionBar.setClipContent(true);
        ActionBar r06 = this.actionBar;
        if (AndroidUtilities.isTablet() == false) goto L17;
    L19:
        boolean r3 = false;
    L20:
        r06.setOccupyStatusBar(r3);
        if (this.inPreviewMode == false) goto L23;
        this.actionBar.setBackgroundColor(0);
        this.actionBar.setInterceptTouches(false);
    L23:
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.getBackButton().setTranslationX(AndroidUtilities.dp(2.0f));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this, r21));
        this.actionBar.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda1(this));
        ActionBarMenu r07 = this.actionBar.createMenu();
        r07.setTranslationX(-AndroidUtilities.dp(5.0f));
        if (this.parentDialogsActivity == null) goto L26;
        ActionBarMenuItem r4 = r07.addItem(0, R.drawable.outline_header_search);
        this.searchItem = r4;
        r4.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda5(this));
    L27:
        ActionBarMenuItem r08 = r07.addItem(0, R.drawable.ic_ab_other, this.themeDelegate);
        this.other = r08;
        r08.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.other.addSubItem(1, R.drawable.msg_discussion, LocaleController.getString(R.string.TopicViewAsMessages));
        this.addMemberSubMenu = this.other.addSubItem(2, R.drawable.msg_addcontact, LocaleController.getString(R.string.AddMember));
        this.boostGroupSubmenu = this.other.addSubItem(14, R.drawable.boosts, LocaleController.getString(R.string.BoostingBoostGroupMenu));
        this.createTopicSubmenu = this.other.addSubItem(3, R.drawable.msg_topic_create, LocaleController.getString(R.string.CreateTopic));
        this.reportSubmenu = this.other.addSubItem(15, R.drawable.msg_report, LocaleController.getString(R.string.ReportChat));
        this.deleteChatSubmenu = this.other.addSubItem(11, R.drawable.msg_leave, LocaleController.getString(R.string.LeaveMegaMenu), this.themeDelegate);
        ChatAvatarContainer r09 = new ChatAvatarContainer(r21, this, false, this.resourceProvider);
        this.avatarContainer = r09;
        r09.getAvatarImageView().setRoundRadius(ExteraConfig.getAvatarCorners(42.0f, false, true));
        ChatAvatarContainer r010 = this.avatarContainer;
        if (AndroidUtilities.isTablet() == false) goto L30;
    L32:
        boolean r42 = false;
    L33:
        r010.setOccupyStatusBar(r42);
        ChatAvatarContainer r011 = this.avatarContainer;
        if (getDialogId() >= 0) goto L36;
        boolean r43 = true;
    L37:
        r011.allowDrawStories = r43;
        this.avatarContainer.setClipChildren(false);
        this.actionBar.addView(this.avatarContainer, 0, LayoutHelper.createFrame(-2, -1.0f, 51, 56.0f, 0.0f, 86.0f, 0.0f));
        if (this.openedForSelect == true) goto L40;
        this.avatarContainer.getAvatarImageView().setOnClickListener(new AnonymousClass4(this));
    L40:
        this.recyclerListView = new AnonymousClass5(this, r21);
        this.iBlur3FactoryLiquidGlass.setSourceRootView(new ViewPositionWatcher(this.contentView), this.contentView);
        TopicsRecyclerView r44 = this.recyclerListView;
        DialogsActivity r5 = this.parentDialogsActivity;
        if (r5 == null) goto L43;
        ViewGroup r52 = (ViewGroup) r5.getFragmentView();
    L44:
        TopicsRecyclerView r10 = this.recyclerListView;
        Objects.requireNonNull(r10);
        this.iBlur3Capture = new ViewGroupPartRenderer(r44, r52, new TopicsFragment$$ExternalSyntheticLambda6(r10));
        this.recyclerListView.addEdgeEffectListener(new TopicsFragment$$ExternalSyntheticLambda7(this));
        SpannableString r012 = new SpannableString("#");
        ForumUtilities.GeneralTopicDrawable r53 = ForumUtilities.createGeneralTopicDrawable(getContext(), 0.85f, -1, false);
        r53.setBounds(0, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(18.0f));
        r012.setSpan(new ImageSpan(r53, 2), 0, 1, 33);
        AnonymousClass6 r54 = new AnonymousClass6(this, AndroidUtilities.replaceCharSequence("#", LocaleController.getString(R.string.AccSwipeForGeneral), r012), AndroidUtilities.replaceCharSequence("#", LocaleController.getString(R.string.AccReleaseForGeneral), r012));
        this.pullForegroundDrawable = r54;
        r54.doNotShow();
        if (this.hiddenShown == false) goto L47;
        int r013 = 2;
    L48:
        this.pullViewState = r013;
        PullForegroundDrawable r45 = this.pullForegroundDrawable;
        if (r013 == 0) goto L51;
        boolean r014 = true;
    L52:
        r45.setWillDraw(r014);
        AnonymousClass7 r015 = new AnonymousClass7(this);
        this.recyclerListView.setHideIfEmpty(false);
        r015.setSupportsChangeAnimations(false);
        r015.setDelayAnimations(false);
        TopicsRecyclerView r46 = this.recyclerListView;
        this.itemAnimator = r015;
        r46.setItemAnimator(r015);
        this.recyclerListView.setOnScrollListener(new AnonymousClass8(this));
        this.recyclerListView.setAnimateEmptyView(true, 0);
        RecyclerItemsEnterAnimator r016 = new RecyclerItemsEnterAnimator(this.recyclerListView, true);
        this.itemsEnterAnimator = r016;
        this.recyclerListView.setItemsEnterAnimator(r016);
        this.recyclerListView.setOnItemClickListener(new TopicsFragment$$ExternalSyntheticLambda8(this));
        this.recyclerListView.setOnItemLongClickListener(new TopicsFragment$$ExternalSyntheticLambda9(this));
        this.recyclerListView.setOnScrollListener(new AnonymousClass9(this));
        TopicsRecyclerView r017 = this.recyclerListView;
        AnonymousClass10 r47 = new AnonymousClass10(this, r21);
        this.layoutManager = r47;
        r017.setLayoutManager(r47);
        this.scrollHelper = new RecyclerAnimationScrollHelper(this.recyclerListView, this.layoutManager);
        this.recyclerListView.setAdapter(this.adapter);
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.addOnScrollListener(new AnonymousClass11(this));
        TouchHelperCallback r48 = new TouchHelperCallback(this);
        this.itemTouchHelperCallback = r48;
        AnonymousClass12 r018 = new AnonymousClass12(this, r48);
        this.itemTouchHelper = r018;
        r018.attachToRecyclerView(this.recyclerListView);
        this.contentView.addView(this.recyclerListView, LayoutHelper.createFrame(-1, -1.0f));
        ((ViewGroup.MarginLayoutParams) this.recyclerListView.getLayoutParams()).topMargin = -AndroidUtilities.dp(100.0f);
        FragmentFloatingButton r019 = new FragmentFloatingButton(getContext(), this.resourceProvider);
        this.floatingButton = r019;
        this.contentView.addView(r019, FragmentFloatingButton.createDefaultLayoutParams());
        this.floatingButton.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda10(this));
        this.floatingButton.imageView.setImageResource(R.drawable.ic_chatlist_add_2);
        this.floatingButton.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int r020 = AndroidUtilities.dp((FabUiHelper.getFabSizeDp() - 24) / 2.0f);
        this.floatingButton.imageView.setPadding(r020, r020, r020, r020);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.CreateTopic));
        FlickerLoadingView r021 = new FlickerLoadingView(r21);
        r021.setViewType(24);
        r021.setVisibility(8);
        r021.showDate(true);
        EmptyViewContainer r49 = new EmptyViewContainer(this, r21);
        this.emptyViewContainer = r49;
        r49.textView.setAlpha(0.0f);
        AnonymousClass13 r410 = new AnonymousClass13(this, r21, r021, 0);
        this.topicsEmptyView = r410;
        r410.stickerView.getImageReceiver().setAutoRepeat(2);     // Catch: Exception -> L89
    L54:
        this.topicsEmptyView.showProgress(this.loadingTopics, this.fragmentBeginToShow);
        this.topicsEmptyView.title.setText(LocaleController.getString(R.string.NoTopics));
        updateTopicsEmptyViewText();
        this.emptyViewContainer.addView(r021);
        this.emptyViewContainer.addView(this.topicsEmptyView);
        this.contentView.addView(this.emptyViewContainer);
        this.recyclerListView.setEmptyView(this.emptyViewContainer);
        this.bottomOverlayContainer = new AnonymousClass14(this, r21);
        UnreadCounterTextView r022 = new UnreadCounterTextView(r21);
        this.bottomOverlayChatText = r022;
        this.bottomOverlayContainer.addView(r022);
        this.contentView.addView(this.bottomOverlayContainer, LayoutHelper.createFrame(-1, 51, 80));
        this.bottomOverlayChatText.setOnClickListener(new AnonymousClass15(this));
        RadialProgressView r023 = new RadialProgressView(r21, this.themeDelegate);
        this.bottomOverlayProgress = r023;
        r023.setSize(AndroidUtilities.dp(22.0f));
        this.bottomOverlayProgress.setVisibility(4);
        this.bottomOverlayContainer.addView(this.bottomOverlayProgress, LayoutHelper.createFrame(30, 30, 17));
        ImageView r024 = new ImageView(r21);
        this.closeReportSpam = r024;
        r024.setImageResource(R.drawable.miniplayer_close);
        this.closeReportSpam.setContentDescription(LocaleController.getString(R.string.Close));
        ImageView r025 = this.closeReportSpam;
        int r55 = Theme.key_chat_topPanelClose;
        r025.setBackground(Theme.AdaptiveRipple.circle(getThemedColor(r55)));
        this.closeReportSpam.setColorFilter(new PorterDuffColorFilter(getThemedColor(r55), PorterDuff.Mode.MULTIPLY));
        this.closeReportSpam.setScaleType(ImageView.ScaleType.CENTER);
        this.bottomOverlayContainer.addView(this.closeReportSpam, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 6.0f, 2.0f, 0.0f));
        this.closeReportSpam.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda11(this));
        this.closeReportSpam.setVisibility(8);
        updateChatInfo();
        AnonymousClass16 r026 = new AnonymousClass16(this, r21);
        this.fullscreenView = r026;
        if (this.parentDialogsActivity != null) goto L57;
        this.contentView.addView(r026, LayoutHelper.createFrame(-1, -1, 119));
    L57:
        MessagesSearchContainer r027 = new MessagesSearchContainer(this, r21);
        this.searchContainer = r027;
        r027.setVisibility(8);
        this.fullscreenView.addView(this.searchContainer, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 44.0f, 0.0f, 0.0f));
        MessagesSearchContainer r028 = this.searchContainer;
        int r102 = Theme.key_windowBackgroundWhite;
        r028.setBackgroundColor(getThemedColor(r102));
        this.actionBar.setDrawBlurBackground(this.contentView);
        getMessagesStorage().loadChatInfo(this.chatId, true, null, true, false, 0);
        DialogsActivityTopPanelLayout r029 = new DialogsActivityTopPanelLayout(r21);
        this.topPanelLayout = r029;
        r029.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f));
        BlurredBackgroundDrawable r030 = this.iBlur3FactoryLiquidGlass.create(this.topPanelLayout, BlurredBackgroundProviderImpl.topPanel(this.resourceProvider));
        r030.setRadius(AndroidUtilities.dp(24.0f));
        r030.setPadding(AndroidUtilities.dp(7.0f));
        this.topPanelLayout.setBlurredBackground(r030);
        this.topPanelLayout.setOnAnimatedHeightChangedListener(new TopicsFragment$$ExternalSyntheticLambda12(this));
        this.contentView.addView(this.topPanelLayout, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, -14.0f, 0.0f, 0.0f));
        TLRPC.Chat r031 = getCurrentChat();
        if (r031 == null) goto L61;
        ChatActivityMemberRequestsDelegate r32 = new ChatActivityMemberRequestsDelegate(this, r031);
        this.pendingRequestsDelegate = r32;
        this.topPanelLayout.addView(r32.getView(), LayoutHelper.createLinear(-1, 40));
        this.topPanelLayout.setPriority(this.pendingRequestsDelegate.getView(), 3);
        this.topPanelLayout.setDebugName(this.pendingRequestsDelegate.getView(), "pendingRequestsDelegate");
        this.pendingRequestsDelegate.setDelegate(new TopicsFragment$$ExternalSyntheticLambda13(this));
        this.pendingRequestsDelegate.setChatInfo(this.chatFull, false);
    L61:
        if (this.inPreviewMode == true) goto L63;
        FrameLayout r032 = new FrameLayout(r21);
        this.fragmentContextViewWrapper = r032;
        this.topPanelLayout.addView(r032);
        this.topPanelLayout.setPriority(this.fragmentContextViewWrapper, 4);
        this.topPanelLayout.setDebugName(this.fragmentContextViewWrapper, "fragment context");
        this.topPanelLayout.setViewVisible(this.fragmentContextViewWrapper, true, false);
        AnonymousClass17 r033 = new AnonymousClass17(this, r21, this, false, this.themeDelegate);
        this.fragmentContextView = r033;
        this.fragmentContextViewWrapper.addView(r033);
        this.topPanelLayout.setCallFragmentContextView(this.fragmentContextView);
    L63:
        FrameLayout.LayoutParams r034 = LayoutHelper.createFrame(-1, -2.0f);
        if (this.inPreviewMode == false) goto L67;
        r034.topMargin = AndroidUtilities.statusBarHeight;
    L67:
        if (isInPreviewMode() == true) goto L69;
        this.contentView.addView(this.actionBar, r034);
    L69:
        checkForLoadMore();
        AnonymousClass18 r035 = new AnonymousClass18(this, r21);
        this.blurredView = r035;
        r035.setForeground(new ColorDrawable(ColorUtils.setAlphaComponent(getThemedColor(r102), 100)));
        this.blurredView.setFocusable(false);
        this.blurredView.setImportantForAccessibility(2);
        this.blurredView.setOnClickListener(new TopicsFragment$$ExternalSyntheticLambda2(this));
        this.blurredView.setFitsSystemWindows(true);
        this.bottomPannelVisible = true;
        if (this.inPreviewMode == true) goto L72;
    L84:
        updateChatInfo();
        updateColors();
        if (ChatObject.isBoostSupported(getCurrentChat()) == false) goto L87;
        getMessagesController().getBoostsController().getBoostsStats(-this.chatId, new TopicsFragment$$ExternalSyntheticLambda3(this));
    L87:
        ViewCompat.setOnApplyWindowInsetsListener(this.fragmentView, new TopicsFragment$$ExternalSyntheticLambda4(this));
        return this.fragmentView;
    L72:
        if (AndroidUtilities.isTablet() == false) goto L84;
        Iterator<BaseFragment> r036 = getParentLayout().getFragmentStack().iterator();
    L75:
        if (r036.hasNext() == false) goto L83;
        BaseFragment r2 = r036.next();
        if ((r2 instanceof DialogsActivity) == false) goto L75;
        DialogsActivity r22 = (DialogsActivity) r2;
        if (r22.isMainDialogList() == false) goto L75;
        MessagesStorage.TopicKey r23 = r22.getOpenedDialogId();
        if (r23.dialogId != (-this.chatId)) goto L75;
        this.selectedTopicForTablet = r23.topicId;
    L83:
        updateTopicsList(false, false);
        goto L84
    L51:
        r014 = false;
        goto L52
    L47:
        r013 = 0;
        goto L48
    L43:
        r52 = this.contentView;
        goto L44
    L36:
        r43 = false;
        goto L37
    L30:
        if (this.inPreviewMode == true) goto L32;
        r42 = true;
        goto L33
    L26:
        ActionBarMenuItem r411 = r07.addItem(0, R.drawable.outline_header_search);
        this.searchItem = r411;
        r411.setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass3(this));
        this.searchItem.setSearchPaddingStart(56);
        this.searchItem.setSearchFieldHint(LocaleController.getString(R.string.Search));
        EditTextBoldCursor r412 = this.searchItem.getSearchField();
        r412.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r412.setHintTextColor(getThemedColor(Theme.key_player_time));
        r412.setCursorColor(getThemedColor(Theme.key_chat_messagePanelCursor));
        goto L27
    L17:
        if (this.inPreviewMode == true) goto L19;
        r3 = true;
        goto L20
    L11:
        if (r03.hasMainTabs == false) goto L13;
        r04 = true;
        goto L14
    L5:
        if (r0.hasMainTabs == false) goto L7;
        r02 = true;
        goto L8
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        if (r8 != NotificationCenter.chatInfoDidLoad) goto L16;
        TLRPC.ChatFull r92 = (TLRPC.ChatFull) r10[0];
        TLRPC.ChatParticipants r3 = r92.participants;
        if (r3 == null) goto L10;
        TLRPC.ChatFull r4 = this.chatFull;
        if (r4 == null) goto L10;
        r4.participants = r3;
    L10:
        if (r92.id != this.chatId) goto L64;
        updateChatInfo();
        ChatActivityMemberRequestsDelegate r32 = this.pendingRequestsDelegate;
        if (r32 == null) goto L14;
        r32.setChatInfo(r92, true);
    L14:
        checkGroupCallJoin(((Boolean) r10[3]).booleanValue());
    L64:
        if (r8 == NotificationCenter.openedChatChanged) goto L66;
        return;
    L66:
        if (getParentActivity() != null) goto L68;
        return;
    L68:
        if (this.inPreviewMode == true) goto L70;
        return;
    L70:
        if (AndroidUtilities.isTablet() == false) goto L88;
        boolean r82 = ((Boolean) r10[2]).booleanValue();
        long r33 = ((Long) r10[0]).longValue();
        long r93 = ((Long) r10[1]).longValue();
        if (r33 != (-this.chatId)) goto L80;
        if (r82 == true) goto L80;
        if (this.selectedTopicForTablet == r93) goto L86;
        this.selectedTopicForTablet = r93;
        updateTopicsList(false, false);
        return;
    L86:
        return;
    L80:
        if (this.selectedTopicForTablet == 0) goto L87;
        this.selectedTopicForTablet = 0;
        updateTopicsList(false, false);
        return;
    L87:
        return;
    L88:
        return;
    L16:
        if (r8 != NotificationCenter.storiesUpdated) goto L19;
        updateChatInfo();
        goto L64
    L19:
        if (r8 != NotificationCenter.chatWasBoostedByUser) goto L24;
        if (this.chatId != (-((Long) r10[2]).longValue())) goto L64;
        this.boostsStatus = (TL_stories.TL_premium_boostsStatus) r10[0];
        goto L64
    L24:
        if (r8 != NotificationCenter.topicsDidLoaded) goto L34;
        if (this.chatId != ((Long) r10[0]).longValue()) goto L64;
        updateTopicsList(false, true);
        if (r10.length > 1) goto L30;
    L32:
        checkLoading();
        goto L64
    L30:
        if (((Boolean) r10[1]).booleanValue() == false) goto L32;
        checkForLoadMore();
        goto L32
    L34:
        if (r8 != NotificationCenter.updateInterfaces) goto L44;
        int r94 = ((Integer) r10[0]).intValue();
        if (r94 != MessagesController.UPDATE_MASK_CHAT) goto L39;
        updateChatInfo();
    L39:
        if ((r94 & MessagesController.UPDATE_MASK_SELECT_DIALOG) <= 0) goto L64;
        getMessagesController().getTopicsController().sortTopics(this.chatId, false);
        boolean r95 = this.recyclerListView.canScrollVertically(-1);
        updateTopicsList(true, false);
        if (r95 == true) goto L64;
        this.layoutManager.scrollToPosition(0);
        goto L64
    L44:
        if (r8 != NotificationCenter.dialogsNeedReload) goto L47;
        updateTopicsList(false, false);
        goto L64
    L47:
        if (r8 != NotificationCenter.groupCallUpdated) goto L55;
        Long r96 = (Long) r10[0];
        if (this.chatId != r96.longValue()) goto L64;
        this.groupCall = getMessagesController().getGroupCall(r96.longValue(), false);
        FragmentContextView r97 = this.fragmentContextView;
        if (r97 == null) goto L53;
        r97.checkCall(!this.fragmentBeginToShow);
    L53:
        checkGroupCallJoin(false);
        goto L64
    L55:
        if (r8 != NotificationCenter.notificationsSettingsUpdated) goto L58;
        updateTopicsList(false, false);
        updateChatInfo(true);
        goto L64
    L58:
        if (r8 == NotificationCenter.chatSwitchedForum) goto L64;
        if (r8 != NotificationCenter.closeChats) goto L64;
        removeSelfFromStack(true);
        goto L64
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.Components.ChatActivityInterface
    public ChatAvatarContainer getAvatarContainer() {
        return this.avatarContainer;
    }

    @Override // org.telegram.ui.Components.ChatActivityInterface
    public SizeNotifierFrameLayout getContentView() {
        return this.contentView;
    }

    @Override // org.telegram.ui.Components.ChatActivityInterface
    public TLRPC.Chat getCurrentChat() {
        return getMessagesController().getChat(Long.valueOf(this.chatId));
    }

    @Override // org.telegram.ui.Components.ChatActivityInterface, org.telegram.ui.Components.InstantCameraView.Delegate
    public long getDialogId() {
        return -this.chatId;
    }

    public BlurredBackgroundSourceRenderNode getFrostedGlassSource() {
        return this.iBlur3SourceGlassFrosted;
    }

    @Override // org.telegram.ui.RightSlidingDialogContainer.BaseFragmentWithFullscreen
    public View getFullscreenView() {
        return this.fullscreenView;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public BlurredBackgroundSourceRenderNode getGlassSource() {
        return this.iBlur3SourceGlass;
    }

    @Override // org.telegram.ui.Components.ChatActivityInterface
    public ChatObject.Call getGroupCall() {
        ChatObject.Call r1 = this.groupCall;
        if (r1 != null) goto L5;
        return null;
    L5:
        if ((r1.call instanceof TLRPC.TL_groupCall) == false) goto L9;
        return r1;
    L9:
        return null;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_windowBackgroundWhite);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        TopicsFragment$$ExternalSyntheticLambda0 r7 = new TopicsFragment$$ExternalSyntheticLambda0(this);
        ArrayList<ThemeDescription> r9 = new ArrayList();
        View r11 = this.fragmentView;
        int r12 = ThemeDescription.FLAG_BACKGROUND;
        int r17 = Theme.key_windowBackgroundWhite;
        r9.add(new ThemeDescription(r11, r12, null, null, null, null, r17));
        r9.add(new ThemeDescription(null, 0, null, null, null, r7, r17));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        MessagesSearchContainer r0 = this.searchContainer;
        if (r0 == null) goto L7;
        RecyclerListView r02 = r0.recyclerView;
        if (r02 == null) goto L7;
        GraySectionCell.createThemeDescriptions(r9, r02);
    L7:
        return r9;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (this.searching == false) goto L6;
        int r0 = Theme.key_windowBackgroundWhite;
    L5:
        int r02 = getThemedColor(r0);
        if (this.actionBar.isActionModeShowed() == false) goto L11;
        r02 = getThemedColor(Theme.key_actionBarActionModeDefault);
    L11:
        if (ColorUtils.calculateLuminance(r02) <= 0.699999988079071d) goto L14;
        return true;
    L14:
        return false;
    L6:
        r0 = Theme.key_actionBarDefault;
        goto L5
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (this.selectedTopics.isEmpty() == true) goto L8;
        if (r3 == false) goto L6;
        clearSelectedTopics();
    L6:
        return false;
    L8:
        if (this.searching == false) goto L13;
        if (r3 == false) goto L11;
        this.actionBar.onSearchFieldVisibilityChanged(this.searchItem.toggleSearch(false));
    L11:
        return false;
    L13:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.closeSearchField();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getMessagesController().loadFullChat(this.chatId, 0, true);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatWasBoostedByUser);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.topicsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogsNeedReload);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupCallUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.notificationsSettingsUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatSwitchedForum);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.closeChats);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.openedChatChanged);
        updateTopicsList(false, false);
        SelectAnimatedEmojiDialog.preload(this.currentAccount);
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (ChatObject.isChannel(r0) == false) goto L6;
        getMessagesController().startShortPoll(r0, this.classGuid, false);
    L6:
        if (settingsPreloaded.contains(Long.valueOf(this.chatId)) == true) goto L8;
        settingsPreloaded.add(Long.valueOf(this.chatId));
        TL_account.getNotifyExceptions r02 = new TL_account.getNotifyExceptions();
        TLRPC.TL_inputNotifyPeer r1 = new TLRPC.TL_inputNotifyPeer();
        r02.peer = r1;
        r02.flags |= 1;
        r1.peer = getMessagesController().getInputPeer(-this.chatId);
        getConnectionsManager().sendRequest(r02, null);
    L8:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        this.notificationsLocker.unlock();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatWasBoostedByUser);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.topicsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogsNeedReload);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupCallUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.notificationsSettingsUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatSwitchedForum);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.closeChats);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.openedChatChanged);
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        if (ChatObject.isChannel(r0) == false) goto L5;
        getMessagesController().startShortPoll(r0, this.classGuid, true);
    L5:
        super.onFragmentDestroy();
        DialogsActivity r02 = this.parentDialogsActivity;
        if (r02 != null) goto L8;
    L10:
        ChatAvatarContainer r4 = this.avatarContainer;
        if (r4 == null) goto L14;
        r4.onDestroy(true);
        return;
    L14:
        return;
    L8:
        if (r02.rightSlidingDialogContainer == null) goto L10;
        r02.getActionBar().setSearchAvatarImageView(null);
        this.parentDialogsActivity.rightSlidingDialogContainer.enabled = true;
        goto L10
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void onParentScrollToTop() {
        this.recyclerListView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        getMessagesController().getTopicsController().onTopicFragmentPause(this.chatId);
        Bulletin.removeDelegate(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.mainTabsHiddenByScroll = false;
        checkUi_mainTabsVisible();
        getMessagesController().getTopicsController().onTopicFragmentResume(this.chatId);
        this.animatedUpdateEnabled = false;
        AndroidUtilities.updateVisibleRows(this.recyclerListView);
        this.animatedUpdateEnabled = true;
        Bulletin.addDelegate(this, new AnonymousClass23(this));
        if (this.inPreviewMode == true) goto L5;
        return;
    L5:
        if (getMessagesController().isForum(-this.chatId) == true) goto L9;
        finishFragment();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onSlideProgress(boolean r1, float r2) {
        if (SharedConfig.getDevicePerformanceClass() != 0) goto L6;
        return;
    L6:
        if (this.isSlideBackTransition == true) goto L8;
        return;
    L8:
        if (this.slideBackTransitionAnimator != null) goto L12;
        setSlideTransitionProgress(r2);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r2, boolean r3) {
        super.onTransitionAnimationEnd(r2, r3);
        if (r2 == false) goto L10;
        View r32 = this.blurredView;
        if (r32 == null) goto L10;
        if (r32.getParent() == null) goto L9;
        ((ViewGroup) this.blurredView.getParent()).removeView(this.blurredView);
    L9:
        this.blurredView.setBackground(null);
    L10:
        if (r2 == false) goto L12;
        this.openAnimationEnded = true;
        checkGroupCallJoin(this.lastCallCheckFromServer);
    L12:
        this.notificationsLocker.unlock();
        if (r2 == false) goto L15;
    L30:
        if (r2 == true) goto L32;
        return;
    L32:
        if (this.bottomPannelVisible == false) goto L36;
        setNavigationBarColor(getNavigationBarColor());
        return;
    L36:
        return;
    L15:
        if (this.openedForSelect == false) goto L22;
        if (this.removeFragmentOnTransitionEnd == false) goto L22;
        removeSelfFromStack();
        DialogsActivity r33 = this.dialogsActivity;
        if (r33 == null) goto L30;
        r33.removeSelfFromStack();
    L22:
        if (this.finishDialogRightSlidingPreviewOnTransitionEnd == false) goto L30;
        removeSelfFromStack();
        DialogsActivity r34 = this.parentDialogsActivity;
        if (r34 == null) goto L30;
        RightSlidingDialogContainer r35 = r34.rightSlidingDialogContainer;
        if (r35 == null) goto L30;
        if (r35.hasFragment() == false) goto L30;
        this.parentDialogsActivity.rightSlidingDialogContainer.finishPreview();
        goto L30
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationProgress(boolean r2, float r3) {
        View r0 = this.blurredView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() != 0) goto L13;
        View r1 = this.blurredView;
        if (r2 == false) goto L10;
        r1.setAlpha(1.0f - r3);
        return;
    L10:
        r1.setAlpha(r3);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r1, boolean r2) {
        super.onTransitionAnimationStart(r1, r2);
        if (r1 == false) goto L5;
        this.openAnimationEnded = false;
    L5:
        this.notificationsLocker.lock();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void prepareFragmentToSlide(boolean r1, boolean r2) {
        if (r1 == true) goto L6;
        if (r2 == false) goto L6;
        this.isSlideBackTransition = true;
        setFragmentIsSliding(true);
        return;
    L6:
        this.slideBackTransitionAnimator = null;
        this.isSlideBackTransition = false;
        setFragmentIsSliding(false);
        setSlideTransitionProgress(1.0f);
    }

    public void sendReorder() {
        ArrayList<Integer> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= this.forumTopics.size()) goto L11;
        TLRPC.TL_forumTopic r2 = this.forumTopics.get(r1).topic;
        if (r2 == null) goto L10;
        if (r2.pinned == false) goto L10;
        r0.add(Integer.valueOf(r2.id));
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        getMessagesController().getTopicsController().reorderPinnedTopics(this.chatId, r0);
        this.ignoreDiffUtil = true;
    }

    public void setExcludeTopics(HashSet<Integer> r1) {
        this.excludeTopics = r1;
    }

    public void setForwardFromDialogFragment(DialogsActivity r1) {
        this.dialogsActivity = r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setNavigationBarColor(int r1) {
        super.setNavigationBarColor(r1);
    }

    public void setOnTopicSelectedListener(OnTopicSelectedListener r1) {
        this.onTopicSelectedListener = r1;
    }

    public void setParentDialogsActivity(DialogsActivity r1) {
        this.parentDialogsActivity = r1;
        checkUi_mainTabsVisible();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setPreviewOpenedProgress(float r2) {
        ChatAvatarContainer r0 = this.avatarContainer;
        if (r0 == null) goto L9;
        r0.setAlpha(r2);
        this.other.setAlpha(r2);
        ActionBarMenuItem r02 = this.searchItem;
        if (r02 == null) goto L7;
        r02.setAlpha(r2);
    L7:
        this.actionBar.getBackButton().setAlpha(r2);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void setPreviewReplaceProgress(float r2) {
        ChatAvatarContainer r0 = this.avatarContainer;
        if (r0 == null) goto L6;
        r0.setAlpha(r2);
        this.avatarContainer.setTranslationX((1.0f - r2) * AndroidUtilities.dp(40.0f));
        return;
    }

    public void setTransitionPadding(int r1) {
        this.transitionPadding = r1;
        updateFloatingButtonOffset();
    }

    public void switchToChat(boolean r4) {
        this.removeFragmentOnTransitionEnd = r4;
        Bundle r42 = new Bundle();
        r42.putLong("chat_id", this.chatId);
        ChatActivity r0 = new ChatActivity(r42);
        r0.setSwitchFromTopics(true);
        presentFragment(r0);
    }

    public void updateReordering() {
        if (ChatObject.canManageTopics(getCurrentChat()) == true) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (this.reordering == r0) goto L12;
        this.reordering = r0;
        Adapter r3 = this.adapter;
        r3.notifyItemRangeChanged(0, r3.getItemCount());
        return;
    L12:
        return;
    L5:
        if (this.selectedTopics.isEmpty() == true) goto L7;
        r0 = true;
        goto L9
    }
}
