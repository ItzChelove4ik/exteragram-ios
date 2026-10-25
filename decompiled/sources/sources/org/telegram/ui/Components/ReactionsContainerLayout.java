package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.chats.GlassMenuHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
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
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Premium.PremiumLockIconView;
import org.telegram.ui.Components.Reactions.CustomEmojiReactionsWindow;
import org.telegram.ui.Components.Reactions.HwEmojis;
import org.telegram.ui.Components.Reactions.ReactionsEffectOverlay;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundProvider;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public class ReactionsContainerLayout extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    public static final Property<ReactionsContainerLayout, Float> TRANSITION_PROGRESS_VALUE = null;
    private boolean allReactionsAvailable;
    private boolean allReactionsIsDefault;
    public List<ReactionsLayoutInBubble.VisibleReaction> allReactionsList;
    final HashSet<ReactionsLayoutInBubble.VisibleReaction> alwaysSelectedReactions;
    private boolean animatePopup;
    private final boolean animationEnabled;
    private final Paint bgPaint;
    public int bigCircleOffset;
    private float bigCircleRadius;
    public float bubblesOffset;
    ValueAnimator cancelPressedAnimation;
    private float cancelPressedProgress;
    public boolean channelReactions;
    ChatScrimPopupContainerLayout chatScrimPopupContainerLayout;
    private boolean clicked;
    private int currentAccount;
    private float customEmojiReactionsEnterProgress;
    private InternalImageView customEmojiReactionsIconView;
    FrameLayout customReactionsContainer;
    private ReactionsContainerDelegate delegate;
    public final float durationScale;
    private float flipVerticalProgress;
    public boolean forceAttachToParent;
    BaseFragment fragment;
    private BlurredBackgroundDrawable glassBackground;
    private BlurredBackgroundDrawable glassBackgroundBubbleBig;
    private BlurredBackgroundDrawable glassBackgroundBubbleSmall;
    private BlurredBackgroundProvider glassBackgroundColorProvider;
    private BlurredBackgroundDrawableViewFactory glassBackgroundFactory;
    public boolean hasHint;
    private boolean hasStar;
    private boolean hintMeasured;
    public TextView hintView;
    public int hintViewHeight;
    public int hintViewWidth;
    public boolean hitLimit;
    private boolean isFlippedVertically;
    public boolean isHiddenNextReaction;
    private boolean isTop;
    public ArrayList<InnerItem> items;
    long lastReactionSentTime;
    private long lastUpdate;
    HashSet<View> lastVisibleViews;
    HashSet<View> lastVisibleViewsTmp;
    private float leftAlpha;
    private final Paint leftShadowPaint;
    private LinearLayoutManager linearLayoutManager;
    private Adapter listAdapter;
    private int[] location;
    private Path mPath;
    public MessageObject messageObject;
    private float miniBubblesOffset;
    private boolean mirrorX;
    public ReactionHolderView nextRecentReaction;
    public final AnimationNotificationsLocker notificationsLocker;
    public ArrayList<InnerItem> oldItems;
    private Runnable onSwitchedToLoopView;
    private float otherViewsScale;
    ChatScrimPopupContainerLayout parentLayout;
    public boolean paused;
    public boolean pausedExceptSelected;
    FrameLayout premiumLockContainer;
    private PremiumLockIconView premiumLockIconView;
    private List<TLRPC.TL_availableReaction> premiumLockedReactions;
    private boolean prepareAnimation;
    private float pressedProgress;
    private ReactionsLayoutInBubble.VisibleReaction pressedReaction;
    private int pressedReactionPosition;
    private float pressedViewScale;
    ValueAnimator pullingDownBackAnimator;
    float pullingLeftOffset;
    public float radius;
    CustomEmojiReactionsWindow reactionsWindow;
    public RectF rect;
    RectF rectF;
    public final RecyclerListView recyclerListView;
    Theme.ResourcesProvider resourcesProvider;
    private float rightAlpha;
    private final Paint rightShadowPaint;
    private final Paint selectedPaint;
    final HashSet<ReactionsLayoutInBubble.VisibleReaction> selectedReactions;
    private Drawable shadow;
    private Rect shadowPad;
    private boolean showExpandableReactions;
    boolean skipDraw;
    public boolean skipEnterAnimation;
    private float smallCircleRadius;
    private LinearGradient starSelectedGradient;
    private Matrix starSelectedGradientMatrix;
    private Paint starSelectedGradientPaint;
    private final Paint starSelectedPaint;
    private float transitionProgress;
    private List<String> triggeredReactions;
    private final int type;
    private List<ReactionsLayoutInBubble.VisibleReaction> visibleReactionsList;
    private long waitingLoadingChatId;

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends Property<ReactionsContainerLayout, Float> {
        public AnonymousClass1(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(ReactionsContainerLayout r1) {
            return Float.valueOf(ReactionsContainerLayout.m12403$$Nest$fgettransitionProgress(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(ReactionsContainerLayout r1) {
            return get2(r1);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(ReactionsContainerLayout r1, Float r2) {
            set2(r1, r2);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(ReactionsContainerLayout r1, Float r2) {
            r1.setTransitionProgress(r2.floatValue());
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public AnonymousClass2(ReactionsContainerLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (r4.getAction() == 1) goto L7;
            if (r4.getAction() == 3) goto L7;
        L13:
            return super.dispatchTouchEvent(r4);
        L7:
            if (r4.getAction() == 1) goto L9;
        L11:
            ReactionsContainerLayout.m12415$$Nest$manimatePullingBack(this.this$0);
            goto L13
        L9:
            if (this.this$0.getPullingLeftProgress() <= 0.95f) goto L11;
            ReactionsContainerLayout.m12418$$Nest$mshowCustomEmojiReactionDialog(this.this$0);
            goto L13
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r3, View r4, long r5) {
            if (ReactionsContainerLayout.m12398$$Nest$fgetpressedReaction(this.this$0) == null) goto L11;
            if ((r4 instanceof ReactionHolderView) == false) goto L11;
            if (((ReactionHolderView) r4).currentReaction.equals(ReactionsContainerLayout.m12398$$Nest$fgetpressedReaction(this.this$0)) == false) goto L11;
            return true;
        L11:
            return super.drawChild(r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends LinearLayoutManager {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public AnonymousClass3(ReactionsContainerLayout r1, Context r2, int r3, boolean r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollHorizontallyBy(int r9, RecyclerView.Recycler r10, RecyclerView.State r11) {
            boolean r2 = false;
            if (r9 >= 0) goto L24;
            ReactionsContainerLayout r4 = this.this$0;
            if (r4.pullingLeftOffset == 0.0f) goto L24;
            float r42 = r4.getPullingLeftProgress();
            ReactionsContainerLayout r5 = this.this$0;
            r5.pullingLeftOffset += r9;
            float r92 = r5.getPullingLeftProgress();
            if (r42 <= 1.0f) goto L9;
            boolean r43 = true;
        L11:
            if (r92 <= 1.0f) goto L13;
            boolean r93 = true;
        L14:
            if (r43 != r93) goto L56;
        L16:
            ReactionsContainerLayout r94 = this.this$0;
            float r44 = r94.pullingLeftOffset;
            if (r44 >= 0.0f) goto L19;
            int r45 = (int) r44;
            r94.pullingLeftOffset = 0.0f;
        L20:
            FrameLayout r95 = r94.customReactionsContainer;
            if (r95 == null) goto L23;
            r95.invalidate();
        L23:
            this.this$0.recyclerListView.invalidate();
            r9 = r45;
            goto L24
        L19:
            r45 = 0;
            goto L20
        L56:
            this.this$0.recyclerListView.performHapticFeedback(3);     // Catch: Exception -> L52
            goto L16
        L13:
            r93 = false;
            goto L14
        L9:
            r43 = false;
        L24:
            int r102 = super.scrollHorizontallyBy(r9, r10, r11);
            if (r9 <= 0) goto L51;
            if (r102 != 0) goto L51;
            if (this.this$0.recyclerListView.getScrollState() != 1) goto L51;
            if (this.this$0.showCustomEmojiReaction() == false) goto L51;
            ValueAnimator r112 = this.this$0.pullingDownBackAnimator;
            if (r112 == null) goto L34;
            r112.removeAllListeners();
            this.this$0.pullingDownBackAnimator.cancel();
        L34:
            float r113 = this.this$0.getPullingLeftProgress();
            if (r113 <= 1.0f) goto L37;
            float r46 = 0.05f;
        L38:
            ReactionsContainerLayout r52 = this.this$0;
            r52.pullingLeftOffset += r9 * r46;
            float r96 = r52.getPullingLeftProgress();
            if (r113 <= 1.0f) goto L41;
            boolean r114 = true;
        L43:
            if (r96 <= 1.0f) goto L45;
            r2 = true;
        L45:
            if (r114 != r2) goto L54;
        L47:
            FrameLayout r97 = this.this$0.customReactionsContainer;
            if (r97 == null) goto L50;
            r97.invalidate();
        L50:
            this.this$0.recyclerListView.invalidate();
            goto L51
        L54:
            this.this$0.recyclerListView.performHapticFeedback(3);     // Catch: Exception -> L53
            goto L47
        L41:
            r114 = false;
            goto L43
        L37:
            r46 = 0.6f;
        L51:
            return r102;
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.ItemDecoration {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public AnonymousClass4(ReactionsContainerLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            super.getItemOffsets(r1, r2, r3, r4);
            if (this.this$0.showCustomEmojiReaction() == true) goto L18;
            int r22 = r3.getChildAdapterPosition(r2);
            if (r22 != 0) goto L7;
            r1.left = AndroidUtilities.dp(6.0f);
        L7:
            r1.right = AndroidUtilities.dp(4.0f);
            if (r22 == (ReactionsContainerLayout.m12393$$Nest$fgetlistAdapter(this.this$0).getItemCount() - 1)) goto L10;
            return;
        L10:
            if (ReactionsContainerLayout.m12420$$Nest$mshowUnlockPremiumButton(this.this$0) == false) goto L12;
        L16:
            r1.right = AndroidUtilities.dp(2.0f);
            return;
        L12:
            if (this.this$0.showCustomEmojiReaction() == true) goto L16;
            r1.right = AndroidUtilities.dp(6.0f);
            return;
        L18:
            r1.left = 0;
            r1.right = 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public AnonymousClass5(ReactionsContainerLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r9, int r10, int r11) {
            if (r9.getChildCount() <= 2) goto L11;
            r9.getLocationInWindow(ReactionsContainerLayout.m12394$$Nest$fgetlocation(this.this$0));
            int r102 = ReactionsContainerLayout.m12394$$Nest$fgetlocation(this.this$0)[0];
            View r2 = r9.getChildAt(0);
            r2.getLocationInWindow(ReactionsContainerLayout.m12394$$Nest$fgetlocation(this.this$0));
            float r3 = ((1.0f - Math.min(1.0f, (-Math.min(ReactionsContainerLayout.m12394$$Nest$fgetlocation(this.this$0)[0] - r102, 0.0f)) / r2.getWidth())) * 0.39999998f) + 0.6f;
            if (Float.isNaN(r3) == false) goto L7;
            r3 = 1.0f;
        L7:
            ReactionsContainerLayout.m12417$$Nest$msetChildScale(this.this$0, r2, r3);
            View r22 = r9.getChildAt(r9.getChildCount() - 1);
            r22.getLocationInWindow(ReactionsContainerLayout.m12394$$Nest$fgetlocation(this.this$0));
            int r112 = ReactionsContainerLayout.m12394$$Nest$fgetlocation(this.this$0)[0];
            float r92 = ((1.0f - Math.min(1.0f, (-Math.min((r102 + r9.getWidth()) - (r112 + r22.getWidth()), 0.0f)) / r22.getWidth())) * 0.39999998f) + 0.6f;
            if (Float.isNaN(r92) == false) goto L10;
            r92 = 1.0f;
        L10:
            ReactionsContainerLayout.m12417$$Nest$msetChildScale(this.this$0, r22, r92);
        L11:
            int r93 = 1;
        L12:
            int r103 = this.this$0.recyclerListView.getChildCount() - 1;
            ReactionsContainerLayout r113 = this.this$0;
            if (r93 >= r103) goto L15;
            ReactionsContainerLayout.m12417$$Nest$msetChildScale(this.this$0, r113.recyclerListView.getChildAt(r93), 1.0f);
            r93 = r93 + 1;
            goto L12
        L15:
            r113.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.ItemDecoration {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public AnonymousClass6(ReactionsContainerLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            int r22 = r3.getChildAdapterPosition(r2);
            if (r22 != 0) goto L6;
            r1.left = AndroidUtilities.dp(8.0f);
        L6:
            if (r22 != (ReactionsContainerLayout.m12393$$Nest$fgetlistAdapter(this.this$0).getItemCount() - 1)) goto L9;
            r1.right = AndroidUtilities.dp(8.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public AnonymousClass7(ReactionsContainerLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.notificationsLocker.unlock();
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$8, reason: invalid class name */
    public class AnonymousClass8 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ ReactionsContainerLayout this$0;
        final /* synthetic */ float val$fromProgress;

        public AnonymousClass8(ReactionsContainerLayout r1, float r2) {
            this.this$0 = r1;
            this.val$fromProgress = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            ReactionsContainerLayout.m12406$$Nest$fputcancelPressedProgress(this.this$0, ((Float) r4.getAnimatedValue()).floatValue());
            ReactionsContainerLayout r42 = this.this$0;
            ReactionsContainerLayout.m12411$$Nest$fputpressedProgress(r42, this.val$fromProgress * (1.0f - ReactionsContainerLayout.m12385$$Nest$fgetcancelPressedProgress(r42)));
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public AnonymousClass9(ReactionsContainerLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            super.onAnimationEnd(r3);
            ReactionsContainerLayout r32 = this.this$0;
            r32.cancelPressedAnimation = null;
            ReactionsContainerLayout.m12411$$Nest$fputpressedProgress(r32, 0.0f);
            ReactionsContainerLayout.m12412$$Nest$fputpressedReaction(this.this$0, null);
            this.this$0.invalidate();
        }
    }

    public class Adapter extends AdapterWithDiffUtils {
        final /* synthetic */ ReactionsContainerLayout this$0;

        public static /* synthetic */ void $r8$lambda$29lAqpIqKXg_BNVnnlfL3uIbLIY(Adapter r0, View r1) {
            r0.lambda$onCreateViewHolder$0(r1);
        }

        /* renamed from: $r8$lambda$G8D7DO0p3G-8qqWZdvCQ0IAsv8g, reason: not valid java name */
        public static /* synthetic */ void m12421$r8$lambda$G8D7DO0p3G8qqWZdvCQ0IAsv8g(Adapter r0, View r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        public Adapter(ReactionsContainerLayout r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(View r5) {
            r5.getLocationOnScreen(new int[2]);
            ReactionsContainerLayout.m12419$$Nest$mshowUnlockPremium(this.this$0, r0[0] + (r5.getMeasuredWidth() / 2.0f), r0[1] + (r5.getMeasuredHeight() / 2.0f));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(View r1) {
            ReactionsContainerLayout.m12418$$Nest$mshowCustomEmojiReactionDialog(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.this$0.items.get(r1).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            if (r3.getItemViewType() != 0) goto L5;
        L8:
            ReactionHolderView r32 = (ReactionHolderView) r3.itemView;
            r32.setScaleX(1.0f);
            r32.setScaleY(1.0f);
            ReactionHolderView.m12423$$Nest$msetReaction(r32, this.this$0.items.get(r4).reaction, r4);
            return;
        L5:
            if (r3.getItemViewType() == 3) goto L8;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r6, int r7) {
            if (r7 != 1) goto L5;
            ReactionsContainerLayout r72 = this.this$0;
            r72.premiumLockContainer = new FrameLayout(this.this$0.getContext());
            ReactionsContainerLayout.m12410$$Nest$fputpremiumLockIconView(this.this$0, new PremiumLockIconView(this.this$0.getContext(), PremiumLockIconView.TYPE_REACTIONS));
            PremiumLockIconView r73 = ReactionsContainerLayout.m12396$$Nest$fgetpremiumLockIconView(this.this$0);
            int r0 = Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon);
            int r1 = Theme.key_dialogBackground;
            r73.setColor(ColorUtils.blendARGB(r0, Theme.getColor(r1), 0.7f));
            ReactionsContainerLayout.m12396$$Nest$fgetpremiumLockIconView(this.this$0).setColorFilter(new PorterDuffColorFilter(Theme.getColor(r1), PorterDuff.Mode.MULTIPLY));
            ReactionsContainerLayout.m12396$$Nest$fgetpremiumLockIconView(this.this$0).setScaleX(0.0f);
            ReactionsContainerLayout.m12396$$Nest$fgetpremiumLockIconView(this.this$0).setScaleY(0.0f);
            ReactionsContainerLayout.m12396$$Nest$fgetpremiumLockIconView(this.this$0).setPadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
            ReactionsContainerLayout r74 = this.this$0;
            r74.premiumLockContainer.addView(ReactionsContainerLayout.m12396$$Nest$fgetpremiumLockIconView(r74), LayoutHelper.createFrame(26, 26, 17));
            ReactionsContainerLayout.m12396$$Nest$fgetpremiumLockIconView(this.this$0).setOnClickListener(new ReactionsContainerLayout$Adapter$$ExternalSyntheticLambda0(this));
            View r62 = this.this$0.premiumLockContainer;
        L18:
            int r75 = ((this.this$0.getLayoutParams().height - ((int) this.this$0.getTopOffset())) - this.this$0.getPaddingTop()) - this.this$0.getPaddingBottom();
            r62.setLayoutParams(new RecyclerView.LayoutParams(r75 - AndroidUtilities.dp(12.0f), r75));
            return new RecyclerListView.Holder(r62);
        L5:
            if (r7 == 2) goto L7;
            ReactionsContainerLayout r76 = this.this$0;
            r62 = new ReactionHolderView(r76, r76.getContext(), true);
            goto L18
        L7:
            ReactionsContainerLayout r77 = this.this$0;
            ReactionsContainerLayout r3 = this.this$0;
            r77.customReactionsContainer = new CustomReactionsContainer(r3, r3.getContext());
            ReactionsContainerLayout r78 = this.this$0;
            ReactionsContainerLayout r32 = this.this$0;
            ReactionsContainerLayout.m12408$$Nest$fputcustomEmojiReactionsIconView(r78, new InternalImageView(r32, r32.getContext()));
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setImageResource(R.drawable.msg_reactions_expand);
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) != 1) goto L10;
        L15:
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        L16:
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setBackground(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(28.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_listSelector), 40)));
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setContentDescription(LocaleController.getString(R.string.AccDescrExpandPanel));
            ReactionsContainerLayout r79 = this.this$0;
            r79.customReactionsContainer.addView(ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(r79), LayoutHelper.createFrame(30, 30, 17));
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setOnClickListener(new ReactionsContainerLayout$Adapter$$ExternalSyntheticLambda1(this));
            r62 = this.this$0.customReactionsContainer;
            goto L18
        L10:
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 2) goto L15;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 4) goto L15;
            ReactionsContainerLayout.m12388$$Nest$fgetcustomEmojiReactionsIconView(this.this$0).setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
            goto L16
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
            if (r4.getItemViewType() != 0) goto L5;
        L6:
            int r0 = r4.getAdapterPosition();
            if (r0 >= 0) goto L9;
        L11:
            super.onViewAttachedToWindow(r4);
            return;
        L9:
            if (r0 >= this.this$0.items.size()) goto L11;
            ((ReactionHolderView) r4.itemView).updateSelected(this.this$0.items.get(r0).reaction, false);
            goto L11
        L5:
            if (r4.getItemViewType() != 3) goto L11;
            goto L6
        }

        public void updateItems(boolean r8) {
            this.this$0.oldItems.clear();
            ReactionsContainerLayout r0 = this.this$0;
            r0.oldItems.addAll(r0.items);
            this.this$0.items.clear();
            int r1 = 0;
        L3:
            int r2 = ReactionsContainerLayout.m12405$$Nest$fgetvisibleReactionsList(this.this$0).size();
            ReactionsContainerLayout r3 = this.this$0;
            if (r1 >= r2) goto L11;
            ReactionsLayoutInBubble.VisibleReaction r22 = (ReactionsLayoutInBubble.VisibleReaction) ReactionsContainerLayout.m12405$$Nest$fgetvisibleReactionsList(r3).get(r1);
            ReactionsContainerLayout r32 = this.this$0;
            ArrayList<InnerItem> r4 = r32.items;
            if (r22.emojicon != null) goto L8;
            int r6 = 3;
        L9:
            r4.add(new InnerItem(r32, r6, r22));
            r1 = r1 + 1;
            goto L3
        L8:
            r6 = 0;
            goto L9
        L11:
            if (ReactionsContainerLayout.m12420$$Nest$mshowUnlockPremiumButton(r3) == false) goto L14;
            ReactionsContainerLayout r02 = this.this$0;
            r02.items.add(new InnerItem(r02, 1, null));
        L14:
            if (this.this$0.showCustomEmojiReaction() == false) goto L16;
            ReactionsContainerLayout r03 = this.this$0;
            r03.items.add(new InnerItem(r03, 2, null));
        L16:
            if (r8 == false) goto L19;
            ReactionsContainerLayout r82 = this.this$0;
            setItems(r82.oldItems, r82.items);
            return;
        L19:
            super.notifyDataSetChanged();
        }
    }

    public class CustomReactionsContainer extends FrameLayout {
        Paint backgroundPaint;
        final /* synthetic */ ReactionsContainerLayout this$0;

        public CustomReactionsContainer(ReactionsContainerLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.backgroundPaint = new Paint(1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r11) {
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) != 1) goto L5;
        L10:
            int r0 = ColorUtils.setAlphaComponent(-1, 30);
        L11:
            this.backgroundPaint.setColor(r0);
            float r02 = getMeasuredHeight() / 2.0f;
            float r2 = getMeasuredWidth() / 2.0f;
            View r3 = getChildAt(0);
            float r4 = (getMeasuredWidth() - AndroidUtilities.dpf2(6.0f)) / 2.0f;
            float r1 = this.this$0.expandSize();
            RectF r5 = AndroidUtilities.rectTmp;
            r5.set(r2 - r4, (r02 - r4) - r1, r2 + r4, (r02 + r4) + r1);
            r11.save();
            r11.scale(r3.getScaleX(), r3.getScaleY(), r2, r02);
            r11.drawRoundRect(r5, r4, r4, this.backgroundPaint);
            r11.restore();
            r11.save();
            r11.translate(0.0f, r1);
            super.dispatchDraw(r11);
            r11.restore();
            return;
        L5:
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 2) goto L10;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 4) goto L10;
            r0 = ColorUtils.blendARGB(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, this.this$0.resourcesProvider), Theme.getColor(Theme.key_dialogBackground, this.this$0.resourcesProvider), 0.7f);
            goto L11
        }
    }

    public class InnerItem extends AdapterWithDiffUtils.Item {
        ReactionsLayoutInBubble.VisibleReaction reaction;
        final /* synthetic */ ReactionsContainerLayout this$0;

        public InnerItem(ReactionsContainerLayout r1, int r2, ReactionsLayoutInBubble.VisibleReaction r3) {
            this.this$0 = r1;
            super(r2, false);
            this.reaction = r3;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if (r6 != null) goto L8;
        L23:
            return false;
        L8:
            if (getClass() != r6.getClass()) goto L23;
            InnerItem r62 = (InnerItem) r6;
            int r2 = this.viewType;
            int r3 = r62.viewType;
            if (r2 != r3) goto L21;
            if (r2 != 0) goto L14;
        L15:
            ReactionsLayoutInBubble.VisibleReaction r5 = this.reaction;
            if (r5 != null) goto L18;
        L20:
            return false;
        L18:
            if (r5.equals(r62.reaction) == false) goto L20;
            return true;
        L14:
            if (r2 == 3) goto L15;
        L21:
            if (r2 != r3) goto L23;
            return true;
        }
    }

    public class InternalImageView extends ImageView {
        boolean isEnter;
        final /* synthetic */ ReactionsContainerLayout this$0;
        ValueAnimator valueAnimator;

        public static /* synthetic */ void $r8$lambda$sjP8JkBLGWpABYKFbStlOwH7gEQ(InternalImageView r0, ValueAnimator r1) {
            r0.lambda$play$0(r1);
        }

        public InternalImageView(ReactionsContainerLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$play$0(ValueAnimator r1) {
            float r12 = ((Float) r1.getAnimatedValue()).floatValue();
            setScaleX(r12);
            setScaleY(r12);
            this.this$0.customReactionsContainer.invalidate();
        }

        public void play(int r5, boolean r6) {
            this.isEnter = true;
            invalidate();
            ValueAnimator r1 = this.valueAnimator;
            if (r1 == null) goto L6;
            r1.removeAllListeners();
            this.valueAnimator.cancel();
        L6:
            if (r6 == false) goto L9;
            ValueAnimator r62 = ValueAnimator.ofFloat(new float[]{getScaleX(), 1.0f});
            this.valueAnimator = r62;
            r62.setInterpolator(AndroidUtilities.overshootInterpolator);
            this.valueAnimator.addUpdateListener(new ReactionsContainerLayout$InternalImageView$$ExternalSyntheticLambda0(this));
            this.valueAnimator.setStartDelay((long) (r5 * this.this$0.durationScale));
            this.valueAnimator.setDuration(300);
            this.valueAnimator.start();
            return;
        L9:
            setScaleX(1.0f);
            setScaleY(1.0f);
        }

        public void resetAnimation() {
            this.isEnter = false;
            setScaleX(0.0f);
            setScaleY(0.0f);
            this.this$0.customReactionsContainer.invalidate();
            ValueAnimator r1 = this.valueAnimator;
            if (r1 == null) goto L6;
            r1.cancel();
            return;
        }
    }

    public final class LeftRightShadowsListener extends RecyclerView.OnScrollListener {
        private ValueAnimator leftAnimator;
        private boolean leftVisible;
        private ValueAnimator rightAnimator;
        private boolean rightVisible;
        final /* synthetic */ ReactionsContainerLayout this$0;

        /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$LeftRightShadowsListener$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ LeftRightShadowsListener this$1;
            final /* synthetic */ Runnable val$onEnd;

            public AnonymousClass1(LeftRightShadowsListener r1, Runnable r2) {
                this.this$1 = r1;
                this.val$onEnd = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                this.val$onEnd.run();
            }
        }

        public static /* synthetic */ void $r8$lambda$6TwvHvK2XC1LhaHQ8PuBN_XeFsc(LeftRightShadowsListener r0) {
            r0.lambda$onScrolled$3();
        }

        public static /* synthetic */ void $r8$lambda$MaEAoej1obmsdOyY0YVAASwbV0o(LeftRightShadowsListener r0, Float r1) {
            r0.lambda$onScrolled$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$YTPt1qrbLcifPA7_qDcFcV3s5Hs(LeftRightShadowsListener r0) {
            r0.lambda$onScrolled$1();
        }

        public static /* synthetic */ void $r8$lambda$eR_ZZAeajN0FQw1ZX4UODyT7F5s(LeftRightShadowsListener r0, Float r1) {
            r0.lambda$onScrolled$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$mnnMo0WrywG4OzdISVEwBjEF5qs(Consumer r0, ValueAnimator r1) {
            r0.accept((Float) r1.getAnimatedValue());
        }

        private LeftRightShadowsListener(ReactionsContainerLayout r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ LeftRightShadowsListener(ReactionsContainerLayout r1, ReactionsContainerLayoutIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onScrolled$0(Float r3) {
            Paint r0 = ReactionsContainerLayout.m12391$$Nest$fgetleftShadowPaint(this.this$0);
            ReactionsContainerLayout r1 = this.this$0;
            float r32 = r3.floatValue();
            ReactionsContainerLayout.m12409$$Nest$fputleftAlpha(r1, r32);
            r0.setAlpha((int) (r32 * 255.0f));
            this.this$0.invalidate();
        }

        private /* synthetic */ void lambda$onScrolled$1() {
            this.leftAnimator = null;
        }

        private /* synthetic */ void lambda$onScrolled$2(Float r3) {
            Paint r0 = ReactionsContainerLayout.m12400$$Nest$fgetrightShadowPaint(this.this$0);
            ReactionsContainerLayout r1 = this.this$0;
            float r32 = r3.floatValue();
            ReactionsContainerLayout.m12414$$Nest$fputrightAlpha(r1, r32);
            r0.setAlpha((int) (r32 * 255.0f));
            this.this$0.invalidate();
        }

        private /* synthetic */ void lambda$onScrolled$3() {
            this.rightAnimator = null;
        }

        private ValueAnimator startAnimator(float r3, float r4, Consumer<Float> r5, Runnable r6) {
            ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r3, r4}).setDuration((long) (Math.abs(r4 - r3) * 150.0f));
            r32.addUpdateListener(new ReactionsContainerLayout$LeftRightShadowsListener$$ExternalSyntheticLambda4(r5));
            r32.addListener(new AnonymousClass1(this, r6));
            r32.start();
            return r32;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r7, int r8, int r9) {
            boolean r82 = false;
            if (ReactionsContainerLayout.m12392$$Nest$fgetlinearLayoutManager(this.this$0).findFirstVisibleItemPosition() == 0) goto L5;
            boolean r72 = true;
        L6:
            float r1 = 0.0f;
            if (r72 == this.leftVisible) goto L17;
            ValueAnimator r0 = this.leftAnimator;
            if (r0 == null) goto L11;
            r0.cancel();
        L11:
            float r02 = ReactionsContainerLayout.m12390$$Nest$fgetleftAlpha(this.this$0);
            if (r72 == false) goto L14;
            float r3 = 1.0f;
        L15:
            this.leftAnimator = startAnimator(r02, r3, new ReactionsContainerLayout$LeftRightShadowsListener$$ExternalSyntheticLambda0(this), new ReactionsContainerLayout$LeftRightShadowsListener$$ExternalSyntheticLambda1(this));
            this.leftVisible = r72;
            goto L17
        L14:
            r3 = 0.0f;
        L17:
            if (ReactionsContainerLayout.m12392$$Nest$fgetlinearLayoutManager(this.this$0).findLastVisibleItemPosition() == (ReactionsContainerLayout.m12393$$Nest$fgetlistAdapter(this.this$0).getItemCount() - 1)) goto L20;
            r82 = true;
        L20:
            if (r82 == this.rightVisible) goto L29;
            ValueAnimator r73 = this.rightAnimator;
            if (r73 == null) goto L24;
            r73.cancel();
        L24:
            float r74 = ReactionsContainerLayout.m12399$$Nest$fgetrightAlpha(this.this$0);
            if (r82 == false) goto L27;
            r1 = 1.0f;
        L27:
            this.rightAnimator = startAnimator(r74, r1, new ReactionsContainerLayout$LeftRightShadowsListener$$ExternalSyntheticLambda2(this), new ReactionsContainerLayout$LeftRightShadowsListener$$ExternalSyntheticLambda3(this));
            this.rightVisible = r82;
            return;
        L29:
            return;
        L5:
            r72 = false;
            goto L6
        }
    }

    public final class ReactionHolderView extends FrameLayout {
        public ReactionsLayoutInBubble.VisibleReaction currentReaction;
        public boolean drawSelected;
        public ValueAnimator enterAnimator;
        public BackupImageView enterImageView;
        public float enterScale;
        public boolean hasEnterAnimation;
        private boolean isEnter;
        public boolean isLocked;
        public PremiumLockIconView lockIconView;
        Runnable longPressRunnable;
        public BackupImageView loopImageView;
        public StarsReactionsSheet.Particles particles;
        Runnable playRunnable;
        public int position;
        private ImageReceiver preloadImageReceiver;
        boolean pressed;
        public BackupImageView pressedBackupImageView;
        float pressedX;
        float pressedY;
        private final boolean recyclerReaction;
        public boolean selected;
        public boolean shouldSwitchToLoopView;
        public float sideScale;
        public boolean switchedToLoopView;
        final /* synthetic */ ReactionsContainerLayout this$0;
        boolean touchable;
        public boolean waitingAnimation;

        /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$ReactionHolderView$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ ReactionHolderView this$1;

            public AnonymousClass1(ReactionHolderView r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.this$1.enterImageView.getImageReceiver().getLottieAnimation() != null) goto L5;
            L9:
                this.this$1.waitingAnimation = false;
                return;
            L5:
                if (this.this$1.enterImageView.getImageReceiver().getLottieAnimation().isRunning() == true) goto L9;
                if (this.this$1.enterImageView.getImageReceiver().getLottieAnimation().isGeneratingCache() == true) goto L9;
                this.this$1.enterImageView.getImageReceiver().getLottieAnimation().start();
                goto L9
            }
        }

        /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$ReactionHolderView$2, reason: invalid class name */
        public class AnonymousClass2 extends BackupImageView {
            final /* synthetic */ ReactionHolderView this$1;
            final /* synthetic */ ReactionsContainerLayout val$this$0;

            /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$ReactionHolderView$2$1, reason: invalid class name */
            public class AnonymousClass1 extends ImageReceiver {
                final /* synthetic */ AnonymousClass2 this$2;

                public AnonymousClass1(AnonymousClass2 r1, View r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // org.telegram.messenger.ImageReceiver
                public boolean setImageBitmapByKey(Drawable r4, String r5, int r6, boolean r7, int r8) {
                    if ((r4 instanceof RLottieDrawable) == false) goto L6;
                    ((RLottieDrawable) r4).setCurrentFrame(0, false, true);
                L6:
                    return super.setImageBitmapByKey(r4, r5, r6, r7, r8);
                }
            }

            /* renamed from: $r8$lambda$p-XiZF0TyBpgb5pAcGCXDarO8Zg, reason: not valid java name */
            public static /* synthetic */ void m12424$r8$lambda$pXiZF0TyBpgb5pAcGCXDarO8Zg(AnonymousClass2 r0) {
                r0.lambda$dispatchDraw$0();
            }

            public AnonymousClass2(ReactionHolderView r1, Context r2, ReactionsContainerLayout r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            private /* synthetic */ void lambda$dispatchDraw$0() {
                this.this$1.enterImageView.setVisibility(4);
            }

            @Override // org.telegram.ui.Components.BackupImageView
            public ImageReceiver createImageReciever() {
                return new AnonymousClass1(this, this);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r3) {
                super.dispatchDraw(r3);
                if (this.imageReceiver.getLottieAnimation() != null) goto L5;
            L7:
                ReactionHolderView r32 = this.this$1;
                if (r32.shouldSwitchToLoopView == true) goto L10;
            L23:
                invalidate();
                return;
            L10:
                if (r32.switchedToLoopView == true) goto L23;
                if (this.imageReceiver.getLottieAnimation() == null) goto L23;
                if (this.imageReceiver.getLottieAnimation().isLastFrame() == false) goto L23;
                if (this.this$1.loopImageView.imageReceiver.getLottieAnimation() == null) goto L23;
                if (this.this$1.loopImageView.imageReceiver.getLottieAnimation().hasBitmap() == false) goto L23;
                ReactionHolderView r33 = this.this$1;
                r33.switchedToLoopView = true;
                r33.loopImageView.imageReceiver.getLottieAnimation().setCurrentFrame(0, false, true);
                this.this$1.loopImageView.setVisibility(0);
                if (ReactionsContainerLayout.m12395$$Nest$fgetonSwitchedToLoopView(this.this$1.this$0) == null) goto L22;
                ReactionsContainerLayout.m12395$$Nest$fgetonSwitchedToLoopView(this.this$1.this$0).run();
            L22:
                AndroidUtilities.runOnUIThread(new ReactionsContainerLayout$ReactionHolderView$2$$ExternalSyntheticLambda0(this));
                goto L23
            L5:
                if (this.this$1.waitingAnimation == true) goto L7;
                this.imageReceiver.getLottieAnimation().start();
                goto L7
            }

            @Override // android.view.View
            public void invalidate() {
                if (HwEmojis.grabIfWeakDevice(new View[]{this, this.this$1.this$0}) == false) goto L5;
                return;
            L5:
                super.invalidate();
                this.this$1.this$0.invalidate();
            }

            @Override // android.view.View
            public void invalidate(int r3, int r4, int r5, int r6) {
                if (HwEmojis.grabIfWeakDevice(new View[]{this}) == false) goto L5;
                return;
            L5:
                super.invalidate(r3, r4, r5, r6);
            }

            @Override // android.view.View
            public void invalidate(Rect r4) {
                if (HwEmojis.grabIfWeakDevice(new View[]{this, this.this$1.this$0}) == false) goto L5;
                return;
            L5:
                super.invalidate(r4);
                this.this$1.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$ReactionHolderView$3, reason: invalid class name */
        public class AnonymousClass3 extends BackupImageView {
            final /* synthetic */ ReactionHolderView this$1;
            final /* synthetic */ ReactionsContainerLayout val$this$0;

            /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$ReactionHolderView$3$1, reason: invalid class name */
            public class AnonymousClass1 extends ImageReceiver {
                final /* synthetic */ AnonymousClass3 this$2;

                public AnonymousClass1(AnonymousClass3 r1, View r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // org.telegram.messenger.ImageReceiver
                public boolean setImageBitmapByKey(Drawable r1, String r2, int r3, boolean r4, int r5) {
                    boolean r0 = super.setImageBitmapByKey(r1, r2, r3, r4, r5);
                    if (r0 == true) goto L5;
                L7:
                    return r0;
                L5:
                    if ((r1 instanceof RLottieDrawable) == false) goto L7;
                    RLottieDrawable r12 = (RLottieDrawable) r1;
                    r12.setCurrentFrame(0, false, true);
                    r12.stop();
                    goto L7
                }
            }

            public AnonymousClass3(ReactionHolderView r1, Context r2, ReactionsContainerLayout r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.BackupImageView
            public ImageReceiver createImageReciever() {
                return new AnonymousClass1(this, this);
            }

            @Override // android.view.View
            public void invalidate() {
                if (HwEmojis.grabIfWeakDevice(new View[]{this}) == false) goto L5;
                return;
            L5:
                super.invalidate();
            }

            @Override // android.view.View
            public void invalidate(int r3, int r4, int r5, int r6) {
                if (HwEmojis.grabIfWeakDevice(new View[]{this}) == false) goto L5;
                return;
            L5:
                super.invalidate(r3, r4, r5, r6);
            }

            @Override // org.telegram.ui.Components.BackupImageView, android.view.View
            public void onDraw(Canvas r2) {
                this.this$1.checkPlayLoopImage();
                super.onDraw(r2);
            }
        }

        /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$ReactionHolderView$4, reason: invalid class name */
        public class AnonymousClass4 extends BackupImageView {
            final /* synthetic */ ReactionHolderView this$1;
            final /* synthetic */ ReactionsContainerLayout val$this$0;

            public AnonymousClass4(ReactionHolderView r1, Context r2, ReactionsContainerLayout r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                this.this$1.this$0.invalidate();
            }

            @Override // org.telegram.ui.Components.BackupImageView, android.view.View
            public void onDraw(Canvas r3) {
                AnimatedEmojiDrawable r0 = this.animatedEmojiDrawable;
                if (r0 == null) goto L5;
                ImageReceiver r02 = r0.getImageReceiver();
            L6:
                if (r02 != null) goto L8;
            L10:
                super.onDraw(r3);
                return;
            L8:
                if (r02.getLottieAnimation() == null) goto L10;
                r02.getLottieAnimation().start();
                goto L10
            L5:
                r02 = this.imageReceiver;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Components.ReactionsContainerLayout$ReactionHolderView$5, reason: invalid class name */
        public class AnonymousClass5 implements Runnable {
            final /* synthetic */ ReactionHolderView this$1;

            public AnonymousClass5(ReactionHolderView r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.this$1.performHapticFeedback(0);     // Catch: Exception -> L5
            L3:
                ReactionsContainerLayout r0 = this.this$1.this$0;
                ReactionsContainerLayout.m12413$$Nest$fputpressedReactionPosition(r0, ReactionsContainerLayout.m12405$$Nest$fgetvisibleReactionsList(r0).indexOf(this.this$1.currentReaction));
                ReactionHolderView r02 = this.this$1;
                ReactionsContainerLayout.m12412$$Nest$fputpressedReaction(r02.this$0, r02.currentReaction);
                this.this$1.this$0.invalidate();
            }
        }

        public static /* synthetic */ void $r8$lambda$YIcXVlv41jUpJ3a4xR6GRhyBsEg(ReactionHolderView r0, ValueAnimator r1) {
            r0.lambda$play$0(r1);
        }

        /* renamed from: -$$Nest$fgetisEnter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12422$$Nest$fgetisEnter(ReactionHolderView r0) {
            return r0.isEnter;
        }

        /* renamed from: -$$Nest$msetReaction, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12423$$Nest$msetReaction(ReactionHolderView r0, ReactionsLayoutInBubble.VisibleReaction r1, int r2) {
            r0.setReaction(r1, r2);
        }

        public ReactionHolderView(ReactionsContainerLayout r4, Context r5, boolean r6) {
            this.this$0 = r4;
            super(r5);
            this.preloadImageReceiver = new ImageReceiver();
            this.sideScale = 1.0f;
            this.drawSelected = true;
            this.playRunnable = new AnonymousClass1(this);
            this.enterScale = 1.0f;
            this.longPressRunnable = new AnonymousClass5(this);
            this.touchable = true;
            this.recyclerReaction = r6;
            this.enterImageView = new AnonymousClass2(this, r5, r4);
            this.loopImageView = new AnonymousClass3(this, r5, r4);
            this.enterImageView.getImageReceiver().setAutoRepeat(0);
            this.enterImageView.getImageReceiver().setAllowStartLottieAnimation(false);
            this.pressedBackupImageView = new AnonymousClass4(this, r5, r4);
            addView(this.enterImageView, LayoutHelper.createFrame(34, 34, 17));
            addView(this.pressedBackupImageView, LayoutHelper.createFrame(34, 34, 17));
            addView(this.loopImageView, LayoutHelper.createFrame(34, 34, 17));
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(r4) != 4) goto L5;
            LayoutTransition r42 = new LayoutTransition();
            r42.setDuration(100);
            r42.enableTransitionType(4);
            setLayoutTransition(r42);
        L5:
            this.enterImageView.setLayerNum(Integer.MAX_VALUE);
            this.loopImageView.setLayerNum(Integer.MAX_VALUE);
            this.loopImageView.imageReceiver.setAutoRepeat(0);
            this.loopImageView.imageReceiver.setAllowStartAnimation(false);
            this.loopImageView.imageReceiver.setAllowStartLottieAnimation(false);
            this.pressedBackupImageView.setLayerNum(Integer.MAX_VALUE);
        }

        private /* synthetic */ void lambda$play$0(ValueAnimator r5) {
            float r52 = ((Float) r5.getAnimatedValue()).floatValue();
            this.enterScale = r52;
            BackupImageView r0 = this.loopImageView;
            float r2 = 1.0f;
            if (this.selected == false) goto L5;
            float r1 = 0.76f;
        L6:
            r0.setScaleY(r52 * r1);
            BackupImageView r53 = this.loopImageView;
            float r02 = this.enterScale;
            if (this.selected == false) goto L9;
            r2 = 0.76f;
        L9:
            r53.setScaleX(r02 * r2);
            return;
        L5:
            r1 = 1.0f;
            goto L6
        }

        private void setReaction(ReactionsLayoutInBubble.VisibleReaction r12, int r13) {
            updateSelected(r12, false);
            ReactionsLayoutInBubble.VisibleReaction r1 = this.currentReaction;
            if (r1 != null) goto L5;
        L8:
            boolean r132 = UserConfig.getInstance(ReactionsContainerLayout.m12387$$Nest$fgetcurrentAccount(this.this$0)).isPremium();
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) != 3) goto L12;
            if (r132 == true) goto L12;
        L16:
            boolean r133 = true;
        L18:
            this.isLocked = r133;
            if (r133 == true) goto L21;
        L23:
            PremiumLockIconView r134 = this.lockIconView;
            if (r134 != null) goto L26;
        L30:
            resetAnimation();
            this.currentReaction = r12;
            if (r12.isStar == false) goto L33;
        L42:
            boolean r135 = true;
        L43:
            this.hasEnterAnimation = r135;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) != 4) goto L46;
        L47:
            this.hasEnterAnimation = false;
        L48:
            ReactionsLayoutInBubble.VisibleReaction r136 = this.currentReaction;
            if (r136.isStar == false) goto L51;
        L65:
            updateImage(r12);
            this.pressedBackupImageView.setAnimatedEmojiDrawable(null);
            if (this.enterImageView.getImageReceiver().getLottieAnimation() == null) goto L68;
            this.enterImageView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false);
        L68:
            PremiumLockIconView r122 = this.lockIconView;
            if (r122 == null) goto L71;
            r122.setAnimatedEmojiDrawable(null);
        L71:
            setFocusable(true);
            boolean r123 = this.hasEnterAnimation;
            this.shouldSwitchToLoopView = r123;
            if (r123 == true) goto L74;
            this.enterImageView.setVisibility(8);
            this.loopImageView.setVisibility(0);
            this.switchedToLoopView = true;
        L75:
            ViewGroup.LayoutParams r124 = this.loopImageView.getLayoutParams();
            ViewGroup.LayoutParams r137 = this.loopImageView.getLayoutParams();
            int r14 = AndroidUtilities.dp(34.0f);
            r137.height = r14;
            r124.width = r14;
            ViewGroup.LayoutParams r125 = this.enterImageView.getLayoutParams();
            ViewGroup.LayoutParams r11 = this.enterImageView.getLayoutParams();
            int r138 = AndroidUtilities.dp(34.0f);
            r11.height = r138;
            r125.width = r138;
            return;
        L74:
            this.switchedToLoopView = false;
            this.enterImageView.setVisibility(0);
            this.loopImageView.setVisibility(8);
            goto L75
        L51:
            if (r136.emojicon != null) goto L65;
            this.pressedBackupImageView.getImageReceiver().clearImage();
            this.loopImageView.getImageReceiver().clearImage();
            AnimatedEmojiDrawable r126 = new AnimatedEmojiDrawable(4, ReactionsContainerLayout.m12387$$Nest$fgetcurrentAccount(this.this$0), this.currentReaction.documentId);
            AnimatedEmojiDrawable r139 = new AnimatedEmojiDrawable(3, ReactionsContainerLayout.m12387$$Nest$fgetcurrentAccount(this.this$0), this.currentReaction.documentId);
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) != 1) goto L56;
        L61:
            PorterDuff.Mode r4 = PorterDuff.Mode.SRC_IN;
            r126.setColorFilter(new PorterDuffColorFilter(-1, r4));
            r139.setColorFilter(new PorterDuffColorFilter(-1, r4));
        L62:
            this.pressedBackupImageView.setAnimatedEmojiDrawable(r126);
            this.loopImageView.setAnimatedEmojiDrawable(r139);
            PremiumLockIconView r127 = this.lockIconView;
            if (r127 == null) goto L71;
            r127.setAnimatedEmojiDrawable(r139);
            goto L71
        L56:
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 2) goto L61;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 4) goto L61;
            int r42 = Theme.key_windowBackgroundWhiteBlueIcon;
            int r5 = Theme.getColor(r42, this.this$0.resourcesProvider);
            PorterDuff.Mode r6 = PorterDuff.Mode.SRC_IN;
            r126.setColorFilter(new PorterDuffColorFilter(r5, r6));
            r139.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r42, this.this$0.resourcesProvider), r6));
            goto L62
        L46:
            if (this.currentReaction.isEffect == false) goto L48;
        L33:
            if (r12.emojicon != null) goto L35;
        L41:
            r135 = false;
            goto L43
        L35:
            if (this.this$0.showCustomEmojiReaction() == true) goto L39;
            if (ReactionsContainerLayout.m12383$$Nest$fgetallReactionsIsDefault(this.this$0) == false) goto L41;
        L39:
            if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == false) goto L41;
        L26:
            if (this.isLocked == false) goto L28;
            int r43 = 0;
        L29:
            r134.setVisibility(r43);
            goto L30
        L28:
            r43 = 8;
            goto L29
        L21:
            if (this.lockIconView != null) goto L23;
            PremiumLockIconView r1310 = new PremiumLockIconView(getContext(), PremiumLockIconView.TYPE_STICKERS_PREMIUM_LOCKED);
            this.lockIconView = r1310;
            r1310.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            this.lockIconView.setImageReceiver(this.loopImageView.getImageReceiver());
            addView(this.lockIconView, LayoutHelper.createFrame(18, 18.0f, 17, 8.0f, 8.0f, 0.0f, 0.0f));
        L12:
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 5) goto L14;
        L17:
            r133 = false;
            goto L18
        L14:
            if (r12.premium == false) goto L17;
            if (r132 == true) goto L17;
        L5:
            if (r1.equals(r12) == false) goto L8;
            this.position = r13;
            updateImage(r12);
        }

        private void updateImage(ReactionsLayoutInBubble.VisibleReaction r27) {
            if (r27 == null) goto L15;
            if (r27.isStar == false) goto L15;
            this.enterImageView.getImageReceiver().setImageBitmap(new RLottieDrawable(R.raw.star_reaction, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f)));
            this.loopImageView.getImageReceiver().setImageBitmap(getContext().getResources().getDrawable(R.drawable.star_reaction));
            if (this.particles == null) goto L9;
            return;
        L9:
            if (SharedConfig.getDevicePerformanceClass() != 2) goto L11;
            int r2 = 45;
        L12:
            this.particles = new StarsReactionsSheet.Particles(1, r2);
            return;
        L11:
            r2 = 18;
        L15:
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) != 4) goto L21;
            if (r27 == null) goto L21;
            if (r27.emojicon == null) goto L21;
            this.enterImageView.getImageReceiver().setImageBitmap(Emoji.getEmojiDrawable(r27.emojicon));
            this.loopImageView.getImageReceiver().setImageBitmap(Emoji.getEmojiDrawable(r27.emojicon));
            return;
        L21:
            ReactionsLayoutInBubble.VisibleReaction r1 = this.currentReaction;
            if (r1.isEffect == false) goto L30;
            TLRPC.Document r12 = MessagesController.getInstance(ReactionsContainerLayout.m12387$$Nest$fgetcurrentAccount(this.this$0)).getEffectDocument(this.currentReaction.documentId);
            SvgHelper.SvgDrawable r22 = DocumentObject.getSvgThumb(r12, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
            ImageReceiver r14 = this.loopImageView.getImageReceiver();
            ImageLocation r15 = ImageLocation.getForDocument(r12);
            if (this.hasEnterAnimation == false) goto L26;
            SvgHelper.SvgDrawable r19 = null;
        L27:
            r14.setImage(r15, "60_60_firstframe", null, null, r19, 0, "tgs", this.currentReaction, 0);
            return;
        L26:
            r19 = r22;
            goto L27
        L30:
            if (r1.emojicon == null) goto L67;
            TLRPC.TL_availableReaction r142 = MediaDataController.getInstance(ReactionsContainerLayout.m12387$$Nest$fgetcurrentAccount(this.this$0)).getReactionsMap().get(this.currentReaction.emojicon);
            if (r142 == null) goto L62;
            SvgHelper.SvgDrawable r6 = DocumentObject.getSvgThumb(r142.activate_animation, Theme.key_windowBackgroundWhiteGrayIcon, 0.2f);
            if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == false) goto L44;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 4) goto L44;
            this.enterImageView.getImageReceiver().setImage(ImageLocation.getForDocument(r142.appear_animation), "30_30_nolimit", null, null, r6, 0, "tgs", r27, 0);
            ImageReceiver r152 = this.loopImageView.getImageReceiver();
            ImageLocation r16 = ImageLocation.getForDocument(r142.select_animation);
            if (this.hasEnterAnimation == false) goto L41;
            SvgHelper.SvgDrawable r20 = null;
        L42:
            r152.setImage(r16, "60_60_pcache", null, null, r20, 0, "tgs", this.currentReaction, 0);
        L59:
            if (this.enterImageView.getImageReceiver().getLottieAnimation() == null) goto L61;
            this.enterImageView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false, true);
        L61:
            this.pressedBackupImageView.getImageReceiver().setImage(ImageLocation.getForDocument(r142.select_animation), "60_60_pcache", null, null, r6, 0, "tgs", r27, 0);
            this.preloadImageReceiver.setAllowStartLottieAnimation(false);
            MediaDataController.getInstance(ReactionsContainerLayout.m12387$$Nest$fgetcurrentAccount(this.this$0)).preloadImage(this.preloadImageReceiver, ImageLocation.getForDocument(r142.around_animation), ReactionsEffectOverlay.getFilterForAroundAnimation());
            goto L62
        L41:
            r20 = r6;
        L44:
            if (SharedConfig.getDevicePerformanceClass() > 0) goto L46;
        L53:
            ImageReceiver r153 = this.loopImageView.getImageReceiver();
            ImageLocation r162 = ImageLocation.getForDocument(r142.select_animation);
            if (this.hasEnterAnimation == false) goto L56;
            SvgHelper.SvgDrawable r202 = null;
        L57:
            r153.setImage(r162, "60_60_firstframe", null, null, r202, 0, "tgs", this.currentReaction, 0);
            goto L59
        L56:
            r202 = r6;
            goto L57
        L46:
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 4) goto L53;
            this.enterImageView.getImageReceiver().setImage(ImageLocation.getForDocument(r142.appear_animation), "30_30_nolimit", null, null, r6, 0, "tgs", r27, 0);
            ImageReceiver r154 = this.loopImageView.getImageReceiver();
            ImageLocation r163 = ImageLocation.getForDocument(r142.select_animation);
            if (this.hasEnterAnimation == false) goto L51;
            SvgHelper.SvgDrawable r203 = null;
        L52:
            r154.setImage(r163, "60_60_firstframe", null, null, r203, 0, "tgs", this.currentReaction, 0);
            goto L59
        L51:
            r203 = r6;
        L62:
            PremiumLockIconView r13 = this.lockIconView;
            if (r13 == null) goto L68;
            r13.setImageReceiver(this.loopImageView.getImageReceiver());
            return;
        L68:
            return;
        }

        public void checkPlayLoopImage() {
            BackupImageView r0 = this.loopImageView;
            AnimatedEmojiDrawable r1 = r0.animatedEmojiDrawable;
            if (r1 == null) goto L5;
            ImageReceiver r02 = r1.getImageReceiver();
        L6:
            if (r02 != null) goto L8;
            return;
        L8:
            if (r02.getLottieAnimation() == null) goto L23;
            ReactionsContainerLayout r12 = this.this$0;
            if (r12.reactionsWindow == null) goto L12;
        L20:
            r02.getLottieAnimation().start();
            return;
        L12:
            if (this.pressed == true) goto L20;
            if (ReactionsContainerLayout.m12383$$Nest$fgetallReactionsIsDefault(r12) == false) goto L20;
            if (r02.getLottieAnimation().getCurrentFrame() > 2) goto L24;
            r02.getLottieAnimation().stop();
            return;
        L24:
            return;
        L23:
            return;
        L5:
            r02 = r0.imageReceiver;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            if (this.selected == true) goto L5;
        L13:
            AnimatedEmojiDrawable r0 = this.loopImageView.animatedEmojiDrawable;
            if (r0 != null) goto L16;
        L24:
            ReactionsLayoutInBubble.VisibleReaction r02 = this.currentReaction;
            if (r02 != null) goto L27;
        L41:
            super.dispatchDraw(r8);
            return;
        L27:
            if (r02.isStar == false) goto L41;
            if (this.particles == null) goto L41;
            if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == false) goto L41;
            if (LiteMode.isEnabled(131072) == false) goto L41;
            RectF r1 = AndroidUtilities.rectTmp;
            float r03 = ((int) (getHeight() * 0.7f)) / 2.0f;
            r1.set((getWidth() / 2.0f) - r03, (getHeight() / 2.0f) - r03, (getWidth() / 2.0f) + r03, (getHeight() / 2.0f) + r03);
            RLottieDrawable r04 = this.enterImageView.getImageReceiver().getLottieAnimation();
            StarsReactionsSheet.Particles r2 = this.particles;
            if (r04 != null) goto L37;
        L39:
            float r05 = 0.0f;
        L40:
            r2.setVisible(r05);
            this.particles.setBounds(r1);
            this.particles.process();
            this.particles.draw(r8, -673522);
            invalidate();
            goto L41
        L37:
            if (r04.getCurrentFrame() <= 30) goto L39;
            r05 = Utilities.clamp01((r04.getCurrentFrame() - 30) / 30.0f);
            goto L40
        L16:
            if (r0.getImageReceiver() == null) goto L24;
            int r06 = this.position;
            BackupImageView r12 = this.loopImageView;
            int r22 = 0;
            if (r06 != 0) goto L20;
            r12.animatedEmojiDrawable.getImageReceiver().setRoundRadius(AndroidUtilities.dp(6.0f), 0, 0, AndroidUtilities.dp(6.0f));
            goto L24
        L20:
            ImageReceiver r07 = r12.animatedEmojiDrawable.getImageReceiver();
            if (this.selected == false) goto L23;
            r22 = AndroidUtilities.dp(6.0f);
        L23:
            r07.setRoundRadius(r22);
            goto L24
        L5:
            if (this.drawSelected == false) goto L13;
            float r08 = getMeasuredWidth() >> 1;
            float r13 = getMeasuredHeight() >> 1;
            float r23 = (getMeasuredWidth() >> 1) - AndroidUtilities.dp(1.0f);
            ReactionsLayoutInBubble.VisibleReaction r3 = this.currentReaction;
            if (r3 != null) goto L9;
        L11:
            Paint r32 = ReactionsContainerLayout.m12401$$Nest$fgetselectedPaint(this.this$0);
        L12:
            r8.drawCircle(r08, r13, r23, r32);
            goto L13
        L9:
            if (r3.isStar == false) goto L11;
            r32 = ReactionsContainerLayout.m12402$$Nest$fgetstarSelectedPaint(this.this$0);
            goto L12
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            resetAnimation();
            this.preloadImageReceiver.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.preloadImageReceiver.onDetachedFromWindow();
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            ReactionsLayoutInBubble.VisibleReaction r1 = this.currentReaction;
            if (r1 == null) goto L10;
            String r12 = r1.emojicon;
            if (r12 == null) goto L8;
            r2.setText(r12);
            r2.setEnabled(true);
            return;
        L8:
            r2.setText(LocaleController.getString(R.string.AccDescrCustomEmoji));
            r2.setEnabled(true);
            return;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r9) {
            if (this.touchable == true) goto L5;
        L57:
            return false;
        L5:
            if (this.this$0.cancelPressedAnimation != null) goto L57;
            if (r9.getAction() != 0) goto L24;
            this.pressed = true;
            this.pressedX = r9.getX();
            this.pressedY = r9.getY();
            if (this.sideScale != 1.0f) goto L24;
            if (this.isLocked == true) goto L24;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 3) goto L24;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 4) goto L24;
            if (ReactionsContainerLayout.m12404$$Nest$fgettype(this.this$0) == 5) goto L24;
            if (ReactionsContainerLayout.m12389$$Nest$fgetdelegate(this.this$0) != null) goto L22;
        L23:
            AndroidUtilities.runOnUIThread(this.longPressRunnable, ViewConfiguration.getLongPressTimeout());
            goto L24
        L22:
            if (ReactionsContainerLayout.m12389$$Nest$fgetdelegate(this.this$0).allowLongPress() == true) goto L23;
        L24:
            float r0 = ViewConfiguration.get(getContext()).getScaledTouchSlop() * 2.0f;
            if (r9.getAction() != 2) goto L32;
            if (Math.abs(this.pressedX - r9.getX()) > r0) goto L36;
            if (Math.abs(this.pressedY - r9.getY()) <= r0) goto L32;
        L36:
            if (r9.getAction() != 1) goto L53;
            if (this.pressed == false) goto L53;
            if (ReactionsContainerLayout.m12398$$Nest$fgetpressedReaction(this.this$0) == null) goto L44;
            if (ReactionsContainerLayout.m12397$$Nest$fgetpressedProgress(this.this$0) <= 0.8f) goto L53;
        L44:
            if (ReactionsContainerLayout.m12389$$Nest$fgetdelegate(this.this$0) == null) goto L53;
            ReactionsContainerLayout.m12407$$Nest$fputclicked(this.this$0, true);
            long r4 = System.currentTimeMillis();
            ReactionsContainerLayout r92 = this.this$0;
            if ((r4 - r92.lastReactionSentTime) <= 300) goto L53;
            r92.lastReactionSentTime = System.currentTimeMillis();
            ReactionsContainerDelegate r93 = ReactionsContainerLayout.m12389$$Nest$fgetdelegate(this.this$0);
            ReactionsLayoutInBubble.VisibleReaction r2 = this.currentReaction;
            if (ReactionsContainerLayout.m12397$$Nest$fgetpressedProgress(this.this$0) <= 0.8f) goto L50;
            boolean r02 = true;
        L51:
            r93.onReactionClicked(this, r2, r02, false);
            goto L53
        L50:
            r02 = false;
        L53:
            if (ReactionsContainerLayout.m12386$$Nest$fgetclicked(this.this$0) == true) goto L55;
            ReactionsContainerLayout.m12416$$Nest$mcancelPressed(this.this$0);
        L55:
            AndroidUtilities.cancelRunOnUIThread(this.longPressRunnable);
            this.pressed = false;
        L56:
            return true;
        L32:
            if (r9.getAction() == 1) goto L36;
            if (r9.getAction() != 3) goto L56;
            goto L56
        }

        public boolean play(int r6) {
            float r1 = 0.76f;
            if (ReactionsContainerLayout.m12384$$Nest$fgetanimationEnabled(this.this$0) == true) goto L16;
            resetAnimation();
            this.isEnter = true;
            if (this.hasEnterAnimation == true) goto L15;
            this.loopImageView.setVisibility(0);
            BackupImageView r62 = this.loopImageView;
            float r0 = this.enterScale;
            if (this.selected == false) goto L9;
            float r3 = 0.76f;
        L10:
            r62.setScaleY(r0 * r3);
            BackupImageView r63 = this.loopImageView;
            float r02 = this.enterScale;
            if (this.selected == true) goto L14;
            r1 = 1.0f;
        L14:
            r63.setScaleX(r02 * r1);
            goto L15
        L9:
            r3 = 1.0f;
        L15:
            return false;
        L16:
            AndroidUtilities.cancelRunOnUIThread(this.playRunnable);
            if (this.hasEnterAnimation == false) goto L48;
            if (this.enterImageView.getImageReceiver().getLottieAnimation() == null) goto L30;
            if (this.enterImageView.getImageReceiver().getLottieAnimation().isGeneratingCache() == true) goto L30;
            if (this.isEnter == true) goto L30;
            this.isEnter = true;
            if (r6 != 0) goto L27;
            this.waitingAnimation = false;
            this.enterImageView.getImageReceiver().getLottieAnimation().stop();
            this.enterImageView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false);
            this.playRunnable.run();
        L28:
            return true;
        L27:
            this.waitingAnimation = true;
            this.enterImageView.getImageReceiver().getLottieAnimation().stop();
            this.enterImageView.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false);
            AndroidUtilities.runOnUIThread(this.playRunnable, r6);
        L30:
            if (this.enterImageView.getImageReceiver().getLottieAnimation() != null) goto L32;
        L38:
            BackupImageView r64 = this.loopImageView;
            float r03 = this.enterScale;
            if (this.selected == false) goto L41;
            float r32 = 0.76f;
        L42:
            r64.setScaleY(r03 * r32);
            BackupImageView r65 = this.loopImageView;
            float r04 = this.enterScale;
            if (this.selected == true) goto L46;
            r1 = 1.0f;
        L46:
            r65.setScaleX(r04 * r1);
        L50:
            return false;
        L41:
            r32 = 1.0f;
            goto L42
        L32:
            if (this.isEnter == false) goto L38;
            if (this.enterImageView.getImageReceiver().getLottieAnimation().isRunning() == true) goto L38;
            if (this.enterImageView.getImageReceiver().getLottieAnimation().isGeneratingCache() == true) goto L38;
            this.enterImageView.getImageReceiver().getLottieAnimation().setCurrentFrame(this.enterImageView.getImageReceiver().getLottieAnimation().getFramesCount() - 1, false);
            goto L38
        L48:
            if (this.isEnter == true) goto L50;
            this.enterScale = 0.0f;
            this.loopImageView.setScaleX(0.0f);
            this.loopImageView.setScaleY(0.0f);
            ValueAnimator r05 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.enterAnimator = r05;
            r05.addUpdateListener(new ReactionsContainerLayout$ReactionHolderView$$ExternalSyntheticLambda0(this));
            this.enterAnimator.setDuration(150);
            this.enterAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.enterAnimator.setStartDelay((long) (r6 * this.this$0.durationScale));
            this.enterAnimator.start();
            this.isEnter = true;
            goto L50
        }

        public void resetAnimation() {
            float r1 = 1.0f;
            if (this.hasEnterAnimation == false) goto L20;
            AndroidUtilities.cancelRunOnUIThread(this.playRunnable);
            if (this.enterImageView.getImageReceiver().getLottieAnimation() != null) goto L7;
        L12:
            this.loopImageView.setVisibility(4);
            this.enterImageView.setVisibility(0);
            this.switchedToLoopView = false;
            BackupImageView r0 = this.loopImageView;
            float r4 = this.enterScale;
            if (this.selected == false) goto L15;
            float r5 = 0.76f;
        L16:
            r0.setScaleY(r4 * r5);
            BackupImageView r02 = this.loopImageView;
            float r42 = this.enterScale;
            if (this.selected == false) goto L19;
            r1 = 0.76f;
        L19:
            r02.setScaleX(r42 * r1);
        L31:
            this.isEnter = false;
            return;
        L15:
            r5 = 1.0f;
            goto L16
        L7:
            if (this.enterImageView.getImageReceiver().getLottieAnimation().isGeneratingCache() == true) goto L12;
            this.enterImageView.getImageReceiver().getLottieAnimation().stop();
            boolean r03 = ReactionsContainerLayout.m12384$$Nest$fgetanimationEnabled(this.this$0);
            BackupImageView r43 = this.enterImageView;
            if (r03 == false) goto L11;
            r43.getImageReceiver().getLottieAnimation().setCurrentFrame(0, false, true);
            goto L12
        L11:
            r43.getImageReceiver().getLottieAnimation().setCurrentFrame(this.enterImageView.getImageReceiver().getLottieAnimation().getFramesCount() - 1, false, true);
            goto L12
        L20:
            this.loopImageView.animate().cancel();
            boolean r04 = this.this$0.skipEnterAnimation;
            BackupImageView r44 = this.loopImageView;
            if (r04 == false) goto L30;
            float r05 = this.enterScale;
            if (this.selected == false) goto L25;
            float r52 = 0.76f;
        L26:
            r44.setScaleY(r05 * r52);
            BackupImageView r06 = this.loopImageView;
            float r45 = this.enterScale;
            if (this.selected == false) goto L29;
            r1 = 0.76f;
        L29:
            r06.setScaleX(r45 * r1);
            goto L31
        L25:
            r52 = 1.0f;
            goto L26
        L30:
            r44.setScaleY(0.0f);
            this.loopImageView.setScaleX(0.0f);
            goto L31
        }

        public void updateSelected(ReactionsLayoutInBubble.VisibleReaction r7, boolean r8) {
            boolean r0 = this.selected;
            boolean r72 = this.this$0.selectedReactions.contains(r7);
            this.selected = r72;
            if (r72 == r0) goto L40;
            BackupImageView r02 = this.loopImageView;
            float r1 = 1.0f;
            if (r8 == true) goto L22;
            float r82 = this.enterScale;
            if (r72 == false) goto L9;
            float r73 = 0.76f;
        L10:
            r02.setScaleX(r82 * r73);
            BackupImageView r74 = this.loopImageView;
            float r83 = this.enterScale;
            if (this.selected == false) goto L13;
            float r03 = 0.76f;
        L14:
            r74.setScaleY(r83 * r03);
            BackupImageView r75 = this.enterImageView;
            float r84 = this.enterScale;
            if (this.selected == false) goto L17;
            float r04 = 0.76f;
        L18:
            r75.setScaleX(r84 * r04);
            BackupImageView r76 = this.enterImageView;
            float r85 = this.enterScale;
            if (this.selected == false) goto L21;
            r1 = 0.76f;
        L21:
            r76.setScaleY(r85 * r1);
        L38:
            requestLayout();
            invalidate();
            return;
        L17:
            r04 = 1.0f;
            goto L18
        L13:
            r03 = 1.0f;
            goto L14
        L9:
            r73 = 1.0f;
            goto L10
        L22:
            ViewPropertyAnimator r77 = r02.animate();
            float r86 = this.enterScale;
            if (this.selected == false) goto L25;
            float r05 = 0.76f;
        L26:
            ViewPropertyAnimator r78 = r77.scaleX(r86 * r05);
            float r87 = this.enterScale;
            if (this.selected == false) goto L29;
            float r06 = 0.76f;
        L30:
            ViewPropertyAnimator r79 = r78.scaleY(r87 * r06).setDuration(240);
            CubicBezierInterpolator r88 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r79.setInterpolator(r88).start();
            ViewPropertyAnimator r710 = this.enterImageView.animate();
            float r07 = this.enterScale;
            if (this.selected == false) goto L33;
            float r5 = 0.76f;
        L34:
            ViewPropertyAnimator r711 = r710.scaleX(r07 * r5);
            float r08 = this.enterScale;
            if (this.selected == false) goto L37;
            r1 = 0.76f;
        L37:
            r711.scaleY(r08 * r1).setDuration(240).setInterpolator(r88).start();
            goto L38
        L33:
            r5 = 1.0f;
            goto L34
        L29:
            r06 = 1.0f;
            goto L30
        L25:
            r05 = 1.0f;
            goto L26
        }
    }

    public interface ReactionsContainerDelegate {
        default boolean allowLongPress() {
            return true;
        }

        default boolean drawBackground() {
            return false;
        }

        default void drawRoundRect(Canvas r1, RectF r2, float r3, float r4, float r5, int r6, boolean r7) {
        }

        default boolean needEnterText() {
            return false;
        }

        default void onEmojiWindowDismissed() {
        }

        void onReactionClicked(View r1, ReactionsLayoutInBubble.VisibleReaction r2, boolean r3, boolean r4);
    }

    /* renamed from: $r8$lambda$-sDBCw5cNZ8A7xGChcTYEPdxBuk, reason: not valid java name */
    public static /* synthetic */ void m12381$r8$lambda$sDBCw5cNZ8A7xGChcTYEPdxBuk(ReactionsContainerLayout r0, ValueAnimator r1) {
        r0.lambda$animatePullingBack$2(r1);
    }

    /* renamed from: $r8$lambda$A1oUHdBxBMzfg-EE8nzWVkj1Fos, reason: not valid java name */
    public static /* synthetic */ void m12382$r8$lambda$A1oUHdBxBMzfgEE8nzWVkj1Fos(ReactionsContainerLayout r0) {
        r0.lambda$showCustomEmojiReactionDialog$3();
    }

    public static /* synthetic */ boolean $r8$lambda$AqNY6gloNhV3NNWV5BlEKMRrUeg(ReactionsContainerLayout r0, int r1, View r2, int r3) {
        return r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$KkELGQCRqc0nyLY9OQUor3II5VA(ReactionsContainerLayout r0, boolean r1, View r2) {
        r0.lambda$updateSelected$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XtZilo1Uq5sU_kQvu1ho3Z3wAkg(ReactionsContainerLayout r0, View r1) {
        r0.lambda$reset$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$mPnB7HiL1xkhUsZn7X_TS666hbw(ReactionsContainerLayout r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetallReactionsIsDefault, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12383$$Nest$fgetallReactionsIsDefault(ReactionsContainerLayout r0) {
        return r0.allReactionsIsDefault;
    }

    /* renamed from: -$$Nest$fgetanimationEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12384$$Nest$fgetanimationEnabled(ReactionsContainerLayout r0) {
        return r0.animationEnabled;
    }

    /* renamed from: -$$Nest$fgetcancelPressedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12385$$Nest$fgetcancelPressedProgress(ReactionsContainerLayout r0) {
        return r0.cancelPressedProgress;
    }

    /* renamed from: -$$Nest$fgetclicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12386$$Nest$fgetclicked(ReactionsContainerLayout r0) {
        return r0.clicked;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12387$$Nest$fgetcurrentAccount(ReactionsContainerLayout r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcustomEmojiReactionsIconView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InternalImageView m12388$$Nest$fgetcustomEmojiReactionsIconView(ReactionsContainerLayout r0) {
        return r0.customEmojiReactionsIconView;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerDelegate m12389$$Nest$fgetdelegate(ReactionsContainerLayout r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetleftAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12390$$Nest$fgetleftAlpha(ReactionsContainerLayout r0) {
        return r0.leftAlpha;
    }

    /* renamed from: -$$Nest$fgetleftShadowPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12391$$Nest$fgetleftShadowPaint(ReactionsContainerLayout r0) {
        return r0.leftShadowPaint;
    }

    /* renamed from: -$$Nest$fgetlinearLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m12392$$Nest$fgetlinearLayoutManager(ReactionsContainerLayout r0) {
        return r0.linearLayoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m12393$$Nest$fgetlistAdapter(ReactionsContainerLayout r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m12394$$Nest$fgetlocation(ReactionsContainerLayout r0) {
        return r0.location;
    }

    /* renamed from: -$$Nest$fgetonSwitchedToLoopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m12395$$Nest$fgetonSwitchedToLoopView(ReactionsContainerLayout r0) {
        return r0.onSwitchedToLoopView;
    }

    /* renamed from: -$$Nest$fgetpremiumLockIconView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PremiumLockIconView m12396$$Nest$fgetpremiumLockIconView(ReactionsContainerLayout r0) {
        return r0.premiumLockIconView;
    }

    /* renamed from: -$$Nest$fgetpressedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12397$$Nest$fgetpressedProgress(ReactionsContainerLayout r0) {
        return r0.pressedProgress;
    }

    /* renamed from: -$$Nest$fgetpressedReaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsLayoutInBubble.VisibleReaction m12398$$Nest$fgetpressedReaction(ReactionsContainerLayout r0) {
        return r0.pressedReaction;
    }

    /* renamed from: -$$Nest$fgetrightAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12399$$Nest$fgetrightAlpha(ReactionsContainerLayout r0) {
        return r0.rightAlpha;
    }

    /* renamed from: -$$Nest$fgetrightShadowPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12400$$Nest$fgetrightShadowPaint(ReactionsContainerLayout r0) {
        return r0.rightShadowPaint;
    }

    /* renamed from: -$$Nest$fgetselectedPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12401$$Nest$fgetselectedPaint(ReactionsContainerLayout r0) {
        return r0.selectedPaint;
    }

    /* renamed from: -$$Nest$fgetstarSelectedPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12402$$Nest$fgetstarSelectedPaint(ReactionsContainerLayout r0) {
        return r0.starSelectedPaint;
    }

    /* renamed from: -$$Nest$fgettransitionProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12403$$Nest$fgettransitionProgress(ReactionsContainerLayout r0) {
        return r0.transitionProgress;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12404$$Nest$fgettype(ReactionsContainerLayout r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fgetvisibleReactionsList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m12405$$Nest$fgetvisibleReactionsList(ReactionsContainerLayout r0) {
        return r0.visibleReactionsList;
    }

    /* renamed from: -$$Nest$fputcancelPressedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12406$$Nest$fputcancelPressedProgress(ReactionsContainerLayout r0, float r1) {
        r0.cancelPressedProgress = r1;
    }

    /* renamed from: -$$Nest$fputclicked, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12407$$Nest$fputclicked(ReactionsContainerLayout r0, boolean r1) {
        r0.clicked = r1;
    }

    /* renamed from: -$$Nest$fputcustomEmojiReactionsIconView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12408$$Nest$fputcustomEmojiReactionsIconView(ReactionsContainerLayout r0, InternalImageView r1) {
        r0.customEmojiReactionsIconView = r1;
    }

    /* renamed from: -$$Nest$fputleftAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12409$$Nest$fputleftAlpha(ReactionsContainerLayout r0, float r1) {
        r0.leftAlpha = r1;
    }

    /* renamed from: -$$Nest$fputpremiumLockIconView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12410$$Nest$fputpremiumLockIconView(ReactionsContainerLayout r0, PremiumLockIconView r1) {
        r0.premiumLockIconView = r1;
    }

    /* renamed from: -$$Nest$fputpressedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12411$$Nest$fputpressedProgress(ReactionsContainerLayout r0, float r1) {
        r0.pressedProgress = r1;
    }

    /* renamed from: -$$Nest$fputpressedReaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12412$$Nest$fputpressedReaction(ReactionsContainerLayout r0, ReactionsLayoutInBubble.VisibleReaction r1) {
        r0.pressedReaction = r1;
    }

    /* renamed from: -$$Nest$fputpressedReactionPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12413$$Nest$fputpressedReactionPosition(ReactionsContainerLayout r0, int r1) {
        r0.pressedReactionPosition = r1;
    }

    /* renamed from: -$$Nest$fputrightAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12414$$Nest$fputrightAlpha(ReactionsContainerLayout r0, float r1) {
        r0.rightAlpha = r1;
    }

    /* renamed from: -$$Nest$manimatePullingBack, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12415$$Nest$manimatePullingBack(ReactionsContainerLayout r0) {
        r0.animatePullingBack();
    }

    /* renamed from: -$$Nest$mcancelPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12416$$Nest$mcancelPressed(ReactionsContainerLayout r0) {
        r0.cancelPressed();
    }

    /* renamed from: -$$Nest$msetChildScale, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12417$$Nest$msetChildScale(ReactionsContainerLayout r0, View r1, float r2) {
        r0.setChildScale(r1, r2);
    }

    /* renamed from: -$$Nest$mshowCustomEmojiReactionDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12418$$Nest$mshowCustomEmojiReactionDialog(ReactionsContainerLayout r0) {
        r0.showCustomEmojiReactionDialog();
    }

    /* renamed from: -$$Nest$mshowUnlockPremium, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12419$$Nest$mshowUnlockPremium(ReactionsContainerLayout r0, float r1, float r2) {
        r0.showUnlockPremium(r1, r2);
    }

    /* renamed from: -$$Nest$mshowUnlockPremiumButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12420$$Nest$mshowUnlockPremiumButton(ReactionsContainerLayout r0) {
        return r0.showUnlockPremiumButton();
    }

    static {
        TRANSITION_PROGRESS_VALUE = new AnonymousClass1(Float.class, "transitionProgress");
    }

    public ReactionsContainerLayout(int r10, BaseFragment r11, Context r12, int r13, Theme.ResourcesProvider r14) {
        super(r12);
        this.forceAttachToParent = false;
        this.items = new ArrayList();
        this.oldItems = new ArrayList();
        boolean r2 = true;
        Paint r1 = new Paint(1);
        this.bgPaint = r1;
        this.leftShadowPaint = new Paint(1);
        this.rightShadowPaint = new Paint(1);
        this.transitionProgress = 1.0f;
        this.rect = new RectF();
        this.mPath = new Path();
        this.radius = AndroidUtilities.dp(72.0f);
        float r4 = AndroidUtilities.dp(8.0f);
        this.bigCircleRadius = r4;
        this.smallCircleRadius = r4 / 2.0f;
        this.bigCircleOffset = AndroidUtilities.dp(36.0f);
        this.visibleReactionsList = new ArrayList(20);
        this.premiumLockedReactions = new ArrayList(10);
        this.allReactionsList = new ArrayList(20);
        this.rectF = new RectF();
        this.hasStar = false;
        this.selectedReactions = new HashSet();
        this.alwaysSelectedReactions = new HashSet();
        this.location = new int[2];
        this.shadowPad = new Rect();
        this.triggeredReactions = new ArrayList();
        this.lastVisibleViews = new HashSet();
        this.lastVisibleViewsTmp = new HashSet();
        Paint r5 = new Paint(1);
        this.selectedPaint = r5;
        Paint r6 = new Paint(1);
        this.starSelectedPaint = r6;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.isHiddenNextReaction = true;
        this.paused = false;
        this.type = r10;
        this.durationScale = Settings.Global.getFloat(r12.getContentResolver(), "animator_duration_scale", 1.0f);
        r5.setColor(Theme.getColor(Theme.key_listSelector, r14));
        r6.setColor(Theme.getColor(Theme.key_reactionStarSelector, r14));
        this.resourcesProvider = r14;
        this.currentAccount = r13;
        this.fragment = r11;
        ReactionHolderView r112 = new ReactionHolderView(this, r12, false);
        this.nextRecentReaction = r112;
        r112.setVisibility(8);
        ReactionHolderView r113 = this.nextRecentReaction;
        r113.touchable = false;
        r113.pressedBackupImageView.setVisibility(8);
        addView(this.nextRecentReaction);
        if (SharedConfig.animationsEnabled() == true) goto L5;
    L7:
        r2 = false;
    L8:
        this.animationEnabled = r2;
        this.shadow = ContextCompat.getDrawable(r12, R.drawable.reactions_bubble_shadow).mutate();
        Rect r114 = this.shadowPad;
        int r22 = AndroidUtilities.dp(7.0f);
        r114.bottom = r22;
        r114.right = r22;
        r114.top = r22;
        r114.left = r22;
        this.shadow.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chat_messagePanelShadow), PorterDuff.Mode.MULTIPLY));
        AnonymousClass2 r115 = new AnonymousClass2(this, r12);
        this.recyclerListView = r115;
        r115.setClipChildren(false);
        r115.setClipToPadding(false);
        this.linearLayoutManager = new AnonymousClass3(this, r12, 0, false);
        r115.addItemDecoration(new AnonymousClass4(this));
        r115.setLayoutManager(this.linearLayoutManager);
        r115.setOverScrollMode(2);
        Adapter r122 = new Adapter(this);
        this.listAdapter = r122;
        r115.setAdapter(r122);
        r115.addOnScrollListener(new LeftRightShadowsListener(this, null));
        r115.addOnScrollListener(new AnonymousClass5(this));
        r115.addItemDecoration(new AnonymousClass6(this));
        r115.setOnItemClickListener(new ReactionsContainerLayout$$ExternalSyntheticLambda0(this));
        r115.setOnItemLongClickListener(new ReactionsContainerLayout$$ExternalSyntheticLambda1(this, r10));
        addView(r115, LayoutHelper.createFrame(-1, -1.0f));
        setClipChildren(false);
        setClipToPadding(false);
        invalidateShaders();
        int r123 = (r115.getLayoutParams().height - r115.getPaddingTop()) - r115.getPaddingBottom();
        this.nextRecentReaction.getLayoutParams().width = r123 - AndroidUtilities.dp(12.0f);
        this.nextRecentReaction.getLayoutParams().height = r123;
        if (r10 != 2) goto L11;
    L14:
        r1.setColor(ColorUtils.blendARGB(-16777216, -1, 0.13f));
    L15:
        MediaDataController.getInstance(r13).preloadDefaultReactions();
        return;
    L11:
        if (r10 == 4) goto L14;
        r1.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, r14));
        goto L15
    L5:
        if (SharedConfig.getDevicePerformanceClass() == 0) goto L7;
        goto L7
    }

    public static boolean allowSmoothEnterTransition() {
        return SharedConfig.deviceIsHigh();
    }

    private void animatePullingBack() {
        if (this.pullingLeftOffset == 0.0f) goto L9;
        ValueAnimator r0 = this.pullingDownBackAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.pullingLeftOffset, 0.0f});
        this.pullingDownBackAnimator = r02;
        r02.addUpdateListener(new ReactionsContainerLayout$$ExternalSyntheticLambda4(this));
        this.pullingDownBackAnimator.setDuration(150);
        this.pullingDownBackAnimator.start();
        return;
    }

    private void cancelPressed() {
        if (this.pressedReaction == null) goto L6;
        this.cancelPressedProgress = 0.0f;
        float r0 = this.pressedProgress;
        ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.cancelPressedAnimation = r1;
        r1.addUpdateListener(new AnonymousClass8(this, r0));
        this.cancelPressedAnimation.addListener(new AnonymousClass9(this));
        this.cancelPressedAnimation.setDuration(150);
        this.cancelPressedAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.cancelPressedAnimation.start();
        return;
    }

    private void checkPressedProgress(Canvas r11, ReactionHolderView r12) {
        if (this.pullingLeftOffset == 0.0f) goto L5;
        float r3 = (Utilities.clamp(r12.getLeft() / (getMeasuredWidth() - AndroidUtilities.dp(34.0f)), 1.0f, 0.0f) * getPullingLeftProgress()) * AndroidUtilities.dp(46.0f);
    L7:
        if (r12.currentReaction.equals(this.pressedReaction) == true) goto L9;
        int r112 = this.recyclerListView.getChildAdapterPosition(r12);
        float r0 = ((r12.getMeasuredWidth() * (this.pressedViewScale - 1.0f)) / 3.0f) - ((r12.getMeasuredWidth() * (1.0f - this.otherViewsScale)) * (Math.abs(this.pressedReactionPosition - r112) - 1));
        if (r112 >= this.pressedReactionPosition) goto L47;
        r12.setPivotX(0.0f);
        r12.setTranslationX(-r0);
    L48:
        r12.setPivotY(r12.enterImageView.getY() + r12.enterImageView.getMeasuredHeight());
        r12.setScaleX(this.otherViewsScale);
        r12.setScaleY(this.otherViewsScale);
        r12.pressedBackupImageView.setVisibility(4);
        r12.enterImageView.setAlpha(1.0f);
        return;
    L47:
        r12.setPivotX(r12.getMeasuredWidth() - r3);
        r12.setTranslationX(r0 - r3);
        goto L48
    L9:
        if (r12.loopImageView.getVisibility() != 0) goto L11;
        BackupImageView r02 = r12.loopImageView;
    L12:
        r12.setPivotX(r12.getMeasuredWidth() >> 1);
        r12.setPivotY(r02.getY() + r02.getMeasuredHeight());
        r12.setScaleX(this.pressedViewScale);
        r12.setScaleY(this.pressedViewScale);
        if (this.clicked == true) goto L31;
        ValueAnimator r5 = this.cancelPressedAnimation;
        BackupImageView r6 = r12.pressedBackupImageView;
        if (r5 != null) goto L25;
        r6.setVisibility(0);
        r12.pressedBackupImageView.setAlpha(1.0f);
        if (r12.pressedBackupImageView.getImageReceiver().hasBitmapImage() == true) goto L24;
        AnimatedEmojiDrawable r52 = r12.pressedBackupImageView.animatedEmojiDrawable;
        if (r52 == null) goto L27;
        if (r52.getImageReceiver() == null) goto L27;
        if (r12.pressedBackupImageView.animatedEmojiDrawable.getImageReceiver().hasBitmapImage() == true) goto L24;
    L27:
        if (this.pressedProgress != 1.0f) goto L31;
        this.clicked = true;
        if ((System.currentTimeMillis() - this.lastReactionSentTime) <= 300) goto L31;
        this.lastReactionSentTime = System.currentTimeMillis();
        this.delegate.onReactionClicked(r12, r12.currentReaction, true, false);
    L24:
        r02.setAlpha(0.0f);
        goto L27
    L25:
        r6.setAlpha(1.0f - this.cancelPressedProgress);
        r02.setAlpha(this.cancelPressedProgress);
    L31:
        r11.save();
        float r03 = this.recyclerListView.getX() + r12.getX();
        float r2 = ((r12.getMeasuredWidth() * r12.getScaleX()) - r12.getMeasuredWidth()) / 2.0f;
        float r4 = r03 - r2;
        if (r4 >= 0.0f) goto L37;
        if (r12.getTranslationX() < 0.0f) goto L37;
        r12.setTranslationX((-r4) - r3);
    L42:
        r11.translate(this.recyclerListView.getX() + r12.getX(), this.recyclerListView.getY() + r12.getY());
        r11.scale(r12.getScaleX(), r12.getScaleY(), r12.getPivotX(), r12.getPivotY());
        r12.draw(r11);
        r11.restore();
        return;
    L37:
        if (((r12.getMeasuredWidth() + r03) + r2) > getMeasuredWidth()) goto L39;
    L41:
        r12.setTranslationX(0.0f - r3);
        goto L42
    L39:
        if (r12.getTranslationX() > 0.0f) goto L41;
        r12.setTranslationX((((getMeasuredWidth() - r03) - r12.getMeasuredWidth()) - r2) - r3);
        goto L42
    L11:
        r02 = r12.enterImageView;
        goto L12
    L5:
        r3 = 0.0f;
        goto L7
    }

    private void checkPressedProgressForOtherViews(View r6) {
        int r0 = this.recyclerListView.getChildAdapterPosition(r6);
        float r1 = ((r6.getMeasuredWidth() * (this.pressedViewScale - 1.0f)) / 3.0f) - ((r6.getMeasuredWidth() * (1.0f - this.otherViewsScale)) * (Math.abs(this.pressedReactionPosition - r0) - 1));
        if (r0 >= this.pressedReactionPosition) goto L5;
        r6.setPivotX(0.0f);
        r6.setTranslationX(-r1);
    L6:
        r6.setScaleX(this.otherViewsScale);
        r6.setScaleY(this.otherViewsScale);
        return;
    L5:
        r6.setPivotX(r6.getMeasuredWidth());
        r6.setTranslationX(r1);
        goto L6
    }

    private void drawBubbles(Canvas r17, float r18, float r19, float r20, int r21) {
        if (this.type != 1) goto L5;
        return;
    L5:
        r17.save();
        if (this.isTop == false) goto L8;
        r17.clipRect(0.0f, 0.0f, getMeasuredWidth(), (AndroidUtilities.lerp(this.rect.top, getMeasuredHeight(), CubicBezierInterpolator.DEFAULT.getInterpolation(this.flipVerticalProgress)) - ((int) Math.ceil((this.rect.height() / 2.0f) * (1.0f - this.transitionProgress)))) + 1.0f);
    L10:
        if (LocaleController.isRTL == false) goto L12;
    L16:
        int r1 = this.bigCircleOffset;
    L17:
        float r12 = r1 + this.bubblesOffset;
        if (this.isTop == false) goto L20;
        float r3 = getPaddingTop() - expandSize();
    L21:
        int r4 = AndroidUtilities.dp(3.0f);
        this.shadow.setAlpha(r21);
        this.bgPaint.setAlpha(r21);
        float r6 = r12 - r18;
        float r10 = r4;
        float r42 = r10 * r19;
        float r11 = r3 - r18;
        float r13 = r12 + r18;
        float r15 = r3 + r18;
        this.shadow.setBounds((int) (r6 - r42), (int) (r11 - r42), (int) (r13 + r42), (int) (r42 + r15));
        this.shadow.draw(r17);
        if (this.delegate.drawBackground() == false) goto L24;
        this.rectF.set(r6, r11, r13, r15);
        float r43 = r18;
        this.delegate.drawRoundRect(r17, this.rectF, r43, getX(), getY(), r21, false);
    L29:
        if (LocaleController.isRTL == false) goto L31;
    L34:
        float r14 = this.bigCircleOffset - this.bigCircleRadius;
    L35:
        float r16 = r14 + (this.bubblesOffset + this.miniBubblesOffset);
        if (this.isTop == false) goto L38;
        float r32 = (getPaddingTop() - expandSize()) - AndroidUtilities.dp(16.0f);
    L39:
        float r33 = AndroidUtilities.lerp(r32, (this.smallCircleRadius + r10) - expandSize(), CubicBezierInterpolator.DEFAULT.getInterpolation(this.flipVerticalProgress));
        float r5 = (-AndroidUtilities.dp(1.0f)) * r19;
        this.shadow.setBounds((int) ((r16 - r43) - r5), (int) ((r33 - r43) - r5), (int) ((r16 + r43) + r5), (int) ((r43 + r33) + r5));
        this.shadow.draw(r17);
        if (this.delegate.drawBackground() == false) goto L43;
        this.rectF.set(r16 - r20, r33 - r20, r16 + r20, r33 + r20);
        this.delegate.drawRoundRect(r17, this.rectF, r20, getX(), getY(), r21, false);
    L46:
        r17.restore();
        this.shadow.setAlpha(255);
        this.bgPaint.setAlpha(255);
        return;
    L43:
        if (this.glassBackgroundBubbleSmall == null) goto L45;
        this.rectF.set(r16 - r20, r33 - r20, r16 + r20, r33 + r20);
        GlassMenuHelper.draw(this.glassBackgroundBubbleSmall, r17, this.rectF, r20, r21);
        goto L46
    L45:
        r17.drawCircle(r16, r33, r20, this.bgPaint);
        goto L46
    L38:
        r32 = ((getHeight() - this.smallCircleRadius) - r10) + expandSize();
        goto L39
    L31:
        if (this.mirrorX == true) goto L34;
        r14 = (getWidth() - this.bigCircleOffset) + this.bigCircleRadius;
        goto L35
    L24:
        r43 = r18;
        if (this.glassBackgroundBubbleBig == null) goto L27;
        this.rectF.set(r6, r11, r13, r15);
        GlassMenuHelper.draw(this.glassBackgroundBubbleBig, r17, this.rectF, r43, r21);
        goto L29
    L27:
        r17.drawCircle(r12, r3, r43, this.bgPaint);
        goto L29
    L20:
        r3 = (getHeight() - getPaddingBottom()) + expandSize();
        goto L21
    L12:
        if (this.mirrorX == true) goto L16;
        r1 = getWidth() - this.bigCircleOffset;
        goto L17
    L8:
        float r110 = this.rect.bottom;
        CubicBezierInterpolator r52 = CubicBezierInterpolator.DEFAULT;
        r17.clipRect(0.0f, (AndroidUtilities.lerp(r110, 0.0f, r52.getInterpolation(this.flipVerticalProgress)) - ((int) Math.ceil((this.rect.height() / 2.0f) * (1.0f - this.transitionProgress)))) - 1.0f, getMeasuredWidth(), AndroidUtilities.lerp(getMeasuredHeight() + AndroidUtilities.dp(8.0f), getPaddingTop() - expandSize(), r52.getInterpolation(this.flipVerticalProgress)));
        goto L10
    }

    private void fillRecentReactionsList(List<ReactionsLayoutInBubble.VisibleReaction> r11) {
        HashSet r0 = new HashSet();
        int r1 = this.type;
        int r2 = 0;
        if (r1 != 4) goto L22;
        Iterator<ReactionsLayoutInBubble.VisibleReaction> r12 = this.selectedReactions.iterator();
        int r3 = 0;
    L6:
        if (r12.hasNext() == false) goto L12;
        ReactionsLayoutInBubble.VisibleReaction r4 = r12.next();
        if (r0.contains(r4) == true) goto L6;
        r0.add(r4);
        r11.add(r4);
        r3 = r3 + 1;
        if (r3 < 8) goto L6;
        return;
    L12:
        List<TLRPC.TL_availableReaction> r10 = MediaDataController.getInstance(this.currentAccount).getEnabledReactionsList();
    L14:
        if (r2 >= r10.size()) goto L140;
        ReactionsLayoutInBubble.VisibleReaction r13 = ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r10.get(r2));
        if (r0.contains(r13) == true) goto L20;
        r0.add(r13);
        r11.add(r13);
        r3 = r3 + 1;
        if (r3 < 8) goto L20;
        return;
    L20:
        r2 = r2 + 1;
        goto L14
    L140:
        return;
    L22:
        if (this.allReactionsAvailable == false) goto L88;
        if (r1 == 4) goto L88;
        if (r1 != 5) goto L35;
        TLRPC.messages_AvailableEffects r102 = MessagesController.getInstance(this.currentAccount).getAvailableEffects();
        if (r102 != null) goto L30;
        return;
    L30:
        if (r2 >= r102.effects.size()) goto L141;
        ReactionsLayoutInBubble.VisibleReaction r14 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r102.effects.get(r2));
        if (r0.contains(r14) == true) goto L34;
        r0.add(r14);
        r11.add(r14);
    L34:
        r2 = r2 + 1;
        goto L30
    L141:
        return;
    L35:
        int r32 = this.currentAccount;
        if (r1 != 3) goto L38;
        ArrayList<TLRPC.Reaction> r15 = MediaDataController.getInstance(r32).getSavedReactions();
    L40:
        if (this.type != 3) goto L57;
        TLRPC.TL_messages_savedReactionsTags r33 = MessagesController.getInstance(this.currentAccount).getSavedReactionTags(0);
        if (r33 == null) goto L50;
        int r42 = 0;
    L45:
        if (r42 >= r33.tags.size()) goto L50;
        ReactionsLayoutInBubble.VisibleReaction r6 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r33.tags.get(r42).reaction);
        if (r0.contains(r6) == true) goto L49;
        r0.add(r6);
        r11.add(r6);
    L49:
        r42 = r42 + 1;
    L50:
        int r34 = 0;
    L52:
        if (r34 >= r15.size()) goto L71;
        ReactionsLayoutInBubble.VisibleReaction r43 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r15.get(r34));
        if (r0.contains(r43) == true) goto L56;
        r0.add(r43);
        r11.add(r43);
    L56:
        r34 = r34 + 1;
    L71:
        if (this.type == 3) goto L73;
    L74:
        ArrayList<TLRPC.Reaction> r16 = MediaDataController.getInstance(this.currentAccount).getRecentReactions();
        int r35 = 0;
    L76:
        if (r35 >= r16.size()) goto L81;
        ReactionsLayoutInBubble.VisibleReaction r44 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r16.get(r35));
        if (r0.contains(r44) == true) goto L80;
        r0.add(r44);
        r11.add(r44);
    L80:
        r35 = r35 + 1;
        goto L76
    L81:
        List<TLRPC.TL_availableReaction> r103 = MediaDataController.getInstance(this.currentAccount).getEnabledReactionsList();
    L83:
        if (r2 >= r103.size()) goto L142;
        ReactionsLayoutInBubble.VisibleReaction r17 = ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r103.get(r2));
        if (r0.contains(r17) == true) goto L87;
        r0.add(r17);
        r11.add(r17);
    L87:
        r2 = r2 + 1;
        goto L83
    L142:
        return;
    L73:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L74;
        return;
    L57:
        int r36 = 0;
    L59:
        if (r36 >= r15.size()) goto L71;
        ReactionsLayoutInBubble.VisibleReaction r45 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r15.get(r36));
        if (r0.contains(r45) == true) goto L69;
        if (this.type != 3) goto L65;
    L68:
        r0.add(r45);
        r11.add(r45);
        goto L69
    L65:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L68;
        if (r45.documentId == 0) goto L68;
    L69:
        r36 = r36 + 1;
        goto L59
    L38:
        r15 = MediaDataController.getInstance(r32).getTopReactions();
    L88:
        int r104 = this.currentAccount;
        if (r1 != 3) goto L100;
        ArrayList<TLRPC.Reaction> r105 = MediaDataController.getInstance(r104).getSavedReactions();
        int r18 = 0;
    L92:
        if (r2 >= r105.size()) goto L144;
        ReactionsLayoutInBubble.VisibleReaction r37 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r105.get(r2));
        if (r0.contains(r37) == true) goto L97;
        r0.add(r37);
        r11.add(r37);
        r18 = r18 + 1;
    L97:
        if (r18 == 16) goto L143;
        r2 = r2 + 1;
        goto L92
    L143:
        return;
    L144:
        return;
    L100:
        List<TLRPC.TL_availableReaction> r106 = MediaDataController.getInstance(r104).getEnabledReactionsList();
    L102:
        if (r2 >= r106.size()) goto L145;
        r11.add(ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r106.get(r2)));
        r2 = r2 + 1;
        goto L102
    }

    private void filterReactions(List<ReactionsLayoutInBubble.VisibleReaction> r3) {
        HashSet r2 = new HashSet();
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L10;
        if (r2.contains(r3.get(r0)) == false) goto L8;
        r0 = r0 - 1;
        r3.remove(r0);
    L9:
        r0 = r0 + 1;
        goto L4
    L8:
        r2.add(r3.get(r0));
        goto L9
    }

    public static HashSet<ReactionsLayoutInBubble.VisibleReaction> getInclusiveReactions(ArrayList<MessageObject> r11) {
        LongSparseArray r0 = new LongSparseArray();
        HashSet r1 = new HashSet();
        int r3 = 0;
        boolean r5 = true;
        int r4 = 0;
    L4:
        if (r4 >= r11.size()) goto L30;
        MessageObject r6 = r11.get(r4);
        r1.clear();
        if (r6 == null) goto L22;
        TLRPC.TL_messageReactions r7 = r6.messageOwner.reactions;
        if (r7 == null) goto L22;
        if (r7.results == null) goto L22;
        int r72 = 0;
    L13:
        if (r72 >= r6.messageOwner.reactions.results.size()) goto L22;
        if (r6.messageOwner.reactions.results.get(r72).chosen == false) goto L21;
        ReactionsLayoutInBubble.VisibleReaction r8 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r6.messageOwner.reactions.results.get(r72).reaction);
        if (r5 == false) goto L19;
    L20:
        r1.add(Long.valueOf(r8.hash));
        r0.put(r8.hash, r8);
        goto L21
    L19:
        if (r0.indexOfKey(r8.hash) >= 0) goto L20;
    L21:
        r72 = r72 + 1;
    L22:
        int r52 = 0;
    L24:
        if (r52 >= r0.size()) goto L29;
        if (r1.contains(Long.valueOf(r0.keyAt(r52))) == true) goto L28;
        r0.removeAt(r52);
        r52 = r52 - 1;
    L28:
        r52 = r52 + 1;
        goto L24
    L29:
        r4 = r4 + 1;
        r5 = false;
        goto L4
    L30:
        HashSet<ReactionsLayoutInBubble.VisibleReaction> r112 = new HashSet();
    L32:
        if (r3 >= r0.size()) goto L37;
        if (r0.valueAt(r3) == null) goto L36;
        r112.add((ReactionsLayoutInBubble.VisibleReaction) r0.valueAt(r3));
    L36:
        r3 = r3 + 1;
        goto L32
    L37:
        return r112;
    }

    private Paint getStarGradientPaint(RectF r10, float r11) {
        if (this.starSelectedGradientPaint != null) goto L6;
        this.starSelectedGradientPaint = new Paint(1);
    L6:
        if (this.starSelectedGradientMatrix != null) goto L9;
        this.starSelectedGradientMatrix = new Matrix();
    L9:
        if (this.starSelectedGradient != null) goto L11;
        int r0 = Theme.getColor(Theme.key_reactionStarSelector, this.resourcesProvider);
        LinearGradient r1 = new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(64.0f), 0.0f, new int[]{r0, Theme.multAlpha(r0, 0.0f)}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.starSelectedGradient = r1;
        this.starSelectedGradientPaint.setShader(r1);
    L11:
        this.starSelectedGradientMatrix.reset();
        this.starSelectedGradientMatrix.postTranslate(r10.left, r10.top);
        this.starSelectedGradient.setLocalMatrix(this.starSelectedGradientMatrix);
        this.starSelectedGradientPaint.setAlpha((int) (r11 * 255.0f));
        return this.starSelectedGradientPaint;
    }

    private void invalidateEmojis() {
        if (this.type != 4) goto L13;
        invalidate();
        this.recyclerListView.invalidate();
        this.recyclerListView.invalidateViews();
        int r0 = 0;
    L7:
        if (r0 >= this.recyclerListView.getChildCount()) goto L17;
        View r1 = this.recyclerListView.getChildAt(r0);
        if ((r1 instanceof ReactionHolderView) == false) goto L11;
        ReactionHolderView r12 = (ReactionHolderView) r1;
        r12.enterImageView.invalidate();
        r12.loopImageView.invalidate();
    L12:
        r0 = r0 + 1;
        goto L7
    L11:
        r1.invalidate();
        goto L12
    L17:
        return;
    }

    private void invalidateShaders() {
        int r0 = AndroidUtilities.dp(24.0f);
        float r5 = getHeight() / 2.0f;
        int r8 = Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground);
        Paint r1 = this.leftShadowPaint;
        Shader.TileMode r10 = Shader.TileMode.CLAMP;
        r1.setShader(new LinearGradient(0.0f, r5, r0, r5, r8, 0, r10));
        this.rightShadowPaint.setShader(new LinearGradient(getWidth(), r5, getWidth() - r0, r5, r8, 0, r10));
        invalidate();
    }

    private /* synthetic */ void lambda$animatePullingBack$2(ValueAnimator r1) {
        this.pullingLeftOffset = ((Float) this.pullingDownBackAnimator.getAnimatedValue()).floatValue();
        FrameLayout r12 = this.customReactionsContainer;
        if (r12 == null) goto L5;
        r12.invalidate();
    L5:
        invalidate();
    }

    private /* synthetic */ void lambda$new$0(View r2, int r3) {
        ReactionsContainerDelegate r32 = this.delegate;
        if (r32 != null) goto L5;
        return;
    L5:
        if ((r2 instanceof ReactionHolderView) == false) goto L9;
        r32.onReactionClicked(this, ((ReactionHolderView) r2).currentReaction, false, false);
        return;
    }

    private /* synthetic */ boolean lambda$new$1(int r2, View r3, int r4) {
        if (r2 != 5) goto L5;
        return false;
    L5:
        ReactionsContainerDelegate r22 = this.delegate;
        if (r22 != null) goto L8;
    L11:
        return false;
    L8:
        if ((r3 instanceof ReactionHolderView) == false) goto L11;
        r22.onReactionClicked(this, ((ReactionHolderView) r3).currentReaction, true, false);
        return true;
    }

    private /* synthetic */ void lambda$reset$5(View r5) {
        if ((r5 instanceof ReactionHolderView) == false) goto L17;
        ReactionHolderView r52 = (ReactionHolderView) r5;
        r52.pressed = false;
        float r1 = 1.0f;
        r52.loopImageView.setAlpha(1.0f);
        if (this.skipEnterAnimation == false) goto L15;
        BackupImageView r4 = r52.loopImageView;
        float r0 = r52.enterScale;
        if (r52.selected == false) goto L9;
        float r2 = 0.76f;
    L10:
        r4.setScaleX(r0 * r2);
        BackupImageView r42 = r52.loopImageView;
        float r02 = r52.enterScale;
        if (r52.selected == false) goto L13;
        r1 = 0.76f;
    L13:
        r42.setScaleY(r02 * r1);
        return;
    L9:
        r2 = 1.0f;
        goto L10
    L15:
        r52.resetAnimation();
        return;
    }

    private /* synthetic */ void lambda$showCustomEmojiReactionDialog$3() {
        this.reactionsWindow = null;
        invalidateLoopViews();
        ReactionsContainerDelegate r1 = this.delegate;
        if (r1 == null) goto L6;
        r1.onEmojiWindowDismissed();
        return;
    }

    private /* synthetic */ void lambda$updateSelected$4(boolean r3, View r4) {
        int r0 = this.recyclerListView.getChildAdapterPosition(r4);
        if (r0 >= 0) goto L5;
        return;
    L5:
        if (r0 < this.items.size()) goto L8;
        return;
    L8:
        if ((r4 instanceof ReactionHolderView) == false) goto L12;
        ((ReactionHolderView) r4).updateSelected(this.items.get(r0).reaction, r3);
        return;
    }

    private void setChildScale(View r1, float r2) {
        if ((r1 instanceof ReactionHolderView) == false) goto L6;
        ((ReactionHolderView) r1).sideScale = r2;
        return;
    L6:
        r1.setScaleX(r2);
        r1.setScaleY(r2);
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void setVisibleReactionsList(List<ReactionsLayoutInBubble.VisibleReaction> r7, boolean r8) {
        this.visibleReactionsList.clear();
        if (showCustomEmojiReaction() == false) goto L16;
        int r0 = (AndroidUtilities.displaySize.x - AndroidUtilities.dp(36.0f)) / AndroidUtilities.dp(34.0f);
        if (r0 <= 7) goto L7;
        r0 = 7;
    L7:
        int r3 = 0;
        if (r0 >= 1) goto L11;
        r0 = 1;
    L11:
        if (r3 >= Math.min(r7.size(), r0)) goto L14;
        this.visibleReactionsList.add(r7.get(r3));
        r3 = r3 + 1;
        goto L11
    L14:
        if (r3 >= r7.size()) goto L17;
        ReactionHolderView.m12423$$Nest$msetReaction(this.nextRecentReaction, r7.get(r3), -1);
    L17:
        this.allReactionsIsDefault = true;
        int r02 = 0;
    L19:
        if (r02 >= this.visibleReactionsList.size()) goto L24;
        if (this.visibleReactionsList.get(r02).documentId == 0) goto L23;
        this.allReactionsIsDefault = false;
    L23:
        r02 = r02 + 1;
        goto L19
    L24:
        this.allReactionsList.clear();
        this.allReactionsList.addAll(r7);
        if (((((getLayoutParams().height - ((int) getTopOffset())) - getPaddingTop()) - getPaddingBottom()) * r7.size()) >= AndroidUtilities.dp(200.0f)) goto L27;
        getLayoutParams().width = -2;
    L27:
        this.listAdapter.updateItems(r8);
        return;
    L16:
        this.visibleReactionsList.addAll(r7);
        goto L17
    }

    private void showCustomEmojiReactionDialog() {
        if (this.reactionsWindow == null) goto L5;
        return;
    L5:
        CustomEmojiReactionsWindow r1 = new CustomEmojiReactionsWindow(this.type, this.fragment, this.allReactionsList, this.selectedReactions, this, this.resourcesProvider, this.forceAttachToParent);
        this.reactionsWindow = r1;
        BlurredBackgroundDrawableViewFactory r9 = this.glassBackgroundFactory;
        boolean r0 = true;
        if (r9 == null) goto L8;
        r1.setGlassBackground(r9.create(r1.containerView, true).setColorProvider(this.glassBackgroundColorProvider), this.glassBackgroundFactory.create(this.reactionsWindow.containerView, true).setColorProvider(this.glassBackgroundColorProvider));
    L8:
        CustomEmojiReactionsWindow r92 = this.reactionsWindow;
        ReactionsContainerDelegate r12 = this.delegate;
        if (r12 != null) goto L11;
    L14:
        r92.setLongPressEnabled(r0);
        invalidateLoopViews();
        this.reactionsWindow.onDismissListener(new ReactionsContainerLayout$$ExternalSyntheticLambda5(this));
        onShownCustomEmojiReactionDialog();
        return;
    L11:
        if (r12.allowLongPress() == true) goto L14;
        r0 = false;
        goto L14
    }

    private void showUnlockPremium(float r2, float r3) {
        new PremiumFeatureBottomSheet(this.fragment, 4, true).show();
    }

    private boolean showUnlockPremiumButton() {
        if (this.premiumLockedReactions.isEmpty() == false) goto L5;
        return false;
    L5:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == true) goto L10;
        return true;
    L10:
        return false;
    }

    private void updateSelected(boolean r3) {
        AndroidUtilities.forEachViews(this.recyclerListView, new ReactionsContainerLayout$$ExternalSyntheticLambda2(this, r3));
    }

    public void clearRecentReactions() {
        MediaDataController.getInstance(this.currentAccount).clearRecentReactions();
        ArrayList r0 = new ArrayList();
        fillRecentReactionsList(r0);
        setVisibleReactionsList(r0, true);
        this.lastVisibleViews.clear();
        this.reactionsWindow.setRecentReactions(r0);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r7, int r8, Object... r9) {
        if (r7 != NotificationCenter.chatInfoDidLoad) goto L13;
        TLRPC.ChatFull r82 = (TLRPC.ChatFull) r9[0];
        if (r82.id == this.waitingLoadingChatId) goto L7;
        return;
    L7:
        if (getVisibility() != 0) goto L9;
        return;
    L9:
        if ((r82.available_reactions instanceof TLRPC.TL_chatReactionsNone) == true) goto L22;
        setMessage(this.messageObject, null, true);
        setVisibility(0);
        startEnterAnimation(false);
        return;
    L22:
        return;
    L13:
        if (r7 != NotificationCenter.emojiLoaded) goto L17;
        invalidateEmojis();
        return;
    L17:
        if (r7 != NotificationCenter.availableEffectsUpdate) goto L23;
        setMessage(this.messageObject, null, true);
        return;
    }

    public void dismissParent(boolean r2) {
        CustomEmojiReactionsWindow r0 = this.reactionsWindow;
        if (r0 == null) goto L6;
        r0.dismiss(r2);
        this.reactionsWindow = null;
        return;
    }

    public void dismissWindow() {
        CustomEmojiReactionsWindow r0 = this.reactionsWindow;
        if (r0 == null) goto L6;
        r0.dismiss();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r24) {
        Canvas r1 = r24;
        long r2 = Math.min(16, System.currentTimeMillis() - this.lastUpdate);
        this.lastUpdate = System.currentTimeMillis();
        boolean r4 = this.isFlippedVertically;
        if (r4 == false) goto L7;
        float r6 = this.flipVerticalProgress;
        if (r6 == 1.0f) goto L7;
        this.flipVerticalProgress = Math.min(1.0f, r6 + (r2 / 220.0f));
        invalidate();
    L11:
        TextView r22 = this.hintView;
        if (r22 == null) goto L14;
        r22.setTranslationY(-expandSize());
    L14:
        float r11 = (Math.max(0.25f, Math.min(this.transitionProgress, 1.0f)) - 0.25f) / 0.75f;
        float r12 = this.bigCircleRadius * r11;
        float r13 = this.smallCircleRadius * r11;
        this.lastVisibleViewsTmp.clear();
        this.lastVisibleViewsTmp.addAll(this.lastVisibleViews);
        this.lastVisibleViews.clear();
        if (this.prepareAnimation == false) goto L18;
        invalidate();
    L18:
        if (this.pressedReaction != null) goto L20;
    L35:
        ReactionsLayoutInBubble.VisibleReaction r23 = this.pressedReaction;
        if (r23 != null) goto L38;
    L40:
        float r25 = this.pressedProgress;
        this.pressedViewScale = (r25 * 2.0f) + 1.0f;
        this.otherViewsScale = 1.0f - (r25 * 0.15f);
    L41:
        int r26 = r1.save();
        if (LocaleController.isRTL == false) goto L44;
    L48:
        float r42 = getWidth();
        float r5 = 0.125f;
    L47:
        float r43 = r42 * r5;
        float r52 = this.transitionProgress;
        if (r52 == 1.0f) goto L53;
        r1.scale(r52, r52, r43, getHeight() / 2.0f);
    L53:
        if (LocaleController.isRTL == false) goto L55;
    L58:
        float r53 = Math.max(0.25f, this.transitionProgress);
        float r3 = 0.0f;
    L59:
        float r16 = getPullingLeftProgress();
        float r62 = expandSize();
        ChatScrimPopupContainerLayout r7 = this.chatScrimPopupContainerLayout;
        if (r7 == null) goto L62;
        r7.setExpandSize(r62);
    L62:
        float r72 = (getWidth() - getPaddingRight()) * Math.min(1.0f, r3);
        float r8 = getTopOffset();
        float r20 = r3;
        this.rect.set(getPaddingLeft() + r72, (getPaddingTop() + (this.recyclerListView.getMeasuredHeight() * (1.0f - this.otherViewsScale))) - r62, (getWidth() - getPaddingRight()) * r53, (getHeight() - getPaddingBottom()) + r62);
        this.radius = ((this.rect.height() - r8) - (r62 * 2.0f)) / 2.0f;
        int r10 = 1;
        if (this.type == 1) goto L65;
        this.shadow.setAlpha((int) (Utilities.clamp(1.0f - (this.customEmojiReactionsEnterProgress / 0.05f), 1.0f, 0.0f) * 255.0f));
        Drawable r32 = this.shadow;
        float r82 = getPaddingLeft();
        int r14 = getWidth() - getPaddingRight();
        Rect r15 = this.shadowPad;
        float r21 = 255.0f;
        int r63 = (int) r62;
        r32.setBounds((int) ((r82 + ((r14 + r15.right) * r20)) - r15.left), (getPaddingTop() - this.shadowPad.top) - r63, (int) (((getWidth() - getPaddingRight()) + this.shadowPad.right) * r53), ((getHeight() - getPaddingBottom()) + this.shadowPad.bottom) + r63);
        this.shadow.draw(r1);
    L66:
        r1.restoreToCount(r26);
        if (this.skipDraw == true) goto L92;
        int r9 = r1.save();
        float r27 = this.transitionProgress;
        if (r27 == 1.0f) goto L72;
        r1.scale(r27, r27, r43, getHeight() / 2.0f);
    L72:
        if (this.type != 1) goto L74;
    L81:
        float r152 = r72;
        float r142 = r43;
        this.delegate.drawRoundRect(r24, this.rect, this.radius, getX(), getY(), 255, false);
        r1 = r24;
    L83:
        if (this.hasStar == false) goto L91;
        Iterator<ReactionsLayoutInBubble.VisibleReaction> r28 = this.selectedReactions.iterator();
    L86:
        if (r28.hasNext() == false) goto L90;
        if (r28.next().isStar == false) goto L86;
    L90:
        RectF r29 = this.rect;
        float r33 = this.radius;
        r1.drawRoundRect(r29, r33, r33, getStarGradientPaint(r29, Utilities.clamp01(1.0f - getPullingLeftProgress())));
    L91:
        r1.restoreToCount(r9);
    L93:
        this.mPath.rewind();
        Path r210 = this.mPath;
        RectF r34 = this.rect;
        float r44 = this.radius;
        r210.addRoundRect(r34, r44, r44, Path.Direction.CW);
        int r211 = r1.save();
        float r35 = this.transitionProgress;
        if (r35 == 1.0f) goto L97;
        r1.scale(r35, r35, r142, getHeight() / 2.0f);
    L97:
        if (this.transitionProgress == 0.0f) goto L194;
        if (getAlpha() != 1.0f) goto L101;
    L102:
        int r45 = 0;
        int r54 = 0;
        int r64 = 0;
    L104:
        if (r45 >= this.recyclerListView.getChildCount()) goto L171;
        View r73 = this.recyclerListView.getChildAt(r45);
        if (this.transitionProgress == 1.0f) goto L111;
        if (allowSmoothEnterTransition() == false) goto L111;
        r64 = (int) (Math.abs(((r73.getLeft() + (r73.getMeasuredWidth() / 2.0f)) / this.recyclerListView.getMeasuredWidth()) - 0.8f) * 200.0f);
    L111:
        if ((r73 instanceof ReactionHolderView) == false) goto L134;
        ReactionHolderView r83 = (ReactionHolderView) this.recyclerListView.getChildAt(r45);
        checkPressedProgress(r1, r83);
        if (r73.getLeft() <= r54) goto L116;
        r54 = r73.getLeft();
    L116:
        if (this.skipEnterAnimation == true) goto L169;
        if (r83.hasEnterAnimation == false) goto L123;
        if (r83.enterImageView.getImageReceiver().getLottieAnimation() == null) goto L169;
    L123:
        if ((r83.getX() + (r83.getMeasuredWidth() / 2.0f)) <= 0.0f) goto L131;
        if ((r83.getX() + (r83.getMeasuredWidth() / 2.0f)) >= this.recyclerListView.getWidth()) goto L131;
        if (this.lastVisibleViewsTmp.contains(r83) == true) goto L129;
        r83.play(r64);
        r64 = r64 + 30;
    L129:
        this.lastVisibleViews.add(r83);
    L131:
        if (ReactionHolderView.m12422$$Nest$fgetisEnter(r83) == true) goto L169;
        r83.resetAnimation();
    L169:
        r45 = r45 + 1;
        goto L104
    L134:
        if (r73 != this.premiumLockContainer) goto L148;
        if ((r73.getX() + (r73.getMeasuredWidth() / 2.0f)) > 0.0f) goto L138;
    L146:
        this.premiumLockIconView.resetAnimation();
        goto L148
    L138:
        if ((r73.getX() + (r73.getMeasuredWidth() / 2.0f)) >= this.recyclerListView.getWidth()) goto L146;
        if (this.lastVisibleViewsTmp.contains(r73) == false) goto L142;
    L145:
        this.lastVisibleViews.add(r73);
        goto L148
    L142:
        if (this.transitionProgress == 1.0f) goto L144;
        this.premiumLockIconView.resetAnimation();
    L144:
        this.premiumLockIconView.play(r64);
        r64 = r64 + 30;
    L148:
        if (r73 == this.customReactionsContainer) goto L150;
    L168:
        checkPressedProgressForOtherViews(r73);
        goto L169
    L150:
        if ((r73.getX() + (r73.getMeasuredWidth() / 2.0f)) > 0.0f) goto L152;
    L167:
        this.customEmojiReactionsIconView.resetAnimation();
        goto L168
    L152:
        if ((r73.getX() + (r73.getMeasuredWidth() / 2.0f)) >= this.recyclerListView.getWidth()) goto L167;
        if (this.lastVisibleViewsTmp.contains(r73) == false) goto L156;
    L166:
        this.lastVisibleViews.add(r73);
        goto L168
    L156:
        if (this.transitionProgress == 1.0f) goto L158;
        this.customEmojiReactionsIconView.resetAnimation();
    L158:
        InternalImageView r84 = this.customEmojiReactionsIconView;
        if (LiteMode.isEnabled(LiteMode.FLAG_ANIMATED_EMOJI_REACTIONS) == false) goto L161;
    L164:
        boolean r92 = true;
    L165:
        r84.play(r64, r92);
        r64 = r64 + 30;
        goto L166
    L161:
        if (SharedConfig.getDevicePerformanceClass() >= 1) goto L164;
        r92 = false;
        goto L165
    L171:
        if (r16 <= 0.0f) goto L186;
        float r46 = getPullingLeftProgress();
        int r65 = this.nextRecentReaction.getMeasuredWidth() - AndroidUtilities.dp(2.0f);
        float r55 = r54 + r65;
        float r74 = (Utilities.clamp(r55 / (getMeasuredWidth() - this.nextRecentReaction.getMeasuredWidth()), 1.0f, 0.0f) * r46) * r65;
        if (this.nextRecentReaction.getTag() != null) goto L175;
        this.nextRecentReaction.setTag(Float.valueOf(1.0f));
        this.nextRecentReaction.resetAnimation();
        this.nextRecentReaction.play(0);
    L175:
        float r47 = Utilities.clamp(r46, 1.0f, 0.0f);
        this.nextRecentReaction.setScaleX(r47);
        this.nextRecentReaction.setScaleY(r47);
        int r48 = this.type;
        if (r48 != 1) goto L178;
    L181:
        int r49 = AndroidUtilities.dp(8.0f);
    L182:
        this.nextRecentReaction.setTranslationX(((this.recyclerListView.getX() + r55) - r74) + (-r49));
        if (this.nextRecentReaction.getVisibility() == 0) goto L194;
        this.nextRecentReaction.setVisibility(0);
        goto L194
    L178:
        if (r48 == 2) goto L181;
        r49 = AndroidUtilities.dp(20.0f);
        goto L182
    L186:
        if (this.nextRecentReaction.getVisibility() == 8) goto L191;
        if (this.isHiddenNextReaction == false) goto L191;
        this.nextRecentReaction.setVisibility(8);
    L191:
        if (this.nextRecentReaction.getTag() == null) goto L194;
        this.nextRecentReaction.setTag(null);
        goto L194
    L101:
        if (this.type == 5) goto L102;
    L194:
        if (this.skipDraw == true) goto L196;
    L199:
        boolean r66 = showCustomEmojiReaction();
        if (r66 == true) goto L203;
        r1.clipPath(this.mPath);
    L203:
        if (LocaleController.isRTL == false) goto L205;
    L206:
        r10 = -1;
    L207:
        r1.translate((r10 * getWidth()) * (1.0f - this.transitionProgress), 0.0f);
        this.recyclerListView.setTranslationX(-r152);
        super.dispatchDraw(r24);
        if (r66 == false) goto L210;
    L215:
        r1.restoreToCount(r211);
        drawBubbles(r1, r12, r11, r13, 255);
        invalidate();
        return;
    L210:
        if (this.leftShadowPaint == null) goto L213;
        this.leftShadowPaint.setAlpha((int) (Utilities.clamp(this.leftAlpha * this.transitionProgress, 1.0f, 0.0f) * r21));
        r1.drawRect(this.rect, this.leftShadowPaint);
    L213:
        if (this.rightShadowPaint == null) goto L215;
        this.rightShadowPaint.setAlpha((int) (Utilities.clamp(this.rightAlpha * this.transitionProgress, 1.0f, 0.0f) * r21));
        r1.drawRect(this.rect, this.rightShadowPaint);
        goto L215
    L205:
        if (this.mirrorX == false) goto L207;
    L196:
        if (this.reactionsWindow == null) goto L199;
        int r56 = (int) ((Utilities.clamp(1.0f - (this.customEmojiReactionsEnterProgress / 0.2f), 1.0f, 0.0f) * (1.0f - this.customEmojiReactionsEnterProgress)) * r21);
        r1.save();
        drawBubbles(r1, r12, r11, r13, r56);
        r1.restore();
        return;
    L74:
        if (this.delegate.drawBackground() == true) goto L81;
        BlurredBackgroundDrawable r212 = this.glassBackground;
        RectF r36 = this.rect;
        if (r212 == null) goto L80;
        GlassMenuHelper.draw(r212, r1, r36, this.radius, 255);
    L79:
        r142 = r43;
        r152 = r72;
        goto L83
    L80:
        float r213 = this.radius;
        r1.drawRoundRect(r36, r213, r213, this.bgPaint);
        goto L79
    L92:
        r142 = r43;
        r152 = r72;
        goto L93
    L65:
        r21 = 255.0f;
        goto L66
    L55:
        if (this.mirrorX == true) goto L58;
        r3 = 1.0f - Math.max(0.25f, this.transitionProgress);
        r53 = 1.0f;
        goto L59
    L44:
        if (this.mirrorX == true) goto L48;
        r42 = getWidth();
        r5 = 0.875f;
        goto L47
    L38:
        if (r23.isStar == false) goto L40;
        this.pressedViewScale = 1.0f;
        this.otherViewsScale = 1.0f;
        goto L41
    L20:
        if (this.type == 5) goto L35;
        ReactionsContainerDelegate r214 = this.delegate;
        if (r214 != null) goto L24;
    L25:
        float r215 = this.pressedProgress;
        if (r215 == 1.0f) goto L35;
        if (this.pressedReaction.isStar == false) goto L30;
        float r410 = ViewConfiguration.getLongPressTimeout();
    L31:
        float r216 = r215 + (16.0f / r410);
        this.pressedProgress = r216;
        if (r216 < 1.0f) goto L34;
        this.pressedProgress = 1.0f;
    L34:
        invalidate();
        goto L35
    L30:
        r410 = 1500.0f;
        goto L31
    L24:
        if (r214.allowLongPress() == false) goto L35;
    L7:
        if (r4 == true) goto L11;
        float r411 = this.flipVerticalProgress;
        if (r411 == 0.0f) goto L11;
        this.flipVerticalProgress = Math.max(0.0f, r411 - (r2 / 220.0f));
        invalidate();
        goto L11
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r3) {
        if (getAlpha() >= 0.5f) goto L7;
        return false;
    L7:
        return super.dispatchTouchEvent(r3);
    }

    public void drawBubbles(Canvas r10) {
        float r6 = (Math.max(0.25f, Math.min(this.transitionProgress, 1.0f)) - 0.25f) / 0.75f;
        float r5 = this.bigCircleRadius * r6;
        float r7 = this.smallCircleRadius * r6;
        if (this.type != 5) goto L6;
        int r0 = 255;
    L7:
        drawBubbles(r10, r5, r6, r7, r0);
        return;
    L6:
        r0 = (int) ((Utilities.clamp(this.customEmojiReactionsEnterProgress / 0.2f, 1.0f, 0.0f) * (1.0f - this.customEmojiReactionsEnterProgress)) * 255.0f);
        goto L7
    }

    public float expandSize() {
        return (int) (getPullingLeftProgress() * AndroidUtilities.dp(6.0f));
    }

    public ReactionsContainerDelegate getDelegate() {
        return this.delegate;
    }

    public int getHintTextWidth() {
        return this.hintViewWidth;
    }

    public int getItemsCount() {
        return (this.visibleReactionsList.size() + (showCustomEmojiReaction() ? 1 : 0)) + 1;
    }

    public float getPullingLeftProgress() {
        return Utilities.clamp(this.pullingLeftOffset / AndroidUtilities.dp(42.0f), 2.0f, 0.0f);
    }

    public CustomEmojiReactionsWindow getReactionsWindow() {
        return this.reactionsWindow;
    }

    public String getSelectedEmoji() {
        if (this.selectedReactions.isEmpty() == false) goto L6;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        ReactionsLayoutInBubble.VisibleReaction r0 = this.selectedReactions.iterator().next();
        long r1 = r0.documentId;
        String r4 = null;
        if (r1 == 0) goto L12;
        TLRPC.Document r5 = AnimatedEmojiDrawable.findDocument(this.currentAccount, r1);
        if (r5 == null) goto L12;
        r4 = MessageObject.findAnimatedEmojiEmoticon(r5, null);
    L12:
        if (TextUtils.isEmpty(r4) == false) goto L15;
        r4 = r0.emojicon;
    L15:
        if (TextUtils.isEmpty(r4) == false) goto L18;
        return "👍";
    L18:
        return r4;
    }

    public HashSet<ReactionsLayoutInBubble.VisibleReaction> getSelectedReactions() {
        return this.selectedReactions;
    }

    public float getTopOffset() {
        if (this.hasHint == true) goto L5;
        return 0.0f;
    L5:
        return ((FrameLayout.LayoutParams) this.recyclerListView.getLayoutParams()).topMargin;
    }

    public int getTotalWidth() {
        int r0 = getItemsCount();
        if (showCustomEmojiReaction() == true) goto L7;
        return ((AndroidUtilities.dp(36.0f) * r0) + (AndroidUtilities.dp(2.0f) * (r0 - 1))) + AndroidUtilities.dp(16.0f);
    L7:
        return (AndroidUtilities.dp(36.0f) * r0) - AndroidUtilities.dp(4.0f);
    }

    public List<ReactionsLayoutInBubble.VisibleReaction> getVisibleReactionsList() {
        return this.visibleReactionsList;
    }

    public int getWindowType() {
        int r0 = this.type;
        if (r0 != 4) goto L7;
        return 13;
    L7:
        if (r0 != 3) goto L11;
        return 11;
    L11:
        if (r0 != 5) goto L15;
        return 14;
    L15:
        if (this.showExpandableReactions == false) goto L18;
        return 8;
    L18:
        return 1;
    }

    public View getWindowView() {
        CustomEmojiReactionsWindow r0 = this.reactionsWindow;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.windowView;
    }

    public void invalidateLoopViews() {
        int r0 = 0;
    L4:
        if (r0 >= this.recyclerListView.getChildCount()) goto L9;
        View r1 = this.recyclerListView.getChildAt(r0);
        if ((r1 instanceof ReactionHolderView) == false) goto L8;
        ((ReactionHolderView) r1).loopImageView.invalidate();
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public boolean isFlippedVertically() {
        return this.isFlippedVertically;
    }

    public void measureHint() {
        if (this.hintMeasured == false) goto L5;
        return;
    L5:
        if (this.hasHint == true) goto L7;
        return;
    L7:
        if (getMeasuredWidth() <= 0) goto L36;
        int r6 = Math.min(AndroidUtilities.dp(320.0f), getMeasuredWidth() - AndroidUtilities.dp(16.0f));
        StaticLayout r3 = new StaticLayout(this.hintView.getText(), this.hintView.getPaint(), r6, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.hintViewHeight = r3.getHeight();
        this.hintViewWidth = 0;
        int r1 = 0;
    L11:
        if (r1 >= r3.getLineCount()) goto L14;
        this.hintViewWidth = Math.max(this.hintViewWidth, (int) Math.ceil(r3.getLineWidth(r1)));
        r1 = r1 + 1;
        goto L11
    L14:
        if (r3.getLineCount() > 1) goto L16;
    L22:
        this.hintView.setWidth(AndroidUtilities.dp(16.0f) + r6);
    L23:
        int r12 = Math.max(AndroidUtilities.dp(20.0f), AndroidUtilities.dp(7.0f) + this.hintViewHeight);
        int r2 = this.type;
        if (r2 != 1) goto L26;
    L29:
        r12 = AndroidUtilities.dp(20.0f);
    L30:
        ((FrameLayout.LayoutParams) this.nextRecentReaction.getLayoutParams()).topMargin = r12;
        ((FrameLayout.LayoutParams) this.recyclerListView.getLayoutParams()).topMargin = r12;
        this.hintMeasured = true;
        return;
    L26:
        if (r2 == 2) goto L29;
        getLayoutParams().height = (AndroidUtilities.dp(52.0f) + r12) + AndroidUtilities.dp(22.0f);
        goto L30
    L16:
        if (this.hintView.getText().toString().contains("\n") == true) goto L22;
        int r7 = HintView2.cutInFancyHalf(this.hintView.getText(), this.hintView.getPaint());
        StaticLayout r4 = new StaticLayout(this.hintView.getText(), this.hintView.getPaint(), r7, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.hintViewHeight = r4.getHeight();
        this.hintViewWidth = 0;
        int r13 = 0;
    L19:
        if (r13 >= r4.getLineCount()) goto L21;
        this.hintViewWidth = Math.max(this.hintViewWidth, (int) Math.ceil(r4.getLineWidth(r13)));
        r13 = r13 + 1;
        goto L19
    L21:
        this.hintView.setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
        this.hintView.setWidth(AndroidUtilities.dp(48.0f) + r7);
        goto L23
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        if (this.type != 5) goto L6;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.availableEffectsUpdate);
        return;
    }

    public void onCustomEmojiWindowClosing() {
        ValueAnimator r0 = this.pullingDownBackAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.pullingLeftOffset = 0.0f;
        FrameLayout r02 = this.customReactionsContainer;
        if (r02 == null) goto L8;
        r02.invalidate();
    L8:
        invalidate();
    }

    public void onCustomEmojiWindowOpened() {
        ValueAnimator r0 = this.pullingDownBackAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.pullingLeftOffset = 0.0f;
        FrameLayout r02 = this.customReactionsContainer;
        if (r02 == null) goto L8;
        r02.invalidate();
    L8:
        invalidate();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
        if (this.type != 5) goto L6;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.availableEffectsUpdate);
        return;
    }

    public void onReactionClicked(View r3, ReactionsLayoutInBubble.VisibleReaction r4, boolean r5) {
        ReactionsContainerDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.onReactionClicked(r3, r4, r5, true);
    L6:
        if (this.type == 5) goto L11;
        return;
    L11:
        performHapticFeedback(3, 1);     // Catch: Exception -> L10
        return;
    }

    public void onShownCustomEmojiReactionDialog() {
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        invalidateShaders();
    }

    public void prepareAnimation(boolean r1) {
        this.prepareAnimation = r1;
        invalidate();
    }

    public void reset() {
        this.isHiddenNextReaction = true;
        this.pressedReactionPosition = 0;
        this.pressedProgress = 0.0f;
        this.pullingLeftOffset = 0.0f;
        this.pressedReaction = null;
        this.clicked = false;
        AndroidUtilities.forEachViews(this.recyclerListView, new ReactionsContainerLayout$$ExternalSyntheticLambda3(this));
        this.lastVisibleViews.clear();
        this.recyclerListView.invalidate();
        FrameLayout r0 = this.customReactionsContainer;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        invalidate();
    }

    @Override // android.view.View
    public void setAlpha(float r3) {
        if (getAlpha() != r3) goto L5;
    L13:
        super.setAlpha(r3);
        return;
    L5:
        if (r3 != 0.0f) goto L13;
        this.lastVisibleViews.clear();
        int r0 = 0;
    L8:
        if (r0 >= this.recyclerListView.getChildCount()) goto L13;
        if ((this.recyclerListView.getChildAt(r0) instanceof ReactionHolderView) == false) goto L12;
        ((ReactionHolderView) this.recyclerListView.getChildAt(r0)).resetAnimation();
    L12:
        r0 = r0 + 1;
        goto L8
    }

    public void setBubbleOffset(float r1) {
        this.bubblesOffset = r1;
    }

    public void setChatScrimView(ChatScrimPopupContainerLayout r1) {
        this.chatScrimPopupContainerLayout = r1;
    }

    public void setCurrentAccount(int r1) {
        this.currentAccount = r1;
    }

    public void setCustomEmojiEnterProgress(float r3) {
        this.customEmojiReactionsEnterProgress = r3;
        ChatScrimPopupContainerLayout r0 = this.chatScrimPopupContainerLayout;
        if (r0 == null) goto L5;
        r0.setPopupAlpha(1.0f - r3);
    L5:
        invalidate();
    }

    public void setCustomEmojiReactionsBackground(boolean r3) {
        InternalImageView r2 = this.customEmojiReactionsIconView;
        if (r3 == false) goto L6;
        r2.setBackground(Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(28.0f), 0, ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_listSelector), 40)));
        return;
    L6:
        r2.setBackground(null);
    }

    public void setDelegate(ReactionsContainerDelegate r1) {
        this.delegate = r1;
    }

    public void setFlippedVertically(boolean r1) {
        this.isFlippedVertically = r1;
        invalidate();
    }

    public void setFragment(BaseFragment r1) {
        this.fragment = r1;
    }

    public void setGlassBackground(BlurredBackgroundDrawableViewFactory r3, BlurredBackgroundProvider r4) {
        this.glassBackgroundFactory = r3;
        this.glassBackgroundColorProvider = r4;
        this.glassBackground = r3.create(this, true).setColorProvider(r4);
        this.glassBackgroundBubbleBig = r3.create(this, true).setColorProvider(r4);
        this.glassBackgroundBubbleSmall = r3.create(this, true).setColorProvider(r4);
        invalidate();
    }

    public void setHint(CharSequence r11) {
        this.hasHint = true;
        if (this.hintView != null) goto L14;
        LinkSpanDrawable.LinksTextView r1 = new LinkSpanDrawable.LinksTextView(getContext(), this.resourcesProvider);
        this.hintView = r1;
        r1.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f), 0);
        this.hintView.setClickable(true);
        this.hintView.setTextSize(1, 12.0f);
        int r12 = this.type;
        if (r12 != 1) goto L7;
    L12:
        this.hintView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        this.hintView.setAlpha(0.5f);
    L13:
        this.hintView.setGravity(1);
        addView(this.hintView, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 6.0f, 0.0f, 0.0f));
        goto L14
    L7:
        if (r12 == 2) goto L12;
        if (r12 == 4) goto L12;
        this.hintView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider));
    L14:
        this.hintView.setText(r11);
        this.hintMeasured = false;
        FrameLayout.LayoutParams r112 = (FrameLayout.LayoutParams) this.nextRecentReaction.getLayoutParams();
        r112.topMargin = AndroidUtilities.dp(20.0f);
        FrameLayout.LayoutParams r10 = (FrameLayout.LayoutParams) this.recyclerListView.getLayoutParams();
        r10.topMargin = AndroidUtilities.dp(20.0f);
    }

    public void setMessage(MessageObject r12, TLRPC.ChatFull r13, boolean r14) {
        this.messageObject = r12;
        int r0 = 0;
        if (r12 == null) goto L13;
        TLRPC.Message r1 = r12.messageOwner;
        if (r1 == null) goto L13;
        TLRPC.TL_messageReactions r15 = r1.reactions;
        if (r15 == null) goto L13;
        ArrayList<TLRPC.ReactionCount> r16 = r15.results;
        int r2 = r16.size();
        int r3 = 0;
        int r4 = 0;
    L9:
        if (r4 >= r2) goto L15;
        TLRPC.ReactionCount r5 = r16.get(r4);
        r4 = r4 + 1;
        if ((r5.reaction instanceof TLRPC.TL_reactionPaid) == true) goto L9;
        r3 = r3 + 1;
    L15:
        if (this.type == 0) goto L17;
    L21:
        boolean r17 = false;
    L22:
        this.hitLimit = r17;
        if (this.type == 0) goto L25;
    L29:
        boolean r18 = false;
    L30:
        this.channelReactions = r18;
        ArrayList r19 = new ArrayList();
        if (r12 != null) goto L33;
    L38:
        this.hasStar = false;
        int r42 = this.type;
        if (r42 != 3) goto L42;
        this.allReactionsAvailable = UserConfig.getInstance(this.currentAccount).isPremium();
        fillRecentReactionsList(r19);
    L83:
        filterReactions(r19);
        if (this.hitLimit == false) goto L86;
    L96:
        boolean r132 = false;
    L97:
        this.showExpandableReactions = r132;
        if (this.type != 3) goto L103;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L103;
        this.showExpandableReactions = false;
    L103:
        if (this.type != 4) goto L105;
        this.showExpandableReactions = true;
    L105:
        setVisibleReactionsList(r19, r14);
        if (r12 == null) goto L117;
        TLRPC.TL_messageReactions r133 = r12.messageOwner.reactions;
        if (r133 != null) goto L110;
        return;
    L110:
        if (r133.results != null) goto L112;
        return;
    L112:
        if (r0 >= r12.messageOwner.reactions.results.size()) goto L137;
        if (r12.messageOwner.reactions.results.get(r0).chosen == false) goto L116;
        this.selectedReactions.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r12.messageOwner.reactions.results.get(r0).reaction));
    L116:
        r0 = r0 + 1;
        goto L112
    L137:
        return;
    L117:
        return;
    L86:
        if (this.allReactionsAvailable == true) goto L90;
        if (r19.size() <= 16) goto L90;
    L95:
        r132 = true;
    L90:
        if (this.allReactionsAvailable == false) goto L96;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L96;
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == false) goto L96;
    L42:
        if (r42 != 5) goto L45;
        this.allReactionsAvailable = true;
        fillRecentReactionsList(r19);
        goto L83
    L45:
        if (this.hitLimit == false) goto L54;
        this.allReactionsAvailable = false;
        if (r13 != null) goto L49;
    L51:
        ArrayList<TLRPC.ReactionCount> r134 = this.messageObject.messageOwner.reactions.results;
        int r43 = r134.size();
        int r6 = 0;
    L52:
        if (r6 >= r43) goto L83;
        TLRPC.ReactionCount r7 = r134.get(r6);
        r6 = r6 + 1;
        r19.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r7.reaction));
        goto L52
    L49:
        if (r13.paid_reactions_available == false) goto L51;
        this.hasStar = true;
        r19.add(ReactionsLayoutInBubble.VisibleReaction.asStar());
        goto L51
    L54:
        if (r13 != null) goto L56;
        this.allReactionsAvailable = true;
        fillRecentReactionsList(r19);
        goto L83
    L56:
        if (r13.paid_reactions_available == false) goto L58;
        this.hasStar = true;
        r19.add(ReactionsLayoutInBubble.VisibleReaction.asStar());
    L58:
        TLRPC.ChatReactions r44 = r13.available_reactions;
        if ((r44 instanceof TLRPC.TL_chatReactionsAll) == false) goto L68;
        TLRPC.Chat r135 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r13.id));
        if (r135 != null) goto L63;
    L65:
        this.allReactionsAvailable = false;
    L66:
        fillRecentReactionsList(r19);
        goto L83
    L63:
        if (ChatObject.isChannelAndNotMegaGroup(r135) == true) goto L65;
        this.allReactionsAvailable = true;
        goto L66
    L68:
        if ((r44 instanceof TLRPC.TL_chatReactionsSome) == false) goto L83;
        ArrayList<TLRPC.Reaction> r136 = ((TLRPC.TL_chatReactionsSome) r44).reactions;
        int r45 = r136.size();
        int r62 = 0;
    L70:
        if (r62 >= r45) goto L83;
        TLRPC.Reaction r72 = r136.get(r62);
        r62 = r62 + 1;
        TLRPC.Reaction r73 = r72;
        Iterator<TLRPC.TL_availableReaction> r8 = MediaDataController.getInstance(this.currentAccount).getEnabledReactionsList().iterator();
    L73:
        if (r8.hasNext() == false) goto L70;
        TLRPC.TL_availableReaction r9 = r8.next();
        if ((r73 instanceof TLRPC.TL_reactionEmoji) == false) goto L80;
        if (r9.reaction.equals(((TLRPC.TL_reactionEmoji) r73).emoticon) == false) goto L80;
        r19.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r73));
    L80:
        if ((r73 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L73;
        r19.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r73));
        goto L70
    L33:
        if (r12.isForwardedChannelPost() == false) goto L38;
        r13 = MessagesController.getInstance(this.currentAccount).getChatFull(-r12.getFromChatId());
        if (r13 != null) goto L38;
        this.waitingLoadingChatId = -r12.getFromChatId();
        MessagesController.getInstance(this.currentAccount).loadFullChat(-r12.getFromChatId(), 0, true);
        setVisibility(4);
        return;
    L25:
        if (this.messageObject == null) goto L29;
        if (ChatObject.isChannelAndNotMegaGroup(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.messageObject.getDialogId()))) == false) goto L29;
        r18 = true;
        goto L30
    L17:
        if (this.messageObject == null) goto L21;
        if (r3 < MessagesController.getInstance(this.currentAccount).getChatMaxUniqReactions(this.messageObject.getDialogId())) goto L21;
        r17 = true;
    L13:
        r3 = 0;
        goto L15
    }

    public void setMiniBubblesOffset(float r1) {
        this.miniBubblesOffset = r1;
    }

    public void setMirrorX(boolean r1) {
        this.mirrorX = r1;
        invalidate();
    }

    public void setOnSwitchedToLoopView(Runnable r1) {
        this.onSwitchedToLoopView = r1;
    }

    public void setParentLayout(ChatScrimPopupContainerLayout r1) {
        this.parentLayout = r1;
    }

    public void setPaused(boolean r2, boolean r3) {
        if (this.paused == r2) goto L13;
        this.paused = r2;
        this.pausedExceptSelected = r3;
        CustomEmojiReactionsWindow r22 = this.reactionsWindow;
        if (r22 != null) goto L8;
        return;
    L8:
        if (r22.getSelectAnimatedEmojiDialog() == null) goto L12;
        this.reactionsWindow.getSelectAnimatedEmojiDialog().setPaused(this.paused, this.pausedExceptSelected);
        return;
    L12:
        return;
    }

    public void setSelectedEmojis(ArrayList<String> r5) {
        this.selectedReactions.clear();
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        String r2 = r5.get(r1);
        r1 = r1 + 1;
        ReactionsLayoutInBubble.VisibleReaction r22 = ReactionsLayoutInBubble.VisibleReaction.fromEmojicon(r2);
        if (r22 == null) goto L3;
        this.selectedReactions.add(r22);
        this.alwaysSelectedReactions.add(r22);
        goto L3
    L7:
        updateSelected(true);
    }

    public void setSelectedReaction(ReactionsLayoutInBubble.VisibleReaction r2) {
        this.selectedReactions.clear();
        if (r2 == null) goto L5;
        this.selectedReactions.add(r2);
    L5:
        this.listAdapter.notifyDataSetChanged();
    }

    public void setSelectedReactionAnimated(ReactionsLayoutInBubble.VisibleReaction r2) {
        this.selectedReactions.clear();
        if (r2 == null) goto L5;
        this.selectedReactions.add(r2);
    L5:
        updateSelected(true);
    }

    public void setSelectedReactionInclusive(ReactionsLayoutInBubble.VisibleReaction r2) {
        this.selectedReactions.clear();
        if (r2 == null) goto L5;
        this.selectedReactions.add(r2);
    L5:
        updateSelected(true);
    }

    public void setSelectedReactions(ArrayList<MessageObject> r7) {
        this.selectedReactions.clear();
        int r1 = 0;
    L4:
        if (r1 >= r7.size()) goto L19;
        MessageObject r2 = r7.get(r1);
        if (r2 == null) goto L18;
        TLRPC.TL_messageReactions r3 = r2.messageOwner.reactions;
        if (r3 == null) goto L18;
        if (r3.results == null) goto L18;
        int r32 = 0;
    L13:
        if (r32 >= r2.messageOwner.reactions.results.size()) goto L18;
        if (r2.messageOwner.reactions.results.get(r32).chosen == false) goto L17;
        this.selectedReactions.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r2.messageOwner.reactions.results.get(r32).reaction));
    L17:
        r32 = r32 + 1;
    L18:
        r1 = r1 + 1;
        goto L4
    L19:
        this.listAdapter.notifyDataSetChanged();
    }

    public void setSelectedReactionsInclusive(ArrayList<MessageObject> r2) {
        this.selectedReactions.clear();
        this.selectedReactions.addAll(getInclusiveReactions(r2));
        updateSelected(true);
    }

    public void setSkipDraw(boolean r5) {
        if (this.skipDraw == r5) goto L27;
        this.skipDraw = r5;
        if (r5 == true) goto L22;
        int r0 = 0;
    L8:
        if (r0 >= this.recyclerListView.getChildCount()) goto L22;
        if ((this.recyclerListView.getChildAt(r0) instanceof ReactionHolderView) == false) goto L21;
        ReactionHolderView r1 = (ReactionHolderView) this.recyclerListView.getChildAt(r0);
        if (r1.hasEnterAnimation == true) goto L14;
    L20:
        r1.invalidate();
        goto L21
    L14:
        if (r1.loopImageView.getImageReceiver().getLottieAnimation() == null) goto L16;
    L17:
        r1.loopImageView.setVisibility(0);
        r1.enterImageView.setVisibility(4);
        if (r1.shouldSwitchToLoopView == false) goto L20;
        r1.switchedToLoopView = true;
        goto L20
    L16:
        if (r1.loopImageView.getImageReceiver().getAnimation() == null) goto L20;
    L21:
        r0 = r0 + 1;
    L22:
        invalidate();
        return;
    }

    public void setStoryItem(TL_stories.StoryItem r2) {
        this.selectedReactions.clear();
        if (r2 == null) goto L7;
        TLRPC.Reaction r22 = r2.sent_reaction;
        if (r22 == null) goto L7;
        this.selectedReactions.add(ReactionsLayoutInBubble.VisibleReaction.fromTL(r22));
    L7:
        this.listAdapter.notifyDataSetChanged();
    }

    public void setTop(boolean r1) {
        this.isTop = r1;
    }

    public void setTransitionProgress(float r3) {
        this.transitionProgress = r3;
        ChatScrimPopupContainerLayout r0 = this.parentLayout;
        if (r0 != null) goto L5;
    L11:
        invalidate();
        return;
    L5:
        if (this.animatePopup == true) goto L7;
    L9:
        r3 = 1.0f;
    L10:
        r0.setReactionsTransitionProgress(r3);
        goto L11
    L7:
        if (allowSmoothEnterTransition() == false) goto L9;
        goto L9
    }

    @Override // android.view.View
    public void setTranslationX(float r2) {
        if (r2 == getTranslationX()) goto L6;
        super.setTranslationX(r2);
        return;
    }

    public boolean showCustomEmojiReaction() {
        if (this.allReactionsAvailable == false) goto L5;
        return true;
    L5:
        if (this.showExpandableReactions == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean showExpandableReactions() {
        return this.showExpandableReactions;
    }

    public void startEnterAnimation(boolean r5) {
        this.animatePopup = r5;
        setTransitionProgress(0.0f);
        setAlpha(1.0f);
        this.notificationsLocker.lock();
        if (allowSmoothEnterTransition() == false) goto L5;
        ObjectAnimator r52 = ObjectAnimator.ofFloat(this, TRANSITION_PROGRESS_VALUE, new float[]{0.0f, 1.0f}).setDuration(250);
        r52.setInterpolator(new OvershootInterpolator(0.5f));
    L6:
        r52.addListener(new AnonymousClass7(this));
        r52.start();
        return;
    L5:
        r52 = ObjectAnimator.ofFloat(this, TRANSITION_PROGRESS_VALUE, new float[]{0.0f, 1.0f}).setDuration(250);
        r52.setInterpolator(new OvershootInterpolator(0.5f));
        goto L6
    }
}
