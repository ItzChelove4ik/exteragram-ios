package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MrzRecognizer;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.camera.CameraController;
import org.telegram.messenger.camera.CameraSessionWrapper;
import org.telegram.messenger.camera.CameraView;
import org.telegram.messenger.camera.Size;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.URLSpanNoUnderline;

/* loaded from: classes6.dex */
public class CameraScanActivity extends BaseFragment {
    private float averageProcessTime;
    private float backShadowAlpha;
    private HandlerThread backgroundHandlerThread;
    private final RectF bounds;
    private final long boundsUpdateDuration;
    private CameraView cameraView;
    private Paint cornerPaint;
    private int currentType;
    private CameraScanActivityDelegate delegate;
    private TextView descriptionText;
    private AnimatorSet flashAnimator;
    private ImageView flashButton;
    private final RectF fromBounds;
    private final PointF[] fromPoints;
    private ImageView galleryButton;
    private ChatAttachAlert galleryPickerAlert;
    private Handler handler;
    private long lastBoundsUpdate;
    private boolean needGalleryButton;
    private float newRecognizedT;
    private RectF normalBounds;
    private Paint paint;
    private Path path;
    private final PointF[] points;
    private long processTimesCount;
    private SpringAnimation qrAppearing;
    private float qrAppearingValue;
    private boolean qrLoaded;
    private boolean qrLoading;
    private QRCodeReader qrReader;
    private int recognizeFailed;
    private int recognizeIndex;
    private boolean recognized;
    private ValueAnimator recognizedAnimator;
    private TextView recognizedMrzView;
    private long recognizedStart;
    private float recognizedT;
    private String recognizedText;
    private final Runnable requestShot;
    protected boolean shownAsBottomSheet;
    private int sps;
    private TextView titleTextView;
    private final PointF[] tmp2Points;
    private final PointF[] tmpPoints;
    private float useRecognizedBounds;
    private SpringAnimation useRecognizedBoundsAnimator;
    private BarcodeDetector visionQrReader;

    /* renamed from: org.telegram.ui.CameraScanActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends BottomSheet {
        CameraScanActivity fragment;
        final /* synthetic */ INavigationLayout[] val$actionBarLayout;
        final /* synthetic */ CameraScanActivityDelegate val$cameraDelegate;
        final /* synthetic */ boolean val$gallery;
        final /* synthetic */ int val$type;

        /* renamed from: org.telegram.ui.CameraScanActivity$1$1, reason: invalid class name and collision with other inner class name */
        public class C00371 extends CameraScanActivity {
            final /* synthetic */ AnonymousClass1 this$0;

            public C00371(AnonymousClass1 r1, int r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public void finishFragment() {
                setFinishing(true);
                this.this$0.dismiss();
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public void removeSelfFromStack() {
                this.this$0.dismiss();
            }
        }

        public static /* synthetic */ void $r8$lambda$tRI66aXXhuM0SoeFKuq8sK7OrJU(AnonymousClass1 r0, DialogInterface r1) {
            r0.lambda$new$0(r1);
        }

        public AnonymousClass1(Context r2, boolean r3, INavigationLayout[] r4, int r5, boolean r6, CameraScanActivityDelegate r7) {
            this.val$actionBarLayout = r4;
            this.val$type = r5;
            this.val$gallery = r6;
            this.val$cameraDelegate = r7;
            super(r2, r3);
            r4[0].setFragmentStack(new ArrayList());
            C00371 r32 = new C00371(this, r5);
            this.fragment = r32;
            r32.shownAsBottomSheet = true;
            CameraScanActivity.m6588$$Nest$fputneedGalleryButton(r32, r6);
            r4[0].addFragmentToStack(this.fragment);
            r4[0].showLastFragment();
            ViewGroup r33 = r4[0].getView();
            int r52 = this.backgroundPaddingLeft;
            r33.setPadding(r52, 0, r52, 0);
            this.fragment.setDelegate(r7);
            if (r7.getSubtitleText() == null) goto L5;
            CameraScanActivity.m6577$$Nest$fgetdescriptionText(this.fragment).setText(r7.getSubtitleText());
        L5:
            this.containerView = r4[0].getView();
            setApplyBottomPadding(false);
            setApplyBottomPadding(false);
            setOnDismissListener(new CameraScanActivity$1$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ void lambda$new$0(DialogInterface r1) {
            this.fragment.onFragmentDestroy();
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public boolean canDismissWithSwipe() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            this.val$actionBarLayout[0] = null;
            this.val$cameraDelegate.onDismiss();
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
        public void onBackPressed() {
            INavigationLayout r0 = this.val$actionBarLayout[0];
            if (r0 != null) goto L5;
        L9:
            super.onBackPressed();
            return;
        L5:
            if (r0.getFragmentStack().size() <= 1) goto L9;
            this.val$actionBarLayout[0].onBackPressed();
        }
    }

    /* renamed from: org.telegram.ui.CameraScanActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CameraScanActivity this$0;

        public AnonymousClass2(CameraScanActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.CameraScanActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewGroup {
        Path path;
        final /* synthetic */ CameraScanActivity this$0;

        public AnonymousClass3(CameraScanActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.path = new Path();
        }

        private RectF aroundPoint(int r3, int r4, int r5) {
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(r3 - r5, r4 - r5, r3 + r5, r4 + r5);
            return r2;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r23, View r24, long r25) {
            boolean r7 = super.drawChild(r23, r24, r25);
            if (CameraScanActivity.m6590$$Nest$misQr(this.this$0) == false) goto L9;
            if (r24 != CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0)) goto L9;
            RectF r1 = CameraScanActivity.m6589$$Nest$mgetBounds(this.this$0);
            int r2 = (int) (r24.getWidth() * r1.width());
            int r3 = (int) (r24.getHeight() * r1.height());
            int r4 = (int) (r24.getWidth() * r1.centerX());
            int r12 = (int) (r24.getHeight() * r1.centerY());
            int r9 = (int) (r2 * ((CameraScanActivity.m6583$$Nest$fgetqrAppearingValue(this.this$0) * 0.5f) + 0.5f));
            int r10 = (int) (r3 * ((CameraScanActivity.m6583$$Nest$fgetqrAppearingValue(this.this$0) * 0.5f) + 0.5f));
            int r11 = r4 - (r9 / 2);
            int r122 = r12 - (r10 / 2);
            CameraScanActivity.m6582$$Nest$fgetpaint(this.this$0).setAlpha((int) ((1.0f - ((1.0f - CameraScanActivity.m6573$$Nest$fgetbackShadowAlpha(this.this$0)) * Math.min(1.0f, CameraScanActivity.m6583$$Nest$fgetqrAppearingValue(this.this$0)))) * 255.0f));
            float r32 = r122;
            r23.drawRect(0.0f, 0.0f, r24.getMeasuredWidth(), r32, CameraScanActivity.m6582$$Nest$fgetpaint(this.this$0));
            int r13 = r122 + r10;
            float r5 = r13;
            r23.drawRect(0.0f, r5, r24.getMeasuredWidth(), r24.getMeasuredHeight(), CameraScanActivity.m6582$$Nest$fgetpaint(this.this$0));
            float r22 = r11;
            r23.drawRect(0.0f, r32, r22, r5, CameraScanActivity.m6582$$Nest$fgetpaint(this.this$0));
            int r14 = r11 + r9;
            float r26 = r14;
            r23.drawRect(r26, r32, r24.getMeasuredWidth(), r5, CameraScanActivity.m6582$$Nest$fgetpaint(this.this$0));
            CameraScanActivity.m6582$$Nest$fgetpaint(this.this$0).setAlpha((int) (Math.max(0.0f, 1.0f - CameraScanActivity.m6583$$Nest$fgetqrAppearingValue(this.this$0)) * 255.0f));
            r23.drawRect(r22, r32, r26, r5, CameraScanActivity.m6582$$Nest$fgetpaint(this.this$0));
            int r6 = AndroidUtilities.lerp(0, AndroidUtilities.dp(4.0f), Math.min(1.0f, CameraScanActivity.m6583$$Nest$fgetqrAppearingValue(this.this$0) * 20.0f));
            int r132 = r6 / 2;
            int r27 = AndroidUtilities.lerp(Math.min(r9, r10), AndroidUtilities.dp(20.0f), Math.min(1.2f, (float) Math.pow(CameraScanActivity.m6583$$Nest$fgetqrAppearingValue(this.this$0), 1.7999999523162842d)));
            CameraScanActivity.m6575$$Nest$fgetcornerPaint(this.this$0).setAlpha((int) (Math.min(1.0f, CameraScanActivity.m6583$$Nest$fgetqrAppearingValue(this.this$0)) * 255.0f));
            this.path.reset();
            int r42 = r122 + r27;
            this.path.arcTo(aroundPoint(r11, r42, r132), 0.0f, 180.0f);
            float r52 = r6 * 1.5f;
            int r102 = (int) (r22 + r52);
            int r92 = (int) (r32 + r52);
            int r28 = r6 * 2;
            this.path.arcTo(aroundPoint(r102, r92, r28), 180.0f, 90.0f);
            int r53 = r11 + r27;
            this.path.arcTo(aroundPoint(r53, r122, r132), 270.0f, 180.0f);
            this.path.lineTo(r11 + r132, r122 + r132);
            this.path.arcTo(aroundPoint(r102, r92, r6), 270.0f, -90.0f);
            this.path.close();
            r23.drawPath(this.path, CameraScanActivity.m6575$$Nest$fgetcornerPaint(this.this$0));
            this.path.reset();
            this.path.arcTo(aroundPoint(r14, r42, r132), 180.0f, -180.0f);
            int r43 = (int) (r26 - r52);
            this.path.arcTo(aroundPoint(r43, r92, r28), 0.0f, -90.0f);
            int r54 = r14 - r27;
            this.path.arcTo(aroundPoint(r54, r122, r132), 270.0f, -180.0f);
            this.path.arcTo(aroundPoint(r43, r92, r6), 270.0f, 90.0f);
            this.path.close();
            r23.drawPath(this.path, CameraScanActivity.m6575$$Nest$fgetcornerPaint(this.this$0));
            this.path.reset();
            int r72 = r13 - r27;
            this.path.arcTo(aroundPoint(r11, r72, r132), 0.0f, -180.0f);
            int r93 = (int) (r5 - r52);
            this.path.arcTo(aroundPoint(r102, r93, r28), 180.0f, -90.0f);
            this.path.arcTo(aroundPoint(r53, r13, r132), 90.0f, -180.0f);
            this.path.arcTo(aroundPoint(r102, r93, r6), 90.0f, 90.0f);
            this.path.close();
            r23.drawPath(this.path, CameraScanActivity.m6575$$Nest$fgetcornerPaint(this.this$0));
            this.path.reset();
            this.path.arcTo(aroundPoint(r14, r72, r132), 180.0f, 180.0f);
            this.path.arcTo(aroundPoint(r43, r93, r28), 0.0f, 90.0f);
            this.path.arcTo(aroundPoint(r54, r13, r132), 90.0f, 180.0f);
            this.path.arcTo(aroundPoint(r43, r93, r6), 90.0f, -90.0f);
            this.path.close();
            r23.drawPath(this.path, CameraScanActivity.m6575$$Nest$fgetcornerPaint(this.this$0));
            return r7;
        L9:
            return r7;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r7, int r8, int r9, int r10, int r11) {
            int r102 = r10 - r8;
            int r112 = r11 - r9;
            int r72 = CameraScanActivity.m6576$$Nest$fgetcurrentType(this.this$0);
            CameraScanActivity r82 = this.this$0;
            if (r72 == 0) goto L5;
            CameraScanActivity.access$300(r82).layout(0, 0, CameraScanActivity.access$100(this.this$0).getMeasuredWidth(), CameraScanActivity.access$200(this.this$0).getMeasuredHeight());
            if (CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0) == null) goto L11;
            CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).layout(0, 0, CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).getMeasuredWidth(), CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).getMeasuredHeight());
        L11:
            int r73 = (int) (Math.min(r102, r112) / 1.5f);
            int r83 = CameraScanActivity.m6576$$Nest$fgetcurrentType(this.this$0);
            CameraScanActivity r2 = this.this$0;
            if (r83 != 1) goto L15;
            int r84 = ((r112 - r73) / 2) - CameraScanActivity.m6586$$Nest$fgettitleTextView(r2).getMeasuredHeight();
            int r22 = AndroidUtilities.dp(30.0f);
        L14:
            int r85 = r84 - r22;
            CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).layout(AndroidUtilities.dp(36.0f), r85, AndroidUtilities.dp(36.0f) + CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r85);
            if (CameraScanActivity.m6576$$Nest$fgetcurrentType(this.this$0) != 3) goto L19;
            int r86 = r85 + (CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            CameraScanActivity.m6577$$Nest$fgetdescriptionText(this.this$0).layout(AndroidUtilities.dp(36.0f), r86, AndroidUtilities.dp(36.0f) + CameraScanActivity.m6577$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth(), CameraScanActivity.m6577$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r86);
        L19:
            CameraScanActivity.m6585$$Nest$fgetrecognizedMrzView(this.this$0).layout(0, getMeasuredHeight() - CameraScanActivity.m6585$$Nest$fgetrecognizedMrzView(this.this$0).getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            if (CameraScanActivity.m6581$$Nest$fgetneedGalleryButton(this.this$0) == false) goto L22;
            int r87 = (r102 / 2) + AndroidUtilities.dp(35.0f);
        L23:
            int r1 = (((r112 - r73) / 2) + r73) + AndroidUtilities.dp(80.0f);
            CameraScanActivity.m6578$$Nest$fgetflashButton(this.this$0).layout(r87, r1, CameraScanActivity.m6578$$Nest$fgetflashButton(this.this$0).getMeasuredWidth() + r87, CameraScanActivity.m6578$$Nest$fgetflashButton(this.this$0).getMeasuredHeight() + r1);
            if (CameraScanActivity.m6579$$Nest$fgetgalleryButton(this.this$0) == null) goto L27;
            int r74 = ((r102 / 2) - AndroidUtilities.dp(35.0f)) - CameraScanActivity.m6579$$Nest$fgetgalleryButton(this.this$0).getMeasuredWidth();
            CameraScanActivity.m6579$$Nest$fgetgalleryButton(this.this$0).layout(r74, r1, CameraScanActivity.m6579$$Nest$fgetgalleryButton(this.this$0).getMeasuredWidth() + r74, CameraScanActivity.m6579$$Nest$fgetgalleryButton(this.this$0).getMeasuredHeight() + r1);
        L27:
            if (CameraScanActivity.m6576$$Nest$fgetcurrentType(this.this$0) == 3) goto L29;
            int r75 = (int) (r112 * 0.74f);
            int r88 = (int) (r102 * 0.05f);
            CameraScanActivity.m6577$$Nest$fgetdescriptionText(this.this$0).layout(r88, r75, CameraScanActivity.m6577$$Nest$fgetdescriptionText(this.this$0).getMeasuredWidth() + r88, CameraScanActivity.m6577$$Nest$fgetdescriptionText(this.this$0).getMeasuredHeight() + r75);
        L29:
            CameraScanActivity.m6592$$Nest$mupdateNormalBounds(this.this$0);
            return;
        L22:
            r87 = (r102 / 2) - (CameraScanActivity.m6578$$Nest$fgetflashButton(this.this$0).getMeasuredWidth() / 2);
            goto L23
        L15:
            r84 = ((r112 - r73) / 2) - CameraScanActivity.m6586$$Nest$fgettitleTextView(r2).getMeasuredHeight();
            r22 = AndroidUtilities.dp(64.0f);
            goto L14
        L5:
            if (CameraScanActivity.m6574$$Nest$fgetcameraView(r82) == null) goto L7;
            CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).layout(0, 0, CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).getMeasuredWidth(), CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).getMeasuredHeight());
        L7:
            CameraScanActivity.m6585$$Nest$fgetrecognizedMrzView(this.this$0).setTextSize(0, r112 / 22);
            CameraScanActivity.m6585$$Nest$fgetrecognizedMrzView(this.this$0).setPadding(0, 0, 0, r112 / 15);
            int r76 = (int) (r112 * 0.65f);
            CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).layout(AndroidUtilities.dp(36.0f), r76, AndroidUtilities.dp(36.0f) + CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).getMeasuredWidth(), CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).getMeasuredHeight() + r76);
            goto L27
        }

        @Override // android.view.View
        public void onMeasure(int r7, int r8) {
            int r0 = View.MeasureSpec.getSize(r7);
            int r1 = View.MeasureSpec.getSize(r8);
            CameraScanActivity.access$000(this.this$0).measure(r7, r8);
            int r72 = CameraScanActivity.m6576$$Nest$fgetcurrentType(this.this$0);
            CameraScanActivity r82 = this.this$0;
            if (r72 != 0) goto L8;
            if (CameraScanActivity.m6574$$Nest$fgetcameraView(r82) == null) goto L14;
            CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.704f), TLObject.FLAG_30));
        L14:
            CameraScanActivity.m6586$$Nest$fgettitleTextView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0 - AndroidUtilities.dp(72.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, 0));
            int r73 = CameraScanActivity.m6576$$Nest$fgetcurrentType(this.this$0);
            CameraScanActivity r4 = this.this$0;
            if (r73 != 3) goto L17;
            CameraScanActivity.m6577$$Nest$fgetdescriptionText(r4).measure(View.MeasureSpec.makeMeasureSpec(r0 - AndroidUtilities.dp(72.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, 0));
        L18:
            setMeasuredDimension(r0, r1);
            return;
        L17:
            CameraScanActivity.m6577$$Nest$fgetdescriptionText(r4).measure(View.MeasureSpec.makeMeasureSpec((int) (r0 * 0.9f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, 0));
            goto L18
        L8:
            if (CameraScanActivity.m6574$$Nest$fgetcameraView(r82) == null) goto L10;
            CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        L10:
            CameraScanActivity.m6585$$Nest$fgetrecognizedMrzView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1, 0));
            if (CameraScanActivity.m6579$$Nest$fgetgalleryButton(this.this$0) == null) goto L13;
            CameraScanActivity.m6579$$Nest$fgetgalleryButton(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
        L13:
            CameraScanActivity.m6578$$Nest$fgetflashButton(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30));
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.CameraScanActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends TextView {
        LinkSpanDrawable.LinkCollector links;
        private LinkSpanDrawable<URLSpanNoUnderline> pressedLink;
        LinkPath textPath;
        final /* synthetic */ CameraScanActivity this$0;
        final /* synthetic */ Paint val$selectionPaint;

        public AnonymousClass4(CameraScanActivity r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$selectionPaint = r3;
            super(r2);
            this.links = new LinkSpanDrawable.LinkCollector(this);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r3) {
            LinkPath r0 = this.textPath;
            if (r0 == null) goto L6;
            r3.drawPath(r0, this.val$selectionPaint);
        L6:
            if (this.links.draw(r3) == false) goto L8;
            invalidate();
        L8:
            super.onDraw(r3);
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r9, int r10) {
            super.onMeasure(r9, r10);
            if ((getText() instanceof Spanned) == false) goto L28;
            Spanned r92 = (Spanned) getText();
            URLSpanNoUnderline[] r102 = (URLSpanNoUnderline[]) r92.getSpans(0, r92.length(), URLSpanNoUnderline.class);
            if (r102 != null) goto L7;
            return;
        L7:
            if (r102.length <= 0) goto L30;
            LinkPath r0 = new LinkPath(true);
            this.textPath = r0;
            r0.setAllowReset(false);
            int r02 = 0;
        L10:
            if (r02 >= r102.length) goto L23;
            int r3 = r92.getSpanStart(r102[r02]);
            int r4 = r92.getSpanEnd(r102[r02]);
            this.textPath.setCurrentLayout(getLayout(), r3, 0.0f);
            if (getText() == null) goto L14;
            int r5 = getPaint().baselineShift;
        L15:
            LinkPath r6 = this.textPath;
            if (r5 == 0) goto L21;
            if (r5 <= 0) goto L19;
            float r7 = 5.0f;
        L20:
            int r52 = r5 + AndroidUtilities.dp(r7);
        L22:
            r6.setBaselineShift(r52);
            getLayout().getSelectionPath(r3, r4, this.textPath);
            r02 = r02 + 1;
            goto L10
        L19:
            r7 = -2.0f;
            goto L20
        L21:
            r52 = 0;
            goto L22
        L14:
            r5 = 0;
            goto L15
        L23:
            this.textPath.setAllowReset(true);
            return;
        L30:
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r11) {
            Layout r0 = getLayout();
            int r1 = (int) (r11.getX() - 0.0f);
            int r3 = (int) (r11.getY() - 0.0f);
            if (r11.getAction() != 0) goto L5;
        L6:
            int r4 = r0.getLineForVertical(r3);
            float r12 = r1;
            int r7 = r0.getOffsetForHorizontal(r4, r12);
            float r8 = r0.getLineLeft(r4);
            if (r8 <= r12) goto L9;
        L28:
            if (r11.getAction() != 1) goto L30;
        L31:
            this.links.clear();
            this.pressedLink = null;
        L33:
            return super.onTouchEvent(r11);
        L30:
            if (r11.getAction() != 3) goto L33;
        L9:
            if ((r8 + r0.getLineWidth(r4)) < r12) goto L28;
            if (r3 < 0) goto L28;
            if (r3 > r0.getHeight()) goto L28;
            Spannable r13 = (Spannable) r0.getText();
            ClickableSpan[] r32 = (ClickableSpan[]) r13.getSpans(r7, r7, ClickableSpan.class);
            if (r32.length == 0) goto L28;
            this.links.clear();
            if (r11.getAction() != 0) goto L19;
            LinkSpanDrawable<URLSpanNoUnderline> r42 = new LinkSpanDrawable(r32[0], null, r11.getX(), r11.getY());
            this.pressedLink = r42;
            r42.setColor(771751935);
            this.links.addLink(this.pressedLink);
            int r112 = r13.getSpanStart(this.pressedLink.getSpan());
            int r14 = r13.getSpanEnd(this.pressedLink.getSpan());
            LinkPath r10 = this.pressedLink.obtainNewPath();
            r10.setCurrentLayout(r0, r112, 0.0f);
            r0.getSelectionPath(r112, r14, r10);
        L26:
            return true;
        L19:
            if (r11.getAction() != 1) goto L26;
            LinkSpanDrawable<URLSpanNoUnderline> r113 = this.pressedLink;
            if (r113 == null) goto L25;
            CharacterStyle r114 = r113.getSpan();
            ClickableSpan r02 = r32[0];
            if (r114 != r02) goto L25;
            r02.onClick(this);
        L25:
            this.pressedLink = null;
            goto L26
        L5:
            if (r11.getAction() != 1) goto L28;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.CameraScanActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ CameraScanActivity this$0;

        public AnonymousClass5(CameraScanActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            CameraScanActivity.m6587$$Nest$fputflashAnimator(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.CameraScanActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ CameraScanActivity this$0;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;

        public AnonymousClass6(CameraScanActivity r1, ChatAttachAlert r2) {
            this.this$0 = r1;
            this.val$chatAttachAlert = r2;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            if (r1 != 7) goto L5;
        L7:
            HashMap<Object, Object> r12 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotos();
            ArrayList<Object> r22 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotosOrder();
            if (r22.isEmpty() == true) goto L11;
            Object r13 = r12.get(r22.get(0));
        L15:
            if ((r13 instanceof MediaController.PhotoEntry) == false) goto L22;
            MediaController.PhotoEntry r14 = (MediaController.PhotoEntry) r13;
            CameraScanActivity r23 = this.this$0;
            String r32 = r14.imagePath;
            if (r32 != null) goto L20;
            r32 = r14.path;
        L20:
            ChatAttachAlert r0 = this.val$chatAttachAlert;
            Objects.requireNonNull(r0);
            CameraScanActivity.m6591$$Nest$mprocessGalleryQr(r23, r32, null, new CameraScanActivity$6$$ExternalSyntheticLambda0(r0));
            return;
        L22:
            return;
        L11:
            if (r12.isEmpty() == true) goto L13;
            r13 = r12.values().iterator().next();
            goto L15
        L13:
            r13 = null;
            goto L15
        L5:
            if (r1 == 8) goto L7;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean selectItemOnClicking() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.CameraScanActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ CameraScanActivity this$0;

        /* renamed from: $r8$lambda$ZWbJDRAop6OAkWktrpEgUHdtW-k, reason: not valid java name */
        public static /* synthetic */ void m6593$r8$lambda$ZWbJDRAop6OAkWktrpEgUHdtWk(AnonymousClass7 r0) {
            r0.lambda$run$0();
        }

        public AnonymousClass7(CameraScanActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0() {
            CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).focusToPoint(CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).getWidth() / 2, CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).getHeight() / 2, false);     // Catch: Exception -> L7
        L4:
            if (CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0) == null) goto L10;
            CameraScanActivity r4 = this.this$0;
            r4.processShot(CameraScanActivity.m6574$$Nest$fgetcameraView(r4).getTextureView().getBitmap());
            return;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0) != null) goto L5;
            return;
        L5:
            if (CameraScanActivity.m6584$$Nest$fgetrecognized(this.this$0) == false) goto L7;
            return;
        L7:
            if (CameraScanActivity.m6574$$Nest$fgetcameraView(this.this$0).getCameraSession() == null) goto L12;
            CameraScanActivity.m6580$$Nest$fgethandler(this.this$0).post(new CameraScanActivity$7$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    public interface CameraScanActivityDelegate {
        default void didFindMrzInfo(MrzRecognizer.Result r1) {
        }

        default void didFindQr(String r1) {
        }

        default String getSubtitleText() {
            return null;
        }

        default void onDismiss() {
        }

        default boolean processQr(String r1, Runnable r2) {
            return false;
        }
    }

    public class QrResult {
        RectF bounds;
        PointF[] cornerPoints;
        String text;
        final /* synthetic */ CameraScanActivity this$0;

        private QrResult(CameraScanActivity r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ QrResult(CameraScanActivity r1, CameraScanActivityIA r2) {
            this(r1);
        }
    }

    /* renamed from: $r8$lambda$-2E11urUQTiD2c9wIUCgzMLDHSU, reason: not valid java name */
    public static /* synthetic */ void m6565$r8$lambda$2E11urUQTiD2c9wIUCgzMLDHSU(CameraScanActivity r0) {
        r0.lambda$processShot$19();
    }

    public static /* synthetic */ boolean $r8$lambda$4cb0ykEfSywGHVBhUUdlyxjpdNo(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$6vAxDu8qc3b3sW0ah2hBF5iWEjE(CameraScanActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    /* renamed from: $r8$lambda$8W6EfegE6KSd2oV-A4YZNTVfkx8, reason: not valid java name */
    public static /* synthetic */ void m6566$r8$lambda$8W6EfegE6KSd2oVA4YZNTVfkx8(CameraScanActivity r0, String r1, Runnable r2) {
        r0.lambda$processGalleryQrResult$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FTCrHplujsFH9g6z_dun_Eqf6Ac(CameraScanActivity r0, ValueAnimator r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$JqmfWK5hPoREj9YiR4PB8CpxM_w(CameraScanActivity r0) {
        r0.lambda$processShot$18();
    }

    public static /* synthetic */ void $r8$lambda$LfjgF80Xk3K8xpirqNPjqcnjT84(CameraScanActivity r0) {
        r0.lambda$processShot$15();
    }

    /* renamed from: $r8$lambda$SuJZiNPuh-gwGnv56dBz_LQ_A0s, reason: not valid java name */
    public static /* synthetic */ void m6567$r8$lambda$SuJZiNPuhgwGnv56dBz_LQ_A0s(CameraScanActivity r0, MrzRecognizer.Result r1) {
        r0.lambda$processShot$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$V6eQWbkHwkWbVLXzsr24_ij8_78(CameraScanActivity r0) {
        r0.lambda$initCameraView$9();
    }

    /* renamed from: $r8$lambda$Va7ahyGVFYH5oX3xzPf-NMbkasQ, reason: not valid java name */
    public static /* synthetic */ void m6568$r8$lambda$Va7ahyGVFYH5oX3xzPfNMbkasQ(CameraScanActivity r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$VvrF7SvNuxBBupjjCUNTxtqpAdQ(CameraScanActivity r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$initCameraView$8(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$XA1mQEf4OUgRJv2Z5L2-1Q4L0xE, reason: not valid java name */
    public static /* synthetic */ void m6569$r8$lambda$XA1mQEf4OUgRJv2Z5L21Q4L0xE(CameraScanActivity r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$updateRecognized$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$YDAb6vPa0QnGpM00C6Dm7MjPIcE(CameraScanActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$dXgV5SHk971WOmhZaASBkZ8aXxU(CameraScanActivity r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$initCameraView$7(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hCaSxOsJ5Ei9deyKulg-ckszngI, reason: not valid java name */
    public static /* synthetic */ void m6570$r8$lambda$hCaSxOsJ5Ei9deyKulgckszngI(CameraScanActivity r0, String r1) {
        r0.lambda$processShot$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$kmm1Atuz2DsDm40ZmHzBPX3DqIk(CameraScanActivity r0) {
        r0.updateRecognized();
    }

    public static /* synthetic */ void $r8$lambda$oie_b2RTwCiADJUeCIiOyIuxV7Q(CameraScanActivity r0, ValueAnimator r1) {
        r0.lambda$updateRecognized$5(r1);
    }

    /* renamed from: $r8$lambda$v6QH-FIBo7PFTl3lPQMmptUzYQA, reason: not valid java name */
    public static /* synthetic */ void m6571$r8$lambda$v6QHFIBo7PFTl3lPQMmptUzYQA(CameraScanActivity r0) {
        r0.initCameraView();
    }

    public static /* synthetic */ void $r8$lambda$vTCa9esdj3Kn8E4Ss0wcTLUCcPQ(CameraScanActivity r0, QrResult r1) {
        r0.lambda$processShot$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$xEeAQ3gci8hV7CKOUXMHnuMChIw(CameraScanActivity r0) {
        r0.lambda$processShot$14();
    }

    public static /* synthetic */ void $r8$lambda$xQsOxu5e6OJJ_pC9zVw8ENo3tP4(CameraScanActivity r0) {
        r0.lambda$onNoQrFound$12();
    }

    /* renamed from: $r8$lambda$yeO-f9XMTgXCGDdzFENu_KUxKUw, reason: not valid java name */
    public static /* synthetic */ void m6572$r8$lambda$yeOf9XMTgXCGDdzFENu_KUxKUw(CameraScanActivity r0, String r1, Runnable r2) {
        r0.lambda$processGalleryQrResult$11(r1, r2);
    }

    /* renamed from: -$$Nest$fgetbackShadowAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6573$$Nest$fgetbackShadowAlpha(CameraScanActivity r0) {
        return r0.backShadowAlpha;
    }

    /* renamed from: -$$Nest$fgetcameraView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CameraView m6574$$Nest$fgetcameraView(CameraScanActivity r0) {
        return r0.cameraView;
    }

    /* renamed from: -$$Nest$fgetcornerPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m6575$$Nest$fgetcornerPaint(CameraScanActivity r0) {
        return r0.cornerPaint;
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m6576$$Nest$fgetcurrentType(CameraScanActivity r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetdescriptionText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6577$$Nest$fgetdescriptionText(CameraScanActivity r0) {
        return r0.descriptionText;
    }

    /* renamed from: -$$Nest$fgetflashButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m6578$$Nest$fgetflashButton(CameraScanActivity r0) {
        return r0.flashButton;
    }

    /* renamed from: -$$Nest$fgetgalleryButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m6579$$Nest$fgetgalleryButton(CameraScanActivity r0) {
        return r0.galleryButton;
    }

    /* renamed from: -$$Nest$fgethandler, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Handler m6580$$Nest$fgethandler(CameraScanActivity r0) {
        return r0.handler;
    }

    /* renamed from: -$$Nest$fgetneedGalleryButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6581$$Nest$fgetneedGalleryButton(CameraScanActivity r0) {
        return r0.needGalleryButton;
    }

    /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m6582$$Nest$fgetpaint(CameraScanActivity r0) {
        return r0.paint;
    }

    /* renamed from: -$$Nest$fgetqrAppearingValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6583$$Nest$fgetqrAppearingValue(CameraScanActivity r0) {
        return r0.qrAppearingValue;
    }

    /* renamed from: -$$Nest$fgetrecognized, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6584$$Nest$fgetrecognized(CameraScanActivity r0) {
        return r0.recognized;
    }

    /* renamed from: -$$Nest$fgetrecognizedMrzView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6585$$Nest$fgetrecognizedMrzView(CameraScanActivity r0) {
        return r0.recognizedMrzView;
    }

    /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6586$$Nest$fgettitleTextView(CameraScanActivity r0) {
        return r0.titleTextView;
    }

    /* renamed from: -$$Nest$fputflashAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6587$$Nest$fputflashAnimator(CameraScanActivity r0, AnimatorSet r1) {
        r0.flashAnimator = r1;
    }

    /* renamed from: -$$Nest$fputneedGalleryButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6588$$Nest$fputneedGalleryButton(CameraScanActivity r0, boolean r1) {
        r0.needGalleryButton = r1;
    }

    /* renamed from: -$$Nest$mgetBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m6589$$Nest$mgetBounds(CameraScanActivity r0) {
        return r0.getBounds();
    }

    /* renamed from: -$$Nest$misQr, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6590$$Nest$misQr(CameraScanActivity r0) {
        return r0.isQr();
    }

    /* renamed from: -$$Nest$mprocessGalleryQr, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6591$$Nest$mprocessGalleryQr(CameraScanActivity r0, String r1, Uri r2, Runnable r3) {
        r0.processGalleryQr(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mupdateNormalBounds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6592$$Nest$mupdateNormalBounds(CameraScanActivity r0) {
        r0.updateNormalBounds();
    }

    public CameraScanActivity(int r10) {
        this.backgroundHandlerThread = new HandlerThread("ScanCamera");
        this.paint = new Paint();
        this.cornerPaint = new Paint(1);
        this.path = new Path();
        this.backShadowAlpha = 0.5f;
        this.shownAsBottomSheet = false;
        this.qrAppearing = null;
        this.qrAppearingValue = 0.0f;
        this.fromPoints = new PointF[4];
        this.points = new PointF[4];
        this.tmpPoints = new PointF[4];
        this.tmp2Points = new PointF[4];
        int r5 = 0;
    L3:
        if (r5 >= 4) goto L5;
        this.fromPoints[r5] = new PointF(-1.0f, -1.0f);
        this.points[r5] = new PointF(-1.0f, -1.0f);
        this.tmpPoints[r5] = new PointF(-1.0f, -1.0f);
        this.tmp2Points[r5] = new PointF(-1.0f, -1.0f);
        r5 = r5 + 1;
        goto L3
    L5:
        this.fromBounds = new RectF();
        this.bounds = new RectF();
        this.lastBoundsUpdate = 0;
        this.boundsUpdateDuration = 75;
        this.recognizeFailed = 0;
        this.recognizeIndex = 0;
        this.qrLoading = false;
        this.qrLoaded = false;
        this.qrReader = null;
        this.visionQrReader = null;
        this.recognizedT = 0.0f;
        this.newRecognizedT = 0.0f;
        this.useRecognizedBounds = 0.0f;
        this.requestShot = new AnonymousClass7(this);
        this.averageProcessTime = 0.0f;
        this.processTimesCount = 0;
        this.currentType = r10;
        if (isQr() == false) goto L8;
        Utilities.globalQueue.postRunnable(new CameraScanActivity$$ExternalSyntheticLambda4(this));
    L8:
        int r102 = SharedConfig.getDevicePerformanceClass();
        if (r102 == 0) goto L15;
        if (r102 == 1) goto L13;
        this.sps = 40;
        return;
    L13:
        this.sps = 24;
        return;
    L15:
        this.sps = 8;
    }

    public static /* synthetic */ ActionBar access$000(CameraScanActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(CameraScanActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(CameraScanActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(CameraScanActivity r0) {
        return r0.actionBar;
    }

    public static ColorMatrix createThresholdMatrix(int r5) {
        float r52 = r5 * (-255.0f);
        return new ColorMatrix(new float[]{85.0f, 85.0f, 85.0f, 0.0f, r52, 85.0f, 85.0f, 85.0f, 0.0f, r52, 85.0f, 85.0f, 85.0f, 0.0f, r52, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    private RectF getBounds() {
        RectF r0 = getRecognizedBounds();
        if (this.useRecognizedBounds < 1.0f) goto L5;
    L8:
        return r0;
    L5:
        if (this.normalBounds != null) goto L7;
        updateNormalBounds();
    L7:
        AndroidUtilities.lerp(this.normalBounds, r0, this.useRecognizedBounds, r0);
        goto L8
    }

    private RectF getRecognizedBounds() {
        float r0 = Math.min(1.0f, Math.max(0.0f, (SystemClock.elapsedRealtime() - this.lastBoundsUpdate) / 75.0f));
        if (r0 >= 1.0f) goto L5;
        this.fragmentView.invalidate();
    L5:
        RectF r1 = this.fromBounds;
        RectF r4 = this.bounds;
        RectF r2 = AndroidUtilities.rectTmp;
        AndroidUtilities.lerp(r1, r4, r0, r2);
        return r2;
    }

    private void initCameraView() {
        if (this.fragmentView != null) goto L5;
        return;
    L5:
        if (CameraView.isCameraAllowed() == false) goto L16;
        CameraController.getInstance().initCamera(null);
        CameraView r0 = new CameraView(this.fragmentView.getContext(), false);
        this.cameraView = r0;
        r0.setUseMaxPreview(true);
        this.cameraView.setOptimizeForBarcode(true);
        this.cameraView.setDelegate(new CameraScanActivity$$ExternalSyntheticLambda7(this));
        ((ViewGroup) this.fragmentView).addView(this.cameraView, 0, LayoutHelper.createFrame(-1, -1.0f));
        if (this.currentType != 0) goto L14;
        TextView r02 = this.recognizedMrzView;
        if (r02 == null) goto L15;
        this.cameraView.addView(r02);
        return;
    L15:
        return;
    L14:
        return;
    }

    private Bitmap invert(Bitmap r7) {
        int r6 = r7.getHeight();
        Bitmap r62 = Bitmap.createBitmap(r7.getWidth(), r6, Bitmap.Config.ARGB_8888);
        Canvas r0 = new Canvas(r62);
        Paint r1 = new Paint();
        ColorMatrix r2 = new ColorMatrix();
        r2.setSaturation(0.0f);
        ColorMatrix r4 = new ColorMatrix();
        r4.set(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        r4.preConcat(r2);
        r1.setColorFilter(new ColorMatrixColorFilter(r4));
        r0.drawBitmap(r7, 0.0f, 0.0f, r1);
        return r62;
    }

    private boolean isQr() {
        int r2 = this.currentType;
        if (r2 != 1) goto L5;
    L11:
        return true;
    L5:
        if (r2 == 2) goto L11;
        if (r2 == 3) goto L11;
        return false;
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        openGalleryPicker();
    }

    private /* synthetic */ void lambda$createView$3(ValueAnimator r1) {
        this.flashButton.invalidate();
    }

    private /* synthetic */ void lambda$createView$4(View r7) {
        CameraView r72 = this.cameraView;
        if (r72 == null) goto L21;
        CameraSessionWrapper r73 = r72.getCameraSession();
        if (r73 == null) goto L20;
        ShapeDrawable r0 = (ShapeDrawable) this.flashButton.getBackground();
        AnimatorSet r1 = this.flashAnimator;
        if (r1 == null) goto L10;
        r1.cancel();
        this.flashAnimator = null;
    L10:
        this.flashAnimator = new AnimatorSet();
        Property<ShapeDrawable, Integer> r12 = AnimationProperties.SHAPE_DRAWABLE_ALPHA;
        if (this.flashButton.getTag() != null) goto L13;
        int r3 = 68;
    L14:
        ObjectAnimator r02 = ObjectAnimator.ofInt(r0, r12, new int[]{r3});
        r02.addUpdateListener(new CameraScanActivity$$ExternalSyntheticLambda8(this));
        this.flashAnimator.playTogether(new Animator[]{r02});
        this.flashAnimator.setDuration(200);
        this.flashAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.flashAnimator.addListener(new AnonymousClass5(this));
        this.flashAnimator.start();
        Object r03 = this.flashButton.getTag();
        ImageView r6 = this.flashButton;
        if (r03 != null) goto L18;
        r6.setTag(1);
        r73.setCurrentFlashMode("torch");
        return;
    L18:
        r6.setTag(null);
        r73.setCurrentFlashMode("off");
        return;
    L13:
        r3 = 34;
        goto L14
    L20:
        return;
    }

    private /* synthetic */ void lambda$initCameraView$7(DynamicAnimation r1, float r2, float r3) {
        this.qrAppearingValue = r2 / 500.0f;
        this.fragmentView.invalidate();
    }

    private /* synthetic */ void lambda$initCameraView$8(DynamicAnimation r1, boolean r2, float r3, float r4) {
        SpringAnimation r12 = this.qrAppearing;
        if (r12 == null) goto L6;
        r12.cancel();
        this.qrAppearing = null;
        return;
    }

    private /* synthetic */ void lambda$initCameraView$9() {
        startRecognizing();
        if (isQr() == false) goto L9;
        SpringAnimation r0 = this.qrAppearing;
        if (r0 == null) goto L7;
        r0.cancel();
        this.qrAppearing = null;
    L7:
        SpringAnimation r02 = new SpringAnimation(new FloatValueHolder(0.0f));
        this.qrAppearing = r02;
        r02.addUpdateListener(new CameraScanActivity$$ExternalSyntheticLambda10(this));
        this.qrAppearing.addEndListener(new CameraScanActivity$$ExternalSyntheticLambda11(this));
        this.qrAppearing.setSpring(new SpringForce(500.0f));
        this.qrAppearing.getSpring().setDampingRatio(0.8f);
        this.qrAppearing.getSpring().setStiffness(250.0f);
        this.qrAppearing.start();
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        this.qrReader = new QRCodeReader();
        this.visionQrReader = new BarcodeDetector.Builder(ApplicationLoader.applicationContext).setBarcodeFormats(256).build();
    }

    private /* synthetic */ void lambda$onNoQrFound$12() {
        if (this.recognizedMrzView.getTag() == null) goto L6;
        this.recognizedMrzView.setTag(null);
        this.recognizedMrzView.animate().setDuration(200).alpha(0.0f).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        return;
    }

    private /* synthetic */ void lambda$processGalleryQrResult$10(String r2, Runnable r3) {
        CameraScanActivityDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.didFindQr(r2);
    L5:
        if (r3 == null) goto L8;
        r3.run();
    L8:
        if (this.currentType == 3) goto L11;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$processGalleryQrResult$11(String r2, Runnable r3) {
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda6(this, r2, r3));
    }

    private /* synthetic */ void lambda$processShot$13(MrzRecognizer.Result r4) {
        this.recognizedMrzView.setText(r4.rawMRZ);
        this.recognizedMrzView.animate().setDuration(200).alpha(1.0f).setInterpolator(CubicBezierInterpolator.DEFAULT).start();
        CameraScanActivityDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.didFindMrzInfo(r4);
    L5:
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda20(this), 1200);
    }

    private /* synthetic */ void lambda$processShot$14() {
        CameraScanActivityDelegate r0 = this.delegate;
        if (r0 == null) goto L5;
        r0.didFindQr(this.recognizedText);
    L5:
        finishFragment();
    }

    private /* synthetic */ void lambda$processShot$15() {
        CameraView r0 = this.cameraView;
        if (r0 != null) goto L5;
    L7:
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda19(this));
        return;
    L5:
        if (r0.getCameraSession() == null) goto L7;
        CameraController.getInstance().stopPreview(this.cameraView.getCameraSession());
        goto L7
    }

    private /* synthetic */ void lambda$processShot$16(QrResult r2) {
        updateRecognizedBounds(r2.bounds, r2.cornerPoints);
    }

    private /* synthetic */ void lambda$processShot$17(String r2) {
        CameraScanActivityDelegate r0 = this.delegate;
        if (r0 == null) goto L6;
        r0.didFindQr(r2);
    L6:
        if (this.currentType == 3) goto L9;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$processShot$18() {
        if (isFinishing() == true) goto L10;
        this.recognizedText = null;
        this.recognized = false;
        this.requestShot.run();
        if (this.recognized == true) goto L9;
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda14(this), 500);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$processShot$19() {
        CameraView r0 = this.cameraView;
        if (r0 == null) goto L6;
        processShot(r0.getTextureView().getBitmap());
        return;
    }

    private /* synthetic */ void lambda$updateRecognized$5(ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.recognizedT = r32;
        this.titleTextView.setAlpha(1.0f - r32);
        if (this.currentType != 3) goto L5;
        this.descriptionText.setAlpha(1.0f - this.recognizedT);
    L5:
        this.flashButton.setAlpha(1.0f - this.recognizedT);
        this.backShadowAlpha = (this.recognizedT * 0.25f) + 0.5f;
        this.fragmentView.invalidate();
    }

    private /* synthetic */ void lambda$updateRecognized$6(DynamicAnimation r1, float r2, float r3) {
        if (this.recognized == false) goto L5;
        float r22 = r2 / 500.0f;
    L6:
        this.useRecognizedBounds = r22;
        this.fragmentView.invalidate();
        return;
    L5:
        r22 = 1.0f - (r2 / 500.0f);
        goto L6
    }

    private Bitmap monochrome(Bitmap r4, int r5) {
        int r3 = r4.getHeight();
        Bitmap r32 = Bitmap.createBitmap(r4.getWidth(), r3, Bitmap.Config.ARGB_8888);
        Canvas r0 = new Canvas(r32);
        Paint r1 = new Paint();
        r1.setColorFilter(new ColorMatrixColorFilter(createThresholdMatrix(r5)));
        r0.drawBitmap(r4, 0.0f, 0.0f, r1);
        return r32;
    }

    private void onNoQrFound() {
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda9(this));
    }

    private void openGalleryPicker() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        ChatAttachAlert r0 = this.galleryPickerAlert;
        if (r0 == null) goto L8;
        r0.dismissInternal();
        this.galleryPickerAlert.onDestroy();
    L8:
        ChatAttachAlert r1 = new ChatAttachAlert(getParentActivity(), this, false, false, false, this.resourceProvider);
        this.galleryPickerAlert = r1;
        r1.drawNavigationBar = true;
        r1.setupPhotoPicker(LocaleController.getString(R.string.ChoosePhoto));
        r1.setMaxSelectedPhotos(1, false);
        r1.setDelegate(new AnonymousClass6(this, r1));
        r1.init();
        r1.getPhotoLayout().loadGalleryPhotos();
        r1.show();
    }

    private void processGalleryQr(String r11, Uri r12, Runnable r13) {
        if (r11 != null) goto L15;
        if (r12 != null) goto L15;
        return;
    L15:
        Point r0 = AndroidUtilities.getRealScreenSize();     // Catch: Throwable -> L12
        Bitmap r9 = ImageLoader.loadBitmap(r11, r12, r0.x, r0.y, true);     // Catch: Throwable -> L12
        if (r9 == null) goto L17;
        QrResult r10 = tryReadQr(null, null, 0, 0, 0, r9);     // Catch: Throwable -> L12
        if (r10 == null) goto L18;
        processGalleryQrResult(r10.text, r13);     // Catch: Throwable -> L12
        return;
    L18:
        return;
    L17:
        return;
    L12:
        th = move-exception;
        FileLog.e(th);
    }

    private void processGalleryQrResult(String r3, Runnable r4) {
        CameraScanActivityDelegate r0 = this.delegate;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.processQr(r3, new CameraScanActivity$$ExternalSyntheticLambda5(this, r3, r4)) == true) goto L14;
        this.delegate.didFindQr(r3);
        if (r4 == null) goto L11;
        r4.run();
    L11:
        if (this.currentType == 3) goto L15;
        finishFragment();
        return;
    L15:
        return;
    }

    private void setPointsFromBounds(RectF r3, PointF[] r4) {
        r4[0].set(r3.left, r3.top);
        r4[1].set(r3.right, r3.top);
        r4[2].set(r3.right, r3.bottom);
        r4[3].set(r3.left, r3.bottom);
    }

    public static BottomSheet showAsSheet(Activity r10, boolean r11, int r12, CameraScanActivityDelegate r13) {
        if (r10 != null) goto L5;
        return null;
    L5:
        AnonymousClass1 r3 = new AnonymousClass1(r10, false, new INavigationLayout[]{INavigationLayout.newLayout(r10, false)}, r12, r11, r13);
        r3.setUseLightStatusBar(false);
        AndroidUtilities.setLightNavigationBar(r3, false);
        AndroidUtilities.setNavigationBarColor(r3, -16777216, false);
        r3.setUseLightStatusBar(false);
        r3.getWindow().addFlags(512);
        r3.show();
        return r3;
    }

    public static BottomSheet showAsSheet(BaseFragment r0, boolean r1, int r2, CameraScanActivityDelegate r3) {
        return showAsSheet(r0.getParentActivity(), r1, r2, r3);
    }

    private void startRecognizing() {
        this.backgroundHandlerThread.start();
        this.handler = new Handler(this.backgroundHandlerThread.getLooper());
        AndroidUtilities.runOnUIThread(this.requestShot, 0);
    }

    private static PointF[] toPointF(Point[] r6, int r7, int r8) {
        PointF[] r0 = new PointF[r6.length];
        int r1 = 0;
    L4:
        if (r1 >= r6.length) goto L6;
        Point r3 = r6[r1];
        r0[r1] = new PointF(r3.x / r7, r3.y / r8);
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private QrResult tryReadQr(byte[] r25, Size r26, int r27, int r28, int r29, Bitmap r30) {
        RectF r10 = new RectF();     // Catch: Throwable -> L98
        BarcodeDetector r2 = this.visionQrReader;     // Catch: Throwable -> L98
        float r11 = Float.MIN_VALUE;
        float r12 = Float.MAX_VALUE;
        int r13 = 0;
        if (r2 == null) goto L55;
        if (r2.isOperational() == false) goto L55;
        if (r30 == null) goto L9;
        Frame r22 = new Frame.Builder().setBitmap(r30).build();     // Catch: Throwable -> L98
        int r3 = r30.getWidth();     // Catch: Throwable -> L98
        int r4 = r30.getHeight();     // Catch: Throwable -> L98
    L10:
        SparseArray<Barcode> r23 = this.visionQrReader.detect(r22);     // Catch: Throwable -> L98
        if (r23 != null) goto L13;
    L24:
        if (r30 == null) goto L79;
        Bitmap r24 = invert(r30);     // Catch: Throwable -> L98
        r30.recycle();     // Catch: Throwable -> L98
        Frame r1 = new Frame.Builder().setBitmap(r24).build();     // Catch: Throwable -> L98
        r3 = r24.getWidth();     // Catch: Throwable -> L98
        r4 = r24.getHeight();     // Catch: Throwable -> L98
        SparseArray<Barcode> r14 = this.visionQrReader.detect(r1);     // Catch: Throwable -> L98
        if (r14 != null) goto L28;
    L38:
        Bitmap r15 = monochrome(r24, 90);     // Catch: Throwable -> L98
        r24.recycle();     // Catch: Throwable -> L98
        Frame r16 = new Frame.Builder().setBitmap(r15).build();     // Catch: Throwable -> L98
        r3 = r24.getWidth();     // Catch: Throwable -> L98
        int r210 = r24.getHeight();     // Catch: Throwable -> L98
        SparseArray<Barcode> r17 = this.visionQrReader.detect(r16);     // Catch: Throwable -> L98
        if (r17 != null) goto L41;
    L53:
        r4 = r210;
        goto L79
    L41:
        if (r17.size() <= 0) goto L53;
        Barcode r18 = r17.valueAt(0);     // Catch: Throwable -> L98
        String r42 = r18.rawValue;     // Catch: Throwable -> L98
        PointF[] r5 = toPointF(r18.cornerPoints, r3, r210);     // Catch: Throwable -> L98
        Point[] r19 = r18.cornerPoints;     // Catch: Throwable -> L98
        if (r19 != null) goto L45;
    L51:
        r10 = null;
    L52:
        r4 = r210;
        String r211 = r42;
    L81:
        if (TextUtils.isEmpty(r211) == false) goto L85;
        onNoQrFound();     // Catch: Throwable -> L98
        return null;
    L85:
        if (this.needGalleryButton == false) goto L88;
        Uri.parse(r211).getPath().replace("/", _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L98
    L93:
        QrResult r110 = new QrResult(this, null);     // Catch: Throwable -> L98
        if (r10 == null) goto L96;
        int r6 = AndroidUtilities.dp(25.0f);     // Catch: Throwable -> L98
        float r62 = r6;
        float r7 = AndroidUtilities.dp(15.0f);     // Catch: Throwable -> L98
        r10.set(r10.left - r62, r10.top - r7, r10.right + r62, r10.bottom + r7);     // Catch: Throwable -> L98
        float r32 = r3;
        float r43 = r4;
        r10.set(r10.left / r32, r10.top / r43, r10.right / r32, r10.bottom / r43);     // Catch: Throwable -> L98
    L96:
        r110.cornerPoints = r5;     // Catch: Throwable -> L98
        r110.bounds = r10;     // Catch: Throwable -> L98
        r110.text = r211;     // Catch: Throwable -> L98
        return r110;
    L88:
        if (this.currentType != 2) goto L93;
        if (r211.startsWith("tg://login?token=") == true) goto L93;
        onNoQrFound();     // Catch: Throwable -> L98
        return null;
    L45:
        if (r19.length == 0) goto L51;
        int r63 = r19.length;     // Catch: Throwable -> L98
        float r72 = Float.MIN_VALUE;
        float r8 = Float.MAX_VALUE;
    L48:
        if (r13 >= r63) goto L50;
        Point r142 = r19[r13];     // Catch: Throwable -> L98
        r12 = Math.min(r12, r142.x);     // Catch: Throwable -> L98
        r11 = Math.max(r11, r142.x);     // Catch: Throwable -> L98
        r8 = Math.min(r8, r142.y);     // Catch: Throwable -> L98
        r72 = Math.max(r72, r142.y);     // Catch: Throwable -> L98
        r13 = r13 + 1;     // Catch: Throwable -> L98
        goto L48
    L50:
        r10.set(r12, r8, r11, r72);     // Catch: Throwable -> L98
        goto L52
    L28:
        if (r14.size() <= 0) goto L38;
        Barcode r111 = r14.valueAt(0);     // Catch: Throwable -> L98
        r211 = r111.rawValue;     // Catch: Throwable -> L98
        r5 = toPointF(r111.cornerPoints, r3, r4);     // Catch: Throwable -> L98
        Point[] r112 = r111.cornerPoints;     // Catch: Throwable -> L98
        if (r112 != null) goto L32;
    L23:
        r10 = null;
        goto L81
    L32:
        if (r112.length == 0) goto L23;
        int r64 = r112.length;     // Catch: Throwable -> L98
        float r73 = Float.MIN_VALUE;
        float r82 = Float.MAX_VALUE;
    L35:
        if (r13 >= r64) goto L37;
        Point r143 = r112[r13];     // Catch: Throwable -> L98
        r12 = Math.min(r12, r143.x);     // Catch: Throwable -> L98
        r11 = Math.max(r11, r143.x);     // Catch: Throwable -> L98
        r82 = Math.min(r82, r143.y);     // Catch: Throwable -> L98
        r73 = Math.max(r73, r143.y);     // Catch: Throwable -> L98
        r13 = r13 + 1;     // Catch: Throwable -> L98
        goto L35
    L37:
        r10.set(r12, r82, r11, r73);     // Catch: Throwable -> L98
    L79:
        r211 = null;
        r5 = null;
        goto L81
    L13:
        if (r23.size() <= 0) goto L24;
        Barcode r113 = r23.valueAt(0);     // Catch: Throwable -> L98
        r211 = r113.rawValue;     // Catch: Throwable -> L98
        r5 = toPointF(r113.cornerPoints, r3, r4);     // Catch: Throwable -> L98
        Point[] r114 = r113.cornerPoints;     // Catch: Throwable -> L98
        if (r114 == null) goto L23;
        if (r114.length == 0) goto L23;
        int r65 = r114.length;     // Catch: Throwable -> L98
        float r74 = Float.MIN_VALUE;
        float r83 = Float.MAX_VALUE;
    L20:
        if (r13 >= r65) goto L22;
        Point r144 = r114[r13];     // Catch: Throwable -> L98
        r12 = Math.min(r12, r144.x);     // Catch: Throwable -> L98
        r11 = Math.max(r11, r144.x);     // Catch: Throwable -> L98
        r83 = Math.min(r83, r144.y);     // Catch: Throwable -> L98
        r74 = Math.max(r74, r144.y);     // Catch: Throwable -> L98
        r13 = r13 + 1;     // Catch: Throwable -> L98
        goto L20
    L22:
        r10.set(r12, r83, r11, r74);     // Catch: Throwable -> L98
        goto L81
    L9:
        r22 = new Frame.Builder().setImageData(ByteBuffer.wrap(r25), r26.getWidth(), r26.getHeight(), 17).build();     // Catch: Throwable -> L98
        r3 = r26.getWidth();     // Catch: Throwable -> L98
        r4 = r26.getWidth();     // Catch: Throwable -> L98
    L55:
        if (this.qrReader == null) goto L78;
        if (r30 == null) goto L58;
        int[] r212 = new int[r30.getWidth() * r30.getHeight()];     // Catch: Throwable -> L98
        r30.getPixels(r212, 0, r30.getWidth(), 0, 0, r30.getWidth(), r30.getHeight());     // Catch: Throwable -> L98
        LuminanceSource r115 = new RGBLuminanceSource(r30.getWidth(), r30.getHeight(), r212);     // Catch: Throwable -> L98
        int r213 = r30.getWidth();     // Catch: Throwable -> L98
        r4 = r30.getHeight();     // Catch: Throwable -> L98
        r3 = r213;
    L59:
        Result r116 = this.qrReader.decode(new BinaryBitmap(new GlobalHistogramBinarizer(r115)));     // Catch: Throwable -> L98
        if (r116 != null) goto L63;
        onNoQrFound();     // Catch: Throwable -> L98
        return null;
    L63:
        r211 = r116.getText();     // Catch: Throwable -> L98
        if (r116.getResultPoints() != null) goto L66;
    L77:
        r5 = null;
        r10 = null;
        goto L81
    L66:
        if (r116.getResultPoints().length == 0) goto L77;
        ResultPoint[] r52 = r116.getResultPoints();     // Catch: Throwable -> L98
        int r66 = r52.length;     // Catch: Throwable -> L98
        float r75 = Float.MIN_VALUE;
        float r84 = Float.MAX_VALUE;
        int r145 = 0;
    L69:
        if (r145 >= r66) goto L71;
        ResultPoint r152 = r52[r145];     // Catch: Throwable -> L98
        r12 = Math.min(r12, r152.getX());     // Catch: Throwable -> L98
        r11 = Math.max(r11, r152.getX());     // Catch: Throwable -> L98
        r84 = Math.min(r84, r152.getY());     // Catch: Throwable -> L98
        r75 = Math.max(r75, r152.getY());     // Catch: Throwable -> L98
        r145 = r145 + 1;     // Catch: Throwable -> L98
        goto L69
    L71:
        r10.set(r12, r84, r11, r75);     // Catch: Throwable -> L98
        if (r116.getResultPoints().length != 4) goto L76;
        r5 = new PointF[4];     // Catch: Throwable -> L98
        int r132 = 0;
    L74:
        if (r132 >= 4) goto L81;
        r5[r132] = new PointF(r116.getResultPoints()[r132].getX() / r3, r116.getResultPoints()[r132].getY() / r4);     // Catch: Throwable -> L98
        r132 = r132 + 1;     // Catch: Throwable -> L98
        goto L74
    L76:
        r5 = null;
        goto L81
    L58:
        PlanarYUVLuminanceSource r146 = new PlanarYUVLuminanceSource(r25, r26.getWidth(), r26.getHeight(), r27, r28, r29, r29, false);     // Catch: Throwable -> L98
        r3 = r26.getWidth();     // Catch: Throwable -> L98
        r4 = r26.getHeight();     // Catch: Throwable -> L98
        r115 = r146;
        goto L59
    L78:
        r3 = 1;
        r4 = 1;
    L98:
        onNoQrFound();
        return null;
    }

    private void updateNormalBounds() {
        if (this.normalBounds != null) goto L5;
        this.normalBounds = new RectF();
    L5:
        int r0 = this.fragmentView.getWidth();
        int r2 = (int) (Math.min(r0, r1) / 1.5f);
        float r5 = r0;
        float r7 = this.fragmentView.getHeight();
        this.normalBounds.set(((r0 - r2) / 2.0f) / r5, ((r1 - r2) / 2.0f) / r7, ((r0 + r2) / 2.0f) / r5, ((r1 + r2) / 2.0f) / r7);
    }

    private void updateRecognized() {
        float r0 = this.recognizedT;
        if (this.recognized == false) goto L5;
        float r1 = 1.0f;
    L6:
        this.newRecognizedT = r1;
        if (r0 == r1) goto L20;
        ValueAnimator r02 = this.recognizedAnimator;
        if (r02 == null) goto L11;
        r02.cancel();
    L11:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{this.recognizedT, this.newRecognizedT});
        this.recognizedAnimator = r03;
        r03.addUpdateListener(new CameraScanActivity$$ExternalSyntheticLambda21(this));
        this.recognizedAnimator.setDuration((long) (Math.abs(this.recognizedT - this.newRecognizedT) * 300.0f));
        this.recognizedAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.recognizedAnimator.start();
        SpringAnimation r04 = this.useRecognizedBoundsAnimator;
        if (r04 == null) goto L14;
        r04.cancel();
    L14:
        boolean r3 = this.recognized;
        float r4 = this.useRecognizedBounds;
        if (r3 == true) goto L18;
        r4 = 1.0f - r4;
    L18:
        SpringAnimation r05 = new SpringAnimation(new FloatValueHolder(r4 * 500.0f));
        this.useRecognizedBoundsAnimator = r05;
        r05.addUpdateListener(new CameraScanActivity$$ExternalSyntheticLambda22(this));
        this.useRecognizedBoundsAnimator.setSpring(new SpringForce(500.0f));
        this.useRecognizedBoundsAnimator.getSpring().setDampingRatio(1.0f);
        this.useRecognizedBoundsAnimator.getSpring().setStiffness(500.0f);
        this.useRecognizedBoundsAnimator.start();
        return;
    L20:
        return;
    L5:
        r1 = 0.0f;
        goto L6
    }

    private void updateRecognizedBounds(RectF r12, PointF[] r13) {
        long r0 = SystemClock.elapsedRealtime();
        long r2 = this.lastBoundsUpdate;
        int r8 = 0;
        if (r2 != 0) goto L9;
        this.lastBoundsUpdate = r0 - 75;
        this.bounds.set(r12);
        this.fromBounds.set(r12);
        if (r13 != null) goto L7;
        setPointsFromBounds(r12, this.fromPoints);
        setPointsFromBounds(r12, this.points);
    L25:
        this.fragmentView.invalidate();
        return;
    L7:
        if (r8 >= 4) goto L25;
        PointF r122 = this.fromPoints[r8];
        PointF r02 = r13[r8];
        r122.set(r02.x, r02.y);
        PointF r123 = this.points[r8];
        PointF r03 = r13[r8];
        r123.set(r03.x, r03.y);
        r8 = r8 + 1;
        goto L7
    L9:
        RectF r4 = this.fromBounds;
        if (r4 != null) goto L12;
    L16:
        r4.set(this.bounds);
        int r22 = 0;
    L17:
        if (r22 >= 4) goto L19;
        PointF r3 = this.fromPoints[r22];
        PointF r42 = this.points[r22];
        r3.set(r42.x, r42.y);
        r22 = r22 + 1;
    L19:
        this.bounds.set(r12);
        if (r13 != null) goto L22;
        setPointsFromBounds(this.bounds, this.points);
    L24:
        this.lastBoundsUpdate = r0;
    L22:
        if (r8 >= 4) goto L24;
        PointF r124 = this.points[r8];
        PointF r23 = r13[r8];
        r124.set(r23.x, r23.y);
        r8 = r8 + 1;
        goto L22
    L12:
        if ((r0 - r2) >= 75) goto L16;
        float r24 = Math.min(1.0f, Math.max(0.0f, (r0 - r2) / 75.0f));
        RectF r32 = this.fromBounds;
        AndroidUtilities.lerp(r32, this.bounds, r24, r32);
        int r33 = 0;
    L14:
        if (r33 >= 4) goto L19;
        PointF r43 = this.fromPoints[r33];
        r43.set(AndroidUtilities.lerp(r43.x, this.points[r33].x, r24), AndroidUtilities.lerp(this.fromPoints[r33].y, this.points[r33].y, r24));
        r33 = r33 + 1;
        goto L14
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r17) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        boolean r2 = this.shownAsBottomSheet;
        ActionBar r3 = this.actionBar;
        if (r2 == false) goto L5;
        r3.setItemsColor(-1, false);
        this.actionBar.setItemsBackgroundColor(-1, false);
        this.actionBar.setTitleColor(-1);
    L6:
        this.actionBar.setCastShadows(false);
        if (AndroidUtilities.isTablet() == false) goto L9;
    L11:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        this.paint.setColor(2130706432);
        this.cornerPaint.setColor(-1);
        this.cornerPaint.setStyle(Paint.Style.FILL);
        AnonymousClass3 r22 = new AnonymousClass3(this, r17);
        r22.setOnTouchListener(new CameraScanActivity$$ExternalSyntheticLambda0());
        this.fragmentView = r22;
        if (isQr() == false) goto L14;
        this.fragmentView.postDelayed(new CameraScanActivity$$ExternalSyntheticLambda1(this), 450);
    L15:
        int r32 = this.currentType;
        ActionBar r6 = this.actionBar;
        if (r32 != 0) goto L18;
        int r33 = Theme.key_windowBackgroundWhite;
        r6.setBackgroundColor(Theme.getColor(r33));
        this.fragmentView.setBackgroundColor(Theme.getColor(r33));
    L19:
        int r34 = this.currentType;
        int r62 = 2;
        if (r34 == 2) goto L22;
        if (r34 == 3) goto L22;
    L23:
        Paint r35 = new Paint(1);
        r35.setPathEffect(LinkPath.getRoundedEffect());
        r35.setColor(ColorUtils.setAlphaComponent(-1, 40));
        AnonymousClass4 r10 = new AnonymousClass4(this, r17, r35);
        this.titleTextView = r10;
        r10.setGravity(1);
        this.titleTextView.setTextSize(1, 24.0f);
        r22.addView(this.titleTextView);
        TextView r36 = new TextView(r17);
        this.descriptionText = r36;
        r36.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText6));
        this.descriptionText.setGravity(1);
        this.descriptionText.setTextSize(1, 16.0f);
        r22.addView(this.descriptionText);
        TextView r37 = new TextView(r17);
        this.recognizedMrzView = r37;
        r37.setTextColor(-1);
        this.recognizedMrzView.setGravity(81);
        this.recognizedMrzView.setAlpha(0.0f);
        int r38 = this.currentType;
        if (r38 != 0) goto L27;
        this.titleTextView.setText(LocaleController.getString(R.string.PassportScanPassport));
        this.descriptionText.setText(LocaleController.getString(R.string.PassportScanPassportInfo));
        this.titleTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.recognizedMrzView.setTypeface(Typeface.MONOSPACE);
    L52:
        AndroidUtilities.lockOrientation(getParentActivity(), 1);
        this.fragmentView.setKeepScreenOn(true);
        return this.fragmentView;
    L27:
        if (this.needGalleryButton == true) goto L41;
        if (r38 == 1) goto L40;
        if (r38 == 3) goto L40;
        SpannableStringBuilder r11 = new SpannableStringBuilder(LocaleController.getString(R.string.AuthAnotherClientInfo5));
        String[] r39 = {LocaleController.getString(R.string.AuthAnotherClientDownloadClientUrl), LocaleController.getString(R.string.AuthAnotherWebClientUrl)};
        int r12 = 0;
    L33:
        if (r12 >= r62) goto L39;
        String r13 = r11.toString();
        int r15 = r13.indexOf(42);
        int r63 = r15 + 1;
        int r132 = r13.indexOf(42, r63);
        if (r15 == (-1)) goto L39;
        if (r132 == (-1)) goto L39;
        if (r15 == r132) goto L39;
        this.titleTextView.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r11.replace(r132, r132 + 1, " ");
        r11.replace(r15, r63, " ");
        r11.setSpan(new URLSpanNoUnderline(r39[r12], true), r63, r132, 33);
        r11.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r63, r132, 33);
        r12 = r12 + 1;
        r62 = 2;
    L39:
        this.titleTextView.setLinkTextColor(-1);
        this.titleTextView.setTextSize(1, 16.0f);
        this.titleTextView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.titleTextView.setPadding(0, 0, 0, 0);
        this.titleTextView.setText(r11);
    L40:
        this.titleTextView.setText(LocaleController.getString(R.string.AuthAnotherClientScan));
    L41:
        this.titleTextView.setTextColor(-1);
        if (this.currentType != 3) goto L44;
        this.descriptionText.setTextColor(-1711276033);
    L44:
        this.recognizedMrzView.setTextSize(1, 16.0f);
        this.recognizedMrzView.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f));
        if (this.needGalleryButton == true) goto L48;
        this.recognizedMrzView.setText(LocaleController.getString(R.string.AuthAnotherClientNotFound));
    L48:
        r22.addView(this.recognizedMrzView);
        if (this.needGalleryButton == false) goto L51;
        ImageView r310 = new ImageView(r17);
        this.galleryButton = r310;
        r310.setScaleType(ImageView.ScaleType.CENTER);
        this.galleryButton.setImageResource(R.drawable.qr_gallery);
        this.galleryButton.setBackgroundDrawable(Theme.createSelectorDrawableFromDrawables(Theme.createCircleDrawable(AndroidUtilities.dp(60.0f), 587202559), Theme.createCircleDrawable(AndroidUtilities.dp(60.0f), 1157627903)));
        r22.addView(this.galleryButton);
        this.galleryButton.setOnClickListener(new CameraScanActivity$$ExternalSyntheticLambda2(this));
    L51:
        ImageView r311 = new ImageView(r17);
        this.flashButton = r311;
        r311.setScaleType(ImageView.ScaleType.CENTER);
        this.flashButton.setImageResource(R.drawable.qr_flashlight);
        this.flashButton.setBackgroundDrawable(Theme.createCircleDrawable(AndroidUtilities.dp(60.0f), 587202559));
        r22.addView(this.flashButton);
        this.flashButton.setOnClickListener(new CameraScanActivity$$ExternalSyntheticLambda3(this));
    L22:
        this.actionBar.setTitle(LocaleController.getString(R.string.AuthAnotherClientScan));
        goto L23
    L18:
        r6.setBackgroundDrawable(null);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setTitleColor(-1);
        this.actionBar.setItemsColor(-1, false);
        this.actionBar.setItemsBackgroundColor(587202559, false);
        r22.setBackgroundColor(-16777216);
        r22.addView(this.actionBar);
        goto L19
    L14:
        initCameraView();
        goto L15
    L9:
        if (isQr() == true) goto L11;
        this.actionBar.showActionModeTop();
        goto L11
    L5:
        r3.setItemsColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarWhiteSelector), false);
        this.actionBar.setTitleColor(Theme.getColor(Theme.key_actionBarDefaultTitle));
        goto L6
    }

    public void destroy(boolean r3, Runnable r4) {
        CameraView r0 = this.cameraView;
        if (r0 == null) goto L5;
        r0.destroy(r3, r4);
        this.cameraView = null;
    L5:
        this.flashButton.setTag(null);
        this.backgroundHandlerThread.quitSafely();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        if (isQr() == false) goto L5;
        return r1;
    L5:
        View r4 = this.fragmentView;
        int r5 = ThemeDescription.FLAG_BACKGROUND;
        int r10 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r10));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarWhiteSelector));
        r1.add(new ThemeDescription(this.titleTextView, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.descriptionText, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText6));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r2, int r3, Intent r4) {
        if (r3 == (-1)) goto L5;
        return;
    L5:
        if (r2 != 11) goto L12;
        if (r4 != null) goto L8;
        return;
    L8:
        if (r4.getData() == null) goto L14;
        processGalleryQr(null, r4.getData(), null);
        return;
    L14:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        destroy(false, null);
        AndroidUtilities.unlockOrientation(getParentActivity());
        BarcodeDetector r0 = this.visionQrReader;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        ChatAttachAlert r02 = this.galleryPickerAlert;
        if (r02 == null) goto L9;
        r02.dismissInternal();
        this.galleryPickerAlert.onDestroy();
        this.galleryPickerAlert = null;
        return;
    }

    public void processShot(Bitmap r15) {
        if (this.cameraView == null) goto L82;
        long r0 = SystemClock.elapsedRealtime();
        Size r7 = this.cameraView.getPreviewSize();     // Catch: Throwable -> L23
        if (this.currentType != 0) goto L29;
        MrzRecognizer.Result r152 = MrzRecognizer.recognize(r15, false);     // Catch: Throwable -> L23
        if (r152 != null) goto L11;
    L28:
        CameraScanActivity r5 = this;
    L74:
        long r14 = SystemClock.elapsedRealtime() - r0;
        float r02 = r5.averageProcessTime;
        long r6 = r5.processTimesCount;
        float r03 = (r02 * r6) + r14;
        long r62 = r6 + 1;
        r5.processTimesCount = r62;
        r5.averageProcessTime = r03 / r62;
        r5.processTimesCount = Math.max(r62, 30);
        if (r5.recognized == true) goto L81;
        AndroidUtilities.runOnUIThread(r5.requestShot, 500);
        return;
    L81:
        return;
    L11:
        if (TextUtils.isEmpty(r152.firstName) == true) goto L28;
        if (TextUtils.isEmpty(r152.lastName) == true) goto L28;
        if (TextUtils.isEmpty(r152.number) == true) goto L28;
        if (r152.birthDay == 0) goto L28;
        if (r152.expiryDay != 0) goto L25;
        if (r152.doesNotExpire == false) goto L28;
    L25:
        if (r152.gender == 0) goto L28;
        this.recognized = true;     // Catch: Throwable -> L23
        CameraController.getInstance().stopPreview(this.cameraView.getCameraSession());     // Catch: Throwable -> L23
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda12(this, r152));     // Catch: Throwable -> L23
        return;
    L29:
        int r10 = (int) (Math.min(r7.getWidth(), r7.getHeight()) / 1.5f);     // Catch: Throwable -> L23
        r5 = this;
        QrResult r142 = r5.tryReadQr(null, r7, (r7.getWidth() - r10) / 2, (r7.getHeight() - r10) / 2, r10, r15);     // Catch: Throwable -> L78
        boolean r153 = r5.recognized;     // Catch: Throwable -> L78
        if (r153 == false) goto L34;
        r5.recognizeIndex++;
    L34:
        if (r142 == null) goto L39;
        r5.recognizeFailed = 0;     // Catch: Throwable -> L78
        String r4 = r142.text;     // Catch: Throwable -> L78
        r5.recognizedText = r4;     // Catch: Throwable -> L78
        if (r153 == true) goto L38;
        r5.recognized = true;     // Catch: Throwable -> L78
        r5.qrLoading = r5.delegate.processQr(r4, new CameraScanActivity$$ExternalSyntheticLambda13(r5));     // Catch: Throwable -> L78
        r5.recognizedStart = SystemClock.elapsedRealtime();     // Catch: Throwable -> L78
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda14(r5));     // Catch: Throwable -> L78
    L38:
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda15(r5, r142));     // Catch: Throwable -> L78
    L47:
        if (r5.recognizeIndex != 0) goto L54;
        if (r142 == null) goto L54;
        if (r142.bounds != null) goto L54;
        if (r5.qrLoading == true) goto L54;
    L58:
        if (r5.recognizedText == null) goto L70;
        CameraView r143 = r5.cameraView;     // Catch: Throwable -> L78
        if (r143 != null) goto L62;
    L66:
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda16(r5, r5.recognizedText));     // Catch: Throwable -> L78
        if (r5.currentType != 3) goto L74;
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda17(r5));     // Catch: Throwable -> L78
        goto L74
    L62:
        if (r143.getCameraSession() == null) goto L66;
        if (r5.currentType == 3) goto L66;
        CameraController.getInstance().stopPreview(r5.cameraView.getCameraSession());     // Catch: Throwable -> L78
    L70:
        if (r5.recognized == false) goto L74;
        r5.handler.postDelayed(new CameraScanActivity$$ExternalSyntheticLambda18(r5), Math.max(16, (1000 / r5.sps) - ((long) r5.averageProcessTime)));     // Catch: Throwable -> L78
    L54:
        if ((SystemClock.elapsedRealtime() - r5.recognizedStart) <= 1000) goto L70;
        if (r5.qrLoading == true) goto L70;
    L39:
        if (r153 == false) goto L47;
        int r154 = r5.recognizeFailed + 1;     // Catch: Throwable -> L78
        r5.recognizeFailed = r154;     // Catch: Throwable -> L78
        if (r154 <= 4) goto L47;
        if (r5.qrLoading == true) goto L47;
        r5.recognized = false;     // Catch: Throwable -> L78
        r5.recognizeIndex = 0;     // Catch: Throwable -> L78
        r5.recognizedText = null;     // Catch: Throwable -> L78
        AndroidUtilities.runOnUIThread(new CameraScanActivity$$ExternalSyntheticLambda14(r5));     // Catch: Throwable -> L78
        AndroidUtilities.runOnUIThread(r5.requestShot, 500);     // Catch: Throwable -> L78
        return;
    L73:
        r5.onNoQrFound();
    L23:
        r5 = this;
        goto L73
    }

    public void setDelegate(CameraScanActivityDelegate r1) {
        this.delegate = r1;
    }
}
