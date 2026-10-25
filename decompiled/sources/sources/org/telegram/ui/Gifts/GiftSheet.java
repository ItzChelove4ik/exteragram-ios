package org.telegram.ui.Gifts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.export.ui.ExportMapper$$ExternalSyntheticLambda2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.Choreographer60FpsContent;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.AccountFrozenAlert;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BatchParticlesDrawHelper;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CompatDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.ExtendedGridLayoutManager;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.GiftPremiumBottomSheet$GiftTier;
import org.telegram.ui.Components.Premium.PremiumLockIconView;
import org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.ProfileGooeyView$GPUImpl$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.Shaker;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.utils.NinePatchBuilder;
import org.telegram.ui.Components.chat.buttons.ChatActivityBlurredRoundButton$$ExternalSyntheticApiModelOutline0;
import org.telegram.ui.Components.chat.buttons.ChatActivityBlurredRoundButton$$ExternalSyntheticApiModelOutline1;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stars.ExplainStarsSheet;
import org.telegram.ui.Stars.StarGiftPatterns;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.bots.AffiliateProgramFragment;

/* loaded from: classes7.dex */
public class GiftSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private int TAB_ALL;
    private int TAB_COLLECTIBLES;
    private int TAB_IN_STOCK;
    private int TAB_LIMITED;
    private int TAB_MY_GIFTS;
    private int TAB_RESALE;
    private UniversalAdapter adapter;
    private final StarsIntroActivity.StarsBalanceView balanceView;
    private boolean birthday;
    private final Utilities.Callback<Boolean> closeParentSheet;
    private final int currentAccount;
    private final long dialogId;
    private final DefaultItemAnimator itemAnimator;
    private final ExtendedGridLayoutManager layoutManager;
    private final StarsController.GiftsList myGifts;
    private final String name;
    private List<TLRPC.TL_premiumGiftCodeOption> options;
    private final FrameLayout premiumHeaderView;
    private final ArrayList<GiftPremiumBottomSheet$GiftTier> premiumTiers;
    private int selectedTab;
    private final boolean self;
    private boolean shownCollectiblesInfo;
    private final LinearLayout starsHeaderView;
    private final LinkSpanDrawable.LinksTextView subtitleCollectiblesStarsView;
    private final LinkSpanDrawable.LinksTextView subtitleStarsView;
    private final ArrayList<CharSequence> tabs;
    private final FrameLayout topView;
    private TLRPC.DisallowedGiftsSettings userSettings;

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass1(GiftSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(120.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$10, reason: invalid class name */
    public class AnonymousClass10 extends SendGiftSheet {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass10(GiftSheet r1, Context r2, int r3, TL_stars.StarGift r4, long r5, Runnable r7, boolean r8, boolean r9) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r7, r8, r9);
        }

        @Override // org.telegram.ui.Gifts.SendGiftSheet
        public BulletinFactory getParentBulletinFactory() {
            GiftSheet r1 = this.this$0;
            return BulletinFactory.of(r1.container, GiftSheet.access$200(r1));
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$11, reason: invalid class name */
    public class AnonymousClass11 implements View.OnAttachStateChangeListener {
        final /* synthetic */ GiftSheet this$0;
        final /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable val$drawable;

        public AnonymousClass11(GiftSheet r1, AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2) {
            this.this$0 = r1;
            this.val$drawable = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$drawable.attach();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.val$drawable.detach();
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass2(GiftSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (getAlpha() >= 0.95f) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass3(GiftSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (getAlpha() >= 0.95f) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnAttachStateChangeListener {
        final /* synthetic */ GiftSheet this$0;
        final /* synthetic */ Runnable val$setSubtitle;

        public AnonymousClass4(GiftSheet r1, Runnable r2) {
            this.this$0 = r1;
            this.val$setSubtitle = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$setSubtitle.run();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass5(GiftSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            if (GiftSheet.m14909$$Nest$fgetadapter(this.this$0) == null) goto L15;
            if (r2 == 0) goto L15;
            UItem r22 = GiftSheet.m14909$$Nest$fgetadapter(this.this$0).getItem(r2 - 1);
            if (r22 == null) goto L13;
            int r23 = r22.spanCount;
            if (r23 == (-1)) goto L13;
            return r23;
        L13:
            return GiftSheet.m14910$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        L15:
            return GiftSheet.m14910$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends DefaultItemAnimator {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass6(GiftSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            return 0.3f;
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$7, reason: invalid class name */
    public class AnonymousClass7 extends SendGiftSheet {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass7(GiftSheet r1, Context r2, int r3, GiftPremiumBottomSheet$GiftTier r4, long r5, Runnable r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r7);
        }

        @Override // org.telegram.ui.Gifts.SendGiftSheet
        public BulletinFactory getParentBulletinFactory() {
            GiftSheet r1 = this.this$0;
            return BulletinFactory.of(r1.container, GiftSheet.access$000(r1));
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$8, reason: invalid class name */
    public class AnonymousClass8 extends StarGiftSheet {
        final /* synthetic */ GiftSheet this$0;

        public AnonymousClass8(GiftSheet r1, Context r2, int r3, long r4, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r6);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet, org.telegram.ui.ActionBar.BottomSheet, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public BulletinFactory getBulletinFactory() {
            GiftSheet r1 = this.this$0;
            return BulletinFactory.of(r1.container, GiftSheet.access$100(r1));
        }
    }

    /* renamed from: org.telegram.ui.Gifts.GiftSheet$9, reason: invalid class name */
    public class AnonymousClass9 extends ResaleGiftsFragment {
        final /* synthetic */ GiftSheet this$0;
        final /* synthetic */ ViewTreeObserver val$observer;
        final /* synthetic */ ViewTreeObserver.OnPreDrawListener val$onPreDrawListener;

        public AnonymousClass9(GiftSheet r1, long r2, String r4, long r5, Theme.ResourcesProvider r7, ViewTreeObserver r8, ViewTreeObserver.OnPreDrawListener r9) {
            this.this$0 = r1;
            this.val$observer = r8;
            this.val$onPreDrawListener = r9;
            super(r2, r4, r5, r7);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onPause() {
            super.onPause();
            this.val$observer.removeOnPreDrawListener(this.val$onPreDrawListener);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onResume() {
            super.onResume();
            this.val$observer.addOnPreDrawListener(this.val$onPreDrawListener);
        }
    }

    public static class CardBackground extends Drawable {
        private static SharedBackgroundDrawables staticSharedBackgroundDrawables;
        private AnimatedFloat animatedSelected;
        private TL_stars.starGiftAttributeBackdrop backdrop;
        private final Path clipPath;
        private RadialGradient gradient;
        private final Matrix gradientMatrix;
        private int gradientRadius;
        private Bitmap lastDrawnBitmap;
        private Paint lastDrawnBitmapPaint;
        private int lastDrawnColor;
        private boolean lastNeedShadow;
        public final Paint paint;
        private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable pattern;
        public long patternDocumentId;
        private float r;
        private final RectF rect;
        private final Theme.ResourcesProvider resourcesProvider;
        private boolean selected;
        public Integer selectedColor;
        public int selectedColorKey;
        private final Paint selectedPaint;
        public int selectionStyle;
        private final Path strokeClipPath;
        private int[] strokeColors;
        private LinearGradient strokeGradient;
        private final Matrix strokeGradientMatrix;
        public final Paint strokePaint;
        private final View view;
        public boolean withPadding;
        private final boolean withShadow;

        /* renamed from: org.telegram.ui.Gifts.GiftSheet$CardBackground$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable {
            final /* synthetic */ CardBackground this$0;

            public AnonymousClass1(CardBackground r1, View r2, int r3) {
                this.this$0 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable, org.telegram.ui.Components.AnimatedEmojiSpan.InvalidateHolder
            public void invalidate() {
                super.invalidate();
                if (this.this$0.getCallback() == null) goto L6;
                this.this$0.getCallback().invalidateDrawable(this.this$0);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Gifts.GiftSheet$CardBackground$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnAttachStateChangeListener {
            final /* synthetic */ CardBackground this$0;

            public AnonymousClass2(CardBackground r1) {
                this.this$0 = r1;
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View r1) {
                CardBackground.m14911$$Nest$fgetpattern(this.this$0).attach();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View r1) {
                CardBackground.m14911$$Nest$fgetpattern(this.this$0).detach();
            }
        }

        /* renamed from: -$$Nest$fgetpattern, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m14911$$Nest$fgetpattern(CardBackground r0) {
            return r0.pattern;
        }

        static {
            staticSharedBackgroundDrawables = new SharedBackgroundDrawables();
        }

        public CardBackground(View r10, Theme.ResourcesProvider r11, boolean r12) {
            Paint r0 = new Paint(1);
            this.paint = r0;
            Paint r2 = new Paint(1);
            this.strokePaint = r2;
            this.rect = new RectF();
            this.clipPath = new Path();
            this.gradientMatrix = new Matrix();
            this.strokeClipPath = new Path();
            this.strokeGradientMatrix = new Matrix();
            Paint r3 = new Paint(1);
            this.selectedPaint = r3;
            this.animatedSelected = new AnimatedFloat(new GiftSheet$CardBackground$$ExternalSyntheticLambda0(this), 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.r = AndroidUtilities.dp(11.0f);
            this.withPadding = true;
            this.selectionStyle = 0;
            int r1 = Theme.key_windowBackgroundWhite;
            this.selectedColorKey = r1;
            this.view = r10;
            this.resourcesProvider = r11;
            this.pattern = new AnonymousClass1(this, r10, AndroidUtilities.dp(28.0f));
            r10.addOnAttachStateChangeListener(new AnonymousClass2(this));
            if (r10.isAttachedToWindow() == false) goto L5;
            this.pattern.attach();
        L5:
            this.withShadow = r12;
            r0.setColor(Theme.getColor(r1, r11));
            checkShadow(r12);
            Paint.Style r9 = Paint.Style.STROKE;
            r3.setStyle(r9);
            r2.setStyle(r9);
        }

        private void checkShadow(boolean r5) {
            if (this.lastNeedShadow == r5) goto L10;
            this.lastNeedShadow = r5;
            Paint r0 = this.paint;
            if (r5 == false) goto L8;
            r0.setShadowLayer(AndroidUtilities.dp(1.66f), 0.0f, AndroidUtilities.dp(0.33f), Theme.getColor(Theme.key_dialogCardShadow, this.resourcesProvider));
            return;
        L8:
            r0.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            return;
        }

        private Bitmap getStableBitmapFromPattern(AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r5) {
            if (r5.isStable() == true) goto L5;
            return null;
        L5:
            Drawable r52 = r5.getDrawable();
            if ((r52 instanceof AnimatedEmojiDrawable) == false) goto L14;
            AnimatedEmojiDrawable r53 = (AnimatedEmojiDrawable) r52;
            ImageReceiver r0 = r53.getImageReceiver();
            long r2 = r53.getDocumentId();
            if (r0 == null) goto L14;
            if (r2 != this.patternDocumentId) goto L14;
            Bitmap r4 = r0.getBitmap();
            if (r4 == null) goto L14;
            return r4;
        L14:
            return null;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r2) {
            draw(r2, 0.0f);
        }

        public void draw(Canvas r24, float r25) {
            Canvas r1 = r24;
            Rect r9 = getBounds();
            float r10 = this.animatedSelected.set(this.selected);
            this.rect.set(r9);
            if (this.withPadding == false) goto L6;
            this.rect.inset(AndroidUtilities.dp(3.33f), AndroidUtilities.dp(4.0f));
        L6:
            if (this.backdrop == null) goto L13;
            int r2 = AndroidUtilities.lerp(Math.min(r9.width(), r9.height()), Math.max(r9.width(), r9.height()), 0.35f) / 2;
            if (this.gradient != null) goto L10;
        L11:
            this.gradientRadius = r2;
            float r15 = r2;
            TL_stars.starGiftAttributeBackdrop r22 = this.backdrop;
            int r5 = r22.center_color;
            this.gradient = new RadialGradient(0.0f, 0.0f, r15, new int[]{r5 | (-16777216), r5 | (-16777216), r22.edge_color | (-16777216)}, new float[]{0.0f, 0.0f, 1.0f}, Shader.TileMode.CLAMP);
        L12:
            this.gradientMatrix.reset();
            this.gradientMatrix.postTranslate(r9.centerX(), Math.min(AndroidUtilities.dp(50.0f), r9.centerY()));
            this.gradient.setLocalMatrix(this.gradientMatrix);
            this.paint.setShader(this.gradient);
        L14:
            int r23 = Theme.key_dialogCardShadow;
            int r52 = Theme.getColor(r23, this.resourcesProvider);
            int r6 = Theme.key_windowBackgroundWhite;
            int r7 = Theme.getColor(r6, this.resourcesProvider);
            boolean r12 = false;
            if (this.r == AndroidUtilities.dp(11.0f)) goto L17;
        L21:
            boolean r26 = false;
        L23:
            if (this.withShadow == false) goto L26;
            if (r26 == true) goto L26;
            boolean r62 = true;
        L27:
            checkShadow(r62);
            if (r26 == true) goto L30;
            RectF r27 = this.rect;
            float r53 = this.r;
            r1.drawRoundRect(r27, r53, r53, this.paint);
        L43:
            if (this.strokeColors == null) goto L45;
        L50:
            boolean r14 = true;
        L51:
            if (r14 == false) goto L54;
            r1.save();
            this.clipPath.rewind();
            Path r28 = this.clipPath;
            RectF r54 = this.rect;
            float r63 = this.r;
            r28.addRoundRect(r54, r63, r63, Path.Direction.CW);
            r1.clipPath(this.clipPath);
        L54:
            if (this.strokeColors == null) goto L60;
            if (this.strokeGradient != null) goto L58;
            this.strokeGradient = new LinearGradient(0.0f, 0.0f, 100.0f, 0.0f, this.strokeColors, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        L58:
            this.strokeGradientMatrix.reset();
            this.strokeGradientMatrix.postTranslate(r9.left, r9.top);
            this.strokeGradientMatrix.postRotate((float) ((Math.atan2(r9.height(), r9.width()) / 3.141592653589793d) * 180.0d));
            float r29 = ((float) Math.sqrt(Math.pow(r9.width(), 2.0d) + Math.pow(r9.height(), 2.0d))) / 100.0f;
            this.strokeGradientMatrix.postScale(r29, r29);
            this.strokeGradient.setLocalMatrix(this.strokeGradientMatrix);
            this.strokePaint.setShader(this.strokeGradient);
            this.strokePaint.setStrokeWidth(AndroidUtilities.dp(4.66f));
            RectF r210 = this.rect;
            float r3 = this.r;
            r1.drawRoundRect(r210, r3, r3, this.strokePaint);
        L60:
            if (this.backdrop != null) goto L62;
        L95:
            if (r14 == false) goto L98;
            r1.restore();
        L98:
            if (r10 <= 0.0f) goto L114;
            int r211 = this.selectionStyle;
            if (r211 != 0) goto L107;
            Paint r212 = this.selectedPaint;
            Integer r32 = this.selectedColor;
            if (r32 == null) goto L104;
            int r33 = r32.intValue();
        L105:
            r212.setColor(r33);
            this.selectedPaint.setStrokeWidth(AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(1.667f), r10));
            RectF r213 = AndroidUtilities.rectTmp;
            r213.set(this.rect);
            float r34 = AndroidUtilities.lerp(-AndroidUtilities.dpf2(2.33f), AndroidUtilities.dpf2(3.33f), r10);
            r213.inset(r34, r34);
            float r35 = AndroidUtilities.lerp(this.r, AndroidUtilities.dpf2(7.33f), r10);
            r1.drawRoundRect(r213, r35, r35, this.selectedPaint);
            return;
        L104:
            r33 = Theme.getColor(this.selectedColorKey, this.resourcesProvider);
            goto L105
        L107:
            if (r211 != 1) goto L115;
            Paint r214 = this.selectedPaint;
            Integer r36 = this.selectedColor;
            if (r36 == null) goto L111;
            int r37 = r36.intValue();
        L112:
            r214.setColor(r37);
            this.selectedPaint.setStrokeWidth(AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(3.0f), r10));
            RectF r215 = AndroidUtilities.rectTmp;
            r215.set(this.rect);
            float r38 = AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(3.0f) / 2.0f, r10);
            r215.inset(r38, r38);
            float r39 = AndroidUtilities.lerp(this.r, AndroidUtilities.dpf2(10.0f), r10);
            r1.drawRoundRect(r215, r39, r39, this.selectedPaint);
            return;
        L111:
            r37 = Theme.getColor(this.selectedColorKey, this.resourcesProvider);
            goto L112
        L115:
            return;
        L114:
            return;
        L62:
            if (this.pattern.isEmpty() == true) goto L95;
            int r216 = this.backdrop.pattern_color | (-16777216);
            r1.save();
            r1.translate(r9.centerX(), r9.centerY());
            if (BatchParticlesDrawHelper.isAvailable() == false) goto L87;
            Bitmap r310 = getStableBitmapFromPattern(this.pattern);
            if (r310 == null) goto L87;
            if (this.lastDrawnBitmap == r310) goto L70;
        L71:
            this.lastDrawnBitmap = r310;
            this.lastDrawnBitmapPaint = BatchParticlesDrawHelper.createBatchParticlesPaint(r310);
            r12 = true;
        L73:
            if (this.lastDrawnColor != r216) goto L75;
            if (r12 == true) goto L75;
        L80:
            if (r25 >= 1.0f) goto L82;
            Bitmap r4 = r310;
            StarGiftPatterns.drawPatternBatch(r1, 2, this.lastDrawnBitmapPaint, r4, r9.width(), r9.height(), 1.0f - r25, 1.0f);
        L84:
            if (r25 <= 0.0f) goto L86;
            r1.translate(0.0f, AndroidUtilities.dp(-31.0f));
            StarGiftPatterns.drawPatternBatch(r1, 0, this.lastDrawnBitmapPaint, r4, r9.width(), r9.height(), r25, 1.0f);
        L86:
            r1 = r24;
        L94:
            r1.restore();
            goto L95
        L82:
            r4 = r310;
        L75:
            this.lastDrawnColor = r216;
            int r55 = Build.VERSION.SDK_INT;
            Paint r64 = this.lastDrawnBitmapPaint;
            if (r55 < 29) goto L78;
            ChatActivityBlurredRoundButton$$ExternalSyntheticApiModelOutline1.m();
            r64.setColorFilter(ChatActivityBlurredRoundButton$$ExternalSyntheticApiModelOutline0.m(r216, ProfileGooeyView$GPUImpl$$ExternalSyntheticApiModelOutline0.m()));
            goto L80
        L78:
            r64.setColorFilter(new PorterDuffColorFilter(r216, PorterDuff.Mode.SRC_IN));
            goto L80
        L70:
            if (this.lastDrawnBitmapPaint != null) goto L73;
        L87:
            this.pattern.setColor(Integer.valueOf(r216));
            if (r25 >= 1.0f) goto L90;
            r1 = r24;
            StarGiftPatterns.drawPattern(r1, 2, this.pattern, r9.width(), r9.height(), 1.0f - r25, 1.0f);
        L92:
            if (r25 <= 0.0f) goto L94;
            r1.translate(0.0f, AndroidUtilities.dp(-31.0f));
            StarGiftPatterns.drawPattern(r1, 0, this.pattern, r9.width(), r9.height(), r25, 1.0f);
            goto L94
        L90:
            r1 = r24;
            goto L92
        L45:
            if (this.backdrop != null) goto L47;
        L49:
            r14 = false;
            goto L51
        L47:
            if (this.pattern.isEmpty() == true) goto L49;
        L30:
            if (staticSharedBackgroundDrawables != null) goto L32;
            staticSharedBackgroundDrawables = new SharedBackgroundDrawables();
        L32:
            RectF r217 = this.rect;
            Rect r65 = AndroidUtilities.rectTmp2;
            r217.round(r65);
            TL_stars.starGiftAttributeBackdrop r218 = this.backdrop;
            boolean r8 = this.withShadow;
            if (r218 == null) goto L37;
            if (r8 == false) goto L36;
            Drawable r219 = staticSharedBackgroundDrawables.getOrCreateShadowNinePatch(r52);
            DrawableUtils.setBoundsIncreasePadding(r219, r65);
            r219.draw(r1);
        L36:
            RectF r220 = this.rect;
            float r56 = this.r;
            r1.drawRoundRect(r220, r56, r56, this.paint);
            goto L43
        L37:
            if (r8 == false) goto L39;
            Drawable r221 = staticSharedBackgroundDrawables.getOrCreateFilledWithShadowNinePatch(r7, r52);
        L40:
            DrawableUtils.setBoundsIncreasePadding(r221, r65);
            r221.draw(r1);
            goto L43
        L39:
            r221 = staticSharedBackgroundDrawables.getOrCreateFilledNinePatch(r7);
        L26:
            r62 = false;
            goto L27
        L17:
            if (r52 != Theme.getColor(r23)) goto L21;
            if (r7 != Theme.getColor(r6)) goto L21;
            r26 = true;
            goto L23
        L10:
            if (this.gradientRadius == r2) goto L12;
        L13:
            this.paint.setShader(null);
            goto L14
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public boolean getPadding(Rect r4) {
            r4.set(AndroidUtilities.dp(3.33f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(3.33f), AndroidUtilities.dp(4.0f));
            return true;
        }

        public void invalidate() {
            this.view.invalidate();
            if (getCallback() == null) goto L6;
            getCallback().invalidateDrawable(this);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        public void setBackdrop(TL_stars.starGiftAttributeBackdrop r2) {
            if (this.backdrop == r2) goto L5;
            this.gradient = null;
        L5:
            this.backdrop = r2;
            invalidate();
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        public void setPadding(boolean r1) {
            this.withPadding = r1;
        }

        public void setPattern(TL_stars.starGiftAttributePattern r4) {
            this.patternDocumentId = 0;
            AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r0 = this.pattern;
            if (r4 != null) goto L6;
            r0.set(null, false);
            return;
        L6:
            r0.set(r4.document, false);
            TLRPC.Document r42 = r4.document;
            if (r42 == null) goto L10;
            this.patternDocumentId = r42.id;
            return;
        }

        public void setRoundRadius(float r1) {
            this.r = r1;
        }

        public void setSelected(boolean r2, boolean r3) {
            if (this.selected != r2) goto L5;
            return;
        L5:
            this.selected = r2;
            if (r3 == true) goto L8;
            this.animatedSelected.force(r2);
        L8:
            invalidate();
        }

        public void setStrokeColors(int[] r2) {
            if (this.strokeColors != r2) goto L5;
            return;
        L5:
            this.strokeColors = r2;
            this.strokeGradient = null;
            invalidate();
        }
    }

    public static class GiftCell extends FrameLayout {
        public static final int[] PREMIUM_STROKE = null;
        public boolean allowResaleInGifts;
        private final AnimatedFloat animatedReordering;
        private final AvatarDrawable avatarDrawable;
        private final BackupImageView avatarView;
        private final FrameLayout.LayoutParams avatarViewLayout1;
        private final FrameLayout.LayoutParams avatarViewLayout2;
        private Runnable cancel;
        public final FrameLayout card;
        public final CardBackground cardBackground;
        private final Rect cardBackgroundPadding;
        public final TextView chanceTextView;
        private CheckBox2 checkBox;
        private final int currentAccount;
        private TL_stars.StarGift gift;
        private boolean giftMine;
        public final BackupImageView imageView;
        public FrameLayout.LayoutParams imageViewLayoutParams;
        public boolean inCollection;
        public boolean inCrafting;
        public boolean inResalePage;
        private TLRPC.Document lastDocument;
        private long lastDocumentId;
        private GiftPremiumBottomSheet$GiftTier lastTier;
        private TL_stars.SavedStarGift lastUserGift;
        private final PremiumLockIconView lockView;
        private final PremiumLockIconView pinView;
        private boolean pinned;
        private boolean pinnedIcon;
        private final ImageView pinnedImageView;
        private final FrameLayout pinnedView;
        private GiftPremiumBottomSheet$GiftTier premiumTier;
        private final StarsBackgroundView priceBackground;
        private final FrameLayout priceLayout;
        private final TextView priceView;
        private boolean priotityAuction;
        private boolean reordering;
        private final Theme.ResourcesProvider resourcesProvider;
        private final Ribbon ribbon;
        private final Shaker shaker;
        private final TextView starsPriceView;
        private Text subtitle;
        private final TextView subtitleView;
        private Text title;
        private final TextView titleView;
        private final ImageView tonOnlySaleView;
        private TL_stars.SavedStarGift userGift;

        /* renamed from: org.telegram.ui.Gifts.GiftSheet$GiftCell$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            final /* synthetic */ GiftCell this$0;

            public AnonymousClass1(GiftCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, r3);
                GiftCell.m14913$$Nest$fgetpriceBackground(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
            }
        }

        public static class Factory extends UItem.UItemFactory<GiftCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asPremiumGift(GiftPremiumBottomSheet$GiftTier r2) {
                UItem r0 = UItem.ofFactory(Factory.class).setSpanCount(1);
                r0.object = r2;
                return r0;
            }

            public static UItem asStarGift(int r2, TL_stars.SavedStarGift r3, boolean r4, boolean r5, boolean r6) {
                UItem r0 = UItem.ofFactory(Factory.class).setSpanCount(1);
                r0.intValue = r2;
                r0.object = r3;
                r0.accent = r4;
                r0.collapsed = r5;
                r0.red = r6;
                return r0;
            }

            public static UItem asStarGift(int r2, TL_stars.StarGift r3, boolean r4, boolean r5, boolean r6, boolean r7, boolean r8) {
                UItem r0 = UItem.ofFactory(Factory.class).setSpanCount(1);
                r0.intValue = r2;
                r0.object = r3;
                r0.checked = r4;
                r0.object2 = Boolean.valueOf(r5);
                r0.red = r7;
                r0.accent = r6;
                r0.locked = r8;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void attachedView(RecyclerListView r1, View r2, UItem r3) {
                ((GiftCell) r2).setReordering(r3.reordering, false);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r8, UItem r9, boolean r10, UniversalAdapter r11, UniversalRecyclerView r12) {
                GiftCell r0 = (GiftCell) r8;
                Object r7 = r9.object;
                if ((r7 instanceof GiftPremiumBottomSheet$GiftTier) == false) goto L5;
                boolean r72 = r0.setPremiumGift((GiftPremiumBottomSheet$GiftTier) r7);
            L16:
                if (r9.collapsed == false) goto L18;
                r0.setChecked(r9.checked, r72);
            L18:
                r0.setReordering(r9.reordering, r72);
                FrameLayout r73 = r0.card;
                float r102 = 1.0f;
                if (r9.enabled == false) goto L21;
                float r82 = 1.0f;
            L22:
                r73.setAlpha(r82);
                Ribbon r74 = GiftCell.m14914$$Nest$fgetribbon(r0);
                if (r9.enabled == true) goto L26;
                r102 = 0.5f;
            L26:
                r74.setAlpha(r102);
                return;
            L21:
                r82 = 0.65f;
                goto L22
            L5:
                boolean r103 = false;
                if ((r7 instanceof TL_stars.StarGift) == false) goto L12;
                TL_stars.StarGift r1 = (TL_stars.StarGift) r7;
                boolean r2 = r9.checked;
                Object r75 = r9.object2;
                if ((r75 instanceof Boolean) == false) goto L10;
                r103 = ((Boolean) r75).booleanValue();
            L10:
                r72 = r0.setStarsGift(r1, r2, r103, r9.accent, r9.red, r9.locked);
                goto L16
            L12:
                if ((r7 instanceof TL_stars.SavedStarGift) == false) goto L14;
                r72 = r0.setStarsGift((TL_stars.SavedStarGift) r7, r9.accent, r9.red);
                goto L16
            L14:
                r72 = false;
                goto L16
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public GiftCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new GiftCell(r1, r3, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r7, UItem r8) {
                if (r7.accent == r8.accent) goto L5;
                return false;
            L5:
                Object r6 = r7.object;
                if (r6 != null) goto L10;
                if (r8.object != null) goto L10;
            L36:
                if (r7.intValue == r8.intValue) goto L38;
            L44:
                return false;
            L38:
                if (r7.checked != r8.checked) goto L44;
                if (r7.longValue != r8.longValue) goto L44;
                if (TextUtils.equals(r7.text, r8.text) == false) goto L44;
                return true;
            L10:
                if ((r6 instanceof GiftPremiumBottomSheet$GiftTier) == false) goto L16;
                if (r6 != r8.object) goto L14;
                return true;
            L14:
                return false;
            L16:
                if ((r6 instanceof TL_stars.StarGift) == false) goto L24;
                Object r2 = r8.object;
                if ((r2 instanceof TL_stars.StarGift) == false) goto L24;
                if (((TL_stars.StarGift) r6).id != ((TL_stars.StarGift) r2).id) goto L22;
                return true;
            L22:
                return false;
            L24:
                if ((r6 instanceof TL_stars.SavedStarGift) == false) goto L36;
                Object r22 = r8.object;
                if ((r22 instanceof TL_stars.SavedStarGift) == false) goto L36;
                TL_stars.SavedStarGift r62 = (TL_stars.SavedStarGift) r6;
                TL_stars.SavedStarGift r23 = (TL_stars.SavedStarGift) r22;
                if (r62.gift.id == r23.gift.id) goto L30;
            L34:
                return false;
            L30:
                if (r62.date != r23.date) goto L34;
                if (r62.saved_id != r23.saved_id) goto L34;
                return true;
            }
        }

        public static /* synthetic */ void $r8$lambda$5izOr0OjXgDr4XGfJaXxVek4JcA(GiftCell r0, boolean r1) {
            r0.lambda$setPinned$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$ch5U3uEmQcE2RTwHaRAYwiXzgO0(GiftCell r0, TL_stars.SavedStarGift r1) {
            r0.lambda$setStarsGift$2(r1);
        }

        /* renamed from: $r8$lambda$nOI2nyvnXOb-2nRbW8cQSvNWZn4, reason: not valid java name */
        public static /* synthetic */ void m14912$r8$lambda$nOI2nyvnXOb2nRbW8cQSvNWZn4(GiftCell r0, boolean r1) {
            r0.lambda$setShowPinIcon$1(r1);
        }

        /* renamed from: -$$Nest$fgetpriceBackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StarsBackgroundView m14913$$Nest$fgetpriceBackground(GiftCell r0) {
            return r0.priceBackground;
        }

        /* renamed from: -$$Nest$fgetribbon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Ribbon m14914$$Nest$fgetribbon(GiftCell r0) {
            return r0.ribbon;
        }

        static {
            PREMIUM_STROKE = new int[]{-2781403, -3635939};
        }

        public GiftCell(Context r23, int r24, Theme.ResourcesProvider r25) {
            super(r23);
            this.cardBackgroundPadding = new Rect();
            this.animatedReordering = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.currentAccount = r24;
            this.resourcesProvider = r25;
            ScaleStateListAnimator.apply(this, 0.04f, 1.5f);
            this.shaker = new Shaker(this);
            FrameLayout r0 = new FrameLayout(r23);
            this.card = r0;
            CardBackground r2 = new CardBackground(r0, r25, true);
            this.cardBackground = r2;
            r0.setBackground(r2);
            addView(r0, LayoutHelper.createFrame(-1, -1, 119));
            Ribbon r22 = new Ribbon(r23);
            this.ribbon = r22;
            addView(r22, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 2.0f, 1.0f, 0.0f));
            BackupImageView r5 = new BackupImageView(r23);
            this.imageView = r5;
            r5.getImageReceiver().setAutoRepeat(0);
            FrameLayout.LayoutParams r6 = LayoutHelper.createFrame(80, 80.0f, 17, 0.0f, 12.0f, 0.0f, 12.0f);
            this.imageViewLayoutParams = r6;
            r0.addView(r5, r6);
            PremiumLockIconView r62 = new PremiumLockIconView(r23, PremiumLockIconView.TYPE_GIFT_LOCK, r25);
            this.lockView = r62;
            r62.setImageReceiver(r5.getImageReceiver());
            r0.addView(r62, LayoutHelper.createFrame(30, 30.0f, 49, 0.0f, 38.0f, 0.0f, 0.0f));
            PremiumLockIconView r63 = new PremiumLockIconView(r23, PremiumLockIconView.TYPE_GIFT_PIN, r25);
            this.pinView = r63;
            r63.setImageReceiver(r5.getImageReceiver());
            r0.addView(r63, LayoutHelper.createFrame(44, 44, 17));
            r63.setAlpha(0.0f);
            r63.setScaleX(0.3f);
            r63.setScaleY(0.3f);
            r63.setVisibility(8);
            TextView r64 = new TextView(r23);
            this.titleView = r64;
            int r13 = Theme.key_windowBackgroundWhiteBlackText;
            r64.setTextColor(Theme.getColor(r13, r25));
            r64.setGravity(17);
            r64.setTextSize(1, 14.0f);
            r64.setTypeface(AndroidUtilities.bold());
            r0.addView(r64, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, 89.0f, 0.0f, 0.0f));
            TextView r65 = new TextView(r23);
            this.subtitleView = r65;
            r65.setTextColor(Theme.getColor(r13, r25));
            r65.setGravity(17);
            r65.setTextSize(1, 12.0f);
            r0.addView(r65, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, 107.0f, 0.0f, 0.0f));
            AnonymousClass1 r66 = new AnonymousClass1(this, r23);
            this.priceLayout = r66;
            TextView r132 = new TextView(r23);
            this.priceView = r132;
            r132.setTextSize(1, 12.0f);
            r132.setTypeface(AndroidUtilities.bold());
            r132.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
            r132.setGravity(17);
            r132.setTextColor(-13397548);
            r0.addView(r66, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 0.0f, 0.0f, 11.0f));
            StarsBackgroundView r14 = new StarsBackgroundView(r23);
            this.priceBackground = r14;
            r14.setBackgroundColor(-16776961);
            r66.addView(r14, LayoutHelper.createFrame(0, 0.0f));
            r66.addView(r132, LayoutHelper.createFrame(-2, 26, 17));
            if (Theme.isCurrentThemeDark() == false) goto L5;
            int r8 = 518759725;
        L6:
            r14.setBackground(new StarsBackground(r8));
            TextView r67 = new TextView(r23);
            this.starsPriceView = r67;
            r67.setTextSize(1, 10.66f);
            r67.setGravity(17);
            if (Theme.isCurrentThemeDark() == false) goto L9;
            int r82 = -1333971;
        L10:
            r67.setTextColor(r82);
            r67.setVisibility(8);
            r0.addView(r67, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 161.0f, 0.0f, 8.0f));
            this.avatarDrawable = new AvatarDrawable();
            BackupImageView r68 = new BackupImageView(r23);
            this.avatarView = r68;
            r68.setRoundRadius(AndroidUtilities.dp(20.0f));
            r68.setVisibility(8);
            FrameLayout.LayoutParams r83 = LayoutHelper.createFrame(20, 20.0f, 51, 2.0f, 2.0f, 2.0f, 2.0f);
            this.avatarViewLayout1 = r83;
            r0.addView(r68, r83);
            this.avatarViewLayout2 = LayoutHelper.createFrame(20, 20.0f, 51, 5.0f, 5.0f, 2.0f, 2.0f);
            FrameLayout r69 = new FrameLayout(r23);
            this.pinnedView = r69;
            r69.setAlpha(0.0f);
            r69.setScaleX(0.3f);
            r69.setScaleY(0.3f);
            r69.setVisibility(8);
            ImageView r52 = new ImageView(r23);
            this.pinnedImageView = r52;
            r52.setImageResource(R.drawable.msg_limit_pin);
            r52.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            r52.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            r69.addView(r52, LayoutHelper.createFrame(12.66f, 12.66f, 17));
            r0.addView(r69, LayoutHelper.createFrame(20, 20.0f, 51, 2.0f, 2.0f, 2.0f, 2.0f));
            ImageView r53 = new ImageView(r23);
            this.tonOnlySaleView = r53;
            r53.setImageResource(R.drawable.mini_gram_14);
            r53.setPadding(0, AndroidUtilities.dp(2.0f), 0, 0);
            r53.setVisibility(8);
            r53.setScaleType(ImageView.ScaleType.CENTER);
            r0.addView(r53, LayoutHelper.createFrame(20, 20.0f, 51, 3.0f, 3.0f, 3.0f, 3.0f));
            TextView r54 = new TextView(r23);
            this.chanceTextView = r54;
            r54.setTextSize(1, 10.0f);
            r54.setTypeface(AndroidUtilities.bold());
            r54.setPadding(AndroidUtilities.dp(5.0f), 0, AndroidUtilities.dp(5.0f), 0);
            r54.setGravity(17);
            r54.setTextColor(-1);
            r0.addView(r54, LayoutHelper.createFrame(-2, 17.0f, 51, 4.0f, 4.0f, 0.0f, 0.0f));
            r54.setVisibility(8);
            setImportantForAccessibility(1);
            r0.setImportantForAccessibility(4);
            r22.setImportantForAccessibility(2);
            return;
        L9:
            r82 = -2722014;
            goto L10
        L5:
            r8 = 1088989954;
            goto L6
        }

        private TL_stars.TL_starGiftUnique getUniqueStarGift() {
            TL_stars.SavedStarGift r1 = this.userGift;
            if (r1 == null) goto L8;
            TL_stars.StarGift r12 = r1.gift;
            if ((r12 instanceof TL_stars.TL_starGiftUnique) == true) goto L7;
            return null;
        L7:
            return (TL_stars.TL_starGiftUnique) r12;
        L8:
            return null;
        }

        private /* synthetic */ void lambda$setPinned$0(boolean r1) {
            if (r1 == true) goto L5;
            this.pinnedView.setVisibility(8);
            return;
        }

        private /* synthetic */ void lambda$setShowPinIcon$1(boolean r1) {
            if (r1 == true) goto L5;
            this.pinView.setVisibility(8);
            return;
        }

        private /* synthetic */ void lambda$setStarsGift$2(TL_stars.SavedStarGift r1) {
            if (r1.unsaved == true) goto L6;
            this.lockView.setVisibility(8);
            return;
        }

        private void setSticker(TLRPC.Document r11, Object r12) {
            if (r11 != null) goto L6;
            this.imageView.clearImage();
            this.lastDocument = null;
            this.lastDocumentId = 0;
            return;
        L6:
            if (this.lastDocument != r11) goto L8;
            return;
        L8:
            this.lastDocument = r11;
            this.lastDocumentId = r11.id;
            TLRPC.PhotoSize r0 = FileLoader.getClosestPhotoSizeWithSize(r11.thumbs, AndroidUtilities.dp(100.0f));
            SvgHelper.SvgDrawable r8 = DocumentObject.getSvgThumb(r11, Theme.key_windowBackgroundGray, 0.3f);
            this.imageView.setImage(ImageLocation.getForDocument(r11), "80_80_nolimit_pcache", ImageLocation.getForDocument(r0, r11), "80_80_nolimit_pcache", r8, r12);
        }

        private void updateRibbonText() {
            TL_stars.SavedStarGift r0 = this.userGift;
            if (r0 == null) goto L18;
            TL_stars.StarGift r02 = r0.gift;
            if ((r02 instanceof TL_stars.TL_starGiftUnique) == false) goto L12;
            this.ribbon.setVisibility(0);
            if (this.userGift.gift.resell_amount == null) goto L10;
            int r03 = Theme.blendOver(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), 0.04f));
            this.ribbon.setColor(Theme.getColor(Theme.key_color_green, this.resourcesProvider));
            this.ribbon.setStrokeColor(r03);
            this.ribbon.setBackdrop(null);
            this.ribbon.setText(LocaleController.getString(R.string.Gift2OnSale), false);
            return;
        L10:
            this.ribbon.setColor(Theme.getColor(Theme.key_gift_ribbon, this.resourcesProvider));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setBackdrop((TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(this.userGift.gift.attributes, TL_stars.starGiftAttributeBackdrop.class));
            this.ribbon.setText("#" + LocaleController.formatNumber(this.userGift.gift.num, ','), true);
            return;
        L12:
            boolean r04 = r02.limited;
            Ribbon r1 = this.ribbon;
            if (r04 == false) goto L16;
            r1.setVisibility(0);
            this.ribbon.setColor(Theme.getColor(Theme.key_gift_ribbon, this.resourcesProvider));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setBackdrop(null);
            this.ribbon.setText(LocaleController.formatString(R.string.Gift2Limited1OfRibbon, new Object[]{AndroidUtilities.formatWholeNumber(this.userGift.gift.availability_total, 0)}), true);
            return;
        L16:
            r1.setBackdrop(null);
            this.ribbon.setVisibility(8);
            return;
        L18:
            TL_stars.StarGift r05 = this.gift;
            if (r05 != null) goto L21;
            GiftPremiumBottomSheet$GiftTier r06 = this.premiumTier;
            if (r06 == null) goto L69;
            int r07 = r06.getDiscount();
            Ribbon r12 = this.ribbon;
            if (r07 <= 0) goto L67;
            r12.setVisibility(0);
            this.ribbon.setBackdrop(null);
            this.ribbon.setColors(-2535425, -8229377);
            this.ribbon.setStrokeColor(0);
            this.ribbon.setText(12, LocaleController.formatString(R.string.GiftPremiumOptionDiscount, new Object[]{Integer.valueOf(this.premiumTier.getDiscount())}), true);
            return;
        L67:
            r12.setVisibility(8);
            this.ribbon.setBackdrop(null);
            this.ribbon.setStrokeColor(0);
            return;
        L69:
            return;
        L21:
            if (this.inResalePage == false) goto L23;
        L59:
            this.ribbon.setVisibility(0);
            this.ribbon.setColor(Theme.getColor(Theme.key_gift_ribbon, this.resourcesProvider));
            this.ribbon.setBackdrop((TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(this.gift.attributes, TL_stars.starGiftAttributeBackdrop.class));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setText("#" + LocaleController.formatNumber(this.gift.num, ','), true);
            return;
        L23:
            if (this.inCrafting == true) goto L59;
            if (this.allowResaleInGifts == false) goto L32;
            if (r05.availability_resale <= 0) goto L32;
            this.ribbon.setVisibility(0);
            this.ribbon.setColor(Theme.getColor(Theme.key_color_green, this.resourcesProvider));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setBackdrop(null);
            this.ribbon.setText(LocaleController.getString(R.string.Gift2Resale), false);
            return;
        L32:
            if (this.giftMine == false) goto L35;
            this.ribbon.setVisibility(0);
            this.ribbon.setColor(Theme.getColor(Theme.key_gift_ribbon, this.resourcesProvider));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setBackdrop((TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(this.gift.attributes, TL_stars.starGiftAttributeBackdrop.class));
            this.ribbon.setText(LocaleController.formatString(R.string.Gift2Limited1OfRibbon, new Object[]{AndroidUtilities.formatWholeNumber(this.gift.availability_issued, 0)}), true);
            return;
        L35:
            boolean r13 = r05.limited;
            if (r13 == false) goto L42;
            if (r05.availability_remains > 0) goto L42;
            this.ribbon.setVisibility(0);
            this.ribbon.setColor(Theme.getColor(Theme.key_gift_ribbon_soldout, this.resourcesProvider));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setBackdrop(null);
            this.ribbon.setText(LocaleController.getString(R.string.Gift2SoldOut), true);
            return;
        L42:
            if (r05.auction == false) goto L50;
            this.ribbon.setVisibility(0);
            this.ribbon.setBackdrop(null);
            this.ribbon.setColors(-2650077, -4227818);
            this.ribbon.setStrokeColor(0);
            int r08 = this.gift.auction_start_date;
            int r14 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
            Ribbon r10 = this.ribbon;
            if (r08 <= r14) goto L47;
            r10.setText(LocaleController.getString(R.string.Gift2LimitedAuctionSoon), true);
            return;
        L47:
            r10.setText(LocaleController.getString(R.string.Gift2LimitedAuction), true);
            return;
        L50:
            if (r05.require_premium == false) goto L53;
            this.ribbon.setVisibility(0);
            this.ribbon.setBackdrop(null);
            this.ribbon.setColors(-2650077, -4227818);
            this.ribbon.setStrokeColor(0);
            this.ribbon.setText(LocaleController.getString(R.string.Gift2LimitedPremium), true);
            return;
        L53:
            Ribbon r09 = this.ribbon;
            if (r13 == false) goto L57;
            r09.setVisibility(0);
            this.ribbon.setColor(Theme.getColor(Theme.key_gift_ribbon, this.resourcesProvider));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setBackdrop(null);
            this.ribbon.setText(LocaleController.getString(R.string.Gift2LimitedRibbon), true);
            return;
        L57:
            r09.setBackdrop(null);
            this.ribbon.setStrokeColor(0);
            this.ribbon.setVisibility(8);
        }

        public void customDraw(View r17, Canvas r18, float r19, float r20, float r21) {
            Canvas r1 = r18;
            r1.save();
            r1.scale(getScaleX(), getScaleY(), r19 / 2.0f, r20 / 2.0f);
            TL_stars.TL_starGiftUnique r12 = getUniqueStarGift();
            if (r12 == null) goto L5;
            float r2 = AndroidUtilities.dp(63.0f) * r21;
        L6:
            this.cardBackground.setBounds(0, 0, (int) r19, (int) r20);
            this.cardBackground.draw(r1, r21);
            this.cardBackground.getPadding(this.cardBackgroundPadding);
            float r3 = AndroidUtilities.lerp(AndroidUtilities.dp(80.0f), AndroidUtilities.dp(120.0f), r21);
            float r22 = r20 - r2;
            this.imageView.getImageReceiver().setImageCoords((r19 - r3) / 2.0f, (r22 - r3) / 2.0f, r3, r3);
            this.imageView.getImageReceiver().draw(r1);
            if (this.imageView.getImageReceiver().isLottieRunning() == false) goto L10;
            r17.invalidate();
        L10:
            if (this.lockView.getVisibility() != 0) goto L15;
            if (this.lockView.getAlpha() <= 0.0f) goto L15;
            r1.save();
            r1.translate((r19 - this.lockView.getMeasuredWidth()) / 2.0f, AndroidUtilities.lerp(this.lockView.getY(), (r22 - this.lockView.getMeasuredHeight()) / 2.0f, r21));
            r1.saveLayerAlpha(0.0f, 0.0f, this.lockView.getWidth(), this.lockView.getHeight(), (int) (((1.0f - r21) * 255.0f) * this.lockView.getAlpha()), 31);
            this.lockView.draw(r1);
            r1.restore();
            r1.restore();
        L15:
            if (this.pinnedView.getVisibility() != 0) goto L20;
            if (this.pinnedView.getAlpha() <= 0.0f) goto L20;
            r1.save();
            r1.translate(this.cardBackgroundPadding.left + AndroidUtilities.dp(2.0f), this.cardBackgroundPadding.top + AndroidUtilities.dp(2.0f));
            r1.saveLayerAlpha(0.0f, 0.0f, this.pinnedView.getWidth(), this.pinnedView.getHeight(), (int) (this.pinnedView.getAlpha() * 255.0f), 31);
            this.pinnedView.draw(r1);
            r1.restore();
            r1.restore();
        L20:
            if (this.avatarView.getVisibility() != 0) goto L25;
            if (this.avatarView.getAlpha() <= 0.0f) goto L25;
            r1.save();
            r1.translate(this.cardBackgroundPadding.left + AndroidUtilities.dp(2.0f), this.cardBackgroundPadding.top + AndroidUtilities.dp(2.0f));
            this.avatarView.draw(r1);
            r1.restore();
        L25:
            if (this.ribbon.getVisibility() == 0) goto L27;
        L29:
            if (r12 != null) goto L31;
        L37:
            FrameLayout r23 = this.priceLayout;
            if (r23 != null) goto L40;
        L42:
            ImageView r24 = this.tonOnlySaleView;
            if (r24 != null) goto L45;
        L47:
            r1.restore();
            return;
        L45:
            if (r24.getVisibility() != 0) goto L47;
            r1.save();
            r1.translate(this.tonOnlySaleView.getX(), this.tonOnlySaleView.getY());
            r1.saveLayerAlpha(0.0f, 0.0f, this.tonOnlySaleView.getWidth(), this.tonOnlySaleView.getHeight(), (int) (((1.0f - r21) * 255.0f) * this.tonOnlySaleView.getAlpha()), 31);
            this.tonOnlySaleView.draw(r1);
            r1.restore();
            r1.restore();
            goto L47
        L40:
            if (r23.getVisibility() != 0) goto L42;
            r1.save();
            r1.translate(this.priceLayout.getX(), this.priceLayout.getY());
            r1.saveLayerAlpha(0.0f, 0.0f, this.priceLayout.getWidth(), this.priceLayout.getHeight(), (int) (((1.0f - r21) * 255.0f) * this.priceLayout.getAlpha()), 31);
            this.priceLayout.draw(r1);
            r1.restore();
            r1.restore();
            goto L42
        L31:
            if (this.title != null) goto L34;
            this.title = new Text(r12.title, 20.0f, AndroidUtilities.bold());
        L34:
            if (this.subtitle != null) goto L36;
            this.subtitle = new Text(LocaleController.formatPluralStringComma("Gift2CollectionNumber", r12.num), 13.0f);
        L36:
            float r13 = 1.0f - r21;
            this.title.ellipsize(r19 - AndroidUtilities.dp(8.0f)).draw(r1, (r19 - this.title.getWidth()) / 2.0f, ((r20 - AndroidUtilities.dp(40.0f)) - (this.title.getHeight() / 2.0f)) + (AndroidUtilities.dp(50.0f) * r13), -1, r21);
            this.subtitle.ellipsize(r19 - AndroidUtilities.dp(8.0f)).draw(r18, (r19 - this.subtitle.getWidth()) / 2.0f, (AndroidUtilities.dp(50.0f) * r13) + ((r20 - AndroidUtilities.dp(19.0f)) - (this.subtitle.getHeight() / 2.0f)), -1, r21 * 0.6f);
            r1 = r18;
            goto L37
        L27:
            if (this.ribbon.getAlpha() <= 0.0f) goto L29;
            r1.save();
            r1.translate(r19 - AndroidUtilities.dp(1.0f), AndroidUtilities.dp(2.0f));
            float r25 = AndroidUtilities.lerp(1.0f, 1.25f, r21);
            r1.scale(r25, r25);
            r1.translate(-this.ribbon.getWidth(), 0.0f);
            this.ribbon.draw(r1);
            r1.restore();
            goto L29
        L5:
            r2 = 0.0f;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            r4.save();
            r4.translate(getWidth() / 2.0f, getHeight() / 2.0f);
            float r0 = this.animatedReordering.set(this.reordering);
            if (r0 <= 0.0f) goto L5;
            this.shaker.concat(r4, r0);
        L5:
            r4.translate((-getWidth()) / 2.0f, (-getHeight()) / 2.0f);
            super.dispatchDraw(r4);
            r4.restore();
        }

        public TL_stars.StarGift getGift() {
            return this.gift;
        }

        public long getGiftId() {
            TL_stars.StarGift r2 = this.gift;
            if (r2 != null) goto L5;
            return 0;
        L5:
            return r2.id;
        }

        public GiftPremiumBottomSheet$GiftTier getPremiumTier() {
            return this.premiumTier;
        }

        public TL_stars.SavedStarGift getSavedGift() {
            return this.userGift;
        }

        public void hidePrice() {
            this.priceLayout.setVisibility(8);
        }

        public void invalidateCustom() {
            this.card.invalidate();
            this.card.invalidateDrawable(this.cardBackground);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r10) {
            super.onInitializeAccessibilityNodeInfo(r10);
            r10.setClassName("android.widget.Button");
            r10.setClickable(true);
            if (isEnabled() == false) goto L97;
            r10.addAction(16);
        L97:
            StringBuilder r1 = new StringBuilder();     // Catch: Exception -> L96
            String r3 = null;
            if (this.premiumTier == null) goto L14;
            TextView r2 = this.titleView;     // Catch: Exception -> L96
            if (r2 != null) goto L10;
        L30:
            CharSequence r22 = null;
        L32:
            if (TextUtils.isEmpty(r22) == false) goto L34;
            r22 = LocaleController.getString(R.string.Gift2Gift);     // Catch: Exception -> L96
        L34:
            r1.append(r22);     // Catch: Exception -> L96
            TextView r23 = this.subtitleView;     // Catch: Exception -> L96
            if (r23 != null) goto L38;
        L42:
            Ribbon r24 = this.ribbon;     // Catch: Exception -> L96
            if (r24 != null) goto L45;
        L49:
            FrameLayout r25 = this.priceLayout;     // Catch: Exception -> L96
            if (r25 != null) goto L52;
        L60:
            TL_stars.SavedStarGift r26 = this.userGift;     // Catch: Exception -> L96
            if (r26 != null) goto L63;
        L65:
            TL_stars.SavedStarGift r27 = this.userGift;     // Catch: Exception -> L96
            if (r27 != null) goto L68;
        L89:
            CheckBox2 r9 = this.checkBox;     // Catch: Exception -> L96
            if (r9 != null) goto L92;
        L94:
            r10.setContentDescription(r1.toString());     // Catch: Exception -> L96
            return;
        L92:
            if (r9.isChecked() == false) goto L94;
            r10.setCheckable(true);     // Catch: Exception -> L96
            r10.setChecked(true);     // Catch: Exception -> L96
            goto L94
        L68:
            if ((r27.gift instanceof TL_stars.TL_starGiftUnique) == true) goto L89;
            if (r27.name_hidden == true) goto L89;
            BackupImageView r28 = this.avatarView;     // Catch: Exception -> L96
            if (r28 == null) goto L89;
            if (r28.getVisibility() != 0) goto L89;
            long r5 = DialogObject.getPeerDialogId(this.userGift.from_id);     // Catch: Exception -> L96
            if (r5 == 0) goto L89;
            int r7 = this.currentAccount;
            if (r5 <= 0) goto L83;
            TLRPC.User r29 = MessagesController.getInstance(r7).getUser(Long.valueOf(r5));     // Catch: Exception -> L96
            if (r29 == null) goto L87;
            r3 = UserObject.getUserName(r29);     // Catch: Exception -> L96
        L87:
            if (TextUtils.isEmpty(r3) == true) goto L89;
            r1.append(", ");     // Catch: Exception -> L96
            r1.append(r3);     // Catch: Exception -> L96
            goto L89
        L83:
            TLRPC.Chat r210 = MessagesController.getInstance(r7).getChat(Long.valueOf(-r5));     // Catch: Exception -> L96
            if (r210 == null) goto L87;
            r3 = r210.title;     // Catch: Exception -> L96
            goto L87
        L63:
            if (r26.unsaved == false) goto L65;
            r1.append(", ");     // Catch: Exception -> L96
            r1.append(LocaleController.getString(R.string.Gift2FilterHidden));     // Catch: Exception -> L96
            goto L65
        L52:
            if (r25.getVisibility() != 0) goto L60;
            TextView r211 = this.priceView;     // Catch: Exception -> L96
            if (r211 == null) goto L60;
            if (r211.getVisibility() != 0) goto L60;
            if (TextUtils.isEmpty(this.priceView.getText()) == true) goto L60;
            r1.append(", ");     // Catch: Exception -> L96
            r1.append(this.priceView.getText());     // Catch: Exception -> L96
            goto L60
        L45:
            if (r24.getVisibility() != 0) goto L49;
            CharSequence r212 = this.ribbon.getText();     // Catch: Exception -> L96
            if (TextUtils.isEmpty(r212) == true) goto L49;
            r1.append(", ");     // Catch: Exception -> L96
            r1.append(r212);     // Catch: Exception -> L96
            goto L49
        L38:
            if (r23.getVisibility() != 0) goto L42;
            if (TextUtils.isEmpty(this.subtitleView.getText()) == true) goto L42;
            r1.append(", ");     // Catch: Exception -> L96
            r1.append(this.subtitleView.getText());     // Catch: Exception -> L96
            goto L42
        L10:
            if (r2.getVisibility() != 0) goto L30;
            if (TextUtils.isEmpty(this.titleView.getText()) == true) goto L30;
            r22 = this.titleView.getText();     // Catch: Exception -> L96
            goto L32
        L14:
            TL_stars.SavedStarGift r213 = this.userGift;     // Catch: Exception -> L96
            if (r213 == null) goto L23;
            TL_stars.StarGift r214 = r213.gift;     // Catch: Exception -> L96
            if (r214 == null) goto L23;
            if ((r214 instanceof TL_stars.TL_starGiftUnique) == false) goto L30;
            if (TextUtils.isEmpty(r214.title) == true) goto L30;
            r22 = this.userGift.gift.title;     // Catch: Exception -> L96
        L23:
            TL_stars.StarGift r215 = this.gift;     // Catch: Exception -> L96
            if (r215 == null) goto L30;
            if ((r215 instanceof TL_stars.TL_starGiftUnique) == false) goto L30;
            if (TextUtils.isEmpty(r215.title) == true) goto L30;
            r22 = this.gift.title;     // Catch: Exception -> L96
        }

        public void removeImage() {
            this.card.removeView(this.imageView);
        }

        public void setChecked(boolean r10, boolean r11) {
            if (this.checkBox != null) goto L5;
            CheckBox2 r0 = new CheckBox2(getContext(), 21);
            this.checkBox = r0;
            r0.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            this.checkBox.setDrawUnchecked(false);
            this.card.addView(this.checkBox, LayoutHelper.createFrame(24, 24.0f, 51, 4.0f, 4.0f, 4.0f, 4.0f));
        L5:
            this.avatarView.setVisibility(8);
            this.checkBox.setChecked(r10, r11);
        }

        public void setImageLayer(int r1) {
            this.imageView.setLayerNum(r1);
        }

        public void setImageSize(int r1) {
            FrameLayout.LayoutParams r0 = this.imageViewLayoutParams;
            r0.width = r1;
            r0.height = r1;
        }

        public void setPinned(boolean r7, boolean r8) {
            if (this.pinned != r7) goto L5;
            return;
        L5:
            this.pinned = r7;
            FrameLayout r0 = this.pinnedView;
            float r1 = 0.0f;
            boolean r2 = false;
            float r3 = 0.3f;
            if (r8 == false) goto L18;
            r0.setVisibility(0);
            ViewPropertyAnimator r02 = this.pinnedView.animate();
            if (r7 == false) goto L10;
            r1 = 1.0f;
        L10:
            ViewPropertyAnimator r03 = r02.alpha(r1);
            if (r7 == false) goto L13;
            float r12 = 1.0f;
        L14:
            ViewPropertyAnimator r04 = r03.scaleX(r12);
            if (r7 == false) goto L17;
            r3 = 1.0f;
        L17:
            r04.scaleY(r3).withEndAction(new GiftSheet$GiftCell$$ExternalSyntheticLambda2(this, r7)).start();
        L33:
            if (this.pinned == false) goto L35;
        L43:
            setShowPinIcon(r2, r8);
            updateRibbonText();
            return;
        L35:
            if (this.reordering == false) goto L43;
            if (this.inCollection == true) goto L43;
            TL_stars.SavedStarGift r72 = this.userGift;
            if (r72 == null) goto L43;
            if ((r72.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L43;
            r2 = true;
            goto L43
        L13:
            r12 = 0.3f;
            goto L14
        L18:
            if (r7 == false) goto L20;
            int r5 = 0;
        L21:
            r0.setVisibility(r5);
            FrameLayout r05 = this.pinnedView;
            if (r7 == false) goto L24;
            r1 = 1.0f;
        L24:
            r05.setAlpha(r1);
            FrameLayout r06 = this.pinnedView;
            if (r7 == false) goto L27;
            float r13 = 1.0f;
        L28:
            r06.setScaleX(r13);
            FrameLayout r07 = this.pinnedView;
            if (r7 == false) goto L31;
            r3 = 1.0f;
        L31:
            r07.setScaleY(r3);
            goto L33
        L27:
            r13 = 0.3f;
            goto L28
        L20:
            r5 = 8;
            goto L21
        }

        public boolean setPremiumGift(GiftPremiumBottomSheet$GiftTier r9) {
            int r0 = r9.getMonths();
            if (this.lastTier == r9) goto L7;
            BackupImageView r1 = this.imageView;
            Runnable r12 = StarsIntroActivity.setPremiumGiftImage(r1, r1.getImageReceiver(), r0);
            this.cancel = r12;
            if (r12 == null) goto L7;
            r12.run();
            this.cancel = null;
        L7:
            this.cardBackground.setBackdrop(null);
            this.cardBackground.setPattern(null);
            this.cardBackground.setStrokeColors(null);
            this.titleView.setText(LocaleController.formatPluralString("Gift2Months", r0, new Object[0]));
            this.subtitleView.setText(LocaleController.getString(R.string.TelegramPremiumShort));
            this.titleView.setVisibility(0);
            this.subtitleView.setVisibility(0);
            this.imageView.setTranslationY(-AndroidUtilities.dp(8.0f));
            this.avatarView.setVisibility(8);
            this.lockView.setVisibility(8);
            boolean r02 = r9.isStarsPaymentAvailable();
            TextView r3 = this.starsPriceView;
            if (r02 == true) goto L10;
            r3.setVisibility(8);
        L15:
            FrameLayout.LayoutParams r03 = this.imageViewLayoutParams;
            r03.gravity = 49;
            this.imageView.setLayoutParams(r03);
            this.priceView.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
            this.priceView.setTextSize(1, 12.0f);
            this.priceView.setText(r9.getFormattedPrice());
            this.priceBackground.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(13.0f), 422810068));
            this.priceView.setTextColor(-13397548);
            ((ViewGroup.MarginLayoutParams) this.priceLayout.getLayoutParams()).topMargin = AndroidUtilities.dp(130.0f);
            ((FrameLayout.LayoutParams) this.priceLayout.getLayoutParams()).gravity = 49;
            this.lastTier = r9;
            this.lastDocument = null;
            this.premiumTier = r9;
            this.gift = null;
            this.giftMine = false;
            this.userGift = null;
            this.allowResaleInGifts = false;
            this.inResalePage = false;
            this.inCollection = false;
            this.title = null;
            this.subtitle = null;
            setPinned(false, false);
            updateRibbonText();
            return false;
        L10:
            if (Theme.isCurrentThemeDark() == false) goto L12;
            int r04 = -1333971;
        L13:
            r3.setTextColor(r04);
            this.starsPriceView.setVisibility(0);
            SpannableStringBuilder r05 = new SpannableStringBuilder(_UrlKt.FRAGMENT_ENCODE_SET + LocaleController.formatNumber(r9.getStarsPrice(), ','));
            r05.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r05.length(), 33);
            ColoredImageSpan[] r13 = new ColoredImageSpan[1];
            this.starsPriceView.setText(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatSpannable(R.string.PremiumOrStarsPrice, new Object[]{r05}), 0.48f, r13));
            r13[0].spaceScaleX = 0.8f;
            goto L15
        L12:
            r04 = -2722014;
            goto L13
        }

        public void setPriorityAuction() {
            this.priotityAuction = true;
        }

        public void setReordering(boolean r2, boolean r3) {
            if (this.reordering != r2) goto L5;
            return;
        L5:
            this.reordering = r2;
            if (r3 == true) goto L8;
            this.animatedReordering.force(r2);
        L8:
            invalidate();
            if (this.pinned == true) goto L18;
            if (r2 == false) goto L18;
            if (this.inCollection == true) goto L18;
            TL_stars.SavedStarGift r22 = this.userGift;
            if (r22 == null) goto L18;
            if ((r22.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L18;
            boolean r23 = true;
        L19:
            setShowPinIcon(r23, r3);
            return;
        L18:
            r23 = false;
            goto L19
        }

        public void setRibbonColor(int r2) {
            this.ribbon.setColor(r2);
            this.ribbon.invalidate();
        }

        public void setRibbonText(String r2) {
            this.ribbon.setText(r2, true);
        }

        public void setRibbonTextOneOf(int r5) {
            this.ribbon.setVisibility(0);
            this.ribbon.setColor(Theme.getColor(Theme.key_gift_ribbon, this.resourcesProvider));
            this.ribbon.setStrokeColor(0);
            this.ribbon.setBackdrop((TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(this.gift.attributes, TL_stars.starGiftAttributeBackdrop.class));
            this.ribbon.setText(LocaleController.formatString(R.string.Gift2Limited1OfRibbon, new Object[]{AndroidUtilities.formatWholeNumber(r5, 0)}), true);
        }

        public void setSelected(boolean r4, boolean r5) {
            this.cardBackground.setSelected(r4, r5);
            ImageView r0 = this.tonOnlySaleView;
            float r1 = 0.0f;
            if (r5 == false) goto L13;
            ViewPropertyAnimator r3 = r0.animate();
            if (r4 == false) goto L7;
            float r52 = AndroidUtilities.dp(6.0f);
        L8:
            ViewPropertyAnimator r32 = r3.translationX(r52);
            if (r4 == false) goto L11;
            r1 = AndroidUtilities.dp(6.0f);
        L11:
            r32.translationY(r1).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            return;
        L7:
            r52 = 0.0f;
            goto L8
        L13:
            r0.animate().cancel();
            ImageView r53 = this.tonOnlySaleView;
            if (r4 == false) goto L16;
            float r02 = AndroidUtilities.dp(6.0f);
        L17:
            r53.setTranslationX(r02);
            ImageView r33 = this.tonOnlySaleView;
            if (r4 == false) goto L20;
            r1 = AndroidUtilities.dp(6.0f);
        L20:
            r33.setTranslationY(r1);
            return;
        L16:
            r02 = 0.0f;
            goto L17
        }

        public void setShowPinIcon(boolean r6, boolean r7) {
            if (this.pinnedIcon != r6) goto L5;
            return;
        L5:
            this.pinnedIcon = r6;
            PremiumLockIconView r0 = this.pinView;
            float r1 = 0.0f;
            int r2 = 0;
            float r3 = 0.3f;
            if (r7 == false) goto L19;
            r0.setVisibility(0);
            ViewPropertyAnimator r72 = this.pinView.animate();
            if (r6 == false) goto L10;
            r1 = 1.0f;
        L10:
            ViewPropertyAnimator r73 = r72.alpha(r1);
            if (r6 == false) goto L13;
            float r02 = 1.0f;
        L14:
            ViewPropertyAnimator r74 = r73.scaleX(r02);
            if (r6 == false) goto L17;
            r3 = 1.0f;
        L17:
            r74.scaleY(r3).withEndAction(new GiftSheet$GiftCell$$ExternalSyntheticLambda0(this, r6)).start();
            return;
        L13:
            r02 = 0.3f;
            goto L14
        L19:
            if (r6 == true) goto L22;
            r2 = 8;
        L22:
            r0.setVisibility(r2);
            PremiumLockIconView r75 = this.pinView;
            if (r6 == false) goto L25;
            r1 = 1.0f;
        L25:
            r75.setAlpha(r1);
            PremiumLockIconView r76 = this.pinView;
            if (r6 == false) goto L28;
            float r03 = 1.0f;
        L29:
            r76.setScaleX(r03);
            PremiumLockIconView r5 = this.pinView;
            if (r6 == false) goto L32;
            r3 = 1.0f;
        L32:
            r5.setScaleY(r3);
            return;
        L28:
            r03 = 0.3f;
            goto L29
        }

        public boolean setStarsGift(TL_stars.SavedStarGift r24, boolean r25, boolean r26) {
            Runnable r2 = this.cancel;
            if (r2 == null) goto L5;
            r2.run();
            this.cancel = null;
        L5:
            setSticker(r24.gift.getDocument(), r24);
            TL_stars.starGiftAttributeBackdrop r22 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r24.gift.attributes, TL_stars.starGiftAttributeBackdrop.class);
            this.cardBackground.setBackdrop(r22);
            this.cardBackground.setPattern((TL_stars.starGiftAttributePattern) StarsController.findAttribute(r24.gift.attributes, TL_stars.starGiftAttributePattern.class));
            this.cardBackground.setStrokeColors(null);
            this.titleView.setVisibility(8);
            this.subtitleView.setVisibility(8);
            this.imageView.setTranslationY(0.0f);
            this.lockView.setWaitingImage();
            PremiumLockIconView r4 = this.lockView;
            if (r22 == null) goto L8;
            Integer r9 = Integer.valueOf(Theme.multAlpha(r22.center_color | (-16777216), 0.75f));
        L9:
            r4.setBlendWithColor(r9);
            this.pinView.setWaitingImage();
            PremiumLockIconView r42 = this.pinView;
            if (r22 == null) goto L12;
            Integer r7 = Integer.valueOf(Theme.multAlpha(r22.center_color | (-16777216), 0.75f));
        L13:
            r42.setBlendWithColor(r7);
            ImageView r43 = this.tonOnlySaleView;
            if (r24.gift.resale_ton_only == false) goto L16;
            int r72 = 0;
        L17:
            r43.setVisibility(r72);
            FrameLayout r44 = this.pinnedView;
            if (r22 == null) goto L20;
            r44.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(20.0f), Theme.adaptHSV(r22.center_color | (-16777216), 0.1f, -0.2f)));
        L21:
            FrameLayout.LayoutParams r45 = this.imageViewLayoutParams;
            r45.gravity = 17;
            this.imageView.setLayoutParams(r45);
            TL_stars.SavedStarGift r46 = this.lastUserGift;
            PremiumLockIconView r73 = this.lockView;
            float r10 = 0.4f;
            if (r46 != r24) goto L36;
            r73.setVisibility(0);
            ViewPropertyAnimator r47 = this.lockView.animate();
            if (r24.unsaved == false) goto L26;
            float r74 = 1.0f;
        L27:
            ViewPropertyAnimator r48 = r47.alpha(r74);
            if (r24.unsaved == false) goto L30;
            float r75 = 1.0f;
        L31:
            ViewPropertyAnimator r49 = r48.scaleX(r75);
            if (r24.unsaved == false) goto L34;
            r10 = 1.0f;
        L34:
            r49.scaleY(r10).setDuration(350).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new GiftSheet$GiftCell$$ExternalSyntheticLambda1(this, r24)).start();
        L51:
            boolean r410 = r24.gift instanceof TL_stars.TL_starGiftUnique;
            this.avatarView.setColorFilter(null);
            this.avatarView.setLayoutParams(this.avatarViewLayout1);
            if (r410 == false) goto L57;
            if (r24.name_hidden == false) goto L57;
            this.avatarView.setVisibility(8);
        L69:
            int r15 = -1;
            if (r22 != null) goto L72;
        L83:
            TextView r23 = this.priceView;
            if (r25 == false) goto L86;
            r23.setVisibility(8);
            this.imageViewLayoutParams.topMargin = AndroidUtilities.dp(12.0f);
            this.imageViewLayoutParams.bottomMargin = AndroidUtilities.dp(12.0f);
            int r3 = 0;
        L87:
            TextView r27 = this.priceView;
            if (r410 == false) goto L90;
            r27.setPadding(AndroidUtilities.dp(8.0f), r3, AndroidUtilities.dp(8.0f), r3);
            this.priceView.setTextSize(1, 12.0f);
            this.priceView.setText(LocaleController.getString(R.string.Gift2PriceUnique));
        L95:
            TextView r28 = this.priceView;
            if (r410 == false) goto L99;
            int r6 = -1;
        L102:
            r28.setTextColor(r6);
            StarsBackgroundView r29 = this.priceBackground;
            int r76 = 1088989954;
            if (r410 == false) goto L106;
            int r102 = 1090519039;
        L109:
            r29.setBackground(new StarsBackground(r102));
            ImageView r210 = this.tonOnlySaleView;
            int r62 = AndroidUtilities.dp(10.0f);
            if (r410 == false) goto L113;
            r76 = 1090519039;
        L115:
            r210.setBackground(Theme.createRoundRectDrawable(r62, r76));
            ImageView r211 = this.tonOnlySaleView;
            if (r410 == false) goto L119;
        L122:
            r211.setColorFilter(r15);
            ((FrameLayout.LayoutParams) this.priceLayout.getLayoutParams()).gravity = 49;
            ((ViewGroup.MarginLayoutParams) this.priceLayout.getLayoutParams()).topMargin = AndroidUtilities.dp(103.0f);
        L123:
            this.starsPriceView.setVisibility(8);
            this.lastUserGift = r24;
            this.lastTier = null;
            TL_stars.SavedStarGift r32 = this.userGift;
            this.premiumTier = null;
            this.gift = null;
            this.giftMine = false;
            this.userGift = r24;
            this.allowResaleInGifts = false;
            this.inResalePage = false;
            this.inCollection = r26;
            this.title = null;
            this.subtitle = null;
            if (r24.pinned_to_top == false) goto L129;
            if (r410 == true) goto L127;
        L128:
            boolean r212 = true;
        L130:
            if (r32 != r24) goto L132;
            boolean r411 = true;
        L133:
            setPinned(r212, r411);
            updateRibbonText();
            if (r32 != r24) goto L136;
            return true;
        L136:
            return false;
        L132:
            r411 = false;
            goto L133
        L127:
            if (r24.name_hidden == true) goto L128;
        L129:
            r212 = false;
            goto L130
        L119:
            if (Theme.isCurrentThemeDark() == false) goto L121;
            r15 = -1333971;
            goto L122
        L121:
            r15 = -4229632;
            goto L122
        L113:
            if (Theme.isCurrentThemeDark() == false) goto L115;
            r76 = 518759725;
            goto L115
        L106:
            if (Theme.isCurrentThemeDark() == false) goto L108;
            r102 = 518759725;
            goto L109
        L108:
            r102 = 1088989954;
            goto L109
        L99:
            if (Theme.isCurrentThemeDark() == false) goto L101;
            r6 = -1333971;
            goto L102
        L101:
            r6 = -4229632;
            goto L102
        L90:
            r27.setPadding(AndroidUtilities.dp(8.0f), r3, AndroidUtilities.dp(10.0f), r3);
            this.priceView.setTextSize(1, 12.0f);
            TextView r213 = this.priceView;
            StringBuilder r33 = new StringBuilder("XTR ");
            TL_stars.StarGift r5 = r24.gift;
            long r63 = r5.stars;
            long r8 = r24.convert_stars;
            if (r8 > 0) goto L94;
            r8 = r5.convert_stars;
        L94:
            r33.append(LocaleController.formatNumber(Math.max(r63, r8), ','));
            r213.setText(StarsIntroActivity.replaceStarsWithPlain(r33.toString(), 0.66f));
            goto L95
        L86:
            r3 = 0;
            r23.setVisibility(0);
            FrameLayout.LayoutParams r214 = this.imageViewLayoutParams;
            r214.topMargin = 0;
            r214.bottomMargin = 0;
            goto L87
        L72:
            if (r24.gift.resell_amount == null) goto L83;
            this.priceView.setVisibility(0);
            FrameLayout.LayoutParams r13 = this.imageViewLayoutParams;
            r13.topMargin = 0;
            r13.bottomMargin = 0;
            this.priceView.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(10.0f), 0);
            this.priceView.setTextSize(1, 12.0f);
            ColoredImageSpan[] r34 = new ColoredImageSpan[1];
            TL_stars.StarGift r52 = r24.gift;
            if (r52.resale_ton_only == true) goto L76;
        L78:
            char r21 = 0;
            this.priceView.setText(StarsIntroActivity.replaceStars("XTR " + LocaleController.formatNumber(r24.gift.getResellStars(), ','), 0.95f, r34));
        L79:
            ColoredImageSpan r35 = r34[r21];
            if (r35 == null) goto L82;
            r35.translate(0.0f, AndroidUtilities.dp(0.5f));
        L82:
            int r215 = Theme.blendOver(r22.center_color | (-16777216), Theme.multAlpha(r22.pattern_color | (-16777216), 0.55f));
            this.priceBackground.setBackground(new StarsBackground(1895825407, r215));
            this.priceView.setTextColor(-1);
            this.tonOnlySaleView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), r215));
            this.tonOnlySaleView.setColorFilter(-1);
            ((FrameLayout.LayoutParams) this.priceLayout.getLayoutParams()).gravity = 49;
            ((ViewGroup.MarginLayoutParams) this.priceLayout.getLayoutParams()).topMargin = AndroidUtilities.dp(79.0f);
            goto L123
        L76:
            if (DialogObject.getPeerDialogId(r52.owner_id) != UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L78;
            TextView r53 = this.priceView;
            StringBuilder r82 = new StringBuilder("XTR ");
            r21 = 0;
            r82.append(StarsIntroActivity.formatStarsAmount(r24.gift.getResellAmount(AmountUtils$Currency.TON).toTl(), 1.0f, ','));
            r53.setText(StarsIntroActivity.replaceStars(true, r82.toString(), 0.95f, r34));
        L57:
            if (r24.name_hidden == false) goto L59;
            this.avatarView.setVisibility(0);
            CombinedDrawable r77 = StarsIntroActivity.StarsTransactionView.getPlatformDrawable("anonymous");
            r77.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
            this.avatarView.setImageDrawable(r77);
            goto L69
        L59:
            long r14 = DialogObject.getPeerDialogId(r24.from_id);
            int r103 = this.currentAccount;
            if (r14 <= 0) goto L65;
            TLRPC.User r78 = MessagesController.getInstance(r103).getUser(Long.valueOf(r14));
            BackupImageView r104 = this.avatarView;
            if (r78 == null) goto L64;
            r104.setVisibility(0);
            this.avatarDrawable.setInfo(r78);
            this.avatarView.setForUserOrChat(r78, this.avatarDrawable);
            goto L69
        L64:
            r104.setVisibility(8);
            goto L69
        L65:
            TLRPC.Chat r79 = MessagesController.getInstance(r103).getChat(Long.valueOf(-r14));
            BackupImageView r105 = this.avatarView;
            if (r79 == null) goto L68;
            r105.setVisibility(0);
            this.avatarDrawable.setInfo(r79);
            this.avatarView.setForUserOrChat(r79, this.avatarDrawable);
            goto L69
        L68:
            r105.setVisibility(8);
            goto L69
        L30:
            r75 = 0.4f;
            goto L31
        L26:
            r74 = 0.0f;
            goto L27
        L36:
            if (r24.unsaved == false) goto L38;
            float r412 = 1.0f;
        L39:
            r73.setAlpha(r412);
            PremiumLockIconView r413 = this.lockView;
            if (r24.unsaved == false) goto L42;
            float r710 = 1.0f;
        L43:
            r413.setScaleX(r710);
            PremiumLockIconView r414 = this.lockView;
            if (r24.unsaved == false) goto L46;
            r10 = 1.0f;
        L46:
            r414.setScaleY(r10);
            PremiumLockIconView r415 = this.lockView;
            if (r24.unsaved == false) goto L49;
            int r711 = 0;
        L50:
            r415.setVisibility(r711);
            goto L51
        L49:
            r711 = 8;
            goto L50
        L42:
            r710 = 0.4f;
            goto L43
        L38:
            r412 = 0.0f;
            goto L39
        L20:
            r44.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(20.0f), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider)));
            goto L21
        L16:
            r72 = 8;
            goto L17
        L12:
            r7 = null;
            goto L13
        L8:
            r9 = null;
            goto L9
        }

        public boolean setStarsGift(TL_stars.StarGift r28, boolean r29, boolean r30, boolean r31, boolean r32, boolean r33) {
            Runnable r6 = this.cancel;
            if (r6 == null) goto L5;
            r6.run();
            this.cancel = null;
        L5:
            setSticker(r28.getDocument(), r28);
            TL_stars.starGiftAttributeBackdrop r62 = (TL_stars.starGiftAttributeBackdrop) StarsController.findAttribute(r28.attributes, TL_stars.starGiftAttributeBackdrop.class);
            this.cardBackground.setBackdrop(r62);
            this.cardBackground.setPattern((TL_stars.starGiftAttributePattern) StarsController.findAttribute(r28.attributes, TL_stars.starGiftAttributePattern.class));
            if (r28.auction == false) goto L18;
            boolean r8 = r28.sold_out;
            if (r8 == true) goto L10;
        L11:
            if (r31 == true) goto L13;
        L14:
            CardBackground r11 = this.cardBackground;
            if (r8 == false) goto L17;
            int r82 = Theme.key_gift_ribbon_soldout;
            r11.setStrokeColors(new int[]{Theme.getColor(r82, this.resourcesProvider), Theme.getColor(r82, this.resourcesProvider)});
        L26:
            this.titleView.setVisibility(8);
            this.subtitleView.setVisibility(8);
            float r12 = 0.0f;
            this.imageView.setTranslationY(0.0f);
            this.lockView.setVisibility(8);
            ImageView r83 = this.tonOnlySaleView;
            if (r28.resale_ton_only == false) goto L29;
            int r13 = 0;
        L30:
            r83.setVisibility(r13);
            TextView r84 = this.chanceTextView;
            if (r33 == false) goto L33;
            int r132 = 0;
        L34:
            r84.setVisibility(r132);
            TextView r85 = this.chanceTextView;
            if (r28.resale_ton_only == false) goto L37;
            float r133 = AndroidUtilities.dp(23.0f);
        L38:
            r85.setTranslationX(r133);
            TextView r86 = this.chanceTextView;
            if (r28.resale_ton_only == false) goto L41;
            r12 = AndroidUtilities.dp(1.0f);
        L41:
            r86.setTranslationY(r12);
            String r87 = "+";
            if (r33 == false) goto L48;
            TextView r122 = this.chanceTextView;
            StringBuilder r134 = new StringBuilder("+");
            int r15 = r28.craft_chance_permille;
            if (r15 > 0) goto L46;
            Object r152 = "<0.1%";
        L47:
            r134.append(r152);
            r122.setText(r134.toString());
            goto L48
        L46:
            r152 = AffiliateProgramFragment.percents(r15);
        L48:
            FrameLayout.LayoutParams r123 = this.imageViewLayoutParams;
            r123.gravity = 49;
            this.imageView.setLayoutParams(r123);
            if (r32 == false) goto L51;
            long r17 = 0;
        L60:
            this.avatarView.setColorFilter(null);
            this.avatarView.setVisibility(8);
        L61:
            TextView r9 = this.priceView;
            if (r33 == false) goto L65;
            if (r32 == true) goto L65;
            int r10 = 8;
        L66:
            r9.setVisibility(r10);
            this.priceView.setTextSize(1, 12.0f);
            if (r29 == false) goto L73;
            this.priceView.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
            this.priceView.setText(LocaleController.getString(R.string.Gift2TransferMine));
            if (r62 == null) goto L71;
            int r92 = Theme.blendOver(r62.center_color | (-16777216), Theme.multAlpha(r62.pattern_color | (-16777216), 0.55f));
        L72:
            this.priceBackground.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(13.0f), r92, Theme.blendOver(r92, 822083583)));
            this.priceView.setTextColor(-1);
            this.tonOnlySaleView.setColorFilter(-1);
            this.tonOnlySaleView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(10.0f), r92, Theme.blendOver(r92, 822083583)));
        L133:
            ((ViewGroup.MarginLayoutParams) this.priceLayout.getLayoutParams()).topMargin = AndroidUtilities.dp(103.0f);
            ((FrameLayout.LayoutParams) this.priceLayout.getLayoutParams()).gravity = 49;
            this.starsPriceView.setVisibility(8);
            this.lastTier = null;
            this.premiumTier = null;
            this.gift = r28;
            this.giftMine = r29;
            this.userGift = null;
            this.allowResaleInGifts = r31;
            this.inResalePage = r32;
            this.inCollection = false;
            this.inCrafting = r33;
            this.title = null;
            this.subtitle = null;
            setPinned(false, false);
            updateRibbonText();
            return false;
        L71:
            r92 = 1090519039;
            goto L72
        L73:
            TextView r93 = this.priceView;
            if (r32 == false) goto L76;
            r93.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(10.0f), 0);
            long r88 = r28.getResellStars();
            int r63 = Theme.blendOver(r62.center_color | (-16777216), Theme.multAlpha(r62.pattern_color | (-16777216), 0.55f));
            this.priceView.setText(StarsIntroActivity.replaceStars("XTR " + LocaleController.formatNumber(r88, ',')));
            this.priceBackground.setBackground(new StarsBackground(1895825407, r63));
            this.priceView.setTextColor(-1);
            this.tonOnlySaleView.setColorFilter(-1);
            this.tonOnlySaleView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), r63));
            this.chanceTextView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(9.0f), r63));
            goto L133
        L76:
            r93.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(10.0f), 0);
            if (r31 == false) goto L86;
            long r14 = r28.availability_resale;
            if (r14 <= r17) goto L86;
            long r135 = r28.resell_min_stars;
            if (r14 > 1) goto L83;
        L85:
            boolean r21 = false;
        L94:
            if (r28.auction == true) goto L96;
        L102:
            TextView r94 = this.priceView;
            StringBuilder r102 = new StringBuilder("XTR ");
            r102.append(LocaleController.formatNumber(r135, ','));
            if (r21 == true) goto L106;
            r87 = _UrlKt.FRAGMENT_ENCODE_SET;
        L106:
            r102.append(r87);
            r94.setText(StarsIntroActivity.replaceStarsWithPlain(r102.toString(), 0.71f));
        L107:
            StarsBackgroundView r7 = this.priceBackground;
            boolean r95 = r28 instanceof TL_stars.TL_starGiftUnique;
            if (r95 == false) goto L111;
            int r136 = 1090519039;
        L114:
            r7.setBackground(new StarsBackground(r136));
            TextView r72 = this.priceView;
            int r137 = -2722014;
            if (Theme.isCurrentThemeDark() == false) goto L117;
            int r89 = -1333971;
        L118:
            r72.setTextColor(r89);
            ImageView r73 = this.tonOnlySaleView;
            if (Theme.isCurrentThemeDark() == false) goto L121;
            r137 = -1333971;
        L121:
            r73.setColorFilter(r137);
            ImageView r74 = this.tonOnlySaleView;
            int r810 = AndroidUtilities.dp(10.0f);
            if (r95 == false) goto L125;
            int r96 = 1090519039;
        L128:
            r74.setBackground(Theme.createRoundRectDrawable(r810, r96));
            if (r62 == null) goto L131;
            int r64 = Theme.blendOver(r62.center_color | (-16777216), Theme.multAlpha(r62.pattern_color | (-16777216), 0.55f));
        L132:
            this.chanceTextView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(9.0f), r64));
            goto L133
        L131:
            r64 = 0;
            goto L132
        L125:
            if (Theme.isCurrentThemeDark() == false) goto L127;
            r96 = 518759725;
            goto L128
        L127:
            r96 = 1088989954;
            goto L128
        L117:
            r89 = -2722014;
            goto L118
        L111:
            if (Theme.isCurrentThemeDark() == false) goto L113;
            r136 = 518759725;
            goto L114
        L113:
            r136 = 1088989954;
            goto L114
        L96:
            if (r28.availability_resale != r17) goto L102;
            TextView r75 = this.priceView;
            if (r28.sold_out == false) goto L100;
            int r811 = R.string.Gift2AuctionPriceView;
        L101:
            r75.setText(LocaleController.getString(r811));
            goto L107
        L100:
            r811 = R.string.Gift2AuctionPriceJoin;
            goto L101
        L83:
            if (r135 >= MessagesController.getInstance(this.currentAccount).config.starsStarGiftResaleAmountMax.get()) goto L85;
            r21 = true;
        L86:
            long r97 = r28.stars;
            if (r30 == true) goto L89;
        L91:
            long r138 = r17;
        L92:
            r135 = r138 + r97;
            goto L85
        L89:
            if (r28.can_upgrade == false) goto L91;
            r138 = r28.upgrade_stars;
        L65:
            r10 = 0;
            goto L66
        L51:
            if (r31 == false) goto L57;
            if (r28.availability_resale <= 0) goto L57;
        L55:
            r17 = 0;
        L57:
            if (r28.locked_until_date <= ConnectionsManager.getInstance(this.currentAccount).getCurrentTime()) goto L55;
            this.avatarView.setVisibility(0);
            this.avatarView.setLayoutParams(this.avatarViewLayout2);
            r17 = 0;
            this.avatarView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_color_red, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            this.avatarView.setImageResource(R.drawable.mini_gift_lock);
            goto L61
        L37:
            r133 = 0.0f;
            goto L38
        L33:
            r132 = 8;
            goto L34
        L29:
            r13 = 8;
            goto L30
        L17:
            r11.setStrokeColors(PREMIUM_STROKE);
            goto L26
        L13:
            if (r28.availability_resale > 0) goto L18;
        L10:
            if (this.priotityAuction == true) goto L11;
        L18:
            CardBackground r812 = this.cardBackground;
            if (r28.require_premium == false) goto L24;
            if (r31 == true) goto L22;
        L23:
            int[] r112 = PREMIUM_STROKE;
        L25:
            r812.setStrokeColors(r112);
            goto L26
        L22:
            if (r28.availability_resale <= 0) goto L23;
        L24:
            r112 = null;
            goto L25
        }
    }

    public static class Ribbon extends View {
        private CharSequence currentText;
        public final RibbonDrawable drawable;

        public Ribbon(Context r2) {
            super(r2);
            RibbonDrawable r22 = new RibbonDrawable(this, 1.0f);
            this.drawable = r22;
            r22.setCallback(this);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r5) {
            this.drawable.setBounds(0, 0, getWidth(), getHeight());
            this.drawable.draw(r5);
        }

        public CharSequence getText() {
            return this.currentText;
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f));
        }

        public void setBackdrop(TL_stars.starGiftAttributeBackdrop r3) {
            this.drawable.setBackdrop(r3, false, false);
            invalidate();
        }

        public void setColor(int r1) {
            this.drawable.setColor(r1);
        }

        public void setColors(int r1, int r2) {
            this.drawable.setColors(r1, r2);
        }

        public void setStrokeColor(int r1) {
            this.drawable.setStrokeColor(r1);
        }

        public void setText(int r1, CharSequence r2, boolean r3) {
            this.currentText = r2;
            this.drawable.setText(r1, r2, r3);
        }

        public void setText(CharSequence r2, boolean r3) {
            this.currentText = r2;
            RibbonDrawable r1 = this.drawable;
            if (r3 == false) goto L5;
            int r0 = 10;
        L6:
            r1.setText(r0, r2, r3);
            return;
        L5:
            r0 = 11;
            goto L6
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.drawable != r2) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static class RibbonDrawable extends CompatDrawable {
        private boolean left;
        private StarsReactionsSheet.Particles particles;
        private Path path;
        private float scale;
        private Paint strokePaint;
        private Text text;
        private int textColor;

        public static /* synthetic */ Float $r8$lambda$XJRGuowh0sbhbTRqcNJjpJ5WB64(boolean r0, Float r1) {
            if (r0 == false) goto L4;
            float r02 = 48.0f - r1.floatValue();
        L6:
            return Float.valueOf(r02);
        L4:
            r02 = r1.floatValue();
            goto L6
        }

        public RibbonDrawable(View r3, float r4) {
            super(r3);
            this.path = new Path();
            this.strokePaint = new Paint(1);
            this.textColor = -1;
            Path r32 = this.path;
            this.scale = r4;
            fillRibbonPath(r32, r4, false);
            this.paint.setColor(-698031);
            this.paint.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(2.33f)));
            this.strokePaint.setColor(0);
            this.strokePaint.setStyle(Paint.Style.STROKE);
            this.strokePaint.setStrokeJoin(Paint.Join.ROUND);
            this.strokePaint.setStrokeCap(Paint.Cap.ROUND);
        }

        public static void fillRibbonPath(Path r10, float r11, boolean r12) {
            GiftSheet$RibbonDrawable$$ExternalSyntheticLambda0 r7 = new GiftSheet$RibbonDrawable$$ExternalSyntheticLambda0(r12);
            r10.rewind();
            float r8 = r11 * 24.5f;
            r10.moveTo(AndroidUtilities.dp(((Float) r7.run(Float.valueOf(46.83f))).floatValue() * r11), AndroidUtilities.dp(r8));
            r10.lineTo(AndroidUtilities.dp(((Float) r7.run(Float.valueOf(23.5f))).floatValue() * r11), AndroidUtilities.dp(1.17f * r11));
            r10.cubicTo(AndroidUtilities.dp(((Float) r7.run(Float.valueOf(22.75f))).floatValue() * r11), AndroidUtilities.dp(0.42f * r11), AndroidUtilities.dp(((Float) r7.run(Float.valueOf(21.73f))).floatValue() * r11), 0.0f, AndroidUtilities.dp(((Float) r7.run(Float.valueOf(20.68f))).floatValue() * r11), 0.0f);
            float r9 = r11 * 0.05f;
            r10.cubicTo(AndroidUtilities.dp(((Float) r7.run(Float.valueOf(19.62f))).floatValue() * r11), 0.0f, AndroidUtilities.dp(((Float) r7.run(Float.valueOf(2.73f))).floatValue() * r11), AndroidUtilities.dp(r9), AndroidUtilities.dp(((Float) r7.run(Float.valueOf(1.55f))).floatValue() * r11), AndroidUtilities.dp(r9));
            r10.cubicTo(AndroidUtilities.dp(((Float) r7.run(Float.valueOf(0.36f))).floatValue() * r11), AndroidUtilities.dp(r9), AndroidUtilities.dp(((Float) r7.run(Float.valueOf(-0.23f))).floatValue() * r11), AndroidUtilities.dp(1.4885f * r11), AndroidUtilities.dp(((Float) r7.run(Float.valueOf(0.6f))).floatValue() * r11), AndroidUtilities.dp(2.32f * r11));
            r10.lineTo(AndroidUtilities.dp(((Float) r7.run(Float.valueOf(45.72f))).floatValue() * r11), AndroidUtilities.dp(47.44f * r11));
            float r1 = AndroidUtilities.dp(((Float) r7.run(Float.valueOf(46.56f))).floatValue() * r11);
            float r2 = AndroidUtilities.dp(48.28f * r11);
            Float r92 = Float.valueOf(48.0f);
            r10.cubicTo(r1, r2, AndroidUtilities.dp(((Float) r7.run(r92)).floatValue() * r11), AndroidUtilities.dp(47.68f * r11), AndroidUtilities.dp(((Float) r7.run(r92)).floatValue() * r11), AndroidUtilities.dp(46.5f * r11));
            r10.cubicTo(AndroidUtilities.dp(((Float) r7.run(r92)).floatValue() * r11), AndroidUtilities.dp(45.31f * r11), AndroidUtilities.dp(((Float) r7.run(r92)).floatValue() * r11), AndroidUtilities.dp(28.38f * r11), AndroidUtilities.dp(((Float) r7.run(r92)).floatValue() * r11), AndroidUtilities.dp(27.32f * r11));
            r10.cubicTo(AndroidUtilities.dp(((Float) r7.run(r92)).floatValue() * r11), AndroidUtilities.dp(26.26f * r11), AndroidUtilities.dp(((Float) r7.run(Float.valueOf(47.5f))).floatValue() * r11), AndroidUtilities.dp(25.24f * r11), AndroidUtilities.dp(((Float) r7.run(Float.valueOf(46.82f))).floatValue() * r11), AndroidUtilities.dp(r8));
            r10.close();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r15) {
            r15.save();
            r15.translate(getBounds().right - AndroidUtilities.dp(48.0f), getBounds().top);
            if (this.strokePaint.getAlpha() <= 0) goto L5;
            this.strokePaint.setStrokeWidth(AndroidUtilities.dp(1.33f) * 2);
            r15.drawPath(this.path, this.strokePaint);
        L5:
            r15.drawPath(this.path, this.paint);
            if (this.particles == null) goto L9;
            r15.clipPath(this.path);
            this.particles.setBounds(0, 0, AndroidUtilities.dp(48.0f), AndroidUtilities.dp(48.0f));
            this.particles.process();
            this.particles.draw(r15, -1);
            invalidateSelf();
        L9:
            if (this.text == null) goto L38;
            r15.save();
            if (this.left == false) goto L13;
            float r0 = -45.0f;
        L14:
            float r1 = getBounds().width() / 2.0f;
            float r4 = -7.0f;
            if (this.left == false) goto L17;
            float r3 = -7.0f;
        L18:
            float r12 = r1 + AndroidUtilities.dp(r3);
            float r32 = getBounds().height() / 2.0f;
            float r7 = 5.0f;
            if (this.left == false) goto L21;
            float r6 = 5.0f;
        L22:
            r15.rotate(r0, r12, r32 - AndroidUtilities.dp(r6));
            float r02 = Math.min(1.0f, AndroidUtilities.dp(40.0f) / this.text.getCurrentWidth());
            float r13 = getBounds().width() / 2.0f;
            if (this.left == false) goto L25;
            float r33 = -7.0f;
        L26:
            float r14 = r13 + AndroidUtilities.dp(r33);
            float r34 = getBounds().height() / 2.0f;
            if (this.left == false) goto L29;
            float r62 = 5.0f;
        L30:
            r15.scale(r02, r02, r14, r34 - AndroidUtilities.dp(r62));
            Text r8 = this.text;
            float r03 = getBounds().width() / 2.0f;
            if (this.left == true) goto L34;
            r4 = 6.0f;
        L34:
            float r10 = (r03 + AndroidUtilities.dp(r4)) - (this.text.getWidth() / 2.0f);
            float r04 = getBounds().height() / 2.0f;
            if (this.left == false) goto L37;
            r7 = 4.0f;
        L37:
            Canvas r9 = r15;
            r8.draw(r9, r10, r04 - AndroidUtilities.dp(r7), this.textColor, 1.0f);
            r9.restore();
        L39:
            r9.restore();
            return;
        L29:
            r62 = 6.0f;
            goto L30
        L25:
            r33 = 6.0f;
            goto L26
        L21:
            r6 = 6.0f;
            goto L22
        L17:
            r3 = 6.0f;
            goto L18
        L13:
            r0 = 45.0f;
            goto L14
        L38:
            r9 = r15;
            goto L39
        }

        public void setBackdrop(TL_stars.starGiftAttributeBackdrop r18, boolean r19, boolean r20) {
            if (r18 != null) goto L7;
            this.paint.setShader(null);
            return;
        L7:
            if (this.left == false) goto L9;
            boolean r2 = !r19;
        L10:
            Paint r0 = this.paint;
            float r6 = AndroidUtilities.dp(48.0f);
            float r7 = AndroidUtilities.dp(48.0f);
            int r4 = r18.center_color | (-16777216);
            float r8 = 0.05f;
            if (r2 == false) goto L13;
            float r10 = 0.07f;
        L14:
            float r11 = -0.1f;
            if (r2 == false) goto L17;
            float r13 = -0.15f;
        L18:
            float r14 = 0.125f;
            float r15 = 0.0f;
            if (r20 == false) goto L21;
            float r16 = 0.125f;
        L22:
            int r42 = Theme.adaptHSV(r4, r10, r13 - r16);
            int r1 = r18.edge_color | (-16777216);
            if (r2 == false) goto L25;
            r8 = 0.07f;
        L25:
            if (r2 == false) goto L27;
            r11 = -0.15f;
        L27:
            if (r20 == true) goto L30;
            r14 = 0.0f;
        L30:
            int[] r82 = {r42, Theme.adaptHSV(r1, r8, r11 - r14)};
            if (r2 == false) goto L33;
            float r43 = 1.0f;
        L34:
            if (r2 == true) goto L37;
            r15 = 1.0f;
        L37:
            r0.setShader(new LinearGradient(0.0f, 0.0f, r6, r7, r82, new float[]{r43, r15}, Shader.TileMode.CLAMP));
            return;
        L33:
            r43 = 0.0f;
            goto L34
        L21:
            r16 = 0.0f;
            goto L22
        L17:
            r13 = -0.1f;
            goto L18
        L13:
            r10 = 0.05f;
            goto L14
        L9:
            r2 = r19;
            goto L10
        }

        public void setColor(int r3) {
            this.paint.setShader(null);
            this.paint.setColor(r3);
        }

        public void setColors(int r9, int r10) {
            this.paint.setShader(new LinearGradient(0.0f, 0.0f, AndroidUtilities.dp(48.0f), AndroidUtilities.dp(48.0f), new int[]{r9, r10}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP));
        }

        public void setLeft(boolean r3) {
            Path r0 = this.path;
            float r1 = this.scale;
            this.left = r3;
            fillRibbonPath(r0, r1, r3);
        }

        public void setParticles(boolean r3) {
            if (this.particles == null) goto L5;
            boolean r0 = true;
        L6:
            if (r3 != r0) goto L8;
            return;
        L8:
            if (r3 == false) goto L11;
            StarsReactionsSheet.Particles r32 = new StarsReactionsSheet.Particles(2, 12);
            this.particles = r32;
            r32.setSpeed(5.0f);
            return;
        L11:
            this.particles = null;
            return;
        L5:
            r0 = false;
            goto L6
        }

        public void setStrokeColor(int r1) {
            this.strokePaint.setColor(r1);
        }

        public void setText(int r2, CharSequence r3, boolean r4) {
            float r22 = r2;
            if (r4 == false) goto L5;
            Typeface r42 = AndroidUtilities.bold();
        L6:
            this.text = new Text(r3, r22, r42);
            return;
        L5:
            r42 = null;
            goto L6
        }

        public void setTextColor(int r1) {
            this.textColor = r1;
        }
    }

    public static class SharedBackgroundDrawables {
        private Drawable filledNinePatch;
        private final Bitmap[] filledNinePatchBitmap;
        private Drawable filledWithShadowNinePatch;
        private final Bitmap[] filledWithShadowNinePatchBitmap;
        private int lastFillingColor;
        private int lastFillingWithShadowFillingColor;
        private int lastFillingWithShadowShadowColor;
        private int lastShadowColor;
        private final float[] radii;
        private Drawable shadowNinePatch;
        private final Bitmap[] shadowNinePatchBitmap;

        public SharedBackgroundDrawables() {
            this.shadowNinePatchBitmap = new Bitmap[1];
            this.filledNinePatchBitmap = new Bitmap[1];
            this.filledWithShadowNinePatchBitmap = new Bitmap[1];
            float[] r0 = new float[8];
            this.radii = r0;
            Arrays.fill(r0, AndroidUtilities.dp(11.0f));
        }

        public Drawable getOrCreateFilledNinePatch(int r10) {
            if (this.filledNinePatch != null) goto L5;
        L6:
            this.lastFillingColor = r10;
            this.filledNinePatch = NinePatchBuilder.createNinePatch(this.filledNinePatchBitmap, r10, this.radii, 0.0f, 0, 0.0f, 0.0f, r10);
        L8:
            return this.filledNinePatch;
        L5:
            if (this.lastFillingColor == r10) goto L8;
            goto L6
        }

        public Drawable getOrCreateFilledWithShadowNinePatch(int r10, int r11) {
            if (this.filledWithShadowNinePatch != null) goto L5;
        L8:
            this.lastFillingWithShadowFillingColor = r10;
            this.lastFillingWithShadowShadowColor = r11;
            this.filledWithShadowNinePatch = NinePatchBuilder.createNinePatch(this.filledWithShadowNinePatchBitmap, r10, this.radii, AndroidUtilities.dp(1.66f), r11, 0.0f, AndroidUtilities.dp(0.33f), r10);
        L10:
            return this.filledWithShadowNinePatch;
        L5:
            if (this.lastFillingWithShadowFillingColor == r10) goto L10;
            if (this.lastFillingWithShadowShadowColor == r11) goto L10;
            goto L8
        }

        public Drawable getOrCreateShadowNinePatch(int r10) {
            if (this.shadowNinePatch != null) goto L5;
        L6:
            this.lastShadowColor = r10;
            this.shadowNinePatch = NinePatchBuilder.createNinePatch(this.shadowNinePatchBitmap, 0, this.radii, AndroidUtilities.dp(1.66f), r10, 0.0f, AndroidUtilities.dp(0.33f), 0);
        L8:
            return this.shadowNinePatch;
        L5:
            if (this.lastShadowColor == r10) goto L8;
            goto L6
        }
    }

    public static class StarsBackground extends Drawable {
        public final Paint backgroundPaint;
        private final int color;
        private Runnable invalidateRunnable;
        private boolean isAttached;
        private Utilities.Callback<Boolean> liteModeCallback;
        public final StarsReactionsSheet.Particles particles;
        private boolean particlesAllowed;
        private final int particlesColor;
        public final Path path;
        public final RectF rectF;

        public static /* synthetic */ void $r8$lambda$U7HXi0Pdhz9hbFt6DT2mJvIHKv8(StarsBackground r0, Boolean r1) {
            r0.lambda$attach$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$uJG4HpHzrtmWk0GCxlhI37_rXvI(StarsBackground r0) {
            r0.invalidateParticles();
        }

        public StarsBackground(int r2) {
            this(ColorUtils.setAlphaComponent(r2, 128), r2);
        }

        public StarsBackground(int r3, int r4) {
            this.rectF = new RectF();
            this.path = new Path();
            Paint r0 = new Paint(1);
            this.backgroundPaint = r0;
            this.particlesColor = r3;
            this.color = r4;
            r0.setColor(r4);
            if (BatchParticlesDrawHelper.isAvailable() == false) goto L6;
            this.particles = new StarsReactionsSheet.Particles(1, 25);
            return;
        L6:
            this.particles = null;
        }

        private void checkParticlesAllowed() {
            if (this.particles != null) goto L5;
        L9:
            boolean r0 = false;
        L11:
            if (this.particlesAllowed != r0) goto L13;
            return;
        L13:
            this.particlesAllowed = r0;
            if (r0 == false) goto L16;
            Choreographer60FpsContent r02 = Choreographer60FpsContent.getInstance();
            GiftSheet$StarsBackground$$ExternalSyntheticLambda1 r1 = new GiftSheet$StarsBackground$$ExternalSyntheticLambda1(this);
            this.invalidateRunnable = r1;
            r02.addFrameCallback(r1, 15);
        L17:
            invalidateSelf();
            return;
        L16:
            Choreographer60FpsContent.getInstance().removeFrameCallback(this.invalidateRunnable);
            goto L17
        L5:
            if (this.isAttached == false) goto L9;
            if (LiteMode.isEnabled(131072) == false) goto L9;
            r0 = true;
            goto L11
        }

        private void invalidateParticles() {
            StarsReactionsSheet.Particles r0 = this.particles;
            if (r0 == null) goto L6;
            r0.process();
            invalidateSelf();
            return;
        }

        private /* synthetic */ void lambda$attach$0(Boolean r1) {
            checkParticlesAllowed();
        }

        public void attach() {
            if (this.isAttached == true) goto L6;
            this.isAttached = true;
            checkParticlesAllowed();
            GiftSheet$StarsBackground$$ExternalSyntheticLambda0 r0 = new GiftSheet$StarsBackground$$ExternalSyntheticLambda0(this);
            this.liteModeCallback = r0;
            LiteMode.addOnPowerSaverAppliedListener(r0);
            return;
        }

        public void detach() {
            if (this.isAttached == false) goto L6;
            this.isAttached = false;
            checkParticlesAllowed();
            LiteMode.removeOnPowerSaverAppliedListener(this.liteModeCallback);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            r3.drawPath(this.path, this.backgroundPaint);
            if (this.particles != null) goto L5;
            return;
        L5:
            if (this.particlesAllowed == false) goto L7;
        L8:
            r3.save();
            r3.clipPath(this.path);
            if (this.invalidateRunnable != null) goto L11;
            this.particles.process();
        L11:
            this.particles.draw(r3, this.particlesColor);
            r3.restore();
            if (this.invalidateRunnable != null) goto L17;
            invalidateSelf();
            return;
        L17:
            return;
        L7:
            if (this.isAttached == false) goto L8;
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r4) {
            super.onBoundsChange(r4);
            float r0 = Math.min(r4.width(), r4.height()) / 2.0f;
            this.rectF.set(r4);
            this.path.rewind();
            this.path.addRoundRect(this.rectF, r0, r0, Path.Direction.CW);
            StarsReactionsSheet.Particles r42 = this.particles;
            if (r42 == null) goto L6;
            r42.setBounds(this.rectF);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.backgroundPaint.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.backgroundPaint.setColorFilter(r1);
        }
    }

    public static class StarsBackgroundView extends View {
        private StarsBackground currentBackground;

        public StarsBackgroundView(Context r1) {
            super(r1);
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            StarsBackground r0 = this.currentBackground;
            if (r0 == null) goto L6;
            r0.attach();
            return;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            StarsBackground r0 = this.currentBackground;
            if (r0 == null) goto L6;
            r0.detach();
            return;
        }

        @Override // android.view.View
        public void setBackground(Drawable r2) {
            if (this.currentBackground != null) goto L5;
        L8:
            super.setBackground(r2);
            if ((r2 instanceof StarsBackground) == false) goto L14;
            this.currentBackground = (StarsBackground) r2;
            if (isAttachedToWindow() == false) goto L15;
            this.currentBackground.attach();
            return;
        L15:
            return;
        L14:
            return;
        L5:
            if (isAttachedToWindow() == false) goto L7;
            this.currentBackground.detach();
        L7:
            this.currentBackground = null;
            goto L8
        }
    }

    public static class Tabs extends FrameLayout {
        private AnimatedFloat animatedSelected;
        private final RectF ceiledRect;
        private final RectF flooredRect;
        private int lastId;
        private final LinearLayout layout;
        private final Theme.ResourcesProvider resourcesProvider;
        private final HorizontalScrollView scrollView;
        private int selected;
        private final Paint selectedPaint;
        private final RectF selectedRect;
        private final ArrayList<TextView> tabs;

        /* renamed from: org.telegram.ui.Gifts.GiftSheet$Tabs$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearLayout {
            final /* synthetic */ Tabs this$0;

            public AnonymousClass1(Tabs r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            private final void setBounds(RectF r3, View r4) {
                r3.set(r4.getLeft(), r4.getTop(), r4.getRight(), r4.getBottom());
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r7) {
                Tabs.m14919$$Nest$fgetselectedPaint(this.this$0).setColor(Theme.multAlpha(Theme.getColor(Theme.key_dialogGiftsTabText), 0.1f));
                float r0 = Tabs.m14915$$Nest$fgetanimatedSelected(this.this$0).set(Tabs.m14918$$Nest$fgetselected(this.this$0));
                double r1 = r0;
                int r3 = Utilities.clamp((int) Math.floor(r1), Tabs.m14921$$Nest$fgettabs(this.this$0).size() - 1, 0);
                int r12 = Utilities.clamp((int) Math.ceil(r1), Tabs.m14921$$Nest$fgettabs(this.this$0).size() - 1, 0);
                int r2 = Tabs.m14921$$Nest$fgettabs(this.this$0).size();
                Tabs r4 = this.this$0;
                if (r3 >= r2) goto L5;
                setBounds(Tabs.m14917$$Nest$fgetflooredRect(r4), (View) Tabs.m14921$$Nest$fgettabs(this.this$0).get(r3));
            L9:
                int r22 = Tabs.m14921$$Nest$fgettabs(this.this$0).size();
                Tabs r42 = this.this$0;
                if (r12 >= r22) goto L12;
                setBounds(Tabs.m14916$$Nest$fgetceiledRect(r42), (View) Tabs.m14921$$Nest$fgettabs(this.this$0).get(r12));
            L16:
                AndroidUtilities.lerp(Tabs.m14917$$Nest$fgetflooredRect(this.this$0), Tabs.m14916$$Nest$fgetceiledRect(this.this$0), r0 - r3, Tabs.m14920$$Nest$fgetselectedRect(this.this$0));
                float r02 = Tabs.m14920$$Nest$fgetselectedRect(this.this$0).height() / 2.0f;
                r7.drawRoundRect(Tabs.m14920$$Nest$fgetselectedRect(this.this$0), r02, r02, Tabs.m14919$$Nest$fgetselectedPaint(this.this$0));
                super.dispatchDraw(r7);
                return;
            L12:
                int r13 = Tabs.m14921$$Nest$fgettabs(r42).size();
                Tabs r23 = this.this$0;
                if (r3 >= r13) goto L15;
                setBounds(Tabs.m14916$$Nest$fgetceiledRect(r23), (View) Tabs.m14921$$Nest$fgettabs(this.this$0).get(r3));
                goto L16
            L15:
                Tabs.m14916$$Nest$fgetceiledRect(r23).set(0.0f, 0.0f, 0.0f, 0.0f);
                goto L16
            L5:
                int r24 = Tabs.m14921$$Nest$fgettabs(r4).size();
                Tabs r43 = this.this$0;
                if (r12 >= r24) goto L8;
                setBounds(Tabs.m14917$$Nest$fgetflooredRect(r43), (View) Tabs.m14921$$Nest$fgettabs(this.this$0).get(r12));
                goto L9
            L8:
                Tabs.m14917$$Nest$fgetflooredRect(r43).set(0.0f, 0.0f, 0.0f, 0.0f);
                goto L9
            }
        }

        public static class Factory extends UItem.UItemFactory<Tabs> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asTabs(int r1, ArrayList<CharSequence> r2, int r3, Utilities.Callback<Integer> r4) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.id = r1;
                r0.object = r2;
                r0.intValue = r3;
                r0.object2 = r4;
                return r0;
            }

            private static boolean eq(ArrayList<CharSequence> r5, ArrayList<CharSequence> r6) {
                if (r5 != r6) goto L5;
                return true;
            L5:
                if (r5 != null) goto L9;
                if (r6 != null) goto L9;
                return true;
            L9:
                if (r5 == null) goto L23;
                if (r6 == null) goto L23;
                if (r5.size() == r6.size()) goto L15;
                return false;
            L15:
                int r2 = 0;
            L17:
                if (r2 >= r5.size()) goto L22;
                if (TextUtils.equals(r5.get(r2), r6.get(r2)) == false) goto L20;
                r2 = r2 + 1;
                goto L17
            L20:
                return false;
            L22:
                return true;
            L23:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((Tabs) r1).set(r2.id, (ArrayList) r2.object, r2.intValue, (Utilities.Callback) r2.object2);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean contentsEquals(UItem r3, UItem r4) {
                if (r3.intValue == r4.intValue) goto L5;
                return false;
            L5:
                if (r3.object2 == r4.object2) goto L7;
                return false;
            L7:
                if (equals(r3, r4) == false) goto L13;
                return true;
            L13:
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public Tabs createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new Tabs(r1, true, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean equals(UItem r2, UItem r3) {
                if (r2.id == r3.id) goto L5;
                return false;
            L5:
                if (eq((ArrayList) r2.object, (ArrayList) r3.object) == false) goto L10;
                return true;
            L10:
                return false;
            }
        }

        public static /* synthetic */ void $r8$lambda$8LUCnuCXeW6XFid7k47_4mlXE7o(Utilities.Callback r0, int r1, View r2) {
            if (r0 == null) goto L5;
            r0.run(Integer.valueOf(r1));
            return;
        }

        /* renamed from: -$$Nest$fgetanimatedSelected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatedFloat m14915$$Nest$fgetanimatedSelected(Tabs r0) {
            return r0.animatedSelected;
        }

        /* renamed from: -$$Nest$fgetceiledRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m14916$$Nest$fgetceiledRect(Tabs r0) {
            return r0.ceiledRect;
        }

        /* renamed from: -$$Nest$fgetflooredRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m14917$$Nest$fgetflooredRect(Tabs r0) {
            return r0.flooredRect;
        }

        /* renamed from: -$$Nest$fgetselected, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14918$$Nest$fgetselected(Tabs r0) {
            return r0.selected;
        }

        /* renamed from: -$$Nest$fgetselectedPaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m14919$$Nest$fgetselectedPaint(Tabs r0) {
            return r0.selectedPaint;
        }

        /* renamed from: -$$Nest$fgetselectedRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m14920$$Nest$fgetselectedRect(Tabs r0) {
            return r0.selectedRect;
        }

        /* renamed from: -$$Nest$fgettabs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m14921$$Nest$fgettabs(Tabs r0) {
            return r0.tabs;
        }

        public Tabs(Context r10, boolean r11, Theme.ResourcesProvider r12) {
            super(r10);
            this.tabs = new ArrayList();
            this.flooredRect = new RectF();
            this.ceiledRect = new RectF();
            this.selectedRect = new RectF();
            this.selectedPaint = new Paint(1);
            this.lastId = Integer.MIN_VALUE;
            this.resourcesProvider = r12;
            AnonymousClass1 r3 = new AnonymousClass1(this, r10);
            this.layout = r3;
            r3.setClipToPadding(false);
            r3.setClipChildren(false);
            r3.setOrientation(0);
            r3.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(10.0f));
            if (r11 == false) goto L5;
            this.scrollView = null;
            addView(r3, LayoutHelper.createFrame(-2, -1, 1));
        L6:
            setHorizontalScrollBarEnabled(false);
            setClipToPadding(false);
            setClipChildren(false);
            this.animatedSelected = new AnimatedFloat(r3, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            return;
        L5:
            r3.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(3.0f));
            HorizontalScrollView r112 = new HorizontalScrollView(r10);
            this.scrollView = r112;
            r112.setHorizontalScrollBarEnabled(false);
            r112.setClipToPadding(false);
            r112.setClipChildren(false);
            r112.addView(r3, LayoutHelper.createFrame(-2, -1, 119));
            addView(r112, LayoutHelper.createFrame(-1, -1, 119));
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(int r8, ArrayList<CharSequence> r9, int r10, Utilities.Callback<Integer> r11) {
            int r1 = 0;
            if (this.lastId != r8) goto L5;
            boolean r0 = true;
        L6:
            this.lastId = r8;
            if (this.tabs.size() == r9.size()) goto L22;
            int r82 = 0;
            int r3 = 0;
        L10:
            if (r82 >= this.tabs.size()) goto L20;
            if (r3 >= r9.size()) goto L14;
            CharSequence r4 = r9.get(r3);
        L15:
            if (r4 != null) goto L17;
            this.layout.removeView(this.tabs.remove(r82));
            r82 = r82 - 1;
        L18:
            r3 = r3 + 1;
            r82 = r82 + 1;
            goto L10
        L17:
            this.tabs.get(r82).setText(r4);
            goto L18
        L14:
            r4 = null;
        L20:
            if (r3 >= r9.size()) goto L22;
            LinkSpanDrawable.LinksTextView r83 = new LinkSpanDrawable.LinksTextView(getContext());
            r83.setGravity(17);
            r83.setText(r9.get(r3));
            r83.setTypeface(AndroidUtilities.bold());
            r83.setTextColor(Theme.blendOver(Theme.getColor(Theme.key_dialogGiftsBackground), Theme.getColor(Theme.key_dialogGiftsTabText)));
            r83.setTextSize(1, 14.0f);
            r83.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
            r83.setEllipsize(TextUtils.TruncateAt.END);
            r83.setSingleLine();
            r83.setMaxLines(1);
            ScaleStateListAnimator.apply(r83, 0.075f, 1.4f);
            this.layout.addView(r83, LayoutHelper.createLinear(-2, 26));
            this.tabs.add(r83);
            r3 = r3 + 1;
        L22:
            this.selected = r10;
            if (r0 == true) goto L25;
            this.animatedSelected.set(r10, true);
        L25:
            this.layout.invalidate();
        L27:
            if (r1 >= this.tabs.size()) goto L29;
            this.tabs.get(r1).setOnClickListener(new GiftSheet$Tabs$$ExternalSyntheticLambda0(r11, r1));
            r1 = r1 + 1;
            goto L27
        L29:
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    /* renamed from: $r8$lambda$0j15-VsU0qDC0UGoVN33nwLSZPU, reason: not valid java name */
    public static /* synthetic */ void m14900$r8$lambda$0j15VsU0qDC0UGoVN33nwLSZPU(GiftSheet r0, Context r1, int r2, Utilities.Callback r3, long r4, View r6, int r7) {
        r0.lambda$new$19(r1, r2, r3, r4, r6, r7);
    }

    /* renamed from: $r8$lambda$1OoAsce-PB-HsaCkiie0ErcjfqE, reason: not valid java name */
    public static /* synthetic */ void m14901$r8$lambda$1OoAscePBHsaCkiie0ErcjfqE(GiftSheet r0, Utilities.Callback r1) {
        r0.lambda$new$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$3dkE23YxP0eT28ZcAWx7zHxtNUw(StarsController.GiftsList r1, Runnable r2, Object[] r3) {
        if (r3[1] != r1) goto L6;
        r2.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$6GKEAsHfL_LRDUEC7EItFMHAJAc(GiftSheet r0, AlertDialog r1, Runnable r2, Utilities.Callback r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$new$18(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$7sUWuQgrE8aC4he9Gv5PYxC2J38(GiftSheet r0, BillingResult r1, List r2) {
        r0.lambda$updatePremiumTiers$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8SDKcdZyeZL8uolQ_sqTBOslkMc(GiftSheet r0, StarGiftSheet r1, long r2, Utilities.Callback r4, Browser.Progress r5) {
        r0.lambda$new$10(r1, r2, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$BWHgLBI1xuuSDJlJhhRqd8BDE2o(TL_stars.StarGift r0) {
        return !r0.auction;
    }

    /* renamed from: $r8$lambda$C1-ONWB6TTKtl15l_B2Z5iU2tJw, reason: not valid java name */
    public static /* synthetic */ void m14902$r8$lambda$C1ONWB6TTKtl15l_B2Z5iU2tJw(GiftSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$EFlt2kdn1rXD9Br1CLN0Hr9nDSA(GiftSheet r0, Context r1, int r2, TL_stars.StarGift r3, Utilities.Callback r4) {
        r0.lambda$new$15(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$HSCej1TuvnAyxiD8uWvIfr9SGf0() {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        BaseFragment.BottomSheetParams r1 = new BaseFragment.BottomSheetParams();
        r1.transitionFromLeft = true;
        r1.allowNestedScroll = false;
        r0.showAsSheet(new PremiumPreviewFragment("gifts"), r1);
    }

    /* renamed from: $r8$lambda$HVG8eWI9lpLVXTPeUZB_CD-TAgg, reason: not valid java name */
    public static /* synthetic */ void m14903$r8$lambda$HVG8eWI9lpLVXTPeUZB_CDTAgg(GiftSheet r0, List r1) {
        r0.lambda$updatePremiumTiers$23(r1);
    }

    /* renamed from: $r8$lambda$Jnl3vS7ebyI_FbsCjj52RPue-GA, reason: not valid java name */
    public static /* synthetic */ void m14904$r8$lambda$Jnl3vS7ebyI_FbsCjj52RPueGA(GiftSheet r0, Utilities.Callback r1, long r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$KyyFQavT26rqu-qXRPj0aUhv404, reason: not valid java name */
    public static /* synthetic */ void m14905$r8$lambda$KyyFQavT26rquqXRPj0aUhv404(GiftSheet r0, AlertDialog r1, Utilities.Callback r2, TextView r3, ClickableSpan r4) {
        r0.lambda$new$16(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$M5-VczxiNOq3xbcZ9_OD-IWKxG0, reason: not valid java name */
    public static /* synthetic */ void m14906$r8$lambda$M5VczxiNOq3xbcZ9_ODIWKxG0(GiftSheet r0, int r1) {
        r0.selectTab(r1);
    }

    public static /* synthetic */ void $r8$lambda$Pi5ow4YrLC4tf07pJefeOojcV60(GiftSheet r0, long r1, View r3) {
        r0.lambda$new$1(r1, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$XUFXsp9t2Unk8m8ATPMnCO7ps6g() {
        return false;
    }

    public static /* synthetic */ void $r8$lambda$ZSr6MFGz92qcDMoGwZc13hO8gXo(GiftSheet r0, Utilities.Callback r1) {
        r0.lambda$new$13(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$_8_5QDLBHBSn2RCZiyK3pcD2eh4(GiftSheet r0, TL_stars.StarGift r1) {
        return r0.lambda$fillItems$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$_hx9q7bgFn_3uBtvh5mEihUcGic(StarGiftSheet r0, TLRPC.TL_error r1) {
        r0.getBulletinFactory().showForError(r1);
    }

    /* renamed from: $r8$lambda$ba52DoZ-DRjvxd27HXP_PNHwSQI, reason: not valid java name */
    public static /* synthetic */ void m14907$r8$lambda$ba52DoZDRjvxd27HXP_PNHwSQI(GiftSheet r0, Utilities.Callback r1) {
        r0.lambda$new$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$cxtX7gdoubyl2bzLgsinQRA62fw(GiftSheet r0) {
        r0.lambda$updatePremiumTiers$21();
    }

    public static /* synthetic */ void $r8$lambda$js4A9A0J_tqDu3NNZMKZ33nxjKs(GiftSheet r0, Browser.Progress r1, Utilities.Callback r2, StarGiftSheet r3, TLRPC.TL_error r4) {
        r0.lambda$new$9(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$l6YoD7qLRSBKj1JnSIWMydtY2VA(GiftSheet r0, AlertDialog r1, TLObject r2, Runnable r3, Utilities.Callback r4, TLRPC.TL_error r5) {
        r0.lambda$new$17(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$n1Ljdvs3KgElThl7Ovu6Pd457TM(Context r1) {
        new ExplainStarsSheet(r1).show();
    }

    /* renamed from: $r8$lambda$tg7mYB2XDb-Bmo8FkH6Hd64Cxik, reason: not valid java name */
    public static /* synthetic */ void m14908$r8$lambda$tg7mYB2XDbBmo8FkH6Hd64Cxik(GiftSheet r0, Utilities.Callback r1, Boolean r2) {
        r0.lambda$new$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zC6R_jpxZl07oHEPdijKlz7reno(GiftSheet r0, StarsController.GiftsList r1, long r2, Utilities.Callback r4, Context r5) {
        r0.lambda$new$5(r1, r2, r4, r5);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m14909$$Nest$fgetadapter(GiftSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ExtendedGridLayoutManager m14910$$Nest$fgetlayoutManager(GiftSheet r0) {
        return r0.layoutManager;
    }

    public GiftSheet(Context r27, int r28, long r29, List<TLRPC.TL_premiumGiftCodeOption> r31, Utilities.Callback<Boolean> r32) {
        super(r27, null, false, false, false, null);
        Context r2 = r27;
        this.premiumTiers = new ArrayList();
        this.TAB_ALL = -1;
        this.TAB_MY_GIFTS = -1;
        this.TAB_LIMITED = -1;
        this.TAB_IN_STOCK = -1;
        this.TAB_RESALE = -1;
        this.TAB_COLLECTIBLES = -1;
        this.tabs = new ArrayList();
        this.currentAccount = r28;
        this.dialogId = r29;
        if (UserConfig.getInstance(r28).getClientUserId() != r29) goto L5;
        boolean r3 = true;
    L6:
        this.self = r3;
        this.options = r31;
        this.closeParentSheet = r32;
        int r6 = Theme.key_dialogGiftsBackground;
        setBackgroundColor(Theme.getColor(r6));
        fixNavigationBar(Theme.getColor(r6));
        this.myGifts = StarsController.getInstance(r28).getProfileGiftsList(UserConfig.getInstance(r28).getClientUserId());
        StarsController.getInstance(r28).loadStarGifts();
        BackupImageView r62 = new BackupImageView(r2);
        r62.setImportantForAccessibility(2);
        AvatarDrawable r12 = new AvatarDrawable();
        if (r29 <= 0) goto L16;
        TLRPC.User r14 = MessagesController.getInstance(r28).getUser(Long.valueOf(r29));
        this.name = UserObject.getForcedFirstName(r14);
        r12.setInfo(r14);
        r62.setForUserOrChat(r14, r12);
        TLRPC.UserFull r122 = MessagesController.getInstance(r28).getUserFull(r29);
        if (r29 == UserConfig.getInstance(r28).getClientUserId()) goto L12;
        if (r122 == null) goto L12;
        TLRPC.DisallowedGiftsSettings r15 = r122.disallowed_stargifts;
    L13:
        this.userSettings = r15;
        if (r122 != null) goto L21;
        MessagesController.getInstance(r28).loadFullUser(r14, 0, true);
    L21:
        this.topPadding = 0.1f;
        StarsIntroActivity.StarsBalanceView r4 = new StarsIntroActivity.StarsBalanceView(r2, r28, this.resourcesProvider);
        this.balanceView = r4;
        ScaleStateListAnimator.apply(r4);
        r4.setOnClickListener(new GiftSheet$$ExternalSyntheticLambda0(this));
        FrameLayout r5 = new FrameLayout(r2);
        this.premiumHeaderView = r5;
        AnonymousClass1 r123 = new AnonymousClass1(this, r2);
        this.topView = r123;
        r123.setClipChildren(false);
        r123.setClipToPadding(false);
        r123.addView(StarsIntroActivity.makeParticlesView(r2, 70, 0), LayoutHelper.createFrame(-1, -1.0f));
        r62.setRoundRadius(ExteraConfig.getAvatarCorners(84.0f));
        r123.addView(r62, LayoutHelper.createFrame(84, 84.0f, 17, 0.0f, 15.0f, 0.0f, 17.0f));
        ScaleStateListAnimator.apply(r62);
        r62.setOnClickListener(new GiftSheet$$ExternalSyntheticLambda1(this, r29));
        r123.addView(r4, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, -3.0f, -10.0f, 0.0f));
        LinearLayout r42 = new LinearLayout(r2);
        r42.setOrientation(1);
        r5.addView(r42, LayoutHelper.createFrame(-1, -2, 55));
        TextView r0 = new TextView(r2);
        r0.setTextSize(1, 20.0f);
        r0.setTypeface(AndroidUtilities.bold());
        int r63 = Theme.key_dialogTextBlack;
        r0.setTextColor(Theme.getColor(r63, this.resourcesProvider));
        r0.setGravity(17);
        r42.addView(r0, LayoutHelper.createLinear(-1, -2, 1, 4, 0, 4, 0));
        r0.setMaxWidth(HintView2.cutInFancyHalf(r0.getText(), r0.getPaint()));
        LinkSpanDrawable.LinksTextView r124 = new LinkSpanDrawable.LinksTextView(r2, this.resourcesProvider);
        int r142 = Theme.key_chat_messageLinkIn;
        r124.setLinkTextColor(Theme.getColor(r142, this.resourcesProvider));
        r124.setTextSize(1, 14.0f);
        r124.setTextColor(Theme.getColor(r63, this.resourcesProvider));
        r124.setGravity(17);
        r124.setLineSpacing(AndroidUtilities.dp(2.33f), 1.0f);
        r42.addView(r124, LayoutHelper.createLinear(-1, -2, 1, 4, 4, 4, 12));
        r0.setText(LocaleController.getString(R.string.Gift2Premium));
        r124.setText(TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2PremiumInfo, new Object[]{this.name})), " ", AndroidUtilities.replaceArrows(AndroidUtilities.makeClickable(LocaleController.getString(R.string.Gift2PremiumInfoLink), new GiftSheet$$ExternalSyntheticLambda2()), true)}));
        r124.setMaxWidth(HintView2.cutInFancyHalf(r124.getText(), r124.getPaint()));
        LinearLayout r02 = new LinearLayout(r2);
        this.starsHeaderView = r02;
        r02.setOrientation(1);
        TextView r43 = new TextView(r2);
        r43.setTextSize(1, 20.0f);
        r43.setTypeface(AndroidUtilities.bold());
        r43.setTextColor(Theme.getColor(r63, this.resourcesProvider));
        r43.setGravity(17);
        r02.addView(r43, LayoutHelper.createLinear(-1, -2, 1, 4, 0, 4, 0));
        AnonymousClass2 r11 = new AnonymousClass2(this, r2, this.resourcesProvider);
        this.subtitleStarsView = r11;
        r11.setLinkTextColor(Theme.getColor(r142, this.resourcesProvider));
        r11.setTextSize(1, 14.0f);
        r11.setTextColor(Theme.getColor(r63, this.resourcesProvider));
        r11.setGravity(17);
        AnonymousClass3 r52 = new AnonymousClass3(this, r2, this.resourcesProvider);
        this.subtitleCollectiblesStarsView = r52;
        r52.setLinkTextColor(Theme.getColor(r142, this.resourcesProvider));
        r52.setTextSize(1, 14.0f);
        r52.setTextColor(Theme.getColor(r63, this.resourcesProvider));
        r52.setGravity(17);
        r52.setAlpha(0.0f);
        r52.setScaleX(0.85f);
        r52.setScaleY(0.85f);
        FrameLayout r125 = new FrameLayout(r2);
        r125.addView(r11, LayoutHelper.createFrame(-1, -2.0f, 49, 26.0f, 0.0f, 26.0f, 0.0f));
        r125.addView(r52, LayoutHelper.createFrame(-1, -2.0f, 49, 26.0f, 0.0f, 26.0f, 0.0f));
        if (r29 >= 0) goto L24;
        int r53 = R.string.Gift2StarsChannel;
    L27:
        r43.setText(LocaleController.getString(r53));
        if (r3 == false) goto L30;
        r02.addView(r125, LayoutHelper.createLinear(-2, -2, 1, 0, 9, 0, 4));
        LinkSpanDrawable.LinksTextView r33 = new LinkSpanDrawable.LinksTextView(r2, this.resourcesProvider);
        r33.setLinkTextColor(Theme.getColor(r142, this.resourcesProvider));
        r33.setTextSize(1, 14.0f);
        r33.setTextColor(Theme.getColor(r63, this.resourcesProvider));
        r33.setGravity(17);
        r02.addView(r33, LayoutHelper.createLinear(-2, -2, 1, 26, 4, 26, 6));
        r11.setText(LocaleController.getString(R.string.Gift2StarsSelfInfo1));
        r33.setText(LocaleController.getString(R.string.Gift2StarsSelfInfo2));
    L36:
        ExtendedGridLayoutManager r03 = new ExtendedGridLayoutManager(r2, 3);
        this.layoutManager = r03;
        r03.setSpanSizeLookup(new AnonymousClass5(this));
        this.recyclerListView.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setClipChildren(false);
        this.recyclerListView.setLayoutManager(r03);
        this.recyclerListView.setSelectorType(9);
        this.recyclerListView.setSelectorDrawableColor(0);
        AnonymousClass6 r04 = new AnonymousClass6(this);
        this.itemAnimator = r04;
        r04.setDelayAnimations(false);
        r04.setSupportsChangeAnimations(false);
        r04.setDurations(350);
        r04.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r04.setDelayIncrement(40);
        this.recyclerListView.setItemAnimator(r04);
        this.recyclerListView.setOnItemClickListener(new GiftSheet$$ExternalSyntheticLambda5(this, r2, r28, r32, r29));
        updatePremiumTiers();
        this.adapter.update(false);
        updateTitle();
        if (BirthdayController.getInstance(r28).isToday(r29) == false) goto L39;
        setBirthday();
    L39:
        NotificationCenter.getInstance(r28).addObserver(this, NotificationCenter.billingProductDetailsUpdated);
        NotificationCenter.getInstance(r28).addObserver(this, NotificationCenter.starGiftsLoaded);
        NotificationCenter.getInstance(r28).addObserver(this, NotificationCenter.userInfoDidLoad);
        NotificationCenter.getInstance(r28).addObserver(this, NotificationCenter.starGiftSoldOut);
        NotificationCenter.getInstance(r28).addObserver(this, NotificationCenter.starUserGiftsLoaded);
        this.actionBar.setTitle(getTitle());
        NotificationCenter.listenEmojiLoading(this.actionBar.getTitleTextView());
        return;
    L30:
        if (r29 >= 0) goto L32;
        r02.addView(r125, LayoutHelper.createLinear(-2, -2, 1, 0, 9, 0, 4));
        NotificationCenter.listenEmojiLoading(r11);
        r11.setText(Emoji.replaceEmoji(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.Gift2StarsChannelInfo, new Object[]{this.name})), r11.getPaint().getFontMetricsInt(), false));
        goto L36
    L32:
        r02.addView(r125, LayoutHelper.createLinear(-1, -2, 1, 0, 9, 0, 6));
        StarsController.GiftsList r05 = StarsController.getInstance(r28).getProfileGiftsList(r29);
        GiftSheet$$ExternalSyntheticLambda3 r06 = new GiftSheet$$ExternalSyntheticLambda3(this, r05, r29, r32, r27);
        r2 = r27;
        r06.run();
        r11.addOnAttachStateChangeListener(new AnonymousClass4(this, r06));
        if (r05.gifts.size() >= 3) goto L35;
        r05.load();
    L35:
        NotificationCenter.getInstance(r28).listen(r11, NotificationCenter.starUserGiftsLoaded, new GiftSheet$$ExternalSyntheticLambda4(r05, r06));
        goto L36
    L24:
        if (r3 == false) goto L26;
        r53 = R.string.Gift2StarsSelf;
        goto L27
    L26:
        r53 = R.string.Gift2Stars;
    L12:
        r15 = null;
        goto L13
    L16:
        TLRPC.Chat r44 = MessagesController.getInstance(r28).getChat(Long.valueOf(-r29));
        if (r44 != null) goto L19;
        String r54 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        this.name = r54;
        r12.setInfo(r44);
        r62.setForUserOrChat(r44, r12);
        goto L21
    L19:
        r54 = r44.title;
        goto L20
    L5:
        r3 = false;
        goto L6
    }

    public GiftSheet(Context r8, int r9, long r10, Utilities.Callback<Boolean> r12) {
        this(r8, r9, r10, null, r12);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(GiftSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(GiftSheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$200(GiftSheet r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ boolean lambda$fillItems$24(TL_stars.StarGift r3) {
        if ((r3 instanceof TL_stars.TL_starGiftUnique) == false) goto L7;
        boolean r2 = this.userSettings.disallow_unique_stargifts;
    L6:
        return !r2;
    L7:
        boolean r0 = r3.limited;
        TLRPC.DisallowedGiftsSettings r22 = this.userSettings;
        if (r0 == true) goto L10;
        r2 = r22.disallow_unlimited_stargifts;
        goto L6
    L10:
        if (r22.disallow_limited_stargifts == true) goto L12;
    L18:
        return true;
    L12:
        if (r3.can_upgrade == true) goto L14;
        return false;
    L14:
        if (r22.disallow_unique_stargifts == false) goto L18;
        return false;
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        if (this.balanceView.lastBalance <= 0) goto L10;
        BaseFragment r2 = LaunchActivity.getLastFragment();
        if (r2 == null) goto L9;
        BaseFragment.BottomSheetParams r32 = new BaseFragment.BottomSheetParams();
        r32.transitionFromLeft = true;
        r32.allowNestedScroll = false;
        r2.showAsSheet(new StarsIntroActivity(), r32);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$new$1(long r1, View r3) {
        BaseFragment r32 = LaunchActivity.getSafeLastFragment();
        if (r32 != null) goto L5;
        return;
    L5:
        dismiss();
        r32.presentFragment(ProfileActivity.of(r1));
    }

    private /* synthetic */ void lambda$new$10(StarGiftSheet r2, long r3, Utilities.Callback r5, Browser.Progress r6) {
        r6.init();
        r2.doTransfer(r3, new GiftSheet$$ExternalSyntheticLambda23(this, r6, r5, r2));
    }

    private /* synthetic */ void lambda$new$12(Utilities.Callback r1, Boolean r2) {
        if (r1 == null) goto L5;
        r1.run(r2);
    L5:
        if (r2.booleanValue() == false) goto L7;
        skipDismissAnimation();
    L7:
        dismiss();
    }

    private /* synthetic */ void lambda$new$13(Utilities.Callback r2) {
        if (r2 == null) goto L4;
        r2.run(Boolean.FALSE);
    L4:
        dismiss();
    }

    private /* synthetic */ void lambda$new$14(Utilities.Callback r2) {
        if (r2 == null) goto L4;
        r2.run(Boolean.FALSE);
    L4:
        dismiss();
    }

    private /* synthetic */ void lambda$new$15(Context r11, int r12, TL_stars.StarGift r13, Utilities.Callback r14) {
        long r5 = this.dialogId;
        GiftSheet$$ExternalSyntheticLambda22 r7 = new GiftSheet$$ExternalSyntheticLambda22(this, r14);
        boolean r142 = r13.limited;
        if (r142 == false) goto L9;
        TLRPC.DisallowedGiftsSettings r3 = this.userSettings;
        if (r3 == null) goto L9;
        if (r3.disallow_limited_stargifts == false) goto L9;
        boolean r8 = true;
    L10:
        if (r142 == false) goto L16;
        TLRPC.DisallowedGiftsSettings r143 = this.userSettings;
        if (r143 == null) goto L16;
        if (r143.disallow_unique_stargifts == false) goto L16;
        boolean r9 = true;
        GiftSheet r1 = this;
        int r32 = r12;
        TL_stars.StarGift r4 = r13;
        Context r2 = r11;
    L17:
        new AnonymousClass10(r1, r2, r32, r4, r5, r7, r8, r9).show();
        return;
    L16:
        r9 = false;
        r2 = r11;
        r32 = r12;
        r4 = r13;
        r1 = this;
    L9:
        r8 = false;
        goto L10
    }

    private /* synthetic */ void lambda$new$16(AlertDialog r1, Utilities.Callback r2, TextView r3, ClickableSpan r4) {
        r1.dismiss();
        if (r2 == null) goto L5;
        r2.run(Boolean.FALSE);
    L5:
        dismiss();
        r4.onClick(r3);
    }

    private /* synthetic */ void lambda$new$17(AlertDialog r1, TLObject r2, Runnable r3, Utilities.Callback r4, TLRPC.TL_error r5) {
        r1.dismiss();
        if ((r2 instanceof TL_stars.checkCanSendGiftResultOk) == false) goto L7;
        r3.run();
        return;
    L7:
        if ((r2 instanceof TL_stars.checkCanSendGiftResultFail) == false) goto L12;
        AlertDialog r12 = new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.GiftLocked)).setMessage(MessageObject.formatTextWithEntities(((TL_stars.checkCanSendGiftResultFail) r2).reason, false)).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
        TextView r22 = r12.getMessageTextView();
        if ((r22 instanceof EffectsTextView) == false) goto L15;
        ((EffectsTextView) r22).setOnLinkPressListener(new GiftSheet$$ExternalSyntheticLambda25(this, r12, r4, r22));
        return;
    L15:
        return;
    L12:
        if (r5 == null) goto L16;
        BulletinFactory.of(this.container, this.resourcesProvider).showForError(r5);
        return;
    }

    private /* synthetic */ void lambda$new$18(AlertDialog r8, Runnable r9, Utilities.Callback r10, TLObject r11, TLRPC.TL_error r12) {
        AndroidUtilities.runOnUIThread(new GiftSheet$$ExternalSyntheticLambda24(this, r8, r11, r9, r10, r12));
    }

    private /* synthetic */ void lambda$new$19(Context r15, int r16, Utilities.Callback r17, long r18, View r20, int r21) {
        UItem r0 = this.adapter.getItem(r21 - 1);
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.instanceOf(GiftCell.Factory.class) == false) goto L69;
        Object r3 = r0.object;
        if ((r3 instanceof GiftPremiumBottomSheet$GiftTier) == false) goto L12;
        new AnonymousClass7(this, r15, r16, (GiftPremiumBottomSheet$GiftTier) r3, this.dialogId, new GiftSheet$$ExternalSyntheticLambda10(this, r17)).show();
        return;
    L12:
        if ((r3 instanceof TL_stars.StarGift) == false) goto L70;
        TL_stars.StarGift r9 = (TL_stars.StarGift) r3;
        StarsController.GiftsList r32 = this.myGifts;
        int r11 = 0;
        if (r32 == null) goto L29;
        if (this.selectedTab != this.TAB_MY_GIFTS) goto L29;
        ArrayList<TL_stars.SavedStarGift> r02 = r32.gifts;
        int r2 = r02.size();
    L19:
        if (r11 >= r2) goto L23;
        TL_stars.SavedStarGift r33 = r02.get(r11);
        r11 = r11 + 1;
        TL_stars.SavedStarGift r34 = r33;
        if (r34.gift.id != r9.id) goto L19;
        TL_stars.SavedStarGift r92 = r34;
    L24:
        if (r92 == null) goto L72;
        StarGiftSheet r22 = new AnonymousClass8(this, getContext(), r16, UserConfig.getInstance(r16).getClientUserId(), this.resourcesProvider).set(r92, null);
        r22.openTransferAlert(r18, new GiftSheet$$ExternalSyntheticLambda11(this, r22, r18, r17));
        return;
    L72:
        return;
    L23:
        r92 = null;
    L29:
        if (r0.accent == false) goto L38;
        if (r9.availability_resale <= 0) goto L38;
        BaseFragment r12 = LaunchActivity.getSafeLastFragment();
        if (r12 == null) goto L73;
        BaseFragment.BottomSheetParams r13 = new BaseFragment.BottomSheetParams();
        r13.transitionFromLeft = true;
        r13.allowNestedScroll = false;
        r13.occupyNavigationBar = true;
        AnonymousClass9 r03 = new AnonymousClass9(this, r18, r9.title, r9.id, this.resourcesProvider, this.container.getViewTreeObserver(), new GiftSheet$$ExternalSyntheticLambda12());
        r03.setCloseParentSheet(new GiftSheet$$ExternalSyntheticLambda13(this, r17));
        r12.showAsSheet(r03, r13);
        return;
    L73:
        return;
    L38:
        if (r9.auction == false) goto L42;
        AuctionJoinSheet.show(r15, this.resourcesProvider, r16, r18, r9.id, new GiftSheet$$ExternalSyntheticLambda14(this, r17));
        return;
    L42:
        if (r9.sold_out == false) goto L46;
        StarsIntroActivity.showSoldOutGiftSheet(r15, r16, r9, this.resourcesProvider);
        return;
    L46:
        if (r9.limited_per_user == true) goto L48;
    L51:
        GiftSheet$$ExternalSyntheticLambda15 r04 = new GiftSheet$$ExternalSyntheticLambda15(this, r15, r16, r9, r17);
        if (r9.locked_until_date <= ConnectionsManager.getInstance(r16).getCurrentTime()) goto L56;
        AlertDialog r23 = new AlertDialog(getContext(), 3);
        r23.showDelayed(500);
        TL_stars.checkCanSendGift r35 = new TL_stars.checkCanSendGift();
        r35.gift_id = r9.id;
        ConnectionsManager.getInstance(r16).sendRequest(r35, new GiftSheet$$ExternalSyntheticLambda16(this, r23, r04, r17));
        return;
    L56:
        if (r9.require_premium == true) goto L58;
    L64:
        r04.run();
        return;
    L58:
        if (UserConfig.getInstance(r16).isPremium() == true) goto L64;
        BaseFragment r5 = LaunchActivity.getSafeLastFragment();
        if (r5 == null) goto L74;
        PremiumPreviewBottomSheet r4 = new PremiumPreviewBottomSheet(r5, r16, null, null, r9, this.resourcesProvider);
        BackupImageView r05 = new BackupImageView(getContext());
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r24 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r05, AndroidUtilities.dp(160.0f), 4);
        r05.setImageDrawable(r24);
        r05.addOnAttachStateChangeListener(new AnonymousClass11(this, r24));
        r24.set(r9.getDocument(), false);
        r4.overrideTitleIcon = r05;
        r4.show();
        r24.play();
        return;
    L74:
        return;
    L48:
        if (r9.per_user_remains > 0) goto L51;
        BulletinFactory.of(this.container, this.resourcesProvider).createSimpleMultiBulletin(r9.getDocument(), AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2PerUserLimit", r9.per_user_total))).show();
        return;
    L70:
        return;
    }

    private /* synthetic */ void lambda$new$3(Utilities.Callback r2, long r3) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        dismiss();
        if (r2 == null) goto L8;
        r2.run(Boolean.FALSE);
    L8:
        Bundle r1 = new Bundle();
        r1.putLong("user_id", r3);
        r1.putBoolean("open_gifts", true);
        r0.presentFragment(new ProfileActivity(r1));
    }

    private /* synthetic */ void lambda$new$5(StarsController.GiftsList r12, long r13, Utilities.Callback r15, Context r16) {
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L36;
        SpannableStringBuilder r2 = new SpannableStringBuilder();
        if (r1 != 1) goto L8;
        String r4 = LocaleController.getString(R.string.Gift2StarsCollectibleInfo);
    L9:
        r2.append(AndroidUtilities.replaceTags(r4));
        r2.append(" ");
        HashSet r5 = new HashSet();
        HashSet r6 = new HashSet();
        int r7 = 0;
    L11:
        if (r7 >= r12.gifts.size()) goto L25;
        if (r5.size() >= 3) goto L25;
        TL_stars.SavedStarGift r8 = r12.gifts.get(r7);
        if (r8 == null) goto L23;
        TL_stars.StarGift r82 = r8.gift;
        if (r82 == null) goto L23;
        TLRPC.Document r83 = r82.getDocument();
        if (r83 == null) goto L23;
        if (r5.contains(Long.valueOf(r83.id)) == true) goto L23;
        r6.add(r83);
        r5.add(Long.valueOf(r83.id));
    L23:
        r7 = r7 + 1;
    L25:
        if (r6.size() <= 0) goto L31;
        SpannableStringBuilder r52 = new SpannableStringBuilder();
        r52.append(LocaleController.formatString(R.string.Gift2StarsInfoProfileLink, new Object[]{DialogObject.getShortName(r13)}).replaceAll(" ", " "));
        r52.append(" ");
        Iterator r42 = r6.iterator();
    L28:
        if (r42.hasNext() == false) goto L30;
        TLRPC.Document r62 = (TLRPC.Document) r42.next();
        r52.append("\u2060e");
        r52.setSpan(new AnimatedEmojiSpan(r62, this.subtitleStarsView.getPaint().getFontMetricsInt()), r52.length() - 1, r52.length(), 33);
        goto L28
    L30:
        r52.append(" >");
        r2.append(AndroidUtilities.replaceArrows(AndroidUtilities.makeClickable(r52, new GiftSheet$$ExternalSyntheticLambda17(this, r15, r13)), true));
    L32:
        if (r1 != 0) goto L34;
        LinkSpanDrawable.LinksTextView r3 = this.subtitleStarsView;
    L35:
        r3.setText(r2);
        r3.setMaxWidth(HintView2.cutInFancyHalf(r3.getText(), r3.getPaint()));
        r1 = r1 + 1;
        goto L4
    L34:
        r3 = this.subtitleCollectiblesStarsView;
        goto L35
    L31:
        r2.append(AndroidUtilities.replaceArrows(AndroidUtilities.makeClickable(LocaleController.getString(R.string.Gift2StarsInfoLink), new GiftSheet$$ExternalSyntheticLambda18(r16)), true));
        goto L32
    L8:
        r4 = LocaleController.formatString(R.string.Gift2StarsInfo, new Object[]{this.name});
        goto L9
    }

    private /* synthetic */ void lambda$new$7(Utilities.Callback r2) {
        if (r2 == null) goto L4;
        r2.run(Boolean.FALSE);
    L4:
        dismiss();
    }

    private /* synthetic */ void lambda$new$9(Browser.Progress r1, Utilities.Callback r2, StarGiftSheet r3, TLRPC.TL_error r4) {
        r1.end();
        if (r2 == null) goto L5;
        r2.run(Boolean.FALSE);
    L5:
        dismiss();
        if (r4 == null) goto L9;
        AndroidUtilities.runOnUIThread(new GiftSheet$$ExternalSyntheticLambda26(r3, r4));
        return;
    L9:
        dismiss();
    }

    private /* synthetic */ void lambda$updatePremiumTiers$21() {
        UniversalAdapter r1 = this.adapter;
        if (r1 == null) goto L6;
        r1.update(false);
        return;
    }

    private /* synthetic */ void lambda$updatePremiumTiers$22(BillingResult r9, List r10) {
        Iterator r92 = r10.iterator();
        long r0 = 0;
    L3:
        int r2 = 0;
        if (r92.hasNext() == false) goto L14;
        ProductDetails r102 = (ProductDetails) r92.next();
        ArrayList<GiftPremiumBottomSheet$GiftTier> r3 = this.premiumTiers;
        int r4 = r3.size();
    L6:
        if (r2 >= r4) goto L3;
        GiftPremiumBottomSheet$GiftTier r5 = r3.get(r2);
        r2 = r2 + 1;
        GiftPremiumBottomSheet$GiftTier r52 = r5;
        if (r52.getStoreProduct() == null) goto L6;
        if (r52.getStoreProduct().equals(r102.getProductId()) == false) goto L6;
        r52.setGooglePlayProductDetails(r102);
        if (r52.getPricePerMonth() <= r0) goto L3;
        r0 = r52.getPricePerMonth();
        goto L3
    L14:
        ArrayList<GiftPremiumBottomSheet$GiftTier> r93 = this.premiumTiers;
        int r103 = r93.size();
    L15:
        if (r2 >= r103) goto L17;
        GiftPremiumBottomSheet$GiftTier r32 = r93.get(r2);
        r2 = r2 + 1;
        r32.setPricePerMonthRegular(r0);
        goto L15
    L17:
        AndroidUtilities.runOnUIThread(new GiftSheet$$ExternalSyntheticLambda9(this));
    }

    private /* synthetic */ void lambda$updatePremiumTiers$23(List r2) {
        if (getContext() != null) goto L5;
        return;
    L5:
        if (isShown() == false) goto L16;
        List<TLRPC.TL_premiumGiftCodeOption> r22 = BoostRepository.filterGiftOptions(r2, 1);
        this.options = r22;
        List<TLRPC.TL_premiumGiftCodeOption> r23 = BoostRepository.filterGiftOptionsByBilling(r22);
        this.options = r23;
        if (r23.isEmpty() == true) goto L14;
        updatePremiumTiers();
        UniversalAdapter r1 = this.adapter;
        if (r1 == null) goto L15;
        r1.update(true);
        return;
    L15:
        return;
    L14:
        return;
    }

    private void selectTab(int r2) {
        if (this.selectedTab != r2) goto L5;
        return;
    L5:
        this.selectedTab = r2;
        this.itemAnimator.endAnimations();
        this.adapter.update(true);
    }

    private void updatePremiumTiers() {
        this.premiumTiers.clear();
        if (this.premiumTiers.isEmpty() == false) goto L44;
        List<TLRPC.TL_premiumGiftCodeOption> r0 = this.options;
        if (r0 == null) goto L44;
        if (r0.isEmpty() == true) goto L44;
        ArrayList r02 = new ArrayList();
        int r2 = this.options.size() - 1;
        long r3 = 0;
    L9:
        if (r2 < 0) goto L36;
        TLRPC.TL_premiumGiftCodeOption r5 = this.options.get(r2);
        if ("XTR".equalsIgnoreCase(r5.currency) == true) goto L34;
        Iterator<TLRPC.TL_premiumGiftCodeOption> r6 = this.options.iterator();
    L15:
        if (r6.hasNext() == false) goto L23;
        TLRPC.TL_premiumGiftCodeOption r8 = r6.next();
        if (r8 == r5) goto L15;
        if ("XTR".equalsIgnoreCase(r8.currency) == false) goto L15;
        if (r8.months != r5.months) goto L15;
    L24:
        GiftPremiumBottomSheet$GiftTier r62 = new GiftPremiumBottomSheet$GiftTier(r5, r8);
        this.premiumTiers.add(r62);
        if (BuildVars.useInvoiceBilling() == false) goto L30;
        if (r62.getPricePerMonth() <= r3) goto L34;
        r3 = r62.getPricePerMonth();
        goto L34
    L30:
        if (r62.getStoreProduct() == null) goto L34;
        if (BillingController.getInstance().isReady() == false) goto L34;
        r02.add(QueryProductDetailsParams.Product.newBuilder().setProductType("inapp").setProductId(r62.getStoreProduct()).build());
        goto L34
    L23:
        r8 = null;
    L34:
        r2 = r2 - 1;
        goto L9
    L36:
        if (BuildVars.useInvoiceBilling() == false) goto L41;
        ArrayList<GiftPremiumBottomSheet$GiftTier> r03 = this.premiumTiers;
        int r22 = r03.size();
        int r52 = 0;
    L38:
        if (r52 >= r22) goto L44;
        GiftPremiumBottomSheet$GiftTier r63 = r03.get(r52);
        r52 = r52 + 1;
        r63.setPricePerMonthRegular(r3);
        goto L38
    L41:
        if (r02.isEmpty() == true) goto L44;
        System.currentTimeMillis();
        BillingController.getInstance().queryProductDetails(r02, new GiftSheet$$ExternalSyntheticLambda6(this));
    L44:
        if (this.premiumTiers.isEmpty() == false) goto L63;
        BoostRepository.loadGiftOptions(this.currentAccount, null, new GiftSheet$$ExternalSyntheticLambda7(this));
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r9) {
        UniversalAdapter r0 = new UniversalAdapter(this.recyclerListView, getContext(), this.currentAccount, 0, true, new GiftSheet$$ExternalSyntheticLambda8(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r4, int r5, Object... r6) {
        if (r4 != NotificationCenter.billingProductDetailsUpdated) goto L7;
        updatePremiumTiers();
        return;
    L7:
        if (r4 != NotificationCenter.starGiftsLoaded) goto L13;
        UniversalAdapter r3 = this.adapter;
        if (r3 == null) goto L68;
        r3.update(true);
        return;
    L68:
        return;
    L13:
        if (r4 != NotificationCenter.userInfoDidLoad) goto L52;
        if (isShown() == false) goto L76;
        long r42 = ((Long) r6[0]).longValue();
        long r1 = this.dialogId;
        if (r42 == r1) goto L20;
    L43:
        ArrayList<GiftPremiumBottomSheet$GiftTier> r43 = this.premiumTiers;
        if (r43 != null) goto L46;
    L47:
        updatePremiumTiers();
        UniversalAdapter r32 = this.adapter;
        if (r32 == null) goto L71;
        r32.update(true);
        return;
    L71:
        return;
    L46:
        if (r43.isEmpty() == true) goto L47;
        return;
    L20:
        if (r1 <= 0) goto L43;
        TLRPC.UserFull r44 = MessagesController.getInstance(this.currentAccount).getUserFull(this.dialogId);
        if (this.dialogId == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L25;
        if (r44 == null) goto L25;
        TLRPC.DisallowedGiftsSettings r45 = r44.disallowed_stargifts;
    L26:
        this.userSettings = r45;
        if (r45 != null) goto L29;
    L40:
        UniversalAdapter r46 = this.adapter;
        if (r46 == null) goto L43;
        r46.update(true);
        goto L43
    L29:
        if (r45.disallow_premium_gifts == false) goto L40;
        if (r45.disallow_unique_stargifts == false) goto L40;
        if (r45.disallow_limited_stargifts == false) goto L40;
        if (r45.disallow_unlimited_stargifts == false) goto L40;
        dismiss();
        BaseFragment r47 = LaunchActivity.getSafeLastFragment();
        if (r47 == null) goto L69;
        BulletinFactory.of(r47).createSimpleBulletin(R.raw.error, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserDisallowedGifts, new Object[]{DialogObject.getShortName(this.dialogId)}))).show();
        return;
    L69:
        return;
    L25:
        r45 = null;
        goto L26
    L76:
        return;
    L52:
        if (r4 != NotificationCenter.starGiftSoldOut) goto L61;
        if (isShown() == false) goto L77;
        TL_stars.StarGift r48 = (TL_stars.StarGift) r6[0];
        BulletinFactory.of(this.container, this.resourcesProvider).createEmojiBulletin(r48.sticker, LocaleController.getString(R.string.Gift2SoldOutTitle), AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("Gift2SoldOutCount", r48.availability_total))).show();
        UniversalAdapter r33 = this.adapter;
        if (r33 == null) goto L72;
        r33.update(true);
        return;
    L72:
        return;
    L77:
        return;
    L61:
        if (r4 == NotificationCenter.starUserGiftsLoaded) goto L63;
        return;
    L63:
        if (r6[1] != this.myGifts) goto L74;
        UniversalAdapter r34 = this.adapter;
        if (r34 == null) goto L75;
        r34.update(true);
        return;
    L75:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.billingProductDetailsUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starGiftsLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.userInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starGiftSoldOut);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.starUserGiftsLoaded);
    }

    public void fillItems(ArrayList<UItem> r23, UniversalAdapter r24) {
        long r3 = 0;
        if (this.self == false) goto L5;
    L19:
        boolean r2 = false;
    L20:
        StarsController r8 = StarsController.getInstance(this.currentAccount);
        if (this.birthday == false) goto L23;
        ArrayList<TL_stars.StarGift> r9 = r8.birthdaySortedGifts;
    L25:
        if (this.userSettings == null) goto L28;
        r9 = (ArrayList) r9.stream().filter(new GiftSheet$$ExternalSyntheticLambda19(this)).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
    L28:
        if (this.dialogId >= 0) goto L31;
        r9 = (ArrayList) r9.stream().filter(new GiftSheet$$ExternalSyntheticLambda20()).collect(Collectors.toCollection(new ExportMapper$$ExternalSyntheticLambda2()));
    L31:
        if (this.dialogId == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L39;
        StarsController.GiftsList r10 = this.myGifts;
        if (r10 == null) goto L39;
        ArrayList<TL_stars.SavedStarGift> r102 = r10.gifts;
        int r11 = r102.size();
        int r12 = 0;
    L35:
        if (r12 >= r11) goto L39;
        TL_stars.SavedStarGift r13 = r102.get(r12);
        r12 = r12 + 1;
        if ((r13.gift instanceof TL_stars.TL_starGiftUnique) == false) goto L35;
        boolean r103 = true;
    L40:
        float r122 = 300.0f;
        if (MessagesController.getInstance(this.currentAccount).stargiftsBlocked == false) goto L43;
    L156:
        TLRPC.DisallowedGiftsSettings r0 = this.userSettings;
        if (r0 != null) goto L159;
        return;
    L159:
        if (r0.disallow_unique_stargifts == false) goto L161;
        return;
    L161:
        if (r9.isEmpty() == false) goto L180;
        r23.add(UItem.asSpace(AndroidUtilities.dp(300.0f)));
        return;
    L180:
        return;
    L43:
        if (r9.isEmpty() == false) goto L52;
        TLRPC.DisallowedGiftsSettings r112 = this.userSettings;
        if (r112 == null) goto L156;
        if (r112.disallow_unique_stargifts == true) goto L156;
        StarsController.GiftsList r113 = this.myGifts;
        if (r113 == null) goto L156;
        if (r113.gifts.isEmpty() == true) goto L156;
    L52:
        if (r2 == true) goto L54;
        r23.add(UItem.asCustom(this.topView));
    L55:
        r23.add(UItem.asCustom(this.starsHeaderView));
        TreeSet r22 = new TreeSet();
        TLRPC.DisallowedGiftsSettings r114 = this.userSettings;
        if (r114 != null) goto L58;
    L59:
        int r115 = 0;
    L61:
        if (r115 >= r9.size()) goto L63;
        r22.add(Long.valueOf(r9.get(r115).stars));
        r115 = r115 + 1;
    L63:
        ArrayList r25 = new ArrayList();
        this.TAB_MY_GIFTS = -1;
        this.TAB_LIMITED = -1;
        this.TAB_IN_STOCK = -1;
        this.TAB_ALL = -1;
        if (r9.isEmpty() == true) goto L66;
        this.TAB_ALL = r25.size();
        r25.add(LocaleController.getString(R.string.Gift2TabAll));
    L66:
        TLRPC.DisallowedGiftsSettings r116 = this.userSettings;
        if (r116 != null) goto L69;
    L70:
        if (r103 == false) goto L72;
        this.TAB_MY_GIFTS = r25.size();
        r25.add(LocaleController.getString(R.string.Gift2TabMine));
    L72:
        this.TAB_COLLECTIBLES = r25.size();
        r25.add(LocaleController.getString(R.string.Gift2TabCollectibles));
        r23.add(Tabs.Factory.asTabs(1, r25, this.selectedTab, new GiftSheet$$ExternalSyntheticLambda21(this)));
        if (this.selectedTab == this.TAB_COLLECTIBLES) goto L75;
    L79:
        boolean r26 = false;
    L80:
        setShowCollectiblesInfo(r26);
        if (this.myGifts != null) goto L83;
    L89:
        int r27 = 0;
        int r104 = 0;
    L91:
        if (r27 >= r9.size()) goto L139;
        TL_stars.StarGift r14 = r9.get(r27);
        int r132 = this.selectedTab;
        if (r132 != this.TAB_ALL) goto L95;
    L105:
        long r20 = r3;
    L107:
        if (r14.sold_out == false) goto L109;
    L125:
        int r133 = this.selectedTab;
        if (r133 != this.TAB_MY_GIFTS) goto L128;
        boolean r15 = true;
    L130:
        if (r14.limited == false) goto L136;
        TLRPC.DisallowedGiftsSettings r32 = this.userSettings;
        if (r32 == null) goto L136;
        if (r32.disallow_limited_stargifts == false) goto L136;
        boolean r16 = true;
    L137:
        r23.add(GiftCell.Factory.asStarGift(r133, r14, r15, r16, true, false, false));
        r104 = r104 + 1;
    L138:
        r27 = r27 + 1;
        r3 = r20;
    L136:
        r16 = false;
        goto L137
    L128:
        r15 = false;
        goto L130
    L109:
        if (r14.availability_resale <= r20) goto L125;
        if (r132 == this.TAB_COLLECTIBLES) goto L125;
        if (r132 != this.TAB_MY_GIFTS) goto L115;
        boolean r152 = true;
    L117:
        if (r14.limited == false) goto L123;
        TLRPC.DisallowedGiftsSettings r33 = this.userSettings;
        if (r33 == null) goto L123;
        if (r33.disallow_limited_stargifts == false) goto L123;
        boolean r162 = true;
    L124:
        r23.add(GiftCell.Factory.asStarGift(r132, r14, r152, r162, false, false, false));
        r104 = r104 + 1;
    L123:
        r162 = false;
        goto L124
    L115:
        r152 = false;
        goto L117
    L95:
        if (r132 == this.TAB_MY_GIFTS) goto L105;
        r20 = r3;
        if (r132 != this.TAB_COLLECTIBLES) goto L138;
        if (r14.availability_resale > r20) goto L107;
        if (r14.require_premium == true) goto L107;
        if (r14.locked_until_date == 0) goto L138;
    L139:
        int r28 = this.selectedTab;
        int r34 = this.TAB_MY_GIFTS;
        if (r28 != r34) goto L146;
        StarsController.GiftsList r02 = this.myGifts;
        if (r02 == null) goto L146;
        if (r02.endReached == true) goto L146;
        r02.load();
        r23.add(UItem.asFlicker(4, 34).setSpanCount(1));
        r23.add(UItem.asFlicker(5, 34).setSpanCount(1));
        r23.add(UItem.asFlicker(6, 34).setSpanCount(1));
    L151:
        if (r104 < 9) goto L154;
        r122 = 40.0f;
    L154:
        r23.add(UItem.asSpace(AndroidUtilities.dp(r122)));
        return;
    L146:
        if (r28 == r34) goto L151;
        if (r8.giftsLoading == false) goto L151;
        r23.add(UItem.asFlicker(4, 34).setSpanCount(1));
        r23.add(UItem.asFlicker(5, 34).setSpanCount(1));
        r23.add(UItem.asFlicker(6, 34).setSpanCount(1));
        goto L151
    L83:
        if (this.selectedTab != this.TAB_MY_GIFTS) goto L89;
        r9 = new ArrayList();
        ArrayList<TL_stars.SavedStarGift> r29 = this.myGifts.gifts;
        int r105 = r29.size();
        int r117 = 0;
    L85:
        if (r117 >= r105) goto L89;
        TL_stars.SavedStarGift r134 = r29.get(r117);
        r117 = r117 + 1;
        TL_stars.StarGift r135 = r134.gift;
        if ((r135 instanceof TL_stars.TL_starGiftUnique) == false) goto L85;
        r9.add(r135);
        goto L85
    L75:
        if (this.self == true) goto L79;
        if (this.dialogId < 0) goto L79;
        r26 = true;
        goto L80
    L69:
        if (r116.disallow_unique_stargifts == true) goto L72;
    L58:
        if (r114.disallow_unique_stargifts == true) goto L63;
    L54:
        r23.add(UItem.asSpace(AndroidUtilities.dp(16.0f)));
    L39:
        r103 = false;
        goto L40
    L23:
        r9 = r8.sortedGifts;
        goto L25
    L5:
        if (this.dialogId < 0) goto L19;
        TLRPC.DisallowedGiftsSettings r210 = this.userSettings;
        if (r210 != null) goto L9;
    L10:
        r23.add(UItem.asCustom(this.topView));
        r23.add(UItem.asCustom(this.premiumHeaderView));
        ArrayList<GiftPremiumBottomSheet$GiftTier> r211 = this.premiumTiers;
        if (r211 != null) goto L13;
    L17:
        r23.add(UItem.asFlicker(1, 34).setSpanCount(1));
        r23.add(UItem.asFlicker(2, 34).setSpanCount(1));
        r23.add(UItem.asFlicker(3, 34).setSpanCount(1));
    L18:
        r2 = true;
        goto L20
    L13:
        if (r211.isEmpty() == true) goto L17;
        ArrayList<GiftPremiumBottomSheet$GiftTier> r212 = this.premiumTiers;
        int r82 = r212.size();
        int r92 = 0;
    L15:
        if (r92 >= r82) goto L18;
        GiftPremiumBottomSheet$GiftTier r106 = r212.get(r92);
        r92 = r92 + 1;
        r23.add(GiftCell.Factory.asPremiumGift(r106));
        goto L15
    L9:
        if (r210.disallow_premium_gifts == true) goto L19;
        goto L10
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        if (this.self == false) goto L7;
        return LocaleController.getString(R.string.Gift2TitleSelf1);
    L7:
        return Emoji.replaceEmoji(LocaleController.formatString(R.string.Gift2User, new Object[]{this.name}), null, false);
    }

    public GiftSheet setBirthday() {
        return setBirthday(true);
    }

    public GiftSheet setBirthday(boolean r2) {
        this.birthday = r2;
        this.adapter.update(false);
        return this;
    }

    public void setShowCollectiblesInfo(boolean r8) {
        if (r8 != this.shownCollectiblesInfo) goto L5;
        return;
    L5:
        this.shownCollectiblesInfo = r8;
        ViewPropertyAnimator r0 = this.subtitleStarsView.animate();
        float r1 = 0.0f;
        float r2 = 1.0f;
        if (r8 == true) goto L8;
        float r3 = 1.0f;
    L9:
        ViewPropertyAnimator r02 = r0.alpha(r3);
        if (r8 == true) goto L12;
        float r4 = 1.0f;
    L13:
        ViewPropertyAnimator r03 = r02.scaleX(r4);
        if (r8 == true) goto L16;
        float r42 = 1.0f;
    L17:
        ViewPropertyAnimator r04 = r03.scaleY(r42).setDuration(380);
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r04.setInterpolator(r6).start();
        ViewPropertyAnimator r7 = this.subtitleCollectiblesStarsView.animate();
        if (r8 == false) goto L20;
        r1 = 1.0f;
    L20:
        ViewPropertyAnimator r72 = r7.alpha(r1);
        if (r8 == false) goto L23;
        float r05 = 1.0f;
    L24:
        ViewPropertyAnimator r73 = r72.scaleX(r05);
        if (r8 == true) goto L28;
        r2 = 0.85f;
    L28:
        r73.scaleY(r2).setDuration(380).setInterpolator(r6).start();
        return;
    L23:
        r05 = 0.85f;
        goto L24
    L16:
        r42 = 0.85f;
        goto L17
    L12:
        r4 = 0.85f;
        goto L13
    L8:
        r3 = 0.0f;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L6;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L6:
        TLRPC.DisallowedGiftsSettings r0 = this.userSettings;
        if (r0 != null) goto L9;
    L20:
        super.show();
        return;
    L9:
        if (r0.disallow_premium_gifts == false) goto L20;
        if (r0.disallow_unique_stargifts == false) goto L20;
        if (r0.disallow_limited_stargifts == false) goto L20;
        if (r0.disallow_unlimited_stargifts == false) goto L20;
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        if (r02 == null) goto L22;
        BulletinFactory.of(r02).createSimpleBulletin(R.raw.error, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserDisallowedGifts, new Object[]{DialogObject.getShortName(this.dialogId)}))).show();
        return;
    }
}
