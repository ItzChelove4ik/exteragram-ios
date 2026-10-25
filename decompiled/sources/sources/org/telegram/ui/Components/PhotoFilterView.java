package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BubbleActivity;
import org.telegram.ui.Cells.PhotoEditRadioCell;
import org.telegram.ui.Cells.PhotoEditToolCell;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.FilterShaders;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.StoryRecorder;

@SuppressLint({"NewApi"})
/* loaded from: classes7.dex */
public class PhotoFilterView extends FrameLayout implements FilterShaders.FilterShadersDelegate, StoryRecorder.Touchable {
    private Bitmap bitmapMask;
    private Bitmap bitmapToEdit;
    private float blurAngle;
    private PhotoFilterBlurControl blurControl;
    private float blurExcludeBlurSize;
    private PointF blurExcludePoint;
    private float blurExcludeSize;
    private ImageView blurItem;
    private FrameLayout blurLayout;
    private TextView blurLinearButton;
    private TextView blurOffButton;
    private TextView blurRadialButton;
    private int blurType;
    private TextView cancelTextView;
    private int contrastTool;
    private float contrastValue;
    private ImageView curveItem;
    private FrameLayout curveLayout;
    private RadioButton[] curveRadioButton;
    private PhotoFilterCurvesControl curvesControl;
    private CurvesToolValue curvesToolValue;
    private TextView doneTextView;
    private FilterGLThread eglThread;
    private int enhanceTool;
    private float enhanceValue;
    private int exposureTool;
    private float exposureValue;
    private int fadeTool;
    private float fadeValue;
    private boolean filtersEmpty;
    private int gradientBottom;
    private int gradientTop;
    private int grainTool;
    private float grainValue;
    private int highlightsTool;
    private float highlightsValue;
    private boolean inBubbleMode;
    private boolean isMirrored;
    private MediaController.SavedFilterState lastState;
    private final Matrix maskMatrix;
    private final Paint maskPaint;
    private final Rect maskRect;
    private int orientation;
    private boolean ownLayout;
    private boolean ownsTextureView;
    private PaintingOverlay paintingOverlay;
    private RecyclerListView recyclerListView;
    private final Theme.ResourcesProvider resourcesProvider;
    private int rowsCount;
    private int saturationTool;
    private float saturationValue;
    private int selectedTool;
    private int shadowsTool;
    private float shadowsValue;
    private int sharpenTool;
    private float sharpenValue;
    private boolean showOriginal;
    private int softenSkinTool;
    private float softenSkinValue;
    private TextureView textureView;
    private int tintHighlightsColor;
    private int tintHighlightsTool;
    private int tintShadowsColor;
    private int tintShadowsTool;
    private FrameLayout toolsView;
    private ImageView tuneItem;
    private int vignetteTool;
    private float vignetteValue;
    private int warmthTool;
    private float warmthValue;

    /* renamed from: org.telegram.ui.Components.PhotoFilterView$1, reason: invalid class name */
    public class AnonymousClass1 extends TextureView {
        final /* synthetic */ PhotoFilterView this$0;

        public AnonymousClass1(PhotoFilterView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            View.MeasureSpec.getSize(r1);
            super.onMeasure(r1, r2);
        }

        @Override // android.view.TextureView
        public void setTransform(Matrix r3) {
            super.setTransform(r3);
            if (PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0) == null) goto L6;
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).updateUiBlurTransform(r3, getWidth(), getHeight());
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.PhotoFilterView$2, reason: invalid class name */
    public class AnonymousClass2 implements TextureView.SurfaceTextureListener {
        final /* synthetic */ PhotoFilterView this$0;
        final /* synthetic */ BlurringShader.BlurManager val$blurManager;
        final /* synthetic */ boolean val$ownLayout;

        public static /* synthetic */ void $r8$lambda$0_AEPPKuQC7xbyBCKR5gxFCpdAA(AnonymousClass2 r0) {
            r0.lambda$onSurfaceTextureSizeChanged$0();
        }

        public AnonymousClass2(PhotoFilterView r1, boolean r2, BlurringShader.BlurManager r3) {
            this.this$0 = r1;
            this.val$ownLayout = r2;
            this.val$blurManager = r3;
        }

        private /* synthetic */ void lambda$onSurfaceTextureSizeChanged$0() {
            if (PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0) == null) goto L6;
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).requestRender(false, true, false);
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r12, int r13, int r14) {
            if (PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0) != null) goto L10;
            if (r12 == null) goto L11;
            PhotoFilterView.m11735$$Nest$fputeglThread(this.this$0, new FilterGLThread(r12, PhotoFilterView.m11697$$Nest$fgetbitmapToEdit(this.this$0), PhotoFilterView.m11714$$Nest$fgetorientation(this.this$0), PhotoFilterView.m11713$$Nest$fgetisMirrored(this.this$0), null, this.val$ownLayout, this.val$blurManager, r13, r14));
            if (this.val$ownLayout == true) goto L8;
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).updateUiBlurGradient(PhotoFilterView.m11708$$Nest$fgetgradientTop(this.this$0), PhotoFilterView.m11707$$Nest$fgetgradientBottom(this.this$0));
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).updateUiBlurTransform(PhotoFilterView.m11725$$Nest$fgettextureView(this.this$0).getTransform(null), PhotoFilterView.m11725$$Nest$fgettextureView(this.this$0).getWidth(), PhotoFilterView.m11725$$Nest$fgettextureView(this.this$0).getHeight());
        L8:
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).setFilterGLThreadDelegate(this.this$0);
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).setSurfaceTextureSize(r13, r14);
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).requestRender(true, true, false);
            return;
        L11:
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r1) {
            if (PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0) == null) goto L7;
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).shutdown();
            PhotoFilterView.m11735$$Nest$fputeglThread(this.this$0, null);
            return true;
        L7:
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r1, int r2, int r3) {
            if (PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0) == null) goto L6;
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).setSurfaceTextureSize(r2, r3);
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).requestRender(false, true, false);
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).postRunnable(new PhotoFilterView$2$$ExternalSyntheticLambda0(this));
            return;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r1) {
        }
    }

    public static class CurvesToolValue {
        public int activeType;
        public CurvesValue blueCurve;
        public ByteBuffer curveBuffer;
        public CurvesValue greenCurve;
        public CurvesValue luminanceCurve;
        public CurvesValue redCurve;

        public CurvesToolValue() {
            this.luminanceCurve = new CurvesValue();
            this.redCurve = new CurvesValue();
            this.greenCurve = new CurvesValue();
            this.blueCurve = new CurvesValue();
            ByteBuffer r0 = ByteBuffer.allocateDirect(800);
            this.curveBuffer = r0;
            r0.order(ByteOrder.LITTLE_ENDIAN);
        }

        public void fillBuffer() {
            this.curveBuffer.position(0);
            float[] r0 = this.luminanceCurve.getDataPoints();
            float[] r2 = this.redCurve.getDataPoints();
            float[] r3 = this.greenCurve.getDataPoints();
            float[] r4 = this.blueCurve.getDataPoints();
            int r5 = 0;
        L3:
            ByteBuffer r6 = this.curveBuffer;
            if (r5 >= 200) goto L6;
            r6.put((byte) (r2[r5] * 255.0f));
            this.curveBuffer.put((byte) (r3[r5] * 255.0f));
            this.curveBuffer.put((byte) (r4[r5] * 255.0f));
            this.curveBuffer.put((byte) (r0[r5] * 255.0f));
            r5 = r5 + 1;
            goto L3
        L6:
            r6.position(0);
        }

        public void readParams(InputSerializedData r2, boolean r3) {
            this.luminanceCurve.readParams(r2, r3);
            this.redCurve.readParams(r2, r3);
            this.greenCurve.readParams(r2, r3);
            this.blueCurve.readParams(r2, r3);
        }

        public void serializeToStream(OutputSerializedData r2) {
            this.luminanceCurve.serializeToStream(r2);
            this.redCurve.serializeToStream(r2);
            this.greenCurve.serializeToStream(r2);
            this.blueCurve.serializeToStream(r2);
        }

        public boolean shouldBeSkipped() {
            if (this.luminanceCurve.isDefault() == true) goto L5;
            return false;
        L5:
            if (this.redCurve.isDefault() == true) goto L7;
            return false;
        L7:
            if (this.greenCurve.isDefault() == true) goto L9;
            return false;
        L9:
            if (this.blueCurve.isDefault() == false) goto L16;
            return true;
        L16:
            return false;
        }
    }

    public static class CurvesValue {
        public float blacksLevel;
        public float[] cachedDataPoints;
        public float highlightsLevel;
        public float midtonesLevel;
        public float previousBlacksLevel;
        public float previousHighlightsLevel;
        public float previousMidtonesLevel;
        public float previousShadowsLevel;
        public float previousWhitesLevel;
        public float shadowsLevel;
        public float whitesLevel;

        public CurvesValue() {
            this.blacksLevel = 0.0f;
            this.shadowsLevel = 25.0f;
            this.midtonesLevel = 50.0f;
            this.highlightsLevel = 75.0f;
            this.whitesLevel = 100.0f;
            this.previousBlacksLevel = 0.0f;
            this.previousShadowsLevel = 25.0f;
            this.previousMidtonesLevel = 50.0f;
            this.previousHighlightsLevel = 75.0f;
            this.previousWhitesLevel = 100.0f;
        }

        public float[] getDataPoints() {
            if (this.cachedDataPoints != null) goto L6;
            interpolateCurve();
        L6:
            return this.cachedDataPoints;
        }

        public float[] interpolateCurve() {
            float r1 = this.blacksLevel;
            float r4 = this.shadowsLevel / 100.0f;
            float r5 = this.midtonesLevel / 100.0f;
            float r6 = this.highlightsLevel / 100.0f;
            float r7 = this.whitesLevel;
            int r9 = 0;
            int r3 = 2;
            int r12 = 5;
            char r42 = '\f';
            float[] r2 = {-0.001f, r1 / 100.0f, 0.0f, r1 / 100.0f, 0.25f, r4, 0.5f, r5, 0.75f, r6, 1.0f, r7 / 100.0f, 1.001f, r7 / 100.0f};
            ArrayList r72 = new ArrayList(100);
            ArrayList r13 = new ArrayList(100);
            r13.add(Float.valueOf(r2[0]));
            r13.add(Float.valueOf(r2[1]));
            int r14 = 1;
        L3:
            if (r14 >= r12) goto L15;
            int r15 = (r14 - 1) * r3;
            float r16 = r2[r15];
            float r152 = r2[r15 + 1];
            int r17 = r14 * 2;
            float r18 = r2[r17];
            float r172 = r2[r17 + 1];
            int r19 = r14 + 1;
            int r20 = r19 * 2;
            float r21 = r2[r20];
            float r202 = r2[r20 + 1];
            int r142 = (r14 + 2) * r3;
            float r22 = r2[r142];
            float r143 = r2[r142 + 1];
            int r110 = 1;
        L5:
            if (r110 >= 100) goto L14;
            int r23 = r3;
            float r32 = r110 * 0.01f;
            float r24 = r32 * r32;
            float r25 = r24 * r32;
            float r27 = ((((r18 * 2.0f) + ((r21 - r16) * r32)) + (((((r16 * 2.0f) - (r18 * 5.0f)) + (r21 * 4.0f)) - r22) * r24)) + (((((r18 * 3.0f) - r16) - (r21 * 3.0f)) + r22) * r25)) * 0.5f;
            float r33 = Math.max(0.0f, Math.min(1.0f, ((((r172 * 2.0f) + ((r202 - r152) * r32)) + (((((2.0f * r152) - (5.0f * r172)) + (4.0f * r202)) - r143) * r24)) + (((((r172 * 3.0f) - r152) - (3.0f * r202)) + r143) * r25)) * 0.5f));
            if (r27 <= r16) goto L9;
            char r242 = r42;
            r13.add(Float.valueOf(r27));
            r13.add(Float.valueOf(r33));
        L11:
            if (((r110 - 1) % 2) != 0) goto L13;
            r72.add(Float.valueOf(r33));
        L13:
            r110 = r110 + 1;
            r3 = r23;
            r42 = r242;
            goto L5
        L9:
            r242 = r42;
            goto L11
        L14:
            r13.add(Float.valueOf(r21));
            r13.add(Float.valueOf(r202));
            r14 = r19;
            r12 = 5;
            goto L3
        L15:
            r13.add(Float.valueOf(r2[r42]));
            r13.add(Float.valueOf(r2[13]));
            this.cachedDataPoints = new float[r72.size()];
            int r111 = 0;
        L16:
            float[] r26 = this.cachedDataPoints;
            if (r111 >= r26.length) goto L19;
            r26[r111] = ((Float) r72.get(r111)).floatValue();
            r111 = r111 + 1;
            goto L16
        L19:
            int r0 = r13.size();
            float[] r112 = new float[r0];
        L20:
            if (r9 >= r0) goto L22;
            r112[r9] = ((Float) r13.get(r9)).floatValue();
            r9 = r9 + 1;
            goto L20
        L22:
            return r112;
        }

        public boolean isDefault() {
            if (Math.abs(this.blacksLevel - 0.0f) < 1.0E-5d) goto L5;
            return false;
        L5:
            if (Math.abs(this.shadowsLevel - 25.0f) < 1.0E-5d) goto L7;
            return false;
        L7:
            if (Math.abs(this.midtonesLevel - 50.0f) < 1.0E-5d) goto L9;
            return false;
        L9:
            if (Math.abs(this.highlightsLevel - 75.0f) < 1.0E-5d) goto L11;
            return false;
        L11:
            if (Math.abs(this.whitesLevel - 100.0f) >= 1.0E-5d) goto L19;
            return true;
        L19:
            return false;
        }

        public void readParams(InputSerializedData r2, boolean r3) {
            float r0 = r2.readFloat(r3);
            this.previousBlacksLevel = r0;
            this.blacksLevel = r0;
            float r02 = r2.readFloat(r3);
            this.previousShadowsLevel = r02;
            this.shadowsLevel = r02;
            float r03 = r2.readFloat(r3);
            this.previousMidtonesLevel = r03;
            this.midtonesLevel = r03;
            float r04 = r2.readFloat(r3);
            this.previousHighlightsLevel = r04;
            this.highlightsLevel = r04;
            float r22 = r2.readFloat(r3);
            this.previousWhitesLevel = r22;
            this.whitesLevel = r22;
        }

        public void serializeToStream(OutputSerializedData r2) {
            r2.writeFloat(this.blacksLevel);
            r2.writeFloat(this.shadowsLevel);
            r2.writeFloat(this.midtonesLevel);
            r2.writeFloat(this.highlightsLevel);
            r2.writeFloat(this.whitesLevel);
        }
    }

    public static class EnhanceView extends View {
        private boolean allowTouch;
        private StaticLayout bottomText;
        private float bottomTextLeft;
        private TextPaint bottomTextPaint;
        private float bottomTextWidth;
        private long downTime;
        private PhotoFilterView filterView;
        private Runnable hide;
        private float lastTouchX;
        private float lastTouchY;
        private float lastVibrateValue;
        private Runnable requestFilterView;
        private AnimatedFloat showT;
        private boolean shown;
        private StaticLayout topText;
        private float topTextLeft;
        private TextPaint topTextPaint;
        private float topTextWidth;
        private boolean tracking;

        /* renamed from: $r8$lambda$Ct-lNek8MOEapJ5dZuNFBzhmlUY, reason: not valid java name */
        public static /* synthetic */ void m11750$r8$lambda$CtlNek8MOEapJ5dZuNFBzhmlUY(EnhanceView r0) {
            r0.lambda$new$0();
        }

        public EnhanceView(Context r9, Runnable r10) {
            super(r9);
            this.topTextPaint = new TextPaint(1);
            this.bottomTextPaint = new TextPaint(1);
            this.showT = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.hide = new PhotoFilterView$EnhanceView$$ExternalSyntheticLambda0(this);
            this.requestFilterView = r10;
        }

        private /* synthetic */ void lambda$new$0() {
            this.shown = false;
            invalidate();
        }

        private void updateBottomText() {
            PhotoFilterView r0 = this.filterView;
            float r1 = 0.0f;
            if (r0 != null) goto L5;
            float r02 = 0.0f;
        L6:
            StaticLayout r2 = new StaticLayout(_UrlKt.FRAGMENT_ENCODE_SET + Math.round(r02 * 100.0f), this.bottomTextPaint, getMeasuredWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.bottomText = r2;
            if (r2.getLineCount() <= 0) goto L9;
            float r03 = this.bottomText.getLineWidth(0);
        L10:
            this.bottomTextWidth = r03;
            if (this.bottomText.getLineCount() <= 0) goto L13;
            r1 = this.bottomText.getLineLeft(0);
        L13:
            this.bottomTextLeft = r1;
            invalidate();
            return;
        L9:
            r03 = 0.0f;
            goto L10
        L5:
            r02 = r0.getEnhanceValue();
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            float r0 = this.showT.set(this.shown);
            if (r0 > 0.0f) goto L5;
            return;
        L5:
            if (this.topText != null) goto L7;
            return;
        L7:
            if (this.bottomText == null) goto L12;
            r10.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r0 * 255.0f), 31);
            r10.save();
            r10.translate(((getWidth() - this.topTextWidth) / 2.0f) - this.topTextLeft, getHeight() * 0.22f);
            this.topText.draw(r10);
            r10.restore();
            r10.save();
            r10.translate(((getWidth() - this.bottomTextWidth) / 2.0f) - this.bottomTextLeft, (getHeight() * 0.22f) + AndroidUtilities.dp(60.0f));
            this.bottomText.draw(r10);
            r10.restore();
            r10.restore();
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r11, int r12) {
            setMeasuredDimension(View.MeasureSpec.getSize(r11), View.MeasureSpec.getSize(r12));
            this.topTextPaint.setColor(-1);
            float r1 = 0.0f;
            this.topTextPaint.setShadowLayer(AndroidUtilities.dp(8.0f), 0.0f, 0.0f, 805306368);
            this.topTextPaint.setTextSize(AndroidUtilities.dp(34.0f));
            this.bottomTextPaint.setColor(-1);
            this.bottomTextPaint.setShadowLayer(AndroidUtilities.dp(12.0f), 0.0f, 0.0f, 805306368);
            this.bottomTextPaint.setTextSize(AndroidUtilities.dp(58.0f));
            if (this.topText != null) goto L13;
            StaticLayout r2 = new StaticLayout(LocaleController.getString(R.string.Enhance), this.topTextPaint, getMeasuredWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.topText = r2;
            if (r2.getLineCount() <= 0) goto L7;
            float r112 = this.topText.getLineWidth(0);
        L8:
            this.topTextWidth = r112;
            if (this.topText.getLineCount() <= 0) goto L11;
            r1 = this.topText.getLineLeft(0);
        L11:
            this.topTextLeft = r1;
            return;
        L7:
            r112 = 0.0f;
            goto L8
        }

        public boolean onTouch(MotionEvent r11) {
            if (this.allowTouch == false) goto L55;
            if (r11.getPointerCount() != 1) goto L55;
            int r0 = r11.getAction();
            if (r0 != 0) goto L13;
            this.tracking = false;
            this.downTime = System.currentTimeMillis();
            this.lastTouchX = r11.getX();
            this.lastTouchY = r11.getY();
            PhotoFilterView r112 = this.filterView;
            if (r112 == null) goto L11;
            this.lastVibrateValue = r112.getEnhanceValue();
        L11:
            return true;
        L13:
            if (r0 != 2) goto L47;
            float r02 = r11.getX();
            float r113 = r11.getY();
            if (this.tracking == true) goto L24;
            if ((System.currentTimeMillis() - this.downTime) > ViewConfiguration.getLongPressTimeout()) goto L24;
            if (Math.abs(this.lastTouchY - r113) >= Math.abs(this.lastTouchX - r02)) goto L24;
            if (Math.abs(this.lastTouchX - r02) <= AndroidUtilities.touchSlop) goto L24;
            this.tracking = true;
            AndroidUtilities.cancelRunOnUIThread(this.hide);
            this.shown = true;
            invalidate();
        L24:
            if (this.tracking == false) goto L46;
            float r5 = r02 - this.lastTouchX;
            if (this.filterView != null) goto L28;
            this.requestFilterView.run();
        L28:
            PhotoFilterView r6 = this.filterView;
            if (r6 != null) goto L32;
            this.tracking = false;
            return false;
        L32:
            float r62 = r6.getEnhanceValue();
            float r52 = Utilities.clamp((r5 / (AndroidUtilities.displaySize.x * 0.8f)) + r62, 1.0f, 0.0f);
            int r8 = Math.round(r52 * 100.0f);
            int r63 = Math.round(r62 * 100.0f);
            int r7 = Math.round(this.lastVibrateValue * 100.0f);
            if (r8 != r63) goto L35;
        L39:
            int r2 = Math.abs(r8 - r7);
            if (SharedConfig.getDevicePerformanceClass() != 2) goto L42;
            int r3 = 5;
        L43:
            if (r2 <= r3) goto L45;
            AndroidUtilities.vibrateCursor(this);
            this.lastVibrateValue = r52;
        L45:
            this.filterView.setEnhanceValue(r52);
            updateBottomText();
            goto L46
        L42:
            r3 = 10;
            goto L43
        L35:
            if (r8 == 100) goto L59;
            if (r8 != 0) goto L39;
        L59:
            performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L58
        L38:
            this.lastVibrateValue = r52;
        L46:
            this.lastTouchX = r02;
            this.lastTouchY = r113;
        L57:
            return false;
        L47:
            if (r0 == 1) goto L49;
            if (r0 != 3) goto L57;
        L49:
            this.tracking = false;
            this.downTime = -1;
            PhotoFilterView r114 = this.filterView;
            if (r114 == null) goto L52;
            this.lastVibrateValue = r114.getEnhanceValue();
        L52:
            AndroidUtilities.runOnUIThread(this.hide, 600);
            return false;
        L55:
            if (this.shown == false) goto L57;
            this.shown = false;
            invalidate();
            goto L57
        }

        public void setAllowTouch(boolean r1) {
            this.allowTouch = r1;
        }

        public void setFilterView(PhotoFilterView r1) {
            this.filterView = r1;
        }
    }

    public static class RecyclerListViewWithShadows extends RecyclerListView {
        private boolean bottom;
        private AnimatedFloat bottomAlpha;
        private final Paint bottomPaint;
        private boolean top;
        private AnimatedFloat topAlpha;
        private final Paint topPaint;

        public RecyclerListViewWithShadows(Context r23) {
            super(r23);
            Paint r1 = new Paint(1);
            this.topPaint = r1;
            Paint r3 = new Paint(1);
            this.bottomPaint = r3;
            this.topAlpha = new AnimatedFloat(this);
            this.bottomAlpha = new AnimatedFloat(this);
            Shader.TileMode r21 = Shader.TileMode.CLAMP;
            r1.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(8.0f), new int[]{-16777216, 0}, new float[]{0.0f, 1.0f}, r21));
            r3.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(8.0f), new int[]{0, -16777216}, new float[]{0.0f, 1.0f}, r21));
        }

        private void updateAlphas() {
            boolean r0 = canScrollVertically(-1);
            boolean r1 = canScrollVertically(1);
            if (r0 == this.top) goto L5;
        L8:
            this.top = r0;
            this.bottom = r1;
            invalidate();
            return;
        L5:
            if (r1 != this.bottom) goto L8;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r19) {
            super.dispatchDraw(r19);
            AnimatedFloat r1 = this.topAlpha;
            float r3 = 1.0f;
            if (this.top == false) goto L5;
            float r2 = 1.0f;
        L6:
            this.topPaint.setAlpha((int) (r1.set(r2) * 255.0f));
            r19.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(8.0f), this.topPaint);
            AnimatedFloat r22 = this.bottomAlpha;
            if (this.bottom == true) goto L10;
            r3 = 0.0f;
        L10:
            this.bottomPaint.setAlpha((int) (r22.set(r3) * 255.0f));
            r19.save();
            r19.translate(0.0f, getHeight() - AndroidUtilities.dp(8.0f));
            r19.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(8.0f), this.bottomPaint);
            r19.restore();
            return;
        L5:
            r2 = 0.0f;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
            updateAlphas();
        }
    }

    public class ToolsAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ PhotoFilterView this$0;

        /* renamed from: $r8$lambda$b-SXTMkBsuHJebyDUm9Emm-A6Cg, reason: not valid java name */
        public static /* synthetic */ void m11751$r8$lambda$bSXTMkBsuHJebyDUm9EmmA6Cg(ToolsAdapter r0, View r1) {
            r0.lambda$onCreateViewHolder$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$fKV5VbKOV6eytJpXWvxip8hR1lU(ToolsAdapter r0, int r1, int r2) {
            r0.lambda$onCreateViewHolder$0(r1, r2);
        }

        public ToolsAdapter(PhotoFilterView r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0(int r3, int r4) {
            int r0 = PhotoFilterView.m11701$$Nest$fgetenhanceTool(this.this$0);
            PhotoFilterView r1 = this.this$0;
            if (r3 != r0) goto L5;
            PhotoFilterView.m11736$$Nest$fputenhanceValue(r1, r4);
        L39:
            if (PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0) == null) goto L41;
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).requestRender(true);
        L41:
            PhotoFilterView.m11749$$Nest$mupdateFiltersEmpty(this.this$0);
            return;
        L5:
            int r02 = PhotoFilterView.m11711$$Nest$fgethighlightsTool(r1);
            PhotoFilterView r12 = this.this$0;
            if (r3 != r02) goto L8;
            PhotoFilterView.m11740$$Nest$fputhighlightsValue(r12, r4);
            goto L39
        L8:
            int r03 = PhotoFilterView.m11698$$Nest$fgetcontrastTool(r12);
            PhotoFilterView r13 = this.this$0;
            if (r3 != r03) goto L11;
            PhotoFilterView.m11734$$Nest$fputcontrastValue(r13, r4);
            goto L39
        L11:
            int r04 = PhotoFilterView.m11703$$Nest$fgetexposureTool(r13);
            PhotoFilterView r14 = this.this$0;
            if (r3 != r04) goto L14;
            PhotoFilterView.m11737$$Nest$fputexposureValue(r14, r4);
            goto L39
        L14:
            int r05 = PhotoFilterView.m11732$$Nest$fgetwarmthTool(r14);
            PhotoFilterView r15 = this.this$0;
            if (r3 != r05) goto L17;
            PhotoFilterView.m11748$$Nest$fputwarmthValue(r15, r4);
            goto L39
        L17:
            int r06 = PhotoFilterView.m11717$$Nest$fgetsaturationTool(r15);
            PhotoFilterView r16 = this.this$0;
            if (r3 != r06) goto L20;
            PhotoFilterView.m11741$$Nest$fputsaturationValue(r16, r4);
            goto L39
        L20:
            int r07 = PhotoFilterView.m11730$$Nest$fgetvignetteTool(r16);
            PhotoFilterView r17 = this.this$0;
            if (r3 != r07) goto L23;
            PhotoFilterView.m11747$$Nest$fputvignetteValue(r17, r4);
            goto L39
        L23:
            int r08 = PhotoFilterView.m11719$$Nest$fgetshadowsTool(r17);
            PhotoFilterView r18 = this.this$0;
            if (r3 != r08) goto L26;
            PhotoFilterView.m11742$$Nest$fputshadowsValue(r18, r4);
            goto L39
        L26:
            int r09 = PhotoFilterView.m11709$$Nest$fgetgrainTool(r18);
            PhotoFilterView r19 = this.this$0;
            if (r3 != r09) goto L29;
            PhotoFilterView.m11739$$Nest$fputgrainValue(r19, r4);
            goto L39
        L29:
            int r010 = PhotoFilterView.m11721$$Nest$fgetsharpenTool(r19);
            PhotoFilterView r110 = this.this$0;
            if (r3 != r010) goto L32;
            PhotoFilterView.m11743$$Nest$fputsharpenValue(r110, r4);
            goto L39
        L32:
            int r011 = PhotoFilterView.m11705$$Nest$fgetfadeTool(r110);
            PhotoFilterView r111 = this.this$0;
            if (r3 != r011) goto L36;
            PhotoFilterView.m11738$$Nest$fputfadeValue(r111, r4);
            goto L39
        L36:
            if (r3 != PhotoFilterView.m11723$$Nest$fgetsoftenSkinTool(r111)) goto L39;
            PhotoFilterView.m11744$$Nest$fputsoftenSkinValue(this.this$0, r4);
            goto L39
        }

        private /* synthetic */ void lambda$onCreateViewHolder$1(View r4) {
            PhotoEditRadioCell r42 = (PhotoEditRadioCell) r4;
            int r0 = ((Integer) r42.getTag()).intValue();
            int r1 = PhotoFilterView.m11729$$Nest$fgettintShadowsTool(this.this$0);
            PhotoFilterView r2 = this.this$0;
            if (r0 != r1) goto L5;
            PhotoFilterView.m11746$$Nest$fputtintShadowsColor(r2, r42.getCurrentColor());
        L7:
            if (PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0) == null) goto L9;
            PhotoFilterView.m11700$$Nest$fgeteglThread(this.this$0).requestRender(false);
        L9:
            PhotoFilterView.m11749$$Nest$mupdateFiltersEmpty(this.this$0);
            return;
        L5:
            PhotoFilterView.m11745$$Nest$fputtintHighlightsColor(r2, r42.getCurrentColor());
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return PhotoFilterView.m11716$$Nest$fgetrowsCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != PhotoFilterView.m11729$$Nest$fgettintShadowsTool(this.this$0)) goto L5;
            return 1;
        L5:
            if (r2 == PhotoFilterView.m11727$$Nest$fgettintHighlightsTool(this.this$0)) goto L11;
            return 0;
        L11:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            int r0 = r5.getItemViewType();
            if (r0 != 0) goto L5;
            PhotoEditToolCell r52 = (PhotoEditToolCell) r5.itemView;
            r52.setTag(Integer.valueOf(r6));
            if (r6 != PhotoFilterView.m11701$$Nest$fgetenhanceTool(this.this$0)) goto L20;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Enhance), PhotoFilterView.m11702$$Nest$fgetenhanceValue(this.this$0), 0, 100);
            return;
        L20:
            if (r6 != PhotoFilterView.m11711$$Nest$fgethighlightsTool(this.this$0)) goto L24;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Highlights), PhotoFilterView.m11712$$Nest$fgethighlightsValue(this.this$0), -100, 100);
            return;
        L24:
            if (r6 != PhotoFilterView.m11698$$Nest$fgetcontrastTool(this.this$0)) goto L28;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Contrast), PhotoFilterView.m11699$$Nest$fgetcontrastValue(this.this$0), -100, 100);
            return;
        L28:
            if (r6 != PhotoFilterView.m11703$$Nest$fgetexposureTool(this.this$0)) goto L32;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Exposure), PhotoFilterView.m11704$$Nest$fgetexposureValue(this.this$0), -100, 100);
            return;
        L32:
            if (r6 != PhotoFilterView.m11732$$Nest$fgetwarmthTool(this.this$0)) goto L36;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Warmth), PhotoFilterView.m11733$$Nest$fgetwarmthValue(this.this$0), -100, 100);
            return;
        L36:
            if (r6 != PhotoFilterView.m11717$$Nest$fgetsaturationTool(this.this$0)) goto L40;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Saturation), PhotoFilterView.m11718$$Nest$fgetsaturationValue(this.this$0), -100, 100);
            return;
        L40:
            if (r6 != PhotoFilterView.m11730$$Nest$fgetvignetteTool(this.this$0)) goto L44;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Vignette), PhotoFilterView.m11731$$Nest$fgetvignetteValue(this.this$0), 0, 100);
            return;
        L44:
            if (r6 != PhotoFilterView.m11719$$Nest$fgetshadowsTool(this.this$0)) goto L48;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Shadows), PhotoFilterView.m11720$$Nest$fgetshadowsValue(this.this$0), -100, 100);
            return;
        L48:
            if (r6 != PhotoFilterView.m11709$$Nest$fgetgrainTool(this.this$0)) goto L52;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Grain), PhotoFilterView.m11710$$Nest$fgetgrainValue(this.this$0), 0, 100);
            return;
        L52:
            if (r6 != PhotoFilterView.m11721$$Nest$fgetsharpenTool(this.this$0)) goto L56;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Sharpen), PhotoFilterView.m11722$$Nest$fgetsharpenValue(this.this$0), 0, 100);
            return;
        L56:
            if (r6 != PhotoFilterView.m11705$$Nest$fgetfadeTool(this.this$0)) goto L60;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.Fade), PhotoFilterView.m11706$$Nest$fgetfadeValue(this.this$0), 0, 100);
            return;
        L60:
            if (r6 != PhotoFilterView.m11723$$Nest$fgetsoftenSkinTool(this.this$0)) goto L64;
            r52.setIconAndTextAndValue(LocaleController.getString(R.string.SoftenSkin), PhotoFilterView.m11724$$Nest$fgetsoftenSkinValue(this.this$0), 0, 100);
            return;
        L64:
            return;
        L5:
            if (r0 != 1) goto L65;
            PhotoEditRadioCell r53 = (PhotoEditRadioCell) r5.itemView;
            r53.setTag(Integer.valueOf(r6));
            if (r6 != PhotoFilterView.m11729$$Nest$fgettintShadowsTool(this.this$0)) goto L12;
            r53.setIconAndTextAndValue(LocaleController.getString(R.string.TintShadows), 0, PhotoFilterView.m11728$$Nest$fgettintShadowsColor(this.this$0));
            return;
        L12:
            if (r6 != PhotoFilterView.m11727$$Nest$fgettintHighlightsTool(this.this$0)) goto L63;
            r53.setIconAndTextAndValue(LocaleController.getString(R.string.TintHighlights), 0, PhotoFilterView.m11726$$Nest$fgettintHighlightsColor(this.this$0));
            return;
        L63:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            PhotoEditToolCell r22 = new PhotoEditToolCell(this.mContext, PhotoFilterView.m11715$$Nest$fgetresourcesProvider(this.this$0));
            r22.setSeekBarDelegate(new PhotoFilterView$ToolsAdapter$$ExternalSyntheticLambda0(this));
            PhotoEditRadioCell r23 = r22;
        L6:
            return new RecyclerListView.Holder(r23);
        L4:
            PhotoEditRadioCell r24 = new PhotoEditRadioCell(this.mContext);
            r24.setOnClickListener(new PhotoFilterView$ToolsAdapter$$ExternalSyntheticLambda1(this));
            r23 = r24;
            goto L6
        }
    }

    /* renamed from: $r8$lambda$25RL1QeJcT2ycWp3TkfBPFjG-38, reason: not valid java name */
    public static /* synthetic */ void m11694$r8$lambda$25RL1QeJcT2ycWp3TkfBPFjG38(PhotoFilterView r0, FilterGLThread r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$N-53pfEyQrIvoxT8__cC1sWkeAQ, reason: not valid java name */
    public static /* synthetic */ void m11695$r8$lambda$N53pfEyQrIvoxT8__cC1sWkeAQ(PhotoFilterView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$Nll8LalZqkJ3NPIiQ9wXEA6UCHg(PhotoFilterView r0, PointF r1, float r2, float r3, float r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$S27SZ2nsN8rhtlMOGd5kXm0iuto(PhotoFilterView r0, View r1) {
        r0.lambda$new$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$UF6wB1YcDwXLUELf3jMZ0ltPCX4(PhotoFilterView r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$WguH3U_ewccmEFe2s6jT_JiE7y0(PhotoFilterView r0, View r1) {
        r0.lambda$new$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$cT6MmAFzKJI6XvJETD9tRHaR6hc(PhotoFilterView r0, View r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$l2FTMscXDwsWuX44jBVY8DxTlVM(PhotoFilterView r0) {
        r0.lambda$new$2();
    }

    /* renamed from: $r8$lambda$o1-rY-_MYNt9f0mgDgzxSp5YRDM, reason: not valid java name */
    public static /* synthetic */ void m11696$r8$lambda$o1rY_MYNt9f0mgDgzxSp5YRDM(PhotoFilterView r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$z5_sK_CoNVLG_aDS8eNNOGMz1KQ(PhotoFilterView r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: -$$Nest$fgetbitmapToEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m11697$$Nest$fgetbitmapToEdit(PhotoFilterView r0) {
        return r0.bitmapToEdit;
    }

    /* renamed from: -$$Nest$fgetcontrastTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11698$$Nest$fgetcontrastTool(PhotoFilterView r0) {
        return r0.contrastTool;
    }

    /* renamed from: -$$Nest$fgetcontrastValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11699$$Nest$fgetcontrastValue(PhotoFilterView r0) {
        return r0.contrastValue;
    }

    /* renamed from: -$$Nest$fgeteglThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FilterGLThread m11700$$Nest$fgeteglThread(PhotoFilterView r0) {
        return r0.eglThread;
    }

    /* renamed from: -$$Nest$fgetenhanceTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11701$$Nest$fgetenhanceTool(PhotoFilterView r0) {
        return r0.enhanceTool;
    }

    /* renamed from: -$$Nest$fgetenhanceValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11702$$Nest$fgetenhanceValue(PhotoFilterView r0) {
        return r0.enhanceValue;
    }

    /* renamed from: -$$Nest$fgetexposureTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11703$$Nest$fgetexposureTool(PhotoFilterView r0) {
        return r0.exposureTool;
    }

    /* renamed from: -$$Nest$fgetexposureValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11704$$Nest$fgetexposureValue(PhotoFilterView r0) {
        return r0.exposureValue;
    }

    /* renamed from: -$$Nest$fgetfadeTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11705$$Nest$fgetfadeTool(PhotoFilterView r0) {
        return r0.fadeTool;
    }

    /* renamed from: -$$Nest$fgetfadeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11706$$Nest$fgetfadeValue(PhotoFilterView r0) {
        return r0.fadeValue;
    }

    /* renamed from: -$$Nest$fgetgradientBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11707$$Nest$fgetgradientBottom(PhotoFilterView r0) {
        return r0.gradientBottom;
    }

    /* renamed from: -$$Nest$fgetgradientTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11708$$Nest$fgetgradientTop(PhotoFilterView r0) {
        return r0.gradientTop;
    }

    /* renamed from: -$$Nest$fgetgrainTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11709$$Nest$fgetgrainTool(PhotoFilterView r0) {
        return r0.grainTool;
    }

    /* renamed from: -$$Nest$fgetgrainValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11710$$Nest$fgetgrainValue(PhotoFilterView r0) {
        return r0.grainValue;
    }

    /* renamed from: -$$Nest$fgethighlightsTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11711$$Nest$fgethighlightsTool(PhotoFilterView r0) {
        return r0.highlightsTool;
    }

    /* renamed from: -$$Nest$fgethighlightsValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11712$$Nest$fgethighlightsValue(PhotoFilterView r0) {
        return r0.highlightsValue;
    }

    /* renamed from: -$$Nest$fgetisMirrored, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11713$$Nest$fgetisMirrored(PhotoFilterView r0) {
        return r0.isMirrored;
    }

    /* renamed from: -$$Nest$fgetorientation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11714$$Nest$fgetorientation(PhotoFilterView r0) {
        return r0.orientation;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m11715$$Nest$fgetresourcesProvider(PhotoFilterView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetrowsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11716$$Nest$fgetrowsCount(PhotoFilterView r0) {
        return r0.rowsCount;
    }

    /* renamed from: -$$Nest$fgetsaturationTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11717$$Nest$fgetsaturationTool(PhotoFilterView r0) {
        return r0.saturationTool;
    }

    /* renamed from: -$$Nest$fgetsaturationValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11718$$Nest$fgetsaturationValue(PhotoFilterView r0) {
        return r0.saturationValue;
    }

    /* renamed from: -$$Nest$fgetshadowsTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11719$$Nest$fgetshadowsTool(PhotoFilterView r0) {
        return r0.shadowsTool;
    }

    /* renamed from: -$$Nest$fgetshadowsValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11720$$Nest$fgetshadowsValue(PhotoFilterView r0) {
        return r0.shadowsValue;
    }

    /* renamed from: -$$Nest$fgetsharpenTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11721$$Nest$fgetsharpenTool(PhotoFilterView r0) {
        return r0.sharpenTool;
    }

    /* renamed from: -$$Nest$fgetsharpenValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11722$$Nest$fgetsharpenValue(PhotoFilterView r0) {
        return r0.sharpenValue;
    }

    /* renamed from: -$$Nest$fgetsoftenSkinTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11723$$Nest$fgetsoftenSkinTool(PhotoFilterView r0) {
        return r0.softenSkinTool;
    }

    /* renamed from: -$$Nest$fgetsoftenSkinValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11724$$Nest$fgetsoftenSkinValue(PhotoFilterView r0) {
        return r0.softenSkinValue;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m11725$$Nest$fgettextureView(PhotoFilterView r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgettintHighlightsColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11726$$Nest$fgettintHighlightsColor(PhotoFilterView r0) {
        return r0.tintHighlightsColor;
    }

    /* renamed from: -$$Nest$fgettintHighlightsTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11727$$Nest$fgettintHighlightsTool(PhotoFilterView r0) {
        return r0.tintHighlightsTool;
    }

    /* renamed from: -$$Nest$fgettintShadowsColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11728$$Nest$fgettintShadowsColor(PhotoFilterView r0) {
        return r0.tintShadowsColor;
    }

    /* renamed from: -$$Nest$fgettintShadowsTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11729$$Nest$fgettintShadowsTool(PhotoFilterView r0) {
        return r0.tintShadowsTool;
    }

    /* renamed from: -$$Nest$fgetvignetteTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11730$$Nest$fgetvignetteTool(PhotoFilterView r0) {
        return r0.vignetteTool;
    }

    /* renamed from: -$$Nest$fgetvignetteValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11731$$Nest$fgetvignetteValue(PhotoFilterView r0) {
        return r0.vignetteValue;
    }

    /* renamed from: -$$Nest$fgetwarmthTool, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11732$$Nest$fgetwarmthTool(PhotoFilterView r0) {
        return r0.warmthTool;
    }

    /* renamed from: -$$Nest$fgetwarmthValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m11733$$Nest$fgetwarmthValue(PhotoFilterView r0) {
        return r0.warmthValue;
    }

    /* renamed from: -$$Nest$fputcontrastValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11734$$Nest$fputcontrastValue(PhotoFilterView r0, float r1) {
        r0.contrastValue = r1;
    }

    /* renamed from: -$$Nest$fputeglThread, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11735$$Nest$fputeglThread(PhotoFilterView r0, FilterGLThread r1) {
        r0.eglThread = r1;
    }

    /* renamed from: -$$Nest$fputenhanceValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11736$$Nest$fputenhanceValue(PhotoFilterView r0, float r1) {
        r0.enhanceValue = r1;
    }

    /* renamed from: -$$Nest$fputexposureValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11737$$Nest$fputexposureValue(PhotoFilterView r0, float r1) {
        r0.exposureValue = r1;
    }

    /* renamed from: -$$Nest$fputfadeValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11738$$Nest$fputfadeValue(PhotoFilterView r0, float r1) {
        r0.fadeValue = r1;
    }

    /* renamed from: -$$Nest$fputgrainValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11739$$Nest$fputgrainValue(PhotoFilterView r0, float r1) {
        r0.grainValue = r1;
    }

    /* renamed from: -$$Nest$fputhighlightsValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11740$$Nest$fputhighlightsValue(PhotoFilterView r0, float r1) {
        r0.highlightsValue = r1;
    }

    /* renamed from: -$$Nest$fputsaturationValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11741$$Nest$fputsaturationValue(PhotoFilterView r0, float r1) {
        r0.saturationValue = r1;
    }

    /* renamed from: -$$Nest$fputshadowsValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11742$$Nest$fputshadowsValue(PhotoFilterView r0, float r1) {
        r0.shadowsValue = r1;
    }

    /* renamed from: -$$Nest$fputsharpenValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11743$$Nest$fputsharpenValue(PhotoFilterView r0, float r1) {
        r0.sharpenValue = r1;
    }

    /* renamed from: -$$Nest$fputsoftenSkinValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11744$$Nest$fputsoftenSkinValue(PhotoFilterView r0, float r1) {
        r0.softenSkinValue = r1;
    }

    /* renamed from: -$$Nest$fputtintHighlightsColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11745$$Nest$fputtintHighlightsColor(PhotoFilterView r0, int r1) {
        r0.tintHighlightsColor = r1;
    }

    /* renamed from: -$$Nest$fputtintShadowsColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11746$$Nest$fputtintShadowsColor(PhotoFilterView r0, int r1) {
        r0.tintShadowsColor = r1;
    }

    /* renamed from: -$$Nest$fputvignetteValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11747$$Nest$fputvignetteValue(PhotoFilterView r0, float r1) {
        r0.vignetteValue = r1;
    }

    /* renamed from: -$$Nest$fputwarmthValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11748$$Nest$fputwarmthValue(PhotoFilterView r0, float r1) {
        r0.warmthValue = r1;
    }

    /* renamed from: -$$Nest$mupdateFiltersEmpty, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11749$$Nest$mupdateFiltersEmpty(PhotoFilterView r0) {
        r0.updateFiltersEmpty();
    }

    public PhotoFilterView(Context r14, VideoEditTextureView r15, Bitmap r16, int r17, MediaController.SavedFilterState r18, PaintingOverlay r19, int r20, boolean r21, boolean r22, BlurringShader.BlurManager r23, Theme.ResourcesProvider r24) {
        this(r14, r15, r16, null, r17, r18, r19, r20, r21, r22, r23, r24);
    }

    public PhotoFilterView(Context r25, VideoEditTextureView r26, Bitmap r27, Bitmap r28, int r29, MediaController.SavedFilterState r30, PaintingOverlay r31, int r32, boolean r33, boolean r34, BlurringShader.BlurManager r35, Theme.ResourcesProvider r36) {
        super(r25);
        this.curveRadioButton = new RadioButton[4];
        this.maskRect = new Rect();
        this.maskMatrix = new Matrix();
        this.maskPaint = new Paint(2);
        this.ownLayout = r34;
        this.resourcesProvider = r36;
        this.inBubbleMode = r25 instanceof BubbleActivity;
        this.paintingOverlay = r31;
        this.isMirrored = r33;
        int r7 = 0;
        this.rowsCount = 0;
        if (r32 != 1) goto L5;
        this.rowsCount = 1;
        this.softenSkinTool = 0;
    L7:
        int r11 = this.rowsCount;
        this.enhanceTool = r11;
        this.exposureTool = r11 + 1;
        this.contrastTool = r11 + 2;
        this.saturationTool = r11 + 3;
        this.warmthTool = r11 + 4;
        this.fadeTool = r11 + 5;
        this.highlightsTool = r11 + 6;
        this.shadowsTool = r11 + 7;
        int r12 = r11 + 9;
        this.rowsCount = r12;
        this.vignetteTool = r11 + 8;
        if (r32 != 2) goto L10;
        this.rowsCount = r11 + 10;
        this.softenSkinTool = r12;
    L10:
        if (r26 != null) goto L12;
        int r4 = this.rowsCount;
        this.rowsCount = r4 + 1;
        this.grainTool = r4;
    L13:
        int r42 = this.rowsCount;
        this.sharpenTool = r42;
        this.tintShadowsTool = r42 + 1;
        this.rowsCount = r42 + 3;
        this.tintHighlightsTool = r42 + 2;
        if (r30 == null) goto L17;
        this.enhanceValue = r30.enhanceValue;
        this.softenSkinValue = r30.softenSkinValue;
        this.exposureValue = r30.exposureValue;
        this.contrastValue = r30.contrastValue;
        this.warmthValue = r30.warmthValue;
        this.saturationValue = r30.saturationValue;
        this.fadeValue = r30.fadeValue;
        this.tintShadowsColor = r30.tintShadowsColor;
        this.tintHighlightsColor = r30.tintHighlightsColor;
        this.highlightsValue = r30.highlightsValue;
        this.shadowsValue = r30.shadowsValue;
        this.vignetteValue = r30.vignetteValue;
        this.grainValue = r30.grainValue;
        this.blurType = r30.blurType;
        this.sharpenValue = r30.sharpenValue;
        this.curvesToolValue = r30.curvesToolValue;
        this.blurExcludeSize = r30.blurExcludeSize;
        this.blurExcludePoint = r30.blurExcludePoint;
        this.blurExcludeBlurSize = r30.blurExcludeBlurSize;
        this.filtersEmpty = r30.isEmpty();
        this.blurAngle = r30.blurAngle;
        this.lastState = r30;
    L18:
        this.bitmapToEdit = r27;
        this.bitmapMask = r28;
        this.orientation = r29;
        if (r26 == null) goto L21;
        this.textureView = r26;
        r26.setDelegate(new PhotoFilterView$$ExternalSyntheticLambda0(this));
    L25:
        PhotoFilterBlurControl r43 = new PhotoFilterBlurControl(r25);
        this.blurControl = r43;
        r43.setVisibility(4);
        if (r34 == false) goto L28;
        addView(this.blurControl, LayoutHelper.createFrame(-1, -1, 51));
    L28:
        this.blurControl.setDelegate(new PhotoFilterView$$ExternalSyntheticLambda1(this));
        PhotoFilterCurvesControl r44 = new PhotoFilterCurvesControl(r25, this.curvesToolValue);
        this.curvesControl = r44;
        r44.setDelegate(new PhotoFilterView$$ExternalSyntheticLambda2(this));
        this.curvesControl.setVisibility(4);
        if (r34 == false) goto L31;
        addView(this.curvesControl, LayoutHelper.createFrame(-1, -1, 51));
    L31:
        FrameLayout r45 = new FrameLayout(r25);
        this.toolsView = r45;
        if (r34 == true) goto L34;
        int r13 = 40;
    L35:
        addView(r45, LayoutHelper.createFrame(-1, r13 + 186, 83));
        FrameLayout r46 = new FrameLayout(r25);
        r46.setBackgroundColor(-16777216);
        this.toolsView.addView(r46, LayoutHelper.createFrame(-1, 48, 83));
        TextView r132 = new TextView(r25);
        this.cancelTextView = r132;
        r132.setTextSize(1, 14.0f);
        this.cancelTextView.setTextColor(-1);
        this.cancelTextView.setGravity(17);
        this.cancelTextView.setBackgroundDrawable(Theme.createSelectorDrawable(-12763843, 0));
        this.cancelTextView.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.cancelTextView.setText(LocaleController.getString(R.string.Cancel).toUpperCase());
        this.cancelTextView.setTypeface(AndroidUtilities.bold());
        r46.addView(this.cancelTextView, LayoutHelper.createFrame(-2, -1, 51));
        TextView r6 = new TextView(r25);
        this.doneTextView = r6;
        r6.setTextSize(1, 14.0f);
        TextView r62 = this.doneTextView;
        int r133 = Theme.key_chat_editMediaButton;
        r62.setTextColor(getThemedColor(r133));
        this.doneTextView.setGravity(17);
        this.doneTextView.setBackgroundDrawable(Theme.createSelectorDrawable(-12763843, 0));
        this.doneTextView.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.doneTextView.setText(LocaleController.getString(R.string.Done).toUpperCase());
        this.doneTextView.setTypeface(AndroidUtilities.bold());
        r46.addView(this.doneTextView, LayoutHelper.createFrame(-2, -1, 53));
        LinearLayout r63 = new LinearLayout(r25);
        r46.addView(r63, LayoutHelper.createFrame(-2, -1, 1));
        ImageView r47 = new ImageView(r25);
        this.tuneItem = r47;
        ImageView.ScaleType r112 = ImageView.ScaleType.CENTER;
        r47.setScaleType(r112);
        this.tuneItem.setImageResource(R.drawable.msg_photo_settings);
        this.tuneItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(r133), PorterDuff.Mode.MULTIPLY));
        this.tuneItem.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        r63.addView(this.tuneItem, LayoutHelper.createLinear(56, 48));
        this.tuneItem.setOnClickListener(new PhotoFilterView$$ExternalSyntheticLambda3(this));
        ImageView r48 = new ImageView(r25);
        this.blurItem = r48;
        r48.setScaleType(r112);
        this.blurItem.setImageResource(R.drawable.msg_photo_blur);
        this.blurItem.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        r63.addView(this.blurItem, LayoutHelper.createLinear(56, 48));
        this.blurItem.setOnClickListener(new PhotoFilterView$$ExternalSyntheticLambda4(this));
        if (r26 == null) goto L38;
        this.blurItem.setVisibility(8);
    L38:
        ImageView r2 = new ImageView(r25);
        this.curveItem = r2;
        r2.setScaleType(r112);
        this.curveItem.setImageResource(R.drawable.msg_photo_curve);
        this.curveItem.setBackgroundDrawable(Theme.createSelectorDrawable(1090519039));
        r63.addView(this.curveItem, LayoutHelper.createLinear(56, 48));
        this.curveItem.setOnClickListener(new PhotoFilterView$$ExternalSyntheticLambda5(this));
        this.recyclerListView = new RecyclerListViewWithShadows(r25);
        LinearLayoutManager r22 = new LinearLayoutManager(r25);
        r22.setOrientation(1);
        this.recyclerListView.setLayoutManager(r22);
        this.recyclerListView.setClipToPadding(false);
        this.recyclerListView.setOverScrollMode(2);
        this.recyclerListView.setAdapter(new ToolsAdapter(this, r25));
        FrameLayout r23 = this.toolsView;
        RecyclerListView r49 = this.recyclerListView;
        if (r34 == true) goto L41;
        int r64 = 60;
    L42:
        r23.addView(r49, LayoutHelper.createFrame(-1, r64 + 120, 51));
        FrameLayout r24 = new FrameLayout(r25);
        this.curveLayout = r24;
        r24.setVisibility(4);
        FrameLayout r210 = this.toolsView;
        FrameLayout r3 = this.curveLayout;
        if (r34 == true) goto L45;
        int r410 = 40;
    L46:
        r210.addView(r3, LayoutHelper.createFrame(-1, 78.0f, 1, 0.0f, r410 + 40, 0.0f, 0.0f));
        LinearLayout r211 = new LinearLayout(r25);
        r211.setOrientation(0);
        this.curveLayout.addView(r211, LayoutHelper.createFrame(-2, -2, 1));
        int r37 = 0;
    L48:
        if (r37 >= 4) goto L68;
        FrameLayout r411 = new FrameLayout(r25);
        r411.setTag(Integer.valueOf(r37));
        this.curveRadioButton[r37] = new RadioButton(r25);
        this.curveRadioButton[r37].setSize(AndroidUtilities.dp(20.0f));
        r411.addView(this.curveRadioButton[r37], LayoutHelper.createFrame(30, 30, 49));
        TextView r65 = new TextView(r25);
        r65.setTextSize(1, 12.0f);
        r65.setGravity(16);
        if (r37 != 0) goto L54;
        String r8 = LocaleController.getString(R.string.CurvesAll);
        r65.setText(r8.substring(0, 1).toUpperCase() + r8.substring(1).toLowerCase());
        r65.setTextColor(-1);
        this.curveRadioButton[r37].setColor(-1, -1);
    L62:
        r411.addView(r65, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 38.0f, 0.0f, 0.0f));
        if (r37 != 0) goto L66;
        float r66 = 0.0f;
    L67:
        r211.addView(r411, LayoutHelper.createLinear(-2, -2, r66, 0.0f, 0.0f, 0.0f));
        r411.setOnClickListener(new PhotoFilterView$$ExternalSyntheticLambda6(this));
        r37 = r37 + 1;
        goto L48
    L66:
        r66 = 30.0f;
        goto L67
    L54:
        if (r37 != 1) goto L57;
        String r82 = LocaleController.getString(R.string.CurvesRed);
        r65.setText(r82.substring(0, 1).toUpperCase() + r82.substring(1).toLowerCase());
        r65.setTextColor(-1684147);
        this.curveRadioButton[r37].setColor(-1684147, -1684147);
        goto L62
    L57:
        if (r37 != 2) goto L60;
        String r113 = LocaleController.getString(R.string.CurvesGreen);
        r65.setText(r113.substring(0, 1).toUpperCase() + r113.substring(1).toLowerCase());
        r65.setTextColor(-10831009);
        this.curveRadioButton[r37].setColor(-10831009, -10831009);
        goto L62
    L60:
        if (r37 != 3) goto L62;
        String r122 = LocaleController.getString(R.string.CurvesBlue);
        r65.setText(r122.substring(0, 1).toUpperCase() + r122.substring(1).toLowerCase());
        r65.setTextColor(-12734994);
        this.curveRadioButton[r37].setColor(-12734994, -12734994);
        goto L62
    L68:
        FrameLayout r212 = new FrameLayout(r25);
        this.blurLayout = r212;
        r212.setVisibility(4);
        FrameLayout r213 = this.toolsView;
        FrameLayout r38 = this.blurLayout;
        if (r34 == true) goto L71;
        r7 = 40;
    L71:
        r213.addView(r38, LayoutHelper.createFrame(280, 60.0f, 1, 0.0f, r7 + 40, 0.0f, 0.0f));
        TextView r214 = new TextView(r25);
        this.blurOffButton = r214;
        r214.setCompoundDrawablePadding(AndroidUtilities.dp(2.0f));
        this.blurOffButton.setTextSize(1, 13.0f);
        this.blurOffButton.setGravity(1);
        this.blurOffButton.setText(LocaleController.getString(R.string.BlurOff));
        this.blurLayout.addView(this.blurOffButton, LayoutHelper.createFrame(80, 60.0f));
        this.blurOffButton.setOnClickListener(new PhotoFilterView$$ExternalSyntheticLambda7(this));
        TextView r215 = new TextView(r25);
        this.blurRadialButton = r215;
        r215.setCompoundDrawablePadding(AndroidUtilities.dp(2.0f));
        this.blurRadialButton.setTextSize(1, 13.0f);
        this.blurRadialButton.setGravity(1);
        this.blurRadialButton.setText(LocaleController.getString(R.string.BlurRadial));
        this.blurLayout.addView(this.blurRadialButton, LayoutHelper.createFrame(80, 80.0f, 51, 100.0f, 0.0f, 0.0f, 0.0f));
        this.blurRadialButton.setOnClickListener(new PhotoFilterView$$ExternalSyntheticLambda8(this));
        TextView r216 = new TextView(r25);
        this.blurLinearButton = r216;
        r216.setCompoundDrawablePadding(AndroidUtilities.dp(2.0f));
        this.blurLinearButton.setTextSize(1, 13.0f);
        this.blurLinearButton.setGravity(1);
        this.blurLinearButton.setText(LocaleController.getString(R.string.BlurLinear));
        this.blurLayout.addView(this.blurLinearButton, LayoutHelper.createFrame(80, 80.0f, 51, 200.0f, 0.0f, 0.0f, 0.0f));
        this.blurLinearButton.setOnClickListener(new PhotoFilterView$$ExternalSyntheticLambda9(this));
        updateSelectedBlurType();
        if (this.inBubbleMode == true) goto L80;
        if (r34 == true) goto L75;
        return;
    L75:
        if (this.ownsTextureView == false) goto L77;
        ((FrameLayout.LayoutParams) this.textureView.getLayoutParams()).topMargin = AndroidUtilities.statusBarHeight;
    L77:
        ((FrameLayout.LayoutParams) this.curvesControl.getLayoutParams()).topMargin = AndroidUtilities.statusBarHeight;
        return;
    L80:
        return;
    L45:
        r410 = 0;
        goto L46
    L41:
        r64 = 0;
        goto L42
    L34:
        r13 = 0;
        goto L35
    L21:
        this.ownsTextureView = true;
        AnonymousClass1 r412 = new AnonymousClass1(this, r25);
        this.textureView = r412;
        if (r34 == false) goto L24;
        addView(r412, LayoutHelper.createFrame(-1, -1, 51));
    L24:
        this.textureView.setVisibility(4);
        this.textureView.setSurfaceTextureListener(new AnonymousClass2(this, r34, r35));
        goto L25
    L17:
        this.curvesToolValue = new CurvesToolValue();
        this.blurExcludeSize = 0.35f;
        this.blurExcludePoint = new PointF(0.5f, 0.5f);
        this.blurExcludeBlurSize = 0.15f;
        this.blurAngle = 1.5707964f;
        this.filtersEmpty = true;
        goto L18
    L12:
        this.grainTool = -1;
        goto L13
    L5:
        if (r32 != 0) goto L7;
        this.softenSkinTool = -1;
        goto L7
    }

    private void fixLayout(int r11, int r12) {
        if (this.ownLayout == false) goto L44;
        int r112 = r11 - AndroidUtilities.dp(28.0f);
        int r1 = AndroidUtilities.dp(214.0f);
        if (this.inBubbleMode == true) goto L8;
        int r2 = AndroidUtilities.statusBarHeight;
    L9:
        int r122 = r12 - (r1 + r2);
        Bitmap r13 = this.bitmapToEdit;
        if (r13 == null) goto L19;
        int r22 = this.orientation;
        if ((r22 % 360) != 90) goto L14;
    L18:
        float r14 = r13.getHeight();
        int r23 = this.bitmapToEdit.getWidth();
    L20:
        float r4 = r112;
        float r6 = r122;
        if ((r4 / r14) <= (r6 / r23)) goto L23;
        float r15 = (int) Math.ceil(r14 * r7);
        float r24 = r6;
    L24:
        int r42 = (int) Math.ceil(((r4 - r15) / 2.0f) + AndroidUtilities.dp(14.0f));
        float r62 = ((r6 - r24) / 2.0f) + AndroidUtilities.dp(14.0f);
        if (this.inBubbleMode == true) goto L27;
        int r5 = AndroidUtilities.statusBarHeight;
    L28:
        int r52 = (int) Math.ceil(r62 + r5);
        int r16 = (int) r15;
        int r25 = (int) r24;
        if (this.ownsTextureView == false) goto L31;
        FrameLayout.LayoutParams r63 = (FrameLayout.LayoutParams) this.textureView.getLayoutParams();
        r63.leftMargin = r42;
        r63.topMargin = r52;
        r63.width = r16;
        r63.height = r25;
    L31:
        PhotoFilterCurvesControl r64 = this.curvesControl;
        float r43 = r42;
        if (this.inBubbleMode == true) goto L34;
        int r7 = AndroidUtilities.statusBarHeight;
    L35:
        float r17 = r16;
        float r26 = r25;
        r64.setActualArea(r43, r52 - r7, r17, r26);
        this.blurControl.setActualAreaSize(r17, r26);
        ((FrameLayout.LayoutParams) this.blurControl.getLayoutParams()).height = AndroidUtilities.dp(38.0f) + r122;
        ((FrameLayout.LayoutParams) this.curvesControl.getLayoutParams()).height = r122 + AndroidUtilities.dp(28.0f);
        if (AndroidUtilities.isTablet() == false) goto L43;
        int r123 = AndroidUtilities.dp(86.0f) * 10;
        FrameLayout.LayoutParams r10 = (FrameLayout.LayoutParams) this.recyclerListView.getLayoutParams();
        if (r123 >= r112) goto L41;
        r10.width = r123;
        r10.leftMargin = (r112 - r123) / 2;
        return;
    L41:
        r10.width = -1;
        r10.leftMargin = 0;
        return;
    L43:
        return;
    L34:
        r7 = 0;
        goto L35
    L27:
        r5 = 0;
        goto L28
    L23:
        r24 = (int) Math.ceil(r2 * r5);
        r15 = r4;
        goto L24
    L14:
        if ((r22 % 360) == 270) goto L18;
        r14 = r13.getWidth();
        r23 = this.bitmapToEdit.getHeight();
        goto L20
    L19:
        r14 = this.textureView.getWidth();
        r23 = this.textureView.getHeight();
        goto L20
    L8:
        r2 = 0;
        goto L9
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$0(FilterGLThread r1) {
        this.eglThread = r1;
        r1.setFilterGLThreadDelegate(this);
    }

    private /* synthetic */ void lambda$new$1(PointF r1, float r2, float r3, float r4) {
        this.blurExcludeSize = r3;
        this.blurExcludePoint = r1;
        this.blurExcludeBlurSize = r2;
        this.blurAngle = r4;
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L6;
        r0.requestRender(false);
        return;
    }

    private /* synthetic */ void lambda$new$2() {
        updateFiltersEmpty();
        FilterGLThread r1 = this.eglThread;
        if (r1 == null) goto L6;
        r1.requestRender(false);
        return;
    }

    private /* synthetic */ void lambda$new$3(View r4) {
        this.selectedTool = 0;
        this.tuneItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY));
        this.blurItem.setColorFilter(null);
        this.curveItem.setColorFilter(null);
        switchMode();
    }

    private /* synthetic */ void lambda$new$4(View r5) {
        this.selectedTool = 1;
        this.tuneItem.setColorFilter(null);
        this.blurItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY));
        this.curveItem.setColorFilter(null);
        switchMode();
    }

    private /* synthetic */ void lambda$new$5(View r4) {
        this.selectedTool = 2;
        this.tuneItem.setColorFilter(null);
        this.blurItem.setColorFilter(null);
        this.curveItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY));
        switchMode();
    }

    private /* synthetic */ void lambda$new$6(View r6) {
        int r62 = ((Integer) r6.getTag()).intValue();
        this.curvesToolValue.activeType = r62;
        int r1 = 0;
    L4:
        if (r1 >= 4) goto L10;
        RadioButton r2 = this.curveRadioButton[r1];
        if (r1 != r62) goto L8;
        boolean r4 = true;
    L9:
        r2.setChecked(r4, true);
        r1 = r1 + 1;
        goto L4
    L8:
        r4 = false;
        goto L9
    L10:
        this.curvesControl.invalidate();
    }

    private /* synthetic */ void lambda$new$7(View r3) {
        this.blurType = 0;
        updateSelectedBlurType();
        this.blurControl.setVisibility(4);
        FilterGLThread r2 = this.eglThread;
        if (r2 == null) goto L6;
        r2.requestRender(false);
        return;
    }

    private /* synthetic */ void lambda$new$8(View r3) {
        this.blurType = 1;
        updateSelectedBlurType();
        this.blurControl.setVisibility(0);
        this.blurControl.setType(1);
        FilterGLThread r2 = this.eglThread;
        if (r2 == null) goto L6;
        r2.requestRender(false);
        return;
    }

    private /* synthetic */ void lambda$new$9(View r2) {
        this.blurType = 2;
        updateSelectedBlurType();
        this.blurControl.setVisibility(0);
        this.blurControl.setType(0);
        FilterGLThread r1 = this.eglThread;
        if (r1 == null) goto L6;
        r1.requestRender(false);
        return;
    }

    private void setShowOriginal(boolean r2) {
        if (this.showOriginal == r2) goto L10;
        this.showOriginal = r2;
        FilterGLThread r1 = this.eglThread;
        if (r1 == null) goto L9;
        r1.requestRender(false);
        return;
    L9:
        return;
    }

    private void updateFiltersEmpty() {
        if (Math.abs(this.enhanceValue) < 0.1f) goto L5;
    L35:
        boolean r0 = false;
    L36:
        this.filtersEmpty = r0;
        return;
    L5:
        if (Math.abs(this.softenSkinValue) >= 0.1f) goto L35;
        if (Math.abs(this.exposureValue) >= 0.1f) goto L35;
        if (Math.abs(this.contrastValue) >= 0.1f) goto L35;
        if (Math.abs(this.warmthValue) >= 0.1f) goto L35;
        if (Math.abs(this.saturationValue) >= 0.1f) goto L35;
        if (Math.abs(this.fadeValue) >= 0.1f) goto L35;
        if (this.tintShadowsColor != 0) goto L35;
        if (this.tintHighlightsColor != 0) goto L35;
        if (Math.abs(this.highlightsValue) >= 0.1f) goto L35;
        if (Math.abs(this.shadowsValue) >= 0.1f) goto L35;
        if (Math.abs(this.vignetteValue) >= 0.1f) goto L35;
        if (Math.abs(this.grainValue) >= 0.1f) goto L35;
        if (this.blurType != 0) goto L35;
        if (Math.abs(this.sharpenValue) >= 0.1f) goto L35;
        if (this.curvesToolValue.shouldBeSkipped() == false) goto L35;
        r0 = true;
        goto L36
    }

    private void updateSelectedBlurType() {
        int r0 = this.blurType;
        if (r0 != 0) goto L6;
        Drawable r02 = this.blurOffButton.getContext().getResources().getDrawable(R.drawable.msg_blur_off).mutate();
        int r5 = Theme.key_chat_editMediaButton;
        r02.setColorFilter(new PorterDuffColorFilter(getThemedColor(r5), PorterDuff.Mode.MULTIPLY));
        this.blurOffButton.setCompoundDrawablesWithIntrinsicBounds(null, r02, null, null);
        this.blurOffButton.setTextColor(getThemedColor(r5));
        this.blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.msg_blur_radial, 0, 0);
        this.blurRadialButton.setTextColor(-1);
        this.blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.msg_blur_linear, 0, 0);
        this.blurLinearButton.setTextColor(-1);
    L11:
        updateFiltersEmpty();
        return;
    L6:
        if (r0 != 1) goto L9;
        this.blurOffButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.msg_blur_off, 0, 0);
        this.blurOffButton.setTextColor(-1);
        Drawable r03 = this.blurOffButton.getContext().getResources().getDrawable(R.drawable.msg_blur_radial).mutate();
        int r52 = Theme.key_chat_editMediaButton;
        r03.setColorFilter(new PorterDuffColorFilter(getThemedColor(r52), PorterDuff.Mode.MULTIPLY));
        this.blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(null, r03, null, null);
        this.blurRadialButton.setTextColor(getThemedColor(r52));
        this.blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.msg_blur_linear, 0, 0);
        this.blurLinearButton.setTextColor(-1);
        goto L11
    L9:
        if (r0 != 2) goto L11;
        this.blurOffButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.msg_blur_off, 0, 0);
        this.blurOffButton.setTextColor(-1);
        this.blurRadialButton.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.msg_blur_radial, 0, 0);
        this.blurRadialButton.setTextColor(-1);
        Drawable r04 = this.blurOffButton.getContext().getResources().getDrawable(R.drawable.msg_blur_linear).mutate();
        int r3 = Theme.key_chat_editMediaButton;
        r04.setColorFilter(new PorterDuffColorFilter(getThemedColor(r3), PorterDuff.Mode.MULTIPLY));
        this.blurLinearButton.setCompoundDrawablesWithIntrinsicBounds(null, r04, null, null);
        this.blurLinearButton.setTextColor(getThemedColor(r3));
        goto L11
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r4, View r5, long r6) {
        boolean r62 = super.drawChild(r4, r5, r6);
        if (this.paintingOverlay != null) goto L5;
    L15:
        return r62;
    L5:
        if (r5 != this.textureView) goto L15;
        r4.save();
        r4.translate(this.textureView.getLeft(), this.textureView.getTop());
        if (this.bitmapMask != null) goto L9;
    L14:
        float r52 = this.textureView.getMeasuredWidth() / this.paintingOverlay.getMeasuredWidth();
        r4.scale(r52, r52);
        this.paintingOverlay.draw(r4);
        r4.restore();
        goto L15
    L9:
        if (this.textureView.getVisibility() != 0) goto L14;
        this.maskRect.set(0, 0, this.textureView.getMeasuredWidth(), this.textureView.getMeasuredHeight());
        if (this.orientation == 0) goto L13;
        this.maskMatrix.reset();
        this.maskMatrix.postRotate(this.orientation, this.bitmapMask.getWidth() / 2.0f, this.bitmapMask.getHeight() / 2.0f);
        float r53 = (this.bitmapMask.getHeight() - this.bitmapMask.getWidth()) / 2.0f;
        this.maskMatrix.postTranslate(r53, -r53);
        this.maskMatrix.postScale(this.maskRect.width() / this.bitmapMask.getHeight(), this.maskRect.height() / this.bitmapMask.getWidth());
        r4.drawBitmap(this.bitmapMask, this.maskMatrix, this.maskPaint);
        goto L14
    L13:
        r4.drawBitmap(this.bitmapMask, null, this.maskRect, this.maskPaint);
        goto L14
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public ByteBuffer fillAndGetCurveBuffer() {
        this.curvesToolValue.fillBuffer();
        return this.curvesToolValue.curveBuffer;
    }

    public Bitmap getBitmap() {
        FilterGLThread r0 = this.eglThread;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getTexture();
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getBlurAngle() {
        return this.blurAngle;
    }

    public PhotoFilterBlurControl getBlurControl() {
        return this.blurControl;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getBlurExcludeBlurSize() {
        return this.blurExcludeBlurSize;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public PointF getBlurExcludePoint() {
        return this.blurExcludePoint;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getBlurExcludeSize() {
        return this.blurExcludeSize;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public int getBlurType() {
        return this.blurType;
    }

    public TextView getCancelTextView() {
        return this.cancelTextView;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getContrastValue() {
        return ((this.contrastValue / 100.0f) * 0.3f) + 1.0f;
    }

    public PhotoFilterCurvesControl getCurveControl() {
        return this.curvesControl;
    }

    public TextView getDoneTextView() {
        return this.doneTextView;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getEnhanceValue() {
        return this.enhanceValue / 100.0f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getExposureValue() {
        return this.exposureValue / 100.0f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getFadeValue() {
        return this.fadeValue / 100.0f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getGrainValue() {
        return (this.grainValue / 100.0f) * 0.04f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getHighlightsValue() {
        return ((this.highlightsValue * 0.75f) + 100.0f) / 100.0f;
    }

    public TextureView getMyTextureView() {
        if (this.ownsTextureView == true) goto L5;
        return null;
    L5:
        if (this.ownLayout == false) goto L7;
        return null;
    L7:
        return this.textureView;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getSaturationValue() {
        float r1 = this.saturationValue / 100.0f;
        if (r1 <= 0.0f) goto L6;
        r1 = r1 * 1.05f;
    L6:
        return r1 + 1.0f;
    }

    public MediaController.SavedFilterState getSavedFilterState() {
        MediaController.SavedFilterState r0 = new MediaController.SavedFilterState();
        r0.enhanceValue = this.enhanceValue;
        r0.exposureValue = this.exposureValue;
        r0.contrastValue = this.contrastValue;
        r0.warmthValue = this.warmthValue;
        r0.saturationValue = this.saturationValue;
        r0.fadeValue = this.fadeValue;
        r0.softenSkinValue = this.softenSkinValue;
        r0.tintShadowsColor = this.tintShadowsColor;
        r0.tintHighlightsColor = this.tintHighlightsColor;
        r0.highlightsValue = this.highlightsValue;
        r0.shadowsValue = this.shadowsValue;
        r0.vignetteValue = this.vignetteValue;
        r0.grainValue = this.grainValue;
        r0.blurType = this.blurType;
        r0.sharpenValue = this.sharpenValue;
        r0.curvesToolValue = this.curvesToolValue;
        r0.blurExcludeSize = this.blurExcludeSize;
        r0.blurExcludePoint = this.blurExcludePoint;
        r0.blurExcludeBlurSize = this.blurExcludeBlurSize;
        r0.blurAngle = this.blurAngle;
        this.lastState = r0;
        return r0;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getShadowsValue() {
        return ((this.shadowsValue * 0.55f) + 100.0f) / 100.0f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getSharpenValue() {
        return ((this.sharpenValue / 100.0f) * 0.6f) + 0.11f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getSoftenSkinValue() {
        return this.softenSkinValue / 100.0f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public int getTintHighlightsColor() {
        return this.tintHighlightsColor;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getTintHighlightsIntensityValue() {
        if (this.tintHighlightsColor != 0) goto L6;
        return 0.0f;
    L6:
        return 0.5f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public int getTintShadowsColor() {
        return this.tintShadowsColor;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getTintShadowsIntensityValue() {
        if (this.tintShadowsColor != 0) goto L6;
        return 0.0f;
    L6:
        return 0.5f;
    }

    public FrameLayout getToolsView() {
        return this.toolsView;
    }

    public Bitmap getUiBlurBitmap() {
        FilterGLThread r0 = this.eglThread;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getUiBlurBitmap();
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getVignetteValue() {
        return this.vignetteValue / 100.0f;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public float getWarmthValue() {
        return this.warmthValue / 100.0f;
    }

    public boolean hasChanges() {
        MediaController.SavedFilterState r0 = this.lastState;
        float r1 = this.enhanceValue;
        if (r0 == null) goto L38;
        if (r1 == r0.enhanceValue) goto L7;
    L36:
        return true;
    L7:
        if (this.contrastValue != r0.contrastValue) goto L36;
        if (this.highlightsValue != r0.highlightsValue) goto L36;
        if (this.exposureValue != r0.exposureValue) goto L36;
        if (this.warmthValue != r0.warmthValue) goto L36;
        if (this.saturationValue != r0.saturationValue) goto L36;
        if (this.vignetteValue != r0.vignetteValue) goto L36;
        if (this.shadowsValue != r0.shadowsValue) goto L36;
        if (this.grainValue != r0.grainValue) goto L36;
        if (this.sharpenValue != r0.sharpenValue) goto L36;
        if (this.fadeValue != r0.fadeValue) goto L36;
        if (this.softenSkinValue != r0.softenSkinValue) goto L36;
        if (this.tintHighlightsColor != r0.tintHighlightsColor) goto L36;
        if (this.tintShadowsColor != r0.tintShadowsColor) goto L36;
        if (this.curvesToolValue.shouldBeSkipped() == false) goto L36;
        return false;
    L38:
        if (r1 == 0.0f) goto L40;
    L69:
        return true;
    L40:
        if (this.contrastValue != 0.0f) goto L69;
        if (this.highlightsValue != 0.0f) goto L69;
        if (this.exposureValue != 0.0f) goto L69;
        if (this.warmthValue != 0.0f) goto L69;
        if (this.saturationValue != 0.0f) goto L69;
        if (this.vignetteValue != 0.0f) goto L69;
        if (this.shadowsValue != 0.0f) goto L69;
        if (this.grainValue != 0.0f) goto L69;
        if (this.sharpenValue != 0.0f) goto L69;
        if (this.fadeValue != 0.0f) goto L69;
        if (this.softenSkinValue != 0.0f) goto L69;
        if (this.tintHighlightsColor != 0) goto L69;
        if (this.tintShadowsColor != 0) goto L69;
        if (this.curvesToolValue.shouldBeSkipped() == false) goto L69;
        return false;
    }

    public void init() {
        this.textureView.setVisibility(0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        fixLayout(View.MeasureSpec.getSize(r3), View.MeasureSpec.getSize(r4));
        super.onMeasure(r3, r4);
    }

    @Override // org.telegram.ui.Stories.recorder.StoryRecorder.Touchable
    public boolean onTouch(MotionEvent r5) {
        if (r5.getActionMasked() != 0) goto L5;
    L12:
        TextureView r0 = this.textureView;
        if ((r0 instanceof VideoEditTextureView) == false) goto L18;
        if (((VideoEditTextureView) r0).containsPoint(r5.getX(), r5.getY()) == false) goto L26;
        setShowOriginal(true);
    L26:
        return true;
    L18:
        if (r5.getX() < this.textureView.getX()) goto L26;
        if (r5.getY() < this.textureView.getY()) goto L26;
        if (r5.getX() > (this.textureView.getX() + this.textureView.getWidth())) goto L26;
        if (r5.getY() > (this.textureView.getY() + this.textureView.getHeight())) goto L26;
        setShowOriginal(true);
        goto L26
    L5:
        if (r5.getActionMasked() == 5) goto L12;
        if (r5.getActionMasked() != 1) goto L10;
    L11:
        setShowOriginal(false);
        goto L26
    L10:
        if (r5.getActionMasked() != 6) goto L26;
        goto L11
    }

    public void setEnhanceValue(float r5) {
        this.enhanceValue = r5 * 100.0f;
        updateFiltersEmpty();
        int r0 = 0;
    L4:
        if (r0 >= this.recyclerListView.getChildCount()) goto L11;
        View r1 = this.recyclerListView.getChildAt(r0);
        if ((r1 instanceof PhotoEditToolCell) == false) goto L10;
        if (this.recyclerListView.getChildAdapterPosition(r1) != this.enhanceTool) goto L10;
        ((PhotoEditToolCell) r1).setIconAndTextAndValue(LocaleController.getString(R.string.Enhance), this.enhanceValue, 0, 100);
    L10:
        r0 = r0 + 1;
    L11:
        FilterGLThread r4 = this.eglThread;
        if (r4 == null) goto L19;
        r4.requestRender(true);
        return;
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public boolean shouldDrawCurvesPass() {
        return !this.curvesToolValue.shouldBeSkipped();
    }

    @Override // org.telegram.ui.Components.FilterShaders.FilterShadersDelegate
    public boolean shouldShowOriginal() {
        if (this.showOriginal == false) goto L5;
        return true;
    L5:
        if (this.filtersEmpty == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void shutdown() {
        if (this.ownsTextureView == false) goto L9;
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L7;
        r0.shutdown();
        this.eglThread = null;
    L7:
        this.textureView.setVisibility(8);
        return;
    L9:
        TextureView r02 = this.textureView;
        if ((r02 instanceof VideoEditTextureView) == false) goto L19;
        VideoEditTextureView r03 = (VideoEditTextureView) r02;
        MediaController.SavedFilterState r2 = this.lastState;
        if (r2 != null) goto L15;
        r03.setDelegate(null);
        return;
    L15:
        FilterGLThread r3 = this.eglThread;
        if (r3 == null) goto L20;
        r3.setFilterGLThreadDelegate(FilterShaders.getFilterShadersDelegate(r2));
        return;
    L20:
        return;
    }

    public void switchMode() {
        int r0 = this.selectedTool;
        if (r0 != 0) goto L7;
        this.blurControl.setVisibility(4);
        this.blurLayout.setVisibility(4);
        this.curveLayout.setVisibility(4);
        this.curvesControl.setVisibility(4);
        this.recyclerListView.setVisibility(0);
        return;
    L7:
        if (r0 != 1) goto L14;
        this.recyclerListView.setVisibility(4);
        this.curveLayout.setVisibility(4);
        this.curvesControl.setVisibility(4);
        this.blurLayout.setVisibility(0);
        if (this.blurType == 0) goto L11;
        this.blurControl.setVisibility(0);
    L11:
        updateSelectedBlurType();
        return;
    L14:
        if (r0 != 2) goto L22;
        this.recyclerListView.setVisibility(4);
        this.blurLayout.setVisibility(4);
        this.blurControl.setVisibility(4);
        this.curveLayout.setVisibility(0);
        this.curvesControl.setVisibility(0);
        this.curvesToolValue.activeType = 0;
        int r02 = 0;
    L16:
        if (r02 >= 4) goto L26;
        RadioButton r4 = this.curveRadioButton[r02];
        if (r02 != 0) goto L20;
        boolean r5 = true;
    L21:
        r4.setChecked(r5, false);
        r02 = r02 + 1;
        goto L16
    L20:
        r5 = false;
        goto L21
    L26:
        return;
    }

    public void updateColors() {
        TextView r0 = this.doneTextView;
        if (r0 == null) goto L5;
        r0.setTextColor(getThemedColor(Theme.key_chat_editMediaButton));
    L5:
        ImageView r02 = this.tuneItem;
        if (r02 != null) goto L8;
    L10:
        ImageView r03 = this.blurItem;
        if (r03 != null) goto L13;
    L15:
        ImageView r04 = this.curveItem;
        if (r04 != null) goto L18;
    L20:
        updateSelectedBlurType();
        return;
    L18:
        if (r04.getColorFilter() == null) goto L20;
        this.curveItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY));
        goto L20
    L13:
        if (r03.getColorFilter() == null) goto L15;
        this.blurItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY));
        goto L15
    L8:
        if (r02.getColorFilter() == null) goto L10;
        this.tuneItem.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_editMediaButton), PorterDuff.Mode.MULTIPLY));
        goto L10
    }

    public void updateUiBlurGradient(int r2, int r3) {
        FilterGLThread r0 = this.eglThread;
        if (r0 == null) goto L6;
        r0.updateUiBlurGradient(r2, r3);
        return;
    L6:
        this.gradientTop = r2;
        this.gradientBottom = r3;
    }
}
