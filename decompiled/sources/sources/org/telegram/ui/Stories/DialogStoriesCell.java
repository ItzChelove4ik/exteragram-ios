package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.TabIconsMode;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.android.animator.ReplaceAnimator;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarAnimatedSubtitleOverlayContainer;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CanvasButton;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EllipsizeSpanAnimator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RadialProgress;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesUtilities;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.Stories.recorder.StoryRecorder;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public abstract class DialogStoriesCell extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    public float K;
    private ActionBar actionBar;
    Adapter adapter;
    Paint addCirclePaint;
    private final Drawable addNewStoryDrawable;
    private int addNewStoryLastColor;
    ArrayList<Runnable> afterNextLayout;
    public boolean allowGlobalUpdates;
    ArrayList<Long> animateToDialogIds;
    private Runnable animationRunnable;
    private final BoolAnimator animatorHasTitleText;
    Paint backgroundPaint;
    private long checkedStoryNotificationDeletion;
    private int clipTop;
    boolean collapsed;
    private ValueAnimator collapsedOvershootAnimator;
    private float collapsedOvershootProgress;
    float collapsedProgress;
    private float collapsedProgress1;
    private float collapsedProgress2;
    private float collapsedSpringCoef;
    Comparator<StoryCell> comparator;
    int currentAccount;
    public int currentCellWidth;
    int currentState;
    private CharSequence currentTitle;
    private boolean dialogsEmojiStatusVisible;
    private boolean dialogsEmojiStatusVisibleOut;
    private CharSequence dialogsTitleOverride;
    boolean drawCircleForce;
    private LinearGradient ellipsizeGradient;
    private Matrix ellipsizeGradientMatrix;
    private Paint ellipsizePaint;
    EllipsizeSpanAnimator ellipsizeSpanAnimator;
    ImageView emojiStatusView;
    private ValueAnimator expandOvershootAnimator;
    private float expandOvershootAnimatorProgress;
    private float expandedSpringCoef;
    BaseFragment fragment;
    private StoriesUtilities.EnsureStoryFileLoadedObject globalCancelable;
    Paint grayPaint;
    private boolean hasOverlayText;
    DefaultItemAnimator itemAnimator;
    ArrayList<Item> items;
    private boolean lastUploadingCloseFriends;
    LinearLayoutManager layoutManager;
    RecyclerListView listViewMini;
    private float menuItemsOffset;
    Adapter miniAdapter;
    private final DefaultItemAnimator miniItemAnimator;
    ArrayList<Item> miniItems;
    CanvasButton miniItemsClickArea;
    ArrayList<Item> oldItems;
    ArrayList<Item> oldMiniItems;
    private float overScrollCoef;
    private int overlayTextId;
    private float overscrollProgress;
    private int overscrollSelectedPosition;
    private StoryCell overscrollSelectedView;
    private HintView2 premiumHint;
    private Drawable premiumStar;
    public RadialProgress radialProgress;
    public RecyclerListView recyclerListView;
    AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable statusDrawable;
    AnimatorSet storiesAnimatorSet;
    private OvershootInterpolator storiesCollapseInterpolator;
    StoriesController storiesController;
    private OvershootInterpolator storiesExpandInterpolator;
    ActionBarAnimatedSubtitleOverlayContainer subtitleOverlayContainer;
    private ValueAnimator textAnimator;
    private ValueAnimator titleOverrideAnimator;
    private boolean titleOverrideForward;
    private float titleOverrideProgress;
    AnimatedTextView titleView;
    AnimatedTextView titleViewOut;
    private final int type;
    boolean updateOnIdleState;
    private ValueAnimator valueAnimator;
    ArrayList<StoryCell> viewsDrawInParent;
    private ValueAnimator yStoriesAnimator;
    private float yStoriesProgress;

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass1(DialogStoriesCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (DialogStoriesCell.m19307$$Nest$fgetcollapsedProgress1(this.this$0) <= 0.2f) goto L7;
            return false;
        L7:
            if (this.this$0.getAlpha() != 0.0f) goto L11;
            return false;
        L11:
            return super.dispatchTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (this.this$0.viewsDrawInParent.contains(r3) == false) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = 0;
        L3:
            int r22 = this.this$0.afterNextLayout.size();
            DialogStoriesCell r32 = this.this$0;
            if (r12 >= r22) goto L6;
            r32.afterNextLayout.get(r12).run();
            r12 = r12 + 1;
            goto L3
        L6:
            r32.afterNextLayout.clear();
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$10, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogStoriesCell this$0;
        final /* synthetic */ boolean val$newCollapsed;

        public AnonymousClass10(DialogStoriesCell r1, boolean r2) {
            this.this$0 = r1;
            this.val$newCollapsed = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            DialogStoriesCell r22 = this.this$0;
            if (this.val$newCollapsed == false) goto L5;
            float r0 = 1.0f;
        L6:
            DialogStoriesCell.m19320$$Nest$fputcollapsedProgress2(r22, r0);
            DialogStoriesCell.m19328$$Nest$mcheckCollapsedProgress(this.this$0);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            super.onAnimationStart(r1);
            this.this$0.performHapticFeedback(3);     // Catch: Exception -> L5
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$11, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass11(DialogStoriesCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (DialogStoriesCell.m19316$$Nest$fgettitleOverrideAnimator(this.this$0) != r3) goto L5;
            DialogStoriesCell.m19326$$Nest$fputtitleOverrideAnimator(this.this$0, null);
            DialogStoriesCell.m19327$$Nest$fputtitleOverrideProgress(this.this$0, 1.0f);
            this.this$0.titleViewOut.setText(null, false);
            this.this$0.titleViewOut.setVisibility(8);
        L5:
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$12, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass12 extends CombinedDrawable {
        int lastColor;
        final /* synthetic */ DialogStoriesCell this$0;
        final /* synthetic */ Drawable val$checkDrawable;
        final /* synthetic */ Drawable val$verifyDrawable;

        public AnonymousClass12(DialogStoriesCell r1, Drawable r2, Drawable r3, Drawable r4, Drawable r5) {
            this.this$0 = r1;
            this.val$verifyDrawable = r4;
            this.val$checkDrawable = r5;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.CombinedDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r6) {
            int r0 = DialogStoriesCell.m19317$$Nest$fgettype(this.this$0);
            DialogStoriesCell r1 = this.this$0;
            if (r0 != 0) goto L6;
            int r02 = Theme.key_actionBarDefault;
        L5:
            int r03 = DialogStoriesCell.m19332$$Nest$mgetThemedColor(r1, r02);
            if (this.lastColor == r03) goto L15;
            this.lastColor = r03;
            int r12 = DialogStoriesCell.m19317$$Nest$fgettype(this.this$0);
            DialogStoriesCell r2 = this.this$0;
            if (r12 != 0) goto L13;
            int r13 = Theme.key_actionBarDefaultTitle;
        L12:
            int r14 = DialogStoriesCell.m19332$$Nest$mgetThemedColor(r2, r13);
            Drawable r22 = this.val$verifyDrawable;
            int r15 = ColorUtils.blendARGB(r14, r03, 0.1f);
            PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
            r22.setColorFilter(new PorterDuffColorFilter(r15, r4));
            this.val$checkDrawable.setColorFilter(new PorterDuffColorFilter(r03, r4));
            goto L15
        L13:
            r13 = Theme.key_actionBarDefaultArchivedTitle;
        L15:
            super.draw(r6);
            return;
        L6:
            r02 = Theme.key_actionBarDefaultArchived;
            goto L5
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$13, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass13 extends AnimatedEmojiDrawable.WrapSizeDrawable {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass13(DialogStoriesCell r1, Drawable r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiDrawable.WrapSizeDrawable, android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            r3.save();
            r3.translate(AndroidUtilities.dp(-2.0f), AndroidUtilities.dp(1.0f));
            super.draw(r3);
            r3.restore();
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ DialogStoriesCell this$0;
        final /* synthetic */ TextView val$overscrollTextView;

        public AnonymousClass2(DialogStoriesCell r1, TextView r2) {
            this.this$0 = r1;
            this.val$overscrollTextView = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r3, float r4) {
            DialogStoriesCell.m19324$$Nest$fputoverScrollCoef(this.this$0, AndroidUtilities.lerp(0.2f, 1.0f, r4));
            this.val$overscrollTextView.setText("Screen oversrcoll: " + DialogStoriesCell.m19312$$Nest$fgetoverScrollCoef(this.this$0));
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ DialogStoriesCell this$0;
        final /* synthetic */ TextView val$collapsedSpringTextView;

        public AnonymousClass3(DialogStoriesCell r1, TextView r2) {
            this.this$0 = r1;
            this.val$collapsedSpringTextView = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r3, float r4) {
            DialogStoriesCell.m19321$$Nest$fputcollapsedSpringCoef(this.this$0, AndroidUtilities.lerp(0.25f, 2.5f, r4));
            this.val$collapsedSpringTextView.setText("Collapsed spring: " + DialogStoriesCell.m19309$$Nest$fgetcollapsedSpringCoef(this.this$0));
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ DialogStoriesCell this$0;
        final /* synthetic */ TextView val$expandedSpringTextView;

        public AnonymousClass4(DialogStoriesCell r1, TextView r2) {
            this.this$0 = r1;
            this.val$expandedSpringTextView = r2;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r3, float r4) {
            DialogStoriesCell.m19322$$Nest$fputexpandedSpringCoef(this.this$0, AndroidUtilities.lerp(0.25f, 2.5f, r4));
            this.val$expandedSpringTextView.setText("Expanded X spring: " + DialogStoriesCell.m19310$$Nest$fgetexpandedSpringCoef(this.this$0));
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass5(DialogStoriesCell r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            this.this$0.invalidate();
            DialogStoriesCell.m19329$$Nest$mcheckLoadMore(this.this$0);
            if (DialogStoriesCell.m19314$$Nest$fgetpremiumHint(this.this$0) == null) goto L6;
            DialogStoriesCell.m19314$$Nest$fgetpremiumHint(this.this$0).hide();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$6, reason: invalid class name */
    public class AnonymousClass6 extends ActionBarAnimatedSubtitleOverlayContainer {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass6(DialogStoriesCell r1, Context r2, Theme.ResourcesProvider r3, EllipsizeSpanAnimator r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarAnimatedSubtitleOverlayContainer, me.vkryl.android.animator.ReplaceAnimator.Callback
        public void onItemChanged(ReplaceAnimator<?> r1) {
            super.onItemChanged(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerListView {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass7(DialogStoriesCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r13) {
            this.this$0.viewsDrawInParent.clear();
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r1 >= getChildCount()) goto L14;
            StoryCell r2 = (StoryCell) getChildAt(r1);
            int r3 = getChildAdapterPosition(r2);
            r2.position = r3;
            boolean r4 = true;
            r2.drawInParent = true;
            if (r3 != 0) goto L8;
            boolean r5 = true;
        L9:
            r2.isFirst = r5;
            if (r3 == (this.this$0.miniItems.size() - 1)) goto L13;
            r4 = false;
        L13:
            r2.isLast = r4;
            this.this$0.viewsDrawInParent.add(r2);
            r1 = r1 + 1;
            goto L4
        L8:
            r5 = false;
            goto L9
        L14:
            DialogStoriesCell r12 = this.this$0;
            Collections.sort(r12.viewsDrawInParent, r12.comparator);
        L16:
            if (r0 >= this.this$0.viewsDrawInParent.size()) goto L22;
            StoryCell r14 = this.this$0.viewsDrawInParent.get(r0);
            int r22 = r13.save();
            r13.translate(r14.getX(), r14.getY());
            if (r14.getAlpha() == 1.0f) goto L20;
            Canvas r52 = r13;
            r52.saveLayerAlpha(-AndroidUtilities.dp(4.0f), -AndroidUtilities.dp(4.0f), AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f), (int) (r14.getAlpha() * 255.0f), 31);
        L21:
            r52.scale(r14.getScaleX(), r14.getScaleY(), AndroidUtilities.dp(14.0f), r14.getCy());
            r14.draw(r52);
            r52.restoreToCount(r22);
            r0 = r0 + 1;
            r13 = r52;
            goto L16
        L20:
            r52 = r13;
            goto L21
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
            if (DialogStoriesCell.m19314$$Nest$fgetpremiumHint(this.this$0) == null) goto L6;
            DialogStoriesCell.m19314$$Nest$fgetpremiumHint(this.this$0).hide();
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$8, reason: invalid class name */
    public class AnonymousClass8 extends RecyclerView.ItemDecoration {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass8(DialogStoriesCell r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            int r0 = r3.getChildLayoutPosition(r2);
            r1.setEmpty();
            if (r0 != 1) goto L7;
            r1.left = (-AndroidUtilities.dp(85.0f)) + AndroidUtilities.dp(31.0f);
            return;
        L7:
            if (r0 != 2) goto L10;
            r1.left = (-AndroidUtilities.dp(85.0f)) + AndroidUtilities.dp(31.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$9, reason: invalid class name */
    public class AnonymousClass9 extends DefaultItemAnimator {
        final /* synthetic */ DialogStoriesCell this$0;

        public AnonymousClass9(DialogStoriesCell r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            return 0.6f;
        }
    }

    public class Adapter extends AdapterWithDiffUtils {
        boolean mini;
        final /* synthetic */ DialogStoriesCell this$0;

        public Adapter(DialogStoriesCell r1, boolean r2) {
            this.this$0 = r1;
            this.mini = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = this.mini;
            DialogStoriesCell r1 = this.this$0;
            if (r0 == false) goto L7;
            ArrayList<Item> r12 = r1.miniItems;
        L6:
            return r12.size();
        L7:
            r12 = r1.items;
            goto L6
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            StoryCell r32 = (StoryCell) r3.itemView;
            r32.position = r4;
            boolean r0 = this.mini;
            DialogStoriesCell r2 = this.this$0;
            if (r0 == false) goto L6;
            r32.setDialogId(r2.miniItems.get(r4).dialogId);
            return;
        L6:
            r32.setDialogId(r2.items.get(r4).dialogId);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            StoryCell r32 = new StoryCell(this.this$0, r2.getContext());
            StoryCell.m19334$$Nest$fputmini(r32, this.mini);
            if (this.mini == false) goto L6;
            r32.setProgressToCollapsed(1.0f, 1.0f, 0.0f, false);
        L6:
            return new RecyclerListView.Holder(r32);
        }
    }

    public class Item extends AdapterWithDiffUtils.Item {
        final long dialogId;
        final /* synthetic */ DialogStoriesCell this$0;

        public Item(DialogStoriesCell r1, long r2) {
            this.this$0 = r1;
            super(0, false);
            this.dialogId = r2;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if ((r6 instanceof Item) == true) goto L9;
            return false;
        L9:
            if (this.dialogId != ((Item) r6).dialogId) goto L11;
            return true;
        L11:
            return false;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.dialogId)});
        }
    }

    /* loaded from: classes7.dex */
    public class StoryCell extends FrameLayout {
        AvatarDrawable avatarDrawable;
        public ImageReceiver avatarImage;
        private float bounceScale;
        public StoriesUtilities.EnsureStoryFileLoadedObject cancellable;
        TLRPC.Chat chat;
        AvatarDrawable crossfadeAvatarDrawable;
        public ImageReceiver crossfadeToAvatarImage;
        boolean crossfadeToDialog;
        long crossfadeToDialogId;
        private float cx;
        private float cy;
        long dialogId;
        public boolean drawAvatar;
        public boolean drawInParent;
        private final AnimatedFloat failT;
        boolean isFail;
        public boolean isFirst;
        public boolean isLast;
        boolean isSelf;
        private boolean isUploadingState;
        private boolean mini;
        private float overscrollProgress;
        public final StoriesUtilities.AvatarStoryParams params;
        public int position;
        float progressToCollapsed;
        float progressToCollapsed2;
        boolean progressWasDrawn;
        public RadialProgress radialProgress;
        private boolean selectedForOverscroll;
        float textAlpha;
        float textAlphaTransition;
        SimpleTextView textView;
        FrameLayout textViewContainer;
        final /* synthetic */ DialogStoriesCell this$0;
        TLRPC.User user;
        private Drawable verifiedDrawable;

        /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$StoryCell$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ StoryCell this$1;
            final /* synthetic */ View val$oldTextView;

            public AnonymousClass1(StoryCell r1, View r2) {
                this.this$1 = r1;
                this.val$oldTextView = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                super.onAnimationEnd(r2);
                DialogStoriesCell.m19325$$Nest$fputtextAnimator(this.this$1.this$0, null);
                AndroidUtilities.removeFromParent(this.val$oldTextView);
            }
        }

        /* renamed from: org.telegram.ui.Stories.DialogStoriesCell$StoryCell$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ StoryCell this$1;

            public AnonymousClass2(StoryCell r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                this.this$1.params.forceAnimateProgressToSegments = false;
            }
        }

        public static /* synthetic */ void $r8$lambda$3ZnTv2Lx6FObaSBnotR50v39svQ(StoryCell r0, View r1, ValueAnimator r2) {
            r0.lambda$setDialogId$0(r1, r2);
        }

        /* renamed from: $r8$lambda$KAhbR-S0GGcWTL6pOfy9fwt3auU, reason: not valid java name */
        public static /* synthetic */ void m19333$r8$lambda$KAhbRS0GGcWTL6pOfy9fwt3auU(StoryCell r0, ValueAnimator r1) {
            r0.lambda$dispatchDraw$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$sHvrbiaOLkQ6sazwSWEPBlEEbEM(StoryCell r0) {
            r0.lambda$setDialogId$1();
        }

        /* renamed from: -$$Nest$fputmini, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19334$$Nest$fputmini(StoryCell r0, boolean r1) {
            r0.mini = r1;
        }

        /* renamed from: -$$Nest$msetClipInParent, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19335$$Nest$msetClipInParent(StoryCell r0, boolean r1) {
            r0.setClipInParent(r1);
        }

        public StoryCell(DialogStoriesCell r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            this.avatarDrawable = new AvatarDrawable();
            this.avatarImage = new ImageReceiver(this);
            this.crossfadeToAvatarImage = new ImageReceiver(this);
            this.crossfadeAvatarDrawable = new AvatarDrawable();
            this.drawAvatar = true;
            StoriesUtilities.AvatarStoryParams r0 = new StoriesUtilities.AvatarStoryParams(true);
            this.params = r0;
            this.textAlpha = 1.0f;
            this.textAlphaTransition = 1.0f;
            this.bounceScale = 1.0f;
            this.failT = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            if (DialogStoriesCell.m19317$$Nest$fgettype(r10) != 1) goto L5;
            boolean r9 = true;
        L6:
            r0.isArchive = r9;
            r0.isDialogStoriesCell = true;
            this.avatarImage.setInvalidateAll(true);
            this.avatarImage.setAllowLoadingOnAttachedOnly(true);
            FrameLayout r92 = new FrameLayout(getContext());
            this.textViewContainer = r92;
            r92.setClipChildren(false);
            if (this.mini == true) goto L9;
            setClipChildren(false);
        L9:
            createTextView();
            addView(this.textViewContainer, LayoutHelper.createFrame(-1, -2.0f));
            this.avatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f));
            this.crossfadeToAvatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f));
            return;
        L5:
            r9 = false;
            goto L6
        }

        private void createTextView() {
            SimpleTextView r0 = new SimpleTextView(getContext());
            this.textView = r0;
            r0.setTypeface(AndroidUtilities.bold());
            this.textView.setGravity(17);
            this.textView.setTextSize(11);
            this.textView.setTextColor(DialogStoriesCell.m19331$$Nest$mgetTextColor(this.this$0));
            NotificationCenter.listenEmojiLoading(this.textView);
            this.textView.setMaxLines(1);
            this.textViewContainer.addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 0, 1.0f, 0.0f, 1.0f, 0.0f));
            this.avatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f));
            this.crossfadeToAvatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f));
        }

        private float getArcProgress(float r3, float r4) {
            if (this.isLast == false) goto L5;
        L11:
            return 0.0f;
        L5:
            if (DialogStoriesCell.m19313$$Nest$fgetoverscrollProgress(this.this$0) > 0.0f) goto L11;
            if (AndroidUtilities.lerp(getMeasuredWidth(), AndroidUtilities.dp(16.0f), CubicBezierInterpolator.EASE_OUT.getInterpolation(this.progressToCollapsed)) >= ((r4 + AndroidUtilities.dpf2(3.5f)) * 2.0f)) goto L11;
            return ((float) Math.toDegrees(Math.acos((r2 / 2.0f) / r4))) * 2.0f;
        }

        private /* synthetic */ void lambda$dispatchDraw$2(ValueAnimator r4) {
            StoriesUtilities.AvatarStoryParams r0 = this.params;
            r0.progressToSegments = AndroidUtilities.lerp(0.0f, 1.0f - DialogStoriesCell.m19308$$Nest$fgetcollapsedProgress2(this.this$0), ((Float) r4.getAnimatedValue()).floatValue());
            invalidate();
        }

        private /* synthetic */ void lambda$setDialogId$0(View r4, ValueAnimator r5) {
            float r52 = ((Float) r5.getAnimatedValue()).floatValue();
            float r0 = 1.0f - r52;
            r4.setAlpha(r0);
            r4.setTranslationY((-AndroidUtilities.dp(5.0f)) * r52);
            this.textView.setAlpha(r52);
            this.textView.setTranslationY(AndroidUtilities.dp(5.0f) * r0);
        }

        private /* synthetic */ void lambda$setDialogId$1() {
            if (DialogStoriesCell.m19315$$Nest$fgettextAnimator(this.this$0) == null) goto L5;
            DialogStoriesCell.m19315$$Nest$fgettextAnimator(this.this$0).start();
        L5:
            DialogStoriesCell.m19319$$Nest$fputanimationRunnable(this.this$0, null);
        }

        private void setClipInParent(boolean r2) {
            if (getParent() == null) goto L6;
            ((ViewGroup) getParent()).setClipChildren(r2);
        L6:
            if (getParent() != null) goto L8;
            return;
        L8:
            if (getParent().getParent() != null) goto L10;
            return;
        L10:
            if (getParent().getParent().getParent() == null) goto L15;
            ((ViewGroup) getParent().getParent().getParent()).setClipChildren(r2);
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r11v2 */
        /* JADX WARN: Type inference failed for: r11v3, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r11v8 */
        /* JADX WARN: Type inference failed for: r1v48, types: [boolean] */
        /* JADX WARN: Type inference failed for: r1v59 */
        /* JADX WARN: Type inference failed for: r4v12 */
        /* JADX WARN: Type inference failed for: r4v13 */
        /* JADX WARN: Type inference failed for: r4v54 */
        /* JADX WARN: Type inference failed for: r5v26, types: [org.telegram.ui.Components.RadialProgress] */
        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r25) {
            float r1 = AndroidUtilities.dp(48.0f);
            float r2 = AndroidUtilities.dp(26.33f);
            float r3 = AndroidUtilities.dp(8.0f) * Utilities.clamp(DialogStoriesCell.m19313$$Nest$fgetoverscrollProgress(this.this$0) / 0.5f, 1.0f, 0.0f);
            if (this.selectedForOverscroll == false) goto L5;
            r3 = r3 + (AndroidUtilities.dp(16.0f) * Utilities.clamp((DialogStoriesCell.m19313$$Nest$fgetoverscrollProgress(this.this$0) - 0.5f) / 0.5f, 1.0f, 0.0f));
        L5:
            float r12 = AndroidUtilities.lerp(r1 + r3, r2, this.progressToCollapsed);
            float r14 = r12 / 2.0f;
            float r15 = (getMeasuredWidth() / 2.0f) - r14;
            float r13 = AndroidUtilities.lerp(r15, 0.0f, this.progressToCollapsed);
            float r22 = AndroidUtilities.lerp(AndroidUtilities.dp(5.0f), (ActionBar.getCurrentActionBarHeight() - r2) / 2.0f, this.progressToCollapsed);
            float r16 = Utilities.clamp(this.progressToCollapsed / 0.5f, 1.0f, 0.0f);
            StoriesUtilities.AvatarStoryParams r32 = this.params;
            r32.drawSegments = true;
            if (r32.forceAnimateProgressToSegments == true) goto L8;
            r32.progressToSegments = 1.0f - DialogStoriesCell.m19308$$Nest$fgetcollapsedProgress2(this.this$0);
        L8:
            float r6 = r22 + r12;
            this.params.originalAvatarRect.set(r13, r22, r13 + r12, r6);
            this.params.additionalInset = AndroidUtilities.dpf2(1.33f) * this.progressToCollapsed;
            this.avatarImage.setAlpha(1.0f);
            this.avatarImage.setRoundRadius(ExteraConfig.getAvatarCorners(r12, true));
            float r33 = r13 + r14;
            this.cx = r33;
            float r5 = r22 + r14;
            this.cy = r5;
            int r7 = DialogStoriesCell.m19317$$Nest$fgettype(this.this$0);
            DialogStoriesCell r8 = this.this$0;
            if (r7 != 0) goto L11;
            float r17 = 16.0f;
            r8.backgroundPaint.setColor(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r8, Theme.key_actionBarDefault));
        L13:
            if (this.progressToCollapsed == 0.0f) goto L15;
            float r82 = AndroidUtilities.dpf2(1.5f) + r14;
            float r11 = ExteraConfig.getAvatarCorners(r14 * 2.0f, true) + AndroidUtilities.dpf2(1.5f);
            float r72 = this.cx;
            float r4 = this.cy;
            float r20 = 1.0f;
            float r9 = r33;
            float r19 = r6;
            float r222 = r22;
            float r23 = r5;
            ?? r112 = 1;
            float r18 = 2.0f;
            float r132 = r13;
            r25.drawRoundRect(r72 - r82, r4 - r82, r72 + r82, r4 + r82, r11, r11, this.this$0.backgroundPaint);
            Canvas r34 = r25;
        L16:
            r34.save();
            float r110 = this.bounceScale;
            r34.scale(r110, r110, this.cx, this.cy);
            if (this.radialProgress != null) goto L19;
            this.radialProgress = this.this$0.radialProgress;
        L19:
            ArrayList<StoriesController.UploadingStory> r111 = this.this$0.storiesController.getUploadingAndEditingStories(this.dialogId);
            int r73 = 0;
            if (r111 != null) goto L22;
        L24:
            ?? r42 = false;
        L26:
            if (r42 == false) goto L28;
        L57:
            if (r42 == true) goto L59;
            boolean r113 = DialogStoriesCell.m19311$$Nest$fgetlastUploadingCloseFriends(this.this$0);
            float r24 = 1.0f;
        L64:
            invalidate();
            if (this.radialProgress != null) goto L71;
            DialogStoriesCell r43 = this.this$0;
            RadialProgress r52 = r43.radialProgress;
            if (r52 == null) goto L69;
            this.radialProgress = r52;
            goto L71
        L69:
            ?? r53 = new RadialProgress(this);
            this.radialProgress = r53;
            r43.radialProgress = r53;
            r53.setBackground(null, r112, false);
        L71:
            if (this.drawAvatar == false) goto L73;
            r34.save();
            r34.scale(this.params.getScale(), this.params.getScale(), this.params.originalAvatarRect.centerX(), this.params.originalAvatarRect.centerY());
            this.avatarImage.setImageCoords(this.params.originalAvatarRect);
            this.avatarImage.draw(r34);
            r34.restore();
        L73:
            this.radialProgress.setDiff(0);
            ImageReceiver r44 = this.avatarImage;
            if (r113 == false) goto L76;
            Paint r114 = StoriesUtilities.getCloseFriendsPaint(r44);
        L77:
            r114.setAlpha(255);
            this.radialProgress.setPaint(r114);
            this.radialProgress.setProgressRect((int) (this.avatarImage.getImageX() - AndroidUtilities.dp(3.0f)), (int) (this.avatarImage.getImageY() - AndroidUtilities.dp(3.0f)), (int) (this.avatarImage.getImageX2() + AndroidUtilities.dp(3.0f)), (int) (this.avatarImage.getImageY2() + AndroidUtilities.dp(3.0f)));
            this.radialProgress.setProgress(Utilities.clamp(r24, 1.0f, 0.0f), this.progressWasDrawn);
            if (this.avatarImage.getVisible() == false) goto L80;
            this.radialProgress.draw(r34);
        L80:
            this.progressWasDrawn = r112;
            this.this$0.drawCircleForce = r112;
            invalidate();
        L81:
            r34.restore();
            if (this.crossfadeToDialog == true) goto L84;
        L86:
            this.textViewContainer.setTranslationY(r19 + (AndroidUtilities.dp(7.0f) * (1.0f - this.progressToCollapsed)));
            this.textViewContainer.setTranslationX(r132 - r15);
            if (this.mini == false) goto L89;
        L105:
            super.dispatchDraw(r25);
            return;
        L89:
            if (this.isSelf == false) goto L91;
            this.textAlpha = 1.0f;
        L100:
            float r115 = this.textAlphaTransition * this.textAlpha;
            this.textViewContainer.setAlpha(r115);
            FrameLayout r26 = this.textViewContainer;
            if (r115 > 0.0f) goto L104;
            r73 = 4;
        L104:
            r26.setVisibility(r73);
            goto L105
        L91:
            StoriesUtilities.AvatarStoryParams r116 = this.params;
            if (r116.progressToSate == 1.0f) goto L94;
            int r27 = r116.currentState;
        L96:
            if (r116.globalState != 2) goto L98;
            float r92 = 0.7f;
        L99:
            this.textAlpha = r92;
            goto L100
        L98:
            r92 = 1.0f;
            goto L99
        L94:
            int r28 = r116.currentState;
            goto L96
        L84:
            if (this.progressToCollapsed2 <= 0.0f) goto L86;
            this.crossfadeToAvatarImage.setImageCoords(r132, r222, r12, r12);
            this.crossfadeToAvatarImage.setAlpha(this.progressToCollapsed2);
            this.crossfadeToAvatarImage.draw(r34);
            goto L86
        L76:
            r114 = StoriesUtilities.getUnreadCirclePaint(r44, r112);
            goto L77
        L59:
            int r29 = 0;
            float r45 = 0.0f;
        L61:
            if (r29 >= r111.size()) goto L63;
            r45 = r45 + r111.get(r29).progress;
            r29 = r29 + 1;
            goto L61
        L63:
            r24 = (this.this$0.storiesController.uploadedStories + r45) / (r2 + r111.size());
            DialogStoriesCell r46 = this.this$0;
            r113 = r111.get(r111.size() - r112).isCloseFriends();
            DialogStoriesCell.m19323$$Nest$fputlastUploadingCloseFriends(r46, r113);
            goto L64
        L28:
            if (this.progressWasDrawn == false) goto L34;
            RadialProgress r210 = this.radialProgress;
            if (r210 == null) goto L34;
            if (r210.getAnimatedProgress() < 0.98f) goto L57;
        L34:
            float r117 = this.failT.set(this.isFail);
            if (this.drawAvatar == true) goto L37;
        L54:
            this.progressWasDrawn = false;
            if (this.drawAvatar == false) goto L81;
            r34.save();
            float r93 = r20 - r16;
            r34.scale(r93, r93, this.cx + AndroidUtilities.dp(r17), this.cy + AndroidUtilities.dp(r17));
            drawPlus(r34, this.cx, this.cy, r20);
            drawFail(r34, this.cx, this.cy, r117);
            r34.restore();
            goto L81
        L37:
            if (this.progressWasDrawn == false) goto L39;
            StoriesUtilities.AvatarStoryParams r211 = this.params;
            r211.forceAnimateProgressToSegments = r112;
            r211.progressToSegments = 0.0f;
            ValueAnimator r212 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r212.addUpdateListener(new DialogStoriesCell$StoryCell$$ExternalSyntheticLambda0(this));
            r212.addListener(new AnonymousClass2(this));
            r212.setDuration(100);
            r212.start();
        L39:
            StoriesUtilities.AvatarStoryParams r213 = this.params;
            float r21 = r117 * r213.progressToSegments;
            r213.animate = (this.progressWasDrawn ? 1 : 0) ^ r112;
            r213.progressToArc = getArcProgress(this.cx, r14);
            StoriesUtilities.AvatarStoryParams r118 = this.params;
            r118.isLast = this.isLast;
            r118.isFirst = this.isFirst;
            r118.alpha = r20 - r21;
            boolean r214 = this.isSelf;
            if (r214 == false) goto L42;
        L45:
            r118.crossfadeToDialog = 0;
        L46:
            long r119 = this.dialogId;
            if (r214 == false) goto L49;
            StoriesUtilities.drawAvatarWithStory(r119, r34, this.avatarImage, this.this$0.storiesController.hasSelfStories(), this.params);
            r34 = r25;
        L51:
            if (r21 <= 0.0f) goto L53;
            Paint r120 = StoriesUtilities.getErrorPaint(this.avatarImage);
            r120.setStrokeWidth(AndroidUtilities.dp(r18));
            r120.setAlpha((int) (255.0f * r21));
            r34.drawCircle(r9, r23, (r14 + AndroidUtilities.dp(4.0f)) * this.params.getScale(), r120);
        L53:
            r117 = r21;
            goto L54
        L49:
            r34 = r25;
            StoriesUtilities.drawAvatarWithStory(r119, r34, this.avatarImage, this.this$0.storiesController.hasStories(r119), this.params);
            goto L51
        L42:
            if (this.crossfadeToDialog == false) goto L45;
            r118.crossfadeToDialog = this.crossfadeToDialogId;
            r118.crossfadeToDialogProgress = this.progressToCollapsed2;
            goto L46
        L22:
            if (r111.isEmpty() == true) goto L24;
            r42 = r112;
            goto L26
        L15:
            r222 = r22;
            r112 = 1;
            r23 = r5;
            r19 = r6;
            r20 = 1.0f;
            r18 = 2.0f;
            r132 = r13;
            r9 = r33;
            r34 = r25;
            goto L16
        L11:
            r17 = 16.0f;
            r8.backgroundPaint.setColor(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r8, Theme.key_actionBarDefaultArchived));
            goto L13
        }

        public void drawFail(Canvas r7, float r8, float r9, float r10) {
            if (r10 > 0.0f) goto L5;
            return;
        L5:
            float r82 = r8 + AndroidUtilities.dp(17.0f);
            float r92 = r9 + AndroidUtilities.dp(17.0f);
            DialogStoriesCell r0 = this.this$0;
            r0.addCirclePaint.setColor(Theme.multAlpha(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r0, Theme.key_text_RedBold), r10));
            int r02 = DialogStoriesCell.m19317$$Nest$fgettype(this.this$0);
            DialogStoriesCell r1 = this.this$0;
            if (r02 != 0) goto L8;
            r1.backgroundPaint.setColor(Theme.multAlpha(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r1, Theme.key_actionBarDefault), r10));
        L9:
            float r03 = AndroidUtilities.dp(9.0f) * CubicBezierInterpolator.EASE_OUT_BACK.getInterpolation(r10);
            r7.drawCircle(r82, r92, AndroidUtilities.dp(2.0f) + r03, this.this$0.backgroundPaint);
            r7.drawCircle(r82, r92, r03, this.this$0.addCirclePaint);
            DialogStoriesCell r04 = this.this$0;
            r04.addCirclePaint.setColor(Theme.multAlpha(DialogStoriesCell.m19331$$Nest$mgetTextColor(r04), r10));
            RectF r102 = AndroidUtilities.rectTmp;
            r102.set(r82 - AndroidUtilities.dp(1.0f), r92 - AndroidUtilities.dpf2(4.6f), AndroidUtilities.dp(1.0f) + r82, AndroidUtilities.dpf2(1.6f) + r92);
            r7.drawRoundRect(r102, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.this$0.addCirclePaint);
            r102.set(r82 - AndroidUtilities.dp(1.0f), AndroidUtilities.dpf2(2.6f) + r92, r82 + AndroidUtilities.dp(1.0f), r92 + AndroidUtilities.dpf2(4.6f));
            r7.drawRoundRect(r102, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.this$0.addCirclePaint);
            return;
        L8:
            r1.backgroundPaint.setColor(Theme.multAlpha(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r1, Theme.key_actionBarDefaultArchived), r10));
            goto L9
        }

        public void drawPlus(Canvas r5, float r6, float r7, float r8) {
            if (this.isSelf == true) goto L5;
            return;
        L5:
            if (this.this$0.storiesController.hasStories(this.dialogId) == false) goto L7;
            return;
        L7:
            if (Utilities.isNullOrEmpty(this.this$0.storiesController.getUploadingStories(this.dialogId)) == false) goto L25;
            float r62 = r6 + AndroidUtilities.dp(16.0f);
            float r72 = r7 + AndroidUtilities.dp(16.0f);
            DialogStoriesCell r0 = this.this$0;
            r0.addCirclePaint.setColor(Theme.multAlpha(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r0, Theme.key_telegram_color), r8));
            int r02 = DialogStoriesCell.m19317$$Nest$fgettype(this.this$0);
            DialogStoriesCell r1 = this.this$0;
            if (r02 != 0) goto L12;
            r1.backgroundPaint.setColor(Theme.multAlpha(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r1, Theme.key_actionBarDefault), r8));
        L13:
            r5.drawCircle(r62, r72, AndroidUtilities.dp(11.0f), this.this$0.backgroundPaint);
            r5.drawCircle(r62, r72, AndroidUtilities.dp(9.0f), this.this$0.addCirclePaint);
            int r03 = DialogStoriesCell.m19317$$Nest$fgettype(this.this$0);
            DialogStoriesCell r12 = this.this$0;
            if (r03 != 0) goto L17;
            int r04 = Theme.key_actionBarDefault;
        L16:
            int r05 = DialogStoriesCell.m19332$$Nest$mgetThemedColor(r12, r04);
            if (r05 == DialogStoriesCell.m19305$$Nest$fgetaddNewStoryLastColor(this.this$0)) goto L21;
            Drawable r13 = DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0);
            DialogStoriesCell.m19318$$Nest$fputaddNewStoryLastColor(this.this$0, r05);
            r13.setColorFilter(new PorterDuffColorFilter(r05, PorterDuff.Mode.MULTIPLY));
        L21:
            DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0).setAlpha((int) (r8 * 255.0f));
            DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0).setBounds((int) (r62 - (DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0).getIntrinsicWidth() / 2.0f)), (int) (r72 - (DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0).getIntrinsicHeight() / 2.0f)), (int) (r62 + (DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0).getIntrinsicWidth() / 2.0f)), (int) (r72 + (DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0).getIntrinsicHeight() / 2.0f)));
            DialogStoriesCell.m19304$$Nest$fgetaddNewStoryDrawable(this.this$0).draw(r5);
            return;
        L17:
            r04 = Theme.key_actionBarDefaultArchived;
            goto L16
        L12:
            r1.backgroundPaint.setColor(Theme.multAlpha(DialogStoriesCell.m19332$$Nest$mgetThemedColor(r1, Theme.key_actionBarDefaultArchived), r8));
            goto L13
        }

        public float getCy() {
            float r0 = AndroidUtilities.dp(48.0f);
            float r1 = AndroidUtilities.dp(26.33f);
            return AndroidUtilities.lerp(AndroidUtilities.dp(5.0f), (ActionBar.getCurrentActionBarHeight() - r1) / 2.0f, DialogStoriesCell.m19307$$Nest$fgetcollapsedProgress1(this.this$0)) + (AndroidUtilities.lerp(r0, r1, this.progressToCollapsed) / 2.0f);
        }

        @Override // android.view.View
        public void invalidate() {
            if (this.mini == false) goto L5;
        L8:
            ViewParent r0 = getParent();
            DialogStoriesCell r1 = this.this$0;
            RecyclerListView r2 = r1.listViewMini;
            if (r0 != r2) goto L11;
            r2.invalidate();
        L12:
            super.invalidate();
            return;
        L11:
            r1.invalidate();
            goto L12
        L5:
            if (this.drawInParent == false) goto L12;
            if (getParent() == null) goto L12;
            goto L8
        }

        @Override // android.view.View
        public void invalidate(int r3, int r4, int r5, int r6) {
            if (this.mini == false) goto L5;
        L8:
            ViewParent r0 = getParent();
            RecyclerListView r1 = this.this$0.listViewMini;
            if (r0 != r1) goto L11;
            r1.invalidate();
        L11:
            this.this$0.invalidate();
        L12:
            super.invalidate(r3, r4, r5, r6);
            return;
        L5:
            if (this.drawInParent == false) goto L12;
            if (getParent() == null) goto L12;
            goto L8
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.avatarImage.onAttachedToWindow();
            this.crossfadeToAvatarImage.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.avatarImage.onDetachedFromWindow();
            this.crossfadeToAvatarImage.onDetachedFromWindow();
            this.params.onDetachFromWindow();
            StoriesUtilities.EnsureStoryFileLoadedObject r0 = this.cancellable;
            if (r0 == null) goto L6;
            r0.cancel();
            this.cancellable = null;
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            if (this.mini == false) goto L5;
            int r22 = AndroidUtilities.dp(70.0f);
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(81.0f), TLObject.FLAG_30));
            return;
        L5:
            r22 = this.this$0.currentCellWidth;
            goto L6
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setCrossfadeTo(long r4) {
            if (this.crossfadeToDialogId == r4) goto L19;
            this.crossfadeToDialogId = r4;
            if (r4 == (-1)) goto L7;
            boolean r0 = true;
        L8:
            this.crossfadeToDialog = r0;
            if (r0 == false) goto L17;
            DialogStoriesCell r1 = this.this$0;
            if (r4 <= 0) goto L13;
            TLRPC.User r42 = MessagesController.getInstance(r1.currentAccount).getUser(Long.valueOf(r4));
            this.user = r42;
            this.chat = null;
            TLRPC.Chat r43 = r42;
        L14:
            if (r43 == null) goto L20;
            this.crossfadeAvatarDrawable.setInfo(this.this$0.currentAccount, r43);
            this.crossfadeToAvatarImage.setForUserOrChat(r43, this.crossfadeAvatarDrawable);
            return;
        L20:
            return;
        L13:
            TLRPC.Chat r44 = MessagesController.getInstance(r1.currentAccount).getChat(Long.valueOf(-r4));
            this.chat = r44;
            this.user = null;
            r43 = r44;
            goto L14
        L17:
            this.crossfadeToAvatarImage.clearImage();
            return;
        L7:
            r0 = false;
            goto L8
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setDialogId(long r9) {
            long r0 = this.dialogId;
            if (r0 != r9) goto L5;
            boolean r2 = true;
        L7:
            if (r2 == false) goto L9;
        L11:
            this.dialogId = r9;
            if (r9 != UserConfig.getInstance(this.this$0.currentAccount).getClientUserId()) goto L14;
            boolean r02 = true;
        L15:
            this.isSelf = r02;
            this.isFail = this.this$0.storiesController.isLastUploadingFailed(r9);
            DialogStoriesCell r1 = this.this$0;
            if (r9 <= 0) goto L18;
            TLRPC.User r03 = MessagesController.getInstance(r1.currentAccount).getUser(Long.valueOf(r9));
            this.user = r03;
            this.chat = null;
            TLRPC.Chat r04 = r03;
        L19:
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r04 != null) goto L23;
            this.textView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.avatarImage.clearImage();
            return;
        L23:
            this.avatarDrawable.setInfo(this.this$0.currentAccount, r04);
            this.avatarImage.setForUserOrChat(r04, this.avatarDrawable);
            if (this.mini == false) goto L26;
            return;
        L26:
            this.textView.setRightDrawable(null);
            if (this.this$0.storiesController.isLastUploadingFailed(r9) == false) goto L31;
            this.textView.setTextSize(10);
            this.textView.setText(LocaleController.getString(R.string.FailedStory));
            this.isUploadingState = false;
            return;
        L31:
            if (Utilities.isNullOrEmpty(this.this$0.storiesController.getUploadingStories(r9)) == true) goto L35;
            this.textView.setTextSize(10);
            StoriesUtilities.applyUploadingStr(this.textView, true, false);
            this.isUploadingState = true;
            return;
        L35:
            if (this.this$0.storiesController.getEditingStory(r9) == null) goto L39;
            this.textView.setTextSize(10);
            StoriesUtilities.applyUploadingStr(this.textView, true, false);
            this.isUploadingState = true;
            return;
        L39:
            if (this.isSelf == false) goto L59;
            if (r2 == true) goto L42;
        L49:
            AndroidUtilities.runOnUIThread(DialogStoriesCell.m19306$$Nest$fgetanimationRunnable(this.this$0), 500);
            this.isUploadingState = false;
            if (DialogStoriesCell.m19317$$Nest$fgettype(this.this$0) != 1) goto L57;
            TL_stories.PeerStories r92 = this.this$0.storiesController.getStories(r9);
            if (r92 == null) goto L54;
            int r93 = r92.stories.size();
        L55:
            this.textView.setTextSize(11);
            this.textView.setText(LocaleController.formatPluralString("Stories", Math.max(1, r93), new Object[0]));
            return;
        L54:
            r93 = 1;
            goto L55
        L57:
            this.textView.setTextSize(10);
            this.textView.setText(LocaleController.getString(R.string.MyStory));
            return;
        L42:
            if (this.isUploadingState == false) goto L49;
            if (this.mini == true) goto L49;
            SimpleTextView r05 = this.textView;
            createTextView();
            if (DialogStoriesCell.m19315$$Nest$fgettextAnimator(this.this$0) == null) goto L48;
            DialogStoriesCell.m19315$$Nest$fgettextAnimator(this.this$0).cancel();
            DialogStoriesCell.m19325$$Nest$fputtextAnimator(this.this$0, null);
        L48:
            DialogStoriesCell.m19325$$Nest$fputtextAnimator(this.this$0, ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
            DialogStoriesCell.m19315$$Nest$fgettextAnimator(this.this$0).addUpdateListener(new DialogStoriesCell$StoryCell$$ExternalSyntheticLambda1(this, r05));
            DialogStoriesCell.m19315$$Nest$fgettextAnimator(this.this$0).addListener(new AnonymousClass1(this, r05));
            DialogStoriesCell.m19315$$Nest$fgettextAnimator(this.this$0).setDuration(150);
            this.textView.setAlpha(0.0f);
            this.textView.setTranslationY(AndroidUtilities.dp(5.0f));
            DialogStoriesCell.m19319$$Nest$fputanimationRunnable(this.this$0, new DialogStoriesCell$StoryCell$$ExternalSyntheticLambda2(this));
            goto L49
        L59:
            TLRPC.User r94 = this.user;
            SimpleTextView r10 = this.textView;
            if (r94 == null) goto L77;
            r10.setTextSize(11);
            String r95 = this.user.first_name;
            if (r95 == null) goto L65;
            r12 = r95.trim();
        L65:
            int r96 = r12.indexOf(" ");
            if (r96 <= 0) goto L69;
            r12 = r12.substring(0, r96);
        L69:
            if (this.user.verified == true) goto L71;
            this.textView.setText(Emoji.replaceEmoji(r12, this.textView.getPaint().getFontMetricsInt(), false));
            this.textView.setRightDrawable(null);
            return;
        L71:
            if (this.verifiedDrawable != null) goto L73;
            this.verifiedDrawable = DialogStoriesCell.m19330$$Nest$mcreateVerifiedDrawable(this.this$0);
        L73:
            this.textView.setText(Emoji.replaceEmoji(r12, this.textView.getPaint().getFontMetricsInt(), false));
            this.textView.setRightDrawable(this.verifiedDrawable);
            return;
        L77:
            r10.setTextSize(11);
            this.textView.setText(Emoji.replaceEmoji(this.chat.title, this.textView.getPaint().getFontMetricsInt(), false));
            this.textView.setRightDrawable(null);
            return;
        L18:
            TLRPC.Chat r06 = MessagesController.getInstance(r1.currentAccount).getChat(Long.valueOf(-r9));
            this.chat = r06;
            this.user = null;
            r04 = r06;
            goto L19
        L14:
            r02 = false;
            goto L15
        L9:
            if (this.cancellable == null) goto L11;
            this.this$0.storiesController.setLoading(r0, false);
            this.cancellable.cancel();
            this.cancellable = null;
            goto L11
        L5:
            r2 = false;
            goto L7
        }

        @Override // android.view.View
        public void setPressed(boolean r5) {
            super.setPressed(r5);
            if (r5 == false) goto L7;
            StoriesUtilities.AvatarStoryParams r0 = this.params;
            if (r0.buttonBounce != null) goto L7;
            r0.buttonBounce = new ButtonBounce(this, 1.5f, 5.0f);
        L7:
            ButtonBounce r4 = this.params.buttonBounce;
            if (r4 == null) goto L11;
            r4.setPressed(r5);
            return;
        }

        public void setProgressToCollapsed(float r2, float r3, float r4, boolean r5) {
            if (this.progressToCollapsed == r2) goto L5;
        L10:
            this.selectedForOverscroll = r5;
            this.progressToCollapsed = r2;
            this.progressToCollapsed2 = r3;
            invalidate();
            this.this$0.recyclerListView.invalidate();
        L12:
            if (this.mini == false) goto L14;
            float r22 = 0.0f;
        L15:
            this.textAlphaTransition = r22;
            float r23 = r22 * this.textAlpha;
            this.textViewContainer.setAlpha(r23);
            FrameLayout r1 = this.textViewContainer;
            if (r23 <= 0.0f) goto L18;
            int r24 = 0;
        L19:
            r1.setVisibility(r24);
            return;
        L18:
            r24 = 4;
            goto L19
        L14:
            DialogStoriesCell r25 = this.this$0;
            r22 = 1.0f - Utilities.clamp(r25.collapsedProgress / r25.K, 1.0f, 0.0f);
            goto L15
        L5:
            if (this.progressToCollapsed2 != r3) goto L10;
            if (this.overscrollProgress != r4) goto L10;
            if (this.selectedForOverscroll == r5) goto L12;
            goto L10
        }
    }

    public static /* synthetic */ void $r8$lambda$2z64GguTSO1WRgpTsWiS7KloF1E(DialogStoriesCell r0, ValueAnimator r1) {
        r0.lambda$setProgressToCollapse$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$3NwAwpqU94ribSfRKDAWQ_nGMf8(DialogStoriesCell r0, ValueAnimator r1) {
        r0.lambda$setDialogsTitleOverride$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$4W4OecZDUaKRpar20MNqQyM5r2U(DialogStoriesCell r0, AlertDialog r1, long r2, StoryCell r4, Boolean r5) {
        r0.lambda$openStoryRecorder$14(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$4qDfdpLsGpFYccN6ALAdRCojpec(DialogStoriesCell r0) {
        r0.lambda$updateCurrentState$16();
    }

    public static /* synthetic */ void $r8$lambda$6Xb4MOL1QEiTCTXvrGPyJTOBHPI(View r0) {
        ((StoryCell) r0).invalidate();
    }

    /* renamed from: $r8$lambda$8-GvsAUzMsSWlUcB1fF2LH8YhfA, reason: not valid java name */
    public static /* synthetic */ void m19297$r8$lambda$8GvsAUzMsSWlUcB1fF2LH8YhfA(View r1) {
        r1.setTranslationY(0.0f);
    }

    /* renamed from: $r8$lambda$8nes5Xgry-HpHmlGkxGGWqgzwJQ, reason: not valid java name */
    public static /* synthetic */ boolean m19298$r8$lambda$8nes5XgryHpHmlGkxGGWqgzwJQ(DialogStoriesCell r0, View r1, int r2) {
        return r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$Bb9TAUkCQfAti7J-EgbNxiWZfg0, reason: not valid java name */
    public static /* synthetic */ void m19299$r8$lambda$Bb9TAUkCQfAti7JEgbNxiWZfg0(DialogStoriesCell r0, boolean r1, boolean r2) {
        r0.lambda$openStoryForCell$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GG4Q1vron03zbe2MLK2tOjNnE2w(DialogStoriesCell r0, ValueAnimator r1) {
        r0.lambda$setProgressToCollapse$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$HQIOqQ7gHGVpkq66OoGB1qqrst0(DialogStoriesCell r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$IWjVIGjdLLZg21YCLA-7rv7YqUo, reason: not valid java name */
    public static /* synthetic */ void m19300$r8$lambda$IWjVIGjdLLZg21YCLA7rv7YqUo(DialogStoriesCell r0, long r1) {
        r0.lambda$openStoryForCell$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$IvQRzivuQCnsoLOlsoxdzVmX7_E(DialogStoriesCell r0, ValueAnimator r1) {
        r0.lambda$setProgressToCollapse$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$JU35FNtrFzbGb3io22HLzqPub2Y(DialogStoriesCell r0, StoryCell r1, long r2) {
        r0.lambda$openStoryForCell$5(r1, r2);
    }

    /* renamed from: $r8$lambda$N45wOmFrj2-J342mieW92R6ts2c, reason: not valid java name */
    public static /* synthetic */ void m19301$r8$lambda$N45wOmFrj2J342mieW92R6ts2c(View r1) {
        r1.setAlpha(1.0f);
        r1.setTranslationX(0.0f);
        r1.setTranslationY(0.0f);
    }

    /* renamed from: $r8$lambda$NrhGsZxg09iUAB-w-bMrBhjzt4g, reason: not valid java name */
    public static /* synthetic */ void m19302$r8$lambda$NrhGsZxg09iUABwbMrBhjzt4g(DialogStoriesCell r0) {
        r0.lambda$makePremiumHint$18();
    }

    public static /* synthetic */ void $r8$lambda$YHyYfzeD7yIG3DqAFSFxFKhk1IM(DialogStoriesCell r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$lAPtwsOpwv1uI1hYpscDS2WX-QQ, reason: not valid java name */
    public static /* synthetic */ void m19303$r8$lambda$lAPtwsOpwv1uI1hYpscDS2WXQQ(DialogStoriesCell r0, ValueAnimator r1) {
        r0.lambda$setProgressToCollapse$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$lVHczz4QeRYnsXttpU5Plx0C_pQ(DialogStoriesCell r0) {
        r0.checkLoadMore();
    }

    public static /* synthetic */ int $r8$lambda$mrKyDE7kqyAfP266l3FH_xwibtA(StoryCell r0, StoryCell r1) {
        return r1.position - r0.position;
    }

    public static /* synthetic */ void $r8$lambda$uroSyU8IkRJvSP88YfdmcDBBKCI(int r0, View r1) {
        StoryCell r12 = (StoryCell) r1;
        r12.invalidate();
        r12.textView.setTextColor(r0);
    }

    /* renamed from: -$$Nest$fgetaddNewStoryDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m19304$$Nest$fgetaddNewStoryDrawable(DialogStoriesCell r0) {
        return r0.addNewStoryDrawable;
    }

    /* renamed from: -$$Nest$fgetaddNewStoryLastColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19305$$Nest$fgetaddNewStoryLastColor(DialogStoriesCell r0) {
        return r0.addNewStoryLastColor;
    }

    /* renamed from: -$$Nest$fgetanimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m19306$$Nest$fgetanimationRunnable(DialogStoriesCell r0) {
        return r0.animationRunnable;
    }

    /* renamed from: -$$Nest$fgetcollapsedProgress1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19307$$Nest$fgetcollapsedProgress1(DialogStoriesCell r0) {
        return r0.collapsedProgress1;
    }

    /* renamed from: -$$Nest$fgetcollapsedProgress2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19308$$Nest$fgetcollapsedProgress2(DialogStoriesCell r0) {
        return r0.collapsedProgress2;
    }

    /* renamed from: -$$Nest$fgetcollapsedSpringCoef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19309$$Nest$fgetcollapsedSpringCoef(DialogStoriesCell r0) {
        return r0.collapsedSpringCoef;
    }

    /* renamed from: -$$Nest$fgetexpandedSpringCoef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19310$$Nest$fgetexpandedSpringCoef(DialogStoriesCell r0) {
        return r0.expandedSpringCoef;
    }

    /* renamed from: -$$Nest$fgetlastUploadingCloseFriends, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19311$$Nest$fgetlastUploadingCloseFriends(DialogStoriesCell r0) {
        return r0.lastUploadingCloseFriends;
    }

    /* renamed from: -$$Nest$fgetoverScrollCoef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19312$$Nest$fgetoverScrollCoef(DialogStoriesCell r0) {
        return r0.overScrollCoef;
    }

    /* renamed from: -$$Nest$fgetoverscrollProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19313$$Nest$fgetoverscrollProgress(DialogStoriesCell r0) {
        return r0.overscrollProgress;
    }

    /* renamed from: -$$Nest$fgetpremiumHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HintView2 m19314$$Nest$fgetpremiumHint(DialogStoriesCell r0) {
        return r0.premiumHint;
    }

    /* renamed from: -$$Nest$fgettextAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m19315$$Nest$fgettextAnimator(DialogStoriesCell r0) {
        return r0.textAnimator;
    }

    /* renamed from: -$$Nest$fgettitleOverrideAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m19316$$Nest$fgettitleOverrideAnimator(DialogStoriesCell r0) {
        return r0.titleOverrideAnimator;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19317$$Nest$fgettype(DialogStoriesCell r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fputaddNewStoryLastColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19318$$Nest$fputaddNewStoryLastColor(DialogStoriesCell r0, int r1) {
        r0.addNewStoryLastColor = r1;
    }

    /* renamed from: -$$Nest$fputanimationRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19319$$Nest$fputanimationRunnable(DialogStoriesCell r0, Runnable r1) {
        r0.animationRunnable = r1;
    }

    /* renamed from: -$$Nest$fputcollapsedProgress2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19320$$Nest$fputcollapsedProgress2(DialogStoriesCell r0, float r1) {
        r0.collapsedProgress2 = r1;
    }

    /* renamed from: -$$Nest$fputcollapsedSpringCoef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19321$$Nest$fputcollapsedSpringCoef(DialogStoriesCell r0, float r1) {
        r0.collapsedSpringCoef = r1;
    }

    /* renamed from: -$$Nest$fputexpandedSpringCoef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19322$$Nest$fputexpandedSpringCoef(DialogStoriesCell r0, float r1) {
        r0.expandedSpringCoef = r1;
    }

    /* renamed from: -$$Nest$fputlastUploadingCloseFriends, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19323$$Nest$fputlastUploadingCloseFriends(DialogStoriesCell r0, boolean r1) {
        r0.lastUploadingCloseFriends = r1;
    }

    /* renamed from: -$$Nest$fputoverScrollCoef, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19324$$Nest$fputoverScrollCoef(DialogStoriesCell r0, float r1) {
        r0.overScrollCoef = r1;
    }

    /* renamed from: -$$Nest$fputtextAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19325$$Nest$fputtextAnimator(DialogStoriesCell r0, ValueAnimator r1) {
        r0.textAnimator = r1;
    }

    /* renamed from: -$$Nest$fputtitleOverrideAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19326$$Nest$fputtitleOverrideAnimator(DialogStoriesCell r0, ValueAnimator r1) {
        r0.titleOverrideAnimator = r1;
    }

    /* renamed from: -$$Nest$fputtitleOverrideProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19327$$Nest$fputtitleOverrideProgress(DialogStoriesCell r0, float r1) {
        r0.titleOverrideProgress = r1;
    }

    /* renamed from: -$$Nest$mcheckCollapsedProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19328$$Nest$mcheckCollapsedProgress(DialogStoriesCell r0) {
        r0.checkCollapsedProgress();
    }

    /* renamed from: -$$Nest$mcheckLoadMore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19329$$Nest$mcheckLoadMore(DialogStoriesCell r0) {
        r0.checkLoadMore();
    }

    /* renamed from: -$$Nest$mcreateVerifiedDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m19330$$Nest$mcreateVerifiedDrawable(DialogStoriesCell r0) {
        return r0.createVerifiedDrawable();
    }

    /* renamed from: -$$Nest$mgetTextColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19331$$Nest$mgetTextColor(DialogStoriesCell r0) {
        return r0.getTextColor();
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19332$$Nest$mgetThemedColor(DialogStoriesCell r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public DialogStoriesCell(Context r12, BaseFragment r13, int r14, int r15) {
        super(r12);
        this.animatorHasTitleText = new BoolAnimator(1, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.oldItems = new ArrayList();
        this.oldMiniItems = new ArrayList();
        this.items = new ArrayList();
        this.miniItems = new ArrayList();
        this.adapter = new Adapter(this, false);
        this.miniAdapter = new Adapter(this, true);
        this.grayPaint = new Paint();
        this.addCirclePaint = new Paint(1);
        this.backgroundPaint = new Paint(1);
        this.miniItemsClickArea = new CanvasButton(this);
        this.collapsedProgress = -1.0f;
        this.currentState = -1;
        this.viewsDrawInParent = new ArrayList();
        this.animateToDialogIds = new ArrayList();
        this.afterNextLayout = new ArrayList();
        this.collapsedProgress1 = -1.0f;
        this.titleOverrideProgress = 1.0f;
        this.titleOverrideForward = true;
        this.allowGlobalUpdates = true;
        this.dialogsEmojiStatusVisible = true;
        this.dialogsEmojiStatusVisibleOut = true;
        this.overScrollCoef = 1.0f;
        this.collapsedSpringCoef = 0.95f;
        this.expandedSpringCoef = 0.9f;
        this.comparator = new DialogStoriesCell$$ExternalSyntheticLambda7();
        this.K = 0.3f;
        this.collapsedOvershootProgress = 1.0f;
        this.storiesExpandInterpolator = new OvershootInterpolator(this.expandedSpringCoef);
        this.storiesCollapseInterpolator = new OvershootInterpolator(this.collapsedSpringCoef);
        this.ellipsizeSpanAnimator = new EllipsizeSpanAnimator(this);
        this.type = r15;
        this.currentAccount = r14;
        this.fragment = r13;
        this.menuItemsOffset = AndroidUtilities.dp(68.0f);
        this.storiesController = MessagesController.getInstance(r14).getStoriesController();
        AnonymousClass1 r132 = new AnonymousClass1(this, r12);
        this.recyclerListView = r132;
        r132.setPadding(AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f), 0);
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setClipChildren(false);
        this.miniItemsClickArea.setDelegate(new DialogStoriesCell$$ExternalSyntheticLambda8(this));
        this.miniItemsClickArea.setLongPress(new DialogStoriesCell$$ExternalSyntheticLambda9(this));
        this.recyclerListView.addOnScrollListener(new AnonymousClass5(this));
        DefaultItemAnimator r133 = new DefaultItemAnimator();
        this.itemAnimator = r133;
        r133.setDelayAnimations(false);
        this.itemAnimator.setDurations(150);
        this.itemAnimator.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(this.itemAnimator);
        RecyclerListView r134 = this.recyclerListView;
        LinearLayoutManager r142 = new LinearLayoutManager(r12, 0, false);
        this.layoutManager = r142;
        r134.setLayoutManager(r142);
        this.recyclerListView.setOnItemClickListener(new DialogStoriesCell$$ExternalSyntheticLambda10(this));
        this.recyclerListView.setOnItemLongClickListener(new DialogStoriesCell$$ExternalSyntheticLambda11(this));
        this.recyclerListView.setAdapter(this.adapter);
        addView(this.recyclerListView, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 4.0f, 0.0f, 0.0f));
        AnimatedTextView r135 = createTitleView();
        this.titleViewOut = r135;
        r135.setVisibility(8);
        addView(this.titleViewOut, LayoutHelper.createFrame(-1, -2.0f));
        AnimatedTextView r136 = createTitleView();
        this.titleView = r136;
        addView(r136, LayoutHelper.createFrame(-1, -2.0f));
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r137 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(null, AndroidUtilities.dp(26.0f));
        this.statusDrawable = r137;
        r137.center = true;
        r137.setCallback(this);
        ImageView r138 = new ImageView(r12);
        this.emojiStatusView = r138;
        r138.setScaleType(ImageView.ScaleType.CENTER);
        this.emojiStatusView.setImageDrawable(this.statusDrawable);
        addView(this.emojiStatusView, LayoutHelper.createFrame(40, 40.0f));
        AnonymousClass6 r139 = new AnonymousClass6(this, r12, null, this.ellipsizeSpanAnimator);
        this.subtitleOverlayContainer = r139;
        addView(r139, LayoutHelper.createFrame(-2, -2.0f));
        this.grayPaint.setColor(-2762018);
        this.grayPaint.setStyle(Paint.Style.STROKE);
        this.grayPaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.addNewStoryDrawable = ContextCompat.getDrawable(getContext(), R.drawable.msg_mini_addstory);
        AnonymousClass7 r11 = new AnonymousClass7(this, getContext());
        this.listViewMini = r11;
        r11.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.listViewMini.addItemDecoration(new AnonymousClass8(this));
        AnonymousClass9 r112 = new AnonymousClass9(this);
        this.miniItemAnimator = r112;
        r112.setDelayAnimations(false);
        r112.setSupportsChangeAnimations(false);
        this.listViewMini.setItemAnimator(r112);
        this.listViewMini.setAdapter(this.miniAdapter);
        this.listViewMini.setClipChildren(false);
        addView(this.listViewMini, LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 4.0f, 0.0f, 0.0f));
        setClipChildren(false);
        setClipToPadding(false);
        checkUi_titleVisibility();
        updateItems(false, false);
    }

    private void cancelTitleOverrideAnimation() {
        ValueAnimator r0 = this.titleOverrideAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.titleOverrideAnimator = null;
    L5:
        this.titleOverrideProgress = 1.0f;
        AnimatedTextView r02 = this.titleViewOut;
        if (r02 == null) goto L9;
        r02.setText(null, false);
        this.titleViewOut.setAlpha(0.0f);
        this.titleViewOut.setVisibility(8);
        return;
    }

    private void checkCollapsedProgress() {
        this.collapsedProgress = 1.0f - AndroidUtilities.lerp(1.0f - this.collapsedProgress1, 1.0f, 1.0f - this.collapsedProgress2);
        checkUi_titleVisibility();
        float r0 = this.collapsedProgress;
        if (r0 != 1.0f) goto L6;
        int r02 = 2;
    L9:
        updateCurrentState(r02);
        invalidate();
        return;
    L6:
        if (r0 == 0.0f) goto L8;
        r02 = 1;
        goto L9
    L8:
        r02 = 0;
        goto L9
    }

    private void checkExpanded() {
        if (System.currentTimeMillis() >= this.checkedStoryNotificationDeletion) goto L5;
        return;
    L5:
        this.checkedStoryNotificationDeletion = System.currentTimeMillis() + 60000;
    }

    private void checkLoadMore() {
        if ((this.layoutManager.findLastVisibleItemPosition() + 10) <= this.items.size()) goto L5;
    L8:
        boolean r1 = true;
        if (this.type == 1) goto L12;
        r1 = false;
    L12:
        this.storiesController.loadNextStories(r1);
        return;
    L5:
        if (isReadAtPosition(this.layoutManager.findLastVisibleItemPosition() + 9) == true) goto L8;
    }

    private void checkUi_titleVisibility() {
        float r0 = MathUtils.clamp(Math.min(this.collapsedProgress, this.collapsedProgress2), 0.0f, 1.0f);
        AnimatedTextView r1 = this.titleView;
        int r3 = 8;
        if (r1 == null) goto L9;
        r1.setAlpha(r0);
        AnimatedTextView r12 = this.titleView;
        if (r0 <= 0.0f) goto L7;
        int r5 = 0;
    L8:
        r12.setVisibility(r5);
        goto L9
    L7:
        r5 = 8;
    L9:
        AnimatedTextView r13 = this.titleViewOut;
        if (r13 != null) goto L12;
    L18:
        ImageView r14 = this.emojiStatusView;
        if (r14 == null) goto L25;
        r14.setAlpha(getEmojiStatusAlpha() * r0);
        ImageView r15 = this.emojiStatusView;
        if (r0 <= 0.0f) goto L23;
        int r52 = 0;
    L24:
        r15.setVisibility(r52);
        goto L25
    L23:
        r52 = 8;
    L25:
        ActionBarAnimatedSubtitleOverlayContainer r16 = this.subtitleOverlayContainer;
        if (r16 == null) goto L32;
        r16.setAlpha(r0);
        ActionBarAnimatedSubtitleOverlayContainer r6 = this.subtitleOverlayContainer;
        if (r0 <= 0.0f) goto L30;
        r3 = 0;
    L30:
        r6.setVisibility(r3);
        return;
    L32:
        return;
    L12:
        if (this.titleOverrideAnimator != null) goto L18;
        r13.setAlpha(r0);
        AnimatedTextView r17 = this.titleViewOut;
        if (r0 <= 0.0f) goto L16;
        int r53 = 0;
    L17:
        r17.setVisibility(r53);
        goto L18
    L16:
        r53 = 8;
        goto L17
    }

    private AnimatedTextView createTitleView() {
        AnimatedTextView r0 = new AnimatedTextView(getContext(), true, true, false);
        r0.setGravity(3);
        r0.setTextColor(getTextLogoColor());
        r0.setEllipsizeByGradient(true);
        r0.setTypeface(AndroidUtilities.bold());
        r0.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        if (AndroidUtilities.isTablet() == false) goto L5;
    L7:
        float r5 = 20.0f;
    L8:
        r0.setTextSize(AndroidUtilities.dp(r5));
        r0.setImportantForAccessibility(1);
        r0.setFocusableInTouchMode(true);
        return r0;
    L5:
        if (getResources().getConfiguration().orientation != 2) goto L7;
        r5 = 18.0f;
        goto L8
    }

    private Drawable createVerifiedDrawable() {
        Drawable r3 = ContextCompat.getDrawable(getContext(), R.drawable.verified_area).mutate();
        Drawable r4 = ContextCompat.getDrawable(getContext(), R.drawable.verified_check).mutate();
        AnonymousClass12 r1 = new AnonymousClass12(this, r3, r4, r3, r4);
        r1.setFullsize(true);
        return r1;
    }

    private void drawActionBarHolidayEffect(Canvas r4) {
        ActionBar r0 = this.actionBar;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getTitlesContainer() != null) goto L7;
        return;
    L7:
        if (this.actionBar.getTitlesContainer().getVisibility() == 0) goto L16;
        r4.save();
        r4.translate(this.actionBar.getX() - getX(), this.actionBar.getY() - getY());
        r4.clipRect(0, 0, this.actionBar.getMeasuredWidth(), this.actionBar.getMeasuredHeight());
        boolean r02 = this.actionBar.drawHolidayEffect(r4);
        r4.restore();
        if (r02 == false) goto L15;
        invalidate();
        return;
    L15:
        return;
    }

    public static float getAvatarRight(int r3, float r4) {
        float r0 = AndroidUtilities.lerp(AndroidUtilities.dp(48.0f), AndroidUtilities.dp(26.33f), r4) / 2.0f;
        return AndroidUtilities.lerp((r3 / 2.0f) - r0, 0.0f, r4) + (r0 * 2.0f);
    }

    private CharSequence getDialogsMainTitle() {
        if (TextUtils.isEmpty(this.dialogsTitleOverride) == true) goto L5;
        CharSequence r0 = this.dialogsTitleOverride;
    L7:
        if (TextUtils.isEmpty(r0) == false) goto L9;
        return r0;
    L9:
        return Emoji.replaceEmoji(r0, this.titleView.getPaint().getFontMetricsInt(), false);
    L5:
        r0 = LocaleUtils.getActionBarTitle();
        goto L7
    }

    private float getEmojiStatusAlpha() {
        if (this.hasOverlayText == false) goto L6;
        return 0.0f;
    L6:
        if (isEmojiStatusFadingOut() == false) goto L10;
        return 1.0f - this.titleOverrideProgress;
    L10:
        if (this.dialogsEmojiStatusVisible == true) goto L12;
        return 0.0f;
    L12:
        return this.titleOverrideProgress;
    }

    private int getTextColor() {
        if (this.type != 0) goto L7;
        return getThemedColor(Theme.key_actionBarDefaultTitle);
    L7:
        return getThemedColor(Theme.key_actionBarDefaultArchivedTitle);
    }

    private int getTextLogoColor() {
        return getThemedColor(Theme.key_telegram_color_dialogsLogo);
    }

    private int getThemedColor(int r2) {
        BaseFragment r0 = this.fragment;
        if (r0 == null) goto L10;
        if (r0.getResourceProvider() == null) goto L10;
        return this.fragment.getThemedColor(r2);
    L10:
        return Theme.getColor(r2);
    }

    private boolean isEmojiStatusFadingOut() {
        if (this.titleOverrideAnimator != null) goto L5;
        return false;
    L5:
        if (this.dialogsEmojiStatusVisible == false) goto L7;
        return false;
    L7:
        if (this.dialogsEmojiStatusVisibleOut == false) goto L13;
        return true;
    L13:
        return false;
    }

    private boolean isReadAtPosition(int r3) {
        if (r3 < this.items.size()) goto L6;
        return false;
    L6:
        if (this.storiesController.getUnreadState(this.items.get(r3).dialogId) != 0) goto L9;
        return true;
    L9:
        return false;
    }

    private /* synthetic */ void lambda$makePremiumHint$18() {
        HintView2 r0 = this.premiumHint;
        if (r0 == null) goto L5;
        r0.hide();
    L5:
        this.fragment.presentFragment(new PremiumPreviewFragment("stories"));
    }

    private /* synthetic */ void lambda$new$0() {
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L5;
        return;
    L5:
        AlertDialog.Builder r1 = new AlertDialog.Builder(getContext());
        LinearLayout r2 = new LinearLayout(getContext());
        r2.setOrientation(1);
        TextView r3 = new TextView(getContext());
        r3.setText("Screen oversrcoll: " + this.overScrollCoef);
        int r4 = Theme.key_windowBackgroundWhiteBlueText;
        r3.setTextColor(Theme.getColor(r4));
        SeekBarView r5 = new SeekBarView(getContext());
        r5.setProgress(Math.min(1.0f, Math.max(0.0f, (this.overScrollCoef - 0.2f) / 0.8f)));
        r5.setDelegate(new AnonymousClass2(this, r3));
        r2.addView(r3, LayoutHelper.createLinear(-1, 38, 0, 20, 20, 5, 0));
        r2.addView(r5, LayoutHelper.createLinear(-1, 38, 0, 5, 0, 20, 0));
        TextView r32 = new TextView(getContext());
        r32.setText("Collapsed spring: " + this.collapsedSpringCoef);
        r32.setTextColor(Theme.getColor(r4));
        r2.addView(r32, LayoutHelper.createLinear(-1, 38, 0, 20, 0, 20, 0));
        SeekBarView r52 = new SeekBarView(getContext());
        r52.setProgress((this.collapsedSpringCoef - 0.25f) / 2.25f);
        r52.setDelegate(new AnonymousClass3(this, r32));
        r2.addView(r52, LayoutHelper.createLinear(-1, 38, 0, 5, 0, 20, 0));
        TextView r33 = new TextView(getContext());
        r33.setText("Expanded X spring: " + this.expandedSpringCoef);
        r33.setTextColor(Theme.getColor(r4));
        r2.addView(r33, LayoutHelper.createLinear(-1, 38, 0, 20, 0, 20, 0));
        SeekBarView r42 = new SeekBarView(getContext());
        r42.setProgress((this.expandedSpringCoef - 0.25f) / 2.25f);
        r42.setDelegate(new AnonymousClass4(this, r33));
        r2.addView(r42, LayoutHelper.createLinear(-1, 38, 0, 5, 0, 20, 0));
        r1.setTopView(r2);
        r1.setTopViewAspectRatio(1.0f);
        r1.show();
    }

    private /* synthetic */ void lambda$new$1(View r1, int r2) {
        openStoryForCell((StoryCell) r1, false);
    }

    private /* synthetic */ boolean lambda$new$2(View r3, int r4) {
        if (this.collapsedProgress == 0.0f) goto L5;
        return false;
    L5:
        if (this.overscrollProgress != 0.0f) goto L10;
        onUserLongPressed(r3, ((StoryCell) r3).dialogId);
        return false;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$openStoryForCell$3(long r2) {
        this.storiesController.setLoading(r2, false);
    }

    private /* synthetic */ void lambda$openStoryForCell$4(boolean r1, boolean r2) {
        if (r1 == true) goto L12;
        if (r2 == false) goto L11;
        boolean r22 = true;
        if (this.type == 1) goto L9;
        r22 = false;
    L9:
        this.storiesController.loadNextStories(r22);
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$openStoryForCell$5(StoryCell r11, long r12) {
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L67;
        int r02 = r11.position;
        ArrayList<Long> r4 = new ArrayList();
        boolean r1 = false;
        int r2 = 0;
    L9:
        if (r2 >= this.items.size()) goto L16;
        long r6 = this.items.get(r2).dialogId;
        if (r6 == UserConfig.getInstance(this.currentAccount).clientUserId) goto L15;
        if (this.storiesController.hasUnreadStories(r6) == false) goto L15;
        boolean r22 = false;
    L18:
        if (r11.isSelf == false) goto L24;
        if (r22 == true) goto L21;
    L22:
        r4.add(Long.valueOf(r11.dialogId));
        boolean r23 = false;
        boolean r112 = true;
    L50:
        StoryViewer r3 = this.fragment.getOrCreateStoryViewer();
        r3.doOnAnimationReady(new DialogStoriesCell$$ExternalSyntheticLambda18(this, r12));
        boolean r122 = r23;
        Context r24 = getContext();
        StoriesListPlaceProvider r13 = StoriesListPlaceProvider.of(this.recyclerListView).with(new DialogStoriesCell$$ExternalSyntheticLambda19(this, r112));
        if (this.type != 1) goto L53;
        r1 = true;
    L53:
        r3.open(r24, null, r4, r02, null, null, r13.setPaginationParaments(r1, r122, r112), false);
        return;
    L21:
        if (this.items.size() == 1) goto L22;
    L24:
        if (r11.isSelf == false) goto L26;
    L40:
        int r113 = 0;
    L42:
        if (r113 >= this.items.size()) goto L49;
        if (this.storiesController.hasStories(this.items.get(r113).dialogId) == false) goto L46;
        r4.add(Long.valueOf(this.items.get(r113).dialogId));
    L48:
        r113 = r113 + 1;
        goto L42
    L46:
        if (r113 > r02) goto L48;
        r02 = r02 - 1;
        goto L48
    L49:
        r112 = false;
        r23 = false;
        goto L50
    L26:
        if (this.storiesController.hasUnreadStories(r11.dialogId) == false) goto L40;
        int r25 = 0;
    L29:
        if (r25 >= this.items.size()) goto L39;
        long r62 = this.items.get(r25).dialogId;
        if (r11.isSelf == true) goto L36;
        if (this.storiesController.hasUnreadStories(r62) == false) goto L36;
        r4.add(Long.valueOf(r62));
    L36:
        if (r62 != r11.dialogId) goto L38;
        r02 = r4.size() - 1;
    L38:
        r25 = r25 + 1;
        goto L29
    L39:
        r112 = false;
        r23 = true;
    L15:
        r2 = r2 + 1;
        goto L9
    L16:
        r22 = true;
        goto L18
    }

    private /* synthetic */ void lambda$openStoryRecorder$14(AlertDialog r1, long r2, StoryCell r4, Boolean r5) {
        r1.dismiss();
        if (r5.booleanValue() == false) goto L6;
        StoryRecorder.getInstance(this.fragment.getParentActivity(), this.currentAccount).selectedPeerId(r2).canChangePeer(false).open(StoryRecorder.SourceView.fromStoryCell(r4));
        return;
    }

    private /* synthetic */ void lambda$setDialogsTitleOverride$15(ValueAnimator r1) {
        this.titleOverrideProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$setProgressToCollapse$10(ValueAnimator r1) {
        this.expandOvershootAnimatorProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$setProgressToCollapse$7(ValueAnimator r1) {
        this.collapsedProgress2 = ((Float) r1.getAnimatedValue()).floatValue();
        checkCollapsedProgress();
    }

    private /* synthetic */ void lambda$setProgressToCollapse$8(ValueAnimator r1) {
        this.yStoriesProgress = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$setProgressToCollapse$9(ValueAnimator r1) {
        this.collapsedOvershootProgress = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$updateCurrentState$16() {
        updateItems(true, false);
    }

    private HintView2 makePremiumHint() {
        HintView2 r0 = this.premiumHint;
        if (r0 == null) goto L5;
        return r0;
    L5:
        this.premiumHint = new HintView2(getContext(), 1).setBgColor(getThemedColor(Theme.key_undo_background)).setMultilineText(true).setTextAlign(Layout.Alignment.ALIGN_CENTER).setJoint(0.0f, 29.0f);
        SpannableStringBuilder r02 = AndroidUtilities.replaceSingleTag(LocaleController.getString("StoriesPremiumHint2").replace('\n', ' '), Theme.key_undo_cancelColor, 0, new DialogStoriesCell$$ExternalSyntheticLambda17(this));
        ClickableSpan[] r1 = (ClickableSpan[]) r02.getSpans(0, r02.length(), ClickableSpan.class);
        if (r1 != null) goto L8;
    L10:
        HintView2 r12 = this.premiumHint;
        r12.setMaxWidthPx(HintView2.cutInFancyHalf(r02, r12.getTextPaint()));
        this.premiumHint.setText(r02);
        this.premiumHint.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(8.0f), 0);
        if ((getParent() instanceof FrameLayout) == false) goto L14;
        ((FrameLayout) getParent()).addView(this.premiumHint, LayoutHelper.createFrame(-1, 150, 51));
    L14:
        return this.premiumHint;
    L8:
        if (r1.length < 1) goto L10;
        int r2 = r02.getSpanStart(r1[0]);
        int r13 = r02.getSpanEnd(r1[0]);
        r02.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r2, r13, 33);
        goto L10
    }

    private void openStoryForCell(StoryCell r5, boolean r6) {
        if (r6 == false) goto L8;
        ValueAnimator r0 = this.expandOvershootAnimator;
        if (r0 == null) goto L8;
        if (r0.isRunning() == false) goto L8;
        return;
    L8:
        if (r5 != null) goto L36;
        return;
    L36:
        performHapticFeedback(3);     // Catch: Exception -> L35
    L13:
        if (r5.isSelf == false) goto L21;
        if (this.storiesController.hasSelfStories() == true) goto L21;
        if (MessagesController.getInstance(this.currentAccount).storiesEnabled() == true) goto L19;
        showPremiumHint();
        return;
    L19:
        openStoryRecorder();
        return;
    L21:
        if (this.storiesController.hasStories(r5.dialogId) == false) goto L23;
    L25:
        TL_stories.PeerStories r02 = this.storiesController.getStories(r5.dialogId);
        long r1 = r5.dialogId;
        StoriesUtilities.EnsureStoryFileLoadedObject r3 = this.globalCancelable;
        if (r3 == null) goto L28;
        r3.cancel();
        this.globalCancelable = null;
    L28:
        DialogStoriesCell$$ExternalSyntheticLambda12 r32 = new DialogStoriesCell$$ExternalSyntheticLambda12(this, r5, r1);
        if (r6 == false) goto L31;
        r32.run();
        return;
    L31:
        StoriesUtilities.EnsureStoryFileLoadedObject r62 = StoriesUtilities.ensureStoryFileLoaded(r02, r32);
        r5.cancellable = r62;
        this.globalCancelable = r62;
        if (r62 == null) goto L41;
        this.storiesController.setLoading(r5.dialogId, true);
        return;
    L41:
        return;
    L23:
        if (this.storiesController.hasUploadingStories(r5.dialogId) == true) goto L25;
    }

    private boolean shouldDrawSelfInMini() {
        long r0 = UserConfig.getInstance(this.currentAccount).clientUserId;
        if (this.storiesController.hasUnreadStories(r0) == false) goto L5;
        return true;
    L5:
        if (this.storiesController.hasSelfStories() == true) goto L7;
        return false;
    L7:
        if (this.storiesController.getDialogListStories().size() <= 3) goto L14;
        return false;
    L14:
        return true;
    }

    private void updateCurrentState(int r8) {
        if (this.currentState != r8) goto L5;
        return;
    L5:
        this.currentState = r8;
        if (r8 != 1) goto L8;
    L10:
        int r82 = this.currentState;
        if (r82 != 0) goto L13;
        AndroidUtilities.forEachViews(this.recyclerListView, new DialogStoriesCell$$ExternalSyntheticLambda16());
        this.listViewMini.setVisibility(4);
        this.recyclerListView.setVisibility(0);
        checkExpanded();
    L31:
        invalidate();
        return;
    L13:
        if (r82 != 1) goto L27;
        this.animateToDialogIds.clear();
        int r83 = 0;
    L16:
        if (r83 >= this.items.size()) goto L25;
        if (this.items.get(r83).dialogId == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L20;
    L21:
        this.animateToDialogIds.add(Long.valueOf(this.items.get(r83).dialogId));
        if (this.animateToDialogIds.size() == 3) goto L25;
    L24:
        r83 = r83 + 1;
        goto L16
    L20:
        if (shouldDrawSelfInMini() == false) goto L24;
    L25:
        this.listViewMini.setVisibility(4);
        this.recyclerListView.setVisibility(0);
        goto L31
    L27:
        if (r82 != 2) goto L31;
        this.listViewMini.setVisibility(0);
        this.recyclerListView.setVisibility(4);
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        MessagesController.getInstance(this.currentAccount).getStoriesController().scheduleSort();
        StoriesUtilities.EnsureStoryFileLoadedObject r84 = this.globalCancelable;
        if (r84 == null) goto L31;
        r84.cancel();
        this.globalCancelable = null;
        goto L31
    L8:
        if (this.updateOnIdleState == false) goto L10;
        AndroidUtilities.runOnUIThread(new DialogStoriesCell$$ExternalSyntheticLambda15(this));
        goto L10
    }

    public void afterNextLayout(Runnable r1) {
        this.afterNextLayout.add(r1);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.storiesUpdated) goto L5;
        return;
    L5:
        if (this.allowGlobalUpdates == true) goto L7;
        return;
    L7:
        if (getVisibility() != 0) goto L9;
        boolean r12 = true;
    L10:
        updateItems(r12, false);
        AndroidUtilities.runOnUIThread(new DialogStoriesCell$$ExternalSyntheticLambda6(this));
        return;
    L9:
        r12 = false;
        goto L10
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r30) {
        r30.save();
        int r2 = this.clipTop;
        boolean r7 = false;
        if (r2 <= 0) goto L5;
        r30.clipRect(0, r2, getMeasuredWidth(), getMeasuredHeight());
    L5:
        float r22 = (getMeasuredHeight() - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(4.0f);
        float r10 = AndroidUtilities.lerp(0.0f, r22, this.collapsedProgress1);
        this.recyclerListView.setTranslationY(r10);
        this.listViewMini.setTranslationY(r10);
        this.listViewMini.setTranslationX(this.menuItemsOffset);
        int r3 = 0;
    L6:
        int r4 = this.viewsDrawInParent.size();
        ArrayList<StoryCell> r5 = this.viewsDrawInParent;
        if (r3 >= r4) goto L9;
        r5.get(r3).drawInParent = false;
        r3 = r3 + 1;
        goto L6
    L9:
        r5.clear();
        int r32 = this.currentState;
        int r12 = -1;
        if (r32 == 1) goto L13;
        if (r32 == 0) goto L13;
    L22:
        if (this.currentState != 2) goto L24;
        int r42 = 0;
    L25:
        int r33 = this.currentState;
        if (r33 < 0) goto L184;
        if (r33 == 2) goto L184;
        if (r42 != (-1)) goto L33;
        r42 = this.layoutManager.findFirstCompletelyVisibleItemPosition();
        if (r42 != (-1)) goto L32;
        r42 = this.layoutManager.findFirstVisibleItemPosition();
    L32:
        boolean r34 = true;
    L34:
        float r16 = 4.0f;
        float r17 = 16.0f;
        this.recyclerListView.setAlpha(1.0f - Utilities.clamp(this.collapsedProgress / this.K, 1.0f, 0.0f));
        this.overscrollSelectedPosition = -1;
        if (this.overscrollProgress == 0.0f) goto L55;
        int r6 = 0;
        int r8 = -1;
    L38:
        if (r6 >= this.recyclerListView.getChildCount()) goto L54;
        View r14 = this.recyclerListView.getChildAt(r6);
        if (r14.getX() >= 0.0f) goto L42;
        int r222 = r6;
    L53:
        r6 = r222 + 1;
        r12 = -1;
        goto L38
    L42:
        if ((r14.getX() + r14.getMeasuredWidth()) <= getMeasuredWidth()) goto L44;
    L43:
        r222 = r6;
        goto L53
    L44:
        int r15 = this.recyclerListView.getChildAdapterPosition(r14);
        if (r15 < 0) goto L43;
        if (r8 == r12) goto L49;
        if (r15 >= r8) goto L43;
    L49:
        r222 = r6;
        if (this.items.get(r15).dialogId == UserConfig.getInstance(this.currentAccount).clientUserId) goto L53;
        this.overscrollSelectedView = (StoryCell) r14;
        r8 = r15;
        goto L53
    L54:
        float r19 = 2.0f;
        this.overscrollSelectedPosition = r8;
    L56:
        int r52 = 0;
        float r62 = 0.0f;
    L58:
        if (r52 >= this.recyclerListView.getChildCount()) goto L182;
        StoryCell r82 = (StoryCell) this.recyclerListView.getChildAt(r52);
        StoryCell.m19335$$Nest$msetClipInParent(r82, r7);
        int r11 = this.recyclerListView.getChildAdapterPosition(r82);
        float r122 = this.collapsedProgress;
        float r223 = r10;
        if (r11 >= r42) goto L62;
    L66:
        double r142 = 0.25d;
    L71:
        if (r11 >= r42) goto L73;
        r122 = (float) Math.pow(this.collapsedProgress, r142);
    L73:
        float r72 = this.collapsedProgress2;
        float r13 = this.overscrollProgress;
        if (this.overscrollSelectedPosition != r82.position) goto L76;
        boolean r143 = true;
    L77:
        r82.setProgressToCollapsed(r122, r72, r13, r143);
        if (r11 > r42) goto L80;
    L90:
        float r132 = r22;
        boolean r25 = r34;
        double r26 = 0.5d;
        StoriesUtilities.AvatarStoryParams r1 = r82.params;
        float r152 = 0.0f;
        r1.rightTopAngleToExclude = 0.0f;
        r1.rightBottomAngleToExclude = 0.0f;
        r1.leftTopAngleToExclude = 0.0f;
        r1.leftBottomAngleToExclude = 0.0f;
        r1.useArcProgress = false;
    L91:
        float r73 = AndroidUtilities.dp(16.0f) * Utilities.clamp((this.overscrollProgress - 0.5f) / 0.5f, 1.0f, r152);
        float r18 = (float) (((1.0f - r1) * 0.5f) + r26);
        if (r11 > r42) goto L96;
        float r23 = 0.0f;
    L94:
        float r153 = 0.0f;
    L99:
        float r24 = r23 + this.menuItemsOffset;
        if (this.collapsed == false) goto L102;
        float r154 = 0.0f;
        float r27 = AndroidUtilities.lerp(0.0f, r24 - r82.getLeft(), this.storiesCollapseInterpolator.getInterpolation(this.collapsedOvershootProgress));
    L111:
        float r35 = MathUtils.clamp((this.collapsedProgress1 - 0.2f) / 0.1f, r154, 1.0f);
        int r9 = r11 - r42;
        if (r9 != 0) goto L114;
        float r232 = 0.65f;
        float r102 = AndroidUtilities.lerp(r154, r223 - r132, CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(this.collapsedProgress));
    L119:
        if (r82.position == this.overscrollSelectedPosition) goto L121;
    L123:
        float r74 = 0.0f;
    L124:
        if (r9 != 0) goto L127;
        float r75 = AndroidUtilities.lerp(r74, r223 - r132, this.yStoriesProgress);
    L130:
        float r36 = AndroidUtilities.lerp(r75, r102, r35);
        if (this.collapsedProgress <= 0.0f) goto L158;
        if (r11 >= r42) goto L134;
    L137:
        boolean r110 = false;
    L136:
        int r76 = r52;
        float r103 = r62;
        if (r25 == false) goto L145;
        if (r9 >= 0) goto L142;
    L144:
        r82.setCrossfadeTo(-1);
    L146:
        r82.drawInParent = r110;
        if (r11 != r42) goto L149;
        boolean r53 = true;
    L150:
        r82.isFirst = r53;
        if (r11 < ((this.animateToDialogIds.size() + r42) - 1)) goto L153;
        boolean r54 = true;
    L154:
        r82.isLast = r54;
        r82.setTranslationX(r27);
        r82.setTranslationY(r36);
        if (r110 == false) goto L174;
        this.viewsDrawInParent.add(r82);
    L174:
        if (r82.drawInParent == false) goto L180;
        float r111 = ((this.recyclerListView.getX() + r82.getX()) + (r82.getMeasuredWidth() / r19)) + (AndroidUtilities.dp(70.0f) / r19);
        if (r103 != 0.0f) goto L178;
    L179:
        r62 = r111;
    L181:
        r52 = r76 + 1;
        r22 = r132;
        r10 = r223;
        r34 = r25;
        r7 = false;
        goto L58
    L178:
        if (r111 > r103) goto L179;
    L180:
        r62 = r103;
        goto L181
    L153:
        r54 = false;
        goto L154
    L149:
        r53 = false;
        goto L150
    L142:
        if (r9 >= this.animateToDialogIds.size()) goto L144;
        r82.setCrossfadeTo(this.animateToDialogIds.get(r9).longValue());
        goto L146
    L145:
        r82.setCrossfadeTo(-1);
        goto L146
    L134:
        if (r11 > (r42 + 2)) goto L137;
        r110 = true;
        goto L136
    L158:
        r76 = r52;
        r103 = r62;
        if (this.recyclerListView.getItemAnimator() == null) goto L163;
        if (this.recyclerListView.getItemAnimator().isRunning() == true) goto L174;
    L163:
        if (this.overscrollProgress <= 0.0f) goto L171;
        int r55 = r82.position;
        int r63 = this.overscrollSelectedPosition;
        if (r55 >= r63) goto L168;
        r82.setAlpha(r18);
    L172:
        r82.setTranslationX(r27);
        r82.setTranslationY(r36);
        goto L174
    L168:
        if (r55 <= r63) goto L170;
        r82.setAlpha(r18);
        goto L172
    L170:
        r82.setAlpha(1.0f);
        goto L172
    L171:
        r82.setAlpha(1.0f);
        goto L172
    L127:
        if (r9 != 1) goto L129;
        r75 = AndroidUtilities.lerp(r74, (r223 - r132) * r232, this.yStoriesProgress);
        goto L130
    L129:
        r75 = 0.0f;
        goto L130
    L121:
        if (this.overscrollProgress <= r154) goto L123;
        r74 = (-r73) / r19;
        goto L124
    L114:
        r232 = 0.65f;
        if (r9 != 1) goto L117;
        r102 = AndroidUtilities.lerp(r154, (r223 - r132) * 0.65f, CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(this.collapsedProgress));
        goto L119
    L117:
        r102 = r154;
        goto L119
    L102:
        if (this.overscrollProgress <= r153) goto L108;
        int r37 = r82.position;
        int r92 = this.overscrollSelectedPosition;
        if (r37 >= r92) goto L106;
        float r38 = -r73;
    L109:
        r27 = AndroidUtilities.lerp(r24 - r82.getLeft(), r38, 1.0f - this.expandOvershootAnimatorProgress);
        r154 = 0.0f;
        goto L111
    L106:
        if (r37 <= r92) goto L108;
        r38 = r73;
    L108:
        r38 = 0.0f;
        goto L109
    L96:
        if (r11 != (r42 + 1)) goto L98;
        r23 = AndroidUtilities.lerp(AndroidUtilities.dp(16.0f), 0.0f, this.collapsedProgress) + ((AndroidUtilities.dp(16.0f) * r122) - AndroidUtilities.dpf2(0.5f));
        goto L94
    L98:
        r153 = 0.0f;
        r23 = AndroidUtilities.lerp(AndroidUtilities.dp(32.0f), 0.0f, this.collapsedProgress) + ((AndroidUtilities.dp(16.0f) + (AndroidUtilities.dp(16.0f) * r122)) - AndroidUtilities.dpf2(0.5f));
        goto L99
    L80:
        if (r11 >= (this.animateToDialogIds.size() + r42)) goto L90;
        StoryCell r77 = (StoryCell) this.recyclerListView.getChildAt(r52 - 1);
        if (r77 == null) goto L88;
        float r133 = AndroidUtilities.dp(48.0f);
        float r144 = AndroidUtilities.dp(26.33f);
        r26 = 0.5d;
        float r155 = AndroidUtilities.lerp(r133, r144, r77.progressToCollapsed) + AndroidUtilities.dp(8.0f);
        float r93 = (AndroidUtilities.lerp(r133, r144, r82.progressToCollapsed) + AndroidUtilities.dp(8.0f)) / r19;
        float r104 = r77.params.originalAvatarRect.centerX() + r77.getX();
        float r134 = r77.params.originalAvatarRect.centerY() + r77.getY();
        float r145 = (r82.params.originalAvatarRect.centerX() + r82.getX()) - r104;
        float r112 = (r82.params.originalAvatarRect.centerY() + r82.getY()) - r134;
        r132 = r22;
        r25 = r34;
        if (((float) Math.sqrt((r145 * r145) + (r112 * r112))) >= ((r155 / r19) + r93)) goto L86;
        float r28 = (float) Math.toDegrees(Math.acos(r2 / r15) * 2.0d);
        float r29 = (float) Math.toDegrees(Math.atan2(r112, r145));
        float r39 = r28 / r19;
        StoriesUtilities.AvatarStoryParams r105 = r77.params;
        r105.rightTopAngleToExclude = r29 - r39;
        r105.rightBottomAngleToExclude = r29 + r39;
        float r113 = (float) Math.toDegrees(Math.atan2(-r112, -r145));
        float r210 = -Math.abs(r113 - r39);
        float r114 = Math.abs(r113 + r39);
        StoriesUtilities.AvatarStoryParams r310 = r82.params;
        r310.leftTopAngleToExclude = r210;
        r310.leftBottomAngleToExclude = r114;
    L87:
        r77.params.useArcProgress = false;
        r82.params.useArcProgress = false;
    L89:
        r152 = 0.0f;
        goto L91
    L86:
        StoriesUtilities.AvatarStoryParams r115 = r77.params;
        r115.rightTopAngleToExclude = 0.0f;
        r115.rightBottomAngleToExclude = 0.0f;
        StoriesUtilities.AvatarStoryParams r116 = r82.params;
        r116.leftTopAngleToExclude = 0.0f;
        r116.leftBottomAngleToExclude = 0.0f;
        goto L87
    L88:
        r132 = r22;
        r25 = r34;
        r26 = 0.5d;
        goto L89
    L76:
        r143 = false;
        goto L77
    L62:
        if (r11 >= (this.animateToDialogIds.size() + r42)) goto L66;
        int r123 = r11 - r42;
        if (r123 != (r42 + 2)) goto L67;
        r122 = this.collapsedProgress;
        goto L66
    L67:
        int r156 = r42 + 1;
        float r78 = this.collapsedProgress;
        if (r123 != r156) goto L70;
        r122 = (float) Math.pow(r78, 0.5d);
        goto L66
    L70:
        r142 = 0.25d;
        r122 = (float) Math.pow(r78, 0.25d);
        goto L71
    L182:
        float r224 = r10;
        float r94 = 1.0f;
    L183:
        float r106 = r62;
        if (this.premiumHint == null) goto L199;
        float r117 = AndroidUtilities.lerp(29, 74, CubicBezierInterpolator.EASE_OUT.getInterpolation(this.collapsedProgress));
        if (this.recyclerListView.getChildCount() <= 0) goto L198;
        r117 = r117 + this.recyclerListView.getChildAt(0).getLeft();
    L198:
        this.premiumHint.setJoint(0.0f, r117);
    L199:
        float r79 = Math.min(this.collapsedProgress, this.collapsedProgress2);
        float r118 = (this.actionBar.menu.getVisibleItemsMeasuredWidthWithAlpha() * r79) - AndroidUtilities.dp(6.0f);
        if (r79 != 0.0f) goto L202;
    L204:
        boolean r135 = false;
    L205:
        if (r135 == false) goto L207;
        Canvas r119 = r30;
        r119.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null);
        r119.save();
        r119.clipRect(0.0f, 0.0f, getWidth() - r118, getHeight());
    L208:
        if (r79 == 0.0f) goto L226;
        float r311 = AndroidUtilities.lerp(r94, 0.95f, this.subtitleOverlayContainer.getTotalVisibility());
        float r43 = (((r224 + AndroidUtilities.dp(14.0f)) - ((this.titleView.getMeasuredHeight() - this.titleView.getTextHeight()) / r19)) + AndroidUtilities.dp(r16)) - (AndroidUtilities.dp(6.0f) * this.subtitleOverlayContainer.getTotalVisibility());
        this.titleView.setPivotX(0.0f);
        this.titleView.setScaleX(r311);
        this.titleView.setScaleY(r311);
        this.titleView.setTranslationY(r43);
        float r107 = r106 + (((-r2) + getAvatarRight(AndroidUtilities.dp(72.0f), this.collapsedProgress)) + AndroidUtilities.dp(r17));
        int r211 = AndroidUtilities.dp(10.0f);
        if (this.titleOverrideForward == false) goto L212;
        int r124 = -1;
    L213:
        float r212 = r211 * r124;
        if (this.titleOverrideAnimator == null) goto L216;
        float r56 = (-r212) * (r94 - this.titleOverrideProgress);
    L217:
        this.titleView.setTranslationX(r56 + r107);
        this.titleView.getDrawable().setRightPadding((r107 - AndroidUtilities.dp(12.0f)) + (this.actionBar.menu.getVisibleItemsMeasuredWidthWithAlpha() * r79));
        this.titleView.setAlpha(this.titleOverrideProgress * r79);
        this.titleViewOut.setPivotX(0.0f);
        this.titleViewOut.setScaleX(r311);
        this.titleViewOut.setScaleY(r311);
        this.titleViewOut.setTranslationY(r43);
        ValueAnimator r57 = this.titleOverrideAnimator;
        AnimatedTextView r83 = this.titleViewOut;
        if (r57 == null) goto L220;
        r83.setTranslationX((r212 * this.titleOverrideProgress) + r107);
        this.titleViewOut.getDrawable().setRightPadding((r107 - AndroidUtilities.dp(12.0f)) + (this.actionBar.menu.getVisibleItemsMeasuredWidthWithAlpha() * r79));
        this.titleViewOut.setAlpha((r94 - this.titleOverrideProgress) * r79);
        int r58 = 0;
        this.titleViewOut.setVisibility(0);
    L222:
        if (isEmojiStatusFadingOut() == false) goto L224;
        AnimatedTextView r213 = this.titleViewOut;
    L225:
        this.emojiStatusView.setTranslationX((r213.getTranslationX() + (r213.getDrawable().getCurrentWidth() * r311)) - AndroidUtilities.dp(r19));
        this.emojiStatusView.setTranslationY(r43 + ((r213.getMeasuredHeight() - this.emojiStatusView.getMeasuredHeight()) / r19));
        this.emojiStatusView.setAlpha(r79 * getEmojiStatusAlpha());
        this.subtitleOverlayContainer.setTranslationX(this.titleView.getTranslationX());
        this.subtitleOverlayContainer.setTranslationY(r224 + AndroidUtilities.dp(36.333f));
    L227:
        super.dispatchDraw(r30);
        int r214 = this.currentState;
        if (r214 >= 0) goto L230;
    L235:
        if (r135 == false) goto L241;
        float r215 = AndroidUtilities.dp(r17);
        if (this.ellipsizeGradient != null) goto L239;
        float r242 = r215;
        this.ellipsizeGradient = new LinearGradient(0.0f, 0.0f, r242, 0.0f, new int[]{16711680, Opcodes.V_PREVIEW}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.ellipsizeGradientMatrix = new Matrix();
        Paint r216 = new Paint(1);
        this.ellipsizePaint = r216;
        r216.setShader(this.ellipsizeGradient);
        this.ellipsizePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    L240:
        this.ellipsizeGradientMatrix.reset();
        this.ellipsizeGradientMatrix.postTranslate((getWidth() - r118) - r242, 0.0f);
        this.ellipsizeGradient.setLocalMatrix(this.ellipsizeGradientMatrix);
        r119.drawRect((getWidth() - r118) - r242, 0.0f, AndroidUtilities.dp(r94) + (getWidth() - r118), getHeight(), this.ellipsizePaint);
        r30.restore();
        r30.restore();
        goto L241
    L239:
        r242 = r215;
    L241:
        drawActionBarHolidayEffect(r30);
        r30.restore();
        return;
    L230:
        if (r214 == 2) goto L235;
        Collections.sort(this.viewsDrawInParent, this.comparator);
        int r710 = r58;
    L233:
        if (r710 >= this.viewsDrawInParent.size()) goto L235;
        StoryCell r217 = this.viewsDrawInParent.get(r710);
        r119.save();
        r119.translate(this.recyclerListView.getX() + r217.getX(), this.recyclerListView.getY() + r217.getY());
        r217.draw(r119);
        r119.restore();
        r710 = r710 + 1;
        goto L233
    L224:
        r213 = this.titleView;
        goto L225
    L220:
        r58 = 0;
        r83.setAlpha(0.0f);
        this.titleViewOut.setVisibility(8);
        goto L222
    L216:
        r56 = 0.0f;
        goto L217
    L212:
        r124 = 1;
        goto L213
    L226:
        r58 = 0;
        goto L227
    L207:
        r119 = r30;
        goto L208
    L202:
        if (r118 <= 0.0f) goto L204;
        r135 = true;
        goto L205
    L55:
        r19 = 2.0f;
        goto L56
    L33:
        r34 = false;
    L184:
        r94 = 1.0f;
        r16 = 4.0f;
        r224 = r10;
        r17 = 16.0f;
        r19 = 2.0f;
        int r120 = 0;
        r62 = 0.0f;
    L186:
        if (r120 >= this.listViewMini.getChildCount()) goto L183;
        float r312 = (this.listViewMini.getX() + ((StoryCell) this.listViewMini.getChildAt(r120)).getX()) + r2.getMeasuredWidth();
        if (r62 != 0.0f) goto L190;
    L191:
        r62 = r312;
    L192:
        r120 = r120 + 1;
        goto L186
    L190:
        if (r312 <= r62) goto L192;
    L24:
        r42 = -1;
    L13:
        if (this.animateToDialogIds.isEmpty() == true) goto L22;
        int r313 = 0;
        r42 = -1;
    L16:
        if (r313 >= this.recyclerListView.getChildCount()) goto L25;
        StoryCell r59 = (StoryCell) this.recyclerListView.getChildAt(r313);
        if (r59.dialogId != this.animateToDialogIds.get(0).longValue()) goto L20;
        r42 = this.recyclerListView.getChildAdapterPosition(r59);
    L20:
        r313 = r313 + 1;
        goto L16
    }

    public StoryCell findStoryCell(long r5) {
        RecyclerListView r0 = this.recyclerListView;
        if (this.currentState != 2) goto L5;
        r0 = this.listViewMini;
    L5:
        int r4 = 0;
    L7:
        if (r4 >= r0.getChildCount()) goto L14;
        View r1 = r0.getChildAt(r4);
        if ((r1 instanceof StoryCell) == false) goto L13;
        StoryCell r12 = (StoryCell) r1;
        if (r12.dialogId != r5) goto L13;
        return r12;
    L13:
        r4 = r4 + 1;
        goto L7
    L14:
        return null;
    }

    public float getCollapsedProgress() {
        return this.collapsedProgress;
    }

    public float getOverScrollCoef() {
        return this.overScrollCoef;
    }

    public HintView2 getPremiumHint() {
        return this.premiumHint;
    }

    public boolean isExpanded() {
        int r1 = this.currentState;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L8;
        return false;
    L8:
        return true;
    }

    public boolean isFullExpanded() {
        if (this.currentState != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateItems(false, false);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesUpdated);
        this.ellipsizeSpanAnimator.onAttachedToWindow();
        this.statusDrawable.attach();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesUpdated);
        this.ellipsizeSpanAnimator.onDetachedFromWindow();
        StoriesUtilities.EnsureStoryFileLoadedObject r0 = this.globalCancelable;
        if (r0 == null) goto L5;
        r0.cancel();
        this.globalCancelable = null;
    L5:
        this.statusDrawable.detach();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 1) goto L6;
        checkUi_titleVisibility();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (AndroidUtilities.isTablet() == false) goto L5;
    L7:
        float r52 = 20.0f;
    L8:
        float r53 = AndroidUtilities.dp(r52);
        this.titleView.setTextSize(r53);
        this.titleViewOut.setTextSize(r53);
        this.currentCellWidth = AndroidUtilities.dp(70.0f);
        AndroidUtilities.rectTmp.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(89.0f), TLObject.FLAG_30));
        return;
    L5:
        if (getResources().getConfiguration().orientation != 2) goto L7;
        r52 = 18.0f;
        goto L8
    }

    public abstract void onMiniListClicked();

    public void onResume() {
        this.storiesController.checkExpiredStories();
        int r0 = 0;
    L4:
        if (r0 >= this.items.size()) goto L9;
        TL_stories.PeerStories r1 = this.storiesController.getStories(this.items.get(r0).dialogId);
        if (r1 == null) goto L8;
        this.storiesController.preloadUserStories(r1);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (this.currentState != 2) goto L8;
        this.miniItemsClickArea.setRect((int) this.listViewMini.getX(), (int) this.listViewMini.getY(), (int) (this.listViewMini.getX() + AndroidUtilities.dp((this.miniItems.size() * 26.33f) - (Math.max(0, r0 - 1) * 16.0f))), (int) (this.listViewMini.getY() + this.listViewMini.getHeight()));
        if (this.miniItemsClickArea.checkTouchEvent(r8) == false) goto L8;
        return true;
    L8:
        return super.onTouchEvent(r8);
    }

    public abstract void onUserLongPressed(View r1, long r2);

    public boolean openOverscrollSelectedStory() {
        ValueAnimator r0 = this.expandOvershootAnimator;
        if (r0 != null) goto L5;
    L8:
        openStoryForCell(this.overscrollSelectedView, true);
        return true;
    L5:
        if (r0.isRunning() == false) goto L8;
        return false;
    }

    public void openSelfStories() {
        if (this.storiesController.hasSelfStories() == true) goto L5;
        return;
    L5:
        ArrayList<Long> r4 = new ArrayList();
        r4.add(Long.valueOf(UserConfig.getInstance(this.currentAccount).clientUserId));
        this.fragment.getOrCreateStoryViewer().open(getContext(), null, r4, 0, null, null, StoriesListPlaceProvider.of(this.listViewMini), false);
    }

    public void openStoryForCell(StoryCell r2) {
        openStoryForCell(r2, false);
    }

    public void openStoryRecorder() {
        openStoryRecorder(0);
    }

    public void openStoryRecorder(long r13) {
        if (r13 != 0) goto L10;
        StoriesController.StoryLimit r2 = MessagesController.getInstance(this.currentAccount).getStoriesController().checkStoryLimit();
        if (r2 == null) goto L10;
        if (r2.active(this.currentAccount) == false) goto L10;
        this.fragment.showDialog(new LimitReachedBottomSheet(this.fragment, getContext(), r2.getLimitReachedType(), this.currentAccount, null));
        return;
    L10:
        int r22 = 0;
    L11:
        Theme.ResourcesProvider r4 = null;
        if (r22 >= this.recyclerListView.getChildCount()) goto L22;
        StoryCell r3 = (StoryCell) this.recyclerListView.getChildAt(r22);
        if (r13 != 0) goto L19;
        if (r3.isSelf == false) goto L21;
    L20:
        StoryCell r5 = r3;
    L23:
        if (r5 != null) goto L25;
        return;
    L25:
        BaseFragment r23 = this.fragment;
        if (r13 == 0) goto L31;
        if (r23 == null) goto L29;
        r4 = r23.getResourceProvider();
    L29:
        Theme.ResourcesProvider r11 = r4;
        AlertDialog r24 = new AlertDialog(getContext(), 3, r11);
        r24.showDelayed(500);
        MessagesController.getInstance(this.currentAccount).getStoriesController().canSendStoryFor(r13, new DialogStoriesCell$$ExternalSyntheticLambda20(this, r24, r13, r5), true, r11);
        return;
    L31:
        StoryRecorder.getInstance(r23.getParentActivity(), this.currentAccount).open(StoryRecorder.SourceView.fromStoryCell(r5));
        return;
    L21:
        r22 = r22 + 1;
        goto L11
    L19:
        if (r3.dialogId != r13) goto L21;
    L22:
        r5 = null;
        goto L23
    }

    public float overscrollProgress() {
        return this.overscrollProgress;
    }

    public boolean scrollTo(long r5) {
        int r1 = 0;
    L4:
        if (r1 >= this.items.size()) goto L9;
        if (this.items.get(r1).dialogId == r5) goto L10;
        r1 = r1 + 1;
    L10:
        if (r1 < 0) goto L19;
        int r52 = this.layoutManager.findFirstCompletelyVisibleItemPosition();
        LinearLayoutManager r6 = this.layoutManager;
        if (r1 >= r52) goto L16;
        r6.scrollToPositionWithOffset(r1, 0);
        return true;
    L16:
        if (r1 <= r6.findLastCompletelyVisibleItemPosition()) goto L19;
        this.layoutManager.scrollToPositionWithOffset(r1, 0, true);
        return true;
    L19:
        return false;
    L9:
        r1 = -1;
        goto L10
    }

    public boolean scrollToFirst() {
        if (this.layoutManager.findFirstVisibleItemPosition() == 0) goto L6;
        this.recyclerListView.smoothScrollToPosition(0);
        return true;
    L6:
        return false;
    }

    public void scrollToFirstCell() {
        this.layoutManager.scrollToPositionWithOffset(0, 0);
    }

    public void setActionBar(ActionBar r1) {
        this.actionBar = r1;
    }

    public void setClipTop(int r2) {
        if (r2 >= 0) goto L5;
        r2 = 0;
    L5:
        if (this.clipTop == r2) goto L8;
        this.clipTop = r2;
        invalidate();
        return;
    }

    public void setDialogsEmojiStatusVisible(boolean r2) {
        this.dialogsEmojiStatusVisibleOut = this.dialogsEmojiStatusVisible;
        if (r2 == false) goto L5;
    L8:
        boolean r22 = true;
    L9:
        this.dialogsEmojiStatusVisible = r22;
        invalidate();
        return;
    L5:
        if (ExteraConfig.getTabIcons() != TabIconsMode.ICONS_ONLY) goto L8;
        r22 = false;
        goto L9
    }

    public void setDialogsTitleOverride(CharSequence r2, boolean r3) {
        setDialogsTitleOverride(r2, r3, true);
    }

    public void setDialogsTitleOverride(CharSequence r3, boolean r4, boolean r5) {
        if (ExteraConfig.getTabIcons() == TabIconsMode.ICONS_ONLY) goto L6;
        r3 = null;
    L6:
        this.dialogsTitleOverride = r3;
        if (this.type != 0) goto L21;
        this.currentTitle = getDialogsMainTitle();
        if (this.hasOverlayText == false) goto L12;
        return;
    L12:
        CharSequence r32 = this.titleView.getText();
        cancelTitleOverrideAnimation();
        if (r4 == true) goto L15;
    L19:
        this.titleView.setText(this.currentTitle, false);
        invalidate();
        return;
    L15:
        if (TextUtils.equals(r32, this.currentTitle) == true) goto L19;
        this.titleOverrideForward = r5;
        this.titleOverrideProgress = 0.0f;
        this.titleViewOut.setText(r32, false);
        this.titleViewOut.setVisibility(0);
        this.titleViewOut.setAlpha(MathUtils.clamp(Math.min(this.collapsedProgress, this.collapsedProgress2), 0.0f, 1.0f));
        this.titleView.setText(this.currentTitle, false);
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.titleOverrideAnimator = r33;
        r33.addUpdateListener(new DialogStoriesCell$$ExternalSyntheticLambda5(this));
        this.titleOverrideAnimator.addListener(new AnonymousClass11(this));
        this.titleOverrideAnimator.setDuration(350);
        this.titleOverrideAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.titleOverrideAnimator.start();
        return;
    }

    public void setMenuItemsOffset(float r1) {
        this.menuItemsOffset = r1;
    }

    public void setOverscroll(float r2) {
        this.overscrollProgress = r2 / AndroidUtilities.dp(90.0f);
        invalidate();
        this.recyclerListView.invalidate();
    }

    public void setProgressToCollapse(float r2) {
        setProgressToCollapse(r2, true);
    }

    public void setProgressToCollapse(float r9, boolean r10) {
        if (this.collapsedProgress1 == r9) goto L44;
        this.collapsedProgress1 = r9;
        checkCollapsedProgress();
        if (r9 <= this.K) goto L8;
        boolean r92 = true;
    L10:
        if (r92 == this.collapsed) goto L43;
        this.collapsed = r92;
        AnimatorSet r2 = this.storiesAnimatorSet;
        if (r2 == null) goto L14;
        r2.removeAllListeners();
        this.storiesAnimatorSet.cancel();
        this.storiesAnimatorSet = null;
    L14:
        float r22 = 1.0f;
        if (r10 == false) goto L38;
        float r102 = this.collapsedProgress2;
        if (r92 == false) goto L19;
        float r4 = 1.0f;
    L20:
        ValueAnimator r103 = ValueAnimator.ofFloat(new float[]{r102, r4});
        this.valueAnimator = r103;
        r103.addUpdateListener(new DialogStoriesCell$$ExternalSyntheticLambda0(this));
        this.valueAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        float r104 = this.collapsedProgress1;
        if (r92 == false) goto L23;
        float r42 = r104;
    L24:
        ValueAnimator r105 = ValueAnimator.ofFloat(new float[]{r104, r42});
        this.yStoriesAnimator = r105;
        r105.addUpdateListener(new DialogStoriesCell$$ExternalSyntheticLambda1(this));
        this.yStoriesAnimator.setDuration(100);
        AnimatorSet r106 = new AnimatorSet();
        this.storiesAnimatorSet = r106;
        r106.addListener(new AnonymousClass10(this, r92));
        ArrayList r107 = new ArrayList();
        r107.add(this.valueAnimator);
        r107.add(this.yStoriesAnimator);
        if (this.collapsed == false) goto L31;
        this.storiesAnimatorSet.setDuration(1000);
        float r43 = this.collapsedProgress2;
        if (r92 == true) goto L30;
        r22 = 0.0f;
    L30:
        ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{r43, r22});
        this.collapsedOvershootAnimator = r93;
        r93.addUpdateListener(new DialogStoriesCell$$ExternalSyntheticLambda2(this));
        OvershootInterpolator r94 = new OvershootInterpolator(this.collapsedSpringCoef);
        this.storiesCollapseInterpolator = r94;
        this.collapsedOvershootAnimator.setInterpolator(r94);
        this.collapsedOvershootAnimator.setDuration(750);
        r107.add(this.collapsedOvershootAnimator);
    L36:
        this.storiesAnimatorSet.playTogether(r107);
        this.storiesAnimatorSet.start();
        return;
    L31:
        float r44 = this.collapsedProgress2;
        if (r92 == true) goto L35;
        r22 = 0.0f;
    L35:
        this.expandOvershootAnimator = ValueAnimator.ofFloat(new float[]{r44, r22});
        OvershootInterpolator r95 = new OvershootInterpolator(this.expandedSpringCoef);
        this.storiesExpandInterpolator = r95;
        this.expandOvershootAnimator.setInterpolator(r95);
        this.expandOvershootAnimator.setDuration(350);
        this.expandOvershootAnimator.addUpdateListener(new DialogStoriesCell$$ExternalSyntheticLambda3(this));
        r107.add(this.expandOvershootAnimator);
        goto L36
    L23:
        r42 = 0.0f;
        goto L24
    L19:
        r4 = 0.0f;
        goto L20
    L38:
        if (r92 == true) goto L41;
        r22 = 0.0f;
    L41:
        this.collapsedProgress2 = r22;
        checkCollapsedProgress();
        AndroidUtilities.forEachViews(this.recyclerListView, new DialogStoriesCell$$ExternalSyntheticLambda4());
        return;
    L43:
        return;
    L8:
        r92 = false;
        goto L10
    }

    public void setTitleOverlayText(String r6, int r7) {
        cancelTitleOverrideAnimation();
        if (r7 != R.string.ConnectingToProxyWithDots) goto L5;
        CharSequence r0 = AndroidUtilities.replaceArrows(LocaleController.getString(R.string.TitleSetupProxy), true, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(2.0f));
    L6:
        this.subtitleOverlayContainer.setText(r0, true);
        if (r6 == null) goto L11;
        this.hasOverlayText = true;
        if (this.overlayTextId == r7) goto L12;
        this.overlayTextId = r7;
        this.titleView.setText(LocaleController.getString(r6, r7), !LocaleController.isRTL);
    L12:
        this.titleViewOut.setText(null, false);
        this.titleViewOut.setVisibility(8);
        this.animatorHasTitleText.setValue(this.hasOverlayText, true);
        this.ellipsizeSpanAnimator.removeView(this.titleView);
        return;
    L11:
        this.hasOverlayText = false;
        this.overlayTextId = 0;
        this.titleView.setText(this.currentTitle, !LocaleController.isRTL);
        goto L12
    L5:
        r0 = null;
        goto L6
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        HintView2 r0 = this.premiumHint;
        if (r0 == null) goto L6;
        r0.setTranslationY(r1);
        return;
    }

    public void showPremiumHint() {
        makePremiumHint();
        HintView2 r0 = this.premiumHint;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.shown() == false) goto L7;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
    L7:
        this.premiumHint.show();
    }

    public void updateColors() {
        StoriesUtilities.updateColors();
        int r0 = getTextColor();
        this.titleView.setTextColor(getTextLogoColor());
        this.titleViewOut.setTextColor(getTextLogoColor());
        ActionBarAnimatedSubtitleOverlayContainer r1 = this.subtitleOverlayContainer;
        if (r1 == null) goto L5;
        r1.updateColors();
    L5:
        AndroidUtilities.forEachViews(this.recyclerListView, new DialogStoriesCell$$ExternalSyntheticLambda13(r0));
        AndroidUtilities.forEachViews(this.listViewMini, new DialogStoriesCell$$ExternalSyntheticLambda14());
    }

    public void updateItems(boolean r8, boolean r9) {
        if (this.currentState != 1) goto L5;
    L6:
        if (r9 == true) goto L9;
        this.updateOnIdleState = true;
        return;
    L9:
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.oldMiniItems.clear();
        this.oldMiniItems.addAll(this.miniItems);
        this.items.clear();
        if (this.type == 1) goto L12;
        this.items.add(new Item(this, UserConfig.getInstance(this.currentAccount).getClientUserId()));
    L12:
        int r92 = this.type;
        StoriesController r0 = this.storiesController;
        if (r92 != 1) goto L15;
        ArrayList<TL_stories.PeerStories> r93 = r0.getHiddenList();
    L16:
        int r02 = 0;
        int r2 = 0;
    L18:
        if (r2 >= r93.size()) goto L23;
        long r3 = DialogObject.getPeerDialogId(r93.get(r2).peer);
        if (r3 == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L22;
        this.items.add(new Item(this, r3));
    L22:
        r2 = r2 + 1;
        goto L18
    L23:
        this.items.size();
        this.storiesController.hasSelfStories();
        if (this.type != 0) goto L26;
        CharSequence r94 = getDialogsMainTitle();
    L27:
        this.currentTitle = r94;
        if (this.hasOverlayText == true) goto L36;
        AnimatedTextView r22 = this.titleView;
        if (r8 == true) goto L32;
    L34:
        boolean r32 = false;
    L35:
        r22.setText(r94, r32);
        goto L36
    L32:
        if (LocaleController.isRTL == true) goto L34;
        r32 = true;
    L36:
        this.animatorHasTitleText.setValue(true, r8);
        this.miniItems.clear();
    L38:
        if (r02 >= this.items.size()) goto L49;
        if (this.items.get(r02).dialogId == UserConfig.getInstance(this.currentAccount).clientUserId) goto L42;
    L44:
        this.miniItems.add(this.items.get(r02));
        if (this.miniItems.size() >= 3) goto L49;
    L47:
        r02 = r02 + 1;
        goto L38
    L42:
        if (shouldDrawSelfInMini() == true) goto L44;
    L49:
        if (r8 == true) goto L51;
        this.recyclerListView.setItemAnimator(null);
        this.listViewMini.setItemAnimator(null);
    L55:
        this.adapter.setItems(this.oldItems, this.items);
        this.miniAdapter.setItems(this.oldMiniItems, this.miniItems);
        this.oldItems.clear();
        invalidate();
        return;
    L51:
        if (this.currentState != 2) goto L53;
        this.listViewMini.setItemAnimator(this.miniItemAnimator);
        this.recyclerListView.setItemAnimator(null);
        goto L55
    L53:
        this.recyclerListView.setItemAnimator(this.itemAnimator);
        this.listViewMini.setItemAnimator(null);
        goto L55
    L26:
        r94 = LocaleController.getString(R.string.ArchivedChats);
        goto L27
    L15:
        r93 = r0.getDialogListStories();
        goto L16
    L5:
        if (this.overscrollProgress == 0.0f) goto L9;
        goto L6
    }

    public void updateStatus(TLRPC.User r5, boolean r6) {
        if (this.statusDrawable != null) goto L5;
        return;
    L5:
        if (this.actionBar == null) goto L26;
        Long r0 = UserObject.getEmojiStatusDocumentId(r5);
        if (ExteraConfig.getHideActionBarStatus() == false) goto L10;
    L22:
        this.statusDrawable.set(null, r6);
        this.statusDrawable.setParticles(false, r6);
    L23:
        this.statusDrawable.setColor(Integer.valueOf(getThemedColor(Theme.key_profile_verifiedBackground)));
        this.emojiStatusView.invalidate();
        return;
    L10:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L22;
        if (r0 == null) goto L14;
        boolean r52 = r5.emoji_status instanceof TLRPC.TL_emojiStatusCollectible;
        this.statusDrawable.set(r0.longValue(), r6);
        this.statusDrawable.setParticles(r52, r6);
        goto L23
    L14:
        if (r5 != null) goto L16;
    L21:
        this.statusDrawable.set(null, r6);
        this.statusDrawable.setParticles(false, r6);
        goto L23
    L16:
        if (MessagesController.getInstance(this.currentAccount).isPremiumUser(r5) == false) goto L21;
        if (this.premiumStar != null) goto L20;
        this.premiumStar = getContext().getResources().getDrawable(R.drawable.msg_premium_liststar).mutate();
        this.premiumStar = new AnonymousClass13(this, this.premiumStar, AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
    L20:
        this.premiumStar.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_profile_verifiedBackground), PorterDuff.Mode.MULTIPLY));
        this.statusDrawable.set(this.premiumStar, r6);
        this.statusDrawable.setParticles(false, r6);
        goto L23
    }
}
