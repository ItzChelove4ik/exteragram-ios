package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import com.exteragram.messenger.IconPackType;
import com.exteragram.messenger.icons.IconManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.Premium.PremiumLockIconView;
import org.telegram.ui.Components.Reactions.HwEmojis;

/* loaded from: classes7.dex */
public abstract class EmojiTabsStrip extends ScrollableHorizontalScrollView {
    private static int[] emojiTabsAnimatedDrawableIds;
    private static int[] emojiTabsDrawableIds;
    private final int accentColor;
    public boolean animateAppear;
    private int animatedEmojiCacheType;
    private ValueAnimator appearAnimation;
    private int appearCount;
    private int currentType;
    private EmojiTabsView emojiTabs;
    boolean first;
    private boolean forceTabsShow;
    private int giftsDrawableId;
    private boolean giftsFirstChange;
    private boolean giftsIsShown;
    public EmojiTabButton giftsTab;
    private boolean includeAnimated;
    private final boolean isGlassDesign;
    private Runnable onSettingsOpenRunnable;
    private int packsIndexStart;
    private float paddingLeftDp;
    private int recentDrawableId;
    private boolean recentFirstChange;
    private boolean recentIsShown;
    public EmojiTabButton recentTab;
    private HashMap<View, Rect> removingViews;
    private Theme.ResourcesProvider resourcesProvider;
    private float selectAnimationT;
    private ValueAnimator selectAnimator;
    private float selectT;
    private int selected;
    private int selectedFullIndex;
    private int settingsDrawableId;
    private EmojiTabButton settingsTab;
    private boolean showSelected;
    private AnimatedFloat showSelectedAlpha;
    public EmojiTabButton toggleEmojiStickersTab;
    public boolean updateButtonDrawables;
    private boolean wasDrawn;
    private int wasIndex;

    /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        private final RectF from;
        private final LongSparseArray<Integer> lastX;
        private final Paint paint;
        private final Path path;
        private final RectF rect;
        final /* synthetic */ EmojiTabsStrip this$0;
        private final RectF to;
        final /* synthetic */ boolean val$includeAnimated;
        final /* synthetic */ boolean val$isGlassDesign;

        public AnonymousClass1(EmojiTabsStrip r1, Context r2, boolean r3, boolean r4) {
            this.this$0 = r1;
            this.val$includeAnimated = r3;
            this.val$isGlassDesign = r4;
            super(r2);
            this.lastX = new LongSparseArray();
            this.paint = new Paint(1);
            this.from = new RectF();
            this.to = new RectF();
            this.rect = new RectF();
            this.path = new Path();
        }

        private void getChildBounds(int r6, RectF r7) {
            View r5 = getChildAt(MathUtils.clamp(r6, 0, getChildCount() - 1));
            if (r5 != null) goto L5;
            return;
        L5:
            r7.set(r5.getLeft(), r5.getTop(), r5.getRight(), r5.getBottom());
            r7.set(r7.centerX() - ((r7.width() / 2.0f) * r5.getScaleX()), r7.centerY() - ((r7.height() / 2.0f) * r5.getScaleY()), r7.centerX() + ((r7.width() / 2.0f) * r5.getScaleX()), r7.centerY() + ((r7.height() / 2.0f) * r5.getScaleY()));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r12) {
            Iterator r0 = EmojiTabsStrip.m10260$$Nest$fgetremovingViews(this.this$0).entrySet().iterator();
        L4:
            if (r0.hasNext() == false) goto L9;
            Map.Entry r1 = (Map.Entry) r0.next();
            View r3 = (View) r1.getKey();
            if (r3 == null) goto L4;
            Rect r13 = (Rect) r1.getValue();
            r12.save();
            r12.translate(r13.left, r13.top);
            r12.scale(r3.getScaleX(), r3.getScaleY(), r13.width() / 2.0f, r13.height() / 2.0f);
            r3.draw(r12);
            r12.restore();
            goto L4
        L9:
            if (EmojiTabsStrip.m10266$$Nest$fgetshowSelectedAlpha(this.this$0) != null) goto L11;
            EmojiTabsStrip.m10267$$Nest$fputshowSelectedAlpha(this.this$0, new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT_QUINT));
        L11:
            AnimatedFloat r02 = EmojiTabsStrip.m10266$$Nest$fgetshowSelectedAlpha(this.this$0);
            float r32 = 0.0f;
            if (EmojiTabsStrip.m10265$$Nest$fgetshowSelected(this.this$0) == false) goto L14;
            float r14 = 1.0f;
        L15:
            float r03 = r02.set(r14);
            int r15 = (int) Math.floor(EmojiTabsStrip.m10263$$Nest$fgetselectT(this.this$0));
            int r5 = (int) Math.ceil(EmojiTabsStrip.m10263$$Nest$fgetselectT(this.this$0));
            getChildBounds(r15, this.from);
            getChildBounds(r5, this.to);
            AndroidUtilities.lerp(this.from, this.to, EmojiTabsStrip.m10263$$Nest$fgetselectT(this.this$0) - r15, this.rect);
            if (EmojiTabsStrip.m10254$$Nest$fgetemojiTabs(this.this$0) == null) goto L23;
            float r16 = EmojiTabsStrip.m10263$$Nest$fgetselectT(this.this$0);
            if (this.this$0.giftsTab == null) goto L21;
            int r33 = 1;
        L22:
            r32 = 1.0f - Utilities.clamp01(Math.abs(r16 - (r33 + 1)));
            goto L23
        L21:
            r33 = 0;
        L23:
            float r17 = (EmojiTabsStrip.m10262$$Nest$fgetselectAnimationT(this.this$0) * 4.0f) * (1.0f - EmojiTabsStrip.m10262$$Nest$fgetselectAnimationT(this.this$0));
            float r6 = (this.rect.width() / 2.0f) * ((0.3f * r17) + 1.0f);
            float r7 = (this.rect.height() / 2.0f) * (1.0f - (r17 * 0.05f));
            RectF r18 = this.rect;
            r18.set(r18.centerX() - r6, this.rect.centerY() - r7, this.rect.centerX() + r6, this.rect.centerY() + r7);
            float r19 = AndroidUtilities.dp(AndroidUtilities.lerp(8.0f, 16.0f, r32));
            this.paint.setColor(EmojiTabsStrip.m10270$$Nest$mselectorColor(this.this$0));
            boolean r72 = EmojiTabsStrip.m10255$$Nest$fgetforceTabsShow(this.this$0);
            Paint r8 = this.paint;
            if (r72 == false) goto L26;
            r8.setAlpha((int) ((r8.getAlpha() * r03) * (1.0f - (r32 * 0.5f))));
        L27:
            this.path.rewind();
            Path r04 = this.path;
            RectF r34 = this.rect;
            if (this.val$isGlassDesign == false) goto L30;
            float r4 = r34.height() / 2.0f;
        L32:
            if (this.val$isGlassDesign == false) goto L34;
            r19 = this.rect.height() / 2.0f;
        L34:
            Path.Direction r73 = Path.Direction.CW;
            r04.addRoundRect(r34, r4, r19, r73);
            r12.drawPath(this.path, this.paint);
            if (EmojiTabsStrip.m10255$$Nest$fgetforceTabsShow(this.this$0) == false) goto L40;
            if (this.val$includeAnimated == false) goto L40;
            this.path.rewind();
            getChildBounds(1, this.rect);
            this.path.addRoundRect(this.rect, AndroidUtilities.dpf2(16.0f), AndroidUtilities.dpf2(16.0f), r73);
            this.paint.setColor(EmojiTabsStrip.m10270$$Nest$mselectorColor(this.this$0));
            this.paint.setAlpha((int) (r0.getAlpha() * 0.5f));
            r12.drawPath(this.path, this.paint);
        L40:
            if (EmojiTabsStrip.m10254$$Nest$fgetemojiTabs(this.this$0) == null) goto L42;
            this.path.addCircle(EmojiTabsStrip.m10254$$Nest$fgetemojiTabs(this.this$0).getLeft() + AndroidUtilities.dp(15.0f), (EmojiTabsStrip.m10254$$Nest$fgetemojiTabs(this.this$0).getTop() + EmojiTabsStrip.m10254$$Nest$fgetemojiTabs(this.this$0).getBottom()) / 2.0f, AndroidUtilities.dp(15.0f), r73);
        L42:
            super.dispatchDraw(r12);
            EmojiTabsStrip.m10268$$Nest$fputwasDrawn(this.this$0, true);
            return;
        L30:
            r4 = r19;
            goto L32
        L26:
            r8.setAlpha((int) (r8.getAlpha() * r03));
            goto L27
        L14:
            r14 = 0.0f;
            goto L15
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != EmojiTabsStrip.m10254$$Nest$fgetemojiTabs(this.this$0)) goto L7;
            r2.save();
            r2.clipPath(this.path);
            boolean r1 = super.drawChild(r2, r3, r4);
            r2.restore();
            return r1;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
            int r122 = (r12 - r10) / 2;
            int r102 = 0;
            if (this.val$includeAnimated == false) goto L64;
            int r82 = getPaddingLeft();
            int r0 = 0;
        L6:
            if (r0 >= getChildCount()) goto L51;
            View r1 = getChildAt(r0);
            if (r1 == EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0)) goto L49;
            if (EmojiTabsStrip.m10260$$Nest$fgetremovingViews(this.this$0).containsKey(r1) == true) goto L49;
            if (r1 == null) goto L49;
            r1.layout(r82, r122 - (r1.getMeasuredHeight() / 2), r1.getMeasuredWidth() + r82, (r1.getMeasuredHeight() / 2) + r122);
            boolean r3 = r1 instanceof EmojiTabButton;
            if (r3 == false) goto L17;
            Long r4 = ((EmojiTabButton) r1).id();
        L21:
            if (this.this$0.animateAppear == false) goto L30;
            if (r3 == false) goto L30;
            EmojiTabButton r32 = (EmojiTabButton) r1;
            if (r32.newly == false) goto L30;
            r32.newly = false;
            r1.setScaleX(0.0f);
            r1.setScaleY(0.0f);
            r1.setAlpha(0.0f);
            ViewPropertyAnimator r33 = r1.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f);
            if (HwEmojis.isHwEnabledOrPreparing() == false) goto L28;
            long r5 = 0;
        L29:
            r33.setDuration(r5).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            goto L30
        L28:
            r5 = 200;
        L30:
            if (r4 == null) goto L39;
            Integer r34 = this.lastX.get(r4.longValue());
            if (r34 != null) goto L34;
        L38:
            this.lastX.put(r4.longValue(), Integer.valueOf(r82));
            goto L39
        L34:
            if (r34.intValue() == r82) goto L38;
            if (Math.abs(r34.intValue() - r82) >= AndroidUtilities.dp(45.0f)) goto L38;
            r1.setTranslationX(r34.intValue() - r82);
            r1.animate().translationX(0.0f).setDuration(250).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L39:
            EmojiTabsStrip r2 = this.this$0;
            if (r1 == r2.recentTab) goto L42;
        L43:
            EmojiTabsStrip r22 = this.this$0;
            if (r1 == r22.giftsTab) goto L46;
        L48:
            r82 = r82 + (r1.getMeasuredWidth() + AndroidUtilities.dp(3.0f));
            goto L49
        L46:
            if (EmojiTabsStrip.m10256$$Nest$fgetgiftsIsShown(r22) == true) goto L48;
        L42:
            if (EmojiTabsStrip.m10259$$Nest$fgetrecentIsShown(r2) == false) goto L49;
        L17:
            if ((r1 instanceof EmojiTabsView) == false) goto L19;
            r4 = Long.valueOf(((EmojiTabsView) r1).id);
            goto L21
        L19:
            r4 = null;
        L49:
            r0 = r0 + 1;
            goto L6
        L51:
            if (EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0) == null) goto L70;
            Long r103 = EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).id;
            int r02 = (EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).getMeasuredWidth() + r82) + getPaddingRight();
            int r13 = this.this$0.getMeasuredWidth();
            EmojiTabsStrip r35 = this.this$0;
            if (r02 > r13) goto L55;
            EmojiTabButton r83 = EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(r35);
            int r112 = r11 - r9;
            int r92 = (r112 - getPaddingRight()) - EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).getMeasuredWidth();
            r83.layout(r92, r122 - (EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).getMeasuredHeight() / 2), r112 - getPaddingRight(), r122 + (EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).getMeasuredHeight() / 2));
            r82 = r92;
        L56:
            if (r103 != null) goto L58;
            return;
        L58:
            if (this.lastX.get(r103.longValue()) != null) goto L60;
        L62:
            this.lastX.put(r103.longValue(), Integer.valueOf(r82));
            return;
        L60:
            if (this.lastX.get(r103.longValue()).intValue() == r82) goto L62;
            EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).setTranslationX(this.lastX.get(r103.longValue()).intValue() - r82);
            EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).animate().translationX(0.0f).setDuration(350).start();
            goto L62
        L55:
            EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(r35).layout(r82, r122 - (EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).getMeasuredHeight() / 2), EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).getMeasuredWidth() + r82, r122 + (EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$0).getMeasuredHeight() / 2));
            goto L56
        L70:
            return;
        L64:
            int r84 = (getChildCount() - (!EmojiTabsStrip.m10259$$Nest$fgetrecentIsShown(this.this$0) ? 1 : 0)) - (!EmojiTabsStrip.m10256$$Nest$fgetgiftsIsShown(this.this$0) ? 1 : 0);
            int r93 = (int) (((((r11 - r9) - getPaddingLeft()) - getPaddingRight()) - (AndroidUtilities.dp(30.0f) * r84)) / Math.max(1, r84 - 1));
            int r113 = getPaddingLeft();
        L65:
            if (r102 >= r84) goto L82;
            View r03 = getChildAt(((!EmojiTabsStrip.m10259$$Nest$fgetrecentIsShown(this.this$0) ? 1 : 0) + (!EmojiTabsStrip.m10256$$Nest$fgetgiftsIsShown(this.this$0) ? 1 : 0)) + r102);
            if (r03 == null) goto L69;
            r03.layout(r113, r122 - (r03.getMeasuredHeight() / 2), r03.getMeasuredWidth() + r113, (r03.getMeasuredHeight() / 2) + r122);
            r113 = r113 + (r03.getMeasuredWidth() + r93);
        L69:
            r102 = r102 + 1;
            goto L65
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r0 = View.MeasureSpec.makeMeasureSpec(99999999, Integer.MIN_VALUE);
            int r1 = getPaddingLeft() + getPaddingRight();
            float r3 = 0.0f;
            if (EmojiTabsStrip.m10259$$Nest$fgetrecentIsShown(this.this$0) == true) goto L8;
            EmojiTabButton r2 = this.this$0.recentTab;
            if (r2 == null) goto L8;
            float r22 = r2.getAlpha() * AndroidUtilities.dp(33.0f);
        L9:
            int r12 = r1 - ((int) r22);
            if (EmojiTabsStrip.m10256$$Nest$fgetgiftsIsShown(this.this$0) == true) goto L15;
            EmojiTabButton r23 = this.this$0.giftsTab;
            if (r23 == null) goto L15;
            r3 = AndroidUtilities.dp(33.0f) * r23.getAlpha();
        L15:
            int r13 = r12 - ((int) r3);
            int r32 = 0;
        L17:
            if (r32 >= getChildCount()) goto L27;
            View r4 = getChildAt(r32);
            if (r4 == null) goto L25;
            r4.measure(r0, r9);
            int r42 = r4.getMeasuredWidth();
            if ((r32 + 1) >= getChildCount()) goto L23;
            int r5 = AndroidUtilities.dp(3.0f);
        L24:
            r13 = r13 + (r42 + r5);
            goto L25
        L23:
            r5 = 0;
        L25:
            r32 = r32 + 1;
            goto L17
        L27:
            if (this.val$includeAnimated == true) goto L30;
            setMeasuredDimension(View.MeasureSpec.getSize(r8), View.MeasureSpec.getSize(r9));
            return;
        L30:
            setMeasuredDimension(Math.max(r13, View.MeasureSpec.getSize(r8)), View.MeasureSpec.getSize(r9));
            return;
        L8:
            r22 = 0.0f;
            goto L9
        }
    }

    public class EmojiTabButton extends ViewGroup {
        AnimatedEmojiDrawable animatedEmoji;
        TLRPC.Document animatedEmojiDocument;
        Long animatedEmojiDocumentId;
        boolean attached;
        private boolean forceSelector;
        public Long id;
        private BackupImageView imageView;
        private boolean isAnimatedEmoji;
        private boolean isVisible;
        public boolean keepAttached;
        private Boolean lastLock;
        private ValueAnimator lockAnimator;
        private float lockT;
        private PremiumLockIconView lockView;
        private RLottieDrawable lottieDrawable;
        public boolean newly;
        private boolean round;
        private ValueAnimator selectAnimator;
        private float selectT;
        private boolean selected;
        EmojiView.EmojiPack setObject;
        public boolean shown;
        final /* synthetic */ EmojiTabsStrip this$0;

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabButton$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ EmojiTabButton this$1;
            final /* synthetic */ EmojiTabsStrip val$this$0;

            public AnonymousClass1(EmojiTabButton r1, Context r2, EmojiTabsStrip r3) {
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
                this.this$1.updateLockImageReceiver();
            }

            @Override // android.view.View
            public void invalidate(int r2, int r3, int r4, int r5) {
                if (HwEmojis.grab(this) == false) goto L5;
                return;
            L5:
                super.invalidate(r2, r3, r4, r5);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabButton$2, reason: invalid class name */
        public class AnonymousClass2 extends PremiumLockIconView {
            final /* synthetic */ EmojiTabButton this$1;
            final /* synthetic */ EmojiTabsStrip val$this$0;

            public AnonymousClass2(EmojiTabButton r1, Context r2, int r3, Theme.ResourcesProvider r4, EmojiTabsStrip r5) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                super(r2, r3, r4);
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

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabButton$3, reason: invalid class name */
        public class AnonymousClass3 extends BackupImageView {
            final /* synthetic */ EmojiTabButton this$1;
            final /* synthetic */ EmojiTabsStrip val$this$0;

            public AnonymousClass3(EmojiTabButton r1, Context r2, EmojiTabsStrip r3) {
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
                this.this$1.updateLockImageReceiver();
            }

            @Override // android.view.View
            public void invalidate(int r2, int r3, int r4, int r5) {
                if (HwEmojis.grab(this) == false) goto L5;
                return;
            L5:
                super.invalidate(r2, r3, r4, r5);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabButton$4, reason: invalid class name */
        public class AnonymousClass4 extends PremiumLockIconView {
            final /* synthetic */ EmojiTabButton this$1;
            final /* synthetic */ EmojiTabsStrip val$this$0;

            public AnonymousClass4(EmojiTabButton r1, Context r2, int r3, Theme.ResourcesProvider r4, EmojiTabsStrip r5) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                super(r2, r3, r4);
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

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabButton$5, reason: invalid class name */
        public class AnonymousClass5 extends AnimatorListenerAdapter {
            final /* synthetic */ EmojiTabButton this$1;
            final /* synthetic */ boolean val$enable;

            public AnonymousClass5(EmojiTabButton r1, boolean r2) {
                this.this$1 = r1;
                this.val$enable = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                if (this.val$enable == true) goto L6;
                EmojiTabButton.m10276$$Nest$fgetlockView(this.this$1).setVisibility(8);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabButton$6, reason: invalid class name */
        public class AnonymousClass6 extends AnimatorListenerAdapter {
            final /* synthetic */ EmojiTabButton this$1;
            final /* synthetic */ boolean val$selected;

            public AnonymousClass6(EmojiTabButton r1, boolean r2) {
                this.this$1 = r1;
                this.val$selected = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                EmojiTabButton r22 = this.this$1;
                if (r22.this$0.updateButtonDrawables == true) goto L5;
                return;
            L5:
                if (EmojiTabButton.m10277$$Nest$fgetround(r22) == false) goto L7;
                return;
            L7:
                if (EmojiTabsStrip.m10257$$Nest$fgetincludeAnimated(this.this$1.this$0) == true) goto L9;
                return;
            L9:
                if (this.val$selected == true) goto L16;
                if (EmojiTabButton.m10275$$Nest$fgetforceSelector(this.this$1) == true) goto L16;
                this.this$1.setBackground(null);
                return;
            L16:
                if (this.this$1.getBackground() != null) goto L22;
                EmojiTabButton r1 = this.this$1;
                r1.setBackground(Theme.createRadSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r1.this$0), 8, 8));
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$LvE6jSNksFWSRUj1DI9tcKOp5v8(EmojiTabButton r0, ValueAnimator r1) {
            r0.lambda$updateLock$0(r1);
        }

        /* renamed from: $r8$lambda$PA3FbMWn1GMQh-LKvHnRtKBw0Ig, reason: not valid java name */
        public static /* synthetic */ void m10274$r8$lambda$PA3FbMWn1GMQhLKvHnRtKBw0Ig(EmojiTabButton r0, ValueAnimator r1) {
            r0.lambda$updateSelect$1(r1);
        }

        /* renamed from: -$$Nest$fgetforceSelector, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10275$$Nest$fgetforceSelector(EmojiTabButton r0) {
            return r0.forceSelector;
        }

        /* renamed from: -$$Nest$fgetlockView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ PremiumLockIconView m10276$$Nest$fgetlockView(EmojiTabButton r0) {
            return r0.lockView;
        }

        /* renamed from: -$$Nest$fgetround, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m10277$$Nest$fgetround(EmojiTabButton r0) {
            return r0.round;
        }

        /* renamed from: -$$Nest$mupdateAttachState, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10278$$Nest$mupdateAttachState(EmojiTabButton r0) {
            r0.updateAttachState();
        }

        public EmojiTabButton(EmojiTabsStrip r9, Context r10, int r11, int r12, boolean r13, boolean r14) {
            this.this$0 = r9;
            super(r10);
            this.shown = true;
            setFocusable(true);
            this.round = r13;
            this.forceSelector = r14;
            if (r13 == false) goto L5;
            setBackground(Theme.createCircleSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r9), 0, 0));
        L10:
            if (IconManager.INSTANCE.isBasePackOnly(IconPackType.DEFAULT) == false) goto L12;
            RLottieDrawable r2 = new RLottieDrawable(r12, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), false, null);
            this.lottieDrawable = r2;
            r2.setBounds(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(27.0f), AndroidUtilities.dp(27.0f));
            this.lottieDrawable.setMasterParent(this);
            this.lottieDrawable.setAllowDecodeSingleFrame(true);
            this.lottieDrawable.start();
        L13:
            updateColor();
            return;
        L12:
            BackupImageView r92 = new BackupImageView(r10);
            this.imageView = r92;
            r92.applyAttach = false;
            int r93 = AndroidUtilities.dp(20.0f);
            this.imageView.setSize(r93, r93);
            this.imageView.setImageDrawable(r10.getResources().getDrawable(r11).mutate());
            addView(this.imageView);
            goto L13
        L5:
            if (r14 == false) goto L10;
            if (EmojiTabsStrip.m10257$$Nest$fgetincludeAnimated(r9) == false) goto L10;
            setBackground(Theme.createRadSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r9), 8, 8));
            goto L10
        }

        public EmojiTabButton(EmojiTabsStrip r2, Context r3, int r4, boolean r5, boolean r6) {
            this.this$0 = r2;
            super(r3);
            this.shown = true;
            setFocusable(true);
            this.round = r5;
            this.forceSelector = r6;
            if (r5 == false) goto L5;
            setBackground(Theme.createCircleSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r2), 0, 0));
        L9:
            BackupImageView r22 = new BackupImageView(r3);
            this.imageView = r22;
            r22.applyAttach = false;
            r22.setImageDrawable(r3.getResources().getDrawable(r4).mutate());
            updateColor();
            addView(this.imageView);
            return;
        L5:
            if (r6 == false) goto L9;
            if (EmojiTabsStrip.m10257$$Nest$fgetincludeAnimated(r2) == false) goto L9;
            setBackground(Theme.createRadSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r2), 8, 8));
            goto L9
        }

        public EmojiTabButton(EmojiTabsStrip r7, Context r8, long r9, boolean r11, boolean r12, boolean r13) {
            this.this$0 = r7;
            super(r8);
            this.shown = true;
            setFocusable(true);
            this.newly = true;
            this.round = r12;
            this.forceSelector = r13;
            if (r12 == false) goto L5;
            setBackground(Theme.createCircleSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r7), 0, 0));
        L7:
            AnonymousClass3 r122 = new AnonymousClass3(this, r8, r7);
            this.imageView = r122;
            r122.applyAttach = false;
            this.animatedEmojiDocumentId = Long.valueOf(r9);
            this.isAnimatedEmoji = true;
            this.imageView.setColorFilter(r7.getEmojiColorFilter());
            addView(this.imageView);
            AnonymousClass4 r0 = new AnonymousClass4(this, r8, PremiumLockIconView.TYPE_STICKERS_PREMIUM_LOCKED, EmojiTabsStrip.m10261$$Nest$fgetresourcesProvider(r7), r7);
            this.lockView = r0;
            r0.setAlpha(0.0f);
            this.lockView.setScaleX(0.0f);
            this.lockView.setScaleY(0.0f);
            updateLockImageReceiver();
            addView(this.lockView);
            updateColor();
            return;
        L5:
            if (r13 == false) goto L7;
            setBackground(Theme.createRadSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r7), 8, 8));
            goto L7
        }

        public EmojiTabButton(EmojiTabsStrip r7, Context r8, TLRPC.Document r9, boolean r10, boolean r11, boolean r12) {
            this.this$0 = r7;
            super(r8);
            this.shown = true;
            setFocusable(true);
            this.newly = true;
            this.round = r11;
            this.forceSelector = r12;
            if (r11 == false) goto L5;
            setBackground(Theme.createCircleSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r7), 0, 0));
        L9:
            AnonymousClass1 r112 = new AnonymousClass1(this, r8, r7);
            this.imageView = r112;
            r112.applyAttach = false;
            this.animatedEmojiDocument = r9;
            this.isAnimatedEmoji = true;
            r112.setColorFilter(r7.getEmojiColorFilter());
            addView(this.imageView);
            AnonymousClass2 r0 = new AnonymousClass2(this, r8, PremiumLockIconView.TYPE_STICKERS_PREMIUM_LOCKED, EmojiTabsStrip.m10261$$Nest$fgetresourcesProvider(r7), r7);
            this.lockView = r0;
            r0.setAlpha(0.0f);
            this.lockView.setScaleX(0.0f);
            this.lockView.setScaleY(0.0f);
            updateLockImageReceiver();
            addView(this.lockView);
            updateColor();
            return;
        L5:
            if (r12 == false) goto L9;
            if (EmojiTabsStrip.m10257$$Nest$fgetincludeAnimated(r7) == false) goto L9;
            setBackground(Theme.createRadSelectorDrawable(EmojiTabsStrip.m10270$$Nest$mselectorColor(r7), 8, 8));
            goto L9
        }

        private void initLock() {
            if (this.lockView == null) goto L10;
            AnimatedEmojiDrawable r0 = this.animatedEmoji;
            if (r0 == null) goto L11;
            ImageReceiver r02 = r0.getImageReceiver();
            if (r02 == null) goto L12;
            this.lockView.setImageReceiver(r02);
            return;
        L12:
            return;
        L11:
            return;
        }

        private /* synthetic */ void lambda$updateLock$0(ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            this.lockT = r22;
            this.lockView.setScaleX(r22);
            this.lockView.setScaleY(this.lockT);
            this.lockView.setAlpha(this.lockT);
        }

        private /* synthetic */ void lambda$updateSelect$1(ValueAnimator r1) {
            this.selectT = ((Float) r1.getAnimatedValue()).floatValue();
            updateColor();
        }

        private void playAnimation() {
            AnimatedEmojiDrawable r4 = this.animatedEmoji;
            if (r4 == null) goto L11;
            ImageReceiver r42 = r4.getImageReceiver();
            if (r42 != null) goto L7;
            return;
        L7:
            if (r42.getAnimation() == null) goto L9;
            r42.getAnimation().seekTo(0, true);
        L9:
            r42.startAnimation();
            return;
        }

        private void setColor(int r3) {
            if (EmojiTabsStrip.m10253$$Nest$fgetcurrentType(this.this$0) != 5) goto L5;
        L6:
            r3 = EmojiTabsStrip.m10251$$Nest$fgetaccentColor(this.this$0);
        L7:
            PorterDuffColorFilter r0 = new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN);
            BackupImageView r32 = this.imageView;
            if (r32 != null) goto L10;
        L12:
            RLottieDrawable r33 = this.lottieDrawable;
            if (r33 == null) goto L16;
            r33.setColorFilter(r0);
            invalidate();
            return;
        L16:
            return;
        L10:
            if (this.isAnimatedEmoji == true) goto L12;
            r32.setColorFilter(r0);
            this.imageView.invalidate();
            goto L12
        L5:
            if (EmojiTabsStrip.m10253$$Nest$fgetcurrentType(this.this$0) != 7) goto L7;
            goto L6
        }

        private void stopAnimation() {
            AnimatedEmojiDrawable r2 = this.animatedEmoji;
            if (r2 == null) goto L14;
            ImageReceiver r22 = r2.getImageReceiver();
            if (r22 != null) goto L7;
            return;
        L7:
            if (r22.getLottieAnimation() == null) goto L11;
            r22.getLottieAnimation().setCurrentFrame(0);
            r22.getLottieAnimation().stop();
            return;
        L11:
            if (r22.getAnimation() == null) goto L16;
            r22.getAnimation().stop();
            return;
        L16:
            return;
        }

        private void updateAttachState() {
            BackupImageView r0 = this.imageView;
            if (r0 != null) goto L6;
            return;
        L6:
            if (this.attached == true) goto L8;
        L27:
            AnimatedEmojiDrawable r1 = this.animatedEmoji;
            if (r1 == null) goto L30;
            r1.removeView(r0);
            this.animatedEmoji = null;
        L30:
            this.imageView.clearImage();
        L32:
            if (this.attached == true) goto L34;
        L36:
            this.imageView.onDetachedFromWindow();
        L37:
            updateLockImageReceiver();
            return;
        L34:
            if (this.isVisible == false) goto L36;
            this.imageView.onAttachedToWindow();
            goto L37
        L8:
            if (this.isVisible == false) goto L27;
            AnimatedEmojiDrawable r12 = this.animatedEmoji;
            if (r12 == null) goto L12;
        L20:
            if (r12 == null) goto L22;
            r12.removeView(r0);
            this.animatedEmoji = null;
        L22:
            this.imageView.clearImage();
            EmojiView.EmojiPack r02 = this.setObject;
            if (r02 == null) goto L32;
            this.imageView.setImage(ImageLocation.getForStickerSet(r02.set), "24_24", null, null, this.setObject);
            if (this.setObject.needLoadSet == null) goto L32;
            MediaDataController.getInstance(UserConfig.selectedAccount).getStickerSet(this.setObject.needLoadSet, false);
            this.setObject.needLoadSet = null;
            goto L32
        L12:
            if (this.animatedEmojiDocument == null) goto L14;
        L15:
            r0.clearImage();
            if (this.animatedEmojiDocument == null) goto L18;
            this.animatedEmoji = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, EmojiTabsStrip.m10252$$Nest$fgetanimatedEmojiCacheType(this.this$0), this.animatedEmojiDocument);
        L19:
            this.animatedEmoji.addView(this.imageView);
            this.imageView.setImageDrawable(this.animatedEmoji);
            goto L32
        L18:
            this.animatedEmoji = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, EmojiTabsStrip.m10252$$Nest$fgetanimatedEmojiCacheType(this.this$0), this.animatedEmojiDocumentId.longValue());
            goto L19
        L14:
            if (this.animatedEmojiDocumentId == null) goto L20;
            goto L15
        }

        private void updateLock(boolean r5, boolean r6) {
            ValueAnimator r0 = this.lockAnimator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            float r02 = this.lockT;
            float r1 = 0.0f;
            if (r5 == false) goto L8;
            float r3 = 1.0f;
        L10:
            if (Math.abs(r02 - r3) >= 0.01f) goto L12;
            return;
        L12:
            int r03 = 0;
            if (r6 == false) goto L23;
            this.lockView.setVisibility(0);
            float r62 = this.lockT;
            if (r5 == false) goto L17;
            r1 = 1.0f;
        L17:
            ValueAnimator r63 = ValueAnimator.ofFloat(new float[]{r62, r1});
            this.lockAnimator = r63;
            r63.addUpdateListener(new EmojiTabsStrip$EmojiTabButton$$ExternalSyntheticLambda1(this));
            this.lockAnimator.addListener(new AnonymousClass5(this, r5));
            this.lockAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            ValueAnimator r52 = this.lockAnimator;
            if (HwEmojis.isHwEnabledOrPreparing() == false) goto L20;
            long r04 = 0;
        L21:
            r52.setDuration(r04);
            this.lockAnimator.start();
            return;
        L20:
            r04 = 200;
            goto L21
        L23:
            if (r5 == false) goto L25;
            r1 = 1.0f;
        L25:
            this.lockT = r1;
            this.lockView.setScaleX(r1);
            this.lockView.setScaleY(this.lockT);
            this.lockView.setAlpha(this.lockT);
            PremiumLockIconView r4 = this.lockView;
            if (r5 == true) goto L29;
            r03 = 8;
        L29:
            r4.setVisibility(r03);
            return;
        L8:
            r3 = 0.0f;
            goto L10
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            super.dispatchDraw(r2);
            RLottieDrawable r0 = this.lottieDrawable;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.isVisible == false) goto L9;
            r0.draw(r2);
            return;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (this.isVisible == true) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        public Drawable getDrawable() {
            BackupImageView r0 = this.imageView;
            if (r0 != null) goto L5;
            return null;
        L5:
            return r0.getImageReceiver().getImageDrawable();
        }

        public Long id() {
            Long r0 = this.id;
            if (r0 == null) goto L5;
            return r0;
        L5:
            EmojiView.EmojiPack r02 = this.setObject;
            if (r02 == null) goto L11;
            TLRPC.StickerSet r03 = r02.set;
            if (r03 == null) goto L11;
            return Long.valueOf(r03.id);
        L11:
            Long r04 = this.animatedEmojiDocumentId;
            if (r04 == null) goto L14;
            return r04;
        L14:
            TLRPC.Document r2 = this.animatedEmojiDocument;
            if (r2 != null) goto L17;
            return null;
        L17:
            return Long.valueOf(r2.id);
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

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.attached = true;
            updateAttachState();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.attached = false;
            updateAttachState();
        }

        @Override // android.view.View
        public void onDraw(Canvas r2) {
            if (this.isVisible == true) goto L5;
            return;
        L5:
            super.onDraw(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r7) {
            super.onInitializeAccessibilityNodeInfo(r7);
            CharSequence r0 = r7.getContentDescription();
            if (r0 != null) goto L19;
            EmojiView.EmojiPack r1 = this.setObject;
            if (r1 == null) goto L11;
            TLRPC.StickerSet r12 = r1.set;
            if (r12 == null) goto L11;
            String r13 = r12.title;
            if (r13 == null) goto L11;
            r0 = r13;
        L11:
            TLRPC.Document r14 = this.animatedEmojiDocument;
            if (r14 == null) goto L14;
            r0 = MessageObject.findAnimatedEmojiEmoticon(r14, null);
            goto L19
        L14:
            Long r15 = this.animatedEmojiDocumentId;
            if (r15 == null) goto L19;
            TLRPC.Document r16 = AnimatedEmojiDrawable.findDocument(UserConfig.selectedAccount, r15.longValue());
            if (r16 == null) goto L19;
            r0 = MessageObject.findAnimatedEmojiEmoticon(r16, null);
        L19:
            Boolean r17 = this.lastLock;
            if (r17 != null) goto L22;
        L27:
            if (r0 == null) goto L29;
            r7.setContentDescription(r0);
        L29:
            r7.setSelected(this.selected);
            return;
        L22:
            if (r17.booleanValue() == true) goto L27;
            String r18 = LocaleController.getString(R.string.FeaturedStickersShort);
            if (r0 != null) goto L26;
            r0 = r18;
            goto L27
        L26:
            r0 = r0 + ", " + r18;
            goto L27
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
            BackupImageView r62 = this.imageView;
            if (r62 == null) goto L5;
            int r0 = (r9 - r7) / 2;
            int r1 = (r10 - r8) / 2;
            r62.layout(r0 - (r62.getMeasuredWidth() / 2), r1 - (this.imageView.getMeasuredHeight() / 2), r0 + (this.imageView.getMeasuredWidth() / 2), r1 + (this.imageView.getMeasuredHeight() / 2));
        L5:
            PremiumLockIconView r63 = this.lockView;
            if (r63 == null) goto L9;
            int r92 = r9 - r7;
            int r102 = r10 - r8;
            r63.layout(r92 - r63.getMeasuredWidth(), r102 - this.lockView.getMeasuredHeight(), r92, r102);
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r3, int r4) {
            setMeasuredDimension(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f));
            BackupImageView r32 = this.imageView;
            if (r32 == null) goto L5;
            r32.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
        L5:
            PremiumLockIconView r2 = this.lockView;
            if (r2 == null) goto L9;
            r2.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(12.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(12.0f), TLObject.FLAG_30));
            return;
        }

        @Override // android.view.View
        public boolean performClick() {
            playAnimation();
            return super.performClick();
        }

        public void setAnimatedEmojiDocument(TLRPC.Document r5) {
            TLRPC.Document r0 = this.animatedEmojiDocument;
            if (r0 == null) goto L5;
        L6:
            if (r5 == null) goto L15;
            Long r1 = this.animatedEmojiDocumentId;
            if (r1 == null) goto L10;
            long r02 = r1.longValue();
        L12:
            if (r02 != r5.id) goto L15;
            return;
        L10:
            r02 = r0.id;
        L15:
            AnimatedEmojiDrawable r03 = this.animatedEmoji;
            if (r03 == null) goto L18;
            r03.removeView(this.imageView);
            this.animatedEmoji = null;
        L18:
            this.imageView.clearImage();
            this.animatedEmojiDocument = r5;
            this.animatedEmojiDocumentId = null;
            updateAttachState();
            return;
        L5:
            if (this.animatedEmojiDocumentId == null) goto L15;
            goto L6
        }

        public void setAnimatedEmojiDocumentId(long r6) {
            TLRPC.Document r0 = this.animatedEmojiDocument;
            if (r0 != null) goto L7;
            if (this.animatedEmojiDocumentId != null) goto L7;
        L16:
            AnimatedEmojiDrawable r02 = this.animatedEmoji;
            Long r3 = null;
            if (r02 == null) goto L19;
            r02.removeView(this.imageView);
            this.animatedEmoji = null;
        L19:
            this.imageView.clearImage();
            this.animatedEmojiDocument = null;
            if (r6 == 0) goto L23;
            r3 = Long.valueOf(r6);
        L23:
            this.animatedEmojiDocumentId = r3;
            updateAttachState();
            return;
        L7:
            if (r6 == 0) goto L16;
            Long r32 = this.animatedEmojiDocumentId;
            if (r32 == null) goto L11;
            long r33 = r32.longValue();
        L13:
            if (r33 != r6) goto L16;
            return;
        L11:
            r33 = r0.id;
            goto L13
        }

        public void setDrawable(Drawable r2) {
            setAnimatedEmojiDocument(null);
            setStickerThumb(null);
            this.imageView.setImageDrawable(r2);
        }

        public void setLock(Boolean r3, boolean r4) {
            if (this.lockView != null) goto L5;
            return;
        L5:
            this.lastLock = r3;
            if (r3 != null) goto L9;
            updateLock(false, r4);
            return;
        L9:
            updateLock(true, r4);
            if (r3.booleanValue() == false) goto L13;
            this.lockView.setImageResource(R.drawable.msg_mini_lockedemoji);
            return;
        L13:
            Drawable r32 = getResources().getDrawable(R.drawable.msg_mini_addemoji).mutate();
            r32.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
            this.lockView.setImageDrawable(r32);
        }

        public void setStickerThumb(EmojiView.EmojiPack r6) {
            if (r6 != null) goto L5;
        L7:
            EmojiView.EmojiPack r1 = this.setObject;
            if (r1 == null) goto L14;
            if (r6 == null) goto L14;
            if (r1.set.id != r6.set.id) goto L14;
            return;
        L14:
            AnimatedEmojiDrawable r12 = this.animatedEmoji;
            if (r12 != null) goto L17;
        L21:
            this.imageView.clearImage();
            this.setObject = r6;
            updateAttachState();
            return;
        L17:
            if (this.animatedEmojiDocument != null) goto L21;
            if (this.animatedEmojiDocumentId != null) goto L21;
            r12.removeView(this.imageView);
            this.animatedEmoji = null;
            goto L21
        L5:
            if (r6.set != null) goto L7;
            r6 = null;
            goto L7
        }

        public void updateColor() {
            Theme.setSelectorDrawableColor(getBackground(), EmojiTabsStrip.m10270$$Nest$mselectorColor(this.this$0), false);
            if (EmojiTabsStrip.m10258$$Nest$fgetisGlassDesign(this.this$0) == false) goto L6;
            setColor(EmojiTabsStrip.m10269$$Nest$mgetGlassIconColor(this.this$0, AndroidUtilities.lerp(0.4f, 0.8f, this.selectT)));
            return;
        L6:
            setColor(ColorUtils.blendARGB(Theme.getColor(Theme.key_chat_emojiPanelIcon, EmojiTabsStrip.m10261$$Nest$fgetresourcesProvider(this.this$0)), Theme.getColor(Theme.key_chat_emojiPanelIconSelected, EmojiTabsStrip.m10261$$Nest$fgetresourcesProvider(this.this$0)), this.selectT));
        }

        public void updateLockImageReceiver() {
            PremiumLockIconView r0 = this.lockView;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.done() == false) goto L7;
            return;
        L7:
            if ((getDrawable() instanceof AnimatedEmojiDrawable) == true) goto L9;
            return;
        L9:
            if (((AnimatedEmojiDrawable) getDrawable()).canOverrideColor() == false) goto L12;
            this.lockView.setImageReceiver(null);
            this.lockView.setColor(EmojiTabsStrip.m10251$$Nest$fgetaccentColor(this.this$0));
            return;
        L12:
            ImageReceiver r02 = ((AnimatedEmojiDrawable) getDrawable()).getImageReceiver();
            if (r02 == null) goto L19;
            this.lockView.setImageReceiver(r02);
            this.lockView.invalidate();
            return;
        }

        public void updateSelect(boolean r4, boolean r5) {
            BackupImageView r0 = this.imageView;
            if (r0 == null) goto L10;
            if (r0.getImageReceiver().getImageDrawable() != null) goto L10;
            if (EmojiTabsStrip.m10258$$Nest$fgetisGlassDesign(this.this$0) == true) goto L10;
            return;
        L10:
            if (this.selected != r4) goto L12;
            return;
        L12:
            this.selected = r4;
            ValueAnimator r02 = this.selectAnimator;
            if (r02 == null) goto L15;
            r02.cancel();
            this.selectAnimator = null;
        L15:
            if (r4 == true) goto L17;
            stopAnimation();
        L17:
            float r03 = 0.0f;
            if (r5 == false) goto L28;
            float r52 = this.selectT;
            if (r4 == false) goto L22;
            r03 = 1.0f;
        L22:
            ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r52, r03});
            this.selectAnimator = r53;
            r53.addUpdateListener(new EmojiTabsStrip$EmojiTabButton$$ExternalSyntheticLambda0(this));
            this.selectAnimator.addListener(new AnonymousClass6(this, r4));
            ValueAnimator r42 = this.selectAnimator;
            if (HwEmojis.isHwEnabledOrPreparing() == false) goto L25;
            long r04 = 0;
        L26:
            r42.setDuration(r04);
            this.selectAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.selectAnimator.start();
            return;
        L25:
            r04 = 350;
            goto L26
        L28:
            if (r4 == false) goto L30;
            r03 = 1.0f;
        L30:
            this.selectT = r03;
            updateColor();
        }

        public void updateVisibilityInbounds(boolean r2, boolean r3) {
            if (this.isVisible == true) goto L12;
            if (r2 == false) goto L12;
            RLottieDrawable r0 = this.lottieDrawable;
            if (r0 == null) goto L12;
            if (r0.isRunning() == true) goto L12;
            if (r3 == true) goto L12;
            this.lottieDrawable.setProgress(0.0f);
            this.lottieDrawable.start();
        L12:
            if (this.isVisible == r2) goto L24;
            this.isVisible = r2;
            if (r2 == false) goto L21;
            invalidate();
            PremiumLockIconView r22 = this.lockView;
            if (r22 == null) goto L18;
            r22.invalidate();
        L18:
            initLock();
            BackupImageView r23 = this.imageView;
            if (r23 == null) goto L22;
            r23.invalidate();
        L22:
            updateAttachState();
            return;
        L21:
            stopAnimation();
            goto L22
        }
    }

    public class EmojiTabsView extends ScrollableHorizontalScrollView {
        public long id;
        private float showT;
        private boolean shown;
        final /* synthetic */ EmojiTabsStrip this$0;

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabsView$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayout {
            final /* synthetic */ EmojiTabsView this$1;
            final /* synthetic */ EmojiTabsStrip val$this$0;

            public AnonymousClass1(EmojiTabsView r1, Context r2, EmojiTabsStrip r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
                int r32 = getPaddingLeft();
                int r72 = (r7 - r5) / 2;
                int r42 = 0;
            L4:
                if (r42 >= getChildCount()) goto L11;
                View r52 = getChildAt(r42);
                if (r52 == EmojiTabsStrip.m10264$$Nest$fgetsettingsTab(this.this$1.this$0)) goto L10;
                if (r52 == null) goto L10;
                r52.layout(r32, r72 - (r52.getMeasuredHeight() / 2), r52.getMeasuredWidth() + r32, (r52.getMeasuredHeight() / 2) + r72);
                r32 = r32 + (r52.getMeasuredWidth() + AndroidUtilities.dp(2.0f));
            L10:
                r42 = r42 + 1;
                goto L4
            }

            @Override // android.widget.LinearLayout, android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(Math.max(View.MeasureSpec.getSize(r3), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.this$1.contentView.getChildCount() * 32), TLObject.FLAG_30)), r4);
            }
        }

        /* renamed from: org.telegram.ui.Components.EmojiTabsStrip$EmojiTabsView$2, reason: invalid class name */
        public class AnonymousClass2 extends EmojiTabButton {
            final /* synthetic */ EmojiTabsView this$1;
            final /* synthetic */ EmojiTabsStrip val$this$0;

            public AnonymousClass2(EmojiTabsView r1, Context r2, int r3, int r4, boolean r5, boolean r6, EmojiTabsStrip r7) {
                this.this$1 = r1;
                this.val$this$0 = r7;
                super(r1.this$0, r2, r3, r4, r5, r6);
            }

            @Override // android.view.View
            public boolean onTouchEvent(MotionEvent r2) {
                EmojiTabsView.m10280$$Nest$mintercept(this.this$1, r2);
                return super.onTouchEvent(r2);
            }
        }

        /* renamed from: $r8$lambda$sh5jkq4CwdOxBo-9e_pB9ZhuTpE, reason: not valid java name */
        public static /* synthetic */ void m10279$r8$lambda$sh5jkq4CwdOxBo9e_pB9ZhuTpE(EmojiTabsView r0, ValueAnimator r1) {
            r0.lambda$show$0(r1);
        }

        /* renamed from: -$$Nest$mintercept, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10280$$Nest$mintercept(EmojiTabsView r0, MotionEvent r1) {
            r0.intercept(r1);
        }

        public EmojiTabsView(EmojiTabsStrip r11, Context r12) {
            this.this$0 = r11;
            super(r12);
            this.shown = EmojiTabsStrip.m10255$$Nest$fgetforceTabsShow(r11);
            if (EmojiTabsStrip.m10255$$Nest$fgetforceTabsShow(r11) == false) goto L5;
            float r0 = 1.0f;
        L6:
            this.showT = r0;
            setSmoothScrollingEnabled(true);
            int r1 = 0;
            setHorizontalScrollBarEnabled(false);
            setVerticalScrollBarEnabled(false);
            setNestedScrollingEnabled(true);
            AnonymousClass1 r02 = new AnonymousClass1(this, r12, r11);
            this.contentView = r02;
            r02.setOrientation(0);
            addView(this.contentView, new FrameLayout.LayoutParams(-2, -1));
        L8:
            if (r1 >= EmojiTabsStrip.m10272$$Nest$sfgetemojiTabsDrawableIds().length) goto L10;
            EmojiTabsView r3 = this;
            AnonymousClass2 r2 = new AnonymousClass2(r3, r12, EmojiTabsStrip.m10272$$Nest$sfgetemojiTabsDrawableIds()[r1], EmojiTabsStrip.m10271$$Nest$sfgetemojiTabsAnimatedDrawableIds()[r1], true, false, r11);
            r2.setContentDescription(EmojiTabsStrip.m10273$$Nest$smgetEmojiCategoryName(r1));
            r3.contentView.addView(r2);
            r1 = r1 + 1;
            EmojiTabsView this = r3;
            goto L8
        L10:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }

        private void intercept(MotionEvent r3) {
            if (this.shown == true) goto L5;
            return;
        L5:
            if (this.scrollingAnimation == true) goto L20;
            int r32 = r3.getAction();
            if (r32 == 0) goto L14;
            if (r32 != 1) goto L10;
            this.touching = false;
            return;
        L10:
            if (r32 == 2) goto L14;
            return;
        L14:
            this.touching = true;
            if (this.scrollingAnimation == true) goto L17;
            resetScrollTo();
        L17:
            this.this$0.requestDisallowInterceptTouchEvent(true);
            return;
        }

        private /* synthetic */ void lambda$show$0(ValueAnimator r1) {
            this.showT = ((Float) r1.getAnimatedValue()).floatValue();
            invalidate();
            requestLayout();
            updateButtonsVisibility();
            this.this$0.contentView.invalidate();
        }

        public int maxWidth() {
            return AndroidUtilities.dp(Math.min(5.7f, this.contentView.getChildCount()) * 32.0f);
        }

        @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.lerp(AndroidUtilities.dp(30.0f), maxWidth(), this.showT), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.Components.ScrollableHorizontalScrollView, android.widget.HorizontalScrollView, android.view.View
        public boolean onTouchEvent(MotionEvent r1) {
            intercept(r1);
            return super.onTouchEvent(r1);
        }

        public void show(boolean r4, boolean r5) {
            if (r4 != this.shown) goto L5;
            return;
        L5:
            this.shown = r4;
            if (r4 == true) goto L8;
            scrollTo(0);
        L8:
            ValueAnimator r1 = this.showAnimator;
            if (r1 == null) goto L11;
            r1.cancel();
        L11:
            float r12 = 0.0f;
            if (r5 == false) goto L18;
            float r52 = this.showT;
            if (r4 == false) goto L16;
            r12 = 1.0f;
        L16:
            ValueAnimator r42 = ValueAnimator.ofFloat(new float[]{r52, r12});
            this.showAnimator = r42;
            r42.addUpdateListener(new EmojiTabsStrip$EmojiTabsView$$ExternalSyntheticLambda0(this));
            this.showAnimator.setDuration(475);
            this.showAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.showAnimator.start();
            return;
        L18:
            if (r4 == false) goto L20;
            r12 = 1.0f;
        L20:
            this.showT = r12;
            invalidate();
            requestLayout();
            updateButtonsVisibility();
            this.this$0.contentView.invalidate();
        }
    }

    public static class StabDrawable extends Drawable {
        private final Paint paint;
        private final RectF rectF;

        public StabDrawable(int r3) {
            Paint r0 = new Paint();
            this.paint = r0;
            this.rectF = new RectF();
            r0.setAlpha(45);
            r0.setColor(r3);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r5) {
            this.rectF.set(0.0f, 0.0f, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f));
            r5.drawRoundRect(this.rectF, AndroidUtilities.dpf2(8.0f), AndroidUtilities.dpf2(8.0f), this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.paint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    /* renamed from: $r8$lambda$7Z-GyUZ2gwhHpNy82qyQKHyjLE8, reason: not valid java name */
    public static /* synthetic */ void m10248$r8$lambda$7ZGyUZ2gwhHpNy82qyQKHyjLE8(EmojiTabsStrip r0, int r1, View r2) {
        r0.lambda$updateClickListeners$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FW8kYOXApKKz0Gy13Rse5307drU(EmojiTabsStrip r0, boolean r1) {
        r0.lambda$showGifts$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$U_ch1Jpo5u_dJqmaAWhzAlX7QiY(EmojiTabsStrip r0, View r1) {
        r0.lambda$updateClickListeners$3(r1);
    }

    /* renamed from: $r8$lambda$_K4hGvUNLM-IFe2aoy5MvxKu3X0, reason: not valid java name */
    public static /* synthetic */ void m10249$r8$lambda$_K4hGvUNLMIFe2aoy5MvxKu3X0(EmojiTabsStrip r0, float r1, float r2, ValueAnimator r3) {
        r0.lambda$select$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hXz3-RvFuBkFbeKQ6grhvsJ6zrs, reason: not valid java name */
    public static /* synthetic */ void m10250$r8$lambda$hXz3RvFuBkFbeKQ6grhvsJ6zrs(EmojiTabsStrip r0, int r1, View r2) {
        r0.lambda$updateClickListeners$2(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaccentColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10251$$Nest$fgetaccentColor(EmojiTabsStrip r0) {
        return r0.accentColor;
    }

    /* renamed from: -$$Nest$fgetanimatedEmojiCacheType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10252$$Nest$fgetanimatedEmojiCacheType(EmojiTabsStrip r0) {
        return r0.animatedEmojiCacheType;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10253$$Nest$fgetcurrentType(EmojiTabsStrip r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetemojiTabs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiTabsView m10254$$Nest$fgetemojiTabs(EmojiTabsStrip r0) {
        return r0.emojiTabs;
    }

    /* renamed from: -$$Nest$fgetforceTabsShow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10255$$Nest$fgetforceTabsShow(EmojiTabsStrip r0) {
        return r0.forceTabsShow;
    }

    /* renamed from: -$$Nest$fgetgiftsIsShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10256$$Nest$fgetgiftsIsShown(EmojiTabsStrip r0) {
        return r0.giftsIsShown;
    }

    /* renamed from: -$$Nest$fgetincludeAnimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10257$$Nest$fgetincludeAnimated(EmojiTabsStrip r0) {
        return r0.includeAnimated;
    }

    /* renamed from: -$$Nest$fgetisGlassDesign, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10258$$Nest$fgetisGlassDesign(EmojiTabsStrip r0) {
        return r0.isGlassDesign;
    }

    /* renamed from: -$$Nest$fgetrecentIsShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10259$$Nest$fgetrecentIsShown(EmojiTabsStrip r0) {
        return r0.recentIsShown;
    }

    /* renamed from: -$$Nest$fgetremovingViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m10260$$Nest$fgetremovingViews(EmojiTabsStrip r0) {
        return r0.removingViews;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m10261$$Nest$fgetresourcesProvider(EmojiTabsStrip r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetselectAnimationT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10262$$Nest$fgetselectAnimationT(EmojiTabsStrip r0) {
        return r0.selectAnimationT;
    }

    /* renamed from: -$$Nest$fgetselectT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10263$$Nest$fgetselectT(EmojiTabsStrip r0) {
        return r0.selectT;
    }

    /* renamed from: -$$Nest$fgetsettingsTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiTabButton m10264$$Nest$fgetsettingsTab(EmojiTabsStrip r0) {
        return r0.settingsTab;
    }

    /* renamed from: -$$Nest$fgetshowSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10265$$Nest$fgetshowSelected(EmojiTabsStrip r0) {
        return r0.showSelected;
    }

    /* renamed from: -$$Nest$fgetshowSelectedAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m10266$$Nest$fgetshowSelectedAlpha(EmojiTabsStrip r0) {
        return r0.showSelectedAlpha;
    }

    /* renamed from: -$$Nest$fputshowSelectedAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10267$$Nest$fputshowSelectedAlpha(EmojiTabsStrip r0, AnimatedFloat r1) {
        r0.showSelectedAlpha = r1;
    }

    /* renamed from: -$$Nest$fputwasDrawn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10268$$Nest$fputwasDrawn(EmojiTabsStrip r0, boolean r1) {
        r0.wasDrawn = r1;
    }

    /* renamed from: -$$Nest$mgetGlassIconColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10269$$Nest$mgetGlassIconColor(EmojiTabsStrip r0, float r1) {
        return r0.getGlassIconColor(r1);
    }

    /* renamed from: -$$Nest$mselectorColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10270$$Nest$mselectorColor(EmojiTabsStrip r0) {
        return r0.selectorColor();
    }

    /* renamed from: -$$Nest$sfgetemojiTabsAnimatedDrawableIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m10271$$Nest$sfgetemojiTabsAnimatedDrawableIds() {
        return emojiTabsAnimatedDrawableIds;
    }

    /* renamed from: -$$Nest$sfgetemojiTabsDrawableIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m10272$$Nest$sfgetemojiTabsDrawableIds() {
        return emojiTabsDrawableIds;
    }

    /* renamed from: -$$Nest$smgetEmojiCategoryName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m10273$$Nest$smgetEmojiCategoryName(int r0) {
        return getEmojiCategoryName(r0);
    }

    static {
        emojiTabsDrawableIds = new int[]{R.drawable.msg_emoji_smiles, R.drawable.msg_emoji_cat, R.drawable.msg_emoji_food, R.drawable.msg_emoji_activities, R.drawable.msg_emoji_travel, R.drawable.msg_emoji_objects, R.drawable.msg_emoji_other, R.drawable.msg_emoji_flags};
        emojiTabsAnimatedDrawableIds = new int[]{R.raw.msg_emoji_smiles, R.raw.msg_emoji_cat, R.raw.msg_emoji_food, R.raw.msg_emoji_activities, R.raw.msg_emoji_travel, R.raw.msg_emoji_objects, R.raw.msg_emoji_other, R.raw.msg_emoji_flags};
    }

    public EmojiTabsStrip(Context r13, Theme.ResourcesProvider r14, boolean r15, boolean r16, boolean r17, boolean r18, int r19, Runnable r20) {
        this(r13, r14, r15, r16, r17, r18, r19, r20, Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, r14), false);
    }

    public EmojiTabsStrip(Context r12, Theme.ResourcesProvider r13, boolean r14, boolean r15, boolean r16, boolean r17, int r18, Runnable r19, int r20) {
        this(r12, r13, r14, r15, r16, r17, r18, r19, r20, false);
    }

    public EmojiTabsStrip(Context r15, Theme.ResourcesProvider r16, boolean r17, boolean r18, boolean r19, boolean r20, int r21, Runnable r22, int r23, boolean r24) {
        super(r15);
        this.recentDrawableId = R.drawable.msg_emoji_recent;
        this.giftsDrawableId = R.drawable.msg_emoji_gem;
        this.settingsDrawableId = R.drawable.smiles_tab_settings;
        this.forceTabsShow = !UserConfig.getInstance(UserConfig.selectedAccount).isPremium();
        this.showSelected = true;
        this.removingViews = new HashMap();
        this.selectT = 0.0f;
        this.selectAnimationT = 0.0f;
        this.selected = 0;
        this.selectedFullIndex = 0;
        this.wasIndex = 0;
        this.animateAppear = true;
        this.animatedEmojiCacheType = 6;
        this.updateButtonDrawables = true;
        this.recentFirstChange = true;
        this.recentIsShown = true;
        this.giftsFirstChange = true;
        this.giftsIsShown = false;
        this.first = true;
        this.paddingLeftDp = 11.0f;
        this.includeAnimated = r20;
        this.resourcesProvider = r16;
        this.onSettingsOpenRunnable = r22;
        this.currentType = r21;
        this.accentColor = r23;
        this.isGlassDesign = r24;
        AnonymousClass1 r3 = new AnonymousClass1(this, r15, r20, r24);
        this.contentView = r3;
        r3.setClipToPadding(false);
        this.contentView.setOrientation(0);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        addView(this.contentView);
        if (r21 != 4) goto L6;
        LinearLayout r13 = this.contentView;
        EmojiTabButton r0 = new EmojiTabButton(this, r15, R.drawable.msg_emoji_stickers, false, false);
        this.toggleEmojiStickersTab = r0;
        r13.addView(r0);
        this.toggleEmojiStickersTab.setContentDescription(LocaleController.getString(R.string.AccDescrStickers));
    L6:
        if (r21 != 3) goto L8;
        this.recentDrawableId = R.drawable.msg_emoji_smiles;
    L8:
        if (r21 != 6) goto L10;
        this.recentDrawableId = R.drawable.emoji_love;
    L10:
        if (r17 == false) goto L12;
        LinearLayout r7 = this.contentView;
        EmojiTabButton r02 = new EmojiTabButton(this, r15, this.recentDrawableId, false, false);
        this.recentTab = r02;
        r7.addView(r02);
        this.recentTab.setContentDescription(LocaleController.getString(R.string.RecentlyUsed));
        this.recentTab.id = -934918565L;
    L12:
        if (r18 == false) goto L14;
        LinearLayout r72 = this.contentView;
        EmojiTabButton r03 = new EmojiTabButton(this, r15, this.giftsDrawableId, false, false);
        this.giftsTab = r03;
        r72.addView(r03);
        this.giftsTab.setContentDescription(LocaleController.getString(R.string.EmojiPackCollectibles));
        this.giftsTab.setAlpha(0.0f);
        this.giftsTab.id = 98352451L;
    L14:
        if (r20 == true) goto L25;
        int r04 = 0;
    L16:
        int[] r2 = emojiTabsDrawableIds;
        if (r04 >= r2.length) goto L23;
        int r25 = r2[r04];
        if (r04 != 0) goto L21;
        boolean r4 = true;
    L22:
        EmojiTabButton r32 = new EmojiTabButton(this, r15, r25, false, r4);
        r32.setContentDescription(getEmojiCategoryName(r04));
        this.contentView.addView(r32);
        r04 = r04 + 1;
        goto L16
    L21:
        r4 = false;
        goto L22
    L23:
        updateClickListeners();
        return;
    L25:
        if (r19 == false) goto L27;
        LinearLayout r05 = this.contentView;
        EmojiTabsView r33 = new EmojiTabsView(this, r15);
        this.emojiTabs = r33;
        r05.addView(r33);
        this.emojiTabs.id = 3552126;
    L27:
        this.packsIndexStart = this.contentView.getChildCount();
        if (r22 == null) goto L30;
        LinearLayout r06 = this.contentView;
        EmojiTabButton r34 = new EmojiTabButton(this, r15, this.settingsDrawableId, false, true);
        this.settingsTab = r34;
        r06.addView(r34);
        this.settingsTab.setContentDescription(LocaleController.getString(R.string.Settings));
        this.settingsTab.id = 1434631203L;
        this.settingsTab.setAlpha(0.0f);
    L30:
        updateClickListeners();
    }

    public EmojiTabsStrip(Context r13, Theme.ResourcesProvider r14, boolean r15, boolean r16, boolean r17, boolean r18, int r19, Runnable r20, boolean r21) {
        this(r13, r14, r15, r16, r17, r18, r19, r20, Theme.getColor(Theme.key_windowBackgroundWhiteBlueIcon, r14), r21);
    }

    private static String getEmojiCategoryName(int r0) {
        switch(r0) {
            case 0: goto L20;
            case 1: goto L18;
            case 2: goto L16;
            case 3: goto L14;
            case 4: goto L12;
            case 5: goto L10;
            case 6: goto L8;
            case 7: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return LocaleController.getString(R.string.Emoji8);
    L8:
        return LocaleController.getString(R.string.Emoji7);
    L10:
        return LocaleController.getString(R.string.Emoji6);
    L12:
        return LocaleController.getString(R.string.Emoji5);
    L14:
        return LocaleController.getString(R.string.Emoji4);
    L16:
        return LocaleController.getString(R.string.Emoji3);
    L18:
        return LocaleController.getString(R.string.Emoji2);
    L20:
        return LocaleController.getString(R.string.Emoji1);
    }

    private int getGlassIconColor(float r2) {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), (int) (r2 * 255.0f));
    }

    private TLRPC.Document getThumbDocument(TLRPC.StickerSet r8, ArrayList<TLRPC.Document> r9) {
        if (r8 != null) goto L6;
        return null;
    L6:
        if (r9 == null) goto L14;
        int r1 = 0;
    L9:
        if (r1 >= r9.size()) goto L14;
        TLRPC.Document r2 = r9.get(r1);
        if (r2.id == r8.thumb_document_id) goto L12;
        r1 = r1 + 1;
        goto L9
    L12:
        return r2;
    L14:
        if (r9 != null) goto L16;
        return null;
    L16:
        if (r9.size() >= 1) goto L18;
        return null;
    L18:
        return r9.get(0);
    }

    private /* synthetic */ void lambda$select$4(float r1, float r2, ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.selectAnimationT = r32;
        this.selectT = AndroidUtilities.lerp(r1, r2, r32);
        this.contentView.invalidate();
    }

    private /* synthetic */ void lambda$showGifts$0(boolean r1) {
        if (r1 == true) goto L5;
        this.giftsTab.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updateClickListeners$1(int r1, View r2) {
        onTabClick(r1);
    }

    private /* synthetic */ void lambda$updateClickListeners$2(int r1, View r2) {
        onTabClick(r1);
    }

    private /* synthetic */ void lambda$updateClickListeners$3(View r1) {
        Runnable r0 = this.onSettingsOpenRunnable;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    private int selectorColor() {
        if (this.isGlassDesign == true) goto L5;
        int r0 = this.currentType;
        if (r0 == 5) goto L14;
        if (r0 == 7) goto L14;
        return Theme.multAlpha(Theme.getColor(Theme.key_chat_emojiPanelIcon, this.resourcesProvider), 0.18f);
    L14:
        return Theme.multAlpha(this.accentColor, 0.09f);
    L5:
        return getGlassIconColor(0.05f);
    }

    public boolean allowEmojisForNonPremium() {
        return false;
    }

    public boolean doIncludeFeatured() {
        return true;
    }

    public ColorFilter getEmojiColorFilter() {
        return Theme.getAnimatedEmojiColorFilter(this.resourcesProvider);
    }

    public boolean isGiftsVisible() {
        if (this.giftsTab != null) goto L5;
        return false;
    L5:
        if (this.giftsIsShown == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isInstalled(EmojiView.EmojiPack r1) {
        return r1.installed;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        this.contentView.setPadding(AndroidUtilities.dp(this.paddingLeftDp), 0, AndroidUtilities.dp(11.0f), 0);
        super.onMeasure(r5, r6);
    }

    public abstract boolean onTabClick(int r1);

    public void onTabCreate(EmojiTabButton r1) {
    }

    @Override // org.telegram.ui.Components.ScrollableHorizontalScrollView, android.widget.HorizontalScrollView, android.view.View
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent r1) {
        return super.onTouchEvent(r1);
    }

    @Override // org.telegram.ui.Components.ScrollableHorizontalScrollView
    public /* bridge */ /* synthetic */ void scrollTo(int r1) {
        super.scrollTo(r1);
    }

    @Override // org.telegram.ui.Components.ScrollableHorizontalScrollView
    public /* bridge */ /* synthetic */ boolean scrollToVisible(int r1, int r2) {
        return super.scrollToVisible(r1, r2);
    }

    public void select(int r2) {
        select(r2, true);
    }

    public void select(int r11, boolean r12) {
        if (r12 == true) goto L5;
    L7:
        boolean r122 = false;
    L8:
        EmojiTabButton r2 = this.toggleEmojiStickersTab;
        if (r2 == null) goto L12;
        r11 = r11 + 1;
    L12:
        if (this.recentIsShown == false) goto L14;
        if (r2 != null) goto L14;
    L15:
        this.selectedFullIndex = r11;
        int r22 = this.selected;
        int r3 = 0;
        int r4 = 0;
    L17:
        if (r3 >= this.contentView.getChildCount()) goto L51;
        View r5 = this.contentView.getChildAt(r3);
        if (r5 != this.giftsTab) goto L24;
        if (isGiftsVisible() == true) goto L24;
        int r42 = r4 - 1;
    L49:
        r3 = r3 + 1;
        r4 = r42 + 1;
    L24:
        if ((r5 instanceof EmojiTabsView) == false) goto L38;
        EmojiTabsView r52 = (EmojiTabsView) r5;
        int r6 = 0;
        int r7 = r4;
    L27:
        if (r6 >= r52.contentView.getChildCount()) goto L36;
        View r8 = r52.contentView.getChildAt(r6);
        if ((r8 instanceof EmojiTabButton) == false) goto L35;
        EmojiTabButton r82 = (EmojiTabButton) r8;
        if (r11 != r7) goto L33;
        boolean r9 = true;
    L34:
        r82.updateSelect(r9, r122);
        goto L35
    L33:
        r9 = false;
    L35:
        r6 = r6 + 1;
        r7 = r7 + 1;
        goto L27
    L36:
        int r72 = r7 - 1;
    L45:
        if (r11 < r4) goto L48;
        if (r11 > r72) goto L48;
        this.selected = r3;
    L48:
        r42 = r72;
        goto L49
    L38:
        if ((r5 instanceof EmojiTabButton) == false) goto L44;
        EmojiTabButton r53 = (EmojiTabButton) r5;
        if (r11 != r4) goto L42;
        boolean r62 = true;
    L43:
        r53.updateSelect(r62, r122);
        goto L44
    L42:
        r62 = false;
    L44:
        r72 = r4;
        goto L45
    L51:
        if (r22 == this.selected) goto L74;
        ValueAnimator r23 = this.selectAnimator;
        if (r23 == null) goto L55;
        r23.cancel();
    L55:
        float r24 = this.selectT;
        float r32 = this.selected;
        if (r122 == false) goto L58;
        ValueAnimator r54 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.selectAnimator = r54;
        r54.addUpdateListener(new EmojiTabsStrip$$ExternalSyntheticLambda4(this, r24, r32));
        this.selectAnimator.setDuration(350);
        this.selectAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.selectAnimator.start();
    L59:
        EmojiTabsView r25 = this.emojiTabs;
        if (r25 != null) goto L62;
    L69:
        View r123 = this.contentView.getChildAt(this.selected);
        if (this.selected < 2) goto L72;
        scrollToVisible(r123.getLeft(), r123.getRight());
        goto L74
    L72:
        scrollTo(0);
        goto L74
    L62:
        if (this.selected != 1) goto L64;
    L67:
        boolean r33 = true;
    L68:
        r25.show(r33, r122);
        goto L69
    L64:
        if (this.forceTabsShow == true) goto L67;
        r33 = false;
        goto L68
    L58:
        this.selectAnimationT = 1.0f;
        this.selectT = AndroidUtilities.lerp(r24, r32, 1.0f);
        this.contentView.invalidate();
    L74:
        if (this.wasIndex == r11) goto L91;
        EmojiTabsView r124 = this.emojiTabs;
        if (r124 != null) goto L78;
    L83:
        this.wasIndex = r11;
        return;
    L78:
        if (this.selected != 1) goto L83;
        if (r11 < 1) goto L83;
        if (r11 > (r124.contentView.getChildCount() + 1)) goto L83;
        int r0 = (r11 - 1) * 36;
        this.emojiTabs.scrollToVisible(AndroidUtilities.dp(r0 - 6), AndroidUtilities.dp(r0 + 24));
        goto L83
    L91:
        return;
    L14:
        r11 = Math.max(1, r11);
        goto L15
    L5:
        if (this.first == true) goto L7;
        r122 = true;
        goto L8
    }

    public void setAnimatedEmojiCacheType(int r1) {
        this.animatedEmojiCacheType = r1;
    }

    public void setPaddingLeft(float r1) {
        this.paddingLeftDp = r1;
    }

    public void showGifts(boolean r6) {
        EmojiTabButton r0 = this.giftsTab;
        if (r0 == null) goto L24;
        boolean r1 = this.giftsFirstChange;
        if (r1 == false) goto L7;
    L9:
        this.giftsIsShown = r6;
        float r2 = 0.0f;
        if (r1 == false) goto L18;
        if (r6 == false) goto L13;
        int r12 = 0;
    L14:
        r0.setVisibility(r12);
        EmojiTabButton r02 = this.giftsTab;
        if (r6 == false) goto L17;
        r2 = 1.0f;
    L17:
        r02.setAlpha(r2);
    L22:
        this.contentView.requestLayout();
        this.giftsFirstChange = false;
        return;
    L13:
        r12 = 8;
        goto L14
    L18:
        r0.setVisibility(0);
        ViewPropertyAnimator r03 = this.giftsTab.animate();
        if (r6 == false) goto L21;
        r2 = 1.0f;
    L21:
        r03.alpha(r2).setDuration(200).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new EmojiTabsStrip$$ExternalSyntheticLambda3(this, r6)).start();
        goto L22
    L7:
        if (this.giftsIsShown != r6) goto L9;
        return;
    }

    public void showRecent(boolean r5) {
        if (this.recentIsShown != r5) goto L5;
        return;
    L5:
        this.recentIsShown = r5;
        boolean r0 = this.recentFirstChange;
        EmojiTabButton r1 = this.recentTab;
        float r2 = 0.0f;
        if (r0 == false) goto L10;
        if (r5 == false) goto L9;
        r2 = 1.0f;
    L9:
        r1.setAlpha(r2);
    L15:
        if (r5 == false) goto L17;
    L18:
        if (r5 == true) goto L20;
    L22:
        this.contentView.requestLayout();
        this.recentFirstChange = false;
        return;
    L20:
        if (this.selected != 1) goto L22;
    L21:
        select(0, !this.recentFirstChange);
        goto L22
    L17:
        if (this.selected == 0) goto L21;
    L10:
        ViewPropertyAnimator r02 = r1.animate();
        if (r5 == false) goto L13;
        r2 = 1.0f;
    L13:
        r02.alpha(r2).setDuration(200).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        goto L15
    }

    public void showRecentTabStub(boolean r2) {
        EmojiTabButton r0 = this.recentTab;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r2 == false) goto L8;
        r0.setBackground(new StabDrawable(selectorColor()));
        return;
    L8:
        r0.setBackground(null);
    }

    public void showSelected(boolean r1) {
        this.showSelected = r1;
        this.contentView.invalidate();
    }

    public void updateClickListeners() {
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.contentView.getChildCount()) goto L19;
        View r3 = this.contentView.getChildAt(r1);
        if (r3 != this.giftsTab) goto L11;
        if (isGiftsVisible() == true) goto L11;
    L9:
        r2 = r2 - 1;
    L18:
        r1 = r1 + 1;
        r2 = r2 + 1;
    L11:
        if ((r3 instanceof EmojiTabsView) == false) goto L16;
        EmojiTabsView r32 = (EmojiTabsView) r3;
        int r4 = 0;
    L14:
        if (r4 >= r32.contentView.getChildCount()) goto L9;
        r32.contentView.getChildAt(r4).setOnClickListener(new EmojiTabsStrip$$ExternalSyntheticLambda0(this, r2));
        r4 = r4 + 1;
        r2 = r2 + 1;
        goto L14
    L16:
        if (r3 == null) goto L18;
        r3.setOnClickListener(new EmojiTabsStrip$$ExternalSyntheticLambda1(this, r2));
        goto L18
    L19:
        EmojiTabButton r0 = this.settingsTab;
        if (r0 == null) goto L28;
        r0.setOnClickListener(new EmojiTabsStrip$$ExternalSyntheticLambda2(this));
        return;
    }

    public void updateColors() {
        EmojiTabButton r0 = this.recentTab;
        if (r0 == null) goto L6;
        r0.updateColor();
        return;
    }

    public void updateEmojiPacks(ArrayList<EmojiView.EmojiPack> r19) {
        if (this.includeAnimated == true) goto L6;
        return;
    L6:
        if (this.first == true) goto L8;
    L10:
        this.first = false;
        if (r19 != null) goto L13;
        return;
    L13:
        int r0 = this.contentView.getChildCount() - this.packsIndexStart;
        if (this.settingsTab == null) goto L16;
        int r2 = 1;
    L17:
        int r12 = r0 - r2;
        if (r12 == 0) goto L20;
    L24:
        Boolean r13 = null;
        if (this.appearAnimation != null) goto L27;
    L29:
        this.appearCount = r19.size();
        doIncludeFeatured();
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L32;
    L35:
        boolean r14 = true;
    L36:
        ArrayList r15 = new ArrayList();
        int r6 = 0;
    L38:
        if (r6 >= Math.max(r19.size(), r12)) goto L97;
        if (r6 >= r12) goto L41;
        EmojiTabButton r02 = (EmojiTabButton) this.contentView.getChildAt(this.packsIndexStart + r6);
    L43:
        if (r6 >= r19.size()) goto L45;
        EmojiView.EmojiPack r22 = r19.get(r6);
    L46:
        if (r22 != null) goto L51;
        if (r02 == null) goto L49;
        this.contentView.removeView(r02);
    L49:
        int r11 = r6;
        Boolean r23 = r13;
    L96:
        r6 = r11 + 1;
        r13 = r23;
        goto L38
    L51:
        if (r22.resId == 0) goto L55;
        if (r02 != null) goto L54;
        EmojiTabButton r03 = new EmojiTabButton(this, getContext(), r22.resId, false, false);
        onTabCreate(r03);
        this.contentView.addView(r03, this.packsIndexStart + r6);
        goto L49
    L54:
        r02.setDrawable(getResources().getDrawable(r22.resId).mutate());
        r02.updateColor();
        r02.setLock(r13, false);
        goto L49
    L55:
        boolean r4 = r22.free;
        Long r3 = r22.thumbDocumentId;
        if (r3 == null) goto L60;
        if (r02 != null) goto L59;
        EmojiView.EmojiPack r132 = r22;
        r11 = r6;
        r02 = new EmojiTabButton(this, getContext(), r22.thumbDocumentId.longValue(), r4, false, false);
        r4 = r4;
        onTabCreate(r02);
        this.contentView.addView(r02, this.packsIndexStart + r11);
    L67:
        if (r132.forGroup == false) goto L69;
        Long r24 = 439488310L;
    L70:
        r02.id = r24;
        if (this.selected != r11) goto L73;
        boolean r25 = true;
    L74:
        r02.updateSelect(r25, false);
        int r26 = this.currentType;
        if (r26 != 4) goto L83;
        if (r14 == true) goto L79;
        if (r4 == true) goto L79;
        Boolean r27 = Boolean.TRUE;
    L80:
        r02.setLock(r27, false);
    L81:
        r23 = null;
    L79:
        r27 = null;
        goto L80
    L83:
        if (r26 == 6) goto L87;
        if (r26 == 5) goto L87;
        if (r26 == 7) goto L87;
        if (r14 == true) goto L92;
        if (r4 == true) goto L92;
        r02.setLock(Boolean.TRUE, false);
    L92:
        if (isInstalled(r132) == true) goto L94;
        r02.setLock(Boolean.FALSE, false);
        goto L81
    L94:
        r23 = null;
        r02.setLock(null, false);
    L87:
        r23 = null;
        r02.setLock(null, false);
        goto L96
    L73:
        r25 = false;
        goto L74
    L69:
        r24 = null;
        goto L70
    L59:
        r132 = r22;
        r11 = r6;
        r02.setAnimatedEmojiDocumentId(r3.longValue());
        goto L67
    L60:
        r132 = r22;
        r11 = r6;
        TLRPC.Document r32 = getThumbDocument(r132.set, r132.documents);
        if (r02 != null) goto L63;
        r02 = new EmojiTabButton(this, getContext(), r32, r4, false, false);
        onTabCreate(r02);
        this.contentView.addView(r02, this.packsIndexStart + r11);
    L64:
        if (r32 != null) goto L67;
        r02.setStickerThumb(r132);
        goto L67
    L63:
        r02.setAnimatedEmojiDocument(r32);
        goto L64
    L45:
        r22 = r13;
        goto L46
    L41:
        r02 = r13;
        goto L43
    L97:
        EmojiTabButton r04 = this.settingsTab;
        if (r04 == null) goto L106;
        r04.bringToFront();
        if (this.settingsTab.getAlpha() >= 1.0f) goto L106;
        ViewPropertyAnimator r05 = this.settingsTab.animate().alpha(1.0f);
        if (HwEmojis.isHwEnabledOrPreparing() == false) goto L104;
        long r28 = 0;
    L105:
        r05.setDuration(r28).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        goto L106
    L104:
        r28 = 200;
    L106:
        int r06 = 0;
    L108:
        if (r06 >= r15.size()) goto L110;
        ((EmojiTabButton) r15.get(r06)).keepAttached = false;
        EmojiTabButton.m10278$$Nest$mupdateAttachState((EmojiTabButton) r15.get(r06));
        r06 = r06 + 1;
        goto L108
    L110:
        updateClickListeners();
        return;
    L32:
        if (allowEmojisForNonPremium() == true) goto L35;
        r14 = false;
        goto L36
    L27:
        if (this.appearCount == r19.size()) goto L29;
        this.appearAnimation.cancel();
        this.appearAnimation = null;
        goto L29
    L20:
        if (r19.size() <= 0) goto L24;
        if (this.appearCount == r19.size()) goto L24;
        boolean r07 = this.wasDrawn;
        goto L24
    L16:
        r2 = 0;
        goto L17
    L8:
        if (MediaDataController.getInstance(UserConfig.selectedAccount).areStickersLoaded(5) == true) goto L10;
    }
}
