package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.PaintTypeface;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes7.dex */
public class PaintTextOptionsView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static final List<AlignFramePair> ALIGN_PAIRS = null;
    private RLottieImageView alignView;
    private View colorClickableView;
    private int currentAlign;
    private Delegate delegate;
    private ChatActivityEnterViewAnimatedIconView emojiButton;
    private String lastTypefaceKey;
    private int outlineType;
    private ImageView outlineView;
    private int plusIcon;
    private ImageView plusView;
    private TypefaceCell typefaceCell;
    private PaintTypefaceListView typefaceListView;
    private int x;

    public static final class AlignFramePair {
        private final int fromAlign;
        private final int fromFrame;
        private final int toAlign;
        private final int toFrame;

        /* renamed from: -$$Nest$fgetfromAlign, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11555$$Nest$fgetfromAlign(AlignFramePair r0) {
            return r0.fromAlign;
        }

        /* renamed from: -$$Nest$fgetfromFrame, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11556$$Nest$fgetfromFrame(AlignFramePair r0) {
            return r0.fromFrame;
        }

        /* renamed from: -$$Nest$fgettoAlign, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11557$$Nest$fgettoAlign(AlignFramePair r0) {
            return r0.toAlign;
        }

        /* renamed from: -$$Nest$fgettoFrame, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11558$$Nest$fgettoFrame(AlignFramePair r0) {
            return r0.toFrame;
        }

        private AlignFramePair(int r1, int r2, int r3, int r4) {
            this.fromAlign = r1;
            this.toAlign = r2;
            this.fromFrame = r3;
            this.toFrame = r4;
        }

        public /* synthetic */ AlignFramePair(int r1, int r2, int r3, int r4, PaintTextOptionsViewIA r5) {
            this(r1, r2, r3, r4);
        }
    }

    public interface Delegate {
        void onColorPickerSelected();

        void onNewTextSelected();

        void onTextAlignmentSelected(int r1);

        void onTextOutlineSelected(View r1);

        void onTypefaceButtonClicked();
    }

    public static final class TypefaceCell extends TextView {
        private Drawable expandDrawable;
        private boolean isCurrent;

        public TypefaceCell(Context r2) {
            super(r2);
            setTextColor(-1);
            setTextSize(1, 14.0f);
            setCurrent(false);
            setEllipsize(TextUtils.TruncateAt.END);
            setSingleLine();
        }

        public void bind(PaintTypeface r2) {
            setTypeface(r2.getTypeface());
            setText(r2.getName());
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r7) {
            r7.save();
            r7.translate(0.0f, AndroidUtilities.dp(-1.0f));
            super.onDraw(r7);
            r7.restore();
            if (this.isCurrent == false) goto L10;
            int r0 = (getHeight() - AndroidUtilities.dp(16.0f)) / 2;
            boolean r2 = LocaleController.isRTL;
            Drawable r3 = this.expandDrawable;
            if (r2 == false) goto L7;
            r3.setBounds(AndroidUtilities.dp(7.0f), r0, AndroidUtilities.dp(23.0f), AndroidUtilities.dp(16.0f) + r0);
        L8:
            this.expandDrawable.draw(r7);
            return;
        L7:
            r3.setBounds(getWidth() - AndroidUtilities.dp(23.0f), r0, getWidth() - AndroidUtilities.dp(7.0f), AndroidUtilities.dp(16.0f) + r0);
            goto L8
        }

        public void setCurrent(boolean r6) {
            this.isCurrent = r6;
            if (r6 == false) goto L13;
            float r0 = 12.0f;
            if (LocaleController.isRTL == false) goto L7;
            float r62 = 27.0f;
        L8:
            int r63 = AndroidUtilities.dp(r62);
            int r3 = AndroidUtilities.dp(6.0f);
            if (LocaleController.isRTL == true) goto L12;
            r0 = 27.0f;
        L12:
            setPadding(r63, r3, AndroidUtilities.dp(r0), AndroidUtilities.dp(6.0f));
            setBackground(Theme.AdaptiveRipple.rect(1090519039, new float[]{AndroidUtilities.dp(32.0f)}));
        L15:
            if (this.isCurrent == true) goto L17;
        L19:
            invalidate();
            return;
        L17:
            if (this.expandDrawable != null) goto L19;
            Drawable r64 = ContextCompat.getDrawable(getContext(), R.drawable.photo_expand);
            this.expandDrawable = r64;
            r64.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            goto L19
        L7:
            r62 = 12.0f;
            goto L8
        L13:
            setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(14.0f));
            setBackground(Theme.AdaptiveRipple.rect(-14145495));
            goto L15
        }
    }

    public static /* synthetic */ void $r8$lambda$CZvNU3Csmrltvpgdqsat6a16BX4(PaintTextOptionsView r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$IFe-Y-PGKMFmypWpLCKa_PwURXU, reason: not valid java name */
    public static /* synthetic */ void m11553$r8$lambda$IFeYPGKMFmypWpLCKa_PwURXU(PaintTextOptionsView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$cIeEReWKs2JET8Puam5DAvsFqjo(PaintTextOptionsView r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$czF_tzZI8zjtH-iz-6FPukYzDfo, reason: not valid java name */
    public static /* synthetic */ void m11554$r8$lambda$czF_tzZI8zjtHiz6FPukYzDfo(PaintTextOptionsView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$m1HbX6UXIv4Aft8M3K31x2ikKUg(PaintTextOptionsView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    static {
        ALIGN_PAIRS = Arrays.asList(new AlignFramePair[]{new AlignFramePair(0, 1, 20, 0, null), new AlignFramePair(0, 2, 20, 40, null), new AlignFramePair(1, 0, 0, 20, null), new AlignFramePair(1, 2, 60, 40, null), new AlignFramePair(2, 0, 40, 20, null), new AlignFramePair(2, 1, 40, 60, null)});
    }

    public PaintTextOptionsView(Context r13) {
        super(r13);
        this.currentAlign = 0;
        setWillNotDraw(false);
        View r0 = new View(r13);
        this.colorClickableView = r0;
        r0.setOnClickListener(new PaintTextOptionsView$$ExternalSyntheticLambda0(this));
        addView(this.colorClickableView, LayoutHelper.createFrame(24, 24.0f, 48, 0.0f, 0.0f, 16.0f, 0.0f));
        RLottieImageView r02 = new RLottieImageView(r13);
        this.alignView = r02;
        r02.setAnimation(R.raw.photo_text_allign, 24, 24);
        RLottieDrawable r03 = this.alignView.getAnimatedDrawable();
        r03.setPlayInDirectionOfCustomEndFrame(true);
        r03.setCustomEndFrame(20);
        r03.setCurrentFrame(20);
        RLottieImageView r04 = this.alignView;
        PorterDuff.Mode r3 = PorterDuff.Mode.SRC_IN;
        r04.setColorFilter(new PorterDuffColorFilter(-1, r3));
        this.alignView.setOnClickListener(new PaintTextOptionsView$$ExternalSyntheticLambda1(this));
        this.alignView.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        addView(this.alignView, LayoutHelper.createFrame(28, 28.0f, 16, 0.0f, 0.0f, 16.0f, 0.0f));
        ImageView r05 = new ImageView(r13);
        this.outlineView = r05;
        r05.setImageResource(R.drawable.msg_text_outlined);
        this.outlineView.setPadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
        this.outlineView.setOnClickListener(new PaintTextOptionsView$$ExternalSyntheticLambda2(this));
        addView(this.outlineView, LayoutHelper.createFrame(28, 28.0f, 16, 0.0f, 0.0f, 16.0f, 0.0f));
        ImageView r06 = new ImageView(r13);
        this.plusView = r06;
        r06.setImageResource(R.drawable.msg_add);
        this.plusView.setColorFilter(new PorterDuffColorFilter(-1, r3));
        this.plusView.setBackground(Theme.createSelectorDrawable(1090519039));
        this.plusView.setOnClickListener(new PaintTextOptionsView$$ExternalSyntheticLambda3(this));
        this.plusView.setPadding(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f));
        addView(this.plusView, LayoutHelper.createFrame(28, 28.0f, 16, 0.0f, 0.0f, 16.0f, 0.0f));
        TypefaceCell r07 = new TypefaceCell(r13);
        this.typefaceCell = r07;
        r07.setCurrent(true);
        this.typefaceCell.setOnClickListener(new PaintTextOptionsView$$ExternalSyntheticLambda4(this));
        addView(this.typefaceCell, LayoutHelper.createLinear(-2, -2, 0.0f, 21));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        this.delegate.onColorPickerSelected();
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        setAlignment((this.currentAlign + 1) % 3, true);
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        this.delegate.onTextOutlineSelected(r1);
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        this.delegate.onNewTextSelected();
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        this.delegate.onTypefaceButtonClicked();
    }

    private void layoutChild(View r7) {
        if (r7.getVisibility() == 8) goto L6;
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) r7.getLayoutParams();
        int r1 = this.x + r0.leftMargin;
        this.x = r1;
        r7.layout(r1, (getMeasuredHeight() - r0.height) / 2, this.x + r0.width, (getMeasuredHeight() + r0.height) / 2);
        this.x += r0.width + r0.rightMargin;
        return;
    }

    public void animatePlusToIcon(int r2) {
        if (r2 != 0) goto L5;
        r2 = R.drawable.msg_add;
    L5:
        if (this.plusIcon == r2) goto L8;
        ImageView r0 = this.plusView;
        this.plusIcon = r2;
        AndroidUtilities.updateImageViewImageAnimated(r0, r2);
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.customTypefacesLoaded) goto L8;
        String r12 = this.lastTypefaceKey;
        if (r12 == null) goto L9;
        setTypeface(r12);
        this.lastTypefaceKey = null;
        return;
    L9:
        return;
    }

    public View getColorClickableView() {
        return this.colorClickableView;
    }

    public ChatActivityEnterViewAnimatedIconView getEmojiButton() {
        return this.emojiButton;
    }

    public TypefaceCell getTypefaceCell() {
        return this.typefaceCell;
    }

    public void getTypefaceCellBounds(RectF r5) {
        r5.set(this.typefaceCell.getLeft() + AndroidUtilities.dp(8.0f), this.typefaceCell.getTop(), this.typefaceCell.getRight() + AndroidUtilities.dp(8.0f), this.typefaceCell.getBottom());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.customTypefacesLoaded);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.customTypefacesLoaded);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        this.x = getPaddingLeft();
        layoutChild(this.colorClickableView);
        layoutChild(this.alignView);
        layoutChild(this.outlineView);
        layoutChild(this.plusView);
        this.typefaceCell.layout((getMeasuredWidth() - getPaddingRight()) - this.typefaceCell.getMeasuredWidth(), (getMeasuredHeight() - this.typefaceCell.getMeasuredHeight()) / 2, getMeasuredWidth() - getPaddingRight(), (getMeasuredHeight() + this.typefaceCell.getMeasuredHeight()) / 2);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        int r82 = View.MeasureSpec.getSize(r8);
        int r92 = View.MeasureSpec.getSize(r9);
        int r0 = (r82 - getPaddingLeft()) - getPaddingRight();
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L10;
        View r2 = getChildAt(r1);
        TypefaceCell r3 = this.typefaceCell;
        if (r2 != r3) goto L8;
        r3.measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r92, Integer.MIN_VALUE));
    L9:
        r1 = r1 + 1;
        goto L4
    L8:
        FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) r2.getLayoutParams();
        r2.measure(View.MeasureSpec.makeMeasureSpec(r32.width, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r32.height, TLObject.FLAG_30));
        r0 = r0 - ((r2.getMeasuredWidth() + r32.leftMargin) + r32.rightMargin);
        goto L9
    L10:
        setMeasuredDimension(r82, r92);
    }

    public void setAlignment(int r2) {
        setAlignment(r2, false);
    }

    public void setAlignment(int r7, boolean r8) {
        int r0 = this.currentAlign;
        this.currentAlign = r7;
        if (r0 != r7) goto L14;
        RLottieDrawable r02 = this.alignView.getAnimatedDrawable();
        List<AlignFramePair> r2 = ALIGN_PAIRS;
        AlignFramePair r1 = r2.get(0);
        Iterator<AlignFramePair> r22 = r2.iterator();
    L6:
        if (r22.hasNext() == false) goto L10;
        AlignFramePair r3 = r22.next();
        if (this.currentAlign != AlignFramePair.m11557$$Nest$fgettoAlign(r3)) goto L6;
        r1 = r3;
    L10:
        r02.setCurrentFrame(AlignFramePair.m11558$$Nest$fgettoFrame(r1));
        r02.setCustomEndFrame(AlignFramePair.m11558$$Nest$fgettoFrame(r1));
        if (r8 == false) goto L35;
        this.delegate.onTextAlignmentSelected(r7);
        return;
    L35:
        return;
    L14:
        List<AlignFramePair> r23 = ALIGN_PAIRS;
        AlignFramePair r12 = r23.get(0);
        Iterator<AlignFramePair> r24 = r23.iterator();
    L16:
        if (r24.hasNext() == false) goto L22;
        AlignFramePair r32 = r24.next();
        if (r0 != AlignFramePair.m11555$$Nest$fgetfromAlign(r32)) goto L16;
        if (this.currentAlign != AlignFramePair.m11557$$Nest$fgettoAlign(r32)) goto L16;
        r12 = r32;
    L22:
        RLottieDrawable r03 = this.alignView.getAnimatedDrawable();
        r03.setCurrentFrame(AlignFramePair.m11556$$Nest$fgetfromFrame(r12));
        r03.setCustomEndFrame(AlignFramePair.m11558$$Nest$fgettoFrame(r12));
        r03.start();
        if (r8 == false) goto L36;
        this.delegate.onTextAlignmentSelected(r7);
        return;
    }

    public void setDelegate(Delegate r1) {
        this.delegate = r1;
    }

    public void setOutlineType(int r2) {
        setOutlineType(r2, false);
    }

    public void setOutlineType(int r2, boolean r3) {
        if (this.outlineType != r2) goto L5;
        return;
    L5:
        this.outlineType = r2;
        if (r2 != 1) goto L8;
        int r22 = R.drawable.msg_photo_text_framed2;
    L18:
        ImageView r1 = this.outlineView;
        if (r3 == false) goto L22;
        AndroidUtilities.updateImageViewImageAnimated(r1, r22);
        return;
    L22:
        r1.setImageResource(r22);
        return;
    L8:
        if (r2 != 2) goto L10;
        r22 = R.drawable.msg_photo_text_framed3;
        goto L18
    L10:
        if (r2 != 3) goto L12;
        r22 = R.drawable.msg_photo_text_regular;
        goto L18
    L12:
        if (r2 == 4) goto L14;
        r22 = R.drawable.msg_photo_text_framed;
        goto L18
    L14:
        r22 = R.drawable.msg_text_outlined;
        goto L18
    }

    public void setTypeface(String r4) {
        this.lastTypefaceKey = r4;
        if (this.typefaceCell == null) goto L15;
        Iterator<PaintTypeface> r0 = PaintTypeface.get().iterator();
    L7:
        if (r0.hasNext() == false) goto L16;
        PaintTypeface r1 = r0.next();
        if (r1.getKey().equals(r4) == false) goto L7;
        this.typefaceCell.bind(r1);
        return;
    L16:
        return;
    }

    public void setTypefaceListView(PaintTypefaceListView r1) {
        this.typefaceListView = r1;
    }
}
