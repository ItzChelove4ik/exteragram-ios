package org.telegram.ui.Stars;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RecordingCanvas;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ExtendedGridLayoutManager;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.ShareAlert$$ExternalSyntheticLambda16;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProviderThemed;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.glass.GlassTabView;
import org.telegram.ui.Components.glass.GlassTabsView;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.bots.AffiliateProgramFragment;

/* loaded from: classes7.dex */
public class StarGiftPreviewSheet extends BottomSheetWithRecyclerListView {
    private UniversalAdapter adapter;
    private final ImageView backButton;
    private final ArrayList<TL_stars.starGiftAttributeBackdrop> backdrops;
    private final ArrayList<RectF> blurredPositions;
    public final Button[] buttons;
    private final LinearLayout buttonsLayout;
    private final boolean crafting;
    private final int currentAccount;
    private final TextView giftNameTextView;
    private final TextView giftStatusTextView;
    private final BlurredBackgroundDrawableViewFactory glassFactory;
    private final BlurredBackgroundSourceColor glassSourceFallback;
    private final BlurredBackgroundSourceRenderNode glassSourceRenderNode;
    private final View gradientTop;
    private boolean gradientVisible;
    private final ImageView headerPlay;
    private final FrameLayout headerView;
    private final DefaultItemAnimator itemAnimator;
    private int lastBottomInset;
    private final ExtendedGridLayoutManager layoutManager;
    private Mode mode;
    private final ArrayList<TL_stars.starGiftAttributeModel> models;
    private final ArrayList<TL_stars.starGiftAttributePattern> patterns;
    private final BagRandomizer<TL_stars.starGiftAttributeBackdrop> rBackdrops;
    private final BagRandomizer<TL_stars.starGiftAttributeModel> rModels;
    private final BagRandomizer<TL_stars.starGiftAttributePattern> rPatterns;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private Attributes selectedAttributes;
    private final ArrayList<TL_stars.starGiftAttributeModel> simpleModels;
    private final PointF tabsPosP;
    private final RectF tabsRectF;
    private final TabsSelectorView tabsSelectorView;
    private final StarGiftSheet.TopView topView;
    private final ViewGroupPartRenderer viewGroupPartRenderer;

    /* renamed from: org.telegram.ui.Stars.StarGiftPreviewSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ StarGiftPreviewSheet this$0;

        public AnonymousClass1(StarGiftPreviewSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            if (StarGiftPreviewSheet.m18914$$Nest$fgetadapter(this.this$0) == null) goto L15;
            if (r2 == 0) goto L15;
            UItem r22 = StarGiftPreviewSheet.m18914$$Nest$fgetadapter(this.this$0).getItem(r2 - 1);
            if (r22 == null) goto L13;
            int r23 = r22.spanCount;
            if (r23 == (-1)) goto L13;
            return r23;
        L13:
            return StarGiftPreviewSheet.m18917$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        L15:
            return StarGiftPreviewSheet.m18917$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftPreviewSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ StarGiftPreviewSheet this$0;

        public AnonymousClass2(StarGiftPreviewSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            super.onScrolled(r2, r3, r4);
            StarGiftPreviewSheet.m18930$$Nest$mupdateTranslationHeader(this.this$0);
            if (Build.VERSION.SDK_INT >= 31) goto L5;
            return;
        L5:
            if (StarGiftPreviewSheet.m18919$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L9;
            StarGiftPreviewSheet.m18919$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            StarGiftPreviewSheet.m18923$$Nest$minvalidateMergedVisibleBlurredPositionsAndSources(this.this$0, 1);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftPreviewSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends DefaultItemAnimator {
        final /* synthetic */ StarGiftPreviewSheet this$0;

        public AnonymousClass3(StarGiftPreviewSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public float animateByScale(View r1) {
            return 0.3f;
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftPreviewSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends StarGiftSheet.TopView {
        final float[] hsv;
        Path path;
        float[] r;
        final /* synthetic */ StarGiftPreviewSheet this$0;

        public AnonymousClass4(StarGiftPreviewSheet r1, Context r2, Theme.ResourcesProvider r3, Runnable r4, View.OnClickListener r5, View.OnClickListener r6, View.OnClickListener r7, View.OnClickListener r8, View.OnClickListener r9, View.OnClickListener r10, View.OnClickListener r11) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
            this.hsv = new float[3];
            this.path = new Path();
            this.r = new float[8];
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            r2.save();
            r2.clipPath(this.path);
            super.dispatchDraw(r2);
            r2.restore();
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public int getFinalHeight() {
            return AndroidUtilities.dp(315.0f);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public float getRealHeight() {
            return AndroidUtilities.dp(315.0f);
        }

        @Override // android.view.View
        public void onSizeChanged(int r8, int r9, int r10, int r11) {
            super.onSizeChanged(r8, r9, r10, r11);
            float[] r102 = this.r;
            float r112 = AndroidUtilities.dp(12.0f);
            r102[3] = r112;
            r102[2] = r112;
            r102[1] = r112;
            r102[0] = r112;
            this.path.rewind();
            this.path.addRoundRect(0.0f, 0.0f, r8, r9, this.r, Path.Direction.CW);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public void onSwitchPage(StarGiftSheet.PageTransition r1) {
            super.onSwitchPage(r1);
            StarGiftPreviewSheet.m18928$$Nest$mupdateHeaderAttributes(this.this$0, true);
        }

        @Override // org.telegram.ui.Stars.StarGiftSheet.TopView
        public void updateButtonsBackgrounds(int r11) {
            super.updateButtonsBackgrounds(r11);
            if (StarGiftPreviewSheet.m18915$$Nest$fgetbackButton(this.this$0) == null) goto L8;
            if (Theme.setSelectorDrawableColor(StarGiftPreviewSheet.m18915$$Nest$fgetbackButton(this.this$0).getBackground(), r11, false) == false) goto L8;
            StarGiftPreviewSheet.m18915$$Nest$fgetbackButton(this.this$0).invalidate();
        L8:
            if (StarGiftPreviewSheet.m18916$$Nest$fgetheaderPlay(this.this$0) != null) goto L10;
        L12:
            Button[] r0 = this.this$0.buttons;
            int r2 = r0.length;
            int r3 = 0;
        L13:
            if (r3 >= r2) goto L24;
            Button r4 = r0[r3];
            if (Theme.setSelectorDrawableColor(r4.getBackground(), r11, false) == false) goto L17;
            r4.invalidate();
        L17:
            Color.colorToHSV(ColorUtils.blendARGB(r11, -1, 0.33f), this.hsv);
            float[] r5 = this.hsv;
            r5[1] = Math.min(1.0f, r5[1] * 1.1f);
            float[] r52 = this.hsv;
            r52[2] = Math.min(1.0f, r52[2] * 1.1f);
            int r53 = Color.HSVToColor(this.hsv);
            boolean r6 = r4.percentView.getSizeableBackground() instanceof StarGiftSheet.RoundRectStrokeDrawable;
            AnimatedTextView r7 = r4.percentView;
            if (r6 == false) goto L21;
            ((StarGiftSheet.RoundRectStrokeDrawable) r7.getSizeableBackground()).setColor(r53);
            r4.percentView.invalidate();
        L23:
            r3 = r3 + 1;
            goto L13
        L21:
            if (Theme.setSelectorDrawableColor(r7.getSizeableBackground(), r53, false) == false) goto L23;
            r4.percentView.invalidate();
            goto L23
        L24:
            return;
        L10:
            if (Theme.setSelectorDrawableColor(StarGiftPreviewSheet.m18916$$Nest$fgetheaderPlay(this.this$0).getBackground(), r11, false) == false) goto L12;
            StarGiftPreviewSheet.m18916$$Nest$fgetheaderPlay(this.this$0).invalidate();
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftPreviewSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends UniversalAdapter {
        final /* synthetic */ StarGiftPreviewSheet this$0;

        public static /* synthetic */ void $r8$lambda$vbttV1S8R2kaf3cqov_a9Ha1d0I(AnonymousClass5 r0, Attributes r1, View r2) {
            r0.lambda$onBindViewHolder$0(r1, r2);
        }

        public AnonymousClass5(StarGiftPreviewSheet r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(Attributes r5, View r6) {
            if (StarGiftPreviewSheet.m18918$$Nest$fgetmode(this.this$0) != Mode.RANDOM) goto L5;
            StarGiftPreviewSheet r62 = this.this$0;
            StarGiftPreviewSheet.m18922$$Nest$fputselectedAttributes(r62, new Attributes(StarGiftPreviewSheet.m18921$$Nest$fgettopView(r62).getUpgradeBackdropAttribute(), StarGiftPreviewSheet.m18921$$Nest$fgettopView(this.this$0).getUpgradePatternAttribute(), StarGiftPreviewSheet.m18921$$Nest$fgettopView(this.this$0).getUpgradeImageViewAttribute()));
            StarGiftPreviewSheet.m18927$$Nest$msetMode(this.this$0, Mode.SELECTED);
        L5:
            StarGiftPreviewSheet r63 = this.this$0;
            StarGiftPreviewSheet.m18922$$Nest$fputselectedAttributes(r63, StarGiftPreviewSheet.m18926$$Nest$mnewSelectedWithCurrentTab(r63, r5));
            StarGiftPreviewSheet.m18921$$Nest$fgettopView(this.this$0).setPreviewAttributes(StarGiftPreviewSheet.m18920$$Nest$fgetselectedAttributes(this.this$0));
            StarGiftPreviewSheet.m18929$$Nest$mupdateSelectedForVisibleViews(this.this$0);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            super.onBindViewHolder(r3, r4);
            View r32 = r3.itemView;
            if ((r32 instanceof GiftAttributeCell) == true) goto L5;
            return;
        L5:
            GiftAttributeCell r33 = (GiftAttributeCell) r32;
            Attributes r42 = GiftAttributeCell.m18931$$Nest$fgetattributes(r33);
            r33.setSelected(StarGiftPreviewSheet.m18925$$Nest$misSelectedWithCurrentTab(this.this$0, r42), false);
            r33.setOnClickListener(new StarGiftPreviewSheet$5$$ExternalSyntheticLambda0(this, r42));
        }
    }

    /* renamed from: org.telegram.ui.Stars.StarGiftPreviewSheet$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerListView {
        final /* synthetic */ StarGiftPreviewSheet this$0;

        public AnonymousClass6(StarGiftPreviewSheet r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean canHighlightChildAt(View r1, float r2, float r3) {
            return StarGiftPreviewSheet.access$000(this.this$0, r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            this.this$0.applyScrolledPosition();
            super.onLayout(r2, r3, r4, r5, r6);
            StarGiftPreviewSheet.m18924$$Nest$minvalidateMergedVisibleBlurredPositionsAndSourcesImpl(this.this$0, 2);
        }
    }

    public static class Attributes {
        public final TL_stars.starGiftAttributeBackdrop backdrop;
        public final TL_stars.starGiftAttributeModel model;
        public final TL_stars.starGiftAttributePattern pattern;

        public Attributes(TL_stars.starGiftAttributeBackdrop r1, TL_stars.starGiftAttributePattern r2, TL_stars.starGiftAttributeModel r3) {
            this.backdrop = r1;
            this.pattern = r2;
            this.model = r3;
        }
    }

    public static class Button extends FrameLayout {
        public AnimatedTextView percentView;
        public TextView textView;
        public AnimatedTextView titleView;

        public Button(Context r12) {
            super(r12);
            setClipChildren(false);
            AnimatedTextView r1 = new AnimatedTextView(r12, true, false, false);
            this.titleView = r1;
            r1.setTypeface(AndroidUtilities.bold());
            this.titleView.setTextSize(AndroidUtilities.dp(13.0f));
            this.titleView.setTextColor(-1);
            this.titleView.setGravity(17);
            addView(this.titleView, LayoutHelper.createFrame(-1, 16.0f, 49, 4.0f, 6.0f, 4.0f, 0.0f));
            TextView r0 = new TextView(r12);
            this.textView = r0;
            r0.setTextSize(1, 12.0f);
            this.textView.setTextColor(-1879048193);
            this.textView.setGravity(17);
            addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 49, 4.0f, 20.0f, 4.0f, 0.0f));
            AnimatedTextView r02 = new AnimatedTextView(r12);
            this.percentView = r02;
            r02.setTypeface(AndroidUtilities.bold());
            this.percentView.setTextColor(-1);
            this.percentView.setGravity(5);
            this.percentView.getDrawable().centerY = true;
            this.percentView.setTextSize(AndroidUtilities.dp(11.0f));
            this.percentView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(1.0f));
            this.percentView.setSizeableBackground(new StarGiftSheet.RoundRectStrokeDrawable(AndroidUtilities.dp(10.0f), 285212671));
            addView(this.percentView, LayoutHelper.createFrame(-1, 16.0f, 53, 0.0f, -9.0f, -4.0f, 0.0f));
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class GiftAttributeCell extends FrameLayout implements FactorAnimator.Target {
        private Attributes attributes;
        private final GiftSheet.CardBackground cardBackground;
        private final FrameLayout cardBackgroundView;
        private final int currentAccount;
        private final BackupImageView imageView;
        private final BoolAnimator isSelected;
        private TLRPC.Document lastDocument;
        private boolean noPercentageBackground;
        private final TextView percentageView;
        private Integer rarityColor;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView textView;

        public static class Factory extends UItem.UItemFactory<GiftAttributeCell> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem asAttribute(int r2, Attributes r3) {
                UItem r0 = UItem.ofFactory(Factory.class).setSpanCount(1);
                r0.intValue = r2;
                r0.object = r3;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r6, UItem r7, boolean r8, UniversalAdapter r9, UniversalRecyclerView r10) {
                GiftAttributeCell r62 = (GiftAttributeCell) r6;
                Attributes r5 = (Attributes) r7.object;
                int r82 = r7.intValue;
                Integer[] r102 = new Integer[1];
                if (r82 != 0) goto L5;
                boolean r1 = true;
            L6:
                GiftAttributeCell.m18938$$Nest$fputnoPercentageBackground(r62, r1);
                GiftAttributeCell.m18937$$Nest$fputattributes(r62, r5);
                int r12 = -1;
                if (r82 != 0) goto L10;
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).setBackdrop(null);
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).setPattern(null);
                GiftAttributeCell.m18936$$Nest$fgettextView(r62).setText(r5.model.name);
                GiftAttributeCell.m18941$$Nest$msetSticker(r62, r5.model.document, 80, r7.object, true);
                GiftAttributeCell.m18933$$Nest$fgetimageView(r62).setColorFilter(null);
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).selectedColorKey = Theme.key_featuredStickers_addButton;
                CharSequence r52 = StarGiftSheet.getRarityName(r5.model.rarity, r102);
            L16:
                TextView r72 = GiftAttributeCell.m18936$$Nest$fgettextView(r62);
                if (r82 != 0) goto L19;
                r12 = Theme.getColor(Theme.key_dialogTextBlack, GiftAttributeCell.m18935$$Nest$fgetresourcesProvider(r62));
            L19:
                r72.setTextColor(r12);
                GiftAttributeCell.m18934$$Nest$fgetpercentageView(r62).setText(r52);
                GiftAttributeCell.m18939$$Nest$fputrarityColor(r62, r102[0]);
                GiftAttributeCell.m18940$$Nest$mcheckPercentageViewBackground(r62);
                return;
            L10:
                if (r82 != 1) goto L13;
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).setBackdrop(r5.backdrop);
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).setPattern(r5.pattern);
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).selectedColorKey = Theme.key_windowBackgroundWhite;
                GiftAttributeCell.m18936$$Nest$fgettextView(r62).setText(r5.backdrop.name);
                GiftAttributeCell.m18941$$Nest$msetSticker(r62, r5.pattern.document, 48, r7.object, false);
                GiftAttributeCell.m18933$$Nest$fgetimageView(r62).setColorFilter(new PorterDuffColorFilter(ColorUtils.setAlphaComponent(r5.backdrop.pattern_color, 64), PorterDuff.Mode.SRC_IN));
                r52 = StarGiftSheet.getRarityName(r5.backdrop.rarity, r102);
                goto L16
            L13:
                if (r82 != 2) goto L15;
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).setBackdrop(r5.backdrop);
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).setPattern(r5.pattern);
                GiftAttributeCell.m18932$$Nest$fgetcardBackground(r62).selectedColorKey = Theme.key_windowBackgroundWhite;
                GiftAttributeCell.m18936$$Nest$fgettextView(r62).setText(r5.pattern.name);
                GiftAttributeCell.m18941$$Nest$msetSticker(r62, r5.pattern.document, 64, r7.object, false);
                GiftAttributeCell.m18933$$Nest$fgetimageView(r62).setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
                r52 = StarGiftSheet.getRarityName(r5.pattern.rarity, r102);
                goto L16
            L15:
                r52 = _UrlKt.FRAGMENT_ENCODE_SET;
                goto L16
            L5:
                r1 = false;
                goto L6
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public GiftAttributeCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new GiftAttributeCell(r1, r3, r5);
            }
        }

        /* renamed from: -$$Nest$fgetattributes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Attributes m18931$$Nest$fgetattributes(GiftAttributeCell r0) {
            return r0.attributes;
        }

        /* renamed from: -$$Nest$fgetcardBackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GiftSheet.CardBackground m18932$$Nest$fgetcardBackground(GiftAttributeCell r0) {
            return r0.cardBackground;
        }

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m18933$$Nest$fgetimageView(GiftAttributeCell r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgetpercentageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m18934$$Nest$fgetpercentageView(GiftAttributeCell r0) {
            return r0.percentageView;
        }

        /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m18935$$Nest$fgetresourcesProvider(GiftAttributeCell r0) {
            return r0.resourcesProvider;
        }

        /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m18936$$Nest$fgettextView(GiftAttributeCell r0) {
            return r0.textView;
        }

        /* renamed from: -$$Nest$fputattributes, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18937$$Nest$fputattributes(GiftAttributeCell r0, Attributes r1) {
            r0.attributes = r1;
        }

        /* renamed from: -$$Nest$fputnoPercentageBackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18938$$Nest$fputnoPercentageBackground(GiftAttributeCell r0, boolean r1) {
            r0.noPercentageBackground = r1;
        }

        /* renamed from: -$$Nest$fputrarityColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18939$$Nest$fputrarityColor(GiftAttributeCell r0, Integer r1) {
            r0.rarityColor = r1;
        }

        /* renamed from: -$$Nest$mcheckPercentageViewBackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18940$$Nest$mcheckPercentageViewBackground(GiftAttributeCell r0) {
            r0.checkPercentageViewBackground();
        }

        /* renamed from: -$$Nest$msetSticker, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18941$$Nest$msetSticker(GiftAttributeCell r0, TLRPC.Document r1, int r2, Object r3, boolean r4) {
            r0.setSticker(r1, r2, r3, r4);
        }

        public GiftAttributeCell(Context r11, int r12, Theme.ResourcesProvider r13) {
            super(r11);
            this.isSelected = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 320);
            this.currentAccount = r12;
            this.resourcesProvider = r13;
            FrameLayout r10 = new FrameLayout(r11);
            this.cardBackgroundView = r10;
            GiftSheet.CardBackground r122 = new GiftSheet.CardBackground(r10, r13, true);
            this.cardBackground = r122;
            r10.setBackground(r122);
            r122.selectionStyle = 1;
            addView(r10, LayoutHelper.createFrame(-1, -1, 119));
            BackupImageView r102 = new BackupImageView(r11);
            this.imageView = r102;
            r102.getImageReceiver().setAutoRepeat(0);
            addView(r102, LayoutHelper.createFrame(80, 80.0f, 49, 0.0f, 17.0f, 0.0f, 0.0f));
            TextView r103 = new TextView(r11);
            this.textView = r103;
            r103.setTypeface(AndroidUtilities.bold());
            r103.setTextSize(1, 13.0f);
            r103.setGravity(17);
            r103.setEllipsize(TextUtils.TruncateAt.END);
            r103.setTextColor(-1);
            addView(r103, LayoutHelper.createFrame(-1, -2.0f, 0, 12.0f, 106.0f, 12.0f, 14.0f));
            TextView r104 = new TextView(r11);
            this.percentageView = r104;
            r104.setClickable(false);
            r104.setTypeface(AndroidUtilities.bold());
            r104.setTextSize(1, 11.0f);
            r104.setPadding(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(5.0f), AndroidUtilities.dp(1.0f));
            r104.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), 285212671));
            addView(r104, LayoutHelper.createFrame(-2, -2.0f, 53, 0.0f, 10.0f, 10.0f, 0.0f));
        }

        private void checkPercentageViewBackground() {
            this.cardBackground.selectedColor = null;
            if (this.rarityColor == null) goto L6;
            int r0 = Theme.blendOver(Theme.getColor(Theme.key_windowBackgroundWhite), Theme.multAlpha(this.rarityColor.intValue(), AndroidUtilities.lerp(0.15f, 1.0f, this.isSelected.getFloatValue())));
            GiftSheet.CardBackground r2 = this.cardBackground;
            r2.selectedColor = this.rarityColor;
            this.cardBackgroundView.invalidate();
            this.percentageView.setTextColor(ColorUtils.blendARGB(this.rarityColor.intValue(), -1, this.isSelected.getFloatValue()));
        L9:
            boolean r1 = this.percentageView.getBackground() instanceof ShapeDrawable;
            TextView r22 = this.percentageView;
            if (r1 == false) goto L14;
            ((ShapeDrawable) r22.getBackground()).getPaint().setColor(r0);
            this.percentageView.invalidate();
            return;
        L14:
            if (Theme.setSelectorDrawableColor(r22.getBackground(), r0, false) == false) goto L17;
            this.percentageView.invalidate();
            return;
        L17:
            return;
        L6:
            if (this.noPercentageBackground == false) goto L8;
            int r02 = Theme.key_windowBackgroundWhite;
            int r3 = Theme.getColor(r02);
            int r4 = Theme.key_windowBackgroundWhiteBlackText;
            int r32 = ColorUtils.blendARGB(ColorUtils.blendARGB(r3, Theme.getColor(r4), 0.05f), Theme.getColor(Theme.key_featuredStickers_addButton), this.isSelected.getFloatValue());
            this.percentageView.setTextColor(ColorUtils.blendARGB(ColorUtils.blendARGB(Theme.getColor(r02), Theme.getColor(r4), 0.5f), -1, this.isSelected.getFloatValue()));
            r0 = r32;
            goto L9
        L8:
            r0 = ColorUtils.blendARGB(ColorUtils.setAlphaComponent(this.attributes.backdrop.center_color, 255), ColorUtils.setAlphaComponent(this.attributes.backdrop.pattern_color, 255), 0.5f);
            this.percentageView.setTextColor(-1);
            goto L9
        }

        private void setSticker(TLRPC.Document r18, int r19, Object r20, boolean r21) {
            if (r18 != null) goto L7;
            this.imageView.clearImage();
            this.lastDocument = null;
            return;
        L7:
            if (this.lastDocument != r18) goto L9;
            return;
        L9:
            this.lastDocument = r18;
            TLRPC.PhotoSize r9 = FileLoader.getClosestPhotoSizeWithSize(r18.thumbs, AndroidUtilities.dp(100.0f));
            SvgHelper.SvgDrawable r15 = DocumentObject.getSvgThumb(r18, Theme.key_windowBackgroundGray, 0.3f);
            StringBuilder r3 = new StringBuilder();
            r3.append(r19);
            r3.append("_");
            r3.append(r19);
            if (r21 == false) goto L12;
            String r4 = "_nolimit_pcache";
        L13:
            r3.append(r4);
            String r12 = r3.toString();
            this.imageView.setLayoutParams(LayoutHelper.createFrame(r19, r19, 49, 0.0f, r3 + 17, 0.0f, (80 - r19) / 2));
            this.imageView.setImage(ImageLocation.getForDocument(r18), r12, ImageLocation.getForDocument(r9, r18), r12, r15, r20);
            return;
        L12:
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L13
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
            checkPercentageViewBackground();
        }

        public void setSelected(boolean r2, boolean r3) {
            this.cardBackground.setSelected(r2, r3);
            this.isSelected.setValue(r2, r3);
        }
    }

    public enum Mode extends Enum<Mode> {
        private static final /* synthetic */ Mode[] $VALUES = null;
        public static final Mode RANDOM = null;
        public static final Mode SELECTED = null;

        private static /* synthetic */ Mode[] $values() {
            return new Mode[]{RANDOM, SELECTED};
        }

        static {
            RANDOM = new Mode("RANDOM", 0);
            SELECTED = new Mode("SELECTED", 1);
            $VALUES = $values();
        }

        Mode(String r1, int r2) {
        }

        public static Mode valueOf(String r1) {
            return (Mode) Enum.valueOf(Mode.class, r1);
        }

        public static Mode[] values() {
            return (Mode[]) $VALUES.clone();
        }
    }

    public static class TabsSelectorView extends GlassTabsView implements FactorAnimator.Target {
        public final FactorAnimator animator;
        public final Utilities.Callback<Integer> onTabSelectListener;
        private int selectedTab;
        private GlassTabView[] tabs;

        public static /* synthetic */ void $r8$lambda$8wa9LEvj7uJYQm5oIHXqt_xSHOo(TabsSelectorView r0, int r1, View r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$mselectTab, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m18942$$Nest$mselectTab(TabsSelectorView r0, int r1) {
            r0.selectTab(r1);
        }

        public TabsSelectorView(Context r7, Theme.ResourcesProvider r8, Utilities.Callback<Integer> r9) {
            super(r7);
            this.animator = new FactorAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 1600);
            this.onTabSelectListener = r9;
            int r6 = Theme.key_glass_defaultIcon;
            setLensColor(Theme.multAlpha(Theme.getColor(r6, r8), 0.09411765f), Theme.multAlpha(Theme.getColor(r6, r8), 0.1254902f));
            this.tabs = new GlassTabView[]{GlassTabView.createMainTab(r7, r8, GlassTabView.TabAnimation.MODELS, R.string.GiftPreviewModels), GlassTabView.createMainTab(r7, r8, GlassTabView.TabAnimation.COLORS, R.string.GiftPreviewBackdrops), GlassTabView.createMainTab(r7, r8, GlassTabView.TabAnimation.SYMBOLS, R.string.GiftPreviewSymbols)};
            int r72 = 0;
        L3:
            GlassTabView[] r82 = this.tabs;
            if (r72 >= r82.length) goto L6;
            this.linearLayout.addView(r82[r72], LayoutHelper.createLinear(0, -1, 1.0f));
            this.tabs[r72].setOnClickListener(new StarGiftPreviewSheet$TabsSelectorView$$ExternalSyntheticLambda0(this, r72));
            r72 = r72 + 1;
            goto L3
        L6:
            r82[0].setSelected(true, false);
        }

        private /* synthetic */ void lambda$new$0(int r1, View r2) {
            selectTab(r1);
        }

        private void selectTab(int r4) {
            int r0 = this.selectedTab;
            if (r0 == r4) goto L6;
            this.tabs[r0].setSelected(false, true);
            this.tabs[r4].setSelected(true, true);
            this.selectedTab = r4;
            this.animator.animateTo(r4);
            this.onTabSelectListener.run(Integer.valueOf(r4));
            return;
        }

        private void updateLens() {
            float r0 = this.animator.getFactor();
            setLensBounds((int) AndroidUtilities.lerp(AndroidUtilities.dp(8.0f), getMeasuredWidth() - AndroidUtilities.dp(8.0f), r0 / 3.0f), AndroidUtilities.dp(8.0f), (int) AndroidUtilities.lerp(AndroidUtilities.dp(8.0f), getMeasuredWidth() - AndroidUtilities.dp(8.0f), (r0 + 1.0f) / 3.0f), getMeasuredHeight() - AndroidUtilities.dp(8.0f));
            MathUtils.clamp((int) ((1.0f - Math.abs(r0 - 1.0f)) * 255.0f), 0, 255);
        }

        public int getSelectedTab() {
            return this.selectedTab;
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
            updateLens();
            invalidate();
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            updateLens();
        }
    }

    /* renamed from: $r8$lambda$16K0s3oJa-qqB3e--_hg2DmJMe4, reason: not valid java name */
    public static /* synthetic */ void m18910$r8$lambda$16K0s3oJaqqB3e_hg2DmJMe4(StarGiftPreviewSheet r0, ArrayList r1, View r2) {
        r0.lambda$new$7(r1, r2);
    }

    /* renamed from: $r8$lambda$4FW-GrWE3omtJROED_SUH0J-jw8, reason: not valid java name */
    public static /* synthetic */ void m18911$r8$lambda$4FWGrWE3omtJROED_SUH0Jjw8(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$6dDAB4oCjfBKUgjWLOcaEjDuSkE(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$9xx9Mc1JoNCWygCbRWTmt_nA2Uc(StarGiftPreviewSheet r0, Integer r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ double $r8$lambda$CuONUr8H9ALoFz92bHkVg0OKCeM(TL_stars.StarGiftAttribute r2) {
        return getRarityIndex(r2);
    }

    public static /* synthetic */ void $r8$lambda$KA5_ktOev1iK_ZrKmafS83L4Id0(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$KnSj2HOwjZNsG6702N0SOUDyuqk(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$X8TaWVcvYe_w0MTIrFhsUY__PAY(StarGiftPreviewSheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XzRzPJ0E1kCKde_hMMjhveM1TV8(View r0) {
    }

    public static /* synthetic */ void $r8$lambda$ZgM6YDtuJcaajd5TN_Vkc6VETuw(StarGiftPreviewSheet r0, int r1, View r2) {
        r0.lambda$new$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_IwOlb_aGrXx5B_EutxngUOUYuQ(StarGiftPreviewSheet r0, boolean r1) {
        r0.lambda$updateTranslationHeader$10(r1);
    }

    /* renamed from: $r8$lambda$hJKEra3gYzQGbDpTZ5SqUa6h-CE, reason: not valid java name */
    public static /* synthetic */ double m18912$r8$lambda$hJKEra3gYzQGbDpTZ5SqUa6hCE(TL_stars.StarGiftAttribute r2) {
        return getRarityIndex(r2);
    }

    public static /* synthetic */ void $r8$lambda$tFz2Uh6jFZbQndo5cRTppgdvTsc(StarGiftPreviewSheet r0) {
        r0.invalidateMergedVisibleBlurredPositionsAndSourcesPositions();
    }

    /* renamed from: $r8$lambda$u5JvGqArX7SbDON-WXxbPqjOhnA, reason: not valid java name */
    public static /* synthetic */ double m18913$r8$lambda$u5JvGqArX7SbDONWXxbPqjOhnA(TL_stars.StarGiftAttribute r2) {
        return getRarityIndex(r2);
    }

    public static /* synthetic */ void $r8$lambda$yAuiNgIRg4TF8DJDF9e4cIdDRRs(StarGiftPreviewSheet r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$yxN4xk9JINfkJndZctv0O4QuA2I(View r0) {
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m18914$$Nest$fgetadapter(StarGiftPreviewSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetbackButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m18915$$Nest$fgetbackButton(StarGiftPreviewSheet r0) {
        return r0.backButton;
    }

    /* renamed from: -$$Nest$fgetheaderPlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m18916$$Nest$fgetheaderPlay(StarGiftPreviewSheet r0) {
        return r0.headerPlay;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ExtendedGridLayoutManager m18917$$Nest$fgetlayoutManager(StarGiftPreviewSheet r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetmode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Mode m18918$$Nest$fgetmode(StarGiftPreviewSheet r0) {
        return r0.mode;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m18919$$Nest$fgetscrollableViewNoiseSuppressor(StarGiftPreviewSheet r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetselectedAttributes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Attributes m18920$$Nest$fgetselectedAttributes(StarGiftPreviewSheet r0) {
        return r0.selectedAttributes;
    }

    /* renamed from: -$$Nest$fgettopView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StarGiftSheet.TopView m18921$$Nest$fgettopView(StarGiftPreviewSheet r0) {
        return r0.topView;
    }

    /* renamed from: -$$Nest$fputselectedAttributes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18922$$Nest$fputselectedAttributes(StarGiftPreviewSheet r0, Attributes r1) {
        r0.selectedAttributes = r1;
    }

    /* renamed from: -$$Nest$minvalidateMergedVisibleBlurredPositionsAndSources, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18923$$Nest$minvalidateMergedVisibleBlurredPositionsAndSources(StarGiftPreviewSheet r0, int r1) {
        r0.invalidateMergedVisibleBlurredPositionsAndSources(r1);
    }

    /* renamed from: -$$Nest$minvalidateMergedVisibleBlurredPositionsAndSourcesImpl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18924$$Nest$minvalidateMergedVisibleBlurredPositionsAndSourcesImpl(StarGiftPreviewSheet r0, int r1) {
        r0.invalidateMergedVisibleBlurredPositionsAndSourcesImpl(r1);
    }

    /* renamed from: -$$Nest$misSelectedWithCurrentTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m18925$$Nest$misSelectedWithCurrentTab(StarGiftPreviewSheet r0, Attributes r1) {
        return r0.isSelectedWithCurrentTab(r1);
    }

    /* renamed from: -$$Nest$mnewSelectedWithCurrentTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Attributes m18926$$Nest$mnewSelectedWithCurrentTab(StarGiftPreviewSheet r0, Attributes r1) {
        return r0.newSelectedWithCurrentTab(r1);
    }

    /* renamed from: -$$Nest$msetMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18927$$Nest$msetMode(StarGiftPreviewSheet r0, Mode r1) {
        r0.setMode(r1);
    }

    /* renamed from: -$$Nest$mupdateHeaderAttributes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18928$$Nest$mupdateHeaderAttributes(StarGiftPreviewSheet r0, boolean r1) {
        r0.updateHeaderAttributes(r1);
    }

    /* renamed from: -$$Nest$mupdateSelectedForVisibleViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18929$$Nest$mupdateSelectedForVisibleViews(StarGiftPreviewSheet r0) {
        r0.updateSelectedForVisibleViews();
    }

    /* renamed from: -$$Nest$mupdateTranslationHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m18930$$Nest$mupdateTranslationHeader(StarGiftPreviewSheet r0) {
        r0.updateTranslationHeader();
    }

    public StarGiftPreviewSheet(Context r30, Theme.ResourcesProvider r31, int r32, String r33, ArrayList<TL_stars.StarGiftAttribute> r34, boolean r35) {
        super(r30, null, false, false, false, r31);
        this.mode = Mode.RANDOM;
        RectF r2 = new RectF();
        this.tabsRectF = r2;
        this.tabsPosP = new PointF();
        ArrayList<RectF> r3 = new ArrayList(1);
        this.blurredPositions = r3;
        r3.add(r2);
        this.currentAccount = r32;
        this.crafting = r35;
        RecyclerListView r36 = this.recyclerListView;
        BottomSheet.ContainerView r4 = this.container;
        Objects.requireNonNull(r36);
        this.viewGroupPartRenderer = new ViewGroupPartRenderer(r36, r4, new ShareAlert$$ExternalSyntheticLambda16(r36));
        ArrayList<TL_stars.starGiftAttributeBackdrop> r22 = TlUtils.findAllInstances(r34, TL_stars.starGiftAttributeBackdrop.class);
        this.backdrops = r22;
        BagRandomizer<TL_stars.starGiftAttributeBackdrop> r37 = new BagRandomizer(r22);
        this.rBackdrops = r37;
        r37.setReshuffleIfEnd(false);
        ArrayList<TL_stars.starGiftAttributePattern> r38 = TlUtils.findAllInstances(r34, TL_stars.starGiftAttributePattern.class);
        this.patterns = r38;
        BagRandomizer<TL_stars.starGiftAttributePattern> r42 = new BagRandomizer(r38);
        this.rPatterns = r42;
        r42.setReshuffleIfEnd(false);
        this.models = TlUtils.findAllInstances(r34, TL_stars.starGiftAttributeModel.class);
        ArrayList<TL_stars.starGiftAttributeModel> r43 = new ArrayList();
        this.simpleModels = r43;
        if (r35 == false) goto L11;
        int r44 = 0;
    L6:
        if (r44 >= this.models.size()) goto L12;
        TL_stars.starGiftAttributeModel r5 = this.models.get(r44);
        if ((r5.rarity instanceof TL_stars.TL_starGiftAttributeRarity) == false) goto L10;
        this.simpleModels.add(r5);
        this.models.remove(r44);
        r44 = r44 - 1;
    L10:
        r44 = r44 + 1;
    L12:
        this.backdrops.sort(Comparator.comparingDouble(new StarGiftPreviewSheet$$ExternalSyntheticLambda7()));
        this.patterns.sort(Comparator.comparingDouble(new StarGiftPreviewSheet$$ExternalSyntheticLambda8()));
        this.models.sort(Comparator.comparingDouble(new StarGiftPreviewSheet$$ExternalSyntheticLambda9()));
        this.simpleModels.sort(Comparator.comparingDouble(new StarGiftPreviewSheet$$ExternalSyntheticLambda9()));
        BagRandomizer<TL_stars.starGiftAttributeModel> r45 = new BagRandomizer(this.models);
        this.rModels = r45;
        r45.setReshuffleIfEnd(false);
        ViewParent r46 = this.actionBar.getParent();
        if ((r46 instanceof ViewGroup) == false) goto L15;
        ((ViewGroup) r46).removeView(this.actionBar);
    L15:
        this.ignoreTouchActionBar = false;
        this.headerMoveTop = AndroidUtilities.dp(6.0f);
        this.occupyNavigationBar = true;
        setBackgroundColor(getBackgroundColor());
        fixNavigationBar();
        BlurredBackgroundSourceColor r47 = new BlurredBackgroundSourceColor();
        this.glassSourceFallback = r47;
        r47.setColor(getBackgroundColor());
        if (Build.VERSION.SDK_INT >= 31) goto L18;
    L20:
        this.scrollableViewNoiseSuppressor = null;
        this.glassSourceRenderNode = null;
        this.glassFactory = new BlurredBackgroundDrawableViewFactory(r47);
    L21:
        this.glassFactory.setSourceRootView(new ViewPositionWatcher(this.container), this.container);
        ExtendedGridLayoutManager r48 = new ExtendedGridLayoutManager(r30, 3);
        this.layoutManager = r48;
        r48.setSpanSizeLookup(new AnonymousClass1(this));
        this.recyclerListView.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(74.0f));
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setLayoutManager(r48);
        this.recyclerListView.setSelectorType(9);
        this.recyclerListView.setSelectorDrawableColor(0);
        this.recyclerListView.addOnScrollListener(new AnonymousClass2(this));
        AnonymousClass3 r49 = new AnonymousClass3(this);
        this.itemAnimator = r49;
        r49.setDelayAnimations(false);
        r49.setSupportsChangeAnimations(false);
        r49.setDurations(280);
        r49.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r49.setDelayIncrement(30);
        this.recyclerListView.setItemAnimator(r49);
        FrameLayout r410 = new FrameLayout(r30);
        this.headerView = r410;
        r410.setClipChildren(false);
        AnonymousClass4 r6 = new AnonymousClass4(this, r30, r31, new StarGiftPreviewSheet$$ExternalSyntheticLambda11(this), new StarGiftPreviewSheet$$ExternalSyntheticLambda12(), null, new StarGiftPreviewSheet$$ExternalSyntheticLambda13(), new StarGiftPreviewSheet$$ExternalSyntheticLambda14(), new StarGiftPreviewSheet$$ExternalSyntheticLambda0(), new StarGiftPreviewSheet$$ExternalSyntheticLambda1(), new StarGiftPreviewSheet$$ExternalSyntheticLambda2());
        this.topView = r6;
        r6.onSwitchPage(new StarGiftSheet.PageTransition(1, 1, 1.0f));
        r6.setPreviewingAttributes(r34);
        r6.hideCloseButton();
        r410.addView(r6, LayoutHelper.createFrame(-1, -1.0f));
        int r23 = this.backgroundPaddingLeft;
        r410.setPadding(r23, 0, r23, 0);
        ImageView r24 = new ImageView(r30);
        this.backButton = r24;
        r24.setBackground(Theme.createRadSelectorDrawable(0, 285212671, 16, 16));
        r24.setImageResource(R.drawable.ic_ab_back);
        ImageView.ScaleType r411 = ImageView.ScaleType.CENTER;
        r24.setScaleType(r411);
        r24.setOnClickListener(new StarGiftPreviewSheet$$ExternalSyntheticLambda3(this));
        ScaleStateListAnimator.apply(r24);
        r410.addView(r24, LayoutHelper.createFrame(32, 32.0f, 51, 12.0f, 14.0f, 0.0f, 0.0f));
        ImageView r25 = new ImageView(r30);
        this.headerPlay = r25;
        r25.setBackground(Theme.createRadSelectorDrawable(0, 285212671, 16, 16));
        r25.setImageResource(R.drawable.filled_gift_pause_24);
        r25.setScaleType(r411);
        r25.setOnClickListener(new StarGiftPreviewSheet$$ExternalSyntheticLambda4(this, r34));
        ScaleStateListAnimator.apply(r25);
        r410.addView(r25, LayoutHelper.createFrame(32, 32.0f, 53, 0.0f, 14.0f, 12.0f, 0.0f));
        TextView r26 = new TextView(r30);
        this.giftNameTextView = r26;
        r26.setTypeface(AndroidUtilities.bold());
        r26.setTextSize(1, 21.0f);
        r26.setText(r33);
        r26.setGravity(17);
        r26.setTextColor(-1);
        r410.addView(r26, LayoutHelper.createFrame(-1, -2.0f, 87, 16.0f, 0.0f, 16.0f, 102.0f));
        TextView r27 = new TextView(r30);
        this.giftStatusTextView = r27;
        r27.setTextSize(1, 13.0f);
        r27.setText(LocaleController.getString(R.string.Gift2PreviewRandomTraits));
        r27.setGravity(17);
        r27.setTextColor(-1879048193);
        r410.addView(r27, LayoutHelper.createFrame(-1, -2.0f, 87, 16.0f, 0.0f, 16.0f, 82.0f));
        LinearLayout r28 = new LinearLayout(r30);
        this.buttonsLayout = r28;
        r28.setOrientation(0);
        r28.setClipChildren(false);
        this.buttons = new Button[3];
        this.tabsSelectorView = new TabsSelectorView(r30, r31, new StarGiftPreviewSheet$$ExternalSyntheticLambda5(this));
        int r29 = 0;
    L22:
        Button[] r412 = this.buttons;
        if (r29 >= r412.length) goto L38;
        r412[r29] = new Button(r30);
        if (r29 == 0) goto L32;
        if (r29 != 1) goto L28;
        this.buttons[r29].textView.setText(LocaleController.getString(R.string.GiftPreviewBackdrop));
    L33:
        ScaleStateListAnimator.apply(this.buttons[r29]);
        this.buttons[r29].setOnClickListener(new StarGiftPreviewSheet$$ExternalSyntheticLambda6(this, r29));
        this.buttons[r29].setBackground(Theme.createRadSelectorDrawable(0, 285212671, 10, 10));
        LinearLayout r413 = this.buttonsLayout;
        Button[] r7 = this.buttons;
        Button r8 = r7[r29];
        if (r29 == (r7.length - 1)) goto L36;
        int r262 = 11;
    L37:
        r413.addView(r8, LayoutHelper.createLinear(0, 42, 1.0f, 7, 0, 0, r262, 0));
        r29 = r29 + 1;
        goto L22
    L36:
        r262 = 0;
        goto L37
    L28:
        if (r29 != 2) goto L33;
        this.buttons[r29].textView.setText(LocaleController.getString(R.string.GiftPreviewSymbol));
        goto L33
    L32:
        this.buttons[r29].textView.setText(LocaleController.getString(R.string.GiftPreviewModel));
        goto L33
    L38:
        this.headerView.addView(this.buttonsLayout, LayoutHelper.createFrame(-1, -2.0f, 87, 16.0f, 0.0f, 16.0f, 18.0f));
        this.containerView.addView(this.headerView, LayoutHelper.createFrame(-1, MediaError.DetailedErrorCode.HLS_NETWORK_INVALID_SEGMENT, 55));
        int r210 = getBackgroundColor();
        View r414 = new View(r30);
        this.gradientTop = r414;
        r414.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{ColorUtils.setAlphaComponent(r210, 160), r210 & 16777215}));
        r414.setAlpha(0.0f);
        FrameLayout.LayoutParams r1 = LayoutHelper.createFrame(-1, 0, 48);
        r1.height = AndroidUtilities.statusBarHeight;
        this.containerView.addView(r414, r1);
        this.tabsSelectorView.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        BlurredBackgroundDrawable r12 = this.glassFactory.create(this.tabsSelectorView);
        r12.setPadding(AndroidUtilities.dp(4.0f));
        r12.setRadius(AndroidUtilities.dp(28.0f));
        r12.setColorProvider(new BlurredBackgroundColorProviderThemed(r31, Theme.key_windowBackgroundWhite));
        this.tabsSelectorView.setBackground(r12);
        this.containerView.addView(this.tabsSelectorView, LayoutHelper.createFrame(268, 64.0f, 81, 0.0f, 0.0f, 0.0f, 5.0f));
        this.selectedAttributes = new Attributes((TL_stars.starGiftAttributeBackdrop) TlUtils.findFirstInstance(r34, TL_stars.starGiftAttributeBackdrop.class), (TL_stars.starGiftAttributePattern) TlUtils.findFirstInstance(r34, TL_stars.starGiftAttributePattern.class), (TL_stars.starGiftAttributeModel) TlUtils.findFirstInstance(r34, TL_stars.starGiftAttributeModel.class));
        this.adapter.update(false);
        updateHeaderAttributes(false);
        return;
    L18:
        if (SharedConfig.chatBlurEnabled() == false) goto L20;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        BlurredBackgroundSourceRenderNode r52 = new BlurredBackgroundSourceRenderNode(r47);
        this.glassSourceRenderNode = r52;
        r52.setOnDrawablesRelativePositionChangeListener(new StarGiftPreviewSheet$$ExternalSyntheticLambda10(this));
        BlurredBackgroundDrawableViewFactory r415 = new BlurredBackgroundDrawableViewFactory(r52);
        this.glassFactory = r415;
        r415.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        goto L21
    L11:
        r43.clear();
        goto L12
    }

    public static /* synthetic */ boolean access$000(StarGiftPreviewSheet r0, View r1, float r2, float r3) {
        return r0.canHighlightChildAt(r1, r2, r3);
    }

    private void applyBottomInset() {
        int r0 = getSystemBottomInset();
        if (this.lastBottomInset == r0) goto L6;
        this.lastBottomInset = r0;
        this.recyclerListView.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), r0 + AndroidUtilities.dp(74.0f));
        ViewGroup.MarginLayoutParams r02 = (ViewGroup.MarginLayoutParams) this.tabsSelectorView.getLayoutParams();
        r02.bottomMargin = this.lastBottomInset + AndroidUtilities.dp(5.0f);
        this.tabsSelectorView.requestLayout();
        return;
    }

    private void fillItems(ArrayList<UItem> r10, UniversalAdapter r11) {
        if (this.models != null) goto L5;
        return;
    L5:
        if (this.backdrops != null) goto L7;
        return;
    L7:
        if (this.patterns == null) goto L44;
        r10.add(UItem.asSpace(AndroidUtilities.dp(315.0f)));
        this.rBackdrops.reset();
        this.rPatterns.reset();
        this.rModels.reset();
        int r112 = this.tabsSelectorView.getSelectedTab();
        int r0 = 0;
        if (r112 != 0) goto L27;
        String r2 = "GiftPreviewCountModels";
        if (this.crafting == false) goto L14;
        String r1 = "GiftPreviewCountModelsCrafting";
    L15:
        r10.add(UItem.asCenterShadow(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma(r1, this.models.size()))));
        ArrayList<TL_stars.starGiftAttributeModel> r12 = this.models;
        int r3 = r12.size();
        int r4 = 0;
    L16:
        if (r4 >= r3) goto L19;
        TL_stars.starGiftAttributeModel r5 = r12.get(r4);
        r4 = r4 + 1;
        r10.add(GiftAttributeCell.Factory.asAttribute(r112, new Attributes(this.rBackdrops.next(), this.rPatterns.next(), r5)));
        goto L16
    L19:
        if (this.simpleModels.isEmpty() == false) goto L21;
        return;
    L21:
        if (this.crafting == false) goto L23;
        r2 = "GiftPreviewCountModelsCrafting2";
    L23:
        r10.add(UItem.asCenterShadow(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma(r2, this.models.size()))));
        ArrayList<TL_stars.starGiftAttributeModel> r13 = this.simpleModels;
        int r22 = r13.size();
    L24:
        if (r0 >= r22) goto L45;
        TL_stars.starGiftAttributeModel r32 = r13.get(r0);
        r0 = r0 + 1;
        r10.add(GiftAttributeCell.Factory.asAttribute(r112, new Attributes(this.rBackdrops.next(), this.rPatterns.next(), r32)));
        goto L24
    L45:
        return;
    L14:
        r1 = "GiftPreviewCountModels";
        goto L15
    L27:
        if (r112 != 1) goto L32;
        r10.add(UItem.asCenterShadow(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("GiftPreviewCountBackdrops", this.backdrops.size()))));
        ArrayList<TL_stars.starGiftAttributeBackdrop> r14 = this.backdrops;
        int r23 = r14.size();
    L29:
        if (r0 >= r23) goto L46;
        TL_stars.starGiftAttributeBackdrop r33 = r14.get(r0);
        r0 = r0 + 1;
        r10.add(GiftAttributeCell.Factory.asAttribute(r112, new Attributes(r33, this.rPatterns.next(), this.rModels.next())));
        goto L29
    L46:
        return;
    L32:
        if (r112 != 2) goto L43;
        r10.add(UItem.asCenterShadow(AndroidUtilities.replaceTags(LocaleController.formatPluralStringComma("GiftPreviewCountSymbols", this.patterns.size()))));
        ArrayList<TL_stars.starGiftAttributePattern> r15 = this.patterns;
        int r24 = r15.size();
    L34:
        if (r0 >= r24) goto L47;
        TL_stars.starGiftAttributePattern r34 = r15.get(r0);
        r0 = r0 + 1;
        r10.add(GiftAttributeCell.Factory.asAttribute(r112, new Attributes(this.rBackdrops.next(), r34, this.rModels.next())));
        goto L34
    L47:
        return;
    L43:
        return;
    }

    private int getBackgroundColor() {
        return ColorUtils.blendARGB(getThemedColor(Theme.key_dialogBackgroundGray), getThemedColor(Theme.key_dialogBackground), 0.1f);
    }

    private static double getRarityIndex(TL_stars.StarGiftAttribute r2) {
        TL_stars.StarGiftAttributeRarity r22 = r2.rarity;
        if ((r22 instanceof TL_stars.TL_starGiftAttributeRarity) == false) goto L7;
        return ((TL_stars.TL_starGiftAttributeRarity) r22).permille;
    L7:
        if ((r22 instanceof TL_stars.TL_starGiftAttributeRarityLegendary) == false) goto L11;
        return 0.01d;
    L11:
        if ((r22 instanceof TL_stars.TL_starGiftAttributeRarityEpic) == false) goto L15;
        return 0.02d;
    L15:
        if ((r22 instanceof TL_stars.TL_starGiftAttributeRarityRare) == false) goto L19;
        return 0.03d;
    L19:
        if ((r22 instanceof TL_stars.TL_starGiftAttributeRarityUncommon) == false) goto L22;
        return 0.04d;
    L22:
        return 0.0d;
    }

    private void invalidateMergedVisibleBlurredPositionsAndSources(int r3) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L10;
        invalidateMergedVisibleBlurredPositionsAndSourcesImpl(r3);
        return;
    }

    private void invalidateMergedVisibleBlurredPositionsAndSourcesImpl(int r3) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor != null) goto L8;
        return;
    L8:
        if (BitwiseUtils.hasFlag(r3, 2) == false) goto L18;
        ViewPositionWatcher.computeCoordinatesInParent(this.tabsSelectorView, this.container, this.tabsPosP);
        RectF r32 = this.tabsRectF;
        PointF r0 = this.tabsPosP;
        float r1 = r0.x;
        r32.left = r1;
        r32.top = r0.y;
        r32.right = r1 + this.tabsSelectorView.getMeasuredWidth();
        RectF r33 = this.tabsRectF;
        r33.bottom = Math.min(r33.top + this.tabsSelectorView.getMeasuredHeight(), this.container.getMeasuredHeight());
        if (this.tabsRectF.isEmpty() == false) goto L13;
        return;
    L13:
        if (LiteMode.isEnabled(262144) == false) goto L15;
        int r34 = 0;
    L16:
        float r35 = -r34;
        this.tabsRectF.inset(r35, r35);
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.blurredPositions, 1);
        goto L18
    L15:
        r34 = AndroidUtilities.dp(48.0f);
    L18:
        if (this.scrollableViewNoiseSuppressor.getRenderNodesCount() == 0) goto L25;
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.viewGroupPartRenderer, this.container.getWidth(), this.container.getHeight());
        return;
    }

    private void invalidateMergedVisibleBlurredPositionsAndSourcesPositions() {
        invalidateMergedVisibleBlurredPositionsAndSources(2);
    }

    private boolean isSelectedWithCurrentTab(Attributes r5) {
        if (this.mode != Mode.RANDOM) goto L5;
        return false;
    L5:
        int r0 = this.tabsSelectorView.getSelectedTab();
        Attributes r4 = this.selectedAttributes;
        if (r4 != null) goto L8;
    L23:
        return false;
    L8:
        if (r0 != 1) goto L14;
        if (r5.backdrop != r4.backdrop) goto L12;
        return true;
    L12:
        return false;
    L14:
        if (r0 == 2) goto L16;
        if (r0 != 0) goto L23;
        if (r5.model != r4.model) goto L23;
        return true;
    L16:
        if (r5.pattern != r4.pattern) goto L18;
        return true;
    L18:
        return false;
    }

    private /* synthetic */ void lambda$new$6(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$new$7(ArrayList r4, View r5) {
        Mode r52 = this.mode;
        Mode r0 = Mode.SELECTED;
        if (r52 != r0) goto L7;
        this.topView.setPreviewingAttributes(r4);
        setMode(Mode.RANDOM);
        return;
    L7:
        if (r52 != Mode.RANDOM) goto L10;
        Attributes r42 = new Attributes(this.topView.getUpgradeBackdropAttribute(), this.topView.getUpgradePatternAttribute(), this.topView.getUpgradeImageViewAttribute());
        this.selectedAttributes = r42;
        this.topView.setPreviewAttributes(r42);
        setMode(r0);
        return;
    }

    private /* synthetic */ void lambda$new$8(Integer r1) {
        this.itemAnimator.endAnimations();
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$new$9(int r1, View r2) {
        TabsSelectorView.m18942$$Nest$mselectTab(this.tabsSelectorView, r1);
    }

    private /* synthetic */ void lambda$updateTranslationHeader$10(boolean r1) {
        if (r1 == true) goto L5;
        this.gradientTop.setVisibility(8);
        return;
    }

    private Attributes newSelectedWithCurrentTab(Attributes r3) {
        int r0 = this.tabsSelectorView.getSelectedTab();
        Attributes r2 = this.selectedAttributes;
        if (r2 != null) goto L5;
        return null;
    L5:
        if (r0 != 1) goto L9;
        return new Attributes(r3.backdrop, r2.pattern, r2.model);
    L9:
        if (r0 == 2) goto L11;
        if (r0 == 0) goto L14;
        return null;
    L14:
        return new Attributes(r2.backdrop, r2.pattern, r3.model);
    L11:
        return new Attributes(r2.backdrop, r3.pattern, r2.model);
    }

    private void setMode(Mode r4) {
        if (this.mode != r4) goto L5;
        return;
    L5:
        this.mode = r4;
        ImageView r0 = this.headerPlay;
        Mode r1 = Mode.SELECTED;
        if (r4 != r1) goto L8;
        int r2 = R.drawable.filled_gift_play_24;
    L9:
        r0.setImageResource(r2);
        TextView r02 = this.giftStatusTextView;
        if (r4 != r1) goto L12;
        int r42 = R.string.Gift2PreviewSelectedTraits;
    L13:
        r02.setText(LocaleController.getString(r42));
        updateSelectedForVisibleViews();
        return;
    L12:
        r42 = R.string.Gift2PreviewRandomTraits;
        goto L13
    L8:
        r2 = R.drawable.filled_gift_pause_24;
        goto L9
    }

    private void updateHeaderAttributes(boolean r5) {
        if (this.topView.getUpgradeImageViewAttribute() != null) goto L5;
        return;
    L5:
        if (this.topView.getUpgradeBackdropAttribute() != null) goto L7;
        return;
    L7:
        if (this.topView.getUpgradePatternAttribute() == null) goto L13;
        this.buttons[0].titleView.setText(this.topView.getUpgradeImageViewAttribute().name, r5);
        AnimatedTextView r1 = this.buttons[0].percentView;
        TL_stars.StarGiftAttributeRarity r3 = this.topView.getUpgradeImageViewAttribute().rarity;
        r1.setText(StarGiftSheet.getRarityName(r3, new Integer[1]));
        this.buttons[1].titleView.setText(this.topView.getUpgradeBackdropAttribute().name, r5);
        this.buttons[1].percentView.setText(AffiliateProgramFragment.percents(this.topView.getUpgradeBackdropAttribute().getRarityPermille()), r5);
        this.buttons[2].titleView.setText(this.topView.getUpgradePatternAttribute().name, r5);
        this.buttons[2].percentView.setText(AffiliateProgramFragment.percents(this.topView.getUpgradePatternAttribute().getRarityPermille()), r5);
        return;
    }

    private void updateSelectedForVisibleViews() {
        int r0 = this.recyclerListView.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        View r2 = this.recyclerListView.getChildAt(r1);
        if ((r2 instanceof GiftAttributeCell) == false) goto L9;
        GiftAttributeCell r22 = (GiftAttributeCell) r2;
        Attributes r3 = GiftAttributeCell.m18931$$Nest$fgetattributes(r22);
        if (r3 == null) goto L9;
        r22.setSelected(isSelectedWithCurrentTab(r3), true);
    L9:
        r1 = r1 + 1;
        goto L3
    }

    private void updateTranslationHeader() {
        boolean r1 = true;
        int r0 = this.recyclerListView.getChildCount() - 1;
    L3:
        int r2 = 0;
        if (r0 < 0) goto L18;
        View r4 = this.recyclerListView.getChildAt(r0);
        int r5 = this.recyclerListView.getChildAdapterPosition(r4);
        if (r5 < 0) goto L17;
        if (r5 == 2) goto L10;
        if (r5 == 1) goto L14;
        if (r5 != 0) goto L17;
        float r02 = r4.getY();
        int r42 = this.headerView.getMeasuredHeight();
    L11:
        float r03 = r02 - r42;
    L12:
        boolean r43 = true;
    L19:
        float r52 = this.headerView.getHeight() + r03;
        if (r43 == false) goto L26;
        if (r52 < 0.0f) goto L26;
        r1 = false;
    L26:
        if (this.gradientVisible == r1) goto L36;
        this.gradientVisible = r1;
        if (r1 == false) goto L30;
        this.gradientTop.setVisibility(0);
    L30:
        ViewPropertyAnimator r53 = this.gradientTop.animate();
        if (r1 == false) goto L33;
        float r6 = 1.0f;
    L34:
        r53.alpha(r6).setDuration(200).withEndAction(new StarGiftPreviewSheet$$ExternalSyntheticLambda16(this, r1)).start();
        goto L36
    L33:
        r6 = 0.0f;
    L36:
        if (r03 > 0.0f) goto L38;
        int r12 = 0;
    L39:
        this.headerMoveTop = r12;
        FrameLayout r13 = this.headerView;
        if (r43 == true) goto L43;
        r2 = 8;
    L43:
        r13.setVisibility(r2);
        this.headerView.setTranslationY(r03);
        return;
    L38:
        r12 = AndroidUtilities.dp(6.0f);
        goto L39
    L14:
        r03 = r4.getY();
        goto L12
    L10:
        r02 = r4.getY();
        r42 = this.headerView.getMeasuredHeight();
    L17:
        r0 = r0 - 1;
        goto L3
    L18:
        r43 = false;
        r03 = 0.0f;
        goto L19
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r10) {
        AnonymousClass5 r0 = new AnonymousClass5(this, this.recyclerListView, getContext(), this.currentAccount, 0, true, new StarGiftPreviewSheet$$ExternalSyntheticLambda15(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView createRecyclerView(Context r3) {
        return new AnonymousClass6(this, r3, this.resourcesProvider);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return null;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean isTouchOutside(float r1, float r2) {
        if (this.headerView.getVisibility() == 0) goto L5;
        return false;
    L5:
        if (this.headerView.getY() <= r2) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void mainContainerDispatchDraw(Canvas r5) {
        super.mainContainerDispatchDraw(r5);
        int r0 = this.container.getWidth();
        int r1 = this.container.getHeight();
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (r5.isHardwareAccelerated() == true) goto L7;
        return;
    L7:
        if (this.scrollableViewNoiseSuppressor == null) goto L22;
        BlurredBackgroundSourceRenderNode r52 = this.glassSourceRenderNode;
        if (r52 != null) goto L11;
        return;
    L11:
        if (r52.inRecording() == false) goto L13;
        return;
    L13:
        if (this.glassSourceRenderNode.needUpdateDisplayList(r0, r1) == false) goto L25;
        RecordingCanvas r53 = this.glassSourceRenderNode.beginRecording(r0, r1);
        r53.drawColor(getThemedColor(Theme.key_dialogBackgroundGray));
        DownscaleScrollableNoiseSuppressor r02 = this.scrollableViewNoiseSuppressor;
        if (LiteMode.isEnabled(262144) == false) goto L17;
        int r12 = -2;
    L18:
        r02.draw(r53, r12);
        this.glassSourceRenderNode.endRecording();
        return;
    L17:
        r12 = -3;
        goto L18
    L25:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onInsetsChanged() {
        super.onInsetsChanged();
        applyBottomInset();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onOpenAnimationEnd() {
        super.onOpenAnimationEnd();
        invalidateMergedVisibleBlurredPositionsAndSourcesImpl(2);
    }
}
