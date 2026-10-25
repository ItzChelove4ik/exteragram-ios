package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.Crop.CropRotationWheel;
import org.telegram.ui.Components.Crop.CropTransform;
import org.telegram.ui.Components.Crop.CropView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.Paint.Views.PhotoView;

/* loaded from: classes7.dex */
public abstract class CropInlineEditor extends FrameLayout {
    private final AnimatedFloat animatedMirror;
    private final AnimatedFloat animatedOrientation;
    private float appearProgress;
    public boolean applied;
    public final FrameLayout buttonsLayout;
    public final TextView cancelButton;
    public boolean closing;
    public final ContentView contentView;
    public final FrameLayout controlsLayout;
    public final TextView cropButton;
    private final CropTransform cropTransform;
    public final CropView cropView;
    private int lastOrientation;
    private PhotoView photoView;
    private final int[] photoViewLocation;
    private final PreviewView previewContainer;
    private final int[] previewLocation;
    public final TextView resetButton;
    private final Theme.ResourcesProvider resourcesProvider;
    public final LinearLayout shapesLayout;
    private final int[] thisLocation;
    public final CropRotationWheel wheel;

    /* renamed from: org.telegram.ui.Stories.recorder.CropInlineEditor$1, reason: invalid class name */
    public class AnonymousClass1 extends CropView {
        final /* synthetic */ CropInlineEditor this$0;

        public AnonymousClass1(CropInlineEditor r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.Crop.CropView
        public int getCurrentHeight() {
            return CropInlineEditor.m19940$$Nest$mgetCurrentHeight(this.this$0);
        }

        @Override // org.telegram.ui.Components.Crop.CropView
        public int getCurrentWidth() {
            return CropInlineEditor.m19941$$Nest$mgetCurrentWidth(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CropInlineEditor$2, reason: invalid class name */
    public class AnonymousClass2 implements CropView.CropViewListener {
        final /* synthetic */ CropInlineEditor this$0;

        public AnonymousClass2(CropInlineEditor r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onAspectLock(boolean r1) {
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onChange(boolean r1) {
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onTapUp() {
        }

        @Override // org.telegram.ui.Components.Crop.CropView.CropViewListener
        public void onUpdate() {
            this.this$0.contentView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CropInlineEditor$3, reason: invalid class name */
    public class AnonymousClass3 implements CropRotationWheel.RotationWheelListener {
        final /* synthetic */ CropInlineEditor this$0;

        public AnonymousClass3(CropInlineEditor r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void aspectRatioPressed() {
            this.this$0.cropView.showAspectRatioDialog();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public boolean mirror() {
            this.this$0.contentView.invalidate();
            return this.this$0.cropView.mirror();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void onChange(float r1) {
            this.this$0.cropView.setRotation(r1);
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void onEnd(float r1) {
            this.this$0.cropView.onRotationEnded();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public void onStart() {
            this.this$0.cropView.onRotationBegan();
        }

        @Override // org.telegram.ui.Components.Crop.CropRotationWheel.RotationWheelListener
        public boolean rotate90Pressed() {
            boolean r0 = this.this$0.cropView.rotate(-90.0f);
            this.this$0.cropView.maximize(true);
            this.this$0.contentView.invalidate();
            return r0;
        }
    }

    public class ContentView extends View {
        private final Paint dimPaint;
        private final Matrix identityMatrix;
        private final Matrix matrix;
        private final Path previewClipPath;
        private final RectF previewClipRect;
        private final Matrix previewMatrix;
        final /* synthetic */ CropInlineEditor this$0;

        public ContentView(CropInlineEditor r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.dimPaint = new Paint(1);
            this.previewClipPath = new Path();
            this.previewClipRect = new RectF();
            this.previewMatrix = new Matrix();
            this.identityMatrix = new Matrix();
            this.matrix = new Matrix();
        }

        private void applyCrop(Canvas r9, float r10, float r11, float r12) {
            int r112 = CropInlineEditor.m19941$$Nest$mgetCurrentWidth(this.this$0);
            int r0 = CropInlineEditor.m19940$$Nest$mgetCurrentHeight(this.this$0);
            int r1 = CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getOrientation();
            if (r1 != 90) goto L5;
        L6:
            r0 = r112;
            r112 = r0;
        L7:
            float r2 = ((CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getTrueCropScale() - 1.0f) * (1.0f - r10)) + 1.0f;
            float r113 = r112;
            float r4 = getContainerWidth() / r113;
            float r02 = r0;
            if ((r4 * r02) <= getContainerHeight()) goto L10;
            r4 = getContainerHeight() / r02;
        L10:
            r9.translate(CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getCropAreaX() * r12, CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getCropAreaY() * r12);
            float r5 = (CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getScale() / r2) * r4;
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0) != null) goto L13;
        L15:
            float r102 = AndroidUtilities.lerp(1.0f, r5, r10);
        L16:
            r9.scale(r102, r102);
            r9.translate((CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getCropPx() * r113) * r12, (CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getCropPy() * r02) * r12);
            float r103 = (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getOrientation() + CropInlineEditor.m19933$$Nest$fgetcropTransform(this.this$0).getRotation()) + CropInlineEditor.m19931$$Nest$fgetanimatedOrientation(this.this$0).set(((CropInlineEditor.m19934$$Nest$fgetlastOrientation(this.this$0) / 360) * 360) + r1);
            MediaController.CropState r114 = CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop;
            CropInlineEditor r122 = this.this$0;
            if (r114 != null) goto L19;
            float r8 = AndroidUtilities.lerp(0.0f, r103, CropInlineEditor.m19932$$Nest$fgetappearProgress(r122));
        L20:
            r9.rotate(r8);
            return;
        L19:
            r8 = AndroidUtilities.lerp(CropInlineEditor.m19935$$Nest$fgetphotoView(r122).crop.cropRotate + CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop.transformRotation, r103, CropInlineEditor.m19932$$Nest$fgetappearProgress(this.this$0));
            goto L20
        L13:
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop == null) goto L15;
            r102 = AndroidUtilities.lerp(CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop.cropScale, r5, r10);
            goto L16
        L5:
            if (r1 != 270) goto L7;
            goto L6
        }

        private float getContainerHeight() {
            boolean r0 = getContext() instanceof BubbleActivity;
            CropView r1 = this.this$0.cropView;
            float r2 = r1.topPadding;
            if (r0 == true) goto L5;
            int r02 = AndroidUtilities.statusBarHeight;
        L6:
            float r22 = r2 + r02;
            return ((getHeight() - r22) - r1.bottomPadding) - AndroidUtilities.dp(32.0f);
        L5:
            r02 = 0;
            goto L6
        }

        private float getContainerWidth() {
            return getWidth() - AndroidUtilities.dp(32.0f);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r12) {
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0) != null) goto L5;
            return;
        L5:
            r12.save();
            this.dimPaint.setColor(-16777216);
            this.dimPaint.setAlpha((int) (CropInlineEditor.m19932$$Nest$fgetappearProgress(this.this$0) * 255.0f));
            r12.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.dimPaint);
            boolean r2 = true;
            if (CropInlineEditor.m19932$$Nest$fgetappearProgress(this.this$0) >= 1.0f) goto L8;
            this.previewClipPath.rewind();
            this.previewClipRect.set(0.0f, 0.0f, CropInlineEditor.m19937$$Nest$fgetpreviewContainer(this.this$0).getWidth(), CropInlineEditor.m19937$$Nest$fgetpreviewContainer(this.this$0).getHeight());
            this.previewClipRect.offset(CropInlineEditor.m19938$$Nest$fgetpreviewLocation(this.this$0)[0], CropInlineEditor.m19938$$Nest$fgetpreviewLocation(this.this$0)[1]);
            RectF r122 = AndroidUtilities.rectTmp;
            r122.set(0.0f, 0.0f, getWidth(), getHeight());
            AndroidUtilities.lerp(this.previewClipRect, r122, CropInlineEditor.m19932$$Nest$fgetappearProgress(this.this$0), this.previewClipRect);
            float r123 = AndroidUtilities.lerp(AndroidUtilities.dp(12.0f), 0, CropInlineEditor.m19932$$Nest$fgetappearProgress(this.this$0));
            this.previewClipPath.addRoundRect(this.previewClipRect, r123, r123, Path.Direction.CW);
            r12.clipPath(this.previewClipPath);
        L8:
            float r124 = 1.0f - CropInlineEditor.m19932$$Nest$fgetappearProgress(this.this$0);
            float r5 = CropInlineEditor.m19932$$Nest$fgetappearProgress(this.this$0);
            r12.translate((-CropInlineEditor.m19939$$Nest$fgetthisLocation(this.this$0)[0]) * r124, (-CropInlineEditor.m19939$$Nest$fgetthisLocation(this.this$0)[1]) * r124);
            if (r124 <= 0.0f) goto L18;
            CropInlineEditor r7 = this.this$0;
            if (r7.closing == false) goto L13;
            CropInlineEditor.m19935$$Nest$fgetphotoView(r7).getLocationOnScreen(CropInlineEditor.m19936$$Nest$fgetphotoViewLocation(this.this$0));
        L13:
            r12.translate(CropInlineEditor.m19936$$Nest$fgetphotoViewLocation(this.this$0)[0] * r124, CropInlineEditor.m19936$$Nest$fgetphotoViewLocation(this.this$0)[1] * r124);
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop == null) goto L16;
            float r72 = CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop.cropPw;
            float r8 = CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop.cropPh;
        L17:
            float r9 = AndroidUtilities.lerp(1.0f, ((CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getWidth() / r72) * CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getScaleX()) / CropInlineEditor.m19937$$Nest$fgetpreviewContainer(this.this$0).getWidth(), r124);
            r12.scale(r9, r9);
            r12.rotate(CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getRotation() * r124);
            r12.translate(((CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getContentWidth() * r72) / 2.0f) * r124, ((CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getContentHeight() * r8) / 2.0f) * r124);
            goto L18
        L16:
            r72 = 1.0f;
            r8 = 1.0f;
        L18:
            boolean r73 = getContext() instanceof BubbleActivity;
            float r82 = this.this$0.cropView.topPadding;
            if (r73 == true) goto L21;
            int r74 = AndroidUtilities.statusBarHeight;
        L22:
            r12.translate((AndroidUtilities.dp(16.0f) + (getContainerWidth() / 2.0f)) * r5, ((r82 + r74) + ((getContainerHeight() + AndroidUtilities.dp(32.0f)) / 2.0f)) * r5);
            if (r124 <= 0.0f) goto L33;
            float r3 = CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getContentWidth();
            float r75 = CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getContentHeight();
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop == null) goto L27;
            float r83 = CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop.cropPw;
        L29:
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop == null) goto L31;
            float r92 = CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop.cropPh;
        L32:
            float r32 = (r3 * AndroidUtilities.lerp(1.0f, r83, r124)) / 2.0f;
            float r76 = (r75 * AndroidUtilities.lerp(1.0f, r92, r124)) / 2.0f;
            float r84 = AndroidUtilities.lerp(1.0f, 4.0f, r5);
            r12.clipRect((-r32) * r84, (-r76) * r84, r32 * r84, r76 * r84);
            goto L33
        L31:
            r92 = 1.0f;
            goto L32
        L27:
            r83 = 1.0f;
        L33:
            applyCrop(r12, r5, r124, 1.0f);
            r12.rotate(CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getOrientation());
            AnimatedFloat r125 = CropInlineEditor.m19930$$Nest$fgetanimatedMirror(this.this$0);
            CropInlineEditor r33 = this.this$0;
            if (r33.closing == true) goto L36;
            r2 = r33.cropView.isMirrored();
        L42:
            r12.scale(AndroidUtilities.lerp(1.0f, -1.0f, r125.set(r2)), 1.0f);
            r12.translate((-CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getContentWidth()) / 2.0f, (-CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).getContentHeight()) / 2.0f);
            CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).drawContent(r12);
            r12.restore();
            return;
        L36:
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(r33).crop != null) goto L38;
        L40:
            r2 = false;
            goto L42
        L38:
            if (CropInlineEditor.m19935$$Nest$fgetphotoView(this.this$0).crop.mirrored == false) goto L40;
        L21:
            r74 = 0;
            goto L22
        }
    }

    /* renamed from: $r8$lambda$COoWKfMix1UDqqL2iEO_-udG5bg, reason: not valid java name */
    public static /* synthetic */ void m19927$r8$lambda$COoWKfMix1UDqqL2iEO_udG5bg(CropInlineEditor r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$M-Mwuf_1zBp74r_DaTWbmTsfQT4, reason: not valid java name */
    public static /* synthetic */ void m19928$r8$lambda$MMwuf_1zBp74r_DaTWbmTsfQT4(CropInlineEditor r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$SEMw8LEg92PbGA-0a3SoeS9_3cU, reason: not valid java name */
    public static /* synthetic */ void m19929$r8$lambda$SEMw8LEg92PbGA0a3SoeS9_3cU(CropInlineEditor r0) {
        r0.lambda$apply$3();
    }

    public static /* synthetic */ void $r8$lambda$wMaJebTYlLYF7gCVB2EyQbjxzV4(CropInlineEditor r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetanimatedMirror, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m19930$$Nest$fgetanimatedMirror(CropInlineEditor r0) {
        return r0.animatedMirror;
    }

    /* renamed from: -$$Nest$fgetanimatedOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m19931$$Nest$fgetanimatedOrientation(CropInlineEditor r0) {
        return r0.animatedOrientation;
    }

    /* renamed from: -$$Nest$fgetappearProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19932$$Nest$fgetappearProgress(CropInlineEditor r0) {
        return r0.appearProgress;
    }

    /* renamed from: -$$Nest$fgetcropTransform, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CropTransform m19933$$Nest$fgetcropTransform(CropInlineEditor r0) {
        return r0.cropTransform;
    }

    /* renamed from: -$$Nest$fgetlastOrientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19934$$Nest$fgetlastOrientation(CropInlineEditor r0) {
        return r0.lastOrientation;
    }

    /* renamed from: -$$Nest$fgetphotoView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoView m19935$$Nest$fgetphotoView(CropInlineEditor r0) {
        return r0.photoView;
    }

    /* renamed from: -$$Nest$fgetphotoViewLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m19936$$Nest$fgetphotoViewLocation(CropInlineEditor r0) {
        return r0.photoViewLocation;
    }

    /* renamed from: -$$Nest$fgetpreviewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PreviewView m19937$$Nest$fgetpreviewContainer(CropInlineEditor r0) {
        return r0.previewContainer;
    }

    /* renamed from: -$$Nest$fgetpreviewLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m19938$$Nest$fgetpreviewLocation(CropInlineEditor r0) {
        return r0.previewLocation;
    }

    /* renamed from: -$$Nest$fgetthisLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m19939$$Nest$fgetthisLocation(CropInlineEditor r0) {
        return r0.thisLocation;
    }

    /* renamed from: -$$Nest$mgetCurrentHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19940$$Nest$mgetCurrentHeight(CropInlineEditor r0) {
        return r0.getCurrentHeight();
    }

    /* renamed from: -$$Nest$mgetCurrentWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19941$$Nest$mgetCurrentWidth(CropInlineEditor r0) {
        return r0.getCurrentWidth();
    }

    public CropInlineEditor(Context r11, PreviewView r12, Theme.ResourcesProvider r13) {
        super(r11);
        this.lastOrientation = 0;
        this.appearProgress = 0.0f;
        this.thisLocation = new int[2];
        this.previewLocation = new int[2];
        this.photoViewLocation = new int[2];
        this.cropTransform = new CropTransform();
        this.previewContainer = r12;
        this.resourcesProvider = r13;
        ContentView r3 = new ContentView(this, r11);
        this.contentView = r3;
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatedMirror = new AnimatedFloat(r3, 0, 320, r8);
        this.animatedOrientation = new AnimatedFloat(r3, 0, 320, r8);
        AnonymousClass1 r122 = new AnonymousClass1(this, r11);
        this.cropView = r122;
        r122.setListener(new AnonymousClass2(this));
        addView(r122);
        FrameLayout r123 = new FrameLayout(r11);
        this.controlsLayout = r123;
        addView(r123, LayoutHelper.createFrame(-1, -1, 119));
        CropRotationWheel r132 = new CropRotationWheel(r11);
        this.wheel = r132;
        r132.setListener(new AnonymousClass3(this));
        r123.addView(r132, LayoutHelper.createFrame(-1, -2.0f, 81, 0.0f, 0.0f, 0.0f, 52.0f));
        FrameLayout r133 = new FrameLayout(r11);
        this.buttonsLayout = r133;
        r123.addView(r133, LayoutHelper.createFrame(-1, 52.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r124 = new TextView(r11);
        this.cancelButton = r124;
        r124.setTextSize(1, 14.0f);
        r124.setTypeface(AndroidUtilities.bold());
        r124.setBackground(Theme.createSelectorDrawable(-12763843, 0));
        r124.setTextColor(-1);
        r124.setText(LocaleController.getString(R.string.Cancel));
        r124.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r133.addView(r124, LayoutHelper.createFrame(-2, -1, 115));
        r124.setOnClickListener(new CropInlineEditor$$ExternalSyntheticLambda0(this));
        TextView r125 = new TextView(r11);
        this.resetButton = r125;
        r125.setTextSize(1, 14.0f);
        r125.setTypeface(AndroidUtilities.bold());
        r125.setBackground(Theme.createSelectorDrawable(-12763843, 0));
        r125.setTextColor(-1);
        r125.setText(LocaleController.getString(R.string.CropReset));
        r125.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r133.addView(r125, LayoutHelper.createFrame(-2, -1, 113));
        r125.setOnClickListener(new CropInlineEditor$$ExternalSyntheticLambda1(this));
        TextView r126 = new TextView(r11);
        this.cropButton = r126;
        r126.setTextSize(1, 14.0f);
        r126.setTypeface(AndroidUtilities.bold());
        r126.setBackground(Theme.createSelectorDrawable(-12763843, 0));
        r126.setTextColor(-15098625);
        r126.setText(LocaleController.getString(R.string.StoryCrop));
        r126.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r133.addView(r126, LayoutHelper.createFrame(-2, -1, 117));
        r126.setOnClickListener(new CropInlineEditor$$ExternalSyntheticLambda2(this));
        this.shapesLayout = new LinearLayout(r11);
    }

    private int getCurrentHeight() {
        PhotoView r0 = this.photoView;
        if (r0 != null) goto L7;
        return 1;
    L7:
        if (r0.getOrientation() == 90) goto L14;
        if (this.photoView.getOrientation() == 270) goto L14;
        return this.photoView.getContentHeight();
    L14:
        return this.photoView.getContentWidth();
    }

    private int getCurrentWidth() {
        PhotoView r0 = this.photoView;
        if (r0 != null) goto L7;
        return 1;
    L7:
        if (r0.getOrientation() == 90) goto L14;
        if (this.photoView.getOrientation() == 270) goto L14;
        return this.photoView.getContentWidth();
    L14:
        return this.photoView.getContentHeight();
    }

    private /* synthetic */ void lambda$apply$3() {
        EntityView.SelectionView r0 = this.photoView.selectionView;
        if (r0 == null) goto L5;
        r0.updatePosition();
    L5:
        this.photoView.updatePosition();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        close();
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        this.cropView.reset(true);
        this.wheel.setRotated(false);
        this.wheel.setMirrored(false);
        this.wheel.setRotation(0.0f, true);
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        apply();
        close();
    }

    public void apply() {
        PhotoView r0 = this.photoView;
        if (r0 != null) goto L5;
        return;
    L5:
        this.applied = true;
        r0.crop = new MediaController.CropState();
        this.cropView.applyToCropState(this.photoView.crop);
        PhotoView r02 = this.photoView;
        MediaController.CropState r1 = r02.crop;
        r1.orientation = r02.getOrientation();
        this.photoView.updatePosition();
        this.photoView.requestLayout();
        this.photoView.containerView.requestLayout();
        this.photoView.containerView.invalidate();
        this.photoView.containerView.post(new CropInlineEditor$$ExternalSyntheticLambda3(this));
    }

    public abstract void close();

    public void disappearStarts() {
        this.closing = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r1) {
        super.dispatchDraw(r1);
    }

    public float getAppearProgress() {
        return this.appearProgress;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
        this.cropView.setTopPadding(AndroidUtilities.dp(52.0f));
        this.cropView.setBottomPadding(this.controlsLayout.getPaddingBottom() + AndroidUtilities.dp(116.0f));
        super.onLayout(r4, r5, r6, r7, r8);
    }

    public void set(PhotoView r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        this.photoView = r9;
        setVisibility(0);
        this.applied = false;
        this.closing = false;
        this.cropView.onShow();
        getLocationOnScreen(this.thisLocation);
        this.previewContainer.getLocationOnScreen(this.previewLocation);
        r9.getLocationOnScreen(this.photoViewLocation);
        MediaController.CropState r1 = r9.crop;
        if (r1 == null) goto L7;
    L6:
        MediaController.CropState r7 = r1;
        this.cropView.start(r9.getOrientation(), true, false, this.cropTransform, r7);
        this.wheel.setRotation(this.cropView.getRotation());
        CropRotationWheel r92 = this.wheel;
        if (r7 == null) goto L15;
        r92.setRotation(r7.cropRotate, false);
        CropRotationWheel r93 = this.wheel;
        if (r7.transformRotation == 0) goto L13;
        boolean r12 = true;
    L14:
        r93.setRotated(r12);
        this.wheel.setMirrored(r7.mirrored);
        this.animatedMirror.set(r7.mirrored, false);
    L16:
        this.cropView.updateMatrix();
        this.contentView.setVisibility(0);
        this.contentView.invalidate();
        return;
    L13:
        r12 = false;
        goto L14
    L15:
        r92.setRotation(0.0f, false);
        this.wheel.setRotated(false);
        this.wheel.setMirrored(false);
        this.animatedMirror.set(false, false);
        goto L16
    L7:
        r1 = null;
        goto L6
    }

    public void setAppearProgress(float r3) {
        if (Math.abs(this.appearProgress - r3) >= 0.001f) goto L5;
        return;
    L5:
        this.appearProgress = r3;
        this.contentView.invalidate();
        this.cropView.areaView.setDimAlpha(0.5f * r3);
        this.cropView.areaView.setFrameAlpha(r3);
        this.cropView.areaView.invalidate();
    }

    public void stop() {
        this.photoView = null;
        this.cropView.stop();
        this.cropView.onHide();
        this.contentView.setVisibility(8);
        setVisibility(8);
    }
}
