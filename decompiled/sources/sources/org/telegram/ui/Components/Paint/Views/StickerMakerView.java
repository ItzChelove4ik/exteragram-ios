package org.telegram.ui.Components.Paint.Views;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.MediaError;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions;
import com.google.mlkit.vision.segmentation.subject.Subject;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentation;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmentationResult;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenter;
import com.google.mlkit.vision.segmentation.subject.SubjectSegmenterOptions;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.EmuDetector;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.VideoEditedInfo;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.ObjectDetectionEmojis;
import org.telegram.ui.Components.Paint.Views.PaintWeightChooserView;
import org.telegram.ui.Components.ThanosEffect;
import org.telegram.ui.Stories.recorder.DownloadButton;
import org.telegram.ui.Stories.recorder.StoryEntry;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class StickerMakerView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final TextView actionTextView;
    private final Path areaPath;
    private final Paint bgPaint;
    private final Path bgPath;
    private final Paint borderPaint;
    private ValueAnimator bordersAnimator;
    private float bordersAnimatorValue;
    private float bordersAnimatorValueStart;
    private final PathMeasure bordersPathMeasure;
    private int containerHeight;
    private int containerWidth;
    public int currentAccount;
    private final Paint dashPaint;
    private final Path dashPath;
    public String detectedEmoji;
    public boolean empty;
    private Rect exclusionRect;
    private ArrayList<Rect> exclusionRects;
    private Bitmap filteredBitmap;
    private float imageReceiverHeight;
    private final Matrix imageReceiverMatrix;
    private float imageReceiverWidth;
    private boolean isSegmentedState;
    public boolean isThanosInProgress;
    private DownloadButton.PreparingVideoToast loadingToast;
    public SegmentedObject[] objects;
    public int orientation;
    private final AnimatedFloat outlineAlpha;
    private final RectF outlineBounds;
    private Path outlineBoundsInnerPath;
    private Path outlineBoundsPath;
    public final Matrix outlineMatrix;
    public boolean outlineVisible;
    public float outlineWidth;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Path screenPath;
    private final AnimatedFloat segmentBorderAlpha;
    private float segmentBorderImageHeight;
    private float segmentBorderImageWidth;
    private final Paint segmentBorderPaint;
    private volatile boolean segmentingLoaded;
    private volatile boolean segmentingLoading;
    private SegmentedObject selectedObject;
    public boolean setOutlineBounds;
    private volatile Bitmap sourceBitmap;
    private int stickerCornerRoundness;
    private StickerCutOutBtn stickerCutOutBtn;
    private StickerUploader stickerUploader;
    private ThanosEffect thanosEffect;
    float tx;
    float ty;
    public PaintWeightChooserView weightChooserView;

    /* renamed from: org.telegram.ui.Components.Paint.Views.StickerMakerView$1, reason: invalid class name */
    public class AnonymousClass1 implements PaintWeightChooserView.ValueOverride {
        final /* synthetic */ StickerMakerView this$0;

        public AnonymousClass1(StickerMakerView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public float get() {
            return this.this$0.outlineWidth;
        }

        @Override // org.telegram.ui.Components.Paint.Views.PaintWeightChooserView.ValueOverride
        public void set(float r1) {
            this.this$0.setOutlineWidth(r1);
        }
    }

    public static class Point extends android.graphics.Point {
        public Point(int r1, int r2, float r3) {
            super((int) (r1 * r3), (int) (r2 * r3));
        }
    }

    public class SegmentedObject {
        private final boolean USE_POINTS;
        private float borderImageHeight;
        private float borderImageWidth;
        private final Paint bordersDiffStrokePaint;
        private final Paint bordersFillPaint;
        private final Paint bordersStrokePaint;
        public RectF bounds;
        public Bitmap darkMaskImage;
        public boolean hover;
        public Bitmap image;
        public int orientation;
        public Bitmap overrideDarkMaskImage;
        public Bitmap overrideImage;
        private final Path partSegmentBorderPath;
        private float[] points;
        private int pointsCount;
        private final Paint pointsHighlightPaint;
        private final Paint pointsPaint;
        public RectF rotatedBounds;
        private final Path segmentBorderPath;
        public AnimatedFloat select;
        final /* synthetic */ StickerMakerView this$0;

        /* renamed from: -$$Nest$fgetborderImageHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11592$$Nest$fgetborderImageHeight(SegmentedObject r0) {
            return r0.borderImageHeight;
        }

        /* renamed from: -$$Nest$fgetborderImageWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11593$$Nest$fgetborderImageWidth(SegmentedObject r0) {
            return r0.borderImageWidth;
        }

        /* renamed from: -$$Nest$fgetsegmentBorderPath, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Path m11594$$Nest$fgetsegmentBorderPath(SegmentedObject r0) {
            return r0.segmentBorderPath;
        }

        /* renamed from: -$$Nest$fputborderImageHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11595$$Nest$fputborderImageHeight(SegmentedObject r0, float r1) {
            r0.borderImageHeight = r1;
        }

        /* renamed from: -$$Nest$fputborderImageWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11596$$Nest$fputborderImageWidth(SegmentedObject r0, float r1) {
            r0.borderImageWidth = r1;
        }

        public SegmentedObject(StickerMakerView r9) {
            this.this$0 = r9;
            this.select = new AnimatedFloat(0.0f, null, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.bounds = new RectF();
            this.rotatedBounds = new RectF();
            this.segmentBorderPath = new Path();
            this.partSegmentBorderPath = new Path();
            this.bordersFillPaint = new Paint(1);
            this.bordersStrokePaint = new Paint(1);
            this.bordersDiffStrokePaint = new Paint(1);
            this.pointsPaint = new Paint(1);
            this.pointsHighlightPaint = new Paint(1);
            this.USE_POINTS = true;
        }

        public void drawAnimationBorders(Canvas r17, float r18, float r19, View r20) {
            this.select.setParent(r20);
            if (StickerMakerView.m11591$$Nest$fgetsourceBitmap(this.this$0) != null) goto L5;
            return;
        L5:
            if (r19 <= 0.0f) goto L43;
            float r4 = AndroidUtilities.lerp(1.0f, 1.065f, r19) * AndroidUtilities.lerp(1.0f, 1.05f, this.select.set(this.hover));
            int r6 = (this.orientation / 90) % 2;
            StickerMakerView r8 = this.this$0;
            if (r6 == 0) goto L10;
            int r62 = StickerMakerView.m11591$$Nest$fgetsourceBitmap(r8).getHeight();
            int r82 = StickerMakerView.m11591$$Nest$fgetsourceBitmap(this.this$0).getWidth();
        L11:
            r17.save();
            float r63 = r62;
            float r9 = this.rotatedBounds.centerX() / r63;
            float r10 = this.borderImageWidth;
            float r92 = (r9 * r10) - (r10 / 2.0f);
            float r83 = r82;
            float r102 = this.rotatedBounds.centerY() / r83;
            float r12 = this.borderImageHeight;
            r17.scale(r4, r4, r92, (r102 * r12) - (r12 / 2.0f));
            if (this.points == null) goto L21;
            int r42 = this.pointsCount;
            int r122 = (int) (r42 * r18);
            int r43 = Math.min(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, (int) (r42 * 0.6f)) + r122;
            if (this.pointsCount <= 0) goto L21;
        L15:
            if (r122 > r43) goto L21;
            float r13 = 1.0f - ((r43 - r122) / this.pointsCount);
            if (r13 <= 0.0f) goto L19;
            this.pointsHighlightPaint.setAlpha((int) ((r13 * 10.2f) * r19));
            r17.drawPoints(this.points, (r122 % this.pointsCount) * 2, 2, this.pointsHighlightPaint);
        L19:
            r122 = r122 + 1;
        L21:
            if (getImage() == null) goto L24;
            r17.save();
            r17.rotate(this.orientation);
            r17.scale((1.0f / r63) * this.borderImageWidth, (1.0f / r83) * this.borderImageHeight);
            r17.drawBitmap(getImage(), (-StickerMakerView.m11591$$Nest$fgetsourceBitmap(this.this$0).getWidth()) / 2.0f, (-StickerMakerView.m11591$$Nest$fgetsourceBitmap(this.this$0).getHeight()) / 2.0f, null);
            r17.restore();
        L24:
            if (this.points == null) goto L30;
            int r3 = this.pointsCount;
            int r44 = (int) (r3 * r18);
            int r32 = Math.min(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, (int) (r3 * 0.6f)) + r44;
            if (this.pointsCount <= 0) goto L36;
            int r84 = r44;
        L28:
            if (r84 > r32) goto L36;
            float r93 = (r84 - r44) / (r32 - r44);
            this.pointsPaint.setAlpha((int) ((Math.min(1.0f, Math.min(r93, 1.0f - r93) * 4.0f) * 255.0f) * r19));
            r17.drawPoints(this.points, (r84 % this.pointsCount) * 2, 2, this.pointsPaint);
            r84 = r84 + 1;
        L36:
            r17.restore();
            return;
        L30:
            StickerMakerView.m11588$$Nest$fgetbordersPathMeasure(this.this$0).setPath(this.segmentBorderPath, false);
            this.partSegmentBorderPath.reset();
            float r45 = StickerMakerView.m11588$$Nest$fgetbordersPathMeasure(this.this$0).getLength();
            if (r45 == 0.0f) goto L44;
            StickerMakerView.m11590$$Nest$fgetsegmentBorderPaint(this.this$0).setAlpha((int) (255.0f * r19));
            StickerMakerView.m11587$$Nest$fgetborderPaint(this.this$0).setAlpha((int) (r19 * 64.0f));
            r17.drawPath(this.partSegmentBorderPath, StickerMakerView.m11587$$Nest$fgetborderPaint(this.this$0));
            float r2 = r18 + 0.2f;
            StickerMakerView.m11588$$Nest$fgetbordersPathMeasure(this.this$0).getSegment(r45 * r18, r45 * r2, this.partSegmentBorderPath, true);
            r17.drawPath(this.partSegmentBorderPath, StickerMakerView.m11590$$Nest$fgetsegmentBorderPaint(this.this$0));
            r17.drawPath(this.partSegmentBorderPath, StickerMakerView.m11590$$Nest$fgetsegmentBorderPaint(this.this$0));
            if (r2 <= 1.0f) goto L36;
            this.partSegmentBorderPath.reset();
            StickerMakerView.m11588$$Nest$fgetbordersPathMeasure(this.this$0).setPath(this.segmentBorderPath, false);
            StickerMakerView.m11588$$Nest$fgetbordersPathMeasure(this.this$0).getSegment(0.0f, (r2 - 1.0f) * r45, this.partSegmentBorderPath, true);
            r17.drawPath(this.partSegmentBorderPath, StickerMakerView.m11590$$Nest$fgetsegmentBorderPaint(this.this$0));
            r17.drawPath(this.partSegmentBorderPath, StickerMakerView.m11590$$Nest$fgetsegmentBorderPaint(this.this$0));
            goto L36
        L44:
            return;
        L10:
            r62 = StickerMakerView.m11591$$Nest$fgetsourceBitmap(r8).getWidth();
            r82 = StickerMakerView.m11591$$Nest$fgetsourceBitmap(this.this$0).getHeight();
            goto L11
        }

        public void drawOutline(Canvas r3, boolean r4, float r5, float r6) {
            if (StickerMakerView.m11589$$Nest$fgetoutlineBoundsPath(this.this$0) != null) goto L5;
            return;
        L5:
            r3.save();
            r3.clipPath(StickerMakerView.m11589$$Nest$fgetoutlineBoundsPath(this.this$0));
            if (StickerMakerView.m11591$$Nest$fgetsourceBitmap(this.this$0) == null) goto L14;
            if (r4 == false) goto L9;
            Paint r0 = this.bordersStrokePaint;
        L10:
            r0.setAlpha((int) (r6 * 255.0f));
            r0.setStrokeWidth(AndroidUtilities.dp(r5));
            r3.drawPath(this.segmentBorderPath, r0);
            if (StickerMakerView.m11589$$Nest$fgetoutlineBoundsPath(this.this$0) == null) goto L14;
            if (r4 == false) goto L14;
            r3.clipPath(this.segmentBorderPath);
            r0.setStrokeWidth(AndroidUtilities.dp(r5 * 2.0f));
            r3.drawPath(StickerMakerView.m11589$$Nest$fgetoutlineBoundsPath(this.this$0), r0);
            goto L14
        L9:
            r0 = this.bordersFillPaint;
        L14:
            r3.restore();
        }

        public Bitmap getDarkMaskImage() {
            Bitmap r0 = this.overrideDarkMaskImage;
            if (r0 == null) goto L6;
            return r0;
        L6:
            return this.darkMaskImage;
        }

        public Bitmap getImage() {
            Bitmap r0 = this.overrideImage;
            if (r0 == null) goto L6;
            return r0;
        L6:
            return this.image;
        }

        public void initPoints() {
            PathMeasure r0 = new PathMeasure();
            r0.setPath(this.segmentBorderPath, true);
            float r1 = r0.getLength();
            int r3 = (int) Math.ceil(r1 / AndroidUtilities.dp(2.0f));
            this.pointsCount = r3;
            this.points = new float[r3 * 2];
            float[] r32 = new float[2];
            int r5 = 0;
        L3:
            int r6 = this.pointsCount;
            if (r5 >= r6) goto L6;
            r0.getPosTan(((r5 / r6) * r1) % r1, r32, null);
            float[] r62 = this.points;
            int r7 = r5 * 2;
            r62[r7] = r32[0];
            r62[r7 + 1] = r32[1];
            r5 = r5 + 1;
            goto L3
        L6:
            this.bordersFillPaint.setStyle(Paint.Style.FILL);
            this.bordersFillPaint.setColor(-1);
            Paint r02 = this.bordersFillPaint;
            Paint.Join r2 = Paint.Join.ROUND;
            r02.setStrokeJoin(r2);
            Paint r03 = this.bordersFillPaint;
            Paint.Cap r33 = Paint.Cap.ROUND;
            r03.setStrokeCap(r33);
            this.bordersFillPaint.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(10.0f)));
            Paint r04 = this.bordersStrokePaint;
            Paint.Style r4 = Paint.Style.STROKE;
            r04.setStyle(r4);
            this.bordersStrokePaint.setColor(-1);
            this.bordersStrokePaint.setStrokeJoin(r2);
            this.bordersStrokePaint.setStrokeCap(r33);
            this.bordersStrokePaint.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(10.0f)));
            this.pointsPaint.setStyle(r4);
            this.pointsPaint.setStrokeWidth(AndroidUtilities.dp(4.0f));
            this.pointsPaint.setColor(-1);
            this.pointsPaint.setStrokeCap(r33);
            Paint r05 = this.pointsPaint;
            float r52 = AndroidUtilities.dp(0.33f);
            BlurMaskFilter.Blur r63 = BlurMaskFilter.Blur.NORMAL;
            r05.setMaskFilter(new BlurMaskFilter(r52, r63));
            this.pointsHighlightPaint.setStyle(r4);
            this.pointsHighlightPaint.setColor(Theme.multAlpha(-1, 0.04f));
            this.pointsHighlightPaint.setStrokeCap(r33);
            this.pointsHighlightPaint.setStrokeWidth(AndroidUtilities.dp(20.0f));
            this.pointsHighlightPaint.setColor(Theme.multAlpha(-1, 0.04f));
            this.pointsHighlightPaint.setMaskFilter(new BlurMaskFilter(AndroidUtilities.dp(60.0f), r63));
        }

        public Bitmap makeDarkMaskImage() {
            Bitmap r0 = Bitmap.createBitmap(getImage().getWidth(), getImage().getHeight(), Bitmap.Config.ARGB_8888);
            Canvas r1 = new Canvas(r0);
            r1.drawColor(-16777216);
            Paint r2 = new Paint(3);
            r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            r1.drawBitmap(getImage(), 0.0f, 0.0f, r2);
            return r0;
        }

        public void recycle() {
            this.segmentBorderPath.reset();
            Bitmap r0 = this.overrideImage;
            if (r0 == null) goto L5;
            r0.recycle();
            this.overrideImage = null;
        L5:
            Bitmap r02 = this.image;
            if (r02 == null) goto L8;
            r02.recycle();
            this.image = null;
        L8:
            Bitmap r03 = this.overrideDarkMaskImage;
            if (r03 == null) goto L11;
            r03.recycle();
            this.overrideDarkMaskImage = null;
        L11:
            Bitmap r04 = this.darkMaskImage;
            if (r04 == null) goto L15;
            r04.recycle();
            this.darkMaskImage = null;
            return;
        }
    }

    public static class StickerUploader {
        public boolean addToFavorite;
        private float convertingProgress;
        public Utilities.Callback2<String, TLRPC.InputDocument> customHandler;
        public String emoji;
        public TLRPC.InputFile file;
        public ArrayList<File> files;
        public ArrayList<File> finalFiles;
        public String finalPath;
        public TLRPC.TL_messageMediaDocument mediaDocument;
        public MessageObject messageObject;
        public String path;
        public TLRPC.Document replacedSticker;
        public int reqId;
        public long sendToDialogId;
        public CharSequence stickerPackName;
        public TLRPC.StickerSet stickerSet;
        public String thumbPath;
        public TLRPC.TL_inputStickerSetItem tlInputStickerSetItem;
        private float uploadProgress;
        public boolean uploaded;
        public TLRPC.Document uploadedSticker;
        public VideoEditedInfo videoEditedInfo;
        public Utilities.Callback<Boolean> whenDone;

        /* renamed from: -$$Nest$fgetconvertingProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11597$$Nest$fgetconvertingProgress(StickerUploader r0) {
            return r0.convertingProgress;
        }

        /* renamed from: -$$Nest$fgetuploadProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m11598$$Nest$fgetuploadProgress(StickerUploader r0) {
            return r0.uploadProgress;
        }

        /* renamed from: -$$Nest$fputconvertingProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11599$$Nest$fputconvertingProgress(StickerUploader r0, float r1) {
            r0.convertingProgress = r1;
        }

        /* renamed from: -$$Nest$fputuploadProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11600$$Nest$fputuploadProgress(StickerUploader r0, float r1) {
            r0.uploadProgress = r1;
        }

        public StickerUploader() {
            this.finalFiles = new ArrayList();
            this.files = new ArrayList();
            this.convertingProgress = 0.0f;
            this.uploadProgress = 0.0f;
        }

        public void destroy(boolean r5) {
            int r0 = 0;
            if (r5 == false) goto L11;
            ArrayList<File> r52 = this.finalFiles;
            int r1 = r52.size();
            int r2 = 0;
        L5:
            if (r2 >= r1) goto L11;
            File r3 = r52.get(r2);
            r2 = r2 + 1;
            r3.delete();     // Catch: Exception -> L9
        L9:
            e = move-exception;
            FileLog.e(e);
        L11:
            this.finalFiles.clear();
            ArrayList<File> r53 = this.files;
            int r12 = r53.size();
        L12:
            if (r0 >= r12) goto L18;
            File r22 = r53.get(r0);
            r0 = r0 + 1;
            r22.delete();     // Catch: Exception -> L16
        L16:
            e = move-exception;
            FileLog.e(e);
            goto L12
        L18:
            this.files.clear();
        }

        public float getProgress() {
            if (this.customHandler != null) goto L5;
            float r0 = 0.9f;
        L7:
            if (this.videoEditedInfo != null) goto L11;
            return r0 * this.uploadProgress;
        L11:
            return r0 * ((this.convertingProgress * 0.5f) + (this.uploadProgress * 0.5f));
        L5:
            r0 = 1.0f;
            goto L7
        }

        public void setupFiles() {
            if (TextUtils.isEmpty(this.finalPath) == true) goto L6;
            this.finalFiles.add(new File(this.finalPath));
        L6:
            if (TextUtils.isEmpty(this.path) == true) goto L11;
            if (TextUtils.equals(this.path, this.finalPath) == true) goto L11;
            this.files.add(new File(this.path));
        L11:
            if (TextUtils.isEmpty(this.thumbPath) == true) goto L14;
            this.files.add(new File(this.thumbPath));
            return;
        }
    }

    public static class SubjectMock {
        public Bitmap bitmap;
        public int height;
        public int startX;
        public int startY;
        public int width;

        private SubjectMock() {
        }

        public static SubjectMock mock(Bitmap r8) {
            SubjectMock r0 = new SubjectMock();
            int r1 = (int) (Math.min(r8.getWidth(), r8.getHeight()) * 0.4f);
            r0.height = r1;
            r0.width = r1;
            r0.bitmap = Bitmap.createBitmap(r1, r1, Bitmap.Config.ARGB_8888);
            new Canvas(r0.bitmap).drawRect(0.0f, 0.0f, r0.width, r0.height, Theme.DEBUG_RED);
            r0.startX = (r8.getWidth() - r0.width) / 2;
            r0.startY = (r8.getHeight() - r0.height) / 2;
            return r0;
        }

        public static SubjectMock of(Subject r2) {
            SubjectMock r0 = new SubjectMock();
            r0.bitmap = r2.getBitmap();
            r0.startX = r2.getStartX();
            r0.startY = r2.getStartY();
            r0.width = r2.getWidth();
            r0.height = r2.getHeight();
            return r0;
        }
    }

    public static /* synthetic */ void $r8$lambda$17hkri3t3bkbK5ci6si4jD3CHDU(StickerMakerView r0, TLObject r1, int r2, StickerUploader r3, TLRPC.TL_error r4) {
        r0.lambda$afterUploadingMedia$26(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$1hSqXjaUxTUkJ0aaHgVEGmFHtxg(StickerMakerView r0, StickerUploader r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$uploadMedia$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$3O9QmiTmUliSRT9Vf722x0VgBdI(StickerMakerView r0) {
        r0.lambda$showLoadingDialog$13();
    }

    public static /* synthetic */ void $r8$lambda$4t4VxzIqhJa8vJrYMQga6xibM9U(StickerMakerView r0, TLObject r1, StickerUploader r2) {
        r0.lambda$afterUploadingMedia$17(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$51aX2r11dUyp0CAhNcOtQJFQyw8(StickerMakerView r0, TLObject r1, StickerUploader r2) {
        r0.lambda$afterUploadingMedia$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ElSmAoKChmg9dKo1aU6HKEnqzU8(StickerUploader r7) {
        MediaDataController.getInstance(UserConfig.selectedAccount).addRecentSticker(2, null, r7.mediaDocument.document, (int) (System.currentTimeMillis() / 1000), false);
    }

    public static /* synthetic */ void $r8$lambda$GpXe47Svl67EFm1jsUCnec8jXAI(StickerMakerView r0, List r1) {
        r0.lambda$segment$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$H8VUVsP_lRDMjS2diSxKwC2ZdGo(StickerMakerView r0, Utilities.Callback r1, String r2, String r3, CharSequence r4, boolean r5, long r6, TLRPC.StickerSet r8, TLRPC.Document r9, TLRPC.Document r10, VideoEditedInfo r11, String r12, Utilities.Callback2 r13) {
        r0.lambda$uploadStickerFile$12(r1, r2, r3, r4, r5, r6, r8, r9, r10, r11, r12, r13);
    }

    public static /* synthetic */ void $r8$lambda$KGUANJerxjJzSa71OZWqUYpRG88(StickerMakerView r0, int r1) {
        r0.lambda$afterUploadingMedia$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$N34q7RjOP0nAX0Rvuf8jtIHGY0g(Utilities.Callback r3, SubjectSegmentationResult r4) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r4.getSubjects().size()) goto L6;
        r0.add(SubjectMock.of(r4.getSubjects().get(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        r3.run(r0);
    }

    /* renamed from: $r8$lambda$OtgUm0DQHDsMQSXyXtK-Dgz42J8, reason: not valid java name */
    public static /* synthetic */ void m11582$r8$lambda$OtgUm0DQHDsMQSXyXtKDgz42J8(StickerMakerView r0, int r1, Utilities.Callback r2, List r3) {
        r0.lambda$segmentImage$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$PsSSzhFuRFQ0x9gxKeb7mkeR1CM(Exception r0) {
    }

    public static /* synthetic */ void $r8$lambda$QN1HjNvzbbb2Br1OovJIm3cCJWk(StickerMakerView r0, ArrayList r1) {
        r0.lambda$segmentImage$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$U_9iQbccptsISVw46jDeyVHiU1Q(StickerMakerView r0, Utilities.Callback r1, View r2) {
        r0.lambda$enableClippingMode$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ZTjfAhAcnRZPA1QhZkKKJ3X3vAU(StickerMakerView r0) {
        r0.lambda$getThanosEffect$0();
    }

    public static /* synthetic */ void $r8$lambda$bwgFaA2Y2xdxKbxNcSw_PMsFC04(StickerMakerView r0, ValueAnimator r1) {
        r0.lambda$enableClippingMode$2(r1);
    }

    /* renamed from: $r8$lambda$eKCtyt-UpTAzCsy5Xij2nFeT5HU, reason: not valid java name */
    public static /* synthetic */ void m11583$r8$lambda$eKCtytUpTAzCsy5Xij2nFeT5HU(StickerMakerView r0, Bitmap r1, int r2, Utilities.Callback r3) {
        r0.lambda$segment$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$f8PtxEjTusNedlaxwQGJSV2n6N8(StickerMakerView r0, int r1, StickerUploader r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$afterUploadingMedia$27(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$fBCQPfH91jrG6r4XEgMvfiZ_Ps4(StickerMakerView r0, TLObject r1, StickerUploader r2, TLRPC.TL_error r3) {
        r0.lambda$uploadMedia$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$g4nNohWAaVQsfIuXosd7zDqFQ6g(StickerMakerView r0, Bitmap r1, int r2, Utilities.Callback r3, Utilities.Callback r4, Exception r5) {
        r0.lambda$segment$9(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$jvwapija84xeBIaQWvUCTAyaqrE(StickerMakerView r0, int r1, List r2, ArrayList r3, Utilities.Callback r4) {
        r0.lambda$segmentImage$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$jzaEKhwAzeww3unXPkKGvW10T18(StickerMakerView r0, TLObject r1, StickerUploader r2) {
        r0.lambda$afterUploadingMedia$20(r1, r2);
    }

    /* renamed from: $r8$lambda$l-jsatAdj3eOPCLedWUapRs7p1g, reason: not valid java name */
    public static /* synthetic */ void m11584$r8$lambda$ljsatAdj3eOPCLedWUapRs7p1g(StickerMakerView r0, TLObject r1, int r2, StickerUploader r3, TLRPC.TL_error r4) {
        r0.lambda$afterUploadingMedia$18(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$mAO7lvCMRR7hb9vt1uB_UjjzWtc(StickerMakerView r0, int r1, StickerUploader r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$afterUploadingMedia$22(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$o_w3nLhs16roAMzbWOFmHoE9-Qk, reason: not valid java name */
    public static /* synthetic */ void m11585$r8$lambda$o_w3nLhs16roAMzbWOFmHoE9Qk(StickerMakerView r0, TLObject r1, int r2, StickerUploader r3, TLRPC.TL_error r4) {
        r0.lambda$afterUploadingMedia$21(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$rwHjF8b0pLirbzEHAbbWpY892oA() {
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationNameOnUIThread(NotificationCenter.customStickerCreated, new Object[0]);
    }

    /* renamed from: $r8$lambda$sHVgvvuV1-foC7_wz1bU3Ticp-o, reason: not valid java name */
    public static /* synthetic */ void m11586$r8$lambda$sHVgvvuV1foC7_wz1bU3Ticpo(StickerMakerView r0, int r1, StickerUploader r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$afterUploadingMedia$19(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$zrBXvr2sY2G5NYIAa5bYBM8FWms(StickerMakerView r0, ArrayList r1, Utilities.Callback r2, SegmentedObject r3) {
        r0.lambda$segmentImage$3(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetborderPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11587$$Nest$fgetborderPaint(StickerMakerView r0) {
        return r0.borderPaint;
    }

    /* renamed from: -$$Nest$fgetbordersPathMeasure, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PathMeasure m11588$$Nest$fgetbordersPathMeasure(StickerMakerView r0) {
        return r0.bordersPathMeasure;
    }

    /* renamed from: -$$Nest$fgetoutlineBoundsPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m11589$$Nest$fgetoutlineBoundsPath(StickerMakerView r0) {
        return r0.outlineBoundsPath;
    }

    /* renamed from: -$$Nest$fgetsegmentBorderPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m11590$$Nest$fgetsegmentBorderPaint(StickerMakerView r0) {
        return r0.segmentBorderPaint;
    }

    /* renamed from: -$$Nest$fgetsourceBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m11591$$Nest$fgetsourceBitmap(StickerMakerView r0) {
        return r0.sourceBitmap;
    }

    public StickerMakerView(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.currentAccount = -1;
        CubicBezierInterpolator r10 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.segmentBorderAlpha = new AnimatedFloat(0.0f, null, 0, 420, r10);
        this.outlineAlpha = new AnimatedFloat(0.0f, null, 0, 420, r10);
        Paint r3 = new Paint(1);
        this.dashPaint = r3;
        Paint r5 = new Paint(1);
        this.bgPaint = r5;
        Paint r6 = new Paint(1);
        this.borderPaint = r6;
        Paint r7 = new Paint(1);
        this.segmentBorderPaint = r7;
        this.bordersPathMeasure = new PathMeasure();
        this.bgPath = new Path();
        this.areaPath = new Path();
        this.screenPath = new Path();
        this.dashPath = new Path();
        this.stickerCornerRoundness = 0;
        this.outlineWidth = 2.0f;
        this.imageReceiverMatrix = new Matrix();
        this.outlineMatrix = new Matrix();
        this.outlineBounds = new RectF();
        this.exclusionRects = new ArrayList();
        this.exclusionRect = new Rect();
        this.resourcesProvider = r19;
        r3.setColor(-1);
        Paint.Style r102 = Paint.Style.STROKE;
        r3.setStyle(r102);
        r3.setStrokeWidth(AndroidUtilities.dp(2.0f));
        Paint.Cap r9 = Paint.Cap.ROUND;
        r3.setStrokeCap(r9);
        r3.setPathEffect(new DashPathEffect(new float[]{AndroidUtilities.dp(5.0f), AndroidUtilities.dp(10.0f)}, 0.5f));
        r3.setShadowLayer(AndroidUtilities.dpf2(0.75f), 0.0f, 0.0f, 1342177280);
        r3.setAlpha(140);
        TextView r32 = new TextView(r18);
        this.actionTextView = r32;
        r32.setTextSize(1, 13.0f);
        r32.setTextColor(-1);
        r32.setAlpha(0.0f);
        r32.setScaleX(0.3f);
        r32.setScaleY(0.3f);
        addView(r32, LayoutHelper.createFrame(-2, -2, 17));
        r6.setColor(-1);
        r6.setStrokeWidth(AndroidUtilities.dp(3.0f));
        r6.setStyle(r102);
        r6.setStrokeCap(r9);
        r6.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(20.0f)));
        float r14 = AndroidUtilities.dp(4.0f);
        BlurMaskFilter.Blur r33 = BlurMaskFilter.Blur.NORMAL;
        r6.setMaskFilter(new BlurMaskFilter(r14, r33));
        r7.setColor(-1);
        r7.setStrokeWidth(AndroidUtilities.dp(3.0f));
        r7.setStyle(r102);
        r7.setStrokeCap(r9);
        r7.setPathEffect(new CornerPathEffect(AndroidUtilities.dp(20.0f)));
        r7.setMaskFilter(new BlurMaskFilter(AndroidUtilities.dp(4.0f), r33));
        r5.setColor(1711276032);
        setLayerType(2, null);
        PaintWeightChooserView r34 = new PaintWeightChooserView(r18);
        this.weightChooserView = r34;
        r34.setAlpha(0.0f);
        this.weightChooserView.setTranslationX(-AndroidUtilities.dp(18.0f));
        this.weightChooserView.setMinMax(0.33f, 10.0f);
        this.weightChooserView.setBrushWeight(this.outlineWidth);
        this.weightChooserView.setValueOverride(new AnonymousClass1(this));
        this.weightChooserView.setTranslationX(-AndroidUtilities.dp(18.0f));
        this.weightChooserView.setAlpha(0.0f);
        addView(this.weightChooserView, LayoutHelper.createFrame(-1, -1.0f));
    }

    private void afterUploadingMedia() {
        StickerUploader r1 = this.stickerUploader;
        if (r1 == null) goto L39;
        int r2 = UserConfig.selectedAccount;
        r1.uploaded = true;
        if (r1.customHandler == null) goto L10;
        hideLoadingDialog();
        r1.customHandler.run(r1.finalPath, r1.tlInputStickerSetItem.document);
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda13(), 250);
        return;
    L10:
        if (r1.replacedSticker == null) goto L14;
        TLRPC.TL_stickers_replaceSticker r3 = new TLRPC.TL_stickers_replaceSticker();
        r3.sticker = MediaDataController.getInputStickerSetItem(r1.replacedSticker, r1.emoji).document;
        r3.new_sticker = r1.tlInputStickerSetItem;
        ConnectionsManager.getInstance(r2).sendRequest(r3, new StickerMakerView$$ExternalSyntheticLambda14(this, r2, r1));
        return;
    L14:
        if (r1.stickerPackName == null) goto L18;
        TLRPC.TL_stickers_createStickerSet r32 = new TLRPC.TL_stickers_createStickerSet();
        r32.user_id = new TLRPC.TL_inputUserSelf();
        r32.title = r1.stickerPackName.toString();
        r32.short_name = _UrlKt.FRAGMENT_ENCODE_SET;
        r32.stickers.add(r1.tlInputStickerSetItem);
        ConnectionsManager.getInstance(r2).sendRequest(r32, new StickerMakerView$$ExternalSyntheticLambda15(this, r2, r1));
        return;
    L18:
        if (r1.addToFavorite == false) goto L24;
        hideLoadingDialog();
        NotificationCenter.getInstance(r2).postNotificationNameOnUIThread(NotificationCenter.customStickerCreated, new Object[]{Boolean.FALSE});
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda16(r1), 350);
        Utilities.Callback<Boolean> r0 = r1.whenDone;
        if (r0 == null) goto L36;
        r0.run(Boolean.TRUE);
        return;
    L36:
        return;
    L24:
        if (r1.sendToDialogId == 0) goto L33;
        SendMessagesHelper.getInstance(r2).sendSticker(r1.mediaDocument.document, null, r1.sendToDialogId, null, null, null, null, null, true, 0, 0, false, null, null, 0, 0, null);
        DownloadButton.PreparingVideoToast r33 = this.loadingToast;
        if (r33 == null) goto L28;
        r33.setProgress(1.0f);
    L28:
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda17(this, r2), 450);
        Utilities.Callback<Boolean> r02 = r1.whenDone;
        if (r02 == null) goto L37;
        r02.run(Boolean.TRUE);
        r1.whenDone = null;
        return;
    L37:
        return;
    L33:
        if (r1.stickerSet == null) goto L38;
        TLRPC.TL_stickers_addStickerToSet r34 = new TLRPC.TL_stickers_addStickerToSet();
        r34.stickerset = MediaDataController.getInputStickerSet(r1.stickerSet);
        r34.sticker = r1.tlInputStickerSetItem;
        ConnectionsManager.getInstance(r2).sendRequest(r34, new StickerMakerView$$ExternalSyntheticLambda18(this, r2, r1));
        return;
    L38:
        return;
    }

    private void createSegmentImagePath(SegmentedObject r26, int r27, int r28) {
        int r1 = r26.getImage().getWidth();
        int r2 = r26.getImage().getHeight();
        float r3 = Math.max(r1, r2);
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L5;
        float r4 = 512.0f;
    L6:
        float r32 = r3 / r4;
        if (((r26.orientation / 90) % 2) == 0) goto L9;
        r1 = r26.getImage().getHeight();
        r2 = r26.getImage().getWidth();
    L9:
        float r12 = r1;
        float r22 = r2;
        Bitmap r7 = Bitmap.createBitmap((int) (r12 / r32), (int) (r22 / r32), Bitmap.Config.ARGB_8888);
        Canvas r33 = new Canvas(r7);
        RectF r42 = new RectF();
        r42.set(0.0f, 0.0f, r7.getWidth(), r7.getHeight());
        float r15 = 2.0f;
        if (r26.orientation == 0) goto L15;
        Matrix r6 = new Matrix();
        r6.postRotate(r26.orientation, r26.getImage().getWidth() / 2.0f, r26.image.getHeight() / 2.0f);
        if (((r26.orientation / 90) % 2) == 0) goto L14;
        float r5 = (r26.getImage().getHeight() - r26.getImage().getWidth()) / 2.0f;
        r6.postTranslate(r5, -r5);
    L14:
        r6.postScale(r42.width() / r12, r42.height() / r22);
        r33.drawBitmap(r26.getImage(), r6, new Paint(3));
    L16:
        int r34 = r7.getWidth() * r7.getHeight();
        int[] r8 = new int[r34];
        r7.getPixels(r8, 0, r7.getWidth(), 0, 0, r7.getWidth(), r7.getHeight());
        ArrayList r52 = new ArrayList();
        ArrayList r62 = new ArrayList();
        float r9 = r27;
        float r11 = r28;
        float r10 = Math.min(r9 / r7.getWidth(), r11 / r7.getHeight());
        Point r13 = null;
        Point r14 = null;
        int r43 = 0;
    L17:
        boolean r16 = true;
        if (r43 >= r34) goto L61;
        int r122 = r43 / r7.getWidth();
        float r18 = r15;
        int r152 = r43 - (r7.getWidth() * r122);
        int r17 = r8[r43];
        if (r17 == 0) goto L22;
        boolean r19 = true;
    L23:
        if (r17 != 0) goto L41;
        int r172 = r43 - 1;
        if (r172 < 0) goto L27;
        boolean r20 = true;
    L28:
        float r21 = r12;
        int r110 = r43 + 1;
        if (r110 >= r34) goto L31;
        boolean r222 = true;
    L32:
        if (r20 == true) goto L34;
    L36:
        if (r13 != null) goto L43;
        if (r222 == false) goto L43;
        if (r8[r110] == 0) goto L43;
        r13 = new Point(r152, r122, r10);
    L43:
        if (r152 != (r7.getWidth() - 1)) goto L45;
        boolean r111 = true;
    L46:
        if (r152 == 0) goto L49;
        r16 = false;
    L49:
        if (r111 == false) goto L57;
        if (r19 == false) goto L52;
        r14 = new Point(r152, r122, r10);
    L52:
        if (r13 == null) goto L54;
        r52.add(r13);
    L54:
        if (r14 == null) goto L56;
        r62.add(r14);
    L56:
        r13 = null;
        r14 = null;
    L57:
        if (r16 == false) goto L60;
        if (r19 == false) goto L60;
        r13 = new Point(r152, r122, r10);
    L60:
        r43 = r43 + 1;
        r15 = r18;
        r12 = r21;
        goto L17
    L45:
        r111 = false;
        goto L46
    L34:
        if (r8[r172] == 0) goto L36;
        r14 = new Point(r152, r122, r10);
        goto L36
    L31:
        r222 = false;
        goto L32
    L27:
        r20 = false;
        goto L28
    L41:
        r21 = r12;
        goto L43
    L22:
        r19 = false;
        goto L23
    L61:
        float r212 = r12;
        float r182 = r15;
        ArrayList r112 = new ArrayList();
        ArrayList r44 = new ArrayList();
        Point r123 = null;
        Point r132 = null;
        int r142 = 0;
    L62:
        if (r142 >= r34) goto L104;
        int r153 = r142 / r7.getHeight();
        float r192 = r22;
        int r23 = r142 - (r7.getHeight() * r153);
        if (r8[r153 + (r7.getWidth() * r23)] == 0) goto L66;
        boolean r173 = true;
    L67:
        if (r173 == true) goto L84;
        int r202 = r153 + ((r23 - 1) * r7.getWidth());
        Bitmap r232 = r7;
        int r72 = r153 + ((r23 + 1) * r7.getWidth());
        if (r202 < 0) goto L71;
        boolean r223 = true;
    L72:
        if (r72 >= r34) goto L74;
        boolean r24 = true;
    L75:
        if (r223 == true) goto L77;
    L79:
        if (r123 != null) goto L86;
        if (r24 == false) goto L86;
        if (r8[r72] == 0) goto L86;
        r123 = new Point(r153, r23, r10);
    L86:
        if (r23 != (r232.getHeight() - 1)) goto L88;
        boolean r73 = true;
    L89:
        if (r23 != 0) goto L91;
        boolean r203 = true;
    L92:
        if (r73 == false) goto L100;
        if (r173 == false) goto L95;
        r132 = new Point(r153, r23, r10);
    L95:
        if (r123 == null) goto L97;
        r112.add(r123);
    L97:
        if (r132 == null) goto L99;
        r44.add(r132);
    L99:
        r123 = null;
        r132 = null;
    L100:
        if (r203 == false) goto L103;
        if (r173 == false) goto L103;
        r123 = new Point(r153, r23, r10);
    L103:
        r142 = r142 + 1;
        r22 = r192;
        r7 = r232;
        goto L62
    L91:
        r203 = false;
        goto L92
    L88:
        r73 = false;
        goto L89
    L77:
        if (r8[r202] == 0) goto L79;
        r132 = new Point(r153, r23, r10);
        goto L79
    L74:
        r24 = false;
        goto L75
    L71:
        r223 = false;
        goto L72
    L84:
        r232 = r7;
        goto L86
    L66:
        r173 = false;
        goto L67
    L104:
        float r193 = r22;
        LinkedHashSet r25 = new LinkedHashSet();
        LinkedHashSet r35 = new LinkedHashSet();
        Collections.reverse(r62);
        Collections.reverse(r112);
        r35.addAll(r52);
        r35.addAll(r62);
        r25.addAll(r44);
        r25.addAll(r112);
        List<Point> r113 = removeUnnecessaryPoints(new ArrayList(r25));
        List<Point> r29 = removeUnnecessaryPoints(new ArrayList(r35));
        Path r36 = new Path();
        int r45 = 0;
    L106:
        if (r45 >= r29.size()) goto L112;
        Point r53 = r29.get(r45);
        if (r36.isEmpty() == false) goto L110;
        r36.moveTo(((android.graphics.Point) r53).x, ((android.graphics.Point) r53).y);
    L111:
        r45 = r45 + 2;
        goto L106
    L110:
        r36.lineTo(((android.graphics.Point) r53).x, ((android.graphics.Point) r53).y);
        goto L111
    L112:
        Path r210 = new Path();
        int r124 = 0;
    L114:
        if (r124 >= r113.size()) goto L120;
        Point r46 = r113.get(r124);
        if (r210.isEmpty() == false) goto L118;
        r210.moveTo(((android.graphics.Point) r46).x, ((android.graphics.Point) r46).y);
    L119:
        r124 = r124 + 2;
        goto L114
    L118:
        r210.lineTo(((android.graphics.Point) r46).x, ((android.graphics.Point) r46).y);
        goto L119
    L120:
        SegmentedObject.m11594$$Nest$fgetsegmentBorderPath(r26).reset();
        SegmentedObject.m11594$$Nest$fgetsegmentBorderPath(r26).op(r36, r210, Path.Op.INTERSECT);
        float r114 = Math.min(r9 / r212, r11 / r193);
        SegmentedObject.m11596$$Nest$fputborderImageWidth(r26, r212 * r114);
        SegmentedObject.m11595$$Nest$fputborderImageHeight(r26, r193 * r114);
        SegmentedObject.m11594$$Nest$fgetsegmentBorderPath(r26).offset((-SegmentedObject.m11593$$Nest$fgetborderImageWidth(r26)) / r182, (-SegmentedObject.m11592$$Nest$fgetborderImageHeight(r26)) / r182);
        r26.initPoints();
        return;
    L15:
        r33.drawBitmap(r26.getImage(), null, r42, new Paint(3));
        goto L16
    L5:
        r4 = 384.0f;
        goto L6
    }

    private Bitmap createSmoothEdgesSegmentedImage(int r8, int r9, Bitmap r10, boolean r11) {
        Bitmap r7 = getSourceBitmap();
        if (r10 != null) goto L5;
        return null;
    L5:
        if (r10.isRecycled() == true) goto L16;
        if (r7 == null) goto L17;
        Paint r0 = new Paint(3);
        int r2 = r7.getWidth();
        int r3 = r7.getHeight();
        Bitmap.Config r4 = Bitmap.Config.ARGB_8888;
        Bitmap r22 = Bitmap.createBitmap(r2, r3, r4);
        Canvas r32 = new Canvas(r22);
        if (r11 == false) goto L11;
        r32.scale(r22.getWidth() / r10.getWidth(), r22.getHeight() / r10.getHeight());
        r32.drawBitmap(r10, r8, r9, r0);
    L12:
        Utilities.stackBlurBitmap(r22, 5);
        Bitmap r82 = Bitmap.createBitmap(r7.getWidth(), r7.getHeight(), r4);
        Canvas r92 = new Canvas(r82);
        r92.drawBitmap(r7, 0.0f, 0.0f, r0);
        Paint r72 = new Paint(3);
        r72.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        r92.drawBitmap(r22, 0.0f, 0.0f, r72);
        r22.recycle();
        return r82;
    L11:
        r32.drawBitmap(r10, r8, r9, r0);
        goto L12
    L17:
        return null;
    L16:
        return null;
    }

    public static float getStickerCornerRadius(int r1, float r2) {
        if (r1 != 1) goto L5;
        float r12 = 16.0f;
    L10:
        return r2 / r12;
    L5:
        if (r1 != 2) goto L7;
        r12 = 32.0f;
        goto L10
    L7:
        if (r1 == 3) goto L11;
        r12 = 8.0f;
        goto L10
    L11:
        return 0.0f;
    }

    private void hideLoadingDialog() {
        DownloadButton.PreparingVideoToast r0 = this.loadingToast;
        if (r0 == null) goto L6;
        r0.hide();
        this.loadingToast = null;
        return;
    }

    private static boolean isPointOnLine(Point r3, Point r4, Point r5) {
        int r0 = ((android.graphics.Point) r4).x;
        int r1 = ((android.graphics.Point) r3).x;
        int r2 = ((android.graphics.Point) r5).y;
        int r32 = ((android.graphics.Point) r3).y;
        if (Math.abs((((r0 - r1) * (r2 - r32)) - ((((android.graphics.Point) r4).y - r32) * (((android.graphics.Point) r5).x - r1))) - (-1.0f)) >= 0.15f) goto L6;
        return true;
    L6:
        return false;
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

    private /* synthetic */ void lambda$afterUploadingMedia$17(TLObject r6, StickerUploader r7) {
        NotificationCenter.getInstance(UserConfig.selectedAccount).postNotificationNameOnUIThread(NotificationCenter.customStickerCreated, new Object[]{Boolean.FALSE, r6, r7.mediaDocument.document, r7.thumbPath, Boolean.TRUE});
        hideLoadingDialog();
    }

    private /* synthetic */ void lambda$afterUploadingMedia$18(TLObject r8, int r9, StickerUploader r10, TLRPC.TL_error r11) {
        if ((r8 instanceof TLRPC.TL_messages_stickerSet) == false) goto L12;
        TLRPC.TL_messages_stickerSet r112 = (TLRPC.TL_messages_stickerSet) r8;
        MediaDataController.getInstance(r9).putStickerSet(r112);
        if (MediaDataController.getInstance(r9).isStickerPackInstalled(r112.set.id) == true) goto L7;
        TLObject r2 = r8;
        MediaDataController.getInstance(r9).toggleStickerSet(null, r2, 2, null, false, false);
    L8:
        DownloadButton.PreparingVideoToast r82 = this.loadingToast;
        if (r82 == null) goto L11;
        r82.setProgress(1.0f);
    L11:
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda26(this, r2, r10), 450);
        boolean r7 = true;
    L13:
        Utilities.Callback<Boolean> r83 = r10.whenDone;
        if (r83 == null) goto L17;
        r83.run(Boolean.valueOf(r7));
        r10.whenDone = null;
        return;
    L17:
        return;
    L7:
        r2 = r8;
        goto L8
    L12:
        showError(r11);
        hideLoadingDialog();
        r7 = false;
        goto L13
    }

    private /* synthetic */ void lambda$afterUploadingMedia$19(int r7, StickerUploader r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda22(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$afterUploadingMedia$20(TLObject r5, StickerUploader r6) {
        NotificationCenter r0 = NotificationCenter.getInstance(UserConfig.selectedAccount);
        int r1 = NotificationCenter.customStickerCreated;
        Boolean r2 = Boolean.FALSE;
        r0.postNotificationNameOnUIThread(r1, new Object[]{r2, r5, r6.mediaDocument.document, r6.thumbPath, r2});
        hideLoadingDialog();
    }

    private /* synthetic */ void lambda$afterUploadingMedia$21(TLObject r9, int r10, StickerUploader r11, TLRPC.TL_error r12) {
        if ((r9 instanceof TLRPC.TL_messages_stickerSet) == false) goto L8;
        MediaDataController r0 = MediaDataController.getInstance(r10);
        r0.putStickerSet((TLRPC.TL_messages_stickerSet) r9);
        MediaDataController.getInstance(r10).toggleStickerSet(null, r9, 2, null, false, false);
        DownloadButton.PreparingVideoToast r92 = this.loadingToast;
        if (r92 == null) goto L7;
        r92.setProgress(1.0f);
    L7:
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda25(this, r9, r11), 250);
        boolean r8 = true;
    L9:
        Utilities.Callback<Boolean> r93 = r11.whenDone;
        if (r93 == null) goto L13;
        r93.run(Boolean.valueOf(r8));
        r11.whenDone = null;
        return;
    L13:
        return;
    L8:
        showError(r12);
        hideLoadingDialog();
        r8 = false;
        goto L9
    }

    private /* synthetic */ void lambda$afterUploadingMedia$22(int r7, StickerUploader r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda23(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$afterUploadingMedia$24(int r3) {
        NotificationCenter.getInstance(r3).postNotificationName(NotificationCenter.customStickerCreated, new Object[]{Boolean.FALSE});
        hideLoadingDialog();
    }

    private /* synthetic */ void lambda$afterUploadingMedia$25(TLObject r5, StickerUploader r6) {
        NotificationCenter r0 = NotificationCenter.getInstance(UserConfig.selectedAccount);
        int r1 = NotificationCenter.customStickerCreated;
        Boolean r2 = Boolean.FALSE;
        r0.postNotificationNameOnUIThread(r1, new Object[]{r2, r5, r6.mediaDocument.document, r6.thumbPath, r2});
        hideLoadingDialog();
    }

    private /* synthetic */ void lambda$afterUploadingMedia$26(TLObject r8, int r9, StickerUploader r10, TLRPC.TL_error r11) {
        if ((r8 instanceof TLRPC.TL_messages_stickerSet) == false) goto L12;
        TLRPC.TL_messages_stickerSet r112 = (TLRPC.TL_messages_stickerSet) r8;
        MediaDataController.getInstance(r9).putStickerSet(r112);
        if (MediaDataController.getInstance(r9).isStickerPackInstalled(r112.set.id) == true) goto L7;
        TLObject r2 = r8;
        MediaDataController.getInstance(r9).toggleStickerSet(null, r2, 2, null, false, false);
    L8:
        DownloadButton.PreparingVideoToast r82 = this.loadingToast;
        if (r82 == null) goto L11;
        r82.setProgress(1.0f);
    L11:
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda27(this, r2, r10), 450);
        boolean r7 = true;
    L13:
        Utilities.Callback<Boolean> r83 = r10.whenDone;
        if (r83 == null) goto L17;
        r83.run(Boolean.valueOf(r7));
        r10.whenDone = null;
        return;
    L17:
        return;
    L7:
        r2 = r8;
        goto L8
    L12:
        showError(r11);
        hideLoadingDialog();
        r7 = false;
        goto L13
    }

    private /* synthetic */ void lambda$afterUploadingMedia$27(int r7, StickerUploader r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda24(this, r9, r7, r8, r10));
    }

    private /* synthetic */ void lambda$enableClippingMode$1(Utilities.Callback r2, View r3) {
        SegmentedObject[] r32 = this.objects;
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.length != 0) goto L7;
        return;
    L7:
        if (this.sourceBitmap == null) goto L16;
        SegmentedObject r1 = objectBehind(this.tx, this.ty);
        if (r1 == null) goto L15;
        r2.run(r1);
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$enableClippingMode$2(ValueAnimator r1) {
        this.bordersAnimatorValue = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$getThanosEffect$0() {
        ThanosEffect r0 = this.thanosEffect;
        if (r0 == null) goto L6;
        this.thanosEffect = null;
        removeView(r0);
        return;
    }

    private /* synthetic */ void lambda$segment$10(List r5) {
        if (r5.size() > 0) goto L6;
        FileLog.d("objimg: no objects");
        return;
    L6:
        this.detectedEmoji = ObjectDetectionEmojis.labelToEmoji(((ImageLabel) r5.get(0)).getIndex());
        FileLog.d("objimg: detected #" + ((ImageLabel) r5.get(0)).getIndex() + " " + this.detectedEmoji + " " + ((ImageLabel) r5.get(0)).getText());
        Emoji.getEmojiDrawable(this.detectedEmoji);
    }

    private /* synthetic */ void lambda$segment$8(Bitmap r7, int r8, Utilities.Callback r9) {
        segmentImage(r7, r8, this.containerWidth, this.containerHeight, r9);
    }

    private /* synthetic */ void lambda$segment$9(Bitmap r2, int r3, Utilities.Callback r4, Utilities.Callback r5, Exception r6) {
        this.segmentingLoading = false;
        FileLog.e(r6);
        if (isWaitingMlKitError(r6) == true) goto L5;
    L8:
        r5.run(new ArrayList());
        return;
    L5:
        if (isAttachedToWindow() == false) goto L8;
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda21(this, r2, r3, r4), 2000);
    }

    private /* synthetic */ void lambda$segmentImage$3(ArrayList r2, Utilities.Callback r3, SegmentedObject r4) {
        this.empty = true;
        this.objects = (SegmentedObject[]) r2.toArray(new SegmentedObject[0]);
        r3.run(r4);
    }

    private /* synthetic */ void lambda$segmentImage$4(ArrayList r3) {
        this.empty = false;
        SegmentedObject[] r32 = (SegmentedObject[]) r3.toArray(new SegmentedObject[0]);
        this.objects = r32;
        if (r32.length <= 0) goto L6;
        this.stickerCutOutBtn.setScaleX(0.3f);
        this.stickerCutOutBtn.setScaleY(0.3f);
        this.stickerCutOutBtn.setAlpha(0.0f);
        this.stickerCutOutBtn.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(250).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    }

    private /* synthetic */ void lambda$segmentImage$5(int r12, List r13, ArrayList r14, Utilities.Callback r15) {
        if (this.sourceBitmap != null) goto L5;
        return;
    L5:
        if (this.segmentingLoaded == true) goto L33;
        Matrix r0 = new Matrix();
        r0.postScale(1.0f / this.sourceBitmap.getWidth(), 1.0f / this.sourceBitmap.getHeight());
        r0.postTranslate(-0.5f, -0.5f);
        r0.postRotate(r12);
        r0.postTranslate(0.5f, 0.5f);
        int r1 = (r12 / 90) % 2;
        Bitmap r2 = this.sourceBitmap;
        if (r1 == 0) goto L10;
        r0.postScale(r2.getHeight(), this.sourceBitmap.getWidth());
    L12:
        if (r13.isEmpty() == false) goto L19;
        SegmentedObject r132 = new SegmentedObject(this);
        r132.bounds.set(0.0f, 0.0f, this.sourceBitmap.getWidth(), this.sourceBitmap.getHeight());
        r132.rotatedBounds.set(r132.bounds);
        r0.mapRect(r132.rotatedBounds);
        r132.orientation = r12;
        Bitmap r122 = createSmoothEdgesSegmentedImage(0, 0, this.sourceBitmap, false);
        r132.image = r122;
        if (r122 != null) goto L17;
        FileLog.e(new RuntimeException("createSmoothEdgesSegmentedImage failed on empty image"));
        return;
    L17:
        r132.darkMaskImage = r132.makeDarkMaskImage();
        createSegmentImagePath(r132, this.containerWidth, this.containerHeight);
        this.segmentBorderImageWidth = SegmentedObject.m11593$$Nest$fgetborderImageWidth(r132);
        this.segmentBorderImageHeight = SegmentedObject.m11592$$Nest$fgetborderImageHeight(r132);
        r14.add(r132);
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda19(this, r14, r15, r132));
        this.selectedObject = r132;
        this.segmentingLoaded = true;
        this.segmentingLoading = false;
        return;
    L19:
        int r152 = 0;
    L21:
        if (r152 >= r13.size()) goto L27;
        SubjectMock r16 = (SubjectMock) r13.get(r152);
        SegmentedObject r4 = new SegmentedObject(this);
        r4.bounds.set(r16.startX, r16.startY, r6 + r16.width, r8 + r16.height);
        r4.rotatedBounds.set(r4.bounds);
        r0.mapRect(r4.rotatedBounds);
        r4.orientation = r12;
        Bitmap r17 = createSmoothEdgesSegmentedImage(r16.startX, r16.startY, r16.bitmap, false);
        r4.image = r17;
        if (r17 == null) goto L26;
        r4.darkMaskImage = r4.makeDarkMaskImage();
        createSegmentImagePath(r4, this.containerWidth, this.containerHeight);
        this.segmentBorderImageWidth = SegmentedObject.m11593$$Nest$fgetborderImageWidth(r4);
        this.segmentBorderImageHeight = SegmentedObject.m11592$$Nest$fgetborderImageHeight(r4);
        r14.add(r4);
    L26:
        r152 = r152 + 1;
        goto L21
    L27:
        this.selectedObject = null;
        this.segmentingLoaded = true;
        this.segmentingLoading = false;
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda20(this, r14));
        return;
    L10:
        r0.postScale(r2.getWidth(), this.sourceBitmap.getHeight());
        goto L12
    }

    private /* synthetic */ void lambda$segmentImage$6(int r8, Utilities.Callback r9, List r10) {
        ArrayList r4 = new ArrayList();
        Utilities.themeQueue.postRunnable(new StickerMakerView$$ExternalSyntheticLambda11(this, r8, r10, r4, r9));
    }

    private /* synthetic */ void lambda$showLoadingDialog$13() {
        StickerUploader r0 = this.stickerUploader;
        if (r0 != null) goto L5;
    L10:
        this.loadingToast.hide();
        this.loadingToast = null;
        return;
    L5:
        if (r0.messageObject == null) goto L9;
        MediaController.getInstance().cancelVideoConvert(this.stickerUploader.messageObject);
        FileLoader.getInstance(this.currentAccount).cancelFileUpload(this.stickerUploader.finalPath, false);
        if (this.stickerUploader.reqId == 0) goto L9;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.stickerUploader.reqId, true);
    L9:
        this.stickerUploader.destroy(true);
        this.stickerUploader = null;
        goto L10
    }

    private /* synthetic */ void lambda$uploadMedia$14(TLObject r2, StickerUploader r3, TLRPC.TL_error r4) {
        if ((r2 instanceof TLRPC.TL_messageMediaDocument) == false) goto L6;
        TLRPC.TL_messageMediaDocument r22 = (TLRPC.TL_messageMediaDocument) r2;
        r3.tlInputStickerSetItem = MediaDataController.getInputStickerSetItem(r22.document, r3.emoji);
        r3.mediaDocument = r22;
        afterUploadingMedia();
        return;
    L6:
        hideLoadingDialog();
        showError(r4);
    }

    private /* synthetic */ void lambda$uploadMedia$15(StickerUploader r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda12(this, r3, r2, r4));
    }

    private /* synthetic */ void lambda$uploadStickerFile$12(Utilities.Callback r7, String r8, String r9, CharSequence r10, boolean r11, long r12, TLRPC.StickerSet r14, TLRPC.Document r15, TLRPC.Document r16, VideoEditedInfo r17, String r18, Utilities.Callback2 r19) {
        if (r7 == null) goto L10;
        StickerUploader r4 = this.stickerUploader;
        if (r4 == null) goto L10;
        if (r4.uploaded == false) goto L10;
        boolean r42 = false;
    L11:
        if (r42 == false) goto L16;
        StickerUploader r5 = this.stickerUploader;
        if (r5 == null) goto L15;
        r5.destroy(true);
    L15:
        this.stickerUploader = new StickerUploader();
    L16:
        StickerUploader r52 = this.stickerUploader;
        r52.emoji = r8;
        r52.finalPath = r9;
        r52.path = r9;
        r52.stickerPackName = r10;
        r52.addToFavorite = r11;
        r52.sendToDialogId = r12;
        r52.stickerSet = r14;
        r52.replacedSticker = r15;
        r52.uploadedSticker = r16;
        r52.videoEditedInfo = r17;
        r52.thumbPath = r18;
        r52.whenDone = r7;
        r52.customHandler = r19;
        r52.setupFiles();
        if (r42 == true) goto L19;
        afterUploadingMedia();
    L24:
        if (r7 != null) goto L27;
        showLoadingDialog();
        return;
    L27:
        return;
    L19:
        if (r16 == null) goto L21;
        StickerUploader r82 = this.stickerUploader;
        r82.tlInputStickerSetItem = MediaDataController.getInputStickerSetItem(r16, r82.emoji);
        this.stickerUploader.mediaDocument = new TLRPC.TL_messageMediaDocument();
        TLRPC.TL_messageMediaDocument r83 = this.stickerUploader.mediaDocument;
        r83.flags |= 1;
        r83.document = r16;
        afterUploadingMedia();
        goto L24
    L21:
        if (r17 == null) goto L23;
        TLRPC.TL_message r84 = new TLRPC.TL_message();
        r84.id = 1;
        StickerUploader r92 = this.stickerUploader;
        String r102 = StoryEntry.makeCacheFile(UserConfig.selectedAccount, "webm").getAbsolutePath();
        r84.attachPath = r102;
        r92.finalPath = r102;
        this.stickerUploader.messageObject = new MessageObject(UserConfig.selectedAccount, r84, null, false, false);
        this.stickerUploader.messageObject.videoEditedInfo = r17;
        MediaController.getInstance().scheduleVideoConvert(this.stickerUploader.messageObject, false, false, false);
        goto L24
    L23:
        FileLoader.getInstance(this.currentAccount).uploadFile(r9, false, true, 67108864);
    L10:
        r42 = true;
        goto L11
    }

    public static List<Point> removeUnnecessaryPoints(List<Point> r6) {
        if (r6.size() >= 3) goto L5;
        return r6;
    L5:
        ArrayList r0 = new ArrayList();
        r0.add(r6.get(0));
        int r2 = 1;
    L7:
        if (r2 >= (r6.size() - 1)) goto L11;
        Point r3 = r6.get(r2 - 1);
        Point r4 = r6.get(r2);
        r2 = r2 + 1;
        if (isPointOnLine(r3, r4, r6.get(r2)) == true) goto L7;
        r0.add(r4);
        goto L7
    L11:
        r0.add(r6.get(r6.size() - 1));
        return r0;
    }

    private void segment(Bitmap r9, int r10, Utilities.Callback<List<SubjectMock>> r11, Utilities.Callback<SegmentedObject> r12) {
        this.segmentingLoading = true;
        SubjectSegmenter r0 = SubjectSegmentation.getClient(new SubjectSegmenterOptions.Builder().enableMultipleSubjects(new SubjectSegmenterOptions.SubjectResultOptions.Builder().enableSubjectBitmap().build()).build());
        if (EmuDetector.with(getContext()).detect() == false) goto L6;
        ArrayList r92 = new ArrayList();
        r92.add(SubjectMock.mock(this.sourceBitmap));
        r11.run(r92);
        return;
    L6:
        InputImage r1 = InputImage.fromBitmap(r9, r10);
        r0.process(r1).addOnSuccessListener(new StickerMakerView$$ExternalSyntheticLambda6(r11)).addOnFailureListener(new StickerMakerView$$ExternalSyntheticLambda7(this, r9, r10, r12, r11));
        if (this.detectedEmoji != null) goto L9;
        ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS).process(r1).addOnSuccessListener(new StickerMakerView$$ExternalSyntheticLambda8(this)).addOnFailureListener(new StickerMakerView$$ExternalSyntheticLambda9());
    L9:
        List<TLRPC.TL_availableReaction> r8 = MediaDataController.getInstance(this.currentAccount).getEnabledReactionsList();
        int r93 = 0;
    L11:
        if (r93 >= Math.min(r8.size(), 9)) goto L13;
        Emoji.getEmojiDrawable(r8.get(r93).reaction);
        r93 = r93 + 1;
        goto L11
    }

    private void showError(TLRPC.TL_error r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if ("PACK_TITLE_INVALID".equals(r3.text) == true) goto L9;
        BulletinFactory.of((FrameLayout) getParent(), this.resourcesProvider).createErrorBulletin(r3.text).show();
        return;
    }

    private void showLoadingDialog() {
        if (this.loadingToast != null) goto L5;
        this.loadingToast = new DownloadButton.PreparingVideoToast(getContext(), LocaleController.getString(R.string.PreparingSticker));
    L5:
        this.loadingToast.setOnCancelListener(new StickerMakerView$$ExternalSyntheticLambda10(this));
        if (this.loadingToast.getParent() != null) goto L8;
        addView(this.loadingToast, LayoutHelper.createFrame(-1, -1, 17));
    L8:
        this.loadingToast.show();
    }

    private void updateStickerAreaPath() {
        float r0 = AndroidUtilities.dp(10.0f);
        float r3 = r0 * 2.0f;
        float r1 = getMeasuredWidth() - r3;
        float r4 = getMeasuredHeight() - r3;
        if (r1 > 0.0f) goto L5;
        return;
    L5:
        if (r4 <= 0.0f) goto L10;
        float r5 = getStickerCornerRadius(r1);
        RectF r6 = AndroidUtilities.rectTmp;
        float r12 = r1 + r0;
        r6.set(r0, r0, r12, r12);
        r6.offset(0.0f, (r4 - r6.height()) / 2.0f);
        this.areaPath.rewind();
        Path r02 = this.areaPath;
        Path.Direction r122 = Path.Direction.CW;
        r02.addRoundRect(r6, r5, r5, r122);
        this.bgPath.rewind();
        this.bgPath.addRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), r122);
        this.screenPath.reset();
        this.screenPath.op(this.bgPath, this.areaPath, Path.Op.DIFFERENCE);
        this.dashPath.rewind();
        r6.inset(AndroidUtilities.dp(-1.0f), AndroidUtilities.dp(-1.0f));
        this.dashPath.addRoundRect(r6, r5, r5, r122);
        return;
    }

    private void uploadMedia() {
        StickerUploader r0 = this.stickerUploader;
        if (r0 != null) goto L5;
        return;
    L5:
        TLRPC.TL_messages_uploadMedia r1 = new TLRPC.TL_messages_uploadMedia();
        r1.peer = new TLRPC.TL_inputPeerSelf();
        TLRPC.TL_inputMediaUploadedDocument r2 = new TLRPC.TL_inputMediaUploadedDocument();
        r1.media = r2;
        r2.file = r0.file;
        if (r0.videoEditedInfo == null) goto L8;
        r2.mime_type = "video/webm";
    L9:
        TLRPC.TL_documentAttributeSticker r22 = new TLRPC.TL_documentAttributeSticker();
        r22.alt = r0.emoji;
        r22.stickerset = new TLRPC.TL_inputStickerSetEmpty();
        r1.media.attributes.add(r22);
        ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(r1, new StickerMakerView$$ExternalSyntheticLambda4(this, r0), 2);
        return;
    L8:
        r2.mime_type = "image/webp";
        goto L9
    }

    public void clean() {
        ValueAnimator r0 = this.bordersAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.bordersAnimator = null;
    L5:
        this.sourceBitmap = null;
        if (this.objects == null) goto L15;
        int r02 = 0;
    L8:
        SegmentedObject[] r3 = this.objects;
        if (r02 >= r3.length) goto L14;
        SegmentedObject r32 = r3[r02];
        if (r32 == null) goto L13;
        r32.recycle();
    L13:
        r02 = r02 + 1;
        goto L8
    L14:
        this.objects = null;
    L15:
        this.segmentingLoaded = false;
        this.segmentingLoading = false;
        this.isSegmentedState = false;
        this.actionTextView.setAlpha(0.0f);
        this.actionTextView.setScaleX(0.3f);
        this.actionTextView.setScaleY(0.3f);
        StickerUploader r03 = this.stickerUploader;
        if (r03 != null) goto L18;
    L21:
        hideLoadingDialog();
        this.isThanosInProgress = false;
        return;
    L18:
        if (r03.uploaded == true) goto L20;
        r03.destroy(true);
    L20:
        this.stickerUploader = null;
        goto L21
    }

    public Bitmap cutSegmentInFilteredBitmap(Bitmap r8, int r9) {
        if (r8 != null) goto L5;
        return null;
    L5:
        SegmentedObject r0 = this.selectedObject;
        if (r0 == null) goto L22;
        this.filteredBitmap = r8;
        if (r0.darkMaskImage == null) goto L22;
        if (this.isSegmentedState == false) goto L22;
        Bitmap r02 = Bitmap.createBitmap(r8.getWidth(), r8.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r1 = new Canvas(r02);
        Paint r2 = new Paint(3);
        Paint r4 = new Paint(3);
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r1.drawBitmap(r8, 0.0f, 0.0f, r4);
        Rect r3 = new Rect();
        r3.set(0, 0, r8.getWidth(), r8.getHeight());
        SegmentedObject r42 = this.selectedObject;
        if (r42.orientation == 0) goto L20;
        Matrix r92 = new Matrix();
        SegmentedObject r32 = this.selectedObject;
        r92.postRotate(r32.orientation, r32.getDarkMaskImage().getWidth() / 2.0f, this.selectedObject.getDarkMaskImage().getHeight() / 2.0f);
        if (((this.selectedObject.orientation / 90) % 2) == 0) goto L18;
        float r33 = (r3.getImage().getHeight() - this.selectedObject.getImage().getWidth()) / 2.0f;
        r92.postTranslate(r33, -r33);
    L18:
        r92.postScale(r8.getWidth() / this.selectedObject.getDarkMaskImage().getHeight(), r8.getHeight() / this.selectedObject.getDarkMaskImage().getWidth());
        r1.drawBitmap(this.selectedObject.getDarkMaskImage(), r92, r2);
        return r02;
    L20:
        r1.drawBitmap(r42.getDarkMaskImage(), null, r3, r2);
        return r02;
    L22:
        return r8;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r12, int r13, Object... r14) {
        if (r12 != NotificationCenter.fileUploaded) goto L11;
        String r122 = (String) r14[0];
        TLRPC.InputFile r132 = (TLRPC.InputFile) r14[1];
        StickerUploader r142 = this.stickerUploader;
        if (r142 != null) goto L7;
        return;
    L7:
        if (r122.equalsIgnoreCase(r142.finalPath) == false) goto L60;
        this.stickerUploader.file = r132;
        uploadMedia();
        return;
    L60:
        return;
    L11:
        if (r12 != NotificationCenter.fileUploadProgressChanged) goto L23;
        String r123 = (String) r14[0];
        StickerUploader r133 = this.stickerUploader;
        if (r133 != null) goto L15;
        return;
    L15:
        if (r123.equalsIgnoreCase(r133.finalPath) == false) goto L62;
        long r124 = ((Long) r14[1]).longValue();
        long r0 = ((Long) r14[2]).longValue();
        if (r0 <= 0) goto L63;
        StickerUploader r143 = this.stickerUploader;
        StickerUploader.m11600$$Nest$fputuploadProgress(r143, Utilities.clamp(r124 / r0, 1.0f, StickerUploader.m11598$$Nest$fgetuploadProgress(r143)));
        DownloadButton.PreparingVideoToast r125 = this.loadingToast;
        if (r125 == null) goto L64;
        r125.setProgress(this.stickerUploader.getProgress());
        return;
    L64:
        return;
    L63:
        return;
    L62:
        return;
    L23:
        if (r12 != NotificationCenter.fileUploadFailed) goto L31;
        String r126 = (String) r14[0];
        StickerUploader r134 = this.stickerUploader;
        if (r134 != null) goto L27;
        return;
    L27:
        if (r126.equalsIgnoreCase(r134.finalPath) == false) goto L66;
        hideLoadingDialog();
        return;
    L66:
        return;
    L31:
        if (r12 != NotificationCenter.filePreparingStarted) goto L40;
        StickerUploader r127 = this.stickerUploader;
        if (r127 != null) goto L36;
        return;
    L36:
        if (r14[0] != r127.messageObject) goto L67;
        FileLoader.getInstance(UserConfig.selectedAccount).uploadFile(this.stickerUploader.finalPath, false, true, 67108864);
        return;
    L67:
        return;
    L40:
        if (r12 != NotificationCenter.fileNewChunkAvailable) goto L51;
        StickerUploader r128 = this.stickerUploader;
        if (r128 != null) goto L45;
        return;
    L45:
        if (r14[0] != r128.messageObject) goto L68;
        String r4 = (String) r14[1];
        long r129 = ((Long) r14[2]).longValue();
        long r8 = ((Long) r14[3]).longValue();
        Float r10 = (Float) r14[4];
        float r144 = r10.floatValue();
        this.stickerUploader.messageObject.videoEditedInfo.needUpdateProgress = true;
        FileLoader.getInstance(this.currentAccount).checkUploadNewDataAvailable(r4, false, Math.max(1, r129), r8, r10);
        StickerUploader r1210 = this.stickerUploader;
        StickerUploader.m11599$$Nest$fputconvertingProgress(r1210, Math.max(StickerUploader.m11597$$Nest$fgetconvertingProgress(r1210), r144));
        DownloadButton.PreparingVideoToast r1211 = this.loadingToast;
        if (r1211 == null) goto L69;
        r1211.setProgress(this.stickerUploader.getProgress());
        return;
    L69:
        return;
    L68:
        return;
    L51:
        if (r12 != NotificationCenter.filePreparingFailed) goto L70;
        StickerUploader r1212 = this.stickerUploader;
        if (r1212 != null) goto L56;
        return;
    L56:
        if (r14[0] != r1212.messageObject) goto L71;
        hideLoadingDialog();
        return;
    L71:
        return;
    }

    public void disableClippingMode() {
        this.segmentBorderAlpha.set(0.0f);
        ValueAnimator r0 = this.bordersAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.bordersAnimator = null;
    L5:
        setOnClickListener(null);
        setClickable(false);
        this.actionTextView.animate().cancel();
        this.actionTextView.animate().alpha(0.0f).scaleX(0.7f).scaleY(0.7f).setDuration(240).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r3) {
        super.dispatchDraw(r3);
        r3.drawPath(this.screenPath, this.bgPaint);
        r3.drawPath(this.dashPath, this.dashPaint);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r6) {
        this.tx = r6.getX();
        float r0 = r6.getY();
        this.ty = r0;
        if (this.objects == null) goto L26;
        if (this.bordersAnimator == null) goto L26;
        SegmentedObject r02 = objectBehind(this.tx, r0);
        int r2 = 0;
    L7:
        SegmentedObject[] r3 = this.objects;
        if (r2 >= r3.length) goto L23;
        if (r3[r2] == r02) goto L12;
    L16:
        boolean r4 = false;
    L17:
        if (r4 == false) goto L21;
        if (this.objects[r2].hover == true) goto L21;
        AndroidUtilities.vibrateCursor(this);
    L21:
        this.objects[r2].hover = r4;
        r2 = r2 + 1;
        goto L7
    L12:
        if (r6.getAction() == 3) goto L16;
        r4 = true;
        if (r6.getAction() == 1) goto L16;
    L23:
        if ((getParent() instanceof View) == false) goto L26;
        ((View) getParent()).invalidate();
    L26:
        return super.dispatchTouchEvent(r6);
    }

    public void drawOutline(Canvas r7, boolean r8, ViewGroup r9, boolean r10) {
        this.outlineAlpha.setParent(r9);
        if (this.outlineVisible == false) goto L5;
    L7:
        int r0 = 0;
        if (r9 != null) goto L10;
        float r92 = 1.0f;
    L16:
        SegmentedObject[] r102 = this.objects;
        if (r102 == null) goto L29;
        int r2 = r102.length;
    L19:
        if (r0 >= r2) goto L36;
        SegmentedObject r3 = r102[r0];
        if (r3 == null) goto L28;
        if (r3 != this.selectedObject) goto L28;
        float r4 = this.outlineWidth;
        if (r4 <= 0.0f) goto L28;
        r3.drawOutline(r7, r8, r4, r92);
        return;
    L28:
        r0 = r0 + 1;
        goto L19
    L36:
        return;
    L29:
        return;
    L10:
        AnimatedFloat r93 = this.outlineAlpha;
        if (this.outlineVisible == false) goto L14;
        if (r10 == true) goto L14;
        boolean r103 = true;
    L15:
        r92 = r93.set(r103);
    L14:
        r103 = false;
        goto L15
    L5:
        if (this.outlineAlpha.get() > 0.0f) goto L7;
    }

    public void drawSegmentBorderPath(Canvas r4, ImageReceiver r5, Matrix r6, ViewGroup r7) {
        this.segmentBorderAlpha.setParent(r7);
        if (this.bordersAnimator == null) goto L5;
    L6:
        if (r7 == null) goto L26;
        this.imageReceiverWidth = r5.getImageWidth();
        this.imageReceiverHeight = r5.getImageHeight();
        this.imageReceiverMatrix.set(r6);
        float r52 = (this.bordersAnimatorValueStart + this.bordersAnimatorValue) % 1.0f;
        AnimatedFloat r62 = this.segmentBorderAlpha;
        int r1 = 0;
        if (this.bordersAnimator == null) goto L11;
        boolean r0 = true;
    L12:
        float r63 = r62.set(r0);
        r4.drawColor(Theme.multAlpha(1342177280, r63));
        SegmentedObject[] r3 = this.objects;
        if (r3 == null) goto L21;
        int r02 = r3.length;
    L15:
        if (r1 >= r02) goto L21;
        SegmentedObject r2 = r3[r1];
        if (r2 == null) goto L20;
        r2.drawAnimationBorders(r4, r52, r63, r7);
    L20:
        r1 = r1 + 1;
    L21:
        r7.invalidate();
        return;
    L11:
        r0 = false;
        goto L12
    L26:
        return;
    L5:
        if (this.segmentBorderAlpha.get() > 0.0f) goto L6;
    }

    public void enableClippingMode(Utilities.Callback<SegmentedObject> r3) {
        setOnClickListener(new StickerMakerView$$ExternalSyntheticLambda2(this, r3));
        this.actionTextView.setText(LocaleController.getString(R.string.SegmentationTabToCrop));
        this.actionTextView.animate().cancel();
        this.actionTextView.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(240).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        ValueAnimator r32 = this.bordersAnimator;
        if (r32 == null) goto L5;
        r32.cancel();
    L5:
        this.bordersAnimatorValueStart = this.bordersAnimatorValue;
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.bordersAnimator = r33;
        r33.addUpdateListener(new StickerMakerView$$ExternalSyntheticLambda3(this));
        this.bordersAnimator.setRepeatCount(-1);
        this.bordersAnimator.setRepeatMode(1);
        this.bordersAnimator.setDuration(2400);
        this.bordersAnimator.setInterpolator(new LinearInterpolator());
        this.bordersAnimator.start();
    }

    public float getSegmentBorderImageHeight() {
        return this.segmentBorderImageHeight;
    }

    public float getSegmentBorderImageWidth() {
        return this.segmentBorderImageWidth;
    }

    public Bitmap getSegmentedDarkMaskImage() {
        if (this.isSegmentedState == false) goto L8;
        SegmentedObject r1 = this.selectedObject;
        if (r1 != null) goto L7;
        return null;
    L7:
        return r1.getDarkMaskImage();
    L8:
        return null;
    }

    public Bitmap getSegmentedImage(Bitmap r2, boolean r3, int r4) {
        SegmentedObject r0 = this.selectedObject;
        if (r0 == null) goto L5;
        if (r3 == false) goto L11;
        if (r2 == null) goto L11;
        return cutSegmentInFilteredBitmap(r2, r4);
    L11:
        return r0.getImage();
    L5:
        return this.sourceBitmap;
    }

    public Bitmap getSourceBitmap() {
        return this.sourceBitmap;
    }

    public Bitmap getSourceBitmap(boolean r1) {
        if (r1 == false) goto L7;
        Bitmap r12 = this.filteredBitmap;
        if (r12 == null) goto L7;
        return r12;
    L7:
        return this.sourceBitmap;
    }

    public float getStickerCornerRadius(float r1) {
        return getStickerCornerRadius(this.stickerCornerRoundness, r1);
    }

    public int getStickerCornerRoundness() {
        return this.stickerCornerRoundness;
    }

    public ThanosEffect getThanosEffect() {
        if (ThanosEffect.supports() == true) goto L7;
        return null;
    L7:
        if (this.thanosEffect != null) goto L10;
        ThanosEffect r0 = new ThanosEffect(getContext(), new StickerMakerView$$ExternalSyntheticLambda0(this));
        this.thanosEffect = r0;
        addView(r0, LayoutHelper.createFrame(-1, -1.0f));
    L10:
        return this.thanosEffect;
    }

    public Bitmap getThanosImage(MediaController.PhotoEntry r12, int r13) {
        String r132 = r12.filterPath;
        if (r132 == null) goto L5;
        Bitmap r133 = BitmapFactory.decodeFile(r132);
    L6:
        Bitmap r0 = BitmapFactory.decodeFile(r12.paintPath);
        Bitmap r1 = Bitmap.createBitmap(r133.getWidth(), r133.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r2 = new Canvas(r1);
        Paint r3 = new Paint(3);
        Paint r5 = new Paint(3);
        r3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        r2.drawBitmap(r133, 0.0f, 0.0f, r5);
        Rect r4 = new Rect();
        r4.set(0, 0, r133.getWidth(), r133.getHeight());
        SegmentedObject r52 = this.selectedObject;
        if (r52 != null) goto L12;
        SegmentedObject[] r11 = this.objects;
        if (r11.length <= 0) goto L12;
        r52 = r11[0];
    L12:
        if (r52 != null) goto L15;
        return null;
    L15:
        if (r52.orientation != 0) goto L17;
    L22:
        r2.drawBitmap(r52.getDarkMaskImage(), null, r4, r3);
    L23:
        if (r0 == null) goto L34;
        r3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        if (r52.orientation != 0) goto L27;
    L33:
        r2.drawBitmap(r0, null, r4, r3);
        goto L34
    L27:
        if (r12.isFiltered == true) goto L33;
        Matrix r112 = new Matrix();
        r112.postRotate(-r52.orientation, r0.getWidth() / 2.0f, r0.getHeight() / 2.0f);
        if (((r52.orientation / 90) % 2) == 0) goto L31;
        float r122 = (r0.getHeight() - r0.getWidth()) / 2.0f;
        r112.postTranslate(r122, -r122);
    L31:
        r112.postScale(r133.getWidth() / r0.getHeight(), r133.getHeight() / r0.getWidth());
        r2.drawBitmap(r0, r112, r3);
        return r1;
    L34:
        return r1;
    L17:
        if (r12.isFiltered == false) goto L22;
        Matrix r6 = new Matrix();
        r6.postRotate(r52.orientation, r52.getDarkMaskImage().getWidth() / 2.0f, r52.getDarkMaskImage().getHeight() / 2.0f);
        if (((r52.orientation / 90) % 2) == 0) goto L21;
        float r8 = (r52.getDarkMaskImage().getHeight() - r52.getDarkMaskImage().getWidth()) / 2.0f;
        r6.postTranslate(r8, -r8);
    L21:
        r6.postScale(r133.getWidth() / r52.getDarkMaskImage().getHeight(), r133.getHeight() / r52.getDarkMaskImage().getWidth());
        r2.drawBitmap(r52.getDarkMaskImage(), r6, r3);
        goto L23
    L5:
        r133 = getSourceBitmap();
        goto L6
    }

    public boolean hasSegmentedBitmap() {
        if (this.segmentingLoaded == false) goto L10;
        SegmentedObject[] r1 = this.objects;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.length <= 0) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public boolean isSegmentedState() {
        return this.isSegmentedState;
    }

    public SegmentedObject objectBehind(float r13, float r14) {
        if (this.sourceBitmap != null) goto L5;
        return null;
    L5:
        int r0 = 0;
    L6:
        SegmentedObject[] r2 = this.objects;
        if (r0 >= r2.length) goto L19;
        SegmentedObject r22 = r2[r0];
        if (r22 == null) goto L18;
        int r3 = (r22.orientation / 90) % 2;
        Bitmap r4 = this.sourceBitmap;
        if (r3 == 0) goto L14;
        int r32 = r4.getHeight();
        int r42 = this.sourceBitmap.getWidth();
    L15:
        RectF r5 = AndroidUtilities.rectTmp;
        RectF r6 = this.objects[r0].rotatedBounds;
        float r33 = r32;
        float r7 = r6.left / r33;
        float r8 = this.imageReceiverWidth;
        float r43 = r42;
        float r9 = r6.top / r43;
        float r10 = this.imageReceiverHeight;
        r5.set(r7 * r8, r9 * r10, (r6.right / r33) * r8, (r6.bottom / r43) * r10);
        this.imageReceiverMatrix.mapRect(r5);
        if (r5.contains(r13, r14) == false) goto L18;
        return r22;
    L14:
        r32 = r4.getWidth();
        r42 = this.sourceBitmap.getHeight();
    L18:
        r0 = r0 + 1;
        goto L6
    L19:
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingFailed);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingStarted);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileNewChunkAvailable);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = this.currentAccount;
        if (r0 < 0) goto L6;
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingStarted);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileNewChunkAvailable);
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateStickerAreaPath();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        super.onMeasure(r4, r5);
        this.actionTextView.setTranslationY(-((getMeasuredWidth() / 2.0f) + AndroidUtilities.dp(10.0f)));
        if (Build.VERSION.SDK_INT < 29) goto L9;
        this.exclusionRects.clear();
        if (this.outlineVisible == false) goto L7;
        this.exclusionRects.add(this.exclusionRect);
        int r42 = (int) (getMeasuredHeight() * 0.3f);
        this.exclusionRect.set(0, (getMeasuredHeight() - r42) / 2, AndroidUtilities.dp(20.0f), (getMeasuredHeight() + r42) / 2);
    L7:
        setSystemGestureExclusionRects(this.exclusionRects);
        return;
    }

    public boolean overriddenPaths() {
        SegmentedObject[] r4 = this.objects;
        if (r4 == null) goto L13;
        int r1 = r4.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L13;
        SegmentedObject r3 = r4[r2];
        if (r3 == null) goto L12;
        if (r3.overrideImage == null) goto L12;
        return true;
    L12:
        r2 = r2 + 1;
    L13:
        return false;
    }

    public void resetPaths() {
        SegmentedObject[] r0 = this.objects;
        if (r0 == null) goto L15;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L20;
        SegmentedObject r3 = r0[r2];
        if (r3 == null) goto L14;
        Bitmap r4 = r3.overrideImage;
        if (r4 == null) goto L14;
        r4.recycle();
        r3.overrideImage = null;
        Bitmap r5 = r3.overrideDarkMaskImage;
        if (r5 == null) goto L13;
        r5.recycle();
        r3.overrideDarkMaskImage = null;
    L13:
        createSegmentImagePath(r3, this.containerWidth, this.containerHeight);
    L14:
        r2 = r2 + 1;
        goto L5
    L20:
        return;
    }

    public void segmentImage(Bitmap r1, int r2, int r3, int r4, Utilities.Callback<SegmentedObject> r5) {
        if (r3 > 0) goto L4;
        r3 = AndroidUtilities.displaySize.x;
    L4:
        if (r4 > 0) goto L6;
        r4 = AndroidUtilities.displaySize.y;
    L6:
        this.containerWidth = r3;
        this.containerHeight = r4;
        if (this.segmentingLoaded == false) goto L10;
        return;
    L10:
        if (this.segmentingLoading == true) goto L15;
        if (r1 == null) goto L17;
        this.sourceBitmap = r1;
        this.orientation = r2;
        this.detectedEmoji = null;
        segment(r1, r2, new StickerMakerView$$ExternalSyntheticLambda1(this, r2, r5), r5);
        return;
    L17:
        return;
    }

    public void setCurrentAccount(int r3) {
        int r0 = this.currentAccount;
        if (r0 == r3) goto L14;
        if (r0 >= 0) goto L6;
    L8:
        this.currentAccount = r3;
        if (r3 >= 0) goto L11;
        return;
    L11:
        if (isAttachedToWindow() == false) goto L16;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingFailed);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.filePreparingStarted);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileNewChunkAvailable);
        return;
    L16:
        return;
    L6:
        if (isAttachedToWindow() == false) goto L8;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileUploadFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingFailed);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.filePreparingStarted);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileNewChunkAvailable);
        goto L8
    }

    public void setOutlineVisible(boolean r5) {
        if (this.outlineVisible == r5) goto L24;
        this.outlineVisible = r5;
        ViewPropertyAnimator r0 = this.weightChooserView.animate();
        float r1 = 0.0f;
        if (r5 == false) goto L8;
        float r2 = 1.0f;
    L9:
        ViewPropertyAnimator r02 = r0.alpha(r2);
        if (r5 == true) goto L13;
        r1 = AndroidUtilities.dp(-18.0f);
    L13:
        r02.translationX(r1).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).start();
        if ((getParent() instanceof View) == false) goto L17;
        ((View) getParent()).invalidate();
    L17:
        if (Build.VERSION.SDK_INT < 29) goto L23;
        this.exclusionRects.clear();
        if (this.outlineVisible == false) goto L21;
        this.exclusionRects.add(this.exclusionRect);
        int r52 = (int) (getMeasuredHeight() * 0.3f);
        this.exclusionRect.set(0, (getMeasuredHeight() - r52) / 2, AndroidUtilities.dp(20.0f), (getMeasuredHeight() + r52) / 2);
    L21:
        setSystemGestureExclusionRects(this.exclusionRects);
        return;
    L23:
        return;
    L8:
        r2 = 0.0f;
        goto L9
    }

    public void setOutlineWidth(float r1) {
        this.outlineWidth = r1;
        if ((getParent() instanceof View) == false) goto L6;
        ((View) getParent()).invalidate();
        return;
    }

    public void setSegmentedState(boolean r1, SegmentedObject r2) {
        this.isSegmentedState = r1;
        this.selectedObject = r2;
    }

    public void setStickerCornerRoundness(int r3) {
        if (this.stickerCornerRoundness == r3) goto L10;
        this.stickerCornerRoundness = r3;
        ExteraConfig.getEditor().putInt("stickerCornerRoundness", this.stickerCornerRoundness).apply();
        updateStickerAreaPath();
        updateOutlineBounds(this.setOutlineBounds);
        invalidate();
        if ((getParent() instanceof View) == false) goto L9;
        ((View) getParent()).invalidate();
        return;
    L9:
        return;
    }

    public void setStickerCutOutBtn(StickerCutOutBtn r1) {
        this.stickerCutOutBtn = r1;
    }

    public void updateOutlineBounds(boolean r4) {
        this.setOutlineBounds = r4;
        if (r4 == false) goto L14;
        Path r42 = this.outlineBoundsPath;
        if (r42 != null) goto L7;
        this.outlineBoundsPath = new Path();
    L8:
        Path r43 = this.outlineBoundsInnerPath;
        if (r43 != null) goto L11;
        this.outlineBoundsInnerPath = new Path();
    L12:
        RectF r44 = AndroidUtilities.rectTmp;
        r44.set(0.0f, 0.0f, 1.0f, 1.0f);
        float r0 = getStickerCornerRadius(1.0f);
        this.outlineBoundsInnerPath.addRoundRect(r44, r0, r0, Path.Direction.CW);
        this.outlineBoundsPath.addPath(this.outlineBoundsInnerPath, this.outlineMatrix);
        this.outlineBoundsPath.computeBounds(this.outlineBounds, true);
        return;
    L11:
        r43.rewind();
        goto L12
    L7:
        r42.rewind();
        goto L8
    }

    public void updateOutlinePath(Bitmap r4) {
        SegmentedObject r0 = this.selectedObject;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.overrideImage = createSmoothEdgesSegmentedImage(0, 0, r4, true);
        SegmentedObject r42 = this.selectedObject;
        r42.overrideDarkMaskImage = r42.makeDarkMaskImage();
        createSegmentImagePath(this.selectedObject, this.containerWidth, this.containerHeight);
    }

    public void uploadStickerFile(String r16, VideoEditedInfo r17, String r18, CharSequence r19, boolean r20, long r21, TLRPC.StickerSet r23, TLRPC.Document r24, TLRPC.Document r25, String r26, Utilities.Callback<Boolean> r27, Utilities.Callback2<String, TLRPC.InputDocument> r28) {
        AndroidUtilities.runOnUIThread(new StickerMakerView$$ExternalSyntheticLambda5(this, r27, r18, r16, r19, r20, r21, r23, r24, r25, r17, r26, r28), 300);
    }
}
