package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Consumer;
import androidx.core.view.WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
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
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.FixedHeightEmptyCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CloseProgressDrawable2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.DrawingInBackgroundThreadDrawable;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.EmojiTabsStrip;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugView$$ExternalSyntheticLambda10;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Premium.PremiumLockIconView;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.Reactions.HwEmojis;
import org.telegram.ui.Components.Reactions.ReactionsEffectOverlay;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SearchStateDrawable;
import org.telegram.ui.Components.StickerCategoriesListView;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes3.dex */
public abstract class SelectAnimatedEmojiDialog extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static final List<String> emptyViewEmojis = null;
    private static boolean isFirstOpen;
    private static String[] lastSearchKeyboardLanguage;
    private static HashMap<Integer, Parcelable> listStates;
    private static boolean[] preloaded;
    private final int EXPAND_MAX_LINES;
    private final int RECENT_MAX_LINES;
    private final int SPAN_COUNT;
    private final int SPAN_COUNT_FOR_EMOJI;
    private final int SPAN_COUNT_FOR_STICKER;
    private int accentColor;
    private Adapter adapter;
    private View animateExpandFromButton;
    private float animateExpandFromButtonTranslate;
    private int animateExpandFromPosition;
    private long animateExpandStartTime;
    private int animateExpandToPosition;
    private boolean animationsEnabled;
    private BackgroundDelegate backgroundDelegate;
    private View backgroundView;
    public boolean badgePicker;
    private BaseFragment baseFragment;
    AnimatedEmojiDrawable bigReactionAnimatedEmoji;
    ImageReceiver bigReactionImageReceiver;
    public onLongPressedListener bigReactionListener;
    private boolean bottomGradientShown;
    private View bottomGradientView;
    private View bubble1View;
    private View bubble2View;
    private EmojiTabsStrip[] cachedEmojiTabs;
    public boolean cancelPressed;
    private Runnable clearSearchRunnable;
    private StarsReactionsSheet.Particles collectionParticles;
    private EditTextCaption commentView;
    public FrameLayout contentView;
    private View contentViewForeground;
    private final int currentAccount;
    private long defaultBadgeId;
    private boolean defaultSetLoading;
    private ArrayList<AnimatedEmojiSpan> defaultStatuses;
    private int defaultTopicIconRow;
    private Runnable dismiss;
    private boolean drawBackground;
    private Rect drawableToBounds;
    final float durationScale;
    public EmojiListView emojiGridView;
    public FrameLayout emojiGridViewContainer;
    DefaultItemAnimator emojiItemAnimator;
    public FrameLayout emojiSearchEmptyView;
    private BackupImageView emojiSearchEmptyViewImageView;
    public EmojiListView emojiSearchGridView;
    private float emojiSelectAlpha;
    private ValueAnimator emojiSelectAnimator;
    private Rect emojiSelectRect;
    private ImageViewEmoji emojiSelectView;
    public EmojiTabsStrip emojiTabs;
    public View emojiTabsShadow;
    private Integer emojiX;
    private boolean enterAnimationInProgress;
    private ArrayList<Long> expandedEmojiSets;
    public boolean forUser;
    private Drawable forumIconDrawable;
    private ImageViewEmoji forumIconImage;
    private ArrayList<TLRPC.TL_messages_stickerSet> frozenEmojiPacks;
    private ArrayList<TL_stars.TL_starGiftUnique> gifts;
    private int giftsEndRow;
    private int giftsSectionRow;
    private int giftsStartRow;
    private boolean gridSearch;
    private ValueAnimator gridSwitchAnimator;
    public FrameLayout gridViewContainer;
    private ValueAnimator hideAnimator;
    private Integer hintExpireDate;
    private boolean includeEmpty;
    public boolean includeHint;
    private ArrayList<Long> installedEmojiSets;
    private boolean isAttached;
    private boolean isLongPressEnabled;
    private String lastQuery;
    private GridLayoutManager layoutManager;
    private Integer listStateId;
    private int longtapHintRow;
    private final float maxDim;
    private AnimationNotificationsLocker notificationsLocker;
    public onRecentClearedListener onRecentClearedListener;
    private OvershootInterpolator overshootInterpolator;
    private ArrayList<EmojiView.EmojiPack> packs;
    Paint paint;
    public boolean paused;
    public boolean pausedExceptSelected;
    private int popularSectionRow;
    private SparseIntArray positionToButton;
    private SparseIntArray positionToExpand;
    private SparseIntArray positionToSection;
    private Drawable premiumStar;
    private final ColorFilter premiumStarColorFilter;
    float pressedProgress;
    private ArrayList<AnimatedEmojiSpan> recent;
    private EmojiPackExpand recentExpandButton;
    private boolean recentExpanded;
    private ArrayList<ReactionsLayoutInBubble.VisibleReaction> recentReactions;
    private int recentReactionsEndRow;
    private int recentReactionsSectionRow;
    private int recentReactionsStartRow;
    private List<ReactionsLayoutInBubble.VisibleReaction> recentReactionsToSet;
    private ArrayList<TLRPC.Document> recentStickers;
    private Theme.ResourcesProvider resourcesProvider;
    private ArrayList<Long> rowHashCodes;
    private float scaleX;
    private float scaleY;
    private float scrimAlpha;
    private int scrimColor;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable scrimDrawable;
    private View scrimDrawableParent;
    private RecyclerAnimationScrollHelper scrollHelper;
    private SearchAdapter searchAdapter;
    public SearchBox searchBox;
    private ValueAnimator searchEmptyViewAnimator;
    private boolean searchEmptyViewVisible;
    private GridLayoutManager searchLayoutManager;
    private ArrayList<ReactionsLayoutInBubble.VisibleReaction> searchResult;
    private ArrayList<ReactionsLayoutInBubble.VisibleReaction> searchResultStickers;
    private int searchRow;
    private Runnable searchRunnable;
    private ArrayList<TLRPC.Document> searchSets;
    public boolean searched;
    public boolean searchedLiftUp;
    public boolean searching;
    private SparseIntArray sectionToPosition;
    private SelectStatusDurationDialog selectStatusDateDialog;
    HashSet<Long> selectedDocumentIds;
    ImageViewEmoji selectedReactionView;
    HashSet<ReactionsLayoutInBubble.VisibleReaction> selectedReactions;
    public Paint selectorAccentPaint;
    public Paint selectorPaint;
    private ValueAnimator showAnimator;
    final long showDuration;
    private boolean showStickers;
    private boolean smoothScrolling;
    private ArrayList<String> standardEmojis;
    private ArrayList<TLRPC.TL_messages_stickerSet> stickerSets;
    private ArrayList<ReactionsLayoutInBubble.VisibleReaction> stickers;
    private int stickersEndRow;
    private ArrayList<TLRPC.Document> stickersSearchResult;
    private int stickersSectionRow;
    private int stickersStartRow;
    private boolean topGradientShown;
    private View topGradientView;
    private int topMarginDp;
    private ArrayList<ReactionsLayoutInBubble.VisibleReaction> topReactions;
    private int topReactionsEndRow;
    private int topReactionsStartRow;
    private int topicEmojiHeaderRow;
    private int totalCount;
    private int type;
    private final Runnable updateRows;
    private final Runnable updateRowsDelayed;
    public boolean useAccentForPlus;

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends View {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(SelectAnimatedEmojiDialog r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$resourcesProvider = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r2) {
            if (SelectAnimatedEmojiDialog.m18646$$Nest$fgetdrawBackground(this.this$0) == true) goto L6;
            super.dispatchDraw(r2);
            return;
        L6:
            r2.drawColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.val$resourcesProvider));
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass10(SelectAnimatedEmojiDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r3) {
            if (SelectAnimatedEmojiDialog.m18666$$Nest$fgetpositionToSection(this.this$0).indexOfKey(r3) >= 0) goto L32;
            if (SelectAnimatedEmojiDialog.m18664$$Nest$fgetpositionToButton(this.this$0).indexOfKey(r3) >= 0) goto L32;
            if (r3 == SelectAnimatedEmojiDialog.m18673$$Nest$fgetrecentReactionsSectionRow(this.this$0)) goto L32;
            if (r3 == SelectAnimatedEmojiDialog.m18696$$Nest$fgetstickersSectionRow(this.this$0)) goto L32;
            if (r3 == SelectAnimatedEmojiDialog.m18651$$Nest$fgetgiftsSectionRow(this.this$0)) goto L32;
            if (r3 == SelectAnimatedEmojiDialog.m18663$$Nest$fgetpopularSectionRow(this.this$0)) goto L32;
            if (r3 == SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0)) goto L32;
            if (r3 == SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0)) goto L32;
            if (r3 == SelectAnimatedEmojiDialog.m18702$$Nest$fgettopicEmojiHeaderRow(this.this$0)) goto L32;
            if (r3 < SelectAnimatedEmojiDialog.m18697$$Nest$fgetstickersStartRow(this.this$0)) goto L27;
            if (r3 >= SelectAnimatedEmojiDialog.m18694$$Nest$fgetstickersEndRow(this.this$0)) goto L27;
            return 8;
        L27:
            if (SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(this.this$0) == false) goto L29;
            return 8;
        L29:
            return 5;
        L32:
            return SelectAnimatedEmojiDialog.m18659$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$11, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass11 extends FrameLayout {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass11(SelectAnimatedEmojiDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3) + AndroidUtilities.dp(36.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$12, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass12 extends FrameLayout {
        private final Rect rect;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass12(SelectAnimatedEmojiDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rect = new Rect();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r11, View r12, long r13) {
            if (r12 != this.this$0.emojiGridView) goto L31;
            if (HwEmojis.isHwEnabled() == false) goto L31;
            if (HwEmojis.isCascade() == false) goto L31;
            int r1 = 0;
        L10:
            if (r1 >= this.this$0.emojiGridView.getChildCount()) goto L29;
            View r2 = this.this$0.emojiGridView.getChildAt(r1);
            if ((r2 instanceof ImageViewEmoji) == false) goto L20;
            ImageViewEmoji r3 = (ImageViewEmoji) r2;
            if (r3.getAnimatedScale() != 1.0f) goto L17;
            this.rect.set(r2.getLeft(), r2.getTop(), r2.getRight(), r2.getBottom());
            r11.save();
            r11.clipRect(this.rect);
            super.drawChild(r11, r12, r13);
            r11.restore();
        L28:
            r1 = r1 + 1;
            goto L10
        L17:
            if (r3.getAnimatedScale() <= 0.0f) goto L28;
            this.rect.set(r2.getLeft(), r2.getTop(), r2.getRight(), r2.getBottom());
            this.rect.set((int) (r2.centerX() - ((this.rect.width() / 2.0f) * r3.getAnimatedScale())), (int) (this.rect.centerY() - ((this.rect.height() / 2.0f) * r3.getAnimatedScale())), (int) (this.rect.centerX() + ((this.rect.width() / 2.0f) * r3.getAnimatedScale())), (int) (this.rect.centerY() + ((this.rect.height() / 2.0f) * r3.getAnimatedScale())));
            r11.save();
            r11.clipRect(this.rect);
            r11.scale(r3.getAnimatedScale(), r3.getAnimatedScale(), this.rect.centerX(), this.rect.centerY());
            super.drawChild(r11, r12, r13);
            r11.restore();
            goto L28
        L20:
            if ((r2 instanceof TextView) == false) goto L22;
        L27:
            this.rect.set(r2.getLeft(), r2.getTop(), r2.getRight(), r2.getBottom());
            r11.save();
            r11.clipRect(this.rect);
            super.drawChild(r11, r12, r13);
            r11.restore();
            goto L28
        L22:
            if ((r2 instanceof EmojiPackExpand) == true) goto L27;
            if ((r2 instanceof EmojiPackButton) == true) goto L27;
            if ((r2 instanceof HeaderView) == false) goto L28;
        L29:
            return false;
        L31:
            return super.drawChild(r11, r12, r13);
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$13, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass13 extends EmojiListView {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass13(SelectAnimatedEmojiDialog r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
            SelectAnimatedEmojiDialog.m18713$$Nest$mcheckScroll(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$14, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass14 extends GridLayoutManager {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$14$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScrollerCustom {
            final /* synthetic */ AnonymousClass14 this$1;

            public AnonymousClass1(AnonymousClass14 r1, Context r2, int r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom
            public void onEnd() {
                SelectAnimatedEmojiDialog.m18712$$Nest$fputsmoothScrolling(this.this$1.this$0, false);
            }
        }

        public AnonymousClass14(SelectAnimatedEmojiDialog r1, Context r2, int r3) {
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

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$15, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass15 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass15(SelectAnimatedEmojiDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r3) {
            int r0 = SelectAnimatedEmojiDialog.m18682$$Nest$fgetsearchAdapter(this.this$0).getItemViewType(r3);
            if (r0 != 6) goto L7;
            return SelectAnimatedEmojiDialog.m18659$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        L7:
            if (r0 != 5) goto L9;
            return 8;
        L9:
            if (SelectAnimatedEmojiDialog.m18682$$Nest$fgetsearchAdapter(this.this$0).isSticker(r3) == true) goto L14;
            return 5;
        L14:
            return 8;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$16, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass16 extends RecyclerAnimationScrollHelper.AnimationCallback {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass16(SelectAnimatedEmojiDialog r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void onEndAnimation() {
            SelectAnimatedEmojiDialog.m18712$$Nest$fputsmoothScrolling(this.this$0, false);
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.AnimationCallback
        public void onPreAnimation() {
            SelectAnimatedEmojiDialog.m18712$$Nest$fputsmoothScrolling(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$17, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass17 implements RecyclerListView.OnItemLongClickListenerExtended {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ boolean val$badgePicker;
        final /* synthetic */ Context val$context;
        final /* synthetic */ Integer val$emojiX;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ int val$type;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$17$1, reason: invalid class name */
        public class AnonymousClass1 extends SelectStatusDurationDialog {
            final /* synthetic */ AnonymousClass17 this$1;
            final /* synthetic */ TL_stars.TL_starGiftUnique val$gift;
            final /* synthetic */ View val$view;

            public AnonymousClass1(AnonymousClass17 r1, Context r2, Runnable r3, View r4, ImageViewEmoji r5, Theme.ResourcesProvider r6, View r7, TL_stars.TL_starGiftUnique r8) {
                this.this$1 = r1;
                this.val$view = r7;
                this.val$gift = r8;
                super(r1.this$0, r2, r3, r4, r5, r6);
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectStatusDurationDialog, android.app.Dialog, android.content.DialogInterface
            public void dismiss() {
                super.dismiss();
                SelectAnimatedEmojiDialog.m18710$$Nest$fputselectStatusDateDialog(this.this$1.this$0, null);
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectStatusDurationDialog
            public boolean getOutBounds(Rect r2) {
                if (SelectAnimatedEmojiDialog.m18681$$Nest$fgetscrimDrawable(this.this$1.this$0) == null) goto L8;
                AnonymousClass17 r1 = this.this$1;
                if (r1.val$emojiX == null) goto L10;
                r2.set(SelectAnimatedEmojiDialog.m18647$$Nest$fgetdrawableToBounds(r1.this$0));
                return true;
            L10:
                return false;
            L8:
                return false;
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectStatusDurationDialog
            public void onEnd(Integer r1) {
                if (r1 != null) goto L4;
                return;
            L4:
                if (SelectAnimatedEmojiDialog.m18645$$Nest$fgetdismiss(this.this$1.this$0) == null) goto L8;
                SelectAnimatedEmojiDialog.m18645$$Nest$fgetdismiss(this.this$1.this$0).run();
                return;
            }

            @Override // org.telegram.ui.SelectAnimatedEmojiDialog.SelectStatusDurationDialog
            public void onEndPartly(Integer r8) {
                SelectAnimatedEmojiDialog.m18717$$Nest$mincrementHintUse(this.this$1.this$0);
                TLRPC.TL_emojiStatus r0 = new TLRPC.TL_emojiStatus();
                View r2 = this.val$view;
                long r3 = ((ImageViewEmoji) r2).span.documentId;
                r0.document_id = r3;
                this.this$1.this$0.onEmojiSelected(r2, Long.valueOf(r3), ((ImageViewEmoji) this.val$view).span.document, this.val$gift, r8);
                if (this.val$gift != null) goto L6;
                MediaDataController.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$1.this$0)).pushRecentEmojiStatus(r0);
                return;
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$17$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass17 this$1;

            public AnonymousClass2(AnonymousClass17 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SelectAnimatedEmojiDialog r1 = this.this$1.this$0;
                r1.selectedReactionView.bigReactionSelectedProgress = 0.0f;
                r1.selectedReactionView = null;
                r1.emojiGridView.invalidate();
            }
        }

        public static /* synthetic */ void $r8$lambda$o6K8QkGOGkKfxGdYK2FzfK0FvB8(AnonymousClass17 r0, ValueAnimator r1) {
            r0.lambda$onLongClickRelease$0(r1);
        }

        public AnonymousClass17(SelectAnimatedEmojiDialog r1, int r2, boolean r3, Context r4, Theme.ResourcesProvider r5, Integer r6) {
            this.this$0 = r1;
            this.val$type = r2;
            this.val$badgePicker = r3;
            this.val$context = r4;
            this.val$resourcesProvider = r5;
            this.val$emojiX = r6;
        }

        private /* synthetic */ void lambda$onLongClickRelease$0(ValueAnimator r1) {
            SelectAnimatedEmojiDialog r0 = this.this$0;
            r0.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public boolean onItemClick(View r12, int r13, float r14, float r15) {
            int r132 = this.val$type;
            if (r132 != 11) goto L5;
        L53:
            return false;
        L5:
            if (r132 == 13) goto L53;
            if (SelectAnimatedEmojiDialog.m18657$$Nest$fgetisLongPressEnabled(this.this$0) == false) goto L53;
            boolean r133 = r12 instanceof ImageViewEmoji;
            if (r133 == false) goto L37;
            int r0 = this.val$type;
            if (r0 != 1) goto L14;
        L15:
            SelectAnimatedEmojiDialog.m18717$$Nest$mincrementHintUse(this.this$0);
            this.this$0.performHapticFeedback(0);     // Catch: Exception -> L54
        L17:
            ImageViewEmoji r1 = (ImageViewEmoji) r12;
            String r134 = null;
            if (r1.isDefaultReaction == false) goto L20;
        L29:
            SelectAnimatedEmojiDialog r122 = this.this$0;
            r122.selectedReactionView = r1;
            r122.pressedProgress = 0.0f;
            r122.cancelPressed = false;
            if (r1.isDefaultReaction == false) goto L34;
            SelectAnimatedEmojiDialog.m18719$$Nest$msetBigReactionAnimatedEmoji(r122, null);
            TLRPC.TL_availableReaction r123 = MediaDataController.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).getReactionsMap().get(this.this$0.selectedReactionView.reaction.emojicon);
            if (r123 == null) goto L35;
            this.this$0.bigReactionImageReceiver.setImage(ImageLocation.getForDocument(r123.select_animation), "60_60_pcache", null, null, null, 0, "tgs", this.this$0.selectedReactionView.reaction, 0);
        L35:
            this.this$0.emojiGridView.invalidate();
            this.this$0.invalidateParent();
            return true;
        L34:
            SelectAnimatedEmojiDialog.m18719$$Nest$msetBigReactionAnimatedEmoji(r122, new AnimatedEmojiDrawable(4, SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0), this.this$0.selectedReactionView.span.documentId));
            goto L35
        L20:
            if (UserConfig.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).isPremium() == true) goto L29;
            TLRPC.Document r124 = r1.span.document;
            if (r124 != null) goto L24;
            r124 = AnimatedEmojiDrawable.findDocument(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0), r1.span.documentId);
        L24:
            TLRPC.Document r3 = r124;
            SelectAnimatedEmojiDialog r02 = this.this$0;
            Long r2 = Long.valueOf(r1.span.documentId);
            TL_stars.TL_starGiftUnique r4 = r1.starGift;
            if (SelectAnimatedEmojiDialog.m18641$$Nest$fgetcommentView(this.this$0) == null) goto L27;
            r134 = SelectAnimatedEmojiDialog.m18641$$Nest$fgetcommentView(this.this$0).getText().toString();
        L27:
            r02.onEmojiSelected(r1, r2, r3, r4, null, r134);
            return true;
        L14:
            if (r0 == 8) goto L15;
        L37:
            if (r133 == false) goto L53;
            ImageViewEmoji r5 = (ImageViewEmoji) r12;
            if (r5.span == null) goto L53;
            if (this.val$badgePicker == true) goto L53;
            int r135 = this.val$type;
            if (r135 != 0) goto L45;
        L50:
            TL_stars.TL_starGiftUnique r8 = r5.starGift;
            SelectAnimatedEmojiDialog r136 = this.this$0;
            AnonymousClass1 r03 = new AnonymousClass1(this, this.val$context, SelectAnimatedEmojiDialog.m18645$$Nest$fgetdismiss(this.this$0), this.this$0, r5, this.val$resourcesProvider, r12, r8);
            SelectAnimatedEmojiDialog.m18710$$Nest$fputselectStatusDateDialog(r136, r03);
            r03.show();
            r12.performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L55
        L52:
            return true;
        L45:
            if (r135 == 12) goto L50;
            if (r135 == 9) goto L50;
            if (r135 != 10) goto L53;
            goto L50
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public void onLongClickRelease() {
            SelectAnimatedEmojiDialog r0 = this.this$0;
            if (r0.selectedReactionView == null) goto L6;
            r0.cancelPressed = true;
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r0.pressedProgress, 0.0f});
            r02.addUpdateListener(new SelectAnimatedEmojiDialog$17$$ExternalSyntheticLambda0(this));
            r02.addListener(new AnonymousClass2(this));
            r02.setDuration(150);
            r02.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r02.start();
            return;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$18, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass18 extends SearchBox {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass18(SelectAnimatedEmojiDialog r1, Context r2, boolean r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            if (SelectAnimatedEmojiDialog.m18634$$Nest$fgetbackgroundDelegate(this.this$0) == null) goto L5;
            BackgroundDelegate r1 = SelectAnimatedEmojiDialog.m18634$$Nest$fgetbackgroundDelegate(this.this$0);
            int r5 = getMeasuredWidth();
            int r6 = getMeasuredHeight();
            float r7 = this.this$0.searchBox.getX() + this.this$0.gridViewContainer.getX();
            float r8 = this.this$0.searchBox.getY() + this.this$0.gridViewContainer.getY();
            Canvas r2 = r10;
            r1.drawRect(r2, 0, 0, r5, r6, r7, r8);
        L6:
            super.dispatchDraw(r2);
            return;
        L5:
            r2 = r10;
            goto L6
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            if (r2 == getTranslationY()) goto L8;
            super.setTranslationY(r2);
            if (SelectAnimatedEmojiDialog.m18634$$Nest$fgetbackgroundDelegate(this.this$0) == null) goto L9;
            invalidate();
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$19, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass19 extends View {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ Integer val$bubbleX;

        public AnonymousClass19(SelectAnimatedEmojiDialog r1, Context r2, Integer r3) {
            this.this$0 = r1;
            this.val$bubbleX = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (this.val$bubbleX == null) goto L6;
            setPivotX(r1.intValue());
            return;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends FrameLayout {
        private final Paint paintApi20;
        private final Path pathApi20;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ Integer val$bubbleX;
        final /* synthetic */ boolean val$clipWithPath;
        final /* synthetic */ boolean val$needBackgroundShadow;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass2(SelectAnimatedEmojiDialog r1, Context r2, boolean r3, boolean r4, Theme.ResourcesProvider r5, Integer r6) {
            this.this$0 = r1;
            this.val$clipWithPath = r3;
            this.val$needBackgroundShadow = r4;
            this.val$resourcesProvider = r5;
            this.val$bubbleX = r6;
            super(r2);
            this.pathApi20 = new Path();
            this.paintApi20 = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            if (SelectAnimatedEmojiDialog.m18646$$Nest$fgetdrawBackground(this.this$0) == true) goto L7;
            super.dispatchDraw(r9);
            return;
        L7:
            if (this.val$clipWithPath == false) goto L21;
            r9.save();
            if (this.val$needBackgroundShadow == false) goto L11;
            Theme.applyDefaultShadow(this.paintApi20);
        L11:
            this.paintApi20.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.val$resourcesProvider));
            this.paintApi20.setAlpha((int) (getAlpha() * 255.0f));
            Integer r0 = this.val$bubbleX;
            if (r0 != null) goto L14;
            float r02 = getWidth() / 2.0f;
        L15:
            float r03 = r02 + AndroidUtilities.dp(20.0f);
            float r1 = (getWidth() - getPaddingLeft()) - getPaddingRight();
            float r2 = (getHeight() - getPaddingBottom()) - getPaddingTop();
            if (this.this$0.isBottom() == false) goto L18;
            AndroidUtilities.rectTmp.set(getPaddingLeft() + (r03 - (SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0) * r03)), getPaddingTop() + ((1.0f - SelectAnimatedEmojiDialog.m18679$$Nest$fgetscaleY(this.this$0)) * r2), (getPaddingLeft() + r03) + ((r1 - r03) * SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0)), getPaddingTop() + r2);
        L19:
            this.pathApi20.rewind();
            this.pathApi20.addRoundRect(AndroidUtilities.rectTmp, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
            r9.drawPath(this.pathApi20, this.paintApi20);
            r9.clipPath(this.pathApi20);
            super.dispatchDraw(r9);
            r9.restore();
            return;
        L18:
            AndroidUtilities.rectTmp.set(getPaddingLeft() + (r03 - (SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0) * r03)), getPaddingTop(), (getPaddingLeft() + r03) + ((r1 - r03) * SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0)), getPaddingTop() + (r2 * SelectAnimatedEmojiDialog.m18679$$Nest$fgetscaleY(this.this$0)));
            goto L19
        L14:
            r02 = r0.intValue();
            goto L15
        L21:
            super.dispatchDraw(r9);
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$20, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass20 extends AnimatorListenerAdapter {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ boolean[] val$done;
        final /* synthetic */ Runnable val$onDone;

        public AnonymousClass20(SelectAnimatedEmojiDialog r1, boolean[] r2, Runnable r3) {
            this.this$0 = r1;
            this.val$done = r2;
            this.val$onDone = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            SelectAnimatedEmojiDialog.m18705$$Nest$fputemojiSelectView(this.this$0, null);
            this.this$0.invalidate();
            boolean[] r32 = this.val$done;
            if (r32[0] == true) goto L6;
            r32[0] = true;
            this.val$onDone.run();
            return;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$21, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass21 extends LinearSmoothScrollerCustom {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass21(SelectAnimatedEmojiDialog r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom
        public void onEnd() {
            SelectAnimatedEmojiDialog.m18712$$Nest$fputsmoothScrolling(this.this$0, false);
        }

        @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public void onStart() {
            SelectAnimatedEmojiDialog.m18712$$Nest$fputsmoothScrolling(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$22, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass22 extends AnimatorListenerAdapter {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ boolean val$search;

        public AnonymousClass22(SelectAnimatedEmojiDialog r1, boolean r2) {
            this.this$0 = r1;
            this.val$search = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            EmojiListView r42 = this.this$0.emojiSearchGridView;
            int r1 = 8;
            if (this.val$search == false) goto L5;
            int r0 = 0;
        L6:
            r42.setVisibility(r0);
            EmojiListView r43 = this.this$0.emojiGridView;
            if (this.val$search == true) goto L10;
            r1 = 0;
        L10:
            r43.setVisibility(r1);
            SelectAnimatedEmojiDialog.m18707$$Nest$fputgridSwitchAnimator(this.this$0, null);
            if (this.val$search == true) goto L19;
            if (SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0) == null) goto L19;
            SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).clear();
            if (SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0) == null) goto L17;
            SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).clear();
        L17:
            SelectAnimatedEmojiDialog.m18682$$Nest$fgetsearchAdapter(this.this$0).updateRows(false);
        L19:
            if (this.val$search == false) goto L21;
            return;
        L21:
            if (SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0) == null) goto L25;
            SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).clear();
            return;
        L25:
            return;
        L5:
            r0 = 8;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$23, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass23 extends AnimatorListenerAdapter {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ boolean val$empty;

        public AnonymousClass23(SelectAnimatedEmojiDialog r1, boolean r2) {
            this.this$0 = r1;
            this.val$empty = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            SelectAnimatedEmojiDialog r32 = this.this$0;
            FrameLayout r0 = r32.emojiSearchEmptyView;
            if (this.val$empty == true) goto L5;
        L7:
            int r33 = 8;
        L8:
            r0.setVisibility(r33);
            SelectAnimatedEmojiDialog.m18709$$Nest$fputsearchEmptyViewAnimator(this.this$0, null);
            return;
        L5:
            if (r32.emojiSearchGridView.getVisibility() != 0) goto L7;
            r33 = 0;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$24, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass24 extends DiffUtil.Callback {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ ArrayList val$prevRowHashCodes;

        public AnonymousClass24(SelectAnimatedEmojiDialog r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$prevRowHashCodes = r2;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r2, int r3) {
            return ((Long) this.val$prevRowHashCodes.get(r2)).equals(SelectAnimatedEmojiDialog.m18677$$Nest$fgetrowHashCodes(this.this$0).get(r3));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return SelectAnimatedEmojiDialog.m18677$$Nest$fgetrowHashCodes(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.val$prevRowHashCodes.size();
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$25, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass25 extends AnimatorListenerAdapter {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass25(SelectAnimatedEmojiDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            HwEmojis.disableHw();
            int r0 = 0;
            this.this$0.emojiGridView.setLayerType(0, null);
            this.this$0.searchBox.setLayerType(0, null);
            this.this$0.emojiTabsShadow.setLayerType(0, null);
            SelectAnimatedEmojiDialog.m18635$$Nest$fgetbackgroundView(this.this$0).setLayerType(0, null);
            if (SelectAnimatedEmojiDialog.m18639$$Nest$fgetbubble2View(this.this$0) == null) goto L6;
            SelectAnimatedEmojiDialog.m18639$$Nest$fgetbubble2View(this.this$0).setLayerType(0, null);
        L6:
            if (SelectAnimatedEmojiDialog.m18638$$Nest$fgetbubble1View(this.this$0) == null) goto L8;
            SelectAnimatedEmojiDialog.m18638$$Nest$fgetbubble1View(this.this$0).setLayerType(0, null);
        L8:
            this.this$0.searchBox.checkInitialization();
            this.this$0.emojiTabs.showRecentTabStub(false);
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            SelectAnimatedEmojiDialog.m18661$$Nest$fgetnotificationsLocker(this.this$0).unlock();
            NotificationCenter r42 = NotificationCenter.getGlobalInstance();
            Objects.requireNonNull(r42);
            AndroidUtilities.runOnUIThread(new SelectAnimatedEmojiDialog$25$$ExternalSyntheticLambda0(r42));
            SelectAnimatedEmojiDialog.m18713$$Nest$mcheckScroll(this.this$0);
            SelectAnimatedEmojiDialog.m18723$$Nest$mupdateShow(this.this$0, 1.0f);
            int r43 = 0;
        L10:
            if (r43 >= this.this$0.emojiGridView.getChildCount()) goto L12;
            View r2 = this.this$0.emojiGridView.getChildAt(r43);
            r2.setScaleX(1.0f);
            r2.setScaleY(1.0f);
            r43 = r43 + 1;
        L12:
            int r44 = this.this$0.emojiTabs.contentView.getChildCount();
            SelectAnimatedEmojiDialog r22 = this.this$0;
            if (r0 >= r44) goto L15;
            View r45 = r22.emojiTabs.contentView.getChildAt(r0);
            r45.setScaleX(1.0f);
            r45.setScaleY(1.0f);
            r0 = r0 + 1;
            goto L12
        L15:
            r22.emojiTabs.contentView.invalidate();
            this.this$0.emojiGridViewContainer.invalidate();
            this.this$0.emojiGridView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$26, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass26 extends AnimatorListenerAdapter {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ Runnable val$dismiss;

        public AnonymousClass26(SelectAnimatedEmojiDialog r1, Runnable r2) {
            this.this$0 = r1;
            this.val$dismiss = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.val$dismiss.run();
            if (SelectAnimatedEmojiDialog.m18688$$Nest$fgetselectStatusDateDialog(this.this$0) == null) goto L6;
            SelectAnimatedEmojiDialog.m18688$$Nest$fgetselectStatusDateDialog(this.this$0).dismiss();
            SelectAnimatedEmojiDialog.m18710$$Nest$fputselectStatusDateDialog(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends ViewOutlineProvider {
        private final Rect rect;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ Integer val$bubbleX;

        public AnonymousClass3(SelectAnimatedEmojiDialog r1, Integer r2) {
            this.this$0 = r1;
            this.val$bubbleX = r2;
            this.rect = new Rect();
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r10, Outline r11) {
            Integer r0 = this.val$bubbleX;
            if (r0 != null) goto L5;
            float r02 = r10.getWidth() / 2.0f;
        L6:
            float r03 = r02 + AndroidUtilities.dp(20.0f);
            float r1 = (r10.getWidth() - r10.getPaddingLeft()) - r10.getPaddingRight();
            float r2 = (r10.getHeight() - r10.getPaddingBottom()) - r10.getPaddingTop();
            boolean r3 = this.this$0.isBottom();
            Rect r4 = this.rect;
            if (r3 == false) goto L9;
            r4.set((int) (r10.getPaddingLeft() + (r03 - (SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0) * r03))), (int) ((r10.getPaddingTop() + ((1.0f - SelectAnimatedEmojiDialog.m18679$$Nest$fgetscaleY(this.this$0)) * r2)) + (AndroidUtilities.dp(SelectAnimatedEmojiDialog.m18698$$Nest$fgettopMarginDp(this.this$0)) * (1.0f - SelectAnimatedEmojiDialog.m18679$$Nest$fgetscaleY(this.this$0)))), (int) ((r10.getPaddingLeft() + r03) + ((r1 - r03) * SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0))), (int) ((r10.getPaddingTop() + r2) + (AndroidUtilities.dp(SelectAnimatedEmojiDialog.m18698$$Nest$fgettopMarginDp(this.this$0)) * (1.0f - SelectAnimatedEmojiDialog.m18679$$Nest$fgetscaleY(this.this$0)))));
        L10:
            r11.setRoundRect(this.rect, AndroidUtilities.dp(12.0f));
            return;
        L9:
            r4.set((int) (r10.getPaddingLeft() + (r03 - (SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0) * r03))), r10.getPaddingTop(), (int) ((r10.getPaddingLeft() + r03) + ((r1 - r03) * SelectAnimatedEmojiDialog.m18678$$Nest$fgetscaleX(this.this$0))), (int) (r10.getPaddingTop() + (r2 * SelectAnimatedEmojiDialog.m18679$$Nest$fgetscaleY(this.this$0))));
            goto L10
        L5:
            r02 = r0.intValue();
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends View {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass4(SelectAnimatedEmojiDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setPivotX(getMeasuredWidth() / 2);
            setPivotY(getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends EmojiTabsStrip {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ int val$type;

        public AnonymousClass5(SelectAnimatedEmojiDialog r1, Context r2, Theme.ResourcesProvider r3, boolean r4, boolean r5, boolean r6, boolean r7, int r8, Runnable r9, int r10, int r11) {
            this.this$0 = r1;
            this.val$type = r11;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10);
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public ColorFilter getEmojiColorFilter() {
            return SelectAnimatedEmojiDialog.m18667$$Nest$fgetpremiumStarColorFilter(this.this$0);
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public boolean onTabClick(int r7) {
            int r1 = 0;
            if (SelectAnimatedEmojiDialog.m18691$$Nest$fgetsmoothScrolling(this.this$0) == false) goto L6;
            return false;
        L6:
            if (this.val$type != 4) goto L18;
            if (r7 != 0) goto L18;
            SelectAnimatedEmojiDialog.m18711$$Nest$fputshowStickers(this.this$0, !SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(r7));
            this.this$0.emojiTabs.setVisibility(8);
            SelectAnimatedEmojiDialog r72 = this.this$0;
            r72.emojiTabs = SelectAnimatedEmojiDialog.m18640$$Nest$fgetcachedEmojiTabs(r72)[SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(this.this$0) ? 1 : 0];
            this.this$0.emojiTabs.setVisibility(0);
            EmojiTabsStrip.EmojiTabButton r73 = this.this$0.emojiTabs.toggleEmojiStickersTab;
            Context r0 = getContext();
            if (SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(this.this$0) == false) goto L11;
            int r2 = R.drawable.msg_emoji_stickers;
        L12:
            r73.setDrawable(ContextCompat.getDrawable(r0, r2));
            SelectAnimatedEmojiDialog r74 = this.this$0;
            EmojiTabsStrip.EmojiTabButton r02 = r74.emojiTabs.toggleEmojiStickersTab;
            if (SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(r74) == false) goto L15;
            int r75 = R.string.AccDescrStickers;
        L16:
            r02.setContentDescription(LocaleController.getString(r75));
            SelectAnimatedEmojiDialog.m18721$$Nest$mupdateRows(this.this$0, true, false, false);
            SelectAnimatedEmojiDialog.m18659$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
            return true;
        L15:
            r75 = R.string.Emoji;
            goto L16
        L11:
            r2 = R.drawable.msg_emoji_smiles;
        L18:
            int r03 = (isGiftsVisible() ? 1 : 0) + 1;
            if (isGiftsVisible() == false) goto L23;
            if (r7 != 1) goto L23;
            int r04 = SelectAnimatedEmojiDialog.m18651$$Nest$fgetgiftsSectionRow(this.this$0);
        L30:
            SelectAnimatedEmojiDialog r22 = this.this$0;
            if (this.val$type != 6) goto L33;
            r1 = 7;
        L33:
            SelectAnimatedEmojiDialog.m18718$$Nest$mscrollToPosition(r22, r04, AndroidUtilities.dp(r1 - 2));
            this.this$0.emojiTabs.select(r7);
            SelectAnimatedEmojiDialog r76 = this.this$0;
            r76.emojiGridView.scrolledByUserOnce = true;
            r76.search(null);
            SearchBox r77 = this.this$0.searchBox;
            if (r77 != null) goto L36;
        L38:
            return true;
        L36:
            if (SearchBox.m18732$$Nest$fgetcategoriesListView(r77) == null) goto L38;
            SearchBox.m18732$$Nest$fgetcategoriesListView(this.this$0.searchBox).selectCategory(null);
        L23:
            if (this.val$type != 4) goto L26;
            if (r7 != 0) goto L26;
        L25:
            r04 = 0;
        L26:
            if (r7 <= 0) goto L25;
            int r05 = r7 - r03;
            if (SelectAnimatedEmojiDialog.m18687$$Nest$fgetsectionToPosition(this.this$0).indexOfKey(r05) < 0) goto L25;
            r04 = SelectAnimatedEmojiDialog.m18687$$Nest$fgetsectionToPosition(this.this$0).get(r05);
            goto L30
        }

        @Override // org.telegram.ui.Components.EmojiTabsStrip
        public void onTabCreate(EmojiTabsStrip.EmojiTabButton r2) {
            if (SelectAnimatedEmojiDialog.m18689$$Nest$fgetshowAnimator(this.this$0) != null) goto L5;
        L8:
            r2.setScaleX(0.0f);
            r2.setScaleY(0.0f);
            return;
        L5:
            if (SelectAnimatedEmojiDialog.m18689$$Nest$fgetshowAnimator(this.this$0).isRunning() == true) goto L8;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends View {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ Integer val$bubbleX;

        public AnonymousClass6(SelectAnimatedEmojiDialog r1, Context r2, Integer r3) {
            this.this$0 = r1;
            this.val$bubbleX = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (this.val$bubbleX == null) goto L6;
            setPivotX(r1.intValue());
            return;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 extends EmojiListView {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        final /* synthetic */ int val$type;

        public AnonymousClass7(SelectAnimatedEmojiDialog r1, Context r2, int r3) {
            this.this$0 = r1;
            this.val$type = r3;
            super(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrollStateChanged(int r5) {
            if (r5 != 0) goto L14;
            SelectAnimatedEmojiDialog.m18712$$Nest$fputsmoothScrolling(this.this$0, false);
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L14;
            if (this.this$0.searchBox.getVisibility() != 0) goto L14;
            if (this.this$0.searchBox.getTranslationY() <= (-AndroidUtilities.dp(51.0f))) goto L14;
            SelectAnimatedEmojiDialog r0 = this.this$0;
            if (r0.searchBox.getTranslationY() <= (-AndroidUtilities.dp(16.0f))) goto L12;
            int r2 = 0;
        L13:
            SelectAnimatedEmojiDialog.m18718$$Nest$mscrollToPosition(r0, r2, 0);
            goto L14
        L12:
            r2 = 1;
        L14:
            super.onScrollStateChanged(r5);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r3, int r4) {
            super.onScrolled(r3, r4);
            SelectAnimatedEmojiDialog.m18713$$Nest$mcheckScroll(this.this$0);
            if (SelectAnimatedEmojiDialog.m18691$$Nest$fgetsmoothScrolling(this.this$0) == true) goto L5;
            SelectAnimatedEmojiDialog r32 = this.this$0;
            SelectAnimatedEmojiDialog.m18724$$Nest$mupdateTabsPosition(r32, SelectAnimatedEmojiDialog.m18659$$Nest$fgetlayoutManager(r32).findFirstCompletelyVisibleItemPosition());
        L5:
            SelectAnimatedEmojiDialog.m18722$$Nest$mupdateSearchBox(this.this$0);
            SelectAnimatedEmojiDialog r33 = this.this$0;
            View r42 = r33.emojiTabsShadow;
            if (r33.emojiGridView.computeVerticalScrollOffset() != 0) goto L20;
            int r34 = this.val$type;
            if (r34 == 0) goto L20;
            if (r34 == 12) goto L20;
            if (r34 == 10) goto L20;
            if (r34 == 1) goto L20;
            if (r34 == 11) goto L20;
            if (r34 == 6) goto L20;
            boolean r35 = false;
        L21:
            AndroidUtilities.updateViewVisibilityAnimated(r42, r35, 1.0f, true);
            this.this$0.invalidateParent();
            return;
        L20:
            r35 = true;
            goto L21
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 extends DefaultItemAnimator {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public AnonymousClass8(SelectAnimatedEmojiDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            if ((r1 instanceof EmojiPackExpand) == false) goto L6;
            return 0.6f;
        L6:
            return 0.0f;
        }
    }

    /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends GridLayoutManager {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$9$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScrollerCustom {
            final /* synthetic */ AnonymousClass9 this$1;

            public AnonymousClass1(AnonymousClass9 r1, Context r2, int r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.recyclerview.LinearSmoothScrollerCustom
            public void onEnd() {
                SelectAnimatedEmojiDialog.m18712$$Nest$fputsmoothScrolling(this.this$1.this$0, false);
            }
        }

        public AnonymousClass9(SelectAnimatedEmojiDialog r1, Context r2, int r3) {
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

    /* loaded from: classes6.dex */
    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(AndroidUtilities.dp(26.0f)), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$iKgG0GifNzUutumoXhSIUKNG1t0(Adapter r0, EmojiView.EmojiPack r1, int r2, View r3) {
            r0.lambda$onBindViewHolder$0(r1, r2, r3);
        }

        private Adapter(SelectAnimatedEmojiDialog r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(SelectAnimatedEmojiDialog r1, SelectAnimatedEmojiDialogIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(EmojiView.EmojiPack r7, int r8, View r9) {
            if (r7.free == false) goto L5;
        L10:
            int r0 = 0;
        L12:
            if (r0 >= this.this$0.emojiGridView.getChildCount()) goto L21;
            if ((this.this$0.emojiGridView.getChildAt(r0) instanceof EmojiPackExpand) == false) goto L20;
            View r1 = this.this$0.emojiGridView.getChildAt(r0);
            int r3 = this.this$0.emojiGridView.getChildAdapterPosition(r1);
            if (r3 < 0) goto L20;
            if (SelectAnimatedEmojiDialog.m18665$$Nest$fgetpositionToExpand(this.this$0).get(r3) != r8) goto L20;
            Integer r82 = Integer.valueOf(r3);
        L22:
            if (r82 == null) goto L24;
            this.this$0.expand(r82.intValue(), r1);
        L24:
            EmojiPacksAlert.installSet(null, r7.set, false);
            SelectAnimatedEmojiDialog.m18655$$Nest$fgetinstalledEmojiSets(this.this$0).add(Long.valueOf(r7.set.id));
            SelectAnimatedEmojiDialog.m18720$$Nest$mupdateRows(this.this$0, true, true);
            return;
        L20:
            r0 = r0 + 1;
            goto L12
        L21:
            r82 = null;
            r1 = null;
            goto L22
        L5:
            if (UserConfig.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).isPremium() == true) goto L10;
            BaseFragment r72 = LaunchActivity.getLastFragment();
            if (r72 == null) goto L31;
            r72.showDialog(new PremiumFeatureBottomSheet(SelectAnimatedEmojiDialog.m18636$$Nest$fgetbaseFragment(this.this$0), this.this$0.getContext(), SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0), 11, false));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return SelectAnimatedEmojiDialog.m18703$$Nest$fgettotalCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return Math.abs(((Long) SelectAnimatedEmojiDialog.m18677$$Nest$fgetrowHashCodes(this.this$0).get(r1)).longValue());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0)) goto L7;
            return 7;
        L7:
            if (r3 < SelectAnimatedEmojiDialog.m18674$$Nest$fgetrecentReactionsStartRow(this.this$0)) goto L11;
            if (r3 >= SelectAnimatedEmojiDialog.m18672$$Nest$fgetrecentReactionsEndRow(this.this$0)) goto L11;
            return 1;
        L11:
            if (r3 < SelectAnimatedEmojiDialog.m18701$$Nest$fgettopReactionsStartRow(this.this$0)) goto L15;
            if (r3 >= SelectAnimatedEmojiDialog.m18700$$Nest$fgettopReactionsEndRow(this.this$0)) goto L15;
            return 1;
        L15:
            if (r3 < SelectAnimatedEmojiDialog.m18697$$Nest$fgetstickersStartRow(this.this$0)) goto L21;
            if (r3 >= SelectAnimatedEmojiDialog.m18694$$Nest$fgetstickersEndRow(this.this$0)) goto L21;
            return 1;
        L21:
            if (r3 < SelectAnimatedEmojiDialog.m18652$$Nest$fgetgiftsStartRow(this.this$0)) goto L26;
            if (r3 >= SelectAnimatedEmojiDialog.m18650$$Nest$fgetgiftsEndRow(this.this$0)) goto L26;
            return 3;
        L26:
            if (SelectAnimatedEmojiDialog.m18665$$Nest$fgetpositionToExpand(this.this$0).indexOfKey(r3) < 0) goto L30;
            return 4;
        L30:
            if (SelectAnimatedEmojiDialog.m18664$$Nest$fgetpositionToButton(this.this$0).indexOfKey(r3) < 0) goto L34;
            return 5;
        L34:
            if (r3 != SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0)) goto L38;
            return 6;
        L38:
            if (SelectAnimatedEmojiDialog.m18666$$Nest$fgetpositionToSection(this.this$0).indexOfKey(r3) < 0) goto L40;
            return 0;
        L40:
            if (r3 != SelectAnimatedEmojiDialog.m18673$$Nest$fgetrecentReactionsSectionRow(this.this$0)) goto L42;
            return 0;
        L42:
            if (r3 != SelectAnimatedEmojiDialog.m18696$$Nest$fgetstickersSectionRow(this.this$0)) goto L44;
            return 0;
        L44:
            if (r3 != SelectAnimatedEmojiDialog.m18651$$Nest$fgetgiftsSectionRow(this.this$0)) goto L46;
            return 0;
        L46:
            if (r3 != SelectAnimatedEmojiDialog.m18663$$Nest$fgetpopularSectionRow(this.this$0)) goto L48;
            return 0;
        L48:
            if (r3 != SelectAnimatedEmojiDialog.m18702$$Nest$fgettopicEmojiHeaderRow(this.this$0)) goto L51;
            return 0;
        L51:
            if (r3 != SelectAnimatedEmojiDialog.m18644$$Nest$fgetdefaultTopicIconRow(this.this$0)) goto L54;
            return 8;
        L54:
            return 3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r1 = r2.getItemViewType();
            if (r1 == 2) goto L12;
            if (r1 == 1) goto L12;
            if (r1 == 3) goto L12;
            if (r1 == 8) goto L12;
            return false;
        L12:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r29, int r30) {
            int r3 = r29.getItemViewType();
            if (r3 != 8) goto L7;
            ImageViewEmoji r1 = (ImageViewEmoji) r29.itemView;
            r1.position = r30;
            r1.selected = this.this$0.selectedDocumentIds.contains(0L);
            return;
        L7:
            if (SelectAnimatedEmojiDialog.m18689$$Nest$fgetshowAnimator(this.this$0) != null) goto L9;
        L10:
            r29.itemView.setScaleX(1.0f);
            r29.itemView.setScaleY(1.0f);
        L12:
            if (r3 != 6) goto L17;
            TextView r12 = (TextView) r29.itemView;
            if (SelectAnimatedEmojiDialog.m18653$$Nest$fgethintExpireDate(this.this$0) == null) goto L162;
            r12.setText(LocaleController.formatString("EmojiStatusExpireHint", R.string.EmojiStatusExpireHint, new Object[]{LocaleController.formatStatusExpireDateTime(SelectAnimatedEmojiDialog.m18653$$Nest$fgethintExpireDate(this.this$0).intValue())}));
            return;
        L162:
            return;
        L17:
            boolean r10 = true;
            boolean r102 = true;
            boolean r103 = true;
            int r104 = 1;
            r10 = true;
            r10 = true;
            r10 = true;
            r10 = true;
            boolean r11 = false;
            if (r3 != 0) goto L62;
            HeaderView r13 = (HeaderView) r29.itemView;
            if (r30 != SelectAnimatedEmojiDialog.m18702$$Nest$fgettopicEmojiHeaderRow(this.this$0)) goto L24;
            r13.setText(LocaleController.getString(R.string.SelectTopicIconHint), false);
            r13.closeIcon.setVisibility(8);
            return;
        L24:
            if (r30 != SelectAnimatedEmojiDialog.m18673$$Nest$fgetrecentReactionsSectionRow(this.this$0)) goto L28;
            r13.setText(LocaleController.getString(R.string.RecentlyUsed), false);
            r13.closeIcon.setVisibility(8);
            return;
        L28:
            if (r30 != SelectAnimatedEmojiDialog.m18696$$Nest$fgetstickersSectionRow(this.this$0)) goto L31;
            r13.setText(LocaleController.getString(R.string.StickerEffects), false);
            r13.closeIcon.setVisibility(8);
            return;
        L31:
            r13.closeIcon.setVisibility(8);
            if (r30 != SelectAnimatedEmojiDialog.m18663$$Nest$fgetpopularSectionRow(this.this$0)) goto L36;
            r13.setText(LocaleController.getString(R.string.PopularReactions), false);
            return;
        L36:
            if (r30 != SelectAnimatedEmojiDialog.m18651$$Nest$fgetgiftsSectionRow(this.this$0)) goto L39;
            r13.setText(LocaleController.getString(R.string.EmojiPackCollectibles), false);
            return;
        L39:
            int r2 = SelectAnimatedEmojiDialog.m18666$$Nest$fgetpositionToSection(this.this$0).get(r30);
            if (r2 < 0) goto L59;
            EmojiView.EmojiPack r22 = (EmojiView.EmojiPack) SelectAnimatedEmojiDialog.m18662$$Nest$fgetpacks(this.this$0).get(r2);
            if (r22.needLoadSet == null) goto L45;
            MediaDataController.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).getStickerSet(r22.needLoadSet, false);
            r22.needLoadSet = null;
        L45:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 5) goto L47;
        L57:
            r13.setText(r22.set.title, r11);
            return;
        L47:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 7) goto L57;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 6) goto L57;
            if (r22.free == false) goto L53;
        L55:
            r102 = false;
        L56:
            r11 = r102;
            goto L57
        L53:
            if (UserConfig.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).isPremium() == true) goto L55;
        L59:
            r13.setText(null, false);
            return;
        L62:
            if (r3 != 1) goto L120;
            ImageViewEmoji r14 = (ImageViewEmoji) r29.itemView;
            r14.position = r30;
            if (r30 < SelectAnimatedEmojiDialog.m18674$$Nest$fgetrecentReactionsStartRow(this.this$0)) goto L69;
            if (r30 >= SelectAnimatedEmojiDialog.m18672$$Nest$fgetrecentReactionsEndRow(this.this$0)) goto L69;
            ReactionsLayoutInBubble.VisibleReaction r23 = (ReactionsLayoutInBubble.VisibleReaction) SelectAnimatedEmojiDialog.m18671$$Nest$fgetrecentReactions(this.this$0).get(r30 - SelectAnimatedEmojiDialog.m18674$$Nest$fgetrecentReactionsStartRow(this.this$0));
        L75:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 13) goto L78;
            r14.notDraw = false;
            r14.isFirstReactions = true;
            r14.reaction = r23;
            r14.setDrawable(Emoji.getEmojiDrawable(r23.emojicon));
            r14.setViewSelected(this.this$0.selectedReactions.contains(r23), false);
            return;
        L78:
            r14.createImageReceiver(this.this$0.emojiGridView);
            r14.isFirstReactions = true;
            r14.reaction = r23;
            r14.setViewSelected(this.this$0.selectedReactions.contains(r23), false);
            r14.notDraw = false;
            if (r23.isEffect == false) goto L81;
        L93:
            r14.isDefaultReaction = false;
            r14.span = new AnimatedEmojiSpan(r23.documentId, null);
            r14.document = null;
            r14.imageReceiver.clearImage();
            r14.preloadEffectImageReceiver.clearImage();
            Drawable r32 = (Drawable) EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiGridView).get(r14.span.getDocumentId());
            if (r32 != null) goto L101;
            int r33 = SelectAnimatedEmojiDialog.m18715$$Nest$mgetCacheType(this.this$0);
            if (r33 == 3) goto L98;
        L100:
            r32 = AnimatedEmojiDrawable.make(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0), r33, r14.span.getDocumentId());
            EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiGridView).put(r14.span.getDocumentId(), r32);
            goto L101
        L98:
            if (r23.sticker == false) goto L100;
            r33 = 27;
        L101:
            r14.setDrawable(r32);
        L103:
            if (UserConfig.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).isPremium() == true) goto L113;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 14) goto L113;
            if (r23.isEffect == false) goto L113;
            if (r23.premium == false) goto L113;
            r14.createPremiumLockView();
            r14.premiumLockIconView.setVisibility(0);
            r14.setEmojicon(null);
            return;
        L113:
            if (r23.sticker == false) goto L115;
            r14.setEmojicon(r23.emojicon);
        L116:
            PremiumLockIconView r0 = r14.premiumLockIconView;
            if (r0 == null) goto L389;
            r0.setVisibility(4);
            return;
        L389:
            return;
        L115:
            r14.setEmojicon(null);
            goto L116
        L81:
            if (r23.emojicon == null) goto L93;
            r14.isDefaultReaction = true;
            TLRPC.TL_availableReaction r34 = MediaDataController.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).getReactionsMap().get(r23.emojicon);
            if (r34 == null) goto L89;
            SvgHelper.SvgDrawable r20 = DocumentObject.getSvgThumb(r34.activate_animation, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
            boolean r5 = LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
            ImageReceiver r15 = r14.imageReceiver;
            if (r5 == true) goto L87;
            r15.setImage(ImageLocation.getForDocument(r34.select_animation), "60_60_firstframe", null, null, r20, 0, "tgs", r23, 0);
        L88:
            MediaDataController.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).preloadImage(r14.preloadEffectImageReceiver, ImageLocation.getForDocument(r34.around_animation), ReactionsEffectOverlay.getFilterForAroundAnimation());
        L90:
            r14.span = null;
            r14.document = null;
            r14.setDrawable(null);
            PremiumLockIconView r35 = r14.premiumLockIconView;
            if (r35 == null) goto L103;
            r35.setVisibility(8);
            r14.premiumLockIconView.setImageReceiver(null);
            goto L103
        L87:
            ReactionsLayoutInBubble.VisibleReaction r24 = r23;
            r15.setImage(ImageLocation.getForDocument(r34.select_animation), "60_60_pcache", ImageLocation.getForDocument(r34.select_animation), "30_30_firstframe", null, null, r20, 0, "tgs", r24, 0);
            r23 = r24;
            goto L88
        L89:
            r14.imageReceiver.clearImage();
            r14.preloadEffectImageReceiver.clearImage();
        L69:
            if (r30 >= SelectAnimatedEmojiDialog.m18697$$Nest$fgetstickersStartRow(this.this$0)) goto L71;
        L73:
            r23 = (ReactionsLayoutInBubble.VisibleReaction) SelectAnimatedEmojiDialog.m18699$$Nest$fgettopReactions(this.this$0).get(r30 - SelectAnimatedEmojiDialog.m18701$$Nest$fgettopReactionsStartRow(this.this$0));
            goto L75
        L71:
            if (r30 >= SelectAnimatedEmojiDialog.m18694$$Nest$fgetstickersEndRow(this.this$0)) goto L73;
            r23 = (ReactionsLayoutInBubble.VisibleReaction) SelectAnimatedEmojiDialog.m18693$$Nest$fgetstickers(this.this$0).get(r30 - SelectAnimatedEmojiDialog.m18697$$Nest$fgetstickersStartRow(this.this$0));
            goto L75
        L120:
            int r4 = 40;
            if (r3 != 4) goto L143;
            EmojiPackExpand r16 = (EmojiPackExpand) r29.itemView;
            int r25 = SelectAnimatedEmojiDialog.m18665$$Nest$fgetpositionToExpand(this.this$0).get(r30);
            if (r25 >= 0) goto L125;
        L127:
            EmojiView.EmojiPack r36 = null;
        L129:
            if (r25 != (-1)) goto L132;
            SelectAnimatedEmojiDialog.m18708$$Nest$fputrecentExpandButton(this.this$0, r16);
            r16.textView.setText("+" + (((SelectAnimatedEmojiDialog.m18668$$Nest$fgetrecent(this.this$0).size() - 40) + (SelectAnimatedEmojiDialog.m18654$$Nest$fgetincludeEmpty(this.this$0) ? 1 : 0)) + 1));
            return;
        L132:
            SelectAnimatedEmojiDialog r26 = this.this$0;
            if (r36 == null) goto L140;
            if (SelectAnimatedEmojiDialog.m18669$$Nest$fgetrecentExpandButton(r26) != r16) goto L137;
            SelectAnimatedEmojiDialog.m18708$$Nest$fputrecentExpandButton(this.this$0, null);
        L137:
            TextView r02 = r16.textView;
            StringBuilder r17 = new StringBuilder("+");
            r17.append(r36.documents.size() - 23);
            r02.setText(r17.toString());
            return;
        L140:
            if (SelectAnimatedEmojiDialog.m18669$$Nest$fgetrecentExpandButton(r26) != r16) goto L390;
            SelectAnimatedEmojiDialog.m18708$$Nest$fputrecentExpandButton(this.this$0, null);
            return;
        L390:
            return;
        L125:
            if (r25 >= SelectAnimatedEmojiDialog.m18662$$Nest$fgetpacks(this.this$0).size()) goto L127;
            r36 = (EmojiView.EmojiPack) SelectAnimatedEmojiDialog.m18662$$Nest$fgetpacks(this.this$0).get(r25);
            goto L129
        L143:
            if (r3 != 5) goto L158;
            EmojiPackButton r18 = (EmojiPackButton) r29.itemView;
            int r27 = SelectAnimatedEmojiDialog.m18664$$Nest$fgetpositionToButton(this.this$0).get(r30);
            if (r27 >= 0) goto L147;
            return;
        L147:
            if (r27 >= SelectAnimatedEmojiDialog.m18662$$Nest$fgetpacks(this.this$0).size()) goto L392;
            EmojiView.EmojiPack r37 = (EmojiView.EmojiPack) SelectAnimatedEmojiDialog.m18662$$Nest$fgetpacks(this.this$0).get(r27);
            if (r37 == null) goto L393;
            String r42 = r37.set.title;
            if (r37.free == false) goto L153;
        L155:
            r103 = false;
        L156:
            r18.set(r42, r103, r37.installed, new SelectAnimatedEmojiDialog$Adapter$$ExternalSyntheticLambda0(this, r37, r27));
            return;
        L153:
            if (UserConfig.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).isPremium() == true) goto L155;
        L393:
            return;
        L392:
            return;
        L158:
            if (r3 != 7) goto L161;
            return;
        L161:
            if (r3 == 9) goto L394;
            ImageViewEmoji r19 = (ImageViewEmoji) r29.itemView;
            r19.starGift = null;
            r19.particlesColor = null;
            r19.empty = false;
            r19.position = r30;
            r19.setPadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 4) goto L168;
            if (SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(this.this$0) == false) goto L168;
        L169:
            int r38 = SelectAnimatedEmojiDialog.m18675$$Nest$fgetrecentStickers(this.this$0).size();
        L184:
            if (SelectAnimatedEmojiDialog.m18654$$Nest$fgetincludeEmpty(this.this$0) == false) goto L199;
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L188;
            int r43 = 1;
        L190:
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) == (-1)) goto L192;
            int r6 = 1;
        L194:
            if (r30 != (r43 + r6)) goto L199;
            boolean r28 = this.this$0.selectedDocumentIds.contains(null);
            r19.empty = true;
            r19.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f));
            r19.span = null;
            r19.document = null;
            r19.isStaticIcon = false;
            ImageReceiver r39 = r19.imageReceiver;
            if (r39 == null) goto L371;
            r39.clearImage();
        L371:
            if (r19.span == null) goto L380;
            AnimatedEmojiDrawable r310 = (AnimatedEmojiDrawable) EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiGridView).get(r19.span.getDocumentId());
            if (r310 != null) goto L379;
            TLRPC.Document r311 = r19.span.document;
            SelectAnimatedEmojiDialog r44 = this.this$0;
            if (r311 == null) goto L377;
            r310 = AnimatedEmojiDrawable.make(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(r44), SelectAnimatedEmojiDialog.m18715$$Nest$mgetCacheType(this.this$0), r19.span.document);
        L378:
            EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiGridView).put(r19.span.getDocumentId(), r310);
            goto L379
        L377:
            r310 = AnimatedEmojiDrawable.make(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(r44), SelectAnimatedEmojiDialog.m18715$$Nest$mgetCacheType(this.this$0), r19.span.getDocumentId());
        L379:
            r19.setDrawable(r310);
        L381:
            r19.setViewSelected(r28, false);
            return;
        L380:
            r19.setDrawable(null);
            goto L381
        L192:
            r6 = 0;
            goto L194
        L188:
            r43 = 0;
        L199:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 13) goto L221;
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L203;
            int r45 = 1;
        L204:
            int r46 = r30 - r45;
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) == (-1)) goto L207;
            int r62 = 1;
        L209:
            if ((r46 - r62) >= SelectAnimatedEmojiDialog.m18692$$Nest$fgetstandardEmojis(this.this$0).size()) goto L221;
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L213;
            int r312 = 1;
        L214:
            int r210 = r30 - r312;
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) != (-1)) goto L218;
            r104 = 0;
        L218:
            String r211 = (String) SelectAnimatedEmojiDialog.m18692$$Nest$fgetstandardEmojis(this.this$0).get((r210 - r104) - (SelectAnimatedEmojiDialog.m18654$$Nest$fgetincludeEmpty(this.this$0) ? 1 : 0));
            r19.notDraw = false;
            r19.isFirstReactions = false;
            r19.reaction = ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r211);
            r19.setDrawable(Emoji.getEmojiDrawable(r211));
            r19.setViewSelected(this.this$0.selectedReactions.contains(r19.reaction), false);
            return;
        L213:
            r312 = 0;
            goto L214
        L207:
            r62 = 0;
            goto L209
        L203:
            r45 = 0;
        L221:
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L223;
            int r47 = 1;
        L224:
            int r48 = r30 - r47;
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) == (-1)) goto L227;
            int r63 = 1;
        L228:
            int r49 = r48 - r63;
            SelectAnimatedEmojiDialog r64 = this.this$0;
            if (r49 >= r38) goto L265;
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(r64) == (-1)) goto L233;
            int r313 = 1;
        L234:
            int r212 = r30 - r313;
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) == (-1)) goto L237;
            int r314 = 1;
        L238:
            int r213 = (r212 - r314) - (SelectAnimatedEmojiDialog.m18654$$Nest$fgetincludeEmpty(this.this$0) ? 1 : 0);
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 4) goto L241;
        L243:
            int r315 = SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0);
            SelectAnimatedEmojiDialog r410 = this.this$0;
            if (r315 != 6) goto L251;
            TLRPC.Document r214 = (TLRPC.Document) SelectAnimatedEmojiDialog.m18675$$Nest$fgetrecentStickers(r410).get(r213);
            r19.setSticker(r214, this.this$0.emojiGridView);
            if (r214 != null) goto L248;
        L250:
            r10 = false;
        L282:
            r28 = r10;
            goto L371
        L248:
            if (this.this$0.selectedDocumentIds.contains(Long.valueOf(r214.id)) == false) goto L250;
        L251:
            AnimatedEmojiSpan r215 = (AnimatedEmojiSpan) SelectAnimatedEmojiDialog.m18668$$Nest$fgetrecent(r410).get(r213);
            r19.span = r215;
            if (r215 != null) goto L254;
            TLRPC.Document r316 = null;
        L255:
            r19.document = r316;
            if (r215 != null) goto L258;
        L260:
            r10 = false;
        L261:
            r19.isStaticIcon = false;
            ImageReceiver r216 = r19.imageReceiver;
            if (r216 == null) goto L282;
            r216.clearImage();
            goto L282
        L258:
            if (this.this$0.selectedDocumentIds.contains(Long.valueOf(r215.getDocumentId())) == false) goto L260;
        L254:
            r316 = r215.document;
            goto L255
        L241:
            if (SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(this.this$0) == false) goto L243;
            r19.setSticker((TLRPC.Document) SelectAnimatedEmojiDialog.m18675$$Nest$fgetrecentStickers(this.this$0).get(r213), this.this$0.emojiGridView);
            r28 = false;
            goto L371
        L237:
            r314 = 0;
            goto L238
        L233:
            r313 = 0;
            goto L234
        L265:
            if (SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(r64).isEmpty() == true) goto L284;
            if ((r30 - SelectAnimatedEmojiDialog.m18652$$Nest$fgetgiftsStartRow(this.this$0)) < 0) goto L284;
            if ((r30 - SelectAnimatedEmojiDialog.m18652$$Nest$fgetgiftsStartRow(this.this$0)) >= SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).size()) goto L284;
            TL_stars.TL_starGiftUnique r217 = (TL_stars.TL_starGiftUnique) SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).get(r30 - SelectAnimatedEmojiDialog.m18652$$Nest$fgetgiftsStartRow(this.this$0));
            TLRPC.Document r317 = r217.getDocument();
            r19.span = new AnimatedEmojiSpan(r317, null);
            r19.document = r317;
            r19.starGift = r217;
            TL_stars.starGiftAttributeBackdrop r318 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r217.attributes, TL_stars.starGiftAttributeBackdrop.class);
            if (r318 == null) goto L274;
            r19.particlesColor = Integer.valueOf(r318.pattern_color | (-1879048192));
        L274:
            if (r19.span != null) goto L276;
        L278:
            r10 = false;
        L279:
            r19.isStaticIcon = false;
            ImageReceiver r218 = r19.imageReceiver;
            if (r218 == null) goto L282;
            r218.clearImage();
            goto L282
        L276:
            if (this.this$0.selectedDocumentIds.contains(Long.valueOf(r217.id)) == false) goto L278;
        L284:
            if (SelectAnimatedEmojiDialog.m18643$$Nest$fgetdefaultStatuses(this.this$0).isEmpty() == false) goto L286;
        L338:
            int r319 = 0;
        L340:
            if (r319 >= SelectAnimatedEmojiDialog.m18666$$Nest$fgetpositionToSection(this.this$0).size()) goto L365;
            int r411 = SelectAnimatedEmojiDialog.m18666$$Nest$fgetpositionToSection(this.this$0).keyAt(r319);
            int r52 = SelectAnimatedEmojiDialog.m18666$$Nest$fgetpositionToSection(this.this$0).valueAt(r319);
            if (r52 < 0) goto L344;
            EmojiView.EmojiPack r53 = (EmojiView.EmojiPack) SelectAnimatedEmojiDialog.m18662$$Nest$fgetpacks(this.this$0).get(r52);
        L345:
            if (r53 == null) goto L364;
            boolean r65 = r53.expanded;
            ArrayList<TLRPC.Document> r7 = r53.documents;
            if (r65 == false) goto L350;
            int r66 = r7.size();
        L351:
            int r412 = (r30 - r411) - 1;
            if (r412 < 0) goto L364;
            if (r412 >= r66) goto L364;
            TLRPC.Document r413 = r53.documents.get(r412);
            if (r413 == null) goto L364;
            if (SelectAnimatedEmojiDialog.m18690$$Nest$fgetshowStickers(this.this$0) == false) goto L359;
            r19.setSticker(r413, this.this$0.emojiSearchGridView);
        L363:
            r19.document = r413;
            goto L364
        L359:
            r19.isStaticIcon = false;
            ImageReceiver r54 = r19.imageReceiver;
            if (r54 == null) goto L362;
            r54.clearImage();
        L362:
            r19.span = new AnimatedEmojiSpan(r413, null);
            goto L363
        L350:
            r66 = Math.min(r7.size(), 24);
        L364:
            r319 = r319 + 1;
            goto L340
        L344:
            r53 = null;
            goto L345
        L365:
            AnimatedEmojiSpan r219 = r19.span;
            if (r219 == null) goto L250;
            if (this.this$0.selectedDocumentIds.contains(Long.valueOf(r219.getDocumentId())) == false) goto L250;
        L286:
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L288;
            int r414 = 1;
        L289:
            int r415 = r30 - r414;
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) == (-1)) goto L292;
            int r55 = 1;
        L293:
            int r416 = ((r415 - r55) - r38) - 1;
            if (SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).isEmpty() == false) goto L296;
            int r56 = 0;
        L298:
            if ((r416 - r56) < 0) goto L338;
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L302;
            int r417 = 1;
        L303:
            int r418 = r30 - r417;
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) == (-1)) goto L306;
            int r57 = 1;
        L307:
            int r419 = ((r418 - r57) - r38) - 1;
            if (SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).isEmpty() == false) goto L310;
            int r58 = 0;
        L312:
            if ((r419 - r58) >= SelectAnimatedEmojiDialog.m18643$$Nest$fgetdefaultStatuses(this.this$0).size()) goto L338;
            if (SelectAnimatedEmojiDialog.m18685$$Nest$fgetsearchRow(this.this$0) == (-1)) goto L316;
            int r420 = 1;
        L317:
            int r220 = r30 - r420;
            if (SelectAnimatedEmojiDialog.m18660$$Nest$fgetlongtapHintRow(this.this$0) == (-1)) goto L320;
            int r421 = 1;
        L321:
            int r221 = ((r220 - r421) - r38) - 1;
            if (SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).isEmpty() == false) goto L324;
            int r320 = 0;
        L325:
            AnimatedEmojiSpan r222 = (AnimatedEmojiSpan) SelectAnimatedEmojiDialog.m18643$$Nest$fgetdefaultStatuses(this.this$0).get(r221 - r320);
            r19.span = r222;
            if (r222 != null) goto L328;
            TLRPC.Document r321 = null;
        L329:
            r19.document = r321;
            if (r222 != null) goto L332;
        L334:
            r10 = false;
        L335:
            r19.isStaticIcon = false;
            ImageReceiver r223 = r19.imageReceiver;
            if (r223 == null) goto L282;
            r223.clearImage();
            goto L282
        L332:
            if (this.this$0.selectedDocumentIds.contains(Long.valueOf(r222.getDocumentId())) == false) goto L334;
        L328:
            r321 = r222.document;
            goto L329
        L324:
            r320 = SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).size() + 1;
            goto L325
        L320:
            r421 = 0;
            goto L321
        L316:
            r420 = 0;
            goto L317
        L310:
            r58 = SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).size() + 1;
            goto L312
        L306:
            r57 = 0;
            goto L307
        L302:
            r417 = 0;
            goto L303
        L296:
            r56 = SelectAnimatedEmojiDialog.m18649$$Nest$fgetgifts(this.this$0).size() + 1;
            goto L298
        L292:
            r55 = 0;
            goto L293
        L288:
            r414 = 0;
            goto L289
        L227:
            r63 = 0;
            goto L228
        L223:
            r47 = 0;
        L168:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 6) goto L169;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 4) goto L173;
        L182:
            r38 = SelectAnimatedEmojiDialog.m18668$$Nest$fgetrecent(this.this$0).size();
            goto L184
        L173:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 3) goto L182;
            if (SelectAnimatedEmojiDialog.m18668$$Nest$fgetrecent(this.this$0).size() > 40) goto L178;
        L180:
            r4 = (SelectAnimatedEmojiDialog.m18654$$Nest$fgetincludeEmpty(this.this$0) ? 1 : 0) + SelectAnimatedEmojiDialog.m18668$$Nest$fgetrecent(this.this$0).size();
        L181:
            r38 = r4;
            goto L184
        L178:
            if (SelectAnimatedEmojiDialog.m18670$$Nest$fgetrecentExpanded(this.this$0) == true) goto L180;
        L394:
            return;
        L9:
            if (SelectAnimatedEmojiDialog.m18689$$Nest$fgetshowAnimator(this.this$0).isRunning() == true) goto L12;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r5, int r6) {
            boolean r0 = true;
            if (r6 != 0) goto L10;
            SelectAnimatedEmojiDialog r1 = this.this$0;
            Context r2 = r1.getContext();
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 6) goto L8;
            r0 = false;
        L8:
            View r62 = new HeaderView(r1, r2, r0);
        L48:
            if (SelectAnimatedEmojiDialog.m18714$$Nest$menterAnimationInProgress(this.this$0) == false) goto L51;
            r62.setScaleX(0.0f);
            r62.setScaleY(0.0f);
        L51:
            return new RecyclerListView.Holder(r62);
        L10:
            if (r6 != 2) goto L13;
            r62 = new ImageView(this.this$0.getContext());
            goto L48
        L13:
            if (r6 == 3) goto L43;
            if (r6 == 1) goto L43;
            if (r6 == 8) goto L43;
            if (r6 != 4) goto L21;
            SelectAnimatedEmojiDialog r52 = this.this$0;
            r62 = new EmojiPackExpand(r52, r52.getContext(), null);
            goto L48
        L21:
            if (r6 != 5) goto L23;
            SelectAnimatedEmojiDialog r53 = this.this$0;
            r62 = new EmojiPackButton(r53, r53.getContext());
            goto L48
        L23:
            if (r6 != 6) goto L40;
            AnonymousClass1 r63 = new AnonymousClass1(this, this.this$0.getContext());
            r63.setTextSize(1, 13.0f);
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 3) goto L28;
            r63.setText(LocaleController.getString(R.string.SelectTopicIconHint));
        L38:
            r63.setGravity(17);
            r63.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(this.this$0)));
            r62 = r63;
            goto L48
        L28:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 0) goto L30;
        L37:
            r63.setText(LocaleController.getString(R.string.EmojiLongtapHint));
            goto L38
        L30:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 12) goto L37;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 9) goto L37;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 10) goto L37;
            r63.setText(LocaleController.getString(R.string.ReactionsLongtapHint));
            goto L38
        L40:
            if (r6 != 7) goto L42;
            View r64 = new FixedHeightEmptyCell(this.this$0.getContext(), 52);
            r64.setTag("searchbox");
            r62 = r64;
            goto L48
        L42:
            SelectAnimatedEmojiDialog r54 = this.this$0;
            r62 = new ImageViewEmoji(r54, r54.getContext());
        L43:
            SelectAnimatedEmojiDialog r22 = this.this$0;
            ImageViewEmoji r55 = new ImageViewEmoji(r22, r22.getContext());
            if (r6 != 8) goto L46;
            r55.isStaticIcon = true;
            ImageReceiver r65 = new ImageReceiver(r55);
            r55.imageReceiver = r65;
            r55.imageReceiverToDraw = r65;
            r65.setImageBitmap(SelectAnimatedEmojiDialog.m18648$$Nest$fgetforumIconDrawable(this.this$0));
            SelectAnimatedEmojiDialog.m18706$$Nest$fputforumIconImage(this.this$0, r55);
            r55.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        L46:
            r62 = r55;
            goto L48
        }
    }

    /* loaded from: classes6.dex */
    public interface BackgroundDelegate {
        void drawRect(Canvas r1, int r2, int r3, int r4, int r5, float r6, float r7);
    }

    /* loaded from: classes6.dex */
    public class EmojiListView extends RecyclerListView {
        private LongSparseArray<AnimatedEmojiDrawable> animatedEmojiDrawables;
        private boolean invalidated;
        private int lastChildCount;
        ArrayList<DrawingInBackgroundLine> lineDrawables;
        ArrayList<DrawingInBackgroundLine> lineDrawablesTmp;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        ArrayList<ArrayList<ImageViewEmoji>> unusedArrays;
        ArrayList<DrawingInBackgroundLine> unusedLineDrawables;
        SparseArray<ArrayList<ImageViewEmoji>> viewsGroupedByLines;

        public class DrawingInBackgroundLine extends DrawingInBackgroundThreadDrawable {
            private OvershootInterpolator appearScaleInterpolator;
            ArrayList<ImageViewEmoji> drawInBackgroundViews;
            ArrayList<ImageViewEmoji> imageViewEmojis;
            boolean lite;
            public int position;
            float skewAlpha;
            boolean skewBelow;
            public int startOffset;
            final /* synthetic */ EmojiListView this$1;

            public DrawingInBackgroundLine(EmojiListView r2) {
                this.this$1 = r2;
                this.drawInBackgroundViews = new ArrayList();
                this.skewAlpha = 1.0f;
                this.skewBelow = false;
                this.lite = LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
                this.appearScaleInterpolator = new OvershootInterpolator(3.0f);
            }

            private void drawImage(Canvas r3, Drawable r4, ImageViewEmoji r5, float r6) {
                if (r4 == null) goto L6;
                r4.setAlpha((int) (r6 * 255.0f));
                r4.setColorFilter(SelectAnimatedEmojiDialog.m18667$$Nest$fgetpremiumStarColorFilter(this.this$1.this$0));
                r4.draw(r3);
                return;
            L6:
                if (r5.isDefaultReaction == true) goto L10;
                if (r5.isStaticIcon == true) goto L10;
                return;
            L10:
                if (r5.imageReceiver == null) goto L14;
                r3.save();
                r3.clipRect(r5.imageReceiver.getImageX(), r5.imageReceiver.getImageY(), r5.imageReceiver.getImageX2(), r5.imageReceiver.getImageY2());
                r5.imageReceiver.setAlpha(r6);
                r5.imageReceiver.draw(r3);
                r3.restore();
                return;
            }

            private void skew(Canvas r6, int r7, int r8) {
                float r0 = this.skewAlpha;
                if (r0 < 1.0f) goto L5;
                return;
            L5:
                if (this.skewBelow == false) goto L8;
                r6.translate(0.0f, r8);
                r6.skew((1.0f - ((r7 * 2.0f) / this.imageViewEmojis.size())) * (-(1.0f - this.skewAlpha)), 0.0f);
                r6.translate(0.0f, -r8);
                return;
            L8:
                r6.scale(1.0f, r0, 0.0f, 0.0f);
                r6.skew((1.0f - ((r7 * 2.0f) / this.imageViewEmojis.size())) * (1.0f - this.skewAlpha), 0.0f);
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void draw(Canvas r12, long r13, int r15, int r16, float r17) {
                ArrayList<ImageViewEmoji> r1 = this.imageViewEmojis;
                if (r1 != null) goto L5;
                return;
            L5:
                float r2 = 1.0f;
                this.skewAlpha = 1.0f;
                int r3 = 0;
                this.skewBelow = false;
                if (r1.isEmpty() == true) goto L10;
                ImageViewEmoji r14 = this.imageViewEmojis.get(0);
                if (r14.getY() <= ((this.this$1.getHeight() - this.this$1.getPaddingBottom()) - r14.getHeight())) goto L10;
                this.skewAlpha = (MathUtils.clamp((-((r14.getY() - this.this$1.getHeight()) + this.this$1.getPaddingBottom())) / r14.getHeight(), 0.0f, 1.0f) * 0.75f) + 0.25f;
            L10:
                boolean r6 = true;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) != 13) goto L13;
            L28:
                boolean r18 = true;
            L29:
                if (r18 == false) goto L31;
            L59:
                r6 = r18;
            L61:
                if (HwEmojis.isHwEnabled() == true) goto L64;
                r2 = r17;
            L64:
                if (r6 == false) goto L66;
            L67:
                float r62 = r2;
                prepareDraw(System.currentTimeMillis());
                drawInUiThread(r12, r62);
                reset();
                return;
            L66:
                if (HwEmojis.isPreparing() == true) goto L67;
                super.draw(r12, r13, r15, r16, r2);
                return;
            L31:
                if (SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) > 0) goto L33;
            L35:
                boolean r5 = false;
            L37:
                if (r3 >= this.imageViewEmojis.size()) goto L59;
                ImageViewEmoji r7 = this.imageViewEmojis.get(r3);
                if (ImageViewEmoji.m18726$$Nest$fgetpressedProgress(r7) != 0.0f) goto L61;
                if (ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r7) != 0.0f) goto L61;
                if (r7.backAnimator != null) goto L61;
                if (r7.getTranslationX() != 0.0f) goto L61;
                if (r7.getTranslationY() != 0.0f) goto L61;
                if (r7.getAlpha() != 1.0f) goto L61;
                if (r5 == false) goto L56;
                if (r7.position <= SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0)) goto L56;
                if (r7.position < SelectAnimatedEmojiDialog.m18633$$Nest$fgetanimateExpandToPosition(this.this$1.this$0)) goto L61;
            L56:
                if (r7.isStaticIcon == true) goto L61;
                r3 = r3 + 1;
                goto L37
            L33:
                if ((SystemClock.elapsedRealtime() - SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) >= this.this$1.this$0.animateExpandDuration()) goto L35;
                r5 = true;
                goto L37
            L13:
                if (this.skewAlpha < 1.0f) goto L28;
                if (this.this$1.isAnimating() == true) goto L28;
                if (this.imageViewEmojis.size() <= 4) goto L28;
                if (this.lite == false) goto L28;
                if (SelectAnimatedEmojiDialog.m18714$$Nest$menterAnimationInProgress(this.this$1.this$0) == true) goto L28;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 4) goto L28;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 6) goto L28;
                r18 = false;
                goto L29
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawBitmap(Canvas r1, Bitmap r2, Paint r3) {
                r1.drawBitmap(r2, 0.0f, 0.0f, r3);
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInBackground(Canvas r5) {
                int r0 = 0;
            L4:
                if (r0 >= this.drawInBackgroundViews.size()) goto L14;
                ImageViewEmoji r1 = this.drawInBackgroundViews.get(r0);
                if (r1.notDraw == true) goto L13;
                if (r1.empty == false) goto L10;
                r1.drawable.setBounds(r1.drawableBounds);
                r1.drawable.draw(r5);
                goto L13
            L10:
                ImageReceiver r2 = r1.imageReceiverToDraw;
                if (r2 == null) goto L13;
                r2.draw(r5, r1.backgroundThreadDrawHolder[this.threadIndex]);
            L13:
                r0 = r0 + 1;
                goto L4
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void drawInUiThread(Canvas r20, float r21) {
                if (this.imageViewEmojis == null) goto L125;
                r20.save();
                r20.translate(-this.startOffset, 0.0f);
                float r4 = r21;
                int r5 = 0;
            L6:
                if (r5 >= this.imageViewEmojis.size()) goto L116;
                ImageViewEmoji r6 = this.imageViewEmojis.get(r5);
                if (r6.notDraw == true) goto L115;
                float r7 = r6.getScaleX();
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) != 13) goto L14;
                r7 = r7 * 0.87f;
            L14:
                if (ImageViewEmoji.m18726$$Nest$fgetpressedProgress(r6) != 0.0f) goto L22;
                if (ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r6) <= 0.0f) goto L29;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 3) goto L29;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) != 4) goto L22;
            L29:
                if (SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) > 0) goto L31;
            L33:
                boolean r8 = false;
            L34:
                if (r8 == true) goto L36;
            L45:
                r4 = r4 * r6.getAlpha();
            L46:
                Rect r10 = AndroidUtilities.rectTmp2;
                r10.set(((int) r6.getX()) + r6.getPaddingLeft(), r6.getPaddingTop(), (((int) r6.getX()) + r6.getWidth()) - r6.getPaddingRight(), r6.getHeight() - r6.getPaddingBottom());
                if (SelectAnimatedEmojiDialog.m18691$$Nest$fgetsmoothScrolling(this.this$1.this$0) == true) goto L51;
                if (r8 == true) goto L51;
                r10.offset(0, (int) r6.getTranslationY());
            L51:
                if (r6.empty == false) goto L67;
                Drawable r82 = SelectAnimatedEmojiDialog.m18716$$Nest$mgetPremiumStar(this.this$1.this$0);
                SelectAnimatedEmojiDialog r13 = this.this$1.this$0;
                if (r13.badgePicker == false) goto L55;
            L64:
                r10.inset((int) ((-r10.width()) * 0.15f), (int) ((-r10.height()) * 0.15f));
            L65:
                r82.setBounds(r10);
                r82.setAlpha(255);
            L86:
                if (SelectAnimatedEmojiDialog.m18667$$Nest$fgetpremiumStarColorFilter(this.this$1.this$0) == null) goto L90;
                Drawable r11 = r6.drawable;
                if ((r11 instanceof AnimatedEmojiDrawable) == false) goto L90;
                r11.setColorFilter(SelectAnimatedEmojiDialog.m18667$$Nest$fgetpremiumStarColorFilter(this.this$1.this$0));
            L90:
                float r112 = this.skewAlpha;
                r6.skewAlpha = r112;
                r6.skewIndex = r5;
                if (r7 == 1.0f) goto L93;
            L96:
                r20.save();
                if (ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r6) <= 1.0f) goto L106;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 3) goto L106;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 4) goto L106;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 6) goto L106;
                float r113 = AndroidUtilities.lerp(1.0f, 0.85f, ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r6));
                r20.scale(r113, r113, r10.centerX(), r10.centerY());
            L106:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) != 6) goto L108;
            L113:
                r20.scale(r7, r7, r10.centerX(), r10.centerY());
            L114:
                drawImage(r20, r82, r6, r4);
                r20.restore();
                goto L115
            L108:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 13) goto L113;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 14) goto L113;
                skew(r20, r5, r6.getHeight());
                goto L114
            L93:
                if (r112 < 1.0f) goto L96;
                drawImage(r20, r82, r6, r4);
                goto L115
            L55:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(r13) == 5) goto L64;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 10) goto L64;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 9) goto L64;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 7) goto L64;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) != 15) goto L65;
            L67:
                if (r6.isDefaultReaction == false) goto L69;
            L81:
                ImageReceiver r83 = r6.imageReceiver;
                if (r83 == null) goto L84;
                r83.setImageCoords(r10);
            L84:
                r82 = null;
                goto L86
            L69:
                if (r6.isStaticIcon == true) goto L81;
                if (r6.span != null) goto L75;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) != 13) goto L115;
            L75:
                if (r6.notDraw == true) goto L115;
                r82 = r6.drawable;
                if (r82 == null) goto L115;
                r82.setAlpha(255);
                r82.setBounds(r10);
                goto L86
            L36:
                if (SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0) < 0) goto L45;
                if (SelectAnimatedEmojiDialog.m18633$$Nest$fgetanimateExpandToPosition(this.this$1.this$0) < 0) goto L45;
                if (SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) <= 0) goto L45;
                int r132 = this.this$1.getChildAdapterPosition(r6) - SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0);
                int r14 = SelectAnimatedEmojiDialog.m18633$$Nest$fgetanimateExpandToPosition(this.this$1.this$0) - SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0);
                if (r132 < 0) goto L46;
                if (r132 >= r14) goto L46;
                float r42 = MathUtils.clamp((SystemClock.elapsedRealtime() - SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) / this.this$1.this$0.animateExpandAppearDuration(), 0.0f, 1.0f);
                float r102 = r132;
                float r114 = r14;
                float r133 = r114 / 4.0f;
                float r142 = AndroidUtilities.cascade(r42, r102, r114, r133);
                r7 = r7 * ((this.appearScaleInterpolator.getInterpolation(AndroidUtilities.cascade(r42, r102, r114, r133)) * 0.5f) + 0.5f);
                r4 = r142;
                goto L46
            L31:
                if ((SystemClock.elapsedRealtime() - SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) >= this.this$1.this$0.animateExpandDuration()) goto L33;
                r8 = true;
            L22:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) != 3) goto L24;
            L26:
                float r134 = 1.0f;
            L27:
                r7 = r7 * (0.8f + (0.2f * (1.0f - Math.max(r134, ImageViewEmoji.m18726$$Nest$fgetpressedProgress(r6)))));
                goto L29
            L24:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 4) goto L26;
                r134 = ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r6) * 0.7f;
            L115:
                r5 = r5 + 1;
                goto L6
            L116:
                r20.restore();
                return;
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void onFrameReady() {
                super.onFrameReady();
                int r0 = 0;
            L4:
                if (r0 >= this.drawInBackgroundViews.size()) goto L9;
                ImageReceiver.BackgroundThreadDrawHolder r1 = this.drawInBackgroundViews.get(r0).backgroundThreadDrawHolder[this.threadIndex];
                if (r1 == null) goto L8;
                r1.release();
            L8:
                r0 = r0 + 1;
                goto L4
            L9:
                this.this$1.this$0.emojiGridView.invalidate();
            }

            @Override // org.telegram.ui.Components.DrawingInBackgroundThreadDrawable
            public void prepareDraw(long r18) {
                this.drawInBackgroundViews.clear();
                int r4 = 0;
            L4:
                if (r4 >= this.imageViewEmojis.size()) goto L97;
                ImageViewEmoji r5 = this.imageViewEmojis.get(r4);
                if (r5.notDraw == true) goto L96;
                if (r5.empty == false) goto L37;
                Drawable r6 = SelectAnimatedEmojiDialog.m18716$$Nest$mgetPremiumStar(this.this$1.this$0);
                SelectAnimatedEmojiDialog r11 = this.this$1.this$0;
                if (r11.badgePicker == false) goto L13;
            L24:
                float r112 = 1.3f;
            L26:
                if (ImageViewEmoji.m18726$$Nest$fgetpressedProgress(r5) == 0.0f) goto L28;
            L29:
                r112 = r112 * (((1.0f - Math.max(ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r5) * 0.8f, ImageViewEmoji.m18726$$Nest$fgetpressedProgress(r5))) * 0.2f) + 0.8f);
            L30:
                if (r6 == null) goto L96;
                r6.setAlpha(255);
                int r7 = (r5.getWidth() - r5.getPaddingLeft()) - r5.getPaddingRight();
                int r8 = (r5.getHeight() - r5.getPaddingTop()) - r5.getPaddingBottom();
                Rect r9 = AndroidUtilities.rectTmp2;
                float r72 = r7 / 2.0f;
                float r82 = r8 / 2.0f;
                r9.set((int) ((r5.getWidth() / 2.0f) - ((r5.getScaleX() * r72) * r112)), (int) ((r5.getHeight() / 2.0f) - ((r5.getScaleY() * r82) * r112)), (int) ((r5.getWidth() / 2.0f) + ((r72 * r5.getScaleX()) * r112)), (int) ((r5.getHeight() / 2.0f) + ((r82 * r5.getScaleY()) * r112)));
                r9.offset(r5.getLeft() - this.startOffset, 0);
                if (r5.drawableBounds != null) goto L35;
                r5.drawableBounds = new Rect();
            L35:
                r5.drawableBounds.set(r9);
                r5.setDrawable(r6);
                this.drawInBackgroundViews.add(r5);
                goto L96
            L28:
                if (ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r5) <= 0.0f) goto L30;
            L13:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(r11) == 5) goto L24;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 10) goto L24;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 9) goto L24;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 7) goto L24;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 15) goto L24;
                r112 = 1.0f;
                goto L26
            L37:
                if (ImageViewEmoji.m18726$$Nest$fgetpressedProgress(r5) == 0.0f) goto L39;
            L40:
                Math.max(ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r5) * 0.8f, ImageViewEmoji.m18726$$Nest$fgetpressedProgress(r5));
            L42:
                if (SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) > 0) goto L44;
            L56:
                float r13 = 1.0f * r5.getAlpha();
            L58:
                if (r5.isDefaultReaction == false) goto L60;
            L73:
                ImageReceiver r83 = r5.imageReceiver;
                r83.setAlpha(r13);
            L74:
                if (r83 == null) goto L96;
                SelectAnimatedEmojiDialog r62 = this.this$1.this$0;
                if (r62.paused == true) goto L79;
            L83:
                boolean r63 = false;
            L84:
                r83.setEmojiPaused(r63);
                if (r5.selected == false) goto L87;
                r83.setRoundRadius(AndroidUtilities.dp(4.0f));
            L88:
                ImageReceiver.BackgroundThreadDrawHolder[] r64 = r5.backgroundThreadDrawHolder;
                int r73 = this.threadIndex;
                r64[r73] = r83.setDrawInBackgroundThread(r64[r73], r73);
                r5.backgroundThreadDrawHolder[this.threadIndex].time = r18;
                r5.imageReceiverToDraw = r83;
                r5.update(r18);
                r5.getWidth();
                r5.getPaddingLeft();
                r5.getPaddingRight();
                r5.getHeight();
                r5.getPaddingTop();
                r5.getPaddingBottom();
                Rect r65 = AndroidUtilities.rectTmp2;
                r65.set(r5.getPaddingLeft(), r5.getPaddingTop(), r5.getWidth() - r5.getPaddingRight(), r5.getHeight() - r5.getPaddingBottom());
                if (r5.selected == true) goto L91;
            L95:
                r65.offset((r5.getLeft() + ((int) r5.getTranslationX())) - this.startOffset, 0);
                r5.backgroundThreadDrawHolder[this.threadIndex].setBounds(r65);
                r5.skewAlpha = 1.0f;
                r5.skewIndex = r4;
                this.drawInBackgroundViews.add(r5);
                goto L96
            L91:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 3) goto L95;
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 4) goto L95;
                r65.set(Math.round(r65.centerX() - ((r65.width() / 2.0f) * 0.86f)), Math.round(r65.centerY() - ((r65.height() / 2.0f) * 0.86f)), Math.round(r65.centerX() + ((r65.width() / 2.0f) * 0.86f)), Math.round(r65.centerY() + ((r65.height() / 2.0f) * 0.86f)));
                goto L95
            L87:
                r83.setRoundRadius(0);
                goto L88
            L79:
                if (r62.pausedExceptSelected == true) goto L81;
            L82:
                r63 = true;
                goto L84
            L81:
                if (r5.selected == true) goto L83;
            L60:
                if (r5.isStaticIcon == true) goto L73;
                if (r5.span == null) goto L96;
                Drawable r66 = r5.drawable;
                if ((r66 instanceof AnimatedEmojiDrawable) == false) goto L67;
                AnimatedEmojiDrawable r67 = (AnimatedEmojiDrawable) r66;
            L68:
                if (r67 == null) goto L96;
                if (r67.getImageReceiver() == null) goto L96;
                r83 = r67.getImageReceiver();
                r67.setAlpha((int) (r13 * 255.0f));
                r5.setDrawable(r67);
                r5.drawable.setColorFilter(SelectAnimatedEmojiDialog.m18667$$Nest$fgetpremiumStarColorFilter(this.this$1.this$0));
                goto L74
            L67:
                r67 = null;
                goto L68
            L44:
                if ((SystemClock.elapsedRealtime() - SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) >= this.this$1.this$0.animateExpandDuration()) goto L56;
                if (SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0) < 0) goto L56;
                if (SelectAnimatedEmojiDialog.m18633$$Nest$fgetanimateExpandToPosition(this.this$1.this$0) < 0) goto L56;
                if (SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0) <= 0) goto L56;
                int r68 = this.this$1.getChildAdapterPosition(r5) - SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0);
                int r113 = SelectAnimatedEmojiDialog.m18633$$Nest$fgetanimateExpandToPosition(this.this$1.this$0) - SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$1.this$0);
                if (r68 < 0) goto L55;
                if (r68 >= r113) goto L55;
                float r84 = MathUtils.clamp((SystemClock.elapsedRealtime() - SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$1.this$0)) / this.this$1.this$0.animateExpandAppearDuration(), 0.0f, 1.0f);
                float r69 = r68;
                float r114 = r113;
                float r12 = r114 / 4.0f;
                float r132 = AndroidUtilities.cascade(r84, r69, r114, r12);
                this.appearScaleInterpolator.getInterpolation(AndroidUtilities.cascade(r84, r69, r114, r12));
                r13 = r132 * 1.0f;
            L55:
                r13 = 1.0f;
                goto L58
            L39:
                if (ImageViewEmoji.m18727$$Nest$fgetselectedProgress(r5) <= 0.0f) goto L42;
            L96:
                r4 = r4 + 1;
                goto L4
            }
        }

        /* renamed from: -$$Nest$fgetanimatedEmojiDrawables, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LongSparseArray m18725$$Nest$fgetanimatedEmojiDrawables(EmojiListView r0) {
            return r0.animatedEmojiDrawables;
        }

        public EmojiListView(SelectAnimatedEmojiDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.viewsGroupedByLines = new SparseArray();
            this.unusedArrays = new ArrayList();
            this.unusedLineDrawables = new ArrayList();
            this.lineDrawables = new ArrayList();
            this.lineDrawablesTmp = new ArrayList();
            this.animatedEmojiDrawables = new LongSparseArray();
            this.lastChildCount = -1;
            setDrawSelectorBehind(true);
            setClipToPadding(false);
            setSelectorRadius(AndroidUtilities.dp(4.0f));
            setSelectorDrawableColor(Theme.getColor(Theme.key_listSelector, this.resourcesProvider));
        }

        private void release(ArrayList<DrawingInBackgroundLine> r2) {
            int r1 = 0;
        L4:
            if (r1 >= r2.size()) goto L6;
            r2.get(r1).onDetachFromWindow();
            r1 = r1 + 1;
            goto L4
        L6:
            r2.clear();
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean canHighlightChildAt(View r3, float r4, float r5) {
            if ((r3 instanceof ImageViewEmoji) == false) goto L11;
            ImageViewEmoji r0 = (ImageViewEmoji) r3;
            if (r0.empty == true) goto L10;
            Drawable r02 = r0.drawable;
            if ((r02 instanceof AnimatedEmojiDrawable) == false) goto L11;
            if (((AnimatedEmojiDrawable) r02).canOverrideColor() == false) goto L11;
        L10:
            setSelectorDrawableColor(ColorUtils.setAlphaComponent(SelectAnimatedEmojiDialog.m18628$$Nest$fgetaccentColor(this.this$0), 30));
        L13:
            return super.canHighlightChildAt(r3, r4, r5);
        L11:
            setSelectorDrawableColor(Theme.getColor(Theme.key_listSelector, this.resourcesProvider));
            goto L13
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r22) {
            Canvas r1 = r22;
            if (getVisibility() == 0) goto L5;
            return;
        L5:
            int r8 = 0;
            this.invalidated = false;
            int r9 = r1.getSaveCount();
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 6) goto L8;
        L17:
            int r2 = 0;
        L18:
            int r3 = this.viewsGroupedByLines.size();
            SparseArray<ArrayList<ImageViewEmoji>> r4 = this.viewsGroupedByLines;
            if (r2 >= r3) goto L21;
            ArrayList<ImageViewEmoji> r32 = r4.valueAt(r2);
            r32.clear();
            this.unusedArrays.add(r32);
            r2 = r2 + 1;
            goto L18
        L21:
            r4.clear();
            if (SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$0) > 0) goto L24;
        L30:
            boolean r11 = false;
        L31:
            int r12 = 2;
            if (this.animatedEmojiDrawables == null) goto L90;
            boolean r23 = false;
            int r13 = 0;
        L35:
            if (r13 >= getChildCount()) goto L90;
            View r14 = getChildAt(r13);
            if ((r14 instanceof ImageViewEmoji) == false) goto L77;
            ImageViewEmoji r33 = (ImageViewEmoji) r14;
            r33.updatePressedProgress();
            int r6 = r33.position;
            if (SelectAnimatedEmojiDialog.m18691$$Nest$fgetsmoothScrolling(this.this$0) == false) goto L42;
            int r7 = (int) r14.getY();
        L41:
            float r16 = 255.0f;
            ArrayList<ImageViewEmoji> r42 = this.viewsGroupedByLines.get(r7);
            r1.save();
            int r17 = 1;
            float r18 = 2.0f;
            r1.translate(r33.getX(), r33.getY());
            if (r33.particlesColor == null) goto L57;
            StarsReactionsSheet.Particles r10 = this.this$0.getCollectionParticles();
            r10.setBounds(r8, r8, r33.getWidth(), r33.getHeight());
            if (r23 == true) goto L48;
            r10.process();
            r23 = true;
        L48:
            r1.save();
            int r5 = r6 % 6;
            if (r5 != r12) goto L51;
            float r15 = -1.0f;
        L52:
            if (r5 != r12) goto L55;
            float r52 = -1.0f;
        L54:
            int r19 = r12;
            r1.scale(r15, r52, r33.getWidth() / 2.0f, r33.getHeight() / 2.0f);
            r1.rotate((r6 % 4) * 90, r33.getWidth() / 2.0f, r33.getHeight() / 2.0f);
            r10.draw(r1, r33.particlesColor.intValue());
            r1.restore();
        L58:
            r33.drawSelected(r1, this);
            r1.restore();
            if (r33.getBackground() == null) goto L61;
            r33.getBackground().setBounds((int) r33.getX(), (int) r33.getY(), ((int) r33.getX()) + r33.getWidth(), ((int) r33.getY()) + r33.getHeight());
            r33.getBackground().setAlpha((int) (r33.getAlpha() * 255.0f));
            r33.getBackground().draw(r1);
            r33.getBackground().setAlpha(255);
        L61:
            if (r42 == null) goto L63;
        L67:
            r42.add(r33);
            PremiumLockIconView r43 = r33.premiumLockIconView;
            if (r43 != null) goto L70;
        L76:
            boolean r82 = r23;
            if (r11 == false) goto L89;
            if (r14 == null) goto L89;
            int r24 = getChildAdapterPosition(r14);
            int r34 = SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$0);
            if (SelectAnimatedEmojiDialog.m18630$$Nest$fgetanimateExpandFromButtonTranslate(this.this$0) <= 0.0f) goto L83;
            int r44 = 0;
        L85:
            if (r24 != (r34 - r44)) goto L89;
            float r25 = CubicBezierInterpolator.EASE_OUT.getInterpolation(MathUtils.clamp((SystemClock.elapsedRealtime() - SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$0)) / 200.0f, 0.0f, 1.0f));
            if (r25 >= 1.0f) goto L89;
            float r102 = 1.0f - r25;
            r1.saveLayerAlpha(r14.getLeft(), r14.getTop(), r14.getRight(), r14.getBottom(), (int) (r102 * r16), 31);
            r1.translate(r14.getLeft(), r14.getTop() + SelectAnimatedEmojiDialog.m18630$$Nest$fgetanimateExpandFromButtonTranslate(this.this$0));
            float r103 = (r102 * 0.5f) + 0.5f;
            r1.scale(r103, r103, r14.getWidth() / r18, r14.getHeight() / r18);
            SelectAnimatedEmojiDialog.m18629$$Nest$fgetanimateExpandFromButton(this.this$0).draw(r1);
            r1.restore();
            goto L89
        L83:
            r44 = r17;
        L89:
            r13 = r13 + 1;
            r23 = r82;
            r12 = r19;
            r8 = 0;
            goto L35
        L70:
            if (r43.getVisibility() != 0) goto L76;
            if (r33.premiumLockIconView.getImageReceiver() != null) goto L76;
            ImageReceiver r45 = r33.imageReceiverToDraw;
            if (r45 == null) goto L76;
            r33.premiumLockIconView.setImageReceiver(r45);
            goto L76
        L63:
            if (this.unusedArrays.isEmpty() == true) goto L65;
            r42 = this.unusedArrays.remove(r4.size() - 1);
        L66:
            this.viewsGroupedByLines.put(r7, r42);
            goto L67
        L65:
            r42 = new ArrayList();
            goto L66
        L55:
            r52 = 1.0f;
            goto L54
        L51:
            r15 = 1.0f;
            goto L52
        L57:
            r19 = r12;
            goto L58
        L42:
            r7 = r14.getTop();
            goto L41
        L77:
            r19 = r12;
            r16 = 255.0f;
            r17 = 1;
            r18 = 2.0f;
        L90:
            this.lineDrawablesTmp.clear();
            this.lineDrawablesTmp.addAll(this.lineDrawables);
            this.lineDrawables.clear();
            long r35 = System.currentTimeMillis();
            int r83 = 0;
        L91:
            DrawingInBackgroundLine r53 = null;
            if (r83 >= this.viewsGroupedByLines.size()) goto L111;
            ArrayList<ImageViewEmoji> r26 = this.viewsGroupedByLines.valueAt(r83);
            ImageViewEmoji r72 = r26.get(0);
            int r62 = getChildAdapterPosition(r72);
            int r104 = 0;
        L95:
            if (r104 >= this.lineDrawablesTmp.size()) goto L100;
            if (this.lineDrawablesTmp.get(r104).position == r62) goto L98;
            r104 = r104 + 1;
            goto L95
        L98:
            r53 = this.lineDrawablesTmp.get(r104);
            this.lineDrawablesTmp.remove(r104);
        L100:
            if (r53 == null) goto L102;
        L106:
            this.lineDrawables.add(r53);
            r53.imageViewEmojis = r26;
            r1.save();
            r1.translate(r72.getLeft(), r72.getY());
            r53.startOffset = r72.getLeft();
            int r27 = getMeasuredWidth() - (r72.getLeft() * 2);
            int r63 = r72.getMeasuredHeight();
            if (r27 <= 0) goto L110;
            if (r63 <= 0) goto L110;
            Canvas r28 = r1;
            r53.draw(r28, r35, r27, r63, getAlpha());
            r1 = r28;
        L110:
            r1.restore();
            r83 = r83 + 1;
            goto L91
        L102:
            if (this.unusedLineDrawables.isEmpty() == true) goto L104;
            r53 = this.unusedLineDrawables.remove(r5.size() - 1);
        L105:
            r53.position = r62;
            r53.onAttachToWindow();
            goto L106
        L104:
            r53 = new DrawingInBackgroundLine(this);
            r53.setLayerNum(7);
            goto L105
        L111:
            int r64 = 0;
        L113:
            if (r64 >= this.lineDrawablesTmp.size()) goto L119;
            if (this.unusedLineDrawables.size() >= 3) goto L117;
            this.unusedLineDrawables.add(this.lineDrawablesTmp.get(r64));
            this.lineDrawablesTmp.get(r64).imageViewEmojis = null;
            this.lineDrawablesTmp.get(r64).reset();
        L118:
            r64 = r64 + 1;
            goto L113
        L117:
            this.lineDrawablesTmp.get(r64).onDetachFromWindow();
            goto L118
        L119:
            this.lineDrawablesTmp.clear();
            int r65 = 0;
        L121:
            if (r65 >= getChildCount()) goto L146;
            View r29 = getChildAt(r65);
            if ((r29 instanceof ImageViewEmoji) == false) goto L141;
            ImageViewEmoji r210 = (ImageViewEmoji) r29;
            PremiumLockIconView r36 = r210.premiumLockIconView;
            if (r36 == null) goto L137;
            if (r36.getVisibility() != 0) goto L137;
            r1.save();
            r1.translate((int) ((r210.getX() + r210.getMeasuredWidth()) - r210.premiumLockIconView.getMeasuredWidth()), (int) ((r210.getY() + r210.getMeasuredHeight()) - r210.premiumLockIconView.getMeasuredHeight()));
            Drawable r37 = r210.drawable;
            if ((r37 instanceof AnimatedEmojiDrawable) == false) goto L131;
            ImageReceiver r38 = ((AnimatedEmojiDrawable) r37).getImageReceiver();
        L133:
            if (r210.premiumLockIconView.done() == true) goto L135;
            r210.premiumLockIconView.setImageReceiver(r38);
        L135:
            r210.premiumLockIconView.draw(r1);
            r1.restore();
            goto L137
        L131:
            r38 = r210.imageReceiver;
        L137:
            if (r210.emojiDrawable == null) goto L145;
            r1.save();
            int r39 = AndroidUtilities.dp(17.0f);
            float r46 = r210.getX() + r210.getMeasuredWidth();
            float r54 = r39;
            r1.translate((int) (r46 - r54), (int) ((r210.getY() + r210.getMeasuredHeight()) - r54));
            r210.emojiDrawable.setBounds(0, 0, r39, r39);
            r210.emojiDrawable.draw(r1);
            r1.restore();
        L145:
            r65 = r65 + 1;
            goto L121
        L141:
            if (r29 == null) goto L145;
            if (r29 == SelectAnimatedEmojiDialog.m18629$$Nest$fgetanimateExpandFromButton(this.this$0)) goto L145;
            r1.save();
            r1.translate((int) r29.getX(), (int) r29.getY());
            r29.draw(r1);
            r1.restore();
            goto L145
        L146:
            r1.restoreToCount(r9);
            HwEmojis.exec();
            return;
        L24:
            if ((SystemClock.elapsedRealtime() - SelectAnimatedEmojiDialog.m18632$$Nest$fgetanimateExpandStartTime(this.this$0)) >= this.this$0.animateExpandDuration()) goto L30;
            if (SelectAnimatedEmojiDialog.m18629$$Nest$fgetanimateExpandFromButton(this.this$0) == null) goto L30;
            if (SelectAnimatedEmojiDialog.m18631$$Nest$fgetanimateExpandFromPosition(this.this$0) < 0) goto L30;
            r11 = true;
            goto L31
        L8:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 14) goto L17;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 13) goto L17;
            if (this.selectorRect.isEmpty() == true) goto L17;
            this.selectorDrawable.setBounds(this.selectorRect);
            r1.save();
            Consumer<Canvas> r211 = this.selectorTransformer;
            if (r211 == null) goto L16;
            r211.accept(r1);
        L16:
            this.selectorDrawable.draw(r1);
            r1.restore();
            goto L17
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            return super.drawChild(r1, r2, r3);
        }

        @Override // android.view.View
        public void invalidate() {
            if (HwEmojis.grab(this) == false) goto L6;
            return;
        L6:
            if (this.invalidated == false) goto L8;
            return;
        L8:
            this.invalidated = true;
            super.invalidate();
        }

        @Override // android.view.View
        public void invalidate(int r2, int r3, int r4, int r5) {
            if (HwEmojis.grab(this) == false) goto L5;
            return;
        L5:
            super.invalidate(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public void invalidateViews() {
            if (HwEmojis.grab(this) == false) goto L5;
            return;
        L5:
            super.invalidateViews();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            SelectAnimatedEmojiDialog r0 = this.this$0;
            if (this != r0.emojiGridView) goto L6;
            r0.bigReactionImageReceiver.onAttachedToWindow();
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            SelectAnimatedEmojiDialog r0 = this.this$0;
            if (this != r0.emojiGridView) goto L5;
            r0.bigReactionImageReceiver.onDetachedFromWindow();
        L5:
            release(this.unusedLineDrawables);
            release(this.lineDrawables);
            release(this.lineDrawablesTmp);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            invalidate();
        }
    }

    /* loaded from: classes6.dex */
    public class EmojiPackButton extends FrameLayout {
        AnimatedTextView addButtonTextView;
        FrameLayout addButtonView;
        private ValueAnimator installFadeAway;
        private String lastTitle;
        private ValueAnimator lockAnimator;
        private Boolean lockShow;
        private float lockT;
        PremiumButtonView premiumButtonView;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$EmojiPackButton$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedTextView {
            final /* synthetic */ EmojiPackButton this$1;
            final /* synthetic */ SelectAnimatedEmojiDialog val$this$0;

            public AnonymousClass1(EmojiPackButton r1, Context r2, SelectAnimatedEmojiDialog r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                if (HwEmojis.grab(this) == false) goto L5;
                return;
            L5:
                super.invalidate();
            }

            @Override // android.view.View
            public void invalidate(int r2, int r3, int r4, int r5) {
                if (HwEmojis.grab(this) == false) goto L5;
                return;
            L5:
                super.invalidate(r2, r3, r4, r5);
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$EmojiPackButton$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ EmojiPackButton this$1;
            final /* synthetic */ boolean val$show;

            public AnonymousClass2(EmojiPackButton r1, boolean r2) {
                this.this$1 = r1;
                this.val$show = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                if (this.val$show == true) goto L6;
                this.this$1.premiumButtonView.setVisibility(8);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$QbitCcGJLADMICUzrLa0R3ye4pw(EmojiPackButton r0, ValueAnimator r1) {
            r0.lambda$updateInstall$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$YvRIHrnize62um2oKIH3cCaPMUk(EmojiPackButton r0, ValueAnimator r1) {
            r0.lambda$updateLock$1(r1);
        }

        public EmojiPackButton(SelectAnimatedEmojiDialog r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            AnonymousClass1 r0 = new AnonymousClass1(this, getContext(), r8);
            this.addButtonTextView = r0;
            r0.setAnimationProperties(0.3f, 0, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.addButtonTextView.setTextSize(AndroidUtilities.dp(14.0f));
            this.addButtonTextView.setTypeface(AndroidUtilities.bold());
            this.addButtonTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r8)));
            this.addButtonTextView.setGravity(17);
            FrameLayout r92 = new FrameLayout(getContext());
            this.addButtonView = r92;
            r92.setBackground(Theme.AdaptiveRipple.filledRect(Theme.getColor(Theme.key_featuredStickers_addButton, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r8)), new float[]{8.0f}));
            this.addButtonView.addView(this.addButtonTextView, LayoutHelper.createFrame(-1, -2, 17));
            addView(this.addButtonView, LayoutHelper.createFrame(-1, -1.0f));
            PremiumButtonView r93 = new PremiumButtonView(getContext(), false, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r8));
            this.premiumButtonView = r93;
            r93.setIcon(R.raw.unlock_icon);
            addView(this.premiumButtonView, LayoutHelper.createFrame(-1, -1.0f));
        }

        private /* synthetic */ void lambda$updateInstall$0(ValueAnimator r1) {
            FrameLayout r0 = this.addButtonView;
            if (r0 == null) goto L6;
            r0.setAlpha(((Float) r1.getAnimatedValue()).floatValue());
            return;
        }

        private /* synthetic */ void lambda$updateLock$1(ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            this.lockT = r32;
            FrameLayout r0 = this.addButtonView;
            if (r0 == null) goto L5;
            r0.setAlpha(1.0f - r32);
        L5:
            PremiumButtonView r33 = this.premiumButtonView;
            if (r33 == null) goto L9;
            r33.setAlpha(this.lockT);
            return;
        }

        private void updateLock(boolean r5, boolean r6) {
            ValueAnimator r0 = this.lockAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.lockAnimator = null;
        L5:
            Boolean r02 = this.lockShow;
            if (r02 != null) goto L8;
        L10:
            Boolean r03 = Boolean.valueOf(r5);
            this.lockShow = r03;
            float r1 = 0.0f;
            int r2 = 0;
            if (r6 == false) goto L18;
            this.premiumButtonView.setVisibility(0);
            float r62 = this.lockT;
            if (r5 == false) goto L15;
            r1 = 1.0f;
        L15:
            ValueAnimator r63 = ValueAnimator.ofFloat(new float[]{r62, r1});
            this.lockAnimator = r63;
            r63.addUpdateListener(new SelectAnimatedEmojiDialog$EmojiPackButton$$ExternalSyntheticLambda1(this));
            this.lockAnimator.addListener(new AnonymousClass2(this, r5));
            this.lockAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.lockAnimator.setDuration(350);
            this.lockAnimator.start();
            return;
        L18:
            if (r03.booleanValue() == false) goto L20;
            r1 = 1.0f;
        L20:
            this.lockT = r1;
            this.addButtonView.setAlpha(1.0f - r1);
            this.premiumButtonView.setAlpha(this.lockT);
            this.premiumButtonView.setScaleX(this.lockT);
            this.premiumButtonView.setScaleY(this.lockT);
            PremiumButtonView r52 = this.premiumButtonView;
            if (this.lockShow.booleanValue() == true) goto L24;
            r2 = 8;
        L24:
            r52.setVisibility(r2);
            return;
        L8:
            if (r02.booleanValue() != r5) goto L10;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(8.0f));
            super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec((AndroidUtilities.dp(44.0f) + getPaddingTop()) + getPaddingBottom(), TLObject.FLAG_30));
        }

        public void set(String r5, boolean r6, boolean r7, View.OnClickListener r8) {
            this.lastTitle = r5;
            if (r6 == false) goto L5;
            this.addButtonView.setVisibility(8);
            this.premiumButtonView.setVisibility(0);
            this.premiumButtonView.setButton(LocaleController.formatString("UnlockPremiumEmojiPack", R.string.UnlockPremiumEmojiPack, new Object[]{r5}), r8);
        L6:
            updateInstall(r7, false);
            updateLock(r6, false);
            return;
        L5:
            this.premiumButtonView.setVisibility(8);
            this.addButtonView.setVisibility(0);
            this.addButtonView.setOnClickListener(r8);
            goto L6
        }

        public void updateInstall(boolean r4, boolean r5) {
            if (r4 == false) goto L4;
            String r0 = LocaleController.getString(R.string.Added);
        L5:
            this.addButtonTextView.setText(r0, r5);
            this.addButtonView.setContentDescription(r0);
            ValueAnimator r02 = this.installFadeAway;
            if (r02 == null) goto L8;
            r02.cancel();
            this.installFadeAway = null;
        L8:
            this.addButtonView.setEnabled(!r4);
            FrameLayout r03 = this.addButtonView;
            float r1 = 1.0f;
            if (r5 == false) goto L15;
            float r52 = r03.getAlpha();
            if (r4 == false) goto L13;
            r1 = 0.6f;
        L13:
            this.installFadeAway = ValueAnimator.ofFloat(new float[]{r52, r1});
            FrameLayout r42 = this.addButtonView;
            r42.setAlpha(r42.getAlpha());
            this.installFadeAway.addUpdateListener(new SelectAnimatedEmojiDialog$EmojiPackButton$$ExternalSyntheticLambda0(this));
            this.installFadeAway.setDuration(450);
            this.installFadeAway.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.installFadeAway.start();
            return;
        L15:
            if (r4 == false) goto L17;
            r1 = 0.6f;
        L17:
            r03.setAlpha(r1);
            return;
        L4:
            r0 = LocaleController.formatString("AddStickersCount", R.string.AddStickersCount, new Object[]{this.lastTitle});
            goto L5
        }
    }

    /* loaded from: classes6.dex */
    public class EmojiPackExpand extends FrameLayout {
        public TextView textView;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public EmojiPackExpand(SelectAnimatedEmojiDialog r3, Context r4, Theme.ResourcesProvider r5) {
            this.this$0 = r3;
            super(r4);
            TextView r0 = new TextView(r4);
            this.textView = r0;
            r0.setTextSize(1, 12.0f);
            this.textView.setTextColor(-1);
            if (r3.useAccentForPlus == false) goto L5;
            int r32 = Theme.blendOver(SelectAnimatedEmojiDialog.m18628$$Nest$fgetaccentColor(r3), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhite), 0.4f));
        L6:
            this.textView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(11.0f), r32));
            this.textView.setTypeface(AndroidUtilities.bold());
            this.textView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(1.66f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f));
            addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
            return;
        L5:
            r32 = ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_chat_emojiPanelStickerSetName, r5), 99);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public class HeaderView extends FrameLayout {
        ImageView closeIcon;
        private LinearLayout layoutView;
        private ValueAnimator lockAnimator;
        private float lockT;
        private RLottieImageView lockView;
        private TextView textView;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        public static /* synthetic */ void $r8$lambda$Q8bLYTWpD0aHf2OdY3L81Yv0sMs(HeaderView r0, ValueAnimator r1) {
            r0.lambda$updateLock$0(r1);
        }

        public HeaderView(SelectAnimatedEmojiDialog r6, Context r7, boolean r8) {
            this.this$0 = r6;
            super(r7);
            LinearLayout r0 = new LinearLayout(r7);
            this.layoutView = r0;
            r0.setOrientation(0);
            LinearLayout r02 = this.layoutView;
            if (r8 == false) goto L5;
            int r82 = 3;
        L6:
            addView(r02, LayoutHelper.createFrame(-2, -2, r82));
            RLottieImageView r83 = new RLottieImageView(r7);
            this.lockView = r83;
            r83.setAnimation(R.raw.unlock_icon, 20, 20);
            RLottieImageView r84 = this.lockView;
            int r03 = Theme.key_chat_emojiPanelStickerSetName;
            r84.setColorFilter(Theme.getColor(r03, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r6)));
            this.layoutView.addView(this.lockView, LayoutHelper.createLinear(20, 20));
            TextView r85 = new TextView(r7);
            this.textView = r85;
            r85.setTextColor(Theme.getColor(r03, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r6)));
            this.textView.setTypeface(AndroidUtilities.bold());
            this.textView.setTextSize(1, 14.0f);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setSingleLine(true);
            this.layoutView.addView(this.textView, LayoutHelper.createLinear(-2, -2, 17));
            ImageView r86 = new ImageView(r7);
            this.closeIcon = r86;
            r86.setImageResource(R.drawable.msg_close);
            this.closeIcon.setScaleType(ImageView.ScaleType.CENTER);
            this.closeIcon.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_emojiPanelStickerSetNameIcon, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r6)), PorterDuff.Mode.MULTIPLY));
            addView(this.closeIcon, LayoutHelper.createFrame(24, 24, 21));
            return;
        L5:
            r82 = 17;
            goto L6
        }

        private /* synthetic */ void lambda$updateLock$0(ValueAnimator r5) {
            this.lockT = ((Float) r5.getAnimatedValue()).floatValue();
            this.lockView.setTranslationX(AndroidUtilities.dp(-8.0f) * (1.0f - this.lockT));
            this.textView.setTranslationX(AndroidUtilities.dp(-8.0f) * (1.0f - this.lockT));
            this.lockView.setAlpha(this.lockT);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
        }

        public void setText(CharSequence r5, String r6, boolean r7) {
            if (r5 == null) goto L7;
            if (r6 == null) goto L7;
            int r0 = r5.toString().toLowerCase().indexOf(r6.toLowerCase());
            if (r0 < 0) goto L7;
            SpannableString r1 = new SpannableString(r5);
            r1.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_chat_emojiPanelStickerSetNameHighlight, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(this.this$0))), r0, r6.length() + r0, 33);
            r5 = r1;
        L7:
            this.textView.setText(r5);
            updateLock(r7, false);
        }

        public void setText(String r2, boolean r3) {
            this.textView.setText(r2);
            updateLock(r3, false);
        }

        public void updateLock(boolean r4, boolean r5) {
            ValueAnimator r0 = this.lockAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.lockAnimator = null;
        L5:
            float r02 = 0.0f;
            if (r5 == false) goto L12;
            float r52 = this.lockT;
            if (r4 == false) goto L10;
            r02 = 1.0f;
        L10:
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r52, r02});
            this.lockAnimator = r42;
            r42.addUpdateListener(new SelectAnimatedEmojiDialog$HeaderView$$ExternalSyntheticLambda0(this));
            this.lockAnimator.setDuration(200);
            this.lockAnimator.setInterpolator(CubicBezierInterpolator.EASE_BOTH);
            this.lockAnimator.start();
            return;
        L12:
            if (r4 == false) goto L14;
            r02 = 1.0f;
        L14:
            this.lockT = r02;
            this.lockView.setTranslationX(AndroidUtilities.dp(-8.0f) * (1.0f - this.lockT));
            this.textView.setTranslationX(AndroidUtilities.dp(-8.0f) * (1.0f - this.lockT));
            this.lockView.setAlpha(this.lockT);
        }
    }

    /* loaded from: classes6.dex */
    public class ImageViewEmoji extends View {
        private float animatedScale;
        public boolean attached;
        ValueAnimator backAnimator;
        public ImageReceiver.BackgroundThreadDrawHolder[] backgroundThreadDrawHolder;
        public float bigReactionSelectedProgress;
        public TLRPC.Document document;
        public Drawable drawable;
        public Rect drawableBounds;
        Drawable emojiDrawable;
        public boolean empty;
        public ImageReceiver imageReceiver;
        public ImageReceiver imageReceiverToDraw;
        final AnimatedEmojiSpan.InvalidateHolder invalidateHolder;
        public boolean isDefaultReaction;
        public boolean isFirstReactions;
        public boolean isStaticIcon;
        public boolean notDraw;
        public Integer particlesColor;
        public int position;
        public ImageReceiver preloadEffectImageReceiver;
        PremiumLockIconView premiumLockIconView;
        private float pressedProgress;
        public ReactionsLayoutInBubble.VisibleReaction reaction;
        public boolean selected;
        private float selectedProgress;
        private float selectedProgressT;
        private boolean shouldSelected;
        public float skewAlpha;
        public int skewIndex;
        public AnimatedEmojiSpan span;
        public TL_stars.TL_starGiftUnique starGift;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$ImageViewEmoji$1, reason: invalid class name */
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

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$ImageViewEmoji$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ ImageViewEmoji this$1;

            public AnonymousClass2(ImageViewEmoji r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$1.backAnimator = null;
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$ImageViewEmoji$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ ImageViewEmoji this$1;

            public AnonymousClass3(ImageViewEmoji r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                ImageViewEmoji.m18728$$Nest$fputpressedProgress(this.this$1, 0.0f);
                ImageViewEmoji r22 = this.this$1;
                r22.backAnimator = null;
                ImageViewEmoji.m18729$$Nest$fputshouldSelected(r22, false);
                this.this$1.setViewSelected(true, false);
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$ImageViewEmoji$4, reason: invalid class name */
        public class AnonymousClass4 extends PremiumLockIconView {
            final /* synthetic */ ImageViewEmoji this$1;

            public AnonymousClass4(ImageViewEmoji r1, Context r2, int r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                if ((this.this$1.getParent() instanceof View) == false) goto L6;
                ((View) this.this$1.getParent()).invalidate();
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$4BTvpGXu1aUmKhgyD0lkz2RXzdI(ImageViewEmoji r0, ValueAnimator r1) {
            r0.lambda$setPressed$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$RAwhDhXAnO88iIs6miir4VjP6gk(ImageViewEmoji r0) {
            r0.lambda$new$0();
        }

        public static /* synthetic */ void $r8$lambda$nhkq8QrZZ7VFkFqYJUBSTuxy8Sg(ImageViewEmoji r0, ValueAnimator r1) {
            r0.lambda$unselectWithScale$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$vEHIBwxoncNZTInN01_KOQmJ6OM(ImageViewEmoji r0, ValueAnimator r1) {
            r0.lambda$setViewSelectedWithScale$3(r1);
        }

        /* renamed from: -$$Nest$fgetpressedProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m18726$$Nest$fgetpressedProgress(ImageViewEmoji r0) {
            return r0.pressedProgress;
        }

        /* renamed from: -$$Nest$fgetselectedProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m18727$$Nest$fgetselectedProgress(ImageViewEmoji r0) {
            return r0.selectedProgress;
        }

        /* renamed from: -$$Nest$fputpressedProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18728$$Nest$fputpressedProgress(ImageViewEmoji r0, float r1) {
            r0.pressedProgress = r1;
        }

        /* renamed from: -$$Nest$fputshouldSelected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18729$$Nest$fputshouldSelected(ImageViewEmoji r0, boolean r1) {
            r0.shouldSelected = r1;
        }

        public ImageViewEmoji(SelectAnimatedEmojiDialog r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.empty = false;
            this.notDraw = false;
            this.backgroundThreadDrawHolder = new ImageReceiver.BackgroundThreadDrawHolder[2];
            this.preloadEffectImageReceiver = new ImageReceiver();
            this.animatedScale = 1.0f;
            this.invalidateHolder = new SelectAnimatedEmojiDialog$ImageViewEmoji$$ExternalSyntheticLambda1(this);
            this.preloadEffectImageReceiver.ignoreNotifications = true;
            setFocusable(true);
        }

        private void cancelBackAnimator() {
            ValueAnimator r0 = this.backAnimator;
            if (r0 == null) goto L6;
            r0.removeAllListeners();
            this.backAnimator.cancel();
            return;
        }

        private /* synthetic */ void lambda$new$0() {
            if (HwEmojis.isHwEnabled() == false) goto L6;
            return;
        L6:
            if (getParent() == null) goto L9;
            ((View) getParent()).invalidate();
            return;
        }

        private /* synthetic */ void lambda$setPressed$1(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            this.this$0.emojiGridView.invalidate();
        }

        private /* synthetic */ void lambda$setViewSelectedWithScale$3(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            this.this$0.emojiGridView.invalidate();
        }

        private /* synthetic */ void lambda$unselectWithScale$2(ValueAnimator r1) {
            this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
            this.this$0.emojiGridView.invalidate();
        }

        public void createImageReceiver(View r2) {
            if (this.imageReceiver != null) goto L9;
            ImageReceiver r0 = new ImageReceiver(r2);
            this.imageReceiver = r0;
            r0.setLayerNum(7);
            if (this.attached == false) goto L7;
            this.imageReceiver.onAttachedToWindow();
        L7:
            this.imageReceiver.setAspectFit(true);
            return;
        }

        public void createPremiumLockView() {
            PremiumLockIconView r0 = this.premiumLockIconView;
            if (r0 != null) goto L6;
            this.premiumLockIconView = new AnonymousClass4(this, getContext(), PremiumLockIconView.TYPE_REACTIONS_LOCK);
            int r02 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(16.66f), TLObject.FLAG_30);
            this.premiumLockIconView.measure(r02, r02);
            PremiumLockIconView r03 = this.premiumLockIconView;
            r03.layout(0, 0, r03.getMeasuredWidth(), this.premiumLockIconView.getMeasuredHeight());
            return;
        L6:
            r0.resetColor();
        }

        public void drawSelected(Canvas r7, View r8) {
            boolean r0 = this.selected;
            if (r0 == true) goto L9;
            if (this.shouldSelected == true) goto L9;
            if (this.selectedProgress > 0.0f) goto L9;
            return;
        L9:
            if (this.notDraw == true) goto L47;
            float r4 = 1.0f;
            if (r0 == false) goto L13;
        L14:
            float r02 = this.selectedProgressT;
            if (r02 >= 1.0f) goto L18;
            this.selectedProgressT = r02 + ((1000.0f / AndroidUtilities.screenRefreshRate) / 240.0f);
            r8.invalidate();
        L18:
            if (this.selected == true) goto L25;
            if (this.shouldSelected == true) goto L25;
            float r03 = this.selectedProgressT;
            if (r03 <= 0.0f) goto L25;
            this.selectedProgressT = r03 - ((1000.0f / AndroidUtilities.screenRefreshRate) / 240.0f);
            r8.invalidate();
        L25:
            if (this.selected == false) goto L27;
            float r82 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(this.selectedProgressT);
        L28:
            this.selectedProgress = Utilities.clamp(r82, 1.0f, 0.0f);
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 6) goto L31;
            r4 = 1.5f;
        L31:
            int r83 = AndroidUtilities.dp(r4);
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 6) goto L34;
            float r04 = 6.0f;
        L35:
            int r05 = AndroidUtilities.dp(r04);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
            float r84 = r83;
            r2.inset(r84, r84);
            if (this.empty == true) goto L43;
            Drawable r85 = this.drawable;
            if ((r85 instanceof AnimatedEmojiDrawable) == true) goto L40;
        L42:
            Paint r86 = this.this$0.selectorPaint;
        L44:
            int r1 = r86.getAlpha();
            r86.setAlpha((int) ((r1 * getAlpha()) * this.selectedProgress));
            float r6 = r05;
            r7.drawRoundRect(r2, r6, r6, r86);
            r86.setAlpha(r1);
            return;
        L40:
            if (((AnimatedEmojiDrawable) r85).canOverrideColor() == false) goto L42;
        L43:
            r86 = this.this$0.selectorAccentPaint;
            goto L44
        L34:
            r04 = 4.0f;
            goto L35
        L27:
            r82 = 1.0f - CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(1.0f - this.selectedProgressT);
            goto L28
        L13:
            if (this.shouldSelected == false) goto L18;
        }

        public float getAnimatedScale() {
            return this.animatedScale;
        }

        @Override // android.view.View
        public void invalidate() {
            if (HwEmojis.isHwEnabled() == false) goto L6;
            return;
        L6:
            if (getParent() == null) goto L9;
            ((View) getParent()).invalidate();
            return;
        }

        @Override // android.view.View
        public void invalidate(int r2, int r3, int r4, int r5) {
            if (HwEmojis.isHwEnabled() == false) goto L5;
            return;
        L5:
            super.invalidate(r2, r3, r4, r5);
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.attached == false) goto L5;
            return;
        L5:
            this.attached = true;
            Drawable r0 = this.drawable;
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L8;
            ((AnimatedEmojiDrawable) r0).addView(this.invalidateHolder);
        L8:
            ImageReceiver r02 = this.imageReceiver;
            if (r02 == null) goto L11;
            r02.setParentView((View) getParent());
            this.imageReceiver.onAttachedToWindow();
        L11:
            this.preloadEffectImageReceiver.onAttachedToWindow();
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if (this.attached == true) goto L5;
            return;
        L5:
            this.attached = false;
            Drawable r1 = this.drawable;
            if ((r1 instanceof AnimatedEmojiDrawable) == false) goto L10;
            ((AnimatedEmojiDrawable) r1).removeView(this.invalidateHolder);
            if (((AnimatedEmojiDrawable) this.drawable).getImageReceiver() == null) goto L10;
            ((AnimatedEmojiDrawable) this.drawable).getImageReceiver().setEmojiPaused(false);
        L10:
            ImageReceiver r12 = this.imageReceiver;
            if (r12 == null) goto L13;
            r12.onDetachedFromWindow();
            this.imageReceiver.setEmojiPaused(false);
        L13:
            this.preloadEffectImageReceiver.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
            super.onInitializeAccessibilityNodeInfo(r4);
            if (this.empty == false) goto L5;
            String r0 = LocaleController.getString(R.string.RemoveStatus);
        L21:
            if (r0 == null) goto L23;
            r4.setContentDescription(r0);
        L23:
            r4.setSelected(this.selected);
            r4.setClickable(true);
            return;
        L5:
            ReactionsLayoutInBubble.VisibleReaction r02 = this.reaction;
            if (r02 == null) goto L10;
            r0 = r02.emojicon;
            if (r0 != null) goto L21;
        L10:
            TLRPC.Document r03 = this.document;
            if (r03 != null) goto L18;
            AnimatedEmojiSpan r1 = this.span;
            if (r1 == null) goto L18;
            r03 = r1.document;
            if (r03 != null) goto L18;
            r03 = AnimatedEmojiDrawable.findDocument(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0), this.span.getDocumentId());
        L18:
            if (r03 == null) goto L20;
            r0 = MessageObject.findAnimatedEmojiEmoticon(r03, null);
            goto L21
        L20:
            r0 = null;
            goto L21
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30));
        }

        public void setAnimatedScale(float r1) {
            this.animatedScale = r1;
        }

        public void setDrawable(Drawable r3) {
            Drawable r0 = this.drawable;
            if (r0 != r3) goto L5;
            return;
        L5:
            if (this.attached == false) goto L10;
            if (r0 == null) goto L10;
            if ((r0 instanceof AnimatedEmojiDrawable) == false) goto L10;
            ((AnimatedEmojiDrawable) r0).removeView(this.invalidateHolder);
        L10:
            this.drawable = r3;
            if (this.attached == true) goto L13;
            return;
        L13:
            if ((r3 instanceof AnimatedEmojiDrawable) == false) goto L18;
            ((AnimatedEmojiDrawable) r3).addView(this.invalidateHolder);
            return;
        }

        public void setEmojicon(String r2) {
            if (TextUtils.isEmpty(r2) == false) goto L6;
            this.emojiDrawable = null;
            return;
        L6:
            this.emojiDrawable = Emoji.getEmojiDrawable(r2);
        }

        @Override // android.view.View
        public void setPressed(boolean r4) {
            if (isPressed() == r4) goto L16;
            super.setPressed(r4);
            invalidate();
            if (r4 == false) goto L9;
            ValueAnimator r0 = this.backAnimator;
            if (r0 == null) goto L9;
            r0.removeAllListeners();
            this.backAnimator.cancel();
        L9:
            if (r4 == false) goto L11;
            return;
        L11:
            if (this.pressedProgress != 0.0f) goto L13;
            return;
        L13:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 14) goto L19;
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.pressedProgress, 0.0f});
            this.backAnimator = r42;
            r42.addUpdateListener(new SelectAnimatedEmojiDialog$ImageViewEmoji$$ExternalSyntheticLambda0(this));
            this.backAnimator.addListener(new AnonymousClass1(this));
            this.backAnimator.setInterpolator(new OvershootInterpolator(5.0f));
            this.backAnimator.setDuration(350);
            this.backAnimator.start();
            return;
        L19:
            return;
        }

        public void setSticker(TLRPC.Document r13, View r14) {
            this.document = r13;
            createImageReceiver(r14);
            SvgHelper.SvgDrawable r6 = DocumentObject.getSvgThumb(r13, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
            int r142 = SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0);
            ImageReceiver r1 = this.imageReceiver;
            if (r142 != 6) goto L10;
            ImageLocation r2 = ImageLocation.getForDocument(r13);
            if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD) == true) goto L8;
            String r143 = "34_34_firstframe";
        L7:
            String r3 = r143;
            r1.setImage(r2, r3, null, null, r6, r13.size, null, r13, 0);
        L11:
            this.isStaticIcon = true;
            this.span = null;
            return;
        L8:
            r143 = "34_34";
            goto L7
        L10:
            r1.setImage(ImageLocation.getForDocument(r13), "100_100_firstframe", null, null, r6, 0, "tgs", r13, 0);
            goto L11
        }

        public void setViewSelected(boolean r3, boolean r4) {
            if (this.selected == r3) goto L15;
            this.selected = r3;
            if (r4 == true) goto L16;
            float r42 = 0.0f;
            if (r3 == false) goto L9;
            float r1 = 1.0f;
        L10:
            this.selectedProgressT = r1;
            if (r3 == false) goto L13;
            r42 = 1.0f;
        L13:
            this.selectedProgress = r42;
            return;
        L9:
            r1 = 0.0f;
            goto L10
        L16:
            return;
        }

        public void setViewSelectedWithScale(boolean r4, boolean r5) {
            if (this.selected == true) goto L10;
            if (r4 == false) goto L10;
            if (r5 == false) goto L10;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 14) goto L10;
            this.shouldSelected = true;
            this.selectedProgress = 1.0f;
            this.selectedProgressT = 1.0f;
            cancelBackAnimator();
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{this.pressedProgress, 1.6f, 0.7f});
            this.backAnimator = r42;
            r42.addUpdateListener(new SelectAnimatedEmojiDialog$ImageViewEmoji$$ExternalSyntheticLambda2(this));
            this.backAnimator.addListener(new AnonymousClass3(this));
            this.backAnimator.setInterpolator(new LinearInterpolator());
            this.backAnimator.setDuration(200);
            this.backAnimator.start();
            return;
        L10:
            this.shouldSelected = false;
            setViewSelected(r4, r5);
        }

        public void unselectWithScale() {
            if (this.selected == true) goto L5;
            return;
        L5:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 14) goto L9;
            cancelBackAnimator();
            this.pressedProgress = 1.0f;
            ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            this.backAnimator = r0;
            r0.addUpdateListener(new SelectAnimatedEmojiDialog$ImageViewEmoji$$ExternalSyntheticLambda3(this));
            this.backAnimator.addListener(new AnonymousClass2(this));
            this.backAnimator.setInterpolator(new OvershootInterpolator(5.0f));
            this.backAnimator.setDuration(350);
            this.backAnimator.start();
            setViewSelected(false, true);
            return;
        }

        public void update(long r3) {
            ImageReceiver r0 = this.imageReceiverToDraw;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getLottieAnimation() == null) goto L8;
            this.imageReceiverToDraw.getLottieAnimation().updateCurrentFrame(r3, true);
        L8:
            if (this.imageReceiverToDraw.getAnimation() == null) goto L12;
            this.imageReceiverToDraw.getAnimation().updateCurrentFrame(r3, true);
            return;
        }

        public void updatePressedProgress() {
            if (isPressed() == true) goto L5;
            return;
        L5:
            if (this.pressedProgress != 1.0f) goto L7;
            return;
        L7:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 14) goto L12;
            this.pressedProgress = Utilities.clamp(this.pressedProgress + 0.16f, 1.0f, 0.0f);
            invalidate();
            return;
        }
    }

    /* loaded from: classes6.dex */
    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private int count;
        int emojiHeaderRow;
        int emojiStartRow;
        private ArrayList<Integer> rowHashCodes;
        int setsStartRow;
        int stickersHeaderRow;
        int stickersStartRow;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass1(SearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
            }
        }

        private SearchAdapter(SelectAnimatedEmojiDialog r1) {
            this.this$0 = r1;
            this.emojiHeaderRow = -1;
            this.stickersHeaderRow = -1;
            this.count = 1;
            this.rowHashCodes = new ArrayList();
        }

        public /* synthetic */ SearchAdapter(SelectAnimatedEmojiDialog r1, SelectAnimatedEmojiDialogIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.count;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r9) {
            if (r9 != this.emojiHeaderRow) goto L5;
        L43:
            return 6;
        L5:
            if (r9 == this.stickersHeaderRow) goto L43;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 14) goto L17;
            if (SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0) == null) goto L23;
            int r0 = this.stickersStartRow;
            if (r9 < r0) goto L23;
            if ((r9 - r0) >= SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).size()) goto L23;
            return 4;
        L23:
            if (SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0) != null) goto L26;
            return 3;
        L26:
            if (r9 > this.emojiStartRow) goto L28;
        L34:
            int r02 = this.setsStartRow;
            if ((r9 - r02) >= 0) goto L37;
        L42:
            return 4;
        L37:
            if ((r9 - r02) >= SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).size()) goto L42;
            if ((SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).get(r9 - this.setsStartRow) instanceof SetTitleDocument) == false) goto L41;
            return 6;
        L41:
            return 3;
        L28:
            if (((r9 - r0) - 1) >= SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).size()) goto L34;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 13) goto L32;
        L33:
            return 3;
        L32:
            if (((ReactionsLayoutInBubble.VisibleReaction) SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).get((r9 - this.emojiStartRow) - 1)).documentId == 0) goto L34;
        L17:
            if (r9 <= this.stickersStartRow) goto L23;
            if (((r9 - r0) - 1) >= SelectAnimatedEmojiDialog.m18695$$Nest$fgetstickersSearchResult(this.this$0).size()) goto L23;
            return 5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 3) goto L5;
            return true;
        L5:
            if (r2.getItemViewType() == 4) goto L11;
            return false;
        L11:
            return true;
        }

        public boolean isSticker(int r5) {
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 14) goto L5;
            int r0 = this.stickersStartRow;
            if (r5 > r0) goto L15;
        L17:
            return false;
        L15:
            if (((r5 - r0) - 1) >= SelectAnimatedEmojiDialog.m18695$$Nest$fgetstickersSearchResult(this.this$0).size()) goto L17;
            return true;
        L5:
            if (SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0) == null) goto L11;
            int r02 = this.stickersStartRow;
            if (r5 < r02) goto L11;
            if ((r5 - r02) >= SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).size()) goto L11;
            return true;
        L11:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r25, int r26) {
            if (r25.getItemViewType() != 6) goto L23;
            HeaderView r1 = (HeaderView) r25.itemView;
            if (SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0) == null) goto L14;
            int r3 = this.setsStartRow;
            if ((r26 - r3) < 0) goto L14;
            if ((r26 - r3) >= SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).size()) goto L14;
            TLRPC.Document r2 = (TLRPC.Document) SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).get(r26 - this.setsStartRow);
            if ((r2 instanceof SetTitleDocument) == false) goto L20;
            r1.setText(((SetTitleDocument) r2).title, SelectAnimatedEmojiDialog.m18658$$Nest$fgetlastQuery(this.this$0), false);
        L20:
            r1.closeIcon.setVisibility(8);
            return;
        L14:
            if (r26 != this.emojiHeaderRow) goto L17;
            r1.setText(LocaleController.getString(R.string.Emoji), false);
            goto L20
        L17:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 14) goto L19;
            r1.setText(LocaleController.getString(R.string.StickerEffects), false);
            goto L20
        L19:
            r1.setText(LocaleController.getString(R.string.AccDescrStickers), false);
            goto L20
        L23:
            if (r25.getItemViewType() != 5) goto L27;
            TLRPC.Document r22 = (TLRPC.Document) SelectAnimatedEmojiDialog.m18695$$Nest$fgetstickersSearchResult(this.this$0).get((r26 - this.stickersStartRow) - 1);
            ImageViewEmoji r12 = (ImageViewEmoji) r25.itemView;
            r12.createImageReceiver(this.this$0.emojiSearchGridView);
            r12.imageReceiver.setImage(ImageLocation.getForDocument(r22), "100_100_firstframe", null, null, DocumentObject.getSvgThumb(r22, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f), 0, "tgs", r22, 0);
            r12.isStaticIcon = true;
            r12.document = r22;
            r12.span = null;
            return;
        L27:
            if (r25.getItemViewType() != 4) goto L89;
            ImageViewEmoji r13 = (ImageViewEmoji) r25.itemView;
            r13.position = r26;
            if (SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0) == null) goto L35;
            if (r26 < 0) goto L35;
            if (r26 >= SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).size()) goto L35;
            ReactionsLayoutInBubble.VisibleReaction r23 = (ReactionsLayoutInBubble.VisibleReaction) SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).get(r26);
        L42:
            if (r13.imageReceiver != null) goto L44;
            ImageReceiver r32 = new ImageReceiver(r13);
            r13.imageReceiver = r32;
            r32.setLayerNum(7);
            r13.imageReceiver.onAttachedToWindow();
        L44:
            r13.imageReceiver.setParentView(this.this$0.emojiSearchGridView);
            r13.reaction = r23;
            r13.isFirstReactions = false;
            r13.setViewSelected(this.this$0.selectedReactions.contains(r23), false);
            r13.notDraw = false;
            r13.invalidate();
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 13) goto L48;
            r13.setDrawable(Emoji.getEmojiDrawable(r23.emojicon));
        L71:
            if (UserConfig.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).isPremium() == true) goto L81;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 14) goto L81;
            if (r23.isEffect == false) goto L81;
            if (r23.premium == false) goto L81;
            r13.createPremiumLockView();
            r13.premiumLockIconView.setVisibility(0);
            r13.setEmojicon(null);
            return;
        L81:
            if (r23.sticker == false) goto L83;
            r13.setEmojicon(r23.emojicon);
        L84:
            PremiumLockIconView r0 = r13.premiumLockIconView;
            if (r0 == null) goto L138;
            r0.setVisibility(4);
            return;
        L138:
            return;
        L83:
            r13.setEmojicon(null);
            goto L84
        L48:
            if (r23.isEffect == false) goto L50;
        L66:
            r13.isDefaultReaction = false;
            r13.span = new AnimatedEmojiSpan(r23.documentId, null);
            r13.document = null;
            r13.imageReceiver.clearImage();
            r13.preloadEffectImageReceiver.clearImage();
            AnimatedEmojiDrawable r33 = (AnimatedEmojiDrawable) EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiSearchGridView).get(r13.span.getDocumentId());
            if (r33 != null) goto L69;
            r33 = AnimatedEmojiDrawable.make(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0), SelectAnimatedEmojiDialog.m18715$$Nest$mgetCacheType(this.this$0), r13.span.getDocumentId());
            EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiSearchGridView).put(r13.span.getDocumentId(), r33);
        L69:
            r13.setDrawable(r33);
            goto L71
        L50:
            if (r23.emojicon == null) goto L66;
            r13.isDefaultReaction = true;
            TLRPC.TL_availableReaction r34 = MediaDataController.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).getReactionsMap().get(r23.emojicon);
            if (r34 == null) goto L58;
            SvgHelper.SvgDrawable r16 = DocumentObject.getSvgThumb(r34.activate_animation, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
            boolean r8 = LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS);
            ImageReceiver r11 = r13.imageReceiver;
            if (r8 == true) goto L56;
            r11.setImage(ImageLocation.getForDocument(r34.select_animation), "60_60_firstframe", null, null, r16, 0, "tgs", r23, 0);
        L57:
            MediaDataController.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).preloadImage(r13.preloadEffectImageReceiver, ImageLocation.getForDocument(r34.around_animation), ReactionsEffectOverlay.getFilterForAroundAnimation());
        L59:
            r13.span = null;
            r13.document = null;
            r13.setDrawable(null);
            PremiumLockIconView r82 = r13.premiumLockIconView;
            if (r82 == null) goto L62;
            r82.setVisibility(8);
            r13.premiumLockIconView.setImageReceiver(null);
        L62:
            if (r34 != null) goto L71;
            if (r23.isEffect == false) goto L71;
            r13.setDrawable(Emoji.getEmojiDrawable(r23.emojicon));
            goto L71
        L56:
            ReactionsLayoutInBubble.VisibleReaction r20 = r23;
            r11.setImage(ImageLocation.getForDocument(r34.select_animation), "60_60_pcache", ImageLocation.getForDocument(r34.select_animation), "30_30_firstframe", null, null, r16, 0, "tgs", r20, 0);
            r23 = r20;
            goto L57
        L58:
            r13.imageReceiver.clearImage();
            r13.preloadEffectImageReceiver.clearImage();
        L35:
            if (SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0) == null) goto L135;
            int r35 = this.stickersStartRow;
            if (r26 >= r35) goto L39;
            return;
        L39:
            if ((r26 - r35) >= SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).size()) goto L137;
            r23 = (ReactionsLayoutInBubble.VisibleReaction) SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).get(r26 - this.stickersStartRow);
            goto L42
        L137:
            return;
        L135:
            return;
        L89:
            if (r25.getItemViewType() != 3) goto L139;
            ImageViewEmoji r14 = (ImageViewEmoji) r25.itemView;
            r14.empty = false;
            r14.position = r26;
            r14.setPadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
            r14.setDrawable(null);
            if (SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0) == null) goto L113;
            if (r26 < 0) goto L113;
            if (r26 >= SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).size()) goto L113;
            ReactionsLayoutInBubble.VisibleReaction r24 = (ReactionsLayoutInBubble.VisibleReaction) SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).get(r26);
            r14.reaction = r24;
            long r112 = r24.documentId;
            SelectAnimatedEmojiDialog r5 = this.this$0;
            if (r112 != 0) goto L99;
            boolean r02 = r5.selectedReactions.contains(r24);
            r14.isFirstReactions = true;
            r14.setDrawable(Emoji.getEmojiDrawable(r24.emojicon));
            r14.setViewSelected(r02, false);
            return;
        L99:
            Long r36 = Long.valueOf(r112);
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(r5) == 14) goto L102;
        L108:
            PremiumLockIconView r27 = r14.premiumLockIconView;
            if (r27 == null) goto L111;
            r27.setVisibility(4);
        L111:
            TLRPC.Document r28 = null;
        L122:
            if (r36 != null) goto L126;
            if (r28 != null) goto L126;
            boolean r29 = false;
        L133:
            r14.setViewSelected(r29, false);
            return;
        L126:
            if (r28 == null) goto L128;
            r14.span = new AnimatedEmojiSpan(r28, null);
            r14.document = r28;
            r29 = this.this$0.selectedDocumentIds.contains(Long.valueOf(r28.id));
        L129:
            AnimatedEmojiDrawable r37 = (AnimatedEmojiDrawable) EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiSearchGridView).get(r14.span.getDocumentId());
            if (r37 != null) goto L132;
            r37 = AnimatedEmojiDrawable.make(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0), SelectAnimatedEmojiDialog.m18715$$Nest$mgetCacheType(this.this$0), r14.span.getDocumentId());
            EmojiListView.m18725$$Nest$fgetanimatedEmojiDrawables(this.this$0.emojiSearchGridView).put(r14.span.getDocumentId(), r37);
        L132:
            r14.setDrawable(r37);
            goto L133
        L128:
            AnimatedEmojiSpan r210 = new AnimatedEmojiSpan(r36.longValue(), null);
            r14.span = r210;
            r14.document = r210.document;
            r29 = this.this$0.selectedDocumentIds.contains(r36);
            goto L129
        L102:
            if (UserConfig.getInstance(SelectAnimatedEmojiDialog.m18642$$Nest$fgetcurrentAccount(this.this$0)).isPremium() == true) goto L108;
            if (r24.isEffect == false) goto L108;
            if (r24.premium == false) goto L108;
            r14.createPremiumLockView();
            r14.premiumLockIconView.setVisibility(0);
        L113:
            if (SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0) == null) goto L120;
            int r38 = this.setsStartRow;
            if ((r26 - r38) < 0) goto L120;
            if ((r26 - r38) >= SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).size()) goto L120;
            r28 = (TLRPC.Document) SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).get(r26 - this.setsStartRow);
            if ((r28 instanceof SetTitleDocument) == true) goto L120;
            r36 = null;
        L120:
            r28 = null;
            r36 = null;
            goto L122
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 != 6) goto L10;
            SelectAnimatedEmojiDialog r0 = this.this$0;
            Context r1 = r0.getContext();
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 6) goto L7;
            boolean r42 = true;
        L8:
            View r52 = new HeaderView(r0, r1, r42);
        L14:
            if (SelectAnimatedEmojiDialog.m18714$$Nest$menterAnimationInProgress(this.this$0) == false) goto L17;
            r52.setScaleX(0.0f);
            r52.setScaleY(0.0f);
        L17:
            return new RecyclerListView.Holder(r52);
        L7:
            r42 = false;
            goto L8
        L10:
            if (r5 != 7) goto L12;
            r52 = new AnonymousClass1(this, this.this$0.getContext());
            r52.setTag("searchbox");
            goto L14
        L12:
            SelectAnimatedEmojiDialog r43 = this.this$0;
            r52 = new ImageViewEmoji(r43, r43.getContext());
            goto L14
        }

        public void updateRows(boolean r9) {
            if (SelectAnimatedEmojiDialog.m18656$$Nest$fgetisAttached(this.this$0) == false) goto L5;
            SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0);
        L5:
            new ArrayList(this.rowHashCodes);
            this.setsStartRow = -1;
            this.stickersStartRow = -1;
            boolean r1 = false;
            this.count = 0;
            this.rowHashCodes.clear();
            if (SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0) != null) goto L8;
        L16:
            int r3 = SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0);
            SelectAnimatedEmojiDialog r5 = this.this$0;
            if (r3 != 14) goto L27;
            if (SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(r5) == null) goto L38;
            if (SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).isEmpty() == true) goto L38;
            int r2 = this.count;
            this.count = r2 + 1;
            this.stickersHeaderRow = r2;
            this.rowHashCodes.add(2);
            this.stickersStartRow = this.count;
            int r0 = 0;
        L24:
            if (r0 >= SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).size()) goto L38;
            this.count++;
            this.rowHashCodes.add(Integer.valueOf(Objects.hash(new Object[]{-7453, SelectAnimatedEmojiDialog.m18684$$Nest$fgetsearchResultStickers(this.this$0).get(r0)})));
            r0 = r0 + 1;
        L38:
            if (SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0) == null) goto L40;
            int r92 = this.count;
            this.setsStartRow = r92;
            this.count = r92 + SelectAnimatedEmojiDialog.m18686$$Nest$fgetsearchSets(this.this$0).size();
        L40:
            notifyDataSetChanged();
            SelectAnimatedEmojiDialog r93 = this.this$0;
            if (r93.searched == true) goto L43;
        L45:
            r93.switchSearchEmptyView(r1);
            return;
        L43:
            if (this.count != 0) goto L45;
            r1 = true;
            goto L45
        L27:
            if (SelectAnimatedEmojiDialog.m18695$$Nest$fgetstickersSearchResult(r5) == null) goto L38;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 4) goto L31;
        L33:
            this.stickersStartRow = this.count;
            int r02 = 0;
        L35:
            if (r02 >= SelectAnimatedEmojiDialog.m18695$$Nest$fgetstickersSearchResult(this.this$0).size()) goto L38;
            this.count++;
            this.rowHashCodes.add(Integer.valueOf(Objects.hash(new Object[]{-7453, SelectAnimatedEmojiDialog.m18695$$Nest$fgetstickersSearchResult(this.this$0).get(r02)})));
            r02 = r02 + 1;
            goto L35
        L31:
            if (SelectAnimatedEmojiDialog.m18695$$Nest$fgetstickersSearchResult(this.this$0).isEmpty() == true) goto L33;
            int r22 = this.count;
            this.count = r22 + 1;
            this.stickersHeaderRow = r22;
            this.rowHashCodes.add(2);
            goto L33
        L8:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 4) goto L10;
        L12:
            this.emojiStartRow = this.count;
            int r32 = 0;
        L14:
            if (r32 >= SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).size()) goto L16;
            this.count++;
            this.rowHashCodes.add(Integer.valueOf(Objects.hash(new Object[]{-4342, SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).get(r32)})));
            r32 = r32 + 1;
            goto L14
        L10:
            if (SelectAnimatedEmojiDialog.m18683$$Nest$fgetsearchResult(this.this$0).isEmpty() == true) goto L12;
            int r33 = this.count;
            this.count = r33 + 1;
            this.emojiHeaderRow = r33;
            this.rowHashCodes.add(1);
            goto L12
        }
    }

    /* loaded from: classes6.dex */
    public class SearchBox extends FrameLayout implements Theme.Colorable {
        private FrameLayout box;
        private StickerCategoriesListView categoriesListView;
        private ImageView clear;
        private Runnable delayedToggle;
        private EditTextCaption input;
        private FrameLayout inputBox;
        private View inputBoxGradient;
        private float inputBoxGradientAlpha;
        private ValueAnimator inputBoxGradientAnimator;
        private boolean inputBoxShown;
        private ImageView search;
        private SearchStateDrawable searchStateDrawable;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        private boolean useCustomBackground;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SearchBox$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            Paint fadePaint;
            final /* synthetic */ SearchBox this$1;
            final /* synthetic */ boolean val$drawBackground;
            final /* synthetic */ SelectAnimatedEmojiDialog val$this$0;

            public AnonymousClass1(SearchBox r1, Context r2, SelectAnimatedEmojiDialog r3, boolean r4) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                this.val$drawBackground = r4;
                super(r2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r17) {
                if (this.val$drawBackground == false) goto L5;
            L11:
                super.dispatchDraw(r17);
                return;
            L5:
                if (SearchBox.m18734$$Nest$fgetinputBoxGradientAlpha(this.this$1) <= 0.0f) goto L11;
                if (this.fadePaint != null) goto L9;
                Paint r1 = new Paint();
                this.fadePaint = r1;
                r1.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(18.0f), 0.0f, new int[]{-1, 0}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
                this.fadePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            L9:
                r17.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), 255, 31);
                super.dispatchDraw(r17);
                this.fadePaint.setAlpha((int) (SearchBox.m18734$$Nest$fgetinputBoxGradientAlpha(this.this$1) * 255.0f));
                r17.drawRect(0.0f, 0.0f, AndroidUtilities.dp(18.0f), getMeasuredHeight(), this.fadePaint);
                r17.restore();
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SearchBox$2, reason: invalid class name */
        public class AnonymousClass2 extends EditTextCaption {
            final /* synthetic */ SearchBox this$1;
            final /* synthetic */ SelectAnimatedEmojiDialog val$this$0;

            public static /* synthetic */ void $r8$lambda$RAKuNuFuMHj6xtmzQ85BvDJNhxc(AnonymousClass2 r0) {
                r0.lambda$onFocusChanged$1();
            }

            public static /* synthetic */ void $r8$lambda$WeunZuCoCtMrYKqn9lPE_HrAAPM(AnonymousClass2 r0) {
                r0.lambda$onTouchEvent$0();
            }

            public AnonymousClass2(SearchBox r1, Context r2, Theme.ResourcesProvider r3, SelectAnimatedEmojiDialog r4) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                super(r2, r3);
            }

            private /* synthetic */ void lambda$onFocusChanged$1() {
                AndroidUtilities.showKeyboard(SearchBox.m18733$$Nest$fgetinput(this.this$1));
            }

            private /* synthetic */ void lambda$onTouchEvent$0() {
                requestFocus();
            }

            @Override // android.view.View
            public void invalidate() {
                if (HwEmojis.isHwEnabled() == false) goto L5;
                return;
            L5:
                super.invalidate();
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public void onFocusChanged(boolean r4, int r5, Rect r6) {
                if (r4 == false) goto L4;
                this.this$1.this$0.onInputFocus();
                AndroidUtilities.runOnUIThread(new SelectAnimatedEmojiDialog$SearchBox$2$$ExternalSyntheticLambda0(this), 200);
            L4:
                super.onFocusChanged(r4, r5, r6);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public boolean onTouchEvent(MotionEvent r3) {
                if (r3.getAction() != 1) goto L9;
                if (this.this$1.this$0.prevWindowKeyboardVisible() == false) goto L9;
                AndroidUtilities.runOnUIThread(new SelectAnimatedEmojiDialog$SearchBox$2$$ExternalSyntheticLambda1(this), 200);
                return false;
            L9:
                return super.onTouchEvent(r3);
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SearchBox$3, reason: invalid class name */
        public class AnonymousClass3 implements TextWatcher {
            final /* synthetic */ SearchBox this$1;
            final /* synthetic */ SelectAnimatedEmojiDialog val$this$0;

            public AnonymousClass3(SearchBox r1, SelectAnimatedEmojiDialog r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r3) {
                if (SearchBox.m18733$$Nest$fgetinput(this.this$1).getText() != null) goto L5;
            L8:
                String r32 = null;
            L9:
                this.this$1.this$0.search(r32);
                if (SearchBox.m18732$$Nest$fgetcategoriesListView(this.this$1) == null) goto L13;
                SearchBox.m18732$$Nest$fgetcategoriesListView(this.this$1).selectCategory(null);
                SearchBox.m18732$$Nest$fgetcategoriesListView(this.this$1).updateCategoriesShown(TextUtils.isEmpty(r32), true);
            L13:
                if (SearchBox.m18733$$Nest$fgetinput(this.this$1) == null) goto L15;
                SearchBox.m18733$$Nest$fgetinput(this.this$1).clearAnimation();
                SearchBox.m18733$$Nest$fgetinput(this.this$1).animate().translationX(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            L15:
                SearchBox.m18735$$Nest$mshowInputBoxGradient(this.this$1, false);
                return;
            L5:
                if (AndroidUtilities.trim(SearchBox.m18733$$Nest$fgetinput(this.this$1).getText(), null).length() == 0) goto L8;
                r32 = SearchBox.m18733$$Nest$fgetinput(this.this$1).getText().toString();
                goto L9
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SearchBox$4, reason: invalid class name */
        public class AnonymousClass4 extends CloseProgressDrawable2 {
            final /* synthetic */ SearchBox this$1;
            final /* synthetic */ SelectAnimatedEmojiDialog val$this$0;

            public AnonymousClass4(SearchBox r1, float r2, SelectAnimatedEmojiDialog r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                setSide(AndroidUtilities.dp(7.0f));
            }

            @Override // org.telegram.ui.Components.CloseProgressDrawable2
            public int getCurrentColor() {
                return Theme.getColor(Theme.key_chat_emojiSearchIcon, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(this.this$1.this$0));
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SearchBox$5, reason: invalid class name */
        public class AnonymousClass5 extends StickerCategoriesListView {
            final /* synthetic */ SearchBox this$1;

            public AnonymousClass5(SearchBox r1, Context r2, int r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.StickerCategoriesListView
            public boolean isTabIconsAnimationEnabled(boolean r1) {
                if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_KEYBOARD) == false) goto L5;
                return true;
            L5:
                if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$1.this$0) == 4) goto L11;
                return false;
            L11:
                return true;
            }

            @Override // org.telegram.ui.Components.StickerCategoriesListView
            public void selectCategory(int r1) {
                super.selectCategory(r1);
                SearchBox.m18737$$Nest$mupdateButton(this.this$1);
            }
        }

        public static /* synthetic */ void $r8$lambda$7QDcN80Fmt0rwdxSnLq7qa6LMV4(SearchBox r0, StickerCategoriesListView.EmojiCategory r1) {
            r0.lambda$createCategoriesListView$4(r1);
        }

        /* renamed from: $r8$lambda$85hSHurtZAX-b9CAACcDfQGBvL8, reason: not valid java name */
        public static /* synthetic */ void m18730$r8$lambda$85hSHurtZAXb9CAACcDfQGBvL8(SearchBox r0) {
            r0.lambda$toggleClear$5();
        }

        public static /* synthetic */ void $r8$lambda$JVoITPGMAExPzlmvxJ5IzbX3Ygg(SearchBox r0, View r1) {
            r0.lambda$new$2(r1);
        }

        /* renamed from: $r8$lambda$PXJp4kATBJ6EUL5YG-Nqohh420s, reason: not valid java name */
        public static /* synthetic */ void m18731$r8$lambda$PXJp4kATBJ6EUL5YGNqohh420s(SearchBox r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$SPoPpWQBFKqlOVEWGHkBf0cgFbk(SearchBox r0, Integer r1) {
            r0.lambda$createCategoriesListView$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$ds0ROq9HHOZAJGdU0ZYeB8W_8rg(SearchBox r0, ValueAnimator r1) {
            r0.lambda$showInputBoxGradient$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$xtaQBYU4PxDJpypTBOWjZJJ7pLs(SearchBox r0, View r1) {
            r0.lambda$new$1(r1);
        }

        /* renamed from: -$$Nest$fgetcategoriesListView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickerCategoriesListView m18732$$Nest$fgetcategoriesListView(SearchBox r0) {
            return r0.categoriesListView;
        }

        /* renamed from: -$$Nest$fgetinput, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextCaption m18733$$Nest$fgetinput(SearchBox r0) {
            return r0.input;
        }

        /* renamed from: -$$Nest$fgetinputBoxGradientAlpha, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m18734$$Nest$fgetinputBoxGradientAlpha(SearchBox r0) {
            return r0.inputBoxGradientAlpha;
        }

        /* renamed from: -$$Nest$mshowInputBoxGradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18735$$Nest$mshowInputBoxGradient(SearchBox r0, boolean r1) {
            r0.showInputBoxGradient(r1);
        }

        /* renamed from: -$$Nest$mtoggleClear, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18736$$Nest$mtoggleClear(SearchBox r0, boolean r1) {
            r0.toggleClear(r1);
        }

        /* renamed from: -$$Nest$mupdateButton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18737$$Nest$mupdateButton(SearchBox r0) {
            r0.updateButton();
        }

        public SearchBox(SelectAnimatedEmojiDialog r21, Context r22, boolean r23) {
            this.this$0 = r21;
            super(r22);
            this.inputBoxShown = false;
            setClickable(true);
            this.box = new FrameLayout(r22);
            if (r23 == false) goto L5;
            setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21)));
        L5:
            FrameLayout r6 = this.box;
            int r8 = AndroidUtilities.dp(18.0f);
            int r9 = Theme.key_chat_emojiPanelBackground;
            r6.setBackground(Theme.createRoundRectDrawable(r8, Theme.getColor(r9, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21))));
            this.box.setClipToOutline(true);
            this.box.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(18.0f)));
            addView(this.box, LayoutHelper.createFrame(-1, 36.0f, 55, 8.0f, 12.0f, 8.0f, 8.0f));
            ImageView r62 = new ImageView(r22);
            this.search = r62;
            ImageView.ScaleType r7 = ImageView.ScaleType.CENTER;
            r62.setScaleType(r7);
            SearchStateDrawable r63 = new SearchStateDrawable();
            this.searchStateDrawable = r63;
            r63.setIconState(0, false);
            SearchStateDrawable r64 = this.searchStateDrawable;
            int r82 = Theme.key_chat_emojiSearchIcon;
            r64.setColor(Theme.getColor(r82, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21)));
            this.search.setImageDrawable(this.searchStateDrawable);
            this.search.setOnClickListener(new SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda0(this));
            this.search.setClickable(false);
            this.search.setImportantForAccessibility(2);
            this.box.addView(this.search, LayoutHelper.createFrame(36, 36, 51));
            AnonymousClass1 r65 = new AnonymousClass1(this, r22, r21, r23);
            this.inputBox = r65;
            this.box.addView(r65, LayoutHelper.createFrame(-1, -1.0f, 119, 36.0f, 0.0f, 0.0f, 0.0f));
            AnonymousClass2 r66 = new AnonymousClass2(this, r22, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21), r21);
            this.input = r66;
            r66.addTextChangedListener(new AnonymousClass3(this, r21));
            this.input.setBackground(null);
            this.input.setPadding(0, 0, AndroidUtilities.dp(4.0f), 0);
            this.input.setTextSize(1, 16.0f);
            this.input.setHint(LocaleController.getString(R.string.Search));
            this.input.setHintTextColor(Theme.getColor(r82, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21)));
            this.input.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21)));
            this.input.setImeOptions(268435459);
            this.input.setCursorColor(Theme.getColor(Theme.key_featuredStickers_addedIcon, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21)));
            this.input.setCursorSize(AndroidUtilities.dp(20.0f));
            this.input.setGravity(19);
            this.input.setCursorWidth(1.5f);
            this.input.setMaxLines(1);
            this.input.setSingleLine(true);
            this.input.setLines(1);
            this.input.setTranslationY(AndroidUtilities.dp(-1.0f));
            this.inputBox.addView(this.input, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 32.0f, 0.0f));
            if (r23 == false) goto L8;
            this.inputBoxGradient = new View(r22);
            Drawable r3 = r22.getResources().getDrawable(R.drawable.gradient_right).mutate();
            r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r9, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21)), PorterDuff.Mode.MULTIPLY));
            this.inputBoxGradient.setBackground(r3);
            this.inputBoxGradient.setAlpha(0.0f);
            this.inputBox.addView(this.inputBoxGradient, LayoutHelper.createFrame(18, -1, 3));
        L8:
            setOnClickListener(new SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda1(this));
            ImageView r32 = new ImageView(r22);
            this.clear = r32;
            r32.setScaleType(r7);
            this.clear.setImageDrawable(new AnonymousClass4(this, 1.25f, r21));
            this.clear.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(r21)), 1, AndroidUtilities.dp(15.0f)));
            this.clear.setAlpha(0.0f);
            this.clear.setOnClickListener(new SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda2(this));
            this.box.addView(this.clear, LayoutHelper.createFrame(36, 36, 53));
            if (HwEmojis.isFirstOpen() == true) goto L12;
            createCategoriesListView();
            return;
        }

        private void createCategoriesListView() {
            if (this.categoriesListView == null) goto L5;
            return;
        L5:
            if (getContext() == null) goto L39;
            int r2 = 2;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 1) goto L10;
        L26:
            int r0 = SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0);
            if (r0 == 0) goto L31;
            if (r0 == 4) goto L32;
            if (r0 == 12) goto L31;
            r2 = 0;
        L32:
            AnonymousClass5 r02 = new AnonymousClass5(this, getContext(), r2, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(this.this$0));
            this.categoriesListView = r02;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) != 4) goto L35;
            float r1 = 6.5f;
        L36:
            r02.setShownButtonsAtStart(r1);
            this.categoriesListView.setDontOccupyWidth((int) this.input.getPaint().measureText(this.input.getHint() + _UrlKt.FRAGMENT_ENCODE_SET));
            this.categoriesListView.setOnScrollIntoOccupiedWidth(new SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda3(this));
            this.categoriesListView.setOnCategoryClick(new SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda4(this));
            this.box.addView(this.categoriesListView, LayoutHelper.createFrame(-1, -1.0f, 119, 36.0f, 0.0f, 0.0f, 0.0f));
            return;
        L35:
            r1 = 4.5f;
        L31:
            r2 = 1;
            goto L32
        L10:
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 11) goto L26;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 2) goto L26;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 0) goto L26;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 12) goto L26;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 4) goto L26;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 10) goto L26;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 9) goto L26;
            if (SelectAnimatedEmojiDialog.m18704$$Nest$fgettype(this.this$0) == 14) goto L26;
            return;
        }

        private /* synthetic */ void lambda$createCategoriesListView$3(Integer r4) {
            boolean r2 = false;
            this.input.setTranslationX(-Math.max(0, r4.intValue()));
            if (r4.intValue() <= 0) goto L5;
            r2 = true;
        L5:
            showInputBoxGradient(r2);
            updateButton();
        }

        private /* synthetic */ void lambda$createCategoriesListView$4(StickerCategoriesListView.EmojiCategory r4) {
            StickerCategoriesListView.EmojiCategory r0 = this.categoriesListView.getSelectedCategory();
            SelectAnimatedEmojiDialog r1 = this.this$0;
            if (r0 != r4) goto L6;
            r1.search(null, false, false);
            this.categoriesListView.selectCategory(null);
            return;
        L6:
            r1.search(r4.emojis, false, false);
            this.categoriesListView.selectCategory(r4);
        }

        private /* synthetic */ void lambda$new$0(View r4) {
            if (this.searchStateDrawable.getIconState() != 1) goto L9;
            this.input.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.this$0.search(null, true, false);
            StickerCategoriesListView r42 = this.categoriesListView;
            if (r42 == null) goto L7;
            r42.selectCategory(null);
            this.categoriesListView.updateCategoriesShown(true, true);
            this.categoriesListView.scrollToStart();
        L7:
            this.input.clearAnimation();
            this.input.animate().translationX(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            showInputBoxGradient(false);
            return;
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            if (this.this$0.prevWindowKeyboardVisible() == false) goto L5;
            return;
        L5:
            this.this$0.onInputFocus();
            this.input.requestFocus();
            SelectAnimatedEmojiDialog.m18718$$Nest$mscrollToPosition(this.this$0, 0, 0);
        }

        private /* synthetic */ void lambda$new$2(View r4) {
            this.input.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.this$0.search(null, true, false);
            StickerCategoriesListView r42 = this.categoriesListView;
            if (r42 == null) goto L5;
            r42.selectCategory(null);
            this.categoriesListView.updateCategoriesShown(true, true);
        L5:
            this.input.clearAnimation();
            this.input.animate().translationX(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            showInputBoxGradient(false);
        }

        private /* synthetic */ void lambda$showInputBoxGradient$6(ValueAnimator r2) {
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

        private /* synthetic */ void lambda$toggleClear$5() {
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
            r43.addUpdateListener(new SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda6(this));
            this.inputBoxGradientAnimator.setDuration(120);
            this.inputBoxGradientAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.inputBoxGradientAnimator.start();
            return;
        L11:
            r42 = 0.0f;
            goto L12
        }

        private void toggleClear(boolean r3) {
            Runnable r0 = this.delayedToggle;
            if (r3 == false) goto L7;
            if (r0 != null) goto L11;
            SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda5 r32 = new SelectAnimatedEmojiDialog$SearchBox$$ExternalSyntheticLambda5(this);
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

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v3 */
        /* JADX WARN: Type inference failed for: r4v4, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r4v6 */
        private void updateButton(boolean r4) {
            if (isInProgress() == true) goto L5;
        L13:
            int r0 = 1;
            if (this.input.length() > 0) goto L25;
            StickerCategoriesListView r42 = this.categoriesListView;
            if (r42 != null) goto L18;
        L24:
            ?? r43 = 0;
        L26:
            this.searchStateDrawable.setIconState(r43);
            this.search.setClickable(r43);
            ImageView r1 = this.search;
            if (r43 == 0) goto L29;
            String r2 = LocaleController.getString(R.string.AccDescrGoBack);
        L30:
            r1.setContentDescription(r2);
            ImageView r3 = this.search;
            if (r43 != 0) goto L34;
            r0 = 2;
        L34:
            r3.setImportantForAccessibility(r0);
            return;
        L29:
            r2 = null;
            goto L30
        L18:
            if (r42.isCategoriesShown() == false) goto L24;
            if (this.categoriesListView.isScrolledIntoOccupiedWidth() == true) goto L25;
            if (this.categoriesListView.getSelectedCategory() == null) goto L24;
        L25:
            r43 = 1;
            goto L26
        L5:
            if (this.input.length() != 0) goto L10;
            StickerCategoriesListView r02 = this.categoriesListView;
            if (r02 == null) goto L13;
            if (r02.getSelectedCategory() == null) goto L13;
        L10:
            if (r4 == true) goto L13;
        }

        public void checkInitialization() {
            createCategoriesListView();
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.view.View
        public void invalidate() {
            if (HwEmojis.grab(this) == false) goto L5;
            return;
        L5:
            super.invalidate();
        }

        public boolean isInProgress() {
            if (this.searchStateDrawable.getIconState() != 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
        }

        public void setUseCustomBackground() {
            this.useCustomBackground = true;
            setBackground(null);
            updateColors();
            invalidate();
        }

        public void showProgress(boolean r1) {
            if (r1 == false) goto L5;
            this.searchStateDrawable.setIconState(2);
            return;
        L5:
            updateButton(true);
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            if (this.useCustomBackground == false) goto L6;
            this.box.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, SelectAnimatedEmojiDialog.m18676$$Nest$fgetresourcesProvider(this.this$0)), 0.06f)));
            return;
        }
    }

    public static class SelectAnimatedEmojiDialogWindow extends PopupWindow {
        private static final ViewTreeObserver.OnScrollChangedListener NOP = null;
        private static final Field superListenerField = null;
        private ViewTreeObserver.OnScrollChangedListener mSuperScrollListener;
        private ViewTreeObserver mViewTreeObserver;

        public static /* synthetic */ void $r8$lambda$Stx6nzxgBbMEwlNK__veO9gJNrk(SelectAnimatedEmojiDialogWindow r0) {
            r0.lambda$dismiss$1();
        }

        public static /* synthetic */ void $r8$lambda$XHVKtgZK_Vi5QBfbkh5XA8tNoGA() {
        }

        static {
            NOP = new SelectAnimatedEmojiDialog$SelectAnimatedEmojiDialogWindow$$ExternalSyntheticLambda0();
            Field r0 = null;
            r0 = PopupWindow.class.getDeclaredField("mOnScrollChangedListener");     // Catch: NoSuchFieldException -> L6
            r0.setAccessible(true);     // Catch: NoSuchFieldException -> L6
        L4:
            superListenerField = r0;
        }

        public SelectAnimatedEmojiDialogWindow(View r1, int r2, int r3) {
            super(r1, r2, r3);
            init();
        }

        private void dismissDim() {
            View r0 = getContentView().getRootView();
            WindowManager r4 = (WindowManager) getContentView().getContext().getSystemService("window");
            if (r0.getLayoutParams() != null) goto L5;
            return;
        L5:
            if ((r0.getLayoutParams() instanceof WindowManager.LayoutParams) == false) goto L17;
            WindowManager.LayoutParams r1 = (WindowManager.LayoutParams) r0.getLayoutParams();
            int r2 = r1.flags;     // Catch: Exception -> L12
            if ((r2 & 2) == 0) goto L16;
            r1.flags = r2 & (-3);     // Catch: Exception -> L12
            r1.dimAmount = 0.0f;     // Catch: Exception -> L12
            r4.updateViewLayout(r0, r1);     // Catch: Exception -> L12
            return;
        L16:
            return;
        L18:
            return;
        }

        private void init() {
            setFocusable(true);
            setAnimationStyle(0);
            setOutsideTouchable(true);
            setClippingEnabled(true);
            setInputMethodMode(0);
            setSoftInputMode(4);
            Field r0 = superListenerField;
            if (r0 == null) goto L10;
            this.mSuperScrollListener = (ViewTreeObserver.OnScrollChangedListener) r0.get(this);     // Catch: Exception -> L6
            r0.set(this, NOP);     // Catch: Exception -> L6
            return;
        L6:
            this.mSuperScrollListener = null;
            return;
        }

        private /* synthetic */ void lambda$dismiss$1() {
            super.dismiss();
        }

        private void registerListener(View r3) {
            if ((getContentView() instanceof SelectAnimatedEmojiDialog) == false) goto L6;
            ((SelectAnimatedEmojiDialog) getContentView()).onShow(new SelectAnimatedEmojiDialog$SelectAnimatedEmojiDialogWindow$$ExternalSyntheticLambda1(this));
        L6:
            if (this.mSuperScrollListener != null) goto L8;
            return;
        L8:
            if (r3.getWindowToken() == null) goto L10;
            ViewTreeObserver r32 = r3.getViewTreeObserver();
        L11:
            ViewTreeObserver r0 = this.mViewTreeObserver;
            if (r32 == r0) goto L22;
            if (r0 != null) goto L15;
        L17:
            this.mViewTreeObserver = r32;
            if (r32 == null) goto L23;
            r32.addOnScrollChangedListener(this.mSuperScrollListener);
            return;
        L23:
            return;
        L15:
            if (r0.isAlive() == false) goto L17;
            this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
            goto L17
        L22:
            return;
        L10:
            r32 = null;
            goto L11
        }

        private void unregisterListener() {
            if (this.mSuperScrollListener == null) goto L11;
            ViewTreeObserver r0 = this.mViewTreeObserver;
            if (r0 != null) goto L7;
            return;
        L7:
            if (r0.isAlive() == false) goto L9;
            this.mViewTreeObserver.removeOnScrollChangedListener(this.mSuperScrollListener);
        L9:
            this.mViewTreeObserver = null;
            return;
        }

        public void dimBehind() {
            View r0 = getContentView().getRootView();
            WindowManager r3 = (WindowManager) getContentView().getContext().getSystemService("window");
            WindowManager.LayoutParams r1 = (WindowManager.LayoutParams) r0.getLayoutParams();
            r1.flags |= 2;
            r1.dimAmount = 0.2f;
            r3.updateViewLayout(r0, r1);
        }

        @Override // android.widget.PopupWindow
        public void dismiss() {
            if ((getContentView() instanceof SelectAnimatedEmojiDialog) == false) goto L6;
            ((SelectAnimatedEmojiDialog) getContentView()).onDismiss(new SelectAnimatedEmojiDialog$SelectAnimatedEmojiDialogWindow$$ExternalSyntheticLambda2(this));
            dismissDim();
            return;
        L6:
            super.dismiss();
        }

        @Override // android.widget.PopupWindow
        public void showAsDropDown(View r1) {
            super.showAsDropDown(r1);
            registerListener(r1);
        }

        @Override // android.widget.PopupWindow
        public void showAsDropDown(View r1, int r2, int r3) {
            super.showAsDropDown(r1, r2, r3);
            registerListener(r1);
        }

        @Override // android.widget.PopupWindow
        public void showAsDropDown(View r1, int r2, int r3, int r4) {
            super.showAsDropDown(r1, r2, r3, r4);
            registerListener(r1);
        }

        @Override // android.widget.PopupWindow
        public void showAtLocation(View r1, int r2, int r3, int r4) {
            super.showAtLocation(r1, r2, r3, r4);
            unregisterListener();
        }
    }

    /* loaded from: classes6.dex */
    public class SelectStatusDurationDialog extends Dialog {
        private Bitmap blurBitmap;
        private Paint blurBitmapPaint;
        private boolean changeToScrimColor;
        private int clipBottom;
        private ContentView contentView;
        private Rect current;
        private BottomSheet dateBottomSheet;
        private boolean dismissed;
        private boolean done;
        private View emojiPreviewView;
        private Rect from;
        private ImageReceiver imageReceiver;
        private ImageViewEmoji imageViewEmoji;
        private WindowInsets lastInsets;
        private LinearLayout linearLayoutView;
        private ActionBarPopupWindow.ActionBarPopupWindowLayout menuView;
        private Runnable parentDialogDismiss;
        private View parentDialogView;
        private int parentDialogX;
        private int parentDialogY;
        private Theme.ResourcesProvider resourcesProvider;
        private ValueAnimator showAnimator;
        private ValueAnimator showMenuAnimator;
        private float showMenuT;
        private float showT;
        private boolean showing;
        private boolean showingMenu;
        private int[] tempLocation;
        final /* synthetic */ SelectAnimatedEmojiDialog this$0;
        private Rect to;

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SelectStatusDurationDialog$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ SelectStatusDurationDialog this$1;
            final /* synthetic */ SelectAnimatedEmojiDialog val$this$0;

            public AnonymousClass1(SelectStatusDurationDialog r1, Context r2, SelectAnimatedEmojiDialog r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
                super.onLayout(r2, r3, r4, r5, r6);
                getLocationOnScreen(SelectStatusDurationDialog.m18756$$Nest$fgettempLocation(this.this$1));
                SelectStatusDurationDialog.m18757$$Nest$fgetto(this.this$1).set(SelectStatusDurationDialog.m18756$$Nest$fgettempLocation(this.this$1)[0], SelectStatusDurationDialog.m18756$$Nest$fgettempLocation(this.this$1)[1], SelectStatusDurationDialog.m18756$$Nest$fgettempLocation(this.this$1)[0] + getWidth(), SelectStatusDurationDialog.m18756$$Nest$fgettempLocation(this.this$1)[1] + getHeight());
                AndroidUtilities.lerp(SelectStatusDurationDialog.m18748$$Nest$fgetfrom(this.this$1), SelectStatusDurationDialog.m18757$$Nest$fgetto(this.this$1), SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1), SelectStatusDurationDialog.m18747$$Nest$fgetcurrent(this.this$1));
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SelectStatusDurationDialog$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SelectStatusDurationDialog this$1;
            final /* synthetic */ Runnable val$onDone;
            final /* synthetic */ Runnable val$onPartly;
            final /* synthetic */ boolean[] val$partlydone;
            final /* synthetic */ boolean val$show;
            final /* synthetic */ boolean val$showback;

            public AnonymousClass2(SelectStatusDurationDialog r1, boolean r2, Runnable r3, boolean[] r4, boolean r5, Runnable r6) {
                this.this$1 = r1;
                this.val$show = r2;
                this.val$onPartly = r3;
                this.val$partlydone = r4;
                this.val$showback = r5;
                this.val$onDone = r6;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                SelectStatusDurationDialog r42 = this.this$1;
                if (this.val$show == false) goto L5;
                float r0 = 1.0f;
            L6:
                SelectStatusDurationDialog.m18762$$Nest$fputshowT(r42, r0);
                AndroidUtilities.lerp(SelectStatusDurationDialog.m18748$$Nest$fgetfrom(this.this$1), SelectStatusDurationDialog.m18757$$Nest$fgetto(this.this$1), SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1), SelectStatusDurationDialog.m18747$$Nest$fgetcurrent(this.this$1));
                SelectStatusDurationDialog.m18746$$Nest$fgetcontentView(this.this$1).invalidate();
                if (this.val$show == true) goto L10;
                SelectStatusDurationDialog.m18751$$Nest$fgetmenuView(this.this$1).setAlpha(SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1));
            L10:
                if (SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1) >= 0.5f) goto L19;
                if (this.val$show == true) goto L19;
                Runnable r43 = this.val$onPartly;
                if (r43 == null) goto L19;
                boolean[] r1 = this.val$partlydone;
                if (r1[0] == true) goto L19;
                r1[0] = true;
                r43.run();
            L19:
                if (this.val$show == false) goto L21;
            L24:
                SelectStatusDurationDialog.m18759$$Nest$fputshowAnimator(this.this$1, null);
                SelectStatusDurationDialog.m18746$$Nest$fgetcontentView(this.this$1).invalidate();
                Runnable r3 = this.val$onDone;
                if (r3 == null) goto L28;
                r3.run();
                return;
            L28:
                return;
            L21:
                if (this.val$showback == false) goto L23;
                SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).notDraw = false;
                this.this$1.this$0.emojiGridView.invalidate();
            L23:
                NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{4});
                goto L24
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.SelectAnimatedEmojiDialog$SelectStatusDurationDialog$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ SelectStatusDurationDialog this$1;
            final /* synthetic */ Runnable val$onDone;
            final /* synthetic */ boolean val$show;

            public AnonymousClass3(SelectStatusDurationDialog r1, boolean r2, Runnable r3) {
                this.this$1 = r1;
                this.val$show = r2;
                this.val$onDone = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r7) {
                SelectStatusDurationDialog r72 = this.this$1;
                if (this.val$show == false) goto L5;
                float r0 = 1.0f;
            L6:
                SelectStatusDurationDialog.m18761$$Nest$fputshowMenuT(r72, r0);
                SelectStatusDurationDialog.m18751$$Nest$fgetmenuView(this.this$1).setBackScaleY(SelectStatusDurationDialog.m18754$$Nest$fgetshowMenuT(this.this$1));
                SelectStatusDurationDialog.m18751$$Nest$fgetmenuView(this.this$1).setAlpha(CubicBezierInterpolator.EASE_OUT.getInterpolation(SelectStatusDurationDialog.m18754$$Nest$fgetshowMenuT(this.this$1)));
                int r73 = SelectStatusDurationDialog.m18751$$Nest$fgetmenuView(this.this$1).getItemsCount();
                int r02 = 0;
            L7:
                SelectStatusDurationDialog r2 = this.this$1;
                if (r02 >= r73) goto L10;
                float r22 = AndroidUtilities.cascade(SelectStatusDurationDialog.m18754$$Nest$fgetshowMenuT(r2), r02, r73, 4.0f);
                SelectStatusDurationDialog.m18751$$Nest$fgetmenuView(this.this$1).getItemAt(r02).setTranslationY((1.0f - r22) * AndroidUtilities.dp(-12.0f));
                SelectStatusDurationDialog.m18751$$Nest$fgetmenuView(this.this$1).getItemAt(r02).setAlpha(r22);
                r02 = r02 + 1;
                goto L7
            L10:
                SelectStatusDurationDialog.m18760$$Nest$fputshowMenuAnimator(r2, null);
                Runnable r6 = this.val$onDone;
                if (r6 == null) goto L15;
                r6.run();
                return;
            L15:
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        public class ContentView extends FrameLayout {
            final /* synthetic */ SelectStatusDurationDialog this$1;

            public ContentView(SelectStatusDurationDialog r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r15) {
                if (SelectStatusDurationDialog.m18742$$Nest$fgetblurBitmap(this.this$1) != null) goto L5;
            L7:
                super.dispatchDraw(r15);
                if (SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1) == null) goto L43;
                Drawable r0 = SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).drawable;
                SelectStatusDurationDialog r3 = this.this$1;
                if (r0 != null) goto L12;
                Canvas r7 = r15;
                if (SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(r3).isDefaultReaction == true) goto L41;
            L45:
                if (SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1) == null) goto L48;
                SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1).setAlpha(SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1));
                SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1).setImageCoords(SelectStatusDurationDialog.m18747$$Nest$fgetcurrent(this.this$1));
                SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1).draw(r7);
                return;
            L48:
                return;
            L41:
                if (SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).imageReceiver == null) goto L45;
                SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).imageReceiver.setAlpha(1.0f - SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1));
                SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).imageReceiver.setImageCoords(SelectStatusDurationDialog.m18747$$Nest$fgetcurrent(this.this$1));
                SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).imageReceiver.draw(r7);
                goto L45
            L12:
                if (SelectStatusDurationDialog.m18744$$Nest$fgetchangeToScrimColor(r3) == false) goto L14;
                r0.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(SelectAnimatedEmojiDialog.m18680$$Nest$fgetscrimColor(this.this$1.this$0), SelectAnimatedEmojiDialog.m18628$$Nest$fgetaccentColor(this.this$1.this$0), SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1)), PorterDuff.Mode.MULTIPLY));
            L15:
                r0.setAlpha((int) ((1.0f - SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1)) * 255.0f));
                RectF r32 = AndroidUtilities.rectTmp;
                r32.set(SelectStatusDurationDialog.m18747$$Nest$fgetcurrent(this.this$1));
                if (ImageViewEmoji.m18726$$Nest$fgetpressedProgress(SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1)) == 0.0f) goto L18;
            L21:
                float r5 = (((1.0f - Math.max(ImageViewEmoji.m18727$$Nest$fgetselectedProgress(SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1)) * 0.8f, ImageViewEmoji.m18726$$Nest$fgetpressedProgress(SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1)))) * 0.2f) + 0.8f) * 1.0f;
            L22:
                Rect r6 = AndroidUtilities.rectTmp2;
                r6.set((int) (r32.centerX() - ((r32.width() / 2.0f) * r5)), (int) (r32.centerY() - ((r32.height() / 2.0f) * r5)), (int) (r32.centerX() + ((r32.width() / 2.0f) * r5)), (int) (r32.centerY() + ((r32.height() / 2.0f) * r5)));
                float r33 = 1.0f - ((1.0f - SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).skewAlpha) * (1.0f - SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1)));
                r15.save();
                if (r33 >= 1.0f) goto L25;
                r15.translate(r6.left, r6.top);
                r15.scale(1.0f, r33, 0.0f, 0.0f);
                r15.skew((1.0f - ((SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).skewIndex * 2.0f) / 8.0f)) * (1.0f - r33), 0.0f);
                r15.translate(-r6.left, -r6.top);
            L25:
                r15.clipRect(0.0f, 0.0f, getWidth(), SelectStatusDurationDialog.m18745$$Nest$fgetclipBottom(this.this$1) + (SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1) * AndroidUtilities.dp(45.0f)));
                r0.setBounds(r6);
                r0.draw(r15);
                r15.restore();
                if (SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).skewIndex != 0) goto L29;
                r6.offset(AndroidUtilities.dp(r33 * 8.0f), 0);
            L37:
                r7 = r15;
                r7.saveLayerAlpha(r6.left, r6.top, r6.right, r6.bottom, (int) ((1.0f - SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1)) * 255.0f), 31);
                r7.clipRect(r6);
                r7.translate((int) ((SelectAnimatedEmojiDialog.m18637$$Nest$fgetbottomGradientView(this.this$1.this$0).getX() + this.this$1.this$0.contentView.getX()) + SelectStatusDurationDialog.m18752$$Nest$fgetparentDialogX(this.this$1)), (((int) SelectAnimatedEmojiDialog.m18637$$Nest$fgetbottomGradientView(this.this$1.this$0).getY()) + this.this$1.this$0.contentView.getY()) + SelectStatusDurationDialog.m18753$$Nest$fgetparentDialogY(this.this$1));
                SelectAnimatedEmojiDialog.m18637$$Nest$fgetbottomGradientView(this.this$1.this$0).draw(r7);
                r7.restore();
                goto L45
            L29:
                if (SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).skewIndex != 1) goto L32;
                r6.offset(AndroidUtilities.dp(r33 * 4.0f), 0);
                goto L37
            L32:
                if (SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).skewIndex != 6) goto L35;
                r6.offset(-AndroidUtilities.dp(r33 * (-4.0f)), 0);
                goto L37
            L35:
                if (SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1).skewIndex != 7) goto L37;
                r6.offset(AndroidUtilities.dp(r33 * (-8.0f)), 0);
                goto L37
            L18:
                if (ImageViewEmoji.m18727$$Nest$fgetselectedProgress(SelectStatusDurationDialog.m18750$$Nest$fgetimageViewEmoji(this.this$1)) > 0.0f) goto L21;
                r5 = 1.0f;
                goto L22
            L14:
                r0.setColorFilter(SelectAnimatedEmojiDialog.m18667$$Nest$fgetpremiumStarColorFilter(this.this$1.this$0));
                goto L15
            L43:
                r7 = r15;
                goto L45
            L5:
                if (SelectStatusDurationDialog.m18743$$Nest$fgetblurBitmapPaint(this.this$1) == null) goto L7;
                r15.save();
                r15.scale(12.0f, 12.0f);
                SelectStatusDurationDialog.m18743$$Nest$fgetblurBitmapPaint(this.this$1).setAlpha((int) (SelectStatusDurationDialog.m18755$$Nest$fgetshowT(this.this$1) * 255.0f));
                r15.drawBitmap(SelectStatusDurationDialog.m18742$$Nest$fgetblurBitmap(this.this$1), 0.0f, 0.0f, SelectStatusDurationDialog.m18743$$Nest$fgetblurBitmapPaint(this.this$1));
                r15.restore();
                goto L7
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onAttachedToWindow() {
                super.onAttachedToWindow();
                if (SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1) == null) goto L6;
                SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1).onAttachedToWindow();
                return;
            }

            @Override // android.view.View
            public void onConfigurationChanged(Configuration r1) {
                SelectStatusDurationDialog.m18758$$Nest$fputlastInsets(this.this$1, null);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onDetachedFromWindow() {
                super.onDetachedFromWindow();
                if (SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1) == null) goto L6;
                SelectStatusDurationDialog.m18749$$Nest$fgetimageReceiver(this.this$1).onDetachedFromWindow();
                return;
            }

            @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
                super.onLayout(r1, r2, r3, r4, r5);
                Activity r12 = SelectStatusDurationDialog.m18763$$Nest$mgetParentActivity(this.this$1);
                if (r12 == null) goto L15;
                View r13 = r12.getWindow().getDecorView();
                if (SelectStatusDurationDialog.m18742$$Nest$fgetblurBitmap(this.this$1) != null) goto L8;
            L13:
                SelectStatusDurationDialog.m18764$$Nest$mprepareBlurBitmap(this.this$1);
                return;
            L8:
                if (SelectStatusDurationDialog.m18742$$Nest$fgetblurBitmap(this.this$1).getWidth() != r13.getMeasuredWidth()) goto L13;
                if (SelectStatusDurationDialog.m18742$$Nest$fgetblurBitmap(this.this$1).getHeight() != r13.getMeasuredHeight()) goto L13;
                return;
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
            }
        }

        /* renamed from: $r8$lambda$-TLyzKSWSet6vA8FhVYmDCaEtUU, reason: not valid java name */
        public static /* synthetic */ void m18738$r8$lambda$TLyzKSWSet6vA8FhVYmDCaEtUU(SelectStatusDurationDialog r0, View r1) {
            r0.lambda$new$1(r1);
        }

        /* renamed from: $r8$lambda$1sRpEgMZY62n1ryFwlg6lg-onmo, reason: not valid java name */
        public static /* synthetic */ void m18739$r8$lambda$1sRpEgMZY62n1ryFwlg6lgonmo(SelectStatusDurationDialog r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$2m5TZjTaQCaGOi0m303AEEtAamU(SelectStatusDurationDialog r0, ValueAnimator r1) {
            r0.lambda$animateMenuShow$11(r1);
        }

        public static /* synthetic */ void $r8$lambda$90yjfsbw67lSRVdBkuMA1O1Vrak(SelectStatusDurationDialog r0, boolean[] r1, DialogInterface r2) {
            r0.lambda$new$5(r1, r2);
        }

        /* renamed from: $r8$lambda$9Nq76aJ9Ztt4l94aBB2EqI-RytY, reason: not valid java name */
        public static /* synthetic */ void m18740$r8$lambda$9Nq76aJ9Ztt4l94aBB2EqIRytY(SelectStatusDurationDialog r0, Integer r1) {
            r0.lambda$done$9(r1);
        }

        public static /* synthetic */ void $r8$lambda$OuzzjnxgNYBMzO1ylT6ETZ4Gui4(SelectStatusDurationDialog r0, boolean r1, boolean r2, Runnable r3, boolean[] r4, ValueAnimator r5) {
            r0.lambda$animateShow$10(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$YoH5oxonncSjmBNwuhtvWdYnf3w(SelectStatusDurationDialog r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$_sLeparfCQ-mXeTxHFy60zi_Dxs, reason: not valid java name */
        public static /* synthetic */ void m18741$r8$lambda$_sLeparfCQmXeTxHFy60zi_Dxs(SelectStatusDurationDialog r0, boolean[] r1, int r2) {
            r0.lambda$new$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$b7SR9v1P5ezJbe5_on89xdTzjiI(SelectStatusDurationDialog r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$go6zH7_hPO6zKFFuKJI6V3PGbe8(SelectStatusDurationDialog r0, Integer r1) {
            r0.lambda$done$8(r1);
        }

        public static /* synthetic */ WindowInsets $r8$lambda$l9CLb_rSQ7kk_9ZMnfOwajoRKeA(SelectStatusDurationDialog r0, View r1, WindowInsets r2) {
            return r0.lambda$new$7(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$xTOAVuC4iEqpsvRWHOMSeEgnILI(SelectStatusDurationDialog r0, Context r1, View r2) {
            r0.lambda$new$6(r1, r2);
        }

        /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap m18742$$Nest$fgetblurBitmap(SelectStatusDurationDialog r0) {
            return r0.blurBitmap;
        }

        /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m18743$$Nest$fgetblurBitmapPaint(SelectStatusDurationDialog r0) {
            return r0.blurBitmapPaint;
        }

        /* renamed from: -$$Nest$fgetchangeToScrimColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m18744$$Nest$fgetchangeToScrimColor(SelectStatusDurationDialog r0) {
            return r0.changeToScrimColor;
        }

        /* renamed from: -$$Nest$fgetclipBottom, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m18745$$Nest$fgetclipBottom(SelectStatusDurationDialog r0) {
            return r0.clipBottom;
        }

        /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ContentView m18746$$Nest$fgetcontentView(SelectStatusDurationDialog r0) {
            return r0.contentView;
        }

        /* renamed from: -$$Nest$fgetcurrent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Rect m18747$$Nest$fgetcurrent(SelectStatusDurationDialog r0) {
            return r0.current;
        }

        /* renamed from: -$$Nest$fgetfrom, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Rect m18748$$Nest$fgetfrom(SelectStatusDurationDialog r0) {
            return r0.from;
        }

        /* renamed from: -$$Nest$fgetimageReceiver, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageReceiver m18749$$Nest$fgetimageReceiver(SelectStatusDurationDialog r0) {
            return r0.imageReceiver;
        }

        /* renamed from: -$$Nest$fgetimageViewEmoji, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageViewEmoji m18750$$Nest$fgetimageViewEmoji(SelectStatusDurationDialog r0) {
            return r0.imageViewEmoji;
        }

        /* renamed from: -$$Nest$fgetmenuView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout m18751$$Nest$fgetmenuView(SelectStatusDurationDialog r0) {
            return r0.menuView;
        }

        /* renamed from: -$$Nest$fgetparentDialogX, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m18752$$Nest$fgetparentDialogX(SelectStatusDurationDialog r0) {
            return r0.parentDialogX;
        }

        /* renamed from: -$$Nest$fgetparentDialogY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m18753$$Nest$fgetparentDialogY(SelectStatusDurationDialog r0) {
            return r0.parentDialogY;
        }

        /* renamed from: -$$Nest$fgetshowMenuT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m18754$$Nest$fgetshowMenuT(SelectStatusDurationDialog r0) {
            return r0.showMenuT;
        }

        /* renamed from: -$$Nest$fgetshowT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m18755$$Nest$fgetshowT(SelectStatusDurationDialog r0) {
            return r0.showT;
        }

        /* renamed from: -$$Nest$fgettempLocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int[] m18756$$Nest$fgettempLocation(SelectStatusDurationDialog r0) {
            return r0.tempLocation;
        }

        /* renamed from: -$$Nest$fgetto, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Rect m18757$$Nest$fgetto(SelectStatusDurationDialog r0) {
            return r0.to;
        }

        /* renamed from: -$$Nest$fputlastInsets, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18758$$Nest$fputlastInsets(SelectStatusDurationDialog r0, WindowInsets r1) {
            r0.lastInsets = r1;
        }

        /* renamed from: -$$Nest$fputshowAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18759$$Nest$fputshowAnimator(SelectStatusDurationDialog r0, ValueAnimator r1) {
            r0.showAnimator = r1;
        }

        /* renamed from: -$$Nest$fputshowMenuAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18760$$Nest$fputshowMenuAnimator(SelectStatusDurationDialog r0, ValueAnimator r1) {
            r0.showMenuAnimator = r1;
        }

        /* renamed from: -$$Nest$fputshowMenuT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18761$$Nest$fputshowMenuT(SelectStatusDurationDialog r0, float r1) {
            r0.showMenuT = r1;
        }

        /* renamed from: -$$Nest$fputshowT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18762$$Nest$fputshowT(SelectStatusDurationDialog r0, float r1) {
            r0.showT = r1;
        }

        /* renamed from: -$$Nest$mgetParentActivity, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Activity m18763$$Nest$mgetParentActivity(SelectStatusDurationDialog r0) {
            return r0.getParentActivity();
        }

        /* renamed from: -$$Nest$mprepareBlurBitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18764$$Nest$mprepareBlurBitmap(SelectStatusDurationDialog r0) {
            r0.prepareBlurBitmap();
        }

        public SelectStatusDurationDialog(SelectAnimatedEmojiDialog r29, Context r30, Runnable r31, View r32, ImageViewEmoji r33, Theme.ResourcesProvider r34) {
            this.this$0 = r29;
            super(r30);
            this.from = new Rect();
            this.to = new Rect();
            this.current = new Rect();
            this.tempLocation = new int[2];
            this.done = false;
            this.dismissed = false;
            this.imageViewEmoji = r33;
            this.resourcesProvider = r34;
            this.parentDialogDismiss = r31;
            this.parentDialogView = r32;
            ContentView r5 = new ContentView(this, r30);
            this.contentView = r5;
            setContentView(r5, new ViewGroup.LayoutParams(-1, -1));
            LinearLayout r52 = new LinearLayout(r30);
            this.linearLayoutView = r52;
            r52.setOrientation(1);
            AnonymousClass1 r53 = new AnonymousClass1(this, r30, r29);
            this.emojiPreviewView = r53;
            this.linearLayoutView.addView(r53, LayoutHelper.createLinear(160, 160, 17, 0, 0, 0, 16));
            ActionBarPopupWindow.ActionBarPopupWindowLayout r54 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r30, R.drawable.popup_fixed_alert2, r34);
            this.menuView = r54;
            this.linearLayoutView.addView(r54, LayoutHelper.createLinear(-2, -2, 17, 0, 0, 0, 0));
            ActionBarMenuItem.addItem(true, false, this.menuView, 0, LocaleController.getString(R.string.SetEmojiStatusUntil1Hour), false, r34).setOnClickListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda0(this));
            ActionBarMenuItem.addItem(false, false, this.menuView, 0, LocaleController.getString(R.string.SetEmojiStatusUntil2Hours), false, r34).setOnClickListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda1(this));
            ActionBarMenuItem.addItem(false, false, this.menuView, 0, LocaleController.getString(R.string.SetEmojiStatusUntil8Hours), false, r34).setOnClickListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda2(this));
            ActionBarMenuItem.addItem(false, false, this.menuView, 0, LocaleController.getString(R.string.SetEmojiStatusUntil2Days), false, r34).setOnClickListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda3(this));
            ActionBarMenuItem.addItem(false, true, this.menuView, 0, LocaleController.getString(R.string.SetEmojiStatusUntilOther), false, r34).setOnClickListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda4(this, r30));
            this.contentView.addView(this.linearLayoutView, LayoutHelper.createFrame(-2, -2, 17));
            Window r2 = getWindow();
            if (r2 == null) goto L8;
            r2.setWindowAnimations(R.style.DialogNoAnimation);
            r2.setBackgroundDrawable(null);
            WindowManager.LayoutParams r55 = r2.getAttributes();
            r55.width = -1;
            r55.gravity = 51;
            r55.dimAmount = 0.0f;
            r55.flags = (r55.flags & (-3)) | (-2147286784);
            this.contentView.setOnApplyWindowInsetsListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda5(this));
            r55.flags |= 1024;
            this.contentView.setFitsSystemWindows(true);
            this.contentView.setSystemUiVisibility(1284);
            r55.height = -1;
            if (Build.VERSION.SDK_INT < 28) goto L7;
            AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r55, 1);
        L7:
            r2.setAttributes(r55);
        L8:
            if (r33 == null) goto L10;
            r33.notDraw = true;
        L10:
            prepareBlurBitmap();
            ImageReceiver r22 = new ImageReceiver();
            this.imageReceiver = r22;
            r22.setParentView(this.contentView);
            this.imageReceiver.setLayerNum(7);
            TLRPC.Document r23 = r33.document;
            if (r23 != null) goto L15;
            Drawable r56 = r33.drawable;
            if ((r56 instanceof AnimatedEmojiDrawable) == false) goto L15;
            r23 = ((AnimatedEmojiDrawable) r56).getDocument();
        L15:
            if (r23 == null) goto L42;
            SvgHelper.SvgDrawable r57 = DocumentObject.getSvgThumb(r23.thumbs, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
            TLRPC.PhotoSize r6 = FileLoader.getClosestPhotoSizeWithSize(r23.thumbs, 90);
            if ("video/webm".equals(r23.mime_type) == false) goto L23;
            ImageLocation r7 = ImageLocation.getForDocument(r23);
            if (r57 == null) goto L21;
            r57.overrideWidthAndHeight(512, 512);
        L21:
            String r17 = "160_160_g";
        L28:
            TLRPC.Document r26 = r23;
            this.imageReceiver.setImage(r7, r17, ImageLocation.getForDocument(r6, r23), "160_160", null, null, r57, r23.size, null, r26, 1);
            if ((r33.drawable instanceof AnimatedEmojiDrawable) == false) goto L42;
            if (MessageObject.isTextColorEmoji(r26) == false) goto L33;
        L34:
            ImageReceiver r24 = this.imageReceiver;
            if (MessageObject.isTextColorEmoji(r26) == false) goto L37;
        L40:
            ColorFilter r1 = SelectAnimatedEmojiDialog.m18667$$Nest$fgetpremiumStarColorFilter(r29);
        L41:
            r24.setColorFilter(r1);
            goto L42
        L37:
            if (AnimatedEmojiDrawable.isDefaultStatusEmoji((AnimatedEmojiDrawable) r33.drawable) == true) goto L40;
            r1 = Theme.getAnimatedEmojiColorFilter(r34);
            goto L41
        L33:
            if (((AnimatedEmojiDrawable) r33.drawable).canOverrideColor() == false) goto L42;
        L23:
            if (r57 != null) goto L25;
        L27:
            r7 = ImageLocation.getForDocument(r23);
            r17 = "160_160";
            goto L28
        L25:
            if (MessageObject.isAnimatedStickerDocument(r23, false) == false) goto L27;
            r57.overrideWidthAndHeight(512, 512);
        L42:
            r33.getLocationOnScreen(this.tempLocation);
            this.from.left = this.tempLocation[0] + r33.getPaddingLeft();
            this.from.top = this.tempLocation[1] + r33.getPaddingTop();
            this.from.right = (this.tempLocation[0] + r33.getWidth()) - r33.getPaddingRight();
            this.from.bottom = (this.tempLocation[1] + r33.getHeight()) - r33.getPaddingBottom();
            AndroidUtilities.lerp(this.from, this.to, this.showT, this.current);
            r32.getLocationOnScreen(this.tempLocation);
            int[] r12 = this.tempLocation;
            this.parentDialogX = r12[0];
            int r13 = r12[1];
            this.parentDialogY = r13;
            this.clipBottom = r13 + r32.getHeight();
        }

        private void animateMenuShow(boolean r5, Runnable r6) {
            ValueAnimator r0 = this.showMenuAnimator;
            if (r0 != null) goto L5;
        L8:
            this.showingMenu = r5;
            float r02 = this.showMenuT;
            if (r5 == false) goto L11;
            float r1 = 1.0f;
        L12:
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
            this.showMenuAnimator = r03;
            r03.addUpdateListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda6(this));
            this.showMenuAnimator.addListener(new AnonymousClass3(this, r5, r6));
            ValueAnimator r62 = this.showMenuAnimator;
            if (r5 == false) goto L15;
            r62.setDuration(360);
            this.showMenuAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        L16:
            this.showMenuAnimator.start();
            return;
        L15:
            r62.setDuration(240);
            this.showMenuAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            goto L16
        L11:
            r1 = 0.0f;
            goto L12
        L5:
            if (this.showingMenu != r5) goto L7;
            return;
        L7:
            r0.cancel();
            goto L8
        }

        private void animateShow(boolean r11, Runnable r12, Runnable r13, boolean r14) {
            if (this.imageViewEmoji != null) goto L7;
            if (r12 == null) goto L11;
            r12.run();
            return;
        L11:
            return;
        L7:
            ValueAnimator r0 = this.showAnimator;
            if (r0 != null) goto L10;
        L13:
            this.showing = r11;
            if (r11 == false) goto L16;
            this.imageViewEmoji.notDraw = true;
        L16:
            boolean[] r6 = new boolean[1];
            float r1 = this.showT;
            if (r11 == false) goto L19;
            float r2 = 1.0f;
        L20:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r1, r2});
            this.showAnimator = r02;
            r02.addUpdateListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda7(this, r11, r14, r13, r6));
            this.showAnimator.addListener(new AnonymousClass2(this, r11, r13, r6, r14, r12));
            this.showAnimator.setDuration(420);
            this.showAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.showAnimator.start();
            return;
        L19:
            r2 = 0.0f;
            goto L20
        L10:
            if (this.showing == r11) goto L22;
            r0.cancel();
            goto L13
        }

        private void done(Integer r7) {
            if (this.done == false) goto L5;
            return;
        L5:
            this.done = true;
            if (r7 != null) goto L8;
        L10:
            boolean r2 = false;
        L11:
            this.changeToScrimColor = r2;
            if (r2 == false) goto L14;
            this.parentDialogView.getLocationOnScreen(this.tempLocation);
            Rect r3 = this.from;
            int[] r4 = this.tempLocation;
            r3.offset(r4[0], r4[1]);
        L15:
            if (r7 == null) goto L19;
            Runnable r32 = this.parentDialogDismiss;
            if (r32 == null) goto L19;
            r32.run();
        L19:
            animateShow(false, new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda8(this, r7), new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda9(this, r7), !r2);
            animateMenuShow(false, null);
            return;
        L14:
            this.imageViewEmoji.getLocationOnScreen(this.tempLocation);
            this.from.left = this.tempLocation[0] + this.imageViewEmoji.getPaddingLeft();
            this.from.top = this.tempLocation[1] + this.imageViewEmoji.getPaddingTop();
            this.from.right = (this.tempLocation[0] + this.imageViewEmoji.getWidth()) - this.imageViewEmoji.getPaddingRight();
            this.from.bottom = (this.tempLocation[1] + this.imageViewEmoji.getHeight()) - this.imageViewEmoji.getPaddingBottom();
            goto L15
        L8:
            if (getOutBounds(this.from) == false) goto L10;
            r2 = true;
            goto L11
        }

        private Activity getParentActivity() {
            Context r1 = getContext();
        L4:
            if ((r1 instanceof ContextWrapper) == false) goto L10;
            if ((r1 instanceof Activity) == true) goto L8;
            r1 = ((ContextWrapper) r1).getBaseContext();
            goto L4
        L8:
            return (Activity) r1;
        L10:
            return null;
        }

        private /* synthetic */ void lambda$animateMenuShow$11(ValueAnimator r6) {
            float r62 = ((Float) r6.getAnimatedValue()).floatValue();
            this.showMenuT = r62;
            this.menuView.setBackScaleY(r62);
            this.menuView.setAlpha(CubicBezierInterpolator.EASE_OUT.getInterpolation(this.showMenuT));
            int r63 = this.menuView.getItemsCount();
            int r0 = 0;
        L3:
            if (r0 >= r63) goto L5;
            float r1 = AndroidUtilities.cascade(this.showMenuT, r0, r63, 4.0f);
            this.menuView.getItemAt(r0).setTranslationY((1.0f - r1) * AndroidUtilities.dp(-12.0f));
            this.menuView.getItemAt(r0).setAlpha(r1);
            r0 = r0 + 1;
            goto L3
        }

        private /* synthetic */ void lambda$animateShow$10(boolean r4, boolean r5, Runnable r6, boolean[] r7, ValueAnimator r8) {
            float r82 = ((Float) r8.getAnimatedValue()).floatValue();
            this.showT = r82;
            AndroidUtilities.lerp(this.from, this.to, r82, this.current);
            this.contentView.invalidate();
            if (r4 == true) goto L6;
            this.menuView.setAlpha(this.showT);
        L6:
            if (this.showT >= 0.025f) goto L12;
            if (r4 == true) goto L12;
            if (r5 == false) goto L10;
            this.imageViewEmoji.notDraw = false;
            this.this$0.emojiGridView.invalidate();
        L10:
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{4});
        L12:
            if (this.showT >= 0.5f) goto L19;
            if (r4 == true) goto L20;
            if (r6 != null) goto L16;
            return;
        L16:
            if (r7[0] == true) goto L22;
            r7[0] = true;
            r6.run();
            return;
        L22:
            return;
        L20:
            return;
        }

        private /* synthetic */ void lambda$done$8(Integer r1) {
            onEnd(r1);
            super.dismiss();     // Catch: Exception -> L5
            return;
        }

        private /* synthetic */ void lambda$done$9(Integer r4) {
            if (r4 == null) goto L9;
            this.this$0.performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L6
        L4:
            onEndPartly(r4);
            return;
        }

        private /* synthetic */ void lambda$new$0(View r5) {
            done(Integer.valueOf((int) ((System.currentTimeMillis() / 1000) + 3600)));
        }

        private /* synthetic */ void lambda$new$1(View r5) {
            done(Integer.valueOf((int) ((System.currentTimeMillis() / 1000) + 7200)));
        }

        private /* synthetic */ void lambda$new$2(View r5) {
            done(Integer.valueOf((int) ((System.currentTimeMillis() / 1000) + 28800)));
        }

        private /* synthetic */ void lambda$new$3(View r5) {
            done(Integer.valueOf((int) ((System.currentTimeMillis() / 1000) + 172800)));
        }

        private /* synthetic */ void lambda$new$4(boolean[] r3, int r4) {
            r3[0] = true;
            done(Integer.valueOf(r4));
        }

        private /* synthetic */ void lambda$new$5(boolean[] r1, DialogInterface r2) {
            if (r1[0] == true) goto L5;
            animateMenuShow(true, null);
        L5:
            this.dateBottomSheet = null;
        }

        private /* synthetic */ void lambda$new$6(Context r5, View r6) {
            if (this.dateBottomSheet == null) goto L5;
            return;
        L5:
            boolean[] r62 = new boolean[1];
            BottomSheet.Builder r52 = AlertsCreator.createStatusUntilDatePickerDialog(r5, System.currentTimeMillis() / 1000, new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda10(this, r62));
            r52.setOnPreDismissListener(new SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda11(this, r62));
            this.dateBottomSheet = r52.show();
            animateMenuShow(false, null);
        }

        private /* synthetic */ WindowInsets lambda$new$7(View r1, WindowInsets r2) {
            this.lastInsets = r2;
            r1.requestLayout();
            if (Build.VERSION.SDK_INT < 30) goto L7;
            return WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0.m();
        L7:
            return r2.consumeSystemWindowInsets();
        }

        private void prepareBlurBitmap() {
            Activity r0 = getParentActivity();
            if (r0 != null) goto L5;
            return;
        L5:
            View r1 = r0.getWindow().getDecorView();
            int r2 = (int) (r1.getMeasuredWidth() / 12.0f);
            int r3 = (int) (r1.getMeasuredHeight() / 12.0f);
            Bitmap r4 = Bitmap.createBitmap(r2, r3, Bitmap.Config.ARGB_8888);
            Canvas r5 = new Canvas(r4);
            r5.scale(0.083333336f, 0.083333336f);
            r5.drawColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r1.draw(r5);
            if ((r0 instanceof LaunchActivity) == false) goto L10;
            LaunchActivity r02 = (LaunchActivity) r0;
            if (r02.getActionBarLayout().getLastFragment().getVisibleDialog() == null) goto L10;
            r02.getActionBarLayout().getLastFragment().getVisibleDialog().getWindow().getDecorView().draw(r5);
        L10:
            View r03 = this.parentDialogView;
            if (r03 == null) goto L13;
            r03.getLocationOnScreen(this.tempLocation);
            r5.save();
            int[] r04 = this.tempLocation;
            r5.translate(r04[0], r04[1]);
            this.parentDialogView.draw(r5);
            r5.restore();
        L13:
            Utilities.stackBlurBitmap(r4, Math.max(10, Math.max(r2, r3) / 180));
            this.blurBitmapPaint = new Paint(1);
            this.blurBitmap = r4;
        }

        @Override // android.app.Dialog, android.content.DialogInterface
        public void dismiss() {
            if (this.dismissed == false) goto L5;
            return;
        L5:
            done(null);
            this.dismissed = true;
        }

        @Override // android.app.Dialog, android.view.Window.Callback
        public boolean dispatchTouchEvent(MotionEvent r2) {
            boolean r0 = super.dispatchTouchEvent(r2);
            if (r0 == false) goto L5;
        L8:
            return r0;
        L5:
            if (r2.getAction() != 0) goto L8;
            dismiss();
            return false;
        }

        public abstract boolean getOutBounds(Rect r1);

        public abstract void onEnd(Integer r1);

        public abstract void onEndPartly(Integer r1);

        @Override // android.app.Dialog
        public void show() {
            super.show();
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{4});
            animateShow(true, null, null, true);
            animateMenuShow(true, null);
        }
    }

    /* loaded from: classes6.dex */
    public static class SetTitleDocument extends TLRPC.Document {
        public final CharSequence title;

        public SetTitleDocument(CharSequence r1) {
            this.title = r1;
        }
    }

    /* loaded from: classes6.dex */
    public interface onLongPressedListener {
        void onLongPressed(ImageViewEmoji r1);
    }

    /* loaded from: classes6.dex */
    public interface onRecentClearedListener {
        void onRecentCleared();
    }

    public static /* synthetic */ void $r8$lambda$0D2laTzvKisWXka1j2YIeRkK0ts(SelectAnimatedEmojiDialog r0, int r1, View r2, int r3) {
        r0.lambda$new$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$0s0faJmIGFzJW3z5m0YrA457sDQ(SelectAnimatedEmojiDialog r0, String r1, ArrayList r2, ArrayList r3, Runnable r4) {
        r0.lambda$search$16(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$32XlQzJvx0RyZ8GI8SM4zOtVfSA(SelectAnimatedEmojiDialog r0, BaseFragment r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$5KtTOhRICh2S1gqRnx5v5MKKGiQ(SelectAnimatedEmojiDialog r0, boolean r1, LinkedHashSet r2, String r3, HashMap r4, ArrayList r5, Runnable r6) {
        r0.lambda$search$26(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$6GKuFT1gt28Pg1368553xZds4z0(SelectAnimatedEmojiDialog r0, String r1, LinkedHashSet r2, Runnable r3) {
        r0.lambda$search$22(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$6xlRZArwPDtzFtriqZn3lluOdK4(SelectAnimatedEmojiDialog r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$9OngJl0RsnEj3pAPIqNz6GKPADM(SelectAnimatedEmojiDialog r0, ValueAnimator r1) {
        r0.lambda$onDismiss$38(r1);
    }

    public static /* synthetic */ void $r8$lambda$B7kocktSFA79pc0fP05CIwbNrbg(SelectAnimatedEmojiDialog r0, String r1, ArrayList r2, Runnable r3) {
        r0.lambda$search$29(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$FuBXHWWf9JVCxxU1a40EgHId8mY(SelectAnimatedEmojiDialog r0, String r1, boolean r2, ArrayList r3, HashMap r4, ArrayList r5, LinkedHashSet r6, LinkedHashSet r7, ArrayList r8, ArrayList r9, boolean r10) {
        r0.lambda$search$12(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    /* renamed from: $r8$lambda$GIYyWF4QW-JObs3FyNSJshjwPCQ, reason: not valid java name */
    public static /* synthetic */ void m18613$r8$lambda$GIYyWF4QWJObs3FyNSJshjwPCQ(SelectAnimatedEmojiDialog r0, LinkedHashSet r1, HashMap r2, ArrayList r3, Runnable r4, ArrayList r5, String r6) {
        r0.lambda$search$25(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$GPI3NbDqsFbZtP7UOVWzayLAD_4(SelectAnimatedEmojiDialog r0) {
        r0.lambda$new$33();
    }

    public static /* synthetic */ void $r8$lambda$MG1f_Mlp0SbAkgGM5_34RG_OFHQ(SelectAnimatedEmojiDialog r0, Rect r1, ImageViewEmoji r2, boolean[] r3, Runnable r4, AnimatedEmojiDrawable r5, ValueAnimator r6) {
        r0.lambda$animateEmojiSelect$7(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$MNGFQn7VkhDLQO6QBkT9fAi3d3c(SelectAnimatedEmojiDialog r0, String r1, ArrayList r2, HashMap r3, Runnable r4) {
        r0.lambda$search$28(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$NVtZW5iEdQ69mj8H25xkh_YEWp4(SelectAnimatedEmojiDialog r0, LinkedHashSet r1, Runnable r2, ArrayList r3) {
        r0.lambda$search$23(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$OoEVRIOoZ9luIK-qwgXBgPt8cmk, reason: not valid java name */
    public static /* synthetic */ void m18614$r8$lambda$OoEVRIOoZ9luIKqwgXBgPt8cmk(HashMap r4, HashMap r5, ArrayList r6, Runnable r7, ArrayList r8, String r9) {
        int r92 = r8.size();
        int r0 = 0;
    L3:
        if (r0 >= r92) goto L15;
        String r1 = ((MediaDataController.KeywordResult) r8.get(r0)).emoji;
        if (r4 == null) goto L7;
        ArrayList r2 = (ArrayList) r4.get(r1);
    L8:
        if (r2 == null) goto L14;
        if (r2.isEmpty() == true) goto L14;
        if (r5.containsKey(r2) == true) goto L14;
        r5.put(r2, r1);
        r6.add(r2);
    L14:
        r0 = r0 + 1;
        goto L3
    L7:
        r2 = null;
        goto L8
    L15:
        r7.run();
    }

    public static /* synthetic */ void $r8$lambda$Uqn0EM85MByBpD0DweJ2uY4FGXA(LinkedHashSet r0, Runnable r1, TLRPC.TL_emojiList r2) {
        if (r2 == null) goto L4;
        r0.addAll(r2.document_id);
    L4:
        r1.run();
    }

    /* renamed from: $r8$lambda$VKMY-pDE_W_yBxnpVqWF92a-xnI, reason: not valid java name */
    public static /* synthetic */ void m18615$r8$lambda$VKMYpDE_W_yBxnpVqWF92axnI(SelectAnimatedEmojiDialog r0, ValueAnimator r1) {
        r0.lambda$switchGrids$9(r1);
    }

    /* renamed from: $r8$lambda$VMU2m0LjilSQzUDgVZbg-HqTAKA, reason: not valid java name */
    public static /* synthetic */ void m18616$r8$lambda$VMU2m0LjilSQzUDgVZbgHqTAKA(SelectAnimatedEmojiDialog r0, boolean r1, ValueAnimator r2) {
        r0.lambda$switchGrids$8(r1, r2);
    }

    /* renamed from: $r8$lambda$XPqQmNWUnSvpwrTLvAOCN-TdBME, reason: not valid java name */
    public static /* synthetic */ void m18617$r8$lambda$XPqQmNWUnSvpwrTLvAOCNTdBME(SelectAnimatedEmojiDialog r0, String r1, LinkedHashSet r2, Runnable r3) {
        r0.lambda$search$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$XXMyX8y4tvU2bBI1EgTxhkaYHj4(SelectAnimatedEmojiDialog r0, ArrayList r1, ArrayList r2, Runnable r3, ArrayList r4, String r5) {
        r0.lambda$search$17(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$XfwmaOcsdSw1sG8NfQDmf2aD5SU(View r1) {
        r1.setScaleX(1.0f);
        r1.setScaleY(1.0f);
    }

    /* renamed from: $r8$lambda$XuMii2UOgsL--dJZe_8u-b1wpHk, reason: not valid java name */
    public static /* synthetic */ void m18618$r8$lambda$XuMii2UOgsLdJZe_8ub1wpHk(SelectAnimatedEmojiDialog r0, View r1, AnimatedEmojiSpan r2, TLRPC.Document r3, ImageViewEmoji r4) {
        r0.lambda$onEmojiClick$31(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$YJiXd7kX-vKjTnAfqA55Z3czxGI, reason: not valid java name */
    public static /* synthetic */ void m18619$r8$lambda$YJiXd7kXvKjTnAfqA55Z3czxGI(SelectAnimatedEmojiDialog r0) {
        r0.lambda$search$11();
    }

    public static /* synthetic */ void $r8$lambda$baA_nVQ7T0MBeRahwTaPA7hqN4M(SelectAnimatedEmojiDialog r0) {
        r0.lambda$new$34();
    }

    /* renamed from: $r8$lambda$gsT1muwh7-DLtr0fqJHJVlf5XL4, reason: not valid java name */
    public static /* synthetic */ void m18620$r8$lambda$gsT1muwh7DLtr0fqJHJVlf5XL4(SelectAnimatedEmojiDialog r0) {
        r0.lambda$onShow$37();
    }

    /* renamed from: $r8$lambda$hvWZ_rkTRaRRe-Z7IlqOjFqVUR4, reason: not valid java name */
    public static /* synthetic */ void m18621$r8$lambda$hvWZ_rkTRaRReZ7IlqOjFqVUR4(SelectAnimatedEmojiDialog r0, ValueAnimator r1) {
        r0.lambda$onShow$35(r1);
    }

    public static /* synthetic */ void $r8$lambda$ix6x7eXmFYQHpyghveJtC6iB_24(SelectAnimatedEmojiDialog r0, String r1, ArrayList r2, ArrayList r3, Runnable r4) {
        r0.lambda$search$18(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$mIOY5-v_EUKjKuafjwiiIKdaSGI, reason: not valid java name */
    public static /* synthetic */ void m18622$r8$lambda$mIOY5v_EUKjKuafjwiiIKdaSGI(SelectAnimatedEmojiDialog r0, String r1, boolean r2, boolean r3, String[] r4) {
        r0.lambda$search$30(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$nMdf9SYCdSK9ps6KLP8KilCmWzA(LinkedHashSet r0, Runnable r1, ArrayList r2) {
        if (r2 == null) goto L4;
        r0.addAll(r2);
    L4:
        r1.run();
    }

    public static /* synthetic */ void $r8$lambda$oE0xTFpVIBp_a965tQFMs2jVRFo(LinkedHashSet r2, Runnable r3, ArrayList r4, String r5) {
        int r52 = 0;
    L16:
        if (r52 >= r4.size()) goto L13;
        if (((MediaDataController.KeywordResult) r4.get(r52)).emoji.startsWith("animated_") == true) goto L12;
        String r0 = Emoji.fixEmoji(((MediaDataController.KeywordResult) r4.get(r52)).emoji);     // Catch: Exception -> L15
        if (Emoji.getEmojiDrawable(r0) == null) goto L12;
        r2.add(r0);     // Catch: Exception -> L15
    L12:
        r52 = r52 + 1;
    L13:
        r3.run();
    }

    public static /* synthetic */ boolean $r8$lambda$pmi9lcAr684oDQOWa8wKtJY7hSk(SelectAnimatedEmojiDialog r0, View r1, MotionEvent r2) {
        return r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$sOP_NJF8u7Iht3Gl9bIy-RR13Xk, reason: not valid java name */
    public static /* synthetic */ void m18623$r8$lambda$sOP_NJF8u7Iht3Gl9bIyRR13Xk(SelectAnimatedEmojiDialog r0, String[] r1, String r2, LinkedHashSet r3, Runnable r4) {
        r0.lambda$search$24(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$smc53Vs-6EYdcOQF8kjDghZN_x0, reason: not valid java name */
    public static /* synthetic */ boolean m18624$r8$lambda$smc53Vs6EYdcOQF8kjDghZN_x0(SelectAnimatedEmojiDialog r0, View r1) {
        return r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$sypj8YmOATIF-nCMHpl3GfOpNiM, reason: not valid java name */
    public static /* synthetic */ void m18625$r8$lambda$sypj8YmOATIFnCMHpl3GfOpNiM(SelectAnimatedEmojiDialog r0, String r1, boolean r2, ArrayList r3, HashMap r4, ArrayList r5, LinkedHashSet r6, LinkedHashSet r7, ArrayList r8, ArrayList r9, boolean r10, Runnable r11) {
        r0.lambda$search$13(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
    }

    public static /* synthetic */ void $r8$lambda$tOJFwxPo8uHNfjvaHP3PnaP38_I(SelectAnimatedEmojiDialog r0, AlertDialog r1, int r2) {
        r0.lambda$onRecentLongClick$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vKBERbIBFU3MF8WpleF4ta0yvt4(boolean r2, String r3, LinkedHashSet r4, Runnable r5) {
        if (r2 == false) goto L5;
        StickerCategoriesListView.search.fetch(UserConfig.selectedAccount, r3, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda32(r4, r5));
        return;
    L5:
        r5.run();
    }

    /* renamed from: $r8$lambda$vvEuOX1TxG5jPl3Mvv3BDnTMJ-o, reason: not valid java name */
    public static /* synthetic */ void m18626$r8$lambda$vvEuOX1TxG5jPl3Mvv3BDnTMJo(SelectAnimatedEmojiDialog r0, boolean r1, ValueAnimator r2) {
        r0.lambda$switchSearchEmptyView$10(r1, r2);
    }

    /* renamed from: $r8$lambda$x1x5qyHWqXLA2qp8mQFq-_C9rSg, reason: not valid java name */
    public static /* synthetic */ void m18627$r8$lambda$x1x5qyHWqXLA2qp8mQFq_C9rSg(SelectAnimatedEmojiDialog r0) {
        r0.lambda$onShow$36();
    }

    public static /* synthetic */ void $r8$lambda$yWHSaNFhn753rBDRf7DBSX0FY8Y(SelectAnimatedEmojiDialog r0, float r1, int r2) {
        r0.lambda$expand$32(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaccentColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18628$$Nest$fgetaccentColor(SelectAnimatedEmojiDialog r0) {
        return r0.accentColor;
    }

    /* renamed from: -$$Nest$fgetanimateExpandFromButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18629$$Nest$fgetanimateExpandFromButton(SelectAnimatedEmojiDialog r0) {
        return r0.animateExpandFromButton;
    }

    /* renamed from: -$$Nest$fgetanimateExpandFromButtonTranslate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18630$$Nest$fgetanimateExpandFromButtonTranslate(SelectAnimatedEmojiDialog r0) {
        return r0.animateExpandFromButtonTranslate;
    }

    /* renamed from: -$$Nest$fgetanimateExpandFromPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18631$$Nest$fgetanimateExpandFromPosition(SelectAnimatedEmojiDialog r0) {
        return r0.animateExpandFromPosition;
    }

    /* renamed from: -$$Nest$fgetanimateExpandStartTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m18632$$Nest$fgetanimateExpandStartTime(SelectAnimatedEmojiDialog r2) {
        return r2.animateExpandStartTime;
    }

    /* renamed from: -$$Nest$fgetanimateExpandToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18633$$Nest$fgetanimateExpandToPosition(SelectAnimatedEmojiDialog r0) {
        return r0.animateExpandToPosition;
    }

    /* renamed from: -$$Nest$fgetbackgroundDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackgroundDelegate m18634$$Nest$fgetbackgroundDelegate(SelectAnimatedEmojiDialog r0) {
        return r0.backgroundDelegate;
    }

    /* renamed from: -$$Nest$fgetbackgroundView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18635$$Nest$fgetbackgroundView(SelectAnimatedEmojiDialog r0) {
        return r0.backgroundView;
    }

    /* renamed from: -$$Nest$fgetbaseFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m18636$$Nest$fgetbaseFragment(SelectAnimatedEmojiDialog r0) {
        return r0.baseFragment;
    }

    /* renamed from: -$$Nest$fgetbottomGradientView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18637$$Nest$fgetbottomGradientView(SelectAnimatedEmojiDialog r0) {
        return r0.bottomGradientView;
    }

    /* renamed from: -$$Nest$fgetbubble1View, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18638$$Nest$fgetbubble1View(SelectAnimatedEmojiDialog r0) {
        return r0.bubble1View;
    }

    /* renamed from: -$$Nest$fgetbubble2View, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m18639$$Nest$fgetbubble2View(SelectAnimatedEmojiDialog r0) {
        return r0.bubble2View;
    }

    /* renamed from: -$$Nest$fgetcachedEmojiTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiTabsStrip[] m18640$$Nest$fgetcachedEmojiTabs(SelectAnimatedEmojiDialog r0) {
        return r0.cachedEmojiTabs;
    }

    /* renamed from: -$$Nest$fgetcommentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCaption m18641$$Nest$fgetcommentView(SelectAnimatedEmojiDialog r0) {
        return r0.commentView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18642$$Nest$fgetcurrentAccount(SelectAnimatedEmojiDialog r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdefaultStatuses, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18643$$Nest$fgetdefaultStatuses(SelectAnimatedEmojiDialog r0) {
        return r0.defaultStatuses;
    }

    /* renamed from: -$$Nest$fgetdefaultTopicIconRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18644$$Nest$fgetdefaultTopicIconRow(SelectAnimatedEmojiDialog r0) {
        return r0.defaultTopicIconRow;
    }

    /* renamed from: -$$Nest$fgetdismiss, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m18645$$Nest$fgetdismiss(SelectAnimatedEmojiDialog r0) {
        return r0.dismiss;
    }

    /* renamed from: -$$Nest$fgetdrawBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18646$$Nest$fgetdrawBackground(SelectAnimatedEmojiDialog r0) {
        return r0.drawBackground;
    }

    /* renamed from: -$$Nest$fgetdrawableToBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m18647$$Nest$fgetdrawableToBounds(SelectAnimatedEmojiDialog r0) {
        return r0.drawableToBounds;
    }

    /* renamed from: -$$Nest$fgetforumIconDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m18648$$Nest$fgetforumIconDrawable(SelectAnimatedEmojiDialog r0) {
        return r0.forumIconDrawable;
    }

    /* renamed from: -$$Nest$fgetgifts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18649$$Nest$fgetgifts(SelectAnimatedEmojiDialog r0) {
        return r0.gifts;
    }

    /* renamed from: -$$Nest$fgetgiftsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18650$$Nest$fgetgiftsEndRow(SelectAnimatedEmojiDialog r0) {
        return r0.giftsEndRow;
    }

    /* renamed from: -$$Nest$fgetgiftsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18651$$Nest$fgetgiftsSectionRow(SelectAnimatedEmojiDialog r0) {
        return r0.giftsSectionRow;
    }

    /* renamed from: -$$Nest$fgetgiftsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18652$$Nest$fgetgiftsStartRow(SelectAnimatedEmojiDialog r0) {
        return r0.giftsStartRow;
    }

    /* renamed from: -$$Nest$fgethintExpireDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Integer m18653$$Nest$fgethintExpireDate(SelectAnimatedEmojiDialog r0) {
        return r0.hintExpireDate;
    }

    /* renamed from: -$$Nest$fgetincludeEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18654$$Nest$fgetincludeEmpty(SelectAnimatedEmojiDialog r0) {
        return r0.includeEmpty;
    }

    /* renamed from: -$$Nest$fgetinstalledEmojiSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18655$$Nest$fgetinstalledEmojiSets(SelectAnimatedEmojiDialog r0) {
        return r0.installedEmojiSets;
    }

    /* renamed from: -$$Nest$fgetisAttached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18656$$Nest$fgetisAttached(SelectAnimatedEmojiDialog r0) {
        return r0.isAttached;
    }

    /* renamed from: -$$Nest$fgetisLongPressEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18657$$Nest$fgetisLongPressEnabled(SelectAnimatedEmojiDialog r0) {
        return r0.isLongPressEnabled;
    }

    /* renamed from: -$$Nest$fgetlastQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m18658$$Nest$fgetlastQuery(SelectAnimatedEmojiDialog r0) {
        return r0.lastQuery;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m18659$$Nest$fgetlayoutManager(SelectAnimatedEmojiDialog r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlongtapHintRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18660$$Nest$fgetlongtapHintRow(SelectAnimatedEmojiDialog r0) {
        return r0.longtapHintRow;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m18661$$Nest$fgetnotificationsLocker(SelectAnimatedEmojiDialog r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetpacks, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18662$$Nest$fgetpacks(SelectAnimatedEmojiDialog r0) {
        return r0.packs;
    }

    /* renamed from: -$$Nest$fgetpopularSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18663$$Nest$fgetpopularSectionRow(SelectAnimatedEmojiDialog r0) {
        return r0.popularSectionRow;
    }

    /* renamed from: -$$Nest$fgetpositionToButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m18664$$Nest$fgetpositionToButton(SelectAnimatedEmojiDialog r0) {
        return r0.positionToButton;
    }

    /* renamed from: -$$Nest$fgetpositionToExpand, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m18665$$Nest$fgetpositionToExpand(SelectAnimatedEmojiDialog r0) {
        return r0.positionToExpand;
    }

    /* renamed from: -$$Nest$fgetpositionToSection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m18666$$Nest$fgetpositionToSection(SelectAnimatedEmojiDialog r0) {
        return r0.positionToSection;
    }

    /* renamed from: -$$Nest$fgetpremiumStarColorFilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColorFilter m18667$$Nest$fgetpremiumStarColorFilter(SelectAnimatedEmojiDialog r0) {
        return r0.premiumStarColorFilter;
    }

    /* renamed from: -$$Nest$fgetrecent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18668$$Nest$fgetrecent(SelectAnimatedEmojiDialog r0) {
        return r0.recent;
    }

    /* renamed from: -$$Nest$fgetrecentExpandButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiPackExpand m18669$$Nest$fgetrecentExpandButton(SelectAnimatedEmojiDialog r0) {
        return r0.recentExpandButton;
    }

    /* renamed from: -$$Nest$fgetrecentExpanded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18670$$Nest$fgetrecentExpanded(SelectAnimatedEmojiDialog r0) {
        return r0.recentExpanded;
    }

    /* renamed from: -$$Nest$fgetrecentReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18671$$Nest$fgetrecentReactions(SelectAnimatedEmojiDialog r0) {
        return r0.recentReactions;
    }

    /* renamed from: -$$Nest$fgetrecentReactionsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18672$$Nest$fgetrecentReactionsEndRow(SelectAnimatedEmojiDialog r0) {
        return r0.recentReactionsEndRow;
    }

    /* renamed from: -$$Nest$fgetrecentReactionsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18673$$Nest$fgetrecentReactionsSectionRow(SelectAnimatedEmojiDialog r0) {
        return r0.recentReactionsSectionRow;
    }

    /* renamed from: -$$Nest$fgetrecentReactionsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18674$$Nest$fgetrecentReactionsStartRow(SelectAnimatedEmojiDialog r0) {
        return r0.recentReactionsStartRow;
    }

    /* renamed from: -$$Nest$fgetrecentStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18675$$Nest$fgetrecentStickers(SelectAnimatedEmojiDialog r0) {
        return r0.recentStickers;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m18676$$Nest$fgetresourcesProvider(SelectAnimatedEmojiDialog r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetrowHashCodes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18677$$Nest$fgetrowHashCodes(SelectAnimatedEmojiDialog r0) {
        return r0.rowHashCodes;
    }

    /* renamed from: -$$Nest$fgetscaleX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18678$$Nest$fgetscaleX(SelectAnimatedEmojiDialog r0) {
        return r0.scaleX;
    }

    /* renamed from: -$$Nest$fgetscaleY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m18679$$Nest$fgetscaleY(SelectAnimatedEmojiDialog r0) {
        return r0.scaleY;
    }

    /* renamed from: -$$Nest$fgetscrimColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18680$$Nest$fgetscrimColor(SelectAnimatedEmojiDialog r0) {
        return r0.scrimColor;
    }

    /* renamed from: -$$Nest$fgetscrimDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m18681$$Nest$fgetscrimDrawable(SelectAnimatedEmojiDialog r0) {
        return r0.scrimDrawable;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m18682$$Nest$fgetsearchAdapter(SelectAnimatedEmojiDialog r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18683$$Nest$fgetsearchResult(SelectAnimatedEmojiDialog r0) {
        return r0.searchResult;
    }

    /* renamed from: -$$Nest$fgetsearchResultStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18684$$Nest$fgetsearchResultStickers(SelectAnimatedEmojiDialog r0) {
        return r0.searchResultStickers;
    }

    /* renamed from: -$$Nest$fgetsearchRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18685$$Nest$fgetsearchRow(SelectAnimatedEmojiDialog r0) {
        return r0.searchRow;
    }

    /* renamed from: -$$Nest$fgetsearchSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18686$$Nest$fgetsearchSets(SelectAnimatedEmojiDialog r0) {
        return r0.searchSets;
    }

    /* renamed from: -$$Nest$fgetsectionToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m18687$$Nest$fgetsectionToPosition(SelectAnimatedEmojiDialog r0) {
        return r0.sectionToPosition;
    }

    /* renamed from: -$$Nest$fgetselectStatusDateDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectStatusDurationDialog m18688$$Nest$fgetselectStatusDateDialog(SelectAnimatedEmojiDialog r0) {
        return r0.selectStatusDateDialog;
    }

    /* renamed from: -$$Nest$fgetshowAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m18689$$Nest$fgetshowAnimator(SelectAnimatedEmojiDialog r0) {
        return r0.showAnimator;
    }

    /* renamed from: -$$Nest$fgetshowStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18690$$Nest$fgetshowStickers(SelectAnimatedEmojiDialog r0) {
        return r0.showStickers;
    }

    /* renamed from: -$$Nest$fgetsmoothScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18691$$Nest$fgetsmoothScrolling(SelectAnimatedEmojiDialog r0) {
        return r0.smoothScrolling;
    }

    /* renamed from: -$$Nest$fgetstandardEmojis, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18692$$Nest$fgetstandardEmojis(SelectAnimatedEmojiDialog r0) {
        return r0.standardEmojis;
    }

    /* renamed from: -$$Nest$fgetstickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18693$$Nest$fgetstickers(SelectAnimatedEmojiDialog r0) {
        return r0.stickers;
    }

    /* renamed from: -$$Nest$fgetstickersEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18694$$Nest$fgetstickersEndRow(SelectAnimatedEmojiDialog r0) {
        return r0.stickersEndRow;
    }

    /* renamed from: -$$Nest$fgetstickersSearchResult, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18695$$Nest$fgetstickersSearchResult(SelectAnimatedEmojiDialog r0) {
        return r0.stickersSearchResult;
    }

    /* renamed from: -$$Nest$fgetstickersSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18696$$Nest$fgetstickersSectionRow(SelectAnimatedEmojiDialog r0) {
        return r0.stickersSectionRow;
    }

    /* renamed from: -$$Nest$fgetstickersStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18697$$Nest$fgetstickersStartRow(SelectAnimatedEmojiDialog r0) {
        return r0.stickersStartRow;
    }

    /* renamed from: -$$Nest$fgettopMarginDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18698$$Nest$fgettopMarginDp(SelectAnimatedEmojiDialog r0) {
        return r0.topMarginDp;
    }

    /* renamed from: -$$Nest$fgettopReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m18699$$Nest$fgettopReactions(SelectAnimatedEmojiDialog r0) {
        return r0.topReactions;
    }

    /* renamed from: -$$Nest$fgettopReactionsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18700$$Nest$fgettopReactionsEndRow(SelectAnimatedEmojiDialog r0) {
        return r0.topReactionsEndRow;
    }

    /* renamed from: -$$Nest$fgettopReactionsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18701$$Nest$fgettopReactionsStartRow(SelectAnimatedEmojiDialog r0) {
        return r0.topReactionsStartRow;
    }

    /* renamed from: -$$Nest$fgettopicEmojiHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18702$$Nest$fgettopicEmojiHeaderRow(SelectAnimatedEmojiDialog r0) {
        return r0.topicEmojiHeaderRow;
    }

    /* renamed from: -$$Nest$fgettotalCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18703$$Nest$fgettotalCount(SelectAnimatedEmojiDialog r0) {
        return r0.totalCount;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18704$$Nest$fgettype(SelectAnimatedEmojiDialog r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fputemojiSelectView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18705$$Nest$fputemojiSelectView(SelectAnimatedEmojiDialog r0, ImageViewEmoji r1) {
        r0.emojiSelectView = r1;
    }

    /* renamed from: -$$Nest$fputforumIconImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18706$$Nest$fputforumIconImage(SelectAnimatedEmojiDialog r0, ImageViewEmoji r1) {
        r0.forumIconImage = r1;
    }

    /* renamed from: -$$Nest$fputgridSwitchAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18707$$Nest$fputgridSwitchAnimator(SelectAnimatedEmojiDialog r0, ValueAnimator r1) {
        r0.gridSwitchAnimator = r1;
    }

    /* renamed from: -$$Nest$fputrecentExpandButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18708$$Nest$fputrecentExpandButton(SelectAnimatedEmojiDialog r0, EmojiPackExpand r1) {
        r0.recentExpandButton = r1;
    }

    /* renamed from: -$$Nest$fputsearchEmptyViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18709$$Nest$fputsearchEmptyViewAnimator(SelectAnimatedEmojiDialog r0, ValueAnimator r1) {
        r0.searchEmptyViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputselectStatusDateDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18710$$Nest$fputselectStatusDateDialog(SelectAnimatedEmojiDialog r0, SelectStatusDurationDialog r1) {
        r0.selectStatusDateDialog = r1;
    }

    /* renamed from: -$$Nest$fputshowStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18711$$Nest$fputshowStickers(SelectAnimatedEmojiDialog r0, boolean r1) {
        r0.showStickers = r1;
    }

    /* renamed from: -$$Nest$fputsmoothScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18712$$Nest$fputsmoothScrolling(SelectAnimatedEmojiDialog r0, boolean r1) {
        r0.smoothScrolling = r1;
    }

    /* renamed from: -$$Nest$mcheckScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18713$$Nest$mcheckScroll(SelectAnimatedEmojiDialog r0) {
        r0.checkScroll();
    }

    /* renamed from: -$$Nest$menterAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18714$$Nest$menterAnimationInProgress(SelectAnimatedEmojiDialog r0) {
        return r0.enterAnimationInProgress();
    }

    /* renamed from: -$$Nest$mgetCacheType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18715$$Nest$mgetCacheType(SelectAnimatedEmojiDialog r0) {
        return r0.getCacheType();
    }

    /* renamed from: -$$Nest$mgetPremiumStar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m18716$$Nest$mgetPremiumStar(SelectAnimatedEmojiDialog r0) {
        return r0.getPremiumStar();
    }

    /* renamed from: -$$Nest$mincrementHintUse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18717$$Nest$mincrementHintUse(SelectAnimatedEmojiDialog r0) {
        r0.incrementHintUse();
    }

    /* renamed from: -$$Nest$mscrollToPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18718$$Nest$mscrollToPosition(SelectAnimatedEmojiDialog r0, int r1, int r2) {
        r0.scrollToPosition(r1, r2);
    }

    /* renamed from: -$$Nest$msetBigReactionAnimatedEmoji, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18719$$Nest$msetBigReactionAnimatedEmoji(SelectAnimatedEmojiDialog r0, AnimatedEmojiDrawable r1) {
        r0.setBigReactionAnimatedEmoji(r1);
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18720$$Nest$mupdateRows(SelectAnimatedEmojiDialog r0, boolean r1, boolean r2) {
        r0.updateRows(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18721$$Nest$mupdateRows(SelectAnimatedEmojiDialog r0, boolean r1, boolean r2, boolean r3) {
        r0.updateRows(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateSearchBox, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18722$$Nest$mupdateSearchBox(SelectAnimatedEmojiDialog r0) {
        r0.updateSearchBox();
    }

    /* renamed from: -$$Nest$mupdateShow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18723$$Nest$mupdateShow(SelectAnimatedEmojiDialog r0, float r1) {
        r0.updateShow(r1);
    }

    /* renamed from: -$$Nest$mupdateTabsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18724$$Nest$mupdateTabsPosition(SelectAnimatedEmojiDialog r0, int r1) {
        r0.updateTabsPosition(r1);
    }

    static {
        emptyViewEmojis = Arrays.asList(new String[]{"😖", "😫", "🫠", "😨", "❓"});
        preloaded = new boolean[16];
        isFirstOpen = true;
        listStates = new HashMap();
    }

    public SelectAnimatedEmojiDialog(BaseFragment r10, Context r11, boolean r12, Integer r13, int r14, Theme.ResourcesProvider r15) {
        this(r10, r11, r12, r13, r14, true, r15, 16);
    }

    public SelectAnimatedEmojiDialog(BaseFragment r12, Context r13, boolean r14, Integer r15, int r16, boolean r17, Theme.ResourcesProvider r18, int r19) {
        this(r12, r13, r14, r15, r16, r17, r18, r19, Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, r18));
    }

    public SelectAnimatedEmojiDialog(BaseFragment r12, Context r13, boolean r14, Integer r15, int r16, boolean r17, Theme.ResourcesProvider r18, int r19, int r20) {
        this(r12, r13, r14, r15, r16, r17, r18, r19, r20, false);
    }

    public SelectAnimatedEmojiDialog(BaseFragment r43, Context r44, boolean r45, Integer r46, int r47, boolean r48, Theme.ResourcesProvider r49, int r50, int r51, boolean r52) {
        BaseFragment r12 = r43;
        Theme.ResourcesProvider r5 = r49;
        int r10 = r51;
        super(r44);
        this.SPAN_COUNT_FOR_EMOJI = 8;
        this.SPAN_COUNT_FOR_STICKER = 5;
        this.SPAN_COUNT = 40;
        this.RECENT_MAX_LINES = 5;
        this.EXPAND_MAX_LINES = 3;
        this.selectedReactions = new HashSet();
        this.selectedDocumentIds = new HashSet();
        this.selectorPaint = new Paint(1);
        this.selectorAccentPaint = new Paint(1);
        this.stickerSets = new ArrayList();
        this.currentAccount = UserConfig.selectedAccount;
        this.cachedEmojiTabs = new EmojiTabsStrip[2];
        this.rowHashCodes = new ArrayList();
        this.positionToSection = new SparseIntArray();
        this.sectionToPosition = new SparseIntArray();
        this.positionToExpand = new SparseIntArray();
        this.positionToButton = new SparseIntArray();
        this.expandedEmojiSets = new ArrayList();
        this.installedEmojiSets = new ArrayList();
        this.recentExpanded = false;
        this.recent = new ArrayList();
        this.gifts = new ArrayList();
        this.recentStickers = new ArrayList();
        this.standardEmojis = new ArrayList();
        this.topReactions = new ArrayList();
        this.recentReactions = new ArrayList();
        this.stickers = new ArrayList();
        this.defaultStatuses = new ArrayList();
        this.frozenEmojiPacks = new ArrayList();
        this.packs = new ArrayList();
        this.includeEmpty = false;
        this.includeHint = false;
        this.drawBackground = true;
        this.bigReactionImageReceiver = new ImageReceiver();
        this.isLongPressEnabled = true;
        this.maxDim = 0.25f;
        this.scrimAlpha = 1.0f;
        this.emojiSelectAlpha = 1.0f;
        this.overshootInterpolator = new OvershootInterpolator(2.0f);
        this.topGradientShown = false;
        this.bottomGradientShown = false;
        this.smoothScrolling = false;
        this.searching = false;
        this.searched = false;
        this.searchedLiftUp = false;
        this.gridSearch = false;
        this.searchEmptyViewVisible = false;
        this.paused = false;
        this.pausedExceptSelected = false;
        this.animateExpandFromPosition = -1;
        this.animateExpandToPosition = -1;
        this.animateExpandStartTime = -1;
        this.defaultSetLoading = false;
        this.updateRows = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda1(this);
        this.updateRowsDelayed = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda2(this);
        this.durationScale = 1.0f;
        this.showDuration = 800;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.paint = new Paint();
        this.badgePicker = r52;
        this.resourcesProvider = r5;
        this.type = r47;
        this.includeEmpty = r45;
        this.baseFragment = r12;
        SharedPreferences r3 = MessagesController.getGlobalMainSettings();
        StringBuilder r4 = new StringBuilder("emoji");
        if (r47 == 0) goto L10;
        if (r47 == 12) goto L10;
        if (r47 == 9) goto L10;
        if (r47 == 10) goto L10;
        String r24 = "reaction";
    L11:
        r4.append(r24);
        r4.append("usehint");
        if (r3.getInt(r4.toString(), 0) >= 3) goto L14;
        boolean r32 = true;
    L15:
        this.includeHint = r32;
        this.accentColor = r10;
        this.selectorPaint.setColor(Theme.getColor(Theme.key_listSelector, r5));
        this.selectorAccentPaint.setColor(ColorUtils.setAlphaComponent(r10, 30));
        this.premiumStarColorFilter = new PorterDuffColorFilter(r10, PorterDuff.Mode.SRC_IN);
        this.emojiX = r46;
        if (r46 != null) goto L18;
        Integer r6 = null;
    L19:
        if (r6 != null) goto L21;
    L24:
        boolean r20 = false;
    L25:
        setFocusableInTouchMode(true);
        if (r47 == 0) goto L36;
        if (r47 == 12) goto L36;
        if (r47 == 9) goto L36;
        if (r47 == 10) goto L36;
        if (r47 == 2) goto L36;
        if (r47 == 5) goto L36;
        if (r47 == 7) goto L36;
    L38:
        if (r6 == null) goto L56;
        this.bubble1View = new View(r44);
        Drawable r42 = getResources().getDrawable(R.drawable.shadowed_bubble1).mutate();
        r42.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r5), PorterDuff.Mode.MULTIPLY));
        this.bubble1View.setBackground(r42);
        View r410 = this.bubble1View;
        if (isBottom() == false) goto L42;
        int r7 = 80;
    L43:
        int r33 = r7 | 3;
        float r72 = r6.intValue() / AndroidUtilities.density;
        if (r20 == false) goto L46;
        int r9 = -12;
    L47:
        float r34 = r72 + r9;
        if (isBottom() == false) goto L50;
        int r73 = 0;
    L51:
        float r74 = r73;
        if (isBottom() == false) goto L54;
        int r92 = this.topMarginDp;
    L55:
        addView(r410, LayoutHelper.createFrame(10, 10.0f, r33, r34, r74, 0.0f, r92));
        goto L56
    L54:
        r92 = 0;
        goto L55
    L50:
        r73 = this.topMarginDp;
        goto L51
    L46:
        r9 = 4;
        goto L47
    L42:
        r7 = 48;
    L56:
        this.backgroundView = new AnonymousClass1(this, r44, r5);
        if (r47 == 3) goto L62;
        if (r47 == 4) goto L62;
        Object[] r411 = 4;
        boolean r35 = false;
    L63:
        int r11 = 2;
        boolean r25 = false;
        AnonymousClass2 r0 = new AnonymousClass2(this, r44, r35, r35, r5, r6);
        Integer r02 = r6;
        this.contentView = r0;
        if (r35 == true) goto L69;
        r0.setOutlineProvider(new AnonymousClass3(this, r02));
        this.contentView.setClipToOutline(true);
        if (r35 == false) goto L69;
        this.contentView.setElevation(2.0f);
    L69:
        if (r47 != 0) goto L71;
    L81:
        this.contentView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
    L82:
        this.contentView.addView(this.backgroundView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r36 = this.contentView;
        char r412 = 6;
        if (r47 != 0) goto L85;
    L93:
        float r37 = this.topMarginDp + 6;
    L95:
        if (isBottom() == false) goto L97;
        float r39 = this.topMarginDp + 6;
    L98:
        addView(r36, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, r37, 0.0f, r39));
        if (r02 == null) goto L117;
        this.bubble2View = new AnonymousClass4(this, r44);
        int r26 = 14;
        Drawable r38 = getResources().getDrawable(R.drawable.shadowed_bubble2_half);
        r38.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r5), PorterDuff.Mode.MULTIPLY));
        this.bubble2View.setBackground(r38);
        View r310 = this.bubble2View;
        if (isBottom() == false) goto L103;
        int r62 = 80;
    L104:
        int r372 = r62 | 3;
        float r63 = r02.intValue() / AndroidUtilities.density;
        if (r20 == false) goto L107;
        int r75 = -25;
    L108:
        float r382 = r63 + r75;
        if (isBottom() == false) goto L111;
        float r392 = 0.0f;
    L113:
        if (isBottom() == false) goto L115;
        float r41 = this.topMarginDp + 14;
    L116:
        addView(r310, LayoutHelper.createFrame(17, 9.0f, r372, r382, r392, 0.0f, r41));
    L118:
        if (r12 == null) goto L134;
        if (r47 == 3) goto L134;
        if (r47 == 6) goto L134;
        if (r47 == 5) goto L134;
        if (r47 == 7) goto L134;
        if (r47 == 4) goto L134;
        if (r47 == 9) goto L134;
        if (r47 == 10) goto L134;
        if (r48 == false) goto L134;
        boolean r19 = true;
    L135:
        int r76 = 0;
    L136:
        float r202 = 5.0f;
        if (r76 >= r11) goto L187;
        Integer r242 = r02;
        if (r47 == 4) goto L141;
        boolean r413 = true;
    L142:
        if (r52 == true) goto L154;
        if (r47 == 0) goto L152;
        if (r47 == 12) goto L152;
        if (r47 == 9) goto L152;
        if (r47 != 10) goto L154;
    L152:
        boolean r53 = true;
    L155:
        if (r19 == false) goto L158;
        SelectAnimatedEmojiDialog$$ExternalSyntheticLambda4 r311 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda4(this, r12);
    L159:
        int r383 = r76;
        boolean r13 = r25;
        AnonymousClass5 r03 = new AnonymousClass5(this, r44, r49, r413, r53, false, true, r47, r311, r10, r47);
        r5 = r49;
        EmojiTabsStrip.EmojiTabButton r312 = r03.recentTab;
        if (r312 == null) goto L162;
        r312.setOnLongClickListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda5(this));
    L162:
        r03.updateButtonDrawables = r13;
        if (r47 != 4) goto L166;
        r03.setAnimatedEmojiCacheType(13);
    L173:
        if (r242 != null) goto L175;
        boolean r414 = true;
    L176:
        r03.animateAppear = r414;
        r412 = 6;
        if (r47 != 6) goto L179;
        r202 = 10.0f;
    L179:
        r03.setPaddingLeft(r202);
        if (r47 == 14) goto L186;
        if (r47 == 8) goto L186;
        if (r47 == 13) goto L186;
        this.contentView.addView(r03, LayoutHelper.createFrame(-1, 36.0f));
    L186:
        this.cachedEmojiTabs[r383] = r03;
        r10 = r51;
        r26 = 14;
        r25 = r13;
        r11 = 2;
        r76 = r383 + 1;
        r02 = r242;
        r12 = r43;
        goto L136
    L175:
        r414 = r13;
        goto L176
    L166:
        if (r47 == 0) goto L171;
        if (r47 == 12) goto L171;
        if (r47 == 2) goto L171;
        int r93 = 5;
    L172:
        r03.setAnimatedEmojiCacheType(r93);
    L171:
        r93 = 6;
        goto L172
    L158:
        r311 = null;
    L154:
        r53 = r25;
        goto L155
    L141:
        r413 = r25;
        goto L142
    L187:
        Integer r122 = r02;
        boolean r132 = r25;
        int r77 = r26;
        EmojiTabsStrip[] r04 = this.cachedEmojiTabs;
        this.emojiTabs = r04[r132 ? 1 : 0];
        r04[1].setVisibility(8);
        AnonymousClass6 r05 = new AnonymousClass6(this, r44, r122);
        this.emojiTabsShadow = r05;
        r05.setBackgroundColor(Theme.getColor(Theme.key_divider, r5));
        if (r47 == r77) goto L193;
        if (r47 == 8) goto L193;
        if (r47 == 13) goto L193;
        float r112 = 1.0f;
        this.contentView.addView(this.emojiTabsShadow, LayoutHelper.createFrame(-1, 1.0f / AndroidUtilities.density, 48, 0.0f, 36.0f, 0.0f, 0.0f));
    L194:
        AndroidUtilities.updateViewVisibilityAnimated(this.emojiTabsShadow, true, r112, r132);
        this.emojiGridView = new AnonymousClass7(this, r44, r47);
        AnonymousClass8 r06 = new AnonymousClass8(this);
        this.emojiItemAnimator = r06;
        float r22 = r112;
        r06.setAddDuration(220);
        this.emojiItemAnimator.setMoveDuration(260);
        this.emojiItemAnimator.setChangeDuration(160);
        this.emojiItemAnimator.setSupportsChangeAnimations(r132);
        DefaultItemAnimator r07 = this.emojiItemAnimator;
        CubicBezierInterpolator r415 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r07.setMoveInterpolator(r415);
        this.emojiItemAnimator.setDelayAnimations(r132);
        this.emojiGridView.setItemAnimator(this.emojiItemAnimator);
        this.emojiGridView.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(38.0f));
        Adapter r08 = new Adapter(this, null);
        this.adapter = r08;
        this.emojiGridView.setAdapter(r08);
        EmojiListView r09 = this.emojiGridView;
        AnonymousClass9 r113 = new AnonymousClass9(this, r44, 40);
        this.layoutManager = r113;
        r09.setLayoutManager(r113);
        this.layoutManager.setSpanSizeLookup(new AnonymousClass10(this));
        this.gridViewContainer = new AnonymousClass11(this, r44);
        AnonymousClass12 r010 = new AnonymousClass12(this, r44);
        this.emojiGridViewContainer = r010;
        r010.addView(this.emojiGridView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 0.0f));
        this.gridViewContainer.addView(this.emojiGridViewContainer, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 0.0f));
        AnonymousClass13 r011 = new AnonymousClass13(this, r44);
        this.emojiSearchGridView = r011;
        if (r011.getItemAnimator() == null) goto L197;
        this.emojiSearchGridView.getItemAnimator().setDurations(180);
        this.emojiSearchGridView.getItemAnimator().setMoveInterpolator(r415);
    L197:
        TextView r012 = new TextView(r44);
        if (r47 != 4) goto L200;
        r012.setText(LocaleController.getString(R.string.NoEmojiOrStickersFound));
    L220:
        r012.setTextSize(1, 14.0f);
        r012.setTextColor(Theme.getColor(Theme.key_chat_emojiPanelEmptyText, r5));
        this.emojiSearchEmptyViewImageView = new BackupImageView(r44);
        FrameLayout r313 = new FrameLayout(r44);
        this.emojiSearchEmptyView = r313;
        r313.addView(this.emojiSearchEmptyViewImageView, LayoutHelper.createFrame(36, 36.0f, 49, 0.0f, 16.0f, 0.0f, 0.0f));
        this.emojiSearchEmptyView.addView(r012, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 60.0f, 0.0f, 0.0f));
        this.emojiSearchEmptyView.setVisibility(8);
        this.emojiSearchEmptyView.setAlpha(0.0f);
        this.gridViewContainer.addView(this.emojiSearchEmptyView, LayoutHelper.createFrame(-1, -2.0f, 16, 0.0f, 0.0f, 0.0f, 0.0f));
        this.emojiSearchGridView.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(54.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(38.0f));
        EmojiListView r013 = this.emojiSearchGridView;
        SearchAdapter r314 = new SearchAdapter(this, null);
        this.searchAdapter = r314;
        r013.setAdapter(r314);
        EmojiListView r014 = this.emojiSearchGridView;
        AnonymousClass14 r315 = new AnonymousClass14(this, r44, 40);
        this.searchLayoutManager = r315;
        r014.setLayoutManager(r315);
        this.searchLayoutManager.setSpanSizeLookup(new AnonymousClass15(this));
        this.emojiSearchGridView.setVisibility(8);
        this.gridViewContainer.addView(this.emojiSearchGridView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 0.0f));
        FrameLayout r015 = this.contentView;
        FrameLayout r316 = this.gridViewContainer;
        if (r47 != 8) goto L223;
    L227:
        float r30 = 0.0f;
    L228:
        if (r52 == false) goto L230;
        float r322 = 48.0f;
    L231:
        r015.addView(r316, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, r30, 0.0f, r322));
        if (r52 == false) goto L239;
        FrameLayout r016 = new FrameLayout(r44);
        r016.setBackgroundColor(Theme.getColor(Theme.key_chat_emojiPanelBackground, r5));
        EditTextCaption r317 = new EditTextCaption(r44, r5);
        this.commentView = r317;
        r317.setHint(LocaleController.getString(R.string.Caption));
        this.commentView.setTextSize(1, 16.0f);
        this.commentView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r5));
        this.commentView.setHintTextColor(Theme.getColor(Theme.key_chat_emojiSearchIcon, r5));
        this.commentView.setCursorColor(Theme.getColor(Theme.key_featuredStickers_addedIcon, r5));
        this.commentView.setCursorWidth(1.5f);
        this.commentView.setCursorSize(AndroidUtilities.dp(20.0f));
        this.commentView.setBackground(null);
        this.commentView.setSingleLine(true);
        this.commentView.setLines(1);
        this.commentView.setMaxLines(1);
        BadgeDTO r318 = BadgesController.INSTANCE.getBadge(UserConfig.getInstance(this.currentAccount).getCurrentUser());
        if (r318 != null) goto L236;
    L238:
        r016.addView(this.commentView, LayoutHelper.createFrame(-1, -2.0f, 16, 16.0f, 0.0f, 16.0f, 0.0f));
        this.contentView.addView(r016, LayoutHelper.createFrame(-1, 48, 80));
        goto L239
    L236:
        if (r318.getText() == null) goto L238;
        this.commentView.setText(r318.getText());
    L239:
        RecyclerAnimationScrollHelper r017 = new RecyclerAnimationScrollHelper(this.emojiGridView, this.layoutManager);
        this.scrollHelper = r017;
        r017.setAnimationCallback(new AnonymousClass16(this));
        this.scrollHelper.setScrollListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda6(this));
        AnonymousClass17 r018 = new AnonymousClass17(this, r47, r52, r44, r5, r46);
        this.emojiGridView.setOnItemLongClickListener(r018, (long) (ViewConfiguration.getLongPressTimeout() * 0.25f));
        this.emojiSearchGridView.setOnItemLongClickListener(r018, (long) (ViewConfiguration.getLongPressTimeout() * 0.25f));
        SelectAnimatedEmojiDialog$$ExternalSyntheticLambda7 r019 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda7(this, r47);
        this.emojiGridView.setOnItemClickListener(r019);
        this.emojiSearchGridView.setOnItemClickListener(r019);
        AnonymousClass18 r020 = new AnonymousClass18(this, r44, r48);
        this.searchBox = r020;
        r020.setTranslationY(-AndroidUtilities.dp(52.0f));
        this.searchBox.setVisibility(4);
        this.gridViewContainer.addView(this.searchBox, LayoutHelper.createFrame(-1, 52.0f, 48, 0.0f, -4.0f, 0.0f, 0.0f));
        this.topGradientView = new AnonymousClass19(this, r44, r122);
        Drawable r021 = getResources().getDrawable(R.drawable.gradient_top);
        int r416 = Theme.key_actionBarDefaultSubmenuBackground;
        int r64 = AndroidUtilities.multiplyAlphaComponent(Theme.getColor(r416, r5), 0.8f);
        PorterDuff.Mode r102 = PorterDuff.Mode.SRC_IN;
        r021.setColorFilter(new PorterDuffColorFilter(r64, r102));
        this.topGradientView.setBackground(r021);
        this.topGradientView.setAlpha(0.0f);
        this.contentView.addView(this.topGradientView, LayoutHelper.createFrame(-1, 20.0f, 55, 0.0f, (r22 / AndroidUtilities.density) + 36.0f, 0.0f, 0.0f));
        this.bottomGradientView = new View(r44);
        Drawable r022 = getResources().getDrawable(R.drawable.gradient_bottom);
        r022.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r416, r5), r102));
        if (r47 != r77) goto L242;
        this.bottomGradientView.setBackground(r022);
    L242:
        this.bottomGradientView.setAlpha(0.0f);
        this.contentView.addView(this.bottomGradientView, LayoutHelper.createFrame(-1, 20, 87));
        View r023 = new View(r44);
        this.contentViewForeground = r023;
        r023.setAlpha(0.0f);
        this.contentViewForeground.setBackgroundColor(-16777216);
        this.contentView.addView(this.contentViewForeground, LayoutHelper.createFrame(-1, -1.0f));
        preload(r47, this.currentAccount);
        this.bigReactionImageReceiver.setLayerNum(7);
        if (isAnimatedShow() == false) goto L245;
        HwEmojis.beforePreparing();
    L245:
        updateRows(true, false);
        return;
    L230:
        r322 = 0.0f;
        goto L231
    L223:
        if (r47 == 13) goto L227;
        if (r47 == r77) goto L227;
        r30 = (r22 / AndroidUtilities.density) + 36.0f;
        goto L228
    L200:
        if (r47 != 0) goto L202;
    L219:
        r012.setText(LocaleController.getString(R.string.NoEmojiFound));
        goto L220
    L202:
        if (r47 == 13) goto L219;
        if (r47 == 12) goto L219;
        if (r47 == 11) goto L219;
        if (r47 == 9) goto L219;
        if (r47 == 10) goto L219;
        if (r47 != 1) goto L213;
    L218:
        r012.setText(LocaleController.getString(R.string.NoReactionsFound));
        goto L220
    L213:
        if (r47 == 2) goto L218;
        if (r47 != r77) goto L217;
        r012.setText(LocaleController.getString(R.string.NoEffectsFound));
        goto L220
    L217:
        r012.setText(LocaleController.getString(R.string.NoIconsFound));
    L193:
        r112 = 1.0f;
    L134:
        r19 = false;
        goto L135
    L115:
        r41 = 0.0f;
        goto L116
    L111:
        r392 = this.topMarginDp + 5;
        goto L113
    L107:
        r75 = 10;
        goto L108
    L103:
        r62 = 48;
        goto L104
    L117:
        r26 = 14;
        goto L118
    L97:
        r39 = 0.0f;
        goto L98
    L85:
        if (r47 == 12) goto L93;
        if (r47 == 9) goto L93;
        if (r47 == 2) goto L93;
        if (r47 == 7) goto L93;
        r37 = 0.0f;
        goto L95
    L71:
        if (r47 == 12) goto L81;
        if (r47 == 9) goto L81;
        if (r47 == 10) goto L81;
        if (r47 == 2) goto L81;
        if (r47 == 5) goto L81;
        if (r47 != 15) goto L82;
    L62:
        r411 = 4;
        r35 = true;
    L36:
        this.topMarginDp = r50;
        setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        setOnTouchListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda3(this));
        goto L38
    L21:
        if (r6.intValue() <= AndroidUtilities.dp(170.0f)) goto L24;
        r20 = true;
        goto L25
    L18:
        r6 = Integer.valueOf(MathUtils.clamp(r46.intValue(), AndroidUtilities.dp(26.0f), AndroidUtilities.dp(292.0f)));
        goto L19
    L14:
        r32 = false;
    L10:
        r24 = "status";
        goto L11
    }

    private void checkScroll() {
        if (this.gridSearch == false) goto L5;
        EmojiListView r0 = this.emojiSearchGridView;
    L6:
        boolean r02 = r0.canScrollVertically(1);
        if (r02 == this.bottomGradientShown) goto L14;
        this.bottomGradientShown = r02;
        ViewPropertyAnimator r2 = this.bottomGradientView.animate();
        if (r02 == false) goto L11;
        float r03 = 1.0f;
    L12:
        r2.alpha(r03).setDuration(200).start();
        return;
    L11:
        r03 = 0.0f;
        goto L12
    L14:
        return;
    L5:
        r0 = this.emojiGridView;
        goto L6
    }

    private boolean enterAnimationInProgress() {
        if (this.enterAnimationInProgress == true) goto L11;
        ValueAnimator r1 = this.showAnimator;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isRunning() == true) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    private ArrayList<TLRPC.Document> filter(ArrayList<TLRPC.Document> r3, HashSet<Long> r4) {
        if (r4 != null) goto L4;
        return r3;
    L4:
        int r2 = 0;
    L6:
        if (r2 >= r3.size()) goto L13;
        TLRPC.Document r0 = r3.get(r2);
        if (r0 != null) goto L10;
    L11:
        r3.remove(r2);
        r2 = r2 - 1;
    L12:
        r2 = r2 + 1;
        goto L6
    L10:
        if (r4.contains(Long.valueOf(r0.id)) == false) goto L12;
    L13:
        return r3;
    }

    public static TLRPC.Document findSticker(TLRPC.TL_messages_stickerSet r6, String r7) {
        if (r6 != null) goto L5;
        return null;
    L5:
        String r72 = Emoji.fixEmoji(r7);
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r2 >= r6.packs.size()) goto L14;
        if (r6.packs.get(r2).documents.isEmpty() == true) goto L13;
        if (TextUtils.equals(Emoji.fixEmoji(r6.packs.get(r2).emoticon), r72) == false) goto L13;
        long r22 = r6.packs.get(r2).documents.get(0).longValue();
    L16:
        if (r22 != 0) goto L19;
        return null;
    L19:
        if (r1 >= r6.documents.size()) goto L24;
        TLRPC.Document r73 = r6.documents.get(r1);
        if (r73.id == r22) goto L22;
        r1 = r1 + 1;
        goto L19
    L22:
        return r73;
    L24:
        return null;
    L13:
        r2 = r2 + 1;
        goto L7
    L14:
        r22 = 0;
        goto L16
    }

    private int getCacheType() {
        int r3 = this.type;
        int r1 = 13;
        if (r3 != 5) goto L5;
    L27:
        return r1;
    L5:
        if (r3 == 7) goto L27;
        if (r3 != 6) goto L12;
        return AnimatedEmojiDrawable.getCacheTypeForEnterView();
    L12:
        if (r3 == 3) goto L27;
        if (r3 == 4) goto L27;
        r1 = 2;
        if (r3 == 0) goto L27;
        if (r3 == 12) goto L27;
        if (r3 == 9) goto L27;
        if (r3 == 10) goto L27;
        if (r3 == 2) goto L27;
        return 3;
    }

    private Drawable getPremiumStar() {
        if (this.premiumStar == null) goto L5;
    L23:
        ColorFilter r0 = this.premiumStarColorFilter;
        if (r0 == null) goto L29;
        Drawable r1 = this.premiumStar;
        if ((r1 instanceof AnimatedEmojiDrawable) == false) goto L29;
        r1.setColorFilter(r0);
    L29:
        return this.premiumStar;
    L5:
        if (this.badgePicker == true) goto L7;
    L9:
        int r02 = this.type;
        if (r02 != 5) goto L12;
    L21:
        this.premiumStar = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.msg_filled_blocked).mutate();
    L22:
        this.premiumStar.setColorFilter(this.premiumStarColorFilter);
        goto L23
    L12:
        if (r02 == 9) goto L21;
        if (r02 == 10) goto L21;
        if (r02 == 7) goto L21;
        if (r02 == 15) goto L21;
        this.premiumStar = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.msg_settings_premium).mutate();
        goto L22
    L7:
        if (this.defaultBadgeId == 0) goto L9;
        this.premiumStar = AnimatedEmojiDrawable.make(this.currentAccount, getCacheType(), this.defaultBadgeId);
        goto L22
    }

    private void incrementHintUse() {
        int r0 = this.type;
        if (r0 != 2) goto L5;
        return;
    L5:
        if (r0 == 15) goto L24;
        StringBuilder r02 = new StringBuilder("emoji");
        int r2 = this.type;
        if (r2 != 0) goto L10;
    L17:
        String r22 = "status";
    L18:
        r02.append(r22);
        r02.append("usehint");
        String r23 = r02.toString();
        int r03 = MessagesController.getGlobalMainSettings().getInt(r23, 0);
        if (r03 > 3) goto L23;
        MessagesController.getGlobalMainSettings().edit().putInt(r23, r03 + 1).apply();
        return;
    L23:
        return;
    L10:
        if (r2 == 12) goto L17;
        if (r2 == 9) goto L17;
        if (r2 == 10) goto L17;
        r22 = "reaction";
        goto L18
    }

    private boolean isAnimatedShow() {
        int r1 = this.type;
        if (r1 != 3) goto L5;
        return false;
    L5:
        if (r1 != 4) goto L7;
        return false;
    L7:
        if (r1 == 6) goto L13;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$animateEmojiSelect$7(Rect r7, ImageViewEmoji r8, boolean[] r9, Runnable r10, AnimatedEmojiDrawable r11, ValueAnimator r12) {
        float r122 = ((Float) r12.getAnimatedValue()).floatValue();
        this.scrimAlpha = 1.0f - ((r122 * r122) * r122);
        this.emojiSelectAlpha = 1.0f - ((float) Math.pow(r122, 10.0d));
        AndroidUtilities.lerp(r7, this.drawableToBounds, r122, this.emojiSelectRect);
        float r72 = Math.max(1.0f, this.overshootInterpolator.getInterpolation(MathUtils.clamp((3.0f * r122) - 2.0f, 0.0f, 1.0f))) * r8.getScaleX();
        this.emojiSelectRect.set((int) (r8.centerX() - ((this.emojiSelectRect.width() / 2.0f) * r72)), (int) (this.emojiSelectRect.centerY() - ((this.emojiSelectRect.height() / 2.0f) * r72)), (int) (this.emojiSelectRect.centerX() + ((this.emojiSelectRect.width() / 2.0f) * r72)), (int) (this.emojiSelectRect.centerY() + ((this.emojiSelectRect.height() / 2.0f) * r72)));
        invalidate();
        if (r122 > 0.85f) goto L5;
        return;
    L5:
        if (r9[0] == true) goto L13;
        r9[0] = true;
        r10.run();
        if (r11 == null) goto L14;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r6 = this.scrimDrawable;
        if (r6 == null) goto L15;
        r6.play();
        return;
    L15:
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$expand$32(float r4, int r5) {
        LinearSmoothScrollerCustom r0 = new LinearSmoothScrollerCustom(this.emojiGridView.getContext(), 0, r4);     // Catch: Exception -> L4
        r0.setTargetPosition(r5);     // Catch: Exception -> L4
        this.layoutManager.startSmoothScroll(r0);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ boolean lambda$new$0(View r1, MotionEvent r2) {
        if (r2.getAction() != 0) goto L8;
        Runnable r0 = this.dismiss;
        if (r0 == null) goto L10;
        r0.run();
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    private /* synthetic */ void lambda$new$1(BaseFragment r4) {
        search(null, false, false);
        onSettings();
        r4.presentFragment(new StickersActivity(5, this.frozenEmojiPacks));
        Runnable r3 = this.dismiss;
        if (r3 == null) goto L6;
        r3.run();
        return;
    }

    private /* synthetic */ boolean lambda$new$2(View r2) {
        onRecentLongClick();
        performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L5
    L4:
        return true;
    }

    private /* synthetic */ void lambda$new$3() {
        invalidateParent();
    }

    private /* synthetic */ void lambda$new$33() {
        updateRows(true, true);
    }

    private /* synthetic */ void lambda$new$34() {
        NotificationCenter.getGlobalInstance().removeDelayed(this.updateRows);
        NotificationCenter.getGlobalInstance().doOnIdle(this.updateRows);
    }

    private /* synthetic */ void lambda$new$4(int r5, View r6, int r7) {
        if ((r6 instanceof ImageViewEmoji) == false) goto L27;
        ImageViewEmoji r62 = (ImageViewEmoji) r6;
        if (r62.isDefaultReaction == true) goto L21;
        ReactionsLayoutInBubble.VisibleReaction r72 = r62.reaction;
        if (r72 == null) goto L11;
        if (r72.isStar == true) goto L21;
    L11:
        if (r5 == 13) goto L21;
        if (r5 == 14) goto L21;
        if (r62.isStaticIcon == false) goto L20;
        TLRPC.Document r73 = r62.document;
        if (r73 == null) goto L20;
        onStickerClick(r62, r73);
    L22:
        if (r5 == 1) goto L45;
        if (r5 == 11) goto L46;
        performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L43
        return;
    L46:
        return;
    L45:
        return;
    L20:
        onEmojiClick(r62, r62.span);
    L21:
        incrementHintUse();
        onReactionClick(r62, r62.reaction);
        goto L22
    L27:
        if ((r6 instanceof ImageView) == false) goto L34;
        onEmojiClick(r6, null);
        if (r5 == 1) goto L47;
        if (r5 == 11) goto L48;
        performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L43
        return;
    L48:
        return;
    L47:
        return;
    L34:
        if ((r6 instanceof EmojiPackExpand) == false) goto L40;
        expand(r7, (EmojiPackExpand) r6);
        if (r5 == 1) goto L49;
        if (r5 == 11) goto L50;
        performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L43
        return;
    L50:
        return;
    L49:
        return;
    L40:
        if (r6 == null) goto L51;
        r6.callOnClick();
        return;
    L51:
        return;
    }

    private /* synthetic */ void lambda$onDismiss$38(ValueAnimator r3) {
        float r32 = 1.0f - ((Float) r3.getAnimatedValue()).floatValue();
        setTranslationY(AndroidUtilities.dp(8.0f) * (1.0f - r32));
        View r0 = this.bubble1View;
        if (r0 == null) goto L5;
        r0.setAlpha(r32);
    L5:
        View r02 = this.bubble2View;
        if (r02 == null) goto L8;
        r02.setAlpha(r32 * r32);
    L8:
        this.contentView.setAlpha(r32);
        this.contentView.invalidate();
        invalidate();
    }

    private /* synthetic */ void lambda$onEmojiClick$31(View r10, AnimatedEmojiSpan r11, TLRPC.Document r12, ImageViewEmoji r13) {
        Long r4 = Long.valueOf(r11.documentId);
        TL_stars.TL_starGiftUnique r6 = r13.starGift;
        EditTextCaption r112 = this.commentView;
        if (r112 == null) goto L6;
        String r113 = r112.getText().toString();
    L7:
        onEmojiSelected(r10, r4, r12, r6, null, r113);
        return;
    L6:
        r113 = null;
        goto L7
    }

    private /* synthetic */ void lambda$onRecentLongClick$5(AlertDialog r1, int r2) {
        onRecentClearedListener r0 = this.onRecentClearedListener;
        if (r0 == null) goto L6;
        r0.onRecentCleared();
        return;
    }

    private /* synthetic */ void lambda$onShow$35(ValueAnimator r1) {
        updateShow(((Float) r1.getAnimatedValue()).floatValue());
    }

    private /* synthetic */ void lambda$onShow$36() {
        this.showAnimator.start();
    }

    private /* synthetic */ void lambda$onShow$37() {
        HwEmojis.enableHw();
        AndroidUtilities.runOnUIThread(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda17(this), 0);
    }

    private /* synthetic */ void lambda$search$11() {
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r0 = this.searchResult;
        if (r0 == null) goto L5;
        r0.clear();
    L5:
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r02 = this.searchResultStickers;
        if (r02 == null) goto L8;
        r02.clear();
    L8:
        ArrayList<TLRPC.Document> r03 = this.searchSets;
        if (r03 == null) goto L11;
        r03.clear();
    L11:
        this.searchAdapter.updateRows(true);
    }

    private /* synthetic */ void lambda$search$12(String r4, boolean r5, ArrayList r6, HashMap r7, ArrayList r8, LinkedHashSet r9, LinkedHashSet r10, ArrayList r11, ArrayList r12, boolean r13) {
        Runnable r0 = this.clearSearchRunnable;
        if (r0 == null) goto L6;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.clearSearchRunnable = null;
    L6:
        if (r4 == this.lastQuery) goto L8;
        return;
    L8:
        this.searched = true;
        switchGrids(true, r5);
        SearchBox r52 = this.searchBox;
        int r1 = 0;
        if (r52 == null) goto L11;
        r52.showProgress(false);
    L11:
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r53 = this.searchResult;
        if (r53 != null) goto L14;
        this.searchResult = new ArrayList();
    L15:
        ArrayList<TLRPC.Document> r54 = this.searchSets;
        if (r54 != null) goto L18;
        this.searchSets = new ArrayList();
    L19:
        ArrayList<TLRPC.Document> r55 = this.stickersSearchResult;
        if (r55 != null) goto L22;
        this.stickersSearchResult = new ArrayList();
    L23:
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r56 = this.searchResultStickers;
        if (r56 != null) goto L26;
        this.searchResultStickers = new ArrayList();
    L27:
        this.emojiSearchGridView.scrollToPosition(0);
        int r57 = this.type;
        if (r57 == 1) goto L36;
        if (r57 == 14) goto L36;
        if (r57 == 11) goto L36;
        if (r57 == 2) goto L36;
    L44:
        Iterator r42 = r9.iterator();
    L46:
        if (r42.hasNext() == false) goto L48;
        Long r58 = (Long) r42.next();
        r58.longValue();
        this.searchResult.add(ReactionsLayoutInBubble.VisibleReaction.fromCustomEmoji(r58));
        goto L46
    L48:
        Iterator r43 = r10.iterator();
    L50:
        if (r43.hasNext() == false) goto L52;
        this.searchResult.add(ReactionsLayoutInBubble.VisibleReaction.fromEmojicon((String) r43.next()));
        goto L50
    L52:
        this.searchSets.addAll(r11);
        int r44 = r12.size();
    L53:
        if (r1 >= r44) goto L55;
        Object r59 = r12.get(r1);
        r1 = r1 + 1;
        this.stickersSearchResult.addAll((ArrayList) r59);
        goto L53
    L55:
        this.searchAdapter.updateRows(!r13);
        return;
    L36:
        if (r6.isEmpty() == true) goto L38;
        this.searchResult.addAll(r6);
    L42:
        if (r8.isEmpty() == true) goto L44;
        this.searchResultStickers.addAll(r8);
        goto L44
    L38:
        TLRPC.TL_availableReaction r45 = (TLRPC.TL_availableReaction) r7.get(r4);
        if (r45 == null) goto L42;
        this.searchResult.add(ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r45));
        goto L42
    L26:
        r56.clear();
        goto L27
    L22:
        r55.clear();
        goto L23
    L18:
        r54.clear();
        goto L19
    L14:
        r53.clear();
        goto L15
    }

    private /* synthetic */ void lambda$search$13(String r1, boolean r2, ArrayList r3, HashMap r4, ArrayList r5, LinkedHashSet r6, LinkedHashSet r7, ArrayList r8, ArrayList r9, boolean r10, Runnable r11) {
        AndroidUtilities.runOnUIThread(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda37(this, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10));
    }

    private /* synthetic */ void lambda$search$15(String r11, LinkedHashSet r12, Runnable r13) {
        MediaDataController.getInstance(this.currentAccount).getEmojiSuggestions(lastSearchKeyboardLanguage, r11, false, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda33(r12, r13), null, false, false, false, 0);
    }

    private /* synthetic */ void lambda$search$16(String r7, ArrayList r8, ArrayList r9, Runnable r10) {
        TLRPC.messages_AvailableEffects r6 = MessagesController.getInstance(this.currentAccount).getAvailableEffects();
        if (r6 == null) goto L15;
        int r0 = 0;
    L18:
        if (r0 >= r6.effects.size()) goto L15;
        TLRPC.TL_availableEffect r1 = r6.effects.get(r0);     // Catch: Exception -> L17
        if (r7.contains(r1.emoticon) == false) goto L14;
        if (r1.effect_animation_id != 0) goto L12;
        ArrayList r2 = r8;
    L13:
        r2.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r1));     // Catch: Exception -> L17
        goto L14
    L12:
        r2 = r9;
    L14:
        r0 = r0 + 1;
    L15:
        r10.run();
    }

    private /* synthetic */ void lambda$search$17(ArrayList r10, ArrayList r11, Runnable r12, ArrayList r13, String r14) {
        TLRPC.messages_AvailableEffects r9 = MessagesController.getInstance(this.currentAccount).getAvailableEffects();
        HashSet r142 = new HashSet();
        if (r9 == null) goto L26;
        int r1 = 0;
    L29:
        if (r1 >= r13.size()) goto L26;
        if (((MediaDataController.KeywordResult) r13.get(r1)).emoji.startsWith("animated_") == true) goto L25;
        String r2 = Emoji.fixEmoji(((MediaDataController.KeywordResult) r13.get(r1)).emoji);     // Catch: Exception -> L28
        int r3 = 0;
    L12:
        if (r3 >= r9.effects.size()) goto L25;
        TLRPC.TL_availableEffect r4 = r9.effects.get(r3);     // Catch: Exception -> L28
        if (r142.contains(Long.valueOf(r4.id)) == true) goto L24;
        if (r4.emoticon.contains(r2) == true) goto L20;
        if (r2.contains(r4.emoticon) == false) goto L24;
    L20:
        if (r4.effect_animation_id != 0) goto L22;
        ArrayList r5 = r10;
    L23:
        r5.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r4));     // Catch: Exception -> L28
        r142.add(Long.valueOf(r4.id));     // Catch: Exception -> L28
        goto L24
    L22:
        r5 = r11;
    L24:
        r3 = r3 + 1;
    L25:
        r1 = r1 + 1;
    L26:
        r12.run();
    }

    private /* synthetic */ void lambda$search$18(String r12, ArrayList r13, ArrayList r14, Runnable r15) {
        MediaDataController.getInstance(this.currentAccount).getEmojiSuggestions(lastSearchKeyboardLanguage, r12, false, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda30(this, r13, r14, r15), null, false, false, false, 0);
    }

    private /* synthetic */ void lambda$search$22(String r2, LinkedHashSet r3, Runnable r4) {
        MediaDataController.getInstance(this.currentAccount).getAnimatedEmojiByKeywords(r2, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda31(r3, r4));
    }

    private /* synthetic */ void lambda$search$23(LinkedHashSet r4, Runnable r5, ArrayList r6) {
        AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).putDocuments(r6);
        int r3 = r6.size();
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L5;
        Object r1 = r6.get(r0);
        r0 = r0 + 1;
        r4.add(Long.valueOf(((TLRPC.Document) r1).id));
        goto L3
    L5:
        r5.run();
    }

    private /* synthetic */ void lambda$search$24(String[] r3, String r4, LinkedHashSet r5, Runnable r6) {
        if (ConnectionsManager.getInstance(this.currentAccount).getConnectionState() == 3) goto L6;
        r6.run();
        return;
    L6:
        if (r3 != null) goto L8;
    L11:
        String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        MediaDataController.getInstance(this.currentAccount).searchStickers(true, r32, r4, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda35(this, r5, r6));
        return;
    L8:
        if (r3.length == 0) goto L11;
        r32 = r3[0];
        goto L12
    }

    private /* synthetic */ void lambda$search$25(LinkedHashSet r3, HashMap r4, ArrayList r5, Runnable r6, ArrayList r7, String r8) {
        int r82 = 0;
    L21:
        if (r82 >= r7.size()) goto L18;
        if (((MediaDataController.KeywordResult) r7.get(r82)).emoji.startsWith("animated_") == false) goto L8;
        r3.add(Long.valueOf(Long.parseLong(((MediaDataController.KeywordResult) r7.get(r82)).emoji.substring(9))));     // Catch: Exception -> L20
    L17:
        r82 = r82 + 1;
        goto L21
    L8:
        int r0 = this.type;     // Catch: Exception -> L20
        if (r0 != 1) goto L11;
    L14:
        TLRPC.TL_availableReaction r02 = (TLRPC.TL_availableReaction) r4.get(((MediaDataController.KeywordResult) r7.get(r82)).emoji);     // Catch: Exception -> L20
        if (r02 == null) goto L17;
        r5.add(ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r02));     // Catch: Exception -> L20
        goto L17
    L11:
        if (r0 == 11) goto L14;
        if (r0 != 2) goto L17;
    L18:
        r6.run();
    }

    private /* synthetic */ void lambda$search$26(boolean r11, LinkedHashSet r12, String r13, HashMap r14, ArrayList r15, Runnable r16) {
        int r0 = this.currentAccount;
        if (r11 == false) goto L49;
        ArrayList<TLRPC.TL_messages_stickerSet> r112 = MediaDataController.getInstance(r0).getStickerSets(5);
        int r02 = 0;
    L6:
        if (r02 >= r112.size()) goto L25;
        if (r112.get(r02).documents == null) goto L24;
        ArrayList<TLRPC.Document> r3 = r112.get(r02).documents;
        if (r3 == null) goto L24;
        int r5 = 0;
    L15:
        if (r5 >= r3.size()) goto L24;
        String r6 = MessageObject.findAnimatedEmojiEmoticon(r3.get(r5), null);
        long r7 = r3.get(r5).id;
        if (r6 == null) goto L23;
        if (r12.contains(Long.valueOf(r7)) == true) goto L23;
        if (r13.contains(r6.toLowerCase()) == false) goto L23;
        r12.add(Long.valueOf(r7));
    L23:
        r5 = r5 + 1;
    L24:
        r02 = r02 + 1;
        goto L6
    L25:
        ArrayList<TLRPC.StickerSetCovered> r10 = MediaDataController.getInstance(this.currentAccount).getFeaturedEmojiSets();
        int r113 = 0;
    L27:
        if (r113 >= r10.size()) goto L47;
        if ((r10.get(r113) instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L46;
        if (((TLRPC.TL_stickerSetFullCovered) r10.get(r113)).keywords == null) goto L46;
        ArrayList<TLRPC.Document> r03 = ((TLRPC.TL_stickerSetFullCovered) r10.get(r113)).documents;
        if (r03 == null) goto L46;
        int r32 = 0;
    L37:
        if (r32 >= r03.size()) goto L46;
        String r52 = MessageObject.findAnimatedEmojiEmoticon(r03.get(r32), null);
        long r62 = r03.get(r32).id;
        if (r52 == null) goto L45;
        if (r12.contains(Long.valueOf(r62)) == true) goto L45;
        if (r13.contains(r52) == false) goto L45;
        r12.add(Long.valueOf(r62));
    L45:
        r32 = r32 + 1;
    L46:
        r113 = r113 + 1;
        goto L27
    L47:
        r16.run();
        return;
    L49:
        MediaDataController r04 = MediaDataController.getInstance(r0);
        String[] r1 = lastSearchKeyboardLanguage;
        SelectAnimatedEmojiDialog$$ExternalSyntheticLambda36 r33 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda36(this, r12, r14, r15, r16);
        if (this.type != 3) goto L52;
        boolean r72 = true;
    L53:
        r04.getEmojiSuggestions(r1, r13, false, r33, null, true, r72, false, 30);
        return;
    L52:
        r72 = false;
        goto L53
    }

    private /* synthetic */ void lambda$search$28(String r13, ArrayList r14, HashMap r15, Runnable r16) {
        if (this.type == 4) goto L6;
        r16.run();
        return;
    L6:
        int r1 = 0;
        ArrayList r0 = new ArrayList(0);
        LongSparseArray r2 = new LongSparseArray(0);
        HashMap<String, ArrayList<TLRPC.Document>> r3 = MediaDataController.getInstance(this.currentAccount).getAllStickers();
        if (r13.length() > 14) goto L40;
        int r4 = r13.length();
        CharSequence r7 = r13;
        int r5 = 0;
    L9:
        if (r5 >= r4) goto L30;
        if (r5 >= (r4 - 1)) goto L27;
        if (r7.charAt(r5) != 55356) goto L19;
        int r8 = r5 + 1;
        if (r7.charAt(r8) < 57339) goto L19;
        if (r7.charAt(r8) > 57343) goto L19;
    L24:
        r7 = TextUtils.concat(new CharSequence[]{r7.subSequence(0, r5), r7.subSequence(r5 + 2, r7.length())});
        r4 = r4 - 2;
    L25:
        r5 = r5 - 1;
    L29:
        r5 = r5 + 1;
    L19:
        if (r7.charAt(r5) != 8205) goto L27;
        int r82 = r5 + 1;
        if (r7.charAt(r82) == 9792) goto L24;
        if (r7.charAt(r82) == 9794) goto L24;
    L27:
        if (r7.charAt(r5) != 65039) goto L29;
        r7 = TextUtils.concat(new CharSequence[]{r7.subSequence(0, r5), r7.subSequence(r5 + 1, r7.length())});
        r4 = r4 - 1;
        goto L25
    L30:
        if (r3 == null) goto L32;
        ArrayList<TLRPC.Document> r42 = r3.get(r7.toString());
    L33:
        if (r42 == null) goto L40;
        if (r42.isEmpty() == true) goto L40;
        r0.addAll(r42);
        int r52 = r42.size();
    L37:
        if (r1 >= r52) goto L39;
        TLRPC.Document r72 = r42.get(r1);
        r2.put(r72.id, r72);
        r1 = r1 + 1;
        goto L37
    L39:
        r14.add(r0);
        goto L40
    L32:
        r42 = null;
    L40:
        if (r3 != null) goto L42;
        return;
    L42:
        if (r3.isEmpty() == false) goto L44;
        return;
    L44:
        if (r13.length() <= 1) goto L53;
        MediaDataController.getInstance(this.currentAccount).getEmojiSuggestions(lastSearchKeyboardLanguage, r13, false, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda34(r3, r15, r14, r16), false);
        return;
    }

    private /* synthetic */ void lambda$search$29(String r10, ArrayList r11, Runnable r12) {
        ArrayList<TLRPC.TL_messages_stickerSet> r0 = MediaDataController.getInstance(this.currentAccount).getStickerSets(5);
        HashSet r1 = new HashSet();
        String r102 = AndroidUtilities.translitSafe(r10);
        String r2 = " " + r102;
        int r3 = 0;
        if (r0 == null) goto L24;
        int r4 = 0;
    L6:
        if (r4 >= r0.size()) goto L24;
        TLRPC.TL_messages_stickerSet r5 = r0.get(r4);
        if (r5 == null) goto L23;
        TLRPC.StickerSet r6 = r5.set;
        if (r6 == null) goto L23;
        if (r6.title == null) goto L23;
        if (r5.documents == null) goto L23;
        if (r1.contains(Long.valueOf(r6.id)) == true) goto L23;
        String r62 = AndroidUtilities.translitSafe(r5.set.title);
        if (r62.startsWith(r102) == false) goto L21;
    L22:
        r11.add(new SetTitleDocument(r62));
        r11.addAll(r5.documents);
        r1.add(Long.valueOf(r5.set.id));
        goto L23
    L21:
        if (r62.contains(r2) == true) goto L22;
    L23:
        r4 = r4 + 1;
    L24:
        ArrayList<TLRPC.StickerSetCovered> r02 = MediaDataController.getInstance(this.currentAccount).getFeaturedEmojiSets();
        if (r02 != null) goto L27;
    L57:
        r12.run();
        return;
    L27:
        if (r3 >= r02.size()) goto L57;
        TLRPC.StickerSetCovered r42 = r02.get(r3);
        if (r42 == null) goto L56;
        TLRPC.StickerSet r52 = r42.set;
        if (r52 == null) goto L56;
        if (r52.title == null) goto L56;
        if (r1.contains(Long.valueOf(r52.id)) == true) goto L56;
        String r53 = AndroidUtilities.translitSafe(r42.set.title);
        if (r53.startsWith(r102) == true) goto L42;
        if (r53.contains(r2) == false) goto L56;
    L42:
        if ((r42 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L48;
        TLRPC.TL_messages_stickerSet r54 = MediaDataController.getInstance(this.currentAccount).getStickerSet(MediaDataController.getInputStickerSet(r42.set), Integer.valueOf(r42.set.hash), true);
        if (r54 == null) goto L46;
        ArrayList<TLRPC.Document> r55 = r54.documents;
    L51:
        if (r55 == null) goto L56;
        if (r55.size() == 0) goto L56;
        r11.add(new SetTitleDocument(r42.set.title));
        r11.addAll(r55);
        r1.add(Long.valueOf(r42.set.id));
        goto L56
    L46:
        r55 = null;
        goto L51
    L48:
        if ((r42 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L50;
        r55 = ((TLRPC.TL_stickerSetFullCovered) r42).documents;
        goto L51
    L50:
        r55 = r42.covers;
    L56:
        r3 = r3 + 1;
        goto L27
    }

    private /* synthetic */ void lambda$search$30(String r18, boolean r19, boolean r20, String[] r21) {
        LinkedHashSet r3 = new LinkedHashSet();
        LinkedHashSet r8 = new LinkedHashSet();
        HashMap<String, TLRPC.TL_availableReaction> r5 = MediaDataController.getInstance(this.currentAccount).getReactionsMap();
        ArrayList r4 = new ArrayList();
        ArrayList r6 = new ArrayList();
        boolean r12 = Emoji.fullyConsistsOfEmojis(r18);
        ArrayList r10 = new ArrayList();
        HashMap r13 = new HashMap();
        ArrayList r9 = new ArrayList();
        SelectAnimatedEmojiDialog$$ExternalSyntheticLambda20 r0 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda20(this, r18, r19, r4, r5, r6, r3, r8, r9, r10, r20);
        int r02 = this.type;
        if (r02 != 13) goto L7;
        Utilities.doCallbacks(new Utilities.Callback[]{new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda21(this, r18, r8), r0});
        return;
    L7:
        if (r02 != 14) goto L13;
        if (r12 == false) goto L10;
        Utilities.Callback r03 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda22(this, r18, r6, r4);
    L11:
        Utilities.doCallbacks(new Utilities.Callback[]{r03, r0});
        return;
    L10:
        r03 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda23(this, r18, r6, r4);
        goto L11
    L13:
        Utilities.doCallbacks(new Utilities.Callback[]{new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda24(r12, r18, r3), new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda25(this, r18, r3), new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda26(this, r21, r18, r3), new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda27(this, r12, r3, r18, r5, r4), new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda28(this, r18, r10, r13), new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda29(this, r18, r9), r0});
    }

    private /* synthetic */ void lambda$switchGrids$8(boolean r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        if (r4 == true) goto L5;
        r52 = 1.0f - r52;
    L5:
        float r0 = 1.0f - r52;
        this.emojiGridView.setAlpha(r0);
        this.emojiGridView.setTranslationY(AndroidUtilities.dp(8.0f) * r52);
        this.emojiSearchGridView.setAlpha(r52);
        this.emojiSearchGridView.setTranslationY(AndroidUtilities.dp(8.0f) * r0);
        this.emojiSearchEmptyView.setAlpha(this.emojiSearchGridView.getAlpha() * r52);
    }

    private /* synthetic */ void lambda$switchGrids$9(ValueAnimator r1) {
        invalidateParent();
    }

    private /* synthetic */ void lambda$switchSearchEmptyView$10(boolean r1, ValueAnimator r2) {
        float r22 = ((Float) r2.getAnimatedValue()).floatValue();
        if (r1 == true) goto L5;
        r22 = 1.0f - r22;
    L5:
        this.emojiSearchEmptyView.setAlpha(this.emojiSearchGridView.getAlpha() * r22);
    }

    private void onRecentLongClick() {
        AlertDialog r3 = new AlertDialog.Builder(getContext()).setTitle(LocaleController.getString(R.string.ClearRecentReactionsAlertTitle)).setMessage(LocaleController.getString(R.string.ClearRecentReactionsAlertMessage)).setPositiveButton(LocaleController.getString(R.string.ClearButton), new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda12(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        r3.show();
        TextView r32 = (TextView) r3.getButton(-1);
        if (r32 == null) goto L6;
        r32.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    }

    private void onStickerClick(ImageViewEmoji r7, TLRPC.Document r8) {
        if (this.type != 6) goto L6;
        onEmojiSelected(r7, Long.valueOf(r8.id), r8, r7.starGift, null);
        return;
    L6:
        onEmojiSelected(r7, null, r8, r7.starGift, null);
    }

    public static void preload(int r4) {
        if (preloaded[r4] == false) goto L5;
        return;
    L5:
        if (MediaDataController.getInstance(r4) == null) goto L10;
        preloaded[r4] = true;
        MediaDataController.getInstance(r4).checkStickers(5);
        MediaDataController.getInstance(r4).fetchEmojiStatuses(0, true);
        MediaDataController.getInstance(r4).checkReactions();
        MediaDataController.getInstance(r4).getStickerSet(new TLRPC.TL_inputStickerSetEmojiDefaultStatuses(), false);
        MediaDataController.getInstance(r4).getDefaultEmojiStatuses();
        MediaDataController.getInstance(r4).checkDefaultTopicIcons();
        StickerCategoriesListView.preload(r4, 1);
        return;
    }

    private void scrollToPosition(int r5, int r6) {
        View r0 = this.layoutManager.findViewByPosition(r5);
        int r1 = this.layoutManager.findFirstVisibleItemPosition();
        if (r0 != null) goto L7;
        if (Math.abs(r5 - r1) <= 72.0f) goto L7;
    L8:
        RecyclerAnimationScrollHelper r02 = this.scrollHelper;
        if (this.layoutManager.findFirstVisibleItemPosition() >= r5) goto L11;
        int r12 = 0;
    L12:
        r02.setScrollDirection(r12);
        this.scrollHelper.scrollToPosition(r5, r6, false, true);
        return;
    L11:
        r12 = 1;
    L7:
        if (SharedConfig.animationsEnabled() == false) goto L8;
        AnonymousClass21 r03 = new AnonymousClass21(this, this.emojiGridView.getContext(), 2);
        r03.setTargetPosition(r5);
        r03.setOffset(r6);
        this.layoutManager.startSmoothScroll(r03);
    }

    private void setBigReactionAnimatedEmoji(AnimatedEmojiDrawable r2) {
        if (this.isAttached == false) goto L15;
        AnimatedEmojiDrawable r0 = this.bigReactionAnimatedEmoji;
        if (r0 == r2) goto L16;
        if (r0 == null) goto L10;
        r0.removeView(this);
    L10:
        this.bigReactionAnimatedEmoji = r2;
        if (r2 == null) goto L14;
        r2.setColorFilter(this.premiumStarColorFilter);
        this.bigReactionAnimatedEmoji.addView(this);
        return;
    L14:
        return;
    L16:
        return;
    }

    private void updateRows(boolean r2, boolean r3) {
        updateRows(r2, r3, true);
    }

    private void updateRows(boolean r32, boolean r33, boolean r34) {
        if (this.animationsEnabled == true) goto L5;
        boolean r2 = false;
    L6:
        MediaDataController r4 = MediaDataController.getInstance(this.currentAccount);
        if (r4 != null) goto L10;
        return;
    L10:
        if (r32 == true) goto L14;
        if (this.frozenEmojiPacks == null) goto L14;
    L18:
        ArrayList<TLRPC.TL_messages_stickerSet> r6 = this.frozenEmojiPacks;
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L21;
    L24:
        ArrayList r7 = new ArrayList(r4.getFeaturedEmojiSets());
    L25:
        ArrayList r10 = new ArrayList(this.rowHashCodes);
        this.totalCount = 0;
        this.recentReactionsSectionRow = -1;
        this.recentReactionsStartRow = -1;
        this.giftsStartRow = -1;
        this.giftsEndRow = -1;
        this.recentReactionsEndRow = -1;
        this.popularSectionRow = -1;
        this.giftsSectionRow = -1;
        this.longtapHintRow = -1;
        this.defaultTopicIconRow = -1;
        this.topicEmojiHeaderRow = -1;
        this.stickersSectionRow = -1;
        this.stickersStartRow = -1;
        this.stickersEndRow = -1;
        this.recent.clear();
        this.defaultStatuses.clear();
        this.topReactions.clear();
        this.recentReactions.clear();
        this.packs.clear();
        this.positionToSection.clear();
        this.sectionToPosition.clear();
        this.positionToExpand.clear();
        this.rowHashCodes.clear();
        this.positionToButton.clear();
        this.stickerSets.clear();
        this.recentStickers.clear();
        this.standardEmojis.clear();
        this.gifts.clear();
        if (r6.isEmpty() == false) goto L30;
        int r12 = this.type;
        if (r12 == 4) goto L30;
        if (r12 == 15) goto L30;
    L35:
        this.searchRow = -1;
    L36:
        int r122 = this.type;
        if (r122 == 5) goto L39;
        if (r122 == 7) goto L39;
        if (r122 == 4) goto L43;
        boolean r23 = r2;
        if (r122 == 6) goto L69;
        if (r122 != 3) goto L121;
        int r22 = this.totalCount;
        this.totalCount = r22 + 1;
        this.topicEmojiHeaderRow = r22;
        this.rowHashCodes.add(12L);
        int r24 = this.totalCount;
        this.totalCount = r24 + 1;
        this.defaultTopicIconRow = r24;
        this.rowHashCodes.add(7L);
        String r25 = UserConfig.getInstance(this.currentAccount).defaultTopicIcons;
        if (r25 == null) goto L85;
        TLRPC.TL_messages_stickerSet r3 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(r25);
        if (r3 != null) goto L86;
        r3 = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName(r25);
    L86:
        if (r3 != null) goto L89;
        this.defaultSetLoading = true;
        goto L121
    L89:
        if (this.includeEmpty == false) goto L91;
        this.totalCount++;
        this.rowHashCodes.add(2L);
    L91:
        ArrayList<TLRPC.Document> r26 = r3.documents;
        if (r26 != null) goto L94;
    L99:
        int r27 = 0;
    L101:
        if (r27 >= this.recent.size()) goto L121;
        this.rowHashCodes.add(Long.valueOf((this.recent.get(r27).getDocumentId() * 13) + 43223));
        this.totalCount++;
        r27 = r27 + 1;
        goto L101
    L94:
        if (r26.isEmpty() == true) goto L99;
        int r28 = 0;
    L97:
        if (r28 >= r3.documents.size()) goto L99;
        this.recent.add(new AnimatedEmojiSpan(r3.documents.get(r28), null));
        r28 = r28 + 1;
        goto L97
    L85:
        r3 = null;
    L121:
        if (this.includeHint == true) goto L123;
    L143:
        int r29 = this.type;
        if (r29 == 9) goto L146;
        if (r29 == 10) goto L146;
    L149:
        HashSet<Long> r13 = null;
    L151:
        if (this.recentReactionsToSet == null) goto L226;
        this.topReactionsStartRow = this.totalCount;
        ArrayList r1 = new ArrayList(this.recentReactionsToSet);
        if (this.type != 13) goto L159;
        if (r1.size() <= 8) goto L159;
        r1.subList(8, r1.size()).clear();
    L157:
        boolean r242 = true;
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r210 = null;
    L168:
        int r9 = this.type;
        if (r9 == 8) goto L180;
        if (r9 == 11) goto L180;
        if (r9 == 13) goto L180;
        int r92 = 0;
    L175:
        if (r92 >= 16) goto L181;
        if (r1.isEmpty() == true) goto L179;
        this.topReactions.add((ReactionsLayoutInBubble.VisibleReaction) r1.remove(0));
    L179:
        r92 = r92 + 1;
    L181:
        int r93 = 0;
    L183:
        if (r93 >= this.topReactions.size()) goto L185;
        this.rowHashCodes.add(Long.valueOf((this.topReactions.get(r93).hashCode() * 13) - 5632));
        r93 = r93 + 1;
        r13 = r13;
        goto L183
    L185:
        HashSet<Long> r272 = r13;
        int r94 = this.totalCount + this.topReactions.size();
        this.totalCount = r94;
        this.topReactionsEndRow = r94;
        if (r1.isEmpty() == true) goto L216;
        int r95 = this.type;
        if (r95 == 8) goto L216;
        if (r95 == 11) goto L216;
        if (r95 == 13) goto L216;
        int r35 = 0;
    L193:
        if (r35 >= r1.size()) goto L198;
        if (((ReactionsLayoutInBubble.VisibleReaction) r1.get(r35)).documentId != 0) goto L196;
        r35 = r35 + 1;
        goto L193
    L196:
        boolean r36 = false;
    L200:
        if (this.type == 14) goto L207;
        if (r36 == true) goto L204;
        int r96 = this.totalCount;
        this.totalCount = r96 + 1;
        this.recentReactionsSectionRow = r96;
        this.rowHashCodes.add(4L);
        goto L207
    L204:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L207;
        int r97 = this.totalCount;
        this.totalCount = r97 + 1;
        this.popularSectionRow = r97;
        this.rowHashCodes.add(5L);
    L207:
        this.recentReactionsStartRow = this.totalCount;
        this.recentReactions.addAll(r1);
        int r14 = 0;
    L209:
        if (r14 >= this.recentReactions.size()) goto L215;
        ArrayList<Long> r98 = this.rowHashCodes;
        if (r36 == false) goto L213;
        int r11 = 4235;
    L214:
        r98.add(Long.valueOf(r11 + (this.recentReactions.get(r14).hash * 13)));
        r14 = r14 + 1;
        r6 = r6;
        goto L209
    L213:
        r11 = -3142;
        goto L214
    L215:
        ArrayList<TLRPC.TL_messages_stickerSet> r15 = r6;
        int r16 = this.totalCount + this.recentReactions.size();
        this.totalCount = r16;
        this.recentReactionsEndRow = r16;
    L217:
        if (r210 != null) goto L219;
    L225:
        ArrayList<TLRPC.TL_messages_stickerSet> r282 = r15;
    L353:
        this.gifts.clear();
        long r211 = 322;
        if (this.badgePicker == true) goto L376;
        int r17 = this.type;
        if (r17 != 0) goto L358;
    L363:
        StarsController.GiftsList r18 = StarsController.getInstance(this.currentAccount).getProfileGiftsList(getDialogId());
        r18.load();
        ArrayList r8 = new ArrayList();
        ArrayList<TL_stars.SavedStarGift> r19 = r18.gifts;
        int r99 = r19.size();
        int r112 = 0;
    L364:
        if (r112 >= r99) goto L369;
        TL_stars.SavedStarGift r123 = r19.get(r112);
        r112 = r112 + 1;
        TL_stars.StarGift r124 = r123.gift;
        if ((r124 instanceof TL_stars.TL_starGiftUnique) == false) goto L364;
        r8.add((TL_stars.TL_starGiftUnique) r124);
        goto L364
    L369:
        if (r8.isEmpty() == true) goto L374;
        int r110 = this.totalCount;
        this.totalCount = r110 + 1;
        this.giftsSectionRow = r110;
        this.rowHashCodes.add(22L);
        this.giftsStartRow = this.totalCount;
        int r111 = r8.size();
        int r910 = 0;
    L371:
        if (r910 >= r111) goto L373;
        Object r113 = r8.get(r910);
        r910 = r910 + 1;
        TL_stars.TL_starGiftUnique r114 = (TL_stars.TL_starGiftUnique) r113;
        this.rowHashCodes.add(Long.valueOf((r114.id * 322) + 13334));
        this.totalCount++;
        this.gifts.add(r114);
        goto L371
    L373:
        this.giftsEndRow = this.totalCount;
        this.emojiTabs.showGifts(true);
        goto L376
    L374:
        this.emojiTabs.showGifts(false);
        goto L376
    L358:
        if (r17 == 12) goto L363;
        if (r17 == 9) goto L363;
        if (r17 == 10) goto L363;
    L376:
        if (this.type != 13) goto L383;
        String[][] r115 = EmojiData.dataColored;
        int r82 = r115.length;
        int r911 = 0;
    L378:
        if (r911 >= r82) goto L383;
        String[] r116 = r115[r911];
        int r125 = r116.length;
        int r132 = 0;
    L380:
        if (r132 >= r125) goto L382;
        this.standardEmojis.add(r116[r132]);
        long r162 = r211;
        this.rowHashCodes.add(Long.valueOf((r14.hashCode() * r162) + 13334));
        this.totalCount++;
        r132 = r132 + 1;
        r211 = r162;
        goto L380
    L382:
        r911 = r911 + 1;
    L383:
        int r117 = this.type;
        long r212 = 9211;
        long r5 = 3212;
        if (r117 != 8) goto L386;
    L427:
        long r163 = r212;
        HashSet<Long> r133 = r272;
        if (r7 != null) goto L430;
    L508:
        int r118 = this.type;
        if (r118 != 14) goto L511;
    L515:
        EmojiListView r119 = this.emojiGridView;
        if (r23 == false) goto L518;
        r119.setItemAnimator(this.emojiItemAnimator);
    L519:
        if (r34 == false) goto L521;
        int r1110 = 0;
        DiffUtil.calculateDiff(new AnonymousClass24(this, r10), false).dispatchUpdatesTo(this.adapter);
    L522:
        EmojiListView r0 = this.emojiGridView;
        if (r0.scrolledByUserOnce == true) goto L590;
        r0.scrollToPosition(r1110);
        return;
    L590:
        return;
    L521:
        r1110 = 0;
        this.adapter.notifyDataSetChanged();
        goto L522
    L518:
        r119.setItemAnimator(null);
        goto L519
    L511:
        if (r118 == 8) goto L515;
        if (r118 == 13) goto L515;
        this.emojiTabs.updateEmojiPacks(this.packs);
        goto L515
    L430:
        if (this.showStickers == true) goto L508;
        int r120 = this.type;
        if (r120 == 8) goto L508;
        if (r120 == 13) goto L508;
        if (r120 == 14) goto L508;
        int r1111 = 0;
    L439:
        if (r1111 >= r7.size()) goto L508;
        TLRPC.StickerSetCovered r121 = (TLRPC.StickerSetCovered) r7.get(r1111);
        TLRPC.StickerSet r213 = r121.set;
        int r37 = 0;
    L442:
        if (r37 >= this.packs.size()) goto L449;
        if (this.packs.get(r37).set.id == r213.id) goto L445;
        r37 = r37 + 1;
    L445:
        long r252 = r5;
    L507:
        r1111 = r1111 + 1;
        r5 = r252;
        goto L439
    L449:
        if ((r121 instanceof TLRPC.TL_stickerSetNoCovered) == false) goto L455;
        TLRPC.TL_messages_stickerSet r38 = r4.getStickerSet(MediaDataController.getInputStickerSet(r121.set), Integer.valueOf(r121.set.hash), true);
        if (r38 == null) goto L453;
        ArrayList<TLRPC.Document> r126 = r38.documents;
        boolean r83 = MessageObject.isPremiumEmojiPack(r38);
    L457:
        TLRPC.InputStickerSet r39 = null;
    L459:
        if (r126 == null) goto L445;
        int r912 = this.type;
        if (r912 == 5) goto L466;
        if (r912 == 7) goto L466;
    L470:
        int r913 = this.type;
        if (r913 == 10) goto L474;
        if (r913 == 9) goto L474;
    L476:
        r252 = r5;
        this.positionToSection.put(this.totalCount, this.packs.size());
        this.sectionToPosition.put(this.packs.size(), this.totalCount);
        this.totalCount++;
        this.rowHashCodes.add(Long.valueOf((r213.id * 13) + r163));
        EmojiView.EmojiPack r52 = new EmojiView.EmojiPack();
        r52.needLoadSet = r39;
        r52.installed = this.installedEmojiSets.contains(Long.valueOf(r213.id));
        r52.featured = true;
        if (this.type != 4) goto L479;
        r52.free = false;
    L480:
        r52.set = r213;
        r52.documents = filter(r126, r133);
        r52.index = this.packs.size();
        r52.expanded = this.expandedEmojiSets.contains(Long.valueOf(r52.set.id));
        if (r52.documents.size() > 24) goto L483;
    L489:
        this.totalCount += r52.documents.size();
        int r127 = 0;
    L491:
        if (r127 >= r52.documents.size()) goto L494;
        this.rowHashCodes.add(Long.valueOf((r52.documents.get(r127).id * 13) + r252));
        r127 = r127 + 1;
    L494:
        if (r52.installed == true) goto L506;
        int r128 = this.type;
        if (r128 == 4) goto L506;
        if (r128 == 5) goto L506;
        if (r128 == 7) goto L506;
        if (r128 == 6) goto L506;
        this.positionToButton.put(this.totalCount, this.packs.size());
        this.totalCount++;
        this.rowHashCodes.add(Long.valueOf((r213.id * 13) + 3321));
    L506:
        this.packs.add(r52);
        goto L507
    L483:
        if (r52.expanded == true) goto L489;
        this.totalCount += 24;
        int r129 = 0;
    L485:
        ArrayList<Long> r310 = this.rowHashCodes;
        if (r129 >= 23) goto L488;
        r310.add(Long.valueOf((r52.documents.get(r129).id * 13) + r252));
        r129 = r129 + 1;
        goto L485
    L488:
        r310.add(Long.valueOf(((r213.id * 13) - 5531) + ((r52.documents.size() - 23) * 169)));
        this.positionToExpand.put(this.totalCount - 1, this.packs.size());
        goto L494
    L479:
        r52.free = !r83;
    L474:
        if (r213.channel_emoji_status == true) goto L476;
        r252 = r5;
    L466:
        if (r126.isEmpty() == true) goto L445;
        if (MessageObject.isTextColorEmoji(r126.get(0)) == true) goto L470;
    L453:
        r39 = MediaDataController.getInputStickerSet(r121.set);
        r126 = new ArrayList();
        r83 = true;
        goto L459
    L455:
        if ((r121 instanceof TLRPC.TL_stickerSetFullCovered) == false) goto L458;
        ArrayList<TLRPC.Document> r311 = ((TLRPC.TL_stickerSetFullCovered) r121).documents;
        r83 = MessageObject.isPremiumEmojiPack(r121);
        r126 = r311;
        goto L457
    L458:
        r126 = null;
        r39 = null;
        r83 = false;
        goto L459
    L386:
        if (r117 == 13) goto L427;
        if (r117 == 14) goto L427;
        int r130 = 0;
    L391:
        if (r130 >= r282.size()) goto L427;
        ArrayList<TLRPC.TL_messages_stickerSet> r152 = r282;
        TLRPC.TL_messages_stickerSet r84 = r152.get(r130);
        if (r84 != null) goto L395;
    L396:
        long r164 = r212;
        HashSet<Long> r134 = r272;
    L426:
        r130 = r130 + 1;
        r272 = r134;
        r282 = r152;
        r212 = r164;
        goto L391
    L395:
        if (r84.set == null) goto L396;
        int r914 = this.type;
        if (r914 == 5) goto L402;
        if (r914 == 7) goto L402;
    L404:
        int r915 = this.type;
        if (r915 == 10) goto L409;
        if (r915 == 9) goto L409;
    L411:
        TLRPC.StickerSet r916 = r84.set;
        if (r916.emojis == true) goto L416;
        if (this.showStickers == false) goto L396;
    L416:
        if (this.installedEmojiSets.contains(Long.valueOf(r916.id)) == true) goto L396;
        this.positionToSection.put(this.totalCount, this.packs.size());
        this.sectionToPosition.put(this.packs.size(), this.totalCount);
        this.totalCount++;
        this.rowHashCodes.add(Long.valueOf((r84.set.id * 13) + r212));
        EmojiView.EmojiPack r917 = new EmojiView.EmojiPack();
        r917.installed = true;
        r917.featured = false;
        r917.expanded = true;
        if (this.type != 4) goto L420;
        r917.free = false;
    L421:
        r917.set = r84.set;
        r134 = r272;
        r917.documents = filter(r84.documents, r134);
        r917.index = this.packs.size();
        this.packs.add(r917);
        this.totalCount += r917.documents.size();
        int r85 = 0;
    L423:
        if (r85 >= r917.documents.size()) goto L425;
        this.rowHashCodes.add(Long.valueOf((r917.documents.get(r85).id * 13) + 3212));
        r85 = r85 + 1;
        r212 = r212;
        goto L423
    L425:
        r164 = r212;
        goto L426
    L420:
        r917.free = !MessageObject.isPremiumEmojiPack(r84);
    L409:
        if (r84.set.channel_emoji_status == true) goto L411;
    L402:
        if (MessageObject.isTextColorSet(r84) == true) goto L404;
    L219:
        if (r210.isEmpty() == true) goto L225;
        int r131 = this.totalCount;
        this.totalCount = r131 + 1;
        this.stickersSectionRow = r131;
        this.rowHashCodes.add(8L);
        this.stickersStartRow = this.totalCount;
        this.stickers = r210;
        int r135 = 0;
    L222:
        if (r135 >= r210.size()) goto L224;
        this.rowHashCodes.add(Long.valueOf(r210.get(r135).effectId * 19));
        r135 = r135 + 1;
        goto L222
    L224:
        int r136 = this.totalCount + r210.size();
        this.totalCount = r136;
        this.stickersEndRow = r136;
        goto L225
    L198:
        r36 = r242;
    L216:
        r15 = r6;
    L180:
        this.topReactions.addAll(r1);
    L159:
        if (this.type != 14) goto L157;
        r210 = new ArrayList();
        r242 = true;
        int r918 = 0;
    L162:
        if (r918 >= r1.size()) goto L168;
        if (((ReactionsLayoutInBubble.VisibleReaction) r1.get(r918)).sticker == false) goto L166;
        r210.add((ReactionsLayoutInBubble.VisibleReaction) r1.remove(r918));
        r918 = r918 - 1;
    L166:
        r918 = r918 + 1;
        goto L162
    L226:
        r272 = r13;
        r15 = r6;
        int r214 = this.type;
        if (r214 == 0) goto L265;
        if (r214 == 12) goto L265;
        if (r214 == 9) goto L265;
        if (r214 == 10) goto L265;
        if (r214 != 15) goto L225;
        TLRPC.TL_messages_stickerSet r215 = MediaDataController.getInstance(this.currentAccount).getStickerSet(new TLRPC.TL_inputStickerSetEmojiDefaultStatuses(), true);
        if (r215 != null) goto L240;
        this.defaultSetLoading = true;
        goto L225
    L240:
        if (this.includeEmpty == false) goto L243;
        this.totalCount++;
        this.rowHashCodes.add(2L);
    L243:
        if (r215.documents == null) goto L248;
        int r137 = 0;
    L246:
        if (r137 >= r215.documents.size()) goto L248;
        this.recent.add(new AnimatedEmojiSpan(r215.documents.get(r137), null));
        r137 = r137 + 1;
    L248:
        boolean r138 = this.includeEmpty;
        if (this.recent.size() > (40 - (r138 ? 1 : 0))) goto L251;
    L261:
        int r139 = 0;
    L263:
        if (r139 >= this.recent.size()) goto L225;
        this.rowHashCodes.add(Long.valueOf((this.recent.get(r139).getDocumentId() * 13) + 43223));
        this.totalCount++;
        r139 = r139 + 1;
        goto L263
    L251:
        if (this.recentExpanded == true) goto L261;
        int r216 = 0;
    L253:
        int r53 = 39 - (r138 ? 1 : 0);
        ArrayList<Long> r62 = this.rowHashCodes;
        if (r216 >= r53) goto L256;
        r62.add(Long.valueOf((this.recent.get(r216).getDocumentId() * 13) + 43223));
        this.totalCount++;
        r216 = r216 + 1;
        goto L253
    L256:
        r62.add(Long.valueOf(((((this.recent.size() - 40) + (this.includeEmpty ? 1 : 0)) + 1) * 13) - 5531));
        EmojiPackExpand r140 = this.recentExpandButton;
        if (r140 == null) goto L260;
        r140.textView.setText("+" + (((this.recent.size() - 40) + (this.includeEmpty ? 1 : 0)) + 1));
    L260:
        this.positionToExpand.put(this.totalCount, -1);
        this.totalCount++;
    L265:
        ArrayList<TLRPC.EmojiStatus> r217 = MediaDataController.getInstance(this.currentAccount).getRecentEmojiStatuses();
        MediaDataController r54 = MediaDataController.getInstance(this.currentAccount);
        int r63 = this.type;
        if (r63 == 0) goto L271;
        if (r63 == 12) goto L271;
        TLRPC.InputStickerSet r64 = new TLRPC.TL_inputStickerSetEmojiChannelDefaultStatuses();
    L272:
        TLRPC.TL_messages_stickerSet r55 = r54.getStickerSet(r64, true);
        if (r55 != null) goto L276;
        this.defaultSetLoading = true;
        goto L225
    L276:
        if (this.includeEmpty == false) goto L278;
        this.totalCount++;
        this.rowHashCodes.add(2L);
    L278:
        int r141 = this.type;
        if (r141 == 0) goto L283;
        if (r141 == 12) goto L283;
        ArrayList<TLRPC.EmojiStatus> r142 = MediaDataController.getInstance(this.currentAccount).getDefaultChannelEmojiStatuses();
    L284:
        ArrayList<TLRPC.Document> r65 = r55.documents;
        if (r65 != null) goto L287;
    L295:
        int r56 = this.type;
        if (r56 == 0) goto L300;
        if (r56 == 12) goto L300;
    L299:
        r282 = r15;
    L319:
        if (r142 != null) goto L321;
    L336:
        boolean r143 = this.includeEmpty;
        if (this.recent.size() > (40 - (r143 ? 1 : 0))) goto L339;
    L349:
        int r144 = 0;
    L351:
        if (r144 >= this.recent.size()) goto L353;
        this.rowHashCodes.add(Long.valueOf((this.recent.get(r144).getDocumentId() * 13) + 43223));
        this.totalCount++;
        r144 = r144 + 1;
        goto L351
    L339:
        if (this.recentExpanded == true) goto L349;
        int r218 = 0;
    L341:
        int r57 = 39 - (r143 ? 1 : 0);
        ArrayList<Long> r66 = this.rowHashCodes;
        if (r218 >= r57) goto L344;
        r66.add(Long.valueOf((this.recent.get(r218).getDocumentId() * 13) + 43223));
        this.totalCount++;
        r218 = r218 + 1;
        goto L341
    L344:
        r66.add(Long.valueOf(((((this.recent.size() - 40) + (this.includeEmpty ? 1 : 0)) + 1) * 13) - 5531));
        EmojiPackExpand r145 = this.recentExpandButton;
        if (r145 == null) goto L348;
        r145.textView.setText("+" + (((this.recent.size() - 40) + (this.includeEmpty ? 1 : 0)) + 1));
    L348:
        this.positionToExpand.put(this.totalCount, -1);
        this.totalCount++;
        goto L353
    L321:
        if (r142.isEmpty() == true) goto L336;
        int r219 = r142.size();
        int r58 = 0;
    L323:
        if (r58 >= r219) goto L336;
        TLRPC.EmojiStatus r67 = r142.get(r58);
        r58 = r58 + 1;
        Long r68 = UserObject.getEmojiStatusDocumentId(r67);
        if (r68 == null) goto L323;
        int r86 = 0;
    L328:
        int r1112 = this.recent.size();
        ArrayList<AnimatedEmojiSpan> r1310 = this.recent;
        if (r86 >= r1112) goto L334;
        if (r1310.get(r86).getDocumentId() == r68.longValue()) goto L323;
        r86 = r86 + 1;
        goto L328
    L334:
        r1310.add(new AnimatedEmojiSpan(r68.longValue(), null));
        if ((this.recent.size() + (this.includeEmpty ? 1 : 0)) < 104) goto L323;
    L300:
        if (r217 == null) goto L299;
        if (r217.isEmpty() == true) goto L299;
        int r59 = r217.size();
        int r69 = 0;
    L304:
        if (r69 >= r59) goto L299;
        TLRPC.EmojiStatus r87 = r217.get(r69);
        r69 = r69 + 1;
        Long r88 = UserObject.getEmojiStatusDocumentId(r87);
        if (r88 == null) goto L304;
        int r1113 = 0;
    L309:
        int r1210 = this.recent.size();
        ArrayList<AnimatedEmojiSpan> r1311 = this.recent;
        if (r1113 >= r1210) goto L315;
        if (r1311.get(r1113).getDocumentId() == r88.longValue()) goto L304;
        r1113 = r1113 + 1;
        goto L309
    L315:
        r282 = r15;
        r1311.add(new AnimatedEmojiSpan(r88.longValue(), null));
        if ((this.recent.size() + (this.includeEmpty ? 1 : 0)) >= 104) goto L319;
        r15 = r282;
        goto L304
    L287:
        if (r65.isEmpty() == true) goto L295;
        int r610 = 0;
    L290:
        if (r610 >= Math.min(7, r55.documents.size())) goto L295;
        this.recent.add(new AnimatedEmojiSpan(r55.documents.get(r610), null));
        if ((this.recent.size() + (this.includeEmpty ? 1 : 0)) >= 104) goto L295;
        r610 = r610 + 1;
    L283:
        r142 = MediaDataController.getInstance(this.currentAccount).getDefaultEmojiStatuses();
    L271:
        r64 = new TLRPC.TL_inputStickerSetEmojiDefaultStatuses();
    L146:
        TLRPC.TL_emojiList r220 = MediaDataController.getInstance(this.currentAccount).restrictedStatusEmojis;
        if (r220 == null) goto L149;
        r13 = new HashSet();
        r13.addAll(r220.document_id);
        goto L151
    L123:
        if (this.badgePicker == true) goto L143;
        int r221 = this.type;
        if (r221 == 13) goto L143;
        if (r221 == 2) goto L143;
        if (r221 == 11) goto L143;
        if (r221 == 3) goto L143;
        if (r221 == 6) goto L143;
        if (r221 == 8) goto L143;
        if (r221 == 4) goto L143;
        if (r221 == 5) goto L143;
        if (r221 == 7) goto L143;
        if (r221 == 15) goto L143;
        int r222 = this.totalCount;
        this.totalCount = r222 + 1;
        this.longtapHintRow = r222;
        this.rowHashCodes.add(6L);
        goto L143
    L69:
        if (this.includeEmpty == false) goto L71;
        this.totalCount++;
        this.rowHashCodes.add(2L);
    L71:
        List<TLRPC.TL_availableReaction> r223 = MediaDataController.getInstance(this.currentAccount).getEnabledReactionsList();
        int r312 = 0;
    L73:
        if (r312 >= r223.size()) goto L75;
        this.recentStickers.add(r223.get(r312).activate_animation);
        r312 = r312 + 1;
        goto L73
    L75:
        int r224 = 0;
    L77:
        if (r224 >= this.recentStickers.size()) goto L121;
        this.rowHashCodes.add(Long.valueOf((this.recentStickers.get(r224).id * 13) + 62425));
        this.totalCount++;
        r224 = r224 + 1;
        goto L77
    L43:
        if (this.showStickers == false) goto L49;
        this.recentStickers.addAll(MediaDataController.getInstance(this.currentAccount).getRecentStickersNoCopy(0));
        int r1211 = 0;
    L46:
        if (r1211 >= this.recentStickers.size()) goto L48;
        this.rowHashCodes.add(Long.valueOf((this.recentStickers.get(r1211).id * 13) + 62425));
        this.totalCount++;
        r1211 = r1211 + 1;
    L48:
        r23 = r2;
        goto L121
    L49:
        boolean r510 = this.forUser;
        int r1114 = this.currentAccount;
        if (r510 == false) goto L52;
        TLRPC.TL_emojiList r511 = MediaDataController.getInstance(r1114).profileAvatarConstructorDefault;
    L53:
        if (r511 == null) goto L48;
        ArrayList<Long> r1115 = r511.document_id;
        if (r1115 == null) goto L48;
        if (r1115.isEmpty() == true) goto L48;
        EmojiView.EmojiPack r1116 = new EmojiView.EmojiPack();
        r1116.installed = true;
        r1116.featured = false;
        r1116.expanded = true;
        r1116.free = true;
        r1116.set = new TLRPC.TL_stickerSet();
        r1116.thumbDocumentId = r511.document_id.get(0);
        r1116.index = this.packs.size();
        this.packs.add(r1116);
        int r1117 = 0;
    L60:
        if (r1117 >= r511.document_id.size()) goto L62;
        this.recent.add(new AnimatedEmojiSpan(r511.document_id.get(r1117).longValue(), null));
        r1117 = r1117 + 1;
        r2 = r2;
        goto L60
    L62:
        r23 = r2;
        int r225 = 0;
    L64:
        if (r225 >= this.recent.size()) goto L121;
        this.rowHashCodes.add(Long.valueOf((this.recent.get(r225).getDocumentId() * 13) + 43223));
        this.totalCount++;
        r225 = r225 + 1;
        goto L64
    L52:
        r511 = MediaDataController.getInstance(r1114).groupAvatarConstructorDefault;
    L39:
        r23 = r2;
        if (this.includeEmpty == false) goto L106;
        this.totalCount++;
        this.rowHashCodes.add(2L);
    L106:
        TLRPC.TL_emojiList r226 = MediaDataController.getInstance(this.currentAccount).replyIconsDefault;
        if (r226 == null) goto L121;
        ArrayList<Long> r313 = r226.document_id;
        if (r313 == null) goto L121;
        if (r313.isEmpty() == true) goto L121;
        int r314 = 0;
    L114:
        if (r314 >= r226.document_id.size()) goto L116;
        this.recent.add(new AnimatedEmojiSpan(r226.document_id.get(r314).longValue(), null));
        r314 = r314 + 1;
        goto L114
    L116:
        int r227 = 0;
    L118:
        if (r227 >= this.recent.size()) goto L121;
        this.rowHashCodes.add(Long.valueOf((this.recent.get(r227).getDocumentId() * 13) + 43223));
        this.totalCount++;
        r227 = r227 + 1;
    L30:
        int r1212 = this.type;
        if (r1212 == 5) goto L35;
        if (r1212 == 7) goto L35;
        if (r1212 == 8) goto L35;
        int r1213 = this.totalCount;
        this.totalCount = r1213 + 1;
        this.searchRow = r1213;
        this.rowHashCodes.add(9L);
        goto L36
    L21:
        if (this.type == 4) goto L24;
        r7 = null;
    L14:
        if (this.showStickers == false) goto L16;
        int r72 = 0;
    L17:
        this.frozenEmojiPacks = new ArrayList(r4.getStickerSets(r72));
        goto L18
    L16:
        r72 = 5;
        goto L17
    L5:
        r2 = r33;
        goto L6
    }

    private void updateRowsDelayed() {
        AndroidUtilities.cancelRunOnUIThread(this.updateRowsDelayed);
        AndroidUtilities.runOnUIThread(this.updateRowsDelayed);
    }

    private void updateSearchBox() {
        SearchBox r0 = this.searchBox;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.searched == false) goto L10;
        r0.clearAnimation();
        this.searchBox.setVisibility(0);
        this.searchBox.animate().translationY(0.0f).start();
        return;
    L10:
        if (this.emojiGridView.getChildCount() <= 0) goto L19;
        View r02 = this.emojiGridView.getChildAt(0);
        if (this.emojiGridView.getChildAdapterPosition(r02) == this.searchRow) goto L14;
    L17:
        this.searchBox.setTranslationY(-AndroidUtilities.dp(52.0f));
        return;
    L14:
        if ("searchbox".equals(r02.getTag()) == false) goto L17;
        this.searchBox.setVisibility(0);
        this.searchBox.setTranslationY(r02.getY());
        return;
    L19:
        this.searchBox.setTranslationY(-AndroidUtilities.dp(52.0f));
    }

    public static void updateSearchEmptyViewImage(int r13, BackupImageView r14) {
        if (r14 == null) goto L88;
        ArrayList r1 = new ArrayList(MediaDataController.getInstance(r13).getFeaturedEmojiSets());
        Collections.shuffle(r1);
        int r0 = (int) Math.round(Math.random() * 10.0d);
        TLRPC.Document r5 = null;
        int r4 = 0;
    L6:
        if (r4 >= r1.size()) goto L27;
        if ((r1.get(r4) instanceof TLRPC.TL_stickerSetFullCovered) == true) goto L10;
    L23:
        if (r5 == null) goto L26;
        if (r0 <= 0) goto L27;
    L26:
        r4 = r4 + 1;
        goto L6
    L10:
        if (((TLRPC.TL_stickerSetFullCovered) r1.get(r4)).documents == null) goto L23;
        ArrayList r6 = new ArrayList(((TLRPC.TL_stickerSetFullCovered) r1.get(r4)).documents);
        Collections.shuffle(r6);
        int r7 = 0;
    L13:
        if (r7 >= r6.size()) goto L23;
        TLRPC.Document r8 = (TLRPC.Document) r6.get(r7);
        if (r8 == null) goto L22;
        if (emptyViewEmojis.contains(MessageObject.findAnimatedEmojiEmoticon(r8, null)) == false) goto L22;
        int r52 = r0 - 1;
        if (r0 <= 0) goto L20;
        r0 = r52;
        r5 = r8;
        goto L22
    L20:
        r0 = r52;
        r5 = r8;
    L22:
        r7 = r7 + 1;
    L27:
        if (r5 == null) goto L31;
        if (r0 > 0) goto L31;
    L30:
        TLRPC.Document r12 = r5;
        if (r12 == null) goto L87;
        SvgHelper.SvgDrawable r11 = DocumentObject.getSvgThumb(r12.thumbs, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
        TLRPC.PhotoSize r132 = FileLoader.getClosestPhotoSizeWithSize(r12.thumbs, 90);
        if ("video/webm".equals(r12.mime_type) == false) goto L62;
        ImageLocation r02 = ImageLocation.getForDocument(r12);
        if (r11 == null) goto L60;
        r11.overrideWidthAndHeight(512, 512);
    L60:
        String r15 = "36_36_g";
    L61:
        ImageLocation r72 = r02;
        String r82 = r15;
        r14.setLayerNum(7);
        r14.setRoundRadius(AndroidUtilities.dp(4.0f));
        r14.setImage(r72, r82, ImageLocation.getForDocument(r132, r12), "36_36", r11, r12);
        return;
    L62:
        if (r11 != null) goto L64;
    L66:
        r02 = ImageLocation.getForDocument(r12);
        r15 = "36_36";
        goto L61
    L64:
        if (MessageObject.isAnimatedStickerDocument(r12, false) == false) goto L66;
        r11.overrideWidthAndHeight(512, 512);
        goto L66
    L87:
        return;
    L31:
        ArrayList r16 = new ArrayList(MediaDataController.getInstance(r13).getStickerSets(5));
        Collections.shuffle(r16);
        int r133 = 0;
    L33:
        if (r133 >= r16.size()) goto L30;
        if (r16.get(r133) != null) goto L37;
    L50:
        if (r5 == null) goto L53;
        if (r0 <= 0) goto L30;
    L53:
        r133 = r133 + 1;
        goto L33
    L37:
        if (((TLRPC.TL_messages_stickerSet) r16.get(r133)).documents == null) goto L50;
        ArrayList r42 = new ArrayList(((TLRPC.TL_messages_stickerSet) r16.get(r133)).documents);
        Collections.shuffle(r42);
        int r62 = 0;
    L40:
        if (r62 >= r42.size()) goto L50;
        TLRPC.Document r73 = (TLRPC.Document) r42.get(r62);
        if (r73 == null) goto L49;
        if (emptyViewEmojis.contains(MessageObject.findAnimatedEmojiEmoticon(r73, null)) == false) goto L49;
        int r53 = r0 - 1;
        if (r0 <= 0) goto L47;
        r0 = r53;
        r5 = r73;
        goto L49
    L47:
        r0 = r53;
        r5 = r73;
    L49:
        r62 = r62 + 1;
        goto L40
    }

    private void updateShow(float r17) {
        int r2 = -1;
        if (this.bubble1View == null) goto L10;
        float r1 = CubicBezierInterpolator.EASE_OUT.getInterpolation(MathUtils.clamp((((r17 * 800.0f) - 0.0f) / 120.0f) / 1.0f, 0.0f, 1.0f));
        this.bubble1View.setAlpha(r1);
        this.bubble1View.setScaleX(r1);
        View r8 = this.bubble1View;
        if (isBottom() == false) goto L7;
        int r9 = -1;
    L8:
        r8.setScaleY(r1 * r9);
        goto L10
    L7:
        r9 = 1;
    L10:
        if (this.bubble2View == null) goto L16;
        float r12 = MathUtils.clamp((((r17 * 800.0f) - 30.0f) / 120.0f) / 1.0f, 0.0f, 1.0f);
        this.bubble2View.setAlpha(r12);
        this.bubble2View.setScaleX(r12);
        View r92 = this.bubble2View;
        if (isBottom() == true) goto L15;
        r2 = 1;
    L15:
        r92.setScaleY(r12 * r2);
    L16:
        float r4 = 800.0f * r17;
        float r13 = r4 - 40.0f;
        float r5 = MathUtils.clamp(r13 / 700.0f, 0.0f, 1.0f);
        float r22 = MathUtils.clamp((r4 - 80.0f) / 700.0f, 0.0f, 1.0f);
        float r14 = MathUtils.clamp(r13 / 750.0f, 0.0f, 1.0f);
        float r3 = MathUtils.clamp((r4 - 30.0f) / 120.0f, 0.0f, 1.0f);
        CubicBezierInterpolator r42 = CubicBezierInterpolator.EASE_OUT_QUINT;
        float r52 = r42.getInterpolation(r5);
        float r23 = r42.getInterpolation(r22);
        this.backgroundView.setAlpha(r3);
        this.searchBox.setAlpha(r3);
        int r43 = 0;
        int r82 = 0;
    L18:
        if (r82 >= this.emojiTabs.contentView.getChildCount()) goto L21;
        this.emojiTabs.contentView.getChildAt(r82).setAlpha(r3);
        r82 = r82 + 1;
        goto L18
    L21:
        if (this.scrimDrawable == null) goto L23;
        invalidate();
    L23:
        float r11 = 1.0f - r3;
        this.contentView.setTranslationY(AndroidUtilities.dp(-5.0f) * r11);
        View r83 = this.bubble2View;
        if (r83 == null) goto L26;
        r83.setTranslationY(AndroidUtilities.dp(-5.0f) * r11);
    L26:
        this.scaleX = (r52 * 0.85f) + 0.15f;
        this.scaleY = (r23 * 0.925f) + 0.075f;
        this.contentView.invalidateOutline();
        View r24 = this.bubble2View;
        if (r24 == null) goto L29;
        r24.setAlpha(r3);
    L29:
        this.emojiTabsShadow.setAlpha(r3);
        this.emojiTabsShadow.setScaleX(Math.min(this.scaleX, 1.0f));
        float r25 = this.emojiTabsShadow.getPivotX();
        float r32 = (float) Math.sqrt(Math.max((r25 * r25) + Math.pow(this.contentView.getHeight(), 2.0d), Math.pow(this.contentView.getWidth() - r25, 2.0d) + Math.pow(this.contentView.getHeight(), 2.0d)));
        int r53 = 0;
    L31:
        if (r53 >= this.emojiTabs.contentView.getChildCount()) goto L46;
        View r84 = this.emojiTabs.contentView.getChildAt(r53);
        if (r17 != 0.0f) goto L36;
        r84.setLayerType(2, null);
    L38:
        float r112 = (r84.getLeft() + (r84.getWidth() / 2.0f)) - r25;
        float r122 = r84.getTop() + (r84.getHeight() / 2.0f);
        if (isBottom() == false) goto L41;
        r122 = getMeasuredHeight() - r122;
    L41:
        float r93 = AndroidUtilities.cascade(r14, (float) Math.sqrt((r112 * r112) + ((r122 * r122) * 0.4f)), r32, r84.getHeight() * 1.75f);
        if (Float.isNaN(r93) == false) goto L44;
        r93 = 0.0f;
    L44:
        r84.setScaleX(r93);
        r84.setScaleY(r93);
        r53 = r53 + 1;
        goto L31
    L36:
        if (r17 != 1.0f) goto L38;
        r84.setLayerType(0, null);
    L46:
        if (r43 >= this.emojiGridView.getChildCount()) goto L57;
        View r54 = this.emojiGridView.getChildAt(r43);
        if ((r54 instanceof ImageViewEmoji) == false) goto L56;
        ImageViewEmoji r7 = (ImageViewEmoji) r54;
        float r85 = (r54.getLeft() + (r54.getWidth() / 2.0f)) - r25;
        float r113 = r54.getTop() + (r54.getHeight() / 2.0f);
        if (isBottom() == false) goto L52;
        r113 = getMeasuredHeight() - r113;
    L52:
        float r55 = AndroidUtilities.cascade(r14, (float) Math.sqrt((r85 * r85) + ((r113 * r113) * 0.2f)), r32, r54.getHeight() * 1.75f);
        if (Float.isNaN(r55) == false) goto L55;
        r55 = 0.0f;
    L55:
        r7.setAnimatedScale(r55);
    L56:
        r43 = r43 + 1;
        goto L46
    L57:
        this.emojiGridViewContainer.invalidate();
        this.emojiGridView.invalidate();
    }

    private void updateTabsPosition(int r7) {
        if (r7 == (-1)) goto L44;
        int r1 = 40;
        if (this.recent.size() > 40) goto L7;
    L9:
        r1 = (this.includeEmpty ? 1 : 0) + this.recent.size();
    L10:
        int r0 = 0;
        if (r7 > r1) goto L13;
    L37:
        this.emojiTabs.select(0);
        return;
    L13:
        if (r7 <= this.recentReactions.size()) goto L37;
        int r12 = 0;
    L17:
        if (r12 >= this.positionToSection.size()) goto L45;
        int r2 = this.positionToSection.keyAt(r12);
        int r3 = this.positionToSection.valueAt(r12);
        if (r3 < 0) goto L21;
        EmojiView.EmojiPack r4 = this.packs.get(r3);
    L22:
        if (r4 == null) goto L36;
        boolean r5 = r4.expanded;
        ArrayList<TLRPC.Document> r42 = r4.documents;
        if (r5 == false) goto L27;
        int r43 = r42.size();
    L28:
        if (r7 <= r2) goto L36;
        if (r7 > ((r2 + 1) + r43)) goto L36;
        EmojiTabsStrip r6 = this.emojiTabs;
        if (r6.recentTab == null) goto L34;
        r0 = 1;
    L34:
        r6.select((r0 + (r6.isGiftsVisible() ? 1 : 0)) + r3);
        return;
    L27:
        r43 = Math.min(24, r42.size());
    L36:
        r12 = r12 + 1;
        goto L17
    L21:
        r4 = null;
        goto L22
    L45:
        return;
    L7:
        if (this.recentExpanded == true) goto L9;
    }

    public void animateEmojiSelect(ImageViewEmoji r9, Runnable r10) {
        if (this.emojiSelectAnimator != null) goto L14;
        if (this.scrimDrawable == null) goto L14;
        r9.notDraw = true;
        Rect r3 = new Rect();
        r3.set((this.contentView.getLeft() + this.emojiGridView.getLeft()) + r9.getLeft(), (this.contentView.getTop() + this.emojiGridView.getTop()) + r9.getTop(), (this.contentView.getLeft() + this.emojiGridView.getLeft()) + r9.getRight(), (this.contentView.getTop() + this.emojiGridView.getTop()) + r9.getBottom());
        Drawable r1 = r9.drawable;
        if ((r1 instanceof AnimatedEmojiDrawable) == false) goto L11;
        AnimatedEmojiDrawable r12 = AnimatedEmojiDrawable.make(this.currentAccount, 7, ((AnimatedEmojiDrawable) r1).getDocumentId());
    L10:
        AnimatedEmojiDrawable r7 = r12;
        this.emojiSelectView = r9;
        Rect r13 = new Rect();
        this.emojiSelectRect = r13;
        r13.set(r3);
        boolean[] r5 = new boolean[1];
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.emojiSelectAnimator = r0;
        r0.addUpdateListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda15(this, r3, r9, r5, r10, r7));
        this.emojiSelectAnimator.addListener(new AnonymousClass20(this, r5, r10));
        this.emojiSelectAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.emojiSelectAnimator.setDuration(260);
        this.emojiSelectAnimator.start();
        return;
    L11:
        r12 = null;
    L14:
        r10.run();
    }

    public long animateExpandAppearDuration() {
        return Math.max(450, Math.min(55, this.animateExpandToPosition - this.animateExpandFromPosition) * 30);
    }

    public long animateExpandCrossfadeDuration() {
        return Math.max(300, Math.min(45, this.animateExpandToPosition - this.animateExpandFromPosition) * 25);
    }

    public long animateExpandDuration() {
        return (animateExpandAppearDuration() + animateExpandCrossfadeDuration()) + 16;
    }

    public void clearSelectedDocuments() {
        this.selectedDocumentIds.clear();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.stickersDidLoad) goto L13;
        if (((Integer) r5[0]).intValue() != 5) goto L7;
    L10:
        updateRowsDelayed();
        return;
    L7:
        if (((Integer) r5[0]).intValue() == 0) goto L9;
        return;
    L9:
        if (this.showStickers == true) goto L10;
        return;
    L13:
        if (r3 != NotificationCenter.featuredEmojiDidLoad) goto L17;
        updateRowsDelayed();
        return;
    L17:
        if (r3 != NotificationCenter.recentEmojiStatusesUpdate) goto L21;
        updateRowsDelayed();
        return;
    L21:
        if (r3 != NotificationCenter.groupStickersDidLoad) goto L25;
        updateRowsDelayed();
        return;
    L25:
        if (r3 != NotificationCenter.emojiLoaded) goto L31;
        AndroidUtilities.forEachViews(this.emojiGridView, new FloatingDebugView$$ExternalSyntheticLambda10());
        EmojiListView r2 = this.emojiGridView;
        if (r2 == null) goto L38;
        r2.invalidate();
        return;
    L38:
        return;
    L31:
        if (r3 == NotificationCenter.starUserGiftsLoaded) goto L33;
        return;
    L33:
        if (((Long) r5[0]).longValue() != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L40;
        updateRowsDelayed();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r13) {
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.scrimDrawable;
        if (r0 != null) goto L5;
    L32:
        super.dispatchDraw(r13);
        ImageViewEmoji r02 = this.emojiSelectView;
        if (r02 != null) goto L35;
        return;
    L35:
        if (this.emojiSelectRect != null) goto L37;
        return;
    L37:
        if (r02.drawable == null) goto L42;
        r13.save();
        r13.translate(0.0f, -getTranslationY());
        this.emojiSelectView.drawable.setAlpha((int) (this.emojiSelectAlpha * 255.0f));
        this.emojiSelectView.drawable.setBounds(this.emojiSelectRect);
        this.emojiSelectView.drawable.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(this.accentColor, this.scrimColor, 1.0f - this.scrimAlpha), PorterDuff.Mode.SRC_IN));
        this.emojiSelectView.drawable.draw(r13);
        r13.restore();
        return;
    L42:
        return;
    L5:
        if (this.emojiX == null) goto L32;
        Rect r03 = r0.getBounds();
        View r3 = this.scrimDrawableParent;
        if (r3 != null) goto L9;
        float r32 = 1.0f;
    L10:
        int r4 = this.scrimDrawable.getAlpha();
        View r5 = this.scrimDrawableParent;
        if (r5 != null) goto L13;
        r03.height();
    L14:
        r13.save();
        r13.translate(0.0f, -getTranslationY());
        this.scrimDrawable.setAlpha((int) ((r4 * Math.pow(this.contentView.getAlpha(), 0.25d)) * this.scrimAlpha));
        if (this.drawableToBounds != null) goto L18;
        this.drawableToBounds = new Rect();
    L18:
        if (r32 > 1.0f) goto L20;
    L22:
        int r52 = 0;
    L23:
        float r53 = r52;
        if (r32 <= 1.5f) goto L26;
        float r6 = (r03.height() * 0.81f) + 1.0f;
    L27:
        float r7 = this.emojiX.intValue() + r53;
        float r54 = (r03.centerY() * (r32 - 1.0f)) + (-r6);
        if (isBottom() == true) goto L30;
        float r62 = AndroidUtilities.dp(this.topMarginDp);
    L31:
        float r55 = (r54 + r62) + getScrimDrawableTranslationY();
        float r63 = (r03.width() * r32) / 2.0f;
        float r9 = (r03.height() * r32) / 2.0f;
        this.drawableToBounds.set((int) (r7 - r63), (int) (r55 - r9), (int) (r7 + r63), (int) (r55 + r9));
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r56 = this.scrimDrawable;
        Rect r64 = this.drawableToBounds;
        int r72 = r64.left;
        Rect r92 = this.drawableToBounds;
        r56.setBounds(r72, r64.top, (int) (r72 + (r64.width() / r32)), (int) (r92.top + (r92.height() / r32)));
        Rect r57 = this.drawableToBounds;
        r13.scale(r32, r32, r57.left, r57.top);
        this.scrimDrawable.draw(r13);
        this.scrimDrawable.setAlpha(r4);
        this.scrimDrawable.setBounds(r03);
        r13.restore();
        goto L32
    L30:
        r62 = getMeasuredHeight() - (AndroidUtilities.dp(this.topMarginDp) / 2.0f);
        goto L31
    L26:
        r6 = 0.0f;
        goto L27
    L20:
        if (r32 >= 1.5f) goto L22;
        r52 = 2;
        goto L23
    L13:
        r5.getHeight();
        goto L14
    L9:
        r32 = r3.getScaleY();
        goto L10
    }

    public void drawBigReaction(Canvas r10, View r11) {
        if (this.selectedReactionView == null) goto L35;
        this.bigReactionImageReceiver.setParentView(r11);
        ImageViewEmoji r0 = this.selectedReactionView;
        if (r0 == null) goto L34;
        float r1 = this.pressedProgress;
        if (r1 != 1.0f) goto L10;
    L19:
        float r02 = (this.pressedProgress * 2.0f) + 1.0f;
        r10.save();
        r10.translate(this.emojiGridView.getX() + this.selectedReactionView.getX(), (this.gridViewContainer.getY() + this.emojiGridView.getY()) + this.selectedReactionView.getY());
        this.paint.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.resourcesProvider));
        r10.drawRect(0.0f, 0.0f, this.selectedReactionView.getMeasuredWidth(), this.selectedReactionView.getMeasuredHeight(), this.paint);
        r10.scale(r02, r02, this.selectedReactionView.getMeasuredWidth() / 2.0f, this.selectedReactionView.getMeasuredHeight());
        ImageViewEmoji r102 = this.selectedReactionView;
        if (r102.isDefaultReaction == false) goto L22;
        ImageReceiver r103 = this.bigReactionImageReceiver;
    L23:
        AnimatedEmojiDrawable r03 = this.bigReactionAnimatedEmoji;
        if (r03 != null) goto L26;
    L30:
        if (r103 == null) goto L32;
        r103.setImageCoords(0.0f, 0.0f, this.selectedReactionView.getMeasuredWidth(), this.selectedReactionView.getMeasuredHeight());
        r103.draw(r10);
    L32:
        r10.restore();
        r11.invalidate();
        return;
    L26:
        if (r03.getImageReceiver() == null) goto L30;
        if (this.bigReactionAnimatedEmoji.getImageReceiver().hasBitmapImage() == false) goto L30;
        r103 = this.bigReactionAnimatedEmoji.getImageReceiver();
        goto L30
    L22:
        r103 = r102.imageReceiverToDraw;
        goto L23
    L10:
        if (this.cancelPressed == true) goto L19;
        if (this.isLongPressEnabled == false) goto L19;
        float r12 = r1 + 0.010666667f;
        this.pressedProgress = r12;
        if (r12 < 1.0f) goto L18;
        this.pressedProgress = 1.0f;
        onLongPressedListener r13 = this.bigReactionListener;
        if (r13 == null) goto L18;
        r13.onLongPressed(r0);
    L18:
        this.selectedReactionView.bigReactionSelectedProgress = this.pressedProgress;
        goto L19
    L34:
        return;
    }

    public void expand(int r9, View r10) {
        int r92 = this.positionToExpand.get(r9);
        this.animateExpandFromButtonTranslate = 0.0f;
        Integer r2 = null;
        if (r92 < 0) goto L23;
        if (r92 >= this.packs.size()) goto L23;
        EmojiView.EmojiPack r3 = this.packs.get(r92);
        if (r3.expanded == false) goto L10;
        return;
    L10:
        if ((r92 + 1) != this.packs.size()) goto L12;
        boolean r4 = true;
    L13:
        int r93 = this.sectionToPosition.get(r92);
        this.expandedEmojiSets.add(Long.valueOf(r3.set.id));
        boolean r5 = r3.expanded;
        ArrayList<TLRPC.Document> r6 = r3.documents;
        int r7 = 24;
        if (r5 == false) goto L16;
        int r52 = r6.size();
    L18:
        if (r3.documents.size() <= 24) goto L20;
        Integer r62 = Integer.valueOf((r93 + 1) + r52);
    L21:
        r3.expanded = true;
        int r32 = r3.documents.size();
        boolean r42 = r4;
    L41:
        if (r32 <= r52) goto L43;
        r62 = Integer.valueOf((r93 + 1) + r52);
        r2 = Integer.valueOf(r32 - r52);
    L43:
        updateRows(false, true);
        if (r62 == null) goto L55;
        if (r2 == null) goto L56;
        this.animateExpandFromButton = r10;
        this.animateExpandFromPosition = r62.intValue();
        this.animateExpandToPosition = r62.intValue() + r2.intValue();
        this.animateExpandStartTime = SystemClock.elapsedRealtime();
        if (r42 == false) goto L57;
        int r94 = r62.intValue();
        if (r2.intValue() <= (r7 / 2)) goto L51;
        float r102 = 1.5f;
    L52:
        post(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda9(this, r102, r94));
        return;
    L51:
        r102 = 3.5f;
        goto L52
    L57:
        return;
    L56:
        return;
    L55:
        return;
    L20:
        r62 = null;
        goto L21
    L16:
        r52 = Math.min(24, r6.size());
        goto L18
    L12:
        r4 = false;
    L23:
        if (r92 != (-1)) goto L54;
        boolean r95 = this.recentExpanded;
        if (r95 == false) goto L28;
        return;
    L28:
        if (this.searchRow == (-1)) goto L30;
        int r43 = 1;
    L32:
        if (this.longtapHintRow == (-1)) goto L34;
        int r33 = 1;
    L35:
        int r44 = r43 + r33;
        boolean r34 = this.includeEmpty;
        int r45 = r44 + (r34 ? 1 : 0);
        ArrayList<AnimatedEmojiSpan> r53 = this.recent;
        if (r95 == false) goto L39;
        int r96 = r53.size();
    L38:
        r52 = r96;
        r32 = this.recent.size();
        this.recentExpanded = true;
        r7 = 40;
        r62 = null;
        r93 = r45;
        r42 = false;
        goto L41
    L39:
        r96 = Math.min(38 - (r34 ? 1 : 0), r53.size());
        goto L38
    L34:
        r33 = 0;
        goto L35
    L30:
        r43 = 0;
        goto L32
    }

    public StarsReactionsSheet.Particles getCollectionParticles() {
        if (this.collectionParticles != null) goto L6;
        this.collectionParticles = new StarsReactionsSheet.Particles(1, 8);
    L6:
        return this.collectionParticles;
    }

    public long getDialogId() {
        return UserConfig.getInstance(this.currentAccount).getClientUserId();
    }

    public float getScrimDrawableTranslationY() {
        return 0.0f;
    }

    public void invalidateParent() {
    }

    public void invalidateSearchBox() {
        this.searchBox.invalidate();
    }

    public boolean isBottom() {
        int r1 = this.type;
        if (r1 != 5) goto L5;
        return true;
    L5:
        if (r1 != 10) goto L7;
        return true;
    L7:
        if (r1 != 12) goto L9;
        return true;
    L9:
        if (r1 == 15) goto L17;
        return false;
    L17:
        return true;
    }

    public void notifyDataSetChanged() {
        Adapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttached = true;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.featuredEmojiDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.recentEmojiStatusesUpdate);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupStickersDidLoad);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        int r0 = this.type;
        if (r0 != 0) goto L5;
    L6:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.starUserGiftsLoaded);
    L7:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r02 = this.scrimDrawable;
        if (r02 == null) goto L11;
        r02.setSecondParent(this);
        return;
    L11:
        return;
    L5:
        if (r0 != 12) goto L7;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setBigReactionAnimatedEmoji(null);
        this.isAttached = false;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.featuredEmojiDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.stickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.recentEmojiStatusesUpdate);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupStickersDidLoad);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        int r1 = this.type;
        if (r1 != 0) goto L5;
    L6:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
    L7:
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r3 = this.scrimDrawable;
        if (r3 == null) goto L11;
        r3.setSecondParent(null);
        return;
    L11:
        return;
    L5:
        if (r1 != 12) goto L7;
        goto L6
    }

    public void onDismiss(Runnable r4) {
        Integer r0 = this.listStateId;
        if (r0 == null) goto L5;
        listStates.put(r0, this.layoutManager.onSaveInstanceState());
    L5:
        ValueAnimator r02 = this.hideAnimator;
        if (r02 == null) goto L8;
        r02.cancel();
        this.hideAnimator = null;
    L8:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.hideAnimator = r03;
        r03.addUpdateListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda16(this));
        this.hideAnimator.addListener(new AnonymousClass26(this, r4));
        this.hideAnimator.setDuration(200);
        this.hideAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.hideAnimator.start();
        SearchBox r3 = this.searchBox;
        if (r3 == null) goto L12;
        AndroidUtilities.hideKeyboard(SearchBox.m18733$$Nest$fgetinput(r3));
        return;
    }

    public void onEmojiClick(View r14, AnimatedEmojiSpan r15) {
        incrementHintUse();
        String r0 = null;
        if (r15 == null) goto L54;
        if (this.badgePicker == true) goto L14;
        int r1 = this.type;
        if (r1 == 0) goto L12;
        if (r1 == 12) goto L12;
        if (r1 == 9) goto L12;
        if (r1 != 10) goto L14;
    L12:
        if (this.selectedDocumentIds.contains(Long.valueOf(r15.documentId)) == true) goto L54;
    L14:
        TLRPC.TL_emojiStatus r12 = new TLRPC.TL_emojiStatus();
        r12.document_id = r15.getDocumentId();
        TLRPC.Document r5 = r15.document;
        if (r5 != null) goto L17;
        r5 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r15.documentId);
    L17:
        TLRPC.Document r9 = r5;
        if ((r14 instanceof ImageViewEmoji) == false) goto L49;
        ImageViewEmoji r52 = (ImageViewEmoji) r14;
        if (r52.starGift != null) goto L27;
        int r6 = this.type;
        if (r6 == 0) goto L26;
        if (r6 == 12) goto L26;
        if (r6 == 9) goto L26;
        if (r6 != 10) goto L27;
    L26:
        MediaDataController.getInstance(this.currentAccount).pushRecentEmojiStatus(r12);
    L27:
        int r13 = this.type;
        if (r13 == 0) goto L41;
        if (r13 == 12) goto L41;
        if (r13 == 9) goto L41;
        if (r13 == 10) goto L41;
        if (r13 == 2) goto L41;
        Long r8 = Long.valueOf(r15.documentId);
        TL_stars.TL_starGiftUnique r10 = r52.starGift;
        EditTextCaption r152 = this.commentView;
        if (r152 == null) goto L38;
        r0 = r152.getText().toString();
    L38:
        onEmojiSelected(r14, r8, r9, r10, null, r0);
        return;
    L41:
        if (willApplyEmoji(r14, Long.valueOf(r15.documentId), r9, r52.starGift, null) == true) goto L47;
        Long r82 = Long.valueOf(r15.documentId);
        TL_stars.TL_starGiftUnique r102 = r52.starGift;
        EditTextCaption r132 = this.commentView;
        if (r132 == null) goto L45;
        r0 = r132.getText().toString();
    L45:
        onEmojiSelected(r14, r82, r9, r102, null, r0);
        return;
    L47:
        animateEmojiSelect(r52, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda8(this, r14, r15, r9, r52));
        return;
    L49:
        Long r83 = Long.valueOf(r15.documentId);
        EditTextCaption r133 = this.commentView;
        if (r133 == null) goto L52;
        r0 = r133.getText().toString();
    L52:
        onEmojiSelected(r14, r83, r9, null, null, r0);
        return;
    L54:
        EditTextCaption r134 = this.commentView;
        if (r134 == null) goto L57;
        r0 = r134.getText().toString();
    L57:
        onEmojiSelected(r14, null, null, null, null, r0);
    }

    public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
    }

    public void onEmojiSelected(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5, String r6) {
        onEmojiSelected(r1, r2, r3, r4, r5);
    }

    public void onInputFocus() {
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (r1 == true) goto L5;
        return;
    L5:
        if (this.type != 6) goto L9;
        int r12 = (getMeasuredWidth() / AndroidUtilities.dp(42.0f)) * 5;
        this.layoutManager.setSpanCount(r12);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.drawBackground == false) goto L11;
        int r0 = this.type;
        if (r0 == 3) goto L11;
        if (r0 == 4) goto L11;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) Math.min(AndroidUtilities.dp(324.0f), AndroidUtilities.displaySize.x * 0.95f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) Math.min(AndroidUtilities.dp(330.0f), AndroidUtilities.displaySize.y * 0.75f), Integer.MIN_VALUE));
        return;
    L11:
        if (this.type != 6) goto L14;
        super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec((int) (AndroidUtilities.displaySize.y * 0.35f), Integer.MIN_VALUE));
        return;
    L14:
        super.onMeasure(r4, r5);
    }

    public void onReactionClick(ImageViewEmoji r1, ReactionsLayoutInBubble.VisibleReaction r2) {
    }

    public void onSettings() {
    }

    public void onShow(Runnable r6) {
        Integer r0 = this.listStateId;
        if (r0 == null) goto L5;
        Parcelable r02 = listStates.get(r0);
    L5:
        this.dismiss = r6;
        if (this.drawBackground == true) goto L12;
        checkScroll();
        int r62 = 0;
    L9:
        if (r62 >= this.emojiGridView.getChildCount()) goto L11;
        View r1 = this.emojiGridView.getChildAt(r62);
        r1.setScaleX(1.0f);
        r1.setScaleY(1.0f);
        r62 = r62 + 1;
        goto L9
    L11:
        return;
    L12:
        ValueAnimator r63 = this.showAnimator;
        if (r63 == null) goto L15;
        r63.cancel();
        this.showAnimator = null;
    L15:
        ValueAnimator r64 = this.hideAnimator;
        if (r64 == null) goto L19;
        r64.cancel();
        this.hideAnimator = null;
    L19:
        if (isAnimatedShow() == false) goto L28;
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.showAnimator = r03;
        r03.addUpdateListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda10(this));
        this.showAnimator.addListener(new AnonymousClass25(this));
        HwEmojis.prepare(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda11(this), true);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.notificationsLocker.lock();
        this.showAnimator.setDuration(800);
        this.emojiGridView.setLayerType(2, null);
        this.searchBox.setLayerType(2, null);
        this.emojiTabsShadow.setLayerType(2, null);
        this.backgroundView.setLayerType(2, null);
        View r04 = this.bubble2View;
        if (r04 == null) goto L23;
        r04.setLayerType(2, null);
    L23:
        View r05 = this.bubble1View;
        if (r05 == null) goto L26;
        r05.setLayerType(2, null);
    L26:
        this.emojiTabs.showRecentTabStub(true);
        updateShow(0.0f);
        return;
    L28:
        checkScroll();
        updateShow(1.0f);
    }

    public void preload(int r5, int r6) {
        if (MediaDataController.getInstance(r6) == null) goto L51;
        MediaDataController.getInstance(r6).checkStickers(5);
        if (r5 != 14) goto L10;
        MessagesController.getInstance(this.currentAccount).getAvailableEffects();
        return;
    L10:
        if (r5 != 1) goto L12;
    L48:
        MediaDataController.getInstance(r6).checkReactions();
        return;
    L12:
        if (r5 == 11) goto L48;
        if (r5 == 2) goto L48;
        if (r5 == 6) goto L48;
        if (r5 == 13) goto L48;
        if (r5 == 9) goto L44;
        if (r5 == 10) goto L44;
        if (r5 != 0) goto L27;
    L41:
        MediaDataController.getInstance(r6).fetchEmojiStatuses(0, true);
        MediaDataController.getInstance(r6).getStickerSet(new TLRPC.TL_inputStickerSetEmojiDefaultStatuses(), false);
        return;
    L27:
        if (r5 == 12) goto L41;
        if (r5 != 15) goto L34;
        MediaDataController.getInstance(r6).getStickerSet(new TLRPC.TL_inputStickerSetEmojiDefaultStatuses(), false);
        return;
    L34:
        if (r5 != 3) goto L38;
        MediaDataController.getInstance(r6).checkDefaultTopicIcons();
        return;
    L38:
        if (r5 != 4) goto L50;
        MediaDataController.getInstance(r6).loadRecents(0, false, true, false);
        MediaDataController.getInstance(r6).checkStickers(0);
        return;
    L50:
        return;
    L44:
        if (MessagesController.getInstance(r6).getMainSettings().getBoolean("resetemojipacks", true) == false) goto L46;
        MediaDataController.getInstance(r6).loadStickers(5, false, false);
        MessagesController.getInstance(r6).getMainSettings().edit().putBoolean("resetemojipacks", false).commit();
    L46:
        MediaDataController.getInstance(r6).fetchEmojiStatuses(2, false);
        MediaDataController.getInstance(r6).loadRestrictedStatusEmojis();
        MediaDataController.getInstance(r6).getStickerSet(new TLRPC.TL_inputStickerSetEmojiChannelDefaultStatuses(), false);
        return;
    }

    public boolean prevWindowKeyboardVisible() {
        return false;
    }

    public void resetBackgroundBitmaps() {
        int r1 = 0;
    L3:
        int r2 = this.emojiGridView.lineDrawables.size();
        EmojiListView r3 = this.emojiGridView;
        if (r1 >= r2) goto L13;
        EmojiListView.DrawingInBackgroundLine r22 = r3.lineDrawables.get(r1);
        int r32 = 0;
    L7:
        if (r32 >= r22.imageViewEmojis.size()) goto L12;
        if (r22.imageViewEmojis.get(r32).notDraw == false) goto L11;
        r22.imageViewEmojis.get(r32).notDraw = false;
        r22.imageViewEmojis.get(r32).invalidate();
        r22.reset();
    L11:
        r32 = r32 + 1;
        goto L7
    L12:
        r1 = r1 + 1;
        goto L3
    L13:
        r3.invalidate();
        int r12 = 0;
    L14:
        int r23 = this.emojiSearchGridView.lineDrawables.size();
        EmojiListView r33 = this.emojiSearchGridView;
        if (r12 >= r23) goto L24;
        EmojiListView.DrawingInBackgroundLine r24 = r33.lineDrawables.get(r12);
        int r34 = 0;
    L18:
        if (r34 >= r24.imageViewEmojis.size()) goto L23;
        if (r24.imageViewEmojis.get(r34).notDraw == false) goto L22;
        r24.imageViewEmojis.get(r34).notDraw = false;
        r24.imageViewEmojis.get(r34).invalidate();
        r24.reset();
    L22:
        r34 = r34 + 1;
        goto L18
    L23:
        r12 = r12 + 1;
        goto L14
    L24:
        r33.invalidate();
    }

    public void search(String r2) {
        search(r2, true, true);
    }

    public void search(String r11, boolean r12, boolean r13) {
        Runnable r0 = this.clearSearchRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
        this.clearSearchRunnable = null;
    L5:
        Runnable r02 = this.searchRunnable;
        if (r02 == null) goto L9;
        AndroidUtilities.cancelRunOnUIThread(r02);
        this.searchRunnable = null;
    L9:
        if (TextUtils.isEmpty(r11) == false) goto L14;
        this.searching = false;
        this.searched = false;
        switchGrids(false, r12);
        SearchBox r112 = this.searchBox;
        if (r112 == null) goto L13;
        r112.showProgress(false);
        SearchBox.m18736$$Nest$mtoggleClear(this.searchBox, false);
    L13:
        this.searchAdapter.updateRows(true);
        this.lastQuery = null;
        SelectAnimatedEmojiDialog r5 = this;
    L41:
        r5.updateSearchBox();
        return;
    L14:
        boolean r03 = this.searching;
        boolean r8 = !r03;
        this.searching = true;
        this.searched = false;
        this.searchedLiftUp = r12;
        SearchBox r1 = this.searchBox;
        if (r1 == null) goto L17;
        r1.showProgress(true);
    L17:
        if (r03 == true) goto L29;
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r04 = this.searchResult;
        if (r04 == null) goto L21;
        r04.clear();
    L21:
        ArrayList<ReactionsLayoutInBubble.VisibleReaction> r05 = this.searchResultStickers;
        if (r05 == null) goto L24;
        r05.clear();
    L24:
        ArrayList<TLRPC.Document> r06 = this.searchSets;
        if (r06 == null) goto L27;
        r06.clear();
    L27:
        this.searchAdapter.updateRows(false);
    L31:
        this.lastQuery = r11;
        String[] r9 = AndroidUtilities.getCurrentKeyboardLanguage();
        if (Arrays.equals(r9, lastSearchKeyboardLanguage) == true) goto L34;
        MediaDataController.getInstance(this.currentAccount).fetchNewEmojiKeywords(r9);
    L34:
        lastSearchKeyboardLanguage = r9;
        r5 = this;
        SelectAnimatedEmojiDialog$$ExternalSyntheticLambda14 r4 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda14(r5, r11, r12, r8, r9);
        r5.searchRunnable = r4;
        if (r13 == false) goto L37;
        long r10 = 425;
    L38:
        AndroidUtilities.runOnUIThread(r4, r10);
        SearchBox r102 = r5.searchBox;
        if (r102 == null) goto L41;
        r102.showProgress(true);
        SearchBox.m18736$$Nest$mtoggleClear(r5.searchBox, r12);
        goto L41
    L37:
        r10 = 0;
        goto L38
    L29:
        if (r11.equals(this.lastQuery) == true) goto L31;
        SelectAnimatedEmojiDialog$$ExternalSyntheticLambda13 r07 = new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda13(this);
        this.clearSearchRunnable = r07;
        AndroidUtilities.runOnUIThread(r07, 120);
        goto L31
    }

    public void setAnimationsEnabled(boolean r1) {
        this.animationsEnabled = r1;
    }

    public void setBackgroundDelegate(BackgroundDelegate r1) {
        this.backgroundDelegate = r1;
    }

    public void setDefaultBadge(Long r3) {
        if (r3 != null) goto L4;
        long r0 = 0;
    L5:
        this.defaultBadgeId = r0;
        return;
    L4:
        r0 = r3.longValue();
        goto L5
    }

    public void setDrawBackground(boolean r2) {
        this.drawBackground = r2;
        this.contentView.setClipToOutline(r2);
        View r1 = this.backgroundView;
        if (r2 == true) goto L6;
        r1.setVisibility(8);
        return;
    L6:
        r1.setVisibility(0);
    }

    public void setEnterAnimationInProgress(boolean r3) {
        if (this.enterAnimationInProgress == r3) goto L13;
        this.enterAnimationInProgress = r3;
        if (r3 == true) goto L14;
        AndroidUtilities.forEachViews(this.emojiGridView, new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda0());
        int r32 = 0;
    L7:
        int r0 = this.emojiTabs.contentView.getChildCount();
        EmojiTabsStrip r1 = this.emojiTabs;
        if (r32 >= r0) goto L10;
        View r02 = r1.contentView.getChildAt(r32);
        r02.setScaleX(1.0f);
        r02.setScaleY(1.0f);
        r32 = r32 + 1;
        goto L7
    L10:
        r1.contentView.invalidate();
        return;
    L14:
        return;
    }

    public void setExpireDateHint(int r2) {
        if (r2 > 0) goto L4;
        return;
    L4:
        this.includeHint = true;
        this.hintExpireDate = Integer.valueOf(r2);
        updateRows(true, false);
    }

    public void setForUser(boolean r1) {
        this.forUser = r1;
        updateRows(false, false);
    }

    public void setForumIconDrawable(Drawable r1) {
        this.forumIconDrawable = r1;
        ImageViewEmoji r0 = this.forumIconImage;
        if (r0 == null) goto L6;
        r0.imageReceiver.setImageBitmap(r1);
        return;
    }

    public void setLongPressEnabled(boolean r1) {
        this.isLongPressEnabled = r1;
    }

    public void setMultiSelected(Long r8, boolean r9) {
        boolean r0 = this.selectedDocumentIds.contains(r8);
        HashSet<Long> r1 = this.selectedDocumentIds;
        int r2 = 0;
        if (r0 == true) goto L5;
        r1.add(r8);
        boolean r02 = true;
    L7:
        if (this.emojiGridView == null) goto L31;
    L8:
        int r12 = this.emojiGridView.getChildCount();
        EmojiListView r3 = this.emojiGridView;
        if (r2 >= r12) goto L23;
        if ((r3.getChildAt(r2) instanceof ImageViewEmoji) == false) goto L22;
        ImageViewEmoji r13 = (ImageViewEmoji) this.emojiGridView.getChildAt(r2);
        AnimatedEmojiSpan r32 = r13.span;
        if (r32 != null) goto L15;
    L17:
        TLRPC.Document r33 = r13.document;
        if (r33 == null) goto L22;
        if (r33.id != r8.longValue()) goto L22;
        r13.setViewSelectedWithScale(r02, r9);
        goto L22
    L15:
        if (r32.getDocumentId() != r8.longValue()) goto L17;
        r13.setViewSelectedWithScale(r02, r9);
    L22:
        r2 = r2 + 1;
        goto L8
    L23:
        r3.invalidate();
        return;
    L31:
        return;
    L5:
        r1.remove(r8);
        r02 = false;
        goto L7
    }

    public void setOnLongPressedListener(onLongPressedListener r1) {
        this.bigReactionListener = r1;
    }

    public void setOnRecentClearedListener(onRecentClearedListener r1) {
        this.onRecentClearedListener = r1;
    }

    public void setPaused(boolean r2, boolean r3) {
        if (this.paused == r2) goto L13;
        this.paused = r2;
        this.pausedExceptSelected = r3;
        EmojiListView r22 = this.emojiGridView;
        if (r22 == null) goto L8;
        r22.invalidate();
    L8:
        EmojiListView r1 = this.emojiSearchGridView;
        if (r1 == null) goto L12;
        r1.invalidate();
        return;
    L12:
        return;
    }

    @Override // android.view.View
    public void setPressed(boolean r1) {
    }

    public void setRecentReactions(List<ReactionsLayoutInBubble.VisibleReaction> r2) {
        this.recentReactionsToSet = r2;
        updateRows(false, true);
    }

    public void setSaveState(int r1) {
        this.listStateId = Integer.valueOf(r1);
    }

    public void setScrimDrawable(AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2, View r3) {
        if (r2 != null) goto L4;
    L7:
        int r0 = 0;
    L8:
        this.scrimColor = r0;
        this.scrimDrawable = r2;
        this.scrimDrawableParent = r3;
        if (this.isAttached == false) goto L12;
        if (r2 == null) goto L12;
        r2.setSecondParent(this);
    L12:
        invalidate();
        return;
    L4:
        if (r2.getColor() == null) goto L7;
        r0 = r2.getColor().intValue();
        goto L8
    }

    public void setSelected(Long r7) {
        this.selectedDocumentIds.clear();
        this.selectedDocumentIds.add(r7);
        if (this.emojiGridView == null) goto L20;
        int r72 = 0;
    L5:
        int r0 = this.emojiGridView.getChildCount();
        EmojiListView r1 = this.emojiGridView;
        if (r72 >= r0) goto L14;
        if ((r1.getChildAt(r72) instanceof ImageViewEmoji) == false) goto L13;
        ImageViewEmoji r02 = (ImageViewEmoji) this.emojiGridView.getChildAt(r72);
        AnimatedEmojiSpan r12 = r02.span;
        HashSet<Long> r2 = this.selectedDocumentIds;
        if (r12 == null) goto L12;
        r02.setViewSelected(r2.contains(Long.valueOf(r12.getDocumentId())), true);
        goto L13
    L12:
        r02.setViewSelected(r2.contains(0L), true);
    L13:
        r72 = r72 + 1;
        goto L5
    L14:
        r1.invalidate();
        return;
    }

    public void setSelectedReaction(ReactionsLayoutInBubble.VisibleReaction r6) {
        this.selectedReactions.clear();
        this.selectedReactions.add(r6);
        int r0 = 0;
        if (this.emojiGridView == null) goto L13;
        int r62 = 0;
    L5:
        int r2 = this.emojiGridView.getChildCount();
        EmojiListView r3 = this.emojiGridView;
        if (r62 >= r2) goto L11;
        if ((r3.getChildAt(r62) instanceof ImageViewEmoji) == false) goto L10;
        ImageViewEmoji r22 = (ImageViewEmoji) this.emojiGridView.getChildAt(r62);
        r22.setViewSelected(this.selectedReactions.contains(r22.reaction), true);
    L10:
        r62 = r62 + 1;
        goto L5
    L11:
        r3.invalidate();
    L13:
        if (this.emojiSearchGridView == null) goto L28;
    L14:
        int r63 = this.emojiSearchGridView.getChildCount();
        EmojiListView r23 = this.emojiSearchGridView;
        if (r0 >= r63) goto L20;
        if ((r23.getChildAt(r0) instanceof ImageViewEmoji) == false) goto L19;
        ImageViewEmoji r64 = (ImageViewEmoji) this.emojiSearchGridView.getChildAt(r0);
        r64.setViewSelected(this.selectedReactions.contains(r64.reaction), true);
    L19:
        r0 = r0 + 1;
        goto L14
    L20:
        r23.invalidate();
        return;
    }

    public void setSelectedReactions(ArrayList<String> r6) {
        this.selectedReactions.clear();
        int r0 = r6.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L6;
        String r3 = r6.get(r2);
        r2 = r2 + 1;
        this.selectedReactions.add(ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r3));
        goto L3
    L6:
        if (this.emojiGridView == null) goto L19;
    L7:
        int r62 = this.emojiGridView.getChildCount();
        EmojiListView r02 = this.emojiGridView;
        if (r1 >= r62) goto L13;
        if ((r02.getChildAt(r1) instanceof ImageViewEmoji) == false) goto L12;
        ImageViewEmoji r63 = (ImageViewEmoji) this.emojiGridView.getChildAt(r1);
        r63.setViewSelected(this.selectedReactions.contains(r63.reaction), true);
    L12:
        r1 = r1 + 1;
        goto L7
    L13:
        r02.invalidate();
        return;
    }

    public void setSelectedReactions(HashSet<ReactionsLayoutInBubble.VisibleReaction> r6) {
        this.selectedReactions = r6;
        this.selectedDocumentIds.clear();
        ArrayList r0 = new ArrayList(r6);
        int r62 = 0;
    L4:
        if (r62 >= r0.size()) goto L11;
        if (r0.get(r62) == null) goto L10;
        if (((ReactionsLayoutInBubble.VisibleReaction) r0.get(r62)).documentId == 0) goto L10;
        this.selectedDocumentIds.add(Long.valueOf(((ReactionsLayoutInBubble.VisibleReaction) r0.get(r62)).documentId));
    L10:
        r62 = r62 + 1;
        goto L4
    }

    public void switchGrids(boolean r4, boolean r5) {
        if (this.gridSearch != r4) goto L5;
        return;
    L5:
        this.gridSearch = r4;
        this.emojiGridView.setVisibility(0);
        this.emojiSearchGridView.setVisibility(0);
        ValueAnimator r0 = this.gridSwitchAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        ValueAnimator r02 = this.searchEmptyViewAnimator;
        if (r02 == null) goto L11;
        r02.cancel();
        this.searchEmptyViewAnimator = null;
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.gridSwitchAnimator = r03;
        r03.addUpdateListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda18(this, r4));
        this.gridSwitchAnimator.addListener(new AnonymousClass22(this, r4));
        this.gridSwitchAnimator.setDuration(320);
        this.gridSwitchAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.gridSwitchAnimator.start();
        ViewPropertyAnimator r42 = ((View) this.emojiGridView.getParent()).animate();
        if (this.gridSearch == false) goto L15;
        if (r5 == false) goto L15;
        float r04 = -AndroidUtilities.dp(36.0f);
    L16:
        r42.translationY(r04).setUpdateListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda19(this)).setInterpolator(CubicBezierInterpolator.DEFAULT).setDuration(160).start();
        if (this.gridSearch == false) goto L20;
        if (r5 == false) goto L20;
        this.emojiSearchGridView.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(54.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(38.0f));
    L21:
        checkScroll();
        return;
    L20:
        this.emojiSearchGridView.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(54.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(38.0f));
    L15:
        r04 = 0.0f;
        goto L16
    }

    public void switchSearchEmptyView(boolean r4) {
        if (this.searchEmptyViewVisible == r4) goto L13;
        this.searchEmptyViewVisible = r4;
        ValueAnimator r0 = this.searchEmptyViewAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.searchEmptyViewAnimator = r02;
        r02.addUpdateListener(new SelectAnimatedEmojiDialog$$ExternalSyntheticLambda38(this, r4));
        this.searchEmptyViewAnimator.addListener(new AnonymousClass23(this, r4));
        this.searchEmptyViewAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.searchEmptyViewAnimator.setDuration(100);
        this.searchEmptyViewAnimator.start();
        if (r4 == false) goto L12;
        updateSearchEmptyViewImage(this.currentAccount, this.emojiSearchEmptyViewImageView);
        return;
    L12:
        return;
    }

    public boolean unselect(Long r13) {
        this.selectedDocumentIds.remove(r13);
        int r1 = 0;
        if (this.emojiGridView == null) goto L36;
        int r0 = 0;
        boolean r2 = false;
    L5:
        int r3 = this.emojiGridView.getChildCount();
        EmojiListView r4 = this.emojiGridView;
        if (r0 >= r3) goto L21;
        if ((r4.getChildAt(r0) instanceof ImageViewEmoji) == false) goto L20;
        ImageViewEmoji r32 = (ImageViewEmoji) this.emojiGridView.getChildAt(r0);
        AnimatedEmojiSpan r42 = r32.span;
        if (r42 != null) goto L12;
    L15:
        TLRPC.Document r43 = r32.document;
        if (r43 == null) goto L20;
        if (r43.id != r13.longValue()) goto L20;
        r32.unselectWithScale();
    L14:
        r2 = true;
        goto L20
    L12:
        if (r42.getDocumentId() != r13.longValue()) goto L15;
        r32.unselectWithScale();
    L20:
        r0 = r0 + 1;
        goto L5
    L21:
        r4.invalidate();
        if (r2 == false) goto L24;
    L35:
        return r2;
    L24:
        if (r1 >= this.rowHashCodes.size()) goto L35;
        long r33 = this.rowHashCodes.get(r1).longValue();
        if (r33 == ((r13.longValue() * 13) + 62425)) goto L31;
        if (r33 == ((r13.longValue() * 13) + 3212)) goto L31;
        r1 = r1 + 1;
    L31:
        Adapter r12 = this.adapter;
        if (r12 == null) goto L34;
        r12.notifyItemChanged(r1);
    L34:
        return true;
    L36:
        return false;
    }

    public boolean willApplyEmoji(View r1, Long r2, TLRPC.Document r3, TL_stars.TL_starGiftUnique r4, Integer r5) {
        return true;
    }
}
