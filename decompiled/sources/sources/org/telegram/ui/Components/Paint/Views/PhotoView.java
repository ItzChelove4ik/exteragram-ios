package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentation;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentationResult;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenter;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MediaController;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.RectOld;
import org.telegram.ui.Components.Size;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public class PhotoView extends EntityView {
    private int anchor;
    public Size baseSize;
    public Bitmap bitmap;
    private final Rect bitmapDst;
    private final Paint bitmapPaint;
    private final Rect bitmapSrc;
    public final FrameLayoutDrawer containerView;
    public MediaController.CropState crop;
    private final RectF dest;
    private LinearGradient highlightGradient;
    private Matrix highlightGradientMatrix;
    private Paint highlightPaint;
    private long highlightStart;
    private int invert;
    private final AnimatedFloat mirrorT;
    private boolean mirrored;
    private boolean needHighlight;
    private TLObject object;
    private int orientation;
    private boolean overridenSegmented;
    private String path;
    private Path roundRectPath;
    private final Paint segmentPaint;
    private boolean segmented;
    private File segmentedFile;
    public Bitmap segmentedImage;
    private AnimatedFloat segmentedT;
    private boolean segmentingLoaded;
    private boolean segmentingLoading;
    private final Rect src;

    public class FrameLayoutDrawer extends FrameLayout {
        final /* synthetic */ PhotoView this$0;

        public FrameLayoutDrawer(PhotoView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r1) {
            this.this$0.stickerDraw(r1);
        }
    }

    public class PhotoViewSelectionView extends EntityView.SelectionView {
        private final Paint clearPaint;
        private Path path;
        final /* synthetic */ PhotoView this$0;

        public PhotoViewSelectionView(PhotoView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            Paint r12 = new Paint(1);
            this.clearPaint = r12;
            this.path = new Path();
            r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        @Override // android.view.View
        public void onDraw(Canvas r19) {
            super.onDraw(r19);
            int r8 = r19.getSaveCount();
            float r1 = getShowAlpha();
            if (r1 > 0.0f) goto L6;
            return;
        L6:
            if (r1 >= 1.0f) goto L8;
            int r6 = (int) (r1 * 255.0f);
            Canvas r12 = r19;
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), r6, 31);
        L9:
            float r3 = AndroidUtilities.dp(2.0f);
            float r10 = AndroidUtilities.dpf2(5.66f);
            float r11 = (r3 + r10) + AndroidUtilities.dp(15.0f);
            float r4 = r11 * 2.0f;
            float r32 = getMeasuredWidth() - r4;
            float r5 = getMeasuredHeight() - r4;
            RectF r42 = AndroidUtilities.rectTmp;
            float r122 = r11 + r32;
            float r13 = r11 + r5;
            r42.set(r11, r11, r122, r13);
            float r62 = AndroidUtilities.dp(12.0f);
            float r33 = Math.min(r62, r32 / 2.0f);
            float r52 = r5 / 2.0f;
            float r14 = Math.min(r62, r52);
            this.path.rewind();
            float r34 = r33 * 2.0f;
            float r63 = r11 + r34;
            float r2 = 2.0f * r14;
            float r7 = r11 + r2;
            r42.set(r11, r11, r63, r7);
            this.path.arcTo(r42, 180.0f, 90.0f);
            float r35 = r122 - r34;
            r42.set(r35, r11, r122, r7);
            this.path.arcTo(r42, 270.0f, 90.0f);
            r12.drawPath(this.path, this.paint);
            this.path.rewind();
            float r72 = r13 - r2;
            r42.set(r11, r72, r63, r13);
            this.path.arcTo(r42, 180.0f, -90.0f);
            r42.set(r35, r72, r122, r13);
            this.path.arcTo(r42, 90.0f, -90.0f);
            r12.drawPath(this.path, this.paint);
            float r9 = r11 + r52;
            r12.drawCircle(r11, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r11, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.drawCircle(r122, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r122, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            float r36 = r11 + r14;
            float r53 = r13 - r14;
            r19.drawLine(r11, r36, r11, r53, this.paint);
            r19.drawLine(r122, r36, r122, r53, this.paint);
            r19.drawCircle(r122, r9, (AndroidUtilities.dp(1.0f) + r10) - 1.0f, this.clearPaint);
            r19.drawCircle(r11, r9, (r10 + AndroidUtilities.dp(1.0f)) - 1.0f, this.clearPaint);
            r19.restoreToCount(r8);
            return;
        L8:
            r12 = r19;
            goto L9
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.SelectionView
        public int pointInsideHandle(float r7, float r8) {
            float r0 = AndroidUtilities.dp(1.0f);
            float r1 = AndroidUtilities.dp(19.5f);
            float r02 = r0 + r1;
            float r4 = r02 * 2.0f;
            float r2 = getMeasuredWidth() - r4;
            float r6 = getMeasuredHeight() - r4;
            float r3 = (r6 / 2.0f) + r02;
            if (r7 > (r02 - r1)) goto L5;
        L12:
            float r42 = r02 + r2;
            if (r7 <= (r42 - r1)) goto L23;
            if (r8 <= (r3 - r1)) goto L23;
            if (r7 >= (r42 + r1)) goto L23;
            if (r8 >= (r3 + r1)) goto L23;
            return 2;
        L23:
            if (r7 > r02) goto L25;
            return 0;
        L25:
            if (r7 < r2) goto L27;
            return 0;
        L27:
            if (r8 > r02) goto L29;
            return 0;
        L29:
            if (r8 >= r6) goto L36;
            return 3;
        L36:
            return 0;
        L5:
            if (r8 <= (r3 - r1)) goto L12;
            if (r7 >= (r02 + r1)) goto L12;
            if (r8 >= (r3 + r1)) goto L12;
            return 1;
        }
    }

    /* renamed from: $r8$lambda$2__OhrsCFQ2SMrJ-bw_u0-rzA50, reason: not valid java name */
    public static /* synthetic */ void m11575$r8$lambda$2__OhrsCFQ2SMrJbw_u0rzA50(PhotoView r0, SubjectSegmentationResult r1) {
        r0.lambda$segmentImage$1(r1);
    }

    public static /* synthetic */ Bitmap $r8$lambda$6Cum3RYRack_hjjgQiXcHF4Hia0(String r0, BitmapFactory.Options r1) {
        return BitmapFactory.decodeFile(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$JXnQgb3Ak96M8WBbQ8mdaqxb9U0(PhotoView r0, Bitmap r1, Exception r2) {
        r0.lambda$segmentImage$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$s0tHel0P_CWxHHlKILAwsbrvGrI(PhotoView r0, Bitmap r1) {
        r0.lambda$segmentImage$2(r1);
    }

    public PhotoView(Context r11, PointF r12, float r13, float r14, Size r15, String r16, int r17, int r18) {
        super(r11, r12);
        this.anchor = -1;
        this.mirrored = false;
        this.overridenSegmented = false;
        this.segmented = false;
        this.src = new Rect();
        this.dest = new RectF();
        this.segmentPaint = new Paint(3);
        this.highlightStart = -1;
        this.bitmapSrc = new Rect();
        this.bitmapDst = new Rect();
        this.bitmapPaint = new Paint(3);
        setRotation(r13);
        setScale(r14);
        this.path = r16;
        this.baseSize = r15;
        FrameLayoutDrawer r4 = new FrameLayoutDrawer(this, r11);
        this.containerView = r4;
        addView(r4, LayoutHelper.createFrame(-1, -1.0f));
        CubicBezierInterpolator r9 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.mirrorT = new AnimatedFloat(r4, 0, 500, r9);
        this.segmentedT = new AnimatedFloat(r4, 0, 350, r9);
        this.orientation = r17;
        this.invert = r18;
        Bitmap r0 = StoryEntry.getScaledBitmap(new PhotoView$$ExternalSyntheticLambda0(r16), 1920, 1920, false, false);
        this.bitmap = r0;
        if (r0 == null) goto L5;
        segmentImage(r0);
    L5:
        updatePosition();
    }

    public PhotoView(Context r10, PointF r11, float r12, float r13, Size r14, TLObject r15) {
        super(r10, r11);
        this.anchor = -1;
        this.mirrored = false;
        this.overridenSegmented = false;
        this.segmented = false;
        this.src = new Rect();
        this.dest = new RectF();
        this.segmentPaint = new Paint(3);
        this.highlightStart = -1;
        this.bitmapSrc = new Rect();
        this.bitmapDst = new Rect();
        this.bitmapPaint = new Paint(3);
        setRotation(r12);
        setScale(r13);
        this.object = r15;
        this.baseSize = r14;
        FrameLayoutDrawer r3 = new FrameLayoutDrawer(this, r10);
        this.containerView = r3;
        addView(r3, LayoutHelper.createFrame(-1, -1.0f));
        CubicBezierInterpolator r8 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.mirrorT = new AnimatedFloat(r3, 0, 500, r8);
        this.segmentedT = new AnimatedFloat(r3, 0, 350, r8);
        updatePosition();
    }

    private void drawSegmented(Canvas r9) {
        Bitmap r0 = this.segmentedImage;
        if (r0 != null) goto L5;
        return;
    L5:
        this.src.set(0, 0, r0.getWidth(), this.segmentedImage.getHeight());
        int r02 = this.segmentedImage.getWidth();
        int r1 = this.segmentedImage.getHeight();
        int r2 = this.orientation;
        if (r2 != 90) goto L8;
    L13:
        r02 = this.segmentedImage.getHeight();
        r1 = this.segmentedImage.getWidth();
    L14:
        Size r22 = this.baseSize;
        float r03 = Math.max(r02 / r22.width, r1 / r22.height);
        float r12 = this.segmentedImage.getWidth() / r03;
        float r23 = this.segmentedImage.getHeight() / r03;
        RectF r04 = this.dest;
        Size r3 = this.baseSize;
        float r4 = r3.width;
        float r32 = r3.height;
        r04.set((r4 - r12) / 2.0f, (r32 - r23) / 2.0f, (r4 + r12) / 2.0f, (r32 + r23) / 2.0f);
        r9.save();
        int r05 = this.orientation;
        if (r05 == 0) goto L18;
        r9.rotate(r05, this.dest.centerX(), this.dest.centerY());
    L18:
        if (this.roundRectPath != null) goto L20;
        this.roundRectPath = new Path();
    L20:
        this.roundRectPath.rewind();
        this.roundRectPath.addRoundRect(this.dest, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
        r9.clipPath(this.roundRectPath);
        r9.drawBitmap(this.segmentedImage, this.src, this.dest, this.segmentPaint);
        r9.restore();
        return;
    L8:
        if (r2 == 270) goto L13;
        if (r2 == (-90)) goto L13;
        if (r2 != (-270)) goto L14;
        goto L13
    }

    private String getImageFilter() {
        Point r2 = AndroidUtilities.displaySize;
        int r22 = Math.round((Math.min(r2.x, r2.y) * 0.8f) / AndroidUtilities.density);
        return r22 + "_" + r22;
    }

    public static boolean isWaitingMlKitError(Exception r1) {
        if ((r1 instanceof MlKitException) == true) goto L5;
        return false;
    L5:
        if (r1.getMessage() != null) goto L7;
        return false;
    L7:
        if (r1.getMessage().contains("segmentation optional module to be downloaded") == false) goto L13;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$segmentImage$1(SubjectSegmentationResult r1) {
        this.segmentingLoaded = true;
        this.segmentingLoading = false;
    }

    private /* synthetic */ void lambda$segmentImage$2(Bitmap r1) {
        segmentImage(r1);
    }

    private /* synthetic */ void lambda$segmentImage$3(Bitmap r2, Exception r3) {
        this.segmentingLoading = false;
        FileLog.e(r3);
        if (isWaitingMlKitError(r3) == true) goto L5;
    L8:
        this.segmentingLoaded = true;
        return;
    L5:
        if (isAttachedToWindow() == false) goto L8;
        AndroidUtilities.runOnUIThread(new PhotoView$$ExternalSyntheticLambda3(this, r2), 2000);
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public EntityView.SelectionView createSelectionView() {
        return new PhotoViewSelectionView(this, getContext());
    }

    public void deleteSegmentedFile() {
        File r0 = this.segmentedFile;
        if (r0 == null) goto L10;
        r0.delete();     // Catch: Exception -> L7
    L5:
        this.segmentedFile = null;
        return;
    }

    public void drawContent(Canvas r3) {
        if (this.bitmap != null) goto L5;
        return;
    L5:
        this.bitmapPaint.setAlpha(255);
        r3.drawBitmap(this.bitmap, 0.0f, 0.0f, this.bitmapPaint);
    }

    public int getAnchor() {
        return this.anchor;
    }

    public Size getBaseSize() {
        return this.baseSize;
    }

    public int getContentHeight() {
        Bitmap r0 = this.bitmap;
        if (r0 != null) goto L7;
        return 1;
    L7:
        return r0.getHeight();
    }

    public int getContentWidth() {
        Bitmap r0 = this.bitmap;
        if (r0 != null) goto L7;
        return 1;
    L7:
        return r0.getWidth();
    }

    public int getOrientation() {
        return this.orientation;
    }

    public String getPath(int r3) {
        TLObject r0 = this.object;
        if ((r0 instanceof TLRPC.Photo) == false) goto L8;
        TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(((TLRPC.Photo) r0).sizes, 1000);
        return FileLoader.getInstance(r3).getPathToAttach(r02, true).getAbsolutePath();
    L8:
        return this.path;
    }

    public Bitmap getSegmentedOutBitmap() {
        Bitmap r0 = this.bitmap;
        if (r0 != null) goto L5;
        return null;
    L5:
        Bitmap r2 = this.segmentedImage;
        if (r0 == null) goto L14;
        if (r2 == null) goto L14;
        int r3 = r0.getWidth();
        int r4 = r0.getHeight();
        if (((this.orientation / 90) % 2) != 1) goto L12;
        r3 = r0.getHeight();
        r4 = r0.getWidth();
    L12:
        Bitmap r5 = Bitmap.createBitmap(r3, r4, Bitmap.Config.ARGB_8888);
        Canvas r6 = new Canvas(r5);
        this.roundRectPath.rewind();
        RectF r7 = AndroidUtilities.rectTmp;
        float r32 = r3;
        float r42 = r4;
        r7.set(0.0f, 0.0f, r32, r42);
        float r9 = this.mirrorT.get();
        float r33 = r32 / 2.0f;
        r6.scale(1.0f - (r9 * 2.0f), 1.0f, r33, 0.0f);
        r6.skew(0.0f, ((4.0f * r9) * (1.0f - r9)) * 0.25f);
        this.roundRectPath.addRoundRect(r7, AndroidUtilities.dp(12.0f) * getScaleX(), AndroidUtilities.dp(12.0f) * getScaleY(), Path.Direction.CW);
        r6.clipPath(this.roundRectPath);
        r6.translate(r33, r42 / 2.0f);
        r6.rotate(this.orientation);
        r6.translate((-r0.getWidth()) / 2.0f, (-r0.getHeight()) / 2.0f);
        r7.set(0.0f, 0.0f, r0.getWidth(), r0.getHeight());
        r6.saveLayerAlpha(r7, 255, 31);
        r6.drawBitmap(r0, 0.0f, 0.0f, null);
        Paint r14 = new Paint(3);
        r14.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r6.save();
        r6.drawBitmap(r2, 0.0f, 0.0f, r14);
        r6.restore();
        r6.restore();
        return r5;
    L14:
        return null;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public RectOld getSelectionBounds() {
        ViewGroup r0 = (ViewGroup) getParent();
        if (r0 == null) goto L5;
        float r02 = r0.getScaleX();
        float r1 = (getMeasuredWidth() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        float r3 = (getMeasuredHeight() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        float r4 = (getMeasuredWidth() * getScale()) + (AndroidUtilities.dp(64.0f) / r02);
        getMeasuredHeight();
        getScale();
        AndroidUtilities.dp(64.0f);
        float r2 = (getPositionX() - (r1 / 2.0f)) * r02;
        return new RectOld(r2, (getPositionY() - (r3 / 2.0f)) * r02, ((r4 * r02) + r2) - r2, r3 * r02);
    L5:
        return new RectOld();
    }

    public boolean hasSegmentedImage() {
        if (this.segmentedImage == null) goto L6;
        return true;
    L6:
        return false;
    }

    public void highlightSegmented() {
        this.needHighlight = true;
        if (this.highlightStart > 0) goto L5;
    L6:
        this.highlightStart = System.currentTimeMillis();
    L7:
        FrameLayoutDrawer r4 = this.containerView;
        if (r4 == null) goto L11;
        r4.invalidate();
        return;
    L11:
        return;
    L5:
        if ((System.currentTimeMillis() - this.highlightStart) < 1000) goto L7;
        goto L6
    }

    public boolean isMirrored() {
        return this.mirrored;
    }

    public boolean isSegmented() {
        return this.segmented;
    }

    public void mirror() {
        mirror(false);
    }

    public void mirror(boolean r3) {
        boolean r0 = !this.mirrored;
        this.mirrored = r0;
        if (r3 == true) goto L5;
        this.mirrorT.set(r0, true);
    L5:
        FrameLayoutDrawer r2 = this.containerView;
        if (r2 == null) goto L9;
        r2.invalidate();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        Size r32 = this.baseSize;
        float r42 = r32.width;
        float r33 = r32.height;
        MediaController.CropState r0 = this.crop;
        if (r0 == null) goto L5;
        r42 = r42 * r0.cropPw;
        r33 = r33 * r0.cropPh;
    L5:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((int) r42, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) r33, TLObject.FLAG_30));
    }

    public void onSwitchSegmentedAnimationStarted(boolean r1) {
        this.overridenSegmented = true;
        FrameLayoutDrawer r0 = this.containerView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void preloadSegmented(String r1) {
        this.segmentingLoading = false;
    }

    public File saveSegmentedImage(int r4) {
        if (this.segmentedImage != null) goto L7;
        return null;
    L7:
        if (this.segmentedFile != null) goto L14;
        this.segmentedFile = StoryEntry.makeCacheFile(r4, "webp");
        this.segmentedImage.compress(Bitmap.CompressFormat.WEBP, 100, new FileOutputStream(this.segmentedFile));     // Catch: FileNotFoundException -> L11
    L11:
        e = move-exception;
        e.printStackTrace();
    L14:
        return this.segmentedFile;
    }

    public void segmentImage(Bitmap r3) {
        if (this.segmentingLoaded == false) goto L5;
        return;
    L5:
        if (this.segmentingLoading == true) goto L11;
        if (r3 == null) goto L12;
        SubjectSegmenter r0 = SubjectSegmentation.getClient(new SubjectSegmenterOptions.Builder().enableForegroundBitmap().build());
        this.segmentingLoading = true;
        r0.process(InputImage.fromBitmap(r3, this.orientation)).addOnSuccessListener(new PhotoView$$ExternalSyntheticLambda1(this)).addOnFailureListener(new PhotoView$$ExternalSyntheticLambda2(this, r3));
        return;
    L12:
        return;
    }

    public void stickerDraw(Canvas r22) {
        if (this.containerView != null) goto L5;
        return;
    L5:
        r22.save();
        float r2 = this.mirrorT.set(this.mirrored);
        r22.scale(1.0f - (r2 * 2.0f), 1.0f, this.baseSize.width / 2.0f, 0.0f);
        r22.skew(0.0f, ((4.0f * r2) * (1.0f - r2)) * 0.25f);
        float r23 = this.segmentedT.set(this.segmented);
        if (this.segmented == true) goto L37;
        r22.save();
        this.bitmapPaint.setAlpha((int) ((1.0f - r23) * 255.0f));
        if (this.bitmap == null) goto L19;
        r22.translate(this.containerView.getWidth() / 2.0f, this.containerView.getHeight() / 2.0f);
        r22.rotate(this.orientation);
        float r3 = Math.max(this.baseSize.width / this.bitmap.getWidth(), this.baseSize.height / this.bitmap.getHeight());
        r22.scale(r3, r3);
        if (this.crop == null) goto L18;
        r22.rotate(-getOrientation());
        int r32 = getContentWidth();
        int r4 = getContentHeight();
        if ((((getOrientation() + this.crop.transformRotation) / 90) % 2) != 1) goto L14;
        r32 = getContentHeight();
        r4 = getContentWidth();
    L14:
        MediaController.CropState r6 = this.crop;
        float r7 = r6.cropPw;
        float r62 = r6.cropPh;
        float r33 = r32;
        float r42 = r4;
        r22.clipRect(((-r32) * r7) / 2.0f, ((-r4) * r62) / 2.0f, (r7 * r33) / 2.0f, (r62 * r42) / 2.0f);
        float r5 = this.crop.cropScale;
        r22.scale(r5, r5);
        MediaController.CropState r52 = this.crop;
        r22.translate(r52.cropPx * r33, r52.cropPy * r42);
        r22.rotate(this.crop.cropRotate + r3.transformRotation);
        if (this.crop.mirrored == false) goto L17;
        r22.scale(-1.0f, 1.0f);
    L17:
        r22.rotate(getOrientation());
    L18:
        r22.translate((-this.bitmap.getWidth()) / 2.0f, (-this.bitmap.getHeight()) / 2.0f);
        this.bitmapSrc.set(0, 0, this.bitmap.getWidth(), this.bitmap.getHeight());
        this.bitmapDst.set(0, 0, this.bitmap.getWidth(), this.bitmap.getHeight());
        r22.drawBitmap(this.bitmap, this.bitmapSrc, this.bitmapDst, this.bitmapPaint);
    L19:
        r22.restore();
        if (r23 <= 0.0f) goto L23;
        drawSegmented(r22);
    L23:
        if (this.segmentedImage == null) goto L38;
        Size r24 = this.baseSize;
        r22.saveLayerAlpha(0.0f, 0.0f, r24.width, r24.height, 255, 31);
        drawSegmented(r22);
        r22.save();
        long r1 = System.currentTimeMillis();
        if (this.highlightStart > 0) goto L27;
        this.highlightStart = r1;
    L27:
        float r34 = this.baseSize.width;
        float r16 = r34 * 0.8f;
        float r72 = (r1 - this.highlightStart) / 1000.0f;
        float r8 = (((2.0f * r16) + r34) * r72) - r16;
        if (this.highlightPaint != null) goto L30;
        Paint r12 = new Paint(1);
        this.highlightPaint = r12;
        r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.highlightGradient = new LinearGradient(0.0f, 0.0f, r16, 0.0f, new int[]{16707212, 1727983244, 1727983244, 16707212}, new float[]{0.0f, 0.4f, 0.6f, 1.0f}, Shader.TileMode.CLAMP);
        Matrix r13 = new Matrix();
        this.highlightGradientMatrix = r13;
        this.highlightGradient.setLocalMatrix(r13);
        this.highlightPaint.setShader(this.highlightGradient);
    L30:
        this.highlightGradientMatrix.reset();
        this.highlightGradientMatrix.postTranslate(r8, 0.0f);
        this.highlightGradient.setLocalMatrix(this.highlightGradientMatrix);
        Size r14 = this.baseSize;
        r22.drawRect(0.0f, 0.0f, (int) r14.width, (int) r14.height, this.highlightPaint);
        r22.restore();
        r22.restore();
        if (r72 > 0.0f) goto L35;
        if (this.needHighlight == false) goto L38;
    L35:
        if (r72 >= 1.0f) goto L38;
        this.needHighlight = false;
        this.containerView.invalidate();
    L38:
        r22.restore();
        return;
    L37:
        this.highlightStart = -1;
        this.needHighlight = false;
        drawSegmented(r22);
        goto L38
    }

    public void toggleSegmented(boolean r3) {
        boolean r0 = this.segmented;
        boolean r1 = !r0;
        this.segmented = r1;
        if (r3 == false) goto L6;
        if (r0 == true) goto L6;
        this.overridenSegmented = false;
    L6:
        if (r3 == true) goto L8;
        this.segmentedT.set(r1, true);
    L8:
        FrameLayoutDrawer r2 = this.containerView;
        if (r2 == null) goto L12;
        r2.invalidate();
        return;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public void updatePosition() {
        Size r0 = this.baseSize;
        float r1 = r0.width / 2.0f;
        float r02 = r0.height / 2.0f;
        MediaController.CropState r2 = this.crop;
        if (r2 == null) goto L5;
        r1 = r1 * r2.cropPw;
        r02 = r02 * r2.cropPh;
    L5:
        setX(getPositionX() - r1);
        setY(getPositionY() - r02);
        updateSelectionView();
    }
}
