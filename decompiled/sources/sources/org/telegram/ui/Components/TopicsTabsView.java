package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.HashSet;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.TopicsController;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.GradientClip;
import org.telegram.ui.TopicCreateFragment;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class TopicsTabsView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private boolean allTopicsHidden;
    private long animateFromSelectedTopicId;
    private ValueAnimator animator;
    private final BoolAnimator animatorCloseButtonVisibility;
    private final BoolAnimator animatorTopicsVisibility;
    private final boolean bot;
    private final HorizontalTabView botCreateTopicButtonHorizontal;
    private final VerticalTabView botCreateTopicButtonVertical;
    private final boolean canShowProgress;
    private final ImageView closeButtonSide;
    private final ImageView closeButtonTop;
    private final int currentAccount;
    private long currentTopicId;
    private final long dialogId;
    private final HashSet<Integer> excludeTopics;
    private final BaseFragment fragment;
    private long lastSelectedTopicId;
    private final boolean mono;
    private boolean notificationsAttached;
    private Utilities.Callback2<Long, Boolean> onDialogSelected;
    private Runnable onTopicCreated;
    private Utilities.Callback2<Integer, Boolean> onTopicSelected;
    private Runnable onUpdateSideMenuPosition;
    private Boolean pendingSidemenu;
    private final Theme.ResourcesProvider resourcesProvider;
    private BlurredBackgroundDrawable sideMenuBackgroundDrawable;
    private float sideMenuBackgroundMarginBottom;
    private float sideMenuBackgroundMarginTop;
    private final UniversalRecyclerView sideTabs;
    private final FrameLayout sideTabsContainer;
    private boolean sidemenuAnimating;
    private boolean sidemenuEnabled;
    private float sidemenuT;
    private final ImageView toggleButtonSide;
    private final ImageView toggleButtonTop;
    private BlurredBackgroundDrawable topMenuBackgroundDrawable;
    private final UniversalRecyclerView topTabs;
    private final FrameLayout topTabsContainer;
    private boolean topicBottom;

    /* renamed from: org.telegram.ui.Components.TopicsTabsView$1, reason: invalid class name */
    public class AnonymousClass1 extends UniversalRecyclerView {
        private final AnimatedFloat animateTab;
        private final AnimatedFloat animatedClipL;
        private final AnimatedFloat animatedClipR;
        private final GradientClip clip;
        private final Paint linePaint;
        private final RectF lineRect;
        private Drawable pinIcon;
        private int pinIconColor;
        private final Paint pinnedBackgroundPaint;
        final /* synthetic */ TopicsTabsView this$0;

        public AnonymousClass1(TopicsTabsView r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
            this.clip = new GradientClip();
            CubicBezierInterpolator r22 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.animatedClipL = new AnimatedFloat(this, 320, r22);
            this.animatedClipR = new AnimatedFloat(this, 320, r22);
            this.lineRect = new RectF();
            this.linePaint = new Paint(1);
            this.animateTab = new AnimatedFloat(this, 420, r22);
            this.pinnedBackgroundPaint = new Paint(1);
        }

        private void drawPinnedBackground(Canvas r9) {
            float r0 = getWidth();
            float r1 = 0.0f;
            int r2 = 0;
        L4:
            if (r2 >= getChildCount()) goto L18;
            View r3 = getChildAt(r2);
            if ((r3 instanceof HorizontalTabView) == false) goto L16;
            HorizontalTabView r32 = (HorizontalTabView) r3;
            if (HorizontalTabView.m13355$$Nest$fgetpinned(r32) == false) goto L16;
            if (r0 <= r32.getX()) goto L14;
            r0 = r32.getX();
            getChildAdapterPosition(r32);
        L14:
            if (r1 >= (r32.getX() + r32.getWidth())) goto L16;
            r1 = r32.getX() + r32.getWidth();
            getChildAdapterPosition(r32);
        L16:
            r2 = r2 + 1;
            goto L4
        L18:
            if (r1 <= r0) goto L32;
            this.pinnedBackgroundPaint.setColor(Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), 0.06f));
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set(r0 + AndroidUtilities.dp(1.0f), (getHeight() - AndroidUtilities.dp(28.0f)) / 2.0f, r1 - AndroidUtilities.dp(1.0f), (getHeight() + AndroidUtilities.dp(28.0f)) / 2.0f);
            r9.drawRoundRect(r22, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.pinnedBackgroundPaint);
            if (this.pinIcon != null) goto L22;
            this.pinIcon = getContext().getResources().getDrawable(R.drawable.msg_limit_pin).mutate();
        L22:
            int r02 = Theme.getColor(Theme.key_chats_pinnedIcon, this.resourcesProvider);
            if (this.pinIconColor == r02) goto L25;
            Drawable r33 = this.pinIcon;
            this.pinIconColor = r02;
            r33.setColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN));
        L25:
            this.pinIcon.setBounds((int) (AndroidUtilities.dp(-17.0f) + r1), (int) (r22.top + AndroidUtilities.dp(10.0f)), (int) (r1 + AndroidUtilities.dp(-7.0f)), (int) (r22.top + AndroidUtilities.dp(20.0f)));
            this.pinIcon.draw(r9);
            return;
        }

        @Override // org.telegram.ui.Components.UniversalRecyclerView, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r18) {
            float r8 = this.animatedClipL.set(canScrollHorizontally(-1));
            float r9 = this.animatedClipR.set(canScrollHorizontally(1));
            if (r8 <= 0.0f) goto L5;
        L8:
            boolean r13 = true;
        L9:
            if (r13 == false) goto L11;
            Canvas r1 = r18;
            r1.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        L12:
            drawPinnedBackground(r18);
            super.dispatchDraw(r18);
            if (TopicsTabsView.m13334$$Nest$fgetlastSelectedTopicId(this.this$0) == TopicsTabsView.m13332$$Nest$fgetcurrentTopicId(this.this$0)) goto L15;
            TopicsTabsView r2 = this.this$0;
            TopicsTabsView.m13340$$Nest$fputanimateFromSelectedTopicId(r2, TopicsTabsView.m13334$$Nest$fgetlastSelectedTopicId(r2));
            this.animateTab.force(0.0f);
        L15:
            TopicsTabsView r22 = this.this$0;
            TopicsTabsView.m13342$$Nest$fputlastSelectedTopicId(r22, TopicsTabsView.m13332$$Nest$fgetcurrentTopicId(r22));
            HorizontalTabView r23 = null;
            HorizontalTabView r3 = null;
            int r4 = 0;
        L17:
            if (r4 >= getChildCount()) goto L30;
            View r5 = getChildAt(r4);
            if ((r5 instanceof HorizontalTabView) == false) goto L29;
            HorizontalTabView r52 = (HorizontalTabView) r5;
            if (HorizontalTabView.m13354$$Nest$fgetisAdd(r52) == true) goto L29;
            if (r52.getTopicId() != TopicsTabsView.m13332$$Nest$fgetcurrentTopicId(this.this$0)) goto L27;
            r23 = r52;
        L27:
            if (r52.getTopicId() != TopicsTabsView.m13329$$Nest$fgetanimateFromSelectedTopicId(this.this$0)) goto L29;
            r3 = r52;
        L29:
            r4 = r4 + 1;
            goto L17
        L30:
            if (r23 == null) goto L35;
            this.lineRect.set(r23.getX() + AndroidUtilities.dp(1.0f), r23.getY() + AndroidUtilities.dp(4.0f), (r23.getX() + r23.getWidth()) - AndroidUtilities.dp(1.0f), (r23.getY() + getHeight()) - AndroidUtilities.dp(4.0f));
            if (r3 == null) goto L34;
            RectF r24 = AndroidUtilities.rectTmp;
            r24.set(r3.getX() + AndroidUtilities.dp(1.0f), r3.getY() + AndroidUtilities.dp(4.0f), (r3.getX() + r3.getWidth()) - AndroidUtilities.dp(1.0f), (r3.getY() + getHeight()) - AndroidUtilities.dp(4.0f));
            AndroidUtilities.lerp(r24, this.lineRect, this.animateTab.set(1.0f), this.lineRect);
        L34:
            this.linePaint.setColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), 31));
            r1.drawRoundRect(this.lineRect, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.linePaint);
        L35:
            if (r13 == false) goto L49;
            r1.save();
            if (r8 <= 0.0f) goto L40;
            RectF r32 = AndroidUtilities.rectTmp;
            r32.set(0.0f, 0.0f, AndroidUtilities.dp(12.0f), getHeight());
            this.clip.draw(r1, r32, 0, r8);
        L40:
            if (r9 <= 0.0f) goto L42;
            RectF r33 = AndroidUtilities.rectTmp;
            r33.set(getWidth() - AndroidUtilities.dp(12.0f), 0.0f, getWidth(), getHeight());
            this.clip.draw(r1, r33, 2, r9);
        L42:
            r1.restore();
            r1.restore();
            return;
        L49:
            return;
        L11:
            r1 = r18;
            goto L12
        L5:
            if (r9 > 0.0f) goto L8;
            r13 = false;
            goto L9
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r1) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.TopicsTabsView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TopicsTabsView this$0;

        public AnonymousClass2(TopicsTabsView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (TopicsTabsView.m13347$$Nest$misLoadingVisible(this.this$0) == false) goto L6;
            TopicsTabsView.m13348$$Nest$mloadMore(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TopicsTabsView$3, reason: invalid class name */
    public class AnonymousClass3 extends UniversalRecyclerView {
        private final AnimatedFloat animatedClip;
        private final GradientClip clip;
        private Drawable pinIcon;
        private int pinIconColor;
        private final Paint pinnedBackgroundPaint;
        final /* synthetic */ TopicsTabsView this$0;

        public AnonymousClass3(TopicsTabsView r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
            this.clip = new GradientClip();
            this.animatedClip = new AnimatedFloat(this, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.pinnedBackgroundPaint = new Paint(1);
        }

        private void drawPinnedBackground(Canvas r8) {
            float r0 = getHeight();
            float r1 = 0.0f;
            int r2 = 0;
        L4:
            if (r2 >= getChildCount()) goto L18;
            View r3 = getChildAt(r2);
            if ((r3 instanceof VerticalTabView) == false) goto L16;
            VerticalTabView r32 = (VerticalTabView) r3;
            if (VerticalTabView.m13367$$Nest$fgetpinned(r32) == false) goto L16;
            if (r0 <= r32.getY()) goto L14;
            r0 = r32.getY();
            getChildAdapterPosition(r32);
        L14:
            if (r1 >= (r32.getY() + r32.getHeight())) goto L16;
            r1 = r32.getY() + r32.getHeight();
            getChildAdapterPosition(r32);
        L16:
            r2 = r2 + 1;
            goto L4
        L18:
            if (r1 <= r0) goto L32;
            this.pinnedBackgroundPaint.setColor(Theme.getColor(Theme.key_chats_pinnedOverlay, this.resourcesProvider));
            RectF r22 = AndroidUtilities.rectTmp;
            r22.set((getWidth() - AndroidUtilities.dp(56.0f)) / 2.0f, r0, (getWidth() + AndroidUtilities.dp(56.0f)) / 2.0f, r1);
            r8.drawRoundRect(r22, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.pinnedBackgroundPaint);
            if (this.pinIcon != null) goto L22;
            this.pinIcon = getContext().getResources().getDrawable(R.drawable.msg_limit_pin).mutate();
        L22:
            int r02 = Theme.getColor(Theme.key_chats_pinnedIcon, this.resourcesProvider);
            if (this.pinIconColor == r02) goto L25;
            Drawable r12 = this.pinIcon;
            this.pinIconColor = r02;
            r12.setColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN));
        L25:
            this.pinIcon.setBounds((int) (r22.left + AndroidUtilities.dp(4.0f)), (int) (r22.top + AndroidUtilities.dp(2.66f)), (int) (r22.left + AndroidUtilities.dp(13.66f)), (int) (r22.top + AndroidUtilities.dp(12.32f)));
            this.pinIcon.draw(r8);
            return;
        }

        @Override // org.telegram.ui.Components.UniversalRecyclerView, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r12) {
            float r0 = this.animatedClip.set(canScrollVertically(-1));
            if (r0 <= 0.0f) goto L5;
            Canvas r4 = r12;
            r4.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
        L6:
            drawPinnedBackground(r4);
            super.dispatchDraw(r4);
            if (r0 <= 0.0f) goto L10;
            r4.save();
            RectF r122 = AndroidUtilities.rectTmp;
            r122.set(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(12.0f));
            this.clip.draw(r4, r122, 1, r0);
            r4.restore();
            r4.restore();
            return;
        L10:
            return;
        L5:
            r4 = r12;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.TopicsTabsView$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TopicsTabsView this$0;

        public AnonymousClass4(TopicsTabsView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (TopicsTabsView.m13347$$Nest$misLoadingVisible(this.this$0) == false) goto L6;
            TopicsTabsView.m13348$$Nest$mloadMore(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.TopicsTabsView$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ TopicsTabsView this$0;
        final /* synthetic */ boolean val$side;

        /* renamed from: $r8$lambda$QU4UlYL-oh8H901kHxU2cma0lRE, reason: not valid java name */
        public static /* synthetic */ void m13349$r8$lambda$QU4UlYLoh8H901kHxU2cma0lRE(AnonymousClass5 r0) {
            r0.lambda$onAnimationEnd$0();
        }

        public AnonymousClass5(TopicsTabsView r1, boolean r2) {
            this.this$0 = r1;
            this.val$side = r2;
        }

        private /* synthetic */ void lambda$onAnimationEnd$0() {
            if (TopicsTabsView.m13347$$Nest$misLoadingVisible(this.this$0) == false) goto L6;
            TopicsTabsView.m13348$$Nest$mloadMore(this.this$0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            if (TopicsTabsView.m13330$$Nest$fgetanimator(this.this$0) != r5) goto L19;
            TopicsTabsView r52 = this.this$0;
            if (this.val$side == false) goto L7;
            float r0 = 1.0f;
        L8:
            TopicsTabsView.m13345$$Nest$fputsidemenuT(r52, r0);
            this.this$0.updateSidemenuPosition();
            TopicsTabsView.m13344$$Nest$fputsidemenuAnimating(this.this$0, false);
            ImageView r53 = TopicsTabsView.m13338$$Nest$fgettoggleButtonSide(this.this$0);
            if (TopicsTabsView.m13339$$Nest$fgettopicBottom(this.this$0) == false) goto L11;
            int r02 = R.drawable.menu_sidebar_top;
        L12:
            r53.setImageResource(r02);
            TopicsTabsView.m13341$$Nest$fputanimator(this.this$0, null);
            MessagesController.getInstance(TopicsTabsView.m13331$$Nest$fgetcurrentAccount(this.this$0)).getMainSettings().edit().putBoolean("topicssidetabs" + TopicsTabsView.m13333$$Nest$fgetdialogId(this.this$0), TopicsTabsView.m13337$$Nest$fgetsidemenuEnabled(this.this$0)).putBoolean("topicssidetabsb" + TopicsTabsView.m13333$$Nest$fgetdialogId(this.this$0), TopicsTabsView.m13339$$Nest$fgettopicBottom(this.this$0)).apply();
            if (TopicsTabsView.m13335$$Nest$fgetpendingSidemenu(this.this$0) != null) goto L15;
        L17:
            AndroidUtilities.runOnUIThread(new TopicsTabsView$5$$ExternalSyntheticLambda0(this));
            return;
        L15:
            if (this.val$side == TopicsTabsView.m13335$$Nest$fgetpendingSidemenu(this.this$0).booleanValue()) goto L17;
            boolean r54 = TopicsTabsView.m13335$$Nest$fgetpendingSidemenu(this.this$0).booleanValue();
            TopicsTabsView.m13343$$Nest$fputpendingSidemenu(this.this$0, null);
            TopicsTabsView.m13346$$Nest$manimateSidemenuTo(this.this$0, r54);
            goto L17
        L11:
            r02 = R.drawable.menu_sidebar_bottom;
            goto L12
        L7:
            r0 = 0.0f;
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.TopicsTabsView$6, reason: invalid class name */
    public class AnonymousClass6 extends Drawable {
        private RectF bound;
        private Paint paint;
        final /* synthetic */ TopicsTabsView this$0;

        public AnonymousClass6(TopicsTabsView r3) {
            this.this$0 = r3;
            this.paint = new Paint(1);
            this.bound = new RectF();
            this.paint.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, TopicsTabsView.m13336$$Nest$fgetresourcesProvider(r3)));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r4) {
            this.bound.set(getBounds());
            this.bound.inset(AndroidUtilities.dp(1.0f), (this.bound.height() - AndroidUtilities.dp(28.0f)) / 2.0f);
            r4.drawRoundRect(this.bound, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.paint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class BotNewTopicDrawable extends Drawable {
        private final Drawable drawable;
        private final Paint paint;
        private final RectF rectF;

        public BotNewTopicDrawable(Context r3) {
            this.paint = new Paint(1);
            this.rectF = new RectF();
            this.drawable = r3.getResources().getDrawable(R.drawable.menu_topic_add).mutate();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r5) {
            r5.drawRoundRect(this.rectF, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.paint);
            this.drawable.draw(r5);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r4) {
            super.onBoundsChange(r4);
            this.rectF.set(r4);
            int r0 = r4.centerX() - AndroidUtilities.dp(12.0f);
            int r42 = r4.centerY() - AndroidUtilities.dp(12.0f);
            this.drawable.setBounds(r0, r42, AndroidUtilities.dp(24.0f) + r0, AndroidUtilities.dp(24.0f) + r42);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.paint.setAlpha(r2);
            this.drawable.setAlpha(r2);
        }

        public void setColor(int r1) {
            this.paint.setColor(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class HorizontalTabView extends FrameLayout {
        private int addW;
        private AvatarSpan avatarSpan;
        private ValueAnimator counterAnimator;
        private int counterBackgroundColorKey;
        private final AnimatedTextView.AnimatedTextDrawable counterText;
        private final View counterView;
        int counterViewX;
        private final int currentAccount;
        private final ImageView imageView;
        private boolean isAdd;
        private boolean lastMention;
        private boolean lastReactions;
        private int lastUnread;
        private CharSequence mentionString;
        private boolean mono;
        private boolean pinned;
        private CharSequence reactionString;
        private boolean reorder;
        private final Theme.ResourcesProvider resourcesProvider;
        private ValueAnimator selectAnimator;
        private float selectT;
        private boolean selected;
        private final AnimatedFloat shakeAlpha;
        private Shaker shaker;
        private boolean staticImage;
        private final LinkSpanDrawable.LinksTextView textView;
        private long topicId;

        /* renamed from: org.telegram.ui.Components.TopicsTabsView$HorizontalTabView$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            private final AnimatedPaint backgroundPaint;
            final /* synthetic */ HorizontalTabView this$0;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            public AnonymousClass1(HorizontalTabView r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$0 = r1;
                this.val$resourcesProvider = r3;
                super(r2);
                this.backgroundPaint = new AnimatedPaint(this, r3);
                HorizontalTabView.m13352$$Nest$fgetcounterText(r1).setCallback(this);
            }

            @Override // android.view.View
            public void dispatchDraw(Canvas r8) {
                float r0 = HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0).isNotEmpty();
                if (r0 <= 0.0f) goto L5;
                float r2 = AndroidUtilities.lerp(0.6f, 1.0f, r0);
                float r3 = Math.max(AndroidUtilities.dp(16.66f), HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0).getCurrentWidth() + AndroidUtilities.dp(10.0f));
                RectF r4 = AndroidUtilities.rectTmp;
                r4.set(0.0f, 0.0f, r3, getHeight());
                r8.save();
                r8.scale(r2, r2, r4.centerX(), r4.centerY());
                r8.drawRoundRect(r4, AndroidUtilities.dp(8.33f), AndroidUtilities.dp(8.33f), this.backgroundPaint.setByKey(HorizontalTabView.m13351$$Nest$fgetcounterBackgroundColorKey(this.this$0)).blendTo(HorizontalTabView.m13359$$Nest$mgetTextColor(this.this$0), HorizontalTabView.m13356$$Nest$fgetselectT(this.this$0)).multAlpha(r0));
                HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0).setBounds(r4);
                HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0).setAlpha((int) (r0 * 255.0f));
                HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0).setTextColor(Theme.getColor(Theme.key_chats_unreadCounterText, this.val$resourcesProvider));
                HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0).draw(r8);
                r8.restore();
            L5:
                super.dispatchDraw(r8);
            }

            @Override // android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) Math.max(AndroidUtilities.dp(16.66f), HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0).getAnimateToWidth() + AndroidUtilities.dp(10.0f)), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(16.66f), TLObject.FLAG_30));
            }

            @Override // android.view.View
            public boolean verifyDrawable(Drawable r2) {
                if (HorizontalTabView.m13352$$Nest$fgetcounterText(this.this$0) != r2) goto L5;
                return true;
            L5:
                if (super.verifyDrawable(r2) == true) goto L11;
                return false;
            L11:
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Components.TopicsTabsView$HorizontalTabView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ HorizontalTabView this$0;
            final /* synthetic */ boolean val$selected;

            public AnonymousClass2(HorizontalTabView r1, boolean r2) {
                this.this$0 = r1;
                this.val$selected = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                HorizontalTabView r22 = this.this$0;
                if (this.val$selected == false) goto L5;
                float r0 = 1.0f;
            L6:
                HorizontalTabView.m13358$$Nest$fputselectT(r22, r0);
                HorizontalTabView.m13360$$Nest$mupdateTextColor(this.this$0);
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Components.TopicsTabsView$HorizontalTabView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ HorizontalTabView this$0;

            public AnonymousClass3(HorizontalTabView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                HorizontalTabView.m13353$$Nest$fgetcounterView(this.this$0).setScaleX(1.0f);
                HorizontalTabView.m13353$$Nest$fgetcounterView(this.this$0).setScaleY(1.0f);
                HorizontalTabView.m13353$$Nest$fgetcounterView(this.this$0).invalidate();
            }
        }

        public static class Factory extends UItem.UItemFactory<HorizontalTabView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asAdd() {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = -2;
                r0.longValue = -2;
                r0.object = null;
                return r0;
            }

            public static UItem asAll(boolean r2, boolean r3) {
                UItem r22 = UItem.ofFactory(Factory.class);
                r22.id = 0;
                r22.longValue = 0;
                r22.object = null;
                r22.accent = r3;
                return r22;
            }

            public static UItem asLoading(int r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.red = true;
                return r0;
            }

            public static UItem asTab(long r1, TLRPC.TL_forumTopic r3, boolean r4) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.dialogId = r1;
                r0.id = r3.id;
                r0.object = r3;
                if (r4 == false) goto L5;
                r0.longValue = DialogObject.getPeerDialogId(r3.from_id);
                r0.withUsername = false;
            L5:
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r4, UItem r5, boolean r6, UniversalAdapter r7, UniversalRecyclerView r8) {
                HorizontalTabView r42 = (HorizontalTabView) r4;
                boolean r62 = false;
                if (r5.red == false) goto L5;
                r42.setLoading();
            L22:
                if (BitwiseUtils.hasFlag(r5.flags, 8) == false) goto L24;
                int r3 = AndroidUtilities.dp(10.0f);
            L25:
                HorizontalTabView.m13357$$Nest$fputaddW(r42, r3);
                if (r8 != null) goto L28;
            L32:
                r42.setReorder(r62);
                return;
            L28:
                if (r8.isReorderAllowed() == false) goto L32;
                if (HorizontalTabView.m13355$$Nest$fgetpinned(r42) == false) goto L32;
                r62 = true;
                goto L32
            L24:
                r3 = 0;
                goto L25
            L5:
                Object r32 = r5.object;
                if (r32 != null) goto L16;
                if (r5.id != (-2)) goto L11;
                r42.setAdd();
                goto L22
            L11:
                if ((r5.flags & 1) == 0) goto L13;
                boolean r33 = true;
            L14:
                r42.setAll(r33, r5.accent, r5.checked);
                goto L22
            L13:
                r33 = false;
                goto L14
            L16:
                if ((r32 instanceof TLRPC.TL_forumTopic) == false) goto L22;
                boolean r0 = r5.withUsername;
                long r1 = r5.dialogId;
                if (r0 == true) goto L20;
                r42.setMf(r1, (TLRPC.TL_forumTopic) r32, r5.checked);
                goto L22
            L20:
                r42.set(r1, (TLRPC.TL_forumTopic) r32, r5.checked);
                goto L22
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public HorizontalTabView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new HorizontalTabView(r1, r3, r5);
            }
        }

        /* renamed from: $r8$lambda$dIab7aQbfEvZj3B-Q2VkfO8Eij8, reason: not valid java name */
        public static /* synthetic */ void m13350$r8$lambda$dIab7aQbfEvZj3BQ2VkfO8Eij8(HorizontalTabView r0, ValueAnimator r1) {
            r0.lambda$animateCounterBounce$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$eP5fsf9aT6pgBv6dp0YJCirxrg4(HorizontalTabView r0, ValueAnimator r1) {
            r0.lambda$setSelected$0(r1);
        }

        /* renamed from: -$$Nest$fgetcounterBackgroundColorKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13351$$Nest$fgetcounterBackgroundColorKey(HorizontalTabView r0) {
            return r0.counterBackgroundColorKey;
        }

        /* renamed from: -$$Nest$fgetcounterText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedTextView.AnimatedTextDrawable m13352$$Nest$fgetcounterText(HorizontalTabView r0) {
            return r0.counterText;
        }

        /* renamed from: -$$Nest$fgetcounterView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m13353$$Nest$fgetcounterView(HorizontalTabView r0) {
            return r0.counterView;
        }

        /* renamed from: -$$Nest$fgetisAdd, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13354$$Nest$fgetisAdd(HorizontalTabView r0) {
            return r0.isAdd;
        }

        /* renamed from: -$$Nest$fgetpinned, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13355$$Nest$fgetpinned(HorizontalTabView r0) {
            return r0.pinned;
        }

        /* renamed from: -$$Nest$fgetselectT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13356$$Nest$fgetselectT(HorizontalTabView r0) {
            return r0.selectT;
        }

        /* renamed from: -$$Nest$fputaddW, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13357$$Nest$fputaddW(HorizontalTabView r0, int r1) {
            r0.addW = r1;
        }

        /* renamed from: -$$Nest$fputselectT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13358$$Nest$fputselectT(HorizontalTabView r0, float r1) {
            r0.selectT = r1;
        }

        /* renamed from: -$$Nest$mgetTextColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13359$$Nest$mgetTextColor(HorizontalTabView r0) {
            return r0.getTextColor();
        }

        /* renamed from: -$$Nest$mupdateTextColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13360$$Nest$mupdateTextColor(HorizontalTabView r0) {
            r0.updateTextColor();
        }

        public HorizontalTabView(Context r9, int r10, Theme.ResourcesProvider r11) {
            super(r9);
            this.shakeAlpha = new AnimatedFloat(this, 360, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.pinned = false;
            this.isAdd = false;
            this.mono = false;
            this.staticImage = false;
            this.counterBackgroundColorKey = Theme.key_chats_unreadCounter;
            this.addW = 0;
            this.currentAccount = r10;
            this.resourcesProvider = r11;
            setClipChildren(false);
            setClipToPadding(false);
            LinkSpanDrawable.LinksTextView r102 = new LinkSpanDrawable.LinksTextView(r9, r11);
            this.textView = r102;
            r102.setTextSize(1, 14.0f);
            r102.setTypeface(AndroidUtilities.bold());
            addView(r102, LayoutHelper.createFrame(-2, -2.0f, 19, 11.0f, 0.0f, 11.0f, 0.0f));
            ScaleStateListAnimator.apply(r102);
            ImageView r103 = new ImageView(r9);
            this.imageView = r103;
            addView(r103, LayoutHelper.createFrame(34, 34, 17));
            AnimatedTextView.AnimatedTextDrawable r104 = new AnimatedTextView.AnimatedTextDrawable();
            this.counterText = r104;
            r104.setTextSize(AndroidUtilities.dp(11.0f));
            r104.setTypeface(AndroidUtilities.bold());
            r104.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r104.setGravity(17);
            View r105 = new AnonymousClass1(this, r9, r11);
            this.counterView = r105;
            addView(r105, LayoutHelper.createFrame(-2, -2.0f, 21, 4.66f, 0.0f, 11.0f, 0.0f));
            ScaleStateListAnimator.apply(r105);
            updateTextColor();
        }

        private void animateCounterBounce() {
            ValueAnimator r0 = this.counterAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.counterAnimator = null;
        L5:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.counterAnimator = r02;
            r02.addUpdateListener(new TopicsTabsView$HorizontalTabView$$ExternalSyntheticLambda1(this));
            this.counterAnimator.addListener(new AnonymousClass3(this));
            this.counterAnimator.setInterpolator(new OvershootInterpolator(2.0f));
            this.counterAnimator.setDuration(200);
            this.counterAnimator.start();
        }

        private int getMeasuringWidth() {
            int r0 = (int) Math.max(AndroidUtilities.dp(16.66f), this.counterText.getAnimateToWidth() + AndroidUtilities.dp(10.0f));
            int r2 = AndroidUtilities.dp(11.0f) + this.textView.getMeasuredWidth();
            if (this.counterText.getAnimateToWidth() <= 0.0f) goto L5;
            int r3 = AndroidUtilities.dp(4.66f) + r0;
        L7:
            return ((r2 + r3) + AndroidUtilities.dp(11.0f)) + this.addW;
        L5:
            r3 = 0;
            goto L7
        }

        private int getTextColor() {
            int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider);
            int r1 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
            if (this.isAdd == false) goto L5;
            float r3 = 1.0f;
        L7:
            return ColorUtils.blendARGB(r0, r1, r3);
        L5:
            r3 = this.selectT;
            goto L7
        }

        private /* synthetic */ void lambda$animateCounterBounce$1(ValueAnimator r4) {
            this.counterView.setScaleX(Math.max(1.0f, ((Float) r4.getAnimatedValue()).floatValue()));
            this.counterView.setScaleY(Math.max(1.0f, ((Float) r4.getAnimatedValue()).floatValue()));
            this.counterView.invalidate();
        }

        private /* synthetic */ void lambda$setSelected$0(ValueAnimator r1) {
            this.selectT = ((Float) r1.getAnimatedValue()).floatValue();
            updateTextColor();
        }

        private void setCounter(boolean r9, int r10, boolean r11, boolean r12, boolean r13) {
            if (r12 == false) goto L8;
            this.counterBackgroundColorKey = Theme.key_dialogReactionMentionBackground;
            if (this.reactionString != null) goto L7;
            SpannableStringBuilder r92 = new SpannableStringBuilder("❤️");
            ColoredImageSpan r6 = new ColoredImageSpan(R.drawable.mini_like_filled);
            r6.setScale(0.8f, 0.8f);
            r6.spaceScaleX = 0.5f;
            r6.translate(-AndroidUtilities.dp(3.0f), 0.0f);
            r92.setSpan(r6, 0, r92.length(), 33);
            this.reactionString = r92;
        L7:
            this.counterText.setText(this.reactionString, r13);
        L22:
            if (r13 == true) goto L24;
        L32:
            this.lastUnread = r10;
            this.lastMention = r11;
            this.lastReactions = r12;
            this.counterView.invalidate();
            if (getMeasuringWidth() == getMeasuredWidth()) goto L36;
            requestLayout();
            return;
        L36:
            return;
        L24:
            if (this.lastUnread >= r10) goto L26;
        L31:
            animateCounterBounce();
            goto L32
        L26:
            if (this.lastMention == true) goto L29;
            if (r11 == true) goto L31;
        L29:
            if (this.lastReactions == true) goto L32;
            if (r12 == false) goto L32;
        L8:
            if (r11 == false) goto L16;
            if (r9 == false) goto L11;
            int r93 = Theme.key_chats_unreadCounterMuted;
        L12:
            this.counterBackgroundColorKey = r93;
            if (this.mentionString != null) goto L15;
            SpannableStringBuilder r94 = new SpannableStringBuilder("@");
            ColoredImageSpan r62 = new ColoredImageSpan(R.drawable.mini_mention_filled_16);
            r62.setScale(0.8f, 0.8f);
            r62.spaceScaleX = 0.5f;
            r62.translate(-AndroidUtilities.dp(3.0f), 0.0f);
            r94.setSpan(r62, 0, 1, 33);
            this.mentionString = r94;
        L15:
            this.counterText.setText(this.mentionString, r13);
            goto L22
        L11:
            r93 = Theme.key_chats_unreadCounter;
            goto L12
        L16:
            if (r10 <= 0) goto L21;
            if (r9 == false) goto L19;
            int r95 = Theme.key_chats_unreadCounterMuted;
        L20:
            this.counterBackgroundColorKey = r95;
            this.counterText.setText(LocaleController.formatNumber(r10, ','), r13);
            goto L22
        L19:
            r95 = Theme.key_chats_unreadCounter;
            goto L20
        L21:
            this.counterBackgroundColorKey = Theme.key_chats_unreadCounterMuted;
            this.counterText.setText(_UrlKt.FRAGMENT_ENCODE_SET, r13);
            goto L22
        }

        private void setLayout(boolean r2) {
            if (this.mono != r2) goto L5;
            return;
        L5:
            this.mono = r2;
        }

        private void setPinned(boolean r1, boolean r2) {
            if (this.pinned == r1) goto L6;
            this.pinned = r1;
            return;
        }

        private void updateTextColor() {
            int r0 = getTextColor();
            this.textView.setTextColor(r0);
            this.textView.setEmojiColor(r0);
            this.counterView.invalidate();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r5, View r6, long r7) {
            if (r6 != this.textView) goto L13;
            r5.save();
            float r0 = this.shakeAlpha.set(this.reorder);
            if (r0 > 0.0f) goto L7;
        L10:
            boolean r4 = super.drawChild(r5, r6, r7);
            r5.restore();
            return r4;
        L7:
            if (this.shaker != null) goto L9;
            this.shaker = new Shaker(this);
        L9:
            r5.translate(getWidth() / 2.0f, getHeight() / 2.0f);
            this.shaker.concat(r5, r0);
            r5.translate((-getWidth()) / 2.0f, (-getHeight()) / 2.0f);
            goto L10
        L13:
            return super.drawChild(r5, r6, r7);
        }

        public long getTopicId() {
            return this.topicId;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            int r72 = r7 - r5;
            int r82 = r8 - r6;
            int r42 = (r72 - this.imageView.getMeasuredWidth()) / 2;
            int r52 = (r82 - this.imageView.getMeasuredHeight()) / 2;
            ImageView r62 = this.imageView;
            r62.layout(r42, r52, r62.getMeasuredWidth() + r42, this.imageView.getMeasuredHeight() + r52);
            int r83 = r82 / 2;
            this.textView.layout(AndroidUtilities.dp(11.0f), r83 - (this.textView.getMeasuredHeight() / 2), AndroidUtilities.dp(11.0f) + this.textView.getMeasuredWidth(), (this.textView.getMeasuredHeight() / 2) + r83);
            float r43 = this.counterText.getAnimateToWidth();
            View r0 = this.counterView;
            if (r43 <= 0.0f) goto L5;
            r0.layout((r72 - AndroidUtilities.dp(11.0f)) - this.counterView.getMeasuredWidth(), r83 - (this.counterView.getMeasuredHeight() / 2), r72 - AndroidUtilities.dp(11.0f), r83 + (this.counterView.getMeasuredHeight() / 2));
        L7:
            if (this.counterViewX != 0) goto L9;
        L11:
            this.counterViewX = this.counterView.getLeft();
            return;
        L9:
            if (this.counterView.getLeft() == this.counterViewX) goto L11;
            this.counterView.setTranslationX((-r4.getLeft()) + this.counterViewX);
            this.counterView.animate().translationX(0.0f).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            goto L11
        L5:
            r0.layout((AndroidUtilities.dp(11.0f) + this.textView.getMeasuredWidth()) + AndroidUtilities.dp(4.66f), r83 - (this.counterView.getMeasuredHeight() / 2), ((AndroidUtilities.dp(11.0f) + this.textView.getMeasuredWidth()) + AndroidUtilities.dp(4.66f)) + this.counterView.getMeasuredWidth(), r83 + (this.counterView.getMeasuredHeight() / 2));
            goto L7
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            this.textView.measure(r2, r3);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(getMeasuringWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36.0f), TLObject.FLAG_30));
        }

        public void set(long r11, TLRPC.TL_forumTopic r13, boolean r14) {
            setLayout(false);
            long r1 = this.topicId;
            int r3 = r13.id;
            if (r1 != r3) goto L5;
            boolean r9 = true;
        L6:
            this.topicId = r3;
            this.staticImage = false;
            this.imageView.setVisibility(8);
            this.textView.setVisibility(0);
            SpannableStringBuilder r12 = new SpannableStringBuilder();
            if (r13.id != 1) goto L14;
            r12.append("#");
            if (r13.hidden == false) goto L11;
            String r32 = "\u200b";
        L12:
            r12.append(r32);
            ColoredImageSpan r33 = new ColoredImageSpan(R.drawable.msg_filled_general);
            r33.setScale(0.66f, 0.66f);
            r12.setSpan(r33, 0, 1, 18);
        L17:
            if (r13.hidden == true) goto L19;
            r12.append(r13.title);
        L19:
            this.textView.setText(r12);
            setSelected(r14);
            updateTextColor();
            setCounter(MessagesController.getInstance(this.currentAccount).isDialogMuted(r11, this.topicId), r13.unread_count, false, false, r9);
            setPinned(r13.pinned, r9);
            return;
        L11:
            r32 = " ";
            goto L12
        L14:
            if (r13.icon_emoji_id == 0) goto L17;
            r12.append("x ");
            r12.setSpan(new AnimatedEmojiSpan(r13.icon_emoji_id, this.textView.getPaint().getFontMetricsInt()), 0, 1, 33);
            goto L17
        L5:
            r9 = false;
            goto L6
        }

        public void setAdd() {
            setLayout(false);
            this.topicId = 0;
            this.isAdd = true;
            this.staticImage = false;
            this.imageView.setVisibility(8);
            this.textView.setVisibility(0);
            SpannableStringBuilder r2 = new SpannableStringBuilder("e\u200b");
            r2.setSpan(new ColoredImageSpan(R.drawable.menu_topic_add), 0, 1, 33);
            this.textView.setText(r2);
            setSelected(false);
            updateTextColor();
            setCounter(true, 0, false, false, false);
            setPinned(false, false);
        }

        public void setAll(boolean r9, boolean r10, boolean r11) {
            setLayout(r10);
            this.topicId = 0;
            this.isAdd = false;
            this.staticImage = true;
            ImageView r0 = this.imageView;
            int r1 = 8;
            if (r9 == false) goto L5;
            int r2 = 0;
        L6:
            r0.setVisibility(r2);
            if (r9 == false) goto L9;
            BotNewTopicDrawable r02 = new BotNewTopicDrawable(getContext());
            r02.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider));
            this.imageView.setImageDrawable(r02);
        L9:
            LinkSpanDrawable.LinksTextView r03 = this.textView;
            if (r9 == false) goto L12;
            int r22 = R.string.BotForumNewTopic;
        L13:
            r03.setText(LocaleController.getString(r22));
            LinkSpanDrawable.LinksTextView r04 = this.textView;
            if (r9 == true) goto L17;
            r1 = 0;
        L17:
            r04.setVisibility(r1);
            setSelected(r11);
            updateTextColor();
            setCounter(true, 0, false, false, false);
            setPinned(false, false);
            return;
        L12:
            r22 = R.string.AllTopicsShort;
            goto L13
        L5:
            r2 = 8;
            goto L6
        }

        public void setLoading() {
            setLayout(false);
            this.topicId = -1;
            this.staticImage = true;
            this.imageView.setVisibility(8);
            this.textView.setVisibility(0);
            SpannableStringBuilder r2 = new SpannableStringBuilder("x");
            LoadingSpan r3 = new LoadingSpan(this.textView, AndroidUtilities.dp(42.0f));
            r3.setScaleY(0.95f);
            r2.setSpan(r3, 0, 1, 33);
            this.textView.setText(r2);
            setSelected(false);
            updateTextColor();
            setCounter(true, 0, false, false, false);
            setPinned(false, false);
        }

        public void setMf(long r12, TLRPC.TL_forumTopic r14, boolean r15) {
            setLayout(true);
            long r1 = DialogObject.getPeerDialogId(r14.from_id);
            if (this.topicId != r1) goto L5;
            boolean r10 = true;
        L6:
            this.topicId = r1;
            this.staticImage = false;
            this.imageView.setVisibility(8);
            this.textView.setVisibility(0);
            if (this.avatarSpan != null) goto L9;
            AvatarSpan r3 = new AvatarSpan(this.textView, this.currentAccount, 18.0f);
            this.avatarSpan = r3;
            r3.usePaintAlpha = false;
        L9:
            SpannableStringBuilder r32 = new SpannableStringBuilder();
            TLObject r5 = MessagesController.getInstance(this.currentAccount).getUserOrChat(r1);
            if (r5 == null) goto L12;
            r32.append("x  ");
            this.avatarSpan.setObject(r5);
            r32.setSpan(this.avatarSpan, 0, 1, 33);
        L12:
            r32.append(DialogObject.getName(r1));
            LinkSpanDrawable.LinksTextView r0 = this.textView;
            r0.setText(TextUtils.ellipsize(r32, r0.getPaint(), AndroidUtilities.dp(150.0f), TextUtils.TruncateAt.END));
            setSelected(r15);
            setCounter(MessagesController.getInstance(this.currentAccount).isDialogMuted(r12, r1), r14.unread_count, false, false, r10);
            setPinned(false, r10);
            return;
        L5:
            r10 = false;
            goto L6
        }

        public void setReorder(boolean r1) {
            this.reorder = r1;
            invalidate();
        }

        @Override // android.view.View
        public void setSelected(boolean r5) {
            if (this.selected != r5) goto L5;
            return;
        L5:
            this.selected = r5;
            ValueAnimator r0 = this.selectAnimator;
            if (r0 == null) goto L8;
            r0.cancel();
        L8:
            float r02 = this.selectT;
            if (r5 == false) goto L11;
            float r1 = 1.0f;
        L12:
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
            this.selectAnimator = r03;
            r03.addUpdateListener(new TopicsTabsView$HorizontalTabView$$ExternalSyntheticLambda0(this));
            this.selectAnimator.addListener(new AnonymousClass2(this, r5));
            this.selectAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.selectAnimator.setDuration(320);
            this.selectAnimator.start();
            return;
        L11:
            r1 = 0.0f;
            goto L12
        }
    }

    public enum Position extends Enum<Position> {
        private static final /* synthetic */ Position[] $VALUES = null;
        public static final Position BOTTOM = null;
        public static final Position LEFT = null;
        public static final Position TOP = null;

        private static /* synthetic */ Position[] $values() {
            return new Position[]{TOP, LEFT, BOTTOM};
        }

        static {
            TOP = new Position("TOP", 0);
            LEFT = new Position("LEFT", 1);
            BOTTOM = new Position("BOTTOM", 2);
            $VALUES = $values();
        }

        Position(String r1, int r2) {
        }

        public static Position valueOf(String r1) {
            return (Position) Enum.valueOf(Position.class, r1);
        }

        public static Position[] values() {
            return (Position[]) $VALUES.clone();
        }
    }

    public static class VerticalTabView extends FrameLayout {
        private final AvatarDrawable avatarDrawable;
        private float countScale;
        private ValueAnimator counterAnimator;
        private int counterBackgroundColorKey;
        private final AnimatedTextView.AnimatedTextDrawable counterText;
        private final int currentAccount;
        private final FrameLayout imageLayoutView;
        private final BackupImageView imageView;
        private final FrameLayout.LayoutParams imageViewParams;
        private boolean isAdd;
        private boolean lastMention;
        private boolean lastReactions;
        private int lastUnread;
        private final LinearLayout layout;
        private final View lineView;
        private LoadingDrawable loadingDrawable;
        private CharSequence mentionString;
        private boolean mono;
        private boolean pinned;
        private CharSequence reactionString;
        private boolean reorder;
        private final Theme.ResourcesProvider resourcesProvider;
        private ValueAnimator selectAnimator;
        private float selectT;
        private boolean selected;
        private Shaker shaker;
        private boolean staticImage;
        private final TextView textView;
        private long topicId;

        /* renamed from: org.telegram.ui.Components.TopicsTabsView$VerticalTabView$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayout {
            private final AnimatedFloat shakeAlpha;
            final /* synthetic */ VerticalTabView this$0;

            public AnonymousClass1(VerticalTabView r3, Context r4) {
                this.this$0 = r3;
                super(r4);
                this.shakeAlpha = new AnimatedFloat(this, 360, CubicBezierInterpolator.EASE_OUT_QUINT);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r5) {
                r5.save();
                float r0 = this.shakeAlpha.set(VerticalTabView.m13368$$Nest$fgetreorder(this.this$0));
                if (r0 > 0.0f) goto L5;
            L8:
                super.dispatchDraw(r5);
                r5.restore();
                return;
            L5:
                if (VerticalTabView.m13369$$Nest$fgetshaker(this.this$0) != null) goto L7;
                VerticalTabView.m13372$$Nest$fputshaker(this.this$0, new Shaker(this));
            L7:
                r5.translate(getWidth() / 2.0f, getHeight() / 2.0f);
                VerticalTabView.m13369$$Nest$fgetshaker(this.this$0).concat(r5, r0);
                r5.translate((-getWidth()) / 2.0f, (-getHeight()) / 2.0f);
                goto L8
            }
        }

        /* renamed from: org.telegram.ui.Components.TopicsTabsView$VerticalTabView$2, reason: invalid class name */
        public class AnonymousClass2 extends FrameLayout {
            private final AnimatedPaint backgroundPaint;
            private final Paint clipPaint;
            final /* synthetic */ VerticalTabView this$0;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

            public AnonymousClass2(VerticalTabView r2, Context r3, Theme.ResourcesProvider r4) {
                this.this$0 = r2;
                this.val$resourcesProvider = r4;
                super(r3);
                Paint r32 = new Paint(1);
                this.clipPaint = r32;
                this.backgroundPaint = new AnimatedPaint(this, r4);
                r32.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                VerticalTabView.m13365$$Nest$fgetcounterText(r2).setCallback(this);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r19) {
                float r8 = VerticalTabView.m13365$$Nest$fgetcounterText(this.this$0).isNotEmpty();
                if (r8 <= 0.0f) goto L6;
                boolean r1 = true;
            L5:
                boolean r10 = r1;
                float r11 = AndroidUtilities.lerp(0.5f, 1.0f, r8) * VerticalTabView.m13363$$Nest$fgetcountScale(this.this$0);
                float r12 = AndroidUtilities.dp(10.0f);
                float r13 = AndroidUtilities.dp(8.33f);
                float r15 = (getWidth() / 2.0f) + AndroidUtilities.dp(12.0f);
                float r2 = AndroidUtilities.dp(12.0f);
                float r16 = Math.max(r13 + r13, VerticalTabView.m13365$$Nest$fgetcounterText(this.this$0).getCurrentWidth() + AndroidUtilities.dp(10.0f));
                if (r10 == false) goto L10;
                float r17 = 2.0f;
                float r14 = r2;
                Canvas r18 = r19;
                r18.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            L11:
                super.dispatchDraw(r19);
                if (r10 == false) goto L14;
                RectF r22 = AndroidUtilities.rectTmp;
                float r3 = r16 / r17;
                r22.set((r15 - r3) - AndroidUtilities.dp(1.33f), r14 - r12, (r3 + r15) + AndroidUtilities.dp(1.33f), r14 + r12);
                AndroidUtilities.scaleRect(r22, r8);
                float r122 = r12 * r8;
                r18.drawRoundRect(r22, r122, r122, this.clipPaint);
                r18.restore();
            L14:
                if (r8 <= 0.0f) goto L17;
                r18.save();
                r18.scale(r11, r11, r15, r14);
                RectF r23 = AndroidUtilities.rectTmp;
                float r162 = r16 / r17;
                r23.set(r15 - r162, r14 - r13, r15 + r162, r14 + r13);
                r18.drawRoundRect(r23, r13, r13, this.backgroundPaint.setByKey(VerticalTabView.m13364$$Nest$fgetcounterBackgroundColorKey(this.this$0), r8));
                VerticalTabView.m13365$$Nest$fgetcounterText(this.this$0).setBounds(r23);
                VerticalTabView.m13365$$Nest$fgetcounterText(this.this$0).setAlpha((int) (r8 * 255.0f));
                VerticalTabView.m13365$$Nest$fgetcounterText(this.this$0).draw(r18);
                r18.restore();
                return;
            L17:
                return;
            L10:
                r18 = r19;
                r17 = 2.0f;
                r14 = r2;
                goto L11
            L6:
                r1 = false;
                goto L5
            }

            @Override // android.view.View
            public boolean verifyDrawable(Drawable r2) {
                if (VerticalTabView.m13365$$Nest$fgetcounterText(this.this$0) != r2) goto L5;
                return true;
            L5:
                if (super.verifyDrawable(r2) == true) goto L11;
                return false;
            L11:
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Components.TopicsTabsView$VerticalTabView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ VerticalTabView this$0;

            public AnonymousClass3(VerticalTabView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                VerticalTabView.m13370$$Nest$fputcountScale(this.this$0, 1.0f);
                VerticalTabView.m13366$$Nest$fgetimageLayoutView(this.this$0).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.TopicsTabsView$VerticalTabView$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ VerticalTabView this$0;
            final /* synthetic */ boolean val$selected;

            public AnonymousClass4(VerticalTabView r1, boolean r2) {
                this.this$0 = r1;
                this.val$selected = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                VerticalTabView r22 = this.this$0;
                if (this.val$selected == false) goto L5;
                float r0 = 1.0f;
            L6:
                VerticalTabView.m13371$$Nest$fputselectT(r22, r0);
                VerticalTabView.m13374$$Nest$mupdateState(this.this$0);
                VerticalTabView.m13373$$Nest$mupdateImageColor(this.this$0);
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        public static class Factory extends UItem.UItemFactory<VerticalTabView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asAdd(boolean r3) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = -2;
                r0.longValue = -2;
                r0.object = null;
                r0.accent = r3;
                return r0;
            }

            public static UItem asAll(boolean r3, boolean r4) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = 0;
                r0.longValue = 0;
                r0.object = null;
                r0.accent = r4;
                r0.flags = r3 ? 1 : 0;
                return r0;
            }

            public static UItem asLoading(int r1) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.red = true;
                r0.checked = false;
                return r0;
            }

            public static UItem asTab(long r1, TLRPC.TL_forumTopic r3, boolean r4) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.dialogId = r1;
                r0.id = r3.id;
                r0.object = r3;
                if (r4 == false) goto L5;
                r0.longValue = DialogObject.getPeerDialogId(r3.from_id);
                r0.withUsername = false;
            L5:
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r5, UItem r6, boolean r7, UniversalAdapter r8, UniversalRecyclerView r9) {
                VerticalTabView r52 = (VerticalTabView) r5;
                boolean r72 = false;
                if (r6.red == false) goto L5;
                r52.setLoading();
            L21:
                if (r9 != null) goto L23;
            L27:
                r52.setReorder(r72);
                return;
            L23:
                if (r9.isReorderAllowed() == false) goto L27;
                if (VerticalTabView.m13367$$Nest$fgetpinned(r52) == false) goto L27;
                r72 = true;
                goto L27
            L5:
                Object r4 = r6.object;
                if (r4 != null) goto L16;
                if (r6.longValue != (-2)) goto L11;
                r52.setAdd(r6.accent, r6.checked);
                goto L21
            L11:
                if ((r6.flags & 1) == 0) goto L13;
                boolean r42 = true;
            L14:
                r52.setAll(r42, r6.accent, r6.checked);
                goto L21
            L13:
                r42 = false;
                goto L14
            L16:
                if ((r4 instanceof TLRPC.TL_forumTopic) == false) goto L21;
                if (r6.withUsername == true) goto L20;
                r52.setMf((TLRPC.TL_forumTopic) r4, r6.checked);
                goto L21
            L20:
                r52.set(r6.dialogId, (TLRPC.TL_forumTopic) r4, r6.checked);
                goto L21
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public VerticalTabView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new VerticalTabView(r1, r3, r5);
            }
        }

        /* renamed from: $r8$lambda$ki7nZX1UYRc-EmPY-N9NucbbIWo, reason: not valid java name */
        public static /* synthetic */ void m13361$r8$lambda$ki7nZX1UYRcEmPYN9NucbbIWo(VerticalTabView r0, ValueAnimator r1) {
            r0.lambda$setSelected$1(r1);
        }

        /* renamed from: $r8$lambda$loo7SqTu8aZpGc7FdidkmQ-c_34, reason: not valid java name */
        public static /* synthetic */ void m13362$r8$lambda$loo7SqTu8aZpGc7FdidkmQc_34(VerticalTabView r0, ValueAnimator r1) {
            r0.lambda$animateCounterBounce$0(r1);
        }

        /* renamed from: -$$Nest$fgetcountScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m13363$$Nest$fgetcountScale(VerticalTabView r0) {
            return r0.countScale;
        }

        /* renamed from: -$$Nest$fgetcounterBackgroundColorKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13364$$Nest$fgetcounterBackgroundColorKey(VerticalTabView r0) {
            return r0.counterBackgroundColorKey;
        }

        /* renamed from: -$$Nest$fgetcounterText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedTextView.AnimatedTextDrawable m13365$$Nest$fgetcounterText(VerticalTabView r0) {
            return r0.counterText;
        }

        /* renamed from: -$$Nest$fgetimageLayoutView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m13366$$Nest$fgetimageLayoutView(VerticalTabView r0) {
            return r0.imageLayoutView;
        }

        /* renamed from: -$$Nest$fgetpinned, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13367$$Nest$fgetpinned(VerticalTabView r0) {
            return r0.pinned;
        }

        /* renamed from: -$$Nest$fgetreorder, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13368$$Nest$fgetreorder(VerticalTabView r0) {
            return r0.reorder;
        }

        /* renamed from: -$$Nest$fgetshaker, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Shaker m13369$$Nest$fgetshaker(VerticalTabView r0) {
            return r0.shaker;
        }

        /* renamed from: -$$Nest$fputcountScale, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13370$$Nest$fputcountScale(VerticalTabView r0, float r1) {
            r0.countScale = r1;
        }

        /* renamed from: -$$Nest$fputselectT, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13371$$Nest$fputselectT(VerticalTabView r0, float r1) {
            r0.selectT = r1;
        }

        /* renamed from: -$$Nest$fputshaker, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13372$$Nest$fputshaker(VerticalTabView r0, Shaker r1) {
            r0.shaker = r1;
        }

        /* renamed from: -$$Nest$mupdateImageColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13373$$Nest$mupdateImageColor(VerticalTabView r0) {
            r0.updateImageColor();
        }

        /* renamed from: -$$Nest$mupdateState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13374$$Nest$mupdateState(VerticalTabView r0) {
            r0.updateState();
        }

        public VerticalTabView(Context r19, int r20, Theme.ResourcesProvider r21) {
            super(r19);
            this.mono = false;
            this.pinned = false;
            this.counterBackgroundColorKey = Theme.key_chats_unreadCounter;
            this.countScale = 1.0f;
            this.topicId = 0;
            this.isAdd = false;
            this.staticImage = false;
            this.currentAccount = r20;
            this.resourcesProvider = r21;
            AnonymousClass1 r4 = new AnonymousClass1(this, r19);
            this.layout = r4;
            r4.setWillNotDraw(false);
            r4.setOrientation(1);
            addView(r4, LayoutHelper.createFrame(-1, -1.0f, 119, 1.0f, 0.0f, 0.0f, 0.0f));
            ScaleStateListAnimator.apply(r4);
            AnimatedTextView.AnimatedTextDrawable r6 = new AnimatedTextView.AnimatedTextDrawable();
            this.counterText = r6;
            r6.setTextSize(AndroidUtilities.dp(11.0f));
            r6.setTypeface(AndroidUtilities.bold());
            r6.setTextColor(Theme.getColor(Theme.key_chats_unreadCounterText, r21));
            r6.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            r6.setGravity(17);
            AnonymousClass2 r62 = new AnonymousClass2(this, r19, r21);
            this.imageLayoutView = r62;
            r62.setWillNotDraw(false);
            r62.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
            r4.addView(r62, LayoutHelper.createLinear(-1, -2, 17));
            BackupImageView r9 = new BackupImageView(r19);
            this.imageView = r9;
            FrameLayout.LayoutParams r10 = LayoutHelper.createFrame(34, 34, 17);
            this.imageViewParams = r10;
            r62.addView(r9, r10);
            this.avatarDrawable = new AvatarDrawable();
            TextView r63 = new TextView(r19);
            this.textView = r63;
            int r92 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r21);
            int r102 = Theme.key_featuredStickers_addButton;
            r63.setTextColor(ColorUtils.blendARGB(r92, Theme.getColor(r102, r21), this.selectT));
            r63.setTextSize(1, 10.0f);
            r63.setGravity(17);
            r63.setTypeface(AndroidUtilities.bold());
            r63.setMaxLines(3);
            r63.setEllipsize(TextUtils.TruncateAt.END);
            r4.addView(r63, LayoutHelper.createLinear(-1, -2, 17, 4, 0, 4, 0));
            r4.setPadding(0, 0, 0, AndroidUtilities.dp(4.0f));
            View r3 = new ImageView(r19);
            this.lineView = r3;
            r3.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(2.33f), Theme.getColor(r102, r21)));
            addView(r3, LayoutHelper.createFrame(6, -1.0f, 115, -3.0f, 3.0f, 0.0f, 3.0f));
            r3.setTranslationX(-AndroidUtilities.dp(3.0f));
            r3.setVisibility(8);
        }

        private void animateCounterBounce() {
            ValueAnimator r0 = this.counterAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
            this.counterAnimator = null;
        L5:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.counterAnimator = r02;
            r02.addUpdateListener(new TopicsTabsView$VerticalTabView$$ExternalSyntheticLambda1(this));
            this.counterAnimator.addListener(new AnonymousClass3(this));
            this.counterAnimator.setInterpolator(new OvershootInterpolator(2.0f));
            this.counterAnimator.setDuration(200);
            this.counterAnimator.start();
        }

        private /* synthetic */ void lambda$animateCounterBounce$0(ValueAnimator r2) {
            this.countScale = Math.max(1.0f, ((Float) r2.getAnimatedValue()).floatValue());
            this.imageLayoutView.invalidate();
        }

        private /* synthetic */ void lambda$setSelected$1(ValueAnimator r1) {
            this.selectT = ((Float) r1.getAnimatedValue()).floatValue();
            updateState();
            updateImageColor();
        }

        private void setCounter(boolean r9, int r10, boolean r11, boolean r12, boolean r13) {
            if (r12 == false) goto L8;
            this.counterBackgroundColorKey = Theme.key_dialogReactionMentionBackground;
            if (this.reactionString != null) goto L7;
            SpannableStringBuilder r92 = new SpannableStringBuilder("❤️");
            ColoredImageSpan r6 = new ColoredImageSpan(R.drawable.mini_like_filled);
            r6.setScale(0.8f, 0.8f);
            r6.spaceScaleX = 0.5f;
            r6.translate(-AndroidUtilities.dp(3.0f), 0.0f);
            r92.setSpan(r6, 0, r92.length(), 33);
            this.reactionString = r92;
        L7:
            this.counterText.setText(this.reactionString, r13);
        L22:
            if (r13 == true) goto L24;
        L32:
            this.lastUnread = r10;
            this.lastMention = r11;
            this.lastReactions = r12;
            this.imageLayoutView.invalidate();
            return;
        L24:
            if (this.lastUnread >= r10) goto L26;
        L31:
            animateCounterBounce();
            goto L32
        L26:
            if (this.lastMention == true) goto L29;
            if (r11 == true) goto L31;
        L29:
            if (this.lastReactions == true) goto L32;
            if (r12 == false) goto L32;
        L8:
            if (r11 == false) goto L16;
            if (r9 == false) goto L11;
            int r93 = Theme.key_chats_unreadCounterMuted;
        L12:
            this.counterBackgroundColorKey = r93;
            if (this.mentionString != null) goto L15;
            SpannableStringBuilder r94 = new SpannableStringBuilder("@");
            ColoredImageSpan r62 = new ColoredImageSpan(R.drawable.mini_mention_filled_16);
            r62.setScale(0.8f, 0.8f);
            r62.spaceScaleX = 0.5f;
            r62.translate(-AndroidUtilities.dp(3.0f), 0.0f);
            r94.setSpan(r62, 0, 1, 33);
            this.mentionString = r94;
        L15:
            this.counterText.setText(this.mentionString, r13);
            goto L22
        L11:
            r93 = Theme.key_chats_unreadCounter;
            goto L12
        L16:
            if (r10 <= 0) goto L21;
            if (r9 == false) goto L19;
            int r95 = Theme.key_chats_unreadCounterMuted;
        L20:
            this.counterBackgroundColorKey = r95;
            this.counterText.setText(LocaleController.formatNumber(r10, ','), r13);
            goto L22
        L19:
            r95 = Theme.key_chats_unreadCounter;
            goto L20
        L21:
            this.counterBackgroundColorKey = Theme.key_chats_unreadCounterMuted;
            this.counterText.setText(_UrlKt.FRAGMENT_ENCODE_SET, r13);
            goto L22
        }

        private void setLayout(boolean r5) {
            if (this.mono != r5) goto L5;
            return;
        L5:
            this.mono = r5;
            this.imageView.setRoundRadius(ExteraConfig.getAvatarCorners(28.0f));
            FrameLayout r0 = this.imageLayoutView;
            if (r5 == false) goto L8;
            float r2 = 7.0f;
        L9:
            r0.setPadding(0, AndroidUtilities.dp(r2), 0, 0);
            FrameLayout.LayoutParams r02 = this.imageViewParams;
            if (r5 == false) goto L12;
            int r3 = AndroidUtilities.dp(28.0f);
        L13:
            r02.width = r3;
            FrameLayout.LayoutParams r4 = this.imageViewParams;
            if (r5 == false) goto L16;
            int r52 = AndroidUtilities.dp(28.0f);
        L17:
            r4.height = r52;
            return;
        L16:
            r52 = AndroidUtilities.dp(30.0f);
            goto L17
        L12:
            r3 = AndroidUtilities.dp(30.0f);
            goto L13
        L8:
            r2 = 4.0f;
            goto L9
        }

        private void setPinned(boolean r1, boolean r2) {
            if (this.pinned == r1) goto L6;
            this.pinned = r1;
            return;
        }

        private void updateImageColor() {
            int r0 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider);
            int r1 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
            if (this.isAdd == false) goto L5;
            float r2 = 1.0f;
        L6:
            int r02 = ColorUtils.blendARGB(r0, r1, r2);
            boolean r12 = this.staticImage;
            BackupImageView r22 = this.imageView;
            if (r12 == true) goto L9;
            r22.setColorFilter(null);
        L10:
            this.imageView.setEmojiColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN));
            this.imageView.invalidate();
            return;
        L9:
            r22.setColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN));
            goto L10
        L5:
            r2 = this.selectT;
            goto L6
        }

        private void updateState() {
            float r3 = 1.0f;
            this.lineView.setTranslationX((-AndroidUtilities.dp(3.0f)) * (1.0f - this.selectT));
            View r0 = this.lineView;
            if (this.selectT > 0.0f) goto L5;
            int r1 = 8;
        L6:
            r0.setVisibility(r1);
            TextView r02 = this.textView;
            int r12 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider);
            int r2 = Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider);
            if (this.isAdd == true) goto L10;
            r3 = this.selectT;
        L10:
            r02.setTextColor(ColorUtils.blendARGB(r12, r2, r3));
            return;
        L5:
            r1 = 0;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f), TLObject.FLAG_30), r3);
        }

        public void set(long r11, TLRPC.TL_forumTopic r13, boolean r14) {
            setLayout(false);
            long r1 = this.topicId;
            int r3 = r13.id;
            if (r1 != r3) goto L5;
            boolean r9 = true;
        L6:
            this.staticImage = false;
            this.topicId = r3;
            this.isAdd = false;
            this.textView.setText(r13.title);
            this.textView.setVisibility(0);
            if (r13.id != 1) goto L9;
            this.staticImage = true;
            this.imageView.clearImage();
            this.imageView.setAnimatedEmojiDrawable(null);
            this.imageView.setImageResource(R.drawable.msg_filled_general);
            this.imageView.setScaleX(0.66f);
            this.imageView.setScaleY(0.66f);
        L13:
            setSelected(r14);
            updateImageColor();
            boolean r5 = MessagesController.getInstance(this.currentAccount).isDialogMuted(r11, r13.id);
            int r6 = r13.unread_count;
            if (r13.unread_mentions_count <= 0) goto L16;
            boolean r7 = true;
        L18:
            if (r13.unread_reactions_count <= 0) goto L21;
            boolean r8 = true;
        L22:
            setCounter(r5, r6, r7, r8, r9);
            setPinned(r13.pinned, r9);
            updateState();
            return;
        L21:
            r8 = false;
            goto L22
        L16:
            r7 = false;
            goto L18
        L9:
            long r4 = r13.icon_emoji_id;
            BackupImageView r42 = this.imageView;
            if (r4 == 0) goto L12;
            r42.clearImage();
            this.imageView.setAnimatedEmojiDrawable(AnimatedEmojiDrawable.make(UserConfig.selectedAccount, 3, r13.icon_emoji_id));
            this.imageView.setScaleX(1.0f);
            this.imageView.setScaleY(1.0f);
            goto L13
        L12:
            r42.setAnimatedEmojiDrawable(null);
            this.imageView.setImageDrawable(ForumUtilities.createTopicDrawable(r13, false));
            this.imageView.setScaleX(1.0f);
            this.imageView.setScaleY(1.0f);
            goto L13
        L5:
            r9 = false;
            goto L6
        }

        public void setAdd(boolean r9, boolean r10) {
            setLayout(r9);
            this.staticImage = true;
            this.isAdd = true;
            this.textView.setText(LocaleController.getString(R.string.NewTopic));
            this.textView.setVisibility(0);
            this.imageView.clearImage();
            this.imageView.setAnimatedEmojiDrawable(null);
            this.imageView.setImageResource(R.drawable.emoji_tabs_new3);
            this.imageView.setScaleX(1.0f);
            this.imageView.setScaleY(1.0f);
            setSelected(r10);
            updateImageColor();
            updateState();
            setCounter(true, 0, false, false, false);
            setPinned(false, false);
        }

        public void setAll(boolean r8, boolean r9, boolean r10) {
            setLayout(r9);
            this.topicId = -1;
            this.staticImage = true;
            this.isAdd = false;
            TextView r0 = this.textView;
            if (r8 == false) goto L5;
            int r1 = R.string.BotForumNewTopic;
        L6:
            r0.setText(LocaleController.getString(r1));
            TextView r02 = this.textView;
            if (r8 == false) goto L9;
            int r12 = 8;
        L10:
            r02.setVisibility(r12);
            this.imageView.clearImage();
            this.imageView.setAnimatedEmojiDrawable(null);
            if (r8 == false) goto L13;
            BotNewTopicDrawable r82 = new BotNewTopicDrawable(getContext());
            r82.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider));
            this.imageView.setImageDrawable(r82);
        L14:
            this.imageView.setScaleX(1.0f);
            this.imageView.setScaleY(1.0f);
            setSelected(r10);
            updateImageColor();
            updateState();
            setCounter(true, 0, false, false, false);
            setPinned(false, false);
            return;
        L13:
            this.imageView.setImageResource(R.drawable.other_chats);
            goto L14
        L9:
            r12 = 0;
            goto L10
        L5:
            r1 = R.string.AllTopicsSide;
            goto L6
        }

        public void setLoading() {
            setLayout(false);
            this.topicId = -1;
            this.staticImage = true;
            this.isAdd = false;
            SpannableStringBuilder r2 = new SpannableStringBuilder("x");
            LoadingSpan r3 = new LoadingSpan(this.textView, AndroidUtilities.dp(38.0f));
            r3.setScaleY(0.75f);
            r2.setSpan(r3, 0, 1, 33);
            this.textView.setText(r2);
            this.textView.setVisibility(0);
            this.imageView.clearImage();
            this.imageView.setAnimatedEmojiDrawable(null);
            if (this.loadingDrawable != null) goto L5;
            LoadingDrawable r1 = new LoadingDrawable(this.resourcesProvider);
            this.loadingDrawable = r1;
            r1.setRadiiDp(38.0f);
            this.loadingDrawable.setCallback(this.imageView);
            int r12 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, this.resourcesProvider);
            this.loadingDrawable.setColors(Theme.multAlpha(r12, 0.15f), Theme.multAlpha(r12, 0.5f), Theme.multAlpha(r12, 0.6f), Theme.multAlpha(r12, 0.15f));
            this.loadingDrawable.stroke = false;
        L5:
            this.imageView.setImageDrawable(this.loadingDrawable);
            this.imageView.setScaleX(1.0f);
            this.imageView.setScaleY(1.0f);
            setSelected(false);
            updateImageColor();
            setCounter(true, 0, false, false, false);
            setPinned(false, false);
            updateState();
        }

        public void setMf(TLRPC.TL_forumTopic r12, boolean r13) {
            setLayout(true);
            this.isAdd = false;
            this.staticImage = false;
            long r2 = DialogObject.getPeerDialogId(r12.from_id);
            if (r2 != this.topicId) goto L5;
            boolean r10 = true;
        L6:
            this.topicId = r2;
            this.textView.setText(DialogObject.getName(r2));
            this.textView.setVisibility(0);
            int r5 = this.currentAccount;
            if (r2 < 0) goto L9;
            TLRPC.User r22 = MessagesController.getInstance(r5).getUser(Long.valueOf(r2));
            this.avatarDrawable.setInfo(r22);
            this.imageView.setForUserOrChat(r22, this.avatarDrawable);
        L10:
            this.imageView.setScaleX(1.0f);
            this.imageView.setScaleY(1.0f);
            updateState();
            setSelected(r13);
            int r7 = r12.unread_count;
            if (r12.unread_reactions_count <= 0) goto L13;
            boolean r9 = true;
        L14:
            setCounter(false, r7, false, r9, r10);
            setPinned(false, r10);
            return;
        L13:
            r9 = false;
            goto L14
        L9:
            TLRPC.Chat r23 = MessagesController.getInstance(r5).getChat(Long.valueOf(-r2));
            this.avatarDrawable.setInfo(r23);
            this.imageView.setForUserOrChat(r23, this.avatarDrawable);
            goto L10
        L5:
            r10 = false;
            goto L6
        }

        public void setReorder(boolean r1) {
            this.reorder = r1;
            this.layout.invalidate();
        }

        @Override // android.view.View
        public void setSelected(boolean r5) {
            if (this.selected != r5) goto L5;
            return;
        L5:
            this.selected = r5;
            ValueAnimator r0 = this.selectAnimator;
            if (r0 == null) goto L8;
            r0.cancel();
        L8:
            float r02 = this.selectT;
            if (r5 == false) goto L11;
            float r1 = 1.0f;
        L12:
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r1});
            this.selectAnimator = r03;
            r03.addUpdateListener(new TopicsTabsView$VerticalTabView$$ExternalSyntheticLambda0(this));
            this.selectAnimator.addListener(new AnonymousClass4(this, r5));
            this.selectAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.selectAnimator.setDuration(320);
            this.selectAnimator.start();
            return;
        L11:
            r1 = 0.0f;
            goto L12
        }
    }

    public static /* synthetic */ void $r8$lambda$33OPo460X2NEAS7LIJYzDdsREV8(TopicsTabsView r0, TLRPC.Updates r1) {
        r0.lambda$onTabLongClick$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$39by0cw6osE4ZbZ8CDpr4d5b7mU(TopicsTabsView r0, int r1, float r2, float r3, FactorAnimator r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$40k0BMc6sOcN8BwURMO1Gut8Mw0(TopicsTabsView r0) {
        r0.lambda$onTabLongClick$12();
    }

    public static /* synthetic */ void $r8$lambda$55lC7hoDZsYMbA8bSgiPiyA_dhQ(TopicsTabsView r0, HashSet r1, ArrayList r2, long r3) {
        r0.lambda$deleteTopics$18(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$6uK0BFsMqfvoPbnBPe9XOZvq2l4(TopicsTabsView r0, View r1) {
        r0.onCloseButtonClick(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$7YeSs_iksy4c0DXVyB21XI1yQpo(TopicsTabsView r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onTabLongClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$CLonHFCd7AvFuAxVI-MZb6fH_5Q, reason: not valid java name */
    public static /* synthetic */ void m13321$r8$lambda$CLonHFCd7AvFuAxVIMZb6fH_5Q(TopicsTabsView r0, ItemOptions r1, boolean r2, long r3, TLRPC.User r5, TLRPC.Chat r6, View r7) {
        r0.lambda$onTabLongClick$8(r1, r2, r3, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$EiIpsIXxuFKko39atwjyVRHNV1Y(TopicsTabsView r0, int r1, ArrayList r2) {
        r0.whenReordered(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FubL1O75eaucTDlzArWgUf3w9xg(TopicsTabsView r0, boolean r1, ActionBarMenuSubItem r2, ItemOptions r3, long r4, TLRPC.User r6, TLRPC.Chat r7) {
        r0.lambda$onTabLongClick$9(r1, r2, r3, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$Ggp4HE_CA1VaytVb0qwbua4GsyI(TopicsTabsView r0, long r1, boolean r3) {
        r0.lambda$onTabLongClick$4(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$GkS68BJnIJYHnniwNlwVfwkzowU(TopicsTabsView r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onTabClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$IE55ebIdLrJQGXqmvB3NkHAgHi8(TopicsTabsView r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$onTabLongClick$7(r1, r2);
    }

    /* renamed from: $r8$lambda$JjV7f6j-n6IjvbeU8UummDq8gUY, reason: not valid java name */
    public static /* synthetic */ void m13322$r8$lambda$JjV7f6jn6IjvbeU8UummDq8gUY(TopicsTabsView r0, ItemOptions r1, TLRPC.TL_forumTopic r2) {
        r0.lambda$onTabLongClick$15(r1, r2);
    }

    /* renamed from: $r8$lambda$L9YC-3H4jgacWc5iQdaGynECqsI, reason: not valid java name */
    public static /* synthetic */ void m13323$r8$lambda$L9YC3H4jgacWc5iQdaGynECqsI(TopicsTabsView r0, ItemOptions r1, long r2, TLRPC.Chat r4) {
        r0.lambda$onTabLongClick$5(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$Vtc8Zek5803_bPgXqzGIoeBlBY8(TopicsTabsView r0, ItemOptions r1, TLRPC.TL_forumTopic r2) {
        r0.lambda$onTabLongClick$13(r1, r2);
    }

    /* renamed from: $r8$lambda$XFq6ghAF3Zl_XeEmlfC2bO-wEjU, reason: not valid java name */
    public static /* synthetic */ void m13324$r8$lambda$XFq6ghAF3Zl_XeEmlfC2bOwEjU(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$_VH_Iq1d6tJdsCGTb25BD3Z5zBk(TopicsTabsView r0, ActionBarMenuSubItem r1, ItemOptions r2, long r3, TLRPC.User r5, TLRPC.Chat r6, boolean r7, TLRPC.TL_chatAdminRights r8, String r9) {
        r0.lambda$onTabLongClick$10(r1, r2, r3, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$eGnPIWHa_04cRgBeAqXNmEu-t1s, reason: not valid java name */
    public static /* synthetic */ void m13325$r8$lambda$eGnPIWHa_04cRgBeAqXNmEut1s(TopicsTabsView r0, MessagesController r1, TLRPC.TL_forumTopic r2, ItemOptions r3, ItemOptions r4) {
        r0.lambda$onTabLongClick$14(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$hnaAfle8ZRYx7weeDq7URLPQaug(TopicsTabsView r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillVerticalTabs(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jlqkcfZ97AQzF5CMy__r8h0M0y0(TopicsTabsView r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillHorizontalTabs(r1, r2);
    }

    /* renamed from: $r8$lambda$mWcRP8z9y-GlnblDnLczn3CcQJU, reason: not valid java name */
    public static /* synthetic */ void m13326$r8$lambda$mWcRP8z9yGlnblDnLczn3CcQJU(TopicsTabsView r0, View r1) {
        r0.onSideMenuButtonClick(r1);
    }

    public static /* synthetic */ void $r8$lambda$n0xZPV2VHWeafr0Cp2mWkLPbGdQ(TopicsTabsView r0) {
        r0.lambda$updateTabs$3();
    }

    /* renamed from: $r8$lambda$pdWT5MgVBrOyvP7PWCQvFkc-7N8, reason: not valid java name */
    public static /* synthetic */ void m13327$r8$lambda$pdWT5MgVBrOyvP7PWCQvFkc7N8(TopicsTabsView r0, ItemOptions r1, MessagesController r2, TLRPC.TL_forumTopic r3) {
        r0.lambda$onTabLongClick$11(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$qNyPDFboaqFROuZz_D3BmV-H00E, reason: not valid java name */
    public static /* synthetic */ void m13328$r8$lambda$qNyPDFboaqFROuZz_D3BmVH00E(TopicsTabsView r0, ValueAnimator r1) {
        r0.lambda$animateSidemenuTo$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$sGEdYedfVQ61DVvYnD7oYstVaIo(TopicsTabsView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$uNFTku88ykuue1DG8CemDdFeSFs(TopicsTabsView r0, ItemOptions r1, TLRPC.TL_forumTopic r2) {
        r0.lambda$onTabLongClick$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$whz9_2q0Ul6L4zdHpqUqnJxFQVc() {
    }

    public static /* synthetic */ void $r8$lambda$xKbK8DPi7sWP3G5JEMN3Y1Q404M(TopicsTabsView r0, ArrayList r1, long r2, HashSet r4, Runnable r5, AlertDialog r6, int r7) {
        r0.lambda$deleteTopics$20(r1, r2, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$zMSitoQRb4LSg59o166cJlXTzA8(TopicsTabsView r0, ArrayList r1, Runnable r2) {
        r0.lambda$deleteTopics$19(r1, r2);
    }

    /* renamed from: -$$Nest$fgetanimateFromSelectedTopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13329$$Nest$fgetanimateFromSelectedTopicId(TopicsTabsView r2) {
        return r2.animateFromSelectedTopicId;
    }

    /* renamed from: -$$Nest$fgetanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m13330$$Nest$fgetanimator(TopicsTabsView r0) {
        return r0.animator;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13331$$Nest$fgetcurrentAccount(TopicsTabsView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentTopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13332$$Nest$fgetcurrentTopicId(TopicsTabsView r2) {
        return r2.currentTopicId;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13333$$Nest$fgetdialogId(TopicsTabsView r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetlastSelectedTopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m13334$$Nest$fgetlastSelectedTopicId(TopicsTabsView r2) {
        return r2.lastSelectedTopicId;
    }

    /* renamed from: -$$Nest$fgetpendingSidemenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Boolean m13335$$Nest$fgetpendingSidemenu(TopicsTabsView r0) {
        return r0.pendingSidemenu;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m13336$$Nest$fgetresourcesProvider(TopicsTabsView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetsidemenuEnabled, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13337$$Nest$fgetsidemenuEnabled(TopicsTabsView r0) {
        return r0.sidemenuEnabled;
    }

    /* renamed from: -$$Nest$fgettoggleButtonSide, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m13338$$Nest$fgettoggleButtonSide(TopicsTabsView r0) {
        return r0.toggleButtonSide;
    }

    /* renamed from: -$$Nest$fgettopicBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13339$$Nest$fgettopicBottom(TopicsTabsView r0) {
        return r0.topicBottom;
    }

    /* renamed from: -$$Nest$fputanimateFromSelectedTopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13340$$Nest$fputanimateFromSelectedTopicId(TopicsTabsView r0, long r1) {
        r0.animateFromSelectedTopicId = r1;
    }

    /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13341$$Nest$fputanimator(TopicsTabsView r0, ValueAnimator r1) {
        r0.animator = r1;
    }

    /* renamed from: -$$Nest$fputlastSelectedTopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13342$$Nest$fputlastSelectedTopicId(TopicsTabsView r0, long r1) {
        r0.lastSelectedTopicId = r1;
    }

    /* renamed from: -$$Nest$fputpendingSidemenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13343$$Nest$fputpendingSidemenu(TopicsTabsView r0, Boolean r1) {
        r0.pendingSidemenu = r1;
    }

    /* renamed from: -$$Nest$fputsidemenuAnimating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13344$$Nest$fputsidemenuAnimating(TopicsTabsView r0, boolean r1) {
        r0.sidemenuAnimating = r1;
    }

    /* renamed from: -$$Nest$fputsidemenuT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13345$$Nest$fputsidemenuT(TopicsTabsView r0, float r1) {
        r0.sidemenuT = r1;
    }

    /* renamed from: -$$Nest$manimateSidemenuTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13346$$Nest$manimateSidemenuTo(TopicsTabsView r0, boolean r1) {
        r0.animateSidemenuTo(r1);
    }

    /* renamed from: -$$Nest$misLoadingVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13347$$Nest$misLoadingVisible(TopicsTabsView r0) {
        return r0.isLoadingVisible();
    }

    /* renamed from: -$$Nest$mloadMore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13348$$Nest$mloadMore(TopicsTabsView r0) {
        r0.loadMore();
    }

    public TopicsTabsView(Context r33, BaseFragment r34, int r35, long r36, Theme.ResourcesProvider r38) {
        super(r33);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorTopicsVisibility = new BoolAnimator(0, this, r3, 380, true);
        this.animatorCloseButtonVisibility = new BoolAnimator(0, new TopicsTabsView$$ExternalSyntheticLambda0(this), r3, 320);
        this.sidemenuT = 0.0f;
        this.excludeTopics = new HashSet();
        this.fragment = r34;
        this.currentAccount = r35;
        this.dialogId = r36;
        this.resourcesProvider = r38;
        long r12 = -r36;
        this.mono = ChatObject.isMonoForum(MessagesController.getInstance(r35).getChat(Long.valueOf(r12)));
        boolean r14 = UserObject.isBotForumWithEditableTopics(MessagesController.getInstance(r35).getUser(Long.valueOf(r36)));
        this.bot = r14;
        SharedPreferences r1 = UserConfig.getInstance(r35).getPreferences();
        this.canShowProgress = !r1.getBoolean("topics_end_reached_" + r12, false);
        setClipChildren(true);
        setClipToPadding(true);
        setWillNotDraw(false);
        FrameLayout r13 = new FrameLayout(r33);
        this.topTabsContainer = r13;
        addView(r13, LayoutHelper.createFrame(-1, 36.0f, 55, 7.0f, 7.0f, 7.0f, 7.0f));
        FrameLayout r32 = new FrameLayout(r33);
        this.sideTabsContainer = r32;
        addView(r32, LayoutHelper.createFrame(64, -1.0f, 115, 7.0f, 7.0f, 7.0f, 7.0f));
        AnonymousClass1 r4 = new AnonymousClass1(this, r33, r35, 0, new TopicsTabsView$$ExternalSyntheticLambda3(this), new TopicsTabsView$$ExternalSyntheticLambda4(this), new TopicsTabsView$$ExternalSyntheticLambda5(this), r38);
        this.topTabs = r4;
        r4.listenReorder(new TopicsTabsView$$ExternalSyntheticLambda6(this));
        r4.setWillNotDraw(false);
        r4.adapter.setApplyBackground(false);
        r4.makeHorizontal();
        r13.addView(r4, LayoutHelper.createFrame(-1, -1.0f, 119, 41.0f, 0.0f, 0.0f, 0.0f));
        r4.addOnScrollListener(new AnonymousClass2(this));
        if (r14 == false) goto L10;
        this.botCreateTopicButtonHorizontal = null;
        VerticalTabView r0 = new VerticalTabView(r33, r35, r38);
        this.botCreateTopicButtonVertical = r0;
        if (this.currentTopicId != 0) goto L8;
        boolean r42 = true;
    L9:
        r0.setAll(true, false, r42);
        r0.setOnClickListener(new TopicsTabsView$$ExternalSyntheticLambda7(this));
        ViewGroup r5 = r32;
        r5.addView(r0, LayoutHelper.createFrame(64, 42.0f, 51, 0.0f, 48.0f, 0.0f, 0.0f));
    L11:
        ViewGroup r23 = r5;
        AnonymousClass3 r02 = new AnonymousClass3(this, r33, r35, 0, new TopicsTabsView$$ExternalSyntheticLambda8(this), new TopicsTabsView$$ExternalSyntheticLambda4(this), new TopicsTabsView$$ExternalSyntheticLambda5(this), r38);
        this.sideTabs = r02;
        r02.listenReorder(new TopicsTabsView$$ExternalSyntheticLambda6(this));
        r02.adapter.setApplyBackground(false);
        r02.setClipToPadding(false);
        r02.setClipChildren(false);
        if (r14 == false) goto L15;
        float r37 = 90.0f;
    L16:
        r23.addView(r02, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, r37, 0.0f, 0.0f));
        r02.addOnScrollListener(new AnonymousClass4(this));
        ImageView r03 = createButton(r33, R.drawable.menu_sidebar_left, new TopicsTabsView$$ExternalSyntheticLambda1(this));
        this.toggleButtonTop = r03;
        ImageView r39 = createButton(r33, R.drawable.menu_sidebar_left, new TopicsTabsView$$ExternalSyntheticLambda1(this));
        this.toggleButtonSide = r39;
        r13.addView(r03, LayoutHelper.createFrame(44, 36, 51));
        r23.addView(r39, LayoutHelper.createFrame(64, 48, 51));
        ImageView r142 = createButton(r33, R.drawable.msg_select, new TopicsTabsView$$ExternalSyntheticLambda2(this));
        this.closeButtonTop = r142;
        ImageView r04 = createButton(r33, R.drawable.msg_select, new TopicsTabsView$$ExternalSyntheticLambda2(this));
        this.closeButtonSide = r04;
        r13.addView(r142, LayoutHelper.createFrame(44, 36, 51));
        r23.addView(r04, LayoutHelper.createFrame(64, 48, 51));
        MessagesController.getInstance(r35).getTopicsController().loadTopics(r12, false, 3);
        SharedPreferences r05 = MessagesController.getInstance(r35).getMainSettings();
        if (r05.getBoolean("topicssidetabs" + r36, false) == false) goto L19;
        this.sidemenuT = 1.0f;
        this.sidemenuEnabled = true;
    L19:
        boolean r06 = r05.getBoolean("topicssidetabsb" + r36, false);
        this.topicBottom = r06;
        if (r06 == false) goto L22;
        int r07 = R.drawable.menu_sidebar_top;
    L23:
        r39.setImageResource(r07);
        checkTopicsVisibility(false);
        checkUi_closeButtonVisibility();
        updateSidemenuPosition();
        updateTabs();
        return;
    L22:
        r07 = R.drawable.menu_sidebar_bottom;
        goto L23
    L15:
        r37 = 48.0f;
        goto L16
    L8:
        r42 = false;
        goto L9
    L10:
        r5 = r32;
        this.botCreateTopicButtonHorizontal = null;
        this.botCreateTopicButtonVertical = null;
        goto L11
    }

    private void animateSidemenuTo(boolean r6) {
        if (this.sidemenuEnabled != r6) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.animator;
        if (r0 == null) goto L12;
        r0.cancel();
        if (this.sidemenuAnimating == false) goto L12;
        this.pendingSidemenu = Boolean.valueOf(r6);
        return;
    L12:
        if (r6 == true) goto L14;
        this.topicBottom = !this.topicBottom;
    L14:
        this.sidemenuEnabled = r6;
        this.sidemenuAnimating = true;
        float r1 = this.sidemenuT;
        if (r6 == false) goto L17;
        float r2 = 1.0f;
    L18:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r1, r2});
        this.animator = r02;
        r02.addUpdateListener(new TopicsTabsView$$ExternalSyntheticLambda18(this));
        this.animator.addListener(new AnonymousClass5(this, r6));
        this.animator.setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR);
        this.animator.setDuration(250);
        this.animator.start();
        return;
    L17:
        r2 = 0.0f;
        goto L18
    }

    private void checkSideTabsPadding(boolean r4) {
        int r0 = this.sideTabsContainer.getPaddingBottom();
        int r1 = Math.round(this.sideMenuBackgroundMarginBottom + this.sideMenuBackgroundMarginTop);
        if (r0 != r1) goto L6;
        return;
    L6:
        if (r4 == false) goto L9;
        this.sideTabsContainer.setPadding(0, 0, 0, r1);
        return;
    L9:
        if (r1 >= r0) goto L12;
        this.sideTabsContainer.setPadding(0, 0, 0, 0);
        return;
    }

    private void checkTopicsVisibility(boolean r4) {
        ArrayList<TLRPC.TL_forumTopic> r0 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopics(-this.dialogId);
        BoolAnimator r1 = this.animatorTopicsVisibility;
        if (r0 != null) goto L5;
    L9:
        boolean r3 = false;
    L10:
        r1.setValue(r3, r4);
        return;
    L5:
        if (r0.isEmpty() == true) goto L9;
        if (this.allTopicsHidden == true) goto L9;
        r3 = true;
        goto L10
    }

    private void checkUi_closeButtonVisibility() {
        float r0 = this.animatorCloseButtonVisibility.getFloatValue();
        this.closeButtonTop.setAlpha(r0);
        this.closeButtonTop.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r0));
        this.closeButtonTop.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r0));
        ImageView r1 = this.closeButtonTop;
        int r6 = 8;
        if (r0 <= 0.0f) goto L5;
        int r8 = 0;
    L6:
        r1.setVisibility(r8);
        this.closeButtonSide.setAlpha(r0);
        this.closeButtonSide.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r0));
        this.closeButtonSide.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r0));
        ImageView r02 = this.closeButtonSide;
        if (r0 <= 0.0f) goto L9;
        int r12 = 0;
    L10:
        r02.setVisibility(r12);
        float r03 = 1.0f - this.animatorCloseButtonVisibility.getFloatValue();
        this.toggleButtonTop.setAlpha(r03);
        this.toggleButtonTop.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r03));
        this.toggleButtonTop.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r03));
        ImageView r13 = this.toggleButtonTop;
        if (r03 <= 0.0f) goto L13;
        int r5 = 0;
    L14:
        r13.setVisibility(r5);
        this.toggleButtonSide.setAlpha(r03);
        this.toggleButtonSide.setScaleX(AndroidUtilities.lerp(0.4f, 1.0f, r03));
        this.toggleButtonSide.setScaleY(AndroidUtilities.lerp(0.4f, 1.0f, r03));
        ImageView r9 = this.toggleButtonSide;
        if (r03 <= 0.0f) goto L17;
        r6 = 0;
    L17:
        r9.setVisibility(r6);
        return;
    L13:
        r5 = 8;
        goto L14
    L9:
        r12 = 8;
        goto L10
    L5:
        r8 = 8;
        goto L6
    }

    private void checkUi_topicsVerticalPosition() {
        this.topTabsContainer.setAlpha(AndroidUtilities.lerp(1.0f, 0.0f, this.sidemenuT));
        FrameLayout r0 = this.topTabsContainer;
        if (((1.0f - this.sidemenuT) * this.animatorTopicsVisibility.getFloatValue()) <= 0.0f) goto L5;
        int r1 = 0;
    L6:
        r0.setVisibility(r1);
        boolean r02 = this.topicBottom;
        FrameLayout r12 = this.topTabsContainer;
        if (r02 == false) goto L10;
        r12.setTranslationY(((getMeasuredHeight() - AndroidUtilities.dp(50.0f)) - this.sideMenuBackgroundMarginBottom) + AndroidUtilities.lerp(AndroidUtilities.dp(43.0f), 0, getTabsVisibility(Position.BOTTOM)));
        return;
    L10:
        r12.setTranslationY(this.sideMenuBackgroundMarginTop + AndroidUtilities.lerp(-AndroidUtilities.dp(43.0f), 0, getTabsVisibility(Position.TOP)));
        return;
    L5:
        r1 = 8;
        goto L6
    }

    private ImageView createButton(Context r1, int r2, View.OnClickListener r3) {
        ImageView r0 = new ImageView(r1);
        r0.setImageResource(r2);
        r0.setScaleType(ImageView.ScaleType.CENTER);
        r0.setOnClickListener(r3);
        ScaleStateListAnimator.apply(r0);
        return r0;
    }

    private void deleteTopics(HashSet<Integer> r10, Runnable r11) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext());
        r0.setTitle(LocaleController.getPluralString("DeleteTopics", r10.size()));
        ArrayList r4 = new ArrayList(r10);
        long r5 = this.currentTopicId;
        if (r10.size() != 1) goto L5;
        r0.setMessage(LocaleController.formatString(R.string.DeleteSelectedTopic, new Object[]{MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(-this.dialogId, ((Integer) r4.get(0)).intValue()).title}));
    L6:
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new TopicsTabsView$$ExternalSyntheticLambda22(this, r4, r5, r10, r11));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new TopicsTabsView$$ExternalSyntheticLambda23());
        AlertDialog r9 = r0.create();
        r9.show();
        TextView r92 = (TextView) r9.getButton(-1);
        if (r92 == null) goto L10;
        r92.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L10:
        return;
    L5:
        r0.setMessage(LocaleController.getString(R.string.DeleteSelectedTopics));
        goto L6
    }

    private void fillHorizontalTabs(ArrayList<UItem> r18, UniversalAdapter r19) {
        TLRPC.Chat r2 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        TLRPC.User r3 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        TopicsController r4 = MessagesController.getInstance(this.currentAccount).getTopicsController();
        ArrayList<TLRPC.TL_forumTopic> r5 = r4.getTopics(-this.dialogId);
        UItem r6 = HorizontalTabView.Factory.asAll(this.bot, this.mono);
        boolean r8 = false;
        if (this.currentTopicId != 0) goto L5;
        boolean r7 = true;
    L6:
        r18.add(r6.setChecked(r7));
        if (r5 == null) goto L34;
        int r62 = r5.size();
        boolean r72 = false;
        int r10 = 0;
    L9:
        if (r10 >= r62) goto L33;
        TLRPC.TL_forumTopic r11 = r5.get(r10);
        r10 = r10 + 1;
        TLRPC.TL_forumTopic r112 = r11;
        if (this.bot == false) goto L16;
        if (r112.id == 1) goto L9;
    L16:
        if (this.excludeTopics.contains(Integer.valueOf(r112.id)) == true) goto L9;
        boolean r12 = r112.pinned;
        if (r12 == true) goto L25;
        if (r72 == false) goto L25;
        if (r18.isEmpty() == true) goto L24;
        r18.get(r18.size() - 1).flags |= 8;
    L24:
        r19.reorderSectionEnd();
        r72 = false;
    L28:
        UItem r122 = HorizontalTabView.Factory.asTab(this.dialogId, r112, this.mono);
        if (this.currentTopicId != getTopicId(r112)) goto L31;
        boolean r113 = true;
    L32:
        r18.add(r122.setChecked(r113));
        goto L9
    L31:
        r113 = false;
    L25:
        if (r12 == false) goto L28;
        if (r72 == true) goto L28;
        r19.reorderSectionStart();
        r72 = true;
        goto L28
    L33:
        r8 = r72;
    L34:
        if (r8 == false) goto L36;
        r19.reorderSectionEnd();
    L36:
        if (r5 == null) goto L45;
        if (r5.isEmpty() == true) goto L45;
        if (r4.endIsReached(-this.dialogId) == true) goto L45;
        if (this.canShowProgress == false) goto L45;
        r18.add(HorizontalTabView.Factory.asLoading(-2));
        r18.add(HorizontalTabView.Factory.asLoading(-3));
        r18.add(HorizontalTabView.Factory.asLoading(-4));
    L45:
        if (this.bot == false) goto L47;
        return;
    L47:
        if (this.mono == true) goto L64;
        if (r2 == null) goto L52;
        if (ChatObject.canCreateTopic(r2) == false) goto L52;
    L53:
        r18.add(HorizontalTabView.Factory.asAdd());
        return;
    L52:
        if (UserObject.isBotForumWithEditableTopics(r3) == true) goto L53;
        return;
    L64:
        return;
    L5:
        r7 = false;
        goto L6
    }

    private void fillVerticalTabs(ArrayList<UItem> r18, UniversalAdapter r19) {
        TLRPC.Chat r2 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        TLRPC.User r3 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        TopicsController r4 = MessagesController.getInstance(this.currentAccount).getTopicsController();
        ArrayList<TLRPC.TL_forumTopic> r5 = r4.getTopics(-this.dialogId);
        boolean r6 = this.bot;
        if (r6 == true) goto L9;
        UItem r62 = VerticalTabView.Factory.asAll(r6, this.mono);
        if (this.currentTopicId != 0) goto L7;
        boolean r9 = true;
    L8:
        r18.add(r62.setChecked(r9));
        goto L9
    L7:
        r9 = false;
    L9:
        if (r5 == null) goto L32;
        int r63 = r5.size();
        boolean r92 = false;
        int r10 = 0;
    L11:
        if (r10 >= r63) goto L33;
        TLRPC.TL_forumTopic r11 = r5.get(r10);
        r10 = r10 + 1;
        TLRPC.TL_forumTopic r112 = r11;
        if (this.bot == false) goto L18;
        if (r112.id == 1) goto L11;
    L18:
        if (this.excludeTopics.contains(Integer.valueOf(r112.id)) == true) goto L11;
        boolean r12 = r112.pinned;
        if (r12 == true) goto L24;
        if (r92 == false) goto L24;
        r19.reorderSectionEnd();
        r92 = false;
    L27:
        UItem r122 = VerticalTabView.Factory.asTab(this.dialogId, r112, this.mono);
        if (this.currentTopicId != getTopicId(r112)) goto L30;
        boolean r113 = true;
    L31:
        r18.add(r122.setChecked(r113));
        goto L11
    L30:
        r113 = false;
    L24:
        if (r12 == false) goto L27;
        if (r92 == true) goto L27;
        r19.reorderSectionStart();
        r92 = true;
    L33:
        if (r92 == false) goto L35;
        r19.reorderSectionEnd();
    L35:
        if (r5 == null) goto L44;
        if (r5.isEmpty() == true) goto L44;
        if (r4.endIsReached(-this.dialogId) == true) goto L44;
        if (this.canShowProgress == false) goto L44;
        r18.add(VerticalTabView.Factory.asLoading(-2));
        r18.add(VerticalTabView.Factory.asLoading(-3));
        r18.add(VerticalTabView.Factory.asLoading(-4));
    L44:
        if (this.bot == false) goto L46;
        return;
    L46:
        if (this.mono == true) goto L63;
        if (r2 == null) goto L51;
        if (ChatObject.canCreateTopic(r2) == false) goto L51;
    L52:
        r18.add(VerticalTabView.Factory.asAdd(false));
        return;
    L51:
        if (UserObject.isBotForumWithEditableTopics(r3) == true) goto L52;
        return;
    L63:
        return;
    L32:
        r92 = false;
        goto L33
    }

    private static int getTabsSize(Position r1) {
        if (r1 != Position.LEFT) goto L5;
        float r12 = 64.0f;
    L7:
        return AndroidUtilities.dp(r12);
    L5:
        r12 = 36.0f;
        goto L7
    }

    private float getTabsVisibility(Position r3) {
        float r0 = this.animatorTopicsVisibility.getFloatValue();
        if (r3 != Position.LEFT) goto L7;
        return this.sidemenuT * r0;
    L7:
        if (r3 != Position.TOP) goto L11;
        if (this.topicBottom == true) goto L11;
    L15:
        return (1.0f - this.sidemenuT) * r0;
    L11:
        if (r3 == Position.BOTTOM) goto L13;
        return 0.0f;
    L13:
        if (this.topicBottom == true) goto L15;
        return 0.0f;
    }

    private long getTopicId(TLRPC.TL_forumTopic r1) {
        if (this.mono == false) goto L7;
        return DialogObject.getPeerDialogId(r1.from_id);
    L7:
        return r1.id;
    }

    private boolean isLoadingVisible() {
        if (this.sidemenuT <= 0.5f) goto L13;
        int r0 = 0;
    L6:
        if (r0 >= this.sideTabs.getChildCount()) goto L22;
        UItem r3 = this.sideTabs.adapter.getItem(this.sideTabs.getChildAdapterPosition(this.sideTabs.getChildAt(r0)));
        if (r3 == null) goto L12;
        if (r3.red == false) goto L12;
        return true;
    L12:
        r0 = r0 + 1;
    L22:
        return false;
    L13:
        int r02 = 0;
    L15:
        if (r02 >= this.topTabs.getChildCount()) goto L22;
        UItem r32 = this.topTabs.adapter.getItem(this.topTabs.getChildAdapterPosition(this.topTabs.getChildAt(r02)));
        if (r32 == null) goto L21;
        if (r32.red == false) goto L21;
        return true;
    L21:
        r02 = r02 + 1;
        goto L15
    }

    private /* synthetic */ void lambda$animateSidemenuTo$2(ValueAnimator r1) {
        this.sidemenuT = ((Float) r1.getAnimatedValue()).floatValue();
        updateSidemenuPosition();
    }

    private /* synthetic */ void lambda$deleteTopics$18(HashSet r6, ArrayList r7, long r8) {
        this.excludeTopics.removeAll(r6);
        updateTabs();
        int r62 = r7.size();
        int r1 = 0;
    L3:
        if (r1 >= r62) goto L11;
        Object r2 = r7.get(r1);
        r1 = r1 + 1;
        long r22 = ((Integer) r2).intValue();
        if (r8 != r22) goto L3;
        selectTopic(r22, false);
        return;
    }

    private /* synthetic */ void lambda$deleteTopics$19(ArrayList r4, Runnable r5) {
        MessagesController.getInstance(this.currentAccount).getTopicsController().deleteTopics(-this.dialogId, r4);
        r5.run();
    }

    private /* synthetic */ void lambda$deleteTopics$20(ArrayList r8, long r9, HashSet r11, Runnable r12, AlertDialog r13, int r14) {
        int r142 = r8.size();
        int r1 = 0;
    L3:
        if (r1 >= r142) goto L7;
        Object r2 = r8.get(r1);
        r1 = r1 + 1;
        if (r9 != ((Integer) r2).intValue()) goto L3;
        selectTopic(0, false);
        goto L3
    L7:
        this.excludeTopics.addAll(r11);
        updateTabs();
        BulletinFactory.of(this.fragment).createUndoBulletin(LocaleController.getPluralString("TopicsDeleted", r11.size()), new TopicsTabsView$$ExternalSyntheticLambda26(this, r11, r8, r9), new TopicsTabsView$$ExternalSyntheticLambda27(this, r8, r12)).show();
        r13.dismiss();
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        this.onTopicSelected.run(0, Boolean.FALSE);
    }

    private /* synthetic */ void lambda$new$1(int r1, float r2, float r3, FactorAnimator r4) {
        checkUi_closeButtonVisibility();
    }

    private /* synthetic */ void lambda$onTabLongClick$10(ActionBarMenuSubItem r4, ItemOptions r5, long r6, TLRPC.User r8, TLRPC.Chat r9, boolean r10, TLRPC.TL_chatAdminRights r11, String r12) {
        AndroidUtilities.runOnUIThread(new TopicsTabsView$$ExternalSyntheticLambda20(this, r10, r4, r5, r6, r8, r9));
    }

    private /* synthetic */ void lambda$onTabLongClick$11(ItemOptions r7, MessagesController r8, TLRPC.TL_forumTopic r9) {
        r7.dismiss();
        r8.getTopicsController().pinTopic(-this.dialogId, r9.id, !r9.pinned, this.fragment);
    }

    private /* synthetic */ void lambda$onTabLongClick$12() {
        this.sideTabs.allowReorder(true);
        this.topTabs.allowReorder(true);
        this.animatorCloseButtonVisibility.setValue(true, true);
        AndroidUtilities.updateVisibleRows(this.topTabs);
        AndroidUtilities.updateVisibleRows(this.sideTabs);
    }

    private /* synthetic */ void lambda$onTabLongClick$13(ItemOptions r5, TLRPC.TL_forumTopic r6) {
        r5.dismiss();
        this.fragment.presentFragment(TopicCreateFragment.create(-this.dialogId, r6.id));
    }

    private /* synthetic */ void lambda$onTabLongClick$14(MessagesController r7, TLRPC.TL_forumTopic r8, ItemOptions r9, ItemOptions r10) {
        if (r7.isDialogMuted(this.dialogId, r8.id) == false) goto L8;
        r9.dismiss();
        NotificationsController.getInstance(this.currentAccount).muteDialog(this.dialogId, r8.id, false);
        if (BulletinFactory.canShowBulletin(this.fragment) == false) goto L10;
        BulletinFactory.createMuteBulletin(this.fragment, 4, 0, this.resourcesProvider).show();
        return;
    L10:
        return;
    L8:
        r9.openSwipeback(r10);
    }

    private /* synthetic */ void lambda$onTabLongClick$15(ItemOptions r3, TLRPC.TL_forumTopic r4) {
        r3.dismiss();
        MessagesController.getInstance(this.currentAccount).getTopicsController().toggleCloseTopic(-this.dialogId, r4.id, !r4.closed);
    }

    private /* synthetic */ void lambda$onTabLongClick$17(ItemOptions r1, TLRPC.TL_forumTopic r2) {
        r1.dismiss();
        HashSet<Integer> r12 = new HashSet();
        r12.add(Integer.valueOf(r2.id));
        deleteTopics(r12, new TopicsTabsView$$ExternalSyntheticLambda21());
    }

    private /* synthetic */ void lambda$onTabLongClick$4(long r2, boolean r4) {
        BaseFragment r1 = this.fragment;
        if ((r1 instanceof ChatActivity) == false) goto L6;
        ((ChatActivity) r1).performHistoryClear(r2, false, true);
        return;
    }

    private /* synthetic */ void lambda$onTabLongClick$5(ItemOptions r9, long r10, TLRPC.Chat r12) {
        r9.dismiss();
        TLRPC.User r3 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r10));
        if (r3 == null) goto L6;
        AlertsCreator.createClearDaysDialogAlert(this.fragment, -1, r3, r12, true, new TopicsTabsView$$ExternalSyntheticLambda19(this, r10), this.fragment.getResourceProvider());
        return;
    }

    private /* synthetic */ void lambda$onTabLongClick$6(TLRPC.Updates r4) {
        TLRPC.Chat r42 = r4.chats.get(0);
        MessagesController.getInstance(this.currentAccount).loadFullChat(r42.id, 0, true);
    }

    private /* synthetic */ void lambda$onTabLongClick$7(TLObject r2, TLRPC.TL_error r3) {
        if (r2 == null) goto L7;
        TLRPC.Updates r22 = (TLRPC.Updates) r2;
        MessagesController.getInstance(this.currentAccount).processUpdates(r22, false);
        if (r22.chats.isEmpty() == true) goto L8;
        AndroidUtilities.runOnUIThread(new TopicsTabsView$$ExternalSyntheticLambda28(this, r22), 1000);
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$onTabLongClick$8(ItemOptions r1, boolean r2, long r3, TLRPC.User r5, TLRPC.Chat r6, View r7) {
        r1.dismiss();
        if (r2 == true) goto L6;
        MessagesController.getInstance(this.currentAccount).deleteParticipantFromChat(r3, r5, null, false, false);
        return;
    L6:
        TLRPC.TL_channels_editBanned r12 = new TLRPC.TL_channels_editBanned();
        r12.participant = MessagesController.getInputPeer(r5);
        r12.channel = MessagesController.getInputChannel(r6);
        r12.banned_rights = new TLRPC.TL_chatBannedRights();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new TopicsTabsView$$ExternalSyntheticLambda25(this));
    }

    private /* synthetic */ void lambda$onTabLongClick$9(boolean r9, ActionBarMenuSubItem r10, ItemOptions r11, long r12, TLRPC.User r14, TLRPC.Chat r15) {
        boolean r3 = !r9;
        r10.setVisibility(0);
        if (r9 == true) goto L5;
        int r92 = R.string.UnbanUserMonoforum;
    L6:
        r10.setText(LocaleController.getString(r92));
        r10.setOnClickListener(new TopicsTabsView$$ExternalSyntheticLambda24(this, r11, r3, r12, r14, r15));
        return;
    L5:
        r92 = R.string.BanUserMonoforum;
        goto L6
    }

    private /* synthetic */ void lambda$updateTabs$3() {
        if (isLoadingVisible() == false) goto L6;
        loadMore();
        return;
    }

    private void loadMore() {
        TopicsController r0 = MessagesController.getInstance(this.currentAccount).getTopicsController();
        if (r0.endIsReached(-this.dialogId) == true) goto L6;
        r0.loadTopics(-this.dialogId);
        return;
    }

    private void onCloseButtonClick(View r3) {
        this.sideTabs.allowReorder(false);
        this.topTabs.allowReorder(false);
        this.animatorCloseButtonVisibility.setValue(false, true);
        AndroidUtilities.updateVisibleRows(this.sideTabs);
        AndroidUtilities.updateVisibleRows(this.topTabs);
    }

    private void onSideMenuButtonClick(View r3) {
        Boolean r32 = this.pendingSidemenu;
        boolean r0 = false;
        if (r32 == null) goto L8;
        if (r32.booleanValue() == true) goto L10;
    L6:
        r0 = true;
    L10:
        animateSidemenuTo(r0);
        return;
    L8:
        if (this.sidemenuEnabled == true) goto L10;
        goto L10
    }

    private void onTabClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (this.mono == false) goto L9;
        Utilities.Callback2<Long, Boolean> r0 = this.onDialogSelected;
        if (r0 == null) goto L18;
        r0.run(Long.valueOf(r1.longValue), Boolean.FALSE);
        return;
    L18:
        return;
    L9:
        if (r1.longValue != (-2)) goto L14;
        Runnable r02 = this.onTopicCreated;
        if (r02 == null) goto L19;
        r02.run();
        return;
    L19:
        return;
    L14:
        Utilities.Callback2<Integer, Boolean> r03 = this.onTopicSelected;
        if (r03 == null) goto L20;
        r03.run(Integer.valueOf(r1.id), Boolean.FALSE);
        return;
    }

    private boolean onTabLongClick(UItem r20, View r21, int r22, float r23, float r24) {
        if (this.sideTabs.isReorderAllowed() == false) goto L5;
    L86:
        return false;
    L5:
        if (this.topTabs.isReorderAllowed() == true) goto L86;
        Object r0 = r20.object;
        if ((r0 instanceof TLRPC.TL_forumTopic) == false) goto L86;
        TLRPC.TL_forumTopic r3 = (TLRPC.TL_forumTopic) r0;
        MessagesController r2 = MessagesController.getInstance(this.currentAccount);
        long r4 = this.dialogId;
        if (r4 >= 0) goto L12;
        TLRPC.Chat r5 = r2.getChat(Long.valueOf(-r4));
    L13:
        long r11 = this.dialogId;
        if (r11 <= 0) goto L16;
        TLRPC.User r112 = r2.getUser(Long.valueOf(r11));
    L17:
        ItemOptions r14 = ItemOptions.makeOptions(this.fragment, r21, true);
        if (ChatObject.isMonoForum(r5) == false) goto L37;
        long r32 = DialogObject.getPeerDialogId(r3.from_id);
        if (r32 != 0) goto L22;
    L36:
        return false;
    L22:
        if (ChatObject.canManageMonoForum(this.currentAccount, r5) == false) goto L36;
        TLRPC.Chat r02 = r5;
        r14.add(R.drawable.msg_clear, LocaleController.getString(R.string.ClearHistory), new TopicsTabsView$$ExternalSyntheticLambda10(this, r14, r32, r5));
        long r6 = r02.id;
        if (ChatObject.isMonoForum(r02) == true) goto L27;
    L31:
        TLRPC.Chat r03 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r6));
        TLRPC.User r25 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r32));
        if (r25 == null) goto L81;
        if (ChatObject.canBlockUsers(r03) == false) goto L81;
        r14.add(R.drawable.msg_remove, LocaleController.getString(R.string.BanUserMonoforum), null);
        ActionBarMenuSubItem r26 = r14.getLast();
        r26.setVisibility(8);
        MessagesController.getInstance(this.currentAccount).checkIsInChat(true, r03, r25, new TopicsTabsView$$ExternalSyntheticLambda11(this, r26, r14, r6, r25, r03));
    L81:
        if ((r21 instanceof HorizontalTabView) == false) goto L83;
        r14.setScrimViewBackground(new AnonymousClass6(this));
        r14.translate(AndroidUtilities.dp(16.0f), 0.0f);
    L84:
        r14.show();
        return true;
    L83:
        r14.setScrimViewBackground(Theme.createRoundRectDrawable(0, AndroidUtilities.dp(5.0f), AndroidUtilities.dp(5.0f), 0, Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider)));
        goto L84
    L27:
        if (ChatObject.canManageMonoForum(this.currentAccount, r02) == false) goto L31;
        long r12 = r02.linked_monoforum_id;
        if (r12 == 0) goto L31;
        r6 = r12;
        goto L31
    L37:
        TLRPC.Chat r04 = r5;
        if (ChatObject.canManageTopics(r04) == false) goto L40;
    L41:
        boolean r42 = r3.pinned;
        if (r42 == false) goto L44;
        int r52 = R.drawable.msg_unpin;
    L45:
        if (r42 == false) goto L47;
        int r43 = R.string.DialogUnpin;
    L48:
        r14.add(r52, LocaleController.getString(r43), new TopicsTabsView$$ExternalSyntheticLambda12(this, r14, r2, r3));
        if (r3.pinned == false) goto L52;
        r14.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.FilterReorder), new TopicsTabsView$$ExternalSyntheticLambda13(this));
    L52:
        if (ChatObject.canManageTopics(r04) == false) goto L54;
    L55:
        r14.add(R.drawable.outline_profile_edit_24, LocaleController.getString(R.string.EditTopic), new TopicsTabsView$$ExternalSyntheticLambda14(this, r14, r3));
    L56:
        ItemOptions r53 = ChatNotificationsPopupWrapper.addAsItemOptions(this.fragment, r14, this.dialogId, r3.id);
        boolean r44 = r2.isDialogMuted(this.dialogId, r3.id);
        if (r44 == false) goto L59;
        int r62 = R.drawable.msg_unmute;
    L60:
        if (r44 == false) goto L63;
        int r45 = R.string.Unmute;
    L64:
        r14.add(r62, LocaleController.getString(r45), new TopicsTabsView$$ExternalSyntheticLambda15(this, r2, r3, r14, r53));
        if (ChatObject.canManageTopic(this.currentAccount, r04, r3) == false) goto L78;
        if (UserObject.isBotForum(r112) == true) goto L78;
        boolean r05 = r3.closed;
        if (r05 == false) goto L71;
        int r27 = R.drawable.msg_topic_restart;
    L72:
        if (r05 == false) goto L75;
        int r06 = R.string.RestartTopic;
    L76:
        r14.add(r27, LocaleController.getString(r06), new TopicsTabsView$$ExternalSyntheticLambda16(this, r14, r3));
        goto L78
    L75:
        r06 = R.string.CloseTopic;
        goto L76
    L71:
        r27 = R.drawable.msg_topic_close;
    L78:
        if (ChatObject.canDeleteTopic(this.currentAccount, r04, r3) == false) goto L81;
        r14.add(R.drawable.msg_delete, LocaleController.getPluralString("DeleteTopics", 1), new TopicsTabsView$$ExternalSyntheticLambda17(this, r14, r3));
        goto L81
    L63:
        r45 = R.string.Mute;
        goto L64
    L59:
        r62 = R.drawable.msg_mute;
        goto L60
    L54:
        if (UserObject.isBotForumWithEditableTopics(r112) == false) goto L56;
    L47:
        r43 = R.string.DialogPin;
        goto L48
    L44:
        r52 = R.drawable.msg_pin;
        goto L45
    L40:
        if (UserObject.isBotForumWithEditableTopics(r112) == false) goto L52;
    L16:
        r112 = null;
        goto L17
    L12:
        r5 = null;
        goto L13
    }

    private void setAttached(boolean r3) {
        if (this.notificationsAttached != r3) goto L5;
        return;
    L5:
        this.notificationsAttached = r3;
        int r0 = this.currentAccount;
        if (r3 == false) goto L9;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.topicsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        MessagesController.getInstance(this.currentAccount).getTopicsController().onTopicFragmentResume(-this.dialogId);
        return;
    L9:
        MessagesController.getInstance(r0).getTopicsController().onTopicFragmentPause(-this.dialogId);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.topicsDidLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
    }

    private void updateTabs() {
        checkTopicsVisibility(true);
        boolean r1 = this.topTabs.canScrollHorizontally(-1);
        this.topTabs.adapter.update(true);
        if (r1 == true) goto L5;
        this.topTabs.scrollToPosition(0);
    L5:
        boolean r12 = this.sideTabs.canScrollVertically(-1);
        this.sideTabs.adapter.update(true);
        if (r12 == true) goto L8;
        this.sideTabs.scrollToPosition(0);
    L8:
        AndroidUtilities.runOnUIThread(new TopicsTabsView$$ExternalSyntheticLambda9(this));
    }

    private void whenReordered(int r5, ArrayList<UItem> r6) {
        TopicsController r52 = MessagesController.getInstance(this.currentAccount).getTopicsController();
        ArrayList<Integer> r0 = new ArrayList();
        int r2 = 0;
    L4:
        if (r2 >= r6.size()) goto L6;
        r0.add(Integer.valueOf(r6.get(r2).id));
        r2 = r2 + 1;
        goto L4
    L6:
        r52.reorderPinnedTopics(-this.dialogId, r0);
        r52.sortTopics(-this.dialogId, false);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.topicsDidLoaded) goto L10;
        if (((Long) r5[0]).longValue() != (-this.dialogId)) goto L17;
        updateTabs();
        return;
    L17:
        return;
    L10:
        if (r3 == NotificationCenter.updateInterfaces) goto L12;
        return;
    L12:
        if ((((Integer) r5[0]).intValue() & MessagesController.UPDATE_MASK_SELECT_DIALOG) <= 0) goto L16;
        MessagesController.getInstance(this.currentAccount).getTopicsController().sortTopics(-this.dialogId, false);
        updateTabs();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r7) {
        if (this.sideTabsContainer.getVisibility() != 0) goto L6;
        this.sideMenuBackgroundDrawable.setBounds((int) this.sideTabsContainer.getTranslationX(), (int) this.sideMenuBackgroundMarginTop, (int) (this.sideTabsContainer.getTranslationX() + AndroidUtilities.dp(78.0f)), (int) (getMeasuredHeight() - this.sideMenuBackgroundMarginBottom));
        this.sideMenuBackgroundDrawable.draw(r7);
    L6:
        if (this.topTabsContainer.getVisibility() != 0) goto L8;
        this.topMenuBackgroundDrawable.setAlpha((int) (this.topTabsContainer.getAlpha() * 255.0f));
        this.topMenuBackgroundDrawable.setBounds(0, (int) this.topTabsContainer.getTranslationY(), getMeasuredWidth(), (int) (this.topTabsContainer.getTranslationY() + AndroidUtilities.dp(50.0f)));
        this.topMenuBackgroundDrawable.draw(r7);
    L8:
        r7.save();
        r7.clipRect(0, 0, getWidth(), getHeight());
        super.dispatchDraw(r7);
        r7.restore();
    }

    public void doOnUpdateSideMenuPosition(Runnable r1) {
        this.onUpdateSideMenuPosition = r1;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r2, View r3, long r4) {
        r2.save();
        if (r3 != this.sideTabsContainer) goto L6;
        r2.clipPath(this.sideMenuBackgroundDrawable.getPath());
    L6:
        if (r3 != this.topTabsContainer) goto L8;
        r2.clipPath(this.topMenuBackgroundDrawable.getPath());
    L8:
        boolean r1 = super.drawChild(r2, r3, r4);
        r2.restore();
        return r1;
    }

    public Position getCurrentTabsPosition() {
        if (this.sidemenuEnabled == false) goto L7;
        return Position.LEFT;
    L7:
        if (this.topicBottom == false) goto L11;
        return Position.BOTTOM;
    L11:
        return Position.TOP;
    }

    public float getSideMenuT() {
        return this.sidemenuT * this.animatorTopicsVisibility.getFloatValue();
    }

    public float getTabsVisibleSpaceWithPadding(Position r1, float r2) {
        return (getTabsSize(r1) + r2) * getTabsVisibility(r1);
    }

    public TLRPC.TL_forumTopic getTopic(long r6) {
        ArrayList<TLRPC.TL_forumTopic> r5 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopics(-this.dialogId);
        if (r5 == null) goto L9;
        int r0 = r5.size();
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L14;
        TLRPC.TL_forumTopic r2 = r5.get(r1);
        r1 = r1 + 1;
        TLRPC.TL_forumTopic r22 = r2;
        if (r22.id != r6) goto L5;
        return r22;
    L14:
        return null;
    L9:
        return null;
    }

    public boolean isSideMenuEnabled() {
        if (this.sidemenuEnabled == true) goto L5;
        return false;
    L5:
        if (this.animatorTopicsVisibility.getValue() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setAttached(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setAttached(false);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        updateSidemenuPosition();
    }

    @Override // android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        checkUi_topicsVerticalPosition();
    }

    public void selectTopic(long r2, boolean r4) {
        if (this.mono == false) goto L8;
        Utilities.Callback2<Long, Boolean> r1 = this.onDialogSelected;
        if (r1 == null) goto L12;
        r1.run(Long.valueOf(r2), Boolean.valueOf(r4));
        return;
    L12:
        return;
    L8:
        Utilities.Callback2<Integer, Boolean> r12 = this.onTopicSelected;
        if (r12 == null) goto L13;
        r12.run(Integer.valueOf((int) r2), Boolean.valueOf(r4));
        return;
    }

    public void setAllTopicsHidden(boolean r2) {
        if (this.allTopicsHidden == r2) goto L6;
        this.allTopicsHidden = r2;
        checkTopicsVisibility(true);
        return;
    }

    public void setCurrentTopic(long r7) {
        this.currentTopicId = r7;
        this.topTabs.adapter.update(true);
        this.topTabs.invalidate();
        this.sideTabs.adapter.update(true);
        VerticalTabView r0 = this.botCreateTopicButtonVertical;
        if (r0 != null) goto L5;
    L9:
        HorizontalTabView r6 = this.botCreateTopicButtonHorizontal;
        if (r6 != null) goto L12;
        return;
    L12:
        if (r7 != 0) goto L14;
        boolean r72 = true;
    L15:
        r6.setAll(true, false, r72);
        return;
    L14:
        r72 = false;
        goto L15
    L5:
        if (r7 != 0) goto L7;
        boolean r5 = true;
    L8:
        r0.setAll(true, false, r5);
        goto L9
    L7:
        r5 = false;
        goto L8
    }

    public void setOnDialogSelected(Utilities.Callback2<Long, Boolean> r1) {
        this.onDialogSelected = r1;
    }

    public void setOnNewTopicSelected(Runnable r1) {
        this.onTopicCreated = r1;
    }

    public void setOnTopicSelected(Utilities.Callback2<Integer, Boolean> r1) {
        this.onTopicSelected = r1;
    }

    public void setSideMenuBackgroundDrawable(BlurredBackgroundDrawable r2) {
        this.sideMenuBackgroundDrawable = r2;
        r2.setRadius(AndroidUtilities.dp(16.0f));
        this.sideMenuBackgroundDrawable.setPadding(AndroidUtilities.dp(7.0f));
    }

    public void setSideMenuBackgroundMarginBottom(float r1) {
        this.sideMenuBackgroundMarginBottom = r1;
        checkUi_topicsVerticalPosition();
        checkSideTabsPadding(true);
        invalidate();
    }

    public void setSideMenuBackgroundMarginTop(float r2) {
        this.sideMenuBackgroundMarginTop = r2;
        this.sideTabsContainer.setTranslationY(r2);
        checkUi_topicsVerticalPosition();
        checkSideTabsPadding(true);
        invalidate();
    }

    public void setTopMenuBackgroundDrawable(BlurredBackgroundDrawable r2) {
        this.topMenuBackgroundDrawable = r2;
        r2.setRadius(AndroidUtilities.dp(18.0f));
        this.topMenuBackgroundDrawable.setPadding(AndroidUtilities.dp(7.0f));
    }

    public void updateSidemenuPosition() {
        Runnable r0 = this.onUpdateSideMenuPosition;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        checkUi_topicsVerticalPosition();
        float r02 = getTabsVisibility(Position.LEFT);
        int r3 = 0;
        this.sideTabsContainer.setTranslationX(AndroidUtilities.lerp(-AndroidUtilities.dp(78.0f), 0, r02));
        FrameLayout r1 = this.sideTabsContainer;
        if (r02 > 0.0f) goto L9;
        r3 = 8;
    L9:
        r1.setVisibility(r3);
        ImageView r03 = this.toggleButtonTop;
        int r2 = Theme.key_windowBackgroundWhiteGrayText2;
        int r32 = Theme.getColor(r2, this.resourcesProvider);
        int r4 = Theme.key_featuredStickers_addButton;
        int r33 = ColorUtils.blendARGB(r32, Theme.getColor(r4, this.resourcesProvider), 1.0f - this.sidemenuT);
        PorterDuff.Mode r5 = PorterDuff.Mode.SRC_IN;
        r03.setColorFilter(new PorterDuffColorFilter(r33, r5));
        this.toggleButtonSide.setColorFilter(new PorterDuffColorFilter(ColorUtils.blendARGB(Theme.getColor(r2, this.resourcesProvider), Theme.getColor(r4, this.resourcesProvider), this.sidemenuT), r5));
        this.closeButtonTop.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4, this.resourcesProvider), r5));
        this.closeButtonSide.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r4, this.resourcesProvider), r5));
        invalidate();
    }
}
