package org.telegram.ui.Components.Paint.Views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Brush;
import org.telegram.ui.Components.Paint.Painting;
import org.telegram.ui.Components.Paint.PersistColorPalette;
import org.telegram.ui.Components.Paint.RenderView;
import org.telegram.ui.Components.Paint.UndoStore;
import org.telegram.ui.Components.Paint.Views.PaintWeightChooserView;
import org.telegram.ui.Components.Size;

/* loaded from: classes7.dex */
public abstract class MaskPaintView extends FrameLayout {
    private float baseScale;
    private Bitmap bitmapToEdit;
    public final FrameLayout buttonsLayout;
    public final TextView cancelButton;
    private int currentAccount;
    private MediaController.CropState currentCropState;
    public final TextView doneButton;
    private boolean eraser;
    private Rect exclusionRect;
    private ArrayList<Rect> exclusionRects;
    private boolean ignoreLayout;
    private float imageHeight;
    private float imageWidth;
    private boolean inBubbleMode;
    private float inputTransformX;
    private float inputTransformY;
    private int orientation;
    private Size paintingSize;
    private float panTranslationY;
    private DispatchQueue queue;
    private final RenderView renderView;
    private float scale;
    private float transformX;
    private float transformY;
    private UndoStore undoStore;
    public PaintWeightChooserView weightChooserView;
    private PaintWeightChooserView.ValueOverride weightDefaultValueOverride;

    /* renamed from: org.telegram.ui.Components.Paint.Views.MaskPaintView$1, reason: invalid class name */
    public class AnonymousClass1 implements PaintWeightChooserView.ValueOverride {
        final /* synthetic */ MaskPaintView this$0;

        public AnonymousClass1(MaskPaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public float get() {
            Brush r0 = MaskPaintView.m11519$$Nest$fgetrenderView(this.this$0).getCurrentBrush();
            MaskPaintView r2 = this.this$0;
            if (r0 != null) goto L7;
            return PersistColorPalette.getInstance(MaskPaintView.m11518$$Nest$fgetcurrentAccount(r2)).getCurrentWeight();
        L7:
            return PersistColorPalette.getInstance(MaskPaintView.m11518$$Nest$fgetcurrentAccount(r2)).getWeight("-1", r0.getDefaultWeight());
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public void set(float r3) {
            PersistColorPalette.getInstance(MaskPaintView.m11518$$Nest$fgetcurrentAccount(this.this$0)).setWeight("-1", r3);
            MaskPaintView.m11519$$Nest$fgetrenderView(this.this$0).setBrushSize(r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MaskPaintView$2, reason: invalid class name */
    public class AnonymousClass2 implements UndoStore.UndoStoreDelegate {
        final /* synthetic */ MaskPaintView this$0;

        public AnonymousClass2(MaskPaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.UndoStore.UndoStoreDelegate
        public void historyChanged() {
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MaskPaintView$3, reason: invalid class name */
    public class AnonymousClass3 implements RenderView.RenderViewDelegate {
        final /* synthetic */ MaskPaintView this$0;

        public AnonymousClass3(MaskPaintView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onBeganDrawing() {
            this.this$0.weightChooserView.setViewHidden(true);
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onFinishedDrawing(boolean r2) {
            MaskPaintView.m11520$$Nest$fgetundoStore(this.this$0).getDelegate().historyChanged();
            this.this$0.weightChooserView.setViewHidden(false);
            this.this$0.onDrawn();
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void onFirstDraw() {
            MaskPaintView.m11519$$Nest$fgetrenderView(this.this$0).animate().alpha(1.0f).setDuration(320).setUpdateListener(new MaskPaintView$3$$ExternalSyntheticLambda0(this.this$0)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public void resetBrush() {
        }

        @Override // org.telegram.ui.Components.Paint.RenderView.RenderViewDelegate
        public boolean shouldDraw() {
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$cXRL7e_YItIUpm3UBcEWZiwtjVc() {
        Looper r0 = Looper.myLooper();
        if (r0 == null) goto L6;
        r0.quit();
        return;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11518$$Nest$fgetcurrentAccount(MaskPaintView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetrenderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RenderView m11519$$Nest$fgetrenderView(MaskPaintView r0) {
        return r0.renderView;
    }

    /* renamed from: -$$Nest$fgetundoStore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UndoStore m11520$$Nest$fgetundoStore(MaskPaintView r0) {
        return r0.undoStore;
    }

    public MaskPaintView(Context r22, int r23, Bitmap r24, Bitmap r25, int r26, MediaController.CropState r27) {
        super(r22);
        this.weightDefaultValueOverride = new AnonymousClass1(this);
        this.exclusionRects = new ArrayList();
        this.exclusionRect = new Rect();
        this.currentAccount = r23;
        this.inBubbleMode = r22 instanceof BubbleActivity;
        UndoStore r3 = new UndoStore();
        this.undoStore = r3;
        r3.setDelegate(new AnonymousClass2(this));
        this.queue = new DispatchQueue("MaskPaint");
        this.currentCropState = r27;
        this.bitmapToEdit = r24;
        this.orientation = r26;
        RenderView r32 = new RenderView(r22, new Painting(getPaintingSize(), r25, r26, null).asMask(), r24, null, null);
        this.renderView = r32;
        r32.setAlpha(0.0f);
        r32.setDelegate(new AnonymousClass3(this));
        r32.setUndoStore(this.undoStore);
        r32.setQueue(this.queue);
        r32.setVisibility(4);
        r32.setBrush(new Brush.Radial());
        r32.setBrushSize(this.weightDefaultValueOverride.get());
        r32.setColor(Opcodes.V_PREVIEW);
        addView(r32, LayoutHelper.createFrame(-1, -1, 51));
        PaintWeightChooserView r4 = new PaintWeightChooserView(r22);
        this.weightChooserView = r4;
        r4.setMinMax(0.05f, 1.0f);
        this.weightChooserView.setBrushWeight(this.weightDefaultValueOverride.get());
        this.weightChooserView.setRenderView(r32);
        this.weightChooserView.setValueOverride(this.weightDefaultValueOverride);
        this.weightChooserView.setTranslationX(-AndroidUtilities.dp(18.0f));
        this.weightChooserView.setAlpha(0.0f);
        addView(this.weightChooserView, LayoutHelper.createFrame(-1, -1.0f));
        FrameLayout r1 = new FrameLayout(r22);
        this.buttonsLayout = r1;
        r1.setAlpha(0.0f);
        r1.setVisibility(8);
        addView(r1, LayoutHelper.createFrame(-1, 44, 80));
        TextView r33 = new TextView(r22);
        this.cancelButton = r33;
        r33.setTextSize(1, 15.0f);
        r33.setTypeface(AndroidUtilities.bold());
        r33.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(-1, 0.15f), 3));
        r33.setPadding(AndroidUtilities.dp(28.0f), 0, AndroidUtilities.dp(28.0f), 0);
        r33.setText(LocaleController.getString(R.string.Cancel).toUpperCase());
        r33.setTextColor(-1);
        r33.setGravity(17);
        r1.addView(r33, LayoutHelper.createFrame(-2, 44.0f, 3, -8.0f, 0.0f, 0.0f, 0.0f));
        TextView r34 = new TextView(r22);
        this.doneButton = r34;
        r34.setTextSize(1, 15.0f);
        r34.setTypeface(AndroidUtilities.bold());
        int r0 = Theme.key_chat_editMediaButton;
        r34.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(Theme.getColor(r0), 0.15f), 3));
        r34.setPadding(AndroidUtilities.dp(28.0f), 0, AndroidUtilities.dp(28.0f), 0);
        r34.setText(LocaleController.getString(R.string.Save).toUpperCase());
        r34.setTextColor(Theme.getColor(r0));
        r34.setGravity(17);
        r1.addView(r34, LayoutHelper.createFrame(-2, 44.0f, 5, 0.0f, 0.0f, -8.0f, 0.0f));
    }

    private Size getPaintingSize() {
        Size r0 = this.paintingSize;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Size r2 = new Size(this.bitmapToEdit.getWidth(), this.bitmapToEdit.getHeight());
        r2.width = 1280.0f;
        float r4 = (float) Math.floor((1280.0f * r1) / r0);
        r2.height = r4;
        if (r4 <= 1280.0f) goto L8;
        r2.height = 1280.0f;
        r2.width = (float) Math.floor((1280.0f * r0) / r1);
    L8:
        this.paintingSize = r2;
        return r2;
    }

    public boolean canUndo() {
        return this.undoStore.canUndo();
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r10, View r11, long r12) {
        int r1 = 0;
        if (r11 == this.renderView) goto L5;
    L15:
        boolean r9 = super.drawChild(r10, r11, r12);
        if (r1 == 0) goto L18;
        r10.restore();
    L18:
        return r9;
    L5:
        if (this.currentCropState == null) goto L15;
        r10.save();
        if (this.inBubbleMode == true) goto L9;
        r1 = AndroidUtilities.statusBarHeight;
    L9:
        int r0 = ActionBar.getCurrentActionBarHeight() + r1;
        int r2 = r11.getMeasuredWidth();
        int r3 = r11.getMeasuredHeight();
        MediaController.CropState r4 = this.currentCropState;
        int r5 = r4.transformRotation;
        if (r5 != 90) goto L12;
    L13:
        r3 = r2;
        r2 = r3;
    L14:
        float r22 = (r2 * r4.cropPw) * r11.getScaleX();
        MediaController.CropState r42 = this.currentCropState;
        int r23 = (int) (r22 / r42.cropScale);
        int r32 = (int) (((r3 * r42.cropPh) * r11.getScaleY()) / this.currentCropState.cropScale);
        float r43 = ((float) Math.ceil((getMeasuredWidth() - r23) / 2.0f)) + this.transformX;
        float r02 = (((((((getMeasuredHeight() - r0) - AndroidUtilities.dp(48.0f)) + getAdditionalBottom()) - r32) / 2.0f) + AndroidUtilities.dp(8.0f)) + r1) + this.transformY;
        r10.clipRect(Math.max(0.0f, r43), Math.max(0.0f, r02), Math.min(r43 + r23, getMeasuredWidth()), Math.min(getMeasuredHeight(), r02 + r32));
        r1 = 1;
        goto L15
    L12:
        if (r5 != 270) goto L14;
        goto L13
    }

    public int getAdditionalBottom() {
        return 0;
    }

    public int getAdditionalTop() {
        return 0;
    }

    public Bitmap getBitmap() {
        Bitmap r0 = this.renderView.getResultBitmap(false, false);
        if (this.orientation == 0) goto L9;
        int r1 = r0.getWidth();
        int r2 = r0.getHeight();
        if (((this.orientation / 90) % 2) == 0) goto L7;
        r1 = r0.getHeight();
        r2 = r0.getWidth();
    L7:
        Bitmap r3 = Bitmap.createBitmap(r1, r2, Bitmap.Config.ARGB_8888);
        Canvas r4 = new Canvas(r3);
        r4.translate(r1 / 2.0f, r2 / 2.0f);
        r4.rotate(-this.orientation);
        RectF r8 = AndroidUtilities.rectTmp;
        r8.set((-r0.getWidth()) / 2.0f, (-r0.getHeight()) / 2.0f, r0.getWidth() / 2.0f, r0.getHeight() / 2.0f);
        r4.drawBitmap(r0, null, r8, new Paint(3));
        r0.recycle();
        return r3;
    L9:
        return r0;
    }

    public RenderView getRenderView() {
        return this.renderView;
    }

    public void init() {
        this.renderView.setVisibility(0);
        this.buttonsLayout.setVisibility(0);
        this.buttonsLayout.setTranslationY(AndroidUtilities.dp(18.0f));
        ViewPropertyAnimator r0 = this.buttonsLayout.animate().alpha(1.0f).translationY(0.0f);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r0.setInterpolator(r3).setDuration(320).start();
        this.weightChooserView.animate().alpha(1.0f).translationX(0.0f).setInterpolator(r3).setDuration(320).start();
    }

    public abstract void onDrawn();

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        int r52 = r5 - r3;
        int r62 = r6 - r4;
        if (this.inBubbleMode == true) goto L5;
        int r22 = AndroidUtilities.REPLACING_TAG_TYPE_LINK;
    L5:
        int r23 = (int) Math.ceil((r52 - this.renderView.getMeasuredWidth()) / 2.0f);
        int r32 = (r62 - this.renderView.getMeasuredHeight()) / 2;
        RenderView r42 = this.renderView;
        r42.layout(r23, r32, r42.getMeasuredWidth() + r23, this.renderView.getMeasuredHeight() + r32);
        FrameLayout r24 = this.buttonsLayout;
        r24.layout(0, r62 - r24.getMeasuredHeight(), this.buttonsLayout.getMeasuredWidth(), r62);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r8, int r9) {
        this.ignoreLayout = true;
        int r0 = View.MeasureSpec.getSize(r8);
        int r1 = View.MeasureSpec.getSize(r9);
        setMeasuredDimension(r0, r1);
        int r2 = AndroidUtilities.displaySize.y;
        Bitmap r3 = this.bitmapToEdit;
        if (r3 == null) goto L5;
        float r12 = r3.getWidth();
        float r32 = this.bitmapToEdit.getHeight();
    L6:
        float r02 = r0;
        float r4 = (float) Math.floor((r02 * r32) / r12);
        float r22 = r2;
        if (r4 <= r22) goto L9;
        r02 = (float) Math.floor((r12 * r22) / r32);
        r4 = r22;
    L9:
        this.renderView.measure(View.MeasureSpec.makeMeasureSpec((int) r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) r4, TLObject.FLAG_30));
        this.baseScale = 1.0f;
        measureChild(this.weightChooserView, r8, r9);
        measureChild(this.buttonsLayout, r8, r9);
        this.ignoreLayout = false;
        if (Build.VERSION.SDK_INT < 29) goto L13;
        this.exclusionRects.clear();
        this.exclusionRects.add(this.exclusionRect);
        int r92 = (int) (getMeasuredHeight() * 0.3f);
        this.exclusionRect.set(0, (getMeasuredHeight() - r92) / 2, AndroidUtilities.dp(20.0f), (getMeasuredHeight() + r92) / 2);
        setSystemGestureExclusionRects(this.exclusionRects);
        return;
    L13:
        return;
    L5:
        r32 = r1;
        r12 = r0;
        goto L6
    }

    public void onRenderViewAlphaUpdate(ValueAnimator r1) {
    }

    public boolean onTouch(MotionEvent r13) {
        float r0 = ((r13.getX() - this.renderView.getTranslationX()) - (getMeasuredWidth() / 2.0f)) / this.renderView.getScaleX();
        float r1 = ((r13.getY() - this.renderView.getTranslationY()) - (getMeasuredHeight() / 2.0f)) / this.renderView.getScaleY();
        double r4 = r0;
        double r6 = (float) Math.toRadians(-this.renderView.getRotation());
        double r02 = r1;
        float r3 = ((float) ((Math.cos(r6) * r4) - (Math.sin(r6) * r02))) + (this.renderView.getMeasuredWidth() / 2.0f);
        float r03 = ((float) ((r4 * Math.sin(r6)) + (r02 * Math.cos(r6)))) + (this.renderView.getMeasuredHeight() / 2.0f);
        MotionEvent r132 = MotionEvent.obtain(r13);
        r132.setLocation(r3, r03);
        this.renderView.onTouch(r132);
        r132.recycle();
        return true;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void setEraser(boolean r2) {
        if (this.eraser != r2) goto L5;
        return;
    L5:
        this.eraser = r2;
        RenderView r1 = this.renderView;
        if (r2 == false) goto L8;
        Brush r22 = new Brush.Eraser();
    L9:
        r1.setBrush(r22);
        return;
    L8:
        r22 = new Brush.Radial();
        goto L9
    }

    public void setTransform(float r10, float r11, float r12, float r13, float r14, float r15) {
        this.scale = r10;
        this.imageWidth = r14;
        this.imageHeight = r15;
        this.inputTransformX = r11;
        this.inputTransformY = r12;
        this.transformX = r11;
        float r122 = r12 + this.panTranslationY;
        this.transformY = r122;
        RenderView r0 = this.renderView;
        MediaController.CropState r1 = this.currentCropState;
        float r2 = 1.0f;
        if (r1 == null) goto L15;
        float r16 = r1.cropScale * 1.0f;
        int r3 = r0.getMeasuredWidth();
        int r4 = r0.getMeasuredHeight();
        if (r3 == 0) goto L14;
        if (r4 == 0) goto L22;
        MediaController.CropState r5 = this.currentCropState;
        int r6 = r5.transformRotation;
        if (r6 != 90) goto L11;
    L12:
        r4 = r3;
        r3 = r4;
    L13:
        float r32 = r3;
        float r142 = Math.max(r14 / ((int) (r5.cropPw * r32)), r15 / ((int) (r5.cropPh * r4)));
        float r17 = r16 * r142;
        MediaController.CropState r152 = this.currentCropState;
        float r52 = ((r152.cropPx * r32) * r10) * r142;
        float r33 = r152.cropScale;
        r11 = r11 + (r52 * r33);
        r122 = r122 + ((((r152.cropPy * r4) * r10) * r142) * r33);
        r13 = r13 + (r152.cropRotate + r6);
    L16:
        float r102 = r10 * r17;
        if (Float.isNaN(r102) == true) goto L20;
        r2 = r102;
    L20:
        r0.setScaleX(r2);
        r0.setScaleY(r2);
        r0.setTranslationX(r11);
        r0.setTranslationY(r122);
        r0.setRotation(r13);
        r0.invalidate();
        invalidate();
        return;
    L11:
        if (r6 != 270) goto L13;
    L22:
        return;
    L14:
        return;
    L15:
        r17 = this.baseScale * 1.0f;
        goto L16
    }

    public void shutdown() {
        this.renderView.shutdown();
        this.queue.postRunnable(new MaskPaintView$$ExternalSyntheticLambda0());
    }

    public boolean undo() {
        if (this.undoStore.canUndo() == true) goto L6;
        return false;
    L6:
        this.undoStore.undo();
        return true;
    }
}
