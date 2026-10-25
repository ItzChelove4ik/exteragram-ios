package org.telegram.ui.iv;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.os.Build;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableRenderNode;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.iv.RichCaptionController;
import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public class RichMediaCell extends RichBlockCell implements Theme.Colorable, TextSelectionHelper.ArticleSelectableView, RichCaptionHost {
    private static Paint slideDotPaint;
    private final ImageView addButton;
    private boolean attached;
    private final Paint backgroundPaint;
    private BlurredBackgroundColorProvider blurColors;
    private BlurredBackgroundSourceRenderNode blurSource;
    private final RichCaptionController caption;
    private final HashMap<ImageView, BlurredBackgroundDrawableRenderNode> circleButtonBg;
    private final ArrayList<ImageView> circleButtons;
    private final Path clipPath;
    private int collageH;
    private final ArrayList<RectF> collageRects;
    private int currentPage;
    private Delegate delegate;
    private float downX;
    private float downY;
    private boolean dragging;
    private final boolean glass;
    private int imageH;
    private int imageW;
    private final ArrayList<RectF> itemRects;
    private final ArrayList<RichMediaItem> items;
    private int lastSwitchIconRes;
    private int maxFlingVelocity;
    private final ArrayList<ImageView> menuButtons;
    private int minFlingVelocity;
    private final AnimatedFloat modeProgress;
    private float pageOffset;
    private int pressedItem;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Paint selectionPaint;
    private ValueAnimator settleAnimator;
    private int slideH;
    private int slideW;
    private SpoilerEffect2 spoilerEffect;
    private final ImageView switchModeButton;
    private int touchSlop;
    private VelocityTracker velocityTracker;

    /* renamed from: org.telegram.ui.iv.RichMediaCell$1, reason: invalid class name */
    public class AnonymousClass1 implements RichCaptionController.Host {
        final /* synthetic */ RichMediaCell this$0;

        public AnonymousClass1(RichMediaCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public TextSelectionHelper.ArticleSelectableView cell() {
            return this.this$0;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public BlockRow currentRow() {
            return this.this$0.currentRow;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionChanged() {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).onCaptionChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionEnter() {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).onCaptionEnter(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionLockedInsert(CharSequence r2) {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).onCaptionLockedInsert(r2);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public boolean onCaptionSelectAll() {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return false;
        L5:
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).onCaptionSelectAll(this.this$0.currentRow) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionSpansChanged() {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).onCaptionSpansChanged(this.this$0.currentRow);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onCaptionWillChange(int r2, int r3) {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).onCaptionWillChange(this.this$0.currentRow, r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public void onRequestWindowFocusable(RichEditText r2, boolean r3) {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) == null) goto L6;
            RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).onRequestWindowFocusable(r2, r3);
            return;
        }

        @Override // org.telegram.ui.iv.RichCaptionController.Host
        public TextSelectionHelper.ArticleTextSelectionHelper selectionHelper() {
            if (RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0) != null) goto L5;
            return null;
        L5:
            return RichMediaCell.m21886$$Nest$fgetdelegate(this.this$0).getSelectionHelper();
        }
    }

    /* renamed from: org.telegram.ui.iv.RichMediaCell$2, reason: invalid class name */
    public class AnonymousClass2 implements BlurredBackgroundColorProvider {
        final /* synthetic */ RichMediaCell this$0;

        public AnonymousClass2(RichMediaCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
        public int getBackgroundColor() {
            return 1711276032;
        }

        @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
        public int getShadowColor() {
            return 0;
        }

        @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
        public int getStrokeColorBottom() {
            return 352321535;
        }

        @Override // org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider
        public int getStrokeColorTop() {
            return 872415231;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichMediaCell$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ RichMediaCell this$0;
        final /* synthetic */ int val$target;

        public AnonymousClass3(RichMediaCell r1, int r2) {
            this.this$0 = r1;
            this.val$target = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            RichMediaCell.m21887$$Nest$fputcurrentPage(this.this$0, this.val$target);
            RichMediaCell.m21888$$Nest$fputpageOffset(this.this$0, 0.0f);
            this.this$0.requestLayout();
            this.this$0.invalidate();
        }
    }

    public interface Delegate {
        TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper();

        ItemOptions makeMenu(View r1);

        void onAddMedia(BlockRow r1);

        void onCancelUpload(BlockRow r1, MediaUploadState r2);

        void onCaptionChanged(BlockRow r1);

        void onCaptionEnter(BlockRow r1);

        void onCaptionLockedInsert(CharSequence r1);

        boolean onCaptionSelectAll(BlockRow r1);

        void onCaptionSpansChanged(BlockRow r1);

        void onCaptionWillChange(BlockRow r1, int r2, int r3);

        void onDeleteMedia(BlockRow r1, MediaUploadState r2);

        void onMediaPick(BlockRow r1);

        void onRequestWindowFocusable(RichEditText r1, boolean r2);

        void onSwitchMode(BlockRow r1);

        void onToggleSpoiler(BlockRow r1, MediaUploadState r2);
    }

    public static final class Factory extends UItem.UItemFactory<RichMediaCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem of(BlockRow r3, Delegate r4) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.id = (int) r3.id;
            r0.object = r3;
            r0.object2 = r4;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            ((RichMediaCell) r1).bind((BlockRow) r2.object, (Delegate) r2.object2);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r1, UItem r2) {
            if (r1.id != r2.id) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public RichMediaCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            RichMediaCell r0 = new RichMediaCell(r1, r5);
            r0.setBackground(new RichEditor.DraggingDrawable(Theme.getColor(Theme.key_windowBackgroundWhite, r5)));
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            if (r1.id != r2.id) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean isClickable() {
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$0NnqA8XAKBApoC4kVQhoN5aQZ_E(RichMediaCell r0, ValueAnimator r1) {
        r0.lambda$settle$5(r1);
    }

    /* renamed from: $r8$lambda$9RJco-V4BYjv5BmAeR-YmETPQjY, reason: not valid java name */
    public static /* synthetic */ void m21882$r8$lambda$9RJcoV4BYjv5BmAeRYmETPQjY(RichMediaCell r0, MediaUploadState r1) {
        r0.lambda$onMenuClicked$3(r1);
    }

    /* renamed from: $r8$lambda$BuKlMjl-Wp0m85cCMaEWfYBvdrE, reason: not valid java name */
    public static /* synthetic */ void m21883$r8$lambda$BuKlMjlWp0m85cCMaEWfYBvdrE(RichMediaCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$F-MujQgOEAAnVjzxV5PjPsbk9v0, reason: not valid java name */
    public static /* synthetic */ void m21884$r8$lambda$FMujQgOEAAnVjzxV5PjPsbk9v0(RichMediaCell r0, View r1) {
        r0.lambda$rebuildItems$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$whirKtH6l9fXjn7vC5CRAPp4yQ4(RichMediaCell r0, MediaUploadState r1) {
        r0.lambda$onMenuClicked$4(r1);
    }

    /* renamed from: $r8$lambda$wwDG2FZwH7dQkX-Nu1uEjQ8KUz8, reason: not valid java name */
    public static /* synthetic */ void m21885$r8$lambda$wwDG2FZwH7dQkXNu1uEjQ8KUz8(RichMediaCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Delegate m21886$$Nest$fgetdelegate(RichMediaCell r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fputcurrentPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21887$$Nest$fputcurrentPage(RichMediaCell r0, int r1) {
        r0.currentPage = r1;
    }

    /* renamed from: -$$Nest$fputpageOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21888$$Nest$fputpageOffset(RichMediaCell r0, float r1) {
        r0.pageOffset = r1;
    }

    public RichMediaCell(Context r13, Theme.ResourcesProvider r14) {
        super(r13);
        this.backgroundPaint = new Paint(1);
        this.selectionPaint = new Paint(1);
        this.items = new ArrayList();
        this.menuButtons = new ArrayList();
        this.collageRects = new ArrayList();
        this.itemRects = new ArrayList();
        int r8 = Build.VERSION.SDK_INT;
        if (r8 >= 31) goto L5;
    L7:
        boolean r11 = false;
    L8:
        this.glass = r11;
        this.circleButtons = new ArrayList();
        this.circleButtonBg = new HashMap();
        this.pressedItem = -1;
        this.modeProgress = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.clipPath = new Path();
        this.resourcesProvider = r14;
        setWillNotDraw(false);
        setBlockPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(4.0f));
        RichCaptionController r0 = new RichCaptionController(r13, r14, new AnonymousClass1(this));
        this.caption = r0;
        addView(r0.editText, LayoutHelper.createFrame(-2, -2, 51));
        setClipChildren(false);
        setClipToPadding(false);
        if (r11 == false) goto L12;
        if (r8 < 31) goto L12;
        BlurredBackgroundSourceRenderNode r02 = new BlurredBackgroundSourceRenderNode(new BlurredBackgroundSourceColor());
        this.blurSource = r02;
        r02.setBlur(AndroidUtilities.dp(24.0f));
        this.blurColors = new AnonymousClass2(this);
    L12:
        ImageView r03 = createCircleButton();
        this.addButton = r03;
        r03.setImageResource(R.drawable.iv_media_add);
        addView(r03, LayoutHelper.createFrame(32, 32.0f, 53, 12.0f, 12.0f, 12.0f, 12.0f));
        r03.setOnClickListener(new RichMediaCell$$ExternalSyntheticLambda2(this));
        ImageView r04 = createCircleButton();
        this.switchModeButton = r04;
        r04.setVisibility(8);
        addView(r04, LayoutHelper.createFrame(32, 32.0f, 53, 12.0f, 12.0f, 66.0f, 12.0f));
        r04.setOnClickListener(new RichMediaCell$$ExternalSyntheticLambda3(this));
        updateColors();
        return;
    L5:
        if (SharedConfig.chatBlurEnabled() == false) goto L7;
        r11 = true;
        goto L8
    }

    private static float aspectRatio(RichMediaItem r1) {
        int r0 = r1.getWidth();
        int r12 = r1.getHeight();
        if (r0 <= 0) goto L7;
        if (r12 > 0) goto L6;
        return 1.0f;
    L6:
        return r0 / r12;
    L7:
        return 1.0f;
    }

    private void buildItemRects(float r13) {
        this.itemRects.clear();
        int r0 = this.items.size();
        if (r0 == 0) goto L14;
        int r1 = getPaddingTop();
        int r2 = 0;
        if (r0 != 1) goto L9;
        this.itemRects.add(new RectF(this.collageRects.get(0)));
        return;
    L9:
        float r3 = (-this.pageOffset) * this.slideW;
    L10:
        if (r2 >= r0) goto L17;
        if (r2 >= this.collageRects.size()) goto L18;
        RectF r4 = this.collageRects.get(r2);
        float r5 = ((r2 - this.currentPage) * this.slideW) + r3;
        this.itemRects.add(new RectF(AndroidUtilities.lerp(r4.left, r5, r13), AndroidUtilities.lerp(r4.top, r1, r13), AndroidUtilities.lerp(r4.right, r5 + this.slideW, r13), AndroidUtilities.lerp(r4.bottom, this.slideH + r1, r13)));
        r2 = r2 + 1;
        goto L10
    L18:
        return;
    L17:
        return;
    }

    private int captionMargin() {
        if (RichBlockChrome.insetFor(this.currentRow) > 0) goto L5;
        return 0;
    L5:
        return nestedContentMargin();
    }

    private void computeGeometry(int r21) {
        this.collageRects.clear();
        int r2 = getPaddingTop();
        int r3 = this.items.size();
        if (r3 != 0) goto L6;
        int r22 = (AndroidUtilities.dp(200.0f) - getPaddingTop()) - getPaddingBottom();
        this.slideH = r22;
        this.collageH = r22;
        this.slideW = r21;
        return;
    L6:
        float r5 = 0.55f;
        int r6 = 0;
        int r7 = 1;
        if (r3 != 1) goto L18;
        RichMediaItem r32 = this.items.get(0);
        int r62 = r32.getWidth();
        int r33 = r32.getHeight();
        if (r62 <= 0) goto L14;
        if (r33 <= 0) goto L14;
        int r4 = (int) ((r21 / Math.max(1, r62)) * r33);
        Point r8 = AndroidUtilities.displaySize;
        int r52 = (int) (Math.max(r8.x, r8.y) * 0.55f);
        if (r4 <= r52) goto L15;
        int r34 = (int) ((r52 / Math.max(1, r33)) * r62);
        r4 = r52;
    L16:
        this.collageRects.add(new RectF((r21 - r34) / 2, r2, r1 + r34, r2 + r4));
        this.collageH = r4;
        this.slideW = r34;
        this.slideH = r4;
        return;
    L15:
        r34 = r21;
    L14:
        r4 = (AndroidUtilities.dp(200.0f) - getPaddingTop()) - getPaddingBottom();
        goto L15
    L18:
        float[] r42 = new float[r3];
        int r82 = 0;
    L19:
        if (r82 >= r3) goto L21;
        r42[r82] = aspectRatio(this.items.get(r82));
        r82 = r82 + 1;
        goto L19
    L21:
        MessageObject.GroupedMessagePosition[] r43 = RichMessageLayout.computeGrouped(r42);
        int r83 = r43.length;
        int r9 = 0;
        int r10 = 0;
    L22:
        if (r9 >= r83) goto L24;
        r10 = Math.max(r10, r43[r9].maxY);
        r9 = r9 + 1;
        goto L22
    L24:
        int r84 = r10 + 1;
        float[] r92 = new float[r84];
        int r11 = r43.length;
        int r12 = 0;
    L25:
        if (r12 >= r11) goto L30;
        MessageObject.GroupedMessagePosition r13 = r43[r12];
        byte r14 = r13.minY;
        if (r14 != r13.maxY) goto L29;
        r92[r14] = Math.max(r92[r14], r13.ph);
    L29:
        r12 = r12 + 1;
        goto L25
    L30:
        int r112 = r43.length;
        int r122 = 0;
    L31:
        if (r122 >= r112) goto L48;
        MessageObject.GroupedMessagePosition r132 = r43[r122];
        int r142 = r132.minY;
        byte r15 = r132.maxY;
        if (r142 == r15) goto L46;
        int r152 = (r15 - r142) + r7;
        float r16 = r5;
        float[] r53 = r132.siblingHeights;
        if (r53 != null) goto L37;
    L42:
        int r18 = r7;
        float r54 = r132.ph / r152;
    L44:
        if (r142 > r132.maxY) goto L47;
        r92[r142] = Math.max(r92[r142], r54);
        r142 = r142 + 1;
    L47:
        r122 = r122 + 1;
        r5 = r16;
        r7 = r18;
        goto L31
    L37:
        if (r53.length != r152) goto L42;
        int r55 = 0;
    L39:
        if (r55 >= r152) goto L41;
        byte r143 = r132.minY;
        r92[r143 + r55] = Math.max(r92[r143 + r55], r132.siblingHeights[r55]);
        r55 = r55 + 1;
        r7 = r7;
    L41:
        r18 = r7;
        goto L47
    L46:
        r16 = r5;
        goto L41
    L48:
        float r162 = r5;
        Point r56 = AndroidUtilities.displaySize;
        float r57 = Math.max(r56.x, r56.y) * 0.5f;
        int[] r113 = new int[r10 + 2];
        int r133 = 0;
        float r144 = 0.0f;
    L49:
        if (r133 > r10) goto L51;
        r113[r133] = Math.round(r144 * r57);
        r144 = r144 + r92[r133];
        r133 = r133 + 1;
        goto L49
    L51:
        r113[r84] = Math.round(r144 * r57);
        int r58 = AndroidUtilities.dp(2.0f);
        int r93 = 0;
    L53:
        if (r93 >= r43.length) goto L79;
        MessageObject.GroupedMessagePosition r102 = r43[r93];
        int r134 = r113[r102.minY];
        int r145 = r113[r102.maxY + 1] - r134;
        if (r102.leftSpanOffset <= 0) goto L57;
        int r153 = Math.round((r15 * r21) / 1000.0f);
    L72:
        if ((r102.flags & 2) == 0) goto L74;
        int r63 = r21 - r153;
    L76:
        if ((r102.flags & 8) != 0) goto L78;
        r145 = r145 - r58;
    L78:
        this.collageRects.add(new RectF(r153, r134 + r2, r153 + Math.max(0, r63), r13 + Math.max(0, r145)));
        r93 = r93 + 1;
        r6 = 0;
        r2 = r2;
        r43 = r43;
        goto L53
    L74:
        r63 = Math.round((r102.pw * r21) / 1000.0f) - r58;
        goto L76
    L57:
        int r154 = r6;
        int r19 = r154;
    L59:
        if (r154 >= r43.length) goto L70;
        if (r154 == r93) goto L69;
        MessageObject.GroupedMessagePosition r123 = r43[r154];
        byte r72 = r123.minY;
        byte r64 = r102.minY;
        if (r72 > r64) goto L69;
        if (r123.maxY < r64) goto L69;
        if (r123.minX >= r102.minX) goto L69;
        r19 = r19 + r123.pw;
    L69:
        r154 = r154 + 1;
        goto L59
    L70:
        r153 = Math.round((r19 * r21) / 1000.0f);
        goto L72
    L79:
        this.collageH = r113[r84];
        this.slideW = r21;
        float r124 = 0.0f;
    L80:
        if (r6 >= r3) goto L82;
        r124 = r124 + aspectRatio(this.items.get(r6));
        r6 = r6 + 1;
        goto L80
    L82:
        int r1 = (int) (this.slideW / Math.max(0.5f, r124 / r3));
        Point r23 = AndroidUtilities.displaySize;
        int r24 = (int) (Math.max(r23.x, r23.y) * r162);
        if (r1 <= r24) goto L85;
        r1 = r24;
    L85:
        this.slideH = r1;
    }

    private ImageView createCircleButton() {
        ImageView r0 = new ImageView(getContext());
        r0.setScaleType(ImageView.ScaleType.CENTER);
        if (this.glass == true) goto L5;
    L7:
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        int r1 = Theme.key_windowBackgroundWhite;
        r0.setBackground(RichEditor.withShadow(Theme.createRadSelectorDrawable(Theme.getColor(r1, this.resourcesProvider), Theme.blendOver(Theme.getColor(r1, this.resourcesProvider), Theme.getColor(Theme.key_listSelector, this.resourcesProvider)), 20, 20)));
    L8:
        ScaleStateListAnimator.apply(r0);
        this.circleButtons.add(r0);
        return r0;
    L5:
        if (Build.VERSION.SDK_INT < 31) goto L7;
        r0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        BlurredBackgroundDrawableRenderNode r12 = (BlurredBackgroundDrawableRenderNode) this.blurSource.createDrawable();
        r12.setColorProvider(this.blurColors);
        r12.setRadius(AndroidUtilities.dp(16.0f));
        this.circleButtonBg.put(r0, r12);
        goto L8
    }

    private void drawDots(Canvas r21, float r22) {
        if (slideDotPaint != null) goto L5;
        Paint r2 = new Paint(1);
        slideDotPaint = r2;
        r2.setColor(-1);
        slideDotPaint.setShadowLayer(AndroidUtilities.dpf2(3.0f), 0.0f, AndroidUtilities.dpf2(1.0f), Integer.MIN_VALUE);
    L5:
        int r23 = this.items.size();
        float r6 = ((getPaddingTop() + this.imageH) - AndroidUtilities.dp(23.0f)) + AndroidUtilities.dp(5.0f);
        int r8 = ((AndroidUtilities.dp(7.0f) * r23) + ((r23 - 1) * AndroidUtilities.dp(6.0f))) + AndroidUtilities.dp(4.0f);
        int r10 = getPaddingLeft();
        int r12 = 0;
        int r11 = Math.max(0, (getWidth() - r10) - getPaddingRight());
        float r13 = this.currentPage + this.pageOffset;
        if (r8 >= r11) goto L8;
        float r5 = r10 + ((r11 - r8) / 2.0f);
        float r16 = 1.0f;
        float r19 = 23.0f;
    L9:
        r21.save();
        r21.clipRect(r10, (getPaddingTop() + this.imageH) - AndroidUtilities.dp(r19), r11 + r10, getPaddingTop() + this.imageH);
    L10:
        if (r12 >= r23) goto L12;
        float r0 = Math.max(0.0f, r16 - Math.abs(r12 - r13));
        slideDotPaint.setAlpha((int) (((r0 * 95.0f) + 160.0f) * r22));
        r21.drawCircle((AndroidUtilities.dp(4.0f) + r5) + (AndroidUtilities.dp(13.0f) * r12), r6, AndroidUtilities.dp(2.0f) + (AndroidUtilities.dp(r16) * r0), slideDotPaint);
        r12 = r12 + 1;
        goto L10
    L12:
        r21.restore();
        return;
    L8:
        float r82 = AndroidUtilities.dp(4.0f) + r10;
        r16 = 1.0f;
        int r3 = AndroidUtilities.dp(13.0f);
        int r52 = ((r11 - AndroidUtilities.dp(8.0f)) / 2) / r3;
        r19 = 23.0f;
        r5 = r82 - (Utilities.clamp(r13 - r52, Math.max(0, (r23 - (r52 * 2)) - 1), 0.0f) * r3);
        goto L9
    }

    private void drawGlassButton(Canvas r5, ImageView r6) {
        if (r6.getVisibility() != 0) goto L10;
        BlurredBackgroundDrawableRenderNode r4 = this.circleButtonBg.get(r6);
        if (r4 != null) goto L8;
        return;
    L8:
        r4.setBounds(r6.getLeft(), r6.getTop(), r6.getRight(), r6.getBottom());
        r4.setAlpha((int) (r6.getAlpha() * 255.0f));
        r4.invalidateDisplayList();
        r4.draw(r5);
        return;
    }

    private void drawGlassButtons(Canvas r4) {
        if (this.glass == true) goto L5;
        return;
    L5:
        if (this.blurSource != null) goto L7;
        return;
    L7:
        if (Build.VERSION.SDK_INT < 31) goto L44;
        int r0 = getWidth();
        int r1 = getHeight();
        if (r0 <= 0) goto L43;
        if (r1 > 0) goto L14;
        return;
    L14:
        if (r4.isHardwareAccelerated() == true) goto L16;
    L23:
        int r02 = 0;
    L25:
        if (r02 >= this.circleButtons.size()) goto L33;
        ImageView r12 = this.circleButtons.get(r02);
        if (r12 == this.addButton) goto L32;
        if (r12 == this.switchModeButton) goto L32;
        drawGlassButton(r4, r12);
    L32:
        r02 = r02 + 1;
        goto L25
    L33:
        drawGlassButton(r4, this.switchModeButton);
        drawGlassButton(r4, this.addButton);
        return;
    L16:
        if (this.blurSource.inRecording() == true) goto L23;
        drawMedia(this.blurSource.beginRecording(r0, r1));     // Catch: Throwable -> L20
        this.blurSource.endRecording();
    L20:
        th = move-exception;
        this.blurSource.endRecording();
        throw th;
    L43:
        return;
    }

    private void drawMedia(Canvas r15) {
        int r0 = getPaddingTop();
        int r1 = getPaddingLeft();
        int r2 = Math.max(0, (getWidth() - r1) - getPaddingRight());
        r15.save();
        r15.translate(r1, 0.0f);
        if (RichBlockChrome.quoteDepth(this.currentRow) <= 0) goto L5;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(0.0f, r0, r2, this.imageH + r0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r15.clipPath(this.clipPath);
    L7:
        if (isSlideshow() == true) goto L9;
    L11:
        boolean r12 = false;
    L13:
        if (RichBlockChrome.quoteDepth(this.currentRow) <= 0) goto L15;
        int r5 = AndroidUtilities.dp(8.0f);
    L16:
        if (r12 == false) goto L26;
        int r7 = this.currentPage;
        if (r7 != 0) goto L22;
        if (this.pageOffset >= 0.0f) goto L22;
    L25:
        Canvas r8 = r15;
        r8.drawRect(0.0f, r0, r2, this.imageH + r0, this.backgroundPaint);
    L28:
        if (this.items.size() == 1) goto L30;
    L36:
        int r152 = 0;
    L38:
        if (r152 >= this.items.size()) goto L70;
        if (r152 >= this.itemRects.size()) goto L70;
        RichMediaItem r02 = this.items.get(r152);
        RectF r22 = this.itemRects.get(r152);
        if (r12 == false) goto L58;
        if (r152 != 0) goto L45;
        int r4 = r5;
    L47:
        if (r152 != (this.items.size() - 1)) goto L49;
        int r72 = r5;
    L51:
        if (r152 != (this.items.size() - 1)) goto L53;
        int r9 = r5;
    L54:
        if (r152 != 0) goto L56;
        int r10 = r5;
    L57:
        r02.setRoundRadius(r4, r72, r9, r10);
    L60:
        if (r02.hasImage() == true) goto L62;
        r8.drawRect(r22, this.backgroundPaint);
    L62:
        r02.draw(r8, r22);
        MediaUploadState r42 = r02.getMedia();
        if (r42 == null) goto L69;
        if (r42.hasSpoiler == false) goto L69;
        if (r02.hasImage() == false) goto L69;
        r02.drawSpoiler(r8, r22, getSpoilerEffect(), this);
    L69:
        r152 = r152 + 1;
        goto L38
    L56:
        r10 = 0;
        goto L57
    L53:
        r9 = 0;
        goto L54
    L49:
        r72 = 0;
        goto L51
    L45:
        r4 = 0;
        goto L47
    L58:
        r02.setRoundRadius(0, 0, 0, 0);
    L70:
        r8.restore();
        return;
    L30:
        if (this.itemRects.size() != 1) goto L36;
        RectF r153 = this.itemRects.get(0);
        if (r153.left <= 0.5f) goto L34;
    L35:
        RectF r154 = AndroidUtilities.rectTmp;
        r154.set(0.0f, r0, r2, r0 + this.imageH);
        this.items.get(0).drawBlurBackground(r8, r154);
        goto L36
    L34:
        if (r153.right >= (r2 - 0.5f)) goto L36;
    L22:
        if (r7 != (this.items.size() - 1)) goto L26;
        if (this.pageOffset > 0.0f) goto L25;
    L26:
        r8 = r15;
        goto L28
    L15:
        r5 = 0;
        goto L16
    L9:
        if (this.items.size() < 2) goto L11;
        r12 = true;
        goto L13
    L5:
        r15.clipRect(0, r0, r2, this.imageH + r0);
        goto L7
    }

    private SpoilerEffect2 getSpoilerEffect() {
        if (this.attached == true) goto L5;
    L17:
        return null;
    L5:
        if (SpoilerEffect2.supports() == false) goto L17;
        SpoilerEffect2 r0 = this.spoilerEffect;
        if (r0 == null) goto L13;
        if (r0.destroyed == false) goto L13;
        this.spoilerEffect = null;
    L13:
        if (this.spoilerEffect != null) goto L16;
        this.spoilerEffect = SpoilerEffect2.getInstance(this);
    L16:
        return this.spoilerEffect;
    }

    private void handleTap(int r4) {
        if (this.delegate != null) goto L5;
        return;
    L5:
        if (this.currentRow == null) goto L27;
        List<MediaUploadState> r0 = medias();
        if (r0.isEmpty() == false) goto L10;
    L21:
        this.delegate.onMediaPick(this.currentRow);
        return;
    L10:
        if (r0.size() == 1) goto L12;
    L14:
        if (r4 >= 0) goto L16;
        return;
    L16:
        if (r4 < r0.size()) goto L18;
        return;
    L18:
        if (r0.get(r4).isPending() == false) goto L26;
        this.delegate.onCancelUpload(this.currentRow, r0.get(r4));
        return;
    L26:
        return;
    L12:
        if (r0.get(0).state != 0) goto L14;
    }

    private boolean hasAnySpoiler() {
        int r1 = 0;
    L4:
        if (r1 >= this.items.size()) goto L12;
        MediaUploadState r2 = this.items.get(r1).getMedia();
        if (r2 == null) goto L11;
        if (r2.hasSpoiler == false) goto L11;
        return true;
    L11:
        r1 = r1 + 1;
        goto L4
    L12:
        return false;
    }

    private boolean isCellSelected() {
        Delegate r0 = this.delegate;
        if (r0 != null) goto L5;
        return false;
    L5:
        TextSelectionHelper.ArticleTextSelectionHelper r02 = r0.getSelectionHelper();
        if (r02 != null) goto L8;
    L22:
        return false;
    L8:
        if (r02.isInSelectionMode() == false) goto L22;
        if ((getParent() instanceof RecyclerView) == true) goto L13;
        return false;
    L13:
        int r3 = ((RecyclerView) getParent()).getChildAdapterPosition(this);
        if (r3 >= 0) goto L17;
        return false;
    L17:
        if (r3 <= r02.getStartCell()) goto L22;
        if (r3 > r02.getEndCell()) goto L22;
        return true;
    }

    private boolean isGalleryRow() {
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L10;
        TL_iv.PageBlock r12 = r1.block;
        if ((r12 instanceof TL_iv.pageBlockCollage) == false) goto L7;
        return true;
    L7:
        if ((r12 instanceof TL_iv.pageBlockSlideshow) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private boolean isSlideshow() {
        BlockRow r0 = this.currentRow;
        if (r0 != null) goto L5;
        return false;
    L5:
        if ((r0.block instanceof TL_iv.pageBlockSlideshow) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private int itemIndexAt(float r3, float r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.itemRects.size()) goto L9;
        if (this.itemRects.get(r0).contains(r3, r4) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        Delegate r12 = this.delegate;
        if (r12 == null) goto L8;
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L9;
        r12.onAddMedia(r0);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        Delegate r12 = this.delegate;
        if (r12 == null) goto L8;
        BlockRow r0 = this.currentRow;
        if (r0 == null) goto L9;
        r12.onSwitchMode(r0);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$onMenuClicked$3(MediaUploadState r2) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L8;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L9;
        r0.onToggleSpoiler(r1, r2);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$onMenuClicked$4(MediaUploadState r2) {
        Delegate r0 = this.delegate;
        if (r0 == null) goto L8;
        BlockRow r1 = this.currentRow;
        if (r1 == null) goto L9;
        r0.onDeleteMedia(r1, r2);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$rebuildItems$2(View r2) {
        onMenuClicked(this.menuButtons.indexOf(r2));
    }

    private /* synthetic */ void lambda$settle$5(ValueAnimator r1) {
        this.pageOffset = ((Float) r1.getAnimatedValue()).floatValue();
        requestLayout();
        invalidate();
    }

    private List<MediaUploadState> medias() {
        if (this.currentRow == null) goto L5;
        boolean r0 = isGalleryRow();
        BlockRow r1 = this.currentRow;
        if (r0 == false) goto L13;
        ArrayList<MediaUploadState> r12 = r1.medias;
        if (r12 == null) goto L12;
        return r12;
    L12:
        return Collections.EMPTY_LIST;
    L13:
        MediaUploadState r13 = r1.media;
        if (r13 == null) goto L18;
        return Collections.singletonList(r13);
    L18:
        return Collections.EMPTY_LIST;
    L5:
        return Collections.EMPTY_LIST;
    }

    private void onMenuClicked(int r5) {
        if (this.delegate != null) goto L5;
        return;
    L5:
        if (this.currentRow == null) goto L32;
        List<MediaUploadState> r0 = medias();
        if (r5 >= 0) goto L10;
        return;
    L10:
        if (r5 < r0.size()) goto L12;
        return;
    L12:
        if (r5 >= this.menuButtons.size()) goto L33;
        MediaUploadState r02 = r0.get(r5);
        ItemOptions r52 = this.delegate.makeMenu(this.menuButtons.get(r5));
        if (r52 == null) goto L34;
        boolean r1 = r02.hasSpoiler;
        if (r1 == false) goto L20;
        int r2 = R.drawable.msg_spoiler_off;
    L21:
        if (r1 == false) goto L23;
        int r12 = R.string.DisablePhotoSpoiler;
    L24:
        r52.add(r2, LocaleController.getString(r12), new RichMediaCell$$ExternalSyntheticLambda4(this, r02));
        r52.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new RichMediaCell$$ExternalSyntheticLambda5(this, r02));
        r52.translate(0.0f, -AndroidUtilities.dp(38.0f));
        if (this.glass == false) goto L27;
        r52.setBlur(false, true);
        r52.setDimAlpha(0);
    L27:
        r52.show();
        return;
    L23:
        r12 = R.string.EnablePhotoSpoiler;
        goto L24
    L20:
        r2 = R.drawable.msg_spoiler;
        goto L21
    L34:
        return;
    L33:
        return;
    }

    private void rebuildItems() {
        List<MediaUploadState> r0 = medias();
    L4:
        if (this.items.size() >= r0.size()) goto L10;
        RichMediaItem r1 = new RichMediaItem(this, this.resourcesProvider);
        if (this.attached == false) goto L8;
        r1.attach();
    L8:
        this.items.add(r1);
    L10:
        if (this.items.size() <= r0.size()) goto L12;
        this.items.remove(r1.size() - 1).detach();
        goto L10
    L12:
        int r12 = 0;
    L14:
        if (r12 >= r0.size()) goto L17;
        this.items.get(r12).setMedia(r0.get(r12));
        r12 = r12 + 1;
    L17:
        if (this.menuButtons.size() >= r0.size()) goto L19;
        ImageView r13 = createCircleButton();
        r13.setImageResource(R.drawable.iv_media_dots);
        r13.setOnClickListener(new RichMediaCell$$ExternalSyntheticLambda0(this));
        addView(r13, LayoutHelper.createFrame(32, 32, 51));
        this.menuButtons.add(r13);
        goto L17
    L19:
        this.addButton.bringToFront();
        this.switchModeButton.bringToFront();
    L21:
        if (this.menuButtons.size() <= r0.size()) goto L24;
        ImageView r14 = this.menuButtons.remove(r1.size() - 1);
        removeView(r14);
        this.circleButtons.remove(r14);
        this.circleButtonBg.remove(r14);
        goto L21
    L24:
        if (this.spoilerEffect != null) goto L26;
        return;
    L26:
        if (hasAnySpoiler() == true) goto L37;
        this.spoilerEffect.detach(this);
        this.spoilerEffect = null;
        return;
    }

    private void settle(float r7) {
        int r0 = this.items.size();
        if (r7 < 0.0f) goto L5;
    L7:
        int r1 = -1;
        if (r7 > 0.0f) goto L10;
    L12:
        float r72 = this.pageOffset;
        if (r72 <= 0.5f) goto L18;
        if (this.currentPage >= (r0 - 1)) goto L18;
    L6:
        r1 = 1;
    L23:
        int r12 = r1 + this.currentPage;
        ValueAnimator r73 = ValueAnimator.ofFloat(new float[]{this.pageOffset, r12 - r7});
        this.settleAnimator = r73;
        r73.setDuration(220);
        this.settleAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.settleAnimator.addUpdateListener(new RichMediaCell$$ExternalSyntheticLambda1(this));
        this.settleAnimator.addListener(new AnonymousClass3(this, r12));
        this.settleAnimator.start();
        return;
    L18:
        if (r72 < (-0.5f)) goto L20;
    L22:
        r1 = 0;
        goto L23
    L20:
        if (this.currentPage <= 0) goto L22;
    L10:
        if (this.currentPage <= 0) goto L12;
    L5:
        if (this.currentPage >= (r0 - 1)) goto L7;
        goto L6
    }

    private void updateSwitchButton(boolean r4) {
        int r2 = 0;
        if (this.items.size() < 2) goto L5;
        boolean r0 = true;
    L6:
        ImageView r1 = this.switchModeButton;
        if (r0 == true) goto L10;
        r2 = 8;
    L10:
        r1.setVisibility(r2);
        if (r0 == true) goto L14;
        return;
    L14:
        if (isSlideshow() == false) goto L16;
        int r02 = R.drawable.iv_media_slideshow;
    L18:
        if (r02 != this.lastSwitchIconRes) goto L20;
        return;
    L20:
        this.lastSwitchIconRes = r02;
        ImageView r3 = this.switchModeButton;
        if (r4 == false) goto L24;
        AndroidUtilities.updateImageViewImageAnimated(r3, r02);
        return;
    L24:
        r3.setImageResource(r02);
        return;
    L16:
        r02 = R.drawable.iv_media_collage;
        goto L18
    L5:
        r0 = false;
        goto L6
    }

    public void bind(BlockRow r4, Delegate r5) {
        this.currentRow = r4;
        this.delegate = r5;
        bindBlockInset(r4);
        if (r4 != null) goto L5;
    L9:
        rebuildItems();
        this.caption.bind();
        if (this.currentPage < this.items.size()) goto L12;
        this.currentPage = Math.max(0, this.items.size() - 1);
    L12:
        float r42 = 0.0f;
        this.pageOffset = 0.0f;
        AnimatedFloat r52 = this.modeProgress;
        if (isSlideshow() == false) goto L15;
        r42 = 1.0f;
    L15:
        r52.set(r42, true);
        updateSwitchButton(false);
        requestLayout();
        invalidate();
        return;
    L5:
        if (isGalleryRow() == true) goto L9;
        if (r4.media != null) goto L9;
        r4.media = new MediaUploadState();
        goto L9
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
        this.caption.drawSelection(r1);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r10, View r11, long r12) {
        if (this.menuButtons.contains(r11) == false) goto L9;
        if (RichBlockChrome.quoteDepth(this.currentRow) <= 0) goto L9;
        r10.save();
        this.clipPath.rewind();
        this.clipPath.addRoundRect(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + Math.max(0, (getWidth() - getPaddingLeft()) - getPaddingRight()), getPaddingTop() + this.imageH, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
        r10.clipPath(this.clipPath);
        boolean r9 = super.drawChild(r10, r11, r12);
        r10.restore();
        return r9;
    L9:
        return super.drawChild(r10, r11, r12);
    }

    @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
    public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r1) {
        this.caption.fillTextLayoutBlocks(r1);
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public RichEditText getCaptionEditText() {
        return this.caption.editText;
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public BlockRow getRow() {
        return this.currentRow;
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public boolean isPressOnCaption(int r1, int r2) {
        return this.caption.isPressOnCaption(r1, r2);
    }

    @Override // org.telegram.ui.iv.RichBlockCell
    public int nestedContentMargin() {
        return AndroidUtilities.dp(16.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attached = true;
        int r0 = 0;
    L4:
        if (r0 >= this.items.size()) goto L6;
        this.items.get(r0).attach();
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = 0;
        this.attached = false;
        if (getParent() == null) goto L5;
        getParent().requestDisallowInterceptTouchEvent(false);
    L5:
        VelocityTracker r1 = this.velocityTracker;
        if (r1 == null) goto L8;
        r1.recycle();
        this.velocityTracker = null;
    L8:
        this.dragging = false;
    L10:
        if (r0 >= this.items.size()) goto L12;
        this.items.get(r0).detach();
        r0 = r0 + 1;
        goto L10
    L12:
        SpoilerEffect2 r02 = this.spoilerEffect;
        if (r02 == null) goto L15;
        r02.detach(this);
        this.spoilerEffect = null;
    L15:
        ValueAnimator r03 = this.settleAnimator;
        if (r03 == null) goto L20;
        r03.cancel();
        this.settleAnimator = null;
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        int r0 = getPaddingTop();
        drawMedia(r9);
        float r1 = this.modeProgress.get();
        if (this.items.size() < 2) goto L8;
        if (r1 <= 0.001f) goto L8;
        drawDots(r9, r1);
    L8:
        if (isCellSelected() == false) goto L10;
        Canvas r2 = r9;
        r2.drawRect(getPaddingLeft(), r0, getWidth() - getPaddingRight(), r0 + this.imageH, this.selectionPaint);
    L11:
        drawGlassButtons(r2);
        if (this.modeProgress.isInProgress() == false) goto L15;
        requestLayout();
        return;
    L15:
        return;
    L10:
        r2 = r9;
        goto L11
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
        int r92 = getPaddingLeft();
        int r112 = getPaddingRight();
        int r122 = r12 - r10;
        int r102 = Math.max(0, (r122 - r92) - r112);
        int r0 = r122 - r112;
        int r1 = captionMargin();
        this.caption.layout(r92 - r1, r112 - r1, r122, getPaddingTop() + this.imageH);
        int r113 = AndroidUtilities.dp(6.0f);
        ImageView r123 = this.addButton;
        int r02 = r0 - r113;
        r123.layout(r02 - r123.getMeasuredWidth(), getPaddingTop() + r113, r02, (getPaddingTop() + r113) + this.addButton.getMeasuredHeight());
        int r03 = r02 - r113;
        this.switchModeButton.layout((r03 - this.addButton.getMeasuredWidth()) - this.switchModeButton.getMeasuredWidth(), getPaddingTop() + r113, r03 - this.addButton.getMeasuredWidth(), (getPaddingTop() + r113) + this.addButton.getMeasuredHeight());
        List<MediaUploadState> r124 = medias();
        int r04 = 0;
    L4:
        if (r04 >= this.menuButtons.size()) goto L38;
        ImageView r14 = this.menuButtons.get(r04);
        int r3 = 8;
        if (r04 < r124.size()) goto L8;
    L36:
        r14.setVisibility(8);
    L37:
        r04 = r04 + 1;
        goto L4
    L8:
        if (r124.get(r04).state == 0) goto L36;
        if (r04 >= this.itemRects.size()) goto L36;
        RectF r2 = this.itemRects.get(r04);
        if (r2.right > 0.0f) goto L15;
    L35:
        r14.setVisibility(8);
        goto L37
    L15:
        if (r2.left >= r102) goto L35;
        if (r2.bottom <= getPaddingTop()) goto L35;
        if (r2.top >= (getPaddingTop() + this.imageH)) goto L35;
        int r4 = (((int) r2.left) + r113) + r92;
        int r22 = ((int) r2.top) + r113;
        r14.layout(r4, r22, r14.getMeasuredWidth() + r4, r14.getMeasuredHeight() + r22);
        float r6 = 1.0f;
        if (this.glass == true) goto L23;
    L30:
        r14.setAlpha(r6);
        if (r6 <= 0.01f) goto L34;
        r3 = 0;
    L34:
        r14.setVisibility(r3);
        goto L37
    L23:
        if (this.switchModeButton.getVisibility() != 0) goto L26;
        ImageView r23 = this.switchModeButton;
    L27:
        int r24 = r23.getLeft() - AndroidUtilities.dp(4.0f);
        if ((r4 + r14.getMeasuredWidth()) <= r24) goto L30;
        r6 = Math.max(0.0f, 1.0f - ((r4 - r24) / r14.getMeasuredWidth()));
        goto L30
    L26:
        r23 = this.addButton;
        goto L27
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        int r62 = View.MeasureSpec.getSize(r6);
        int r72 = getPaddingLeft();
        int r0 = getPaddingRight();
        int r2 = 0;
        int r1 = Math.max(0, (r62 - r72) - r0);
        AnimatedFloat r3 = this.modeProgress;
        if (isSlideshow() == false) goto L5;
        float r4 = 1.0f;
    L6:
        float r32 = r3.set(r4);
        computeGeometry(r1);
        this.imageW = r1;
        this.imageH = Math.round(AndroidUtilities.lerp(this.collageH, this.slideH, r32));
        buildItemRects(r32);
        int r12 = captionMargin();
        setMeasuredDimension(r62, ((getPaddingTop() + this.imageH) + this.caption.measure(r72 - r12, r0 - r12, r62)) + getPaddingBottom());
        this.addButton.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
        this.switchModeButton.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
    L8:
        if (r2 >= this.menuButtons.size()) goto L11;
        this.menuButtons.get(r2).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
        r2 = r2 + 1;
        goto L8
    L11:
        if (this.modeProgress.isInProgress() == false) goto L15;
        requestLayout();
        return;
    L15:
        return;
    L5:
        r4 = 0.0f;
        goto L6
    }

    public void onModeChanged() {
        ValueAnimator r0 = this.settleAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
        this.settleAnimator = null;
    L6:
        if (getParent() == null) goto L8;
        getParent().requestDisallowInterceptTouchEvent(false);
    L8:
        VelocityTracker r02 = this.velocityTracker;
        if (r02 == null) goto L11;
        r02.recycle();
        this.velocityTracker = null;
    L11:
        this.currentPage = 0;
        this.pageOffset = 0.0f;
        updateSwitchButton(true);
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r11) {
        float r0 = r11.getX();
        float r1 = r11.getY();
        int r2 = r11.getActionMasked();
        if (r1 >= getPaddingTop()) goto L5;
    L7:
        boolean r3 = false;
    L9:
        if (isSlideshow() == true) goto L11;
    L87:
        if (r2 != 0) goto L93;
        if (r3 == false) goto L90;
        this.pressedItem = itemIndexAt(r0, r1);
        return true;
    L90:
        return super.onTouchEvent(r11);
    L93:
        if (r2 != 1) goto L111;
        if (r3 == false) goto L99;
        int r02 = itemIndexAt(r0, r1);
        int r12 = this.pressedItem;
        if (r02 != r12) goto L99;
        handleTap(r12);
    L99:
        if (this.pressedItem != (-1)) goto L103;
        if (r3 == true) goto L103;
        boolean r03 = false;
    L104:
        this.pressedItem = -1;
        if (r03 == false) goto L107;
    L110:
        return true;
    L107:
        if (super.onTouchEvent(r11) == true) goto L110;
        return false;
    L103:
        r03 = true;
        goto L104
    L111:
        if (r2 != 3) goto L114;
        this.pressedItem = -1;
    L114:
        return super.onTouchEvent(r11);
    L11:
        if (this.modeProgress.isInProgress() == true) goto L87;
        if (this.items.size() < 2) goto L87;
        if (r2 != 0) goto L34;
        if (r3 == true) goto L20;
        return super.onTouchEvent(r11);
    L20:
        if (this.touchSlop != 0) goto L22;
        ViewConfiguration r22 = ViewConfiguration.get(getContext());
        this.touchSlop = r22.getScaledTouchSlop();
        this.minFlingVelocity = r22.getScaledMinimumFlingVelocity();
        this.maxFlingVelocity = r22.getScaledMaximumFlingVelocity();
    L22:
        this.downX = r0;
        this.downY = r1;
        this.dragging = false;
        VelocityTracker r04 = this.velocityTracker;
        if (r04 != null) goto L25;
        this.velocityTracker = VelocityTracker.obtain();
    L26:
        this.velocityTracker.addMovement(r11);
        if (getParent() == null) goto L29;
        getParent().requestDisallowInterceptTouchEvent(true);
    L29:
        ValueAnimator r112 = this.settleAnimator;
        if (r112 == null) goto L32;
        r112.cancel();
        this.settleAnimator = null;
    L32:
        this.pressedItem = this.currentPage;
        return true;
    L25:
        r04.clear();
        goto L26
    L34:
        float r32 = 0.0f;
        if (r2 != 2) goto L60;
        VelocityTracker r23 = this.velocityTracker;
        if (r23 == null) goto L39;
        r23.addMovement(r11);
    L39:
        float r05 = r0 - this.downX;
        float r13 = r1 - this.downY;
        if (this.dragging == true) goto L47;
        if (Math.abs(r05) <= this.touchSlop) goto L47;
        if (Math.abs(r05) <= Math.abs(r13)) goto L47;
        this.dragging = true;
        this.pressedItem = -1;
    L47:
        if (this.dragging == false) goto L59;
        float r113 = (-r05) / this.slideW;
        int r06 = this.currentPage;
        if (r06 != 0) goto L54;
        if (r113 >= 0.0f) goto L54;
        r113 = r113 * 0.3f;
    L54:
        if (r06 == (this.items.size() - 1)) goto L56;
    L58:
        this.pageOffset = r113;
        requestLayout();
        invalidate();
        goto L59
    L56:
        if (r113 <= 0.0f) goto L58;
        r113 = r113 * 0.3f;
    L59:
        return true;
    L60:
        if (r2 == 1) goto L64;
        if (r2 == 3) goto L64;
        return true;
    L64:
        if (r2 != 1) goto L72;
        VelocityTracker r07 = this.velocityTracker;
        if (r07 == null) goto L72;
        r07.addMovement(r11);
        this.velocityTracker.computeCurrentVelocity(1000, this.maxFlingVelocity);
        float r114 = this.velocityTracker.getXVelocity();
        float r08 = this.velocityTracker.getYVelocity();
        if (Math.abs(r114) < this.minFlingVelocity) goto L72;
        if (Math.abs(r114) <= Math.abs(r08)) goto L72;
        r32 = r114;
    L72:
        VelocityTracker r115 = this.velocityTracker;
        if (r115 == null) goto L76;
        r115.recycle();
        this.velocityTracker = null;
    L76:
        if (getParent() == null) goto L79;
        getParent().requestDisallowInterceptTouchEvent(false);
    L79:
        if (this.dragging == false) goto L81;
        this.dragging = false;
        settle(r32);
    L85:
        this.pressedItem = -1;
        return true;
    L81:
        if (r2 != 1) goto L85;
        int r116 = this.pressedItem;
        int r09 = this.currentPage;
        if (r116 != r09) goto L85;
        handleTap(r09);
        goto L85
    L5:
        if (r1 >= (getPaddingTop() + this.imageH)) goto L7;
        r3 = true;
        goto L9
    }

    @Override // org.telegram.ui.iv.RichCaptionHost
    public void persistCaption() {
        this.caption.persist();
    }

    public void refresh() {
        if (this.currentRow != null) goto L5;
        return;
    L5:
        rebuildItems();
        if (this.currentPage < this.items.size()) goto L8;
        this.currentPage = Math.max(0, this.items.size() - 1);
    L8:
        updateSwitchButton(false);
        requestLayout();
        invalidate();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_chat_inFileBackground, this.resourcesProvider));
        this.selectionPaint.setColor(Theme.getColor(Theme.key_chat_inTextSelectionHighlight, this.resourcesProvider));
        RichCaptionController r3 = this.caption;
        if (r3 == null) goto L6;
        r3.applyColors();
        return;
    }
}
