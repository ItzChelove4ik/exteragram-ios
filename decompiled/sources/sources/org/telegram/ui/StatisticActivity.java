package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.collection.ArraySet;
import androidx.core.graphics.ColorUtils;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LruCache;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.ManageChatTextCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.StatisticPostInfoCell;
import org.telegram.ui.Charts.BarChartView;
import org.telegram.ui.Charts.BaseChartView;
import org.telegram.ui.Charts.ChartPickerDelegate;
import org.telegram.ui.Charts.DoubleLinearChartView;
import org.telegram.ui.Charts.LinearBarChartView;
import org.telegram.ui.Charts.LinearChartView;
import org.telegram.ui.Charts.PieChartView;
import org.telegram.ui.Charts.StackBarChartView;
import org.telegram.ui.Charts.StackLinearChartView;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Charts.data.DoubleLinearChartData;
import org.telegram.ui.Charts.data.StackBarChartData;
import org.telegram.ui.Charts.data.StackLinearChartData;
import org.telegram.ui.Charts.view_data.ChartHeaderView;
import org.telegram.ui.Charts.view_data.LegendSignatureView;
import org.telegram.ui.Charts.view_data.LineViewData;
import org.telegram.ui.Charts.view_data.TransitionParams;
import org.telegram.ui.ChatRightsEditActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.FlatCheckBox;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.BoostDialogs;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert$$ExternalSyntheticLambda16;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.UniversalRecyclerView;
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
import org.telegram.ui.Components.glass.GlassTabView;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;

/* loaded from: classes6.dex */
public class StatisticActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private final int ADDITIONAL_LIST_HEIGHT_DP;
    private ChartViewData actionsData;
    private Adapter adapter;
    private RecyclerView.ItemAnimator animator;
    ChatAvatarContainer avatarContainer;
    private ChannelBoostLayout boostLayout;
    private TLRPC.ChatFull chat;
    private final long chatId;
    private final LruCache<ChartData> childDataCache;
    private DiffUtilsCallback diffUtilsCallback;
    private ChartViewData followersData;
    private ChartViewData groupMembersData;
    private ChartViewData growthData;
    private IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryLiquidGlass;
    private final RectF iBlur3PositionActionBar;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private RLottieImageView imageView;
    private boolean initialLoading;
    private ChartViewData interactionsData;
    private final boolean isMegagroup;
    private ChartViewData ivInteractionsData;
    private ChartViewData languagesData;
    private ZoomCancelable lastCancelable;
    private LinearLayoutManager layoutManager;
    private IBlur3Capture listBlur3Capture;
    private int loadFromId;
    private long maxDateOverview;
    private ChartViewData membersLanguageData;
    private ChartViewData messagesData;
    private boolean messagesIsLoading;
    private long minDateOverview;
    private ChannelMonetizationLayout monetizationLayout;
    private ChartViewData newFollowersBySourceData;
    private ChartViewData newMembersBySourceData;
    private ChartViewData notificationsData;
    private final boolean onlyBoostsStat;
    private OverviewChannelData overviewChannelData;
    private OverviewChatData overviewChatData;
    private final AlertDialog[] progressDialog;
    private LinearLayout progressLayout;
    private ChartViewData reactionsByEmotionData;
    private final ArrayList<RecentPostInfo> recentAllSortedDataLoaded;
    private final SparseIntArray recentPostIdtoIndexMap;
    private final ArrayList<RecentPostInfo> recentPostsAll;
    private final ArrayList<RecentPostInfo> recentPostsLoaded;
    private final ArrayList<RecentPostInfo> recentStoriesAll;
    private final SparseIntArray recentStoriesIdtoIndexMap;
    private final ArrayList<RecentPostInfo> recentStoriesLoaded;
    private RecyclerListView recyclerListView;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private BaseChartView.SharedUiComponents sharedUi;
    private final Runnable showProgressbar;
    private boolean showTabs;
    private final boolean startFromBoosts;
    private final boolean startFromMonetization;
    private StoriesController.StoriesList storiesList;
    private int storiesListId;
    private ChartViewData storyInteractionsData;
    private ChartViewData storyReactionsByEmotionData;
    private GlassTabView[] tabs;
    private MainTabsLayout tabsView;
    private final ArrayList<MemberData> topAdmins;
    private ChartViewData topDayOfWeeksData;
    private ChartViewData topHoursData;
    private final ArrayList<MemberData> topInviters;
    private final ArrayList<MemberData> topMembersAll;
    private final ArrayList<MemberData> topMembersVisible;
    private ViewPagerFixed viewPagerFixed;
    private ChartViewData viewsBySourceData;

    /* renamed from: org.telegram.ui.StatisticActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass1(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            StatisticActivity.m19258$$Nest$fgetprogressLayout(this.this$0).animate().alpha(1.0f).setDuration(230);
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$10, reason: invalid class name */
    public class AnonymousClass10 implements IBlur3Capture {
        final RectF fragmentPosition;
        final /* synthetic */ StatisticActivity this$0;
        final /* synthetic */ SizeNotifierFrameLayout val$contentLayout;

        public AnonymousClass10(StatisticActivity r1, SizeNotifierFrameLayout r2) {
            this.this$0 = r1;
            this.val$contentLayout = r2;
            this.fragmentPosition = new RectF();
        }

        @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
        public void capture(Canvas r6, RectF r7) {
            this.this$0.fragmentView.getMeasuredWidth();
            this.this$0.fragmentView.getMeasuredHeight();
            r6.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            int r0 = 0;
        L4:
            if (r0 >= 3) goto L24;
            if (r0 != 0) goto L8;
            IBlur3Capture r1 = StatisticActivity.m19245$$Nest$fgetlistBlur3Capture(this.this$0);
            View r2 = StatisticActivity.m19263$$Nest$fgetrecyclerListView(this.this$0);
        L16:
            if (r1 == null) goto L23;
            if (r2 == null) goto L23;
            ViewPositionWatcher.computeRectInParent(r2, this.val$contentLayout, this.fragmentPosition);
            RectF r22 = this.fragmentPosition;
            if (r22.right <= 0.0f) goto L22;
            float r23 = r22.left;
            this.this$0.fragmentView.getMeasuredWidth();
        L22:
            r6.save();
            r1.capture(r6, r7);
            r6.restore();
        L23:
            r0 = r0 + 1;
            goto L4
        L8:
            if (r0 != 1) goto L13;
            if (StatisticActivity.m19232$$Nest$fgetboostLayout(this.this$0) == null) goto L13;
            r1 = StatisticActivity.m19232$$Nest$fgetboostLayout(this.this$0).iBlur3Capture;
            r2 = StatisticActivity.m19232$$Nest$fgetboostLayout(this.this$0);
        L13:
            if (StatisticActivity.m19251$$Nest$fgetmonetizationLayout(this.this$0) == null) goto L15;
            r1 = StatisticActivity.m19251$$Nest$fgetmonetizationLayout(this.this$0).iBlur3Capture;
            r2 = StatisticActivity.m19251$$Nest$fgetmonetizationLayout(this.this$0);
            goto L16
        L15:
            r1 = null;
            r2 = null;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends DefaultItemAnimator {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass11(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public long getAddAnimationDelay(long r1, long r3, long r5) {
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$12, reason: invalid class name */
    public class AnonymousClass12 extends RecyclerView.OnScrollListener {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass12(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            if (StatisticActivity.m19261$$Nest$fgetrecentPostsAll(this.this$0).size() == StatisticActivity.m19262$$Nest$fgetrecentPostsLoaded(this.this$0).size()) goto L10;
            if (StatisticActivity.m19249$$Nest$fgetmessagesIsLoading(this.this$0) == true) goto L10;
            if (StatisticActivity.m19244$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition() <= (StatisticActivity.m19231$$Nest$fgetadapter(this.this$0).getItemCount() - 20)) goto L10;
            StatisticActivity.m19282$$Nest$mloadMessages(this.this$0);
        L10:
            if (Build.VERSION.SDK_INT >= 31) goto L12;
            return;
        L12:
            if (StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L16;
            StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            StatisticActivity.m19278$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$13, reason: invalid class name */
    public class AnonymousClass13 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass13(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass2(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            StatisticActivity.m19258$$Nest$fgetprogressLayout(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewPagerFixed {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass3(StatisticActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            StatisticActivity.m19280$$Nest$mcheckUi_actionBar(this.this$0);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onScrollEnd() {
            super.onScrollEnd();
            StatisticActivity r0 = this.this$0;
            r0.selectTab(StatisticActivity.m19275$$Nest$fgetviewPagerFixed(r0).getCurrentPosition(), true);
            this.this$0.setGestureSelectedOverride(0.0f, false);
            StatisticActivity.m19278$$Nest$mblur3_InvalidateBlur(this.this$0);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r4) {
            float r1 = StatisticActivity.m19275$$Nest$fgetviewPagerFixed(this.this$0).getPositionAnimated();
            this.this$0.setGestureSelectedOverride(r1, !r4);
            if (r4 == true) goto L5;
            this.this$0.selectTab(Math.round(r1), true);
        L5:
            StatisticActivity.m19278$$Nest$mblur3_InvalidateBlur(this.this$0);
            StatisticActivity.m19280$$Nest$mcheckUi_actionBar(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewPagerFixed.Adapter {
        final /* synthetic */ StatisticActivity this$0;
        final /* synthetic */ boolean val$hasMonetization;
        final /* synthetic */ boolean val$hasStats;
        final /* synthetic */ boolean val$isBoostSupported;
        final /* synthetic */ FrameLayout val$statisticLayout;

        public AnonymousClass4(StatisticActivity r1, boolean r2, boolean r3, boolean r4, FrameLayout r5) {
            this.this$0 = r1;
            this.val$hasStats = r2;
            this.val$isBoostSupported = r3;
            this.val$hasMonetization = r4;
            this.val$statisticLayout = r5;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            if (StatisticActivity.m19255$$Nest$fgetonlyBoostsStat(this.this$0) == false) goto L7;
            return StatisticActivity.m19232$$Nest$fgetboostLayout(this.this$0);
        L7:
            if (this.val$hasStats == false) goto L13;
            if (r2 == 0) goto L10;
            r2 = r2 - 1;
            goto L13
        L10:
            return this.val$statisticLayout;
        L13:
            if (this.val$isBoostSupported == false) goto L19;
            if (r2 == 0) goto L16;
            r2 = r2 - 1;
            goto L19
        L16:
            return StatisticActivity.m19232$$Nest$fgetboostLayout(this.this$0);
        L19:
            if (this.val$hasMonetization == false) goto L24;
            if (r2 != 0) goto L24;
            return StatisticActivity.m19251$$Nest$fgetmonetizationLayout(this.this$0);
        L24:
            return this.val$statisticLayout;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            if (StatisticActivity.m19255$$Nest$fgetonlyBoostsStat(this.this$0) == false) goto L6;
            return 1;
        L6:
            boolean r0 = this.val$hasStats;
            boolean r1 = this.val$isBoostSupported;
            return ((r0 ? 1 : 0) + (r1 ? 1 : 0)) + (this.val$hasMonetization ? 1 : 0);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            return r1;
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends SizeNotifierFrameLayout {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass5(StatisticActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L22:
            super.dispatchDraw(r6);
            return;
        L5:
            if (StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L22;
            StatisticActivity.m19278$$Nest$mblur3_InvalidateBlur(this.this$0);
            int r0 = getMeasuredWidth();
            int r1 = getMeasuredHeight();
            if (StatisticActivity.m19239$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L15;
            if (StatisticActivity.m19239$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).inRecording() == true) goto L15;
            RecordingCanvas r2 = StatisticActivity.m19239$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).beginRecording(r0, r1);
            r2.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L13;
            StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r2, -3);
        L13:
            StatisticActivity.m19239$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).endRecording();
        L15:
            if (StatisticActivity.m19238$$Nest$fgetiBlur3SourceGlass(this.this$0) == null) goto L22;
            if (StatisticActivity.m19238$$Nest$fgetiBlur3SourceGlass(this.this$0).inRecording() == true) goto L22;
            RecordingCanvas r02 = StatisticActivity.m19238$$Nest$fgetiBlur3SourceGlass(this.this$0).beginRecording(r0, r1);
            r02.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L21;
            StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r02, -2);
        L21:
            StatisticActivity.m19238$$Nest$fgetiBlur3SourceGlass(this.this$0).endRecording();
            goto L22
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawBlurRect(Canvas r7, float r8, Rect r9, Paint r10, boolean r11) {
            if (Build.VERSION.SDK_INT < 29) goto L11;
            if (SharedConfig.chatBlurEnabled() == false) goto L11;
            if (StatisticActivity.m19239$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L11;
            r7.save();
            r7.translate(0.0f, -r8);
            StatisticActivity.m19239$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).draw(r7, r9.left, r9.top + r8, r9.right, r9.bottom + r8);
            r7.restore();
            int r6 = r10.getAlpha();
            r10.setAlpha(178);
            r7.drawRect(r9, r10);
            r10.setAlpha(r6);
            return;
        L11:
            r7.drawRect(r9, r10);
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements Bulletin.Delegate {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass6(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.dp(64.0f);
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerListView {
        int lastH;
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass7(StatisticActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (this.lastH != getMeasuredHeight()) goto L5;
        L7:
            this.lastH = getMeasuredHeight();
            return;
        L5:
            if (StatisticActivity.m19231$$Nest$fgetadapter(this.this$0) == null) goto L7;
            StatisticActivity.m19231$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.OnScrollListener {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass8(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
            return;
        L5:
            if (StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L9;
            StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            StatisticActivity.m19278$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.StatisticActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends RecyclerView.OnScrollListener {
        final /* synthetic */ StatisticActivity this$0;

        public AnonymousClass9(StatisticActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
            return;
        L5:
            if (StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L9;
            StatisticActivity.m19264$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            StatisticActivity.m19278$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        int actionsCell;
        int count;
        ArraySet<Integer> emptyCells;
        int expandTopMembersRow;
        int folowersCell;
        int groupMembersCell;
        int growCell;
        int interactionsCell;
        int ivInteractionsCell;
        int languagesCell;
        int membersLanguageCell;
        int messagesCell;
        int newFollowersBySourceCell;
        int newMembersBySourceCell;
        int notificationsCell;
        int overviewCell;
        int overviewHeaderCell;
        int progressCell;
        int reactionsByEmotionCell;
        int recentPostsEndRow;
        int recentPostsHeaderCell;
        int recentPostsStartRow;
        ArraySet<Integer> shadowDivideCells;
        int storyInteractionsCell;
        int storyReactionsByEmotionCell;
        final /* synthetic */ StatisticActivity this$0;
        int topAdminsEndRow;
        int topAdminsHeaderCell;
        int topAdminsStartRow;
        int topDayOfWeeksCell;
        int topHourseCell;
        int topInviterEndRow;
        int topInviterHeaderCell;
        int topInviterStartRow;
        int topMembersEndRow;
        int topMembersHeaderCell;
        int topMembersStartRow;
        int viewsBySourceCell;

        /* renamed from: org.telegram.ui.StatisticActivity$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends ChartCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2, int r3, int r4, BaseChartView.SharedUiComponents r5) {
                this.this$1 = r1;
                super(r1.this$0, r2, r3, r4, r5);
            }

            @Override // android.view.View
            public void onDraw(Canvas r3) {
                if (getTranslationY() == 0.0f) goto L5;
                r3.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            L5:
                super.onDraw(r3);
            }
        }

        /* renamed from: org.telegram.ui.StatisticActivity$Adapter$2, reason: invalid class name */
        public class AnonymousClass2 extends StatisticPostInfoCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass2(Adapter r1, Context r2, TLRPC.ChatFull r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // android.view.View
            public void onDraw(Canvas r3) {
                if (getTranslationY() == 0.0f) goto L5;
                r3.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            L5:
                super.onDraw(r3);
            }
        }

        /* renamed from: org.telegram.ui.StatisticActivity$Adapter$3, reason: invalid class name */
        public class AnonymousClass3 extends ChartHeaderView {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass3(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onDraw(Canvas r3) {
                if (getTranslationY() == 0.0f) goto L5;
                r3.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            L5:
                super.onDraw(r3);
            }
        }

        public static /* synthetic */ void $r8$lambda$e4eMoW2OcnJPFJxqEV0XkrjYVAs(Adapter r0, RecentPostInfo r1, View r2) {
            r0.lambda$onBindViewHolder$0(r1, r2);
        }

        public Adapter(StatisticActivity r1) {
            this.this$0 = r1;
            this.overviewHeaderCell = -1;
            this.growCell = -1;
            this.progressCell = -1;
            this.folowersCell = -1;
            this.topHourseCell = -1;
            this.interactionsCell = -1;
            this.ivInteractionsCell = -1;
            this.viewsBySourceCell = -1;
            this.newFollowersBySourceCell = -1;
            this.languagesCell = -1;
            this.notificationsCell = -1;
            this.reactionsByEmotionCell = -1;
            this.storyInteractionsCell = -1;
            this.storyReactionsByEmotionCell = -1;
            this.recentPostsHeaderCell = -1;
            this.recentPostsStartRow = -1;
            this.recentPostsEndRow = -1;
            this.groupMembersCell = -1;
            this.newMembersBySourceCell = -1;
            this.membersLanguageCell = -1;
            this.messagesCell = -1;
            this.actionsCell = -1;
            this.topDayOfWeeksCell = -1;
            this.topMembersHeaderCell = -1;
            this.topMembersStartRow = -1;
            this.topMembersEndRow = -1;
            this.topAdminsHeaderCell = -1;
            this.topAdminsStartRow = -1;
            this.topAdminsEndRow = -1;
            this.topInviterHeaderCell = -1;
            this.topInviterStartRow = -1;
            this.topInviterEndRow = -1;
            this.expandTopMembersRow = -1;
            this.shadowDivideCells = new ArraySet();
            this.emptyCells = new ArraySet();
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(RecentPostInfo r3, View r4) {
            this.this$0.getOrCreateStoryViewer().open(this.this$0.getContext(), r3.getId(), StatisticActivity.m19266$$Nest$fgetstoriesList(this.this$0), StoriesListPlaceProvider.of(StatisticActivity.m19263$$Nest$fgetrecyclerListView(this.this$0)));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.count;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r2) {
            if (r2 < this.recentPostsStartRow) goto L9;
            if (r2 >= this.recentPostsEndRow) goto L9;
            return ((RecentPostInfo) StatisticActivity.m19260$$Nest$fgetrecentAllSortedDataLoaded(this.this$0).get(r2 - this.recentPostsStartRow)).getId();
        L9:
            if (r2 != this.growCell) goto L13;
            return 1;
        L13:
            if (r2 != this.folowersCell) goto L17;
            return 2;
        L17:
            if (r2 != this.topHourseCell) goto L21;
            return 3;
        L21:
            if (r2 != this.interactionsCell) goto L25;
            return 4;
        L25:
            if (r2 != this.notificationsCell) goto L29;
            return 5;
        L29:
            if (r2 != this.ivInteractionsCell) goto L33;
            return 6;
        L33:
            if (r2 != this.viewsBySourceCell) goto L37;
            return 7;
        L37:
            if (r2 != this.newFollowersBySourceCell) goto L41;
            return 8;
        L41:
            if (r2 != this.languagesCell) goto L45;
            return 9;
        L45:
            if (r2 != this.groupMembersCell) goto L49;
            return 10;
        L49:
            if (r2 != this.newMembersBySourceCell) goto L53;
            return 11;
        L53:
            if (r2 != this.membersLanguageCell) goto L57;
            return 12;
        L57:
            if (r2 != this.messagesCell) goto L61;
            return 13;
        L61:
            if (r2 != this.actionsCell) goto L65;
            return 14;
        L65:
            if (r2 != this.topDayOfWeeksCell) goto L69;
            return 15;
        L69:
            if (r2 != this.reactionsByEmotionCell) goto L73;
            return 16;
        L73:
            if (r2 != this.storyInteractionsCell) goto L77;
            return 17;
        L77:
            if (r2 != this.storyReactionsByEmotionCell) goto L81;
            return 18;
        L81:
            return super.getItemId(r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r4) {
            if (r4 != this.growCell) goto L5;
            return 0;
        L5:
            if (r4 != this.folowersCell) goto L7;
            return 0;
        L7:
            if (r4 != this.topHourseCell) goto L9;
            return 0;
        L9:
            if (r4 != this.notificationsCell) goto L11;
            return 0;
        L11:
            if (r4 != this.actionsCell) goto L13;
            return 0;
        L13:
            if (r4 != this.groupMembersCell) goto L16;
            return 0;
        L16:
            if (r4 != this.interactionsCell) goto L18;
            return 1;
        L18:
            if (r4 != this.ivInteractionsCell) goto L20;
            return 1;
        L20:
            if (r4 != this.storyInteractionsCell) goto L23;
            return 1;
        L23:
            if (r4 != this.viewsBySourceCell) goto L25;
            return 2;
        L25:
            if (r4 != this.newFollowersBySourceCell) goto L27;
            return 2;
        L27:
            if (r4 != this.newMembersBySourceCell) goto L29;
            return 2;
        L29:
            if (r4 != this.messagesCell) goto L31;
            return 2;
        L31:
            if (r4 != this.reactionsByEmotionCell) goto L33;
            return 2;
        L33:
            if (r4 != this.storyReactionsByEmotionCell) goto L36;
            return 2;
        L36:
            if (r4 != this.languagesCell) goto L38;
            return 4;
        L38:
            if (r4 != this.membersLanguageCell) goto L40;
            return 4;
        L40:
            if (r4 != this.topDayOfWeeksCell) goto L43;
            return 4;
        L43:
            if (r4 < this.recentPostsStartRow) goto L48;
            if (r4 > this.recentPostsEndRow) goto L48;
            return 9;
        L48:
            if (r4 != this.progressCell) goto L52;
            return 11;
        L52:
            if (this.emptyCells.contains(Integer.valueOf(r4)) == false) goto L56;
            return 12;
        L56:
            if (r4 != this.recentPostsHeaderCell) goto L58;
            return 13;
        L58:
            if (r4 != this.overviewHeaderCell) goto L60;
            return 13;
        L60:
            if (r4 != this.topAdminsHeaderCell) goto L62;
            return 13;
        L62:
            if (r4 != this.topMembersHeaderCell) goto L64;
            return 13;
        L64:
            if (r4 != this.topInviterHeaderCell) goto L67;
            return 13;
        L67:
            if (r4 != this.overviewCell) goto L71;
            return 14;
        L71:
            if (r4 < this.topAdminsStartRow) goto L75;
            if (r4 > this.topAdminsEndRow) goto L75;
        L82:
            return 9;
        L75:
            if (r4 < this.topMembersStartRow) goto L79;
            if (r4 <= this.topMembersEndRow) goto L82;
        L79:
            if (r4 < this.topInviterStartRow) goto L84;
            if (r4 <= this.topInviterEndRow) goto L82;
        L84:
            if (r4 != this.expandTopMembersRow) goto L87;
            return 15;
        L87:
            return 10;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 9) goto L5;
            return true;
        L5:
            if (r2.getItemViewType() == 15) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            int r0 = getItemViewType(r8);
            boolean r1 = false;
            if (r0 < 0) goto L61;
            if (r0 > 4) goto L61;
            if (this.growCell != r8) goto L10;
            ChartViewData r6 = StatisticActivity.m19237$$Nest$fgetgrowthData(this.this$0);
        L58:
            ((ChartCell) r7.itemView).updateData(r6, false);
            return;
        L10:
            if (this.folowersCell != r8) goto L13;
            r6 = StatisticActivity.m19235$$Nest$fgetfollowersData(this.this$0);
            goto L58
        L13:
            if (this.interactionsCell != r8) goto L16;
            r6 = StatisticActivity.m19240$$Nest$fgetinteractionsData(this.this$0);
            goto L58
        L16:
            if (this.viewsBySourceCell != r8) goto L19;
            r6 = StatisticActivity.m19276$$Nest$fgetviewsBySourceData(this.this$0);
            goto L58
        L19:
            if (this.newFollowersBySourceCell != r8) goto L22;
            r6 = StatisticActivity.m19252$$Nest$fgetnewFollowersBySourceData(this.this$0);
            goto L58
        L22:
            if (this.ivInteractionsCell != r8) goto L25;
            r6 = StatisticActivity.m19242$$Nest$fgetivInteractionsData(this.this$0);
            goto L58
        L25:
            if (this.topHourseCell != r8) goto L28;
            r6 = StatisticActivity.m19271$$Nest$fgettopHoursData(this.this$0);
            goto L58
        L28:
            if (this.notificationsCell != r8) goto L31;
            r6 = StatisticActivity.m19254$$Nest$fgetnotificationsData(this.this$0);
            goto L58
        L31:
            if (this.reactionsByEmotionCell != r8) goto L34;
            r6 = StatisticActivity.m19259$$Nest$fgetreactionsByEmotionData(this.this$0);
            goto L58
        L34:
            if (this.storyInteractionsCell != r8) goto L37;
            r6 = StatisticActivity.m19267$$Nest$fgetstoryInteractionsData(this.this$0);
            goto L58
        L37:
            if (this.storyReactionsByEmotionCell != r8) goto L40;
            r6 = StatisticActivity.m19268$$Nest$fgetstoryReactionsByEmotionData(this.this$0);
            goto L58
        L40:
            if (this.groupMembersCell != r8) goto L43;
            r6 = StatisticActivity.m19236$$Nest$fgetgroupMembersData(this.this$0);
            goto L58
        L43:
            if (this.newMembersBySourceCell != r8) goto L46;
            r6 = StatisticActivity.m19253$$Nest$fgetnewMembersBySourceData(this.this$0);
            goto L58
        L46:
            if (this.membersLanguageCell != r8) goto L49;
            r6 = StatisticActivity.m19247$$Nest$fgetmembersLanguageData(this.this$0);
            goto L58
        L49:
            if (this.messagesCell != r8) goto L52;
            r6 = StatisticActivity.m19248$$Nest$fgetmessagesData(this.this$0);
            goto L58
        L52:
            if (this.actionsCell != r8) goto L54;
            r6 = StatisticActivity.m19230$$Nest$fgetactionsData(this.this$0);
            goto L58
        L54:
            int r02 = this.topDayOfWeeksCell;
            StatisticActivity r62 = this.this$0;
            if (r02 != r8) goto L57;
            r6 = StatisticActivity.m19270$$Nest$fgettopDayOfWeeksData(r62);
            goto L58
        L57:
            r6 = StatisticActivity.m19243$$Nest$fgetlanguagesData(r62);
        L61:
            if (r0 != 9) goto L92;
            if (StatisticActivity.m19241$$Nest$fgetisMegagroup(this.this$0) == false) goto L82;
            int r03 = this.topAdminsStartRow;
            if (r8 >= r03) goto L67;
        L70:
            int r04 = this.topMembersStartRow;
            if (r8 >= r04) goto L73;
        L76:
            int r05 = this.topInviterStartRow;
            if (r8 >= r05) goto L79;
            return;
        L79:
            if (r8 > this.topInviterEndRow) goto L124;
            ((StatisticPostInfoCell) r7.itemView).setData((MemberData) StatisticActivity.m19272$$Nest$fgettopInviters(this.this$0).get(r8 - r05));
            return;
        L124:
            return;
        L73:
            if (r8 > this.topMembersEndRow) goto L76;
            ((StatisticPostInfoCell) r7.itemView).setData((MemberData) StatisticActivity.m19274$$Nest$fgettopMembersVisible(this.this$0).get(r8 - r04));
            return;
        L67:
            if (r8 > this.topAdminsEndRow) goto L70;
            ((StatisticPostInfoCell) r7.itemView).setData((MemberData) StatisticActivity.m19269$$Nest$fgettopAdmins(this.this$0).get(r8 - r03));
            return;
        L82:
            int r82 = r8 - this.recentPostsStartRow;
            RecentPostInfo r06 = (RecentPostInfo) StatisticActivity.m19260$$Nest$fgetrecentAllSortedDataLoaded(this.this$0).get(r82);
            StatisticPostInfoCell r72 = (StatisticPostInfoCell) r7.itemView;
            if (r82 != (StatisticActivity.m19260$$Nest$fgetrecentAllSortedDataLoaded(this.this$0).size() - 1)) goto L85;
            r1 = true;
        L85:
            r72.setData(r06, r1);
            if (r06.isStory() == false) goto L89;
            r72.setImageViewAction(new StatisticActivity$Adapter$$ExternalSyntheticLambda0(this, r06));
            return;
        L89:
            r72.setImageViewAction(null);
            return;
        L92:
            if (r0 != 13) goto L112;
            ChartHeaderView r73 = (ChartHeaderView) r7.itemView;
            r73.showDate(true);
            r73.setDates(StatisticActivity.m19250$$Nest$fgetminDateOverview(this.this$0), StatisticActivity.m19246$$Nest$fgetmaxDateOverview(this.this$0));
            r73.setPadding(0, AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f));
            if (r8 != this.overviewHeaderCell) goto L98;
            r73.setTitle(LocaleController.getString("StatisticOverview", R.string.StatisticOverview));
            return;
        L98:
            if (r8 != this.topAdminsHeaderCell) goto L102;
            r73.setTitle(LocaleController.getString("TopAdmins", R.string.TopAdmins));
            return;
        L102:
            if (r8 != this.topInviterHeaderCell) goto L106;
            r73.setTitle(LocaleController.getString("TopInviters", R.string.TopInviters));
            return;
        L106:
            if (r8 != this.topMembersHeaderCell) goto L109;
            r73.setTitle(LocaleController.getString("TopMembers", R.string.TopMembers));
            return;
        L109:
            r73.showDate(false);
            r73.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(15.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(6.0f));
            r73.setTitle(LocaleController.getString("RecentPostsCapitalize", R.string.RecentPostsCapitalize));
            return;
        L112:
            if (r0 != 14) goto L120;
            OverviewCell r74 = (OverviewCell) r7.itemView;
            boolean r83 = StatisticActivity.m19241$$Nest$fgetisMegagroup(this.this$0);
            StatisticActivity r07 = this.this$0;
            if (r83 == false) goto L117;
            r74.setData(StatisticActivity.m19257$$Nest$fgetoverviewChatData(r07));
            return;
        L117:
            r74.setData(StatisticActivity.m19256$$Nest$fgetoverviewChannelData(r07), StatisticActivity.m19233$$Nest$fgetchat(this.this$0));
            return;
        L120:
            if (r0 != 15) goto L125;
            ((ManageChatTextCell) r7.itemView).setText(LocaleController.formatPluralString("ShowVotes", StatisticActivity.m19273$$Nest$fgettopMembersAll(this.this$0).size() - StatisticActivity.m19274$$Nest$fgettopMembersVisible(this.this$0).size(), new Object[0]), null, R.drawable.arrow_more, false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            int r0 = 4;
            if (r10 < 0) goto L7;
            if (r10 > 4) goto L7;
            View r2 = new AnonymousClass1(this, r9.getContext(), StatisticActivity.access$000(this.this$0), r10, StatisticActivity.m19265$$Nest$fgetsharedUi(this.this$0));
            r2.setWillNotDraw(false);
            View r22 = r2;
        L28:
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L7:
            if (r10 != 9) goto L10;
            View r23 = new AnonymousClass2(this, r9.getContext(), StatisticActivity.m19233$$Nest$fgetchat(this.this$0), this.this$0.getResourceProvider());
            r23.setWillNotDraw(false);
            r22 = r23;
            goto L28
        L10:
            if (r10 != 11) goto L13;
            r22 = new LoadingCell(r9.getContext());
            goto L28
        L13:
            if (r10 != 12) goto L16;
            r22 = new EmptyCell(r9.getContext(), AndroidUtilities.dp(15.0f));
            goto L28
        L16:
            if (r10 != 13) goto L19;
            View r24 = new AnonymousClass3(this, r9.getContext());
            r24.setWillNotDraw(false);
            r24.setPadding(r24.getPaddingLeft(), AndroidUtilities.dp(16.0f), r24.getRight(), AndroidUtilities.dp(16.0f));
            r22 = r24;
            goto L28
        L19:
            if (r10 != 14) goto L25;
            Context r8 = r9.getContext();
            if (StatisticActivity.m19241$$Nest$fgetisMegagroup(this.this$0) == false) goto L23;
            r0 = 2;
        L23:
            r22 = new OverviewCell(r8, r0);
            goto L28
        L25:
            if (r10 != 15) goto L27;
            ManageChatTextCell r25 = new ManageChatTextCell(r9.getContext());
            r25.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r22 = r25;
            goto L28
        L27:
            r22 = new ShadowSectionCell(r9.getContext(), 12, 0);
            goto L28
        }

        public void update() {
            this.growCell = -1;
            this.folowersCell = -1;
            this.interactionsCell = -1;
            this.viewsBySourceCell = -1;
            this.newFollowersBySourceCell = -1;
            this.languagesCell = -1;
            this.recentPostsStartRow = -1;
            this.recentPostsEndRow = -1;
            this.progressCell = -1;
            this.recentPostsHeaderCell = -1;
            this.ivInteractionsCell = -1;
            this.topHourseCell = -1;
            this.notificationsCell = -1;
            this.storyReactionsByEmotionCell = -1;
            this.storyInteractionsCell = -1;
            this.reactionsByEmotionCell = -1;
            this.groupMembersCell = -1;
            this.newMembersBySourceCell = -1;
            this.membersLanguageCell = -1;
            this.messagesCell = -1;
            this.actionsCell = -1;
            this.topDayOfWeeksCell = -1;
            this.topMembersHeaderCell = -1;
            this.topMembersStartRow = -1;
            this.topMembersEndRow = -1;
            this.topAdminsHeaderCell = -1;
            this.topAdminsStartRow = -1;
            this.topAdminsEndRow = -1;
            this.topInviterHeaderCell = -1;
            this.topInviterStartRow = -1;
            this.topInviterEndRow = -1;
            this.expandTopMembersRow = -1;
            this.count = 0;
            this.emptyCells.clear();
            this.shadowDivideCells.clear();
            boolean r0 = StatisticActivity.m19241$$Nest$fgetisMegagroup(this.this$0);
            StatisticActivity r1 = this.this$0;
            if (r0 == false) goto L109;
            if (StatisticActivity.m19257$$Nest$fgetoverviewChatData(r1) == null) goto L8;
            int r02 = this.count;
            this.overviewHeaderCell = r02;
            this.count = r02 + 2;
            this.overviewCell = r02 + 1;
        L8:
            if (StatisticActivity.m19237$$Nest$fgetgrowthData(this.this$0) == null) goto L16;
            if (StatisticActivity.m19237$$Nest$fgetgrowthData(this.this$0).isEmpty == true) goto L16;
            int r03 = this.count;
            if (r03 <= 0) goto L14;
            ArraySet<Integer> r12 = this.shadowDivideCells;
            this.count = r03 + 1;
            r12.add(Integer.valueOf(r03));
        L14:
            int r04 = this.count;
            this.count = r04 + 1;
            this.growCell = r04;
        L16:
            if (StatisticActivity.m19236$$Nest$fgetgroupMembersData(this.this$0) == null) goto L24;
            if (StatisticActivity.m19236$$Nest$fgetgroupMembersData(this.this$0).isEmpty == true) goto L24;
            int r05 = this.count;
            if (r05 <= 0) goto L22;
            ArraySet<Integer> r13 = this.shadowDivideCells;
            this.count = r05 + 1;
            r13.add(Integer.valueOf(r05));
        L22:
            int r06 = this.count;
            this.count = r06 + 1;
            this.groupMembersCell = r06;
        L24:
            if (StatisticActivity.m19253$$Nest$fgetnewMembersBySourceData(this.this$0) == null) goto L34;
            if (StatisticActivity.m19253$$Nest$fgetnewMembersBySourceData(this.this$0).isEmpty == true) goto L34;
            if (StatisticActivity.m19253$$Nest$fgetnewMembersBySourceData(this.this$0).isError == true) goto L34;
            int r07 = this.count;
            if (r07 <= 0) goto L32;
            ArraySet<Integer> r14 = this.shadowDivideCells;
            this.count = r07 + 1;
            r14.add(Integer.valueOf(r07));
        L32:
            int r08 = this.count;
            this.count = r08 + 1;
            this.newMembersBySourceCell = r08;
        L34:
            if (StatisticActivity.m19247$$Nest$fgetmembersLanguageData(this.this$0) == null) goto L44;
            if (StatisticActivity.m19247$$Nest$fgetmembersLanguageData(this.this$0).isEmpty == true) goto L44;
            if (StatisticActivity.m19247$$Nest$fgetmembersLanguageData(this.this$0).isError == true) goto L44;
            int r09 = this.count;
            if (r09 <= 0) goto L42;
            ArraySet<Integer> r15 = this.shadowDivideCells;
            this.count = r09 + 1;
            r15.add(Integer.valueOf(r09));
        L42:
            int r010 = this.count;
            this.count = r010 + 1;
            this.membersLanguageCell = r010;
        L44:
            if (StatisticActivity.m19248$$Nest$fgetmessagesData(this.this$0) == null) goto L54;
            if (StatisticActivity.m19248$$Nest$fgetmessagesData(this.this$0).isEmpty == true) goto L54;
            if (StatisticActivity.m19248$$Nest$fgetmessagesData(this.this$0).isError == true) goto L54;
            int r011 = this.count;
            if (r011 <= 0) goto L52;
            ArraySet<Integer> r16 = this.shadowDivideCells;
            this.count = r011 + 1;
            r16.add(Integer.valueOf(r011));
        L52:
            int r012 = this.count;
            this.count = r012 + 1;
            this.messagesCell = r012;
        L54:
            if (StatisticActivity.m19230$$Nest$fgetactionsData(this.this$0) == null) goto L64;
            if (StatisticActivity.m19230$$Nest$fgetactionsData(this.this$0).isEmpty == true) goto L64;
            if (StatisticActivity.m19230$$Nest$fgetactionsData(this.this$0).isError == true) goto L64;
            int r013 = this.count;
            if (r013 <= 0) goto L62;
            ArraySet<Integer> r17 = this.shadowDivideCells;
            this.count = r013 + 1;
            r17.add(Integer.valueOf(r013));
        L62:
            int r014 = this.count;
            this.count = r014 + 1;
            this.actionsCell = r014;
        L64:
            if (StatisticActivity.m19271$$Nest$fgettopHoursData(this.this$0) == null) goto L74;
            if (StatisticActivity.m19271$$Nest$fgettopHoursData(this.this$0).isEmpty == true) goto L74;
            if (StatisticActivity.m19271$$Nest$fgettopHoursData(this.this$0).isError == true) goto L74;
            int r015 = this.count;
            if (r015 <= 0) goto L72;
            ArraySet<Integer> r18 = this.shadowDivideCells;
            this.count = r015 + 1;
            r18.add(Integer.valueOf(r015));
        L72:
            int r016 = this.count;
            this.count = r016 + 1;
            this.topHourseCell = r016;
        L74:
            if (StatisticActivity.m19270$$Nest$fgettopDayOfWeeksData(this.this$0) == null) goto L84;
            if (StatisticActivity.m19270$$Nest$fgettopDayOfWeeksData(this.this$0).isEmpty == true) goto L84;
            if (StatisticActivity.m19270$$Nest$fgettopDayOfWeeksData(this.this$0).isError == true) goto L84;
            int r017 = this.count;
            if (r017 <= 0) goto L82;
            ArraySet<Integer> r19 = this.shadowDivideCells;
            this.count = r017 + 1;
            r19.add(Integer.valueOf(r017));
        L82:
            int r018 = this.count;
            this.count = r018 + 1;
            this.topDayOfWeeksCell = r018;
        L84:
            if (StatisticActivity.m19274$$Nest$fgettopMembersVisible(this.this$0).size() <= 0) goto L93;
            int r019 = this.count;
            if (r019 <= 0) goto L88;
            ArraySet<Integer> r110 = this.shadowDivideCells;
            this.count = r019 + 1;
            r110.add(Integer.valueOf(r019));
        L88:
            int r020 = this.count;
            int r111 = r020 + 1;
            this.topMembersHeaderCell = r020;
            this.count = r020 + 2;
            this.topMembersStartRow = r111;
            int r112 = r111 + StatisticActivity.m19274$$Nest$fgettopMembersVisible(this.this$0).size();
            this.topMembersEndRow = r112 - 1;
            this.count = r112;
            if (StatisticActivity.m19274$$Nest$fgettopMembersVisible(this.this$0).size() == StatisticActivity.m19273$$Nest$fgettopMembersAll(this.this$0).size()) goto L91;
            int r021 = this.count;
            this.count = r021 + 1;
            this.expandTopMembersRow = r021;
            goto L93
        L91:
            ArraySet<Integer> r022 = this.emptyCells;
            int r113 = this.count;
            this.count = r113 + 1;
            r022.add(Integer.valueOf(r113));
        L93:
            if (StatisticActivity.m19269$$Nest$fgettopAdmins(this.this$0).size() <= 0) goto L99;
            int r023 = this.count;
            if (r023 <= 0) goto L97;
            ArraySet<Integer> r114 = this.shadowDivideCells;
            this.count = r023 + 1;
            r114.add(Integer.valueOf(r023));
        L97:
            int r024 = this.count;
            int r115 = r024 + 1;
            this.topAdminsHeaderCell = r024;
            this.count = r024 + 2;
            this.topAdminsStartRow = r115;
            int r116 = r115 + StatisticActivity.m19269$$Nest$fgettopAdmins(this.this$0).size();
            this.topAdminsEndRow = r116 - 1;
            ArraySet<Integer> r025 = this.emptyCells;
            this.count = r116 + 1;
            r025.add(Integer.valueOf(r116));
        L99:
            if (StatisticActivity.m19272$$Nest$fgettopInviters(this.this$0).size() <= 0) goto L104;
            int r026 = this.count;
            if (r026 <= 0) goto L103;
            ArraySet<Integer> r117 = this.shadowDivideCells;
            this.count = r026 + 1;
            r117.add(Integer.valueOf(r026));
        L103:
            int r027 = this.count;
            int r118 = r027 + 1;
            this.topInviterHeaderCell = r027;
            this.count = r027 + 2;
            this.topInviterStartRow = r118;
            int r119 = r118 + StatisticActivity.m19272$$Nest$fgettopInviters(this.this$0).size();
            this.topInviterEndRow = r119 - 1;
            this.count = r119;
        L104:
            int r028 = this.count;
            if (r028 <= 0) goto L223;
            ArraySet<Integer> r120 = this.emptyCells;
            this.count = r028 + 1;
            r120.add(Integer.valueOf(r028));
            ArraySet<Integer> r029 = this.shadowDivideCells;
            int r121 = this.count;
            this.count = r121 + 1;
            r029.add(Integer.valueOf(r121));
            return;
        L223:
            return;
        L109:
            if (StatisticActivity.m19256$$Nest$fgetoverviewChannelData(r1) == null) goto L112;
            int r030 = this.count;
            this.overviewHeaderCell = r030;
            this.count = r030 + 2;
            this.overviewCell = r030 + 1;
        L112:
            if (StatisticActivity.m19237$$Nest$fgetgrowthData(this.this$0) == null) goto L120;
            if (StatisticActivity.m19237$$Nest$fgetgrowthData(this.this$0).isEmpty == true) goto L120;
            int r031 = this.count;
            if (r031 <= 0) goto L118;
            ArraySet<Integer> r122 = this.shadowDivideCells;
            this.count = r031 + 1;
            r122.add(Integer.valueOf(r031));
        L118:
            int r032 = this.count;
            this.count = r032 + 1;
            this.growCell = r032;
        L120:
            if (StatisticActivity.m19235$$Nest$fgetfollowersData(this.this$0) == null) goto L128;
            if (StatisticActivity.m19235$$Nest$fgetfollowersData(this.this$0).isEmpty == true) goto L128;
            int r033 = this.count;
            if (r033 <= 0) goto L126;
            ArraySet<Integer> r123 = this.shadowDivideCells;
            this.count = r033 + 1;
            r123.add(Integer.valueOf(r033));
        L126:
            int r034 = this.count;
            this.count = r034 + 1;
            this.folowersCell = r034;
        L128:
            if (StatisticActivity.m19254$$Nest$fgetnotificationsData(this.this$0) == null) goto L136;
            if (StatisticActivity.m19254$$Nest$fgetnotificationsData(this.this$0).isEmpty == true) goto L136;
            int r035 = this.count;
            if (r035 <= 0) goto L134;
            ArraySet<Integer> r124 = this.shadowDivideCells;
            this.count = r035 + 1;
            r124.add(Integer.valueOf(r035));
        L134:
            int r036 = this.count;
            this.count = r036 + 1;
            this.notificationsCell = r036;
        L136:
            if (StatisticActivity.m19271$$Nest$fgettopHoursData(this.this$0) == null) goto L144;
            if (StatisticActivity.m19271$$Nest$fgettopHoursData(this.this$0).isEmpty == true) goto L144;
            int r037 = this.count;
            if (r037 <= 0) goto L142;
            ArraySet<Integer> r125 = this.shadowDivideCells;
            this.count = r037 + 1;
            r125.add(Integer.valueOf(r037));
        L142:
            int r038 = this.count;
            this.count = r038 + 1;
            this.topHourseCell = r038;
        L144:
            if (StatisticActivity.m19276$$Nest$fgetviewsBySourceData(this.this$0) == null) goto L152;
            if (StatisticActivity.m19276$$Nest$fgetviewsBySourceData(this.this$0).isEmpty == true) goto L152;
            int r039 = this.count;
            if (r039 <= 0) goto L150;
            ArraySet<Integer> r126 = this.shadowDivideCells;
            this.count = r039 + 1;
            r126.add(Integer.valueOf(r039));
        L150:
            int r040 = this.count;
            this.count = r040 + 1;
            this.viewsBySourceCell = r040;
        L152:
            if (StatisticActivity.m19252$$Nest$fgetnewFollowersBySourceData(this.this$0) == null) goto L160;
            if (StatisticActivity.m19252$$Nest$fgetnewFollowersBySourceData(this.this$0).isEmpty == true) goto L160;
            int r041 = this.count;
            if (r041 <= 0) goto L158;
            ArraySet<Integer> r127 = this.shadowDivideCells;
            this.count = r041 + 1;
            r127.add(Integer.valueOf(r041));
        L158:
            int r042 = this.count;
            this.count = r042 + 1;
            this.newFollowersBySourceCell = r042;
        L160:
            if (StatisticActivity.m19243$$Nest$fgetlanguagesData(this.this$0) == null) goto L168;
            if (StatisticActivity.m19243$$Nest$fgetlanguagesData(this.this$0).isEmpty == true) goto L168;
            int r043 = this.count;
            if (r043 <= 0) goto L166;
            ArraySet<Integer> r128 = this.shadowDivideCells;
            this.count = r043 + 1;
            r128.add(Integer.valueOf(r043));
        L166:
            int r044 = this.count;
            this.count = r044 + 1;
            this.languagesCell = r044;
        L168:
            if (StatisticActivity.m19240$$Nest$fgetinteractionsData(this.this$0) == null) goto L176;
            if (StatisticActivity.m19240$$Nest$fgetinteractionsData(this.this$0).isEmpty == true) goto L176;
            int r045 = this.count;
            if (r045 <= 0) goto L174;
            ArraySet<Integer> r129 = this.shadowDivideCells;
            this.count = r045 + 1;
            r129.add(Integer.valueOf(r045));
        L174:
            int r046 = this.count;
            this.count = r046 + 1;
            this.interactionsCell = r046;
        L176:
            if (StatisticActivity.m19242$$Nest$fgetivInteractionsData(this.this$0) == null) goto L186;
            if (StatisticActivity.m19242$$Nest$fgetivInteractionsData(this.this$0).loading == true) goto L186;
            if (StatisticActivity.m19242$$Nest$fgetivInteractionsData(this.this$0).isError == true) goto L186;
            int r047 = this.count;
            if (r047 <= 0) goto L184;
            ArraySet<Integer> r130 = this.shadowDivideCells;
            this.count = r047 + 1;
            r130.add(Integer.valueOf(r047));
        L184:
            int r048 = this.count;
            this.count = r048 + 1;
            this.ivInteractionsCell = r048;
        L186:
            if (StatisticActivity.m19259$$Nest$fgetreactionsByEmotionData(this.this$0) == null) goto L196;
            if (StatisticActivity.m19259$$Nest$fgetreactionsByEmotionData(this.this$0).isEmpty == true) goto L196;
            if (StatisticActivity.m19259$$Nest$fgetreactionsByEmotionData(this.this$0).isError == true) goto L196;
            int r049 = this.count;
            if (r049 <= 0) goto L194;
            ArraySet<Integer> r131 = this.shadowDivideCells;
            this.count = r049 + 1;
            r131.add(Integer.valueOf(r049));
        L194:
            int r050 = this.count;
            this.count = r050 + 1;
            this.reactionsByEmotionCell = r050;
        L196:
            if (StatisticActivity.m19267$$Nest$fgetstoryInteractionsData(this.this$0) == null) goto L206;
            if (StatisticActivity.m19267$$Nest$fgetstoryInteractionsData(this.this$0).isEmpty == true) goto L206;
            if (StatisticActivity.m19267$$Nest$fgetstoryInteractionsData(this.this$0).isError == true) goto L206;
            int r051 = this.count;
            if (r051 <= 0) goto L204;
            ArraySet<Integer> r132 = this.shadowDivideCells;
            this.count = r051 + 1;
            r132.add(Integer.valueOf(r051));
        L204:
            int r052 = this.count;
            this.count = r052 + 1;
            this.storyInteractionsCell = r052;
        L206:
            if (StatisticActivity.m19268$$Nest$fgetstoryReactionsByEmotionData(this.this$0) != null) goto L208;
        L215:
            ArraySet<Integer> r053 = this.shadowDivideCells;
            int r133 = this.count;
            this.count = r133 + 1;
            r053.add(Integer.valueOf(r133));
            if (StatisticActivity.m19260$$Nest$fgetrecentAllSortedDataLoaded(this.this$0).size() <= 0) goto L224;
            int r054 = this.count;
            int r134 = r054 + 1;
            this.recentPostsHeaderCell = r054;
            this.count = r054 + 2;
            this.recentPostsStartRow = r134;
            int r135 = r134 + StatisticActivity.m19260$$Nest$fgetrecentAllSortedDataLoaded(this.this$0).size();
            this.recentPostsEndRow = r135 - 1;
            this.count = r135;
            if (StatisticActivity.m19262$$Nest$fgetrecentPostsLoaded(this.this$0).size() == StatisticActivity.m19261$$Nest$fgetrecentPostsAll(this.this$0).size()) goto L220;
            int r055 = this.count;
            this.count = r055 + 1;
            this.progressCell = r055;
        L221:
            ArraySet<Integer> r056 = this.shadowDivideCells;
            int r136 = this.count;
            this.count = r136 + 1;
            r056.add(Integer.valueOf(r136));
            return;
        L220:
            ArraySet<Integer> r057 = this.emptyCells;
            int r137 = this.count;
            this.count = r137 + 1;
            r057.add(Integer.valueOf(r137));
            goto L221
        L224:
            return;
        L208:
            if (StatisticActivity.m19268$$Nest$fgetstoryReactionsByEmotionData(this.this$0).isEmpty == true) goto L215;
            if (StatisticActivity.m19268$$Nest$fgetstoryReactionsByEmotionData(this.this$0).isError == true) goto L215;
            int r058 = this.count;
            if (r058 <= 0) goto L214;
            ArraySet<Integer> r138 = this.shadowDivideCells;
            this.count = r058 + 1;
            r138.add(Integer.valueOf(r058));
        L214:
            int r059 = this.count;
            this.count = r059 + 1;
            this.storyReactionsByEmotionCell = r059;
            goto L215
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class BaseChartCell extends FrameLayout {
        ChartHeaderView chartHeaderView;
        int chartType;
        BaseChartView chartView;
        ArrayList<CheckBoxHolder> checkBoxes;
        ViewGroup checkboxContainer;
        ChartViewData data;
        TextView errorTextView;
        RadialProgressView progressView;
        final Window window;
        BaseChartView zoomedChartView;

        /* renamed from: org.telegram.ui.StatisticActivity$BaseChartCell$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 extends FrameLayout {
            final /* synthetic */ BaseChartCell this$0;

            public AnonymousClass1(BaseChartCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
                int r42 = getChildCount();
                int r62 = 0;
                int r72 = 0;
                int r82 = 0;
            L3:
                if (r62 >= r42) goto L8;
                if ((getChildAt(r62).getMeasuredWidth() + r72) <= getMeasuredWidth()) goto L7;
                r82 = r82 + getChildAt(r62).getMeasuredHeight();
                r72 = 0;
            L7:
                getChildAt(r62).layout(r72, r82, getChildAt(r62).getMeasuredWidth() + r72, getChildAt(r62).getMeasuredHeight() + r82);
                r72 = r72 + getChildAt(r62).getMeasuredWidth();
                r62 = r62 + 1;
                goto L3
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r7, int r8) {
                int r72 = View.MeasureSpec.getSize(r7);
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), r8);
                int r82 = getChildCount();
                int r0 = 0;
                if (r82 <= 0) goto L5;
                int r1 = getChildAt(0).getMeasuredHeight();
            L6:
                int r2 = 0;
                int r3 = 0;
                int r4 = 0;
            L7:
                if (r2 >= r82) goto L12;
                if ((getChildAt(r2).getMeasuredWidth() + r3) <= r72) goto L11;
                r4 = r4 + getChildAt(r2).getMeasuredHeight();
                r3 = 0;
            L11:
                r3 = r3 + getChildAt(r2).getMeasuredWidth();
                r2 = r2 + 1;
                goto L7
            L12:
                int r73 = getMeasuredWidth();
                if (getChildCount() == 0) goto L16;
                r0 = (r1 + r4) + AndroidUtilities.dp(16.0f);
            L16:
                setMeasuredDimension(r73, r0);
                return;
            L5:
                r1 = 0;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.StatisticActivity$BaseChartCell$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ BaseChartCell this$0;

            public AnonymousClass2(BaseChartCell r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                this.this$0.chartView.setVisibility(4);
                BaseChartCell r3 = this.this$0;
                BaseChartView r42 = r3.chartView;
                r42.enabled = false;
                BaseChartView r1 = r3.zoomedChartView;
                r1.enabled = true;
                r42.transitionMode = 0;
                r1.transitionMode = 0;
                Window r32 = r3.window;
                if (r32 == null) goto L6;
                r32.clearFlags(16);
                return;
            }
        }

        /* renamed from: org.telegram.ui.StatisticActivity$BaseChartCell$3, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ BaseChartCell this$0;

            public AnonymousClass3(BaseChartCell r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                this.this$0.zoomedChartView.setVisibility(4);
                BaseChartCell r42 = this.this$0;
                BaseChartView r0 = r42.chartView;
                r0.transitionMode = 0;
                BaseChartView r43 = r42.zoomedChartView;
                r43.transitionMode = 0;
                r0.enabled = true;
                r43.enabled = false;
                if ((r0 instanceof StackLinearChartView) == true) goto L5;
                r0.legendShowing = true;
                r0.moveLegend();
                this.this$0.chartView.animateLegend(true);
                this.this$0.chartView.invalidate();
            L6:
                Window r3 = this.this$0.window;
                if (r3 == null) goto L10;
                r3.clearFlags(16);
                return;
            L10:
                return;
            L5:
                r0.legendShowing = false;
                r0.clearSelection();
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.StatisticActivity$BaseChartCell$4, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ BaseChartCell this$0;

            public AnonymousClass4(BaseChartCell r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                BaseChartCell r1 = this.this$0;
                r1.chartView.transitionMode = 0;
                r1.progressView.setVisibility(8);
            }
        }

        /* loaded from: classes6.dex */
        public class CheckBoxHolder {
            final FlatCheckBox checkBox;
            LineViewData line;
            final int position;
            final /* synthetic */ BaseChartCell this$0;

            public static /* synthetic */ void $r8$lambda$aLcrFy5OlSfYCw3DIUA2_uxXsD4(CheckBoxHolder r0, LineViewData r1, View r2) {
                r0.lambda$setData$0(r1, r2);
            }

            public static /* synthetic */ boolean $r8$lambda$kvjyblqbIZKB_BiA1TSVIpPQSX0(CheckBoxHolder r0, LineViewData r1, View r2) {
                return r0.lambda$setData$1(r1, r2);
            }

            public CheckBoxHolder(BaseChartCell r4, int r5) {
                this.this$0 = r4;
                this.position = r5;
                FlatCheckBox r52 = new FlatCheckBox(r4.getContext());
                this.checkBox = r52;
                r52.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
                r4.checkboxContainer.addView(r52);
                r4.checkBoxes.add(this);
            }

            private /* synthetic */ void lambda$setData$0(LineViewData r5, View r6) {
                if (this.checkBox.enabled == false) goto L34;
                int r62 = this.this$0.checkBoxes.size();
                boolean r0 = false;
                int r1 = 0;
            L7:
                if (r1 >= r62) goto L16;
                if (r1 == this.position) goto L15;
                if (this.this$0.checkBoxes.get(r1).checkBox.enabled == false) goto L15;
                if (this.this$0.checkBoxes.get(r1).checkBox.checked == false) goto L15;
            L17:
                this.this$0.zoomCanceled();
                FlatCheckBox r63 = this.checkBox;
                if (r0 == false) goto L21;
                r63.denied();
                return;
            L21:
                r63.setChecked(!r63.checked);
                r5.enabled = this.checkBox.checked;
                this.this$0.chartView.onCheckChanged();
                BaseChartCell r52 = this.this$0;
                if (r52.data.activeZoom > 0) goto L24;
                return;
            L24:
                if (this.position >= r52.zoomedChartView.lines.size()) goto L33;
                ((LineViewData) this.this$0.zoomedChartView.lines.get(this.position)).enabled = this.checkBox.checked;
                this.this$0.zoomedChartView.onCheckChanged();
                return;
            L33:
                return;
            L15:
                r1 = r1 + 1;
                goto L7
            L16:
                r0 = true;
                goto L17
            }

            private /* synthetic */ boolean lambda$setData$1(LineViewData r8, View r9) {
                if (this.checkBox.enabled == true) goto L5;
                return false;
            L5:
                this.this$0.zoomCanceled();
                int r92 = this.this$0.checkBoxes.size();
                int r1 = 0;
            L7:
                if (r1 >= r92) goto L14;
                this.this$0.checkBoxes.get(r1).checkBox.setChecked(false);
                this.this$0.checkBoxes.get(r1).line.enabled = false;
                BaseChartCell r4 = this.this$0;
                if (r4.data.activeZoom <= 0) goto L13;
                if (r1 >= r4.zoomedChartView.lines.size()) goto L13;
                ((LineViewData) this.this$0.zoomedChartView.lines.get(r1)).enabled = false;
            L13:
                r1 = r1 + 1;
                goto L7
            L14:
                this.checkBox.setChecked(true);
                r8.enabled = true;
                this.this$0.chartView.onCheckChanged();
                BaseChartCell r82 = this.this$0;
                if (r82.data.activeZoom <= 0) goto L17;
                ((LineViewData) r82.zoomedChartView.lines.get(this.position)).enabled = true;
                this.this$0.zoomedChartView.onCheckChanged();
            L17:
                return true;
            }

            public void recolor(int r1) {
                this.checkBox.recolor(r1);
            }

            public void setData(LineViewData r4) {
                this.line = r4;
                this.checkBox.setText(r4.line.name);
                this.checkBox.setChecked(r4.enabled, false);
                this.checkBox.setOnTouchListener(new RecyclerListView.FoucsableOnTouchListener());
                this.checkBox.setOnClickListener(new StatisticActivity$BaseChartCell$CheckBoxHolder$$ExternalSyntheticLambda0(this, r4));
                this.checkBox.setOnLongClickListener(new StatisticActivity$BaseChartCell$CheckBoxHolder$$ExternalSyntheticLambda1(this, r4));
            }
        }

        public static /* synthetic */ void $r8$lambda$2ZXd9xHwj2UzqRC8ICk7UfLrxpE(BaseChartCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$M300l7FVWcRq6l3lCS7qawX2Gfc(BaseChartCell r0, long r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$QrPwqYh2pTNiJM_H9q2L7fadNOo(BaseChartCell r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$RAa_AZgMeTmV90h3fZrtuBtXzp0(BaseChartCell r0, ValueAnimator r1) {
            r0.lambda$updateData$5(r1);
        }

        /* renamed from: $r8$lambda$UyxITjH46P9X-f9qly_OzlsBqmg, reason: not valid java name */
        public static /* synthetic */ void m19283$r8$lambda$UyxITjH46P9Xf9qly_OzlsBqmg(BaseChartCell r0, TransitionParams r1, float r2, ValueAnimator r3) {
            r0.lambda$createTransitionAnimator$4(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$vI6a4KfAeQyHIQu-516lDfBz8k0, reason: not valid java name */
        public static /* synthetic */ void m19284$r8$lambda$vI6a4KfAeQyHIQu516lDfBz8k0(BaseChartCell r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public BaseChartCell(Context r2, int r3, BaseChartView.SharedUiComponents r4) {
            this(r2, r3, r4, null);
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public BaseChartCell(Context r12, int r13, BaseChartView.SharedUiComponents r14, Theme.ResourcesProvider r15) {
            super(r12);
            this.checkBoxes = new ArrayList();
            setWillNotDraw(false);
            if ((r12 instanceof Activity) == false) goto L5;
            this.window = ((Activity) r12).getWindow();
        L6:
            this.chartType = r13;
            LinearLayout r1 = new LinearLayout(r12);
            r1.setOrientation(1);
            this.checkboxContainer = new AnonymousClass1(this, r12);
            ChartHeaderView r3 = new ChartHeaderView(getContext(), r15);
            this.chartHeaderView = r3;
            r3.back.setOnTouchListener(new RecyclerListView.FoucsableOnTouchListener());
            this.chartHeaderView.back.setOnClickListener(new StatisticActivity$BaseChartCell$$ExternalSyntheticLambda0(this));
            if (r13 != 1) goto L9;
            this.chartView = new DoubleLinearChartView(getContext(), r15);
            DoubleLinearChartView r132 = new DoubleLinearChartView(getContext(), r15);
            this.zoomedChartView = r132;
            r132.legendSignatureView.useHour = true;
        L21:
            FrameLayout r133 = new FrameLayout(r12);
            this.chartView.sharedUiComponents = r14;
            this.zoomedChartView.sharedUiComponents = r14;
            this.progressView = new RadialProgressView(r12);
            r133.addView(this.chartView);
            r133.addView(this.chartView.legendSignatureView, -2, -2);
            r133.addView(this.zoomedChartView);
            r133.addView(this.zoomedChartView.legendSignatureView, -2, -2);
            r133.addView(this.progressView, LayoutHelper.createFrame(44, 44.0f, 17, 0.0f, 0.0f, 0.0f, 60.0f));
            TextView r142 = new TextView(r12);
            this.errorTextView = r142;
            r142.setTextSize(1, 15.0f);
            r133.addView(this.errorTextView, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 0.0f, 0.0f, 30.0f));
            this.progressView.setVisibility(8);
            this.errorTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray4, r15));
            this.chartView.setDateSelectionListener(new StatisticActivity$BaseChartCell$$ExternalSyntheticLambda1(this));
            this.chartView.legendSignatureView.showProgress(false, false);
            this.chartView.legendSignatureView.setOnTouchListener(new RecyclerListView.FoucsableOnTouchListener());
            this.chartView.legendSignatureView.setOnClickListener(new StatisticActivity$BaseChartCell$$ExternalSyntheticLambda2(this));
            this.zoomedChartView.legendSignatureView.setOnClickListener(new StatisticActivity$BaseChartCell$$ExternalSyntheticLambda3(this));
            this.chartView.setVisibility(0);
            this.zoomedChartView.setVisibility(4);
            this.chartView.setHeader(this.chartHeaderView);
            r1.addView(this.chartHeaderView, LayoutHelper.createFrame(-1, 52.0f));
            r1.addView(r133, LayoutHelper.createFrame(-1, -2.0f));
            r1.addView(this.checkboxContainer, LayoutHelper.createFrame(-1, -2.0f, 7, 10.0f, 0.0f, 10.0f, 0.0f));
            if (this.chartType != 4) goto L24;
            r133.setClipChildren(false);
            r133.setClipToPadding(false);
            r1.setClipChildren(false);
            r1.setClipToPadding(false);
        L24:
            addView(r1);
            return;
        L9:
            if (r13 != 2) goto L11;
            this.chartView = new StackBarChartView(getContext(), r15);
            StackBarChartView r134 = new StackBarChartView(getContext(), r15);
            this.zoomedChartView = r134;
            r134.legendSignatureView.useHour = true;
            goto L21
        L11:
            if (r13 == 3) goto L18;
            if (r13 != 4) goto L14;
            StackLinearChartView r135 = new StackLinearChartView(getContext());
            this.chartView = r135;
            r135.legendSignatureView.showPercentage = true;
            this.zoomedChartView = new PieChartView(getContext());
            goto L21
        L14:
            if (r13 == 5) goto L16;
            this.chartView = new LinearChartView(getContext());
            LinearChartView r136 = new LinearChartView(getContext());
            this.zoomedChartView = r136;
            r136.legendSignatureView.useHour = true;
            goto L21
        L16:
            this.chartView = new LinearBarChartView(getContext());
            LinearBarChartView r137 = new LinearBarChartView(getContext());
            this.zoomedChartView = r137;
            r137.legendSignatureView.useHour = true;
            goto L21
        L18:
            this.chartView = new BarChartView(getContext());
            LinearChartView r138 = new LinearChartView(getContext());
            this.zoomedChartView = r138;
            r138.legendSignatureView.useHour = true;
            goto L21
        L5:
            this.window = null;
            goto L6
        }

        private ValueAnimator createTransitionAnimator(long r12, boolean r14) {
            Window r0 = this.window;
            if (r0 == null) goto L5;
            r0.setFlags(16, 16);
        L5:
            BaseChartView r02 = this.chartView;
            r02.enabled = false;
            BaseChartView r2 = this.zoomedChartView;
            r2.enabled = false;
            r02.transitionMode = 2;
            r2.transitionMode = 1;
            TransitionParams r22 = new TransitionParams();
            ChartPickerDelegate r4 = this.chartView.pickerDelegate;
            r22.pickerEndOut = r4.pickerEnd;
            r22.pickerStartOut = r4.pickerStart;
            r22.date = r12;
            int r122 = Arrays.binarySearch(this.data.chartData.x, r12);
            if (r122 >= 0) goto L8;
            r122 = this.data.chartData.x.length - 1;
        L8:
            r22.xPercentage = this.data.chartData.xPercentage[r122];
            this.zoomedChartView.setVisibility(0);
            this.zoomedChartView.transitionParams = r22;
            this.chartView.transitionParams = r22;
            long r42 = 0;
            long r6 = 2147483647L;
            int r13 = 0;
        L10:
            if (r13 >= this.data.chartData.lines.size()) goto L18;
            if (this.data.chartData.lines.get(r13).y[r122] <= r42) goto L15;
            r42 = this.data.chartData.lines.get(r13).y[r122];
        L15:
            if (this.data.chartData.lines.get(r13).y[r122] >= r6) goto L17;
            r6 = this.data.chartData.lines.get(r13).y[r122];
        L17:
            r13 = r13 + 1;
            goto L10
        L18:
            float r123 = r6 + (r42 - r6);
            BaseChartView r132 = this.chartView;
            float r43 = r132.currentMinHeight;
            float r124 = (r123 - r43) / (r132.currentMaxHeight - r43);
            r132.fillTransitionParams(r22);
            this.zoomedChartView.fillTransitionParams(r22);
            float r133 = 1.0f;
            if (r14 == false) goto L21;
            float r5 = 0.0f;
        L22:
            if (r14 == true) goto L25;
            r133 = 0.0f;
        L25:
            ValueAnimator r134 = ValueAnimator.ofFloat(new float[]{r5, r133});
            r134.addUpdateListener(new StatisticActivity$BaseChartCell$$ExternalSyntheticLambda5(this, r22, r124));
            r134.setDuration(400);
            r134.setInterpolator(new FastOutSlowInInterpolator());
            return r134;
        L21:
            r5 = 1.0f;
            goto L22
        }

        private /* synthetic */ void lambda$createTransitionAnimator$4(TransitionParams r5, float r6, ValueAnimator r7) {
            BaseChartView r0 = this.chartView;
            float r1 = r0.chartWidth;
            ChartPickerDelegate r2 = r0.pickerDelegate;
            float r3 = r2.pickerEnd;
            float r22 = r2.pickerStart;
            float r12 = ((r1 / (r3 - r22)) * r22) - BaseChartView.HORIZONTAL_PADDING;
            RectF r02 = r0.chartArea;
            r5.pY = r02.top + ((1.0f - r6) * r02.height());
            r5.pX = (this.chartView.chartFullWidth * r5.xPercentage) - r12;
            r5.progress = ((Float) r7.getAnimatedValue()).floatValue();
            this.zoomedChartView.invalidate();
            this.zoomedChartView.fillTransitionParams(r5);
            this.chartView.invalidate();
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            zoomOut(true);
        }

        private /* synthetic */ void lambda$new$1(long r1) {
            zoomCanceled();
            this.chartView.legendSignatureView.showProgress(false, false);
        }

        private /* synthetic */ void lambda$new$2(View r1) {
            onZoomed();
        }

        private /* synthetic */ void lambda$new$3(View r1) {
            this.zoomedChartView.animateLegend(false);
        }

        private /* synthetic */ void lambda$updateData$5(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            this.progressView.setAlpha(1.0f - r32);
            this.chartView.transitionParams.progress = r32;
            this.zoomedChartView.invalidate();
            this.chartView.invalidate();
        }

        private void zoomOut(boolean r8) {
            ChartViewData r0 = this.data;
            if (r0 == null) goto L24;
            ChartData r02 = r0.chartData;
            if (r02 != null) goto L7;
            return;
        L7:
            if (r02.x == null) goto L26;
            this.chartHeaderView.zoomOut(this.chartView, r8);
            this.chartView.legendSignatureView.chevron.setAlpha(1.0f);
            this.zoomedChartView.setHeader(null);
            long r3 = this.chartView.getSelectedDate();
            this.data.activeZoom = 0;
            int r5 = 0;
            this.chartView.setVisibility(0);
            this.zoomedChartView.clearSelection();
            this.zoomedChartView.setHeader(null);
            this.chartView.setHeader(this.chartHeaderView);
            if (r8 == true) goto L17;
            this.zoomedChartView.setVisibility(4);
            BaseChartView r82 = this.chartView;
            r82.enabled = true;
            this.zoomedChartView.enabled = false;
            r82.invalidate();
            Window r83 = this.window;
            if (r83 == null) goto L14;
            r83.clearFlags(16);
        L14:
            ArrayList<CheckBoxHolder> r7 = this.checkBoxes;
            int r84 = r7.size();
        L15:
            if (r5 >= r84) goto L27;
            CheckBoxHolder r2 = r7.get(r5);
            r5 = r5 + 1;
            CheckBoxHolder r22 = r2;
            r22.checkBox.setAlpha(1.0f);
            r22.checkBox.enabled = true;
            goto L15
        L27:
            return;
        L17:
            ValueAnimator r85 = createTransitionAnimator(r3, false);
            r85.addListener(new AnonymousClass3(this));
            ArrayList<CheckBoxHolder> r72 = this.checkBoxes;
            int r23 = r72.size();
        L18:
            if (r5 >= r23) goto L20;
            CheckBoxHolder r32 = r72.get(r5);
            r5 = r5 + 1;
            CheckBoxHolder r33 = r32;
            r33.checkBox.animate().alpha(1.0f).start();
            r33.checkBox.enabled = true;
            goto L18
        L20:
            r85.start();
            return;
        L26:
            return;
        }

        public abstract void loadData(ChartViewData r1);

        public abstract void onZoomed();

        public void recolor() {
            this.chartView.updateColors();
            this.chartView.invalidate();
            this.zoomedChartView.updateColors();
            this.zoomedChartView.invalidate();
            this.chartHeaderView.recolor();
            this.chartHeaderView.invalidate();
            ChartViewData r0 = this.data;
            if (r0 == null) goto L26;
            ChartData r02 = r0.chartData;
            if (r02 == null) goto L26;
            ArrayList<ChartData.Line> r03 = r02.lines;
            if (r03 == null) goto L26;
            if (r03.size() <= 1) goto L26;
            int r04 = 0;
        L12:
            if (r04 >= this.data.chartData.lines.size()) goto L26;
            if (this.data.chartData.lines.get(r04).colorKey >= 0) goto L16;
        L18:
            double r1 = ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite));
            ChartViewData r2 = this.data;
            if (r1 >= 0.5d) goto L21;
            int r12 = r2.chartData.lines.get(r04).colorDark;
        L23:
            if (r04 >= this.checkBoxes.size()) goto L25;
            this.checkBoxes.get(r04).recolor(r12);
        L25:
            r04 = r04 + 1;
            goto L12
        L21:
            r12 = r2.chartData.lines.get(r04).color;
            goto L23
        L16:
            if (Theme.hasThemeKey(this.data.chartData.lines.get(r04).colorKey) == false) goto L18;
            r12 = Theme.getColor(this.data.chartData.lines.get(r04).colorKey);
        L26:
            this.progressView.setProgressColor(Theme.getColor(Theme.key_progressCircle));
            this.errorTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray4));
        }

        public void updateData(ChartViewData r11, boolean r12) {
            if (r11 == null) goto L56;
            this.chartHeaderView.setTitle(r11.title);
            if (getContext().getResources().getConfiguration().orientation != 2) goto L7;
            boolean r0 = true;
        L8:
            this.chartView.setLandscape(r0);
            r11.viewShowed = true;
            this.zoomedChartView.setLandscape(r0);
            this.data = r11;
            if (r11.isEmpty == false) goto L11;
        L46:
            this.progressView.setVisibility(8);
            String r112 = r11.errorMessage;
            if (r112 == null) goto L52;
            this.errorTextView.setText(r112);
            if (this.errorTextView.getVisibility() != 8) goto L51;
            this.errorTextView.setAlpha(0.0f);
            this.errorTextView.animate().alpha(1.0f);
        L51:
            this.errorTextView.setVisibility(0);
        L52:
            this.checkboxContainer.removeAllViews();
            this.checkBoxes.clear();
            this.chartView.setData(null);
            return;
        L11:
            if (r11.isError == true) goto L46;
            this.errorTextView.setVisibility(8);
            LegendSignatureView r02 = this.chartView.legendSignatureView;
            boolean r8 = r11.useHourFormat;
            r02.isTopHourChart = r8;
            this.chartHeaderView.showDate(!r8);
            if (r11.chartData == null) goto L16;
        L19:
            if (r12 == true) goto L22;
            this.progressView.setVisibility(8);
        L22:
            if (this.chartView.setData(r11.chartData) == true) goto L24;
        L26:
            this.chartHeaderView.setUseWeekInterval(r11.useWeekFormat);
            this.chartView.legendSignatureView.setUseWeek(r11.useWeekFormat);
            LegendSignatureView r113 = this.chartView.legendSignatureView;
            if (this.data.zoomToken == null) goto L29;
        L32:
            boolean r03 = true;
        L33:
            r113.zoomEnabled = r03;
            this.zoomedChartView.legendSignatureView.zoomEnabled = false;
            r113.setEnabled(r113.zoomEnabled);
            LegendSignatureView r114 = this.zoomedChartView.legendSignatureView;
            r114.setEnabled(r114.zoomEnabled);
            int r115 = this.chartView.lines.size();
            this.checkboxContainer.removeAllViews();
            this.checkBoxes.clear();
            if (r115 <= 1) goto L38;
            int r04 = 0;
        L36:
            if (r04 >= r115) goto L38;
            new CheckBoxHolder(this, r04).setData((LineViewData) this.chartView.lines.get(r04));
            r04 = r04 + 1;
        L38:
            long r6 = this.data.activeZoom;
            if (r6 <= 0) goto L41;
            this.chartView.selectDate(r6);
            zoomChart(true);
        L42:
            recolor();
            if (r12 == false) goto L55;
            this.chartView.transitionMode = 3;
            ValueAnimator r116 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.chartView.transitionParams = new TransitionParams();
            this.chartView.transitionParams.progress = 0.0f;
            r116.addUpdateListener(new StatisticActivity$BaseChartCell$$ExternalSyntheticLambda4(this));
            r116.addListener(new AnonymousClass4(this));
            r116.start();
            return;
        L55:
            return;
        L41:
            zoomOut(false);
            this.chartView.invalidate();
            goto L42
        L29:
            if (this.chartType == 4) goto L32;
            r03 = false;
            goto L33
        L24:
            if (r11.showAll == false) goto L26;
            this.chartView.pickerDelegate.set(0.0f, 1.0f);
            goto L26
        L16:
            if (r11.token == null) goto L19;
            this.progressView.setAlpha(1.0f);
            this.progressView.setVisibility(0);
            loadData(r11);
            this.chartView.setData(null);
            return;
        L7:
            r0 = false;
            goto L8
        }

        public abstract void zoomCanceled();

        public void zoomChart(boolean r13) {
            long r0 = this.chartView.getSelectedDate();
            ChartData r2 = this.data.childChartData;
            if (r13 == true) goto L5;
        L6:
            this.zoomedChartView.updatePicker(r2, r0);
        L7:
            this.zoomedChartView.setData(r2);
            int r6 = 0;
            if (this.data.chartData.lines.size() <= 1) goto L35;
            int r3 = 0;
            int r7 = 0;
        L11:
            if (r3 >= this.data.chartData.lines.size()) goto L30;
            int r8 = 0;
        L14:
            if (r8 >= r2.lines.size()) goto L26;
            if (r2.lines.get(r8).id.equals(this.data.chartData.lines.get(r3).id) == true) goto L17;
            r8 = r8 + 1;
            goto L14
        L17:
            boolean r10 = this.checkBoxes.get(r3).checkBox.checked;
            ((LineViewData) this.zoomedChartView.lines.get(r8)).enabled = r10;
            LineViewData r82 = (LineViewData) this.zoomedChartView.lines.get(r8);
            if (r10 == false) goto L20;
            float r11 = 1.0f;
        L21:
            r82.alpha = r11;
            this.checkBoxes.get(r3).checkBox.enabled = true;
            this.checkBoxes.get(r3).checkBox.animate().alpha(1.0f).start();
            if (r10 == false) goto L24;
            r7 = r7 + 1;
        L24:
            boolean r83 = true;
        L27:
            if (r83 == true) goto L29;
            this.checkBoxes.get(r3).checkBox.enabled = false;
            this.checkBoxes.get(r3).checkBox.animate().alpha(0.0f).start();
        L29:
            r3 = r3 + 1;
            goto L11
        L20:
            r11 = 0.0f;
            goto L21
        L26:
            r83 = false;
            goto L27
        L30:
            if (r7 != 0) goto L35;
        L32:
            if (r6 >= this.data.chartData.lines.size()) goto L34;
            this.checkBoxes.get(r6).checkBox.enabled = true;
            this.checkBoxes.get(r6).checkBox.animate().alpha(1.0f).start();
            r6 = r6 + 1;
            goto L32
        L34:
            return;
        L35:
            this.data.activeZoom = r0;
            this.chartView.legendSignatureView.setAlpha(0.0f);
            BaseChartView r22 = this.chartView;
            r22.selectionA = 0.0f;
            r22.legendShowing = false;
            r22.animateLegentTo = false;
            this.zoomedChartView.updateColors();
            if (r13 == true) goto L38;
            this.zoomedChartView.clearSelection();
            this.chartHeaderView.zoomTo(this.zoomedChartView, r0, true);
        L38:
            this.zoomedChartView.setHeader(this.chartHeaderView);
            this.chartView.setHeader(null);
            if (r13 == false) goto L42;
            this.chartView.setVisibility(4);
            this.zoomedChartView.setVisibility(0);
            BaseChartView r132 = this.chartView;
            r132.transitionMode = 0;
            BaseChartView r23 = this.zoomedChartView;
            r23.transitionMode = 0;
            r132.enabled = false;
            r23.enabled = true;
            this.chartHeaderView.zoomTo(r23, r0, false);
            return;
        L42:
            ValueAnimator r133 = createTransitionAnimator(r0, true);
            r133.addListener(new AnonymousClass2(this));
            r133.start();
            return;
        L5:
            if (this.zoomedChartView.getVisibility() == 0) goto L7;
            goto L6
        }
    }

    public class ChartCell extends BaseChartCell {
        private final int currentAccount;
        final /* synthetic */ StatisticActivity this$0;

        public static /* synthetic */ void $r8$lambda$BENT6UP3jyKGcCzUpqnD82nSX4k(ChartCell r0, String r1, ZoomCancelable r2, TLObject r3, TLRPC.TL_error r4) {
            r0.lambda$onZoomed$1(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$m6AONxUc0mzE7GpoSJMg41gkGWw(ChartCell r0, ChartData r1, String r2, ZoomCancelable r3) {
            r0.lambda$onZoomed$0(r1, r2, r3);
        }

        public ChartCell(StatisticActivity r1, Context r2, int r3, int r4, BaseChartView.SharedUiComponents r5) {
            this.this$0 = r1;
            super(r2, r4, r5);
            this.currentAccount = r3;
        }

        private /* synthetic */ void lambda$onZoomed$0(ChartData r2, String r3, ZoomCancelable r4) {
            if (r2 == null) goto L4;
            StatisticActivity.m19234$$Nest$fgetchildDataCache(this.this$0).put(r3, r2);
        L4:
            if (r2 != null) goto L6;
        L12:
            StatisticActivity.m19279$$Nest$mcancelZoom(this.this$0);
            return;
        L6:
            if (r4.canceled == true) goto L12;
            if (r4.adapterPosition < 0) goto L12;
            View r32 = StatisticActivity.m19244$$Nest$fgetlayoutManager(this.this$0).findViewByPosition(r4.adapterPosition);
            if ((r32 instanceof ChartCell) == false) goto L12;
            this.data.childChartData = r2;
            ChartCell r33 = (ChartCell) r32;
            r33.chartView.legendSignatureView.showProgress(false, false);
            r33.zoomChart(false);
            goto L12
        }

        private /* synthetic */ void lambda$onZoomed$1(String r5, ZoomCancelable r6, TLObject r7, TLRPC.TL_error r8) {
            boolean r0 = true;
            ChartData r1 = null;
            if ((r7 instanceof TL_stats.TL_statsGraph) == false) goto L14;
            JSONObject r82 = new JSONObject(((TL_stats.TL_statsGraph) r7).json.data);     // Catch: JSONException -> L11
            ChartViewData r72 = this.data;     // Catch: JSONException -> L11
            int r2 = r72.graphType;     // Catch: JSONException -> L11
            if (r72 == StatisticActivity.m19243$$Nest$fgetlanguagesData(this.this$0)) goto L9;
            r0 = false;
        L9:
            r1 = StatisticActivity.createChartData(r82, r2, r0);     // Catch: JSONException -> L11
        L16:
            AndroidUtilities.runOnUIThread(new StatisticActivity$ChartCell$$ExternalSyntheticLambda1(this, r1, r5, r6));
            return;
        L11:
            e = move-exception;
            e.printStackTrace();
            goto L16
        L14:
            if ((r7 instanceof TL_stats.TL_statsGraphError) == false) goto L16;
            Toast.makeText(getContext(), ((TL_stats.TL_statsGraphError) r7).error, 1).show();
            goto L16
        }

        @Override // org.telegram.ui.StatisticActivity.BaseChartCell
        public void loadData(ChartViewData r5) {
            r5.load(this.currentAccount, StatisticActivity.access$200(this.this$0), StatisticActivity.m19233$$Nest$fgetchat(this.this$0).stats_dc, StatisticActivity.m19281$$Nest$mgetFindChartCell(this.this$0, this.data));
        }

        @Override // org.telegram.ui.StatisticActivity.BaseChartCell
        public void onZoomed() {
            if (this.data.activeZoom > 0) goto L24;
            performClick();
            BaseChartView r1 = this.chartView;
            if (r1.legendSignatureView.canGoZoom == false) goto L25;
            long r12 = r1.getSelectedDate();
            int r5 = this.chartType;
            ChartViewData r6 = this.data;
            if (r5 != 4) goto L13;
            r6.childChartData = new StackLinearChartData(r6.chartData, r12);
            zoomChart(false);
            return;
        L13:
            if (r6.zoomToken != null) goto L15;
            return;
        L15:
            StatisticActivity.m19279$$Nest$mcancelZoom(this.this$0);
            String r52 = this.data.zoomToken + "_" + r12;
            ChartData r62 = (ChartData) StatisticActivity.m19234$$Nest$fgetchildDataCache(this.this$0).get(r52);
            if (r62 == null) goto L19;
            this.data.childChartData = r62;
            zoomChart(false);
            return;
        L19:
            TL_stats.TL_loadAsyncGraph r9 = new TL_stats.TL_loadAsyncGraph();
            r9.token = this.data.zoomToken;
            if (r12 == 0) goto L22;
            r9.x = r12;
            r9.flags |= 1;
        L22:
            StatisticActivity r13 = this.this$0;
            ZoomCancelable r2 = new ZoomCancelable();
            StatisticActivity.m19277$$Nest$fputlastCancelable(r13, r2);
            r2.adapterPosition = StatisticActivity.m19263$$Nest$fgetrecyclerListView(this.this$0).getChildAdapterPosition(this);
            this.chartView.legendSignatureView.showProgress(true, false);
            ConnectionsManager.getInstance(this.currentAccount).bindRequestToGuid(ConnectionsManager.getInstance(this.currentAccount).sendRequest(r9, new StatisticActivity$ChartCell$$ExternalSyntheticLambda0(this, r52, r2), null, null, 0, StatisticActivity.m19233$$Nest$fgetchat(this.this$0).stats_dc, 1, true), StatisticActivity.access$100(this.this$0));
            return;
        L25:
            return;
        }

        @Override // org.telegram.ui.StatisticActivity.BaseChartCell
        public void zoomCanceled() {
            StatisticActivity.m19279$$Nest$mcancelZoom(this.this$0);
        }
    }

    /* loaded from: classes3.dex */
    public static class ChartViewData {
        public long activeZoom;
        public ChartData chartData;
        ChartData childChartData;
        public String errorMessage;
        final int graphType;
        public boolean isEmpty;
        public boolean isError;
        public boolean isLanguages;
        public boolean loading;
        public boolean showAll;
        final String title;
        String token;
        public boolean useHourFormat;
        public boolean useWeekFormat;
        public boolean viewShowed;
        String zoomToken;

        public static /* synthetic */ void $r8$lambda$YDzszG0LC1lduhtmcCJe6LsqDYE(ChartViewData r0, ChartData r1, String r2, Utilities.Callback0Return r3) {
            r0.lambda$load$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$drfzCkZoqNOROH7As0VXuIv64Wc(ChartViewData r0, Utilities.Callback0Return r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$load$1(r1, r2, r3);
        }

        public ChartViewData(String r1, int r2) {
            this.title = r1;
            this.graphType = r2;
        }

        private /* synthetic */ void lambda$load$0(ChartData r2, String r3, Utilities.Callback0Return r4) {
            this.loading = false;
            this.chartData = r2;
            this.zoomToken = r3;
            BaseChartCell r22 = (BaseChartCell) r4.run();
            if (r22 == null) goto L6;
            r22.updateData(this, true);
            return;
        }

        private /* synthetic */ void lambda$load$1(Utilities.Callback0Return r7, TLObject r8, TLRPC.TL_error r9) {
            ChartData r0 = null;
            String r02 = null;
            r0 = null;
            r0 = null;
            if (r9 == null) goto L5;
            String r92 = null;
        L27:
            AndroidUtilities.runOnUIThread(new StatisticActivity$ChartViewData$$ExternalSyntheticLambda1(this, r0, r92, r7));
            return;
        L5:
            if ((r8 instanceof TL_stats.TL_statsGraph) == true) goto L29;
            r92 = null;
        L24:
            if ((r8 instanceof TL_stats.TL_statsGraphError) == false) goto L27;
            this.isEmpty = false;
            this.isError = true;
            this.errorMessage = ((TL_stats.TL_statsGraphError) r8).error;
            goto L27
        L29:
            ChartData r93 = StatisticActivity.createChartData(new JSONObject(((TL_stats.TL_statsGraph) r8).json.data), this.graphType, this.isLanguages);     // Catch: JSONException -> L19
            r02 = ((TL_stats.TL_statsGraph) r8).zoom_token;     // Catch: JSONException -> L16
            if (this.graphType != 4) goto L18;
            long[] r2 = r93.x;     // Catch: JSONException -> L16
            if (r2 == null) goto L18;
            if (r2.length <= 0) goto L18;
            long r3 = r2[r2.length - 1];     // Catch: JSONException -> L16
            this.childChartData = new StackLinearChartData(r93, r3);     // Catch: JSONException -> L16
            this.activeZoom = r3;     // Catch: JSONException -> L16
        L18:
            r0 = r93;
            r92 = r02;
        L16:
            e = e;
            String r5 = r02;
            r0 = r93;
            r92 = r5;
        L21:
            e.printStackTrace();
        L19:
            e = e;
            r92 = null;
            goto L21
        }

        public void load(int r11, int r12, int r13, Utilities.Callback0Return<BaseChartCell> r14) {
            if (this.loading == true) goto L6;
            this.loading = true;
            TL_stats.TL_loadAsyncGraph r2 = new TL_stats.TL_loadAsyncGraph();
            r2.token = this.token;
            int r10 = ConnectionsManager.getInstance(r11).sendRequest(r2, new StatisticActivity$ChartViewData$$ExternalSyntheticLambda0(this, r14), null, null, 0, r13, 1, true);
            ConnectionsManager.getInstance(r11).bindRequestToGuid(r10, r12);
            return;
        }
    }

    public static class DiffUtilsCallback extends DiffUtil.Callback {
        int actionsCell;
        private final Adapter adapter;
        int count;
        int endPosts;
        int folowersCell;
        int groupMembersCell;
        int growCell;
        int interactionsCell;
        int ivInteractionsCell;
        int languagesCell;
        private final LinearLayoutManager layoutManager;
        int membersLanguageCell;
        int messagesCell;
        int newFollowersBySourceCell;
        int newMembersBySourceCell;
        int notificationsCell;
        SparseIntArray positionToTypeMap;
        int reactionsByEmotionCell;
        int startPosts;
        int storyInteractionsCell;
        int storyReactionsByEmotionCell;
        int topDayOfWeeksCell;
        int topHourseCell;
        int viewsBySourceCell;

        private DiffUtilsCallback(Adapter r2, LinearLayoutManager r3) {
            this.positionToTypeMap = new SparseIntArray();
            this.growCell = -1;
            this.folowersCell = -1;
            this.interactionsCell = -1;
            this.ivInteractionsCell = -1;
            this.viewsBySourceCell = -1;
            this.newFollowersBySourceCell = -1;
            this.languagesCell = -1;
            this.topHourseCell = -1;
            this.notificationsCell = -1;
            this.reactionsByEmotionCell = -1;
            this.storyInteractionsCell = -1;
            this.storyReactionsByEmotionCell = -1;
            this.groupMembersCell = -1;
            this.newMembersBySourceCell = -1;
            this.membersLanguageCell = -1;
            this.messagesCell = -1;
            this.actionsCell = -1;
            this.topDayOfWeeksCell = -1;
            this.startPosts = -1;
            this.endPosts = -1;
            this.adapter = r2;
            this.layoutManager = r3;
        }

        public /* synthetic */ DiffUtilsCallback(Adapter r1, LinearLayoutManager r2, StatisticActivityIA r3) {
            this(r1, r2);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r2, int r3) {
            if (this.positionToTypeMap.get(r2) != this.adapter.getItemViewType(r3)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r5, int r6) {
            if (this.positionToTypeMap.get(r5) != 13) goto L8;
            if (this.adapter.getItemViewType(r6) != 13) goto L8;
            return true;
        L8:
            if (this.positionToTypeMap.get(r5) == 10) goto L10;
        L12:
            int r0 = this.startPosts;
            if (r5 < r0) goto L21;
            if (r5 > this.endPosts) goto L21;
            if ((r5 - r0) != (r6 - this.adapter.recentPostsStartRow)) goto L19;
            return true;
        L19:
            return false;
        L21:
            if (r5 != this.growCell) goto L26;
            if (r6 != this.adapter.growCell) goto L26;
            return true;
        L26:
            if (r5 != this.folowersCell) goto L31;
            if (r6 != this.adapter.folowersCell) goto L31;
            return true;
        L31:
            if (r5 != this.interactionsCell) goto L36;
            if (r6 != this.adapter.interactionsCell) goto L36;
            return true;
        L36:
            if (r5 != this.ivInteractionsCell) goto L41;
            if (r6 != this.adapter.ivInteractionsCell) goto L41;
            return true;
        L41:
            if (r5 != this.viewsBySourceCell) goto L46;
            if (r6 != this.adapter.viewsBySourceCell) goto L46;
            return true;
        L46:
            if (r5 != this.newFollowersBySourceCell) goto L51;
            if (r6 != this.adapter.newFollowersBySourceCell) goto L51;
            return true;
        L51:
            if (r5 != this.languagesCell) goto L56;
            if (r6 != this.adapter.languagesCell) goto L56;
            return true;
        L56:
            if (r5 != this.topHourseCell) goto L61;
            if (r6 != this.adapter.topHourseCell) goto L61;
            return true;
        L61:
            if (r5 != this.notificationsCell) goto L66;
            if (r6 != this.adapter.notificationsCell) goto L66;
            return true;
        L66:
            if (r5 != this.groupMembersCell) goto L71;
            if (r6 != this.adapter.groupMembersCell) goto L71;
            return true;
        L71:
            if (r5 != this.newMembersBySourceCell) goto L76;
            if (r6 != this.adapter.newMembersBySourceCell) goto L76;
            return true;
        L76:
            if (r5 != this.membersLanguageCell) goto L81;
            if (r6 != this.adapter.membersLanguageCell) goto L81;
            return true;
        L81:
            if (r5 != this.messagesCell) goto L86;
            if (r6 != this.adapter.messagesCell) goto L86;
            return true;
        L86:
            if (r5 != this.actionsCell) goto L91;
            if (r6 != this.adapter.actionsCell) goto L91;
            return true;
        L91:
            if (r5 != this.topDayOfWeeksCell) goto L96;
            if (r6 != this.adapter.topDayOfWeeksCell) goto L96;
            return true;
        L96:
            if (r5 != this.reactionsByEmotionCell) goto L101;
            if (r6 != this.adapter.reactionsByEmotionCell) goto L101;
            return true;
        L101:
            if (r5 != this.storyInteractionsCell) goto L106;
            if (r6 != this.adapter.storyInteractionsCell) goto L106;
            return true;
        L106:
            if (r5 == this.storyReactionsByEmotionCell) goto L108;
        L110:
            return false;
        L108:
            if (r6 != this.adapter.storyReactionsByEmotionCell) goto L110;
            return true;
        L10:
            if (this.adapter.getItemViewType(r6) != 10) goto L12;
            return true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return this.adapter.count;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.count;
        }

        public void saveOldState() {
            this.positionToTypeMap.clear();
            this.count = this.adapter.getItemCount();
            int r0 = 0;
        L4:
            if (r0 >= this.count) goto L6;
            this.positionToTypeMap.put(r0, this.adapter.getItemViewType(r0));
            r0 = r0 + 1;
            goto L4
        L6:
            Adapter r02 = this.adapter;
            this.growCell = r02.growCell;
            this.folowersCell = r02.folowersCell;
            this.interactionsCell = r02.interactionsCell;
            this.ivInteractionsCell = r02.ivInteractionsCell;
            this.viewsBySourceCell = r02.viewsBySourceCell;
            this.newFollowersBySourceCell = r02.newFollowersBySourceCell;
            this.languagesCell = r02.languagesCell;
            this.topHourseCell = r02.topHourseCell;
            this.notificationsCell = r02.notificationsCell;
            this.startPosts = r02.recentPostsStartRow;
            this.endPosts = r02.recentPostsEndRow;
            this.reactionsByEmotionCell = r02.reactionsByEmotionCell;
            this.storyInteractionsCell = r02.storyInteractionsCell;
            this.storyReactionsByEmotionCell = r02.storyReactionsByEmotionCell;
            this.groupMembersCell = r02.groupMembersCell;
            this.newMembersBySourceCell = r02.newMembersBySourceCell;
            this.membersLanguageCell = r02.membersLanguageCell;
            this.messagesCell = r02.messagesCell;
            this.actionsCell = r02.actionsCell;
            this.topDayOfWeeksCell = r02.topDayOfWeeksCell;
        }

        public void update() {
            saveOldState();
            this.adapter.update();
            int r0 = this.layoutManager.findFirstVisibleItemPosition();
            int r1 = this.layoutManager.findLastVisibleItemPosition();
        L3:
            int r4 = 0;
            if (r0 > r1) goto L11;
            if (this.adapter.getItemId(r0) == (-1)) goto L10;
            View r5 = this.layoutManager.findViewByPosition(r0);
            if (r5 == null) goto L10;
            long r02 = this.adapter.getItemId(r0);
            int r52 = r5.getTop();
        L12:
            DiffUtil.calculateDiff(this).dispatchUpdatesTo(this.adapter);
            if (r02 != (-1)) goto L15;
            return;
        L15:
            if (r4 >= this.adapter.getItemCount()) goto L20;
            if (this.adapter.getItemId(r4) == r02) goto L21;
            r4 = r4 + 1;
        L21:
            if (r4 <= 0) goto L31;
            this.layoutManager.scrollToPositionWithOffset(r4, r52);
            return;
        L31:
            return;
        L20:
            r4 = -1;
        L10:
            r0 = r0 + 1;
            goto L3
        L11:
            r02 = -1;
            r52 = 0;
            goto L12
        }
    }

    public static class MemberData {
        public String description;
        public TLRPC.User user;
        long user_id;

        /* renamed from: org.telegram.ui.StatisticActivity$MemberData$1, reason: invalid class name */
        public class AnonymousClass1 extends ChatRightsEditActivity {
            final /* synthetic */ MemberData this$0;
            final /* synthetic */ StatisticActivity val$fragment;
            final /* synthetic */ boolean[] val$needShowBulletin;

            public AnonymousClass1(MemberData r14, long r15, long r17, TLRPC.TL_chatAdminRights r19, TLRPC.TL_chatBannedRights r20, TLRPC.TL_chatBannedRights r21, String r22, int r23, boolean r24, boolean r25, String r26, boolean[] r27, StatisticActivity r28) {
                this.this$0 = r14;
                this.val$needShowBulletin = r27;
                this.val$fragment = r28;
                super(r15, r17, r19, r20, r21, r22, r23, r24, r25, r26);
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public void onTransitionAnimationEnd(boolean r1, boolean r2) {
                if (r1 == true) goto L10;
                if (r2 == true) goto L5;
                return;
            L5:
                if (this.val$needShowBulletin[0] == true) goto L7;
                return;
            L7:
                if (BulletinFactory.canShowBulletin(this.val$fragment) == false) goto L13;
                BulletinFactory.createPromoteToAdminBulletin(this.val$fragment, this.this$0.user.first_name).show();
                return;
            L13:
                return;
            }
        }

        /* renamed from: org.telegram.ui.StatisticActivity$MemberData$2, reason: invalid class name */
        public class AnonymousClass2 implements ChatRightsEditActivity.ChatRightsEditActivityDelegate {
            final /* synthetic */ MemberData this$0;
            final /* synthetic */ TLRPC.TL_chatChannelParticipant val$finalCurrentParticipant;
            final /* synthetic */ boolean val$finalIsAdmin;
            final /* synthetic */ boolean[] val$needShowBulletin;

            public AnonymousClass2(MemberData r1, TLRPC.TL_chatChannelParticipant r2, boolean r3, boolean[] r4) {
                this.this$0 = r1;
                this.val$finalCurrentParticipant = r2;
                this.val$finalIsAdmin = r3;
                this.val$needShowBulletin = r4;
            }

            @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
            public void didChangeOwner(TLRPC.User r1) {
            }

            @Override // org.telegram.ui.ChatRightsEditActivity.ChatRightsEditActivityDelegate
            public void didSetRights(int r1, TLRPC.TL_chatAdminRights r2, TLRPC.TL_chatBannedRights r3, String r4) {
                TLRPC.TL_chatChannelParticipant r32 = this.val$finalCurrentParticipant;
                if (r1 != 0) goto L6;
                TLRPC.ChannelParticipant r0 = r32.channelParticipant;
                r0.admin_rights = null;
                r0.rank = _UrlKt.FRAGMENT_ENCODE_SET;
                return;
            L6:
                TLRPC.ChannelParticipant r12 = r32.channelParticipant;
                r12.admin_rights = r2;
                r12.rank = r4;
                if (this.val$finalIsAdmin == false) goto L10;
                this.val$needShowBulletin[0] = true;
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$5zhsaTZLAzn4wVFResRXdYRND20(MemberData r0, StatisticActivity r1, AlertDialog[] r2, TLRPC.TL_error r3, TLObject r4, TLRPC.ChatFull r5) {
            r0.lambda$onLongClick$0(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$QJa5xfs83-mCtVBaeC5Egg2AqP4, reason: not valid java name */
        public static /* synthetic */ void m19285$r8$lambda$QJa5xfs83mCtVBaeC5Egg2AqP4(MemberData r0, StatisticActivity r1, AlertDialog[] r2, TLRPC.TL_error r3, TLObject r4, TLRPC.ChatFull r5) {
            r0.lambda$onLongClick$2(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$_QnG5-I1j0WPrYc4E1CjeJHnQw8, reason: not valid java name */
        public static /* synthetic */ void m19286$r8$lambda$_QnG5I1j0WPrYc4E1CjeJHnQw8(MemberData r0, ArrayList r1, TLRPC.ChatFull r2, TLRPC.TL_chatChannelParticipant r3, boolean r4, StatisticActivity r5, DialogInterface r6, int r7) {
            r0.lambda$onLongClick$4(r1, r2, r3, r4, r5, r6, r7);
        }

        /* renamed from: $r8$lambda$gHKbiFjFbkfB5v-EmWz2NxtW3Cw, reason: not valid java name */
        public static /* synthetic */ void m19287$r8$lambda$gHKbiFjFbkfB5vEmWz2NxtW3Cw(MemberData r0, StatisticActivity r1, AlertDialog[] r2, TLRPC.ChatFull r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$onLongClick$1(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$x3zz0qvH9VUeDY3KVti0hIsd26Y(MemberData r0, StatisticActivity r1, AlertDialog[] r2, TLRPC.ChatFull r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$onLongClick$3(r1, r2, r3, r4, r5);
        }

        public MemberData() {
        }

        public static TLRPC.User find(long r5, ArrayList<TLRPC.User> r7) {
            int r0 = r7.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L7;
            TLRPC.User r2 = r7.get(r1);
            r1 = r1 + 1;
            TLRPC.User r22 = r2;
            if (r22.id != r5) goto L3;
            return r22;
        L7:
            return null;
        }

        public static MemberData from(TL_stats.TL_statsGroupTopAdmin r6, ArrayList<TLRPC.User> r7) {
            MemberData r0 = new MemberData();
            long r1 = r6.user_id;
            r0.user_id = r1;
            r0.user = find(r1, r7);
            StringBuilder r72 = new StringBuilder();
            int r12 = r6.deleted;
            if (r12 <= 0) goto L6;
            r72.append(LocaleController.formatPluralString("Deletions", r12, new Object[0]));
        L6:
            if (r6.banned <= 0) goto L12;
            if (r72.length() <= 0) goto L10;
            r72.append(", ");
        L10:
            r72.append(LocaleController.formatPluralString("Bans", r6.banned, new Object[0]));
        L12:
            if (r6.kicked > 0) goto L14;
        L17:
            r0.description = r72.toString();
            return r0;
        L14:
            if (r72.length() <= 0) goto L16;
            r72.append(", ");
        L16:
            r72.append(LocaleController.formatPluralString("Restrictions", r6.kicked, new Object[0]));
            goto L17
        }

        public static MemberData from(TL_stats.TL_statsGroupTopInviter r3, ArrayList<TLRPC.User> r4) {
            MemberData r0 = new MemberData();
            long r1 = r3.user_id;
            r0.user_id = r1;
            r0.user = find(r1, r4);
            int r32 = r3.invitations;
            if (r32 <= 0) goto L6;
            r0.description = LocaleController.formatPluralString("Invitations", r32, new Object[0]);
            return r0;
        L6:
            r0.description = _UrlKt.FRAGMENT_ENCODE_SET;
            return r0;
        }

        public static MemberData from(TL_stats.TL_statsGroupTopPoster r5, ArrayList<TLRPC.User> r6) {
            MemberData r0 = new MemberData();
            long r1 = r5.user_id;
            r0.user_id = r1;
            r0.user = find(r1, r6);
            StringBuilder r62 = new StringBuilder();
            int r12 = r5.messages;
            if (r12 <= 0) goto L6;
            r62.append(LocaleController.formatPluralString("messages", r12, new Object[0]));
        L6:
            if (r5.avg_chars > 0) goto L8;
        L11:
            r0.description = r62.toString();
            return r0;
        L8:
            if (r62.length() <= 0) goto L10;
            r62.append(", ");
        L10:
            r62.append(LocaleController.formatString("CharactersPerMessage", R.string.CharactersPerMessage, new Object[]{LocaleController.formatPluralString("Characters", r5.avg_chars, new Object[0])}));
            goto L11
        }

        private /* synthetic */ void lambda$onLongClick$0(StatisticActivity r4, AlertDialog[] r5, TLRPC.TL_error r6, TLObject r7, TLRPC.ChatFull r8) {
            if (StatisticActivity.access$400(r4) == false) goto L5;
            return;
        L5:
            if (r4.getFragmentView() != null) goto L8;
            return;
        L8:
            if (r5[0] == null) goto L17;
            if (r6 != null) goto L13;
            TLRPC.TL_chatChannelParticipant r62 = new TLRPC.TL_chatChannelParticipant();
            r62.channelParticipant = ((TLRPC.TL_channels_channelParticipant) r7).participant;
            r62.user_id = this.user.id;
            r8.participants.participants.add(0, r62);
            onLongClick(r8, r4, r5);
            return;
        L13:
            onLongClick(r8, r4, r5, false);
            return;
        }

        private /* synthetic */ void lambda$onLongClick$1(StatisticActivity r8, AlertDialog[] r9, TLRPC.ChatFull r10, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new StatisticActivity$MemberData$$ExternalSyntheticLambda3(this, r8, r9, r12, r11, r10));
        }

        private /* synthetic */ void lambda$onLongClick$2(StatisticActivity r4, AlertDialog[] r5, TLRPC.TL_error r6, TLObject r7, TLRPC.ChatFull r8) {
            if (StatisticActivity.access$300(r4) == false) goto L5;
            return;
        L5:
            if (r4.getFragmentView() != null) goto L8;
            return;
        L8:
            if (r5[0] == null) goto L17;
            if (r6 != null) goto L13;
            TLRPC.TL_chatChannelParticipant r62 = new TLRPC.TL_chatChannelParticipant();
            r62.channelParticipant = ((TLRPC.TL_channels_channelParticipant) r7).participant;
            r62.user_id = UserConfig.getInstance(UserConfig.selectedAccount).clientUserId;
            r8.participants.participants.add(0, r62);
            onLongClick(r8, r4, r5);
            return;
        L13:
            onLongClick(r8, r4, r5, false);
            return;
        }

        private /* synthetic */ void lambda$onLongClick$3(StatisticActivity r8, AlertDialog[] r9, TLRPC.ChatFull r10, TLObject r11, TLRPC.TL_error r12) {
            AndroidUtilities.runOnUIThread(new StatisticActivity$MemberData$$ExternalSyntheticLambda4(this, r8, r9, r12, r11, r10));
        }

        private /* synthetic */ void lambda$onLongClick$4(ArrayList r19, TLRPC.ChatFull r20, TLRPC.TL_chatChannelParticipant r21, boolean r22, StatisticActivity r23, DialogInterface r24, int r25) {
            if (((Integer) r19.get(r25)).intValue() != 0) goto L7;
            boolean[] r14 = new boolean[1];
            long r4 = this.user.id;
            long r42 = r20.id;
            TLRPC.ChannelParticipant r2 = r21.channelParticipant;
            AnonymousClass1 r0 = new AnonymousClass1(this, r4, r42, r2.admin_rights, null, r2.banned_rights, r2.rank, 0, true, r22, null, r14, r23);
            r0.setDelegate(new AnonymousClass2(this, r21, r22, r14));
            r23.presentFragment(r0);
            return;
        L7:
            if (((Integer) r19.get(r25)).intValue() != 2) goto L10;
            onClick(r23);
            return;
        L10:
            Bundle r02 = new Bundle();
            r02.putLong("chat_id", r20.id);
            r02.putLong("search_from_user_id", this.user.id);
            r23.presentFragment(new ChatActivity(r02));
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void onLongClick(TLRPC.ChatFull r20, StatisticActivity r21, AlertDialog[] r22, boolean r23) {
            int r5 = 0;
            MessagesController.getInstance(UserConfig.selectedAccount).putUser(this.user, false);
            ArrayList r2 = new ArrayList();
            ArrayList r4 = new ArrayList();
            ArrayList r7 = new ArrayList();
            if (r23 == false) goto L20;
            ArrayList<TLRPC.ChatParticipant> r9 = r20.participants.participants;
            if (r9 == null) goto L20;
            int r92 = r9.size();
            int r10 = 0;
            TLRPC.TL_chatChannelParticipant r11 = null;
            TLRPC.TL_chatChannelParticipant r12 = null;
        L7:
            if (r10 >= r92) goto L19;
            TLRPC.ChatParticipant r13 = r20.participants.participants.get(r10);
            long r14 = r13.user_id;
            int r16 = r5;
            int r18 = r92;
            if (r14 != this.user.id) goto L14;
            if ((r13 instanceof TLRPC.TL_chatChannelParticipant) == false) goto L14;
            r11 = (TLRPC.TL_chatChannelParticipant) r13;
        L14:
            if (r14 != UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) goto L18;
            if ((r13 instanceof TLRPC.TL_chatChannelParticipant) == false) goto L18;
            r12 = (TLRPC.TL_chatChannelParticipant) r13;
        L18:
            r10 = r10 + 1;
            r5 = r16;
            r92 = r18;
            goto L7
        L19:
            int r162 = r5;
            AlertDialog r17 = null;
        L21:
            r2.add(LocaleController.getString("StatisticOpenProfile", R.string.StatisticOpenProfile));
            r7.add(Integer.valueOf(R.drawable.msg_openprofile));
            r4.add(2);
            r2.add(LocaleController.getString("StatisticSearchUserHistory", R.string.StatisticSearchUserHistory));
            r7.add(Integer.valueOf(R.drawable.msg_msgbubble3));
            int r52 = 1;
            r4.add(1);
            if (r23 == false) goto L29;
            if (r11 != null) goto L29;
            if (r22[r162] != null) goto L27;
            AlertDialog r24 = new AlertDialog(r21.getFragmentView().getContext(), 3);
            r22[r162] = r24;
            r24.showDelayed(300);
        L27:
            TLRPC.TL_channels_getParticipant r25 = new TLRPC.TL_channels_getParticipant();
            r25.channel = MessagesController.getInstance(UserConfig.selectedAccount).getInputChannel(r20.id);
            r25.participant = MessagesController.getInputPeer(this.user);
            ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r25, new StatisticActivity$MemberData$$ExternalSyntheticLambda0(this, r21, r22, r20));
            return;
        L29:
            if (r23 == false) goto L36;
            if (r12 != null) goto L36;
            if (r22[r162] != null) goto L34;
            AlertDialog r26 = new AlertDialog(r21.getFragmentView().getContext(), 3);
            r22[r162] = r26;
            r26.showDelayed(300);
        L34:
            TLRPC.TL_channels_getParticipant r27 = new TLRPC.TL_channels_getParticipant();
            r27.channel = MessagesController.getInstance(UserConfig.selectedAccount).getInputChannel(r20.id);
            r27.participant = MessagesController.getInstance(UserConfig.selectedAccount).getInputPeer(UserConfig.getInstance(UserConfig.selectedAccount).clientUserId);
            ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r27, new StatisticActivity$MemberData$$ExternalSyntheticLambda1(this, r21, r22, r20));
            return;
        L36:
            AlertDialog r8 = r22[r162];
            if (r8 == null) goto L39;
            r8.dismiss();
            r22[r162] = r17;
        L39:
            if (r12 == null) goto L71;
            if (r11 == null) goto L71;
            if (r12.user_id == r11.user_id) goto L71;
            TLRPC.ChannelParticipant r0 = r11.channelParticipant;
            TLRPC.TL_chatAdminRights r82 = r12.channelParticipant.admin_rights;
            if (r82 != null) goto L46;
        L48:
            int r83 = r162;
        L49:
            if (r83 != 0) goto L51;
        L57:
            if (r83 == 0) goto L71;
            if (r0.admin_rights == null) goto L62;
            r52 = r162;
        L62:
            if (r52 == 0) goto L65;
            String r02 = "SetAsAdmin";
            int r84 = R.string.SetAsAdmin;
        L66:
            r2.add(LocaleController.getString(r02, r84));
            if (r52 == 0) goto L69;
            int r03 = R.drawable.msg_admins;
        L70:
            r7.add(Integer.valueOf(r03));
            r4.add(Integer.valueOf(r162));
            boolean r53 = r52;
        L72:
            AlertDialog.Builder r85 = new AlertDialog.Builder(r21.getParentActivity());
            r85.setItems((CharSequence[]) r2.toArray(new CharSequence[r4.size()]), AndroidUtilities.toIntArray(r7), new StatisticActivity$MemberData$$ExternalSyntheticLambda2(this, r4, r20, r11, r53, r21));
            r21.showDialog(r85.create());
            return;
        L69:
            r03 = R.drawable.msg_permissions;
            goto L70
        L65:
            r02 = "EditAdminRights";
            r84 = R.string.EditAdminRights;
            goto L66
        L51:
            if ((r0 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L53;
        L56:
            r83 = r162;
            goto L57
        L53:
            if ((r0 instanceof TLRPC.TL_channelParticipantAdmin) == false) goto L57;
            if (r0.can_edit == true) goto L57;
        L46:
            if (r82.add_admins == false) goto L48;
            r83 = 1;
        L71:
            r53 = r162;
        L20:
            r162 = 0;
            r17 = null;
            r11 = null;
            r12 = null;
            goto L21
        }

        public void onClick(BaseFragment r5) {
            Bundle r0 = new Bundle();
            r0.putLong("user_id", this.user.id);
            MessagesController.getInstance(UserConfig.selectedAccount).putUser(this.user, false);
            r5.presentFragment(new ProfileActivity(r0));
        }

        public void onLongClick(TLRPC.ChatFull r2, StatisticActivity r3, AlertDialog[] r4) {
            onLongClick(r2, r3, r4, true);
        }
    }

    public static class OverviewCell extends LinearLayout {
        TextView[] primary;
        TextView[] secondary;
        TextView[] title;

        /* renamed from: -$$Nest$mupdateColors, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19288$$Nest$mupdateColors(OverviewCell r0) {
            r0.updateColors();
        }

        public OverviewCell(Context r2) {
            this(r2, 2);
        }

        public OverviewCell(Context r13, int r14) {
            super(r13);
            int r0 = r14 * 2;
            this.primary = new TextView[r0];
            this.secondary = new TextView[r0];
            this.title = new TextView[r0];
            setOrientation(1);
            setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
            int r1 = 0;
        L3:
            if (r1 >= r14) goto L9;
            LinearLayout r2 = new LinearLayout(r13);
            r2.setOrientation(0);
            int r4 = 0;
        L6:
            if (r4 >= 2) goto L8;
            LinearLayout r5 = new LinearLayout(r13);
            r5.setOrientation(1);
            LinearLayout r6 = new LinearLayout(r13);
            r6.setOrientation(0);
            int r8 = (r1 * 2) + r4;
            this.primary[r8] = new TextView(r13);
            this.secondary[r8] = new TextView(r13);
            this.title[r8] = new TextView(r13);
            this.primary[r8].setTypeface(AndroidUtilities.bold());
            this.primary[r8].setTextSize(1, 17.0f);
            this.title[r8].setTextSize(1, 13.0f);
            this.title[r8].setGravity(3);
            this.secondary[r8].setTextSize(1, 13.0f);
            this.secondary[r8].setPadding(AndroidUtilities.dp(4.0f), 0, 0, 0);
            r6.addView(this.primary[r8]);
            r6.addView(this.secondary[r8]);
            r5.addView(r6);
            r5.addView(this.title[r8]);
            r2.addView(r5, LayoutHelper.createLinear(-1, -2, 1.0f));
            r4 = r4 + 1;
            goto L6
        L8:
            addView(r2, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 0.0f, 0.0f, 16.0f));
            r1 = r1 + 1;
            goto L3
        }

        private void updateColors() {
            int r0 = 0;
        L3:
            TextView[] r1 = this.primary;
            if (r0 >= r1.length) goto L10;
            TextView r12 = r1[r0];
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r12.setTextColor(Theme.getColor(r2));
            this.title[r0].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
            Integer r13 = (Integer) this.secondary[r0].getTag();
            TextView[] r3 = this.secondary;
            if (r13 == null) goto L8;
            r3[r0].setTextColor(Theme.getColor(r13.intValue()));
        L9:
            r0 = r0 + 1;
            goto L3
        L8:
            r3[r0].setTextColor(Theme.getColor(r2));
            goto L9
        }

        public void setData(int r2, String r3, String r4, String r5) {
            this.primary[r2].setText(r3);
            this.secondary[r2].setText(r4);
            this.secondary[r2].setTag(Integer.valueOf(Theme.key_windowBackgroundWhiteGrayText2));
            this.title[r2].setText(r5);
            updateColors();
        }

        public void setData(OverviewChannelData r5, TLRPC.ChatFull r6) {
            int r0 = 0;
            int r1 = 0;
        L3:
            TextView[] r2 = this.primary;
            if (r0 >= r2.length) goto L53;
            switch(r0) {
                case 0: goto L46;
                case 1: goto L45;
                case 2: goto L40;
                case 3: goto L33;
                case 4: goto L28;
                case 5: goto L21;
                case 6: goto L14;
                case 7: goto L7;
                default: goto L51;
            };
        L7:
            r2[r1].setText(r5.reactionsPerStoryPrimary);
            this.secondary[r1].setText(r5.reactionsPerStorySecondary);
            TextView r22 = this.secondary[r1];
            if (r5.reactionsPerStoryUp == false) goto L10;
            int r3 = Theme.key_windowBackgroundWhiteGreenText2;
        L11:
            r22.setTag(Integer.valueOf(r3));
            this.title[r1].setText(r5.reactionsPerStoryTitle);
            if (r5.reactionsPerStoryVisible == false) goto L51;
        L13:
            r1 = r1 + 1;
            goto L51
        L10:
            r3 = Theme.key_text_RedRegular;
            goto L11
        L14:
            r2[r1].setText(r5.reactionsPerPostPrimary);
            this.secondary[r1].setText(r5.reactionsPerPostSecondary);
            TextView r23 = this.secondary[r1];
            if (r5.reactionsPerPostUp == false) goto L17;
            int r32 = Theme.key_windowBackgroundWhiteGreenText2;
        L18:
            r23.setTag(Integer.valueOf(r32));
            this.title[r1].setText(r5.reactionsPerPostTitle);
            if (r5.reactionsPerPostVisible == false) goto L51;
        L17:
            r32 = Theme.key_text_RedRegular;
            goto L18
        L21:
            r2[r1].setText(r5.sharesPerStoryPrimary);
            this.secondary[r1].setText(r5.sharesPerStorySecondary);
            TextView r24 = this.secondary[r1];
            if (r5.sharesPerStoryUp == false) goto L24;
            int r33 = Theme.key_windowBackgroundWhiteGreenText2;
        L25:
            r24.setTag(Integer.valueOf(r33));
            this.title[r1].setText(r5.sharesPerStoryTitle);
            if (r5.sharesPerStoryVisible == false) goto L51;
        L24:
            r33 = Theme.key_text_RedRegular;
            goto L25
        L28:
            r2[r1].setText(r5.sharesPrimary);
            this.secondary[r1].setText(r5.sharesSecondary);
            TextView r25 = this.secondary[r1];
            if (r5.sharesUp == false) goto L31;
            int r34 = Theme.key_windowBackgroundWhiteGreenText2;
        L32:
            r25.setTag(Integer.valueOf(r34));
            this.title[r1].setText(r5.sharesTitle);
            goto L13
        L31:
            r34 = Theme.key_text_RedRegular;
            goto L32
        L33:
            r2[r1].setText(r5.viewsPerStoryPrimary);
            this.secondary[r1].setText(r5.viewsPerStorySecondary);
            TextView r26 = this.secondary[r1];
            if (r5.viewsPerStoryUp == false) goto L36;
            int r35 = Theme.key_windowBackgroundWhiteGreenText2;
        L37:
            r26.setTag(Integer.valueOf(r35));
            this.title[r1].setText(r5.viewsPerStoryTitle);
            if (r5.viewsPerStoryVisible == false) goto L51;
        L36:
            r35 = Theme.key_text_RedRegular;
            goto L37
        L40:
            r2[r1].setText(r5.viewsPrimary);
            this.secondary[r1].setText(r5.viewsSecondary);
            TextView r27 = this.secondary[r1];
            if (r5.viewsUp == false) goto L43;
            int r36 = Theme.key_windowBackgroundWhiteGreenText2;
        L44:
            r27.setTag(Integer.valueOf(r36));
            this.title[r1].setText(r5.viewsTitle);
            goto L13
        L43:
            r36 = Theme.key_text_RedRegular;
            goto L44
        L45:
            r2[r1].setText(r5.notificationsPrimary);
            this.secondary[r1].setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.title[r1].setText(r5.notificationsTitle);
            goto L13
        L46:
            r2[r1].setText(r5.followersPrimary);
            this.secondary[r1].setText(r5.followersSecondary);
            TextView r28 = this.secondary[r1];
            if (r5.followersUp == false) goto L49;
            int r37 = Theme.key_windowBackgroundWhiteGreenText2;
        L50:
            r28.setTag(Integer.valueOf(r37));
            this.title[r1].setText(r5.followersTitle);
            goto L13
        L49:
            r37 = Theme.key_text_RedRegular;
        L51:
            r0 = r0 + 1;
        L53:
            if (r1 >= this.primary.length) goto L55;
            ((ViewGroup) this.title[r1].getParent()).setVisibility(8);
            r1 = r1 + 1;
            goto L53
        L55:
            int r52 = 0;
        L57:
            if (r52 >= getChildCount()) goto L64;
            ViewGroup r12 = (ViewGroup) getChildAt(r52);
            if (r12.getChildAt(0).getVisibility() != 8) goto L63;
            if (r12.getChildAt(1).getVisibility() != 8) goto L63;
            r12.setVisibility(8);
        L63:
            r52 = r52 + 1;
            goto L57
        L64:
            updateColors();
        }

        public void setData(OverviewChatData r7) {
            this.primary[0].setText(r7.membersPrimary);
            this.primary[1].setText(r7.messagesPrimary);
            this.primary[2].setText(r7.viewingMembersPrimary);
            this.primary[3].setText(r7.postingMembersPrimary);
            this.secondary[0].setText(r7.membersSecondary);
            TextView r0 = this.secondary[0];
            if (r7.membersUp == false) goto L5;
            int r5 = Theme.key_windowBackgroundWhiteGreenText2;
        L6:
            r0.setTag(Integer.valueOf(r5));
            this.secondary[1].setText(r7.messagesSecondary);
            TextView r02 = this.secondary[1];
            if (r7.messagesUp == false) goto L9;
            int r52 = Theme.key_windowBackgroundWhiteGreenText2;
        L10:
            r02.setTag(Integer.valueOf(r52));
            this.secondary[2].setText(r7.viewingMembersSecondary);
            TextView r03 = this.secondary[2];
            if (r7.viewingMembersUp == false) goto L13;
            int r53 = Theme.key_windowBackgroundWhiteGreenText2;
        L14:
            r03.setTag(Integer.valueOf(r53));
            this.secondary[3].setText(r7.postingMembersSecondary);
            TextView r04 = this.secondary[3];
            if (r7.postingMembersUp == false) goto L17;
            int r54 = Theme.key_windowBackgroundWhiteGreenText2;
        L18:
            r04.setTag(Integer.valueOf(r54));
            this.title[0].setText(r7.membersTitle);
            this.title[1].setText(r7.messagesTitle);
            this.title[2].setText(r7.viewingMembersTitle);
            this.title[3].setText(r7.postingMembersTitle);
            updateColors();
            return;
        L17:
            r54 = Theme.key_text_RedRegular;
            goto L18
        L13:
            r53 = Theme.key_text_RedRegular;
            goto L14
        L9:
            r52 = Theme.key_text_RedRegular;
            goto L10
        L5:
            r5 = Theme.key_text_RedRegular;
            goto L6
        }
    }

    public static class OverviewChannelData {
        String followersPrimary;
        String followersSecondary;
        String followersTitle;
        boolean followersUp;
        String notificationsPrimary;
        String notificationsTitle;
        String reactionsPerPostPrimary;
        String reactionsPerPostSecondary;
        String reactionsPerPostTitle;
        boolean reactionsPerPostUp;
        boolean reactionsPerPostVisible;
        String reactionsPerStoryPrimary;
        String reactionsPerStorySecondary;
        String reactionsPerStoryTitle;
        boolean reactionsPerStoryUp;
        boolean reactionsPerStoryVisible;
        String sharesPerStoryPrimary;
        String sharesPerStorySecondary;
        String sharesPerStoryTitle;
        boolean sharesPerStoryUp;
        boolean sharesPerStoryVisible;
        String sharesPrimary;
        String sharesSecondary;
        String sharesTitle;
        boolean sharesUp;
        String viewsPerStoryPrimary;
        String viewsPerStorySecondary;
        String viewsPerStoryTitle;
        boolean viewsPerStoryUp;
        boolean viewsPerStoryVisible;
        String viewsPrimary;
        String viewsSecondary;
        String viewsTitle;
        boolean viewsUp;

        public static class Quadruple<A, B, C, D> {
            public A fist;
            public D fourth;
            public B second;
            public C third;

            public Quadruple(A r1, B r2, C r3, D r4) {
                this.fist = r1;
                this.second = r2;
                this.third = r3;
                this.fourth = r4;
            }
        }

        public OverviewChannelData(TL_stats.TL_broadcastStats r19) {
            Quadruple<String, String, Boolean, Boolean> r2 = prepare(r19.reactions_per_post);
            this.reactionsPerPostTitle = LocaleController.getString("ReactionsPerPost", R.string.ReactionsPerPost);
            this.reactionsPerPostPrimary = r2.fist;
            this.reactionsPerPostSecondary = r2.second;
            this.reactionsPerPostUp = r2.third.booleanValue();
            this.reactionsPerPostVisible = r2.fourth.booleanValue();
            Quadruple<String, String, Boolean, Boolean> r22 = prepare(r19.reactions_per_story);
            this.reactionsPerStoryTitle = LocaleController.getString("ReactionsPerStory", R.string.ReactionsPerStory);
            this.reactionsPerStoryPrimary = r22.fist;
            this.reactionsPerStorySecondary = r22.second;
            this.reactionsPerStoryUp = r22.third.booleanValue();
            this.reactionsPerStoryVisible = r22.fourth.booleanValue();
            Quadruple<String, String, Boolean, Boolean> r23 = prepare(r19.views_per_story);
            this.viewsPerStoryTitle = LocaleController.getString("ViewsPerStory", R.string.ViewsPerStory);
            this.viewsPerStoryPrimary = r23.fist;
            this.viewsPerStorySecondary = r23.second;
            this.viewsPerStoryUp = r23.third.booleanValue();
            this.viewsPerStoryVisible = r23.fourth.booleanValue();
            Quadruple<String, String, Boolean, Boolean> r24 = prepare(r19.shares_per_story);
            this.sharesPerStoryTitle = LocaleController.getString("SharesPerStory", R.string.SharesPerStory);
            this.sharesPerStoryPrimary = r24.fist;
            this.sharesPerStorySecondary = r24.second;
            this.sharesPerStoryUp = r24.third.booleanValue();
            this.sharesPerStoryVisible = r24.fourth.booleanValue();
            TL_stats.TL_statsAbsValueAndPrev r25 = r19.followers;
            double r3 = r25.current;
            double r5 = r25.previous;
            int r26 = (int) (r3 - r5);
            if (r5 != 0.0d) goto L5;
            float r52 = 0.0f;
        L6:
            this.followersTitle = LocaleController.getString("FollowersChartTitle", R.string.FollowersChartTitle);
            boolean r7 = false;
            this.followersPrimary = AndroidUtilities.formatWholeNumber((int) r19.followers.current, 0);
            String r11 = "+";
            if (r26 != 0) goto L9;
        L10:
            double r16 = 0.0d;
            this.followersSecondary = _UrlKt.FRAGMENT_ENCODE_SET;
        L25:
            if (r26 < 0) goto L27;
            boolean r27 = true;
        L28:
            this.followersUp = r27;
            TL_stats.TL_statsAbsValueAndPrev r28 = r19.shares_per_post;
            double r4 = r28.current;
            double r14 = r28.previous;
            int r29 = (int) (r4 - r14);
            if (r14 != r16) goto L31;
            float r42 = 0.0f;
        L32:
            this.sharesTitle = LocaleController.getString("SharesPerPost", R.string.SharesPerPost);
            this.sharesPrimary = AndroidUtilities.formatWholeNumber((int) r19.shares_per_post.current, 0);
            if (r29 != 0) goto L35;
        L49:
            this.sharesSecondary = _UrlKt.FRAGMENT_ENCODE_SET;
        L50:
            if (r29 < 0) goto L52;
            boolean r210 = true;
        L53:
            this.sharesUp = r210;
            TL_stats.TL_statsAbsValueAndPrev r211 = r19.views_per_post;
            double r43 = r211.current;
            double r142 = r211.previous;
            int r212 = (int) (r43 - r142);
            if (r142 != r16) goto L56;
            float r44 = 0.0f;
        L57:
            this.viewsTitle = LocaleController.getString("ViewsPerPost", R.string.ViewsPerPost);
            this.viewsPrimary = AndroidUtilities.formatWholeNumber((int) r19.views_per_post.current, 0);
            if (r212 != 0) goto L60;
        L74:
            this.viewsSecondary = _UrlKt.FRAGMENT_ENCODE_SET;
        L75:
            if (r212 < 0) goto L77;
            r7 = true;
        L77:
            this.viewsUp = r7;
            TL_stats.TL_statsPercentValue r1 = r19.enabled_notifications;
            float r12 = (float) ((r1.part / r1.total) * 100.0d);
            this.notificationsTitle = LocaleController.getString("EnabledNotifications", R.string.EnabledNotifications);
            int r213 = (int) r12;
            if (r12 != r213) goto L81;
            this.notificationsPrimary = String.format(Locale.ENGLISH, "%d%s", new Object[]{Integer.valueOf(r213), "%"});
            return;
        L81:
            this.notificationsPrimary = String.format(Locale.ENGLISH, "%.2f%s", new Object[]{Float.valueOf(r12), "%"});
            return;
        L60:
            if (r44 == 0.0f) goto L74;
            int r53 = (int) r44;
            if (r44 != r53) goto L69;
            Locale r45 = Locale.ENGLISH;
            StringBuilder r6 = new StringBuilder();
            if (r212 > 0) goto L68;
            r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        L68:
            r6.append(r11);
            r6.append(AndroidUtilities.formatWholeNumber(r212, 0));
            this.viewsSecondary = String.format(r45, "%s (%d%s)", new Object[]{r6.toString(), Integer.valueOf(r53), "%"});
            goto L75
        L69:
            Locale r54 = Locale.ENGLISH;
            StringBuilder r8 = new StringBuilder();
            if (r212 > 0) goto L73;
            r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        L73:
            r8.append(r11);
            r8.append(AndroidUtilities.formatWholeNumber(r212, 0));
            this.viewsSecondary = String.format(r54, "%s (%.1f%s)", new Object[]{r8.toString(), Float.valueOf(r44), "%"});
            goto L75
        L56:
            r44 = Math.abs((r212 / ((float) r142)) * 100.0f);
            goto L57
        L52:
            r210 = false;
            goto L53
        L35:
            if (r42 == 0.0f) goto L49;
            int r55 = (int) r42;
            if (r42 != r55) goto L44;
            Locale r46 = Locale.ENGLISH;
            StringBuilder r143 = new StringBuilder();
            if (r29 <= 0) goto L42;
            String r15 = "+";
        L43:
            r143.append(r15);
            r143.append(AndroidUtilities.formatWholeNumber(r29, 0));
            this.sharesSecondary = String.format(r46, "%s (%d%s)", new Object[]{r143.toString(), Integer.valueOf(r55), "%"});
            goto L50
        L42:
            r15 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L43
        L44:
            Locale r56 = Locale.ENGLISH;
            StringBuilder r144 = new StringBuilder();
            if (r29 <= 0) goto L47;
            String r152 = "+";
        L48:
            r144.append(r152);
            r144.append(AndroidUtilities.formatWholeNumber(r29, 0));
            this.sharesSecondary = String.format(r56, "%s (%.1f%s)", new Object[]{r144.toString(), Float.valueOf(r42), "%"});
            goto L50
        L47:
            r152 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L48
        L31:
            r42 = Math.abs((r29 / ((float) r14)) * 100.0f);
            goto L32
        L27:
            r27 = false;
            goto L28
        L9:
            if (r52 == 0.0f) goto L10;
            int r145 = (int) r52;
            if (r52 != r145) goto L18;
            Locale r57 = Locale.ENGLISH;
            StringBuilder r153 = new StringBuilder();
            r16 = 0.0d;
            if (r26 <= 0) goto L16;
            String r32 = "+";
        L17:
            r153.append(r32);
            r153.append(AndroidUtilities.formatWholeNumber(r26, 0));
            this.followersSecondary = String.format(r57, "%s (%d%s)", new Object[]{r153.toString(), Integer.valueOf(r145), "%"});
            goto L25
        L16:
            r32 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L17
        L18:
            r16 = 0.0d;
            Locale r33 = Locale.ENGLISH;
            StringBuilder r47 = new StringBuilder();
            if (r26 <= 0) goto L21;
            String r146 = "+";
        L22:
            r47.append(r146);
            r47.append(AndroidUtilities.formatWholeNumber(r26, 0));
            this.followersSecondary = String.format(r33, "%s (%.1f%s)", new Object[]{r47.toString(), Float.valueOf(r52), "%"});
            goto L25
        L21:
            r146 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L22
        L5:
            r52 = Math.abs((r26 / ((float) r5)) * 100.0f);
            goto L6
        }

        private Quadruple<String, String, Boolean, Boolean> prepare(TL_stats.TL_statsAbsValueAndPrev r11) {
            double r0 = r11.current;
            double r2 = r11.previous;
            int r10 = (int) (r0 - r2);
            if (r2 != 0.0d) goto L5;
            float r22 = 0.0f;
        L6:
            boolean r4 = false;
            String r3 = AndroidUtilities.formatWholeNumber((int) r11.current, 0);
            String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r10 == 0) goto L22;
            if (r22 == 0.0f) goto L22;
            int r5 = (int) r22;
            if (r22 != r5) goto L17;
            Locale r23 = Locale.ENGLISH;
            StringBuilder r7 = new StringBuilder();
            if (r10 <= 0) goto L16;
            r6 = "+";
        L16:
            r7.append(r6);
            r7.append(AndroidUtilities.formatWholeNumber(r10, 0));
            r6 = String.format(r23, "%s (%d%s)", new Object[]{r7.toString(), Integer.valueOf(r5), "%"});
            goto L22
        L17:
            Locale r52 = Locale.ENGLISH;
            StringBuilder r72 = new StringBuilder();
            if (r10 <= 0) goto L20;
            r6 = "+";
        L20:
            r72.append(r6);
            r72.append(AndroidUtilities.formatWholeNumber(r10, 0));
            r6 = String.format(r52, "%s (%.1f%s)", new Object[]{r72.toString(), Float.valueOf(r22), "%"});
        L22:
            if (r10 < 0) goto L24;
            boolean r53 = true;
        L25:
            if (r10 == 0) goto L27;
        L28:
            r4 = true;
        L30:
            return new Quadruple(r3, r6, Boolean.valueOf(r53), Boolean.valueOf(r4));
        L27:
            if (r11.current == 0.0d) goto L30;
        L24:
            r53 = false;
            goto L25
        L5:
            r22 = Math.abs((r10 / ((float) r2)) * 100.0f);
            goto L6
        }
    }

    public static class OverviewChatData {
        String membersPrimary;
        String membersSecondary;
        String membersTitle;
        boolean membersUp;
        String messagesPrimary;
        String messagesSecondary;
        String messagesTitle;
        boolean messagesUp;
        String postingMembersPrimary;
        String postingMembersSecondary;
        String postingMembersTitle;
        boolean postingMembersUp;
        String viewingMembersPrimary;
        String viewingMembersSecondary;
        String viewingMembersTitle;
        boolean viewingMembersUp;

        public OverviewChatData(TL_stats.TL_megagroupStats r18) {
            TL_stats.TL_statsAbsValueAndPrev r2 = r18.members;
            double r3 = r2.current;
            double r5 = r2.previous;
            int r22 = (int) (r3 - r5);
            if (r5 != 0.0d) goto L5;
            float r52 = 0.0f;
        L6:
            this.membersTitle = LocaleController.getString("MembersOverviewTitle", R.string.MembersOverviewTitle);
            boolean r7 = false;
            this.membersPrimary = AndroidUtilities.formatWholeNumber((int) r18.members.current, 0);
            String r6 = "+";
            if (r22 != 0) goto L9;
        L23:
            this.membersSecondary = _UrlKt.FRAGMENT_ENCODE_SET;
        L25:
            if (r22 < 0) goto L27;
            boolean r23 = true;
        L28:
            this.membersUp = r23;
            TL_stats.TL_statsAbsValueAndPrev r24 = r18.viewers;
            double r11 = r24.current;
            double r13 = r24.previous;
            int r25 = (int) (r11 - r13);
            if (r13 != 0.0d) goto L31;
            float r112 = 0.0f;
        L32:
            this.viewingMembersTitle = LocaleController.getString("ViewingMembers", R.string.ViewingMembers);
            this.viewingMembersPrimary = AndroidUtilities.formatWholeNumber((int) r18.viewers.current, 0);
            if (r25 != 0) goto L35;
        L42:
            this.viewingMembersSecondary = _UrlKt.FRAGMENT_ENCODE_SET;
        L43:
            if (r25 < 0) goto L45;
            boolean r26 = true;
        L46:
            this.viewingMembersUp = r26;
            TL_stats.TL_statsAbsValueAndPrev r27 = r18.posters;
            double r132 = r27.current;
            double r32 = r27.previous;
            int r28 = (int) (r132 - r32);
            if (r32 != 0.0d) goto L49;
            float r33 = 0.0f;
        L50:
            this.postingMembersTitle = LocaleController.getString("PostingMembers", R.string.PostingMembers);
            this.postingMembersPrimary = AndroidUtilities.formatWholeNumber((int) r18.posters.current, 0);
            if (r28 != 0) goto L53;
        L60:
            this.postingMembersSecondary = _UrlKt.FRAGMENT_ENCODE_SET;
        L61:
            if (r28 < 0) goto L63;
            boolean r29 = true;
        L64:
            this.postingMembersUp = r29;
            TL_stats.TL_statsAbsValueAndPrev r210 = r18.messages;
            double r34 = r210.current;
            double r133 = r210.previous;
            int r211 = (int) (r34 - r133);
            if (r133 != 0.0d) goto L67;
            float r35 = 0.0f;
        L68:
            this.messagesTitle = LocaleController.getString("MessagesOverview", R.string.MessagesOverview);
            this.messagesPrimary = AndroidUtilities.formatWholeNumber((int) r18.messages.current, 0);
            if (r211 != 0) goto L71;
        L78:
            this.messagesSecondary = _UrlKt.FRAGMENT_ENCODE_SET;
        L79:
            if (r211 < 0) goto L81;
            r7 = true;
        L81:
            this.messagesUp = r7;
            return;
        L71:
            if (r35 == 0.0f) goto L78;
            Locale r1 = Locale.ENGLISH;
            StringBuilder r36 = new StringBuilder();
            if (r211 > 0) goto L77;
            r6 = _UrlKt.FRAGMENT_ENCODE_SET;
        L77:
            r36.append(r6);
            r36.append(AndroidUtilities.formatWholeNumber(r211, 0));
            this.messagesSecondary = String.format(r1, "%s", new Object[]{r36.toString()});
            goto L79
        L67:
            r35 = Math.abs((r211 / ((float) r133)) * 100.0f);
            goto L68
        L63:
            r29 = false;
            goto L64
        L53:
            if (r33 == 0.0f) goto L60;
            Locale r37 = Locale.ENGLISH;
            StringBuilder r4 = new StringBuilder();
            if (r28 <= 0) goto L58;
            String r113 = "+";
        L59:
            r4.append(r113);
            r4.append(AndroidUtilities.formatWholeNumber(r28, 0));
            this.postingMembersSecondary = String.format(r37, "%s", new Object[]{r4.toString()});
            goto L61
        L58:
            r113 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L59
        L49:
            r33 = Math.abs((r28 / ((float) r32)) * 100.0f);
            goto L50
        L45:
            r26 = false;
            goto L46
        L35:
            if (r112 == 0.0f) goto L42;
            Locale r114 = Locale.ENGLISH;
            StringBuilder r134 = new StringBuilder();
            if (r25 <= 0) goto L40;
            String r14 = "+";
        L41:
            r134.append(r14);
            r134.append(AndroidUtilities.formatWholeNumber(r25, 0));
            this.viewingMembersSecondary = String.format(r114, "%s", new Object[]{r134.toString()});
            goto L43
        L40:
            r14 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L41
        L31:
            r112 = Math.abs((r25 / ((float) r13)) * 100.0f);
            goto L32
        L27:
            r23 = false;
            goto L28
        L9:
            if (r52 == 0.0f) goto L23;
            int r115 = (int) r52;
            if (r52 != r115) goto L18;
            Locale r53 = Locale.ENGLISH;
            StringBuilder r12 = new StringBuilder();
            if (r22 <= 0) goto L16;
            String r142 = "+";
        L17:
            r12.append(r142);
            r12.append(AndroidUtilities.formatWholeNumber(r22, 0));
            this.membersSecondary = String.format(r53, "%s (%d%s)", new Object[]{r12.toString(), Integer.valueOf(r115), "%"});
            goto L25
        L16:
            r142 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L17
        L18:
            Locale r116 = Locale.ENGLISH;
            StringBuilder r122 = new StringBuilder();
            if (r22 <= 0) goto L21;
            String r143 = "+";
        L22:
            r122.append(r143);
            r122.append(AndroidUtilities.formatWholeNumber(r22, 0));
            this.membersSecondary = String.format(r116, "%s (%.1f%s)", new Object[]{r122.toString(), Float.valueOf(r52), "%"});
            goto L25
        L21:
            r143 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L22
        L5:
            r52 = Math.abs((r22 / ((float) r5)) * 100.0f);
            goto L6
        }
    }

    public static class RecentPostInfo {
        public TL_stats.PostInteractionCounters counters;
        public MessageObject message;

        public RecentPostInfo() {
        }

        public long getDate() {
            if (this.message != null) goto L7;
            return 0;
        L7:
            return r2.messageOwner.date;
        }

        public int getForwards() {
            TL_stats.PostInteractionCounters r1 = this.counters;
            if ((r1 instanceof TL_stats.TL_postInteractionCountersMessage) == false) goto L7;
            return ((TL_stats.TL_postInteractionCountersMessage) r1).forwards;
        L7:
            if ((r1 instanceof TL_stats.TL_postInteractionCountersStory) == true) goto L9;
            return 0;
        L9:
            return ((TL_stats.TL_postInteractionCountersStory) r1).forwards;
        }

        public int getId() {
            TL_stats.PostInteractionCounters r1 = this.counters;
            if ((r1 instanceof TL_stats.TL_postInteractionCountersMessage) == false) goto L7;
            return ((TL_stats.TL_postInteractionCountersMessage) r1).msg_id;
        L7:
            if ((r1 instanceof TL_stats.TL_postInteractionCountersStory) == true) goto L9;
            return 0;
        L9:
            return ((TL_stats.TL_postInteractionCountersStory) r1).story_id;
        }

        public int getReactions() {
            TL_stats.PostInteractionCounters r1 = this.counters;
            if ((r1 instanceof TL_stats.TL_postInteractionCountersMessage) == false) goto L7;
            return ((TL_stats.TL_postInteractionCountersMessage) r1).reactions;
        L7:
            if ((r1 instanceof TL_stats.TL_postInteractionCountersStory) == true) goto L9;
            return 0;
        L9:
            return ((TL_stats.TL_postInteractionCountersStory) r1).reactions;
        }

        public int getViews() {
            TL_stats.PostInteractionCounters r1 = this.counters;
            if ((r1 instanceof TL_stats.TL_postInteractionCountersMessage) == false) goto L7;
            return ((TL_stats.TL_postInteractionCountersMessage) r1).views;
        L7:
            if ((r1 instanceof TL_stats.TL_postInteractionCountersStory) == true) goto L9;
            return 0;
        L9:
            return ((TL_stats.TL_postInteractionCountersStory) r1).views;
        }

        public boolean isStory() {
            return this.counters instanceof TL_stats.TL_postInteractionCountersStory;
        }
    }

    public static class UniversalChartCell extends BaseChartCell {
        private final int classGuid;
        private final int currentAccount;
        private Utilities.Callback0Return<BaseChartCell> findCell;
        private int stats_dc;

        public UniversalChartCell(Context r1, int r2, int r3, BaseChartView.SharedUiComponents r4, int r5) {
            super(r1, r3, r4);
            this.currentAccount = r2;
            this.classGuid = r5;
        }

        @Override // org.telegram.ui.StatisticActivity.BaseChartCell
        public void loadData(ChartViewData r4) {
            if (r4 == null) goto L8;
            int r0 = this.stats_dc;
            if (r0 < 0) goto L9;
            r4.load(this.currentAccount, this.classGuid, r0, this.findCell);
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.StatisticActivity.BaseChartCell
        public void onZoomed() {
        }

        public void set(int r1, ChartViewData r2, Utilities.Callback0Return<BaseChartCell> r3) {
            this.stats_dc = r1;
            this.findCell = r3;
            updateData(r2, false);
        }

        @Override // org.telegram.ui.StatisticActivity.BaseChartCell
        public void zoomCanceled() {
        }
    }

    public static class ZoomCancelable {
        int adapterPosition;
        boolean canceled;

        public ZoomCancelable() {
        }
    }

    public static /* synthetic */ void $r8$lambda$1VGLTYe54JvtoK0S_dq7Q04a2SM(StatisticActivity r0, MessageObject r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ls4W6fALilS5S5yod7MbDEFs6i4(StatisticActivity r0, List r1) {
        r0.lambda$loadStatistic$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$_YWoipRl0qvX_upretOU5PiGUSw(StatisticActivity r0, ChartViewData[] r1) {
        r0.lambda$loadStatistic$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$d2qIe0CDmJ0Hk8SM6e7PM6pVwEc(StatisticActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadStatistic$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$iVkLN2kZJwQVxdU2n87w1Kzot1k(StatisticActivity r0, int r1, View r2) {
        r0.lambda$createView$5(r1, r2);
    }

    /* renamed from: $r8$lambda$kzDZk-O5lMuNmnXdqdiuNeOBLZU, reason: not valid java name */
    public static /* synthetic */ void m19226$r8$lambda$kzDZkO5lMuNmnXdqdiuNeOBLZU(StatisticActivity r0) {
        r0.lambda$getThemeDescriptions$12();
    }

    /* renamed from: $r8$lambda$p_A4GLNg_YvfWL65-zjNyi3_jO8, reason: not valid java name */
    public static /* synthetic */ void m19227$r8$lambda$p_A4GLNg_YvfWL65zjNyi3_jO8(StatisticActivity r0, ArrayList r1) {
        r0.lambda$loadMessages$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$s65yMgCWLdTFF_alknIt5HDLwwY(StatisticActivity r0, View r1, int r2) {
        r0.lambda$createView$6(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$uCkmCvT1gZpFUMYprqy5itptObg(StatisticActivity r0, MessageObject r1) {
        r0.lambda$createView$8(r1);
    }

    /* renamed from: $r8$lambda$x_Arct0ETDjSjyLKSF7yzZjF-2o, reason: not valid java name */
    public static /* synthetic */ void m19228$r8$lambda$x_Arct0ETDjSjyLKSF7yzZjF2o(StatisticActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadMessages$11(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$xnHNteOsASGTZa3yvmFfSy9bsqA(StatisticActivity r0, View r1, int r2) {
        return r0.lambda$createView$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ybje828jnpguQ4E6dsVf49lIVMk(StatisticActivity r0, ChartViewData[] r1) {
        r0.lambda$loadStatistic$1(r1);
    }

    /* renamed from: $r8$lambda$zWT1xcmDpYJbuLwpky99RNL-gQI, reason: not valid java name */
    public static /* synthetic */ BaseChartCell m19229$r8$lambda$zWT1xcmDpYJbuLwpky99RNLgQI(StatisticActivity r0, ChartViewData r1) {
        return r0.lambda$getFindChartCell$4(r1);
    }

    /* renamed from: -$$Nest$fgetactionsData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19230$$Nest$fgetactionsData(StatisticActivity r0) {
        return r0.actionsData;
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m19231$$Nest$fgetadapter(StatisticActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetboostLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChannelBoostLayout m19232$$Nest$fgetboostLayout(StatisticActivity r0) {
        return r0.boostLayout;
    }

    /* renamed from: -$$Nest$fgetchat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m19233$$Nest$fgetchat(StatisticActivity r0) {
        return r0.chat;
    }

    /* renamed from: -$$Nest$fgetchildDataCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LruCache m19234$$Nest$fgetchildDataCache(StatisticActivity r0) {
        return r0.childDataCache;
    }

    /* renamed from: -$$Nest$fgetfollowersData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19235$$Nest$fgetfollowersData(StatisticActivity r0) {
        return r0.followersData;
    }

    /* renamed from: -$$Nest$fgetgroupMembersData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19236$$Nest$fgetgroupMembersData(StatisticActivity r0) {
        return r0.groupMembersData;
    }

    /* renamed from: -$$Nest$fgetgrowthData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19237$$Nest$fgetgrowthData(StatisticActivity r0) {
        return r0.growthData;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m19238$$Nest$fgetiBlur3SourceGlass(StatisticActivity r0) {
        return r0.iBlur3SourceGlass;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m19239$$Nest$fgetiBlur3SourceGlassFrosted(StatisticActivity r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetinteractionsData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19240$$Nest$fgetinteractionsData(StatisticActivity r0) {
        return r0.interactionsData;
    }

    /* renamed from: -$$Nest$fgetisMegagroup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19241$$Nest$fgetisMegagroup(StatisticActivity r0) {
        return r0.isMegagroup;
    }

    /* renamed from: -$$Nest$fgetivInteractionsData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19242$$Nest$fgetivInteractionsData(StatisticActivity r0) {
        return r0.ivInteractionsData;
    }

    /* renamed from: -$$Nest$fgetlanguagesData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19243$$Nest$fgetlanguagesData(StatisticActivity r0) {
        return r0.languagesData;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m19244$$Nest$fgetlayoutManager(StatisticActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistBlur3Capture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IBlur3Capture m19245$$Nest$fgetlistBlur3Capture(StatisticActivity r0) {
        return r0.listBlur3Capture;
    }

    /* renamed from: -$$Nest$fgetmaxDateOverview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19246$$Nest$fgetmaxDateOverview(StatisticActivity r2) {
        return r2.maxDateOverview;
    }

    /* renamed from: -$$Nest$fgetmembersLanguageData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19247$$Nest$fgetmembersLanguageData(StatisticActivity r0) {
        return r0.membersLanguageData;
    }

    /* renamed from: -$$Nest$fgetmessagesData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19248$$Nest$fgetmessagesData(StatisticActivity r0) {
        return r0.messagesData;
    }

    /* renamed from: -$$Nest$fgetmessagesIsLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19249$$Nest$fgetmessagesIsLoading(StatisticActivity r0) {
        return r0.messagesIsLoading;
    }

    /* renamed from: -$$Nest$fgetminDateOverview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m19250$$Nest$fgetminDateOverview(StatisticActivity r2) {
        return r2.minDateOverview;
    }

    /* renamed from: -$$Nest$fgetmonetizationLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChannelMonetizationLayout m19251$$Nest$fgetmonetizationLayout(StatisticActivity r0) {
        return r0.monetizationLayout;
    }

    /* renamed from: -$$Nest$fgetnewFollowersBySourceData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19252$$Nest$fgetnewFollowersBySourceData(StatisticActivity r0) {
        return r0.newFollowersBySourceData;
    }

    /* renamed from: -$$Nest$fgetnewMembersBySourceData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19253$$Nest$fgetnewMembersBySourceData(StatisticActivity r0) {
        return r0.newMembersBySourceData;
    }

    /* renamed from: -$$Nest$fgetnotificationsData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19254$$Nest$fgetnotificationsData(StatisticActivity r0) {
        return r0.notificationsData;
    }

    /* renamed from: -$$Nest$fgetonlyBoostsStat, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19255$$Nest$fgetonlyBoostsStat(StatisticActivity r0) {
        return r0.onlyBoostsStat;
    }

    /* renamed from: -$$Nest$fgetoverviewChannelData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OverviewChannelData m19256$$Nest$fgetoverviewChannelData(StatisticActivity r0) {
        return r0.overviewChannelData;
    }

    /* renamed from: -$$Nest$fgetoverviewChatData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OverviewChatData m19257$$Nest$fgetoverviewChatData(StatisticActivity r0) {
        return r0.overviewChatData;
    }

    /* renamed from: -$$Nest$fgetprogressLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m19258$$Nest$fgetprogressLayout(StatisticActivity r0) {
        return r0.progressLayout;
    }

    /* renamed from: -$$Nest$fgetreactionsByEmotionData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19259$$Nest$fgetreactionsByEmotionData(StatisticActivity r0) {
        return r0.reactionsByEmotionData;
    }

    /* renamed from: -$$Nest$fgetrecentAllSortedDataLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19260$$Nest$fgetrecentAllSortedDataLoaded(StatisticActivity r0) {
        return r0.recentAllSortedDataLoaded;
    }

    /* renamed from: -$$Nest$fgetrecentPostsAll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19261$$Nest$fgetrecentPostsAll(StatisticActivity r0) {
        return r0.recentPostsAll;
    }

    /* renamed from: -$$Nest$fgetrecentPostsLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19262$$Nest$fgetrecentPostsLoaded(StatisticActivity r0) {
        return r0.recentPostsLoaded;
    }

    /* renamed from: -$$Nest$fgetrecyclerListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m19263$$Nest$fgetrecyclerListView(StatisticActivity r0) {
        return r0.recyclerListView;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m19264$$Nest$fgetscrollableViewNoiseSuppressor(StatisticActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsharedUi, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseChartView.SharedUiComponents m19265$$Nest$fgetsharedUi(StatisticActivity r0) {
        return r0.sharedUi;
    }

    /* renamed from: -$$Nest$fgetstoriesList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesController.StoriesList m19266$$Nest$fgetstoriesList(StatisticActivity r0) {
        return r0.storiesList;
    }

    /* renamed from: -$$Nest$fgetstoryInteractionsData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19267$$Nest$fgetstoryInteractionsData(StatisticActivity r0) {
        return r0.storyInteractionsData;
    }

    /* renamed from: -$$Nest$fgetstoryReactionsByEmotionData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19268$$Nest$fgetstoryReactionsByEmotionData(StatisticActivity r0) {
        return r0.storyReactionsByEmotionData;
    }

    /* renamed from: -$$Nest$fgettopAdmins, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19269$$Nest$fgettopAdmins(StatisticActivity r0) {
        return r0.topAdmins;
    }

    /* renamed from: -$$Nest$fgettopDayOfWeeksData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19270$$Nest$fgettopDayOfWeeksData(StatisticActivity r0) {
        return r0.topDayOfWeeksData;
    }

    /* renamed from: -$$Nest$fgettopHoursData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19271$$Nest$fgettopHoursData(StatisticActivity r0) {
        return r0.topHoursData;
    }

    /* renamed from: -$$Nest$fgettopInviters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19272$$Nest$fgettopInviters(StatisticActivity r0) {
        return r0.topInviters;
    }

    /* renamed from: -$$Nest$fgettopMembersAll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19273$$Nest$fgettopMembersAll(StatisticActivity r0) {
        return r0.topMembersAll;
    }

    /* renamed from: -$$Nest$fgettopMembersVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m19274$$Nest$fgettopMembersVisible(StatisticActivity r0) {
        return r0.topMembersVisible;
    }

    /* renamed from: -$$Nest$fgetviewPagerFixed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m19275$$Nest$fgetviewPagerFixed(StatisticActivity r0) {
        return r0.viewPagerFixed;
    }

    /* renamed from: -$$Nest$fgetviewsBySourceData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChartViewData m19276$$Nest$fgetviewsBySourceData(StatisticActivity r0) {
        return r0.viewsBySourceData;
    }

    /* renamed from: -$$Nest$fputlastCancelable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19277$$Nest$fputlastCancelable(StatisticActivity r0, ZoomCancelable r1) {
        r0.lastCancelable = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19278$$Nest$mblur3_InvalidateBlur(StatisticActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mcancelZoom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19279$$Nest$mcancelZoom(StatisticActivity r0) {
        r0.cancelZoom();
    }

    /* renamed from: -$$Nest$mcheckUi_actionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19280$$Nest$mcheckUi_actionBar(StatisticActivity r0) {
        r0.checkUi_actionBar();
    }

    /* renamed from: -$$Nest$mgetFindChartCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback0Return m19281$$Nest$mgetFindChartCell(StatisticActivity r0, ChartViewData r1) {
        return r0.getFindChartCell(r1);
    }

    /* renamed from: -$$Nest$mloadMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19282$$Nest$mloadMessages(StatisticActivity r0) {
        r0.loadMessages();
    }

    public StatisticActivity(Bundle r7) {
        super(r7);
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L5;
        int r3 = 48;
    L6:
        this.ADDITIONAL_LIST_HEIGHT_DP = r3;
        this.topMembersAll = new ArrayList();
        this.topMembersVisible = new ArrayList();
        this.topInviters = new ArrayList();
        this.topAdmins = new ArrayList();
        this.childDataCache = new LruCache(50);
        this.progressDialog = new AlertDialog[1];
        this.loadFromId = -1;
        this.recentPostIdtoIndexMap = new SparseIntArray();
        this.recentStoriesIdtoIndexMap = new SparseIntArray();
        this.recentPostsAll = new ArrayList();
        this.recentPostsLoaded = new ArrayList();
        this.recentStoriesAll = new ArrayList();
        this.recentStoriesLoaded = new ArrayList();
        this.recentAllSortedDataLoaded = new ArrayList();
        this.initialLoading = true;
        this.showProgressbar = new AnonymousClass1(this);
        ArrayList<RectF> r32 = new ArrayList();
        this.iBlur3Positions = r32;
        RectF r4 = new RectF();
        this.iBlur3PositionActionBar = r4;
        RectF r5 = new RectF();
        this.iBlur3PositionMainTabs = r5;
        r32.add(r4);
        r32.add(r5);
        long r33 = r7.getLong("chat_id");
        this.chatId = r33;
        this.isMegagroup = r7.getBoolean("is_megagroup", false);
        this.startFromBoosts = r7.getBoolean("start_from_boosts", false);
        this.startFromMonetization = r7.getBoolean("start_from_monetization", false);
        this.onlyBoostsStat = r7.getBoolean("only_boosts", false);
        this.chat = getMessagesController().getChatFull(r33);
        BlurredBackgroundSourceColor r72 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r72;
        r72.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        if (r0 < 31) goto L10;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        this.iBlur3SourceGlassFrosted = new BlurredBackgroundSourceRenderNode(null);
        BlurredBackgroundSourceRenderNode r73 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = r73;
        BlurredBackgroundDrawableViewFactory r02 = new BlurredBackgroundDrawableViewFactory(r73);
        this.iBlur3FactoryLiquidGlass = r02;
        r02.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        return;
    L10:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
        this.iBlur3FactoryLiquidGlass = new BlurredBackgroundDrawableViewFactory(r72);
        return;
    L5:
        r3 = 0;
        goto L6
    }

    public static /* synthetic */ int access$000(StatisticActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(StatisticActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ int access$200(StatisticActivity r0) {
        return r0.classGuid;
    }

    public static /* synthetic */ boolean access$300(StatisticActivity r0) {
        return r0.isFinishing();
    }

    public static /* synthetic */ boolean access$400(StatisticActivity r0) {
        return r0.isFinishing();
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor != null) goto L7;
        return;
    L7:
        if (this.fragmentView == null) goto L17;
        int r1 = AndroidUtilities.dp(48.0f);
        int r2 = (this.fragmentView.getMeasuredHeight() - AndroidUtilities.navigationBarHeight) - AndroidUtilities.dp(8.0f);
        int r3 = r2 - AndroidUtilities.dp(56.0f);
        this.iBlur3PositionActionBar.set(0.0f, -r1, this.fragmentView.getMeasuredWidth(), this.actionBar.getMeasuredHeight() + r1);
        this.iBlur3PositionMainTabs.set(0.0f, r3, this.fragmentView.getMeasuredWidth(), r2);
        RectF r12 = this.iBlur3PositionMainTabs;
        if (LiteMode.isEnabled(262144) == false) goto L12;
        float r0 = 0.0f;
    L13:
        r12.inset(0.0f, r0);
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.iBlur3Positions, 2);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        return;
    L12:
        r0 = -AndroidUtilities.dp(48.0f);
        goto L13
    }

    private void cancelZoom() {
        ZoomCancelable r0 = this.lastCancelable;
        if (r0 == null) goto L5;
        r0.canceled = true;
    L5:
        int r02 = this.recyclerListView.getChildCount();
        int r3 = 0;
    L6:
        if (r3 >= r02) goto L11;
        View r4 = this.recyclerListView.getChildAt(r3);
        if ((r4 instanceof ChartCell) == false) goto L10;
        ((ChartCell) r4).chartView.legendSignatureView.showProgress(false, true);
    L10:
        r3 = r3 + 1;
        goto L6
    }

    private void checkUi_actionBar() {
        View r0 = this.viewPagerFixed.getCurrentView();
        ChannelBoostLayout r1 = this.boostLayout;
        if (r0 != r1) goto L6;
        this.actionBar.setAdaptiveBackground(r1.listView);
        return;
    L6:
        ChannelMonetizationLayout r12 = this.monetizationLayout;
        ActionBar r2 = this.actionBar;
        if (r0 != r12) goto L10;
        r2.setAdaptiveBackground(r12.listView);
        return;
    L10:
        r2.setAdaptiveBackground(this.recyclerListView);
    }

    private void checkUi_listPaddings() {
        int r0 = AndroidUtilities.navigationBarHeight;
        int r1 = AndroidUtilities.statusBarHeight;
        MainTabsLayout r2 = this.tabsView;
        if (r2 == null) goto L5;
        r2.setTranslationY(-r0);
    L5:
        int r22 = ActionBar.getCurrentActionBarHeight() + r1;
        if (this.showTabs == false) goto L8;
        int r12 = AndroidUtilities.dp(72.0f);
    L9:
        int r13 = r12 + r0;
        RecyclerListView r02 = this.recyclerListView;
        if (r02 == null) goto L12;
        r02.setPadding(0, r22, 0, r13);
    L12:
        ChannelBoostLayout r03 = this.boostLayout;
        if (r03 == null) goto L15;
        r03.listView.setPadding(0, r22, 0, r13);
    L15:
        ChannelMonetizationLayout r4 = this.monetizationLayout;
        if (r4 == null) goto L19;
        r4.listView.setPadding(0, r22, 0, r13);
        return;
    L19:
        return;
    L8:
        r12 = 0;
        goto L9
    }

    public static BaseFragment create(TLRPC.Chat r1) {
        return create(r1, true);
    }

    public static BaseFragment create(TLRPC.Chat r4, boolean r5) {
        Bundle r0 = new Bundle();
        r0.putLong("chat_id", r4.id);
        r0.putBoolean("is_megagroup", r4.megagroup);
        r0.putBoolean("start_from_boosts", r5);
        TLRPC.ChatFull r52 = MessagesController.getInstance(UserConfig.selectedAccount).getChatFull(r4.id);
        if (r52 == null) goto L12;
        if (r52.can_view_stats == true) goto L10;
        if (r52.can_view_stars_revenue == false) goto L12;
    L10:
        return new StatisticActivity(r0);
    L12:
        return new BoostsActivity(-r4.id);
    }

    public static ChartData createChartData(JSONObject r1, int r2, boolean r3) {
        if (r2 != 0) goto L6;
        return new ChartData(r1);
    L6:
        if (r2 != 1) goto L10;
        return new DoubleLinearChartData(r1);
    L10:
        if (r2 != 2) goto L14;
        return new StackBarChartData(r1);
    L14:
        if (r2 == 4) goto L16;
        return null;
    L16:
        return new StackLinearChartData(r1, r3);
    }

    public static ChartViewData createViewData(TL_stats.StatsGraph r1, String r2, int r3) {
        return createViewData(r1, r2, r3, false);
    }

    public static ChartViewData createViewData(TL_stats.StatsGraph r4, String r5, int r6, boolean r7) {
        if (r4 != null) goto L5;
    L37:
        return null;
    L5:
        if ((r4 instanceof TL_stats.TL_statsGraphError) == true) goto L37;
        ChartViewData r1 = new ChartViewData(r5, r6);
        r1.isLanguages = r7;
        if ((r4 instanceof TL_stats.TL_statsGraph) == false) goto L34;
        ChartData r52 = createChartData(new JSONObject(((TL_stats.TL_statsGraph) r4).json.data), r6, r7);     // Catch: JSONException -> L13
        r1.chartData = r52;     // Catch: JSONException -> L13
        if (r52 == null) goto L15;
        r52.yRate = r4.rate;     // Catch: JSONException -> L13
    L15:
        r1.zoomToken = ((TL_stats.TL_statsGraph) r4).zoom_token;     // Catch: JSONException -> L13
        if (r52 == null) goto L21;
        long[] r72 = r52.x;     // Catch: JSONException -> L13
        if (r72 == null) goto L21;
        if (r72.length < 2) goto L21;
    L23:
        if (r6 != 4) goto L36;
        if (r52 == null) goto L36;
        long[] r62 = r52.x;     // Catch: JSONException -> L13
        if (r62 == null) goto L36;
        if (r62.length <= 0) goto L36;
        long r2 = r62[r62.length - 1];     // Catch: JSONException -> L13
        r1.childChartData = new StackLinearChartData(r52, r2);     // Catch: JSONException -> L13
        r1.activeZoom = r2;     // Catch: JSONException -> L13
        return r1;
    L36:
        return r1;
    L21:
        r1.isEmpty = true;     // Catch: JSONException -> L13
    L13:
        e = move-exception;
        e.printStackTrace();
        return null;
    L34:
        if ((r4 instanceof TL_stats.TL_statsGraphAsync) == false) goto L36;
        r1.token = ((TL_stats.TL_statsGraphAsync) r4).token;
        goto L36
    }

    private void dataLoaded(ChartViewData[] r8) {
        Adapter r0 = this.adapter;
        if (r0 == null) goto L5;
        r0.update();
        this.recyclerListView.setItemAnimator(null);
        this.adapter.notifyDataSetChanged();
    L5:
        int r02 = 0;
        this.initialLoading = false;
        LinearLayout r1 = this.progressLayout;
        if (r1 != null) goto L8;
        return;
    L8:
        if (r1.getVisibility() != 0) goto L25;
        AndroidUtilities.cancelRunOnUIThread(this.showProgressbar);
        this.progressLayout.animate().alpha(0.0f).setDuration(230).setListener(new AnonymousClass2(this));
        this.recyclerListView.setVisibility(0);
        this.recyclerListView.setAlpha(0.0f);
        this.recyclerListView.animate().alpha(1.0f).setDuration(230).start();
        int r12 = r8.length;
    L10:
        if (r02 >= r12) goto L26;
        ChartViewData r2 = r8[r02];
        if (r2 == null) goto L18;
        if (r2.chartData != null) goto L18;
        if (r2.token == null) goto L18;
        r2.load(this.currentAccount, this.classGuid, this.chat.stats_dc, getFindChartCell(r2));
    L18:
        r02 = r02 + 1;
        goto L10
    L26:
        return;
    }

    private Utilities.Callback0Return<BaseChartCell> getFindChartCell(ChartViewData r2) {
        return new StatisticActivity$$ExternalSyntheticLambda13(this, r2);
    }

    private /* synthetic */ void lambda$createView$5(int r1, View r2) {
        this.viewPagerFixed.scrollToPosition(r1);
        selectTab(r1, true);
    }

    private /* synthetic */ void lambda$createView$6(View r4, int r5) {
        Adapter r42 = this.adapter;
        int r0 = r42.recentPostsStartRow;
        if (r5 >= r0) goto L5;
    L8:
        int r02 = r42.topAdminsStartRow;
        if (r5 >= r02) goto L11;
    L14:
        int r03 = r42.topMembersStartRow;
        if (r5 >= r03) goto L17;
    L20:
        int r04 = r42.topInviterStartRow;
        if (r5 < r04) goto L27;
        if (r5 > r42.topInviterEndRow) goto L27;
        ArrayList<MemberData> r43 = this.topInviters;
        r43.get(r5 - r04).onClick(this);
        return;
    L27:
        if (r5 != r42.expandTopMembersRow) goto L32;
        int r44 = this.topMembersAll.size() - this.topMembersVisible.size();
        int r52 = this.adapter.expandTopMembersRow;
        this.topMembersVisible.clear();
        this.topMembersVisible.addAll(this.topMembersAll);
        Adapter r05 = this.adapter;
        if (r05 == null) goto L33;
        r05.update();
        this.recyclerListView.setItemAnimator(this.animator);
        this.adapter.notifyItemRangeInserted(r52 + 1, r44);
        this.adapter.notifyItemRemoved(r52);
        return;
    L33:
        return;
    L32:
        return;
    L17:
        if (r5 > r42.topMembersEndRow) goto L20;
        ArrayList<MemberData> r45 = this.topMembersVisible;
        r45.get(r5 - r03).onClick(this);
        return;
    L11:
        if (r5 > r42.topAdminsEndRow) goto L14;
        ArrayList<MemberData> r46 = this.topAdmins;
        r46.get(r5 - r02).onClick(this);
        return;
    L5:
        if (r5 > r42.recentPostsEndRow) goto L8;
        presentFragment(new MessageStatisticActivity(this.recentAllSortedDataLoaded.get(r5 - r0), this.chatId, true));
    }

    private /* synthetic */ void lambda$createView$7(MessageObject r2) {
        presentFragment(new MessageStatisticActivity(r2));
    }

    private /* synthetic */ void lambda$createView$8(MessageObject r5) {
        Bundle r0 = new Bundle();
        r0.putLong("chat_id", this.chatId);
        r0.putInt("message_id", r5.getId());
        r0.putBoolean("need_remove_previous_same_chat_activity", false);
        presentFragment(new ChatActivity(r0), false);
    }

    private /* synthetic */ boolean lambda$createView$9(View r6, int r7) {
        Adapter r0 = this.adapter;
        int r1 = r0.recentPostsStartRow;
        if (r7 >= r1) goto L5;
    L11:
        int r62 = r0.topAdminsStartRow;
        if (r7 >= r62) goto L14;
    L17:
        int r63 = r0.topMembersStartRow;
        if (r7 >= r63) goto L20;
    L23:
        int r64 = r0.topInviterStartRow;
        if (r7 >= r64) goto L26;
    L29:
        return false;
    L26:
        if (r7 > r0.topInviterEndRow) goto L29;
        int r72 = r7 - r64;
        this.topInviters.get(r72).onLongClick(this.chat, this, this.progressDialog);
        return true;
    L20:
        if (r7 > r0.topMembersEndRow) goto L23;
        int r73 = r7 - r63;
        this.topMembersVisible.get(r73).onLongClick(this.chat, this, this.progressDialog);
        return true;
    L14:
        if (r7 > r0.topAdminsEndRow) goto L17;
        int r74 = r7 - r62;
        this.topAdmins.get(r74).onLongClick(this.chat, this, this.progressDialog);
        return true;
    L5:
        if (r7 > r0.recentPostsEndRow) goto L11;
        MessageObject r75 = this.recentAllSortedDataLoaded.get(r7 - r1).message;
        if (r75.isStory() == false) goto L9;
        return false;
    L9:
        ItemOptions.makeOptions(this, r6).add(R.drawable.msg_stats, LocaleController.getString(R.string.ViewMessageStatistic), new StatisticActivity$$ExternalSyntheticLambda11(this, r75)).add(R.drawable.msg_msgbubble3, LocaleController.getString(R.string.ViewMessage), new StatisticActivity$$ExternalSyntheticLambda12(this, r75)).setScrimViewBackground(this.recyclerListView.getClipBackground(r6)).show();
        return true;
    }

    private /* synthetic */ BaseChartCell lambda$getFindChartCell$4(ChartViewData r5) {
        int r0 = this.recyclerListView.getChildCount();
        int r1 = 0;
    L3:
        RecyclerListView r2 = this.recyclerListView;
        if (r1 >= r0) goto L11;
        View r22 = r2.getChildAt(r1);
        if ((r22 instanceof ChartCell) == false) goto L10;
        ChartCell r23 = (ChartCell) r22;
        if (r23.data != r5) goto L10;
        return r23;
    L10:
        r1 = r1 + 1;
        goto L3
    L11:
        r2.setItemAnimator(null);
        this.diffUtilsCallback.update();
        return null;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$12() {
        RecyclerListView r0 = this.recyclerListView;
        if (r0 == null) goto L21;
        int r02 = r0.getChildCount();
        int r1 = 0;
        int r2 = 0;
    L5:
        RecyclerListView r3 = this.recyclerListView;
        if (r2 >= r02) goto L8;
        recolorRecyclerItem(r3.getChildAt(r2));
        r2 = r2 + 1;
        goto L5
    L8:
        int r03 = r3.getHiddenChildCount();
        int r22 = 0;
    L9:
        RecyclerListView r32 = this.recyclerListView;
        if (r22 >= r03) goto L12;
        recolorRecyclerItem(r32.getHiddenChildAt(r22));
        r22 = r22 + 1;
        goto L9
    L12:
        int r04 = r32.getCachedChildCount();
        int r23 = 0;
    L13:
        RecyclerListView r33 = this.recyclerListView;
        if (r23 >= r04) goto L16;
        recolorRecyclerItem(r33.getCachedChildAt(r23));
        r23 = r23 + 1;
        goto L13
    L16:
        int r05 = r33.getAttachedScrapChildCount();
    L17:
        RecyclerListView r24 = this.recyclerListView;
        if (r1 >= r05) goto L20;
        recolorRecyclerItem(r24.getAttachedScrapChildAt(r1));
        r1 = r1 + 1;
        goto L17
    L20:
        r24.getRecycledViewPool().clear();
    L21:
        BaseChartView.SharedUiComponents r4 = this.sharedUi;
        if (r4 == null) goto L29;
        r4.invalidate();
        return;
    }

    private /* synthetic */ void lambda$loadMessages$10(ArrayList r8) {
        int r0 = 0;
        this.messagesIsLoading = false;
        if (r8.isEmpty() == false) goto L5;
        return;
    L5:
        int r1 = r8.size();
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L13;
        MessageObject r3 = (MessageObject) r8.get(r2);
        int r4 = this.recentPostIdtoIndexMap.get(r3.getId(), -1);
        if (r4 < 0) goto L12;
        if (this.recentPostsAll.get(r4).getId() != r3.getId()) goto L12;
        this.recentPostsAll.get(r4).message = r3;
    L12:
        r2 = r2 + 1;
        goto L6
    L13:
        this.recentPostsLoaded.clear();
        int r82 = this.recentPostsAll.size();
    L14:
        if (r0 >= r82) goto L19;
        RecentPostInfo r12 = this.recentPostsAll.get(r0);
        if (r12.message == null) goto L17;
        this.recentPostsLoaded.add(r12);
        r0 = r0 + 1;
        goto L14
    L17:
        this.loadFromId = r12.getId();
    L19:
        sortAllLoadedData();
        this.recyclerListView.setItemAnimator(null);
        this.diffUtilsCallback.update();
    }

    private /* synthetic */ void lambda$loadMessages$11(TLObject r10, TLRPC.TL_error r11) {
        ArrayList r112 = new ArrayList();
        if ((r10 instanceof TLRPC.messages_Messages) == false) goto L9;
        ArrayList<TLRPC.Message> r1 = ((TLRPC.messages_Messages) r10).messages;
        int r0 = 0;
    L6:
        if (r0 >= r1.size()) goto L8;
        r112.add(new MessageObject(this.currentAccount, r1.get(r0), false, true));
        r0 = r0 + 1;
        goto L6
    L8:
        getMessagesStorage().putMessages(r1, false, true, true, 0, 0, 0);
    L9:
        AndroidUtilities.runOnUIThread(new StatisticActivity$$ExternalSyntheticLambda10(this, r112));
    }

    private /* synthetic */ void lambda$loadStatistic$0(List r2) {
        if (this.storiesList.load(r2) == true) goto L6;
        prepareStoriesLoadedItems();
        sortAllLoadedData();
        return;
    }

    private /* synthetic */ void lambda$loadStatistic$1(ChartViewData[] r2) {
        this.ivInteractionsData = r2[0];
        this.followersData = r2[1];
        this.topHoursData = r2[2];
        this.interactionsData = r2[3];
        this.growthData = r2[4];
        this.viewsBySourceData = r2[5];
        this.newFollowersBySourceData = r2[6];
        this.languagesData = r2[7];
        this.notificationsData = r2[8];
        this.reactionsByEmotionData = r2[9];
        this.storyInteractionsData = r2[10];
        this.storyReactionsByEmotionData = r2[11];
        dataLoaded(r2);
    }

    private /* synthetic */ void lambda$loadStatistic$2(ChartViewData[] r2) {
        this.growthData = r2[0];
        this.groupMembersData = r2[1];
        this.newMembersBySourceData = r2[2];
        this.membersLanguageData = r2[3];
        this.messagesData = r2[4];
        this.actionsData = r2[5];
        this.topHoursData = r2[6];
        this.topDayOfWeeksData = r2[7];
        dataLoaded(r2);
    }

    private /* synthetic */ void lambda$loadStatistic$3(TLObject r47, TLRPC.TL_error r48) {
        int r9 = 0;
        if ((r47 instanceof TL_stats.TL_broadcastStats) == false) goto L20;
        TL_stats.TL_broadcastStats r2 = (TL_stats.TL_broadcastStats) r47;
        long r25 = 1000;
        ChartViewData[] r6 = {createViewData(r2.iv_interactions_graph, LocaleController.getString("IVInteractionsChartTitle", R.string.IVInteractionsChartTitle), 1), createViewData(r2.followers_graph, LocaleController.getString("FollowersChartTitle", R.string.FollowersChartTitle), 0), createViewData(r2.top_hours_graph, LocaleController.getString("TopHoursChartTitle", R.string.TopHoursChartTitle), 0), createViewData(r2.interactions_graph, LocaleController.getString("ViewsAndSharesChartTitle", R.string.ViewsAndSharesChartTitle), 1), createViewData(r2.growth_graph, LocaleController.getString("GrowthChartTitle", R.string.GrowthChartTitle), 0), createViewData(r2.views_by_source_graph, LocaleController.getString("ViewsBySourceChartTitle", R.string.ViewsBySourceChartTitle), 2), createViewData(r2.new_followers_by_source_graph, LocaleController.getString("NewFollowersBySourceChartTitle", R.string.NewFollowersBySourceChartTitle), 2), createViewData(r2.languages_graph, LocaleController.getString("LanguagesChartTitle", R.string.LanguagesChartTitle), 4, true), createViewData(r2.mute_graph, LocaleController.getString("NotificationsChartTitle", R.string.NotificationsChartTitle), 0), createViewData(r2.reactions_by_emotion_graph, LocaleController.getString("ReactionsByEmotionChartTitle", R.string.ReactionsByEmotionChartTitle), 2), createViewData(r2.story_interactions_graph, LocaleController.getString("StoryInteractionsChartTitle", R.string.StoryInteractionsChartTitle), 1), createViewData(r2.story_reactions_by_emotion_graph, LocaleController.getString("StoryReactionsByEmotionChartTitle", R.string.StoryReactionsByEmotionChartTitle), 2)};
        ChartViewData r7 = r6[2];
        if (r7 == null) goto L7;
        r7.useHourFormat = true;
    L7:
        this.overviewChannelData = new OverviewChannelData(r2);
        TL_stats.TL_statsDateRangeDays r72 = r2.period;
        this.maxDateOverview = r72.max_date * 1000;
        this.minDateOverview = r72.min_date * 1000;
        this.recentPostsAll.clear();
        ArrayList r73 = new ArrayList();
        ArrayList<TL_stats.PostInteractionCounters> r22 = r2.recent_posts_interactions;
        int r11 = r22.size();
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
    L8:
        if (r14 >= r11) goto L16;
        TL_stats.PostInteractionCounters r15 = r22.get(r14);
        r14 = r14 + 1;
        TL_stats.PostInteractionCounters r152 = r15;
        RecentPostInfo r5 = new RecentPostInfo();
        r5.counters = r152;
        if ((r152 instanceof TL_stats.TL_postInteractionCountersMessage) == false) goto L13;
        this.recentPostsAll.add(r5);
        this.recentPostIdtoIndexMap.put(r5.getId(), r12);
        r12 = r12 + 1;
    L13:
        if ((r152 instanceof TL_stats.TL_postInteractionCountersStory) == false) goto L8;
        r73.add(Integer.valueOf(r5.getId()));
        this.recentStoriesAll.add(r5);
        this.recentStoriesIdtoIndexMap.put(r5.getId(), r13);
        r13 = r13 + 1;
        goto L8
    L16:
        AndroidUtilities.runOnUIThread(new StatisticActivity$$ExternalSyntheticLambda7(this, r73));
        if (this.recentPostsAll.size() <= 0) goto L19;
        int r34 = this.recentPostsAll.get(0).getId();
        getMessagesStorage().getMessages(-this.chatId, 0, false, this.recentPostsAll.size(), r34, 0, 0, this.classGuid, 0, 0, 0, 0, true, false, null);
    L19:
        AndroidUtilities.runOnUIThread(new StatisticActivity$$ExternalSyntheticLambda8(this, r6));
    L22:
        if ((r47 instanceof TL_stats.TL_megagroupStats) == false) goto L68;
        TL_stats.TL_megagroupStats r1 = (TL_stats.TL_megagroupStats) r47;
        ChartViewData[] r23 = {createViewData(r1.growth_graph, LocaleController.getString("GrowthChartTitle", R.string.GrowthChartTitle), 0), createViewData(r1.members_graph, LocaleController.getString("GroupMembersChartTitle", R.string.GroupMembersChartTitle), 0), createViewData(r1.new_members_by_source_graph, LocaleController.getString("NewMembersBySourceChartTitle", R.string.NewMembersBySourceChartTitle), 2), createViewData(r1.languages_graph, LocaleController.getString("MembersLanguageChartTitle", R.string.MembersLanguageChartTitle), 4, true), createViewData(r1.messages_graph, LocaleController.getString("MessagesChartTitle", R.string.MessagesChartTitle), 2), createViewData(r1.actions_graph, LocaleController.getString("ActionsChartTitle", R.string.ActionsChartTitle), 1), createViewData(r1.top_hours_graph, LocaleController.getString("TopHoursChartTitle", R.string.TopHoursChartTitle), 0), createViewData(r1.weekdays_graph, LocaleController.getString("TopDaysOfWeekChartTitle", R.string.TopDaysOfWeekChartTitle), 4)};
        ChartViewData r3 = r23[6];
        if (r3 == null) goto L26;
        r3.useHourFormat = true;
    L26:
        ChartViewData r32 = r23[7];
        if (r32 == null) goto L29;
        r32.useWeekFormat = true;
    L29:
        this.overviewChatData = new OverviewChatData(r1);
        TL_stats.TL_statsDateRangeDays r33 = r1.period;
        this.maxDateOverview = r33.max_date * r25;
        this.minDateOverview = r33.min_date * r25;
        ArrayList<TL_stats.TL_statsGroupTopPoster> r35 = r1.top_posters;
        if (r35 != null) goto L32;
    L43:
        ArrayList<TL_stats.TL_statsGroupTopAdmin> r36 = r1.top_admins;
        if (r36 != null) goto L46;
    L51:
        ArrayList<TL_stats.TL_statsGroupTopInviter> r37 = r1.top_inviters;
        if (r37 != null) goto L54;
    L58:
        AndroidUtilities.runOnUIThread(new StatisticActivity$$ExternalSyntheticLambda9(this, r23));
        return;
    L54:
        if (r37.isEmpty() == true) goto L58;
    L56:
        if (r9 >= r1.top_inviters.size()) goto L58;
        this.topInviters.add(MemberData.from(r1.top_inviters.get(r9), r1.users));
        r9 = r9 + 1;
        goto L56
    L46:
        if (r36.isEmpty() == true) goto L51;
        int r38 = 0;
    L49:
        if (r38 >= r1.top_admins.size()) goto L51;
        this.topAdmins.add(MemberData.from(r1.top_admins.get(r38), r1.users));
        r38 = r38 + 1;
        goto L49
    L32:
        if (r35.isEmpty() == true) goto L43;
        int r39 = 0;
    L35:
        if (r39 >= r1.top_posters.size()) goto L41;
        MemberData r4 = MemberData.from(r1.top_posters.get(r39), r1.users);
        if (this.topMembersVisible.size() >= 10) goto L39;
        this.topMembersVisible.add(r4);
    L39:
        this.topMembersAll.add(r4);
        r39 = r39 + 1;
        goto L35
    L41:
        if ((this.topMembersAll.size() - this.topMembersVisible.size()) >= 2) goto L43;
        this.topMembersVisible.clear();
        this.topMembersVisible.addAll(this.topMembersAll);
        goto L43
    L68:
        return;
    L20:
        r25 = 1000;
        goto L22
    }

    private void loadMessages() {
        TLRPC.TL_channels_getMessages r0 = new TLRPC.TL_channels_getMessages();
        r0.id = new ArrayList();
        int r1 = this.recentPostIdtoIndexMap.get(this.loadFromId);
        int r2 = this.recentPostsAll.size();
        int r3 = 0;
    L3:
        if (r1 >= r2) goto L10;
        if (this.recentPostsAll.get(r1).message != null) goto L9;
        r0.id.add(Integer.valueOf(this.recentPostsAll.get(r1).getId()));
        r3 = r3 + 1;
        if (r3 > 50) goto L10;
    L9:
        r1 = r1 + 1;
    L10:
        r0.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(this.chatId);
        this.messagesIsLoading = true;
        getConnectionsManager().sendRequest(r0, new StatisticActivity$$ExternalSyntheticLambda5(this));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void loadStatistic() {
        if (this.onlyBoostsStat == false) goto L6;
        return;
    L6:
        if (this.isMegagroup == false) goto L9;
        TL_stats.TL_getMegagroupStats r0 = new TL_stats.TL_getMegagroupStats();
        r0.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(this.chatId);
        TL_stats.TL_getBroadcastStats r02 = r0;
    L8:
        TL_stats.TL_getBroadcastStats r3 = r02;
        int r03 = getConnectionsManager().sendRequest(r3, new StatisticActivity$$ExternalSyntheticLambda6(this), null, null, 0, this.chat.stats_dc, 1, true);
        getConnectionsManager().bindRequestToGuid(r03, this.classGuid);
        return;
    L9:
        TL_stats.TL_getBroadcastStats r04 = new TL_stats.TL_getBroadcastStats();
        r04.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(this.chatId);
        r02 = r04;
        goto L8
    }

    private void prepareStoriesLoadedItems() {
        this.recentStoriesLoaded.clear();
        ArrayList<RecentPostInfo> r0 = this.recentStoriesAll;
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        RecentPostInfo r3 = r0.get(r2);
        r2 = r2 + 1;
        RecentPostInfo r32 = r3;
        MessageObject r4 = this.storiesList.findMessageObject(r32.getId());
        if (r4 == null) goto L3;
        r32.message = r4;
        this.recentStoriesLoaded.add(r32);
        goto L3
    L7:
        this.recentStoriesIdtoIndexMap.clear();
        this.recentStoriesAll.clear();
    }

    public static void putColorFromData(ChartViewData r14, ArrayList<ThemeDescription> r15, ThemeDescription.ThemeDescriptionDelegate r16) {
        if (r14 == null) goto L17;
        ChartData r142 = r14.chartData;
        if (r142 == null) goto L23;
        ArrayList<ChartData.Line> r143 = r142.lines;
        int r0 = r143.size();
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L24;
        ChartData.Line r3 = r143.get(r2);
        r2 = r2 + 1;
        ChartData.Line r32 = r3;
        int r4 = r32.colorKey;
        if (r4 < 0) goto L6;
        if (Theme.hasThemeKey(r4) == true) goto L16;
        int r42 = r32.colorKey;
        if (Theme.isCurrentThemeNight() == false) goto L14;
        int r5 = r32.colorDark;
    L15:
        Theme.setColor(r42, r5, false);
        Theme.setDefaultColor(r32.colorKey, r32.color);
        goto L16
    L14:
        r5 = r32.color;
    L16:
        r15.add(new ThemeDescription(null, 0, null, null, null, r16, r32.colorKey));
        goto L6
    L24:
        return;
    L23:
        return;
    }

    private void recolorRecyclerItem(View r4) {
        if ((r4 instanceof ChartCell) == false) goto L7;
        ((ChartCell) r4).recolor();
        return;
    L7:
        if ((r4 instanceof ShadowSectionCell) == false) goto L11;
        Drawable r3 = Theme.getThemedDrawableByKey(ApplicationLoader.applicationContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow);
        CombinedDrawable r1 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray)), r3, 0, 0);
        r1.setFullsize(true);
        r4.setBackground(r1);
        return;
    L11:
        if ((r4 instanceof ChartHeaderView) == false) goto L15;
        ((ChartHeaderView) r4).recolor();
        return;
    L15:
        if ((r4 instanceof OverviewCell) == false) goto L18;
        OverviewCell.m19288$$Nest$mupdateColors((OverviewCell) r4);
        return;
    }

    private void sortAllLoadedData() {
        this.recentAllSortedDataLoaded.clear();
        this.recentAllSortedDataLoaded.addAll(this.recentPostsLoaded);
        this.recentAllSortedDataLoaded.addAll(this.recentStoriesLoaded);
        Collections.sort(this.recentAllSortedDataLoaded, Collections.reverseOrder(Comparator.comparingLong(new StatisticActivity$$ExternalSyntheticLambda4())));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ActionBar createActionBar(Context r1) {
        ActionBar r0 = super.createActionBar(r1);
        r0.setAddToContainer(false);
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r22) {
        StatisticActivity r1 = this;
        r1.sharedUi = new BaseChartView.SharedUiComponents();
        TLRPC.Chat r0 = MessagesController.getInstance(r1.currentAccount).getChat(Long.valueOf(r1.chatId));
        TLRPC.ChatFull r2 = MessagesController.getInstance(r1.currentAccount).getChatFull(r1.chatId);
        if (r2 != null) goto L5;
    L7:
        boolean r12 = false;
    L8:
        boolean r13 = ChatObject.isBoostSupported(r0);
        if (r2 != null) goto L11;
    L15:
        boolean r14 = false;
    L16:
        ArrayList r3 = new ArrayList(3);
        if (r12 == false) goto L19;
        r3.add(GlassTabView.createMainTab(r22, r1.resourceProvider, GlassTabView.TabAnimation.POLL, R.string.Statistics));
    L19:
        r3.add(GlassTabView.createMainTab(r22, r1.resourceProvider, GlassTabView.TabAnimation.BOOSTS, R.string.Boosts));
        if (r14 == false) goto L22;
        r3.add(GlassTabView.createMainTab(r22, r1.resourceProvider, GlassTabView.TabAnimation.MONETIZATION, R.string.Monetization));
    L22:
        r1.tabs = (GlassTabView[]) r3.toArray(new GlassTabView[0]);
        MainTabsLayout r32 = new MainTabsLayout(r22, r1.resourceProvider);
        r1.tabsView = r32;
        r32.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        int r33 = 0;
    L23:
        GlassTabView[] r4 = r1.tabs;
        if (r33 >= r4.length) goto L26;
        GlassTabView r42 = r4[r33];
        r42.setOnClickListener(new StatisticActivity$$ExternalSyntheticLambda1(r1, r33));
        r1.tabsView.addView(r1.tabs[r33]);
        r1.tabsView.setViewVisible(r42, true, false);
        r33 = r33 + 1;
        goto L23
    L26:
        r1.viewPagerFixed = new AnonymousClass3(r1, r1.getContext());
        FrameLayout r15 = new FrameLayout(r22);
        if (r13 == false) goto L29;
        r1.boostLayout = new ChannelBoostLayout(r1, -r1.chatId, r1.getResourceProvider());
    L29:
        if (r14 == false) goto L37;
        Context r43 = r1.getContext();
        int r34 = r1.currentAccount;
        long r6 = -r1.chatId;
        Theme.ResourcesProvider r62 = getResourceProvider();
        if (ChatObject.isChannelAndNotMegaGroup(r0) == true) goto L33;
    L35:
        boolean r7 = false;
    L36:
        ChannelMonetizationLayout r02 = new ChannelMonetizationLayout(r43, this, r34, r6, r62, r7, r2.can_view_stars_revenue);
        r1 = this;
        r1.monetizationLayout = r02;
        r02.setActionBar(r1.actionBar);
        goto L37
    L33:
        if (r2.can_view_revenue == false) goto L35;
        r7 = true;
    L37:
        boolean r23 = r12;
        r1.viewPagerFixed.setAdapter(new AnonymousClass4(r1, r23, r13, r14, r15));
        if (r13 == true) goto L40;
    L42:
        boolean r03 = false;
    L43:
        r1.showTabs = r03;
        if (r03 == true) goto L46;
    L48:
        if (r03 == true) goto L50;
    L57:
        r1.selectTab(r1.viewPagerFixed.getCurrentPosition(), false);
        AnonymousClass5 r04 = new AnonymousClass5(r1, r1.getContext());
        r1.actionBar.setDrawBlurBackground(r04);
        r04.setBackgroundColor(r1.getThemedColor(Theme.key_windowBackgroundGray));
        r1.iBlur3FactoryLiquidGlass.setSourceRootView(new ViewPositionWatcher(r04), r04);
        r04.addView(r1.viewPagerFixed, LayoutHelper.createFrameMatchParent());
        r04.addView(r1.actionBar);
        if (r1.showTabs == false) goto L60;
        r04.addView(r1.tabsView, LayoutHelper.createFrame(344, 72, 81));
        Bulletin.addDelegate(r1, new AnonymousClass6(r1));
    L60:
        r1.fragmentView = r04;
        AnonymousClass7 r24 = new AnonymousClass7(r1, r22);
        r1.recyclerListView = r24;
        r24.setSegmentedSectionsEnabled(false);
        r1.recyclerListView.setSections(true);
        r1.recyclerListView.setClipToPadding(false);
        RecyclerListView r35 = r1.recyclerListView;
        Objects.requireNonNull(r35);
        r1.listBlur3Capture = new ViewGroupPartRenderer(r35, r04, new ShareAlert$$ExternalSyntheticLambda16(r35));
        ChannelBoostLayout r25 = r1.boostLayout;
        if (r25 == null) goto L63;
        RecyclerListView r44 = r25.listView;
        Objects.requireNonNull(r44);
        r25.iBlur3Capture = new ViewGroupPartRenderer(r44, r04, new ShareAlert$$ExternalSyntheticLambda16(r44));
        r1.boostLayout.listView.addOnScrollListener(new AnonymousClass8(r1));
    L63:
        ChannelMonetizationLayout r26 = r1.monetizationLayout;
        if (r26 == null) goto L66;
        UniversalRecyclerView r45 = r26.listView;
        Objects.requireNonNull(r45);
        r26.iBlur3Capture = new ViewGroupPartRenderer(r45, r04, new CallLogActivity$$ExternalSyntheticLambda6(r45));
        r1.monetizationLayout.listView.addOnScrollListener(new AnonymousClass9(r1));
    L66:
        r1.iBlur3Capture = new AnonymousClass10(r1, r04);
        r1.recyclerListView.setSections();
        LinearLayout r05 = new LinearLayout(r22);
        r1.progressLayout = r05;
        r05.setOrientation(1);
        RLottieImageView r06 = new RLottieImageView(r22);
        r1.imageView = r06;
        r06.setAutoRepeat(true);
        r1.imageView.setAnimation(R.raw.statistic_preload, 120, 120);
        r1.imageView.playAnimation();
        TextView r07 = new TextView(r22);
        r07.setTextSize(1, 20.0f);
        r07.setTypeface(AndroidUtilities.bold());
        int r27 = Theme.key_player_actionBarTitle;
        r07.setTextColor(Theme.getColor(r27));
        r07.setTag(Integer.valueOf(r27));
        r07.setText(LocaleController.getString(R.string.LoadingStats));
        r07.setGravity(1);
        TextView r36 = new TextView(r22);
        r36.setTextSize(1, 15.0f);
        int r46 = Theme.key_player_actionBarSubtitle;
        r36.setTextColor(Theme.getColor(r46));
        r36.setTag(Integer.valueOf(r46));
        r36.setText(LocaleController.getString(R.string.LoadingStatsDescription));
        r36.setGravity(1);
        r1.progressLayout.addView(r1.imageView, LayoutHelper.createLinear(120, 120, 1, 0, 0, 0, 20));
        r1.progressLayout.addView(r07, LayoutHelper.createLinear(-2, -2, 1, 0, 0, 0, 10));
        r1.progressLayout.addView(r36, LayoutHelper.createLinear(-2, -2, 1));
        r15.addView(r1.progressLayout, LayoutHelper.createFrame(240, -2.0f, 17, 0.0f, 0.0f, 0.0f, 30.0f));
        if (r1.adapter != null) goto L69;
        r1.adapter = new Adapter(r1);
    L69:
        r1.recyclerListView.setAdapter(r1.adapter);
        LinearLayoutManager r08 = new LinearLayoutManager(r22);
        r1.layoutManager = r08;
        r1.recyclerListView.setLayoutManager(r08);
        r1.animator = new AnonymousClass11(r1);
        r1.recyclerListView.setItemAnimator(null);
        r1.recyclerListView.addOnScrollListener(new AnonymousClass12(r1));
        r1.recyclerListView.setOnItemClickListener(new StatisticActivity$$ExternalSyntheticLambda2(r1));
        r1.recyclerListView.setOnItemLongClickListener(new StatisticActivity$$ExternalSyntheticLambda3(r1));
        r15.addView(r1.recyclerListView);
        ChatAvatarContainer r09 = new ChatAvatarContainer(r22, null, false);
        r1.avatarContainer = r09;
        r09.setOccupyStatusBar(!AndroidUtilities.isTablet());
        r1.avatarContainer.getAvatarImageView().setScaleX(0.9f);
        r1.avatarContainer.getAvatarImageView().setScaleY(0.9f);
        r1.avatarContainer.setRightAvatarPadding(-AndroidUtilities.dp(3.0f));
        ActionBar r010 = r1.actionBar;
        ChatAvatarContainer r5 = r1.avatarContainer;
        if (r1.inPreviewMode == true) goto L72;
        float r152 = 50.0f;
    L73:
        r010.addView(r5, 0, LayoutHelper.createFrame(-2, -1.0f, 51, r152, 0.0f, 40.0f, 0.0f));
        TLRPC.Chat r011 = r1.getMessagesController().getChat(Long.valueOf(r1.chatId));
        r1.avatarContainer.setChatAvatar(r011);
        ChatAvatarContainer r52 = r1.avatarContainer;
        if (r011 != null) goto L76;
        String r012 = _UrlKt.FRAGMENT_ENCODE_SET;
    L77:
        r52.setTitle(r012);
        r1.avatarContainer.hideSubtitle();
        r1.actionBar.setBackButtonDrawable(new BackDrawable(false));
        r1.actionBar.setActionBarMenuOnItemClick(new AnonymousClass13(r1));
        r1.avatarContainer.setTitleColors(Theme.getColor(r27), Theme.getColor(r46));
        r1.actionBar.setItemsColor(Theme.getColor(r27), false);
        r1.actionBar.setItemsColor(Theme.getColor(r27), true);
        r1.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector), false);
        r1.actionBar.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        if (r1.initialLoading == false) goto L80;
        r1.progressLayout.setAlpha(0.0f);
        AndroidUtilities.runOnUIThread(r1.showProgressbar, 500);
        r1.progressLayout.setVisibility(0);
        r1.recyclerListView.setVisibility(8);
    L81:
        BlurredBackgroundDrawable r013 = r1.iBlur3FactoryLiquidGlass.create(r1.tabsView, BlurredBackgroundProviderImpl.mainTabs(r1.resourceProvider));
        r013.setRadius(AndroidUtilities.dp(28.0f));
        r013.setPadding(AndroidUtilities.dp(7.666f));
        r1.tabsView.setBackground(r013);
        r1.checkUi_listPaddings();
        r1.diffUtilsCallback = new DiffUtilsCallback(r1.adapter, r1.layoutManager, null);
        return r1.fragmentView;
    L80:
        AndroidUtilities.cancelRunOnUIThread(r1.showProgressbar);
        r1.progressLayout.setVisibility(8);
        r1.recyclerListView.setVisibility(0);
        goto L81
    L76:
        r012 = r011.title;
        goto L77
    L72:
        r152 = 0.0f;
        goto L73
    L50:
        if (r1.startFromMonetization == false) goto L57;
        ViewPagerFixed r014 = r1.viewPagerFixed;
        if (r1.onlyBoostsStat == true) goto L55;
        if (r13 == false) goto L55;
        int r37 = 1;
    L56:
        r014.setPosition((r23 ? 1 : 0) + r37);
    L55:
        r37 = 0;
        goto L56
    L46:
        if (r1.startFromBoosts == false) goto L48;
        r1.viewPagerFixed.setPosition(r23 ? 1 : 0);
        goto L57
    L40:
        if (r1.onlyBoostsStat == true) goto L42;
        r03 = true;
        goto L43
    L11:
        if (r2.can_view_revenue == false) goto L13;
    L14:
        r14 = true;
        goto L16
    L13:
        if (r2.can_view_stars_revenue == false) goto L15;
    L5:
        if (r2.can_view_stats == false) goto L7;
        r12 = true;
        goto L8
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        BaseFragment r0 = null;
        int r1 = 0;
        if (r8 != NotificationCenter.storiesListUpdated) goto L11;
        if (((StoriesController.StoriesList) r10[0]) != this.storiesList) goto L84;
        prepareStoriesLoadedItems();
        sortAllLoadedData();
        if (this.adapter == null) goto L85;
        this.recyclerListView.setItemAnimator(null);
        this.diffUtilsCallback.update();
        return;
    L85:
        return;
    L84:
        return;
    L11:
        if (r8 != NotificationCenter.boostByChannelCreated) goto L42;
        if (getParentLayout() == null) goto L93;
        TLRPC.Chat r82 = (TLRPC.Chat) r10[0];
        boolean r102 = ((Boolean) r10[1]).booleanValue();
        List<BaseFragment> r3 = getParentLayout().getFragmentStack();
        if (r3.size() < 2) goto L18;
        BaseFragment r32 = r3.get(r3.size() - 2);
    L20:
        if ((r32 instanceof ChatEditActivity) == false) goto L22;
        getParentLayout().removeFragmentFromStack(r32);
    L22:
        List<BaseFragment> r33 = getParentLayout().getFragmentStack();
        if (r33.size() < 2) goto L25;
        BaseFragment r2 = r33.get(r33.size() - 2);
    L26:
        if (r102 == true) goto L28;
        finishFragment();
        if ((r2 instanceof ProfileActivity) == false) goto L87;
        BoostDialogs.showBulletin(r2, r82, false);
        return;
    L87:
        return;
    L28:
        if (r33.size() < 3) goto L31;
        r0 = r33.get(r33.size() - 3);
    L31:
        if ((r2 instanceof ProfileActivity) == false) goto L33;
        getParentLayout().removeFragmentFromStack(r2);
    L33:
        finishFragment();
        if ((r0 instanceof ChatActivity) == false) goto L86;
        BoostDialogs.showBulletin(r0, r82, true);
        return;
    L86:
        return;
    L25:
        r2 = null;
        goto L26
    L18:
        r32 = null;
        goto L20
    L93:
        return;
    L42:
        if (r8 != NotificationCenter.messagesDidLoad) goto L70;
        if (((Integer) r10[10]).intValue() != this.classGuid) goto L88;
        ArrayList r83 = (ArrayList) r10[2];
        ArrayList r92 = new ArrayList();
        int r103 = r83.size();
        int r22 = 0;
    L46:
        if (r22 >= r103) goto L56;
        MessageObject r34 = (MessageObject) r83.get(r22);
        int r4 = this.recentPostIdtoIndexMap.get(r34.getId(), -1);
        if (r4 < 0) goto L55;
        if (this.recentPostsAll.get(r4).getId() != r34.getId()) goto L55;
        boolean r5 = r34.deleted;
        ArrayList<RecentPostInfo> r6 = this.recentPostsAll;
        if (r5 == false) goto L54;
        r92.add(r6.get(r4));
        goto L55
    L54:
        r6.get(r4).message = r34;
    L55:
        r22 = r22 + 1;
        goto L46
    L56:
        this.recentPostsAll.removeAll(r92);
        this.recentPostsLoaded.clear();
        int r84 = this.recentPostsAll.size();
    L57:
        if (r1 >= r84) goto L63;
        RecentPostInfo r93 = this.recentPostsAll.get(r1);
        if (r93.message == null) goto L60;
        this.recentPostsLoaded.add(r93);
        r1 = r1 + 1;
        goto L57
    L60:
        this.loadFromId = r93.getId();
    L63:
        if (this.recentPostsLoaded.size() >= 20) goto L65;
        loadMessages();
    L65:
        sortAllLoadedData();
        if (this.adapter == null) goto L89;
        this.recyclerListView.setItemAnimator(null);
        this.diffUtilsCallback.update();
        return;
    L89:
        return;
    L88:
        return;
    L70:
        if (r8 != NotificationCenter.chatInfoDidLoad) goto L90;
        TLRPC.ChatFull r85 = (TLRPC.ChatFull) r10[0];
        if (r85.id == this.chatId) goto L74;
        return;
    L74:
        if (this.chat != null) goto L92;
        this.chat = r85;
        loadStatistic();
        return;
    L92:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        return getThemedColor(Theme.key_windowBackgroundWhite);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        StatisticActivity$$ExternalSyntheticLambda0 r8 = new StatisticActivity$$ExternalSyntheticLambda0(this);
        ArrayList<ThemeDescription> r10 = new ArrayList();
        View r12 = this.fragmentView;
        int r13 = ThemeDescription.FLAG_BACKGROUND;
        int r18 = Theme.key_windowBackgroundGray;
        r10.add(new ThemeDescription(r12, r13, null, null, null, null, r18));
        int r32 = Theme.key_dialogTextBlack;
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{StatisticPostInfoCell.class}, new String[]{"message"}, null, null, null, r32));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{StatisticPostInfoCell.class}, new String[]{"views"}, null, null, null, r32));
        int r41 = Theme.key_windowBackgroundWhiteGrayText3;
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{StatisticPostInfoCell.class}, new String[]{"shares"}, null, null, null, r41));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{StatisticPostInfoCell.class}, new String[]{"likes"}, null, null, null, r41));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{StatisticPostInfoCell.class}, new String[]{"date"}, null, null, null, r41));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{ChartHeaderView.class}, new String[]{"textView"}, null, null, null, r32));
        View r2 = null;
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, r32));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_statisticChartSignature));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_statisticChartSignatureAlpha));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_statisticChartHintLine));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_statisticChartActiveLine));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_statisticChartInactivePickerChart));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_statisticChartActivePickerChart));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_dialogBackground));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_windowBackgroundWhite));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_windowBackgroundWhiteGrayText2));
        r10.add(new ThemeDescription(null, 0, null, null, null, null, r8, Theme.key_actionBarActionModeDefaultSelector));
        r10.add(new ThemeDescription(null, 0, null, null, null, r8, r18));
        r10.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_windowBackgroundGrayShadow));
        r10.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_windowBackgroundWhiteGreenText2));
        int r20 = Theme.key_text_RedRegular;
        r10.add(new ThemeDescription(null, 0, null, null, null, r8, r20));
        ChatAvatarContainer r1 = this.avatarContainer;
        if (r1 == null) goto L5;
        SimpleTextView r132 = r1.getTitleTextView();
    L6:
        r10.add(new ThemeDescription(r132, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_player_actionBarTitle));
        ChatAvatarContainer r14 = this.avatarContainer;
        if (r14 == null) goto L9;
        r2 = r14.getSubtitleTextView();
    L9:
        r10.add(new ThemeDescription(r2, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_player_actionBarSubtitle, null));
        r10.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_statisticChartLineEmpty));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueButton));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueIcon));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"imageView"}, null, null, null, r20));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKTAG, new Class[]{ManageChatTextCell.class}, new String[]{"textView"}, null, null, null, r20));
        int r6 = 0;
        if (this.isMegagroup == false) goto L25;
    L11:
        if (r6 >= 6) goto L55;
        if (r6 != 0) goto L14;
        ChartViewData r15 = this.growthData;
    L23:
        putColorFromData(r15, r10, r8);
        r6 = r6 + 1;
        goto L11
    L14:
        if (r6 != 1) goto L16;
        r15 = this.groupMembersData;
        goto L23
    L16:
        if (r6 != 2) goto L18;
        r15 = this.newMembersBySourceData;
        goto L23
    L18:
        if (r6 != 3) goto L20;
        r15 = this.membersLanguageData;
        goto L23
    L20:
        if (r6 != 4) goto L22;
        r15 = this.messagesData;
        goto L23
    L22:
        r15 = this.actionsData;
    L55:
        return r10;
    L25:
        if (r6 >= 12) goto L55;
        if (r6 != 0) goto L28;
        ChartViewData r16 = this.growthData;
    L54:
        putColorFromData(r16, r10, r8);
        r6 = r6 + 1;
        goto L25
    L28:
        if (r6 != 1) goto L30;
        r16 = this.followersData;
        goto L54
    L30:
        if (r6 != 2) goto L32;
        r16 = this.interactionsData;
        goto L54
    L32:
        if (r6 != 3) goto L34;
        r16 = this.ivInteractionsData;
        goto L54
    L34:
        if (r6 != 4) goto L37;
        r16 = this.viewsBySourceData;
        goto L54
    L37:
        if (r6 != 5) goto L39;
        r16 = this.newFollowersBySourceData;
        goto L54
    L39:
        if (r6 != 6) goto L42;
        r16 = this.notificationsData;
        goto L54
    L42:
        if (r6 != 7) goto L45;
        r16 = this.topHoursData;
        goto L54
    L45:
        if (r6 != 8) goto L48;
        r16 = this.languagesData;
        goto L54
    L48:
        if (r6 != 9) goto L51;
        r16 = this.reactionsByEmotionData;
        goto L54
    L51:
        if (r6 != 10) goto L53;
        r16 = this.storyInteractionsData;
        goto L54
    L53:
        r16 = this.storyReactionsByEmotionData;
        goto L54
    L5:
        r132 = null;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(getThemedColor(Theme.key_windowBackgroundWhite)) <= 0.699999988079071d) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r3) {
        ViewPagerFixed r0 = this.viewPagerFixed;
        if (r0 == null) goto L11;
        if (r0.currentPosition == 0) goto L7;
        return false;
    L7:
        if (r0.currentProgress == 1.0f) goto L11;
        return false;
    L11:
        return super.isSwipeBackEnabled(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.messagesDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.boostByChannelCreated);
        getNotificationCenter().addObserver(this, NotificationCenter.storiesListUpdated);
        StoriesController.StoriesList r0 = getMessagesController().getStoriesController().getStoriesList(-this.chatId, 2);
        this.storiesList = r0;
        if (r0 == null) goto L6;
        this.storiesListId = r0.link();
    L6:
        if (this.chat == null) goto L8;
        loadStatistic();
    L10:
        return super.onFragmentCreate();
    L8:
        MessagesController.getInstance(this.currentAccount).loadFullChat(this.chatId, this.classGuid, true);
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.boostByChannelCreated);
        getNotificationCenter().removeObserver(this, NotificationCenter.messagesDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.storiesListUpdated);
        AlertDialog r0 = this.progressDialog[0];
        if (r0 == null) goto L5;
        r0.dismiss();
        this.progressDialog[0] = null;
    L5:
        StoriesController.StoriesList r02 = this.storiesList;
        if (r02 == null) goto L8;
        r02.unlink(this.storiesListId);
    L8:
        super.onFragmentDestroy();
        Bulletin.removeDelegate(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        checkUi_listPaddings();
    }

    public void selectTab(int r5, boolean r6) {
        int r1 = 0;
    L3:
        GlassTabView[] r2 = this.tabs;
        if (r1 >= r2.length) goto L10;
        GlassTabView r22 = r2[r1];
        if (r1 != r5) goto L8;
        boolean r3 = true;
    L9:
        r22.setSelected(r3, r6);
        r1 = r1 + 1;
        goto L3
    L8:
        r3 = false;
        goto L9
    }

    public void setGestureSelectedOverride(float r4, boolean r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.tabs.length) goto L6;
        this.tabs[r0].setGestureSelectedOverride(Math.max(0.0f, 1.0f - Math.abs(r0 - r4)), r5);
        r0 = r0 + 1;
        goto L4
    L6:
        this.tabsView.invalidate();
    }
}
