package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;
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

/* loaded from: classes7.dex */
public abstract class CropEditor extends FrameLayout {
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
    private StoryEntry entry;
    private int lastOrientation;
    private final int[] previewLocation;
    private final PreviewView previewView;
    public final TextView resetButton;
    private final Theme.ResourcesProvider resourcesProvider;
    private final int[] thisLocation;
    public final CropRotationWheel wheel;

    /* renamed from: org.telegram.ui.Stories.recorder.CropEditor$1, reason: invalid class name */
    public class AnonymousClass1 extends CropView {
        final /* synthetic */ CropEditor this$0;

        public AnonymousClass1(CropEditor r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.Crop.CropView
        public int getCurrentHeight() {
            return CropEditor.m19925$$Nest$mgetCurrentHeight(this.this$0);
        }

        @Override // org.telegram.ui.Components.Crop.CropView
        public int getCurrentWidth() {
            return CropEditor.m19926$$Nest$mgetCurrentWidth(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.CropEditor$2, reason: invalid class name */
    public class AnonymousClass2 implements CropView.CropViewListener {
        final /* synthetic */ CropEditor this$0;

        public AnonymousClass2(CropEditor r1) {
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

    /* renamed from: org.telegram.ui.Stories.recorder.CropEditor$3, reason: invalid class name */
    public class AnonymousClass3 implements CropRotationWheel.RotationWheelListener {
        final /* synthetic */ CropEditor this$0;

        public AnonymousClass3(CropEditor r1) {
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
        private final Matrix clipMatrix;
        private final Matrix cropMatrix;
        private final Paint dimPaint;
        private final Matrix identityMatrix;
        private final Matrix invertedClipMatrix;
        private final Matrix matrix;
        private final Path previewClipPath;
        private final RectF previewClipRect;
        private final Matrix previewMatrix;
        final /* synthetic */ CropEditor this$0;

        public ContentView(CropEditor r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.dimPaint = new Paint(1);
            this.previewClipPath = new Path();
            this.previewClipRect = new RectF();
            this.identityMatrix = new Matrix();
            this.matrix = new Matrix();
            this.previewMatrix = new Matrix();
            this.cropMatrix = new Matrix();
            this.clipMatrix = new Matrix();
            this.invertedClipMatrix = new Matrix();
        }

        private void applyCrop(Matrix r12, boolean r13) {
            int r0 = CropEditor.m19926$$Nest$mgetCurrentWidth(this.this$0);
            int r1 = CropEditor.m19925$$Nest$mgetCurrentHeight(this.this$0);
            int r2 = CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getOrientation();
            if (r2 != 90) goto L5;
        L6:
            r1 = r0;
            r0 = r1;
        L7:
            float r5 = 1.0f;
            float r4 = CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getTrueCropScale() - 1.0f;
            float r6 = 0.0f;
            if (r13 == false) goto L10;
            float r7 = 1.0f;
        L11:
            float r42 = (r4 * r7) + 1.0f;
            float r02 = r0;
            float r72 = getContainerWidth() / r02;
            float r14 = r1;
            if ((r72 * r14) <= getContainerHeight()) goto L14;
            r72 = getContainerHeight() / r14;
        L14:
            boolean r3 = true;
            if (((CropEditor.m19921$$Nest$fgetentry(this.this$0).orientation / 90) % 2) == 1) goto L18;
            r3 = false;
        L18:
            r12.preTranslate(CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getCropAreaX(), CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getCropAreaY());
            float r8 = (CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getScale() / r42) * r72;
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0) != null) goto L21;
        L24:
            if (r13 == true) goto L27;
        L26:
            r5 = r8;
        L27:
            r12.preScale(r5, r5);
            float r43 = CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getCropPx();
            float r52 = CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getCropPy();
            CropEditor r73 = this.this$0;
            if (r73.closing == false) goto L45;
            if (r13 == false) goto L45;
            if (CropEditor.m19921$$Nest$fgetentry(r73).crop != null) goto L33;
            r43 = 0.0f;
        L38:
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0).crop != null) goto L40;
            r52 = 0.0f;
            goto L45
        L40:
            CropEditor r53 = this.this$0;
            if (r3 == true) goto L44;
            float r32 = CropEditor.m19921$$Nest$fgetentry(r53).crop.cropPy;
        L43:
            r52 = r32;
            goto L45
        L44:
            r32 = CropEditor.m19921$$Nest$fgetentry(r53).crop.cropPx;
            goto L43
        L33:
            MediaController.CropState r44 = CropEditor.m19921$$Nest$fgetentry(this.this$0).crop;
            if (r3 == true) goto L36;
            r43 = r44.cropPx;
            goto L38
        L36:
            r43 = r44.cropPy;
        L45:
            r12.preTranslate(r43 * r02, r52 * r14);
            float r03 = (CropEditor.m19921$$Nest$fgetentry(this.this$0).orientation + CropEditor.m19920$$Nest$fgetcropTransform(this.this$0).getRotation()) + r2;
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0).crop != null) goto L49;
            if (r13 == true) goto L52;
        L51:
            r6 = r03;
        L52:
            r12.preRotate(r6);
            return;
        L49:
            if (r13 == false) goto L51;
            r6 = CropEditor.m19921$$Nest$fgetentry(this.this$0).crop.cropRotate + CropEditor.m19921$$Nest$fgetentry(this.this$0).crop.transformRotation;
            goto L52
        L21:
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0).crop == null) goto L24;
            if (r13 == false) goto L26;
            r5 = CropEditor.m19921$$Nest$fgetentry(this.this$0).crop.cropScale;
            goto L27
        L10:
            r7 = 0.0f;
            goto L11
        L5:
            if (r2 != 270) goto L7;
            goto L6
        }

        private float getContainerHeight() {
            if ((getContext() instanceof BubbleActivity) == true) goto L5;
            float r0 = AndroidUtilities.statusBarHeight;
        L7:
            return ((getHeight() - r0) - this.this$0.cropView.bottomPadding) - AndroidUtilities.dp(32.0f);
        L5:
            r0 = 0.0f;
            goto L7
        }

        private float getContainerWidth() {
            return getWidth() - AndroidUtilities.dp(32.0f);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r2) {
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0) != null) goto L5;
            return;
        L5:
            drawImage(r2, false);
        }

        public void drawImage(Canvas r15, boolean r16) {
            boolean r8 = true;
            if (r16 == true) goto L5;
        L8:
            r15.save();
            this.dimPaint.setColor(-16777216);
            this.dimPaint.setAlpha((int) (CropEditor.m19919$$Nest$fgetappearProgress(this.this$0) * 255.0f));
            r15.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.dimPaint);
            if (CropEditor.m19919$$Nest$fgetappearProgress(this.this$0) >= 1.0f) goto L12;
            if (r16 == true) goto L12;
            this.previewClipPath.rewind();
            this.previewClipRect.set(0.0f, 0.0f, CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getWidth(), CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getHeight());
            this.previewClipRect.offset(CropEditor.m19922$$Nest$fgetpreviewLocation(this.this$0)[0], CropEditor.m19922$$Nest$fgetpreviewLocation(this.this$0)[1]);
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(0.0f, 0.0f, getWidth(), getHeight());
            AndroidUtilities.lerp(this.previewClipRect, r1, CropEditor.m19919$$Nest$fgetappearProgress(this.this$0), this.previewClipRect);
            float r12 = AndroidUtilities.lerp(AndroidUtilities.dp(12.0f), 0, CropEditor.m19919$$Nest$fgetappearProgress(this.this$0));
            this.previewClipPath.addRoundRect(this.previewClipRect, r12, r12, Path.Direction.CW);
            r15.clipPath(this.previewClipPath);
        L12:
            CropEditor.m19919$$Nest$fgetappearProgress(this.this$0);
            float r13 = CropEditor.m19919$$Nest$fgetappearProgress(this.this$0);
            this.previewMatrix.reset();
            this.cropMatrix.reset();
            this.previewMatrix.preTranslate(-CropEditor.m19924$$Nest$fgetthisLocation(this.this$0)[0], -CropEditor.m19924$$Nest$fgetthisLocation(this.this$0)[1]);
            this.previewMatrix.preTranslate(CropEditor.m19922$$Nest$fgetpreviewLocation(this.this$0)[0], CropEditor.m19922$$Nest$fgetpreviewLocation(this.this$0)[1]);
            this.previewMatrix.preScale(CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getWidth() / CropEditor.m19921$$Nest$fgetentry(this.this$0).resultWidth, CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getHeight() / CropEditor.m19921$$Nest$fgetentry(this.this$0).resultHeight);
            this.previewMatrix.preConcat(CropEditor.m19921$$Nest$fgetentry(this.this$0).matrix);
            this.previewMatrix.preTranslate(CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentWidth() / 2.0f, CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentHeight() / 2.0f);
            if ((getContext() instanceof BubbleActivity) == true) goto L15;
            float r3 = AndroidUtilities.statusBarHeight;
        L16:
            this.cropMatrix.preTranslate(AndroidUtilities.dp(16.0f) + (getContainerWidth() / 2.0f), r3 + ((getContainerHeight() + AndroidUtilities.dp(32.0f)) / 2.0f));
            if (r16 == false) goto L44;
            AndroidUtilities.lerp(this.previewMatrix, this.identityMatrix, CropEditor.m19919$$Nest$fgetappearProgress(this.this$0), this.clipMatrix);
            this.clipMatrix.preRotate(-CropEditor.m19921$$Nest$fgetentry(this.this$0).orientation);
            if (this.clipMatrix.invert(this.invertedClipMatrix) == false) goto L44;
            int r4 = CropEditor.m19921$$Nest$fgetentry(this.this$0).orientation;
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0).crop == null) goto L23;
            int r5 = CropEditor.m19921$$Nest$fgetentry(this.this$0).crop.transformRotation;
        L25:
            if ((((r4 + r5) / 90) % 2) != 1) goto L27;
            boolean r42 = true;
        L28:
            float r52 = CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentWidth();
            float r6 = CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentHeight();
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0).crop == null) goto L31;
            float r7 = CropEditor.m19921$$Nest$fgetentry(this.this$0).crop.cropPw;
        L33:
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0).crop == null) goto L35;
            float r122 = CropEditor.m19921$$Nest$fgetentry(this.this$0).crop.cropPh;
        L36:
            if (r42 == false) goto L38;
            float r132 = r6;
        L39:
            float r133 = (r132 * r7) / 2.0f;
            if (r42 == true) goto L43;
            r52 = r6;
        L43:
            float r53 = (r52 * r122) / 2.0f;
            float r14 = AndroidUtilities.lerp(1.0f, 4.0f, r13);
            r15.concat(this.clipMatrix);
            r15.clipRect((-r133) * r14, (-r53) * r14, r133 * r14, r53 * r14);
            r15.concat(this.invertedClipMatrix);
            goto L44
        L38:
            r132 = r52;
            goto L39
        L35:
            r122 = 1.0f;
            goto L36
        L31:
            r7 = 1.0f;
            goto L33
        L27:
            r42 = false;
            goto L28
        L23:
            r5 = 0;
        L44:
            applyCrop(this.previewMatrix, true);
            applyCrop(this.cropMatrix, false);
            AnimatedFloat r17 = CropEditor.m19918$$Nest$fgetanimatedMirror(this.this$0);
            CropEditor r43 = this.this$0;
            if (r43.closing == true) goto L47;
            r8 = r43.cropView.isMirrored();
        L53:
            float r18 = r17.set(r8);
            float r54 = 1.0f - (r18 * 2.0f);
            this.cropMatrix.preScale(r54, 1.0f);
            this.previewMatrix.preScale(r54, 1.0f);
            float r32 = ((4.0f * r18) * (1.0f - r18)) * 0.25f;
            this.cropMatrix.preSkew(0.0f, r32);
            this.previewMatrix.preSkew(0.0f, r32);
            this.cropMatrix.preTranslate((-CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentWidth()) / 2.0f, (-CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentHeight()) / 2.0f);
            this.previewMatrix.preTranslate((-CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentWidth()) / 2.0f, (-CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getContentHeight()) / 2.0f);
            AndroidUtilities.lerp(this.previewMatrix, this.cropMatrix, CropEditor.m19919$$Nest$fgetappearProgress(this.this$0), this.matrix);
            r15.concat(this.matrix);
            CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).drawContent(r15);
            r15.restore();
            if (r16 == false) goto L57;
            r15.restore();
            return;
        L57:
            return;
        L47:
            if (CropEditor.m19921$$Nest$fgetentry(r43).crop != null) goto L49;
        L51:
            r8 = false;
            goto L53
        L49:
            if (CropEditor.m19921$$Nest$fgetentry(this.this$0).crop.mirrored == false) goto L51;
        L15:
            r3 = 0.0f;
            goto L16
        L5:
            if (CropEditor.m19919$$Nest$fgetappearProgress(this.this$0) >= 1.0f) goto L58;
            r15.saveLayerAlpha(0.0f, 0.0f, CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getWidth(), CropEditor.m19923$$Nest$fgetpreviewView(this.this$0).getHeight(), (int) (Math.min(1.0f, (1.0f - CropEditor.m19919$$Nest$fgetappearProgress(this.this$0)) * 2.0f) * 255.0f), 31);
            r15.translate(CropEditor.m19924$$Nest$fgetthisLocation(this.this$0)[0] - CropEditor.m19922$$Nest$fgetpreviewLocation(this.this$0)[0], CropEditor.m19924$$Nest$fgetthisLocation(this.this$0)[1] - CropEditor.m19922$$Nest$fgetpreviewLocation(this.this$0)[1]);
            goto L8
        }
    }

    /* renamed from: $r8$lambda$pB0iypz3luHJfa7T-bUYI6kJSiM, reason: not valid java name */
    public static /* synthetic */ void m19917$r8$lambda$pB0iypz3luHJfa7TbUYI6kJSiM(CropEditor r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$sk4bWES24z60hFhL2jp19vFKFBo(CropEditor r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$wixU8lPg5nMeT7rJC7EyI1D93V0(CropEditor r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetanimatedMirror, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedFloat m19918$$Nest$fgetanimatedMirror(CropEditor r0) {
        return r0.animatedMirror;
    }

    /* renamed from: -$$Nest$fgetappearProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m19919$$Nest$fgetappearProgress(CropEditor r0) {
        return r0.appearProgress;
    }

    /* renamed from: -$$Nest$fgetcropTransform, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CropTransform m19920$$Nest$fgetcropTransform(CropEditor r0) {
        return r0.cropTransform;
    }

    /* renamed from: -$$Nest$fgetentry, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryEntry m19921$$Nest$fgetentry(CropEditor r0) {
        return r0.entry;
    }

    /* renamed from: -$$Nest$fgetpreviewLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m19922$$Nest$fgetpreviewLocation(CropEditor r0) {
        return r0.previewLocation;
    }

    /* renamed from: -$$Nest$fgetpreviewView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PreviewView m19923$$Nest$fgetpreviewView(CropEditor r0) {
        return r0.previewView;
    }

    /* renamed from: -$$Nest$fgetthisLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m19924$$Nest$fgetthisLocation(CropEditor r0) {
        return r0.thisLocation;
    }

    /* renamed from: -$$Nest$mgetCurrentHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19925$$Nest$mgetCurrentHeight(CropEditor r0) {
        return r0.getCurrentHeight();
    }

    /* renamed from: -$$Nest$mgetCurrentWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19926$$Nest$mgetCurrentWidth(CropEditor r0) {
        return r0.getCurrentWidth();
    }

    public CropEditor(Context r17, PreviewView r18, Theme.ResourcesProvider r19) {
        super(r17);
        this.lastOrientation = 0;
        this.appearProgress = 0.0f;
        this.thisLocation = new int[2];
        this.previewLocation = new int[2];
        this.cropTransform = new CropTransform();
        this.previewView = r18;
        this.resourcesProvider = r19;
        ContentView r10 = new ContentView(this, r17);
        this.contentView = r10;
        CubicBezierInterpolator r15 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatedMirror = new AnimatedFloat(r10, 0, 320, r15);
        this.animatedOrientation = new AnimatedFloat(this, 0, 360, r15);
        AnonymousClass1 r0 = new AnonymousClass1(this, r17);
        this.cropView = r0;
        r0.setListener(new AnonymousClass2(this));
        addView(r0);
        FrameLayout r02 = new FrameLayout(r17);
        this.controlsLayout = r02;
        addView(r02, LayoutHelper.createFrame(-1, -1, 119));
        CropRotationWheel r2 = new CropRotationWheel(r17);
        this.wheel = r2;
        r2.setListener(new AnonymousClass3(this));
        r02.addView(r2, LayoutHelper.createFrame(-1, -2.0f, 81, 0.0f, 0.0f, 0.0f, 52.0f));
        FrameLayout r22 = new FrameLayout(r17);
        this.buttonsLayout = r22;
        r02.addView(r22, LayoutHelper.createFrame(-1, 52.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        TextView r03 = new TextView(r17);
        this.cancelButton = r03;
        r03.setTextSize(1, 14.0f);
        r03.setTypeface(AndroidUtilities.bold());
        r03.setBackground(Theme.createSelectorDrawable(-12763843, 0));
        r03.setTextColor(-1);
        r03.setText(LocaleController.getString(R.string.Cancel));
        r03.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r22.addView(r03, LayoutHelper.createFrame(-2, -1, 115));
        r03.setOnClickListener(new CropEditor$$ExternalSyntheticLambda0(this));
        TextView r04 = new TextView(r17);
        this.resetButton = r04;
        r04.setTextSize(1, 14.0f);
        r04.setTypeface(AndroidUtilities.bold());
        r04.setBackground(Theme.createSelectorDrawable(-12763843, 0));
        r04.setTextColor(-1);
        r04.setText(LocaleController.getString(R.string.CropReset));
        r04.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r22.addView(r04, LayoutHelper.createFrame(-2, -1, 113));
        r04.setOnClickListener(new CropEditor$$ExternalSyntheticLambda1(this));
        TextView r05 = new TextView(r17);
        this.cropButton = r05;
        r05.setTextSize(1, 14.0f);
        r05.setTypeface(AndroidUtilities.bold());
        r05.setBackground(Theme.createSelectorDrawable(-12763843, 0));
        r05.setTextColor(-15098625);
        r05.setText(LocaleController.getString(R.string.StoryCrop));
        r05.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r22.addView(r05, LayoutHelper.createFrame(-2, -1, 117));
        r05.setOnClickListener(new CropEditor$$ExternalSyntheticLambda2(this));
    }

    private int getCurrentHeight() {
        StoryEntry r0 = this.entry;
        if (r0 != null) goto L6;
        return 1;
    L6:
        int r02 = r0.orientation;
        if (r02 == 90) goto L14;
        if (r02 == 270) goto L14;
        return this.previewView.getContentHeight();
    L14:
        return this.previewView.getContentWidth();
    }

    private int getCurrentWidth() {
        StoryEntry r0 = this.entry;
        if (r0 != null) goto L6;
        return 1;
    L6:
        int r02 = r0.orientation;
        if (r02 == 90) goto L14;
        if (r02 == 270) goto L14;
        return this.previewView.getContentWidth();
    L14:
        return this.previewView.getContentHeight();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        close();
    }

    private /* synthetic */ void lambda$new$1(View r3) {
        this.cropView.reset(true);
        this.wheel.setRotated(false);
        this.wheel.setMirrored(false);
        this.wheel.setRotation(0.0f, true);
        this.contentView.invalidate();
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        apply();
        close();
    }

    public void apply() {
        StoryEntry r0 = this.entry;
        if (r0 != null) goto L5;
        return;
    L5:
        this.applied = true;
        r0.crop = new MediaController.CropState();
        this.cropView.applyToCropState(this.entry.crop);
        StoryEntry r2 = this.entry;
        MediaController.CropState r02 = r2.crop;
        r02.orientation = r2.orientation;
    }

    public abstract void close();

    public void disappearStarts() {
        this.previewView.setCropEditorDrawing(this);
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
        this.cropView.setBottomPadding(this.controlsLayout.getPaddingBottom() + AndroidUtilities.dp(116.0f));
        super.onLayout(r4, r5, r6, r7, r8);
    }

    public void setAppearProgress(float r3) {
        if (Math.abs(this.appearProgress - r3) >= 0.001f) goto L5;
        return;
    L5:
        this.appearProgress = r3;
        this.contentView.setAlpha(r3);
        this.contentView.invalidate();
        this.cropView.areaView.setDimAlpha(0.5f * r3);
        this.cropView.areaView.setFrameAlpha(r3);
        this.cropView.areaView.invalidate();
        this.previewView.invalidate();
    }

    public void setEntry(StoryEntry r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        this.entry = r9;
        this.applied = false;
        this.closing = false;
        this.cropView.onShow();
        getLocationOnScreen(this.thisLocation);
        this.previewView.getLocationOnScreen(this.previewLocation);
        MediaController.CropState r1 = r9.crop;
        if (r1 == null) goto L7;
    L6:
        MediaController.CropState r7 = r1;
        this.cropView.start(r9.orientation, true, false, this.cropTransform, r7);
        this.wheel.setRotation(this.cropView.getRotation());
        CropRotationWheel r92 = this.wheel;
        if (r7 == null) goto L15;
        r92.setRotation(r7.cropRotate, false);
        CropRotationWheel r93 = this.wheel;
        if (r7.transformRotation == 0) goto L13;
        boolean r2 = true;
    L14:
        r93.setRotated(r2);
        this.wheel.setMirrored(r7.mirrored);
        this.animatedMirror.set(r7.mirrored, false);
    L16:
        this.cropView.updateMatrix();
        this.animatedOrientation.set(((this.lastOrientation / 360) * 360) + this.cropTransform.getOrientation(), true);
        this.contentView.setVisibility(0);
        this.contentView.invalidate();
        this.previewView.setCropEditorDrawing(this);
        return;
    L13:
        r2 = false;
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

    public void stop() {
        this.entry = null;
        this.cropView.stop();
        this.cropView.onHide();
        this.contentView.setVisibility(8);
        this.previewView.setCropEditorDrawing(null);
    }
}
