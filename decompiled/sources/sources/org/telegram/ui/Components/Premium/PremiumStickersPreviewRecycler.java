package org.telegram.ui.Components.Premium;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.SvgHelper;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public abstract class PremiumStickersPreviewRecycler extends RecyclerListView implements NotificationCenter.NotificationCenterDelegate, PagerHeaderView {
    boolean autoPlayEnabled;
    Runnable autoScrollRunnable;
    private boolean checkEffect;
    Comparator<StickerView> comparator;
    private final int currentAccount;
    boolean firstDraw;
    boolean firstMeasure;
    boolean haptic;
    boolean hasSelectedView;
    CubicBezierInterpolator interpolator;
    boolean isVisible;
    LinearLayoutManager layoutManager;
    View oldSelectedView;
    private final ArrayList<TLRPC.Document> premiumStickers;
    int selectStickerOnNextLayout;
    private int size;
    ArrayList<StickerView> sortedView;

    /* renamed from: org.telegram.ui.Components.Premium.PremiumStickersPreviewRecycler$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ PremiumStickersPreviewRecycler this$0;

        public AnonymousClass1(PremiumStickersPreviewRecycler r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            PremiumStickersPreviewRecycler r0 = this.this$0;
            if (r0.autoPlayEnabled == true) goto L6;
            return;
        L6:
            if (r0.sortedView.isEmpty() == true) goto L12;
            ArrayList<StickerView> r02 = this.this$0.sortedView;
            StickerView r03 = r02.get(r02.size() - 1);
            int r04 = this.this$0.getChildAdapterPosition(r03);
            if (r04 < 0) goto L12;
            LinearLayoutManager r1 = this.this$0.layoutManager;
            View r05 = r1.findViewByPosition(r04 + 1);
            if (r05 == null) goto L12;
            PremiumStickersPreviewRecycler r12 = this.this$0;
            r12.haptic = false;
            PremiumStickersPreviewRecycler.m12048$$Nest$mdrawEffectForView(r12, r05, true);
            this.this$0.smoothScrollBy(0, r05.getTop() - ((this.this$0.getMeasuredHeight() - r05.getMeasuredHeight()) / 2), AndroidUtilities.overshootInterpolator);
        L12:
            PremiumStickersPreviewRecycler.m12049$$Nest$mscheduleAutoScroll(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Premium.PremiumStickersPreviewRecycler$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ PremiumStickersPreviewRecycler this$0;

        public AnonymousClass2(PremiumStickersPreviewRecycler r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r7, int r8) {
            super.onScrollStateChanged(r7, r8);
            if (r8 != 1) goto L5;
            this.this$0.haptic = true;
        L5:
            if (r8 != 0) goto L19;
            StickerView r1 = null;
            int r2 = 0;
        L8:
            if (r2 >= r7.getChildCount()) goto L15;
            StickerView r3 = (StickerView) this.this$0.getChildAt(r2);
            if (r1 != null) goto L12;
        L13:
            r1 = r3;
        L14:
            r2 = r2 + 1;
            goto L8
        L12:
            if (r3.progress <= r1.progress) goto L14;
        L15:
            if (r1 == null) goto L17;
            PremiumStickersPreviewRecycler.m12048$$Nest$mdrawEffectForView(this.this$0, r1, true);
            PremiumStickersPreviewRecycler r72 = this.this$0;
            r72.haptic = false;
            r72.smoothScrollBy(0, r1.getTop() - ((this.this$0.getMeasuredHeight() - r1.getMeasuredHeight()) / 2), AndroidUtilities.overshootInterpolator);
        L17:
            PremiumStickersPreviewRecycler.m12049$$Nest$mscheduleAutoScroll(this.this$0);
            return;
        L19:
            AndroidUtilities.cancelRunOnUIThread(this.this$0.autoScrollRunnable);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            if (r1.getScrollState() != 1) goto L5;
            PremiumStickersPreviewRecycler.m12048$$Nest$mdrawEffectForView(this.this$0, null, true);
        L5:
            this.this$0.invalidate();
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ PremiumStickersPreviewRecycler this$0;

        private Adapter(PremiumStickersPreviewRecycler r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(PremiumStickersPreviewRecycler r1, PremiumStickersPreviewRecyclerIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            if (PremiumStickersPreviewRecycler.m12046$$Nest$fgetpremiumStickers(this.this$0).isEmpty() == false) goto L5;
            return;
        L5:
            StickerView r32 = (StickerView) r3.itemView;
            r32.setSticker((TLRPC.Document) PremiumStickersPreviewRecycler.m12046$$Nest$fgetpremiumStickers(this.this$0).get(r4 % PremiumStickersPreviewRecycler.m12046$$Nest$fgetpremiumStickers(this.this$0).size()));
            r32.setDrawImage(!this.this$0.hasSelectedView, false, false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            StickerView r32 = new StickerView(this.this$0, r2.getContext());
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        }
    }

    public class StickerView extends FrameLayout {
        boolean animateImage;
        private float animateImageProgress;
        ImageReceiver centerImage;
        TLRPC.Document document;
        boolean drawEffect;
        ImageReceiver effectImage;
        private float effectProgress;
        public float progress;
        final /* synthetic */ PremiumStickersPreviewRecycler this$0;
        boolean update;
        View view;

        /* renamed from: org.telegram.ui.Components.Premium.PremiumStickersPreviewRecycler$StickerView$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ StickerView this$1;
            final /* synthetic */ PremiumStickersPreviewRecycler val$this$0;

            public AnonymousClass1(StickerView r1, Context r2, PremiumStickersPreviewRecycler r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void draw(Canvas r11) {
                super.draw(r11);
                StickerView r0 = this.this$1;
                if (r0.update == false) goto L7;
                SvgHelper.SvgDrawable r6 = DocumentObject.getSvgThumb(r0.document, Theme.key_windowBackgroundGray, 0.5f);
                StickerView r02 = this.this$1;
                r02.centerImage.setImage(ImageLocation.getForDocument(r02.document), null, r6, "webp", null, 1);
                if (MessageObject.isPremiumSticker(this.this$1.document) == false) goto L7;
                StickerView r03 = this.this$1;
                r03.effectImage.setImage(ImageLocation.getForDocument(MessageObject.getPremiumStickerAnimation(r03.document), this.this$1.document), "140_140", null, null, "tgs", null, 1);
            L7:
                StickerView r04 = this.this$1;
                if (r04.drawEffect == false) goto L25;
                if (StickerView.m12051$$Nest$fgeteffectProgress(r04) != 0.0f) goto L15;
                StickerView.m12053$$Nest$fputeffectProgress(this.this$1, 1.0f);
                if (this.this$1.effectImage.getLottieAnimation() == null) goto L15;
                this.this$1.effectImage.getLottieAnimation().setCurrentFrame(0, false);
            L15:
                if (this.this$1.effectImage.getLottieAnimation() == null) goto L18;
                this.this$1.effectImage.getLottieAnimation().start();
            L18:
                if (this.this$1.effectImage.getLottieAnimation() != null) goto L20;
            L27:
                StickerView r05 = this.this$1;
                if (r05.animateImage == false) goto L33;
                if (r05.centerImage.getLottieAnimation() == null) goto L35;
                this.this$1.centerImage.getLottieAnimation().start();
            L35:
                StickerView r06 = this.this$1;
                if (r06.animateImage == true) goto L38;
            L40:
                StickerView r07 = this.this$1;
                if (r07.animateImage == false) goto L43;
            L45:
                StickerView r08 = this.this$1;
                StickerView.m12052$$Nest$fputanimateImageProgress(r08, Utilities.clamp(StickerView.m12050$$Nest$fgetanimateImageProgress(r08), 1.0f, 0.0f));
                StickerView r09 = this.this$1;
                if (r09.drawEffect == true) goto L48;
            L50:
                StickerView r010 = this.this$1;
                if (r010.drawEffect == false) goto L53;
            L55:
                StickerView r011 = this.this$1;
                StickerView.m12053$$Nest$fputeffectProgress(r011, Utilities.clamp(StickerView.m12051$$Nest$fgeteffectProgress(r011), 1.0f, 0.0f));
                float r012 = PremiumStickersPreviewRecycler.m12047$$Nest$fgetsize(this.this$1.this$0) * 0.45f;
                float r1 = 1.499267f * r012;
                float r2 = getMeasuredWidth() - r1;
                float r4 = (getMeasuredHeight() - r1) / 2.0f;
                float r7 = r1 - r012;
                this.this$1.centerImage.setImageCoords((r7 - (0.02f * r1)) + r2, (r7 / 2.0f) + r4, r012, r012);
                StickerView r013 = this.this$1;
                r013.centerImage.setAlpha((StickerView.m12050$$Nest$fgetanimateImageProgress(r013) * 0.7f) + 0.3f);
                this.this$1.centerImage.draw(r11);
                if (StickerView.m12051$$Nest$fgeteffectProgress(this.this$1) == 0.0f) goto L59;
                this.this$1.effectImage.setImageCoords(r2, r4, r1, r1);
                StickerView r014 = this.this$1;
                r014.effectImage.setAlpha(StickerView.m12051$$Nest$fgeteffectProgress(r014));
                this.this$1.effectImage.draw(r11);
                return;
            L59:
                return;
            L53:
                if (StickerView.m12051$$Nest$fgeteffectProgress(r010) == 0.0f) goto L55;
                StickerView r015 = this.this$1;
                StickerView.m12053$$Nest$fputeffectProgress(r015, StickerView.m12051$$Nest$fgeteffectProgress(r015) - 0.10666667f);
                invalidate();
                goto L55
            L48:
                if (StickerView.m12051$$Nest$fgeteffectProgress(r09) == 1.0f) goto L50;
                StickerView r016 = this.this$1;
                StickerView.m12053$$Nest$fputeffectProgress(r016, StickerView.m12051$$Nest$fgeteffectProgress(r016) + 0.10666667f);
                invalidate();
                goto L55
            L43:
                if (StickerView.m12050$$Nest$fgetanimateImageProgress(r07) == 0.0f) goto L45;
                StickerView r017 = this.this$1;
                StickerView.m12052$$Nest$fputanimateImageProgress(r017, StickerView.m12050$$Nest$fgetanimateImageProgress(r017) - 0.10666667f);
                invalidate();
                goto L45
            L38:
                if (StickerView.m12050$$Nest$fgetanimateImageProgress(r06) == 1.0f) goto L40;
                StickerView r018 = this.this$1;
                StickerView.m12052$$Nest$fputanimateImageProgress(r018, StickerView.m12050$$Nest$fgetanimateImageProgress(r018) + 0.10666667f);
                invalidate();
                goto L45
            L33:
                if (r05.centerImage.getLottieAnimation() == null) goto L35;
                this.this$1.centerImage.getLottieAnimation().stop();
                goto L35
            L20:
                if (this.this$1.effectImage.getLottieAnimation().isLastFrame() == false) goto L27;
                PremiumStickersPreviewRecycler r019 = this.this$1.this$0;
                if (r019.autoPlayEnabled == false) goto L27;
                AndroidUtilities.cancelRunOnUIThread(r019.autoScrollRunnable);
                AndroidUtilities.runOnUIThread(this.this$1.this$0.autoScrollRunnable, 0);
                goto L27
            L25:
                if (r04.effectImage.getLottieAnimation() == null) goto L27;
                this.this$1.effectImage.getLottieAnimation().stop();
                goto L27
            }
        }

        /* renamed from: -$$Nest$fgetanimateImageProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m12050$$Nest$fgetanimateImageProgress(StickerView r0) {
            return r0.animateImageProgress;
        }

        /* renamed from: -$$Nest$fgeteffectProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m12051$$Nest$fgeteffectProgress(StickerView r0) {
            return r0.effectProgress;
        }

        /* renamed from: -$$Nest$fputanimateImageProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12052$$Nest$fputanimateImageProgress(StickerView r0, float r1) {
            r0.animateImageProgress = r1;
        }

        /* renamed from: -$$Nest$fputeffectProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12053$$Nest$fputeffectProgress(StickerView r0, float r1) {
            r0.effectProgress = r1;
        }

        public StickerView(PremiumStickersPreviewRecycler r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.animateImage = true;
            this.view = new AnonymousClass1(this, r4, r3);
            this.centerImage = new ImageReceiver(this.view);
            this.effectImage = new ImageReceiver(this.view);
            this.centerImage.setAllowStartAnimation(false);
            this.effectImage.setAllowStartAnimation(false);
            setClipChildren(false);
            addView(this.view, LayoutHelper.createFrame(-1, -2, 21));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.centerImage.onAttachedToWindow();
            this.effectImage.onAttachedToWindow();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.centerImage.onDetachedFromWindow();
            this.effectImage.onDetachedFromWindow();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r52 = (int) (PremiumStickersPreviewRecycler.m12047$$Nest$fgetsize(this.this$0) * 0.6f);
            ViewGroup.LayoutParams r0 = this.view.getLayoutParams();
            ViewGroup.LayoutParams r1 = this.view.getLayoutParams();
            int r2 = r52 - AndroidUtilities.dp(16.0f);
            r1.height = r2;
            r0.width = r2;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r52 * 0.7f), TLObject.FLAG_30));
        }

        public void setDrawImage(boolean r4, boolean r5, boolean r6) {
            float r1 = 0.0f;
            if (this.drawEffect == r5) goto L12;
            this.drawEffect = r5;
            if (r6 == true) goto L10;
            if (r5 == false) goto L8;
            float r52 = 1.0f;
        L9:
            this.effectProgress = r52;
            goto L10
        L8:
            r52 = 0.0f;
        L10:
            this.view.invalidate();
        L12:
            if (this.animateImage == r4) goto L20;
            this.animateImage = r4;
            if (r6 == true) goto L18;
            if (r4 == false) goto L17;
            r1 = 1.0f;
        L17:
            this.animateImageProgress = r1;
        L18:
            this.view.invalidate();
            return;
        }

        public void setSticker(TLRPC.Document r1) {
            this.document = r1;
            this.update = true;
        }
    }

    public static /* synthetic */ void $r8$lambda$LtPvx6q49UXGaz6bj1W2OpkzTx4(PremiumStickersPreviewRecycler r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ol72qQV_dukX9CE2rba7tmXvXE0(PremiumStickersPreviewRecycler r0) {
        r0.lambda$onLayout$2();
    }

    public static /* synthetic */ int $r8$lambda$lz8HK1mQJjBpR5yE6PkdsLHYANo(StickerView r1, StickerView r2) {
        return (int) ((r1.progress * 100.0f) - (r2.progress * 100.0f));
    }

    /* renamed from: -$$Nest$fgetpremiumStickers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12046$$Nest$fgetpremiumStickers(PremiumStickersPreviewRecycler r0) {
        return r0.premiumStickers;
    }

    /* renamed from: -$$Nest$fgetsize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12047$$Nest$fgetsize(PremiumStickersPreviewRecycler r0) {
        return r0.size;
    }

    /* renamed from: -$$Nest$mdrawEffectForView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12048$$Nest$mdrawEffectForView(PremiumStickersPreviewRecycler r0, View r1, boolean r2) {
        r0.drawEffectForView(r1, r2);
    }

    /* renamed from: -$$Nest$mscheduleAutoScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12049$$Nest$mscheduleAutoScroll(PremiumStickersPreviewRecycler r0) {
        r0.scheduleAutoScroll();
    }

    public PremiumStickersPreviewRecycler(Context r5, int r6) {
        super(r5);
        this.premiumStickers = new ArrayList();
        this.firstMeasure = true;
        this.firstDraw = true;
        this.autoScrollRunnable = new AnonymousClass1(this);
        this.interpolator = new CubicBezierInterpolator(0.0f, 0.5f, 0.5f, 1.0f);
        this.sortedView = new ArrayList();
        this.comparator = new PremiumStickersPreviewRecycler$$ExternalSyntheticLambda0();
        this.selectStickerOnNextLayout = -1;
        this.currentAccount = r6;
        LinearLayoutManager r0 = new LinearLayoutManager(r5);
        this.layoutManager = r0;
        setLayoutManager(r0);
        setAdapter(new Adapter(this, null));
        setClipChildren(false);
        setOnScrollListener(new AnonymousClass2(this));
        setOnItemClickListener(new PremiumStickersPreviewRecycler$$ExternalSyntheticLambda1(this));
        MediaDataController.getInstance(r6).preloadPremiumPreviewStickers();
        setStickers();
    }

    private void drawEffectForView(View r6, boolean r7) {
        if (r6 == null) goto L5;
        boolean r2 = true;
    L6:
        this.hasSelectedView = r2;
        int r22 = 0;
    L8:
        if (r22 >= getChildCount()) goto L14;
        StickerView r3 = (StickerView) getChildAt(r22);
        if (r3 != r6) goto L12;
        r3.setDrawImage(true, true, r7);
    L13:
        r22 = r22 + 1;
        goto L8
    L12:
        r3.setDrawImage(!this.hasSelectedView, false, r7);
        goto L13
    L14:
        return;
    L5:
        r2 = false;
        goto L6
    }

    private /* synthetic */ void lambda$new$1(View r3, int r4) {
        if (r3 == null) goto L5;
        drawEffectForView(r3, true);
        this.haptic = false;
        smoothScrollBy(0, r3.getTop() - ((getMeasuredHeight() - r3.getMeasuredHeight()) / 2), AndroidUtilities.overshootInterpolator);
        return;
    }

    private /* synthetic */ void lambda$onLayout$2() {
        int r1 = 1073741823 - (1073741823 % this.premiumStickers.size());
        LinearLayoutManager r0 = this.layoutManager;
        this.selectStickerOnNextLayout = r1;
        r0.scrollToPositionWithOffset(r1, (getMeasuredHeight() - getChildAt(0).getMeasuredHeight()) >> 1);
        drawEffectForView(null, false);
    }

    private void scheduleAutoScroll() {
        if (this.autoPlayEnabled == true) goto L5;
        return;
    L5:
        AndroidUtilities.cancelRunOnUIThread(this.autoScrollRunnable);
        AndroidUtilities.runOnUIThread(this.autoScrollRunnable, 2700);
    }

    private void setStickers() {
        this.premiumStickers.clear();
        this.premiumStickers.addAll(MediaDataController.getInstance(this.currentAccount).premiumPreviewStickers);
        getAdapter().notifyDataSetChanged();
        invalidate();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.premiumStickersPreviewLoaded) goto L6;
        setStickers();
        return;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        if (this.isVisible == false) goto L30;
        this.sortedView.clear();
        int r0 = 0;
        int r1 = 0;
    L7:
        if (r1 >= getChildCount()) goto L12;
        StickerView r2 = (StickerView) getChildAt(r1);
        float r4 = ((r2.getTop() + r2.getMeasuredHeight()) + (r2.getMeasuredHeight() >> 1)) / ((getMeasuredHeight() >> 1) + r2.getMeasuredHeight());
        if (r4 <= 1.0f) goto L11;
        r4 = 2.0f - r4;
    L11:
        float r42 = Utilities.clamp(r4, 1.0f, 0.0f);
        r2.progress = r42;
        r2.view.setTranslationX(((-getMeasuredWidth()) * 2.0f) * (1.0f - this.interpolator.getInterpolation(r42)));
        this.sortedView.add(r2);
        r1 = r1 + 1;
        goto L7
    L12:
        Collections.sort(this.sortedView, this.comparator);
        if (this.firstDraw == true) goto L17;
        if (this.checkEffect == true) goto L17;
    L22:
        if (this.oldSelectedView == this.sortedView.get(r2.size() - 1)) goto L28;
        this.oldSelectedView = this.sortedView.get(r1.size() - 1);
        if (this.haptic == false) goto L28;
        performHapticFeedback(3);     // Catch: Exception -> L31
    L28:
        if (r0 >= this.sortedView.size()) goto L39;
        r9.save();
        r9.translate(this.sortedView.get(r0).getX(), this.sortedView.get(r0).getY());
        this.sortedView.get(r0).draw(r9);
        r9.restore();
        r0 = r0 + 1;
        goto L28
    L39:
        return;
    L17:
        if (this.sortedView.size() <= 0) goto L22;
        if (this.premiumStickers.isEmpty() == true) goto L22;
        StickerView r12 = this.sortedView.get(r1.size() - 1);
        this.oldSelectedView = r12;
        drawEffectForView(r12, !this.firstDraw);
        this.firstDraw = false;
        this.checkEffect = false;
        goto L28
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean drawChild(Canvas r1, View r2, long r3) {
        return true;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.premiumStickersPreviewLoaded);
        scheduleAutoScroll();
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.premiumStickersPreviewLoaded);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (this.firstMeasure == true) goto L5;
    L9:
        int r12 = this.selectStickerOnNextLayout;
        if (r12 <= 0) goto L16;
        RecyclerView.ViewHolder r13 = findViewHolderForAdapterPosition(r12);
        if (r13 == null) goto L14;
        drawEffectForView(r13.itemView, false);
    L14:
        this.selectStickerOnNextLayout = -1;
        return;
    L16:
        return;
    L5:
        if (this.premiumStickers.isEmpty() == true) goto L9;
        if (getChildCount() <= 0) goto L9;
        this.firstMeasure = false;
        AndroidUtilities.runOnUIThread(new PremiumStickersPreviewRecycler$$ExternalSyntheticLambda2(this));
        goto L9
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int r3, int r4) {
        if (View.MeasureSpec.getSize(r4) <= View.MeasureSpec.getSize(r3)) goto L5;
        this.size = View.MeasureSpec.getSize(r3);
    L6:
        super.onMeasure(r3, r4);
        return;
    L5:
        this.size = View.MeasureSpec.getSize(r4);
        goto L6
    }

    public void setAutoPlayEnabled(boolean r2) {
        if (this.autoPlayEnabled == r2) goto L10;
        this.autoPlayEnabled = r2;
        if (r2 == false) goto L8;
        scheduleAutoScroll();
        this.checkEffect = true;
        invalidate();
        return;
    L8:
        AndroidUtilities.cancelRunOnUIThread(this.autoScrollRunnable);
        drawEffectForView(null, true);
        return;
    }

    public void setOffset(float r2) {
        if (Math.abs(r2 / getMeasuredWidth()) >= 1.0f) goto L5;
        boolean r22 = true;
    L7:
        if (this.isVisible == r22) goto L10;
        this.isVisible = r22;
        invalidate();
        return;
    L10:
        return;
    L5:
        r22 = false;
        goto L7
    }
}
