package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.LongSparseArray;
import android.util.Property;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.CompoundEmoji;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.EmojiData;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.FeaturedStickerSetInfoCell;
import org.telegram.ui.Cells.StickerEmojiCell;
import org.telegram.ui.Cells.StickerSetGroupInfoCell;
import org.telegram.ui.Cells.StickerSetNameCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.ListView.RecyclerListViewWithOverlayDraw;
import org.telegram.ui.Components.PagerSlidingTabStrip;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrollSlidingTabStrip;
import org.telegram.ui.Components.StickerCategoriesListView;
import org.telegram.ui.Components.TrendingStickersLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.emojiview.FoundEmojiPacksRecyclerView;
import org.telegram.ui.Components.emojiview.FoundStickerPackButton;
import org.telegram.ui.Components.emojiview.FoundStickerPackButtonContainer;
import org.telegram.ui.Components.emojiview.FoundStickerPackCell;
import org.telegram.ui.Components.emojiview.FoundStickerPacksHeaderCell;
import org.telegram.ui.Components.inset.InAppKeyboardInsetView;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.StickersActivity;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;
import org.telegram.utils.glass.GlassEngine;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class EmojiView extends FrameLayout implements FactorAnimator.Target, NotificationCenter.NotificationCenterDelegate, InAppKeyboardInsetView {
    private ArrayList<Tab> allTabs;
    private boolean allowAnimatedEmoji;
    private boolean allowEmojisForNonPremium;
    private View animateExpandFromButton;
    private int animateExpandFromPosition;
    private long animateExpandStartTime;
    private int animateExpandToPosition;
    private LongSparseArray<AnimatedEmojiDrawable> animatedEmojiDrawables;
    private PorterDuffColorFilter animatedEmojiTextColorFilter;
    private final BoolAnimator animatorSearchEmojiPackSelected;
    private final BoolAnimator animatorSearchStickerPackSelected;
    private ImageView backspaceButton;
    private AnimatorSet backspaceButtonAnimation;
    private boolean backspaceOnce;
    private boolean backspacePressed;
    private final IBlur3Capture blurCaptureMethod;
    private final BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableFactory;
    private final BlurredBackgroundSourceColor blurredBackgroundSourceColor;
    private final BlurredBackgroundSourceRenderNode blurredBackgroundSourceRenderNode;
    private final RectF blurredRectF;
    private final ArrayList<RectF> blurredRectList;
    private int bottomInset;
    private FrameLayout bottomTabContainer;
    private View bottomTabContainerBackground;
    private final BoolAnimator bottomTabVisibility;
    private FrameLayout bulletinContainer;
    private FrameLayout bulletinContainer2;
    private Runnable checkExpandStickerTabsRunnable;
    private ChooseStickerActionTracker chooseStickerActionTracker;
    private EmojiColorPickerWindow colorPickerView;
    private ContentPreviewViewer.ContentPreviewViewerDelegate contentPreviewViewerDelegate;
    public int currentAccount;
    private int currentBackgroundType;
    private long currentChatId;
    private int currentPage;
    private ArrayList<Tab> currentTabs;
    public boolean customOutline;
    private EmojiViewDelegate delegate;
    private boolean disableStickerEditor;
    private Paint dotPaint;
    private DragListener dragListener;
    private EmojiGridAdapter emojiAdapter;
    private FoundStickerPackButton emojiAddPackButton;
    private FoundStickerPackButtonContainer emojiAddPackButtonContainer;
    boolean emojiBanned;
    public int emojiCacheType;
    private FrameLayout emojiContainer;
    private EmojiGridView emojiGridView;
    private float emojiLastX;
    private float emojiLastY;
    private GridLayoutManager emojiLayoutManager;
    private Drawable emojiLockDrawable;
    private Paint emojiLockPaint;
    private boolean emojiPackAlertOpened;
    EmojiPagesAdapter emojiPagerAdapter;
    private RecyclerAnimationScrollHelper emojiScrollHelper;
    private EmojiSearchAdapter emojiSearchAdapter;
    private SearchField emojiSearchField;
    private FoundStickerPacksHeaderCell emojiSearchHeader;
    private int emojiSize;
    private boolean emojiSmoothScrolling;
    private AnimatorSet emojiTabShadowAnimator;
    private EmojiTabsStrip emojiTabs;
    private View emojiTabsShadow;
    private String[] emojiTitles;
    private ImageViewEmoji emojiTouchedView;
    private float emojiTouchedX;
    private float emojiTouchedY;
    private ArrayList<EmojiPack> emojipacksProcessed;
    private boolean expandStickersByDragg;
    private ArrayList<Long> expandedEmojiSets;
    private final GradientDrawable fadeDrawable;
    private int favTabNum;
    private ArrayList<TLRPC.Document> favouriteStickers;
    private ArrayList<TLRPC.StickerSetCovered> featuredEmojiSets;
    private ArrayList<TLRPC.StickerSetCovered> featuredStickerSets;
    private boolean firstEmojiAttach;
    private boolean firstGifAttach;
    private boolean firstStickersAttach;
    private boolean firstTabUpdate;
    public boolean fixBottomTabContainerTranslation;
    private boolean forseMultiwindowLayout;
    private BaseFragment fragment;
    private boolean frozen;
    ArrayList<TLRPC.TL_messages_stickerSet> frozenStickerSets;
    private GifAdapter gifAdapter;
    private final Map<String, TLRPC.messages_BotResults> gifCache;
    private FrameLayout gifContainer;
    private int gifFirstEmojiTabNum;
    private RecyclerListView gifGridView;
    private Drawable[] gifIcons;
    private GifLayoutManager gifLayoutManager;
    private RecyclerListView.OnItemClickListener gifOnItemClickListener;
    private int gifRecentTabNum;
    private GifAdapter gifSearchAdapter;
    private SearchField gifSearchField;
    private GifSearchPreloader gifSearchPreloader;
    private ScrollSlidingTabStrip gifTabs;
    private int gifTrendingTabNum;
    private boolean glassDesign;
    private final GlassEngine glassEngine;
    private int groupStickerPackNum;
    private int groupStickerPackPosition;
    private TLRPC.TL_messages_stickerSet groupStickerSet;
    private boolean groupStickersHidden;
    private boolean hasChatStickers;
    private int hasRecentEmoji;
    private Runnable hideStickersBan;
    private boolean ignorePagerScroll;
    private boolean ignoreStickersScroll;
    private TLRPC.ChatFull info;
    public ArrayList<Long> installedEmojiSets;
    private LongSparseArray<TLRPC.StickerSetCovered> installingStickerSets;
    private boolean isLayout;
    public boolean isNewHeightControl;
    private ArrayList<Long> keepFeaturedDuplicate;
    private float lastBottomScrollDy;
    private int lastFadeColor;
    private int lastNotifyWidth;
    private ArrayList<String> lastRecentArray;
    private int lastRecentCount;
    private int lastRecentEmojiVersion;
    private String[] lastSearchKeyboardLanguage;
    private float lastStickersX;
    private int[] location;
    private boolean mForceHideBackspaceButton;
    private boolean mForceHideSettingsButton;
    private TextView mediaBanTooltip;
    private boolean needEmojiSearch;
    private NotificationCenter.ObserversGroup observersGroup;
    private Object outlineProvider;
    private ViewPager pager;
    private boolean premiumBulletin;
    private ArrayList<TLRPC.Document> premiumStickers;
    private int premiumTabNum;
    private TLRPC.StickerSetCovered[] primaryInstallingStickerSets;
    private ArrayList<TLRPC.Document> recentGifs;
    private ArrayList<TLRPC.Document> recentStickers;
    private int recentTabNum;
    Rect rect;
    private LongSparseArray<TLRPC.StickerSetCovered> removingStickerSets;
    private final Theme.ResourcesProvider resourcesProvider;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private AnimatorSet searchAnimation;
    private ImageView searchButton;
    private int searchFieldHeight;
    private Drawable searchIconDotDrawable;
    private Drawable searchIconDrawable;
    private boolean shouldDrawBackground;
    public boolean shouldDrawStickerSettings;
    public boolean shouldLightenBackground;
    private boolean showGifs;
    private boolean showLocalPremiumEmojiHint;
    private AnimatorSet showStickersBanAnimator;
    private boolean showing;
    private long shownBottomTabAfterClick;
    private boolean stickerAdapterBuilt;
    private FoundStickerPackButton stickerAddPackButton;
    private FoundStickerPackButtonContainer stickerAddPackButtonContainer;
    private Drawable[] stickerIcons;
    private FoundStickerPacksHeaderCell stickerSearchHeader;
    private ArrayList<TLRPC.TL_messages_stickerSet> stickerSets;
    private ImageView stickerSettingsButton;
    boolean stickersBanned;
    private AnimatorSet stickersButtonAnimation;
    private FrameLayout stickersContainer;
    private boolean stickersContainerAttached;
    private StickersGridAdapter stickersGridAdapter;
    private RecyclerListView stickersGridView;
    private GridLayoutManager stickersLayoutManager;
    private RecyclerListView.OnItemClickListener stickersOnItemClickListener;
    private RecyclerAnimationScrollHelper stickersScrollHelper;
    private SearchField stickersSearchField;
    private StickersSearchGridAdapter stickersSearchGridAdapter;
    private ScrollSlidingTabStrip stickersTab;
    private FrameLayout stickersTabContainer;
    private int stickersTabOffset;
    private Drawable[] tabIcons;
    private final int[] tabsMinusDy;
    private ObjectAnimator[] tabsYAnimators;
    private HashMap<Long, Utilities.Callback<TLRPC.TL_messages_stickerSet>> toInstall;
    private TrendingAdapter trendingAdapter;
    private TrendingAdapter trendingEmojiAdapter;
    private int trendingTabNum;
    private PagerSlidingTabStrip typeTabs;
    private Runnable updateStickersLoadedDelayed;
    private float visibleInAppKeyboardHeight;

    /* renamed from: org.telegram.ui.Components.EmojiView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass1(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (EmojiView.m10385$$Nest$fgetstickersTab(this.this$0).isDragging() == true) goto L6;
            EmojiView.m10405$$Nest$fputexpandStickersByDragg(this.this$0, false);
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$10, reason: invalid class name */
    public class AnonymousClass10 extends SearchField {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass10(EmojiView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (r2 == getTranslationY()) goto L6;
            super.setTranslationY(r2);
            EmojiView.m10313$$Nest$fgetemojiContainer(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$11, reason: invalid class name */
    public class AnonymousClass11 implements View.OnFocusChangeListener {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass11(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View r1, boolean r2) {
            if (r2 == false) goto L5;
            EmojiView.m10412$$Nest$fputlastSearchKeyboardLanguage(this.this$0, AndroidUtilities.getCurrentKeyboardLanguage());
            MediaDataController.getInstance(this.this$0.currentAccount).fetchNewEmojiKeywords(EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$12, reason: invalid class name */
    public class AnonymousClass12 extends FrameLayout {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass12(EmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (r6 != EmojiView.m10341$$Nest$fgetgifGridView(this.this$0)) goto L7;
            r5.save();
            r5.clipRect(0.0f, EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).getY() + EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            boolean r4 = super.drawChild(r5, r6, r7);
            r5.restore();
            return r4;
        L7:
            return super.drawChild(r5, r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$13, reason: invalid class name */
    public class AnonymousClass13 extends RecyclerListView {
        private boolean ignoreLayout;
        final /* synthetic */ EmojiView this$0;
        private boolean wasMeasured;

        public AnonymousClass13(EmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r7) {
            boolean r72 = ContentPreviewViewer.getInstance().onInterceptTouchEvent(r7, EmojiView.m10341$$Nest$fgetgifGridView(this.this$0), 0, EmojiView.m10305$$Nest$fgetcontentPreviewViewerDelegate(this.this$0), this.resourcesProvider);
            if (super.onInterceptTouchEvent(r7) == true) goto L8;
            if (r72 == true) goto L10;
            return false;
        L10:
            return true;
        L8:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            if (EmojiView.m10333$$Nest$fgetfirstGifAttach(this.this$0) == true) goto L5;
        L7:
            super.onLayout(r4, r5, r6, r7, r8);
            EmojiView.m10423$$Nest$mcheckGifSearchFieldScroll(this.this$0, true);
            return;
        L5:
            if (EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0).getItemCount() <= 1) goto L7;
            this.ignoreLayout = true;
            EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
            EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).setVisibility(0);
            EmojiView.m10347$$Nest$fgetgifTabs(this.this$0).onPageScrolled(0, 0);
            EmojiView.m10407$$Nest$fputfirstGifAttach(this.this$0, false);
            this.ignoreLayout = false;
            goto L7
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (this.wasMeasured == true) goto L6;
            EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0).notifyDataSetChanged();
            this.wasMeasured = true;
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$14, reason: invalid class name */
    public class AnonymousClass14 extends RecyclerView.ItemDecoration {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass14(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r2, View r3, RecyclerView r4, RecyclerView.State r5) {
            int r32 = r4.getChildAdapterPosition(r3);
            int r0 = 0;
            if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter() == EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0)) goto L5;
        L8:
            if (r32 == 0) goto L10;
        L14:
            r2.left = 0;
            r2.bottom = 0;
            r2.top = AndroidUtilities.dp(2.0f);
            if (EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).isLastInRow(r32 - (GifAdapter.m10504$$Nest$fgetaddSearch(EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0)) ? 1 : 0)) == true) goto L18;
            r0 = AndroidUtilities.dp(2.0f);
        L18:
            r2.right = r0;
            return;
        L10:
            if (GifAdapter.m10504$$Nest$fgetaddSearch(EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0)) == false) goto L14;
            r2.set(0, 0, 0, 0);
            return;
        L5:
            if (r32 != GifAdapter.m10514$$Nest$fgettrendingSectionItem(EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0))) goto L8;
            r2.set(0, 0, 0, 0);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$15, reason: invalid class name */
    public class AnonymousClass15 extends TypedScrollListener {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass15(EmojiView r1, int r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.EmojiView.TypedScrollListener, androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            super.onScrolled(r2, r3, r4);
            if (Build.VERSION.SDK_INT >= 31) goto L5;
            return;
        L5:
            if (EmojiView.m10371$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L9;
            EmojiView.m10371$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$16, reason: invalid class name */
    public class AnonymousClass16 extends SearchField {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass16(EmojiView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (getTranslationY() == r2) goto L6;
            super.setTranslationY(r2);
            EmojiView.m10339$$Nest$fgetgifContainer(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$17, reason: invalid class name */
    public class AnonymousClass17 extends FrameLayout {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ boolean val$shouldDrawBackground;

        public AnonymousClass17(EmojiView r1, Context r2, boolean r3) {
            this.this$0 = r1;
            this.val$shouldDrawBackground = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (this.val$shouldDrawBackground == true) goto L14;
            if (r6 != EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0)) goto L7;
        L8:
            r5.save();
            float r0 = (EmojiView.m10385$$Nest$fgetstickersTab(this.this$0).getY() + EmojiView.m10385$$Nest$fgetstickersTab(this.this$0).getMeasuredHeight()) + 1.0f;
            if (r6 != EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0)) goto L11;
            r0 = Math.max(r0, (EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$0).getY() + EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$0).getMeasuredHeight()) + 1.0f);
        L11:
            r5.clipRect(0.0f, r0 - (AndroidUtilities.dp(16.0f) * EmojiView.m10297$$Nest$fgetanimatorSearchStickerPackSelected(this.this$0).getFloatValue()), getMeasuredWidth(), getMeasuredHeight());
            boolean r4 = super.drawChild(r5, r6, r7);
            r5.restore();
            return r4;
        L7:
            if (r6 == EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$0)) goto L8;
        L14:
            return super.drawChild(r5, r6, r7);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            EmojiView.m10416$$Nest$fputstickersContainerAttached(this.this$0, true);
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
            if (EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0) == null) goto L6;
            EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0).checkVisibility();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            EmojiView.m10416$$Nest$fputstickersContainerAttached(this.this$0, false);
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
            if (EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0) == null) goto L6;
            EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0).checkVisibility();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$18, reason: invalid class name */
    public class AnonymousClass18 extends RecyclerListViewWithOverlayDraw {
        boolean ignoreLayout;
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass18(EmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ListView.RecyclerListViewWithOverlayDraw, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r1) {
            super.dispatchDraw(r1);
            EmojiView.m10350$$Nest$fgetglassEngine(this.this$0).invalidate();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r9) {
            if (EmojiView.m10356$$Nest$fgetignorePagerScroll(this.this$0) == false) goto L5;
            return false;
        L5:
            boolean r92 = ContentPreviewViewer.getInstance().onInterceptTouchEvent(r9, EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0), this.this$0.getMeasuredHeight(), EmojiView.m10305$$Nest$fgetcontentPreviewViewerDelegate(this.this$0), this.resourcesProvider);
            if (super.onInterceptTouchEvent(r9) == true) goto L10;
            if (r92 == true) goto L12;
            return false;
        L12:
            return true;
        L10:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            if (EmojiView.m10334$$Nest$fgetfirstStickersAttach(this.this$0) == true) goto L5;
        L7:
            super.onLayout(r4, r5, r6, r7, r8);
            EmojiView.m10426$$Nest$mcheckStickersSearchFieldScroll(this.this$0, true);
            return;
        L5:
            if (EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0).getItemCount() <= 0) goto L7;
            this.ignoreLayout = true;
            EmojiView.m10382$$Nest$fgetstickersLayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
            EmojiView.m10408$$Nest$fputfirstStickersAttach(this.this$0, false);
            this.ignoreLayout = false;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r3, int r4) {
            super.onScrolled(r3, r4);
            if (Build.VERSION.SDK_INT < 31) goto L8;
            if (EmojiView.m10371$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L8;
            EmojiView.m10371$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
        L8:
            if (EmojiView.m10386$$Nest$fgetstickersTabContainer(this.this$0) == null) goto L15;
            ScrollSlidingTabStrip r32 = EmojiView.m10385$$Nest$fgetstickersTab(this.this$0);
            if (EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0).canScrollVertically(-1) == false) goto L12;
            int r42 = AndroidUtilities.getShadowHeight();
        L13:
            r32.setUnderlineHeight(r42);
            goto L15
        L12:
            r42 = 0;
        L15:
            if (EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0) != null) goto L17;
            return;
        L17:
            if (getAdapter() == EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0)) goto L19;
            return;
        L19:
            if (StickersSearchGridAdapter.m10557$$Nest$fgetselectedPackId(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0)) == 0) goto L21;
            return;
        L21:
            if (StickersSearchGridAdapter.m10556$$Nest$fgetsearchRunnable(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0)).isLoading() == false) goto L23;
            return;
        L23:
            if (StickersSearchGridAdapter.m10556$$Nest$fgetsearchRunnable(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0)).isCompleted() == false) goto L25;
            return;
        L25:
            if ((EmojiView.m10382$$Nest$fgetstickersLayoutManager(this.this$0).findLastVisibleItemPosition() + 50) <= EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0).getItemCount()) goto L33;
            SearchRunnable r2 = StickersSearchGridAdapter.m10556$$Nest$fgetsearchRunnable(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0));
            Objects.requireNonNull(r2);
            AndroidUtilities.runOnUIThread(new EmojiView$18$$ExternalSyntheticLambda0(r2));
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$19, reason: invalid class name */
    public class AnonymousClass19 extends GridLayoutManager {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass19(EmojiView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int r1, RecyclerView.Recycler r2, RecyclerView.State r3) {
            int r12 = super.scrollVerticallyBy(r1, r2, r3);
            if (r12 == 0) goto L8;
            if (EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0).getScrollState() != 1) goto L8;
            EmojiView.m10405$$Nest$fputexpandStickersByDragg(this.this$0, false);
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
        L8:
            if (EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0) != null) goto L10;
            EmojiView.m10428$$Nest$mcreateStickersChooseActionTracker(this.this$0);
        L10:
            EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0).doSomeAction();
            return r12;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r2, RecyclerView.State r3, int r4) {
            LinearSmoothScrollerCustom r32 = new LinearSmoothScrollerCustom(r2.getContext(), 2);     // Catch: Exception -> L4
            r32.setTargetPosition(r4);     // Catch: Exception -> L4
            startSmoothScroll(r32);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$2, reason: invalid class name */
    public class AnonymousClass2 implements ContentPreviewViewer.ContentPreviewViewerDelegate {
        final /* synthetic */ EmojiView this$0;

        public static /* synthetic */ void $r8$lambda$FASxy65HSvWQQGmCpolK7_To_PM(AnonymousClass2 r0, String r1) {
            r0.lambda$setAsBadge$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$GHIiFHwD4O_zzTOmI6fCgNPDviU(AnonymousClass2 r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$deleteSticker$7(r1, r2);
        }

        /* renamed from: $r8$lambda$M0gYwTNMDKDnJDsdhG-Aie1ML80, reason: not valid java name */
        public static /* synthetic */ void m10459$r8$lambda$M0gYwTNMDKDnJDsdhGAie1ML80(AnonymousClass2 r0, String r1) {
            r0.lambda$setAsBadge$2(r1);
        }

        /* renamed from: $r8$lambda$XOaIIa6e_OzUiot-B25jsQkrWgQ, reason: not valid java name */
        public static /* synthetic */ void m10460$r8$lambda$XOaIIa6e_OzUiotB25jsQkrWgQ(AnonymousClass2 r0, TLRPC.EmojiStatus r1) {
            r0.lambda$setAsEmojiStatus$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$ZxixVt06ojrEsxy_2mVvPamzssE(AnonymousClass2 r0, String r1, TLRPC.Document r2, Runnable r3) {
            r0.lambda$setAsBadge$4(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$bDiqQ2MVFaoNsqhNd3lY-slN8h4, reason: not valid java name */
        public static /* synthetic */ void m10461$r8$lambda$bDiqQ2MVFaoNsqhNd3lYslN8h4(AnonymousClass2 r0, TLRPC.Document r1, Runnable r2, String r3) {
            r0.lambda$setAsBadge$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$qcbUbV86ovQUUUtKBpNQftjzlKA(AnonymousClass2 r0, TLObject r1) {
            r0.lambda$deleteSticker$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$yZJ5zPS5FnWYTCqUFId8FaG15Ak(AnonymousClass2 r0, BadgeDTO r1) {
            r0.lambda$setAsBadge$3(r1);
        }

        public AnonymousClass2(EmojiView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$deleteSticker$6(TLObject r2) {
            if ((r2 instanceof TLRPC.TL_messages_stickerSet) == false) goto L6;
            TLRPC.TL_messages_stickerSet r22 = (TLRPC.TL_messages_stickerSet) r2;
            MediaDataController.getInstance(this.this$0.currentAccount).putStickerSet(r22);
            MediaDataController.getInstance(this.this$0.currentAccount).replaceStickerSet(r22);
            return;
        }

        private /* synthetic */ void lambda$deleteSticker$7(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new EmojiView$2$$ExternalSyntheticLambda5(this, r1));
        }

        private /* synthetic */ void lambda$setAsBadge$1(String r2) {
            if (r2 != null) goto L4;
        L7:
            BaseFragment r22 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            EmojiView r0 = this.this$0;
            if (r22 == null) goto L11;
            BulletinFactory.of(EmojiView.m10335$$Nest$fgetfragment(r0)).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L11:
            BulletinFactory.of(EmojiView.m10301$$Nest$fgetbulletinContainer(r0), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L4:
            if (r2.equals("ok") == false) goto L7;
        }

        private /* synthetic */ void lambda$setAsBadge$2(String r2) {
            AndroidUtilities.runOnUIThread(new EmojiView$2$$ExternalSyntheticLambda7(this, r2));
        }

        private /* synthetic */ void lambda$setAsBadge$3(BadgeDTO r3) {
            BadgesController.INSTANCE.updateBadge(r3, new EmojiView$2$$ExternalSyntheticLambda4(this));
        }

        private /* synthetic */ void lambda$setAsBadge$4(String r2, TLRPC.Document r3, Runnable r4) {
            if (r2 != null) goto L4;
        L12:
            BaseFragment r22 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            EmojiView r32 = this.this$0;
            if (r22 == null) goto L16;
            BulletinFactory.of(EmojiView.m10335$$Nest$fgetfragment(r32)).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L16:
            BulletinFactory.of(EmojiView.m10301$$Nest$fgetbulletinContainer(r32), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0)).createErrorBulletin(LocaleController.getString(R.string.UnknownError)).show();
            return;
        L4:
            if (r2.equals("ok") == false) goto L12;
            BaseFragment r23 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            EmojiView r0 = this.this$0;
            if (r23 == null) goto L10;
            BulletinFactory.of(EmojiView.m10335$$Nest$fgetfragment(r0)).createEmojiBulletin(r3, LocaleController.getString(R.string.SetAsBadgeStatusInfo), LocaleController.getString(R.string.UndoNoCaps), r4).show();
            return;
        L10:
            BulletinFactory.of(EmojiView.m10301$$Nest$fgetbulletinContainer(r0), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0)).createEmojiBulletin(r3, LocaleController.getString(R.string.SetAsBadgeStatusInfo), LocaleController.getString(R.string.UndoNoCaps), r4).show();
        }

        private /* synthetic */ void lambda$setAsBadge$5(TLRPC.Document r2, Runnable r3, String r4) {
            AndroidUtilities.runOnUIThread(new EmojiView$2$$ExternalSyntheticLambda6(this, r4, r2, r3));
        }

        private /* synthetic */ void lambda$setAsEmojiStatus$0(TLRPC.EmojiStatus r1) {
            MessagesController.getInstance(this.this$0.currentAccount).updateEmojiStatus(r1);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void addCaptionToGif(Object r9, Object r10, boolean r11, int r12, int r13) {
            if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter() != EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0)) goto L5;
        L8:
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onGifSelectedForAddCaption(null, r9, null, r10, r11, r12, r13);
            return;
        L5:
            if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter() == EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)) goto L8;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean can() {
            if (EmojiView.m10335$$Nest$fgetfragment(this.this$0) == null) goto L5;
            return true;
        L5:
            if (EmojiView.m10374$$Nest$fgetshouldDrawBackground(this.this$0) == false) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean canAddCaption(TLRPC.Document r1) {
            return EmojiView.m10307$$Nest$fgetdelegate(this.this$0).canAddCaptionToGif(r1);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean canEditSticker() {
            return true;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean canSchedule() {
            return EmojiView.m10307$$Nest$fgetdelegate(this.this$0).canSchedule();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public Boolean canSetAsStatus(TLRPC.Document r3) {
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L5;
            return null;
        L5:
            TLRPC.User r2 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
            if (r2 != null) goto L8;
            return null;
        L8:
            Long r22 = UserObject.getEmojiStatusDocumentId(r2);
            if (r3 == null) goto L14;
            if (r22 != null) goto L12;
        L13:
            boolean r23 = true;
        L16:
            return Boolean.valueOf(r23);
        L12:
            if (r22.longValue() != r3.id) goto L13;
        L14:
            r23 = false;
            goto L16
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void copyEmoji(TLRPC.Document r5) {
            SpannableStringBuilder r0 = SpannableStringBuilder.valueOf(MessageObject.findAnimatedEmojiEmoticon(r5));
            r0.setSpan(new AnimatedEmojiSpan(r5, null), 0, r0.length(), 33);
            if (AndroidUtilities.addToClipboard(r0) == false) goto L10;
            BaseFragment r52 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            EmojiView r02 = this.this$0;
            if (r52 == null) goto L7;
            BulletinFactory r4 = BulletinFactory.of(EmojiView.m10335$$Nest$fgetfragment(r02));
        L8:
            r4.createCopyBulletin(LocaleController.getString(R.string.EmojiCopied)).show();
            return;
        L7:
            r4 = BulletinFactory.of(EmojiView.m10301$$Nest$fgetbulletinContainer(r02), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            goto L8
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void deleteSticker(TLRPC.Document r3) {
            TLRPC.TL_stickers_removeStickerFromSet r0 = new TLRPC.TL_stickers_removeStickerFromSet();
            r0.sticker = MediaDataController.getInputStickerSetItem(r3, _UrlKt.FRAGMENT_ENCODE_SET).document;
            ConnectionsManager.getInstance(this.this$0.currentAccount).sendRequest(r0, new EmojiView$2$$ExternalSyntheticLambda3(this));
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void editSticker(TLRPC.Document r4) {
            int r0 = 0;
        L4:
            if (r0 >= r4.attributes.size()) goto L11;
            TLRPC.DocumentAttribute r1 = r4.attributes.get(r0);
            if ((r1 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L10;
            TLRPC.InputStickerSet r12 = r1.stickerset;
            if (r12 == null) goto L10;
        L12:
            TLRPC.TL_messages_stickerSet r02 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSet(r12, true);
            StickersAlert.editSticker(EmojiView.m10335$$Nest$fgetfragment(this.this$0), r02, r4);
            return;
        L10:
            r0 = r0 + 1;
            goto L4
        L11:
            r12 = null;
            goto L12
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public long getDialogId() {
            return EmojiView.m10307$$Nest$fgetdelegate(this.this$0).getDialogId();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public String getQuery(boolean r3) {
            EmojiView r0 = this.this$0;
            if (r3 == false) goto L10;
            if (EmojiView.m10341$$Nest$fgetgifGridView(r0).getAdapter() == EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)) goto L7;
            return null;
        L7:
            return GifAdapter.m10506$$Nest$fgetlastSearchImageString(EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0));
        L10:
            if (EmojiView.m10314$$Nest$fgetemojiGridView(r0).getAdapter() == EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0)) goto L12;
            return null;
        L12:
            return EmojiSearchAdapter.m10487$$Nest$fgetlastSearchEmojiString(EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0));
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void gifAddedOrDeleted() {
            EmojiView.m10456$$Nest$mupdateRecentGifs(this.this$0);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean isInScheduleMode() {
            return EmojiView.m10307$$Nest$fgetdelegate(this.this$0).isInScheduleMode();
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needCopy(TLRPC.Document r1) {
            return true;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needRemoveFromRecent(TLRPC.Document r4) {
            if (r4 != null) goto L4;
            return false;
        L4:
            if (Emoji.recentEmoji.contains("animated_" + r4.id) == false) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needSend(int r3) {
            if (r3 == 2) goto L5;
            return true;
        L5:
            if ((EmojiView.m10335$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == true) goto L7;
            return false;
        L7:
            if (((ChatActivity) EmojiView.m10335$$Nest$fgetfragment(this.this$0)).canSendMessage() == true) goto L9;
            return false;
        L9:
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L11;
        L16:
            return true;
        L11:
            if (((ChatActivity) EmojiView.m10335$$Nest$fgetfragment(this.this$0)).getCurrentUser() == null) goto L15;
            if (UserObject.isUserSelf(((ChatActivity) EmojiView.m10335$$Nest$fgetfragment(this.this$0)).getCurrentUser()) == true) goto L16;
        L15:
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == true) goto L16;
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public boolean needShowEmojiSet(TLRPC.Document r1) {
            if (r1 != null) goto L4;
            return false;
        L4:
            if (MessageObject.getInputStickerSet(r1) == null) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void openSet(TLRPC.InputStickerSet r2, boolean r3) {
            if (r2 != null) goto L4;
            return;
        L4:
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onShowStickerSet(null, r2, false);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void removeFromRecent(TLRPC.Document r4) {
            if (r4 == null) goto L7;
            Emoji.removeRecentEmoji("animated_" + r4.id);
            if (EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0) == null) goto L8;
            EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0).notifyDataSetChanged();
            EmojiView.m10452$$Nest$msyncRecentEmojiVersion(this.this$0);
            return;
        L8:
            return;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void resetTouch() {
            if (EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0) == null) goto L6;
            EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).clearAllTouches();
            return;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void sendEmoji(TLRPC.Document r3) {
            if ((EmojiView.m10335$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == false) goto L6;
            ((ChatActivity) EmojiView.m10335$$Nest$fgetfragment(this.this$0)).sendAnimatedEmoji(r3, true, 0);
            return;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void sendGif(Object r17, Object r18, boolean r19, int r20, int r21) {
            RecyclerView.Adapter r1 = EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter();
            GifAdapter r2 = EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0);
            EmojiView r3 = this.this$0;
            if (r1 != r2) goto L7;
            EmojiView.m10307$$Nest$fgetdelegate(r3).onGifSelected(null, r17, null, r18, r19, r20, r21);
            return;
        L7:
            if (EmojiView.m10341$$Nest$fgetgifGridView(r3).getAdapter() != EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)) goto L10;
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onGifSelected(null, r17, null, r18, r19, r20, r21);
            return;
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void sendSticker(TLRPC.Document r10, String r11, Object r12, boolean r13, int r14, int r15) {
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickerSelected(null, r10, r11, r12, null, r13, r14, 0);
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void setAsBadge(TLRPC.Document r7) {
            if (r7 != null) goto L4;
            return;
        L4:
            BadgesController r0 = BadgesController.INSTANCE;
            BadgeDTO r1 = r0.getBadge();
            EmojiView$2$$ExternalSyntheticLambda1 r2 = new EmojiView$2$$ExternalSyntheticLambda1(this, r1);
            long r4 = r7.id;
            if (r1 == null) goto L7;
            String r12 = r1.getText();
        L8:
            r0.updateBadge(new BadgeDTO(r4, r12), new EmojiView$2$$ExternalSyntheticLambda2(this, r7, r2));
            return;
        L7:
            r12 = null;
            goto L8
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void setAsEmojiStatus(TLRPC.Document r6, Integer r7) {
            if (r6 != null) goto L5;
            TLRPC.EmojiStatus r72 = new TLRPC.TL_emojiStatusEmpty();
        L9:
            TLRPC.User r1 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
            if (r1 != null) goto L12;
            TLRPC.EmojiStatus r12 = new TLRPC.TL_emojiStatusEmpty();
        L13:
            MessagesController.getInstance(this.this$0.currentAccount).updateEmojiStatus(r72);
            EmojiView$2$$ExternalSyntheticLambda0 r73 = new EmojiView$2$$ExternalSyntheticLambda0(this, r12);
            if (r6 != null) goto L21;
            Bulletin.SimpleLayout r62 = new Bulletin.SimpleLayout(this.this$0.getContext(), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            r62.textView.setText(LocaleController.getString(R.string.RemoveStatusInfo));
            r62.imageView.setImageResource(R.drawable.msg_settings_premium);
            r62.imageView.setScaleX(0.8f);
            r62.imageView.setScaleY(0.8f);
            r62.imageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_verifiedBackground, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0)), PorterDuff.Mode.MULTIPLY));
            Bulletin.UndoButton r13 = new Bulletin.UndoButton(this.this$0.getContext(), true, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            r13.setUndoAction(r73);
            r62.setButton(r13);
            BaseFragment r74 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            EmojiView r5 = this.this$0;
            if (r74 == null) goto L19;
            Bulletin.make(EmojiView.m10335$$Nest$fgetfragment(r5), r62, 1500).show();
            return;
        L19:
            Bulletin.make(EmojiView.m10301$$Nest$fgetbulletinContainer(r5), r62, 1500).show();
            return;
        L21:
            BaseFragment r0 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            EmojiView r14 = this.this$0;
            if (r0 == null) goto L24;
            BulletinFactory r52 = BulletinFactory.of(EmojiView.m10335$$Nest$fgetfragment(r14));
        L25:
            r52.createEmojiBulletin(r6, LocaleController.getString(R.string.SetAsEmojiStatusInfo), LocaleController.getString(R.string.UndoNoCaps), r73).show();
            return;
        L24:
            r52 = BulletinFactory.of(EmojiView.m10301$$Nest$fgetbulletinContainer(r14), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            goto L25
        L12:
            r12 = r1.emoji_status;
            goto L13
        L5:
            TLRPC.TL_emojiStatus r15 = new TLRPC.TL_emojiStatus();
            r15.document_id = r6.id;
            if (r7 == null) goto L8;
            r15.flags |= 1;
            r15.until = r7.intValue();
        L8:
            r72 = r15;
            goto L9
        }

        @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
        public void showEmojiSet(TLRPC.Document r2) {
            EmojiView.m10435$$Nest$mopenEmojiPackAlert(this.this$0, MessageObject.getInputStickerSet(r2), r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$20, reason: invalid class name */
    public class AnonymousClass20 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass20(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r4) {
            if (EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0).getAdapter() != EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)) goto L18;
            EmojiView r0 = this.this$0;
            if (r4 != 0) goto L9;
            return StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(r0));
        L9:
            if (r4 == StickersGridAdapter.m10551$$Nest$fgettotalItems(EmojiView.m10380$$Nest$fgetstickersGridAdapter(r0))) goto L16;
            if (StickersGridAdapter.m10549$$Nest$fgetcache(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)).get(r4) != null) goto L13;
        L14:
            return 1;
        L13:
            if ((StickersGridAdapter.m10549$$Nest$fgetcache(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)).get(r4) instanceof TLRPC.Document) == true) goto L14;
        L16:
            return StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0));
        L18:
            if (r4 == StickersSearchGridAdapter.m10559$$Nest$fgettotalItems(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0))) goto L25;
            if (StickersSearchGridAdapter.m10552$$Nest$fgetcache(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0)).get(r4) != null) goto L22;
        L23:
            return 1;
        L22:
            if ((StickersSearchGridAdapter.m10552$$Nest$fgetcache(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0)).get(r4) instanceof TLRPC.Document) == true) goto L23;
        L25:
            return StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$21, reason: invalid class name */
    public class AnonymousClass21 extends SearchField {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass21(EmojiView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (r2 == getTranslationY()) goto L6;
            super.setTranslationY(r2);
            EmojiView.m10378$$Nest$fgetstickersContainer(this.this$0).invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$22, reason: invalid class name */
    public class AnonymousClass22 extends DraggableScrollSlidingTabStrip {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ boolean val$shouldDrawBackground;

        public static /* synthetic */ void $r8$lambda$Iy7_nznk8kyMazdGgXOlPGXDWDs(TLObject r0, TLRPC.TL_error r1) {
        }

        /* renamed from: $r8$lambda$RADhpOn-u_y-DVkC8RVNteK4XjI, reason: not valid java name */
        public static /* synthetic */ int m10462$r8$lambda$RADhpOnu_yDVkC8RVNteK4XjI(AnonymousClass22 r0, TLRPC.TL_messages_stickerSet r1, TLRPC.TL_messages_stickerSet r2) {
            return r0.lambda$stickerSetPositionChanged$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$wRdmQ94jfZd2yLCwA9CvpymEEz8(BaseFragment r3) {
            r3.presentFragment(new StickersActivity(0, null));
        }

        public AnonymousClass22(EmojiView r1, Context r2, Theme.ResourcesProvider r3, BaseFragment r4, boolean r5) {
            this.this$0 = r1;
            this.val$fragment = r4;
            this.val$shouldDrawBackground = r5;
            super(r1, r2, r3);
        }

        private /* synthetic */ int lambda$stickerSetPositionChanged$0(TLRPC.TL_messages_stickerSet r2, TLRPC.TL_messages_stickerSet r3) {
            int r22 = EmojiView.m10376$$Nest$fgetstickerSets(this.this$0).indexOf(r2);
            int r1 = EmojiView.m10376$$Nest$fgetstickerSets(this.this$0).indexOf(r3);
            if (r22 < 0) goto L7;
            if (r1 >= 0) goto L6;
            return 0;
        L6:
            return r22 - r1;
        L7:
            return 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v2, types: [int] */
        /* JADX WARN: Type inference failed for: r2v8 */
        /* JADX WARN: Type inference failed for: r2v9 */
        /* JADX WARN: Type inference failed for: r4v1, types: [java.util.ArrayList] */
        private void sendReorder() {
            MediaDataController.getInstance(this.this$0.currentAccount).calcNewHash(0);
            TLRPC.TL_messages_reorderStickerSets r0 = new TLRPC.TL_messages_reorderStickerSets();
            r0.masks = false;
            r0.emojis = false;
            ?? r2 = EmojiView.m10355$$Nest$fgethasChatStickers(this.this$0);
        L4:
            if (r2 >= EmojiView.m10376$$Nest$fgetstickerSets(this.this$0).size()) goto L6;
            r0.order.add(Long.valueOf(((TLRPC.TL_messages_stickerSet) EmojiView.m10376$$Nest$fgetstickerSets(this.this$0).get(r2)).set.id));
            r2 = r2 + 1;
            goto L4
        L6:
            ConnectionsManager.getInstance(this.this$0.currentAccount).sendRequest(r0, new EmojiView$22$$ExternalSyntheticLambda2());
            NotificationCenter.getInstance(this.this$0.currentAccount).postNotificationName(NotificationCenter.stickersDidLoad, new Object[]{0, Boolean.TRUE});
        }

        private void swapListElements(List<TLRPC.TL_messages_stickerSet> r1, int r2, int r3) {
            r1.add(r3, r1.remove(r2));
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTabStrip
        public void invalidateOverlays() {
            if (EmojiView.m10386$$Nest$fgetstickersTabContainer(this.this$0) == null) goto L6;
            EmojiView.m10386$$Nest$fgetstickersTabContainer(this.this$0).invalidate();
            return;
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (getTranslationY() == r2) goto L8;
            super.setTranslationY(r2);
            if (this.val$shouldDrawBackground == true) goto L9;
            EmojiView.m10378$$Nest$fgetstickersContainer(this.this$0).invalidate();
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTabStrip
        public void stickerSetPositionChanged(int r7, int r8) {
            int r72 = r7 - EmojiView.m10387$$Nest$fgetstickersTabOffset(this.this$0);
            int r82 = r8 - EmojiView.m10387$$Nest$fgetstickersTabOffset(this.this$0);
            MediaDataController r0 = MediaDataController.getInstance(this.this$0.currentAccount);
            swapListElements(EmojiView.m10376$$Nest$fgetstickerSets(this.this$0), r72, r82);
            Collections.sort(r0.getStickerSets(0), new EmojiView$22$$ExternalSyntheticLambda0(this));
            ArrayList<TLRPC.TL_messages_stickerSet> r73 = this.this$0.frozenStickerSets;
            if (r73 == null) goto L5;
            r73.clear();
            EmojiView r74 = this.this$0;
            r74.frozenStickerSets.addAll(EmojiView.m10376$$Nest$fgetstickerSets(r74));
        L5:
            EmojiView.m10440$$Nest$mreloadStickersAdapter(this.this$0);
            AndroidUtilities.cancelRunOnUIThread(EmojiView.m10302$$Nest$fgetcheckExpandStickerTabsRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(EmojiView.m10302$$Nest$fgetcheckExpandStickerTabsRunnable(this.this$0), 1500);
            sendReorder();
            EmojiView.m10457$$Nest$mupdateStickerTabs(this.this$0, true);
            if (SharedConfig.updateStickersOrderOnSend == false) goto L15;
            SharedConfig.toggleUpdateStickersOrderOnSend();
            BaseFragment r75 = this.val$fragment;
            if (r75 == null) goto L12;
            BulletinFactory.of(r75).createSimpleBulletin(R.raw.filter_reorder, LocaleController.getString(R.string.DynamicPackOrderOff), LocaleController.getString(R.string.DynamicPackOrderOffInfo), LocaleController.getString("Settings"), new EmojiView$22$$ExternalSyntheticLambda1(this.val$fragment)).show();
            return;
        L12:
            if (EmojiView.m10301$$Nest$fgetbulletinContainer(this.this$0) == null) goto L16;
            BulletinFactory.of(EmojiView.m10301$$Nest$fgetbulletinContainer(this.this$0), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0)).createSimpleBulletin(R.raw.filter_reorder, LocaleController.getString(R.string.DynamicPackOrderOff), LocaleController.getString(R.string.DynamicPackOrderOffInfo)).show();
            return;
        L16:
            return;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTabStrip
        public void updatePosition() {
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
            if (EmojiView.m10386$$Nest$fgetstickersTabContainer(this.this$0) == null) goto L5;
            EmojiView.m10386$$Nest$fgetstickersTabContainer(this.this$0).invalidate();
        L5:
            invalidate();
            if (EmojiView.m10307$$Nest$fgetdelegate(this.this$0) == null) goto L9;
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).invalidateEnterView();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$23, reason: invalid class name */
    public class AnonymousClass23 extends FrameLayout {
        Paint paint;
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass23(EmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            float r1 = AndroidUtilities.dp(50.0f) * EmojiView.m10307$$Nest$fgetdelegate(this.this$0).getProgressToSearchOpened();
            if (r1 <= getMeasuredHeight()) goto L5;
            return;
        L5:
            r8.save();
            if (r1 == 0.0f) goto L8;
            r8.clipRect(0.0f, r1, getMeasuredWidth(), getMeasuredHeight());
        L8:
            this.paint.setColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$0, Theme.key_chat_emojiPanelBackground));
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.dp(36.0f) + EmojiView.m10385$$Nest$fgetstickersTab(this.this$0).getExpandedOffset(), this.paint);
            super.dispatchDraw(r8);
            EmojiView.m10385$$Nest$fgetstickersTab(this.this$0).drawOverlays(r8);
            r8.restore();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$24, reason: invalid class name */
    public class AnonymousClass24 extends ViewPager {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass24(EmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (EmojiView.m10356$$Nest$fgetignorePagerScroll(this.this$0) == false) goto L6;
            return false;
        L6:
            if (getParent() == null) goto L11;
            getParent().requestDisallowInterceptTouchEvent(canScrollHorizontally(-1));
        L11:
            return super.onInterceptTouchEvent(r4);
        L10:
            return false;
        }

        @Override // androidx.viewpager.widget.ViewPager
        public void setCurrentItem(int r6, boolean r7) {
            EmojiView r0 = this.this$0;
            if (r6 != 1) goto L5;
            boolean r3 = true;
        L6:
            EmojiView.m10450$$Nest$mstartStopVisibleGifs(r0, r3);
            if (r6 != getCurrentItem()) goto L19;
            if (r6 != 0) goto L13;
            EmojiView.m10388$$Nest$fgettabsMinusDy(this.this$0)[1] = 0;
            ObjectAnimator r62 = ObjectAnimator.ofFloat(EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0), ViewGroup.TRANSLATION_Y, new float[]{0.0f});
            r62.setDuration(150);
            r62.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r62.start();
            EmojiView.m10443$$Nest$mscrollEmojisToPosition(this.this$0, 1, 0);
            if (EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0) == null) goto L21;
            EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0).select(0);
            return;
        L21:
            return;
        L13:
            EmojiView r5 = this.this$0;
            if (r6 != 1) goto L17;
            EmojiView.m10341$$Nest$fgetgifGridView(r5).smoothScrollToPosition(0);
            return;
        L17:
            EmojiView.m10381$$Nest$fgetstickersGridView(r5).smoothScrollToPosition(1);
            return;
        L19:
            super.setCurrentItem(r6, r7);
            return;
        L5:
            r3 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$25, reason: invalid class name */
    public class AnonymousClass25 extends ImageView {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass25(EmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L6;
            EmojiView.m10396$$Nest$fputbackspacePressed(this.this$0, true);
            EmojiView.m10395$$Nest$fputbackspaceOnce(this.this$0, false);
            EmojiView.m10439$$Nest$mpostBackspaceRunnable(this.this$0, 350);
        L16:
            super.onTouchEvent(r5);
            return true;
        L6:
            if (r5.getAction() != 3) goto L8;
        L9:
            EmojiView.m10396$$Nest$fputbackspacePressed(this.this$0, false);
            if (EmojiView.m10299$$Nest$fgetbackspaceOnce(this.this$0) == true) goto L16;
            if (EmojiView.m10307$$Nest$fgetdelegate(this.this$0) == null) goto L16;
            if (EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onBackspace() == false) goto L16;
            EmojiView.m10298$$Nest$fgetbackspaceButton(this.this$0).performHapticFeedback(3);     // Catch: Exception -> L18
            goto L16
        L8:
            if (r5.getAction() != 1) goto L16;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$26, reason: invalid class name */
    public class AnonymousClass26 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ boolean val$shouldDrawBackground;

        public AnonymousClass26(EmojiView r1, boolean r2) {
            this.this$0 = r1;
            this.val$shouldDrawBackground = r2;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r7, float r8, int r9) {
            EmojiView.m10424$$Nest$mcheckGridVisibility(this.this$0, r7, r8);
            EmojiView r0 = this.this$0;
            EmojiView.m10434$$Nest$monPageScrolled(r0, r7, (r0.getMeasuredWidth() - this.this$0.getPaddingLeft()) - this.this$0.getPaddingRight(), r9);
            boolean r02 = true;
            EmojiView.m10448$$Nest$mshowBottomTab(this.this$0, true, true);
            int r92 = EmojiView.m10363$$Nest$fgetpager(this.this$0).getCurrentItem();
            if (r92 != 0) goto L5;
            SearchField r93 = EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0);
        L9:
            String r1 = SearchField.m10538$$Nest$fgetsearchEditText(r93).getText().toString();
            int r3 = 0;
        L11:
            if (r3 >= 3) goto L27;
            if (r3 != 0) goto L14;
            SearchField r4 = EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0);
        L18:
            if (r4 == null) goto L26;
            if (r4 == r93) goto L26;
            if (SearchField.m10538$$Nest$fgetsearchEditText(r4) == null) goto L26;
            if (SearchField.m10538$$Nest$fgetsearchEditText(r4).getText().toString().equals(r1) == true) goto L26;
            SearchField.m10538$$Nest$fgetsearchEditText(r4).setText(r1);
            SearchField.m10538$$Nest$fgetsearchEditText(r4).setSelection(r1.length());
        L26:
            r3 = r3 + 1;
            goto L11
        L14:
            EmojiView r42 = this.this$0;
            if (r3 != 1) goto L17;
            r4 = EmojiView.m10345$$Nest$fgetgifSearchField(r42);
            goto L18
        L17:
            r4 = EmojiView.m10383$$Nest$fgetstickersSearchField(r42);
            goto L18
        L27:
            EmojiView r94 = this.this$0;
            if (r7 == 0) goto L30;
        L31:
            if (r7 == 1) goto L34;
            r02 = false;
        L34:
            EmojiView.m10450$$Nest$mstartStopVisibleGifs(r94, r02);
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
            return;
        L30:
            if (r8 > 0.0f) goto L34;
        L5:
            EmojiView r12 = this.this$0;
            if (r92 != 1) goto L8;
            r93 = EmojiView.m10345$$Nest$fgetgifSearchField(r12);
            goto L9
        L8:
            r93 = EmojiView.m10383$$Nest$fgetstickersSearchField(r12);
            goto L9
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r5) {
            EmojiView.m10442$$Nest$msaveNewPage(this.this$0);
            EmojiView r0 = this.this$0;
            boolean r1 = false;
            if (r5 != 0) goto L5;
            boolean r3 = true;
        L6:
            EmojiView.m10447$$Nest$mshowBackspaceButton(r0, r3, true);
            EmojiView r02 = this.this$0;
            if (r5 == 2) goto L9;
        L13:
            EmojiView.m10449$$Nest$mshowStickerSettingsButton(r02, r1, true);
            if (EmojiView.m10307$$Nest$fgetdelegate(this.this$0).isSearchOpened() == false) goto L30;
            if (r5 == 0) goto L17;
            EmojiView r03 = this.this$0;
            if (r5 != 1) goto L27;
            if (EmojiView.m10345$$Nest$fgetgifSearchField(r03) == null) goto L32;
            SearchField.m10538$$Nest$fgetsearchEditText(EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0)).requestFocus();
            return;
        L32:
            return;
        L27:
            if (EmojiView.m10383$$Nest$fgetstickersSearchField(r03) == null) goto L33;
            SearchField.m10538$$Nest$fgetsearchEditText(EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$0)).requestFocus();
            return;
        L33:
            return;
        L17:
            if (EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0) == null) goto L31;
            SearchField.m10538$$Nest$fgetsearchEditText(EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0)).requestFocus();
            return;
        L31:
            return;
        L30:
            return;
        L9:
            if (this.val$shouldDrawBackground == false) goto L11;
        L12:
            r1 = true;
            goto L13
        L11:
            if (r02.shouldDrawStickerSettings == false) goto L13;
        L5:
            r3 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$27, reason: invalid class name */
    public class AnonymousClass27 extends ChooseStickerActionTracker {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass27(EmojiView r1, int r2, long r3, long r5) {
            this.this$0 = r1;
            super(r2, r3, r5);
        }

        @Override // org.telegram.ui.Components.EmojiView.ChooseStickerActionTracker
        public boolean isShown() {
            if (EmojiView.m10307$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (this.this$0.getVisibility() == 0) goto L7;
            return false;
        L7:
            if (EmojiView.m10379$$Nest$fgetstickersContainerAttached(this.this$0) == false) goto L13;
            return true;
        L13:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$28, reason: invalid class name */
    public class AnonymousClass28 extends EmojiPacksAlert {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ TLRPC.InputStickerSet val$set;

        public AnonymousClass28(EmojiView r1, BaseFragment r2, Context r3, Theme.ResourcesProvider r4, ArrayList r5, TLRPC.InputStickerSet r6) {
            this.this$0 = r1;
            this.val$set = r6;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.EmojiPacksAlert, org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            EmojiView.m10399$$Nest$fputemojiPackAlertOpened(this.this$0, false);
            super.dismiss();
        }

        @Override // org.telegram.ui.Components.EmojiPacksAlert
        public void onButtonClicked(boolean r4) {
            TLRPC.InputStickerSet r0 = this.val$set;
            if ((r0 instanceof TLRPC.TL_inputStickerSetID) == false) goto L12;
            long r02 = ((TLRPC.TL_inputStickerSetID) r0).id;
            EmojiView r2 = this.this$0;
            if (r4 == true) goto L7;
            r2.installedEmojiSets.remove(Long.valueOf(r02));
        L10:
            EmojiView.m10453$$Nest$mupdateEmojiHeaders(this.this$0);
            return;
        L7:
            if (r2.installedEmojiSets.contains(Long.valueOf(r02)) == true) goto L10;
            this.this$0.installedEmojiSets.add(Long.valueOf(r02));
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$29, reason: invalid class name */
    public class AnonymousClass29 extends TrendingStickersLayout.Delegate {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass29(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public boolean canSchedule() {
            return EmojiView.m10307$$Nest$fgetdelegate(this.this$0).canSchedule();
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public boolean canSendSticker() {
            return true;
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public String[] getLastSearchKeyboardLanguage() {
            return EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(this.this$0);
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public boolean isInScheduleMode() {
            return EmojiView.m10307$$Nest$fgetdelegate(this.this$0).isInScheduleMode();
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public boolean onListViewInterceptTouchEvent(RecyclerListView r7, MotionEvent r8) {
            return ContentPreviewViewer.getInstance().onInterceptTouchEvent(r8, r7, this.this$0.getMeasuredHeight(), EmojiView.m10305$$Nest$fgetcontentPreviewViewerDelegate(this.this$0), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public boolean onListViewTouchEvent(RecyclerListView r8, RecyclerListView.OnItemClickListener r9, MotionEvent r10) {
            return ContentPreviewViewer.getInstance().onTouch(r10, r8, this.this$0.getMeasuredHeight(), r9, EmojiView.m10305$$Nest$fgetcontentPreviewViewerDelegate(this.this$0), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public void onStickerSelected(TLRPC.Document r10, Object r11, boolean r12, boolean r13, int r14) {
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickerSelected(null, r10, null, r11, null, r13, r14, 0);
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public void onStickerSetAdd(TLRPC.StickerSetCovered r2, boolean r3) {
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickerSetAdd(r2);
            if (r3 == false) goto L6;
            EmojiView.m10457$$Nest$mupdateStickerTabs(this.this$0, true);
            return;
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public void onStickerSetRemove(TLRPC.StickerSetCovered r1) {
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickerSetRemove(r1);
        }

        @Override // org.telegram.ui.Components.TrendingStickersLayout.Delegate
        public void setLastSearchKeyboardLanguage(String[] r1) {
            EmojiView.m10412$$Nest$fputlastSearchKeyboardLanguage(this.this$0, r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$3, reason: invalid class name */
    public class AnonymousClass3 extends ChatActivity {
        final /* synthetic */ EmojiView this$0;

        /* renamed from: $r8$lambda$eEM_r91VGNgQTiG0G5xlXl7-d2M, reason: not valid java name */
        public static /* synthetic */ void m10463$r8$lambda$eEM_r91VGNgQTiG0G5xlXl7d2M(AnonymousClass3 r0) {
            r0.lambda$onTransitionAnimationEnd$0();
        }

        public AnonymousClass3(EmojiView r1, Bundle r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onTransitionAnimationEnd$0() {
            if (this.chatActivityEnterView.getEmojiView() == null) goto L6;
            this.chatActivityEnterView.getEmojiView().scrollEmojisToAnimated();
            return;
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onTransitionAnimationEnd(boolean r3, boolean r4) {
            super.onTransitionAnimationEnd(r3, r4);
            if (r3 == false) goto L8;
            ChatActivityEnterView r32 = this.chatActivityEnterView;
            if (r32 == null) goto L9;
            r32.showEmojiView();
            this.chatActivityEnterView.postDelayed(new EmojiView$3$$ExternalSyntheticLambda0(this), 100);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$30, reason: invalid class name */
    public class AnonymousClass30 extends AnimatorListenerAdapter {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ RecyclerListView val$gridView;

        public AnonymousClass30(EmojiView r1, RecyclerListView r2) {
            this.this$0 = r1;
            this.val$gridView = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(EmojiView.m10372$$Nest$fgetsearchAnimation(this.this$0)) == false) goto L6;
            EmojiView.m10414$$Nest$fputsearchAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            if (r5.equals(EmojiView.m10372$$Nest$fgetsearchAnimation(this.this$0)) == false) goto L15;
            this.val$gridView.setTranslationY(0.0f);
            RecyclerListView r52 = this.val$gridView;
            RecyclerListView r0 = EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0);
            RecyclerListView r1 = this.val$gridView;
            if (r52 != r0) goto L7;
            r1.setPadding(0, 0, 0, EmojiView.m10300$$Nest$fgetbottomInset(this.this$0));
        L13:
            EmojiView.m10414$$Nest$fputsearchAnimation(this.this$0, null);
            return;
        L7:
            EmojiGridView r53 = EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0);
            RecyclerListView r02 = this.val$gridView;
            if (r1 != r53) goto L11;
            r02.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f), EmojiView.m10300$$Nest$fgetbottomInset(this.this$0));
            goto L13
        L11:
            if (r02 != EmojiView.m10341$$Nest$fgetgifGridView(this.this$0)) goto L13;
            this.val$gridView.setPadding(0, EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0), 0, EmojiView.m10300$$Nest$fgetbottomInset(this.this$0));
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$31, reason: invalid class name */
    public class AnonymousClass31 extends AnimatorListenerAdapter {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass31(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            EmojiView.m10401$$Nest$fputemojiTabShadowAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$32, reason: invalid class name */
    public class AnonymousClass32 extends LinearSmoothScrollerCustom {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass32(EmojiView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom
        public void onEnd() {
            EmojiView.m10400$$Nest$fputemojiSmoothScrolling(this.this$0, false);
        }

        @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public void onStart() {
            EmojiView.m10400$$Nest$fputemojiSmoothScrolling(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$33, reason: invalid class name */
    public class AnonymousClass33 extends AnimatorListenerAdapter {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ RecyclerListView val$gridView;
        final /* synthetic */ GridLayoutManager val$layoutManager;

        public AnonymousClass33(EmojiView r1, GridLayoutManager r2, RecyclerListView r3) {
            this.this$0 = r1;
            this.val$layoutManager = r2;
            this.val$gridView = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(EmojiView.m10372$$Nest$fgetsearchAnimation(this.this$0)) == false) goto L6;
            EmojiView.m10414$$Nest$fputsearchAnimation(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r8) {
            if (r8.equals(EmojiView.m10372$$Nest$fgetsearchAnimation(this.this$0)) == false) goto L18;
            int r82 = this.val$layoutManager.findFirstVisibleItemPosition();
            this.val$gridView.setTranslationY(0.0f);
            RecyclerListView r0 = this.val$gridView;
            RecyclerListView r1 = EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0);
            RecyclerListView r2 = this.val$gridView;
            if (r0 != r1) goto L7;
            r2.setPadding(0, AndroidUtilities.dp(36.0f), 0, AndroidUtilities.dp(44.0f) + EmojiView.m10300$$Nest$fgetbottomInset(this.this$0));
        L14:
            if (r82 == (-1)) goto L16;
            this.val$layoutManager.scrollToPositionWithOffset(r82, 0);
        L16:
            EmojiView.m10414$$Nest$fputsearchAnimation(this.this$0, null);
            return;
        L7:
            RecyclerListView r02 = EmojiView.m10341$$Nest$fgetgifGridView(this.this$0);
            RecyclerListView r12 = this.val$gridView;
            if (r2 != r02) goto L11;
            r12.setPadding(0, EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0), 0, AndroidUtilities.dp(44.0f) + EmojiView.m10300$$Nest$fgetbottomInset(this.this$0));
            goto L14
        L11:
            if (r12 != EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0)) goto L14;
            this.val$gridView.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(36.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(44.0f) + EmojiView.m10300$$Nest$fgetbottomInset(this.this$0));
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$34, reason: invalid class name */
    public class AnonymousClass34 extends AnimatorListenerAdapter {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass34(EmojiView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$show == true) goto L6;
            EmojiView.m10298$$Nest$fgetbackspaceButton(this.this$0).setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$35, reason: invalid class name */
    public class AnonymousClass35 extends AnimatorListenerAdapter {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass35(EmojiView r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$show == true) goto L6;
            EmojiView.m10377$$Nest$fgetstickerSettingsButton(this.this$0).setVisibility(4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$36, reason: invalid class name */
    public class AnonymousClass36 extends LinearSmoothScroller {
        final /* synthetic */ EmojiView this$0;
        final /* synthetic */ int val$tabsMinusDy;

        public AnonymousClass36(EmojiView r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$tabsMinusDy = r3;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int r1, int r2, int r3, int r4, int r5) {
            return super.calculateDtToFit(r1, r2, r3, r4, r5) + this.val$tabsMinusDy;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateTimeForDeceleration(int r1) {
            return super.calculateTimeForDeceleration(r1) * 16;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int getVerticalSnapPreference() {
            return -1;
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass4(EmojiView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (r6 != EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0)) goto L5;
        L9:
            r5.save();
            float r0 = (EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0).getY() + EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0).getMeasuredHeight()) + 1.0f;
            if (r6 == EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0)) goto L12;
        L14:
            r5.clipRect(0.0f, r0 - (AndroidUtilities.dp(16.0f) * EmojiView.m10296$$Nest$fgetanimatorSearchEmojiPackSelected(this.this$0).getFloatValue()), getMeasuredWidth(), getMeasuredHeight());
            boolean r4 = super.drawChild(r5, r6, r7);
            r5.restore();
            return r4;
        L12:
            if (EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0) == null) goto L14;
            r0 = Math.max(r0, (EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0).getY() + EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0).getMeasuredHeight()) + 1.0f);
            goto L14
        L5:
            if (r6 == EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0)) goto L9;
            return super.drawChild(r5, r6, r7);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$5, reason: invalid class name */
    public class AnonymousClass5 extends GridLayoutManager {
        final /* synthetic */ EmojiView this$0;

        /* renamed from: org.telegram.ui.Components.EmojiView$5$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScrollerCustom {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1, Context r2, int r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom
            public void onEnd() {
                EmojiView.m10400$$Nest$fputemojiSmoothScrolling(this.this$1.this$0, false);
            }
        }

        public AnonymousClass5(EmojiView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r2, RecyclerView.State r3, int r4) {
            AnonymousClass1 r32 = new AnonymousClass1(this, r2.getContext(), 2);     // Catch: Exception -> L4
            r32.setTargetPosition(r4);     // Catch: Exception -> L4
            startSmoothScroll(r32);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$6, reason: invalid class name */
    public class AnonymousClass6 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass6(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanGroupIndex(int r1, int r2) {
            return super.getSpanGroupIndex(r1, r2);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r5) {
            RecyclerView.Adapter r0 = EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getAdapter();
            EmojiSearchAdapter r1 = EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0);
            EmojiView r2 = this.this$0;
            if (r0 != r1) goto L17;
            int r52 = EmojiView.m10316$$Nest$fgetemojiSearchAdapter(r2).getItemViewType(r5);
            if (r52 == 1) goto L15;
            if (r52 == 3) goto L15;
            if (r52 == 2) goto L15;
            if (r52 == 4) goto L15;
            if (r52 == 5) goto L15;
        L30:
            return 1;
        L15:
            return EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount();
        L17:
            if (EmojiView.m10362$$Nest$fgetneedEmojiSearch(r2) == false) goto L20;
            if (r5 != 0) goto L20;
        L32:
            return EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount();
        L20:
            if (r5 == EmojiGridAdapter.m10476$$Nest$fgettrendingRow(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0))) goto L32;
            if (r5 == EmojiGridAdapter.m10475$$Nest$fgettrendingHeaderRow(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0))) goto L32;
            if (r5 == EmojiGridAdapter.m10472$$Nest$fgetrecentlyUsedHeaderRow(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0))) goto L32;
            if (EmojiGridAdapter.m10470$$Nest$fgetpositionToSection(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0)).indexOfKey(r5) >= 0) goto L32;
            if (EmojiGridAdapter.m10471$$Nest$fgetpositionToUnlock(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0)).indexOfKey(r5) < 0) goto L30;
            goto L30
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerAnimationScrollHelper.AnimationCallback {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass7(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void ignoreView(View r1, boolean r2) {
            if ((r1 instanceof ImageViewEmoji) == false) goto L6;
            ((ImageViewEmoji) r1).ignoring = r2;
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void onEndAnimation() {
            EmojiView.m10400$$Nest$fputemojiSmoothScrolling(this.this$0, false);
            EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).updateEmojiDrawables();
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void onPreAnimation() {
            EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).updateEmojiDrawables();
            EmojiView.m10400$$Nest$fputemojiSmoothScrolling(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$8, reason: invalid class name */
    public class AnonymousClass8 extends TypedScrollListener {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass8(EmojiView r1, int r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.EmojiView.TypedScrollListener, androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            if (r4 != 0) goto L4;
            EmojiView.m10400$$Nest$fputemojiSmoothScrolling(this.this$0, false);
        L4:
            super.onScrollStateChanged(r3, r4);
        }

        @Override // org.telegram.ui.Components.EmojiView.TypedScrollListener, androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r4, int r5, int r6) {
            EmojiView.m10454$$Nest$mupdateEmojiTabsPosition(this.this$0);
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L7:
            super.onScrolled(r4, r5, r6);
            if (EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0) != null) goto L10;
            return;
        L10:
            if (EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getAdapter() == EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0)) goto L12;
            return;
        L12:
            if (EmojiSearchAdapter.m10492$$Nest$fgetsearchRunnable(EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0)).isLoading() == false) goto L14;
            return;
        L14:
            if (EmojiSearchAdapter.m10492$$Nest$fgetsearchRunnable(EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0)).isCompleted() == false) goto L16;
            return;
        L16:
            if ((EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).findLastVisibleItemPosition() + 20) <= EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0).getItemCount()) goto L23;
            SearchRunnable r3 = EmojiSearchAdapter.m10492$$Nest$fgetsearchRunnable(EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0));
            Objects.requireNonNull(r3);
            AndroidUtilities.runOnUIThread(new EmojiView$18$$ExternalSyntheticLambda0(r3));
            return;
        L23:
            return;
        L5:
            if (EmojiView.m10371$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L7;
            EmojiView.m10371$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r5, r6);
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.EmojiView$9, reason: invalid class name */
    public class AnonymousClass9 extends EmojiTabsStrip {
        final /* synthetic */ EmojiView this$0;

        public AnonymousClass9(EmojiView r1, Context r2, Theme.ResourcesProvider r3, boolean r4, boolean r5, boolean r6, boolean r7, int r8, Runnable r9, boolean r10) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10);
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public boolean allowEmojisForNonPremium() {
            return EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0);
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public boolean doIncludeFeatured() {
            if (EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).size() > 0) goto L5;
            return true;
        L5:
            if (((TLRPC.StickerSetCovered) EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).get(0)).set != null) goto L7;
            return true;
        L7:
            if (MessagesController.getEmojiSettings(this.this$0.currentAccount).getLong("emoji_featured_hidden", 0) != ((TLRPC.StickerSetCovered) EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).get(0)).set.id) goto L9;
            return true;
        L9:
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L16;
            return false;
        L16:
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public ColorFilter getEmojiColorFilter() {
            return EmojiView.m10295$$Nest$fgetanimatedEmojiTextColorFilter(this.this$0);
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public boolean isInstalled(EmojiPack r3) {
            if (r3.installed == false) goto L5;
            return true;
        L5:
            if (this.this$0.installedEmojiSets.contains(Long.valueOf(r3.set.id)) == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public boolean onTabClick(int r9) {
            if (EmojiView.m10318$$Nest$fgetemojiSmoothScrolling(this.this$0) == false) goto L6;
            return false;
        L6:
            if (EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0) == null) goto L9;
            EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0).search(null);
        L9:
            if (EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0) != null) goto L11;
        L13:
            if (r9 != 0) goto L15;
            Integer r0 = Integer.valueOf(EmojiView.m10362$$Nest$fgetneedEmojiSearch(this.this$0) ? 1 : 0);
        L16:
            if (r0 == null) goto L18;
        L22:
            if (r0 != null) goto L37;
            ArrayList<EmojiPack> r3 = this.this$0.getEmojipacks();
            int r92 = r9 - EmojiData.dataColored.length;
            if (r3 == null) goto L37;
            if (r92 < 0) goto L37;
            if (r92 >= r3.size()) goto L37;
            int r02 = 0;
        L30:
            if (r02 >= EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).size()) goto L35;
            if (((EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r02)).set.id == r3.get(r92).set.id) goto L36;
            r02 = r02 + 1;
        L36:
            r0 = Integer.valueOf(EmojiGridAdapter.m10474$$Nest$fgetsectionToPosition(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0)).get(r02 + EmojiData.dataColored.length));
            int r93 = AndroidUtilities.dp(-9.0f);
        L38:
            if (r0 == null) goto L44;
            EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).stopScroll();
            EmojiView.m10455$$Nest$mupdateEmojiTabsPosition(this.this$0, r0.intValue());
            EmojiView.m10443$$Nest$mscrollEmojisToPosition(this.this$0, r0.intValue(), r93);
            EmojiView.m10422$$Nest$mcheckEmojiTabY(this.this$0, null, 0);
            return true;
        L44:
            return true;
        L35:
            r02 = -1;
        L37:
            r93 = 0;
            goto L38
        L18:
            if (r9 >= EmojiData.dataColored.length) goto L22;
            if (EmojiGridAdapter.m10474$$Nest$fgetsectionToPosition(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0)).indexOfKey(r9) < 0) goto L22;
            r0 = Integer.valueOf(EmojiGridAdapter.m10474$$Nest$fgetsectionToPosition(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0)).get(r9));
            goto L22
        L15:
            r9 = r9 - 1;
            r0 = null;
            goto L16
        L11:
            if (SearchField.m10535$$Nest$fgetcategoriesListView(EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0)) == null) goto L13;
            SearchField.m10535$$Nest$fgetcategoriesListView(EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0)).selectCategory(null);
            goto L13
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (getTranslationY() == r2) goto L9;
            super.setTranslationY(r2);
            if (EmojiView.m10320$$Nest$fgetemojiTabsShadow(this.this$0) == null) goto L7;
            EmojiView.m10320$$Nest$fgetemojiTabsShadow(this.this$0).setTranslationY(r2);
        L7:
            EmojiView.m10313$$Nest$fgetemojiContainer(this.this$0).invalidate();
            return;
        }
    }

    public static abstract class ChooseStickerActionTracker {
        private final int currentAccount;
        private final long dialogId;
        long lastActionTime;
        private final long threadId;
        boolean typingWasSent;
        boolean visible;

        /* renamed from: -$$Nest$mreset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10464$$Nest$mreset(ChooseStickerActionTracker r0) {
            r0.reset();
        }

        public ChooseStickerActionTracker(int r3, long r4, long r6) {
            this.visible = false;
            this.lastActionTime = -1;
            this.currentAccount = r3;
            this.dialogId = r4;
            this.threadId = r6;
        }

        private void reset() {
            if (this.typingWasSent == false) goto L5;
            MessagesController.getInstance(this.currentAccount).sendTyping(this.dialogId, this.threadId, 2, 0);
        L5:
            this.lastActionTime = -1;
        }

        public void checkVisibility() {
            boolean r0 = isShown();
            this.visible = r0;
            if (r0 == true) goto L6;
            reset();
            return;
        }

        public void doSomeAction() {
            if (this.visible == true) goto L5;
            return;
        L5:
            if (this.lastActionTime != (-1)) goto L9;
            this.lastActionTime = System.currentTimeMillis();
            return;
        L9:
            if ((System.currentTimeMillis() - this.lastActionTime) <= 2000) goto L13;
            this.typingWasSent = true;
            this.lastActionTime = System.currentTimeMillis();
            MessagesController.getInstance(this.currentAccount).sendTyping(this.dialogId, this.threadId, 10, 0);
            return;
        }

        public abstract boolean isShown();
    }

    public static class CustomEmoji {
        public long documentId;
        public String emoticon;
        public TLRPC.TL_messages_stickerSet stickerSet;

        public CustomEmoji() {
        }

        public TLRPC.Document getDocument() {
            TLRPC.TL_messages_stickerSet r0 = this.stickerSet;
            if (r0 != null) goto L5;
        L16:
            return null;
        L5:
            if (r0.documents == null) goto L16;
            int r02 = 0;
        L9:
            if (r02 >= this.stickerSet.documents.size()) goto L16;
            TLRPC.Document r2 = this.stickerSet.documents.get(r02);
            if (r2 == null) goto L15;
            if (r2.id != this.documentId) goto L15;
            return r2;
        L15:
            r02 = r02 + 1;
            goto L9
        }
    }

    public interface DragListener {
        void onDrag(int r1);

        void onDragCancel();

        void onDragEnd(float r1);

        void onDragStart();
    }

    public class DraggableScrollSlidingTabStrip extends ScrollSlidingTabStrip {
        private float downX;
        private float downY;
        private boolean draggingHorizontally;
        private boolean draggingVertically;
        private boolean first;
        private float lastTranslateX;
        private float lastX;
        private boolean startedScroll;
        final /* synthetic */ EmojiView this$0;
        private final int touchSlop;
        private VelocityTracker vTracker;

        public DraggableScrollSlidingTabStrip(EmojiView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3, EmojiView.m10349$$Nest$fgetglassDesign(r1));
            this.first = true;
            this.touchSlop = ViewConfiguration.get(r2).getScaledTouchSlop();
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTabStrip, android.widget.HorizontalScrollView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r5) {
            if (isDragging() == false) goto L7;
            return super.onInterceptTouchEvent(r5);
        L7:
            if (getParent() == null) goto L10;
            getParent().requestDisallowInterceptTouchEvent(true);
        L10:
            if (r5.getAction() != 0) goto L13;
            this.draggingHorizontally = false;
            this.draggingVertically = false;
            this.downX = r5.getRawX();
            this.downY = r5.getRawY();
        L25:
            return super.onInterceptTouchEvent(r5);
        L13:
            if (this.draggingVertically == true) goto L25;
            if (this.draggingHorizontally == true) goto L25;
            if (EmojiView.m10310$$Nest$fgetdragListener(this.this$0) == null) goto L25;
            if (Math.abs(r5.getRawY() - this.downY) < this.touchSlop) goto L25;
            this.draggingVertically = true;
            this.downY = r5.getRawY();
            EmojiView.m10310$$Nest$fgetdragListener(this.this$0).onDragStart();
            if (this.startedScroll == false) goto L23;
            EmojiView.m10363$$Nest$fgetpager(this.this$0).endFakeDrag();
            this.startedScroll = false;
        L23:
            return true;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTabStrip, android.widget.HorizontalScrollView, android.view.View
        public boolean onTouchEvent(MotionEvent r8) {
            if (isDragging() == false) goto L7;
            return super.onTouchEvent(r8);
        L7:
            if (this.first == false) goto L10;
            this.first = false;
            this.lastX = r8.getX();
        L10:
            if (r8.getAction() != 0) goto L12;
        L13:
            EmojiView.m10413$$Nest$fputlastStickersX(this.this$0, r8.getRawX());
        L15:
            if (r8.getAction() != 0) goto L18;
            this.draggingHorizontally = false;
            this.draggingVertically = false;
            this.downX = r8.getRawX();
            this.downY = r8.getRawY();
        L34:
            if (EmojiView.m10326$$Nest$fgetexpandStickersByDragg(this.this$0) == false) goto L41;
            if (r8.getAction() != 1) goto L38;
        L39:
            AndroidUtilities.runOnUIThread(EmojiView.m10302$$Nest$fgetcheckExpandStickerTabsRunnable(this.this$0), 1500);
            goto L41
        L38:
            if (r8.getAction() == 3) goto L39;
        L41:
            if (this.draggingVertically == true) goto L43;
            float r0 = getTranslationX();
            if (getScrollX() != 0) goto L77;
            if (r0 != 0.0f) goto L77;
            if (this.startedScroll == true) goto L70;
            if ((this.lastX - r8.getX()) >= 0.0f) goto L70;
            if (EmojiView.m10363$$Nest$fgetpager(this.this$0).beginFakeDrag() == false) goto L77;
            this.startedScroll = true;
            this.lastTranslateX = getTranslationX();
        L70:
            if (this.startedScroll == false) goto L77;
            if ((this.lastX - r8.getX()) <= 0.0f) goto L77;
            if (EmojiView.m10363$$Nest$fgetpager(this.this$0).isFakeDragging() == false) goto L77;
            EmojiView.m10363$$Nest$fgetpager(this.this$0).endFakeDrag();
            this.startedScroll = false;
        L77:
            if (this.startedScroll == false) goto L84;
            r8.getX();
            this.lastTranslateX = r0;     // Catch: Exception -> L81
        L81:
            e = move-exception;
            EmojiView.m10363$$Nest$fgetpager(this.this$0).endFakeDrag();     // Catch: Exception -> L97
        L83:
            this.startedScroll = false;
            FileLog.e(e);
        L84:
            this.lastX = r8.getX();
            if (r8.getAction() != 3) goto L87;
        L88:
            this.first = true;
            this.draggingHorizontally = false;
            this.draggingVertically = false;
            if (this.startedScroll == false) goto L92;
            EmojiView.m10363$$Nest$fgetpager(this.this$0).endFakeDrag();
            this.startedScroll = false;
        L92:
            if (this.startedScroll == true) goto L96;
            if (super.onTouchEvent(r8) == true) goto L96;
            return false;
        L96:
            return true;
        L87:
            if (r8.getAction() != 1) goto L92;
        L43:
            if (this.vTracker != null) goto L45;
            this.vTracker = VelocityTracker.obtain();
        L45:
            this.vTracker.addMovement(r8);
            if (r8.getAction() != 1) goto L48;
        L51:
            this.vTracker.computeCurrentVelocity(1000);
            float r02 = this.vTracker.getYVelocity();
            this.vTracker.recycle();
            this.vTracker = null;
            int r82 = r8.getAction();
            EmojiView r3 = this.this$0;
            if (r82 != 1) goto L54;
            EmojiView.m10310$$Nest$fgetdragListener(r3).onDragEnd(r02);
        L55:
            this.first = true;
            this.draggingHorizontally = false;
            this.draggingVertically = false;
        L56:
            cancelLongPress();
            return true;
        L54:
            EmojiView.m10310$$Nest$fgetdragListener(r3).onDragCancel();
            goto L55
        L48:
            if (r8.getAction() == 3) goto L51;
            EmojiView.m10310$$Nest$fgetdragListener(this.this$0).onDrag(Math.round(r8.getRawY() - this.downY));
            goto L56
        L18:
            if (this.draggingVertically == true) goto L34;
            if (this.draggingHorizontally == true) goto L34;
            if (EmojiView.m10310$$Nest$fgetdragListener(this.this$0) == null) goto L34;
            if (Math.abs(r8.getRawX() - this.downX) < this.touchSlop) goto L29;
            if (canScrollHorizontally((int) (this.downX - r8.getRawX())) == false) goto L29;
            this.draggingHorizontally = true;
            AndroidUtilities.cancelRunOnUIThread(EmojiView.m10302$$Nest$fgetcheckExpandStickerTabsRunnable(this.this$0));
            EmojiView.m10405$$Nest$fputexpandStickersByDragg(this.this$0, true);
            EmojiView.m10458$$Nest$mupdateStickerTabsPosition(this.this$0);
        L29:
            if (Math.abs(r8.getRawY() - this.downY) < this.touchSlop) goto L34;
            this.draggingVertically = true;
            this.downY = r8.getRawY();
            EmojiView.m10310$$Nest$fgetdragListener(this.this$0).onDragStart();
            if (this.startedScroll == false) goto L34;
            EmojiView.m10363$$Nest$fgetpager(this.this$0).endFakeDrag();
            this.startedScroll = false;
            goto L34
        L12:
            if (r8.getAction() != 2) goto L15;
            goto L13
        }
    }

    public class EmojiGridAdapter extends RecyclerListView.SelectionAdapter {
        private int firstTrendingRow;
        private ArrayList<TLRPC.TL_messages_stickerSet> frozenEmojiPacks;
        private int itemCount;
        private ArrayList<Integer> packStartPosition;
        public int plainEmojisCount;
        private SparseIntArray positionToExpand;
        private SparseIntArray positionToSection;
        private SparseIntArray positionToUnlock;
        private int recentlyUsedHeaderRow;
        private ArrayList<Integer> rowHashCodes;
        private SparseIntArray sectionToPosition;
        final /* synthetic */ EmojiView this$0;
        private int trendingHeaderRow;
        private int trendingRow;

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiGridAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerView.ItemDecoration {
            final /* synthetic */ EmojiGridAdapter this$1;

            public AnonymousClass1(EmojiGridAdapter r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
                r1.right = AndroidUtilities.dp(2.0f);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiGridAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends DiffUtil.Callback {
            final /* synthetic */ EmojiGridAdapter this$1;
            final /* synthetic */ ArrayList val$prevRowHashCodes;

            public AnonymousClass2(EmojiGridAdapter r1, ArrayList r2) {
                this.this$1 = r1;
                this.val$prevRowHashCodes = r2;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int r1, int r2) {
                return true;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int r2, int r3) {
                return ((Integer) this.val$prevRowHashCodes.get(r2)).equals(EmojiGridAdapter.m10473$$Nest$fgetrowHashCodes(this.this$1).get(r3));
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return EmojiGridAdapter.m10473$$Nest$fgetrowHashCodes(this.this$1).size();
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return this.val$prevRowHashCodes.size();
            }
        }

        public static /* synthetic */ void $r8$lambda$0ehvVEl3UGlj7qq_Sgh8catGgvI(EmojiGridAdapter r0, View r1, int r2) {
            r0.lambda$onCreateViewHolder$1(r1, r2);
        }

        /* renamed from: $r8$lambda$OF_EqalKYyRGvTVa8qluQYF-ziM, reason: not valid java name */
        public static /* synthetic */ void m10465$r8$lambda$OF_EqalKYyRGvTVa8qluQYFziM(EmojiGridAdapter r0, float r1, int r2) {
            r0.lambda$expand$2(r1, r2);
        }

        /* renamed from: $r8$lambda$jLrEW-hmfBlZjxf-F4GXgVNShps, reason: not valid java name */
        public static /* synthetic */ void m10466$r8$lambda$jLrEWhmfBlZjxfF4GXgVNShps(EmojiGridAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        /* renamed from: -$$Nest$fgetfrozenEmojiPacks, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10467$$Nest$fgetfrozenEmojiPacks(EmojiGridAdapter r0) {
            return r0.frozenEmojiPacks;
        }

        /* renamed from: -$$Nest$fgetpackStartPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10468$$Nest$fgetpackStartPosition(EmojiGridAdapter r0) {
            return r0.packStartPosition;
        }

        /* renamed from: -$$Nest$fgetpositionToExpand, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseIntArray m10469$$Nest$fgetpositionToExpand(EmojiGridAdapter r0) {
            return r0.positionToExpand;
        }

        /* renamed from: -$$Nest$fgetpositionToSection, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseIntArray m10470$$Nest$fgetpositionToSection(EmojiGridAdapter r0) {
            return r0.positionToSection;
        }

        /* renamed from: -$$Nest$fgetpositionToUnlock, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseIntArray m10471$$Nest$fgetpositionToUnlock(EmojiGridAdapter r0) {
            return r0.positionToUnlock;
        }

        /* renamed from: -$$Nest$fgetrecentlyUsedHeaderRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10472$$Nest$fgetrecentlyUsedHeaderRow(EmojiGridAdapter r0) {
            return r0.recentlyUsedHeaderRow;
        }

        /* renamed from: -$$Nest$fgetrowHashCodes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10473$$Nest$fgetrowHashCodes(EmojiGridAdapter r0) {
            return r0.rowHashCodes;
        }

        /* renamed from: -$$Nest$fgetsectionToPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseIntArray m10474$$Nest$fgetsectionToPosition(EmojiGridAdapter r0) {
            return r0.sectionToPosition;
        }

        /* renamed from: -$$Nest$fgettrendingHeaderRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10475$$Nest$fgettrendingHeaderRow(EmojiGridAdapter r0) {
            return r0.trendingHeaderRow;
        }

        /* renamed from: -$$Nest$fgettrendingRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10476$$Nest$fgettrendingRow(EmojiGridAdapter r0) {
            return r0.trendingRow;
        }

        private EmojiGridAdapter(EmojiView r1) {
            this.this$0 = r1;
            this.trendingHeaderRow = -1;
            this.trendingRow = -1;
            this.firstTrendingRow = -1;
            this.recentlyUsedHeaderRow = -1;
            this.rowHashCodes = new ArrayList();
            this.positionToSection = new SparseIntArray();
            this.sectionToPosition = new SparseIntArray();
            this.positionToUnlock = new SparseIntArray();
            this.positionToExpand = new SparseIntArray();
            this.packStartPosition = new ArrayList();
        }

        public /* synthetic */ EmojiGridAdapter(EmojiView r1, EmojiViewIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$expand$2(float r4, int r5) {
            LinearSmoothScrollerCustom r0 = new LinearSmoothScrollerCustom(EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getContext(), 0, r4);     // Catch: Exception -> L4
            r0.setTargetPosition(r5);     // Catch: Exception -> L4
            EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).startSmoothScroll(r0);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r4) {
            if (EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0) != null) goto L5;
            return;
        L5:
            if (EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).isEmpty() == false) goto L7;
            return;
        L7:
            if (((TLRPC.StickerSetCovered) EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).get(0)).set == null) goto L19;
            long r0 = ((TLRPC.StickerSetCovered) EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).get(0)).set.id;
            MessagesController.getEmojiSettings(this.this$0.currentAccount).edit().putLong("emoji_featured_hidden", r0).commit();
            if (EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0) == null) goto L13;
            EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0).notifyItemRangeRemoved(1, 3);
        L13:
            if (EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0) == null) goto L15;
            EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0).updateEmojiPacks(this.this$0.getEmojipacks());
        L15:
            updateRows();
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(View r9, int r10) {
            if ((r9.getTag() instanceof TLRPC.StickerSetCovered) == false) goto L39;
            TLRPC.StickerSetCovered r92 = (TLRPC.StickerSetCovered) r9.getTag();
            ArrayList r102 = new ArrayList();
            ArrayList<TLRPC.StickerSetCovered> r0 = MediaDataController.getInstance(this.this$0.currentAccount).getFeaturedEmojiSets();
            int r1 = -1;
            int r2 = 0;
        L6:
            if (r2 >= r0.size()) goto L19;
            TLRPC.StickerSetCovered r3 = r0.get(r2);
            if (r3 == null) goto L18;
            if (r3.set == null) goto L18;
            TLRPC.TL_inputStickerSetID r4 = new TLRPC.TL_inputStickerSetID();
            TLRPC.StickerSet r5 = r3.set;
            r4.id = r5.id;
            r4.access_hash = r5.access_hash;
            r102.add(r4);
            if (r92 == null) goto L18;
            TLRPC.StickerSet r42 = r92.set;
            if (r42 == null) goto L18;
            if (r42.id != r3.set.id) goto L18;
            r1 = r2;
        L18:
            r2 = r2 + 1;
            goto L6
        L19:
            MediaDataController.getInstance(this.this$0.currentAccount).markFeaturedStickersAsRead(true, true);
            BaseFragment r02 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            Context r22 = this.this$0.getContext();
            BaseFragment r32 = EmojiView.m10335$$Nest$fgetfragment(this.this$0);
            EmojiView r43 = this.this$0;
            if (r32 != null) goto L22;
            Theme.ResourcesProvider r33 = EmojiView.m10370$$Nest$fgetresourcesProvider(r43);
        L23:
            EmojiPacksAlert r93 = new EmojiPacksAlert(r02, r22, r33, r102);
            if (r1 < 0) goto L27;
            r93.highlight(r1);
        L27:
            if (EmojiView.m10335$$Nest$fgetfragment(this.this$0) == null) goto L30;
            EmojiView.m10335$$Nest$fgetfragment(this.this$0).showDialog(r93);
            return;
        L30:
            r93.show();
            return;
        L22:
            r33 = EmojiView.m10335$$Nest$fgetfragment(r43).getResourceProvider();
            goto L23
        }

        private void removeGroupEmojiPackFromInstalled(TLRPC.StickerSet r5, ArrayList<TLRPC.TL_messages_stickerSet> r6) {
            int r4 = 0;
        L4:
            if (r4 >= r6.size()) goto L12;
            TLRPC.TL_messages_stickerSet r0 = r6.get(r4);
            if (r0 == null) goto L11;
            if (r0.set.id != r5.id) goto L11;
            r6.remove(r4);
            return;
        L11:
            r4 = r4 + 1;
            goto L4
        }

        public void expand(int r9, View r10) {
            int r92 = this.positionToExpand.get(r9);
            if (r92 >= 0) goto L5;
            return;
        L5:
            if (r92 >= EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).size()) goto L54;
            EmojiPack r0 = (EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r92);
            if (r0.expanded == false) goto L11;
            return;
        L11:
            if ((r92 + 1) != EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).size()) goto L13;
            boolean r1 = true;
        L14:
            int r93 = this.packStartPosition.get(r92).intValue();
            EmojiView.m10327$$Nest$fgetexpandedEmojiSets(this.this$0).add(Long.valueOf(r0.set.id));
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L17;
        L20:
            boolean r2 = true;
        L21:
            int r5 = EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount() * 3;
            if (r0.installed == false) goto L29;
            if (r0.featured == true) goto L29;
            if (r0.free == true) goto L30;
            if (r2 == false) goto L29;
        L30:
            int r22 = r0.documents.size();
        L32:
            Integer r7 = null;
            if (r0.documents.size() <= r5) goto L35;
            Integer r6 = Integer.valueOf((r93 + 1) + r22);
        L36:
            r0.expanded = true;
            int r02 = r0.documents.size() - r22;
            if (r02 <= 0) goto L39;
            r6 = Integer.valueOf((r93 + 1) + r22);
            r7 = Integer.valueOf(r02);
        L39:
            processEmoji(false);
            updateRows();
            if (r6 == null) goto L51;
            if (r7 == null) goto L52;
            EmojiView.m10390$$Nest$fputanimateExpandFromButton(this.this$0, r10);
            EmojiView.m10391$$Nest$fputanimateExpandFromPosition(this.this$0, r6.intValue());
            EmojiView.m10393$$Nest$fputanimateExpandToPosition(this.this$0, r6.intValue() + r7.intValue());
            EmojiView.m10392$$Nest$fputanimateExpandStartTime(this.this$0, SystemClock.elapsedRealtime());
            notifyItemRangeInserted(r6.intValue(), r7.intValue());
            notifyItemChanged(r6.intValue());
            if (r1 == false) goto L53;
            int r94 = r6.intValue();
            if (r7.intValue() <= (r5 / 2)) goto L47;
            float r102 = 1.5f;
        L48:
            this.this$0.post(new EmojiView$EmojiGridAdapter$$ExternalSyntheticLambda2(this, r102, r94));
            return;
        L47:
            r102 = 4.0f;
            goto L48
        L53:
            return;
        L52:
            return;
        L51:
            return;
        L35:
            r6 = null;
        L29:
            if (r0.expanded == true) goto L30;
            r22 = Math.min(r5, r0.documents.size());
            goto L32
        L17:
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == true) goto L20;
            r2 = false;
            goto L21
        L13:
            r1 = false;
            goto L14
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.itemCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != this.trendingRow) goto L7;
            return 4;
        L7:
            if (r3 != this.trendingHeaderRow) goto L9;
        L33:
            return 1;
        L9:
            if (r3 == this.recentlyUsedHeaderRow) goto L33;
            if (this.positionToSection.indexOfKey(r3) < 0) goto L19;
            if (this.positionToSection.get(r3) < EmojiData.dataColored.length) goto L17;
            return 5;
        L17:
            return 1;
        L19:
            if (EmojiView.m10362$$Nest$fgetneedEmojiSearch(this.this$0) == false) goto L24;
            if (r3 != 0) goto L24;
            return 2;
        L24:
            if (this.positionToUnlock.indexOfKey(r3) < 0) goto L28;
            return 3;
        L28:
            if (this.positionToExpand.indexOfKey(r3) < 0) goto L31;
            return 6;
        L31:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            int r0 = r1.getItemViewType();
            if (r0 != 0) goto L5;
            return true;
        L5:
            if (r0 != 4) goto L7;
            return true;
        L7:
            if (r0 != 3) goto L9;
            return true;
        L9:
            if (r0 == 6) goto L17;
            return false;
        L17:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            notifyDataSetChanged(false);
        }

        public void notifyDataSetChanged(boolean r3) {
            if (EmojiView.m10336$$Nest$fgetfrozen(this.this$0) == false) goto L5;
            return;
        L5:
            ArrayList r0 = new ArrayList(this.rowHashCodes);
            MediaDataController.getInstance(this.this$0.currentAccount).getFeaturedEmojiSets();
            EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).clear();
            processEmoji(r3);
            updateRows();
            if (EmojiView.m10389$$Nest$fgettrendingEmojiAdapter(this.this$0) == null) goto L8;
            EmojiView.m10389$$Nest$fgettrendingEmojiAdapter(this.this$0).notifyDataSetChanged();
        L8:
            DiffUtil.calculateDiff(new AnonymousClass2(this, r0), false).dispatchUpdatesTo(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r13, @SuppressLint({"RecyclerView"}) int r14) {
            int r0 = r13.getItemViewType();
            boolean r1 = true;
            EmojiPack r3 = null;
            if (r0 == 0) goto L57;
            if (r0 != 1) goto L6;
            StickerSetNameCell r132 = (StickerSetNameCell) r13.itemView;
            r132.position = r14;
            int r02 = this.positionToSection.get(r14);
            if (r14 != this.trendingHeaderRow) goto L47;
            r132.setText(LocaleController.getString(R.string.FeaturedEmojiPacks), R.drawable.msg_close, LocaleController.getString(R.string.AccDescrCloseTrendingEmoji));
            return;
        L47:
            if (r14 != this.recentlyUsedHeaderRow) goto L50;
            r132.setText(LocaleController.getString(R.string.RecentlyUsed), 0);
            return;
        L50:
            int r142 = EmojiView.m10321$$Nest$fgetemojiTitles(this.this$0).length;
            EmojiView r12 = this.this$0;
            if (r02 >= r142) goto L142;
            r132.setText(EmojiView.m10321$$Nest$fgetemojiTitles(r12)[r02], 0);
            return;
        L142:
            r132.setText(((EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(r12).get(r02 - EmojiView.m10321$$Nest$fgetemojiTitles(this.this$0).length)).set.title, 0);     // Catch: Exception -> L54
            return;
        L54:
            r132.setText(_UrlKt.FRAGMENT_ENCODE_SET, 0);
            return;
        L6:
            if (r0 != 5) goto L8;
            EmojiPackHeader r133 = (EmojiPackHeader) r13.itemView;
            int r143 = this.positionToSection.get(r14) - EmojiView.m10321$$Nest$fgetemojiTitles(this.this$0).length;
            EmojiPack r03 = (EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r143);
            int r144 = r143 - 1;
            if (r144 < 0) goto L21;
            EmojiPack r145 = (EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r144);
        L22:
            if (r03 != null) goto L24;
        L35:
            r1 = false;
        L36:
            if (r03 != null) goto L38;
        L40:
            r133.setStickerSet(r03, r1);
            return;
        L38:
            if (r03.needLoadSet == null) goto L40;
            MediaDataController.getInstance(this.this$0.currentAccount).getStickerSet(r03.needLoadSet, false);
            r03.needLoadSet = null;
            goto L40
        L24:
            if (r03.featured == false) goto L35;
            if (r145 == null) goto L36;
            if (r145.free == true) goto L36;
            if (r145.installed == false) goto L36;
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == true) goto L36;
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == false) goto L35;
        L21:
            r145 = null;
            goto L22
        L8:
            if (r0 != 6) goto L154;
            EmojiPackExpand r134 = (EmojiPackExpand) r13.itemView;
            int r146 = this.positionToExpand.get(r14);
            int r04 = EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount() * 3;
            if (r146 >= 0) goto L13;
        L15:
            if (r3 == null) goto L152;
            r134.textView.setText("+" + ((r3.documents.size() - r04) + 1));
            return;
        L152:
            return;
        L13:
            if (r146 >= EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).size()) goto L15;
            r3 = (EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r146);
            goto L15
        L154:
            return;
        L57:
            ImageViewEmoji r135 = (ImageViewEmoji) r13.itemView;
            r135.position = r14;
            ImageViewEmoji.m10527$$Nest$fputpack(r135, null);
            if (EmojiView.m10362$$Nest$fgetneedEmojiSearch(this.this$0) == false) goto L61;
            r14 = r14 - 1;
        L61:
            if (this.recentlyUsedHeaderRow < 0) goto L64;
            r14 = r14 - 1;
        L64:
            if (this.trendingRow < 0) goto L66;
            r14 = r14 - 2;
        L66:
            int r05 = this.this$0.getRecentEmoji().size();
            if (r14 >= r05) goto L78;
            String r122 = this.this$0.getRecentEmoji().get(r14);
            if (r122 != null) goto L71;
        L76:
            String r147 = r122;
            Long r123 = null;
        L75:
            String r06 = r147;
            TLRPC.Document r4 = null;
        L125:
            if (r123 == null) goto L127;
            r135.setPadding(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        L128:
            if (r123 == null) goto L136;
            r135.setImageDrawable(null, r1);
            if (r135.getSpan() != null) goto L132;
        L133:
            if (r4 == null) goto L135;
            r135.setSpan(new AnimatedEmojiSpan(r4, null));
        L137:
            r135.setTag(r06);
            r135.setContentDescription(r147);
            return;
        L135:
            r135.setSpan(new AnimatedEmojiSpan(r123.longValue(), null));
            goto L137
        L132:
            if (r135.getSpan().getDocumentId() == r123.longValue()) goto L137;
        L136:
            r135.setImageDrawable(Emoji.getEmojiBigDrawable(r147), r1);
            r135.setSpan(null);
            goto L137
        L127:
            r135.setPadding(0, 0, 0, 0);
            goto L128
        L71:
            if (r122.startsWith("animated_") == false) goto L76;
            r123 = Long.valueOf(Long.parseLong(r122.substring(9)));     // Catch: Exception -> L139
            r147 = null;
            goto L75
        L78:
            int r42 = 0;
        L79:
            String[][] r5 = EmojiData.dataColored;
            if (r42 >= r5.length) goto L90;
            String[] r52 = r5[r42];
            int r6 = r52.length + 1;
            int r7 = (r14 - r05) - 1;
            if (r7 < 0) goto L89;
            if (r14 >= (r05 + r6)) goto L89;
            r147 = r52[r7];
            String r07 = Emoji.emojiColor.get(r147);
            if (r07 == null) goto L88;
            String r08 = EmojiView.addColorToCode(r147, r07);
            r06 = r147;
            r147 = r08;
        L91:
            if (r06 == null) goto L93;
        L123:
            r123 = null;
            TLRPC.Document r15 = null;
        L124:
            r4 = r15;
            r1 = false;
            goto L125
        L93:
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L95;
        L98:
            boolean r43 = true;
        L99:
            int r53 = EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount() * 3;
            int r62 = 0;
        L101:
            if (r62 >= this.packStartPosition.size()) goto L123;
            EmojiPack r72 = (EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r62);
            int r8 = this.packStartPosition.get(r62).intValue() + 1;
            if (r72.installed == false) goto L110;
            if (r72.featured == true) goto L110;
            if (r72.free == true) goto L111;
            if (r43 == false) goto L110;
        L111:
            int r9 = r72.documents.size();
        L113:
            int r10 = r135.position;
            if (r10 < r8) goto L122;
            if ((r10 - r8) >= r9) goto L122;
            ImageViewEmoji.m10527$$Nest$fputpack(r135, r72);
            TLRPC.Document r124 = r72.documents.get(r135.position - r8);
            if (r124 != null) goto L120;
            Long r16 = null;
        L121:
            Long r11 = r16;
            r15 = r124;
            r123 = r11;
            goto L124
        L120:
            r16 = Long.valueOf(r124.id);
        L122:
            r62 = r62 + 1;
        L110:
            if (r72.expanded == true) goto L111;
            r9 = Math.min(r53, r72.documents.size());
            goto L113
        L95:
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == true) goto L98;
            r43 = false;
        L88:
            r06 = r147;
        L89:
            r05 = r05 + r6;
            r42 = r42 + 1;
            goto L79
        L90:
            r147 = null;
            goto L88
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v5, types: [android.view.View, androidx.recyclerview.widget.RecyclerView, org.telegram.ui.Components.EmojiView$TrendingListView, org.telegram.ui.Components.RecyclerListView] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r5, int r6) {
            if (r6 != 0) goto L4;
            EmojiView r4 = this.this$0;
            View r52 = new ImageViewEmoji(r4, r4.getContext());
        L22:
            return new RecyclerListView.Holder(r52);
        L4:
            if (r6 != 1) goto L6;
            StickerSetNameCell r62 = new StickerSetNameCell(this.this$0.getContext(), true, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0), EmojiView.m10349$$Nest$fgetglassDesign(this.this$0));
            r62.setOnIconClickListener(new EmojiView$EmojiGridAdapter$$ExternalSyntheticLambda0(this));
            StickerSetNameCell r63 = r62;
        L17:
            r52 = r63;
            goto L22
        L6:
            if (r6 != 3) goto L8;
            EmojiView r42 = this.this$0;
            r52 = new EmojiPackButton(r42, r42.getContext());
            goto L22
        L8:
            if (r6 != 4) goto L10;
            EmojiView r0 = this.this$0;
            Context r1 = r0.getContext();
            EmojiView r2 = this.this$0;
            TrendingAdapter r3 = new TrendingAdapter(r2, true);
            EmojiView.m10418$$Nest$fputtrendingEmojiAdapter(r2, r3);
            ?? r64 = new TrendingListView(r0, r1, r3);
            r64.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), 0);
            r64.setClipToPadding(false);
            r64.addItemDecoration(new AnonymousClass1(this));
            r64.setOnItemClickListener(new EmojiView$EmojiGridAdapter$$ExternalSyntheticLambda1(this));
            r63 = r64;
            goto L17
        L10:
            if (r6 != 5) goto L12;
            EmojiView r43 = this.this$0;
            r52 = new EmojiPackHeader(r43, r43.getContext());
            goto L22
        L12:
            if (r6 == 6) goto L14;
            r52 = new View(this.this$0.getContext());
            r52.setLayoutParams(new RecyclerView.LayoutParams(-1, EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0)));
            goto L22
        L14:
            r52 = new EmojiPackExpand(this.this$0.getContext(), EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            goto L22
        }

        public void processEmoji(boolean r14) {
            EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).clear();
            if (EmojiView.m10288$$Nest$fgetallowAnimatedEmoji(this.this$0) == false) goto L110;
            MediaDataController r0 = MediaDataController.getInstance(this.this$0.currentAccount);
            if (r14 == false) goto L8;
        L9:
            this.frozenEmojiPacks = new ArrayList(r0.getStickerSets(5));
        L10:
            ArrayList<TLRPC.TL_messages_stickerSet> r142 = new ArrayList(this.frozenEmojiPacks);
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L13;
        L16:
            boolean r1 = true;
        L18:
            if (EmojiView.m10358$$Nest$fgetinfo(this.this$0) != null) goto L20;
        L24:
            int r4 = 0;
        L25:
            if (r1 == true) goto L35;
            int r5 = 0;
        L28:
            if (r5 >= r142.size()) goto L35;
            TLRPC.TL_messages_stickerSet r6 = r142.get(r5);
            if (r6 == null) goto L34;
            if (MessageObject.isPremiumEmojiPack(r6) == true) goto L34;
            EmojiPack r7 = new EmojiPack();
            r7.index = r4;
            r7.set = r6.set;
            r7.documents = new ArrayList(r6.documents);
            r7.free = true;
            r7.installed = r0.isStickerPackInstalled(r6.set.id);
            r7.featured = false;
            r7.expanded = true;
            EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).add(r7);
            r142.remove(r5);
            r5 = r5 - 1;
            r4 = r4 + 1;
        L34:
            r5 = r5 + 1;
        L35:
            int r52 = 0;
        L37:
            if (r52 >= r142.size()) goto L60;
            TLRPC.TL_messages_stickerSet r62 = r142.get(r52);
            if (r1 == false) goto L41;
            EmojiPack r72 = new EmojiPack();
            int r8 = r4 + 1;
            r72.index = r4;
            TLRPC.StickerSet r42 = r62.set;
            r72.set = r42;
            r72.documents = r62.documents;
            r72.free = false;
            r72.installed = r0.isStickerPackInstalled(r42.id);
            r72.featured = false;
            r72.expanded = true;
            EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).add(r72);
            r4 = r8;
        L59:
            r52 = r52 + 1;
            goto L37
        L41:
            ArrayList r73 = new ArrayList();
            ArrayList r82 = new ArrayList();
            if (r62 == null) goto L54;
            if (r62.documents == null) goto L54;
            int r9 = 0;
        L47:
            if (r9 >= r62.documents.size()) goto L54;
            boolean r10 = MessageObject.isFreeEmoji(r62.documents.get(r9));
            ArrayList<TLRPC.Document> r11 = r62.documents;
            if (r10 == false) goto L51;
            r73.add(r11.get(r9));
        L52:
            r9 = r9 + 1;
            goto L47
        L51:
            r82.add(r11.get(r9));
        L54:
            if (r73.size() <= 0) goto L57;
            EmojiPack r92 = new EmojiPack();
            r92.index = r4;
            r92.set = r62.set;
            r92.documents = new ArrayList(r73);
            r92.free = true;
            r92.installed = r0.isStickerPackInstalled(r62.set.id);
            r92.featured = false;
            r92.expanded = true;
            EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).add(r92);
            r4 = r4 + 1;
        L57:
            if (r82.size() <= 0) goto L59;
            EmojiPack r74 = new EmojiPack();
            r74.index = r4;
            r74.set = r62.set;
            r74.documents = new ArrayList(r82);
            r74.free = false;
            r74.installed = r0.isStickerPackInstalled(r62.set.id);
            r74.featured = false;
            r74.expanded = EmojiView.m10327$$Nest$fgetexpandedEmojiSets(this.this$0).contains(Long.valueOf(r74.set.id));
            EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).add(r74);
            r4 = r4 + 1;
            goto L59
        L60:
            int r143 = 0;
        L61:
            int r12 = EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).size();
            EmojiView r53 = this.this$0;
            if (r143 >= r12) goto L89;
            TLRPC.StickerSetCovered r13 = (TLRPC.StickerSetCovered) EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(r53).get(r143);
            EmojiPack r54 = new EmojiPack();
            r54.installed = r0.isStickerPackInstalled(r13.set.id);
            TLRPC.StickerSet r63 = r13.set;
            r54.set = r63;
            if ((r13 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L67;
            r54.documents = ((TLRPC.TL_stickerSetFullCovered) r13).documents;
        L73:
            ArrayList<TLRPC.Document> r15 = r54.documents;
            if (r15 == null) goto L87;
            if (r15.isEmpty() == true) goto L87;
            int r16 = r4 + 1;
            r54.index = r4;
            int r43 = 0;
        L80:
            if (r43 >= r54.documents.size()) goto L85;
            if (MessageObject.isFreeEmoji(r54.documents.get(r43)) == false) goto L83;
            r43 = r43 + 1;
            goto L80
        L83:
            boolean r44 = true;
        L86:
            r54.free = !r44;
            r54.expanded = EmojiView.m10327$$Nest$fgetexpandedEmojiSets(this.this$0).contains(Long.valueOf(r54.set.id));
            r54.featured = true;
            EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).add(r54);
            r4 = r16;
            goto L87
        L85:
            r44 = false;
        L87:
            r143 = r143 + 1;
            goto L61
        L67:
            if ((r13 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L72;
            TLRPC.TL_messages_stickerSet r64 = r0.getStickerSet(MediaDataController.getInputStickerSet(r63), Integer.valueOf(r13.set.hash), true);
            if (r64 == null) goto L71;
            r54.documents = r64.documents;
            goto L73
        L71:
            r54.needLoadSet = MediaDataController.getInputStickerSet(r13.set);
            goto L73
        L72:
            r54.documents = r13.covers;
            goto L73
        L89:
            if (EmojiView.m10319$$Nest$fgetemojiTabs(r53) == null) goto L109;
            EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0).updateEmojiPacks(this.this$0.getEmojipacks());
            return;
        L109:
            return;
        L20:
            if (EmojiView.m10358$$Nest$fgetinfo(this.this$0).emojiset == null) goto L24;
            TLRPC.TL_messages_stickerSet r45 = r0.getGroupStickerSetById(EmojiView.m10358$$Nest$fgetinfo(this.this$0).emojiset);
            if (r45 == null) goto L24;
            EmojiPack r55 = new EmojiPack();
            r55.index = 0;
            r55.set = EmojiView.m10358$$Nest$fgetinfo(this.this$0).emojiset;
            r55.documents = new ArrayList(r45.documents);
            r55.free = true;
            r55.installed = true;
            r55.featured = false;
            r55.expanded = true;
            r55.forGroup = true;
            EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).add(r55);
            removeGroupEmojiPackFromInstalled(r55.set, r142);
            r4 = 1;
            goto L25
        L13:
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == true) goto L16;
            r1 = false;
            goto L18
        L8:
            if (this.frozenEmojiPacks != null) goto L10;
        }

        public void updateRows() {
            this.positionToSection.clear();
            this.sectionToPosition.clear();
            this.positionToUnlock.clear();
            this.positionToExpand.clear();
            this.packStartPosition.clear();
            this.rowHashCodes.clear();
            int r0 = 0;
            this.itemCount = 0;
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L5;
        L8:
            boolean r1 = true;
        L10:
            if (EmojiView.m10362$$Nest$fgetneedEmojiSearch(this.this$0) == false) goto L12;
            this.itemCount++;
            this.rowHashCodes.add(-1);
        L12:
            if (r1 == true) goto L14;
        L24:
            this.trendingHeaderRow = -1;
            this.trendingRow = -1;
            this.recentlyUsedHeaderRow = -1;
        L25:
            ArrayList<String> r3 = this.this$0.getRecentEmoji();
            if (EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0) == null) goto L28;
            EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0).showRecent(!r3.isEmpty());
        L28:
            this.itemCount += r3.size();
            int r5 = 0;
        L30:
            if (r5 >= r3.size()) goto L32;
            this.rowHashCodes.add(Integer.valueOf(Objects.hash(new Object[]{-43263, r3.get(r5)})));
            r5 = r5 + 1;
            goto L30
        L32:
            int r32 = 0;
            int r52 = 0;
        L33:
            String[][] r6 = EmojiData.dataColored;
            if (r32 >= r6.length) goto L40;
            this.positionToSection.put(this.itemCount, r52);
            this.sectionToPosition.put(r52, this.itemCount);
            this.itemCount += r6[r32].length + 1;
            this.rowHashCodes.add(Integer.valueOf(Objects.hash(new Object[]{43245, Integer.valueOf(r32)})));
            int r62 = 0;
        L36:
            String[] r7 = EmojiData.dataColored[r32];
            if (r62 >= r7.length) goto L39;
            this.rowHashCodes.add(Integer.valueOf(r7[r62].hashCode()));
            r62 = r62 + 1;
            goto L36
        L39:
            r32 = r32 + 1;
            r52 = r52 + 1;
            goto L33
        L40:
            int r33 = EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount() * 3;
            this.plainEmojisCount = this.itemCount;
            this.firstTrendingRow = -1;
            if (EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0) != null) goto L43;
            return;
        L43:
            if (r0 >= EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).size()) goto L101;
            this.positionToSection.put(this.itemCount, r52);
            this.sectionToPosition.put(r52, this.itemCount);
            this.packStartPosition.add(Integer.valueOf(this.itemCount));
            EmojiPack r4 = (EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r0);
            boolean r63 = r4.featured;
            if (r63 == false) goto L50;
            if (this.firstTrendingRow >= 0) goto L50;
            this.firstTrendingRow = this.itemCount;
        L50:
            if (r4.installed == false) goto L56;
            if (r63 == true) goto L56;
            if (r4.free == true) goto L57;
            if (r1 == false) goto L56;
        L57:
            int r64 = r4.documents.size();
        L59:
            int r72 = 1 + r64;
            if (r4.expanded == false) goto L62;
        L64:
            r64 = r72;
        L65:
            ArrayList<Integer> r73 = this.rowHashCodes;
            if (r4.featured == false) goto L68;
            int r8 = 56345;
        L69:
            Integer r82 = Integer.valueOf(r8);
            TLRPC.StickerSet r9 = r4.set;
            if (r9 != null) goto L72;
            long r92 = r0;
        L73:
            r73.add(Integer.valueOf(Objects.hash(new Object[]{r82, Long.valueOf(r92), Boolean.valueOf(r4.forGroup)})));
            int r74 = 1;
        L74:
            if (r74 >= r64) goto L80;
            ArrayList<Integer> r83 = this.rowHashCodes;
            if (r4.featured == false) goto L78;
            int r93 = 3442;
        L79:
            r83.add(Integer.valueOf(Objects.hash(new Object[]{Integer.valueOf(r93), Long.valueOf(r4.documents.get(r74 - 1).id)})));
            r74 = r74 + 1;
            goto L74
        L78:
            r93 = -9964;
            goto L79
        L80:
            this.itemCount += r64;
            if (r4.expanded == true) goto L89;
            if (r4.documents.size() <= r33) goto L89;
            this.positionToExpand.put(this.itemCount, r0);
            ArrayList<Integer> r65 = this.rowHashCodes;
            if (r4.featured == false) goto L87;
            int r75 = -65174;
        L88:
            r65.add(Integer.valueOf(Objects.hash(new Object[]{Integer.valueOf(r75), Long.valueOf(r4.set.id)})));
            this.itemCount++;
            goto L89
        L87:
            r75 = 92242;
        L89:
            r0 = r0 + 1;
            r52 = r52 + 1;
            goto L43
        L72:
            r92 = r9.id;
            goto L73
        L68:
            r8 = -495231;
            goto L69
        L62:
            if (r4.documents.size() <= r33) goto L64;
        L56:
            if (r4.expanded == true) goto L57;
            r64 = Math.min(r33, r4.documents.size());
            goto L59
        L101:
            return;
        L14:
            if (EmojiView.m10288$$Nest$fgetallowAnimatedEmoji(this.this$0) == false) goto L24;
            if (EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).size() <= 0) goto L24;
            if (((TLRPC.StickerSetCovered) EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).get(0)).set == null) goto L24;
            if (MessagesController.getEmojiSettings(this.this$0.currentAccount).getLong("emoji_featured_hidden", 0) == ((TLRPC.StickerSetCovered) EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(this.this$0).get(0)).set.id) goto L24;
            if (EmojiView.m10362$$Nest$fgetneedEmojiSearch(this.this$0) == false) goto L24;
            int r34 = this.itemCount;
            this.trendingHeaderRow = r34;
            this.trendingRow = r34 + 1;
            this.itemCount = r34 + 3;
            this.recentlyUsedHeaderRow = r34 + 2;
            this.rowHashCodes.add(324953);
            this.rowHashCodes.add(123342);
            this.rowHashCodes.add(929132);
            goto L25
        L5:
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == true) goto L8;
            r1 = false;
            goto L10
        }
    }

    public class EmojiGridSpacing extends RecyclerView.ItemDecoration {
        final /* synthetic */ EmojiView this$0;

        public EmojiGridSpacing(EmojiView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r2, View r3, RecyclerView r4, RecyclerView.State r5) {
            if ((r3 instanceof StickerSetNameCell) == false) goto L13;
            r2.left = AndroidUtilities.dp(5.0f);
            r2.right = AndroidUtilities.dp(5.0f);
            if ((r4.getChildAdapterPosition(r3) + 1) > EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0).plainEmojisCount) goto L7;
            return;
        L7:
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L9;
            return;
        L9:
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == true) goto L27;
            r2.top = AndroidUtilities.dp(10.0f);
            return;
        L27:
            return;
        L13:
            if ((r3 instanceof RecyclerListView) == false) goto L15;
        L21:
            r2.left = -EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getPaddingLeft();
            r2.right = -EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getPaddingRight();
            if ((r3 instanceof EmojiPackHeader) == false) goto L29;
            r2.top = AndroidUtilities.dp(8.0f);
            return;
        L29:
            return;
        L15:
            if ((r3 instanceof EmojiPackHeader) == true) goto L21;
            if ((r3 instanceof BackupImageView) == false) goto L28;
            r2.bottom = AndroidUtilities.dp(12.0f);
            return;
        }
    }

    public class EmojiGridView extends RecyclerListView {
        private SparseIntArray headerWidthsCache;
        private boolean ignoreLayout;
        private int lastChildCount;
        ArrayList<DrawingInBackgroundLine> lineDrawables;
        ArrayList<DrawingInBackgroundLine> lineDrawablesTmp;
        private AnimatedFloat premiumT;
        final /* synthetic */ EmojiView this$0;
        private SparseArray<TouchDownInfo> touches;
        ArrayList<ArrayList<ImageViewEmoji>> unusedArrays;
        ArrayList<DrawingInBackgroundLine> unusedLineDrawables;
        SparseArray<ArrayList<ImageViewEmoji>> viewsGroupedByLines;

        public class DrawingInBackgroundLine extends DrawingInBackgroundThreadDrawable {
            private OvershootInterpolator appearScaleInterpolator;
            ArrayList<ImageViewEmoji> drawInBackgroundViews;
            ArrayList<ImageViewEmoji> imageViewEmojis;
            public int position;
            public int startOffset;
            final /* synthetic */ EmojiGridView this$1;

            public DrawingInBackgroundLine(EmojiGridView r2) {
                this.this$1 = r2;
                this.drawInBackgroundViews = new ArrayList();
                this.appearScaleInterpolator = new OvershootInterpolator(3.0f);
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void draw(Canvas r9, long r10, int r12, int r13, float r14) {
                ArrayList<ImageViewEmoji> r0 = this.imageViewEmojis;
                if (r0 != null) goto L5;
                return;
            L5:
                int r2 = 0;
                boolean r3 = true;
                if (r0.size() > 4) goto L8;
            L13:
                boolean r02 = true;
            L14:
                if (r02 == false) goto L16;
            L34:
                r3 = r02;
            L35:
                if (r3 == false) goto L38;
                prepareDraw(System.currentTimeMillis());
                drawInUiThread(r9, r14);
                reset();
                return;
            L38:
                super.draw(r9, r10, r12, r13, r14);
                return;
            L16:
                if (EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) > 0) goto L18;
            L20:
                boolean r1 = false;
            L22:
                if (r2 >= this.imageViewEmojis.size()) goto L34;
                ImageViewEmoji r4 = this.imageViewEmojis.get(r2);
                if (r4.pressedProgress != 0.0f) goto L35;
                if (r4.backAnimator != null) goto L35;
                if (r4.position <= EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0)) goto L33;
                if (r4.position >= EmojiView.m10293$$Nest$fgetanimateExpandToPosition(this.this$1.this$0)) goto L33;
                if (r1 == true) goto L35;
            L33:
                r2 = r2 + 1;
                goto L22
            L18:
                if ((SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) >= this.this$1.animateExpandDuration()) goto L20;
                r1 = true;
                goto L22
            L8:
                if (SharedConfig.getDevicePerformanceClass() == 0) goto L13;
                if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD) == false) goto L13;
                r02 = false;
                goto L14
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInBackground(Canvas r6) {
                int r1 = 0;
            L4:
                if (r1 >= this.drawInBackgroundViews.size()) goto L9;
                ImageViewEmoji r2 = this.drawInBackgroundViews.get(r1);
                AnimatedEmojiDrawable r3 = r2.drawable;
                if (r3 == null) goto L8;
                r3.draw(r6, ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r2)[this.threadIndex], false);
            L8:
                r1 = r1 + 1;
                goto L4
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInUiThread(Canvas r20, float r21) {
                if (this.imageViewEmojis == null) goto L43;
                r20.save();
                float r3 = 0.0f;
                r20.translate(-this.startOffset, 0.0f);
                int r4 = 0;
                float r2 = r21;
            L6:
                if (r4 >= this.imageViewEmojis.size()) goto L37;
                ImageViewEmoji r5 = this.imageViewEmojis.get(r4);
                if (r5.getSpan() == null) goto L12;
                AnimatedEmojiDrawable r6 = (AnimatedEmojiDrawable) EmojiView.m10294$$Nest$fgetanimatedEmojiDrawables(this.this$1.this$0).get(ImageViewEmoji.m10526$$Nest$fgetspan(r5).getDocumentId());
                if (r6 == null) goto L12;
                int r7 = (int) (r5.getHeight() * 0.03f);
                Rect r8 = AndroidUtilities.rectTmp2;
                r8.set(r5.getLeft() + r5.getPaddingLeft(), r7, r5.getRight() - r5.getPaddingRight(), ((r5.getMeasuredHeight() + r7) - r5.getPaddingBottom()) - r5.getPaddingTop());
                float r72 = r5.pressedProgress;
                if (r72 == r3) goto L16;
                float r73 = (((1.0f - r72) * 0.2f) + 0.8f) * 1.0f;
            L18:
                if (EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) > 0) goto L20;
            L31:
                float r11 = r3;
            L32:
                r6.setAlpha((int) (255.0f * r2));
                r6.setBounds(r8);
                r6.setColorFilter(EmojiView.m10295$$Nest$fgetanimatedEmojiTextColorFilter(this.this$1.this$0));
                if (r73 == 1.0f) goto L35;
                r20.save();
                r20.scale(r73, r73, r8.centerX(), r8.centerY());
                r6.draw(r20);
                r20.restore();
            L36:
                r4 = r4 + 1;
                r3 = r11;
                goto L6
            L35:
                r6.draw(r20);
                goto L36
            L20:
                if ((SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) >= this.this$1.animateExpandDuration()) goto L31;
                if (EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0) < 0) goto L31;
                if (EmojiView.m10293$$Nest$fgetanimateExpandToPosition(this.this$1.this$0) < 0) goto L31;
                if (EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) <= 0) goto L31;
                int r52 = this.this$1.getChildAdapterPosition(r5) - EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0);
                int r9 = EmojiView.m10293$$Nest$fgetanimateExpandToPosition(this.this$1.this$0) - EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0);
                if (r52 < 0) goto L31;
                if (r52 >= r9) goto L31;
                float r112 = this.this$1.animateExpandAppearDuration();
                float r12 = MathUtils.clamp(((SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) - (0.45f * r112)) / this.this$1.animateExpandCrossfadeDuration(), r3, 1.0f);
                float r32 = (SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) / r112;
                r11 = 0.0f;
                float r33 = CubicBezierInterpolator.EASE_OUT.getInterpolation(MathUtils.clamp(r32, 0.0f, 1.0f));
                float r13 = r52;
                float r14 = r9;
                AndroidUtilities.cascade(r12, r13, r14, r14 / 5.0f);
                float r122 = r14 / 4.0f;
                float r132 = AndroidUtilities.cascade(r33, r13, r14, r122);
                int r142 = r9 / 4;
                r73 = r73 * ((this.appearScaleInterpolator.getInterpolation(AndroidUtilities.cascade(r33, r52 + r142, r9 + r142, r122)) * 0.5f) + 0.5f);
                r2 = r2 * r132;
                goto L32
            L16:
                r73 = 1.0f;
            L12:
                r11 = r3;
                goto L36
            L37:
                r20.restore();
                return;
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void onFrameReady() {
                super.onFrameReady();
                int r0 = 0;
            L4:
                if (r0 >= this.drawInBackgroundViews.size()) goto L9;
                ImageViewEmoji r1 = this.drawInBackgroundViews.get(r0);
                if (ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1) == null) goto L8;
                ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1)[this.threadIndex].release();
            L8:
                r0 = r0 + 1;
                goto L4
            L9:
                EmojiView.m10314$$Nest$fgetemojiGridView(this.this$1.this$0).invalidate();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void prepareDraw(long r10) {
                this.drawInBackgroundViews.clear();
                int r0 = 0;
            L4:
                if (r0 >= this.imageViewEmojis.size()) goto L19;
                ImageViewEmoji r1 = this.imageViewEmojis.get(r0);
                if (r1.getSpan() == null) goto L18;
                AnimatedEmojiDrawable r2 = (AnimatedEmojiDrawable) EmojiView.m10294$$Nest$fgetanimatedEmojiDrawables(this.this$1.this$0).get(ImageViewEmoji.m10526$$Nest$fgetspan(r1).getDocumentId());
                if (r2 == null) goto L18;
                if (r2.getImageReceiver() == null) goto L18;
                r2.update(r10);
                ImageReceiver.BackgroundThreadDrawHolder[] r3 = ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1);
                int r4 = this.threadIndex;
                ImageReceiver r5 = r2.getImageReceiver();
                ImageReceiver.BackgroundThreadDrawHolder[] r6 = ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1);
                int r7 = this.threadIndex;
                r3[r4] = r5.setDrawInBackgroundThread(r6[r7], r7);
                ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1)[this.threadIndex].time = r10;
                ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1)[this.threadIndex].overrideAlpha = 1.0f;
                r2.setAlpha(255);
                int r32 = (int) (r1.getHeight() * 0.03f);
                Rect r42 = AndroidUtilities.rectTmp2;
                r42.set((r1.getLeft() + r1.getPaddingLeft()) - this.startOffset, r32, (r1.getRight() - r1.getPaddingRight()) - this.startOffset, ((r1.getMeasuredHeight() + r32) - r1.getPaddingTop()) - r1.getPaddingBottom());
                ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1)[this.threadIndex].setBounds(r42);
                r1.drawable = r2;
                r1.imageReceiver = r2.getImageReceiver();
                ImageReceiver.BackgroundThreadDrawHolder r33 = ImageViewEmoji.m10523$$Nest$fgetbackgroundThreadDrawHolder(r1)[this.threadIndex];
                if (r2.canOverrideColor() == false) goto L16;
                PorterDuffColorFilter r22 = EmojiView.m10295$$Nest$fgetanimatedEmojiTextColorFilter(this.this$1.this$0);
            L17:
                r33.colorFilter = r22;
                this.drawInBackgroundViews.add(r1);
                goto L18
            L16:
                r22 = null;
            L18:
                r0 = r0 + 1;
                goto L4
            }
        }

        public class TouchDownInfo {
            final /* synthetic */ EmojiGridView this$1;
            long time;
            View view;
            float x;
            float y;

            public TouchDownInfo(EmojiGridView r1) {
                this.this$1 = r1;
            }
        }

        public EmojiGridView(EmojiView r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.viewsGroupedByLines = new SparseArray();
            this.lineDrawables = new ArrayList();
            this.lineDrawablesTmp = new ArrayList();
            this.unusedArrays = new ArrayList();
            this.unusedLineDrawables = new ArrayList();
            this.lastChildCount = -1;
            this.headerWidthsCache = new SparseIntArray();
            this.premiumT = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        private AnimatedEmojiSpan[] getAnimatedEmojiSpans() {
            AnimatedEmojiSpan[] r0 = new AnimatedEmojiSpan[EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildCount()];
            int r1 = 0;
        L4:
            if (r1 >= EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildCount()) goto L9;
            View r2 = EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildAt(r1);
            if ((r2 instanceof ImageViewEmoji) == false) goto L8;
            r0[r1] = ((ImageViewEmoji) r2).getSpan();
        L8:
            r1 = r1 + 1;
            goto L4
        L9:
            return r0;
        }

        public long animateExpandAppearDuration() {
            return Math.max(600, Math.min(55, EmojiView.m10293$$Nest$fgetanimateExpandToPosition(this.this$0) - EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$0)) * 40);
        }

        public long animateExpandCrossfadeDuration() {
            return Math.max(400, Math.min(45, EmojiView.m10293$$Nest$fgetanimateExpandToPosition(this.this$0) - EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$0)) * 35);
        }

        public long animateExpandDuration() {
            return (animateExpandAppearDuration() + animateExpandCrossfadeDuration()) + 150;
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public void captureChildren(Canvas r18, RectF r19, long r20) {
            Canvas r1 = r18;
            super.captureChildren(r18, r19, r20);
            r1.save();
            r18.clipRect(r19);
            if (this.lastChildCount == getChildCount()) goto L5;
            updateEmojiDrawables();
            this.lastChildCount = getChildCount();
        L5:
            int r8 = 0;
            int r2 = 0;
        L6:
            int r3 = this.viewsGroupedByLines.size();
            SparseArray<ArrayList<ImageViewEmoji>> r4 = this.viewsGroupedByLines;
            if (r2 >= r3) goto L9;
            ArrayList<ImageViewEmoji> r32 = r4.valueAt(r2);
            r32.clear();
            this.unusedArrays.add(r32);
            r2 = r2 + 1;
            goto L6
        L9:
            r4.clear();
            if (EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$0) > 0) goto L12;
        L18:
            boolean r10 = false;
        L20:
            if (EmojiView.m10294$$Nest$fgetanimatedEmojiDrawables(this.this$0) != null) goto L22;
        L48:
            this.lineDrawablesTmp.clear();
            this.lineDrawablesTmp.addAll(this.lineDrawables);
            this.lineDrawables.clear();
            long r33 = System.currentTimeMillis();
            int r102 = 0;
        L49:
            DrawingInBackgroundLine r5 = null;
            if (r102 >= this.viewsGroupedByLines.size()) goto L74;
            ArrayList<ImageViewEmoji> r22 = this.viewsGroupedByLines.valueAt(r102);
            ImageViewEmoji r6 = r22.get(0);
            int r7 = r6.position;
            int r13 = 0;
        L53:
            if (r13 >= this.lineDrawablesTmp.size()) goto L58;
            if (this.lineDrawablesTmp.get(r13).position == r7) goto L56;
            r13 = r13 + 1;
            goto L53
        L56:
            r5 = this.lineDrawablesTmp.get(r13);
            this.lineDrawablesTmp.remove(r13);
        L58:
            if (r5 == null) goto L60;
        L64:
            this.lineDrawables.add(r5);
            r5.imageViewEmojis = r22;
            r1.save();
            r1.translate(r6.getLeft(), r6.getY() + r6.getPaddingTop());
            r5.startOffset = r6.getLeft();
            int r23 = getMeasuredWidth() - (r6.getLeft() * 2);
            int r62 = r6.getMeasuredHeight() - r6.getPaddingBottom();
            if (r23 <= 0) goto L72;
            if (r62 <= 0) goto L72;
            if (Build.VERSION.SDK_INT >= 30) goto L70;
        L71:
            r5.draw(r1, r33, r23, r62, 1.0f);
            goto L72
        L70:
            if (r1.quickReject(0.0f, 0.0f, r23, r62) == false) goto L71;
        L72:
            r18.restore();
            invalidate();
            r102 = r102 + 1;
            r1 = r18;
            goto L49
        L60:
            if (this.unusedLineDrawables.isEmpty() == true) goto L62;
            ArrayList<DrawingInBackgroundLine> r52 = this.unusedLineDrawables;
            r5 = r52.remove(r52.size() - 1);
        L63:
            r5.position = r7;
            r5.onAttachToWindow();
            goto L64
        L62:
            r5 = new DrawingInBackgroundLine(this);
        L74:
            if (r8 >= this.lineDrawablesTmp.size()) goto L80;
            if (this.unusedLineDrawables.size() >= 3) goto L78;
            this.unusedLineDrawables.add(this.lineDrawablesTmp.get(r8));
            this.lineDrawablesTmp.get(r8).imageViewEmojis = null;
            this.lineDrawablesTmp.get(r8).reset();
        L79:
            r8 = r8 + 1;
            goto L74
        L78:
            this.lineDrawablesTmp.get(r8).onDetachFromWindow();
            goto L79
        L80:
            this.lineDrawablesTmp.clear();
            r18.restore();
            return;
        L22:
            if (EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0) == null) goto L48;
            int r132 = 0;
        L25:
            if (r132 >= EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildCount()) goto L48;
            View r14 = EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildAt(r132);
            if ((r14 instanceof ImageViewEmoji) == false) goto L36;
            int r24 = r14.getTop() + ((int) r14.getTranslationY());
            ArrayList<ImageViewEmoji> r34 = this.viewsGroupedByLines.get(r24);
            if (r34 == null) goto L31;
        L35:
            r34.add((ImageViewEmoji) r14);
            goto L36
        L31:
            if (this.unusedArrays.isEmpty() == true) goto L33;
            ArrayList<ArrayList<ImageViewEmoji>> r35 = this.unusedArrays;
            r34 = r35.remove(r35.size() - 1);
        L34:
            this.viewsGroupedByLines.put(r24, r34);
            goto L35
        L33:
            r34 = new ArrayList();
        L36:
            if (r10 == false) goto L47;
            if (r14 == null) goto L47;
            if (getChildAdapterPosition(r14) != (EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$0) - 1)) goto L47;
            float r25 = CubicBezierInterpolator.EASE_OUT.getInterpolation(MathUtils.clamp((SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$0)) / 140.0f, 0.0f, 1.0f));
            if (r25 >= 1.0f) goto L47;
            if (Build.VERSION.SDK_INT >= 30) goto L45;
        L46:
            float r15 = 1.0f - r25;
            r1.saveLayerAlpha(r14.getLeft(), r14.getTop(), r14.getRight(), r14.getBottom(), (int) (255.0f * r15), 31);
            r1.translate(r14.getLeft(), r14.getTop());
            float r152 = (r15 * 0.5f) + 0.5f;
            r1.scale(r152, r152, r14.getWidth() / 2.0f, r14.getHeight() / 2.0f);
            EmojiView.m10290$$Nest$fgetanimateExpandFromButton(this.this$0).draw(r1);
            r1.restore();
            goto L47
        L45:
            if (r1.quickReject(r14.getLeft(), r14.getTop(), r14.getRight(), r14.getBottom()) == false) goto L46;
        L47:
            r132 = r132 + 1;
            goto L25
        L12:
            if ((SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$0)) >= animateExpandDuration()) goto L18;
            if (EmojiView.m10290$$Nest$fgetanimateExpandFromButton(this.this$0) == null) goto L18;
            if (EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$0) < 0) goto L18;
            r10 = true;
            goto L20
        }

        public void clearAllTouches() {
            if (this.touches != null) goto L5;
            return;
        L5:
            if (this.touches.size() <= 0) goto L24;
            TouchDownInfo r0 = this.touches.valueAt(0);
            this.touches.removeAt(0);
            if (r0 == null) goto L5;
            View r2 = r0.view;
            if (r2 != null) goto L11;
        L13:
            View r02 = r0.view;
            if (r02 == null) goto L5;
            r02.setPressed(false);
            goto L5
        L11:
            if ((r2.getBackground() instanceof RippleDrawable) == false) goto L13;
            r0.view.getBackground().setState(new int[0]);
            goto L13
        }

        public void clearTouchesFor(View r6) {
            if (this.touches == null) goto L18;
            int r1 = 0;
        L6:
            if (r1 >= this.touches.size()) goto L23;
            TouchDownInfo r2 = this.touches.valueAt(r1);
            if (r2.view != r6) goto L17;
            this.touches.removeAt(r1);
            r1 = r1 - 1;
            View r3 = r2.view;
            if (r3 != null) goto L12;
        L14:
            View r22 = r2.view;
            if (r22 == null) goto L17;
            r22.setPressed(false);
            goto L17
        L12:
            if ((r3.getBackground() instanceof RippleDrawable) == false) goto L14;
            r2.view.getBackground().setState(new int[0]);
        L17:
            r1 = r1 + 1;
            goto L6
        L23:
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r14) {
            super.dispatchDraw(r14);
            EmojiView.m10350$$Nest$fgetglassEngine(this.this$0).invalidate();
            if (this.lastChildCount == getChildCount()) goto L5;
            updateEmojiDrawables();
            this.lastChildCount = getChildCount();
        L5:
            int r7 = 0;
            int r0 = 0;
        L6:
            int r1 = this.viewsGroupedByLines.size();
            SparseArray<ArrayList<ImageViewEmoji>> r2 = this.viewsGroupedByLines;
            if (r0 >= r1) goto L9;
            ArrayList<ImageViewEmoji> r12 = r2.valueAt(r0);
            r12.clear();
            this.unusedArrays.add(r12);
            r0 = r0 + 1;
            goto L6
        L9:
            r2.clear();
            if (EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$0) > 0) goto L12;
        L18:
            boolean r9 = false;
        L20:
            if (EmojiView.m10294$$Nest$fgetanimatedEmojiDrawables(this.this$0) != null) goto L22;
        L44:
            this.lineDrawablesTmp.clear();
            this.lineDrawablesTmp.addAll(this.lineDrawables);
            this.lineDrawables.clear();
            long r22 = System.currentTimeMillis();
            int r92 = 0;
        L45:
            DrawingInBackgroundLine r4 = null;
            if (r92 >= this.viewsGroupedByLines.size()) goto L66;
            ArrayList<ImageViewEmoji> r13 = this.viewsGroupedByLines.valueAt(r92);
            ImageViewEmoji r5 = r13.get(0);
            int r6 = r5.position;
            int r10 = 0;
        L49:
            if (r10 >= this.lineDrawablesTmp.size()) goto L54;
            if (this.lineDrawablesTmp.get(r10).position == r6) goto L52;
            r10 = r10 + 1;
            goto L49
        L52:
            r4 = this.lineDrawablesTmp.get(r10);
            this.lineDrawablesTmp.remove(r10);
        L54:
            if (r4 == null) goto L56;
        L60:
            this.lineDrawables.add(r4);
            r4.imageViewEmojis = r13;
            r14.save();
            r14.translate(r5.getLeft(), r5.getY() + r5.getPaddingTop());
            r4.startOffset = r5.getLeft();
            int r15 = getMeasuredWidth() - (r5.getLeft() * 2);
            int r52 = r5.getMeasuredHeight() - r5.getPaddingBottom();
            if (r15 <= 0) goto L64;
            if (r52 <= 0) goto L64;
            r4.draw(r14, r22, r15, r52, 1.0f);
        L64:
            r14.restore();
            invalidate();
            r92 = r92 + 1;
            goto L45
        L56:
            if (this.unusedLineDrawables.isEmpty() == true) goto L58;
            ArrayList<DrawingInBackgroundLine> r42 = this.unusedLineDrawables;
            r4 = r42.remove(r42.size() - 1);
        L59:
            r4.position = r6;
            r4.onAttachToWindow();
            goto L60
        L58:
            r4 = new DrawingInBackgroundLine(this);
        L66:
            if (r7 >= this.lineDrawablesTmp.size()) goto L72;
            if (this.unusedLineDrawables.size() >= 3) goto L70;
            this.unusedLineDrawables.add(this.lineDrawablesTmp.get(r7));
            this.lineDrawablesTmp.get(r7).imageViewEmojis = null;
            this.lineDrawablesTmp.get(r7).reset();
        L71:
            r7 = r7 + 1;
            goto L66
        L70:
            this.lineDrawablesTmp.get(r7).onDetachFromWindow();
            goto L71
        L72:
            this.lineDrawablesTmp.clear();
            return;
        L22:
            if (EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0) == null) goto L44;
            int r102 = 0;
        L25:
            if (r102 >= EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildCount()) goto L44;
            View r11 = EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildAt(r102);
            if ((r11 instanceof ImageViewEmoji) == false) goto L36;
            int r02 = r11.getTop() + ((int) r11.getTranslationY());
            ArrayList<ImageViewEmoji> r16 = this.viewsGroupedByLines.get(r02);
            if (r16 == null) goto L31;
        L35:
            r16.add((ImageViewEmoji) r11);
            goto L36
        L31:
            if (this.unusedArrays.isEmpty() == true) goto L33;
            ArrayList<ArrayList<ImageViewEmoji>> r17 = this.unusedArrays;
            r16 = r17.remove(r17.size() - 1);
        L34:
            this.viewsGroupedByLines.put(r02, r16);
            goto L35
        L33:
            r16 = new ArrayList();
        L36:
            if (r9 == false) goto L43;
            if (r11 == null) goto L43;
            if (getChildAdapterPosition(r11) != (EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$0) - 1)) goto L43;
            float r03 = CubicBezierInterpolator.EASE_OUT.getInterpolation(MathUtils.clamp((SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$0)) / 140.0f, 0.0f, 1.0f));
            if (r03 >= 1.0f) goto L43;
            float r122 = 1.0f - r03;
            r14.saveLayerAlpha(r11.getLeft(), r11.getTop(), r11.getRight(), r11.getBottom(), (int) (255.0f * r122), 31);
            r14.translate(r11.getLeft(), r11.getTop());
            float r123 = (r122 * 0.5f) + 0.5f;
            r14.scale(r123, r123, r11.getWidth() / 2.0f, r11.getHeight() / 2.0f);
            EmojiView.m10290$$Nest$fgetanimateExpandFromButton(this.this$0).draw(r14);
            r14.restore();
        L43:
            r102 = r102 + 1;
            goto L25
        L12:
            if ((SystemClock.elapsedRealtime() - EmojiView.m10292$$Nest$fgetanimateExpandStartTime(this.this$0)) >= animateExpandDuration()) goto L18;
            if (EmojiView.m10290$$Nest$fgetanimateExpandFromButton(this.this$0) == null) goto L18;
            if (EmojiView.m10291$$Nest$fgetanimateExpandFromPosition(this.this$0) < 0) goto L18;
            r9 = true;
            goto L20
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r13) {
            if (r13.getActionMasked() != 5) goto L5;
        L8:
            boolean r0 = true;
        L10:
            if (r13.getActionMasked() != 6) goto L12;
        L15:
            boolean r1 = true;
        L17:
            if (r13.getActionMasked() != 3) goto L19;
            boolean r4 = true;
        L20:
            if (r0 == true) goto L23;
            if (r1 == true) goto L23;
            if (r4 == true) goto L23;
        L66:
            if (super.dispatchTouchEvent(r13) == true) goto L71;
            if (r4 == false) goto L69;
            return false;
        L69:
            if (this.touches.size() > 0) goto L71;
            return false;
        L71:
            return true;
        L23:
            int r12 = r13.getActionIndex();
            int r6 = r13.getPointerId(r12);
            if (this.touches != null) goto L26;
            this.touches = new SparseArray();
        L26:
            float r7 = r13.getX(r12);
            float r14 = r13.getY(r12);
            View r8 = findChildViewUnder(r7, r14);
            if (r0 == false) goto L33;
            if (r8 == null) goto L66;
            TouchDownInfo r02 = new TouchDownInfo(this);
            r02.x = r7;
            r02.y = r14;
            r02.time = SystemClock.elapsedRealtime();
            r02.view = r8;
            if ((r8.getBackground() instanceof RippleDrawable) == false) goto L32;
            r8.getBackground().setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});
        L32:
            r02.view.setPressed(true);
            this.touches.put(r6, r02);
            stopScroll();
            goto L66
        L33:
            TouchDownInfo r03 = this.touches.get(r6);
            this.touches.remove(r6);
            if (r8 == null) goto L55;
            if (r03 == null) goto L55;
            if (Math.sqrt(Math.pow(r7 - r03.x, 2.0d) + Math.pow(r14 - r03.y, 2.0d)) >= (AndroidUtilities.touchSlop * 3.0f)) goto L55;
            if (r4 == true) goto L55;
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).isShowing() == true) goto L42;
        L43:
            View r15 = r03.view;
            int r62 = getChildAdapterPosition(r15);
            if ((r15 instanceof ImageViewEmoji) == false) goto L49;
            EmojiView.m10445$$Nest$msendEmoji(this.this$0, (ImageViewEmoji) r15, null);
            performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L72
        L73:
            goto L55
        L49:
            if ((r15 instanceof EmojiPackExpand) == false) goto L53;
            EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0).expand(r62, (EmojiPackExpand) r15);
            performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L72
            goto L73
        L53:
            if (r15 == null) goto L55;
            r15.callOnClick();
            goto L55
        L42:
            if ((SystemClock.elapsedRealtime() - r03.time) < ViewConfiguration.getLongPressTimeout()) goto L43;
        L55:
            if (r03 == null) goto L61;
            View r16 = r03.view;
            if (r16 == null) goto L61;
            if ((r16.getBackground() instanceof RippleDrawable) == false) goto L61;
            r03.view.getBackground().setState(new int[0]);
        L61:
            if (r03 == null) goto L66;
            View r04 = r03.view;
            if (r04 == null) goto L66;
            r04.setPressed(false);
            goto L66
        L19:
            r4 = false;
            goto L20
        L12:
            if (r13.getActionMasked() == 1) goto L15;
            r1 = false;
            goto L17
        L5:
            if (r13.getActionMasked() == 0) goto L8;
            r0 = false;
            goto L10
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            updateEmojiDrawables();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AnimatedEmojiSpan.release(this, EmojiView.m10294$$Nest$fgetanimatedEmojiDrawables(this.this$0));
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r1 >= this.lineDrawables.size()) goto L6;
            this.lineDrawables.get(r1).onDetachFromWindow();
            r1 = r1 + 1;
        L6:
            int r12 = this.unusedLineDrawables.size();
            ArrayList<DrawingInBackgroundLine> r2 = this.unusedLineDrawables;
            if (r0 >= r12) goto L9;
            r2.get(r0).onDetachFromWindow();
            r0 = r0 + 1;
            goto L6
        L9:
            r2.addAll(this.lineDrawables);
            this.lineDrawables.clear();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r9) {
            if (EmojiView.m10356$$Nest$fgetignorePagerScroll(this.this$0) == false) goto L5;
            return false;
        L5:
            boolean r8 = ContentPreviewViewer.getInstance().onInterceptTouchEvent(r9, this, 0, EmojiView.m10305$$Nest$fgetcontentPreviewViewerDelegate(this.this$0), this.resourcesProvider);
            if (super.onInterceptTouchEvent(r9) == true) goto L10;
            if (r8 == true) goto L12;
            return false;
        L12:
            return true;
        L10:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            if (EmojiView.m10362$$Nest$fgetneedEmojiSearch(this.this$0) == true) goto L5;
        L7:
            super.onLayout(r4, r5, r6, r7, r8);
            EmojiView.m10421$$Nest$mcheckEmojiSearchFieldScroll(this.this$0, true);
            updateEmojiDrawables();
            return;
        L5:
            if (EmojiView.m10332$$Nest$fgetfirstEmojiAttach(this.this$0) == false) goto L7;
            this.ignoreLayout = true;
            EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
            EmojiView.m10406$$Nest$fputfirstEmojiAttach(this.this$0, false);
            this.ignoreLayout = false;
            goto L7
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r6, int r7) {
            this.ignoreLayout = true;
            int r1 = View.MeasureSpec.getSize(r6);
            int r2 = EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount();
            GridLayoutManager r3 = EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0);
            if (AndroidUtilities.isTablet() == false) goto L5;
            float r4 = 60.0f;
        L6:
            r3.setSpanCount(Math.max(1, r1 / AndroidUtilities.dp(r4)));
            this.ignoreLayout = false;
            super.onMeasure(r6, r7);
            if (r2 == EmojiView.m10315$$Nest$fgetemojiLayoutManager(this.this$0).getSpanCount()) goto L10;
            EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0).notifyDataSetChanged();
            return;
        L10:
            return;
        L5:
            r4 = 45.0f;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrollStateChanged(int r2) {
            super.onScrollStateChanged(r2);
            if (r2 == 0) goto L5;
            return;
        L5:
            if (canScrollVertically(-1) == true) goto L7;
        L8:
            EmojiView.m10448$$Nest$mshowBottomTab(this.this$0, true, true);
        L10:
            if (canScrollVertically(1) == true) goto L14;
            EmojiView.m10427$$Nest$mcheckTabsY(this.this$0, 1, AndroidUtilities.dp(36.0f));
            return;
        L14:
            return;
        L7:
            if (canScrollVertically(1) == true) goto L10;
            goto L8
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r8) {
            if (EmojiView.m10322$$Nest$fgetemojiTouchedView(this.this$0) != null) goto L5;
        L52:
            EmojiView.m10397$$Nest$fputemojiLastX(this.this$0, r8.getX());
            EmojiView.m10398$$Nest$fputemojiLastY(this.this$0, r8.getY());
            return super.onTouchEvent(r8);
        L5:
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0) == null) goto L52;
            if (r8.getAction() == 1) goto L22;
            if (r8.getAction() == 3) goto L22;
            if (r8.getAction() != 2) goto L46;
            if (EmojiView.m10323$$Nest$fgetemojiTouchedX(this.this$0) != (-10000.0f)) goto L16;
        L20:
            getLocationOnScreen(EmojiView.m10361$$Nest$fgetlocation(this.this$0));
            float r0 = EmojiView.m10361$$Nest$fgetlocation(this.this$0)[0] + r8.getX();
            EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).pickerView.getLocationOnScreen(EmojiView.m10361$$Nest$fgetlocation(this.this$0));
            EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).onTouchMove((int) (r0 - (EmojiView.m10361$$Nest$fgetlocation(this.this$0)[0] + AndroidUtilities.dp(3.0f))));
            goto L46
        L16:
            if (Math.abs(EmojiView.m10323$$Nest$fgetemojiTouchedX(this.this$0) - r8.getX()) <= AndroidUtilities.getPixelsInCM(0.2f, true)) goto L18;
        L19:
            EmojiView.m10403$$Nest$fputemojiTouchedX(this.this$0, -10000.0f);
            EmojiView.m10404$$Nest$fputemojiTouchedY(this.this$0, -10000.0f);
            goto L20
        L18:
            if (Math.abs(EmojiView.m10324$$Nest$fgetemojiTouchedY(this.this$0) - r8.getY()) > AndroidUtilities.getPixelsInCM(0.2f, false)) goto L19;
        L46:
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0) != null) goto L48;
        L51:
            return true;
        L48:
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).isCompound() == false) goto L51;
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).isShowing() == false) goto L52;
        L22:
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0) == null) goto L40;
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).isShowing() == false) goto L40;
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).isCompound() == true) goto L40;
            EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).dismiss();
            String r02 = EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).getSkinTone(0);
            String r2 = (String) EmojiView.m10322$$Nest$fgetemojiTouchedView(this.this$0).getTag();
            if (ImageViewEmoji.m10524$$Nest$fgetisRecent(EmojiView.m10322$$Nest$fgetemojiTouchedView(this.this$0)) == true) goto L35;
            if (r02 == null) goto L31;
            Emoji.emojiColor.put(r2, r02);
            r2 = EmojiView.addColorToCode(r2, r02);
        L32:
            EmojiView.m10322$$Nest$fgetemojiTouchedView(this.this$0).setImageDrawable(Emoji.getEmojiBigDrawable(r2), ImageViewEmoji.m10524$$Nest$fgetisRecent(EmojiView.m10322$$Nest$fgetemojiTouchedView(this.this$0)));
            EmojiView r03 = this.this$0;
            EmojiView.m10445$$Nest$msendEmoji(r03, EmojiView.m10322$$Nest$fgetemojiTouchedView(r03), null);
            performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L54
        L34:
            Emoji.saveEmojiColors();
            goto L40
        L31:
            Emoji.emojiColor.remove(r2);
            goto L32
        L35:
            String r1 = r2.replace("🏻", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏼", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏽", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏾", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏿", _UrlKt.FRAGMENT_ENCODE_SET);
            EmojiView r22 = this.this$0;
            if (r02 == null) goto L38;
            EmojiView.m10445$$Nest$msendEmoji(r22, EmojiView.m10322$$Nest$fgetemojiTouchedView(r22), EmojiView.addColorToCode(r1, r02));
            goto L40
        L38:
            EmojiView.m10445$$Nest$msendEmoji(r22, EmojiView.m10322$$Nest$fgetemojiTouchedView(r22), r1);
        L40:
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0) != null) goto L42;
        L43:
            EmojiView.m10402$$Nest$fputemojiTouchedView(this.this$0, null);
        L44:
            EmojiView.m10403$$Nest$fputemojiTouchedX(this.this$0, -10000.0f);
            EmojiView.m10404$$Nest$fputemojiTouchedY(this.this$0, -10000.0f);
            goto L46
        L42:
            if (EmojiView.m10304$$Nest$fgetcolorPickerView(this.this$0).isCompound() == true) goto L44;
            goto L43
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        public void updateEmojiDrawables() {
            EmojiView r0 = this.this$0;
            EmojiView.m10394$$Nest$fputanimatedEmojiDrawables(r0, AnimatedEmojiSpan.update(r0.emojiCacheType, this, getAnimatedEmojiSpans(), EmojiView.m10294$$Nest$fgetanimatedEmojiDrawables(this.this$0)));
        }
    }

    public static class EmojiPack {
        public ArrayList<TLRPC.Document> documents;
        public boolean expanded;
        public boolean featured;
        public boolean forGroup;
        public boolean free;
        public int index;
        public boolean installed;
        public TLRPC.InputStickerSet needLoadSet;
        public int resId;
        public TLRPC.StickerSet set;
        public Long thumbDocumentId;

        public EmojiPack() {
            this.documents = new ArrayList();
        }
    }

    public class EmojiPackButton extends FrameLayout {
        AnimatedTextView addButtonTextView;
        FrameLayout addButtonView;
        PremiumButtonView premiumButtonView;
        final /* synthetic */ EmojiView this$0;

        public EmojiPackButton(EmojiView r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            AnimatedTextView r0 = new AnimatedTextView(getContext());
            this.addButtonTextView = r0;
            r0.setAnimationProperties(0.3f, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.addButtonTextView.setTextSize(AndroidUtilities.dp(14.0f));
            this.addButtonTextView.setTypeface(AndroidUtilities.bold());
            this.addButtonTextView.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(r8, Theme.key_featuredStickers_buttonText));
            this.addButtonTextView.setGravity(17);
            FrameLayout r92 = new FrameLayout(getContext());
            this.addButtonView = r92;
            r92.setBackground(Theme.AdaptiveRipple.filledRect(EmojiView.m10432$$Nest$mgetThemedColor(r8, Theme.key_featuredStickers_addButton), new float[]{8.0f}));
            this.addButtonView.addView(this.addButtonTextView, LayoutHelper.createFrame(-1, -2, 17));
            addView(this.addButtonView, LayoutHelper.createFrame(-1, -1.0f));
            PremiumButtonView r93 = new PremiumButtonView(getContext(), false, EmojiView.m10370$$Nest$fgetresourcesProvider(r8));
            this.premiumButtonView = r93;
            r93.setIcon(R.raw.unlock_icon);
            addView(this.premiumButtonView, LayoutHelper.createFrame(-1, -1.0f));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(11.0f));
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec((AndroidUtilities.dp(44.0f) + getPaddingTop()) + getPaddingBottom(), TLObject.FLAG_30));
        }
    }

    public static class EmojiPackExpand extends FrameLayout {
        public TextView textView;

        public EmojiPackExpand(Context r4, Theme.ResourcesProvider r5) {
            super(r4);
            TextView r0 = new TextView(r4);
            this.textView = r0;
            r0.setTextSize(1, 13.0f);
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhite, r5));
            this.textView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(11.0f), ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_chat_emojiPanelStickerSetName, r5), 99)));
            this.textView.setTypeface(AndroidUtilities.bold());
            this.textView.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.66f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(2.0f));
            addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
        }
    }

    public class EmojiPackHeader extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
        TextView addButtonView;
        FrameLayout buttonsView;
        private int currentButtonState;
        boolean divider;
        private Paint dividerPaint;
        SimpleTextView headerView;
        RLottieImageView lockView;
        TextView markView;
        private EmojiPack pack;
        PremiumButtonView premiumButtonView;
        TextView removeButtonView;
        private AnimatorSet stateAnimator;
        final /* synthetic */ EmojiView this$0;
        private TLRPC.InputStickerSet toInstall;
        private TLRPC.InputStickerSet toUninstall;

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiPackHeader$1, reason: invalid class name */
        public class AnonymousClass1 extends BaseFragment {
            final /* synthetic */ EmojiPackHeader this$1;

            public AnonymousClass1(EmojiPackHeader r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public int getCurrentAccount() {
                return this.this$1.this$0.currentAccount;
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public View getFragmentView() {
                return EmojiView.m10301$$Nest$fgetbulletinContainer(this.this$1.this$0);
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public FrameLayout getLayoutContainer() {
                return EmojiView.m10301$$Nest$fgetbulletinContainer(this.this$1.this$0);
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public Theme.ResourcesProvider getResourceProvider() {
                return EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$1.this$0);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiPackHeader$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ EmojiPackHeader this$1;
            final /* synthetic */ int val$state;

            public AnonymousClass2(EmojiPackHeader r1, int r2) {
                this.this$1 = r1;
                this.val$state = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r5) {
                PremiumButtonView r52 = this.this$1.premiumButtonView;
                int r2 = 8;
                if (this.val$state != 1) goto L5;
                int r0 = 0;
            L6:
                r52.setVisibility(r0);
                TextView r53 = this.this$1.addButtonView;
                if (this.val$state != 2) goto L9;
                int r02 = 0;
            L10:
                r53.setVisibility(r02);
                TextView r54 = this.this$1.removeButtonView;
                if (this.val$state != 3) goto L13;
                r2 = 0;
            L13:
                r54.setVisibility(r2);
                return;
            L9:
                r02 = 8;
                goto L10
            L5:
                r0 = 8;
                goto L6
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r2) {
                this.this$1.premiumButtonView.setVisibility(0);
                this.this$1.addButtonView.setVisibility(0);
                this.this$1.removeButtonView.setVisibility(0);
            }
        }

        public static /* synthetic */ void $r8$lambda$10IHWeoiGy6HO91MSNayXBsny6k(EmojiPackHeader r0) {
            r0.lambda$install$7();
        }

        public static /* synthetic */ void $r8$lambda$2ypoxWsO8QLW1UzOTx825CFFESo(EmojiPackHeader r0, TLRPC.TL_messages_stickerSet r1) {
            r0.lambda$uninstall$8(r1);
        }

        /* renamed from: $r8$lambda$VuO8F--CSIoBJZWkmJ3TTeMLgak, reason: not valid java name */
        public static /* synthetic */ void m10477$r8$lambda$VuO8FCSIoBJZWkmJ3TTeMLgak(EmojiPackHeader r0, View r1) {
            r0.lambda$new$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$XpOSziWQtyFgXV9BqpHm5ccoJUE(EmojiPackHeader r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$_zVMzcnWhWF1OPmsACUXn_sLkok(EmojiPackHeader r0, View r1) {
            r0.lambda$setStickerSet$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$epcEH0RBfqrghhpSRvsOxiqgW1U(EmojiPackHeader r0, View r1) {
            r0.lambda$setStickerSet$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$fpqciimOAmnAoG1uTFovF_VddUw(EmojiPackHeader r0, View r1) {
            r0.lambda$new$3(r1);
        }

        /* renamed from: $r8$lambda$l84GWYnapSooRkkY_Sj9Jm7cc-U, reason: not valid java name */
        public static /* synthetic */ void m10478$r8$lambda$l84GWYnapSooRkkY_Sj9Jm7ccU(EmojiPackHeader r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$qeMdA4RRX4ZPSwWKl1TTlz5diJ8(EmojiPackHeader r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public EmojiPackHeader(EmojiView r14, Context r15) {
            this.this$0 = r14;
            super(r15);
            RLottieImageView r0 = new RLottieImageView(r15);
            this.lockView = r0;
            r0.setAnimation(R.raw.unlock_icon, 24, 24);
            RLottieImageView r02 = this.lockView;
            int r1 = Theme.key_chat_emojiPanelStickerSetName;
            r02.setColorFilter(EmojiView.m10432$$Nest$mgetThemedColor(r14, r1));
            addView(this.lockView, LayoutHelper.createFrameRelatively(20.0f, 20.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 10.0f, 15.0f, 0.0f, 0.0f));
            SimpleTextView r03 = new SimpleTextView(r15);
            this.headerView = r03;
            r03.setTextSize(15);
            this.headerView.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(r14, r1));
            this.headerView.setTypeface(AndroidUtilities.bold());
            this.headerView.setOnClickListener(new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda0(this));
            TextView r04 = new TextView(r15);
            this.markView = r04;
            r04.setTextSize(1, 11.0f);
            this.markView.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(r14, r1));
            this.markView.setTypeface(AndroidUtilities.bold());
            this.markView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(8.0f), Theme.multAlpha(EmojiView.m10432$$Nest$mgetThemedColor(r14, Theme.key_chat_emojiPanelIcon), 0.12f)));
            this.markView.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.5f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(1.5f));
            this.markView.setText(LocaleController.getString(R.string.GroupEmoji));
            this.headerView.setEllipsizeByGradient(true);
            addView(this.headerView, LayoutHelper.createFrameRelatively(-2.0f, -1.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 15.0f, 15.0f, 0.0f, 0.0f));
            addView(this.markView, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 15.0f, 10.0f, 0.0f, 0.0f));
            FrameLayout r05 = new FrameLayout(r15);
            this.buttonsView = r05;
            r05.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(11.0f), 0);
            this.buttonsView.setClipToPadding(false);
            this.buttonsView.setOnClickListener(new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda1(this));
            addView(this.buttonsView, LayoutHelper.createFrameRelatively(-2.0f, -1.0f, 8388725));
            TextView r06 = new TextView(r15);
            this.addButtonView = r06;
            r06.setTextSize(1, 14.0f);
            this.addButtonView.setTypeface(AndroidUtilities.bold());
            this.addButtonView.setText(LocaleController.getString(R.string.Add));
            this.addButtonView.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(r14, Theme.key_featuredStickers_buttonText));
            TextView r07 = this.addButtonView;
            int r4 = Theme.key_featuredStickers_addButton;
            r07.setBackground(Theme.AdaptiveRipple.createRect(EmojiView.m10432$$Nest$mgetThemedColor(r14, r4), EmojiView.m10432$$Nest$mgetThemedColor(r14, Theme.key_featuredStickers_addButtonPressed), new float[]{16.0f}));
            this.addButtonView.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
            this.addButtonView.setGravity(17);
            this.addButtonView.setOnClickListener(new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda2(this));
            this.buttonsView.addView(this.addButtonView, LayoutHelper.createFrameRelatively(-2.0f, 26.0f, 8388661));
            TextView r08 = new TextView(r15);
            this.removeButtonView = r08;
            r08.setTextSize(1, 14.0f);
            this.removeButtonView.setTypeface(AndroidUtilities.bold());
            this.removeButtonView.setText(LocaleController.getString(R.string.StickersRemove));
            this.removeButtonView.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(r14, Theme.key_featuredStickers_removeButtonText));
            this.removeButtonView.setBackground(Theme.AdaptiveRipple.createRect(0, EmojiView.m10432$$Nest$mgetThemedColor(r14, r4) & 452984831, new float[]{16.0f}));
            this.removeButtonView.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            this.removeButtonView.setGravity(17);
            this.removeButtonView.setTranslationX(AndroidUtilities.dp(4.0f));
            this.removeButtonView.setOnClickListener(new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda3(this));
            this.buttonsView.addView(this.removeButtonView, LayoutHelper.createFrameRelatively(-2.0f, 26.0f, 8388661));
            PremiumButtonView r09 = new PremiumButtonView(r15, AndroidUtilities.dp(16.0f), false, EmojiView.m10370$$Nest$fgetresourcesProvider(r14));
            this.premiumButtonView = r09;
            r09.setIcon(R.raw.unlock_icon);
            this.premiumButtonView.setButton(LocaleController.getString(R.string.Unlock), new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda4(this));
            ViewGroup.MarginLayoutParams r142 = (ViewGroup.MarginLayoutParams) this.premiumButtonView.getIconView().getLayoutParams();     // Catch: Exception -> L6
            r142.leftMargin = AndroidUtilities.dp(1.0f);     // Catch: Exception -> L6
            r142.topMargin = AndroidUtilities.dp(1.0f);     // Catch: Exception -> L6
            int r152 = AndroidUtilities.dp(20.0f);     // Catch: Exception -> L6
            r142.height = r152;     // Catch: Exception -> L6
            r142.width = r152;     // Catch: Exception -> L6
            ViewGroup.MarginLayoutParams r143 = (ViewGroup.MarginLayoutParams) this.premiumButtonView.getTextView().getLayoutParams();     // Catch: Exception -> L6
            r143.leftMargin = AndroidUtilities.dp(5.0f);     // Catch: Exception -> L6
            r143.topMargin = AndroidUtilities.dp(-0.5f);     // Catch: Exception -> L6
            this.premiumButtonView.getChildAt(0).setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);     // Catch: Exception -> L6
        L4:
            this.buttonsView.addView(this.premiumButtonView, LayoutHelper.createFrameRelatively(-2.0f, 26.0f, 8388661));
            setWillNotDraw(false);
        }

        private BaseFragment getFragment() {
            if (EmojiView.m10335$$Nest$fgetfragment(this.this$0) == null) goto L7;
            return EmojiView.m10335$$Nest$fgetfragment(this.this$0);
        L7:
            return new AnonymousClass1(this);
        }

        private void install(TLRPC.TL_messages_stickerSet r4) {
            EmojiPacksAlert.installSet(getFragment(), r4, true, null, new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda8(this));
        }

        private /* synthetic */ void lambda$install$7() {
            this.pack.installed = true;
            updateState(true);
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            EmojiPack r12 = this.pack;
            if (r12 == null) goto L8;
            TLRPC.StickerSet r13 = r12.set;
            if (r13 == null) goto L9;
            this.this$0.openEmojiPackAlert(r13);
            return;
        L9:
            return;
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            TextView r12 = this.addButtonView;
            if (r12 != null) goto L5;
        L10:
            TextView r13 = this.removeButtonView;
            if (r13 != null) goto L13;
        L18:
            PremiumButtonView r14 = this.premiumButtonView;
            if (r14 != null) goto L21;
            return;
        L21:
            if (r14.getVisibility() == 0) goto L23;
            return;
        L23:
            if (this.premiumButtonView.isEnabled() == false) goto L28;
            this.premiumButtonView.performClick();
            return;
        L28:
            return;
        L13:
            if (r13.getVisibility() != 0) goto L18;
            if (this.removeButtonView.isEnabled() == false) goto L18;
            this.removeButtonView.performClick();
            return;
        L5:
            if (r12.getVisibility() != 0) goto L10;
            if (this.addButtonView.isEnabled() == false) goto L10;
            this.addButtonView.performClick();
        }

        private /* synthetic */ void lambda$new$2(View r9) {
            EmojiPack r92 = this.pack;
            if (r92 == null) goto L53;
            TLRPC.StickerSet r0 = r92.set;
            if (r0 == null) goto L54;
            r92.installed = true;
            if (this.this$0.installedEmojiSets.contains(Long.valueOf(r0.id)) == true) goto L10;
            this.this$0.installedEmojiSets.add(Long.valueOf(this.pack.set.id));
        L10:
            updateState(true);
            int r02 = 0;
        L12:
            if (r02 >= EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildCount()) goto L29;
            if ((EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildAt(r02) instanceof EmojiPackExpand) == false) goto L28;
            View r2 = EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildAt(r02);
            int r3 = EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getChildAdapterPosition(r2);
            if (r3 < 0) goto L28;
            int r4 = EmojiGridAdapter.m10469$$Nest$fgetpositionToExpand(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0)).get(r3);
            if (r4 < 0) goto L28;
            if (r4 >= EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).size()) goto L28;
            if (EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r4) == null) goto L28;
            if (this.pack == null) goto L28;
            if (((EmojiPack) EmojiView.m10325$$Nest$fgetemojipacksProcessed(this.this$0).get(r4)).set.id != this.pack.set.id) goto L28;
            Integer r03 = Integer.valueOf(r3);
        L30:
            if (r03 == null) goto L33;
            EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0).expand(r03.intValue(), r2);
        L33:
            if (this.toInstall != null) goto L55;
            TLRPC.TL_inputStickerSetID r04 = new TLRPC.TL_inputStickerSetID();
            TLRPC.StickerSet r22 = this.pack.set;
            r04.id = r22.id;
            r04.access_hash = r22.access_hash;
            TLRPC.TL_messages_stickerSet r1 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSet(r04, true);
            if (r1 != null) goto L38;
        L42:
            NotificationCenter.getInstance(this.this$0.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
            MediaDataController r12 = MediaDataController.getInstance(this.this$0.currentAccount);
            this.toInstall = r04;
            r12.getStickerSet(r04, false);
            return;
        L38:
            if (r1.set == null) goto L42;
            install(r1);
            return;
        L55:
            return;
        L28:
            r02 = r02 + 1;
            goto L12
        L29:
            r2 = null;
            r03 = null;
            goto L30
        L54:
            return;
        }

        private /* synthetic */ void lambda$new$3(View r6) {
            EmojiPack r62 = this.pack;
            if (r62 == null) goto L22;
            TLRPC.StickerSet r0 = r62.set;
            if (r0 == null) goto L23;
            r62.installed = false;
            this.this$0.installedEmojiSets.remove(Long.valueOf(r0.id));
            updateState(true);
            if (EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0) == null) goto L10;
            EmojiView.m10319$$Nest$fgetemojiTabs(this.this$0).updateEmojiPacks(this.this$0.getEmojipacks());
        L10:
            EmojiView.m10454$$Nest$mupdateEmojiTabsPosition(this.this$0);
            if (this.toUninstall != null) goto L24;
            TLRPC.TL_inputStickerSetID r02 = new TLRPC.TL_inputStickerSetID();
            TLRPC.StickerSet r2 = this.pack.set;
            r02.id = r2.id;
            r02.access_hash = r2.access_hash;
            TLRPC.TL_messages_stickerSet r63 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSet(r02, true);
            if (r63 != null) goto L16;
        L20:
            NotificationCenter.getInstance(this.this$0.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
            MediaDataController r64 = MediaDataController.getInstance(this.this$0.currentAccount);
            this.toUninstall = r02;
            r64.getStickerSet(r02, false);
            return;
        L16:
            if (r63.set == null) goto L20;
            uninstall(r63);
            return;
        L24:
            return;
        L23:
            return;
        }

        private /* synthetic */ void lambda$new$4(View r1) {
            EmojiView.m10436$$Nest$mopenPremiumAnimatedEmojiFeature(this.this$0);
        }

        private /* synthetic */ void lambda$setStickerSet$5(View r1) {
            EmojiView.m10436$$Nest$mopenPremiumAnimatedEmojiFeature(this.this$0);
        }

        private /* synthetic */ void lambda$setStickerSet$6(View r1) {
            EmojiView.m10436$$Nest$mopenPremiumAnimatedEmojiFeature(this.this$0);
        }

        private /* synthetic */ void lambda$uninstall$8(TLRPC.TL_messages_stickerSet r5) {
            this.pack.installed = true;
            if (this.this$0.installedEmojiSets.contains(Long.valueOf(r5.set.id)) == true) goto L5;
            this.this$0.installedEmojiSets.add(Long.valueOf(r5.set.id));
        L5:
            updateState(true);
        }

        private void uninstall(TLRPC.TL_messages_stickerSet r4) {
            EmojiPacksAlert.uninstallSet(getFragment(), r4, true, new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda7(this, r4), false);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r3, int r4, Object... r5) {
            if (r3 == NotificationCenter.groupStickersDidLoad) goto L5;
            return;
        L5:
            if (this.toInstall == null) goto L12;
            TLRPC.TL_messages_stickerSet r32 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSetById(this.toInstall.id);
            if (r32 == null) goto L12;
            if (r32.set == null) goto L12;
            install(r32);
            this.toInstall = null;
        L12:
            if (this.toUninstall == null) goto L20;
            TLRPC.TL_messages_stickerSet r33 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSetById(this.toUninstall.id);
            if (r33 != null) goto L16;
            return;
        L16:
            if (r33.set == null) goto L22;
            uninstall(r33);
            this.toUninstall = null;
            return;
        L22:
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(this.this$0.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.divider == true) goto L5;
            Canvas r1 = r8;
        L9:
            super.onDraw(r1);
            return;
        L5:
            if (this.dividerPaint != null) goto L7;
            Paint r0 = new Paint(1);
            this.dividerPaint = r0;
            r0.setStrokeWidth(1.0f);
            this.dividerPaint.setColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$0, Theme.key_divider));
        L7:
            r1 = r8;
            r1.drawRect(0.0f, 0.0f, getMeasuredWidth(), 1.0f, this.dividerPaint);
            goto L9
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = this.buttonsView.getWidth() + AndroidUtilities.dp(11.0f);
            if (this.markView.getVisibility() != 0) goto L5;
            int r22 = this.markView.getMeasuredWidth();
        L6:
            int r13 = r12 + r22;
            this.headerView.setRightPadding(r13);
            if (this.markView.getVisibility() != 0) goto L12;
            this.markView.setTranslationX(this.headerView.getTextWidth() + AndroidUtilities.dp(4.0f));
            float r23 = (this.headerView.getMaxTextWidth() - r13) + AndroidUtilities.dp(4.0f);
            if (this.markView.getTranslationX() <= r23) goto L13;
            this.markView.setTranslationX(r23);
            return;
        L13:
            return;
        L12:
            return;
        L5:
            r22 = 0;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            ViewGroup.MarginLayoutParams r32 = (ViewGroup.MarginLayoutParams) this.headerView.getLayoutParams();
            if (this.currentButtonState != 0) goto L5;
            float r0 = 10.0f;
        L6:
            r32.topMargin = AndroidUtilities.dp(r0);
            int r22 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30);
            if (this.currentButtonState != 0) goto L9;
            float r02 = 32.0f;
        L10:
            super.onMeasure(r22, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r02), TLObject.FLAG_30));
            return;
        L9:
            r02 = 42.0f;
            goto L10
        L5:
            r0 = 15.0f;
            goto L6
        }

        public void setStickerSet(EmojiPack r3, boolean r4) {
            if (r3 != null) goto L4;
            return;
        L4:
            this.pack = r3;
            this.divider = r4;
            this.headerView.setText(r3.set.title);
            TextView r42 = this.markView;
            if (r3.forGroup == false) goto L7;
            int r0 = 0;
        L8:
            r42.setVisibility(r0);
            if (r3.installed == true) goto L11;
        L13:
            this.premiumButtonView.setButton(LocaleController.getString(R.string.Unlock), new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda6(this));
        L14:
            updateState(false);
            return;
        L11:
            if (r3.set.official == true) goto L13;
            this.premiumButtonView.setButton(LocaleController.getString(R.string.Restore), new EmojiView$EmojiPackHeader$$ExternalSyntheticLambda5(this));
            goto L14
        L7:
            r0 = 8;
            goto L8
        }

        public void updateState(int r25, boolean r26) {
            if (r25 != 0) goto L5;
            boolean r4 = true;
        L7:
            if (this.currentButtonState != 0) goto L9;
            boolean r5 = true;
        L10:
            if (r4 == r5) goto L12;
            requestLayout();
        L12:
            this.currentButtonState = r25;
            AnimatorSet r42 = this.stateAnimator;
            if (r42 == null) goto L15;
            r42.cancel();
            this.stateAnimator = null;
        L15:
            PremiumButtonView r43 = this.premiumButtonView;
            if (r25 != 1) goto L18;
            boolean r52 = true;
        L19:
            r43.setEnabled(r52);
            TextView r44 = this.addButtonView;
            if (r25 != 2) goto L22;
            boolean r6 = true;
        L23:
            r44.setEnabled(r6);
            TextView r45 = this.removeButtonView;
            if (r25 != 3) goto L26;
            boolean r7 = true;
        L27:
            r45.setEnabled(r7);
            if (r26 == false) goto L84;
            AnimatorSet r11 = new AnimatorSet();
            this.stateAnimator = r11;
            RLottieImageView r12 = this.lockView;
            if (r25 != 1) goto L32;
            float r13 = 0.0f;
        L33:
            float[] r14 = {r13};
            Property r132 = FrameLayout.TRANSLATION_X;
            ObjectAnimator r122 = ObjectAnimator.ofFloat(r12, r132, r14);
            RLottieImageView r142 = this.lockView;
            if (r25 != 1) goto L37;
            float r15 = 1.0f;
        L38:
            float[] r2 = {r15};
            Property r152 = FrameLayout.ALPHA;
            ObjectAnimator r22 = ObjectAnimator.ofFloat(r142, r152, r2);
            SimpleTextView r143 = this.headerView;
            if (r25 != 1) goto L42;
            float r72 = AndroidUtilities.dp(16.0f);
        L43:
            ObjectAnimator r46 = ObjectAnimator.ofFloat(r143, r132, new float[]{r72});
            PremiumButtonView r73 = this.premiumButtonView;
            if (r25 != 1) goto L46;
            float r133 = 1.0f;
        L47:
            ObjectAnimator r74 = ObjectAnimator.ofFloat(r73, r152, new float[]{r133});
            PremiumButtonView r134 = this.premiumButtonView;
            if (r25 != 1) goto L50;
            float r144 = 1.0f;
        L51:
            float[] r8 = {r144};
            Property r145 = FrameLayout.SCALE_X;
            ObjectAnimator r82 = ObjectAnimator.ofFloat(r134, r145, r8);
            PremiumButtonView r135 = this.premiumButtonView;
            if (r25 != 1) goto L54;
            float r19 = 1.0f;
        L55:
            Property r10 = FrameLayout.SCALE_Y;
            ObjectAnimator r9 = ObjectAnimator.ofFloat(r135, r10, new float[]{r19});
            TextView r136 = this.addButtonView;
            if (r25 != 2) goto L58;
            float r21 = 1.0f;
        L59:
            ObjectAnimator r62 = ObjectAnimator.ofFloat(r136, r152, new float[]{r21});
            TextView r137 = this.addButtonView;
            if (r25 != 2) goto L62;
            float r212 = 1.0f;
        L63:
            ObjectAnimator r53 = ObjectAnimator.ofFloat(r137, r145, new float[]{r212});
            TextView r138 = this.addButtonView;
            if (r25 != 2) goto L67;
            float r213 = 1.0f;
        L68:
            ObjectAnimator r3 = ObjectAnimator.ofFloat(r138, r10, new float[]{r213});
            TextView r139 = this.removeButtonView;
            if (r25 != 3) goto L72;
            float r20 = 1.0f;
        L73:
            ObjectAnimator r32 = ObjectAnimator.ofFloat(r139, r152, new float[]{r20});
            TextView r1310 = this.removeButtonView;
            if (r25 != 3) goto L76;
            float r202 = 1.0f;
        L77:
            ObjectAnimator r1311 = ObjectAnimator.ofFloat(r1310, r145, new float[]{r202});
            TextView r146 = this.removeButtonView;
            if (r25 != 3) goto L81;
            float r18 = 1.0f;
        L82:
            r11.playTogether(new Animator[]{r122, r22, r46, r74, r82, r9, r62, r53, r3, r32, r1311, ObjectAnimator.ofFloat(r146, r10, new float[]{r18})});
            this.stateAnimator.addListener(new AnonymousClass2(this, r25));
            this.stateAnimator.setDuration(250);
            this.stateAnimator.setInterpolator(new OvershootInterpolator(1.02f));
            this.stateAnimator.start();
            return;
        L81:
            r18 = 0.6f;
            goto L82
        L76:
            r202 = 0.6f;
            goto L77
        L72:
            r20 = 0.0f;
            goto L73
        L67:
            r213 = 0.6f;
            goto L68
        L62:
            r212 = 0.6f;
            goto L63
        L58:
            r21 = 0.0f;
            goto L59
        L54:
            r19 = 0.6f;
            goto L55
        L50:
            r144 = 0.6f;
            goto L51
        L46:
            r133 = 0.0f;
            goto L47
        L42:
            r72 = 0.0f;
            goto L43
        L37:
            r15 = 0.0f;
            goto L38
        L32:
            r13 = -AndroidUtilities.dp(16.0f);
            goto L33
        L84:
            RLottieImageView r23 = this.lockView;
            if (r25 != 1) goto L87;
            float r47 = 1.0f;
        L88:
            r23.setAlpha(r47);
            RLottieImageView r24 = this.lockView;
            if (r25 != 1) goto L91;
            float r48 = 0.0f;
        L92:
            r24.setTranslationX(r48);
            SimpleTextView r27 = this.headerView;
            if (r25 != 1) goto L95;
            float r49 = AndroidUtilities.dp(16.0f);
        L96:
            r27.setTranslationX(r49);
            PremiumButtonView r28 = this.premiumButtonView;
            if (r25 != 1) goto L99;
            float r410 = 1.0f;
        L100:
            r28.setAlpha(r410);
            PremiumButtonView r29 = this.premiumButtonView;
            if (r25 != 1) goto L103;
            float r411 = 1.0f;
        L104:
            r29.setScaleX(r411);
            PremiumButtonView r210 = this.premiumButtonView;
            if (r25 != 1) goto L107;
            float r412 = 1.0f;
        L108:
            r210.setScaleY(r412);
            PremiumButtonView r211 = this.premiumButtonView;
            if (r25 != 1) goto L111;
            int r33 = 0;
        L112:
            r211.setVisibility(r33);
            TextView r214 = this.addButtonView;
            if (r25 != 2) goto L115;
            float r413 = 1.0f;
        L116:
            r214.setAlpha(r413);
            TextView r215 = this.addButtonView;
            if (r25 != 2) goto L119;
            float r414 = 1.0f;
        L120:
            r215.setScaleX(r414);
            TextView r216 = this.addButtonView;
            if (r25 != 2) goto L123;
            float r415 = 1.0f;
        L124:
            r216.setScaleY(r415);
            TextView r217 = this.addButtonView;
            if (r25 != 2) goto L127;
            int r34 = 0;
        L128:
            r217.setVisibility(r34);
            TextView r218 = this.removeButtonView;
            if (r25 != 3) goto L131;
            float r92 = 1.0f;
        L132:
            r218.setAlpha(r92);
            TextView r219 = this.removeButtonView;
            if (r25 != 3) goto L135;
            float r35 = 1.0f;
        L136:
            r219.setScaleX(r35);
            TextView r220 = this.removeButtonView;
            if (r25 != 3) goto L139;
            float r83 = 1.0f;
        L140:
            r220.setScaleY(r83);
            TextView r0 = this.removeButtonView;
            if (r25 != 3) goto L143;
            int r221 = 0;
        L144:
            r0.setVisibility(r221);
            return;
        L143:
            r221 = 8;
            goto L144
        L139:
            r83 = 0.6f;
            goto L140
        L135:
            r35 = 0.6f;
            goto L136
        L131:
            r92 = 0.0f;
            goto L132
        L127:
            r34 = 8;
            goto L128
        L123:
            r415 = 0.6f;
            goto L124
        L119:
            r414 = 0.6f;
            goto L120
        L115:
            r413 = 0.0f;
            goto L116
        L111:
            r33 = 8;
            goto L112
        L107:
            r412 = 0.6f;
            goto L108
        L103:
            r411 = 0.6f;
            goto L104
        L99:
            r410 = 0.0f;
            goto L100
        L95:
            r49 = 0.0f;
            goto L96
        L91:
            r48 = -AndroidUtilities.dp(16.0f);
            goto L92
        L87:
            r47 = 0.0f;
            goto L88
        L26:
            r7 = false;
            goto L27
        L22:
            r6 = false;
            goto L23
        L18:
            r52 = false;
            goto L19
        L9:
            r5 = false;
            goto L10
        L5:
            r4 = false;
            goto L7
        }

        public void updateState(boolean r7) {
            EmojiPack r0 = this.pack;
            if (r0 != null) goto L5;
            return;
        L5:
            int r2 = 1;
            if (r0.installed == false) goto L8;
        L11:
            boolean r02 = true;
        L13:
            if (this.pack.free == true) goto L20;
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == true) goto L20;
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$0) == true) goto L20;
        L25:
            updateState(r2, r7);
            return;
        L20:
            if (this.pack.featured == false) goto L24;
            if (r02 == false) goto L23;
            r2 = 3;
            goto L25
        L23:
            r2 = 2;
            goto L25
        L24:
            r2 = 0;
            goto L25
        L8:
            if (this.this$0.installedEmojiSets.contains(Long.valueOf(r0.set.id)) == true) goto L11;
            r02 = false;
            goto L13
        }
    }

    public static class EmojiPackInfo {
        private final ArrayList<TLRPC.Document> documents;
        private final TLRPC.Document firstDocument;
        private final TLRPC.StickerSet set;
        private final TLRPC.TL_messages_stickerSet stickerSet;
        private final TLRPC.StickerSetCovered stickerSetCovered;

        /* renamed from: -$$Nest$fgetdocuments, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10479$$Nest$fgetdocuments(EmojiPackInfo r0) {
            return r0.documents;
        }

        /* renamed from: -$$Nest$fgetfirstDocument, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.Document m10480$$Nest$fgetfirstDocument(EmojiPackInfo r0) {
            return r0.firstDocument;
        }

        /* renamed from: -$$Nest$fgetset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.StickerSet m10481$$Nest$fgetset(EmojiPackInfo r0) {
            return r0.set;
        }

        /* renamed from: -$$Nest$fgetstickerSet, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.TL_messages_stickerSet m10482$$Nest$fgetstickerSet(EmojiPackInfo r0) {
            return r0.stickerSet;
        }

        /* renamed from: -$$Nest$fgetstickerSetCovered, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.StickerSetCovered m10483$$Nest$fgetstickerSetCovered(EmojiPackInfo r0) {
            return r0.stickerSetCovered;
        }

        private EmojiPackInfo(TLRPC.StickerSetCovered r2, ArrayList<TLRPC.Document> r3) {
            this.stickerSetCovered = r2;
            TLRPC.Document r0 = null;
            this.stickerSet = null;
            this.set = r2.set;
            this.documents = r3;
            if (r3 != null) goto L5;
        L7:
            this.firstDocument = r0;
            return;
        L5:
            if (r3.isEmpty() == true) goto L7;
            r0 = r3.get(0);
            goto L7
        }

        public /* synthetic */ EmojiPackInfo(TLRPC.StickerSetCovered r1, ArrayList r2, EmojiViewIA r3) {
            this(r1, r2);
        }

        private EmojiPackInfo(TLRPC.TL_messages_stickerSet r2, ArrayList<TLRPC.Document> r3) {
            TLRPC.Document r0 = null;
            this.stickerSetCovered = null;
            this.stickerSet = r2;
            this.set = r2.set;
            this.documents = r3;
            if (r3 != null) goto L5;
        L7:
            this.firstDocument = r0;
            return;
        L5:
            if (r3.isEmpty() == true) goto L7;
            r0 = r3.get(0);
            goto L7
        }

        public /* synthetic */ EmojiPackInfo(TLRPC.TL_messages_stickerSet r1, ArrayList r2, EmojiViewIA r3) {
            this(r1, r2);
        }
    }

    public class EmojiPagesAdapter extends PagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
        final /* synthetic */ EmojiView this$0;

        private EmojiPagesAdapter(EmojiView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ EmojiPagesAdapter(EmojiView r1, EmojiViewIA r2) {
            this(r1);
        }

        @Override // org.telegram.ui.Components.PagerSlidingTabStrip.IconTabProvider
        public boolean canScrollToTab(int r5) {
            if (r5 != 1) goto L5;
        L6:
            EmojiView r2 = this.this$0;
            if (r2.stickersBanned == false) goto L13;
            if (r5 != 1) goto L10;
            boolean r4 = true;
        L11:
            r2.showStickerBanHint(true, false, r4);
            return false;
        L10:
            r4 = false;
        L13:
            if (r5 != 0) goto L18;
            EmojiView r42 = this.this$0;
            if (r42.emojiBanned == false) goto L18;
            r42.showStickerBanHint(true, true, false);
            return false;
        L18:
            return true;
        L5:
            if (r5 != 2) goto L13;
            goto L6
        }

        @Override // org.telegram.ui.Components.PagerSlidingTabStrip.IconTabProvider
        public void customOnDraw(Canvas r1, View r2, int r3) {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            r1.removeView((View) r3);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return EmojiView.m10306$$Nest$fgetcurrentTabs(this.this$0).size();
        }

        @Override // org.telegram.ui.Components.PagerSlidingTabStrip.IconTabProvider
        public Drawable getPageIconDrawable(int r1) {
            return null;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int r1) {
            if (r1 == 0) goto L14;
            if (r1 == 1) goto L12;
            if (r1 == 2) goto L10;
            return null;
        L10:
            return LocaleController.getString(R.string.AccDescrStickers);
        L12:
            return LocaleController.getString(R.string.AccDescrGIFs);
        L14:
            return LocaleController.getString(R.string.Emoji);
        }

        @Override // org.telegram.ui.Components.PagerSlidingTabStrip.IconTabProvider
        public int getTabPadding(int r1) {
            if (r1 != 1) goto L5;
            float r0 = 12.0f;
        L7:
            return AndroidUtilities.dp(r0);
        L5:
            r0 = 18.0f;
            goto L7
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup r3, int r4) {
            if (((Tab) EmojiView.m10306$$Nest$fgetcurrentTabs(this.this$0).get(r4)).type == 2) goto L5;
        L9:
            View r2 = ((Tab) EmojiView.m10306$$Nest$fgetcurrentTabs(this.this$0).get(r4)).view;
            r3.addView(r2);
            return r2;
        L5:
            if (EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0) == null) goto L9;
            if (EmojiView.m10433$$Nest$mneedsStickerAdapterBuild(this.this$0) == false) goto L9;
            EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0).notifyDataSetChanged();
            goto L9
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r1, Object r2) {
            if (r1 != r2) goto L5;
            return true;
        L5:
            return false;
        }
    }

    public class EmojiSearchAdapter extends RecyclerListView.SelectionAdapter {
        private FoundEmojiPacksRecyclerView foundPacksListView;
        private boolean isCompleted;
        private String lastSearchAlias;
        private String lastSearchEmojiString;
        private final ArrayList<EmojiPackInfo> packs;
        private final ArrayList<MediaDataController.KeywordResult> result;
        private final ArrayList<MediaDataController.KeywordResult> resultGlobal;
        private final ArrayList<MediaDataController.KeywordResult> resultPre;
        private SearchRunnable searchRunnable;
        private boolean searchWas;
        private long selectedPackId;
        private TLRPC.StickerSet selectedPackStickerSet;
        private ArrayList<TLRPC.Document> selectedPackStickers;
        final /* synthetic */ EmojiView this$0;

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiSearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends FoundEmojiPacksRecyclerView {
            final /* synthetic */ EmojiSearchAdapter this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass1(EmojiSearchAdapter r1, Context r2, int r3, int r4, boolean r5, Utilities.Callback2 r6, Utilities.Callback5 r7, Utilities.Callback5Return r8, Theme.ResourcesProvider r9, int r10, int r11, EmojiView r12) {
                this.this$1 = r1;
                this.val$this$0 = r12;
                super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiSearchAdapter$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnTouchListener {
            final /* synthetic */ EmojiSearchAdapter this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass2(EmojiSearchAdapter r1, EmojiView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View r3, MotionEvent r4) {
                if (r4.getAction() != 0) goto L6;
                EmojiView.m10410$$Nest$fputignorePagerScroll(this.this$1.this$0, true);
            L10:
                return false;
            L6:
                if (r4.getAction() != 1) goto L8;
            L9:
                EmojiView.m10410$$Nest$fputignorePagerScroll(this.this$1.this$0, false);
                goto L10
            L8:
                if (r4.getAction() != 3) goto L10;
                goto L9
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiSearchAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends FrameLayout {
            final /* synthetic */ EmojiSearchAdapter this$1;

            public AnonymousClass3(EmojiSearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                if (((View) this.this$1.this$0.getParent()) == null) goto L5;
                int r32 = (int) (r3.getMeasuredHeight() - this.this$1.this$0.getY());
            L6:
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(r32 - EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$1.this$0), TLObject.FLAG_30));
                return;
            L5:
                r32 = AndroidUtilities.dp(120.0f);
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiSearchAdapter$4, reason: invalid class name */
        public class AnonymousClass4 implements View.OnClickListener {
            final /* synthetic */ EmojiSearchAdapter this$1;

            /* renamed from: org.telegram.ui.Components.EmojiView$EmojiSearchAdapter$4$1, reason: invalid class name */
            public class AnonymousClass1 implements View.OnClickListener {
                final /* synthetic */ AnonymousClass4 this$2;
                final /* synthetic */ BottomSheet.Builder val$builder;
                final /* synthetic */ boolean[] val$loadingUrl;

                public static /* synthetic */ void $r8$lambda$EeIbURzzoSgq8EibxzzlO3EAnK4(AnonymousClass1 r0, AlertDialog[] r1, BottomSheet.Builder r2, TLObject r3, TLRPC.TL_error r4) {
                    r0.lambda$onClick$1(r1, r2, r3, r4);
                }

                /* renamed from: $r8$lambda$JQIXZCXKJzwQ-lz5TLuoNJxowEs, reason: not valid java name */
                public static /* synthetic */ void m10500$r8$lambda$JQIXZCXKJzwQlz5TLuoNJxowEs(AnonymousClass1 r0, AlertDialog[] r1, TLObject r2, BottomSheet.Builder r3) {
                    r0.lambda$onClick$0(r1, r2, r3);
                }

                /* renamed from: $r8$lambda$mcHJmQumpo3SG7f8RaOC1hVvx-Q, reason: not valid java name */
                public static /* synthetic */ void m10501$r8$lambda$mcHJmQumpo3SG7f8RaOC1hVvxQ(AnonymousClass1 r0, AlertDialog[] r1, int r2) {
                    r0.lambda$onClick$3(r1, r2);
                }

                public static /* synthetic */ void $r8$lambda$muRqOCDSW6FQ3Uo6n_UU90DhKAU(AnonymousClass1 r0, int r1, DialogInterface r2) {
                    r0.lambda$onClick$2(r1, r2);
                }

                public AnonymousClass1(AnonymousClass4 r1, boolean[] r2, BottomSheet.Builder r3) {
                    this.this$2 = r1;
                    this.val$loadingUrl = r2;
                    this.val$builder = r3;
                }

                private /* synthetic */ void lambda$onClick$0(AlertDialog[] r3, TLObject r4, BottomSheet.Builder r5) {
                    r3[0].dismiss();     // Catch: Throwable -> L8
                L4:
                    r3[0] = null;
                    if ((r4 instanceof TLRPC.TL_emojiURL) == false) goto L11;
                    Browser.openUrl(this.this$2.this$1.this$0.getContext(), ((TLRPC.TL_emojiURL) r4).url);
                    r5.getDismissRunnable().run();
                    return;
                }

                private /* synthetic */ void lambda$onClick$1(AlertDialog[] r1, BottomSheet.Builder r2, TLObject r3, TLRPC.TL_error r4) {
                    AndroidUtilities.runOnUIThread(new EmojiView$EmojiSearchAdapter$4$1$$ExternalSyntheticLambda3(this, r1, r3, r2));
                }

                private /* synthetic */ void lambda$onClick$2(int r1, DialogInterface r2) {
                    ConnectionsManager.getInstance(this.this$2.this$1.this$0.currentAccount).cancelRequest(r1, true);
                }

                private /* synthetic */ void lambda$onClick$3(AlertDialog[] r4, int r5) {
                    AlertDialog r1 = r4[0];
                    if (r1 != null) goto L5;
                    return;
                L5:
                    r1.setOnCancelListener(new EmojiView$EmojiSearchAdapter$4$1$$ExternalSyntheticLambda2(this, r5));
                    r4[0].show();
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View r5) {
                    boolean[] r52 = this.val$loadingUrl;
                    if (r52[0] == false) goto L5;
                    return;
                L5:
                    r52[0] = true;
                    AlertDialog[] r53 = {new AlertDialog(this.this$2.this$1.this$0.getContext(), 3)};
                    TLRPC.TL_messages_getEmojiURL r1 = new TLRPC.TL_messages_getEmojiURL();
                    String r2 = EmojiSearchAdapter.m10486$$Nest$fgetlastSearchAlias(this.this$2.this$1);
                    AnonymousClass4 r3 = this.this$2;
                    if (r2 == null) goto L8;
                    String r0 = EmojiSearchAdapter.m10486$$Nest$fgetlastSearchAlias(r3.this$1);
                L9:
                    r1.lang_code = r0;
                    AndroidUtilities.runOnUIThread(new EmojiView$EmojiSearchAdapter$4$1$$ExternalSyntheticLambda1(this, r53, ConnectionsManager.getInstance(this.this$2.this$1.this$0.currentAccount).sendRequest(r1, new EmojiView$EmojiSearchAdapter$4$1$$ExternalSyntheticLambda0(this, r53, this.val$builder))), 1000);
                    return;
                L8:
                    r0 = EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(r3.this$1.this$0)[0];
                    goto L9
                }
            }

            public AnonymousClass4(EmojiSearchAdapter r1) {
                this.this$1 = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r18) {
                boolean[] r2 = new boolean[1];
                BottomSheet.Builder r3 = new BottomSheet.Builder(this.this$1.this$0.getContext());
                LinearLayout r4 = new LinearLayout(this.this$1.this$0.getContext());
                r4.setOrientation(1);
                r4.setPadding(AndroidUtilities.dp(21.0f), 0, AndroidUtilities.dp(21.0f), 0);
                ImageView r5 = new ImageView(this.this$1.this$0.getContext());
                r5.setImageResource(R.drawable.smiles_info);
                r4.addView(r5, LayoutHelper.createLinear(-2, -2, 49, 0, 15, 0, 0));
                TextView r52 = new TextView(this.this$1.this$0.getContext());
                r52.setText(LocaleController.getString(R.string.EmojiSuggestions));
                r52.setTextSize(1, 15.0f);
                r52.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$1.this$0, Theme.key_dialogTextBlue2));
                int r8 = 3;
                if (LocaleController.isRTL == false) goto L5;
                int r7 = 5;
            L6:
                r52.setGravity(r7);
                r52.setTypeface(AndroidUtilities.bold());
                r4.addView(r52, LayoutHelper.createLinear(-2, -2, 51, 0, 24, 0, 0));
                TextView r53 = new TextView(this.this$1.this$0.getContext());
                r53.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.EmojiSuggestionsInfo)));
                r53.setTextSize(1, 15.0f);
                r53.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$1.this$0, Theme.key_dialogTextBlack));
                if (LocaleController.isRTL == false) goto L9;
                int r72 = 5;
            L10:
                r53.setGravity(r72);
                r4.addView(r53, LayoutHelper.createLinear(-2, -2, 51, 0, 11, 0, 0));
                TextView r54 = new TextView(this.this$1.this$0.getContext());
                int r73 = R.string.EmojiSuggestionsUrl;
                String r10 = EmojiSearchAdapter.m10486$$Nest$fgetlastSearchAlias(this.this$1);
                EmojiSearchAdapter r11 = this.this$1;
                if (r10 == null) goto L13;
                Object r102 = EmojiSearchAdapter.m10486$$Nest$fgetlastSearchAlias(r11);
            L14:
                r54.setText(LocaleController.formatString("EmojiSuggestionsUrl", r73, new Object[]{r102}));
                r54.setTextSize(1, 15.0f);
                r54.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$1.this$0, Theme.key_dialogTextLink));
                if (LocaleController.isRTL == false) goto L17;
                r8 = 5;
            L17:
                r54.setGravity(r8);
                r4.addView(r54, LayoutHelper.createLinear(-2, -2, 51, 0, 18, 0, 16));
                r54.setOnClickListener(new AnonymousClass1(this, r2, r3));
                r3.setCustomView(r4);
                r3.show();
                return;
            L13:
                r102 = EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(r11.this$0);
                goto L14
            L9:
                r72 = 3;
                goto L10
            L5:
                r7 = 3;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$EmojiSearchAdapter$5, reason: invalid class name */
        public class AnonymousClass5 implements SearchRunnable {
            final /* synthetic */ EmojiSearchAdapter this$1;

            public static /* synthetic */ void $r8$lambda$7bs73AflJHu4uQp6IkeGL87H9Eg(AnonymousClass5 r0, ArrayList r1) {
                r0.lambda$loadNext$0(r1);
            }

            public static /* synthetic */ void $r8$lambda$FEgw6kW_9Z8cB_SFVZ9o4Us_BFQ(AnonymousClass5 r0, String r1, ArrayList r2, Runnable r3) {
                r0.lambda$run$5(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$FpjB2T1UavjcSVcu0qkG8nGT8K0(AnonymousClass5 r0, String r1, ArrayList r2, Runnable r3) {
                r0.lambda$run$2(r1, r2, r3);
            }

            /* renamed from: $r8$lambda$LEx64Em-irWIYAifXz40C3kGgSc, reason: not valid java name */
            public static /* synthetic */ void m10502$r8$lambda$LEx64EmirWIYAifXz40C3kGgSc(AnonymousClass5 r0, String r1, ArrayList r2, ArrayList r3, ArrayList r4, Runnable r5) {
                r0.lambda$run$7(r1, r2, r3, r4, r5);
            }

            /* renamed from: $r8$lambda$OeBtE6c7Q2A7FShUI3sWpvi-95A, reason: not valid java name */
            public static /* synthetic */ void m10503$r8$lambda$OeBtE6c7Q2A7FShUI3sWpvi95A(AnonymousClass5 r0, ArrayList r1, Runnable r2) {
                r0.lambda$run$6(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$S50ekS_VYIzDxDZu7ERVvnTyD_o(AnonymousClass5 r0, String r1, Runnable r2, ArrayList r3, String r4) {
                r0.lambda$run$3(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$aYmFiSEV_zPNdYdH42thmnPihWA(LinkedHashSet r0, Runnable r1, TLRPC.TL_emojiList r2) {
                if (r2 == null) goto L4;
                r0.addAll(r2.document_id);
            L4:
                r1.run();
            }

            public static /* synthetic */ void $r8$lambda$nyfw7I3AhzakuWEerdcgSxs0QAk(AnonymousClass5 r0, String r1) {
                r0.lambda$run$8(r1);
            }

            public static /* synthetic */ void $r8$lambda$pWRvsSGHEIP1WZUZjXOd7_5vZ_Y(AnonymousClass5 r0, ArrayList r1, Runnable r2, ArrayList r3) {
                r0.lambda$run$1(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$rXJ9r8XRJdgfD9xs3jRLh95CPIU(AnonymousClass5 r0, String r1, Runnable r2) {
                r0.lambda$run$4(r1, r2);
            }

            public AnonymousClass5(EmojiSearchAdapter r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$loadNext$0(ArrayList r5) {
                boolean r1 = false;
                EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$1.this$0).showProgress(false);
                EmojiSearchAdapter r0 = this.this$1;
                if (EmojiSearchAdapter.m10490$$Nest$fgetresultGlobal(r0).size() < r5.size()) goto L5;
                r1 = true;
            L5:
                EmojiSearchAdapter.m10495$$Nest$fputisCompleted(r0, r1);
                EmojiSearchAdapter.m10490$$Nest$fgetresultGlobal(this.this$1).clear();
                EmojiSearchAdapter.m10490$$Nest$fgetresultGlobal(this.this$1).addAll(r5);
                this.this$1.notifyDataSetChanged();
            }

            private /* synthetic */ void lambda$run$1(ArrayList r9, Runnable r10, ArrayList r11) {
                int r0 = r11.size();
                int r1 = 0;
            L3:
                if (r1 >= r0) goto L18;
                Object r2 = r11.get(r1);
                r1 = r1 + 1;
                TLRPC.StickerSetCovered r22 = (TLRPC.StickerSetCovered) r2;
                if ((r22 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L8;
                ArrayList<TLRPC.Document> r3 = ((TLRPC.TL_stickerSetFullCovered) r22).documents;
            L14:
                if (r3 == null) goto L3;
                if (r3.isEmpty() == true) goto L3;
                r9.add(new EmojiPackInfo(r22, r3, null));
                goto L3
            L8:
                if ((r22 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L13;
                TLRPC.TL_messages_stickerSet r32 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getStickerSet(MediaDataController.getInputStickerSet(r22.set), Integer.valueOf(r22.set.hash), true);
                if (r32 == null) goto L12;
                r3 = r32.documents;
                goto L14
            L12:
                r3 = null;
                goto L14
            L13:
                r3 = r22.covers;
                goto L14
            L18:
                r10.run();
            }

            private /* synthetic */ void lambda$run$2(String r3, ArrayList r4, Runnable r5) {
                MediaDataController.getInstance(this.this$1.this$0.currentAccount).searchStickerSets(true, r3, new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda8(this, r4, r5));
            }

            private /* synthetic */ void lambda$run$3(String r2, Runnable r3, ArrayList r4, String r5) {
                if (r2.equals(EmojiSearchAdapter.m10487$$Nest$fgetlastSearchEmojiString(this.this$1)) == true) goto L5;
                return;
            L5:
                EmojiSearchAdapter.m10496$$Nest$fputlastSearchAlias(this.this$1, r5);
                EmojiSearchAdapter.m10491$$Nest$fgetresultPre(this.this$1).addAll(r4);
                r3.run();
            }

            private /* synthetic */ void lambda$run$4(String r12, Runnable r13) {
                MediaDataController r1 = MediaDataController.getInstance(this.this$1.this$0.currentAccount);
                String[] r2 = EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(this.this$1.this$0);
                String r3 = EmojiSearchAdapter.m10487$$Nest$fgetlastSearchEmojiString(this.this$1);
                EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda9 r5 = new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda9(this, r12, r13);
                if (SharedConfig.suggestAnimatedEmoji == false) goto L5;
            L11:
                boolean r11 = true;
            L12:
                r1.getEmojiSuggestions(r2, r3, false, r5, null, r11, false, true, 25);
                return;
            L5:
                if (UserConfig.getInstance(this.this$1.this$0.currentAccount).isPremium() == true) goto L11;
                if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(this.this$1.this$0) == true) goto L11;
                r11 = false;
                goto L12
            }

            private /* synthetic */ void lambda$run$5(String r11, ArrayList r12, Runnable r13) {
                if (SharedConfig.suggestAnimatedEmoji == false) goto L5;
            L6:
                String r112 = AndroidUtilities.translitSafe((r11 + _UrlKt.FRAGMENT_ENCODE_SET).toLowerCase());
                ArrayList<TLRPC.TL_messages_stickerSet> r0 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getStickerSets(5);
                HashSet r1 = new HashSet();
                int r3 = 0;
                if (r0 == null) goto L30;
                int r5 = 0;
            L10:
                if (r5 >= r0.size()) goto L30;
                TLRPC.TL_messages_stickerSet r6 = r0.get(r5);
                if (r6 == null) goto L29;
                TLRPC.StickerSet r7 = r6.set;
                if (r7 == null) goto L29;
                if (r7.title == null) goto L29;
                ArrayList<TLRPC.Document> r72 = r6.documents;
                if (r72 == null) goto L29;
                if (r72.isEmpty() == true) goto L29;
                if (r1.contains(Long.valueOf(r6.set.id)) == true) goto L29;
                String r73 = AndroidUtilities.translitSafe(r6.set.title.toLowerCase());
                if (r73.startsWith(r112) == false) goto L27;
            L28:
                r12.add(new EmojiPackInfo(r6, r6.documents, null));
                r1.add(Long.valueOf(r6.set.id));
                goto L29
            L27:
                if (r73.contains(" " + r112) == true) goto L28;
            L29:
                r5 = r5 + 1;
            L30:
                ArrayList<TLRPC.StickerSetCovered> r02 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getFeaturedEmojiSets();
                if (r02 != null) goto L33;
            L62:
                r13.run();
                return;
            L33:
                if (r3 >= r02.size()) goto L62;
                TLRPC.StickerSetCovered r52 = r02.get(r3);
                if (r52 == null) goto L61;
                TLRPC.StickerSet r62 = r52.set;
                if (r62 == null) goto L61;
                if (r62.title == null) goto L61;
                if (r1.contains(Long.valueOf(r62.id)) == true) goto L61;
                String r63 = AndroidUtilities.translitSafe(r52.set.title.toLowerCase());
                if (r63.startsWith(r112) == true) goto L48;
                if (r63.contains(" " + r112) == false) goto L61;
            L48:
                if ((r52 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L51;
                ArrayList<TLRPC.Document> r64 = ((TLRPC.TL_stickerSetFullCovered) r52).documents;
            L57:
                if (r64 == null) goto L61;
                if (r64.isEmpty() == true) goto L61;
                r12.add(new EmojiPackInfo(r52, r64, null));
                r1.add(Long.valueOf(r52.set.id));
                goto L61
            L51:
                if ((r52 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L56;
                TLRPC.TL_messages_stickerSet r65 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getStickerSet(MediaDataController.getInputStickerSet(r52.set), Integer.valueOf(r52.set.hash), true);
                if (r65 == null) goto L55;
                r64 = r65.documents;
                goto L57
            L55:
                r64 = null;
                goto L57
            L56:
                r64 = r52.covers;
            L61:
                r3 = r3 + 1;
                goto L33
            L5:
                if (UserConfig.getInstance(this.this$1.this$0.currentAccount).isPremium() == false) goto L62;
                goto L6
            }

            private /* synthetic */ void lambda$run$6(ArrayList r3, Runnable r4) {
                if (ConnectionsManager.getInstance(this.this$1.this$0.currentAccount).getConnectionState() == 3) goto L6;
                r4.run();
                return;
            L6:
                EmojiSearchAdapter.m10499$$Nest$msearchEmoji(this.this$1, r4, r3, false);
            }

            private /* synthetic */ void lambda$run$7(String r6, ArrayList r7, ArrayList r8, ArrayList r9, Runnable r10) {
                if (r6.equals(EmojiSearchAdapter.m10487$$Nest$fgetlastSearchEmojiString(this.this$1)) == true) goto L5;
                return;
            L5:
                int r102 = 0;
                EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$1.this$0).showProgress(false);
                EmojiSearchAdapter.m10497$$Nest$fputsearchWas(this.this$1, true);
                if (EmojiView.m10314$$Nest$fgetemojiGridView(this.this$1.this$0).getAdapter() == EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$1.this$0)) goto L8;
                EmojiView.m10314$$Nest$fgetemojiGridView(this.this$1.this$0).setAdapter(EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$1.this$0));
            L8:
                EmojiSearchAdapter.m10489$$Nest$fgetresult(this.this$1).clear();
                EmojiSearchAdapter.m10489$$Nest$fgetresult(this.this$1).addAll(EmojiSearchAdapter.m10491$$Nest$fgetresultPre(this.this$1));
                EmojiSearchAdapter.m10490$$Nest$fgetresultGlobal(this.this$1).clear();
                EmojiSearchAdapter.m10490$$Nest$fgetresultGlobal(this.this$1).addAll(r7);
                EmojiSearchAdapter.m10488$$Nest$fgetpacks(this.this$1).clear();
                LongSparseIntArray r62 = new LongSparseIntArray();
                int r72 = r8.size();
                int r1 = 0;
            L9:
                if (r1 >= r72) goto L13;
                Object r2 = r8.get(r1);
                r1 = r1 + 1;
                EmojiPackInfo r22 = (EmojiPackInfo) r2;
                if (r62.indexOfKey(EmojiPackInfo.m10481$$Nest$fgetset(r22).id) >= 0) goto L9;
                r62.append(EmojiPackInfo.m10481$$Nest$fgetset(r22).id, 1);
                EmojiSearchAdapter.m10488$$Nest$fgetpacks(this.this$1).add(r22);
                goto L9
            L13:
                int r73 = r9.size();
            L14:
                if (r102 >= r73) goto L18;
                Object r82 = r9.get(r102);
                r102 = r102 + 1;
                EmojiPackInfo r83 = (EmojiPackInfo) r82;
                if (r62.indexOfKey(EmojiPackInfo.m10481$$Nest$fgetset(r83).id) >= 0) goto L14;
                r62.append(EmojiPackInfo.m10481$$Nest$fgetset(r83).id, 1);
                EmojiSearchAdapter.m10488$$Nest$fgetpacks(this.this$1).add(r83);
                goto L14
            L18:
                this.this$1.notifyDataSetChanged();
            }

            private /* synthetic */ void lambda$run$8(String r11) {
                String[] r0 = AndroidUtilities.getCurrentKeyboardLanguage();
                if (Arrays.equals(EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(this.this$1.this$0), r0) == true) goto L5;
                MediaDataController.getInstance(this.this$1.this$0.currentAccount).fetchNewEmojiKeywords(r0);
            L5:
                EmojiView.m10412$$Nest$fputlastSearchKeyboardLanguage(this.this$1.this$0, r0);
                ArrayList r5 = new ArrayList();
                ArrayList r6 = new ArrayList();
                ArrayList r7 = new ArrayList();
                Utilities.doCallbacks(new Utilities.Callback[]{new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda3(this, r11, r7), new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda4(this, r11), new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda5(this, r11, r6), new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda6(this, r5), new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda7(this, r11, r5, r6, r7)});
            }

            @Override // org.telegram.ui.Components.EmojiView.SearchRunnable
            public boolean isCompleted() {
                return EmojiSearchAdapter.m10485$$Nest$fgetisCompleted(this.this$1);
            }

            @Override // org.telegram.ui.Components.EmojiView.SearchRunnable
            public boolean isLoading() {
                return EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$1.this$0).isInProgress();
            }

            @Override // org.telegram.ui.Components.EmojiView.SearchRunnable
            public void loadNext() {
                if (isLoading() == false) goto L5;
                return;
            L5:
                ArrayList r0 = new ArrayList();
                EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$1.this$0).showProgress(true);
                EmojiSearchAdapter.m10499$$Nest$msearchEmoji(this.this$1, new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda0(this, r0), r0, true);
            }

            @Override // java.lang.Runnable
            public void run() {
                LinkedHashSet r0 = new LinkedHashSet();
                String r1 = EmojiSearchAdapter.m10487$$Nest$fgetlastSearchEmojiString(this.this$1);
                EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda1 r2 = new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda1(this, r1);
                if (Emoji.fullyConsistsOfEmojis(r1) == false) goto L6;
                StickerCategoriesListView.search.fetch(UserConfig.selectedAccount, r1, new EmojiView$EmojiSearchAdapter$5$$ExternalSyntheticLambda2(r0, r2));
                return;
            L6:
                r2.run();
            }
        }

        public static /* synthetic */ void $r8$lambda$5aSsoNjikXXDkWWaEXMbGS2htQs(EmojiSearchAdapter r0, UItem r1, View r2, int r3, float r4, float r5) {
            r0.foundPackListOnClickItem(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$ZRfLMAYHvIuPl7h9bniDqd_I41M(EmojiSearchAdapter r0, ArrayList r1, UniversalAdapter r2) {
            r0.foundPackListFillItems(r1, r2);
        }

        /* renamed from: $r8$lambda$jolIOcTLbA50-bVpzRn9F1U_Ux4, reason: not valid java name */
        public static /* synthetic */ void m10484$r8$lambda$jolIOcTLbA50bVpzRn9F1U_Ux4(EmojiSearchAdapter r0, String r1, ArrayList r2, Runnable r3, ArrayList r4) {
            r0.lambda$searchEmoji$0(r1, r2, r3, r4);
        }

        /* renamed from: -$$Nest$fgetisCompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10485$$Nest$fgetisCompleted(EmojiSearchAdapter r0) {
            return r0.isCompleted;
        }

        /* renamed from: -$$Nest$fgetlastSearchAlias, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m10486$$Nest$fgetlastSearchAlias(EmojiSearchAdapter r0) {
            return r0.lastSearchAlias;
        }

        /* renamed from: -$$Nest$fgetlastSearchEmojiString, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m10487$$Nest$fgetlastSearchEmojiString(EmojiSearchAdapter r0) {
            return r0.lastSearchEmojiString;
        }

        /* renamed from: -$$Nest$fgetpacks, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10488$$Nest$fgetpacks(EmojiSearchAdapter r0) {
            return r0.packs;
        }

        /* renamed from: -$$Nest$fgetresult, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10489$$Nest$fgetresult(EmojiSearchAdapter r0) {
            return r0.result;
        }

        /* renamed from: -$$Nest$fgetresultGlobal, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10490$$Nest$fgetresultGlobal(EmojiSearchAdapter r0) {
            return r0.resultGlobal;
        }

        /* renamed from: -$$Nest$fgetresultPre, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10491$$Nest$fgetresultPre(EmojiSearchAdapter r0) {
            return r0.resultPre;
        }

        /* renamed from: -$$Nest$fgetsearchRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchRunnable m10492$$Nest$fgetsearchRunnable(EmojiSearchAdapter r0) {
            return r0.searchRunnable;
        }

        /* renamed from: -$$Nest$fgetselectedPackId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m10493$$Nest$fgetselectedPackId(EmojiSearchAdapter r2) {
            return r2.selectedPackId;
        }

        /* renamed from: -$$Nest$fgetselectedPackStickers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10494$$Nest$fgetselectedPackStickers(EmojiSearchAdapter r0) {
            return r0.selectedPackStickers;
        }

        /* renamed from: -$$Nest$fputisCompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10495$$Nest$fputisCompleted(EmojiSearchAdapter r0, boolean r1) {
            r0.isCompleted = r1;
        }

        /* renamed from: -$$Nest$fputlastSearchAlias, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10496$$Nest$fputlastSearchAlias(EmojiSearchAdapter r0, String r1) {
            r0.lastSearchAlias = r1;
        }

        /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10497$$Nest$fputsearchWas(EmojiSearchAdapter r0, boolean r1) {
            r0.searchWas = r1;
        }

        /* renamed from: -$$Nest$fputselectedPackStickers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10498$$Nest$fputselectedPackStickers(EmojiSearchAdapter r0, ArrayList r1) {
            r0.selectedPackStickers = r1;
        }

        /* renamed from: -$$Nest$msearchEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10499$$Nest$msearchEmoji(EmojiSearchAdapter r0, Runnable r1, ArrayList r2, boolean r3) {
            r0.searchEmoji(r1, r2, r3);
        }

        public EmojiSearchAdapter(EmojiView r14, Context r15) {
            this.this$0 = r14;
            this.result = new ArrayList();
            this.resultPre = new ArrayList();
            this.resultGlobal = new ArrayList();
            this.packs = new ArrayList();
            AnonymousClass1 r0 = new AnonymousClass1(this, r15, r14.currentAccount, -1, false, new EmojiView$EmojiSearchAdapter$$ExternalSyntheticLambda0(this), new EmojiView$EmojiSearchAdapter$$ExternalSyntheticLambda1(this), null, EmojiView.m10370$$Nest$fgetresourcesProvider(r14), -1, 0, r14);
            this.foundPacksListView = r0;
            r0.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(5.0f));
            this.foundPacksListView.setClipToPadding(false);
            this.foundPacksListView.adapter.setApplyBackground(false);
            this.foundPacksListView.setNestedScrollingEnabled(false);
            this.foundPacksListView.setDrawSelection(false);
            this.foundPacksListView.setOnTouchListener(new AnonymousClass2(this, r14));
        }

        private void foundPackListFillItems(ArrayList<UItem> r11, UniversalAdapter r12) {
            ArrayList<EmojiPackInfo> r122 = this.packs;
            int r0 = r122.size();
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L18;
            EmojiPackInfo r3 = r122.get(r2);
            r2 = r2 + 1;
            EmojiPackInfo r32 = r3;
            boolean r5 = true;
            if (EmojiPackInfo.m10482$$Nest$fgetstickerSet(r32) != null) goto L6;
            if (EmojiPackInfo.m10483$$Nest$fgetstickerSetCovered(r32) == null) goto L3;
            TLRPC.StickerSetCovered r4 = EmojiPackInfo.m10483$$Nest$fgetstickerSetCovered(r32);
            if (EmojiPackInfo.m10483$$Nest$fgetstickerSetCovered(r32).set.id == this.selectedPackId) goto L17;
            r5 = false;
        L17:
            r11.add(FoundStickerPackFactory.of(r4, r32, r5));
            goto L3
        L6:
            TLRPC.TL_messages_stickerSet r42 = EmojiPackInfo.m10482$$Nest$fgetstickerSet(r32);
            if (EmojiPackInfo.m10482$$Nest$fgetstickerSet(r32).set.id == this.selectedPackId) goto L10;
            r5 = false;
        L10:
            r11.add(FoundStickerPackFactory.of(r42, r5));
            goto L3
        }

        private void foundPackListOnClickItem(UItem r19, View r20, int r21, float r22, float r23) {
            Object r3 = r19.object;
            TLRPC.Document r7 = null;
            if ((r3 instanceof TLRPC.StickerSetCovered) == false) goto L10;
            TLRPC.StickerSetCovered r32 = (TLRPC.StickerSetCovered) r3;
            EmojiPackInfo r4 = (EmojiPackInfo) r19.object2;
            long r8 = this.selectedPackId;
            TLRPC.StickerSet r10 = r32.set;
            long r11 = r10.id;
            if (r8 != r11) goto L7;
            this.selectedPackId = 0;
            r10 = null;
        L8:
            TLRPC.StickerSet r14 = r10;
        L16:
            int r33 = this.foundPacksListView.getChildCount();
            boolean r42 = false;
            int r82 = 0;
        L18:
            if (r82 >= r33) goto L25;
            FoundStickerPackCell r102 = (FoundStickerPackCell) this.foundPacksListView.getChildAt(r82);
            if (r102 == r20) goto L23;
            r102.setSelected(false, true);
        L23:
            r82 = r82 + 1;
            goto L18
        L25:
            if (this.selectedPackId != 0) goto L27;
        L31:
            TLObject r13 = (TLObject) r19.object;
            EmojiView r112 = this.this$0;
            FoundStickerPackButton r12 = EmojiView.m10312$$Nest$fgetemojiAddPackButton(r112);
            ArrayList<TLRPC.Document> r1 = this.selectedPackStickers;
            if (r1 != null) goto L34;
        L36:
            TLRPC.Document r15 = r7;
            if (EmojiView.m10296$$Nest$fgetanimatorSearchEmojiPackSelected(this.this$0).getFloatValue() <= 0.0f) goto L39;
            boolean r17 = true;
        L40:
            EmojiView.m10446$$Nest$msetFoundPackButtonText(r112, r12, r13, r14, r15, true, r17);
            FoundStickerPackCell r16 = (FoundStickerPackCell) r20;
            if (this.selectedPackId == 0) goto L43;
            boolean r34 = true;
        L44:
            r16.setSelected(r34, true);
            BoolAnimator r18 = EmojiView.m10296$$Nest$fgetanimatorSearchEmojiPackSelected(this.this$0);
            if (this.selectedPackId == 0) goto L47;
            r42 = true;
        L47:
            r18.setValue(r42, true);
            notifyDataSetChanged();
            EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0).hideKeyboard();
            if (this.selectedPackId == 0) goto L54;
            this.foundPacksListView.scrollOnSelect(r20);
            return;
        L54:
            return;
        L43:
            r34 = false;
            goto L44
        L39:
            r17 = false;
            goto L40
        L34:
            if (r1.isEmpty() == true) goto L36;
            r7 = this.selectedPackStickers.get(0);
            goto L36
        L27:
            if (this.selectedPackStickers.size() >= this.selectedPackStickerSet.count) goto L31;
            TLRPC.TL_messages_stickerSet r35 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSet(this.selectedPackStickerSet, false);
            if (r35 == null) goto L31;
            this.selectedPackStickers = r35.documents;
            goto L31
        L7:
            this.selectedPackId = r11;
            this.selectedPackStickers = EmojiPackInfo.m10479$$Nest$fgetdocuments(r4);
            this.selectedPackStickerSet = r32.set;
            goto L8
        L10:
            if ((r3 instanceof TLRPC.TL_messages_stickerSet) == false) goto L15;
            TLRPC.TL_messages_stickerSet r36 = (TLRPC.TL_messages_stickerSet) r3;
            long r83 = this.selectedPackId;
            r10 = r36.set;
            long r113 = r10.id;
            if (r83 != r113) goto L14;
            this.selectedPackId = 0;
            goto L15
        L14:
            this.selectedPackId = r113;
            this.selectedPackStickers = r36.documents;
            this.selectedPackStickerSet = r10;
        L15:
            r14 = null;
            goto L16
        }

        private int globalSectionStart() {
            if (this.packs.isEmpty() == true) goto L6;
            int r0 = 3;
        L10:
            return r0 + this.result.size();
        L6:
            if (this.result.isEmpty() == true) goto L8;
            r0 = 2;
            goto L10
        L8:
            r0 = 1;
            goto L10
        }

        private /* synthetic */ void lambda$searchEmoji$0(String r6, ArrayList r7, Runnable r8, ArrayList r9) {
            if (r6.equals(this.lastSearchEmojiString) == true) goto L5;
            return;
        L5:
            AnimatedEmojiDrawable.getDocumentFetcher(this.this$0.currentAccount).putDocuments(r9);
            int r5 = r9.size();
            int r62 = 0;
        L6:
            if (r62 >= r5) goto L8;
            Object r0 = r9.get(r62);
            r62 = r62 + 1;
            MediaDataController.KeywordResult r1 = new MediaDataController.KeywordResult();
            r1.emoji = "animated_" + ((TLRPC.Document) r0).id;
            r1.keyword = null;
            r7.add(r1);
            goto L6
        L8:
            r8.run();
        }

        private void searchEmoji(Runnable r8, ArrayList<MediaDataController.KeywordResult> r9, boolean r10) {
            String[] r0 = EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(this.this$0);
            if (r0 != null) goto L5;
        L9:
            String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            String r3 = r02;
            String r4 = this.lastSearchEmojiString;
            if (r4 != null) goto L13;
            return;
        L13:
            MediaDataController.getInstance(this.this$0.currentAccount).searchStickers(true, r3, r4, new EmojiView$EmojiSearchAdapter$$ExternalSyntheticLambda2(this, r4, r9, r8), r10);
            return;
        L5:
            if (r0.length == 0) goto L9;
            r02 = r0[0];
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.selectedPackId == 0) goto L7;
            return this.selectedPackStickers.size() + 4;
        L7:
            if (this.result.isEmpty() == true) goto L9;
        L16:
            int r2 = 2;
            if (this.result.isEmpty() == false) goto L25;
            if (this.resultGlobal.isEmpty() == false) goto L25;
            if (this.packs.isEmpty() == false) goto L25;
            return 2;
        L25:
            if (this.packs.isEmpty() == true) goto L28;
            r2 = 3;
        L31:
            int r22 = r2 + this.result.size();
            if (this.resultGlobal.isEmpty() == false) goto L34;
            return r22;
        L34:
            return (r22 + 1) + this.resultGlobal.size();
        L28:
            if (this.result.isEmpty() == false) goto L31;
            r2 = 1;
            goto L31
        L9:
            if (this.resultGlobal.isEmpty() == false) goto L16;
            if (this.packs.isEmpty() == false) goto L16;
            if (this.searchWas == true) goto L16;
            return this.this$0.getRecentEmoji().size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r7) {
            if (this.selectedPackId == 0) goto L15;
            if (r7 != 0) goto L6;
            return 1;
        L6:
            if (r7 != 1) goto L8;
            return 4;
        L8:
            if (r7 != 2) goto L11;
            return 3;
        L11:
            if (r7 != (getItemCount() - 1)) goto L14;
            return 5;
        L14:
            return 0;
        L15:
            if (r7 != 0) goto L17;
            return 1;
        L17:
            if (r7 != 1) goto L28;
            if (this.searchWas == false) goto L28;
            if (this.result.isEmpty() == false) goto L28;
            if (this.resultGlobal.isEmpty() == false) goto L28;
            if (this.packs.isEmpty() == false) goto L28;
            return 2;
        L28:
            if (this.packs.isEmpty() == true) goto L34;
            if (r7 != 1) goto L31;
            return 4;
        L31:
            if (r7 != 2) goto L38;
            return 3;
        L38:
            if (this.resultGlobal.isEmpty() == false) goto L40;
        L42:
            return 0;
        L40:
            if (r7 != globalSectionStart()) goto L42;
            return 3;
        L34:
            if (this.result.isEmpty() == true) goto L38;
            if (r7 != 1) goto L38;
            return 3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L5;
            return true;
        L5:
            if (r1.getItemViewType() == 4) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            this.foundPacksListView.adapter.update(false);
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            int r0 = r9.getItemViewType();
            if (r0 != 0) goto L5;
            ImageViewEmoji r92 = (ImageViewEmoji) r9.itemView;
            r92.position = r10;
            ImageViewEmoji.m10527$$Nest$fputpack(r92, null);
            int r4 = r10 - 1;
            if (this.packs.isEmpty() == true) goto L20;
        L25:
            r4 = r10 - 3;
        L27:
            if (this.selectedPackId == 0) goto L31;
            TLRPC.Document r1 = this.selectedPackStickers.get(r4);
            String r102 = null;
            String r2 = null;
        L29:
            boolean r42 = false;
        L42:
            if (r102 != null) goto L44;
        L48:
            String r5 = r2;
            String r22 = r102;
            Long r103 = null;
        L49:
            if (r1 != null) goto L51;
            if (r103 != null) goto L51;
        L56:
            if (r1 != null) goto L60;
            if (r103 != null) goto L60;
            r92.setPadding(0, 0, 0, 0);
        L61:
            if (r1 == null) goto L67;
            r92.setImageDrawable(null, r42);
            if (r92.getSpan() != null) goto L65;
        L66:
            r92.setSpan(new AnimatedEmojiSpan(r1, null));
        L76:
            r92.setTag(r22);
            return;
        L65:
            if (r92.getSpan().document == r1) goto L76;
        L67:
            if (r103 == null) goto L73;
            r92.setImageDrawable(null, r42);
            if (r92.getSpan() != null) goto L71;
        L72:
            r92.setSpan(new AnimatedEmojiSpan(r103.longValue(), null));
            goto L76
        L71:
            if (r92.getSpan().getDocumentId() == r103.longValue()) goto L76;
        L73:
            if (r5 == null) goto L75;
            r92.setImageDrawable(Emoji.getEmojiBigDrawable(r5), r42);
            r92.setSpan(null);
            goto L76
        L75:
            r92.setImageDrawable(null, r42);
            r92.setSpan(null);
        L60:
            r92.setPadding(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        L51:
            EmojiView r8 = this.this$0;
            if (r1 == null) goto L54;
            long r6 = r1.id;
        L55:
            ImageViewEmoji.m10527$$Nest$fputpack(r92, EmojiView.m10429$$Nest$mfindEmojiPackByDocumentId(r8, r6));
            goto L56
        L54:
            r6 = r103.longValue();
            goto L55
        L44:
            if (r102.startsWith("animated_") == false) goto L48;
            r103 = Long.valueOf(Long.parseLong(r102.substring(9)));     // Catch: Exception -> L78
            r22 = null;
            r5 = null;
            goto L49
        L31:
            if (this.result.isEmpty() == true) goto L33;
        L37:
            int r104 = this.result.size();
            ArrayList<MediaDataController.KeywordResult> r23 = this.result;
            if (r4 >= r104) goto L41;
            r102 = r23.get(r4).emoji;
        L40:
            r2 = r102;
            r1 = null;
            goto L29
        L41:
            r102 = this.resultGlobal.get((r4 - r23.size()) - 1).emoji;
            goto L40
        L33:
            if (this.resultGlobal.isEmpty() == false) goto L37;
            if (this.searchWas == true) goto L37;
            r102 = this.this$0.getRecentEmoji().get(r4);
            r2 = r102;
            r42 = true;
            r1 = null;
            goto L42
        L20:
            if (this.selectedPackId != 0) goto L25;
            if (this.result.isEmpty() == true) goto L27;
            r4 = r10 - 2;
            goto L27
        L5:
            if (r0 == 3) goto L7;
            return;
        L7:
            StickerSetNameCell r93 = (StickerSetNameCell) r9.itemView;
            if (this.selectedPackId == 0) goto L12;
            r93.setText(LocaleController.formatPluralString("EmojiCount", this.selectedPackStickers.size(), new Object[0]), 0);
            return;
        L12:
            if (r10 != globalSectionStart()) goto L15;
            r93.setText(LocaleController.getString(R.string.StickerOrEmojiGlobalSearchResult), 0);
            return;
        L15:
            r93.setText(LocaleController.getString(R.string.StickerOrEmojiSearchResult), 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r11, int r12) {
            if (r12 != 0) goto L4;
            EmojiView r10 = this.this$0;
            View r122 = new ImageViewEmoji(r10, r10.getContext());
        L18:
            return new RecyclerListView.Holder(r122);
        L4:
            if (r12 != 1) goto L6;
            View r123 = new View(this.this$0.getContext());
            r123.setLayoutParams(new RecyclerView.LayoutParams(-1, EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0)));
            r122 = r123;
            goto L18
        L6:
            if (r12 != 3) goto L8;
            r122 = new StickerSetNameCell(this.this$0.getContext(), true, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0), EmojiView.m10349$$Nest$fgetglassDesign(this.this$0));
            goto L18
        L8:
            if (r12 != 4) goto L10;
            View r124 = this.foundPacksListView;
            r124.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(79.0f)));
            r122 = r124;
            goto L18
        L10:
            if (r12 == 5) goto L12;
            AnonymousClass3 r125 = new AnonymousClass3(this, this.this$0.getContext());
            TextView r1 = new TextView(this.this$0.getContext());
            r1.setText(LocaleController.getString(R.string.NoEmojiFound));
            r1.setTextSize(1, 16.0f);
            EmojiView r112 = this.this$0;
            int r2 = Theme.key_chat_emojiPanelEmptyText;
            r1.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(r112, r2));
            r125.addView(r1, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 10.0f, 0.0f, 0.0f));
            ImageView r113 = new ImageView(this.this$0.getContext());
            r113.setScaleType(ImageView.ScaleType.CENTER);
            r113.setImageResource(R.drawable.msg_emoji_question);
            r113.setColorFilter(new PorterDuffColorFilter(EmojiView.m10432$$Nest$mgetThemedColor(this.this$0, r2), PorterDuff.Mode.MULTIPLY));
            r125.addView(r113, LayoutHelper.createFrame(48, 48, 85));
            r113.setOnClickListener(new AnonymousClass4(this));
            r125.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r122 = r125;
            goto L18
        L12:
            View r126 = new View(this.this$0.getContext());
            r126.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(68.0f)));
            r122 = r126;
            goto L18
        }

        public void resetSelectedPackId() {
            int r0 = this.foundPacksListView.getChildCount();
            int r2 = 0;
        L4:
            if (r2 >= r0) goto L6;
            ((FoundStickerPackCell) this.foundPacksListView.getChildAt(r2)).setSelected(false, true);
            r2 = r2 + 1;
            goto L4
        L6:
            this.selectedPackId = 0;
            EmojiView.m10296$$Nest$fgetanimatorSearchEmojiPackSelected(this.this$0).setValue(false, true);
            notifyDataSetChanged();
        }

        public void search(String r2) {
            search(r2, true);
        }

        public void search(String r6, boolean r7) {
            long r2 = 0;
            if (TextUtils.isEmpty(r6) == false) goto L8;
            this.lastSearchEmojiString = null;
            if (EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).getAdapter() == EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0)) goto L7;
            EmojiView.m10314$$Nest$fgetemojiGridView(this.this$0).setAdapter(EmojiView.m10311$$Nest$fgetemojiAdapter(this.this$0));
            this.searchWas = false;
        L7:
            this.selectedPackId = 0;
            EmojiView.m10296$$Nest$fgetanimatorSearchEmojiPackSelected(this.this$0).setValue(false, true);
            notifyDataSetChanged();
        L9:
            SearchRunnable r62 = this.searchRunnable;
            if (r62 == null) goto L13;
            AndroidUtilities.cancelRunOnUIThread(r62);
        L13:
            if (TextUtils.isEmpty(this.lastSearchEmojiString) == true) goto L19;
            this.resultPre.clear();
            this.isCompleted = false;
            EmojiView.m10317$$Nest$fgetemojiSearchField(this.this$0).showProgress(true);
            AnonymousClass5 r63 = new AnonymousClass5(this);
            this.searchRunnable = r63;
            if (r7 == false) goto L17;
            r2 = 300;
        L17:
            AndroidUtilities.runOnUIThread(r63, r2);
            return;
        L19:
            return;
        L8:
            this.lastSearchEmojiString = r6.toLowerCase();
            goto L9
        }
    }

    public interface EmojiViewDelegate {
        default boolean canAddCaptionToGif(TLRPC.Document r1) {
            return false;
        }

        default boolean canSchedule() {
            return false;
        }

        default long getDialogId() {
            return 0;
        }

        default float getProgressToSearchOpened() {
            return 0.0f;
        }

        default int getThreadId() {
            return 0;
        }

        default void invalidateEnterView() {
        }

        default boolean isExpanded() {
            return false;
        }

        default boolean isInScheduleMode() {
            return false;
        }

        default boolean isSearchOpened() {
            return false;
        }

        default boolean isUserSelf() {
            return false;
        }

        default void onAnimatedEmojiUnlockClick() {
        }

        default boolean onBackspace() {
            return false;
        }

        default void onClearEmojiRecent() {
        }

        void onCustomEmojiSelected(long r1, TLRPC.Document r3, String r4, boolean r5);

        default void onEmojiSelected(String r1) {
        }

        default void onEmojiSettingsClick(ArrayList<TLRPC.TL_messages_stickerSet> r1) {
        }

        default void onGifSelected(View r1, Object r2, String r3, Object r4, boolean r5, int r6, int r7) {
        }

        default void onGifSelectedForAddCaption(View r1, Object r2, String r3, Object r4, boolean r5, int r6, int r7) {
        }

        default void onSearchOpenClose(int r1) {
        }

        default void onShowStickerSet(TLRPC.StickerSet r1, TLRPC.InputStickerSet r2, boolean r3) {
        }

        default void onStickerSelected(View r1, TLRPC.Document r2, String r3, Object r4, MessageObject.SendAnimationData r5, boolean r6, int r7, int r8) {
        }

        default void onStickerSetAdd(TLRPC.StickerSetCovered r1) {
        }

        default void onStickerSetRemove(TLRPC.StickerSetCovered r1) {
        }

        default void onStickersGroupClick(long r1) {
        }

        default void onStickersSettingsClick() {
        }

        default void onTabOpened(int r1) {
        }

        default void showTrendingStickersAlert(TrendingStickersLayout r1) {
        }
    }

    public static class FoundStickerPackFactory extends UItem.UItemFactory<FoundStickerPackCell> {
        static {
            UItem.UItemFactory.setup(new FoundStickerPackFactory());
        }

        private FoundStickerPackFactory() {
        }

        public static UItem of(TLRPC.StickerSetCovered r5, EmojiPackInfo r6, boolean r7) {
            UItem r0 = UItem.ofFactory(FoundStickerPackFactory.class);
            r0.id = Long.hashCode(r5.set.id + 1);
            r0.longValue = r5.set.id;
            r0.object = r5;
            r0.object2 = r6;
            r0.checked = r7;
            return r0;
        }

        public static UItem of(TLRPC.TL_messages_stickerSet r3, boolean r4) {
            UItem r0 = UItem.ofFactory(FoundStickerPackFactory.class);
            r0.id = Long.hashCode(r3.set.id);
            r0.longValue = r3.set.id;
            r0.object = r3;
            r0.checked = r4;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            FoundStickerPackCell r12 = (FoundStickerPackCell) r1;
            Object r0 = r2.object;
            if ((r0 instanceof TLRPC.TL_messages_stickerSet) == false) goto L6;
            r12.setPack((TLRPC.TL_messages_stickerSet) r0);
        L8:
            r12.setSelected(r2.checked, false);
            return;
        L6:
            if ((r0 instanceof TLRPC.StickerSetCovered) == false) goto L8;
            r12.setPack((TLRPC.StickerSetCovered) r0, EmojiPackInfo.m10480$$Nest$fgetfirstDocument((EmojiPackInfo) r2.object2));
            goto L8
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r5, UItem r6) {
            if (r5.longValue == r6.longValue) goto L5;
            return false;
        L5:
            if (r5.checked != r6.checked) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public FoundStickerPackCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            FoundStickerPackCell r0 = new FoundStickerPackCell(r1, r5);
            r0.setLayoutParams(new RecyclerView.LayoutParams(AndroidUtilities.dp(64.0f), -1));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r3, UItem r4) {
            if (r3.longValue != r4.longValue) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public class GifAdapter extends RecyclerListView.SelectionAdapter {
        private boolean addSearch;
        private TLRPC.User bot;
        private final Context context;
        private int firstResultItem;
        private int itemsCount;
        private String lastSearchImageString;
        private boolean lastSearchIsEmoji;
        private final int maxRecentRowsCount;
        private String nextSearchOffset;
        private final GifProgressEmptyView progressEmptyView;
        private int recentItemsCount;
        private int reqId;
        private ArrayList<TLRPC.BotInlineResult> results;
        private HashMap<String, TLRPC.BotInlineResult> resultsMap;
        private boolean searchEndReached;
        private Runnable searchRunnable;
        private boolean searchingUser;
        private boolean showTrendingWhenSearchEmpty;
        final /* synthetic */ EmojiView this$0;
        private int trendingSectionItem;
        private final boolean withRecent;

        public static /* synthetic */ void $r8$lambda$HzQZW7GQdiwKPzXCLdGIq4zi0gI(GifAdapter r0, String r1) {
            r0.lambda$search$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$IRFlBiNZ7TSS5uIGtFC9EOUsIN4(GifAdapter r0, TLObject r1) {
            r0.lambda$searchBotUser$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$hHyc_KrhyMLnzUfG3lkdz_jOejA(GifAdapter r0, String r1, String r2, boolean r3, boolean r4, boolean r5, String r6, TLObject r7) {
            r0.lambda$search$3(r1, r2, r3, r4, r5, r6, r7);
        }

        public static /* synthetic */ void $r8$lambda$k2KLawDKXP5HjKriHUi2eplOPXY(GifAdapter r0, String r1, String r2, boolean r3, boolean r4, boolean r5, String r6, TLObject r7, TLRPC.TL_error r8) {
            r0.lambda$search$4(r1, r2, r3, r4, r5, r6, r7, r8);
        }

        public static /* synthetic */ void $r8$lambda$oJrI4IQsw4y6SlAnZ4G_r4XTZPw(GifAdapter r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$searchBotUser$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgetaddSearch, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10504$$Nest$fgetaddSearch(GifAdapter r0) {
            return r0.addSearch;
        }

        /* renamed from: -$$Nest$fgetbot, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.User m10505$$Nest$fgetbot(GifAdapter r0) {
            return r0.bot;
        }

        /* renamed from: -$$Nest$fgetlastSearchImageString, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m10506$$Nest$fgetlastSearchImageString(GifAdapter r0) {
            return r0.lastSearchImageString;
        }

        /* renamed from: -$$Nest$fgetlastSearchIsEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10507$$Nest$fgetlastSearchIsEmoji(GifAdapter r0) {
            return r0.lastSearchIsEmoji;
        }

        /* renamed from: -$$Nest$fgetnextSearchOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m10508$$Nest$fgetnextSearchOffset(GifAdapter r0) {
            return r0.nextSearchOffset;
        }

        /* renamed from: -$$Nest$fgetprogressEmptyView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GifProgressEmptyView m10509$$Nest$fgetprogressEmptyView(GifAdapter r0) {
            return r0.progressEmptyView;
        }

        /* renamed from: -$$Nest$fgetrecentItemsCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10510$$Nest$fgetrecentItemsCount(GifAdapter r0) {
            return r0.recentItemsCount;
        }

        /* renamed from: -$$Nest$fgetreqId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10511$$Nest$fgetreqId(GifAdapter r0) {
            return r0.reqId;
        }

        /* renamed from: -$$Nest$fgetresults, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10512$$Nest$fgetresults(GifAdapter r0) {
            return r0.results;
        }

        /* renamed from: -$$Nest$fgetsearchEndReached, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10513$$Nest$fgetsearchEndReached(GifAdapter r0) {
            return r0.searchEndReached;
        }

        /* renamed from: -$$Nest$fgettrendingSectionItem, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10514$$Nest$fgettrendingSectionItem(GifAdapter r0) {
            return r0.trendingSectionItem;
        }

        /* renamed from: -$$Nest$fputshowTrendingWhenSearchEmpty, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10515$$Nest$fputshowTrendingWhenSearchEmpty(GifAdapter r0, boolean r1) {
            r0.showTrendingWhenSearchEmpty = r1;
        }

        /* renamed from: -$$Nest$mprocessResponse, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10516$$Nest$mprocessResponse(GifAdapter r0, String r1, String r2, boolean r3, boolean r4, boolean r5, String r6, TLObject r7) {
            r0.processResponse(r1, r2, r3, r4, r5, r6, r7);
        }

        public GifAdapter(EmojiView r2, Context r3) {
            this(r2, r3, false, 0);
        }

        public GifAdapter(EmojiView r2, Context r3, boolean r4) {
            if (r4 == false) goto L4;
            int r0 = Integer.MAX_VALUE;
        L5:
            this(r2, r3, r4, r0);
            return;
        L4:
            r0 = 0;
            goto L5
        }

        public GifAdapter(EmojiView r2, Context r3, boolean r4, int r5) {
            this.this$0 = r2;
            this.results = new ArrayList();
            this.resultsMap = new HashMap();
            this.trendingSectionItem = -1;
            this.firstResultItem = -1;
            this.context = r3;
            this.withRecent = r4;
            this.maxRecentRowsCount = r5;
            if (r4 == false) goto L5;
            GifProgressEmptyView r22 = null;
        L6:
            this.progressEmptyView = r22;
            return;
        L5:
            r22 = new GifProgressEmptyView(r2, r3);
            goto L6
        }

        private /* synthetic */ void lambda$search$2(String r3) {
            search(r3, _UrlKt.FRAGMENT_ENCODE_SET, true);
        }

        private /* synthetic */ void lambda$search$3(String r1, String r2, boolean r3, boolean r4, boolean r5, String r6, TLObject r7) {
            processResponse(r1, r2, r3, r4, r5, r6, r7);
        }

        private /* synthetic */ void lambda$search$4(String r1, String r2, boolean r3, boolean r4, boolean r5, String r6, TLObject r7, TLRPC.TL_error r8) {
            AndroidUtilities.runOnUIThread(new EmojiView$GifAdapter$$ExternalSyntheticLambda2(this, r1, r2, r3, r4, r5, r6, r7));
        }

        private /* synthetic */ void lambda$searchBotUser$0(TLObject r5) {
            TLRPC.TL_contacts_resolvedPeer r52 = (TLRPC.TL_contacts_resolvedPeer) r5;
            MessagesController.getInstance(this.this$0.currentAccount).putUsers(r52.users, false);
            MessagesController.getInstance(this.this$0.currentAccount).putChats(r52.chats, false);
            MessagesStorage.getInstance(this.this$0.currentAccount).putUsersAndChats(r52.users, r52.chats, true, true);
            String r53 = this.lastSearchImageString;
            this.lastSearchImageString = null;
            search(r53, _UrlKt.FRAGMENT_ENCODE_SET, false);
        }

        private /* synthetic */ void lambda$searchBotUser$1(TLObject r1, TLRPC.TL_error r2) {
            if (r1 == null) goto L5;
            AndroidUtilities.runOnUIThread(new EmojiView$GifAdapter$$ExternalSyntheticLambda3(this, r1));
            return;
        }

        private void processResponse(String r5, String r6, boolean r7, boolean r8, boolean r9, String r10, TLObject r11) {
            if (r5 != null) goto L4;
            return;
        L4:
            if (r5.equals(this.lastSearchImageString) == false) goto L80;
            boolean r0 = false;
            this.reqId = 0;
            if (r9 == false) goto L15;
            if ((r11 instanceof TLRPC.messages_BotResults) == true) goto L11;
        L12:
            search(r5, r6, r7, r8, false);
            return;
        L11:
            if (((TLRPC.messages_BotResults) r11).results.isEmpty() == true) goto L12;
        L15:
            if (this.withRecent == true) goto L20;
            if (TextUtils.isEmpty(r6) == false) goto L20;
            this.results.clear();
            this.resultsMap.clear();
            EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).showProgress(false);
        L20:
            if ((r11 instanceof TLRPC.messages_BotResults) == false) goto L59;
            int r72 = this.results.size();
            TLRPC.messages_BotResults r112 = (TLRPC.messages_BotResults) r11;
            if (EmojiView.m10338$$Nest$fgetgifCache(this.this$0).containsKey(r10) == true) goto L24;
            EmojiView.m10338$$Nest$fgetgifCache(this.this$0).put(r10, r112);
        L24:
            if (r9 == false) goto L26;
        L28:
            this.nextSearchOffset = r112.next_offset;
            int r92 = 0;
            int r102 = 0;
        L30:
            if (r92 >= r112.results.size()) goto L37;
            TLRPC.BotInlineResult r1 = r112.results.get(r92);
            if (this.resultsMap.containsKey(r1.id) == true) goto L35;
            r1.query_id = r112.query_id;
            this.results.add(r1);
            this.resultsMap.put(r1.id, r1);
            r102 = r102 + 1;
        L35:
            r92 = r92 + 1;
            goto L30
        L37:
            if (r72 != this.results.size()) goto L39;
        L40:
            r0 = true;
        L41:
            this.searchEndReached = r0;
            if (r102 == 0) goto L57;
            if (r8 == false) goto L47;
            if (r72 != 0) goto L47;
            notifyDataSetChanged();
        L61:
            if (this.withRecent == false) goto L63;
            return;
        L63:
            if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter() == this) goto L65;
            EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).setAdapter(this);
        L65:
            if (r8 == true) goto L67;
            return;
        L67:
            if (TextUtils.isEmpty(r5) == false) goto L69;
            return;
        L69:
            if (TextUtils.isEmpty(r6) == false) goto L79;
            EmojiView.m10444$$Nest$mscrollGifsToTop(this.this$0);
            return;
        L79:
            return;
        L47:
            updateItems();
            if (this.withRecent == false) goto L53;
            int r93 = this.recentItemsCount;
            if (r72 == 0) goto L52;
            notifyItemChanged((r93 + (EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0).addSearch ? 1 : 0)) + r72);
            notifyItemRangeInserted(((this.recentItemsCount + (EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0).addSearch ? 1 : 0)) + r72) + 1, r102);
            goto L61
        L52:
            notifyItemRangeInserted(r93 + (EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0).addSearch ? 1 : 0), r102 + 1);
            goto L61
        L53:
            if (r72 == 0) goto L55;
            notifyItemChanged(r72);
        L55:
            notifyItemRangeInserted(r72 + (EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0).addSearch ? 1 : 0), r102);
            goto L61
        L57:
            if (this.results.isEmpty() == false) goto L61;
            notifyDataSetChanged();
            goto L61
        L39:
            if (TextUtils.isEmpty(this.nextSearchOffset) == false) goto L41;
        L26:
            if (r112.cache_time == 0) goto L28;
            MessagesStorage.getInstance(this.this$0.currentAccount).saveBotCache(r10, r112);
            goto L28
        L59:
            notifyDataSetChanged();
            goto L61
        }

        private void searchBotUser() {
            if (this.searchingUser == false) goto L5;
            return;
        L5:
            this.searchingUser = true;
            TLRPC.TL_contacts_resolveUsername r0 = new TLRPC.TL_contacts_resolveUsername();
            r0.username = MessagesController.getInstance(this.this$0.currentAccount).gifSearchBot;
            ConnectionsManager.getInstance(this.this$0.currentAccount).sendRequest(r0, new EmojiView$GifAdapter$$ExternalSyntheticLambda1(this));
        }

        private void updateItems() {
            this.trendingSectionItem = -1;
            this.firstResultItem = -1;
            this.itemsCount = 0;
            if (this.addSearch == false) goto L6;
            this.itemsCount = 1;
        L6:
            if (this.withRecent == false) goto L8;
            this.itemsCount += this.recentItemsCount;
        L8:
            boolean r0 = this.results.isEmpty();
            boolean r2 = this.withRecent;
            if (r0 == true) goto L16;
            if (r2 == true) goto L12;
        L14:
            int r02 = this.itemsCount;
            this.firstResultItem = r02;
            this.itemsCount = r02 + this.results.size();
            return;
        L12:
            if (this.recentItemsCount <= 0) goto L14;
            int r03 = this.itemsCount;
            this.itemsCount = r03 + 1;
            this.trendingSectionItem = r03;
            goto L14
        L16:
            if (r2 == true) goto L19;
            this.itemsCount++;
            return;
        }

        private void updateRecentItemsCount() {
            if (this.withRecent == false) goto L31;
            int r0 = this.maxRecentRowsCount;
            if (r0 == 0) goto L33;
            EmojiView r1 = this.this$0;
            if (r0 != Integer.MAX_VALUE) goto L12;
            this.recentItemsCount = EmojiView.m10366$$Nest$fgetrecentGifs(r1).size();
            return;
        L12:
            if (EmojiView.m10341$$Nest$fgetgifGridView(r1).getMeasuredWidth() == 0) goto L34;
            int r02 = EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getMeasuredWidth();
            int r12 = EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).getSpanCount();
            int r2 = AndroidUtilities.dp(100.0f);
            this.recentItemsCount = 0;
            int r4 = EmojiView.m10366$$Nest$fgetrecentGifs(this.this$0).size();
            int r6 = r12;
            int r5 = 0;
            int r7 = 0;
            int r8 = 0;
        L15:
            if (r5 >= r4) goto L24;
            Size r9 = EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).fixSize(EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).getSizeForItem((TLRPC.Document) EmojiView.m10366$$Nest$fgetrecentGifs(this.this$0).get(r5)));
            int r92 = Math.min(r12, (int) Math.floor(r12 * (((r9.width / r9.height) * r2) / r02)));
            if (r6 >= r92) goto L22;
            this.recentItemsCount += r7;
            r8 = r8 + 1;
            if (r8 == this.maxRecentRowsCount) goto L24;
            r6 = r12;
            r7 = 0;
        L22:
            r7 = r7 + 1;
            r6 = r6 - r92;
            r5 = r5 + 1;
        L24:
            if (r8 >= this.maxRecentRowsCount) goto L32;
            this.recentItemsCount += r7;
            return;
        L32:
            return;
        L34:
            return;
        L33:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.itemsCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 == 0) goto L4;
        L7:
            boolean r0 = this.withRecent;
            if (r0 == true) goto L10;
        L13:
            if (r0 == false) goto L15;
            return 0;
        L15:
            if (this.results.isEmpty() == false) goto L20;
            return 3;
        L20:
            return 0;
        L10:
            if (r3 != this.trendingSectionItem) goto L13;
            return 2;
        L4:
            if (this.addSearch == false) goto L7;
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void loadTrendingGifs() {
            search(_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, true, true, true);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            updateRecentItemsCount();
            updateItems();
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            if (r8.getItemViewType() == 0) goto L5;
            return;
        L5:
            ContextLinkCell r0 = (ContextLinkCell) r8.itemView;
            int r82 = this.firstResultItem;
            if (r82 < 0) goto L10;
            if (r9 < r82) goto L10;
            r0.setLink(this.results.get(r9 - r82), this.bot, true, false, false, true);
            return;
        L10:
            r0.setGif((TLRPC.Document) EmojiView.m10366$$Nest$fgetrecentGifs(this.this$0).get(r9 - (this.addSearch ? 1 : 0)), false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r5, int r6) {
            if (r6 != 0) goto L5;
            ContextLinkCell r62 = new ContextLinkCell(this.context);
            r62.setIsKeyboard(true);
            r62.setCanPreviewGif(true);
            View r4 = r62;
        L14:
            return new RecyclerListView.Holder(r4);
        L5:
            if (r6 != 1) goto L7;
            View r52 = new View(this.this$0.getContext());
            r52.setLayoutParams(new RecyclerView.LayoutParams(-1, EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0)));
            View r53 = r52;
        L10:
            r4 = r53;
            goto L14
        L7:
            if (r6 == 2) goto L9;
            r4 = this.progressEmptyView;
            r4.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            goto L14
        L9:
            StickerSetNameCell r54 = new StickerSetNameCell(this.context, false, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0), EmojiView.m10349$$Nest$fgetglassDesign(this.this$0));
            r54.setText(LocaleController.getString(R.string.FeaturedGifs), 0);
            RecyclerView.LayoutParams r42 = new RecyclerView.LayoutParams(-1, -2);
            ((ViewGroup.MarginLayoutParams) r42).topMargin = AndroidUtilities.dp(2.5f);
            ((ViewGroup.MarginLayoutParams) r42).bottomMargin = AndroidUtilities.dp(5.5f);
            r54.setLayoutParams(r42);
            r53 = r54;
            goto L10
        }

        public void search(String r2) {
            search(r2, true);
        }

        public void search(String r7, String r8, boolean r9) {
            search(r7, r8, r9, false, false);
        }

        public void search(String r11, String r12, boolean r13, boolean r14, boolean r15) {
            int r0 = this.reqId;
            if (r0 == 0) goto L7;
            if (r0 < 0) goto L6;
            ConnectionsManager.getInstance(this.this$0.currentAccount).cancelRequest(this.reqId, true);
        L6:
            this.reqId = 0;
        L7:
            this.lastSearchImageString = r11;
            this.lastSearchIsEmoji = r14;
            GifProgressEmptyView r02 = this.progressEmptyView;
            if (r02 == null) goto L10;
            r02.setLoadingState(r14);
        L10:
            TLObject r03 = MessagesController.getInstance(this.this$0.currentAccount).getUserOrChat(MessagesController.getInstance(this.this$0.currentAccount).gifSearchBot);
            if ((r03 instanceof TLRPC.User) == true) goto L18;
            if (r13 == false) goto L40;
            searchBotUser();
            if (this.withRecent == true) goto L50;
            EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).showProgress(true);
            return;
        L50:
            return;
        L40:
            return;
        L18:
            if (this.withRecent == false) goto L20;
        L22:
            this.bot = (TLRPC.User) r03;
            String r8 = "gif_search_" + r11 + "_" + r12;
            EmojiView$GifAdapter$$ExternalSyntheticLambda0 r2 = new EmojiView$GifAdapter$$ExternalSyntheticLambda0(this, r11, r12, r13, r14, r15, r8);
            if (r15 == false) goto L25;
        L33:
            if (r15 == false) goto L39;
            if (EmojiView.m10338$$Nest$fgetgifCache(this.this$0).containsKey(r8) == false) goto L39;
            processResponse(r11, r12, r13, r14, true, r8, (TLObject) EmojiView.m10338$$Nest$fgetgifCache(this.this$0).get(r8));
            return;
        L39:
            if (EmojiView.m10346$$Nest$fgetgifSearchPreloader(this.this$0).isLoading(r8) == true) goto L51;
            if (r15 == false) goto L44;
            this.reqId = -1;
            MessagesStorage.getInstance(this.this$0.currentAccount).getBotCache(r8, r2);
            return;
        L44:
            TLRPC.TL_messages_getInlineBotResults r112 = new TLRPC.TL_messages_getInlineBotResults();
            if (r11 != null) goto L47;
            String r122 = _UrlKt.FRAGMENT_ENCODE_SET;
        L48:
            r112.query = r122;
            r112.bot = MessagesController.getInstance(this.this$0.currentAccount).getInputUser(this.bot);
            r112.offset = r12;
            r112.peer = new TLRPC.TL_inputPeerEmpty();
            this.reqId = ConnectionsManager.getInstance(this.this$0.currentAccount).sendRequest(r112, r2);
            return;
        L47:
            r122 = r11;
            goto L48
        L51:
            return;
        L25:
            if (this.withRecent == true) goto L33;
            if (r14 == false) goto L33;
            if (TextUtils.isEmpty(r12) == false) goto L33;
            this.results.clear();
            this.resultsMap.clear();
            if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter() == this) goto L32;
            EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).setAdapter(this);
        L32:
            notifyDataSetChanged();
            EmojiView.m10444$$Nest$mscrollGifsToTop(this.this$0);
            goto L33
        L20:
            if (TextUtils.isEmpty(r12) == false) goto L22;
            EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).showProgress(true);
            goto L22
        }

        public void search(String r5, boolean r6) {
            if (this.withRecent == true) goto L43;
            int r0 = this.reqId;
            if (r0 == 0) goto L10;
            if (r0 < 0) goto L9;
            ConnectionsManager.getInstance(this.this$0.currentAccount).cancelRequest(this.reqId, true);
        L9:
            this.reqId = 0;
        L10:
            this.lastSearchIsEmoji = false;
            GifProgressEmptyView r02 = this.progressEmptyView;
            if (r02 == null) goto L13;
            r02.setLoadingState(false);
        L13:
            Runnable r03 = this.searchRunnable;
            if (r03 == null) goto L17;
            AndroidUtilities.cancelRunOnUIThread(r03);
        L17:
            if (TextUtils.isEmpty(r5) == false) goto L33;
            this.lastSearchImageString = null;
            if (this.showTrendingWhenSearchEmpty == false) goto L22;
            loadTrendingGifs();
            return;
        L22:
            int r52 = EmojiView.m10347$$Nest$fgetgifTabs(this.this$0).getCurrentPosition();
            if (r52 == EmojiView.m10343$$Nest$fgetgifRecentTabNum(this.this$0)) goto L30;
            if (r52 == EmojiView.m10348$$Nest$fgetgifTrendingTabNum(this.this$0)) goto L30;
            searchEmoji(MessagesController.getInstance(this.this$0.currentAccount).gifSearchEmojies.get(r52 - EmojiView.m10340$$Nest$fgetgifFirstEmojiTabNum(this.this$0)));
            return;
        L30:
            if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter() == EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0)) goto L41;
            EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).setAdapter(EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0));
            return;
        L41:
            return;
        L33:
            String r04 = r5.toLowerCase();
            this.lastSearchImageString = r04;
            if (TextUtils.isEmpty(r04) == true) goto L42;
            EmojiView$GifAdapter$$ExternalSyntheticLambda4 r05 = new EmojiView$GifAdapter$$ExternalSyntheticLambda4(this, r5);
            this.searchRunnable = r05;
            if (r6 == false) goto L38;
            long r4 = 300;
        L39:
            AndroidUtilities.runOnUIThread(r05, r4);
            return;
        L38:
            r4 = 0;
            goto L39
        L42:
            return;
        }

        public void searchEmoji(String r7) {
            if (this.lastSearchIsEmoji == true) goto L5;
        L8:
            search(r7, _UrlKt.FRAGMENT_ENCODE_SET, true, true, true);
            return;
        L5:
            if (TextUtils.equals(this.lastSearchImageString, r7) == false) goto L8;
            EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
        }
    }

    public class GifLayoutManager extends ExtendedGridLayoutManager {
        private Size size;
        final /* synthetic */ EmojiView this$0;

        /* renamed from: org.telegram.ui.Components.EmojiView$GifLayoutManager$1, reason: invalid class name */
        public class AnonymousClass1 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ GifLayoutManager this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass1(GifLayoutManager r1, EmojiView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r3) {
                if (r3 != 0) goto L6;
                if (GifAdapter.m10504$$Nest$fgetaddSearch(EmojiView.m10337$$Nest$fgetgifAdapter(this.this$1.this$0)) == false) goto L6;
            L10:
                return this.this$1.getSpanCount();
            L6:
                if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$1.this$0).getAdapter() == EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$1.this$0)) goto L8;
            L11:
                GifLayoutManager r2 = this.this$1;
                return r2.getSpanSizeForItem(r3 - (GifAdapter.m10504$$Nest$fgetaddSearch(EmojiView.m10337$$Nest$fgetgifAdapter(r2.this$0)) ? 1 : 0));
            L8:
                if (GifAdapter.m10512$$Nest$fgetresults(EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$1.this$0)).isEmpty() == false) goto L11;
                goto L11
            }
        }

        public GifLayoutManager(EmojiView r3, Context r4) {
            this.this$0 = r3;
            super(r4, 100, true);
            this.size = new Size();
            setSpanSizeLookup(new AnonymousClass1(this, r3));
        }

        @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
        public int getFlowItemCount() {
            if (EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter() != EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)) goto L9;
            if (GifAdapter.m10512$$Nest$fgetresults(EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)).isEmpty() == false) goto L9;
            return 0;
        L9:
            return getItemCount() - 1;
        }

        @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
        public Size getSizeForItem(int r5) {
            RecyclerView.Adapter r0 = EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getAdapter();
            GifAdapter r1 = EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0);
            EmojiView r2 = this.this$0;
            TLRPC.Document r3 = null;
            ArrayList<TLRPC.DocumentAttribute> r32 = null;
            r32 = null;
            if (r0 != r1) goto L21;
            int r02 = GifAdapter.m10510$$Nest$fgetrecentItemsCount(EmojiView.m10337$$Nest$fgetgifAdapter(r2));
            EmojiView r12 = this.this$0;
            if (r5 <= r02) goto L17;
            TLRPC.BotInlineResult r52 = (TLRPC.BotInlineResult) GifAdapter.m10512$$Nest$fgetresults(EmojiView.m10337$$Nest$fgetgifAdapter(r12)).get((r5 - GifAdapter.m10510$$Nest$fgetrecentItemsCount(EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0))) - 1);
            TLRPC.Document r03 = r52.document;
            if (r03 == null) goto L9;
            r32 = r03.attributes;
        L15:
            ArrayList<TLRPC.DocumentAttribute> r53 = r32;
            r3 = r03;
        L33:
            return getSizeForItem(r3, r53);
        L9:
            TLRPC.WebDocument r13 = r52.content;
            if (r13 == null) goto L12;
            r32 = r13.attributes;
            goto L15
        L12:
            TLRPC.WebDocument r54 = r52.thumb;
            if (r54 == null) goto L15;
            r32 = r54.attributes;
            goto L15
        L17:
            if (r5 != GifAdapter.m10510$$Nest$fgetrecentItemsCount(EmojiView.m10337$$Nest$fgetgifAdapter(r12))) goto L19;
            return null;
        L19:
            r3 = (TLRPC.Document) EmojiView.m10366$$Nest$fgetrecentGifs(this.this$0).get(r5);
            r53 = r3.attributes;
            goto L33
        L21:
            if (GifAdapter.m10512$$Nest$fgetresults(EmojiView.m10344$$Nest$fgetgifSearchAdapter(r2)).isEmpty() == true) goto L31;
            TLRPC.BotInlineResult r55 = (TLRPC.BotInlineResult) GifAdapter.m10512$$Nest$fgetresults(EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)).get(r5);
            r03 = r55.document;
            if (r03 == null) goto L25;
            r32 = r03.attributes;
            goto L15
        L25:
            TLRPC.WebDocument r14 = r55.content;
            if (r14 == null) goto L28;
            r32 = r14.attributes;
            goto L15
        L28:
            TLRPC.WebDocument r56 = r55.thumb;
            if (r56 == null) goto L15;
            r32 = r56.attributes;
            goto L15
        L31:
            r53 = null;
            goto L33
        }

        public Size getSizeForItem(TLRPC.Document r2) {
            return getSizeForItem(r2, r2.attributes);
        }

        public Size getSizeForItem(TLRPC.Document r3, List<TLRPC.DocumentAttribute> r4) {
            Size r0 = this.size;
            r0.height = 100.0f;
            r0.width = 100.0f;
            if (r3 == null) goto L11;
            TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r3.thumbs, 90);
            if (r32 == null) goto L11;
            int r02 = r32.w;
            if (r02 == 0) goto L11;
            int r33 = r32.h;
            if (r33 == 0) goto L11;
            Size r1 = this.size;
            r1.width = r02;
            r1.height = r33;
        L11:
            if (r4 == null) goto L23;
            int r34 = 0;
        L14:
            if (r34 >= r4.size()) goto L23;
            TLRPC.DocumentAttribute r03 = r4.get(r34);
            if ((r03 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L21;
            if ((r03 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L21;
            r34 = r34 + 1;
        L21:
            Size r35 = this.size;
            r35.width = r03.w;
            r35.height = r03.h;
        L23:
            return this.size;
        }
    }

    public class GifProgressEmptyView extends FrameLayout {
        private final ImageView imageView;
        private boolean loadingState;
        private final RadialProgressView progressView;
        private final TextView textView;
        final /* synthetic */ EmojiView this$0;

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageView m10517$$Nest$fgetimageView(GifProgressEmptyView r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RadialProgressView m10518$$Nest$fgetprogressView(GifProgressEmptyView r0) {
            return r0.progressView;
        }

        /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m10519$$Nest$fgettextView(GifProgressEmptyView r0) {
            return r0.textView;
        }

        public GifProgressEmptyView(EmojiView r13, Context r14) {
            this.this$0 = r13;
            super(r14);
            ImageView r0 = new ImageView(getContext());
            this.imageView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            r0.setImageResource(R.drawable.gif_empty);
            int r2 = Theme.key_chat_emojiPanelEmptyText;
            r0.setColorFilter(new PorterDuffColorFilter(EmojiView.m10432$$Nest$mgetThemedColor(r13, r2), PorterDuff.Mode.MULTIPLY));
            addView(r0, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 8.0f, 0.0f, 0.0f));
            TextView r02 = new TextView(getContext());
            this.textView = r02;
            r02.setText(LocaleController.getString(R.string.NoGIFsFound));
            r02.setTextSize(1, 16.0f);
            r02.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(r13, r2));
            addView(r02, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 42.0f, 0.0f, 0.0f));
            RadialProgressView r03 = new RadialProgressView(r14, EmojiView.m10370$$Nest$fgetresourcesProvider(r13));
            this.progressView = r03;
            r03.setVisibility(8);
            r03.setProgressColor(EmojiView.m10432$$Nest$mgetThemedColor(r13, Theme.key_progressCircle));
            addView(r03, LayoutHelper.createFrame(-2, -2, 17));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            int r32 = EmojiView.m10341$$Nest$fgetgifGridView(this.this$0).getMeasuredHeight();
            if (this.loadingState == true) goto L5;
            int r33 = (int) ((((r32 - EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0)) - AndroidUtilities.dp(8.0f)) / 3) * 1.7f);
        L6:
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(r33, TLObject.FLAG_30));
            return;
        L5:
            r33 = r32 - AndroidUtilities.dp(80.0f);
            goto L6
        }

        public void setLoadingState(boolean r5) {
            if (this.loadingState == r5) goto L18;
            this.loadingState = r5;
            ImageView r0 = this.imageView;
            int r1 = 0;
            if (r5 == false) goto L7;
            int r3 = 8;
        L8:
            r0.setVisibility(r3);
            TextView r02 = this.textView;
            if (r5 == false) goto L11;
            int r32 = 8;
        L12:
            r02.setVisibility(r32);
            RadialProgressView r4 = this.progressView;
            if (r5 == true) goto L16;
            r1 = 8;
        L16:
            r4.setVisibility(r1);
            return;
        L11:
            r32 = 0;
            goto L12
        L7:
            r3 = 0;
            goto L8
        }
    }

    public class GifSearchPreloader {
        private final List<String> loadingKeys;
        final /* synthetic */ EmojiView this$0;

        /* renamed from: $r8$lambda$OBwyNN8qtZHx4-jP1AjKi8GUF6E, reason: not valid java name */
        public static /* synthetic */ void m10520$r8$lambda$OBwyNN8qtZHx4jP1AjKi8GUF6E(GifSearchPreloader r0, String r1, String r2, boolean r3, String r4, TLObject r5, TLRPC.TL_error r6) {
            r0.lambda$preload$1(r1, r2, r3, r4, r5, r6);
        }

        /* renamed from: $r8$lambda$Y9L9x7-O6if4oubYr-_y1QsNNrs, reason: not valid java name */
        public static /* synthetic */ void m10521$r8$lambda$Y9L9x7O6if4oubYr_y1QsNNrs(GifSearchPreloader r0, String r1, String r2, boolean r3, String r4, TLObject r5) {
            r0.lambda$preload$0(r1, r2, r3, r4, r5);
        }

        private GifSearchPreloader(EmojiView r1) {
            this.this$0 = r1;
            this.loadingKeys = new ArrayList();
        }

        public /* synthetic */ GifSearchPreloader(EmojiView r1, EmojiViewIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$preload$0(String r1, String r2, boolean r3, String r4, TLObject r5) {
            processResponse(r1, r2, r3, r4, r5);
        }

        private /* synthetic */ void lambda$preload$1(String r1, String r2, boolean r3, String r4, TLObject r5, TLRPC.TL_error r6) {
            AndroidUtilities.runOnUIThread(new EmojiView$GifSearchPreloader$$ExternalSyntheticLambda1(this, r1, r2, r3, r4, r5));
        }

        private void preload(String r9, String r10, boolean r11) {
            String r7 = "gif_search_" + r9 + "_" + r10;
            if (r11 == true) goto L5;
        L7:
            EmojiView$GifSearchPreloader$$ExternalSyntheticLambda0 r2 = new EmojiView$GifSearchPreloader$$ExternalSyntheticLambda0(this, r9, r10, r11, r7);
            if (r11 == false) goto L11;
            this.loadingKeys.add(r7);
            MessagesStorage.getInstance(this.this$0.currentAccount).getBotCache(r7, r2);
            return;
        L11:
            MessagesController r8 = MessagesController.getInstance(this.this$0.currentAccount);
            TLObject r92 = r8.getUserOrChat(r8.gifSearchBot);
            if ((r92 instanceof TLRPC.User) == true) goto L14;
            return;
        L14:
            this.loadingKeys.add(r7);
            TLRPC.TL_messages_getInlineBotResults r102 = new TLRPC.TL_messages_getInlineBotResults();
            if (r9 != null) goto L17;
            String r112 = _UrlKt.FRAGMENT_ENCODE_SET;
        L18:
            r102.query = r112;
            r102.bot = r8.getInputUser((TLRPC.User) r92);
            r102.offset = r10;
            r102.peer = new TLRPC.TL_inputPeerEmpty();
            ConnectionsManager.getInstance(this.this$0.currentAccount).sendRequest(r102, r2, 2);
            return;
        L17:
            r112 = r9;
            goto L18
        L5:
            if (EmojiView.m10338$$Nest$fgetgifCache(this.this$0).containsKey(r7) == false) goto L7;
        }

        private void processResponse(String r9, String r10, boolean r11, String r12, TLObject r13) {
            this.loadingKeys.remove(r12);
            if (GifAdapter.m10507$$Nest$fgetlastSearchIsEmoji(EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)) == false) goto L9;
            if (GifAdapter.m10506$$Nest$fgetlastSearchImageString(EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0)).equals(r9) == false) goto L9;
            GifAdapter.m10516$$Nest$mprocessResponse(EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0), r9, r10, false, true, r11, r12, r13);
            return;
        L9:
            if (r11 == false) goto L17;
            if ((r13 instanceof TLRPC.messages_BotResults) == true) goto L13;
        L14:
            preload(r9, r10, false);
            return;
        L13:
            if (((TLRPC.messages_BotResults) r13).results.isEmpty() == true) goto L14;
        L17:
            if ((r13 instanceof TLRPC.messages_BotResults) == true) goto L19;
            return;
        L19:
            if (EmojiView.m10338$$Nest$fgetgifCache(this.this$0).containsKey(r12) == true) goto L23;
            EmojiView.m10338$$Nest$fgetgifCache(this.this$0).put(r12, (TLRPC.messages_BotResults) r13);
            return;
        }

        public boolean isLoading(String r1) {
            return this.loadingKeys.contains(r1);
        }

        public void preload(String r3) {
            preload(r3, _UrlKt.FRAGMENT_ENCODE_SET, true);
        }
    }

    public class ImageViewEmoji extends ImageView {
        ValueAnimator backAnimator;
        private ImageReceiver.BackgroundThreadDrawHolder[] backgroundThreadDrawHolder;
        public AnimatedEmojiDrawable drawable;
        public boolean ignoring;
        public ImageReceiver imageReceiver;
        private boolean isRecent;
        private EmojiPack pack;
        public int position;
        float pressedProgress;
        private AnimatedEmojiSpan span;
        final /* synthetic */ EmojiView this$0;

        /* renamed from: org.telegram.ui.Components.EmojiView$ImageViewEmoji$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ ImageViewEmoji this$1;

            public AnonymousClass1(ImageViewEmoji r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$1.backAnimator = null;
            }
        }

        /* renamed from: $r8$lambda$ri2om8IFp4RRHog-B4slAsmI-BQ, reason: not valid java name */
        public static /* synthetic */ void m10522$r8$lambda$ri2om8IFp4RRHogB4slAsmIBQ(ImageViewEmoji r0, ValueAnimator r1) {
            r0.lambda$setPressed$0(r1);
        }

        /* renamed from: -$$Nest$fgetbackgroundThreadDrawHolder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageReceiver.BackgroundThreadDrawHolder[] m10523$$Nest$fgetbackgroundThreadDrawHolder(ImageViewEmoji r0) {
            return r0.backgroundThreadDrawHolder;
        }

        /* renamed from: -$$Nest$fgetisRecent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10524$$Nest$fgetisRecent(ImageViewEmoji r0) {
            return r0.isRecent;
        }

        /* renamed from: -$$Nest$fgetpack, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EmojiPack m10525$$Nest$fgetpack(ImageViewEmoji r0) {
            return r0.pack;
        }

        /* renamed from: -$$Nest$fgetspan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiSpan m10526$$Nest$fgetspan(ImageViewEmoji r0) {
            return r0.span;
        }

        /* renamed from: -$$Nest$fputpack, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10527$$Nest$fputpack(ImageViewEmoji r0, EmojiPack r1) {
            r0.pack = r1;
        }

        public ImageViewEmoji(EmojiView r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            this.backgroundThreadDrawHolder = new ImageReceiver.BackgroundThreadDrawHolder[2];
            setScaleType(ImageView.ScaleType.CENTER);
            setBackground(Theme.createRadSelectorDrawable(EmojiView.m10432$$Nest$mgetThemedColor(r2, Theme.key_listSelector), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f)));
        }

        private /* synthetic */ void lambda$setPressed$0(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
        }

        public TLRPC.Document getDocument() {
            AnimatedEmojiSpan r0 = this.span;
            if (r0 != null) goto L7;
            return null;
        L7:
            return EmojiView.m10441$$Nest$mresolveEmojiDocument(this.this$0, r0.getDocumentId(), this.span.document, this.pack);
        }

        public AnimatedEmojiSpan getSpan() {
            return this.span;
        }

        @Override // android.widget.ImageView, android.view.View
        public void onDraw(Canvas r5) {
            if (isPressed() == false) goto L7;
            float r0 = this.pressedProgress;
            if (r0 == 1.0f) goto L7;
            float r02 = r0 + (Math.min(40.0f, 1000.0f / AndroidUtilities.screenRefreshRate) / 100.0f);
            this.pressedProgress = r02;
            this.pressedProgress = Utilities.clamp(r02, 1.0f, 0.0f);
            invalidate();
        L7:
            float r1 = ((1.0f - this.pressedProgress) * 0.2f) + 0.8f;
            r5.save();
            r5.scale(r1, r1, getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f);
            super.onDraw(r5);
            r5.restore();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setClassName("android.view.View");
        }

        @Override // android.widget.ImageView, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), View.MeasureSpec.getSize(r1));
        }

        public void setImageDrawable(Drawable r1, boolean r2) {
            super.setImageDrawable(r1);
            this.isRecent = r2;
        }

        @Override // android.view.View
        public void setPressed(boolean r4) {
            if (isPressed() == r4) goto L14;
            super.setPressed(r4);
            invalidate();
            if (r4 == false) goto L9;
            ValueAnimator r0 = this.backAnimator;
            if (r0 == null) goto L9;
            r0.removeAllListeners();
            this.backAnimator.cancel();
        L9:
            if (r4 == true) goto L15;
            float r42 = this.pressedProgress;
            if (r42 == 0.0f) goto L16;
            ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, 0.0f});
            this.backAnimator = r43;
            r43.addUpdateListener(new EmojiView$ImageViewEmoji$$ExternalSyntheticLambda0(this));
            this.backAnimator.addListener(new AnonymousClass1(this));
            this.backAnimator.setInterpolator(new OvershootInterpolator(5.0f));
            this.backAnimator.setDuration(350);
            this.backAnimator.start();
            return;
        L16:
            return;
        L15:
            return;
        }

        public void setSpan(AnimatedEmojiSpan r1) {
            this.span = r1;
        }
    }

    public class SearchField extends FrameLayout implements FactorAnimator.Target {
        private final BoolAnimator animatorShadowVisibility;
        private View backgroundView;
        private FrameLayout box;
        private StickerCategoriesListView categoriesListView;
        private ImageView clear;
        private Runnable delayedToggle;
        private FrameLayout inputBox;
        private View inputBoxGradient;
        private float inputBoxGradientAlpha;
        ValueAnimator inputBoxGradientAnimator;
        private boolean inputBoxShown;
        private boolean isprogress;
        private StickerCategoriesListView.EmojiCategory recent;
        private EditTextBoldCursor searchEditText;
        private ImageView searchImageView;
        private SearchStateDrawable searchStateDrawable;
        private View shadowView;
        final /* synthetic */ EmojiView this$0;
        private StickerCategoriesListView.EmojiCategory trending;
        private int type;

        /* renamed from: org.telegram.ui.Components.EmojiView$SearchField$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            Paint fadePaint;
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass1(SearchField r1, Context r2, EmojiView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r17) {
                if (EmojiView.m10374$$Nest$fgetshouldDrawBackground(this.this$1.this$0) == false) goto L5;
            L11:
                super.dispatchDraw(r17);
                return;
            L5:
                if (SearchField.m10536$$Nest$fgetinputBoxGradientAlpha(this.this$1) <= 0.0f) goto L11;
                if (this.fadePaint != null) goto L9;
                Paint r1 = new Paint();
                this.fadePaint = r1;
                r1.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(18.0f), 0.0f, new int[]{-1, 0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
                this.fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            L9:
                r17.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
                super.dispatchDraw(r17);
                this.fadePaint.setAlpha((int) (SearchField.m10536$$Nest$fgetinputBoxGradientAlpha(this.this$1) * 255.0f));
                r17.drawRect(0.0f, 0.0f, AndroidUtilities.dp(18.0f), getMeasuredHeight(), this.fadePaint);
                r17.restore();
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$SearchField$2, reason: invalid class name */
        public class AnonymousClass2 extends EditTextBoldCursor {
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ EmojiView val$this$0;
            final /* synthetic */ int val$type;

            public AnonymousClass2(SearchField r1, Context r2, EmojiView r3, int r4) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                this.val$type = r4;
                super(r2);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public boolean onTouchEvent(MotionEvent r4) {
                if (SearchField.m10538$$Nest$fgetsearchEditText(this.this$1).isEnabled() == true) goto L7;
                return super.onTouchEvent(r4);
            L7:
                if (r4.getAction() != 0) goto L16;
                if (EmojiView.m10307$$Nest$fgetdelegate(this.this$1.this$0).isSearchOpened() == true) goto L11;
                SearchField r0 = this.this$1;
                EmojiView.m10437$$Nest$mopenSearch(r0.this$0, r0);
            L11:
                EmojiViewDelegate r02 = EmojiView.m10307$$Nest$fgetdelegate(this.this$1.this$0);
                int r2 = 1;
                if (this.val$type != 1) goto L14;
                r2 = 2;
            L14:
                r02.onSearchOpenClose(r2);
                SearchField.m10538$$Nest$fgetsearchEditText(this.this$1).requestFocus();
                AndroidUtilities.showKeyboard(SearchField.m10538$$Nest$fgetsearchEditText(this.this$1));
            L16:
                return super.onTouchEvent(r4);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$SearchField$3, reason: invalid class name */
        public class AnonymousClass3 implements TextWatcher {
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass3(SearchField r1, EmojiView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r4) {
                SearchField.m10545$$Nest$mupdateButton(this.this$1);
                String r42 = SearchField.m10538$$Nest$fgetsearchEditText(this.this$1).getText().toString();
                this.this$1.search(r42, true);
                if (SearchField.m10535$$Nest$fgetcategoriesListView(this.this$1) == null) goto L5;
                SearchField.m10535$$Nest$fgetcategoriesListView(this.this$1).selectCategory(null);
                SearchField.m10535$$Nest$fgetcategoriesListView(this.this$1).updateCategoriesShown(TextUtils.isEmpty(r42), true);
            L5:
                SearchField.m10544$$Nest$mtoggleClear(this.this$1, !TextUtils.isEmpty(r42));
                if (SearchField.m10538$$Nest$fgetsearchEditText(this.this$1) == null) goto L8;
                SearchField.m10538$$Nest$fgetsearchEditText(this.this$1).clearAnimation();
                SearchField.m10538$$Nest$fgetsearchEditText(this.this$1).animate().translationX(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            L8:
                SearchField.m10542$$Nest$mshowInputBoxGradient(this.this$1, false);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$SearchField$4, reason: invalid class name */
        public class AnonymousClass4 extends CloseProgressDrawable2 {
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass4(SearchField r1, float r2, EmojiView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                setSide(AndroidUtilities.dp(7.0f));
            }

            @Override // org.telegram.ui.Components.CloseProgressDrawable2
            public int getCurrentColor() {
                return Theme.getColor(Theme.key_chat_emojiSearchIcon, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$1.this$0));
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$SearchField$5, reason: invalid class name */
        public class AnonymousClass5 extends StickerCategoriesListView {
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ EmojiView val$this$0;
            final /* synthetic */ int val$type;

            public AnonymousClass5(SearchField r1, Context r2, StickerCategoriesListView.EmojiCategory[] r3, int r4, Theme.ResourcesProvider r5, EmojiView r6, int r7) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                this.val$type = r7;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.StickerCategoriesListView
            public boolean isTabIconsAnimationEnabled(boolean r1) {
                return LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
            }

            @Override // org.telegram.ui.Components.StickerCategoriesListView
            public void selectCategory(int r4) {
                super.selectCategory(r4);
                SearchField r42 = this.this$1;
                EmojiView r0 = r42.this$0;
                boolean r1 = false;
                if (SearchField.m10535$$Nest$fgetcategoriesListView(r42).getSelectedCategory() != null) goto L5;
                boolean r43 = true;
            L6:
                EmojiView.m10448$$Nest$mshowBottomTab(r0, r43, true);
                if (this.val$type != 1) goto L15;
                if (EmojiView.m10319$$Nest$fgetemojiTabs(this.this$1.this$0) == null) goto L15;
                EmojiTabsStrip r44 = EmojiView.m10319$$Nest$fgetemojiTabs(this.this$1.this$0);
                if (SearchField.m10535$$Nest$fgetcategoriesListView(this.this$1).getSelectedCategory() != null) goto L13;
                r1 = true;
            L13:
                r44.showSelected(r1);
            L22:
                SearchField.m10545$$Nest$mupdateButton(this.this$1);
                return;
            L15:
                if (this.val$type != 0) goto L22;
                if (EmojiView.m10385$$Nest$fgetstickersTab(this.this$1.this$0) == null) goto L22;
                ScrollSlidingTabStrip r45 = EmojiView.m10385$$Nest$fgetstickersTab(this.this$1.this$0);
                if (SearchField.m10535$$Nest$fgetcategoriesListView(this.this$1).getSelectedCategory() != null) goto L21;
                r1 = true;
            L21:
                r45.showSelected(r1);
                goto L22
            L5:
                r43 = false;
                goto L6
            }
        }

        /* renamed from: $r8$lambda$6qe3-TCxWD_suhjN8mPqRssQU08, reason: not valid java name */
        public static /* synthetic */ void m10528$r8$lambda$6qe3TCxWD_suhjN8mPqRssQU08(SearchField r0, ValueAnimator r1) {
            r0.lambda$showInputBoxGradient$5(r1);
        }

        /* renamed from: $r8$lambda$E0eVs-GU_0oGJawBO0MncWDOeKY, reason: not valid java name */
        public static /* synthetic */ void m10529$r8$lambda$E0eVsGU_0oGJawBO0MncWDOeKY(SearchField r0, View r1) {
            r0.lambda$new$1(r1);
        }

        /* renamed from: $r8$lambda$JjrZE-rdbvf1SkMvTOptPpWRqeE, reason: not valid java name */
        public static /* synthetic */ void m10530$r8$lambda$JjrZErdbvf1SkMvTOptPpWRqeE(SearchField r0, StickerCategoriesListView.EmojiCategory r1) {
            r0.lambda$new$4(r1);
        }

        /* renamed from: $r8$lambda$S7sQDb2yijC6tSjDxRxAi2-H-o8, reason: not valid java name */
        public static /* synthetic */ void m10531$r8$lambda$S7sQDb2yijC6tSjDxRxAi2Ho8(SearchField r0, Integer r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ boolean $r8$lambda$U76NecSQ4KB6NEGcBBDVG4uS6iE(SearchField r0, View r1, MotionEvent r2) {
            return r0.lambda$new$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$YHGbuLqN9GsWSjqM8L4oYzbSW6k(SearchField r0) {
            r0.lambda$toggleClear$6();
        }

        /* renamed from: $r8$lambda$Yo63wsNLDe_F-5EkAbrUx3s-tYo, reason: not valid java name */
        public static /* synthetic */ void m10532$r8$lambda$Yo63wsNLDe_F5EkAbrUx3stYo(SearchField r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgetbackgroundView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m10533$$Nest$fgetbackgroundView(SearchField r0) {
            return r0.backgroundView;
        }

        /* renamed from: -$$Nest$fgetbox, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m10534$$Nest$fgetbox(SearchField r0) {
            return r0.box;
        }

        /* renamed from: -$$Nest$fgetcategoriesListView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickerCategoriesListView m10535$$Nest$fgetcategoriesListView(SearchField r0) {
            return r0.categoriesListView;
        }

        /* renamed from: -$$Nest$fgetinputBoxGradientAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10536$$Nest$fgetinputBoxGradientAlpha(SearchField r0) {
            return r0.inputBoxGradientAlpha;
        }

        /* renamed from: -$$Nest$fgetrecent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickerCategoriesListView.EmojiCategory m10537$$Nest$fgetrecent(SearchField r0) {
            return r0.recent;
        }

        /* renamed from: -$$Nest$fgetsearchEditText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextBoldCursor m10538$$Nest$fgetsearchEditText(SearchField r0) {
            return r0.searchEditText;
        }

        /* renamed from: -$$Nest$fgetsearchStateDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchStateDrawable m10539$$Nest$fgetsearchStateDrawable(SearchField r0) {
            return r0.searchStateDrawable;
        }

        /* renamed from: -$$Nest$fgetshadowView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m10540$$Nest$fgetshadowView(SearchField r0) {
            return r0.shadowView;
        }

        /* renamed from: -$$Nest$fgettrending, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickerCategoriesListView.EmojiCategory m10541$$Nest$fgettrending(SearchField r0) {
            return r0.trending;
        }

        /* renamed from: -$$Nest$mshowInputBoxGradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10542$$Nest$mshowInputBoxGradient(SearchField r0, boolean r1) {
            r0.showInputBoxGradient(r1);
        }

        /* renamed from: -$$Nest$mshowShadow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10543$$Nest$mshowShadow(SearchField r0, boolean r1, boolean r2) {
            r0.showShadow(r1, r2);
        }

        /* renamed from: -$$Nest$mtoggleClear, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10544$$Nest$mtoggleClear(SearchField r0, boolean r1) {
            r0.toggleClear(r1);
        }

        /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10545$$Nest$mupdateButton(SearchField r0) {
            r0.updateButton();
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public SearchField(EmojiView r20, Context r21, int r22) {
            this.this$0 = r20;
            super(r21);
            this.animatorShadowVisibility = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT, 200);
            this.inputBoxShown = false;
            this.type = r22;
            View r2 = new View(r21);
            this.shadowView = r2;
            r2.setVisibility(4);
            this.shadowView.setBackgroundColor(EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiPanelShadowLine));
            addView(this.shadowView, new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 83));
            this.backgroundView = new View(r21);
            if (EmojiView.m10374$$Nest$fgetshouldDrawBackground(r20) == false) goto L5;
            this.backgroundView.setBackgroundColor(EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiPanelBackground));
        L5:
            addView(this.backgroundView, new FrameLayout.LayoutParams(-1, EmojiView.m10373$$Nest$fgetsearchFieldHeight(r20)));
            FrameLayout r23 = new FrameLayout(r21);
            this.box = r23;
            int r4 = AndroidUtilities.dp(18.0f);
            if (EmojiView.m10349$$Nest$fgetglassDesign(r20) == false) goto L8;
            int r5 = EmojiView.m10430$$Nest$mgetGlassIconColor(r20, 0.06f);
        L9:
            r23.setBackground(Theme.createRoundRectDrawable(r4, r5));
            this.box.setClipToOutline(true);
            this.box.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(18.0f)));
            FrameLayout r24 = this.box;
            if (r22 != 2) goto L12;
            addView(r24, LayoutHelper.createFrame(-1, 36.0f, 119, 10.0f, 8.0f, 10.0f, 8.0f));
        L13:
            AnonymousClass1 r25 = new AnonymousClass1(this, r21, r20);
            this.inputBox = r25;
            this.box.addView(r25, LayoutHelper.createFrame(-1, 40.0f, 51, 38.0f, 0.0f, 0.0f, 0.0f));
            this.searchImageView = new ImageView(r21);
            SearchStateDrawable r26 = new SearchStateDrawable();
            this.searchStateDrawable = r26;
            r26.setIconState(0, false);
            SearchStateDrawable r27 = this.searchStateDrawable;
            if (EmojiView.m10349$$Nest$fgetglassDesign(r20) == false) goto L16;
            int r3 = EmojiView.m10430$$Nest$mgetGlassIconColor(r20, 0.4f);
        L17:
            r27.setColor(r3);
            ImageView r28 = this.searchImageView;
            ImageView.ScaleType r32 = ImageView.ScaleType.CENTER;
            r28.setScaleType(r32);
            this.searchImageView.setImageDrawable(this.searchStateDrawable);
            this.searchImageView.setOnClickListener(new EmojiView$SearchField$$ExternalSyntheticLambda0(this));
            this.box.addView(this.searchImageView, LayoutHelper.createFrame(36, 36, 51));
            AnonymousClass2 r29 = new AnonymousClass2(this, r21, r20, r22);
            this.searchEditText = r29;
            r29.setTextSize(1, 16.0f);
            EditTextBoldCursor r210 = this.searchEditText;
            if (EmojiView.m10349$$Nest$fgetglassDesign(r20) == false) goto L20;
            int r52 = EmojiView.m10430$$Nest$mgetGlassIconColor(r20, 0.45f);
        L21:
            r210.setHintTextColor(r52);
            EditTextBoldCursor r211 = this.searchEditText;
            if (EmojiView.m10349$$Nest$fgetglassDesign(r20) == false) goto L24;
            int r53 = EmojiView.m10430$$Nest$mgetGlassIconColor(r20, 0.8f);
        L25:
            r211.setTextColor(r53);
            this.searchEditText.setBackgroundDrawable(null);
            this.searchEditText.setPadding(0, 0, 0, 0);
            this.searchEditText.setMaxLines(1);
            this.searchEditText.setLines(1);
            this.searchEditText.setSingleLine(true);
            this.searchEditText.setImeOptions(268435459);
            this.searchEditText.setHint(LocaleController.getString(R.string.Search));
            this.searchEditText.setCursorColor(EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_featuredStickers_addedIcon));
            this.searchEditText.setCursorSize(AndroidUtilities.dp(20.0f));
            this.searchEditText.setCursorWidth(1.5f);
            this.searchEditText.setTranslationY(AndroidUtilities.dp(-2.0f));
            this.inputBox.addView(this.searchEditText, LayoutHelper.createFrame(-1, 40.0f, 51, 0.0f, 0.0f, 28.0f, 0.0f));
            this.searchEditText.addTextChangedListener(new AnonymousClass3(this, r20));
            if (EmojiView.m10374$$Nest$fgetshouldDrawBackground(r20) == false) goto L28;
            this.inputBoxGradient = new View(r21);
            Drawable r212 = r21.getResources().getDrawable(R.drawable.gradient_right).mutate();
            r212.setColorFilter(new PorterDuffColorFilter(Theme.blendOver(EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiPanelBackground), EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiSearchBackground)), PorterDuff.Mode.MULTIPLY));
            this.inputBoxGradient.setBackground(r212);
            this.inputBoxGradient.setAlpha(0.0f);
            this.inputBox.addView(this.inputBoxGradient, LayoutHelper.createFrame(18, -1, 3));
        L28:
            ImageView r213 = new ImageView(r21);
            this.clear = r213;
            r213.setScaleType(r32);
            this.clear.setImageDrawable(new AnonymousClass4(this, 1.25f, r20));
            this.clear.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, EmojiView.m10370$$Nest$fgetresourcesProvider(r20)), 1, AndroidUtilities.dp(15.0f)));
            this.clear.setAlpha(0.0f);
            this.clear.setOnClickListener(new EmojiView$SearchField$$ExternalSyntheticLambda1(this));
            this.box.addView(this.clear, LayoutHelper.createFrame(36, 36, 53));
            if (r22 != 1) goto L39;
            if (EmojiView.m10288$$Nest$fgetallowAnimatedEmoji(r20) == true) goto L33;
            return;
        L33:
            if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L39;
            if (EmojiView.m10289$$Nest$fgetallowEmojisForNonPremium(r20) == true) goto L39;
            return;
        L39:
            if (r22 != 0) goto L41;
            int r42 = 3;
        L42:
            AnonymousClass5 r0 = new AnonymousClass5(this, r21, null, r42, EmojiView.m10370$$Nest$fgetresourcesProvider(r20), r20, r22);
            this.categoriesListView = r0;
            r0.isGlassDesign = EmojiView.m10349$$Nest$fgetglassDesign(r20);
            this.categoriesListView.setDontOccupyWidth(((int) this.searchEditText.getPaint().measureText(this.searchEditText.getHint() + _UrlKt.FRAGMENT_ENCODE_SET)) + AndroidUtilities.dp(16.0f));
            if (EmojiView.m10374$$Nest$fgetshouldDrawBackground(r20) == false) goto L45;
            this.categoriesListView.setBackgroundColor(Theme.blendOver(EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiPanelBackground), EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiSearchBackground)));
        L45:
            this.categoriesListView.setOnScrollIntoOccupiedWidth(new EmojiView$SearchField$$ExternalSyntheticLambda2(this));
            this.categoriesListView.setOnTouchListener(new EmojiView$SearchField$$ExternalSyntheticLambda3(this));
            this.categoriesListView.setOnCategoryClick(new EmojiView$SearchField$$ExternalSyntheticLambda4(this));
            this.box.addView(this.categoriesListView, LayoutHelper.createFrame(-1, 36.0f, 51, 36.0f, 0.0f, 0.0f, 0.0f));
            return;
        L41:
            r42 = 0;
            goto L42
        L24:
            r53 = EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_windowBackgroundWhiteBlackText);
            goto L25
        L20:
            r52 = EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiSearchIcon);
            goto L21
        L16:
            r3 = EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiSearchIcon);
            goto L17
        L12:
            addView(r24, LayoutHelper.createFrame(-1, 36.0f, 119, 10.0f, 6.0f, 10.0f, 8.0f));
            goto L13
        L8:
            r5 = EmojiView.m10432$$Nest$mgetThemedColor(r20, Theme.key_chat_emojiSearchBackground);
            goto L9
        }

        private /* synthetic */ void lambda$new$0(View r4) {
            if (this.searchStateDrawable.getIconState() != 1) goto L12;
            this.searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            search(null, false);
            StickerCategoriesListView r2 = this.categoriesListView;
            if (r2 == null) goto L7;
            r2.scrollToStart();
            this.categoriesListView.selectCategory(null);
            this.categoriesListView.updateCategoriesShown(true, true);
        L7:
            toggleClear(false);
            EditTextBoldCursor r42 = this.searchEditText;
            if (r42 == null) goto L10;
            r42.clearAnimation();
            this.searchEditText.animate().translationX(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L10:
            showInputBoxGradient(false);
            return;
        }

        private /* synthetic */ void lambda$new$1(View r3) {
            this.searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            search(null, false);
            StickerCategoriesListView r1 = this.categoriesListView;
            if (r1 == null) goto L5;
            r1.scrollToStart();
            this.categoriesListView.selectCategory(null);
            this.categoriesListView.updateCategoriesShown(true, true);
        L5:
            toggleClear(false);
            EditTextBoldCursor r32 = this.searchEditText;
            if (r32 == null) goto L8;
            r32.clearAnimation();
            this.searchEditText.animate().translationX(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L8:
            showInputBoxGradient(false);
        }

        private /* synthetic */ void lambda$new$2(Integer r4) {
            boolean r2 = false;
            this.searchEditText.setTranslationX(-Math.max(0, r4.intValue()));
            if (r4.intValue() <= 0) goto L5;
            r2 = true;
        L5:
            showInputBoxGradient(r2);
            updateButton();
        }

        private /* synthetic */ boolean lambda$new$3(View r3, MotionEvent r4) {
            if (r4.getAction() != 0) goto L6;
            EmojiView.m10410$$Nest$fputignorePagerScroll(this.this$0, true);
        L10:
            return false;
        L6:
            if (r4.getAction() != 1) goto L8;
        L9:
            EmojiView.m10410$$Nest$fputignorePagerScroll(this.this$0, false);
            goto L10
        L8:
            if (r4.getAction() != 3) goto L10;
            goto L9
        }

        private /* synthetic */ void lambda$new$4(StickerCategoriesListView.EmojiCategory r4) {
            if (r4 != this.recent) goto L7;
            showInputBoxGradient(false);
            this.categoriesListView.selectCategory(this.recent);
            EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
            return;
        L7:
            if (r4 != this.trending) goto L13;
            showInputBoxGradient(false);
            EmojiView.m10345$$Nest$fgetgifSearchField(this.this$0).searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            EmojiView.m10342$$Nest$fgetgifLayoutManager(this.this$0).scrollToPositionWithOffset(GifAdapter.m10514$$Nest$fgettrendingSectionItem(EmojiView.m10337$$Nest$fgetgifAdapter(this.this$0)), -AndroidUtilities.dp(4.0f));
            this.categoriesListView.selectCategory(this.trending);
            ArrayList<String> r42 = MessagesController.getInstance(this.this$0.currentAccount).gifSearchEmojies;
            if (r42.isEmpty() == true) goto L18;
            EmojiView.m10346$$Nest$fgetgifSearchPreloader(this.this$0).preload(r42.get(0));
            return;
        L18:
            return;
        L13:
            if (this.categoriesListView.getSelectedCategory() != r4) goto L16;
            search(null, false);
            this.categoriesListView.selectCategory(null);
            return;
        L16:
            search(r4.emojis, false);
            this.categoriesListView.selectCategory(r4);
        }

        private /* synthetic */ void lambda$showInputBoxGradient$5(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.inputBoxGradientAlpha = r22;
            View r0 = this.inputBoxGradient;
            if (r0 == null) goto L6;
            r0.setAlpha(r22);
            return;
        L6:
            FrameLayout r1 = this.inputBox;
            if (r1 == null) goto L10;
            r1.invalidate();
            return;
        }

        private /* synthetic */ void lambda$toggleClear$6() {
            AndroidUtilities.updateViewShow(this.clear, true);
        }

        private void showInputBoxGradient(boolean r4) {
            if (r4 != this.inputBoxShown) goto L5;
            return;
        L5:
            this.inputBoxShown = r4;
            ValueAnimator r0 = this.inputBoxGradientAnimator;
            if (r0 == null) goto L8;
            r0.cancel();
        L8:
            float r02 = this.inputBoxGradientAlpha;
            if (r4 == false) goto L11;
            float r42 = 1.0f;
        L12:
            ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r02, r42});
            this.inputBoxGradientAnimator = r43;
            r43.addUpdateListener(new EmojiView$SearchField$$ExternalSyntheticLambda6(this));
            this.inputBoxGradientAnimator.setDuration(120);
            this.inputBoxGradientAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.inputBoxGradientAnimator.start();
            return;
        L11:
            r42 = 0.0f;
            goto L12
        }

        private void showShadow(boolean r1, boolean r2) {
            this.animatorShadowVisibility.setValue(r1, r2);
        }

        private void toggleClear(boolean r3) {
            Runnable r0 = this.delayedToggle;
            if (r3 == false) goto L7;
            if (r0 != null) goto L11;
            EmojiView$SearchField$$ExternalSyntheticLambda5 r32 = new EmojiView$SearchField$$ExternalSyntheticLambda5(this);
            this.delayedToggle = r32;
            AndroidUtilities.runOnUIThread(r32, 340);
            return;
        L11:
            return;
        L7:
            if (r0 == null) goto L9;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.delayedToggle = null;
        L9:
            AndroidUtilities.updateViewShow(this.clear, false);
        }

        private void updateButton() {
            updateButton(false);
        }

        private void updateButton(boolean r3) {
            if (isInProgress() == false) goto L14;
            if (this.searchEditText.length() != 0) goto L10;
            StickerCategoriesListView r0 = this.categoriesListView;
            if (r0 == null) goto L14;
            if (r0.getSelectedCategory() == null) goto L14;
        L10:
            if (r3 == true) goto L14;
            return;
        L14:
            if (this.searchEditText.length() > 0) goto L25;
            StickerCategoriesListView r32 = this.categoriesListView;
            if (r32 != null) goto L18;
        L24:
            int r33 = 0;
        L26:
            this.searchStateDrawable.setIconState(r33);
            this.isprogress = false;
            return;
        L18:
            if (r32.isCategoriesShown() == false) goto L24;
            if (this.categoriesListView.isScrolledIntoOccupiedWidth() == true) goto L25;
            if (this.categoriesListView.getSelectedCategory() == null) goto L24;
        L25:
            r33 = 1;
            goto L26
        }

        public void hideKeyboard() {
            AndroidUtilities.hideKeyboard(this.searchEditText);
        }

        public boolean isCategorySelected() {
            StickerCategoriesListView r0 = this.categoriesListView;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.getSelectedCategory() == null) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isInProgress() {
            return this.isprogress;
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
            if (r1 != 0) goto L9;
            this.shadowView.setAlpha(r2);
            View r0 = this.shadowView;
            if (r2 <= 0.0f) goto L6;
            int r12 = 0;
        L7:
            r0.setVisibility(r12);
            return;
        L6:
            r12 = 4;
            goto L7
        }

        public void search(String r3, boolean r4) {
            int r0 = this.type;
            if (r0 != 0) goto L7;
            EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$0).search(r3, r4);
            return;
        L7:
            if (r0 != 1) goto L11;
            EmojiView.m10316$$Nest$fgetemojiSearchAdapter(this.this$0).search(r3, r4);
            return;
        L11:
            if (r0 != 2) goto L14;
            EmojiView.m10344$$Nest$fgetgifSearchAdapter(this.this$0).search(r3, r4);
            return;
        }

        public void showProgress(boolean r1) {
            this.isprogress = r1;
            if (r1 == false) goto L6;
            this.searchStateDrawable.setIconState(2);
            return;
        L6:
            updateButton(true);
        }
    }

    public interface SearchRunnable extends Runnable {
        boolean isCompleted();

        boolean isLoading();

        void loadNext();
    }

    public class StickersGridAdapter extends RecyclerListView.SelectionAdapter {
        private SparseArray<Object> cache;
        private SparseArray<Object> cacheParents;
        private Context context;
        private HashMap<Object, Integer> packStartPosition;
        private SparseIntArray positionToRow;
        private SparseArray<Object> rowStartPack;
        private int stickersPerRow;
        final /* synthetic */ EmojiView this$0;
        private int totalItems;

        /* renamed from: org.telegram.ui.Components.EmojiView$StickersGridAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends StickerEmojiCell {
            final /* synthetic */ StickersGridAdapter this$1;

            public AnonymousClass1(StickersGridAdapter r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(82.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$StickersGridAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.ItemDecoration {
            final /* synthetic */ StickersGridAdapter this$1;

            public AnonymousClass2(StickersGridAdapter r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
                r1.right = AndroidUtilities.dp(2.0f);
            }
        }

        public static /* synthetic */ void $r8$lambda$2xO3JjiiMQdReWEoXfY5F2qV0ik(StickersGridAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$2(r1);
        }

        /* renamed from: $r8$lambda$KW1wSJbSF2ar0_2SyPUQ-Eb3-r4, reason: not valid java name */
        public static /* synthetic */ void m10546$r8$lambda$KW1wSJbSF2ar0_2SyPUQEb3r4(StickersGridAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$5(r1);
        }

        /* renamed from: $r8$lambda$R-7R9cwR1sYuihoT0LOCXt6BUvw, reason: not valid java name */
        public static /* synthetic */ void m10547$r8$lambda$R7R9cwR1sYuihoT0LOCXt6BUvw(StickersGridAdapter r0, TLRPC.TL_messages_stickerSet r1, View r2) {
            r0.lambda$onBindViewHolder$6(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$VpgO6xlreVQhP2ZlV3IfGa5tgH4(StickersGridAdapter r0, View r1, int r2) {
            r0.lambda$onCreateViewHolder$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ZnB4spfzdgLx6Midx_ZLI2dmg1Y(StickersGridAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$cjKhAwel2eSuiVtIdCpthhEPbLg(StickersGridAdapter r0, TLRPC.TL_messages_stickerSet r1, View r2) {
            r0.lambda$onBindViewHolder$7(r1, r2);
        }

        /* renamed from: $r8$lambda$eSB0-R83OKx2Dqz72dtM5m3KcCE, reason: not valid java name */
        public static /* synthetic */ void m10548$r8$lambda$eSB0R83OKx2Dqz72dtM5m3KcCE(StickersGridAdapter r0, StickerSetNameCell r1, View r2) {
            r0.lambda$onCreateViewHolder$1(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$oiWzYkRGnBGZ02dhZtHjtkx1JrY(StickersGridAdapter r0, AlertDialog r1, int r2) {
            r0.lambda$onCreateViewHolder$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetcache, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m10549$$Nest$fgetcache(StickersGridAdapter r0) {
            return r0.cache;
        }

        /* renamed from: -$$Nest$fgetstickersPerRow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10550$$Nest$fgetstickersPerRow(StickersGridAdapter r0) {
            return r0.stickersPerRow;
        }

        /* renamed from: -$$Nest$fgettotalItems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10551$$Nest$fgettotalItems(StickersGridAdapter r0) {
            return r0.totalItems;
        }

        public StickersGridAdapter(EmojiView r1, Context r2) {
            this.this$0 = r1;
            this.rowStartPack = new SparseArray();
            this.packStartPosition = new HashMap();
            this.cache = new SparseArray();
            this.cacheParents = new SparseArray();
            this.positionToRow = new SparseIntArray();
            this.context = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$6(TLRPC.TL_messages_stickerSet r2, View r3) {
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onShowStickerSet(r2.set, null, true);
        }

        private /* synthetic */ void lambda$onBindViewHolder$7(TLRPC.TL_messages_stickerSet r2, View r3) {
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onShowStickerSet(r2.set, null, false);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(AlertDialog r1, int r2) {
            MediaDataController.getInstance(this.this$0.currentAccount).clearRecentStickers();
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(StickerSetNameCell r3, View r4) {
            if (EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0).indexOfChild(r3) == (-1)) goto L34;
            RecyclerView.ViewHolder r32 = EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0).getChildViewHolder(r3);
            if (r32 != null) goto L8;
            return;
        L8:
            if (r32.getAdapterPosition() != EmojiView.m10352$$Nest$fgetgroupStickerPackPosition(this.this$0)) goto L24;
            TLRPC.TL_messages_stickerSet r33 = EmojiView.m10353$$Nest$fgetgroupStickerSet(this.this$0);
            EmojiView r42 = this.this$0;
            if (r33 != null) goto L12;
            SharedPreferences.Editor r34 = MessagesController.getEmojiSettings(r42.currentAccount).edit();
            String r43 = "group_hide_stickers_" + EmojiView.m10358$$Nest$fgetinfo(this.this$0).id;
            if (EmojiView.m10358$$Nest$fgetinfo(this.this$0).stickerset == null) goto L18;
            long r0 = EmojiView.m10358$$Nest$fgetinfo(this.this$0).stickerset.id;
        L19:
            r34.putLong(r43, r0).apply();
            EmojiView.m10457$$Nest$mupdateStickerTabs(this.this$0, false);
            if (EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0) == null) goto L31;
            EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0).notifyDataSetChanged();
            return;
        L31:
            return;
        L18:
            r0 = 0;
            goto L19
        L12:
            if (EmojiView.m10307$$Nest$fgetdelegate(r42) == null) goto L30;
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickersGroupClick(EmojiView.m10358$$Nest$fgetinfo(this.this$0).id);
            return;
        L30:
            return;
        L24:
            if (this.cache.get(r32.getAdapterPosition()) != EmojiView.m10367$$Nest$fgetrecentStickers(this.this$0)) goto L32;
            AlertDialog r35 = new AlertDialog.Builder(this.context, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0)).setTitle(LocaleController.getString(R.string.ClearRecentStickersAlertTitle)).setMessage(LocaleController.getString(R.string.ClearRecentStickersAlertMessage)).setPositiveButton(LocaleController.getString(R.string.ClearButton), new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda7(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
            r35.show();
            TextView r36 = (TextView) r35.getButton(-1);
            if (r36 == null) goto L33;
            r36.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$0, Theme.key_text_RedBold));
            return;
        L33:
            return;
        L32:
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$2(View r3) {
            if (EmojiView.m10307$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickersGroupClick(EmojiView.m10358$$Nest$fgetinfo(this.this$0).id);
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$3(View r5) {
            ArrayList<TLRPC.StickerSetCovered> r52 = MediaDataController.getInstance(this.this$0.currentAccount).getFeaturedStickerSets();
            if (r52.isEmpty() == true) goto L9;
            MessagesController.getEmojiSettings(this.this$0.currentAccount).edit().putLong("featured_hidden", r52.get(0).set.id).apply();
            if (EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0) == null) goto L7;
            EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0).notifyItemRangeRemoved(1, 2);
        L7:
            EmojiView.m10457$$Nest$mupdateStickerTabs(this.this$0, false);
            return;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$4(View r1, int r2) {
            EmojiView.m10438$$Nest$mopenTrendingStickers(this.this$0, (TLRPC.StickerSetCovered) r1.getTag());
        }

        private /* synthetic */ void lambda$onCreateViewHolder$5(View r1) {
            if ((EmojiView.m10335$$Nest$fgetfragment(this.this$0) instanceof ChatActivity) == false) goto L6;
            ((ChatActivity) EmojiView.m10335$$Nest$fgetfragment(this.this$0)).openAttachMenuForCreatingSticker();
            return;
        }

        private void refreshItems() {
            updateItems();
            if (EmojiView.m10336$$Nest$fgetfrozen(this.this$0) == true) goto L6;
            EmojiView.m10415$$Nest$fputstickerAdapterBuilt(this.this$0, true);
            return;
        }

        private void updateItems() {
            if (EmojiView.m10336$$Nest$fgetfrozen(this.this$0) == true) goto L74;
            int r1 = this.this$0.getMeasuredWidth();
            if (r1 != 0) goto L8;
            r1 = AndroidUtilities.displaySize.x;
        L8:
            this.stickersPerRow = r1 / AndroidUtilities.dp(72.0f);
            EmojiView.m10382$$Nest$fgetstickersLayoutManager(this.this$0).setSpanCount(this.stickersPerRow);
            this.rowStartPack.clear();
            this.packStartPosition.clear();
            this.positionToRow.clear();
            this.cache.clear();
            int r12 = 0;
            this.totalItems = 0;
            ArrayList r2 = EmojiView.m10376$$Nest$fgetstickerSets(this.this$0);
            int r3 = -5;
            int r5 = 0;
            boolean r6 = false;
            int r4 = -5;
        L10:
            if (r4 >= r2.size()) goto L91;
            if (r4 != r3) goto L14;
            SparseArray<Object> r7 = this.cache;
            int r8 = this.totalItems;
            this.totalItems = r8 + 1;
            r7.put(r8, "search");
            r5 = r5 + 1;
        L73:
            r4 = r4 + 1;
            r12 = 0;
            r3 = -5;
            goto L10
        L14:
            if (r4 != (-4)) goto L20;
            MediaDataController r72 = MediaDataController.getInstance(this.this$0.currentAccount);
            SharedPreferences r82 = MessagesController.getEmojiSettings(this.this$0.currentAccount);
            ArrayList<TLRPC.StickerSetCovered> r73 = r72.getFeaturedStickerSets();
            if (EmojiView.m10331$$Nest$fgetfeaturedStickerSets(this.this$0).isEmpty() == true) goto L73;
            if (r82.getLong("featured_hidden", 0) == r73.get(r12).set.id) goto L73;
            SparseArray<Object> r74 = this.cache;
            int r83 = this.totalItems;
            this.totalItems = r83 + 1;
            r74.put(r83, "trend1");
            SparseArray<Object> r75 = this.cache;
            int r84 = this.totalItems;
            this.totalItems = r84 + 1;
            r75.put(r84, "trend2");
            r5 = r5 + 2;
            goto L73
        L20:
            TLRPC.TL_messages_stickerSet r122 = null;
            boolean r13 = true;
            if (r4 != (-3)) goto L23;
            ArrayList<TLRPC.Document> r76 = EmojiView.m10329$$Nest$fgetfavouriteStickers(this.this$0);
            this.packStartPosition.put("fav", Integer.valueOf(this.totalItems));
            Object r14 = "fav";
        L39:
            if (r4 != EmojiView.m10351$$Nest$fgetgroupStickerPackNum(this.this$0)) goto L44;
            EmojiView.m10409$$Nest$fputgroupStickerPackPosition(this.this$0, this.totalItems);
            if (r76.isEmpty() == false) goto L44;
            this.rowStartPack.put(r5, r122);
            int r85 = r5 + 1;
            this.positionToRow.put(this.totalItems, r5);
            this.rowStartPack.put(r85, r122);
            r5 = r5 + 2;
            this.positionToRow.put(this.totalItems + 1, r85);
            SparseArray<Object> r15 = this.cache;
            int r77 = this.totalItems;
            this.totalItems = r77 + 1;
            r15.put(r77, r122);
            SparseArray<Object> r16 = this.cache;
            int r78 = this.totalItems;
            this.totalItems = r78 + 1;
            r16.put(r78, "group");
        L44:
            if (r76.isEmpty() == true) goto L73;
            int r142 = (int) Math.ceil(r76.size() / this.stickersPerRow);
            SparseArray<Object> r152 = this.cache;
            if (r122 == null) goto L49;
            r152.put(this.totalItems, r122);
        L50:
            this.positionToRow.put(this.totalItems, r5);
            int r32 = 0;
        L52:
            if (r32 >= r76.size()) goto L58;
            int r153 = r32 + 1;
            boolean r162 = r13;
            int r132 = this.totalItems + r153;
            this.cache.put(r132, r76.get(r32));
            SparseArray<Object> r86 = this.cacheParents;
            if (r122 == null) goto L56;
            r86.put(r132, r122);
        L57:
            this.positionToRow.put(this.totalItems + r153, (r5 + 1) + (r32 / this.stickersPerRow));
            r32 = r153;
            r13 = r162;
            r14 = r14;
            goto L52
        L56:
            r86.put(r132, r14);
            goto L57
        L58:
            int r17 = 0;
        L59:
            int r33 = r142 + 1;
            if (r17 >= r33) goto L72;
            if (r122 == null) goto L65;
            this.rowStartPack.put(r5 + r17, r122);
        L71:
            r17 = r17 + 1;
            goto L59
        L65:
            if (r4 != (-1)) goto L67;
            this.rowStartPack.put(r5 + r17, "premium");
            goto L71
        L67:
            SparseArray<Object> r34 = this.rowStartPack;
            if (r4 != (-2)) goto L70;
            r34.put(r5 + r17, "recent");
            goto L71
        L70:
            r34.put(r5 + r17, "fav");
            goto L71
        L72:
            this.totalItems += (r142 * this.stickersPerRow) + 1;
            r5 = r5 + r33;
            goto L73
        L49:
            r152.put(this.totalItems, r76);
            goto L50
        L23:
            if (r4 != (-2)) goto L30;
            r76 = EmojiView.m10367$$Nest$fgetrecentStickers(this.this$0);
            if (r76.isEmpty() == false) goto L27;
        L29:
            this.packStartPosition.put("recent", Integer.valueOf(this.totalItems));
            r14 = "recent";
            goto L39
        L27:
            if (EmojiView.m10308$$Nest$fgetdisableStickerEditor(this.this$0) == true) goto L29;
            r6 = true;
            goto L29
        L30:
            if (r4 == (-1)) goto L73;
            TLRPC.TL_messages_stickerSet r79 = (TLRPC.TL_messages_stickerSet) r2.get(r4);
            ArrayList<TLRPC.Document> r143 = r79.documents;
            if (r6 == false) goto L35;
        L37:
            this.packStartPosition.put(r79, Integer.valueOf(this.totalItems));
            r14 = null;
            r122 = r79;
            r76 = r143;
            goto L39
        L35:
            if (EmojiView.m10308$$Nest$fgetdisableStickerEditor(this.this$0) == true) goto L37;
            ArrayList<TLRPC.Document> r62 = new ArrayList(r143);
            r62.add(r12, new TLRPC.TL_documentEmpty());
            r143 = r62;
            r6 = true;
            goto L37
        L91:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.totalItems;
            if (r0 != 0) goto L5;
            return 0;
        L5:
            return r0 + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L5;
            return 4;
        L5:
            Object r0 = this.cache.get(r1);
            if (r0 != null) goto L8;
            return 1;
        L8:
            if ((r0 instanceof TLRPC.Document) == false) goto L16;
            if ((r0 instanceof TLRPC.TL_documentEmpty) == false) goto L13;
            return 7;
        L13:
            return 0;
        L16:
            if ((r0 instanceof String) == true) goto L18;
            return 2;
        L18:
            if ("trend1".equals(r0) == false) goto L22;
            return 5;
        L22:
            if ("trend2".equals(r0) == false) goto L25;
            return 6;
        L25:
            return 3;
        }

        public int getPositionForPack(Object r1) {
            Integer r0 = this.packStartPosition.get(r1);
            if (r0 != null) goto L7;
            return -1;
        L7:
            return r0.intValue();
        }

        public int getTabForPosition(int r4) {
            Object r0 = this.cache.get(r4);
            if ("search".equals(r0) == false) goto L5;
        L36:
            int r42 = EmojiView.m10328$$Nest$fgetfavTabNum(this.this$0);
            EmojiView r02 = this.this$0;
            if (r42 < 0) goto L41;
            return EmojiView.m10328$$Nest$fgetfavTabNum(r02);
        L41:
            if (EmojiView.m10368$$Nest$fgetrecentTabNum(r02) >= 0) goto L43;
            return 0;
        L43:
            return EmojiView.m10368$$Nest$fgetrecentTabNum(this.this$0);
        L5:
            if ("trend1".equals(r0) == true) goto L36;
            if ("trend2".equals(r0) == true) goto L36;
            if (r4 != 0) goto L13;
            r4 = 1;
        L13:
            if (this.stickersPerRow != 0) goto L18;
            int r1 = this.this$0.getMeasuredWidth();
            if (r1 != 0) goto L17;
            r1 = AndroidUtilities.displaySize.x;
        L17:
            this.stickersPerRow = r1 / AndroidUtilities.dp(72.0f);
        L18:
            int r43 = this.positionToRow.get(r4, Integer.MIN_VALUE);
            if (r43 != Integer.MIN_VALUE) goto L23;
            int r44 = EmojiView.m10376$$Nest$fgetstickerSets(this.this$0).size() - 1;
            int r3 = EmojiView.m10387$$Nest$fgetstickersTabOffset(this.this$0);
        L22:
            return r44 + r3;
        L23:
            Object r45 = this.rowStartPack.get(r43);
            if ((r45 instanceof String) == true) goto L26;
            r44 = EmojiView.m10376$$Nest$fgetstickerSets(this.this$0).indexOf((TLRPC.TL_messages_stickerSet) r45);
            r3 = EmojiView.m10387$$Nest$fgetstickersTabOffset(this.this$0);
            goto L22
        L26:
            if ("premium".equals(r45) == true) goto L28;
            boolean r46 = "recent".equals(r45);
            EmojiView r32 = this.this$0;
            if (r46 == false) goto L34;
            return EmojiView.m10368$$Nest$fgetrecentTabNum(r32);
        L34:
            return EmojiView.m10328$$Nest$fgetfavTabNum(r32);
        L28:
            return EmojiView.m10365$$Nest$fgetpremiumTabNum(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return r1.itemView instanceof RecyclerListView;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            refreshItems();
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyItemRangeRemoved(int r1, int r2) {
            refreshItems();
            super.notifyItemRangeRemoved(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            int r0 = r7.getItemViewType();
            int r1 = 0;
            boolean r12 = false;
            if (r0 == 0) goto L98;
            ArrayList<TLRPC.Document> r2 = null;
            TLRPC.Chat r22 = null;
            int r3 = 1;
            if (r0 != 1) goto L7;
            EmptyCell r72 = (EmptyCell) r7.itemView;
            if (r8 != this.totalItems) goto L96;
            int r82 = this.positionToRow.get(r8 - 1, Integer.MIN_VALUE);
            if (r82 != Integer.MIN_VALUE) goto L75;
            r72.setHeight(1);
            return;
        L75:
            Object r83 = this.rowStartPack.get(r82);
            if ((r83 instanceof TLRPC.TL_messages_stickerSet) == false) goto L79;
            r2 = ((TLRPC.TL_messages_stickerSet) r83).documents;
        L84:
            if (r2 != null) goto L88;
            r72.setHeight(1);
            return;
        L88:
            if (r2.isEmpty() == false) goto L91;
            r72.setHeight(AndroidUtilities.dp(8.0f));
            return;
        L91:
            int r84 = EmojiView.m10363$$Nest$fgetpager(this.this$0).getHeight() - (((int) Math.ceil(r2.size() / this.stickersPerRow)) * AndroidUtilities.dp(82.0f));
            if (r84 <= 0) goto L94;
            r3 = r84;
        L94:
            r72.setHeight(r3);
            return;
        L79:
            if ((r83 instanceof String) == false) goto L84;
            boolean r85 = "recent".equals(r83);
            EmojiView r02 = this.this$0;
            if (r85 == false) goto L83;
            r2 = EmojiView.m10367$$Nest$fgetrecentStickers(r02);
            goto L84
        L83:
            r2 = EmojiView.m10329$$Nest$fgetfavouriteStickers(r02);
            goto L84
        L96:
            r72.setHeight(AndroidUtilities.dp(82.0f));
            return;
        L7:
            if (r0 != 2) goto L9;
            StickerSetNameCell r73 = (StickerSetNameCell) r7.itemView;
            r73.setHeaderOnClick(null);
            if (r8 == EmojiView.m10352$$Nest$fgetgroupStickerPackPosition(this.this$0)) goto L28;
            Object r86 = this.cache.get(r8);
            if ((r86 instanceof TLRPC.TL_messages_stickerSet) == false) goto L58;
            TLRPC.TL_messages_stickerSet r87 = (TLRPC.TL_messages_stickerSet) r86;
            TLRPC.StickerSet r03 = r87.set;
            if (r03 == null) goto L100;
            r73.setText(r03.title, 0);
            if (r87.set.creator == true) goto L53;
        L55:
            r73.setHeaderOnClick(new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda6(this, r87));
            return;
        L53:
            if (EmojiView.m10308$$Nest$fgetdisableStickerEditor(this.this$0) == true) goto L55;
            r73.setEdit(new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda5(this, r87));
            goto L55
        L100:
            return;
        L58:
            if (r86 != EmojiView.m10367$$Nest$fgetrecentStickers(this.this$0)) goto L62;
            r73.setText(LocaleController.getString(R.string.RecentStickers), R.drawable.msg_close, LocaleController.getString(R.string.ClearRecentStickersAlertTitle));
            return;
        L62:
            if (r86 != EmojiView.m10329$$Nest$fgetfavouriteStickers(this.this$0)) goto L66;
            r73.setText(LocaleController.getString(R.string.FavoriteStickers), 0);
            return;
        L66:
            if (r86 != EmojiView.m10364$$Nest$fgetpremiumStickers(this.this$0)) goto L101;
            r73.setText(LocaleController.getString(R.string.PremiumStickers), 0);
            return;
        L101:
            return;
        L28:
            if (EmojiView.m10354$$Nest$fgetgroupStickersHidden(this.this$0) == false) goto L33;
            if (EmojiView.m10353$$Nest$fgetgroupStickerSet(this.this$0) != null) goto L33;
        L38:
            if (EmojiView.m10358$$Nest$fgetinfo(this.this$0) == null) goto L40;
            r22 = MessagesController.getInstance(this.this$0.currentAccount).getChat(Long.valueOf(EmojiView.m10358$$Nest$fgetinfo(this.this$0).id));
        L40:
            int r6 = R.string.CurrentGroupStickers;
            if (r22 == null) goto L43;
            String r88 = r22.title;
        L44:
            r73.setText(LocaleController.formatString("CurrentGroupStickers", r6, new Object[]{r88}), r1);
            return;
        L43:
            r88 = "Group Stickers";
        L33:
            if (EmojiView.m10353$$Nest$fgetgroupStickerSet(this.this$0) == null) goto L36;
            int r89 = R.drawable.msg_mini_customize;
        L35:
            r1 = r89;
            goto L38
        L36:
            r89 = R.drawable.msg_close;
            goto L35
        L9:
            if (r0 != 3) goto L11;
            StickerSetGroupInfoCell r74 = (StickerSetGroupInfoCell) r7.itemView;
            if (r8 != (this.totalItems - 1)) goto L23;
            r12 = true;
        L23:
            r74.setIsLast(r12);
            return;
        L11:
            if (r0 != 5) goto L102;
            StickerSetNameCell r75 = (StickerSetNameCell) r7.itemView;
            if (MediaDataController.getInstance(this.this$0.currentAccount).loadFeaturedPremium == false) goto L17;
            int r62 = R.string.FeaturedStickersPremium;
        L18:
            r75.setText(LocaleController.getString(r62), R.drawable.msg_close, LocaleController.getString(R.string.AccDescrCloseTrendingStickers));
            return;
        L17:
            r62 = R.string.FeaturedStickers;
            goto L18
        L102:
            return;
        L98:
            TLRPC.Document r04 = (TLRPC.Document) this.cache.get(r8);
            StickerEmojiCell r76 = (StickerEmojiCell) r7.itemView;
            r76.setSticker(r04, this.cacheParents.get(r8), false);
            r76.setRecent(EmojiView.m10367$$Nest$fgetrecentStickers(this.this$0).contains(r04));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v6, types: [android.view.ViewGroup, android.widget.FrameLayout] */
        /* JADX WARN: Type inference failed for: r2v4, types: [android.view.View] */
        /* JADX WARN: Type inference failed for: r2v8, types: [android.view.View, androidx.recyclerview.widget.RecyclerView, org.telegram.ui.Components.EmojiView$TrendingListView, org.telegram.ui.Components.RecyclerListView] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @SuppressLint({"NotifyDataSetChanged"})
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r18, int r19) {
            switch(r19) {
                case 0: goto L14;
                case 1: goto L13;
                case 2: goto L12;
                case 3: goto L11;
                case 4: goto L10;
                case 5: goto L9;
                case 6: goto L7;
                case 7: goto L5;
                default: goto L4;
            };
        L4:
            View r0 = null;
        L16:
            return new RecyclerListView.Holder(r0);
        L5:
            ?? r1 = new FrameLayout(this.context);
            LinearLayout r3 = new LinearLayout(this.context);
            r3.setOrientation(1);
            r3.setGravity(17);
            int r5 = AndroidUtilities.dp(13.0f);
            EmojiView r6 = this.this$0;
            int r7 = Theme.key_chat_emojiPanelIcon;
            r3.setBackground(Theme.createRoundRectDrawable(r5, Theme.multAlpha(EmojiView.m10432$$Nest$mgetThemedColor(r6, r7), 0.12f)));
            ScaleStateListAnimator.apply(r3, 0.1f, 1.5f);
            r3.setOnClickListener(new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda4(this));
            ImageView r52 = new ImageView(this.context);
            r52.setImageResource(R.drawable.menu_sticker_add);
            r52.setColorFilter(new PorterDuffColorFilter(EmojiView.m10432$$Nest$mgetThemedColor(this.this$0, r7), PorterDuff.Mode.SRC_IN));
            r3.addView(r52, LayoutHelper.createLinear(24, 24, 17, 0, 0, 0, 0));
            TextView r53 = new TextView(this.context);
            r53.setGravity(17);
            r53.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$0, r7));
            r53.setTextSize(1, 11.0f);
            r53.setTypeface(AndroidUtilities.bold());
            r53.setText(LocaleController.getString(R.string.Create));
            r3.addView(r53, LayoutHelper.createLinear(-1, -2, 17, 0, 3, 0, 0));
            r1.addView(r3, LayoutHelper.createFrame(-1, -1.0f, 119, 8.0f, 8.0f, 8.0f, 8.0f));
            FrameLayout r12 = r1;
        L6:
            r0 = r12;
            goto L16
        L7:
            EmojiView r4 = this.this$0;
            Context r54 = this.context;
            TrendingAdapter r62 = new TrendingAdapter(r4, false);
            EmojiView.m10417$$Nest$fputtrendingAdapter(r4, r62);
            ?? r2 = new TrendingListView(r4, r54, r62);
            r2.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), 0);
            r2.setClipToPadding(false);
            r2.addItemDecoration(new AnonymousClass2(this));
            r2.setOnItemClickListener(new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda3(this));
            r2.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(52.0f)));
            StickerSetGroupInfoCell r22 = r2;
        L8:
            r0 = r22;
            goto L16
        L9:
            StickerSetNameCell r13 = new StickerSetNameCell(this.context, false, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0), EmojiView.m10349$$Nest$fgetglassDesign(this.this$0));
            r13.setOnIconClickListener(new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda2(this));
            r12 = r13;
            goto L6
        L10:
            ?? r23 = new View(this.context);
            r23.setLayoutParams(new RecyclerView.LayoutParams(-1, EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0)));
            r22 = r23;
            goto L8
        L11:
            StickerSetGroupInfoCell r24 = new StickerSetGroupInfoCell(this.context);
            r24.setAddOnClickListener(new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda1(this));
            r24.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r22 = r24;
            goto L8
        L12:
            StickerSetNameCell r14 = new StickerSetNameCell(this.context, false, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0), EmojiView.m10349$$Nest$fgetglassDesign(this.this$0));
            r14.setOnIconClickListener(new EmojiView$StickersGridAdapter$$ExternalSyntheticLambda0(this, r14));
            r12 = r14;
            goto L6
        L13:
            r12 = new EmptyCell(this.context);
            goto L6
        L14:
            r12 = new AnonymousClass1(this, this.context, true, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            goto L6
        }
    }

    public class StickersSearchGridAdapter extends RecyclerListView.SelectionAdapter {
        private SparseArray<Object> cache;
        private SparseArray<Object> cacheParent;
        private Context context;
        private ArrayList<ArrayList<TLRPC.Document>> emojiArrays;
        private int emojiSearchId;
        private HashMap<ArrayList<TLRPC.Document>, String> emojiStickers;
        private ArrayList<EmojiPackInfo> foundEmojiPacks;
        private FoundEmojiPacksRecyclerView foundPacksListView;
        private int foundPacksRow;
        private ArrayList<TLRPC.Document> globalSearchArray;
        private boolean isCompleted;
        private ArrayList<TLRPC.TL_messages_stickerSet> localPacks;
        private HashMap<TLRPC.TL_messages_stickerSet, Integer> localPacksByName;
        private HashMap<TLRPC.TL_messages_stickerSet, Boolean> localPacksByShortName;
        private SparseArray<String> positionToEmoji;
        private SparseIntArray positionToRow;
        private int reqId;
        private int reqId2;
        private SparseArray<Object> rowStartPack;
        private String searchQuery;
        private final SearchRunnable searchRunnable;
        private long selectedPackId;
        private TLRPC.StickerSet selectedPackStickerSet;
        private ArrayList<TLRPC.Document> selectedPackStickers;
        final /* synthetic */ EmojiView this$0;
        private int totalItems;

        /* renamed from: org.telegram.ui.Components.EmojiView$StickersSearchGridAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements SearchRunnable {
            final ArrayList<ArrayList<TLRPC.Document>> emojiArrays;
            final HashMap<ArrayList<TLRPC.Document>, String> emojiStickers;
            final ArrayList<TLRPC.Document> emojiStickersArray;
            final ArrayList<TLRPC.Document> emojiStickersArray2;
            final LongSparseArray<TLRPC.Document> emojiStickersMap;
            final ArrayList<EmojiPackInfo> foundEmojiPacks;
            int lastId;
            final ArrayList<TLRPC.TL_messages_stickerSet> localPacks;
            final HashMap<TLRPC.TL_messages_stickerSet, Integer> localPacksByName;
            final HashMap<TLRPC.TL_messages_stickerSet, Boolean> localPacksByShortName;
            String query;
            final /* synthetic */ StickersSearchGridAdapter this$1;

            public static /* synthetic */ void $r8$lambda$2Pt2ytVABj67HTy51djyvKFDeDE(AnonymousClass1 r0, HashMap r1, Runnable r2, ArrayList r3, String r4) {
                r0.lambda$addFromSuggestions$0(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$3mqxY4aXoyr6bffj5LETLiXqHUc(AnonymousClass1 r0, Runnable r1, ArrayList r2) {
                r0.lambda$searchStickerSetsByName$1(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$5_oG9MwudLN6qehjDCSMcSpjkg0(AnonymousClass1 r0, Runnable r1) {
                r0.addLocalPacks(r1);
            }

            public static /* synthetic */ void $r8$lambda$5qDemOr1tKAuvj37_yoEQr9CI20(AnonymousClass1 r0, Runnable r1) {
                r0.searchStickers(r1);
            }

            public static /* synthetic */ void $r8$lambda$6yrXTZb4N7wWbGa8PgwtpBB_pdY(AnonymousClass1 r0, Runnable r1) {
                r0.searchStickerSetsByName(r1);
            }

            public static /* synthetic */ void $r8$lambda$FWJmmJpUFngxJ3tK8LHdVaOT4Wg(AnonymousClass1 r0, Runnable r1) {
                r0.searchStickerSets(r1);
            }

            public static /* synthetic */ void $r8$lambda$GLL9JAGgFP383w_rM6JjfQ3ilOA(AnonymousClass1 r0, TLRPC.TL_messages_getStickers r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
                r0.lambda$searchStickers$4(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$HKKzqvPLgjidzoG9yLKZie6ffOU(AnonymousClass1 r0, Runnable r1) {
                r0.addPremiumStickers(r1);
            }

            public static /* synthetic */ void $r8$lambda$IQayWNWh_F9u0MmB8jirO1_w3tU(AnonymousClass1 r0, Runnable r1) {
                r0.addFromAllStickers(r1);
            }

            /* renamed from: $r8$lambda$PJ3iHlQLPlHdtDz54DdBvUp-QdE, reason: not valid java name */
            public static /* synthetic */ void m10571$r8$lambda$PJ3iHlQLPlHdtDz54DdBvUpQdE(AnonymousClass1 r0, Runnable r1) {
                r0.addFromSuggestions(r1);
            }

            public static /* synthetic */ void $r8$lambda$RrKQlg1CCvztJOwfJ9PfapY5cmg(AnonymousClass1 r0, boolean r1, Runnable r2, ArrayList r3) {
                r0.lambda$searchStickerSets$2(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$SpgYd1J325e0ZbbDlzZVsdwbpKs(AnonymousClass1 r0, Runnable r1) {
                r0.lambda$loadNext$5(r1);
            }

            public static /* synthetic */ void $r8$lambda$Tnb2GcfnNpVfN8vnf2a6UnvhFqY(AnonymousClass1 r0, TLRPC.TL_messages_getStickers r1, TLObject r2, Runnable r3) {
                r0.lambda$searchStickers$3(r1, r2, r3);
            }

            /* renamed from: $r8$lambda$osMy-FKwLOG4nmk3lHczXRGv95o, reason: not valid java name */
            public static /* synthetic */ void m10572$r8$lambda$osMyFKwLOG4nmk3lHczXRGv95o(AnonymousClass1 r0) {
                r0.searchFinish();
            }

            public AnonymousClass1(StickersSearchGridAdapter r2) {
                this.this$1 = r2;
                this.localPacks = new ArrayList();
                this.localPacksByShortName = new HashMap();
                this.localPacksByName = new HashMap();
                this.emojiStickers = new HashMap();
                this.emojiArrays = new ArrayList();
                this.foundEmojiPacks = new ArrayList();
                this.emojiStickersArray = new ArrayList(0);
                this.emojiStickersArray2 = new ArrayList(0);
                this.emojiStickersMap = new LongSparseArray(0);
            }

            private void addFromAllStickers(Runnable r11) {
                if (this.query.length() > 14) goto L36;
                HashMap<String, ArrayList<TLRPC.Document>> r0 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getAllStickers();
                CharSequence r1 = this.query;
                int r2 = r1.length();
                int r3 = 0;
                int r4 = 0;
            L5:
                if (r4 >= r2) goto L26;
                if (r4 >= (r2 - 1)) goto L23;
                if (r1.charAt(r4) != 55356) goto L15;
                int r5 = r4 + 1;
                if (r1.charAt(r5) < 57339) goto L15;
                if (r1.charAt(r5) > 57343) goto L15;
            L20:
                r1 = TextUtils.concat(new CharSequence[]{r1.subSequence(0, r4), r1.subSequence(r4 + 2, r1.length())});
                r2 = r2 - 2;
            L21:
                r4 = r4 - 1;
            L25:
                r4 = r4 + 1;
            L15:
                if (r1.charAt(r4) != 8205) goto L23;
                int r52 = r4 + 1;
                if (r1.charAt(r52) == 9792) goto L20;
                if (r1.charAt(r52) == 9794) goto L20;
            L23:
                if (r1.charAt(r4) != 65039) goto L25;
                r1 = TextUtils.concat(new CharSequence[]{r1.subSequence(0, r4), r1.subSequence(r4 + 1, r1.length())});
                r2 = r2 - 1;
                goto L21
            L26:
                if (r0 == null) goto L28;
                ArrayList<TLRPC.Document> r02 = r0.get(r1.toString());
            L29:
                if (r02 == null) goto L36;
                if (r02.isEmpty() == true) goto L36;
                this.emojiStickersArray2.addAll(r02);
                int r12 = r02.size();
            L33:
                if (r3 >= r12) goto L35;
                TLRPC.Document r22 = r02.get(r3);
                this.emojiStickersMap.put(r22.id, r22);
                r3 = r3 + 1;
                goto L33
            L35:
                this.emojiStickers.put(this.emojiStickersArray2, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                this.emojiArrays.add(this.emojiStickersArray2);
                goto L36
            L28:
                r02 = null;
            L36:
                r11.run();
            }

            private void addFromSuggestions(Runnable r9) {
                HashMap<String, ArrayList<TLRPC.Document>> r0 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getAllStickers();
                if (r0 != null) goto L5;
            L13:
                r9.run();
                return;
            L5:
                if (r0.isEmpty() == true) goto L13;
                if (this.query.length() <= 1) goto L13;
                String[] r1 = AndroidUtilities.getCurrentKeyboardLanguage();
                if (Arrays.equals(EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(this.this$1.this$0), r1) == true) goto L11;
                MediaDataController.getInstance(this.this$1.this$0.currentAccount).fetchNewEmojiKeywords(r1);
            L11:
                EmojiView.m10412$$Nest$fputlastSearchKeyboardLanguage(this.this$1.this$0, r1);
                MediaDataController.getInstance(this.this$1.this$0.currentAccount).getEmojiSuggestions(EmojiView.m10360$$Nest$fgetlastSearchKeyboardLanguage(this.this$1.this$0), StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1), true, new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda11(this, r0, r9), false);
            }

            private void addLocalPacks(Runnable r10) {
                int r1 = 0;
                ArrayList<TLRPC.TL_messages_stickerSet> r0 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getStickerSets(0);
                MessagesController.getInstance(this.this$1.this$0.currentAccount).filterPremiumStickers(r0);
                int r2 = r0.size();
                int r3 = 0;
            L4:
                if (r3 >= r2) goto L20;
                TLRPC.TL_messages_stickerSet r5 = r0.get(r3);
                int r6 = AndroidUtilities.indexOfIgnoreCase(r5.set.title, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                if (r6 < 0) goto L11;
                if (r6 != 0) goto L9;
            L10:
                this.localPacks.add(r5);
                this.localPacksByName.put(r5, Integer.valueOf(r6));
            L19:
                r3 = r3 + 1;
                goto L4
            L9:
                if (r5.set.title.charAt(r6 - 1) != ' ') goto L19;
            L11:
                String r62 = r5.set.short_name;
                if (r62 == null) goto L19;
                int r63 = AndroidUtilities.indexOfIgnoreCase(r62, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                if (r63 < 0) goto L19;
                if (r63 != 0) goto L17;
            L18:
                this.localPacks.add(r5);
                this.localPacksByShortName.put(r5, Boolean.TRUE);
                goto L19
            L17:
                if (r5.set.short_name.charAt(r63 - 1) != ' ') goto L19;
            L20:
                ArrayList<TLRPC.TL_messages_stickerSet> r02 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getStickerSets(3);
                MessagesController.getInstance(this.this$1.this$0.currentAccount).filterPremiumStickers(r02);
                int r22 = r02.size();
            L21:
                if (r1 >= r22) goto L37;
                TLRPC.TL_messages_stickerSet r32 = r02.get(r1);
                int r52 = AndroidUtilities.indexOfIgnoreCase(r32.set.title, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                if (r52 < 0) goto L28;
                if (r52 != 0) goto L26;
            L27:
                this.localPacks.add(r32);
                this.localPacksByName.put(r32, Integer.valueOf(r52));
            L36:
                r1 = r1 + 1;
                goto L21
            L26:
                if (r32.set.title.charAt(r52 - 1) != ' ') goto L36;
            L28:
                String r53 = r32.set.short_name;
                if (r53 == null) goto L36;
                int r54 = AndroidUtilities.indexOfIgnoreCase(r53, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                if (r54 < 0) goto L36;
                if (r54 != 0) goto L34;
            L35:
                this.localPacks.add(r32);
                this.localPacksByShortName.put(r32, Boolean.TRUE);
                goto L36
            L34:
                if (r32.set.short_name.charAt(r54 - 1) != ' ') goto L36;
            L37:
                r10.run();
            }

            private void addPremiumStickers(Runnable r14) {
                HashMap<String, ArrayList<TLRPC.Document>> r0 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getAllStickers();
                HashSet r1 = new HashSet();
                ArrayList r2 = new ArrayList();
                Iterator<ArrayList<TLRPC.Document>> r02 = r0.values().iterator();
            L3:
                int r4 = 0;
                if (r02.hasNext() == false) goto L12;
                ArrayList<TLRPC.Document> r3 = r02.next();
                int r5 = r3.size();
            L6:
                if (r4 >= r5) goto L3;
                TLRPC.Document r6 = r3.get(r4);
                r4 = r4 + 1;
                TLRPC.Document r62 = r6;
                if (r1.contains(Long.valueOf(r62.id)) == true) goto L6;
                if (MessageObject.isPremiumSticker(r62) == false) goto L6;
                r1.add(Long.valueOf(r62.id));
                r2.add(r62);
                this.emojiStickersMap.put(r62.id, r62);
                goto L6
            L12:
                ArrayList<TLRPC.StickerSetCovered> r03 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getFeaturedStickerSets();
                int r32 = r03.size();
                int r52 = 0;
            L13:
                if (r52 >= r32) goto L31;
                TLRPC.StickerSetCovered r63 = r03.get(r52);
                r52 = r52 + 1;
                TLRPC.StickerSetCovered r64 = r63;
                TLRPC.Document r7 = r64.cover;
                if (r7 != null) goto L17;
            L21:
                ArrayList<TLRPC.Document> r65 = r64.covers;
                if (r65 == null) goto L13;
                int r72 = r65.size();
                int r8 = 0;
            L24:
                if (r8 >= r72) goto L13;
                TLRPC.Document r9 = r65.get(r8);
                r8 = r8 + 1;
                TLRPC.Document r92 = r9;
                if (r1.contains(Long.valueOf(r92.id)) == true) goto L24;
                if (MessageObject.isPremiumSticker(r92) == false) goto L24;
                r1.add(Long.valueOf(r92.id));
                r2.add(r92);
                this.emojiStickersMap.put(r92.id, r92);
                goto L24
            L17:
                if (r1.contains(Long.valueOf(r7.id)) == true) goto L21;
                if (MessageObject.isPremiumSticker(r64.cover) == false) goto L21;
                r1.add(Long.valueOf(r64.cover.id));
                r2.add(r64.cover);
                LongSparseArray<TLRPC.Document> r73 = this.emojiStickersMap;
                TLRPC.Document r82 = r64.cover;
                r73.put(r82.id, r82);
                goto L21
            L31:
                if (r2.isEmpty() == true) goto L33;
                this.emojiStickersArray2.addAll(r2);
                this.emojiStickers.put(this.emojiStickersArray2, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                this.emojiArrays.add(this.emojiStickersArray2);
            L33:
                r14.run();
            }

            private /* synthetic */ void lambda$addFromSuggestions$0(HashMap r5, Runnable r6, ArrayList r7, String r8) {
                if (StickersSearchGridAdapter.m10553$$Nest$fgetemojiSearchId(this.this$1) == this.lastId) goto L5;
                return;
            L5:
                int r82 = r7.size();
                int r0 = 0;
            L6:
                if (r0 >= r82) goto L15;
                String r1 = ((MediaDataController.KeywordResult) r7.get(r0)).emoji;
                ArrayList<TLRPC.Document> r2 = (ArrayList) r5.get(r1);
                if (r2 == null) goto L14;
                if (r2.isEmpty() == true) goto L14;
                if (this.emojiStickers.containsKey(r2) == true) goto L14;
                this.emojiStickers.put(r2, r1);
                this.emojiArrays.add(r2);
            L14:
                r0 = r0 + 1;
                goto L6
            L15:
                r6.run();
            }

            private /* synthetic */ void lambda$loadNext$5(Runnable r2) {
                searchStickerSets(r2, true);
            }

            private /* synthetic */ void lambda$searchStickerSets$2(boolean r6, Runnable r7, ArrayList r8) {
                if (StickersSearchGridAdapter.m10553$$Nest$fgetemojiSearchId(this.this$1) == this.lastId) goto L5;
                return;
            L5:
                int r0 = 0;
                if (r6 == false) goto L12;
                int r62 = this.emojiStickersArray.size();
                this.emojiStickersArray.clear();
                StickersSearchGridAdapter r1 = this.this$1;
                if (r62 != r8.size()) goto L10;
                boolean r63 = true;
            L11:
                StickersSearchGridAdapter.m10565$$Nest$fputisCompleted(r1, r63);
                goto L12
            L10:
                r63 = false;
            L12:
                this.emojiStickersArray.addAll(r8);
                int r64 = r8.size();
            L13:
                if (r0 >= r64) goto L15;
                Object r12 = r8.get(r0);
                r0 = r0 + 1;
                TLRPC.Document r13 = (TLRPC.Document) r12;
                this.emojiStickersMap.put(r13.id, r13);
                goto L13
            L15:
                this.emojiStickers.put(this.emojiStickersArray, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                r7.run();
            }

            private /* synthetic */ void lambda$searchStickerSetsByName$1(Runnable r9, ArrayList r10) {
                int r0 = r10.size();
                int r1 = 0;
            L3:
                if (r1 >= r0) goto L18;
                Object r2 = r10.get(r1);
                r1 = r1 + 1;
                TLRPC.StickerSetCovered r22 = (TLRPC.StickerSetCovered) r2;
                if ((r22 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L8;
                ArrayList<TLRPC.Document> r3 = ((TLRPC.TL_stickerSetFullCovered) r22).documents;
            L14:
                if (r3 == null) goto L3;
                if (r3.isEmpty() == true) goto L3;
                this.foundEmojiPacks.add(new EmojiPackInfo(r22, r3, null));
                goto L3
            L8:
                if ((r22 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L13;
                TLRPC.TL_messages_stickerSet r32 = MediaDataController.getInstance(this.this$1.this$0.currentAccount).getStickerSet(MediaDataController.getInputStickerSet(r22.set), Integer.valueOf(r22.set.hash), true);
                if (r32 == null) goto L12;
                r3 = r32.documents;
                goto L14
            L12:
                r3 = null;
                goto L14
            L13:
                r3 = r22.covers;
                goto L14
            L18:
                r9.run();
            }

            private /* synthetic */ void lambda$searchStickers$3(TLRPC.TL_messages_getStickers r7, TLObject r8, Runnable r9) {
                if (StickersSearchGridAdapter.m10553$$Nest$fgetemojiSearchId(this.this$1) == this.lastId) goto L5;
                return;
            L5:
                int r1 = 0;
                StickersSearchGridAdapter.m10569$$Nest$fputreqId2(this.this$1, 0);
                if (r7.emoticon.equals(this.query) == true) goto L8;
            L23:
                r9.run();
                return;
            L8:
                if ((r8 instanceof TLRPC.TL_messages_stickers) == true) goto L11;
                r9.run();
                return;
            L11:
                TLRPC.TL_messages_stickers r82 = (TLRPC.TL_messages_stickers) r8;
                int r72 = this.emojiStickersArray2.size();
                int r0 = r82.stickers.size();
            L12:
                if (r1 >= r0) goto L19;
                TLRPC.Document r2 = r82.stickers.get(r1);
                if (this.emojiStickersMap.indexOfKey(r2.id) >= 0) goto L17;
                this.emojiStickersArray2.add(r2);
            L17:
                r1 = r1 + 1;
                goto L12
            L19:
                if (r72 == this.emojiStickersArray2.size()) goto L23;
                this.emojiStickers.put(this.emojiStickersArray2, StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                if (r72 != 0) goto L23;
                this.emojiArrays.add(this.emojiStickersArray2);
                goto L23
            }

            private /* synthetic */ void lambda$searchStickers$4(TLRPC.TL_messages_getStickers r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
                AndroidUtilities.runOnUIThread(new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda13(this, r1, r3, r2));
            }

            private void searchFinish() {
                if (StickersSearchGridAdapter.m10553$$Nest$fgetemojiSearchId(this.this$1) == this.lastId) goto L5;
                return;
            L5:
                this.emojiArrays.remove(this.emojiStickersArray);
                StickersSearchGridAdapter.m10566$$Nest$fputlocalPacks(this.this$1, this.localPacks);
                StickersSearchGridAdapter.m10568$$Nest$fputlocalPacksByShortName(this.this$1, this.localPacksByShortName);
                StickersSearchGridAdapter.m10567$$Nest$fputlocalPacksByName(this.this$1, this.localPacksByName);
                StickersSearchGridAdapter.m10562$$Nest$fputemojiStickers(this.this$1, this.emojiStickers);
                StickersSearchGridAdapter.m10560$$Nest$fputemojiArrays(this.this$1, this.emojiArrays);
                StickersSearchGridAdapter.m10563$$Nest$fputfoundEmojiPacks(this.this$1, this.foundEmojiPacks);
                StickersSearchGridAdapter.m10564$$Nest$fputglobalSearchArray(this.this$1, new ArrayList(this.emojiStickersArray));
                EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$1.this$0).showProgress(false);
                if (EmojiView.m10381$$Nest$fgetstickersGridView(this.this$1.this$0).getAdapter() == EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$1.this$0)) goto L8;
                EmojiView.m10381$$Nest$fgetstickersGridView(this.this$1.this$0).setAdapter(EmojiView.m10384$$Nest$fgetstickersSearchGridAdapter(this.this$1.this$0));
            L8:
                this.this$1.notifyDataSetChanged();
            }

            private void searchStickerSets(Runnable r2) {
                searchStickerSets(r2, false);
            }

            private void searchStickerSets(Runnable r8, boolean r9) {
                String[] r0 = AndroidUtilities.getCurrentKeyboardLanguage();
                if (r0 != null) goto L5;
            L9:
                String r02 = _UrlKt.FRAGMENT_ENCODE_SET;
            L8:
                String r3 = r02;
                MediaDataController.getInstance(this.this$1.this$0.currentAccount).searchStickers(false, r3, this.query, new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda12(this, r9, r8), r9);
                return;
            L5:
                if (r0.length == 0) goto L9;
                r02 = r0[0];
                goto L8
            }

            private void searchStickerSetsByName(Runnable r4) {
                MediaDataController.getInstance(this.this$1.this$0.currentAccount).searchStickerSets(false, this.query, new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda9(this, r4));
            }

            private void searchStickers(Runnable r5) {
                if (Emoji.fullyConsistsOfEmojis(StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1)) == false) goto L6;
                TLRPC.TL_messages_getStickers r0 = new TLRPC.TL_messages_getStickers();
                r0.emoticon = this.query;
                r0.hash = 0;
                StickersSearchGridAdapter r1 = this.this$1;
                StickersSearchGridAdapter.m10569$$Nest$fputreqId2(r1, ConnectionsManager.getInstance(r1.this$0.currentAccount).sendRequest(r0, new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda10(this, r0, r5)));
                return;
            L6:
                r5.run();
            }

            @Override // org.telegram.ui.Components.EmojiView.SearchRunnable
            public boolean isCompleted() {
                return StickersSearchGridAdapter.m10554$$Nest$fgetisCompleted(this.this$1);
            }

            @Override // org.telegram.ui.Components.EmojiView.SearchRunnable
            public boolean isLoading() {
                return EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$1.this$0).isInProgress();
            }

            @Override // org.telegram.ui.Components.EmojiView.SearchRunnable
            public void loadNext() {
                if (isLoading() == false) goto L5;
                return;
            L5:
                EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$1.this$0).showProgress(true);
                Utilities.raceCallbacks(new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda0(this), new Utilities.Callback[]{new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda1(this)});
            }

            @Override // java.lang.Runnable
            public void run() {
                boolean r0 = TextUtils.isEmpty(StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1));
                StickersSearchGridAdapter r1 = this.this$1;
                if (r0 == true) goto L5;
                int r02 = StickersSearchGridAdapter.m10553$$Nest$fgetemojiSearchId(r1) + 1;
                StickersSearchGridAdapter.m10561$$Nest$fputemojiSearchId(r1, r02);
                this.lastId = r02;
                this.query = StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(this.this$1);
                StickersSearchGridAdapter.m10565$$Nest$fputisCompleted(this.this$1, false);
                this.localPacks.clear();
                this.localPacksByShortName.clear();
                this.localPacksByName.clear();
                this.emojiStickers.clear();
                this.emojiArrays.clear();
                this.emojiStickersArray.clear();
                this.emojiStickersArray2.clear();
                this.emojiStickersMap.clear();
                EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$1.this$0).showProgress(true);
                if ("premium".equalsIgnoreCase(this.query) == false) goto L13;
                Utilities.raceCallbacks(new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda0(this), new Utilities.Callback[]{new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda2(this)});
                return;
            L13:
                Utilities.raceCallbacks(new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda0(this), new Utilities.Callback[]{new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda3(this), new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda4(this), new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda5(this), new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda6(this), new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda7(this), new EmojiView$StickersSearchGridAdapter$1$$ExternalSyntheticLambda8(this)});
                return;
            L5:
                if (EmojiView.m10381$$Nest$fgetstickersGridView(r1.this$0).getAdapter() == EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$1.this$0)) goto L7;
                EmojiView.m10381$$Nest$fgetstickersGridView(this.this$1.this$0).setAdapter(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$1.this$0));
            L7:
                this.this$1.notifyDataSetChanged();
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$StickersSearchGridAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends FoundEmojiPacksRecyclerView {
            final /* synthetic */ StickersSearchGridAdapter this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass2(StickersSearchGridAdapter r1, Context r2, int r3, int r4, boolean r5, Utilities.Callback2 r6, Utilities.Callback5 r7, Utilities.Callback5Return r8, Theme.ResourcesProvider r9, int r10, int r11, EmojiView r12) {
                this.this$1 = r1;
                this.val$this$0 = r12;
                super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$StickersSearchGridAdapter$3, reason: invalid class name */
        public class AnonymousClass3 implements View.OnTouchListener {
            final /* synthetic */ StickersSearchGridAdapter this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass3(StickersSearchGridAdapter r1, EmojiView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View r3, MotionEvent r4) {
                if (r4.getAction() != 0) goto L6;
                EmojiView.m10410$$Nest$fputignorePagerScroll(this.this$1.this$0, true);
            L10:
                return false;
            L6:
                if (r4.getAction() != 1) goto L8;
            L9:
                EmojiView.m10410$$Nest$fputignorePagerScroll(this.this$1.this$0, false);
                goto L10
            L8:
                if (r4.getAction() != 3) goto L10;
                goto L9
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$StickersSearchGridAdapter$4, reason: invalid class name */
        public class AnonymousClass4 extends StickerEmojiCell {
            final /* synthetic */ StickersSearchGridAdapter this$1;

            public AnonymousClass4(StickersSearchGridAdapter r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(82.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiView$StickersSearchGridAdapter$5, reason: invalid class name */
        public class AnonymousClass5 extends FrameLayout {
            final /* synthetic */ StickersSearchGridAdapter this$1;

            public AnonymousClass5(StickersSearchGridAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((int) ((((EmojiView.m10381$$Nest$fgetstickersGridView(this.this$1.this$0).getMeasuredHeight() - EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$1.this$0)) - AndroidUtilities.dp(8.0f)) / 3) * 1.7f), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$5aeEPVW6nbsNcMNIdg_VEYce2rQ(StickersSearchGridAdapter r0, ArrayList r1, UniversalAdapter r2) {
            r0.foundPackListFillItems(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$UMwr9UPKIrii2rTRCpgEC1SWlZ8(StickersSearchGridAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$wHNPwgFzeXpmrWjuP26GTYZcdwA(StickersSearchGridAdapter r0, UItem r1, View r2, int r3, float r4, float r5) {
            r0.foundPackListOnClickItem(r1, r2, r3, r4, r5);
        }

        /* renamed from: -$$Nest$fgetcache, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SparseArray m10552$$Nest$fgetcache(StickersSearchGridAdapter r0) {
            return r0.cache;
        }

        /* renamed from: -$$Nest$fgetemojiSearchId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10553$$Nest$fgetemojiSearchId(StickersSearchGridAdapter r0) {
            return r0.emojiSearchId;
        }

        /* renamed from: -$$Nest$fgetisCompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10554$$Nest$fgetisCompleted(StickersSearchGridAdapter r0) {
            return r0.isCompleted;
        }

        /* renamed from: -$$Nest$fgetsearchQuery, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m10555$$Nest$fgetsearchQuery(StickersSearchGridAdapter r0) {
            return r0.searchQuery;
        }

        /* renamed from: -$$Nest$fgetsearchRunnable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchRunnable m10556$$Nest$fgetsearchRunnable(StickersSearchGridAdapter r0) {
            return r0.searchRunnable;
        }

        /* renamed from: -$$Nest$fgetselectedPackId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m10557$$Nest$fgetselectedPackId(StickersSearchGridAdapter r2) {
            return r2.selectedPackId;
        }

        /* renamed from: -$$Nest$fgetselectedPackStickers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m10558$$Nest$fgetselectedPackStickers(StickersSearchGridAdapter r0) {
            return r0.selectedPackStickers;
        }

        /* renamed from: -$$Nest$fgettotalItems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m10559$$Nest$fgettotalItems(StickersSearchGridAdapter r0) {
            return r0.totalItems;
        }

        /* renamed from: -$$Nest$fputemojiArrays, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10560$$Nest$fputemojiArrays(StickersSearchGridAdapter r0, ArrayList r1) {
            r0.emojiArrays = r1;
        }

        /* renamed from: -$$Nest$fputemojiSearchId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10561$$Nest$fputemojiSearchId(StickersSearchGridAdapter r0, int r1) {
            r0.emojiSearchId = r1;
        }

        /* renamed from: -$$Nest$fputemojiStickers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10562$$Nest$fputemojiStickers(StickersSearchGridAdapter r0, HashMap r1) {
            r0.emojiStickers = r1;
        }

        /* renamed from: -$$Nest$fputfoundEmojiPacks, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10563$$Nest$fputfoundEmojiPacks(StickersSearchGridAdapter r0, ArrayList r1) {
            r0.foundEmojiPacks = r1;
        }

        /* renamed from: -$$Nest$fputglobalSearchArray, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10564$$Nest$fputglobalSearchArray(StickersSearchGridAdapter r0, ArrayList r1) {
            r0.globalSearchArray = r1;
        }

        /* renamed from: -$$Nest$fputisCompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10565$$Nest$fputisCompleted(StickersSearchGridAdapter r0, boolean r1) {
            r0.isCompleted = r1;
        }

        /* renamed from: -$$Nest$fputlocalPacks, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10566$$Nest$fputlocalPacks(StickersSearchGridAdapter r0, ArrayList r1) {
            r0.localPacks = r1;
        }

        /* renamed from: -$$Nest$fputlocalPacksByName, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10567$$Nest$fputlocalPacksByName(StickersSearchGridAdapter r0, HashMap r1) {
            r0.localPacksByName = r1;
        }

        /* renamed from: -$$Nest$fputlocalPacksByShortName, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10568$$Nest$fputlocalPacksByShortName(StickersSearchGridAdapter r0, HashMap r1) {
            r0.localPacksByShortName = r1;
        }

        /* renamed from: -$$Nest$fputreqId2, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10569$$Nest$fputreqId2(StickersSearchGridAdapter r0, int r1) {
            r0.reqId2 = r1;
        }

        /* renamed from: -$$Nest$fputselectedPackStickers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10570$$Nest$fputselectedPackStickers(StickersSearchGridAdapter r0, ArrayList r1) {
            r0.selectedPackStickers = r1;
        }

        public StickersSearchGridAdapter(EmojiView r14, Context r15) {
            this.this$0 = r14;
            this.rowStartPack = new SparseArray();
            this.cache = new SparseArray();
            this.cacheParent = new SparseArray();
            this.positionToRow = new SparseIntArray();
            this.positionToEmoji = new SparseArray();
            this.localPacks = new ArrayList();
            this.localPacksByShortName = new HashMap();
            this.localPacksByName = new HashMap();
            this.emojiStickers = new HashMap();
            this.emojiArrays = new ArrayList();
            this.foundEmojiPacks = new ArrayList();
            this.globalSearchArray = new ArrayList();
            this.searchRunnable = new AnonymousClass1(this);
            this.foundPacksRow = -1;
            this.context = r15;
            AnonymousClass2 r0 = new AnonymousClass2(this, r15, r14.currentAccount, -1, false, new EmojiView$StickersSearchGridAdapter$$ExternalSyntheticLambda1(this), new EmojiView$StickersSearchGridAdapter$$ExternalSyntheticLambda2(this), null, EmojiView.m10370$$Nest$fgetresourcesProvider(r14), -1, 0, r14);
            this.foundPacksListView = r0;
            r0.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(5.0f));
            this.foundPacksListView.setClipToPadding(false);
            this.foundPacksListView.adapter.setApplyBackground(false);
            this.foundPacksListView.setNestedScrollingEnabled(false);
            this.foundPacksListView.setDrawSelection(false);
            this.foundPacksListView.setOnTouchListener(new AnonymousClass3(this, r14));
        }

        private void foundPackListFillItems(ArrayList<UItem> r12, UniversalAdapter r13) {
            LongSparseIntArray r132 = new LongSparseIntArray();
            ArrayList<TLRPC.TL_messages_stickerSet> r0 = this.localPacks;
            int r1 = r0.size();
            int r3 = 0;
        L3:
            boolean r4 = true;
            if (r3 >= r1) goto L12;
            TLRPC.TL_messages_stickerSet r5 = r0.get(r3);
            r3 = r3 + 1;
            TLRPC.TL_messages_stickerSet r52 = r5;
            if (r132.indexOfKey(r52.set.id) >= 0) goto L3;
            r132.append(r52.set.id, 1);
            if (r52.set.id == this.selectedPackId) goto L11;
            r4 = false;
        L11:
            r12.add(FoundStickerPackFactory.of(r52, r4));
            goto L3
        L12:
            ArrayList<EmojiPackInfo> r02 = this.foundEmojiPacks;
            int r14 = r02.size();
            int r32 = 0;
        L13:
            if (r32 >= r14) goto L21;
            EmojiPackInfo r53 = r02.get(r32);
            r32 = r32 + 1;
            EmojiPackInfo r54 = r53;
            if (r132.indexOfKey(EmojiPackInfo.m10481$$Nest$fgetset(r54).id) >= 0) goto L13;
            r132.append(EmojiPackInfo.m10481$$Nest$fgetset(r54).id, 1);
            TLRPC.StickerSetCovered r6 = EmojiPackInfo.m10483$$Nest$fgetstickerSetCovered(r54);
            if (EmojiPackInfo.m10481$$Nest$fgetset(r54).id != this.selectedPackId) goto L19;
            boolean r7 = true;
        L20:
            r12.add(FoundStickerPackFactory.of(r6, r54, r7));
            goto L13
        L19:
            r7 = false;
            goto L20
        }

        private void foundPackListOnClickItem(UItem r19, View r20, int r21, float r22, float r23) {
            Object r3 = r19.object;
            TLRPC.Document r7 = null;
            if ((r3 instanceof TLRPC.StickerSetCovered) == false) goto L10;
            TLRPC.StickerSetCovered r32 = (TLRPC.StickerSetCovered) r3;
            EmojiPackInfo r4 = (EmojiPackInfo) r19.object2;
            long r8 = this.selectedPackId;
            TLRPC.StickerSet r10 = r32.set;
            long r11 = r10.id;
            if (r8 != r11) goto L7;
            this.selectedPackId = 0;
            r10 = null;
        L8:
            TLRPC.StickerSet r14 = r10;
        L16:
            int r33 = this.foundPacksListView.getChildCount();
            boolean r42 = false;
            int r82 = 0;
        L18:
            if (r82 >= r33) goto L25;
            FoundStickerPackCell r102 = (FoundStickerPackCell) this.foundPacksListView.getChildAt(r82);
            if (r102 == r20) goto L23;
            r102.setSelected(false, true);
        L23:
            r82 = r82 + 1;
            goto L18
        L25:
            if (this.selectedPackId != 0) goto L27;
        L31:
            TLObject r13 = (TLObject) r19.object;
            EmojiView r112 = this.this$0;
            FoundStickerPackButton r12 = EmojiView.m10375$$Nest$fgetstickerAddPackButton(r112);
            ArrayList<TLRPC.Document> r1 = this.selectedPackStickers;
            if (r1 != null) goto L34;
        L36:
            TLRPC.Document r15 = r7;
            if (EmojiView.m10297$$Nest$fgetanimatorSearchStickerPackSelected(this.this$0).getFloatValue() <= 0.0f) goto L39;
            boolean r17 = true;
        L40:
            EmojiView.m10446$$Nest$msetFoundPackButtonText(r112, r12, r13, r14, r15, false, r17);
            FoundStickerPackCell r16 = (FoundStickerPackCell) r20;
            if (this.selectedPackId == 0) goto L43;
            boolean r34 = true;
        L44:
            r16.setSelected(r34, true);
            BoolAnimator r18 = EmojiView.m10297$$Nest$fgetanimatorSearchStickerPackSelected(this.this$0);
            if (this.selectedPackId == 0) goto L47;
            r42 = true;
        L47:
            r18.setValue(r42, true);
            notifyDataSetChanged();
            EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$0).hideKeyboard();
            if (this.selectedPackId == 0) goto L54;
            this.foundPacksListView.scrollOnSelect(r20);
            return;
        L54:
            return;
        L43:
            r34 = false;
            goto L44
        L39:
            r17 = false;
            goto L40
        L34:
            if (r1.isEmpty() == true) goto L36;
            r7 = this.selectedPackStickers.get(0);
            goto L36
        L27:
            if (this.selectedPackStickers.size() >= this.selectedPackStickerSet.count) goto L31;
            TLRPC.TL_messages_stickerSet r35 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSet(this.selectedPackStickerSet, false);
            if (r35 == null) goto L31;
            this.selectedPackStickers = r35.documents;
            goto L31
        L7:
            this.selectedPackId = r11;
            this.selectedPackStickers = EmojiPackInfo.m10479$$Nest$fgetdocuments(r4);
            this.selectedPackStickerSet = r32.set;
            goto L8
        L10:
            if ((r3 instanceof TLRPC.TL_messages_stickerSet) == false) goto L15;
            TLRPC.TL_messages_stickerSet r36 = (TLRPC.TL_messages_stickerSet) r3;
            long r83 = this.selectedPackId;
            r10 = r36.set;
            long r113 = r10.id;
            if (r83 != r113) goto L14;
            this.selectedPackId = 0;
            goto L15
        L14:
            this.selectedPackId = r113;
            this.selectedPackStickers = r36.documents;
            this.selectedPackStickerSet = r10;
        L15:
            r14 = null;
            goto L16
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r5) {
            FeaturedStickerSetInfoCell r52 = (FeaturedStickerSetInfoCell) r5.getParent();
            TLRPC.StickerSetCovered r0 = r52.getStickerSet();
            if (EmojiView.m10359$$Nest$fgetinstallingStickerSets(this.this$0).indexOfKey(r0.set.id) < 0) goto L5;
            return;
        L5:
            if (EmojiView.m10369$$Nest$fgetremovingStickerSets(this.this$0).indexOfKey(r0.set.id) < 0) goto L8;
            return;
        L8:
            if (r52.isInstalled() == false) goto L11;
            EmojiView.m10369$$Nest$fgetremovingStickerSets(this.this$0).put(r0.set.id, r0);
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickerSetRemove(r52.getStickerSet());
            return;
        L11:
            r52.setAddDrawProgress(true, true);
            EmojiView.m10359$$Nest$fgetinstallingStickerSets(this.this$0).put(r0.set.id, r0);
            EmojiView.m10307$$Nest$fgetdelegate(this.this$0).onStickerSetAdd(r52.getStickerSet());
        }

        private void rebuild() {
            this.foundPacksRow = -1;
            this.rowStartPack.clear();
            this.positionToRow.clear();
            this.cache.clear();
            this.positionToEmoji.clear();
            int r1 = 0;
            this.totalItems = 0;
            int r2 = this.localPacks.size() + this.localPacksByName.size();
            this.foundPacksListView.adapter.update(false);
            long r3 = this.selectedPackId;
            String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r3 == 0) goto L24;
            ArrayList<TLRPC.Document> r32 = this.selectedPackStickers;
            SparseArray<Object> r8 = this.cache;
            int r9 = this.totalItems;
            this.totalItems = r9 + 1;
            r8.put(r9, "search");
            if (r2 <= 0) goto L7;
            SparseArray<Object> r22 = this.cache;
            int r6 = this.totalItems;
            this.totalItems = r6 + 1;
            this.foundPacksRow = r6;
            r22.put(r6, "packs");
            SparseArray<Object> r23 = this.cache;
            int r5 = this.totalItems;
            this.totalItems = r5 + 1;
            r23.put(r5, LocaleController.formatPluralString("Stickers", this.selectedPackStickerSet.count, new Object[0]));
            int r7 = 3;
        L8:
            String r24 = this.emojiStickers.get(r32);
            if (r24 != null) goto L11;
        L13:
            int r25 = r32.size();
            int r42 = 0;
            int r52 = 0;
        L14:
            if (r42 >= r25) goto L19;
            int r62 = this.totalItems + r52;
            int r82 = (r52 / StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0))) + r7;
            TLRPC.Document r92 = r32.get(r42);
            this.cache.put(r62, r92);
            TLRPC.TL_messages_stickerSet r93 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSetById(MediaDataController.getStickerSetId(r92));
            if (r93 == null) goto L18;
            this.cacheParent.put(r62, r93);
        L18:
            this.positionToRow.put(r62, r82);
            r52 = r52 + 1;
            r42 = r42 + 1;
            goto L14
        L19:
            int r26 = (int) Math.ceil(r52 / StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)));
        L20:
            if (r1 >= r26) goto L22;
            this.rowStartPack.put(r7 + r1, Integer.valueOf(r52));
            r1 = r1 + 1;
            goto L20
        L22:
            this.totalItems += r26 * StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0));
            return;
        L11:
            if (_UrlKt.FRAGMENT_ENCODE_SET.equals(r24) == true) goto L13;
            this.positionToEmoji.put(this.totalItems, r24);
            goto L13
        L7:
            r7 = 1;
            goto L8
        L24:
            boolean r33 = this.emojiArrays.isEmpty();
            ArrayList<TLRPC.Document> r83 = this.globalSearchArray;
            if (r83 != null) goto L27;
        L29:
            boolean r84 = false;
        L30:
            SparseArray<Object> r94 = this.cache;
            int r10 = this.totalItems;
            this.totalItems = r10 + 1;
            r94.put(r10, "search");
            if (r2 <= 0) goto L33;
            SparseArray<Object> r63 = this.cache;
            int r95 = this.totalItems;
            this.totalItems = r95 + 1;
            this.foundPacksRow = r95;
            r63.put(r95, "packs");
            int r53 = 2;
        L34:
            if (r33 == true) goto L53;
            SparseArray<Object> r64 = this.cache;
            int r96 = this.totalItems;
            this.totalItems = r96 + 1;
            r64.put(r96, LocaleController.getString(R.string.StickerOrEmojiSearchResult));
            int r54 = r53 + 1;
            int r65 = this.emojiArrays.size();
            int r97 = 0;
            int r102 = 0;
        L36:
            if (r97 >= r65) goto L49;
            ArrayList<TLRPC.Document> r11 = this.emojiArrays.get(r97);
            String r12 = this.emojiStickers.get(r11);
            if (r12 != null) goto L40;
        L42:
            int r122 = r11.size();
            int r13 = r1;
        L43:
            if (r13 >= r122) goto L48;
            int r14 = this.totalItems + r102;
            int r15 = (r102 / StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0))) + r54;
            TLRPC.Document r16 = r11.get(r13);
            this.cache.put(r14, r16);
            int r17 = r2;
            TLRPC.TL_messages_stickerSet r18 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSetById(MediaDataController.getStickerSetId(r16));
            if (r18 == null) goto L47;
            this.cacheParent.put(r14, r18);
        L47:
            this.positionToRow.put(r14, r15);
            r102 = r102 + 1;
            r13 = r13 + 1;
            r2 = r17;
            goto L43
        L48:
            r97 = r97 + 1;
            r1 = 0;
            goto L36
        L40:
            if (r4.equals(r12) == true) goto L42;
            this.positionToEmoji.put(this.totalItems + r102, r12);
            r4 = r12;
            goto L42
        L49:
            int r172 = r2;
            int r162 = 1;
            int r19 = (int) Math.ceil(r102 / StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)));
            int r27 = 0;
        L50:
            if (r27 >= r19) goto L52;
            this.rowStartPack.put(r54 + r27, Integer.valueOf(r102));
            r27 = r27 + 1;
            goto L50
        L52:
            this.totalItems += StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)) * r19;
            r53 = r54 + r19;
        L54:
            if (r84 == false) goto L68;
            SparseArray<Object> r110 = this.cache;
            int r28 = this.totalItems;
            this.totalItems = r28 + 1;
            r110.put(r28, LocaleController.getString(R.string.StickerOrEmojiGlobalSearchResult));
            int r55 = r53 + 1;
            String r111 = this.emojiStickers.get(this.globalSearchArray);
            if (r111 == null) goto L58;
            this.positionToEmoji.put(this.totalItems, r111);
        L58:
            int r112 = this.globalSearchArray.size();
            int r29 = 0;
            int r43 = 0;
        L59:
            if (r29 >= r112) goto L64;
            int r66 = this.totalItems + r43;
            int r72 = (r43 / StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0))) + r55;
            TLRPC.Document r98 = this.globalSearchArray.get(r29);
            this.cache.put(r66, r98);
            TLRPC.TL_messages_stickerSet r99 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSetById(MediaDataController.getStickerSetId(r98));
            if (r99 == null) goto L63;
            this.cacheParent.put(r66, r99);
        L63:
            this.positionToRow.put(r66, r72);
            r43 = r43 + 1;
            r29 = r29 + 1;
            goto L59
        L64:
            int r113 = (int) Math.ceil(r43 / StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)));
            int r210 = 0;
        L65:
            if (r210 >= r113) goto L67;
            this.rowStartPack.put(r55 + r210, Integer.valueOf(r43));
            r210 = r210 + 1;
            goto L65
        L67:
            this.totalItems += r113 * StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0));
        L68:
            if (r33 == false) goto L86;
            if (r84 == true) goto L87;
            if (r172 != 0) goto L88;
            this.totalItems = r162;
            return;
        L88:
            return;
        L87:
            return;
        L86:
            return;
        L53:
            r172 = r2;
            r162 = 1;
            goto L54
        L33:
            r53 = 1;
            goto L34
        L27:
            if (r83.isEmpty() == true) goto L29;
            r84 = true;
            goto L30
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r1 = this.totalItems;
            if (r1 != 1) goto L5;
            return 2;
        L5:
            return r1 + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r5) {
            if (this.selectedPackId == 0) goto L9;
            if (r5 != (getItemCount() - 1)) goto L9;
            return 8;
        L9:
            if (r5 != this.foundPacksRow) goto L12;
            return 7;
        L12:
            if (r5 != 0) goto L15;
            return 4;
        L15:
            if (r5 == 1) goto L17;
        L20:
            Object r4 = this.cache.get(r5);
            if (r4 != null) goto L23;
            return 1;
        L23:
            if ((r4 instanceof TLRPC.Document) == false) goto L27;
            return 0;
        L27:
            if ((r4 instanceof TLRPC.StickerSetCovered) == false) goto L30;
            return 3;
        L30:
            return 2;
        L17:
            if (this.totalItems != 1) goto L20;
            return 5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 7) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            rebuild();
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            int r0 = r10.getItemViewType();
            boolean r1 = true;
            char r12 = 1;
            int r13 = 1;
            r1 = true;
            if (r0 == 0) goto L94;
            Integer r3 = null;
            if (r0 != 1) goto L7;
            EmptyCell r102 = (EmptyCell) r10.itemView;
            if (r11 != this.totalItems) goto L92;
            int r112 = this.positionToRow.get(r11 - 1, Integer.MIN_VALUE);
            if (r112 != Integer.MIN_VALUE) goto L74;
            r102.setHeight(1);
            return;
        L74:
            Object r113 = this.rowStartPack.get(r112);
            if ((r113 instanceof TLRPC.TL_messages_stickerSet) == false) goto L78;
            r3 = Integer.valueOf(((TLRPC.TL_messages_stickerSet) r113).documents.size());
        L80:
            if (r3 != null) goto L84;
            r102.setHeight(1);
            return;
        L84:
            if (r3.intValue() != 0) goto L87;
            r102.setHeight(AndroidUtilities.dp(8.0f));
            return;
        L87:
            int r114 = EmojiView.m10363$$Nest$fgetpager(this.this$0).getHeight() - (((int) Math.ceil(r3.intValue() / StickersGridAdapter.m10550$$Nest$fgetstickersPerRow(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)))) * AndroidUtilities.dp(82.0f));
            if (r114 <= 0) goto L90;
            r13 = r114;
        L90:
            r102.setHeight(r13);
            return;
        L78:
            if ((r113 instanceof Integer) == false) goto L80;
            r3 = (Integer) r113;
            goto L80
        L92:
            r102.setHeight(AndroidUtilities.dp(82.0f));
            return;
        L7:
            if (r0 != 2) goto L9;
            StickerSetNameCell r103 = (StickerSetNameCell) r10.itemView;
            Object r115 = this.cache.get(r11);
            if ((r115 instanceof TLRPC.TL_messages_stickerSet) == false) goto L65;
            TLRPC.TL_messages_stickerSet r116 = (TLRPC.TL_messages_stickerSet) r115;
            if (TextUtils.isEmpty(this.searchQuery) == false) goto L48;
        L54:
            Integer r02 = this.localPacksByName.get(r116);
            TLRPC.StickerSet r117 = r116.set;
            if (r117 == null) goto L62;
            if (r02 == null) goto L62;
            String r118 = r117.title;
            int r03 = r02.intValue();
            if (TextUtils.isEmpty(this.searchQuery) == true) goto L60;
            int r9 = this.searchQuery.length();
        L61:
            r103.setText(r118, 0, r03, r9);
            goto L62
        L60:
            r9 = 0;
        L62:
            r103.setUrl(null, 0);
            return;
        L48:
            if (this.localPacksByShortName.containsKey(r116) == false) goto L54;
            TLRPC.StickerSet r04 = r116.set;
            if (r04 == null) goto L52;
            r103.setText(r04.title, 0);
        L52:
            r103.setUrl(r116.set.short_name, this.searchQuery.length());
            return;
        L65:
            if ((r115 instanceof String) == false) goto L104;
            r103.setText((String) r115, 0);
            r103.setUrl(null, 0);
            return;
        L104:
            return;
        L9:
            if (r0 != 3) goto L105;
            TLRPC.StickerSetCovered r4 = (TLRPC.StickerSetCovered) this.cache.get(r11);
            FeaturedStickerSetInfoCell r32 = (FeaturedStickerSetInfoCell) r10.itemView;
            if (EmojiView.m10359$$Nest$fgetinstallingStickerSets(this.this$0).indexOfKey(r4.set.id) < 0) goto L14;
            boolean r104 = true;
        L16:
            if (EmojiView.m10369$$Nest$fgetremovingStickerSets(this.this$0).indexOfKey(r4.set.id) >= 0) goto L19;
            r12 = 0;
        L19:
            if (r104 == true) goto L21;
            if (r12 != 0) goto L21;
        L29:
            r32.setAddDrawProgress(r104, false);
            if (TextUtils.isEmpty(this.searchQuery) == false) goto L33;
            int r105 = -1;
        L32:
            int r7 = r105;
            if (r7 < 0) goto L37;
            r32.setStickerSet(r4, false, false, r7, this.searchQuery.length());
            return;
        L37:
            r32.setStickerSet(r4, false);
            if (TextUtils.isEmpty(this.searchQuery) == false) goto L40;
            return;
        L40:
            if (AndroidUtilities.indexOfIgnoreCase(r4.set.short_name, this.searchQuery) != 0) goto L103;
            r32.setUrl(r4.set.short_name, this.searchQuery.length());
            return;
        L103:
            return;
        L33:
            r105 = AndroidUtilities.indexOfIgnoreCase(r4.set.title, this.searchQuery);
        L21:
            if (r104 == true) goto L23;
        L25:
            if (r12 == 0) goto L29;
            if (r32.isInstalled() == true) goto L29;
            EmojiView.m10369$$Nest$fgetremovingStickerSets(this.this$0).remove(r4.set.id);
            goto L29
        L23:
            if (r32.isInstalled() == false) goto L25;
            EmojiView.m10359$$Nest$fgetinstallingStickerSets(this.this$0).remove(r4.set.id);
            r104 = false;
            goto L29
        L14:
            r104 = false;
            goto L16
        L105:
            return;
        L94:
            TLRPC.Document r42 = (TLRPC.Document) this.cache.get(r11);
            StickerEmojiCell r33 = (StickerEmojiCell) r10.itemView;
            r33.setSticker(r42, null, this.cacheParent.get(r11), this.positionToEmoji.get(r11), false);
            if (EmojiView.m10367$$Nest$fgetrecentStickers(this.this$0).contains(r42) == false) goto L97;
        L100:
            r33.setRecent(r1);
            return;
        L97:
            if (EmojiView.m10329$$Nest$fgetfavouriteStickers(this.this$0).contains(r42) == true) goto L100;
            r1 = false;
            goto L100
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r13, int r14) {
            switch(r14) {
                case 0: goto L14;
                case 1: goto L13;
                case 2: goto L12;
                case 3: goto L11;
                case 4: goto L10;
                case 5: goto L8;
                case 6: goto L4;
                case 7: goto L7;
                case 8: goto L5;
                default: goto L4;
            };
        L4:
            View r12 = null;
        L16:
            return new RecyclerListView.Holder(r12);
        L5:
            View r132 = new View(this.this$0.getContext());
            r132.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(68.0f)));
        L6:
            r12 = r132;
            goto L16
        L7:
            r12 = this.foundPacksListView;
            r12.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(79.0f)));
            goto L16
        L8:
            FrameLayout r142 = new AnonymousClass5(this, this.context);
            ImageView r1 = new ImageView(this.context);
            r1.setScaleType(ImageView.ScaleType.CENTER);
            r1.setImageResource(R.drawable.stickers_empty);
            EmojiView r3 = this.this$0;
            int r4 = Theme.key_chat_emojiPanelEmptyText;
            r1.setColorFilter(new PorterDuffColorFilter(EmojiView.m10432$$Nest$mgetThemedColor(r3, r4), PorterDuff.Mode.MULTIPLY));
            r1.setTranslationY(-AndroidUtilities.dp(24.0f));
            r142.addView(r1, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 42.0f, 0.0f, 28.0f));
            TextView r15 = new TextView(this.context);
            r15.setText(LocaleController.getString(R.string.NoStickersFound));
            r15.setTextSize(1, 16.0f);
            r15.setTextColor(EmojiView.m10432$$Nest$mgetThemedColor(this.this$0, r4));
            r142.addView(r15, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 42.0f, 0.0f, 9.0f));
            r142.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        L9:
            r12 = r142;
            goto L16
        L10:
            r132 = new View(this.context);
            r132.setLayoutParams(new RecyclerView.LayoutParams(-1, EmojiView.m10373$$Nest$fgetsearchFieldHeight(this.this$0)));
            goto L6
        L11:
            FeaturedStickerSetInfoCell r16 = new FeaturedStickerSetInfoCell(this.context, 17, false, true, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            r16.setAddOnClickListener(new EmojiView$StickersSearchGridAdapter$$ExternalSyntheticLambda0(this));
            r12 = r16;
            goto L16
        L12:
            r132 = new StickerSetNameCell(this.context, false, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0), EmojiView.m10349$$Nest$fgetglassDesign(this.this$0));
            goto L6
        L13:
            r132 = new EmptyCell(this.context);
            goto L6
        L14:
            r142 = new AnonymousClass4(this, this.context, true, EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
            goto L9
        }

        public void resetSelectedPackId() {
            int r0 = this.foundPacksListView.getChildCount();
            int r2 = 0;
        L4:
            if (r2 >= r0) goto L6;
            ((FoundStickerPackCell) this.foundPacksListView.getChildAt(r2)).setSelected(false, true);
            r2 = r2 + 1;
            goto L4
        L6:
            this.selectedPackId = 0;
            EmojiView.m10297$$Nest$fgetanimatorSearchStickerPackSelected(this.this$0).setValue(false, true);
            notifyDataSetChanged();
        }

        public void search(String r4, boolean r5) {
            if (this.reqId == 0) goto L6;
            ConnectionsManager.getInstance(this.this$0.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = 0;
        L6:
            if (this.reqId2 == 0) goto L9;
            ConnectionsManager.getInstance(this.this$0.currentAccount).cancelRequest(this.reqId2, true);
            this.reqId2 = 0;
        L9:
            if (TextUtils.isEmpty(r4) == false) goto L14;
            this.searchQuery = null;
            this.localPacks.clear();
            this.emojiStickers.clear();
            this.globalSearchArray = new ArrayList();
            if (EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0).getAdapter() == EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0)) goto L13;
            EmojiView.m10381$$Nest$fgetstickersGridView(this.this$0).setAdapter(EmojiView.m10380$$Nest$fgetstickersGridAdapter(this.this$0));
        L13:
            this.selectedPackId = 0;
            EmojiView.m10297$$Nest$fgetanimatorSearchStickerPackSelected(this.this$0).setValue(false, true);
            notifyDataSetChanged();
            EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$0).showProgress(false);
        L15:
            AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
            AndroidUtilities.runOnUIThread(this.searchRunnable, 300);
            return;
        L14:
            this.searchQuery = r4.toLowerCase();
            EmojiView.m10383$$Nest$fgetstickersSearchField(this.this$0).showProgress(true);
            goto L15
        }
    }

    public class Tab {
        final /* synthetic */ EmojiView this$0;
        int type;
        View view;

        private Tab(EmojiView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Tab(EmojiView r1, EmojiViewIA r2) {
            this(r1);
        }
    }

    public class TrendingAdapter extends RecyclerListView.SelectionAdapter {
        private boolean emoji;
        final /* synthetic */ EmojiView this$0;

        /* renamed from: org.telegram.ui.Components.EmojiView$TrendingAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ TrendingAdapter this$1;

            public AnonymousClass1(TrendingAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.BackupImageView, android.view.View
            public void onDraw(Canvas r6) {
                super.onDraw(r6);
                if (TrendingAdapter.m10573$$Nest$fgetemoji(this.this$1) == false) goto L5;
                return;
            L5:
                if (MediaDataController.getInstance(this.this$1.this$0.currentAccount).isStickerPackUnread(TrendingAdapter.m10573$$Nest$fgetemoji(this.this$1), ((TLRPC.StickerSetCovered) getTag()).set.id) == true) goto L7;
                return;
            L7:
                if (EmojiView.m10309$$Nest$fgetdotPaint(this.this$1.this$0) == null) goto L12;
                r6.drawCircle(r6.getWidth() - AndroidUtilities.dp(8.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(3.0f), EmojiView.m10309$$Nest$fgetdotPaint(this.this$1.this$0));
                return;
            }
        }

        /* renamed from: -$$Nest$fgetemoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10573$$Nest$fgetemoji(TrendingAdapter r0) {
            return r0.emoji;
        }

        public TrendingAdapter(EmojiView r1, boolean r2) {
            this.this$0 = r1;
            this.emoji = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = this.emoji;
            EmojiView r1 = this.this$0;
            if (r0 == false) goto L5;
            ArrayList r12 = EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(r1);
        L7:
            return r12.size();
        L5:
            r12 = EmojiView.m10331$$Nest$fgetfeaturedStickerSets(r1);
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            BackupImageView r0 = (BackupImageView) r9.itemView;
            boolean r92 = this.emoji;
            EmojiView r1 = this.this$0;
            if (r92 == false) goto L5;
            ArrayList r93 = EmojiView.m10330$$Nest$fgetfeaturedEmojiSets(r1);
        L6:
            TLRPC.StickerSetCovered r5 = (TLRPC.StickerSetCovered) r93.get(r10);
            r0.setTag(r5);
            ColorFilter r102 = null;
            if ((r5 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L10;
            ArrayList<TLRPC.Document> r94 = ((TLRPC.TL_stickerSetFullCovered) r5).documents;
        L16:
            TLRPC.Document r2 = r5.cover;
            if (r2 != null) goto L35;
            if (r94 != null) goto L21;
        L34:
            r2 = null;
            goto L35
        L21:
            if (r94.isEmpty() == true) goto L34;
            if (r5.set == null) goto L31;
            int r22 = 0;
        L26:
            if (r22 >= r94.size()) goto L31;
            if (r94.get(r22).id == r5.set.thumb_document_id) goto L29;
            r22 = r22 + 1;
            goto L26
        L29:
            r2 = r94.get(r22);
        L32:
            if (r2 != null) goto L35;
            r2 = r94.get(0);
        L31:
            r2 = null;
        L35:
            if (r2 != null) goto L38;
            return;
        L38:
            if (this.emoji == true) goto L40;
        L43:
            TLObject r95 = FileLoader.getClosestPhotoSizeWithSize(r5.set.thumbs, 90);
            SvgHelper.SvgDrawable r4 = DocumentObject.getSvgThumb(r5.set.thumbs, Theme.key_emptyListPlaceholder, 0.2f);
            if (r4 == null) goto L46;
            r4.overrideWidthAndHeight(512, 512);
        L46:
            if (r95 != null) goto L48;
        L49:
            r95 = r2;
        L50:
            boolean r12 = r95 instanceof TLRPC.Document;
            if (r12 == false) goto L55;
            ImageLocation r96 = ImageLocation.getForDocument(FileLoader.getClosestPhotoSizeWithSize(r2.thumbs, 90), r2);
        L53:
            ImageLocation r3 = r96;
            if (r3 != null) goto L60;
            return;
        L60:
            if (this.emoji == false) goto L62;
            int r8 = LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD;
        L64:
            if (LiteMode.isEnabled(r8) == true) goto L66;
            String r82 = "30_30_firstframe";
        L67:
            if (r12 == true) goto L69;
        L73:
            String r23 = r82;
            if (r3.imageType != 1) goto L83;
            r0.setImage(r3, r23, "tgs", r4, r5);
            return;
        L83:
            r0.setImage(r3, null, "webp", r4, r5);
            return;
        L69:
            if (MessageObject.isAnimatedStickerDocument(r2, true) == false) goto L71;
        L74:
            if (r4 == null) goto L77;
            r0.setImage(ImageLocation.getForDocument(r2), r82, r4, 0, r5);
            return;
        L77:
            r0.setImage(ImageLocation.getForDocument(r2), r82, r3, null, 0, r5);
            return;
        L71:
            if (MessageObject.isVideoSticker(r2) == false) goto L73;
        L66:
            r82 = "30_30";
            goto L67
        L62:
            r8 = 1;
            goto L64
        L55:
            if ((r95 instanceof TLRPC.PhotoSize) == false) goto L87;
            r96 = ImageLocation.getForSticker((TLRPC.PhotoSize) r95, r2, r5.set.thumb_version);
            goto L53
        L87:
            return;
        L48:
            if (MessageObject.isVideoSticker(r2) == false) goto L50;
        L40:
            if (MessageObject.isTextColorEmoji(r2) == false) goto L42;
            r102 = Theme.getAnimatedEmojiColorFilter(EmojiView.m10370$$Nest$fgetresourcesProvider(this.this$0));
        L42:
            r0.setColorFilter(r102);
            goto L43
        L10:
            if ((r5 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L15;
            TLRPC.TL_messages_stickerSet r97 = MediaDataController.getInstance(this.this$0.currentAccount).getStickerSet(MediaDataController.getInputStickerSet(r5.set), false);
            if (r97 != null) goto L14;
            r94 = null;
            goto L16
        L14:
            r94 = r97.documents;
            goto L16
        L15:
            r94 = r5.covers;
            goto L16
        L5:
            r93 = EmojiView.m10331$$Nest$fgetfeaturedStickerSets(r1);
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, this.this$0.getContext());
            r22.setSize(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
            r22.setLayerNum(1);
            r22.setAspectFit(true);
            r22.setLayoutParams(new RecyclerView.LayoutParams(AndroidUtilities.dp(34.0f), AndroidUtilities.dp(34.0f)));
            return new RecyclerListView.Holder(r22);
        }
    }

    public class TrendingListView extends RecyclerListView {
        final /* synthetic */ EmojiView this$0;

        /* renamed from: org.telegram.ui.Components.EmojiView$TrendingListView$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayoutManager {
            final /* synthetic */ TrendingListView this$1;
            final /* synthetic */ EmojiView val$this$0;

            public AnonymousClass1(TrendingListView r1, Context r2, EmojiView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        }

        public TrendingListView(EmojiView r2, Context r3, RecyclerView.Adapter r4) {
            this.this$0 = r2;
            super(r3);
            setNestedScrollingEnabled(true);
            setSelectorRadius(AndroidUtilities.dp(8.0f));
            setSelectorDrawableColor(getThemedColor(Theme.key_listSelector));
            setTag(9);
            setItemAnimator(null);
            setLayoutAnimation(null);
            AnonymousClass1 r0 = new AnonymousClass1(this, r3, r2);
            r0.setOrientation(0);
            setLayoutManager(r0);
            setAdapter(r4);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (getParent() == null) goto L15;
            if (getParent().getParent() == null) goto L15;
            ViewParent r0 = getParent().getParent();
            if (canScrollHorizontally(-1) == false) goto L9;
        L12:
            boolean r1 = true;
        L13:
            r0.requestDisallowInterceptTouchEvent(r1);
            EmojiView.m10363$$Nest$fgetpager(this.this$0).requestDisallowInterceptTouchEvent(true);
            goto L15
        L9:
            if (canScrollHorizontally(1) == true) goto L12;
            r1 = false;
        L15:
            return super.onInterceptTouchEvent(r4);
        }
    }

    public class TypedScrollListener extends RecyclerView.OnScrollListener {
        private boolean smoothScrolling;
        final /* synthetic */ EmojiView this$0;
        private final int type;

        public TypedScrollListener(EmojiView r1, int r2) {
            this.this$0 = r1;
            this.type = r2;
        }

        private void checkSearchFieldScroll() {
            int r0 = this.type;
            if (r0 != 0) goto L5;
            EmojiView.m10426$$Nest$mcheckStickersSearchFieldScroll(this.this$0, false);
            return;
        L5:
            if (r0 != 1) goto L7;
            EmojiView.m10421$$Nest$mcheckEmojiSearchFieldScroll(this.this$0, false);
            return;
        L7:
            if (r0 == 2) goto L9;
            return;
        L9:
            EmojiView.m10423$$Nest$mcheckGifSearchFieldScroll(this.this$0, false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r2.getLayoutManager().isSmoothScrolling() == false) goto L7;
            this.smoothScrolling = true;
            return;
        L7:
            if (r3 == 0) goto L9;
            if (r3 != 1) goto L25;
            if (EmojiView.m10357$$Nest$fgetignoreStickersScroll(this.this$0) == false) goto L20;
            EmojiView.m10411$$Nest$fputignoreStickersScroll(this.this$0, false);
        L20:
            SearchField r32 = EmojiView.m10431$$Nest$mgetSearchFieldForType(this.this$0, this.type);
            if (r32 == null) goto L23;
            r32.hideKeyboard();
        L23:
            this.smoothScrolling = false;
        L25:
            if (this.smoothScrolling == true) goto L28;
            EmojiView.m10451$$Nest$mstopAnimatingTabsY(this.this$0, this.type);
        L28:
            if (this.type == 0) goto L30;
            return;
        L30:
            if (EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0) != null) goto L32;
            EmojiView.m10428$$Nest$mcreateStickersChooseActionTracker(this.this$0);
        L32:
            EmojiView.m10303$$Nest$fgetchooseStickerActionTracker(this.this$0).doSomeAction();
            return;
        L9:
            if (this.smoothScrolling == true) goto L12;
            EmojiView.m10419$$Nest$manimateTabsY(this.this$0, this.type);
        L12:
            if (EmojiView.m10357$$Nest$fgetignoreStickersScroll(this.this$0) == false) goto L14;
            EmojiView.m10411$$Nest$fputignoreStickersScroll(this.this$0, false);
        L14:
            this.smoothScrolling = false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            EmojiView.m10425$$Nest$mcheckScroll(this.this$0, this.type);
            EmojiView.m10427$$Nest$mcheckTabsY(this.this$0, this.type, r3);
            checkSearchFieldScroll();
            if (this.smoothScrolling == true) goto L6;
            EmojiView.m10420$$Nest$mcheckBottomTabScroll(this.this$0, r3);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$1MoStaX3nQFfB3OtlcX1AqjtZKU(EmojiView r0, int r1) {
        r0.lambda$postBackspaceRunnable$22(r1);
    }

    /* renamed from: $r8$lambda$36RvouQER08BJyVo35z-c5hCjFo, reason: not valid java name */
    public static /* synthetic */ void m10281$r8$lambda$36RvouQER08BJyVo35zc5hCjFo(EmojiView r0, TLObject r1, TLRPC.Document r2, boolean r3, FoundStickerPackButton r4, TLRPC.StickerSet r5, boolean r6, View r7) {
        r0.lambda$setFoundPackButtonText$27(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$4RHjkQcNobqRRzwa1aFLOE98BmA(EmojiView r0) {
        r0.lambda$new$5();
    }

    public static /* synthetic */ void $r8$lambda$5ZNgWfp51UkVXSa1ylcveozD22E(EmojiView r0, View r1, int r2) {
        r0.lambda$new$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$6e2JY5iuHingjNGDVSIOMsu6818(EmojiView r0) {
        r0.lambda$new$25();
    }

    /* renamed from: $r8$lambda$9RvAdIzwsEX-XFjRTFYv4ep7_OQ, reason: not valid java name */
    public static /* synthetic */ void m10282$r8$lambda$9RvAdIzwsEXXFjRTFYv4ep7_OQ(EmojiView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$DusovaWoygscFh5cJ_1F9DjEG3c(EmojiView r0) {
        r0.lambda$onOpen$23();
    }

    public static /* synthetic */ void $r8$lambda$EZxd3ekU64WmT8kqo2WRB0ugZX8(EmojiView r0, int r1) {
        r0.invalidateBlurCaptures(r1);
    }

    /* renamed from: $r8$lambda$E_C4nuq79yzA8y8yQBXQ-hk0we4, reason: not valid java name */
    public static /* synthetic */ boolean m10283$r8$lambda$E_C4nuq79yzA8y8yQBXQhk0we4(EmojiView r0, Theme.ResourcesProvider r1, View r2, MotionEvent r3) {
        return r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$HC-mj9QXykA3pAjgNzPHl9-kN5Y, reason: not valid java name */
    public static /* synthetic */ void m10284$r8$lambda$HCmj9QXykA3pAjgNzPHl9kN5Y(EmojiView r0, long r1, TLRPC.Document r3, String r4, ImageViewEmoji r5) {
        r0.lambda$sendEmoji$0(r1, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$JRW1OSUBcme2KrtriYdx_g5rgII(EmojiView r0, Theme.ResourcesProvider r1, View r2, MotionEvent r3) {
        return r0.lambda$new$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$KSWcTlz4d6hZU235YMOAN5GCV7M(EmojiView r0, View r1) {
        r0.lambda$new$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$LMsIqpgowqHBJrTn13QaDkQuXok(EmojiView r0, View r1) {
        r0.lambda$new$16(r1);
    }

    /* renamed from: $r8$lambda$MwzNsmLCTt8xO3l81-b_uoQiPPg, reason: not valid java name */
    public static /* synthetic */ void m10285$r8$lambda$MwzNsmLCTt8xO3l81b_uoQiPPg(EmojiView r0, Integer r1, Integer r2) {
        r0.lambda$new$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Pku1CKgUeyVrwD0E0C8til5qfPg(EmojiView r0, FoundStickerPackButton r1, TLObject r2, TLRPC.StickerSet r3, TLRPC.Document r4, boolean r5) {
        r0.lambda$setFoundPackButtonText$26(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$QgvpfMFqNjYqAUCQmi9fZc2cUmM(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$RG8cjXrYXRdPqSaGHB1J9XkrzS4(EmojiView r0, View r1) {
        r0.lambda$new$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$S0myWo1YOKOXo63fdEljksHrBpY(EmojiView r0) {
        r0.lambda$sendEmoji$1();
    }

    public static /* synthetic */ void $r8$lambda$VSOxdnLV9etUyb9Gxkded6TTl34(EmojiView r0, int r1, ValueAnimator r2) {
        r0.lambda$animateTabsY$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$f1FvPK6hXJ4KLjlkQAttHi4T7rc(EmojiView r0, TLRPC.InputStickerSet r1, TLRPC.Document r2) {
        r0.lambda$openEmojiPackAlert$19(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$hbn8xDm5MV6U9NZTvv4qzWCWtv0(EmojiView r0, View r1, int r2) {
        return r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ieq7zK2n0rytEBejyTkF6_h4MvA(EmojiView r0) {
        r0.openPremiumAnimatedEmojiFeature();
    }

    public static /* synthetic */ void $r8$lambda$oQcSuEW7pNa7h8071yCCAWdr_TE(EmojiView r0, boolean r1, boolean r2) {
        r0.lambda$showStickerBanHint$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qDwhmmHBFYisGMWaAfLJPagBRwI(EmojiView r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.lambda$new$20(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$qGdqUdKFfTEW0vCuJy0dPpfmPi4(EmojiView r0, View r1, int r2) {
        r0.lambda$new$11(r1, r2);
    }

    public static /* synthetic */ Integer $r8$lambda$roMuldUBeMpvL2VV1XisjntU8Zo(Integer r0) {
        return 0;
    }

    public static /* synthetic */ boolean $r8$lambda$rqzbZfavLMvZbspdUfkEx8v9zb4(EmojiView r0, Theme.ResourcesProvider r1, View r2, MotionEvent r3) {
        return r0.lambda$new$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$slx7PV_t8_S8Hnz8_iv8lKGTsBc(EmojiView r0, int r1) {
        r0.lambda$new$13(r1);
    }

    /* renamed from: $r8$lambda$t0Hdnd_eSoTABwXPIwtaHH9C-fA, reason: not valid java name */
    public static /* synthetic */ void m10286$r8$lambda$t0Hdnd_eSoTABwXPIwtaHH9CfA(EmojiView r0, int r1) {
        r0.lambda$new$9(r1);
    }

    /* renamed from: $r8$lambda$u9_MSfe-8CCyKebkB-taXZlHCuI, reason: not valid java name */
    public static /* synthetic */ void m10287$r8$lambda$u9_MSfe8CCyKebkBtaXZlHCuI(EmojiView r0, Canvas r1, RectF r2) {
        r0.lambda$new$18(r1, r2);
    }

    /* renamed from: -$$Nest$fgetallowAnimatedEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10288$$Nest$fgetallowAnimatedEmoji(EmojiView r0) {
        return r0.allowAnimatedEmoji;
    }

    /* renamed from: -$$Nest$fgetallowEmojisForNonPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10289$$Nest$fgetallowEmojisForNonPremium(EmojiView r0) {
        return r0.allowEmojisForNonPremium;
    }

    /* renamed from: -$$Nest$fgetanimateExpandFromButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m10290$$Nest$fgetanimateExpandFromButton(EmojiView r0) {
        return r0.animateExpandFromButton;
    }

    /* renamed from: -$$Nest$fgetanimateExpandFromPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10291$$Nest$fgetanimateExpandFromPosition(EmojiView r0) {
        return r0.animateExpandFromPosition;
    }

    /* renamed from: -$$Nest$fgetanimateExpandStartTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m10292$$Nest$fgetanimateExpandStartTime(EmojiView r2) {
        return r2.animateExpandStartTime;
    }

    /* renamed from: -$$Nest$fgetanimateExpandToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10293$$Nest$fgetanimateExpandToPosition(EmojiView r0) {
        return r0.animateExpandToPosition;
    }

    /* renamed from: -$$Nest$fgetanimatedEmojiDrawables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m10294$$Nest$fgetanimatedEmojiDrawables(EmojiView r0) {
        return r0.animatedEmojiDrawables;
    }

    /* renamed from: -$$Nest$fgetanimatedEmojiTextColorFilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PorterDuffColorFilter m10295$$Nest$fgetanimatedEmojiTextColorFilter(EmojiView r0) {
        return r0.animatedEmojiTextColorFilter;
    }

    /* renamed from: -$$Nest$fgetanimatorSearchEmojiPackSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m10296$$Nest$fgetanimatorSearchEmojiPackSelected(EmojiView r0) {
        return r0.animatorSearchEmojiPackSelected;
    }

    /* renamed from: -$$Nest$fgetanimatorSearchStickerPackSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m10297$$Nest$fgetanimatorSearchStickerPackSelected(EmojiView r0) {
        return r0.animatorSearchStickerPackSelected;
    }

    /* renamed from: -$$Nest$fgetbackspaceButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m10298$$Nest$fgetbackspaceButton(EmojiView r0) {
        return r0.backspaceButton;
    }

    /* renamed from: -$$Nest$fgetbackspaceOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10299$$Nest$fgetbackspaceOnce(EmojiView r0) {
        return r0.backspaceOnce;
    }

    /* renamed from: -$$Nest$fgetbottomInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10300$$Nest$fgetbottomInset(EmojiView r0) {
        return r0.bottomInset;
    }

    /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10301$$Nest$fgetbulletinContainer(EmojiView r0) {
        return r0.bulletinContainer;
    }

    /* renamed from: -$$Nest$fgetcheckExpandStickerTabsRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10302$$Nest$fgetcheckExpandStickerTabsRunnable(EmojiView r0) {
        return r0.checkExpandStickerTabsRunnable;
    }

    /* renamed from: -$$Nest$fgetchooseStickerActionTracker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChooseStickerActionTracker m10303$$Nest$fgetchooseStickerActionTracker(EmojiView r0) {
        return r0.chooseStickerActionTracker;
    }

    /* renamed from: -$$Nest$fgetcolorPickerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiColorPickerWindow m10304$$Nest$fgetcolorPickerView(EmojiView r0) {
        return r0.colorPickerView;
    }

    /* renamed from: -$$Nest$fgetcontentPreviewViewerDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContentPreviewViewer.ContentPreviewViewerDelegate m10305$$Nest$fgetcontentPreviewViewerDelegate(EmojiView r0) {
        return r0.contentPreviewViewerDelegate;
    }

    /* renamed from: -$$Nest$fgetcurrentTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10306$$Nest$fgetcurrentTabs(EmojiView r0) {
        return r0.currentTabs;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiViewDelegate m10307$$Nest$fgetdelegate(EmojiView r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdisableStickerEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10308$$Nest$fgetdisableStickerEditor(EmojiView r0) {
        return r0.disableStickerEditor;
    }

    /* renamed from: -$$Nest$fgetdotPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m10309$$Nest$fgetdotPaint(EmojiView r0) {
        return r0.dotPaint;
    }

    /* renamed from: -$$Nest$fgetdragListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DragListener m10310$$Nest$fgetdragListener(EmojiView r0) {
        return r0.dragListener;
    }

    /* renamed from: -$$Nest$fgetemojiAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiGridAdapter m10311$$Nest$fgetemojiAdapter(EmojiView r0) {
        return r0.emojiAdapter;
    }

    /* renamed from: -$$Nest$fgetemojiAddPackButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FoundStickerPackButton m10312$$Nest$fgetemojiAddPackButton(EmojiView r0) {
        return r0.emojiAddPackButton;
    }

    /* renamed from: -$$Nest$fgetemojiContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10313$$Nest$fgetemojiContainer(EmojiView r0) {
        return r0.emojiContainer;
    }

    /* renamed from: -$$Nest$fgetemojiGridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiGridView m10314$$Nest$fgetemojiGridView(EmojiView r0) {
        return r0.emojiGridView;
    }

    /* renamed from: -$$Nest$fgetemojiLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m10315$$Nest$fgetemojiLayoutManager(EmojiView r0) {
        return r0.emojiLayoutManager;
    }

    /* renamed from: -$$Nest$fgetemojiSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiSearchAdapter m10316$$Nest$fgetemojiSearchAdapter(EmojiView r0) {
        return r0.emojiSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetemojiSearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchField m10317$$Nest$fgetemojiSearchField(EmojiView r0) {
        return r0.emojiSearchField;
    }

    /* renamed from: -$$Nest$fgetemojiSmoothScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10318$$Nest$fgetemojiSmoothScrolling(EmojiView r0) {
        return r0.emojiSmoothScrolling;
    }

    /* renamed from: -$$Nest$fgetemojiTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiTabsStrip m10319$$Nest$fgetemojiTabs(EmojiView r0) {
        return r0.emojiTabs;
    }

    /* renamed from: -$$Nest$fgetemojiTabsShadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m10320$$Nest$fgetemojiTabsShadow(EmojiView r0) {
        return r0.emojiTabsShadow;
    }

    /* renamed from: -$$Nest$fgetemojiTitles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String[] m10321$$Nest$fgetemojiTitles(EmojiView r0) {
        return r0.emojiTitles;
    }

    /* renamed from: -$$Nest$fgetemojiTouchedView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageViewEmoji m10322$$Nest$fgetemojiTouchedView(EmojiView r0) {
        return r0.emojiTouchedView;
    }

    /* renamed from: -$$Nest$fgetemojiTouchedX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10323$$Nest$fgetemojiTouchedX(EmojiView r0) {
        return r0.emojiTouchedX;
    }

    /* renamed from: -$$Nest$fgetemojiTouchedY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10324$$Nest$fgetemojiTouchedY(EmojiView r0) {
        return r0.emojiTouchedY;
    }

    /* renamed from: -$$Nest$fgetemojipacksProcessed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10325$$Nest$fgetemojipacksProcessed(EmojiView r0) {
        return r0.emojipacksProcessed;
    }

    /* renamed from: -$$Nest$fgetexpandStickersByDragg, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10326$$Nest$fgetexpandStickersByDragg(EmojiView r0) {
        return r0.expandStickersByDragg;
    }

    /* renamed from: -$$Nest$fgetexpandedEmojiSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10327$$Nest$fgetexpandedEmojiSets(EmojiView r0) {
        return r0.expandedEmojiSets;
    }

    /* renamed from: -$$Nest$fgetfavTabNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10328$$Nest$fgetfavTabNum(EmojiView r0) {
        return r0.favTabNum;
    }

    /* renamed from: -$$Nest$fgetfavouriteStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10329$$Nest$fgetfavouriteStickers(EmojiView r0) {
        return r0.favouriteStickers;
    }

    /* renamed from: -$$Nest$fgetfeaturedEmojiSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10330$$Nest$fgetfeaturedEmojiSets(EmojiView r0) {
        return r0.featuredEmojiSets;
    }

    /* renamed from: -$$Nest$fgetfeaturedStickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10331$$Nest$fgetfeaturedStickerSets(EmojiView r0) {
        return r0.featuredStickerSets;
    }

    /* renamed from: -$$Nest$fgetfirstEmojiAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10332$$Nest$fgetfirstEmojiAttach(EmojiView r0) {
        return r0.firstEmojiAttach;
    }

    /* renamed from: -$$Nest$fgetfirstGifAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10333$$Nest$fgetfirstGifAttach(EmojiView r0) {
        return r0.firstGifAttach;
    }

    /* renamed from: -$$Nest$fgetfirstStickersAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10334$$Nest$fgetfirstStickersAttach(EmojiView r0) {
        return r0.firstStickersAttach;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m10335$$Nest$fgetfragment(EmojiView r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetfrozen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10336$$Nest$fgetfrozen(EmojiView r0) {
        return r0.frozen;
    }

    /* renamed from: -$$Nest$fgetgifAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GifAdapter m10337$$Nest$fgetgifAdapter(EmojiView r0) {
        return r0.gifAdapter;
    }

    /* renamed from: -$$Nest$fgetgifCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Map m10338$$Nest$fgetgifCache(EmojiView r0) {
        return r0.gifCache;
    }

    /* renamed from: -$$Nest$fgetgifContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10339$$Nest$fgetgifContainer(EmojiView r0) {
        return r0.gifContainer;
    }

    /* renamed from: -$$Nest$fgetgifFirstEmojiTabNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10340$$Nest$fgetgifFirstEmojiTabNum(EmojiView r0) {
        return r0.gifFirstEmojiTabNum;
    }

    /* renamed from: -$$Nest$fgetgifGridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m10341$$Nest$fgetgifGridView(EmojiView r0) {
        return r0.gifGridView;
    }

    /* renamed from: -$$Nest$fgetgifLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GifLayoutManager m10342$$Nest$fgetgifLayoutManager(EmojiView r0) {
        return r0.gifLayoutManager;
    }

    /* renamed from: -$$Nest$fgetgifRecentTabNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10343$$Nest$fgetgifRecentTabNum(EmojiView r0) {
        return r0.gifRecentTabNum;
    }

    /* renamed from: -$$Nest$fgetgifSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GifAdapter m10344$$Nest$fgetgifSearchAdapter(EmojiView r0) {
        return r0.gifSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetgifSearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchField m10345$$Nest$fgetgifSearchField(EmojiView r0) {
        return r0.gifSearchField;
    }

    /* renamed from: -$$Nest$fgetgifSearchPreloader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GifSearchPreloader m10346$$Nest$fgetgifSearchPreloader(EmojiView r0) {
        return r0.gifSearchPreloader;
    }

    /* renamed from: -$$Nest$fgetgifTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollSlidingTabStrip m10347$$Nest$fgetgifTabs(EmojiView r0) {
        return r0.gifTabs;
    }

    /* renamed from: -$$Nest$fgetgifTrendingTabNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10348$$Nest$fgetgifTrendingTabNum(EmojiView r0) {
        return r0.gifTrendingTabNum;
    }

    /* renamed from: -$$Nest$fgetglassDesign, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10349$$Nest$fgetglassDesign(EmojiView r0) {
        return r0.glassDesign;
    }

    /* renamed from: -$$Nest$fgetglassEngine, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GlassEngine m10350$$Nest$fgetglassEngine(EmojiView r0) {
        return r0.glassEngine;
    }

    /* renamed from: -$$Nest$fgetgroupStickerPackNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10351$$Nest$fgetgroupStickerPackNum(EmojiView r0) {
        return r0.groupStickerPackNum;
    }

    /* renamed from: -$$Nest$fgetgroupStickerPackPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10352$$Nest$fgetgroupStickerPackPosition(EmojiView r0) {
        return r0.groupStickerPackPosition;
    }

    /* renamed from: -$$Nest$fgetgroupStickerSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.TL_messages_stickerSet m10353$$Nest$fgetgroupStickerSet(EmojiView r0) {
        return r0.groupStickerSet;
    }

    /* renamed from: -$$Nest$fgetgroupStickersHidden, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10354$$Nest$fgetgroupStickersHidden(EmojiView r0) {
        return r0.groupStickersHidden;
    }

    /* renamed from: -$$Nest$fgethasChatStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10355$$Nest$fgethasChatStickers(EmojiView r0) {
        return r0.hasChatStickers;
    }

    /* renamed from: -$$Nest$fgetignorePagerScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10356$$Nest$fgetignorePagerScroll(EmojiView r0) {
        return r0.ignorePagerScroll;
    }

    /* renamed from: -$$Nest$fgetignoreStickersScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10357$$Nest$fgetignoreStickersScroll(EmojiView r0) {
        return r0.ignoreStickersScroll;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m10358$$Nest$fgetinfo(EmojiView r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetinstallingStickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m10359$$Nest$fgetinstallingStickerSets(EmojiView r0) {
        return r0.installingStickerSets;
    }

    /* renamed from: -$$Nest$fgetlastSearchKeyboardLanguage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String[] m10360$$Nest$fgetlastSearchKeyboardLanguage(EmojiView r0) {
        return r0.lastSearchKeyboardLanguage;
    }

    /* renamed from: -$$Nest$fgetlocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m10361$$Nest$fgetlocation(EmojiView r0) {
        return r0.location;
    }

    /* renamed from: -$$Nest$fgetneedEmojiSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10362$$Nest$fgetneedEmojiSearch(EmojiView r0) {
        return r0.needEmojiSearch;
    }

    /* renamed from: -$$Nest$fgetpager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPager m10363$$Nest$fgetpager(EmojiView r0) {
        return r0.pager;
    }

    /* renamed from: -$$Nest$fgetpremiumStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10364$$Nest$fgetpremiumStickers(EmojiView r0) {
        return r0.premiumStickers;
    }

    /* renamed from: -$$Nest$fgetpremiumTabNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10365$$Nest$fgetpremiumTabNum(EmojiView r0) {
        return r0.premiumTabNum;
    }

    /* renamed from: -$$Nest$fgetrecentGifs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10366$$Nest$fgetrecentGifs(EmojiView r0) {
        return r0.recentGifs;
    }

    /* renamed from: -$$Nest$fgetrecentStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10367$$Nest$fgetrecentStickers(EmojiView r0) {
        return r0.recentStickers;
    }

    /* renamed from: -$$Nest$fgetrecentTabNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10368$$Nest$fgetrecentTabNum(EmojiView r0) {
        return r0.recentTabNum;
    }

    /* renamed from: -$$Nest$fgetremovingStickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m10369$$Nest$fgetremovingStickerSets(EmojiView r0) {
        return r0.removingStickerSets;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m10370$$Nest$fgetresourcesProvider(EmojiView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m10371$$Nest$fgetscrollableViewNoiseSuppressor(EmojiView r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsearchAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m10372$$Nest$fgetsearchAnimation(EmojiView r0) {
        return r0.searchAnimation;
    }

    /* renamed from: -$$Nest$fgetsearchFieldHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10373$$Nest$fgetsearchFieldHeight(EmojiView r0) {
        return r0.searchFieldHeight;
    }

    /* renamed from: -$$Nest$fgetshouldDrawBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10374$$Nest$fgetshouldDrawBackground(EmojiView r0) {
        return r0.shouldDrawBackground;
    }

    /* renamed from: -$$Nest$fgetstickerAddPackButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FoundStickerPackButton m10375$$Nest$fgetstickerAddPackButton(EmojiView r0) {
        return r0.stickerAddPackButton;
    }

    /* renamed from: -$$Nest$fgetstickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10376$$Nest$fgetstickerSets(EmojiView r0) {
        return r0.stickerSets;
    }

    /* renamed from: -$$Nest$fgetstickerSettingsButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m10377$$Nest$fgetstickerSettingsButton(EmojiView r0) {
        return r0.stickerSettingsButton;
    }

    /* renamed from: -$$Nest$fgetstickersContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10378$$Nest$fgetstickersContainer(EmojiView r0) {
        return r0.stickersContainer;
    }

    /* renamed from: -$$Nest$fgetstickersContainerAttached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10379$$Nest$fgetstickersContainerAttached(EmojiView r0) {
        return r0.stickersContainerAttached;
    }

    /* renamed from: -$$Nest$fgetstickersGridAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickersGridAdapter m10380$$Nest$fgetstickersGridAdapter(EmojiView r0) {
        return r0.stickersGridAdapter;
    }

    /* renamed from: -$$Nest$fgetstickersGridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m10381$$Nest$fgetstickersGridView(EmojiView r0) {
        return r0.stickersGridView;
    }

    /* renamed from: -$$Nest$fgetstickersLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m10382$$Nest$fgetstickersLayoutManager(EmojiView r0) {
        return r0.stickersLayoutManager;
    }

    /* renamed from: -$$Nest$fgetstickersSearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchField m10383$$Nest$fgetstickersSearchField(EmojiView r0) {
        return r0.stickersSearchField;
    }

    /* renamed from: -$$Nest$fgetstickersSearchGridAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickersSearchGridAdapter m10384$$Nest$fgetstickersSearchGridAdapter(EmojiView r0) {
        return r0.stickersSearchGridAdapter;
    }

    /* renamed from: -$$Nest$fgetstickersTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollSlidingTabStrip m10385$$Nest$fgetstickersTab(EmojiView r0) {
        return r0.stickersTab;
    }

    /* renamed from: -$$Nest$fgetstickersTabContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10386$$Nest$fgetstickersTabContainer(EmojiView r0) {
        return r0.stickersTabContainer;
    }

    /* renamed from: -$$Nest$fgetstickersTabOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10387$$Nest$fgetstickersTabOffset(EmojiView r0) {
        return r0.stickersTabOffset;
    }

    /* renamed from: -$$Nest$fgettabsMinusDy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m10388$$Nest$fgettabsMinusDy(EmojiView r0) {
        return r0.tabsMinusDy;
    }

    /* renamed from: -$$Nest$fgettrendingEmojiAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TrendingAdapter m10389$$Nest$fgettrendingEmojiAdapter(EmojiView r0) {
        return r0.trendingEmojiAdapter;
    }

    /* renamed from: -$$Nest$fputanimateExpandFromButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10390$$Nest$fputanimateExpandFromButton(EmojiView r0, View r1) {
        r0.animateExpandFromButton = r1;
    }

    /* renamed from: -$$Nest$fputanimateExpandFromPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10391$$Nest$fputanimateExpandFromPosition(EmojiView r0, int r1) {
        r0.animateExpandFromPosition = r1;
    }

    /* renamed from: -$$Nest$fputanimateExpandStartTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10392$$Nest$fputanimateExpandStartTime(EmojiView r0, long r1) {
        r0.animateExpandStartTime = r1;
    }

    /* renamed from: -$$Nest$fputanimateExpandToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10393$$Nest$fputanimateExpandToPosition(EmojiView r0, int r1) {
        r0.animateExpandToPosition = r1;
    }

    /* renamed from: -$$Nest$fputanimatedEmojiDrawables, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10394$$Nest$fputanimatedEmojiDrawables(EmojiView r0, LongSparseArray r1) {
        r0.animatedEmojiDrawables = r1;
    }

    /* renamed from: -$$Nest$fputbackspaceOnce, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10395$$Nest$fputbackspaceOnce(EmojiView r0, boolean r1) {
        r0.backspaceOnce = r1;
    }

    /* renamed from: -$$Nest$fputbackspacePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10396$$Nest$fputbackspacePressed(EmojiView r0, boolean r1) {
        r0.backspacePressed = r1;
    }

    /* renamed from: -$$Nest$fputemojiLastX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10397$$Nest$fputemojiLastX(EmojiView r0, float r1) {
        r0.emojiLastX = r1;
    }

    /* renamed from: -$$Nest$fputemojiLastY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10398$$Nest$fputemojiLastY(EmojiView r0, float r1) {
        r0.emojiLastY = r1;
    }

    /* renamed from: -$$Nest$fputemojiPackAlertOpened, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10399$$Nest$fputemojiPackAlertOpened(EmojiView r0, boolean r1) {
        r0.emojiPackAlertOpened = r1;
    }

    /* renamed from: -$$Nest$fputemojiSmoothScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10400$$Nest$fputemojiSmoothScrolling(EmojiView r0, boolean r1) {
        r0.emojiSmoothScrolling = r1;
    }

    /* renamed from: -$$Nest$fputemojiTabShadowAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10401$$Nest$fputemojiTabShadowAnimator(EmojiView r0, AnimatorSet r1) {
        r0.emojiTabShadowAnimator = r1;
    }

    /* renamed from: -$$Nest$fputemojiTouchedView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10402$$Nest$fputemojiTouchedView(EmojiView r0, ImageViewEmoji r1) {
        r0.emojiTouchedView = r1;
    }

    /* renamed from: -$$Nest$fputemojiTouchedX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10403$$Nest$fputemojiTouchedX(EmojiView r0, float r1) {
        r0.emojiTouchedX = r1;
    }

    /* renamed from: -$$Nest$fputemojiTouchedY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10404$$Nest$fputemojiTouchedY(EmojiView r0, float r1) {
        r0.emojiTouchedY = r1;
    }

    /* renamed from: -$$Nest$fputexpandStickersByDragg, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10405$$Nest$fputexpandStickersByDragg(EmojiView r0, boolean r1) {
        r0.expandStickersByDragg = r1;
    }

    /* renamed from: -$$Nest$fputfirstEmojiAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10406$$Nest$fputfirstEmojiAttach(EmojiView r0, boolean r1) {
        r0.firstEmojiAttach = r1;
    }

    /* renamed from: -$$Nest$fputfirstGifAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10407$$Nest$fputfirstGifAttach(EmojiView r0, boolean r1) {
        r0.firstGifAttach = r1;
    }

    /* renamed from: -$$Nest$fputfirstStickersAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10408$$Nest$fputfirstStickersAttach(EmojiView r0, boolean r1) {
        r0.firstStickersAttach = r1;
    }

    /* renamed from: -$$Nest$fputgroupStickerPackPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10409$$Nest$fputgroupStickerPackPosition(EmojiView r0, int r1) {
        r0.groupStickerPackPosition = r1;
    }

    /* renamed from: -$$Nest$fputignorePagerScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10410$$Nest$fputignorePagerScroll(EmojiView r0, boolean r1) {
        r0.ignorePagerScroll = r1;
    }

    /* renamed from: -$$Nest$fputignoreStickersScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10411$$Nest$fputignoreStickersScroll(EmojiView r0, boolean r1) {
        r0.ignoreStickersScroll = r1;
    }

    /* renamed from: -$$Nest$fputlastSearchKeyboardLanguage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10412$$Nest$fputlastSearchKeyboardLanguage(EmojiView r0, String[] r1) {
        r0.lastSearchKeyboardLanguage = r1;
    }

    /* renamed from: -$$Nest$fputlastStickersX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10413$$Nest$fputlastStickersX(EmojiView r0, float r1) {
        r0.lastStickersX = r1;
    }

    /* renamed from: -$$Nest$fputsearchAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10414$$Nest$fputsearchAnimation(EmojiView r0, AnimatorSet r1) {
        r0.searchAnimation = r1;
    }

    /* renamed from: -$$Nest$fputstickerAdapterBuilt, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10415$$Nest$fputstickerAdapterBuilt(EmojiView r0, boolean r1) {
        r0.stickerAdapterBuilt = r1;
    }

    /* renamed from: -$$Nest$fputstickersContainerAttached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10416$$Nest$fputstickersContainerAttached(EmojiView r0, boolean r1) {
        r0.stickersContainerAttached = r1;
    }

    /* renamed from: -$$Nest$fputtrendingAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10417$$Nest$fputtrendingAdapter(EmojiView r0, TrendingAdapter r1) {
        r0.trendingAdapter = r1;
    }

    /* renamed from: -$$Nest$fputtrendingEmojiAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10418$$Nest$fputtrendingEmojiAdapter(EmojiView r0, TrendingAdapter r1) {
        r0.trendingEmojiAdapter = r1;
    }

    /* renamed from: -$$Nest$manimateTabsY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10419$$Nest$manimateTabsY(EmojiView r0, int r1) {
        r0.animateTabsY(r1);
    }

    /* renamed from: -$$Nest$mcheckBottomTabScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10420$$Nest$mcheckBottomTabScroll(EmojiView r0, float r1) {
        r0.checkBottomTabScroll(r1);
    }

    /* renamed from: -$$Nest$mcheckEmojiSearchFieldScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10421$$Nest$mcheckEmojiSearchFieldScroll(EmojiView r0, boolean r1) {
        r0.checkEmojiSearchFieldScroll(r1);
    }

    /* renamed from: -$$Nest$mcheckEmojiTabY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10422$$Nest$mcheckEmojiTabY(EmojiView r0, View r1, int r2) {
        r0.checkEmojiTabY(r1, r2);
    }

    /* renamed from: -$$Nest$mcheckGifSearchFieldScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10423$$Nest$mcheckGifSearchFieldScroll(EmojiView r0, boolean r1) {
        r0.checkGifSearchFieldScroll(r1);
    }

    /* renamed from: -$$Nest$mcheckGridVisibility, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10424$$Nest$mcheckGridVisibility(EmojiView r0, int r1, float r2) {
        r0.checkGridVisibility(r1, r2);
    }

    /* renamed from: -$$Nest$mcheckScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10425$$Nest$mcheckScroll(EmojiView r0, int r1) {
        r0.checkScroll(r1);
    }

    /* renamed from: -$$Nest$mcheckStickersSearchFieldScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10426$$Nest$mcheckStickersSearchFieldScroll(EmojiView r0, boolean r1) {
        r0.checkStickersSearchFieldScroll(r1);
    }

    /* renamed from: -$$Nest$mcheckTabsY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10427$$Nest$mcheckTabsY(EmojiView r0, int r1, int r2) {
        r0.checkTabsY(r1, r2);
    }

    /* renamed from: -$$Nest$mcreateStickersChooseActionTracker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10428$$Nest$mcreateStickersChooseActionTracker(EmojiView r0) {
        r0.createStickersChooseActionTracker();
    }

    /* renamed from: -$$Nest$mfindEmojiPackByDocumentId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiPack m10429$$Nest$mfindEmojiPackByDocumentId(EmojiView r0, long r1) {
        return r0.findEmojiPackByDocumentId(r1);
    }

    /* renamed from: -$$Nest$mgetGlassIconColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10430$$Nest$mgetGlassIconColor(EmojiView r0, float r1) {
        return r0.getGlassIconColor(r1);
    }

    /* renamed from: -$$Nest$mgetSearchFieldForType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchField m10431$$Nest$mgetSearchFieldForType(EmojiView r0, int r1) {
        return r0.getSearchFieldForType(r1);
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10432$$Nest$mgetThemedColor(EmojiView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$mneedsStickerAdapterBuild, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10433$$Nest$mneedsStickerAdapterBuild(EmojiView r0) {
        return r0.needsStickerAdapterBuild();
    }

    /* renamed from: -$$Nest$monPageScrolled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10434$$Nest$monPageScrolled(EmojiView r0, int r1, int r2, int r3) {
        r0.onPageScrolled(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mopenEmojiPackAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10435$$Nest$mopenEmojiPackAlert(EmojiView r0, TLRPC.InputStickerSet r1, TLRPC.Document r2) {
        r0.openEmojiPackAlert(r1, r2);
    }

    /* renamed from: -$$Nest$mopenPremiumAnimatedEmojiFeature, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10436$$Nest$mopenPremiumAnimatedEmojiFeature(EmojiView r0) {
        r0.openPremiumAnimatedEmojiFeature();
    }

    /* renamed from: -$$Nest$mopenSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10437$$Nest$mopenSearch(EmojiView r0, SearchField r1) {
        r0.openSearch(r1);
    }

    /* renamed from: -$$Nest$mopenTrendingStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10438$$Nest$mopenTrendingStickers(EmojiView r0, TLRPC.StickerSetCovered r1) {
        r0.openTrendingStickers(r1);
    }

    /* renamed from: -$$Nest$mpostBackspaceRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10439$$Nest$mpostBackspaceRunnable(EmojiView r0, int r1) {
        r0.postBackspaceRunnable(r1);
    }

    /* renamed from: -$$Nest$mreloadStickersAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10440$$Nest$mreloadStickersAdapter(EmojiView r0) {
        r0.reloadStickersAdapter();
    }

    /* renamed from: -$$Nest$mresolveEmojiDocument, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Document m10441$$Nest$mresolveEmojiDocument(EmojiView r0, long r1, TLRPC.Document r3, EmojiPack r4) {
        return r0.resolveEmojiDocument(r1, r3, r4);
    }

    /* renamed from: -$$Nest$msaveNewPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10442$$Nest$msaveNewPage(EmojiView r0) {
        r0.saveNewPage();
    }

    /* renamed from: -$$Nest$mscrollEmojisToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10443$$Nest$mscrollEmojisToPosition(EmojiView r0, int r1, int r2) {
        r0.scrollEmojisToPosition(r1, r2);
    }

    /* renamed from: -$$Nest$mscrollGifsToTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10444$$Nest$mscrollGifsToTop(EmojiView r0) {
        r0.scrollGifsToTop();
    }

    /* renamed from: -$$Nest$msendEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10445$$Nest$msendEmoji(EmojiView r0, ImageViewEmoji r1, String r2) {
        r0.sendEmoji(r1, r2);
    }

    /* renamed from: -$$Nest$msetFoundPackButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10446$$Nest$msetFoundPackButtonText(EmojiView r0, FoundStickerPackButton r1, TLObject r2, TLRPC.StickerSet r3, TLRPC.Document r4, boolean r5, boolean r6) {
        r0.setFoundPackButtonText(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: -$$Nest$mshowBackspaceButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10447$$Nest$mshowBackspaceButton(EmojiView r0, boolean r1, boolean r2) {
        r0.showBackspaceButton(r1, r2);
    }

    /* renamed from: -$$Nest$mshowBottomTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10448$$Nest$mshowBottomTab(EmojiView r0, boolean r1, boolean r2) {
        r0.showBottomTab(r1, r2);
    }

    /* renamed from: -$$Nest$mshowStickerSettingsButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10449$$Nest$mshowStickerSettingsButton(EmojiView r0, boolean r1, boolean r2) {
        r0.showStickerSettingsButton(r1, r2);
    }

    /* renamed from: -$$Nest$mstartStopVisibleGifs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10450$$Nest$mstartStopVisibleGifs(EmojiView r0, boolean r1) {
        r0.startStopVisibleGifs(r1);
    }

    /* renamed from: -$$Nest$mstopAnimatingTabsY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10451$$Nest$mstopAnimatingTabsY(EmojiView r0, int r1) {
        r0.stopAnimatingTabsY(r1);
    }

    /* renamed from: -$$Nest$msyncRecentEmojiVersion, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10452$$Nest$msyncRecentEmojiVersion(EmojiView r0) {
        r0.syncRecentEmojiVersion();
    }

    /* renamed from: -$$Nest$mupdateEmojiHeaders, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10453$$Nest$mupdateEmojiHeaders(EmojiView r0) {
        r0.updateEmojiHeaders();
    }

    /* renamed from: -$$Nest$mupdateEmojiTabsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10454$$Nest$mupdateEmojiTabsPosition(EmojiView r0) {
        r0.updateEmojiTabsPosition();
    }

    /* renamed from: -$$Nest$mupdateEmojiTabsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10455$$Nest$mupdateEmojiTabsPosition(EmojiView r0, int r1) {
        r0.updateEmojiTabsPosition(r1);
    }

    /* renamed from: -$$Nest$mupdateRecentGifs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10456$$Nest$mupdateRecentGifs(EmojiView r0) {
        r0.updateRecentGifs();
    }

    /* renamed from: -$$Nest$mupdateStickerTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10457$$Nest$mupdateStickerTabs(EmojiView r0, boolean r1) {
        r0.updateStickerTabs(r1);
    }

    /* renamed from: -$$Nest$mupdateStickerTabsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10458$$Nest$mupdateStickerTabsPosition(EmojiView r0) {
        r0.updateStickerTabsPosition();
    }

    public EmojiView(BaseFragment r14, boolean r15, boolean r16, boolean r17, Context r18, boolean r19, TLRPC.ChatFull r20, ViewGroup r21, boolean r22, Theme.ResourcesProvider r23, boolean r24) {
        this(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, false);
    }

    public EmojiView(BaseFragment r15, boolean r16, boolean r17, boolean r18, Context r19, boolean r20, TLRPC.ChatFull r21, ViewGroup r22, boolean r23, Theme.ResourcesProvider r24, boolean r25, boolean r26) {
        this(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, false);
    }

    public EmojiView(BaseFragment r39, boolean r40, boolean r41, boolean r42, Context r43, boolean r44, TLRPC.ChatFull r45, ViewGroup r46, boolean r47, Theme.ResourcesProvider r48, boolean r49, boolean r50, boolean r51) {
        super(r43);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorSearchStickerPackSelected = new BoolAnimator(0, this, r3, 320);
        this.animatorSearchEmojiPackSelected = new BoolAnimator(1, this, r3, 320);
        this.emojiCacheType = 2;
        this.allTabs = new ArrayList();
        this.currentTabs = new ArrayList();
        this.firstEmojiAttach = true;
        this.hasRecentEmoji = -1;
        this.gifSearchPreloader = new GifSearchPreloader(this, null);
        this.gifCache = new HashMap();
        this.firstGifAttach = true;
        this.gifRecentTabNum = -2;
        this.gifTrendingTabNum = -2;
        this.gifFirstEmojiTabNum = -2;
        this.shouldDrawBackground = true;
        this.shouldLightenBackground = true;
        this.firstStickersAttach = true;
        this.tabsMinusDy = new int[3];
        this.tabsYAnimators = new ObjectAnimator[3];
        this.currentAccount = UserConfig.selectedAccount;
        this.stickerSets = new ArrayList();
        this.recentGifs = new ArrayList();
        this.recentStickers = new ArrayList();
        this.favouriteStickers = new ArrayList();
        this.premiumStickers = new ArrayList();
        this.featuredStickerSets = new ArrayList();
        this.featuredEmojiSets = new ArrayList();
        this.keepFeaturedDuplicate = new ArrayList();
        this.expandedEmojiSets = new ArrayList();
        this.installedEmojiSets = new ArrayList();
        this.emojipacksProcessed = new ArrayList();
        this.toInstall = new HashMap();
        this.primaryInstallingStickerSets = new TLRPC.StickerSetCovered[10];
        this.installingStickerSets = new LongSparseArray();
        this.removingStickerSets = new LongSparseArray();
        this.lastRecentEmojiVersion = -1;
        int r25 = 3;
        this.location = new int[2];
        this.recentTabNum = -2;
        this.favTabNum = -2;
        this.trendingTabNum = -2;
        this.premiumTabNum = -2;
        this.currentBackgroundType = -1;
        this.checkExpandStickerTabsRunnable = new AnonymousClass1(this);
        this.contentPreviewViewerDelegate = new AnonymousClass2(this);
        this.premiumBulletin = true;
        GlassEngine r0 = new GlassEngine();
        this.glassEngine = r0;
        this.visibleInAppKeyboardHeight = -1.0f;
        this.animateExpandFromPosition = -1;
        this.animateExpandToPosition = -1;
        this.animateExpandStartTime = -1;
        this.emojiPackAlertOpened = false;
        this.fixBottomTabContainerTranslation = true;
        this.rect = new Rect();
        RectF r1 = new RectF();
        this.blurredRectF = r1;
        ArrayList<RectF> r4 = new ArrayList(1);
        this.blurredRectList = r4;
        r4.add(r1);
        this.fadeDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, null);
        this.bottomTabVisibility = new BoolAnimator(0, new EmojiView$$ExternalSyntheticLambda0(this), r3, 380, true);
        this.updateStickersLoadedDelayed = new EmojiView$$ExternalSyntheticLambda11(this);
        this.disableStickerEditor = false;
        this.shouldDrawBackground = r47;
        this.fragment = r39;
        this.allowAnimatedEmoji = r40;
        this.resourcesProvider = r48;
        this.glassDesign = r50;
        allowEmojisForNonPremium(r51);
        BlurredBackgroundSourceColor r12 = new BlurredBackgroundSourceColor();
        this.blurredBackgroundSourceColor = r12;
        r12.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        if (r49 == false) goto L5;
        freeze(true);
    L5:
        ColorUtils.setAlphaComponent(getThemedColor(Theme.key_glass_defaultIcon), 30);
        this.searchFieldHeight = AndroidUtilities.dp(50.0f);
        this.needEmojiSearch = r44;
        int r32 = R.drawable.smiles_tab_smiles;
        if (r50 == false) goto L8;
        int r5 = getGlassIconColor(0.4f);
    L10:
        if (r50 == false) goto L13;
        int r16 = getGlassIconColor(0.8f);
    L14:
        Drawable r33 = Theme.createEmojiIconSelectorDrawable(r43, r32, r5, r16);
        int r410 = R.drawable.smiles_tab_gif;
        if (r50 == false) goto L18;
        int r162 = getGlassIconColor(0.4f);
    L17:
        int r52 = r162;
        if (r50 == false) goto L23;
        int r17 = getGlassIconColor(0.8f);
    L24:
        Drawable r13 = Theme.createEmojiIconSelectorDrawable(r43, r410, r52, r17);
        int r411 = R.drawable.smiles_tab_stickers;
        if (r50 == false) goto L28;
        int r172 = getGlassIconColor(0.4f);
    L27:
        int r53 = r172;
        if (r50 == false) goto L32;
        int r173 = getGlassIconColor(0.8f);
    L33:
        this.tabIcons = new Drawable[]{r33, r13, Theme.createEmojiIconSelectorDrawable(r43, r411, r53, r173)};
        int r14 = R.drawable.msg_emoji_recent;
        if (r50 == false) goto L36;
        int r34 = getGlassIconColor(0.4f);
    L37:
        if (r50 == false) goto L39;
        int r412 = getGlassIconColor(0.8f);
    L40:
        Drawable r15 = Theme.createEmojiIconSelectorDrawable(r43, r14, r34, r412);
        int r35 = R.drawable.emoji_tabs_faves;
        if (r50 == false) goto L43;
        int r413 = getGlassIconColor(0.4f);
    L44:
        if (r50 == false) goto L46;
        int r54 = getGlassIconColor(0.8f);
    L47:
        Drawable r36 = Theme.createEmojiIconSelectorDrawable(r43, r35, r413, r54);
        int r414 = R.drawable.emoji_tabs_new3;
        if (r50 == false) goto L50;
        int r7 = getGlassIconColor(0.4f);
    L51:
        if (r50 == false) goto L54;
        int r174 = getGlassIconColor(0.8f);
    L55:
        Drawable r415 = Theme.createEmojiIconSelectorDrawable(r43, r414, r7, r174);
        int r72 = R.drawable.emoji_tabs_new1;
        if (r50 == false) goto L59;
        int r18 = getGlassIconColor(0.4f);
    L58:
        int r19 = r18;
        if (r50 == false) goto L64;
        int r192 = getGlassIconColor(0.8f);
    L65:
        Drawable r110 = Theme.createEmojiIconSelectorDrawable(r43, r72, r19, r192);
        this.searchIconDrawable = r110;
        int r37 = R.drawable.emoji_tabs_new2;
        int r73 = Theme.key_chat_emojiPanelStickerPackSelectorLine;
        Drawable r38 = Theme.createEmojiIconSelectorDrawable(r43, r37, getThemedColor(r73), getThemedColor(r73));
        this.searchIconDotDrawable = r38;
        this.stickerIcons = new Drawable[]{r15, r36, r415, new LayerDrawable(new Drawable[]{r110, r38})};
        int r310 = R.drawable.msg_emoji_recent;
        if (r50 == false) goto L68;
        int r416 = getGlassIconColor(0.4f);
    L69:
        if (r50 == false) goto L71;
        int r8 = getGlassIconColor(0.8f);
    L72:
        Drawable r311 = Theme.createEmojiIconSelectorDrawable(r43, r310, r416, r8);
        int r417 = R.drawable.stickers_gifs_trending;
        if (r50 == false) goto L75;
        int r55 = getGlassIconColor(0.4f);
    L76:
        if (r50 == false) goto L78;
        int r82 = getGlassIconColor(0.8f);
    L79:
        this.gifIcons = new Drawable[]{r311, Theme.createEmojiIconSelectorDrawable(r43, r417, r55, r82)};
        this.emojiTitles = new String[]{LocaleController.getString(R.string.Emoji1), LocaleController.getString(R.string.Emoji2), LocaleController.getString(R.string.Emoji3), LocaleController.getString(R.string.Emoji4), LocaleController.getString(R.string.Emoji5), LocaleController.getString(R.string.Emoji6), LocaleController.getString(R.string.Emoji7), LocaleController.getString(R.string.Emoji8)};
        this.showGifs = r42;
        this.info = r45;
        Paint r312 = new Paint(1);
        this.dotPaint = r312;
        r312.setColor(getThemedColor(Theme.key_chat_emojiPanelNewTrending));
        this.outlineProvider = ViewOutlineProviderImpl.boundsWithPaddingFromViewAndRoundRect(AndroidUtilities.dp(6.0f));
        this.emojiContainer = new AnonymousClass4(this, r43);
        Tab r313 = new Tab(this, null);
        r313.type = 0;
        r313.view = this.emojiContainer;
        this.allTabs.add(r313);
        if (r40 == false) goto L82;
        MediaDataController.getInstance(this.currentAccount).checkStickers(5);
        MediaDataController.getInstance(this.currentAccount).checkFeaturedEmoji();
        this.animatedEmojiTextColorFilter = new PorterDuffColorFilter(getThemedColor(Theme.key_featuredStickers_addButton), PorterDuff.Mode.SRC_IN);
    L82:
        EmojiGridView r418 = new EmojiGridView(this, r43);
        this.emojiGridView = r418;
        r0.addScrolledView(r418);
        DefaultItemAnimator r419 = new DefaultItemAnimator();
        r419.setAddDelay(0);
        r419.setAddDuration(220);
        r419.setMoveDuration(220);
        r419.setChangeDuration(160);
        r419.setMoveInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.emojiGridView.setItemAnimator(r419);
        this.emojiGridView.setOnTouchListener(new EmojiView$$ExternalSyntheticLambda12(this, r48));
        this.emojiGridView.setOnItemLongClickListener(new EmojiView$$ExternalSyntheticLambda13(this));
        this.emojiGridView.setInstantClick(true);
        EmojiGridView r111 = this.emojiGridView;
        AnonymousClass5 r420 = new AnonymousClass5(this, r43, 8);
        this.emojiLayoutManager = r420;
        r111.setLayoutManager(r420);
        this.emojiGridView.setTopGlowOffset(AndroidUtilities.dp(38.0f));
        this.emojiGridView.setBottomGlowOffset(AndroidUtilities.dp(36.0f));
        this.emojiGridView.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(36.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(44.0f));
        EmojiGridView r112 = this.emojiGridView;
        int r314 = Theme.key_chat_emojiPanelBackground;
        r112.setGlowColor(getThemedColor(r314));
        this.emojiGridView.setSelectorType(100);
        this.emojiGridView.setSelectorDrawableColor(0);
        this.emojiGridView.setItemSelectorColorProvider(new EmojiView$$ExternalSyntheticLambda14());
        this.emojiGridView.setClipToPadding(false);
        this.emojiLayoutManager.setSpanSizeLookup(new AnonymousClass6(this));
        EmojiGridView r113 = this.emojiGridView;
        EmojiGridAdapter r56 = new EmojiGridAdapter(this, null);
        this.emojiAdapter = r56;
        r113.setAdapter(r56);
        this.emojiGridView.addItemDecoration(new EmojiGridSpacing(this));
        this.emojiSearchAdapter = new EmojiSearchAdapter(this, r43);
        this.emojiContainer.addView(this.emojiGridView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerAnimationScrollHelper r02 = new RecyclerAnimationScrollHelper(this.emojiGridView, this.emojiLayoutManager);
        this.emojiScrollHelper = r02;
        r02.setAnimationCallback(new AnonymousClass7(this));
        this.emojiGridView.setOnScrollListener(new AnonymousClass8(this, 1));
        if (r39 == null) goto L86;
        EmojiView$$ExternalSyntheticLambda15 r9 = new EmojiView$$ExternalSyntheticLambda15(this);
    L87:
        EmojiView r2 = this;
        r2.emojiTabs = new AnonymousClass9(this, r43, r48, true, false, true, r40, 0, r9, r50);
        if (r44 == false) goto L90;
        AnonymousClass10 r03 = new AnonymousClass10(r2, r43, 1);
        r2.emojiSearchField = r03;
        int r74 = -1;
        r2.emojiContainer.addView(r03, new FrameLayout.LayoutParams(-1, r2.searchFieldHeight + AndroidUtilities.getShadowHeight()));
        SearchField.m10538$$Nest$fgetsearchEditText(r2.emojiSearchField).setOnFocusChangeListener(new AnonymousClass11(r2));
        FoundStickerPacksHeaderCell r04 = new FoundStickerPacksHeaderCell(r43, r48);
        r2.emojiSearchHeader = r04;
        r04.setVisibility(8);
        r2.emojiSearchHeader.setOnBackClickListener(new EmojiView$$ExternalSyntheticLambda16(r2));
        r2.emojiContainer.addView(r2.emojiSearchHeader, new FrameLayout.LayoutParams(-1, r2.searchFieldHeight));
    L91:
        int r05 = r2.getThemedColor(r314);
        if (Color.alpha(r05) < 255) goto L94;
        r2.emojiTabs.setBackgroundColor(r05);
    L94:
        r2.emojiAdapter.processEmoji(true);
        r2.emojiTabs.updateEmojiPacks(r2.getEmojipacks());
        r2.emojiContainer.addView(r2.emojiTabs, LayoutHelper.createFrame(r74, 36.0f));
        View r06 = new View(r43);
        r2.emojiTabsShadow = r06;
        r06.setAlpha(0.0f);
        r2.emojiTabsShadow.setTag(1);
        View r07 = r2.emojiTabsShadow;
        int r83 = Theme.key_chat_emojiPanelShadowLine;
        r07.setBackgroundColor(r2.getThemedColor(r83));
        FrameLayout.LayoutParams r08 = new FrameLayout.LayoutParams(r74, AndroidUtilities.getShadowHeight(), 51);
        r08.topMargin = AndroidUtilities.dp(36.0f);
        r2.emojiContainer.addView(r2.emojiTabsShadow, r08);
        r2.emojiAddPackButton = new FoundStickerPackButton(r43, r48);
        FoundStickerPackButtonContainer r09 = new FoundStickerPackButtonContainer(r43, r48);
        r2.emojiAddPackButtonContainer = r09;
        r09.setVisibility(8);
        r2.emojiAddPackButtonContainer.addView(r2.emojiAddPackButton, LayoutHelper.createFrame(-1, 48.0f, 80, 10.0f, 5.0f, 10.0f, 10.0f));
        r2.emojiContainer.addView(r2.emojiAddPackButtonContainer, LayoutHelper.createFrame(r74, -2, 80));
        if (r41 == false) goto L109;
        if (r42 == false) goto L98;
        r2.gifContainer = new AnonymousClass12(r2, r43);
        EmojiViewIA r114 = null;
        Tab r010 = new Tab(r2, null);
        r010.type = 1;
        r010.view = r2.gifContainer;
        r2.allTabs.add(r010);
        AnonymousClass13 r011 = new AnonymousClass13(r2, r43);
        r2.gifGridView = r011;
        r0.addScrolledView(r011);
        r2.gifGridView.setClipToPadding(false);
        RecyclerListView r012 = r2.gifGridView;
        GifLayoutManager r57 = new GifLayoutManager(r2, r43);
        r2.gifLayoutManager = r57;
        r012.setLayoutManager(r57);
        r2.gifGridView.addItemDecoration(new AnonymousClass14(r2));
        r2.gifGridView.setPadding(0, r2.searchFieldHeight, 0, AndroidUtilities.dp(44.0f) + r2.bottomInset);
        ((SimpleItemAnimator) r2.gifGridView.getItemAnimator()).setSupportsChangeAnimations(false);
        RecyclerListView r013 = r2.gifGridView;
        GifAdapter r421 = new GifAdapter(r2, r43, true);
        r2.gifAdapter = r421;
        r013.setAdapter(r421);
        r2.gifSearchAdapter = new GifAdapter(r2, r43);
        int r10 = 2;
        r2.gifGridView.setOnScrollListener(new AnonymousClass15(r2, 2));
        r2.gifGridView.setOnTouchListener(new EmojiView$$ExternalSyntheticLambda17(r2, r48));
        EmojiView$$ExternalSyntheticLambda18 r014 = new EmojiView$$ExternalSyntheticLambda18(r2);
        r2.gifOnItemClickListener = r014;
        r2.gifGridView.setOnItemClickListener(r014);
        r2.gifContainer.addView(r2.gifGridView, LayoutHelper.createFrame(r74, -1.0f));
        AnonymousClass16 r015 = new AnonymousClass16(r2, r43, 2);
        r2.gifSearchField = r015;
        r2.gifContainer.addView(r015, new FrameLayout.LayoutParams(r74, r2.searchFieldHeight + AndroidUtilities.getShadowHeight()));
        DraggableScrollSlidingTabStrip r016 = new DraggableScrollSlidingTabStrip(r2, r43, r48);
        r2.gifTabs = r016;
        r016.setType(ScrollSlidingTabStrip.Type.TAB);
        r2.gifTabs.setUnderlineHeight(AndroidUtilities.getShadowHeight());
        r2.gifTabs.setIndicatorColor(r2.getThemedColor(r73));
        r2.gifTabs.setUnderlineColor(r2.getThemedColor(r83));
        r2.gifTabs.setBackgroundColor(r2.getThemedColor(r314));
        r2.updateGifTabs();
        r2.gifTabs.setDelegate(new EmojiView$$ExternalSyntheticLambda19(r2));
        r2.gifAdapter.loadTrendingGifs();
    L99:
        boolean r58 = r47;
        r2.stickersContainer = new AnonymousClass17(r2, r43, r58);
        MediaDataController.getInstance(r2.currentAccount).checkStickers(0);
        MediaDataController.getInstance(r2.currentAccount).checkFeaturedStickers();
        AnonymousClass18 r017 = new AnonymousClass18(r2, r43);
        r2.stickersGridView = r017;
        r0.addScrolledView(r017);
        RecyclerListView r018 = r2.stickersGridView;
        AnonymousClass19 r422 = new AnonymousClass19(r2, r43, 5);
        r2.stickersLayoutManager = r422;
        r018.setLayoutManager(r422);
        r2.stickersLayoutManager.setSpanSizeLookup(new AnonymousClass20(r2));
        r2.stickersGridView.setPadding(0, AndroidUtilities.dp(36.0f), 0, AndroidUtilities.dp(44.0f));
        r2.stickersGridView.setClipToPadding(false);
        Tab r019 = new Tab(r2, r114);
        r019.type = r10;
        r019.view = r2.stickersContainer;
        r2.allTabs.add(r019);
        r2.stickersSearchGridAdapter = new StickersSearchGridAdapter(r2, r43);
        RecyclerListView r020 = r2.stickersGridView;
        StickersGridAdapter r423 = new StickersGridAdapter(r2, r43);
        r2.stickersGridAdapter = r423;
        r020.setAdapter(r423);
        r2.stickersGridView.setOnTouchListener(new EmojiView$$ExternalSyntheticLambda1(r2, r48));
        EmojiView$$ExternalSyntheticLambda2 r021 = new EmojiView$$ExternalSyntheticLambda2(r2);
        r2.stickersOnItemClickListener = r021;
        r2.stickersGridView.setOnItemClickListener(r021);
        r2.stickersGridView.setGlowColor(r2.getThemedColor(r314));
        r2.stickersContainer.addView(r2.stickersGridView);
        r2.stickersScrollHelper = new RecyclerAnimationScrollHelper(r2.stickersGridView, r2.stickersLayoutManager);
        AnonymousClass21 r022 = new AnonymousClass21(r2, r43, 0);
        r2.stickersSearchField = r022;
        r2.stickersContainer.addView(r022, new FrameLayout.LayoutParams(r74, r2.searchFieldHeight + AndroidUtilities.getShadowHeight()));
        FoundStickerPacksHeaderCell r023 = new FoundStickerPacksHeaderCell(r43, r48);
        r2.stickerSearchHeader = r023;
        r023.setVisibility(8);
        r2.stickerSearchHeader.setOnBackClickListener(new EmojiView$$ExternalSyntheticLambda3(r2));
        r2.stickersContainer.addView(r2.stickerSearchHeader, new FrameLayout.LayoutParams(r74, r2.searchFieldHeight));
        EmojiViewIA r6 = r114;
        int r92 = -2;
        AnonymousClass22 r024 = new AnonymousClass22(r2, r43, r48, r39, r58);
        Context r025 = r43;
        r2 = r2;
        r2.stickersTab = r024;
        r024.setDragEnabled(true);
        r2.stickersTab.setWillNotDraw(false);
        r2.stickersTab.setType(ScrollSlidingTabStrip.Type.TAB);
        ScrollSlidingTabStrip r115 = r2.stickersTab;
        if (r2.stickersGridView.canScrollVertically(r74) == false) goto L102;
        int r424 = AndroidUtilities.getShadowHeight();
    L103:
        r115.setUnderlineHeight(r424);
        r2.stickersTab.setIndicatorColor(r2.getThemedColor(r73));
        r2.stickersTab.setUnderlineColor(r2.getThemedColor(r83));
        if (r46 == null) goto L107;
        if (r58 == false) goto L107;
        AnonymousClass23 r425 = new AnonymousClass23(r2, r025);
        r2.stickersTabContainer = r425;
        r425.addView(r2.stickersTab, LayoutHelper.createFrame(r74, 36, 51));
        r46.addView(r2.stickersTabContainer, LayoutHelper.createFrame(r74, -2.0f));
    L108:
        r2.updateRecentStickerLists();
        r2.updateStickerTabs(true);
        r2.stickersTab.setDelegate(new EmojiView$$ExternalSyntheticLambda4(r2));
        r2.stickersGridView.setOnScrollListener(new TypedScrollListener(r2, 0));
        r2.stickerAddPackButton = new FoundStickerPackButton(r025, r48);
        FoundStickerPackButtonContainer r116 = new FoundStickerPackButtonContainer(r025, r48);
        r2.stickerAddPackButtonContainer = r116;
        r116.setVisibility(8);
        r2.stickerAddPackButtonContainer.addView(r2.stickerAddPackButton, LayoutHelper.createFrame(-1, 48.0f, 80, 10.0f, 5.0f, 10.0f, 10.0f));
        r2.stickersContainer.addView(r2.stickerAddPackButtonContainer, LayoutHelper.createFrame(r74, -2, 80));
    L110:
        r2.currentTabs.clear();
        r2.currentTabs.addAll(r2.allTabs);
        AnonymousClass24 r117 = new AnonymousClass24(r2, r025);
        r2.pager = r117;
        r0.addScrolledView(r117);
        r2.pager.setOverScrollMode(r10);
        ViewPager r118 = r2.pager;
        EmojiPagesAdapter r426 = new EmojiPagesAdapter(r2, r6);
        r2.emojiPagerAdapter = r426;
        r118.setAdapter(r426);
        AnonymousClass25 r119 = new AnonymousClass25(r2, r025);
        r2.backspaceButton = r119;
        r119.setHapticFeedbackEnabled(true);
        r2.backspaceButton.setImageResource(R.drawable.smiles_tab_clear);
        ImageView r120 = r2.backspaceButton;
        if (r50 == false) goto L113;
        int r102 = r2.getGlassIconColor(0.6f);
    L114:
        PorterDuff.Mode r132 = PorterDuff.Mode.MULTIPLY;
        r120.setColorFilter(new PorterDuffColorFilter(r102, r132));
        ImageView r121 = r2.backspaceButton;
        ImageView.ScaleType r427 = ImageView.ScaleType.CENTER;
        r121.setScaleType(r427);
        r2.backspaceButton.setContentDescription(LocaleController.getString(R.string.AccDescrBackspace));
        r2.backspaceButton.setFocusable(true);
        r2.backspaceButton.setOnClickListener(new EmojiView$$ExternalSyntheticLambda5());
        ScaleStateListAnimator.apply(r2.backspaceButton);
        FrameLayout r122 = new FrameLayout(r025);
        r2.bulletinContainer = r122;
        if (r44 == false) goto L117;
        r2.addView(r122, LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, (AndroidUtilities.getShadowHeight() / AndroidUtilities.density) + 40.0f));
    L118:
        FrameLayout r123 = new FrameLayout(r025);
        r2.bulletinContainer2 = r123;
        r2.addView(r123, LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, 64.0f));
        r2.bottomTabContainer = new FrameLayout(r025);
        View r124 = new View(r025);
        r2.bottomTabContainerBackground = r124;
        r2.bottomTabContainer.addView(r124, new FrameLayout.LayoutParams(r74, AndroidUtilities.dp(40.0f), 83));
        FrameLayout r125 = r2.bottomTabContainer;
        if (r44 == false) goto L133;
        r2.addView(r125, LayoutHelper.createFrame(r74, 48, 80));
        r2.bottomTabContainer.addView(r2.backspaceButton, LayoutHelper.createFrame(48, 48.0f, 85, 2.0f, 0.0f, 2.0f, 0.0f));
        if (r41 == false) goto L127;
        ImageView r126 = new ImageView(r025);
        r2.stickerSettingsButton = r126;
        r126.setImageResource(R.drawable.smiles_tab_settings);
        ImageView r127 = r2.stickerSettingsButton;
        if (r50 == false) goto L125;
        int r128 = r2.getGlassIconColor(0.6f);
    L126:
        r127.setColorFilter(new PorterDuffColorFilter(r128, r132));
        r2.stickerSettingsButton.setScaleType(r427);
        r2.stickerSettingsButton.setFocusable(true);
        r2.stickerSettingsButton.setContentDescription(LocaleController.getString(R.string.Settings));
        ScaleStateListAnimator.apply(r2.stickerSettingsButton);
        r2.bottomTabContainer.addView(r2.stickerSettingsButton, LayoutHelper.createFrame(48, 48.0f, 85, 2.0f, 0.0f, 2.0f, 0.0f));
        r2.stickerSettingsButton.setOnClickListener(new EmojiView$$ExternalSyntheticLambda6(r2));
        goto L127
    L125:
        r128 = r2.getThemedColor(Theme.key_chat_emojiPanelBackspace);
    L127:
        PagerSlidingTabStrip r129 = new PagerSlidingTabStrip(r025, r48);
        r2.typeTabs = r129;
        r129.setViewPager(r2.pager);
        r2.typeTabs.setShouldExpand(false);
        r2.typeTabs.setIndicatorHeight(AndroidUtilities.dp(3.0f));
        r2.typeTabs.setIndicatorColor(ColorUtils.setAlphaComponent(r2.getThemedColor(Theme.key_chat_emojiPanelIconSelected), 20));
        r2.typeTabs.setUnderlineHeight(0);
        r2.typeTabs.setTabPaddingLeftRight(AndroidUtilities.dp(11.0f));
        r2.typeTabs.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(11.0f));
        r2.bottomTabContainer.addView(r2.typeTabs, LayoutHelper.createFrame(r92, 48, 81));
        r2.typeTabs.setOnPageChangeListener(new AnonymousClass26(r2, r58));
        ImageView r130 = new ImageView(r025);
        r2.searchButton = r130;
        r130.setImageResource(R.drawable.smiles_tab_search);
        ImageView r131 = r2.searchButton;
        if (r50 == false) goto L130;
        int r62 = r2.getGlassIconColor(0.6f);
    L131:
        r131.setColorFilter(new PorterDuffColorFilter(r62, r132));
        r2.searchButton.setScaleType(r427);
        r2.searchButton.setContentDescription(LocaleController.getString(R.string.Search));
        r2.searchButton.setFocusable(true);
        r2.searchButton.setVisibility(8);
        r2.bottomTabContainer.addView(r2.searchButton, LayoutHelper.createFrame(48, 48.0f, 83, 2.0f, 0.0f, 2.0f, 0.0f));
        r2.searchButton.setOnClickListener(new EmojiView$$ExternalSyntheticLambda7(r2));
    L137:
        r2.addView(r2.pager, 0, LayoutHelper.createFrame(r74, r74, 51));
        CorrectlyMeasuringTextView r133 = new CorrectlyMeasuringTextView(r025);
        r2.mediaBanTooltip = r133;
        r133.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), r2.getThemedColor(Theme.key_chat_gifSaveHintBackground)));
        r2.mediaBanTooltip.setTextColor(r2.getThemedColor(Theme.key_chat_gifSaveHintText));
        r2.mediaBanTooltip.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(7.0f));
        r2.mediaBanTooltip.setGravity(16);
        r2.mediaBanTooltip.setTextSize(1, 14.0f);
        r2.mediaBanTooltip.setVisibility(4);
        r2.addView(r2.mediaBanTooltip, LayoutHelper.createFrame(-2, -2.0f, 81, 5.0f, 0.0f, 5.0f, 53.0f));
        if (AndroidUtilities.isTablet() == false) goto L140;
        float r103 = 40.0f;
    L141:
        r2.emojiSize = AndroidUtilities.dp(r103);
        EmojiColorPickerWindow r026 = EmojiColorPickerWindow.create(r025, r48);
        r2.colorPickerView = r026;
        r026.setOnSelectionUpdateListener(new EmojiView$$ExternalSyntheticLambda8(r2));
        r2.currentPage = MessagesController.getGlobalEmojiSettings().getInt("selected_page", 0);
        Emoji.loadRecentEmoji();
        r2.emojiAdapter.notifyDataSetChanged();
        r2.syncRecentEmojiVersion();
        r2.setAllow(r41, r42, false);
        if (Build.VERSION.SDK_INT < 31) goto L149;
        DownscaleScrollableNoiseSuppressor r027 = new DownscaleScrollableNoiseSuppressor();
        r2.scrollableViewNoiseSuppressor = r027;
        BlurredBackgroundSourceRenderNode r134 = new BlurredBackgroundSourceRenderNode(null);
        r2.blurredBackgroundSourceRenderNode = r134;
        r134.setUnderSource(r12);
        r134.setScrollableNoiseSuppressor(r027, r92);
        BlurredBackgroundDrawableViewFactory r028 = new BlurredBackgroundDrawableViewFactory(r134);
        r2.blurredBackgroundDrawableFactory = r028;
        r028.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        if (LiteMode.isEnabled(262144) == false) goto L147;
        float r135 = 8.0f;
    L148:
        r028.setOutset(AndroidUtilities.dp(r135));
        r028.setGlassEngine(r0);
    L150:
        r2.blurCaptureMethod = new EmojiView$$ExternalSyntheticLambda9(r2);
        r2.setBlurredBackgroundDrawableFactory(r2.blurredBackgroundDrawableFactory);
        r0.setRoot(r2);
        r0.setGlassInvalidationListener(new EmojiView$$ExternalSyntheticLambda10(r2));
        return;
    L147:
        r135 = 48.0f;
        goto L148
    L149:
        r2.blurredBackgroundSourceRenderNode = null;
        BlurredBackgroundDrawableViewFactory r029 = new BlurredBackgroundDrawableViewFactory(r12);
        r2.blurredBackgroundDrawableFactory = r029;
        r029.setGlassEngine(r0);
        r2.scrollableViewNoiseSuppressor = null;
        goto L150
    L140:
        r103 = 32.0f;
        goto L141
    L130:
        r62 = r2.getThemedColor(Theme.key_chat_emojiPanelBackspace);
        goto L131
    L133:
        if (LocaleController.isRTL == true) goto L136;
        r25 = 5;
    L136:
        r2.addView(r125, LayoutHelper.createFrame(56, 48.0f, r25 | 80, 0.0f, 0.0f, 2.0f, 0.0f));
        Drawable r136 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(56.0f), r2.getThemedColor(r314), r2.getThemedColor(r314));
        ScaleStateListAnimator.apply(r2.backspaceButton);
        r2.backspaceButton.setPadding(0, 0, AndroidUtilities.dp(2.0f), 0);
        r2.backspaceButton.setBackground(r136);
        r2.backspaceButton.setColorFilter(new PorterDuffColorFilter(r2.getThemedColor(Theme.key_chats_actionIcon), r132));
        r2.backspaceButton.setContentDescription(LocaleController.getString(R.string.AccDescrBackspace));
        r2.backspaceButton.setFocusable(true);
        r2.bottomTabContainer.addView(r2.backspaceButton, LayoutHelper.createFrame(48, 48.0f, 51, 2.0f, 0.0f, 2.0f, 0.0f));
        r2.bottomTabContainerBackground.setVisibility(8);
        goto L137
    L117:
        r2.addView(r122, LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, 0.0f));
        goto L118
    L113:
        r102 = r2.getThemedColor(Theme.key_chat_emojiPanelBackspace);
    L107:
        r2.stickersContainer.addView(r2.stickersTab, LayoutHelper.createFrame(r74, 36, 51));
        goto L108
    L102:
        r424 = 0;
        goto L103
    L98:
        r114 = null;
        r10 = 2;
        goto L99
    L109:
        r58 = r47;
        r92 = -2;
        r025 = r43;
        r6 = null;
        r10 = 2;
        goto L110
    L90:
        r74 = -1;
        goto L91
    L86:
        r9 = null;
        goto L87
    L78:
        r82 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L79
    L75:
        r55 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L76
    L71:
        r8 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L72
    L68:
        r416 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L69
    L64:
        r192 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L65
    L59:
        r18 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L58
    L54:
        r174 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L55
    L50:
        r7 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L51
    L46:
        r54 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L47
    L43:
        r413 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L44
    L39:
        r412 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L40
    L36:
        r34 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L37
    L32:
        r173 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L33
    L28:
        r172 = getThemedColor(Theme.key_chat_emojiPanelBackspace);
        goto L27
    L23:
        r17 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L24
    L18:
        r162 = getThemedColor(Theme.key_chat_emojiPanelBackspace);
        goto L17
    L13:
        r16 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L14
    L8:
        r5 = getThemedColor(Theme.key_chat_emojiPanelBackspace);
        goto L10
    }

    public static String addColorToCode(String r6, String r7) {
        if (CompoundEmoji.isHandshake(r6) == null) goto L7;
        return CompoundEmoji.applyColor(r6, r7);
    L7:
        if (Emoji.endsWithRightArrow(r6) == false) goto L9;
        r6 = r6.substring(0, r6.length() - 2);
        boolean r0 = true;
    L10:
        int r3 = r6.length();
        if (r3 <= 2) goto L16;
        if (r6.charAt(r6.length() - 2) != 8205) goto L16;
        String r32 = r6.substring(r6.length() - 2);
        r6 = r6.substring(0, r6.length() - 2);
    L21:
        String r62 = r6 + r7;
        if (r32 == null) goto L24;
        r62 = r62.concat(r32);
    L24:
        if (r0 == true) goto L26;
        return r62;
    L26:
        return r62.concat("\u200d➡");
    L16:
        if (r3 > 3) goto L18;
    L20:
        r32 = null;
        goto L21
    L18:
        if (r6.charAt(r6.length() - 3) != 8205) goto L20;
        r32 = r6.substring(r6.length() - 3);
        r6 = r6.substring(0, r6.length() - 3);
        goto L21
    L9:
        r0 = false;
        goto L10
    }

    private void animateSearchField(int r6) {
        RecyclerListView r0 = getListViewForType(r6);
        boolean r1 = true;
        if (r6 != 1) goto L5;
        float r2 = 38.0f;
    L6:
        int r22 = AndroidUtilities.dp(r2);
        RecyclerView.ViewHolder r02 = r0.findViewHolderForAdapterPosition(0);
        if (r02 == null) goto L18;
        int r03 = r02.itemView.getBottom();
        int r4 = this.tabsMinusDy[r6];
        float r04 = (r03 - (r22 + r4)) / this.searchFieldHeight;
        if (r04 > 0.0f) goto L13;
        if (r04 < 1.0f) goto L13;
        return;
    L13:
        if (r04 > 0.5f) goto L16;
        r1 = false;
    L16:
        animateSearchField(r6, r1, r4);
        return;
    L18:
        return;
    L5:
        r2 = 48.0f;
        goto L6
    }

    private void animateSearchField(int r3, boolean r4, int r5) {
        if (r3 != 2) goto L6;
        return;
    L6:
        if (getListViewForType(r3).findViewHolderForAdapterPosition(0) != null) goto L8;
        return;
    L8:
        AnonymousClass36 r0 = new AnonymousClass36(this, getContext(), r5);
        r0.setTargetPosition(!r4 ? 1 : 0);
        getLayoutManagerForType(r3).startSmoothScroll(r0);
    }

    private void animateTabsY(int r8) {
        EmojiViewDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
    L6:
        if (r8 != 0) goto L9;
        return;
    L9:
        if (r8 != 1) goto L11;
        float r1 = 36.0f;
    L12:
        float r12 = AndroidUtilities.dpf2(r1);
        float r2 = this.tabsMinusDy[r8] / (-r12);
        if (r2 > 0.0f) goto L15;
    L31:
        animateSearchField(r8);
        return;
    L15:
        if (r2 >= 1.0f) goto L31;
        View r3 = getTabsForType(r8);
        if (r2 <= 0.5f) goto L20;
        int r13 = (int) (-Math.ceil(r12));
    L21:
        if (r2 <= 0.5f) goto L23;
        animateSearchField(r8, false, r13);
    L23:
        if (r8 != 1) goto L25;
        checkEmojiShadow(r13);
    L25:
        ObjectAnimator[] r22 = this.tabsYAnimators;
        ObjectAnimator r5 = r22[r8];
        if (r5 != null) goto L28;
        r22[r8] = ObjectAnimator.ofFloat(r3, View.TRANSLATION_Y, new float[]{r3.getTranslationY(), r13});
        this.tabsYAnimators[r8].addUpdateListener(new EmojiView$$ExternalSyntheticLambda25(this, r8));
        this.tabsYAnimators[r8].setDuration(200);
    L29:
        this.tabsYAnimators[r8].start();
        return;
    L28:
        r5.setFloatValues(new float[]{r3.getTranslationY(), r13});
        goto L29
    L20:
        r13 = 0;
        goto L21
    L11:
        r1 = 48.0f;
        goto L12
    L5:
        if (r0.isSearchOpened() == false) goto L6;
    }

    private static void applyBottomInsetAsPadding(View r3, int r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        r3.setPadding(r3.getPaddingLeft(), r3.getPaddingTop(), r3.getPaddingRight(), r4);
    }

    private void checkBottomTabScroll(float r5) {
        if ((SystemClock.elapsedRealtime() - this.shownBottomTabAfterClick) < ViewConfiguration.getTapTimeout()) goto L29;
        this.lastBottomScrollDy += r5;
        if (this.pager.getCurrentItem() != 0) goto L8;
        int r52 = AndroidUtilities.dp(38.0f);
    L9:
        float r0 = this.lastBottomScrollDy;
        if (r0 < r52) goto L14;
        showBottomTab(false, true);
        return;
    L14:
        if (r0 > (-r52)) goto L18;
        showBottomTab(true, true);
        return;
    L18:
        if (this.bottomTabContainer.getTag() != null) goto L22;
        if (this.lastBottomScrollDy >= 0.0f) goto L22;
    L25:
        this.lastBottomScrollDy = 0.0f;
        return;
    L22:
        if (this.bottomTabContainer.getTag() != null) goto L24;
        return;
    L24:
        if (this.lastBottomScrollDy > 0.0f) goto L25;
        return;
    L8:
        r52 = AndroidUtilities.dp(48.0f);
        goto L9
    }

    private void checkDocuments(boolean r7) {
        if (r7 == false) goto L5;
        updateRecentGifs();
        return;
    L5:
        ArrayList<TLRPC.Document> r72 = this.recentStickers;
        ArrayList<TLRPC.Document> r0 = this.favouriteStickers;
        updateRecentStickerLists();
        boolean r3 = true;
        if (r72.size() == this.recentStickers.size()) goto L8;
        boolean r1 = true;
    L10:
        if (r0.size() != this.favouriteStickers.size()) goto L13;
        r3 = false;
    L13:
        if (r1 == true) goto L15;
        if (r3 == true) goto L15;
    L16:
        boolean r73 = documentsEquals(r72, this.recentStickers);
        boolean r02 = documentsEquals(r0, this.favouriteStickers);
        StickersGridAdapter r12 = this.stickersGridAdapter;
        if (r12 != null) goto L19;
    L23:
        checkPanels();
        return;
    L19:
        if (this.stickerAdapterBuilt == false) goto L23;
        if (r73 == false) goto L22;
        if (r02 == true) goto L23;
    L22:
        r12.notifyDataSetChanged();
    L15:
        updateStickerTabs(false);
        goto L16
    L8:
        r1 = false;
        goto L10
    }

    private void checkEmojiSearchFieldScroll(boolean r7) {
        EmojiViewDelegate r0 = this.delegate;
        if (r0 == null) goto L17;
        if (r0.isSearchOpened() == false) goto L17;
        RecyclerView.ViewHolder r02 = this.emojiGridView.findViewHolderForAdapterPosition(0);
        SearchField r4 = this.emojiSearchField;
        if (r02 != null) goto L10;
        SearchField.m10543$$Nest$mshowShadow(r4, true, !r7);
    L14:
        showEmojiShadow(false, !r7);
        this.emojiSearchField.setTranslationY(this.animatorSearchEmojiPackSelected.getFloatValue() * AndroidUtilities.dp(15.0f));
        return;
    L10:
        if (r02.itemView.getTop() >= this.emojiGridView.getPaddingTop()) goto L12;
        boolean r03 = true;
    L13:
        SearchField.m10543$$Nest$mshowShadow(r4, r03, !r7);
        goto L14
    L12:
        r03 = false;
    L17:
        if (this.emojiSearchField == null) goto L27;
        EmojiGridView r04 = this.emojiGridView;
        if (r04 == null) goto L28;
        RecyclerView.ViewHolder r05 = r04.findViewHolderForAdapterPosition(0);
        if (r05 == null) goto L24;
        int r06 = r05.itemView.getTop();
    L25:
        this.emojiSearchField.setTranslationY(r06 + (this.animatorSearchEmojiPackSelected.getFloatValue() * AndroidUtilities.dp(15.0f)));
        SearchField.m10543$$Nest$mshowShadow(this.emojiSearchField, false, !r7);
        checkEmojiShadow(Math.round(this.emojiTabs.getTranslationY()));
        return;
    L24:
        r06 = -this.searchFieldHeight;
        goto L25
    L28:
        return;
    }

    private void checkEmojiSearchFieldVisibility() {
        float r0 = 1.0f - this.animatorSearchEmojiPackSelected.getFloatValue();
        this.emojiSearchField.setAlpha(r0);
        SearchField r2 = this.emojiSearchField;
        int r5 = 4;
        if (r0 <= 0.0f) goto L5;
        int r4 = 0;
    L6:
        r2.setVisibility(r4);
        float r1 = 1.0f - r0;
        this.emojiSearchHeader.setAlpha(r1);
        this.emojiSearchHeader.setTranslationY((-AndroidUtilities.dp(15.0f)) * r0);
        FoundStickerPacksHeaderCell r22 = this.emojiSearchHeader;
        if (r1 <= 0.0f) goto L9;
        int r42 = 0;
    L10:
        r22.setVisibility(r42);
        this.emojiAddPackButtonContainer.setAlpha(r1);
        this.emojiAddPackButtonContainer.setTranslationY(AndroidUtilities.dp(30.0f) * r0);
        FoundStickerPackButtonContainer r7 = this.emojiAddPackButtonContainer;
        if (r1 <= 0.0f) goto L13;
        r5 = 0;
    L13:
        r7.setVisibility(r5);
        return;
    L9:
        r42 = 4;
        goto L10
    L5:
        r4 = 4;
        goto L6
    }

    private void checkEmojiShadow(int r5) {
        ObjectAnimator r0 = this.tabsYAnimators[1];
        if (r0 != null) goto L5;
    L7:
        boolean r2 = false;
        RecyclerView.ViewHolder r02 = this.emojiGridView.findViewHolderForAdapterPosition(0);
        int r3 = AndroidUtilities.dp(38.0f) + r5;
        if (r3 <= 0) goto L13;
        if (r02 != null) goto L11;
    L12:
        r2 = true;
        goto L13
    L11:
        if (r02.itemView.getBottom() < r3) goto L12;
    L13:
        showEmojiShadow(r2, !this.isLayout);
        return;
    L5:
        if (r0.isRunning() == false) goto L7;
    }

    private void checkEmojiTabY(View r4, int r5) {
        if (r4 != null) goto L7;
        EmojiTabsStrip r42 = this.emojiTabs;
        this.tabsMinusDy[1] = 0;
        r42.setTranslationY(0.0f);
        return;
    L7:
        if (r4.getVisibility() == 0) goto L9;
        return;
    L9:
        if (this.emojiSmoothScrolling == true) goto L39;
        EmojiViewDelegate r43 = this.delegate;
        if (r43 != null) goto L14;
    L16:
        if (r5 <= 0) goto L30;
        EmojiGridView r44 = this.emojiGridView;
        if (r44 == null) goto L30;
        if (r44.getVisibility() != 0) goto L30;
        RecyclerView.ViewHolder r45 = this.emojiGridView.findViewHolderForAdapterPosition(0);
        if (r45 == null) goto L30;
        int r46 = r45.itemView.getTop();
        if (this.needEmojiSearch == false) goto L26;
        int r2 = this.searchFieldHeight;
    L28:
        if ((r46 + r2) < this.emojiGridView.getPaddingTop()) goto L30;
        return;
    L26:
        r2 = 0;
    L30:
        int[] r47 = this.tabsMinusDy;
        int r22 = r47[1] - r5;
        r47[1] = r22;
        if (r22 <= 0) goto L34;
        r47[1] = 0;
    L36:
        this.emojiTabs.setTranslationY(Math.max(-AndroidUtilities.dp(36.0f), this.tabsMinusDy[1]));
        return;
    L34:
        if (r22 >= (-AndroidUtilities.dp(108.0f))) goto L36;
        this.tabsMinusDy[1] = -AndroidUtilities.dp(108.0f);
        goto L36
    L14:
        if (r43.isSearchOpened() == false) goto L16;
        return;
    }

    private void checkGifSearchFieldScroll(boolean r8) {
        RecyclerListView r0 = this.gifGridView;
        if (r0 != null) goto L5;
    L17:
        EmojiViewDelegate r02 = this.delegate;
        if (r02 != null) goto L20;
    L30:
        SearchField r03 = this.gifSearchField;
        if (r03 != null) goto L33;
        return;
    L33:
        if (this.gifGridView == null) goto L38;
        SearchField.m10543$$Nest$mshowShadow(r03, true, !r8);
        return;
    L38:
        return;
    L20:
        if (r02.isSearchOpened() == false) goto L30;
        boolean r2 = false;
        RecyclerView.ViewHolder r04 = this.gifGridView.findViewHolderForAdapterPosition(0);
        SearchField r3 = this.gifSearchField;
        if (r04 != null) goto L26;
        SearchField.m10543$$Nest$mshowShadow(r3, true, !r8);
        return;
    L26:
        if (r04.itemView.getTop() >= this.gifGridView.getPaddingTop()) goto L28;
        r2 = true;
    L28:
        SearchField.m10543$$Nest$mshowShadow(r3, r2, !r8);
        return;
    L5:
        if ((r0.getAdapter() instanceof GifAdapter) == false) goto L17;
        GifAdapter r1 = (GifAdapter) this.gifGridView.getAdapter();
        if (GifAdapter.m10513$$Nest$fgetsearchEndReached(r1) == true) goto L17;
        if (GifAdapter.m10511$$Nest$fgetreqId(r1) != 0) goto L17;
        if (GifAdapter.m10512$$Nest$fgetresults(r1).isEmpty() == true) goto L17;
        int r05 = this.gifLayoutManager.findLastVisibleItemPosition();
        if (r05 == (-1)) goto L17;
        if (r05 <= (this.gifLayoutManager.getItemCount() - 5)) goto L17;
        r1.search(GifAdapter.m10506$$Nest$fgetlastSearchImageString(r1), GifAdapter.m10508$$Nest$fgetnextSearchOffset(r1), true, GifAdapter.m10507$$Nest$fgetlastSearchIsEmoji(r1), GifAdapter.m10507$$Nest$fgetlastSearchIsEmoji(r1));
        goto L17
    }

    private void checkGridVisibility(int r5, float r6) {
        if (this.stickersContainer != null) goto L5;
        return;
    L5:
        if (this.gifContainer == null) goto L43;
        int r1 = 0;
        if (r5 != 0) goto L21;
        this.emojiGridView.setVisibility(0);
        RecyclerListView r52 = this.gifGridView;
        if (r6 != 0.0f) goto L12;
        int r0 = 8;
    L13:
        r52.setVisibility(r0);
        ScrollSlidingTabStrip r53 = this.gifTabs;
        if (r6 != 0.0f) goto L16;
        r1 = 8;
    L16:
        r53.setVisibility(r1);
        this.stickersGridView.setVisibility(8);
        FrameLayout r4 = this.stickersTabContainer;
        if (r4 == null) goto L39;
        r4.setVisibility(8);
        return;
    L39:
        return;
    L12:
        r0 = 0;
        goto L13
    L21:
        if (r5 != 1) goto L33;
        this.emojiGridView.setVisibility(8);
        this.gifGridView.setVisibility(0);
        this.gifTabs.setVisibility(0);
        RecyclerListView r54 = this.stickersGridView;
        if (r6 != 0.0f) goto L25;
        int r02 = 8;
    L26:
        r54.setVisibility(r02);
        FrameLayout r42 = this.stickersTabContainer;
        if (r42 == null) goto L40;
        if (r6 != 0.0f) goto L30;
        r1 = 8;
    L30:
        r42.setVisibility(r1);
        return;
    L40:
        return;
    L25:
        r02 = 0;
        goto L26
    L33:
        if (r5 != 2) goto L41;
        this.emojiGridView.setVisibility(8);
        this.gifGridView.setVisibility(8);
        this.gifTabs.setVisibility(8);
        this.stickersGridView.setVisibility(0);
        FrameLayout r43 = this.stickersTabContainer;
        if (r43 == null) goto L42;
        r43.setVisibility(0);
        return;
    L42:
        return;
    L41:
        return;
    }

    private void checkPanels() {
        if (this.stickersTab == null) goto L17;
        int r0 = this.stickersLayoutManager.findFirstVisibleItemPosition();
        if (r0 == (-1)) goto L16;
        int r1 = this.favTabNum;
        if (r1 > 0) goto L14;
        r1 = this.recentTabNum;
        if (r1 > 0) goto L14;
        r1 = this.stickersTabOffset;
    L14:
        this.stickersTab.onPageScrolled(this.stickersGridAdapter.getTabForPosition(r0), r1);
        return;
    L16:
        return;
    }

    private void checkScroll(int r3) {
        if (r3 != 0) goto L23;
        if (this.ignoreStickersScroll == true) goto L46;
        int r32 = this.stickersLayoutManager.findFirstVisibleItemPosition();
        if (r32 != (-1)) goto L11;
        return;
    L11:
        if (this.stickersGridView == null) goto L48;
        int r0 = this.favTabNum;
        if (r0 > 0) goto L20;
        r0 = this.recentTabNum;
        if (r0 > 0) goto L20;
        r0 = this.stickersTabOffset;
    L20:
        this.stickersTab.onPageScrolled(this.stickersGridAdapter.getTabForPosition(r32), r0);
        return;
    L48:
        return;
    L46:
        return;
    L23:
        if (r3 != 2) goto L41;
        RecyclerView.Adapter r33 = this.gifGridView.getAdapter();
        GifAdapter r1 = this.gifAdapter;
        if (r33 == r1) goto L27;
        return;
    L27:
        if (GifAdapter.m10514$$Nest$fgettrendingSectionItem(r1) >= 0) goto L29;
        return;
    L29:
        if (this.gifTrendingTabNum >= 0) goto L31;
        return;
    L31:
        if (this.gifRecentTabNum < 0) goto L45;
        int r34 = this.gifLayoutManager.findFirstVisibleItemPosition();
        if (r34 == (-1)) goto L49;
        ScrollSlidingTabStrip r02 = this.gifTabs;
        if (r34 < GifAdapter.m10514$$Nest$fgettrendingSectionItem(this.gifAdapter)) goto L38;
        int r2 = this.gifTrendingTabNum;
    L39:
        r02.onPageScrolled(r2, 0);
        return;
    L38:
        r2 = this.gifRecentTabNum;
        goto L39
    L49:
        return;
    L45:
        return;
    }

    private void checkStickersSearchFieldScroll(boolean r7) {
        EmojiViewDelegate r0 = this.delegate;
        boolean r2 = false;
        if (r0 == null) goto L16;
        if (r0.isSearchOpened() == false) goto L16;
        RecyclerView.ViewHolder r02 = this.stickersGridView.findViewHolderForAdapterPosition(0);
        SearchField r4 = this.stickersSearchField;
        if (r02 != null) goto L10;
        SearchField.m10543$$Nest$mshowShadow(r4, true, !r7);
    L13:
        this.stickersSearchField.setTranslationY(this.animatorSearchStickerPackSelected.getFloatValue() * AndroidUtilities.dp(15.0f));
        return;
    L10:
        if (r02.itemView.getTop() >= this.stickersGridView.getPaddingTop()) goto L12;
        r2 = true;
    L12:
        SearchField.m10543$$Nest$mshowShadow(r4, r2, !r7);
    L16:
        if (this.stickersSearchField == null) goto L26;
        RecyclerListView r03 = this.stickersGridView;
        if (r03 == null) goto L27;
        RecyclerView.ViewHolder r04 = r03.findViewHolderForAdapterPosition(0);
        if (r04 == null) goto L23;
        int r05 = r04.itemView.getTop();
    L24:
        this.stickersSearchField.setTranslationY(r05 + (this.animatorSearchStickerPackSelected.getFloatValue() * AndroidUtilities.dp(15.0f)));
        SearchField.m10543$$Nest$mshowShadow(this.stickersSearchField, false, !r7);
        return;
    L23:
        r05 = -this.searchFieldHeight;
        goto L24
    L27:
        return;
    }

    private void checkStickersSearchFieldVisibility() {
        float r0 = 1.0f - this.animatorSearchStickerPackSelected.getFloatValue();
        this.stickersSearchField.setAlpha(r0);
        SearchField r2 = this.stickersSearchField;
        int r5 = 4;
        if (r0 <= 0.0f) goto L5;
        int r4 = 0;
    L6:
        r2.setVisibility(r4);
        float r1 = 1.0f - r0;
        this.stickerSearchHeader.setAlpha(r1);
        this.stickerSearchHeader.setTranslationY((-AndroidUtilities.dp(15.0f)) * r0);
        FoundStickerPacksHeaderCell r22 = this.stickerSearchHeader;
        if (r1 <= 0.0f) goto L9;
        int r42 = 0;
    L10:
        r22.setVisibility(r42);
        this.stickerAddPackButtonContainer.setAlpha(r1);
        this.stickerAddPackButtonContainer.setTranslationY(AndroidUtilities.dp(30.0f) * r0);
        FoundStickerPackButtonContainer r7 = this.stickerAddPackButtonContainer;
        if (r1 <= 0.0f) goto L13;
        r5 = 0;
    L13:
        r7.setVisibility(r5);
        return;
    L9:
        r42 = 4;
        goto L10
    L5:
        r4 = 4;
        goto L6
    }

    private void checkTabsY(int r5, int r6) {
        if (r5 != 1) goto L6;
        checkEmojiTabY(this.emojiGridView, r6);
        return;
    L6:
        EmojiViewDelegate r0 = this.delegate;
        if (r0 == null) goto L11;
        if (r0.isSearchOpened() == false) goto L11;
        return;
    L11:
        if (this.ignoreStickersScroll == true) goto L35;
        RecyclerListView r02 = getListViewForType(r5);
        if (r6 <= 0) goto L23;
        if (r02 == null) goto L23;
        if (r02.getVisibility() != 0) goto L23;
        RecyclerView.ViewHolder r2 = r02.findViewHolderForAdapterPosition(0);
        if (r2 == null) goto L23;
        if ((r2.itemView.getTop() + this.searchFieldHeight) < r02.getPaddingTop()) goto L23;
        return;
    L23:
        int[] r03 = this.tabsMinusDy;
        int r22 = r03[r5] - r6;
        r03[r5] = r22;
        if (r22 <= 0) goto L27;
        r03[r5] = 0;
    L29:
        if (r5 != 0) goto L32;
        updateStickerTabsPosition();
        return;
    L32:
        getTabsForType(r5).setTranslationY(Math.max(-AndroidUtilities.dp(48.0f), this.tabsMinusDy[r5]));
        return;
    L27:
        if (r22 >= (-AndroidUtilities.dp(288.0f))) goto L29;
        this.tabsMinusDy[r5] = -AndroidUtilities.dp(288.0f);
        goto L29
    }

    private void createStickersChooseActionTracker() {
        AnonymousClass27 r0 = new AnonymousClass27(this, this.currentAccount, this.delegate.getDialogId(), this.delegate.getThreadId());
        this.chooseStickerActionTracker = r0;
        r0.checkVisibility();
    }

    private static boolean documentsEquals(ArrayList<TLRPC.Document> r9, ArrayList<TLRPC.Document> r10) {
        if (r9 != r10) goto L6;
        return true;
    L6:
        if (r9 == null) goto L27;
        if (r10 == null) goto L27;
        if (r9.size() != r10.size()) goto L27;
        int r2 = 0;
    L13:
        if (r2 >= r9.size()) goto L26;
        TLRPC.Document r3 = r9.get(r2);
        TLRPC.Document r4 = r10.get(r2);
        if (r3 == r4) goto L24;
        if (r3 == null) goto L25;
        if (r4 == null) goto L25;
        if (r3.id != r4.id) goto L25;
        if (r3.dc_id == r4.dc_id) goto L24;
    L25:
        return false;
    L24:
        r2 = r2 + 1;
        goto L13
    L26:
        return true;
    L27:
        return false;
    }

    private EmojiPack findEmojiPackByDocumentId(long r8) {
        if (r8 != 0) goto L5;
        return null;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= this.emojipacksProcessed.size()) goto L23;
        EmojiPack r3 = this.emojipacksProcessed.get(r2);
        if (r3 == null) goto L22;
        if (r3.documents == null) goto L22;
        int r4 = 0;
    L15:
        if (r4 >= r3.documents.size()) goto L22;
        TLRPC.Document r5 = r3.documents.get(r4);
        if (r5 == null) goto L21;
        if (r5.id != r8) goto L21;
        return r3;
    L21:
        r4 = r4 + 1;
    L22:
        r2 = r2 + 1;
        goto L7
    L23:
        return null;
    }

    private int getGlassIconColor(float r2) {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), (int) (r2 * 255.0f));
    }

    private GridLayoutManager getLayoutManagerForType(int r2) {
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L12;
        if (r2 == 2) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unexpected argument: ", r2);
        return null;
    L8:
        return this.gifLayoutManager;
    L12:
        return this.emojiLayoutManager;
    L14:
        return this.stickersLayoutManager;
    }

    private SearchField getSearchFieldForType(int r2) {
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L12;
        if (r2 == 2) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unexpected argument: ", r2);
        return null;
    L8:
        return this.gifSearchField;
    L12:
        return this.emojiSearchField;
    L14:
        return this.stickersSearchField;
    }

    private int getThemedColor(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.getColor(r1);
    L7:
        return Theme.getColor(r1);
    }

    private void invalidateBlurCaptures(int r5) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L17;
        ViewPositionWatcher.computeRectInParent(this.typeTabs, this, this.blurredRectF);
        RectF r52 = this.blurredRectF;
        float r3 = 0.0f;
        if (LiteMode.isEnabled(262144) == false) goto L9;
        float r1 = 0.0f;
    L11:
        if (LiteMode.isEnabled(262144) == true) goto L14;
        r3 = -AndroidUtilities.dp(48.0f);
    L14:
        r52.inset(r1, r3);
        this.blurredRectF.right = getMeasuredWidth();
        RectF r53 = this.blurredRectF;
        r53.bottom = Math.min(r53.bottom, getMeasuredHeight());
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.blurredRectList, !this.blurredRectF.isEmpty() ? 1 : 0);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.blurCaptureMethod, getWidth(), getHeight());
        return;
    L9:
        r1 = -AndroidUtilities.dp(48.0f);
        goto L11
    }

    private /* synthetic */ void lambda$animateTabsY$21(int r1, ValueAnimator r2) {
        this.tabsMinusDy[r1] = (int) ((Float) r2.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ boolean lambda$new$10(Theme.ResourcesProvider r8, View r9, MotionEvent r10) {
        return ContentPreviewViewer.getInstance().onTouch(r10, this.stickersGridView, getMeasuredHeight(), this.stickersOnItemClickListener, this.contentPreviewViewerDelegate, r8);
    }

    private /* synthetic */ void lambda$new$11(View r10, int r11) {
        RecyclerView.Adapter r112 = this.stickersGridView.getAdapter();
        StickersSearchGridAdapter r0 = this.stickersSearchGridAdapter;
        if (r112 != r0) goto L6;
        String r113 = StickersSearchGridAdapter.m10555$$Nest$fgetsearchQuery(r0);
    L5:
        String r3 = r113;
        if ((r10 instanceof StickerEmojiCell) == false) goto L23;
        StickerEmojiCell r1 = (StickerEmojiCell) r10;
        if (r1.getSticker() != null) goto L13;
    L18:
        ContentPreviewViewer.getInstance().reset();
        if (r1.isDisabled() == false) goto L21;
        return;
    L21:
        r1.disable();
        this.delegate.onStickerSelected(r1, r1.getSticker(), r3, r1.getParentObject(), r1.getSendAnimationData(), true, 0, 0);
        return;
    L13:
        if (MessageObject.isPremiumSticker(r1.getSticker()) == false) goto L18;
        if (AccountInstance.getInstance(this.currentAccount).getUserConfig().isPremium() == true) goto L18;
        ContentPreviewViewer.getInstance().showMenuFor(r1);
        return;
    L23:
        return;
    L6:
        r113 = null;
        goto L5
    }

    private /* synthetic */ void lambda$new$12(View r1) {
        this.stickersSearchGridAdapter.resetSelectedPackId();
    }

    private /* synthetic */ void lambda$new$13(int r5) {
        if (this.firstTabUpdate == false) goto L6;
        return;
    L6:
        if (r5 != this.trendingTabNum) goto L9;
        openTrendingStickers(null);
        return;
    L9:
        SearchField r0 = this.stickersSearchField;
        if (r0 == null) goto L15;
        if (r0.isCategorySelected() == false) goto L15;
        this.stickersSearchField.search(null, false);
        SearchField.m10535$$Nest$fgetcategoriesListView(this.stickersSearchField).selectCategory(null);
    L15:
        if (r5 != this.recentTabNum) goto L23;
        this.stickersGridView.stopScroll();
        scrollStickersToPosition(this.stickersGridAdapter.getPositionForPack("recent"), 0);
        resetTabsY(0);
        ScrollSlidingTabStrip r52 = this.stickersTab;
        int r02 = this.recentTabNum;
        if (r02 <= 0) goto L19;
        int r4 = r02;
    L20:
        r52.onPageScrolled(r02, r4);
        return;
    L19:
        r4 = this.stickersTabOffset;
        goto L20
    L23:
        if (r5 != this.favTabNum) goto L31;
        this.stickersGridView.stopScroll();
        scrollStickersToPosition(this.stickersGridAdapter.getPositionForPack("fav"), 0);
        resetTabsY(0);
        ScrollSlidingTabStrip r53 = this.stickersTab;
        int r03 = this.favTabNum;
        if (r03 <= 0) goto L27;
        int r42 = r03;
    L28:
        r53.onPageScrolled(r03, r42);
        return;
    L27:
        r42 = this.stickersTabOffset;
        goto L28
    L31:
        if (r5 != this.premiumTabNum) goto L38;
        this.stickersGridView.stopScroll();
        scrollStickersToPosition(this.stickersGridAdapter.getPositionForPack("premium"), 0);
        resetTabsY(0);
        ScrollSlidingTabStrip r54 = this.stickersTab;
        int r04 = this.premiumTabNum;
        if (r04 <= 0) goto L35;
        int r43 = r04;
    L36:
        r54.onPageScrolled(r04, r43);
        return;
    L35:
        r43 = this.stickersTabOffset;
        goto L36
    L38:
        int r05 = r5 - this.stickersTabOffset;
        if (r05 < this.stickerSets.size()) goto L42;
        return;
    L42:
        if (r05 < this.stickerSets.size()) goto L44;
        r05 = this.stickerSets.size() - 1;
    L44:
        this.firstStickersAttach = false;
        this.stickersGridView.stopScroll();
        scrollStickersToPosition(this.stickersGridAdapter.getPositionForPack(this.stickerSets.get(r05)), 0);
        resetTabsY(0);
        checkScroll(0);
        int r06 = this.favTabNum;
        if (r06 > 0) goto L51;
        r06 = this.recentTabNum;
        if (r06 > 0) goto L51;
        r06 = this.stickersTabOffset;
    L51:
        this.stickersTab.onPageScrolled(r5, r06);
        this.expandStickersByDragg = false;
        updateStickerTabsPosition();
    }

    private /* synthetic */ void lambda$new$15(View r1) {
        EmojiViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onStickersSettingsClick();
        return;
    }

    private /* synthetic */ void lambda$new$16(View r10) {
        int r102 = this.pager.getCurrentItem();
        if (r102 != 0) goto L6;
        SearchField r9 = this.emojiSearchField;
    L9:
        if (r9 != null) goto L11;
        return;
    L11:
        SearchField.m10538$$Nest$fgetsearchEditText(r9).requestFocus();
        MotionEvent r103 = MotionEvent.obtain(0, 0, 0, 0.0f, 0.0f, 0);
        SearchField.m10538$$Nest$fgetsearchEditText(r9).onTouchEvent(r103);
        r103.recycle();
        MotionEvent r104 = MotionEvent.obtain(0, 0, 1, 0.0f, 0.0f, 0);
        SearchField.m10538$$Nest$fgetsearchEditText(r9).onTouchEvent(r104);
        r104.recycle();
        return;
    L6:
        if (r102 != 1) goto L8;
        r9 = this.gifSearchField;
        goto L9
    L8:
        r9 = this.stickersSearchField;
        goto L9
    }

    private /* synthetic */ void lambda$new$17(Integer r4, Integer r5) {
        ImageViewEmoji r0 = this.emojiTouchedView;
        if (r0 != null) goto L5;
        return;
    L5:
        if ((r0.getDrawable() instanceof CompoundEmoji.CompoundEmojiDrawable) == false) goto L22;
        ((CompoundEmoji.CompoundEmojiDrawable) this.emojiTouchedView.getDrawable()).update(r4.intValue(), r5.intValue());
        String r3 = (String) this.emojiTouchedView.getTag();
        if (r4.intValue() == (-1)) goto L9;
    L11:
        StringBuilder r02 = new StringBuilder();
        int r1 = r4.intValue();
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 < 0) goto L14;
        String r42 = CompoundEmoji.skinTones.get(r4.intValue());
    L15:
        r02.append(r42);
        r02.append("\u200d");
        if (r5.intValue() < 0) goto L18;
        r2 = CompoundEmoji.skinTones.get(r5.intValue());
    L18:
        r02.append(r2);
        String r43 = r02.toString();
        Emoji.emojiColor.put(r3, r43);
    L19:
        Emoji.saveEmojiColors();
        return;
    L14:
        r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L15
    L9:
        if (r5.intValue() != (-1)) goto L11;
        Emoji.emojiColor.remove(r3);
        goto L19
    }

    private /* synthetic */ void lambda$new$18(Canvas r2, RectF r3) {
        EmojiGridView r0 = this.emojiGridView;
        Blur3Utils.captureRelativeParent(r0, r2, r3, r0, this);
        RecyclerListView r02 = this.gifGridView;
        Blur3Utils.captureRelativeParent(r02, r2, r3, r02, this);
        RecyclerListView r03 = this.stickersGridView;
        Blur3Utils.captureRelativeParent(r03, r2, r3, r03, this);
    }

    private /* synthetic */ boolean lambda$new$2(Theme.ResourcesProvider r8, View r9, MotionEvent r10) {
        return ContentPreviewViewer.getInstance().onTouch(r10, this.emojiGridView, getMeasuredHeight(), null, this.contentPreviewViewerDelegate, r8);
    }

    private /* synthetic */ void lambda$new$20(int r1, float r2, float r3, FactorAnimator r4) {
        updateBottomTabContainerPosition();
    }

    private /* synthetic */ void lambda$new$25() {
        EmojiGridAdapter r1 = this.emojiAdapter;
        if (r1 == null) goto L6;
        r1.notifyDataSetChanged(true);
        return;
    }

    private /* synthetic */ boolean lambda$new$3(View r10, int r11) {
        int r0 = 0;
        if ((r10 instanceof ImageViewEmoji) == false) goto L53;
        ImageViewEmoji r112 = (ImageViewEmoji) r10;
        if (ImageViewEmoji.m10524$$Nest$fgetisRecent(r112) == false) goto L14;
        RecyclerView.ViewHolder r113 = this.emojiGridView.findContainingViewHolder(r10);
        if (r113 != null) goto L9;
    L11:
        this.emojiGridView.clearTouchesFor(r10);
        return true;
    L9:
        if (r113.getAdapterPosition() > getRecentEmoji().size()) goto L11;
        this.delegate.onClearEmojiRecent();
        goto L11
    L14:
        if (r112.getSpan() != null) goto L53;
        String r1 = (String) r112.getTag();
        if (r1 != null) goto L18;
        return false;
    L18:
        String r12 = r1.replace("🏻", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏼", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏽", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏾", _UrlKt.FRAGMENT_ENCODE_SET).replace("🏿", _UrlKt.FRAGMENT_ENCODE_SET);
        if (ImageViewEmoji.m10524$$Nest$fgetisRecent(r112) == true) goto L21;
        String r3 = Emoji.emojiColor.get(r12);
    L22:
        boolean r4 = CompoundEmoji.isCompound(r12);
        if (r4 == false) goto L25;
    L26:
        this.emojiTouchedView = r112;
        this.emojiTouchedX = this.emojiLastX;
        this.emojiTouchedY = this.emojiLastY;
        if (r4 == false) goto L29;
        r12 = addColorToCode(r12, r3);
    L30:
        this.colorPickerView.setEmoji(r12);
        int r13 = this.colorPickerView.getPopupWidth();
        int r32 = this.colorPickerView.getPopupHeight();
        r112.getLocationOnScreen(this.location);
        if (this.colorPickerView.isCompound() == true) goto L37;
        int r42 = this.emojiSize * this.colorPickerView.getSelection();
        int r5 = this.colorPickerView.getSelection() * 4;
        if (AndroidUtilities.isTablet() == false) goto L35;
        int r6 = 5;
    L36:
        int r43 = r42 + AndroidUtilities.dp(r5 - r6);
    L38:
        int r52 = this.location[0] - r43;
        int r7 = AndroidUtilities.dp(5.0f);
        int[] r8 = this.location;
        if (r52 >= r7) goto L42;
        r43 = r43 + ((r8[0] - r43) - AndroidUtilities.dp(5.0f));
    L44:
        int r14 = -r43;
        if (r112.getTop() >= 0) goto L47;
        r0 = r112.getTop();
    L47:
        EmojiColorPickerWindow r114 = this.colorPickerView;
        if (AndroidUtilities.isTablet() == false) goto L50;
        float r44 = 30.0f;
    L51:
        r114.setupArrow((AndroidUtilities.dp(r44) - r14) + ((int) AndroidUtilities.dpf2(0.5f)));
        this.colorPickerView.setFocusable(true);
        this.colorPickerView.showAsDropDown(r10, r14, (((-r10.getMeasuredHeight()) - r32) + ((r10.getMeasuredHeight() - this.emojiSize) / 2)) - r0);
        this.pager.requestDisallowInterceptTouchEvent(true);
        this.emojiGridView.hideSelector(true);
        this.emojiGridView.clearTouchesFor(r10);
        return true;
    L50:
        r44 = 22.0f;
        goto L51
    L42:
        if (((r8[0] - r43) + r13) <= (AndroidUtilities.displaySize.x - AndroidUtilities.dp(5.0f))) goto L44;
        r43 = r43 + (((this.location[0] - r43) + r13) - (AndroidUtilities.displaySize.x - AndroidUtilities.dp(5.0f)));
        goto L44
    L35:
        r6 = 1;
        goto L36
    L37:
        r43 = 0;
        goto L38
    L29:
        this.colorPickerView.setSelection(CompoundEmoji.skinTones.indexOf(r3) + 1);
        goto L30
    L25:
        if (EmojiData.emojiColoredMap.contains(r12) == false) goto L53;
    L21:
        r3 = null;
    L53:
        return false;
    }

    private /* synthetic */ void lambda$new$5() {
        EmojiViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onEmojiSettingsClick(EmojiGridAdapter.m10467$$Nest$fgetfrozenEmojiPacks(this.emojiAdapter));
        return;
    }

    private /* synthetic */ void lambda$new$6(View r1) {
        this.emojiSearchAdapter.resetSelectedPackId();
    }

    private /* synthetic */ boolean lambda$new$7(Theme.ResourcesProvider r8, View r9, MotionEvent r10) {
        return ContentPreviewViewer.getInstance().onTouch(r10, this.gifGridView, 0, this.gifOnItemClickListener, this.contentPreviewViewerDelegate, r8);
    }

    private /* synthetic */ void lambda$new$8(View r20, int r21) {
        if (this.delegate != null) goto L6;
        return;
    L6:
        if (GifAdapter.m10504$$Nest$fgetaddSearch(this.gifAdapter) == false) goto L8;
        int r1 = r21 - 1;
    L9:
        RecyclerView.Adapter r2 = this.gifGridView.getAdapter();
        GifAdapter r3 = this.gifAdapter;
        if (r2 != r3) goto L25;
        if (r1 >= 0) goto L14;
        return;
    L14:
        if (r1 >= GifAdapter.m10510$$Nest$fgetrecentItemsCount(r3)) goto L18;
        this.delegate.onGifSelected(r20, this.recentGifs.get(r1), null, "gif", true, 0, 0);
        return;
    L18:
        if (GifAdapter.m10510$$Nest$fgetrecentItemsCount(this.gifAdapter) <= 0) goto L20;
        r1 = (r1 - GifAdapter.m10510$$Nest$fgetrecentItemsCount(this.gifAdapter)) - 1;
    L20:
        if (r1 >= 0) goto L22;
        return;
    L22:
        if (r1 >= GifAdapter.m10512$$Nest$fgetresults(this.gifAdapter).size()) goto L34;
        this.delegate.onGifSelected(r20, GifAdapter.m10512$$Nest$fgetresults(this.gifAdapter).get(r1), null, GifAdapter.m10505$$Nest$fgetbot(this.gifAdapter), true, 0, 0);
        return;
    L34:
        return;
    L25:
        RecyclerView.Adapter r22 = this.gifGridView.getAdapter();
        GifAdapter r32 = this.gifSearchAdapter;
        if (r22 != r32) goto L35;
        if (r1 >= 0) goto L29;
        return;
    L29:
        if (r1 >= GifAdapter.m10512$$Nest$fgetresults(r32).size()) goto L39;
        this.delegate.onGifSelected(r20, GifAdapter.m10512$$Nest$fgetresults(this.gifSearchAdapter).get(r1), GifAdapter.m10506$$Nest$fgetlastSearchImageString(this.gifSearchAdapter), GifAdapter.m10505$$Nest$fgetbot(this.gifSearchAdapter), true, 0, 0);
        updateRecentGifs();
        return;
    L39:
        return;
    L35:
        return;
    L8:
        r1 = r21;
        goto L9
    }

    private /* synthetic */ void lambda$new$9(int r5) {
        if (r5 == this.gifTrendingTabNum) goto L5;
    L7:
        this.gifGridView.stopScroll();
        this.gifTabs.onPageScrolled(r5, 0);
        int r2 = 1;
        if (r5 != this.gifRecentTabNum) goto L10;
    L18:
        SearchField.m10538$$Nest$fgetsearchEditText(this.gifSearchField).setText(_UrlKt.FRAGMENT_ENCODE_SET);
        if (r5 == this.gifTrendingTabNum) goto L21;
    L23:
        GifLayoutManager r0 = this.gifLayoutManager;
        EmojiViewDelegate r3 = this.delegate;
        if (r3 != null) goto L26;
    L28:
        r0.scrollToPositionWithOffset(r2, 0);
    L30:
        if (r5 != this.gifTrendingTabNum) goto L34;
        ArrayList<String> r52 = MessagesController.getInstance(this.currentAccount).gifSearchEmojies;
        if (r52.isEmpty() == true) goto L34;
        this.gifSearchPreloader.preload(r52.get(0));
    L34:
        resetTabsY(2);
        return;
    L26:
        if (r3.isExpanded() == false) goto L28;
        r2 = 0;
        goto L28
    L21:
        if (GifAdapter.m10514$$Nest$fgettrendingSectionItem(this.gifAdapter) < 1) goto L23;
        this.gifLayoutManager.scrollToPositionWithOffset(GifAdapter.m10514$$Nest$fgettrendingSectionItem(this.gifAdapter), -AndroidUtilities.dp(4.0f));
        goto L30
    L10:
        if (r5 == this.gifTrendingTabNum) goto L18;
        ArrayList<String> r02 = MessagesController.getInstance(this.currentAccount).gifSearchEmojies;
        this.gifSearchAdapter.searchEmoji(r02.get(r5 - this.gifFirstEmojiTabNum));
        int r1 = this.gifFirstEmojiTabNum;
        if ((r5 - r1) <= 0) goto L16;
        this.gifSearchPreloader.preload(r02.get((r5 - r1) - 1));
    L16:
        if ((r5 - this.gifFirstEmojiTabNum) >= (r02.size() - 1)) goto L34;
        this.gifSearchPreloader.preload(r02.get((r5 - this.gifFirstEmojiTabNum) + 1));
        goto L34
    L5:
        if (GifAdapter.m10512$$Nest$fgetresults(this.gifAdapter).isEmpty() == false) goto L7;
    }

    private /* synthetic */ void lambda$onOpen$23() {
        ArrayList<EmojiPack> r0 = getEmojipacks();
        int r2 = 0;
    L4:
        if (r2 >= r0.size()) goto L9;
        if (r0.get(r2).forGroup == false) goto L8;
        int r3 = EmojiGridAdapter.m10474$$Nest$fgetsectionToPosition(this.emojiAdapter).get(EmojiData.dataColored.length + r2);
        this.emojiGridView.stopScroll();
        updateEmojiTabsPosition(r3);
        scrollEmojisToPosition(r3, AndroidUtilities.dp(-9.0f));
        checkEmojiTabY(null, 0);
    L8:
        r2 = r2 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$openEmojiPackAlert$19(TLRPC.InputStickerSet r8, TLRPC.Document r9) {
        if (getContext() != null) goto L6;
        this.emojiPackAlertOpened = false;
        return;
    L6:
        ArrayList r5 = new ArrayList(1);
        r5.add(r8);
        AnonymousClass28 r0 = new AnonymousClass28(this, this.fragment, getContext(), this.resourcesProvider, r5, r8);
        if (r9 == null) goto L9;
        r0.setPreviewEmoji(r9);
    L9:
        BaseFragment r7 = this.fragment;
        if (r7 == null) goto L13;
        r7.showDialog(r0);
        return;
    L13:
        r0.show();
    }

    private /* synthetic */ void lambda$postBackspaceRunnable$22(int r3) {
        if (this.backspacePressed == true) goto L5;
        return;
    L5:
        EmojiViewDelegate r0 = this.delegate;
        if (r0 != null) goto L8;
    L10:
        this.backspaceOnce = true;
        postBackspaceRunnable(Math.max(50, r3 - 100));
        return;
    L8:
        if (r0.onBackspace() == false) goto L10;
        this.backspaceButton.performHapticFeedback(3);     // Catch: Exception -> L12
        goto L10
    }

    private /* synthetic */ void lambda$sendEmoji$0(long r4, TLRPC.Document r6, String r7, ImageViewEmoji r8) {
        ExteraConfig.getPreferences().edit().putBoolean("local_premium_emoji_hint" + this.currentAccount, true).apply();
        this.shownBottomTabAfterClick = SystemClock.elapsedRealtime();
        showBottomTab(true, true);
        addEmojiToRecent("animated_" + r4);
        this.delegate.onCustomEmojiSelected(r4, r6, r7, ImageViewEmoji.m10524$$Nest$fgetisRecent(r8));
    }

    private /* synthetic */ void lambda$sendEmoji$1() {
        Bundle r0 = new Bundle();
        r0.putLong("user_id", UserConfig.getInstance(this.currentAccount).getClientUserId());
        this.fragment.presentFragment(new AnonymousClass3(this, r0));
    }

    private /* synthetic */ void lambda$setFoundPackButtonText$26(FoundStickerPackButton r8, TLObject r9, TLRPC.StickerSet r10, TLRPC.Document r11, boolean r12) {
        setFoundPackButtonText(r8, r9, r10, r11, r12, true);
    }

    private /* synthetic */ void lambda$setFoundPackButtonText$27(TLObject r14, TLRPC.Document r15, boolean r16, FoundStickerPackButton r17, TLRPC.StickerSet r18, boolean r19, View r20) {
        MediaDataController r7 = MediaDataController.getInstance(this.currentAccount);
        Context r8 = getContext();
        if (r16 == false) goto L6;
        int r1 = 0;
    L5:
        int r9 = r1;
        r7.toggleStickerSet(r8, r14, r15, r9, this.fragment, this.bulletinContainer2, false, true, new EmojiView$$ExternalSyntheticLambda29(this, r17, r14, r18, r15, r19), false);
        setFoundPackButtonText(r17, r14, r18, r15, r19, true);
        return;
    L6:
        r1 = 2;
        goto L5
    }

    private /* synthetic */ void lambda$showStickerBanHint$24(boolean r2, boolean r3) {
        showStickerBanHint(false, r2, r3);
    }

    private boolean needsStickerAdapterBuild() {
        if (this.stickerAdapterBuilt == false) goto L5;
        return false;
    L5:
        if (this.lastNotifyWidth == 0) goto L10;
        return true;
    L10:
        return false;
    }

    private void onPageScrolled(int r3, int r4, int r5) {
        EmojiViewDelegate r2 = this.delegate;
        if (r2 != null) goto L5;
        return;
    L5:
        int r0 = 0;
        if (r3 != 1) goto L11;
        if (r5 == 0) goto L9;
        r0 = 2;
    L9:
        r2.onTabOpened(r0);
        return;
    L11:
        if (r3 != 2) goto L14;
        r2.onTabOpened(3);
        return;
    L14:
        r2.onTabOpened(0);
    }

    private void openEmojiPackAlert(TLRPC.InputStickerSet r11, TLRPC.Document r12) {
        if (r11 != null) goto L4;
        return;
    L4:
        if (this.emojiPackAlertOpened == true) goto L26;
        TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSet(r11, true);
        if (r0 == null) goto L23;
        TLRPC.StickerSet r02 = r0.set;
        if (r02 == null) goto L23;
        if (r02.emojis == true) goto L23;
        Context r3 = getContext();
        BaseFragment r4 = this.fragment;
        if ((r4 instanceof ChatActivity) == false) goto L16;
        ChatActivityEnterView r122 = ((ChatActivity) r4).getChatActivityEnterView();
    L15:
        ChatActivityEnterView r7 = r122;
        StickersAlert r2 = new StickersAlert(r3, r4, r11, null, r7, this.resourcesProvider, false);
        BaseFragment r10 = this.fragment;
        if (r10 == null) goto L21;
        r10.showDialog(r2);
        return;
    L21:
        r2.show();
        return;
    L16:
        r122 = null;
    L23:
        this.emojiPackAlertOpened = true;
        AndroidUtilities.runOnUIThread(new EmojiView$$ExternalSyntheticLambda21(this, r11, r12), 16);
        return;
    }

    private void openPremiumAnimatedEmojiFeature() {
        EmojiViewDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onAnimatedEmojiUnlockClick();
        return;
    }

    private void openSearch(SearchField r15) {
        AnimatorSet r0 = this.searchAnimation;
        if (r0 == null) goto L5;
        r0.cancel();
        this.searchAnimation = null;
    L5:
        this.firstStickersAttach = false;
        this.firstGifAttach = false;
        this.firstEmojiAttach = false;
        int r1 = 0;
    L6:
        boolean r3 = true;
        if (r1 >= 3) goto L56;
        if (r1 != 0) goto L10;
        SearchField r4 = this.emojiSearchField;
        RecyclerListView r5 = this.emojiGridView;
        View r6 = this.emojiTabs;
        LinearLayoutManager r7 = this.emojiLayoutManager;
    L13:
        if (r4 == null) goto L55;
        if (r15 != r4) goto L30;
        EmojiViewDelegate r11 = this.delegate;
        if (r11 == null) goto L30;
        if (r11.isExpanded() == false) goto L30;
        AnimatorSet r72 = new AnimatorSet();
        this.searchAnimation = r72;
        Property r12 = View.TRANSLATION_Y;
        if (r6 == null) goto L25;
        if (r1 == 2) goto L25;
        r72.playTogether(new Animator[]{ObjectAnimator.ofFloat(r6, r12, new float[]{-AndroidUtilities.dp(40.0f)}), ObjectAnimator.ofFloat(r5, r12, new float[]{-AndroidUtilities.dp(36.0f)}), ObjectAnimator.ofFloat(r4, r12, new float[]{AndroidUtilities.dp(0.0f)})});
    L29:
        this.searchAnimation.setDuration(220);
        this.searchAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.searchAnimation.addListener(new AnonymousClass30(this, r5));
        this.searchAnimation.start();
    L25:
        if (r1 != 2) goto L27;
        float r2 = 0.0f;
    L28:
        r72.playTogether(new Animator[]{ObjectAnimator.ofFloat(r5, r12, new float[]{r2}), ObjectAnimator.ofFloat(r4, r12, new float[]{AndroidUtilities.dp(0.0f)})});
        goto L29
    L27:
        r2 = -AndroidUtilities.dp(36.0f);
    L30:
        r4.setTranslationY(AndroidUtilities.dp(0.0f));
        if (r6 == null) goto L35;
        if (r1 == 2) goto L35;
        r6.setTranslationY(-AndroidUtilities.dp(40.0f));
    L35:
        if (r5 != this.stickersGridView) goto L38;
        r5.setPadding(0, 0, 0, this.bottomInset);
    L44:
        if (r5 != this.gifGridView) goto L54;
        GifAdapter r22 = this.gifSearchAdapter;
        if (GifAdapter.m10512$$Nest$fgetresults(this.gifAdapter).size() > 0) goto L49;
        r3 = false;
    L49:
        GifAdapter.m10515$$Nest$fputshowTrendingWhenSearchEmpty(r22, r3);
        if (r3 == false) goto L54;
        this.gifSearchAdapter.search(_UrlKt.FRAGMENT_ENCODE_SET);
        RecyclerView.Adapter r23 = this.gifGridView.getAdapter();
        GifAdapter r32 = this.gifSearchAdapter;
        if (r23 == r32) goto L54;
        this.gifGridView.setAdapter(r32);
    L54:
        r7.scrollToPositionWithOffset(0, 0);
        goto L55
    L38:
        if (r5 != this.emojiGridView) goto L41;
        r5.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f), this.bottomInset);
        goto L44
    L41:
        if (r5 != this.gifGridView) goto L44;
        r5.setPadding(0, this.searchFieldHeight, 0, this.bottomInset);
    L55:
        r1 = r1 + 1;
        goto L6
    L10:
        if (r1 != 1) goto L12;
        r4 = this.gifSearchField;
        r5 = this.gifGridView;
        r6 = this.gifTabs;
        r7 = this.gifLayoutManager;
        goto L13
    L12:
        r4 = this.stickersSearchField;
        r5 = this.stickersGridView;
        r6 = this.stickersTab;
        r7 = this.stickersLayoutManager;
        goto L13
    L56:
        showBottomTab(false, true);
    }

    private void openTrendingStickers(TLRPC.StickerSetCovered r10) {
        AnonymousClass29 r2 = new AnonymousClass29(this);
        this.delegate.showTrendingStickersAlert(new TrendingStickersLayout(getContext(), r2, this.primaryInstallingStickerSets, this.installingStickerSets, this.removingStickerSets, r10, this.resourcesProvider));
    }

    private void postBackspaceRunnable(int r2) {
        AndroidUtilities.runOnUIThread(new EmojiView$$ExternalSyntheticLambda26(this, r2), r2);
    }

    private void reloadStickersAdapter() {
        StickersGridAdapter r0 = this.stickersGridAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        StickersSearchGridAdapter r1 = this.stickersSearchGridAdapter;
        if (r1 == null) goto L9;
        r1.notifyDataSetChanged();
    L9:
        if (ContentPreviewViewer.getInstance().isVisible() == false) goto L11;
        ContentPreviewViewer.getInstance().close();
    L11:
        ContentPreviewViewer.getInstance().reset();
    }

    private void resetTabsY(int r3) {
        EmojiViewDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
    L6:
        if (r3 == 0) goto L10;
        View r02 = getTabsForType(r3);
        this.tabsMinusDy[r3] = 0;
        r02.setTranslationY(0.0f);
        return;
    L10:
        return;
    L5:
        if (r0.isSearchOpened() == false) goto L6;
    }

    private TLRPC.Document resolveEmojiDocument(long r4, TLRPC.Document r6, EmojiPack r7) {
        if (r6 == null) goto L4;
        return r6;
    L4:
        if (r7 != null) goto L7;
        r7 = findEmojiPackByDocumentId(r4);
    L7:
        if (r7 == null) goto L20;
        if (r7.documents == null) goto L20;
        int r62 = 0;
    L12:
        if (r62 >= r7.documents.size()) goto L20;
        TLRPC.Document r0 = r7.documents.get(r62);
        if (r0 == null) goto L18;
        if (r0.id != r4) goto L18;
        return r0;
    L18:
        r62 = r62 + 1;
    L20:
        return AnimatedEmojiDrawable.findDocument(this.currentAccount, r4);
    }

    private void saveNewPage() {
        ViewPager r0 = this.pager;
        if (r0 == null) goto L16;
        int r02 = r0.getCurrentItem();
        int r1 = 1;
        if (r02 == 2) goto L12;
        if (r02 != 1) goto L10;
        r1 = 2;
        goto L12
    L10:
        r1 = 0;
    L12:
        if (this.currentPage == r1) goto L15;
        this.currentPage = r1;
        MessagesController.getGlobalEmojiSettings().edit().putInt("selected_page", r1).apply();
        return;
    L15:
        return;
    }

    private void scrollEmojisToPosition(int r5, int r6) {
        View r0 = this.emojiLayoutManager.findViewByPosition(r5);
        int r1 = this.emojiLayoutManager.findFirstVisibleItemPosition();
        if (r0 != null) goto L7;
        if (Math.abs(r5 - r1) <= (this.emojiLayoutManager.getSpanCount() * 9.0f)) goto L7;
    L8:
        RecyclerAnimationScrollHelper r02 = this.emojiScrollHelper;
        if (this.emojiLayoutManager.findFirstVisibleItemPosition() >= r5) goto L11;
        int r12 = 0;
    L12:
        r02.setScrollDirection(r12);
        this.emojiScrollHelper.scrollToPosition(r5, r6, false, true);
        return;
    L11:
        r12 = 1;
    L7:
        if (SharedConfig.animationsEnabled() == false) goto L8;
        this.ignoreStickersScroll = true;
        AnonymousClass32 r03 = new AnonymousClass32(this, this.emojiGridView.getContext(), 2);
        r03.setTargetPosition(r5);
        r03.setOffset(r6);
        this.emojiLayoutManager.startSmoothScroll(r03);
    }

    private void scrollGifsToTop() {
        this.gifLayoutManager.scrollToPositionWithOffset(0, 0);
        resetTabsY(2);
    }

    private void scrollStickersToPosition(int r5, int r6) {
        View r0 = this.stickersLayoutManager.findViewByPosition(r5);
        int r1 = this.stickersLayoutManager.findFirstVisibleItemPosition();
        if (r0 == null) goto L5;
    L12:
        this.ignoreStickersScroll = true;
        this.stickersGridView.smoothScrollToPosition(r5);
        return;
    L5:
        if (Math.abs(r5 - r1) <= 40) goto L12;
        RecyclerAnimationScrollHelper r02 = this.stickersScrollHelper;
        if (this.stickersLayoutManager.findFirstVisibleItemPosition() >= r5) goto L9;
        int r12 = 0;
    L10:
        r02.setScrollDirection(r12);
        this.stickersScrollHelper.scrollToPosition(r5, r6, false, true);
        return;
    L9:
        r12 = 1;
        goto L10
    }

    private void sendEmoji(ImageViewEmoji r14, String r15) {
        if (r14 != null) goto L5;
        return;
    L5:
        if (r14.getSpan() != null) goto L7;
        this.shownBottomTabAfterClick = SystemClock.elapsedRealtime();
        showBottomTab(true, true);
        if (r15 == null) goto L60;
        String r13 = r15;
    L61:
        new SpannableStringBuilder().append(r13);
        if (r15 == null) goto L64;
        EmojiViewDelegate r132 = this.delegate;
        if (r132 == null) goto L78;
        r132.onEmojiSelected(Emoji.fixEmoji(r15));
        return;
    L78:
        return;
    L64:
        if (ImageViewEmoji.m10524$$Nest$fgetisRecent(r14) == true) goto L68;
        String r142 = Emoji.emojiColor.get(r13);
        if (r142 == null) goto L68;
        r13 = addColorToCode(r13, r142);
    L68:
        addEmojiToRecent(r13);
        EmojiViewDelegate r143 = this.delegate;
        if (r143 == null) goto L77;
        r143.onEmojiSelected(Emoji.fixEmoji(r13));
        return;
    L77:
        return;
    L60:
        r13 = (String) r14.getTag();
        goto L61
    L7:
        if (this.delegate == null) goto L76;
        long r3 = r14.getSpan().documentId;
        if (ImageViewEmoji.m10525$$Nest$fgetpack(r14) != null) goto L11;
        ImageViewEmoji.m10527$$Nest$fputpack(r14, findEmojiPackByDocumentId(r3));
    L11:
        TLRPC.Document r5 = resolveEmojiDocument(r3, r14.getSpan().document, ImageViewEmoji.m10525$$Nest$fgetpack(r14));
        if (ImageViewEmoji.m10525$$Nest$fgetpack(r14) != null) goto L14;
    L16:
        boolean r152 = false;
    L17:
        if (r5 == null) goto L20;
        String r2 = MessageObject.findAnimatedEmojiEmoticon(r5);
    L19:
        String r6 = r2;
        if (MessageObject.isFreeEmoji(r5) == false) goto L24;
    L30:
        ImageViewEmoji r8 = r14;
        EmojiView r32 = this;
        long r133 = r3;
    L55:
        r32.shownBottomTabAfterClick = SystemClock.elapsedRealtime();
        r32.showBottomTab(true, true);
        r32.addEmojiToRecent("animated_" + r133);
        r32.delegate.onCustomEmojiSelected(r133, r5, r6, ImageViewEmoji.m10524$$Nest$fgetisRecent(r8));
        return;
    L24:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L30;
        EmojiViewDelegate r22 = this.delegate;
        if (r22 != null) goto L28;
    L31:
        if (r152 == true) goto L30;
        BaseFragment r153 = this.fragment;
        if (r153 == null) goto L35;
        BulletinFactory r154 = BulletinFactory.of(r153);
    L37:
        if (this.allowEmojisForNonPremium == true) goto L39;
    L44:
        r8 = r14;
        r32 = this;
        r133 = r3;
        if (r32.allowEmojisForNonPremium == true) goto L55;
        r32.showBottomTab(false, true);
        if (r32.premiumBulletin == false) goto L49;
    L52:
        r154.createEmojiBulletin(r5, AndroidUtilities.replaceTags(LocaleController.getString(R.string.UnlockPremiumEmojiHint)), LocaleController.getString(R.string.PremiumMore), new EmojiView$$ExternalSyntheticLambda23(r32)).show();
    L53:
        r32.premiumBulletin = !r32.premiumBulletin;
        return;
    L49:
        if (r32.fragment == null) goto L52;
        r154.createSimpleBulletin(R.raw.saved_messages, AndroidUtilities.replaceTags(LocaleController.getString(R.string.UnlockPremiumEmojiHint2)), LocaleController.getString(R.string.Open), new EmojiView$$ExternalSyntheticLambda24(r32)).show();
        goto L53
    L39:
        if (this.showLocalPremiumEmojiHint == false) goto L44;
        if (ExteraConfig.getPreferences().getBoolean("local_premium_emoji_hint" + this.currentAccount, false) == true) goto L44;
        showBottomTab(false, true);
        r154.createEmojiBulletin(r5, LocaleController.getString(R.string.LocalPremiumEmojiHint), LocaleController.getString(R.string.OK), new EmojiView$$ExternalSyntheticLambda22(this, r3, r5, r6, r14)).show();
        return;
    L35:
        r154 = BulletinFactory.of(this.bulletinContainer, this.resourcesProvider);
        goto L37
    L28:
        if (r22.isUserSelf() == true) goto L30;
    L20:
        r2 = null;
        goto L19
    L14:
        if (ImageViewEmoji.m10525$$Nest$fgetpack(r14).forGroup == false) goto L16;
        r152 = true;
        goto L17
    }

    private void setFoundPackButtonText(FoundStickerPackButton r10, TLObject r11, TLRPC.StickerSet r12, TLRPC.Document r13, boolean r14, boolean r15) {
        if (r12 == null) goto L32;
        if (r14 == false) goto L10;
        EmojiSearchAdapter r2 = this.emojiSearchAdapter;
        if (r2 == null) goto L10;
        if (EmojiSearchAdapter.m10493$$Nest$fgetselectedPackId(r2) == r12.id) goto L10;
        return;
    L10:
        if (r14 == true) goto L16;
        StickersSearchGridAdapter r22 = this.stickersSearchGridAdapter;
        if (r22 == null) goto L16;
        if (StickersSearchGridAdapter.m10557$$Nest$fgetselectedPackId(r22) == r12.id) goto L16;
        return;
    L16:
        boolean r4 = MediaDataController.getInstance(this.currentAccount).isStickerPackInstalled(r12.id);
        boolean r23 = r12.masks;
        if (r4 == false) goto L24;
        if (r23 == false) goto L20;
        String r24 = LocaleController.formatPluralString("RemoveManyMasksCount", r12.count, new Object[0]);
    L30:
        r10.setText(r24, r15);
        r10.setIsPrimary(!r4, r15);
        r10.setOnClickListener(new EmojiView$$ExternalSyntheticLambda28(this, r11, r13, r4, r10, r12, r14));
        return;
    L20:
        boolean r25 = r12.emojis;
        int r7 = r12.count;
        if (r25 == false) goto L23;
        r24 = LocaleController.formatPluralString("RemoveManyEmojiCount", r7, new Object[0]);
        goto L30
    L23:
        r24 = LocaleController.formatPluralString("RemoveManyStickersCount", r7, new Object[0]);
        goto L30
    L24:
        if (r23 == false) goto L26;
        r24 = LocaleController.formatPluralString("AddManyMasksCount", r12.count, new Object[0]);
        goto L30
    L26:
        boolean r26 = r12.emojis;
        int r72 = r12.count;
        if (r26 == false) goto L29;
        r24 = LocaleController.formatPluralString("AddManyEmojiCount", r72, new Object[0]);
        goto L30
    L29:
        r24 = LocaleController.formatPluralString("AddManyStickersCount", r72, new Object[0]);
        goto L30
    }

    private void showBackspaceButton(boolean r9, boolean r10) {
        if (r9 == true) goto L4;
    L5:
        if (r9 == true) goto L9;
        if (this.backspaceButton.getTag() == null) goto L9;
        return;
    L9:
        if (this.mForceHideBackspaceButton == true) goto L53;
        AnimatorSet r0 = this.backspaceButtonAnimation;
        Integer r1 = null;
        if (r0 == null) goto L14;
        r0.cancel();
        this.backspaceButtonAnimation = null;
    L14:
        ImageView r02 = this.backspaceButton;
        if (r9 == true) goto L18;
        r1 = 1;
    L18:
        r02.setTag(r1);
        int r03 = 0;
        float r12 = 0.0f;
        if (r10 == false) goto L35;
        if (r9 == false) goto L22;
        this.backspaceButton.setVisibility(0);
    L22:
        AnimatorSet r102 = new AnimatorSet();
        this.backspaceButtonAnimation = r102;
        ImageView r4 = this.backspaceButton;
        if (r9 == false) goto L25;
        float r5 = 1.0f;
    L26:
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{r5});
        ImageView r52 = this.backspaceButton;
        if (r9 == false) goto L29;
        float r6 = 1.0f;
    L30:
        ObjectAnimator r53 = ObjectAnimator.ofFloat(r52, View.SCALE_X, new float[]{r6});
        ImageView r62 = this.backspaceButton;
        if (r9 == false) goto L33;
        r12 = 1.0f;
    L33:
        r102.playTogether(new Animator[]{r42, r53, ObjectAnimator.ofFloat(r62, View.SCALE_Y, new float[]{r12})});
        this.backspaceButtonAnimation.setDuration(200);
        this.backspaceButtonAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.backspaceButtonAnimation.addListener(new AnonymousClass34(this, r9));
        this.backspaceButtonAnimation.start();
        return;
    L29:
        r6 = 0.0f;
        goto L30
    L25:
        r5 = 0.0f;
        goto L26
    L35:
        ImageView r103 = this.backspaceButton;
        if (r9 == false) goto L38;
        float r2 = 1.0f;
    L39:
        r103.setAlpha(r2);
        ImageView r104 = this.backspaceButton;
        if (r9 == false) goto L42;
        float r22 = 1.0f;
    L43:
        r104.setScaleX(r22);
        ImageView r105 = this.backspaceButton;
        if (r9 == false) goto L46;
        r12 = 1.0f;
    L46:
        r105.setScaleY(r12);
        ImageView r8 = this.backspaceButton;
        if (r9 == true) goto L50;
        r03 = 4;
    L50:
        r8.setVisibility(r03);
        return;
    L42:
        r22 = 0.0f;
        goto L43
    L38:
        r2 = 0.0f;
        goto L39
    L53:
        return;
    L4:
        if (this.backspaceButton.getTag() != null) goto L5;
    }

    private void showBottomTab(boolean r3, boolean r4) {
        this.lastBottomScrollDy = 0.0f;
        EmojiViewDelegate r0 = this.delegate;
        if (r0 != null) goto L5;
    L7:
        if (r3 == true) goto L9;
    L10:
        if (r3 == false) goto L12;
    L14:
        FrameLayout r02 = this.bottomTabContainer;
        if (r3 == false) goto L17;
        Integer r1 = null;
    L18:
        r02.setTag(r1);
        this.bottomTabVisibility.setValue(r3, r4);
        return;
    L17:
        r1 = 1;
        goto L18
    L12:
        if (this.bottomTabContainer.getTag() == null) goto L14;
        return;
    L9:
        if (this.bottomTabContainer.getTag() != null) goto L10;
        return;
    L5:
        if (r0.isSearchOpened() == false) goto L7;
        r3 = false;
        goto L7
    }

    private void showEmojiShadow(boolean r5, boolean r6) {
        if (r5 == true) goto L4;
    L5:
        if (r5 == false) goto L7;
    L9:
        AnimatorSet r0 = this.emojiTabShadowAnimator;
        Integer r1 = null;
        if (r0 == null) goto L12;
        r0.cancel();
        this.emojiTabShadowAnimator = null;
    L12:
        View r02 = this.emojiTabsShadow;
        if (r5 == true) goto L16;
        r1 = 1;
    L16:
        r02.setTag(r1);
        float r03 = 0.0f;
        if (r6 == false) goto L23;
        AnimatorSet r62 = new AnimatorSet();
        this.emojiTabShadowAnimator = r62;
        View r3 = this.emojiTabsShadow;
        if (r5 == false) goto L21;
        r03 = 1.0f;
    L21:
        r62.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, View.ALPHA, new float[]{r03})});
        this.emojiTabShadowAnimator.setDuration(200);
        this.emojiTabShadowAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.emojiTabShadowAnimator.addListener(new AnonymousClass31(this));
        this.emojiTabShadowAnimator.start();
        return;
    L23:
        View r4 = this.emojiTabsShadow;
        if (r5 == false) goto L26;
        r03 = 1.0f;
    L26:
        r4.setAlpha(r03);
        return;
    L7:
        if (this.emojiTabsShadow.getTag() == null) goto L9;
        return;
    L4:
        if (this.emojiTabsShadow.getTag() != null) goto L5;
    }

    private void showStickerSettingsButton(boolean r9, boolean r10) {
        ImageView r0 = this.stickerSettingsButton;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mForceHideSettingsButton == true) goto L57;
        if (r9 == true) goto L9;
    L10:
        if (r9 == false) goto L12;
    L14:
        AnimatorSet r02 = this.stickersButtonAnimation;
        Integer r1 = null;
        if (r02 == null) goto L17;
        r02.cancel();
        this.stickersButtonAnimation = null;
    L17:
        ImageView r03 = this.stickerSettingsButton;
        if (r9 == true) goto L21;
        r1 = 1;
    L21:
        r03.setTag(r1);
        int r04 = 0;
        float r12 = 0.0f;
        if (r10 == false) goto L38;
        if (r9 == false) goto L25;
        this.stickerSettingsButton.setVisibility(0);
    L25:
        AnimatorSet r102 = new AnimatorSet();
        this.stickersButtonAnimation = r102;
        ImageView r4 = this.stickerSettingsButton;
        if (r9 == false) goto L28;
        float r5 = 1.0f;
    L29:
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{r5});
        ImageView r52 = this.stickerSettingsButton;
        if (r9 == false) goto L32;
        float r6 = 1.0f;
    L33:
        ObjectAnimator r53 = ObjectAnimator.ofFloat(r52, View.SCALE_X, new float[]{r6});
        ImageView r62 = this.stickerSettingsButton;
        if (r9 == false) goto L36;
        r12 = 1.0f;
    L36:
        r102.playTogether(new Animator[]{r42, r53, ObjectAnimator.ofFloat(r62, View.SCALE_Y, new float[]{r12})});
        this.stickersButtonAnimation.setDuration(200);
        this.stickersButtonAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.stickersButtonAnimation.addListener(new AnonymousClass35(this, r9));
        this.stickersButtonAnimation.start();
        return;
    L32:
        r6 = 0.0f;
        goto L33
    L28:
        r5 = 0.0f;
        goto L29
    L38:
        ImageView r103 = this.stickerSettingsButton;
        if (r9 == false) goto L41;
        float r2 = 1.0f;
    L42:
        r103.setAlpha(r2);
        ImageView r104 = this.stickerSettingsButton;
        if (r9 == false) goto L45;
        float r22 = 1.0f;
    L46:
        r104.setScaleX(r22);
        ImageView r105 = this.stickerSettingsButton;
        if (r9 == false) goto L49;
        r12 = 1.0f;
    L49:
        r105.setScaleY(r12);
        ImageView r8 = this.stickerSettingsButton;
        if (r9 == true) goto L53;
        r04 = 4;
    L53:
        r8.setVisibility(r04);
        return;
    L45:
        r22 = 0.0f;
        goto L46
    L41:
        r2 = 0.0f;
        goto L42
    L12:
        if (this.stickerSettingsButton.getTag() == null) goto L14;
        return;
    L9:
        if (r0.getTag() != null) goto L10;
        return;
    }

    private void startStopVisibleGifs(boolean r6) {
        RecyclerListView r0 = this.gifGridView;
        if (r0 == null) goto L14;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L19;
        View r3 = this.gifGridView.getChildAt(r2);
        if ((r3 instanceof ContextLinkCell) == false) goto L13;
        ImageReceiver r32 = ((ContextLinkCell) r3).getPhotoImage();
        if (r6 == false) goto L12;
        r32.setAllowStartAnimation(true);
        r32.startAnimation();
        goto L13
    L12:
        r32.setAllowStartAnimation(false);
        r32.stopAnimation();
    L13:
        r2 = r2 + 1;
        goto L6
    L19:
        return;
    }

    private void stopAnimatingTabsY(int r2) {
        ObjectAnimator r0 = this.tabsYAnimators[r2];
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isRunning() == false) goto L9;
        this.tabsYAnimators[r2].cancel();
        return;
    }

    private void syncRecentEmojiVersion() {
        if (this.frozen == true) goto L6;
        this.lastRecentEmojiVersion = Emoji.recentEmojiVersion;
        return;
    }

    private void updateBottomTabContainerPosition() {
        View r0 = (View) getParent();
        if (r0 == null) goto L32;
        float r1 = getY();
        if (getLayoutParams().height <= 0) goto L8;
        int r2 = getLayoutParams().height;
    L7:
        float r12 = r1 + r2;
        if (AndroidUtilities.isInMultiwindow == true) goto L16;
        BaseFragment r22 = this.fragment;
        if (r22 != null) goto L14;
    L17:
        int r02 = r0.getHeight();
    L18:
        float r13 = r12 - r02;
        if (this.visibleInAppKeyboardHeight < 0.0f) goto L24;
        r13 = r13 + (getMeasuredHeight() - this.visibleInAppKeyboardHeight);
    L28:
        float r03 = (-r13) + AndroidUtilities.lerp(AndroidUtilities.dp(50.0f), -this.bottomInset, this.bottomTabVisibility.getFloatValue());
        this.bottomTabContainer.setTranslationY(r03);
        if (this.needEmojiSearch == false) goto L33;
        this.bulletinContainer.setTranslationY(r03);
        return;
    L33:
        return;
    L24:
        if ((this.bottomTabContainer.getTop() - r13) >= 0.0f) goto L26;
    L27:
        r13 = 0.0f;
        goto L28
    L26:
        if (this.fixBottomTabContainerTranslation == true) goto L28;
    L14:
        if (r22.isInBubbleMode() == false) goto L17;
    L16:
        if (this.isNewHeightControl == true) goto L17;
        r02 = AndroidUtilities.dp(1.0f);
        goto L18
    L8:
        r2 = getMeasuredHeight();
        goto L7
    }

    private void updateEmojiHeaders() {
        if (this.emojiGridView == null) goto L12;
        int r0 = 0;
    L7:
        if (r0 >= this.emojiGridView.getChildCount()) goto L16;
        View r1 = this.emojiGridView.getChildAt(r0);
        if ((r1 instanceof EmojiPackHeader) == false) goto L11;
        ((EmojiPackHeader) r1).updateState(true);
    L11:
        r0 = r0 + 1;
        goto L7
    L16:
        return;
    }

    private void updateEmojiTabsPosition() {
        updateEmojiTabsPosition(this.emojiLayoutManager.findFirstCompletelyVisibleItemPosition());
    }

    private void updateEmojiTabsPosition(int r9) {
        if (this.emojiSmoothScrolling == true) goto L52;
        int r0 = -1;
        if (r9 == (-1)) goto L53;
        int r1 = getRecentEmoji().size() + (this.needEmojiSearch ? 1 : 0);
        int r3 = 0;
        if (EmojiGridAdapter.m10475$$Nest$fgettrendingHeaderRow(this.emojiAdapter) < 0) goto L9;
        int r2 = 3;
    L10:
        int r12 = r1 + r2;
        if (r9 < r12) goto L39;
        int r22 = 0;
    L13:
        String[][] r4 = EmojiData.dataColored;
        if (r22 >= r4.length) goto L19;
        r12 = r12 + (r4[r22].length + 1);
        if (r9 < r12) goto L17;
        r22 = r22 + 1;
        goto L13
    L17:
        r0 = r22 + 1;
    L19:
        if (r0 >= 0) goto L38;
        ArrayList<EmojiPack> r13 = getEmojipacks();
        int r23 = EmojiGridAdapter.m10468$$Nest$fgetpackStartPosition(this.emojiAdapter).size() - 1;
    L21:
        if (r23 < 0) goto L38;
        if (((Integer) EmojiGridAdapter.m10468$$Nest$fgetpackStartPosition(this.emojiAdapter).get(r23)).intValue() <= r9) goto L24;
        r23 = r23 - 1;
        goto L21
    L24:
        EmojiPack r92 = this.emojipacksProcessed.get(r23);
    L26:
        if (r3 >= r13.size()) goto L38;
        long r42 = r13.get(r3).set.id;
        long r6 = r92.set.id;
        if (r42 != r6) goto L36;
        if (r92.featured == false) goto L35;
        if (r92.installed == true) goto L36;
        if (this.installedEmojiSets.contains(Long.valueOf(r6)) == true) goto L36;
    L35:
        r3 = (EmojiData.dataColored.length + 1) + r3;
    L36:
        r3 = r3 + 1;
    L38:
        r3 = r0;
    L39:
        if (r3 < 0) goto L54;
        this.emojiTabs.select(r3);
        return;
    L54:
        return;
    L9:
        r2 = 0;
        goto L10
    L53:
        return;
    }

    private void updateGifTabs() {
        int r0 = this.gifTabs.getCurrentPosition();
        int r1 = this.gifRecentTabNum;
        if (r0 != r1) goto L5;
        boolean r4 = true;
    L6:
        if (r1 < 0) goto L8;
        boolean r12 = true;
    L9:
        boolean r5 = this.recentGifs.isEmpty();
        this.gifTabs.beginUpdate(false);
        this.gifRecentTabNum = -2;
        this.gifTrendingTabNum = -2;
        this.gifFirstEmojiTabNum = -2;
        if (r5 == true) goto L12;
        this.gifRecentTabNum = 0;
        this.gifTabs.addIconTab(0, this.gifIcons[0]).setContentDescription(LocaleController.getString(R.string.RecentStickers));
        int r6 = 1;
    L13:
        this.gifTrendingTabNum = r6;
        this.gifTabs.addIconTab(1, this.gifIcons[1]).setContentDescription(LocaleController.getString(R.string.FeaturedGifs));
        this.gifFirstEmojiTabNum = r6 + 1;
        AndroidUtilities.dp(13.0f);
        AndroidUtilities.dp(11.0f);
        ArrayList<String> r62 = MessagesController.getInstance(this.currentAccount).gifSearchEmojies;
        int r7 = r62.size();
        int r8 = 0;
    L14:
        if (r8 >= r7) goto L19;
        String r9 = r62.get(r8);
        Emoji.EmojiDrawable r10 = Emoji.getEmojiDrawable(r9);
        if (r10 == null) goto L18;
        this.gifTabs.addEmojiTab(r8 + 3, r10, MediaDataController.getInstance(this.currentAccount).getEmojiAnimatedSticker(r9)).setContentDescription(r9);
    L18:
        r8 = r8 + 1;
        goto L14
    L19:
        this.gifTabs.commitUpdate();
        this.gifTabs.updateTabStyles();
        if (r4 == false) goto L29;
        if (r5 == false) goto L29;
        this.gifTabs.selectTab(this.gifTrendingTabNum);
        SearchField r02 = this.gifSearchField;
        if (r02 != null) goto L25;
        return;
    L25:
        if (SearchField.m10535$$Nest$fgetcategoriesListView(r02) == null) goto L42;
        SearchField.m10535$$Nest$fgetcategoriesListView(this.gifSearchField).selectCategory(SearchField.m10541$$Nest$fgettrending(this.gifSearchField));
        return;
    L42:
        return;
    L29:
        if (ViewCompat.isLaidOut(this.gifTabs) == false) goto L43;
        if (r5 == true) goto L34;
        if (r12 == true) goto L34;
        this.gifTabs.onPageScrolled(r0 + 1, 0);
        return;
    L34:
        if (r5 == false) goto L44;
        if (r12 == false) goto L45;
        this.gifTabs.onPageScrolled(r0 - 1, 0);
        return;
    L45:
        return;
    L44:
        return;
    L43:
        return;
    L12:
        r6 = 0;
        goto L13
    L8:
        r12 = false;
        goto L9
    L5:
        r4 = false;
        goto L6
    }

    private void updateRecentGifs() {
        int r0 = this.recentGifs.size();
        long r3 = MediaDataController.calcDocumentsHash(this.recentGifs, Integer.MAX_VALUE);
        ArrayList<TLRPC.Document> r1 = MediaDataController.getInstance(this.currentAccount).getRecentGifs();
        this.recentGifs = r1;
        long r12 = MediaDataController.calcDocumentsHash(r1, Integer.MAX_VALUE);
        if (this.gifTabs == null) goto L7;
        if (r0 != 0) goto L7;
        if (this.recentGifs.isEmpty() == true) goto L7;
    L10:
        updateGifTabs();
    L12:
        if (r0 == this.recentGifs.size()) goto L14;
    L15:
        GifAdapter r6 = this.gifAdapter;
        if (r6 == null) goto L20;
        r6.notifyDataSetChanged();
        return;
    L20:
        return;
    L14:
        if (r3 != r12) goto L15;
        return;
    L7:
        if (r0 == 0) goto L12;
        if (this.recentGifs.isEmpty() == false) goto L12;
        goto L10
    }

    private void updateRecentStickerLists() {
        int r1 = 0;
        this.recentStickers = MediaDataController.getInstance(this.currentAccount).getRecentStickers(0, true);
        this.favouriteStickers = MediaDataController.getInstance(this.currentAccount).getRecentStickers(2);
        this.premiumStickers = new ArrayList();
        int r0 = 0;
    L4:
        if (r0 >= this.favouriteStickers.size()) goto L16;
        TLRPC.Document r3 = this.favouriteStickers.get(r0);
        int r4 = 0;
    L7:
        if (r4 >= this.recentStickers.size()) goto L14;
        TLRPC.Document r5 = this.recentStickers.get(r4);
        if (r5.dc_id != r3.dc_id) goto L13;
        if (r5.id != r3.id) goto L13;
        this.recentStickers.remove(r4);
    L13:
        r4 = r4 + 1;
    L14:
        r0 = r0 + 1;
        goto L4
    L16:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == false) goto L30;
        int r02 = 0;
    L19:
        if (r02 >= this.favouriteStickers.size()) goto L25;
        if (MessageObject.isPremiumSticker(this.favouriteStickers.get(r02)) == false) goto L23;
        this.favouriteStickers.remove(r02);
        r02 = r02 - 1;
    L23:
        r02 = r02 + 1;
    L25:
        if (r1 >= this.recentStickers.size()) goto L43;
        if (MessageObject.isPremiumSticker(this.recentStickers.get(r1)) == false) goto L29;
        this.recentStickers.remove(r1);
        r1 = r1 - 1;
    L29:
        r1 = r1 + 1;
        goto L25
    L43:
        return;
    }

    private void updateStickerTabs(boolean r13) {
        ScrollSlidingTabStrip r0 = this.stickersTab;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isDragging() == true) goto L166;
        this.recentTabNum = -2;
        this.favTabNum = -2;
        this.trendingTabNum = -2;
        this.premiumTabNum = -2;
        this.hasChatStickers = false;
        this.stickersTabOffset = 0;
        int r1 = this.stickersTab.getCurrentPosition();
        ScrollSlidingTabStrip r2 = this.stickersTab;
        if (getParent() != null) goto L10;
    L16:
        boolean r3 = false;
    L17:
        r2.beginUpdate(r3);
        MediaDataController r22 = MediaDataController.getInstance(this.currentAccount);
        MessagesController.getEmojiSettings(this.currentAccount);
        this.featuredStickerSets.clear();
        r22.getFeaturedStickerSets();
        TrendingAdapter r32 = this.trendingAdapter;
        if (r32 == null) goto L21;
        r32.notifyDataSetChanged();
    L21:
        if (this.favouriteStickers.isEmpty() == true) goto L24;
        int r33 = this.stickersTabOffset;
        this.favTabNum = r33;
        this.stickersTabOffset = r33 + 1;
        StickerTabView r34 = this.stickersTab.addStickerIconTab(1, this.stickerIcons[1]);
        r34.textView.setText(LocaleController.getString(R.string.FavoriteStickersShort));
        r34.setContentDescription(LocaleController.getString(R.string.FavoriteStickers));
    L24:
        if (this.recentStickers.isEmpty() == true) goto L26;
        int r35 = this.stickersTabOffset;
        this.recentTabNum = r35;
        this.stickersTabOffset = r35 + 1;
        StickerTabView r36 = this.stickersTab.addStickerIconTab(0, this.stickerIcons[0]);
        r36.textView.setText(LocaleController.getString(R.string.RecentStickersShort));
        r36.setContentDescription(LocaleController.getString(R.string.RecentStickers));
    L26:
        this.stickerSets.clear();
        this.groupStickerSet = null;
        this.groupStickerPackPosition = -1;
        this.groupStickerPackNum = -10;
        if (this.frozenStickerSets == null) goto L29;
        if (r13 == true) goto L29;
    L30:
        ArrayList<TLRPC.TL_messages_stickerSet> r132 = this.frozenStickerSets;
        int r5 = 0;
    L31:
        TLRPC.StickerSetCovered[] r6 = this.primaryInstallingStickerSets;
        if (r5 >= r6.length) goto L52;
        TLRPC.StickerSetCovered r62 = r6[r5];
        if (r62 == null) goto L51;
        TLRPC.TL_messages_stickerSet r7 = r22.getStickerSetById(r62.set.id);
        if (r7 == null) goto L42;
        TLRPC.StickerSet r72 = r7.set;
        if (r72 == null) goto L42;
        if (r72.archived == true) goto L42;
        this.primaryInstallingStickerSets[r5] = null;
    L42:
        TLRPC.TL_messages_stickerSet r73 = new TLRPC.TL_messages_stickerSet();
        r73.set = r62.set;
        TLRPC.Document r8 = r62.cover;
        if (r8 == null) goto L46;
        r73.documents.add(r8);
    L49:
        if (r73.documents.isEmpty() == true) goto L51;
        this.stickerSets.add(r73);
        goto L51
    L46:
        if (r62.covers.isEmpty() == true) goto L49;
        r73.documents.addAll(r62.covers);
    L51:
        r5 = r5 + 1;
        goto L31
    L52:
        ArrayList<TLRPC.TL_messages_stickerSet> r133 = MessagesController.getInstance(this.currentAccount).filterPremiumStickers(r132);
        int r52 = 0;
    L54:
        if (r52 >= r133.size()) goto L67;
        TLRPC.TL_messages_stickerSet r63 = r133.get(r52);
        TLRPC.StickerSet r74 = r63.set;
        if (r74 != null) goto L58;
    L59:
        ArrayList<TLRPC.Document> r75 = r63.documents;
        if (r75 == null) goto L65;
        if (r75.isEmpty() == true) goto L65;
        this.stickerSets.add(r63);
    L65:
        r52 = r52 + 1;
        goto L54
    L58:
        if (r74.archived == true) goto L65;
    L67:
        if (this.info == null) goto L110;
        long r82 = MessagesController.getEmojiSettings(this.currentAccount).getLong("group_hide_stickers_" + this.info.id, -1);
        TLRPC.Chat r134 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.info.id));
        if (this.info.stickerset == null) goto L81;
        if (ChatObject.hasAdminRights(r134) == false) goto L81;
        TLRPC.StickerSet r135 = this.info.stickerset;
        if (r135 != null) goto L76;
    L85:
        TLRPC.ChatFull r136 = this.info;
        TLRPC.StickerSet r53 = r136.stickerset;
        if (r53 == null) goto L105;
        TLRPC.TL_messages_stickerSet r137 = r22.getGroupStickerSetById(r53);
        if (r137 == null) goto L110;
        ArrayList<TLRPC.Document> r23 = r137.documents;
        if (r23 == null) goto L110;
        if (r23.isEmpty() == true) goto L110;
        if (r137.set == null) goto L110;
        TLRPC.TL_messages_stickerSet r24 = new TLRPC.TL_messages_stickerSet();
        r24.documents = r137.documents;
        r24.packs = r137.packs;
        r24.set = r137.set;
        if (this.groupStickersHidden == false) goto L98;
        this.groupStickerPackNum = this.stickerSets.size();
        this.stickerSets.add(r24);
    L100:
        if (this.info.can_set_stickers == true) goto L103;
        r24 = null;
    L103:
        this.groupStickerSet = r24;
        goto L110
    L98:
        this.groupStickerPackNum = 0;
        this.stickerSets.add(0, r24);
        goto L100
    L105:
        if (r136.can_set_stickers == false) goto L110;
        TLRPC.TL_messages_stickerSet r138 = new TLRPC.TL_messages_stickerSet();
        if (this.groupStickersHidden == false) goto L109;
        this.groupStickerPackNum = this.stickerSets.size();
        this.stickerSets.add(r138);
        goto L110
    L109:
        this.groupStickerPackNum = 0;
        this.stickerSets.add(0, r138);
        goto L110
    L76:
        if (r82 != r135.id) goto L78;
        boolean r139 = true;
    L79:
        this.groupStickersHidden = r139;
        goto L85
    L78:
        r139 = false;
    L81:
        if (r82 == (-1)) goto L83;
        boolean r1310 = true;
    L84:
        this.groupStickersHidden = r1310;
        goto L85
    L83:
        r1310 = false;
    L110:
        int r1311 = 0;
    L112:
        if (r1311 >= this.stickerSets.size()) goto L142;
        if (r1311 != this.groupStickerPackNum) goto L119;
        TLRPC.Chat r25 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.info.id));
        if (r25 != null) goto L118;
        this.stickerSets.remove(0);
        r1311 = r1311 - 1;
    L141:
        r1311 = r1311 + 1;
        goto L112
    L118:
        this.hasChatStickers = true;
        this.stickersTab.addStickerTab(r25);
        goto L141
    L119:
        TLRPC.TL_messages_stickerSet r26 = this.stickerSets.get(r1311);
        TLRPC.StickerSet r54 = r26.set;
        if (r54 != null) goto L122;
    L132:
        TLRPC.Document r64 = null;
    L133:
        if (r64 != null) goto L135;
        r64 = r26.documents.get(0);
    L135:
        TLObject r55 = FileLoader.getClosestPhotoSizeWithSize(r26.set.thumbs, 90);
        if (r55 != null) goto L138;
    L139:
        r55 = r64;
    L140:
        this.stickersTab.addStickerTab(r55, r64, r26).setContentDescription(r26.set.title + ", " + LocaleController.getString(R.string.AccDescrStickerSet));
        goto L141
    L138:
        if (r26.set.gifs == false) goto L140;
    L122:
        if (r54.thumb_document_id == 0) goto L132;
        int r56 = 0;
    L125:
        if (r56 >= r26.documents.size()) goto L132;
        r64 = r26.documents.get(r56);
        if (r64 == null) goto L131;
        if (r26.set.thumb_document_id == r64.id) goto L133;
    L131:
        r56 = r56 + 1;
        goto L125
    L142:
        this.stickersTab.commitUpdate();
        this.stickersTab.updateTabStyles();
        if (r1 == 0) goto L145;
        this.stickersTab.onPageScrolled(r1, r1);
    L145:
        checkPanels();
        return;
    L29:
        this.frozenStickerSets = new ArrayList(r22.getStickerSets(0));
        goto L30
    L10:
        if (getVisibility() != 0) goto L16;
        if (this.installingStickerSets.size() == 0) goto L14;
    L15:
        r3 = true;
        goto L17
    L14:
        if (this.removingStickerSets.size() == 0) goto L16;
    }

    private void updateStickerTabsPosition() {
        ScrollSlidingTabStrip r0 = this.stickersTab;
        if (r0 == null) goto L10;
        if (this.stickersTabContainer != null) goto L10;
        if (this.delegate == null) goto L10;
        r0.setTranslationY((-AndroidUtilities.dp(50.0f)) * this.delegate.getProgressToSearchOpened());
    L10:
        if (this.stickersTabContainer != null) goto L13;
        return;
    L13:
        if (getVisibility() == 0) goto L15;
    L19:
        boolean r02 = false;
    L20:
        FrameLayout r4 = this.stickersTabContainer;
        if (r02 == false) goto L23;
        int r5 = 0;
    L24:
        r4.setVisibility(r5);
        if (r02 == false) goto L35;
        this.rect.setEmpty();
        this.pager.getChildVisibleRect(this.stickersContainer, this.rect, null);
        float r1 = AndroidUtilities.dp(50.0f) * this.delegate.getProgressToSearchOpened();
        int r42 = this.rect.left;
        if (r42 == 0) goto L29;
    L30:
        this.expandStickersByDragg = false;
    L31:
        this.stickersTabContainer.setTranslationX(r42);
        float r43 = (((getTop() + getTranslationY()) - this.stickersTabContainer.getTop()) - this.stickersTab.getExpandedOffset()) - r1;
        if (this.stickersTabContainer.getTranslationY() == r43) goto L35;
        this.stickersTabContainer.setTranslationY(r43);
        this.stickersTabContainer.invalidate();
        goto L35
    L29:
        if (r1 == 0.0f) goto L31;
    L35:
        if (this.expandStickersByDragg == false) goto L41;
        if (r02 == false) goto L41;
        if (this.showing == false) goto L41;
        this.stickersTab.expandStickers(this.lastStickersX, true);
        return;
    L41:
        this.expandStickersByDragg = false;
        this.stickersTab.expandStickers(this.lastStickersX, false);
        return;
    L23:
        r5 = 8;
        goto L24
    L15:
        if (this.stickersContainerAttached == false) goto L19;
        if (this.delegate.getProgressToSearchOpened() == 1.0f) goto L19;
        r02 = true;
        goto L20
    }

    private void updateVisibleTrendingSets() {
        RecyclerListView r0 = this.stickersGridView;
        if (r0 != null) goto L59;
        return;
    L59:
        int r02 = r0.getChildCount();     // Catch: Exception -> L56
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L69;
        View r3 = this.stickersGridView.getChildAt(r2);     // Catch: Exception -> L56
        if ((r3 instanceof FeaturedStickerSetInfoCell) == false) goto L54;
        if (((RecyclerListView.Holder) this.stickersGridView.getChildViewHolder(r3)) == null) goto L54;
        FeaturedStickerSetInfoCell r5 = (FeaturedStickerSetInfoCell) r3;     // Catch: Exception -> L56
        ArrayList<Long> r32 = MediaDataController.getInstance(this.currentAccount).getUnreadStickerSets();     // Catch: Exception -> L56
        TLRPC.StickerSetCovered r6 = r5.getStickerSet();     // Catch: Exception -> L56
        if (r32 != null) goto L15;
    L17:
        boolean r7 = false;
    L18:
        int r33 = 0;
    L19:
        TLRPC.StickerSetCovered[] r8 = this.primaryInstallingStickerSets;     // Catch: Exception -> L56
        if (r33 >= r8.length) goto L27;
        TLRPC.StickerSetCovered r82 = r8[r33];     // Catch: Exception -> L56
        if (r82 == null) goto L26;
        if (r82.set.id != r6.set.id) goto L26;
        boolean r11 = true;
    L28:
        r5.setStickerSet(r6, r7, true, 0, 0, r11);     // Catch: Exception -> L56
        if (r7 == false) goto L32;
        MediaDataController.getInstance(this.currentAccount).markFeaturedStickersByIdAsRead(false, r6.set.id);     // Catch: Exception -> L56
    L32:
        if (this.installingStickerSets.indexOfKey(r6.set.id) < 0) goto L34;
        boolean r34 = true;
    L36:
        if (this.removingStickerSets.indexOfKey(r6.set.id) < 0) goto L38;
        boolean r72 = true;
    L39:
        if (r34 == true) goto L41;
        if (r72 == true) goto L41;
    L49:
        if (r11 == true) goto L52;
        if (r34 == false) goto L52;
        boolean r35 = true;
    L53:
        r5.setAddDrawProgress(r35, true);     // Catch: Exception -> L56
    L52:
        r35 = false;
    L41:
        if (r34 == true) goto L43;
    L45:
        if (r72 == false) goto L49;
        if (r5.isInstalled() == true) goto L49;
        this.removingStickerSets.remove(r6.set.id);     // Catch: Exception -> L56
        goto L49
    L43:
        if (r5.isInstalled() == false) goto L45;
        this.installingStickerSets.remove(r6.set.id);     // Catch: Exception -> L56
        r34 = false;
        goto L49
    L38:
        r72 = false;
        goto L39
    L34:
        r34 = false;
    L26:
        r33 = r33 + 1;     // Catch: Exception -> L56
        goto L19
    L27:
        r11 = false;
        goto L28
    L15:
        if (r32.contains(Long.valueOf(r6.set.id)) == false) goto L17;
        r7 = true;
    L54:
        r2 = r2 + 1;
        goto L6
    L69:
        return;
    L56:
        e = move-exception;
        FileLog.e(e);
    }

    public void addEmojiToRecent(String r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r4.startsWith("animated_") == false) goto L6;
    L8:
        Emoji.addRecentEmoji(r4);
        if (getVisibility() == 0) goto L11;
    L12:
        Emoji.sortEmoji();
        this.emojiAdapter.notifyDataSetChanged();
        syncRecentEmojiVersion();
    L13:
        Emoji.saveRecentEmoji();
        if (this.allowAnimatedEmoji == true) goto L32;
        ArrayList<String> r42 = this.lastRecentArray;
        if (r42 != null) goto L18;
        this.lastRecentArray = new ArrayList();
    L19:
        int r43 = 0;
    L20:
        ArrayList<String> r1 = Emoji.recentEmoji;
        if (r43 >= r1.size()) goto L26;
        if (r1.get(r43).startsWith("animated_") == true) goto L25;
        this.lastRecentArray.add(r1.get(r43));
    L25:
        r43 = r43 + 1;
        goto L20
    L26:
        this.lastRecentCount = this.lastRecentArray.size();
        return;
    L18:
        r42.clear();
        goto L19
    L32:
        return;
    L11:
        if (this.pager.getCurrentItem() == 0) goto L13;
    L6:
        if (Emoji.isValidEmoji(r4) == true) goto L8;
    }

    public void addRecentGif(TLRPC.Document r1) {
        if (r1 == null) goto L9;
        boolean r12 = this.recentGifs.isEmpty();
        updateRecentGifs();
        if (r12 == false) goto L8;
        updateStickerTabs(false);
        return;
    L8:
        return;
    }

    public void addRecentSticker(TLRPC.Document r8) {
        if (r8 == null) goto L11;
        MediaDataController.getInstance(this.currentAccount).addRecentSticker(0, null, r8, (int) (System.currentTimeMillis() / 1000), false);
        boolean r82 = this.recentStickers.isEmpty();
        this.recentStickers = MediaDataController.getInstance(this.currentAccount).getRecentStickers(0, true);
        StickersGridAdapter r0 = this.stickersGridAdapter;
        if (r0 == null) goto L7;
        r0.notifyDataSetChanged();
    L7:
        if (r82 == false) goto L10;
        updateStickerTabs(false);
        return;
    L10:
        return;
    }

    public void allowEmojisForNonPremium(boolean r1) {
        allowEmojisForNonPremium(r1, r1);
    }

    public void allowEmojisForNonPremium(boolean r1, boolean r2) {
        this.showLocalPremiumEmojiHint = r2;
        if (this.allowEmojisForNonPremium == r1) goto L10;
        this.allowEmojisForNonPremium = r1;
        EmojiGridAdapter r0 = this.emojiAdapter;
        if (r0 == null) goto L9;
        r0.notifyDataSetChanged(true);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.inset.InAppKeyboardInsetView
    public void applyInAppKeyboardAnimatedHeight(float r1) {
        this.visibleInAppKeyboardHeight = r1;
        updateBottomTabContainerPosition();
    }

    @Override // org.telegram.ui.Components.inset.InAppKeyboardInsetView
    public void applyNavigationBarHeight(int r1) {
        setBottomInset(r1);
    }

    public boolean areThereAnyStickers() {
        StickersGridAdapter r0 = this.stickersGridAdapter;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getItemCount() <= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public void clearRecentEmoji() {
        Emoji.clearRecentEmoji();
        this.emojiAdapter.notifyDataSetChanged();
        syncRecentEmojiVersion();
    }

    public void closeSearch(boolean r3) {
        closeSearch(r3, -1);
    }

    public void closeSearch(boolean r17, long r18) {
        AnimatorSet r3 = this.searchAnimation;
        StickerCategoriesListView.EmojiCategory r4 = null;
        if (r3 == null) goto L5;
        r3.cancel();
        this.searchAnimation = null;
    L5:
        int r32 = this.pager.getCurrentItem();
        if (r32 == 2) goto L8;
    L16:
        GifAdapter r1 = this.gifSearchAdapter;
        if (r1 == null) goto L19;
        GifAdapter.m10515$$Nest$fputshowTrendingWhenSearchEmpty(r1, false);
    L19:
        int r12 = 0;
    L21:
        if (r12 >= 3) goto L59;
        if (r12 != 0) goto L25;
        SearchField r8 = this.emojiSearchField;
        RecyclerListView r9 = this.emojiGridView;
        GridLayoutManager r10 = this.emojiLayoutManager;
        View r11 = this.emojiTabs;
    L28:
        if (r8 == null) goto L58;
        SearchField.m10538$$Nest$fgetsearchEditText(r8).setText(_UrlKt.FRAGMENT_ENCODE_SET);
        if (SearchField.m10535$$Nest$fgetcategoriesListView(r8) == null) goto L34;
        SearchField.m10535$$Nest$fgetcategoriesListView(r8).selectCategory(r4);
        SearchField.m10535$$Nest$fgetcategoriesListView(r8).scrollToStart();
    L34:
        if (r12 != r32) goto L43;
        if (r17 == false) goto L43;
        AnimatorSet r14 = new AnimatorSet();
        this.searchAnimation = r14;
        Property r15 = View.TRANSLATION_Y;
        if (r11 == null) goto L40;
        if (r12 == 1) goto L40;
        r14.playTogether(new Animator[]{ObjectAnimator.ofFloat(r11, r15, new float[]{0.0f}), ObjectAnimator.ofFloat(r9, r15, new float[]{AndroidUtilities.dp(36.0f)}), ObjectAnimator.ofFloat(r8, r15, new float[]{AndroidUtilities.dp(36.0f)})});
    L41:
        this.searchAnimation.setDuration(200);
        this.searchAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.searchAnimation.addListener(new AnonymousClass33(this, r10, r9));
        this.searchAnimation.start();
    L40:
        r14.playTogether(new Animator[]{ObjectAnimator.ofFloat(r9, r15, new float[]{AndroidUtilities.dp(36.0f) - this.searchFieldHeight})});
    L43:
        if (r8 == this.gifSearchField) goto L45;
        r8.setTranslationY(AndroidUtilities.dp(36.0f) - this.searchFieldHeight);
    L45:
        if (r11 == null) goto L49;
        if (r12 == 2) goto L49;
        r11.setTranslationY(0.0f);
    L49:
        if (r9 != this.stickersGridView) goto L52;
        r9.setPadding(0, AndroidUtilities.dp(36.0f), 0, AndroidUtilities.dp(44.0f) + this.bottomInset);
    L57:
        r10.scrollToPositionWithOffset(0, 0);
        goto L58
    L52:
        if (r9 != this.gifGridView) goto L55;
        r9.setPadding(0, AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(44.0f) + this.bottomInset);
        goto L57
    L55:
        if (r9 != this.emojiGridView) goto L57;
        r9.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(36.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(44.0f) + this.bottomInset);
    L58:
        r12 = r12 + 1;
        r4 = null;
        goto L21
    L25:
        if (r12 != 1) goto L27;
        r8 = this.gifSearchField;
        r9 = this.gifGridView;
        r10 = this.gifLayoutManager;
        r11 = this.gifTabs;
        goto L28
    L27:
        r8 = this.stickersSearchField;
        r9 = this.stickersGridView;
        r10 = this.stickersLayoutManager;
        r11 = this.stickersTab;
        goto L28
    L59:
        if (r17 == true) goto L66;
        this.delegate.onSearchOpenClose(0);
        return;
    L66:
        return;
    L8:
        if (r18 == (-1)) goto L16;
        TLRPC.TL_messages_stickerSet r13 = MediaDataController.getInstance(this.currentAccount).getStickerSetById(r18);
        if (r13 == null) goto L16;
        int r16 = this.stickersGridAdapter.getPositionForPack(r13);
        if (r16 < 0) goto L16;
        if (r16 >= this.stickersGridAdapter.getItemCount()) goto L16;
        scrollStickersToPosition(r16, AndroidUtilities.dp(48.0f));
        goto L16
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r10, int r11, Object... r12) {
        int r3 = 0;
        if (r10 != NotificationCenter.stickersDidLoad) goto L22;
        if (((Integer) r12[0]).intValue() != 0) goto L14;
        if (this.stickersGridAdapter == null) goto L148;
        updateStickerTabs(((Boolean) r12[1]).booleanValue());
        updateVisibleTrendingSets();
        if (this.stickerAdapterBuilt == false) goto L11;
        reloadStickersAdapter();
    L11:
        checkPanels();
        return;
    L148:
        return;
    L14:
        if (((Integer) r12[0]).intValue() == 5) goto L16;
        return;
    L16:
        if (((Boolean) r12[1]).booleanValue() == false) goto L19;
        AndroidUtilities.cancelRunOnUIThread(this.updateStickersLoadedDelayed);
        AndroidUtilities.runOnUIThread(this.updateStickersLoadedDelayed, 100);
        return;
    L19:
        this.emojiAdapter.notifyDataSetChanged(false);
        return;
    L22:
        if (r10 != NotificationCenter.groupPackUpdated) goto L31;
        long r102 = ((Long) r12[0]).longValue();
        boolean r122 = ((Boolean) r12[1]).booleanValue();
        TLRPC.ChatFull r0 = this.info;
        if (r0 != null) goto L26;
        return;
    L26:
        if (r0.id != r102) goto L151;
        if (r122 == false) goto L152;
        this.emojiAdapter.notifyDataSetChanged(true);
        return;
    L152:
        return;
    L151:
        return;
    L31:
        if (r10 != NotificationCenter.recentDocumentsDidLoad) goto L39;
        boolean r103 = ((Boolean) r12[0]).booleanValue();
        int r112 = ((Integer) r12[1]).intValue();
        if (r103 == true) goto L36;
        if (r112 == 0) goto L36;
        if (r112 == 2) goto L36;
        return;
    L36:
        checkDocuments(r103);
        return;
    L39:
        if (r10 != NotificationCenter.featuredStickersDidLoad) goto L48;
        updateVisibleTrendingSets();
        PagerSlidingTabStrip r104 = this.typeTabs;
        if (r104 == null) goto L45;
        int r105 = r104.getChildCount();
        int r113 = 0;
    L43:
        if (r113 >= r105) goto L45;
        this.typeTabs.getChildAt(r113).invalidate();
        r113 = r113 + 1;
    L45:
        updateStickerTabs(false);
        return;
    L48:
        if (r10 != NotificationCenter.featuredEmojiDidLoad) goto L54;
        EmojiGridAdapter r9 = this.emojiAdapter;
        if (r9 == null) goto L154;
        r9.notifyDataSetChanged();
        return;
    L154:
        return;
    L54:
        if (r10 != NotificationCenter.groupStickersDidLoad) goto L94;
        Long r106 = (Long) r12[0];
        long r5 = r106.longValue();
        if (r12.length <= 1) goto L58;
        TLRPC.TL_messages_stickerSet r114 = (TLRPC.TL_messages_stickerSet) r12[1];
    L59:
        if (r114 == null) goto L74;
        StickersSearchGridAdapter r2 = this.stickersSearchGridAdapter;
        if (r2 != null) goto L63;
    L67:
        EmojiSearchAdapter r22 = this.emojiSearchAdapter;
        if (r22 == null) goto L74;
        if (EmojiSearchAdapter.m10493$$Nest$fgetselectedPackId(r22) != r5) goto L74;
        if (EmojiSearchAdapter.m10494$$Nest$fgetselectedPackStickers(this.emojiSearchAdapter).size() >= r114.documents.size()) goto L74;
        EmojiSearchAdapter.m10498$$Nest$fputselectedPackStickers(this.emojiSearchAdapter, r114.documents);
        this.emojiSearchAdapter.notifyDataSetChanged();
        goto L74
    L63:
        if (StickersSearchGridAdapter.m10557$$Nest$fgetselectedPackId(r2) != r5) goto L67;
        if (StickersSearchGridAdapter.m10558$$Nest$fgetselectedPackStickers(this.stickersSearchGridAdapter).size() >= r114.documents.size()) goto L67;
        StickersSearchGridAdapter.m10570$$Nest$fputselectedPackStickers(this.stickersSearchGridAdapter, r114.documents);
        this.stickersSearchGridAdapter.notifyDataSetChanged();
    L74:
        TLRPC.ChatFull r23 = this.info;
        if (r23 == null) goto L82;
        TLRPC.StickerSet r24 = r23.stickerset;
        if (r24 == null) goto L82;
        if (r24.id != r5) goto L82;
        updateStickerTabs(false);
    L82:
        if (this.toInstall.containsKey(r106) == true) goto L84;
    L91:
        AndroidUtilities.cancelRunOnUIThread(this.updateStickersLoadedDelayed);
        AndroidUtilities.runOnUIThread(this.updateStickersLoadedDelayed, 100);
        return;
    L84:
        if (r12.length < 2) goto L91;
        if (this.toInstall.get(r106) == null) goto L91;
        if (r114 == null) goto L91;
        Utilities.Callback<TLRPC.TL_messages_stickerSet> r107 = this.toInstall.remove(r106);
        if (r107 == null) goto L91;
        r107.run(r114);
        goto L91
    L58:
        r114 = null;
        goto L59
    L94:
        if (r10 != NotificationCenter.emojiLoaded) goto L121;
        RecyclerListView r108 = this.stickersGridView;
        if (r108 == null) goto L105;
        int r109 = r108.getChildCount();
        int r115 = 0;
    L98:
        if (r115 >= r109) goto L105;
        View r123 = this.stickersGridView.getChildAt(r115);
        if ((r123 instanceof StickerSetNameCell) == false) goto L102;
    L103:
        r123.invalidate();
    L104:
        r115 = r115 + 1;
        goto L98
    L102:
        if ((r123 instanceof StickerEmojiCell) == false) goto L104;
    L105:
        EmojiGridView r1010 = this.emojiGridView;
        if (r1010 == null) goto L113;
        r1010.invalidate();
        int r1011 = this.emojiGridView.getChildCount();
    L108:
        if (r3 >= r1011) goto L113;
        View r116 = this.emojiGridView.getChildAt(r3);
        if ((r116 instanceof ImageViewEmoji) == false) goto L112;
        r116.invalidate();
    L112:
        r3 = r3 + 1;
    L113:
        EmojiColorPickerWindow r1012 = this.colorPickerView;
        if (r1012 == null) goto L116;
        r1012.pickerView.invalidate();
    L116:
        ScrollSlidingTabStrip r92 = this.gifTabs;
        if (r92 == null) goto L155;
        r92.invalidateTabs();
        return;
    L155:
        return;
    L121:
        if (r10 != NotificationCenter.newEmojiSuggestionsAvailable) goto L135;
        if (this.emojiGridView != null) goto L125;
        return;
    L125:
        if (this.needEmojiSearch == true) goto L127;
        return;
    L127:
        if (SearchField.m10539$$Nest$fgetsearchStateDrawable(this.emojiSearchField).getIconState() == 2) goto L131;
        if (this.emojiGridView.getAdapter() == this.emojiSearchAdapter) goto L131;
        return;
    L131:
        if (TextUtils.isEmpty(EmojiSearchAdapter.m10487$$Nest$fgetlastSearchEmojiString(this.emojiSearchAdapter)) == true) goto L159;
        EmojiSearchAdapter r93 = this.emojiSearchAdapter;
        r93.search(EmojiSearchAdapter.m10487$$Nest$fgetlastSearchEmojiString(r93));
        return;
    L159:
        return;
    L135:
        if (r10 != NotificationCenter.currentUserPremiumStatusChanged) goto L160;
        EmojiGridAdapter r1013 = this.emojiAdapter;
        if (r1013 == null) goto L139;
        r1013.notifyDataSetChanged();
    L139:
        updateEmojiHeaders();
        updateStickerTabs(false);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        updateBottomTabContainerPosition();
        super.dispatchDraw(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r4) {
        if (this.bottomInset <= 0) goto L9;
        if (r4.getY() < (getMeasuredHeight() - this.bottomInset)) goto L9;
        return true;
    L9:
        return super.dispatchTouchEvent(r4);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r4, View r5, long r6) {
        if (r5 != this.pager) goto L20;
        r4.save();
        if (this.bottomTabContainer.getVisibility() != 8) goto L7;
    L11:
        boolean r52 = super.drawChild(r4, r5, r6);
        float r62 = AndroidUtilities.getNavigationBarThirdButtonsFactor(this.bottomInset);
        if (r62 <= 0.0f) goto L17;
        int r63 = Theme.multAlpha(getThemedColor(Theme.key_chat_emojiPanelBackground), r62);
        if (this.lastFadeColor == r63) goto L16;
        this.fadeDrawable.setColors(new int[]{r63, Theme.multAlpha(r63, 0.66f), ColorUtils.setAlphaComponent(r63, 0)});
        this.lastFadeColor = r63;
    L16:
        this.fadeDrawable.setBounds(0, getMeasuredHeight() - this.bottomInset, getMeasuredWidth(), getMeasuredHeight());
        this.fadeDrawable.draw(r4);
    L17:
        r4.restore();
        return r52;
    L7:
        if (this.shouldDrawBackground == true) goto L11;
        if (this.shouldLightenBackground == false) goto L11;
        r4.drawColor(ColorUtils.setAlphaComponent(-1, 25));
        goto L11
    L20:
        return super.drawChild(r4, r5, r6);
    }

    public void forceHideBackspaceButton() {
        this.mForceHideBackspaceButton = true;
        ImageView r1 = this.backspaceButton;
        if (r1 == null) goto L6;
        r1.setVisibility(8);
        return;
    }

    public void forceHideSettingsButton() {
        this.mForceHideSettingsButton = true;
        ImageView r1 = this.stickerSettingsButton;
        if (r1 == null) goto L6;
        r1.setVisibility(8);
        return;
    }

    public void freeze(boolean r2) {
        boolean r0 = this.frozen;
        this.frozen = r2;
        if (r0 == false) goto L26;
        if (r2 == true) goto L27;
        int r22 = this.currentPage;
        if (r22 != 0) goto L11;
        EmojiGridAdapter r23 = this.emojiAdapter;
        if (r23 == null) goto L21;
        r23.notifyDataSetChanged();
        syncRecentEmojiVersion();
    L21:
        if (this.stickersContainerAttached == true) goto L23;
        return;
    L23:
        if (this.stickerAdapterBuilt == true) goto L29;
        reloadStickersAdapter();
        return;
    L29:
        return;
    L11:
        if (r22 != 1) goto L16;
        GifAdapter r24 = this.gifAdapter;
        if (r24 == null) goto L21;
        r24.notifyDataSetChanged();
        goto L21
    L16:
        if (r22 != 2) goto L21;
        StickersGridAdapter r25 = this.stickersGridAdapter;
        if (r25 == null) goto L21;
        r25.notifyDataSetChanged();
        goto L21
    L27:
        return;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public ArrayList<EmojiPack> getEmojipacks() {
        ArrayList<EmojiPack> r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= this.emojipacksProcessed.size()) goto L19;
        EmojiPack r2 = this.emojipacksProcessed.get(r1);
        if (r2.featured == true) goto L12;
        if (r2.installed == false) goto L10;
    L17:
        r0.add(r2);
    L18:
        r1 = r1 + 1;
        goto L4
    L10:
        if (this.installedEmojiSets.contains(Long.valueOf(r2.set.id)) == true) goto L17;
    L12:
        if (r2.featured == false) goto L18;
        if (r2.installed == true) goto L18;
        if (this.installedEmojiSets.contains(Long.valueOf(r2.set.id)) == true) goto L18;
    L19:
        return r0;
    }

    public RecyclerListView getListViewForType(int r2) {
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L12;
        if (r2 == 2) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unexpected argument: ", r2);
        return null;
    L8:
        return this.gifGridView;
    L12:
        return this.emojiGridView;
    L14:
        return this.stickersGridView;
    }

    public ArrayList<String> getRecentEmoji() {
        if (this.allowAnimatedEmoji == false) goto L7;
        return Emoji.recentEmoji;
    L7:
        if (this.lastRecentArray != null) goto L10;
        this.lastRecentArray = new ArrayList();
    L10:
        if (Emoji.recentEmoji.size() == this.lastRecentCount) goto L20;
        this.lastRecentArray.clear();
        int r0 = 0;
    L12:
        ArrayList<String> r1 = Emoji.recentEmoji;
        if (r0 >= r1.size()) goto L18;
        if (r1.get(r0).startsWith("animated_") == true) goto L17;
        this.lastRecentArray.add(r1.get(r0));
    L17:
        r0 = r0 + 1;
        goto L12
    L18:
        this.lastRecentCount = this.lastRecentArray.size();
    L20:
        return this.lastRecentArray;
    }

    public float getStickersExpandOffset() {
        ScrollSlidingTabStrip r0 = this.stickersTab;
        if (r0 != null) goto L7;
        return 0.0f;
    L7:
        return r0.getExpandedOffset();
    }

    public View getTabsForType(int r2) {
        if (r2 == 0) goto L14;
        if (r2 == 1) goto L12;
        if (r2 == 2) goto L8;
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unexpected argument: ", r2);
        return null;
    L8:
        return this.gifTabs;
    L12:
        return this.emojiTabs;
    L14:
        return this.stickersTab;
    }

    public void hideBottomTabContainerBackground() {
        View r1 = this.bottomTabContainerBackground;
        if (r1 == null) goto L6;
        r1.setVisibility(8);
        return;
    }

    public void hideSearchKeyboard() {
        SearchField r0 = this.stickersSearchField;
        if (r0 == null) goto L5;
        r0.hideKeyboard();
    L5:
        SearchField r02 = this.gifSearchField;
        if (r02 == null) goto L8;
        r02.hideKeyboard();
    L8:
        SearchField r1 = this.emojiSearchField;
        if (r1 == null) goto L12;
        r1.hideKeyboard();
        return;
    }

    public void invalidateViews() {
        this.emojiGridView.invalidateViews();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.ObserversGroup r0 = this.observersGroup;
        if (r0 == null) goto L5;
        r0.removeAllObservers();
        this.observersGroup = null;
    L5:
        NotificationCenter.ObserversGroup r02 = NotificationCenter.getInstance(this.currentAccount).createWeakObserversGroup(this).addGlobal(NotificationCenter.emojiLoaded).add(NotificationCenter.newEmojiSuggestionsAvailable).add(NotificationCenter.groupPackUpdated).add(NotificationCenter.stickersDidLoad);
        this.observersGroup = r02;
        if (this.stickersGridAdapter == null) goto L9;
        r02.add(NotificationCenter.recentDocumentsDidLoad).add(NotificationCenter.featuredStickersDidLoad).add(NotificationCenter.groupStickersDidLoad).add(NotificationCenter.currentUserPremiumStatusChanged);
        return;
    }

    public void onDestroy() {
        NotificationCenter.ObserversGroup r0 = this.observersGroup;
        if (r0 == null) goto L6;
        r0.removeAllObservers();
        this.observersGroup = null;
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EmojiColorPickerWindow r0 = this.colorPickerView;
        if (r0 != null) goto L5;
    L7:
        ContentPreviewViewer.getInstance().clearDelegate(this.contentPreviewViewerDelegate);
        return;
    L5:
        if (r0.isShowing() == false) goto L7;
        this.colorPickerView.dismiss();
        goto L7
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChangeFinished(int r1, float r2, FactorAnimator r3) {
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L7;
        checkStickersSearchFieldScroll(false);
        checkStickersSearchFieldVisibility();
        updateBottomTabContainerPosition();
        this.stickersContainer.invalidate();
        return;
    L7:
        if (r1 != 1) goto L10;
        checkEmojiSearchFieldScroll(false);
        checkEmojiSearchFieldVisibility();
        updateBottomTabContainerPosition();
        this.emojiContainer.invalidate();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        int r1 = r6 - r4;
        if (this.lastNotifyWidth == r1) goto L9;
        this.lastNotifyWidth = r1;
        if (this.stickersContainerAttached == false) goto L7;
    L8:
        reloadStickersAdapter();
        goto L9
    L7:
        if (this.stickerAdapterBuilt == true) goto L8;
    L9:
        super.onLayout(r3, r4, r5, r6, r7);
        updateBottomTabContainerPosition();
        updateStickerTabsPosition();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        this.isLayout = true;
        if (AndroidUtilities.isInMultiwindow == true) goto L19;
        if (this.forseMultiwindowLayout == true) goto L19;
        if (this.currentBackgroundType != 0) goto L10;
    L29:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r8), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r9), TLObject.FLAG_30));
        this.isLayout = false;
        setTranslationY(getTranslationY());
        return;
    L10:
        if (this.customOutline == true) goto L13;
        setOutlineProvider(null);
        setClipToOutline(false);
        setElevation(0.0f);
    L13:
        if (this.shouldDrawBackground == false) goto L17;
        int r0 = Theme.key_chat_emojiPanelBackground;
        setBackgroundColor(getThemedColor(r0));
        if (this.needEmojiSearch == false) goto L17;
        this.bottomTabContainerBackground.setBackgroundColor(getThemedColor(r0));
    L17:
        this.currentBackgroundType = 0;
    L19:
        if (this.currentBackgroundType == 1) goto L29;
        if (this.customOutline == true) goto L23;
        setOutlineProvider((ViewOutlineProvider) this.outlineProvider);
        setClipToOutline(true);
        setElevation(AndroidUtilities.dp(2.0f));
    L23:
        setBackgroundResource(R.drawable.smiles_popup);
        Drawable r1 = getBackground();
        int r4 = Theme.key_chat_emojiPanelBackground;
        r1.setColorFilter(new PorterDuffColorFilter(getThemedColor(r4), PorterDuff.Mode.MULTIPLY));
        if (this.needEmojiSearch == true) goto L26;
    L28:
        this.currentBackgroundType = 1;
        goto L29
    L26:
        if (this.shouldDrawBackground == false) goto L28;
        this.bottomTabContainerBackground.setBackgroundColor(getThemedColor(r4));
        goto L28
    }

    public void onMessageSend() {
        ChooseStickerActionTracker r0 = this.chooseStickerActionTracker;
        if (r0 == null) goto L6;
        ChooseStickerActionTracker.m10464$$Nest$mreset(r0);
        return;
    }

    public void onOpen(boolean r4, boolean r5) {
        if (this.currentPage == 0) goto L8;
        if (this.stickersBanned == false) goto L8;
        this.currentPage = 0;
    L8:
        if (this.currentPage != 0) goto L13;
        if (this.emojiBanned == false) goto L13;
        this.currentPage = 1;
    L13:
        if (this.currentPage == 0) goto L52;
        if (r4 == true) goto L52;
        if (this.currentTabs.size() == 1) goto L52;
        int r42 = this.currentPage;
        if (r42 != 1) goto L40;
        showBackspaceButton(false, false);
        if (this.shouldDrawBackground == false) goto L23;
    L26:
        boolean r43 = true;
    L27:
        showStickerSettingsButton(r43, false);
        if (this.pager.getCurrentItem() == 2) goto L30;
        this.pager.setCurrentItem(2, false);
    L30:
        ScrollSlidingTabStrip r44 = this.stickersTab;
        if (r44 == null) goto L57;
        this.firstTabUpdate = true;
        int r52 = this.favTabNum;
        if (r52 < 0) goto L35;
        r44.selectTab(r52);
    L39:
        this.firstTabUpdate = false;
        this.stickersLayoutManager.scrollToPositionWithOffset(0, 0);
        goto L57
    L35:
        int r53 = this.recentTabNum;
        if (r53 < 0) goto L38;
        r44.selectTab(r53);
        goto L39
    L38:
        r44.selectTab(this.stickersTabOffset);
    L57:
        showBottomTab(true, true);
        return;
    L23:
        if (this.shouldDrawStickerSettings == true) goto L26;
        r43 = false;
        goto L27
    L40:
        if (r42 != 2) goto L57;
        showBackspaceButton(false, false);
        showStickerSettingsButton(false, false);
        if (this.pager.getCurrentItem() == 1) goto L44;
        this.pager.setCurrentItem(1, false);
    L44:
        ScrollSlidingTabStrip r45 = this.gifTabs;
        if (r45 == null) goto L47;
        r45.selectTab(0);
    L47:
        SearchField r46 = this.gifSearchField;
        if (r46 == null) goto L57;
        if (SearchField.m10535$$Nest$fgetcategoriesListView(r46) == null) goto L57;
        SearchField.m10535$$Nest$fgetcategoriesListView(this.gifSearchField).selectCategory(SearchField.m10537$$Nest$fgetrecent(this.gifSearchField));
    L52:
        showBackspaceButton(true, false);
        showStickerSettingsButton(false, false);
        if (this.pager.getCurrentItem() == 0) goto L55;
        this.pager.setCurrentItem(0, !r4);
    L55:
        if (r5 == false) goto L57;
        AndroidUtilities.runOnUIThread(new EmojiView$$ExternalSyntheticLambda20(this), 350);
        goto L57
    }

    public void openEmojiPackAlert(TLRPC.StickerSet r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        TLRPC.TL_inputStickerSetID r0 = new TLRPC.TL_inputStickerSetID();
        r0.id = r4.id;
        r0.access_hash = r4.access_hash;
        openEmojiPackAlert(r0, null);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.isLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void scrollEmojiToTop() {
        this.emojiGridView.stopScroll();
        this.emojiTabs.scrollTo(0, 0);
        resetTabsY(1);
        this.emojiLayoutManager.scrollToPositionWithOffset(0, 0);
    }

    public void scrollEmojisToAnimated() {
        if (this.emojiSmoothScrolling == true) goto L13;
        int r0 = EmojiGridAdapter.m10474$$Nest$fgetsectionToPosition(this.emojiAdapter).get(EmojiData.dataColored.length);     // Catch: Exception -> L9
        if (r0 <= 0) goto L12;
        this.emojiGridView.stopScroll();     // Catch: Exception -> L9
        updateEmojiTabsPosition(r0);     // Catch: Exception -> L9
        scrollEmojisToPosition(r0, AndroidUtilities.dp(-9.0f));     // Catch: Exception -> L9
        checkEmojiTabY(null, 0);     // Catch: Exception -> L9
        return;
    L12:
        return;
    L14:
        return;
    }

    public void searchProgressChanged() {
        updateStickerTabsPosition();
    }

    public void setAllow(boolean r2, boolean r3, boolean r4) {
        setAllow(true, r2, r3, r4);
    }

    public void setAllow(boolean r6, boolean r7, boolean r8, boolean r9) {
        this.currentTabs.clear();
        boolean r0 = false;
        int r1 = 0;
    L4:
        if (r1 >= this.allTabs.size()) goto L18;
        if (this.allTabs.get(r1).type != 0) goto L10;
        if (r6 == false) goto L10;
        this.currentTabs.add(this.allTabs.get(r1));
    L10:
        if (this.allTabs.get(r1).type != 1) goto L14;
        if (r8 == false) goto L14;
        this.currentTabs.add(this.allTabs.get(r1));
    L14:
        if (this.allTabs.get(r1).type != 2) goto L17;
        if (r7 == false) goto L17;
        this.currentTabs.add(this.allTabs.get(r1));
    L17:
        r1 = r1 + 1;
        goto L4
    L18:
        PagerSlidingTabStrip r62 = this.typeTabs;
        if (r62 != null) goto L21;
    L24:
        ViewPager r63 = this.pager;
        if (r63 == null) goto L34;
        r63.setAdapter(null);
        this.pager.setAdapter(this.emojiPagerAdapter);
        PagerSlidingTabStrip r64 = this.typeTabs;
        if (r64 == null) goto L35;
        r64.setViewPager(this.pager);
        return;
    L35:
        return;
    L34:
        return;
    L21:
        if (this.currentTabs.size() <= 1) goto L23;
        r0 = true;
    L23:
        AndroidUtilities.updateViewVisibilityAnimated(r62, r0, 1.0f, r9);
        goto L24
    }

    public void setBlurredBackgroundDrawableFactory(BlurredBackgroundDrawableViewFactory r6) {
        ImageView r0 = this.backspaceButton;
        if (r0 == null) goto L5;
        r0.setBackground(r6.create(r0).setColorProvider(BlurredBackgroundProviderImpl.emojiViewButton(this.resourcesProvider)).setRadius(AndroidUtilities.dp(18.0f)).setPadding(AndroidUtilities.dp(6.0f)));
    L5:
        ImageView r02 = this.searchButton;
        if (r02 == null) goto L8;
        r02.setBackground(r6.create(r02).setColorProvider(BlurredBackgroundProviderImpl.emojiViewButton(this.resourcesProvider)).setRadius(AndroidUtilities.dp(18.0f)).setPadding(AndroidUtilities.dp(6.0f)));
    L8:
        PagerSlidingTabStrip r03 = this.typeTabs;
        if (r03 == null) goto L11;
        r03.setBackground(r6.create(r03).setColorProvider(BlurredBackgroundProviderImpl.emojiViewButton(this.resourcesProvider)).setRadius(AndroidUtilities.dp(18.0f)).setPadding(AndroidUtilities.dp(6.0f)));
    L11:
        ImageView r04 = this.stickerSettingsButton;
        if (r04 == null) goto L15;
        r04.setBackground(r6.create(r04).setColorProvider(BlurredBackgroundProviderImpl.emojiViewButton(this.resourcesProvider)).setRadius(AndroidUtilities.dp(18.0f)).setPadding(AndroidUtilities.dp(6.0f)));
        return;
    }

    public void setBottomInset(int r4) {
        if (this.bottomInset == r4) goto L9;
        this.bottomInset = r4;
        applyBottomInsetAsPadding(this.emojiAddPackButtonContainer, r4);
        applyBottomInsetAsPadding(this.stickerAddPackButtonContainer, r4);
        applyBottomInsetAsPadding(this.emojiGridView, AndroidUtilities.dp(44.0f) + r4);
        applyBottomInsetAsPadding(this.stickersGridView, AndroidUtilities.dp(44.0f) + r4);
        applyBottomInsetAsPadding(this.gifGridView, AndroidUtilities.dp(44.0f) + r4);
        FrameLayout r0 = this.bulletinContainer2;
        if (r0 == null) goto L7;
        r0.setTranslationY(-r4);
    L7:
        updateBottomTabContainerPosition();
        invalidate();
        return;
    }

    public void setChatInfo(TLRPC.ChatFull r1) {
        this.info = r1;
        updateStickerTabs(false);
    }

    public void setDelegate(EmojiViewDelegate r1) {
        this.delegate = r1;
    }

    public void setDisableStickerEditor() {
        this.disableStickerEditor = true;
    }

    public void setDragListener(DragListener r1) {
        this.dragListener = r1;
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        super.setEnabled(r2);
        SearchField r0 = this.stickersSearchField;
        if (r0 == null) goto L5;
        SearchField.m10538$$Nest$fgetsearchEditText(r0).setEnabled(r2);
    L5:
        SearchField r02 = this.gifSearchField;
        if (r02 == null) goto L8;
        SearchField.m10538$$Nest$fgetsearchEditText(r02).setEnabled(r2);
    L8:
        SearchField r1 = this.emojiSearchField;
        if (r1 == null) goto L12;
        SearchField.m10538$$Nest$fgetsearchEditText(r1).setEnabled(r2);
        return;
    }

    public void setForseMultiwindowLayout(boolean r1) {
        this.forseMultiwindowLayout = r1;
    }

    public void setShouldDrawBackground(boolean r2) {
        if (this.shouldDrawBackground == r2) goto L6;
        this.shouldDrawBackground = r2;
        updateColors();
        return;
    }

    public void setShowing(boolean r1) {
        this.showing = r1;
        updateStickerTabsPosition();
    }

    public void setStickersBanned(boolean r6, boolean r7, long r8) {
        PagerSlidingTabStrip r0 = this.typeTabs;
        if (r0 == null) goto L40;
        this.emojiBanned = r6;
        this.stickersBanned = r7;
        if (r7 == true) goto L10;
        if (r6 == true) goto L10;
        this.currentChatId = 0;
    L12:
        if (r7 == false) goto L14;
        int r82 = 2;
    L15:
        View r83 = r0.getTab(r82);
        if (r83 != null) goto L18;
        return;
    L18:
        if (this.currentChatId == 0) goto L20;
        float r9 = 0.15f;
    L21:
        r83.setAlpha(r9);
        long r84 = this.currentChatId;
        if (r7 == false) goto L30;
        if (r84 != 0) goto L26;
        return;
    L26:
        if (this.pager.getCurrentItem() == 0) goto L37;
        showBackspaceButton(true, true);
        showStickerSettingsButton(false, true);
        this.pager.setCurrentItem(0, false);
        return;
    L37:
        return;
    L30:
        if (r84 != 0) goto L32;
        return;
    L32:
        if (this.pager.getCurrentItem() == 1) goto L39;
        showBackspaceButton(false, true);
        showStickerSettingsButton(false, true);
        this.pager.setCurrentItem(1, false);
        return;
    L39:
        return;
    L20:
        r9 = 1.0f;
        goto L21
    L14:
        r82 = 0;
    L10:
        this.currentChatId = r8;
        goto L12
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        updateStickerTabsPosition();
        updateBottomTabContainerPosition();
    }

    @Override // android.view.View
    public void setVisibility(int r4) {
        if (getVisibility() == r4) goto L5;
        boolean r0 = true;
    L6:
        super.setVisibility(r4);
        if (r0 == true) goto L9;
        return;
    L9:
        if (r4 != 8) goto L11;
    L14:
        ChooseStickerActionTracker r3 = this.chooseStickerActionTracker;
        if (r3 == null) goto L19;
        r3.checkVisibility();
        return;
    L19:
        return;
    L11:
        if (Emoji.recentEmojiVersion == this.lastRecentEmojiVersion) goto L13;
        Emoji.sortEmoji();
        this.emojiAdapter.notifyDataSetChanged();
        syncRecentEmojiVersion();
    L13:
        checkDocuments(true);
        checkDocuments(false);
        MediaDataController.getInstance(this.currentAccount).loadRecents(0, true, true, false);
        MediaDataController.getInstance(this.currentAccount).loadRecents(0, false, true, false);
        MediaDataController.getInstance(this.currentAccount).loadRecents(2, false, true, false);
        goto L14
    L5:
        r0 = false;
        goto L6
    }

    public void showSearchField(boolean r6) {
        int r1 = 0;
    L4:
        if (r1 >= 3) goto L15;
        GridLayoutManager r2 = getLayoutManagerForType(r1);
        int r3 = r2.findFirstVisibleItemPosition();
        if (r6 == true) goto L8;
        if (r3 != 0) goto L14;
        r2.scrollToPositionWithOffset(0, 0);
    L14:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r3 != 1) goto L10;
    L11:
        r2.scrollToPosition(0);
        resetTabsY(r1);
        goto L14
    L10:
        if (r3 != 2) goto L14;
    }

    public void showStickerBanHint(boolean r10, boolean r11, boolean r12) {
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.currentChatId));
        if (r0 != null) goto L6;
        return;
    L6:
        if (r10 == true) goto L8;
    L45:
        AnimatorSet r02 = this.showStickersBanAnimator;
        if (r02 == null) goto L48;
        r02.cancel();
        this.showStickersBanAnimator = null;
    L48:
        AnimatorSet r03 = new AnimatorSet();
        this.showStickersBanAnimator = r03;
        TextView r2 = this.mediaBanTooltip;
        float r3 = 1.0f;
        if (r10 == false) goto L51;
        float r4 = r2.getAlpha();
    L52:
        float r5 = 0.0f;
        if (r10 == true) goto L56;
        r3 = 0.0f;
    L56:
        ObjectAnimator r22 = ObjectAnimator.ofFloat(r2, View.ALPHA, new float[]{r4, r3});
        TextView r32 = this.mediaBanTooltip;
        if (r10 == false) goto L59;
        float r8 = AndroidUtilities.dp(12.0f);
    L60:
        if (r10 == true) goto L63;
        r5 = AndroidUtilities.dp(12.0f);
    L63:
        r03.playTogether(new Animator[]{r22, ObjectAnimator.ofFloat(r32, View.TRANSLATION_Y, new float[]{r8, r5})});
        Runnable r04 = this.hideStickersBan;
        if (r04 == null) goto L66;
        AndroidUtilities.cancelRunOnUIThread(r04);
    L66:
        if (r10 == false) goto L68;
        EmojiView$$ExternalSyntheticLambda27 r102 = new EmojiView$$ExternalSyntheticLambda27(this, r11, r12);
        this.hideStickersBan = r102;
        AndroidUtilities.runOnUIThread(r102, 3500);
    L68:
        this.showStickersBanAnimator.setDuration(320);
        this.showStickersBanAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.showStickersBanAnimator.start();
        return;
    L59:
        r8 = r32.getTranslationY();
        goto L60
    L51:
        r4 = 1.0f;
        goto L52
    L8:
        if (ChatObject.hasAdminRights(r0) == true) goto L27;
        TLRPC.TL_chatBannedRights r23 = r0.default_banned_rights;
        if (r23 == null) goto L27;
        if (r23.send_stickers == true) goto L16;
        if (r11 == false) goto L27;
        if (r23.send_plain == false) goto L27;
    L16:
        BaseFragment r05 = this.fragment;
        if ((r05 instanceof ChatActivity) == true) goto L19;
    L21:
        if (r11 == false) goto L23;
        this.mediaBanTooltip.setText(LocaleController.getString(R.string.GlobalAttachEmojiRestricted));
    L44:
        this.mediaBanTooltip.setVisibility(0);
        goto L45
    L23:
        TextView r06 = this.mediaBanTooltip;
        if (r12 == false) goto L26;
        r06.setText(LocaleController.getString(R.string.GlobalAttachGifRestricted));
        goto L44
    L26:
        r06.setText(LocaleController.getString(R.string.GlobalAttachStickersRestricted));
        goto L44
    L19:
        if (((ChatActivity) r05).checkCanRemoveRestrictionsByBoosts() == false) goto L21;
        return;
    L27:
        TLRPC.TL_chatBannedRights r24 = r0.banned_rights;
        if (r24 != null) goto L31;
        return;
    L31:
        if (AndroidUtilities.isBannedForever(r24) == false) goto L38;
        if (r11 == false) goto L34;
        this.mediaBanTooltip.setText(LocaleController.getString(R.string.AttachPlainRestrictedForever));
        goto L44
    L34:
        TextView r07 = this.mediaBanTooltip;
        if (r12 == false) goto L37;
        r07.setText(LocaleController.getString(R.string.AttachGifRestrictedForever));
        goto L44
    L37:
        r07.setText(LocaleController.getString(R.string.AttachStickersRestrictedForever));
        goto L44
    L38:
        if (r11 == false) goto L40;
        this.mediaBanTooltip.setText(LocaleController.formatString("AttachPlainRestricted", R.string.AttachPlainRestricted, new Object[]{LocaleController.formatDateForBan(r0.banned_rights.until_date)}));
    L40:
        TextView r25 = this.mediaBanTooltip;
        if (r12 == false) goto L43;
        r25.setText(LocaleController.formatString("AttachGifRestricted", R.string.AttachGifRestricted, new Object[]{LocaleController.formatDateForBan(r0.banned_rights.until_date)}));
        goto L44
    L43:
        r25.setText(LocaleController.formatString("AttachStickersRestricted", R.string.AttachStickersRestricted, new Object[]{LocaleController.formatDateForBan(r0.banned_rights.until_date)}));
        goto L44
    }

    public void switchToGifRecent() {
        showBackspaceButton(false, false);
        showStickerSettingsButton(false, false);
        this.pager.setCurrentItem(1, false);
    }

    public void updateColors() {
        if (this.shouldDrawBackground == true) goto L6;
        setBackground(null);
        this.bottomTabContainerBackground.setBackground(null);
    L16:
        EmojiTabsStrip r0 = this.emojiTabs;
        if (r0 != null) goto L19;
    L22:
        EmojiColorPickerWindow r02 = this.colorPickerView;
        if (r02 == null) goto L25;
        r02.updateColors();
    L25:
        int r2 = 0;
    L27:
        if (r2 >= 3) goto L57;
        if (r2 != 0) goto L30;
        SearchField r3 = this.stickersSearchField;
    L33:
        if (r3 == null) goto L56;
        if (this.shouldDrawBackground == false) goto L38;
        SearchField.m10533$$Nest$fgetbackgroundView(r3).setBackgroundColor(getThemedColor(Theme.key_chat_emojiPanelBackground));
    L39:
        SearchField.m10540$$Nest$fgetshadowView(r3).setBackgroundColor(getThemedColor(Theme.key_chat_emojiPanelShadowLine));
        SearchStateDrawable r6 = SearchField.m10539$$Nest$fgetsearchStateDrawable(r3);
        if (this.glassDesign == false) goto L42;
        int r5 = getGlassIconColor(0.4f);
    L43:
        r6.setColor(r5);
        Drawable r52 = SearchField.m10534$$Nest$fgetbox(r3).getBackground();
        if (this.glassDesign == false) goto L46;
        int r62 = getGlassIconColor(0.06f);
    L47:
        Theme.setDrawableColor(r52, r62);
        SearchField.m10534$$Nest$fgetbox(r3).invalidate();
        EditTextBoldCursor r53 = SearchField.m10538$$Nest$fgetsearchEditText(r3);
        if (this.glassDesign == false) goto L50;
        int r63 = getGlassIconColor(0.45f);
    L51:
        r53.setHintTextColor(r63);
        EditTextBoldCursor r32 = SearchField.m10538$$Nest$fgetsearchEditText(r3);
        if (this.glassDesign == false) goto L54;
        int r4 = getGlassIconColor(0.8f);
    L55:
        r32.setTextColor(r4);
        goto L56
    L54:
        r4 = getThemedColor(Theme.key_windowBackgroundWhiteBlackText);
        goto L55
    L50:
        r63 = getThemedColor(Theme.key_chat_emojiSearchIcon);
        goto L51
    L46:
        r62 = getThemedColor(Theme.key_chat_emojiSearchBackground);
        goto L47
    L42:
        r5 = getThemedColor(Theme.key_chat_emojiSearchIcon);
        goto L43
    L38:
        SearchField.m10533$$Nest$fgetbackgroundView(r3).setBackground(null);
    L56:
        r2 = r2 + 1;
        goto L27
    L30:
        if (r2 != 1) goto L32;
        r3 = this.emojiSearchField;
        goto L33
    L32:
        r3 = this.gifSearchField;
        goto L33
    L57:
        Paint r22 = this.dotPaint;
        if (r22 == null) goto L60;
        r22.setColor(getThemedColor(Theme.key_chat_emojiPanelNewTrending));
    L60:
        EmojiGridView r23 = this.emojiGridView;
        if (r23 == null) goto L63;
        r23.setGlowColor(getThemedColor(Theme.key_chat_emojiPanelBackground));
    L63:
        RecyclerListView r24 = this.stickersGridView;
        if (r24 == null) goto L66;
        r24.setGlowColor(getThemedColor(Theme.key_chat_emojiPanelBackground));
    L66:
        ScrollSlidingTabStrip r25 = this.stickersTab;
        if (r25 == null) goto L72;
        r25.setIndicatorColor(getThemedColor(Theme.key_chat_emojiPanelStickerPackSelectorLine));
        this.stickersTab.setUnderlineColor(getThemedColor(Theme.key_chat_emojiPanelShadowLine));
        boolean r26 = this.shouldDrawBackground;
        ScrollSlidingTabStrip r33 = this.stickersTab;
        if (r26 == false) goto L71;
        r33.setBackgroundColor(getThemedColor(Theme.key_chat_emojiPanelBackground));
        goto L72
    L71:
        r33.setBackground(null);
    L72:
        ScrollSlidingTabStrip r27 = this.gifTabs;
        if (r27 == null) goto L78;
        r27.setIndicatorColor(getThemedColor(Theme.key_chat_emojiPanelStickerPackSelectorLine));
        this.gifTabs.setUnderlineColor(getThemedColor(Theme.key_chat_emojiPanelShadowLine));
        boolean r28 = this.shouldDrawBackground;
        ScrollSlidingTabStrip r34 = this.gifTabs;
        if (r28 == false) goto L77;
        r34.setBackgroundColor(getThemedColor(Theme.key_chat_emojiPanelBackground));
        goto L78
    L77:
        r34.setBackground(null);
    L78:
        ImageView r1 = this.backspaceButton;
        if (r1 != null) goto L81;
    L87:
        ImageView r12 = this.stickerSettingsButton;
        if (r12 != null) goto L90;
    L94:
        ImageView r13 = this.searchButton;
        if (r13 != null) goto L97;
    L101:
        TextView r14 = this.mediaBanTooltip;
        if (r14 == null) goto L104;
        ((ShapeDrawable) r14.getBackground()).getPaint().setColor(getThemedColor(Theme.key_chat_gifSaveHintBackground));
        this.mediaBanTooltip.setTextColor(getThemedColor(Theme.key_chat_gifSaveHintText));
    L104:
        GifAdapter r15 = this.gifSearchAdapter;
        if (r15 == null) goto L107;
        ImageView r16 = GifProgressEmptyView.m10517$$Nest$fgetimageView(GifAdapter.m10509$$Nest$fgetprogressEmptyView(r15));
        int r35 = Theme.key_chat_emojiPanelEmptyText;
        r16.setColorFilter(new PorterDuffColorFilter(getThemedColor(r35), PorterDuff.Mode.MULTIPLY));
        GifProgressEmptyView.m10519$$Nest$fgettextView(GifAdapter.m10509$$Nest$fgetprogressEmptyView(this.gifSearchAdapter)).setTextColor(getThemedColor(r35));
        GifProgressEmptyView.m10518$$Nest$fgetprogressView(GifAdapter.m10509$$Nest$fgetprogressEmptyView(this.gifSearchAdapter)).setProgressColor(getThemedColor(Theme.key_progressCircle));
    L107:
        this.animatedEmojiTextColorFilter = new PorterDuffColorFilter(getThemedColor(Theme.key_featuredStickers_addButton), PorterDuff.Mode.SRC_IN);
        int r17 = 0;
    L108:
        Drawable[] r29 = this.tabIcons;
        if (r17 >= r29.length) goto L119;
        Drawable r210 = r29[r17];
        if (this.glassDesign == false) goto L113;
        int r36 = getGlassIconColor(0.4f);
    L114:
        Theme.setEmojiDrawableColor(r210, r36, false);
        Drawable r211 = this.tabIcons[r17];
        if (this.glassDesign == false) goto L117;
        int r37 = getGlassIconColor(0.8f);
    L118:
        Theme.setEmojiDrawableColor(r211, r37, true);
        r17 = r17 + 1;
        goto L108
    L117:
        r37 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L118
    L113:
        r36 = getThemedColor(Theme.key_chat_emojiBottomPanelIcon);
        goto L114
    L119:
        EmojiTabsStrip r18 = this.emojiTabs;
        if (r18 == null) goto L122;
        r18.updateColors();
    L122:
        int r19 = 0;
    L123:
        Drawable[] r212 = this.stickerIcons;
        if (r19 >= r212.length) goto L134;
        Drawable r213 = r212[r19];
        if (this.glassDesign == false) goto L128;
        int r38 = getGlassIconColor(0.4f);
    L129:
        Theme.setEmojiDrawableColor(r213, r38, false);
        Drawable r214 = this.stickerIcons[r19];
        if (this.glassDesign == false) goto L132;
        int r39 = getGlassIconColor(0.8f);
    L133:
        Theme.setEmojiDrawableColor(r214, r39, true);
        r19 = r19 + 1;
        goto L123
    L132:
        r39 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L133
    L128:
        r38 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L129
    L134:
        int r110 = 0;
    L135:
        Drawable[] r215 = this.gifIcons;
        if (r110 >= r215.length) goto L146;
        Drawable r216 = r215[r110];
        if (this.glassDesign == false) goto L140;
        int r310 = getGlassIconColor(0.4f);
    L141:
        Theme.setEmojiDrawableColor(r216, r310, false);
        Drawable r217 = this.gifIcons[r110];
        if (this.glassDesign == false) goto L144;
        int r311 = getGlassIconColor(0.8f);
    L145:
        Theme.setEmojiDrawableColor(r217, r311, true);
        r110 = r110 + 1;
        goto L135
    L144:
        r311 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L145
    L140:
        r310 = getThemedColor(Theme.key_chat_emojiPanelIcon);
        goto L141
    L146:
        Drawable r111 = this.searchIconDrawable;
        if (r111 != null) goto L149;
    L157:
        Drawable r112 = this.searchIconDotDrawable;
        if (r112 != null) goto L160;
    L168:
        Paint r03 = this.emojiLockPaint;
        if (r03 == null) goto L171;
        r03.setColor(getThemedColor(Theme.key_chat_emojiPanelStickerSetName));
        this.emojiLockPaint.setAlpha((int) (r0.getAlpha() * 0.5f));
    L171:
        Drawable r04 = this.emojiLockDrawable;
        if (r04 == null) goto L187;
        r04.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_emojiPanelStickerSetName), PorterDuff.Mode.MULTIPLY));
        return;
    L187:
        return;
    L160:
        if (this.glassDesign == false) goto L162;
        int r218 = getGlassIconColor(0.4f);
    L163:
        Theme.setEmojiDrawableColor(r112, r218, false);
        Drawable r05 = this.searchIconDotDrawable;
        if (this.glassDesign == false) goto L166;
        int r113 = getGlassIconColor(0.8f);
    L167:
        Theme.setEmojiDrawableColor(r05, r113, true);
        goto L168
    L166:
        r113 = getThemedColor(Theme.key_chat_emojiPanelStickerPackSelectorLine);
        goto L167
    L162:
        r218 = getThemedColor(Theme.key_chat_emojiPanelStickerPackSelectorLine);
        goto L163
    L149:
        if (this.glassDesign == false) goto L151;
        int r219 = getGlassIconColor(0.4f);
    L152:
        Theme.setEmojiDrawableColor(r111, r219, false);
        Drawable r114 = this.searchIconDrawable;
        if (this.glassDesign == false) goto L155;
        int r220 = getGlassIconColor(0.8f);
    L156:
        Theme.setEmojiDrawableColor(r114, r220, true);
        goto L157
    L155:
        r220 = getThemedColor(Theme.key_chat_emojiPanelIconSelected);
        goto L156
    L151:
        r219 = getThemedColor(Theme.key_chat_emojiBottomPanelIcon);
        goto L152
    L97:
        if (this.glassDesign == false) goto L99;
        int r221 = getGlassIconColor(0.6f);
    L100:
        r13.setColorFilter(new PorterDuffColorFilter(r221, PorterDuff.Mode.MULTIPLY));
        goto L101
    L99:
        r221 = getThemedColor(Theme.key_chat_emojiPanelBackspace);
        goto L100
    L90:
        if (this.glassDesign == false) goto L92;
        int r7 = getGlassIconColor(0.6f);
    L93:
        r12.setColorFilter(new PorterDuffColorFilter(r7, PorterDuff.Mode.MULTIPLY));
        goto L94
    L92:
        r7 = getThemedColor(Theme.key_chat_emojiPanelBackspace);
        goto L93
    L81:
        if (this.glassDesign == false) goto L83;
        int r72 = getGlassIconColor(0.6f);
    L84:
        r1.setColorFilter(new PorterDuffColorFilter(r72, PorterDuff.Mode.MULTIPLY));
        if (this.emojiSearchField != null) goto L87;
        Drawable r115 = this.backspaceButton.getBackground();
        int r312 = Theme.key_chat_emojiPanelBackground;
        Theme.setSelectorDrawableColor(r115, getThemedColor(r312), false);
        Theme.setSelectorDrawableColor(this.backspaceButton.getBackground(), getThemedColor(r312), true);
        goto L87
    L83:
        r72 = getThemedColor(Theme.key_chats_actionIcon);
        goto L84
    L19:
        if (this.shouldDrawBackground == false) goto L21;
        r0.setBackgroundColor(getThemedColor(Theme.key_chat_emojiPanelBackground));
        this.emojiTabsShadow.setBackgroundColor(getThemedColor(Theme.key_chat_emojiPanelShadowLine));
        goto L22
    L21:
        r0.setBackground(null);
        goto L22
    L6:
        if (AndroidUtilities.isInMultiwindow == false) goto L8;
    L13:
        Drawable r06 = getBackground();
        if (r06 == null) goto L16;
        r06.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_emojiPanelBackground), PorterDuff.Mode.MULTIPLY));
        goto L16
    L8:
        if (this.forseMultiwindowLayout == true) goto L13;
        int r07 = Theme.key_chat_emojiPanelBackground;
        setBackgroundColor(getThemedColor(r07));
        if (this.needEmojiSearch == false) goto L16;
        this.bottomTabContainerBackground.setBackgroundColor(getThemedColor(r07));
        goto L16
    }
}
